package com.glenwood.glaceemr.server.application.services.chart.clinicalElements;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalHistory;
import com.glenwood.glaceemr.server.application.models.PatientEpisodeAttributeDetails;
import com.glenwood.glaceemr.server.application.models.cnm.history.PatientClinicalElementsBean;
import com.glenwood.glaceemr.server.application.services.chart.SaveClinicalData.ElementsToDeleteBean;
import com.glenwood.glaceemr.server.application.services.chart.SaveClinicalData.ElementsToSaveBean;
import com.glenwood.glaceemr.server.application.services.chart.SaveClinicalData.PatientEpisodeElementBean;
import com.glenwood.glaceemr.server.application.services.chart.SaveClinicalData.PatientHistoryElementBean;
import com.glenwood.glaceemr.server.utils.HUtil;


@Component
public class ClinicalDataBean {

	@Autowired
	ElementQueryFactory elementQueryFactory;

	public HashMap <String, ClinicalElementBean> clinicalElements=null;
	public HashMap <String,PatientElementBean> patientElements=null;
	public HashMap <String,PatientHistoryElementBean> patientHistoryElements=null;
	public HashMap <String,PatientEpisodeElementBean> patientEpisodeElements=null;
	public HashMap <String,ElementsToSaveBean> elementsToSave=null;
	public HashMap <String,ElementsToDeleteBean> elementsToDelete=null;
	public String clientId="000";

	
	public ClinicalDataBean(){
		clinicalElements=new HashMap<String, ClinicalElementBean>();
		patientElements=new HashMap<String, PatientElementBean>();
		patientHistoryElements=new HashMap<String, PatientHistoryElementBean>();
		patientEpisodeElements=new HashMap<String, PatientEpisodeElementBean>();
		elementsToSave=new HashMap<String, ElementsToSaveBean>();
		elementsToDelete=new HashMap<String, ElementsToDeleteBean>();
	}



	/**
	 * Sets clinical element details to {@link ClinicalElementBean} 
	 * 
	 * @param elementList
	 * 
	 */
	public void setClinicalData(List<ClinicalElements> elementList) {
		for (ClinicalElements element : elementList) {
			ClinicalElementBean clinicalElementBean=new ClinicalElementBean();
			clinicalElementBean.setClinicalElementName(HUtil.Nz(element.getClinicalElementsName(),""));
			clinicalElementBean.setClinicalElementNotes(HUtil.Nz(element.getClinicalElementsNotes(),""));
			clinicalElementBean.setClinicalElementDataType(Integer.parseInt(HUtil.Nz(element.getClinicalElementsDatatype(),"-1")));
			clinicalElementBean.setClinicalElementCPT(HUtil.Nz(element.getClinicalElementsCptcode(),""));
			clinicalElementBean.setClinicalElementICD9(HUtil.Nz(element.getClinicalElementsIcd9code(),""));
			clinicalElementBean.setClinicalElementSNOMED(HUtil.Nz(element.getClinicalElementsSnomed(),""));
			clinicalElementBean.setClinicalElementIsActive(Boolean.parseBoolean(HUtil.Nz(element.getClinicalElementsIsactive(),"false")));
			clinicalElementBean.setClinicalElementIsGlobal(Boolean.parseBoolean(HUtil.Nz(element.getClinicalElementsIsglobal(),"false")));
			clinicalElementBean.setClinicalElementIsHistory(Boolean.parseBoolean(HUtil.Nz(element.getClinicalElementsIshistory(),"false")));
			clinicalElementBean.setClinicalElementIsEpisode(Boolean.parseBoolean(HUtil.Nz(element.getClinicalElementsIsepisode(),"false")));
			clinicalElementBean.setClinicalElementTextDimension(HUtil.Nz(element.getClinicalElementsTextDimension(),""));
			clinicalElementBean.setClinicalElementGender(Integer.parseInt(HUtil.Nz(element.getClinicalElementsGender(),"false")));
			clinicalElementBean.setClinicalElementIsSelect(Integer.parseInt(HUtil.Nz(element.getClinicalElementsIsselect(),"-1")));
			
		
			if(element.getClinicalTextMappings() != null)
			for (ClinicalTextMapping clinicalTextMapping : element.getClinicalTextMappings()) {
				ClinicalTextMapping mapping = new ClinicalTextMapping();	
				mapping.setClinicalTextMappingAssociatedElement(HUtil.Nz(clinicalTextMapping.getClinicalTextMappingAssociatedElement(),""));
				mapping.setClinicalTextMappingId(Integer.parseInt(HUtil.Nz(clinicalTextMapping.getClinicalTextMappingId(),"-1")));
				mapping.setClinicalTextMappingIsdate(Boolean.parseBoolean(HUtil.Nz(clinicalTextMapping.getClinicalTextMappingIsdate(),"false")));
				mapping.setClinicalTextMappingPopupType(Integer.parseInt(HUtil.Nz(clinicalTextMapping.getClinicalTextMappingPopupType(),"-1")));
				mapping.setClinicalTextMappingTextboxGwid(HUtil.Nz(clinicalTextMapping.getClinicalTextMappingTextboxGwid(),""));
				clinicalElementBean.getClinicalTextMappings().add(clinicalTextMapping);
			}
			
			this.setClinicalElements(HUtil.Nz(element.getClinicalElementsGwid(),"-1"), clinicalElementBean);
		
		}
	}
	
