package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vaccine_group_mapping")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VaccineGroupMapping  implements Serializable{

	
	@Id
	@Column(name="vaccine_group_mapping_id")
	private Integer vaccineGroupMappingId;

	@Column(name="vaccine_group")
	private String vaccineGroup;

	@Column(name="vaccine_group_cvx")
	private String vaccineGroupCvx;

	@Column(name="vaccine_group_sort_order")
	private Integer vaccineGroupSortOrder;

	@Column(name="vaccine_group_dosage_level_max")
	private Integer vaccineGroupDosageLevelMax;

	public Integer getVaccineGroupMappingId() {
		return vaccineGroupMappingId;
	}

	public void setVaccineGroupMappingId(Integer vaccineGroupMappingId) {
		this.vaccineGroupMappingId = vaccineGroupMappingId;
	}

	public String getVaccineGroup() {
		return vaccineGroup;
	}

	public void setVaccineGroup(String vaccineGroup) {
		this.vaccineGroup = vaccineGroup;
	}

	public String getVaccineGroupCvx() {
		return vaccineGroupCvx;
	}

	public void setVaccineGroupCvx(String vaccineGroupCvx) {
		this.vaccineGroupCvx = vaccineGroupCvx;
	}

	public Integer getVaccineGroupSortOrder() {
		return vaccineGroupSortOrder;
	}

	public void setVaccineGroupSortOrder(Integer vaccineGroupSortOrder) {
		this.vaccineGroupSortOrder = vaccineGroupSortOrder;
	}

	public Integer getVaccineGroupDosageLevelMax() {
		return vaccineGroupDosageLevelMax;
	}

	public void setVaccineGroupDosageLevelMax(Integer vaccineGroupDosageLevelMax) {
		this.vaccineGroupDosageLevelMax = vaccineGroupDosageLevelMax;
	}
	
}