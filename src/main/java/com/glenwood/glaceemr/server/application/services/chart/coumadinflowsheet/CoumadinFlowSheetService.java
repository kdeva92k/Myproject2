package com.glenwood.glaceemr.server.application.services.chart.coumadinflowsheet;

import java.util.List;

import org.w3c.dom.Document;


import com.glenwood.glaceemr.server.application.models.PatientEpisode;
import com.glenwood.glaceemr.server.application.models.WarfarinIndication;
public interface CoumadinFlowSheetService {
	/**
	 * Method to retrieve episode data
	 */
	List<PatientEpisode> getEpisodes(Integer patientId, Integer episodeId,
			Integer type) throws Exception;

	/**
	 * Method to save episode data without end date
	 */
	void saveEpisodes(Integer patientId, Integer userId, Integer episodeId,
			java.util.Date date1, Integer episodeStatus,
			java.sql.Timestamp currentTimestamp, String isEpisodeDirty)
			throws Exception;
	/**
	 * Method to save episode data with end date
	 */
	void saveEpisodeswithEndDate(Integer patientId, Integer userId,
			Integer episodeId, java.util.Date episodeEndDate,
			java.util.Date date1, Integer episodeStatus,
			java.sql.Timestamp currentTimestamp, String isEpisodeDirty)
			throws Exception;
	/**
	 * Method to update INR Goal
	 */
	void updateINRGoal(Integer patientId, Integer episodeId,
			String INRGoalRangeStart, String INRGoalRangeEnd) throws Exception;
	/**
	 * Method to save indication
	 */
	void saveIndication(Document document, Integer episodeId, Integer userId,
			java.sql.Timestamp currentTimestamp, Integer patientId)
			throws Exception;
	/**
	 * Method to save log informations
	 */
	void saveLog(Document document, Integer chartId, Integer patientId,
			Integer userId, Integer episodeId,
			java.sql.Timestamp currentTimestamp) throws Exception;
	/**
	 * Method to get Log informations details
	 */
	List<LogInfoBean> getLogInfo(Integer chartId, Integer episodeId)
			throws Exception;
	/**
	 * Method to get PTINR values
	 */
	List<LabDetailsBean> getPTINR(Integer chartId, java.util.Date date1)
			throws Exception;
	/**
	 * Method to get RecentINR details
	 */
	List<RecentINRBean> getRecentINR(Integer episodeId, Integer chartId,
			Integer patientId) throws Exception;
	/**
	 * Method to get indication details
	 */
	List<WarfarinIndication> getIndication(Integer episodeId) throws Exception;
	/**
	 * Method to check for configurations
	 */
	Boolean checkConfiguration() throws Exception;
	/**
	 * Method to create Coumadin reminders
	 */
	void createCoumadinReminders(Integer patientId, Integer episodeId,
			Integer userId, java.util.Date date);
}
