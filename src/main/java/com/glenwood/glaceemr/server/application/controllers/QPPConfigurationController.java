package com.glenwood.glaceemr.server.application.controllers;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.Bean.DiagnosisList;
import com.glenwood.glaceemr.server.application.Bean.IAMeasureBean;
import com.glenwood.glaceemr.server.application.Bean.MIPSPatientInformation;
import com.glenwood.glaceemr.server.application.Bean.getMeasureBean;
import com.glenwood.glaceemr.server.application.models.MacraProviderConfiguration;
import com.glenwood.glaceemr.server.application.models.QualityMeasuresProviderMapping;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.chart.MIPS.ConfigurationDetails;
import com.glenwood.glaceemr.server.application.services.chart.MIPS.QPPConfigurationService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;


@RestController
@Transactional
@RequestMapping(value = "/user/QPPConfiguration")
public class QPPConfigurationController {

	@Autowired
	QPPConfigurationService QppConfigurationService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;

	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(value = "/saveConfDetails", method = RequestMethod.GET)
	@ResponseBody
	public void saveConfDetails(
			@RequestParam(value = "prgmYear", required = true) Integer programYear,
			@RequestParam(value = "type", required = true) Integer type,
			@RequestParam(value = "providerId", required = false, defaultValue = "-1") Integer providerId,
			@RequestParam(value = "startDate", required = true) String startDate,
			@RequestParam(value = "endDate", required = true) String endDate,
			@RequestParam(value = "submissionMtd", required = true) Integer submissionMtd,
			@RequestParam(value = "reportType", required = true) short reportType)throws Exception {
		
			SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
			
			Date StartDate = originalFormat.parse(startDate);
			Date EndDate = originalFormat.parse(endDate);
			
			QppConfigurationService.saveConfDetails(programYear, type, providerId, StartDate, EndDate, submissionMtd,reportType);

			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.CREATEORUPDATE, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in saving provider MACRA configuration details" , -1, request.getRemoteAddr(),-1,"providerId="+providerId+"&reportingYear="+programYear,LogUserType.USER_LOGIN, "", "");

	}
	
