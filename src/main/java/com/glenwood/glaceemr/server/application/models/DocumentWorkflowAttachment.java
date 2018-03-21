package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "document_workflow_attachments")
public class DocumentWorkflowAttachment {
	@Id
	@SequenceGenerator(name = "sequence", sequenceName = "document_workflow_attachments_document_workflow_attachments_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
	@Column(name="document_workflow_attachments_id")
	private Integer id;
	
	@Column(name="document_workflow_attachments_document_workflow_id")
	private Integer documentWorkflowId;
	
	@Column(name="document_workflow_attachments_filename")
	private String fileName;
	
	@Column(name="document_workflow_attachments_file_detail_id")
	private Integer fileDetailId;
	
	@Column(name="document_workflow_attachments_message_id")
	private String messageId;
	
	@Column(name="document_workflow_attachments_content_type")
	private String contentType;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Integer getFileDetailId() {
		return fileDetailId;
	}

	public void setFileDetailId(Integer fileDetailId) {
		this.fileDetailId = fileDetailId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDocumentWorkflowId() {
		return documentWorkflowId;
	}

	public void setDocumentWorkflowId(Integer documentWorkflowId) {
		this.documentWorkflowId = documentWorkflowId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	

}
