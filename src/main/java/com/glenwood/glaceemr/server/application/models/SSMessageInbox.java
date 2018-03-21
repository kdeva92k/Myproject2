package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;


@Entity
@Table(name = "ss_message_inbox")
public class SSMessageInbox implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer getSsMessageInboxId() {
		return ssMessageInboxId;
	}

	public void setSsMessageInboxId(Integer ssMessageInboxId) {
		this.ssMessageInboxId = ssMessageInboxId;
	}

	public Integer getSsMessageInboxMapPatientId() {
		return ssMessageInboxMapPatientId;
	}

	public void setSsMessageInboxMapPatientId(Integer ssMessageInboxMapPatientId) {
		this.ssMessageInboxMapPatientId = ssMessageInboxMapPatientId;
	}

	public String getSsMessageInboxReceivedPatientLastname() {
		return ssMessageInboxReceivedPatientLastname;
	}

	public void setSsMessageInboxReceivedPatientLastname(
			String ssMessageInboxReceivedPatientLastname) {
		this.ssMessageInboxReceivedPatientLastname = ssMessageInboxReceivedPatientLastname;
	}

	public String getSsMessageInboxReceivedPatientFirstname() {
		return ssMessageInboxReceivedPatientFirstname;
	}

	public void setSsMessageInboxReceivedPatientFirstname(
			String ssMessageInboxReceivedPatientFirstname) {
		this.ssMessageInboxReceivedPatientFirstname = ssMessageInboxReceivedPatientFirstname;
	}

	public String getSsMessageInboxReceivedPatientMidname() {
		return ssMessageInboxReceivedPatientMidname;
	}

	public void setSsMessageInboxReceivedPatientMidname(
			String ssMessageInboxReceivedPatientMidname) {
		this.ssMessageInboxReceivedPatientMidname = ssMessageInboxReceivedPatientMidname;
	}

	public Timestamp getSsMessageInboxReceivedPatientDob() {
		return ssMessageInboxReceivedPatientDob;
	}

	public void setSsMessageInboxReceivedPatientDob(
			Timestamp ssMessageInboxReceivedPatientDob) {
		this.ssMessageInboxReceivedPatientDob = ssMessageInboxReceivedPatientDob;
	}

	public Integer getSsMessageInboxMapUserId() {
		return ssMessageInboxMapUserId;
	}

	public void setSsMessageInboxMapUserId(Integer ssMessageInboxMapUserId) {
		this.ssMessageInboxMapUserId = ssMessageInboxMapUserId;
	}

	public Timestamp getSsMessageInboxReceivedOn() {
		return ssMessageInboxReceivedOn;
	}

	public void setSsMessageInboxReceivedOn(Timestamp ssMessageInboxReceivedOn) {
		this.ssMessageInboxReceivedOn = ssMessageInboxReceivedOn;
	}

	public String getSsMessageInboxReceivedPharmacyNcpdpId() {
		return ssMessageInboxReceivedPharmacyNcpdpId;
	}

	public void setSsMessageInboxReceivedPharmacyNcpdpId(
			String ssMessageInboxReceivedPharmacyNcpdpId) {
		this.ssMessageInboxReceivedPharmacyNcpdpId = ssMessageInboxReceivedPharmacyNcpdpId;
	}

	public String getSsMessageInboxReceivedPharmacyName() {
		return ssMessageInboxReceivedPharmacyName;
	}

	public void setSsMessageInboxReceivedPharmacyName(
			String ssMessageInboxReceivedPharmacyName) {
		this.ssMessageInboxReceivedPharmacyName = ssMessageInboxReceivedPharmacyName;
	}

	public String getSsMessageInboxXmlcontent() {
		return ssMessageInboxXmlcontent;
	}

	public void setSsMessageInboxXmlcontent(String ssMessageInboxXmlcontent) {
		this.ssMessageInboxXmlcontent = ssMessageInboxXmlcontent;
	}

	public String getSsMessageInboxXmlfilepath() {
		return ssMessageInboxXmlfilepath;
	}

	public void setSsMessageInboxXmlfilepath(String ssMessageInboxXmlfilepath) {
		this.ssMessageInboxXmlfilepath = ssMessageInboxXmlfilepath;
	}

	public Boolean getSsMessageInboxStatus() {
		return ssMessageInboxStatus;
	}

	public void setSsMessageInboxStatus(Boolean ssMessageInboxStatus) {
		this.ssMessageInboxStatus = ssMessageInboxStatus;
	}

	public String getSsMessageInboxMapMessageId() {
		return ssMessageInboxMapMessageId;
	}

	public void setSsMessageInboxMapMessageId(String ssMessageInboxMapMessageId) {
		this.ssMessageInboxMapMessageId = ssMessageInboxMapMessageId;
	}

	public Integer getSsMessageInboxMessageType() {
		return ssMessageInboxMessageType;
	}

	public void setSsMessageInboxMessageType(Integer ssMessageInboxMessageType) {
		this.ssMessageInboxMessageType = ssMessageInboxMessageType;
	}

	public Boolean getSsMessageInboxIsClose() {
		return ssMessageInboxIsClose;
	}

	public void setSsMessageInboxIsClose(Boolean ssMessageInboxIsClose) {
		this.ssMessageInboxIsClose = ssMessageInboxIsClose;
	}

	@Id
	@Column(name="ss_message_inbox_id")
	private Integer ssMessageInboxId;

	@Column(name="ss_message_inbox_map_patient_id")
	private Integer ssMessageInboxMapPatientId;

	@Column(name="ss_message_inbox_received_patient_lastname")
	private String ssMessageInboxReceivedPatientLastname;

	@Column(name="ss_message_inbox_received_patient_firstname")
	private String ssMessageInboxReceivedPatientFirstname;

	@Column(name="ss_message_inbox_received_patient_midname")
	private String ssMessageInboxReceivedPatientMidname;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="ss_message_inbox_received_patient_dob")
	private Timestamp ssMessageInboxReceivedPatientDob;

	@Column(name="ss_message_inbox_received_patient_gender")
	private String ssMessageInboxReceivedPatientGender;

	@Column(name="ss_message_inbox_map_user_id")
	private Integer ssMessageInboxMapUserId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="ss_message_inbox_received_on")
	private Timestamp ssMessageInboxReceivedOn;
	
	@Column(name="ss_message_inbox_received_pharmacy_ncpdp_id")
	private String ssMessageInboxReceivedPharmacyNcpdpId;

	@Column(name="ss_message_inbox_received_pharmacy_name")
	private String ssMessageInboxReceivedPharmacyName;

	@Column(name="ss_message_inbox_xmlcontent")
	private String ssMessageInboxXmlcontent;

	@Column(name="ss_message_inbox_xmlfilepath")
	private String ssMessageInboxXmlfilepath;

	@Column(name="ss_message_inbox_status")
	private Boolean ssMessageInboxStatus;

	@Column(name="ss_message_inbox_map_message_id")
	private String ssMessageInboxMapMessageId;

	@Column(name="ss_message_inbox_message_type")
	private Integer ssMessageInboxMessageType;

	@Column(name="ss_message_inbox_is_close")
	private Boolean ssMessageInboxIsClose;
	
