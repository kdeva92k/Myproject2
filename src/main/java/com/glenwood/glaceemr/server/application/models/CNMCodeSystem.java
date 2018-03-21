package com.glenwood.glaceemr.server.application.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "cnm_code_system")
public class CNMCodeSystem {
	@Id
	@SequenceGenerator(name = "cnm_code_system_cnm_code_system_id_seq", sequenceName = "cnm_code_system_cnm_code_system_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "cnm_code_system_cnm_code_system_id_seq")
	
	@Column(name="cnm_code_system_id")
	private Integer cnmCodeSystemId;

	@Column(name="cnm_code_system_gwid")
	private String cnmCodeSystemGwid;

	@Column(name="cnm_code_system_code")
	private String cnmCodeSystemCode;

	@Column(name="cnm_code_system_oid")
	private String cnmCodeSystemOid;

	@Column(name="cnm_code_system_isactive")
	private Boolean cnmCodeSystemIsactive;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cnm_code_system_gwid", referencedColumnName = "clinical_elements_gwid", insertable = false, updatable = false)
	private  ClinicalElements clinicalElements;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "cnm_code_system_gwid", referencedColumnName = "vitals_parameter_gw_id", insertable = false, updatable = false)
	private VitalsParameter cnmCodeSystem;
	
	public ClinicalElements getClinicalElements() {
		return clinicalElements;
	}

	public void setClinicalElements(ClinicalElements clinicalElements) {
		this.clinicalElements = clinicalElements;
	}

	public Integer getCnmCodeSystemId() {
		return cnmCodeSystemId;
	}

	public void setCnmCodeSystemId(Integer cnmCodeSystemId) {
		this.cnmCodeSystemId = cnmCodeSystemId;
	}

	public String getCnmCodeSystemGwid() {
		return cnmCodeSystemGwid;
	}

	public void setCnmCodeSystemGwid(String cnmCodeSystemGwid) {
		this.cnmCodeSystemGwid = cnmCodeSystemGwid;
	}

	public String getCnmCodeSystemCode() {
		return cnmCodeSystemCode;
	}

	public void setCnmCodeSystemCode(String cnmCodeSystemCode) {
		this.cnmCodeSystemCode = cnmCodeSystemCode;
	}

	public String getCnmCodeSystemOid() {
		return cnmCodeSystemOid;
	}

	public void setCnmCodeSystemOid(String cnmCodeSystemOid) {
		this.cnmCodeSystemOid = cnmCodeSystemOid;
	}

	public Boolean getCnmCodeSystemIsactive() {
		return cnmCodeSystemIsactive;
	}

	public void setCnmCodeSystemIsactive(Boolean cnmCodeSystemIsactive) {
		this.cnmCodeSystemIsactive = cnmCodeSystemIsactive;
	}
	
	
}