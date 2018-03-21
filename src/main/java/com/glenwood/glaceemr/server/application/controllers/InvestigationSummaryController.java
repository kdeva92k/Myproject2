package com.glenwood.glaceemr.server.application.controllers;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.LabDescription;
import com.glenwood.glaceemr.server.application.models.VaccinationConsentForm;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.investigation.ConsentDetails;
import com.glenwood.glaceemr.server.application.services.investigation.InvestigationSummaryService;
import com.glenwood.glaceemr.server.application.services.investigation.SaveLabDetails;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

/**
 * @author yasodha
 *
 * Controller for Investigations in GlaceEMR, 
 * contains request to get the list of ordered labs, performed labs and reviewed labs.
 * and ability to order the lab.
 */
@RestController
@Transactional
@RequestMapping(value = "/user/Investigation")
public class InvestigationSummaryController {

	@Autowired
	AuditTrailSaveService auditTrailSaveService;

	@Autowired
	SessionMap sessionMap;

	@Autowired
	HttpServletRequest request;

	@Autowired
	InvestigationSummaryService investigationService;

	private Logger logger = Logger.getLogger(InvestigationSummaryController.class);

	/**
	 * Method to get order set data
	 * @throws Exception
	 */
	@RequestMapping(value = "/OrderSet",method = RequestMethod.GET)
	public EMRResponseBean getOrderSetList() throws Exception {
		logger.debug("in getting order set data");
//		List<OrderSetData> orderSetData = investigationService.findOrderSetData();
		  System.out.println("before request  for orderset "+System.currentTimeMillis());
	        long starttime=System.currentTimeMillis();
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(investigationService.findOrderSetData());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Success in getting order set details",sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"",LogUserType.USER_LOGIN,"","");
		System.out.println("After request  for orderset "+System.currentTimeMillis());
        System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime));
		return emrResponseBean;
	}
	
	/**
	 * Method to get result list
	 * @param encounterId
	 * @param chartId
	 * @return the entity LabEntries
	 * @throws Exception
	 */
	@RequestMapping(value = "/ListOfResults",method = RequestMethod.GET)
	public EMRResponseBean getResultsList(@RequestParam(value="encounterId", required=false, defaultValue="") Integer encounterId,
			@RequestParam(value="chartId", required=false, defaultValue="") Integer chartId) throws Exception {
		logger.debug("in getting pending orders in investigations");
		System.out.println("before request  for results "+System.currentTimeMillis());
        long starttime=System.currentTimeMillis();
//		List<LabEntries> labInfo = investigationService.findResultsLabs(chartId);
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(investigationService.findResultsLabs(chartId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Success in in getting pending orders  chartId="+chartId,sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"chartId="+chartId,LogUserType.USER_LOGIN,"","");
		 System.out.println("After request  for pendingresults "+System.currentTimeMillis());
	        System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime));
        return emrResponseBean;
	}
	
	/**
	 * Method to get pending order list based on
	 * @param encounterId
	 * @return the entity LabEntries
	 * @throws Exception
	 */
	@RequestMapping(value = "/PendingOrders",method = RequestMethod.GET)
	public EMRResponseBean getPendingOrdersList(@RequestParam(value="encounterId", required=false, defaultValue="") Integer encounterId,
			@RequestParam(value="chartId", required=false, defaultValue="") Integer chartId) throws Exception {
		logger.debug("in getting pending orders in investigations");
//		List<LabEntries> labInfo = investigationService.findPendingOrders(encounterId, chartId);
		 System.out.println("before request  for pendingorders "+System.currentTimeMillis());
	        long starttime=System.currentTimeMillis();
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Success in  getting pending orders chartId="+chartId,sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"chartId="+chartId,LogUserType.USER_LOGIN,"","");
		emrResponseBean.setData(investigationService.findPendingOrders(encounterId, chartId));
		System.out.println("After request  for pendingorders "+System.currentTimeMillis());
        System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime));
        return emrResponseBean;
	}

	/**
	 * Method to update test status
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateTestStatus",method = RequestMethod.PUT)
	public EMRResponseBean updateTestStatus(@RequestParam(value="saveObject", required=false, defaultValue="") String saveObject) throws Exception {
		logger.debug("in updating test status");
		saveObject = URLDecoder.decode(saveObject, "UTF-8");
		String testDetailIds = saveObject.split("#@@#")[0];
		String encounterId = saveObject.split("#@@#")[1];
		String currentEnId = saveObject.split("#@@#")[2];
		String chartId = saveObject.split("#@@#")[3];
		String testStatus = saveObject.split("#@@#")[4];
		System.out.println("before request  for performing delete lab "+System.currentTimeMillis());
		long starttime=System.currentTimeMillis();
		investigationService.performOrDeleteLab(testDetailIds , encounterId.split("@##@")[0], testStatus);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.UPDATE,-1,Log_Outcome.SUCCESS,"Success in updating test details chartId="+chartId+" testDetailIds="+testDetailIds,sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"chartId="+chartId,LogUserType.USER_LOGIN,"","");
		 System.out.println("After request  for flowsheet "+System.currentTimeMillis());
	     System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime));
//		List<LabEntries> labs = null;
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		if( encounterId.split("@##@")[1].equalsIgnoreCase("Pending")) {
//			labs = getPendingOrdersList(Integer.parseInt(currentEnId), Integer.parseInt(chartId));
			System.out.println("before request  for findpendingorders "+System.currentTimeMillis());
			long starttime1=System.currentTimeMillis();
			emrResponseBean.setData(investigationService.findPendingOrders(Integer.parseInt(currentEnId), Integer.parseInt(chartId)));
			System.out.println("After request  for findpendingorders "+System.currentTimeMillis());
	        System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime1));
		} else if( encounterId.split("@##@")[1].equalsIgnoreCase("Todays")) {
//			labs = getTodaysOrdersList(Integer.parseInt(currentEnId));
			System.out.println("before request  for findtodayorders "+System.currentTimeMillis());
			long starttime1=System.currentTimeMillis();
			emrResponseBean.setData(investigationService.findTodaysOrders(Integer.parseInt(currentEnId)));
			System.out.println("After request  for findtodayorders "+System.currentTimeMillis());
	        System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime1));
		}
		emrResponseBean.setLogin(true);
		return emrResponseBean;
	}
	/**
	 * Method to get body site information
	 * @param searchStr
	 * @param limit
	 * @param offset
	 * @return siteInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/BodySite",method = RequestMethod.GET)
	public EMRResponseBean getBodySiteData(@RequestParam(value="searchStr", required=false, defaultValue="") String searchStr,
			@RequestParam(value="limit", required=false, defaultValue="") Integer limit,
			@RequestParam(value="offset", required=false, defaultValue="") Integer offset) throws Exception {
		logger.debug("in getting body site details");
//		Page<BodySite> siteInfo = investigationService.findBodySiteData(searchStr, limit, offset);
		System.out.println("before request  for body site details "+System.currentTimeMillis());
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(investigationService.findBodySiteData(searchStr, limit, offset));
		   long starttime=System.currentTimeMillis();
		   auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Success in getting body site details searchStr="+searchStr,sessionMap.getUserID(),
					request.getRemoteAddr(),-1,"searchStr="+searchStr,LogUserType.USER_LOGIN,"","");
	         System.out.println("After request  for body site details "+System.currentTimeMillis());
	        System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime));
        return emrResponseBean;
	}
	
	/**
	 * Method to get vaccine details which has to take consent
	 * @return the entity LabDescription
	 * @throws Exception
	 */
	@RequestMapping(value = "/LotInfo",method = RequestMethod.GET)
	public EMRResponseBean getLotNumberData(@RequestParam(value="vaccineId", required=false, defaultValue="") Integer vaccineId, 
			@RequestParam(value="onLoad", required=false, defaultValue="") String onLoad) throws Exception {
		logger.debug("in getting lot number details");
		
		vaccineId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + vaccineId)).or("-1"));
		System.out.println("before request  for LotInfo "+System.currentTimeMillis());
		long starttime=System.currentTimeMillis();
