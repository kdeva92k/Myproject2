package com.glenwood.glaceemr.server.application.models;

public class PortalMessageBean {

	Integer patientid;
	
	Integer chartid;
	
	Integer encounterid;
	
	String message;
	
	String messageDate;
	
	String messageComposedDate;
	
	Integer status;
	
	String messageReply;
	
	Integer messageAlertid;
	
	Integer messasgeBy;
	
	Boolean isActive;
	
	Integer messageTo;
	
	Integer parentid;
	
	String messageSender;
	
	String messageReceiver;
	
	Integer messageMapid;
	
	Boolean isPortalMessageReplied;
	
	Integer messageType;
	
	String fromPage;

	public Integer getPatientid() {
		return patientid;
	}

	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}

	public Integer getChartid() {
		return chartid;
	}

	public void setChartid(Integer chartid) {
		this.chartid = chartid;
	}
	
	public Integer getEncounterid() {
		return encounterid;
	}

	public void setEncounterid(Integer encounterid) {
		this.encounterid = encounterid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}
	
	public String getMessageComposedDate() {
		return messageComposedDate;
	}

	public void setMessageComposedDate(String messageComposedDate) {
		this.messageComposedDate = messageComposedDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessageReply() {
		return messageReply;
	}

	public void setMessageReply(String messageReply) {
		this.messageReply = messageReply;
	}

	public Integer getMessageAlertid() {
		return messageAlertid;
	}

	public void setMessageAlertid(Integer messageAlertid) {
		this.messageAlertid = messageAlertid;
	}

	public Integer getMessasgeBy() {
		return messasgeBy;
	}

	public void setMessasgeBy(Integer messasgeBy) {
		this.messasgeBy = messasgeBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getMessageTo() {
		return messageTo;
	}

	public void setMessageTo(Integer messageTo) {
		this.messageTo = messageTo;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(String messageSender) {
		this.messageSender = messageSender;
	}

	public String getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(String messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	public Integer getMessageMapid() {
		return messageMapid;
	}

	public void setMessageMapid(Integer messageMapid) {
		this.messageMapid = messageMapid;
	}

	public Boolean getIsPortalMessageReplied() {
		return isPortalMessageReplied;
	}

	public void setIsPortalMessageReplied(Boolean isPortalMessageReplied) {
		this.isPortalMessageReplied = isPortalMessageReplied;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public String getFromPage() {
		return fromPage;
	}

	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}

}
