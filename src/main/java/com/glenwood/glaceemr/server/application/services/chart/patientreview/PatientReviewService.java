package com.glenwood.glaceemr.server.application.services.chart.patientreview;

import com.glenwood.glaceemr.server.application.models.PatientReviewedDetails;

/**
 * Service file for HistoryController
 * @author 
 *
 */
public interface PatientReviewService {

	public PatientReviewDetailsBean getReviewInfo(Integer chartId);

	public void saveReviewInfo(PatientReviewedDetails historyReviewed);
	
}
