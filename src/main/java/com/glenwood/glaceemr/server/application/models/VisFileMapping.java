package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vis_file_mapping")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VisFileMapping {
	
	@Id
	@Column(name="vis_file_mapping_id")
	private Integer visFileMappingId;

	@Column(name="vis_file_mapping_vis_id")
	private Integer visFileMappingVisId;

	@Column(name="vis_file_mapping_file_name")
	private String visFileMappingFileName;

	@Column(name="vis_file_mapping_language_code")
	private String visFileMappingLanguageCode;

	@Column(name="vis_file_mapping_status")
	private String visFileMappingStatus;
	
	public String getVisFileMappingStatus() {
		return visFileMappingStatus;
	}

	public void setVisFileMappingStatus(String visFileMappingStatus) {
		this.visFileMappingStatus = visFileMappingStatus;
	}

	public Integer getVisFileMappingId() {
		return visFileMappingId;
	}

	public void setVisFileMappingId(Integer visFileMappingId) {
		this.visFileMappingId = visFileMappingId;
	}

	public Integer getVisFileMappingVisId() {
		return visFileMappingVisId;
	}

	public void setVisFileMappingVisId(Integer visFileMappingVisId) {
		this.visFileMappingVisId = visFileMappingVisId;
	}

	public String getVisFileMappingFileName() {
		return visFileMappingFileName;
	}

	public void setVisFileMappingFileName(String visFileMappingFileName) {
		this.visFileMappingFileName = visFileMappingFileName;
	}

	public String getVisFileMappingLanguageCode() {
		return visFileMappingLanguageCode;
	}

	public void setVisFileMappingLanguageCode(String visFileMappingLanguageCode) {
		this.visFileMappingLanguageCode = visFileMappingLanguageCode;
	}
}