	/**
	 * Sets clinical element details to {@link ClinicalElementBean} 
	 * 
	 * @param elementList
	 * 
	 */
	public void setClinicalDataBean(List<ClinicalElementBean> elementList) {
		if(elementList != null)
		for (ClinicalElementBean element : elementList) {
			ClinicalElementBean clinicalElementBean=new ClinicalElementBean();
			clinicalElementBean.setClinicalElementName(HUtil.Nz(element.getClinicalElementName(),""));
			clinicalElementBean.setClinicalElementNotes(HUtil.Nz(element.getClinicalElementNotes(),""));
			clinicalElementBean.setClinicalElementDataType(Integer.parseInt(HUtil.Nz(element.getClinicalElementDataType(),"-1")));
			clinicalElementBean.setClinicalElementCPT(HUtil.Nz(element.getClinicalElementCPT(),""));
			clinicalElementBean.setClinicalElementICD9(HUtil.Nz(element.getClinicalElementICD9(),""));
			clinicalElementBean.setClinicalElementSNOMED(HUtil.Nz(element.getClinicalElementSNOMED(),""));
			clinicalElementBean.setClinicalElementIsActive(Boolean.parseBoolean(HUtil.Nz(element.getClinicalElementIsActive(),"false")));
			clinicalElementBean.setClinicalElementIsGlobal(Boolean.parseBoolean(HUtil.Nz(element.getClinicalElementIsGlobal(),"false")));
			clinicalElementBean.setClinicalElementIsHistory(Boolean.parseBoolean(HUtil.Nz(element.getClinicalElementIsHistory(),"false")));
			clinicalElementBean.setClinicalElementIsEpisode(Boolean.parseBoolean(HUtil.Nz(element.getClinicalElementIsEpisode(),"false")));
			clinicalElementBean.setClinicalElementTextDimension(HUtil.Nz(element.getClinicalElementTextDimension(),""));
			clinicalElementBean.setClinicalElementGender(Integer.parseInt(HUtil.Nz(element.getClinicalElementGender(),"false")));
			clinicalElementBean.setClinicalElementIsSelect(Integer.parseInt(HUtil.Nz(element.getClinicalElementIsSelect(),"-1")));
			
		
			if(element.getClinicalTextMappings() != null)
			for (ClinicalTextMapping clinicalTextMapping : element.getClinicalTextMappings()) {
				ClinicalTextMapping mapping = new ClinicalTextMapping();	
				mapping.setClinicalTextMappingAssociatedElement(HUtil.Nz(clinicalTextMapping.getClinicalTextMappingAssociatedElement(),""));
				mapping.setClinicalTextMappingId(Integer.parseInt(HUtil.Nz(clinicalTextMapping.getClinicalTextMappingId(),"-1")));
				mapping.setClinicalTextMappingIsdate(Boolean.parseBoolean(HUtil.Nz(clinicalTextMapping.getClinicalTextMappingIsdate(),"false")));
				mapping.setClinicalTextMappingPopupType(Integer.parseInt(HUtil.Nz(clinicalTextMapping.getClinicalTextMappingPopupType(),"-1")));
				mapping.setClinicalTextMappingTextboxGwid(HUtil.Nz(clinicalTextMapping.getClinicalTextMappingTextboxGwid(),""));
				clinicalElementBean.getClinicalTextMappings().add(clinicalTextMapping);
			}
			
			this.setClinicalElements(HUtil.Nz(element.getClinicalElementGWID(),"-1"), clinicalElementBean);
		
		}
	}


