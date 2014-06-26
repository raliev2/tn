package com.teamidea.platform.technonikol.services.email;

import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.site.BaseSiteService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * This class is job of email sending for b2bcustomers not set password;
 *
 * Last modified: 26.06.2014
 *
 * @author Igor Bobko
 */
public class CheckUnsendedPasswordCustomersJob extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(CheckUnsendedPasswordCustomersJob.class);

	@Autowired
	private BusinessProcessService businessProcessService;

	@Autowired
	private BaseSiteService baseSiteService;

	private final static String PROCESS_NAME = "b2bcustomerRequestSetPasswordEmailProcess";

	/**
	 * This execution than job is start running
	 */
	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		LOG.info("Sending notice for b2bcustomers about password");
		for (final B2BCustomerModel customer : getSendCustomers().getResult())
		{
			processCustomer(customer);
		}
		LOG.info("Sending notice for b2bcustomers about password FINISHED");
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * Execute FlexibleSearch for b2bcustomers
	 *
	 * @return Returns B2BCustomers is not set password
	 */
	public SearchResult<B2BCustomerModel> getSendCustomers()
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {" + B2BCustomerModel.PK + "} FROM {"
				+ B2BCustomerModel._TYPECODE + "} WHERE {" + B2BCustomerModel.SENDMAIL + "}=?" + B2BCustomerModel.SENDMAIL);
		query.addQueryParameter(B2BCustomerModel.SENDMAIL, Boolean.FALSE);
		return flexibleSearchService.search(query);
	}

	/**
	 * Start process make sending notice
	 */
	public void processCustomer(final B2BCustomerModel customer)
	{
		final StoreFrontCustomerProcessModel storeFrontCustomerProcessModel = (StoreFrontCustomerProcessModel) businessProcessService
				.createProcess(PROCESS_NAME + System.currentTimeMillis(), PROCESS_NAME);
		storeFrontCustomerProcessModel.setCustomer(customer);
		storeFrontCustomerProcessModel.setSite(baseSiteService.getCurrentBaseSite());
		businessProcessService.startProcess(storeFrontCustomerProcessModel);
	}
}