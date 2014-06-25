
package ru.technonikol.ws.orders;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderSAPMsg" type="{http://tn.ru/po/UTS/Orders}Order" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "orderSAPMsg"
})
@XmlRootElement(name = "SendOrderSAP")
public class SendOrderSAP {

    @XmlElement(name = "OrderSAPMsg", required = true)
    protected Order orderSAPMsg;

    /**
     * Gets the value of the orderSAPMsg property.
     * 
     * @return
     *     possible object is
     *     {@link Order }
     *     
     */
    public Order getOrderSAPMsg() {
        return orderSAPMsg;
    }

    /**
     * Sets the value of the orderSAPMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order }
     *     
     */
    public void setOrderSAPMsg(Order value) {
        this.orderSAPMsg = value;
    }

}
