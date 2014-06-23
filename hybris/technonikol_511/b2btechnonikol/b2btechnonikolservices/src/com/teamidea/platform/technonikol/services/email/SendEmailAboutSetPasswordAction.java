package com.teamidea.platform.technonikol.services.email;

import de.hybris.platform.acceleratorservices.email.EmailGenerationService;
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.task.RetryLaterException;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author igor
 *
 */
public class SendEmailAboutSetPasswordAction implements de.hybris.platform.processengine.spring.Action
{
	@Autowired
	EmailGenerationService emailGenerationService;

	@Autowired
	FlexibleSearchService flexibleSearchService;

	@Autowired
	EmailService emailService;


	@Override
	public String execute(final BusinessProcessModel businessProcessModel) throws RetryLaterException, Exception
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {" + EmailPageModel.PK + "} FROM {"
				+ EmailPageModel._TYPECODE + "} WHERE {" + EmailPageModel.UID + "}=?name");
		query.addQueryParameter("name", "CustomerRequestAboutSetPasswordEmailPage");


		final List result = flexibleSearchService.search(query).getResult();
		final EmailPageModel emailPageModel = (EmailPageModel) result.get(0);
		final EmailMessageModel message = emailGenerationService.generate(businessProcessModel, emailPageModel);


		final String value = ((StoreFrontCustomerProcessModel) businessProcessModel).getCustomer().getName();

		message.setBody(value);


		//emailService.send(message);






		return "OK";
	}


	@Override
	public Set getTransitions()
	{
		final Set result = new TreeSet<String>();
		return result;
	}

}
