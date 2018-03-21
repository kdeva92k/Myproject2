package com.glenwood.glaceemr.server.application.models;

public class PortalRefillRequestRxBean {

	
	int prescriptionId;
	
	String prescriptionName;
	
	String prescriptionDosage;

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public String getPrescriptionName() {
		return prescriptionName;
	}

	public void setPrescriptionName(String prescriptionName) {
		this.prescriptionName = prescriptionName;
	}

	public String getPrescriptionDosage() {
		return prescriptionDosage;
	}

	public void setPrescriptionDosage(String prescriptionDosage) {
		this.prescriptionDosage = prescriptionDosage;
	}
	
}
