package com.glenwood.glaceemr.server.application.services.chart.Examination;

import java.util.LinkedHashMap;

public class ExaminationElementBean{
	private int elementId;
	private String elementGWID;
	private String elementName;
	private String elementPrintText;
	private String associatedGWID;
	private String focalElement;
	private String isNameNeededInPrint;
	LinkedHashMap<Integer, ExaminationDetailBean> examinationDetails;
	public ExaminationElementBean(){
		elementGWID="";
		elementName="";
		elementPrintText="";
		associatedGWID="";
		focalElement="";
	}

	public int getElementId() {
		return elementId;
	}
	
	public void setElementId(int elementId) {
		this.elementId = elementId;
	}
	
	public String getAssociatedGWID() {
		return associatedGWID;
	}
	
	public void setAssociatedGWID(String associatedGWID) {
		this.associatedGWID = associatedGWID;
	}
	
	public String getElementGWID() {
		return elementGWID;
	}
	
	public void setElementGWID(String elementGWID) {
		this.elementGWID = elementGWID;
	}
	
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
	
	public String getFocalElement() {
		return focalElement;
	}
	
	public void setFocalElement(String focalElement) {
		this.focalElement = focalElement;
	}
	
	public String getNameNeededInPrint() {
		return isNameNeededInPrint;
	}
	
	public void setNameNeededInPrint(String isNameNeededInPrint) {
		this.isNameNeededInPrint = isNameNeededInPrint;
	}
	
	public LinkedHashMap<Integer, ExaminationDetailBean> getExaminationDetails() {
		return examinationDetails;
	}
	
	public void setExaminationDetails(
			LinkedHashMap<Integer, ExaminationDetailBean> examinationDetails) {
		this.examinationDetails = examinationDetails;
	}
}