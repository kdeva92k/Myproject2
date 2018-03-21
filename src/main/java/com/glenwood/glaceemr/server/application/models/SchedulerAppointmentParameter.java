package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "sch_appt_parameter")
public class SchedulerAppointmentParameter implements Serializable{

	@Id
	@Column(name="sch_appt_parameter_id")
	private Integer schApptParameterId;

	@Column(name="sch_appt_parameter_appt_id")
	private Integer schApptParameterApptId;

	@Column(name="sch_appt_parameter_type")
	private Integer schApptParameterType;

	@Column(name="sch_appt_parameter_value_id")
	private Integer schApptParameterValueId;

	@Column(name="sch_appt_parameter_isactive")
	private Boolean schApptParameterIsactive;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="sch_appt_parameter_on")
	private Timestamp schApptParameterOn;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="sch_appt_parameter_value_id",referencedColumnName="App_Reference_Values_statusId",insertable=false,updatable=false)
	AppReferenceValues App_Reference_ValuesReason;

	public Integer getSchApptParameterId() {
		return schApptParameterId;
	}

	public void setSchApptParameterId(Integer schApptParameterId) {
		this.schApptParameterId = schApptParameterId;
	}

	public Integer getSchApptParameterApptId() {
		return schApptParameterApptId;
	}

	public void setSchApptParameterApptId(Integer schApptParameterApptId) {
		this.schApptParameterApptId = schApptParameterApptId;
	}

	public Integer getSchApptParameterType() {
		return schApptParameterType;
	}

	public void setSchApptParameterType(Integer schApptParameterType) {
		this.schApptParameterType = schApptParameterType;
	}

	public Integer getSchApptParameterValueId() {
		return schApptParameterValueId;
	}

	public void setSchApptParameterValueId(Integer schApptParameterValueId) {
		this.schApptParameterValueId = schApptParameterValueId;
	}

	public Boolean getSchApptParameterIsactive() {
		return schApptParameterIsactive;
	}

	public void setSchApptParameterIsactive(Boolean schApptParameterIsactive) {
		this.schApptParameterIsactive = schApptParameterIsactive;
	}

	public Timestamp getSchApptParameterOn() {
		return schApptParameterOn;
	}

	public void setSchApptParameterOn(Timestamp schApptParameterOn) {
		this.schApptParameterOn = schApptParameterOn;
	}
}