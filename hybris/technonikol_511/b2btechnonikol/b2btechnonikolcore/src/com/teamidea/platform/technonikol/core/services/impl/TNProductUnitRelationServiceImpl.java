package com.teamidea.platform.technonikol.core.services.impl;

import com.teamidea.platform.technonikol.core.daos.TNProductUnitRelationDao;
import com.teamidea.platform.technonikol.core.services.TNProductUnitRelationService;
import de.hybris.platform.core.model.link.LinkModel;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("tnProductUnitRelationService")
public class TNProductUnitRelationServiceImpl implements TNProductUnitRelationService {

    @Resource(name = "tnProductUnitRelationDao")
    private TNProductUnitRelationDao tnProductUnitRelationDao;

    @Override
    public Map<String, Double> getProductUnitRelationByProduct(ProductModel productModel) {
        return tnProductUnitRelationDao.getProductUnitRelationByProduct(productModel);
    }
}
