package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "sch_template_owner_mapping")
public class SchedulerTemplateOwnerMapping {

	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sch_template_owner_mapping_sch_template_owner_mapping_id_seq")
	@SequenceGenerator(name = "sch_template_owner_mapping_sch_template_owner_mapping_id_seq", sequenceName = "sch_template_owner_mapping_sch_template_owner_mapping_id_seq", allocationSize = 1)
	@Column(name="sch_template_owner_mapping_id")
	private Integer schTemplateOwnerMappingId;

	@Column(name="sch_template_owner_mapping_name")
	private String schTemplateOwnerMappingName;

	@Column(name="sch_template_owner_mapping_template_id")
	private Integer schTemplateOwnerMappingTemplateId;

	@Column(name="sch_template_owner_mapping_resource")
	private Integer schTemplateOwnerMappingResource;

	@Column(name="sch_template_owner_mapping_isactive")
	private boolean schTemplateOwnerMappingIsActive;

	public Integer getSchTemplateOwnerMappingId() {
		return schTemplateOwnerMappingId;
	}

	public void setSchTemplateOwnerMappingId(Integer schTemplateOwnerMappingId) {
		this.schTemplateOwnerMappingId = schTemplateOwnerMappingId;
	}

	public String getSchTemplateOwnerMappingName() {
		return schTemplateOwnerMappingName;
	}

	public void setSchTemplateOwnerMappingName(String schTemplateOwnerMappingName) {
		this.schTemplateOwnerMappingName = schTemplateOwnerMappingName;
	}

	public Integer getSchTemplateOwnerMappingTemplateId() {
		return schTemplateOwnerMappingTemplateId;
	}

	public void setSchTemplateOwnerMappingTemplateId(
			Integer schTemplateOwnerMappingTemplateId) {
		this.schTemplateOwnerMappingTemplateId = schTemplateOwnerMappingTemplateId;
	}

	public Integer getSchTemplateOwnerMappingResource() {
		return schTemplateOwnerMappingResource;
	}

	public void setSchTemplateOwnerMappingResource(
			Integer schTemplateOwnerMappingResource) {
		this.schTemplateOwnerMappingResource = schTemplateOwnerMappingResource;
	}

	public boolean isSchTemplateOwnerMappingIsActive() {
		return schTemplateOwnerMappingIsActive;
	}

	public void setSchTemplateOwnerMappingIsActive(
			boolean schTemplateOwnerMappingIsActive) {
		this.schTemplateOwnerMappingIsActive = schTemplateOwnerMappingIsActive;
	}
	
	
}
