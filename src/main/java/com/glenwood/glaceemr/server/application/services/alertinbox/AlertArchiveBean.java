package com.glenwood.glaceemr.server.application.services.alertinbox;

import java.sql.Timestamp;
import java.util.Date;

public class AlertArchiveBean {
	String roomName;
	Integer alertCategoryId;
	String alertCategoryName;
	String alertCategoryUrl;
	String alertCategoryDisplayName;
	Integer alertCategoryDisplayOrder;
	Integer alertCategoryGroup;
	Integer alertCategoryType;
	Integer alertCategorySection;
	Integer alertCategoryActionMap;
	Integer alertCategorySubpage;
	Integer alertCategoryQrFlag;
	String alertCategoryQrUrl;
	Integer chartId;
	Integer alertEventId;
	String patientRegistrationAccountno;
	Integer alertEventParentalertid;
	Integer alertEventFrom;
	Integer alertEventTo;
	Integer alertEventRefId;
	Integer alertEventPatientId;
	Integer alertEventReadby;
	Integer alertEventModifiedby;
	Integer alertEventStatus;
	Integer alertEventEncounterId;
	String createdDate;
	String createdTime;
	Date createdDateTime;
	String eventMessage;
	String readOn;
	String modifiedOn;
	String forwardedBy;
	String receiveOtherwise;
	String readByOtherwise;
	String modifiedByOtherwise;
	Boolean alertEventRead;
	Boolean alertEventHighlight;
	String alertEventPatientName;
	String expanded;
	String needdatewisegrouping;
	String status;
	
