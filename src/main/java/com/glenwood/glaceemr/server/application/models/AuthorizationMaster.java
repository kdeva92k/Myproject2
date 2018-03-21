package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "auth_master")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorizationMaster implements Serializable {

	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_master_auth_master_uniqueid_seq")
	@SequenceGenerator(name = "auth_master_auth_master_uniqueid_seq", sequenceName = "auth_master_auth_master_uniqueid_seq", allocationSize = 1)
	@Column(name="auth_master_uniqueid")
	private Integer authMasterUniqueid;

	@Column(name="auth_master_patient_id")
	private Integer authMasterPatientId;

	@Column(name="auth_master_official_notes")
	private String authMasterOfficialNotes;

	@Column(name="auth_master_official_notes2")
	private String authMasterOfficialNotes2;

	@Column(name="auth_master_curr_auth_no")
	private Integer authMasterCurrAuthNo;

	@Column(name="auth_master_alert_notes")
	private Boolean authMasterAlertNotes;

	@Column(name="auth_master_recall_notes")
	private String authMasterRecallNotes;

	@Column(name="auth_master_recall_notes2")
	private String authMasterRecallNotes2;

	@Column(name="auth_master_sysalert")
	private String authMasterSysalert;

	@Column(name="auth_master_splalert")
	private String authMasterSplalert;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="auth_master_splalert2")
	private String authMasterSplalert2;

	@Column(name="auth_master_official_notes_new")
	private String authMasterOfficialNotesNew;

	@Column(name="auth_master_official_notes_date_new")
	private Date authMasterOfficialNotesDateNew;

	@Column(name="auth_master_recall_notes_new")
	private String authMasterRecallNotesNew;

	@Column(name="auth_master_recall_notes_date_new")
	private Date authMasterRecallNotesDateNew;

	@Column(name="auth_master_ptbalance_alert")
	private String authMasterPtbalanceAlert;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="auth_master_patient_id", referencedColumnName="patient_registration_id" , insertable=false, updatable=false)
	PatientRegistration patientRegistration;

	public Integer getAuthMasterUniqueid() {
		return authMasterUniqueid;
	}

	public void setAuthMasterUniqueid(Integer authMasterUniqueid) {
		this.authMasterUniqueid = authMasterUniqueid;
	}

	public Integer getAuthMasterPatientId() {
		return authMasterPatientId;
	}

	public void setAuthMasterPatientId(Integer authMasterPatientId) {
		this.authMasterPatientId = authMasterPatientId;
	}

	public String getAuthMasterOfficialNotes() {
		return authMasterOfficialNotes;
	}

	public void setAuthMasterOfficialNotes(String authMasterOfficialNotes) {
		this.authMasterOfficialNotes = authMasterOfficialNotes;
	}

	public String getAuthMasterOfficialNotes2() {
		return authMasterOfficialNotes2;
	}

	public void setAuthMasterOfficialNotes2(String authMasterOfficialNotes2) {
		this.authMasterOfficialNotes2 = authMasterOfficialNotes2;
	}

	public Integer getAuthMasterCurrAuthNo() {
		return authMasterCurrAuthNo;
	}

	public void setAuthMasterCurrAuthNo(Integer authMasterCurrAuthNo) {
		this.authMasterCurrAuthNo = authMasterCurrAuthNo;
	}

	public Boolean getAuthMasterAlertNotes() {
		return authMasterAlertNotes;
	}

	public void setAuthMasterAlertNotes(Boolean authMasterAlertNotes) {
		this.authMasterAlertNotes = authMasterAlertNotes;
	}

	public String getAuthMasterRecallNotes() {
		return authMasterRecallNotes;
	}

	public void setAuthMasterRecallNotes(String authMasterRecallNotes) {
		this.authMasterRecallNotes = authMasterRecallNotes;
	}

	public String getAuthMasterRecallNotes2() {
		return authMasterRecallNotes2;
	}

	public void setAuthMasterRecallNotes2(String authMasterRecallNotes2) {
		this.authMasterRecallNotes2 = authMasterRecallNotes2;
	}

	public String getAuthMasterSysalert() {
		return authMasterSysalert;
	}

	public void setAuthMasterSysalert(String authMasterSysalert) {
		this.authMasterSysalert = authMasterSysalert;
	}

	public String getAuthMasterSplalert() {
		return authMasterSplalert;
	}

	public void setAuthMasterSplalert(String authMasterSplalert) {
		this.authMasterSplalert = authMasterSplalert;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public String getAuthMasterSplalert2() {
		return authMasterSplalert2;
	}

	public void setAuthMasterSplalert2(String authMasterSplalert2) {
		this.authMasterSplalert2 = authMasterSplalert2;
	}

	public String getAuthMasterOfficialNotesNew() {
		return authMasterOfficialNotesNew;
	}

	public void setAuthMasterOfficialNotesNew(String authMasterOfficialNotesNew) {
		this.authMasterOfficialNotesNew = authMasterOfficialNotesNew;
	}

	public Date getAuthMasterOfficialNotesDateNew() {
		return authMasterOfficialNotesDateNew;
	}

	public void setAuthMasterOfficialNotesDateNew(
			Date authMasterOfficialNotesDateNew) {
		this.authMasterOfficialNotesDateNew = authMasterOfficialNotesDateNew;
	}

	public String getAuthMasterRecallNotesNew() {
		return authMasterRecallNotesNew;
	}

	public void setAuthMasterRecallNotesNew(String authMasterRecallNotesNew) {
		this.authMasterRecallNotesNew = authMasterRecallNotesNew;
	}

	public Date getAuthMasterRecallNotesDateNew() {
		return authMasterRecallNotesDateNew;
	}

	public void setAuthMasterRecallNotesDateNew(Date authMasterRecallNotesDateNew) {
		this.authMasterRecallNotesDateNew = authMasterRecallNotesDateNew;
	}

	public String getAuthMasterPtbalanceAlert() {
		return authMasterPtbalanceAlert;
	}

	public void setAuthMasterPtbalanceAlert(String authMasterPtbalanceAlert) {
		this.authMasterPtbalanceAlert = authMasterPtbalanceAlert;
	}

	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}