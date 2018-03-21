package com.glenwood.glaceemr.server.application.services.fax;

import java.util.Date;

public class FaxInboxBean {
	int faxId;
	Date receiveDate;
	String  senderName;
	String recepientName;
	String faxFileName;
	int folderId;
	String faxSubject;
	int faxTypeId;
	int statusId;
	int forwardTo;
	int modifiedBy;
	Date modifiedDate;
	String h491020Faxnotes;
	public FaxInboxBean(int faxId, Date receiveDate, String senderName,
			String recepientName, String faxFileName,
			int folderId, String faxSubject, int faxTypeId, int statusId,
			int forwardTo, int modifiedBy, Date modifiedDate,
			String h491020Faxnotes) {
		super();
		this.faxId = faxId;
		this.receiveDate = receiveDate;
		this.senderName = senderName;
		this.recepientName = recepientName;
		this.faxFileName = faxFileName;
		this.folderId = folderId;
		this.faxSubject = faxSubject;
		this.faxTypeId = faxTypeId;
		this.statusId = statusId;
		this.forwardTo = forwardTo;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.h491020Faxnotes = h491020Faxnotes;
	}
	public int getFaxId() {
		return faxId;
	}
	public void setFaxId(int faxId) {
		this.faxId = faxId;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getRecepientName() {
		return recepientName;
	}
	public void setRecepientName(String recepientName) {
		this.recepientName = recepientName;
	}
	public String getFaxFileName() {
		return faxFileName;
	}
	public void setFaxFileName(String faxFileName) {
		this.faxFileName = faxFileName;
	}
	public int getFolderId() {
		return folderId;
	}
	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}
	public String getFaxSubject() {
		return faxSubject;
	}
	public void setFaxSubject(String faxSubject) {
		this.faxSubject = faxSubject;
	}
	public int getFaxTypeId() {
		return faxTypeId;
	}
	public void setFaxTypeId(int faxTypeId) {
		this.faxTypeId = faxTypeId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getForwardTo() {
		return forwardTo;
	}
	public void setForwardTo(int forwardTo) {
		this.forwardTo = forwardTo;
	}
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getH491020Faxnotes() {
		return h491020Faxnotes;
	}
	public void setH491020Faxnotes(String h491020Faxnotes) {
		this.h491020Faxnotes = h491020Faxnotes;
	}

}