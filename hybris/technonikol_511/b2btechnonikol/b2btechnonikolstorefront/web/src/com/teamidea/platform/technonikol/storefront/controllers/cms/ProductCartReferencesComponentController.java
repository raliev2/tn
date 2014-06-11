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
package com.teamidea.platform.technonikol.storefront.controllers.cms;

import de.hybris.platform.acceleratorcms.model.components.ProductReferencesComponentModel;
import de.hybris.platform.b2bacceleratorfacades.order.B2BCartFacade;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductReferenceData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamidea.platform.technonikol.storefront.controllers.ControllerConstants;


/**
 * Controller for CMS ProductReferencesComponent.
 */
@Controller("ProductCartReferencesComponentController")
@Scope("tenant")
@RequestMapping(value = ControllerConstants.Actions.Cms.ProductCartReferencesComponent)
public class ProductCartReferencesComponentController extends AbstractCMSComponentController<ProductReferencesComponentModel>
{
	protected static final List<ProductOption> PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE);

	@Resource(name = "b2bCartFacade")
	private B2BCartFacade cartFacade;

	@Resource(name = "b2bProductFacade")
	private ProductFacade productFacade;


	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final ProductReferencesComponentModel component)
	{

		final Set<ProductReferenceData> productReferences = new HashSet<ProductReferenceData>();
		final List<OrderEntryData> entries = cartFacade.getSessionCart().getEntries();
		for (final OrderEntryData entry : entries)
		{
			final List<ProductReferenceData> oneOfproductReferences = productFacade.getProductReferencesForCode(entry.getProduct()
					.getCode(), component.getProductReferenceTypes(), PRODUCT_OPTIONS, component.getMaximumNumberProducts());
			productReferences.addAll(oneOfproductReferences);
		}

		final List<ProductReferenceData> productReferences2 = new ArrayList<ProductReferenceData>();
		productReferences2.addAll(productReferences);

		model.addAttribute("title", component.getTitle());
		model.addAttribute("productReferences", productReferences2);

	}
}
