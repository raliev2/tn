/**
 * 
 */
package com.teamidea.platform.technonikol.facades.search.solrfacetsearch.querybuilder;

import de.hybris.platform.commerceservices.search.solrfacetsearch.querybuilder.impl.DefaultFreeTextQueryBuilder;
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

	@Override
	protected void addFreeTextQuery(final SearchQuery searchQuery, final String field, final String value, final String suffixOp,
			final double boost)
	{
		searchQuery.searchInField(field, ClientUtils.escapeQueryChars(value) + suffixOp + "^" + boost, SearchQuery.Operator.AND);
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
