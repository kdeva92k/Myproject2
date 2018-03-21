package com.glenwood.glaceemr.server.application.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.models.AlertCategory;
import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.services.alertinbox.AlertArchiveBean;
import com.glenwood.glaceemr.server.application.services.alertinbox.AlertCategoryBean;
import com.glenwood.glaceemr.server.application.services.alertinbox.AlertCountBean;
import com.glenwood.glaceemr.server.application.services.alertinbox.AlertEventBean;
import com.glenwood.glaceemr.server.application.services.alertinbox.AlertInboxBean;
import com.glenwood.glaceemr.server.application.services.alertinbox.AlertInboxService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditLogConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.RestDispatcherTemplate;
import com.glenwood.glaceemr.server.utils.SessionMap;

/**
 * Controller for alerts module. It has two main entities, <b>AlertEvent </b>and <b>AlertCategory</b>.
 * @author Manikandan Nachimuthu
 * <br> <br>
 * <b>Example requests:</b>
 * <br> <br>
 * <ul>
 * <li><b>Category details</b><i> AlertInbox.Action/getcategories?categoryid=1,2,3</i><br>
 * Gets the list of categories based on the category Id.</li><br>
 * <li><b>Highlight</b><i> AlertInbox.Action/highlight?alertid=18563,18557&userid=49</i><br>
 * Highlights one or more alerts and updates the modified user and date.</li><br>
 * <li><b>Un Highlight</b><i> AlertInbox.Action/unhighlight?alertid=18563,18557&userid=49</i><br>
 * Un Highlights one or more alerts and updates the modified user and date.</li><br>
 * <li><b>Read</b><i> AlertInbox.Action/markread?alertid=18563,18557&userid=49</i><br>
 * Mark one or more alerts to read and updates the modified user and date.</li><br>
 * <li><b>Un Read</b><i> AlertInbox.Action/markunread?alertid=18563,18557&userid=49</i><br>
 * Mark one or more alerts to un read and updates the modified user and date.</li><br>
 * <li><b>User Details</b><i> AlertInbox.Action/getusers</i><br>
 * Gets the list of users from employee table grouped by their group Id's.</li><br>
 * </ul>
 */
@RestController
@Transactional
@RequestMapping(value="/user/AlertInbox.Action")
public class AlertInboxController {

	@Autowired
	AlertInboxService alertInboxService;

	@Autowired
	SessionMap sessionMap;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	RestDispatcherTemplate requestDispatcher;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;

	private Logger logger = Logger.getLogger(AlertInboxController.class);

