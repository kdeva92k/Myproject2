package com.glenwood.glaceemr.server.application.services.chart.reports;

public class OverrideBean {
	String elementids;
	Integer patientid;
	Integer fsElementType;
	Integer flowsheetId;
	String reason;
	String data;
	Integer userId;
	public String getElementids() {
		return elementids;
	}
	public void setElementids(String elementids) {
		this.elementids = elementids;
	}
	public Integer getPatientid() {
		return patientid;
	}
	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}
	public Integer getFsElementType() {
		return fsElementType;
	}
	public void setFsElementType(Integer fsElementType) {
		this.fsElementType = fsElementType;
	}
	public Integer getFlowsheetId() {
		return flowsheetId;
	}
	public void setFlowsheetId(Integer flowsheetId) {
		this.flowsheetId = flowsheetId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
