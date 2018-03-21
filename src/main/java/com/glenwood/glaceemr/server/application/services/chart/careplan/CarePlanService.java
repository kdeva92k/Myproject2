package com.glenwood.glaceemr.server.application.services.chart.careplan;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.glenwood.glaceemr.server.application.models.CarePlanConcern;
import com.glenwood.glaceemr.server.application.models.CarePlanIntervention;
import com.glenwood.glaceemr.server.application.models.CarePlanRecommendedIntervention;

public interface CarePlanService{
	
	List<CarePlanConcernBean> fetchCarePlanConcerns(Integer concernId,Integer patientId,Integer categoryId,Integer episodeId,Integer encounterId,String frmDate,String toDate);
	
	List<CarePlanGoalBean> fetchCarePlanGoals(Integer goalId,Integer concernId,Integer patientId,Integer encoutnerId,Integer episodeId,String frmDate,String toDate);
	
	List<CarePlanOutcomeBean> fetchCarePlanOutcomes(Integer outcomeId,Integer goalId,Integer patientId,Integer encoutnerId,Integer episodeId,String frmDate,String toDate);

	List<CarePlanGoalBean> fetchCarePlanOutcomesForCDA(Integer outcomeId,Integer goalId,Integer patientId,Integer encoutnerId,Integer episodeId,String frmDate,String toDate);

	Map<String,Object> getCarePlanInitialData(Integer patientId,Integer encounterId,Integer episodeId,Integer episodeTypeId,Integer previousEpisodeId);	
	List<CarePlanConcernBean> saveCarePlanConcern(CarePlanConcernBean carePlanConcerns);
	
	List<CarePlanGoalBean>  saveCarePlanGoal(CarePlanGoalBean carePlanGoal);
	
	List<CarePlanGoalBean>  saveCarePlanOutcomes(Integer goalId,Integer providerId,Integer patientId,Integer encounterId,Integer progress,String reviewDate,String targetDate,String notes,Integer outcomeStatus,Integer episodeId,Integer goalAssisStatus,Integer goalLevelStatus,Boolean targetedGoal);

	String getVitals(Integer patientId,Integer encounterId);
		
	List<Object> fetchCarePlanShortcuts(Integer categoryId,String searchType,String searchStr);

	Map<String,Object> importCarePlanShortcuts(Integer patientId,Integer encounterId,String shortcutIDs,Integer providerId,Integer episodeId,Integer shortcutTerm,Integer categoryId,Integer previousEpisodeId,Integer summaryMode) throws ParseException;
	
	void saveProgressPlanNotes(Integer encounterId,String planText);
	
	void clearProgressPlanNotes(Integer encounterId);
	
	void saveProgressSubjectiveNotes(String gwid,Integer chartId,Integer patientId,Integer encounterId,String planText);
	
	void clearProgressSubjectiveNotes(String gwid,Integer patientId,Integer encounterId);
	
	Map<String,Object> getCarePlanProgressInitialData(Integer patientId,Integer encounterId,Integer episodeId,String gwid) throws ParseException;
	
	Map<String,Object> importCarePlanShortcutsFromPrevious(Integer patientId,Integer encounterId,String previousGoalShortcutIDs,Integer providerId,Integer episodeId,Integer shortcutTerm,Integer categoryId,Integer previousEpisodeId);
	
	CarePlanIntervention saveCarePlanIntervention(Integer patientId, Integer encounterId, String description, String snomedCode, Integer status, Integer userId, Integer interventionMode, String intervenDxCode, String intervenDxDesc, String intervenDxCodeSystem);

	List<CarePlanInterventionBean> fetchCarePlanInterventions(Integer patientId, Integer encounterId, Integer interventionMode, String intervenDxCode,Integer Status,String frmDate,String toDate);

	void deleteCarePlanIntervention(Integer patientId, Integer encounterId, Integer delVal);
	
	void updateCarePlanIntervention(Integer patientId, Integer encounterId, Integer interventionId,  String editedNotes, Integer orderedBy,	Integer performedBy,Integer notDoneBy, String notDoneReason, Integer userId, Integer status, String perfOn, String orderedOn);

	Map<String, Object> getEditCarePlanIntervention(Integer patientId, Integer encounterId, Integer intervenId);

	void saveInterventionData(List<CarePlanInterventionBean> carePlanInterventionBean);

	//List<CarePlanInterventionBean> saveInterventionData(CarePlanInterventionBean carePlanInterventionBean);
	
	Map<String, Object> getCarePlanSummaryData(Integer patientId, Integer episodeId, Integer encounterId, Integer episodeTypeId) throws ParseException;
	
	void saveCarePlanSummaryData(Integer patientId,Integer encounterId,Integer episodeId,String completeJSON,Integer userId) throws Exception;

	Map<String, Object> saveConcernAndGoal(
			CarePlanConcernBean carePlanConcernJSON, int previousEpisodeId);

	Map<String, Object> showInactiveConcerns(int patientId,int encounterId,int episodeId);

	List<CarePlanRecommendedInterventionBean> saveCarePlanRecommendedIntervention(CarePlanRecommendedInterventionBean carePlanRecommendedInterventionBean);

	List<CarePlanRecommendedInterventionBean> fetchRecommIntervention(Integer patientId,Integer encounterId,Integer episodeId,String frmDate,String toDate);
	
	void deleteCarePlanRecommIntervention(Integer patientId, Integer encounterId, Integer delVal);
	
	List<Object[]>  saveCarePlanLog(Integer patientId,Integer userId,Boolean reviewStatus);
	
	Map<String, Object> getCarePlanPrint(Integer patientId,Integer encounterId,Integer episodeId);
	
	void addFrequentIntervention(String elementName,String snomed,Integer userId, Integer providerId, Integer isfrmconfig, String categoryType, String codeOid,String groupName) throws Exception;

	List<Object> fetchFrequentInterventions(Integer userId, String categoryType);

	void deleteFrequentIntervention(String delid);

	void UpdateFrequentInterventionGroup(Integer groupVal, String groupName);

	Map<String, Object> FrequentInterventionGroups(String groupName);
	
	void saveCarePlanStatus(Integer patientId,Integer encounterId,Integer episodeId,String Description,Integer userId,Integer Status,String code);
	
	List<Object> getCarePlanStatus(Integer patientId, Integer encounterId,Integer episodeId);
	
	List<CarePlanInterventionBean> fetchInterventionPlanData(Integer goalId,Integer concernId,
			Integer CategoryId, Integer patientId, Integer encounterId,Integer intervenId);

	List<Object> getImportShortcuts(Integer patientId, Integer encounterId);

	List<CarePlanGoalBean>  saveAssistanceStatus(Integer patientId,Integer episodeId,Integer goalId,Integer providerId,Integer status,Integer levelStatus,Integer encounterId);
	
}