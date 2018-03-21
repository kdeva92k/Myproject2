package com.glenwood.glaceemr.server.application.services.chart.Questionaries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientClinicalFindingsBean{
	Integer patientClinicalFindingsId;
	Integer patientClinicalFindingsPatientId;
	Integer patientClinicalFindingsEncounterId;
	String patientClinicalFindingsDate;
	String patientClinicalFindingsDescription;
	String patientClinicalFindingsCode;
	String patientClinicalFindingsCodeSystem;
	Integer patientClinicalFindingsStatus;
	String patientClinicalFindingsResultValue;
	String patientClinicalFindingsResultUnits;
	String patientClinicalFindingsResultDescription;
	String patientClinicalFindingsResultCode;
	String patientClinicalFindingsResultCodeSystem;
	Boolean patientClinicalFindingsIsactive;
	Integer patientClinicalFindingsCreatedBy;
	String patientClinicalFindingsCreatedOn;
	Integer patientClinicalFindingsModifiedBy;
	String patientClinicalFindingsModifiedOn;
	String patientClinicalFindingsAnswerListId;
	String patientClinicalFindingsAnswerListName;
	String patientClinicalFindingsAnswerListOid;
	String patientClinicalFindingsAnswerId;
	Integer patientClinicalFindingsRiskAssessmentId;
	String patientClinicalFindingsNotes;
	
	Integer riskAssessmentId;
	Integer riskAssessmentPatientId;
	Integer riskAssessmentEncounterId;
	String riskAssessmentDescription;
	String riskAssessmentCode;
	Integer riskAssessmentStatus;
	Integer riskAssessmentScreeningId;

	DateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy");

	public PatientClinicalFindingsBean() {
		// TODO Auto-generated constructor stub
	}
	
	public PatientClinicalFindingsBean(Integer patientClinicalFindingsId,
			Integer patientClinicalFindingsPatientId,
			Integer patientClinicalFindingsEncounterId,
			Date patientClinicalFindingsDate,
			String patientClinicalFindingsDescription,
			String patientClinicalFindingsCode,
			String patientClinicalFindingsCodeSystem,
			Integer patientClinicalFindingsStatus,
			String patientClinicalFindingsResultValue,
			String patientClinicalFindingsResultUnits,
			String patientClinicalFindingsResultDescription,
			String patientClinicalFindingsResultCode,
			String patientClinicalFindingsResultCodeSystem,
			Boolean patientClinicalFindingsIsactive,
			Integer patientClinicalFindingsCreatedBy,
			Date patientClinicalFindingsCreatedOn,
			String patientClinicalFindingsAnswerListId,
			String patientClinicalFindingsAnswerListName,
			String patientClinicalFindingsAnswerListOid,
			String patientClinicalFindingsAnswerId,
			Integer patientClinicalFindingsRiskAssessmentId,
			String patientClinicalFindingsNotes
			) {
		super();
		this.patientClinicalFindingsId = patientClinicalFindingsId;
		this.patientClinicalFindingsPatientId = patientClinicalFindingsPatientId;
		this.patientClinicalFindingsEncounterId = patientClinicalFindingsEncounterId;
		this.patientClinicalFindingsDate = patientClinicalFindingsDate==null?null:timeFormat.format(patientClinicalFindingsDate);
		this.patientClinicalFindingsDescription = patientClinicalFindingsDescription;
		this.patientClinicalFindingsCode = patientClinicalFindingsCode;
		this.patientClinicalFindingsCodeSystem = patientClinicalFindingsCodeSystem;
		this.patientClinicalFindingsStatus = patientClinicalFindingsStatus;
		this.patientClinicalFindingsResultValue = patientClinicalFindingsResultValue;
		this.patientClinicalFindingsResultUnits = patientClinicalFindingsResultUnits;
		this.patientClinicalFindingsResultDescription = patientClinicalFindingsResultDescription;
		this.patientClinicalFindingsResultCode = patientClinicalFindingsResultCode;
		this.patientClinicalFindingsResultCodeSystem = patientClinicalFindingsResultCodeSystem;
		this.patientClinicalFindingsIsactive = patientClinicalFindingsIsactive;
		this.patientClinicalFindingsCreatedBy = patientClinicalFindingsCreatedBy;
		this.patientClinicalFindingsCreatedOn = patientClinicalFindingsCreatedOn==null?null:timeFormat.format(patientClinicalFindingsCreatedOn);
		this.patientClinicalFindingsAnswerListId = patientClinicalFindingsAnswerListId;
		this.patientClinicalFindingsAnswerListName = patientClinicalFindingsAnswerListName;
		this.patientClinicalFindingsAnswerListOid = patientClinicalFindingsAnswerListOid;
		this.patientClinicalFindingsAnswerId = patientClinicalFindingsAnswerId;
		this.patientClinicalFindingsRiskAssessmentId = patientClinicalFindingsRiskAssessmentId;
		this.patientClinicalFindingsNotes = patientClinicalFindingsNotes;
		
		}

		
	public PatientClinicalFindingsBean(Integer patientClinicalFindingsId,
			Date patientClinicalFindingsDate,
			String patientClinicalFindingsDescription,
			String patientClinicalFindingsCode,
			Boolean patientClinicalFindingsIsactive,
			Integer patientClinicalFindingsCreatedBy,
			Date patientClinicalFindingsCreatedOn,
			String patientClinicalFindingsNotes, Integer riskAssessmentId,
			Integer riskAssessmentPatientId, Integer riskAssessmentEncounterId,
			String riskAssessmentDescription, String riskAssessmentCode,
			Integer riskAssessmentStatus, Integer riskAssessmentScreeningId) {
		super();
		this.patientClinicalFindingsId = patientClinicalFindingsId;
		this.patientClinicalFindingsDate = patientClinicalFindingsDate==null?null:timeFormat.format(patientClinicalFindingsDate);
		this.patientClinicalFindingsDescription = patientClinicalFindingsDescription;
		this.patientClinicalFindingsCode = patientClinicalFindingsCode;
		this.patientClinicalFindingsIsactive = patientClinicalFindingsIsactive;
		this.patientClinicalFindingsCreatedBy = patientClinicalFindingsCreatedBy;
		this.patientClinicalFindingsCreatedOn = patientClinicalFindingsCreatedOn==null?null:timeFormat.format(patientClinicalFindingsCreatedOn);
		this.patientClinicalFindingsNotes = patientClinicalFindingsNotes;
		this.riskAssessmentId = riskAssessmentId;
		this.riskAssessmentPatientId = riskAssessmentPatientId;
		this.riskAssessmentEncounterId = riskAssessmentEncounterId;
		this.riskAssessmentDescription = riskAssessmentDescription;
		this.riskAssessmentCode = riskAssessmentCode;
		this.riskAssessmentStatus = riskAssessmentStatus;
		this.riskAssessmentScreeningId = riskAssessmentScreeningId;
	}

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

	public String getPatientClinicalFindingsDate() {
		return patientClinicalFindingsDate;
	}

	public void setPatientClinicalFindingsDate(String patientClinicalFindingsDate) {
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

	public Integer getPatientClinicalFindingsCreatedBy() {
		return patientClinicalFindingsCreatedBy;
	}

	public void setPatientClinicalFindingsCreatedBy(
			Integer patientClinicalFindingsCreatedBy) {
		this.patientClinicalFindingsCreatedBy = patientClinicalFindingsCreatedBy;
	}

	public String getPatientClinicalFindingsCreatedOn() {
		return patientClinicalFindingsCreatedOn;
	}

	public void setPatientClinicalFindingsCreatedOn(
			String patientClinicalFindingsCreatedOn) {
		this.patientClinicalFindingsCreatedOn = patientClinicalFindingsCreatedOn;
	}

	public Integer getPatientClinicalFindingsModifiedBy() {
		return patientClinicalFindingsModifiedBy;
	}

	public void setPatientClinicalFindingsModifiedBy(
			Integer patientClinicalFindingsModifiedBy) {
		this.patientClinicalFindingsModifiedBy = patientClinicalFindingsModifiedBy;
	}

	public String getPatientClinicalFindingsModifiedOn() {
		return patientClinicalFindingsModifiedOn;
	}

	public void setPatientClinicalFindingsModifiedOn(
			String patientClinicalFindingsModifiedOn) {
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

	public Integer getRiskAssessmentStatus() {
		return riskAssessmentStatus;
	}

	public void setRiskAssessmentStatus(Integer riskAssessmentStatus) {
		this.riskAssessmentStatus = riskAssessmentStatus;
	}

	public Integer getRiskAssessmentScreeningId() {
		return riskAssessmentScreeningId;
	}

	public void setRiskAssessmentScreeningId(Integer riskAssessmentScreeningId) {
		this.riskAssessmentScreeningId = riskAssessmentScreeningId;
	}
	
	
}