package com.glenwood.glaceemr.server.application.services.chart.patientreview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.CnmSettings;
import com.glenwood.glaceemr.server.application.models.PatientReviewedDetails;
import com.glenwood.glaceemr.server.application.repositories.CNMSettingsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientReviewRepository;
import com.glenwood.glaceemr.server.application.specifications.CNMSettingsSpecification;
import com.glenwood.glaceemr.server.application.specifications.PatientReviewedSpecification;

/**
 * Service implementation file for HistoryController
 * @author Chandrahas
 */
@Service
public class PatientReviewServiceImpl implements PatientReviewService{

	@Autowired
	PatientReviewRepository patientReviewRepository;
	
	@Autowired
	CNMSettingsRepository cnmSettingsRepository;
	
	/**
	 * Method to get patient review information
	 * @param chartId
	 */
	@Override
	public PatientReviewDetailsBean getReviewInfo(Integer chartId) {
		
		PatientReviewDetailsBean reviewBean = new PatientReviewDetailsBean();
		
		List<CnmSettings> cnmsettingsList =  cnmSettingsRepository.findAll(CNMSettingsSpecification.getReviewDetails(1010));
		if(cnmsettingsList.size() > 0)
			reviewBean.setIsReviewNeeded(true);
		else
			reviewBean.setIsReviewNeeded(false);
		
		List<PatientReviewedDetails> reviewList =  patientReviewRepository.findAll(PatientReviewedSpecification.getReviewDetails(chartId));
		reviewBean.setPatientReviewList(reviewList);
		
		return reviewBean;
	}

	/**
	 * Method to save patient review information
	 */
	@Override
	public void saveReviewInfo(PatientReviewedDetails patientReviewed) {
		patientReviewRepository.save(patientReviewed);		
	}
	
}
