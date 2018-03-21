package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class SavePatientDemographicsBean {

	PatientRegistrationBean savePatientRegistrationBean;
		
	List<PatientInsDetailBean> patientInsList;

	public PatientRegistrationBean getSavePatientRegistrationBean() {
		return savePatientRegistrationBean;
	}

	public void setSavePatientRegistrationBean(
			PatientRegistrationBean savePatientRegistrationBean) {
		this.savePatientRegistrationBean = savePatientRegistrationBean;
	}

	public List<PatientInsDetailBean> getPatientInsList() {
		return patientInsList;
	}

	public void setPatientInsList(List<PatientInsDetailBean> patientInsList) {
		this.patientInsList = patientInsList;
	}
	
}
