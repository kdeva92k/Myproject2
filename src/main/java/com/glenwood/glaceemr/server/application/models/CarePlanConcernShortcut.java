package com.glenwood.glaceemr.server.application.models;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.application.models.CarePlanGoalShortcut;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name="careplan_concern_shortcut")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarePlanConcernShortcut {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="careplan_concern_shortcut_id_seq")
	@SequenceGenerator(name ="careplan_concern_shortcut_id_seq", sequenceName="careplan_concern_shortcut_id_seq", allocationSize=1)
	@Column(name="careplan_concern_shortcut_id")
	private Integer carePlanConcernShortcutId;
	
	@Column(name="careplan_concern_shortcut_category_id")
	private Integer carePlanConcernShortcutCategoryId;
	
	@Column(name="careplan_concern_shortcut_priority")
	private Integer carePlanConcernShortcutPriority;
	
	@Column(name="careplan_concern_shortcut_type")
	private Integer carePlanConcernShortcutType;
	
	@Column(name="careplan_concern_shortcut_code")
	private String carePlanConcernShortcutCode;
	
	@Column(name="careplan_concern_shortcut_code_system")
	private String carePlanConcernShortcutCodeSystem;
	
	@Column(name="careplan_concern_shortcut_code_system_name")
	private String carePlanConcernShortcutCodeSystemName;
	
	@Column(name="careplan_concern_shortcut_code_description")
	private String carePlanConcernShortcutCodeDesc;
	
	@Column(name="careplan_concern_shortcut_value")
	private String carePlanConcernShortcutValue;
	
	@Column(name="careplan_concern_shortcut_unit")
	private String carePlanConcernShortcutUnit;
	
	@Column(name="careplan_concern_shortcut_description")
	private String carePlanConcernShortcutDesc;
	
	@Column(name="careplan_concern_shortcut_notes")
	private String carePlanConcernShortcutNotes;
	
	@Column(name="careplan_concern_shortcut_status")
	private Integer carePlanConcernShortcutStatus;

	@Column(name="careplan_concern_shortcut_created_by")
	private Integer carePlanConcernShortcutCreatedBy;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="careplan_concern_shortcut_created_on")
	private Timestamp carePlanConcernShortcutCreatedOn;
	
	@Column(name="careplan_concern_shortcut_modified_by")
	private Integer carePlanConcernShortcutModifiedBy;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="careplan_concern_shortcut_modified_on")
	private Timestamp carePlanConcernShortcutModifiedOn;

	@OneToMany(mappedBy="carePlanConcernShortcut")
	List<CarePlanGoalShortcut> carePlanGoalShortcut;
	
	
	public Integer getCarePlanConcernShortcutId() {
		return carePlanConcernShortcutId;
	}

	public void setCarePlanConcernShortcutId(Integer carePlanConcernShortcutId) {
		this.carePlanConcernShortcutId = carePlanConcernShortcutId;
	}

	public Integer getCarePlanConcernShortcutCategoryId() {
		return carePlanConcernShortcutCategoryId;
	}

	public void setCarePlanConcernShortcutCategoryId(
			Integer carePlanConcernShortcutCategoryId) {
		this.carePlanConcernShortcutCategoryId = carePlanConcernShortcutCategoryId;
	}

	public Integer getCarePlanConcernShortcutPriority() {
		return carePlanConcernShortcutPriority;
	}

	public void setCarePlanConcernShortcutPriority(
			Integer carePlanConcernShortcutPriority) {
		this.carePlanConcernShortcutPriority = carePlanConcernShortcutPriority;
	}

	public Integer getCarePlanConcernShortcutType() {
		return carePlanConcernShortcutType;
	}

	public void setcarePlanConcernShortcutType(Integer carePlanConcernShortcutType) {
		this.carePlanConcernShortcutType = carePlanConcernShortcutType;
	}

	public String getCarePlanConcernShortcutCode() {
		return carePlanConcernShortcutCode;
	}

	public void setcarePlanConcernShortcutCode(String carePlanConcernShortcutCode) {
		this.carePlanConcernShortcutCode = carePlanConcernShortcutCode;
	}

	public String getCarePlanConcernShortcutCodeSystem() {
		return carePlanConcernShortcutCodeSystem;
	}

	public void setcarePlanConcernShortcutCodeSystem(
			String carePlanConcernShortcutCodeSystem) {
		this.carePlanConcernShortcutCodeSystem = carePlanConcernShortcutCodeSystem;
	}

	public String getCarePlanConcernShortcutCodeSystemName() {
		return carePlanConcernShortcutCodeSystemName;
	}

	public void setcarePlanConcernShortcutCodeSystemName(
			String carePlanConcernShortcutCodeSystemName) {
		this.carePlanConcernShortcutCodeSystemName = carePlanConcernShortcutCodeSystemName;
	}

	public String getCarePlanConcernShortcutCodeDesc() {
		return carePlanConcernShortcutCodeDesc;
	}

	public void setcarePlanConcernShortcutCodeDesc(
			String carePlanConcernShortcutCodeDesc) {
		this.carePlanConcernShortcutCodeDesc = carePlanConcernShortcutCodeDesc;
	}

	public String getCarePlanConcernShortcutValue() {
		return carePlanConcernShortcutValue;
	}

	public void setcarePlanConcernShortcutValue(String carePlanConcernShortcutValue) {
		this.carePlanConcernShortcutValue = carePlanConcernShortcutValue;
	}

	public String getCarePlanConcernShortcutUnit() {
		return carePlanConcernShortcutUnit;
	}

	public void setcarePlanConcernShortcutUnit(String carePlanConcernShortcutUnit) {
		this.carePlanConcernShortcutUnit = carePlanConcernShortcutUnit;
	}

	public String getCarePlanConcernShortcutDesc() {
		return carePlanConcernShortcutDesc;
	}

	public void setcarePlanConcernShortcutDesc(String carePlanConcernShortcutDesc) {
		this.carePlanConcernShortcutDesc = carePlanConcernShortcutDesc;
	}

	public String getCarePlanConcernShortcutNotes() {
		return carePlanConcernShortcutNotes;
	}

	public void setcarePlanConcernShortcutNotes(String carePlanConcernShortcutNotes) {
		this.carePlanConcernShortcutNotes = carePlanConcernShortcutNotes;
	}

	public Integer getCarePlanConcernShortcutStatus() {
		return carePlanConcernShortcutStatus;
	}

	public void setcarePlanConcernShortcutStatus(
			Integer carePlanConcernShortcutStatus) {
		this.carePlanConcernShortcutStatus = carePlanConcernShortcutStatus;
	}

	public Integer getCarePlanConcernShortcutCreatedBy() {
		return carePlanConcernShortcutCreatedBy;
	}

	public void setcarePlanConcernShortcutCreatedBy(
			Integer carePlanConcernShortcutCreatedBy) {
		this.carePlanConcernShortcutCreatedBy = carePlanConcernShortcutCreatedBy;
	}

	public Timestamp getCarePlanConcernShortcutCreatedOn() {
		return carePlanConcernShortcutCreatedOn;
	}

	public void setcarePlanConcernShortcutCreatedOn(
			Timestamp carePlanConcernShortcutCreatedOn) {
		this.carePlanConcernShortcutCreatedOn = carePlanConcernShortcutCreatedOn;
	}

	public Integer getCarePlanConcernShortcutModifiedBy() {
		return carePlanConcernShortcutModifiedBy;
	}

	public void setcarePlanConcernShortcutModifiedBy(
			Integer carePlanConcernShortcutModifiedBy) {
		this.carePlanConcernShortcutModifiedBy = carePlanConcernShortcutModifiedBy;
	}

	public Timestamp getCarePlanConcernShortcutModifiedOn() {
		return carePlanConcernShortcutModifiedOn;
	}

	public void setcarePlanConcernShortcutModifiedOn(
			Timestamp carePlanConcernShortcutModifiedOn) {
		this.carePlanConcernShortcutModifiedOn = carePlanConcernShortcutModifiedOn;
	}

	
}
