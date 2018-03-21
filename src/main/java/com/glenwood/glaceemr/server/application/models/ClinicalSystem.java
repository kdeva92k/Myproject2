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
@Table(name = "clinical_system")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClinicalSystem implements Serializable {

	@Id
	@Column(name="clinical_system_id")
	private Integer clinicalSystemId;

	@Column(name="clinical_system_name")
	private String clinicalSystemName;

	@Column(name="clinical_system_hpi_gwid")
	private String clinicalSystemHpiGwid;

	@Column(name="clinical_system_ros_gwid")
	private Integer clinicalSystemRosGwid;

	@Column(name="clinical_system_ros_eandmtype")
	private String clinicalSystemRosEandmtype;

	@Column(name="clinical_system_ros_deferred_gwid")
	private String clinicalSystemRosDeferredGwid;

	@Column(name="clinical_system_hx_gwid")
	private Integer clinicalSystemHxGwid;

	@Column(name="clinical_system_pmh_gwid")
	private String clinicalSystemPmhGwid;

	@Column(name="clinical_system_fmh_gwid")
	private String clinicalSystemFmhGwid;

	@Column(name="clinical_system_surgical_gwid")
	private String clinicalSystemSurgicalGwid;

	@Column(name="clinical_system_pe_gwid")
	private Integer clinicalSystemPeGwid;

	@Column(name="clinical_system_pe_eandmtype")
	private String clinicalSystemPeEandmtype;

	@Column(name="clinical_system_pe_deferred_gwid")
	private String clinicalSystemPeDeferredGwid;

	@Column(name="clinical_system_pe_chaperone_gwid")
	private String clinicalSystemPeChaperoneGwid;

	@Column(name="clinical_system_isactive")
	private Boolean clinicalSystemIsactive;

	@Column(name="clinical_system_orderby")
	private Integer clinicalSystemOrderby;
	
	@OneToMany(mappedBy="clinicalSystem",fetch=FetchType.LAZY)
	List<ClinicalSystemOrder> clinicalSystemOrders;	
	
	
	@OneToMany(mappedBy="clinicalSystem",fetch=FetchType.LAZY)
	List<PeElementGroup> peElementGroups;	
	
	
	public List<PeElementGroup> getPeElementGroups() {
		return peElementGroups;
	}

	public void setPeElementGroups(List<PeElementGroup> peElementGroups) {
		this.peElementGroups = peElementGroups;
	}

	public Integer getClinicalSystemId() {
		return clinicalSystemId;
	}

	public void setClinicalSystemId(Integer clinicalSystemId) {
		this.clinicalSystemId = clinicalSystemId;
	}

	public String getClinicalSystemName() {
		return clinicalSystemName;
	}

	public void setClinicalSystemName(String clinicalSystemName) {
		this.clinicalSystemName = clinicalSystemName;
	}

	public String getClinicalSystemHpiGwid() {
		return clinicalSystemHpiGwid;
	}

	public void setClinicalSystemHpiGwid(String clinicalSystemHpiGwid) {
		this.clinicalSystemHpiGwid = clinicalSystemHpiGwid;
	}

	public Integer getClinicalSystemRosGwid() {
		return clinicalSystemRosGwid;
	}

	public void setClinicalSystemRosGwid(Integer clinicalSystemRosGwid) {
		this.clinicalSystemRosGwid = clinicalSystemRosGwid;
	}

	public String getClinicalSystemRosEandmtype() {
		return clinicalSystemRosEandmtype;
	}

	public void setClinicalSystemRosEandmtype(String clinicalSystemRosEandmtype) {
		this.clinicalSystemRosEandmtype = clinicalSystemRosEandmtype;
	}

	public String getClinicalSystemRosDeferredGwid() {
		return clinicalSystemRosDeferredGwid;
	}

	public void setClinicalSystemRosDeferredGwid(
			String clinicalSystemRosDeferredGwid) {
		this.clinicalSystemRosDeferredGwid = clinicalSystemRosDeferredGwid;
	}

	public Integer getClinicalSystemHxGwid() {
		return clinicalSystemHxGwid;
	}

	public void setClinicalSystemHxGwid(Integer clinicalSystemHxGwid) {
		this.clinicalSystemHxGwid = clinicalSystemHxGwid;
	}

	public String getClinicalSystemPmhGwid() {
		return clinicalSystemPmhGwid;
	}

	public void setClinicalSystemPmhGwid(String clinicalSystemPmhGwid) {
		this.clinicalSystemPmhGwid = clinicalSystemPmhGwid;
	}

	public String getClinicalSystemFmhGwid() {
		return clinicalSystemFmhGwid;
	}

	public void setClinicalSystemFmhGwid(String clinicalSystemFmhGwid) {
		this.clinicalSystemFmhGwid = clinicalSystemFmhGwid;
	}

	public String getClinicalSystemSurgicalGwid() {
		return clinicalSystemSurgicalGwid;
	}

	public void setClinicalSystemSurgicalGwid(String clinicalSystemSurgicalGwid) {
		this.clinicalSystemSurgicalGwid = clinicalSystemSurgicalGwid;
	}

	public Integer getClinicalSystemPeGwid() {
		return clinicalSystemPeGwid;
	}

	public void setClinicalSystemPeGwid(Integer clinicalSystemPeGwid) {
		this.clinicalSystemPeGwid = clinicalSystemPeGwid;
	}

	public String getClinicalSystemPeEandmtype() {
		return clinicalSystemPeEandmtype;
	}

	public void setClinicalSystemPeEandmtype(String clinicalSystemPeEandmtype) {
		this.clinicalSystemPeEandmtype = clinicalSystemPeEandmtype;
	}

	public String getClinicalSystemPeDeferredGwid() {
		return clinicalSystemPeDeferredGwid;
	}

	public void setClinicalSystemPeDeferredGwid(String clinicalSystemPeDeferredGwid) {
		this.clinicalSystemPeDeferredGwid = clinicalSystemPeDeferredGwid;
	}

	public String getClinicalSystemPeChaperoneGwid() {
		return clinicalSystemPeChaperoneGwid;
	}

	public void setClinicalSystemPeChaperoneGwid(
			String clinicalSystemPeChaperoneGwid) {
		this.clinicalSystemPeChaperoneGwid = clinicalSystemPeChaperoneGwid;
	}

	public Boolean getClinicalSystemIsactive() {
		return clinicalSystemIsactive;
	}

	public void setClinicalSystemIsactive(Boolean clinicalSystemIsactive) {
		this.clinicalSystemIsactive = clinicalSystemIsactive;
	}

	public Integer getClinicalSystemOrderby() {
		return clinicalSystemOrderby;
	}

	public void setClinicalSystemOrderby(Integer clinicalSystemOrderby) {
		this.clinicalSystemOrderby = clinicalSystemOrderby;
	}
	
	
}