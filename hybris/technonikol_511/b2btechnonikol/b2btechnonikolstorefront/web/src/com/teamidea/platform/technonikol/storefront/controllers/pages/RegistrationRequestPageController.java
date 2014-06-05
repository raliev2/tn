package com.teamidea.platform.technonikol.storefront.controllers.pages;

import com.teamidea.platform.technonikol.storefront.controllers.ControllerConstants;
import com.teamidea.platform.technonikol.storefront.forms.RegistrationRequestForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
}
