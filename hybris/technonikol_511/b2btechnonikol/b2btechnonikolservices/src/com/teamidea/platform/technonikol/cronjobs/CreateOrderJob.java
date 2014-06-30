/**
 * 
 */
package com.teamidea.platform.technonikol.cronjobs;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.ws.WebServiceException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import ru.technonikol.ws.orders.MaterialsRow;
import ru.technonikol.ws.orders.Order.Materials;
import com.teamidea.platform.technonikol.services.order.CreateOrderIntegrationService;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.util.DiscountValue;


/**
 * @author Marina
 * 
 */
public class CreateOrderJob extends AbstractJobPerformable
{

	private static final Logger LOG = Logger.getLogger(CreateOrderJob.class);

	private CreateOrderIntegrationService createOrderIntegrationService;

	@Override
	public PerformResult perform(final CronJobModel model)

	{
		LOG.info("CreateOrderJob started ");

		final List<OrderModel> orders = getAssignedToAdminOrders();

		for (final OrderModel order : orders) {
			ru.technonikol.ws.orders.Order integrationOrder = null;
			// convert order
			try {
				integrationOrder = convert(order);
			} catch (final ConversionException ex) {
				LOG.error("Error when trying to convert de.hybris.platform.core.model.order.OrderModel to ru.technonikol.ws.orders.Order", ex);
				order.setStatus(OrderStatus.SENT_TO_SERVER_ERROR);
				modelService.save(order);
				continue;
			}
			// send to service
			try {
				createOrderIntegrationService.ordersCreateRequestOutSyn(integrationOrder);
			} catch (WebServiceException exception) {
				// if service is unavailable
				if (exception.getCause() instanceof ConnectException
						|| exception.getCause() instanceof SocketTimeoutException) {
					order.setStatus(OrderStatus.RESEND_TO_SERVER);
					modelService.save(order);
					continue;
				// service data processing errors
				} else {
					order.setStatus(OrderStatus.SENT_TO_SERVER_ERROR);
					modelService.save(order);
				}
			}
			order.setStatus(OrderStatus.SENT_TO_SERVER_OK);
			modelService.save(order);
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

	public ru.technonikol.ws.orders.Order convert(final OrderModel source) throws ConversionException
	{
		final ru.technonikol.ws.orders.Order target = new ru.technonikol.ws.orders.Order();
		final SimpleDateFormat serviceDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

		try
		{
			if (source.getDeliveryAddress() != null)
			{
				final String fullAddress = source.getDeliveryAddress().getPostalcode() + "," + source.getDeliveryAddress().getTown()
						+ "," + source.getDeliveryAddress().getLine2() + "," + source.getDeliveryAddress().getLine2();
				target.setAddress(fullAddress);
				target.setHouse(source.getDeliveryAddress().getLine2());
				target.setCountry(source.getDeliveryAddress().getCountry().getIsocode());
				target.setStreet(source.getDeliveryAddress().getStreetname());
				target.setTown(source.getDeliveryAddress().getTown());
				target.setAddressCode(source.getDeliveryAddress().getPostalcode());
				target.setRegion(source.getDeliveryAddress().getTown());
			}
			else
			{
				target.setAddress("");
				target.setHouse("");
				target.setCountry("");
				target.setStreet("");
				target.setTown("");
				target.setAddressCode("");
				target.setRegion("");
			}
			target.setCountryCode("695");
			target.setApartment("");
			target.setBuilding("");
			target.setCity("");
			target.setCityCode("");
			target.setRegionCode("77");

			target.setObjectType("664b64d0-403f-11e2-8e4e-005056010274"); //TODO no data now
			target.setConstructionType("1"); //TODO no data now

			target.setDate(serviceDateFormat.format(source.getCreationtime()));
			/*
			 * if (source.getPaymentType() != null) { target.setPaymentType(source.getPaymentType().getType()); } else {
			 * target.setPaymentType(""); }
			 */

			target.setPaymentType(source.getPaymentMethod().getCode());

			LOG.debug("User uid: " + source.getUser().getUid());
			target.setIDPartner(getPartnerID(source.getUser()));
			target.setNumber(source.getCode());
			target.setWarehouseGUID("");

			if (!CollectionUtils.isEmpty(source.getEntries()))
			{
				if (source.getEntries().get(0).getDeliveryPointOfService() != null)
				{
					final List<WarehouseModel> wrhs = source.getEntries().get(0).getDeliveryPointOfService().getWarehouses();
					if (!CollectionUtils.isEmpty(wrhs))
					{
						target.setWarehouseGUID(wrhs.get(0).getCode());
					}
				}
			}

			final Materials materials = new Materials();

			for (final AbstractOrderEntryModel entry : source.getEntries())
			{
				final MaterialsRow row = new MaterialsRow();
				final Date entryDate = serviceDateFormat.parse(source.getProvidedDeliveryDate());
				row.setDeliveryDate(serviceDateFormat.format(entryDate));
				double discountValue = 0;
				for (final DiscountValue discount : entry.getDiscountValues())
				{
					discountValue += discount.getAppliedValue();
				}
				row.setDiscount(String.valueOf(discountValue));
				row.setEKN(entry.getProduct().getCode());
				row.setLineTotal(entry.getTotalPrice().toString());
				row.setPackage("");
				row.setPrice(entry.getBasePrice().toString());
				row.setQnty(entry.getQuantity().toString());
				row.setUserGroup("");
				materials.getRow().add(row);
			}
			target.setMaterials(materials);
		}
		catch (final Exception ex)
		{
			throw new ConversionException(
					"Error when trying to convert de.hybris.platform.core.model.order.OrderModel to ru.technonikol.ws.orders.Order",
					ex);
		}

		return target;
	}

	public String getPartnerID(final UserModel user)
	{
		for (final PrincipalGroupModel group : user.getGroups())
		{
			if (group.getItemtype().contains("TNContractor"))
			{
				for (final PrincipalGroupModel innerGroup : group.getGroups())
				{
					if (innerGroup.getItemtype().contains("TNPartner"))
					{
						return innerGroup.getUid();
					}
				}
			}
			else
			{
				if (group.getItemtype().contains("TNPartner"))
				{
					return group.getUid();
				}
			}
		}
		return "";
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
