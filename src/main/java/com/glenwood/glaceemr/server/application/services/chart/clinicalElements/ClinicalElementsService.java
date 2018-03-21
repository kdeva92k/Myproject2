package com.glenwood.glaceemr.server.application.services.chart.clinicalElements;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping;
import com.glenwood.glaceemr.server.application.services.chart.HPI.ClinicalElementsOptionBean;

public interface ClinicalElementsService {
	
	public List<ClinicalElementsOptions> getClinicalElementOptions(String gwid);
	public ClinicalDataBean setVitalsClinicalData(String gwidPattern,Integer patientId,Integer encounterId,Boolean isDischargeVitals,Integer admssEpisode,short patientSex,Integer ageinDay);
	public void setVitalsClinicalDataLoadWithLastVisit(String gwidPattern,Integer patientId, Integer encounterId, Boolean isDischargeVitals,Integer admssEpisode, short patientSex, Integer ageinDay);
	public boolean isClinicalElemActive(String gwid);
	public ClinicalTextMapping getClinicalTextMapping(String gwid);
	public ClinicalElements getClinicalElement(String gwid);
	public List<ClinicalElements> setClinicalDataBean(Integer patientId,Integer encounterId,Integer templateId,Integer tabType,String gwidPattern);
	public List<ClinicalElementBean> setClinicalDataBeans(Integer patientId,Integer encounterId,Integer templateId,Integer tabType,String gwidPattern);
	public List<String> delPatientElementByEncID(Integer patientId,Integer encounterId, Integer tabId,Integer templateId, Integer prevEncounterId);
	public void deleteNotesData(Integer patientId, Integer encounterId,Integer tabId, Integer templateId);
	public void insertDataForImport(Integer patientId,Integer encounterId,Integer prevEncounterId, Integer tabId, Integer templateId,List<String> mappedGwids);
	public List<ClinicalElements> setHistoryClinicalDataBean(Integer patientId,Integer encounterId,Integer templateId,Integer tabType,String gwidPattern);
	public List<ClinicalElementsOptionBean> getSymptomClinicalElementOptions(
			String elementGWId, Integer patientId, Integer encounterId,
			int patientGender, int ageinDay, boolean isAgeBased, int i);
	public List<ClinicalElementsOptionBean> getSymptomElementOptionAfterUnion(
			String elementGWId, Integer patientId, Integer encounterId,
			int patientGender, int ageinDay, boolean isAgeBased, int flag);

}
