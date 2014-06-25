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

import de.hybris.platform.commercefacades.product.converters.populator.AbstractProductPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

import org.springframework.beans.factory.annotation.Required;

import com.teamidea.platform.technonikol.core.model.TNBrandModel;
import com.teamidea.platform.technonikol.facades.brand.data.TNBrandData;


/**
 * Populate the product data with the product's brand
 */
public class TNSearchBrandPopulator<SOURCE extends ProductModel, TARGET extends ProductData> extends
		AbstractProductPopulator<SOURCE, TARGET>
{
	private ModelService modelService;

	private Converter<TNBrandModel, TNBrandData> tnBrandConverter;

	@Override
	public void populate(final SOURCE productModel, final TARGET productData) throws ConversionException
	{

		final TNBrandModel brandModel = productModel.getBrand();
		if (brandModel != null)
		{
			final TNBrandData tnBrandData = tnBrandConverter.convert(brandModel);
			productData.setBrand(tnBrandData);
		}
	}

	@Override
	public ModelService getModelService()
	{
		return modelService;
	}

	@Override
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public Converter<TNBrandModel, TNBrandData> getTnBrandConverter()
	{
		return tnBrandConverter;
	}

	@Required
	public void setTnBrandConverter(final Converter<TNBrandModel, TNBrandData> tnBrandConverter)
	{
		this.tnBrandConverter = tnBrandConverter;
	}
}
