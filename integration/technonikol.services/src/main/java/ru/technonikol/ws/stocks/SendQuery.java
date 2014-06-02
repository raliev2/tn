
package ru.technonikol.ws.stocks;

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
 *         &lt;element name="QueryMessage" type="{http://tn.ru/po/UTS/DeliveryCost}PocketQuery" form="qualified"/>
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
    "queryMessage"
})
@XmlRootElement(name = "SendQuery")
public class SendQuery {

    @XmlElement(name = "QueryMessage", required = true)
    protected PocketQuery queryMessage;

    /**
     * Gets the value of the queryMessage property.
     * 
     * @return
     *     possible object is
     *     {@link PocketQuery }
     *     
     */
    public PocketQuery getQueryMessage() {
        return queryMessage;
    }

    /**
     * Sets the value of the queryMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link PocketQuery }
     *     
     */
    public void setQueryMessage(PocketQuery value) {
        this.queryMessage = value;
    }

}
