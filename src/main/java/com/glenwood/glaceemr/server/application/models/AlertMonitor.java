package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "alert_monitor")
public class AlertMonitor implements Serializable {

	@Id
	@Column(name="alert_monitor_id")
	private Integer alertMonitorId;

	@Column(name="alert_monitor_option_type")
	private String alertMonitorOptionType;

	@Column(name="alert_monitor_option_value")
	private Integer alertMonitorOptionValue;
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="alert_monitor_option_value",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	@JsonBackReference
	EmployeeProfile empProfile;
	
	
	public Integer getAlertMonitorId() {
		return alertMonitorId;
	}

	public void setAlertMonitorId(Integer alertMonitorId) {
		this.alertMonitorId = alertMonitorId;
	}

	public String getAlertMonitorOptionType() {
		return alertMonitorOptionType;
	}

	public void setAlertMonitorOptionType(String alertMonitorOptionType) {
		this.alertMonitorOptionType = alertMonitorOptionType;
	}

	public Integer getAlertMonitorOptionValue() {
		return alertMonitorOptionValue;
	}

	public void setAlertMonitorOptionValue(Integer alertMonitorOptionValue) {
		this.alertMonitorOptionValue = alertMonitorOptionValue;
	}

}