package com.glenwood.glaceemr.server.application.Bean.pqrs;

public class QualityMeasureBean {


	
	String measureId;
	Integer providerId;
   String title;
	
	public QualityMeasureBean(String measureId, Integer providerId,String title) {
		super();
		this.measureId = measureId;
		this.providerId = providerId;
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMeasureId() {
		return measureId;
	}
	
	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	

	
}
