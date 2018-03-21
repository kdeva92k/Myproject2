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
@Table(name = "careplan_recommended_intervention")
public class CarePlanRecommendedIntervention{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="careplan_recommended_intervention_id_seq")
	@SequenceGenerator(name ="careplan_recommended_intervention_id_seq", sequenceName="careplan_recommended_intervention_id_seq", allocationSize=1)
	
	@Column(name="careplan_recommended_intervention_id")
	private Integer carePlanRecommendedInterventionId;
	
	@Column(name="careplan_recommended_intervention_patient_id")
	private Integer carePlanRecommendedInterventionPatientId;
	
	@Column(name="careplan_recommended_intervention_encounter_id")
	private Integer carePlanRecommendedInterventionEncounterId;
	
	@Column(name="careplan_recommended_intervention_episode_id")
	private Integer carePlanRecommendedInterventionEpisodeId;
	
	@Column(name="careplan_recommended_intervention_concern_id")
	private Integer carePlanRecommendedInterventionConcernId;
	
	@Column(name="careplan_recommended_intervention_goal_id")
	private Integer carePlanRecommendedInterventionGoalId;
	
	@Column(name="careplan_recommended_intervention_category_id")
	private Integer carePlanRecommendedInterventionCategoryId;
	
	@Column(name="careplan_recommended_intervention_category_name")
	private String carePlanRecommendedInterventionCategoryName;
	
	@Column(name="careplan_recommended_intervention_description")
	private String carePlanRecommendedInterventionDescription;
	
	@Column(name="careplan_recommended_intervention_code")
	private String carePlanRecommendedInterventionCode;
	
	@Column(name="careplan_recommended_intervention_code_system")
	private String carePlanRecommendedInterventionCodeSystem;
	
	@Column(name="careplan_recommended_intervention_code_system_name")
	private String carePlanRecommendedInterventionCodeSystemName;
	
	@Column(name="careplan_recommended_intervention_code_name")
	private String carePlanRecommendedInterventionCodeName;
	
	@Column(name="careplan_recommended_intervention_problem_code")
	private String carePlanRecommendedInterventionProblemCode;
	
	@Column(name="careplan_recommended_intervention_problem_code_system")
	private String carePlanRecommendedInterventionProblemCodeSystem;
	
	@Column(name="careplan_recommended_intervention_problem_code_system_descripti")
	private String carePlanRecommendedInterventionProblemCodeSystemDescription;
	
	@Column(name="careplan_recommended_intervention_status")
	private Integer carePlanRecommendedInterventionStatus;
	
	@Column(name="careplan_recommended_intervention_ordered_by")
	private Integer carePlanRecommendedInterventionOrderedBy;
	
	@Column(name="careplan_recommended_intervention_ordered_on")
	private Timestamp carePlanRecommendedInterventionOrderedOn;
	
	@Column(name="careplan_recommended_intervention_performed_by")
	private Integer carePlanRecommendedInterventionPerformedBy;
	
	@Column(name="careplan_recommended_intervention_performed_on")
	private Timestamp carePlanRecommendedInterventionPerformedOn;
	
	@Column(name="careplan_recommended_intervention_notdone_type")
	private Integer carePlanRecommendedInterventionNotDoneType;
	
	@Column(name="careplan_recommended_intervention_notdone_description")
	private String carePlanRecommendedInterventionNotDoneDescription;
	
	@Column(name="careplan_recommended_intervention_notdone_code")
	private String carePlanRecommendedInterventionNotDoneCode;
	
	@Column(name="careplan_recommended_intervention_notdone_code_system")
	private String carePlanRecommendedInterventionNotDoneCodeSystem;
	
	@Column(name="careplan_recommended_intervention_notes")
	private String carePlanRecommendedInterventionNotes;
	
	@Column(name="careplan_recommended_intervention_created_by")
	private Integer carePlanRecommendedInterventionCreatedBy;
	
	@Column(name="careplan_recommended_intervention_created_on")
	private Timestamp carePlanRecommendedInterventionCreatedOn;
	
	@Column(name="careplan_recommended_intervention_modified_by")
	private Integer carePlanRecommendedInterventionModifiedBy;
	
	@Column(name="careplan_recommended_intervention_modified_on")
	private Timestamp carePlanRecommendedInterventionModifiedOn;
	
	@Column(name="careplan_recommended_intervention_recommended_by")
	private Integer carePlanRecommendedInterventionRecommendedBy;
	
	@Column(name="careplan_recommended_intervention_recommended_on")
	private Timestamp carePlanRecommendedInterventionRecommendedOn;
	
	@Column(name="careplan_recommended_intervention_responsible_party")
	private Integer careplanRecommendedInterventionResponsibleParty;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "careplan_recommended_intervention_concern_id", referencedColumnName = "careplan_concern_id", insertable = false, updatable = false)
	private CarePlanConcern carePlanRecommConcern;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "careplan_recommended_intervention_goal_id", referencedColumnName = "careplan_goal_id", insertable = false, updatable = false)
	private CarePlanGoal carePlanRecommGoal;
	
	public Integer getCarePlanRecommendedInterventionId() {
		return carePlanRecommendedInterventionId;
	}

	public void setCarePlanRecommendedInterventionId(
			Integer carePlanRecommendedInterventionId) {
		this.carePlanRecommendedInterventionId = carePlanRecommendedInterventionId;
	}

	public Integer getCarePlanRecommendedInterventionPatientId() {
		return carePlanRecommendedInterventionPatientId;
	}

	public void setCarePlanRecommendedInterventionPatientId(
			Integer carePlanRecommendedInterventionPatientId) {
		this.carePlanRecommendedInterventionPatientId = carePlanRecommendedInterventionPatientId;
	}

	public Integer getCarePlanRecommendedInterventionEncounterId() {
		return carePlanRecommendedInterventionEncounterId;
	}

	public void setCarePlanRecommendedInterventionEncounterId(
			Integer carePlanRecommendedInterventionEncounterId) {
		this.carePlanRecommendedInterventionEncounterId = carePlanRecommendedInterventionEncounterId;
	}

	public Integer getCarePlanRecommendedInterventionConcernId() {
		return carePlanRecommendedInterventionConcernId;
	}

	public void setCarePlanRecommendedInterventionConcernId(
			Integer carePlanRecommendedInterventionConcernId) {
		this.carePlanRecommendedInterventionConcernId = carePlanRecommendedInterventionConcernId;
	}

	public Integer getCarePlanRecommendedInterventionGoalId() {
		return carePlanRecommendedInterventionGoalId;
	}

	public void setCarePlanRecommendedInterventionGoalId(
			Integer carePlanRecommendedInterventionGoalId) {
		this.carePlanRecommendedInterventionGoalId = carePlanRecommendedInterventionGoalId;
	}

	public Integer getCarePlanRecommendedInterventionCategoryId() {
		return carePlanRecommendedInterventionCategoryId;
	}

	public void setCarePlanRecommendedInterventionCategoryId(
			Integer carePlanRecommendedInterventionCategoryId) {
		this.carePlanRecommendedInterventionCategoryId = carePlanRecommendedInterventionCategoryId;
	}

	public String getCarePlanRecommendedInterventionCategoryName() {
		return carePlanRecommendedInterventionCategoryName;
	}

	public void setCarePlanRecommendedInterventionCategoryName(
			String carePlanRecommendedInterventionCategoryName) {
		this.carePlanRecommendedInterventionCategoryName = carePlanRecommendedInterventionCategoryName;
	}

	public String getCarePlanRecommendedInterventionDescription() {
		return carePlanRecommendedInterventionDescription;
	}

	public void setCarePlanRecommendedInterventionDescription(
			String carePlanRecommendedInterventionDescription) {
		this.carePlanRecommendedInterventionDescription = carePlanRecommendedInterventionDescription;
	}

	public String getCarePlanRecommendedInterventionCode() {
		return carePlanRecommendedInterventionCode;
	}

	public void setCarePlanRecommendedInterventionCode(
			String carePlanRecommendedInterventionCode) {
		this.carePlanRecommendedInterventionCode = carePlanRecommendedInterventionCode;
	}

	public String getCarePlanRecommendedInterventionCodeSystem() {
		return carePlanRecommendedInterventionCodeSystem;
	}

	public void setCarePlanRecommendedInterventionCodeSystem(
			String carePlanRecommendedInterventionCodeSystem) {
		this.carePlanRecommendedInterventionCodeSystem = carePlanRecommendedInterventionCodeSystem;
	}

	public String getCarePlanRecommendedInterventionCodeSystemName() {
		return carePlanRecommendedInterventionCodeSystemName;
	}

	public void setCarePlanRecommendedInterventionCodeSystemName(
			String carePlanRecommendedInterventionCodeSystemName) {
		this.carePlanRecommendedInterventionCodeSystemName = carePlanRecommendedInterventionCodeSystemName;
	}

	public String getCarePlanRecommendedInterventionCodeName() {
		return carePlanRecommendedInterventionCodeName;
	}

	public void setCarePlanRecommendedInterventionCodeName(
			String carePlanRecommendedInterventionCodeName) {
		this.carePlanRecommendedInterventionCodeName = carePlanRecommendedInterventionCodeName;
	}

	public String getCarePlanRecommendedInterventionProblemCode() {
		return carePlanRecommendedInterventionProblemCode;
	}

	public void setCarePlanRecommendedInterventionProblemCode(
			String carePlanRecommendedInterventionProblemCode) {
		this.carePlanRecommendedInterventionProblemCode = carePlanRecommendedInterventionProblemCode;
	}

	public String getCarePlanRecommendedInterventionProblemCodeSystem() {
		return carePlanRecommendedInterventionProblemCodeSystem;
	}

	public void setCarePlanRecommendedInterventionProblemCodeSystem(
			String carePlanRecommendedInterventionProblemCodeSystem) {
		this.carePlanRecommendedInterventionProblemCodeSystem = carePlanRecommendedInterventionProblemCodeSystem;
	}

	public String getCarePlanRecommendedInterventionProblemCodeSystemDescription() {
		return carePlanRecommendedInterventionProblemCodeSystemDescription;
	}

	public void setCarePlanRecommendedInterventionProblemCodeSystemDescription(
			String carePlanRecommendedInterventionProblemCodeSystemDescription) {
		this.carePlanRecommendedInterventionProblemCodeSystemDescription = carePlanRecommendedInterventionProblemCodeSystemDescription;
	}

	public Integer getCarePlanRecommendedInterventionStatus() {
		return carePlanRecommendedInterventionStatus;
	}

	public void setCarePlanRecommendedInterventionStatus(
			Integer carePlanRecommendedInterventionStatus) {
		this.carePlanRecommendedInterventionStatus = carePlanRecommendedInterventionStatus;
	}

	public Integer getCarePlanRecommendedInterventionOrderedBy() {
		return carePlanRecommendedInterventionOrderedBy;
	}

	public void setCarePlanRecommendedInterventionOrderedBy(
			Integer carePlanRecommendedInterventionOrderedBy) {
		this.carePlanRecommendedInterventionOrderedBy = carePlanRecommendedInterventionOrderedBy;
	}

	public Timestamp getCarePlanRecommendedInterventionOrderedOn() {
		return carePlanRecommendedInterventionOrderedOn;
	}

	public void setCarePlanRecommendedInterventionOrderedOn(
			Timestamp carePlanRecommendedInterventionOrderedOn) {
		this.carePlanRecommendedInterventionOrderedOn = carePlanRecommendedInterventionOrderedOn;
	}

	public Integer getCarePlanRecommendedInterventionPerformedBy() {
		return carePlanRecommendedInterventionPerformedBy;
	}

	public void setCarePlanRecommendedInterventionPerformedBy(
			Integer carePlanRecommendedInterventionPerformedBy) {
		this.carePlanRecommendedInterventionPerformedBy = carePlanRecommendedInterventionPerformedBy;
	}

	public Timestamp getCarePlanRecommendedInterventionPerformedOn() {
		return carePlanRecommendedInterventionPerformedOn;
	}

	public void setCarePlanRecommendedInterventionPerformedOn(
			Timestamp carePlanRecommendedInterventionPerformedOn) {
		this.carePlanRecommendedInterventionPerformedOn = carePlanRecommendedInterventionPerformedOn;
	}

	public Integer getCarePlanRecommendedInterventionNotDoneType() {
		return carePlanRecommendedInterventionNotDoneType;
	}

	public void setCarePlanRecommendedInterventionNotDoneType(
			Integer carePlanRecommendedInterventionNotDoneType) {
		this.carePlanRecommendedInterventionNotDoneType = carePlanRecommendedInterventionNotDoneType;
	}

	public String getCarePlanRecommendedInterventionNotDoneDescription() {
		return carePlanRecommendedInterventionNotDoneDescription;
	}

	public void setCarePlanRecommendedInterventionNotDoneDescription(
			String carePlanRecommendedInterventionNotDoneDescription) {
		this.carePlanRecommendedInterventionNotDoneDescription = carePlanRecommendedInterventionNotDoneDescription;
	}

	public String getCarePlanRecommendedInterventionNotDoneCode() {
		return carePlanRecommendedInterventionNotDoneCode;
	}

	public void setCarePlanRecommendedInterventionNotDoneCode(
			String carePlanRecommendedInterventionNotDoneCode) {
		this.carePlanRecommendedInterventionNotDoneCode = carePlanRecommendedInterventionNotDoneCode;
	}

	public String getCarePlanRecommendedInterventionNotDoneCodeSystem() {
		return carePlanRecommendedInterventionNotDoneCodeSystem;
	}

	public void setCarePlanRecommendedInterventionNotDoneCodeSystem(
			String carePlanRecommendedInterventionNotDoneCodeSystem) {
		this.carePlanRecommendedInterventionNotDoneCodeSystem = carePlanRecommendedInterventionNotDoneCodeSystem;
	}

	public String getCarePlanRecommendedInterventionNotes() {
		return carePlanRecommendedInterventionNotes;
	}

	public void setCarePlanRecommendedInterventionNotes(
			String carePlanRecommendedInterventionNotes) {
		this.carePlanRecommendedInterventionNotes = carePlanRecommendedInterventionNotes;
	}

	public Integer getCarePlanRecommendedInterventionCreatedBy() {
		return carePlanRecommendedInterventionCreatedBy;
	}

	public void setCarePlanRecommendedInterventionCreatedBy(
			Integer carePlanRecommendedInterventionCreatedBy) {
		this.carePlanRecommendedInterventionCreatedBy = carePlanRecommendedInterventionCreatedBy;
	}

	public Timestamp getCarePlanRecommendedInterventionCreatedOn() {
		return carePlanRecommendedInterventionCreatedOn;
	}

	public void setCarePlanRecommendedInterventionCreatedOn(
			Timestamp carePlanRecommendedInterventionCreatedOn) {
		this.carePlanRecommendedInterventionCreatedOn = carePlanRecommendedInterventionCreatedOn;
	}

	public Integer getCarePlanRecommendedInterventionModifiedBy() {
		return carePlanRecommendedInterventionModifiedBy;
	}

	public void setCarePlanRecommendedInterventionModifiedBy(
			Integer carePlanRecommendedInterventionModifiedBy) {
		this.carePlanRecommendedInterventionModifiedBy = carePlanRecommendedInterventionModifiedBy;
	}

	public Timestamp getCarePlanRecommendedInterventionModifiedOn() {
		return carePlanRecommendedInterventionModifiedOn;
	}

	public void setCarePlanRecommendedInterventionModifiedOn(
			Timestamp carePlanRecommendedInterventionModifiedOn) {
		this.carePlanRecommendedInterventionModifiedOn = carePlanRecommendedInterventionModifiedOn;
	}

	public Integer getCarePlanRecommendedInterventionRecommendedBy() {
		return carePlanRecommendedInterventionRecommendedBy;
	}

	public void setCarePlanRecommendedInterventionRecommendedBy(
			Integer carePlanRecommendedInterventionRecommendedBy) {
		this.carePlanRecommendedInterventionRecommendedBy = carePlanRecommendedInterventionRecommendedBy;
	}

	public Timestamp getCarePlanRecommendedInterventionRecommendedOn() {
		return carePlanRecommendedInterventionRecommendedOn;
	}

	public void setCarePlanRecommendedInterventionRecommendedOn(
			Timestamp carePlanRecommendedInterventionRecommendedOn) {
		this.carePlanRecommendedInterventionRecommendedOn = carePlanRecommendedInterventionRecommendedOn;
	}

	public Integer getCarePlanRecommendedInterventionEpisodeId() {
		return carePlanRecommendedInterventionEpisodeId;
	}

	public void setCarePlanRecommendedInterventionEpisodeId(
			Integer carePlanRecommendedInterventionEpisodeId) {
		this.carePlanRecommendedInterventionEpisodeId = carePlanRecommendedInterventionEpisodeId;
	}

	public Integer getCareplanRecommendedInterventionResponsibleParty() {
		return careplanRecommendedInterventionResponsibleParty;
	}

	public void setCareplanRecommendedInterventionResponsibleParty(
			Integer careplanRecommendedInterventionResponsibleParty) {
		this.careplanRecommendedInterventionResponsibleParty = careplanRecommendedInterventionResponsibleParty;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "careplan_recommended_intervention_created_by", referencedColumnName = "emp_profile_empid", insertable = false, updatable = false)
	private EmployeeProfile empProfileRecommInterCreatedBy;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "careplan_recommended_intervention_modified_by", referencedColumnName = "emp_profile_empid", insertable = false, updatable = false)
	private EmployeeProfile empProfileRecommInterModifiedBy;

	public CarePlanConcern getCarePlanRecommConcern() {
		return carePlanRecommConcern;
	}

	public void setCarePlanRecommConcern(CarePlanConcern carePlanRecommConcern) {
		this.carePlanRecommConcern = carePlanRecommConcern;
	}

	public CarePlanGoal getCarePlanRecommGoal() {
		return carePlanRecommGoal;
	}

	public void setCarePlanRecommGoal(CarePlanGoal carePlanRecommGoal) {
		this.carePlanRecommGoal = carePlanRecommGoal;
	}

	public EmployeeProfile getEmpProfileRecommInterCreatedBy() {
		return empProfileRecommInterCreatedBy;
	}

	public void setEmpProfileRecommInterCreatedBy(
			EmployeeProfile empProfileRecommInterCreatedBy) {
		this.empProfileRecommInterCreatedBy = empProfileRecommInterCreatedBy;
	}

	public EmployeeProfile getEmpProfileRecommInterModifiedBy() {
		return empProfileRecommInterModifiedBy;
	}

	public void setEmpProfileRecommInterModifiedBy(
			EmployeeProfile empProfileRecommInterModifiedBy) {
		this.empProfileRecommInterModifiedBy = empProfileRecommInterModifiedBy;
	}
}