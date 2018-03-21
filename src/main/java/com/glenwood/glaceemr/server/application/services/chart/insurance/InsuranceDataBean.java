package com.glenwood.glaceemr.server.application.services.chart.insurance;

/**
 * Insurance details bean 
 * @author software
 *
 */
public class InsuranceDataBean {
	
	private String insId;
	
	private Integer insType;
	
	private Integer insCompId;
	
	private String insCompName;
	
	private String insAddress;
	
	private String insCity;
	
	private String insState;
	
	private String insZip;

	public InsuranceDataBean(String insId, Integer insType, Integer insCompId,
			String insCompName, String insAddress, String insCity,
			String insState, String insZip) {
		this.insId = insId;
		this.insType = insType;
		this.insCompId = insCompId;
		this.insCompName = insCompName;
		this.insAddress = insAddress;
		this.insCity = insCity;
		this.insState = insState;
		this.insZip = insZip;
	}

	public String getInsId() {
		return insId;
	}

	public Integer getInsType() {
		return insType;
	}

	public void setInsType(Integer insType) {
		this.insType = insType;
	}

	public Integer getInsCompId() {
		return insCompId;
	}

	public void setInsCompId(Integer insCompId) {
		this.insCompId = insCompId;
	}

	public String getInsCompName() {
		return insCompName;
	}

	public void setInsCompName(String insCompName) {
		this.insCompName = insCompName;
	}

	public String getInsAddress() {
		return insAddress;
	}

	public void setInsAddress(String insAddress) {
		this.insAddress = insAddress;
	}

	public String getInsCity() {
		return insCity;
	}

	public void setInsCity(String insCity) {
		this.insCity = insCity;
	}

	public String getInsState() {
		return insState;
	}

	public void setInsState(String insState) {
		this.insState = insState;
	}

	public String getInsZip() {
		return insZip;
	}

	public void setInsZip(String insZip) {
		this.insZip = insZip;
	}

	public void setInsId(String insId) {
		this.insId = insId;
	}

}
