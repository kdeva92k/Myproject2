package com.glenwood.glaceemr.server.application.services.chart.prescription;

public class IntakeBean {

	
	public String name;
	public String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public IntakeBean(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	
}