//	@Column(name="ss_message_inbox_relates_message_id")
//	private char ssMessageInboxRelatesMessageId;
//	
//	
//	
//	
//	public char getSsMessageInboxRelatesMessageId() {
//		return ssMessageInboxRelatesMessageId;
//	}
//
//	public void setSsMessageInboxRelatesMessageId(
//			char ssMessageInboxRelatesMessageId) {
//		this.ssMessageInboxRelatesMessageId = ssMessageInboxRelatesMessageId;
//	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ss_message_inbox_received_pharmacy_ncpdp_id",referencedColumnName="pharm_details_ncpdpid",insertable=false,updatable=false)
	@JsonManagedReference
	PharmDetails pharm;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ss_message_inbox_map_message_id",referencedColumnName="ss_inbox_pharmacy_message_id",insertable=false,updatable=false)
	@JsonManagedReference
	SSInbox ssinbox;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ss_message_inbox_message_type",referencedColumnName="refill_message_type_id",insertable=false,updatable=false)
	@JsonManagedReference
	RefillMessageType RefillMessageType;
	
	public RefillMessageType getRefillMessageType() {
		return RefillMessageType;
	}

	public void setRefillMessageType(RefillMessageType refillMessageType) {
		RefillMessageType = refillMessageType;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ss_message_inbox_map_user_id",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	@JsonManagedReference
	EmployeeProfile emp;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ss_message_inbox_map_patient_id",referencedColumnName="patient_registration_id",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	@JsonManagedReference
	PatientRegistration patientregistration;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ss_message_inbox_map_patient_id",referencedColumnName="chart_patientid",insertable=false,updatable=false)
	@JsonManagedReference
	Chart chart;
	
	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public PatientRegistration getPatientregistration() {
		return patientregistration;
	}

	public void setPatientregistration(PatientRegistration patientregistration) {
		this.patientregistration = patientregistration;
	}

	public EmployeeProfile getEmp() {
		return emp;
	}

	public void setEmp(EmployeeProfile emp) {
		this.emp = emp;
	}

	public SSInbox getSsinbox() {
		return ssinbox;
	}

	public void setSsinbox(SSInbox ssinbox) {
		this.ssinbox = ssinbox;
	}

	public String getSsMessageInboxReceivedPatientGender() {
		return ssMessageInboxReceivedPatientGender;
	}

	public void setSsMessageInboxReceivedPatientGender(
			String ssMessageInboxReceivedPatientGender) {
		this.ssMessageInboxReceivedPatientGender = ssMessageInboxReceivedPatientGender;
	}

	public PharmDetails getPharm() {
		return pharm;
	}

	public void setPharm(PharmDetails pharm) {
		this.pharm = pharm;
	}

	
	
	
	
		
	
}