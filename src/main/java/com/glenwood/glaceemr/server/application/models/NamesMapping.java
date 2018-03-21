package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="names_mapping")
public class NamesMapping {
	@Id
	@Column(name="names_mapping_plan_id")
	private Integer namesMappingPlanId;

	@Column(name="names_mapping_plan_name")
	private String namesMappingPlanName;

	@Column(name="names_mapping_plan_code")
	private String namesMappingPlanCode;

	@Column(name="names_mapping_plan_type")
	private Integer namesMappingPlanType;

	public Integer getNamesMappingPlanId() {
		return namesMappingPlanId;
	}

	public void setNamesMappingPlanId(Integer namesMappingPlanId) {
		this.namesMappingPlanId = namesMappingPlanId;
	}

	public String getNamesMappingPlanName() {
		return namesMappingPlanName;
	}

	public void setNamesMappingPlanName(String namesMappingPlanName) {
		this.namesMappingPlanName = namesMappingPlanName;
	}

	public String getNamesMappingPlanCode() {
		return namesMappingPlanCode;
	}

	public void setNamesMappingPlanCode(String namesMappingPlanCode) {
		this.namesMappingPlanCode = namesMappingPlanCode;
	}

	public Integer getNamesMappingPlanType() {
		return namesMappingPlanType;
	}

	public void setNamesMappingPlanType(Integer namesMappingPlanType) {
		this.namesMappingPlanType = namesMappingPlanType;
	}   
}
