package com.glenwood.glaceemr.server.application.services.chart.plan;

public class InstructionElementDetailsData {

	String optionname;
	String optionvalue;
	String value;
	Integer clinicalelementtype;
	String elementprinttext;
	String retaincase;
	String elementgwid;

	public String getOptionname() {
		return optionname;
	}

	public void setOptionname(String optionname) {
		this.optionname = optionname;
	}

	public String getOptionvalue() {
		return optionvalue;
	}

	public void setOptionvalue(String optionvalue) {
		this.optionvalue = optionvalue;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getClinicalelementtype() {
		return clinicalelementtype;
	}

	public void setClinicalelementtype(Integer clinicalelementtype) {
		this.clinicalelementtype = clinicalelementtype;
	}

	public String getElementprinttext() {
		return elementprinttext;
	}

	public void setElementprinttext(String elementprinttext) {
		this.elementprinttext = elementprinttext;
	}

	public String getRetaincase() {
		return retaincase;
	}

	public void setRetaincase(String retaincase) {
		this.retaincase = retaincase;
	}

	public String getElementgwid() {
		return elementgwid;
	}

	public void setElementgwid(String elementgwid) {
		this.elementgwid = elementgwid;
	}

}