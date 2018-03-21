package com.glenwood.glaceemr.server.application.services.chart.careplan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarePlanRecommendedInterventionBean {

	Integer recommInterventionId;
	Integer recommInterventionPatientId;
	Integer recommInterventionEncounterId;
	Integer recommInterventionEpisodeId;
	Integer recommInterventionConcernId;
	Integer recommInterventionGoalId;
	Integer recommInterventionCategoryId;
	Integer recommInterventionCategoryName;
	String recommInterventionDescription;
	Integer recommInterventionRecommendedBy;
	String recommInterventionRecommendedOn;
	String recommInterventionCode;
	String recommInterventionCodeName;
	String recommInterventionCodeSystem; 
	String recommInterventionCodeSystemname;           
	String recommInterventionProblemCodeSystem;
	String recommInterventionProblemCodeSystemDescription;
	String recommInterventionProblemCode;
	Integer recommInterventionStatus;
	Integer recommInterventionOrderedBy;
	String recommInterventionOrderedOn;
	Integer recommInterventionPerformedBy;
	String recommInterventionPerformedOn;
	Integer recommInterventionNotDoneType;
	String recommInterventionNotDoneDescription;
	String recommInterventionNotDoneCode;
	String recommInterventionNotDoneCodeSystem;
	String recommInterventionNotes;
	Integer recommInterventionCreatedBy;
	String recommInterventionCreatedOn;
	Integer recommInterventionModifiedBy;
	String recommInterventionModifiedOn;
	Integer recommResponsibleParty;
	String recommCreatedName;
	String recommModifiedName;
	String concernDesc;
	String goalDesc;
	
	DateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	public CarePlanRecommendedInterventionBean(){
		
	}
	
	public CarePlanRecommendedInterventionBean(Integer recommInterventionId,Integer recommInterventionPatientId,
			Integer recommInterventionEncounterId,Integer recommInterventionEpisodeId,
			Integer recommInterventionConcernId,Integer recommInterventionGoalId,
			Integer recommInterventionCategoryId,String recommInterventionDescription,
			Integer recommInterventionRecommendedBy,Date recommInterventionRecommendedOn,
			String recommInterventionCode,String recommInterventionCodeSystem, String recommInterventionCodeSystemname, 
			Integer recommInterventionPerformedBy,Date recommInterventionPerformedOn,String recommInterventionNotes,
			Integer recommInterventionCreatedBy,Date recommInterventionCreatedOn,Integer recommInterventionModifiedBy,Date recommInterventionModifiedOn,
			Integer recommInterventionStatus,Date recommInterventionOrderedOn,
			String recommCreatedName,String recommModifiedName,String concernDesc,String goalDesc) {
				
				super();
				DateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy");
				this.recommInterventionId=recommInterventionId;
				this.recommInterventionPatientId=recommInterventionPatientId;
				this.recommInterventionEncounterId=recommInterventionEncounterId;
				this.recommInterventionEpisodeId=recommInterventionEpisodeId;
				this.recommInterventionConcernId=recommInterventionConcernId;
				this.recommInterventionGoalId=recommInterventionConcernId;
				this.recommInterventionCategoryId=recommInterventionCategoryId;
				this.recommInterventionDescription=recommInterventionDescription;
				this.recommInterventionRecommendedBy=recommInterventionRecommendedBy;
				if(recommInterventionRecommendedOn==null)
					this.recommInterventionRecommendedOn="";
				else
					this.recommInterventionRecommendedOn=timeFormat.format(recommInterventionRecommendedOn);
				this.recommInterventionCode=recommInterventionCode;
				this.recommInterventionCodeSystem=recommInterventionCodeSystem;
				this.recommInterventionCodeSystemname= recommInterventionCodeSystemname;        
				this.recommInterventionPerformedBy=recommInterventionPerformedBy;
				if(recommInterventionPerformedOn==null)
					this.recommInterventionPerformedOn="";
				else
					this.recommInterventionPerformedOn=timeFormat.format(recommInterventionPerformedOn);
				this.recommInterventionNotes=recommInterventionNotes;
				this.recommInterventionCreatedBy=recommInterventionCreatedBy;
				if(recommInterventionCreatedOn==null)
					this.recommInterventionCreatedOn="";
				else
					this.recommInterventionCreatedOn=timeFormat.format(recommInterventionCreatedOn);
				this.recommInterventionModifiedBy=recommInterventionModifiedBy;
				if(recommInterventionModifiedOn==null)
					this.recommInterventionModifiedOn="";
				else
					this.recommInterventionModifiedOn=timeFormat.format(recommInterventionCreatedOn);
				this.recommCreatedName=recommCreatedName;
				this.recommModifiedName=recommModifiedName;
				this.recommInterventionStatus=recommInterventionStatus;
				if(recommInterventionOrderedOn==null)
					this.recommInterventionOrderedOn="";
				else
					this.recommInterventionOrderedOn = timeFormat.format(recommInterventionOrderedOn);
				this.concernDesc = concernDesc;
				this.goalDesc = goalDesc;
	}

	public Integer getRecommInterventionId() {
		return recommInterventionId;
	}

	public void setRecommInterventionId(Integer recommInterventionId) {
		this.recommInterventionId = recommInterventionId;
	}

	public Integer getRecommInterventionPatientId() {
		return recommInterventionPatientId;
	}

	public void setRecommInterventionPatientId(Integer recommInterventionPatientId) {
		this.recommInterventionPatientId = recommInterventionPatientId;
	}

	public Integer getRecommInterventionEncounterId() {
		return recommInterventionEncounterId;
	}

	public void setRecommInterventionEncounterId(
			Integer recommInterventionEncounterId) {
		this.recommInterventionEncounterId = recommInterventionEncounterId;
	}

	public Integer getRecommInterventionEpisodeId() {
		return recommInterventionEpisodeId;
	}

	public void setRecommInterventionEpisodeId(Integer recommInterventionEpisodeId) {
		this.recommInterventionEpisodeId = recommInterventionEpisodeId;
	}

	public Integer getRecommInterventionConcernId() {
		return recommInterventionConcernId;
	}

	public void setRecommInterventionConcernId(Integer recommInterventionConcernId) {
		this.recommInterventionConcernId = recommInterventionConcernId;
	}

	public Integer getRecommInterventionGoalId() {
		return recommInterventionGoalId;
	}

	public void setRecommInterventionGoalId(Integer recommInterventionGoalId) {
		this.recommInterventionGoalId = recommInterventionGoalId;
	}

	public Integer getRecommInterventionCategoryId() {
		return recommInterventionCategoryId;
	}

	public void setRecommInterventionCategoryId(Integer recommInterventionCategoryId) {
		this.recommInterventionCategoryId = recommInterventionCategoryId;
	}

	public Integer getRecommInterventionCategoryName() {
		return recommInterventionCategoryName;
	}

	public void setRecommInterventionCategoryName(
			Integer recommInterventionCategoryName) {
		this.recommInterventionCategoryName = recommInterventionCategoryName;
	}

	public String getRecommInterventionDescription() {
		return recommInterventionDescription;
	}

	public void setRecommInterventionDescription(
			String recommInterventionDescription) {
		this.recommInterventionDescription = recommInterventionDescription;
	}

	public Integer getRecommInterventionRecommendedBy() {
		return recommInterventionRecommendedBy;
	}

	public void setRecommInterventionRecommendedBy(
			Integer recommInterventionRecommendedBy) {
		this.recommInterventionRecommendedBy = recommInterventionRecommendedBy;
	}

	public String getRecommInterventionRecommendedOn() {
		return recommInterventionRecommendedOn;
	}

	public void setRecommInterventionRecommendedOn(
			String recommInterventionRecommendedOn) {
		this.recommInterventionRecommendedOn = recommInterventionRecommendedOn;
	}

	public String getRecommInterventionCode() {
		return recommInterventionCode;
	}

	public void setRecommInterventionCode(String recommInterventionCode) {
		this.recommInterventionCode = recommInterventionCode;
	}

	public String getRecommInterventionCodeName() {
		return recommInterventionCodeName;
	}

	public void setRecommInterventionCodeName(String recommInterventionCodeName) {
		this.recommInterventionCodeName = recommInterventionCodeName;
	}

	public String getRecommInterventionCodeSystem() {
		return recommInterventionCodeSystem;
	}

	public void setRecommInterventionCodeSystem(String recommInterventionCodeSystem) {
		this.recommInterventionCodeSystem = recommInterventionCodeSystem;
	}

	public String getRecommInterventionCodeSystemname() {
		return recommInterventionCodeSystemname;
	}

	public void setRecommInterventionCodeSystemname(
			String recommInterventionCodeSystemname) {
		this.recommInterventionCodeSystemname = recommInterventionCodeSystemname;
	}

	public String getRecommInterventionProblemCodeSystem() {
		return recommInterventionProblemCodeSystem;
	}

	public void setRecommInterventionProblemCodeSystem(
			String recommInterventionProblemCodeSystem) {
		this.recommInterventionProblemCodeSystem = recommInterventionProblemCodeSystem;
	}

	public String getRecommInterventionProblemCodeSystemDescription() {
		return recommInterventionProblemCodeSystemDescription;
	}

	public void setRecommInterventionProblemCodeSystemDescription(
			String recommInterventionProblemCodeSystemDescription) {
		this.recommInterventionProblemCodeSystemDescription = recommInterventionProblemCodeSystemDescription;
	}

	public String getRecommInterventionProblemCode() {
		return recommInterventionProblemCode;
	}

	public void setRecommInterventionProblemCode(
			String recommInterventionProblemCode) {
		this.recommInterventionProblemCode = recommInterventionProblemCode;
	}

	public Integer getRecommInterventionStatus() {
		return recommInterventionStatus;
	}

	public void setRecommInterventionStatus(Integer recommInterventionStatus) {
		this.recommInterventionStatus = recommInterventionStatus;
	}

	public Integer getRecommInterventionOrderedBy() {
		return recommInterventionOrderedBy;
	}

	public void setRecommInterventionOrderedBy(Integer recommInterventionOrderedBy) {
		this.recommInterventionOrderedBy = recommInterventionOrderedBy;
	}

	public String getRecommInterventionOrderedOn() {
		return recommInterventionOrderedOn;
	}

	public void setRecommInterventionOrderedOn(String recommInterventionOrderedOn) {
		this.recommInterventionOrderedOn = recommInterventionOrderedOn;
	}

	public Integer getRecommInterventionPerformedBy() {
		return recommInterventionPerformedBy;
	}

	public void setRecommInterventionPerformedBy(
			Integer recommInterventionPerformedBy) {
		this.recommInterventionPerformedBy = recommInterventionPerformedBy;
	}

	public String getRecommInterventionPerformedOn() {
		return recommInterventionPerformedOn;
	}

	public void setRecommInterventionPerformedOn(
			String recommInterventionPerformedOn) {
		this.recommInterventionPerformedOn = recommInterventionPerformedOn;
	}

	public Integer getRecommInterventionNotDoneType() {
		return recommInterventionNotDoneType;
	}

	public void setRecommInterventionNotDoneType(
			Integer recommInterventionNotDoneType) {
		this.recommInterventionNotDoneType = recommInterventionNotDoneType;
	}

	public String getRecommInterventionNotDoneDescription() {
		return recommInterventionNotDoneDescription;
	}

	public void setRecommInterventionNotDoneDescription(
			String recommInterventionNotDoneDescription) {
		this.recommInterventionNotDoneDescription = recommInterventionNotDoneDescription;
	}

	public String getRecommInterventionNotDoneCode() {
		return recommInterventionNotDoneCode;
	}

	public void setRecommInterventionNotDoneCode(
			String recommInterventionNotDoneCode) {
		this.recommInterventionNotDoneCode = recommInterventionNotDoneCode;
	}

	public String getRecommInterventionNotDoneCodeSystem() {
		return recommInterventionNotDoneCodeSystem;
	}

	public void setRecommInterventionNotDoneCodeSystem(
			String recommInterventionNotDoneCodeSystem) {
		this.recommInterventionNotDoneCodeSystem = recommInterventionNotDoneCodeSystem;
	}

	public String getRecommInterventionNotes() {
		return recommInterventionNotes;
	}

	public void setRecommInterventionNotes(String recommInterventionNotes) {
		this.recommInterventionNotes = recommInterventionNotes;
	}

	public Integer getRecommInterventionCreatedBy() {
		return recommInterventionCreatedBy;
	}

	public void setRecommInterventionCreatedBy(Integer recommInterventionCreatedBy) {
		this.recommInterventionCreatedBy = recommInterventionCreatedBy;
	}

	public String getRecommInterventionCreatedOn() {
		return recommInterventionCreatedOn;
	}

	public void setRecommInterventionCreatedOn(String recommInterventionCreatedOn) {
		this.recommInterventionCreatedOn = recommInterventionCreatedOn;
	}

	public Integer getRecommInterventionModifiedBy() {
		return recommInterventionModifiedBy;
	}

	public void setRecommInterventionModifiedBy(Integer recommInterventionModifiedBy) {
		this.recommInterventionModifiedBy = recommInterventionModifiedBy;
	}

	public String getRecommInterventionModifiedOn() {
		return recommInterventionModifiedOn;
	}

	public void setRecommInterventionModifiedOn(String recommInterventionModifiedOn) {
		this.recommInterventionModifiedOn = recommInterventionModifiedOn;
	}

	public DateFormat getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(DateFormat timeFormat) {
		this.timeFormat = timeFormat;
	}

	public Integer getRecommResponsibleParty() {
		return recommResponsibleParty;
	}

	public void setRecommResponsibleParty(Integer recommResponsibleParty) {
		this.recommResponsibleParty = recommResponsibleParty;
	}

	public String getRecommCreatedName() {
		return recommCreatedName;
	}

	public void setRecommCreatedName(String recommCreatedName) {
		this.recommCreatedName = recommCreatedName;
	}

	public String getRecommModifiedName() {
		return recommModifiedName;
	}

	public void setRecommModifiedName(String recommModifiedName) {
		this.recommModifiedName = recommModifiedName;
	}

	public String getConcernDesc() {
		return concernDesc;
	}

	public void setConcernDesc(String concernDesc) {
		this.concernDesc = concernDesc;
	}

	public String getGoalDesc() {
		return goalDesc;
	}

	public void setGoalDesc(String goalDesc) {
		this.goalDesc = goalDesc;
	}
}
