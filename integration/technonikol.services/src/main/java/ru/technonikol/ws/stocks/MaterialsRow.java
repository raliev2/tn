
package ru.technonikol.ws.stocks;

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
 *         &lt;element name="Count" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="DatePost" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
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
    "count",
    "datePost"
})
public class MaterialsRow {

    @XmlElement(name = "EKN", required = true)
    protected String ekn;
    @XmlElement(name = "Count", required = true)
    protected String count;
    @XmlElement(name = "DatePost", required = true)
    protected String datePost;

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
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCount(String value) {
        this.count = value;
    }

    /**
     * Gets the value of the datePost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatePost() {
        return datePost;
    }

    /**
     * Sets the value of the datePost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatePost(String value) {
        this.datePost = value;
    }

}
