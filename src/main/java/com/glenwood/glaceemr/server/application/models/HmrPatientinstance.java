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
@Table(name = "hmr_patientinstance")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HmrPatientinstance {
	@Id
	@Column(name="id", nullable=false)
	@SequenceGenerator(name="hmr_patientinstance_id_seq", sequenceName="hmr_patientinstance_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="hmr_patientinstance_id_seq")
	private Integer hmrPatientinstanceId;

	@Column(name="instance_id")
	private Integer hmrPatientinstanceInstanceId;
	
	@Column(name="test_id")
	private Integer hmrPatientinstanceTestId;
	
	@Column(name="from_duration")
	private Integer hmrPatientinstanceFromDuration;
	
	@Column(name="to_duration")
	private Integer hmrPatientinstanceToDuration;
	
	@Column(name="duration_type")
	private Integer hmrPatientinstanceDurationType;
	
	@Column(name="preferred_age")
	private Integer hmrPatientinstancePreferredAge;
	
	@Column(name="sortby")
	private Integer hmrPatientinstanceSortby;
	
	@Column(name="isactive")
	private Integer hmrPatientinstanceIsactive;
	
	@Column(name="schedule")
	private Integer hmrPatientinstanceSchedule;
	
	@Column(name="schedule_type")
	private Integer hmrPatientinstanceScheduleType;
	
	@Column(name="patient_id")
	private Integer hmrPatientinstancePatientId;
	
	@Column(name="chart_id")
	private Integer hmrPatientinstanceChartId;
	
	@Column(name="description", length=100)
	private String hmrPatientinstanceDescription;

	public Integer getHmrPatientinstanceId() {
		return hmrPatientinstanceId;
	}

	public void setHmrPatientinstanceId(Integer hmrPatientinstanceId) {
		this.hmrPatientinstanceId = hmrPatientinstanceId;
	}

	public Integer getHmrPatientinstanceInstanceId() {
		return hmrPatientinstanceInstanceId;
	}

	public void setHmrPatientinstanceInstanceId(Integer hmrPatientinstanceInstanceId) {
		this.hmrPatientinstanceInstanceId = hmrPatientinstanceInstanceId;
	}

	public Integer getHmrPatientinstanceTestId() {
		return hmrPatientinstanceTestId;
	}

	public void setHmrPatientinstanceTestId(Integer hmrPatientinstanceTestId) {
		this.hmrPatientinstanceTestId = hmrPatientinstanceTestId;
	}

	public Integer getHmrPatientinstanceFromDuration() {
		return hmrPatientinstanceFromDuration;
	}

	public void setHmrPatientinstanceFromDuration(
			Integer hmrPatientinstanceFromDuration) {
		this.hmrPatientinstanceFromDuration = hmrPatientinstanceFromDuration;
	}

	public Integer getHmrPatientinstanceToDuration() {
		return hmrPatientinstanceToDuration;
	}

	public void setHmrPatientinstanceToDuration(Integer hmrPatientinstanceToDuration) {
		this.hmrPatientinstanceToDuration = hmrPatientinstanceToDuration;
	}

	public Integer getHmrPatientinstanceDurationType() {
		return hmrPatientinstanceDurationType;
	}

	public void setHmrPatientinstanceDurationType(
			Integer hmrPatientinstanceDurationType) {
		this.hmrPatientinstanceDurationType = hmrPatientinstanceDurationType;
	}

	public Integer getHmrPatientinstancePreferredAge() {
		return hmrPatientinstancePreferredAge;
	}

	public void setHmrPatientinstancePreferredAge(
			Integer hmrPatientinstancePreferredAge) {
		this.hmrPatientinstancePreferredAge = hmrPatientinstancePreferredAge;
	}

	public Integer getHmrPatientinstanceSortby() {
		return hmrPatientinstanceSortby;
	}

	public void setHmrPatientinstanceSortby(Integer hmrPatientinstanceSortby) {
		this.hmrPatientinstanceSortby = hmrPatientinstanceSortby;
	}

	public Integer getHmrPatientinstanceIsactive() {
		return hmrPatientinstanceIsactive;
	}

	public void setHmrPatientinstanceIsactive(Integer hmrPatientinstanceIsactive) {
		this.hmrPatientinstanceIsactive = hmrPatientinstanceIsactive;
	}

	public Integer getHmrPatientinstanceSchedule() {
		return hmrPatientinstanceSchedule;
	}

	public void setHmrPatientinstanceSchedule(Integer hmrPatientinstanceSchedule) {
		this.hmrPatientinstanceSchedule = hmrPatientinstanceSchedule;
	}

	public Integer getHmrPatientinstanceScheduleType() {
		return hmrPatientinstanceScheduleType;
	}

	public void setHmrPatientinstanceScheduleType(
			Integer hmrPatientinstanceScheduleType) {
		this.hmrPatientinstanceScheduleType = hmrPatientinstanceScheduleType;
	}

	public Integer getHmrPatientinstancePatientId() {
		return hmrPatientinstancePatientId;
	}

	public void setHmrPatientinstancePatientId(Integer hmrPatientinstancePatientId) {
		this.hmrPatientinstancePatientId = hmrPatientinstancePatientId;
	}

	public Integer getHmrPatientinstanceChartId() {
		return hmrPatientinstanceChartId;
	}

	public void setHmrPatientinstanceChartId(Integer hmrPatientinstanceChartId) {
		this.hmrPatientinstanceChartId = hmrPatientinstanceChartId;
	}

	public String getHmrPatientinstanceDescription() {
		return hmrPatientinstanceDescription;
	}

	public void setHmrPatientinstanceDescription(
			String hmrPatientinstanceDescription) {
		this.hmrPatientinstanceDescription = hmrPatientinstanceDescription;
	}

}
