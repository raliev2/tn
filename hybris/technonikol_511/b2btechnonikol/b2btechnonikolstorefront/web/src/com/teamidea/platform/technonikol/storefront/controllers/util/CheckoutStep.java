/**
 * 
 */
package com.teamidea.platform.technonikol.storefront.controllers.util;



/**
 * @author Marina
 * 
 */
public class CheckoutStep
{
	private final String name;
	private final String url;
	private final String view;
	private CheckoutStep next;
	private CheckoutStep previous;

	public CheckoutStep(final String name, final String url, final String view)
	{
		this.name = name;
		this.url = url;
		this.view = view;
	}

	public CheckoutStep(final String name, final String url, final String view, final CheckoutStep previous)
	{
		this.name = name;
		this.url = url;
		this.view = view;
		this.previous = previous;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the url
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * @return the view
	 */
	public String getView()
	{
		return view;
	}

	/**
	 * @return the next
	 */
	public CheckoutStep getNext()
	{
		return next;
	}

	/**
	 * @return the previous
	 */
	public CheckoutStep getPrevious()
	{
		return previous;
	}

	/**
	 * @param next
	 *           the next to set
	 */
	public void setNext(final CheckoutStep next)
	{
		this.next = next;
	}

	/**
	 * @param previous
	 *           the previous to set
	 */
	public void setPrevious(final CheckoutStep previous)
	{
		this.previous = previous;
	}

}