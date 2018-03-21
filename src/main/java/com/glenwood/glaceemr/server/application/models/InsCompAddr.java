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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "ins_comp_addr")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InsCompAddr {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ins_comp_addr_ins_comp_addr_id_seq")
	@SequenceGenerator(name = "ins_comp_addr_ins_comp_addr_id_seq", sequenceName = "ins_comp_addr_ins_comp_addr_id_seq", allocationSize = 1)
	@Column(name="ins_comp_addr_id")
	private Integer insCompAddrId;

	@Column(name="ins_comp_addr_inscompany_id")
	private Integer insCompAddrInscompanyId;

	@Column(name="ins_comp_addr_address")
	private String insCompAddrAddress;

	@Column(name="ins_comp_addr_city")
	private String insCompAddrCity;

	@Column(name="ins_comp_addr_state")
	private String insCompAddrState;

	@Column(name="ins_comp_addr_zip")
	private String insCompAddrZip;

	@Column(name="ins_comp_addr_isactive")
	private Boolean insCompAddrIsactive;

	@Column(name="ins_comp_addr_claim_processing_time")
	private Short insCompAddrClaimProcessingTime;

	@Column(name="ins_comp_addr_appeal_limit")
	private Short insCompAddrAppealLimit;

	@Column(name="ins_comp_addr_filing_limit")
	private Short insCompAddrFilingLimit;

	@Column(name="ins_comp_addr_phoneno1")
	private String insCompAddrPhoneno1;

	@Column(name="ins_comp_addr_ins")
	private Boolean insCompAddrIns;

	@Column(name="ins_comp_addr_count")
	private Short insCompAddrCount;

	@Column(name="ins_comp_addr_ins_name")
	private String insCompAddrInsName;

	@Column(name="ins_comp_addr_contact_person")
	private String insCompAddrContactPerson;

	@Column(name="ins_comp_addr_phoneno2")
	private String insCompAddrPhoneno2;

	@Column(name="ins_comp_addr_fax_no")
	private String insCompAddrFaxNo;

	@Column(name="ins_comp_addr_email")
	private String insCompAddrEmail;

	@Column(name="ins_comp_addr_alt_email")
	private String insCompAddrAltEmail;

	@Column(name="ins_comp_addr_website")
	private String insCompAddrWebsite;

	@Column(name="ins_comp_addr_dont_send_claim")
	private Boolean insCompAddrDontSendClaim;

	@Column(name="ins_comp_addr_pri_submission_method")
	private Short insCompAddrPriSubmissionMethod;

	@Column(name="ins_comp_addr_insurance_group")
	private Short insCompAddrInsuranceGroup;

	@Column(name="ins_comp_addr_insurance_type")
	private Short insCompAddrInsuranceType;

	@Column(name="ins_comp_addr_patient_signature")
	private Short insCompAddrPatientSignature;

	@Column(name="ins_comp_addr_insured_signature")
	private Short insCompAddrInsuredSignature;

	@Column(name="ins_comp_addr_physician_signature")
	private Short insCompAddrPhysicianSignature;

	@Column(name="ins_comp_addr_pin_on_forms")
	private Short insCompAddrPinOnForms;

	@Column(name="ins_comp_addr_claimextra_1")
	private Short insCompAddrClaimextra1;

	@Column(name="ins_comp_addr_claimextra_2")
	private Short insCompAddrClaimextra2;

	@Column(name="ins_comp_addr_payment_code")
	private Integer insCompAddrPaymentCode;

	@Column(name="ins_comp_addr_adjustment_code")
	private Integer insCompAddrAdjustmentCode;

	@Column(name="ins_comp_addr_ins_withhold_adjustcode")
	private Integer insCompAddrInsWithholdAdjustcode;

	@Column(name="ins_comp_addr_deductable_code")
	private Integer insCompAddrDeductableCode;

	@Column(name="ins_comp_addr_refund_code")
	private Integer insCompAddrRefundCode;

	@Column(name="ins_comp_addr_denial_code")
	private Integer insCompAddrDenialCode;

	@Column(name="ins_comp_addr_extra1")
	private String insCompAddrExtra1;

	@Column(name="ins_comp_addr_extra2")
	private String insCompAddrExtra2;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="ins_comp_addr_created_date")
	private Timestamp insCompAddrCreatedDate;

	@Column(name="ins_comp_addr_created_by")
	private String insCompAddrCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="ins_comp_addr_modified_date")
	private Timestamp insCompAddrModifiedDate;

	@Column(name="ins_comp_addr_modified_by")
	private String insCompAddrModifiedBy;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="ins_comp_addr_sec_submission_method")
	private Long insCompAddrSecSubmissionMethod;

	@Column(name="ins_comp_addr_ins_groupid")
	private Integer insCompAddrInsGroupid;

	@Column(name="ins_comp_addr_third_ins_eclaim")
	private Long insCompAddrThirdInsEclaim;

	@Column(name="ins_comp_addr_labcode")
	private String insCompAddrLabcode;

	@Column(name="ins_comp_addr_isgroup")
	private Boolean insCompAddrIsgroup;

	@Column(name="ins_comp_addr_isindividual")
	private Boolean insCompAddrIsindividual;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="ins_comp_addr_inscompany_id", referencedColumnName="ins_company_id" , insertable=false, updatable=false)
	private InsCompany insCompany;
	
	public InsCompany getInsCompany() {
		return insCompany;
	}

	public void setInsCompany(InsCompany insCompany) {
		this.insCompany = insCompany;
	}

	public Integer getInsCompAddrId() {
		return insCompAddrId;
	}

	public void setInsCompAddrId(Integer insCompAddrId) {
		this.insCompAddrId = insCompAddrId;
	}

	public Integer getInsCompAddrInscompanyId() {
		return insCompAddrInscompanyId;
	}

	public void setInsCompAddrInscompanyId(Integer insCompAddrInscompanyId) {
		this.insCompAddrInscompanyId = insCompAddrInscompanyId;
	}

	public String getInsCompAddrAddress() {
		return insCompAddrAddress;
	}

	public void setInsCompAddrAddress(String insCompAddrAddress) {
		this.insCompAddrAddress = insCompAddrAddress;
	}

	public String getInsCompAddrCity() {
		return insCompAddrCity;
	}

	public void setInsCompAddrCity(String insCompAddrCity) {
		this.insCompAddrCity = insCompAddrCity;
	}

	public String getInsCompAddrState() {
		return insCompAddrState;
	}

	public void setInsCompAddrState(String insCompAddrState) {
		this.insCompAddrState = insCompAddrState;
	}

	public String getInsCompAddrZip() {
		return insCompAddrZip;
	}

	public void setInsCompAddrZip(String insCompAddrZip) {
		this.insCompAddrZip = insCompAddrZip;
	}

	public Boolean getInsCompAddrIsactive() {
		return insCompAddrIsactive;
	}

	public void setInsCompAddrIsactive(Boolean insCompAddrIsactive) {
		this.insCompAddrIsactive = insCompAddrIsactive;
	}

	public Short getInsCompAddrClaimProcessingTime() {
		return insCompAddrClaimProcessingTime;
	}

	public void setInsCompAddrClaimProcessingTime(
			Short insCompAddrClaimProcessingTime) {
		this.insCompAddrClaimProcessingTime = insCompAddrClaimProcessingTime;
	}

	public Short getInsCompAddrAppealLimit() {
		return insCompAddrAppealLimit;
	}

	public void setInsCompAddrAppealLimit(Short insCompAddrAppealLimit) {
		this.insCompAddrAppealLimit = insCompAddrAppealLimit;
	}

	public Short getInsCompAddrFilingLimit() {
		return insCompAddrFilingLimit;
	}

	public void setInsCompAddrFilingLimit(Short insCompAddrFilingLimit) {
		this.insCompAddrFilingLimit = insCompAddrFilingLimit;
	}

	public String getInsCompAddrPhoneno1() {
		return insCompAddrPhoneno1;
	}

	public void setInsCompAddrPhoneno1(String insCompAddrPhoneno1) {
		this.insCompAddrPhoneno1 = insCompAddrPhoneno1;
	}

	public Boolean getInsCompAddrIns() {
		return insCompAddrIns;
	}

	public void setInsCompAddrIns(Boolean insCompAddrIns) {
		this.insCompAddrIns = insCompAddrIns;
	}

	public Short getInsCompAddrCount() {
		return insCompAddrCount;
	}

	public void setInsCompAddrCount(Short insCompAddrCount) {
		this.insCompAddrCount = insCompAddrCount;
	}

	public String getInsCompAddrInsName() {
		return insCompAddrInsName;
	}

	public void setInsCompAddrInsName(String insCompAddrInsName) {
		this.insCompAddrInsName = insCompAddrInsName;
	}

	public String getInsCompAddrContactPerson() {
		return insCompAddrContactPerson;
	}

	public void setInsCompAddrContactPerson(String insCompAddrContactPerson) {
		this.insCompAddrContactPerson = insCompAddrContactPerson;
	}

	public String getInsCompAddrPhoneno2() {
		return insCompAddrPhoneno2;
	}

	public void setInsCompAddrPhoneno2(String insCompAddrPhoneno2) {
		this.insCompAddrPhoneno2 = insCompAddrPhoneno2;
	}

	public String getInsCompAddrFaxNo() {
		return insCompAddrFaxNo;
	}

	public void setInsCompAddrFaxNo(String insCompAddrFaxNo) {
		this.insCompAddrFaxNo = insCompAddrFaxNo;
	}

	public String getInsCompAddrEmail() {
		return insCompAddrEmail;
	}

	public void setInsCompAddrEmail(String insCompAddrEmail) {
		this.insCompAddrEmail = insCompAddrEmail;
	}

	public String getInsCompAddrAltEmail() {
		return insCompAddrAltEmail;
	}

	public void setInsCompAddrAltEmail(String insCompAddrAltEmail) {
		this.insCompAddrAltEmail = insCompAddrAltEmail;
	}

	public String getInsCompAddrWebsite() {
		return insCompAddrWebsite;
	}

	public void setInsCompAddrWebsite(String insCompAddrWebsite) {
		this.insCompAddrWebsite = insCompAddrWebsite;
	}

	public Boolean getInsCompAddrDontSendClaim() {
		return insCompAddrDontSendClaim;
	}

	public void setInsCompAddrDontSendClaim(Boolean insCompAddrDontSendClaim) {
		this.insCompAddrDontSendClaim = insCompAddrDontSendClaim;
	}

	public Short getInsCompAddrPriSubmissionMethod() {
		return insCompAddrPriSubmissionMethod;
	}

	public void setInsCompAddrPriSubmissionMethod(
			Short insCompAddrPriSubmissionMethod) {
		this.insCompAddrPriSubmissionMethod = insCompAddrPriSubmissionMethod;
	}

	public Short getInsCompAddrInsuranceGroup() {
		return insCompAddrInsuranceGroup;
	}

	public void setInsCompAddrInsuranceGroup(Short insCompAddrInsuranceGroup) {
		this.insCompAddrInsuranceGroup = insCompAddrInsuranceGroup;
	}

	public Short getInsCompAddrInsuranceType() {
		return insCompAddrInsuranceType;
	}

	public void setInsCompAddrInsuranceType(Short insCompAddrInsuranceType) {
		this.insCompAddrInsuranceType = insCompAddrInsuranceType;
	}

	public Short getInsCompAddrPatientSignature() {
		return insCompAddrPatientSignature;
	}

	public void setInsCompAddrPatientSignature(Short insCompAddrPatientSignature) {
		this.insCompAddrPatientSignature = insCompAddrPatientSignature;
	}

	public Short getInsCompAddrInsuredSignature() {
		return insCompAddrInsuredSignature;
	}

	public void setInsCompAddrInsuredSignature(Short insCompAddrInsuredSignature) {
		this.insCompAddrInsuredSignature = insCompAddrInsuredSignature;
	}

	public Short getInsCompAddrPhysicianSignature() {
		return insCompAddrPhysicianSignature;
	}

	public void setInsCompAddrPhysicianSignature(Short insCompAddrPhysicianSignature) {
		this.insCompAddrPhysicianSignature = insCompAddrPhysicianSignature;
	}

	public Short getInsCompAddrPinOnForms() {
		return insCompAddrPinOnForms;
	}

	public void setInsCompAddrPinOnForms(Short insCompAddrPinOnForms) {
		this.insCompAddrPinOnForms = insCompAddrPinOnForms;
	}

	public Short getInsCompAddrClaimextra1() {
		return insCompAddrClaimextra1;
	}

	public void setInsCompAddrClaimextra1(Short insCompAddrClaimextra1) {
		this.insCompAddrClaimextra1 = insCompAddrClaimextra1;
	}

	public Short getInsCompAddrClaimextra2() {
		return insCompAddrClaimextra2;
	}

	public void setInsCompAddrClaimextra2(Short insCompAddrClaimextra2) {
		this.insCompAddrClaimextra2 = insCompAddrClaimextra2;
	}

	public Integer getInsCompAddrPaymentCode() {
		return insCompAddrPaymentCode;
	}

	public void setInsCompAddrPaymentCode(Integer insCompAddrPaymentCode) {
		this.insCompAddrPaymentCode = insCompAddrPaymentCode;
	}

	public Integer getInsCompAddrAdjustmentCode() {
		return insCompAddrAdjustmentCode;
	}

	public void setInsCompAddrAdjustmentCode(Integer insCompAddrAdjustmentCode) {
		this.insCompAddrAdjustmentCode = insCompAddrAdjustmentCode;
	}

	public Integer getInsCompAddrInsWithholdAdjustcode() {
		return insCompAddrInsWithholdAdjustcode;
	}

	public void setInsCompAddrInsWithholdAdjustcode(
			Integer insCompAddrInsWithholdAdjustcode) {
		this.insCompAddrInsWithholdAdjustcode = insCompAddrInsWithholdAdjustcode;
	}

	public Integer getInsCompAddrDeductableCode() {
		return insCompAddrDeductableCode;
	}

	public void setInsCompAddrDeductableCode(Integer insCompAddrDeductableCode) {
		this.insCompAddrDeductableCode = insCompAddrDeductableCode;
	}

	public Integer getInsCompAddrRefundCode() {
		return insCompAddrRefundCode;
	}

	public void setInsCompAddrRefundCode(Integer insCompAddrRefundCode) {
		this.insCompAddrRefundCode = insCompAddrRefundCode;
	}

	public Integer getInsCompAddrDenialCode() {
		return insCompAddrDenialCode;
	}

	public void setInsCompAddrDenialCode(Integer insCompAddrDenialCode) {
		this.insCompAddrDenialCode = insCompAddrDenialCode;
	}

	public String getInsCompAddrExtra1() {
		return insCompAddrExtra1;
	}

	public void setInsCompAddrExtra1(String insCompAddrExtra1) {
		this.insCompAddrExtra1 = insCompAddrExtra1;
	}

	public String getInsCompAddrExtra2() {
		return insCompAddrExtra2;
	}

	public void setInsCompAddrExtra2(String insCompAddrExtra2) {
		this.insCompAddrExtra2 = insCompAddrExtra2;
	}

	public Timestamp getInsCompAddrCreatedDate() {
		return insCompAddrCreatedDate;
	}

	public void setInsCompAddrCreatedDate(Timestamp insCompAddrCreatedDate) {
		this.insCompAddrCreatedDate = insCompAddrCreatedDate;
	}

	public String getInsCompAddrCreatedBy() {
		return insCompAddrCreatedBy;
	}

	public void setInsCompAddrCreatedBy(String insCompAddrCreatedBy) {
		this.insCompAddrCreatedBy = insCompAddrCreatedBy;
	}

	public Timestamp getInsCompAddrModifiedDate() {
		return insCompAddrModifiedDate;
	}

	public void setInsCompAddrModifiedDate(Timestamp insCompAddrModifiedDate) {
		this.insCompAddrModifiedDate = insCompAddrModifiedDate;
	}

	public String getInsCompAddrModifiedBy() {
		return insCompAddrModifiedBy;
	}

	public void setInsCompAddrModifiedBy(String insCompAddrModifiedBy) {
		this.insCompAddrModifiedBy = insCompAddrModifiedBy;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public Long getInsCompAddrSecSubmissionMethod() {
		return insCompAddrSecSubmissionMethod;
	}

	public void setInsCompAddrSecSubmissionMethod(
			Long insCompAddrSecSubmissionMethod) {
		this.insCompAddrSecSubmissionMethod = insCompAddrSecSubmissionMethod;
	}

	public Integer getInsCompAddrInsGroupid() {
		return insCompAddrInsGroupid;
	}

	public void setInsCompAddrInsGroupid(Integer insCompAddrInsGroupid) {
		this.insCompAddrInsGroupid = insCompAddrInsGroupid;
	}

	public Long getInsCompAddrThirdInsEclaim() {
		return insCompAddrThirdInsEclaim;
	}

	public void setInsCompAddrThirdInsEclaim(Long insCompAddrThirdInsEclaim) {
		this.insCompAddrThirdInsEclaim = insCompAddrThirdInsEclaim;
	}

	public String getInsCompAddrLabcode() {
		return insCompAddrLabcode;
	}

	public void setInsCompAddrLabcode(String insCompAddrLabcode) {
		this.insCompAddrLabcode = insCompAddrLabcode;
	}

	public Boolean getInsCompAddrIsgroup() {
		return insCompAddrIsgroup;
	}

	public void setInsCompAddrIsgroup(Boolean insCompAddrIsgroup) {
		this.insCompAddrIsgroup = insCompAddrIsgroup;
	}

	public Boolean getInsCompAddrIsindividual() {
		return insCompAddrIsindividual;
	}

	public void setInsCompAddrIsindividual(Boolean insCompAddrIsindividual) {
		this.insCompAddrIsindividual = insCompAddrIsindividual;
	}	
}