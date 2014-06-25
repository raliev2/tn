/**
 * 
 */
package com.teamidea.platform.technonikol.facades.order.populators;

import de.hybris.platform.commercefacades.user.converters.populator.AddressReversePopulator;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.core.model.user.AddressModel;

import org.springframework.util.Assert;


/**
 * @author Marina
 * 
 */
public class TechnonikolAddressReversePopulator extends AddressReversePopulator
{
	@Override
	public void populate(final AddressData source, final AddressModel target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		super.populate(source, target);

		target.setEmail(source.getEmail());
	}

}
