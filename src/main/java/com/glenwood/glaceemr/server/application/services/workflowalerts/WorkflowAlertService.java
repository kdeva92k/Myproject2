package com.glenwood.glaceemr.server.application.services.workflowalerts;

import java.text.ParseException;
import java.util.List;

import com.glenwood.glaceemr.server.application.models.AlertCategory;
import com.glenwood.glaceemr.server.application.models.Room;
import com.glenwood.glaceemr.server.application.models.Workflow;

public interface WorkflowAlertService {

	/**
	 * User id based patient flow alerts.
	 * @param userId 	Login user id
	 * @return 			List of alerts based on given user id
	 * @throws ParseException
	 */
	List<Object> workflowAlertList(int userId) throws ParseException;
	
	/**
	 * Get the list of patient flow alerts categories
	 * @return 			List of alert categories which is marked as workflow category. 
	 */
	List<AlertCategory> getWorkflowCategory();
	
	/**
	 * Create a new category under alert category.
	 * @param name		Name of the new category.
	 * @param url		URL to be redirected.
	 * @return 			New category which is created newly.
	 */
	AlertCategory insertStatus(String name, String url);
	
	/**
	 * Delete the category by category id.
	 * @param categoryId	Category id need to be deleted.
	 */
	void deleteStatus(int categoryId);
	
	/**
	 * Update the category by category id. 
	 * @param categoryId	Category id need to be updated
	 * @param categoryName	Category name to be updated.
	 * @param url			URL to be redirected.
	 * @return updated status based on category id
	 */
	AlertCategory updateStatus(int categoryId, String categoryName,String url);

	/**
	 * Create a patient flow alert. 
	 * @param patientId		Patient id of a particular alert.
	 * @param encounterId	Encounter id of a particular alert.
	 * @param chartId		Chart id of a particular alert.
	 * @param roomId		Room id of a particular alert.
	 * @param fromId		User who is creating the alert.
	 * @param toId			User who is receiving the alert.
	 * @param msg			Message of the particular alert.
	 * @param status		Category to which the alert is to be forwarded. 
	 * @param isHighPriority	
	 * @return newly inserted alert
	 */
	Workflow insertAlert(int patientId, int encounterId, int chartId,
			int roomId, int fromId, int toId, String msg, int status,
			boolean isHighPriority);
	
	/**
	 * To get the list of patient flow alert count.
	 * @param userId		Currently logged in user id.
	 * @return 				List of object which have alert count.
	 */
	List<Object> getAlertCount(int userId);
	
	/**
	 * To make the alert highlight.
	 * @param alertId			Alert id which is need to be high lighted.
	 * @param isHighPriority	True, if it is high lighted else false.
	 * @return 					Patient flow alert which was modified. 
	 */
	Workflow highlightAlert(int alertId, boolean isHighPriority);
	
	/**
	 * Forward the alert.
	 * @param alertId			Alert id which is need to be forwarded.
	 * @param forwardto			User who is receiving the alert
	 * @param status			Category to which the alert has been forwarded.
	 * @param isHighpriority 	True, if it is high lighted else false.
	 * @param roomId 			Room id related to the alert.
	 * @return 					Created alert object
	 */
	Workflow fowardAlert(int alertId,int fromId, int forwardto, int status, int roomId, String isHighpriority,String msg);
	
	/**
	 * To get the list of active room details.
	 * @return 					List of active room details.
	 */
	List<Room> getRoomDetails();
	
	/**
	 * To make the alert inactive.
	 * @param alertId			Alert which has been modified.
	 */
	Workflow closeAlert(int patientId);
	
	/**
	 * To get the configuration status for patient flow alerts.
	 * @param optionName		"Workflow" is the keyword. 
	 * @return					True if it is configured, else it is false.
	 */
	boolean getWorkflowConfigStatus(String optionName);
	
	/**
	 * Get an active alert based on the encounter id.
	 * @param encounterId		Encounter id 
	 * @return					Active patient flow alert for the required encounter.
	 */
	Workflow workflowAlertListByencounterId(int encounterId);
	
	/**
	 * List the total time taken for every active category.
	 * @param toId				User who is logged in id.
	 * @param startDate			Date which is from date.
	 * @param endDate			Date which is to date.
	 * @return 					List of active category name and category total time.
	 */
	List<Object> gettotaltimedetails(int toId, String startDate, String endDate);
	
	/**
	 * Get patient flow alert based on alert id.
	 * @param workflowId		Alert id which is need to be view.
	 * @return					Get patient flow alert based on  Patient flow id.
	 */
	Workflow workflowAlertListById(int workflowId);

}
