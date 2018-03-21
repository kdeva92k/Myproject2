package com.glenwood.glaceemr.server.application.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
  
import com.glenwood.glaceemr.server.application.models.implanteddevices.ImplantedDevice;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditLogConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailService;
import com.glenwood.glaceemr.server.application.services.implanteddevices.ImplantedDeviceService;
import com.glenwood.glaceemr.server.utils.SessionMap;

@RestController
@RequestMapping(value="/user/ImplantedDevice.Action")
public class ImplantedDeviceController {
	
	@Autowired
	ImplantedDeviceService implantedDeviceService;
	
	@Autowired
	AuditTrailService auditTrailService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	private Logger logger = Logger.getLogger(ImplantedDeviceController.class);
	
	/**
	 * Request to get the list of all implanted devices entered for a patient
	 * 
	 */
	@RequestMapping(value = "/FetchImplantedDeviceList",method = RequestMethod.GET)
	public List<ImplantedDevice> fetchImplantedDeviceList(@RequestParam(value="patientId") Integer patientId) throws Exception{
		logger.debug("Begin of request to get the list of all implanted devices entered for a patient.");
		List<ImplantedDevice> implantedDeviceList = implantedDeviceService.getImplantedDeviceList(patientId);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.VIEWED,1,AuditLogConstants.SUCCESS,"Successfully loaded implanted devices entered for a patient",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Chart,request,"Successfully loaded implanted devices entered for a patient");
		logger.debug("End of request to get the list of all implanted devices entered for a patient.");
		return implantedDeviceList;
		
	}
	
	/**
	 * Request to get company list
	 * 
	 */
	@RequestMapping(value = "/FetchCompanyList",method = RequestMethod.GET)
	public String fetchCompanyList() throws Exception{
		logger.debug("Begin of request to get company list.");
		String companyList = implantedDeviceService.getCompanyList();
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.VIEWED,1,AuditLogConstants.SUCCESS,"Successfully loaded implanted devices details",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Chart,request,"Successfully loaded implanted devices details");
		logger.debug("End of request to get company list.");
		return companyList;
		
	}
	
	/**
	 * Request to get brand list based on company name
	 * 
	 */
	@RequestMapping(value = "/FetchBrandListBasedOnCompany",method = RequestMethod.GET)
	public String fetchBrandListBasedOnCompany(@RequestParam(value="companyName") String companyName) throws Exception{
		logger.debug("Begin of request to get brand list.");
		String brandList = implantedDeviceService.getBrandListBasedOnCompany(companyName);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.VIEWED,1,AuditLogConstants.SUCCESS,"Successfully loaded implanted devices details",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Chart,request,"Successfully loaded implanted devices details");
		logger.debug("End of request to get brand list.");
		return brandList;
		
	}
	
	/**
	 * Request to get model list based on brand name and company name
	 * 
	 */
	@RequestMapping(value = "/FetchModelListBasedOnBrand",method = RequestMethod.GET)
	public String fetchModelListBasedOnBrand(@RequestParam(value="brandName") String brandName,@RequestParam(value="companyName") String companyName) throws Exception{
		logger.debug("Begin of request to get model list.");
		String modelList = implantedDeviceService.getModelListBasedOnBrand(brandName,companyName);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.VIEWED,1,AuditLogConstants.SUCCESS,"Successfully loaded implanted devices details",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Chart,request,"Successfully loaded implanted devices details");
		logger.debug("End of request to get model list.");
		return modelList;
		
	}
	
	
	/**
	 * Request to get device details based on UDI
	 * 
	 */
	@RequestMapping(value = "/FetchImplantedDeviceBasedOnUDI",method = RequestMethod.GET)
	public String fetchImplantedDeviceBasedOnUDI(@RequestParam(value="udi")String udi) throws Exception{
		logger.debug("Begin of request to get device details based on UDI.");
		String implantedDeviceDetails ="";
		implantedDeviceDetails = implantedDeviceService.getDeviceDetailsBasedOnUDI(udi);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.VIEWED,1,AuditLogConstants.SUCCESS,"Successfully loaded implanted devices details",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Chart,request,"Successfully loaded implanted devices details");
		logger.debug("End of request to get device details based on UDI.");
		return implantedDeviceDetails;
		
	}
	
	/**
	 * Request to get device details based on DI
	 * 
	 */
	@RequestMapping(value = "/FetchImplantedDeviceBasedOnDI",method = RequestMethod.GET)
	public String fetchImplantedDeviceBasedOnDI(@RequestParam(value="di")String di) throws Exception{
		logger.debug("Begin of request to get device details based on DI.");
		String implantedDeviceDetails = implantedDeviceService.getDeviceDetailsBasedOnDI(di);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.VIEWED,1,AuditLogConstants.SUCCESS,"Successfully loaded implanted devices details",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Chart,request,"Successfully loaded implanted devices details");
		logger.debug("End of request to get device details based on DI.");
		return implantedDeviceDetails;
		
	}
		
	/**
	 * Request to get the list of implanted devices entered for a patient based on status requested
	 * 
	 */
	@RequestMapping(value = "/FetchImplantedDeviceListWithStatusReq",method = RequestMethod.GET)
	public List<ImplantedDevice> fetchImplantedDeviceListWithStatusReq(@RequestParam(value="patientId") Integer patientId,@RequestParam(value="statusId") Byte statusId) throws Exception{
		logger.debug("Begin of request to get the list of implanted devices entered for a patient.");
		List<ImplantedDevice> implantedDeviceList = implantedDeviceService.getImplantedDeviceListWithStatusReq(patientId,statusId);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.VIEWED,1,AuditLogConstants.SUCCESS,"Successfully loaded implanted devices entered for a patient",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Chart,request,"Successfully loaded implanted devices entered for a patient");
		logger.debug("End of request to get the list of implanted devices entered for a patient.");
		return implantedDeviceList;
		
	}
	
	/**
	 * Request to get implanted device details based on id 
	 * 
	 */
	@RequestMapping(value = "/FetchImplantedDeviceDetails",method = RequestMethod.GET)
	public ImplantedDevice fetchImplantedDeviceDetails(@RequestParam(value="implantedDeviceId") Integer implantedDeviceId) throws Exception{
		logger.debug("Begin of request to get implanted device details.");
		ImplantedDevice implantedDevice = implantedDeviceService.getImplantedDeviceDetails(implantedDeviceId);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.VIEWED,1,AuditLogConstants.SUCCESS,"Successfully fetched implanted device details",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Chart,request,"Successfully fetched implanted device details");
		logger.debug("End of request to get implanted device details.");
		return implantedDevice;

	}
	
	/**
	 * Request to update device status
	 * 
	 */
	@RequestMapping(value = "/UpdateDeviceStatus",method = RequestMethod.GET)
	public ImplantedDevice updateDeviceStatus(@RequestParam(value="deviceId") Integer deviceId,@RequestParam(value="status") Byte status,@RequestParam(value="modifiedBy") Integer modifiedBy) throws Exception{
		logger.debug("Begin of request to update implanted device status.");
		ImplantedDevice implantedDevice = implantedDeviceService.getImplantedDeviceDetails(deviceId);
		implantedDevice.setImplantedDeviceStatus(status);
		implantedDevice.setImplantedDeviceModifiedby(modifiedBy);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date  modifiedDate=formatter.parse((new Timestamp(System.currentTimeMillis())).toString());
		implantedDevice.setImplantedDeviceModifiedon(modifiedDate);
		implantedDeviceService.saveImplantedDeviceDetails(implantedDevice);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.UPDATE,1,AuditLogConstants.SUCCESS,"Successfully fetched implanted device details",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Chart,request,"Successfully fetched implanted device details");
		logger.debug("End of request to update implanted device status.");
		return implantedDevice;

	}
	
	/**
	 * Request to update device status
	 * 
	 */
	
	@RequestMapping(value = "/UpdateDeviceDetails",method = RequestMethod.GET)
	public ImplantedDevice updateDeviceDetails(@RequestParam(value="deviceId") Integer deviceId,@RequestParam(value="status") Byte status,@RequestParam(value="modifiedBy") Integer modifiedBy,@RequestParam(value="reason") String reason,@RequestParam(value="implantDate") String implantDate) throws Exception{
		logger.debug("Begin of request to update implanted device details.");
		ImplantedDevice implantedDevice = implantedDeviceService.getImplantedDeviceDetails(deviceId);
		implantedDevice.setImplantedDeviceStatus(status);
		implantedDevice.setImplantedDeviceReasonDeactivation(reason);
		if(!implantDate.equalsIgnoreCase("-1")){
			implantedDevice.setImplantedDeviceImplantDate(new Date(implantDate));
		}
		implantedDevice.setImplantedDeviceModifiedby(modifiedBy);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date  modifiedDate=formatter.parse((new Timestamp(System.currentTimeMillis())).toString());
		implantedDevice.setImplantedDeviceModifiedon(modifiedDate);
		implantedDeviceService.saveImplantedDeviceDetails(implantedDevice);
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.UPDATE,1,AuditLogConstants.SUCCESS,"Successfully fetched implanted device details",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Chart,request,"Successfully fetched implanted device details");
		logger.debug("End of request to update implanted device details.");
		return implantedDevice;

	}

	/**
	 * Request to save implanted device details
	 * 
	 */

	@RequestMapping(value = "/saveImplantedDeviceDetails",method = RequestMethod.POST, consumes = "application/json")
	public ImplantedDevice saveImplatedDeviceDetails(@RequestBody ImplantedDevice implantedDeviceData) throws Exception{
		ImplantedDevice savedimplantedDevice=new ImplantedDevice();
		try{
			logger.debug("Begin of request to save implanted  details.");
			if(implantedDeviceData.getImplantedDeviceUDI().equalsIgnoreCase("-1")){
				implantedDeviceData.setImplantedDeviceJSON(implantedDeviceService.getDeviceDetailsBasedOnDI(implantedDeviceData.getImplantedDeviceDI()));
			}else{
				implantedDeviceData.setImplantedDeviceJSON(implantedDeviceService.getDeviceDetailsBasedOnUDI(implantedDeviceData.getImplantedDeviceUDI()));
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date createdDate=formatter.parse((new Timestamp(System.currentTimeMillis())).toString());
			implantedDeviceData.setImplantedDeviceCreatedon(createdDate);
			implantedDeviceData=implantedDeviceService.saveImplantedDeviceDetails(implantedDeviceData);
			auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.CREATED,1,AuditLogConstants.SUCCESS,"Successfully fetched implanted device details",-1,"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.Chart,request,"Successfully fetched implanted device details");
			logger.debug("End of request to save implanted device details.");
		}catch(Exception e){
			e.printStackTrace();
		}

		return savedimplantedDevice;
	}
}