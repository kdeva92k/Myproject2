package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "drug_schedule")
public class DrugSchedule implements Serializable{

	
	@Id
	@Column(name="drug_schedule_id")
	private Integer drugScheduleId;

	public Integer getDrugScheduleId() {
		return drugScheduleId;
	}

	public void setDrugScheduleId(Integer drugScheduleId) {
		this.drugScheduleId = drugScheduleId;
	}

	public Integer getDrugScheduleType() {
		return drugScheduleType;
	}

	public void setDrugScheduleType(Integer drugScheduleType) {
		this.drugScheduleType = drugScheduleType;
	}

	public String getDrugScheduleName() {
		return drugScheduleName;
	}

	public void setDrugScheduleName(String drugScheduleName) {
		this.drugScheduleName = drugScheduleName;
	}

	public String getDrugScheduleDesc() {
		return drugScheduleDesc;
	}

	public void setDrugScheduleDesc(String drugScheduleDesc) {
		this.drugScheduleDesc = drugScheduleDesc;
	}

	public Integer getDrugScheduleCyclesPerDay() {
		return drugScheduleCyclesPerDay;
	}

	public void setDrugScheduleCyclesPerDay(Integer drugScheduleCyclesPerDay) {
		this.drugScheduleCyclesPerDay = drugScheduleCyclesPerDay;
	}

	public List<Prescription> getPresc() {
		return presc;
	}

	public void setPresc(List<Prescription> presc) {
		this.presc = presc;
	}

	@Column(name="drug_schedule_type")
	private Integer drugScheduleType;

	@Column(name="drug_schedule_name")
	private String drugScheduleName;

	@Column(name="drug_schedule_desc")
	private String drugScheduleDesc;
	
	@Column(name="drug_schedule_cycles_per_day")
	private Integer drugScheduleCyclesPerDay;
	
	@OneToMany(mappedBy="drugSchedule")
	@JsonManagedReference
	List<Prescription> presc;
}