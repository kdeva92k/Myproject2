package com.glenwood.glaceemr.server.application.services.chart.CurrentMedication;


import java.util.List;

public class CurrentMedDataBean {
	Boolean transitionOfCare;
	Boolean summaryOfCare;
	Boolean noMedicationFlag;
	List<MedicationDetailBean> currentMeds;
	
	public Boolean getTransitionOfCare() {
		return transitionOfCare;
	}

	public void setTransitionOfCare(Boolean transitionOfCare) {
		this.transitionOfCare = transitionOfCare;
	}

	public Boolean getSummaryOfCare() {
		return summaryOfCare;
	}

	public void setSummaryOfCare(Boolean summaryOfCare) {
		this.summaryOfCare = summaryOfCare;
	}

	public Boolean getNoMedicationFlag() {
		return noMedicationFlag;
	}

	public void setNoMedicationFlag(Boolean noMedicationFlag) {
		this.noMedicationFlag = noMedicationFlag;
	}

	public List<MedicationDetailBean> getCurrentMeds() {
		return currentMeds;
	}

	public void setCurrentMeds(List<MedicationDetailBean> currentMeds) {
		this.currentMeds = currentMeds;
	}

	public CurrentMedDataBean(Boolean transitionOfCare,Boolean summaryOfCare,Boolean noMedicationFlag,List<MedicationDetailBean> medDetails){
		this.transitionOfCare=transitionOfCare;
		this.summaryOfCare=summaryOfCare;
		this.noMedicationFlag=noMedicationFlag;
		this.currentMeds=medDetails;
	}

}
