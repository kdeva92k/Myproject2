package com.glenwood.glaceemr.server.application.controllers;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.Bean.EPMeasureBean;
import com.glenwood.glaceemr.server.application.Bean.GeneratePDFDetails;
import com.glenwood.glaceemr.server.application.Bean.MIPSPatientInformation;
import com.glenwood.glaceemr.server.application.Bean.MIPSPerformanceBean;
import com.glenwood.glaceemr.server.application.Bean.MIPSResponse;
import com.glenwood.glaceemr.server.application.Bean.MUDashboardBean;
import com.glenwood.glaceemr.server.application.Bean.MacraProviderQDM;
import com.glenwood.glaceemr.server.application.Bean.MeasureStatus;
import com.glenwood.glaceemr.server.application.Bean.QPPDetails;
import com.glenwood.glaceemr.server.application.Bean.SharedFolderBean;
import com.glenwood.glaceemr.server.application.Bean.getPatientBean;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.HttpConnectionUtils;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.Request;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.Response;
import com.glenwood.glaceemr.server.application.Bean.macra.ecqm.EMeasureUtils;
import com.glenwood.glaceemr.server.application.Bean.mailer.GlaceMailer;
import com.glenwood.glaceemr.server.application.models.MacraProviderConfiguration;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.chart.MIPS.ConfigurationDetails;
import com.glenwood.glaceemr.server.application.services.chart.MIPS.MeasureCalculationService;
import com.glenwood.glaceemr.server.application.services.chart.MIPS.QPPConfigurationService;
import com.glenwood.glaceemr.server.application.services.chart.admission.AdmissionBean;
import com.glenwood.glaceemr.server.datasource.TennantContextHolder;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

@RestController
@Transactional
@RequestMapping(value = "/user/QPPPerformance")
public class QPPPerformanceController {
	
	@Autowired
	MeasureCalculationService measureService;
	
	@Autowired
	QPPConfigurationService providerConfService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	SharedFolderBean sharedFolderBean;
	
	@RequestMapping(value = "/insertPatientEntries", method = RequestMethod.GET)
	@ResponseBody
	public void saveMeasureDetails(
			@RequestParam(value="userId", required=true) int userId,
			@RequestParam(value="patientID", required=true) int patientID,
			@RequestParam(value="measureStatus", required=true) List<MeasureStatus> measureStatus)
	{
		
		measureService.saveMeasureDetails(userId, patientID, measureStatus, true);
		
	auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.UPDATE, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in saving measure status details" , -1, request.getRemoteAddr(),-1,"",LogUserType.USER_LOGIN, "", "");
		
	}
	
	/**
	 * 
	 * @param patientID
	 * @param providerId
	 * @return
	 * @throws Exception
	 * 
	 * Step -1 : Using provider info, get the configuration details
	 * Step -2 : Get the performance rate as of now.
	 * Step -3 : Based on configured measures, get the EMeasure list
	 * Step -4 : Get the request QDM Object.
	 * Step -5 : Send the request QDM Object to central validation server.
	 * 
	 */
	
