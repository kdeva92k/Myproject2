package com.glenwood.glaceemr.server.application.controllers;

import java.io.ByteArrayInputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.glenwood.glaceemr.server.application.models.PatientEpisode;
import com.glenwood.glaceemr.server.application.models.WarfarinIndication;
import com.glenwood.glaceemr.server.application.services.chart.coumadinflowsheet.CoumadinFlowSheetService;
import com.glenwood.glaceemr.server.application.services.chart.coumadinflowsheet.LabDetailsBean;
import com.glenwood.glaceemr.server.application.services.chart.coumadinflowsheet.LogInfoBean;
import com.glenwood.glaceemr.server.application.services.chart.coumadinflowsheet.RecentINRBean;
import com.wordnik.swagger.annotations.Api;

@Api(value = "CoumadinFlowSheet", description = "CoumadinFlowSheet", consumes = "application/json")
@RestController
@Transactional
@RequestMapping(value = "/CoumadinFlowSheet")
public class CoumadinFlowSheetController {

	@Autowired
	CoumadinFlowSheetService coumadinflowsheetService;

	/**
	 * Method to retrieve Episode details
	 * 
	 * @param patientId
	 * @param episodeId
	 * @param type
	 * @return episodedata
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEpisodes", method = RequestMethod.GET)
	@ResponseBody
	public List<PatientEpisode> getEpisodes(
			@RequestParam(value = "patientId", required = true) Integer patientId,
			@RequestParam(value = "episodeId", required = false, defaultValue = "-1") Integer episodeId,
			@RequestParam(value = "type", required = false, defaultValue = "-1") Integer type)
			throws Exception {
		List<PatientEpisode> episodedata = coumadinflowsheetService
				.getEpisodes(patientId, episodeId, type);
		return episodedata;
	}

	/**
	 * Method to save Episode details
	 * 
	 * @param coumadin_xml
	 * @param patientId
	 * @param userId
	 * @param timestamp
	 * @param episodeId
	 * @param episodeEndDate
	 * @param episodeStartDate
	 * @param isEpisodeDirty
	 * @param INRGoalRangeStart
	 * @param INRGoalRangeEnd
	 * save episode details
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveEpisodes", method = RequestMethod.GET)
	@ResponseBody
	public void saveEpisodes(
			@RequestParam(value = "patientId", required = true) Integer patientId,
			@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "episodeId", required = false, defaultValue = "-1") Integer episodeId,
			@RequestParam(value = "episodeStartDate", required = true) String episodeStartDate,
			@RequestParam(value = "chartId", required = true) Integer chartId,
			@RequestParam(value = "episodeEndDate", required = false, defaultValue = "") String episodeEndDate,
			@RequestParam(value = "episodeStatus", required = true) Integer episodeStatus,
			@RequestParam(value = "isEpisodeDirty", required = false, defaultValue = "-1") String isEpisodeDirty,
			@RequestParam(value = "INRGoalRangeStart", required = false, defaultValue = "") String INRGoalRangeStart,
			@RequestParam(value = "INRGoalRangeEnd", required = false, defaultValue = "") String INRGoalRangeEnd,
			@RequestParam(value = "coumadin_xml", required = false, defaultValue = "-1") String coumadin_xml)
			throws Exception {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(
				now.getTime());
		if (!episodeStartDate.equals("") && episodeEndDate.equals("")) {
			SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date date1 = originalFormat.parse(episodeStartDate);
			coumadinflowsheetService.saveEpisodes(patientId, userId, episodeId,
					date1, episodeStatus, currentTimestamp, isEpisodeDirty);
		}

		else if (!episodeStartDate.equals("") && (!episodeEndDate.equals(""))) {
			SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date date2 = originalFormat.parse(episodeEndDate);
			java.util.Date date1 = originalFormat.parse(episodeStartDate);
			coumadinflowsheetService.saveEpisodeswithEndDate(patientId, userId,
					episodeId, date1, date2, episodeStatus, currentTimestamp,
					isEpisodeDirty);

		}
		if (episodeId != -1) {
			coumadinflowsheetService.updateINRGoal(patientId, episodeId,
					INRGoalRangeStart, INRGoalRangeEnd);
		}
		if (!coumadin_xml.equals("-1")) {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentFactory
					.newDocumentBuilder();
			String xml = URLDecoder.decode(coumadin_xml, "UTF-8");
			InputSource source = new InputSource(new ByteArrayInputStream(
					xml.getBytes()));
			Document document = (Document) documentBuilder.parse(source);
			coumadinflowsheetService.saveIndication(document, episodeId,
					userId, currentTimestamp, patientId);
			coumadinflowsheetService.saveLog(document, chartId, patientId,
					userId, episodeId, currentTimestamp);
		}
	}
	/**
	 * Method to get Log informations details
	 * @param chartId
	 * @param episodeId
	 * returns logInfo
	 */
	@RequestMapping(value = "/getLogInfo", method = RequestMethod.GET)
	@ResponseBody
	public List<LogInfoBean> getLogInfo(
			@RequestParam(value = "chartId", required = true) Integer chartId,
			@RequestParam(value = "episodeId", required = true) Integer episodeId)
			throws Exception {
		List<LogInfoBean> logInfo = coumadinflowsheetService.getLogInfo(
				chartId, episodeId);
		return logInfo;
	}
	/**
	 * Method to get PTINR values
	 * @param chartId
	 * @param date
	 * returns ptINR
	 */
	@RequestMapping(value = "/getPTINR", method = RequestMethod.GET)
	@ResponseBody
	public List<LabDetailsBean> getPTINR(
			@RequestParam(value = "chartId", required = false,defaultValue = "-1") Integer chartId,
			@RequestParam(value = "date", required = false,defaultValue = "-1") String date)
			throws Exception {
		SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date1 = originalFormat.parse(date);
		List<LabDetailsBean> ptINR = coumadinflowsheetService.getPTINR(chartId,
				date1);
		return ptINR;
	}
	/**
	 * Method to get RecentINR details
	 * @param episodeId
	 * @param chartId
	 * @param patientId
	 * returns recentINR
	 */
	
