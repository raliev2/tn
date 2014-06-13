/**
 * 
 */
package com.teamidea.platform.technonikol.storefront.controllers.util;

import com.teamidea.platform.technonikol.core.enums.TNPaymentMethodTypeEnum;



/**
 * @author Marina
 * 
 */
public class PaymentMethod
{

	private String name;
	private TNPaymentMethodTypeEnum code;

	public PaymentMethod()
	{

	}

	public PaymentMethod(final TNPaymentMethodTypeEnum code)
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
	public TNPaymentMethodTypeEnum getCode()
	{
		return code;
	}

	/**
	 * @param code
	 *           the code to set
	 */
	public void setCode(final TNPaymentMethodTypeEnum code)
	{
		this.code = code;
	}


}
