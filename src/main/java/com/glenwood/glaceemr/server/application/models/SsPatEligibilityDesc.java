package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ss_pat_eligibility_desc")
public class SsPatEligibilityDesc {

	public Integer getSsPatEligibilityDescId() {
		return ssPatEligibilityDescId;
	}

	public void setSsPatEligibilityDescId(Integer ssPatEligibilityDescId) {
		this.ssPatEligibilityDescId = ssPatEligibilityDescId;
	}

	public Integer getSsPatEligibilityDescMapEligibilityId() {
		return ssPatEligibilityDescMapEligibilityId;
	}

	public void setSsPatEligibilityDescMapEligibilityId(
			Integer ssPatEligibilityDescMapEligibilityId) {
		this.ssPatEligibilityDescMapEligibilityId = ssPatEligibilityDescMapEligibilityId;
	}

	public String getSsPatEligibilityDescHealthPlanId() {
		return ssPatEligibilityDescHealthPlanId;
	}

	public void setSsPatEligibilityDescHealthPlanId(
			String ssPatEligibilityDescHealthPlanId) {
		this.ssPatEligibilityDescHealthPlanId = ssPatEligibilityDescHealthPlanId;
	}

	public String getSsPatEligibilityDescHealthPlanName() {
		return ssPatEligibilityDescHealthPlanName;
	}

	public void setSsPatEligibilityDescHealthPlanName(
			String ssPatEligibilityDescHealthPlanName) {
		this.ssPatEligibilityDescHealthPlanName = ssPatEligibilityDescHealthPlanName;
	}

	public String getSsPatEligibilityDescCardHolderId() {
		return ssPatEligibilityDescCardHolderId;
	}

	public void setSsPatEligibilityDescCardHolderId(
			String ssPatEligibilityDescCardHolderId) {
		this.ssPatEligibilityDescCardHolderId = ssPatEligibilityDescCardHolderId;
	}

	public String getSsPatEligibilityDescCardHolderName() {
		return ssPatEligibilityDescCardHolderName;
	}

	public void setSsPatEligibilityDescCardHolderName(
			String ssPatEligibilityDescCardHolderName) {
		this.ssPatEligibilityDescCardHolderName = ssPatEligibilityDescCardHolderName;
	}

	public String getSsPatEligibilityDescGroupId() {
		return ssPatEligibilityDescGroupId;
	}

	public void setSsPatEligibilityDescGroupId(String ssPatEligibilityDescGroupId) {
		this.ssPatEligibilityDescGroupId = ssPatEligibilityDescGroupId;
	}

	public String getSsPatEligibilityDescGroupName() {
		return ssPatEligibilityDescGroupName;
	}

	public void setSsPatEligibilityDescGroupName(
			String ssPatEligibilityDescGroupName) {
		this.ssPatEligibilityDescGroupName = ssPatEligibilityDescGroupName;
	}

	public String getSsPatEligibilityDescFormularyId() {
		return ssPatEligibilityDescFormularyId;
	}

	public void setSsPatEligibilityDescFormularyId(
			String ssPatEligibilityDescFormularyId) {
		this.ssPatEligibilityDescFormularyId = ssPatEligibilityDescFormularyId;
	}

	public String getSsPatEligibilityDescFormularyListId() {
		return ssPatEligibilityDescFormularyListId;
	}

	public void setSsPatEligibilityDescFormularyListId(
			String ssPatEligibilityDescFormularyListId) {
		this.ssPatEligibilityDescFormularyListId = ssPatEligibilityDescFormularyListId;
	}

	public String getSsPatEligibilityDescAlternativeListId() {
		return ssPatEligibilityDescAlternativeListId;
	}

	public void setSsPatEligibilityDescAlternativeListId(
			String ssPatEligibilityDescAlternativeListId) {
		this.ssPatEligibilityDescAlternativeListId = ssPatEligibilityDescAlternativeListId;
	}

