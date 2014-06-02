package com.teamidea.integration.prototype.technonikol.wsclient;

import ru.technonikol.ws.stocks.DeliveryDateQueryOut;
import ru.technonikol.ws.stocks.MaterialsRow;
import ru.technonikol.ws.stocks.PocketQuery;
import ru.technonikol.ws.stocks.PocketQuery.Materials;
import ru.technonikol.ws.stocks.SendQuery;
import ru.technonikol.ws.stocks.SendQueryResponse;

public class DeliveryDateSoapClient
{
	
	DeliveryDateQueryOut port;

	public DeliveryDateSoapClient(DeliveryDateQueryOut port){
		this.port = port;
	}

	public SendQueryResponse deliveryDateQueryOut(String EKN, String count, String datePost){
		SendQuery request = new SendQuery();
			
		PocketQuery pocketQuery = new PocketQuery();
		//TODO fill in data
		//pocketQuery.setAddressString("test");
		//pocketQuery.setApartment("test");
		//pocketQuery.setArea("test");
		//pocketQuery.setBuilding("test");
		//pocketQuery.setCity("test");
		//pocketQuery.setCountry("test");
		//pocketQuery.setHouse("test");
		//pocketQuery.setIDPartner("test");
		//pocketQuery.setNumberOrder("test");
		//pocketQuery.setPostIndex("test");
		//pocketQuery.setRegionCode("test");
		//pocketQuery.setRegionName("test");
		//pocketQuery.setStreet("test");
		//pocketQuery.setTown("test");
		
		Materials materials = new Materials();
		MaterialsRow row = new MaterialsRow();
		row.setEKN(EKN);
		row.setCount(count);
		row.setDatePost(datePost);
		materials.getRow().add(row);
		pocketQuery.setMaterials(materials);
				
		return port.deliveryDateQueryOut(request);
	}

}
