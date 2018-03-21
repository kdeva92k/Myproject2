package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "clinical_system_order")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClinicalSystemOrder {

	@Id
	@Column(name="clinical_system_order_id")
	private Integer clinicalSystemOrderId;

	@Column(name="clinical_system_order_systemid")
	private Integer clinicalSystemOrderSystemid;

	@Column(name="clinical_system_order_hpi")
	private Integer clinicalSystemOrderHpi;

	@Column(name="clinical_system_order_ros")
	private Integer clinicalSystemOrderRos;

	@Column(name="clinical_system_order_pe")
	private Integer clinicalSystemOrderPe;

	@Column(name="clinical_system_order_hx")
	private Integer clinicalSystemOrderHx;

	@Column(name="clinical_system_order_templateid")
	private Integer clinicalSystemOrderTemplateid;

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JsonManagedReference
	@JoinColumn(name = "clinical_system_order_systemid", referencedColumnName = "clinical_system_id", insertable = false, updatable = false)
	private ClinicalSystem clinicalSystem;
	
	
	
	public ClinicalSystem getClinicalSystem() {
		return clinicalSystem;
	}

	public void setClinicalSystem(ClinicalSystem clinicalSystem) {
		this.clinicalSystem = clinicalSystem;
	}

	public Integer getClinicalSystemOrderId() {
		return clinicalSystemOrderId;
	}

	public void setClinicalSystemOrderId(Integer clinicalSystemOrderId) {
		this.clinicalSystemOrderId = clinicalSystemOrderId;
	}

	public Integer getClinicalSystemOrderSystemid() {
		return clinicalSystemOrderSystemid;
	}

	public void setClinicalSystemOrderSystemid(Integer clinicalSystemOrderSystemid) {
		this.clinicalSystemOrderSystemid = clinicalSystemOrderSystemid;
	}

	public Integer getClinicalSystemOrderHpi() {
		return clinicalSystemOrderHpi;
	}

	public void setClinicalSystemOrderHpi(Integer clinicalSystemOrderHpi) {
		this.clinicalSystemOrderHpi = clinicalSystemOrderHpi;
	}

	public Integer getClinicalSystemOrderRos() {
		return clinicalSystemOrderRos;
	}

	public void setClinicalSystemOrderRos(Integer clinicalSystemOrderRos) {
		this.clinicalSystemOrderRos = clinicalSystemOrderRos;
	}

	public Integer getClinicalSystemOrderPe() {
		return clinicalSystemOrderPe;
	}

	public void setClinicalSystemOrderPe(Integer clinicalSystemOrderPe) {
		this.clinicalSystemOrderPe = clinicalSystemOrderPe;
	}

	public Integer getClinicalSystemOrderHx() {
		return clinicalSystemOrderHx;
	}

	public void setClinicalSystemOrderHx(Integer clinicalSystemOrderHx) {
		this.clinicalSystemOrderHx = clinicalSystemOrderHx;
	}

	public Integer getClinicalSystemOrderTemplateid() {
		return clinicalSystemOrderTemplateid;
	}

	public void setClinicalSystemOrderTemplateid(
			Integer clinicalSystemOrderTemplateid) {
		this.clinicalSystemOrderTemplateid = clinicalSystemOrderTemplateid;
	}
	
	
}