/**
 *
 */
package com.teamidea.platform.technonikol.facades.product.converters;
 
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.commercefacades.product.converters.populator.ProductPopulator;
import java.lang.*;
 
public class TechnonikolProductPopulator extends ProductPopulator
{
 
 
    @Override
    public void populate(final ProductModel source, final ProductData target)
    {
        super.populate(source, target);
        final Double weightNet = source.getWeightNet();
        final Double weightGross = source.getWeightGross();
        final Double volume = source.getVolume();
        target.setWeightNet(weightNet);
        target.setWeightGross(weightGross);
        target.setVolume(volume);
    }
}