package com.teamidea.platform.technonikol.core.pricing;

import de.hybris.platform.catalog.jalo.CatalogAwareEurope1PriceFactory;
import de.hybris.platform.core.PK;
import de.hybris.platform.europe1.constants.Europe1Constants;
import de.hybris.platform.europe1.enums.PriceRowChannel;
import de.hybris.platform.europe1.jalo.PDTRow;
import de.hybris.platform.europe1.jalo.PriceRow;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.flexiblesearch.FlexibleSearch;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.util.DateRange;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.teamidea.platform.technonikol.core.constants.B2btechnonikolCoreConstants;


/**
 * Created by anton.gavazyuk on 07/04/14.
 */
public class TNPriceFactory extends CatalogAwareEurope1PriceFactory
{
	private static final Logger log = Logger.getLogger(TNPriceFactory.class);

	private FlexibleSearchService flexibleSearchService;

	//private UserService userService;
	//private B2BCommerceB2BUserGroupService b2BUserGroupService;

	@Override
	protected List<PriceRow> filterPriceRows4Info(final Collection<PriceRow> rows, final Currency curr, final Date date,
			final PriceRowChannel channel)
	{
		if (log.isDebugEnabled())
		{
			log.debug(">> filterPriceRows4Info()");
		}

		if (rows.isEmpty())
		{
			return Collections.EMPTY_LIST;
		}

		final Currency base = (curr.isBase().booleanValue()) ? null : C2LManager.getInstance().getBaseCurrency();

		final List<PriceRow> ret = new ArrayList<PriceRow>(rows);
		boolean hasChannelRowMatching = false;
		for (final ListIterator<PriceRow> it = ret.listIterator(); it.hasNext();)
		{
			final PriceRow priceRow = it.next();
			final Currency currency = priceRow.getCurrency();
			if ((!(curr.equals(currency))) && (((base == null) || (!(base.equals(currency))))))
			{
				it.remove();
			}
			else
			{
				final DateRange dateRange = priceRow.getDateRange();
				if ((dateRange != null) && (!(dateRange.encloses(date))))
				{
					it.remove();
				}
				else if (priceRow.isGiveAwayPriceAsPrimitive())
				{
					it.remove();
				}
				else if ((channel != null) && (priceRow.getChannel() != null)
						&& (!(priceRow.getChannel().getCode().equalsIgnoreCase(channel.getCode()))))
				{
					it.remove();
				}
				else
				{
					if ((channel == null) || (priceRow.getChannel() == null)
							|| (!(priceRow.getChannel().getCode().equalsIgnoreCase(channel.getCode()))))
					{
						continue;
					}
					hasChannelRowMatching = true;
				}
			}
		}

		if ((hasChannelRowMatching) && (ret.size() > 1))
		{
			for (final ListIterator<PriceRow> it = ret.listIterator(); it.hasNext();)
			{
				final PriceRow priceRow = it.next();
				if (priceRow.getChannel() != null)
				{
					continue;
				}
				it.remove();
			}
		}

		if (log.isDebugEnabled())
		{
			log.debug("After filtering. " + ret.toString());
		}

		// find minumum price
		double minPrice = Double.MAX_VALUE;
		PriceRow selectedPriceRow = null;
		PriceRow defaultPriceRow = null;
		for (final PriceRow priceRow : ret)
		{
			if (priceRow.getUserGroup() != null)
			{
				if (minPrice > priceRow.getPrice())
				{
					minPrice = priceRow.getPrice();
					selectedPriceRow = priceRow;
				}
			}
			else
			{
				if (defaultPriceRow == null)
				{
					defaultPriceRow = priceRow;
				}
			}
		}
		final List<PriceRow> returnRows = new ArrayList<PriceRow>();
		returnRows.add(selectedPriceRow != null ? selectedPriceRow : defaultPriceRow);

		if (log.isDebugEnabled())
		{
			log.debug("minimum price: " + selectedPriceRow + " -> "
					+ (selectedPriceRow != null ? selectedPriceRow.getPrice().doubleValue() : "-"));
			log.debug("default price: " + defaultPriceRow + " -> "
					+ (defaultPriceRow != null ? defaultPriceRow.getPrice().doubleValue() : "-"));

			log.debug("<< filterPriceRows4Info(). " + returnRows.toString());
		}

		return returnRows;
	}

	@Override
	protected Collection<PriceRow> queryPriceRows4Price(final SessionContext ctx, final Product product,
			final EnumerationValue productGroup, final User user, final EnumerationValue userGroup)
	{
		if (log.isDebugEnabled())
		{
			log.debug(">> queryPriceRows4Price(). " + "Product:" + (product != null ? product.getCode() : "-") + "; User:"
					+ (user != null ? user.getUid() : "-"));
		}

		//
		final StringBuilder query = new StringBuilder("SELECT {").append(Item.PK).append("} FROM {")
				.append(Europe1Constants.TC.PRICEROW).append(" AS pr} WHERE ");
		final Map values = new HashMap();

		// product & PG
		appendProductConditions(query, "pr", product, productGroup, values, PriceRow.PG);


		// user & UG
		query.append(" AND ");
		if (CollectionUtils.isNotEmpty((Collection) ctx.getAttribute(B2btechnonikolCoreConstants.CTX_ATTRIBUTE_PRICE_LISTS)))
		{
			appendUserConditions(query, (Collection) ctx.getAttribute(B2btechnonikolCoreConstants.CTX_ATTRIBUTE_PRICE_LISTS),
					values, user);
		}
		else
		{
			appendUserConditions(query, "pr", user, userGroup, values, PriceRow.UG);
		}

		//        query.append(" SORT BY {}")

		if (log.isDebugEnabled())
		{
			log.debug("<< queryPriceRows4Price(). " + "Query:" + query.toString());
		}

		return FlexibleSearch.getInstance().search(//
				ctx, //
				query.toString(), //
				values, //
				PriceRow.class//
				).getResult();

	}

	protected StringBuilder appendUserConditions(final StringBuilder query, final Collection<Long> userGroups, final Map values,
			final User user)
	{
		query.append("{").append("pr").append(":").append(PDTRow.USERMATCHQUALIFIER).append("} IN ( ?anyU ");
		values.put("anyU", Long.valueOf(PK.NULL_PK.getLongValue()));
		if (user != null)
		{
			query.append(",?user ");
			values.put("user", Long.valueOf(user.getPK().getLongValue()));
		}

		for (final Long ugId : userGroups)
		{
			query.append(" ,").append(ugId);
		}
		query.append(")");
		return query;
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
