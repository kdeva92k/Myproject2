package com.glenwood.glaceemr.server.application.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "patient_encounter_type")
public class PatientEncounterType {
    
	@Column(name="groupid")
	private Short groupid;
    @Id
	@Column(name="categoryid")
	private Short categoryid;

	@Column(name="encounter_typename")
	private String encounterTypename;

	@Column(name="isactive")
	private Boolean isactive;

	@Column(name="plantype")
	private short planType;
	
	public short getPlanType() {
		return planType;
	}

	public void setPlanType(short planType) {
		this.planType = planType;
	}

	public Short getGroupid() {
		return groupid;
	}

	public Short getCategoryid() {
		return categoryid;
	}

	public String getEncounterTypename() {
		return encounterTypename;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setGroupid(Short groupid) {
		this.groupid = groupid;
	}

	public void setCategoryid(Short categoryid) {
		this.categoryid = categoryid;
	}

	public void setEncounterTypename(String encounterTypename) {
		this.encounterTypename = encounterTypename;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="plantype", referencedColumnName="emp_profile_empid", insertable=false, updatable=false)
	EmployeeProfile empProfileAllgInactiveByTable;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="plantype", referencedColumnName="rate_plan_id", insertable=false, updatable=false)
	RatePlan ratePlan;
}