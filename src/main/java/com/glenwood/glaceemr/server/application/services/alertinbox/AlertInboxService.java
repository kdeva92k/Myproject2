package com.glenwood.glaceemr.server.application.services.alertinbox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.glenwood.glaceemr.server.application.models.AlertCategory;
import com.glenwood.glaceemr.server.application.models.AlertEvent;

/**
 * Service Interface for AlertInbox
 * @author Manikandan
 *
 */
public interface AlertInboxService {

	/**
	 * To get the list of alerts
	 * @param userId			Logged in user's userid
	 * @param categoryIdList	List of category Id 
	 * @param pageno			Page number to be fetched. (offset)
	 * @param pagesize			No. of alerts per page.
	 * @return					List of alerts with categories
	 */
	List<Map<String, List<Map<String, List<Object>>>>> getAlerts(String userId,	List<String> categoryIdList, int pageno, int pagesize);

	/**
	 * Highlight the list of alerts
	 * @param alertIdList 		Alert id's are separated by ","
	 * @param userId			Currently logged in users.
	 * @return					List of modified alerts.
	 */
	List<AlertEvent> hightlightAlert(List<Integer> alertIdList, Integer userId);

	/**
	 * Un Highlight the list of alerts
	 * @param alertIdList 		Alert id's are separated by ","
	 * @param userId			Currently logged in users.
	 * @return					List of modified alerts.
	 */
	List<AlertEvent> unHightlightAlert(List<Integer> alertIdList, Integer userId);

	/**
	 * mark read the list of alerts
	 * @param alertIdList 		Alert id's are separated by ","
	 * @param userId			Currently logged in users.
	 * @return					List of modified alerts.
	 */
	List<AlertEvent> markReadAlert(List<Integer> alertIdList, Integer userId);

	/**
	 * mark unread the list of alerts
	 * @param alertIdList 		Alert id's are separated by ","
	 * @param userId			Currently logged in users.
	 * @return					List of modified alerts.
	 */
	List<AlertEvent> markUnReadAlert(List<Integer> alertIdList, Integer userId);

	/**
	 * Get the list of categories
	 * @param categoryIdList 		Category id's are separated by ","
	 * @return						List of alert categories.
	 */
	List<AlertCategory> getCategories(List<Integer> categoryIdList);

	/**
	 * Get the alert counts grouped by alert category.
	 * @param userId			Currently logged in user id.
	 * @return					List of AlertCountBean.
	 */
	List<AlertCountBean> getAlertCount(String userId);

	/**
	 * Get alerts by category id
	 * @param userId			Currently logged in user id.
	 * @param categoryIds		Category id's separated by comma(",")
	 * @param pageno			Page no to be fetched. (Offset)
	 * @param pagesize			No of alerts per page.
	 * @return					List of AlertInboxBean
	 */
	List<AlertInboxBean> getAlertsByCategory(String userId, String categoryIds, int pageno, int pagesize);

	/**
	 * To forward the alert
	 * @param alertIdList		List of alerts to be forwarded.
	 * @param categoryId		To which category the alerts are need to be forwarded.
	 * @param message			Message to be forwarded.
	 * @param userId			Currently logged in user id.
	 * @param forwardTo			User id to whom the alerts are forwarded.
	 * @param ishighpriority	True if the alerts are high priority, else false.
	 * @return					List of modified alerts
	 */
	List<AlertEvent> forwardAlerts(List<Integer> alertIdList, String categoryId, String message, String userId, String forwardTo, String ishighpriority);

	/**
	 * Delete the list of alerts.
	 * @param alertIdList		List of alerts to be deleted.
	 * @param userId			Currently logged in user id.
	 * @return					List of modified alerts
	 */
	List<AlertEvent> deleteAlert(List<Integer> alertIdList, Integer userId);
	
	/**
	 * Create the alert.
	 * @param fromId			User who is going to create the alert.
	 * @param toIdList			Users who is going to receive the alert.
	 * @param status			Status is 1 if the alert is active.
	 * @param categoryId		Category id to which the alert is created.
	 * @param refId				Reference id. Example, for CNM based alerts leaf id to be added.
	 * @param patientId			Patient id to which the alert is related.
	 * @param encounterId		Encounter id of the particular alert.
	 * @param msg				Message of the alert.
	 * @param chartId			Chart id of the alert.
	 * @param roomId			Room id of the alert.
	 * @param parentId			Parent alert id. If it is a conversation this parent id to be used.
	 * @return					List of created alert.
	 */
	List<AlertEvent> composeAlert(int fromId, List<Integer> toIdList,int status, int categoryId, int refId, int patientId,int encounterId, String msg, int chartId, int roomId,int parentId);

	/**
	 * Delete the alerts by encounter id.
	 * @param encounterIdList	List of encounter id's
	 * @param userId			Currently logged in users id.
	 * @return					List of deleted alerts.
	 */
	List<AlertEvent> deleteAlertByEncounter(List<Integer> encounterIdList, Integer userId);

	/**
	 * Get the list of alerts by encounter.
	 * @param encounterIdList	List of encounter id's
	 * @return					List of alerts.
	 */
	List<AlertEvent> alertByEncounter(List<Integer> encounterIdList);
	
	AlertEvent getAlertId(Integer patientId, Integer labEncounterId, Integer refId);
	
	Integer getCategoryId(Integer section, Integer actionMap);

	Integer getAlertCategoryId(Integer actionMap, Integer catType);
	
	List<AlertEvent> getAlertsByEncIdAndCatId(Integer encounterId,Integer categoryId);

	List<AlertEvent> getConversion(String alertid);
	
	List<AlertEventBean> getMessageConversion(String alertid) throws Exception;

	List<AlertEvent> forwardIcmAlert(Integer alertid, Integer userId, Integer encounterid,Integer patientid, Integer categoryid,
			Integer forwardto, String message, Integer parentalertid);

	List<Map<String, List<Object>>> searchAlerts(String userId, List<String> categoryIdList,
			String patientNameSearchValue, String senderNameSearchValue,
			String receiverNameSearchValue, String messageSearchValue,
			String fromDateSearchValue, String toDateSearchValue);

	JSONArray getStatusCategories(String alertSection);

	HashMap<Integer, AlertCategoryBean> archieve(Integer alertStatus, Integer days,
			Integer patientid, String patientName, Integer fromId,
			Integer toId, String message);

	List<AlertCategory> getAllCategories();
	
	List<AlertEvent> updateStatusbyAlertEventIds(List<Integer> alertEventIds,
			String alertStatus, String userId);
}