package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "patient_portal_user")
public class PatientPortalUser {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_portal_user_patient_portal_user_portal_id_seq")
	@SequenceGenerator(name ="patient_portal_user_patient_portal_user_portal_id_seq", sequenceName="patient_portal_user_patient_portal_user_portal_id_seq", allocationSize=1)
	@Column(name="patient_portal_user_portal_id")
	private Integer patient_portal_user_portal_id;

	@Column(name="patient_portal_user_patient_id")
	private Long patient_portal_user_patient_id;

	@Column(name="patient_portal_user_created_on")
	private Date patient_portal_user_created_on;

	@Column(name="patient_portal_user_name")
	private String patient_portal_user_name;

	@Column(name="patient_portal_user_password_hash")
	private String patient_portal_user_password_hash;

	@Column(name="patient_portal_user_account_state")
	private Integer patient_portal_user_account_state;

	@Column(name="patient_portal_user_security_question")
	private String patient_portal_user_security_question;

	@Column(name="patient_portal_user_security_answer")
	private String patient_portal_user_security_answer;

	@Column(name="patient_portal_user_portal_account_verified")
	private Integer patient_portal_user_portal_account_verified;

	@Column(name="patient_portal_user_password")
	private String patient_portal_user_password;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_portal_user_access_time")
	private Timestamp patient_portal_user_access_time;

	@Column(name="patient_portal_user_wrong_entry_count")
	private Integer wrongEntryCount;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="access_time")
	private Timestamp accessTime;

	@Column(name="security_question1")
	private String securityQuestion1;

	@Column(name="security_question2")
	private String securityQuestion2;

	@Column(name="security_question3")
	private String securityQuestion3;

	@Column(name="security_answer1")
	private String securityAnswer1;

	@Column(name="security_answer2")
	private String securityAnswer2;

	@Column(name="security_answer3")
	private String securityAnswer3;
	
	@Column(name="password_reset")
	private Integer passwordReset;
	
	@Column(name="from_portal")
	private boolean fromPortal;
	
	@Column(name="from_portal_isactive")
	private boolean fromPortalIsactive;
	
	@Column(name="patient_portal_user_token")
	private String patient_portal_userToken;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="patient_portal_user_patient_id", referencedColumnName="chart_patientid", insertable=false, updatable=false)
	Chart chartpatient_portal_userTable;

	public Integer getpatient_portal_user_portal_id() {
		return patient_portal_user_portal_id;
	}

	public void setpatient_portal_user_portal_id(Integer patient_portal_user_portal_id) {
		this.patient_portal_user_portal_id = patient_portal_user_portal_id;
	}

	public Long getpatient_portal_user_patient_id() {
		return patient_portal_user_patient_id;
	}

	public void setpatient_portal_user_patient_id(Long patient_portal_user_patient_id) {
		this.patient_portal_user_patient_id = patient_portal_user_patient_id;
	}

	public Date getpatient_portal_user_created_on() {
		return patient_portal_user_created_on;
	}

	public void setpatient_portal_user_created_on(Date patient_portal_user_created_on) {
		this.patient_portal_user_created_on = patient_portal_user_created_on;
	}

	public String getpatient_portal_user_name() {
		return patient_portal_user_name;
	}

	public void setpatient_portal_user_name(String patient_portal_user_name) {
		this.patient_portal_user_name = patient_portal_user_name;
	}

	public String getpatient_portal_user_password_hash() {
		return patient_portal_user_password_hash;
	}

	public void setpatient_portal_user_password_hash(String patient_portal_user_password_hash) {
		this.patient_portal_user_password_hash = patient_portal_user_password_hash;
	}

	public Integer getpatient_portal_user_account_state() {
		return patient_portal_user_account_state;
	}

	public void setpatient_portal_user_account_state(Integer patient_portal_user_account_state) {
		this.patient_portal_user_account_state = patient_portal_user_account_state;
	}

	public String getpatient_portal_user_security_question() {
		return patient_portal_user_security_question;
	}

	public void setpatient_portal_user_security_question(String patient_portal_user_security_question) {
		this.patient_portal_user_security_question = patient_portal_user_security_question;
	}

	public String getpatient_portal_user_security_answer() {
		return patient_portal_user_security_answer;
	}

	public void setpatient_portal_user_security_answer(String patient_portal_user_security_answer) {
		this.patient_portal_user_security_answer = patient_portal_user_security_answer;
	}

	public Integer getpatient_portal_user_portal_account_verified() {
		return patient_portal_user_portal_account_verified;
	}

	public void setpatient_portal_user_portal_account_verified(Integer patient_portal_user_portal_account_verified) {
		this.patient_portal_user_portal_account_verified = patient_portal_user_portal_account_verified;
	}

	public String getpatient_portal_user_password() {
		return patient_portal_user_password;
	}

	public void setpatient_portal_user_password(String patient_portal_user_password) {
		this.patient_portal_user_password = patient_portal_user_password;
	}

	public Timestamp getpatient_portal_user_access_time() {
		return patient_portal_user_access_time;
	}

	public void setpatient_portal_user_access_time(Timestamp patient_portal_user_access_time) {
		this.patient_portal_user_access_time = patient_portal_user_access_time;
	}

	public Integer getWrongEntryCount() {
		return wrongEntryCount;
	}

	public void setWrongEntryCount(Integer wrongEntryCount) {
		this.wrongEntryCount = wrongEntryCount;
	}

	public Timestamp getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Timestamp accessTime) {
		this.accessTime = accessTime;
	}

	public String getSecurityQuestion1() {
		return securityQuestion1;
	}

	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}

	public String getSecurityQuestion2() {
		return securityQuestion2;
	}

	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}

	public String getSecurityQuestion3() {
		return securityQuestion3;
	}

	public void setSecurityQuestion3(String securityQuestion3) {
		this.securityQuestion3 = securityQuestion3;
	}

	public String getSecurityAnswer1() {
		return securityAnswer1;
	}

	public void setSecurityAnswer1(String securityAnswer1) {
		this.securityAnswer1 = securityAnswer1;
	}

	public String getSecurityAnswer2() {
		return securityAnswer2;
	}

	public void setSecurityAnswer2(String securityAnswer2) {
		this.securityAnswer2 = securityAnswer2;
	}

	public String getSecurityAnswer3() {
		return securityAnswer3;
	}

	public void setSecurityAnswer3(String securityAnswer3) {
		this.securityAnswer3 = securityAnswer3;
	}
	public Integer getPasswordReset() {
		return passwordReset;
	}

	public void setPasswordReset(Integer passwordReset) {
		this.passwordReset = passwordReset;
	}

	public boolean isFromPortal() {
		return fromPortal;
	}

	public void setFromPortal(boolean fromPortal) {
		this.fromPortal = fromPortal;
	}

	public boolean isFromPortalIsactive() {
		return fromPortalIsactive;
	}

	public void setFromPortalIsactive(boolean fromPortalIsactive) {
		this.fromPortalIsactive = fromPortalIsactive;
	}

	public String getpatient_portal_userToken() {
		return patient_portal_userToken;
	}

	public void setpatient_portal_userToken(String patient_portal_userToken) {
		this.patient_portal_userToken = patient_portal_userToken;
	}

	public Chart getChartpatient_portal_userTable() {
		return chartpatient_portal_userTable;
	}

	public void setChartpatient_portal_userTable(
			Chart chartpatient_portal_userTable) {
		this.chartpatient_portal_userTable = chartpatient_portal_userTable;
	}
	
	
	
}