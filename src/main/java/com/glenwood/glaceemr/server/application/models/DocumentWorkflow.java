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
@Table(name = "document_workflow")
public class DocumentWorkflow {
	@Id
	@SequenceGenerator(name = "sequence", sequenceName = "document_workflow_document_workflow_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
	@Column(name="document_workflow_id")
	private Integer id;
	
	@Column(name="document_workflow_patient_id")
	private Integer patientId;
	
	@Column(name="document_workflow_source")
	private Integer source;
	
	@Column(name="document_workflow_source_information")
	private String sourceInformation;
	
	@Column(name="document_workflow_destination_information")
	private String destinationInformation;
	
	@Column(name="document_workflow_sent_by")
	private Integer sentBy;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="document_workflow_sent_on")
	private Timestamp sentOn;
	
	@Column(name="document_workflow_notes")
	private String notes;
	
	@Column(name="document_workflow_status")
	private Integer status;
	
	@Column(name="document_workflow_processed_by")
	private Integer processedBy;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="document_workflow_processed_on")
	private Timestamp processedOn;
	
	
	@Column(name="isactive",columnDefinition = "Boolean default true")
	private Boolean isactive;
	
	@Column(name="document_workflow_body")
	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getSourceInformation() {
		return sourceInformation;
	}

	public void setSourceInformation(String sourceInformation) {
		this.sourceInformation = sourceInformation;
	}

	public String getDestinationInformation() {
		return destinationInformation;
	}

	public void setDestinationInformation(String destinationInformation) {
		this.destinationInformation = destinationInformation;
	}

	public Integer getSentBy() {
		return sentBy;
	}

	public void setSentBy(Integer sentBy) {
		this.sentBy = sentBy;
	}

	public Timestamp getSentOn() {
		return sentOn;
	}

	public void setSentOn(Timestamp sentOn) {
		this.sentOn = sentOn;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getProcessedBy() {
		return processedBy;
	}

	public void setProcessedBy(Integer processedBy) {
		this.processedBy = processedBy;
	}

	public Timestamp getProcessedOn() {
		return processedOn;
	}

	public void setProcessedOn(Timestamp processedOn) {
		this.processedOn = processedOn;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
	
	

}