	/**
	 * 
	 * Sets Patient Clinical Data to {@link PatientElementBean}
	 * 
	 * @param patientElementData
	 */
	public void setPatientClinicalData(List<PatientClinicalElements> patientElementData) {
		if(patientElementData!=null)
		for (PatientClinicalElements patData : patientElementData) {
			PatientElementBean patElmtBean=new PatientElementBean();
			String clinicalElementGWId=HUtil.Nz(patData.getPatientClinicalElementsGwid(),"-1");
			int clinicalElementDataType=Integer.parseInt(HUtil.Nz(patData.getClinicalElement().getClinicalElementsDatatype(),"-1"));
			patElmtBean.setPatientClinicalElementId(Integer.parseInt(HUtil.Nz(patData.getPatientClinicalElementsId(),"-1")));
			String elementData=HUtil.Nz(patData.getPatientClinicalElementsValue(),"-1").trim();
			setPatientElementBeanData(clinicalElementDataType,patElmtBean,elementData,clinicalElementGWId);
		}
	}
	
	/**
	 * 
	 * Sets Patient Clinical Data to {@link PatientElementBean}
	 * 
	 * @param patientElementData
	 */
	public void setPatientClinicalDataBean(List<PatientClinicalElementsBean> patientElementData) {
		if(patientElementData!=null)
		for (PatientClinicalElementsBean patData : patientElementData) {
			PatientElementBean patElmtBean=new PatientElementBean();
			String clinicalElementGWId=HUtil.Nz(patData.getPatientClinicalElementsGwid(),"-1");
			int clinicalElementDataType=Integer.parseInt(HUtil.Nz(patData.getClinicalElementsDatatype(),"-1"));
			patElmtBean.setPatientClinicalElementId(Integer.parseInt(HUtil.Nz(patData.getPatientClinicalElementsId(),"-1")));
			String elementData=HUtil.Nz(patData.getPatientClinicalElementsValue(),"-1").trim();
			setPatientElementBeanData(clinicalElementDataType,patElmtBean,elementData,clinicalElementGWId);
		}
	}
	
	
	/**
	 * 
	 * Sets Patient Vitals Data to {@link PatientElementBean}
	 * 
	 * @param patientElementData
	 *//*
	public void setPatientVitalsClinicalData(List<PatientVitals> patientVitalsElementData) {
		for (PatientVitals patData : patientVitalsElementData) {
			PatientElementBean patElmtBean=new PatientElementBean();
			String clinicalElementGWId=HUtil.Nz(patData.getPatientVitalsGwid(),"-1");
			int clinicalElementDataType=Integer.parseInt(HUtil.Nz(patData.getClinicalElement().getClinicalElementsDatatype(),"-1"));
			patElmtBean.setPatientClinicalElementId(Integer.parseInt(HUtil.Nz(patData.getPatientVitalsId(),"-1")));
			String elementData=HUtil.Nz(patData.getPatientVitalsValue(),"-1").trim();
			setPatientElementBeanData(clinicalElementDataType,patElmtBean,elementData,clinicalElementGWId);
		}
	}*/



