package com.glenwood.glaceemr.server.application.services.chart.HPI;

public class ClinicalElementsOptionBean{
	
	public ClinicalElementsOptionBean(Integer clinicalelementoptionId,Integer clinicalelementoptionOrderby,String clinicalelementoptionGwid,String clinicalelementoptionName,String clinicalelementoptionValue,Boolean clinicalElementsOptionsRetaincase){
		super();
			this.clinicalelementoptionId=clinicalelementoptionId;
			this.clinicalelementoptionOrderby=clinicalelementoptionOrderby;
			this.clinicalelementoptionGwid=clinicalelementoptionGwid;
			this.clinicalelementoptionName=clinicalelementoptionName;
			this.clinicalelementoptionValue=clinicalelementoptionValue;
			this.clinicalElementsOptionsRetaincase=clinicalElementsOptionsRetaincase;
			//this.patientClinicalElementOptionsValue=patientClinicalElementOptionsValue;
	}

	private Integer clinicalelementoptionId;
	private Integer clinicalelementoptionOrderby;
	private String clinicalelementoptionGwid;
	private String clinicalelementoptionName;
	private String clinicalelementoptionValue;
	private Boolean clinicalElementsOptionsRetaincase;
	//private String patientClinicalElementOptionsValue;
	public Integer getClinicalelementoptionId() {
		return clinicalelementoptionId;
	}
	public void setClinicalelementoptionId(Integer clinicalelementoptionId) {
		this.clinicalelementoptionId = clinicalelementoptionId;
	}
	public Integer getClinicalelementoptionOrderby() {
		return clinicalelementoptionOrderby;
	}
	public void setClinicalelementoptionOrderby(Integer clinicalelementoptionOrderby) {
		this.clinicalelementoptionOrderby = clinicalelementoptionOrderby;
	}
	public String getClinicalelementoptionGwid() {
		return clinicalelementoptionGwid;
	}
	public void setClinicalelementoptionGwid(String clinicalelementoptionGwid) {
		this.clinicalelementoptionGwid = clinicalelementoptionGwid;
	}
	public String getClinicalelementoptionName() {
		return clinicalelementoptionName;
	}
	public void setClinicalelementoptionName(String clinicalelementoptionName) {
		this.clinicalelementoptionName = clinicalelementoptionName;
	}
	public String getClinicalelementoptionValue() {
		return clinicalelementoptionValue;
	}
	public void setClinicalelementoptionValue(String clinicalelementoptionValue) {
		this.clinicalelementoptionValue = clinicalelementoptionValue;
	}
	public Boolean getClinicalElementsOptionsRetaincase() {
		return clinicalElementsOptionsRetaincase;
	}
	public void setClinicalElementsOptionsRetaincase(
			Boolean clinicalElementsOptionsRetaincase) {
		this.clinicalElementsOptionsRetaincase = clinicalElementsOptionsRetaincase;
	}
	/*public String getPatientClinicalElementOptionsValue() {
		return patientClinicalElementOptionsValue;
	}
	public void setPatientClinicalElementOptionsValue(
			String patientClinicalElementOptionsValue) {
		this.patientClinicalElementOptionsValue = patientClinicalElementOptionsValue;
	}*/
	
	
		
}

