package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table( name="rate_plan")
public class RatePlan {
	@Id
	@Column(name="rate_plan_id")
	private Integer ratePlanId;
	
	@Column(name="rate_plan_plan_name")
	private String ratePlanPlanName;
	
	public String getRatePlanPlanName() {
		return ratePlanPlanName;
	}

	public void setRatePlanPlanName(String ratePlanPlanName) {
		this.ratePlanPlanName = ratePlanPlanName;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	PatientEncounterType patientEncounterType;;

	public Integer getRatePlanId() {
		return ratePlanId;
	}

	public void setRatePlanId(Integer ratePlanId) {
		this.ratePlanId = ratePlanId;
	}


	public PatientEncounterType getPatientEncounterType() {
		return patientEncounterType;
	}

	public void setPatientEncounterType(PatientEncounterType patientEncounterType) {
		this.patientEncounterType = patientEncounterType;
	}
    
}
