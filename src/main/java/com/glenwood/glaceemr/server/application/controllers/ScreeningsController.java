package com.glenwood.glaceemr.server.application.controllers;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.chart.Questionaries.PatientClinicalFindingsBean;
import com.glenwood.glaceemr.server.application.services.chart.Questionaries.ScreeningsService;
import com.glenwood.glaceemr.server.application.services.chart.Questionaries.RiskAssessmentBean;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
@RestController
@Transactional
@RequestMapping(value="/user/Screenings")
public class ScreeningsController {
	
	@Autowired
	ScreeningsService screeningsService;
		
	
	/**
	 * To get the list of Screenings for the given patientId and encounterId
	 * @param patientId
	 * @param encounterId
	 * @return Screenings List
	 * @throws Exception
	 */
	@RequestMapping(value ="/LoadScreeningsList", method = RequestMethod.GET) 
	@ResponseBody
	public List<RiskAssessmentBean> getScreeningsList(@RequestParam(value="patientId",required=false,defaultValue="-1")Integer patientId,
			@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId,
			@RequestParam(value="screenId",required=false,defaultValue="-1")Integer riskscreenId)throws Exception{
		int screenId = -1;
		List<RiskAssessmentBean> lists=screeningsService.getScreeningsList(patientId,encounterId,screenId,riskscreenId);
		return lists;
	}
		
	/**
	 *To get List of frequent Interventions configured for account
	 * @param userId
	 * @throws Exception
	 */
	/*@RequestMapping(value="/LoadFrequentScreenings", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getFreqUsedScreenings(@RequestParam(value="userId",required=false)Integer userId){
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		List<Object> getFrequentList = screeningsService.fetchFrequentInterventions(userId);
		emrResponseBean.setData(getFrequentList);
		return emrResponseBean;
	}*/
	
