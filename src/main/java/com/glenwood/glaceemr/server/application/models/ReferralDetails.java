package com.glenwood.glaceemr.server.application.models;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "referral_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferralDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="public.referral_details_referral_details_refid_seq")
	@SequenceGenerator(name = "public.referral_details_referral_details_refid_seq", sequenceName="public.referral_details_referral_details_refid_seq", allocationSize=1)
	@Column(name="referral_details_refid")
	private Integer referral_details_refid;

	@Column(name="referral_details_chartid")
	private Integer referral_details_chartid;

	@Column(name="referral_details_encounterid")
	private Integer referral_details_encounterid;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="referral_details_ord_on")
	private Timestamp referral_details_ord_on;

	@Column(name="referral_details_rdoctor_from")
	private String referral_details_rdoctor_from;

	@Column(name="referral_details_rdoctor_to")
	private String referral_details_rdoctor_to;

	@Column(name="referral_details_rdoctor_spec")
	private String referral_details_rdoctor_spec;

	@Column(name="referral_details_rdoctor_address")
	private String referral_details_rdoctor_address;

	@Column(name="referral_details_rdoctor_phno")
	private String referral_details_rdoctor_phno;

	@Column(name="referral_details_rdoctor_faxno")
	private String referral_details_rdoctor_faxno;

	@Column(name="referral_details_dxcode")
	private String referral_details_dxcode;

	@Column(name="referral_details_cptcode")
	private String referral_details_cptcode;

	@Column(name="referral_details_comment")
	private String referral_details_comment;

	@Column(name="referral_details_authneeded")
	private Integer referral_details_authneeded;

	@Column(name="referral_details_auth_id")
	private Integer referral_details_auth_id;

	@Column(name="referral_details_authnumber")
	private String referral_details_authnumber;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="referral_details_authdate")
	private Timestamp referral_details_authdate;

	@Column(name="referral_details_authoriseddate")
	private String referral_details_authoriseddate;

	@Column(name="referral_details_authby")
	private String referral_details_authby;

	@Column(name="referral_details_authcontact_person")
	private String referral_details_authcontact_person;

	@Column(name="referral_details_authcomment")
	private Integer referral_details_authcomment;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="referral_details_apptconfirmdate")
	private Timestamp referral_details_apptconfirmdate;

	@Column(name="referral_details_appttime")
	private String referral_details_appttime;

	@Column(name="referral_details_apptby")
	private String referral_details_apptby;

	@Column(name="referral_details_authcontactperson")
	private String referral_details_authcontactperson;

	@Column(name="referral_details_ptnotified")
	private Integer referral_details_ptnotified;

	@Column(name="referral_details_apptcomment")
	private String referral_details_apptcomment;

	@Column(name="referral_details_concernreportreceived")
	private Integer referral_details_concernreportreceived;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="referral_details_concernreprtrecdate")
	private Timestamp referral_details_concernreprtrecdate;

	@Column(name="referral_details_concernreportid")
	private Integer referral_details_concernreportid;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="referral_details_revdate")
	private Timestamp referral_details_revdate;

	@Column(name="referral_details_revby")
	private String referral_details_revby;

	@Column(name="referral_details_reportverify")
	private String referral_details_reportverify;

	@Column(name="referral_details_isactive")
	private Integer referral_details_isactive;

	@Column(name="referral_details_myalert")
	private Integer referral_details_myalert;

	@Column(name="referral_details_referral_for")
	private Integer referral_details_referral_for;

	@Column(name="referral_details_printleafdetail")
	private String referral_details_printleafdetail;

	@Column(name="referral_details_scribble")
	private String referral_details_scribble;

	@Column(name="referral_details_alert")
	private String referral_details_alert;

	@Column(name="referral_details_leafid")
	private Integer referral_details_leafid;

	@Column(name="referreddoctor_loginid")
	private Integer referreddoctorLoginid;

	@Column(name="referral_details_patientid")
	private Integer referral_details_patientid;

	@Column(name="referral_order_by")
	private Integer referralOrderBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="referral_order_on")
	private Timestamp referralOrderOn;

	@Column(name="referral_cancel_by")
	private Integer referralCancelBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="referral_cancel_on")
	private Timestamp referralCancelOn;

	@Column(name="referral_receive_by")
	private Integer referralReceiveBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="referral_receive_on")
	private Timestamp referralReceiveOn;

	@Column(name="referral_reviewed_by")
	private Integer referralReviewedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="referral_reviewed_on")
	private Timestamp referralReviewedOn;

	@Column(name="referral_hospitalname")
	private String referralHospitalname;

	@Column(name="referral_guarantorname")
	private String referralGuarantorname;

	@Column(name="referral_no_visits")
	private Integer referralNoVisits;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="referral_expiration_date")
	private Timestamp referralExpirationDate;

	@Column(name="referral_referredtoid")
	private Integer referralReferredtoid;

	@Column(name="summary_care_record_provided")
	private Boolean summaryCareRecordProvided;

	@Column(name="summary_care_record_provided_electronic")
	private Integer summaryCareRecordProvidedElectronic;

	@Column(name="referral_critical_status")
	private Integer referralCriticalStatus;

	@Column(name="referral_delinquency_days")
	private Integer referralDelinquencyDays;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="referral_expect_result_date")
	private Timestamp referralExpectResultDate;

	@Column(name="referral_by_id")
	private Integer referralById;

	public Integer getreferral_details_refid() {
		return referral_details_refid;
	}

	public void setreferral_details_refid(Integer referral_details_refid) {
		this.referral_details_refid = referral_details_refid;
	}

	public Integer getreferral_details_chartid() {
		return referral_details_chartid;
	}

	public void setreferral_details_chartid(Integer referral_details_chartid) {
		this.referral_details_chartid = referral_details_chartid;
	}

	public Integer getreferral_details_encounterid() {
		return referral_details_encounterid;
	}

	public void setreferral_details_encounterid(Integer referral_details_encounterid) {
		this.referral_details_encounterid = referral_details_encounterid;
	}

	public Timestamp getreferral_details_ord_on() {
		return referral_details_ord_on;
	}

	public void setreferral_details_ord_on(Timestamp referral_details_ord_on) {
		this.referral_details_ord_on = referral_details_ord_on;
	}

	public String getreferral_details_rdoctor_from() {
		return referral_details_rdoctor_from;
	}

	public void setreferral_details_rdoctor_from(String referral_details_rdoctor_from) {
		this.referral_details_rdoctor_from = referral_details_rdoctor_from;
	}

	public String getreferral_details_rdoctor_to() {
		return referral_details_rdoctor_to;
	}

	public void setreferral_details_rdoctor_to(String referral_details_rdoctor_to) {
		this.referral_details_rdoctor_to = referral_details_rdoctor_to;
	}

	public String getreferral_details_rdoctor_spec() {
		return referral_details_rdoctor_spec;
	}

	public void setreferral_details_rdoctor_spec(String referral_details_rdoctor_spec) {
		this.referral_details_rdoctor_spec = referral_details_rdoctor_spec;
	}

	public String getreferral_details_rdoctor_address() {
		return referral_details_rdoctor_address;
	}

	public void setreferral_details_rdoctor_address(String referral_details_rdoctor_address) {
		this.referral_details_rdoctor_address = referral_details_rdoctor_address;
	}

	public String getreferral_details_rdoctor_phno() {
		return referral_details_rdoctor_phno;
	}

	public void setreferral_details_rdoctor_phno(String referral_details_rdoctor_phno) {
		this.referral_details_rdoctor_phno = referral_details_rdoctor_phno;
	}

	public String getreferral_details_rdoctor_faxno() {
		return referral_details_rdoctor_faxno;
	}

	public void setreferral_details_rdoctor_faxno(String referral_details_rdoctor_faxno) {
		this.referral_details_rdoctor_faxno = referral_details_rdoctor_faxno;
	}

	public String getreferral_details_dxcode() {
		return referral_details_dxcode;
	}

	public void setreferral_details_dxcode(String referral_details_dxcode) {
		this.referral_details_dxcode = referral_details_dxcode;
	}

	public String getreferral_details_cptcode() {
		return referral_details_cptcode;
	}

	public void setreferral_details_cptcode(String referral_details_cptcode) {
		this.referral_details_cptcode = referral_details_cptcode;
	}

	public String getreferral_details_comment() {
		return referral_details_comment;
	}

	public void setreferral_details_comment(String referral_details_comment) {
		this.referral_details_comment = referral_details_comment;
	}

	public Integer getreferral_details_authneeded() {
		return referral_details_authneeded;
	}

	public void setreferral_details_authneeded(Integer referral_details_authneeded) {
		this.referral_details_authneeded = referral_details_authneeded;
	}

	public Integer getreferral_details_auth_id() {
		return referral_details_auth_id;
	}

	public void setreferral_details_auth_id(Integer referral_details_auth_id) {
		this.referral_details_auth_id = referral_details_auth_id;
	}

	public String getreferral_details_authnumber() {
		return referral_details_authnumber;
	}

	public void setreferral_details_authnumber(String referral_details_authnumber) {
		this.referral_details_authnumber = referral_details_authnumber;
	}

	public Timestamp getreferral_details_authdate() {
		return referral_details_authdate;
	}

	public void setreferral_details_authdate(Timestamp referral_details_authdate) {
		this.referral_details_authdate = referral_details_authdate;
	}

	public String getreferral_details_authoriseddate() {
		return referral_details_authoriseddate;
	}

	public void setreferral_details_authoriseddate(String referral_details_authoriseddate) {
		this.referral_details_authoriseddate = referral_details_authoriseddate;
	}

	public String getreferral_details_authby() {
		return referral_details_authby;
	}

	public void setreferral_details_authby(String referral_details_authby) {
		this.referral_details_authby = referral_details_authby;
	}

	public String getreferral_details_authcontact_person() {
		return referral_details_authcontact_person;
	}

	public void setreferral_details_authcontact_person(String referral_details_authcontact_person) {
		this.referral_details_authcontact_person = referral_details_authcontact_person;
	}

	public Integer getreferral_details_authcomment() {
		return referral_details_authcomment;
	}

	public void setreferral_details_authcomment(Integer referral_details_authcomment) {
		this.referral_details_authcomment = referral_details_authcomment;
	}

	public Timestamp getreferral_details_apptconfirmdate() {
		return referral_details_apptconfirmdate;
	}

	public void setreferral_details_apptconfirmdate(Timestamp referral_details_apptconfirmdate) {
		this.referral_details_apptconfirmdate = referral_details_apptconfirmdate;
	}

	public String getreferral_details_appttime() {
		return referral_details_appttime;
	}

	public void setreferral_details_appttime(String referral_details_appttime) {
		this.referral_details_appttime = referral_details_appttime;
	}

	public String getreferral_details_apptby() {
		return referral_details_apptby;
	}

	public void setreferral_details_apptby(String referral_details_apptby) {
		this.referral_details_apptby = referral_details_apptby;
	}

	public String getreferral_details_authcontactperson() {
		return referral_details_authcontactperson;
	}

	public void setreferral_details_authcontactperson(String referral_details_authcontactperson) {
		this.referral_details_authcontactperson = referral_details_authcontactperson;
	}

	public Integer getreferral_details_ptnotified() {
		return referral_details_ptnotified;
	}

	public void setreferral_details_ptnotified(Integer referral_details_ptnotified) {
		this.referral_details_ptnotified = referral_details_ptnotified;
	}

	public String getreferral_details_apptcomment() {
		return referral_details_apptcomment;
	}

	public void setreferral_details_apptcomment(String referral_details_apptcomment) {
		this.referral_details_apptcomment = referral_details_apptcomment;
	}

	public Integer getreferral_details_concernreportreceived() {
		return referral_details_concernreportreceived;
	}

	public void setreferral_details_concernreportreceived(Integer referral_details_concernreportreceived) {
		this.referral_details_concernreportreceived = referral_details_concernreportreceived;
	}

	public Timestamp getreferral_details_concernreprtrecdate() {
		return referral_details_concernreprtrecdate;
	}

	public void setreferral_details_concernreprtrecdate(Timestamp referral_details_concernreprtrecdate) {
		this.referral_details_concernreprtrecdate = referral_details_concernreprtrecdate;
	}

	public Integer getreferral_details_concernreportid() {
		return referral_details_concernreportid;
	}

	public void setreferral_details_concernreportid(Integer referral_details_concernreportid) {
		this.referral_details_concernreportid = referral_details_concernreportid;
	}

	public Timestamp getreferral_details_revdate() {
		return referral_details_revdate;
	}

	public void setreferral_details_revdate(Timestamp referral_details_revdate) {
		this.referral_details_revdate = referral_details_revdate;
	}

	public String getreferral_details_revby() {
		return referral_details_revby;
	}

	public void setreferral_details_revby(String referral_details_revby) {
		this.referral_details_revby = referral_details_revby;
	}

	public String getreferral_details_reportverify() {
		return referral_details_reportverify;
	}

	public void setreferral_details_reportverify(String referral_details_reportverify) {
		this.referral_details_reportverify = referral_details_reportverify;
	}

	public Integer getreferral_details_isactive() {
		return referral_details_isactive;
	}

	public void setreferral_details_isactive(Integer referral_details_isactive) {
		this.referral_details_isactive = referral_details_isactive;
	}

	public Integer getreferral_details_myalert() {
		return referral_details_myalert;
	}

	public void setreferral_details_myalert(Integer referral_details_myalert) {
		this.referral_details_myalert = referral_details_myalert;
	}

	public Integer getreferral_details_referral_for() {
		return referral_details_referral_for;
	}

	public void setreferral_details_referral_for(Integer referral_details_referral_for) {
		this.referral_details_referral_for = referral_details_referral_for;
	}

	public String getreferral_details_printleafdetail() {
		return referral_details_printleafdetail;
	}

	public void setreferral_details_printleafdetail(String referral_details_printleafdetail) {
		this.referral_details_printleafdetail = referral_details_printleafdetail;
	}

	public String getreferral_details_scribble() {
		return referral_details_scribble;
	}

	public void setreferral_details_scribble(String referral_details_scribble) {
		this.referral_details_scribble = referral_details_scribble;
	}

	public String getreferral_details_alert() {
		return referral_details_alert;
	}

	public void setreferral_details_alert(String referral_details_alert) {
		this.referral_details_alert = referral_details_alert;
	}

	public Integer getreferral_details_leafid() {
		return referral_details_leafid;
	}

	public void setreferral_details_leafid(Integer referral_details_leafid) {
		this.referral_details_leafid = referral_details_leafid;
	}

	public Integer getReferreddoctorLoginid() {
		return referreddoctorLoginid;
	}

	public void setReferreddoctorLoginid(Integer referreddoctorLoginid) {
		this.referreddoctorLoginid = referreddoctorLoginid;
	}

	public Integer getreferral_details_patientid() {
		return referral_details_patientid;
	}

	public void setreferral_details_patientid(Integer referral_details_patientid) {
		this.referral_details_patientid = referral_details_patientid;
	}

	public Integer getReferralOrderBy() {
		return referralOrderBy;
	}

	public void setReferralOrderBy(Integer referralOrderBy) {
		this.referralOrderBy = referralOrderBy;
	}

	public Timestamp getReferralOrderOn() {
		return referralOrderOn;
	}

	public void setReferralOrderOn(Timestamp referralOrderOn) {
		this.referralOrderOn = referralOrderOn;
	}

	public Integer getReferralCancelBy() {
		return referralCancelBy;
	}

	public void setReferralCancelBy(Integer referralCancelBy) {
		this.referralCancelBy = referralCancelBy;
	}

	public Timestamp getReferralCancelOn() {
		return referralCancelOn;
	}

	public void setReferralCancelOn(Timestamp referralCancelOn) {
		this.referralCancelOn = referralCancelOn;
	}

	public Integer getReferralReceiveBy() {
		return referralReceiveBy;
	}

	public void setReferralReceiveBy(Integer referralReceiveBy) {
		this.referralReceiveBy = referralReceiveBy;
	}

	public Timestamp getReferralReceiveOn() {
		return referralReceiveOn;
	}

	public void setReferralReceiveOn(Timestamp referralReceiveOn) {
		this.referralReceiveOn = referralReceiveOn;
	}

	public Integer getReferralReviewedBy() {
		return referralReviewedBy;
	}

	public void setReferralReviewedBy(Integer referralReviewedBy) {
		this.referralReviewedBy = referralReviewedBy;
	}

	public Timestamp getReferralReviewedOn() {
		return referralReviewedOn;
	}

	public void setReferralReviewedOn(Timestamp referralReviewedOn) {
		this.referralReviewedOn = referralReviewedOn;
	}

	public String getReferralHospitalname() {
		return referralHospitalname;
	}

	public void setReferralHospitalname(String referralHospitalname) {
		this.referralHospitalname = referralHospitalname;
	}

	public String getReferralGuarantorname() {
		return referralGuarantorname;
	}

	public void setReferralGuarantorname(String referralGuarantorname) {
		this.referralGuarantorname = referralGuarantorname;
	}

	public Integer getReferralNoVisits() {
		return referralNoVisits;
	}

	public void setReferralNoVisits(Integer referralNoVisits) {
		this.referralNoVisits = referralNoVisits;
	}

	public Timestamp getReferralExpirationDate() {
		return referralExpirationDate;
	}

	public void setReferralExpirationDate(Timestamp referralExpirationDate) {
		this.referralExpirationDate = referralExpirationDate;
	}

	public Integer getReferralReferredtoid() {
		return referralReferredtoid;
	}

	public void setReferralReferredtoid(Integer referralReferredtoid) {
		this.referralReferredtoid = referralReferredtoid;
	}

	public Boolean getSummaryCareRecordProvided() {
		return summaryCareRecordProvided;
	}

	public void setSummaryCareRecordProvided(Boolean summaryCareRecordProvided) {
		this.summaryCareRecordProvided = summaryCareRecordProvided;
	}

	public Integer getSummaryCareRecordProvidedElectronic() {
		return summaryCareRecordProvidedElectronic;
	}

	public void setSummaryCareRecordProvidedElectronic(
			Integer summaryCareRecordProvidedElectronic) {
		this.summaryCareRecordProvidedElectronic = summaryCareRecordProvidedElectronic;
	}

	public Integer getReferralCriticalStatus() {
		return referralCriticalStatus;
	}

	public void setReferralCriticalStatus(Integer referralCriticalStatus) {
		this.referralCriticalStatus = referralCriticalStatus;
	}

	public Integer getReferralDelinquencyDays() {
		return referralDelinquencyDays;
	}

	public void setReferralDelinquencyDays(Integer referralDelinquencyDays) {
		this.referralDelinquencyDays = referralDelinquencyDays;
	}

	public Timestamp getReferralExpectResultDate() {
		return referralExpectResultDate;
	}

	public void setReferralExpectResultDate(Timestamp referralExpectResultDate) {
		this.referralExpectResultDate = referralExpectResultDate;
	}

	public Integer getReferralById() {
		return referralById;
	}

	public void setReferralById(Integer referralById) {
		this.referralById = referralById;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="referral_details_rdoctor_from", referencedColumnName="emp_profile_fullname", insertable=false, updatable=false)
	@JsonBackReference
	EmployeeProfile empProfile;
}