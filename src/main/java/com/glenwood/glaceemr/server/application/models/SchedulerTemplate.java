package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "sch_template")
public class SchedulerTemplate {

	@Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sch_template_sch_template_id_seq")
	@SequenceGenerator(name = "sch_template_sch_template_id_seq", sequenceName = "sch_template_sch_template_id_seq", allocationSize = 1)
	@Column(name="sch_template_id")
	private Integer schTemplateId;

	@Column(name="sch_template_name")
	private String schTemplateName;

	@Column(name="sch_template_schedule")
	private Integer schTemplateSchedule;

	@Column(name="sch_template_isactive")
	private boolean schTemplateIsActive;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sch_template_id", referencedColumnName="sch_template_owner_mapping_template_id", updatable=false, insertable=false)
	private SchedulerTemplateOwnerMapping schedulerTemplateOwnerMapping;
	

	public Integer getSchTemplateId() {
		return schTemplateId;
	}

	public void setSchTemplateId(Integer schTemplateId) {
		this.schTemplateId = schTemplateId;
	}

	public String getSchTemplateName() {
		return schTemplateName;
	}

	public void setSchTemplateName(String schTemplateName) {
		this.schTemplateName = schTemplateName;
	}

	public Integer getSchTemplateSchedule() {
		return schTemplateSchedule;
	}

	public void setSchTemplateSchedule(Integer schTemplateSchedule) {
		this.schTemplateSchedule = schTemplateSchedule;
	}

	public boolean isSchTemplateIsActive() {
		return schTemplateIsActive;
	}

	public void setSchTemplateIsActive(boolean schTemplateIsActive) {
		this.schTemplateIsActive = schTemplateIsActive;
	}

	public SchedulerTemplateOwnerMapping getSchTemplateOwnerMapping() {
		return schedulerTemplateOwnerMapping;
	}

	public void setSchTemplateOwnerMapping(
			SchedulerTemplateOwnerMapping schedulerTemplateOwnerMapping) {
		this.schedulerTemplateOwnerMapping = schedulerTemplateOwnerMapping;
	}
	
}