	/**
	 * To get the alerts based on the offset and limits.
	 * @param userId
	 * @param categoryIds
	 * @param pageno
	 * @param pagesize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getalerts", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getalerts(
			 @RequestParam(value="userid", required=false, defaultValue="true") String userId, 
			 @RequestParam(value="categoryids", required=false, defaultValue="-1") String categoryIds, 
			 @RequestParam(value="pageno", required=false, defaultValue="0") int pageno, 
			 @RequestParam(value="pagesize", required=false, defaultValue="10") int pagesize) throws Exception{

		logger.debug("Enters into get alerts");

		List<String> categoryIdList=new ArrayList<String>();
		String[] categoryIdArray = {"-1"};

		List<Map<String, List<Map<String, List<Object>>>>> alertsList;

		if(!categoryIds.equals("-1")){
			categoryIdArray=categoryIds.split(",");
		}

		for (String s : categoryIdArray) {
			categoryIdList.add(s);
		}

		alertsList=alertInboxService.getAlerts(userId,categoryIdList,pageno,pagesize);
		EMRResponseBean alertsLists=new EMRResponseBean();
		alertsLists.setData(alertsList);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved all alerts based on list of categoty id's", sessionMap.getUserID(), request.getRemoteAddr(), -1, "userId="+userId+"categoryIds="+categoryIds, LogUserType.USER_LOGIN, "", "");

		return alertsLists;
	}

	/**
	 * Update the status based on the list of AlertEventId's.
	 * @param alerteventid		    AlertEvent id's are separated by ","
	 * @param alertEventStatus		status of an alert.
	 * @return				        List of modified entities.
	 */
	@RequestMapping(value = "/updateStatus", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean updateStatusbyAlerEventId(
			 @RequestParam(value="statusId", required=false, defaultValue="0") String statusId,
			 @RequestParam(value="alertIds",required=true) String alertEventIds,
			 @RequestParam(value="userId",required=true) String userId){
		List<Integer> alertEventIdList=new ArrayList<Integer>();
		String[] alertEventIdsArray = {"-1"};
		List<Map<String, List<Map<String, List<Object>>>>> alertsList;
		if(!alertEventIds.equals("-1")){
			alertEventIdsArray=alertEventIds.split(",");
		}
		for (String alertEvent : alertEventIdsArray) {
			alertEventIdList.add(Integer.parseInt(alertEvent));
		}
		List<AlertEvent> alertEventLists= alertInboxService.updateStatusbyAlertEventIds(alertEventIdList,statusId,userId);
		EMRResponseBean alertsLists=new EMRResponseBean();
		alertsLists.setData(alertEventLists);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successfully updation of alert list status", sessionMap.getUserID(), request.getRemoteAddr(), -1, "userId="+userId+"statusId="+statusId, LogUserType.USER_LOGIN, "", "");
		
		return alertsLists;
	}
	
	/**
	 * To get the alerts based on the offset and limits.
	 * @param userId
	 * @param categoryIds
	 * @param pageno
	 * @param pagesize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getalertsBasedOnSearch(
			 @RequestParam(value="userid", required=false, defaultValue="true") String userId, 
			 @RequestParam(value="categoryids", required=false, defaultValue="-1") String categoryIds, 
			 @RequestParam(value="patientNameSearchValue", required=false, defaultValue="") String patientNameSearchValue,
			 @RequestParam(value="senderNameSearchValue", required=false, defaultValue="") String senderNameSearchValue,
			 @RequestParam(value="receiverNameSearchValue", required=false, defaultValue="") String receiverNameSearchValue,
			 @RequestParam(value="messageSearchValue", required=false, defaultValue="") String messageSearchValue,
			 @RequestParam(value="fromDateSearchValue", required=false, defaultValue="") String fromDateSearchValue,
			 @RequestParam(value="toDateSearchValue", required=false, defaultValue="") String toDateSearchValue) throws Exception{

		logger.debug("Enters into get alerts");

		List<String> categoryIdList=new ArrayList<String>();
		String[] categoryIdArray = {"-1"};

		List<Map<String, List<Object>>> alertsList;

		if(!categoryIds.equals("-1")){
			categoryIdArray=categoryIds.split(",");
		}

		for (String s : categoryIdArray) {
			categoryIdList.add(s);
		}

		alertsList = alertInboxService.searchAlerts(userId,categoryIdList,patientNameSearchValue,
				senderNameSearchValue,receiverNameSearchValue,messageSearchValue,
				fromDateSearchValue,toDateSearchValue);
		
		EMRResponseBean alertsLists=new EMRResponseBean();
		alertsLists.setData(alertsList);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved alerts based on search criteria", sessionMap.getUserID(), request.getRemoteAddr(), -1, "userId="+userId, LogUserType.USER_LOGIN, "", "");
		
		return alertsLists;
	}

	/**
	 * Get the category list with count based on the user id
	 * @param userId 	User Id to get the list of categories with count
	 * @return
	 */
	@RequestMapping(value="/alertcount", method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getAlertCount(
			 @RequestParam(value="userid", required=false, defaultValue="") String userId){
		logger.debug("Getting alert counts");
		
		List<AlertCountBean> shortcutInfoBeans=alertInboxService.getAlertCount(userId);
		EMRResponseBean shortcutInfoBean=new EMRResponseBean();
		shortcutInfoBean.setData(shortcutInfoBeans);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved alerts count based on user", sessionMap.getUserID(), request.getRemoteAddr(), -1, "userId="+userId, LogUserType.USER_LOGIN, "", "");
		
		return shortcutInfoBean;
	}
	
	/**
	 * Gets the alerts by categories (Pagination)
	 * @param userId		Logged in user Id
	 * @param categoryIds	Category Id to fetch details
	 * @param pageno		Page no Ex:: For first page page no will be 1
	 * @param pagesize		Size of the Page (limit)
	 * @return
	 */
	@RequestMapping(value="/alertbycategory",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean alertsByCategory(
			 @RequestParam(value="userid", required=false, defaultValue="") String userId, 
			 @RequestParam(value="categoryid", required=false, defaultValue="-1") String categoryId, 
			 @RequestParam(value="pageno", required=false, defaultValue="1") int pageno, 
			 @RequestParam(value="pagesize", required=false, defaultValue="20") int pagesize){
		logger.debug("Getting alerts by category id "+categoryId);
		List<AlertInboxBean> alertInboxBeans=alertInboxService.getAlertsByCategory(userId,categoryId,pageno,pagesize);
		EMRResponseBean alertInboxBean=new EMRResponseBean();
		alertInboxBean.setData(alertInboxBeans);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved alerts based on categoryId", sessionMap.getUserID(), request.getRemoteAddr(), -1, "userId="+userId+"categoryId="+categoryId, LogUserType.USER_LOGIN, "", "");
		
		return alertInboxBean;
	}


	/**
	 * Highlights the alerts
	 * @param alertids 		Alert id's are separated by ","
	 * @param userId   		Currently logged in users.
	 * @return 				List of modified entities.
	 */
	@RequestMapping(value = "/highlight", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean highlight(
			 @RequestParam(value="alertid",required=true) String alertids, 
			 @RequestParam(value="userid",required=true) Integer userId){

		logger.debug("Highlighting the alert with id "+alertids);

		List<Integer> alertIdList=new ArrayList<Integer>();
		String[] alertIdArray=alertids.split(",");

		for (String s : alertIdArray) {
			alertIdList.add(Integer.parseInt(s));
		}
		List<AlertEvent> alertEvent=alertInboxService.hightlightAlert(alertIdList,userId);
		EMRResponseBean alertEvents=new EMRResponseBean();
		alertEvents.setData(alertEvent);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successfully highlighted list of alerts", sessionMap.getUserID(), request.getRemoteAddr(), -1, "userId="+userId+"alertid="+alertids, LogUserType.USER_LOGIN, "", "");
		
		return alertEvents;
	}


	/**
	 * Un Highlights the alerts
	 * @param alertids 		Alert id's are separated by ","
	 * @param userId   		Currently logged in users.
	 * @return 				List of modified entities.
	 */
	@RequestMapping(value = "/unhighlight", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean unHighlight(
			@RequestParam(value="alertid",required=true) String alertids,
			 @RequestParam(value="userid",required=true) Integer userId){

		logger.debug("Un Highlighting the alert with id "+alertids);

		List<Integer> alertIdList=new ArrayList<Integer>();
		String[] alertIdArray=alertids.split(",");

		for (String s : alertIdArray) {
			alertIdList.add(Integer.parseInt(s));
		}
		List<AlertEvent> alertEvent=alertInboxService.unHightlightAlert(alertIdList,userId);
		EMRResponseBean alertEvents=new EMRResponseBean();
		alertEvents.setData(alertEvent);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successfully upadated list of alerts with unhighlighted option", sessionMap.getUserID(), request.getRemoteAddr(), -1, "userId="+userId+"alertid="+alertids, LogUserType.USER_LOGIN, "", "");
		
		return alertEvents;
	}


	/**
	 * Marks the list of alerts to read 
	 * @param alertids		Alert id's are separated by ","
	 * @param userId		Currently logged in users.
	 * @return				List of modified entities.
	 */
	@RequestMapping(value = "/markread", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean markRead(
			 @RequestParam(value="alertid",required=true) String alertids, 
			 @RequestParam(value="userid",required=true) Integer userId){

		logger.debug("Read the alert with id "+alertids);

		List<Integer> alertIdList=new ArrayList<Integer>();
		String[] alertIdArray=alertids.split(",");

		for (String s : alertIdArray) {
			alertIdList.add(Integer.parseInt(s));
		}
		List<AlertEvent> alertEvent=alertInboxService.markReadAlert(alertIdList,userId);
		EMRResponseBean alertEvents=new EMRResponseBean();
		alertEvents.setData(alertEvent);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successfully upadated list of alerts with read option", sessionMap.getUserID(), request.getRemoteAddr(), -1, "userId="+userId+"alertid="+alertids, LogUserType.USER_LOGIN, "", "");
		
		return alertEvents;
	}


	/**
	 * Marks the list of alerts to unread 
	 * @param alertids		Alert id's are separated by ","
	 * @param userId		Currently logged in users.
	 * @return				List of modified entities.
	 */
	@RequestMapping(value = "/markunread", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean markUnRead(
			 @RequestParam(value="alertid",required=true) String alertids, 
			 @RequestParam(value="userid",required=false) Integer userId){

		logger.debug("Un Read the alert with id "+alertids);

		List<Integer> alertIdList=new ArrayList<Integer>();
		String[] alertIdArray=alertids.split(",");

		for (String s : alertIdArray) {
			alertIdList.add(Integer.parseInt(s));
		}
		List<AlertEvent> alertEvent=alertInboxService.markUnReadAlert(alertIdList,userId);
		EMRResponseBean alertEvents=new EMRResponseBean();
		alertEvents.setData(alertEvent);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successfully updated list of alerts with unread option", sessionMap.getUserID(), request.getRemoteAddr(), -1, "userId="+userId+"alertid="+alertids, LogUserType.USER_LOGIN, "", "");
		return alertEvents;
	}


	/**
	 * Gets the list of categories based on the category id
	 * @param categoryIds 	Category id's are separated by ","
	 * @return				List of required category details.
	 */
	@RequestMapping(value = "/getcategories", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getCategories(
			 @RequestParam(value="categoryid",required=true) String categoryIds){

		logger.debug("Getting the categories with id "+categoryIds);

		List<Integer> categoryIdList=new ArrayList<Integer>();
		String[] categoryIdArray=categoryIds.split(",");

		for (String s : categoryIdArray) {
			categoryIdList.add(Integer.parseInt(s));
		}
		List<AlertCategory> alertEvent=alertInboxService.getCategories(categoryIdList);
		EMRResponseBean alertEvents=new EMRResponseBean();
		alertEvents.setData(alertEvent);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved category details based on categoryId", sessionMap.getUserID(), request.getRemoteAddr(), -1, "categoryIds="+categoryIds, LogUserType.USER_LOGIN, "", "");
		return alertEvents;
	}


	/**
	 * Forward one or more alerts based on the alert id
	 * @param categoryIds 	Category id's are separated by ","
	 * @return				List of required category details.
	 */
	@RequestMapping(value = "/forward", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean forwardAlerts(
			 @RequestParam(value="userid",required=true) String userId, 
			 @RequestParam(value="alertid",required=true) String alertIds, 
			 @RequestParam(value="categoryid",required=true) String categoryId, 
			 @RequestParam(value="message",required=true) String message, 
			 @RequestParam(value="forwardto",required=true) String forwardTo, 
			 @RequestParam(value="ishighpriority",required=false) String ishighpriority){

		logger.debug("Forwarding the alerts with id "+alertIds);

		List<Integer> alertIdList=new ArrayList<Integer>();
		String[] alertIdArray=alertIds.split(",");

		for (String s : alertIdArray) {
			alertIdList.add(Integer.parseInt(s));
		}
		List<AlertEvent> alertEvent=alertInboxService.forwardAlerts(alertIdList,categoryId, message, userId, forwardTo, ishighpriority);
		EMRResponseBean alertEvents=new EMRResponseBean();
		alertEvents.setData(alertEvent);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successfully forwarded list of alerts to "+forwardTo, sessionMap.getUserID(), request.getRemoteAddr(), -1, "userId="+userId+"categoryId="+categoryId, LogUserType.USER_LOGIN, "", "");
		return alertEvents;
	}


	/**
	 * Delete the alerts
	 * @param alertids 		Alert id's are separated by ","
	 * @param userId   		Currently logged in users.
	 * @return 				List of modified entities.
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean deleteAlerts(
			 @RequestParam(value="alertid",required=true) String alertids,
			 @RequestParam(value="userid",required=true) Integer userId){

		logger.debug("Deleting the alert with id "+alertids);

		List<Integer> alertIdList=new ArrayList<Integer>();
		String[] alertIdArray=alertids.split(",");

		for (String s : alertIdArray) {
			alertIdList.add(Integer.parseInt(s));
		}
		
		List<AlertEvent> alertEvent=null;
		if(userId!=null)
			alertEvent=alertInboxService.deleteAlert(alertIdList,userId);
		
		EMRResponseBean alertEvents=new EMRResponseBean();
		alertEvents.setData(alertEvent);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successfully deleted list of alerts", sessionMap.getUserID(), request.getRemoteAddr(), -1, "userId="+userId+"alertids="+alertids, LogUserType.USER_LOGIN, "", "");
		return alertEvents;
	}


	/**
	 * Delete the alerts by encounter id.
	 * @param encounterIds 	Encounter id's are separated by ","
	 * @param userId   		Currently logged in users.
	 * @return 				List of modified entities.
	 */
	@RequestMapping(value = "/deleteByEncounterId", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean deleteByEncounterId(
			 @RequestParam(value="encounterid",required=true) String encounterIds,
			 @RequestParam(value="userid",required=true) Integer userId){

		logger.debug("Deleting the alert with encounter id "+encounterIds);

		List<Integer> encounterIdList=new ArrayList<Integer>();
		String[] alertIdArray=encounterIds.split(",");

		for (String s : alertIdArray) {
			encounterIdList.add(Integer.parseInt(s));
		}
		List<AlertEvent> alertEvent=alertInboxService.deleteAlertByEncounter(encounterIdList,userId);
		EMRResponseBean alertEvents=new EMRResponseBean();
		alertEvents.setData(alertEvent);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successfully deleted the alerts by encounterId", sessionMap.getUserID(), request.getRemoteAddr(), -1, "userId="+userId+"encounterId="+encounterIds, LogUserType.USER_LOGIN, "", "");
		return alertEvents;
	}


	/**
	 * Get alerts by encounter id.
	 * @param encounterId 	Encounter id's are separated by ","
	 * @return 				List of AlertEvent entities.
	 */
	@RequestMapping(value = "/alertsByEncounterId", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean byEncounterId(
			@RequestParam(value="encounterid",required=true) String encounterId){

		List<Integer> encounterIdList=new ArrayList<Integer>();
		String[] alertIdArray=encounterId.split(",");

		for (String s : alertIdArray) {
			encounterIdList.add(Integer.parseInt(s));
		}
		List<AlertEvent> alertEvent=alertInboxService.alertByEncounter(encounterIdList);
		EMRResponseBean alertEvents=new EMRResponseBean();
		alertEvents.setData(alertEvent);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved alerts by encounterId", sessionMap.getUserID(), request.getRemoteAddr(), -1, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		return alertEvents;
	}


	/**
	 * Creates alert in AlertEvent Table.
	 * @param fromid required employee fromId
	 * @param toid required employee toId
	 * @param parentid required alert parentId
	 * @param categoryid required alert categoryId
	 * @return newly inserted alert information
	 * Example url: AlertInbox.Action/compose?fromid=49&toid=-1,2&msg=test msg&parentid=123&categoryid=37
	 */
	@RequestMapping(value = "/compose", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean createAlerts(
			 @RequestParam(value="fromid",required=true) String fromid,
			@RequestParam(value="toid",required=true) String toid,
			@RequestParam(value="status",required=true, defaultValue="1") String status,
			@RequestParam(value="categoryid",required=true) String categoryid,
			@RequestParam(value="refid",required=true, defaultValue="-1") String refid,
			 @RequestParam(value="patientid",required=true, defaultValue="-1") String patientid,
			@RequestParam(value="encounterid",required=true, defaultValue="-1") String encounterid,
			@RequestParam(value="msg",required=true, defaultValue="") String msg,
			 @RequestParam(value="chartid",required=true, defaultValue="-1") String chartid,
			 @RequestParam(value="roomid",required=true, defaultValue="-1") String roomid,
			 @RequestParam(value="parentid",required=true, defaultValue="-1") String parentid){
		
		List<Integer> toIdList=new ArrayList<Integer>();
		String[] toIdArray=toid.split(",");
		for (String s : toIdArray) {
			toIdList.add(Integer.parseInt(s));
		}
		logger.debug("Creating the alert with id "+fromid);	
		List<AlertEvent> alertEvent=alertInboxService.composeAlert(Integer.parseInt(fromid),toIdList,Integer.parseInt(status),Integer.parseInt(categoryid),Integer.parseInt(refid),Integer.parseInt(patientid),Integer.parseInt(encounterid),msg,Integer.parseInt(chartid),Integer.parseInt(roomid),Integer.parseInt(parentid));
		EMRResponseBean alertEvents=new EMRResponseBean();
		alertEvents.setData(alertEvent);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.CREATE, 0, Log_Outcome.SUCCESS, "successfully created alert", sessionMap.getUserID(), request.getRemoteAddr(), Integer.parseInt(patientid), "encounterid="+encounterid+"chartid="+chartid, LogUserType.USER_LOGIN, "", "");
		
		return alertEvents;
	}
	
	@RequestMapping(value = "/getConversation", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getConversation(
			 @RequestParam(value="alertid",required=true) String alertid){
		
		List<AlertEvent> alerts=alertInboxService.getConversion(alertid);
		EMRResponseBean alert=new EMRResponseBean();
		alert.setData(alerts);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved conversation message for particular alert", sessionMap.getUserID(), request.getRemoteAddr(), -1, "alertid="+alertid, LogUserType.USER_LOGIN, "", "");
		
		return alert;
	}
	
	@RequestMapping(value = "/forwardIcmAlert", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean forwardIcmAlert(
			 @RequestParam(value="alertid",required=true) Integer alertid,
			 @RequestParam(value="userid",required=true) Integer userId,
			 @RequestParam(value="categoryid",required=true, defaultValue="37" ) Integer categoryid,
			 @RequestParam(value="patientid",required=true, defaultValue="-1111") Integer patientid,
			 @RequestParam(value="encounterid",required=true, defaultValue="-1" ) Integer encounterid,
			 @RequestParam(value="forwardto",required=true) Integer forwardto,
			 @RequestParam(value="message",required=true, defaultValue="") String message,
			 @RequestParam(value="parentalertid",required=true, defaultValue="-1" ) Integer parentalertid){
	
		List<AlertEvent> alerts=alertInboxService.forwardIcmAlert(alertid,userId,encounterid,patientid,categoryid,forwardto,message,parentalertid);
		EMRResponseBean alert=new EMRResponseBean();
		alert.setData(alerts);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successfully forwarded internal communication message alert(updation)", sessionMap.getUserID(), request.getRemoteAddr(), patientid, "userId="+userId+"alertid="+alertid, LogUserType.USER_LOGIN, "", "");
		return alert;
	}
	
	@RequestMapping(value = "/statusCategories", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getStatusCategories(
			 @RequestParam(value="alertSection",required=true) String alertSection){
		
		EMRResponseBean alert=new EMRResponseBean();
		alert.setData(alertInboxService.getStatusCategories(alertSection).toString());
		return alert;
	}
	
	@RequestMapping(value = "/archive", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean archive(
			 @RequestParam(value="alertStatus",required=true, defaultValue="1") Integer alertStatus,
			 @RequestParam(value="days",required=true, defaultValue="-1") Integer days,
			 @RequestParam(value="patientid",required=true, defaultValue="-1") Integer patientid,
			 @RequestParam(value="patientName",required=true, defaultValue="All" ) String patientName,
			 @RequestParam(value="fromId",required=true, defaultValue="-999") Integer fromId,
			 @RequestParam(value="toId",required=true, defaultValue="-999") Integer toId,
			 @RequestParam(value="message",required=true, defaultValue="") String message){
	
		HashMap<Integer, AlertCategoryBean> alerts=alertInboxService.archieve(alertStatus,days,patientid,patientName,fromId,toId,message);
		EMRResponseBean alert=new EMRResponseBean();
		alert.setData(alerts);
		return alert;
	}
	
	@RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getAllCategories(){
		
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(alertInboxService.getAllCategories());
		return emrResponseBean;
	}
	
	@RequestMapping(value = "/getMessageConversion", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getMessageConversation(
			 @RequestParam(value="alertid",required=true) String alertid) throws Exception{
		List<AlertEventBean> alerts=alertInboxService.getMessageConversion(alertid);
		EMRResponseBean alert=new EMRResponseBean();
		alert.setData(alerts);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved conversation message for particular alert", sessionMap.getUserID(), request.getRemoteAddr(), -1, "alertid="+alertid, LogUserType.USER_LOGIN, "", "");
		
		return alert;
	}
}