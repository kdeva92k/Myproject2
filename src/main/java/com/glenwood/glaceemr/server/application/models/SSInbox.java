package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ss_inbox")
public class SSInbox implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Integer getSsInboxId() {
		return ssInboxId;
	}

	public void setSsInboxId(Integer ssInboxId) {
		this.ssInboxId = ssInboxId;
	}

	public String getSsInboxFromId() {
		return ssInboxFromId;
	}

	public void setSsInboxFromId(String ssInboxFromId) {
		this.ssInboxFromId = ssInboxFromId;
	}

	public String getSsInboxToId() {
		return ssInboxToId;
	}

	public void setSsInboxToId(String ssInboxToId) {
		this.ssInboxToId = ssInboxToId;
	}

	public String getSsInboxFrom() {
		return ssInboxFrom;
	}

	public void setSsInboxFrom(String ssInboxFrom) {
		this.ssInboxFrom = ssInboxFrom;
	}

	public String getSsInboxTo() {
		return ssInboxTo;
	}

	public void setSsInboxTo(String ssInboxTo) {
		this.ssInboxTo = ssInboxTo;
	}

	public String getSsInboxSentDateTime() {
		return ssInboxSentDateTime;
	}

	public void setSsInboxSentDateTime(String ssInboxSentDateTime) {
		this.ssInboxSentDateTime = ssInboxSentDateTime;
	}

	public String getSsInboxMessageType() {
		return ssInboxMessageType;
	}

	public void setSsInboxMessageType(String ssInboxMessageType) {
		this.ssInboxMessageType = ssInboxMessageType;
	}

	public String getSsInboxXmlFileName() {
		return ssInboxXmlFileName;
	}

	public void setSsInboxXmlFileName(String ssInboxXmlFileName) {
		this.ssInboxXmlFileName = ssInboxXmlFileName;
	}

	public String getSsInboxXmlContent() {
		return ssInboxXmlContent;
	}

	public void setSsInboxXmlContent(String ssInboxXmlContent) {
		this.ssInboxXmlContent = ssInboxXmlContent;
	}

	public Integer getSsInboxPrescriptionId() {
		return ssInboxPrescriptionId;
	}

	public void setSsInboxPrescriptionId(Integer ssInboxPrescriptionId) {
		this.ssInboxPrescriptionId = ssInboxPrescriptionId;
	}

	public String getSsInboxRxReferenceNo() {
		return ssInboxRxReferenceNo;
	}

	public void setSsInboxRxReferenceNo(String ssInboxRxReferenceNo) {
		this.ssInboxRxReferenceNo = ssInboxRxReferenceNo;
	}

	public String getSsInboxPharmacyMessageId() {
		return ssInboxPharmacyMessageId;
	}

	public void setSsInboxPharmacyMessageId(String ssInboxPharmacyMessageId) {
		this.ssInboxPharmacyMessageId = ssInboxPharmacyMessageId;
	}

	public String getSsInboxRelatesToMessageId() {
		return ssInboxRelatesToMessageId;
	}

	public void setSsInboxRelatesToMessageId(String ssInboxRelatesToMessageId) {
		this.ssInboxRelatesToMessageId = ssInboxRelatesToMessageId;
	}

	public Integer getSsInboxAlertId() {
		return ssInboxAlertId;
	}

	public void setSsInboxAlertId(Integer ssInboxAlertId) {
		this.ssInboxAlertId = ssInboxAlertId;
	}

	public String getSsInboxResponseMessage() {
		return ssInboxResponseMessage;
	}

	public void setSsInboxResponseMessage(String ssInboxResponseMessage) {
		this.ssInboxResponseMessage = ssInboxResponseMessage;
	}

	public String getSsInboxPrescriberOrderNo() {
		return ssInboxPrescriberOrderNo;
	}

	public void setSsInboxPrescriberOrderNo(String ssInboxPrescriberOrderNo) {
		this.ssInboxPrescriberOrderNo = ssInboxPrescriberOrderNo;
	}

	public Integer getSsInboxAlertEventMessageId() {
		return ssInboxAlertEventMessageId;
	}

	public void setSsInboxAlertEventMessageId(Integer ssInboxAlertEventMessageId) {
		this.ssInboxAlertEventMessageId = ssInboxAlertEventMessageId;
	}

	@Id
	@Column(name="ss_inbox_id")
	private Integer ssInboxId;

	@Column(name="ss_inbox_from_id")
	private String ssInboxFromId;

	@Column(name="ss_inbox_to_id")
	private String ssInboxToId;

	@Column(name="ss_inbox_from")
	private String ssInboxFrom;

	@Column(name="ss_inbox_to")
	private String ssInboxTo;

	@Column(name="ss_inbox_sent_date_time")
	private String ssInboxSentDateTime;

	@Column(name="ss_inbox_message_type")
	private String ssInboxMessageType;

	@Column(name="ss_inbox_xml_file_name")
	private String ssInboxXmlFileName;

	@Column(name="ss_inbox_xml_content")
	private String ssInboxXmlContent;

	@Column(name="ss_inbox_prescription_id")
	private Integer ssInboxPrescriptionId;

	@Column(name="ss_inbox_rx_reference_no")
	private String ssInboxRxReferenceNo;

	@Column(name="ss_inbox_pharmacy_message_id")
	private String ssInboxPharmacyMessageId;

	@Column(name="ss_inbox_relates_to_message_id")
	private String ssInboxRelatesToMessageId;

	@Column(name="ss_inbox_alert_id")
	private Integer ssInboxAlertId;

	@Column(name="ss_inbox_response_message")
	private String ssInboxResponseMessage;

	@Column(name="ss_inbox_prescriber_order_no")
	private String ssInboxPrescriberOrderNo;

	@Column(name="ss_inbox_alert_event_message_id")
	private Integer ssInboxAlertEventMessageId;
	
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="ss_inbox_id",referencedColumnName="ss_patient_message_map_inbox_id",insertable=false,updatable=false)
	@JsonManagedReference
	SSPatientMessageMap sspatientmessagemap;


	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="ss_inbox_id",referencedColumnName="ss_med_details_map_inbox_id",insertable=false,updatable=false)
	@JsonManagedReference
	SSMedDetails ssmeddetails;
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	@JoinColumn(name="ss_inbox_message_type",referencedColumnName="refill_message_type_id",insertable=false,updatable=false)
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(formula = @JoinFormula(value = "ss_inbox_message_type::integer", referencedColumnName = "refill_message_type_id")) })
	@JsonManagedReference
	RefillMessageType refillMessageType;
	
	public RefillMessageType getRefillMessageType() {
		return refillMessageType;
	}

	public void setRefillMessageType(RefillMessageType refillMessageType) {
		this.refillMessageType = refillMessageType;
	}

	public SSMedDetails getSsmeddetails() {
		return ssmeddetails;
	}

	public void setSsmeddetails(SSMedDetails ssmeddetails) {
		this.ssmeddetails = ssmeddetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SSPatientMessageMap getSspatientmessagemap() {
		return sspatientmessagemap;
	}

	public void setSspatientmessagemap(SSPatientMessageMap sspatientmessagemap) {
		this.sspatientmessagemap = sspatientmessagemap;
	}

	
}