package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.LetterHeaderEmp;
import com.glenwood.glaceemr.server.application.models.LetterHeaderPos;
import com.glenwood.glaceemr.server.application.models.print.GenericLetterHeader;
import com.glenwood.glaceemr.server.application.models.print.LetterHeaderContent;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditLogConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.chart.print.genericheader.LetterHeaderEmployeeBean;
import com.glenwood.glaceemr.server.application.services.chart.print.genericheader.LetterHeaderPosBean;
import com.glenwood.glaceemr.server.application.services.chart.print.genericheader.LetterHeaderService;
import com.glenwood.glaceemr.server.application.services.employee.EmployeeDataBean;
import com.glenwood.glaceemr.server.application.services.pos.PosDataBean;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;


@RestController
@RequestMapping(value="/user/LetterHeader.Action")
public class LetterHeaderController {
	
	@Autowired
	LetterHeaderService letterHeaderService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	private Logger logger = Logger.getLogger(LetterHeaderController.class);
	
	/**
	 * Request to get the list of all letter headers for configuration
	 * 
	 */
	@RequestMapping(value = "/FetchLetterHeader",method = RequestMethod.GET)
	public EMRResponseBean fetchLetterHeaderList() throws Exception{
		logger.debug("Begin of request to get the list of all letter headers.");
		List<GenericLetterHeader> letterHeaderList = letterHeaderService.getLetterHeaderList();
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded header list for configuration", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");		
		logger.debug("End of request to get the list of all letter headers.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(letterHeaderList);
		return respBean;
		
	}
	
