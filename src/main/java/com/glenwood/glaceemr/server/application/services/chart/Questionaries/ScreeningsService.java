package com.glenwood.glaceemr.server.application.services.chart.Questionaries;

import java.util.List;
import java.util.Map;

import com.glenwood.glaceemr.server.application.models.CarePlanConcern;

public interface ScreeningsService{

	List<RiskAssessmentBean> getScreeningsList(Integer patientId,Integer encounterId,Integer ScreenId, Integer riskscreenId);

	//List<Object> fetchFrequentInterventions(Integer userId);

	String getSearchScreenings(String searchString);

	String getScreeningInfo(Integer screenId);

	void saveScreeningsInfo(RiskAssessmentBean riskAssessmentBean);

	void SaveScreeningsquestions(List<PatientClinicalFindingsBean> patientClinicalFindingsBean, Integer patientId, Integer encounterId, Integer riskAssessmentId, Integer userId,Integer Mode);

	//List<PatientClinicalFindingsBean> editScreeningQuestions(Integer screeningId, Integer patientId,Integer encounterId);

	void saveScreeningStatus(Integer riskAssessmentId, Integer patientId,Integer encounterId, Integer status);

	Map<String, Object> editScreeningQuestions(Integer riskAssessmentId,Integer patientId, Integer encounterId);

	void updateriskAssessmentScore(Integer patientId, Integer encounterId, Integer riskAssessmentId,Integer answerScore, String notes, Integer status, Integer userId);

	void saveScreeningResult(Integer riskAssessmentId, Integer patientId,Integer encounterId, Integer status, String description,
			String code, String codeSys, Integer userId);

	String getSearchInterventions(String searchString);

	void deleteSnomedIntervention(Integer delId, Integer encounterId,Integer patientId);

	Map<String, Object> getPreviousData(String prevDate,Integer patientId, Integer encounterId, Integer riskAssId,Integer userId,Integer screenId);

	List<PatientClinicalFindingsBean> getScreeningsStatus(Integer patientId,Integer encounterId, String lOINC, String startDate, String endDate);

	String getScreenInterventions(Integer screenId);

	void deleteScreeningInfo(Integer patientId, Integer encounterId,Integer riskId, String screenId);
}