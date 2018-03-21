package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "workflow")
public class Workflow implements Serializable{

	@Id
	@SequenceGenerator(name = "sequence", sequenceName = "workflow_workflow_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
	@Column(name="workflow_id")
	private Integer workflowId;

	@Column(name="workflow_patientid")
	private Integer workflowPatientid;

	@Column(name="workflow_encounterid")
	private Integer workflowEncounterid;

	@Column(name="workflow_chartid")
	private Integer workflowChartid;

	@Column(name="workflow_roomid")
	private Integer workflowRoomid;

	@Column(name="workflow_fromid")
	private Integer workflowFromid;

	@Column(name="workflow_toid")
	private Integer workflowToid;

	@Column(name="workflow_message")
	private String workflowMessage;

	@Column(name="workflow_statusid")
	private Integer workflowStatus;

	@Column(name="workflow_isactive")
	private Boolean workflowIsactive;

	@Column(name="workflow_ishighpriority")
	private Boolean workflowIshighpriority;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="workflow_starttime")
	private Timestamp workflowStarttime;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="workflow_endtime")
	private Timestamp workflowEndtime;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="workflow_fromid",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	EmployeeProfile empProfileTableFromId;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="workflow_toid",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	EmployeeProfile empProfileTableToId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="workflow_patientid",referencedColumnName="patient_registration_id",insertable=false,updatable=false)
	PatientRegistration patRegPatientId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="workflow_roomid",referencedColumnName="room_details_id",insertable=false,updatable=false)
	Room roomTableRoomId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="workflow_statusid",referencedColumnName="alert_category_id",insertable=false,updatable=false)
	AlertCategory alertCategoryName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="workflow_encounterid",referencedColumnName="encounter_id",insertable=false,updatable=false)
	Encounter encounter;

	public Integer getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
	}

	public Integer getWorkflowPatientid() {
		return workflowPatientid;
	}

	public void setWorkflowPatientid(Integer workflowPatientid) {
		this.workflowPatientid = workflowPatientid;
	}

	public Integer getWorkflowEncounterid() {
		return workflowEncounterid;
	}

	public void setWorkflowEncounterid(Integer workflowEncounterid) {
		this.workflowEncounterid = workflowEncounterid;
	}

	public Integer getWorkflowChartid() {
		return workflowChartid;
	}

	public void setWorkflowChartid(Integer workflowChartid) {
		this.workflowChartid = workflowChartid;
	}

	public Integer getWorkflowRoomid() {
		return workflowRoomid;
	}

	public void setWorkflowRoomid(Integer workflowRoomid) {
		this.workflowRoomid = workflowRoomid;
	}

	public Integer getWorkflowFromid() {
		return workflowFromid;
	}

	public void setWorkflowFromid(Integer workflowFromid) {
		this.workflowFromid = workflowFromid;
	}

	public Integer getWorkflowToid() {
		return workflowToid;
	}

	public void setWorkflowToid(Integer workflowToid) {
		this.workflowToid = workflowToid;
	}

	public String getWorkflowMessage() {
		return workflowMessage;
	}

	public void setWorkflowMessage(String workflowMessage) {
		this.workflowMessage = workflowMessage;
	}

	public Integer getWorkflowStatus() {
		return workflowStatus;
	}

	public void setWorkflowStatus(Integer workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	public Boolean getWorkflowIsactive() {
		return workflowIsactive;
	}

	public void setWorkflowIsactive(Boolean workflowIsactive) {
		this.workflowIsactive = workflowIsactive;
	}

	public Boolean getWorkflowIshighpriority() {
		return workflowIshighpriority;
	}

	public void setWorkflowIshighpriority(Boolean workflowIshighpriority) {
		this.workflowIshighpriority = workflowIshighpriority;
	}

	public Timestamp getWorkflowStarttime() {
		return workflowStarttime;
	}

	public void setWorkflowStarttime(Timestamp workflowStarttime) {
		this.workflowStarttime = workflowStarttime;
	}

	public Timestamp getWorkflowEndtime() {
		return workflowEndtime;
	}

	public void setWorkflowEndtime(Timestamp workflowEndtime) {
		this.workflowEndtime = workflowEndtime;
	}
}