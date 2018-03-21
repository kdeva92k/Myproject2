package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "aftercare_ins_language_mapping")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AftercareInsLanguageMapping {

	@Id
	@Column(name="aftercare_ins_language_mapping_id")
	private Integer aftercareInsLanguageMappingId;

	@Column(name="aftercare_ins_language_mapping_insid")
	private Integer aftercareInsLanguageMappingInsid;

	@Column(name="aftercare_ins_language_mapping_languageid")
	private Integer aftercareInsLanguageMappingLanguageid;

	public Integer getAftercareInsLanguageMappingId() {
		return aftercareInsLanguageMappingId;
	}

	public void setAftercareInsLanguageMappingId(
			Integer aftercareInsLanguageMappingId) {
		this.aftercareInsLanguageMappingId = aftercareInsLanguageMappingId;
	}

	public Integer getAftercareInsLanguageMappingInsid() {
		return aftercareInsLanguageMappingInsid;
	}

	public void setAftercareInsLanguageMappingInsid(
			Integer aftercareInsLanguageMappingInsid) {
		this.aftercareInsLanguageMappingInsid = aftercareInsLanguageMappingInsid;
	}

	public Integer getAftercareInsLanguageMappingLanguageid() {
		return aftercareInsLanguageMappingLanguageid;
	}

	public void setAftercareInsLanguageMappingLanguageid(
			Integer aftercareInsLanguageMappingLanguageid) {
		this.aftercareInsLanguageMappingLanguageid = aftercareInsLanguageMappingLanguageid;
	}
	
}