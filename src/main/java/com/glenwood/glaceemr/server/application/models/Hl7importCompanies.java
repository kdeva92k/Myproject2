package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "hl7import_companies")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hl7importCompanies {

	@Id
	@Column(name="id")
	private Integer id;

	@Column(name="labcompanyname")
	private String labcompanyname;

	@Column(name="defaultlab")
	private Boolean defaultlab;

	@Column(name="ishubresult")
	private Boolean ishubresult;

	@Column(name="isproduction")
	private Boolean isproduction;

	@Column(name="type")
	private Integer type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabcompanyname() {
		return labcompanyname;
	}

	public void setLabcompanyname(String labcompanyname) {
		this.labcompanyname = labcompanyname;
	}

	public Boolean getDefaultlab() {
		return defaultlab;
	}

	public void setDefaultlab(Boolean defaultlab) {
		this.defaultlab = defaultlab;
	}

	public Boolean getIshubresult() {
		return ishubresult;
	}

	public void setIshubresult(Boolean ishubresult) {
		this.ishubresult = ishubresult;
	}

	public Boolean getIsproduction() {
		return isproduction;
	}

	public void setIsproduction(Boolean isproduction) {
		this.isproduction = isproduction;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}