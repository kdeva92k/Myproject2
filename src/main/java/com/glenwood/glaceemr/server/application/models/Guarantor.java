package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;


@Entity
@Table(name = "guarantor")
public class Guarantor {

	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guarantor_guarantor_key_seq")
	@SequenceGenerator(name = "guarantor_guarantor_key_seq", sequenceName = "guarantor_guarantor_key_seq", allocationSize = 1)
	@Column(name="guarantor_key")
	private Integer guarantorKey;

	@Column(name="guarantor_last_name")
	private String guarantorLastName;

	@Column(name="guarantor_middle_initial")
	private String guarantorMiddleInitial;

	@Column(name="guarantor_first_name")
	private String guarantorFirstName;

	@Column(name="guarantor_account_no")
	private String guarantorAccountNo;

	@Column(name="guarantor_ssn")
	private String guarantorSsn;

	@Column(name="guarantor_dob")
	private Date guarantorDob;

	@Column(name="guarantor_sex")
	private Integer guarantorSex;

	@Column(name="guarantor_marital_status")
	private Integer guarantorMaritalStatus;

	@Column(name="guarantor_address")
	private String guarantorAddress;

	@Column(name="guarantor_unknown1")
	private String guarantorUnknown1;

	@Column(name="guarantor_city")
	private String guarantorCity;

	@Column(name="guarantor_zip")
	private String guarantorZip;

	@Column(name="guarantor_home_phone")
	private String guarantorHomePhone;

	@Column(name="guarantor_work_phone")
	private String guarantorWorkPhone;

	@Column(name="guarantor_unknown2")
	private Boolean guarantorUnknown2;

	@Column(name="guarantor_mail_id")
	private String guarantorMailId;

	@Column(name="guarantor_mobile")
	private String guarantorMobile;

	@Column(name="guarantor_unknown3")
	private String guarantorUnknown3;

	@Column(name="guarantor_custom_field")
	private String guarantorCustomField;

	@Column(name="guarantor_unknown4")
	private Integer guarantorUnknown4;

	@Column(name="guarantor_last_modified_date")
	private Date guarantorLastModifiedDate;

