package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "sch_template_detail")
public class SchedulerTemplateDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sch_template_detail_sch_template_detail_id_seq")
	@SequenceGenerator(name = "sch_template_detail_sch_template_detail_id_seq", sequenceName = "sch_template_detail_sch_template_detail_id_seq", allocationSize = 1)
	@Column(name="sch_template_detail_id")
	private Integer schTemplateDetailId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="sch_template_detail_starttime")
	private Timestamp schTemplateDetailStarttime;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="sch_template_detail_endtime")
	private Timestamp schTemplateDetailEndtime;

	@Column(name="sch_template_detail_interval")
	private Integer schTemplateDetailInterval;

	@Column(name="sch_template_detail_appt_type")
	private Integer schTemplateDetailApptType;

	@Column(name="sch_template_detail_secowner_id")
	private Integer schTemplateDetailSecownerId;

	@Column(name="sch_template_detail_islocked")
	private Boolean schTemplateDetailIslocked;

	@Column(name="sch_template_detail_notes")
	private String schTemplateDetailNotes;
	
	@Column(name="sch_template_detail_samedayslot")
	private Integer schTemplateDetailSameDaySlot;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="sch_template_detail_secowner_id", referencedColumnName="sch_resource_id", insertable=false, updatable=false)
	private SchedulerResource schApptLocation;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="sch_template_detail_secowner_id", referencedColumnName="sch_resource_id", insertable=false, updatable=false)
	private SchedulerResource schApptDoctor;

	public Integer getSchTemplateDetailId() {
		return schTemplateDetailId;
	}

	public void setSchTemplateDetailId(Integer schTemplateDetailId) {
		this.schTemplateDetailId = schTemplateDetailId;
	}

	public Timestamp getSchTemplateDetailStarttime() {
		return schTemplateDetailStarttime;
	}

	public void setSchTemplateDetailStarttime(Timestamp schTemplateDetailStarttime) {
		this.schTemplateDetailStarttime = schTemplateDetailStarttime;
	}

	public Timestamp getSchTemplateDetailEndtime() {
		return schTemplateDetailEndtime;
	}

	public void setSchTemplateDetailEndtime(Timestamp schTemplateDetailEndtime) {
		this.schTemplateDetailEndtime = schTemplateDetailEndtime;
	}

	public Integer getSchTemplateDetailInterval() {
		return schTemplateDetailInterval;
	}

	public void setSchTemplateDetailInterval(Integer schTemplateDetailInterval) {
		this.schTemplateDetailInterval = schTemplateDetailInterval;
	}

	public Integer getSchTemplateDetailApptType() {
		return schTemplateDetailApptType;
	}

	public void setSchTemplateDetailApptType(Integer schTemplateDetailApptType) {
		this.schTemplateDetailApptType = schTemplateDetailApptType;
	}

	public Integer getSchTemplateDetailSecownerId() {
		return schTemplateDetailSecownerId;
	}

	public void setSchTemplateDetailSecownerId(Integer schTemplateDetailSecownerId) {
		this.schTemplateDetailSecownerId = schTemplateDetailSecownerId;
	}

	public Boolean getSchTemplateDetailIslocked() {
		return schTemplateDetailIslocked;
	}

	public void setSchTemplateDetailIslocked(Boolean schTemplateDetailIslocked) {
		this.schTemplateDetailIslocked = schTemplateDetailIslocked;
	}

	public String getSchTemplateDetailNotes() {
		return schTemplateDetailNotes;
	}

	public void setSchTemplateDetailNotes(String schTemplateDetailNotes) {
		this.schTemplateDetailNotes = schTemplateDetailNotes;
	}

	public Integer getSchTemplateDetailSameDaySlot() {
		return schTemplateDetailSameDaySlot;
	}

	public void setSchTemplateDetailSameDaySlot(Integer schTemplateDetailSameDaySlot) {
		this.schTemplateDetailSameDaySlot = schTemplateDetailSameDaySlot;
	}

	public SchedulerResource getSchApptLocation() {
		return schApptLocation;
	}

	public void setSchApptLocation(SchedulerResource schApptLocation) {
		this.schApptLocation = schApptLocation;
	}

	public SchedulerResource getSchApptDoctor() {
		return schApptDoctor;
	}

	public void setSchApptDoctor(SchedulerResource schApptDoctor) {
		this.schApptDoctor = schApptDoctor;
	}

}