package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

public class FlowsheetNameBean {
	private int flowsheetId;
	private int flowsheetType;
	private String flowsheetName;
	private String flowsheetTypeName;
	private String date;
	public FlowsheetNameBean(int flowsheetId,int flowsheetType,String flowsheetName,String flowsheetTypeName,String date){
		this.flowsheetId=flowsheetId;
		this.flowsheetType=flowsheetType;
		this.flowsheetName=flowsheetName;
		this.flowsheetTypeName=flowsheetTypeName;
		this.date=date;
	}
	public FlowsheetNameBean(int flowsheetId,int flowsheetType,String flowsheetName,String flowsheetTypeName){
		this.flowsheetId=flowsheetId;
		this.flowsheetType=flowsheetType;
		this.flowsheetName=flowsheetName;
		this.flowsheetTypeName=flowsheetTypeName;
	}
	public int getFlowsheetId() {
		return flowsheetId;
	}
	public void setFlowsheetId(int flowsheetId) {
		this.flowsheetId = flowsheetId;
	}
	public int getFlowsheetType() {
		return flowsheetType;
	}
	public void setFlowsheetType(int flowsheetType) {
		this.flowsheetType = flowsheetType;
	}
	public String getFlowsheetName() {
		return flowsheetName;
	}
	public void setFlowsheetName(String flowsheetName) {
		this.flowsheetName = flowsheetName;
	}
	public String getFlowsheetTypeName() {
		return flowsheetTypeName;
	}
	public void setFlowsheetTypeName(String flowsheetTypeName) {
		this.flowsheetTypeName = flowsheetTypeName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
