package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "alert_patient_doc_mapping")
public class AlertPatientDocMapping {



	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="alert_patient_doc_mapping_alert_mapping_id_seq")
	@SequenceGenerator(name =" alert_patient_doc_mapping_alert_mapping_id_seq", sequenceName="alert_patient_doc_mapping_alert_mapping_id_seq", allocationSize=1)
	@Column(name="alert_mapping_id")
	private Integer alertMappingId;

	@Id
	@Column(name="alert_id")
	private Integer alertId;

	@Column(name="forwarded_filedetails_id")
	private String forwardedFiledetailsId;

	@Column(name="alert_patient_doc_mapping_description")
	private String alertPatientDocMappingDescription;

	public Integer getAlertMappingId() {
		return alertMappingId;
	}

	public void setAlertMappingId(Integer alertMappingId) {
		this.alertMappingId = alertMappingId;
	}

	public Integer getAlertId() {
		return alertId;
	}

	public void setAlertId(Integer alertId) {
		this.alertId = alertId;
	}

	public String getForwardedFiledetailsId() {
		return forwardedFiledetailsId;
	}

	public void setForwardedFiledetailsId(String forwardedFiledetailsId) {
		this.forwardedFiledetailsId = forwardedFiledetailsId;
	}

	public String getAlertPatientDocMappingDescription() {
		return alertPatientDocMappingDescription;
	}

	public void setAlertPatientDocMappingDescription(
			String alertPatientDocMappingDescription) {
		this.alertPatientDocMappingDescription = alertPatientDocMappingDescription;
	}

}