	/**
	 * 
	 * Sets Patient History Data to {@link PatientElementBean}
	 * 
	 * @param patientHistoryData
	 */
	public void setHistoryDatatoPatElementBean(List<PatientClinicalHistory> patientHistoryData) {
		if(patientHistoryData != null)
		for (PatientClinicalHistory patHisData : patientHistoryData) {
			PatientElementBean patElmtBean=new PatientElementBean();
			String clinicalElementGWId=HUtil.Nz(patHisData.getPatientClinicalHistoryGwid(),"-1");
			int clinicalElementDataType=Integer.parseInt(HUtil.Nz(patHisData.getClinicalElement().getClinicalElementsDatatype(),"-1"));
			patElmtBean.setPatientClinicalElementId(Integer.parseInt(HUtil.Nz(patHisData.getPatientClinicalHistoryId(),"-1")));
			String elementData=HUtil.Nz(patHisData.getPatientClinicalHistoryValue(),"-1").trim();
			setPatientElementBeanData(clinicalElementDataType,patElmtBean,elementData,clinicalElementGWId);
		}
	}

	/**
	 * 
	 * Sets Patient History Data to {@link PatientElementBean}
	 * 
	 * @param patientHistoryData
	 */
	public void setHistoryDatatoPatElement(List<PatientClinicalElementsBean> patientHistoryData) {
		if(patientHistoryData != null)
		for (PatientClinicalElementsBean patHisData : patientHistoryData) {
			PatientElementBean patElmtBean=new PatientElementBean();
			String clinicalElementGWId=HUtil.Nz(patHisData.getPatientClinicalElementsGwid(),"-1");
			int clinicalElementDataType=Integer.parseInt(HUtil.Nz(patHisData.getClinicalElementsDatatype(),"-1"));
			patElmtBean.setPatientClinicalElementId(Integer.parseInt(HUtil.Nz(patHisData.getPatientClinicalElementsHistoryId(),"-1")));
			String elementData=HUtil.Nz(patHisData.getPatientClinicalElementsValue(),"-1").trim();
			setPatientElementBeanData(clinicalElementDataType,patElmtBean,elementData,clinicalElementGWId);
		}
	}



	/**
	 * Sets Patient History Data to {@link PatientHistoryElementBean}
	 * 
	 * @param patientHistoryData
	 */
	public void setPatientHistoryData(List<PatientClinicalHistory> patientHistoryData) {

		for (PatientClinicalHistory patHisData : patientHistoryData) {
			PatientHistoryElementBean patHistoryElmtBean=new PatientHistoryElementBean();
			String clinicalElementGWId=HUtil.Nz(patHisData.getPatientClinicalHistoryGwid(),"-1");
			int clinicalElementDataType=Integer.parseInt(HUtil.Nz(patHisData.getClinicalElement().getClinicalElementsDatatype(),"-1"));
			patHistoryElmtBean.setpatientHistoryElementId(Integer.parseInt(HUtil.Nz(patHisData.getPatientClinicalHistoryId(),"-1")));
			String elementData=HUtil.Nz(patHisData.getPatientClinicalHistoryValue(),"-1").trim();
			setPatientHistoryBeanData(clinicalElementDataType, patHistoryElmtBean, elementData, clinicalElementGWId);
		}
	}





	/**
	 * Sets Patient Clinical Data to {@link PatientHistoryElementBean}
	 * 
	 * @param patientClinicalData
	 */
	public void setPatientClinData(List<PatientClinicalElements> patientClinicalData) {

		for (PatientClinicalElements patclinicalData : patientClinicalData) {
			PatientHistoryElementBean patHistoryElmtBean=new PatientHistoryElementBean();
			String clinicalElementGWId=HUtil.Nz(patclinicalData.getPatientClinicalElementsGwid(),"-1");
			int clinicalElementDataType=Integer.parseInt(HUtil.Nz(patclinicalData.getClinicalElement().getClinicalElementsDatatype(),"-1"));
			patHistoryElmtBean.setpatientHistoryElementId(Integer.parseInt(HUtil.Nz(patclinicalData.getPatientClinicalElementsId(),"-1")));
			String elementData=HUtil.Nz(patclinicalData.getPatientClinicalElementsValue(),"-1").trim();
			setPatientHistoryBeanData(clinicalElementDataType, patHistoryElmtBean, elementData, clinicalElementGWId);

		}
	}


