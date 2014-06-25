/**
 * 
 */
package com.teamidea.platform.technonikol.services.order;

import ru.technonikol.ws.orders.Order;
import ru.technonikol.ws.orders.SendOrderSAPResponse;


/**
 * @author Marina
 * 
 */
public interface CreateOrderIntegrationService
{

	public abstract SendOrderSAPResponse ordersCreateRequestOutSyn(Order order);

}
