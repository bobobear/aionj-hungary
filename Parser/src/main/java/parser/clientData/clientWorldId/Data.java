//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.22 at 08:21:18 DE CET 
//


package parser.clientData.clientWorldId;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for Data complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Data">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="cool_time" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="enable_chase_user_by_trace" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="except_buff" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="max_move_broadcast_count" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="max_user" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="min_attack_dist_chase_user_by_trace" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="min_move_broadcast_range" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="prison" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="twin_count" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Data", propOrder = {
    "value"
})
public class Data {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "cool_time")
    protected BigInteger coolTime;
    @XmlAttribute(name = "enable_chase_user_by_trace")
    protected Boolean enableChaseUserByTrace;
    @XmlAttribute(name = "except_buff")
    protected Boolean exceptBuff;
    @XmlAttribute(required = true)
    protected Integer id;
    @XmlAttribute(name = "max_move_broadcast_count")
    protected BigInteger maxMoveBroadcastCount;
    @XmlAttribute(name = "max_user")
    protected BigInteger maxUser;
    @XmlAttribute(name = "min_attack_dist_chase_user_by_trace")
    protected BigDecimal minAttackDistChaseUserByTrace;
    @XmlAttribute(name = "min_move_broadcast_range")
    protected BigInteger minMoveBroadcastRange;
    @XmlAttribute
    protected Boolean prison;
    @XmlAttribute(name = "twin_count")
    protected BigInteger twinCount;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the coolTime property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCoolTime() {
        return coolTime;
    }

    /**
     * Sets the value of the coolTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCoolTime(BigInteger value) {
        this.coolTime = value;
    }

    /**
     * Gets the value of the enableChaseUserByTrace property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnableChaseUserByTrace() {
        return enableChaseUserByTrace;
    }

    /**
     * Sets the value of the enableChaseUserByTrace property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnableChaseUserByTrace(Boolean value) {
        this.enableChaseUserByTrace = value;
    }

    /**
     * Gets the value of the exceptBuff property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExceptBuff() {
        return exceptBuff;
    }

    /**
     * Sets the value of the exceptBuff property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExceptBuff(Boolean value) {
        this.exceptBuff = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
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
     *     {@link BigInteger }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the maxMoveBroadcastCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxMoveBroadcastCount() {
        return maxMoveBroadcastCount;
    }

    /**
     * Sets the value of the maxMoveBroadcastCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxMoveBroadcastCount(BigInteger value) {
        this.maxMoveBroadcastCount = value;
    }

    /**
     * Gets the value of the maxUser property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxUser() {
        return maxUser;
    }

    /**
     * Sets the value of the maxUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxUser(BigInteger value) {
        this.maxUser = value;
    }

    /**
     * Gets the value of the minAttackDistChaseUserByTrace property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMinAttackDistChaseUserByTrace() {
        return minAttackDistChaseUserByTrace;
    }

    /**
     * Sets the value of the minAttackDistChaseUserByTrace property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMinAttackDistChaseUserByTrace(BigDecimal value) {
        this.minAttackDistChaseUserByTrace = value;
    }

    /**
     * Gets the value of the minMoveBroadcastRange property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinMoveBroadcastRange() {
        return minMoveBroadcastRange;
    }

    /**
     * Sets the value of the minMoveBroadcastRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinMoveBroadcastRange(BigInteger value) {
        this.minMoveBroadcastRange = value;
    }

    /**
     * Gets the value of the prison property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrison() {
        return prison;
    }

    /**
     * Sets the value of the prison property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrison(Boolean value) {
        this.prison = value;
    }

    /**
     * Gets the value of the twinCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTwinCount() {
        return twinCount;
    }

    /**
     * Sets the value of the twinCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTwinCount(BigInteger value) {
        this.twinCount = value;
    }

}