	@RequestMapping(value = "/getCQMStatusByPatient", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getQDMRequestObject(
			@RequestParam(value="accountId", required=true) String accountId,
			@RequestParam(value="patientID", required=true) int patientID,
			@RequestParam(value="userId", required=true) int userId,
			@RequestParam(value="sharedFolder", required=true) String sharedPath,
			@RequestParam(value="providerId", required=true) int providerId,
			@RequestParam(value="year", required=true) int year) throws Exception
	{
	
		EMeasureUtils utils = new EMeasureUtils();
		List<Integer> cqmMeasures = new ArrayList<Integer>();		
		Request requestObj = new Request();
		Response responseFromCentralServer = new Response();
		MIPSResponse finalResponse = new MIPSResponse();
		EMRResponseBean response = new EMRResponseBean();
		
		String hub_url = measureService.getMeasureValidationServer()+"/glacecds/ECQMServices/validateECQM";
		int savedUser = userId; //used to store logged userId to store the details in patient entries
		
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		
			
			Boolean isIndividual=measureService.checkGroupOrIndividual(year);

			if(!isIndividual){
				providerId=-1;
				userId=-1;
			}
			
			List<MacraProviderQDM> providerInfo = providerConfService.getCompleteProviderInfo(providerId,year);
			if(providerInfo.size()>0){

				String[] measureIds = providerInfo.get(0).getMeasures().split(",");

				for(int i=0;i<measureIds.length;i++){
					cqmMeasures.add(Integer.parseInt(measureIds[i]));
				}

				HashMap<String, HashMap<String, String>> codeListForQDM = utils.getCodelist(utils.getMeasureBeanDetails(year,providerInfo.get(0).getMeasures(), sharedPath,accountId));
				finalResponse.setMeasureInfo(utils.getMeasureInfo());
				requestObj = measureService.getQDMRequestObject(accountId,isIndividual,patientID, userId, codeListForQDM, providerInfo.get(0).getMacraProviderConfigurationReportingStart(), providerInfo.get(0).getMacraProviderConfigurationReportingEnd());

				requestObj.setAccountId(accountId);
				requestObj.setReportingYear(providerInfo.get(0).getMacraProviderConfigurationReportingYear());
				requestObj.setStartDate(providerInfo.get(0).getMacraProviderConfigurationReportingStart());
				requestObj.setEndDate(providerInfo.get(0).getMacraProviderConfigurationReportingEnd());
				requestObj.setMeasureIds(cqmMeasures);

				response.setData(utils.getMeasureDetails().toString());

				ObjectMapper objectMapper = new ObjectMapper();
				try{
				String requestString = objectMapper.writeValueAsString(requestObj);
				String responseStr = HttpConnectionUtils.postData(hub_url, requestString, HttpConnectionUtils.HTTP_CONNECTION_MODE,"application/json");
				responseFromCentralServer = objectMapper.readValue(responseStr, Response.class);
				}
				catch(Exception e)
				{
					e.printStackTrace(printWriter);

					String responseMsg = GlaceMailer.buildMailContentFormat(accountId, patientID,"Problem with Measure validation server",writer.toString());
					
					GlaceMailer.sendFailureReport(responseMsg,accountId,GlaceMailer.Configure.MU);
					
					
				}
				Map<String,MeasureStatus> measureStatus = responseFromCentralServer.getMeasureStatus();

				responseFromCentralServer.setMeasureStatus(measureStatus);

				finalResponse.setDataFromResponse(responseFromCentralServer);

				List<MeasureStatus> responseToSave = new ArrayList<MeasureStatus>();

				for(int i=0;i<measureStatus.keySet().toArray().length;i++){

					String measureID = measureStatus.keySet().toArray()[i].toString();
					
					measureStatus.get(measureID).setReportingYear(""+providerInfo.get(0).getMacraProviderConfigurationReportingYear());
					responseToSave.add(measureStatus.get(measureID));

				}
				measureService.saveMeasureDetails(savedUser, patientID, responseToSave, true);

			}else{

			}
			response.setData(finalResponse);
			
			printWriter.flush();
			printWriter.close();
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.GENERATE, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in generating and validating patient QDM" , -1, request.getRemoteAddr(),-1,"patientId="+patientID+"&providerId="+providerId,LogUserType.USER_LOGIN, "patientId="+patientID, "");
		return response;
		
	}
	
	/**
	 * Function to get EP Measure status for given patientID
	 */
	
	@RequestMapping(value = "/getEPStatusByPatient", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getEPRequestObject(
			@RequestParam(value="accountId", required=true) String accountId,
			@RequestParam(value="patientID", required=true) int patientID,
			@RequestParam(value="userId", required=true) int userId,
			@RequestParam(value="providerId", required=true) int providerId,
			@RequestParam(value="isTrans", required=false, defaultValue="false") boolean isTrans,
			@RequestParam(value="year", required=true) int year) throws Exception
	{
	
		EMRResponseBean response = new EMRResponseBean();
		List<EPMeasureBean> epMeasureStatus = new ArrayList<EPMeasureBean>();
		
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		
		try{
			
			Boolean isGroup=measureService.checkGroupOrIndividual(year);

			if(!isGroup){
				providerId=-1;
				userId=-1;
			}

			List<MacraProviderQDM> providerInfo = providerConfService.getCompleteProviderInfo(providerId,year);

			if(providerInfo.size()>0){

				epMeasureStatus = measureService.getEPMeasuresResponseObject(accountId,isGroup,patientID, userId, providerInfo.get(0).getMacraProviderConfigurationReportingStart(), providerInfo.get(0).getMacraProviderConfigurationReportingEnd(), providerInfo.get(0).getMacraProviderConfigurationReportingYear(), isTrans);
				//epMeasureStatus = null;
			}else{

			}
			
			response.setData(epMeasureStatus);

		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.GENERATE, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in generating EP Measure details" , -1, request.getRemoteAddr(),-1,"patientId="+patientID+"&providerId="+providerId,LogUserType.USER_LOGIN, "patientId="+patientID, "");
			
		}catch(Exception e){
			
			try {
				
				e.printStackTrace(printWriter);

				String responseMsg = GlaceMailer.buildMailContentFormat(accountId, patientID,"Error occurred while getting EP Measure status",writer.toString());
				
				GlaceMailer.sendFailureReport(responseMsg,accountId,GlaceMailer.Configure.MU);
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
				
			}finally{

				printWriter.flush();
				printWriter.close();
			
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.GENERATE, -1,AuditTrailEnumConstants.Log_Outcome.EXCEPTION,"Error occurred while generating EP Measure details" , -1, request.getRemoteAddr(),-1,"patientId="+patientID+"&providerId="+providerId,LogUserType.USER_LOGIN, "patientId="+patientID, "");
				
				e.printStackTrace();
				
			}
			
		}
		
		return response;
		
	}
	
	@RequestMapping(value = "/getMIPSPerformanceRate", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getMIPSPerformanceRate(
			@RequestParam(value="providerId", required=true) int providerId,
			@RequestParam(value="accountId", required=true) String accountId,
			@RequestParam(value="mode", required=false, defaultValue="1") int mode,
			@RequestParam(value="tinValue", required=false, defaultValue="") String tinValue,
			@RequestParam(value="measureId", required=false, defaultValue="") String measureId,
			@RequestParam(value="isACIReport", required=false, defaultValue="false") boolean isACIReport,
			@RequestParam(value="isTrans", required=false, defaultValue="false") boolean isTrans,
			@RequestParam(value="reportingYear", required=true) int reportingYear) throws Exception{
		
		EMRResponseBean response = new EMRResponseBean();
		
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		String configuredMeasures = "";
		
		try{

			List<MacraProviderQDM> providerInfo = new ArrayList<MacraProviderQDM>();

			if(isACIReport){
				
				if(isTrans){
					configuredMeasures = "ACI_TRANS_EP_1,ACI_TRANS_SM_1,ACI_TRANS_PEA_1,ACI_TRANS_PEA_2,ACI_TRANS_HIE_1,ACI_TRANS_PSE_1,ACI_TRANS_MR_1";
				}else{
					configuredMeasures = "ACI_EP_1,ACI_CCTPE_2,ACI_PEA_1,ACI_CCTPE_1,ACI_HIE_1,ACI_PEA_2,ACI_HIE_3";
				}
				
			}else{
				if(mode!=2){
					providerInfo = providerConfService.getCompleteProviderInfo(providerId,reportingYear);
					if(providerInfo.size()>0)
					configuredMeasures = providerInfo.get(0).getMeasures();
						
				}else{
					configuredMeasures = providerConfService.getCompleteTinInfo(tinValue, reportingYear);
				}
			}
			List<MIPSPerformanceBean> performanceObj = new ArrayList<MIPSPerformanceBean>();
			if(configuredMeasures.equals(""))
			{
				MIPSPerformanceBean emptyObj=new MIPSPerformanceBean();
				emptyObj.setMessage("No Measures Configured for the Reporting Year "+reportingYear);
				performanceObj.add(emptyObj);
			}
			else
			{if(mode == 0){
				performanceObj = measureService.getMeasureRateReportByNPI(providerId, accountId, configuredMeasures,isACIReport, true,reportingYear);
			}else if(mode == 1){
				performanceObj = measureService.getMeasureRateReport(providerId, accountId, configuredMeasures,isACIReport, true,reportingYear);
			}else{
				performanceObj = measureService.getGroupPerformanceCount(tinValue,configuredMeasures, accountId,isACIReport, true,reportingYear);
			}
			}
			if(performanceObj.size()==0)
			{
				MIPSPerformanceBean emptyObj=new MIPSPerformanceBean();
				emptyObj.setMessage("No Measure credits recorded for the reporting year "+reportingYear);
				performanceObj.add(emptyObj);
			}
			else if(isACIReport==true && isTrans==true)
			{
				performanceObj=measureService.getAttObjsAndOrderIt(providerId,reportingYear,performanceObj);
				
			}
			
			
			response.setData(performanceObj);
			
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.GENERATE, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in generating MIPS Performance Report" , -1, request.getRemoteAddr(),-1,"isACIReport="+isACIReport+"&providerId="+providerId,LogUserType.USER_LOGIN, "", "");

		}catch(Exception e){
			
			try {
				
				e.printStackTrace(printWriter);

				String responseMsg = GlaceMailer.buildMailContentFormat(accountId, -1,"Error occurred while getting MIPSPerformance Report",writer.toString());
				
				GlaceMailer.sendFailureReport(responseMsg,accountId,GlaceMailer.Configure.MU);
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
				
			}finally{

				printWriter.flush();
				printWriter.close();
			
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.GENERATE, -1,AuditTrailEnumConstants.Log_Outcome.FAILURE ,"Error in generating MIPS Performance Report" , -1, request.getRemoteAddr(),-1,"isACIReport="+isACIReport+"&providerId="+providerId,LogUserType.USER_LOGIN, "", "");
				
				e.printStackTrace();
				
			}
			
		}
		
		return response;
		
	}
	
	
	@RequestMapping(value = "/getPatient", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean getPatient(@RequestBody getPatientBean requestBean)throws JSONException{
		
		EMRResponseBean response = new EMRResponseBean();
		
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		try{
		
			List<MIPSPatientInformation> filtersInfo = measureService.getPatient(requestBean.getPatientId(),requestBean.getMeasureId(),requestBean.getCriteria(),requestBean.getProvider(),requestBean.getEmpTIN(), requestBean.getMode(),requestBean.getIsNotMet(),requestBean.getYear());
			response.setData(filtersInfo);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.GENERATE, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in getting filtered patients list based on measure" , -1, request.getRemoteAddr(),-1,"patientId="+requestBean.getPatientId()+"&measureId="+requestBean.getMeasureId()+"&providerId="+requestBean.getEmpTIN(),LogUserType.USER_LOGIN, "patientId="+requestBean.getPatientId(), "");
		
		}catch(Exception e){
			
			try {
				
				e.printStackTrace(printWriter);

				String responseMsg = GlaceMailer.buildMailContentFormat(requestBean.getAccountId(), -1,"Error occurred while getting filtered patients list",writer.toString());
				
				GlaceMailer.sendFailureReport(responseMsg,requestBean.getAccountId(),GlaceMailer.Configure.MU);
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
				
			}finally{

				printWriter.flush();
				printWriter.close();
			
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.GENERATE, -1,AuditTrailEnumConstants.Log_Outcome.FAILURE ,"Error in getting filtered patients list based on measure" , -1, request.getRemoteAddr(),-1,"patientId="+requestBean.getPatientId()+"&measureId="+requestBean.getMeasureId()+"&providerId="+requestBean.getEmpTIN(),LogUserType.USER_LOGIN, "patientId="+requestBean.getPatientId(), "");
				
				e.printStackTrace();
				
			}
			
		}
		
		return response;
		
	}
	
	/*@RequestMapping(value = "/getMIPSPerformanceRateByNPI", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getMIPSPerformanceRateByNPI(
			@RequestParam(value="providerId", required=true) int providerId,
			@RequestParam(value="accountId", required=true) String accountId,
			@RequestParam(value="measureId", required=false, defaultValue="") String measureId,
			@RequestParam(value="isACIReport", required=false, defaultValue="false") boolean isACIReport) throws Exception{
		
		EMRResponseBean response = new EMRResponseBean();
		
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		try{
		
		List<MacraProviderQDM> providerInfo = providerConfService.getCompleteProviderInfo(providerId,Calendar.getInstance().get(Calendar.YEAR));
		
		String configuredMeasures = providerInfo.get(0).getMeasures();
		
		List<MIPSPerformanceBean> performanceObj = measureService.getMeasureRateReport(providerId, accountId, configuredMeasures,isACIReport, true);

		response.setData(performanceObj);
		
	auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.GENERATE, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in generating MIPS Performance Report by NPI" , -1, request.getRemoteAddr(),-1,"isACIReport="+isACIReport+"&providerId="+providerId,LogUserType.USER_LOGIN, "", "");
		
		}catch(Exception e){
			
			try {
				
				e.printStackTrace(printWriter);

				String responseMsg = GlaceMailer.buildMailContentFormat(accountId, -1,"Error occurred while getting MIPSPerformance Report",writer.toString());
				
				GlaceMailer.sendFailureReport(responseMsg,accountId,GlaceMailer.Configure.MU);
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
				
			}finally{

				printWriter.flush();
				printWriter.close();
				
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.GENERATE, -1,AuditTrailEnumConstants.Log_Outcome.FAILURE ,"Error in generating MIPS Performance Report by NPI" , -1, request.getRemoteAddr(),-1,"isACIReport="+isACIReport+"&providerId="+providerId,LogUserType.USER_LOGIN, "", "");
				
				e.printStackTrace();
				
			}
			
		}
		
		return response;
		
	}
	*/
	@RequestMapping(value = "/getDashBoardDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getMUDashboardDetails(
			@RequestParam(value="providerId", required=true) int providerId,
			@RequestParam(value="accountId", required=true) String accountId,
			@RequestParam(value="tinValue", required=false, defaultValue="") String tinValue,
			@RequestParam(value="sharedPath", required=true) String sharedPath,
			@RequestParam(value="isByNpi", required=false, defaultValue="false") boolean byNpi,
			@RequestParam(value="isTrans", required=false, defaultValue="false") boolean isTrans,
			@RequestParam(value="reportingYear", required=true) Integer reportingYear) throws Exception{
		
		EMRResponseBean response = new EMRResponseBean();
		MUDashboardBean providerDashboard = new MUDashboardBean();
		String message="";
		try{
			
			String aciMeasures = "";
			
			if(isTrans){
				aciMeasures = "ACI_TRANS_EP_1,ACI_TRANS_SM_1,ACI_TRANS_PEA_1,ACI_TRANS_PEA_2,ACI_TRANS_HIE_1,ACI_TRANS_PSE_1,ACI_TRANS_MR_1";
			}else{
				aciMeasures = "ACI_EP_1,ACI_CCTPE_2,ACI_PEA_1,ACI_CCTPE_1,ACI_HIE_1,ACI_PEA_2,ACI_HIE_3";
			}
			
			Boolean isIndividual=measureService.checkGroupOrIndividual(reportingYear);

			if(!isIndividual){
				providerId=-1;
			}

			List<MacraProviderQDM> providerInfo = providerConfService.getCompleteProviderInfo(providerId,reportingYear);
			
			if(providerInfo.size()>0){
				providerDashboard.setMessage("Success");
				providerDashboard.setReportingYear(providerInfo.get(0).getMacraProviderConfigurationReportingYear());
				providerDashboard.setStartDate(providerInfo.get(0).getMacraProviderConfigurationReportingStart());
				providerDashboard.setEndDate(providerInfo.get(0).getMacraProviderConfigurationReportingEnd());
				
				measureService.getDashBoardDetails(providerId, accountId, tinValue, providerInfo.get(0).getMeasures(), aciMeasures, byNpi, providerDashboard,sharedPath);
				
			}else{
				
				//measureService.getDashBoardDetails(providerId, accountId, tinValue, "", aciMeasures, byNpi, providerDashboard,sharedPath);
				message="No Measures Configured for the Reporting year "+reportingYear;
				providerDashboard.setMessage(message);
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		response.setData(providerDashboard);
		
		return response;
		
	}
	
	@RequestMapping(value="/fetchMeasureDetails", method= RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getMIPSMeasureDetails(
			//@RequestParam(value="patientId", required=false) int patientId,
			//@RequestParam(value="encounterId", required=false) int encounterId,
			//@RequestParam(value="providerId", required=false) int providerId,
			@RequestParam(value="accountId", required=false) String accountId,
			@RequestParam(value="measureId", required=true) String measureId,
			@RequestParam(value="year", required=true) Integer year) throws Exception{
		String resData = measureService.getMIPSMeasureDetails(measureId,accountId,year);
		EMRResponseBean response = new EMRResponseBean();
		response.setData(resData);
		return response;
		
	}
	
	@RequestMapping(value = "/generatePDF", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean generatePDF(
			@RequestParam(value = "provId", required = true) int provId,
			@RequestParam(value = "measureid", required = true)String measureid,
			@RequestParam(value = "accountId", required = true)String accountId,
			@RequestParam(value = "criteriaId", required = true)int criteriaId,
			@RequestParam(value = "tinId", required= true)String tinId,
			@RequestParam(value = "criterias", required = true)int criterias,
			@RequestParam(value = "isNotMet", required= true)boolean isNotMet)throws Exception
	{	
		EMRResponseBean result=new EMRResponseBean();
		String fileName;
		Date reportingStart = null,reportingEnd = null;
		Integer ReportingMethod = null;
		String submissionMethod = null;String reportEnd = null,reportStart = null;
		GeneratePDFDetails generatePDFDetails=new GeneratePDFDetails();
		try{
		if(provId!=-1){
			List<ConfigurationDetails> groupData = providerConfService.getProviderInfo(provId,2017);
			reportingStart= groupData.get(0).getMacraProviderConfigurationReportingStart();
			reportingEnd= groupData.get(0).getMacraProviderConfigurationReportingEnd();
			ReportingMethod=groupData.get(0).getMacraProviderConfigurationReportingMethod();

			if(ReportingMethod==1)
				submissionMethod = "Claims";
			else if(ReportingMethod==2)
				submissionMethod = "EHR";
			else if(ReportingMethod==3)
				submissionMethod = "Registry";

			SimpleDateFormat reportingDate= new SimpleDateFormat("MM/dd/yyyy");
			reportEnd=reportingDate.format(reportingEnd);
			reportStart=reportingDate.format(reportingStart);
			generatePDFDetails.setSubmissionMethod(submissionMethod);
			generatePDFDetails.setReportStart(reportStart);
			generatePDFDetails.setReportEnd(reportEnd);
		}

		fileName = measureService.generatePDFFile(generatePDFDetails,provId,measureid,accountId,criteriaId,tinId,criterias,isNotMet);
		}
		catch(Exception e){
			fileName="";
		}
		result.setData(fileName);
		return result;
	}
	
	@RequestMapping(value = "/mipsPerformancePDF", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean mipsPerformancePDF(@RequestParam(value = "provId", required = true) int provId,
			@RequestParam(value = "measureid", required = true)String measureid,
			@RequestParam(value = "accountId", required = true)String accountId,
			@RequestParam(value = "tinId", required= true)String tinId,
			@RequestParam(value="isACIReport", required=false, defaultValue="false") boolean isACIReport)throws Exception
	{
		EMRResponseBean result=new EMRResponseBean();
		String fileName;
		Date reportingStart = null,reportingEnd = null;
		Integer ReportingMethod = null;
		String submissionMethod = null;String reportEnd = null,reportStart = null;
		GeneratePDFDetails generatePDFDetails=new GeneratePDFDetails();
		String configuredMeasures = null;
		Calendar now = Calendar.getInstance();
		int reportingYear = now.get(Calendar.YEAR);	
		try{
		if(provId!=-1){
			List<ConfigurationDetails> groupData = providerConfService.getProviderInfo(provId,2017);
			reportingStart= groupData.get(0).getMacraProviderConfigurationReportingStart();
			reportingEnd= groupData.get(0).getMacraProviderConfigurationReportingEnd();
			ReportingMethod=groupData.get(0).getMacraProviderConfigurationReportingMethod();

			if(ReportingMethod==1)
				submissionMethod = "Claims";
			else if(ReportingMethod==2)
				submissionMethod = "EHR";
			else if(ReportingMethod==3)
				submissionMethod = "Registry";

			SimpleDateFormat reportingDate= new SimpleDateFormat("MM/dd/yyyy");
			reportEnd=reportingDate.format(reportingEnd);
			reportStart=reportingDate.format(reportingStart);
			generatePDFDetails.setSubmissionMethod(submissionMethod);
			generatePDFDetails.setReportStart(reportStart);
			generatePDFDetails.setReportEnd(reportEnd);
			List<MacraProviderQDM> providerInfo = new ArrayList<MacraProviderQDM>();
			providerInfo = providerConfService.getCompleteProviderInfo(provId,Calendar.getInstance().get(Calendar.YEAR));
			configuredMeasures = providerInfo.get(0).getMeasures();
		}
		else
			configuredMeasures = providerConfService.getCompleteTinInfo(tinId, reportingYear);

		fileName = measureService.mipsPDFfile(generatePDFDetails,provId,configuredMeasures,accountId,tinId,isACIReport,true);
		
		}
		catch(Exception e)
		{
			fileName="";
		}
		result.setData(fileName);
		return result;
	}
	
	@RequestMapping(value = "/mipsReport", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean mipsReport(@RequestParam(value = "provId", required = true) int provId,
			@RequestParam(value = "reportingYear", required = true) Integer year,
			@RequestParam(value = "accountId", required = true)String accountId,
			@RequestParam(value = "measureid", required = true)String measureid,
			@RequestParam(value = "tinId", required= true)String tinId,
			@RequestParam(value="isByNpi", required=false, defaultValue="false") boolean byNpi,
			@RequestParam(value="mode", required=false, defaultValue="1") int mode,
			@RequestParam(value="isACIReport", required=false, defaultValue="false") boolean isACIReport,
			@RequestParam(value="practiceName",required=true)String practiceName,
			@RequestParam(value="sharedPath", required=true) String sharedPath,
			@RequestParam(value="isTrans", required=false, defaultValue="false") boolean isTrans)throws Exception
	{
		EMRResponseBean result=new EMRResponseBean();
		String fileName = null;
		Date reportingStart = null,reportingEnd = null;
		Integer ReportingMethod = null;
		String submissionMethod = null;String reportEnd = null,reportStart = null;
		GeneratePDFDetails generatePDFDetails=new GeneratePDFDetails();
		MUDashboardBean providerDashboard = new MUDashboardBean();
		String configuredMeasures = null;
		Calendar now = Calendar.getInstance();
		int reportingYear = now.get(Calendar.YEAR);	
		try{
			List<MacraProviderQDM> providerInfo = new ArrayList<MacraProviderQDM>();

			EMRResponseBean  emrResponse=getMIPSPerformanceRate(provId, accountId, mode, tinId, measureid, isACIReport, false,year);
			List<MIPSPerformanceBean> aciMeasures= (List<MIPSPerformanceBean>) emrResponse.getData();
			generatePDFDetails.setAciMeasureInfo(aciMeasures);

			EMRResponseBean  Response=getMIPSPerformanceRate(provId, accountId, mode, tinId, measureid, isACIReport, true,year);
			List<MIPSPerformanceBean> aciTransMeasures= (List<MIPSPerformanceBean>) Response.getData();
			generatePDFDetails.setAciTransMeasureInfo(aciTransMeasures);

			if(provId!=-1){
				List<ConfigurationDetails> groupData = providerConfService.getProviderInfo(provId,year);
				reportingStart= groupData.get(0).getMacraProviderConfigurationReportingStart();
				reportingEnd= groupData.get(0).getMacraProviderConfigurationReportingEnd();
				ReportingMethod=groupData.get(0).getMacraProviderConfigurationReportingMethod();

				if(ReportingMethod==1)
					submissionMethod = "Claims";
				else if(ReportingMethod==2)
					submissionMethod = "EHR";
				else if(ReportingMethod==3)
					submissionMethod = "Registry";

				SimpleDateFormat reportingDate= new SimpleDateFormat("MM/dd/yyyy");
				reportEnd=reportingDate.format(reportingEnd);
				reportStart=reportingDate.format(reportingStart);
				generatePDFDetails.setSubmissionMethod(submissionMethod);
				generatePDFDetails.setReportStart(reportStart);
				generatePDFDetails.setReportEnd(reportEnd);
				providerInfo = providerConfService.getCompleteProviderInfo(provId,year);
				configuredMeasures = providerInfo.get(0).getMeasures();
				result = getMUDashboardDetails(provId, accountId, tinId, sharedPath, byNpi, isTrans,year);

			}
			else{
				configuredMeasures = providerConfService.getCompleteTinInfo(tinId, reportingYear);
				result = getMUDashboardDetails(provId, accountId, tinId, sharedPath, byNpi, isTrans,year);
			}
			providerDashboard=(MUDashboardBean) result.getData();
			generatePDFDetails.setDashboardInfo(providerDashboard);
			fileName = measureService.mipsReportPDF(generatePDFDetails,provId,configuredMeasures,accountId,tinId,isACIReport,true,practiceName,year);

		}
		catch(Exception e)
		{
			fileName="";
		}
		result.setData(fileName);
		return result;
	}
	
	@RequestMapping(value = "/mipsReportwithPatientInfo", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean mipsReportwithPatientInfo(@RequestParam(value = "provId", required = true) int provId,
			@RequestParam(value = "reportingYear", required = true) Integer year,
			@RequestParam(value = "accountId", required = true)String accountId,
			@RequestParam(value = "measureid", required = true)String measureid,
			@RequestParam(value = "tinId", required= true)String tinId,
			@RequestParam(value="isByNpi", required=false, defaultValue="false") boolean byNpi,
			@RequestParam(value="mode", required=false, defaultValue="1") int mode,
			@RequestParam(value="isACIReport", required=false, defaultValue="false") boolean isACIReport,
			@RequestParam(value="practiceName",required=true)String practiceName,
			@RequestParam(value="sharedPath", required=true) String sharedPath,
			@RequestParam(value="isTrans", required=false, defaultValue="false") boolean isTrans)throws Exception
	{
		EMRResponseBean result=new EMRResponseBean();
		String fileName = null;
		Date reportingStart = null,reportingEnd = null;
		Integer ReportingMethod = null;
		String submissionMethod = null;String reportEnd = null,reportStart = null;
		GeneratePDFDetails generatePDFDetails=new GeneratePDFDetails();
		MUDashboardBean providerDashboard = new MUDashboardBean();
		String configuredMeasures = null;
		Calendar now = Calendar.getInstance();
		int reportingYear = now.get(Calendar.YEAR);	
		try{
			List<MacraProviderQDM> providerInfo = new ArrayList<MacraProviderQDM>();

			EMRResponseBean  emrResponse=getMIPSPerformanceRate(provId, accountId, mode, tinId, measureid, isACIReport, false,year);
			List<MIPSPerformanceBean> aciMeasures= (List<MIPSPerformanceBean>) emrResponse.getData();
			generatePDFDetails.setAciMeasureInfo(aciMeasures);

			EMRResponseBean  Response=getMIPSPerformanceRate(provId, accountId, mode, tinId, measureid, isACIReport, true,year);
			List<MIPSPerformanceBean> aciTransMeasures= (List<MIPSPerformanceBean>) Response.getData();
			generatePDFDetails.setAciTransMeasureInfo(aciTransMeasures);

			if(provId!=-1){
				List<ConfigurationDetails> groupData = providerConfService.getProviderInfo(provId,year);
				reportingStart= groupData.get(0).getMacraProviderConfigurationReportingStart();
				reportingEnd= groupData.get(0).getMacraProviderConfigurationReportingEnd();
				ReportingMethod=groupData.get(0).getMacraProviderConfigurationReportingMethod();

				if(ReportingMethod==1)
					submissionMethod = "Claims";
				else if(ReportingMethod==2)
					submissionMethod = "EHR";
				else if(ReportingMethod==3)
					submissionMethod = "Registry";

				SimpleDateFormat reportingDate= new SimpleDateFormat("MM/dd/yyyy");
				reportEnd=reportingDate.format(reportingEnd);
				reportStart=reportingDate.format(reportingStart);
				generatePDFDetails.setSubmissionMethod(submissionMethod);
				generatePDFDetails.setReportStart(reportStart);
				generatePDFDetails.setReportEnd(reportEnd);
				providerInfo = providerConfService.getCompleteProviderInfo(provId,year);
				configuredMeasures = providerInfo.get(0).getMeasures();
				result = getMUDashboardDetails(provId, accountId, tinId, sharedPath, byNpi, isTrans,year);

			}
			else{
				configuredMeasures = providerConfService.getCompleteTinInfo(tinId, reportingYear);
				result = getMUDashboardDetails(provId, accountId, tinId, sharedPath, byNpi, isTrans,year);
			}
			providerDashboard=(MUDashboardBean) result.getData();
			generatePDFDetails.setDashboardInfo(providerDashboard);
			fileName = measureService.mipsReportwithPatientInfo(generatePDFDetails,provId,configuredMeasures,accountId,tinId,isACIReport,true,practiceName,year);

		}
		catch(Exception e)
		{
			fileName="";
		}
		result.setData(fileName);

		return result;
	}
	
}