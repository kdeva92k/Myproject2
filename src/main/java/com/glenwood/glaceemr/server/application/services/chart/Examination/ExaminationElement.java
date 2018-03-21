package com.glenwood.glaceemr.server.application.services.chart.Examination;

import java.util.ArrayList;
import java.util.List;

public class ExaminationElement {
	
	String value;
	String elementPrintText;
	String elementGwid;
	Integer dataType;
	String elementName;
	
	
	List<AssociateElement> associate=new ArrayList<AssociateElement>();
	List<ElementDetail> elementDetails=new ArrayList<ElementDetail>();
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getElementPrintText() {
		return elementPrintText;
	}
	public void setElementPrintText(String elementPrintText) {
		this.elementPrintText = elementPrintText;
	}
	public String getElementGwid() {
		return elementGwid;
	}
	public void setElementGwid(String elementGwid) {
		this.elementGwid = elementGwid;
	}
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public List<AssociateElement> getAssociate() {
		return associate;
	}
	public void setAssociate(List<AssociateElement> associate) {
		this.associate = associate;
	}
	public List<ElementDetail> getElementDetails() {
		return elementDetails;
	}
	public void setElementDetails(List<ElementDetail> elementDetails) {
		this.elementDetails = elementDetails;
	}
	
	
	
	
	
}
