package com.teamidea.platform.technonikol.storefront.controllers.pages.checkout;

import de.hybris.platform.acceleratorfacades.order.data.DeliveryOrderEntryGroupData;
import de.hybris.platform.acceleratorfacades.order.data.PickupOrderEntryGroupData;
import de.hybris.platform.acceleratorservices.email.EmailService;
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
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.storefinder.StoreFinderFacade;
import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.voucher.VoucherFacade;
import de.hybris.platform.commercefacades.voucher.exceptions.VoucherOperationException;
import de.hybris.platform.commerceservices.delivery.DeliveryService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.storefinder.data.StoreFinderSearchPageData;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teamidea.platform.technonikol.core.enums.TNDeliveryMethodTypeEnum;
import com.teamidea.platform.technonikol.core.enums.TNDeliveryModeTypeEnum;
import com.teamidea.platform.technonikol.core.enums.TNPaymentMethodTypeEnum;
import com.teamidea.platform.technonikol.storefront.annotations.RequireHardLogIn;
import com.teamidea.platform.technonikol.storefront.controllers.ControllerConstants;
import com.teamidea.platform.technonikol.storefront.controllers.util.CheckoutStep;
import com.teamidea.platform.technonikol.storefront.controllers.util.DeliveryMethod;
import com.teamidea.platform.technonikol.storefront.controllers.util.DeliveryMode;
import com.teamidea.platform.technonikol.storefront.controllers.util.GlobalMessages;
import com.teamidea.platform.technonikol.storefront.controllers.util.PaymentMethod;
import com.teamidea.platform.technonikol.storefront.forms.CheckoutAddressForm;
import com.teamidea.platform.technonikol.storefront.security.B2BUserGroupProvider;

import ru.technonikol.ws.stocks.MaterialsRow;
import ru.technonikol.ws.stocks.SendQueryResponse;

