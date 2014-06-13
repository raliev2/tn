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

import de.hybris.platform.b2b.model.B2BCostCenterModel;
import de.hybris.platform.b2bacceleratorfacades.order.data.B2BCostCenterData;
import de.hybris.platform.b2bacceleratorfacades.order.impl.DefaultB2BCheckoutFacade;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import com.teamidea.platform.technonikol.core.checkout.flow.B2BCheckoutFlowStrategy;
import com.teamidea.platform.technonikol.core.checkout.pci.B2BCheckoutPciStrategy;
import com.teamidea.platform.technonikol.core.enums.B2BCheckoutFlowEnum;
import com.teamidea.platform.technonikol.core.enums.B2BCheckoutPciOptionEnum;
import com.teamidea.platform.technonikol.core.enums.TNDeliveryMethodTypeEnum;
import com.teamidea.platform.technonikol.core.enums.TNPaymentMethodTypeEnum;
import com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade;

import org.springframework.beans.factory.annotation.Required;


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

	/* (non-Javadoc)
	 * @see com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade#setDeliveryMethod(com.teamidea.platform.technonikol.core.enums.TNDeliveryMethodTypeEnum)
	 */
	@Override
	public void setDeliveryMethod(TNDeliveryMethodTypeEnum deliveryMethod)
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			B2BCostCenterModel costCenterModel = null;
			if (deliveryMethod != null)
			{		
				ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
				cartModel.setDeliveryMethod(deliveryMethod);
				getModelService().save(cartModel);
			}
		}	
	}

	/* (non-Javadoc)
	 * @see com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade#setPaymentMethod(com.teamidea.platform.technonikol.core.enums.TNPaymentMethodTypeEnum)
	 */
	@Override
	public void setPaymentMethod(TNPaymentMethodTypeEnum paymentMethod)
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			B2BCostCenterModel costCenterModel = null;
			if (paymentMethod != null)
			{		
				ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
				cartModel.setPaymentMethod(paymentMethod);
				getModelService().save(cartModel);
			}
		}			
	}

	/* (non-Javadoc)
	 * @see com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade#setProvidedDeliveryDate(java.lang.String)
	 */
	@Override
	public void setProvidedDeliveryDate(String providedDeliveryDate)
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			B2BCostCenterModel costCenterModel = null;
			if (providedDeliveryDate != null)
			{		
				ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
				cartModel.setProvidedDeliveryDate(providedDeliveryDate);
				getModelService().save(cartModel);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade#setProvidedDescription(java.lang.String)
	 */
	@Override
	public void setProvidedDescription(String providedDescription)
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			B2BCostCenterModel costCenterModel = null;
			if (providedDescription != null)
			{		
				ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
				cartModel.setProvidedDescription(providedDescription);
				getModelService().save(cartModel);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.teamidea.platform.technonikol.facades.flow.B2BCheckoutFlowFacade#setEmailNotification(java.lang.Boolean)
	 */
	@Override
	public void setEmailNotification(Boolean emailNotification)
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			B2BCostCenterModel costCenterModel = null;
			if (emailNotification != null)
			{		
				ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
				cartModel.setEmailNotification(emailNotification);
				getModelService().save(cartModel);
			}
		}
	}
}
