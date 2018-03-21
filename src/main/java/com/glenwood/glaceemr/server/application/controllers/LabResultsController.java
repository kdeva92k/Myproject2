package com.glenwood.glaceemr.server.application.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailService;
import com.glenwood.glaceemr.server.application.services.labresults.AttachLabData;
import com.glenwood.glaceemr.server.application.services.labresults.DocumentData;
import com.glenwood.glaceemr.server.application.services.labresults.LabResultsService;
import com.glenwood.glaceemr.server.application.services.labresults.ResultDetails;
import com.glenwood.glaceemr.server.application.services.labresults.SaveData;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;


/**
 * @author yasodha
 *
 * Controller for Lab results in GlaceEMR, 
 * contains request to get the list of results, reviewed results.
 * and ability to filter results based on ordered date of the lab.
 */
@RestController
@Transactional
@RequestMapping(value = "/user/LabResults")
public class LabResultsController {
	
	@Autowired
	AuditTrailService auditTrailService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;

	@Autowired
	SessionMap sessionMap;

	@Autowired
	HttpServletRequest request;

	@Autowired
	LabResultsService labResultsService;

	private Logger logger = Logger.getLogger(InvestigationSummaryController.class);
	
	/**
	 * Method to get users list
	 * @return usersList
	 * @throws Exception
	 */
	@RequestMapping(value = "/ListOfUsers",method = RequestMethod.GET)
	public EMRResponseBean getUsersList() throws Exception {
		logger.debug("in getting pending orders in investigations");
//		List<UsersList> listOfUsers = labResultsService.getListOfUsers();		
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(labResultsService.getListOfUsers());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.VIEW, -1,Log_Outcome.SUCCESS,"Success in getting the list of users" , -1,
				request.getRemoteAddr() ,-1 , "",LogUserType.USER_LOGIN ,"","");
        return emrResponseBean;
	}
	
	/**
	 * Method to get todays orders
	 * @throws Exception
	 */
	@RequestMapping(value = "/TodaysOrders",method = RequestMethod.GET)
	public EMRResponseBean getTodaysOrders(@RequestParam(value="orderedOn", required=false, defaultValue="") String orderedOn,
			@RequestParam(value="patientId", required=false, defaultValue="") String patientId) throws Exception {
		logger.debug("in getting list of todays orders");
//		List<LabEntries> orderedLabs = labResultsService.getOrderedList(orderedOn, patientId);
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(labResultsService.getOrderedList(orderedOn, patientId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.VIEW, -1,Log_Outcome.SUCCESS,"Success in getting the list of ordered labs" , -1,
				request.getRemoteAddr() , Integer.parseInt(patientId) , "",LogUserType.USER_LOGIN ,"","");
        return emrResponseBean;
	}
	
	/**
	 * Method to get todays orders
	 * @throws Exception
	 */
	@RequestMapping(value = "/PendingOrders",method = RequestMethod.GET)
	public EMRResponseBean getPendingOrders(@RequestParam(value="orderedOn", required=false, defaultValue="") String orderedOn,
			@RequestParam(value="patientId", required=false, defaultValue="") String patientId) throws Exception {
		logger.debug("in getting list od pending orders");
//		List<LabEntries> pendingLabs = labResultsService.getPendingList(orderedOn, patientId);
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(labResultsService.getPendingList(orderedOn, patientId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.VIEW, -1,Log_Outcome.SUCCESS,"Success in getting the the list of pending lab" , -1,
				request.getRemoteAddr() , Integer.parseInt(patientId) , "",LogUserType.USER_LOGIN ,"","");
        return emrResponseBean;
	}
	
	/**
	 * Method to get todays orders
	 * @throws Exception
	 */
	@RequestMapping(value = "/LabMapping",method = RequestMethod.GET)
	public Boolean checkLabMapping(@RequestParam(value="patientId", required=false, defaultValue="") String patientId,
			@RequestParam(value="hl7Id", required=false, defaultValue="") String hl7Id,
			@RequestParam(value="testName", required=false, defaultValue="") String testName) throws Exception {
		logger.debug("in checking mapping for labs");
		Integer chartId = Integer.parseInt(labResultsService.getChartId(patientId));
		boolean isMapped = labResultsService.checkForMapping(chartId, Integer.parseInt(hl7Id), testName);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.READ, -1,Log_Outcome.SUCCESS,"Success in verifying the mappings of the lab" , -1,
				request.getRemoteAddr() , Integer.parseInt(patientId) , "chartId="+chartId+"|hl7FileId="+hl7Id+"|testName="+testName,LogUserType.USER_LOGIN ,"","");
		return isMapped;
	}
	
	/**
	 * Method to get todays orders
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteLab",method = RequestMethod.GET)
	public String deleteLab(@RequestParam(value="hl7Id", required=false, defaultValue="") String hl7Id,
			@RequestParam(value="testName", required=false, defaultValue="") String testName, @RequestParam(value="orderedOn", required=false, defaultValue="") String orderedOn) throws Exception {
		logger.debug("in deleting unmapped labs");
		String result = labResultsService.deleteUnmappedLab(hl7Id, testName, orderedOn);
		return result;
	}
	
	/**
	 * Method to get list of results
	 * @throws Exception
	 */
	@RequestMapping(value = "/ResultsList",method = RequestMethod.GET)
	public EMRResponseBean getResultList(@RequestParam(value="doctorId", required=false, defaultValue="") String doctorId,
			@RequestParam(value="isReviewed", required=false, defaultValue="") String isReviewed,
			@RequestParam(value="orderedDate", required=false, defaultValue="") String orderedDate,
			@RequestParam(value="pageNo", required=false, defaultValue="") Integer pageNo,
			@RequestParam(value="pageSize", required=false, defaultValue="") Integer pageSize) throws Exception {
		logger.debug("in getting list of results");
//		List<ResultList> orderSetData = labResultsService.getListOfResults(doctorId, isReviewed, orderedDate, pageNo, pageSize);
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(labResultsService.getListOfResults(doctorId, isReviewed, orderedDate, pageNo, pageSize));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.VIEW, -1,Log_Outcome.SUCCESS,"Success in getting the list of results received for the user" , -1,
				request.getRemoteAddr() , -1 , "doctorId="+doctorId,LogUserType.USER_LOGIN ,"","");
        return emrResponseBean;
	}
	
	/**
	 * Method to get patient result data
	 * @throws Exception
	 */
	@RequestMapping(value = "/ResultsDetails",method = RequestMethod.GET)
	public ResultDetails getResultDetails(@RequestParam(value="hl7FileId", required=false, defaultValue="") String hl7FileId) {
		logger.debug("in getting complete details of each patient result");
		ResultDetails patientResults = labResultsService.getPatientResultData(hl7FileId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.VIEW, -1,Log_Outcome.SUCCESS,"Success in getting the complete details of the received result" , -1,
				request.getRemoteAddr() , -1 , "hl7FileId="+hl7FileId,LogUserType.USER_LOGIN ,"","");
		return patientResults;
	}
	
	/**
	 * Method to attach result to patient
	 * @throws Exception
	 */
	@RequestMapping(value = "/attachToPatient",method = RequestMethod.GET)
	public String attachResults(@RequestParam(value="hl7FileId", required=false, defaultValue="") String hl7FileId,
			@RequestParam(value="patientId", required=false, defaultValue="") String patientId,
			@RequestParam(value="userId", required=false, defaultValue="") String userId,
			@RequestParam(value="sharedFolderPath", required=false, defaultValue="") String sharedFodlerPath) {
		logger.debug("in attaching result to patient");
		labResultsService.attachToPatient(hl7FileId, patientId, userId, sharedFodlerPath);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.ATTACH, -1,Log_Outcome.SUCCESS,"Success in attaching the result to a Patient" , -1,
				request.getRemoteAddr() , Integer.parseInt(patientId) , "hl7FileId="+hl7FileId+"|userId="+userId,LogUserType.USER_LOGIN ,"","");
		return "successfully attached";
	}
	
	@RequestMapping(value = "/getPreviousOrders", method = RequestMethod.GET)
	public ResultDetails getPreviousOrders(@RequestParam(value="hl7FileId", required=false, defaultValue="-1") String hl7FileId) {
		logger.debug("in getting previous orders");		
		return labResultsService.findPreviousOrders(hl7FileId);
	}
	
	/**
	 * Method to attach result to patient
	 * @throws Exception
	 */
	@RequestMapping(value = "/attachToLabs",method = RequestMethod.PUT)
	public String attachLabs(@RequestBody AttachLabData attachLabData) throws Exception {
		logger.debug("in attaching result to ordered labs");
		labResultsService.attachToOrderedLabs(attachLabData);
		return "successfully attached";
	}
	
	@RequestMapping(value = "/SaveLabData",method = RequestMethod.POST)
	public String saveLabData(@RequestBody SaveData labDetails) throws Exception {
		logger.debug("Begin of save the complete details for a particular lab from Hl7");
		labResultsService.saveLabDetails(labDetails);
		logger.debug("End of save the complete details for a particular lab");
		return "Successfully saved";
	}
	
	@RequestMapping(value = "/DocumentsData", method = RequestMethod.GET)
	public DocumentData getDocumentsInfo(@RequestParam(value="fileType", required=false, defaultValue="-1") String fileType,
			@RequestParam(value="patientId", required=false, defaultValue="-1") String patientId,
			@RequestParam(value="documentId", required=false, defaultValue="-1") String documentId,
			@RequestParam(value="sharedFolderPath", required=false, defaultValue="") String sharedFolderPath) {
		logger.debug("in getting document data");		
		return labResultsService.getDocumentData(fileType, patientId, documentId, sharedFolderPath);
	}
	
	@RequestMapping(value = "/ReviewDocuments", method = RequestMethod.GET)
	public String reviewDocuments(@RequestParam(value="fileId", required=false, defaultValue="-1") String fileId,
			@RequestParam(value="userId", required=false, defaultValue="-1") String userId) {
		logger.debug("in reviewing documents");	
		return labResultsService.reviewFile(fileId, userId);
	}
	
	@RequestMapping(value = "/PdfInfo", method = RequestMethod.GET)
	public String getPDFData(@RequestParam(value="testDetailId", required=false, defaultValue="-1") String testDetailId) {
		logger.debug("in getting PDF name");		
		return labResultsService.getPDFName(testDetailId);
	}
}