package com.glenwood.glaceemr.server.application.Bean;

public class getMeasureBean {

	String measureIds;
	String measureNames;
	String priority;
	Integer providerId;
	Integer year;
	
	
	
	public String getMeasureIds() {
		return measureIds;
	}
	public void setMeasureIds(String measureIds) {
		this.measureIds = measureIds;
	}
	public String getMeasureNames() {
		return measureNames;
	}
	public void setMeasureNames(String measureNames) {
		this.measureNames = measureNames;
	}
	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	
	
}
