//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.22 at 10:30:31 AM BRT 
//


package br.com.centralit.citsmart.rest.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CtAddServiceRequestResp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CtAddServiceRequestResp">
 *   &lt;complexContent>
 *     &lt;extension base="{}CtMessageResp">
 *       &lt;sequence>
 *         &lt;element name="ServiceRequestDest" type="{}CtServiceRequest"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CtAddServiceRequestResp", propOrder = {
    "serviceRequestDest"
})
public class CtAddServiceRequestResp
    extends CtMessageResp
{

    @XmlElement(name = "ServiceRequestDest", required = true)
    protected CtServiceRequest serviceRequestDest;

    /**
     * Gets the value of the serviceRequestDest property.
     * 
     * @return
     *     possible object is
     *     {@link CtServiceRequest }
     *     
     */
    public CtServiceRequest getServiceRequestDest() {
        return serviceRequestDest;
    }

    /**
     * Sets the value of the serviceRequestDest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CtServiceRequest }
     *     
     */
    public void setServiceRequestDest(CtServiceRequest value) {
        this.serviceRequestDest = value;
    }

}
