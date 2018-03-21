package com.glenwood.glaceemr.server.application.models;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
 

@Entity
@Table(name = "patient_ins_detail")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientInsDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_ins_detail_patient_ins_detail_id_seq")
	@SequenceGenerator(name = "patient_ins_detail_patient_ins_detail_id_seq", sequenceName = "patient_ins_detail_patient_ins_detail_id_seq", allocationSize = 1)
	@Column(name="patient_ins_detail_id")
	private Long patientInsDetailId;

	@Column(name="patient_ins_detail_patientid")
	private Long patientInsDetailPatientid;

	@Column(name="patient_ins_detail_insaddressid")
	private Integer patientInsDetailInsaddressid;

	@Column(name="patient_ins_detail_insno")
	private Short patientInsDetailInsno;

	@Column(name="patient_ins_detail_instype")
	private Integer patientInsDetailInstype;

	@Column(name="patient_ins_detail_patientinsuranceid")
	private String patientInsDetailPatientinsuranceid;

	@Column(name="patient_ins_detail_subscribername")
	private String patientInsDetailSubscribername;

	@Column(name="patient_ins_detail_relationtosubs")
	private Short patientInsDetailRelationtosubs;

	@Column(name="patient_ins_detail_validfrom")
	private Date patientInsDetailValidfrom;

	@Column(name="patient_ins_detail_validto")
	private Date patientInsDetailValidto;

	@Column(name="patient_ins_detail_isactive")
	private Boolean patientInsDetailIsactive;

	@Column(name="patient_ins_detail_last_modified_by")
	private Long patientInsDetailLastModifiedBy;

	@Column(name="patient_ins_detail_last_modified_date")
	private Date patientInsDetailLastModifiedDate;

	@Column(name="patient_ins_detail_contact_person")
	private String patientInsDetailContactPerson;

	@Column(name="patient_ins_detail_groupno")
	private String patientInsDetailGroupno;

	@Column(name="patient_ins_detail_groupname")
	private String patientInsDetailGroupname;

	@Column(name="patient_ins_detail_insphone")
	private String patientInsDetailInsphone;

	@Column(name="patient_ins_detail_extension")
	private String patientInsDetailExtension;

	@Column(name="patient_ins_detail_faxno")
	private String patientInsDetailFaxno;

	@Column(name="patient_ins_detail_copay")
	private Double patientInsDetailCopay;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="patient_ins_detail_plantype")
	private Short patientInsDetailPlantype;

	@Column(name="patient_ins_detail_capitated")
	private Boolean patientInsDetailCapitated;

	@Column(name="patient_ins_detail_deductible")
	private Double patientInsDetailDeductible;

	@Column(name="patient_ins_detail_subscriber_dob")
	private Date patientInsDetailSubscriberDob;

	@Column(name="patient_ins_detail_bookmark")
	private String patientInsDetailBookmark;

	@Column(name="patient_ins_detail_max_visit")
	private Integer patientInsDetailMaxVisit;

	@Column(name="patient_ins_detail_sls_present")
	private Boolean patientInsDetailSlsPresent;

	@Column(name="patient_ins_detail_dme_present")
	private Boolean patientInsDetailDmePresent;

	@Column(name="patient_ins_detail_amount_met")
	private Double patientInsDetailAmountMet;

	@Column(name="patient_ins_detail_out_of_pocket")
	private Double patientInsDetailOutOfPocket;

	@Column(name="patient_ins_detail_oop_met")
	private Double patientInsDetailOopMet;

	@Column(name="patient_ins_detail_referral_req")
	private Boolean patientInsDetailReferralReq;

	@Column(name="patient_ins_detail_dme_coins")
	private String patientInsDetailDmeCoins;

	@Column(name="patient_ins_detail_sleepstudy_coins")
	private String patientInsDetailSleepstudyCoins;

	@Column(name="patient_ins_detail_coins")
	private String patientInsDetailCoins;

	@Column(name="patient_ins_detail_lifetimemax")
	private Integer patientInsDetailLifetimemax;

	@Column(name="patient_ins_detail_issueddate")
	private Date patientInsDetailIssueddate;

	@Column(name="patient_ins_detail_auth_req")
	private String patientInsDetailAuthReq;

	@Column(name="patient_ins_detail_isdeductable")
	private Boolean patientInsDetailIsdeductable;

	@Column(name="patient_ins_detail_deductablemet")
	private Boolean patientInsDetailDeductablemet;

	@Column(name="patient_ins_detail_other_copay")
	private Double patientInsDetailOtherCopay;

	@Column(name="patient_ins_detail_procedure_copay")
	private Double patientInsDetailProcedureCopay;

	@Column(name="patient_ins_detail_isvfceligible")
	private Boolean patientInsDetailIsvfceligible;

	@Column(name="patient_ins_detail_fin_class")
	private Integer patientInsDetailFinClass;

	@Column(name="patient_ins_detail_program_type")
	private String patientInsDetailProgramType;

	@Column(name="patient_ins_detail_program_startdate")
	private Date patientInsDetailProgramStartdate;
	
	@Column(name="patient_ins_detail_program_enddate")
	private Date patientInsDetailProgramEnddate;

	public Date getPatientInsDetailProgramEnddate() {
		return patientInsDetailProgramEnddate;
	}

	public void setPatientInsDetailProgramEnddate(
			Date patientInsDetailProgramEnddate) {
		this.patientInsDetailProgramEnddate = patientInsDetailProgramEnddate;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="patient_ins_detail_insaddressid", referencedColumnName="ins_comp_addr_id" , insertable=false, updatable=false)
	private InsCompAddr insCompAddr;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="patient_ins_detail_patientid", referencedColumnName="patient_registration_id" , insertable=false, updatable=false)
	private PatientRegistration patientRegistrationTable;
	
	public PatientRegistration getPatientRegistrationTable() {
		return patientRegistrationTable;
	}

	public void setPatientRegistrationTable(
			PatientRegistration patientRegistrationTable) {
		this.patientRegistrationTable = patientRegistrationTable;
	}

	public InsCompAddr getInsCompAddr() {
		return insCompAddr;
	}

	public void setInsCompAddr(InsCompAddr insCompAddr) {
		this.insCompAddr = insCompAddr;
	}
	
	public Long getPatientInsDetailId() {
		return patientInsDetailId;
	}

		public void setPatientInsDetailId(Long patientInsDetailId) {
		this.patientInsDetailId = patientInsDetailId;
	}

	public Long getPatientInsDetailPatientid() {
		return patientInsDetailPatientid;
	}

	public void setPatientInsDetailPatientid(Long patientInsDetailPatientid) {
		this.patientInsDetailPatientid = patientInsDetailPatientid;
	}

	public Integer getPatientInsDetailInsaddressid() {
		return patientInsDetailInsaddressid;
	}

	public void setPatientInsDetailInsaddressid(Integer patientInsDetailInsaddressid) {
		this.patientInsDetailInsaddressid = patientInsDetailInsaddressid;
	}

	public Short getPatientInsDetailInsno() {
		return patientInsDetailInsno;
	}

	public void setPatientInsDetailInsno(Short patientInsDetailInsno) {
		this.patientInsDetailInsno = patientInsDetailInsno;
	}

	public Integer getPatientInsDetailInstype() {
		return patientInsDetailInstype;
	}

	public void setPatientInsDetailInstype(Integer patientInsDetailInstype) {
		this.patientInsDetailInstype = patientInsDetailInstype;
	}

	public String getPatientInsDetailPatientinsuranceid() {
		return patientInsDetailPatientinsuranceid;
	}

	public void setPatientInsDetailPatientinsuranceid(
			String patientInsDetailPatientinsuranceid) {
		this.patientInsDetailPatientinsuranceid = patientInsDetailPatientinsuranceid;
	}

	public String getPatientInsDetailSubscribername() {
		return patientInsDetailSubscribername;
	}

	public void setPatientInsDetailSubscribername(
			String patientInsDetailSubscribername) {
		this.patientInsDetailSubscribername = patientInsDetailSubscribername;
	}

	public Short getPatientInsDetailRelationtosubs() {
		return patientInsDetailRelationtosubs;
	}

	public void setPatientInsDetailRelationtosubs(
			Short patientInsDetailRelationtosubs) {
		this.patientInsDetailRelationtosubs = patientInsDetailRelationtosubs;
	}

	public Date getPatientInsDetailValidfrom() {
		return patientInsDetailValidfrom;
	}

	public void setPatientInsDetailValidfrom(Date patientInsDetailValidfrom) {
		this.patientInsDetailValidfrom = patientInsDetailValidfrom;
	}

	public Date getPatientInsDetailValidto() {
		return patientInsDetailValidto;
	}

	public void setPatientInsDetailValidto(Date patientInsDetailValidto) {
		this.patientInsDetailValidto = patientInsDetailValidto;
	}

	public Boolean getPatientInsDetailIsactive() {
		return patientInsDetailIsactive;
	}

	public void setPatientInsDetailIsactive(Boolean patientInsDetailIsactive) {
		this.patientInsDetailIsactive = patientInsDetailIsactive;
	}

	public Long getPatientInsDetailLastModifiedBy() {
		return patientInsDetailLastModifiedBy;
	}

	public void setPatientInsDetailLastModifiedBy(
			Long patientInsDetailLastModifiedBy) {
		this.patientInsDetailLastModifiedBy = patientInsDetailLastModifiedBy;
	}

	public Date getPatientInsDetailLastModifiedDate() {
		return patientInsDetailLastModifiedDate;
	}

	public void setPatientInsDetailLastModifiedDate(
			Date patientInsDetailLastModifiedDate) {
		this.patientInsDetailLastModifiedDate = patientInsDetailLastModifiedDate;
	}

	public String getPatientInsDetailContactPerson() {
		return patientInsDetailContactPerson;
	}

	public void setPatientInsDetailContactPerson(
			String patientInsDetailContactPerson) {
		this.patientInsDetailContactPerson = patientInsDetailContactPerson;
	}

	public String getPatientInsDetailGroupno() {
		return patientInsDetailGroupno;
	}

	public void setPatientInsDetailGroupno(String patientInsDetailGroupno) {
		this.patientInsDetailGroupno = patientInsDetailGroupno;
	}

	public String getPatientInsDetailGroupname() {
		return patientInsDetailGroupname;
	}

	public void setPatientInsDetailGroupname(String patientInsDetailGroupname) {
		this.patientInsDetailGroupname = patientInsDetailGroupname;
	}

	public String getPatientInsDetailInsphone() {
		return patientInsDetailInsphone;
	}

	public void setPatientInsDetailInsphone(String patientInsDetailInsphone) {
		this.patientInsDetailInsphone = patientInsDetailInsphone;
	}

	public String getPatientInsDetailExtension() {
		return patientInsDetailExtension;
	}

	public void setPatientInsDetailExtension(String patientInsDetailExtension) {
		this.patientInsDetailExtension = patientInsDetailExtension;
	}

	public String getPatientInsDetailFaxno() {
		return patientInsDetailFaxno;
	}

	public void setPatientInsDetailFaxno(String patientInsDetailFaxno) {
		this.patientInsDetailFaxno = patientInsDetailFaxno;
	}

	public Double getPatientInsDetailCopay() {
		return patientInsDetailCopay;
	}

	public void setPatientInsDetailCopay(Double patientInsDetailCopay) {
		this.patientInsDetailCopay = patientInsDetailCopay;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public Short getPatientInsDetailPlantype() {
		return patientInsDetailPlantype;
	}

	public void setPatientInsDetailPlantype(Short patientInsDetailPlantype) {
		this.patientInsDetailPlantype = patientInsDetailPlantype;
	}

	public Boolean getPatientInsDetailCapitated() {
		return patientInsDetailCapitated;
	}

	public void setPatientInsDetailCapitated(Boolean patientInsDetailCapitated) {
		this.patientInsDetailCapitated = patientInsDetailCapitated;
	}

	public Double getPatientInsDetailDeductible() {
		return patientInsDetailDeductible;
	}

	public void setPatientInsDetailDeductible(Double patientInsDetailDeductible) {
		this.patientInsDetailDeductible = patientInsDetailDeductible;
	}

	public Date getPatientInsDetailSubscriberDob() {
		return patientInsDetailSubscriberDob;
	}

	public void setPatientInsDetailSubscriberDob(Date patientInsDetailSubscriberDob) {
		this.patientInsDetailSubscriberDob = patientInsDetailSubscriberDob;
	}

	public String getPatientInsDetailBookmark() {
		return patientInsDetailBookmark;
	}

	public void setPatientInsDetailBookmark(String patientInsDetailBookmark) {
		this.patientInsDetailBookmark = patientInsDetailBookmark;
	}

	public Integer getPatientInsDetailMaxVisit() {
		return patientInsDetailMaxVisit;
	}

	public void setPatientInsDetailMaxVisit(Integer patientInsDetailMaxVisit) {
		this.patientInsDetailMaxVisit = patientInsDetailMaxVisit;
	}

	public Boolean getPatientInsDetailSlsPresent() {
		return patientInsDetailSlsPresent;
	}

	public void setPatientInsDetailSlsPresent(Boolean patientInsDetailSlsPresent) {
		this.patientInsDetailSlsPresent = patientInsDetailSlsPresent;
	}

	public Boolean getPatientInsDetailDmePresent() {
		return patientInsDetailDmePresent;
	}

	public void setPatientInsDetailDmePresent(Boolean patientInsDetailDmePresent) {
		this.patientInsDetailDmePresent = patientInsDetailDmePresent;
	}

	public Double getPatientInsDetailAmountMet() {
		return patientInsDetailAmountMet;
	}

	public void setPatientInsDetailAmountMet(Double patientInsDetailAmountMet) {
		this.patientInsDetailAmountMet = patientInsDetailAmountMet;
	}

	public Double getPatientInsDetailOutOfPocket() {
		return patientInsDetailOutOfPocket;
	}

	public void setPatientInsDetailOutOfPocket(Double patientInsDetailOutOfPocket) {
		this.patientInsDetailOutOfPocket = patientInsDetailOutOfPocket;
	}

	public Double getPatientInsDetailOopMet() {
		return patientInsDetailOopMet;
	}

	public void setPatientInsDetailOopMet(Double patientInsDetailOopMet) {
		this.patientInsDetailOopMet = patientInsDetailOopMet;
	}

	public Boolean getPatientInsDetailReferralReq() {
		return patientInsDetailReferralReq;
	}

	public void setPatientInsDetailReferralReq(Boolean patientInsDetailReferralReq) {
		this.patientInsDetailReferralReq = patientInsDetailReferralReq;
	}

	public String getPatientInsDetailDmeCoins() {
		return patientInsDetailDmeCoins;
	}

	public void setPatientInsDetailDmeCoins(String patientInsDetailDmeCoins) {
		this.patientInsDetailDmeCoins = patientInsDetailDmeCoins;
	}

	public String getPatientInsDetailSleepstudyCoins() {
		return patientInsDetailSleepstudyCoins;
	}

	public void setPatientInsDetailSleepstudyCoins(
			String patientInsDetailSleepstudyCoins) {
		this.patientInsDetailSleepstudyCoins = patientInsDetailSleepstudyCoins;
	}

	public String getPatientInsDetailCoins() {
		return patientInsDetailCoins;
	}

	public void setPatientInsDetailCoins(String patientInsDetailCoins) {
		this.patientInsDetailCoins = patientInsDetailCoins;
	}

	public Integer getPatientInsDetailLifetimemax() {
		return patientInsDetailLifetimemax;
	}

	public void setPatientInsDetailLifetimemax(Integer patientInsDetailLifetimemax) {
		this.patientInsDetailLifetimemax = patientInsDetailLifetimemax;
	}

	public Date getPatientInsDetailIssueddate() {
		return patientInsDetailIssueddate;
	}

	public void setPatientInsDetailIssueddate(Date patientInsDetailIssueddate) {
		this.patientInsDetailIssueddate = patientInsDetailIssueddate;
	}

	public String getPatientInsDetailAuthReq() {
		return patientInsDetailAuthReq;
	}

	public void setPatientInsDetailAuthReq(String patientInsDetailAuthReq) {
		this.patientInsDetailAuthReq = patientInsDetailAuthReq;
	}

	public Boolean getPatientInsDetailIsdeductable() {
		return patientInsDetailIsdeductable;
	}

	public void setPatientInsDetailIsdeductable(Boolean patientInsDetailIsdeductable) {
		this.patientInsDetailIsdeductable = patientInsDetailIsdeductable;
	}

	public Boolean getPatientInsDetailDeductablemet() {
		return patientInsDetailDeductablemet;
	}

	public void setPatientInsDetailDeductablemet(
			Boolean patientInsDetailDeductablemet) {
		this.patientInsDetailDeductablemet = patientInsDetailDeductablemet;
	}

	public Double getPatientInsDetailOtherCopay() {
		return patientInsDetailOtherCopay;
	}

	public void setPatientInsDetailOtherCopay(Double patientInsDetailOtherCopay) {
		this.patientInsDetailOtherCopay = patientInsDetailOtherCopay;
	}

	public Double getPatientInsDetailProcedureCopay() {
		return patientInsDetailProcedureCopay;
	}

	public void setPatientInsDetailProcedureCopay(
			Double patientInsDetailProcedureCopay) {
		this.patientInsDetailProcedureCopay = patientInsDetailProcedureCopay;
	}

	public Boolean getPatientInsDetailIsvfceligible() {
		return patientInsDetailIsvfceligible;
	}

	public void setPatientInsDetailIsvfceligible(
			Boolean patientInsDetailIsvfceligible) {
		this.patientInsDetailIsvfceligible = patientInsDetailIsvfceligible;
	}

	public Integer getPatientInsDetailFinClass() {
		return patientInsDetailFinClass;
	}

	public void setPatientInsDetailFinClass(Integer patientInsDetailFinClass) {
		this.patientInsDetailFinClass = patientInsDetailFinClass;
	}

	public String getPatientInsDetailProgramType() {
		return patientInsDetailProgramType;
	}

	public void setPatientInsDetailProgramType(String patientInsDetailProgramType) {
		this.patientInsDetailProgramType = patientInsDetailProgramType;
	}

	public Date getPatientInsDetailProgramStartdate() {
		return patientInsDetailProgramStartdate;
	}

	public void setPatientInsDetailProgramStartdate(
			Date patientInsDetailProgramStartdate) {
		this.patientInsDetailProgramStartdate = patientInsDetailProgramStartdate;
	}
	
}