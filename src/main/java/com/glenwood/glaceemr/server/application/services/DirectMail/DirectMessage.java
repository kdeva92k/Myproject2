package com.glenwood.glaceemr.server.application.services.DirectMail;

import java.util.ArrayList;
import java.util.List;

public class DirectMessage {
String fromAddress=new String();
String toAddress=new String();
Body body=new Body();
List<Attachment> attachments=new ArrayList<Attachment>();
String subject=new String();
public String getMessageId() {
	return messageId;
}
public void setMessageId(String messageId) {
	this.messageId = messageId;
}
String accountId=new String();
String messageId=new String();
String sentOn=new String();
 
public String getSentOn() {
	return sentOn;
}
public void setSentOn(String sentOn) {
	this.sentOn = sentOn;
}
public String getFromAddress() {
	return fromAddress;
}
public void setFromAddress(String fromAddress) {
	this.fromAddress = fromAddress;
}
public String getToAddress() {
	return toAddress;
}
public void setToAddress(String toAddress) {
	this.toAddress = toAddress;
}
public Body getBody() {
	return body;
}
public void setBody(Body body) {
	this.body = body;
}

public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getAccountId() {
	return accountId;
}
public void setAccountId(String accountId) {
	this.accountId = accountId;
}

public DirectMessage() {
	super();
}
public List<Attachment> getAttachments() {
	return attachments;
}
public void setAttachments(List<Attachment> attachments) {
	this.attachments = attachments;
}
public DirectMessage(String fromAddress, String toAddress, Body body,
		List<Attachment> attachments, String subject, String accountId,
		String messageId, String sentOn) {
	super();
	this.fromAddress = fromAddress;
	this.toAddress = toAddress;
	this.body = body;
	this.attachments = attachments;
	this.subject = subject;
	this.accountId = accountId;
	this.messageId = messageId;
	this.sentOn = sentOn;
}




}