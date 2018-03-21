package com.glenwood.glaceemr.server.application.services.GroupTherapy;

public class AddNoteBean {
	private String patientId;
	private String patientDetailsModifiedOn;
	private String gwid;
	private String patientDetailsEnteredBy;
	private String value;
	private Integer sessionId;
	private Integer patientDetailsId;
	private String patientDetailsEnteredOn;
	private String patientDetailsModifiedBy;
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientDetailsModifiedOn() {
		return patientDetailsModifiedOn;
	}
	public void setPatientDetailsModifiedOn(String patientDetailsModifiedOn) {
		this.patientDetailsModifiedOn = patientDetailsModifiedOn;
	}
	public String getGwid() {
		return gwid;
	}
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	public String getPatientDetailsEnteredBy() {
		return patientDetailsEnteredBy;
	}
	public void setPatientDetailsEnteredBy(String patientDetailsEnteredBy) {
		this.patientDetailsEnteredBy = patientDetailsEnteredBy;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getSessionId() {
		return sessionId;
	}
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
	public Integer getPatientDetailsId() {
		return patientDetailsId;
	}
	public void setPatientDetailsId(Integer patientDetailsId) {
		this.patientDetailsId = patientDetailsId;
	}
	public String getPatientDetailsEnteredOn() {
		return patientDetailsEnteredOn;
	}
	public void setPatientDetailsEnteredOn(String patientDetailsEnteredOn) {
		this.patientDetailsEnteredOn = patientDetailsEnteredOn;
	}
	public String getPatientDetailsModifiedBy() {
		return patientDetailsModifiedBy;
	}
	public void setPatientDetailsModifiedBy(String patientDetailsModifiedBy) {
		this.patientDetailsModifiedBy = patientDetailsModifiedBy;
	}
	
}