import com.teamidea.platform.technonikol.services.stock.DeliveryDateIntegrationService;


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

	@Resource(name = "b2bStoreFinderFacade")
	private StoreFinderFacade storeFinderFacade;

	@Resource(name = "defaultVoucherFacade")
	private VoucherFacade voucherFacade;

	@Resource(name = "defaultDeliveryService")
	private DeliveryService deliveryService;

	@Resource(name = "deliveryModeConverter")
	private Converter<DeliveryModeModel, DeliveryModeData> deliveryModeConverter;

	@Resource(name = "emailService")
	private EmailService emailService;

	@Resource(name = "deliveryDateIntegrationService")
	private DeliveryDateIntegrationService deliveryDateIntegrationService;

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
	private static final List<DeliveryMode> deliveryModes = new ArrayList<DeliveryMode>();

	static
	{
		deliveryMethods.add(new DeliveryMethod(TNDeliveryMethodTypeEnum.DELIVERY));
		deliveryMethods.add(new DeliveryMethod(TNDeliveryMethodTypeEnum.PICKUP));
		paymentMethods.add(new PaymentMethod(TNPaymentMethodTypeEnum.DELAY));
		paymentMethods.add(new PaymentMethod(TNPaymentMethodTypeEnum.PREPAYMENT));
		deliveryModes.add(new DeliveryMode(TNDeliveryModeTypeEnum.GROUP));
		deliveryModes.add(new DeliveryMode(TNDeliveryModeTypeEnum.SINGLE));

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

	@ModelAttribute("deliveryModes")
	public List<DeliveryMode> getDeliveryModes()
	{
		return deliveryModes;
	}

	@ModelAttribute("paymentMethods")
	public List<PaymentMethod> getPaymentMethods()
	{
		return paymentMethods;
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

	@ModelAttribute("storeAddresses")
	public List<? extends AddressData> getStoreAddresses()
	{
		final List<AddressData> storeAddresses = new ArrayList<AddressData>();
		for (final PointOfServiceData store : getPointsOfService())
		{
			storeAddresses.add(store.getAddress());
		}
		return storeAddresses;
	}

	@ModelAttribute("pointsOfService")
	public List<PointOfServiceData> getPointsOfService()
	{
		final PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(0);
		pageableData.setPageSize(10);
		pageableData.setSort("name");
		final StoreFinderSearchPageData<PointOfServiceData> storesSearchResult = storeFinderFacade
				.getAllPointOfServices(pageableData);
		final List<PointOfServiceData> stores = storesSearchResult.getResults();
		return stores;
	}

	@RequestMapping(method = RequestMethod.GET)
	@RequireHardLogIn
	public String gotoFirstStep()
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
			final List<DeliveryOrderEntryGroupData> deliveryGroups = new ArrayList<DeliveryOrderEntryGroupData>();
			final DeliveryOrderEntryGroupData deliveryGroup = new DeliveryOrderEntryGroupData();
			deliveryGroup.setEntries(cartData.getEntries());

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
				newAddress.setVisibleInAddressBook(true);
				newAddress.setCountry(getI18NFacade().getCountryForIsocode("RU"));//TODO

				getCheckoutFlowFacade().setDeliveryAddress(newAddress);
				deliveryGroup.setDeliveryAddress(newAddress);

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

				final AddressData oldAddress = getCheckoutFlowFacade().getDeliveryAddressForCode(selectedAddress);
				getCheckoutFlowFacade().setDeliveryAddress(oldAddress);
				deliveryGroup.setDeliveryAddress(oldAddress);
			}

			deliveryGroups.add(deliveryGroup);
			cartData.setDeliveryOrderGroups(deliveryGroups);
			getCheckoutFlowFacade().setDeliveryModeIfAvailable(); //TODO think about it (how to set delivery mode and address)
		}
		else
		{
			final String selectedStore = request.getParameter("selectedStore");

			if (StringUtils.isEmpty(selectedStore))
			{
				GlobalMessages.addErrorMessage(model, "checkout.multi.storeAddress.notprovided");
				storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
				setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
				return currentStep.getView();
			}

			final PointOfServiceData storeSearchResult = storeFinderFacade.getPointOfServiceForName(selectedStore);

			final List<PickupOrderEntryGroupData> pickupOrderGroups = new ArrayList<PickupOrderEntryGroupData>();
			final PickupOrderEntryGroupData pickupData = new PickupOrderEntryGroupData();
			pickupOrderGroups.add(pickupData);
			pickupData.setDeliveryPointOfService(storeSearchResult);
			cartData.setPickupOrderGroups(pickupOrderGroups);

			for (final OrderEntryData entry : cartData.getEntries())
			{
				entry.setDeliveryPointOfService(storeSearchResult); //TODO think about it (how to set selected point of service)
			}

		}

		PriceData deliveryCost = null;
		if (getCheckoutFlowFacade().getCheckoutCart().getDeliveryMode() != null)
		{
			deliveryCost = getCheckoutFlowFacade().getCheckoutCart().getDeliveryMode().getDeliveryCost();
		}

		model.addAttribute("cartData", cartData);
		model.addAttribute("deliveryCost", deliveryCost);
		model.addAttribute("metaRobots", "no-index,no-follow");
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));

		setCurrentStep(DELIVERY_MODE);
		model.addAttribute("currentStep", currentStep);
		return currentStep.getView();
	}


	@RequestMapping(value = ControllerConstants.Actions.Checkout.CHECK_PRODUCT, method = RequestMethod.GET)
	@RequireHardLogIn
	public String checkStock(final HttpServletRequest request, final Model model)
	{

		final String PRODUCT_CODE = "productCode";
		final String PRODUCT_COUNT = "count";
		final String ERROR_MESSAGE = "errorMessage";
		final String ROWS = "rows";

		final String productCode = request.getParameter(PRODUCT_CODE);
		final String count = request.getParameter(PRODUCT_COUNT);
		final String date = "";

		final SendQueryResponse response = deliveryDateIntegrationService.deliveryDateQueryOut(productCode, count, date);

		if (response == null || response.getReturn() == null || response.getReturn().getMaterials() == null)
		{
			LOG.debug("Error while trying to get delivery information for product with code = " + productCode);
			model.addAttribute(ERROR_MESSAGE, "Ошибка получения данных");
			return ControllerConstants.Views.Fragments.Stock.CheckStockInfo;
		}

		final List<MaterialsRow> deliveryInfo = response.getReturn().getMaterials().getRow();
		final List<MaterialsRow> productInfo = new ArrayList<MaterialsRow>();
		for (final MaterialsRow row : deliveryInfo)
		{
			if (StringUtils.equals(row.getEKN(), productCode))
			{
				productInfo.add(row);
			}
		}

		if (CollectionUtils.isEmpty(productInfo))
		{
			LOG.debug("Didn't get delivery information for product with code = " + productCode);
			model.addAttribute(ERROR_MESSAGE, "Данные отсутствуют");
			return ControllerConstants.Views.Fragments.Stock.CheckStockInfo;
		}

		model.addAttribute(ROWS, productInfo);
		model.addAttribute(PRODUCT_COUNT, count);

		return ControllerConstants.Views.Fragments.Stock.CheckStockInfo;
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

		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();
		getCheckoutFlowFacade().setDeliveryMode(TNDeliveryModeTypeEnum.valueOf(selectedDeliveryMode));

		model.addAttribute("cartData", cartData);
		model.addAttribute("metaRobots", "no-index,no-follow");
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));

		setCurrentStep(PAYMENT_METHOD);
		model.addAttribute("currentStep", currentStep);
		return currentStep.getView();
	}

	@RequestMapping(value = ControllerConstants.Actions.Checkout.APPLY_VOUCHER, method = RequestMethod.GET)
	@RequireHardLogIn
	public String applyVoucher(final HttpServletRequest request, final Model model) throws CMSItemNotFoundException
	{
		final String voucherCode = request.getParameter("voucherCode");
		String applyingResult = "OK";
		try
		{
			voucherFacade.applyVoucher(voucherCode);
		}
		catch (final VoucherOperationException e)
		{
			applyingResult = "ERROR";
		}

		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));

		model.addAttribute("applyingResult", applyingResult);
		return ControllerConstants.Views.Pages.MultiStepCheckout.ApplyVoucherResult;
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
			sendHostedOrderSuccessEmailTest(orderData);
			setCurrentStep(HOSTED_ORDER_SUCCESS);
		}
		catch (final Exception e)
		{
			LOG.error("Failed to place Order", e);
			setCurrentStep(HOSTED_ORDER_ERROR);
		}

		model.addAttribute("metaRobots", "no-index,no-follow");
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));

		model.addAttribute("currentStep", currentStep);
		return currentStep.getView();
	}

	private void sendHostedOrderSuccessEmailTest(final OrderData orderData)
	{
		// Recipient's email ID needs to be mentioned.
		final String to = "marina.zhigalova@gmail.com";

		// Sender's email ID needs to be mentioned
		final String from = "1plt@tn.ru";

		// Get system properties
		final Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.from", "1plt@tn.ru");
		properties.setProperty("mail.replyto", "1plt@tn.ru");

		properties.setProperty("mail.smtp.host", "mail.evozon.com");
		properties.setProperty("mail.smtp.server", "mail.evozon.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.user", "devhybris@evozon.com");
		properties.setProperty("mail.smtp.password", ".8/Vaekjd9");
		properties.setProperty("mail.use.tls", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");

		// Get the default Session object.
		final Session session = Session.getDefaultInstance(properties);

		try
		{
			// Create a default MimeMessage object.
			final MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("zhigalova@teamidea.ru"));
			// Set CC: header field of the header.
			message.addRecipient(Message.RecipientType.CC, new InternetAddress("marina.zhigalova@gmail.com"));

			// Set Subject: header field
			message.setSubject("Информация о размещенном заказе");

			// Create the message part
			final BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(createMailBody(orderData));

			// Create a multipar message
			final Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			final Transport transport = session.getTransport("smtp");
			transport.connect("mail.evozon.com", 587, "devhybris@evozon.com", ".8/Vaekjd9");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
		catch (final MessagingException mex)
		{
			mex.printStackTrace();
		}
	}

	private String createMailBody(final OrderData orderData)
	{
		final StringBuilder builder = new StringBuilder();

		builder.append("Клиент: " + orderData.getUser().getName() + "\n");
		builder.append("Юридическое лицо: " + orderData.getCostCenter().getName() + "\n");
		builder.append("Способ доставки: " + orderData.getDeliveryMethod().getCode() + "\n");
		//builder.append("Адрес доставки: " + orderData.getDeliveryAddress().getFormattedAddress() + "\n");
		builder.append("Способ оплаты: " + orderData.getPaymentMethod().getCode() + "\n");
		builder.append("Доставка товара: " + orderData.getDeliveryGroupMode().getCode() + "\n");
		builder.append("Уведомления: " + (orderData.getEmailNotification() ? "да" : "нет") + "\n");
		builder.append("Желаемая дата доставки: " + orderData.getProvidedDeliveryDate() + "\n");
		builder.append("Комментарий клиента: " + orderData.getProvidedDescription() + "\n");
		for (final OrderEntryData entry : orderData.getEntries())
		{
			builder.append("Товар: " + entry.getProduct().getName() + "\n");
			builder.append("Количество: " + entry.getQuantity() + "\n");
		}

		return builder.toString();
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
