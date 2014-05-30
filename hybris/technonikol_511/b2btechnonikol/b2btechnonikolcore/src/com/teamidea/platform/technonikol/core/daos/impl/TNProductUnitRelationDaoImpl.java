package com.teamidea.platform.technonikol.core.daos.impl;

import com.teamidea.platform.technonikol.core.constants.GeneratedB2btechnonikolCoreConstants;
import com.teamidea.platform.technonikol.core.daos.TNProductUnitRelationDao;
import de.hybris.platform.core.*;
import de.hybris.platform.core.model.link.LinkModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.genericsearch.GenericSearchQuery;
import de.hybris.platform.genericsearch.GenericSearchService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("tnProductUnitRelationDao")
public class TNProductUnitRelationDaoImpl implements TNProductUnitRelationDao {
    private static final Logger log = Logger.getLogger(TNProductUnitRelationDaoImpl.class);
    private static final String _TYPECODE = "ProductUnitRelation";
    private static final String CONVERSION = "conversion";

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Autowired
    private GenericSearchService genericSearchService;

    @Override
    public Map<String, Double> getProductUnitRelationByProduct(ProductModel productModel) {
        Map<String, Double> unitsMap = new HashMap<String, Double>();

        GenericQuery genericQuery = new GenericQuery(_TYPECODE);
        GenericSearchField fieldQualifier = new GenericSearchField(_TYPECODE, LinkModel.QUALIFIER);
        GenericSearchField fieldSource = new GenericSearchField(_TYPECODE, LinkModel.SOURCE);
        GenericSearchField fieldTarget = new GenericSearchField(_TYPECODE, LinkModel.TARGET);

        GenericCondition conditionQualifier = GenericCondition.createConditionForValueComparison(fieldQualifier, Operator.EQUAL, "ProductUnitRelation");
        GenericCondition conditionSource = GenericCondition.createConditionForValueComparison(fieldSource, Operator.EQUAL, productModel.getPk());
        GenericConditionList conditionList = GenericCondition.createConditionList();
        conditionList.addToConditionList(conditionQualifier);
        conditionList.addToConditionList(conditionSource);
        genericQuery.addCondition(conditionList);

        final GenericSelectField selectFieldTarget = new GenericSelectField(_TYPECODE, LinkModel.TARGET, String.class);
        final GenericSelectField selectFieldConversion = new GenericSelectField(_TYPECODE, CONVERSION, Double.class);
        genericQuery.addSelectField(selectFieldTarget);
        genericQuery.addSelectField(selectFieldConversion);
        Collection result = genericSearchService.search(genericQuery).getResult();

        for (Object obj: result) {
            if (obj instanceof ArrayList && ((ArrayList) obj).size() == 2) {
                final String unit = ((ArrayList) obj).get(0).toString();
                final String conversion = ((ArrayList) obj).get(1).toString();
                unitsMap.put(unit, Double.valueOf(conversion));
            }
        }
        return unitsMap;
    }
}

