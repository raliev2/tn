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
package com.teamidea.platform.technonikol.facades.flow;

import de.hybris.platform.b2bacceleratorfacades.order.B2BCheckoutFacade;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;

import com.teamidea.platform.technonikol.core.enums.B2BCheckoutFlowEnum;
import com.teamidea.platform.technonikol.core.enums.B2BCheckoutPciOptionEnum;
import com.teamidea.platform.technonikol.core.enums.TNDeliveryMethodTypeEnum;
import com.teamidea.platform.technonikol.core.enums.TNDeliveryModeTypeEnum;
import com.teamidea.platform.technonikol.core.enums.TNPaymentMethodTypeEnum;


/**
 * CheckoutFlowFacade interface extends the {@link CheckoutFacade}. The CheckoutFlowFacade supports resolving the
 * {@link B2BCheckoutFlowEnum} for the current request.
 * 
 * @since 4.6
 * @spring.bean checkoutFacade
 */
public interface B2BCheckoutFlowFacade extends B2BCheckoutFacade
{
	B2BCheckoutFlowEnum getCheckoutFlow();

	B2BCheckoutPciOptionEnum getSubscriptionPciOption();

	void setDeliveryMethod(TNDeliveryMethodTypeEnum deliveryMethod);

	void setPaymentMethod(TNPaymentMethodTypeEnum paymentMethod);

	void setProvidedDeliveryDate(String providedDeliveryDate);

	void setProvidedDescription(String providedDescription);

	void setEmailNotification(Boolean emailNotification);

	void setDeliveryMode(TNDeliveryModeTypeEnum deliveryMode);

	@Override
	boolean setDeliveryAddress(AddressData addressData);

	void setDeliveryPointOfService(String selectedStore);

	String getEmailForCustomer();

	void afterScheduleOrder();
}
