/**
 *
 */
package com.teamidea.platform.technonikol.facades.product.converters;
 
import com.teamidea.platform.technonikol.core.constants.GeneratedB2btechnonikolCoreConstants;
import com.teamidea.platform.technonikol.core.jalo.TNBrand;
import com.teamidea.platform.technonikol.core.jalo.TNProductType;
import com.teamidea.platform.technonikol.core.jalo.TNSupplier;
import com.teamidea.platform.technonikol.core.model.TNBrandModel;
import com.teamidea.platform.technonikol.core.model.TNProductTypeModel;
import com.teamidea.platform.technonikol.core.model.TNSupplierModel;
import com.teamidea.platform.technonikol.core.services.TNProductUnitRelationService;
import com.teamidea.platform.technonikol.facades.supplier.data.TNSupplierData;
import com.teamidea.platform.technonikol.facades.unit.data.UnitData;
import com.teamidea.platform.technonikol.facades.brand.data.TNBrandData;
import com.teamidea.platform.technonikol.facades.productType.data.TNProductTypeData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.link.LinkModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.commercefacades.product.converters.populator.ProductPopulator;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.core.model.type.RelationDescriptorModel;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.c2l.Country;
import de.hybris.platform.jalo.link.Link;
import de.hybris.platform.jalo.link.LinkManager;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.lang.*;
import java.util.*;

public class TechnonikolProductPopulator extends ProductPopulator {
    private static final Logger log = Logger.getLogger(TechnonikolProductPopulator.class);

    @Autowired
    private ModelService modelService;

    @Autowired
    private Converter<TNBrandModel, TNBrandData> tnBrandConverter;

    @Autowired
    private Converter<TNProductTypeModel, TNProductTypeData> tnProductTypeConverter;

    @Autowired
    private Converter<TNSupplierModel, TNSupplierData> tnSupplierConverter;

    @Autowired
    private Converter<UnitModel, UnitData> unitConverter;

    @Resource(name = "countryConverter")
    private Converter<CountryModel, CountryData> countryConverter;

    @Autowired
    private TNProductUnitRelationService tnProductUnitRelationService;

    @Override
    public void populate(final ProductModel source, final ProductData target) {
        super.populate(source, target);

        target.setManufacturerCode(source.getManufacturerCode());
        target.setSupplierCode(source.getSupplierCode());
        target.setDocumentCode(source.getDocumentCode());
        target.setETIMCode(source.getETIMCode());
        target.setEClassCode(source.getEClassCode());
        target.setBarcode(source.getBarcode());

        target.setProductSubtype(source.getProductSubtype());
        target.setProductModel(source.getProductModel());
        target.setProductPurpose(source.getProductPurpose());
        target.setProductProperties(source.getProductProperties());
        target.setProductContent(source.getProductContent());

        target.setUnreplenishable(source.getUnreplenishable());
        target.setArchiveDate(source.getArchiveDate());
        target.setQuantityInPackage(source.getQuantityInPackage());
        target.setWeightGross(source.getWeightGross());
        target.setWeightNet(source.getWeightNet());
        target.setVolume(source.getVolume());
        target.setVat(source.getVat());

        setProductBrand(source, target);
        setProductSupplier(source, target);
        setProductType(source, target);
        setProductionCountry(source, target);
        target.setBaseUnit(unitConverter.convert(source.getUnit()));
        target.setSalesUnit(unitConverter.convert(source.getSunit()));
        setProductUnitsConversion(source, target);
    }

    private void setProductBrand(ProductModel productModel, ProductData productData) {
        TNBrandModel brandModel = productModel.getBrand();
        if (brandModel != null) {
            TNBrandData tnBrandData = tnBrandConverter.convert(brandModel);
            productData.setBrand(tnBrandData);
        }
    }

    private void setProductSupplier(ProductModel productModel, ProductData productData) {
        TNSupplierModel supplierModel = productModel.getSupplier();
        if (supplierModel != null) {
            TNSupplierData supplierData = tnSupplierConverter.convert(supplierModel);
            productData.setSupplier(supplierData);
        }
    }

    private void setProductType(ProductModel productModel, ProductData productData) {
        TNProductTypeModel productTypeModel = productModel.getProductType();
        if (productTypeModel != null) {
            TNProductTypeData tnProductTypeData = tnProductTypeConverter.convert(productTypeModel);
            productData.setProductType(tnProductTypeData);
        }
    }

    private void setProductionCountry(ProductModel productModel, ProductData productData) {
        CountryModel countryModel = productModel.getProductionCountry();
        if (countryModel != null) {
            CountryData countryData = countryConverter.convert(countryModel);
            productData.setProductionCountry(countryData);
        }
    }

    private void setProductUnitsConversion(ProductModel productModel, ProductData productData) {
        /*
        Product productItem = modelService.getSource(productModel);
        Collection<Link> links = LinkManager.getInstance().getLinks("ProductUnitRelation", productItem, Link.ANYITEM);
        for (Link link : links) {
            log.info("Link class: " + link.getClass());
            log.info("Retrieved link" + link.getTarget().getPK());
        }
        */
        List<UnitData> unitList = new ArrayList<UnitData>();
        Map<String, Double> unitMap = new HashMap<String, Double>();
        Map<String, Double> unitMapModel = tnProductUnitRelationService.getProductUnitRelationByProduct(productModel);
        for (String key: unitMapModel.keySet())
        log.info("relation key:" + key + ", value: " + unitMapModel.get(key));

        if (productModel.getUnits() != null)
            log.info("Found " + productModel.getUnits().size() + " units for product");
            for (UnitModel unitModel: productModel.getUnits()) {
                log.info("Looking up unit: " + unitModel.getPk() + ":" + unitModel.getCode());
                if (unitMapModel.get(unitModel.getPk().toString()) != null) {
                    log.info("Adding unit: " + unitModel.getCode() + " with conversion: " + unitMapModel.get(unitModel.getPk().toString()));
                    unitList.add(unitConverter.convert(unitModel));
                    unitMap.put(unitModel.getCode(), unitMapModel.get(unitModel.getPk().toString()));
                }
            }
        productData.setUnits(unitList);
        productData.setUnitsMap(unitMap);
    }
}