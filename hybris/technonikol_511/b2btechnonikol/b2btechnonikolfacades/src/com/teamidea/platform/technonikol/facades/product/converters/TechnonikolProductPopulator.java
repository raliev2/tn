/**
 *
 */
package com.teamidea.platform.technonikol.facades.product.converters;

import com.teamidea.platform.technonikol.facades.brand.data.TNBrandData;
import com.teamidea.platform.technonikol.facades.productType.data.TNProductTypeData;
import com.teamidea.platform.technonikol.facades.supplier.data.TNSupplierData;
import com.teamidea.platform.technonikol.facades.unit.data.UnitData;
import de.hybris.platform.commercefacades.product.converters.populator.ProductPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.teamidea.platform.technonikol.core.model.TNBrandModel;
import com.teamidea.platform.technonikol.core.model.TNProductTypeModel;
import com.teamidea.platform.technonikol.core.model.TNSupplierModel;
import com.teamidea.platform.technonikol.core.services.TNProductUnitRelationService;


public class TechnonikolProductPopulator extends ProductPopulator
{
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
	public void populate(final ProductModel source, final ProductData target)
	{
		super.populate(source, target);

		log.info("Calling TNProduct populator");
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
        target.setMinOrderQuantity(source.getMinOrderQuantity());
        target.setMaxOrderQuantity(source.getMaxOrderQuantity());

        setProductBrand(source, target);
		setProductSupplier(source, target);
		setProductType(source, target);
		setProductionCountry(source, target);
		if (source.getBaseUnit() != null)
		{
			target.setBaseUnit(unitConverter.convert(source.getBaseUnit()));
		}
		if (source.getUnit() != null)
		{
			target.setSalesUnit(unitConverter.convert(source.getUnit()));
		}
		setProductUnitsConversion(source, target);
	}

	private void setProductBrand(final ProductModel productModel, final ProductData productData)
	{
		final TNBrandModel brandModel = productModel.getBrand();
		if (brandModel != null)
		{
			final TNBrandData tnBrandData = tnBrandConverter.convert(brandModel);
			productData.setBrand(tnBrandData);
		}
	}

	private void setProductSupplier(final ProductModel productModel, final ProductData productData)
	{
		final TNSupplierModel supplierModel = productModel.getSupplier();
		if (supplierModel != null)
		{
			final TNSupplierData supplierData = tnSupplierConverter.convert(supplierModel);
			productData.setSupplier(supplierData);
		}
	}

	private void setProductType(final ProductModel productModel, final ProductData productData)
	{
		final TNProductTypeModel productTypeModel = productModel.getProductType();
		if (productTypeModel != null)
		{
			final TNProductTypeData tnProductTypeData = tnProductTypeConverter.convert(productTypeModel);
			productData.setProductType(tnProductTypeData);
		}
	}

	private void setProductionCountry(final ProductModel productModel, final ProductData productData)
	{
		final CountryModel countryModel = productModel.getProductionCountry();
		if (countryModel != null)
		{
			final CountryData countryData = countryConverter.convert(countryModel);
			productData.setProductionCountry(countryData);
		}
	}

	private void setProductUnitsConversion(final ProductModel productModel, final ProductData productData)
	{
		/*
		 * Product productItem = modelService.getSource(productModel); Collection<Link> links =
		 * LinkManager.getInstance().getLinks("ProductUnitRelation", productItem, Link.ANYITEM); for (Link link : links) {
		 * log.info("Link class: " + link.getClass()); log.info("Retrieved link" + link.getTarget().getPK()); }
		 */
		final List<UnitData> unitList = new ArrayList<UnitData>();
		final Map<String, Double> unitMap = new HashMap<String, Double>();
		final Map<String, Double> unitMapModel = tnProductUnitRelationService.getProductUnitRelationByProduct(productModel);

		if (log.isDebugEnabled())
		{
			for (final String key : unitMapModel.keySet())
			{
				log.debug("relation key:" + key + ", value: " + unitMapModel.get(key));
			}
		}

		if (productModel.getUnits() != null)
		{
			//log.info("Found " + productModel.getUnits().size() + " units for product");
			for (final UnitModel unitModel : productModel.getUnits())
			{
				final String unitPk = unitModel.getPk().toString();
				//log.info("Looking up unit: " + unitModel.getPk() + ":" + unitModel.getCode());
				if (unitMapModel.get(unitPk) != null)
				{
					if (log.isDebugEnabled())
					{
						log.debug("Adding unit: " + unitModel.getCode() + " with conversion: "
								+ unitMapModel.get(unitModel.getPk().toString()));
					}
					unitList.add(unitConverter.convert(unitModel));
					unitMap.put(unitModel.getCode(), unitMapModel.get(unitPk));
				}
			}
		}
		productData.setUnits(unitList);
		productData.setUnitsMap(unitMap);
	}
}