/**
 * 
 */
package com.teamidea.platform.technonikol.cronjobs.order;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.teamidea.platform.technonikol.services.jalo.JaloThreadFactory;
import com.teamidea.platform.technonikol.services.order.CreateOrderIntegrationService;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.util.config.ConfigIntf;


/**
 * @author Marina Zhigalova
 * 
 */
public class CreateOrderJobPerformable extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(CreateOrderJobPerformable.class);
	
	private CreateOrderIntegrationService createOrderIntegrationService;

	@Override
	public PerformResult perform(final CronJobModel model)
	{
		LOG.info("CreateOrderJob started ");

		List<OrderModel> orders = getAssignedToAdminOrders();
		
		ConfigIntf config = Registry.getCurrentTenant().getConfig();
		Integer threadNumber = Integer.valueOf(config.getParameter("b2btechnonikolstorefront.create.order.job.threads.number"));
		
		ExecutorService executor = Executors.newFixedThreadPool(threadNumber);
		JaloThreadFactory threadFactory = new JaloThreadFactory();

		for (final OrderModel order : orders) {
			CreateOrderRunnable orderCreatorRunnable = new CreateOrderRunnable(order, createOrderIntegrationService, modelService); 
			Thread orderCreatorThread = threadFactory.newThread(orderCreatorRunnable);
			executor.execute(orderCreatorThread);
		}

		executor.shutdown();

		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
		}

		LOG.info("CreateOrderJob is done ");
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	public List<OrderModel> getAssignedToAdminOrders()
	{

		final FlexibleSearchQuery query = new FlexibleSearchQuery(
				"SELECT {o:PK} FROM {Order AS o JOIN OrderStatus as os ON {o:status} = {os:PK}} WHERE {os:code} NOT IN ('SENT_TO_SERVER_OK', 'SENT_TO_SERVER_ERROR')");
		//"WHERE {os:code} = 'ASSIGNED_TO_ADMIN'"); //TODO after fixing status in which order is created (problems with approvers/permissions and etc)
		final SearchResult<OrderModel> orders = flexibleSearchService.search(query);
		return orders.getResult();
	}

	public CreateOrderIntegrationService getCreateOrderIntegrationService()
	{
		return createOrderIntegrationService;
	}

	public void setCreateOrderIntegrationService(final CreateOrderIntegrationService createOrderIntegrationService)
	{
		this.createOrderIntegrationService = createOrderIntegrationService;
	}
	
}
