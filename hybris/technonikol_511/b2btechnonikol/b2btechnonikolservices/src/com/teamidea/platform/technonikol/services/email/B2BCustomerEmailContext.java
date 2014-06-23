package com.teamidea.platform.technonikol.services.email;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.processengine.model.BusinessProcessModel;


/**
 * Velocity context for a customer email.
 */
public class B2BCustomerEmailContext extends AbstractEmailContext<BusinessProcessModel>
{

	@Override
	public void init(final BusinessProcessModel businessProcessModel, final EmailPageModel emailPageModel)
	{
		// ...
		put(FROM_EMAIL, "limit-speed@yandex.ru");
		put(FROM_DISPLAY_NAME, emailPageModel.getFromName());
		put(DISPLAY_NAME, "BDI Customer Service");
		put(EMAIL, "limit-speed@yandex.ru");//getCustomerEmailResolutionService().getEmailForCustomer(getCustomer()));
		// ...
	}

	@Override
	protected BaseSiteModel getSite(final BusinessProcessModel businessProcessModel)
	{
		return ((StoreFrontCustomerProcessModel) businessProcessModel).getSite();
	}

	@Override
	protected CustomerModel getCustomer(final BusinessProcessModel businessProcessModel)
	{
		return ((StoreFrontCustomerProcessModel) businessProcessModel).getCustomer();
	}

	@Override
	protected LanguageModel getEmailLanguage(final BusinessProcessModel businessProcessModel)
	{
		return ((StoreFrontCustomerProcessModel) businessProcessModel).getLanguage();
	}

}
