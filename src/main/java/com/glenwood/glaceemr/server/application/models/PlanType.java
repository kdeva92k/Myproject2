package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "plan_type")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanType implements Serializable{

	@Id
	@Column(name="plan_type_id")
	private Integer planTypeId;

	@Column(name="plan_type_name")
	private String planTypeName;

	@Column(name="plan_type_gwid")
	private String planTypeGwid;
	
	@Column(name="plan_type_orderby")
	private String planTypeOrderby;
	
	@OneToMany(mappedBy="planType", fetch=FetchType.LAZY)
	@JsonBackReference
	List<PlanInstruction> planTypeInstructions;

	public Integer getPlanTypeId() {
		return planTypeId;
	}

	public void setPlanTypeId(Integer planTypeId) {
		this.planTypeId = planTypeId;
	}

	public String getPlanTypeName() {
		return planTypeName;
	}

	public void setPlanTypeName(String planTypeName) {
		this.planTypeName = planTypeName;
	}

	public String getPlanTypeGwid() {
		return planTypeGwid;
	}

	public void setPlanTypeGwid(String planTypeGwid) {
		this.planTypeGwid = planTypeGwid;
	}

	public List<PlanInstruction> getPlanTypeInstructions() {
		return planTypeInstructions;
	}

	public void setPlanTypeInstructions(List<PlanInstruction> planTypeInstructions) {
		this.planTypeInstructions = planTypeInstructions;
	}

	public String getPlanTypeOrderby() {
		return planTypeOrderby;
	}

	public void setPlanTypeOrderby(String planTypeOrderby) {
		this.planTypeOrderby = planTypeOrderby;
	}
	
	
}