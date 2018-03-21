package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "ss_cancel_outbox")
public class SSCancelOutbox {

	@Id
	@Column(name="ss_cancel_outbox_id")
	private Integer ssCancelOutboxId;

	public Integer getSsCancelOutboxId() {
		return ssCancelOutboxId;
	}

	public void setSsCancelOutboxId(Integer ssCancelOutboxId) {
		this.ssCancelOutboxId = ssCancelOutboxId;
	}

	public String getSsCancelOutboxFromId() {
		return ssCancelOutboxFromId;
	}

	public void setSsCancelOutboxFromId(String ssCancelOutboxFromId) {
		this.ssCancelOutboxFromId = ssCancelOutboxFromId;
	}

	public String getSsCancelOutboxToId() {
		return ssCancelOutboxToId;
	}

	public void setSsCancelOutboxToId(String ssCancelOutboxToId) {
		this.ssCancelOutboxToId = ssCancelOutboxToId;
	}

	public String getSsCancelOutboxPharmacyName() {
		return ssCancelOutboxPharmacyName;
	}

	public void setSsCancelOutboxPharmacyName(String ssCancelOutboxPharmacyName) {
		this.ssCancelOutboxPharmacyName = ssCancelOutboxPharmacyName;
	}

	public String getSsCancelOutboxMessageType() {
		return ssCancelOutboxMessageType;
	}

	public void setSsCancelOutboxMessageType(String ssCancelOutboxMessageType) {
		this.ssCancelOutboxMessageType = ssCancelOutboxMessageType;
	}

	public Integer getSsCancelOutboxPatientId() {
		return ssCancelOutboxPatientId;
	}

	public void setSsCancelOutboxPatientId(Integer ssCancelOutboxPatientId) {
		this.ssCancelOutboxPatientId = ssCancelOutboxPatientId;
	}

	public Integer getSsCancelOutboxPrescriptionId() {
		return ssCancelOutboxPrescriptionId;
	}

	public void setSsCancelOutboxPrescriptionId(Integer ssCancelOutboxPrescriptionId) {
		this.ssCancelOutboxPrescriptionId = ssCancelOutboxPrescriptionId;
	}

	public String getSsCancelOutboxSentDateTime() {
		return ssCancelOutboxSentDateTime;
	}

	public void setSsCancelOutboxSentDateTime(String ssCancelOutboxSentDateTime) {
		this.ssCancelOutboxSentDateTime = ssCancelOutboxSentDateTime;
	}

	public Integer getSsCancelOutboxMessageStatus() {
		return ssCancelOutboxMessageStatus;
	}

	public void setSsCancelOutboxMessageStatus(Integer ssCancelOutboxMessageStatus) {
		this.ssCancelOutboxMessageStatus = ssCancelOutboxMessageStatus;
	}

	public String getSsCancelOutboxStatusOrErrorCode() {
		return ssCancelOutboxStatusOrErrorCode;
	}

	public void setSsCancelOutboxStatusOrErrorCode(
			String ssCancelOutboxStatusOrErrorCode) {
		this.ssCancelOutboxStatusOrErrorCode = ssCancelOutboxStatusOrErrorCode;
	}

	public Integer getSsCancelOutboxEncounterId() {
		return ssCancelOutboxEncounterId;
	}

	public void setSsCancelOutboxEncounterId(Integer ssCancelOutboxEncounterId) {
		this.ssCancelOutboxEncounterId = ssCancelOutboxEncounterId;
	}

	public String getSsCancelOutboxSentMessageId() {
		return ssCancelOutboxSentMessageId;
	}

	public void setSsCancelOutboxSentMessageId(String ssCancelOutboxSentMessageId) {
		this.ssCancelOutboxSentMessageId = ssCancelOutboxSentMessageId;
	}

	public String getSsCancelOutboxXmlContent() {
		return ssCancelOutboxXmlContent;
	}

	public void setSsCancelOutboxXmlContent(String ssCancelOutboxXmlContent) {
		this.ssCancelOutboxXmlContent = ssCancelOutboxXmlContent;
	}

	public String getSsCancelOutboxPrescriberOrderNumber() {
		return ssCancelOutboxPrescriberOrderNumber;
	}

	public void setSsCancelOutboxPrescriberOrderNumber(
			String ssCancelOutboxPrescriberOrderNumber) {
		this.ssCancelOutboxPrescriberOrderNumber = ssCancelOutboxPrescriberOrderNumber;
	}

	public Boolean getSsCancelOutboxResponseReceived() {
		return ssCancelOutboxResponseReceived;
	}

