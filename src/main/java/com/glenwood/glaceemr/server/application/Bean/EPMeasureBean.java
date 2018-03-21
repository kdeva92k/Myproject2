package com.glenwood.glaceemr.server.application.Bean;

public class EPMeasureBean {

	String measureId;
	String measureTitle;
	String status;
	String shortDescription = "";
	String description = "";

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMeasureId() {
		return measureId;
	}
	
	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}
	
	public String getMeasureTitle() {
		return measureTitle;
	}
	
	public void setMeasureTitle(String measureTitle) {
		this.measureTitle = measureTitle;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

}