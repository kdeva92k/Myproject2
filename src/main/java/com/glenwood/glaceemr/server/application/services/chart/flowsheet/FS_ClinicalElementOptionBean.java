package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

public class FS_ClinicalElementOptionBean {
	private String clinicalElementGwId;
	private String clinicalElementName;
	private String clinicalElementOptionName;
	private String clinicalElementOptionValue;
	private String patientValue;
	private Integer clinicalElementType;
	private String clinicalElementUnits;
	public String getClinicalElementGwId() {
		return clinicalElementGwId;
	}
	public void setClinicalElementGwId(String clinicalElementGwId) {
		this.clinicalElementGwId = clinicalElementGwId;
	}
	public String getClinicalElementName() {
		return clinicalElementName;
	}
	public void setClinicalElementName(String clinicalElementName) {
		this.clinicalElementName = clinicalElementName;
	}
	public String getClinicalElementOptionName() {
		return clinicalElementOptionName;
	}
	public void setClinicalElementOptionName(String clinicalElementOptionName) {
		this.clinicalElementOptionName = clinicalElementOptionName;
	}
	public String getClinicalElementOptionValue() {
		return clinicalElementOptionValue;
	}
	public void setClinicalElementOptionValue(String clinicalElementOptionVaue) {
		this.clinicalElementOptionValue = clinicalElementOptionVaue;
	}
	public Integer getClinicalElementType() {
		return clinicalElementType;
	}
	public void setClinicalElementType(Integer clinicalElementType) {
		this.clinicalElementType = clinicalElementType;
	}
	public String getClinicalElementUnits() {
		return clinicalElementUnits;
	}
	public void setClinicalElementUnits(String clinicalElementUnits) {
		this.clinicalElementUnits = clinicalElementUnits;
	}
	public String getPatientValue() {
		return patientValue;
	}
	public void setPatientValue(String patientValue) {
		this.patientValue = patientValue;
	}
}
