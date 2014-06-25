/**
 * 
 */
package com.teamidea.platform.technonikol.services.order.impl;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ru.technonikol.ws.orders.Order;
import ru.technonikol.ws.orders.OrdersCreateRequestOutSyn;
import ru.technonikol.ws.orders.SendOrderSAPResponse;

import com.teamidea.integration.prototype.technonikol.wsclient.CreateOrderSoapClient;
import com.teamidea.platform.technonikol.services.order.CreateOrderIntegrationService;


/**
 * @author Marina
 * 
 */
@Component("createOrderIntegrationService")
public class CreateOrderIntegrationServiceImpl implements CreateOrderIntegrationService
{
	protected static final Logger LOG = Logger.getLogger(CreateOrderIntegrationServiceImpl.class);

	@Autowired
	@Qualifier("orderCreate")
	private OrdersCreateRequestOutSyn orderCreate;

	private CreateOrderSoapClient client;

	@PostConstruct
	private void postConstruct()
	{
		client = new CreateOrderSoapClient(orderCreate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.teamidea.platform.technonikol.services.order.CreateOrderIntegrationService#ordersCreateRequestOutSyn(ru.
	 * technonikol.ws.orders.Order)
	 */
	@Override
	public SendOrderSAPResponse ordersCreateRequestOutSyn(final Order order)
	{
		SendOrderSAPResponse response = null;
		try
		{
			response = client.ordersCreateRequestOutSyn(order);
		}
		catch (final Exception exception)
		{
			LOG.error("Error while trying to create order with number = " + order.getNumber(), exception);
			return null;
		}

		return response;
	}

}
