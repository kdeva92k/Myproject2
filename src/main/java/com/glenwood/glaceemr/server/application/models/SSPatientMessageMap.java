package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "ss_patient_message_map")
public class SSPatientMessageMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer getSsPatientMessageMapId() {
		return ssPatientMessageMapId;
	}

	public void setSsPatientMessageMapId(Integer ssPatientMessageMapId) {
		this.ssPatientMessageMapId = ssPatientMessageMapId;
	}

	public Integer getSsPatientMessageMapInboxId() {
		return ssPatientMessageMapInboxId;
	}

	public void setSsPatientMessageMapInboxId(Integer ssPatientMessageMapInboxId) {
		this.ssPatientMessageMapInboxId = ssPatientMessageMapInboxId;
	}

	public Integer getSsPatientMessageMapPatientId() {
		return ssPatientMessageMapPatientId;
	}

	public void setSsPatientMessageMapPatientId(Integer ssPatientMessageMapPatientId) {
		this.ssPatientMessageMapPatientId = ssPatientMessageMapPatientId;
	}

	public Integer getSsPatientMessageMapEncounterId() {
		return ssPatientMessageMapEncounterId;
	}

	public void setSsPatientMessageMapEncounterId(
			Integer ssPatientMessageMapEncounterId) {
		this.ssPatientMessageMapEncounterId = ssPatientMessageMapEncounterId;
	}

	public Integer getSsPatientMessageMapPrescriptionId() {
		return ssPatientMessageMapPrescriptionId;
	}

	public void setSsPatientMessageMapPrescriptionId(
			Integer ssPatientMessageMapPrescriptionId) {
		this.ssPatientMessageMapPrescriptionId = ssPatientMessageMapPrescriptionId;
	}

	public Integer getSsPatientMessageMapResponseType() {
		return ssPatientMessageMapResponseType;
	}

	public void setSsPatientMessageMapResponseType(
			Integer ssPatientMessageMapResponseType) {
		this.ssPatientMessageMapResponseType = ssPatientMessageMapResponseType;
	}

	public String getSsPatientMessageMapReplyNotes() {
		return ssPatientMessageMapReplyNotes;
	}

	public void setSsPatientMessageMapReplyNotes(
			String ssPatientMessageMapReplyNotes) {
		this.ssPatientMessageMapReplyNotes = ssPatientMessageMapReplyNotes;
	}

	public String getSsPatientMessageMapOutboxId() {
		return ssPatientMessageMapOutboxId;
	}

	public void setSsPatientMessageMapOutboxId(String ssPatientMessageMapOutboxId) {
		this.ssPatientMessageMapOutboxId = ssPatientMessageMapOutboxId;
	}

	public String getSsPatientMessageMapReasonCode() {
		return ssPatientMessageMapReasonCode;
	}

	public void setSsPatientMessageMapReasonCode(
			String ssPatientMessageMapReasonCode) {
		this.ssPatientMessageMapReasonCode = ssPatientMessageMapReasonCode;
	}

	public String getSsPatientMessageMapDenialNewPrescSentFlag() {
		return ssPatientMessageMapDenialNewPrescSentFlag;
	}

	public void setSsPatientMessageMapDenialNewPrescSentFlag(
			String ssPatientMessageMapDenialNewPrescSentFlag) {
		this.ssPatientMessageMapDenialNewPrescSentFlag = ssPatientMessageMapDenialNewPrescSentFlag;
	}
	
	@Column(name="ss_patient_message_map_id")
	private Integer ssPatientMessageMapId;

	@Id
	@Column(name="ss_patient_message_map_inbox_id")
	private Integer ssPatientMessageMapInboxId;

	@Column(name="ss_patient_message_map_patient_id")
	private Integer ssPatientMessageMapPatientId;

	@Column(name="ss_patient_message_map_encounter_id")
	private Integer ssPatientMessageMapEncounterId;

	@Column(name="ss_patient_message_map_prescription_id")
	private Integer ssPatientMessageMapPrescriptionId;

	@Column(name="ss_patient_message_map_response_type")
	private Integer ssPatientMessageMapResponseType;

	@Column(name="ss_patient_message_map_reply_notes")
	private String ssPatientMessageMapReplyNotes;

	@Column(name="ss_patient_message_map_outbox_id")
	private String ssPatientMessageMapOutboxId;

	@Column(name="ss_patient_message_map_reason_code")
	private String ssPatientMessageMapReasonCode;
	
	@Column(name="ss_patient_message_map_denial_new_presc_sent_flag")
	private String ssPatientMessageMapDenialNewPrescSentFlag;
	
	


	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="ss_patient_message_map_outbox_id",referencedColumnName="ss_outbox_sent_message_id",insertable=false,updatable=false)
	@JsonManagedReference
	SSOutbox ssoutbox;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="ss_patient_message_map_prescription_id",referencedColumnName="doc_presc_id",insertable=false,updatable=false)
	@JsonManagedReference
	Prescription docpresc;
	
	public Prescription getDocpresc() {
		return docpresc;
	}

	public void setDocpresc(Prescription docpresc) {
		this.docpresc = docpresc;
	}

	public SSOutbox getSsoutbox() {
		return ssoutbox;
	}

	public void setSsoutbox(SSOutbox ssoutbox) {
		this.ssoutbox = ssoutbox;
	}
}