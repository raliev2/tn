package com.teamidea.platform.technonikol.core.daos;

import de.hybris.platform.core.model.link.LinkModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;
import java.util.Map;

public interface TNProductUnitRelationDao {
    Map<String, Double> getProductUnitRelationByProduct(ProductModel productModel);
}
