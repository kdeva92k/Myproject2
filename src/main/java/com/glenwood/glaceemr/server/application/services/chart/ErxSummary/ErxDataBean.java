package com.glenwood.glaceemr.server.application.services.chart.ErxSummary;

import java.util.List;

public class ErxDataBean {

	List<ErxPatientDataBean> patientdetails;
	List<DoctorDetailBean> providerdetails;
	List<PharmacyBean> pharmacydetails;
	List<NewRxBean> medicationdetails;
	
	public ErxDataBean(List<ErxPatientDataBean> patientdetails,List<DoctorDetailBean> providerdetails, List<PharmacyBean> pharmacydetails,List<NewRxBean> medicationdetails) {
	
		this.patientdetails=patientdetails;
		this.providerdetails=providerdetails;
		this.pharmacydetails=pharmacydetails;
		this.medicationdetails=medicationdetails;
	}

	public List<ErxPatientDataBean> getPatientdetails() {
		return patientdetails;
	}

	public void setPatientdetails(List<ErxPatientDataBean> patientdetails) {
		this.patientdetails = patientdetails;
	}

	public List<DoctorDetailBean> getProviderdetails() {
		return providerdetails;
	}

	public void setProviderdetails(List<DoctorDetailBean> providerdetails) {
		this.providerdetails = providerdetails;
	}

	public List<PharmacyBean> getPharmacydetails() {
		return pharmacydetails;
	}

	public void setPharmacydetails(List<PharmacyBean> pharmacydetails) {
		this.pharmacydetails = pharmacydetails;
	}

	public List<NewRxBean> getMedicationdetails() {
		return medicationdetails;
	}

	public void setMedicationdetails(List<NewRxBean> medicationdetails) {
		this.medicationdetails = medicationdetails;
	}

}
