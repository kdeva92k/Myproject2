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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name="careplan_outcome")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarePlanOutcome {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="careplan_outcome_id_seq")
	@SequenceGenerator(name="careplan_outcome_id_seq",sequenceName="careplan_outcome_id_seq",allocationSize=1)
	@Column(name="careplan_outcome_id")
	private Integer carePlanOutcomeId;
	
	@Column(name="careplan_outcome_patient_id")
	private Integer carePlanOutcomePatientId;
	
	@Column(name="careplan_outcome_encounter_id")
	private Integer carePlanOutcomeEncounterId;
	
	@Column(name="careplan_outcome_goal_id")
	private Integer carePlanOutcomeGoalId;
	
	@Column(name="careplan_outcome_provider_id")
	private Integer carePlanOutcomeProviderId;
	
	@Column(name="careplan_outcome_progress")
	private Integer carePlanOutcomeProgress;
	
	@Column(name="careplan_outcome_status_code")
	private String carePlanOutcomeCode;
	
	@Column(name="careplan_outcome_status_code_system")
	private String carePlanOutcomeCodeSystem;
	
	@Column(name="careplan_outcome_status_code_system_name")
	private String carePlanOutcomeCodeSystemName;
	
	@Column(name="careplan_outcome_status_code_description")
	private String carePlanOutcomeCodeDesc;
	
	@Column(name="careplan_outcome_notes")
	private String carePlanOutcomeNotes;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="careplan_outcome_review_date")
	private Timestamp carePlanOutcomeReviewDate;
	
	@Column(name="careplan_outcome_created_by")
	private Integer carePlanOutcomeCreatedBy;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="careplan_outcome_created_on")
	private Timestamp carePlanOutcomeCreatedOn;
	
	@Column(name="careplan_outcome_modified_by")
	private Integer carePlanOutcomeModifiedBy;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="careplan_outcome_modified_on")
	private Timestamp carePlanOutcomeModifiedOn;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "careplan_outcome_goal_id", referencedColumnName = "careplan_goal_id", insertable = false, updatable = false)
	private CarePlanGoal carePlanGoal;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="careplan_outcome_created_by", referencedColumnName="emp_profile_empid" , insertable=false, updatable=false)
	private EmployeeProfile empProfile;
	
	@Column(name="careplan_outcome_targeted_goal")
	private Boolean carePlanOutcomeTargetedGoal;
	
	
	public Integer getCarePlanOutcomeId() {
		return carePlanOutcomeId;
	}

	public void setCarePlanOutcomeId(Integer carePlanOutcomeId) {
		this.carePlanOutcomeId = carePlanOutcomeId;
	}

	public Integer getCarePlanOutcomePatientId() {
		return carePlanOutcomePatientId;
	}

	public void setCarePlanOutcomePatientId(Integer carePlanOutcomePatientId) {
		this.carePlanOutcomePatientId = carePlanOutcomePatientId;
	}

	public Integer getCarePlanOutcomeEncounterId() {
		return carePlanOutcomeEncounterId;
	}

	public void setCarePlanOutcomeEncounterId(Integer carePlanOutcomeEncounterId) {
		this.carePlanOutcomeEncounterId = carePlanOutcomeEncounterId;
	}

	public Integer getCarePlanOutcomeGoalId() {
		return carePlanOutcomeGoalId;
	}

	public void setCarePlanOutcomeGoalId(Integer carePlanOutcomeGoalId) {
		this.carePlanOutcomeGoalId = carePlanOutcomeGoalId;
	}

	public Integer getCarePlanOutcomeProviderId() {
		return carePlanOutcomeProviderId;
	}

	public void setCarePlanOutcomeProviderId(Integer carePlanOutcomeProviderId) {
		this.carePlanOutcomeProviderId = carePlanOutcomeProviderId;
	}

	public Integer getCarePlanOutcomeProgress() {
		return carePlanOutcomeProgress;
	}

	public void setCarePlanOutcomeProgress(Integer carePlanOutcomeProgress) {
		this.carePlanOutcomeProgress = carePlanOutcomeProgress;
	}
	
	public String getCarePlanOutcomeCode() {
		return carePlanOutcomeCode;
	}

	public void setCarePlanOutcomeCode(String carePlanOutcomeCode) {
		this.carePlanOutcomeCode = carePlanOutcomeCode;
	}

	public String getCarePlanOutcomeCodeSystem() {
		return carePlanOutcomeCodeSystem;
	}

	public void setCarePlanOutcomeCodeSystem(String carePlanOutcomeCodeSystem) {
		this.carePlanOutcomeCodeSystem = carePlanOutcomeCodeSystem;
	}

	public String getCarePlanOutcomeCodeSystemName() {
		return carePlanOutcomeCodeSystemName;
	}

	public void setCarePlanOutcomeCodeSystemName(
			String carePlanOutcomeCodeSystemName) {
		this.carePlanOutcomeCodeSystemName = carePlanOutcomeCodeSystemName;
	}

	public String getCarePlanOutcomeCodeDesc() {
		return carePlanOutcomeCodeDesc;
	}

	public void setCarePlanOutcomeCodeDesc(String carePlanOutcomeCodeDesc) {
		this.carePlanOutcomeCodeDesc = carePlanOutcomeCodeDesc;
	}

	public String getCarePlanOutcomeNotes() {
		return carePlanOutcomeNotes;
	}

	public void setCarePlanOutcomeNotes(String carePlanOutcomeNotes) {
		this.carePlanOutcomeNotes = carePlanOutcomeNotes;
	}

	public Integer getCarePlanOutcomeCreatedBy() {
		return carePlanOutcomeCreatedBy;
	}

	public void setCarePlanOutcomeCreatedBy(Integer carePlanOutcomereatedBy) {
		this.carePlanOutcomeCreatedBy = carePlanOutcomereatedBy;
	}

	public Timestamp getCarePlanOutcomeCreatedOn() {
		return carePlanOutcomeCreatedOn;
	}

	public void setCarePlanOutcomeCreatedOn(Timestamp carePlanOutcomeCreatedOn) {
		this.carePlanOutcomeCreatedOn = carePlanOutcomeCreatedOn;
	}

	public Integer getCarePlanOutcomeModifiedBy() {
		return carePlanOutcomeModifiedBy;
	}

	public void setCarePlanOutcomeModifiedBy(Integer carePlanOutcomeModifiedBy) {
		this.carePlanOutcomeModifiedBy = carePlanOutcomeModifiedBy;
	}

	public Timestamp getCarePlanOutcomeModifiedOn() {
		return carePlanOutcomeModifiedOn;
	}

	public void setCarePlanOutcomeModifiedOn(Timestamp carePlanOutcomeModifiedOn) {
		this.carePlanOutcomeModifiedOn = carePlanOutcomeModifiedOn;
	}

	public Timestamp getCarePlanOutcomeReviewDate() {
		return carePlanOutcomeReviewDate;
	}

	public void setCarePlanOutcomeReviewDate(Timestamp carePlanOutcomeReviewDate) {
		this.carePlanOutcomeReviewDate = carePlanOutcomeReviewDate;
	}

	public Boolean getCarePlanOutcomeTargetedGoal() {
		return carePlanOutcomeTargetedGoal;
	}

	public void setCarePlanOutcomeTargetedGoal(Boolean carePlanOutcomeTargetedGoal) {
		this.carePlanOutcomeTargetedGoal = carePlanOutcomeTargetedGoal;
	}
}