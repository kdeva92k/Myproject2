package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "patient_portal_feature_config")
public class PatientPortalFeatureConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_portal_feature_config_idseq")
	@SequenceGenerator(name ="patient_portal_feature_config_idseq", sequenceName="patient_portal_feature_config_idseq", allocationSize=1)
	@Column(name="patient_portal_feature_config_id")
	private Integer patientPortalFeatureConfigId;

	@Column(name="patient_portal_feature_config_name")
	private String patientPortalFeatureConfigName;

	@Column(name="patient_portal_feature_config_desc")
	private String patientPortalFeatureConfigDesc;

	@Column(name="patient_portal_feature_config_isdesktopenabled")
	private Boolean patientPortalFeatureConfigIsdesktopenabled;

	@Column(name="patient_portal_feature_config_istabletenabled")
	private Boolean patientPortalFeatureConfigIstabletenabled;

	@Column(name="patient_portal_feature_config_ismobileenabled")
	private Boolean patientPortalFeatureConfigIsmobileenabled;

	@Column(name="patient_portal_feature_config_isactive")
	private Boolean patientPortalFeatureConfigIsactive;

	public Integer getPatientPortalFeatureConfigId() {
		return patientPortalFeatureConfigId;
	}

	public void setPatientPortalFeatureConfigId(Integer patientPortalFeatureConfigId) {
		this.patientPortalFeatureConfigId = patientPortalFeatureConfigId;
	}

	public String getPatientPortalFeatureConfigName() {
		return patientPortalFeatureConfigName;
	}

	public void setPatientPortalFeatureConfigName(String patientPortalFeatureConfigName) {
		this.patientPortalFeatureConfigName = patientPortalFeatureConfigName;
	}

	public String getPatientPortalFeatureConfigDesc() {
		return patientPortalFeatureConfigDesc;
	}

	public void setPatientPortalFeatureConfigDesc(String patientPortalFeatureConfigDesc) {
		this.patientPortalFeatureConfigDesc = patientPortalFeatureConfigDesc;
	}

	public Boolean getPatientPortalFeatureConfigIsdesktopenabled() {
		return patientPortalFeatureConfigIsdesktopenabled;
	}

	public void setPatientPortalFeatureConfigIsdesktopenabled(Boolean patientPortalFeatureConfigIsdesktopenabled) {
		this.patientPortalFeatureConfigIsdesktopenabled = patientPortalFeatureConfigIsdesktopenabled;
	}

	public Boolean getPatientPortalFeatureConfigIstabletenabled() {
		return patientPortalFeatureConfigIstabletenabled;
	}

	public void setPatientPortalFeatureConfigIstabletenabled(Boolean patientPortalFeatureConfigIstabletenabled) {
		this.patientPortalFeatureConfigIstabletenabled = patientPortalFeatureConfigIstabletenabled;
	}

	public Boolean getPatientPortalFeatureConfigIsmobileenabled() {
		return patientPortalFeatureConfigIsmobileenabled;
	}

	public void setPatientPortalFeatureConfigIsmobileenabled(Boolean patientPortalFeatureConfigIsmobileenabled) {
		this.patientPortalFeatureConfigIsmobileenabled = patientPortalFeatureConfigIsmobileenabled;
	}

	public Boolean getPatientPortalFeatureConfigIsactive() {
		return patientPortalFeatureConfigIsactive;
	}

	public void setPatientPortalFeatureConfigIsactive(Boolean patientPortalFeatureConfigIsactive) {
		this.patientPortalFeatureConfigIsactive = patientPortalFeatureConfigIsactive;
	}
	
}