//		List<VaccineOrderDetails> lotDeatils = investigationService.findLotDetails(vaccineId,onLoad);
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(investigationService.findLotDetails(vaccineId,onLoad));
		  auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Success in getting lot number details vaccineId="+vaccineId,sessionMap.getUserID(),
					request.getRemoteAddr(),-1,"vaccineId="+vaccineId,LogUserType.USER_LOGIN,"","");
		 System.out.println("After request  for LotInfo "+System.currentTimeMillis());
	       System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime));
        return emrResponseBean;
	}
	
	/**
	 * Method to get frequent orders list
	 * @return the entity LabDescription
	 * @throws Exception
	 */
	@RequestMapping(value = "/FrequentOrders",method = RequestMethod.GET)
	public EMRResponseBean getFrequentOrdersList(@RequestParam(value="encounterId", required=false, defaultValue="") Integer encounterId) throws Exception {
		logger.debug("in getting frequent orders list");
//		List<FrequentOrders> frequentOrders = investigationService.findFrequentOrders(encounterId);
		System.out.println("before request  for frequentOrders "+System.currentTimeMillis());
		long starttime=System.currentTimeMillis();
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(investigationService.findFrequentOrders(encounterId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Success in getting frequent orders list encounterId="+encounterId,sessionMap.getUserID(),
					request.getRemoteAddr(),-1,"encounterId="+encounterId,LogUserType.USER_LOGIN,"","");
        System.out.println("After request  for frequentOrders "+System.currentTimeMillis());
       System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime));
        return emrResponseBean;
	}
		
	/**
	 * Method to get vaccine details which has to take consent
	 * @return the entity LabDescription
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveConsent",method = RequestMethod.POST)
	@ResponseBody
	public List<VaccinationConsentForm> saveVaccineInfo(@RequestBody ConsentDetails consentData) throws Exception {
		logger.debug("in saving consent details vaccine");
		System.out.println("before request  for saveconsent "+System.currentTimeMillis());
		long starttime=System.currentTimeMillis();
		List<VaccinationConsentForm> consentList = investigationService.saveVaccineConsent(consentData);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.CREATE,-1,Log_Outcome.SUCCESS,"Success in saving consent details",sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"",LogUserType.USER_LOGIN,"","");
		 System.out.println("After request  for saveconsent "+System.currentTimeMillis());
	     System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime));
		return consentList;
	}
		
	/**
	 * Method to get todays order list based on
	 * @param encounterId
	 * @return the entity LabEntries
	 * @throws Exception
	 */
	@RequestMapping(value = "/TestDetails",method = RequestMethod.GET)
	public EMRResponseBean getCompleteTestDetails(@RequestParam(value="testDetailId", required=false, defaultValue="") Integer testDetailId,
			@RequestParam(value="testId", required=false, defaultValue="") Integer testId, @RequestParam(value="groupId", required=false, defaultValue="") String groupId) throws Exception {
		logger.debug("in getting complete test details");
//		Orders labInfo = investigationService.findCompleteTestDetails(testDetailId, testId, groupId);
		System.out.println("before request  for TestDetails "+System.currentTimeMillis());
		long starttime=System.currentTimeMillis();
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(investigationService.findCompleteTestDetails(testDetailId, testId, groupId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Success in getting complete test details testDetailId="+testDetailId,sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"testId="+testId,LogUserType.USER_LOGIN,"","");  
	         System.out.println("After request  for TestDetails "+System.currentTimeMillis());
	        System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime));
        return emrResponseBean;
	}
	
	/**
	 * Method to get todays order list based on
	 * @param encounterId
	 * @return the entity LabEntries
	 * @throws Exception
	 */
	@RequestMapping(value = "/TodaysOrders",method = RequestMethod.GET)
	public EMRResponseBean getTodaysOrdersList(@RequestParam(value="encounterId", required=false, defaultValue="") Integer encounterId) throws Exception {
		logger.debug("in getting todays orders in investigations");
//		List<LabEntries> labInfo = investigationService.findTodaysOrders(encounterId);
		System.out.println("before request  for getting todaysorders "+System.currentTimeMillis());
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(investigationService.findTodaysOrders(encounterId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Success in getting todays orders encounterId="+encounterId,sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"encounterId="+encounterId,LogUserType.USER_LOGIN,"","");  
	       long starttime=System.currentTimeMillis();
	         System.out.println("After request  for todayOrders "+System.currentTimeMillis());
	        System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime));
        return emrResponseBean;
        
	}
	
	/**
	 * Request to save the details of an order
	 * @param labDetails
	 * @return the message
	 * @throws Exception
	 */
	@RequestMapping(value = "/SaveTestDetails",method = RequestMethod.POST)
	public String saveTestDetails(@RequestBody SaveLabDetails labDetails) throws Exception {
		logger.debug("Begin of save the complete details for a particular lab");
		System.out.println("before request  for Investigation "+System.currentTimeMillis());
		long starttime=System.currentTimeMillis();
		investigationService.savelab(labDetails.getRequestToSave(), labDetails.getEncounterId(), labDetails.getPatientId(), labDetails.getChartId(), labDetails.getUserId(), labDetails.getFullData(), "-1", "-1", "false", "" + labDetails.getTestId());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.CREATE,-1,Log_Outcome.SUCCESS,"Success in save the complete details chartId="+labDetails.getChartId(),sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"encounterId="+labDetails.getEncounterId()+" testId="+labDetails.getTestId(),LogUserType.USER_LOGIN,"","");  
		logger.debug("End of save the complete details for a particular lab");
		System.out.println("After request  for Investigation "+System.currentTimeMillis());
        System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime));
		return "success";
	}
	
	@RequestMapping(value = "/SaveNewOrder", method = RequestMethod.GET)
	public String saveNewOrder(@RequestParam(value = "encounterId", required = false, defaultValue = "") Integer encounterId,
			@RequestParam(value = "chartId", required = false, defaultValue = "") Integer chartId,
			@RequestParam(value = "patientId", required = false, defaultValue = "") Integer patientId,
			@RequestParam(value = "labTestIds", required = false, defaultValue = "") String labTestIds,
			@RequestParam(value = "userId", required = false, defaultValue = "-1") int userId)
			throws Exception {
		String testIdArray[] = labTestIds.split("','");
		for (int i = 1; i < testIdArray.length; i++) {
			  System.out.println("before request  for getnewtestdetails "+System.currentTimeMillis());
		        long starttime=System.currentTimeMillis();
			LabDescription testDetails = investigationService.getNewTestDetails(Integer.parseInt(testIdArray[i]));
			System.out.println("After request  for getnewtestdetail "+System.currentTimeMillis());
	        System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime));
	        System.out.println("before request  for saveneworder "+System.currentTimeMillis());
	        long starttime1=System.currentTimeMillis();
			investigationService.saveNewLab(testDetails, encounterId, Integer.parseInt(testIdArray[i]),userId, chartId, patientId);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.CREATE,-1,Log_Outcome.SUCCESS,"Success in save the complete details for lab chartId="+chartId,userId,
					request.getRemoteAddr(),-1,"chartId="+chartId+" labTestIds="+labTestIds,LogUserType.USER_LOGIN,"","");  
			System.out.println("After request  for saveneworder "+System.currentTimeMillis());
	        System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime1));
		}
		return "success";
	}
	
	@RequestMapping(value = "/OrdersHistoryByChart",method = RequestMethod.GET)
	public EMRResponseBean getLabDetailsByChart(@RequestParam(value="chartId", required=false, defaultValue="-1") Integer chartId) throws Exception {
		logger.debug("Get the complete details for a patient");
//		LS_Bean labInfo = investigationService.findPatientLabDataByChart(chartId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Data succesfully loaded for chart id="+chartId,sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"chartId="+chartId,LogUserType.USER_LOGIN,"","");  
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(investigationService.findPatientLabDataByChart(chartId));
        return emrResponseBean;
	}
	
	@RequestMapping(value = "/OrdersLogByChart",method = RequestMethod.GET)
	public EMRResponseBean getOrderLogByChart(@RequestParam(value="chartId", required=false, defaultValue="-1") Integer chartId) throws Exception {
//		OrderLogGroups labInfo = investigationService.findOrdersSummary(chartId);
		
		 System.out.println("before request  for orderHistory "+System.currentTimeMillis());
	     long starttime=System.currentTimeMillis();EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(investigationService.findOrdersSummary(chartId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Successfully loaded (OrdersLogByChart) data for patient having chart id="+chartId,sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"chartId="+chartId,LogUserType.USER_LOGIN,"","");  
		 System.out.println("After request  for orderset "+System.currentTimeMillis());
	     System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime));
        return emrResponseBean;
	}
	
	@RequestMapping(value = "/ReviewedLogByChart",method = RequestMethod.GET)
	public EMRResponseBean getReviewedLogByChart(@RequestParam(value="chartId", required=false, defaultValue="-1") Integer chartId) throws Exception {
//		OrderLogGroups labInfo = investigationService.findReviewedSummary(chartId);
		  System.out.println("before request  for ReviewedLogByChart "+System.currentTimeMillis());
	        long starttime=System.currentTimeMillis();
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(investigationService.findReviewedSummary(chartId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Successfully loaded (getReviewedLogByChart) data for patient having chart id="+chartId,sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"chartId="+chartId,LogUserType.USER_LOGIN,"","");  
		 System.out.println("After request  for ReviewedLogByChart "+System.currentTimeMillis());
	     System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime));
        return emrResponseBean;
	}
	
	@RequestMapping(value = "/PendingLogByChart",method = RequestMethod.GET)
	
	public EMRResponseBean getPendingLogByChart(@RequestParam(value="chartId", required=false, defaultValue="-1") Integer chartId) throws Exception {
//		OrderLogGroups labInfo = investigationService.findPendingSummary(chartId);
		  System.out.println("before request  for PendingLogByChart "+System.currentTimeMillis());
	        long starttime=System.currentTimeMillis();
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(investigationService.findPendingSummary(chartId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Successfully loaded (getPendingLogByChart) data for patient having chart id="+chartId,sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"chartId="+chartId,LogUserType.USER_LOGIN,"","");   
		System.out.println("After request  for PendingLogByChart "+System.currentTimeMillis());
	    System.out.println("Final time taken::::::::"+(System.currentTimeMillis()-starttime));
        return emrResponseBean;
	}
	
	@RequestMapping(value = "/ParamDataByDate",method = RequestMethod.GET)
	public EMRResponseBean getLabDetailsByDate(@RequestParam(value="chartId", required=false, defaultValue="-1") Integer chartId,
			@RequestParam(value="fromDate", required=false, defaultValue="") String fromDate,
			@RequestParam(value="toDate", required=false, defaultValue="") String toDate) throws Exception {
		logger.debug("Get the complete details for a patient");
//		LS_Bean labInfo = investigationService.getResultsByDate(chartId, fromDate, toDate);
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(investigationService.getResultsByDate(chartId, fromDate, toDate));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Successfully loaded (getLabDetailsByDate) data for patient having chart id="+chartId,sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"chartId="+chartId,LogUserType.USER_LOGIN,"","");   
        return emrResponseBean;
	}
	
	@RequestMapping(value = "/OrdersHistoryByCategory",method = RequestMethod.GET)
	public EMRResponseBean getLabDetailsByCategory(@RequestParam(value="chartId", required=false, defaultValue="-1") Integer chartId,
			@RequestParam(value="categoryId", required=false, defaultValue="-1") Integer catetoryId) throws Exception {
		logger.debug("Get the complete details for a patient");
//		LS_Bean labInfo = investigationService.findPatientLabDataByCategory(chartId,catetoryId);
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(investigationService.findPatientLabDataByCategory(chartId,catetoryId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Successfully loaded (OrdersHistoryByCategory) data for patient having chart id="+chartId,sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"categoryId="+catetoryId,LogUserType.USER_LOGIN,"","");   
        return emrResponseBean;
	}
	
	@RequestMapping(value = "/OrdersHistoryByTest",method = RequestMethod.GET)
	public EMRResponseBean getLabDetailsByTest(@RequestParam(value="chartId", required=false, defaultValue="-1") Integer chartId,
			@RequestParam(value="testId", required=false, defaultValue="-1") Integer testId) throws Exception {
		logger.debug("Get the complete details for a patient");
//		LS_Bean labInfo = investigationService.findPatientLabDataByTest(chartId, testId);
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(investigationService.findPatientLabDataByTest(chartId, testId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Successfully loaded (OrdersHistoryByTest) data for patient having chart id="+chartId,sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"testId="+testId,LogUserType.USER_LOGIN,"","");
        return emrResponseBean;
	}
	@RequestMapping(value = "/OrderByDateLogByChart",method = RequestMethod.GET)
	public EMRResponseBean OrderByDateLogByChart(@RequestParam(value="chartId", required=false, defaultValue="-1") Integer chartId) throws Exception {
//		OrderLogGroups labInfo = investigationService.findReviewedSummary(chartId);
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(investigationService.findOrderByDateSummary(chartId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.INVESTIGATION,LogActionType.VIEW,-1,Log_Outcome.SUCCESS,"Successfully loaded OrderByDateLogByChart) data for patient having chart id="+chartId,sessionMap.getUserID(),
				request.getRemoteAddr(),-1,"chartId="+chartId,LogUserType.USER_LOGIN,"","");
        return emrResponseBean;
	}
}