/**
 * 
 */
package com.teamidea.platform.technonikol.storefront.controllers.util;

import com.teamidea.platform.technonikol.core.enums.TNDeliveryModeTypeEnum;


/**
 * @author Marina
 * 
 */
public class DeliveryMode
{
	private String name;
	private String description;
	private TNDeliveryModeTypeEnum code;

	public DeliveryMode()
	{

	}

	public DeliveryMode(final TNDeliveryModeTypeEnum code)
	{
		this.name = "checkout.delivery.mode." + code.name().toLowerCase();
		this.name = "checkout.delivery.mode." + code.name().toLowerCase().trim() + ".description";
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
	public TNDeliveryModeTypeEnum getCode()
	{
		return code;
	}

	/**
	 * @param code
	 *           the code to set
	 */
	public void setCode(final TNDeliveryModeTypeEnum code)
	{
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *           the description to set
	 */
	public void setDescription(final String description)
	{
		this.description = description;
	}


}
