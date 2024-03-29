
package ru.technonikol.ws.orders;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "OrdersCreateRequest_OutSynService", targetNamespace = "http://tn.ru/po/HYB/Order", wsdlLocation = "file:/C:/Program%20Files/Java/jdk1.7.0_55/bin/CreateOrderService.wsdl")
public class OrdersCreateRequestOutSynService
    extends Service
{

    private final static URL ORDERSCREATEREQUESTOUTSYNSERVICE_WSDL_LOCATION;
    private final static WebServiceException ORDERSCREATEREQUESTOUTSYNSERVICE_EXCEPTION;
    private final static QName ORDERSCREATEREQUESTOUTSYNSERVICE_QNAME = new QName("http://tn.ru/po/HYB/Order", "OrdersCreateRequest_OutSynService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Program%20Files/Java/jdk1.7.0_55/bin/CreateOrderService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ORDERSCREATEREQUESTOUTSYNSERVICE_WSDL_LOCATION = url;
        ORDERSCREATEREQUESTOUTSYNSERVICE_EXCEPTION = e;
    }

    public OrdersCreateRequestOutSynService() {
        super(__getWsdlLocation(), ORDERSCREATEREQUESTOUTSYNSERVICE_QNAME);
    }

    public OrdersCreateRequestOutSynService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ORDERSCREATEREQUESTOUTSYNSERVICE_QNAME, features);
    }

    public OrdersCreateRequestOutSynService(URL wsdlLocation) {
        super(wsdlLocation, ORDERSCREATEREQUESTOUTSYNSERVICE_QNAME);
    }

    public OrdersCreateRequestOutSynService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ORDERSCREATEREQUESTOUTSYNSERVICE_QNAME, features);
    }

    public OrdersCreateRequestOutSynService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public OrdersCreateRequestOutSynService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns OrdersCreateRequestOutSyn
     */
    @WebEndpoint(name = "HTTP_Port")
    public OrdersCreateRequestOutSyn getHTTPPort() {
        return super.getPort(new QName("http://tn.ru/po/HYB/Order", "HTTP_Port"), OrdersCreateRequestOutSyn.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OrdersCreateRequestOutSyn
     */
    @WebEndpoint(name = "HTTP_Port")
    public OrdersCreateRequestOutSyn getHTTPPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://tn.ru/po/HYB/Order", "HTTP_Port"), OrdersCreateRequestOutSyn.class, features);
    }

    /**
     * 
     * @return
     *     returns OrdersCreateRequestOutSyn
     */
    @WebEndpoint(name = "HTTPS_Port")
    public OrdersCreateRequestOutSyn getHTTPSPort() {
        return super.getPort(new QName("http://tn.ru/po/HYB/Order", "HTTPS_Port"), OrdersCreateRequestOutSyn.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns OrdersCreateRequestOutSyn
     */
    @WebEndpoint(name = "HTTPS_Port")
    public OrdersCreateRequestOutSyn getHTTPSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://tn.ru/po/HYB/Order", "HTTPS_Port"), OrdersCreateRequestOutSyn.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ORDERSCREATEREQUESTOUTSYNSERVICE_EXCEPTION!= null) {
            throw ORDERSCREATEREQUESTOUTSYNSERVICE_EXCEPTION;
        }
        return ORDERSCREATEREQUESTOUTSYNSERVICE_WSDL_LOCATION;
    }

}
