package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "patient_portal_refill_request")
public class PatientPortalRefillRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="public.patient_portal_refill_request_id_seq")
	@SequenceGenerator(name ="public.patient_portal_refill_request_id_seq", sequenceName="public.patient_portal_refill_request_id_seq", allocationSize=1)
	@Column(name="patient_portal_refill_request_id")
	private Integer patient_portal_refill_request_id;

	@Column(name="patient_portal_refill_request_refill_id")
	private Integer patient_portal_refill_request_refill_id;

	@Column(name="patient_portal_refill_request_patient_id")
	private Integer patient_portal_refill_request_patient_id;

	@Column(name="patient_portal_refill_request_zhtml")
	private Integer patient_portal_refill_request_zhtml;

	@Column(name="patient_portal_refill_request_requested_date")
	private String patient_portal_refill_request_requested_date;

	@Column(name="patient_portal_refill_request_prescription_id")
	private Integer patient_portal_refill_request_prescription_id;

	@Column(name="patient_portal_refill_request_status")
	private Integer patient_portal_refill_request_status;

	@Column(name="patient_portal_refill_request_flag")
	private Integer patient_portal_refill_request_flag;

	@Column(name="patient_portal_refill_request_requested_to")
	private String patient_portal_refill_request_requested_to;

	public Integer getpatient_portal_refill_request_id() {
		return patient_portal_refill_request_id;
	}

	public void setpatient_portal_refill_request_id(Integer patient_portal_refill_request_id) {
		this.patient_portal_refill_request_id = patient_portal_refill_request_id;
	}

	public Integer getpatient_portal_refill_request_refill_id() {
		return patient_portal_refill_request_refill_id;
	}

	public void setpatient_portal_refill_request_refill_id(Integer patient_portal_refill_request_refill_id) {
		this.patient_portal_refill_request_refill_id = patient_portal_refill_request_refill_id;
	}

	public Integer getpatient_portal_refill_request_patient_id() {
		return patient_portal_refill_request_patient_id;
	}

	public void setpatient_portal_refill_request_patient_id(Integer patient_portal_refill_request_patient_id) {
		this.patient_portal_refill_request_patient_id = patient_portal_refill_request_patient_id;
	}

	public Integer getpatient_portal_refill_request_zhtml() {
		return patient_portal_refill_request_zhtml;
	}

	public void setpatient_portal_refill_request_zhtml(Integer patient_portal_refill_request_zhtml) {
		this.patient_portal_refill_request_zhtml = patient_portal_refill_request_zhtml;
	}

	public String getpatient_portal_refill_request_requested_date() {
		return patient_portal_refill_request_requested_date;
	}

	public void setpatient_portal_refill_request_requested_date(String patient_portal_refill_request_requested_date) {
		this.patient_portal_refill_request_requested_date = patient_portal_refill_request_requested_date;
	}

	public Integer getpatient_portal_refill_request_prescription_id() {
		return patient_portal_refill_request_prescription_id;
	}

	public void setpatient_portal_refill_request_prescription_id(Integer patient_portal_refill_request_prescription_id) {
		this.patient_portal_refill_request_prescription_id = patient_portal_refill_request_prescription_id;
	}

	public Integer getpatient_portal_refill_request_status() {
		return patient_portal_refill_request_status;
	}

	public void setpatient_portal_refill_request_status(Integer patient_portal_refill_request_status) {
		this.patient_portal_refill_request_status = patient_portal_refill_request_status;
	}

	public Integer getpatient_portal_refill_request_flag() {
		return patient_portal_refill_request_flag;
	}

	public void setpatient_portal_refill_request_flag(Integer patient_portal_refill_request_flag) {
		this.patient_portal_refill_request_flag = patient_portal_refill_request_flag;
	}

	public String getpatient_portal_refill_request_requested_to() {
		return patient_portal_refill_request_requested_to;
	}

	public void setpatient_portal_refill_request_requested_to(String patient_portal_refill_request_requested_to) {
		this.patient_portal_refill_request_requested_to = patient_portal_refill_request_requested_to;
	}
	
	
}