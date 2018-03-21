package com.glenwood.glaceemr.server.application.Bean;

public class IAMeasureBean {
	
	String measureIds;
	boolean measureStatus;
	Integer providerId;
	Integer year;
	
	public String getMeasureIds() {
		return measureIds;
	}
	public void setMeasureIds(String measureIds) {
		this.measureIds = measureIds;
	}
	public boolean getMeasureStatus() {
		return measureStatus;
	}
	public void setMeasureStatus(boolean measureStatus) {
		this.measureStatus = measureStatus;
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
