package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;


@Entity
@Table(name = "cpt_cost_plan")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CptCostPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cpt_cost_plan_cpt_cost_plan_id_seq")
	@SequenceGenerator(name ="cpt_cost_plan_cpt_cost_plan_id_seq", sequenceName="cpt_cost_plan_cpt_cost_plan_id_seq", allocationSize=1)
	@Column(name="cpt_cost_plan_id")
	private Integer cpt_cost_plan_id;

	@Column(name="cpt_cost_plan_costplan")
	private Integer cpt_cost_plan_costplan;

	@Column(name="cpt_cost_plan_cpt_id")
	private Integer cpt_cost_plan_cpt_id;

	@Column(name="cpt_cost_plan_cpt_cost")
	private Double cpt_cost_plan_cpt_cost;

	@Column(name="cpt_cost_plan_unknown1")
	private Double cpt_cost_plan_unknown1;

	public Integer getcpt_cost_plan_id() {
		return cpt_cost_plan_id;
	}

	public void setcpt_cost_plan_id(Integer cpt_cost_plan_id) {
		this.cpt_cost_plan_id = cpt_cost_plan_id;
	}

	public Integer getcpt_cost_plan_costplan() {
		return cpt_cost_plan_costplan;
	}

	public void setcpt_cost_plan_costplan(Integer cpt_cost_plan_costplan) {
		this.cpt_cost_plan_costplan = cpt_cost_plan_costplan;
	}

	public Integer getcpt_cost_plan_cpt_id() {
		return cpt_cost_plan_cpt_id;
	}

	public void setcpt_cost_plan_cpt_id(Integer cpt_cost_plan_cpt_id) {
		this.cpt_cost_plan_cpt_id = cpt_cost_plan_cpt_id;
	}

	public Double getcpt_cost_plan_cpt_cost() {
		return cpt_cost_plan_cpt_cost;
	}

	public void setcpt_cost_plan_cpt_cost(Double cpt_cost_plan_cpt_cost) {
		this.cpt_cost_plan_cpt_cost = cpt_cost_plan_cpt_cost;
	}

	public Double getcpt_cost_plan_unknown1() {
		return cpt_cost_plan_unknown1;
	}

	public void setcpt_cost_plan_unknown1(Double cpt_cost_plan_unknown1) {
		this.cpt_cost_plan_unknown1 = cpt_cost_plan_unknown1;
	}
}