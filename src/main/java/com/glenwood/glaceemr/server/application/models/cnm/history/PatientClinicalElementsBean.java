package com.glenwood.glaceemr.server.application.models.cnm.history;


public class PatientClinicalElementsBean {
	
	
	private String patientClinicalElementsGwid;

	private String patientClinicalElementsValue;
	
	private Integer clinicalElementsDatatype;
	
	private Long patientClinicalElementsId;

	private Integer patientClinicalElementsHistoryId;
	
	public PatientClinicalElementsBean(String patientClinicalElementsGwid,
			String patientClinicalElementsValue,
			Integer clinicalElementsDatatype, Long patientClinicalElementsId) {
		super();
		this.patientClinicalElementsGwid = patientClinicalElementsGwid;
		this.patientClinicalElementsValue = patientClinicalElementsValue;
		this.clinicalElementsDatatype = clinicalElementsDatatype;
		this.patientClinicalElementsId = patientClinicalElementsId;
	}
	
	public PatientClinicalElementsBean(String patientClinicalElementsGwid,
			String patientClinicalElementsValue,
			Integer clinicalElementsDatatype, Integer patientClinicalElementsHistoryId) {
		super();
		this.patientClinicalElementsGwid = patientClinicalElementsGwid;
		this.patientClinicalElementsValue = patientClinicalElementsValue;
		this.clinicalElementsDatatype = clinicalElementsDatatype;
		this.patientClinicalElementsHistoryId = patientClinicalElementsHistoryId;
	}

	public String getPatientClinicalElementsGwid() {
		return patientClinicalElementsGwid;
	}

	public String getPatientClinicalElementsValue() {
		return patientClinicalElementsValue;
	}

	public Integer getClinicalElementsDatatype() {
		return clinicalElementsDatatype;
	}

	public Long getPatientClinicalElementsId() {
		return patientClinicalElementsId;
	}

	public void setPatientClinicalElementsGwid(String patientClinicalElementsGwid) {
		this.patientClinicalElementsGwid = patientClinicalElementsGwid;
	}

	public void setPatientClinicalElementsValue(String patientClinicalElementsValue) {
		this.patientClinicalElementsValue = patientClinicalElementsValue;
	}

	public void setClinicalElementsDatatype(Integer clinicalElementsDatatype) {
		this.clinicalElementsDatatype = clinicalElementsDatatype;
	}

	public void setPatientClinicalElementsId(Long patientClinicalElementsId) {
		this.patientClinicalElementsId = patientClinicalElementsId;
	}
	
	public Integer getPatientClinicalElementsHistoryId() {
		return patientClinicalElementsHistoryId;
	}

	public void setPatientClinicalElementsHistoryId(
			Integer patientClinicalElementsHistoryId) {
		this.patientClinicalElementsHistoryId = patientClinicalElementsHistoryId;
	}
	
}
