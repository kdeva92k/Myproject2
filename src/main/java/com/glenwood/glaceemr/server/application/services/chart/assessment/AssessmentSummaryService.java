package com.glenwood.glaceemr.server.application.services.chart.assessment;

import java.util.List;

import org.json.JSONObject;
import com.glenwood.glaceemr.server.application.models.PatientAssessments;


public interface AssessmentSummaryService {

	/**
	 * Method to retrieve current visit data
	 * @param patienId
	 * @param encounterId
	 * @return
	 * @throws Exception
	 */
	List<PatientAssessments> getCurrentDiagnosis(Integer patienId,Integer encounterId) throws Exception;
	
	/**
	 * Method to save current visit data
	 * @param assessListobj
	 * @return
	 * @throws Exception
	 */
	Boolean saveDiagnosis(JSONObject assessListobj) throws Exception;
	
	/**
	 * Method to fetch data for edit page
	 * @param patientId
	 * @param encounterId
	 * @param dxCode
	 * @param problemId
	 * @return
	 */
	List<PatientAssessments> getEditData(Integer patientId, Integer encounterId,
			String dxCode, Integer problemId);

	/**
	 * Method to send current encounter assessments to problem list 
	 * @param patientId
	 * @param encounterId
	 * @param userId
	 * @return
	 * @throws Exception
	 */

	String moveToProblemList(Integer patientId,Integer encounterId,Integer userId) throws Exception;
}

