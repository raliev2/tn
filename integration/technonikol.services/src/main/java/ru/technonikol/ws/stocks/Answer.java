
package ru.technonikol.ws.stocks;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ru.technonikol.ws.stocks.PocketQuery.Materials;


/**
 * <p>Java class for Answer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Answer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumberOrder" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="IDPartner" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="AddressString" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="Materials" minOccurs="0" form="qualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Row" type="{http://tn.ru/po/UTS/DeliveryCost}MaterialsRow" maxOccurs="unbounded" minOccurs="0" form="qualified"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Answer", propOrder = {
    "numberOrder",
    "idPartner",
    "addressString",
    "materials"
})
public class Answer {

    @XmlElement(name = "NumberOrder", required = true)
    protected String numberOrder;
    @XmlElement(name = "IDPartner", required = true)
    protected String idPartner;
    @XmlElement(name = "AddressString", required = true)
    protected String addressString;
    @XmlElement(name = "Materials")
    protected Answer.Materials materials;

    /**
     * Gets the value of the numberOrder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOrder() {
        return numberOrder;
    }

    /**
     * Sets the value of the numberOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOrder(String value) {
        this.numberOrder = value;
    }

    /**
     * Gets the value of the idPartner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDPartner() {
        return idPartner;
    }

    /**
     * Sets the value of the idPartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDPartner(String value) {
        this.idPartner = value;
    }

    /**
     * Gets the value of the addressString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressString() {
        return addressString;
    }

    /**
     * Sets the value of the addressString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressString(String value) {
        this.addressString = value;
    }

    /**
     * Gets the value of the materials property.
     * 
     * @return
     *     possible object is
     *     {@link Answer.Materials }
     *     
     */
    public Answer.Materials getMaterials() {
        return materials;
    }

    /**
     * Sets the value of the materials property.
     * 
     * @param value
     *     allowed object is
     *     {@link Answer.Materials }
     *     
     */
    public void setMaterials(Answer.Materials value) {
        this.materials = value;
    }


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
     *         &lt;element name="Row" type="{http://tn.ru/po/UTS/DeliveryCost}MaterialsRow" maxOccurs="unbounded" minOccurs="0" form="qualified"/>
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
        "row"
    })
    public static class Materials {

        @XmlElement(name = "Row")
        protected List<MaterialsRow> row;

        /**
         * Gets the value of the row property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the row property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRow().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MaterialsRow }
         * 
         * 
         */
        public List<MaterialsRow> getRow() {
            if (row == null) {
                row = new ArrayList<MaterialsRow>();
            }
            return this.row;
        }

    }
    
    @Override
    public String toString(){
   	 StringBuilder answer = new StringBuilder();
   	 answer.append("Full address: " + this.getAddressString() + "; ");
   	 answer.append("IDPartner: " + this.getIDPartner() + "; ");
   	 answer.append("NumberOrder: " + this.getNumberOrder() + "; ");

   	 Materials materials = this.getMaterials();
   	 for(MaterialsRow row: materials.getRow()){
   		 answer.append("{");
   		 answer.append("EKN: " + row.getEKN() + "; ");
   		 answer.append("Count: " + row.getCount() + "; ");
   		 answer.append("DatePost: " + row.getDatePost() + ";");
   		 answer.append("}; ");
   	 }
   	 
   	 return answer.toString();
    }

}
