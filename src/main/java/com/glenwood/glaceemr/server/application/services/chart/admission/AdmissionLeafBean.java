package com.glenwood.glaceemr.server.application.services.chart.admission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.glenwood.glaceemr.server.application.models.FaxOutbox;
import com.glenwood.glaceemr.server.application.models.LeafPatient;
import com.glenwood.glaceemr.server.application.models.PatientAllergies;


public class AdmissionLeafBean {
	
	List<LeafPatient> admissionLeafBean = Collections.emptyList();
	List<FaxOutbox> admissionLeafFaxBean = Collections.emptyList();
	List<PatientAllergies> allergyBean = Collections.emptyList();
	
	public AdmissionLeafBean(){
		admissionLeafBean = new ArrayList<LeafPatient>();
		admissionLeafFaxBean = new ArrayList<FaxOutbox>();
		allergyBean = new ArrayList<PatientAllergies>();
	}

	
	public List<PatientAllergies> getAllergyBean() {
		return allergyBean;
	}

	public void setAllergyBean(List<PatientAllergies> allergyBean) {
		this.allergyBean = allergyBean;
	}


	public List<LeafPatient> getAdmissionLeafBean() {
		return admissionLeafBean;
	}

	public void setAdmissionLeafBean(List<LeafPatient> admissionLeafBean) {
		this.admissionLeafBean = admissionLeafBean;
	}

	public List<FaxOutbox> getAdmissionLeafFaxBean() {
		return admissionLeafFaxBean;
	}

	public void setAdmissionLeafFaxBean(List<FaxOutbox> admissionLeafFaxBean) {
		this.admissionLeafFaxBean = admissionLeafFaxBean;
	}
	
	
}
