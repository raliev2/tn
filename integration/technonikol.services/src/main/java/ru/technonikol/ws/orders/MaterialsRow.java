
package ru.technonikol.ws.orders;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MaterialsRow complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MaterialsRow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EKN" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="Qnty" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="Price" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="Discount" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="LineTotal" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="UserGroup" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="Package" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="DeliveryDate" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MaterialsRow", propOrder = {
    "ekn",
    "qnty",
    "price",
    "discount",
    "lineTotal",
    "userGroup",
    "_package",
    "deliveryDate"
})
public class MaterialsRow {

    @XmlElement(name = "EKN", required = true)
    protected String ekn;
    @XmlElement(name = "Qnty", required = true)
    protected String qnty;
    @XmlElement(name = "Price", required = true)
    protected String price;
    @XmlElement(name = "Discount", required = true)
    protected String discount;
    @XmlElement(name = "LineTotal", required = true)
    protected String lineTotal;
    @XmlElement(name = "UserGroup", required = true)
    protected String userGroup;
    @XmlElement(name = "Package", required = true)
    protected String _package;
    @XmlElement(name = "DeliveryDate", required = true)
    protected String deliveryDate;

    /**
     * Gets the value of the ekn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEKN() {
        return ekn;
    }

    /**
     * Sets the value of the ekn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEKN(String value) {
        this.ekn = value;
    }

    /**
     * Gets the value of the qnty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQnty() {
        return qnty;
    }

    /**
     * Sets the value of the qnty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQnty(String value) {
        this.qnty = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrice(String value) {
        this.price = value;
    }

    /**
     * Gets the value of the discount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscount() {
        return discount;
    }

    /**
     * Sets the value of the discount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscount(String value) {
        this.discount = value;
    }

    /**
     * Gets the value of the lineTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineTotal() {
        return lineTotal;
    }

    /**
     * Sets the value of the lineTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineTotal(String value) {
        this.lineTotal = value;
    }

    /**
     * Gets the value of the userGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserGroup() {
        return userGroup;
    }

    /**
     * Sets the value of the userGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserGroup(String value) {
        this.userGroup = value;
    }

    /**
     * Gets the value of the package property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackage() {
        return _package;
    }

    /**
     * Sets the value of the package property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackage(String value) {
        this._package = value;
    }

    /**
     * Gets the value of the deliveryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Sets the value of the deliveryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryDate(String value) {
        this.deliveryDate = value;
    }

}
