package com.glenwood.glaceemr.server.application.services.labresults;

/**
 * @author yasodha
 *
 * LabResultsService is an interface holds the method
 * declarations and predicates required for external lab documents
 */
public interface LabDocsService {

	/**
	 * Method to get HTML content of a file
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	String getFileContent(String filePath) throws Exception;

	/**
	 * Method to get lab name
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	String getLabName(String filePath) throws Exception;

}
