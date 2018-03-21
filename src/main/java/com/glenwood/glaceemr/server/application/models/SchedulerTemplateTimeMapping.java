package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "sch_template_time_mapping")
public class SchedulerTemplateTimeMapping implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4032091034773651981L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sch_template_time_mapping_sch_template_time_mapping_id_seq")
	@SequenceGenerator(name = "sch_template_time_mapping_sch_template_time_mapping_id_seq", sequenceName = "sch_template_time_mapping_sch_template_time_mapping_id_seq", allocationSize = 1)
	@Column(name="sch_template_time_mapping_id")
	private Integer schTemplateTimeMappingId;

	@Column(name="sch_template_time_mapping_template_id")
	private Integer schTemplateTimeMappingTemplateId;
	
	@NotNull
	@Column(name="sch_template_time_mapping_template_detail_id")
	private Integer schTemplateTimeMappingTemplateDetailId;
	

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="sch_template_time_mapping_template_detail_id",referencedColumnName="sch_template_detail_id", insertable=false, updatable=false)
	private SchedulerTemplateDetail schedulerTemplateDetail;
	

	public Integer getSchTemplateTimeMappingId() {
		return schTemplateTimeMappingId;
	}

	public void setSchTemplateTimeMappingId(Integer schTemplateTimeMappingId) {
		this.schTemplateTimeMappingId = schTemplateTimeMappingId;
	}

	public Integer getSchTemplateTimeMappingTemplateId() {
		return schTemplateTimeMappingTemplateId;
	}

	public void setSchTemplateTimeMappingTemplateId(
			Integer schTemplateTimeMappingTemplateId) {
		this.schTemplateTimeMappingTemplateId = schTemplateTimeMappingTemplateId;
	}

	public Integer getSchTemplateTimeMappingTemplateDetailId() {
		return schTemplateTimeMappingTemplateDetailId;
	}

	public void setSchTemplateTimeMappingTemplateDetailId(
			Integer schTemplateTimeMappingTemplateDetailId) {
		this.schTemplateTimeMappingTemplateDetailId = schTemplateTimeMappingTemplateDetailId;
	}
	
}
