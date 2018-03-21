package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.print.GenericLetterFooter;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.chart.print.genericfooter.GenericFooterService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

 
@RestController
@RequestMapping(value="/user/LetterFooter.Action")
public class GenericFooterController {
	
	@Autowired
	GenericFooterService genericFooterService;

	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	private Logger logger = Logger.getLogger(GenericFooterController.class);
	
	/**
	 * Request to get the list of all generic footers
	 * 
	 */
	@RequestMapping(value = "/FetchGenericFooterList",method = RequestMethod.GET)
	public EMRResponseBean fetchGenericFooterList() throws Exception{
		logger.debug("Begin of request to get the list of all generic footers.");
		List<GenericLetterFooter> genericLetterFooterList = genericFooterService.getGenericFooterList();		
		logger.debug("End of request to get the list of all generic footers.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(genericLetterFooterList);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded generic footers", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	
	/**
	 * Request to save generic footer
	 * 
	 */
	@RequestMapping(value = "/saveGenericFooter",method = RequestMethod.POST)
	public EMRResponseBean saveGenericFooter(@RequestParam(value="footerName") String footerName,@RequestParam(value="footerVariant") Integer footerVariant,
			@RequestParam(value="footerText") String footerText,@RequestParam(value="footerStyle") String footerStyle,@RequestParam(value="isDefault") Boolean isDefault) throws Exception{
		logger.debug("Begin of request to Save generic footer.");
		
		GenericLetterFooter newGenericLetterFooter=new GenericLetterFooter();
		newGenericLetterFooter.setGenericLetterFooterName(footerName);
		newGenericLetterFooter.setGenericLetterFooterVariant(footerVariant);
		newGenericLetterFooter.setGenericLetterFooterCustom(footerText);
		newGenericLetterFooter.setGenericLetterFooterStyle(footerStyle);
		newGenericLetterFooter.setGenericLetterFooterIsDefault(isDefault);
		newGenericLetterFooter.setGenericLetterFooterIsActive(true);
		GenericLetterFooter genericLetterFooter = genericFooterService.saveGenericFooter(newGenericLetterFooter);
		logger.debug("End of request to Save generic footer.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(genericLetterFooter);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Successfully saved generic footer", sessionMap.getUserID(), request.getRemoteAddr(), -1, "footerName="+footerName, LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	
	/**
	 * Request to update generic footer
	 * 
	 */
	@RequestMapping(value = "/updateGenericFooter",method = RequestMethod.POST)
	public EMRResponseBean updateGenericFooter(@RequestParam(value="footerName") String footerName,@RequestParam(value="footerVariant") Integer footerVariant,
			@RequestParam(value="footerText") String footerText,@RequestParam(value="footerStyle") String footerStyle,
			@RequestParam(value="isActive") Boolean isActive,@RequestParam(value="isDefault") Boolean isDefault,
			@RequestParam(value="footerId") Integer footerId) throws Exception{
		logger.debug("Begin of request to Update generic footer.");
		
		GenericLetterFooter updateGenericLetterFooter=new GenericLetterFooter();
		updateGenericLetterFooter.setGenericLetterFooterId(footerId);
		updateGenericLetterFooter.setGenericLetterFooterName(footerName);
		updateGenericLetterFooter.setGenericLetterFooterVariant(footerVariant);
		updateGenericLetterFooter.setGenericLetterFooterCustom(footerText);
		updateGenericLetterFooter.setGenericLetterFooterStyle(footerStyle);
		updateGenericLetterFooter.setGenericLetterFooterIsDefault(isDefault);
		updateGenericLetterFooter.setGenericLetterFooterIsActive(isActive);
		GenericLetterFooter genericLetterFooter = genericFooterService.saveGenericFooter(updateGenericLetterFooter);
		logger.debug("End of request to Update generic footer.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(genericLetterFooter);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Successfully saved generic footer", sessionMap.getUserID(), request.getRemoteAddr(), -1, "footerId="+footerId, LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	
	/**
	 * Request to get generic footer based on id
	 * 
	 */
	@RequestMapping(value = "/FetchGenericFooter",method = RequestMethod.GET)
	public EMRResponseBean getGenericFooter(@RequestParam(value="footerId") Integer footerId) throws Exception{
		logger.debug("Begin of request to Fetch generic footer.");
		GenericLetterFooter genericLetterFooter = genericFooterService.getGenericFooter(footerId);
		logger.debug("End of request to Fetch generic footer.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(genericLetterFooter);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully fetch generic footer", sessionMap.getUserID(), request.getRemoteAddr(), -1, "footerId="+footerId, LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	
	
}
