package com.glenwood.glaceemr.server.application.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.Bean.macra.ecqm.CQMSpecification;
import com.glenwood.glaceemr.server.application.Bean.macra.ecqm.Category;
import com.glenwood.glaceemr.server.application.Bean.macra.ecqm.Code;
import com.glenwood.glaceemr.server.application.Bean.macra.ecqm.CodeSet;
import com.glenwood.glaceemr.server.application.Bean.macra.ecqm.EMeasure;
import com.glenwood.glaceemr.server.application.Bean.macra.ecqm.EMeasureUtils;
import com.glenwood.glaceemr.server.application.Bean.macra.ecqm.Valueset;
import com.glenwood.glaceemr.server.application.models.CarePlanConcern;
import com.glenwood.glaceemr.server.application.models.CarePlanGoal;
import com.glenwood.glaceemr.server.application.models.CarePlanIntervention;
import com.glenwood.glaceemr.server.application.models.CarePlanOutcome;
import com.glenwood.glaceemr.server.application.models.CarePlanRecommendedIntervention;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.chart.MIPS.ConfigurationDetails;
import com.glenwood.glaceemr.server.application.services.chart.MIPS.QPPConfigurationService;
import com.glenwood.glaceemr.server.application.services.chart.careplan.CarePlanConcernBean;
import com.glenwood.glaceemr.server.application.services.chart.careplan.CarePlanGoalBean;
import com.glenwood.glaceemr.server.application.services.chart.careplan.CarePlanInterventionBean;
import com.glenwood.glaceemr.server.application.services.chart.careplan.CarePlanOutcomeBean;
import com.glenwood.glaceemr.server.application.services.chart.careplan.CarePlanRecommendedInterventionBean;
import com.glenwood.glaceemr.server.application.services.chart.careplan.CarePlanService;
import com.glenwood.glaceemr.server.application.services.chart.careplan.CarePlanServiceImpl;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

@RestController
@Transactional
@RequestMapping(value="/user/CarePlan")
public class CarePlanController {
	
	@Autowired
	CarePlanService carePlanService;
	
	@Autowired
	CarePlanServiceImpl carePlanServiceImpl;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	QPPConfigurationService QppConfigurationService;
	
