package com.teamidea.platform.technonikol.core.pricing;

import de.hybris.platform.b2b.constants.B2BConstants;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.b2b.services.impl.DefaultB2BUnitService;
import de.hybris.platform.core.model.enumeration.EnumerationValueModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.europe1.constants.Europe1Constants;
import de.hybris.platform.europe1.enums.UserPriceGroup;
import de.hybris.platform.servicelayer.session.Session;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.teamidea.platform.technonikol.core.constants.B2btechnonikolCoreConstants;


/**
 * Created by anton.gavazyuk on 07/04/14.
 */
public class TNB2BUnitService extends DefaultB2BUnitService
{
	private static final Logger log = Logger.getLogger(TNB2BUnitService.class);

	@Override
	public void updateBranchInSession(final Session session, final UserModel currentUser)
	{
		if (log.isDebugEnabled())
		{
			log.debug(">> updateBranchInSession()");
		}

		if (currentUser instanceof B2BCustomerModel)
		{
			final Collection<Long> priceLists = Sets.newHashSet();

			final Object[] branchInfo = (Object[]) getSessionService().executeInLocalView(new SessionExecutionBody()
			{
				@Override
				public Object[] execute()
				{
					getSearchRestrictionService().disableSearchRestrictions();

					final B2BCustomerModel currentCustomer = (B2BCustomerModel) currentUser;
					final B2BUnitModel unitOfCustomer = getParent(currentCustomer);

					// Will not add UPG from groups only their own and parents UPG will be used
					//addAllGroupsUPG(priceLists, unitOfCustomer);					

					if (unitOfCustomer.getAllUserPriceGroups() != null && !unitOfCustomer.getAllUserPriceGroups().isEmpty())
					{
						addAllUPG(priceLists, asEnumerationValueModels(unitOfCustomer.getAllUserPriceGroups()));
					}
					else
					{
						addAllUPG(priceLists, lookupPriceGroupCollectionFromClosestParent(unitOfCustomer));
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
			if (CollectionUtils.isNotEmpty(priceLists))
			{
				getSessionService().setAttribute(B2btechnonikolCoreConstants.CTX_ATTRIBUTE_PRICE_LISTS, priceLists);
			}
			else
			{
				getSessionService().setAttribute(Europe1Constants.PARAMS.UPG, branchInfo[3]);
			}
		}

		if (log.isDebugEnabled())
		{
			log.debug("<< updateBranchInSession()");
		}
	}


	/**
	 * Add the UPG from the assigned groups
	 * 
	 * @param priceLists
	 * @param unitOfCustomer
	 */
	private void addAllGroupsUPG(final Collection<Long> priceLists, final B2BUnitModel unitOfCustomer)
	{
		final Collection<PrincipalGroupModel> userGroupModels = unitOfCustomer.getAllGroups();

		for (final PrincipalGroupModel b2bUnitOrganization : userGroupModels)
		{
			if (log.isDebugEnabled())
			{
				log.debug("Check user price groups for B2BUnit: " + b2bUnitOrganization.getUid());
			}

			if (b2bUnitOrganization instanceof UserGroupModel)
			{
				addAllUPG(priceLists, asEnumerationValueModels(((UserGroupModel) b2bUnitOrganization).getAllUserPriceGroups()));
			}
		}
	}

	/**
	 * Add all UPG
	 * 
	 * @param priceLists
	 * @param userGroup
	 */
	private void addAllUPG(final Collection<Long> priceLists, final Collection<EnumerationValueModel> upgModels)
	{
		for (final EnumerationValueModel model : upgModels)
		{
			if (log.isDebugEnabled())
			{
				log.debug("add UPG: " + model.getCode());
			}
			priceLists.add(model.getPk().getLongValue());
		}
	}

	/**
	 * 
	 * @param userPriceGroups
	 * @return
	 */
	private Collection<EnumerationValueModel> asEnumerationValueModels(final Collection<UserPriceGroup> userPriceGroups)
	{
		final List<EnumerationValueModel> enumModels = Lists.newArrayList();

		for (final UserPriceGroup priceGroup : userPriceGroups)
		{
			enumModels.add(getTypeService().getEnumerationValue(priceGroup));
		}
		return enumModels;
	}

	/**
	 * 
	 * @param unitOfCustomer
	 * @return
	 */
	protected Collection<EnumerationValueModel> lookupPriceGroupCollectionFromClosestParent(final B2BUnitModel unitOfCustomer)
	{
		if (log.isDebugEnabled())
		{
			log.debug(">> lookupPriceGroupCollectionFromClosestParent(). B2BUnit:" + unitOfCustomer.getUid());
		}

		for (final B2BUnitModel unitModel : getAllParents(unitOfCustomer))
		{

			if (unitModel.getAllUserPriceGroups() != null && !unitModel.getAllUserPriceGroups().isEmpty())
			{
				if (log.isDebugEnabled())
				{
					log.debug("<< lookupPriceGroupCollectionFromClosestParent()");
				}
				return asEnumerationValueModels(unitModel.getAllUserPriceGroups());
			}
		}

		if (log.isDebugEnabled())
		{
			log.debug("<< lookupPriceGroupCollectionFromClosestParent().B2B_DEFAULT_PRICE_GROUP");
		}
		return Lists.newArrayList(getTypeService().getEnumerationValue("UserPriceGroup", "B2B_DEFAULT_PRICE_GROUP"));
	}
}
