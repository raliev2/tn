package com.teamidea.platform.technonikol.services.email;

import de.hybris.platform.acceleratorservices.email.EmailGenerationService;
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.cms2.model.contents.CMSItemModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.processengine.spring.Action;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.task.RetryLaterException;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * This class is action of email sending for b2bcustomers not set password;
 *
 * Last modified: 26.06.2014
 *
 * @author Igor Bobko
 */
public class SendEmailAboutSetPasswordAction implements Action
{
	@Autowired
	private EmailGenerationService emailGenerationService;

	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private ModelService modelService;

	final private static String EMAIL_PAGE_NAME = "CustomerRequestAboutSetPasswordEmailPage";

	//Result Enumeration
	private static enum R
	{
		OK, NOK
	}

	/**
	 * Execution of this action
	 *
	 * @return Returns of status this operations
	 */
	@Override
	public String execute(final BusinessProcessModel businessProcessModel) throws RetryLaterException, Exception
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {" + ItemModel.PK + "} FROM {" + EmailPageModel._TYPECODE
				+ "} WHERE {" + CMSItemModel.UID + "}=?name");
		query.addQueryParameter("name", EMAIL_PAGE_NAME);
		final EmailPageModel emailPageModel = (EmailPageModel) flexibleSearchService.searchUnique(query);
		if (emailPageModel != null)
		{
			final EmailMessageModel message = emailGenerationService.generate(businessProcessModel, emailPageModel);
			if (emailService.send(message))
			{
				final B2BCustomerModel customer = (B2BCustomerModel) ((StoreFrontCustomerProcessModel) businessProcessModel)
						.getCustomer();
				customer.setSendMail(Boolean.TRUE);
				modelService.save(customer);
				return R.OK.toString();
			}
		}
		return R.NOK.toString();
	}

	/**
	 * I don't know what it needed
	 *
	 * @return Set of Transactions
	 */
	@Override
	public Set getTransitions()
	{
		final Set result = new HashSet<String>();
		for (final R t : R.values())
		{
			result.add(t.toString());
		}
		return result;
	}
}
