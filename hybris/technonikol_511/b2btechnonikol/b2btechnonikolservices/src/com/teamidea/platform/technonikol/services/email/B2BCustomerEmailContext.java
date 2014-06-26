package com.teamidea.platform.technonikol.services.email;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.b2b.model.B2BCustomerModel;
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
		super.init(businessProcessModel, emailPageModel);
		final B2BCustomerModel b2bCustomer = (B2BCustomerModel) this.getCustomer(businessProcessModel);
		emailPageModel.getFromEmail();
		put(EMAIL, b2bCustomer.getEmail());
		put(FROM_EMAIL, "hdevhybris@evozon.com");
		put(FROM_DISPLAY_NAME, "TN");
		put(EMAIL, b2bCustomer.getCustomerID());
		put(DISPLAY_NAME, "lala");

		put(BASE_URL, "baseUrl");
		put(SECURE_BASE_URL, "secureBaseUrl");
		put(MEDIA_BASE_URL, "mediaBaseUrl");
		put(MEDIA_SECURE_BASE_URL, "mediaSecureBaseUrl");
		put(TITLE, "title");
		put(FROM_DISPLAY_NAME, "fromDisplayName");
		put(THEME, "theme");
		put(BASE_SITE, this.getSite(businessProcessModel));
		put(BASE_THEME_URL, "/");
		put(EMAIL_LANGUAGE, this.getEmailLanguage(businessProcessModel));

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
