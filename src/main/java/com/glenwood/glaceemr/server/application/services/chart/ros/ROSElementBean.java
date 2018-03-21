package com.glenwood.glaceemr.server.application.services.chart.ros;


public class ROSElementBean {
	
	private String elementGWID;
	private String elementName;
	private String elementPrintText;
	private Integer dataType;
	private String value;
	private String associatedGWID;
	private ROSElementAssociateBean associateElement;
		
	public ROSElementBean(String elementGWID, String elementName,
			String elementPrintText, Integer dataType, String associatedGWID) {
		super();
		this.elementGWID = elementGWID;
		this.elementName = elementName;
		this.elementPrintText = elementPrintText;
		this.dataType = dataType;
		this.associatedGWID = associatedGWID;
	}

	public ROSElementBean() {
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getElementGWID(){
		return elementGWID;
	}
	
	public void setElementGWID(String elementGWID){
		this.elementGWID = elementGWID;
	}
	
	public String getElementName(){
		return elementName;
	}
	
	public void setElementName(String elementName){
		this.elementName = elementName;
	}
	
	public String getElementPrintText() {
		return elementPrintText;
	}
	
	public void setElementPrintText(String elementPrintText) {
		this.elementPrintText = elementPrintText;
	}

	public String getAssociatedGWID() {
		return associatedGWID;
	}

	public void setAssociatedGWID(String associatedGWID) {
		this.associatedGWID = associatedGWID;
	}

	public ROSElementAssociateBean getAssociateElement() {
		return associateElement;
	}

	public void setAssociateElement(ROSElementAssociateBean associateElement) {
		this.associateElement = associateElement;
	}

	
}
