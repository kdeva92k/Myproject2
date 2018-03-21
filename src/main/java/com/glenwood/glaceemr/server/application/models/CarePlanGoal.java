package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name="careplan_goal")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarePlanGoal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="careplan_goal_id_seq")
	@SequenceGenerator(name ="careplan_goal_id_seq", sequenceName="careplan_goal_id_seq", allocationSize=1)
	@Column(name="careplan_goal_id")
	private Integer carePlanGoalId;
	
	@Column(name="careplan_goal_patient_id")
	private Integer carePlanGoalPatientId;
	
	@Column(name="careplan_goal_encounter_id")
	private Integer carePlanGoalEncounterId;
	
	@Column(name="careplan_goal_parent_id")
	private Integer carePlanGoalParentId;
	
	@Column(name="careplan_goal_concern_id")
	private Integer carePlanGoalConcernId;
	
	@Column(name="careplan_goal_priority")
	private Integer carePlanGoalPriority;
	
	@Column(name="careplan_goal_from")
	private Integer carePlanGoalFrom;
	
	@Column(name="careplan_goal_term")
	private Integer carePlanGoalTerm;
	
	@Column(name="careplan_goal_type")
	private Integer carePlanGoalType;
	
	@Column(name="careplan_goal_provider_id")
	private Integer carePlanGoalProviderId;
	
	@Column(name="careplan_goal_description")
	private String carePlanGoalDesc;
	
	@Column(name="careplan_goal_code")
	private String carePlanGoalCode;
	
	@Column(name="careplan_goal_code_system")
	private String carePlanGoalCodeSystem;
	
	@Column(name="careplan_goal_code_system_name")
	private String carePlanGoalCodeSystemName;
	
	@Column(name="careplan_goal_code_description")
	private String carePlanGoalCodeDescription;
	
	@Column(name="careplan_goal_code_operator")
	private String carePlanGoalCodeOperator;

	@Column(name="careplan_goal_value")
	private String carePlanGoalValue;

	@Column(name="careplan_goal_unit")
	private String carePlanGoalUnit;

	@Column(name="careplan_goal_status")
	private Integer carePlanGoalStatus;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="careplan_goal_target_date")
	private Timestamp carePlanGoalTargetDate;
  
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="careplan_goal_next_review_date")
	private Timestamp carePlanGoalNextReviewDate;
	
	@Column(name="careplan_goal_notes")
	private String carePlanGoalNotes;


	@Column(name="careplan_goal_created_by")
	private Integer carePlanGoalCreatedBy;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="careplan_goal_created_on")
	private Timestamp carePlanGoalCreatedOn;
	
	@Column(name="careplan_goal_modified_by")
	private Integer carePlanGoalModifiedBy;
	
	@Column(name="careplan_goal_mastered_date")		
	private Timestamp careplanGoalMasteredDate;		
			
	@Column(name="careplan_goal_order")		
	private Integer carePlanGoalOrder;		
			
	@Column(name="careplan_goal_value_one")		
	private String carePlanGoalValueOne;		

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="careplan_goal_modified_on")
	private Timestamp carePlanGoalModifiedOn;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "careplan_goal_concern_id", referencedColumnName = "careplan_concern_id", insertable = false, updatable = false)
	private CarePlanConcern carePlanConcern;
	
	@Column(name="careplan_goal_episodeId")
	private Integer careplanGoalEpisodeId;
	
	@OneToMany(mappedBy="carePlanGoal")
	List<CarePlanOutcome> carePlanOutcome;
	
	@OneToMany(mappedBy="carePlanRecommGoal")		
	List<CarePlanRecommendedIntervention> carePlanRecommIntervention;

	@Column(name="careplan_goal_result_status")
	private Integer carePlanGoalResultStatus;
	
	@Column(name="careplan_goal_assistance_status")
	private Integer carePlanGoalAssistanceStatus;
	
	@Column(name="careplan_goal_level_status")
	private Integer carePlanGoalLevelStatus;
	
	
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

	public Integer getCarePlanGoalParentId() {
		return carePlanGoalParentId;
	}

	public void setCarePlanGoalParentId(Integer carePlanGoalParentId) {
		this.carePlanGoalParentId = carePlanGoalParentId;
	}

	public Integer getCarePlanGoalConcernId() {
		return carePlanGoalConcernId;
	}

	public void setCarePlanGoalConcernId(Integer carePlanGoalConcernId) {
		this.carePlanGoalConcernId = carePlanGoalConcernId;
	}

	public Integer getCarePlanGoalPriority() {
		return carePlanGoalPriority;
	}

	public void setCarePlanGoalPriority(Integer carePlanGoalPriority) {
		this.carePlanGoalPriority = carePlanGoalPriority;
	}

	public Integer getCarePlanGoalFrom() {
		return carePlanGoalFrom;
	}

	public void setCarePlanGoalFrom(Integer carePlanGoalFrom) {
		this.carePlanGoalFrom = carePlanGoalFrom;
	}

	public Integer getCarePlanGoalTerm() {
		return carePlanGoalTerm;
	}

	public void setCarePlanGoalTerm(Integer carePlanGoalTerm) {
		this.carePlanGoalTerm = carePlanGoalTerm;
	}

	public Integer getCarePlanGoalType() {
		return carePlanGoalType;
	}

	public void setCarePlanGoalType(Integer carePlanGoalType) {
		this.carePlanGoalType = carePlanGoalType;
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

	public String getCarePlanGoalCodeDescription() {
		return carePlanGoalCodeDescription;
	}

	public void setCarePlanGoalCodeDescription(String carePlanGoalCodeDescription) {
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

	public Timestamp getCarePlanGoalTargetDate() {
		return carePlanGoalTargetDate;
	}

	public void setCarePlanGoalTargetDate(Timestamp carePlanGoalTargetDate) {
		this.carePlanGoalTargetDate = carePlanGoalTargetDate;
	}

	public Timestamp getCarePlanGoalNextReviewDate() {
		return carePlanGoalNextReviewDate;
	}

	public void setCarePlanGoalNextReviewDate(Timestamp carePlanGoalNextReviewDate) {
		this.carePlanGoalNextReviewDate = carePlanGoalNextReviewDate;
	}

	public String getCarePlanGoalNotes() {
		return carePlanGoalNotes;
	}

	public void setCarePlanGoalNotes(String carePlanGoalNotes) {
		this.carePlanGoalNotes = carePlanGoalNotes;
	}

	public Integer getCarePlanGoalCreatedBy() {
		return carePlanGoalCreatedBy;
	}

	public void setCarePlanGoalCreatedBy(Integer carePlanGoalCreatedBy) {
		this.carePlanGoalCreatedBy = carePlanGoalCreatedBy;
	}

	public Timestamp getCarePlanGoalCreatedOn() {
		return carePlanGoalCreatedOn;
	}

	public void setCarePlanGoalCreatedOn(Timestamp carePlanGoalCreatedOn) {
		this.carePlanGoalCreatedOn = carePlanGoalCreatedOn;
	}

	public Integer getCarePlanGoalModifiedBy() {
		return carePlanGoalModifiedBy;
	}

	public void setCarePlanGoalModifiedBy(Integer carePlanGoalModifiedBy) {
		this.carePlanGoalModifiedBy = carePlanGoalModifiedBy;
	}

	public Timestamp getCarePlanGoalModifiedOn() {
		return carePlanGoalModifiedOn;
	}

	public void setCarePlanGoalModifiedOn(Timestamp carePlanGoalModifiedOn) {
		this.carePlanGoalModifiedOn = carePlanGoalModifiedOn;
	}
	
	public Integer getCareplanGoalEpisodeId() {
		return careplanGoalEpisodeId;
	}

	public void setCareplanGoalEpisodeId(Integer careplanGoalEpisodeId) {
		this.careplanGoalEpisodeId = careplanGoalEpisodeId;
	}

	public Integer getCareplanGoalResultStatus() {
		return carePlanGoalResultStatus;
	}

	public void setCareplanGoalResultStatus(Integer carePlanGoalResultStatus) {
		this.carePlanGoalResultStatus = carePlanGoalResultStatus;
	}
	public Integer getCarePlanGoalOrder() {		
		return carePlanGoalOrder;		
	}		
		
	public void setCarePlanGoalOrder(Integer carePlanGoalOrder) {		
		this.carePlanGoalOrder = carePlanGoalOrder;		
	}		
		
	public Timestamp getCareplanGoalMasteredDate() {		
		return careplanGoalMasteredDate;		
	}		
		
	public void setCareplanGoalMasteredDate(Timestamp careplanGoalMasteredDate) {		
		this.careplanGoalMasteredDate = careplanGoalMasteredDate;		
	}		
		
	public String getCarePlanGoalValueOne() {		
		return carePlanGoalValueOne;		
	}		
		
	public void setCarePlanGoalValueOne(String carePlanGoalValueOne) {		
		this.carePlanGoalValueOne = carePlanGoalValueOne;		
	}

	public Integer getCarePlanGoalAssistanceStatus() {
		return carePlanGoalAssistanceStatus;
	}

	public void setCarePlanGoalAssistanceStatus(Integer carePlanGoalAssistanceStatus) {
		this.carePlanGoalAssistanceStatus = carePlanGoalAssistanceStatus;
	}

	public Integer getCarePlanGoalLevelStatus() {
		return carePlanGoalLevelStatus;
	}

	public void setCarePlanGoalLevelStatus(Integer carePlanGoalLevelStatus) {
		this.carePlanGoalLevelStatus = carePlanGoalLevelStatus;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "careplan_goal_created_by", referencedColumnName = "emp_profile_empid", insertable = false, updatable = false)
	private EmployeeProfile empProfileGoalCreatedBy;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "careplan_goal_modified_by", referencedColumnName = "emp_profile_empid", insertable = false, updatable = false)
	private EmployeeProfile empProfileGoalModifiedBy;
}