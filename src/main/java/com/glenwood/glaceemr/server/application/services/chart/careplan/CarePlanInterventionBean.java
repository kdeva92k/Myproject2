package com.glenwood.glaceemr.server.application.services.chart.careplan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarePlanInterventionBean {

	Integer interventionId;
	Integer interventionPatientId;
	Integer interventionEncounterId;
	Integer interventionConcernId;
	Integer interventionGoalId;
	Integer interventionCategoryId;
	String interventionDescription;
	String interventionCode;
	String interventionCodeName;
	String interventionCodeSystem;
	String interventionCodeSystemName;
	String interventionProblemCode;
	String interventionProblemCodeSystem;
	String interventionProblemCodeSystemDescription;
	Integer interventionStatus;
	Integer interventionOrderedBy;
	String interventionOrderedOn;
	Integer interventionPerformedBy;
	String interventionPerformedOn;
	Integer interventionNotDoneType;
	String interventionNotDoneDescription;
	String interventionNotDoneCode;
	String interventionNotDoneCodeSystem;
	String interventionNotes;
	DateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	public CarePlanInterventionBean(){
		
	}

	public CarePlanInterventionBean(Integer interventionId, Integer interventionPatientId,
			Integer interventionEncounterId, Integer interventionConcernId, Integer interventionGoalId,
			Integer interventionCategoryId, String interventionDescription, String interventionCode,
			String interventionCodeName, String interventionProblemCode, String interventionProblemCodeSystem,
			String interventionProblemCodeSystemDescription, Integer interventionStatus, Date interventionOrderedOn,
			Date interventionPerformedOn, Integer interventionNotDoneType, String interventionNotDoneDescription,
			String interventionNotDoneCode, String interventionNotDoneCodeSystem, String interventionNotes) {
		super();
		this.interventionId = interventionId;
		this.interventionPatientId = interventionPatientId;
		this.interventionEncounterId = interventionEncounterId;
		this.interventionConcernId = interventionConcernId;
		this.interventionGoalId = interventionGoalId;
		this.interventionCategoryId = interventionCategoryId;
		this.interventionDescription = interventionDescription;
		this.interventionCode = interventionCode;
		this.interventionCodeName = interventionCodeName;
		this.interventionProblemCode = interventionProblemCode;
		this.interventionProblemCodeSystem = interventionProblemCodeSystem;
		this.interventionProblemCodeSystemDescription = interventionProblemCodeSystemDescription;
		this.interventionStatus = interventionStatus;
		if(interventionOrderedOn!=null)
		this.interventionOrderedOn = interventionOrderedOn==null?null:timeFormat.format(interventionOrderedOn);
		else
			this.interventionOrderedOn ="";
		if(this.interventionPerformedOn !=null)
		this.interventionPerformedOn = interventionPerformedOn==null?null:timeFormat.format(interventionPerformedOn);
		else
			this.interventionPerformedOn ="";
		this.interventionNotDoneType = interventionNotDoneType;
		this.interventionNotDoneDescription = interventionNotDoneDescription;
		this.interventionNotDoneCode = interventionNotDoneCode;
		this.interventionNotDoneCodeSystem = interventionNotDoneCodeSystem;
		this.interventionNotes = interventionNotes;
	}

	public CarePlanInterventionBean(Integer interventionId,
			Integer interventionPatientId, Integer interventionEncounterId,
			Integer interventionConcernId, Integer interventionGoalId,
			Integer interventionCategoryId, String interventionDescription,
			String interventionCode, String interventionCodeName,
			String interventionProblemCode,
			String interventionProblemCodeSystem,
			String interventionProblemCodeSystemDescription,
			Integer interventionStatus,Integer interventionOrderedBy,
			Date interventionOrderedOn,Integer interventionPerformedBy,
			Date interventionPerformedOn, Integer interventionNotDoneType,
			String interventionNotDoneDescription,
			String interventionNotDoneCode, String interventionNotDoneCodeSystem,String interventionNotes) {
		super();
		this.interventionId = interventionId;
		this.interventionPatientId = interventionPatientId;
		this.interventionEncounterId = interventionEncounterId;
		this.interventionConcernId = interventionConcernId;
		this.interventionGoalId = interventionGoalId;
		this.interventionCategoryId = interventionCategoryId;
		this.interventionDescription = interventionDescription;
		this.interventionCode = interventionCode;
		this.interventionCodeName = interventionCodeName;
		this.interventionProblemCode = interventionProblemCode;
		this.interventionProblemCodeSystem = interventionProblemCodeSystem;
		this.interventionProblemCodeSystemDescription = interventionProblemCodeSystemDescription;
		this.interventionStatus = interventionStatus;
		this.interventionOrderedBy = interventionOrderedBy;
		this.interventionOrderedOn = interventionOrderedOn==null?null:timeFormat.format(interventionOrderedOn);
		this.interventionPerformedBy = interventionPerformedBy;
		this.interventionPerformedOn = interventionPerformedOn==null?null:timeFormat.format(interventionPerformedOn);
		this.interventionNotDoneType = interventionNotDoneType;
		this.interventionNotDoneDescription = interventionNotDoneDescription;
		this.interventionNotDoneCode = interventionNotDoneCode;
		this.interventionNotDoneCodeSystem = interventionNotDoneCodeSystem;
		this.interventionNotes=interventionNotes;
	}
	
	public Integer getInterventionId() {
		return interventionId;
	}
	public void setInterventionId(Integer interventionId) {
		this.interventionId = interventionId;
	}
	public Integer getInterventionPatientId() {
		return interventionPatientId;
	}
	public void setInterventionPatientId(Integer interventionPatientId) {
		this.interventionPatientId = interventionPatientId;
	}
	public Integer getInterventionEncounterId() {
		return interventionEncounterId;
	}
	public void setInterventionEncounterId(Integer interventionEncounterId) {
		this.interventionEncounterId = interventionEncounterId;
	}
	public Integer getInterventionConcernId() {
		return interventionConcernId;
	}
	public void setInterventionConcernId(Integer interventionConcernId) {
		this.interventionConcernId = interventionConcernId;
	}
	public Integer getInterventionGoalId() {
		return interventionGoalId;
	}
	public void setInterventionGoalId(Integer interventionGoalId) {
		this.interventionGoalId = interventionGoalId;
	}
	public Integer getInterventionCategoryId() {
		return interventionCategoryId;
	}
	public void setInterventionCategoryId(Integer interventionCategoryId) {
		this.interventionCategoryId = interventionCategoryId;
	}
	public String getInterventionDescription() {
		return interventionDescription;
	}
	public void setInterventionDescription(String interventionDescription) {
		this.interventionDescription = interventionDescription;
	}
	public String getInterventionCode() {
		return interventionCode;
	}
	public void setInterventionCode(String interventionCode) {
		this.interventionCode = interventionCode;
	}
	public String getInterventionCodeName() {
		return interventionCodeName;
	}
	public void setInterventionCodeName(String interventionCodeName) {
		this.interventionCodeName = interventionCodeName;
	}
	public String getInterventionCodeSystem() {
		return interventionCodeSystem;
	}
	public void setInterventionCodeSystem(String interventionCodeSystem) {
		this.interventionCodeSystem = interventionCodeSystem;
	}
	public String getInterventionCodeSystemName() {
		return interventionCodeSystemName;
	}
	public void setInterventionCodeSystemName(String interventionCodeSystemName) {
		this.interventionCodeSystemName = interventionCodeSystemName;
	}
	public String getInterventionProblemCode() {
		return interventionProblemCode;
	}
	public void setInterventionProblemCode(String interventionProblemCode) {
		this.interventionProblemCode = interventionProblemCode;
	}
	public String getInterventionProblemCodeSystem() {
		return interventionProblemCodeSystem;
	}
	public void setInterventionProblemCodeSystem(
			String interventionProblemCodeSystem) {
		this.interventionProblemCodeSystem = interventionProblemCodeSystem;
	}
	public String getInterventionProblemCodeSystemDescription() {
		return interventionProblemCodeSystemDescription;
	}
	public void setInterventionProblemCodeSystemDescription(
			String interventionProblemCodeSystemDescription) {
		this.interventionProblemCodeSystemDescription = interventionProblemCodeSystemDescription;
	}
	public Integer getInterventionStatus() {
		return interventionStatus;
	}
	public void setInterventionStatus(Integer interventionStatus) {
		this.interventionStatus = interventionStatus;
	}
	public String getInterventionOrderedOn() {
		return interventionOrderedOn;
	}
	public void setInterventionOrderedOn(String interventionOrderedOn) {
		this.interventionOrderedOn = interventionOrderedOn;
	}
	public String getInterventionPerformedOn() {
		return interventionPerformedOn;
	}
	public void setInterventionPerformedOn(String interventionPerformedOn) {
		this.interventionPerformedOn = interventionPerformedOn;
	}
	public Integer getInterventionOrderedBy() {
		return interventionOrderedBy;
	}

	public void setInterventionOrderedBy(Integer interventionOrderedBy) {
		this.interventionOrderedBy = interventionOrderedBy;
	}
	public Integer getInterventionPerformedBy() {
		return interventionPerformedBy;
	}
	public void setInterventionPerformedBy(Integer interventionPerformedBy) {
		this.interventionPerformedBy = interventionPerformedBy;
	}
	public Integer getInterventionNotDoneType() {
		return interventionNotDoneType;
	}
	public void setInterventionNotDoneType(Integer interventionNotDoneType) {
		this.interventionNotDoneType = interventionNotDoneType;
	}
	public String getInterventionNotDoneDescription() {
		return interventionNotDoneDescription;
	}
	public void setInterventionNotDoneDescription(
			String interventionNotDoneDescription) {
		this.interventionNotDoneDescription = interventionNotDoneDescription;
	}
	public String getInterventionNotDoneCode() {
		return interventionNotDoneCode;
	}
	public void setInterventionNotDoneCode(String interventionNotDoneCode) {
		this.interventionNotDoneCode = interventionNotDoneCode;
	}
	public String getInterventionNotDoneCodeSystem() {
		return interventionNotDoneCodeSystem;
	}
	public void setInterventionNotDoneCodeSystem(
			String interventionNotDoneCodeSystem) {
		this.interventionNotDoneCodeSystem = interventionNotDoneCodeSystem;
	}
	public String getInterventionNotes() {
		return interventionNotes;
	}
	public void setInterventionNotes(String interventionNotes) {
		this.interventionNotes = interventionNotes;
	}

	
}