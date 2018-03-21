package com.glenwood.glaceemr.server.application.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.referral.ReferralBean;
import com.glenwood.glaceemr.server.application.services.referral.ReferralForm;
import com.glenwood.glaceemr.server.application.services.referral.ReferralListBean;
import com.glenwood.glaceemr.server.application.services.referral.ReferralService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

/**
 * Controller for referral module
 * @author software
 */
@RestController
@RequestMapping(value="/user/ReferralTabletSummary.Action")
public class ReferralController {
	
	@Autowired
	ReferralService referralService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	private Logger logger = Logger.getLogger(ReferralController.class);
	
	
	/**
	 * Get list of referrals
	 * @param chartId
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value = "/listReferral", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getReferralList(@RequestParam(value="chartId",required = false) Integer chartId) throws JSONException {
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Getting list of referrals:: chartId"+chartId);
			ReferralListBean result = referralService.getListOfReferrals(chartId);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Referrals viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "chartId="+chartId, LogUserType.USER_LOGIN, "", "");
			emrResponseBean.setData(result);
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Referrals viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "chartId="+chartId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;

	}	
	
	
	/**
	 * Get referral details
	 * @param referralId
	 * @param chartId
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value = "/GetReferral", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getReferral(@RequestParam(value="referralId",required = false) Integer referralId,
			   @RequestParam(value="chartId",required = false) Integer chartId,
			   @RequestParam(value="fromEdit",required = false,defaultValue="false") Boolean fromEdit) throws JSONException {
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Getting referral:: chartId"+chartId+" referralId"+referralId);
			ReferralBean result = referralService.getReferral(referralId,chartId,fromEdit);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Referral viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "refId="+referralId+"|chartId="+chartId, LogUserType.USER_LOGIN, "", "");
			emrResponseBean.setData(result);
		}catch(Exception e){			
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Referral viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "refId="+referralId+"|chartId="+chartId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;

	}

	
	/**
	 * Save referral details
	 * @throws JSONException
	 */
	@RequestMapping(value = "/SaveReferralForm", method = RequestMethod.POST)
	public EMRResponseBean saveReferral(@RequestBody ReferralForm formBean) throws JSONException {

		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Saving referral");
			String result = referralService.saveReferral(formBean);

			if(formBean != null && formBean.getEditReferral().equals("1"))
				auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.UPDATE, 1, Log_Outcome.SUCCESS, "Referral details updated", sessionMap.getUserID(), request.getRemoteAddr(), -1, "refId="+formBean.getRefId()+"|chartId="+formBean.getChartId(), LogUserType.USER_LOGIN, "", "");
			else
				auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Referral created", sessionMap.getUserID(), request.getRemoteAddr(), -1, "encounterId="+formBean.getEncounterId()+"|chartId="+formBean.getChartId(), LogUserType.USER_LOGIN, "", "");
			emrResponseBean.setData(result);
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.UPDATE, 1, Log_Outcome.EXCEPTION, "Referral details updated", sessionMap.getUserID(), request.getRemoteAddr(), -1, "refId="+formBean.getRefId()+"|chartId="+formBean.getChartId(), LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
}
