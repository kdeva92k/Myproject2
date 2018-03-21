package com.glenwood.glaceemr.server.application.services.alertinbox;


import java.util.Date;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;

public class AlertEventBean {

	Integer alertEventId;
	Integer alertEventFrom;
	Integer alertEventTo;
	Integer alertEventStatus;
	Integer alertEventCategoryId;
	Integer alertEventRefId;
	Integer alertEventPatientId;
	Integer alertEventEncounterId;
	String alertEventCreatedDate;
	String alertEventClosedDate;
	String alertEventMessage;
	Boolean alertEventUnknown;
	Boolean alertEventRead;
	Boolean alertEventHighlight;
	Integer alertEventReadby;
	Integer alertEventModifiedby;
	String alertEventPatientName;
	String alertEventReadDate;
	String alertEventModifiedDate;
	Integer alertEventChartId;
	Integer alertEventRoomId;
	Integer alertEventStatus1;
	Integer alertEventParentalertid;
	Boolean alertEventIsgroupalert;
	String alertEventFrompage;
	EmployeeProfile empProfileTableFrom;
	Date currentmilliseconds;
	String createddate;
	String createdDateTime;
	

	
	public AlertEventBean(){

	}

	public AlertEventBean(Integer alertEventId,Integer alertEventFrom,Integer alertEventTo,
			Integer alertEventStatus,Integer alertEventCategoryId,Integer alertEventRefId,
			Integer alertEventPatientId,Integer alertEventEncounterId,String alertEventCreatedDate,
			String alertEventClosedDate,String alertEventMessage,Boolean alertEventUnknown,
			Boolean alertEventRead,Boolean alertEventHighlight,Integer alertEventReadby,Integer alertEventModifiedby,
			String alertEventPatientName,String alertEventReadDate,String alertEventModifiedDate,
			Integer alertEventChartId,Integer alertEventRoomId,Integer alertEventStatus1,
			Integer alertEventParentalertid,Boolean alertEventIsgroupalert,String alertEventFrompage,
			EmployeeProfile empProfileTableFrom,Date currentmilliseconds,String createddate,String createdDateTime){

		this.alertEventId=alertEventId;
		this.alertEventFrom=alertEventFrom;
		this.alertEventTo=alertEventTo;
		this.alertEventStatus=alertEventStatus;
		this.alertEventCategoryId=alertEventCategoryId;
		this.alertEventRefId=alertEventRefId;
		this.alertEventPatientId=alertEventPatientId;
		this.alertEventEncounterId=alertEventEncounterId;
		this.alertEventCreatedDate=alertEventCreatedDate;
		this.alertEventClosedDate=alertEventClosedDate;
		this.alertEventMessage=alertEventMessage;
		this.alertEventUnknown=alertEventUnknown;
		this.alertEventRead=alertEventRead;
		this.alertEventHighlight=alertEventHighlight;
		this.alertEventReadby=alertEventReadby;
		this.alertEventModifiedby=alertEventModifiedby;
		this.alertEventPatientName=alertEventPatientName;
		this.alertEventReadDate=alertEventReadDate;
		this.alertEventModifiedDate=alertEventModifiedDate;
		this.alertEventChartId=alertEventChartId;
		this.alertEventRoomId=alertEventRoomId;
		this.alertEventStatus1=alertEventStatus1;
		this.alertEventParentalertid=alertEventParentalertid;
		this.alertEventIsgroupalert=alertEventIsgroupalert;
		this.alertEventFrompage=alertEventFrompage;
		this.empProfileTableFrom=empProfileTableFrom;
		this.currentmilliseconds=currentmilliseconds;
		this.createddate=createddate;
		this.createdDateTime=createdDateTime;

	}

