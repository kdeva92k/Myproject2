package com.glenwood.glaceemr.server.application.services.chart.print;

public class PrintDetailsDataBean {

	int pageSize;
	int pageOrientation;
	String htmlData;
	int patientId;
	int encounterId;
	int styleId;
	String sharedFolder;
	String fileName;
	int datatype;
	int isDoctorSignReq;
	int isPatientHeaderReq;
	int isPracticeHeaderReq;

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageOrientation() {
		return pageOrientation;
	}
	public void setPageOrientation(int pageOrientation) {
		this.pageOrientation = pageOrientation;
	}
	public String getHtmlData() {
		return htmlData;
	}
	public void setHtmlData(String htmlData) {
		this.htmlData = htmlData;
	}
	public int getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(int encounterId) {
		this.encounterId = encounterId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getStyleId() {
		return styleId;
	}
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}
	public String getSharedFolder() {
		return sharedFolder;
	}
	public void setSharedFolder(String sharedFolder) {
		this.sharedFolder = sharedFolder;
	}
	public int getDatatype() {
		return datatype;
	}
	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}
	public int getIsDoctorSignReq() {
		return isDoctorSignReq;
	}
	public void setIsDoctorSignReq(int isDoctorSignReq) {
		this.isDoctorSignReq = isDoctorSignReq;
	}
	public int getIsPatientHeaderReq() {
		return isPatientHeaderReq;
	}
	public void setIsPatientHeaderReq(int isPatientHeaderReq) {
		this.isPatientHeaderReq = isPatientHeaderReq;
	}
	public int getIsPracticeHeaderReq() {
		return isPracticeHeaderReq;
	}
	public void setIsPracticeHeaderReq(int isPracticeHeaderReq) {
		this.isPracticeHeaderReq = isPracticeHeaderReq;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



}
