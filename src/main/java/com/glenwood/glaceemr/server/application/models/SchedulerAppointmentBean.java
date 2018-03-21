package com.glenwood.glaceemr.server.application.models;

import java.util.Date;

public class SchedulerAppointmentBean {
	
	
	public SchedulerAppointmentBean(Integer apptId, String apptStartTime,
			String apptEndTime, Date apptDate, Integer patientId,
			String patientAccNo, String patientName, String homePhone,
			String homeExtn, String workPhone, String workExtn, Integer resourceId,
			String apptStatus, Integer apptStatusId,String apptStatusColor, String apptType,
			Integer apptTypeId, String apptLocation, Integer apptLocationId,
			String apptReason,Integer apptReasonId, String refPhysician,
			Integer refPhysicianId, String refPhoneNumber, String refFaxNumber, 
			Integer workflowStatusId, String workflowStatusName,
			Date workflowStatusDate) {
		super();
		this.apptId = apptId;
		this.apptStartTime = apptStartTime;
		this.apptEndTime = apptEndTime;
		this.apptDate = apptDate;
		this.patientId = patientId;
		this.patientAccNo = patientAccNo;
		this.patientName = patientName;
		this.homePhone = homePhone;
		this.homeExtn = homeExtn;
		this.workPhone = workPhone;
		this.workExtn = workExtn;
		this.resourceId = resourceId;
		this.apptStatus = apptStatus;
		this.apptStatusId = apptStatusId;
		this.apptStatusColor = apptStatusColor;
		this.apptType = apptType;
		this.apptTypeId = apptTypeId;
		this.apptLocation = apptLocation;
		this.apptLocationId = apptLocationId;
		this.apptReason = apptReason;
		this.apptReasonId = apptReasonId;
		this.refPhysician = refPhysician;
		this.refPhysicianId = refPhysicianId;
		this.refPhoneNumber = refPhoneNumber;
		this.refFaxNumber = refFaxNumber;
		this.workflowStatusId=workflowStatusId;
		this.workflowStatusName=workflowStatusName;
		this.workflowStatusDate=workflowStatusDate;
	}
	
	private Integer apptId;
	private String apptStartTime;
	private String apptEndTime;
	private Date apptDate;
	private Integer patientId;
	private String patientAccNo;
	private String patientName;
	private String homePhone;
	private String homeExtn;
	private String workPhone;
	private String workExtn;
	private Integer resourceId;
	private String apptStatus;
	private Integer apptStatusId;
	private String apptStatusColor;
	private String apptType;
	private Integer apptTypeId;
	private String apptLocation;
	private Integer apptLocationId;
	private String apptReason;
	private Integer apptReasonId;
	private String refPhysician;
	private Integer refPhysicianId;
	private String refPhoneNumber;
	private String refFaxNumber;
	private Integer workflowStatusId;
	private String workflowStatusName;
	private Date workflowStatusDate;
	
	public Integer getApptId() {
		return apptId;
	}
	public void setApptId(Integer apptId) {
		this.apptId = apptId;
	}
	public String getApptStartTime() {
		return apptStartTime;
	}
	public void setApptStartTime(String apptStartTime) {
		this.apptStartTime = apptStartTime;
	}
	public String getApptEndTime() {
		return apptEndTime;
	}
	public void setApptEndTime(String apptEndTime) {
		this.apptEndTime = apptEndTime;
	}
	public Date getApptDate() {
		return apptDate;
	}
	public void setApptDate(Date apptDate) {
		this.apptDate = apptDate;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public String getPatientAccNo() {
		return patientAccNo;
	}
	public void setPatientAccNo(String
			patientAccNo) {
		this.patientAccNo = patientAccNo;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getHomeExtn() {
		return homeExtn;
	}
	public void setHomeExtn(String homeExtn) {
		this.homeExtn = homeExtn;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getWorkExtn() {
		return workExtn;
	}
	public void setWorkExtn(String workExtn) {
		this.workExtn = workExtn;
	}
	public String getApptStatus() {
		return apptStatus;
	}
	public void setApptStatus(String apptStatus) {
		this.apptStatus = apptStatus;
	}
	public Integer getApptStatusId() {
		return apptStatusId;
	}
	public void setApptStatusId(Integer apptStatusId) {
		this.apptStatusId = apptStatusId;
	}
	public String getApptType() {
		return apptType;
	}
	public void setApptType(String apptType) {
		this.apptType = apptType;
	}
	public Integer getApptTypeId() {
		return apptTypeId;
	}
	public void setApptTypeId(Integer apptTypeId) {
		this.apptTypeId = apptTypeId;
	}
	public String getApptLocation() {
		return apptLocation;
	}
	public void setApptLocation(String apptLocation) {
		this.apptLocation = apptLocation;
	}
	public Integer getApptLocationId() {
		return apptLocationId;
	}
	public void setApptLocationId(Integer apptLocationId) {
		this.apptLocationId = apptLocationId;
	}
	public String getApptReason() {
		return apptReason;
	}
	public void setApptReason(String apptReason) {
		this.apptReason = apptReason;
	}
	public Integer getApptReasonId() {
		return apptReasonId;
	}
	public void setApptReasonId(Integer apptReasonId) {
		this.apptReasonId = apptReasonId;
	}
	public String getRefPhysician() {
		return refPhysician;
	}
	public void setRefPhysician(String refPhysician) {
		this.refPhysician = refPhysician;
	}
	public Integer getRefPhysicianId() {
		return refPhysicianId;
	}
	public void setRefPhysicianId(Integer refPhysicianId) {
		this.refPhysicianId = refPhysicianId;
	}
	public String getRefPhoneNumber() {
		return refPhoneNumber;
	}
	public void setRefPhoneNumber(String refPhoneNumber) {
		this.refPhoneNumber = refPhoneNumber;
	}
	public String getRefFaxNumber() {
		return refFaxNumber;
	}
	public void setRefFaxNumber(String refFaxNumber) {
		this.refFaxNumber = refFaxNumber;
	}
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public Integer getWorkflowStatus() {
		return workflowStatusId;
	}
	public void setWorkflowStatus(Integer workflowStatus) {
		this.workflowStatusId = workflowStatus;
	}
	public String getWorkflowStatusName() {
		return workflowStatusName;
	}
	public void setWorkflowStatusName(String workflowStatusName) {
		this.workflowStatusName = workflowStatusName;
	}
	public Date getWorkflowStatusDate() {
		return workflowStatusDate;
	}
	public void setWorkflowStatusDate(Date workflowStatusDate) {
		this.workflowStatusDate = workflowStatusDate;
	}
	public Integer getWorkflowStatusId() {		
		return workflowStatusId;		
	}		
	public void setWorkflowStatusId(Integer workflowStatusId) {		
		this.workflowStatusId = workflowStatusId;		
	}		
	public String getApptStatusColor() {		
		return apptStatusColor;		
	}		
	public void setApptStatusColor(String apptStatusColor) {		
		this.apptStatusColor = apptStatusColor;		
	}
}