package com.glenwood.glaceemr.server.application.services.chart.clinicalElements;


public class PatientElementBean{
	private long patientClinicalElementId;
	private String patientClinicalElementGWId;
	private String patientClinicalElementText;
	private boolean patientClinicalElementBoolean;
	private int patientClinicalElementOption;
	private int patientClinicalElementNumber;
	private int patientClinicalElementEncounterId;
	private String patientClinicalElementChangeHistoryValue;
	public String getPatientClinicalElementChangeHistoryValue() {
		return patientClinicalElementChangeHistoryValue;
	}
	public void setPatientClinicalElementChangeHistoryValue(
			String patientClinicalElementChangeHistoryValue) {
		this.patientClinicalElementChangeHistoryValue = patientClinicalElementChangeHistoryValue;
	}
	private int patientClinicalElementChangeHistoryOpType;
	PatientElementBean(){
		patientClinicalElementId=-1;
		patientClinicalElementGWId="";
		patientClinicalElementText="";
		patientClinicalElementBoolean=false;
		patientClinicalElementOption=-1;
		patientClinicalElementNumber=-1;
		patientClinicalElementEncounterId=-1;
		patientClinicalElementChangeHistoryOpType=0;
		patientClinicalElementChangeHistoryValue="";
	}
	public int getPatientClinicalElementChangeHistoryOpType() {
		return patientClinicalElementChangeHistoryOpType;
	}
	public void setPatientClinicalElementChangeHistoryOpType(
			int patientClinicalElementChangeHistoryOpType) {
		this.patientClinicalElementChangeHistoryOpType = patientClinicalElementChangeHistoryOpType;
	}
	public long getPatientClinicalElementId() {
		return patientClinicalElementId;
	}
	public void setPatientClinicalElementId(int patientClinicalElementId) {
		this.patientClinicalElementId = patientClinicalElementId;
	}
	public String getPatientClinicalElementGWId() {
		return patientClinicalElementGWId;
	}
	public void setPatientClinicalElementGWId(String patientClinicalElementGWId) {
		this.patientClinicalElementGWId = patientClinicalElementGWId;
	}
	public String getPatientClinicalElementText() {
		return patientClinicalElementText;
	}
	public String getPatientClinicalElementTextWithCDATA() {
		return "<![CDATA[" + patientClinicalElementText + "]]>";
	}
	public void setPatientClinicalElementText(String patientClinicalElementText) {
		this.patientClinicalElementText = patientClinicalElementText;
	}
	public Boolean getPatientClinicalElementBoolean() {
		return patientClinicalElementBoolean;
	}
	public void setPatientClinicalElementBoolean(
			Boolean patientClinicalElementBoolean) {
		this.patientClinicalElementBoolean = patientClinicalElementBoolean;
	}
	public int getPatientClinicalElementOption() {
		return patientClinicalElementOption;
	}
	public void setPatientClinicalElementOption(int patientClinicalElementOption) {
		this.patientClinicalElementOption = patientClinicalElementOption;
	}
	public int getPatientClinicalElementNumber() {
		return patientClinicalElementNumber;
	}
	public void setPatientClinicalElementNumber(int patientClinicalElementNumber) {
		this.patientClinicalElementNumber = patientClinicalElementNumber;
	}
	public int getPatientClinicalElementEncounterId() {
		return patientClinicalElementEncounterId;
	}
	public void setPatientClinicalElementEncounterId(int patientClinicalElementEncounterId){
		this.patientClinicalElementEncounterId = patientClinicalElementEncounterId;
	}
}