	/**
	 * To get the list of concerns,goals,outcomes and recommendended Intervetion on initial load which belongs to the given patientId and encounterId
	 * @param patientId
	 * @param encounterId
	 * @param episodeId
	 * @param fromDate
	 * @param toDate
	 * @return CarePlanGoal
	 * @throws Exception
	 */
	@RequestMapping(value ="/GetCarePlanInitialData", method = RequestMethod.POST) 
	@ResponseBody
	public EMRResponseBean getCarePlanIntialData(@RequestParam(value="patientId",required=false,defaultValue="-1")Integer patientId,@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId,@RequestParam(value="episodeId",required=false,defaultValue="-1")Integer episodeId,@RequestParam(value="episodeTypeId",required=false,defaultValue="0")Integer episodeTypeId,@RequestParam(value="previousEpisodeId",required=false,defaultValue="-1")Integer previousEpisodeId)throws Exception{
		Map<String, Object> lists=carePlanService.getCarePlanInitialData(patientId,encounterId,episodeId,episodeTypeId,previousEpisodeId);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(lists);
	//	auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of concerns which belongs to the given PatientId", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}	
	/**
	 * To get the list of goals which belongs to the given patientId, encounterId, episodeId ,concernId and goalId
	 * @param goalId
	 * @param concernId
	 * @param patientId
	 * @param encounterId
	 * @param episodeId
	 * @param fromDate
	 * @param toDate
	 * @return List
	 * @throws Exception
	 */
	@RequestMapping(value ="/GetCarePlanGoals", method = RequestMethod.POST) 
	@ResponseBody
	public List<CarePlanGoalBean> getCarePlanGoals(@RequestParam(value="goalId",required=false,defaultValue="-1")Integer goalId,@RequestParam(value="concernId",required=false,defaultValue="-1")Integer concernId,@RequestParam(value="patientId",required=false,defaultValue="-1")Integer patientId,@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId,@RequestParam(value="episodeId",required=false,defaultValue="-1")Integer episodeId,
			@RequestParam(value="frmDate", required=false,defaultValue="-1") String frmDate,
			@RequestParam(value="toDate", required=false,defaultValue="-1") String toDate)throws Exception{
		List<CarePlanGoalBean> listOfGoals=carePlanService.fetchCarePlanGoals(goalId,concernId,patientId,encounterId,episodeId,frmDate,toDate);
	//	auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of goals which belongs to the given PatientId", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return listOfGoals;
	}
	
	/**
	 * To get the list of concerns which belongs to the given patientId, encounterId, episodeId, concernId and concernId
	 * @param concernId
	 * @param patientId
	 * @param encounterId
	 * @param episodeId
	 * @param fromDate
	 * @param toDate
	 * @return List
	 * @throws Exception
	 */
	@RequestMapping(value ="/GetCarePlanConcerns", method = RequestMethod.GET) 
	@ResponseBody
	public List<CarePlanConcernBean> getCarePlanConcerns(@RequestParam(value="episodeId",required=false,defaultValue="-1")Integer episodeId,@RequestParam(value="concernId",required=false,defaultValue="-1")Integer concernId,@RequestParam(value="patientId",required=false,defaultValue="-1")Integer patientId,@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId,@RequestParam(value="categoryId",required=false,defaultValue="-1")Integer categoryId,
			@RequestParam(value="frmDate", required=false,defaultValue="-1") String frmDate,
			@RequestParam(value="toDate", required=false,defaultValue="-1") String toDate)throws Exception{
		List<CarePlanConcernBean> listOfConcerns=carePlanService.fetchCarePlanConcerns(concernId, patientId, categoryId, episodeId, encounterId,frmDate,toDate);
	//	auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of goals which belongs to the given PatientId", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return listOfConcerns;
	}
	
	/**
	 * To get the list of outcomes which belongs to the given outcomeId, goalId, patientId and encounterId
	 * @param patientId
	 * @param fromDate
	 * @param toDate
	 * @return List
	 * @throws Exception
	 */
	@RequestMapping(value ="/GetCarePlanOutcomes", method = RequestMethod.POST) 
	@ResponseBody
	public List<CarePlanOutcomeBean> getCarePlanOutcomes(@RequestParam(value="outcomeId",required=false,defaultValue="-1")Integer outcomeId,@RequestParam(value="goalId",required=false,defaultValue="-1")Integer goalId,@RequestParam(value="patientId",required=false,defaultValue="-1")Integer patientId,@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId,@RequestParam(value="episodeId",required=false,defaultValue="-1")Integer episodeId,
			@RequestParam(value="frmDate", required=false,defaultValue="-1") String frmDate,
			@RequestParam(value="toDate", required=false,defaultValue="-1") String toDate)throws Exception{
		List<CarePlanOutcomeBean> listOfGoals=carePlanService.fetchCarePlanOutcomes(outcomeId,goalId,patientId,encounterId,episodeId,frmDate,toDate);
		//	auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of goals which belongs to the given PatientId", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return listOfGoals;
	}
	
	/**
	 * To save given care plan concern
	 * @param carePlanConcernBean
	 * @return EMRResponseBean
	 * @throws Exception
	 */
	@RequestMapping(value="/SaveCarePlanConcern", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean saveCarePlanConcern(@RequestBody CarePlanConcernBean carePlanConcernBean){
		List<CarePlanConcernBean> carePlanConcerns = carePlanService.saveCarePlanConcern(carePlanConcernBean);
		EMRResponseBean bean= new EMRResponseBean();
		bean.setData(carePlanConcerns);
		return bean;		
	}
	
	/**
	 * To save given care plan goal
	 * @param carePlanGoalBean
	 * @return EMRResponseBean
	 * @throws Exception
	 */
	@RequestMapping(value="/SaveCarePlanGoal", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean saveCarePlanGoal(@RequestBody CarePlanGoalBean carePlanGoalBean){
		List<CarePlanGoalBean> carePlanGoals = carePlanService.saveCarePlanGoal(carePlanGoalBean);
		EMRResponseBean bean= new EMRResponseBean();
		bean.setData(carePlanGoals);
		return bean;		
	}
	
	/**
	 * To save given care plan outcome
	 * @param goalId
	 * @param providerId
	 * @param patientId
	 * @param encounterId
	 * @param outcomeProgress
	 * @param outcomeReview
	 * @param outcomeTarget
	 * @param outcomeNotes
	 * @param outcomeStatus
	 * @return EMRResponseBean
	 */
	@RequestMapping(value ="/SaveCarePlanOutcome", method = RequestMethod.POST) 
	@ResponseBody
	
	public EMRResponseBean SaveCarePlanOutcomes(@RequestParam(value="goalId",required=false,defaultValue="-1")Integer goalId,@RequestParam(value="providerId",required=false,defaultValue="-1")Integer providerId,@RequestParam(value="patientId",required=true,defaultValue="-1")Integer patientId,@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId,@RequestParam(value="outcomeProgress",required=false,defaultValue="-1")Integer outcomeProgress,@RequestParam(value="outcomeReview",required=false,defaultValue="-1")String outcomeReview,@RequestParam(value="outcomeTarget",required=false,defaultValue="-1")String outcomeTarget,@RequestParam(value="outcomeNotes",required=false,defaultValue="")String outcomeNotes,@RequestParam(value="outcomeStatus",required=false,defaultValue="-1")Integer outcomeStatus,@RequestParam(value="episodeId",required=false,defaultValue="-1")Integer episodeId,
			@RequestParam(value="goalAssistanceStatus",required=false,defaultValue="-1")Integer goalAssStatus,@RequestParam(value="goalLevelStatus",required=false,defaultValue="-1")Integer goalLevelStatus,
			@RequestParam(value="targetedGoal",required=false,defaultValue="false")Boolean targetedGoal){
		List<CarePlanGoalBean> carePlanGoals = carePlanService.saveCarePlanOutcomes(goalId,providerId,patientId,encounterId,outcomeProgress,outcomeReview,outcomeTarget,outcomeNotes,outcomeStatus,episodeId,goalAssStatus,goalLevelStatus,targetedGoal);
		EMRResponseBean bean= new EMRResponseBean();
		bean.setData(carePlanGoals);
		//auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of goals which belongs to the given PatientId", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return bean;
	}
	
	
	
	/**
	 * To get the list of vitals which belongs to the given encounterId
	 * @param patientId
	 * @param encounterId
	 * @return EMRResponseBean
	 * @throws Exception
	 */
	@RequestMapping(value ="/GetVitals", method = RequestMethod.GET) 
	@ResponseBody
	public EMRResponseBean getVitals(@RequestParam(value="patientId",required=true,defaultValue="-1")Integer patientId,@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId){
		String carePlanVitals = carePlanService.getVitals(patientId,encounterId);
		EMRResponseBean bean= new EMRResponseBean();
		bean.setData(carePlanVitals);
		//	auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of goals which belongs to the given PatientId", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return bean;
	}
	

	/**
	 * To import shortcuts based on given shortcut IDs,patientId
	 * @param patientId
	 * @param encounterId
	 * @param episodeId
	 * @param shortcutIDs
	 * @param providerId
	 * @return EMRResponseBean
	 * @throws Exception
	 */
	@RequestMapping(value ="/ImportCarePlanGoal", method = RequestMethod.POST) 
	@ResponseBody
	public EMRResponseBean importCarePlanGoal(@RequestParam(value="patientId",required=false,defaultValue="-1")Integer patientId,@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId,@RequestParam(value="shortcutIDs",required=false,defaultValue="")String shortcutIDs,@RequestParam(value="providerId",required=false,defaultValue="-1")Integer providerId,@RequestParam(value="episodeId",required=false,defaultValue="-1")Integer episodeId,@RequestParam(value="shortcutTerm",required=false,defaultValue="-1")Integer shortcutTerm,@RequestParam(value="categoryId",required=false,defaultValue="0")Integer categoryId,@RequestParam(value="previousEpisodeId",required=false,defaultValue="-1")Integer previousEpisodeId,@RequestParam(value="summaryMode",required=false,defaultValue="-1")Integer summaryMode)throws Exception{
		Map<String, Object> lists=carePlanService.importCarePlanShortcuts(patientId,encounterId,shortcutIDs,providerId,episodeId,shortcutTerm,categoryId,previousEpisodeId,summaryMode);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(lists);
		//auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of concerns which belongs to the given PatientId", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To save progress plan notes
	 * @param encounterId
	 * @param planText
	 */
	
	@RequestMapping(value ="/SaveProgressPlanNotes", method = RequestMethod.POST) 
	@ResponseBody
	public void saveProgressPlanNotes(@RequestParam(value="encounterId",required=true,defaultValue="-1") Integer encounterId,@RequestParam(value="planNotes",required=false,defaultValue="") String planText) {
		carePlanService.saveProgressPlanNotes(encounterId, planText);
	}
	
	/**
	 * To clear Progress plan notes
	 * @param encounterId
	 */
	@RequestMapping(value ="/ClearProgressPlanNotes", method = RequestMethod.GET) 
	@ResponseBody
	public void clearProgressPlanNotes(@RequestParam(value="encounterId",required=true,defaultValue="-1") Integer encounterId) {
		carePlanService.clearProgressPlanNotes(encounterId);
	}
	
	/**
	 * To save subjective notes
	 * @param gwid
	 * @param chartId
	 * @param patientId
	 * @param encounterId
	 * @param subjectiveNotes
	 */
	
	@RequestMapping(value ="/SaveProgressSubjectiveNotes", method = RequestMethod.POST) 
	@ResponseBody
	public void saveProgressSubjectiveNotes(@RequestParam(value="gwid",required=true,defaultValue="-1") String gwid,@RequestParam(value="chartId",required=true,defaultValue="-1") Integer chartId,@RequestParam(value="patientId",required=true,defaultValue="-1") Integer patientId,@RequestParam(value="encounterId",required=true,defaultValue="-1") Integer encounterId,@RequestParam(value="subjectiveNotes",required=false,defaultValue="") String subjectiveNotes) {
		carePlanService.saveProgressSubjectiveNotes(gwid,chartId,patientId,encounterId, subjectiveNotes);
	}
	
	/**
	 * To clear subjective notes
	 * @param gwid
	 * @param patientId
	 * @param encounterId
	 */
	
	@RequestMapping(value ="/ClearProgressSubjectiveNotes", method = RequestMethod.GET) 
	@ResponseBody
	public void clearProgressSubjectiveNotes(@RequestParam(value="gwid",required=true,defaultValue="-1") String gwid,@RequestParam(value="patientId",required=true,defaultValue="-1") Integer patientId,@RequestParam(value="encounterId",required=true,defaultValue="-1") Integer encounterId) {
		carePlanService.clearProgressSubjectiveNotes(gwid,patientId,encounterId);
	}

	/**
	 * To import shortcuts based on given shortcut IDs,patientId from previous visits
	 * @param patientId
	 * @param encounterId
	 * @param shortcutIDs
	 * @param providerId
	 * @return EMRResponseBean
	 * @throws Exception
	 */
	@RequestMapping(value ="/ImportCarePlanGoalFromPreviousVisits", method = RequestMethod.POST) 
	@ResponseBody
	public EMRResponseBean importCarePlanGoalFromPreviousVisits(@RequestParam(value="patientId",required=false,defaultValue="-1")Integer patientId,@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId,@RequestParam(value="previousVisistShortcutIDs",required=false,defaultValue="")String previousVisistShortcutIDs,@RequestParam(value="providerId",required=false,defaultValue="-1")Integer providerId,@RequestParam(value="episodeId",required=false,defaultValue="-1")Integer episodeId,@RequestParam(value="shortcutTermPrevious",required=false,defaultValue="-1")Integer shortcutTermPrevious,@RequestParam(value="categoryId",required=false,defaultValue="-1")Integer categoryId,@RequestParam(value="previousEpisodeId",required=false,defaultValue="-1")Integer previousEpisodeId)throws Exception{
		Map<String, Object> lists=carePlanService.importCarePlanShortcutsFromPrevious(patientId,encounterId,previousVisistShortcutIDs,providerId,episodeId,shortcutTermPrevious,categoryId,previousEpisodeId);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(lists);
		//auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of concerns which belongs to the given PatientId", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To load progress notes
	 * @param patientId
	 * @param encounterId
	 * @param episodeId
	 * @param gwid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/GetCarePlanProgressInitialData", method = RequestMethod.POST) 
	@ResponseBody
	
	public EMRResponseBean getCarePlanProgressIntialData(@RequestParam(value="patientId",required=false,defaultValue="-1")Integer patientId,@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId,@RequestParam(value="episodeId",required=false,defaultValue="-1")Integer episodeId,@RequestParam(value="gwid",required=false,defaultValue="-1")String gwid)throws Exception{
		Map<String, Object> lists=carePlanService.getCarePlanProgressInitialData(patientId,encounterId,episodeId,gwid);
	//	auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of concerns which belongs to the given PatientId", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(lists);
		//auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.THERAPHYSESSION, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved providers,pos,groups data", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	

	/**
	 * To insert intervention data from care plan tab
	 * @param patientId,encounterId,description,snomedCode
	 * @throws Exception
	 */
	/*
	@RequestMapping(value ="/SaveCarePlanInterventionData", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean saveInterventionData(@RequestBody List<CarePlanInterventionBean> carePlanInterventionBean,
			@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=true) Integer encounterId) throws JSONException{
		//String test="";
		//JSONArray json=new JSONArray(test);
		carePlanService.saveInterventionData(carePlanInterventionBean);
		List<CarePlanInterventionBean> carePlanInterventions=carePlanServiceImpl.fetchInterventionPlanData(-1,-1,-1,patientId,encounterId,-1);
		EMRResponseBean bean= new EMRResponseBean();
		bean.setData(carePlanInterventions);
		//auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.CREATEORUPDATE, 0, Log_Outcome.SUCCESS, "Intervention Added Successfully for "+patientId, -1, request.getRemoteAddr(), patientId, "encounterId ="+encounterId, LogUserType.USER_LOGIN, "", "");
		return bean;		
	}*/
	
	/*
	@RequestMapping(value ="/SaveCarePlanInterventionData", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean saveInterventionData(@RequestBody CarePlanInterventionBean carePlanInterventionBean){
		List<CarePlanInterventionBean> carePlanInterventions = carePlanService.saveInterventionData(carePlanInterventionBean);
		EMRResponseBean bean= new EMRResponseBean();
		bean.setData(carePlanInterventions);
		return bean;		
	}*/
	
	/**
	 * To insert intervention data from care plan tab
	 * @param patientId,encounterId,description,snomedCode
	 * @throws JSONException 
	 * @throws Exceptions
	 */
	@RequestMapping(value ="/SaveCarePlanInterventionData", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean saveInterventionData(@RequestBody List<CarePlanInterventionBean> carePlanInterventionBean,
			@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=true) Integer encounterId) throws JSONException{
		//String test="";
		//JSONArray json=new JSONArray(test);
		carePlanService.saveInterventionData(carePlanInterventionBean);
		List<CarePlanInterventionBean> carePlanInterventions=carePlanService.fetchInterventionPlanData(-1,-1,-1,patientId,encounterId,-1);
		EMRResponseBean bean= new EMRResponseBean();
		bean.setData(carePlanInterventions);
		return bean;		
	}
	
	/**
	 * To insert care Plan Intervention data for patient
	 * @param patientId,encounterId,description,snomedCode
	 * @throws Exception
	 */
	
	@RequestMapping(value ="/InsertCarePlanIntervention", method = RequestMethod.GET)
	@ResponseBody
	
	public EMRResponseBean SaveCarePlanIntervention(
			@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=true) Integer encounterId,
			@RequestParam(value="elementName", required=true) String description,
			@RequestParam(value="elementGwid", required=true) String snomedCode,
			@RequestParam(value="status", required=true) Integer status,
			@RequestParam(value="userId", required=true) Integer userId,
			@RequestParam(value="interventionMode", required=true) Integer interventionMode,
			@RequestParam(value="intervenDxCode", required=true) String intervenDxCode,
			@RequestParam(value="intervenDxDesc", required=true) String intervenDxDesc,
			@RequestParam(value="intervenDxCodeSystem", required=true) String intervenDxCodeSystem,
			@RequestParam(value="Status", required=false,defaultValue="-1") Integer Status,
			@RequestParam(value="frmDate", required=false,defaultValue="-1") String frmDate,
			@RequestParam(value="toDate", required=false,defaultValue="-1") String toDate){
			carePlanService.saveCarePlanIntervention(patientId,encounterId,description,snomedCode,status,userId,interventionMode,intervenDxCode,intervenDxDesc,intervenDxCodeSystem);
			List<CarePlanInterventionBean> getData = carePlanService.fetchCarePlanInterventions(patientId,encounterId,interventionMode,intervenDxCode,Status,frmDate,toDate);
		//	auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.CREATE, 0, Log_Outcome.SUCCESS, "Intervention saved for patient:"+intervenDxDesc, -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
			EMRResponseBean bean= new EMRResponseBean();
			bean.setData(getData);
				return bean;
	}
	
	/**
	 * To get care Plan Intervention data of a patient for the encounter
	 * @param patientId,encounterId
	 * @throws Exception
	 */
	@RequestMapping(value="/FetchCarePlanIntervention", method = RequestMethod.GET)
	@ResponseBody
	
	public EMRResponseBean getCarePlanData(
			@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=false,defaultValue="-1") Integer encounterId,
			@RequestParam(value="interventionMode", required=false,defaultValue="-1") Integer interventionMode,
			@RequestParam(value="intervenDxCode", required=false,defaultValue="-1") String intervenDxCode,
			@RequestParam(value="Status", required=false,defaultValue="-1") Integer Status,
			@RequestParam(value="frmDate", required=false,defaultValue="-1") String frmDate,
			@RequestParam(value="toDate", required=false,defaultValue="-1") String toDate){
			List<CarePlanInterventionBean> getData = carePlanService.fetchCarePlanInterventions(patientId,encounterId,interventionMode,intervenDxCode,Status,frmDate,toDate);
			EMRResponseBean bean= new EMRResponseBean();
			bean.setData(getData);
				return bean;
		
	}
	
	/**
	 * To delete selected intervention data of a patient
	 * @param patientId,encounterId,interventionId
	 * @throws Exception
	 */
	@RequestMapping(value="/DeleteCarePlanIntervention", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean deleteCarePlan(
			@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=true) Integer encounterId,
			@RequestParam(value="delVal", required=true) Integer delVal,
			@RequestParam(value="interventionMode", required=true) Integer interventionMode,
			@RequestParam(value="intervenDxCode", required=true) String intervenDxCode,
			@RequestParam(value="Status", required=false,defaultValue="-1") Integer Status,
			@RequestParam(value="frmDate", required=false,defaultValue="-1") String frmDate,
			@RequestParam(value="toDate", required=false,defaultValue="-1") String toDate){
			carePlanService.deleteCarePlanIntervention(patientId,encounterId,delVal);
			List<CarePlanInterventionBean> getData = carePlanService.fetchCarePlanInterventions(patientId,encounterId,interventionMode,intervenDxCode,Status,frmDate,toDate);
			//auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.DELETE, 0, Log_Outcome.SUCCESS, "Intervention Deleted for patient with Intervention id:"+delVal, -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
			EMRResponseBean bean= new EMRResponseBean();
			bean.setData(getData);
				return bean;
	}
	
	
	/**
	 * To select interventions for edit
	 * @param patientId,encounterId,interventionId
	 * @throws Exception
	 */
	@RequestMapping(value="/GetEditCarePlanIntervention", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getEditCarePlanIntervention(@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=true) Integer encounterId,
			@RequestParam(value="intervenId", required=true) Integer intervenId){
			Map<String, Object> lists=carePlanService.getEditCarePlanIntervention(patientId,encounterId,intervenId);
			EMRResponseBean bean= new EMRResponseBean();
			bean.setData(lists);
			return bean;
	}
	
	/**
	 * To update intervention data 
	 * @param patientId,encounterId,interventionId
	 * @throws Exception
	 */	
	@RequestMapping(value="/UpdateCarePlanInterventionData", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean updateCarePlanIntervention(@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=true) Integer encounterId,
			@RequestParam(value="code", required=true) Integer interventionId,
			@RequestParam(value="editedNotes", required=true) String editedNotes,
			@RequestParam(value="orderedBy", required=true) Integer orderedBy,
			@RequestParam(value="performedBy", required=true) Integer performedBy,
			@RequestParam(value="notDoneBy", required=true) Integer notDoneBy,
			@RequestParam(value="notDoneReason", required=true) String notDoneReason,
			@RequestParam(value="userId", required=true) Integer userId,
			@RequestParam(value="status", required=true) Integer status,
			@RequestParam(value="perfOn", required=true) String perfOn,
			@RequestParam(value="orderedOn", required=true) String orderedOn,
			@RequestParam(value="interventionMode", required=true) Integer interventionMode,
			@RequestParam(value="intervenDxCode", required=true) String intervenDxCode,
			@RequestParam(value="Status", required=false,defaultValue="-1") Integer Status,
			@RequestParam(value="frmDate", required=false,defaultValue="-1") String frmDate,
			@RequestParam(value="toDate", required=false,defaultValue="-1") String toDate){
			carePlanService.updateCarePlanIntervention(patientId,encounterId,interventionId,editedNotes,orderedBy,performedBy,notDoneBy,notDoneReason,userId,status,perfOn,orderedOn);
			//auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "Intervention updated for patient with Intervention id:"+interventionId, -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
			List<CarePlanInterventionBean> getData = carePlanService.fetchCarePlanInterventions(patientId,encounterId,interventionMode,intervenDxCode,Status,frmDate,toDate);
			EMRResponseBean bean= new EMRResponseBean();
			bean.setData(getData);
				return bean;
	}
	
	/**
	 * To get care plan summary 
	 * @param patientId
	 * @param episodeId
	 * @param episodeTypeId
	 * @param encounterId
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/GetCarePlanSummary",method= RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getCarePlanSummary(@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="episodeId",required=false,defaultValue="-1")Integer episodeId,
			@RequestParam(value="episodeTypeId",required=false,defaultValue="-1")Integer episodeTypeId,
			@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId) throws ParseException{
			Map<String, Object> lists=carePlanService.getCarePlanSummaryData(patientId,episodeId,encounterId,episodeTypeId);
			EMRResponseBean emrResponseBean = new EMRResponseBean();
			emrResponseBean.setData(lists);
			return emrResponseBean;
				
	}
	
	/**
	 * To save care plan summary
	 * @param patientId
	 * @param episodeId
	 * @param userId
	 * @param completeJSON
	 * @throws Exception
	 */
	
	@RequestMapping(value="/SaveCarePlanSummary",method= RequestMethod.POST)
	@ResponseBody
	public void saveCarePlanSummary(@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="episodeId",required=false,defaultValue="-1")Integer episodeId,
			@RequestParam(value="userId",required=false,defaultValue="-1")Integer userId,
			@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId,
			@RequestParam(value="completeJSON", required=true) String completeJSON) throws Exception{
		carePlanService.saveCarePlanSummaryData(patientId,encounterId,episodeId,completeJSON,userId);
	}
	
	/**
	 * To inactive concern and associated goals
	 * @return EMRResponseBean
	 * @throws Exception
	 */
	@RequestMapping(value ="/InactiveConcernAndGoal", method = RequestMethod.POST) 
	@ResponseBody
	public EMRResponseBean inactiveConcernAndGoal(@RequestBody CarePlanConcernBean carePlanConcernBean,@RequestParam(value="previousEpisodeId",required=false,defaultValue="-1")Integer previousEpisodeId)throws Exception{
		Map<String, Object> lists=carePlanService.saveConcernAndGoal(carePlanConcernBean,previousEpisodeId);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(lists);
		//auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of concerns which belongs to the given PatientId", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	
	/**
	 * To insert Recommended intervention data from care plan tab
	 * @throws Exception
	 */
	@RequestMapping(value ="/SaveCarePlanRecommendedIntervention", method = RequestMethod.POST)
	@ResponseBody
	public  EMRResponseBean saveRecommendedInterventionData(@RequestBody CarePlanRecommendedInterventionBean CarePlanRecommendedInterventionBean){
		List<CarePlanRecommendedInterventionBean> carePlanRecommInterventions = carePlanService.saveCarePlanRecommendedIntervention(CarePlanRecommendedInterventionBean);
	EMRResponseBean bean= new EMRResponseBean();
		bean.setData(carePlanRecommInterventions);
		return bean;	
	}
	
	/**
	 * To fetch Recommended intervention data from care plan tab
	 * @throws Exception
	 */
	@RequestMapping(value ="/FetchCarePlanRecommendedIntervention", method = RequestMethod.POST)
	@ResponseBody
	public  EMRResponseBean fetchRecommendedInterventionData(@RequestParam(value="patientId", required=true) Integer patientId,@RequestParam(value="patientId", required=true) Integer encounterId,
			@RequestParam(value="episodeId",required=false,defaultValue="-1")Integer episodeId,
			@RequestParam(value="frmDate", required=false,defaultValue="-1") String frmDate,
			@RequestParam(value="toDate", required=false,defaultValue="-1") String toDate){
		List<CarePlanRecommendedInterventionBean> carePlanRecommInterventions = carePlanService.fetchRecommIntervention(patientId,encounterId,episodeId,frmDate,toDate);
		EMRResponseBean bean= new EMRResponseBean();
		bean.setData(carePlanRecommInterventions);
		return bean;	
	}
	
	/**
	 * To delete selected recommended intervention data of a patient
	 * @param patientId,encounterId,interventionId
	 * @throws Exception
	 */
	@RequestMapping(value="/DeleteCarePlanRecommendedIntervention", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean deleteCarePlanRecommIntervention(
			@RequestParam(value="patientId", required=true) Integer patientId,
			@RequestParam(value="encounterId", required=true) Integer encounterId,
			@RequestParam(value="episodeId", required=true) Integer episodeId,
			@RequestParam(value="delVal", required=true) Integer delVal,
			@RequestParam(value="frmDate", required=false,defaultValue="-1") String frmDate,
			@RequestParam(value="toDate", required=false,defaultValue="-1") String toDate){
			carePlanService.deleteCarePlanRecommIntervention(patientId,encounterId,delVal);
			List<CarePlanRecommendedInterventionBean> getData = carePlanService.fetchRecommIntervention(patientId, encounterId, episodeId,frmDate,toDate);
			//auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.DELETE, 0, Log_Outcome.SUCCESS, "Intervention Deleted for patient with Intervention id:"+delVal, -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
			EMRResponseBean bean= new EMRResponseBean();
			bean.setData(getData);
				return bean;
	}
	
	/** 
	 * To save review care plan
	 * @param patientId
	 * @throws Exception
	 */
	@RequestMapping(value="/SaveCarePlanLog", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean  saveCarePlanLog(@RequestParam(value="patientId", required=true) Integer patientId,@RequestParam(value="userId", required=true) Integer userId,@RequestParam(value="reviewStatus", required=true) Boolean reviewStatus) {
		List<Object[]> logList = carePlanService.saveCarePlanLog(patientId, userId,reviewStatus);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(logList);
		return emrResponseBean;
	}
	
	/**
	 * To get the list of care plans for print which belongs to the given patientId and encounterId and episodeId
	 * @param patientId
	 * @param encounterId
	 * @param episodeId
	 * @return CarePlanGoal
	 * @throws Exception
	 */
	@RequestMapping(value ="/GetCarePlanPrint", method = RequestMethod.POST) 
	@ResponseBody
	public EMRResponseBean getCarePlanPrint(@RequestParam(value="patientId",required=false,defaultValue="-1")Integer patientId,@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId,@RequestParam(value="episodeId",required=false,defaultValue="-1")Integer episodeId)throws Exception{
		Map<String, Object> lists=carePlanService.getCarePlanPrint(patientId,encounterId,episodeId);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(lists);
	//	auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of concerns which belongs to the given PatientId", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}	
	@RequestMapping(value="/AddFrequentIntervention",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean AddFrequentIntervention(@RequestParam(value="elementName", required=true) String elementName,
			@RequestParam(value="elementGwid", required=true) String snomed,
			@RequestParam(value="userId", required=true) Integer userId,
			@RequestParam(value="providerId", required=true) Integer providerId,
			@RequestParam(value="isfrmconfig", required=true) Integer isfrmconfig,
			@RequestParam(value="categoryType", required=false) String categoryType,
			@RequestParam(value="groupName", required=false) String groupName,
			@RequestParam(value="codeOid", required=false) String codeOid) throws Exception{
			carePlanService.addFrequentIntervention(elementName,snomed,userId,providerId,isfrmconfig,categoryType,codeOid,groupName);
			List<Object> getFrequentList = carePlanService.fetchFrequentInterventions(userId,categoryType);
			EMRResponseBean emrResponseBean = new EMRResponseBean();
			emrResponseBean.setData(getFrequentList);
			return emrResponseBean;
	}
	@RequestMapping(value="/LoadFrequentIntervention",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean LoadFrequentIntervention(@RequestParam(value="userId", required=false) Integer userId,
			@RequestParam(value="categoryType", required=false) String categoryType){
		List<Object> getFrequentList = carePlanService.fetchFrequentInterventions(userId,categoryType);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(getFrequentList);
		return emrResponseBean;
	}
	@RequestMapping(value="/DeleteFrequentIntervention",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean DeleteFrequentIntervention(@RequestParam(value="delid", required=true) String delid,
			@RequestParam(value="userId", required=true) Integer userId,
			@RequestParam(value="categoryType", required=false) String categoryType) throws Exception{
			carePlanService.deleteFrequentIntervention(delid);
			List<Object> getFrequentList = carePlanService.fetchFrequentInterventions(userId,categoryType);
			EMRResponseBean emrResponseBean = new EMRResponseBean();
			emrResponseBean.setData(getFrequentList);
			return emrResponseBean;
	}
	/**	To update Frequent Interventions Group name
	*/	
	@RequestMapping(value="/UpdateFrequentIntervention",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean UpdateFrequentInterventionGroup(@RequestParam(value="groupVal", required=true) Integer groupVal,
			@RequestParam(value="groupName", required=true) String groupName) throws Exception{
			carePlanService.UpdateFrequentInterventionGroup(groupVal,groupName);
			//List<Object> getFrequentList = carePlanService.fetchFrequentInterventions(userId);
			EMRResponseBean emrResponseBean = new EMRResponseBean();
			emrResponseBean.setData("");
			return emrResponseBean;
	}
	/**	To load Frequent Interventions Group
	*/	
	@RequestMapping(value="/FrequentInterventionGroups",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean FrequentInterventionGroups(@RequestParam(value="groupName", required=true) String groupName) throws Exception{
			Map<String, Object> lists = carePlanService.FrequentInterventionGroups(groupName);
			//List<Object> getFrequentList = carePlanService.fetchFrequentInterventions(userId);
			EMRResponseBean emrResponseBean = new EMRResponseBean();
			emrResponseBean.setData(lists);
			return emrResponseBean;
	}
	
	/**	To load Frequent Interventions Group
	*/	
	@RequestMapping(value="/SaveCarePlanHealthStatus",method=RequestMethod.GET)
	@ResponseBody
	public void saveCarePlanHealthStatus(@RequestParam(value="patientId", required=false) Integer patientId,@RequestParam(value="encounterId", required=false) Integer encounterId,
			@RequestParam(value="episodeId", required=false) Integer episodeId,@RequestParam(value="description", required=false) String desc,
			@RequestParam(value="userId", required=false) Integer userId,@RequestParam(value="status", required=false) Integer status,@RequestParam(value="code", required=false) String code) throws Exception{
			carePlanService.saveCarePlanStatus(patientId, encounterId, episodeId, desc, userId, status,code);
	}
	
	/**
	 * To get the list of outcomes for CDA which belongs to the given outcomeId, goalId, patientId and encounterId
	 * @param patientId
	 * @return List
	 * @throws Exception
	 */
	@RequestMapping(value ="/GetCarePlanOutcomesForCDa", method = RequestMethod.POST) 
	@ResponseBody
	public List<CarePlanGoalBean> getCarePlanOutcomesForCDA(@RequestParam(value="outcomeId",required=false,defaultValue="-1")Integer outcomeId,@RequestParam(value="goalId",required=false,defaultValue="-1")Integer goalId,@RequestParam(value="patientId",required=false,defaultValue="-1")Integer patientId,@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId,@RequestParam(value="episodeId",required=false,defaultValue="-1")Integer episodeId,
			@RequestParam(value="frmDate", required=false,defaultValue="-1") String frmDate,
			@RequestParam(value="toDate", required=false,defaultValue="-1") String toDate)throws Exception{
		List<CarePlanGoalBean> listOfGoals=carePlanService.fetchCarePlanOutcomesForCDA(outcomeId,goalId,patientId,encounterId,episodeId,frmDate,toDate);
		//	auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of goals which belongs to the given PatientId", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return listOfGoals;
	}
	
	/**
	 * To import selected encounter shortcuts for progress notes
	 * @param patientId
	 * @param encounterId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/GetListOfImportShortcut", method = RequestMethod.POST) 
	@ResponseBody
	public List<Object> getImportData(@RequestParam(value="patientId",required=false,defaultValue="-1")Integer patientId,@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId)throws Exception{
		List<Object> listOfShrtImport=carePlanService.getImportShortcuts(patientId, encounterId);
		//	auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of goals which belongs to the given PatientId", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return listOfShrtImport;
	}
	/**
	 * To save assistance and level status
	 * @param patientId
	 * @param episodeId
	 * @param goalId
	 * @param providerId
	 * @param status
	 * @param levelStatus
	 * @param encounterId
	 * @return
	 */
	@RequestMapping(value ="/SaveAssistanceOrLevelStatus", method = RequestMethod.POST) 
	@ResponseBody
	public EMRResponseBean saveAssistanceOrLevelStatus(@RequestParam(value="patientId",required=false,defaultValue="-1")Integer patientId,
			@RequestParam(value="episodeId",required=false,defaultValue="-1")Integer episodeId,
			@RequestParam(value="goalId",required=false,defaultValue="-1")Integer goalId,
			@RequestParam(value="providerId",required=false,defaultValue="-1")Integer providerId,
			@RequestParam(value="status",required=false,defaultValue="-1")Integer status,
			@RequestParam(value="levelStatus",required=false,defaultValue="-1")Integer levelStatus,
			@RequestParam(value="encounterId",required=false,defaultValue="-1")Integer encounterId) {
		List<CarePlanGoalBean> carePlanGoals = carePlanService.saveAssistanceStatus(patientId, episodeId, goalId,providerId,status,levelStatus,encounterId);
		EMRResponseBean bean= new EMRResponseBean();
		bean.setData(carePlanGoals);
		return bean;
	}
	
	/**
	 * To get the list of shortcuts for goal library
	 * @param categoryId
	 * @param searchType
	 * @param searchStr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/GetListOfShortcuts", method = RequestMethod.POST) 
	@ResponseBody
	public List<Object> getListOfShortcuts(@RequestParam(value="categoryId",required=false,defaultValue="-1")Integer categoryId,
			@RequestParam(value="searchType",required=false,defaultValue="-1")String searchType,
			@RequestParam(value="searchStr",required=false,defaultValue="-1")String searchStr)throws Exception{
		List<Object> listOfShrtImport=carePlanService.fetchCarePlanShortcuts(categoryId, searchType, searchStr);
		//	auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CAREPLAN, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of goals which belongs to the given PatientId", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return listOfShrtImport;
	}
	
	// TO Get MEASURES IDS
		@RequestMapping(value = "/getFreqMeasureIds", method = RequestMethod.GET)
		@ResponseBody
		public EMRResponseBean getFrequentMeasureIds(
				@RequestParam(value = "providerId", required = true) Integer providerId,
				@RequestParam(value="accountId", required=true) String accountId,
				@RequestParam(value = "year", required = true) Integer year,
				@RequestParam(value = "sharedFolder", required = true) String  sharedFolder)throws Exception {
			EMRResponseBean result=new EMRResponseBean();
			EMeasureUtils utils = new EMeasureUtils();
			List<ConfigurationDetails> indiMeasureids=QppConfigurationService.getMeasureIds(providerId,year);
			String measureId="";
			for(int i=0;i<indiMeasureids.size();i++){
				measureId += indiMeasureids.get(i).getQualityMeasuresProviderMappingMeasureId()+",";
				List<EMeasure> emeasure = utils.getMeasureBeanDetails(year,measureId, sharedFolder,accountId);
					CQMSpecification specification = emeasure.get(0).getSpecification();
					HashMap<String, Category> qdmCatagory = specification.getQdmCategory();
					List<Object>  cmsIdsList=new ArrayList<Object>();
						for(int j=0 ;j<emeasure.size();j++){
							if(qdmCatagory.containsKey("Intervention")){
								Map<String, Object> cmsIdsListObject=new HashMap<String, Object>();
								try {
									cmsIdsListObject.put("CmsId", emeasure.get(j).getCmsId().toString());
									cmsIdsListObject.put("Id", emeasure.get(j).getId());
									cmsIdsList.add(cmsIdsListObject);
								}catch (Exception e) {
							}
							result.setData(cmsIdsList);
							}
						}
					}
				return result;
		}
		@RequestMapping(value = "/getFreqMeasureDescription", method = RequestMethod.GET)
		@ResponseBody
		public EMRResponseBean getFrequentMeasureDescription(
				@RequestParam(value="accountId", required=true) String accountId,
				@RequestParam(value = "year", required = true) Integer year,
				@RequestParam(value = "measureId", required = true) String measureId,
				@RequestParam(value = "sharedFolder", required = true) String  sharedFolder) throws Exception {
			EMRResponseBean result=new EMRResponseBean();
			EMeasureUtils utils = new EMeasureUtils();
			List<EMeasure> emeasure = utils.getMeasureBeanDetails(year,measureId, sharedFolder,accountId);
			CQMSpecification specification = emeasure.get(0).getSpecification();
			HashMap<String, Category> qdmCatagory = specification.getQdmCategory();
			for(int p=0 ;p<emeasure.size();p++){
				List<Object> code=new ArrayList<Object>();
				List<Object> description=new ArrayList<Object>();
				List<Object> name=new ArrayList<Object>();
				HashMap<String, HashMap<String, List<Object>>> interventionList = new HashMap<String, HashMap<String,List<Object>>>();
				HashMap<String, List<Object>> interventionCodeList;
				if(qdmCatagory.containsKey("Intervention")){
				Category interventionCategory = qdmCatagory.get("Intervention");
				List<Valueset> valueSet = interventionCategory.getValueSet();
				for(int i=0;i<valueSet.size();i++)
				{
					interventionCodeList = new HashMap<String, List<Object>>();
					code=new ArrayList<Object>();
					description=new ArrayList<Object>();
					name=new ArrayList<Object>();
					name.add(valueSet.get(i));
					List<CodeSet> codeSetList=valueSet.get(i).getCodeSetList();
					for(int j=0;j<codeSetList.size();j++)
					{
						if(codeSetList.get(j).getCodeSystem().contains("SNOMEDCT")){
							List<Code> codeList=codeSetList.get(j).getCodeList();
							for(int k=0;k<codeList.size();k++){
								code.add(codeList.get(k));
								description.add(codeList.get(k));
							}
						}
					}
					interventionCodeList.put("Code", code);
					interventionCodeList.put("description", description);
					interventionCodeList.put("name", name);
					interventionList.put(valueSet.get(i).getName(), interventionCodeList);
				}
				result.setData(interventionList);
			}
		}
			return result;
		}

}