	public AlertArchiveBean(){
		
	}
	public AlertArchiveBean(String roomName,Integer alertCategoryId,String alertCategoryName,
			String alertCategoryUrl,String alertCategoryDisplayName,Integer alertCategoryDisplayOrder,
			Integer alertCategoryGroup,Integer alertCategoryType,Integer alertCategorySection,Integer alertCategoryActionMap,
			Integer alertCategorySubpage,Integer alertCategoryQrFlag,String alertCategoryQrUrl,Integer chartId,
			Integer alertEventId,String patientRegistrationAccountno,Integer alertEventParentalertid,
			Integer alertEventFrom,Integer alertEventTo,Integer alertEventRefId,Integer alertEventPatientId,
			Integer alertEventReadby,Integer alertEventModifiedby,Integer alertEventStatus,Integer alertEventEncounterId,String createdDate,
			String createdTime,Date createdDateTime,String eventMessage,String readOn,String modifiedOn,
			String forwardedBy,String receiveOtherwise,String readByOtherwise,String modifiedByOtherwise,Boolean alertEventRead,
			Boolean alertEventHighlight,String alertEventPatientName,String expanded,String needdatewisegrouping)
	{
		this.roomName = roomName;
		this.alertCategoryId = alertCategoryId;
		this.alertCategoryName = alertCategoryName;
		this.alertCategoryUrl = alertCategoryUrl;
		this.alertCategoryDisplayName = alertCategoryDisplayName;
		this.alertCategoryDisplayOrder = alertCategoryDisplayOrder;
		this.alertCategoryGroup = alertCategoryGroup;
		this.alertCategoryType = alertCategoryType;
		this.alertCategorySection = alertCategorySection;
		this.alertCategoryActionMap = alertCategoryActionMap;
		this.alertCategorySubpage = alertCategorySubpage;
		this.alertCategoryQrFlag = alertCategoryQrFlag;
		this.alertCategoryQrUrl = alertCategoryQrUrl;
		this.chartId = chartId;
			this.alertEventId = alertEventId;
			this.patientRegistrationAccountno = patientRegistrationAccountno;
			this.alertEventParentalertid = alertEventParentalertid;
			this.alertEventFrom = alertEventFrom;
			this.alertEventTo = alertEventTo;
			this.alertEventRefId = alertEventRefId;
			this.alertEventPatientId = alertEventPatientId;
			this.alertEventReadby = alertEventReadby;
			this.alertEventModifiedby = alertEventModifiedby;
			this.alertEventStatus = alertEventStatus;
			this.alertEventEncounterId = alertEventEncounterId;
			this.createdDate = createdDate;
			this.createdTime = createdTime;
			this.createdDateTime = createdDateTime;
			this.eventMessage = eventMessage;
			this.readOn = readOn;
			this.modifiedOn = modifiedOn;
			this.forwardedBy = forwardedBy;
			this.receiveOtherwise = receiveOtherwise;
			this.readByOtherwise = readByOtherwise;
			this.modifiedByOtherwise = modifiedByOtherwise;
			this.alertEventRead = alertEventRead;
			this.alertEventHighlight = alertEventHighlight;
			this.alertEventPatientName = alertEventPatientName;
			this.expanded = expanded;
			this.needdatewisegrouping = needdatewisegrouping;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Integer getAlertCategoryId() {
		return alertCategoryId;
	}
	public void setAlertCategoryId(Integer alertCategoryId) {
		this.alertCategoryId = alertCategoryId;
	}
	public String getAlertCategoryName() {
		return alertCategoryName;
	}
	public void setAlertCategoryName(String alertCategoryName) {
		this.alertCategoryName = alertCategoryName;
	}
	public String getAlertCategoryUrl() {
		return alertCategoryUrl;
	}
	public void setAlertCategoryUrl(String alertCategoryUrl) {
		this.alertCategoryUrl = alertCategoryUrl;
	}
	public String getAlertCategoryDisplayName() {
		return alertCategoryDisplayName;
	}
	public void setAlertCategoryDisplayName(String alertCategoryDisplayName) {
		this.alertCategoryDisplayName = alertCategoryDisplayName;
	}
	public Integer getAlertCategoryDisplayOrder() {
		return alertCategoryDisplayOrder;
	}
	public void setAlertCategoryDisplayOrder(Integer alertCategoryDisplayOrder) {
		this.alertCategoryDisplayOrder = alertCategoryDisplayOrder;
	}
	public Integer getAlertCategoryGroup() {
		return alertCategoryGroup;
	}
	public void setAlertCategoryGroup(Integer alertCategoryGroup) {
		this.alertCategoryGroup = alertCategoryGroup;
	}
	public Integer getAlertCategoryType() {
		return alertCategoryType;
	}
	public void setAlertCategoryType(Integer alertCategoryType) {
		this.alertCategoryType = alertCategoryType;
	}
	public Integer getAlertCategorySection() {
		return alertCategorySection;
	}
	public void setAlertCategorySection(Integer alertCategorySection) {
		this.alertCategorySection = alertCategorySection;
	}
	public Integer getAlertCategoryActionMap() {
		return alertCategoryActionMap;
	}
	public void setAlertCategoryActionMap(Integer alertCategoryActionMap) {
		this.alertCategoryActionMap = alertCategoryActionMap;
	}
	public Integer getAlertCategorySubpage() {
		return alertCategorySubpage;
	}
	public void setAlertCategorySubpage(Integer alertCategorySubpage) {
		this.alertCategorySubpage = alertCategorySubpage;
	}
	public Integer getAlertCategoryQrFlag() {
		return alertCategoryQrFlag;
	}
	public void setAlertCategoryQrFlag(Integer alertCategoryQrFlag) {
		this.alertCategoryQrFlag = alertCategoryQrFlag;
	}
	public String getAlertCategoryQrUrl() {
		return alertCategoryQrUrl;
	}
	public void setAlertCategoryQrUrl(String alertCategoryQrUrl) {
		this.alertCategoryQrUrl = alertCategoryQrUrl;
	}
	public Integer getChartId() {
		return chartId;
	}
	public void setChartId(Integer chartId) {
		this.chartId = chartId;
	}
	public Integer getAlertEventId() {
		return alertEventId;
	}
	public void setAlertEventId(Integer alertEventId) {
		this.alertEventId = alertEventId;
	}
	public String getPatientRegistrationAccountno() {
		return patientRegistrationAccountno;
	}
	public void setPatientRegistrationAccountno(String patientRegistrationAccountno) {
		this.patientRegistrationAccountno = patientRegistrationAccountno;
	}
	public Integer getAlertEventParentalertid() {
		return alertEventParentalertid;
	}
	public void setAlertEventParentalertid(Integer alertEventParentalertid) {
		this.alertEventParentalertid = alertEventParentalertid;
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
	public Integer getAlertEventStatus() {
		return alertEventStatus;
	}
	public void setAlertEventStatus(Integer alertEventStatus) {
		this.alertEventStatus = alertEventStatus;
	}
	public Integer getAlertEventEncounterId() {
		return alertEventEncounterId;
	}
	public void setAlertEventEncounterId(Integer alertEventEncounterId) {
		this.alertEventEncounterId = alertEventEncounterId;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public Date getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public String getEventMessage() {
		return eventMessage;
	}
	public void setEventMessage(String eventMessage) {
		this.eventMessage = eventMessage;
	}
	public String getReadOn() {
		return readOn;
	}
	public void setReadOn(String readOn) {
		this.readOn = readOn;
	}
	public String getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getForwardedBy() {
		return forwardedBy;
	}
	public void setForwardedBy(String forwardedBy) {
		this.forwardedBy = forwardedBy;
	}
	public String getReceiveOtherwise() {
		return receiveOtherwise;
	}
	public void setReceiveOtherwise(String receiveOtherwise) {
		this.receiveOtherwise = receiveOtherwise;
	}
	public String getReadByOtherwise() {
		return readByOtherwise;
	}
	public void setReadByOtherwise(String readByOtherwise) {
		this.readByOtherwise = readByOtherwise;
	}
	public String getModifiedByOtherwise() {
		return modifiedByOtherwise;
	}
	public void setModifiedByOtherwise(String modifiedByOtherwise) {
		this.modifiedByOtherwise = modifiedByOtherwise;
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
	public String getAlertEventPatientName() {
		return alertEventPatientName;
	}
	public void setAlertEventPatientName(String alertEventPatientName) {
		this.alertEventPatientName = alertEventPatientName;
	}
	public String getExpanded() {
		return expanded;
	}
	public void setExpanded(String expanded) {
		this.expanded = expanded;
	}
	public String getNeeddatewisegrouping() {
		return needdatewisegrouping;
	}
	public void setNeeddatewisegrouping(String needdatewisegrouping) {
		this.needdatewisegrouping = needdatewisegrouping;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}