/**
 * 
 */
package com.teamidea.platform.technonikol.storefront.controllers.util;

import com.teamidea.platform.technonikol.core.enums.TNDeliveryMethodTypeEnum;


/**
 * @author Marina
 * 
 */
public class DeliveryMethod
{

	private String name;
	private TNDeliveryMethodTypeEnum code;

	public DeliveryMethod()
	{

	}

	public DeliveryMethod(final TNDeliveryMethodTypeEnum code)
	{
		this.name = "checkout.delivery.method." + code.name().toLowerCase();
		this.code = code;
	}


	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *           the name to set
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public TNDeliveryMethodTypeEnum getCode()
	{
		return code;
	}

	/**
	 * @param code
	 *           the code to set
	 */
	public void setCode(final TNDeliveryMethodTypeEnum code)
	{
		this.code = code;
	}


}
