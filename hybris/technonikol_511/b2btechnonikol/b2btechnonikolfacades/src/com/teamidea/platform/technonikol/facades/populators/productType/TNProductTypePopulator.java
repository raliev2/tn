package com.teamidea.platform.technonikol.facades.populators.productType;

import com.teamidea.platform.technonikol.core.model.TNProductTypeModel;
import com.teamidea.platform.technonikol.facades.productType.data.TNProductTypeData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class TNProductTypePopulator implements Populator<TNProductTypeModel, TNProductTypeData> {

    @Override
    public void populate(TNProductTypeModel tnProductTypeModel, TNProductTypeData tnProductTypeData) throws ConversionException {
        Assert.notNull(tnProductTypeModel, "Parameter source cannot be null.");
        Assert.notNull(tnProductTypeData, "Parameter target cannot be null.");

        tnProductTypeData.setCode(tnProductTypeModel.getCode());
        tnProductTypeData.setName(tnProductTypeModel.getName());
    }
}
