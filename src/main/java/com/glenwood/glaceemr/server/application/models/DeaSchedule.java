package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dea_schedule")
public class DeaSchedule {

	@Id
	@Column(name="dea_schedule_id")
	private Integer deaScheduleId;

	@Column(name="dea_schedule_value")
	private Integer deaScheduleValue;

	@Column(name="dea_schedule_qualifier")
	private String deaScheduleQualifier;

	public Integer getDeaScheduleId() {
		return deaScheduleId;
	}

	public void setDeaScheduleId(Integer deaScheduleId) {
		this.deaScheduleId = deaScheduleId;
	}

	public Integer getDeaScheduleValue() {
		return deaScheduleValue;
	}

	public void setDeaScheduleValue(Integer deaScheduleValue) {
		this.deaScheduleValue = deaScheduleValue;
	}

	public String getDeaScheduleQualifier() {
		return deaScheduleQualifier;
	}

	public void setDeaScheduleQualifier(String deaScheduleQualifier) {
		this.deaScheduleQualifier = deaScheduleQualifier;
	}
}