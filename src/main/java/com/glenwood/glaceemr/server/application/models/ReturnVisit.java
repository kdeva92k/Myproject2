package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "return_visit")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReturnVisit {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "return_visit_return_visit_id_seq")
	@SequenceGenerator(name = "return_visit_return_visit_id_seq", sequenceName = "return_visit_return_visit_id_seq", allocationSize = 1)
	@Column(name="return_visit_id")
	private Integer returnVisitId;

	@Column(name="return_visit_encounter_id")
	private Integer returnVisitEncounterId;

	@Column(name="return_visit_patient_id")
	private Integer returnVisitPatientId;

	@Column(name="return_visit_plan_data")
	private String returnVisitPlanData;

	@Column(name="return_visit_value")
	private String returnVisitValue;

	@Column(name="return_visit_physicalvisit_value")
	private String returnVisitPhysicalvisitValue;

	public Integer getReturnVisitId() {
		return returnVisitId;
	}

	public void setReturnVisitId(Integer returnVisitId) {
		this.returnVisitId = returnVisitId;
	}

	public Integer getReturnVisitEncounterId() {
		return returnVisitEncounterId;
	}

	public void setReturnVisitEncounterId(Integer returnVisitEncounterId) {
		this.returnVisitEncounterId = returnVisitEncounterId;
	}

	public Integer getReturnVisitPatientId() {
		return returnVisitPatientId;
	}

	public void setReturnVisitPatientId(Integer returnVisitPatientId) {
		this.returnVisitPatientId = returnVisitPatientId;
	}

	public String getReturnVisitPlanData() {
		return returnVisitPlanData;
	}

	public void setReturnVisitPlanData(String returnVisitPlanData) {
		this.returnVisitPlanData = returnVisitPlanData;
	}

	public String getReturnVisitValue() {
		return returnVisitValue;
	}

	public void setReturnVisitValue(String returnVisitValue) {
		this.returnVisitValue = returnVisitValue;
	}

	public String getReturnVisitPhysicalvisitValue() {
		return returnVisitPhysicalvisitValue;
	}

	public void setReturnVisitPhysicalvisitValue(
			String returnVisitPhysicalvisitValue) {
		this.returnVisitPhysicalvisitValue = returnVisitPhysicalvisitValue;
	}
}