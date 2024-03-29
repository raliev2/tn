/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.teamidea.platform.technonikol.storefront.forms;

import javax.validation.constraints.Size;


/**
 * Pojo for collection address form.
 */
public class CheckoutAddressForm
{
	private String addressId;
	private String firstName;
	private String lastName;
	private String phone;
	private String townCity;
	private String postcode;
	private String street;
	private String house;
	private String countryIso;
	private String email;

	public String getAddressId()
	{
		return addressId;
	}

	public void setAddressId(final String addressId)
	{
		this.addressId = addressId;
	}

	/**
	 * @return the firstName
	 */
	@Size(min = 1, max = 255, message = "{address.firstName.invalid}")
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName
	 *           the firstName to set
	 */
	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	@Size(min = 1, max = 255, message = "{address.lastName.invalid}")
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName
	 *           the lastName to set
	 */
	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return the townCity
	 */
	@Size(min = 1, max = 255, message = "{address.townCity.invalid}")
	public String getTownCity()
	{
		return townCity;
	}

	/**
	 * @param townCity
	 *           the townCity to set
	 */
	public void setTownCity(final String townCity)
	{
		this.townCity = townCity;
	}

	/**
	 * @return the postcode
	 */
	@Size(min = 1, max = 10, message = "{address.postcode.invalid}")
	public String getPostcode()
	{
		return postcode;
	}

	/**
	 * @param postcode
	 *           the postcode to set
	 */
	public void setPostcode(final String postcode)
	{
		this.postcode = postcode;
	}

	/**
	 * @return the countryIso
	 */
	@Size(min = 1, max = 255, message = "{address.country.invalid}")
	public String getCountryIso()
	{
		return countryIso;
	}

	/**
	 * @param countryIso
	 *           the countryIso to set
	 */
	public void setCountryIso(final String countryIso)
	{
		this.countryIso = countryIso;
	}

	/**
	 * @return the phone
	 */
	@Size(min = 1, max = 255, message = "{address.phone.invalid}")
	public String getPhone()
	{
		return phone;
	}

	/**
	 * @param phone
	 *           the phone to set
	 */
	public void setPhone(final String phone)
	{
		this.phone = phone;
	}

	/**
	 * @return the street
	 */
	@Size(min = 1, max = 255, message = "{address.street.invalid}")
	public String getStreet()
	{
		return street;
	}

	/**
	 * @param street
	 *           the street to set
	 */
	public void setStreet(final String street)
	{
		this.street = street;
	}

	/**
	 * @return the houseAndApt
	 */
	@Size(min = 1, max = 255, message = "{address.houseAndApt.invalid}")
	public String getHouse()
	{
		return house;
	}

	/**
	 * @param houseAndApt
	 *           the houseAndApt to set
	 */
	public void setHouse(final String house)
	{
		this.house = house;
	}

	/**
	 * @return the email
	 */
	@Size(min = 1, max = 255, message = "{address.houseAndApt.invalid}")
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 *           the email to set
	 */
	public void setEmail(final String email)
	{
		this.email = email;
	}

}