	public String getSsPatEligibilityDescCoverageId() {
		return ssPatEligibilityDescCoverageId;
	}

	public void setSsPatEligibilityDescCoverageId(
			String ssPatEligibilityDescCoverageId) {
		this.ssPatEligibilityDescCoverageId = ssPatEligibilityDescCoverageId;
	}

	public String getSsPatEligibilityDescCoverageListId() {
		return ssPatEligibilityDescCoverageListId;
	}

	public void setSsPatEligibilityDescCoverageListId(
			String ssPatEligibilityDescCoverageListId) {
		this.ssPatEligibilityDescCoverageListId = ssPatEligibilityDescCoverageListId;
	}

	public String getSsPatEligibilityDescCopayId() {
		return ssPatEligibilityDescCopayId;
	}

	public void setSsPatEligibilityDescCopayId(String ssPatEligibilityDescCopayId) {
		this.ssPatEligibilityDescCopayId = ssPatEligibilityDescCopayId;
	}

	public String getSsPatEligibilityDescPbmParticipantId() {
		return ssPatEligibilityDescPbmParticipantId;
	}

	public void setSsPatEligibilityDescPbmParticipantId(
			String ssPatEligibilityDescPbmParticipantId) {
		this.ssPatEligibilityDescPbmParticipantId = ssPatEligibilityDescPbmParticipantId;
	}

	public String getSsPatEligibilityDescInsuranceId() {
		return ssPatEligibilityDescInsuranceId;
	}

	public void setSsPatEligibilityDescInsuranceId(
			String ssPatEligibilityDescInsuranceId) {
		this.ssPatEligibilityDescInsuranceId = ssPatEligibilityDescInsuranceId;
	}

	public String getSsPatEligibilityDescInsuranceName() {
		return ssPatEligibilityDescInsuranceName;
	}

	public void setSsPatEligibilityDescInsuranceName(
			String ssPatEligibilityDescInsuranceName) {
		this.ssPatEligibilityDescInsuranceName = ssPatEligibilityDescInsuranceName;
	}

	public String getSsPatEligibilityDescRelationship() {
		return ssPatEligibilityDescRelationship;
	}

	public void setSsPatEligibilityDescRelationship(
			String ssPatEligibilityDescRelationship) {
		this.ssPatEligibilityDescRelationship = ssPatEligibilityDescRelationship;
	}

	public Boolean getSsPatEligibilityDescIsEligible() {
		return ssPatEligibilityDescIsEligible;
	}

	public void setSsPatEligibilityDescIsEligible(
			Boolean ssPatEligibilityDescIsEligible) {
		this.ssPatEligibilityDescIsEligible = ssPatEligibilityDescIsEligible;
	}

	public Boolean getSsPatEligibilityDescIsRetailEligible() {
		return ssPatEligibilityDescIsRetailEligible;
	}

	public void setSsPatEligibilityDescIsRetailEligible(
			Boolean ssPatEligibilityDescIsRetailEligible) {
		this.ssPatEligibilityDescIsRetailEligible = ssPatEligibilityDescIsRetailEligible;
	}

	public Boolean getSsPatEligibilityDescIsMailorderEligible() {
		return ssPatEligibilityDescIsMailorderEligible;
	}

	public void setSsPatEligibilityDescIsMailorderEligible(
			Boolean ssPatEligibilityDescIsMailorderEligible) {
		this.ssPatEligibilityDescIsMailorderEligible = ssPatEligibilityDescIsMailorderEligible;
	}

	public String getSsPatEligibilityDescBinId() {
		return ssPatEligibilityDescBinId;
	}

	public void setSsPatEligibilityDescBinId(String ssPatEligibilityDescBinId) {
		this.ssPatEligibilityDescBinId = ssPatEligibilityDescBinId;
	}

	public String getSsPatEligibilityDescPlanCoverageDescription() {
		return ssPatEligibilityDescPlanCoverageDescription;
	}

