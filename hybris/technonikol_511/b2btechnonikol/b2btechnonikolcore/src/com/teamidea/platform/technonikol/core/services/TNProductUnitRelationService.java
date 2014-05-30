package com.teamidea.platform.technonikol.core.services;

import de.hybris.platform.core.model.link.LinkModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;
import java.util.Map;

public interface TNProductUnitRelationService {
    Map<String, Double> getProductUnitRelationByProduct(ProductModel productModel);
}
