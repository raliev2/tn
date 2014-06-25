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
package com.teamidea.platform.technonikol.facades.flow.impl;

import de.hybris.platform.b2bacceleratorfacades.order.impl.DefaultB2BCheckoutFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.customer.CustomerEmailResolutionService;
import de.hybris.platform.commerceservices.storefinder.StoreFinderService;
import de.hybris.platform.commerceservices.storefinder.data.PointOfServiceDistanceData;
import de.hybris.platform.commerceservices.storefinder.data.StoreFinderSearchPageData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import org.springframework.beans.factory.annotation.Required;

import com.teamidea.platform.technonikol.core.checkout.flow.B2BCheckoutFlowStrategy;
import com.teamidea.platform.technonikol.core.checkout.pci.B2BCheckoutPciStrategy;
import com.teamidea.platform.technonikol.core.enums.B2BCheckoutFlowEnum;
import com.teamidea.platform.technonikol.core.enums.B2BCheckoutPciOptionEnum;
import com.teamidea.platform.technonikol.core.enums.TNDeliveryMethodTypeEnum;
import com.teamidea.platform.technonikol.core.enums.TNDeliveryModeTypeEnum;
import com.teamidea.platform.technonikol.core.enums.TNPaymentMethodTypeEnum;
import com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade;


/**
 * Default implementation of the {@link B2BCheckoutFlowFacade}. Delegates resolving the checkout flow to an injected
 * {@link B2BCheckoutFlowStrategy}.
 * 
 * @since 4.6
 * @spring.bean checkoutFlowFacade
 */
public class DefaultB2BCheckoutFlowFacade extends DefaultB2BCheckoutFacade implements B2BCheckoutFlowFacade
{
	private B2BCheckoutFlowStrategy checkoutFlowStrategy;
	private B2BCheckoutPciStrategy b2BCheckoutPciStrategy;
	private StoreFinderService<PointOfServiceDistanceData, StoreFinderSearchPageData<PointOfServiceDistanceData>> storeFinderService;
	private CustomerEmailResolutionService customerEmailResolutionService;

	@Override
	public B2BCheckoutFlowEnum getCheckoutFlow()
	{
		return getCheckoutFlowStrategy().getCheckoutFlow();
	}

	@Override
	public B2BCheckoutPciOptionEnum getSubscriptionPciOption()
	{
		return getCheckoutPciStrategy().getSubscriptionPciOption();
	}

	protected B2BCheckoutFlowStrategy getCheckoutFlowStrategy()
	{
		return checkoutFlowStrategy;
	}

	@Required
	public void setCheckoutFlowStrategy(final B2BCheckoutFlowStrategy strategy)
	{
		this.checkoutFlowStrategy = strategy;
	}

	protected B2BCheckoutPciStrategy getCheckoutPciStrategy()
	{
		return this.b2BCheckoutPciStrategy;
	}

	@Required
	public void setCheckoutPciStrategy(final B2BCheckoutPciStrategy strategy)
	{
		this.b2BCheckoutPciStrategy = strategy;
	}


	/**
	 * @return the customerEmailResolutionService
	 */
	public CustomerEmailResolutionService getCustomerEmailResolutionService()
	{
		return customerEmailResolutionService;
	}

	/**
	 * @param customerEmailResolutionService
	 *           the customerEmailResolutionService to set
	 */
	public void setCustomerEmailResolutionService(final CustomerEmailResolutionService customerEmailResolutionService)
	{
		this.customerEmailResolutionService = customerEmailResolutionService;
	}

	/**
	 * @return the storeFinderService
	 */
	public StoreFinderService<PointOfServiceDistanceData, StoreFinderSearchPageData<PointOfServiceDistanceData>> getStoreFinderService()
	{
		return storeFinderService;
	}

