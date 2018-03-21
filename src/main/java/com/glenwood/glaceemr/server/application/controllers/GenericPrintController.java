package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.models.LeafLibrary;
import com.glenwood.glaceemr.server.application.models.print.GenericPrintStyle;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.chart.print.CustomGenericBean;
import com.glenwood.glaceemr.server.application.services.chart.print.GenericPrintService;
import com.glenwood.glaceemr.server.application.services.chart.print.GenericPrintBean;
import com.glenwood.glaceemr.server.application.services.chart.print.GenericPrintTemplateBean;
import com.glenwood.glaceemr.server.application.services.chart.print.PrintDetailsDataBean;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

@RestController
@RequestMapping(value="/user/GenericPrintStyle.Action")
public class GenericPrintController {

	@Autowired
	GenericPrintService genericPrintService;
	
	@Autowired
	ObjectMapper objectMapper; 
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	private Logger logger = Logger.getLogger(GenericPrintController.class);
	

	/**
	 * Request to get the list of all generic print styles for configuration
	 * 
	 */
	@RequestMapping(value = "/FetchGenericPrintStyleList",method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean fetchgenericPrintStyleList() throws Exception{
		logger.debug("Begin of request to get the list of all generic print styles.");
		List<GenericPrintStyle> genericPrintStyleList = genericPrintService.getGenericPrintStyleList();
		logger.debug("End of request to get the list of all generic print styles.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(genericPrintStyleList);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded header list for configuration", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	

	/**
	 * Request to get generic print styles based on id for configuration
	 * 
	 */
	@RequestMapping(value = "/FetchGenericPrintStyle",method = RequestMethod.GET)
	public EMRResponseBean fetchgenericPrintStyle(@RequestParam(value="styleId") Integer styleId) throws Exception{
		logger.debug("Begin of request to get the list of all generic print styles.");
		GenericPrintStyle genericPrintStyleList = genericPrintService.getGenericPrintStyle(styleId);
		logger.debug("End of request to get the list of all generic print styles.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(genericPrintStyleList);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded header list for configuration", sessionMap.getUserID(), request.getRemoteAddr(), -1, "styleId="+styleId, LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	
	/**
	 * Request to save generic footer
	 * 
	 */
	@RequestMapping(value = "/saveGenericPrintStyle",method = RequestMethod.POST)
	public EMRResponseBean saveGenericPrintStyle(@RequestParam(value="styleName") String styleName,@RequestParam(value="letterHeaderId") Integer letterHeaderId,
			@RequestParam(value="patientHeaderId") Integer patientHeaderId,@RequestParam(value="footerId") Integer footerId,
			@RequestParam(value="isDefault") Boolean isDefault,@RequestParam(value="signId", defaultValue="-1", required=false) Integer signId) throws Exception{
		
		logger.debug("Begin of request to Save generic print style.");
		
		GenericPrintStyle newGenericPrintStyle=new GenericPrintStyle();
		newGenericPrintStyle.setGenericPrintStyleName(styleName);
		newGenericPrintStyle.setGenericPrintStyleHeaderId(letterHeaderId);
		newGenericPrintStyle.setGenericPrintStylePatientHeaderId(patientHeaderId);
		newGenericPrintStyle.setGenericPrintStyleFooterId(footerId);
		newGenericPrintStyle.setGenericPrintStyleSignId(signId);
		newGenericPrintStyle.setGenericPrintStyleIsDefault(isDefault);
		newGenericPrintStyle.setGenericPrintStyleIsActive(true);
		
		GenericPrintStyle genericPrintStyle = genericPrintService.saveGenericPrintStyle(newGenericPrintStyle);			
		logger.debug("End of request to Save generic print style.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(genericPrintStyle);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Successfully saved generic print style", sessionMap.getUserID(), request.getRemoteAddr(), -1, "styleName="+styleName+"|headerId="+letterHeaderId+"|patientHeaderId="+patientHeaderId+"|footerId="+footerId, LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	
	/**
	 * Request to save generic footer
	 * 
	 */
	@RequestMapping(value = "/updateGenericPrintStyle",method = RequestMethod.POST)
	public EMRResponseBean updateGenericPrintStyle(@RequestParam(value="styleName") String styleName,@RequestParam(value="letterHeaderId") Integer letterHeaderId,
			@RequestParam(value="patientHeaderId") Integer patientHeaderId,@RequestParam(value="footerId") Integer footerId,@RequestParam(value="styleId") Integer styleId,
			@RequestParam(value="isDefault") Boolean isDefault,@RequestParam(value="isActive") Boolean isActive
			,@RequestParam(value="signId", defaultValue="-1", required=false) Integer signId) throws Exception{
		
		logger.debug("Begin of request to Update generic print style.");
		
		GenericPrintStyle updateGenericPrintStyle=new GenericPrintStyle();
		updateGenericPrintStyle.setGenericPrintStyleId(styleId);
		updateGenericPrintStyle.setGenericPrintStyleName(styleName);
		updateGenericPrintStyle.setGenericPrintStyleHeaderId(letterHeaderId);
		updateGenericPrintStyle.setGenericPrintStylePatientHeaderId(patientHeaderId);
		updateGenericPrintStyle.setGenericPrintStyleFooterId(footerId);
		updateGenericPrintStyle.setGenericPrintStyleSignId(signId);
		updateGenericPrintStyle.setGenericPrintStyleIsDefault(isDefault);
		updateGenericPrintStyle.setGenericPrintStyleIsActive(isActive);
		
		GenericPrintStyle genericPrintStyle = genericPrintService.saveGenericPrintStyle(updateGenericPrintStyle);
		logger.debug("End of request to Update generic print style.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(genericPrintStyle);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Successfully saved generic print style", sessionMap.getUserID(), request.getRemoteAddr(), -1, "styleId="+styleId+"|styleName="+styleName+"|headerId="+letterHeaderId+"|patientHeaderId="+patientHeaderId+"|footerId="+footerId, LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	
	/**
	 * Request to get generic print styles based on id for configuration
	 * 
	 */
	@RequestMapping(value = "/FetchGenericPrintPDF",method = RequestMethod.GET)
	public void fetchgenericPrintPDF(@RequestParam(value="styleId") Integer styleId) throws Exception{
		logger.debug("Begin of request to get the list of all generic print styles.");
		 genericPrintService.generatePDFPreview(styleId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded header list for configuration", sessionMap.getUserID(), request.getRemoteAddr(), -1, "styleId="+styleId, LogUserType.USER_LOGIN, "", "");
		logger.debug("End of request to get the list of all generic print styles.");
		
		
	}
	
	/**
	 * Request to get generic print styles based on id for configuration
	 * 
	 */
	@RequestMapping(value = "/FetchGenericPrintPDFData",method = RequestMethod.GET)
	public void fetchgenericPrintPDFData(@RequestParam(value="styleId") Integer styleId,
			@RequestParam(value="patientId") Integer patientId/*,@RequestParam(value="printDetails")String printDetails*/) throws Exception{
		logger.debug("Begin of request to get the list of all generic print styles.");
		//http://localhost:7080/glaceemr_backend/api/GenericPrintStyle.Action/GetPrint?patientId=7514&encounterId=7328
		//,objectMapper.readValue(printDetails, PrintDetailsDataBean.class)
		 genericPrintService.generatePDFPreview(styleId,patientId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded header list for configuration", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "styleId="+styleId, LogUserType.USER_LOGIN, "", "");
		logger.debug("End of request to get the list of all generic print styles.");
		
		
	}

	/**
	 * To get complete details of patient for print
	 * @param encounterId
	 * @param patientId
	 * @return 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/GetPrint", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getCompleteDetails(@RequestParam(value="patientId",required = false) Integer patientId,
			@RequestParam(value="encounterId",required = false) Integer encounterId) throws Exception {
	
		logger.debug("Begin of request to get the list of employees and patient details.");
		GenericPrintBean bean = genericPrintService.getCompleteDetails(patientId, encounterId);
		logger.debug("End of request to get the list of employees and patient details.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(bean);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded report", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		return respBean;

	}
	
	@RequestMapping(value = "/FetchGenericPrintHeaderData",method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean fetchGenericPrintHeader(@RequestParam(value="styleId") Integer styleId,
			@RequestParam(value="patientId", defaultValue="-1") Integer patientId,
			@RequestParam(value="encounterId", defaultValue="-1") Integer encounterId,
			@RequestParam(value="sharedFolderPath", defaultValue="", required=false) String sharedFolderPath) throws Exception{
		logger.debug("Begin of request to get generic print header data.");
    	String headerHTML = genericPrintService.getHeaderHTML(styleId, patientId, encounterId, sharedFolderPath);
		logger.debug("End of request to get generic print header data.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(headerHTML);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded header data in report", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	
	@RequestMapping(value = "/FetchGenericPrintPatientHeaderData",method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean fetchGenericPrintPatientHeader(@RequestParam(value="styleId") Integer styleId,
			@RequestParam(value="patientId", defaultValue="-1") Integer patientId,
			@RequestParam(value="encounterId", defaultValue="-1") Integer encounterId) throws Exception{
		logger.debug("Begin of request to get generic print patient header data.");
    	String headerHTML = genericPrintService.getPatientHeaderHTML(styleId, patientId, encounterId);
		logger.debug("End of request to get generic print patient header data.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(headerHTML);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded patient header data in report", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	
	@RequestMapping(value = "/FetchGenericPrintFooterData",method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean fetchGenericPrintFooter(@RequestParam(value="styleId") Integer styleId) throws Exception{
		logger.debug("Begin of request to get generic print footer data.");
    	String headerHTML = genericPrintService.getFooterHTML(styleId);
		logger.debug("End of request to get generic print footer data.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(headerHTML);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded footer data in report", sessionMap.getUserID(), request.getRemoteAddr(), -1, "styleId="+styleId, LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	
	@RequestMapping(value = "/FetchGenericPrintLeftHeaderData",method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean fetchGenericPrintLeftData(@RequestParam(value="styleId") Integer styleId) throws Exception{
		logger.debug("Begin of request to get generic print left data.");
    	String headerHTML = genericPrintService.getLeftHeaderHTML(styleId);
		logger.debug("End of request to get generic print left data.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(headerHTML);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded left header data in report", sessionMap.getUserID(), request.getRemoteAddr(), -1, "styleId="+styleId, LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	
	@RequestMapping(value = "/FetchGenericPrintData",method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean fetchGenericPrint(@RequestParam(value="styleId") Integer styleId,
			@RequestParam(value="patientId", defaultValue="-1") Integer patientId,
			@RequestParam(value="encounterId", defaultValue="-1") Integer encounterId,
			@RequestParam(value="sharedFolderPath", defaultValue="", required=false) String sharedFolderPath) throws Exception{
		logger.debug("Begin of request to get generic print header data.");
    	CustomGenericBean bean = genericPrintService.getCustomeGenericData(styleId, patientId, encounterId, sharedFolderPath);
		logger.debug("End of request to get generic print header data.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(bean);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded header list for configuration", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "encounterId="+encounterId+"|styleId="+styleId, LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}

	/**
	 * Request to get template list
	 * 
	 */
	@RequestMapping(value = "/FetchTemplatesList",method = RequestMethod.GET)
	public EMRResponseBean fetchTemplatesList(@RequestParam(value="styleId", defaultValue="-1") Integer styleId) throws Exception{
		logger.debug("Begin of request to get template list.");
		
		List<LeafLibrary> templateList=genericPrintService.getTemplatesList();
		List<LeafLibrary> styleTemplateList=genericPrintService.getStyleTemplatesList(styleId);
		
		GenericPrintTemplateBean bean=new GenericPrintTemplateBean();
		bean.setTemplateList(templateList);
		bean.setStyleTemplateList(styleTemplateList);
		
		logger.debug("End of request to get template list.");
		EMRResponseBean respBean= new EMRResponseBean();
		respBean.setData(bean);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Successfully loaded templates list for configuration", sessionMap.getUserID(), request.getRemoteAddr(), -1, "styleId="+styleId, LogUserType.USER_LOGIN, "", "");
		return respBean;
		
	}
	
	/**
	 * Request to save style for template
	 * 
	 */
	@RequestMapping(value = "/UpdateTemplatesList",method = RequestMethod.POST)
	public void updateTemplatesList(@RequestParam(value="styleId") Integer styleId,
			@RequestParam(value="value") String value) throws Exception{
		
		logger.debug("Begin of request to Update style for template.");
		
		List<LeafLibrary> leafLibraryStyle = genericPrintService.getLeafLibraryStyle(styleId);
		for(int i=0; i<leafLibraryStyle.size(); i++){
			LeafLibrary leafLibrary = leafLibraryStyle.get(i);
			leafLibrary.setLeafLibraryPrintStyleId(null);
			genericPrintService.saveLeafLibrary(leafLibrary);
		}
		String[] valueArr=value.split("~");
		for(int i=0; i<valueArr.length; i++){
			int templateId=-1;
			try{
				templateId= Integer.parseInt(valueArr[i]);
			}catch(Exception e){
				templateId=-1;
			}
			
			LeafLibrary leafLibrary=genericPrintService.getLeafLibrary(templateId);			
			leafLibrary.setLeafLibraryPrintStyleId(styleId);
			genericPrintService.saveLeafLibrary(leafLibrary);
		}		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.PRINTINGANDREPORTING, LogActionType.CREATE, 1, Log_Outcome.SUCCESS, "Successfully saved style for template", sessionMap.getUserID(), request.getRemoteAddr(), -1, "styleId="+styleId, LogUserType.USER_LOGIN, "", "");
		logger.debug("End of request to Update style for template.");
	}
	
	/**
	 * Request to generate PDF for template print - Creates a temp file in shared folder
	 * @param PrintDetailsDataBean - Contains details required for printing and print data
	 */
	@RequestMapping(value = "/FetchGenericPrintPDFData",method = RequestMethod.POST, consumes = "application/json")
	public void fetchgenericPrintPDFData(@RequestBody PrintDetailsDataBean dataBean) throws Exception{
		try{
			logger.debug("Begin of request to Generate PDF file.");

			genericPrintService.generatePDFPrint(dataBean.getStyleId(),dataBean.getPatientId(),dataBean);

			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.PRINTINGANDREPORTING,LogActionType.CREATE,1,Log_Outcome.SUCCESS,"Successfully generated PDF file.",sessionMap.getUserID(), request.getRemoteAddr(),-1,"",LogUserType.USER_LOGIN,"","");		
			logger.debug("End of request to Generate PDF file.");
		}catch(Exception e){
			e.printStackTrace();
		}


	}
}

