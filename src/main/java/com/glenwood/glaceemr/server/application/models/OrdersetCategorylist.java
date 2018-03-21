package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orderset_categorylist")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdersetCategorylist {

	@Id
	@Column(name="id")
	private Integer id;

	@Column(name="setid")
	private Integer setid;

	@Column(name="category_id")
	private Integer categoryId;

	@Column(name="associated_list")
	private String associatedList;

	@Column(name="category_type")
	private String categoryType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSetid() {
		return setid;
	}

	public void setSetid(Integer setid) {
		this.setid = setid;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getAssociatedList() {
		return associatedList;
	}

	public void setAssociatedList(String associatedList) {
		this.associatedList = associatedList;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
}