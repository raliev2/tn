/**
 * 
 */
package com.teamidea.platform.technonikol.facades.search.impl;

import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commercefacades.search.solrfacetsearch.impl.DefaultSolrProductSearchFacade;
import de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;

import javax.annotation.Resource;


public class DefaultB2BSolrProductSearchFacade<ITEM extends ProductData> extends DefaultSolrProductSearchFacade<ITEM>
{
	@Resource(name = "b2bProductSearchUtil")
	private DefaultB2BProductSearchUtil<ITEM> b2bProductSearchUtil;

	@Override
	public ProductSearchPageData<SearchStateData, ITEM> textSearch(final String text)
	{
		final ProductSearchPageData<SearchStateData, ITEM> pageData = super.textSearch(text);
		b2bProductSearchUtil.populateVariantProducts(pageData);
		return pageData;
	}

	@Override
	public ProductSearchPageData<SearchStateData, ITEM> textSearch(final SearchStateData searchState,
			final PageableData pageableData)
	{
		final ProductSearchPageData<SearchStateData, ITEM> pageData = super.textSearch(searchState, pageableData);
		b2bProductSearchUtil.populateVariantProducts(pageData);
		return pageData;
	}

	@Override
	public ProductCategorySearchPageData<SearchStateData, ITEM, CategoryData> categorySearch(final String categoryCode)
	{
		final ProductCategorySearchPageData<SearchStateData, ITEM, CategoryData> pageData = super.categorySearch(categoryCode);
		b2bProductSearchUtil.populateVariantProducts(pageData);
		return pageData;
	}

	@Override
	public ProductCategorySearchPageData<SearchStateData, ITEM, CategoryData> categorySearch(final String categoryCode,
			final SearchStateData searchState, final PageableData pageableData)
	{
		final ProductCategorySearchPageData<SearchStateData, ITEM, CategoryData> pageData = super.categorySearch(categoryCode,
				searchState, pageableData);
		b2bProductSearchUtil.populateVariantProducts(pageData);
		return pageData;
	}
}
