package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "encounter_plan")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EncounterPlan {

	@Id
	@Column(name="encounterid")
	private Integer encounterid;

	@Column(name="plantext")
	private String plantext;

	public Integer getEncounterid() {
		return encounterid;
	}

	public void setEncounterid(Integer encounterid) {
		this.encounterid = encounterid;
	}

	public String getPlantext() {
		return plantext;
	}

	public void setPlantext(String plantext) {
		this.plantext = plantext;
	}
	
	
}