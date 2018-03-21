package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.PatientAllergies;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.chart.Allergies.AllergiesService;
import com.glenwood.glaceemr.server.application.services.chart.Allergies.AllergiesTypeBean;
import com.glenwood.glaceemr.server.application.services.chart.Allergies.AllergyBean;
import com.glenwood.glaceemr.server.application.services.chart.CurrentMedication.PatientAllergiesBean;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

/**
 * Controller for Allergies
 * @author Bhagya Lakshmi
 *
 */

@RestController
@Transactional
@RequestMapping(value="/user/Allergies")
public class AllergiesController {
	
	@Autowired
	AllergiesService AllergiesService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;

	/**
	 *  
	 * @return Getting patient allergies data
	 * @param chartId
	 * @throws Exception
	 */
	@RequestMapping(value ="/allergyData", method = RequestMethod.GET)
    @ResponseBody
	public EMRResponseBean getAllergyDetails( @RequestParam(value="chartId",required=false,defaultValue="-1")int chartId,
			@RequestParam(value="encounterId",required=false,defaultValue="-1")int encounterId,
			@RequestParam(value="statusList",required=false,defaultValue="-1")String statusList,
			@RequestParam(value="fromSoap",required=false,defaultValue="-1")int fromSoap)throws Exception
	{
		List<PatientAllergiesBean> allergies=AllergiesService.getAllergies(chartId,encounterId,statusList,fromSoap);
		EMRResponseBean allergyList = new EMRResponseBean();
		allergyList.setData(allergies);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved patient allergies", -1, request.getRemoteAddr(), -1, "chartId="+chartId, LogUserType.USER_LOGIN, "", "");
		return allergyList;
	}
	
