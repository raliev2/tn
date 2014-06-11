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
package com.teamidea.platform.technonikol.storefront.controllers.pages.checkout;

import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.user.data.AddressData;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade;
import com.teamidea.platform.technonikol.storefront.controllers.pages.AbstractPageController;


/**
 * Base controller for all checkout page controllers. Provides common functionality for all checkout page controllers.
 */
public abstract class AbstractCheckoutController extends AbstractPageController
{
	@Resource(name = "b2bCheckoutFlowFacade")
	private B2BCheckoutFlowFacade checkoutFlowFacade;

	@Resource(name = "i18NFacade")
	private I18NFacade i18NFacade;

	protected B2BCheckoutFlowFacade getCheckoutFlowFacade()
	{
		return checkoutFlowFacade;
	}

	protected I18NFacade getI18NFacade()
	{
		return i18NFacade;
	}

	/**
	 * Checks if there are any items in the cart.
	 * 
	 * @return returns true if items found in cart.
	 */
	protected boolean hasValidCart()
	{
		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();
		final boolean validCart = cartData.getEntries() != null && !cartData.getEntries().isEmpty();

		return validCart;
	}

	/**
	 * @return True if cart is null or does not have deliveryAddress and if order has shipping items otherwise returns
	 *         false
	 */
	protected boolean hasNoDeliveryAddress()
	{
		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();
		return getCheckoutFlowFacade().hasShippingItems() && (cartData == null || cartData.getDeliveryAddress() == null);
	}

	/**
	 * @return True if cart is null or does not have deliveryMode and if order has shipping items otherwise returns false
	 */
	protected boolean hasNoDeliveryMode()
	{
		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();
		return getCheckoutFlowFacade().hasShippingItems() && (cartData == null || cartData.getDeliveryMode() == null);
	}

	protected List<? extends AddressData> getDeliveryAddresses(final AddressData selectedAddressData)
	{
		List<AddressData> deliveryAddresses = null;
		if (selectedAddressData != null)
		{
			deliveryAddresses = (List<AddressData>) getCheckoutFlowFacade().getSupportedDeliveryAddresses(true);

			if (deliveryAddresses == null || deliveryAddresses.isEmpty())
			{
				deliveryAddresses = Collections.singletonList(selectedAddressData);
			}
			else if (!isAddressOnList(deliveryAddresses, selectedAddressData))
			{
				deliveryAddresses.add(selectedAddressData);
			}
		}

		return deliveryAddresses == null ? Collections.<AddressData> emptyList() : deliveryAddresses;
	}

	protected boolean isAddressOnList(final List<AddressData> deliveryAddresses, final AddressData selectedAddressData)
	{
		if (deliveryAddresses == null || selectedAddressData == null)
		{
			return false;
		}

		for (final AddressData address : deliveryAddresses)
		{
			if (address.getId().equals(selectedAddressData.getId()))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks if there are any items in the cart.
	 * 
	 * @return returns true if items found in cart.
	 */
	protected boolean hasItemsInCart()
	{
		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();

		return (cartData.getEntries() != null && !cartData.getEntries().isEmpty());
	}
}
