package com.glenwood.glaceemr.server.application.models.print;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "patient_header_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientHeaderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="patient_header_details_id")
	private Integer patientHeaderDetailsId;

	public Integer getPatientHeaderDetailsId() {
		return patientHeaderDetailsId;
	}

	public void setPatientHeaderDetailsId(Integer patientHeaderDetailsId) {
		this.patientHeaderDetailsId = patientHeaderDetailsId;
	}

	@Column(name="patient_header_details_map_id")
	private Integer patientHeaderId;
	
	@Column(name="patient_header_details_page")
	private Integer pageId;
	
	@Column(name="patient_header_details_component_id")
	private Integer componentId;
	
	@Column(name="patient_header_details_order")
	private Integer componentOrder;

	public Integer getPatientHeaderId() {
		return patientHeaderId;
	}

	public void setPatientHeaderId(Integer patientHeaderId) {
		this.patientHeaderId = patientHeaderId;
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Integer getComponentId() {
		return componentId;
	}

	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}

	public Integer getComponentOrder() {
		return componentOrder;
	}

	public void setComponentOrder(Integer componentOrder) {
		this.componentOrder = componentOrder;
	}
	
	
	
}
