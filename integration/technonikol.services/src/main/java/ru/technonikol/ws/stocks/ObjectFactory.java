
package ru.technonikol.ws.stocks;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.technonikol.ws.stocks package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.technonikol.ws.stocks
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PocketQuery }
     * 
     */
    public PocketQuery createPocketQuery() {
        return new PocketQuery();
    }

    /**
     * Create an instance of {@link Answer }
     * 
     */
    public Answer createAnswer() {
        return new Answer();
    }

    /**
     * Create an instance of {@link SendQueryResponse }
     * 
     */
    public SendQueryResponse createSendQueryResponse() {
        return new SendQueryResponse();
    }

    /**
     * Create an instance of {@link SendQuery }
     * 
     */
    public SendQuery createSendQuery() {
        return new SendQuery();
    }

    /**
     * Create an instance of {@link MaterialsRow }
     * 
     */
    public MaterialsRow createMaterialsRow() {
        return new MaterialsRow();
    }

    /**
     * Create an instance of {@link PocketQuery.Materials }
     * 
     */
    public PocketQuery.Materials createPocketQueryMaterials() {
        return new PocketQuery.Materials();
    }

    /**
     * Create an instance of {@link Answer.Materials }
     * 
     */
    public Answer.Materials createAnswerMaterials() {
        return new Answer.Materials();
    }

}
