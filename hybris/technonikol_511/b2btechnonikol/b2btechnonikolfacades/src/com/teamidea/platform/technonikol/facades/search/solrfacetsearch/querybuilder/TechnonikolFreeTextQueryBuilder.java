/**
 * 
 */
package com.teamidea.platform.technonikol.facades.search.solrfacetsearch.querybuilder;

import de.hybris.platform.commerceservices.search.solrfacetsearch.querybuilder.impl.DefaultFreeTextQueryBuilder;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Nullable;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.util.ClientUtils;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * @author Aleksey Lubimov
 * 
 */
public class TechnonikolFreeTextQueryBuilder extends DefaultFreeTextQueryBuilder
{
	private static final Logger LOG = Logger.getLogger(TechnonikolFreeTextQueryBuilder.class);
	private static final String TEXT_FIELD = "text";
	private static final String BOOST_OPERATOR = "^";

	private boolean specialProperty = false;

	private static final String[] suffixOp = new String[]
	{ "", "*", "~" };
	private static final double[] boostDiv = new double[]
	{ 1D, 2D, 4D };
	private static final String[] specialSuffixOp = new String[]
	{ "" };
	private static final double[] specialBoostDiv = new double[]
	{ 0.5D };

	@Override
	protected void addFreeTextQuery(final SearchQuery searchQuery, final IndexedProperty indexedProperty, final String fullText,
			final String[] textWords, final int boost)
	{
		// Для спец.свойств не кидаем в запрос выражение для полного текста - только одиночные слова
		if (!specialProperty)
		{
			addFreeTextQuery(searchQuery, indexedProperty, fullText, boost * 2.0D);
		}

		if (textWords == null)
		{
			return;
		}

		final Collection<String> words = removeCommas(textWords);
		// В случае обычного свойства и одиночного слова выходим.
		if (!specialProperty && words.size() == 1)
		{
			return;
		}

		if (specialProperty)
		{
			addSingleWordsExpressionSpecial(searchQuery, indexedProperty, words, boost);
		}
		else
		{
			addSingleWordsExpression(searchQuery, indexedProperty, words, boost);
		}
	}

	/**
	 * @param searchQuery
	 * @param indexedProperty
	 * @param textWords
	 * @param boost
	 */
	private void addSingleWordsExpression(final SearchQuery searchQuery, final IndexedProperty indexedProperty,
			final Collection<String> textWords, final int boost)
	{
		final StringBuilder subquery = new StringBuilder();
		subquery.append("(");
		for (int i = 0; i < suffixOp.length; i++)
		{
			if (i > 0)
			{
				subquery.append(" OR ");
			}
			subquery.append("(");
			boolean firstWord = true;
			for (final String word : textWords)
			{
				if (!firstWord)
				{
					subquery.append(" AND ");
				}
				firstWord = false;
				appendFreeTextQuery(subquery, word, suffixOp[i], boost / boostDiv[i]);
			}
			subquery.append(")");
		}
		subquery.append(")");
		addFreeTextSubquery(searchQuery, indexedProperty, subquery.toString());
	}

	/**
	 * @param searchQuery
	 * @param indexedProperty
	 * @param textWords
	 * @param boost
	 */
	private void addSingleWordsExpressionSpecial(final SearchQuery searchQuery, final IndexedProperty indexedProperty,
			final Collection<String> textWords, final int boost)
	{
		final StringBuilder subquery = new StringBuilder();
		subquery.append("(");
		for (int i = 0; i < specialSuffixOp.length; i++)
		{
			if (i > 0)
			{
				subquery.append(" OR ");
			}
			subquery.append("(");
			boolean firstWord = true;
			for (final String word : textWords)
			{
				if (!firstWord)
				{
					subquery.append(" OR ");
				}
				firstWord = false;
				appendFreeTextQuery(subquery, word, specialSuffixOp[i], boost / specialBoostDiv[i]);
			}
			subquery.append(")");
		}
		subquery.append(")");
		addFreeTextSubquery(searchQuery, indexedProperty, subquery.toString());
	}

