package com.glenwood.glaceemr.server.application.services.chart.HPI;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;

public class SymptomAndNegativeSymptomList {
	List<PatientClinicalElements> symptomList;
	List<PatientClinicalElements> negativeSymptomList;
	public List<PatientClinicalElements> getSymptomList() {
		return symptomList;
	}
	public void setSymptomList(List<PatientClinicalElements> symptomList) {
		this.symptomList = symptomList;
	}
	public List<PatientClinicalElements> getNegativeSymptomList() {
		return negativeSymptomList;
	}
	public void setNegativeSymptomList(
			List<PatientClinicalElements> negativeSymptomList) {
		this.negativeSymptomList = negativeSymptomList;
	}
	
}
