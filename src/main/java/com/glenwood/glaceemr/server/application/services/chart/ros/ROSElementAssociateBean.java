package com.glenwood.glaceemr.server.application.services.chart.ros;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.SoapElementDatalist;

/**
 * Bean for ROS Element associated element
 * @author Chandrahas
 *
 */
public class ROSElementAssociateBean {

	private String elementName;
	private String elementPrintText;
	private String elementGWID;
	private String elementType;
	private String elementValue;
	private List<SoapElementDatalist> elementShortcuts;


	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public String getElementPrintText() {
		return elementPrintText;
	}
	public void setElementPrintText(String elementPrintText) {
		this.elementPrintText = elementPrintText;
	}
	public String getElementGWID() {
		return elementGWID;
	}
	public void setElementGWID(String elementGWID) {
		this.elementGWID = elementGWID;
	}
	public String getElementType() {
		return elementType;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	public String getElementValue() {
		return elementValue;
	}
	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}
	public List<SoapElementDatalist> getElementShortcuts() {
		return elementShortcuts;
	}
	public void setElementShortcuts(List<SoapElementDatalist> elementShortcuts) {
		this.elementShortcuts = elementShortcuts;
	}
}
