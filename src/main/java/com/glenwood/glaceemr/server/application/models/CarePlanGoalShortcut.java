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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name="careplan_goal_shortcut")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarePlanGoalShortcut {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="careplan_goal_shortcut_id_seq")
	@SequenceGenerator(name ="careplan_goal_shortcut_id_seq", sequenceName="careplan_goal_shortcut_id_seq", allocationSize=1)
	@Column(name="careplan_goal_shortcut_id")
	private Integer carePlanGoalShortcutId;
	
	@Column(name="careplan_goal_shortcut_concern_shortcut_id")
	private Integer carePlanGoalShortcutConcernShortcutId;
	
	@Column(name="careplan_goal_shortcut_priority")
	private Integer carePlanGoalShortcutPriority;
	
	@Column(name="careplan_goal_shortcut_goal_type")
	private Integer carePlanGoalShortcutGoalType;
	
	@Column(name="careplan_goal_shortcut_term")
	private Integer carePlanGoalShortcutTerm;
	
	@Column(name="careplan_goal_shortcut_type")
	private Integer carePlanGoalShortcutType;
	
	@Column(name="careplan_goal_shortcut_provider_id")
	private Integer carePlanGoalShortcutProviderId;
	
	@Column(name="careplan_goal_shortcut_description")
	private String carePlanGoalShortcutDesc;
	
	@Column(name="careplan_goal_shortcut_code")
	private String carePlanGoalShortcutCode;
	
	@Column(name="careplan_goal_shortcut_code_system")
	private String carePlanShortcutCodeSystem;
	
	@Column(name="careplan_goal_shortcut_code_system_name")
	private String carePlanGoalShortcutCodeSystemName;
	
	@Column(name="careplan_goal_shortcut_code_description")
	private String carePlanGoalShortcutCodeDescription;
	
	@Column(name="careplan_goal_shortcut_code_operator")
	private String carePlanGoalShortcutCodeOperator;

	@Column(name="careplan_goal_shortcut_value")
	private String carePlanGoalShortcutValue;

	@Column(name="careplan_goal_shortcut_unit")
	private String carePlanGoalShortcutUnit;

	@Column(name="careplan_goal_shortcut_status")
	private Integer carePlanGoalShortcutStatus;

	@Column(name="careplan_goal_shortcut_notes")
	private String carePlanGoalShortcutNotes;


	@Column(name="careplan_goal_shortcut_created_by")
	private Integer carePlanShortcutCreatedBy;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="careplan_goal_shortcut_created_on")
	private Timestamp carePlanGoalShortcutCreatedOn;
	
	@Column(name="careplan_goal_shortcut_modified_by")
	private Integer carePlanGoalShortcutModifiedBy;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="careplan_goal_shortcut_modified_on")
	private Timestamp carePlanGoalShortcutModifiedOn;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "careplan_goal_shortcut_concern_shortcut_id", referencedColumnName = "careplan_concern_shortcut_id", insertable = false, updatable = false)
	private CarePlanConcernShortcut carePlanConcernShortcut;
	
	
	public Integer getCarePlanGoalShortcutId() {
		return carePlanGoalShortcutId;
	}

	public void setCarePlanGoalShortcutId(Integer carePlanGoalShortcutId) {
		this.carePlanGoalShortcutId = carePlanGoalShortcutId;
	}

	public Integer getCarePlanGoalShortcutConcernShortcutId() {
		return carePlanGoalShortcutConcernShortcutId;
	}

	public void setCarePlanGoalShortcutConcernShortcutId(
			Integer carePlanGoalShortcutConcernShortcutId) {
		this.carePlanGoalShortcutConcernShortcutId = carePlanGoalShortcutConcernShortcutId;
	}

	public Integer getCarePlanGoalShortcutPriority() {
		return carePlanGoalShortcutPriority;
	}

	public void setCarePlanGoalShortcutPriority(Integer carePlanGoalShortcutPriority) {
		this.carePlanGoalShortcutPriority = carePlanGoalShortcutPriority;
	}

	public Integer getCarePlanGoalShortcutGoalType() {
		return carePlanGoalShortcutGoalType;
	}

	public void setCarePlanGoalShortcutGoalType(Integer carePlanGoalShortcutGoalType) {
		this.carePlanGoalShortcutGoalType = carePlanGoalShortcutGoalType;
	}

	public Integer getCarePlanGoalShortcutTerm() {
		return carePlanGoalShortcutTerm;
	}

	public void setCarePlanGoalShortcutTerm(Integer carePlanGoalShortcutTerm) {
		this.carePlanGoalShortcutTerm = carePlanGoalShortcutTerm;
	}

	public Integer getCarePlanGoalShortcutType() {
		return carePlanGoalShortcutType;
	}

	public void setCarePlanGoalShortcutType(Integer carePlanGoalShortcutType) {
		this.carePlanGoalShortcutType = carePlanGoalShortcutType;
	}

	public Integer getCarePlanGoalShortcutProviderId() {
		return carePlanGoalShortcutProviderId;
	}

	public void setCarePlanGoalShortcutProviderId(
			Integer carePlanGoalShortcutProviderId) {
		this.carePlanGoalShortcutProviderId = carePlanGoalShortcutProviderId;
	}

	public String getCarePlanGoalShortcutDesc() {
		return carePlanGoalShortcutDesc;
	}

	public void setCarePlanGoalShortcutDesc(String carePlanGoalShortcutDesc) {
		this.carePlanGoalShortcutDesc = carePlanGoalShortcutDesc;
	}

	public String getCarePlanGoalShortcutCode() {
		return carePlanGoalShortcutCode;
	}

	public void setCarePlanGoalShortcutCode(String carePlanGoalShortcutCode) {
		this.carePlanGoalShortcutCode = carePlanGoalShortcutCode;
	}

	public String getCarePlanShortcutCodeSystem() {
		return carePlanShortcutCodeSystem;
	}

	public void setCarePlanShortcutCodeSystem(String carePlanShortcutCodeSystem) {
		this.carePlanShortcutCodeSystem = carePlanShortcutCodeSystem;
	}

	public String getCarePlanGoalShortcutCodeSystemName() {
		return carePlanGoalShortcutCodeSystemName;
	}

	public void setCarePlanGoalShortcutCodeSystemName(
			String carePlanGoalShortcutCodeSystemName) {
		this.carePlanGoalShortcutCodeSystemName = carePlanGoalShortcutCodeSystemName;
	}

	public String getCarePlanGoalShortcutCodeDescription() {
		return carePlanGoalShortcutCodeDescription;
	}

	public void setCarePlanGoalShortcutCodeDescription(
			String carePlanGoalShortcutCodeDescription) {
		this.carePlanGoalShortcutCodeDescription = carePlanGoalShortcutCodeDescription;
	}

	public String getCarePlanGoalShortcutCodeOperator() {
		return carePlanGoalShortcutCodeOperator;
	}

	public void setCarePlanGoalShortcutCodeOperator(
			String carePlanGoalShortcutCodeOperator) {
		this.carePlanGoalShortcutCodeOperator = carePlanGoalShortcutCodeOperator;
	}

	public String getCarePlanGoalShortcutValue() {
		return carePlanGoalShortcutValue;
	}

	public void setCarePlanGoalShortcutValue(String carePlanGoalShortcutValue) {
		this.carePlanGoalShortcutValue = carePlanGoalShortcutValue;
	}

	public String getCarePlanGoalShortcutUnit() {
		return carePlanGoalShortcutUnit;
	}

	public void setCarePlanGoalShortcutUnit(String carePlanGoalShortcutUnit) {
		this.carePlanGoalShortcutUnit = carePlanGoalShortcutUnit;
	}

	public Integer getCarePlanGoalShortcutStatus() {
		return carePlanGoalShortcutStatus;
	}

	public void setCarePlanGoalShortcutStatus(Integer carePlanGoalShortcutStatus) {
		this.carePlanGoalShortcutStatus = carePlanGoalShortcutStatus;
	}

	public String getCarePlanGoalShortcutNotes() {
		return carePlanGoalShortcutNotes;
	}

	public void setCarePlanGoalShortcutNotes(String carePlanGoalShortcutNotes) {
		this.carePlanGoalShortcutNotes = carePlanGoalShortcutNotes;
	}

	public Integer getCarePlanShortcutCreatedBy() {
		return carePlanShortcutCreatedBy;
	}

	public void setCarePlanShortcutCreatedBy(Integer carePlanShortcutCreatedBy) {
		this.carePlanShortcutCreatedBy = carePlanShortcutCreatedBy;
	}

	public Timestamp getCarePlanGoalShortcutCreatedOn() {
		return carePlanGoalShortcutCreatedOn;
	}

	public void setCarePlanGoalShortcutCreatedOn(
			Timestamp carePlanGoalShortcutCreatedOn) {
		this.carePlanGoalShortcutCreatedOn = carePlanGoalShortcutCreatedOn;
	}

	public Integer getCarePlanGoalShortcutModifiedBy() {
		return carePlanGoalShortcutModifiedBy;
	}

	public void setCarePlanGoalShortcutModifiedBy(
			Integer carePlanGoalShortcutModifiedBy) {
		this.carePlanGoalShortcutModifiedBy = carePlanGoalShortcutModifiedBy;
	}

	public Timestamp getCarePlanGoalShortcutModifiedOn() {
		return carePlanGoalShortcutModifiedOn;
	}

	public void setCarePlanGoalShortcutModifiedOn(
			Timestamp carePlanGoalShortcutModifiedOn) {
		this.carePlanGoalShortcutModifiedOn = carePlanGoalShortcutModifiedOn;
	}
	
}

