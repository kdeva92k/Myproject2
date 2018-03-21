package com.glenwood.glaceemr.server.application.models;

public class ClinicalIntakeFormBean {

	
	ClinicalQuestionsGroup formGroupDetails;
	
	PatientClinicalElementsQuestions patientFormDetails;

	public ClinicalQuestionsGroup getFormGroupDetails() {
		return formGroupDetails;
	}

	public void setFormGroupDetails(ClinicalQuestionsGroup formGroupDetails) {
		this.formGroupDetails = formGroupDetails;
	}

	public PatientClinicalElementsQuestions getPatientFormDetails() {
		return patientFormDetails;
	}

	public void setPatientFormDetails(
			PatientClinicalElementsQuestions patientFormDetails) {
		this.patientFormDetails = patientFormDetails;
	}
	
}
