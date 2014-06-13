/**
 *
 */
package com.teamidea.platform.technonikol.facades.order.populators;

import de.hybris.platform.commercefacades.order.converters.populator.CartPopulator;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.core.model.order.CartModel;

import org.springframework.util.Assert;


public class TechnonikolCartPopulator extends CartPopulator
{

	@Override
	public void populate(final CartModel source, final CartData target)
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