package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

public class OverdueAlert {
	String name;
	String id;
	String due;
	String type;
	Integer flowsheetId;
	Integer flowsheetType;
	String agePeriod;
	String testDetailId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDue() {
		return due;
	}
	public void setDue(String due) {
		this.due = due;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getFlowsheetId() {
		return flowsheetId;
	}
	public void setFlowsheetId(Integer flowsheetId) {
		this.flowsheetId = flowsheetId;
	}
	public Integer getFlowsheetType() {
		return flowsheetType;
	}
	public void setFlowsheetType(Integer flowsheetType) {
		this.flowsheetType = flowsheetType;
	}
	public String getAgePeriod() {
		return agePeriod;
	}
	public void setAgePeriod(String agePeriod) {
		this.agePeriod = agePeriod;
	}
	public String getTestDetailId() {
		return testDetailId;
	}
	public void setTestDetailId(String testDetailId) {
		this.testDetailId = testDetailId;
	}
}
