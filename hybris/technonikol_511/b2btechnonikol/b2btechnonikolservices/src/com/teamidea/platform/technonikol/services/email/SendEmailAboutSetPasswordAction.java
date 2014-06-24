package com.teamidea.platform.technonikol.services.email;

import de.hybris.platform.acceleratorservices.email.EmailGenerationService;
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.task.RetryLaterException;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Igor Bobko
 *
 */

public class SendEmailAboutSetPasswordAction implements de.hybris.platform.processengine.spring.Action
{
	@Autowired
	private EmailGenerationService emailGenerationService;

	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@Autowired
	private EmailService emailService;

	//Result Enum
	enum R
	{
		OK, NOK
	}

	@Override
	public String execute(final BusinessProcessModel businessProcessModel) throws RetryLaterException, Exception
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {" + EmailPageModel.PK + "} FROM {"
				+ EmailPageModel._TYPECODE + "} WHERE {" + EmailPageModel.UID + "}=?name");
		query.addQueryParameter("name", "CustomerRequestAboutSetPasswordEmailPage");

		final List result = flexibleSearchService.search(query).getResult();
		final EmailPageModel emailPageModel = (EmailPageModel) result.get(0);

		final de.hybris.platform.acceleratorservices.model.email.EmailMessageModel message = emailGenerationService.generate(
				businessProcessModel, emailPageModel);
		emailService.send(message);
		return R.OK.toString();
	}

	// I don't know what it needed
	@Override
	public Set getTransitions()
	{
		final Set result = new TreeSet<String>();
		final R[] rs = R.values();
		for (int i = 0; i < rs.length; i++)
		{
			result.add(rs[i].toString());
		}
		return result;
	}
}