	/**
	 * Трансформируем исходный массив в слов/выражений в список слов
	 * 
	 * @param textWords
	 * @return
	 */
	private Collection<String> removeCommas(final String[] textWords)
	{
		// трансформация выражений в отдельные слова
		final Collection<Collection<String>> dirtyWords = Collections2.transform(Arrays.asList(textWords),
				new Function<String, Collection<String>>()
				{
					@Override
					@Nullable
					public Collection<String> apply(@Nullable final String dirtyWord)
					{
						return Arrays.asList(dirtyWord.split("[;., ]"));
					}
				});

		// трансформация в одноуровневую коллекцию
		final Collection<String> words = Lists.newArrayList(Iterables.concat(dirtyWords));

		// удаляем слова пустышки и однобуквенные
		return Collections2.filter(words, new Predicate<String>()
		{
			@Override
			public boolean apply(@Nullable final String value)
			{
				return value != null && value.length() > 1;
			}
		});
	}

	private void appendFreeTextQuery(final StringBuilder subquery, final String value, final String suffixOp, final double boost)
	{
		subquery.append(ClientUtils.escapeQueryChars(value)).append(suffixOp != null ? suffixOp : "").append(BOOST_OPERATOR)
				.append(boost);
	}

	private void addFreeTextSubquery(final SearchQuery searchQuery, final IndexedProperty indexedProperty, final String subquery)
	{
		final String field = indexedProperty.getName();
		if (!(indexedProperty.isFacet()))
		{
			searchQuery.searchInField(field, subquery, SearchQuery.Operator.AND);
		}
		else
		{
			LOG.warn("Not searching " + indexedProperty
					+ ". Free text search not available in facet property. Configure an additional text property for searching.");
		}
	}

	@Override
	protected void addFreeTextQuery(final SearchQuery searchQuery, final IndexedProperty indexedProperty, final String value,
			final double boost)
	{
		final String field = indexedProperty.getName();
		if (!(indexedProperty.isFacet()))
		{
			if (TEXT_FIELD.equalsIgnoreCase(indexedProperty.getType()))
			{
				//addFreeTextQuery(searchQuery, field, value.toLowerCase(), "", boost);
				addFreeTextQuery(searchQuery, field, value.toLowerCase(), "*", boost / 2.0D);
				//addFreeTextQuery(searchQuery, field, value.toLowerCase(), "~", boost / 4.0D);
			}
			else
			{
				//addFreeTextQuery(searchQuery, field, value.toLowerCase(), "", boost);
				addFreeTextQuery(searchQuery, field, value.toLowerCase(), "*", boost / 2.0D);
			}
		}
		else
		{
			LOG.warn("Not searching " + indexedProperty
					+ ". Free text search not available in facet property. Configure an additional text property for searching.");
		}
	}

	@Override
	protected void addFreeTextQuery(final SearchQuery searchQuery, final String field, final String value, final String suffixOp,
			final double boost)
	{
		searchQuery.searchInField(field, prepareLong(value, suffixOp) + BOOST_OPERATOR + boost,
				specialProperty ? SearchQuery.Operator.OR : SearchQuery.Operator.AND);
	}

	private String prepareLong(final String value, final String suffixOp)
	{
		final String[] words = value.split("[, ]");

		if (words.length > 1)
		{
			final StringBuilder b = new StringBuilder();
			b.append("(");
			boolean notEmpty = false;
			for (int i = 0; i < words.length; i++)
			{
				if (words[i].length() > 1)
				{
					if (notEmpty)
					{
						b.append(" ");
					}
					b.append(ClientUtils.escapeQueryChars(words[i])).append(suffixOp);
					notEmpty = true;
				}
			}
			b.append(")");
			return b.toString();
		}

		return ClientUtils.escapeQueryChars(value.trim()) + suffixOp;
	}

	public boolean isSpecialProperty()
	{
		return specialProperty;
	}

	public void setSpecialProperty(final boolean specialProperty)
	{
		this.specialProperty = specialProperty;
	}

}
