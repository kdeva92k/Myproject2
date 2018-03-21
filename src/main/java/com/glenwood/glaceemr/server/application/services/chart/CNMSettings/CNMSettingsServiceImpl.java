package com.glenwood.glaceemr.server.application.services.chart.CNMSettings;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.CnmSettings;
import com.glenwood.glaceemr.server.application.repositories.CnmSettingsRepository;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.utils.SessionMap;



@Service
public class CNMSettingsServiceImpl implements CNMSettingsService{

	@Autowired
	CnmSettingsRepository cnmSettingsRepository;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
	
	@Override
	public boolean getCNMSettingsIsActiveById(Integer id) {
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CONFIGURATION, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "CNM settings viewed by id @Id="+id, sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return getCNMSetting(cnmSettingsRepository.findAll(CNMSettingsSpecification.getCNMSettingsById(id)));
		
		
	}

	@Override
	public boolean getCNMSettingsIsActiveByName(String name) {
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CONFIGURATION, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "CNM settings viewed by name @name="+name, sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return getCNMSetting(cnmSettingsRepository.findAll(CNMSettingsSpecification.getCNMSettingsByName(name)));
	}

	@Override
	public boolean getCNMSettingsIsActiveByIdAndName(Integer id, String name) {
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CONFIGURATION, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "CNM settings viewed by id and name @Id="+id+" @name="+name, sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return getCNMSetting(cnmSettingsRepository.findAll(CNMSettingsSpecification.getCNMSettingsByIdAndName(id, name)));
	}
	
	
	private boolean getCNMSetting(List<CnmSettings> cnmSettings) {
		if(cnmSettings.size()>0){
			return cnmSettings.get(0).getCnmSettingsIsactive();
		}else{
			return false;
		}
		
		
	}
	
}
