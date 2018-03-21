package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "risk_assessment")
public class RiskAssessment {

	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="risk_assessment_id_seq")
	@SequenceGenerator(name ="risk_assessment_id_seq", sequenceName="risk_assessment_id_seq", allocationSize=1)
	@Id
	@Column(name="risk_assessment_id")
	private Integer riskAssessmentId;

	@Column(name="risk_assessment_patient_id")
	private Integer riskAssessmentPatientId;

	@Column(name="risk_assessment_encounter_id")
	private Integer riskAssessmentEncounterId;

	@Column(name="risk_assessment_description")
	private String riskAssessmentDescription;

	@Column(name="risk_assessment_code")
	private String riskAssessmentCode;

	@Column(name="risk_assessment_code_system")
	private String riskAssessmentCodeSystem;

	@Column(name="risk_assessment_code_system_name")
	private String riskAssessmentCodeSystemName;

	@Column(name="risk_assessment_result_description")
	private String riskAssessmentResultDescription;

	@Column(name="risk_assessment_result_code")
	private String riskAssessmentResultCode;

	@Column(name="risk_assessment_result_code_system")
	private String riskAssessmentResultCodeSystem;

	@Column(name="risk_assessment_result_code_system_name")
	private String riskAssessmentResultCodeSystemName;

	@Column(name="risk_assessment_code_name")
	private String riskAssessmentCodeName;

	@Column(name="risk_assessment_problem_code")
	private String riskAssessmentProblemCode;

	@Column(name="risk_assessment_problem_code_system")
	private String riskAssessmentProblemCodeSystem;

	@Column(name="risk_assessment_problem_code_system_description")
	private String riskAssessmentProblemCodeSystemDescription;

	@Column(name="risk_assessment_status")
	private Integer riskAssessmentStatus;

	@Column(name="risk_assessment_ordered_by")
	private Integer riskAssessmentOrderedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="risk_assessment_ordered_on")
	private Timestamp riskAssessmentOrderedOn;

	@Column(name="risk_assessment_reviewed_by")
	private Integer riskAssessmentReviewedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="risk_assessment_reviewed_on")
	private Timestamp riskAssessmentReviewedOn;

	@Column(name="risk_assessment_performed_by")
	private Integer riskAssessmentPerformedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="risk_assessment_performed_on")
	private Timestamp riskAssessmentPerformedOn;

	@Column(name="risk_assessment_notdone_type")
	private Integer riskAssessmentNotdoneType;

	@Column(name="risk_assessment_notdone_description")
	private String riskAssessmentNotdoneDescription;

	@Column(name="risk_assessment_notdone_code")
	private String riskAssessmentNotdoneCode;

	@Column(name="risk_assessment_notdone_code_system")
	private String riskAssessmentNotdoneCodeSystem;

	@Column(name="risk_assessment_notes")
	private String riskAssessmentNotes;

	@Column(name="risk_assessment_created_by")
	private Integer riskAssessmentCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="risk_assessment_created_on")
	private Timestamp riskAssessmentCreatedOn;

	@Column(name="risk_assessment_modified_by")
	private Integer riskAssessmentModifiedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="risk_assessment_modified_on")
	private Timestamp riskAssessmentModifiedOn;
	
	@Column(name="risk_assessment_result_value")
	private Integer riskAssessmentResultValue;
	
	@Column(name="risk_assessment_date")
	private Timestamp riskAssessmentDate;

	@Column(name="risk_assessment_result_value_units")
	private String riskAssessmentResultValueUnits;
	
	@Column(name="risk_assessment_screening_id")
	private Integer riskAssessmentScreeningId;
	
	@Column(name="risk_assessment_screening_name")
	private String riskAssessmentScreeningName;

	@Column(name="risk_assessment_screening_code")
	private String riskAssessmentScreeningCode;

	@Column(name="risk_assessment_screening_code_system")
	private String riskAssessmentScreeningCodeSystem;
	
	@Column(name="risk_assessment_screening_code_system_oid")
	private String riskAssessmentScreeningCodeSystemOid;
	
