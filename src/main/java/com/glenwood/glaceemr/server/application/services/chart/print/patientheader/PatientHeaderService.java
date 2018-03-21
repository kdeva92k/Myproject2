package com.glenwood.glaceemr.server.application.services.chart.print.patientheader;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.print.PatientHeader;
import com.glenwood.glaceemr.server.application.models.print.PatientHeaderDetails;

public interface PatientHeaderService {

	List<PatientHeader> getPatientHeaderList();
	PatientHeader getPatientHeader(int patientHeaderId);
	List<PatientHeaderDetails> getPatientHeaderDetailList(int patientHeaderId,int pageId);
	PatientHeader savePatientHeader(PatientHeader patientHeader);
	Integer getPatientHeaderAttributeCount(int patientHeaderId,int pageId);
	void deletePatientHeaderDetails(List<PatientHeaderDetails> PatientHeaderDetailsList);
	void savePatientHeaderDetails(PatientHeaderDetails patientHeaderDetails);
}
