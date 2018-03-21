package com.glenwood.glaceemr.server.application.Bean;

public class CommonResponseBean {
	
	String responseStatus;
	Integer serviceId;
	String patientAccountId;
	String accountServerIp;
	String failedReason;
	Integer rowId;
	String innerRowId;
	Integer taskId;
	String denialId;
	
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getPatientAccountId() {
		return patientAccountId;
	}
	public void setPatientAccountId(String patientAccountId) {
		this.patientAccountId = patientAccountId;
	}
	public String getAccountServerIp() {
		return accountServerIp;
	}
	public void setAccountServerIp(String accountServerIp) {
		this.accountServerIp = accountServerIp;
	}
	public String getFailedReason() {
		return failedReason;
	}
	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	public String getInnerRowId() {
		return innerRowId;
	}
	public void setInnerRowId(String innerRowId) {
		this.innerRowId = innerRowId;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getDenialId() {
		return denialId;
	}
	public void setDenialId(String denialId) {
		this.denialId = denialId;
	}
	
	
}