	@RequestMapping(value = "/getProviderInfo", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getProviderInfo(
			@RequestParam(value = "provider", required = true) Integer provider,
			@RequestParam(value = "year", required = true) Integer year)throws Exception {

		EMRResponseBean result=new EMRResponseBean();

		if(!provider.equals(null)){

			List<ConfigurationDetails> groupData = QppConfigurationService.getProviderInfo(provider,year);
			result.setData(groupData);

		}

//		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.GENERATE, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in getting MACRA configuration details for provider" , -1, request.getRemoteAddr(),-1,"providerId="+provider,LogUserType.USER_LOGIN, "", "");
		
		return result;

	}
	
	@RequestMapping(value = "/getMeasureIds", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getIndividualMeasureIds(
			@RequestParam(value = "providerId", required = true) Integer providerId,
			@RequestParam(value = "year", required = true)Integer year)throws Exception {
		EMRResponseBean result=new EMRResponseBean();
		List<ConfigurationDetails> indiMeasureids=QppConfigurationService.getMeasureIds(providerId,year);
		result.setData(indiMeasureids);
		return result;
	}
	
	@RequestMapping(value = "/addMeasuresToProvider", method = RequestMethod.GET)
	@ResponseBody
	public void addMeasuresToProvider(
			@RequestParam(value = "measureIds", required = true) String measureIds,
			@RequestParam(value = "providerId", required = true) Integer providerId,
			@RequestParam(value = "prgmYear", required = true) Integer prgmYear)throws Exception {
		
		QppConfigurationService.addMeasuresToProvider(measureIds,providerId,prgmYear);
		
//		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.CREATEORUPDATE, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in saving measure configuration for provider" , -1, request.getRemoteAddr(),-1,"measures="+measureIds,LogUserType.USER_LOGIN, "", "");
		
	}
	
	@RequestMapping(value = "/getImprovementActMeasureIds", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getImprovementActMeasureIds(
			@RequestParam(value = "providerId", required = true) Integer providerId,
			@RequestParam(value = "year", required = true)Integer year)throws Exception {
		EMRResponseBean result=new EMRResponseBean();
		List<ConfigurationDetails> iaMeasureDetails=QppConfigurationService.getImprovementActivityMeasureIds(providerId,year);
		result.setData(iaMeasureDetails);
		return result;
	}
	
	@RequestMapping(value = "/addImpMeasuresToProvider", method = RequestMethod.POST)
	@ResponseBody 
	public void addImpMeasuresToProvider(@RequestBody List<getMeasureBean> requestBean ) throws Exception{
		
		QppConfigurationService.addImpMeasuresToProvider(requestBean);
		
	}
	
	@RequestMapping(value = "/addIAmeasures", method = RequestMethod.POST)
	@ResponseBody
	public void addIAmeasures(
			@RequestBody List<IAMeasureBean> requestBean ) throws Exception {
		QppConfigurationService.addIAmeasures(requestBean);
	}
	
	@RequestMapping(value = "/getconfigIAmeasures", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getconfigIAmeasures(
			@RequestParam(value = "providerId", required = true) Integer providerId,
			@RequestParam(value = "year", required = true)Integer year)throws Exception {
		EMRResponseBean result=new EMRResponseBean();
		List<ConfigurationDetails> iaMeasures = QppConfigurationService.getConfiguredIameasures(providerId,year);
		result.setData(iaMeasures);
		return result;
	}
	
	@RequestMapping(value = "/getFilterDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getFilterDetails()throws Exception {
		
		EMRResponseBean result=new EMRResponseBean();
		HashMap<String,Object> filterDetails=QppConfigurationService.getFilterDetails();
		result.setData(filterDetails);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.GENERATE, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in getting filter details to search patients" , -1, request.getRemoteAddr(),-1,"",LogUserType.USER_LOGIN, "", "");
		
		return result;
	}
	
	@RequestMapping(value = "/getFilteredDetails", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getFilteredDetails(
			@RequestParam(value = "ageFrom", required = false, defaultValue = "-1") Integer ageFrom,
			@RequestParam(value = "ageTo", required = false, defaultValue = "-1") Integer ageTo,
			@RequestParam(value = "raceCode", required = false, defaultValue = "-1") String raceCode,
			@RequestParam(value = "ethnicityCode", required = false, defaultValue = "-1") String ethnicityCode,
			@RequestParam(value = "gender", required = false, defaultValue = "-1") String gender,
			@RequestParam(value = "patientId", required = true) String patientId,
			@RequestParam(value = "insCompanyId", required = false, defaultValue = "-1") Integer insCompanyId,
			@RequestParam(value = "ageCriteria", required = false, defaultValue = "-1") Integer ageCriteria,
			@RequestParam(value = "currMeasureId", required = true) String currMeasureId,
			@RequestParam(value = "dxCodes", required = false,defaultValue = "-1")String dxCodes,
			@RequestParam(value = "posId", required = false,defaultValue = "-1")int posId,
			@RequestParam(value = "insId", required = false,defaultValue = "-1")int insId)throws Exception {
		
		EMRResponseBean result=new EMRResponseBean();
		List<MIPSPatientInformation> filteredDetails=QppConfigurationService.getFilteredDetails(patientId,ageFrom,ageTo,ageCriteria,raceCode,ethnicityCode,gender,insCompanyId,currMeasureId,dxCodes,posId,insId);
		result.setData(filteredDetails);

		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.GENERATE, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in getting patients based on selected filters" , -1, request.getRemoteAddr(),-1,"",LogUserType.USER_LOGIN, "", "");
		
		return result;
		
	}
	@RequestMapping(value = "/getDXList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getDXList(
			@RequestParam(value = "measureId", required = false, defaultValue = "-1")String measureId,
			@RequestParam(value="sharedFolder", required=true) String sharedPath)throws Exception {
		
		EMRResponseBean result=new EMRResponseBean();
		DiagnosisList DXList=QppConfigurationService.getDXList(measureId,sharedPath); 
		result.setData(DXList);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.GENERATE, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in getting diagnosis codes based on selected measure details" , -1, request.getRemoteAddr(),-1,"measureId="+measureId,LogUserType.USER_LOGIN, "", "");
		
		return result;
		
	}
	
	@RequestMapping(value = "/getPatientBasedOnDX", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientBasedOnDX(
			@RequestParam(value = "patientId", required = true)String patientId,
			@RequestParam(value="dxCodes", required=true) String dxCodes)throws Exception {
		
		EMRResponseBean result=new EMRResponseBean();
		List<MIPSPatientInformation> DXList=QppConfigurationService.getPatientBasedOnDX(patientId,dxCodes); 
		result.setData(DXList);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.MU,LogActionType.GENERATE, -1,AuditTrailEnumConstants.Log_Outcome.SUCCESS ,"Success in getting patients list based on selected diagnosis codes" , -1, request.getRemoteAddr(),-1,"patientId="+patientId,LogUserType.USER_LOGIN, "patientId="+patientId, "");
		
		return result;
		
	}
	
}