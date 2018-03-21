package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "sch_resource_category")
public class SchedulerResourceCategory {

	@Id
	@Column(name="sch_resource_category_id")
	private Integer schResourceCategoryId;

	@Column(name="sch_resource_category_resource_name")
	private String schResourceCategoryResourceName;

	@Column(name="sch_resource_category_resource_id")
	private Integer schResourceCategoryResourceId;

	@Column(name="sch_resource_category_isactive")
	private Boolean schResourceCategoryIsactive;

	public Integer getSchResourceCategoryId() {
		return schResourceCategoryId;
	}

	public void setSchResourceCategoryId(Integer schResourceCategoryId) {
		this.schResourceCategoryId = schResourceCategoryId;
	}

	public String getSchResourceCategoryResourceName() {
		return schResourceCategoryResourceName;
	}

	public void setSchResourceCategoryResourceName(
			String schResourceCategoryResourceName) {
		this.schResourceCategoryResourceName = schResourceCategoryResourceName;
	}

	public Integer getSchResourceCategoryResourceId() {
		return schResourceCategoryResourceId;
	}

	public void setSchResourceCategoryResourceId(
			Integer schResourceCategoryResourceId) {
		this.schResourceCategoryResourceId = schResourceCategoryResourceId;
	}

	public Boolean getSchResourceCategoryIsactive() {
		return schResourceCategoryIsactive;
	}

	public void setSchResourceCategoryIsactive(Boolean schResourceCategoryIsactive) {
		this.schResourceCategoryIsactive = schResourceCategoryIsactive;
	}
}