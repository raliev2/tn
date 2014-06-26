/**
 * 
 */
package com.teamidea.platform.technonikol.facades.search.solrfacetsearch.querybuilder;

import de.hybris.platform.commerceservices.search.solrfacetsearch.querybuilder.impl.DefaultFreeTextQueryBuilder;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.util.ClientUtils;


/**
 * @author Aleksey Lubimov
 * 
 */
public class TechnonikolFreeTextQueryBuilder extends DefaultFreeTextQueryBuilder
{
	private static final Logger LOG = Logger.getLogger(TechnonikolFreeTextQueryBuilder.class);
	private static final String TEXT_FIELD = "text";
	private static final String BOOST_OPERATOR = "^";

	private static final String[] suffixOp = new String[]
	{ "", "*", "~" };
	private static final double[] boostDiv = new double[]
	{ 1D, 2D, 4D };

	@Override
	protected void addFreeTextQuery(final SearchQuery searchQuery, final IndexedProperty indexedProperty, final String fullText,
			final String[] textWords, final int boost)
	{
		addFreeTextQuery(searchQuery, indexedProperty, fullText, boost * 2.0D);

		if ((textWords == null) || (textWords.length <= 1))
		{
			return;
		}

		final StringBuilder subquery = new StringBuilder();
		subquery.append("(");
		for (int i = 0; i < 3; i++)
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
			if ("text".equalsIgnoreCase(indexedProperty.getType()))
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
		if (field.toString().equals("code"))
		{
			searchQuery.searchInField(field, prepareLong(value, suffixOp) + BOOST_OPERATOR + boost, SearchQuery.Operator.OR);
		}
		else
		{
			searchQuery.searchInField(field, prepareLong(value, suffixOp) + BOOST_OPERATOR + boost, SearchQuery.Operator.AND);
		}
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

	//	protected Set<String> getFreeTextQueryValues(final IndexedProperty indexedProperty, final String value, final double boost)
	//	{
	//		final Set<String> values = new TreeSet<String>();
	//		final String escapedValue = "\"" + ClientUtils.escapeQueryChars(String.valueOf(value.toLowerCase())) + "\"";
	//		if (!indexedProperty.isFacet())
	//		{
	//			if (TEXT_FIELD.equalsIgnoreCase(indexedProperty.getType()))
	//			{
	//				values.add(escapedValue + "" + BOOST_OPERATOR + boost);
	//				values.add(escapedValue + "*" + BOOST_OPERATOR + boost / 2.0D);
	//				values.add(escapedValue + "~" + BOOST_OPERATOR + boost / 4.0D);
	//			}
	//			else
	//			{
	//				values.add(escapedValue + "" + BOOST_OPERATOR + boost);
	//				values.add(escapedValue + "*" + BOOST_OPERATOR + boost / 2.0D);
	//			}
	//		}
	//		else
	//		{
	//			LOG.warn("Not searching " + indexedProperty
	//					+ ". Free text search not available in facet property. Configure an additional text property for searching.");
	//		}
	//		return values;
	//	}
	//
	//	@Override
	//	protected void addFreeTextQuery(final SearchQuery searchQuery, final IndexedProperty indexedProperty, final String fullText,
	//			final String[] textWords, final int boost)
	//	{
	//		final String field = indexedProperty.getName();
	//		final Set<String> fullTextValues = getFreeTextQueryValues(indexedProperty, fullText, boost * 2.0D);
	//
	//		final QueryField fullTextQueryField = new QueryField(field, fullTextValues, SearchQuery.Operator.AND);
	//		addQueryField(searchQuery, fullTextQueryField);
	//
	//		if ((textWords != null) && (textWords.length > 1))
	//		{
	//			final Set<String> textWordsValues = new TreeSet<String>();
	//			for (final String word : textWords)
	//			{
	//				textWordsValues.addAll(getFreeTextQueryValues(indexedProperty, word, boost));
	//			}
	//			final QueryField textWordsQueryField = new QueryField(field, textWordsValues, SearchQuery.Operator.AND);
	//			addQueryField(searchQuery, textWordsQueryField);
	//		}
	//	}
	//
	//	private void addQueryField(final SearchQuery searchQuery, final QueryField fullTextQueryField)
	//	{
	//		searchQuery.getAllFields().add(fullTextQueryField);
	//	}
}
