package com.glenwood.glaceemr.server.application.services.chart.careplan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarePlanGoalBean {
	Integer carePlanGoalId;
	Integer carePlanGoalPatientId;
	Integer carePlanGoalEncounterId;
	Integer carePlanGoalConcernId;
	String carePlanGoalConcernDesc;
	Integer carePlanGoalPriority;
	Integer carePlanGoalType;
	Integer carePlanGoalTerm;
	Integer carePlanGoalProviderId;
	String carePlanGoalDesc;
	String carePlanGoalCode;
	String carePlanGoalCodeSystem;
	String carePlanGoalCodeSystemName;
	String carePlanGoalCodeDescription;
	String carePlanGoalCodeOperator;
	String carePlanGoalValue;
	String carePlanGoalUnit;
	Integer carePlanGoalStatus;
	Integer carePlanGoalProgress;
	String carePlanGoalTargetDate;
	String carePlanGoalNextReviewDate;
	String carePlanGoalNotes;
	Integer carePlanGoalCreatedBy;
	String carePlanGoalCreatedOn;
	Integer carePlanGoalModifiedBy;
	String carePlanGoalModifiedOn;
	Integer episodeId;
	Integer carePlanGoalFrom;
	Integer aggregateValue;
	Integer carePlanGoalResultStatus;
	Integer carePlanGoalOrder;
	String carePlanGoalValueOne;
	Integer outcomeCreatedBy;
	String outcomeCreatedOn;
	Integer assistanceStatus;
	Integer levelStatus;
	Integer outcomeId;
	String carePlanGoalCreatedName;
	String carePlanGoalModifiedName;
	Boolean carePlanOutcomeTargetedGoal;
	
	public CarePlanGoalBean(){	
	}

	public CarePlanGoalBean(Integer carePlanGoalId,
			Integer carePlanGoalPatientId, Integer carePlanGoalEncounterId,
			Integer carePlanGoalConcernId, String carePlanGoalConcernDesc,
			Integer carePlanGoalPriority, Integer carePlanGoalType,
			Integer carePlanGoalTerm, Integer carePlanGoalProviderId,
			String carePlanGoalDesc,
			String carePlanGoalCode, String carePlanGoalCodeDescription,
			String carePlanGoalCodeOperator, String carePlanGoalValue,
			String carePlanGoalUnit, Integer carePlanGoalStatus,
			Date carePlanGoalTargetDate, Date carePlanGoalNextReviewDate,
			String carePlanGoalNotes,Integer carePlanGoalFrom,Integer carePlanGoalProgress,Integer carePlanGoalResultStatus,Integer episodeId,Integer carePlanGoalOrder,String carePlanGoalValueOne,
			Integer carePlanAssistanceStatus,Integer carePlanLevelStatus) {
		
		super();
		
		DateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.carePlanGoalId = carePlanGoalId;
		this.carePlanGoalPatientId = carePlanGoalPatientId;
		this.carePlanGoalEncounterId = carePlanGoalEncounterId;
		this.carePlanGoalConcernId = carePlanGoalConcernId;
		this.carePlanGoalConcernDesc = carePlanGoalConcernDesc;
		this.carePlanGoalPriority = carePlanGoalPriority;
		this.carePlanGoalType = carePlanGoalType;
		this.carePlanGoalTerm = carePlanGoalTerm;
		this.carePlanGoalProviderId = carePlanGoalProviderId;
		this.carePlanGoalDesc=carePlanGoalDesc;
		this.carePlanGoalCode = carePlanGoalCode;
		this.carePlanGoalCodeDescription = carePlanGoalCodeDescription;
		this.carePlanGoalCodeOperator = carePlanGoalCodeOperator;
		this.carePlanGoalValue = carePlanGoalValue;
		this.carePlanGoalUnit = carePlanGoalUnit;
		this.carePlanGoalStatus = carePlanGoalStatus;
		if(carePlanGoalTargetDate!=null)
		this.carePlanGoalTargetDate = timeFormat.format(carePlanGoalTargetDate);
		else
			this.carePlanGoalTargetDate = "";
		if(carePlanGoalNextReviewDate!=null)
		this.carePlanGoalNextReviewDate = timeFormat.format(carePlanGoalNextReviewDate);
		else
			this.carePlanGoalNextReviewDate = "";	
		this.carePlanGoalNotes = carePlanGoalNotes;
		this.carePlanGoalFrom = carePlanGoalFrom;
		this.carePlanGoalProgress=carePlanGoalProgress;
		this.carePlanGoalResultStatus=carePlanGoalResultStatus;
		this.episodeId=episodeId;
		this.carePlanGoalOrder=carePlanGoalOrder;
		this.carePlanGoalValueOne=carePlanGoalValueOne;
		this.assistanceStatus=carePlanAssistanceStatus;
		this.levelStatus=carePlanLevelStatus;
	}
	
	public CarePlanGoalBean(Integer carePlanGoalId,
			Integer carePlanGoalPatientId, Integer carePlanGoalEncounterId,
			Integer carePlanGoalConcernId, 
			Integer carePlanGoalPriority, Integer carePlanGoalType,
			Integer carePlanGoalTerm, Integer carePlanGoalProviderId,
			String carePlanGoalDesc,
			String carePlanGoalCode, String carePlanGoalCodeDescription,
			String carePlanGoalCodeOperator, String carePlanGoalValue,
			String carePlanGoalUnit, Integer carePlanGoalStatus,
			Date carePlanGoalTargetDate, Date carePlanGoalNextReviewDate,
			String carePlanGoalNotes,Integer carePlanGoalFrom,Integer carePlanGoalProgress,Integer carePlanGoalResultStatus,Integer episodeId,Integer carePlanGoalOrder,String carePlanGoalValueOne,
			Integer outcomeCreatedBy,Date outcomeCreatedOn) {
		
		super();
		
		DateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.carePlanGoalId = carePlanGoalId;
		this.carePlanGoalPatientId = carePlanGoalPatientId;
		this.carePlanGoalEncounterId = carePlanGoalEncounterId;
		this.carePlanGoalConcernId = carePlanGoalConcernId;
		this.carePlanGoalConcernDesc = carePlanGoalConcernDesc;
		this.carePlanGoalPriority = carePlanGoalPriority;
		this.carePlanGoalType = carePlanGoalType;
		this.carePlanGoalTerm = carePlanGoalTerm;
		this.carePlanGoalProviderId = carePlanGoalProviderId;
		this.carePlanGoalDesc=carePlanGoalDesc;
		this.carePlanGoalCode = carePlanGoalCode;
		this.carePlanGoalCodeDescription = carePlanGoalCodeDescription;
		this.carePlanGoalCodeOperator = carePlanGoalCodeOperator;
		this.carePlanGoalValue = carePlanGoalValue;
		this.carePlanGoalUnit = carePlanGoalUnit;
		this.carePlanGoalStatus = carePlanGoalStatus;
		if(carePlanGoalTargetDate!=null)
		this.carePlanGoalTargetDate = timeFormat.format(carePlanGoalTargetDate);
		else
			this.carePlanGoalTargetDate = "";
		if(carePlanGoalNextReviewDate!=null)
		this.carePlanGoalNextReviewDate = timeFormat.format(carePlanGoalNextReviewDate);
		else
			this.carePlanGoalNextReviewDate = "";	
		this.carePlanGoalNotes = carePlanGoalNotes;
		this.carePlanGoalFrom = carePlanGoalFrom;
		this.carePlanGoalProgress=carePlanGoalProgress;
		this.carePlanGoalResultStatus=carePlanGoalResultStatus;
		this.episodeId=episodeId;
		this.carePlanGoalOrder=carePlanGoalOrder;
		this.carePlanGoalValueOne=carePlanGoalValueOne;
		this.outcomeCreatedBy=outcomeCreatedBy;
		if(outcomeCreatedOn!=null)
			this.outcomeCreatedOn = timeFormat.format(outcomeCreatedOn);
		else
			this.outcomeCreatedOn = "";
	}

	public CarePlanGoalBean(Integer carePlanGoalId,
			Integer carePlanGoalPatientId, Integer carePlanGoalEncounterId,
			Integer carePlanGoalConcernId, String carePlanGoalConcernDesc,
			Integer carePlanGoalPriority, Integer carePlanGoalType,
			Integer carePlanGoalTerm, Integer carePlanGoalProviderId,
			String carePlanGoalDesc,
			String carePlanGoalCode, String carePlanGoalCodeDescription,
			String carePlanGoalCodeOperator, String carePlanGoalValue,
			String carePlanGoalUnit, Integer carePlanGoalStatus,
			Date carePlanGoalTargetDate, Date carePlanGoalNextReviewDate,
			String carePlanGoalNotes,Integer carePlanGoalFrom,Integer carePlanGoalProgress,Integer carePlanGoalResultStatus,Integer episodeId,Integer carePlanGoalOrder,String carePlanGoalValueOne,
			Integer carePlanAssistanceStatus,Integer carePlanLevelStatus,Integer carePlanOutcomeId) {
		
		super();
		
		DateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.carePlanGoalId = carePlanGoalId;
		this.carePlanGoalPatientId = carePlanGoalPatientId;
		this.carePlanGoalEncounterId = carePlanGoalEncounterId;
		this.carePlanGoalConcernId = carePlanGoalConcernId;
		this.carePlanGoalConcernDesc = carePlanGoalConcernDesc;
		this.carePlanGoalPriority = carePlanGoalPriority;
		this.carePlanGoalType = carePlanGoalType;
		this.carePlanGoalTerm = carePlanGoalTerm;
		this.carePlanGoalProviderId = carePlanGoalProviderId;
		this.carePlanGoalDesc=carePlanGoalDesc;
		this.carePlanGoalCode = carePlanGoalCode;
		this.carePlanGoalCodeDescription = carePlanGoalCodeDescription;
		this.carePlanGoalCodeOperator = carePlanGoalCodeOperator;
		this.carePlanGoalValue = carePlanGoalValue;
		this.carePlanGoalUnit = carePlanGoalUnit;
		this.carePlanGoalStatus = carePlanGoalStatus;
		if(carePlanGoalTargetDate!=null)
		this.carePlanGoalTargetDate = timeFormat.format(carePlanGoalTargetDate);
		else
			this.carePlanGoalTargetDate = "";
		if(carePlanGoalNextReviewDate!=null)
		this.carePlanGoalNextReviewDate = timeFormat.format(carePlanGoalNextReviewDate);
		else
			this.carePlanGoalNextReviewDate = "";	
		this.carePlanGoalNotes = carePlanGoalNotes;
		this.carePlanGoalFrom = carePlanGoalFrom;
		this.carePlanGoalProgress=carePlanGoalProgress;
		this.carePlanGoalResultStatus=carePlanGoalResultStatus;
		this.episodeId=episodeId;
		this.carePlanGoalOrder=carePlanGoalOrder;
		this.carePlanGoalValueOne=carePlanGoalValueOne;
		this.assistanceStatus=carePlanAssistanceStatus;
		this.levelStatus=carePlanLevelStatus;
		this.outcomeId=carePlanOutcomeId;
	}

	public CarePlanGoalBean(Integer carePlanGoalId,
			Integer carePlanGoalPatientId, Integer carePlanGoalEncounterId,
			Integer carePlanGoalConcernId, String carePlanGoalConcernDesc,
			Integer carePlanGoalPriority, Integer carePlanGoalType,
			Integer carePlanGoalTerm, Integer carePlanGoalProviderId,
			String carePlanGoalDesc,
			String carePlanGoalCode, String carePlanGoalCodeDescription,
			String carePlanGoalCodeOperator, String carePlanGoalValue,
			String carePlanGoalUnit, Integer carePlanGoalStatus,
			Date carePlanGoalTargetDate, Date carePlanGoalNextReviewDate,
			String carePlanGoalNotes,Integer carePlanGoalFrom,Integer carePlanGoalProgress,Integer carePlanGoalResultStatus,Integer episodeId,Integer carePlanGoalOrder,String carePlanGoalValueOne,
			Integer carePlanAssistanceStatus,Integer carePlanLevelStatus,Boolean carePlanOutcomeTargetedGoal,Integer goalOutcomeId,
			String goalCreatedBy,String goalModifiedBy,
			Date goalCreatedOn,Date goalModifiedOn) {
		
		super();
		
		DateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.carePlanGoalId = carePlanGoalId;
		this.carePlanGoalPatientId = carePlanGoalPatientId;
		this.carePlanGoalEncounterId = carePlanGoalEncounterId;
		this.carePlanGoalConcernId = carePlanGoalConcernId;
		this.carePlanGoalConcernDesc = carePlanGoalConcernDesc;
		this.carePlanGoalPriority = carePlanGoalPriority;
		this.carePlanGoalType = carePlanGoalType;
		this.carePlanGoalTerm = carePlanGoalTerm;
		this.carePlanGoalProviderId = carePlanGoalProviderId;
		this.carePlanGoalDesc=carePlanGoalDesc;
		this.carePlanGoalCode = carePlanGoalCode;
		this.carePlanGoalCodeDescription = carePlanGoalCodeDescription;
		this.carePlanGoalCodeOperator = carePlanGoalCodeOperator;
		this.carePlanGoalValue = carePlanGoalValue;
		this.carePlanGoalUnit = carePlanGoalUnit;
		this.carePlanGoalStatus = carePlanGoalStatus;
		if(carePlanGoalTargetDate!=null)
		this.carePlanGoalTargetDate = timeFormat.format(carePlanGoalTargetDate);
		else
			this.carePlanGoalTargetDate = "";
		if(carePlanGoalNextReviewDate!=null)
		this.carePlanGoalNextReviewDate = timeFormat.format(carePlanGoalNextReviewDate);
		else
			this.carePlanGoalNextReviewDate = "";	
		this.carePlanGoalNotes = carePlanGoalNotes;
		this.carePlanGoalFrom = carePlanGoalFrom;
		this.carePlanGoalProgress=carePlanGoalProgress;
		this.carePlanGoalResultStatus=carePlanGoalResultStatus;
		this.episodeId=episodeId;
		this.carePlanGoalOrder=carePlanGoalOrder;
		this.carePlanGoalValueOne=carePlanGoalValueOne;
		this.assistanceStatus=carePlanAssistanceStatus;
		this.levelStatus=carePlanLevelStatus;
		this.carePlanOutcomeTargetedGoal = carePlanOutcomeTargetedGoal;
		this.outcomeId=goalOutcomeId;
		this.carePlanGoalCreatedName = goalCreatedBy;
		this.carePlanGoalModifiedName= goalModifiedBy;

		if(goalCreatedOn!=null)
			this.carePlanGoalCreatedOn = timeFormat.format(goalCreatedOn);
		else
			this.carePlanGoalCreatedOn = "";
		if(goalModifiedOn!=null)
			this.carePlanGoalModifiedOn = timeFormat.format(goalModifiedOn);
		else
			this.carePlanGoalModifiedOn = "";
	}
	public Integer getCarePlanGoalId() {
		return carePlanGoalId;
	}

	public void setCarePlanGoalId(Integer carePlanGoalId) {
		this.carePlanGoalId = carePlanGoalId;
	}

	public Integer getCarePlanGoalPatientId() {
		return carePlanGoalPatientId;
	}

	public void setCarePlanGoalPatientId(Integer carePlanGoalPatientId) {
		this.carePlanGoalPatientId = carePlanGoalPatientId;
	}

	public Integer getCarePlanGoalEncounterId() {
		return carePlanGoalEncounterId;
	}

	public void setCarePlanGoalEncounterId(Integer carePlanGoalEncounterId) {
		this.carePlanGoalEncounterId = carePlanGoalEncounterId;
	}

	public Integer getCarePlanGoalConcernId() {
		return carePlanGoalConcernId;
	}

	public void setCarePlanGoalConcernId(Integer carePlanGoalConcernId) {
		this.carePlanGoalConcernId = carePlanGoalConcernId;
	}

	public String getCarePlanGoalConcernDesc() {
		return carePlanGoalConcernDesc;
	}

	public void setCarePlanGoalConcernDesc(String carePlanGoalConcernDesc) {
		this.carePlanGoalConcernDesc = carePlanGoalConcernDesc;
	}

	public Integer getCarePlanGoalPriority() {
		return carePlanGoalPriority;
	}

	public void setCarePlanGoalPriority(Integer carePlanGoalPriority) {
		this.carePlanGoalPriority = carePlanGoalPriority;
	}
	
	public Integer getCarePlanGoalType() {
		return carePlanGoalType;
	}

	public void setCarePlanGoalType(Integer carePlanGoalType) {
		this.carePlanGoalType = carePlanGoalType;
	}

	public Integer getCarePlanGoalTerm() {
		return carePlanGoalTerm;
	}

	public void setCarePlanGoalTerm(Integer carePlanGoalTerm) {
		this.carePlanGoalTerm = carePlanGoalTerm;
	}

	public Integer getCarePlanGoalProviderId() {
		return carePlanGoalProviderId;
	}

	public void setCarePlanGoalProviderId(Integer carePlanGoalProviderId) {
		this.carePlanGoalProviderId = carePlanGoalProviderId;
	}
	public String getCarePlanGoalDesc() {
		return carePlanGoalDesc;
	}

	public void setCarePlanGoalDesc(String carePlanGoalDesc) {
		this.carePlanGoalDesc = carePlanGoalDesc;
	}
	public String getCarePlanGoalCode() {
		return carePlanGoalCode;
	}

	public void setCarePlanGoalCode(String carePlanGoalCode) {
		this.carePlanGoalCode = carePlanGoalCode;
	}

	public String getCarePlanGoalCodeDescription() {
		return carePlanGoalCodeDescription;
	}

	public void setCarePlanGoalCodeDescription(
			String carePlanGoalCodeDescription) {
		this.carePlanGoalCodeDescription = carePlanGoalCodeDescription;
	}

	public String getCarePlanGoalCodeOperator() {
		return carePlanGoalCodeOperator;
	}

	public void setCarePlanGoalCodeOperator(String carePlanGoalCodeOperator) {
		this.carePlanGoalCodeOperator = carePlanGoalCodeOperator;
	}

	public String getCarePlanGoalValue() {
		return carePlanGoalValue;
	}

	public void setCarePlanGoalValue(String carePlanGoalValue) {
		this.carePlanGoalValue = carePlanGoalValue;
	}

	public String getCarePlanGoalUnit() {
		return carePlanGoalUnit;
	}

	public void setCarePlanGoalUnit(String carePlanGoalUnit) {
		this.carePlanGoalUnit = carePlanGoalUnit;
	}

	public Integer getCarePlanGoalStatus() {
		return carePlanGoalStatus;
	}

	public void setCarePlanGoalStatus(Integer carePlanGoalStatus) {
		this.carePlanGoalStatus = carePlanGoalStatus;
	}

	public String getCarePlanGoalTargetDate() {
		return carePlanGoalTargetDate;
	}

	public void setCarePlanGoalTargetDate(String carePlanGoalTargetDate) {
		this.carePlanGoalTargetDate = carePlanGoalTargetDate;
	}

	public String getCarePlanGoalNextReviewDate() {
		return carePlanGoalNextReviewDate;
	}

	public void setCarePlanGoalNextReviewDate(String carePlanGoalNextReviewDate) {
		this.carePlanGoalNextReviewDate = carePlanGoalNextReviewDate;
	}

	public String getCarePlanGoalNotes() {
		return carePlanGoalNotes;
	}

	public void setCarePlanGoalNotes(String carePlanGoalNotes) {
		this.carePlanGoalNotes = carePlanGoalNotes;
	}

	public Integer getCarePlanGoalProgress() {
		return carePlanGoalProgress;
	}

	public void setCarePlanGoalProgress(Integer carePlanGoalProgress) {
		this.carePlanGoalProgress = carePlanGoalProgress;
	}

	public Integer getCarePlanGoalCreatedBy() {
		return carePlanGoalCreatedBy;
	}

	public void setCarePlanGoalCreatedBy(Integer carePlanGoalCreatedBy) {
		this.carePlanGoalCreatedBy = carePlanGoalCreatedBy;
	}

	public String getCarePlanGoalCreatedOn() {
		return carePlanGoalCreatedOn;
	}

	public void setCarePlanGoalCreatedOn(String carePlanGoalCreatedOn) {
		this.carePlanGoalCreatedOn = carePlanGoalCreatedOn;
	}

	public Integer getCarePlanGoalModifiedBy() {
		return carePlanGoalModifiedBy;
	}

	public void setCarePlanGoalModifiedBy(Integer carePlanGoalModifiedBy) {
		this.carePlanGoalModifiedBy = carePlanGoalModifiedBy;
	}

	public String getCarePlanGoalModifiedOn() {
		return carePlanGoalModifiedOn;
	}

	public void setCarePlanGoalModifiedOn(String carePlanGoalModifiedOn) {
		this.carePlanGoalModifiedOn = carePlanGoalModifiedOn;
	}
	
	public Integer getEpisodeId() {
		return episodeId;
	}

	public void setEpisodeId(Integer episodeId) {
		this.episodeId = episodeId;
	}

	public Integer getCarePlanGoalFrom() {
		return carePlanGoalFrom;
	}

	public void setCarePlanGoalFrom(Integer carePlanGoalFrom) {
		this.carePlanGoalFrom = carePlanGoalFrom;
	}

	public Integer getAggregateValue() {
		return aggregateValue;
	}

	public void setAggregateValue(Integer aggregateValue) {
		this.aggregateValue = aggregateValue;
	}

	public Integer getCarePlanGoalResultStatus() {
		return carePlanGoalResultStatus;
	}

	public void setCarePlanGoalResultStatus(Integer carePlanGoalResultStatus) {
		this.carePlanGoalResultStatus = carePlanGoalResultStatus;
	}

	public Integer getCarePlanGoalOrder() {
		return carePlanGoalOrder;
	}

	public void setCarePlanGoalOrder(Integer carePlanGoalOrder) {
		this.carePlanGoalOrder = carePlanGoalOrder;
	}

	public String getCarePlanGoalCodeSystem() {
		return carePlanGoalCodeSystem;
	}

	public void setCarePlanGoalCodeSystem(String carePlanGoalCodeSystem) {
		this.carePlanGoalCodeSystem = carePlanGoalCodeSystem;
	}

	public String getCarePlanGoalCodeSystemName() {
		return carePlanGoalCodeSystemName;
	}

	public void setCarePlanGoalCodeSystemName(String carePlanGoalCodeSystemName) {
		this.carePlanGoalCodeSystemName = carePlanGoalCodeSystemName;
	}

	public String getCarePlanGoalValueOne() {
		return carePlanGoalValueOne;
	}

	public void setCarePlanGoalValueOne(String carePlanGoalValueOne) {
		this.carePlanGoalValueOne = carePlanGoalValueOne;
	}

	public Integer getOutcomeCreatedBy() {
		return outcomeCreatedBy;
	}

	public void setOutcomeCreatedBy(Integer outcomeCreatedBy) {
		this.outcomeCreatedBy = outcomeCreatedBy;
	}

	public String getOutcomeCreatedOn() {
		return outcomeCreatedOn;
	}

	public void setOutcomeCreatedOn(String outcomeCreatedOn) {
		this.outcomeCreatedOn = outcomeCreatedOn;
	}

	public Integer getAssistanceStatus() {
		return assistanceStatus;
	}

	public void setAssistanceStatus(Integer assistanceStatus) {
		this.assistanceStatus = assistanceStatus;
	}

	public Integer getLevelStatus() {
		return levelStatus;
	}

	public void setLevelStatus(Integer levelStatus) {
		this.levelStatus = levelStatus;
	}

	public Integer getOutcomeId() {
		return outcomeId;
	}

	public void setOutcomeId(Integer outcomeId) {
		this.outcomeId = outcomeId;
	}

	public String getCarePlanGoalCreatedName() {
		return carePlanGoalCreatedName;
	}

	public void setCarePlanGoalCreatedName(String carePlanGoalCreatedName) {
		this.carePlanGoalCreatedName = carePlanGoalCreatedName;
	}

	public String getCarePlanGoalModifiedName() {
		return carePlanGoalModifiedName;
	}

	public void setCarePlanGoalModifiedName(String carePlanGoalModifiedName) {
		this.carePlanGoalModifiedName = carePlanGoalModifiedName;
	}

	public Boolean getCarePlanOutcomeTargetedGoal() {
		return carePlanOutcomeTargetedGoal;
	}

	public void setCarePlanOutcomeTargetedGoal(Boolean carePlanOutcomeTargetedGoal) {
		this.carePlanOutcomeTargetedGoal = carePlanOutcomeTargetedGoal;
	}
	
}