package com.teamidea.platform.technonikol.storefront.controllers.pages;

import com.teamidea.platform.technonikol.storefront.controllers.ControllerConstants;
import com.teamidea.platform.technonikol.storefront.controllers.util.GlobalMessages;
import com.teamidea.platform.technonikol.storefront.forms.RegistrationRequestForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@Scope("tenant")
@RequestMapping(value = "/register")
public class RegistrationRequestPageController extends DefaultPageController
{
    @Override
    @RequestMapping(method = RequestMethod.GET)
    public String get(final Model model, final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException
    {
        final RegistrationRequestForm form = new RegistrationRequestForm();
        model.addAttribute(form);
        super.get(model, request, response);
        return ControllerConstants.Views.Pages.Form.registrationRequestForm;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String sendRegistrationRequest(@Valid final RegistrationRequestForm form, final BindingResult bindingResult, final Model model,
                                 final HttpServletRequest request, final HttpServletResponse response,
                                 final RedirectAttributes redirectModel) throws CMSItemNotFoundException
    {
        if (bindingResult.hasErrors()) {
            GlobalMessages.addErrorMessage(model, "form.global.error");
            final ContentPageModel page = getContentPageForRequest(request);
            storeCmsPageInModel(model, page);
            setUpMetaDataForContentPage(model, page);
            return ControllerConstants.Views.Pages.Form.registrationRequestForm;
        }
        else {
            return ControllerConstants.Views.Pages.Form.registrationRequestForm;
        }

    }

}
