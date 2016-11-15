package com.glenwood.glaceemr.server.application.services.portal.portalSettings;

import java.text.ParseException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EncryptedPatientDetails;
import com.glenwood.glaceemr.server.application.models.InitialSettings;
import com.glenwood.glaceemr.server.application.models.InsuranceFilterBean;
import com.glenwood.glaceemr.server.application.models.PatientPortalMenuConfig;
import com.glenwood.glaceemr.server.application.models.PortalNotificationAlertsBean;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistrationBean;
import com.glenwood.glaceemr.server.application.models.PosTable;

public interface PortalSettingsService {

	
	PatientProfileSettingsFields getPatientProfileSettingsFieldsList();
	
	List<PosTable> getActivePosList();
	
	List<EmployeeProfile> getProvidersList();
	
	PortalBillingConfigFields getPortalBillingConfigFields();

	PatientRegistration saveDemographicChanges(PatientRegistrationBean regDetailsBean);
	
	EncryptedPatientDetails getActiveSessionForOldEMR(int patientId, int chartId) throws Exception;
	
	List<InitialSettings> getPracticeDetails();
	
	InitialSettings getPracticeDetailsItem(String optionName);

	PortalNotificationAlertsBean getPortalConfigDetails(int patientId, int chartId) throws ParseException, JsonProcessingException;
	
	List<PatientPortalMenuConfig> getPortalMenuConfig(boolean isActiveMenuItemList);

	InsuranceFilterBean getInsuranceListList(InsuranceFilterBean insFilterBean);
	
}
