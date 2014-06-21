/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */

package com.teamidea.platform.technonikol.facades.search.converters.populator;


import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultProductPopulator;
import de.hybris.platform.commerceservices.price.CommercePriceService;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.product.PriceService;
import de.hybris.platform.product.ProductService;

import javax.annotation.Resource;
import java.math.BigDecimal;


/**
 * Converter implementation for {@link de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData} as
 * source and {@link de.hybris.platform.commercefacades.product.data.ProductData} as target type. Adds all the
 * information related to multivariant products.
 */
public class VariantSearchResultProductPopulator extends SearchResultProductPopulator
{
    @Resource(name = "priceDataFactory")
    private PriceDataFactory priceDataFactory;

    @Resource(name = "commercePriceService")
    private CommercePriceService commercePriceService;

    @Resource(name = "priceService")
    private PriceService priceService;

    @Resource(name = "productService")
    private ProductService productService;

    @Override
	public void populate(final SearchResultValueData source, final ProductData target)
	{
		super.populate(source, target);
        final PriceInformation info;
        final ProductModel productModel = productService.getProductForCode(source.getValues().get("code").toString());
        info = commercePriceService.getWebPriceForProduct(productModel);
        if (info != null)
        {
               PriceData price = priceDataFactory.create(PriceDataType.BUY, BigDecimal.valueOf(info.getPriceValue().getValue()), info.getPriceValue().getCurrencyIso());
               target.setPrice(price);
        }
		target.setMultidimensional(this.<Boolean> getValue(source, "multidimensional"));
	}
}
