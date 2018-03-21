package com.glenwood.glaceemr.server.application.services.portal.portalForms;

import java.io.IOException;
import java.util.List;

import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsQuestions;
import com.glenwood.glaceemr.server.application.models.ClinicalQuestionsGroup;
import com.glenwood.glaceemr.server.application.models.FileDetails;
import com.glenwood.glaceemr.server.application.models.LeafXmlVersion;
import com.glenwood.glaceemr.server.application.models.TabLibrary;

public interface PortalFormsService {
	
	PortalClinicalIntakeAndConsentFormsListBean getPatientClinicalIntakeAndConsentFormsList(int patientId, int chartId, int isXML);
	
	List<ClinicalQuestionsGroup> getPatientClinicalIntakeFormsList(int isXML);

	List<ClinicalElementsQuestions> getLibraryQustionnaireElements(int patientId,int groupId);
	
	ClinicalIntakeXMLBean getClinicalIntakePatientXml(int patientId,int requestId,int mode,int groupId,String groupName, int isXML, int retXML,int tabId, int limit,int pageNo) throws Exception;
		
	ClinicalIntakeXMLBean getXMLIntakeForm(int isXML, int retXML, int patientId, int requestId, int tabId, int chartID, int groupId, String groupName);
	
	TabLibrary getXMLTabData(int tabId);
	
	LeafXmlVersion getLeafXMLDetails(int tabId, String gender, int patientAge, int category);
	
	List<FileDetails> getIncompletePatientConsentFormsList(int patientId, int chartId);
	
	PortalConsentFormDetailsBean getIncompletePatientConsentFormsDetails(int patientId, int chartId, int fileDetailId) throws IOException;
		
	PortalConsentFormDetailsBean saveConsentAsPDF(PortalConsentFormDetailsBean portalConsentFormDetailsBean);
	
	AlertEvent savePatientConsentForm(PortalConsentFormDetailsBean consentSaveBean) throws IOException;

	List<FileDetails> getAllPatientConsentFormsList(int patientId, int chartId);

	List<FileDetails> getSignedPatientConsentFormsList(int patientId, int chartId);
	
	
}
