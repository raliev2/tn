/**
 *
 */
package com.teamidea.platform.technonikol.facades.order.populators;

import de.hybris.platform.commercefacades.order.converters.populator.OrderPopulator;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.OrderModel;

import org.springframework.util.Assert;


public class TechnonikolOrderPopulator extends OrderPopulator
{

	@Override
	public void populate(final OrderModel source, final OrderData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		super.populate(source, target);

		target.setDeliveryMethod(source.getDeliveryMethod());
		target.setPaymentMethod(source.getPaymentMethod());
		target.setProvidedDeliveryDate(source.getProvidedDeliveryDate());
		target.setProvidedDescription(source.getProvidedDescription());
		target.setEmailNotification(source.getEmailNotification());

	}

}