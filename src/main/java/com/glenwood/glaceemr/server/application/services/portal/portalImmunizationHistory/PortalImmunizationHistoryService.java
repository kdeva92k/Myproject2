package com.glenwood.glaceemr.server.application.services.portal.portalImmunizationHistory;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.ChartStatus;
import com.glenwood.glaceemr.server.application.models.ImmunizationRecord;
import com.glenwood.glaceemr.server.application.models.LabDescription;
import com.glenwood.glaceemr.server.application.models.PatientImmunizationInformation;
import com.glenwood.glaceemr.server.application.models.PortalImmunizationHistoryBean;
import com.glenwood.glaceemr.server.application.models.Vaccine;
import com.glenwood.glaceemr.server.application.models.VaccineUpdateBean;
import com.glenwood.glaceemr.server.application.models.Vis;

public interface PortalImmunizationHistoryService {

	
	List<ImmunizationRecord> getPatientImmunizationHistory(int patientId, int chartId, int pageOffset, int pageIndex);
	
	List<Vis> getVaccineVISFilesList(String labDescCVX);
	
	List<Vaccine> getVaccineList(String searchKey);
	
	List<ChartStatus> getVaccUpdateReasonList();
	
	PatientImmunizationInformation requestVaccineUpdate(VaccineUpdateBean vaccineUpdateBean);
}
