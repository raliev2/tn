package com.teamidea.platform.technonikol.services.order.impl;

import de.hybris.platform.b2bacceleratorservices.order.impl.DefaultB2BCommerceCartService;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import org.apache.log4j.Logger;


public class TechnonikolCommerceCartService extends DefaultB2BCommerceCartService
{

	private static final Logger LOG = Logger.getLogger(TechnonikolCommerceCartService.class);

	private CartService cartService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.commerceservices.order.impl.DefaultCommerceCartService#addToCart(de.hybris.platform.core.model
	 * .order.CartModel, de.hybris.platform.core.model.product.ProductModel, long,
	 * de.hybris.platform.core.model.product.UnitModel, boolean)
	 */
	@Override
	public CommerceCartModification addToCart(final CartModel cartModel, final ProductModel productModel,
			final long quantityToAdd, final UnitModel unit, final boolean forceNewEntry) throws CommerceCartModificationException
	{
		try
		{
			return super.addToCart(cartModel, productModel, quantityToAdd, unit, forceNewEntry);
		}
		catch (final Exception e)
		{
			LOG.error("Undefined cart modification exception caught", e);

			lookupLostEntity(cartModel, productModel);

			throw new CommerceCartModificationException("Unknow exception: " + e.getMessage());
			//			final CommerceCartModification modification = new CommerceCartModification();
			//			modification.setStatusCode("unavailable");
			//			modification.setQuantityAdded(0L);
			//			modification.setQuantity(quantityToAdd);
			//			final CartEntryModel entry = new CartEntryModel();
			//			entry.setProduct(productModel);
			//			modification.setEntry(entry);
			//			return modification;
		}
	}

	/**
	 * @param cartModel
	 * @param productModel
	 */
	private void lookupLostEntity(final CartModel cartModel, final ProductModel productModel)
			throws CommerceCartModificationException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(">> lookupLostEntity");
		}
		for (final CartEntryModel entryModel : getCartService().getEntriesForProduct(cartModel, productModel))
		{
			if (isZeroValue(entryModel.getBasePrice()) && isZeroValue(entryModel.getTotalPrice())
					&& isZeroValue(entryModel.getDiscountValues()))
			{
				// Lost entity found. Remove it
				LOG.error("Try remove broken entity");
				updateQuantityForCartEntry(cartModel, entryModel.getEntryNumber(), 0);
			}
		}
		if (LOG.isDebugEnabled())
		{
			LOG.debug("<< lookupLostEntity");
		}
	}

	private boolean isZeroValue(final Double value)
	{
		return value == null || value.intValue() == 0;
	}

	private boolean isZeroValue(final java.util.List value)
	{
		return value == null || value.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.commerceservices.order.impl.DefaultCommerceCartService#addToCart(de.hybris.platform.core.model
	 * .order.CartModel, de.hybris.platform.core.model.product.ProductModel,
	 * de.hybris.platform.storelocator.model.PointOfServiceModel, long, de.hybris.platform.core.model.product.UnitModel,
	 * boolean)
	 */
	@Override
	public CommerceCartModification addToCart(final CartModel cartModel, final ProductModel productModel,
			final PointOfServiceModel deliveryPointOfService, final long quantityToAdd, final UnitModel unit,
			final boolean forceNewEntry) throws CommerceCartModificationException
	{
		// YTODO Auto-generated method stub
		return super.addToCart(cartModel, productModel, deliveryPointOfService, quantityToAdd, unit, forceNewEntry);
	}

	@Override
	public CartService getCartService()
	{
		return cartService;
	}

	@Override
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}


}
