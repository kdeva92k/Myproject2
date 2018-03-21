package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ss_outbox")
public class SSOutbox implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer getSsOutboxId() {
		return ssOutboxId;
	}

	public void setSsOutboxId(Integer ssOutboxId) {
		this.ssOutboxId = ssOutboxId;
	}

	public String getSsOutboxFromId() {
		return ssOutboxFromId;
	}

	public void setSsOutboxFromId(String ssOutboxFromId) {
		this.ssOutboxFromId = ssOutboxFromId;
	}

	public String getSsOutboxToId() {
		return ssOutboxToId;
	}

	public void setSsOutboxToId(String ssOutboxToId) {
		this.ssOutboxToId = ssOutboxToId;
	}

	public String getSsOutboxPharmacyName() {
		return ssOutboxPharmacyName;
	}

	public void setSsOutboxPharmacyName(String ssOutboxPharmacyName) {
		this.ssOutboxPharmacyName = ssOutboxPharmacyName;
	}

	public String getSsOutboxMessageType() {
		return ssOutboxMessageType;
	}

	public void setSsOutboxMessageType(String ssOutboxMessageType) {
		this.ssOutboxMessageType = ssOutboxMessageType;
	}

	public Integer getSsOutboxPatientId() {
		return ssOutboxPatientId;
	}

	public void setSsOutboxPatientId(Integer ssOutboxPatientId) {
		this.ssOutboxPatientId = ssOutboxPatientId;
	}

	public Integer getSsOutboxPrescriptionId() {
		return ssOutboxPrescriptionId;
	}

	public void setSsOutboxPrescriptionId(Integer ssOutboxPrescriptionId) {
		this.ssOutboxPrescriptionId = ssOutboxPrescriptionId;
	}

	public String getSsOutboxSentDateTime() {
		return ssOutboxSentDateTime;
	}

	public void setSsOutboxSentDateTime(String ssOutboxSentDateTime) {
		this.ssOutboxSentDateTime = ssOutboxSentDateTime;
	}

	public Integer getSsOutboxMessageStatus() {
		return ssOutboxMessageStatus;
	}

	public void setSsOutboxMessageStatus(Integer ssOutboxMessageStatus) {
		this.ssOutboxMessageStatus = ssOutboxMessageStatus;
	}

	public String getSsOutboxStatusOrErrorCode() {
		return ssOutboxStatusOrErrorCode;
	}

	public void setSsOutboxStatusOrErrorCode(String ssOutboxStatusOrErrorCode) {
		this.ssOutboxStatusOrErrorCode = ssOutboxStatusOrErrorCode;
	}

	public String getSsOutboxActualSentTime() {
		return ssOutboxActualSentTime;
	}

	public void setSsOutboxActualSentTime(String ssOutboxActualSentTime) {
		this.ssOutboxActualSentTime = ssOutboxActualSentTime;
	}

	public Integer getSsOutboxEncounterId() {
		return ssOutboxEncounterId;
	}

	public void setSsOutboxEncounterId(Integer ssOutboxEncounterId) {
		this.ssOutboxEncounterId = ssOutboxEncounterId;
	}

	public String getSsOutboxSentMessageId() {
		return ssOutboxSentMessageId;
	}

	public void setSsOutboxSentMessageId(String ssOutboxSentMessageId) {
		this.ssOutboxSentMessageId = ssOutboxSentMessageId;
	}

	public String getSsOutboxXmlContent() {
		return ssOutboxXmlContent;
	}

	public void setSsOutboxXmlContent(String ssOutboxXmlContent) {
		this.ssOutboxXmlContent = ssOutboxXmlContent;
	}

	@Id
	@Column(name="ss_outbox_id")
	private Integer ssOutboxId;

	@Column(name="ss_outbox_from_id")
	private String ssOutboxFromId;

	@Column(name="ss_outbox_to_id")
	private String ssOutboxToId;

	@Column(name="ss_outbox_pharmacy_name")
	private String ssOutboxPharmacyName;

	@Column(name="ss_outbox_message_type")
	private String ssOutboxMessageType;

	@Column(name="ss_outbox_patient_id")
	private Integer ssOutboxPatientId;

	@Column(name="ss_outbox_prescription_id")
	private Integer ssOutboxPrescriptionId;

	@Column(name="ss_outbox_sent_date_time")
	private String ssOutboxSentDateTime;

	@Column(name="ss_outbox_message_status")
	private Integer ssOutboxMessageStatus;

	@Column(name="ss_outbox_status_or_error_code")
	private String ssOutboxStatusOrErrorCode;

	@Column(name="ss_outbox_actual_sent_time")
	private String ssOutboxActualSentTime;

	@Column(name="ss_outbox_encounter_id")
	private Integer ssOutboxEncounterId;

	@Column(name="ss_outbox_sent_message_id")
	private String ssOutboxSentMessageId;

	@Column(name="ss_outbox_xml_content")
	private String ssOutboxXmlContent;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="ss_outbox_status_or_error_code",referencedColumnName="ss_status_error_codes_name",insertable=false,updatable=false)
	@JsonManagedReference
	SSStatusErrorCodes ssstatuserrorcodes;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="ss_outbox_prescription_id",referencedColumnName="doc_presc_id",insertable=false,updatable=false)
	@JsonBackReference
	Prescription prescription;

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="ss_outbox_from_id",referencedColumnName="spi_locationid",insertable=false,updatable=false)
	@JsonManagedReference
	LocationDetails locationDetails;
	
	
	public LocationDetails getLocationDetails() {
		return locationDetails;
	}

	public void setLocationDetails(LocationDetails locationDetails) {
		this.locationDetails = locationDetails;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public SSStatusErrorCodes getSsstatuserrorcodes() {
		return ssstatuserrorcodes;
	}

	public void setSsstatuserrorcodes(SSStatusErrorCodes ssstatuserrorcodes) {
		this.ssstatuserrorcodes = ssstatuserrorcodes;
	}
	
}