	public void setSsPatEligibilityDescPlanCoverageDescription(
			String ssPatEligibilityDescPlanCoverageDescription) {
		this.ssPatEligibilityDescPlanCoverageDescription = ssPatEligibilityDescPlanCoverageDescription;
	}

	public String getSsPatEligibilityDescMsgId() {
		return ssPatEligibilityDescMsgId;
	}

	public void setSsPatEligibilityDescMsgId(String ssPatEligibilityDescMsgId) {
		this.ssPatEligibilityDescMsgId = ssPatEligibilityDescMsgId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ss_pat_eligibility_desc_ss_pat_eligibility_desc_id_seq")
	@SequenceGenerator(name ="ss_pat_eligibility_desc_ss_pat_eligibility_desc_id_seq", sequenceName="ss_pat_eligibility_desc_ss_pat_eligibility_desc_id_seq", allocationSize=1)
	@Column(name="ss_pat_eligibility_desc_id")
	private Integer ssPatEligibilityDescId;

	@Column(name="ss_pat_eligibility_desc_map_eligibility_id")
	private Integer ssPatEligibilityDescMapEligibilityId;

	@Column(name="ss_pat_eligibility_desc_health_plan_id")
	private String ssPatEligibilityDescHealthPlanId;

	@Column(name="ss_pat_eligibility_desc_health_plan_name")
	private String ssPatEligibilityDescHealthPlanName;

	@Column(name="ss_pat_eligibility_desc_card_holder_id")
	private String ssPatEligibilityDescCardHolderId;

	@Column(name="ss_pat_eligibility_desc_card_holder_name")
	private String ssPatEligibilityDescCardHolderName;

	@Column(name="ss_pat_eligibility_desc_group_id")
	private String ssPatEligibilityDescGroupId;

	@Column(name="ss_pat_eligibility_desc_group_name")
	private String ssPatEligibilityDescGroupName;

	@Column(name="ss_pat_eligibility_desc_formulary_id")
	private String ssPatEligibilityDescFormularyId;

	@Column(name="ss_pat_eligibility_desc_formulary_list_id")
	private String ssPatEligibilityDescFormularyListId;

	@Column(name="ss_pat_eligibility_desc_alternative_list_id")
	private String ssPatEligibilityDescAlternativeListId;

	@Column(name="ss_pat_eligibility_desc_coverage_id")
	private String ssPatEligibilityDescCoverageId;

	@Column(name="ss_pat_eligibility_desc_coverage_list_id")
	private String ssPatEligibilityDescCoverageListId;

	@Column(name="ss_pat_eligibility_desc_copay_id")
	private String ssPatEligibilityDescCopayId;

	@Column(name="ss_pat_eligibility_desc_pbm_participant_id")
	private String ssPatEligibilityDescPbmParticipantId;

	@Column(name="ss_pat_eligibility_desc_insurance_id")
	private String ssPatEligibilityDescInsuranceId;

	@Column(name="ss_pat_eligibility_desc_insurance_name")
	private String ssPatEligibilityDescInsuranceName;

	@Column(name="ss_pat_eligibility_desc_relationship")
	private String ssPatEligibilityDescRelationship;

	@Column(name="ss_pat_eligibility_desc_is_eligible")
	private Boolean ssPatEligibilityDescIsEligible;

	@Column(name="ss_pat_eligibility_desc_is_retail_eligible")
	private Boolean ssPatEligibilityDescIsRetailEligible;

	@Column(name="ss_pat_eligibility_desc_is_mailorder_eligible")
	private Boolean ssPatEligibilityDescIsMailorderEligible;

	@Column(name="ss_pat_eligibility_desc_bin_id")
	private String ssPatEligibilityDescBinId;

	@Column(name="ss_pat_eligibility_desc_plan_coverage_description")
	private String ssPatEligibilityDescPlanCoverageDescription;

	@Column(name="ss_pat_eligibility_desc_msg_id")
	private String ssPatEligibilityDescMsgId;
}