	@Column(name="guarantor_state")
	private String guarantorState;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="tempid")
	private Integer tempid;

	@Column(name="guarantor_patientid")
	private Integer guarantorPatientid;

	@Column(name="guarantor_relation")
	private Integer guarantorRelation;

	@Column(name="guarantor_isactive")
	private Boolean guarantorIsactive;

	@Column(name="guarantor_last_modifiedby")
	private Integer guarantorLastModifiedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="guarantor_last_modifiedon")
	private Timestamp guarantorLastModifiedon;

	@Column(name="guarantor_createdby")
	private String guarantorCreatedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="guarantor_createdon")
	private Timestamp guarantorCreatedon;

	@Column(name="guarantor_maiden_name")
	private String guarantorMaidenName;

	@Column(name="guarantor_notes")
	private String guarantorNotes;

	@OneToMany(mappedBy="patientRegistrationGuarantorid", fetch=FetchType.LAZY)
	@JsonBackReference
	List<PatientRegistration> patientRegTable;
	
	public Integer getGuarantorKey() {
		return guarantorKey;
	}

	public void setGuarantorKey(Integer guarantorKey) {
		this.guarantorKey = guarantorKey;
	}

	public String getGuarantorLastName() {
		return guarantorLastName;
	}

	public void setGuarantorLastName(String guarantorLastName) {
		this.guarantorLastName = guarantorLastName;
	}

	public String getGuarantorMiddleInitial() {
		return guarantorMiddleInitial;
	}

	public void setGuarantorMiddleInitial(String guarantorMiddleInitial) {
		this.guarantorMiddleInitial = guarantorMiddleInitial;
	}

	public String getGuarantorFirstName() {
		return guarantorFirstName;
	}

	public void setGuarantorFirstName(String guarantorFirstName) {
		this.guarantorFirstName = guarantorFirstName;
	}

	public String getGuarantorAccountNo() {
		return guarantorAccountNo;
	}

	public void setGuarantorAccountNo(String guarantorAccountNo) {
		this.guarantorAccountNo = guarantorAccountNo;
	}

	public String getGuarantorSsn() {
		return guarantorSsn;
	}

	public void setGuarantorSsn(String guarantorSsn) {
		this.guarantorSsn = guarantorSsn;
	}

	public Date getGuarantorDob() {
		return guarantorDob;
	}

	public void setGuarantorDob(Date guarantorDob) {
		this.guarantorDob = guarantorDob;
	}

	public Integer getGuarantorSex() {
		return guarantorSex;
	}

	public void setGuarantorSex(Integer guarantorSex) {
		this.guarantorSex = guarantorSex;
	}

	public Integer getGuarantorMaritalStatus() {
		return guarantorMaritalStatus;
	}

	public void setGuarantorMaritalStatus(Integer guarantorMaritalStatus) {
		this.guarantorMaritalStatus = guarantorMaritalStatus;
	}

	public String getGuarantorAddress() {
		return guarantorAddress;
	}

	public void setGuarantorAddress(String guarantorAddress) {
		this.guarantorAddress = guarantorAddress;
	}

	public String getGuarantorUnknown1() {
		return guarantorUnknown1;
	}

	public void setGuarantorUnknown1(String guarantorUnknown1) {
		this.guarantorUnknown1 = guarantorUnknown1;
	}

	public String getGuarantorCity() {
		return guarantorCity;
	}

	public void setGuarantorCity(String guarantorCity) {
		this.guarantorCity = guarantorCity;
	}

	public String getGuarantorZip() {
		return guarantorZip;
	}

	public void setGuarantorZip(String guarantorZip) {
		this.guarantorZip = guarantorZip;
	}

	public String getGuarantorHomePhone() {
		return guarantorHomePhone;
	}

	public void setGuarantorHomePhone(String guarantorHomePhone) {
		this.guarantorHomePhone = guarantorHomePhone;
	}

	public String getGuarantorWorkPhone() {
		return guarantorWorkPhone;
	}

	public void setGuarantorWorkPhone(String guarantorWorkPhone) {
		this.guarantorWorkPhone = guarantorWorkPhone;
	}

	public Boolean getGuarantorUnknown2() {
		return guarantorUnknown2;
	}

	public void setGuarantorUnknown2(Boolean guarantorUnknown2) {
		this.guarantorUnknown2 = guarantorUnknown2;
	}

	public String getGuarantorMailId() {
		return guarantorMailId;
	}

	public void setGuarantorMailId(String guarantorMailId) {
		this.guarantorMailId = guarantorMailId;
	}

	public String getGuarantorMobile() {
		return guarantorMobile;
	}

	public void setGuarantorMobile(String guarantorMobile) {
		this.guarantorMobile = guarantorMobile;
	}

	public String getGuarantorUnknown3() {
		return guarantorUnknown3;
	}

	public void setGuarantorUnknown3(String guarantorUnknown3) {
		this.guarantorUnknown3 = guarantorUnknown3;
	}

	public String getGuarantorCustomField() {
		return guarantorCustomField;
	}

	public void setGuarantorCustomField(String guarantorCustomField) {
		this.guarantorCustomField = guarantorCustomField;
	}

	public Integer getGuarantorUnknown4() {
		return guarantorUnknown4;
	}

	public void setGuarantorUnknown4(Integer guarantorUnknown4) {
		this.guarantorUnknown4 = guarantorUnknown4;
	}

	public Date getGuarantorLastModifiedDate() {
		return guarantorLastModifiedDate;
	}

	public void setGuarantorLastModifiedDate(Date guarantorLastModifiedDate) {
		this.guarantorLastModifiedDate = guarantorLastModifiedDate;
	}

	public String getGuarantorState() {
		return guarantorState;
	}

	public void setGuarantorState(String guarantorState) {
		this.guarantorState = guarantorState;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public Integer getTempid() {
		return tempid;
	}

	public void setTempid(Integer tempid) {
		this.tempid = tempid;
	}

	public Integer getGuarantorPatientid() {
		return guarantorPatientid;
	}

	public void setGuarantorPatientid(Integer guarantorPatientid) {
		this.guarantorPatientid = guarantorPatientid;
	}

	public Integer getGuarantorRelation() {
		return guarantorRelation;
	}

	public void setGuarantorRelation(Integer guarantorRelation) {
		this.guarantorRelation = guarantorRelation;
	}

	public Boolean getGuarantorIsactive() {
		return guarantorIsactive;
	}

	public void setGuarantorIsactive(Boolean guarantorIsactive) {
		this.guarantorIsactive = guarantorIsactive;
	}

	public Integer getGuarantorLastModifiedby() {
		return guarantorLastModifiedby;
	}

	public void setGuarantorLastModifiedby(Integer guarantorLastModifiedby) {
		this.guarantorLastModifiedby = guarantorLastModifiedby;
	}

	public Timestamp getGuarantorLastModifiedon() {
		return guarantorLastModifiedon;
	}

	public void setGuarantorLastModifiedon(Timestamp guarantorLastModifiedon) {
		this.guarantorLastModifiedon = guarantorLastModifiedon;
	}

	public String getGuarantorCreatedby() {
		return guarantorCreatedby;
	}

	public void setGuarantorCreatedby(String guarantorCreatedby) {
		this.guarantorCreatedby = guarantorCreatedby;
	}

	public Timestamp getGuarantorCreatedon() {
		return guarantorCreatedon;
	}

	public void setGuarantorCreatedon(Timestamp guarantorCreatedon) {
		this.guarantorCreatedon = guarantorCreatedon;
	}

	public String getGuarantorMaidenName() {
		return guarantorMaidenName;
	}

	public void setGuarantorMaidenName(String guarantorMaidenName) {
		this.guarantorMaidenName = guarantorMaidenName;
	}

	public String getGuarantorNotes() {
		return guarantorNotes;
	}

	public void setGuarantorNotes(String guarantorNotes) {
		this.guarantorNotes = guarantorNotes;
	}
	
	
}