	/**
	 * Request to get letter header content details based on header id for configuration
	 * 
	 */
	@RequestMapping(value = "/FetchLetterHeaderContent",method = RequestMethod.GET)
	public EMRResponseBean fetchPatientHeaderDetailsList(@RequestParam(value="headerId") Integer headerId) throws Exception{
		logger.debug("Begin of request to get letter header content details based on header id.");
		List<LetterHeaderContent> letterHeaderContent = letterHeaderService.getLetterHeaderContentList(headerId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded letter headers details based on header id", sessionMap.getUserID(), request.getRemoteAddr(), -1, "headerId="+headerId, LogUserType.USER_LOGIN, "", "");		
		logger.debug("End of request to get letter header content details based on header id.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(letterHeaderContent);
		return respBean;
		
	}
	
	/**
	 * Request to save Letter header
	 * 
	 */
	@RequestMapping(value = "/SaveLetterHeader",method = RequestMethod.POST)
	public EMRResponseBean saveLetterHeader(@RequestParam(value="headerName") String headerName,@RequestParam(value="headerType") Integer headerType,
			@RequestParam(value="leftFormat") Integer leftFormat,@RequestParam(value="leftFormatAddress") Integer leftFormatAddress,
			@RequestParam(value="addressFormat") Integer addressFormat,@RequestParam(value="isDefault") Boolean isDefault) throws Exception{
		logger.debug("Begin of request to Save Letter header.");
		
		GenericLetterHeader newLetterHeader=new GenericLetterHeader();
		newLetterHeader.setGenericLetterHeaderName(headerName);
		newLetterHeader.setGenericLetterHeaderType(headerType);
		newLetterHeader.setGenericLetterHeaderAddress(addressFormat);
		newLetterHeader.setGenericLetterHeaderLeft(leftFormat);
		newLetterHeader.setGenericLetterHeaderLeftAddress(leftFormatAddress);
		newLetterHeader.setGenericLetterHeaderIsDefault(isDefault);
		newLetterHeader.setGenericLetterHeaderIsActive(true);
		GenericLetterHeader letterHeader = letterHeaderService.saveLetterHeader(newLetterHeader);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Successfully saved Letter header", sessionMap.getUserID(), request.getRemoteAddr(), -1, "headerType="+headerType+"|headerName="+headerName, LogUserType.USER_LOGIN, "", "");
		logger.debug("End of request to Save Letter header.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(letterHeader);
		return respBean;
		
	}
	
	/**
	 * Request to save Letter header
	 * 
	 */
	@RequestMapping(value = "/UpdateLetterHeader",method = RequestMethod.POST)
	public EMRResponseBean updateLetterHeader(@RequestParam(value="headerName") String headerName,@RequestParam(value="headerType") Integer headerType,
			@RequestParam(value="leftFormat") Integer leftFormat,@RequestParam(value="leftFormatAddress") Integer leftFormatAddress,
			@RequestParam(value="addressFormat") Integer addressFormat,@RequestParam(value="headerId") Integer headerId,
			@RequestParam(value="isDefault") Boolean isDefault,@RequestParam(value="isActive") Boolean isActive) throws Exception{
		logger.debug("Begin of request to Save Letter header.");
		
		GenericLetterHeader newLetterHeader=new GenericLetterHeader();
		newLetterHeader.setGenericLetterHeaderId(headerId);
		newLetterHeader.setGenericLetterHeaderName(headerName);
		newLetterHeader.setGenericLetterHeaderType(headerType);
		newLetterHeader.setGenericLetterHeaderAddress(addressFormat);
		newLetterHeader.setGenericLetterHeaderLeft(leftFormat);
		newLetterHeader.setGenericLetterHeaderLeftAddress(leftFormatAddress);
		newLetterHeader.setGenericLetterHeaderIsDefault(isDefault);
		newLetterHeader.setGenericLetterHeaderIsActive(isActive);
		GenericLetterHeader letterHeader = letterHeaderService.saveLetterHeader(newLetterHeader);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Successfully saved Letter header", sessionMap.getUserID(), request.getRemoteAddr(), -1, "headerId="+headerId, LogUserType.USER_LOGIN, "", "");
		logger.debug("End of request to Save Letter header.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(letterHeader);
		return respBean;
		
	}
	
	/**
	 * Request to delete letter header details
	 * 
	 */
	@RequestMapping(value = "/deleteLetterHeaderContent",method = RequestMethod.POST)
	public EMRResponseBean deleteLetterHeaderDetails(@RequestParam(value="headerId") Integer headerId) throws Exception{
		logger.debug("Begin of request to delete letter header details.");
		List<LetterHeaderContent> letterHeaderContent = letterHeaderService.getLetterHeaderContentList(headerId);
		if(letterHeaderContent!=null && !letterHeaderContent.isEmpty()){
			letterHeaderService.deleteLetterHeaderContent(letterHeaderContent);
		}
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.DELETE, 1, Log_Outcome.SUCCESS, "Successfully deleted letter header details", sessionMap.getUserID(), request.getRemoteAddr(), -1, "headerId="+headerId, LogUserType.USER_LOGIN, "", "");
		logger.debug("End of request to delete letter header details.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData("success");
		return respBean;
	}
	
	/**
	 * Request to save letter header details
	 * 
	 */
	@RequestMapping(value = "/saveLetterHeaderContent",method = RequestMethod.POST)
	public EMRResponseBean saveLetterHeaderDetails(@RequestParam(value="headerId") Integer headerId,@RequestParam(value="variantId") Integer variantId,
			@RequestParam(value="flagId") Integer flagId,@RequestParam(value="customText") String customText,@RequestParam(value="style") String style) throws Exception{
		logger.debug("Begin of request to save letter header details.");
		LetterHeaderContent newLetterHeaderContent= new LetterHeaderContent();
		newLetterHeaderContent.setLetterHeaderContentStyleMapId(headerId);
		newLetterHeaderContent.setLetterHeaderContentVariant(variantId);
		newLetterHeaderContent.setLetterHeaderContentFlag(flagId);
		newLetterHeaderContent.setLetterHeaderContentCustom(customText);
		newLetterHeaderContent.setLetterHeaderContentStyle(style);
		letterHeaderService.saveLetterHeaderContent(newLetterHeaderContent);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Successfully saved letter header details", sessionMap.getUserID(), request.getRemoteAddr(), -1, "headerId="+headerId, LogUserType.USER_LOGIN, "", "");
		logger.debug("End of request to save letter header details.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData("success");
		return respBean;
	}
	
	/**
	 * To get employee list
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/FetchEmployeeList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getEmployeeList() throws Exception {
	
		logger.debug("Begin of request to get the list of employees details.");
		List<EmployeeDataBean> bean = letterHeaderService.getEmployeeDetails();
		logger.debug("End of request to get the list of employees details.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(bean);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully viewed employee list in configuration", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return respBean;

	}
	/**
	 * To get POS list
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/FetchPOSList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPOSList() throws Exception {
	
		logger.debug("Begin of request to get the list of POS.");
		List<PosDataBean> bean = letterHeaderService.getPOSDetails();
		logger.debug("End of request to get the list of POS.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(bean);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully viewed POS list in configuration", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return respBean;

	}
	
	@RequestMapping(value = "/FetchLetterHeaderPOSList",method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean fetchLetterHeaderPOSList(@RequestParam(value="headerId") Integer headerId,
			@RequestParam(value="variantId") Integer variantId) throws Exception{
		logger.debug("Begin of request to fetch letter header pos details.");
		
		List<PosDataBean> posList = letterHeaderService.getPOSDetails();
		List<LetterHeaderPos> headerPosList = letterHeaderService.fetchLetterHeaderPOSList(headerId,variantId);
		
		LetterHeaderPosBean bean = new LetterHeaderPosBean();
		bean.setPosList(posList);
		bean.setSavedPosList(headerPosList);
		
		logger.debug("End of request to fetch letter header pos details.");
		
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(bean);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully viewed saved employee list in configuration", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return respBean;
	}
	
	@RequestMapping(value = "/FetchLetterHeaderEmpList",method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean fetchLetterHeaderEmpList(@RequestParam(value="headerId") Integer headerId,
			@RequestParam(value="variantId") Integer variantId) throws Exception{
		logger.debug("Begin of request to fetch letter header employee details.");
		
		List<EmployeeDataBean> empList = letterHeaderService.getEmployeeDetails();
		List<LetterHeaderEmp> headerEmpList = letterHeaderService.getLetterHeaderEmpList(headerId,variantId);
		
		LetterHeaderEmployeeBean bean = new LetterHeaderEmployeeBean();
		bean.setEmpList(empList);
		bean.setSavedEmpList(headerEmpList);
		
		logger.debug("End of request to fetch letter header employee details.");
		
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(bean);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully viewed saved POS list in configuration", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return respBean;
	}
	
	@RequestMapping(value = "/SaveLetterHeaderPOSList",method = RequestMethod.POST)
	public EMRResponseBean saveLetterHeaderPOSList(@RequestParam(value="orderValue", defaultValue="") String orderValue,
			@RequestParam(value="value", defaultValue="") String value,
			@RequestParam(value="variantId", defaultValue="-1") Integer variantId,
			@RequestParam(value="headerId", defaultValue="-1") Integer headerId) throws Exception{
		logger.debug("Begin of request to fetch letter header pos details.");
		String[] valueArr = value.split("~");
		String[] orderArr = orderValue.split("~");
		List<LetterHeaderPos> letterHeaderContent = letterHeaderService.getLetterHeaderPOSList(headerId, variantId);
		if(letterHeaderContent!=null && !letterHeaderContent.isEmpty()){
			letterHeaderService.deleteLetterHeaderPos(letterHeaderContent);
		}
		for(int i=0; i<valueArr.length; i++){
			LetterHeaderPos letterHeaderPos = new LetterHeaderPos();
			letterHeaderPos.setLetterHeaderPosMapId(headerId);
			letterHeaderPos.setLetterHeaderPosPosid(Integer.parseInt(valueArr[i]));
			letterHeaderPos.setLetterHeaderPosOrder(Integer.parseInt(orderArr[i]));
			letterHeaderPos.setLetterHeaderPosVariant(variantId);
			letterHeaderService.saveLetterHeaderPOS(letterHeaderPos);
		}
		
		logger.debug("End of request to save letter header pos details.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData("Success");
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.CREATEORUPDATE, 1, Log_Outcome.SUCCESS, "Successfully saved employee list in configuration", sessionMap.getUserID(), request.getRemoteAddr(), -1, "headerId="+headerId, LogUserType.USER_LOGIN, "", "");
		return respBean;
	}
	
	@RequestMapping(value = "/SaveLetterHeaderEmpList",method = RequestMethod.POST)
	public EMRResponseBean saveLetterHeaderEmpList(@RequestParam(value="orderValue", defaultValue="") String orderValue,
			@RequestParam(value="value", defaultValue="") String value,
			@RequestParam(value="headerId", defaultValue="") Integer headerId,
			@RequestParam(value="variantId", defaultValue="-1") Integer variantId) throws Exception{
		logger.debug("Begin of request to fetch letter header employee details.");
		String[] valueArr = value.split("~");
		String[] orderArr = orderValue.split("~");
		List<LetterHeaderEmp> letterHeaderContent = letterHeaderService.getLetterHeaderEmpList(headerId,variantId);
		if(letterHeaderContent!=null && !letterHeaderContent.isEmpty()){
			letterHeaderService.deleteLetterHeaderEmp(letterHeaderContent);
		}
		for(int i=0; i<valueArr.length; i++){
			
			LetterHeaderEmp letterHeaderEmp = new LetterHeaderEmp();
			letterHeaderEmp.setLetterHeaderEmpMapId(headerId);
			letterHeaderEmp.setLetterHeaderEmpEmpid(Integer.parseInt(valueArr[i]));
			letterHeaderEmp.setLetterHeaderEmpOrder(Integer.parseInt(orderArr[i]));
			letterHeaderEmp.setLetterHeaderEmpVariant(variantId);
			letterHeaderService.saveLetterHeaderEmp(letterHeaderEmp);
		}
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData("Success");
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.CREATEORUPDATE, 1, Log_Outcome.SUCCESS, "Successfully saved POS list in configuration", sessionMap.getUserID(), request.getRemoteAddr(), -1, "headerId="+headerId, LogUserType.USER_LOGIN, "", "");
		return respBean;
	}	
}