package com.teamidea.platform.technonikol.storefront.controllers.pages.checkout;

import de.hybris.platform.b2bacceleratorfacades.company.B2BCommerceCostCenterFacade;
import de.hybris.platform.b2bacceleratorfacades.order.data.B2BCostCenterData;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.delivery.DeliveryService;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teamidea.platform.technonikol.core.enums.TNDeliveryMethodTypeEnum;
import com.teamidea.platform.technonikol.core.enums.TNPaymentMethodTypeEnum;
import com.teamidea.platform.technonikol.storefront.annotations.RequireHardLogIn;
import com.teamidea.platform.technonikol.storefront.controllers.ControllerConstants;
import com.teamidea.platform.technonikol.storefront.controllers.util.CheckoutStep;
import com.teamidea.platform.technonikol.storefront.controllers.util.DeliveryMethod;
import com.teamidea.platform.technonikol.storefront.controllers.util.GlobalMessages;
import com.teamidea.platform.technonikol.storefront.controllers.util.PaymentMethod;
import com.teamidea.platform.technonikol.storefront.forms.CheckoutAddressForm;
import com.teamidea.platform.technonikol.storefront.security.B2BUserGroupProvider;


/**
 * MultiStepCheckoutController
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/checkout/multi")
public class MultiStepCheckoutController extends AbstractCheckoutController
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(MultiStepCheckoutController.class);

	private static final String MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL = "multiStepCheckout";
	private static final String REDIRECT_URL_CART = REDIRECT_PREFIX + "/cart";

	@Resource(name = "b2bUserGroupProvider")
	private B2BUserGroupProvider b2bUserGroupProvider;

	@Resource(name = "b2bProductFacade")
	private ProductFacade productFacade;

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	@Resource(name = "b2bCommerceCostCenterFacade")
	private B2BCommerceCostCenterFacade costCenterFacade;

	@Resource(name = "defaultDeliveryService")
	private DeliveryService deliveryService;

	@Resource(name = "deliveryModeConverter")
	private Converter<DeliveryModeModel, DeliveryModeData> deliveryModeConverter;

	private static final CheckoutStep DELIVERY_METHOD;
	private static final CheckoutStep SELECT_ADDRESS;
	private static final CheckoutStep SELECT_DELIVERY_ADDRESS;
	private static final CheckoutStep ADDRESS_MAP;
	private static final CheckoutStep DELIVERY_MODE;
	private static final CheckoutStep PAYMENT_METHOD;
	private static final CheckoutStep CHECKOUT_SUMMARY;
	private static final CheckoutStep HOSTED_ORDER;
	private static final CheckoutStep HOSTED_ORDER_ERROR;
	private static final CheckoutStep HOSTED_ORDER_SUCCESS;

	private CheckoutStep currentStep = DELIVERY_METHOD;

	private static final List<DeliveryMethod> deliveryMethods = new ArrayList<DeliveryMethod>();
	private static final List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();

	static
	{
		deliveryMethods.add(new DeliveryMethod(TNDeliveryMethodTypeEnum.DELIVERY));
		deliveryMethods.add(new DeliveryMethod(TNDeliveryMethodTypeEnum.PICKUP));
		paymentMethods.add(new PaymentMethod(TNPaymentMethodTypeEnum.DELAY));
		paymentMethods.add(new PaymentMethod(TNPaymentMethodTypeEnum.PREPAYMENT));

		DELIVERY_METHOD = new CheckoutStep("checkout.step.delivery.method",
				ControllerConstants.Actions.Checkout.SELECT_DELIVERY_METHOD_URL,
				ControllerConstants.Views.Pages.MultiStepCheckout.ChooseDeliveryMethodPage);
		SELECT_ADDRESS = new CheckoutStep(null, ControllerConstants.Actions.Checkout.SELECT_ADDRESS_URL, null, DELIVERY_METHOD);
		SELECT_DELIVERY_ADDRESS = new CheckoutStep("checkout.step.delivery.address",
				ControllerConstants.Actions.Checkout.SELECT_DELIVERY_ADDRESS_URL,
				ControllerConstants.Views.Pages.MultiStepCheckout.AddSelectDeliveryAddressPage, DELIVERY_METHOD);
		ADDRESS_MAP = new CheckoutStep("checkout.step.delivery.address.map",
				ControllerConstants.Actions.Checkout.SHOW_DELIVERY_ADDRESS_MAP_URL,
				ControllerConstants.Views.Pages.MultiStepCheckout.DeliveryAddressMapPage, DELIVERY_METHOD);
		DELIVERY_MODE = new CheckoutStep("checkout.step.delivery.mode",
				ControllerConstants.Actions.Checkout.SELECT_DELIVERY_MODE_URL,
				ControllerConstants.Views.Pages.MultiStepCheckout.ChooseDeliveryModePage, ADDRESS_MAP);
		PAYMENT_METHOD = new CheckoutStep("checkout.step.payment.method",
				ControllerConstants.Actions.Checkout.SELECT_PAYMENT_METHOD_URL,
				ControllerConstants.Views.Pages.MultiStepCheckout.ChoosePaymentMethodPage, DELIVERY_MODE);
		CHECKOUT_SUMMARY = new CheckoutStep("checkout.step.summary",
				ControllerConstants.Actions.Checkout.SHOW_CHECKOUT_SUMMARY_URL,
				ControllerConstants.Views.Pages.MultiStepCheckout.CheckoutSummaryPage, PAYMENT_METHOD);
		HOSTED_ORDER = new CheckoutStep(null, ControllerConstants.Actions.Checkout.SHOW_HOSTED_ORDER_URL, null);
		HOSTED_ORDER_ERROR = new CheckoutStep("checkout.step.hosted.order.error",
				ControllerConstants.Actions.Checkout.SHOW_HOSTED_ORDER_ERROR_URL,
				ControllerConstants.Views.Pages.MultiStepCheckout.HostedOrderErrorPage, CHECKOUT_SUMMARY);
		HOSTED_ORDER_SUCCESS = new CheckoutStep("checkout.step.hosted.order.success",
				ControllerConstants.Actions.Checkout.SHOW_HOSTED_ORDER_SUCCESS_URL,
				ControllerConstants.Views.Pages.MultiStepCheckout.HostedOrderPostPage, CHECKOUT_SUMMARY);

		DELIVERY_METHOD.setNext(SELECT_ADDRESS);
		SELECT_DELIVERY_ADDRESS.setNext(DELIVERY_MODE);
		ADDRESS_MAP.setNext(DELIVERY_MODE);
		DELIVERY_MODE.setNext(PAYMENT_METHOD);
		PAYMENT_METHOD.setNext(CHECKOUT_SUMMARY);
		CHECKOUT_SUMMARY.setNext(HOSTED_ORDER);
	}

	@ModelAttribute("deliveryMethods")
	public List<DeliveryMethod> getDeliveryMethods()
	{
		return deliveryMethods;
	}

	@ModelAttribute("paymentMethods")
	public List<PaymentMethod> getPaymentMethods()
	{
		return paymentMethods;
	}

	@ModelAttribute("deliveryModes")
	public List<? extends DeliveryModeData> getDeliveryModes()
	{
		return getCheckoutFlowFacade().getSupportedDeliveryModes();
	}

	@ModelAttribute("costCenters")
	public List<B2BCostCenterData> getCostCenters()
	{
		return getCheckoutFlowFacade().getActiveVisibleCostCenters();
	}

	@ModelAttribute("deliveryAddresses")
	public List<? extends AddressData> getDeliveryAddresses()
	{
		return getCheckoutFlowFacade().getSupportedDeliveryAddresses(true);
	}

	@RequestMapping(method = RequestMethod.GET)
	@RequireHardLogIn
	public String gotoFirstStep(final Model model)
	{
		if (!hasValidCart())
		{
			LOG.info("Missing, empty or unsupported cart");
			return REDIRECT_URL_CART;
		}

		setCurrentStep(DELIVERY_METHOD);
		return REDIRECT_PREFIX + "/checkout/multi" + currentStep.getUrl();
	}

	@RequestMapping(value = ControllerConstants.Actions.Checkout.SELECT_DELIVERY_METHOD_URL, method = RequestMethod.GET)
	@RequireHardLogIn
	public String chooseDeliveryMethod(final Model model) throws CMSItemNotFoundException
	{
		setCurrentStep(DELIVERY_METHOD);

		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();
		for (final OrderEntryData entry : cartData.getEntries())
		{
			final String productCode = entry.getProduct().getCode();
			final ProductData product = productFacade.getProductForCodeAndOptions(productCode,
					Arrays.asList(ProductOption.BASIC, ProductOption.PRICE));
			entry.setProduct(product);
		}

		model.addAttribute("cartData", cartData);
		model.addAttribute("currentStep", currentStep);
		model.addAttribute("metaRobots", "no-index,no-follow");
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));

		return currentStep.getView();
	}

	@RequestMapping(value = ControllerConstants.Actions.Checkout.SELECT_ADDRESS_URL, method = RequestMethod.GET)
	@RequireHardLogIn
	public String chooseDeliveryAddress(final HttpServletRequest request, final Model model) throws CMSItemNotFoundException
	{

		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();

		final String selectedDeliveryMethod = request.getParameter("selectedDeliveryMethod");
		final String selectedCostCenter = request.getParameter("selectedCostCenter");

		if (StringUtils.isEmpty(selectedDeliveryMethod))
		{
			GlobalMessages.addErrorMessage(model, "checkout.multi.deliveryMethod.notprovided");
			storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
			return currentStep.getView();
		}

		if (StringUtils.isEmpty(selectedCostCenter))
		{
			GlobalMessages.addErrorMessage(model, "checkout.multi.costCenter.notprovided");
			storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
			return currentStep.getView();
		}

		getCheckoutFlowFacade().setCostCenterForCart(selectedCostCenter, cartData.getCode());
		getCheckoutFlowFacade().setDeliveryMethod(TNDeliveryMethodTypeEnum.valueOf(selectedDeliveryMethod));

		model.addAttribute("cartData", cartData);
		model.addAttribute("metaRobots", "no-index,no-follow");

		if (StringUtils.equalsIgnoreCase(selectedDeliveryMethod, TNDeliveryMethodTypeEnum.PICKUP.name()))
		{
			setCurrentStep(ADDRESS_MAP);
		}
		else
		{
			model.addAttribute("addressForm", new CheckoutAddressForm());
			setCurrentStep(SELECT_DELIVERY_ADDRESS);
		}
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
		model.addAttribute("currentStep", currentStep);
		return currentStep.getView();
	}

	@RequestMapping(value = ControllerConstants.Actions.Checkout.SELECT_DELIVERY_MODE_URL, method = RequestMethod.POST)
	@RequireHardLogIn
	public String chooseDeliveryMode(final CheckoutAddressForm addressForm, final HttpServletRequest request, final Model model)
			throws CMSItemNotFoundException
	{
		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();

		if (currentStep == SELECT_DELIVERY_ADDRESS)
		{
			final boolean saveAddress = StringUtils.equals(request.getParameter("saveAddress"), "on");
			if (saveAddress)
			{
				final AddressData newAddress = new AddressData();
				newAddress.setFirstName(addressForm.getFirstName());
				newAddress.setLastName(addressForm.getLastName());
				newAddress.setPhone(addressForm.getPhone());
				newAddress.setLine1(addressForm.getStreet());
				newAddress.setTown(addressForm.getTownCity());
				newAddress.setPostalCode(addressForm.getPostcode());
				newAddress.setLine2(addressForm.getHouse());
				newAddress.setBillingAddress(false);
				newAddress.setShippingAddress(true);
				cartData.setDeliveryAddress(newAddress);
			}
			else
			{
				final String selectedAddress = request.getParameter("selectedDeliveryAddress");

				if (StringUtils.isEmpty(selectedAddress))
				{
					GlobalMessages.addErrorMessage(model, "checkout.multi.deliveryAddress.notprovided");
					storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
					setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
					return currentStep.getView();
				}

				getCheckoutFlowFacade().setDeliveryAddress(getCheckoutFlowFacade().getDeliveryAddressForCode(selectedAddress));
			}

		}
		else
		{
			//TODO address map
		}

		model.addAttribute("cartData", cartData);
		model.addAttribute("metaRobots", "no-index,no-follow");
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));

		setCurrentStep(DELIVERY_MODE);
		model.addAttribute("currentStep", currentStep);
		return currentStep.getView();
	}

	@RequestMapping(value = ControllerConstants.Actions.Checkout.SELECT_PAYMENT_METHOD_URL, method = RequestMethod.GET)
	@RequireHardLogIn
	public String choosePaymentMethod(final HttpServletRequest request, final Model model) throws CMSItemNotFoundException
	{
		final String selectedDeliveryMode = request.getParameter("selectedDeliveryMode");

		if (StringUtils.isEmpty(selectedDeliveryMode))
		{
			GlobalMessages.addErrorMessage(model, "checkout.multi.deliveryMode.notprovided");
			storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
			return currentStep.getView();
		}

		getCheckoutFlowFacade().setDeliveryMode(selectedDeliveryMode);

		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();

		model.addAttribute("cartData", cartData);
		model.addAttribute("metaRobots", "no-index,no-follow");
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));

		setCurrentStep(PAYMENT_METHOD);
		model.addAttribute("currentStep", currentStep);
		return currentStep.getView();
	}

	@RequestMapping(value = ControllerConstants.Actions.Checkout.SHOW_CHECKOUT_SUMMARY_URL, method = RequestMethod.GET)
	@RequireHardLogIn
	public String showCheckoutSummary(final HttpServletRequest request, final Model model) throws CMSItemNotFoundException
	{
		final String selectedPaymentMethod = request.getParameter("selectedPaymentMethod");

		if (StringUtils.isEmpty(selectedPaymentMethod))
		{
			GlobalMessages.addErrorMessage(model, "checkout.multi.paymentMethod.notprovided");
			storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
			return currentStep.getView();
		}

		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();
		getCheckoutFlowFacade().setPaymentMethod(TNPaymentMethodTypeEnum.valueOf(selectedPaymentMethod));//TODO

		model.addAttribute("cartData", cartData);
		model.addAttribute("metaRobots", "no-index,no-follow");
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));

		setCurrentStep(CHECKOUT_SUMMARY);
		model.addAttribute("currentStep", currentStep);
		return currentStep.getView();
	}

	@RequestMapping(value = ControllerConstants.Actions.Checkout.SHOW_HOSTED_ORDER_URL, method = RequestMethod.POST)
	@RequireHardLogIn
	public String showHostedOrderError(final HttpServletRequest request, final Model model) throws CMSItemNotFoundException
	{
		final String providedDeliveryDate = request.getParameter("providedDeliveryDate");
		final String providedDescription = request.getParameter("providedDescription");
		final Boolean emailNotification = StringUtils.equals(request.getParameter("emailNotification"), "on");

		getCheckoutFlowFacade().setProvidedDeliveryDate(providedDeliveryDate);
		getCheckoutFlowFacade().setProvidedDescription(providedDescription);
		getCheckoutFlowFacade().setEmailNotification(emailNotification);

		final OrderData orderData;
		try
		{
			orderData = getCheckoutFlowFacade().placeOrder();
			model.addAttribute("orderData", orderData);
		}
		catch (final Exception e)
		{
			LOG.error("Failed to place Order", e);
			setCurrentStep(HOSTED_ORDER_ERROR);
		}

		setCurrentStep(HOSTED_ORDER_SUCCESS);

		model.addAttribute("metaRobots", "no-index,no-follow");
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));

		model.addAttribute("currentStep", currentStep);
		return currentStep.getView();
	}

	/**
	 * @return the currentStep
	 */
	public CheckoutStep getCurrentStep()
	{
		return currentStep;
	}

	/**
	 * @param currentStep
	 *           the currentStep to set
	 */
	public void setCurrentStep(final CheckoutStep currentStep)
	{
		this.currentStep = currentStep;
	}
}
