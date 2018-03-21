package com.glenwood.glaceemr.server.application.services.chart.careplan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder.In;

public class CarePlanSummaryBean {
	
	Integer carePlanSummaryId;
	Integer carePlanSummaryPatientId;
	Integer carePlanSummaryEncounterId;
	Integer carePlanSummaryConcernId;
	String carePlanSummaryConcernDesc;
	String carePlanSummaryGoalDesc;
	Integer carePlanSummaryGoalTerm;
	String carePlanSummaryMasteredDate;
	String carePlanSummaryComments;
	Integer carePlanSummaryAggregate;
	Integer carePlanSummaryEpisodeId;
	Integer carePlanSummaryGoalId;
	Integer carePlanSummaryGoalProgress;
	Integer carePlanSummaryGoalStatus;
	Integer carePlanSummaryGoalOrder;
	Integer carePlanSummaryOutcomeId;
	
	public CarePlanSummaryBean(Integer carePlanSummaryId,
			Integer carePlanSummaryPatientId,
			Integer carePlanSummaryEncounterId,
			Integer carePlanSummaryConcernId,
			String carePlanSummaryConcernDesc, String carePlanSummaryGoalDesc,
			Integer carePlanSummaryGoalTerm,
			Date carePlanSummaryMasteredDate, String carePlanSummaryComments,
			Integer carePlanSummaryAggregate, Integer carePlanSummaryEpisodeId, Integer carePlanSummaryGoalId, Integer carePlanSummaryGoalProgress,Integer carePlanSummaryGoalStatus,Integer carePlanSummaryGoalOrder,
			Integer outcomeId) {
		super();
		DateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy");

		this.carePlanSummaryId = carePlanSummaryId;
		this.carePlanSummaryPatientId = carePlanSummaryPatientId;
		this.carePlanSummaryEncounterId = carePlanSummaryEncounterId;
		this.carePlanSummaryConcernId = carePlanSummaryConcernId;
		this.carePlanSummaryConcernDesc = carePlanSummaryConcernDesc;
		this.carePlanSummaryGoalDesc = carePlanSummaryGoalDesc;
		this.carePlanSummaryGoalTerm = carePlanSummaryGoalTerm;
		if(carePlanSummaryMasteredDate!=null)
			this.carePlanSummaryMasteredDate = timeFormat.format(carePlanSummaryMasteredDate);
			else
			this.carePlanSummaryMasteredDate = "";
		this.carePlanSummaryComments = carePlanSummaryComments;
		this.carePlanSummaryAggregate = carePlanSummaryAggregate;
		this.carePlanSummaryEpisodeId = carePlanSummaryEpisodeId;
		this.carePlanSummaryGoalId = carePlanSummaryGoalId; 
		this.carePlanSummaryGoalProgress = carePlanSummaryGoalProgress;
		this.carePlanSummaryGoalStatus = carePlanSummaryGoalStatus;
		this.carePlanSummaryGoalOrder = carePlanSummaryGoalOrder;
		this.carePlanSummaryOutcomeId = outcomeId;
	}

	public CarePlanSummaryBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getCarePlanSummaryId() {
		return carePlanSummaryId;
	}

	public void setCarePlanSummaryId(Integer carePlanSummaryId) {
		this.carePlanSummaryId = carePlanSummaryId;
	}

	public Integer getCarePlanSummaryPatientId() {
		return carePlanSummaryPatientId;
	}

	public void setCarePlanSummaryPatientId(Integer carePlanSummaryPatientId) {
		this.carePlanSummaryPatientId = carePlanSummaryPatientId;
	}

	public Integer getCarePlanSummaryEncounterId() {
		return carePlanSummaryEncounterId;
	}

	public void setCarePlanSummaryEncounterId(Integer carePlanSummaryEncounterId) {
		this.carePlanSummaryEncounterId = carePlanSummaryEncounterId;
	}

	public Integer getCarePlanSummaryConcernId() {
		return carePlanSummaryConcernId;
	}

	public void setCarePlanSummaryConcernId(Integer carePlanSummaryConcernId) {
		this.carePlanSummaryConcernId = carePlanSummaryConcernId;
	}

	public String getCarePlanSummaryConcernDesc() {
		return carePlanSummaryConcernDesc;
	}

	public void setCarePlanSummaryConcernDesc(String carePlanSummaryConcernDesc) {
		this.carePlanSummaryConcernDesc = carePlanSummaryConcernDesc;
	}

	public String getCarePlanSummaryGoalDesc() {
		return carePlanSummaryGoalDesc;
	}

	public void setCarePlanSummaryGoalDesc(String carePlanSummaryGoalDesc) {
		this.carePlanSummaryGoalDesc = carePlanSummaryGoalDesc;
	}

	public Integer getCarePlanSummaryGoalTerm() {
		return carePlanSummaryGoalTerm;
	}

	public void setCarePlanSummaryGoalTerm(Integer carePlanSummaryGoalTerm) {
		this.carePlanSummaryGoalTerm = carePlanSummaryGoalTerm;
	}

	public String getCarePlanSummaryMasteredDate() {
		return carePlanSummaryMasteredDate;
	}

	public void setCarePlanSummaryMasteredDate(String carePlanSummaryMasteredDate) {
		this.carePlanSummaryMasteredDate = carePlanSummaryMasteredDate;
	}

	public String getCarePlanSummaryComments() {
		return carePlanSummaryComments;
	}

	public void setCarePlanSummaryComments(String carePlanSummaryComments) {
		this.carePlanSummaryComments = carePlanSummaryComments;
	}

	public Integer getCarePlanSummaryAggregate() {
		return carePlanSummaryAggregate;
	}

	public void setCarePlanSummaryAggregate(Integer carePlanSummaryAggregate) {
		this.carePlanSummaryAggregate = carePlanSummaryAggregate;
	}

	public Integer getCarePlanSummaryEpisodeId() {
		return carePlanSummaryEpisodeId;
	}

	public void setCarePlanSummaryEpisodeId(Integer carePlanSummaryEpisodeId) {
		this.carePlanSummaryEpisodeId = carePlanSummaryEpisodeId;
	}

	public Integer getCarePlanSummaryGoalId() {
		return carePlanSummaryGoalId;
	}

	public void setCarePlanSummaryGoalId(Integer carePlanSummaryGoalId) {
		this.carePlanSummaryGoalId = carePlanSummaryGoalId;
	}

	public Integer getCarePlanSummaryGoalProgress() {
		return carePlanSummaryGoalProgress;
	}

	public void setCarePlanSummaryGoalProgress(Integer carePlanSummaryGoalProgress) {
		this.carePlanSummaryGoalProgress = carePlanSummaryGoalProgress;
	}

	public Integer getCarePlanSummaryGoalStatus() {
		return carePlanSummaryGoalStatus;
	}

	public void setCarePlanSummaryGoalStatus(Integer carePlanSummaryGoalStatus) {
		this.carePlanSummaryGoalStatus = carePlanSummaryGoalStatus;
	}

	public Integer getCarePlanSummaryGoalOrder() {
		return carePlanSummaryGoalOrder;
	}

	public void setCarePlanSummaryGoalOrder(Integer carePlanSummaryGoalOrder) {
		this.carePlanSummaryGoalOrder = carePlanSummaryGoalOrder;
	}

	public Integer getCarePlanSummaryOutcomeId() {
		return carePlanSummaryOutcomeId;
	}

	public void setCarePlanSummaryOutcomeId(Integer carePlanSummaryOutcomeId) {
		this.carePlanSummaryOutcomeId = carePlanSummaryOutcomeId;
	}
}
