package com.glenwood.glaceemr.server.application.models.print;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "patient_header")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientHeader {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="patient_header_id")
	private Integer patientHeaderId;

	@Column(name="patient_header_name")
	private String patientHeaderName;

	@Column(name="patient_header_type")
	private Integer patientHeaderType;

	@Column(name="patient_header_isdefault")
	private Boolean patientHeaderIsDefault;
	
	@Column(name="patient_header_isactive")
	private Boolean patientHeaderIsActive;

	public Boolean getPatientHeaderIsActive() {
		return patientHeaderIsActive;
	}

	public void setPatientHeaderIsActive(Boolean patientHeaderIsActive) {
		this.patientHeaderIsActive = patientHeaderIsActive;
	}

	public Integer getPatientHeaderId() {
		return patientHeaderId;
	}

	public void setPatientHeaderId(Integer patientHeaderId) {
		this.patientHeaderId = patientHeaderId;
	}

	public String getPatientHeaderName() {
		return patientHeaderName;
	}

	public void setPatientHeaderName(String patientHeaderName) {
		this.patientHeaderName = patientHeaderName;
	}

	public Integer getPatientHeaderType() {
		return patientHeaderType;
	}

	public void setPatientHeaderType(Integer patientHeaderType) {
		this.patientHeaderType = patientHeaderType;
	}

	public Boolean getPatientHeaderIsDefault() {
		return patientHeaderIsDefault;
	}

	public void setPatientHeaderIsDefault(Boolean patientHeaderIsDefault) {
		this.patientHeaderIsDefault = patientHeaderIsDefault;
	}
	

}
