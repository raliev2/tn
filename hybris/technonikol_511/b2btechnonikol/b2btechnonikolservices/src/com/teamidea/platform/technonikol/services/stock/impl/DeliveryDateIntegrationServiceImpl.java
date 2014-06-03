/**
 * 
 */
package com.teamidea.platform.technonikol.services.stock.impl;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ru.technonikol.ws.stocks.DeliveryDateQueryOut;
import ru.technonikol.ws.stocks.SendQueryResponse;

import com.teamidea.integration.prototype.technonikol.wsclient.DeliveryDateSoapClient;
import com.teamidea.platform.technonikol.services.stock.DeliveryDateIntegrationService;


/**
 * @author Marina
 * 
 */
@Component("deliveryDateIntegrationService")
public class DeliveryDateIntegrationServiceImpl implements DeliveryDateIntegrationService
{
	protected static final Logger LOG = Logger.getLogger(DeliveryDateIntegrationServiceImpl.class);

	@Autowired
	@Qualifier("deliveryDate")
	private DeliveryDateQueryOut deliveryDate;

	private DeliveryDateSoapClient client;

	@PostConstruct
	private void postConstruct()
	{
		client = new DeliveryDateSoapClient(deliveryDate);
	}

	@Override
	public SendQueryResponse deliveryDateQueryOut(final String ekn, final String count, final String datePost)
	{
		SendQueryResponse response = null;
		try
		{
			response = client.deliveryDateQueryOut(ekn, count, datePost);
		}
		catch (final Exception exception)
		{
			LOG.error("Error while trying to get delivery information for product with code = " + ekn, exception);
			return null;
		}

		return response;
	}

	@Override
	public SendQueryResponse deliveryDateQueryOut(final String ekn, final String count, final String datePost,
			final String idPartner)
	{
		SendQueryResponse response = null;
		try
		{
			response = client.deliveryDateQueryOut(ekn, count, datePost, idPartner);
		}
		catch (final Exception exception)
		{
			LOG.error("Error while trying to get delivery information for product with code = " + ekn, exception);
			return null;
		}

		return response;
	}

	@Override
	public SendQueryResponse deliveryDateQueryOut(final String... parameters)
	{
		final String ekn = parameters[0];
		SendQueryResponse response = null;
		try
		{
			response = client.deliveryDateQueryOut(ekn, parameters[1], parameters[2], parameters[3], parameters[4], parameters[5],
					parameters[6], parameters[7], parameters[8], parameters[9], parameters[10], parameters[11], parameters[12],
					parameters[13], parameters[14], parameters[15], parameters[16]);
		}
		catch (final Exception exception)
		{
			LOG.error("Error while trying to get delivery information for product with code = " + ekn, exception);
			return null;
		}

		return response;
	}

}
