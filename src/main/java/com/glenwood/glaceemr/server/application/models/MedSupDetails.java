package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "med_sup_details")
public class MedSupDetails {

	@Id
	@Column(name="med_sup_details_id")
	private Integer medSupDetailsId;

	@Column(name="inner_pkg_size")
	private String innerPkgSize;

	@Column(name="inner_pkg_desc")
	private String innerPkgDesc;

	@Column(name="outer_pkg_size")
	private String outerPkgSize;

	@Column(name="source_desc")
	private String sourceDesc;

	@Column(name="category_group")
	private String categoryGroup;

	@Column(name="category_name")
	private String categoryName;

	public Integer getMedSupDetailsId() {
		return medSupDetailsId;
	}

	public void setMedSupDetailsId(Integer medSupDetailsId) {
		this.medSupDetailsId = medSupDetailsId;
	}

	public String getInnerPkgSize() {
		return innerPkgSize;
	}

	public void setInnerPkgSize(String innerPkgSize) {
		this.innerPkgSize = innerPkgSize;
	}

	public String getInnerPkgDesc() {
		return innerPkgDesc;
	}

	public void setInnerPkgDesc(String innerPkgDesc) {
		this.innerPkgDesc = innerPkgDesc;
	}

	public String getOuterPkgSize() {
		return outerPkgSize;
	}

	public void setOuterPkgSize(String outerPkgSize) {
		this.outerPkgSize = outerPkgSize;
	}

	public String getSourceDesc() {
		return sourceDesc;
	}

	public void setSourceDesc(String sourceDesc) {
		this.sourceDesc = sourceDesc;
	}

	public String getCategoryGroup() {
		return categoryGroup;
	}

	public void setCategoryGroup(String categoryGroup) {
		this.categoryGroup = categoryGroup;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}