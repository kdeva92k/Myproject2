package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "careplan_summary")
public class CarePlanSummary{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="careplan_summary_id_seq")
	@SequenceGenerator(name ="careplan_summary_id_seq", sequenceName="careplan_summary_id_seq", allocationSize=1)
	
	@Column(name="careplan_summary_id")
	private Integer carePlanSummaryId;
	
	@Column(name="careplan_summary_patient_id")
	private Integer carePlanSummaryPatientId;

	@Column(name="careplan_summary_encounter_id")
	private Integer carePlanSummaryEncounterId;
	
	@Column(name="careplan_summary_goal_id")
	private Integer carePlanSummaryGoalId;
	
	@Column(name="careplan_summary_concern_id")
	private Integer carePlanSummaryConcernId;
	
	@Column(name="careplan_summary_avg_value")
	private Integer carePlanSummaryAvgVal;
	
	@Column(name="careplan_summary_mastered_date")
	private Timestamp carePlanSummaryMasterDate;
	
	@Column(name="careplan_summary_comments")
	private String carePlanSummaryComments;
		
	@Column(name="careplan_summary_modified_by")
	private Integer carePlanSummaryModifiedBy;
	
	@Column(name="careplan_summary_modified_on")
	private Timestamp carePlanSummaryModifiedOn;

	@Column(name="careplan_summary_episode_id")
	private Integer carePlanSummaryEpisodeId;
	
	@Column(name="careplan_summary_goal_progress")
	private Integer carePlanSummaryGoalProgress;

	@Column(name="careplan_summary_outcome_id")
	private Integer carePlanSummaryOutcomeId;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "careplan_summary_concern_id", referencedColumnName = "careplan_concern_id", insertable = false, updatable = false)
	private CarePlanConcern carePlanConcern;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "careplan_summary_goal_id", referencedColumnName = "careplan_goal_id", insertable = false, updatable = false)
	private CarePlanGoal carePlanGoal;
	
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

	public Integer getCarePlanSummaryGoalId() {
		return carePlanSummaryGoalId;
	}

	public void setCarePlanSummaryGoalId(Integer carePlanSummaryGoalId) {
		this.carePlanSummaryGoalId = carePlanSummaryGoalId;
	}

	public Integer getCarePlanSummaryConcernId() {
		return carePlanSummaryConcernId;
	}

	public void setCarePlanSummaryConcernId(Integer carePlanSummaryConcernId) {
		this.carePlanSummaryConcernId = carePlanSummaryConcernId;
	}

	public Integer getCarePlanSummaryAvgVal() {
		return carePlanSummaryAvgVal;
	}

	public void setCarePlanSummaryAvgVal(Integer carePlanSummaryAvgVal) {
		this.carePlanSummaryAvgVal = carePlanSummaryAvgVal;
	}

	public Timestamp getCarePlanSummaryMasterDate() {
		return carePlanSummaryMasterDate;
	}

	public void setCarePlanSummaryMasterDate(Timestamp carePlanSummaryMasterDate) {
		this.carePlanSummaryMasterDate = carePlanSummaryMasterDate;
	}

	public String getCarePlanSummaryComments() {
		return carePlanSummaryComments;
	}

	public void setCarePlanSummaryComments(String carePlanSummaryComments) {
		this.carePlanSummaryComments = carePlanSummaryComments;
	}

	public Integer getCarePlanSummaryModifiedBy() {
		return carePlanSummaryModifiedBy;
	}

	public void setCarePlanSummaryModifiedBy(Integer carePlanSummaryModifiedBy) {
		this.carePlanSummaryModifiedBy = carePlanSummaryModifiedBy;
	}

	public Timestamp getCarePlanSummaryModifiedOn() {
		return carePlanSummaryModifiedOn;
	}

	public void setCarePlanSummaryModifiedOn(Timestamp carePlanSummaryModifiedOn) {
		this.carePlanSummaryModifiedOn = carePlanSummaryModifiedOn;
	}

	public Integer getCarePlanSummaryEpisodeId() {
		return carePlanSummaryEpisodeId;
	}

	public void setCarePlanSummaryEpisodeId(Integer carePlanSummaryEpisodeId) {
		this.carePlanSummaryEpisodeId = carePlanSummaryEpisodeId;
	}

	public Integer getCarePlanSummaryGoalProgress() {
		return carePlanSummaryGoalProgress;
	}

	public void setCarePlanSummaryGoalProgress(Integer carePlanSummaryGoalProgress) {
		this.carePlanSummaryGoalProgress = carePlanSummaryGoalProgress;
	}

	public Integer getCarePlanSummaryOutcomeId() {
		return carePlanSummaryOutcomeId;
	}

	public void setCarePlanSummaryOutcomeId(Integer carePlanSummaryOutcomeId) {
		this.carePlanSummaryOutcomeId = carePlanSummaryOutcomeId;
	}
}