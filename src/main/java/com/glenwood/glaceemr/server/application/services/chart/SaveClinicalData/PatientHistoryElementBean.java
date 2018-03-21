package com.glenwood.glaceemr.server.application.services.chart.SaveClinicalData;


public class PatientHistoryElementBean{
	private int patientHistoryElementId;
	private long patientHistoryElementGWId;
	private String patientHistoryElementText;
	private Boolean patientHistoryElementBoolean;
	private int patientHistoryElementOption;
	private int patientHistoryElementNumber;
	
	public PatientHistoryElementBean(){
		patientHistoryElementId=-1;
		patientHistoryElementGWId=-1;
		patientHistoryElementText="";
		patientHistoryElementBoolean=false;
		patientHistoryElementOption=-1;
		patientHistoryElementNumber=-1;
	}
	public int getpatientHistoryElementId() {
		return patientHistoryElementId;
	}
	public void setpatientHistoryElementId(
			int patientHistoryElementId) {
		this.patientHistoryElementId = patientHistoryElementId;
	}
	public long getpatientHistoryElementGWId() {
		return patientHistoryElementGWId;
	}
	public void setpatientHistoryElementGWId(
			long patientHistoryElementGWId) {
		this.patientHistoryElementGWId = patientHistoryElementGWId;
	}
	public String getpatientHistoryElementText() {
		return patientHistoryElementText;
	}
	public void setpatientHistoryElementText(
			String patientHistoryElementText) {
		this.patientHistoryElementText = patientHistoryElementText;
	}
	public Boolean getpatientHistoryElementBoolean() {
		return patientHistoryElementBoolean;
	}
	public void setpatientHistoryElementBoolean(
			Boolean patientHistoryElementBoolean) {
		this.patientHistoryElementBoolean = patientHistoryElementBoolean;
	}
	public int getpatientHistoryElementOption() {
		return patientHistoryElementOption;
	}
	public void setpatientHistoryElementOption(
			int patientHistoryElementOption) {
		this.patientHistoryElementOption = patientHistoryElementOption;
	}
	public int getpatientHistoryElementNumber() {
		return patientHistoryElementNumber;
	}
	public void setpatientHistoryElementNumber(
			int patientHistoryElementNumber) {
		this.patientHistoryElementNumber = patientHistoryElementNumber;
	}
}