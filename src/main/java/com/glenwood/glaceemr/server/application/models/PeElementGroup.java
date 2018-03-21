package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pe_element_group")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeElementGroup {

	@Id
	@Column(name="pe_element_group_id")
	private Integer peElementGroupId;

	@Column(name="pe_element_group_name")
	private String peElementGroupName;

	@Column(name="pe_element_group_system_id")
	private Integer peElementGroupSystemId;

	@Column(name="pe_element_group_orderby")
	private Integer peElementGroupOrderby;

	@Column(name="pe_element_group_isactive")
	private Boolean peElementGroupIsactive;

	@Column(name="pe_element_group_gwid")
	private String peElementGroupGwid;

	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name = "pe_element_group_id", referencedColumnName = "pe_element_group_id", insertable = false, updatable = false)
	List<PeElement> peElements;
	
	//This is automatically fetched as there is no PK and FK constraints on both tables with reference to joined columns
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "pe_element_group_system_id", referencedColumnName = "clinical_system_pe_gwid", insertable = false, updatable = false)
	ClinicalSystem clinicalSystem;
	
	public Integer getPeElementGroupId() {
		return peElementGroupId;
	}

	public void setPeElementGroupId(Integer peElementGroupId) {
		this.peElementGroupId = peElementGroupId;
	}

	public String getPeElementGroupName() {
		return peElementGroupName;
	}

	public void setPeElementGroupName(String peElementGroupName) {
		this.peElementGroupName = peElementGroupName;
	}

	public Integer getPeElementGroupSystemId() {
		return peElementGroupSystemId;
	}

	public void setPeElementGroupSystemId(Integer peElementGroupSystemId) {
		this.peElementGroupSystemId = peElementGroupSystemId;
	}

	public Integer getPeElementGroupOrderby() {
		return peElementGroupOrderby;
	}

	public void setPeElementGroupOrderby(Integer peElementGroupOrderby) {
		this.peElementGroupOrderby = peElementGroupOrderby;
	}

	public Boolean getPeElementGroupIsactive() {
		return peElementGroupIsactive;
	}

	public void setPeElementGroupIsactive(Boolean peElementGroupIsactive) {
		this.peElementGroupIsactive = peElementGroupIsactive;
	}

	public String getPeElementGroupGwid() {
		return peElementGroupGwid;
	}

	public void setPeElementGroupGwid(String peElementGroupGwid) {
		this.peElementGroupGwid = peElementGroupGwid;
	}
	
	
}