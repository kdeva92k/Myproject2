package com.glenwood.glaceemr.server.application.Bean;

public class getPatientBean {

	String patientId;
	String measureId;
	int criteria;
	int mode;
	String empTIN="";
	String accountId;
	Boolean isNotMet=false;
	Integer provider;
	Integer year;
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getMeasureId() {
		return measureId;
	}
	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}
	public int getCriteria() {
		return criteria;
	}
	public void setCriteria(int criteria) {
		this.criteria = criteria;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public String getEmpTIN() {
		return empTIN;
	}
	public void setEmpTIN(String empTIN) {
		this.empTIN = empTIN;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Boolean getIsNotMet() {
		return isNotMet;
	}
	public void setIsNotMet(Boolean isNotMet) {
		this.isNotMet = isNotMet;
	}
	public Integer getProvider() {
		return provider;
	}
	public void setProvider(Integer provider) {
		this.provider = provider;
	}
	
	
}
