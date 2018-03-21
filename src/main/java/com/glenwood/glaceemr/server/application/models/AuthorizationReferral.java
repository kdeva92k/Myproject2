package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;
import java.sql.Timestamp;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;
@Entity
@Table(name = "authorization_referral")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorizationReferral {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorization_referral_authorization_referral_id_seq")
	@SequenceGenerator(name = "authorization_referral_authorization_referral_id_seq", sequenceName = "authorization_referral_authorization_referral_id_seq", allocationSize = 1)
	@Column(name="authorization_referral_id")
	private Integer authorizationReferralId;

	@Column(name="authorization_referral_doctor_id")
	private Integer authorizationReferralDoctorId;

	@Column(name="authorization_referral_patient_id")
	private Integer authorizationReferralPatientId;

	@Column(name="authorization_referral_auth_cptcode")
	private String authorizationReferralAuthCptcode;

	@Column(name="authorization_referral_start_date")
	private Date authorizationReferralStartDate;

	@Column(name="authorization_referral_end_date")
	private Date authorizationReferralEndDate;

	@Column(name="authorization_referral_total_visits")
	private Short authorizationReferralTotalVisits;

	@Column(name="authorization_referral_used_visits")
	private Short authorizationReferralUsedVisits;

	@Column(name="authorization_referral_notes_tif")
	private String authorizationReferralNotesTif;

	@Column(name="authorization_referral_auth_no")
	private String authorizationReferralAuthNo;

	@Column(name="authorization_referral_auth_ins")
	private String authorizationReferralAuthIns;

	@Column(name="authorization_referral_auth_phoneno")
	private String authorizationReferralAuthPhoneno;

	@Column(name="authorization_referral_auth_dx")
	private String authorizationReferralAuthDx;

	@Column(name="authorization_referral_login_user")
	private Integer authorizationReferralLoginUser;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="authorization_referral_last_modifieddate")
	private Timestamp authorizationReferralLastModifieddate;

	@Column(name="authorization_referral_isactive")
	private Boolean authorizationReferralIsactive;

	@Column(name="authorization_referral_reference_type")
	private Short authorizationReferralReferenceType;

	@Column(name="authorization_referral_isyearauth")
	private Boolean authorizationReferralIsyearauth;

	@Column(name="authorization_referral_maxcharges")
	private Double authorizationReferralMaxcharges;

	@Column(name="authorization_referral_usedcharges")
	private Double authorizationReferralUsedcharges;

	@Column(name="authorization_referral_priority")
	private Short authorizationReferralPriority;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="authorization_referral_rdocid")
	private Integer authorizationReferralRdocid;

	@Column(name="authorization_referral_authtype")
	private Short authorizationReferralAuthtype;

	@Column(name="authorization_referral_comments")
	private String authorizationReferralComments;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="authorization_referral_doctor_id", referencedColumnName="referring_doctor_uniqueid" , insertable=false, updatable=false)
	private ReferringDoctor  referring_doctor;

	
	public ReferringDoctor getreferring_doctor() {
		return referring_doctor;
	}

	public void setreferring_doctor(ReferringDoctor referring_doctor) {
		this.referring_doctor = referring_doctor;
	}

	public Integer getAuthorizationReferralId() {
		return authorizationReferralId;
	}

	public void setAuthorizationReferralId(Integer authorizationReferralId) {
		this.authorizationReferralId = authorizationReferralId;
	}

	public Integer getAuthorizationReferralDoctorId() {
		return authorizationReferralDoctorId;
	}

	public void setAuthorizationReferralDoctorId(
			Integer authorizationReferralDoctorId) {
		this.authorizationReferralDoctorId = authorizationReferralDoctorId;
	}

	public Integer getAuthorizationReferralPatientId() {
		return authorizationReferralPatientId;
	}

	public void setAuthorizationReferralPatientId(
			Integer authorizationReferralPatientId) {
		this.authorizationReferralPatientId = authorizationReferralPatientId;
	}

	public String getAuthorizationReferralAuthCptcode() {
		return authorizationReferralAuthCptcode;
	}

	public void setAuthorizationReferralAuthCptcode(
			String authorizationReferralAuthCptcode) {
		this.authorizationReferralAuthCptcode = authorizationReferralAuthCptcode;
	}

	public Date getAuthorizationReferralStartDate() {
		return authorizationReferralStartDate;
	}

	public void setAuthorizationReferralStartDate(
			Date authorizationReferralStartDate) {
		this.authorizationReferralStartDate = authorizationReferralStartDate;
	}

	public Date getAuthorizationReferralEndDate() {
		return authorizationReferralEndDate;
	}

	public void setAuthorizationReferralEndDate(Date authorizationReferralEndDate) {
		this.authorizationReferralEndDate = authorizationReferralEndDate;
	}

	public Short getAuthorizationReferralTotalVisits() {
		return authorizationReferralTotalVisits;
	}

	public void setAuthorizationReferralTotalVisits(
			Short authorizationReferralTotalVisits) {
		this.authorizationReferralTotalVisits = authorizationReferralTotalVisits;
	}

	public Short getAuthorizationReferralUsedVisits() {
		return authorizationReferralUsedVisits;
	}

	public void setAuthorizationReferralUsedVisits(
			Short authorizationReferralUsedVisits) {
		this.authorizationReferralUsedVisits = authorizationReferralUsedVisits;
	}

	public String getAuthorizationReferralNotesTif() {
		return authorizationReferralNotesTif;
	}

	public void setAuthorizationReferralNotesTif(
			String authorizationReferralNotesTif) {
		this.authorizationReferralNotesTif = authorizationReferralNotesTif;
	}

	public String getAuthorizationReferralAuthNo() {
		return authorizationReferralAuthNo;
	}

	public void setAuthorizationReferralAuthNo(String authorizationReferralAuthNo) {
		this.authorizationReferralAuthNo = authorizationReferralAuthNo;
	}

	public String getAuthorizationReferralAuthIns() {
		return authorizationReferralAuthIns;
	}

	public void setAuthorizationReferralAuthIns(String authorizationReferralAuthIns) {
		this.authorizationReferralAuthIns = authorizationReferralAuthIns;
	}

	public String getAuthorizationReferralAuthPhoneno() {
		return authorizationReferralAuthPhoneno;
	}

	public void setAuthorizationReferralAuthPhoneno(
			String authorizationReferralAuthPhoneno) {
		this.authorizationReferralAuthPhoneno = authorizationReferralAuthPhoneno;
	}

	public String getAuthorizationReferralAuthDx() {
		return authorizationReferralAuthDx;
	}

	public void setAuthorizationReferralAuthDx(String authorizationReferralAuthDx) {
		this.authorizationReferralAuthDx = authorizationReferralAuthDx;
	}

	public Integer getAuthorizationReferralLoginUser() {
		return authorizationReferralLoginUser;
	}

	public void setAuthorizationReferralLoginUser(
			Integer authorizationReferralLoginUser) {
		this.authorizationReferralLoginUser = authorizationReferralLoginUser;
	}

	public Timestamp getAuthorizationReferralLastModifieddate() {
		return authorizationReferralLastModifieddate;
	}

	public void setAuthorizationReferralLastModifieddate(
			Timestamp authorizationReferralLastModifieddate) {
		this.authorizationReferralLastModifieddate = authorizationReferralLastModifieddate;
	}

	public Boolean getAuthorizationReferralIsactive() {
		return authorizationReferralIsactive;
	}

	public void setAuthorizationReferralIsactive(
			Boolean authorizationReferralIsactive) {
		this.authorizationReferralIsactive = authorizationReferralIsactive;
	}

	public Short getAuthorizationReferralReferenceType() {
		return authorizationReferralReferenceType;
	}

	public void setAuthorizationReferralReferenceType(
			Short authorizationReferralReferenceType) {
		this.authorizationReferralReferenceType = authorizationReferralReferenceType;
	}

	public Boolean getAuthorizationReferralIsyearauth() {
		return authorizationReferralIsyearauth;
	}

	public void setAuthorizationReferralIsyearauth(
			Boolean authorizationReferralIsyearauth) {
		this.authorizationReferralIsyearauth = authorizationReferralIsyearauth;
	}

	public Double getAuthorizationReferralMaxcharges() {
		return authorizationReferralMaxcharges;
	}

	public void setAuthorizationReferralMaxcharges(
			Double authorizationReferralMaxcharges) {
		this.authorizationReferralMaxcharges = authorizationReferralMaxcharges;
	}

	public Double getAuthorizationReferralUsedcharges() {
		return authorizationReferralUsedcharges;
	}

	public void setAuthorizationReferralUsedcharges(
			Double authorizationReferralUsedcharges) {
		this.authorizationReferralUsedcharges = authorizationReferralUsedcharges;
	}

	public Short getAuthorizationReferralPriority() {
		return authorizationReferralPriority;
	}

	public void setAuthorizationReferralPriority(Short authorizationReferralPriority) {
		this.authorizationReferralPriority = authorizationReferralPriority;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public Integer getAuthorizationReferralRdocid() {
		return authorizationReferralRdocid;
	}

	public void setAuthorizationReferralRdocid(Integer authorizationReferralRdocid) {
		this.authorizationReferralRdocid = authorizationReferralRdocid;
	}

	public Short getAuthorizationReferralAuthtype() {
		return authorizationReferralAuthtype;
	}

	public void setAuthorizationReferralAuthtype(Short authorizationReferralAuthtype) {
		this.authorizationReferralAuthtype = authorizationReferralAuthtype;
	}

	public String getAuthorizationReferralComments() {
		return authorizationReferralComments;
	}

	public void setAuthorizationReferralComments(
			String authorizationReferralComments) {
		this.authorizationReferralComments = authorizationReferralComments;
	}

	
}