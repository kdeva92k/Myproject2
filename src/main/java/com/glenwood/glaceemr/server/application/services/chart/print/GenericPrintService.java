package com.glenwood.glaceemr.server.application.services.chart.print;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.InitialSettings;
import com.glenwood.glaceemr.server.application.models.LeafLibrary;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.print.GenericPrintStyle;
import com.glenwood.glaceemr.server.application.services.employee.EmployeeDataBean;
import com.glenwood.glaceemr.server.application.services.pos.PosDataBean;

public interface GenericPrintService {

	public List<GenericPrintStyle> getGenericPrintStyleList();
	public GenericPrintStyle getGenericPrintStyle(Integer styleId);
	public GenericPrintStyle saveGenericPrintStyle(GenericPrintStyle genericPrintStyle);
	public void generatePDFPreview(Integer styleId);
	public void generatePDFPreview(Integer styleId,Integer patientId);
	public void generatePDFPrint(Integer styleId,Integer patientId,PrintDetailsDataBean databean);
	public List<InitialSettings> getPracticeDetails();
	/*
	 * <b> Purpose: </b> To get patient details, employee details and pos details
	 */
	public GenericPrintBean getCompleteDetails(Integer patientId, Integer encounterId) throws Exception;
	String getHeaderHTML(Integer styleId, Integer patientId, Integer encounterId, String sharedPath) throws Exception;
	String getFooterHTML(Integer styleId);
	String getPatientHeaderHTML(Integer styleId, Integer patientId, Integer encounterId) throws Exception;
	public PatientRegistration getPatientData(int patientId);
	public Encounter getEncounterData(int encounterId);
	public List<PosDataBean> parsePOSDetails(List<PosTable> posList);
	public List<EmployeeDataBean> parseEmployeeDetails(List<EmployeeProfile> empList) throws Exception; 
	public PosDataBean parsePOSDetail(PosTable posList);
	public EmployeeDataBean parseEmployeeDetail(EmployeeProfile empList) throws Exception;
	
	
	PatientRegistration getTesData(int patientId);
	public String getLeftHeaderHTML(Integer styleId);
	CustomGenericBean getCustomeGenericData(Integer patientId, Integer encounterId, Integer styleId, String sharedFolderPath) throws Exception;
	public void saveLeafLibrary(LeafLibrary leafLibrary);
	public List<LeafLibrary> getTemplatesList();
	public List<LeafLibrary> getStyleTemplatesList(Integer styleId);
	public LeafLibrary getLeafLibrary(int templateId);
	public List<LeafLibrary> getLeafLibraryStyle(Integer styleId);
	EmployeeDataBean parseEmployeeDetail1(EmployeeDataBean emp)
			throws Exception;
	
	
}