	/**
	 * Sets Patient Clinical Data to {@link PatientHistoryElementBean}
	 * 
	 * @param patientClinicalData
	 */
	public void setPatientClinData1(List<PatientClinicalElementsBean> patientClinicalData) {

		for (PatientClinicalElementsBean patclinicalData : patientClinicalData) {
			PatientHistoryElementBean patHistoryElmtBean=new PatientHistoryElementBean();
			String clinicalElementGWId=HUtil.Nz(patclinicalData.getPatientClinicalElementsGwid(),"-1");
			int clinicalElementDataType=Integer.parseInt(HUtil.Nz(patclinicalData.getClinicalElementsDatatype(),"-1"));
			patHistoryElmtBean.setpatientHistoryElementId(Integer.parseInt(HUtil.Nz(patclinicalData.getPatientClinicalElementsId(),"-1")));
			String elementData=HUtil.Nz(patclinicalData.getPatientClinicalElementsValue(),"-1").trim();
			setPatientHistoryBeanData(clinicalElementDataType, patHistoryElmtBean, elementData, clinicalElementGWId);
			
		}
	}
	
	

	/**
	 * 
	 * Sets {@link PatientHistoryElementBean} based on clinical Element  Datatype
	 * 
	 * @param clinicalElementDataType
	 * @param patHistoryElmtBean
	 * @param elementData
	 * @param clinicalElementGWId
	 */
	private void setPatientHistoryBeanData(int clinicalElementDataType,PatientHistoryElementBean patHistoryElmtBean,String elementData,String clinicalElementGWId){
		if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_NUMBER){
			patHistoryElmtBean.setpatientHistoryElementNumber(Integer.parseInt(elementData));
		}else if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT){
			patHistoryElmtBean.setpatientHistoryElementText(elementData.replaceAll("#~#", "\r\n"));
		}else if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN){
			patHistoryElmtBean.setpatientHistoryElementBoolean(Boolean.parseBoolean(elementData));
		}else if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION || clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION){
			patHistoryElmtBean.setpatientHistoryElementOption(Integer.parseInt(elementData));
			if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION){
				clinicalElementGWId=clinicalElementGWId+"_"+elementData;
			}
		}
		this.setPatientHistoryElements(clinicalElementGWId,patHistoryElmtBean);
	}



	/**
	 * 
	 * Sets {@link PatientElementBean} based on clinical Element  Datatype
	 * 
	 * @param clinicalElementDataType
	 * @param patElmtBean
	 * @param elementData
	 * @param clinicalElementGWId
	 */
	private void setPatientElementBeanData(int clinicalElementDataType,PatientElementBean patElmtBean,String elementData,String clinicalElementGWId){

		if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_NUMBER){
			patElmtBean.setPatientClinicalElementNumber(Integer.parseInt(elementData));
		}else if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT){
			patElmtBean.setPatientClinicalElementText(elementData.replaceAll("#~#", "\r\n"));
		}else if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN){
			patElmtBean.setPatientClinicalElementBoolean(Boolean.parseBoolean(elementData));
		}else if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION || clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION){
			patElmtBean.setPatientClinicalElementOption(Integer.parseInt(elementData));
			if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION){
				clinicalElementGWId=clinicalElementGWId+"_"+elementData;
			}
		}
		else if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_OCX){
			patElmtBean.setPatientClinicalElementText(elementData);
		}
		this.setPatientElements(clinicalElementGWId, patElmtBean);
	}




	/**
	 * Sets patient Clinical Data to {@link PatientElementBean} 
	 * 
	 * @param patientClinicalElements
	 */
	public void setPatientBeanToSave(List<PatientClinicalElements> patientClinicalElements){
		for (PatientClinicalElements patClinicalElement : patientClinicalElements) {
			PatientElementBean patElmtBean=new PatientElementBean();
			String clinicalElementGWId=HUtil.Nz(patClinicalElement.getPatientClinicalElementsGwid(),"-1");
			if(this.getClinicalElements(clinicalElementGWId)!=null){
				int clinicalElementDataType=Integer.parseInt(HUtil.Nz(patClinicalElement.getClinicalElement().getClinicalElementsDatatype(),"-1"));
				String elementData=HUtil.Nz(patClinicalElement.getPatientClinicalElementsValue(),"-1").trim();
				if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION){
					clinicalElementGWId=clinicalElementGWId+"_"+elementData;
				}
				patElmtBean.setPatientClinicalElementGWId(clinicalElementGWId);
				patElmtBean.setPatientClinicalElementText(elementData.replaceAll("#~#", "\r\n"));
				this.setPatientElements(clinicalElementGWId, patElmtBean);
			}
		}

	}




	/**
	 * Sets patient Episode Element Data to {@link PatientEpisodeElementBean} 
	 * 
	 * @param patientClinicalElements
	 */
	public void setPatientEpisodeElementsToSave(List<PatientEpisodeAttributeDetails> patientEpisodeAttributeDetails){
		for (PatientEpisodeAttributeDetails patientEpisodeAttributeDet : patientEpisodeAttributeDetails) {
			PatientEpisodeElementBean patEpisodeElmtBean=new PatientEpisodeElementBean();
			String clinicalElementGWId=HUtil.Nz(patientEpisodeAttributeDet.getPatientEpisodeAttributeDetailsGwid(),"-1");
			if(this.getClinicalElements(clinicalElementGWId)!=null){
				patEpisodeElmtBean.setPatientEpisodeElementId(Integer.parseInt(HUtil.Nz(patientEpisodeAttributeDet.getPatientEpisodeAttributeDetailsEpisodeid(),"-1")));
				patEpisodeElmtBean.setPatientEpisodeElementGWId(Long.parseLong(HUtil.Nz(patientEpisodeAttributeDet.getPatientEpisodeAttributeDetailsGwid(),"-1")));
				String elementData=HUtil.Nz(patientEpisodeAttributeDet.getPatientEpisodeAttributeDetailsValue(),"-1").trim();
				patEpisodeElmtBean.setPatientEpisodeElementText(elementData.replaceAll("#~#", "\r\n"));
				this.setPatientEpisodeElements(clinicalElementGWId,patEpisodeElmtBean);
			}
		}
	}


	public ClinicalElementBean getClinicalElements(String elementId) {
		return clinicalElements.get(elementId);
	}
	public void setClinicalElements(String elementId,ClinicalElementBean elementBean) {
		this.clinicalElements.put(elementId, elementBean);
	}
	public PatientElementBean getPatientElements(String elementId) {
		return patientElements.get(elementId);
	}


	public HashMap<String, ClinicalElementBean> getClinicalElements() {
		return clinicalElements;
	}



	public void setClinicalElements(HashMap<String, ClinicalElementBean> clinicalElements) {
		this.clinicalElements = clinicalElements;
	}



	public HashMap<String, PatientElementBean> getPatientElements() {
		return patientElements;
	}



	public void setPatientElements(HashMap<String, PatientElementBean> patientElements) {
		this.patientElements = patientElements;
	}



	public HashMap<String, PatientHistoryElementBean> getPatientHistoryElements() {
		return patientHistoryElements;
	}



	public void setPatientHistoryElements(
			HashMap<String, PatientHistoryElementBean> patientHistoryElements) {
		this.patientHistoryElements = patientHistoryElements;
	}



	public HashMap<String, PatientEpisodeElementBean> getPatientEpisodeElements() {
		return patientEpisodeElements;
	}



	public void setPatientEpisodeElements(
			HashMap<String, PatientEpisodeElementBean> patientEpisodeElements) {
		this.patientEpisodeElements = patientEpisodeElements;
	}



	public HashMap<String, ElementsToSaveBean> getElementsToSave() {
		return elementsToSave;
	}



	public void setElementsToSave(HashMap<String, ElementsToSaveBean> elementsToSave) {
		this.elementsToSave = elementsToSave;
	}



	public HashMap<String, ElementsToDeleteBean> getElementsToDelete() {
		return elementsToDelete;
	}



	public void setElementsToDelete(
			HashMap<String, ElementsToDeleteBean> elementsToDelete) {
		this.elementsToDelete = elementsToDelete;
	}



	public void setPatientElements(String elementId,PatientElementBean patientElements) {
		this.patientElements.put(elementId,patientElements);
	}
	public void setPatientHistoryElements(String elementId,PatientHistoryElementBean patientHistoryElementBean) {
		this.patientHistoryElements.put(elementId,patientHistoryElementBean);
	}
	public PatientHistoryElementBean getPatientHistoryElements(String elementId) {
		return patientHistoryElements.get(elementId);
	}

	public void setPatientEpisodeElements(String elementId,PatientEpisodeElementBean patientEpisodeElementBean) {
		this.patientEpisodeElements.put(elementId,patientEpisodeElementBean);
	}


	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}



	public void createNewPlanReadInstance(String clientId, Integer patientId,
			Integer chartId, Integer encounterId, int tabType, Integer templateId,
			Short patientGender, Date patDOB, Integer ageinDay,
			String leafCreatedDate, Boolean isAgeBased) {
		
		loadClinicalElements(patientId,encounterId, "04",templateId,"-1",tabType, patientGender, patDOB, ageinDay, leafCreatedDate, isAgeBased, clientId);
		loadPatientClinicalElements(encounterId,patientId,false,"-1","04", tabType, chartId, patientGender, patDOB, ageinDay, leafCreatedDate, isAgeBased, clientId);
		
	}



	private void loadPatientClinicalElements(Integer encounterId,
			Integer patientId, boolean isHistory, String string, String gwidPattern,
			Integer tabType, Integer chartId, Short patientGender, Date patDOB,
			Integer ageinDay, String leafCreatedDate, Boolean isAgeBased, String clientId) {

		List<Object[]> list= elementQueryFactory.getPatientClinicalElementandHistory(patientId,encounterId,tabType, clientId, gwidPattern);
		for(int i=0; i<list.size(); i++){
			PatientElementBean patElmtBean=new PatientElementBean();

			String clinicalElementGWId=HUtil.Nz(list.get(i)[1],"-1");
			int clinicalElementDataType=Integer.parseInt(HUtil.Nz(list.get(i)[3],"-1"));
			patElmtBean.setPatientClinicalElementId(Integer.parseInt(HUtil.Nz(list.get(i)[0],"-1")));
			String elementData=HUtil.Nz(list.get(i)[2],"-1").trim();
			if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_NUMBER){
				patElmtBean.setPatientClinicalElementNumber(Integer.parseInt(elementData));
			}else if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT){
				patElmtBean.setPatientClinicalElementText(elementData.replaceAll("#~#", "\r\n"));
			}else if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN){
				patElmtBean.setPatientClinicalElementBoolean(Boolean.parseBoolean(elementData));
			}else if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION || clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION){
				patElmtBean.setPatientClinicalElementOption(Integer.parseInt(elementData));
				if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION){
					clinicalElementGWId=clinicalElementGWId+"_"+elementData;
				}
			}
			else if(clinicalElementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_OCX){
				patElmtBean.setPatientClinicalElementText(elementData);
			}
			System.out.println("this.setPatientElements:: "+clinicalElementGWId);
			this.setPatientElements(clinicalElementGWId, patElmtBean);

		}
	}



	private void loadClinicalElements(Integer patientId, Integer encounterId,
			String string, Integer templateId, String gwPattern, int tabType,
			Short patientGender, Date patDOB, Integer ageinDay,
			String leafCreatedDate, Boolean isAgeBased, String clientId) {
		
		Set<ClinicalElementBean> set= new HashSet<ClinicalElementBean>();
		
		List<ClinicalElementBean> list= elementQueryFactory.getClinicalElementsFirst(patientGender, gwPattern, patientId, encounterId, templateId, ageinDay, tabType, isAgeBased, leafCreatedDate);
		set.addAll(list);
		list.clear();
		
		list= elementQueryFactory.getClinicalElementsSecond(patientGender, gwPattern, patientId, encounterId, templateId, ageinDay, tabType, isAgeBased, leafCreatedDate, clientId);
		set.addAll(list);
		list.clear();
		list.addAll(set);
		
		Iterator<ClinicalElementBean> iterator= set.iterator();
		while(iterator.hasNext()){
			ClinicalElementBean bean= iterator.next();
			System.out.println("bean.getClinicalElementGWID():: "+bean.getClinicalElementGWID());			
			this.setClinicalElements(bean.getClinicalElementGWID(), bean);
		}
	}
	
}