/**
 * 
 */
package com.teamidea.platform.technonikol.storefront.controllers.util;

import com.teamidea.platform.technonikol.storefront.controllers.ControllerConstants;


/**
 * @author Marina
 * 
 */
public enum CheckoutStep
{
	//TODO localization
	DELIVERY_METHOD("Выбор способа доставки", ControllerConstants.Actions.Checkout.SELECT_DELIVERY_METHOD_URL,
			ControllerConstants.Views.Pages.MultiStepCheckout.ChooseDeliveryMethodPage), DELIVERY_ADDRESS("Адрес доставки",
			ControllerConstants.Actions.Checkout.SELECT_DELIVERY_ADDRESS_URL,
			ControllerConstants.Views.Pages.MultiStepCheckout.AddSelectDeliveryAddressPage), ADDRESS_MAP("Адрес доставки",
			ControllerConstants.Actions.Checkout.SHOW_DELIVERY_ADDRESS_MAP_URL,
			ControllerConstants.Views.Pages.MultiStepCheckout.DeliveryAddressMapPage), DELIVERY_MODE("Способ доставки",
			ControllerConstants.Actions.Checkout.SELECT_DELIVERY_MODE_URL,
			ControllerConstants.Views.Pages.MultiStepCheckout.ChooseDeliveryModePage), PAYMENT_METHOD("Способ оплаты",
			ControllerConstants.Actions.Checkout.SELECT_PAYMENT_METHOD_URL,
			ControllerConstants.Views.Pages.MultiStepCheckout.ChoosePaymentMethodPage), CHECKOUT_SUMMARY("Проверка заказа",
			ControllerConstants.Actions.Checkout.SHOW_CHECKOUT_SUMMARY_URL,
			ControllerConstants.Views.Pages.MultiStepCheckout.CheckoutSummaryPage), HOSTED_ORDER_ERROR("Готово",
			ControllerConstants.Actions.Checkout.SHOW_HOSTED_ORDER_ERROR_URL,
			ControllerConstants.Views.Pages.MultiStepCheckout.HostedOrderErrorPage), HOSTED_ORDER_SUCCESS("Ваш заказ принят",
			ControllerConstants.Actions.Checkout.SHOW_HOSTED_ORDER_SUCCESS_URL,
			ControllerConstants.Views.Pages.MultiStepCheckout.HostedOrderPostPage), ;

	private final String name;
	private final String url;
	private final String view;

	private CheckoutStep(final String name, final String url, final String view)
	{
		this.name = name;
		this.url = url;
		this.view = view;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}


	/**
	 * @return the url
	 */
	public String getUrl()
	{
		return url;
	}


	/**
	 * @return the view
	 */
	public String getView()
	{
		return view;
	}

	public CheckoutStep getNext()
	{
		return this.ordinal() < CheckoutStep.values().length - 1 ? CheckoutStep.values()[this.ordinal() + 1] : null;
	}

	public CheckoutStep getPrevious()
	{
		return this.ordinal() == 0 ? null : CheckoutStep.values()[this.ordinal() - 1];
	}

}