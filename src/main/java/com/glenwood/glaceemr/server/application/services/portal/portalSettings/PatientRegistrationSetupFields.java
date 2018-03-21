package com.glenwood.glaceemr.server.application.services.portal.portalSettings;

import java.util.List;

public class PatientRegistrationSetupFields {

	PatientProfileSettingsFields patientProfileSettingsFields;
	
	PortalBillingConfigFields portalBillingConfigFields;
	
	List<PrincipalDoctor> principalDoctorsList;

	public PatientProfileSettingsFields getPatientProfileSettingsFields() {
		return patientProfileSettingsFields;
	}

	public void setPatientProfileSettingsFields(
			PatientProfileSettingsFields patientProfileSettingsFields) {
		this.patientProfileSettingsFields = patientProfileSettingsFields;
	}

	public PortalBillingConfigFields getPortalBillingConfigFields() {
		return portalBillingConfigFields;
	}

	public void setPortalBillingConfigFields(
			PortalBillingConfigFields portalBillingConfigFields) {
		this.portalBillingConfigFields = portalBillingConfigFields;
	}

	public List<PrincipalDoctor> getPrincipalDoctorsList() {
		return principalDoctorsList;
	}

	public void setPrincipalDoctorsList(List<PrincipalDoctor> principalDoctorsList) {
		this.principalDoctorsList = principalDoctorsList;
	}
	
}
