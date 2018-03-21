package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orderset_list")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdersetList {

	@Id
	@Column(name="set_id")
	private Integer setId;

	@Column(name="set_name")
	private String setName;

	@Column(name="isactive")
	private Boolean isactive;

	@Column(name="set_type")
	private Integer setType;

	public Integer getSetId() {
		return setId;
	}

	public void setSetId(Integer setId) {
		this.setId = setId;
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public Integer getSetType() {
		return setType;
	}

	public void setSetType(Integer setType) {
		this.setType = setType;
	}	
}