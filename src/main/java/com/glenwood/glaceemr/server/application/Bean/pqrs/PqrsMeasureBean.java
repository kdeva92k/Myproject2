package com.glenwood.glaceemr.server.application.Bean.pqrs;

public class PqrsMeasureBean {


	
	String measureId;
	String measureTitle;
	String status;
	String description = "";
	
	public PqrsMeasureBean(String measureId, String measureTitle, String status, String description) {
		super();
		this.measureId = measureId;
		this.measureTitle = measureTitle;
		this.status = status;
		this.description = description;
		
	}

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
	


	
}
