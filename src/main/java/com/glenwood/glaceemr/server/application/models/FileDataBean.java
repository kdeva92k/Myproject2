package com.glenwood.glaceemr.server.application.models;

public class FileDataBean {

	String stringContent;
	
	byte[] byteArrayContent;
		
	Integer patientId;

	public String getStringContent() {
		return stringContent;
	}

	public void setStringContent(String stringContent) {
		this.stringContent = stringContent;
	}

	public byte[] getByteArrayContent() {
		return byteArrayContent;
	}

	public void setByteArrayContent(byte[] byteArrayContent) {
		this.byteArrayContent = byteArrayContent;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	
}
