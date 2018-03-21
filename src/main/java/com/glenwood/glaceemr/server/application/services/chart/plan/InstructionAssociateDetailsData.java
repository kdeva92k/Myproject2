package com.glenwood.glaceemr.server.application.services.chart.plan;

import java.util.List;


public class InstructionAssociateDetailsData{

	String printtext;
	String elementname;
	String elementgwid;
	String value;
	String clinicalelementtype;
	Integer popup;
	String isdate;
	String associateelement;
	List<QuickNotesData> shortcuts=null;
	
	public String getPrinttext() {
		return printtext;
	}
	public void setPrinttext(String printtext) {
		this.printtext = printtext;
	}
	public String getElementname() {
		return elementname;
	}
	public void setElementname(String elementname) {
		this.elementname = elementname;
	}
	public String getElementgwid() {
		return elementgwid;
	}
	public void setElementgwid(String elementgwid) {
		this.elementgwid = elementgwid;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getClinicalelementtype() {
		return clinicalelementtype;
	}
	public void setClinicalelementtype(String clinicalelementtype) {
		this.clinicalelementtype = clinicalelementtype;
	}
	public Integer getPopup() {
		return popup;
	}
	public void setPopup(Integer popup) {
		this.popup = popup;
	}
	public String getIsdate() {
		return isdate;
	}
	public void setIsdate(String isdate) {
		this.isdate = isdate;
	}
	public String getAssociateelement() {
		return associateelement;
	}
	public void setAssociateelement(String associateelement) {
		this.associateelement = associateelement;
	}
	public List<QuickNotesData> getShortcuts() {
		return shortcuts;
	}
	public void setShortcuts(List<QuickNotesData> shortcuts) {
		this.shortcuts = shortcuts;
	}
	
}
