package com.teamidea.platform.technonikol.facades.populators.supplier;

import com.teamidea.platform.technonikol.core.model.TNSupplierModel;
import com.teamidea.platform.technonikol.facades.supplier.data.TNSupplierData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class TNSupplierPopulator implements Populator<TNSupplierModel, TNSupplierData> {

    @Override
    public void populate(TNSupplierModel tnSupplierModel, TNSupplierData tnSupplierData) throws ConversionException {
        Assert.notNull(tnSupplierModel, "Parameter source cannot be null.");
        Assert.notNull(tnSupplierData, "Parameter target cannot be null.");

        tnSupplierData.setCode(tnSupplierModel.getCode());
        tnSupplierData.setName(tnSupplierModel.getName());
    }
}