	public void setSsCancelOutboxResponseReceived(
			Boolean ssCancelOutboxResponseReceived) {
		this.ssCancelOutboxResponseReceived = ssCancelOutboxResponseReceived;
	}

	public String getSsCancelOutboxResponseReceivedOn() {
		return ssCancelOutboxResponseReceivedOn;
	}

	public void setSsCancelOutboxResponseReceivedOn(
			String ssCancelOutboxResponseReceivedOn) {
		this.ssCancelOutboxResponseReceivedOn = ssCancelOutboxResponseReceivedOn;
	}

	public String getSsCancelOutboxUpdateNote() {
		return ssCancelOutboxUpdateNote;
	}

	public void setSsCancelOutboxUpdateNote(String ssCancelOutboxUpdateNote) {
		this.ssCancelOutboxUpdateNote = ssCancelOutboxUpdateNote;
	}

	public String getSsCancelOutboxLastUpdateOn() {
		return ssCancelOutboxLastUpdateOn;
	}

	public void setSsCancelOutboxLastUpdateOn(String ssCancelOutboxLastUpdateOn) {
		this.ssCancelOutboxLastUpdateOn = ssCancelOutboxLastUpdateOn;
	}

	@Column(name="ss_cancel_outbox_from_id")
	private String ssCancelOutboxFromId;

	@Column(name="ss_cancel_outbox_to_id")
	private String ssCancelOutboxToId;

	@Column(name="ss_cancel_outbox_pharmacy_name")
	private String ssCancelOutboxPharmacyName;

	@Column(name="ss_cancel_outbox_message_type")
	private String ssCancelOutboxMessageType;

	@Column(name="ss_cancel_outbox_patient_id")
	private Integer ssCancelOutboxPatientId;

	@Column(name="ss_cancel_outbox_prescription_id")
	private Integer ssCancelOutboxPrescriptionId;

	@Column(name="ss_cancel_outbox_sent_date_time")
	private String ssCancelOutboxSentDateTime;

	@Column(name="ss_cancel_outbox_message_status")
	private Integer ssCancelOutboxMessageStatus;

	@Column(name="ss_cancel_outbox_status_or_error_code")
	private String ssCancelOutboxStatusOrErrorCode;

	@Column(name="ss_cancel_outbox_encounter_id")
	private Integer ssCancelOutboxEncounterId;

	@Column(name="ss_cancel_outbox_sent_message_id")
	private String ssCancelOutboxSentMessageId;

	@Column(name="ss_cancel_outbox_xml_content")
	private String ssCancelOutboxXmlContent;

	@Column(name="ss_cancel_outbox_prescriber_order_number")
	private String ssCancelOutboxPrescriberOrderNumber;

	@Column(name="ss_cancel_outbox_response_received")
	private Boolean ssCancelOutboxResponseReceived;

	@Column(name="ss_cancel_outbox_response_received_on")
	private String ssCancelOutboxResponseReceivedOn;

	@Column(name="ss_cancel_outbox_update_note")
	private String ssCancelOutboxUpdateNote;

	@Column(name="ss_cancel_outbox_last_update_on")
	private String ssCancelOutboxLastUpdateOn;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="ss_cancel_outbox_prescription_id",referencedColumnName="doc_presc_id",insertable=false,updatable=false)
	@JsonManagedReference
	Prescription docpresc;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="ss_cancel_outbox_patient_id",referencedColumnName="patient_registration_id",insertable=false,updatable=false)
	@JsonManagedReference
	PatientRegistration patientregistration;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="ss_cancel_outbox_from_id",referencedColumnName="spi_locationid",insertable=false,updatable=false)
	@JsonManagedReference
	LocationDetails locationdetails;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="ss_cancel_outbox_to_id",referencedColumnName="pharm_details_ncpdpid",insertable=false,updatable=false)
	@JsonManagedReference
	PharmDetails pharmacydetails;

	public LocationDetails getLocationdetails() {
		return locationdetails;
	}

	public void setLocationdetails(LocationDetails locationdetails) {
		this.locationdetails = locationdetails;
	}

	public PharmDetails getPharmacydetails() {
		return pharmacydetails;
	}

	public void setPharmacydetails(PharmDetails pharmacydetails) {
		this.pharmacydetails = pharmacydetails;
	}

	public PatientRegistration getPatientregistration() {
		return patientregistration;
	}

	public void setPatientregistration(PatientRegistration patientregistration) {
		this.patientregistration = patientregistration;
	}

	public Prescription getDocpresc() {
		return docpresc;
	}

	public void setDocpresc(Prescription docpresc) {
		this.docpresc = docpresc;
	}
}