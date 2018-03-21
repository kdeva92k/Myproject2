package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "pe_system")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeSystem implements Serializable{

	
	@Column(name="pe_system_id")
	private Integer peSystemId;

	@Column(name="pe_system_name")
	private String peSystemName;

	@Column(name="pe_system_eandmtype")
	private String peSystemEandmtype;

	@Column(name="pe_system_orderby")
	private Integer peSystemOrderby;

	@Column(name="pe_system_isactive")
	private Boolean peSystemIsactive;
	
	/*Made pe_system_deferred_gwid as @Id in PeSystem to avoid query on PE System table when queried clinicalelementOptions*/
	@Id
	@Column(name="pe_system_deferred_gwid")
	private String peSystemDeferredGwid;

	@OneToMany(mappedBy="peSystem",fetch=FetchType.LAZY)
	List<ClinicalElementsOptions>  clinicalElementsOptions;
	
	public Integer getPeSystemId() {
		return peSystemId;
	}

	public void setPeSystemId(Integer peSystemId) {
		this.peSystemId = peSystemId;
	}

	public String getPeSystemName() {
		return peSystemName;
	}

	public void setPeSystemName(String peSystemName) {
		this.peSystemName = peSystemName;
	}

	public String getPeSystemEandmtype() {
		return peSystemEandmtype;
	}

	public void setPeSystemEandmtype(String peSystemEandmtype) {
		this.peSystemEandmtype = peSystemEandmtype;
	}

	public Integer getPeSystemOrderby() {
		return peSystemOrderby;
	}

	public void setPeSystemOrderby(Integer peSystemOrderby) {
		this.peSystemOrderby = peSystemOrderby;
	}

	public Boolean getPeSystemIsactive() {
		return peSystemIsactive;
	}

	public void setPeSystemIsactive(Boolean peSystemIsactive) {
		this.peSystemIsactive = peSystemIsactive;
	}

	public String getPeSystemDeferredGwid() {
		return peSystemDeferredGwid;
	}

	public void setPeSystemDeferredGwid(String peSystemDeferredGwid) {
		this.peSystemDeferredGwid = peSystemDeferredGwid;
	}
	
	
}