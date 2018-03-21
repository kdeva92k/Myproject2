package com.glenwood.glaceemr.server.application.services.portal.portalForms;

public class PortalConsentFormDetailsBean {

	String formName;
	
	String formFileType;
	
	int formTemplateId;
	
	String formFilePath;
	
	String pdfSavePath;
	
	String consentHtml;
	
	int patientId;
	
	int fileDetailsId;
	
	String formSaveName;
	
	boolean signed;

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getFormFileType() {
		return formFileType;
	}

	public void setFormFileType(String formFileType) {
		this.formFileType = formFileType;
	}

	public int getFormTemplateId() {
		return formTemplateId;
	}

	public void setFormTemplateId(int formTemplateId) {
		this.formTemplateId = formTemplateId;
	}

	public String getFormFilePath() {
		return formFilePath;
	}

	public void setFormFilePath(String formFilePath) {
		this.formFilePath = formFilePath;
	}

	public String getPdfSavePath() {
		return pdfSavePath;
	}

	public void setPdfSavePath(String pdfSavePath) {
		this.pdfSavePath = pdfSavePath;
	}

	public String getConsentHtml() {
		return consentHtml;
	}

	public void setConsentHtml(String consentHtml) {
		this.consentHtml = consentHtml;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getFileDetailsId() {
		return fileDetailsId;
	}

	public void setFileDetailsId(int fileDetailsId) {
		this.fileDetailsId = fileDetailsId;
	}

	public String getFormSaveName() {
		return formSaveName;
	}

	public void setFormSaveName(String formSaveName) {
		this.formSaveName = formSaveName;
	}

	public boolean isSigned() {
		return signed;
	}

	public void setSigned(boolean signed) {
		this.signed = signed;
	}
	
}
