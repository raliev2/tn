package com.teamidea.integration.prototype.technonikol.wsclient.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.technonikol.ws.orders.MaterialsRow;
import ru.technonikol.ws.orders.Order;
import ru.technonikol.ws.orders.Order.Materials;
import ru.technonikol.ws.orders.OrdersCreateRequestOutSyn;
import ru.technonikol.ws.stocks.DeliveryDateQueryOut;

import com.teamidea.integration.prototype.technonikol.wsclient.CreateOrderSoapClient;
import com.teamidea.integration.prototype.technonikol.wsclient.DeliveryDateSoapClient;

public class CreateOrderSoapClientDemo
{
	
	private AbstractApplicationContext ctx;

	public CreateOrderSoapClientDemo(AbstractApplicationContext ctx){
		this.ctx = ctx;
	}

	public static void main(String[] args) throws IOException {
		CreateOrderSoapClientDemo demo = new CreateOrderSoapClientDemo(new ClassPathXmlApplicationContext("technonikolServicesBeans.xml"));
		OrdersCreateRequestOutSyn port = (OrdersCreateRequestOutSyn) demo.ctx.getBean("orderCreate");
		CreateOrderSoapClient client = new CreateOrderSoapClient(port);
		
		System.out.println(String.format("DeliveryDateService demo app started\n" +
				"Type help for list of commands\n"));

		String command = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		do {
			String sargs[] = reader.readLine().split("\\s+");
			command = sargs[0];
			
			switch (command) {
			case "help":
				System.out.println(String.format("Available commands:\n" +
						" * ordersCreateRequestOutSyn\n" +
						" * exit\n"));					
				break;
			case "ordersCreateRequestOutSyn":
				client.ordersCreateRequestOutSyn(getSampleOrder());				
				break;
			case "exit":
				System.out.println("Bye");
				break;
			default:
				System.out.println("Unknown command");
			}
			
		} while (!"exit".equals(command.toLowerCase()));
		
	}

	private static Order getSampleOrder() {
		Order order = new Order();
		
		order.setAddress("127555, Москва г.,,,,Мишина ул.,35 д");
		order.setAddressCode("127555");
		order.setApartment("");
		order.setBuilding("");
		order.setCity("");
		order.setCityCode("");
		order.setConstructionType("1");
		order.setCountry("RU");
		order.setCountryCode("695");
		order.setDate("20140601000000");
		order.setHouse("35");
		order.setIDPartner("4052d764-513e-11e2-be3d-005056010626");
		order.setNumber("000123456");
		order.setObjectType("664b64d0-403f-11e2-8e4e-005056010274");
		order.setPaymentType("Безналичная");
		order.setRegion("Москва г.");
		order.setRegionCode("77");
		order.setStreet("Мишина ул.");
		order.setTown("");
		order.setWarehouseGUID("d00b2bd4-3a2e-11e2-845d-005056010274");
		
		Materials materials = new Materials();
		MaterialsRow row = new MaterialsRow();
		row.setDeliveryDate("20140611090900");
		row.setDiscount("10");
		row.setEKN("448481");
		row.setLineTotal("4500");
		row.setPackage("");
		row.setPrice("500");
		row.setQnty("10");
		row.setUserGroup("");
		materials.getRow().add(row);	
		order.setMaterials(materials);
		
		return order;
	}

}
