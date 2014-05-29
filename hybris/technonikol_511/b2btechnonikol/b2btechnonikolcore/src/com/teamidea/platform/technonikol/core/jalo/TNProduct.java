package com.teamidea.platform.technonikol.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.product.Unit;
import de.hybris.platform.jalo.type.ComposedType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


public class TNProduct extends GeneratedTNProduct
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(TNProduct.class.getName());

	public static final String BASEUNIT = "baseUnit";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;

	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(GeneratedTNProduct.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(BASEUNIT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}

	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}

	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes)
			throws JaloBusinessException
	{
		// business code placed here will be executed before the item is created
		// then create the item
		allAttributes.setAttributeMode(BASEUNIT, Item.AttributeMode.INITIAL);

		final Item item = super.createItem(ctx, type, allAttributes);
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}

	public Unit getBaseUnit(final SessionContext ctx)
	{
		return (Unit) getProperty(ctx, BASEUNIT);
	}

	public Unit getBaseUnit()
	{
		return getBaseUnit(getSession().getSessionContext());
	}

	public void setBaseUnit(final SessionContext ctx, final Unit value)
	{
		setProperty(ctx, BASEUNIT, value);
	}

	public void setBaseUnit(final Unit value)
	{
		setBaseUnit(getSession().getSessionContext(), value);
	}

}
