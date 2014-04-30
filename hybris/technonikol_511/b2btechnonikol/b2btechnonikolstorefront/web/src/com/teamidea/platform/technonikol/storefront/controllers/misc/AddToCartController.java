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
package com.teamidea.platform.technonikol.storefront.controllers.misc;

import de.hybris.platform.b2bacceleratorfacades.product.data.ProductQuantityData;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.util.Config;
import com.teamidea.platform.technonikol.storefront.controllers.AbstractController;
import com.teamidea.platform.technonikol.storefront.controllers.ControllerConstants;
import com.teamidea.platform.technonikol.storefront.controllers.util.GlobalMessages;
import com.teamidea.platform.technonikol.storefront.forms.AddToCartForm;
import com.teamidea.platform.technonikol.storefront.forms.AddToCartOrderForm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Controller for Add to Cart functionality which is not specific to a certain page.
 */
@Controller
@Scope("tenant")
public class AddToCartController extends AbstractController
{
	private static final String TYPE_MISMATCH_ERROR_CODE = "typeMismatch";
	private static final String ERROR_MSG_TYPE = "errorMsg";
	private static final String QUANTITY_INVALID_BINDING_MESSAGE_KEY = "basket.error.quantity.invalid.binding";
	private static final String BASKET_QUANTITY_ERROR_KEY = "basket.error.quantity.invalid";
	private static final String BASKET_QUANTITY_MULTIPLE_ERROR_KEY = "basket.error.quantity.multiple.invalid";
	private static final String BASKET_QUANTITY_NOITEMADDED_ERROR_PREFIX_KEY = "basket.information.quantity.noItemsAdded.";
	private static final String BASKET_QUANTITY_REDUCED_NUMBER_PREFIX_KEY = "basket.information.quantity.reducedNumberOfItemsAdded.";

	protected static final Logger LOG = Logger.getLogger(AddToCartController.class);
	private static final Integer MINIMUM_SINGLE_SKU_ADD_CART = 0;
	private static final String SHOWN_PRODUCT_COUNT = "storefront.minicart.shownProductCount";

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	@InitBinder
	public void initBinder(final WebDataBinder binder)
	{
		binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
	}

	@RequestMapping(value = "/cart/add", method = RequestMethod.POST, produces = "application/json")
	public String addToCart(@RequestParam("productCodePost") final String code, final Model model,
			@Valid final AddToCartForm form, final BindingResult bindingErrors)
	{
		ProductData product = null;

		if (bindingErrors.hasErrors())
		{
			return getViewWithBindingErrorMessages(model, bindingErrors);
		}

		final ProductQuantityData productQuantity = new ProductQuantityData();
		productQuantity.setQuantity(form.getQty());
		productQuantity.setSku(code);

		if (productQuantity.getQuantity() > MINIMUM_SINGLE_SKU_ADD_CART)
		{
			product = addToCart(model, productQuantity);
		}
		else
		{
			model.addAttribute(ERROR_MSG_TYPE, BASKET_QUANTITY_ERROR_KEY);
		}


		final List<ProductData> productDataList = new ArrayList<ProductData>();

		if (product != null)
		{
			productDataList.add(product);
		}

		model.addAttribute("products", productDataList);


		return ControllerConstants.Views.Fragments.Cart.AddToCartPopup;
	}


	@RequestMapping(value = "/cart/addGrid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public final String addGridToCart(@RequestBody final AddToCartOrderForm form, final Model model)
	{
		addToCart(model, form.getProductQuantities());

		return ControllerConstants.Views.Fragments.Cart.AddToCartPopup;
	}


	protected ProductData addToCart(final Model model, final ProductQuantityData productQuantity)
	{
		ProductData product = null;

		try
		{
			final CartModificationData cartModification = cartFacade.addToCart(productQuantity.getSku(),
					productQuantity.getQuantity());

			product = cartModification.getEntry().getProduct();
			model.addAttribute("entry", cartModification.getEntry());
			model.addAttribute("numberShowing", Config.getInt(SHOWN_PRODUCT_COUNT, 3));

			if (cartModification.getQuantityAdded() <= 0L)
			{
				if (!model.containsAttribute(ERROR_MSG_TYPE))
				{
					final String msg = BASKET_QUANTITY_NOITEMADDED_ERROR_PREFIX_KEY + cartModification.getStatusCode();
					model.addAttribute(ERROR_MSG_TYPE, msg);
					GlobalMessages.addMessage(model, GlobalMessages.ERROR_MESSAGES_HOLDER, msg, new Object[]
					{ product.getName() });
				}
			}
			else if (cartModification.getQuantityAdded() < productQuantity.getQuantity())
			{
				if (!model.containsAttribute(ERROR_MSG_TYPE))
				{
					final String msg = BASKET_QUANTITY_REDUCED_NUMBER_PREFIX_KEY + cartModification.getStatusCode();
					model.addAttribute(ERROR_MSG_TYPE, msg);
					GlobalMessages.addMessage(model, GlobalMessages.ERROR_MESSAGES_HOLDER, msg, new Object[]
					{ product.getName() });
				}

			}
		}
		catch (final CommerceCartModificationException ex)
		{
			GlobalMessages.addMessage(model, GlobalMessages.ERROR_MESSAGES_HOLDER, "basket.error.occurred", null);
		}

		return product;

	}

	protected void addToCart(final Model model, final List<ProductQuantityData> productQuantities)
	{
		int index = 0;
		final List<ProductData> productDataList = new ArrayList<ProductData>();

		for (final ProductQuantityData productQuantity : productQuantities)
		{
			if (productQuantity.getQuantity() != null)
			{
				// Ignore all skus with 
				if (productQuantity.getQuantity() > MINIMUM_SINGLE_SKU_ADD_CART)
				{
					final ProductData product = addToCart(model, productQuantity);
					if (product != null)
					{
						productDataList.add(product);
					}
				}
				else if (productQuantity.getQuantity() < MINIMUM_SINGLE_SKU_ADD_CART)
				{
					model.addAttribute(ERROR_MSG_TYPE, BASKET_QUANTITY_MULTIPLE_ERROR_KEY);
					GlobalMessages.addMessage(model, GlobalMessages.ERROR_MESSAGES_HOLDER, BASKET_QUANTITY_MULTIPLE_ERROR_KEY, null);
				}

			}

			if (productQuantity.getQuantity() == null || productQuantity.getQuantity() == MINIMUM_SINGLE_SKU_ADD_CART)
			{
				index++;
			}

		}

		if (index == productQuantities.size())
		{
			model.addAttribute(ERROR_MSG_TYPE, BASKET_QUANTITY_ERROR_KEY);
			GlobalMessages.addMessage(model, GlobalMessages.ERROR_MESSAGES_HOLDER, BASKET_QUANTITY_ERROR_KEY, null);
		}

		model.addAttribute("products", productDataList);
	}

	protected String getViewWithBindingErrorMessages(final Model model, final BindingResult bindingErrors)
	{
		for (final ObjectError error : bindingErrors.getAllErrors())
		{
			if (isTypeMismatchError(error))
			{
				model.addAttribute(ERROR_MSG_TYPE, QUANTITY_INVALID_BINDING_MESSAGE_KEY);
			}
			else
			{
				model.addAttribute(ERROR_MSG_TYPE, error.getDefaultMessage());
			}
		}
		return ControllerConstants.Views.Fragments.Cart.AddToCartPopup;
	}


	protected boolean isTypeMismatchError(final ObjectError error)
	{
		return error.getCode().equals(TYPE_MISMATCH_ERROR_CODE);
	}
}
