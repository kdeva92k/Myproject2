package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "direct_sent_items")
public class DirectSentItems {
	@Id
	@Column(name="direct_sent_items_message_id")
	private String messageId;
	
	@Column(name="direct_sent_items_from_address")
	private String fromAddress;
	
	@Column(name="direct_sent_items_to_address")
	private String toAddress;
	
	@Column(name="direct_sent_items_subject")
	private String subject;
	
	@Column(name="direct_sent_items_sent_by")
	private Integer sentBy;
	
	@Column(name="direct_sent_items_sent_by_type")
	private Integer sentByType;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="direct_sent_items_sent_on")
	private Timestamp sentOn;
	
	@Column(name="direct_sent_items_patientid")
	private Integer patientId;
	
	@Column(name="direct_sent_items_filename")
	private String fileName;
	
	@Column(name="direct_sent_items_status")
	private Integer status;
	
	@Column(name="direct_sent_items_mdn_filename")
	private String mdnFileName;
	
	@Column(name="isactive",columnDefinition = "Boolean default true")
	private Boolean isactive;
	
	@Column(name="direct_sent_items_body")
	private String body;
	
	@Column(name="direct_sent_items_statuscode")
	private Integer statusCode;

	@Column(name="direct_sent_items_statusmessage")
	private String statusMessage;
	
	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}



	public Integer getStatusCode() {
		return statusCode;
	}



	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}



	public String getStatusMessage() {
		return statusMessage;
	}



	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}



	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getSentBy() {
		return sentBy;
	}

	public void setSentBy(Integer sentBy) {
		this.sentBy = sentBy;
	}

	public Integer getSentByType() {
		return sentByType;
	}

	public void setSentByType(Integer sentByType) {
		this.sentByType = sentByType;
	}

	public Timestamp getSentOn() {
		return sentOn;
	}

	public void setSentOn(Timestamp sentOn) {
		this.sentOn = sentOn;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMdnFileName() {
		return mdnFileName;
	}

	public void setMdnFileName(String mdnFileName) {
		this.mdnFileName = mdnFileName;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
	
	
	
	
	
	

}
