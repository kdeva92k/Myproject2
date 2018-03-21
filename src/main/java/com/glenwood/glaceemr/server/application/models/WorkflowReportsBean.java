package com.glenwood.glaceemr.server.application.models;

import java.util.Date;

public class WorkflowReportsBean {
	private Integer patientId;
	private Integer encounterId;
	private Integer categoryId;
	private String categoryName;
	private Date startTime;
	private Date endTime;
	private String difference;
	
	public WorkflowReportsBean(Integer patientId,Integer encounterId,Integer categoryId,String categoryName,Date startTime,Date endTime,String difference)
	{
		super();
		this.patientId=patientId;
		this.encounterId=encounterId;
		this.categoryId=categoryId;
		this.categoryName=categoryName;
		this.startTime=startTime;
		this.endTime=endTime;
		this.difference=difference;
		
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDifference() {
		return difference;
	}

	public void setDifference(String difference) {
		this.difference = difference;
	}

}
