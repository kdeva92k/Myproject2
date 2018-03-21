package com.glenwood.glaceemr.server.application.models;

public class PortalFileDetailBean {

	int patientId;
	
	String fileNameWithLocation;

	String fileName;
	
	String filePath;
	
	String fileContent;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getFileNameWithLocation() {
		return fileNameWithLocation;
	}

	public void setFileNameWithLocation(String fileNameWithLocation) {
		this.fileNameWithLocation = fileNameWithLocation;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
}
