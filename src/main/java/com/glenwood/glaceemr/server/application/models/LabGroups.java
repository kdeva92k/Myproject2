package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "lab_groups")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabGroups {
	
	@Id
	@Column(name="lab_groups_id")
	private Integer labGroupsId;

	@Column(name="lab_groups_isactive")
	private Boolean labGroupsIsactive;

	@Column(name="lab_groups_sort_by")
	private Integer labGroupsSortBy;

	@Column(name="lab_groups_desc")
	private String labGroupsDesc;

	@Column(name="lab_groups_isdefault", columnDefinition="Boolean default false")
	private Boolean labGroupsIsdefault;
	public Integer getLabGroupsId() {
		return labGroupsId;
	}

	public void setLabGroupsId(Integer labGroupsId) {
		this.labGroupsId = labGroupsId;
	}

	public Boolean getLabGroupsIsactive() {
		return labGroupsIsactive;
	}

	public void setLabGroupsIsactive(Boolean labGroupsIsactive) {
		this.labGroupsIsactive = labGroupsIsactive;
	}

	public Integer getLabGroupsSortBy() {
		return labGroupsSortBy;
	}

	public void setLabGroupsSortBy(Integer labGroupsSortBy) {
		this.labGroupsSortBy = labGroupsSortBy;
	}

	public String getLabGroupsDesc() {
		return labGroupsDesc;
	}

	public void setLabGroupsDesc(String labGroupsDesc) {
		this.labGroupsDesc = labGroupsDesc;
	}

	public Boolean getLabGroupsIsdefault() {
		return labGroupsIsdefault;
	}

	public void setLabGroupsIsdefault(Boolean labGroupsIsdefault) {
		this.labGroupsIsdefault = labGroupsIsdefault;
	}
}