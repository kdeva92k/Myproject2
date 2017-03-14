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

import com.glenwood.glaceemr.server.application.models.DoctorSign;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.print.SignaturePrint;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditLogConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailService;
import com.glenwood.glaceemr.server.application.services.chart.print.signature.SignaturePrintService;
import com.glenwood.glaceemr.server.application.services.employee.EmployeeService;
import com.glenwood.glaceemr.server.utils.SessionMap;

@RestController
@RequestMapping(value="/user/SignaturePrint.Action")
public class SignaturePrintController {

	@Autowired
	SignaturePrintService signaturePrintService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	AuditTrailService auditTrailService;

	@Autowired
	SessionMap sessionMap;

	@Autowired
	HttpServletRequest request;

	private Logger logger = Logger.getLogger(SignaturePrintController.class);


	/**
	 * Request to get the list of all Signature print styles for configuration
	 * @return List<SignaturePrint>
	 * 
	 */
	@RequestMapping(value = "/FetchSignaturePrintStyleList",method = RequestMethod.GET)
	public List<SignaturePrint> fetchSignaturePrintStyleList() throws Exception{
		logger.debug("Begin request processing - to get the list of all Signature print styles.");
		List<SignaturePrint> signaturePrintStyleList = signaturePrintService.getSignaturePrintStyleList();
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PrintingAndReporting,AuditLogConstants.VIEWED,1,AuditLogConstants.SUCCESS,"Successfully loaded signature list for configuration",sessionMap.getUserID(),"",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.PrintingAndReporting,request,"Successfully loaded signature list for configuration");
		logger.debug("End of request to get the list of all Signature print styles.");
		return signaturePrintStyleList;

	}
	
	
	/**
	 * Request to save signature print
	 * @param signature style details
	 * @return SignaturePrint - saved data
	 */
	@RequestMapping(value = "/saveSignaturePrint",method = RequestMethod.POST)
	public SignaturePrint saveSignaturePrint(@RequestParam(value="signaturePrintName") String signaturePrintName,
			@RequestParam(value="isSigndetailsReq") Boolean isSigndetailsReq,
			@RequestParam(value="disclaimer") String signaturePrintDisclaimer,@RequestParam(value="disclaimerStyle") String signaturePrintDisclaimerStyle,
			@RequestParam(value="signfooter") String signaturePrintSignfooter,@RequestParam(value="signfooterStyle") String signaturePrintSignfooterStyle,
			@RequestParam(value="cosignfooter") String signaturePrintCosignfooter,@RequestParam(value="cosignfooterStyle") String signaturePrintCosignfooterStyle,
			@RequestParam(value="isEncounterDateReq") Boolean isEncounterDateReq,@RequestParam(value="isSignTimestampReq") Boolean isSignTimestampReq,
			@RequestParam(value="isCosignTimestampReq") Boolean isCosignTimestampReq,@RequestParam(value="isEvaluationTimeReq") Boolean isEvaluationTimeReq) throws Exception{
		logger.debug("Begin request processing  - to Save signature print.");
		
		SignaturePrint newSignaturePrint=new SignaturePrint();
		newSignaturePrint.setSignaturePrintName(signaturePrintName);
		newSignaturePrint.setIsSigndetailsReq(isSigndetailsReq);
		newSignaturePrint.setSignaturePrintDisclaimer(signaturePrintDisclaimer);
		newSignaturePrint.setSignaturePrintDisclaimerStyle(signaturePrintDisclaimerStyle);
		newSignaturePrint.setSignaturePrintSignfooter(signaturePrintSignfooter);
		newSignaturePrint.setSignaturePrintSignfooterStyle(signaturePrintSignfooterStyle);
		newSignaturePrint.setSignaturePrintCosignfooter(signaturePrintCosignfooter);
		newSignaturePrint.setSignaturePrintCosignfooterStyle(signaturePrintCosignfooterStyle);
		newSignaturePrint.setIsEncounterDateReq(isEncounterDateReq);
		newSignaturePrint.setIsSignTimestampReq(isSignTimestampReq);
		newSignaturePrint.setIsCosignTimestampReq(isCosignTimestampReq);
		newSignaturePrint.setIsEvaluationTimeReq(isEvaluationTimeReq);
		SignaturePrint signaturePrint = signaturePrintService.saveSignaturePrint(newSignaturePrint);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PrintingAndReporting,AuditLogConstants.CREATED,1,AuditLogConstants.SUCCESS,"Successfully saved signature print style",sessionMap.getUserID(),"",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.PrintingAndReporting,request,"Successfully saved signature print style");
		logger.debug("End of request to Save signature print.");
		return signaturePrint;
		
	}
	
	/**
	 * Request to update signature print
	 * @param Signature details to update
	 * @return SignaturePrint - updated data
	 * 
	 */
	@RequestMapping(value = "/updateSignaturePrint",method = RequestMethod.POST)
	public SignaturePrint updateGenericFooter(@RequestParam(value="signaturePrintName") String signaturePrintName,
			@RequestParam(value="isSigndetailsReq") Boolean isSigndetailsReq,
			@RequestParam(value="disclaimer") String signaturePrintDisclaimer,@RequestParam(value="disclaimerStyle") String signaturePrintDisclaimerStyle,
			@RequestParam(value="signfooter") String signaturePrintSignfooter,@RequestParam(value="signfooterStyle") String signaturePrintSignfooterStyle,
			@RequestParam(value="cosignfooter") String signaturePrintCosignfooter,@RequestParam(value="cosignfooterStyle") String signaturePrintCosignfooterStyle,
			@RequestParam(value="isEncounterDateReq") Boolean isEncounterDateReq,@RequestParam(value="isSignTimestampReq") Boolean isSignTimestampReq,
			@RequestParam(value="isCosignTimestampReq") Boolean isCosignTimestampReq,@RequestParam(value="isEvaluationTimeReq") Boolean isEvaluationTimeReq,
			@RequestParam(value="signaturePrintId") Integer signaturePrintId) throws Exception{
		logger.debug("Begin request processing - to Update signature print.");
		
		SignaturePrint updateSignaturePrint=new SignaturePrint();
		updateSignaturePrint.setSignaturePrintId(signaturePrintId);
		updateSignaturePrint.setSignaturePrintName(signaturePrintName);
		updateSignaturePrint.setIsSigndetailsReq(isSigndetailsReq);
		updateSignaturePrint.setSignaturePrintDisclaimer(signaturePrintDisclaimer);
		updateSignaturePrint.setSignaturePrintDisclaimerStyle(signaturePrintDisclaimerStyle);
		updateSignaturePrint.setSignaturePrintSignfooter(signaturePrintSignfooter);
		updateSignaturePrint.setSignaturePrintSignfooterStyle(signaturePrintSignfooterStyle);
		updateSignaturePrint.setSignaturePrintCosignfooter(signaturePrintCosignfooter);
		updateSignaturePrint.setSignaturePrintCosignfooterStyle(signaturePrintCosignfooterStyle);
		updateSignaturePrint.setIsEncounterDateReq(isEncounterDateReq);
		updateSignaturePrint.setIsSignTimestampReq(isSignTimestampReq);
		updateSignaturePrint.setIsCosignTimestampReq(isCosignTimestampReq);
		updateSignaturePrint.setIsEvaluationTimeReq(isEvaluationTimeReq);
		SignaturePrint signaturePrint = signaturePrintService.saveSignaturePrint(updateSignaturePrint);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PrintingAndReporting,AuditLogConstants.CREATED,1,AuditLogConstants.UPDATE,"Successfully updated signature print style",sessionMap.getUserID(),"",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.PrintingAndReporting,request,"Successfully updated signature print style");
		logger.debug("End of request to Update signature print.");
		return signaturePrint;
		
	}
	
	/**
	 * Request to get signature style based on id
	 * @param Integer signaturePrintId - style id
	 * @return SignaturePrint - Object that matches style id
	 */
	@RequestMapping(value = "/FetchSignaturePrint",method = RequestMethod.GET)
	public SignaturePrint getSignaturePrint(@RequestParam(value="signId") Integer signaturePrintId) throws Exception{
		logger.debug("Begin request processing - to Fetch signature print.");
		SignaturePrint signaturePrint = signaturePrintService.getSignaturePrint(signaturePrintId);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PrintingAndReporting,AuditLogConstants.VIEWED,1,AuditLogConstants.UPDATE,"Successfully fetched signature print",sessionMap.getUserID(),"",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.PrintingAndReporting,request,"Successfully fetched signature print");
		logger.debug("End of request to Fetch signature print.");
		return signaturePrint;
		
	}

	/**
	 * Request to get signature footer based on id
	 * @param Integer loginId
	 * @return DoctorSign
	 */
	@RequestMapping(value = "/FetchUserSignDetails",method = RequestMethod.GET)
	public DoctorSign getUserSignDetails(@RequestParam(value="loginId") Integer loginId) throws Exception{
		logger.debug("Begin request processing - to Fetch user signature details.");
		DoctorSign signaturedetails = signaturePrintService.getUserSignDetails(loginId);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PrintingAndReporting,AuditLogConstants.VIEWED,1,AuditLogConstants.UPDATE,"Successfully fetched user sign details print",sessionMap.getUserID(),"",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.PrintingAndReporting,request,"Successfully fetched user sign details");
		logger.debug("End of request to fetch user signature details.");
		return signaturedetails;
		
	}

	/**
	 * Request to get the list of all employee for configuration of sign footer notes
	 * 
	 */
	@RequestMapping(value = "/FetchEmployeeList",method = RequestMethod.GET)
	public List<EmployeeProfile> fetchEmployeeList() throws Exception{
		logger.debug("Begin request processing - to get the list of all employee.");
		List<EmployeeProfile> employeeList= employeeService.getEmployeeDetails("all", "asc");
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PrintingAndReporting,AuditLogConstants.VIEWED,1,AuditLogConstants.SUCCESS,"Successfully loaded employee list for configuration",sessionMap.getUserID(),"",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.PrintingAndReporting,request,"Successfully loaded employee list for configuration");
		logger.debug("End of request to get the list of all employee.");
		return employeeList;

	}
	
	/**
	 * Request to save user signature details
	 * 
	 */
			
	@RequestMapping(value = "/saveUserSignDetails",method = RequestMethod.POST)
	public DoctorSign saveUserSignDetails(@RequestParam(value="loginId") Integer loginId,
			@RequestParam(value="signStyle") String signStyle,
			@RequestParam(value="signFooter") String signFooter) throws Exception{
		logger.debug("Begin of request to Save user sign details.");
		
		DoctorSign signaturedetails = signaturePrintService.getUserSignDetails(loginId);
		
		DoctorSign newDoctorSign=new DoctorSign();
		newDoctorSign.setLoginid(loginId);
		newDoctorSign.setSignfooter(signFooter);
		newDoctorSign.setSignstyle(signStyle);
		if(signaturedetails!=null){
			newDoctorSign.setFilename(signaturedetails.getFilename());
			newDoctorSign.setSignaccess(signaturedetails.getSignaccess());
			newDoctorSign.setSignatureid(signaturedetails.getSignatureid());			
		}else{
			newDoctorSign.setFilename("");
			newDoctorSign.setSignaccess(null);
			newDoctorSign.setSignatureid(loginId);
		}
		DoctorSign doctorSign = signaturePrintService.saveUserSignDetails(newDoctorSign);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PrintingAndReporting,AuditLogConstants.CREATED,1,AuditLogConstants.SUCCESS,"Successfully saved user sign details",sessionMap.getUserID(),"",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.PrintingAndReporting,request,"Successfully saved user sign details");
		logger.debug("End of request to Save user sign details.");
		return doctorSign;
		
	}
	
}