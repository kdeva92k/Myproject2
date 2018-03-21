package com.glenwood.glaceemr.server.application.services.chart.careplan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarePlanOutcomeBean {

	Integer outcomeId;
	Integer outcomeGoalId;
	String outcomeReviewDate;
	String outcomeTargetDate;
	String outcomeNotes;
	Integer outcomeProgress;
	String outcomeReviewedBy;
	Integer outcomeStatus;
	Integer goalResultStatus;

	public CarePlanOutcomeBean(Integer outcomeId, Integer outcomeGoalId,
			Date outcomeReviewDate, Date outcomeTargetDate,
			String outcomeNotes, Integer outcomeProgress,
			String outcomeReviewedBy, Integer outcomeStatus,Integer goalResultStatus) {	

		super();
		DateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.outcomeId = outcomeId;
		this.outcomeGoalId = outcomeGoalId;
		if(outcomeReviewDate!=null)
			this.outcomeReviewDate = timeFormat.format(outcomeReviewDate);
		else
			this.outcomeReviewDate="";
		if(outcomeTargetDate!=null)
			this.outcomeTargetDate = timeFormat.format(outcomeTargetDate);
		else
			this.outcomeTargetDate = "";
		this.outcomeNotes = outcomeNotes;
		this.outcomeProgress = outcomeProgress;
		this.outcomeReviewedBy = outcomeReviewedBy;
		this.outcomeStatus = outcomeStatus;
		this.goalResultStatus=goalResultStatus;
	}

	public Integer getOutcomeId() {
		return outcomeId;
	}
	public void setOutcomeId(Integer outcomeId) {
		this.outcomeId = outcomeId;
	}
	public Integer getOutcomeGoalId() {
		return outcomeGoalId;
	}
	public void setOutcomeGoalId(Integer outcomeGoalId) {
		this.outcomeGoalId = outcomeGoalId;
	}
	public String getOutcomeReviewDate() {
		return outcomeReviewDate;
	}
	public void setOutcomeReviewDate(String outcomeReviewDate) {
		this.outcomeReviewDate = outcomeReviewDate;
	}
	public String getOutcomeTargetDate() {
		return outcomeTargetDate;
	}
	public void setOutcomeTargetDate(String outcomeTargetDate) {
		this.outcomeTargetDate = outcomeTargetDate;
	}
	public String getOutcomeNotes() {
		return outcomeNotes;
	}
	public void setOutcomeNotes(String outcomeNotes) {
		this.outcomeNotes = outcomeNotes;
	}
	public Integer getOutcomeProgress() {
		return outcomeProgress;
	}
	public void setOutcomeProgress(Integer outcomeProgress) {
		this.outcomeProgress = outcomeProgress;
	}
	public String getOutcomeReviewedBy() {
		return outcomeReviewedBy;
	}
	public void setOutcomeReviewedBy(String outcomeReviewedBy) {
		this.outcomeReviewedBy = outcomeReviewedBy;
	}
	public Integer getOutcomeStatus() {
		return outcomeStatus;
	}
	public void setOutcomeStatus(Integer outcomeStatus) {
		this.outcomeStatus = outcomeStatus;
	}

	public Integer getGoalResultStatus() {
		return goalResultStatus;
	}

	public void setGoalResultStatus(Integer goalResultStatus) {
		this.goalResultStatus = goalResultStatus;
	}
	
}