	/**
	 * To get Screenings based on search Keyword
	 * @param searchString
	 * @throws Exception
	 */
	@RequestMapping(value="/SearchScreenings", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getSearchScreenings(@RequestParam(value="searchString",required=false)String searchString){
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		String getSearchList = screeningsService.getSearchScreenings(searchString);
		emrResponseBean.setData(getSearchList);
		return emrResponseBean;
	}
	
	/**
	 * To load Selected screening Info 
	 * @param screenId
	 * @throws Exception
	 */
	@RequestMapping(value="/LoadScreeningInfo", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getScreeningInfo(@RequestParam(value="screenId",required=false)Integer screenId){
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		String getSearchList = screeningsService.getScreeningInfo(screenId);
		emrResponseBean.setData(getSearchList);
		return emrResponseBean;
	}
	
	/**
	 * To save Screenings Info for patient
	 * @param ScreenInfo
	 * @return EMRResponseBean
	 * @throws Exception
	 */
	@RequestMapping(value="/SaveScreeningsInfo", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean saveScreeningsInfo(@RequestBody RiskAssessmentBean riskAssessmentBean,
			@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=true) Integer encounterId){
		int riskAssId = -1;
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		screeningsService.saveScreeningsInfo(riskAssessmentBean);
		List<RiskAssessmentBean> lists=screeningsService.getScreeningsList(patientId,encounterId,riskAssId,-1);
		emrResponseBean.setData(lists);
		return emrResponseBean;
	}
	
	/**
	 * To Save Question and Answers of Selected screening
	 * @param ScreenInfo
	 * @return EMRResponseBean
	 * @throws Exception
	 */
	@RequestMapping(value="/SaveScreeningsquestions", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean SaveScreeningsquestions(@RequestBody List<PatientClinicalFindingsBean> patientClinicalFindingsBean,
			@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=true) Integer encounterId,
			@RequestParam(value="riskAssessmentId", required=true)Integer riskAssessmentId,
			@RequestParam(value="answerScore", required=true)Integer answerScore,
			@RequestParam(value="notes", required=true)String notes,
			@RequestParam(value="status", required=true)Integer status,
			@RequestParam(value="userId", required=true)Integer userId){
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		screeningsService.SaveScreeningsquestions(patientClinicalFindingsBean,patientId,encounterId,riskAssessmentId,userId,0);
		screeningsService.updateriskAssessmentScore(patientId,encounterId,riskAssessmentId,answerScore,notes,status,userId);
		Map<String, Object> geteditData=screeningsService.editScreeningQuestions(riskAssessmentId,patientId,encounterId);
		//List<PatientClinicalFindingsBean> getQandAList = screeningsService.editScreeningQuestions(screenId,patientId,encounterId);
		emrResponseBean.setData(geteditData);
		return emrResponseBean;
	}
	
	/**To Edit saved questionnaire 
	 * @param riskAssessmentId
	 * @return EMRResponseBean
	 * @throws Exception
	 */
	@RequestMapping(value="/EditScreeningQuestions", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean editScreeningQuestions(@RequestParam(value="riskAssessmentId",required=false)Integer riskAssessmentId,
			@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=true) Integer encounterId){
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		Map<String, Object> geteditData=screeningsService.editScreeningQuestions(riskAssessmentId,patientId,encounterId);
		emrResponseBean.setData(geteditData);
		return emrResponseBean;
	}
	/**To set Screen status
	 * @param riskAssessmentId,Status
	 * @return success
	 * @throws Exception
	 */
	@RequestMapping(value="/SaveScreeningStatus", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean saveScreeningStatus(@RequestParam(value="riskAssessmentId",required=false)Integer riskAssessmentId,
			@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=true) Integer encounterId,
			@RequestParam(value="status", required=true) Integer status){
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		screeningsService.saveScreeningStatus(riskAssessmentId,patientId,encounterId,status);
		//List<RiskAssessmentBean> lists=screeningsService.getScreeningsList(patientId,encounterId,-1);

//		List<PatientClinicalFindingsBean> geteditData = screeningsService.editScreeningQuestions(screeningId,patientId,encounterId);
		emrResponseBean.setData(null);
		return emrResponseBean;
	}
	/**
	 * To save Screening Result status
	 * @param riskAssessmentId,status
	 * @return success
	 * @throws Exception
	 */
	@RequestMapping(value="/SaveScreeningResult", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean SaveScreeningResult(@RequestParam(value="riskAssessmentId",required=false)Integer riskAssessmentId,
			@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=true) Integer encounterId,
			@RequestParam(value="status", required=true) Integer status,
			@RequestParam(value="description", required=true) String description,
			@RequestParam(value="code", required=true) String code,
			@RequestParam(value="codeSys", required=true) String codeSys,
			@RequestParam(value="userId", required=true) Integer userId){
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		screeningsService.saveScreeningResult(riskAssessmentId,patientId,encounterId,status,description,code,codeSys,userId);
		//List<RiskAssessmentBean> lists=screeningsService.getScreeningsList(patientId,encounterId,-1);

//		List<PatientClinicalFindingsBean> geteditData = screeningsService.editScreeningQuestions(screeningId,patientId,encounterId);
		emrResponseBean.setData(null);
		return emrResponseBean;
	}
	/**
	 * To get Interventions based on search Keyword
	 * @param searchString
	 * @throws Exception
	 */
	@RequestMapping(value="/SearchInterventions", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getSearchInterventions(@RequestParam(value="searchString",required=false)String searchString){
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		String getSearchList = screeningsService.getSearchInterventions(searchString);
		emrResponseBean.setData(getSearchList);
		return emrResponseBean;
	}
	
	/**
	 * To delete SNOMED Interventions based on search Keyword
	 * @param Id
	 * @throws Exception
	 */
	@RequestMapping(value="/DeleteSnomedIntervention", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean deleteSnomedInterventions(@RequestParam(value="delId",required=true)Integer delId,
			@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=true) Integer encounterId,
			@RequestParam(value="screenId", required=true) Integer screenId){
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		screeningsService.deleteSnomedIntervention(delId,encounterId,patientId);
		Map<String, Object> geteditData=screeningsService.editScreeningQuestions(screenId,patientId,encounterId);
		//String getSearchList = screeningsService.getSearchInterventions(searchString);
		emrResponseBean.setData(geteditData);
		return emrResponseBean;
	}
	/**
	 * To get previous visit screen Info for Functional screening
	 * @param Date
	 * @throws Exception
	 */
	@RequestMapping(value="/GetPreviousVisitScreenInfo", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPreviousVisitScreenInfo(@RequestParam(value="prevDate",required=true)String prevDate,
			@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=true) Integer encounterId,
			@RequestParam(value="riskAssId", required=true) Integer riskAssId,
			@RequestParam(value="userId", required=true) Integer userId,
			@RequestParam(value="screenId", required=true) Integer screenId){
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		//screeningsService.deleteSnomedIntervention(delId,encounterId,patientId);
		Map<String, Object>  geteditData=screeningsService.getPreviousData(prevDate,patientId,encounterId,riskAssId,userId,screenId);
		emrResponseBean.setData(geteditData);
		return emrResponseBean;
	}
	
	/**
	 * To get Screening status for different Date criteria 
	 * @param Date
	 * @throws Exception
	 */
	@RequestMapping(value="/GetScreeningStatus", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getscreeningstatus(@RequestParam(value="startDate",required=false,defaultValue="-1")String startDate,
			@RequestParam(value="EndDate",required=false,defaultValue="-1")String EndDate,
			@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=false,defaultValue="-1") Integer encounterId,
			@RequestParam(value="LOINC", required=false,defaultValue="-1") String LOINC){
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		List<PatientClinicalFindingsBean> lists=screeningsService.getScreeningsStatus(patientId,encounterId,LOINC,startDate,EndDate);
		emrResponseBean.setData(lists);
		return emrResponseBean;
	}
	
	/**
	 * To get recommended Interventions of selected screenings
	 * @param screenId
	 * @throws Exception
	 */
	@RequestMapping(value="/GetScreenInterventions", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getScreenInterventions(@RequestParam(value="screenId",required=false,defaultValue="-1")Integer screenId){
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		String getScreenInterventions = screeningsService.getScreenInterventions(screenId);
		emrResponseBean.setData(getScreenInterventions);
		return emrResponseBean;
	}
	
	/**
	 * To Delete selected screening 
	 * @param screenId,encounterId,patientId
	 * @throws Exception
	 */
	@RequestMapping(value="/DeleteScreenings", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean deleteScreening(@RequestParam(value="riskId",required=true)Integer riskId,
			@RequestParam(value="patientId",required=true)Integer patientId,
			@RequestParam(value="encounterId",required=true)Integer encounterId,
			@RequestParam(value="screenId",required=true)String screenId){
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		screeningsService.deleteScreeningInfo(patientId,encounterId,riskId,screenId);
		List<RiskAssessmentBean> lists=screeningsService.getScreeningsList(patientId,encounterId,-1,-1);
		emrResponseBean.setData(lists);
		return emrResponseBean;
	}
		
}