package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "patient_portal_alert_config")
public class PatientPortalAlertConfig {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="public.patient_portal_alert_config_patient_portal_alert_config_id_seq")
	@SequenceGenerator(name ="public.patient_portal_alert_config_patient_portal_alert_config_id_seq", sequenceName="public.patient_portal_alert_config_patient_portal_alert_config_id_seq", allocationSize=1)
	@Column(name="patient_portal_alert_config_id")
	private Integer patient_portal_alert_config_id;

	@Column(name="patient_portal_alert_config_display_name")
	private String patient_portal_alert_config_display_name;

	@Column(name="patient_portal_alert_config_provider")
	private String patient_portal_alert_config_provider;

	@Column(name="patient_portal_alert_config_forward_to")
	private String patient_portal_alert_config_forward_to;

	@Column(name="patient_portal_alert_config_send_to_all")
	private Boolean patient_portal_alert_config_send_to_all;

	public Integer getpatient_portal_alert_config_id() {
		return patient_portal_alert_config_id;
	}

	public void setpatient_portal_alert_config_id(Integer patient_portal_alert_config_id) {
		this.patient_portal_alert_config_id = patient_portal_alert_config_id;
	}

	public String getpatient_portal_alert_config_display_name() {
		return patient_portal_alert_config_display_name;
	}

	public void setpatient_portal_alert_config_display_name(String patient_portal_alert_config_display_name) {
		this.patient_portal_alert_config_display_name = patient_portal_alert_config_display_name;
	}

	public String getpatient_portal_alert_config_provider() {
		return patient_portal_alert_config_provider;
	}

	public void setpatient_portal_alert_config_provider(String patient_portal_alert_config_provider) {
		this.patient_portal_alert_config_provider = patient_portal_alert_config_provider;
	}

	public String getpatient_portal_alert_config_forward_to() {
		return patient_portal_alert_config_forward_to;
	}

	public void setpatient_portal_alert_config_forward_to(String patient_portal_alert_config_forward_to) {
		this.patient_portal_alert_config_forward_to = patient_portal_alert_config_forward_to;
	}

	public Boolean getpatient_portal_alert_config_send_to_all() {
		return patient_portal_alert_config_send_to_all;
	}

	public void setpatient_portal_alert_config_send_to_all(Boolean patient_portal_alert_config_send_to_all) {
		this.patient_portal_alert_config_send_to_all = patient_portal_alert_config_send_to_all;
	}
	
}