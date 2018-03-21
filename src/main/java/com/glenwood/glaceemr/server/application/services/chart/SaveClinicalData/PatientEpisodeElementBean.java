package com.glenwood.glaceemr.server.application.services.chart.SaveClinicalData;
public class PatientEpisodeElementBean{
	private int patientEpisodeElementId;
	private long patientEpisodeElementGWId;
	private String patientEpisodeElementText;
	private Boolean patientEpisodeElementBoolean;
	private int patientEpisodeElementOption;
	private int patientEpisodeElementNumber;
	public PatientEpisodeElementBean(){
		patientEpisodeElementId=-1;
		patientEpisodeElementGWId=-1;
		patientEpisodeElementText="";
		patientEpisodeElementBoolean=false;
		patientEpisodeElementOption=-1;
		patientEpisodeElementNumber=-1;

	}
	public int getPatientEpisodeElementId() {
		return patientEpisodeElementId;
	}
	public void setPatientEpisodeElementId(int patientEpisodeElementId) {
		this.patientEpisodeElementId = patientEpisodeElementId;
	}
	public long getPatientEpisodeElementGWId() {
		return patientEpisodeElementGWId;
	}
	public void setPatientEpisodeElementGWId(long patientEpisodeElementGWId) {
		this.patientEpisodeElementGWId = patientEpisodeElementGWId;
	}
	public String getPatientEpisodeElementText() {
		return patientEpisodeElementText;
	}
	public void setPatientEpisodeElementText(String patientEpisodeElementText) {
		this.patientEpisodeElementText = patientEpisodeElementText;
	}
	public Boolean getPatientEpisodeElementBoolean() {
		return patientEpisodeElementBoolean;
	}
	public void setPatientEpisodeElementBoolean(Boolean patientEpisodeElementBoolean) {
		this.patientEpisodeElementBoolean = patientEpisodeElementBoolean;
	}
	public int getPatientEpisodeElementOption() {
		return patientEpisodeElementOption;
	}
	public void setPatientEpisodeElementOption(int patientEpisodeElementOption) {
		this.patientEpisodeElementOption = patientEpisodeElementOption;
	}
	public int getPatientEpisodeElementNumber() {
		return patientEpisodeElementNumber;
	}
	public void setPatientEpisodeElementNumber(int patientEpisodeElementNumber) {
		this.patientEpisodeElementNumber = patientEpisodeElementNumber;
	}
}