	public Integer getAlertEventId() {
		return alertEventId;
	}
	public void setAlertEventId(Integer alertEventId) {
		this.alertEventId = alertEventId;
	}
	public Integer getAlertEventFrom() {
		return alertEventFrom;
	}
	public void setAlertEventFrom(Integer alertEventFrom) {
		this.alertEventFrom = alertEventFrom;
	}
	public Integer getAlertEventTo() {
		return alertEventTo;
	}
	public void setAlertEventTo(Integer alertEventTo) {
		this.alertEventTo = alertEventTo;
	}
	public Integer getAlertEventStatus() {
		return alertEventStatus;
	}
	public void setAlertEventStatus(Integer alertEventStatus) {
		this.alertEventStatus = alertEventStatus;
	}
	public Integer getAlertEventCategoryId() {
		return alertEventCategoryId;
	}
	public void setAlertEventCategoryId(Integer alertEventCategoryId) {
		this.alertEventCategoryId = alertEventCategoryId;
	}
	public Integer getAlertEventRefId() {
		return alertEventRefId;
	}
	public void setAlertEventRefId(Integer alertEventRefId) {
		this.alertEventRefId = alertEventRefId;
	}
	public Integer getAlertEventPatientId() {
		return alertEventPatientId;
	}
	public void setAlertEventPatientId(Integer alertEventPatientId) {
		this.alertEventPatientId = alertEventPatientId;
	}
	public Integer getAlertEventEncounterId() {
		return alertEventEncounterId;
	}
	public void setAlertEventEncounterId(Integer alertEventEncounterId) {
		this.alertEventEncounterId = alertEventEncounterId;
	}
	public String getAlertEventCreatedDate() {
		return alertEventCreatedDate;
	}
	public void setAlertEventCreatedDate(String alertEventCreatedDate) {
		this.alertEventCreatedDate = alertEventCreatedDate;
	}
	public String getAlertEventClosedDate() {
		return alertEventClosedDate;
	}
	public void setAlertEventClosedDate(String alertEventClosedDate) {
		this.alertEventClosedDate = alertEventClosedDate;
	}
	public String getAlertEventMessage() {
		return alertEventMessage;
	}
	public void setAlertEventMessage(String alertEventMessage) {
		this.alertEventMessage = alertEventMessage;
	}
	public Boolean getAlertEventUnknown() {
		return alertEventUnknown;
	}
	public void setAlertEventUnknown(Boolean alertEventUnknown) {
		this.alertEventUnknown = alertEventUnknown;
	}
	public Boolean getAlertEventRead() {
		return alertEventRead;
	}
	public void setAlertEventRead(Boolean alertEventRead) {
		this.alertEventRead = alertEventRead;
	}
	public Boolean getAlertEventHighlight() {
		return alertEventHighlight;
	}
	public void setAlertEventHighlight(Boolean alertEventHighlight) {
		this.alertEventHighlight = alertEventHighlight;
	}
	public Integer getAlertEventReadby() {
		return alertEventReadby;
	}
	public void setAlertEventReadby(Integer alertEventReadby) {
		this.alertEventReadby = alertEventReadby;
	}
	public Integer getAlertEventModifiedby() {
		return alertEventModifiedby;
	}
	public void setAlertEventModifiedby(Integer alertEventModifiedby) {
		this.alertEventModifiedby = alertEventModifiedby;
	}
	public String getAlertEventPatientName() {
		return alertEventPatientName;
	}
	public void setAlertEventPatientName(String alertEventPatientName) {
		this.alertEventPatientName = alertEventPatientName;
	}
	public String getAlertEventReadDate() {
		return alertEventReadDate;
	}
	public void setAlertEventReadDate(String alertEventReadDate) {
		this.alertEventReadDate = alertEventReadDate;
	}
	public String getAlertEventModifiedDate() {
		return alertEventModifiedDate;
	}
	public void setAlertEventModifiedDate(String alertEventModifiedDate) {
		this.alertEventModifiedDate = alertEventModifiedDate;
	}
	public Integer getAlertEventChartId() {
		return alertEventChartId;
	}
	public void setAlertEventChartId(Integer alertEventChartId) {
		this.alertEventChartId = alertEventChartId;
	}
	public Integer getAlertEventRoomId() {
		return alertEventRoomId;
	}
	public void setAlertEventRoomId(Integer alertEventRoomId) {
		this.alertEventRoomId = alertEventRoomId;
	}
	public Integer getAlertEventStatus1() {
		return alertEventStatus1;
	}
	public void setAlertEventStatus1(Integer alertEventStatus1) {
		this.alertEventStatus1 = alertEventStatus1;
	}
	public Integer getAlertEventParentalertid() {
		return alertEventParentalertid;
	}
	public void setAlertEventParentalertid(Integer alertEventParentalertid) {
		this.alertEventParentalertid = alertEventParentalertid;
	}
	public Boolean getAlertEventIsgroupalert() {
		return alertEventIsgroupalert;
	}
	public void setAlertEventIsgroupalert(Boolean alertEventIsgroupalert) {
		this.alertEventIsgroupalert = alertEventIsgroupalert;
	}
	public String getAlertEventFrompage() {
		return alertEventFrompage;
	}

	public void setAlertEventFrompage(String alertEventFrompage) {
		this.alertEventFrompage = alertEventFrompage;
	}
	public EmployeeProfile getEmpProfileTableFrom() {
		return empProfileTableFrom;
	}

	public void setEmpProfileTableFrom(EmployeeProfile empProfileTableFrom) {
		this.empProfileTableFrom = empProfileTableFrom;
	}
	public Date getCurrentmilliseconds() {
		return currentmilliseconds;
	}

	public void setCurrentmilliseconds(Date currentmilliseconds) {
		this.currentmilliseconds = currentmilliseconds;
	}
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	public String getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

}
