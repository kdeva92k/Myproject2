package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "sch_date_template")
public class SchedulerDateTemplate implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sch_date_template_sch_date_template_id_seq")
	@SequenceGenerator(name ="sch_date_template_sch_date_template_id_seq", sequenceName="sch_date_template_sch_date_template_id_seq", allocationSize=1)
	@Column(name="sch_date_template_id")
	private Integer schDateTemplateId;

	@Column(name="sch_date_template_date")
	private Date schDateTemplateDate;

	@Column(name="sch_date_template_resource_id")
	private Integer schDateTemplateResourceId;
	
	@Column(name="sch_date_template_template_ids")
	private String schDateTemplateTemplateIds;

	public Integer getSchDateTemplateId() {
		return schDateTemplateId;
	}

	public void setSchDateTemplateId(Integer schDateTemplateId) {
		this.schDateTemplateId = schDateTemplateId;
	}

	public Date getSchDateTemplateDate() {
		return schDateTemplateDate;
	}

	public void setSchDateTemplateDate(Date schDateTemplateDate) {
		this.schDateTemplateDate = schDateTemplateDate;
	}

	public Integer getSchDateTemplateResourceId() {
		return schDateTemplateResourceId;
	}

	public void setSchDateTemplateResourceId(Integer schDateTemplateResourceId) {
		this.schDateTemplateResourceId = schDateTemplateResourceId;
	}

	public String getSchDateTemplateTemplateIds() {
		return schDateTemplateTemplateIds;
	}

	public void setSchDateTemplateTemplateIds(String schDateTemplateTemplateIds) {
		this.schDateTemplateTemplateIds = schDateTemplateTemplateIds;
	}
	
	
}