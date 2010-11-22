//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.17 at 07:33:37 DE CET 
//


package parser.serverData.objectInfos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NpcInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NpcInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="class_name" type="{http://www.w3.org/2001/XMLSchema}string" default="SpawnedObject" />
 *       &lt;attribute name="knowlist_name" type="{http://www.w3.org/2001/XMLSchema}string" default="KnownList" />
 *       &lt;attribute name="ai_name" type="{http://www.w3.org/2001/XMLSchema}string" default="NoActionAI" />
 *       &lt;attribute name="random_walking" type="{http://www.w3.org/2001/XMLSchema}string" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NpcInfo")
public class NpcInfo {

    @XmlAttribute
    protected Integer id;
    @XmlAttribute(name = "class_name")
    protected String className;
    @XmlAttribute(name = "knowlist_name")
    protected String knowlistName;
    @XmlAttribute(name = "ai_name")
    protected String aiName;
    @XmlAttribute(name = "random_walking")
    protected String randomWalking;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the className property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassName() {
        if (className == null) {
            return "SpawnedObject";
        } else {
            return className;
        }
    }

    /**
     * Sets the value of the className property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassName(String value) {
        this.className = value;
    }

    /**
     * Gets the value of the knowlistName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKnowlistName() {
        if (knowlistName == null) {
            return "KnownList";
        } else {
            return knowlistName;
        }
    }

    /**
     * Sets the value of the knowlistName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKnowlistName(String value) {
        this.knowlistName = value;
    }

    /**
     * Gets the value of the aiName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAiName() {
        if (aiName == null) {
            return "NoActionAI";
        } else {
            return aiName;
        }
    }

    /**
     * Sets the value of the aiName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAiName(String value) {
        this.aiName = value;
    }

    /**
     * Gets the value of the randomWalking property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRandomWalking() {
        if (randomWalking == null) {
            return "false";
        } else {
            return randomWalking;
        }
    }

    /**
     * Sets the value of the randomWalking property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRandomWalking(String value) {
        this.randomWalking = value;
    }

}