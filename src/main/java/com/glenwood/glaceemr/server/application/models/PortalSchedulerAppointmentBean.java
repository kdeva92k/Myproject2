package com.glenwood.glaceemr.server.application.models;

import java.util.Date;

public class PortalSchedulerAppointmentBean {
	
	
	public PortalSchedulerAppointmentBean(Integer apptId, String apptStartTime,
			String apptEndTime, Date apptDate, Integer apptInterval, Integer patientId,
			String patientAccNo, String patientName, String homePhone,
			String homeExtn, String workPhone, String workExtn, Integer resourceId,
			String apptStatus, Integer apptStatusId, String apptType,
			Integer apptTypeId, String apptLocation, Integer apptLocationId,String apptProvider,Integer apptProviderId,
			String apptReason, Integer apptReasonId, String apptComments, String refPhysician,
			Integer refPhysicianId, String refPhoneNumber, String refFaxNumber, 
			Integer workflowStatusId, String workflowStatusName,
			Date workflowStatusDate) {
		super();
		if(apptId!=null)
			this.apptId = apptId;
		if(apptStartTime!=null)
			this.apptStartTime = apptStartTime;
		if(apptEndTime!=null)
			this.apptEndTime = apptEndTime;
		if(apptDate!=null)
			this.apptDate = apptDate;
		if(apptInterval!=null)
			this.apptInterval=apptInterval;
		if(patientId!=null)
			this.patientId = patientId;
		if(patientAccNo!=null)
			this.patientAccNo = patientAccNo;
		if(patientName!=null)
			this.patientName = patientName;
		if(homePhone!=null)
			this.homePhone = homePhone;
		if(homeExtn!=null)
			this.homeExtn = homeExtn;
		if(workPhone!=null)
			this.workPhone = workPhone;
		if(workExtn!=null)
			this.workExtn = workExtn;
		if(resourceId!=null)
			this.resourceId = resourceId;
		if(apptStatus!=null)
			this.apptStatus = apptStatus;
		if(apptStatusId!=null)
			this.apptStatusId = apptStatusId;
		if(apptType!=null)
			this.apptType = apptType;
		if(apptTypeId!=null)
			this.apptTypeId = apptTypeId;
		if(apptLocation!=null)
			this.apptLocation = apptLocation;
		if(apptLocationId!=null)
			this.apptLocationId = apptLocationId;
		if(apptProvider!=null)
			this.apptProvider=apptProvider;
		if(apptProviderId!=null)
			this.apptProviderId=apptProviderId;
		if(apptReason!=null)
			this.apptReason = apptReason;
		if(apptReasonId!=null)
			this.apptReasonId = apptReasonId;
		if(apptComments!=null)
			this.apptComments=apptComments;
		if(refPhysician!=null)
			this.refPhysician = refPhysician;
		if(refPhysicianId!=null)
			this.refPhysicianId = refPhysicianId;
		if(refPhoneNumber!=null)
			this.refPhoneNumber = refPhoneNumber;
		if(refFaxNumber!=null)
			this.refFaxNumber = refFaxNumber;
		if(workflowStatusId!=null)
			this.workflowStatusId=workflowStatusId;
		if(workflowStatusName!=null)
			this.workflowStatusName=workflowStatusName;
		if(workflowStatusDate!=null)
			this.workflowStatusDate=workflowStatusDate;
	}
	
	private Integer apptId;
	private String apptStartTime;
	private String apptEndTime;
	private Date apptDate;
	private Integer apptInterval;
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
	private String apptType;
	private Integer apptTypeId;
	private String apptLocation;
	private Integer apptLocationId;
	private String apptProvider;
	private Integer apptProviderId;
	private String apptReason;
	private Integer apptReasonId;
	private String apptComments;
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
	public Integer getApptInterval() {
		return apptInterval;
	}
	public void setApptInterval(Integer apptInterval) {
		this.apptInterval = apptInterval;
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
	public void setPatientAccNo(String patientAccNo) {
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
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
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
	public String getApptProvider() {
		return apptProvider;
	}
	public void setApptProvider(String apptProvider) {
		this.apptProvider = apptProvider;
	}
	public Integer getApptProviderId() {
		return apptProviderId;
	}
	public void setApptProviderId(Integer apptProviderId) {
		this.apptProviderId = apptProviderId;
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
	public String getApptComments() {
		return apptComments;
	}
	public void setApptComments(String apptComments) {
		this.apptComments = apptComments;
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
	public Integer getWorkflowStatusId() {
		return workflowStatusId;
	}
	public void setWorkflowStatusId(Integer workflowStatusId) {
		this.workflowStatusId = workflowStatusId;
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
		
}
