//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.11.23 at 05:57:07 DE CET 
//

package parser.clientData.mission;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="Objects" type="{}ObjectsClass" maxOccurs="unbounded"/>
 *         &lt;element name="CommonShapeList" type="{}CommonShapeList" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder =
{ "objects", "commonShapeList" })
@XmlRootElement(name = "Mission")
public class Mission
{

	@XmlElement(name = "Objects", required = true)
	protected List<ObjectsClass>	objects;
	@XmlElement(name = "CommonShapeList")
	protected List<CommonShapeList>	commonShapeList;

	/**
	 * Gets the value of the objects property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the objects property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getObjects().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ObjectsClass }
	 * 
	 * 
	 */
	public List<ObjectsClass> getObjects()
	{
		if (objects == null)
		{
			objects = new ArrayList<ObjectsClass>();
		}
		return this.objects;
	}

	/**
	 * Gets the value of the commonShapeList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the commonShapeList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getCommonShapeList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link CommonShapeList }
	 * 
	 * 
	 */
	public List<CommonShapeList> getCommonShapeList()
	{
		if (commonShapeList == null)
		{
			commonShapeList = new ArrayList<CommonShapeList>();
		}
		return this.commonShapeList;
	}

}
