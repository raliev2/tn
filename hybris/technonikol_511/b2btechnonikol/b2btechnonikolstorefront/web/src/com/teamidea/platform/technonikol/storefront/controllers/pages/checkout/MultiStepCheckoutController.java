package com.teamidea.platform.technonikol.storefront.controllers.pages.checkout;

import de.hybris.platform.b2bacceleratorfacades.order.data.B2BCostCenterData;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.user.data.AddressData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teamidea.platform.technonikol.storefront.annotations.RequireHardLogIn;
import com.teamidea.platform.technonikol.storefront.controllers.ControllerConstants;
import com.teamidea.platform.technonikol.storefront.controllers.util.CheckoutStep;
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

    private CheckoutStep currentStep = CheckoutStep.DELIVERY_METHOD;

    @RequestMapping(method = RequestMethod.GET)
    @RequireHardLogIn
    public String gotoFirstStep(final Model model)
    {
        if (!hasValidCart())
        {
            LOG.info("Missing, empty or unsupported cart");
            return REDIRECT_URL_CART;
        }

        return REDIRECT_PREFIX + "/checkout/multi" + currentStep.getUrl();
    }

    @RequestMapping(value = "/checkout/multi/select-test", method = RequestMethod.GET)
    @RequireHardLogIn
    public String test(final Model model)
    {
        return "pages/checkout/multi/chooseDeliveryMethodPage";
    }

    @RequestMapping(value = ControllerConstants.Actions.Checkout.SELECT_DELIVERY_METHOD_URL, method = RequestMethod.GET)
    @RequireHardLogIn
    public String chooseDeliveryMethod(final Model model) throws CMSItemNotFoundException
    {

        setCurrentStep(CheckoutStep.DELIVERY_METHOD);

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

        //final List<? extends DeliveryModeData> deliveryData = getCheckoutFlowFacade().getSupportedDeliveryModes();
        //FIXME
        final List<DeliveryModeData> deliveryData = new ArrayList<DeliveryModeData>();
        final DeliveryModeData item1 = new DeliveryModeData();
        item1.setCode("1");
        item1.setName("ITEM1");
        deliveryData.add(item1);
        final DeliveryModeData item2 = new DeliveryModeData();
        item2.setCode("2");
        item2.setName("ITEM2");
        deliveryData.add(item2);

        model.addAttribute("deliveryModes", deliveryData);
        model.addAttribute("costCenters", getCheckoutFlowFacade().getActiveVisibleCostCenters());

        model.addAttribute("metaRobots", "no-index,no-follow");
        storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_STEP_CHECKOUT_CMS_PAGE_LABEL));

        return currentStep.getView();
    }

    @RequestMapping(value = ControllerConstants.Actions.Checkout.SELECT_DELIVERY_ADDRESS_URL, method = RequestMethod.GET)
    @RequireHardLogIn
    public String chooseDeliveryAddress(final HttpServletRequest request, final Model model)
    {

        setCurrentStep(CheckoutStep.DELIVERY_ADDRESS);

        final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();

        final String selectedDeliveryMode = request.getParameter("selectedDeliveryMode");
        final String selectedCostCenter = request.getParameter("selectedCostCenter");

        //FIXME
        final List<B2BCostCenterData> costCenters = getCheckoutFlowFacade().getActiveVisibleCostCenters();
        for (final B2BCostCenterData costCenter : costCenters)
        {
            if (costCenter.getCode().equals(selectedCostCenter))
            {
                cartData.setCostCenter(costCenter);
            }
        }

        final List<DeliveryModeData> deliveryData = new ArrayList<DeliveryModeData>();
        final DeliveryModeData item1 = new DeliveryModeData();
        item1.setCode("1");
        item1.setName("ITEM1");
        deliveryData.add(item1);
        final DeliveryModeData item2 = new DeliveryModeData();
        item2.setCode("2");
        item2.setName("ITEM2");
        deliveryData.add(item2);

        for (final DeliveryModeData deliveryMode : deliveryData)
        {
            if (deliveryMode.getCode().equals(selectedDeliveryMode))
            {
                cartData.setDeliveryMode(deliveryMode);
            }
        }

        //		if (hasNoDeliveryMode())
        //		{
        //			GlobalMessages.addErrorMessage(model, "checkout.multi.deliveryMethod.notprovided");
        //			return currentStep.getView();
        //		}

        model.addAttribute("currentStep", currentStep);
        model.addAttribute("deliveryAddresses", getCheckoutFlowFacade().getSupportedDeliveryAddresses(true));
        model.addAttribute("addressForm", new CheckoutAddressForm());

        return currentStep.getView();
    }

    @RequestMapping(value = ControllerConstants.Actions.Checkout.SHOW_DELIVERY_ADDRESS_MAP_URL, method = RequestMethod.POST)
    @RequireHardLogIn
    public String showDeliveryAddressMap(final CheckoutAddressForm addressForm, final HttpServletRequest request, final Model model)
    {

        setCurrentStep(CheckoutStep.ADDRESS_MAP);

        final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();

        //getAddressValidator().validate(addressForm, bindingResult); TODO

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

        model.addAttribute("currentStep", currentStep);

        return currentStep.getView();
    }

    @RequestMapping(value = ControllerConstants.Actions.Checkout.SELECT_DELIVERY_MODE_URL, method = RequestMethod.GET)
    @RequireHardLogIn
    public String chooseDeliveryMode(final Model model) throws CMSItemNotFoundException
    {
        setCurrentStep(CheckoutStep.DELIVERY_MODE);
        model.addAttribute("currentStep", currentStep);
        return currentStep.getView();
    }

    @RequestMapping(value = ControllerConstants.Actions.Checkout.SELECT_PAYMENT_METHOD_URL, method = RequestMethod.GET)
    @RequireHardLogIn
    public String choosePaymentMethod(final Model model) throws CMSItemNotFoundException
    {
        setCurrentStep(CheckoutStep.PAYMENT_METHOD);
        model.addAttribute("currentStep", currentStep);
        return currentStep.getView();
    }

    @RequestMapping(value = ControllerConstants.Actions.Checkout.SHOW_CHECKOUT_SUMMARY_URL, method = RequestMethod.POST)
    @RequireHardLogIn
    public String showCheckoutSummary(final Model model) throws CMSItemNotFoundException
    {
        setCurrentStep(CheckoutStep.CHECKOUT_SUMMARY);
        model.addAttribute("currentStep", currentStep);
        return currentStep.getView();
    }

    @RequestMapping(value = ControllerConstants.Actions.Checkout.SHOW_HOSTED_ORDER_ERROR_URL, method = RequestMethod.GET)
    @RequireHardLogIn
    public String showHostedOrderError(final Model model) throws CMSItemNotFoundException
    {
        setCurrentStep(CheckoutStep.HOSTED_ORDER_ERROR);
        model.addAttribute("currentStep", currentStep);
        return currentStep.getView();
    }

    @RequestMapping(value = ControllerConstants.Actions.Checkout.SHOW_HOSTED_ORDER_SUCCESS_URL, method = RequestMethod.GET)
    @RequireHardLogIn
    public String showHostedOrderSuccess(final Model model) throws CMSItemNotFoundException
    {
        setCurrentStep(CheckoutStep.HOSTED_ORDER_SUCCESS);
        model.addAttribute("currentStep", currentStep);
        return currentStep.getView();
    }

    @Override
    protected List<? extends AddressData> getDeliveryAddresses(final AddressData selectedAddressData)
    {
        List<AddressData> deliveryAddresses = null;
        if (selectedAddressData == null)
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

    @Override
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

    public CheckoutStep getCurrentStep()
    {
        return currentStep;
    }

    public void setCurrentStep(final CheckoutStep currentStep)
    {
        this.currentStep = currentStep;
    }

}
