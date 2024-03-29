
package ru.technonikol.ws.orders;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "OrdersCreateRequest_OutSyn", targetNamespace = "http://tn.ru/po/HYB/Order")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface OrdersCreateRequestOutSyn {


    /**
     * 
     * @param parameters
     * @return
     *     returns ru.tn.po.uts.orders.SendOrderSAPResponse
     */
    @WebMethod(operationName = "OrdersCreateRequest_OutSyn", action = "http://sap.com/xi/WebService/soap1.1")
    @WebResult(name = "SendOrderSAPResponse", targetNamespace = "http://tn.ru/po/UTS/Orders", partName = "parameters")
    public SendOrderSAPResponse ordersCreateRequestOutSyn(
        @WebParam(name = "SendOrderSAP", targetNamespace = "http://tn.ru/po/UTS/Orders", partName = "parameters")
        SendOrderSAP parameters);

}
