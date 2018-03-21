package com.glenwood.glaceemr.server.application.services.chart.plan;

public class PlanElementBean{
	private String elementGWId;
	private String elementname;
	private String elementDescription;
	private int elementType;
	private int elementId;
	private String elementCode;
	private int elementDataType;
	public PlanElementBean(){
		elementGWId="";
		elementname="";
		elementDescription="";
		elementType=-1;
		elementId=-1;
		elementCode="";
		elementDataType=-1;
	}
	public PlanElementBean(String elementGWId, String elementname, String elementDescription, Integer elementType, String elementCode, Integer elementDataType, Integer elementId){
		this.elementGWId= elementGWId;
		this.elementname= elementname;
		this.elementDescription= elementDescription;
		this.elementType= elementType;
		this.elementCode= elementCode;
		this.elementDataType= elementDataType;
		this.elementId= elementId;
	}
	public String getElementGWId() {
		return elementGWId;
	}
	public void setElementGWId(String elementGWId) {
		this.elementGWId = elementGWId;
	}
	public String getElementname() {
		return elementname;
	}
	public void setElementname(String elementname) {
		this.elementname = elementname;
	}
	public String getElementDescription() {
		return elementDescription;
	}
	public void setElementDescription(String elementDescription) {
		this.elementDescription = elementDescription;
	}
	public int getElementType() {
		return elementType;
	}
	public void setElementType(int elementType) {
		this.elementType = elementType;
	}
	public int getElementId() {
		return elementId;
	}
	public void setElementId(int elementId) {
		this.elementId = elementId;
	}
	public String getElementCode() {
		return elementCode;
	}
	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}
	public int getElementDataType() {
	    return elementDataType;
	}
	public void setElementDataType(int elementDataType) {
	    this.elementDataType = elementDataType;
	}
}