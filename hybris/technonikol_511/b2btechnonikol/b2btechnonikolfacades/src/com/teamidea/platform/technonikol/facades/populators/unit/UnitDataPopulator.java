package com.teamidea.platform.technonikol.facades.populators.unit;

import de.hybris.platform.converters.Populator;
import com.teamidea.platform.technonikol.facades.unit.data.UnitData;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UnitDataPopulator implements Populator<UnitModel, UnitData> {

    @Override
    public void populate(UnitModel unitModel, UnitData unitData) throws ConversionException {
        Assert.notNull(unitModel, "Parameter source cannot be null.");
        Assert.notNull(unitData, "Parameter target cannot be null.");
        if(unitModel != null) {
            unitData.setCode(unitModel.getCode());
            unitData.setName(unitModel.getName());
            unitData.setUnitType(unitModel.getUnitType());
            unitData.setConversion(unitModel.getConversion());
        }
    }
}
