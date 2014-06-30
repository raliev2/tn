package com.teamidea.platform.technonikol.storefront.title;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import de.hybris.platform.acceleratorservices.storefront.util.PageTitleResolver;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commerceservices.search.facetdata.BreadcrumbData;
import de.hybris.platform.core.model.product.ProductModel;

public class DefaultPageTitleResolver extends PageTitleResolver {

	@Override
	public String resolveContentPageTitle(String title) {
		String returning = super.resolveContentPageTitle(title);
		returning = StringEscapeUtils.unescapeHtml(returning);
		return returning;
	}

	@Override
	public String resolveHomePageTitle(String title) {
		String returning = super.resolveHomePageTitle(title);
		returning = StringEscapeUtils.unescapeHtml(returning);
		return returning;
	}

	@Override
	public <STATE> String resolveSearchPageTitle(String searchText,
			List<BreadcrumbData<STATE>> appliedFacets) {
		String returning = super.resolveSearchPageTitle(searchText, appliedFacets);
		returning = StringEscapeUtils.unescapeHtml(returning);
		return returning;
	}

	@Override
	public String resolveCategoryPageTitle(CategoryModel category) {
		String returning = super.resolveCategoryPageTitle(category);
		returning = StringEscapeUtils.unescapeHtml(returning);
		return returning;
	}

	@Override
	public <STATE> String resolveCategoryPageTitle(CategoryModel category,
			List<BreadcrumbData<STATE>> appliedFacets) {
		String returning = super.resolveCategoryPageTitle(category, appliedFacets);
		returning = StringEscapeUtils.unescapeHtml(returning);
		return returning;
	}

	@Override
	public <STATE> String resolveCategoryPageTitle(String categoryCode,
			List<BreadcrumbData<STATE>> appliedFacets) {
		String returning = super.resolveCategoryPageTitle(categoryCode, appliedFacets);
		returning = StringEscapeUtils.unescapeHtml(returning);
		return returning;
	}

	@Override
	public String resolveProductPageTitle(ProductModel product) {
		String returning = super.resolveProductPageTitle(product);
		returning = StringEscapeUtils.unescapeHtml(returning);
		return returning;
	}

	@Override
	public String resolveProductPageTitle(String productCode) {
		String returning = super.resolveProductPageTitle(productCode);
		returning = StringEscapeUtils.unescapeHtml(returning);
		return returning;
	}

}
