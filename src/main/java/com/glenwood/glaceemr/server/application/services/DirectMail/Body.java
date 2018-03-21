package com.glenwood.glaceemr.server.application.services.DirectMail;

public class Body {
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
public Body(String content, String contentType) {
	super();
	this.content = content;
	this.contentType = contentType;
}
public Body() {
	super();
}

}