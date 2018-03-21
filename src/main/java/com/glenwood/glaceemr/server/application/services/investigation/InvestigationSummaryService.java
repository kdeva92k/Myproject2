package com.glenwood.glaceemr.server.application.services.investigation;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.springframework.data.domain.Page;

import com.glenwood.glaceemr.server.application.models.BodySite;
import com.glenwood.glaceemr.server.application.models.LabDescription;
import com.glenwood.glaceemr.server.application.models.LabEntries;
import com.glenwood.glaceemr.server.application.models.VaccinationConsentForm;
import com.glenwood.glaceemr.server.application.models.VaccineOrderDetails;

/**
 * @author yasodha
 *
 * InvestigationSummaryService is an interface holds the method
 * declarations and predicates required for investigation summary
 */
public interface InvestigationSummaryService {
	
	
	/**
	 * Method to save investigation data received from HL7 results (Parsing and attaching unknown patient results)
	 * @param attachData
	 * @return
	 * @throws Exception
	 */
	public String saveInvestigation(SaveAttachResultData attachData) throws Exception;
	
	/**
	 * Method to get order set data
	 * @throws Exception
	 */
	List<OrderSetData> findOrderSetData() throws Exception;
	
	/**
	 * Save current selected lab
	 * @param testDetails
	 * @param encounterId
	 * @return
	 */
	String saveNewLab(LabDescription testDetails, Integer encounterId, Integer testId,Integer userId, Integer chartId, Integer patientId) throws Exception;
	
	/**
	 * Method to get test details of a newly ordered lab
	 * @param testId
	 * @return
	 */
	LabDescription getNewTestDetails(Integer testId);
	
	/**
	 * Method to get list of labs to be reviewed
	 * @param chartId
	 * @return
	 */
	List<LabEntries> findResultsLabs(Integer chartId);

	/**
	 * Method to get pending orders list
	 * @param encounterId
	 * @param chartId
	 * @return
	 */
	List<LabEntries> findPendingOrders(Integer encounterId, Integer chartId);
	
	/**
	 * Method to update status for a lab
	 * @param testDetailIds
	 * @param encounterId
	 * @param testStatus
	 */
	void performOrDeleteLab(String testDetailIds, String encounterId,
			String testStatus);
	
	/**
	 * Method to get lot number details
	 * @param vaccineId
	 * @param onLoad
	 * @return
	 */
	List<VaccineOrderDetails> findLotDetails(Integer vaccineId, String onLoad);
	
	/**
	 * Method to save vaccine consent
	 * @param consentData
	 */
	List<VaccinationConsentForm> saveVaccineConsent(ConsentDetails consentData);
	
	/**
	 * Method to get the lab details based on
	 * @param testDetailId
	 * @param testId
	 * @param groupId 
	 * @return Orders
	 * @throws Exception 
	 */
	Orders findCompleteTestDetails(Integer testDetailId, Integer testId, String groupId) throws Exception;

	/**
	 * Method to get the body site data
	 * @param searchStr
	 * @param limit
	 * @param offset
	 * @return
	 */
	Page<BodySite> findBodySiteData(String searchStr, Integer limit, Integer offset) throws Exception;

	/**
	 * Method to find the frequent orders
	 * @param encounterId 
	*/	
	List<FrequentOrders> findFrequentOrders(Integer encounterId) throws Exception;
	
	/**
	 * Method to get the lab details based on
	 * @param encounterId
	 * @return LabEntries
	 * @throws Exception 
	 */
	List<LabEntries> findTodaysOrders(Integer encounterId) throws Exception;
	
	public void  savelab(String requesttosave,Integer encounterIdParam,Integer patientIdParam,Integer chartIdParam,
			Integer userIdParam,String fullDataParam,String isforwardParam,String forwardto,
			String ishighpriorityParam,String testidParam) throws Exception;
	LS_Bean findPatientLabDataByChart(Integer chartId) throws Exception;
	LS_Bean getResultsByDate(Integer chartId, String fromDate, String toDate) throws Exception;
	LS_Bean findPatientLabDataByCategory(Integer chartId,Integer category) throws Exception;
	LS_Bean findPatientLabDataByTest(Integer chartId,Integer testId) throws Exception;
	

	/**
	 * Method to delete lab parameters
	 * @param testDetailId
	 * @throws Exception
	 */
	void DeleteParameters(int testDetailId) throws Exception;

	/**
	 * Method to save parameters from Hl7
	 */
	@SuppressWarnings("rawtypes")
	Vector SaveParameters(int chartId, int testDetailId, String paramString,
			Hashtable labLocDetails, String rootPath, String resultFileName)
			throws Exception;

	/**
	 * Method to add lab alert from Hl7 results
	 */
	void addLabAlert(int patientId, int encounterId, int testDetailId,
			int status, int isExternal, int userId) throws Exception;

	/**
	 * Method to get file scan id based on 
	 * @param parameterId
	 * @return
	 */
	String getFileScanId(int parameterId);
	
	/**
	 * Method to get All Investigation data orderby date
	 * @param parameterId
	 * @return
	 */
	public List<OrderLog> findOrderByDateSummary(Integer chartId);

	public String decodeToPDF(String encryptedData, String destPath, String resultFileName) throws Exception;

	/**
	 * Method to find summary of all orders
	 */
	public OrderLogGroups findOrdersSummary(Integer chartId);

	public OrderLogGroups findReviewedSummary(Integer chartId);

	public OrderLogGroups findPendingSummary(Integer chartId);
	
	
}