	/**
	 * Method to retrieve allergy types
	 * @param gender
	 * @return
	 */
	@RequestMapping(value ="/retrievingAllergyType", method = RequestMethod.GET)
    @ResponseBody
	public EMRResponseBean getAllergyData(@RequestParam(value="gender",required=false,defaultValue="-1")Integer gender)
	{
		List<AllergiesTypeBean> allergyTypes=AllergiesService.retrievingAllergyType(gender);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(allergyTypes);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved allergy types", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * Method to get data for allergen search
	 * @param Criteria
	 * @param mode
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/searchAllergyData", method = RequestMethod.GET)
    @ResponseBody
	public EMRResponseBean getSearchAllergyData(@RequestParam(value="Criteria",required=false,defaultValue="-1")String Criteria,
			@RequestParam(value="mode",required=false,defaultValue="-1")int mode,
			 @RequestParam(value="offset",required=false,defaultValue="-1")int offset,
			 @RequestParam(value="limit",required=false,defaultValue="-1")int limit)throws Exception
	{
		AllergyBean SearchAllergyData=AllergiesService.searchAllergy(Criteria,mode,offset,limit);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(SearchAllergyData);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved data for allergen search", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * Method to retrieve reaction search data
	 * @param Criteria
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/getReactionsOfAllergies", method = RequestMethod.GET)
    @ResponseBody
	public EMRResponseBean getReactionsOfAllergies(@RequestParam(value="Criteria",required=false,defaultValue="-1")String Criteria,
			 @RequestParam(value="offset",required=false,defaultValue="-1")int offset,
			 @RequestParam(value="limit",required=false,defaultValue="-1")int limit)throws Exception
	{
		AllergyBean SearchAllergyData=AllergiesService.getReactionsOfAllergies(Criteria,offset,limit);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(SearchAllergyData);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved Allergy reaction search data", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * Method to check NKDA and NKA (No known Drug Allergies & No known Allergies)
	 * @param ChartId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/getNKDAandNKACheckDetails", method = RequestMethod.GET)
    @ResponseBody
	public EMRResponseBean getNKDAandNKACheckDetails( @RequestParam(value="chartId",required=false,defaultValue="-1")Integer chartId)throws Exception
	{
		List<PatientAllergies> SearchAllergyData=AllergiesService.getNKDAandNKACheckDetails(chartId);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(SearchAllergyData);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved NKDA and NKA check details", -1, request.getRemoteAddr(), -1, "chartId="+chartId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * Method to save new Allergies
	 * @param save
	 * @param insertParameters
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveNewAllergies", method = RequestMethod.POST)
    @ResponseBody
    public void saveNewAllergies(@RequestParam(value="save",required=false,defaultValue="-1")Integer save,
    		 @RequestParam(value="insertParameters",required=false,defaultValue="-1")String insertParameters)throws Exception
	{
		AllergiesService.saveNewAllergies(save,insertParameters);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.CREATE, 0, Log_Outcome.SUCCESS, "successful insertion of new allergies", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	/**
	 * Method to save allergy edit data
	 * @param editData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveAllergyEditData", method = RequestMethod.POST)
    @ResponseBody
    public void saveNewAllergies(
    		@RequestParam(value="editData",required=false,defaultValue="-1")String editData)throws Exception
	{
		AllergiesService.editAllergies(editData);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successful updation of allergy data", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	/**
	 * Method to delete allergies
	 * @param allergyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/deleteAllergies", method = RequestMethod.POST)
    @ResponseBody
    public void deleteAllergies(
    		 @RequestParam(value="allergyId",required=false,defaultValue="-1")String allergyId)throws Exception
	{
		AllergiesService.deleteAllergy(allergyId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.DELETE, 0, Log_Outcome.SUCCESS, "successful deletion of allergies", -1, request.getRemoteAddr(), -1, "allergyId="+allergyId, LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	/**
	 * Method to review Allergies
	 * @param reviewData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/reviewAllergies", method = RequestMethod.POST)
    @ResponseBody
    public void reviewAllergies(
    		 @RequestParam(value="reviewData",required=false,defaultValue="-1")String reviewData)throws Exception
	{
		AllergiesService.reviewAllergies(reviewData);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successful updation of review details", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	/**
	 * Method to check NKDA
	 * @param nkdaData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/NKDACheck", method = RequestMethod.POST)
    @ResponseBody
    public void NKDACheck(
    		 @RequestParam(value="nkdaData",required=false,defaultValue="-1")String nkdaData)throws Exception
	{
		AllergiesService.nkdaUpdate(nkdaData);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successful updation of nkda check details", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	/**
	 * Method to check NKA
	 * @param nkaData
	 * @throws Exception
	 */
	@RequestMapping(value ="/NKACheck", method = RequestMethod.POST)
    @ResponseBody
    public void NKACheck(
    		 @RequestParam(value="nkaData",required=false,defaultValue="-1")String nkaData)throws Exception
	{
		AllergiesService.nkaUpdate(nkaData);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successful updation of nka check details", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	/**
	 * Method to uncheck NKDA
	 * @param nkdaData
	 * @throws Exception
	 */
	@RequestMapping(value ="/NKDAUnCheck", method = RequestMethod.POST)
    @ResponseBody
    public void NKDAUnCheck(
    		 @RequestParam(value="nkdaData",required=false,defaultValue="-1")String nkdaData)throws Exception
	{
		AllergiesService.nkdauncheck(nkdaData);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successful updation of nkda uncheck details", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	/**
	 * Method to uncheck NKA
	 * @param nkaData
	 * @throws Exception
	 */
	@RequestMapping(value ="/NKAUnCheck", method = RequestMethod.POST)
    @ResponseBody
    public void NKAUnCheck(
    		 @RequestParam(value="nkaData",required=false,defaultValue="-1")String nkaData)throws Exception
	{
		AllergiesService.nkauncheck(nkaData);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successful updation of nka uncheck details", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	/**
	 * Method to retrieve Edited allergy data
	 * @param chartId
	 * @param patId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/RetrieveEditAllergData", method = RequestMethod.GET)
    @ResponseBody
    public EMRResponseBean RetrieveEditAllergData(
    		 @RequestParam(value="chartId",required=false,defaultValue="-1")String chartId,
    		@RequestParam(value="patId",required=false,defaultValue="-1")String patId)throws Exception
	{
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(AllergiesService.retrieveDataForEditAllerg(chartId, patId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved edit Allergy data", -1, request.getRemoteAddr(), Integer.parseInt(patId), "chartId="+chartId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * Method to retrieve In active Allergy data
	 * @param chartId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/RetrieveInActiveAllergData", method = RequestMethod.GET)
    @ResponseBody
    public EMRResponseBean RetrieveInActiveAllergData(
    		 @RequestParam(value="chartId",required=false,defaultValue="-1")String chartId,
    		 @RequestParam(value="statusList",required=false,defaultValue="-1")String statusList)throws Exception
	{
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(AllergiesService.retrieveInActiveAllerg(chartId,statusList));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "Successful retrieval of in active Allergy data", -1, request.getRemoteAddr(), -1, "chartId="+chartId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * Method to get last review details
	 * @param chartId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/LastReviewDetails", method = RequestMethod.GET)
    @ResponseBody
    public EMRResponseBean LastReviewDetails(
    		 @RequestParam(value="chartId",required=false,defaultValue="-1")String chartId)throws Exception
	{
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(AllergiesService.lastReviewDetails(chartId).toString());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "Successful retrieval of last review details", -1, request.getRemoteAddr(), -1, "chartId="+chartId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * Method to edit or add new allergies
	 * @param chartId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/SaveAllergies", method = RequestMethod.POST)
    @ResponseBody
    public void saveAllergies(@RequestParam(value="allergData",required=false,defaultValue="-1")String allergData)throws Exception
	{
		AllergiesService.saveAllergies(allergData);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.CREATE, 0, Log_Outcome.SUCCESS, "successful insertion of new allergies", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	@RequestMapping(value ="/PatientAllergiesHistory", method = RequestMethod.GET)
    @ResponseBody
    public EMRResponseBean PatientAllergiesHistory(
    		@RequestParam(value="chartId",required=false,defaultValue="-1")String chartId)throws Exception
	{
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(AllergiesService.patientAllergiesHistory(chartId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALLERGY, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved patient allergy history", -1, request.getRemoteAddr(), -1, "chartId="+chartId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
}