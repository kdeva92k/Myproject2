package com.glenwood.glaceemr.server.application.services.labresults;

import java.util.Hashtable;
import java.util.List;

public interface ResultParametersService {

	/**
	 * Method to get result parameters
	 * @param resultXML
	 * @param labCompName
	 * @return
	 */
	String getResultParam(String paramXML, String labCompName) throws Exception;
		
	/**
	 * Method to get parameters and values from result XML
	 * @param resultXML
	 * @param map
	 * @param testDetailId
	 */
	void getParameterValues(String resultXML, List<Parameters> map,String testDetailId) throws Exception;

	/**
	 * Method to get lab location details
	 * @param resultXML
	 * @param labCompany
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	Hashtable getLabLocationDetails(String resultXML, String labCompany) throws Exception;
	
	/**
	 * Method to get local test detail id
	 * @param labCompanyId
	 * @param testcode
	 * @param testname
	 * @return
	 */
	Integer getLocalTestId(Integer labCompanyId, String testcode, String testname);
	
	/**
	 * Find testDetailId if received test is already ordered for patient
	 * @param accountNo
	 * @param chartId
	 * @param labAccessionNo
	 * @param testCode
	 * @param testId
	 * @param testName
	 * @param orderedDate
	 * @param orderId
	 * @return
	 */
	public Integer getTestDetailId(String accountNo, Integer chartId, String labAccessionNo, String testCode, Integer testId, String testName, String orderedDate, String orderId);

	/**
	 * Method to insert or update config table
	 * @param chartId
	 * @param testName
	 * @param testDetailId
	 * @param testCode
	 * @param labcompId
	 * @throws Exception
	 */
	public void insertConfigTbl(String chartId,String testName,String testDetailId,String testCode,int labcompId) throws Exception;

	/**
	 * Method to import parameters from file details
	 */
	@SuppressWarnings("rawtypes")
	void importParameters(String chartId, String testDetailId,
			String paramString, Hashtable labLocCodeDetails, String rootPath,
			String resultFileName, int status, int orderedBy)
			throws NumberFormatException, Exception;

	/**
	 * Method to find the encounters open
	 */
	Integer findEncounters(Integer chartId, String orderedate,
			Integer encounterType);

	/**
	 * Method to create new external encounter for the result
	 */
	Integer addNewExternalEncounter(String orderDate, Integer chartId, String userId);

	/**
	 * Method to get result inbox account no for unattached results
	 * @param hl7FileId
	 * @return
	 */
	String getAccountNo(String hl7FileId, List<Integer> status);

	/**
	 * Method to get result status
	 * @param hl7FileId
	 * @return
	 */
	Integer getResultStatus(String hl7FileId);

	/**
	 * Method to get result is reviewed or not
	 * @param hl7FileId
	 * @return
	 */
	String getIsReviewed(String hl7FileId);

	String getUnmappedOrderedDate(String hl7FileId);

	String getDataFromFile(String sharedFolderPath, String fileName) throws Exception;

	void putPDFAttacmentEntry(int encounterId, int patientID, String chartId,
			int testDetailId, String resultFileName, String rootPath,
			String encryptedData) throws Exception;

	void createFolder(String folderLocation) throws Exception;

	boolean moveFile(String sourcePath, String desPath, String sourcefileName,
			boolean appendDate) throws Exception;

	long saveFileDetail(FileDetailBean filedetail) throws Exception;
}
