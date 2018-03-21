package com.glenwood.glaceemr.server.application.controllers;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.cdaValidator.CdaValidatorService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.glenwood.glaceemr.server.utils.SessionMap;

/**
 * Controller for CDAValidation
 * @author Nithya
 *
 */
@RestController
@Transactional
@RequestMapping(value="/user/cdaValidation")

public class CdaValidatorController {
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired(required=true)
	CdaValidatorService cdaValidatorService;
	
	@Autowired
	EMRResponseBean emrResponseBean;

	@Autowired
	SessionMap sessionMap;

	@Autowired
	HttpServletRequest request;
	
	/**
	 * To validate the cda file.
	 * @param filePath  Full path of the cda file 
	 * @param fileId    	File id to be validate 
	 * @param isData     	Flag will decide whether heve to update the status or have to give the schema error message
	 * @return    			Validation Result / Exception message 
	 * @throws Exception
	 */
	@RequestMapping(value="/validation",method=RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean CDAValidation(@RequestParam (value="filePath", required=false)String filePath,@RequestParam (value="fileId", required=false)String fileId,@RequestParam (value="isData", required=false , defaultValue="f")String isData,@RequestParam(value="userId",required=true) String userId) throws Exception{
		JSONObject errorObject = cdaValidatorService.doValidation(filePath,fileId,isData);
		if(isData.equalsIgnoreCase("t")){
			emrResponseBean.setData(HUtil.Nz(errorObject.get("data"),""));
		}else{
			emrResponseBean.setData(HUtil.Nz(errorObject.get("updateResult"),""));
			if(errorObject.get("updateResult").toString().contains("successfully added")){
				auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CDAReconcile, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successfully update the CDA file schema Error Indication", sessionMap.getUserID(), request.getRemoteAddr(), -1, "userId="+userId, LogUserType.USER_LOGIN, "", "");
			}
		}
		
		return emrResponseBean;
		
	}
}
