package com.glenwood.glaceemr.server.application.services.chart.patientreview;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.PatientReviewedDetails;

/**
 * Bean to set patient review details
 * @author Chandrahas
 *
 */
public class PatientReviewDetailsBean {

	Boolean isReviewNeeded;
	
	List<PatientReviewedDetails> patientReviewList;

	public Boolean getIsReviewNeeded() {
		return isReviewNeeded;
	}

	public void setIsReviewNeeded(Boolean isReviewNeeded) {
		this.isReviewNeeded = isReviewNeeded;
	}

	public List<PatientReviewedDetails> getPatientReviewList() {
		return patientReviewList;
	}

	public void setPatientReviewList(List<PatientReviewedDetails> patientReviewList) {
		this.patientReviewList = patientReviewList;
	}
	
}
