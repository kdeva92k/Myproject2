package com.glenwood.glaceemr.server.application.services.portal.portalForms;

public class ClinicalIntakeXMLBean {
	
	int patientId;
	
	int intakeFormGroupId;
	
	String intakeFormGroupName;
	
	String xmlData;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getIntakeFormGroupId() {
		return intakeFormGroupId;
	}

	public void setIntakeFormGroupId(int intakeFormGroupId) {
		this.intakeFormGroupId = intakeFormGroupId;
	}

	public String getIntakeFormGroupName() {
		return intakeFormGroupName;
	}

	public void setIntakeFormGroupName(String intakeFormGroupName) {
		this.intakeFormGroupName = intakeFormGroupName;
	}

	public String getXmlData() {
		return xmlData;
	}

	public void setXmlData(String xmlData) {
		this.xmlData = xmlData;
	}
	
}
