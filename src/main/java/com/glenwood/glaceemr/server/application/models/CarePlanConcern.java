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
@Table(name="careplan_concern")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarePlanConcern {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="careplan_concern_id_seq")
	@SequenceGenerator(name ="careplan_concern_id_seq", sequenceName="careplan_concern_id_seq", allocationSize=1)
	@Column(name="careplan_concern_id")
	private Integer carePlanConcernId;
	
	@Column(name="careplan_concern_patient_id")
	private Integer carePlanConcernPatientId;
	
	@Column(name="careplan_concern_category_id")
	private Integer carePlanConcernCategoryId;
	
	@Column(name="careplan_concern_provider_id")
	private Integer carePlanConcernProviderId;
	
	@Column(name="careplan_concern_priority")
	private Integer carePlanConcernPriority;
	
	@Column(name="careplan_concern_type")
	private Integer carePlanConcernType;
	
	@Column(name="careplan_concern_code")
	private String carePlanConcernCode;
	
	@Column(name="careplan_concern_code_system")
	private String carePlanConcernCodeSystem;
	
	@Column(name="careplan_concern_code_system_name")
	private String carePlanConcernCodeSystemName;
	
	@Column(name="careplan_concern_code_description")
	private String carePlanConcernCodeDesc;
	
	@Column(name="careplan_concern_value")
	private String carePlanConcernValue;
	
	@Column(name="careplan_concern_unit")
	private String carePlanConcernUnit;
	
	@Column(name="careplan_concern_description")
	private String carePlanConcernDesc;
	
	@Column(name="careplan_concern_notes")
	private String carePlanConcernNotes;
	
	@Column(name="careplan_concern_status")
	private Integer carePlanConcernStatus;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="careplan_concern_status_updated_date")
	private Timestamp carePlanConcernStatusUpdatedDate;
	
	@Column(name="careplan_concern_created_by")
	private Integer carePlanConcernCreatedBy;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="careplan_concern_created_on")
	private Timestamp carePlanConcernCreatedOn;
	
	@Column(name="careplan_concern_modified_by")
	private Integer carePlanConcernModifiedBy;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="careplan_concern_modified_on")
	private Timestamp carePlanConcernModifiedOn;
	
	@Column(name="careplan_concern_from")
	private Integer carePlanConcernFrom;
	
	@Column(name="careplan_concern_episodeId")
	private Integer careplanConcernEpisodeId;
	
	@Column(name="careplan_concern_encounterId")		
	private Integer careplanConcernEncounterId;
	
	@OneToMany(mappedBy="carePlanConcern")
	List<CarePlanGoal> carePlanGoal;
	
	@OneToMany(mappedBy="carePlanRecommConcern")		
	List<CarePlanRecommendedIntervention> carePlanRecommIntervention;
	
	public Integer getCarePlanConcernId() {
		return carePlanConcernId;
	}

	public void setCarePlanConcernId(Integer carePlanConcernId) {
		this.carePlanConcernId = carePlanConcernId;
	}

	public Integer getCarePlanConcernPatientId() {
		return carePlanConcernPatientId;
	}

	public void setCarePlanConcernPatientId(Integer carePlanConcernPatientId) {
		this.carePlanConcernPatientId = carePlanConcernPatientId;
	}

	public Integer getCarePlanConcernCategoryId() {
		return carePlanConcernCategoryId;
	}

	public void setCarePlanConcernCategoryId(Integer carePlanConcernCategoryId) {
		this.carePlanConcernCategoryId = carePlanConcernCategoryId;
	}

	public Integer getCarePlanConcernProviderId() {
		return carePlanConcernProviderId;
	}

	public void setCarePlanConcernProviderId(Integer carePlanConcernProviderId) {
		this.carePlanConcernProviderId = carePlanConcernProviderId;
	}

	public Integer getCarePlanConcernPriority() {
		return carePlanConcernPriority;
	}

	public void setCarePlanConcernPriority(Integer carePlanConcernPriority) {
		this.carePlanConcernPriority = carePlanConcernPriority;
	}

	public Integer getCarePlanConcernType() {
		return carePlanConcernType;
	}

	public void setCarePlanConcernType(Integer carePlanConcernType) {
		this.carePlanConcernType = carePlanConcernType;
	}

	public String getCarePlanConcernCode() {
		return carePlanConcernCode;
	}

	public void setCarePlanConcernCode(String carePlanConcernCode) {
		this.carePlanConcernCode = carePlanConcernCode;
	}

	public String getCarePlanConcernCodeSystem() {
		return carePlanConcernCodeSystem;
	}

	public void setCarePlanConcernCodeSystem(String carePlanConcernCodeSystem) {
		this.carePlanConcernCodeSystem = carePlanConcernCodeSystem;
	}

	public String getCarePlanConcernCodeSystemName() {
		return carePlanConcernCodeSystemName;
	}

	public void setCarePlanConcernCodeSystemName(
			String carePlanConcernCodeSystemName) {
		this.carePlanConcernCodeSystemName = carePlanConcernCodeSystemName;
	}

	public String getCarePlanConcernCodeDesc() {
		return carePlanConcernCodeDesc;
	}

	public void setCarePlanConcernCodeDesc(String carePlanConcernCodeDesc) {
		this.carePlanConcernCodeDesc = carePlanConcernCodeDesc;
	}

	public String getCarePlanConcernValue() {
		return carePlanConcernValue;
	}

	public void setCarePlanConcernValue(String carePlanConcernValue) {
		this.carePlanConcernValue = carePlanConcernValue;
	}

	public String getCarePlanConcernUnit() {
		return carePlanConcernUnit;
	}

	public void setCarePlanConcernUnit(String carePlanConcernUnit) {
		this.carePlanConcernUnit = carePlanConcernUnit;
	}

	public String getCarePlanConcernDesc() {
		return carePlanConcernDesc;
	}

	public void setCarePlanConcernDesc(String carePlanConcernDesc) {
		this.carePlanConcernDesc = carePlanConcernDesc;
	}

	public String getCarePlanConcernNotes() {
		return carePlanConcernNotes;
	}

	public void setCarePlanConcernNotes(String carePlanConcernNotes) {
		this.carePlanConcernNotes = carePlanConcernNotes;
	}

	public Integer getCarePlanConcernStatus() {
		return carePlanConcernStatus;
	}

	public void setCarePlanConcernStatus(Integer carePlanConcernStatus) {
		this.carePlanConcernStatus = carePlanConcernStatus;
	}

	public Timestamp getCarePlanConcernStatusUpdatedDate() {
		return carePlanConcernStatusUpdatedDate;
	}

	public void setCarePlanConcernStatusUpdatedDate(
			Timestamp carePlanConcernStatusUpdatedDate) {
		this.carePlanConcernStatusUpdatedDate = carePlanConcernStatusUpdatedDate;
	}

	public Integer getCarePlanConcernCreatedBy() {
		return carePlanConcernCreatedBy;
	}

	public void setCarePlanConcernCreatedBy(Integer carePlanConcernCreatedBy) {
		this.carePlanConcernCreatedBy = carePlanConcernCreatedBy;
	}

	public Timestamp getCarePlanConcernCreatedOn() {
		return carePlanConcernCreatedOn;
	}

	public void setCarePlanConcernCreatedOn(Timestamp carePlanConcernCreatedOn) {
		this.carePlanConcernCreatedOn = carePlanConcernCreatedOn;
	}

	public Integer getCarePlanConcernModifiedBy() {
		return carePlanConcernModifiedBy;
	}

	public void setCarePlanConcernModifiedBy(Integer carePlanConcernModifiedBy) {
		this.carePlanConcernModifiedBy = carePlanConcernModifiedBy;
	}

	public Timestamp getCarePlanConcernModifiedOn() {
		return carePlanConcernModifiedOn;
	}

	public void setCarePlanConcernModifiedOn(Timestamp carePlanConcernModifiedOn) {
		this.carePlanConcernModifiedOn = carePlanConcernModifiedOn;
	}

	public Integer getCareplanConcernEpisodeId() {
		return careplanConcernEpisodeId;
	}

	public void setCareplanConcernEpisodeId(Integer careplanConcernEpisodeId) {
		this.careplanConcernEpisodeId = careplanConcernEpisodeId;
	}		
	
	public Integer getCareplanConcernEncounterId() {		
		return careplanConcernEncounterId;		
	}		
		
	public void setCareplanConcernEncounterId(Integer careplanConcernEncounterId) {		
		this.careplanConcernEncounterId = careplanConcernEncounterId;
	}

	public Integer getCarePlanConcernFrom() {
		return carePlanConcernFrom;
	}

	public void setCarePlanConcernFrom(Integer carePlanConcernFrom) {
		this.carePlanConcernFrom = carePlanConcernFrom;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "careplan_concern_created_by", referencedColumnName = "emp_profile_empid", insertable = false, updatable = false)
	private EmployeeProfile empProfileConcernCreatedBy;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "careplan_concern_modified_by", referencedColumnName = "emp_profile_empid", insertable = false, updatable = false)
	private EmployeeProfile empProfileConcernModifiedBy;

	public EmployeeProfile getEmpProfileConcernCreatedBy() {
		return empProfileConcernCreatedBy;
	}

	public void setEmpProfileConcernCreatedBy(
			EmployeeProfile empProfileConcernCreatedBy) {
		this.empProfileConcernCreatedBy = empProfileConcernCreatedBy;
	}

	public EmployeeProfile getEmpProfileConcernModifiedBy() {
		return empProfileConcernModifiedBy;
	}

	public void setEmpProfileConcernModifiedBy(
			EmployeeProfile empProfileConcernModifiedBy) {
		this.empProfileConcernModifiedBy = empProfileConcernModifiedBy;
	}
}