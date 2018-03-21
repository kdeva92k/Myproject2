package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "patient_clinical_findings")
public class PatientClinicalFindings {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_clinical_findings_patient_clinical_findings_id_seq")
	@SequenceGenerator(name ="patient_clinical_findings_patient_clinical_findings_id_seq", sequenceName="patient_clinical_findings_patient_clinical_findings_id_seq", allocationSize=1)
	@Column(name="patient_clinical_findings_id")
	private Integer patientClinicalFindingsId;

	@Column(name="patient_clinical_findings_patient_id")
	private Integer patientClinicalFindingsPatientId;

	@Column(name="patient_clinical_findings_encounter_id")
	private Integer patientClinicalFindingsEncounterId;

	@Column(name="patient_clinical_findings_episode_id")
	private Integer patientClinicalFindingsEpisodeId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_clinical_findings_date")
	private Timestamp patientClinicalFindingsDate;

	@Column(name="patient_clinical_findings_description")
	private String patientClinicalFindingsDescription;

	@Column(name="patient_clinical_findings_code")
	private String patientClinicalFindingsCode;

	@Column(name="patient_clinical_findings_code_system")
	private String patientClinicalFindingsCodeSystem;

	@Column(name="patient_clinical_findings_status")
	private Integer patientClinicalFindingsStatus;

	@Column(name="patient_clinical_findings_not_done_reason")
	private String patientClinicalFindingsNotDoneReason;

	@Column(name="patient_clinical_findings_not_done_reason_code")
	private String patientClinicalFindingsNotDoneReasonCode;

	@Column(name="patient_clinical_findings_not_done_reason_code_system")
	private String patientClinicalFindingsNotDoneReasonCodeSystem;

	@Column(name="patient_clinical_findings_result_value")
	private String patientClinicalFindingsResultValue;

	@Column(name="patient_clinical_findings_result_units")
	private String patientClinicalFindingsResultUnits;

	@Column(name="patient_clinical_findings_result_description")
	private String patientClinicalFindingsResultDescription;

	@Column(name="patient_clinical_findings_result_code")
	private String patientClinicalFindingsResultCode;

	@Column(name="patient_clinical_findings_result_code_system")
	private String patientClinicalFindingsResultCodeSystem;

	@Column(name="patient_clinical_findings_isactive")
	private Boolean patientClinicalFindingsIsactive;

	@Column(name="patient_clinical_findings_created_source")
	private String patientClinicalFindingsCreatedSource;

	@Column(name="patient_clinical_findings_created_by")
	private Integer patientClinicalFindingsCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_clinical_findings_created_on")
	private Timestamp patientClinicalFindingsCreatedOn;

	@Column(name="patient_clinical_findings_modified_by")
	private Integer patientClinicalFindingsModifiedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_clinical_findings_modified_on")
	private Timestamp patientClinicalFindingsModifiedOn;
	
	@Column(name="patient_clinical_findings_answerlistid")
	private String patientClinicalFindingsAnswerListId;

	@Column(name="patient_clinical_findings_answerlist_name")
	private String patientClinicalFindingsAnswerListName;

	@Column(name="patient_clinical_findings_answerlist_oid")
	private String patientClinicalFindingsAnswerListOid;

	@Column(name="patient_clinical_findings_answerid")
	private String patientClinicalFindingsAnswerId;
	
	@Column(name="patient_clinical_findings_risk_assessment_id")
	private Integer patientClinicalFindingsRiskAssessmentId;

