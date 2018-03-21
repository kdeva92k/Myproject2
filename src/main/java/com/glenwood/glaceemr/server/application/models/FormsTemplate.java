package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "forms_template")
public class FormsTemplate {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="forms_template_forms_template_id_seq")
	@SequenceGenerator(name ="forms_template_forms_template_id_seq", sequenceName="forms_template_forms_template_id_seq", allocationSize=1)
	@Column(name="forms_template_id")
	private Integer formsTemplateId;

	@Column(name="forms_template_name")
	private String formsTemplateName;

	@Column(name="forms_template_filename")
	private String formsTemplateFilename;

	@Column(name="forms_template_type")
	private Integer formsTemplateType;

	@Column(name="forms_template_thumbnail")
	private String formsTemplateThumbnail;

	@Column(name="forms_template_isactive")
	private Boolean formsTemplateIsactive;

	public Integer getFormsTemplateId() {
		return formsTemplateId;
	}

	public void setFormsTemplateId(Integer formsTemplateId) {
		this.formsTemplateId = formsTemplateId;
	}

	public String getFormsTemplateName() {
		return formsTemplateName;
	}

	public void setFormsTemplateName(String formsTemplateName) {
		this.formsTemplateName = formsTemplateName;
	}

	public String getFormsTemplateFilename() {
		return formsTemplateFilename;
	}

	public void setFormsTemplateFilename(String formsTemplateFilename) {
		this.formsTemplateFilename = formsTemplateFilename;
	}

	public Integer getFormsTemplateType() {
		return formsTemplateType;
	}

	public void setFormsTemplateType(Integer formsTemplateType) {
		this.formsTemplateType = formsTemplateType;
	}

	public String getFormsTemplateThumbnail() {
		return formsTemplateThumbnail;
	}

	public void setFormsTemplateThumbnail(String formsTemplateThumbnail) {
		this.formsTemplateThumbnail = formsTemplateThumbnail;
	}

	public Boolean getFormsTemplateIsactive() {
		return formsTemplateIsactive;
	}

	public void setFormsTemplateIsactive(Boolean formsTemplateIsactive) {
		this.formsTemplateIsactive = formsTemplateIsactive;
	}
	
}