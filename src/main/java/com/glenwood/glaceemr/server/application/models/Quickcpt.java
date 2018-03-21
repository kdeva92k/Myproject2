package com.glenwood.glaceemr.server.application.models;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "quickcpt")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quickcpt {

    @Id
	@Column(name="cpt_code")
	private String cptCode;

	@Column(name="group_no")
	private Integer groupNo;

	@Column(name="group_name")
	private String groupName;

	@Column(name="cpt_desc")
	private String cptDesc;

	@OneToMany(mappedBy="quickCpt",fetch=FetchType.EAGER)
	@JsonManagedReference
	Set<Cpt> cpt;

	public Set<Cpt> getCpt() {
		return cpt;
	}

	public void setCpt(Set<Cpt> cpt) {
		this.cpt = cpt;
	}
	
	public String getCptDesc() {
		return cptDesc;
	}

	public void setCptDesc(String cptDesc) {
		this.cptDesc = cptDesc;
	}

	public Integer getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCptCode() {
		return cptCode;
	}

	public void setCptCode(String cptCode) {
		this.cptCode = cptCode;
	}
	
	
}