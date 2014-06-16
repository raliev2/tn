/**
 *
 */
package com.teamidea.platform.technonikol.facades.search.impl;

import de.hybris.platform.b2b.model.GenericVariantProductModel;
import de.hybris.platform.commercefacades.converter.ConfigurablePopulator;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.variants.model.VariantProductModel;
import com.teamidea.platform.technonikol.facades.search.B2BProductSearchUtil;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;


/**
 * Default implementation of {@link B2BProductSearchUtil}.
 */
public class DefaultB2BProductSearchUtil<ITEM extends ProductData> implements B2BProductSearchUtil<ITEM>
{

    @Resource(name = "b2bProductFacade")
    private ProductFacade productFacade;

    @Resource(name = "productService")
    private ProductService productService;

    @Resource(name = "b2bProductConfiguredPopulator")
    private ConfigurablePopulator<ProductModel, ITEM, ProductOption> productConfiguredPopulator;

    @Override
    public void populateVariantProducts(final ProductSearchPageData<SearchStateData, ITEM> pageData)
    {
        if ((pageData != null) && (pageData.getResults() != null))
        {
            if (CollectionUtils.isNotEmpty(pageData.getResults()))
            {
                final Collection<ProductOption> optionsWithVariants = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE,
                        ProductOption.SUMMARY, ProductOption.DESCRIPTION, ProductOption.GALLERY, ProductOption.CATEGORIES,
                        ProductOption.REVIEW, ProductOption.PROMOTIONS, ProductOption.CLASSIFICATION, ProductOption.VARIANT_FULL,
                        ProductOption.STOCK, ProductOption.VOLUME_PRICES, ProductOption.PRICE_RANGE, ProductOption.VARIANT_MATRIX, ProductOption.BRAND);

                final Collection<ProductOption> optionsWithoutVariants = Arrays.asList(ProductOption.STOCK, ProductOption.BRAND);

                for (final ITEM productData : pageData.getResults())
                {

                    final ProductModel productModel = productService.getProductForCode(productData.getCode());
                    if (CollectionUtils.isNotEmpty(productModel.getVariants()))
                    {
                        // check if product has at least one generic variant
                        GenericVariantProductModel firstVariant = null;
                        for (final VariantProductModel variant : productModel.getVariants())
                        {
                            if (variant instanceof GenericVariantProductModel)
                            {
                                firstVariant = (GenericVariantProductModel) variant;
                                break;
                            }
                        }

                        if (firstVariant != null)
                        {
                            final ProductData firstVariantData = productFacade.getProductForOptions(firstVariant, null);
                            this.productConfiguredPopulator.populate(firstVariant, productData, optionsWithVariants);
                            // set url from first variant into base product, to enable links to product details and order form
                            productData.setUrl(firstVariantData.getUrl());
                        }
                    }
                    else
                    {
                        this.productConfiguredPopulator.populate(productModel, productData, optionsWithoutVariants);
                    }
                }
            }
        }
        else
        {
            throw new IllegalArgumentException("Cannot populate ProductSearchPageData with null value or null results.");
        }
    }

    public void setProductFacade(final ProductFacade productFacade)
    {
        this.productFacade = productFacade;
    }

    public void setProductService(final ProductService productService)
    {
        this.productService = productService;
    }

    public void setProductConfiguredPopulator(
            final ConfigurablePopulator<ProductModel, ITEM, ProductOption> productConfiguredPopulator)
    {
        this.productConfiguredPopulator = productConfiguredPopulator;
    }

}