	/**
	 * @param storeFinderService
	 *           the storeFinderService to set
	 */
	public void setStoreFinderService(
			final StoreFinderService<PointOfServiceDistanceData, StoreFinderSearchPageData<PointOfServiceDistanceData>> storeFinderService)
	{
		this.storeFinderService = storeFinderService;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade#setDeliveryMethod(com.teamidea.platform.
	 * technonikol.core.enums.TNDeliveryMethodTypeEnum)
	 */
	@Override
	public void setDeliveryMethod(final TNDeliveryMethodTypeEnum deliveryMethod)
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			if (deliveryMethod != null)
			{
				ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
				cartModel.setDeliveryMethod(deliveryMethod);
				getModelService().save(cartModel);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade#setPaymentMethod(com.teamidea.platform.
	 * technonikol.core.enums.TNPaymentMethodTypeEnum)
	 */
	@Override
	public void setPaymentMethod(final TNPaymentMethodTypeEnum paymentMethod)
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			if (paymentMethod != null)
			{
				ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
				cartModel.setPaymentMethod(paymentMethod);
				getModelService().save(cartModel);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade#setPaymentMethod(com.teamidea.platform.
	 * technonikol.core.enums.TNPaymentMethodTypeEnum)
	 */
	@Override
	public void setDeliveryMode(final TNDeliveryModeTypeEnum deliveryMode)
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			if (deliveryMode != null)
			{
				ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
				cartModel.setDeliveryGroupMode(deliveryMode);
				getModelService().save(cartModel);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade#setProvidedDeliveryDate(java.lang.String)
	 */
	@Override
	public void setProvidedDeliveryDate(final String providedDeliveryDate)
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			if (providedDeliveryDate != null)
			{
				ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
				cartModel.setProvidedDeliveryDate(providedDeliveryDate);
				getModelService().save(cartModel);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade#setProvidedDescription(java.lang.String)
	 */
	@Override
	public void setProvidedDescription(final String providedDescription)
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			if (providedDescription != null)
			{
				ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
				cartModel.setProvidedDescription(providedDescription);
				getModelService().save(cartModel);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade#setEmailNotification(java.lang.Boolean)
	 */
	@Override
	public void setEmailNotification(final Boolean emailNotification)
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			if (emailNotification != null)
			{
				ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
				cartModel.setEmailNotification(emailNotification);
				getModelService().save(cartModel);
			}
		}
	}


	@Override
	public boolean setDeliveryAddress(final AddressData addressData)
	{
		ServicesUtil.validateParameterNotNull(addressData, "Parameter 'addressData' is null!");

		final CartModel cartModel = getCart();
		clearPointOfService(cartModel);

		if (cartModel != null)
		{
			AddressModel addressModel = null;
			if (addressData.getId() != null)
			{
				addressModel = getDeliveryAddressModelForCode(addressData.getId());
			}
			else
			{
				addressModel = createDeliveryAddressModel(addressData, cartModel);
			}
			return saveDeliveryAddress(cartModel, addressModel);
		}
		return false;
	}


	public boolean saveDeliveryAddress(final CartModel cartModel, final AddressModel addressModel)
	{
		ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
		ServicesUtil.validateParameterNotNull(addressModel, "Address model cannot be null");

		if (addressModel.getOwner() != null && addressModel.getOwner() instanceof CartModel)
		{
			addressModel.setOwner(cartModel.getUser());
		}

		getModelService().save(addressModel);
		cartModel.setDeliveryAddress(addressModel);
		getModelService().save(cartModel);

		return true;
	}

	public void clearDeliveryAddress(final CartModel cartModel)
	{
		cartModel.setDeliveryAddress(null);
		getModelService().save(cartModel);
	}

	public void clearPointOfService(final CartModel cartModel)
	{
		for (final AbstractOrderEntryModel entry : cartModel.getEntries())
		{
			entry.setDeliveryPointOfService(null);
		}
		getModelService().save(cartModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade#setDeliveryPointOfService(de.hybris.platform
	 * .commercefacades.storelocator.data.PointOfServiceData)
	 */
	@Override
	public void setDeliveryPointOfService(final String store)
	{
		ServicesUtil.validateParameterNotNull(store, "Parameter 'store' is null!");
		final CartModel cartModel = getCart();

		clearDeliveryAddress(cartModel);

		final BaseStoreModel currentBaseStore = getBaseStoreService().getCurrentBaseStore();
		final PointOfServiceModel pointOfService = getStoreFinderService().getPointOfServiceForName(currentBaseStore, store);

		if (cartModel != null)
		{
			for (final AbstractOrderEntryModel entry : cartModel.getEntries())
			{
				entry.setDeliveryPointOfService(pointOfService);
			}
		}
		getModelService().save(cartModel);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade#getEmailForCustomer(de.hybris.platform.
	 * commercefacades.user.data.CustomerData)
	 */
	@Override
	public String getEmailForCustomer()
	{
		return getCustomerEmailResolutionService().getEmailForCustomer(getCurrentUserForCheckout());
	}


	public void afterScheduleOrder()
	{
		//clean previous card because it can be restored after next login
		getCartFacade().cleanSavedCart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade#setGeneratedNumber()
	 */
	@Override
	public void setGeneratedNumber()
	{
		final CartModel cartModel = getCart();
		String generatedCode = cartModel.getCode();
		while (generatedCode.length() < 12)
		{
			generatedCode = "0" + generatedCode;
		}
		cartModel.setGeneratedNumber("R-" + generatedCode);
		getModelService().save(cartModel);
	}
}
