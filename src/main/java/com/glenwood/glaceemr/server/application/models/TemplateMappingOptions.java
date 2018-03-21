package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "template_mapping_options")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateMappingOptions {

	@Id
	@Column(name="template_mapping_options_id")
	private Integer templateMappingOptionsId;

	@Column(name="template_mapping_options_mapid")
	private Integer templateMappingOptionsMapid;

	@Column(name="template_mapping_options_option_value")
	private Integer templateMappingOptionsOptionValue;

	@Column(name="template_mapping_options_default")
	private Boolean templateMappingOptionsDefault;

	@Column(name="template_mapping_options_isactive")
	private Boolean templateMappingOptionsIsactive;

	@Column(name="template_mapping_options_orderby")
	private Integer templateMappingOptionsOrderby;

	@Column(name="template_mapping_options_isfocal")
	private Boolean templateMappingOptionsIsfocal;

	public Integer getTemplateMappingOptionsId() {
		return templateMappingOptionsId;
	}

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "template_mapping_options_mapid", referencedColumnName = "clinical_element_template_mapping_id", insertable = false, updatable = false)
	private ClinicalElementTemplateMapping clinicalElementTemplateMapping;
	
	
	
	public void setTemplateMappingOptionsId(Integer templateMappingOptionsId) {
		this.templateMappingOptionsId = templateMappingOptionsId;
	}

	public Integer getTemplateMappingOptionsMapid() {
		return templateMappingOptionsMapid;
	}

	public void setTemplateMappingOptionsMapid(Integer templateMappingOptionsMapid) {
		this.templateMappingOptionsMapid = templateMappingOptionsMapid;
	}

	public Integer getTemplateMappingOptionsOptionValue() {
		return templateMappingOptionsOptionValue;
	}

	public void setTemplateMappingOptionsOptionValue(
			Integer templateMappingOptionsOptionValue) {
		this.templateMappingOptionsOptionValue = templateMappingOptionsOptionValue;
	}

	public Boolean getTemplateMappingOptionsDefault() {
		return templateMappingOptionsDefault;
	}

	public void setTemplateMappingOptionsDefault(
			Boolean templateMappingOptionsDefault) {
		this.templateMappingOptionsDefault = templateMappingOptionsDefault;
	}

	public Boolean getTemplateMappingOptionsIsactive() {
		return templateMappingOptionsIsactive;
	}

	public void setTemplateMappingOptionsIsactive(
			Boolean templateMappingOptionsIsactive) {
		this.templateMappingOptionsIsactive = templateMappingOptionsIsactive;
	}

	public Integer getTemplateMappingOptionsOrderby() {
		return templateMappingOptionsOrderby;
	}

	public void setTemplateMappingOptionsOrderby(
			Integer templateMappingOptionsOrderby) {
		this.templateMappingOptionsOrderby = templateMappingOptionsOrderby;
	}

	public Boolean getTemplateMappingOptionsIsfocal() {
		return templateMappingOptionsIsfocal;
	}

	public void setTemplateMappingOptionsIsfocal(
			Boolean templateMappingOptionsIsfocal) {
		this.templateMappingOptionsIsfocal = templateMappingOptionsIsfocal;
	}
	
	
}