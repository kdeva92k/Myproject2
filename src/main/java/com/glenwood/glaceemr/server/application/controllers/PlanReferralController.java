package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.Referral;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.referral.ReferralBean;
import com.glenwood.glaceemr.server.application.services.referral.ReferralService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

/**
 * Controller for plan referral. It has one main entity, <b>H413 </b>.
 * @author Chandrahas
 * <br> <br>
 * <b>Example requests:</b>
 * <br> <br>
 * <ul>
 * <li><b>Get Referral list</b><i> ReferralSummary.Action/listReferral?encounterId=6701&chartId=1196</i><br>
 * Gets the list of categories based on the category Id.</li><br>
 * <li><b>Get Referral</b><i> ReferralSummary.Action/GetReferral?referralId=12173</i><br>
 * Highlights one or more alerts and updates the modified user and date.</li><br>
 * <li><b>Save Referral</b><i> ReferralSummary.Action/SaveReferral?referralId=12173&reason=knee pain&notes=not able to walk</i><br>
 * Un Highlights one or more alerts and updates the modified user and date.</li><br>
 * <li><b>Cancel Referral</b><i> ReferralSummary.Action/CancelReferral?referralId=12173&loginId=49</i><br>
 * Mark one or more alerts to read and updates the modified user and date.</li><br>
 * </ul>
 */

@RestController
@RequestMapping(value="/user/ReferralSummary.Action")
public class PlanReferralController {
	
	@Autowired
	ReferralService referralService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	private Logger logger = Logger.getLogger(PlanReferralController.class);
	
		
	/**
	 * To get list of referrals
	 * @param encounterId
	 * @param chartId
	 * @return list of referrals
	 * @throws JSONException
	 */
	@RequestMapping(value = "/listReferral", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getReferralList(@RequestParam(value="encounterId",required = false) Integer encounterId,
			   @RequestParam(value="chartId",required = false) Integer chartId,
			   @RequestParam(value="dxCode",required = false,defaultValue="") String dx) throws JSONException {
	
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Getting referral list::encounterId"+encounterId+" chartId"+chartId+" dxCode"+dx);
			List<Referral> result = referralService.getListOfReferralsPlan(encounterId,chartId,dx);
			emrResponseBean.setData(result);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Referrals viewed in plan", sessionMap.getUserID(), request.getRemoteAddr(), -1, "chartId="+chartId+"|encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Referrals viewed in plan", sessionMap.getUserID(), request.getRemoteAddr(), -1, "chartId="+chartId+"|encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
	
	
	/**
	 * To get referral and diagnosis
	 * @param referralId
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value = "/GetReferralWithDiagnosis", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getReferral(@RequestParam(value="referralId",required = false,defaultValue="-1") Integer referralId,
			@RequestParam(value="chartId",required = false,defaultValue="-1") Integer chartId,
			@RequestParam(value="encounterId",required = false,defaultValue="-1") Integer encounterId)
			    throws JSONException {
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Getting referral::referralId"+referralId);
			ReferralBean result = referralService.getReferral(referralId, chartId, encounterId);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Referral viewed in plan", sessionMap.getUserID(), request.getRemoteAddr(), -1, "refId="+referralId+"|chartId="+chartId+"|encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
			emrResponseBean.setData(result);
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Referral viewed in plan", sessionMap.getUserID(), request.getRemoteAddr(), -1, "refId="+referralId+"|chartId="+chartId+"|encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;

	}
	
	/**
	 * To get referral based on referral id
	 * @param referralId
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value = "/GetReferral", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getReferral(@RequestParam(value="referralId",required = false,defaultValue="-1") Integer referralId)
			    throws JSONException {
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Getting referral::referralId"+referralId);
			Referral result = referralService.getReferralPlan(referralId);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Referral viewed in plan", sessionMap.getUserID(), request.getRemoteAddr(), -1, "refId="+referralId, LogUserType.USER_LOGIN, "", "");
			emrResponseBean.setData(result);
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.VIEW, 1, Log_Outcome.EXCEPTION, "Referral viewed in plan", sessionMap.getUserID(), request.getRemoteAddr(), -1, "refId="+referralId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;

	}
	
	/**
	 * To save the referral
	 * @param referralId
	 * @param reason
	 * @param notes
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value = "/SaveReferral", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean saveReferral(
			  @RequestParam(value="referralId",required = false) Integer referralId,
			  @RequestParam(value="reason",required = false) String reason,
			  @RequestParam(value="notes",required = false) String notes,
			  @RequestParam(value="criticalstatus",required = false) Integer criticalstatus,
			  @RequestParam(value="diagnosis",required = false, defaultValue="") String diagnosis,
			  @RequestParam(value="exprespdate",required = false, defaultValue="") String exprespdate) throws JSONException {
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Saving referral::referralId"+referralId+" reason"+reason+" notes"+notes+"criticalstatus"+criticalstatus);
			referralService.saveReferralPlan(referralId,reason,notes,diagnosis,criticalstatus,exprespdate);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.UPDATE, 1, Log_Outcome.SUCCESS, "Referral details updated in plan", sessionMap.getUserID(), request.getRemoteAddr(), -1, "refId="+referralId, LogUserType.USER_LOGIN, "", "");
			emrResponseBean.setData("success");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.UPDATE, 1, Log_Outcome.EXCEPTION, "Referral details updated in plan", sessionMap.getUserID(), request.getRemoteAddr(), -1, "refId="+referralId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;
	}
		
	/** 
	 * To update the referral status to cancelled
	 * @param loginID
	 * @param refId
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value = "/CancelReferral", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean cancelReferral(@RequestParam(value="loginId",required = false, defaultValue = "-1") Integer loginID,
			   @RequestParam(value="referralId",required = false, defaultValue = "-1") Integer referralId) throws JSONException {
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Cancelling referral::referralId"+referralId+" loginId"+loginID);
			referralService.cancelReferral(loginID,referralId);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.UPDATE, 1, Log_Outcome.SUCCESS, "Referral cancelled in plan", sessionMap.getUserID(), request.getRemoteAddr(), -1, "refId="+referralId, LogUserType.USER_LOGIN, "", "");
			emrResponseBean.setData("success");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.REFERRAL, LogActionType.UPDATE, 1, Log_Outcome.EXCEPTION, "Referral cancelled in plan", sessionMap.getUserID(), request.getRemoteAddr(), -1, "refId="+referralId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;

	}
	
}
