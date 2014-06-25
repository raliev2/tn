
package ru.technonikol.ws.orders;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for Order complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Order">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Number" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="IDPartner" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="PaymentType" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="ObjectType" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="ConstructionType" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="Region" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="RegionCode" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="Address" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="CountryCode" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="CityCode" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="AddressCode" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="Town" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="Street" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="House" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="Building" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="Apartment" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="WarehouseGUID" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="Materials" minOccurs="0" form="qualified">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Row" type="{http://tn.ru/po/UTS/Orders}MaterialsRow" maxOccurs="unbounded" minOccurs="0" form="qualified"/>
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
@XmlType(name = "Order", propOrder = {
    "number",
    "date",
    "idPartner",
    "paymentType",
    "objectType",
    "constructionType",
    "country",
    "region",
    "regionCode",
    "city",
    "address",
    "countryCode",
    "cityCode",
    "addressCode",
    "town",
    "street",
    "house",
    "building",
    "apartment",
    "warehouseGUID",
    "materials"
})
public class Order {

    @XmlElement(name = "Number", required = true)
    protected String number;
    @XmlElement(name = "Date", required = true)
    protected String date;
    @XmlElement(name = "IDPartner", required = true)
    protected String idPartner;
    @XmlElement(name = "PaymentType", required = true)
    protected String paymentType;
    @XmlElement(name = "ObjectType", required = true)
    protected String objectType;
    @XmlElement(name = "ConstructionType", required = true)
    protected String constructionType;
    @XmlElement(name = "Country", required = true)
    protected String country;
    @XmlElement(name = "Region", required = true)
    protected String region;
    @XmlElement(name = "RegionCode", required = true)
    protected String regionCode;
    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "Address", required = true)
    protected String address;
    @XmlElement(name = "CountryCode", required = true)
    protected String countryCode;
    @XmlElement(name = "CityCode", required = true)
    protected String cityCode;
    @XmlElement(name = "AddressCode", required = true)
    protected String addressCode;
    @XmlElement(name = "Town", required = true)
    protected String town;
    @XmlElement(name = "Street", required = true)
    protected String street;
    @XmlElement(name = "House", required = true)
    protected String house;
    @XmlElement(name = "Building", required = true)
    protected String building;
    @XmlElement(name = "Apartment", required = true)
    protected String apartment;
    @XmlElement(name = "WarehouseGUID", required = true)
    protected String warehouseGUID;
    @XmlElement(name = "Materials")
    protected Order.Materials materials;

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumber(String value) {
        this.number = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
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
     * Gets the value of the paymentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the value of the paymentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentType(String value) {
        this.paymentType = value;
    }

    /**
     * Gets the value of the objectType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * Sets the value of the objectType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectType(String value) {
        this.objectType = value;
    }

    /**
     * Gets the value of the constructionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstructionType() {
        return constructionType;
    }

    /**
     * Sets the value of the constructionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstructionType(String value) {
        this.constructionType = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the region property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the value of the region property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegion(String value) {
        this.region = value;
    }

    /**
     * Gets the value of the regionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * Sets the value of the regionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegionCode(String value) {
        this.regionCode = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the cityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Sets the value of the cityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityCode(String value) {
        this.cityCode = value;
    }

    /**
     * Gets the value of the addressCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressCode() {
        return addressCode;
    }

    /**
     * Sets the value of the addressCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressCode(String value) {
        this.addressCode = value;
    }

    /**
     * Gets the value of the town property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTown() {
        return town;
    }

    /**
     * Sets the value of the town property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTown(String value) {
        this.town = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the house property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouse() {
        return house;
    }

    /**
     * Sets the value of the house property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouse(String value) {
        this.house = value;
    }

    /**
     * Gets the value of the building property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Sets the value of the building property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuilding(String value) {
        this.building = value;
    }

    /**
     * Gets the value of the apartment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApartment() {
        return apartment;
    }

    /**
     * Sets the value of the apartment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApartment(String value) {
        this.apartment = value;
    }

    /**
     * Gets the value of the warehouseGUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarehouseGUID() {
        return warehouseGUID;
    }

    /**
     * Sets the value of the warehouseGUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarehouseGUID(String value) {
        this.warehouseGUID = value;
    }

    /**
     * Gets the value of the materials property.
     * 
     * @return
     *     possible object is
     *     {@link Order.Materials }
     *     
     */
    public Order.Materials getMaterials() {
        return materials;
    }

    /**
     * Sets the value of the materials property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order.Materials }
     *     
     */
    public void setMaterials(Order.Materials value) {
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
     *         &lt;element name="Row" type="{http://tn.ru/po/UTS/Orders}MaterialsRow" maxOccurs="unbounded" minOccurs="0" form="qualified"/>
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
    
    public String toString(){
      	 StringBuilder pocketQuery = new StringBuilder();
      	 
       	 pocketQuery.append("Full address: " + this.getAddress() + "; ");
       	 pocketQuery.append("Address code: " + this.getAddressCode() + "; ");
      	 pocketQuery.append("Apartment: " + this.getApartment() + "; ");   	 
       	 pocketQuery.append("Building: " + this.getBuilding() + "; ");  	   	 
       	 pocketQuery.append("City: " + this.getCity() + "; ");
       	 pocketQuery.append("City Code: " + this.getCityCode() + "; ");
       	 pocketQuery.append("Construction Type: " + this.getConstructionType() + "; ");
       	 pocketQuery.append("Country: " + this.getCountry() + "; ");         	     	 
       	 pocketQuery.append("Country Code: " + this.getCountryCode() + "; ");  
       	 pocketQuery.append("Date: " + this.getDate() + "; "); 	
       	 pocketQuery.append("House: " + this.getHouse() + "; "); 	 
       	 pocketQuery.append("IDPartner: " + this.getIDPartner() + "; ");    
       	 pocketQuery.append("Number: " + this.getNumber() + "; ");      
       	 pocketQuery.append("Object Type: " + this.getObjectType()+ "; ");  	 
       	 pocketQuery.append("Payment Type: " + this.getPaymentType() + "; ");  
       	 pocketQuery.append("Region Code: " + this.getRegionCode() + "; ");
       	 pocketQuery.append("Region: " + this.getRegion()+ "; ");  	      	 
       	 pocketQuery.append("Street: " + this.getStreet() + "; "); 	
       	 pocketQuery.append("Town: " + this.getTown()+ "; ");    
       	 pocketQuery.append("Warehouse GUID: " + this.getWarehouseGUID() + "; ");   	 

       	 Materials materials = this.getMaterials();
       	 for(MaterialsRow row: materials.getRow()){
       		 pocketQuery.append("{");
       		 pocketQuery.append("Delivery Date: " + row.getDeliveryDate() + "; ");
       		 pocketQuery.append("Discount: " + row.getDiscount() + "; ");
       		 pocketQuery.append("EKN: " + row.getEKN() + "; ");
       		 pocketQuery.append("Line Total: " + row.getLineTotal() + "; ");
       		 pocketQuery.append("Package: " + row.getPackage() + "; ");
       		 pocketQuery.append("Price: " + row.getPrice() + "; ");
       		 pocketQuery.append("Qnty: " + row.getQnty() + "; ");
       		 pocketQuery.append("User Group: " + row.getUserGroup() + "; ");     		 
       		 pocketQuery.append("}; ");
       	 }
       	 
       	 return pocketQuery.toString();	
    }

}
