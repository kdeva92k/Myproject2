package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "patient_portal_menu_config")
public class PatientPortalMenuConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_portal_menu_config_idseq")
	@SequenceGenerator(name ="patient_portal_menu_config_idseq", sequenceName="patient_portal_menu_config_idseq", allocationSize=1)
	@Column(name="patient_portal_menu_config_id")
	private Integer patientPortalMenuConfigId;

	@Column(name="patient_portal_menu_config_name")
	private String patientPortalMenuConfigName;

	@Column(name="patient_portal_menu_config_desc")
	private String patientPortalMenuConfigDesc;

	@Column(name="patient_portal_menu_config_order")
	private Integer patientPortalMenuConfigOrder;

	@Column(name="patient_portal_menu_config_isdesktopenabled")
	private Boolean patientPortalMenuConfigIsdesktopenabled;

	@Column(name="patient_portal_menu_config_istabletenabled")
	private Boolean patientPortalMenuConfigIstabletenabled;

	@Column(name="patient_portal_menu_config_ismobileenabled")
	private Boolean patientPortalMenuConfigIsmobileenabled;

	@Column(name="patient_portal_menu_config_isactive")
	private Boolean patientPortalMenuConfigIsactive;

	public Integer getPatientPortalMenuConfigId() {
		return patientPortalMenuConfigId;
	}

	public void setPatientPortalMenuConfigId(Integer patientPortalMenuConfigId) {
		this.patientPortalMenuConfigId = patientPortalMenuConfigId;
	}

	public String getPatientPortalMenuConfigName() {
		return patientPortalMenuConfigName;
	}

	public void setPatientPortalMenuConfigName(String patientPortalMenuConfigName) {
		this.patientPortalMenuConfigName = patientPortalMenuConfigName;
	}

	public String getPatientPortalMenuConfigDesc() {
		return patientPortalMenuConfigDesc;
	}

	public void setPatientPortalMenuConfigDesc(String patientPortalMenuConfigDesc) {
		this.patientPortalMenuConfigDesc = patientPortalMenuConfigDesc;
	}

	public Integer getPatientPortalMenuConfigOrder() {
		return patientPortalMenuConfigOrder;
	}

	public void setPatientPortalMenuConfigOrder(Integer patientPortalMenuConfigOrder) {
		this.patientPortalMenuConfigOrder = patientPortalMenuConfigOrder;
	}

	public Boolean getPatientPortalMenuConfigIsdesktopenabled() {
		return patientPortalMenuConfigIsdesktopenabled;
	}

	public void setPatientPortalMenuConfigIsdesktopenabled(
			Boolean patientPortalMenuConfigIsdesktopenabled) {
		this.patientPortalMenuConfigIsdesktopenabled = patientPortalMenuConfigIsdesktopenabled;
	}

	public Boolean getPatientPortalMenuConfigIstabletenabled() {
		return patientPortalMenuConfigIstabletenabled;
	}

	public void setPatientPortalMenuConfigIstabletenabled(
			Boolean patientPortalMenuConfigIstabletenabled) {
		this.patientPortalMenuConfigIstabletenabled = patientPortalMenuConfigIstabletenabled;
	}

	public Boolean getPatientPortalMenuConfigIsmobileenabled() {
		return patientPortalMenuConfigIsmobileenabled;
	}

	public void setPatientPortalMenuConfigIsmobileenabled(
			Boolean patientPortalMenuConfigIsmobileenabled) {
		this.patientPortalMenuConfigIsmobileenabled = patientPortalMenuConfigIsmobileenabled;
	}

	public Boolean getPatientPortalMenuConfigIsactive() {
		return patientPortalMenuConfigIsactive;
	}

	public void setPatientPortalMenuConfigIsactive(
			Boolean patientPortalMenuConfigIsactive) {
		this.patientPortalMenuConfigIsactive = patientPortalMenuConfigIsactive;
	}
	
}