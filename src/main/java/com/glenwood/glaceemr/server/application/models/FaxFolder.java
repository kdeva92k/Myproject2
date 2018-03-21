package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "fax_folder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FaxFolder {
	@Id
	@Column(name="fax_folder_id")
	private Integer faxFolderId;

	@Column(name="fax_folder_priority")
	private Integer faxFolderPriority;

	@Column(name="fax_folder_name")
	private String faxFolderName;

	@Column(name="fax_folder_path")
	private String faxFolderPath;

	@Column(name="fax_folder_created_by")
	private Integer faxFolderCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="fax_folder_unused")
	private Timestamp faxFolderUnused;
	
	@OneToMany(mappedBy="faxFolder")
	@JsonManagedReference
	List<FaxInbox> fax_inbox;
	
	public Integer getFaxFolderId() {
		return faxFolderId;
	}

	public void setFaxFolderId(Integer faxFolderId) {
		this.faxFolderId = faxFolderId;
	}

	public Integer getFaxFolderPriority() {
		return faxFolderPriority;
	}
	

	public void setFaxFolderPriority(Integer faxFolderPriority) {
		this.faxFolderPriority = faxFolderPriority;
	}

	public String getFaxFolderName() {
		return faxFolderName;
	}

	public void setFaxFolderName(String faxFolderName) {
		this.faxFolderName = faxFolderName;
	}

	public String getFaxFolderPath() {
		return faxFolderPath;
	}

	public void setFaxFolderPath(String faxFolderPath) {
		this.faxFolderPath = faxFolderPath;
	}

	public Integer getFaxFolderCreatedBy() {
		return faxFolderCreatedBy;
	}

	public void setFaxFolderCreatedBy(Integer faxFolderCreatedBy) {
		this.faxFolderCreatedBy = faxFolderCreatedBy;
	}

	public Timestamp getFaxFolderUnused() {
		return faxFolderUnused;
	}

	public void setFaxFolderUnused(Timestamp faxFolderUnused) {
		this.faxFolderUnused = faxFolderUnused;
	}
		
}
