package com.glenwood.glaceemr.server.application.services.labresults;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.LabEntries;


/**
 * @author yasodha
 *
 * LabResultsService is an interface holds the method
 * declarations and predicates required for external lab results
 */
public interface LabResultsService {

	/**
	 * Method to get list of users
	 * @throws Exception
	 */
	List<UsersList> getListOfUsers() throws Exception;

	/**
	 * Method to get list of results
	 * @param doctorId
	 * @param isReviewed
	 * @param orderedDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	List<ResultList> getListOfResults(String doctorId, String isReviewed, String orderedDate, Integer pageNo, Integer pageSize) throws Exception;

	/**
	 * Service method to get patient result details
	 * @param hl7FileId
	 * @return
	 */
	ResultDetails getPatientResultData(String hl7FileId);

	/**
	 * Method to attach result to patient
	 * @param hl7FileId
	 * @param patientId
	 * @param userId
	 * @param sharedFodlerPath 
	 */
	void attachToPatient(String hl7FileId, String patientId, String userId, String sharedFodlerPath);

	/**
	 * Method to save lab details
	 * @param labDetails
	 */
	void saveLabDetails(SaveData labDetails) throws Exception;

	/**
	 * Method to attach results to already ordered labs
	 * @throws Exception 
	 */
	void attachToOrderedLabs(AttachLabData attachLabData) throws Exception;

	/**
	 * Method to list the pending orders
	 * @param hl7FileId
	 * @return 
	 */
	ResultDetails findPreviousOrders(String hl7FileId);

	List<LabEntries> getOrderedList(String orderedOn, String patientId);

	List<LabEntries> getPendingList(String orderedOn, String patientId);

	String getChartId(String patientId);

	boolean checkForMapping(Integer chartId, Integer hl7FileId, String curTestName) throws Exception;

	String deleteUnmappedLab(String hl7Id, String testName, String orderedOn);

	DocumentData getDocumentData(String fileType, String patientId, String documentId, String sharedFolderPath);

	String reviewFile(String fileId, String userId);

	String getPDFName(String testDetailId);
}
