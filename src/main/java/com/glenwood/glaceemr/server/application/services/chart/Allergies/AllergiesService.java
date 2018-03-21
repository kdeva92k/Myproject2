package com.glenwood.glaceemr.server.application.services.chart.Allergies;

import java.util.List;

import org.json.JSONArray;
import com.glenwood.glaceemr.server.application.models.PatientAllergies;
import com.glenwood.glaceemr.server.application.services.chart.CurrentMedication.PatientAllergiesBean;

public interface AllergiesService {

	List<AllergiesTypeBean> retrievingAllergyType(int gender);

	AllergyBean searchAllergy(String value, int mode, int offset,
			int limit);

	AllergyBean getReactionsOfAllergies(String criteria, int offset, int limit);

	List<PatientAllergies> getNKDAandNKACheckDetails(Integer chartId);

	void saveNewAllergies(Integer save,
			String insertParameters);

	void editAllergies(String editData);

	void reviewAllergies(String reviewData);

	String deleteAllergy(String allergyid);

	void nkdaUpdate(String nkdaData);

	void nkaUpdate(String nkaData) throws Exception;

	void nkdauncheck(String nkdaData);

	void nkauncheck(String nkaData);

	List<PatientAllergiesBean> retrieveDataForEditAllerg(String chartId, String patId);

	JSONArray lastReviewDetails(String chartId);

	List<PatientAllergiesBean> retrieveInActiveAllerg(String chartId, String statusList);
	
	List<PatientAllergiesHistoryBean> patientAllergiesHistory(String chartId);

	void saveAllergies(String allergData);

	List<PatientAllergiesBean> getAllergies(int chartId,int encounterId,String statusList,int fromSoap);

}