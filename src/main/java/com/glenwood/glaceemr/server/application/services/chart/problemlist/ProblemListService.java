package com.glenwood.glaceemr.server.application.services.chart.problemlist;

import java.util.List;

import org.json.JSONException;
import com.glenwood.glaceemr.server.application.models.ProblemList;

/**
 * Service contains all the operations related to problem list
 * @author Mamatha
 *
 */
public interface ProblemListService {

	/**
	 * Method to get active problems list
	 * @param patientId
	 * @return
	 * @throws Exception
	 */
	List<ProblemList> getActiveProblems(Integer patientId) throws Exception;

	/**
	 * Method to retrieve inactive and resolved problems
	 * @param patientId
	 * @return
	 * @throws Exception
	 */
	List<ProblemList> getInactiveProblems(Integer patientId) throws Exception;
	
	/**
	 * Method to fetch edit page information
	 * @param patientId
	 * @param problemId
	 * @return
	 * @throws Exception
	 */
	List<ProblemList> getEditData(Integer patientId, Integer problemId) throws Exception;

	/**
	 * Method to save edit page information
	 * @param patientId
	 * @param userId
	 * @param saveData
	 * @throws JSONException
	 * @throws Exception
	 */
	void editDataSave(Integer patientId, Integer userId, String saveData) throws JSONException, Exception;

	/**
	 * Method to delete a problem from problem list
	 * @param patientId
	 * @param deleteData
	 * @throws Exception
	 */
	void deleteDataSave(Integer patientId, String deleteData) throws Exception;

	List<ProblemList> deleteDataSaveFetch(Integer patientId, String deleteData) throws JSONException;

}
