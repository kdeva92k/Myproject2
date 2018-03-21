package com.glenwood.glaceemr.server.application.services.chart.Questionaries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RiskAssessmentBean{
	Integer riskAssessmentId;
	Integer riskAssessmentPatientId;
	Integer riskAssessmentEncounterId;
	String riskAssessmentDescription;
	String riskAssessmentCode;
	String riskAssessmentCodeSystem;
	String riskAssessmentCodeSystemName;
	String riskAssessmentResultDescription;
	String riskAssessmentResultCode;
	String riskAssessmentResultCodeSystem;
	String riskAssessmentResultCodeSystemName;
	String riskAssessmentCodeName;
	Integer riskAssessmentStatus;
	Integer riskAssessmentOrderedBy;
	String riskAssessmentOrderedOn;
	Integer riskAssessmentReviewedBy;
	String riskAssessmentReviewedOn;
	Integer riskAssessmentPerformedBy;
	String riskAssessmentPerformedOn;
	String riskAssessmentNotes;
	Integer riskAssessmentCreatedBy;
	String riskAssessmentCreatedOn;
	Integer riskAssessmentModifiedBy;
	String riskAssessmentModifiedOn;
	Integer riskAssessmentResultValue;
	String riskAssessmentDate;
	String riskAssessmentResultValueUnits;
	String riskAssessmentScreeningName;
	String riskAssessmentScreeningCode;
	String riskAssessmentScreeningCodeSystem;
	String riskAssessmentScreeningCodeSystemOid;
	Integer riskAssessmentScreeningId;
	DateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy");

	public RiskAssessmentBean(){
		
	}

	public RiskAssessmentBean(Integer riskAssessmentId,
			Integer riskAssessmentPatientId, Integer riskAssessmentEncounterId,
			String riskAssessmentDescription,String riskAssessmentCode,
			String riskAssessmentCodeSystem,String riskAssessmentCodeSystemName,
			Integer riskAssessmentStatus,String riskAssessmentNotes,
			Integer riskAssessmentCreatedBy,Date riskAssessmentCreatedOn,
			Integer riskAssessmentResultValue,String riskAssessmentResultDescription,String riskAssessmentResultCode,
			Integer riskAssessmentOrderedBy,Date  riskAssessmentOrderedOn,
			Date riskAssessmentDate,String riskAssessmentScreeningName,
			String riskAssessmentScreeningCode,	String riskAssessmentScreeningCodeSystem,
			String riskAssessmentScreeningCodeSystemOid,Integer riskAssessmentScreeningId) {
		super();
		this.riskAssessmentId = riskAssessmentId;
		this.riskAssessmentPatientId = riskAssessmentPatientId;
		this.riskAssessmentEncounterId = riskAssessmentEncounterId;
		this.riskAssessmentDescription = riskAssessmentDescription;
		this.riskAssessmentCode = riskAssessmentCode;
		this.riskAssessmentCodeSystem = riskAssessmentCodeSystem;
		this.riskAssessmentCodeSystemName =  riskAssessmentCodeSystemName;
		this.riskAssessmentStatus = riskAssessmentStatus;
		this.riskAssessmentNotes = riskAssessmentNotes;
		this.riskAssessmentCreatedBy = riskAssessmentCreatedBy;
		this.riskAssessmentCreatedOn = riskAssessmentCreatedOn==null?null:timeFormat.format(riskAssessmentCreatedOn);
		this.riskAssessmentResultValue = riskAssessmentResultValue;
		this.riskAssessmentResultDescription = riskAssessmentResultDescription;
		this.riskAssessmentResultCode = riskAssessmentResultCode;
		this.riskAssessmentOrderedBy = riskAssessmentOrderedBy;
		this.riskAssessmentOrderedOn = riskAssessmentOrderedOn==null?null:timeFormat.format(riskAssessmentOrderedOn);
		this.riskAssessmentDate = riskAssessmentDate==null?null:timeFormat.format(riskAssessmentDate);
		this.riskAssessmentScreeningName = riskAssessmentScreeningName;
		this.riskAssessmentScreeningCode = riskAssessmentScreeningCode;
		this.riskAssessmentScreeningCodeSystem = riskAssessmentScreeningCodeSystem;
		this.riskAssessmentScreeningCodeSystemOid = riskAssessmentScreeningCodeSystemOid;
		this.riskAssessmentScreeningId = riskAssessmentScreeningId;
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
	public String getRiskAssessmentOrderedOn() {
		return riskAssessmentOrderedOn;
	}
	public void setRiskAssessmentOrderedOn(String riskAssessmentOrderedOn) {
		this.riskAssessmentOrderedOn = riskAssessmentOrderedOn;
	}
	public Integer getRiskAssessmentReviewedBy() {
		return riskAssessmentReviewedBy;
	}
	public void setRiskAssessmentReviewedBy(Integer riskAssessmentReviewedBy) {
		this.riskAssessmentReviewedBy = riskAssessmentReviewedBy;
	}
	public String getRiskAssessmentReviewedOn() {
		return riskAssessmentReviewedOn;
	}
	public void setRiskAssessmentReviewedOn(String riskAssessmentReviewedOn) {
		this.riskAssessmentReviewedOn = riskAssessmentReviewedOn;
	}
	public Integer getRiskAssessmentPerformedBy() {
		return riskAssessmentPerformedBy;
	}
	public void setRiskAssessmentPerformedBy(Integer riskAssessmentPerformedBy) {
		this.riskAssessmentPerformedBy = riskAssessmentPerformedBy;
	}
	public String getRiskAssessmentPerformedOn() {
		return riskAssessmentPerformedOn;
	}
	public void setRiskAssessmentPerformedOn(String riskAssessmentPerformedOn) {
		this.riskAssessmentPerformedOn = riskAssessmentPerformedOn;
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
	public String getRiskAssessmentCreatedOn() {
		return riskAssessmentCreatedOn;
	}
	public void setRiskAssessmentCreatedOn(String riskAssessmentCreatedOn) {
		this.riskAssessmentCreatedOn = riskAssessmentCreatedOn;
	}
	public Integer getRiskAssessmentModifiedBy() {
		return riskAssessmentModifiedBy;
	}
	public void setRiskAssessmentModifiedBy(Integer riskAssessmentModifiedBy) {
		this.riskAssessmentModifiedBy = riskAssessmentModifiedBy;
	}
	public String getRiskAssessmentModifiedOn() {
		return riskAssessmentModifiedOn;
	}
	public void setRiskAssessmentModifiedOn(String riskAssessmentModifiedOn) {
		this.riskAssessmentModifiedOn = riskAssessmentModifiedOn;
	}
	public Integer getRiskAssessmentResultValue() {
		return riskAssessmentResultValue;
	}
	public void setRiskAssessmentResultValue(Integer riskAssessmentResultValue) {
		this.riskAssessmentResultValue = riskAssessmentResultValue;
	}
	public String getRiskAssessmentDate() {
		return riskAssessmentDate;
	}
	public void setRiskAssessmentDate(String riskAssessmentDate) {
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