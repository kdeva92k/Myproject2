package com.glenwood.glaceemr.server.application.services.chart.admission;

import java.sql.Date;
import java.util.List;

import org.json.JSONException;

import com.glenwood.glaceemr.server.application.models.Admission;
import com.glenwood.glaceemr.server.application.models.AdmissionRoom;
import com.glenwood.glaceemr.server.application.models.Encounter;

public interface AdmissionService  {

	String saveAdmission(AdmissionBean admission);

	Admission getAdmission(Integer patientId);

	String dischargePatient(Integer patientId,Integer loginId,Integer userId);
	
	String dischargePatientDetails(Integer admissionId,Integer patientId,Integer loginId,Integer userId, String DischargeDate, String dispositionvalue, String dispositionText);
	
	Admission getPastAdmission(Integer admissionId);

	List<Admission> getLeafDetails(Integer encounterId, Integer userId);

	Encounter openAdmissionLeaf(AdmissionBean admission);

	String getAdmissionEncDetails(Integer admssEpisode);

	AdmissionLeafBean getAdmissionLeafs(Integer admssEpisode);

	List<AdmissionRoom> getRooms(Integer blockId);	

	Admission getAdmissionPast(Integer patientId);

	List<String> getPastAdmissionDates(Integer patientId);
	
	List<Object[]> getdischargeValues(Integer admissionId,Integer patientId);

	void saveDishcargeDetails(AdmissionBean dataJson) throws JSONException;
	
}
