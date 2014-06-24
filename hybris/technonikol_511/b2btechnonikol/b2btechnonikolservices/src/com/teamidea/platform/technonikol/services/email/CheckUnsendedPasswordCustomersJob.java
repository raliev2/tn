package com.teamidea.platform.technonikol.services.email;

import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.site.BaseSiteService;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public class CheckUnsendedPasswordCustomersJob extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(CheckUnsendedPasswordCustomersJob.class);

	@Autowired
	BusinessProcessService businessProcessService;

	@Autowired
	ModelService modelService;

	@Autowired
	BaseSiteService baseSiteService;

	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		LOG.info("Sending notice for b2bcustomers");
		final SearchResult<B2BCustomerModel> result = getSendCustomers();
		LOG.info("" + result.getCount());
		for (final Iterator<B2BCustomerModel> iterator = result.getResult().iterator(); iterator.hasNext();)
		{
			final B2BCustomerModel customer = iterator.next();
			processCustomer(customer);
		}

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	public SearchResult<B2BCustomerModel> getSendCustomers()
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {" + B2BCustomerModel.PK + "} FROM {"
				+ B2BCustomerModel._TYPECODE + "} WHERE {" + B2BCustomerModel.SENDMAIL + "}=?send");

		query.addQueryParameter("send", Boolean.FALSE);
		LOG.info(query.getQuery());
		return flexibleSearchService.search(query);
	}

	public void processCustomer(final B2BCustomerModel customer)
	{
		try
		{
			final StoreFrontCustomerProcessModel storeFrontCustomerProcessModel = (StoreFrontCustomerProcessModel) businessProcessService
					.createProcess("b2bcustomerRequestSetPasswordEmailProces" + System.currentTimeMillis(),
							"b2bcustomerRequestSetPasswordEmailProcess");
			storeFrontCustomerProcessModel.setCustomer(customer);
			storeFrontCustomerProcessModel.setSite(baseSiteService.getCurrentBaseSite());
			modelService.save(storeFrontCustomerProcessModel);
			businessProcessService.startProcess(storeFrontCustomerProcessModel);
			customer.setSendMail(Boolean.TRUE);
			modelService.save(customer);
		}
		catch (final Exception e)
		{
			throw e;
		}
	}
}