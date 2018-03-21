package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "careplan_intervention")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarePlanIntervention{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="careplan_intervention_id_seq")
	@SequenceGenerator(name ="careplan_intervention_id_seq", sequenceName="careplan_intervention_id_seq", allocationSize=1)
	
	@Column(name="careplan_intervention_id")
	private Integer carePlanInterventionId;
	
	@Column(name="careplan_intervention_patient_id")
	private Integer carePlanInterventionPatientId;
	
	@Column(name="careplan_intervention_encounter_id")
	private Integer carePlanInterventionEncounterId;
	
	@Column(name="careplan_intervention_concern_id")
	private Integer carePlanInterventionConcernId;
	
	@Column(name="careplan_intervention_goal_id")
	private Integer carePlanInterventionGoalId;
	
	@Column(name="careplan_intervention_category_id")
	private Integer carePlanInterventionCategoryId;
	
	@Column(name="careplan_intervention_category_name")
	private String carePlanInterventionCategoryName;
	
	@Column(name="careplan_intervention_description")
	private String carePlanInterventionDescription;
	
	@Column(name="careplan_intervention_code")
	private String carePlanInterventionCode;
	
	@Column(name="careplan_intervention_code_system")
	private String carePlanInterventionCodeSystem;
	
	@Column(name="careplan_intervention_code_system_name")
	private String carePlanInterventionCodeSystemName;
	
	@Column(name="careplan_intervention_code_name")
	private String carePlanInterventionCodeName;
	
	@Column(name="careplan_intervention_problem_code")
	private String carePlanInterventionProblemCode;
	
	@Column(name="careplan_intervention_problem_code_system")
	private String carePlanInterventionProblemCodeSystem;
	
	@Column(name="careplan_intervention_problem_code_system_description")
	private String carePlanInterventionProblemCodeSystemDescription;
	
	@Column(name="careplan_intervention_status")
	private Integer carePlanInterventionStatus;
	
	@Column(name="careplan_intervention_ordered_by")
	private Integer carePlanInterventionOrderedBy;
	
	@Column(name="careplan_intervention_ordered_on")
	private Timestamp carePlanInterventionOrderedOn;
	
	@Column(name="careplan_intervention_performed_by")
	private Integer carePlanInterventionPerformedBy;
	
	@Column(name="careplan_intervention_performed_on")
	private Timestamp carePlanInterventionPerformedOn;
	
	@Column(name="careplan_intervention_notdone_type")
	private Integer carePlanInterventionNotDoneType;
	
	@Column(name="careplan_intervention_notdone_description")
	private String carePlanInterventionNotDoneDescription;
	
	@Column(name="careplan_intervention_notdone_code")
	private String carePlanInterventionNotDoneCode;
	
	@Column(name="careplan_intervention_notdone_code_system")
	private String carePlanInterventionNotDoneCodeSystem;
	
	@Column(name="careplan_intervention_notes")
	private String carePlanInterventionNotes;
	
	@Column(name="careplan_intervention_created_by")
	private Integer carePlanInterventionCreatedBy;
	
	@Column(name="careplan_intervention_created_on")
	private Timestamp carePlanInterventionCreatedOn;
	
	@Column(name="careplan_intervention_modified_by")
	private Integer carePlanInterventionModifiedBy;
	
	@Column(name="careplan_intervention_modified_on")
	private Timestamp carePlanInterventionModifiedOn;
	
	public Integer getCareplanInterventionId() {
		return carePlanInterventionId;
	}
	
	public void setCareplanInterventionId(Integer carePlanInterventionId) {
		this.carePlanInterventionId = carePlanInterventionId;
	}
	
	public Integer getCareplanInterventionPatientId() {
		return carePlanInterventionPatientId;
	}
	
	public void setCareplanInterventionPatientId(Integer carePlanInterventionPatientId) {
		this.carePlanInterventionPatientId = carePlanInterventionPatientId;
	}
	
	public Integer getCareplanInterventionEncounterId() {
		return carePlanInterventionEncounterId;
	}
	
	public void setCareplanInterventionEncounterId(Integer carePlanInterventionEncounterId) {
		this.carePlanInterventionEncounterId = carePlanInterventionEncounterId;
	}
	
	public Integer getCareplanInterventionConcernId() {
		return carePlanInterventionConcernId;
	}
	
	public void setCareplanInterventionConcernId(Integer carePlanInterventionConcernId) {
		this.carePlanInterventionConcernId = carePlanInterventionConcernId;
	}
	
	public Integer getCareplanInterventionGoalId() {
		return carePlanInterventionGoalId;
	}
	
	public void setCareplanInterventionGoalId(Integer carePlanInterventionGoalId) {
		this.carePlanInterventionGoalId = carePlanInterventionGoalId;
	}
	
	public Integer getCareplanInterventionCategoryId() {
		return carePlanInterventionCategoryId;
	}
	
	public void setCareplanInterventionCategoryId(Integer carePlanInterventionCategoryId) {
		this.carePlanInterventionCategoryId = carePlanInterventionCategoryId;
	}
	
	public String getCareplanInterventionCategoryName() {
		return carePlanInterventionCategoryName;
	}
	
	public void setCareplanInterventionCategoryName(String carePlanInterventionCategoryName) {
		this.carePlanInterventionCategoryName = carePlanInterventionCategoryName;
	}
	
	public String getCareplanInterventionDescription() {
		return carePlanInterventionDescription;
	}
	
	public void setCareplanInterventionDescription(String carePlanInterventionDescription) {
		this.carePlanInterventionDescription = carePlanInterventionDescription;
	}
	
	public String getCareplanInterventionCode() {
		return carePlanInterventionCode;
	}
	
	public void setCareplanInterventionCode(String carePlanInterventionCode) {
		this.carePlanInterventionCode = carePlanInterventionCode;
	}
	
	public String getCareplanInterventionCodeSystem() {
		return carePlanInterventionCodeSystem;
		
		
	}
	
	public void setCareplanInterventionCodeSystem(String carePlanInterventionCodeSystem) {
		this.carePlanInterventionCodeSystem = carePlanInterventionCodeSystem;
	}
	
	public String getCareplanInterventionCodeSystemName() {
		return carePlanInterventionCodeSystemName;
	}
	
	public void setCareplanInterventionCodeSystemName(String carePlanInterventionCodeSystemName) {
		this.carePlanInterventionCodeSystemName = carePlanInterventionCodeSystemName;
	}
	
	public String getCareplanInterventionCodeName() {
		return carePlanInterventionCodeName;
	}
	
	public void setCareplanInterventionCodeName(String carePlanInterventionCodeName) {
		this.carePlanInterventionCodeName = carePlanInterventionCodeName;
	}
	
	public String getCareplanInterventionProblemCode() {
		return carePlanInterventionProblemCode;
	}
	
	public void setCareplanInterventionProblemCode(String carePlanInterventionProblemCode) {
		this.carePlanInterventionProblemCode = carePlanInterventionProblemCode;
	}
	
	public String getCareplanInterventionProblemCodeSystem() {
		return carePlanInterventionProblemCodeSystem;
	}
	
	public void setCareplanInterventionProblemCodeSystem(String carePlanInterventionProblemCodeSystem) {
		this.carePlanInterventionProblemCodeSystem = carePlanInterventionProblemCodeSystem;
	}
	
	public String getCareplanInterventionProblemCodeSystemDescription() {
		return carePlanInterventionProblemCodeSystemDescription;
	}
	
	public void setCareplanInterventionProblemCodeSystemDescription(
			String carePlanInterventionProblemCodeSystemDescription) {
		this.carePlanInterventionProblemCodeSystemDescription = carePlanInterventionProblemCodeSystemDescription;
	}
	
	public Integer getCareplanInterventionStatus() {
		return carePlanInterventionStatus;
	}
	
	public void setCareplanInterventionStatus(Integer carePlanInterventionStatus) {
		this.carePlanInterventionStatus = carePlanInterventionStatus;
	}
	
	public Integer getCareplanInterventionOrderedBy() {
		return carePlanInterventionOrderedBy;
	}
	
	public void setCareplanInterventionOrderedBy(Integer carePlanInterventionOrderedBy) {
		this.carePlanInterventionOrderedBy = carePlanInterventionOrderedBy;
	}
	
	public Timestamp getCareplanInterventionOrderedOn() {
		return carePlanInterventionOrderedOn;
	}
	
	public void setCareplanInterventionOrderedOn(Timestamp carePlanInterventionOrderedOn) {
		this.carePlanInterventionOrderedOn = carePlanInterventionOrderedOn;
	}
	
	public Integer getCareplanInterventionPerformedBy() {
		return carePlanInterventionPerformedBy;
	}
	
	public void setCareplanInterventionPerformedBy(Integer carePlanInterventionPerformedBy) {
		this.carePlanInterventionPerformedBy = carePlanInterventionPerformedBy;
	}
	
	public Timestamp getCareplanInterventionPerformedOn() {
		return carePlanInterventionPerformedOn;
	}
	
	public void setCareplanInterventionPerformedOn(Timestamp carePlanInterventionPerformedOn) {
		this.carePlanInterventionPerformedOn = carePlanInterventionPerformedOn;
	}
	
	public Integer getCareplanInterventionNotDoneType() {
		return carePlanInterventionNotDoneType;
	}
	
	public void setCareplanInterventionNotDoneType(Integer carePlanInterventionNotDoneType) {
		this.carePlanInterventionNotDoneType = carePlanInterventionNotDoneType;
	}
	
	public String getCareplanInterventionNotDoneDescription() {
		return carePlanInterventionNotDoneDescription;
	}
	
	public void setCareplanInterventionNotDoneDescription(String carePlanInterventionNotDoneDescription) {
		this.carePlanInterventionNotDoneDescription = carePlanInterventionNotDoneDescription;
	}
	
	public String getCareplanInterventionNotDoneCode() {
		return carePlanInterventionNotDoneCode;
	}
	
	public void setCareplanInterventionNotDoneCode(String carePlanInterventionNotDoneCode) {
		this.carePlanInterventionNotDoneCode = carePlanInterventionNotDoneCode;
	}
	
	public String getCareplanInterventionNotDoneCodeSystem() {
		return carePlanInterventionNotDoneCodeSystem;
	}
	
	public void setCareplanInterventionNotDoneCodeSystem(String carePlanInterventionNotDoneCodeSystem) {
		this.carePlanInterventionNotDoneCodeSystem = carePlanInterventionNotDoneCodeSystem;
	}
	
	public String getCareplanInterventionNotes() {
		return carePlanInterventionNotes;
	}
	
	public void setCareplanInterventionNotes(String carePlanInterventionNotes) {
		this.carePlanInterventionNotes = carePlanInterventionNotes;
	}
	
	public Integer getCareplanInterventionCreatedBy() {
		return carePlanInterventionCreatedBy;
	}
	
	public void setCareplanInterventionCreatedBy(Integer carePlanInterventionCreatedBy) {
		this.carePlanInterventionCreatedBy = carePlanInterventionCreatedBy;
	}
	
	public Timestamp getCareplanInterventionCreatedOn() {
		return carePlanInterventionCreatedOn;
	}
	
	public void setCareplanInterventionCreatedOn(Timestamp carePlanInterventionCreatedOn) {
		this.carePlanInterventionCreatedOn = carePlanInterventionCreatedOn;
	}
	
	public Integer getCareplanInterventionModifiedBy() {
		return carePlanInterventionModifiedBy;
	}
	
	public void setCareplanInterventionModifiedBy(Integer carePlanInterventionModifiedBy) {
		this.carePlanInterventionModifiedBy = carePlanInterventionModifiedBy;
	}
	
	public Timestamp getCareplanInterventionModifiedOn() {
		return carePlanInterventionModifiedOn;
	}
	
	public void setCareplanInterventionModifiedOn(Timestamp carePlanInterventionModifiedOn) {
		this.carePlanInterventionModifiedOn = carePlanInterventionModifiedOn;
	}
}