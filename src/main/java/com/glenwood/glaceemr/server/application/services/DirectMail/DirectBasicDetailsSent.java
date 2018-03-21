package com.glenwood.glaceemr.server.application.services.DirectMail;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

public class DirectBasicDetailsSent {
	
	ArrayList<String> fileName=new ArrayList();;
String body=new String();
String subject=new String();
String toAddress=new String();
String fromAddress=new String();
String accountID=new String();
Integer userId;
Integer repId;
Integer loginId;
Integer patientId;
Integer loginType;
Integer actionType;
Integer filecount;
Boolean fromPrintFax;
Boolean hreadable;
Boolean electronic;
String loginName;
String sharedFolderPath;

ArrayList<String> filenameArray=new ArrayList();
ArrayList<String> filenameArrayDoc=new ArrayList();

public ArrayList<String> getFileName() {
	return fileName;
}

public void setFileName(ArrayList<String> fileName) {
	this.fileName = fileName;
}
public ArrayList<String> getFilenameArrayDoc() {
	return filenameArrayDoc;
}
public void setFilenameArrayDoc(ArrayList<String> filenameArrayDoc) {
	this.filenameArrayDoc = filenameArrayDoc;
}

public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getToAddress() {
	return toAddress;
}
public void setToAddress(String toAddress) {
	this.toAddress = toAddress;
}
public String getFromAddress() {
	return fromAddress;
}
public void setFromAddress(String fromAddress) {
	this.fromAddress = fromAddress;
}
public String getAccountID() {
	return accountID;
}
public void setAccountID(String accountID) {
	this.accountID = accountID;
}
public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}
public Integer getRepId() {
	return repId;
}
public void setRepId(Integer repId) {
	this.repId = repId;
}
public Integer getLoginId() {
	return loginId;
}
public void setLoginId(Integer loginId) {
	this.loginId = loginId;
}
public Integer getPatientId() {
	return patientId;
}
public void setPatientId(Integer patientId) {
	this.patientId = patientId;
}
public Integer getLoginType() {
	return loginType;
}
public void setLoginType(Integer loginType) {
	this.loginType = loginType;
}
public Integer getActionType() {
	return actionType;
}
public void setActionType(Integer actionType) {
	this.actionType = actionType;
}
public Integer getFilecount() {
	return filecount;
}
public void setFilecount(Integer filecount) {
	this.filecount = filecount;
}
public Boolean getFromPrintFax() {
	return fromPrintFax;
}
public void setFromPrintFax(Boolean fromPrintFax) {
	this.fromPrintFax = fromPrintFax;
}
public String getLoginName() {
	return loginName;
}
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
public ArrayList<String> getFilenameArray() {
	return filenameArray;
}
public void setFilenameArray(ArrayList<String> filenameArray) {
	this.filenameArray = filenameArray;
}
public Boolean getHreadable() {
	return hreadable;
}
public void setHreadable(Boolean hreadable) {
	this.hreadable = hreadable;
}
public Boolean getElectronic() {
	return electronic;
}
public void setElectronic(Boolean electronic) {
	this.electronic = electronic;
}
public String getSharedFolderPath() {
	return sharedFolderPath;
}
public void setSharedFolderPath(String sharedFolderPath) {
	this.sharedFolderPath = sharedFolderPath;
}



public DirectBasicDetailsSent(ArrayList<String> fileName, String body,
		String subject, String toAddress, String fromAddress, String accountID,
		Integer userId, Integer repId, Integer loginId, Integer patientId,
		Integer loginType, Integer actionType, Integer filecount,
		Boolean fromPrintFax, Boolean hreadable, Boolean electronic,
		String loginName, String sharedFolderPath,
		ArrayList<String> filenameArray, ArrayList<String> filenameArrayDoc) {
	super();
	this.fileName = fileName;
	this.body = body;
	this.subject = subject;
	this.toAddress = toAddress;
	this.fromAddress = fromAddress;
	this.accountID = accountID;
	this.userId = userId;
	this.repId = repId;
	this.loginId = loginId;
	this.patientId = patientId;
	this.loginType = loginType;
	this.actionType = actionType;
	this.filecount = filecount;
	this.fromPrintFax = fromPrintFax;
	this.hreadable = hreadable;
	this.electronic = electronic;
	this.loginName = loginName;
	this.sharedFolderPath = sharedFolderPath;
	this.filenameArray = filenameArray;
	this.filenameArrayDoc = filenameArrayDoc;
}

public DirectBasicDetailsSent() {
	super();
}

}
