package com.glenwood.glaceemr.server.application.services.scheduler;

import java.util.Date;

public class SchedulerTemplateDetailBean {

	public SchedulerTemplateDetailBean(Integer schTemplateDetailId,
			Date schTemplateDetailStarttime,
			Date schTemplateDetailEndtime,
			Integer schTemplateDetailInterval,
			Integer schTemplateDetailApptType,
			Integer schTemplateDetailSecownerId,
			Boolean schTemplateDetailIslocked,
			String schTemplateDetailNotes,
			Integer schTemplateDetailSameDaySlot) {
		super();
		this.schTemplateDetailId = schTemplateDetailId;
		this.schTemplateDetailStarttime = schTemplateDetailStarttime;
		this.schTemplateDetailEndtime = schTemplateDetailEndtime;
		this.schTemplateDetailInterval = schTemplateDetailInterval;
		this.schTemplateDetailApptType = schTemplateDetailApptType;
		this.schTemplateDetailSecownerId = schTemplateDetailSecownerId;
		this.schTemplateDetailIslocked = schTemplateDetailIslocked;
		this.schTemplateDetailNotes = schTemplateDetailNotes;
		this.schTemplateDetailSameDaySlot = schTemplateDetailSameDaySlot;
	}

	private Integer schTemplateDetailId;

	private Date schTemplateDetailStarttime;

	private Date schTemplateDetailEndtime;

	private Integer schTemplateDetailInterval;

	private Integer schTemplateDetailApptType;

	private Integer schTemplateDetailSecownerId;

	private Boolean schTemplateDetailIslocked;

	private String schTemplateDetailNotes;
	
	private Integer schTemplateDetailSameDaySlot;
	
	public Integer getSchTemplateDetailId() {
		return schTemplateDetailId;
	}

	public void setSchTemplateDetailId(Integer schTemplateDetailId) {
		this.schTemplateDetailId = schTemplateDetailId;
	}

	public Date getSchTemplateDetailStarttime() {
		return schTemplateDetailStarttime;
	}

	public void setSchTemplateDetailStarttime(Date schTemplateDetailStarttime) {
		this.schTemplateDetailStarttime = schTemplateDetailStarttime;
	}

	public Date getSchTemplateDetailEndtime() {
		return schTemplateDetailEndtime;
	}

	public void setSchTemplateDetailEndtime(Date schTemplateDetailEndtime) {
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
	
}