	@Column(name="patient_clinical_findings_notes")
	private String patientClinicalFindingsNotes;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "patient_clinical_findings_risk_assessment_id", referencedColumnName = "risk_assessment_id", insertable = false, updatable = false)
	private RiskAssessment riskAssessment;
	
	public Integer getPatientClinicalFindingsId() {
		return patientClinicalFindingsId;
	}

	public void setPatientClinicalFindingsId(Integer patientClinicalFindingsId) {
		this.patientClinicalFindingsId = patientClinicalFindingsId;
	}

	public Integer getPatientClinicalFindingsPatientId() {
		return patientClinicalFindingsPatientId;
	}

	public void setPatientClinicalFindingsPatientId(
			Integer patientClinicalFindingsPatientId) {
		this.patientClinicalFindingsPatientId = patientClinicalFindingsPatientId;
	}

	public Integer getPatientClinicalFindingsEncounterId() {
		return patientClinicalFindingsEncounterId;
	}

	public void setPatientClinicalFindingsEncounterId(
			Integer patientClinicalFindingsEncounterId) {
		this.patientClinicalFindingsEncounterId = patientClinicalFindingsEncounterId;
	}

	public Integer getPatientClinicalFindingsEpisodeId() {
		return patientClinicalFindingsEpisodeId;
	}

	public void setPatientClinicalFindingsEpisodeId(
			Integer patientClinicalFindingsEpisodeId) {
		this.patientClinicalFindingsEpisodeId = patientClinicalFindingsEpisodeId;
	}

	public Timestamp getPatientClinicalFindingsDate() {
		return patientClinicalFindingsDate;
	}

	public void setPatientClinicalFindingsDate(Timestamp patientClinicalFindingsDate) {
		this.patientClinicalFindingsDate = patientClinicalFindingsDate;
	}

	public String getPatientClinicalFindingsDescription() {
		return patientClinicalFindingsDescription;
	}

	public void setPatientClinicalFindingsDescription(
			String patientClinicalFindingsDescription) {
		this.patientClinicalFindingsDescription = patientClinicalFindingsDescription;
	}

	public String getPatientClinicalFindingsCode() {
		return patientClinicalFindingsCode;
	}

	public void setPatientClinicalFindingsCode(String patientClinicalFindingsCode) {
		this.patientClinicalFindingsCode = patientClinicalFindingsCode;
	}

	public String getPatientClinicalFindingsCodeSystem() {
		return patientClinicalFindingsCodeSystem;
	}

	public void setPatientClinicalFindingsCodeSystem(
			String patientClinicalFindingsCodeSystem) {
		this.patientClinicalFindingsCodeSystem = patientClinicalFindingsCodeSystem;
	}

	public Integer getPatientClinicalFindingsStatus() {
		return patientClinicalFindingsStatus;
	}

	public void setPatientClinicalFindingsStatus(
			Integer patientClinicalFindingsStatus) {
		this.patientClinicalFindingsStatus = patientClinicalFindingsStatus;
	}

	public String getPatientClinicalFindingsNotDoneReason() {
		return patientClinicalFindingsNotDoneReason;
	}

	public void setPatientClinicalFindingsNotDoneReason(
			String patientClinicalFindingsNotDoneReason) {
		this.patientClinicalFindingsNotDoneReason = patientClinicalFindingsNotDoneReason;
	}

	public String getPatientClinicalFindingsNotDoneReasonCode() {
		return patientClinicalFindingsNotDoneReasonCode;
	}

	public void setPatientClinicalFindingsNotDoneReasonCode(
			String patientClinicalFindingsNotDoneReasonCode) {
		this.patientClinicalFindingsNotDoneReasonCode = patientClinicalFindingsNotDoneReasonCode;
	}

	public String getPatientClinicalFindingsNotDoneReasonCodeSystem() {
		return patientClinicalFindingsNotDoneReasonCodeSystem;
	}

	public void setPatientClinicalFindingsNotDoneReasonCodeSystem(
			String patientClinicalFindingsNotDoneReasonCodeSystem) {
		this.patientClinicalFindingsNotDoneReasonCodeSystem = patientClinicalFindingsNotDoneReasonCodeSystem;
	}

	public String getPatientClinicalFindingsResultValue() {
		return patientClinicalFindingsResultValue;
	}

	public void setPatientClinicalFindingsResultValue(
			String patientClinicalFindingsResultValue) {
		this.patientClinicalFindingsResultValue = patientClinicalFindingsResultValue;
	}

	public String getPatientClinicalFindingsResultUnits() {
		return patientClinicalFindingsResultUnits;
	}

	public void setPatientClinicalFindingsResultUnits(
			String patientClinicalFindingsResultUnits) {
		this.patientClinicalFindingsResultUnits = patientClinicalFindingsResultUnits;
	}

	public String getPatientClinicalFindingsResultDescription() {
		return patientClinicalFindingsResultDescription;
	}

	public void setPatientClinicalFindingsResultDescription(
			String patientClinicalFindingsResultDescription) {
		this.patientClinicalFindingsResultDescription = patientClinicalFindingsResultDescription;
	}

	public String getPatientClinicalFindingsResultCode() {
		return patientClinicalFindingsResultCode;
	}

	public void setPatientClinicalFindingsResultCode(
			String patientClinicalFindingsResultCode) {
		this.patientClinicalFindingsResultCode = patientClinicalFindingsResultCode;
	}

	public String getPatientClinicalFindingsResultCodeSystem() {
		return patientClinicalFindingsResultCodeSystem;
	}

	public void setPatientClinicalFindingsResultCodeSystem(
			String patientClinicalFindingsResultCodeSystem) {
		this.patientClinicalFindingsResultCodeSystem = patientClinicalFindingsResultCodeSystem;
	}

	public Boolean getPatientClinicalFindingsIsactive() {
		return patientClinicalFindingsIsactive;
	}

	public void setPatientClinicalFindingsIsactive(
			Boolean patientClinicalFindingsIsactive) {
		this.patientClinicalFindingsIsactive = patientClinicalFindingsIsactive;
	}

	public String getPatientClinicalFindingsCreatedSource() {
		return patientClinicalFindingsCreatedSource;
	}

	public void setPatientClinicalFindingsCreatedSource(
			String patientClinicalFindingsCreatedSource) {
		this.patientClinicalFindingsCreatedSource = patientClinicalFindingsCreatedSource;
	}

	public Integer getPatientClinicalFindingsCreatedBy() {
		return patientClinicalFindingsCreatedBy;
	}

	public void setPatientClinicalFindingsCreatedBy(
			Integer patientClinicalFindingsCreatedBy) {
		this.patientClinicalFindingsCreatedBy = patientClinicalFindingsCreatedBy;
	}

	public Timestamp getPatientClinicalFindingsCreatedOn() {
		return patientClinicalFindingsCreatedOn;
	}

	public void setPatientClinicalFindingsCreatedOn(
			Timestamp patientClinicalFindingsCreatedOn) {
		this.patientClinicalFindingsCreatedOn = patientClinicalFindingsCreatedOn;
	}

	public Integer getPatientClinicalFindingsModifiedBy() {
		return patientClinicalFindingsModifiedBy;
	}

	public void setPatientClinicalFindingsModifiedBy(
			Integer patientClinicalFindingsModifiedBy) {
		this.patientClinicalFindingsModifiedBy = patientClinicalFindingsModifiedBy;
	}

	public Timestamp getPatientClinicalFindingsModifiedOn() {
		return patientClinicalFindingsModifiedOn;
	}

	public void setPatientClinicalFindingsModifiedOn(
			Timestamp patientClinicalFindingsModifiedOn) {
		this.patientClinicalFindingsModifiedOn = patientClinicalFindingsModifiedOn;
	}

	public String getPatientClinicalFindingsAnswerListId() {
		return patientClinicalFindingsAnswerListId;
	}

	public void setPatientClinicalFindingsAnswerListId(
			String patientClinicalFindingsAnswerListId) {
		this.patientClinicalFindingsAnswerListId = patientClinicalFindingsAnswerListId;
	}

	public String getPatientClinicalFindingsAnswerListName() {
		return patientClinicalFindingsAnswerListName;
	}

	public void setPatientClinicalFindingsAnswerListName(
			String patientClinicalFindingsAnswerListName) {
		this.patientClinicalFindingsAnswerListName = patientClinicalFindingsAnswerListName;
	}

	public String getPatientClinicalFindingsAnswerListOid() {
		return patientClinicalFindingsAnswerListOid;
	}

	public void setPatientClinicalFindingsAnswerListOid(
			String patientClinicalFindingsAnswerListOid) {
		this.patientClinicalFindingsAnswerListOid = patientClinicalFindingsAnswerListOid;
	}

	public String getPatientClinicalFindingsAnswerId() {
		return patientClinicalFindingsAnswerId;
	}

	public void setPatientClinicalFindingsAnswerId(
			String patientClinicalFindingsAnswerId) {
		this.patientClinicalFindingsAnswerId = patientClinicalFindingsAnswerId;
	}

	public Integer getpatientClinicalFindingsRiskAssessmentId() {
		return patientClinicalFindingsRiskAssessmentId;
	}

	public void setpatientClinicalFindingsRiskAssessmentId(
			Integer patientClinicalFindingsRiskAssessmentId) {
		this.patientClinicalFindingsRiskAssessmentId = patientClinicalFindingsRiskAssessmentId;
	}

	public String getPatientClinicalFindingsNotes() {
		return patientClinicalFindingsNotes;
	}

	public void setPatientClinicalFindingsNotes(String patientClinicalFindingsNotes) {
		this.patientClinicalFindingsNotes = patientClinicalFindingsNotes;
	}
	
	
}