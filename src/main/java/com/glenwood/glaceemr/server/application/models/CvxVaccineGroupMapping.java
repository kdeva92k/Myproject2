package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@SuppressWarnings("serial")
@Entity
@Table(name = "cvx_vaccine_group_mapping")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CvxVaccineGroupMapping implements Serializable{

	@Id
	@Column(name="cvx_vaccine_group_mapping_id")
	private Integer cvxVaccineGroupMappingId;

	@Column(name="cvx_vaccine_group_mapping_short_description")
	private String cvxVaccineGroupMappingShortDescription;

	@Column(name="cvx_vaccine_group_mapping_cvx_code")
	private String cvxVaccineGroupMappingCvxCode;

	@Column(name="cvx_vaccine_group_mapping_vaccine_status")
	private String cvxVaccineGroupMappingVaccineStatus;

	@Column(name="cvx_vaccine_group_mapping_vaccine_group_code")
	private String cvxVaccineGroupMappingVaccineGroupCode;

	@Column(name="cvx_vaccine_group_mapping_uncertain_formulation_cvx")
	private String cvxVaccineGroupMappingUncertainFormulationCvx;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="cvx_vaccine_group_mapping_uncertain_formulation_cvx", referencedColumnName="vaccine_group_cvx", updatable=false, insertable=false)
	VaccineGroupMapping vaccineGroupMapping;

	public Integer getCvxVaccineGroupMappingId() {
		return cvxVaccineGroupMappingId;
	}

	public void setCvxVaccineGroupMappingId(Integer cvxVaccineGroupMappingId) {
		this.cvxVaccineGroupMappingId = cvxVaccineGroupMappingId;
	}

	public String getCvxVaccineGroupMappingShortDescription() {
		return cvxVaccineGroupMappingShortDescription;
	}

	public void setCvxVaccineGroupMappingShortDescription(
			String cvxVaccineGroupMappingShortDescription) {
		this.cvxVaccineGroupMappingShortDescription = cvxVaccineGroupMappingShortDescription;
	}

	public String getCvxVaccineGroupMappingCvxCode() {
		return cvxVaccineGroupMappingCvxCode;
	}

	public void setCvxVaccineGroupMappingCvxCode(
			String cvxVaccineGroupMappingCvxCode) {
		this.cvxVaccineGroupMappingCvxCode = cvxVaccineGroupMappingCvxCode;
	}

	public String getCvxVaccineGroupMappingVaccineStatus() {
		return cvxVaccineGroupMappingVaccineStatus;
	}

	public void setCvxVaccineGroupMappingVaccineStatus(
			String cvxVaccineGroupMappingVaccineStatus) {
		this.cvxVaccineGroupMappingVaccineStatus = cvxVaccineGroupMappingVaccineStatus;
	}

	public String getCvxVaccineGroupMappingVaccineGroupCode() {
		return cvxVaccineGroupMappingVaccineGroupCode;
	}

	public void setCvxVaccineGroupMappingVaccineGroupCode(
			String cvxVaccineGroupMappingVaccineGroupCode) {
		this.cvxVaccineGroupMappingVaccineGroupCode = cvxVaccineGroupMappingVaccineGroupCode;
	}

	public String getCvxVaccineGroupMappingUncertainFormulationCvx() {
		return cvxVaccineGroupMappingUncertainFormulationCvx;
	}

	public void setCvxVaccineGroupMappingUncertainFormulationCvx(
			String cvxVaccineGroupMappingUncertainFormulationCvx) {
		this.cvxVaccineGroupMappingUncertainFormulationCvx = cvxVaccineGroupMappingUncertainFormulationCvx;
	}

	public VaccineGroupMapping getVaccineGroupMapping() {
		return vaccineGroupMapping;
	}

	public void setVaccineGroupMapping(VaccineGroupMapping vaccineGroupMapping) {
		this.vaccineGroupMapping = vaccineGroupMapping;
	}
	
}