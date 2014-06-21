/**
 * 
 */
package com.teamidea.platform.technonikol.services.stock;

import ru.technonikol.ws.stocks.PocketQuery;
import ru.technonikol.ws.stocks.SendQueryResponse;



/**
 * @author Marina
 * 
 */
public interface DeliveryDateIntegrationService
{

	public abstract SendQueryResponse deliveryDateQueryOut(String ekn, String count, String datePost);

	public abstract SendQueryResponse deliveryDateQueryOut(String ekn, String count, String datePost, String idPartner);

	public abstract SendQueryResponse deliveryDateQueryOut(String... parameters);

	public abstract SendQueryResponse deliveryDateQueryOut(PocketQuery pocketQuery);

}
