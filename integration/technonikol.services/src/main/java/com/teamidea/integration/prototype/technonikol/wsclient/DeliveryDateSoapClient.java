package com.teamidea.integration.prototype.technonikol.wsclient;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.teamidea.integration.prototype.technonikol.wsclient.demo.DeliveryDateSoapClientDemo;

import ru.technonikol.ws.stocks.DeliveryDateQueryOut;
import ru.technonikol.ws.stocks.MaterialsRow;
import ru.technonikol.ws.stocks.PocketQuery;
import ru.technonikol.ws.stocks.PocketQuery.Materials;
import ru.technonikol.ws.stocks.SendQuery;
import ru.technonikol.ws.stocks.SendQueryResponse;


public class DeliveryDateSoapClient
{

	DeliveryDateQueryOut port;
	
	public DeliveryDateSoapClient(DeliveryDateQueryOut port)
	{
		this.port = port;
	}

	/**
	 * @param ekn
	 *           product code
	 * @param count
	 *           product quantity for order
	 * @param datePost
	 *           product delivery date
	 * @return information about product delivery information
	 */
	public SendQueryResponse deliveryDateQueryOut(String ekn, String count, String datePost)
	{
		SendQuery request = new SendQuery();

		PocketQuery pocketQuery = populatePocketQuery("", "", "", "", "", "", "", "", "", "", "", "", "", "", ekn, count, datePost);

		request.setQueryMessage(pocketQuery);
		System.out.println("Request: " + request.getQueryMessage());

		SendQueryResponse response = port.deliveryDateQueryOut(request);
		System.out.println("Response: " + response.getReturn());
		return response;
	}

	/**
	 * @param ekn
	 *           product code
	 * @param count
	 *           product quantity for order
	 * @param datePost
	 *           product delivery date
	 * @param idPartner
	 *           client group id
	 * @return information about product delivery information
	 */
	public SendQueryResponse deliveryDateQueryOut(String ekn, String count, String datePost, String idPartner)
	{
		SendQuery request = new SendQuery();

		PocketQuery pocketQuery = populatePocketQuery("", "", "", "", "", "", "", idPartner, "", "", "", "", "", "", ekn, count,
				datePost);

		request.setQueryMessage(pocketQuery);
		System.out.println("Request: " + request.getQueryMessage());

		SendQueryResponse response = port.deliveryDateQueryOut(request);
		System.out.println("Response: " + response.getReturn());
		return response;
	}

	/**
	 * @param parameters
	 *           addressString, apartment, area, building, city, country, house, idPartner, numberOrder, postIndex, regionCode,
	 *           regionName, street, town, ekn, count, datePost
	 * @return information about product delivery information
	 */
	public SendQueryResponse deliveryDateQueryOut(String... parameters)
	{
		SendQuery request = new SendQuery();

		PocketQuery pocketQuery = populatePocketQuery(parameters[0], parameters[1], parameters[2], parameters[3], parameters[4],
				parameters[5], parameters[6], parameters[7], parameters[8], parameters[9], parameters[10], parameters[11],
				parameters[12], parameters[13], parameters[14], parameters[15], parameters[16]);

		request.setQueryMessage(pocketQuery);
		System.out.println("Request: " + request.getQueryMessage());

		SendQueryResponse response = port.deliveryDateQueryOut(request);
		System.out.println("Response: " + response.getReturn());
		return response;
	}
	
	
	/**
	 * @param pocketQuery
	 *           pocketQuery
	 * @return information about product delivery information
	 */
	public SendQueryResponse deliveryDateQueryOut(PocketQuery pocketQuery)
	{
		SendQuery request = new SendQuery();

		request.setQueryMessage(pocketQuery);
		System.out.println("Request: " + request.getQueryMessage());

		SendQueryResponse response = port.deliveryDateQueryOut(request);
		System.out.println("Response: " + response.getReturn());
		return response;
	}


	public static PocketQuery populatePocketQuery(String addressString, String apartment, String area, String building,
			String city, String country, String house, String idPartner, String numberOrder, String postIndex, String regionCode,
			String regionName, String street, String town, String ekn, String count, String datePost)
	{
		PocketQuery pocketQuery = new PocketQuery();
		pocketQuery.setAddressString(addressString);
		pocketQuery.setApartment(apartment);
		pocketQuery.setArea(area);
		pocketQuery.setBuilding(building);
		pocketQuery.setCity(city);
		pocketQuery.setCountry(country);
		pocketQuery.setHouse(house);
		pocketQuery.setIDPartner(idPartner);
		pocketQuery.setNumberOrder(numberOrder);
		pocketQuery.setPostIndex(postIndex);
		pocketQuery.setRegionCode(regionCode);
		pocketQuery.setRegionName(regionName);
		pocketQuery.setStreet(street);
		pocketQuery.setTown(town);

		Materials materials = new Materials();
		MaterialsRow row = new MaterialsRow();
		row.setEKN(ekn);
		row.setCount(count);
		row.setDatePost(datePost);
		materials.getRow().add(row);
		pocketQuery.setMaterials(materials);

		return pocketQuery;
	}

}
