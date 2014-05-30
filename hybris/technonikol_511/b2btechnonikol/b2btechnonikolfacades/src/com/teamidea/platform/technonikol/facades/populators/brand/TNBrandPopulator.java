package com.teamidea.platform.technonikol.facades.populators.brand;

import com.teamidea.platform.technonikol.core.model.TNBrandModel;
import com.teamidea.platform.technonikol.facades.brand.data.TNBrandData;
import de.hybris.platform.commercefacades.product.converters.populator.ImagePopulator;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class TNBrandPopulator implements Populator<TNBrandModel, TNBrandData> {

    @Autowired
    private ImagePopulator imagePopulator;

    @Override
    public void populate(TNBrandModel tnBrandModel, TNBrandData tnBrandData) throws ConversionException {
        Assert.notNull(tnBrandModel, "Parameter source cannot be null.");
        Assert.notNull(tnBrandData, "Parameter target cannot be null.");

        tnBrandData.setCode(tnBrandModel.getCode());
        tnBrandData.setName(tnBrandModel.getName());

        MediaModel logoImage = tnBrandModel.getLogo();
        if (logoImage != null) {
            ImageData logoImageData = new ImageData();
            imagePopulator.populate(logoImage, logoImageData);
            tnBrandData.setLogo(logoImageData);
        }
    }
}
