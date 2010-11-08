//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.08 at 07:07:21 DE CET 
//


package parser.serverData.tribeRelation;

import javax.xml.bind.annotation.XmlEnum;


/**
 * <p>Java class for Race.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Race">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ELYOS"/>
 *     &lt;enumeration value="ASMODIANS"/>
 *     &lt;enumeration value="LYCAN"/>
 *     &lt;enumeration value="CONSTRUCT"/>
 *     &lt;enumeration value="CARRIER"/>
 *     &lt;enumeration value="DRAKAN"/>
 *     &lt;enumeration value="LIZARDMAN"/>
 *     &lt;enumeration value="TELEPORTER"/>
 *     &lt;enumeration value="NAGA"/>
 *     &lt;enumeration value="BROWNIE"/>
 *     &lt;enumeration value="KRALL"/>
 *     &lt;enumeration value="SHULACK"/>
 *     &lt;enumeration value="BARRIER"/>
 *     &lt;enumeration value="PC_LIGHT_CASTLE_DOOR"/>
 *     &lt;enumeration value="PC_DARK_CASTLE_DOOR"/>
 *     &lt;enumeration value="DRAGON_CASTLE_DOOR"/>
 *     &lt;enumeration value="GCHIEF_LIGHT"/>
 *     &lt;enumeration value="GCHIEF_DARK"/>
 *     &lt;enumeration value="DRAGON"/>
 *     &lt;enumeration value="OUTSIDER"/>
 *     &lt;enumeration value="RATMAN"/>
 *     &lt;enumeration value="DEMIHUMANOID"/>
 *     &lt;enumeration value="UNDEAD"/>
 *     &lt;enumeration value="BEAST"/>
 *     &lt;enumeration value="MAGICALMONSTER"/>
 *     &lt;enumeration value="ELEMENTAL"/>
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="PC_ALL"/>
 *     &lt;enumeration value="GOBLIN"/>
 *     &lt;enumeration value="GENERAL"/>
 *     &lt;enumeration value="NPC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum Race {

    ELYOS,
    ASMODIANS,
    LYCAN,
    CONSTRUCT,
    CARRIER,
    DRAKAN,
    LIZARDMAN,
    TELEPORTER,
    NAGA,
    BROWNIE,
    KRALL,
    SHULACK,
    BARRIER,
    PC_LIGHT_CASTLE_DOOR,
    PC_DARK_CASTLE_DOOR,
    DRAGON_CASTLE_DOOR,
    GCHIEF_LIGHT,
    GCHIEF_DARK,
    DRAGON,
    OUTSIDER,
    RATMAN,
    DEMIHUMANOID,
    UNDEAD,
    BEAST,
    MAGICALMONSTER,
    ELEMENTAL,
    NONE,
    PC_ALL,
    GOBLIN,
    GENERAL,
    NPC;

    public String value() {
        return name();
    }

    public static Race fromValue(String v) {
        return valueOf(v);
    }

}