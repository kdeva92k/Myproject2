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
@Table(name = "cnm_settings")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CnmSettings {

	@Id
	@SequenceGenerator(name="cnm_settings_cnm_settings_id_seq", sequenceName="cnm_settings_cnm_settings_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="cnm_settings_cnm_settings_id_seq")
	@Column(name="cnm_settings_id")
	private Integer cnmSettingsId;

	@Column(name="cnm_settings_name")
	private String cnmSettingsName;

	@Column(name="cnm_settings_isactive")
	private Boolean cnmSettingsIsactive;

	@Column(name="cnm_settings_saveastemplatetype")
	private Boolean cnmSettingsSaveastemplatetype;

	@Column(name="cnm_settings_assessmentdx_required")
	private Integer cnmSettingsAssessmentdxRequired;

	@Column(name="cnm_settings_value")
	private String cnmSettingsValue;

	public Integer getCnmSettingsId() {
		return cnmSettingsId;
	}

	public void setCnmSettingsId(Integer cnmSettingsId) {
		this.cnmSettingsId = cnmSettingsId;
	}

	public String getCnmSettingsName() {
		return cnmSettingsName;
	}

	public void setCnmSettingsName(String cnmSettingsName) {
		this.cnmSettingsName = cnmSettingsName;
	}

	public Boolean getCnmSettingsIsactive() {
		return cnmSettingsIsactive;
	}

	public void setCnmSettingsIsactive(Boolean cnmSettingsIsactive) {
		this.cnmSettingsIsactive = cnmSettingsIsactive;
	}

	public Boolean getCnmSettingsSaveastemplatetype() {
		return cnmSettingsSaveastemplatetype;
	}

	public void setCnmSettingsSaveastemplatetype(
			Boolean cnmSettingsSaveastemplatetype) {
		this.cnmSettingsSaveastemplatetype = cnmSettingsSaveastemplatetype;
	}

	public Integer getCnmSettingsAssessmentdxRequired() {
		return cnmSettingsAssessmentdxRequired;
	}

	public void setCnmSettingsAssessmentdxRequired(
			Integer cnmSettingsAssessmentdxRequired) {
		this.cnmSettingsAssessmentdxRequired = cnmSettingsAssessmentdxRequired;
	}

	public String getCnmSettingsValue() {
		return cnmSettingsValue;
	}

	public void setCnmSettingsValue(String cnmSettingsValue) {
		this.cnmSettingsValue = cnmSettingsValue;
	}
	
	
}