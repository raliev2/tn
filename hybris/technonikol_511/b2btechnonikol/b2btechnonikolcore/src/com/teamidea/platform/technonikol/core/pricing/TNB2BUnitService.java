package com.teamidea.platform.technonikol.core.pricing;

import com.google.common.collect.Lists;
import de.hybris.platform.b2b.constants.B2BConstants;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.b2b.model.B2BUserGroupModel;
import de.hybris.platform.b2b.services.impl.DefaultB2BUnitService;
import de.hybris.platform.core.model.enumeration.EnumerationValueModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.europe1.constants.Europe1Constants;
import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;

/**
 * Created by anton.gavazyuk on 07/04/14.
 */
public class TNB2BUnitService extends DefaultB2BUnitService {

    @Override
    public void updateBranchInSession(final Session session, final UserModel currentUser)
    {
        if (currentUser instanceof B2BCustomerModel)
        {
            final Collection<Long> priceLists = Lists.newArrayList();
            final Object[] branchInfo = (Object[]) getSessionService().executeInLocalView(new SessionExecutionBody()
            {
                @Override
                public Object[] execute()
                {
                    getSearchRestrictionService().disableSearchRestrictions();
                    final B2BCustomerModel currentCustomer = (B2BCustomerModel) currentUser;
                    final B2BUnitModel unitOfCustomer = getParent(currentCustomer);

                    Collection<PrincipalGroupModel> userGroupModels = unitOfCustomer.getAllGroups();

                    for(PrincipalGroupModel b2bUnitOrganization: userGroupModels){
                        if(b2bUnitOrganization instanceof UserGroupModel && ((UserGroupModel) b2bUnitOrganization).getUserPriceGroup()!=null)
                            priceLists.add(getTypeService().getEnumerationValue(((UserGroupModel) b2bUnitOrganization).getUserPriceGroup()).getPk().getLongValue());
                    }
                    final EnumerationValueModel userPriceGroup = (unitOfCustomer.getUserPriceGroup() != null ? getTypeService()
                            .getEnumerationValue(unitOfCustomer.getUserPriceGroup()) : lookupPriceGroupFromClosestParent(unitOfCustomer));

                    priceLists.add(userPriceGroup.getPk().getLong());
                    return new Object[]
                            { getRootUnit(unitOfCustomer), getBranch(unitOfCustomer), unitOfCustomer, userPriceGroup };
                }
            });

            getSessionService().setAttribute(B2BConstants.CTX_ATTRIBUTE_ROOTUNIT, branchInfo[0]);
            getSessionService().setAttribute(B2BConstants.CTX_ATTRIBUTE_BRANCH, branchInfo[1]);
            getSessionService().setAttribute(B2BConstants.CTX_ATTRIBUTE_UNIT, branchInfo[2]);
            if(CollectionUtils.isNotEmpty(priceLists)){
                getSessionService().setAttribute("PRICE_LISTS",priceLists);
            } else {
                getSessionService().setAttribute(Europe1Constants.PARAMS.UPG, branchInfo[3]);
            }
        }
    }
}
