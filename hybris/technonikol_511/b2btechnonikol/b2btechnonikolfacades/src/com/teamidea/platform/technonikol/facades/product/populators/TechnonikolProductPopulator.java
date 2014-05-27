/**
 *
 */
package com.teamidea.platform.technonikol.facades.product.populators;

import de.hybris.platform.commercefacades.product.converters.populator.ProductPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;

import com.teamidea.platform.technonikol.core.model.TNBrandModel;
import com.teamidea.platform.technonikol.core.model.TNProductTypeModel;
import com.teamidea.platform.technonikol.core.model.TNSupplierModel;


public class TechnonikolProductPopulator extends ProductPopulator
{


	@Override
	public void populate(final ProductModel source, final ProductData target)
	{
		super.populate(source, target);

		final java.lang.String manufacturerCode = source.getManufacturerCode();
		target.setManufacturerCode(manufacturerCode);
		final java.lang.String supplierCode = source.getSupplierCode();
		target.setSupplierCode(supplierCode);
		final java.lang.String documentCode = source.getDocumentCode();
		target.setDocumentCode(documentCode);
		final java.lang.String ETIMCode = source.getETIMCode();
		target.setETIMCode(ETIMCode);
		final java.lang.String EClassCode = source.getEClassCode();
		target.setEClassCode(EClassCode);
		final java.lang.String barcode = source.getBarcode();
		target.setBarcode(barcode);
		final TNBrandModel brand = source.getBrand();
		target.setBrand(brand);
		final TNSupplierModel supplier = source.getSupplier();
		target.setSupplier(supplier);
		final CountryModel productionCountry = source.getProductionCountry();
		target.setProductionCountry(productionCountry);
		final TNProductTypeModel productType = source.getProductType();
		target.setProductType(productType);
		final String productSubtype = source.getProductSubtype();
		target.setProductSubtype(productSubtype);
		final java.lang.String productModel = source.getProductModel();
		target.setProductModel(productModel);
		final java.lang.String productPurpose = source.getProductPurpose();
		target.setProductPurpose(productPurpose);
		final String productProperties = source.getProductProperties();
		target.setProductProperties(productProperties);
		final String productContent = source.getProductContent();
		target.setProductContent(productContent);
		final java.lang.Boolean unreplenishable = source.getUnreplenishable();
		target.setUnreplenishable(unreplenishable);
		final java.util.Date archiveDate = source.getArchiveDate();
		target.setArchiveDate(archiveDate);
		final java.lang.Integer quantityInPackage = source.getQuantityInPackage();
		target.setQuantityInPackage(quantityInPackage);
		final java.lang.Double weightGross = source.getWeightGross();
		target.setWeightGross(weightGross);
		final java.lang.Double weightNet = source.getWeightNet();
		target.setWeightNet(weightNet);
		final java.lang.Double volume = source.getVolume();
		target.setVolume(volume);
		final UnitModel baseUnit = source.getBaseUnit();
		target.setBaseUnit(baseUnit);
		final Integer vat = source.getVat();
		target.setVat(vat);


		/*
		 * final Double weightNet = source.getWeightNet(); final Double weightGross = source.getWeightGross(); final
		 * Double volume = source.getVolume(); target.setWeightNet(weightNet); target.setWeightGross(weightGross);
		 * target.setVolume(volume);
		 */
	}
}