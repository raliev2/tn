package com.teamidea.integration.prototype.technonikol.wsclient;

import ru.technonikol.ws.orders.Order;
import ru.technonikol.ws.orders.OrdersCreateRequestOutSyn;
import ru.technonikol.ws.orders.SendOrderSAP;
import ru.technonikol.ws.orders.SendOrderSAPResponse;

public class CreateOrderSoapClient {
	
	OrdersCreateRequestOutSyn port;
	
	public CreateOrderSoapClient(OrdersCreateRequestOutSyn port)
	{
		this.port = port;
	}

	/**
	 * @param order
	 *           order
	 * @return order creation status
	 */
	public SendOrderSAPResponse ordersCreateRequestOutSyn(Order order)
	{
		SendOrderSAP request = new SendOrderSAP();
		request.setOrderSAPMsg(order);
		
		System.out.println("Request: " + request.getOrderSAPMsg());
		
		SendOrderSAPResponse response = port.ordersCreateRequestOutSyn(request);
		
		System.out.println("Response: " + response.getReturn());
		return response;
	}

}
