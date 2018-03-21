package com.glenwood.glaceemr.server.application.models;

import java.util.Date;

public class WorkflowBean {
	private Integer workflowId;
	private Integer workflowPatientid;
	private Integer workflowEncounterid;
	private Integer workflowChartid;
	private Integer workflowRoomid;
	private Integer workflowFromid;
	private Integer workflowToid;
	private String workflowMessage;
	private Integer workflowStatus;
	private Boolean workflowIsactive;
	private Boolean workflowIshighpriority;
	private Date workflowStarttime;
	private Date workflowEndtime;
	private Date currentmilliseconds;
	private String timeDifference;
	private String senderName;
	private String receiverName;
	private String roomName;
	private String statusName;
	private String categoryUrl;
	private String patientName;

	public WorkflowBean(Integer workflowId, Integer workflowPatientid,
			Integer workflowEncounterid, Integer workflowChartid,
			Integer workflowRoomid, Integer workflowFromid,
			Integer workflowToid, String workflowMessage,
			Integer workflowStatus, Boolean workflowIsactive,
			Boolean workflowIshighpriority, Date workflowStarttime,
			Date workflowEndtime, Date currentmilliseconds,
			String timeDifference,String senderName,String receiverName,
			String roomName,String statusName,String categoryUrl,String patientName) {
		super();
		this.workflowId = workflowId;
		this.workflowPatientid = workflowPatientid;
		this.workflowEncounterid = workflowEncounterid;
		this.workflowChartid = workflowChartid;
		this.workflowRoomid = workflowRoomid;
		this.workflowFromid = workflowFromid;
		this.workflowToid = workflowToid;
		this.workflowMessage = workflowMessage;
		this.workflowStatus = workflowStatus;
		this.workflowIsactive = workflowIsactive;
		this.workflowIshighpriority = workflowIshighpriority;
		this.workflowStarttime = workflowStarttime;
		this.workflowEndtime = workflowEndtime;
		this.currentmilliseconds = currentmilliseconds;
		this.timeDifference = timeDifference;
		this.senderName=senderName;
		this.receiverName=receiverName; 
		this.roomName=roomName;
		this.statusName=statusName;
		this.patientName = patientName;
		this.categoryUrl=categoryUrl;
	}
	
	public WorkflowBean(Integer workflowPatientid,Integer workflowEncounterid){
		this.workflowPatientid = workflowPatientid;
		this.workflowEncounterid = workflowEncounterid;
	}
	
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

	public Date getWorkflowStarttime() {
		return workflowStarttime;
	}

	public void setWorkflowStarttime(Date workflowStarttime) {
		this.workflowStarttime = workflowStarttime;
	}

	public Date getWorkflowEndtime() {
		return workflowEndtime;
	}

	public void setWorkflowEndtime(Date workflowEndtime) {
		this.workflowEndtime = workflowEndtime;
	}

	public Date getCurrentmilliseconds() {
		return currentmilliseconds;
	}

	public void setCurrentmilliseconds(Date currentmilliseconds) {
		this.currentmilliseconds = currentmilliseconds;
	}

	public String getTimeDifference() {
		return timeDifference;
	}

	public void setTimeDifference(String timeDifference) {
		this.timeDifference = timeDifference;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCategoryUrl() {
		return categoryUrl;
	}

	public void setCategoryUrl(String categoryUrl) {
		this.categoryUrl = categoryUrl;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

}
