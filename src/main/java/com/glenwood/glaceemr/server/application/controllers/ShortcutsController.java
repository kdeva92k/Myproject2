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

import com.glenwood.glaceemr.server.application.models.SoapElementDatalist;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.shortcuts.ShortcutsService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

/**
 * Shortcuts controller
 * @author software
 *
 */

@RestController
@RequestMapping(value="/user/Shortcuts.Action")
public class ShortcutsController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ShortcutsService shortcutsService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	private Logger logger = Logger.getLogger(ShortcutsController.class);
	
	/**
	 * Method to add a new shortcut
	 * @param tabId
	 * @param elementId
	 * @param data
	 * @return
	 * @throws JSONException
	 */
	
	@RequestMapping(value = "/AddShortcut", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean addShort(@RequestParam(value="tabId",required = false, defaultValue="-1") Integer tabId,
			   @RequestParam(value="elementId",required = false, defaultValue="") String elementId,
			   @RequestParam(value="data",required = false, defaultValue="") String data) throws JSONException {
	
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Begin of request to Add shortcut @@tabId"+tabId+" elementId"+elementId+" data"+data);

			List<SoapElementDatalist> shortcutList = shortcutsService.addShortcut(tabId, elementId, data);
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SHORTCUTS, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Quick shorcut added", sessionMap.getUserID(), request.getRemoteAddr(), -1, "tabId="+tabId+"|elementId="+elementId, LogUserType.USER_LOGIN, "", "");
			logger.debug("End of request to Add shortcut");
			emrResponseBean.setData(shortcutList);
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SHORTCUTS, LogActionType.CREATE, 1, Log_Outcome.EXCEPTION, "Quick shorcut added", sessionMap.getUserID(), request.getRemoteAddr(), -1, "tabId="+tabId+"|elementId="+elementId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;

	}
	
	/**
	 * Method to delete shortcut
	 * @param shortcutId
	 * @param tabId
	 * @param elementId
	 * @return
	 * @throws JSONException
	 */
	
	@RequestMapping(value = "/deleteShortcut", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean deleteShort(@RequestParam(value="shortcutId",required = false, defaultValue="-1") Integer shortcutId,
			   @RequestParam(value="tabId",required = false, defaultValue="-1") Integer tabId,
			   @RequestParam(value="elementId",required = false, defaultValue="") String elementId)
			   throws JSONException {
		
		EMRResponseBean emrResponseBean= new EMRResponseBean();
		try{
			logger.debug("Begin of request to Delete shortcut @@shortcutId:"+shortcutId);
			List<SoapElementDatalist> list = shortcutsService.fetchShortcuts(shortcutId);
			if(list != null && !list.isEmpty())
				shortcutsService.deleteShortcut(list);

			List<SoapElementDatalist> shortcutList = shortcutsService.fetchShortcuts(tabId, elementId);
			emrResponseBean.setData(shortcutList);
			logger.debug("End of request to Delete shortcut");
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SHORTCUTS, LogActionType.DELETE, 1, Log_Outcome.SUCCESS, "Quick shorcut deleted", sessionMap.getUserID(), request.getRemoteAddr(), -1, "shortcutId="+shortcutId+"|tabId="+tabId+"|elementId="+elementId, LogUserType.USER_LOGIN, "", "");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SHORTCUTS, LogActionType.DELETE, 1, Log_Outcome.EXCEPTION, "Quick shorcut deleted", sessionMap.getUserID(), request.getRemoteAddr(), -1, "shortcutId="+shortcutId+"|tabId="+tabId+"|elementId="+elementId, LogUserType.USER_LOGIN, "", "");
		}
		return emrResponseBean;

	}
		
}
