package com.glenwood.glaceemr.server.application.services.DirectMail;

public class Attachment {
String filename;
String content;
String contentType;

public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getContentType() {
	return contentType;
}
public void setContentType(String contentType) {
	this.contentType = contentType;
}

public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public Attachment() {
	super();
}
public Attachment(String filename, String content, String contentType) {
	super();
	this.filename = filename;
	this.content = content;
	this.contentType = contentType;
}


}