	@RequestMapping(value = "/getRecentINR", method = RequestMethod.GET)
	@ResponseBody
	public List<RecentINRBean> getRecentINR(
			@RequestParam(value = "episodeId", required = false, defaultValue = "-1") Integer episodeId,
			@RequestParam(value = "chartId", required = false,defaultValue = "-1") Integer chartId,
			@RequestParam(value = "patientId", required = false,defaultValue = "-1") Integer patientId)
			throws Exception {
		List<RecentINRBean> recentINR = coumadinflowsheetService.getRecentINR(
				episodeId, chartId, patientId);
		return recentINR;
	}
	/**
	 * Method to get indication details
	 * @param episodeId
	 * returns indications
	 */
	@RequestMapping(value = "/getIndication", method = RequestMethod.GET)
	@ResponseBody
	public List<WarfarinIndication> getIndication(
			@RequestParam(value = "episodeId", required = true) Integer episodeId)
			throws Exception {
		List<WarfarinIndication> indications = coumadinflowsheetService
				.getIndication(episodeId);
		return indications;
	}
	/**
	 * Method to check for configurations
	 * return true/false
	 */
	@RequestMapping(value = "/checkConfiguration", method = RequestMethod.GET)
	@ResponseBody
	public Boolean checkConfiguration() throws Exception {
		Boolean configure = coumadinflowsheetService.checkConfiguration();
		return configure;
	}
	/**
	 * Method to create Coumadin reminders
	 * @param patientId
	 * @param episodeId
	 * @param userId
	 * @param date
	 * save reminder details
	 */
	@RequestMapping(value = "/createCoumadinReminders", method = RequestMethod.GET)
	@ResponseBody
	public void createCoumadinReminders(
			@RequestParam(value = "patientId", required = false,defaultValue = "-1") Integer patientId,
			@RequestParam(value = "episodeId", required = false,defaultValue = "-1") Integer episodeId,
			@RequestParam(value = "userId", required = false,defaultValue = "-1") Integer userId,
			@RequestParam(value = "date", required = true) String date)
			throws Exception {
		SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date createdDate = originalFormat.parse(date);
		coumadinflowsheetService.createCoumadinReminders(patientId, episodeId,
				userId, createdDate);
	}

}
