package com.teamidea.platform.technonikol.core.pricing;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import de.hybris.platform.b2bacceleratorservices.company.B2BCommerceB2BUserGroupService;
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
import de.hybris.platform.jalo.order.price.JaloPriceFactoryException;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.product.Unit;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.DateRange;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * Created by anton.gavazyuk on 07/04/14.
 */
public class TNPriceFactory extends CatalogAwareEurope1PriceFactory {

    private FlexibleSearchService flexibleSearchService;

    private UserService userService;

    private B2BCommerceB2BUserGroupService b2BUserGroupService;


    protected List<PriceRow> filterPriceRows4Info(final Collection<PriceRow> rows, final Currency curr, final Date date,
                                                  final PriceRowChannel channel)
    {
        if (rows.isEmpty())
        {
            return Collections.EMPTY_LIST;
        }
        else
        {
            final Currency base = curr.isBase().booleanValue() ? null : C2LManager.getInstance().getBaseCurrency();

            final List<PriceRow> returnRows = new ArrayList<PriceRow>();

            boolean hasChannelRowMatching = false;

            double minPrice = Double.MAX_VALUE;
            PriceRow selectedPriceRow = null;
            PriceRow defaultPriceRow = null;

            for(PriceRow priceRow: rows){
                final Currency currency = priceRow.getCurrency();
                if (!curr.equals(currency) && (base == null || !base.equals(currency)))
                {
                    continue; // skip if currency does not match
                }

                final DateRange dateRange = priceRow.getDateRange();
                if (dateRange != null && !dateRange.encloses(date))
                {
                    continue; // skip if date range does not match
                }
                if (priceRow.isGiveAwayPriceAsPrimitive())
                {
                    continue; // always skip give-away prices -> do not show up in info
                }

                //YTODO: Need to discuss with Business. Will validate this condition tomorrow.
				/*
				 * if (channel == null && priceRow.getChannel() != null && priceRow.getChannel().getCode() != null) {
				 * it.remove(); continue; // skip if the channel provided by the context is null and not the price row
				 * channel. }
				 */

                if (channel != null && priceRow.getChannel() != null
                        && !priceRow.getChannel().getCode().equalsIgnoreCase(channel.getCode()))
                {
                    continue; // skip if the price row channel is defined and it's not matching with the channel provided by the context.
                }

                if (channel != null && priceRow.getChannel() != null
                        && priceRow.getChannel().getCode().equalsIgnoreCase(channel.getCode()))
                {
                    // found a matching row for the given channel
                    hasChannelRowMatching = true;
                }

                if(priceRow.getPrice()<minPrice) {
                    if(priceRow.getUserGroup()!=null) {
                        minPrice = priceRow.getPrice();
                        selectedPriceRow = priceRow;
                    }else {
                        defaultPriceRow = priceRow;
                    }
                }

            }

            if(selectedPriceRow != null){
                returnRows.add(selectedPriceRow);
            } else {
                returnRows.add(defaultPriceRow);
            }



            // if there is a possibility to have price row without channel
//            if (hasChannelRowMatching && returnRows.size() > 1)
//            {
//                for (final ListIterator<PriceRow> it = ret.listIterator(); it.hasNext();)
//                {
//                    final PriceRow priceRow = it.next();
//                    if (priceRow.getChannel() == null)
//                    {
//                        it.remove();
//                        continue; // remove price row without channel
//                    }
//                }
//            }
            return returnRows;
        }
    }

    protected Collection<PriceRow> queryPriceRows4Price(final SessionContext ctx, final Product product,
                                                        final EnumerationValue productGroup, final User user, final EnumerationValue userGroup)
    {
        //
        final StringBuilder query = new StringBuilder("SELECT {").append(Item.PK).append("} FROM {").
                append(Europe1Constants.TC.PRICEROW).append(" AS pr} WHERE ");
        final Map values = new HashMap();

        // product & PG
        appendProductConditions(query, "pr", product, productGroup, values, PriceRow.PG);


        // user & UG
        query.append(" AND ");
        if(CollectionUtils.isNotEmpty((Collection)ctx.getAttribute("PRICE_LISTS"))){
            appendUserConditions(query,(Collection)ctx.getAttribute("PRICE_LISTS"),values,user);
        } else {
            appendUserConditions(query, "pr", user, userGroup, values, PriceRow.UG);
        }

//        query.append(" SORT BY {}")

        return FlexibleSearch.getInstance().search(//
                ctx, //
                query.toString(), //
                values, //
                PriceRow.class//
        ).getResult();

    }

    protected StringBuilder appendUserConditions(StringBuilder query, Collection<Long> userGroups, Map values, User user){
        query.append("{").append("pr").append(":").append(PDTRow.USERMATCHQUALIFIER).append("} IN ( ?anyU ");
        values.put("anyU", Long.valueOf(PK.NULL_PK.getLongValue()));
        if (user != null)
        {
            query.append(",?user ");
            values.put("user", Long.valueOf(user.getPK().getLongValue()));
        }

        for(Long ugId: userGroups){
           query.append(",").append(ugId);
        }
        query.append(")");
        return query;
    }

    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