/*	@Column(name="risk_assessment_result_screening_name")
	private String riskAssessmentScreeningName;

	@Column(name="risk_assessment_result_screening_code")
	private String riskAssessmentScreeningCode;

	@Column(name="risk_assessment_result_screening_code_system")
	private String riskAssessmentScreeningCodeSystem;
	
	@Column(name="risk_assessment_result_screening_code_system_oid")
	private String riskAssessmentScreeningCodeSystemOid;
	
	@Column(name="risk_assessment_result_screening_id")
	private Integer riskAssessmentScreeningId;*/
	
	public Integer getRiskAssessmentId() {
		return riskAssessmentId;
	}

	public void setRiskAssessmentId(Integer riskAssessmentId) {
		this.riskAssessmentId = riskAssessmentId;
	}

	public Integer getRiskAssessmentPatientId() {
		return riskAssessmentPatientId;
	}

	public void setRiskAssessmentPatientId(Integer riskAssessmentPatientId) {
		this.riskAssessmentPatientId = riskAssessmentPatientId;
	}

	public Integer getRiskAssessmentEncounterId() {
		return riskAssessmentEncounterId;
	}

	public void setRiskAssessmentEncounterId(Integer riskAssessmentEncounterId) {
		this.riskAssessmentEncounterId = riskAssessmentEncounterId;
	}

	public String getRiskAssessmentDescription() {
		return riskAssessmentDescription;
	}

	public void setRiskAssessmentDescription(String riskAssessmentDescription) {
		this.riskAssessmentDescription = riskAssessmentDescription;
	}

	public String getRiskAssessmentCode() {
		return riskAssessmentCode;
	}

	public void setRiskAssessmentCode(String riskAssessmentCode) {
		this.riskAssessmentCode = riskAssessmentCode;
	}

	public String getRiskAssessmentCodeSystem() {
		return riskAssessmentCodeSystem;
	}

	public void setRiskAssessmentCodeSystem(String riskAssessmentCodeSystem) {
		this.riskAssessmentCodeSystem = riskAssessmentCodeSystem;
	}

	public String getRiskAssessmentCodeSystemName() {
		return riskAssessmentCodeSystemName;
	}

	public void setRiskAssessmentCodeSystemName(String riskAssessmentCodeSystemName) {
		this.riskAssessmentCodeSystemName = riskAssessmentCodeSystemName;
	}

	public String getRiskAssessmentResultDescription() {
		return riskAssessmentResultDescription;
	}

	public void setRiskAssessmentResultDescription(
			String riskAssessmentResultDescription) {
		this.riskAssessmentResultDescription = riskAssessmentResultDescription;
	}

	public String getRiskAssessmentResultCode() {
		return riskAssessmentResultCode;
	}

	public void setRiskAssessmentResultCode(String riskAssessmentResultCode) {
		this.riskAssessmentResultCode = riskAssessmentResultCode;
	}

	public String getRiskAssessmentResultCodeSystem() {
		return riskAssessmentResultCodeSystem;
	}

	public void setRiskAssessmentResultCodeSystem(
			String riskAssessmentResultCodeSystem) {
		this.riskAssessmentResultCodeSystem = riskAssessmentResultCodeSystem;
	}

	public String getRiskAssessmentResultCodeSystemName() {
		return riskAssessmentResultCodeSystemName;
	}

	public void setRiskAssessmentResultCodeSystemName(
			String riskAssessmentResultCodeSystemName) {
		this.riskAssessmentResultCodeSystemName = riskAssessmentResultCodeSystemName;
	}

	public String getRiskAssessmentCodeName() {
		return riskAssessmentCodeName;
	}

	public void setRiskAssessmentCodeName(String riskAssessmentCodeName) {
		this.riskAssessmentCodeName = riskAssessmentCodeName;
	}

	public String getRiskAssessmentProblemCode() {
		return riskAssessmentProblemCode;
	}

	public void setRiskAssessmentProblemCode(String riskAssessmentProblemCode) {
		this.riskAssessmentProblemCode = riskAssessmentProblemCode;
	}

	public String getRiskAssessmentProblemCodeSystem() {
		return riskAssessmentProblemCodeSystem;
	}

	public void setRiskAssessmentProblemCodeSystem(
			String riskAssessmentProblemCodeSystem) {
		this.riskAssessmentProblemCodeSystem = riskAssessmentProblemCodeSystem;
	}

	public String getRiskAssessmentProblemCodeSystemDescription() {
		return riskAssessmentProblemCodeSystemDescription;
	}

	public void setRiskAssessmentProblemCodeSystemDescription(
			String riskAssessmentProblemCodeSystemDescription) {
		this.riskAssessmentProblemCodeSystemDescription = riskAssessmentProblemCodeSystemDescription;
	}

	public Integer getRiskAssessmentStatus() {
		return riskAssessmentStatus;
	}

	public void setRiskAssessmentStatus(Integer riskAssessmentStatus) {
		this.riskAssessmentStatus = riskAssessmentStatus;
	}

	public Integer getRiskAssessmentOrderedBy() {
		return riskAssessmentOrderedBy;
	}

	public void setRiskAssessmentOrderedBy(Integer riskAssessmentOrderedBy) {
		this.riskAssessmentOrderedBy = riskAssessmentOrderedBy;
	}

	public Timestamp getRiskAssessmentOrderedOn() {
		return riskAssessmentOrderedOn;
	}

	public void setRiskAssessmentOrderedOn(Timestamp riskAssessmentOrderedOn) {
		this.riskAssessmentOrderedOn = riskAssessmentOrderedOn;
	}

	public Integer getRiskAssessmentReviewedBy() {
		return riskAssessmentReviewedBy;
	}

	public void setRiskAssessmentReviewedBy(Integer riskAssessmentReviewedBy) {
		this.riskAssessmentReviewedBy = riskAssessmentReviewedBy;
	}

	public Timestamp getRiskAssessmentReviewedOn() {
		return riskAssessmentReviewedOn;
	}

	public void setRiskAssessmentReviewedOn(Timestamp riskAssessmentReviewedOn) {
		this.riskAssessmentReviewedOn = riskAssessmentReviewedOn;
	}

	public Integer getRiskAssessmentPerformedBy() {
		return riskAssessmentPerformedBy;
	}

	public void setRiskAssessmentPerformedBy(Integer riskAssessmentPerformedBy) {
		this.riskAssessmentPerformedBy = riskAssessmentPerformedBy;
	}

	public Timestamp getRiskAssessmentPerformedOn() {
		return riskAssessmentPerformedOn;
	}

	public void setRiskAssessmentPerformedOn(Timestamp riskAssessmentPerformedOn) {
		this.riskAssessmentPerformedOn = riskAssessmentPerformedOn;
	}

	public Integer getRiskAssessmentNotdoneType() {
		return riskAssessmentNotdoneType;
	}

	public void setRiskAssessmentNotdoneType(Integer riskAssessmentNotdoneType) {
		this.riskAssessmentNotdoneType = riskAssessmentNotdoneType;
	}

	public String getRiskAssessmentNotdoneDescription() {
		return riskAssessmentNotdoneDescription;
	}

	public void setRiskAssessmentNotdoneDescription(
			String riskAssessmentNotdoneDescription) {
		this.riskAssessmentNotdoneDescription = riskAssessmentNotdoneDescription;
	}

	public String getRiskAssessmentNotdoneCode() {
		return riskAssessmentNotdoneCode;
	}

	public void setRiskAssessmentNotdoneCode(String riskAssessmentNotdoneCode) {
		this.riskAssessmentNotdoneCode = riskAssessmentNotdoneCode;
	}

	public String getRiskAssessmentNotdoneCodeSystem() {
		return riskAssessmentNotdoneCodeSystem;
	}

	public void setRiskAssessmentNotdoneCodeSystem(
			String riskAssessmentNotdoneCodeSystem) {
		this.riskAssessmentNotdoneCodeSystem = riskAssessmentNotdoneCodeSystem;
	}

	public String getRiskAssessmentNotes() {
		return riskAssessmentNotes;
	}

	public void setRiskAssessmentNotes(String riskAssessmentNotes) {
		this.riskAssessmentNotes = riskAssessmentNotes;
	}

	public Integer getRiskAssessmentCreatedBy() {
		return riskAssessmentCreatedBy;
	}

	public void setRiskAssessmentCreatedBy(Integer riskAssessmentCreatedBy) {
		this.riskAssessmentCreatedBy = riskAssessmentCreatedBy;
	}

	public Timestamp getRiskAssessmentCreatedOn() {
		return riskAssessmentCreatedOn;
	}

	public void setRiskAssessmentCreatedOn(Timestamp riskAssessmentCreatedOn) {
		this.riskAssessmentCreatedOn = riskAssessmentCreatedOn;
	}

	public Integer getRiskAssessmentModifiedBy() {
		return riskAssessmentModifiedBy;
	}

	public void setRiskAssessmentModifiedBy(Integer riskAssessmentModifiedBy) {
		this.riskAssessmentModifiedBy = riskAssessmentModifiedBy;
	}

	public Timestamp getRiskAssessmentModifiedOn() {
		return riskAssessmentModifiedOn;
	}

	public void setRiskAssessmentModifiedOn(Timestamp riskAssessmentModifiedOn) {
		this.riskAssessmentModifiedOn = riskAssessmentModifiedOn;
	}

	public Integer getRiskAssessmentResultValue() {
		return riskAssessmentResultValue;
	}

	public void setRiskAssessmentResultValue(Integer riskAssessmentResultValue) {
		this.riskAssessmentResultValue = riskAssessmentResultValue;
	}

	public Timestamp getRiskAssessmentDate() {
		return riskAssessmentDate;
	}

	public void setRiskAssessmentDate(Timestamp riskAssessmentDate) {
		this.riskAssessmentDate = riskAssessmentDate;
	}

	public String getRiskAssessmentResultValueUnits() {
		return riskAssessmentResultValueUnits;
	}

	public void setRiskAssessmentResultValueUnits(
			String riskAssessmentResultValueUnits) {
		this.riskAssessmentResultValueUnits = riskAssessmentResultValueUnits;
	}

	public String getRiskAssessmentScreeningName() {
		return riskAssessmentScreeningName;
	}

	public void setRiskAssessmentScreeningName(String riskAssessmentScreeningName) {
		this.riskAssessmentScreeningName = riskAssessmentScreeningName;
	}

	public String getRiskAssessmentScreeningCode() {
		return riskAssessmentScreeningCode;
	}

	public void setRiskAssessmentScreeningCode(String riskAssessmentScreeningCode) {
		this.riskAssessmentScreeningCode = riskAssessmentScreeningCode;
	}

	public String getRiskAssessmentScreeningCodeSystem() {
		return riskAssessmentScreeningCodeSystem;
	}

	public void setRiskAssessmentScreeningCodeSystem(
			String riskAssessmentScreeningCodeSystem) {
		this.riskAssessmentScreeningCodeSystem = riskAssessmentScreeningCodeSystem;
	}

	public String getRiskAssessmentScreeningCodeSystemOid() {
		return riskAssessmentScreeningCodeSystemOid;
	}

	public void setRiskAssessmentScreeningCodeSystemOid(
			String riskAssessmentScreeningCodeSystemOid) {
		this.riskAssessmentScreeningCodeSystemOid = riskAssessmentScreeningCodeSystemOid;
	}

	public Integer getRiskAssessmentScreeningId() {
		return riskAssessmentScreeningId;
	}

	public void setRiskAssessmentScreeningId(Integer riskAssessmentScreeningId) {
		this.riskAssessmentScreeningId = riskAssessmentScreeningId;
	}
	
}