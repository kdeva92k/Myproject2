package com.glenwood.glaceemr.server.application.services.chart.HPI;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.Trimspec;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.ChiefComplaints;
import com.glenwood.glaceemr.server.application.models.ChiefComplaints_;
import com.glenwood.glaceemr.server.application.models.ClinicalElementTemplateMapping;
import com.glenwood.glaceemr.server.application.models.ClinicalElementTemplateMapping_;
import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsCondition;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsCondition_;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.GeneralShortcut;
import com.glenwood.glaceemr.server.application.models.GeneralShortcut_;
import com.glenwood.glaceemr.server.application.models.HpiDxMap;
import com.glenwood.glaceemr.server.application.models.HpiDxMap_;
import com.glenwood.glaceemr.server.application.models.HpiElement;
import com.glenwood.glaceemr.server.application.models.HpiElement_;
import com.glenwood.glaceemr.server.application.models.HpiSymptom;
import com.glenwood.glaceemr.server.application.models.HpiSymptomMapping;
import com.glenwood.glaceemr.server.application.models.HpiSymptomMapping_;
import com.glenwood.glaceemr.server.application.models.HpiSymptomOrderby;
import com.glenwood.glaceemr.server.application.models.HpiSymptomQualifier;
import com.glenwood.glaceemr.server.application.models.HpiSymptom_;
import com.glenwood.glaceemr.server.application.models.LeafLibrary;
import com.glenwood.glaceemr.server.application.models.PatientAssessments;
import com.glenwood.glaceemr.server.application.models.PatientAssessments_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.ProblemList;
import com.glenwood.glaceemr.server.application.models.ProblemList_;
import com.glenwood.glaceemr.server.application.models.SoapElementDatalist;
import com.glenwood.glaceemr.server.application.repositories.ChiefComplaintsRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterRepository;
import com.glenwood.glaceemr.server.application.repositories.GeneralShortcutRepository;
import com.glenwood.glaceemr.server.application.repositories.HpiSymptomOrderbyRepository;
import com.glenwood.glaceemr.server.application.repositories.HpiSymptomQualifierRepository;
import com.glenwood.glaceemr.server.application.repositories.HpiSymptomRepository;
import com.glenwood.glaceemr.server.application.repositories.LeafLibraryRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.SoapElementDatalistRepository;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalConstants;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalElementsService;
import com.glenwood.glaceemr.server.application.specifications.ClinicalElementsSpecification;
import com.glenwood.glaceemr.server.application.specifications.EncounterSpecification;
import com.glenwood.glaceemr.server.application.specifications.HpiSpecification;
import com.glenwood.glaceemr.server.application.specifications.LeafLibrarySpecification;
import com.glenwood.glaceemr.server.application.specifications.PatientClinicalElementsSpecification;
import com.glenwood.glaceemr.server.utils.DateUtil;
import com.glenwood.glaceemr.server.utils.HUtil;
/**
 * Service Implementation for History Of Present Illness
 * @author Bhagya Lakshmi
 *
 */


@Service
@Transactional
public class HPIServiceImpl implements HPIService{
	
	@Autowired
	LeafLibraryRepository leafLibraryRepository;
	
	@Autowired
	HpiSymptomOrderbyRepository hpiSymptomOrderbyRepository;
	
	@Autowired
	PatientClinicalElementsRepository patientClinicalElementsRepository;
	
	@Autowired
	HpiSymptomRepository hpiSymptomRepository;
	
	@Autowired
	HpiSymptomQualifierRepository hpiSymptomQualifierRepository;
	
	@Autowired
	SoapElementDatalistRepository soapElementDatalistRepository;
	
	@Autowired
	EncounterRepository encounterRepository;
	
	@Autowired
	ChiefComplaintsRepository chiefComplaintsRepository;
	
	@Autowired
	GeneralShortcutRepository generalShortcutRepository;
	
	@Autowired
	ClinicalElementsService clinicalElementsService;
	
	@PersistenceContext
	EntityManager em;


	/**
	 * Method to get Particular Symptom Data	
	 */
	@Override
	public JSONArray getSymptomData(String clientId, Integer patientId,
			Integer chartId, Integer encounterId, Integer templateId, String symptomGWId, String symptomId, String isFollowUp, String tabId) {
		List<LeafLibrary> leafDetails=getIsAgeBased(templateId);
		HpiSymptomOrderby hpiSymptomOrderby=hpiSymptomOrderbyRepository.findOne(HpiSpecification.OrderBy());
		List<Object[]> list= getPatientReg(patientId);
		List<PatientClinicalElements> patientClinicalElements=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getByHpiSymptomidEncId(symptomId,encounterId));
		
		boolean isAgeBased=leafDetails.get(0).getLeafLibraryIsagebased()==null?false:leafDetails.get(0).getLeafLibraryIsagebased();
		int orderBy = Integer.parseInt((hpiSymptomOrderby.getHpiSymptomOrderbyTypeid()==null?"-1":hpiSymptomOrderby.getHpiSymptomOrderbyTypeid()).toString());
		int patientGender = 0;
		String DOB = "-1";
		int ageinDay=0;
		try{
			if(list.size()>0)
			{
				for(Object[] values : list) {
					try{
						patientGender = Integer.parseInt(values[0] == null? "" : values[0].toString());	//values[0]=====>patientRegistrationSex
						DOB = (values[1] == null? "f" : values[1].toString());		//values[1]=====>patientRegistrationDob
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			else
			{
				patientGender = 0;
				DOB = "-1";
			}
			
			DateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd");
	        Date date = srcDf.parse(DOB);
	        DateFormat destDf = new SimpleDateFormat("MM/dd/yyyy");
	        DOB = destDf.format(date);
	        ageinDay = (int)((DateUtil.dateDiff( DateUtil.DATE , DateUtil.getDate(DOB) , new java.util.Date() )));
		}catch (Exception e) {
			patientGender = 0;
		}
		int chronicConditionStatus;
		if(patientClinicalElements.size()>0)
		{
			chronicConditionStatus=Integer.parseInt(patientClinicalElements.get(0).getPatientClinicalElementsValue());
		}
		else
		{
			chronicConditionStatus=Integer.parseInt("-1");
		}
		if(Integer.parseInt(isFollowUp)==1 && chronicConditionStatus==-1){
			encounterId=getLastEncounterIdOfChronic(symptomId+"",patientId,encounterId,1);
		}else{
			updateHitCountofSymptom(symptomId+"");
		}
		LinkedHashMap<Integer, SymptomBean> hPISymptoms = getNewReadInstanceForSymptom(symptomId,isFollowUp,patientGender,ageinDay,patientId,encounterId,orderBy,isAgeBased);
		HashMap<String, String> associateMap= getAssociateElement(clientId);
		JSONArray HPIJSONArray = generateSymptomJSON(isFollowUp,clientId,hPISymptoms,symptomId,0,patientId,encounterId,patientGender,ageinDay,associateMap,isAgeBased,tabId);
		return HPIJSONArray;
	}

	/**
	 * Method to generate final Symptom Details
	 * @return
	 */
	public JSONArray generateSymptomJSON(String isFollowUp, String clientId,
			LinkedHashMap<Integer, SymptomBean> hPISymptoms, String symptomId, int value, Integer patientId,
			Integer encounterId, int patientGender, int ageinDay,
			HashMap<String, String> associateMap, boolean isAgeBased,
			String tabId) {
		JSONObject symptomObject = new JSONObject();
		JSONArray symptomArray = new JSONArray();
		SymptomBean sBean=hPISymptoms.get(Integer.parseInt(symptomId));
		if(sBean != null){
			try
			{
				String symptomName=sBean.getSymptomName();
				String symptomGWId=sBean.getSymptomGWId();
				String symptomPrintText=sBean.getSymptomPrintText();
				int smptId=sBean.getSymptomId();
				int symptomType=sBean.getSymptomType();
				String retain = sBean.getRetainCase();
				JSONArray qualifierListArray;
				JSONObject qualifierListObject;
				JSONObject qualifierObject = new JSONObject();
				JSONArray qualiferFinalArray = new JSONArray();
				JSONArray qualifierArray = null;
				int elementId=0;
				symptomObject.put("symptomname", symptomName);
				LinkedHashMap<String, QualifierBean> temp=sBean.getSymptomQualifiers();
				for(Map.Entry<String,QualifierBean> entrySet:temp.entrySet()){
					String qualifierId=entrySet.getKey();
					QualifierBean qBean=temp.get(qualifierId);
					String qualifierName=qBean.getQualifierName();
					String qualifierPrientTextWcase=qBean.getqualifierPrintText();
					String qualifierPrientText="";
					if(!qualifierPrientTextWcase.equalsIgnoreCase("")){
						if(qualifierPrientTextWcase.split("#~#")[0].equalsIgnoreCase("1")){
							qualifierPrientText=qualifierPrientTextWcase.split("#~#")[1];
						}else {
							String tempStr=qualifierPrientTextWcase.split("#~#")[1];
							qualifierPrientText=tempStr.substring(0, tempStr.length()).toLowerCase();
						}
					}
					String qualifierEandM=qBean.getQualifierEandM();
					LinkedHashMap <String,HPIElementBean> element=qBean.getQualifierElements();
					qualifierListArray = new JSONArray();
					qualifierListObject = new JSONObject();
					qualifierArray =  new JSONArray();
					for(Map.Entry<String,HPIElementBean> entrySet1:element.entrySet()){
						String elementGWId=entrySet1.getKey();
						String associateKey=HUtil.Nz(associateMap.get(elementGWId),"-1");
						if(associateKey.equalsIgnoreCase("-1")){
							qualifierListArray = elementList(elementGWId,isFollowUp,element, qualifierEandM, elementId,patientId,encounterId,patientGender, ageinDay,isAgeBased,tabId);
						  if(qualifierListArray.length()>0)
						   for(int k=0;k<qualifierListArray.length();k++){
							   qualifierListObject = qualifierListArray.getJSONObject(k);
							   qualifierArray.put(qualifierListObject);
						   }
						}
					}
					if(qualifierListObject.length()>0){
						qualifierObject = new JSONObject();
						qualifierObject.put("qualifiername", qualifierName);
	        			qualifierObject.put("elementdetail", qualifierArray);
	        			qualiferFinalArray.put(qualifierObject);
	        			symptomObject.put("qualifier", qualiferFinalArray);
	        		}
					
				}
				symptomObject.put("symptomid",symptomGWId);
				symptomObject.put("symptomname",symptomName);
				symptomObject.put("smptid",smptId);
				symptomObject.put("symptomtype",symptomType);
				symptomObject.put("value",value);
				symptomObject.put("symptomprinttext",symptomPrintText);
				symptomObject.put("retaincase",retain);
				symptomArray.put(symptomObject);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return symptomArray;			
	}

	/**
	 * Method to get element option list
	 * @return
	 */
	public JSONArray elementList(String elementGWId, String isFollowUp,
			HashMap<String, HPIElementBean> element, String qualifierEandM,
			int elementId, Integer patientId, Integer encounterId,
			int patientGender, int ageinDay, boolean isAgeBased, String tabId) {
		JSONArray elementListArray = new JSONArray();
		elementId++;
		
		String elementName=HUtil.ValidateSpecialQuote(element.get(elementGWId).getElementname());
		int isSelect = element.get(elementGWId).getClinicalElementIsSelect();
		String elementPrintTextWCase=HUtil.ValidateSpecialQuote(element.get(elementGWId).getElementPrintText());
		String elementPrintText="";
		if(!elementPrintTextWCase.equalsIgnoreCase("")){
			if(elementPrintTextWCase.split("#~#")[0].equalsIgnoreCase("1")){
				elementPrintText=elementPrintTextWCase.split("#~#")[1];
			}else {
				String tempStr=elementPrintTextWCase.split("#~#")[1];
				elementPrintText=tempStr.substring(0, tempStr.length()).toLowerCase();
			}
		}
		HPIElementBean elementBean=element.get(elementGWId);
		if(elementBean!=null){
			int elementtype=elementBean.getClinicalElementDataType();
			switch(elementtype){
				case (ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_NUMBER):{
					elementId++;
					break;
				}
				case (ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT):{
					JSONArray elementSubList1 = null;
					JSONArray elementSubList2 = null;
					String associatedElement = null;
					String isdate = null;
					ClinicalTextMapping clinicalTextMapping = clinicalElementsService.getClinicalTextMapping(elementGWId);
					if(clinicalTextMapping == null)
					{
						associatedElement= "";
						isdate="f";
					}
					else
					{
						associatedElement = clinicalTextMapping.getClinicalTextMappingAssociatedElement()+"";
						isdate = clinicalTextMapping.getClinicalTextMappingIsdate()+"";
					}
					
					String dimension=elementBean.getClinicalElementTextDimension();
					String size="";
					String rows="";
					String cols="";
					if(!dimension.equalsIgnoreCase("")){
						if(dimension.indexOf('X')!=-1){
							rows=dimension.split("X")[1];
							cols=dimension.split("X")[0];
						}else {
							size=dimension;
						}
					}
					if(!associatedElement.equalsIgnoreCase("")){
						elementSubList2 = new JSONArray();
						if(associatedElement.split("#")[1].equalsIgnoreCase("2") || associatedElement.split("#")[1].equalsIgnoreCase("3")){
							if(element.get(associatedElement.split("#")[0])!=null){
								
								elementSubList2 = elementList(associatedElement.split("#")[0] ,isFollowUp, element, qualifierEandM, elementId, patientId, encounterId, patientGender, ageinDay, isAgeBased, tabId);
								JSONObject hh;
								try {
									hh = elementSubList2.getJSONObject(0);
									elementListArray.put(hh);
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}
						}
					}
					if(!elementName.trim().equalsIgnoreCase("")){
							JSONObject textObject = new JSONObject();
							try {
								textObject.put("elementName", elementName);
								textObject.put("isdate", isdate);
								textObject.put("elementGWId", elementGWId);
								textObject.put("elementPrintText", elementPrintText);
								textObject.put("associatedElement", associatedElement);
								textObject.put("eandmtype", qualifierEandM.replace(" ",""));
								textObject.put("clinicalelementtype", ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT);
								textObject.put("value", elementBean.getValue());
								textObject.put("dimension", dimension);
								textObject.put("isSelect", isSelect);
								textObject.put("shortcuts", getElementDataList(tabId, "xml_hpi_element_1_" + elementGWId));
							}
							catch (Exception e) {
								e.printStackTrace();
							}
							elementListArray.put(textObject);
						}else{
							JSONObject textObject = new JSONObject();
							try {
								textObject.put("elementName", elementName);
								textObject.put("isdate", isdate);
								textObject.put("elementGWId", elementGWId);
								textObject.put("elementPrintText", elementPrintText);
								textObject.put("associatedElement", associatedElement);
								textObject.put("eandmtype", qualifierEandM.replace(" ",""));
								textObject.put("clinicalelementtype", ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT);
								textObject.put("value", elementBean.getValue());
								textObject.put("dimension", dimension);
								textObject.put("isSelect", isSelect);
								textObject.put("shortcuts", getElementDataList(tabId, "xml_hpi_element_1_" + elementGWId));
							}
							 catch (Exception e) {
									e.printStackTrace();
							}
							elementListArray.put(textObject);
							
						}
					if(!associatedElement.equalsIgnoreCase("")){
						if(associatedElement.split("#")[1].equalsIgnoreCase("1")){
							if(element.get(associatedElement.split("#")[0])!=null){
								elementSubList1 = elementList(associatedElement.split("#")[0] ,isFollowUp, element, qualifierEandM, elementId,patientId,encounterId,patientGender, ageinDay, isAgeBased,tabId);
								JSONObject elementSubListObject;
								try {
									elementSubListObject = elementSubList1.getJSONObject(0);
									elementListArray.put(elementSubListObject);
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}
						}
					}
					elementId++;
					break;
				}
				case (ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN):{
					String elementValue=elementBean.getValue();
					if(elementValue.equalsIgnoreCase("true")){
						JSONObject booleanObject = new JSONObject();
						try {
							booleanObject.put("elementName", elementName);
							booleanObject.put("elementGWId", elementGWId);
							booleanObject.put("elementPrintText", elementPrintText);
							booleanObject.put("eandmtype", qualifierEandM.replace(" ",""));
							booleanObject.put("clinicalelementtype", ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN);
							booleanObject.put("value","true");
							booleanObject.put("isSelect", isSelect);
							elementListArray.put(booleanObject);
						}
						catch (JSONException e) {
							e.printStackTrace();
						}
						elementId++;
					}else if(elementValue.equalsIgnoreCase("false")){
						JSONObject booleanObject = new JSONObject();
						try {
							booleanObject.put("elementName", elementName);
							booleanObject.put("elementGWId", elementGWId);
							booleanObject.put("elementPrintText", elementPrintText);
							booleanObject.put("eandmtype", qualifierEandM.replace(" ",""));
							booleanObject.put("clinicalelementtype", ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN);
							booleanObject.put("value","false");
							booleanObject.put("isSelect", isSelect);
							elementListArray.put(booleanObject);
						}
						catch (JSONException e) {
							e.printStackTrace();
						}
						elementId++;
					}else{
						JSONObject booleanObject = new JSONObject();
						try {
							booleanObject.put("elementName", elementName);
							booleanObject.put("elementGWId", elementGWId);
							booleanObject.put("elementPrintText", elementPrintText);
							booleanObject.put("eandmtype", qualifierEandM.replace(" ",""));
							booleanObject.put("clinicalelementtype", ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN);
							booleanObject.put("value","");
							booleanObject.put("isSelect", isSelect);
							elementListArray.put(booleanObject);
						}
						catch (JSONException e) {
							e.printStackTrace();
						}
						elementId++;
					}
					elementId++;
					break;
				}
				case (ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION):{
					
					try{
						List<String> detOpGwids = new ArrayList<String>();
						List<String> patElemOptionValues = getPatientElemValues(elementGWId,patientId,encounterId);
						List<ClinicalElementsOptionBean> option=clinicalElementsService.getSymptomClinicalElementOptions(elementGWId,patientId,encounterId,patientGender,ageinDay,isAgeBased,0);
						if(isAgeBased)
						{
							List<ClinicalElementsOptionBean> patclinicaldetailOptions=clinicalElementsService.getSymptomElementOptionAfterUnion(elementGWId,patientId,encounterId,patientGender,ageinDay,isAgeBased,0);
							//remove duplicates from patclinicaldetailOptions

							for (ClinicalElementsOptionBean detOption : option) {
								detOpGwids.add(detOption.getClinicalelementoptionGwid());
							}

							for(int i=0;i<patclinicaldetailOptions.size();i++){
								if(detOpGwids.contains(patclinicaldetailOptions.get(i).getClinicalelementoptionGwid())){
									patclinicaldetailOptions.remove(i);
									i--;
								}
							}
							option.addAll(patclinicaldetailOptions);
						}
							
						JSONObject multipleObject = new JSONObject();
						multipleObject.put("elementName", elementName);
						multipleObject.put("elementGWId", elementGWId);
						multipleObject.put("elementPrintText", elementPrintText);
						multipleObject.put("eandmtype", qualifierEandM.replace(" ",""));
						multipleObject.put("clinicalelementtype", ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION);
						multipleObject.put("isSelect", isSelect);
						if(option.size() > 0){
							int j=0;
							for(;j<option.size();j++){
								ClinicalElementsOptionBean optionRecord = option.get(j);
								String optionName=HUtil.ValidateSpecialQuote(optionRecord.getClinicalelementoptionName() == null?"":optionRecord.getClinicalelementoptionName());
								String optionValue=optionRecord.getClinicalelementoptionValue() == null?"":optionRecord.getClinicalelementoptionValue();
								String retain=optionRecord.getClinicalElementsOptionsRetaincase() == null?"f":optionRecord.getClinicalElementsOptionsRetaincase().toString();
								//String elementValue=optionRecord.getPatientClinicalElementOptionsValue() == null?"":optionRecord.getClinicalelementoptionValue();
									
									elementListArray = new JSONArray();
									JSONObject multipleOptionObject = new JSONObject();
									multipleOptionObject.put("optionname", optionName);
									multipleOptionObject.put("optionvalue", optionValue);
									multipleOptionObject.put("retain", retain);
									multipleOptionObject.put("value",patElemOptionValues.contains(optionValue)?1:0);
									multipleObject.append("detailoption",multipleOptionObject);
									elementListArray.put(multipleObject);
								/*if(optionValue.equalsIgnoreCase(elementValue)){
										elementListArray = new JSONArray();
										JSONObject multipleOptionObject = new JSONObject();
										multipleOptionObject.put("optionname", optionName);
										multipleOptionObject.put("optionvalue", optionValue);
										multipleOptionObject.put("retain", retain);
										multipleOptionObject.put("value",1);
										multipleObject.append("detailoption",multipleOptionObject);
										elementListArray.put(multipleObject);
									}else{
										elementListArray = new JSONArray();
										JSONObject multipleOptionObject = new JSONObject();
										multipleOptionObject.put("optionname", optionName);
										multipleOptionObject.put("optionvalue", optionValue);
										multipleOptionObject.put("retain", retain);
										multipleOptionObject.put("value",0);
										multipleObject.append("detailoption",multipleOptionObject);
										elementListArray.put(multipleObject);
									}*/
								elementId++;
							}
						}	
					}catch(Exception e){
						e.printStackTrace();
					}
					break;
				}
				case (ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION):{
					JSONObject singleObject = new JSONObject();
					try {
						singleObject.put("elementName", elementName);
						singleObject.put("elementGWId", elementGWId);
						singleObject.put("elementPrintText", elementPrintText);
						singleObject.put("eandmtype", qualifierEandM.replace(" ",""));
						singleObject.put("clinicalelementtype", ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION);
						singleObject.put("isSelect", isSelect);
					}
					catch (JSONException e1) {
						e1.printStackTrace();
					}
					try{
						List<String> detOpGwids = new ArrayList<String>();
						List<String> patElemOptionValues = getPatientElemValues(elementGWId,patientId,encounterId);
						List<ClinicalElementsOptionBean> option=clinicalElementsService.getSymptomClinicalElementOptions(elementGWId,patientId,encounterId,patientGender,ageinDay,isAgeBased,0);
						if(isAgeBased)
						{
							List<ClinicalElementsOptionBean> patclinicaldetailOptions=clinicalElementsService.getSymptomElementOptionAfterUnion(elementGWId,patientId,encounterId,patientGender,ageinDay,isAgeBased,0);
	
							//remove duplicates from patclinicaldetailOptions
	
							for (ClinicalElementsOptionBean detOption : option) {
								detOpGwids.add(detOption.getClinicalelementoptionGwid());
							}
	
							for(int i=0;i<patclinicaldetailOptions.size();i++){
								if(detOpGwids.contains(patclinicaldetailOptions.get(i).getClinicalelementoptionGwid())){
									patclinicaldetailOptions.remove(i);
									i--;
								}
							}	
	
							option.addAll(patclinicaldetailOptions);
						}
						if(option.size() > 0){
							int j=0;
							for(;j<option.size();j++){
								ClinicalElementsOptionBean optionRecord = option.get(j);
								String optionName=HUtil.ValidateSpecialQuote(optionRecord.getClinicalelementoptionName() == null?"":optionRecord.getClinicalelementoptionName());
								String optionValue=optionRecord.getClinicalelementoptionValue() == null?"":optionRecord.getClinicalelementoptionValue();
								String retain=optionRecord.getClinicalElementsOptionsRetaincase() == null?"f":optionRecord.getClinicalElementsOptionsRetaincase().toString();
								//String elementValue=optionRecord.getPatientClinicalElementOptionsValue() == null?"":optionRecord.getPatientClinicalElementOptionsValue();
								elementListArray = new JSONArray();
								
								JSONObject singleOptionObject = new JSONObject();
								singleOptionObject.put("optionname", optionName);
								singleOptionObject.put("optionvalue", optionValue);
								singleOptionObject.put("retain", retain);
								singleOptionObject.put("value", patElemOptionValues.contains(optionValue)?1:0);
								singleObject.append("detailoption",singleOptionObject);
								elementListArray.put(singleObject);
								/*if(optionValue.equalsIgnoreCase(elementValue)){
									elementListArray = new JSONArray();
									
									JSONObject singleOptionObject = new JSONObject();
									singleOptionObject.put("optionname", optionName);
									singleOptionObject.put("optionvalue", optionValue);
									singleOptionObject.put("retain", retain);
									singleOptionObject.put("value", 1);
									singleObject.append("detailoption",singleOptionObject);
									elementListArray.put(singleObject);
								}else{
									elementListArray = new JSONArray();
									
									JSONObject singleOptionObject = new JSONObject();
									singleOptionObject.put("optionname", optionName);
									singleOptionObject.put("optionvalue", optionValue);
									singleOptionObject.put("retain", retain);
									singleOptionObject.put("value", 0);
									singleObject.append("detailoption",singleOptionObject);
									elementListArray.put(singleObject);
								}*/
								elementId++;
							}
						}	
					}catch(Exception e){
						e.printStackTrace();
					}
					break;
				}
				case (ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_OCX):{
					String dimension=elementBean.getClinicalElementTextDimension();
					String height="";
					String width="";
					if(!dimension.equalsIgnoreCase("")){
						if(dimension.indexOf('X')!=-1){
							height=dimension.split("X")[1];
							width=dimension.split("X")[0];
						}
					}else{
						height="228";
						width="620";
					}
					if(elementBean!=null){
						String elementValue=elementBean.getValue();
						if(!elementValue.equalsIgnoreCase("")){
						}else{
						}
					}
					elementId++;
					break;
				}
			}
		}
		return elementListArray;
	}

	private List<String> getPatientElemValues(String elementGWId,
			Integer patientId, Integer encounterId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PatientClinicalElements> root = cq.from(PatientClinicalElements.class);
		cq.select(root.get(PatientClinicalElements_.patientClinicalElementsValue));
		cq.where(builder.and(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsGwid), elementGWId),
				builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid), patientId),
				builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId)));
		List<Object> patElemValuesQuery = em.createQuery(cq).getResultList();
		List<String> patElemValues = new ArrayList<String>();
		for(int i=0;i<patElemValuesQuery.size();i++)
		{
			patElemValues.add(patElemValuesQuery.get(i).toString());
		}
		return patElemValues;
	}

	/**
	 * Method to get element data list from Soap Element Data List
	 * @return
	 * @throws Exception
	 */
	public JSONArray getElementDataList(String tabId,String elementId)throws Exception{
		try{
			int tabIdInteger = Integer.parseInt(tabId);
			List<SoapElementDatalist> soapElementDatalists = soapElementDatalistRepository.findAll(ClinicalElementsSpecification.getSoapElementDataList(tabIdInteger, elementId));
			JSONArray soapElementDatalistJSONArray = new JSONArray();
			for(int i=0;i<soapElementDatalists.size();i++)
			{
				JSONObject soapElementDatalistJSONObject = new JSONObject();
				soapElementDatalistJSONObject.put("datalistid", soapElementDatalists.get(i).getSoapElementDatalistId().toString());
				soapElementDatalistJSONObject.put("data", soapElementDatalists.get(i).getSoapElementDatalistData());
				soapElementDatalistJSONArray.put(i, soapElementDatalistJSONObject);
			}
			return soapElementDatalistJSONArray;
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


	/**
	 * Method to get Associate Element from Clinical Text Mapping Table
	 * @param clientId
	 * @return
	 */
	public HashMap<String, String> getAssociateElement(String clientId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ClinicalTextMapping> root = cq.from(ClinicalTextMapping.class);
		cq.select(builder.substring(root.get(ClinicalTextMapping_.clinicalTextMappingAssociatedElement), 1, 19)).where(builder.or(builder.like(root.get(ClinicalTextMapping_.clinicalTextMappingAssociatedElement), "00001002%"), builder.like(root.get(ClinicalTextMapping_.clinicalTextMappingAssociatedElement), clientId+"01002%")));
		List<Object> clinicalTextMapping = em.createQuery(cq).getResultList();
		HashMap<String, String> associateMap= new HashMap();
		for(int i=0;i<clinicalTextMapping.size();i++){
			associateMap.put( clinicalTextMapping.get(i).toString() , i+"");
		}
		return associateMap;
	}

	/**
	 * update query to increase hit count basing on SymptomId
	 * @param symptomId
	 */
	public void updateHitCountofSymptom(String symptomId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<HpiSymptom> root = cq.from(HpiSymptom.class);
		cq.select(builder.coalesce(root.get(HpiSymptom_.hpiSymptomHitcount), 0)).where(builder.equal(root.get(HpiSymptom_.hpiSymptomId), symptomId));
		Integer hitCount = (Integer)em.createQuery(cq).getSingleResult()+1;
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<HpiSymptom> update = cb.createCriteriaUpdate(HpiSymptom.class);
		Root<HpiSymptom> rootCriteria = update.from(HpiSymptom.class);
		update.set(rootCriteria.get(HpiSymptom_.hpiSymptomHitcount), hitCount);
		update.where(cb.equal(rootCriteria.get(HpiSymptom_.hpiSymptomId),Integer.parseInt(symptomId)));
		this.em.createQuery(update).executeUpdate();
	}

	/**
	 * Method to get Particular Symptom Details (qualifiers,elements,options)
	 * @param symptomId
	 * @param isFollowUp
	 * @param patientGender
	 * @param ageinDay
	 * @param patientId
	 * @param encounterId
	 * @param orderBy
	 * @param isAgeBased
	 * @return
	 */
	public LinkedHashMap<Integer,SymptomBean> getNewReadInstanceForSymptom(String symptomId,
			String isFollowUp, int patientGender, int ageinDay,
			Integer patientId, Integer encounterId, int orderBy,
			boolean isAgeBased) {
		List<HpiSymptom> hpiSymptom=hpiSymptomRepository.findAll(HpiSpecification.getBYSymptomId(symptomId));
		LinkedHashMap<Integer, SymptomBean> HPISymptoms=new LinkedHashMap<Integer, SymptomBean>();
		SymptomBean sBean= new SymptomBean();
		for(int j=0;j<hpiSymptom.size();j++){
			HpiSymptom hpiSymptomList = hpiSymptom.get(j);
			
			sBean.setSymptomGWId(hpiSymptomList.getHpiSymptomGwid());
			sBean.setSymptomId(Integer.parseInt(HUtil.Nz(hpiSymptomList.getHpiSymptomId(),"-1")));
			sBean.setSymptomName(HUtil.Nz(hpiSymptomList.getHpiSymptomName(),""));
			sBean.setSymptomComments(HUtil.Nz(hpiSymptomList.getHpiSymptomComments(),""));
			sBean.setSymptomPrintText(HUtil.Nz(hpiSymptomList.getHpiSymptomPrinttext(),""));
			sBean.setSymptomType(Integer.parseInt(HUtil.Nz(hpiSymptomList.getHpiSymptomType(),"-1")));
			sBean.setRetainCase(HUtil.Nz(hpiSymptomList.getHpiSymptomRetaincase(),"t"));
			
			List<HpiSymptomQualifier> hpiSymptomQualifier=hpiSymptomQualifierRepository.findAll(HpiSpecification.getQualifierQuery(symptomId,orderBy));
			LinkedHashMap<String, QualifierBean> hMapQualifier= new LinkedHashMap<String, QualifierBean>();
			for(int k=0;k<hpiSymptomQualifier.size();k++){
				HpiSymptomQualifier hpiSymptomQualifierlist =  hpiSymptomQualifier.get(k);
				QualifierBean qBean=new QualifierBean();
				qBean.setQualifierName(hpiSymptomQualifierlist.getHpiSymptomQualifierName() == null?"":hpiSymptomQualifierlist.getHpiSymptomQualifierName());
				qBean.setQualifierEandM(hpiSymptomQualifierlist.getHpiSymptomQualifierEandm() == null?"-":hpiSymptomQualifierlist.getHpiSymptomQualifierEandm());
				qBean.setQualifierId(Integer.parseInt(hpiSymptomQualifierlist.getHpiSymptomQualifierId() == null?"-1":hpiSymptomQualifierlist.getHpiSymptomQualifierId().toString()));
				qBean.setQualifierGWId(hpiSymptomQualifierlist.getHpiSymptomQualifierGwid() == null?"":hpiSymptomQualifierlist.getHpiSymptomQualifierGwid());
				qBean.setqualifierPrintText(hpiSymptomQualifierlist.getHpiSymptomQualifierPrinttext() == null?"":hpiSymptomQualifierlist.getHpiSymptomQualifierPrinttext());
				int qualifierId=Integer.parseInt(hpiSymptomQualifierlist.getHpiSymptomQualifierId() == null?"-1":hpiSymptomQualifierlist.getHpiSymptomQualifierId().toString());
				
				List<Object[]> hpiElement=getElementQuery(qualifierId,orderBy,patientGender,ageinDay,patientId,encounterId,isAgeBased);
				LinkedHashMap<String, HPIElementBean> hMapElement = new LinkedHashMap<String, HPIElementBean>();
				
				if(isAgeBased)
				{
					List<String> detOpGwids = new ArrayList<String>();
					List<Object[]> afterUnionList1=getElementQueryAfterUnion(qualifierId,orderBy,patientGender,ageinDay,patientId,encounterId,isAgeBased);
					for (Object[] value : hpiElement) {
						detOpGwids.add(value[0].toString());
					}
					int i = 0;
					for(Object[] value : afterUnionList1){
						if(detOpGwids.contains(value[0])){
							afterUnionList1.remove(i);
							i--;
						}
						i++;
					}	

					hpiElement.addAll(afterUnionList1);
				}
				for(Object[] value : hpiElement){
					HPIElementBean eBean=new HPIElementBean();
					String elementGWId=value[0] == null?"-1":value[0].toString();
					//int elementDatatype= (int) (value[6] == null?-1:value[6]);
					eBean.setElementGWId(elementGWId);
					eBean.setElementname(value[1] == null?"":value[1].toString());
					eBean.setElementPrintText(value[2] == null?"":value[2].toString());
					//eBean.setClinicalElementName(value[5] == null?"":value[5].toString());
					//eBean.setClinicalElementDataType(elementDatatype);
					//eBean.setClinicalElementIsActive(Boolean.parseBoolean((value[7] == null?false:value[7]).toString()));
					//eBean.setClinicalElementIsHistory(Boolean.parseBoolean((value[8] == null?false:value[8]).toString()));
					//eBean.setClinicalElementIsEpisode(Boolean.parseBoolean((value[9] == null?false:value[9]).toString()));
					//eBean.setClinicalElementIsSelect((Integer)(value[12] == null?-1:value[12]));
					//eBean.setClinicalElementGender((Integer)(value[11] == null?-1:value[11]));
					//eBean.setClinicalElementTextDimension((value[10] == null?-1:value[10]).toString());
					//eBean.setValue((value[13] == null?-1:value[13]).toString());					
					hMapElement.put(elementGWId,eBean);
				}
					qBean.setQualifierElements(hMapElement);
					hMapQualifier.put(qualifierId+"", qBean);
			}
			sBean.setSymptomQualifiers(hMapQualifier);	
			HPISymptoms.put(Integer.parseInt(symptomId), sBean);
		}
		return HPISymptoms;
	}


	/**
	 * Method to get last encounderId of chronic condition status
	 * @param symptomGWId
	 * @param patientId
	 * @param encounterId
	 * @param isSymptomId
	 * @return
	 */
	public Integer getLastEncounterIdOfChronic(String symptomGWId,
			Integer patientId, Integer encounterId, int isSymptomId) {
		int encId=-1;
		HpiSymptom hpiSymptom=hpiSymptomRepository.findOne(HpiSpecification.getBySymptomGWId(symptomGWId));
		Encounter encounter=encounterRepository.findOne(EncounterSpecification.EncounterById(encounterId));

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PatientClinicalElements> root = cq.from(PatientClinicalElements.class);
		Join<PatientClinicalElements, Encounter> file=root.join(PatientClinicalElements_.encounter,JoinType.INNER);
		cq.select(builder.greatest(file.get(Encounter_.encounterDate))).where(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid), patientId),builder.like(builder.lower(root.get(PatientClinicalElements_.patientClinicalElementsGwid)),getLikePattern(symptomGWId)),builder.lessThan(file.get(Encounter_.encounterDate),encounter.getEncounterDate()));
		
		Integer encounterIdRes= getEncounterByDate(em.createQuery(cq).getSingleResult());
		if(isSymptomId==1){
			if(!(hpiSymptom == null))
			{
				symptomGWId=(hpiSymptom.getHpiSymptomGwid()==null?"-1":hpiSymptom.getHpiSymptomGwid());
			}
			else
			{
				symptomGWId="-1";
			}
		}
		encId=Integer.parseInt(encounterIdRes==null?"-1":encounterIdRes.toString());
		return encId;
	}

	/**
	 * Method to get encounter id from Encounter Table basing on encounter date
	 * @param Encounter Date
	 * @return
	 */
	private Integer getEncounterByDate(Object encounterDate) {
		Timestamp encDate = (Timestamp) encounterDate;
		Integer encId;
		if(!(encDate == null))
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Encounter> root = cq.from(Encounter.class);
			cq.select(root.get(Encounter_.encounterId)).where(builder.equal(root.get(Encounter_.encounterDate), encDate));
			encId = (Integer) em.createQuery(cq).getSingleResult();
		}
		else
		{
			encId = -1;
		}
		return encId;
	}

	/**
	 * Method to get Chief Complaints Data 
	 */
	@Override
	public JSONArray fetchCCData(Integer patientId, Integer chartId,
			Integer encounterId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Encounter> root = cq.from(Encounter.class);
		Join<Encounter,PatientClinicalElements> file=root.join(Encounter_.patientClinicalElements,JoinType.LEFT);
		file.on(builder.like(file.get(PatientClinicalElements_.patientClinicalElementsGwid), "0000100100000000000"));
		cq.select(builder.selectCase().when(builder.notEqual(builder.trim(root.get(Encounter_.encounterChiefcomp)), ""), root.get(Encounter_.encounterChiefcomp)).otherwise(file.get(PatientClinicalElements_.patientClinicalElementsValue)));
		cq.where(builder.equal(root.get(Encounter_.encounterId), encounterId));
		List<Object> ccData=em.createQuery(cq).getResultList();
		JSONArray ccJSONArray = new JSONArray();
		try {
			for(int i=0;i<ccData.size();i++)
			{
				JSONObject ccDataJSONObject = new JSONObject();
				ccDataJSONObject.put("ccNotes", ccData.get(i) == null?"":ccData.get(i));
				ccJSONArray.put(i, ccDataJSONObject);
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return ccJSONArray;
	}


	/**
	 * Method to Fetch Patient HPI Notes Data
	 */
	@Override
	public String fetchPatientHPINotesData(Integer patientId,
			Integer chartId, Integer encounterId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PatientClinicalElements> root = cq.from(PatientClinicalElements.class);
		cq.select(builder.coalesce(root.get(PatientClinicalElements_.patientClinicalElementsValue), "null"));
		cq.where(builder.and(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId),builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsGwid), "0000100200000000000")));
		List<Object> hpinotes=em.createQuery(cq).getResultList();
		JSONArray patientNotesArray = new JSONArray();
		for(int i=0;i<hpinotes.size();i++)
		{
			JSONObject patientNotesObj = new JSONObject();
			try
			{
				patientNotesObj.put("hpinotes",hpinotes.get(i).toString());
				patientNotesArray.put(patientNotesObj);	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return patientNotesArray.toString();
	}

  
	/**
	 * Method to Fetch default shortcut data
	 */
	@Override
	public JSONArray fetchDefaultShortcutData(Integer mode) {
		JSONArray defaultShortcutArr= null;
		if(mode == 1) // for HPI Notes Shortcuts
		{
			defaultShortcutArr = new JSONArray();
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<GeneralShortcut> root = cq.from(GeneralShortcut.class);
			cq.select(builder.construct(GeneralShortcutBean.class,
					root.get(GeneralShortcut_.generalShortcutId),
					builder.trim(Trimspec.TRAILING, root.get(GeneralShortcut_.generalShortcutCode)),
					root.get(GeneralShortcut_.generalShortcutDescription)));
			cq.where(builder.and(root.get(GeneralShortcut_.generalShortcutMapGroupId).in(1),builder.equal(root.get(GeneralShortcut_.generalShortcutIsactive),true)));
			cq.orderBy(builder.asc(root.get(GeneralShortcut_.generalShortcutCode)));
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Object> query = cb.createQuery();
			Root<GeneralShortcut> rootCount = query.from(GeneralShortcut.class);
			query.select(cb.count(rootCount.get(GeneralShortcut_.generalShortcutId))).where(cb.and(rootCount.get(GeneralShortcut_.generalShortcutMapGroupId).in(1),cb.equal(rootCount.get(GeneralShortcut_.generalShortcutIsactive),true)));
			Object rst = em.createQuery(query).getSingleResult();
			
			List<Object> hpinotes=em.createQuery(cq).setFirstResult(0).setMaxResults(10).getResultList();
			JSONObject NotesObj = new JSONObject();
			JSONArray NotesArr = new JSONArray();
				for(int i=0; i<hpinotes.size();i++)
				{
					GeneralShortcutBean gs = (GeneralShortcutBean) hpinotes.get(i); 
					JSONObject NotesJsonObj = new JSONObject();
					try
					{
						NotesJsonObj.put("shortcutid", gs.getShortcutId().toString());
						NotesJsonObj.put("shortcutname", gs.getShortcutName());
						NotesJsonObj.put("shortcutvalue", gs.getShortcutValue());
						NotesArr.put(i, NotesJsonObj);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				Long rs = Long.valueOf(rst.toString());
				try {
					NotesObj.put("Data",NotesArr);
					NotesObj.put("pages", getNormalPageDetails(rs,10,0));
					NotesObj.put("offset", 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				defaultShortcutArr.put(NotesObj);
		}
		if(mode == 5) // for chief complaints shortcuts
		{
			defaultShortcutArr = new JSONArray();
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<ChiefComplaints> root = cq.from(ChiefComplaints.class);
			cq.select(builder.construct(ChiefComplaintsBean.class,
					root.get(ChiefComplaints_.chiefComplaintsId),
					root.get(ChiefComplaints_.chiefComplaintsName),
					root.get(ChiefComplaints_.chiefComplaintsName)));
			cq.where(builder.and(builder.equal(root.get(ChiefComplaints_.chiefComplaintsIsactive),true),builder.equal(root.get(ChiefComplaints_.chiefComplaintsDefault1),true)));
			cq.orderBy(builder.asc(root.get(ChiefComplaints_.chiefComplaintsName)));
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> query = cb.createQuery(Long.class);
			Root<ChiefComplaints> rootCount = query.from(ChiefComplaints.class);
			query.select(cb.count(rootCount.get(ChiefComplaints_.chiefComplaintsId))).where(cb.and(cb.equal(rootCount.get(ChiefComplaints_.chiefComplaintsIsactive),true),cb.equal(rootCount.get(ChiefComplaints_.chiefComplaintsDefault1),true)));
			Long rst = em.createQuery(query).getSingleResult();
			
			List<Object> hpinotes=em.createQuery(cq).setFirstResult(0).setMaxResults(10).getResultList();
			JSONObject ccObj = new JSONObject();
			JSONArray ccArr = new JSONArray();
				for(int i=0; i<hpinotes.size();i++)
				{
					ChiefComplaintsBean cc = (ChiefComplaintsBean) hpinotes.get(i);
					JSONObject ccJsonObj = new JSONObject();
					try
					{
						ccJsonObj.put("shortcutid", cc.getShortcutId().toString());
						ccJsonObj.put("shortcutname", cc.getShortcutName());
						ccJsonObj.put("shortcutvalue", cc.getShortcutValue());
						ccArr.put(i, ccJsonObj);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				try {
					ccObj.put("Data",ccArr);
					ccObj.put("pages", getNormalPageDetails(rst,10,0));
					ccObj.put("offset", 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				defaultShortcutArr.put(ccObj);
		}
		return defaultShortcutArr;
	}

	/**
	 * 
	 * @param rst
	 * @param limit
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	public static JSONArray getNormalPageDetails(Long rst, int limit , int offset) throws Exception{
		JSONArray pageInfo=new JSONArray();
		JSONObject pageInfoData=new JSONObject();
		long totalCount = (rst == null?0:rst);
		int total=(int)Math.floor( ( ((double)totalCount) /limit) ) ;

		pageInfoData.put("totalPages", Integer.toString((int) Math.ceil( ( ((double)totalCount) /limit) ) ) );
		pageInfoData.put("lastOffset", ((total*limit)) );
		if(offset==0)
			pageInfoData.put("prevOffset", 0);
		else
			pageInfoData.put("prevOffset", offset-limit);
		pageInfoData.put("currentPage", Integer.toString((offset/limit)+1) );
		if((offset/limit) == total)
			pageInfoData.put("nextOffset", Integer.toString(offset));
		else
			pageInfoData.put("nextOffset", Integer.toString(offset+limit));
		pageInfo.put(0, pageInfoData);
		return pageInfo;
	}

	/**
	 * Method to delete Symptom Details
	 */
	@Override
	public void deleteSymptomDetails(String symptomGWId,
			Integer patientId, Integer encounterId) {
		List<PatientClinicalElements> deleteList = patientClinicalElementsRepository.findAll(HpiSpecification.getDeleteSymptomList(symptomGWId,encounterId));
		patientClinicalElementsRepository.deleteInBatch(deleteList);
		return;
	}

	/**
	 * Method to fetch Shortcut Data
	 */
	@Override
	public JSONArray fetchShortcutData(String shortcutId) {
		JSONArray generalShortcut = new JSONArray();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<GeneralShortcut> root = cq.from(GeneralShortcut.class);
		cq.select(builder.construct(GeneralShortcutBean.class,
				root.get(GeneralShortcut_.generalShortcutId),
				builder.trim(Trimspec.TRAILING, root.get(GeneralShortcut_.generalShortcutCode)),
				root.get(GeneralShortcut_.generalShortcutDescription)));
		cq.where(builder.and(root.get(GeneralShortcut_.generalShortcutMapGroupId).in(1),builder.equal(root.get(GeneralShortcut_.generalShortcutIsactive),true),root.get(GeneralShortcut_.generalShortcutId).in(shortcutId)));
		cq.orderBy(builder.asc(root.get(GeneralShortcut_.generalShortcutCode)));
		List<Object> hpinotes=em.createQuery(cq).getResultList();
		List<Object> shortCutData;
		List<GeneralShortcutBean> gsBean = new ArrayList<GeneralShortcutBean>();
		if(hpinotes.size() > 0)
		{
			shortCutData=em.createQuery(cq).setFirstResult(0).setMaxResults(10).getResultList();
			for(int i=0; i<shortCutData.size();i++)
			{
				GeneralShortcutBean gs = (GeneralShortcutBean) shortCutData.get(i);
				gsBean.add(gs);
			}
		}
		for(int i=0;i<gsBean.size();i++)
		{
			JSONObject generalShortcutObject = new JSONObject();
			try
			{
				generalShortcutObject.put("id", gsBean.get(i).getShortcutId());
				generalShortcutObject.put("name", gsBean.get(i).getShortcutName());
				generalShortcutObject.put("value", gsBean.get(i).getShortcutValue());
				generalShortcut.put(i, generalShortcutObject);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return generalShortcut;
	}

	/**
	 * Method to fetch Shortcut list based on search String
	 */
	@Override
	public JSONArray fetchShortCutListBasedOnSearchString(Integer mode,
			String searchStr, Integer limit, Integer offset) {
		String searchStrParam = searchStr.replaceAll("'", "''").trim();
		JSONArray fetchShortcutArr = null;
		if(mode == 3)	// notes search
		{
			fetchShortcutArr = new JSONArray();
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<GeneralShortcut> root = cq.from(GeneralShortcut.class);
			cq.select(builder.construct(GeneralShortcutBean.class,
					root.get(GeneralShortcut_.generalShortcutId),
					builder.trim(Trimspec.TRAILING, root.get(GeneralShortcut_.generalShortcutCode)),
					root.get(GeneralShortcut_.generalShortcutDescription)));																						
			cq.where(builder.and(root.get(GeneralShortcut_.generalShortcutMapGroupId).in(1),builder.equal(root.get(GeneralShortcut_.generalShortcutIsactive),true),builder.like(builder.lower(root.get(GeneralShortcut_.generalShortcutCode)), getLikePattern(searchStrParam))));
			cq.orderBy(builder.asc(root.get(GeneralShortcut_.generalShortcutCode)));
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Object> query = cb.createQuery();
			Root<GeneralShortcut> rootCount = query.from(GeneralShortcut.class);
			query.select(cb.count(rootCount.get(GeneralShortcut_.generalShortcutId))).where(cb.and(rootCount.get(GeneralShortcut_.generalShortcutMapGroupId).in(1),cb.equal(rootCount.get(GeneralShortcut_.generalShortcutIsactive),true),cb.like(builder.lower(rootCount.get(GeneralShortcut_.generalShortcutCode)), getLikePattern(searchStrParam))));
			Object rst = em.createQuery(query).getSingleResult();
			
			List<Object> hpinotes=em.createQuery(cq).setFirstResult(offset).setMaxResults(limit).getResultList();
			JSONObject notesObj = new JSONObject();
			JSONArray notesArr = new JSONArray();
			try {
				for(int i=0; i<hpinotes.size();i++)
				{
					GeneralShortcutBean gs = (GeneralShortcutBean) hpinotes.get(i);  
					JSONObject notesJsonObj = new JSONObject();
					try
					{
						notesJsonObj.put("id", gs.getShortcutId());
						notesJsonObj.put("name", gs.getShortcutName());
						notesJsonObj.put("value", gs.getShortcutValue());
						notesArr.put(i, notesJsonObj);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				Long rs = Long.valueOf(rst.toString());
				try {
					notesObj.put("Data",notesArr);
					notesObj.put("pages", getNormalPageDetails(rs,limit,offset));
					notesObj.put("offset", 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				fetchShortcutArr.put(notesObj);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(mode == 7) // for chief complaints search 
		{
			fetchShortcutArr = new JSONArray();
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<ChiefComplaints> root = cq.from(ChiefComplaints.class);
			cq.select(builder.construct(ChiefComplaintsBean.class,
					root.get(ChiefComplaints_.chiefComplaintsId),
					root.get(ChiefComplaints_.chiefComplaintsName),
					root.get(ChiefComplaints_.chiefComplaintsName)));
			cq.where(builder.and(builder.equal(root.get(ChiefComplaints_.chiefComplaintsIsactive),true),builder.equal(root.get(ChiefComplaints_.chiefComplaintsDefault1),true),builder.like(builder.lower(root.get(ChiefComplaints_.chiefComplaintsName)), getLikePattern(searchStrParam))));
			cq.orderBy(builder.asc(root.get(ChiefComplaints_.chiefComplaintsName)));
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> query = cb.createQuery(Long.class);
			Root<ChiefComplaints> rootCount = query.from(ChiefComplaints.class);
			query.select(cb.count(rootCount.get(ChiefComplaints_.chiefComplaintsId))).where(cb.and(cb.equal(rootCount.get(ChiefComplaints_.chiefComplaintsIsactive),true),cb.equal(rootCount.get(ChiefComplaints_.chiefComplaintsDefault1),true),cb.like(builder.lower(rootCount.get(ChiefComplaints_.chiefComplaintsName)), getLikePattern(searchStrParam))));
			Long rst = em.createQuery(query).getSingleResult();
			
			List<Object> hpinotes=em.createQuery(cq).setFirstResult(offset).setMaxResults(limit).getResultList();
			List<ChiefComplaintsBean> ccBean = new ArrayList<ChiefComplaintsBean>();
			JSONObject ccObj = new JSONObject();
			JSONArray ccArr = new JSONArray();
			for(int i=0; i<hpinotes.size();i++)
			{
				ChiefComplaintsBean cs = (ChiefComplaintsBean) hpinotes.get(i);
				JSONObject ccJsonObj = new JSONObject();
				try
				{
					ccJsonObj.put("id", cs.getShortcutId());
					ccJsonObj.put("name", cs.getShortcutName());
					ccJsonObj.put("value", cs.getShortcutValue());
					ccArr.put(i, ccJsonObj);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				ccBean.add(cs);
			}
			try {
				ccObj.put("Data",ccArr);
				ccObj.put("pages", getNormalPageDetails(rst,limit,offset));
				ccObj.put("offset", offset);
			} catch (Exception e) {
				e.printStackTrace();
			}
			fetchShortcutArr.put(ccObj);
		}
		return fetchShortcutArr;
	}
	
	/**
	 * Method to add shortcut in HPI chief complaints
	*/
	@Override
	public void addShorctutInCC(String complaint) {
		int maxId = 0;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery();
		Root<ChiefComplaints> root = cq.from(ChiefComplaints.class);
		cq.select(cb.max(root.get(ChiefComplaints_.chiefComplaintsId)));
		
		Query query=em.createQuery(cq);
		Object maxIdObj=query.getSingleResult();
		ChiefComplaints chiefComplaints = new ChiefComplaints(); 
		if(maxIdObj!=null)
		{
			maxId = (int) maxIdObj;
		}
		chiefComplaints.setChiefComplaintsId(maxId+1);
		chiefComplaints.setChiefComplaintsName(HUtil.ValidateSingleQuote(complaint));
		chiefComplaints.setChiefComplaintsIsactive(true);
		chiefComplaints.setChiefComplaintsDefault1(true);
		chiefComplaints.setChiefComplaintsDefault2(true);
		chiefComplaintsRepository.saveAndFlush(chiefComplaints);
	}

	/**
	 * Method to add shortcut in HPI Notes
	 */
	@Override
	public void addShorctutInNotes(String id, String shortCutCode,
			String description, String categoryId) {
		int maxId = 0;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery();
		Root<GeneralShortcut> root = cq.from(GeneralShortcut.class);
		cq.select(cb.max(root.get(GeneralShortcut_.generalShortcutId)));
		
		Query query=em.createQuery(cq);
		Object maxIdObj=query.getSingleResult();
		if(maxIdObj!=null)
		{
			maxId = (int) maxIdObj;
		}
		GeneralShortcut generalShortcut = new GeneralShortcut();
		generalShortcut.setGeneralShortcutId(maxId+1);
		generalShortcut.setGeneralShortcutCode(shortCutCode.replaceAll("'","''").trim());
		generalShortcut.setGeneralShortcutDescription(description.replaceAll("'","''"));
		generalShortcut.setGeneralShortcutMapGroupId(Integer.parseInt(categoryId));
		generalShortcut.setGeneralShortcutIsactive(true);
		generalShortcutRepository.saveAndFlush(generalShortcut);
	}

	/**
	 * Method to fetch all symptoms data
	 */
	@Override
	public String fetchSymptom(Integer patientId, Integer chartId,
			Integer encounterId, Integer templateId, String isCompleted,
			String symptomTypeList, String isFollowUp,String clientId) {
		String HPIJSONString = "";
		List<LeafLibrary> leafDetails= getIsAgeBased(templateId);
		List<Object[]> patientReg = getPatientReg(patientId);
		HpiSymptomOrderby hpiSymptomOrderby=getHpiSymptomOrderBy();
		
		boolean isAgeBased=leafDetails.get(0).getLeafLibraryIsagebased()==null?false:leafDetails.get(0).getLeafLibraryIsagebased();
		int orderBy = Integer.parseInt((hpiSymptomOrderby.getHpiSymptomOrderbyTypeid()==null?"-1":hpiSymptomOrderby.getHpiSymptomOrderbyTypeid()).toString());
		int patientGender = 0;
		String DOB = "-1";
		int ageinDay=0;
		try{
			if(patientReg.size()>0)
			{
				for(Object[] values : patientReg) {
					try{
						patientGender = Integer.parseInt(values[0] == null? "" : values[0].toString());
						DOB = (values[1] == null? "f" : values[1].toString());
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			else
			{
				patientGender = 0;
				DOB = "-1";
			}
			DateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd");
	        Date date = srcDf.parse(DOB);
	        DateFormat destDf = new SimpleDateFormat("MM/dd/yyyy");
	        DOB = destDf.format(date);
	        ageinDay = (int)((DateUtil.dateDiff( DateUtil.DATE , DateUtil.getDate(DOB) , new java.util.Date() )));
			String searchStr="";
			List<SymptomAndNegativeSymptomList> dataBean = createNewHPIReadInstance(clientId,patientId, chartId, encounterId);
			JSONObject symptomListDetails=new JSONObject();
			JSONArray symptomListDetailsArray=new JSONArray();
			//[================== Collecting different list of conditions/symptoms
			JSONArray hpiPickerData =  getAllSymptomsData(symptomTypeList,searchStr,patientId,encounterId,isAgeBased,templateId,ageinDay,orderBy,1);
			symptomListDetails.put("hpipickerdata", hpiPickerData);
			
			JSONArray hpiPtProblems =  getAllSymptomsDataProblems(symptomTypeList, searchStr,patientId,encounterId,isAgeBased,templateId,orderBy);
			symptomListDetails.put("hpiPtProblems", hpiPtProblems);
			
			List<PatientClinicalElements> symptomList=dataBean.get(0).getSymptomList();
			JSONArray symptomDataDetailsNew=new JSONArray();
			JSONObject ccNotes = new JSONObject();
			ccNotes.put("symptomname", "Chief complaints");
			ccNotes.put("symptomPrint", "");
			ccNotes.put("symptomid", 10000000);
			ccNotes.put("symptomgwid", "ccnotes");
			JSONObject hpiNotes = new JSONObject();
			hpiNotes.put("symptomname", "Notes");
			hpiNotes.put("symptomPrint", "");
			hpiNotes.put("symptomid", 10000001);
			hpiNotes.put("symptomgwid", "hpinotes");
			symptomDataDetailsNew.put(0,ccNotes);
			symptomDataDetailsNew.put(1,hpiNotes);
			if(symptomList.size()>0){				
				for(int i=0;i<symptomList.size();i++){
					JSONObject symptomDetails=new JSONObject();
					PatientClinicalElements dataRecord = (symptomList.get(i));
					//int savedSymptomId=Integer.parseInt(HUtil.Nz(dataRecord.getHpiSymptom().getHpiSymptomId().toString(),"-1"));
					int chronicConditionStatus=Integer.parseInt(HUtil.Nz(dataRecord.getPatientClinicalElementsValue().toString(),"-1"));

					HashMap<Integer, SymptomBean> HPISymptoms = new HashMap<Integer, SymptomBean>();
					if(chronicConditionStatus!=2){
						//HPISymptoms = getNewReadInstanceForSymptomGWT(savedSymptomId,0,patientGender,ageinDay,patientId,encounterId,orderBy,isAgeBased);
					}else{
						//HPISymptoms = getNewReadInstanceForSymptomGWT(savedSymptomId,1,patientGender,ageinDay,patientId,encounterId,orderBy,isAgeBased);
					}

					/*SymptomBean sBean=HPISymptoms.get(savedSymptomId);
					String savedSymptomGWId=sBean.getSymptomGWId();
					String savedSymptomName=sBean.getSymptomName();
					String savedSymptomPrint=sBean.getSymptomPrintText();
					symptomDetails.put("symptomgwid",savedSymptomGWId );
					symptomDetails.put("symptomid",savedSymptomId );
					symptomDetails.put("symptomname",savedSymptomName );
					symptomDetails.put("symptomPrint",savedSymptomPrint );
					symptomDataDetailsNew.put(i+2,symptomDetails);*/
					}
			}
			String allSuggestedSymptomsQry= getHPIAllSymptoms(orderBy,searchStr,patientId,encounterId,templateId,isAgeBased,ageinDay,2);
			JSONArray allSuggestedSymptoms = new JSONArray(allSuggestedSymptomsQry);
			int length=symptomDataDetailsNew.length();
			for(int i=0;i<allSuggestedSymptoms.length();i++){
				JSONObject symptomSuggested=(JSONObject)allSuggestedSymptoms.get(i);
				String symptomid=symptomSuggested.get("id").toString();
				int checked=0;
				for(int j=0;j<symptomDataDetailsNew.length();j++){
					JSONObject symptomDetail=(JSONObject)symptomDataDetailsNew.get(j);
					if((symptomDetail.get("symptomid").toString().equalsIgnoreCase(symptomid))){
						checked=1;
						break;
					}
				}
					if(checked==0){
						JSONObject addObject=new JSONObject();
						addObject.put("symptomgwid", symptomSuggested.get("gwid").toString());
						addObject.put("symptomid", Integer.parseInt(symptomSuggested.get("id").toString()));
						addObject.put("symptomname", symptomSuggested.get("name").toString());
						addObject.put("symptomPrint", symptomSuggested.get("printtext").toString());
						symptomDataDetailsNew.put(length,addObject);
						length++;
					}
			}
		symptomListDetails.put("hpisymptomdata",symptomDataDetailsNew);
		symptomListDetailsArray.put(symptomListDetails);
		HPIJSONString = symptomListDetailsArray.toString();
		}
		catch(Exception e)
		{
			
		}
		return HPIJSONString;
	}
	
	
	/**
	 * Creates New HPI Read Instance
	 * @param clientId
	 * @param patientId
	 * @param chartId
	 * @param encounterId
	 * @return
	 */
	private List<SymptomAndNegativeSymptomList> createNewHPIReadInstance(String clientId,
			Integer patientId, Integer chartId, Integer encounterId) {
		List<SymptomAndNegativeSymptomList> symptomAndNegativeSymptomList= new ArrayList<SymptomAndNegativeSymptomList>();
		//List<PatientClinicalElements> symptomList = loadPatientsHPISymptomList(clientId,patientId,encounterId);
		//List<PatientClinicalElements> negativeSymptomList = loadPatientsHPInegativeSymptomList(clientId,patientId,encounterId);
		SymptomAndNegativeSymptomList symptomAndNegativeSymptomBean = new SymptomAndNegativeSymptomList();
		//symptomAndNegativeSymptomBean.setSymptomList(symptomList);
		//symptomAndNegativeSymptomBean.setNegativeSymptomList(negativeSymptomList);
		symptomAndNegativeSymptomList.add(symptomAndNegativeSymptomBean);
		return symptomAndNegativeSymptomList;
	}
	
	/**
	 * returns All Symptoms Data
	 * @param symptomTypeList
	 * @param searchStr
	 * @param patientId
	 * @param encounterId
	 * @param isAgeBased
	 * @param templateId
	 * @param ageinDay
	 * @param orderBy
	 * @param mode
	 * @return
	 */
	public JSONArray getAllSymptomsData(String symptomTypeList,
			String searchStr, Integer patientId, Integer encounterId,
			boolean isAgeBased, Integer templateId, int ageinDay, int orderBy,int mode) {
		JSONArray allDefaultSymptoms =new JSONArray();
		String[] symptomsTypeArray = symptomTypeList.split("~!");
		if(symptomsTypeArray[0].equals("1")){                                                       //acute symptoms
			List<HPISymptomBean> hpiSymptom = getWhereHpiSymptomMapping(ageinDay,searchStr,isAgeBased,mode);
			List<HPISymptomBean> hpiSymptomBeanList = getWhereHpiSympGwidNotInPatElemGwid(ageinDay,templateId,searchStr,patientId,isAgeBased,mode);
			List<String> detOpGwids = new ArrayList<String>();
			
			//remove duplicates from hpiSymptom

			for (HPISymptomBean detOption : hpiSymptom) {
				detOpGwids.add(detOption.getHpiSymptomGwid());
			}

			for(int i=0;i<hpiSymptomBeanList.size();i++){
				if(detOpGwids.contains(hpiSymptomBeanList.get(i).getHpiSymptomGwid())){
					hpiSymptomBeanList.remove(i);
					i--;
				}
			}	

			hpiSymptom.addAll(hpiSymptomBeanList);
			List<HPISymptomBean> hpiSymptomBeanList2 = getWhereHpiSympGwidNotInHpiSympMappingGwid(ageinDay,templateId,searchStr,patientId,isAgeBased,mode,encounterId);
			List<String> stmptomGwids = new ArrayList<String>();
			//remove duplicates from hpiSymptom
					for (HPISymptomBean symOption : hpiSymptom) {
						stmptomGwids.add(symOption.getHpiSymptomGwid());
					}

					for(int i=0;i<hpiSymptomBeanList2.size();i++){
						if(stmptomGwids.contains(hpiSymptomBeanList2.get(i).getHpiSymptomGwid())){
							hpiSymptomBeanList2.remove(i);
							i--;
						}
					}
				hpiSymptom.addAll(hpiSymptomBeanList2);
				int size = 0;
				for(int i=0;i < hpiSymptom.size(); i++) {
					JSONObject hpiSymptomObj= new JSONObject();
					try {
						hpiSymptomObj.put("id", hpiSymptom.get(i).getHpiSymptomId() == null?"":hpiSymptom.get(i).getHpiSymptomId());
						hpiSymptomObj.put("gwid", hpiSymptom.get(i).getHpiSymptomGwid() == null?"":hpiSymptom.get(i).getHpiSymptomGwid());
						hpiSymptomObj.put("name", hpiSymptom.get(i).getHpiSymptomName() == null?"":hpiSymptom.get(i).getHpiSymptomName());
						hpiSymptomObj.put("printtext", hpiSymptom.get(i).getHpiSymptomPrinttext() == null?"":hpiSymptom.get(i).getHpiSymptomPrinttext());
						hpiSymptomObj.put("hitcount", hpiSymptom.get(i).getHpiSymptomHitcount() == null?"":hpiSymptom.get(i).getHpiSymptomHitcount());
						hpiSymptomObj.put("retain", hpiSymptom.get(i).getHpiSymptomRetaincase() == null?"":hpiSymptom.get(i).getHpiSymptomRetaincase());
						hpiSymptomObj.put("orderby", hpiSymptom.get(i).getHpiSymptomOrderby() == null?"":hpiSymptom.get(i).getHpiSymptomOrderby());
						hpiSymptomObj.put("limitorder", (hpiSymptom.get(i).getLimitOrder() == null || hpiSymptom.get(i).getLimitOrder() == "1")?"3":hpiSymptom.get(i).getLimitOrder());
						allDefaultSymptoms.put(size, hpiSymptomObj);
					}
					catch (JSONException e) {
						e.printStackTrace();
					}
					size++;
				}
				List<Object> partStr = getHpiSymptomGwid(patientId);
				/*if(partStr.size()>0)
				{
					List<Object[]> patElem = getPatElemList(patientId,searchStr,partStr,encounterId,orderBy,mode,isAgeBased,ageinDay);
					for(Object[] values : patElem) {
						JSONObject patElemObj= new JSONObject();
						try {
							String limit = values[7] == null?"":values[7].toString();
							patElemObj.put("id", values[0] == null?"":values[0]); 			//HpiSymptomId()
							patElemObj.put("gwid", values[1] == null?"":values[1]); 		//HpiSymptomGwid()
							patElemObj.put("name", values[2] == null?"":values[2]); 		//HpiSymptomName()
							patElemObj.put("printtext", values[3] == null?"":values[3]); 	//HpiSymptomPrinttext()
							patElemObj.put("hitcount", values[4] == null?"":values[4]); 	//HpiSymptomHitcount()
							patElemObj.put("retain", values[5] == null?"":values[5]);		//HpiSymptomRetaincase()
							patElemObj.put("orderby", values[6] == null?"":values[6]); 		//HpiSymptomOrderby()
							patElemObj.put("limitorder", limit.equalsIgnoreCase("1")?"3":limit); 	//LimitOrder()
							allDefaultSymptoms.put(size, patElemObj);
						}
						catch (JSONException e) {
							e.printStackTrace();
						}
						size++;
					}
				}*/
		}
		List<JSONObject> jsonValues = new ArrayList<JSONObject>();
		for (int i = 0; i < allDefaultSymptoms.length(); i++)
		{
			try
			{
				jsonValues.add(allDefaultSymptoms.getJSONObject(i));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		if(searchStr.trim().equals("")){
			if(orderBy == 1)
			{
				jsonList = sortBy(jsonValues,"hitcount","limitorder");
			}
			else if(orderBy == 2)
			{
				jsonList = sortBy(jsonValues,"name","limitorder");
			}
			else if(orderBy == 3)
			{
				jsonList = sortBy(jsonValues,"orderby","limitorder");
			}
		}else{
			if(orderBy == 1)
			{
				jsonList = sortBy(jsonValues,"limitorder","hitcount");
			}
			else if(orderBy == 2)
			{
				jsonList = sortBy(jsonValues,"limitorder","name");
			}
			else if(orderBy == 3)
			{
				jsonList = sortBy(jsonValues,"limitorder","orderby");
			}
		}
		JSONArray returnJson = new JSONArray();
		try
		{
			if(jsonList.size()>=10)
			{
				for(int j=0;j<10;j++)
				{
					returnJson.put(j, jsonList.get(j));
				}
			}
			else
			{
				for(int j=0;j<jsonList.size();j++)
				{
					returnJson.put(j, jsonList.get(j));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnJson;
	}

	/**
	 * Method to get all Symptoms
	 * @return
	 */
	public String getHPIAllSymptoms(int orderBy, String searchStr,
			Integer patientId, Integer encounterId, Integer templateId,
			boolean isAgeBased, int ageinDay, int mode) {
		JSONArray allSymptomsArray = new JSONArray();
		List<HPISymptomBean> hpiSymptom = getWhereHpiSymptomMapping(ageinDay,searchStr,isAgeBased,mode);
		List<HPISymptomBean> hpiSymptomBeanList = getWhereHpiSympGwidNotInPatElemGwid(ageinDay,templateId,searchStr,patientId,isAgeBased,mode);
		
		List<String> detOpGwids = new ArrayList<String>();
		
		//remove duplicates from hpiSymptom

		for (HPISymptomBean detOption : hpiSymptom) {
			detOpGwids.add(detOption.getHpiSymptomGwid());
		}

		for(int i=0;i<hpiSymptomBeanList.size();i++){
			if(detOpGwids.contains(hpiSymptomBeanList.get(i).getHpiSymptomGwid())){
				hpiSymptomBeanList.remove(i);
				i--;
			}
		}	

		hpiSymptom.addAll(hpiSymptomBeanList);
		
		List<HPISymptomBean> hpiSymptomBeanList2 = getWhereHpiSympGwidNotInHpiSympMappingGwid(ageinDay,templateId,searchStr,patientId,isAgeBased,mode,encounterId);
		List<String> stmptomGwids = new ArrayList<String>();
		//remove duplicates from hpiSymptom

				for (HPISymptomBean symOption : hpiSymptom) {
					stmptomGwids.add(symOption.getHpiSymptomGwid());
				}

				for(int i=0;i<hpiSymptomBeanList2.size();i++){
					if(stmptomGwids.contains(hpiSymptomBeanList2.get(i).getHpiSymptomGwid())){
						hpiSymptomBeanList2.remove(i);
						i--;
					}
				}
			hpiSymptom.addAll(hpiSymptomBeanList2);
			int size = 0;
			if(hpiSymptom.size() > 0)
			{
				for(int i=0;i<hpiSymptom.size();i++)
				{
					JSONObject hpiSymptomObj= new JSONObject();
					try {
						hpiSymptomObj.put("id", hpiSymptom.get(i).getHpiSymptomId()== null?"":hpiSymptom.get(i).getHpiSymptomId());
						hpiSymptomObj.put("gwid", hpiSymptom.get(i).getHpiSymptomGwid()== null?"":hpiSymptom.get(i).getHpiSymptomGwid());
						hpiSymptomObj.put("name", hpiSymptom.get(i).getHpiSymptomName()== null?"":hpiSymptom.get(i).getHpiSymptomName());
						hpiSymptomObj.put("printtext", hpiSymptom.get(i).getHpiSymptomPrinttext()== null?"":hpiSymptom.get(i).getHpiSymptomPrinttext());
						hpiSymptomObj.put("hitcount", hpiSymptom.get(i).getHpiSymptomHitcount()== null?"":hpiSymptom.get(i).getHpiSymptomHitcount());
						hpiSymptomObj.put("retain", hpiSymptom.get(i).getHpiSymptomRetaincase() == null?"":hpiSymptom.get(i).getHpiSymptomRetaincase());
						hpiSymptomObj.put("orderby", hpiSymptom.get(i).getHpiSymptomOrderby()== null?"":hpiSymptom.get(i).getHpiSymptomOrderby());
						hpiSymptomObj.put("limitorder", hpiSymptom.get(i).getLimitOrder()== null?"":hpiSymptom.get(i).getLimitOrder());
						allSymptomsArray.put(size, hpiSymptomObj);
					}
					catch (JSONException e) {
						e.printStackTrace();
					}
					size++;
				}
			}
			
			//List<Object> partStr = getHpiSymptomGwid(patientId);
			/*if(partStr.size()>0)
			{
				List<Object[]> patElem = getPatElemList(patientId,searchStr,partStr,encounterId,orderBy, mode, isAgeBased, ageinDay);
				for(Object[] values : patElem) {
					JSONObject patElemObj= new JSONObject();
					try {
						patElemObj.put("id", values[0]== null?"":values[0]);			//HpiSymptomId
						patElemObj.put("gwid", values[1]== null?"":values[1]);			//HpiSymptomGwid
						patElemObj.put("name", values[2]== null?"":values[2]);			//HpiSymptomName
						patElemObj.put("printtext", values[3]== null?"":values[3]);		//HpiSymptomPrinttext
						patElemObj.put("hitcount", values[4]== null?"":values[4]);		//HpiSymptomHitcount
						patElemObj.put("type", values[5]== null?"":values[5]);			//HpiSymptomType
						patElemObj.put("orderby", values[6]== null?"":values[6]);		//HpiSymptomOrderby
						patElemObj.put("limitorder", values[7]== null?"":values[7]);	//HpiSymptomRetaincase
						allSymptomsArray.put(size, patElemObj);
					}
					catch (JSONException e) {
						e.printStackTrace();
					}
					size++;
				}
			}*/
			List<JSONObject> jsonValues = new ArrayList<JSONObject>();
			for (int i = 0; i < allSymptomsArray.length(); i++)
			{
				try
				{
					jsonValues.add(allSymptomsArray.getJSONObject(i));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			List<JSONObject> jsonList = new ArrayList<JSONObject>();
			JSONArray returnJson = new JSONArray();
			if(jsonValues.size()>0)
			{
				if(searchStr.trim().equals("")){
					if(orderBy == 1)
					{
						jsonList = sortBy(jsonValues,"hitcount","limitorder");
					}
					else if(orderBy == 2)
					{
						jsonList = sortBy(jsonValues,"name","limitorder");
					}
					else if(orderBy == 3)
					{
						jsonList = sortBy(jsonValues,"orderby","limitorder");
					}
				}else{
					if(orderBy == 1)
					{
						jsonList = sortBy(jsonValues,"limitorder","hitcount");
					}
					else if(orderBy == 2)
					{
						jsonList = sortBy(jsonValues,"limitorder","name");
					}
					else if(orderBy == 3)
					{
						jsonList = sortBy(jsonValues,"limitorder","orderby");
					}
				}
				try
				{
					if(jsonList.size()>=10)
					{
						for(int j=0;j<10;j++)
						{
							returnJson.put(j, jsonList.get(j));
						}
					}
					else
					{
						for(int j=0;j<jsonList.size();j++)
						{
							returnJson.put(j, jsonList.get(j));
						}
					}
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			return returnJson.toString();
	}

	/**
	 * Method to get patient element list
	 * @param isAgeBased 
	 * @param mode 
	 * @param ageinDay 
	 * @return
	 */
	/*private List<Object[]> getPatElemList(Integer patientId, String searchStr, List<Object> partStr, Integer encounterId, int orderBy, int mode, boolean isAgeBased, int ageinDay) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<PatientClinicalElements> root = cq.from(PatientClinicalElements.class);
		//Join<PatientClinicalElements, HpiSymptom> condJoin = root.join(PatientClinicalElements_.hpiSymptom,JoinType.INNER);
		//Join<HpiSymptom, ClinicalElements> leftJoinClinicalElem;
		//Join<ClinicalElements, ClinicalElementsCondition> clinicalCondJoin = null;
		if(isAgeBased){
			if(mode==2)
			{
				//leftJoinClinicalElem = condJoin.join(HpiSymptom_.clinicalElements,JoinType.LEFT);
				//clinicalCondJoin = leftJoinClinicalElem.join(ClinicalElements_.clinicalElementsConditions,JoinType.LEFT);
			}
		}
		Expression<Integer> inte = builder.<Integer>selectCase().when(builder.equal(builder.locate(builder.upper(condJoin.get(HpiSymptom_.hpiSymptomName)),builder.literal(searchStr.split(" ")[0].toUpperCase())),
				builder.literal(0)),
				-99999).<Integer>otherwise(builder.locate(builder.upper(condJoin.get(HpiSymptom_.hpiSymptomName)),builder.literal(searchStr.split(" ")[0].toUpperCase())));
		cq.multiselect(condJoin.get(HpiSymptom_.hpiSymptomId),
				condJoin.get(HpiSymptom_.hpiSymptomGwid),
				condJoin.get(HpiSymptom_.hpiSymptomName),
				condJoin.get(HpiSymptom_.hpiSymptomPrinttext),
				condJoin.get(HpiSymptom_.hpiSymptomHitcount),
				condJoin.get(HpiSymptom_.hpiSymptomRetaincase),
				condJoin.get(HpiSymptom_.hpiSymptomOrderby),
				inte.alias("limitOrder"));
		List<Predicate> predList = new LinkedList<Predicate>();
		for(int i=0;i<partStr.size();i++){
			Object symptomId = partStr.get(i);
			int prevEncId=Integer.parseInt(getPreviousEncounterId(patientId,symptomId,encounterId));
			if(prevEncId!=-1){
				Predicate p1 = builder.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid), symptomId.toString());
				Predicate p2 = builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), prevEncId);
				Predicate p3 = builder.not(root.get(PatientClinicalElements_.patientClinicalElementsValue).in("0","3"));
				predList.add(builder.and(p1,p2,p3));
			}
		}
		Predicate cond1 = builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
		Predicate cond2 = builder.or(predList.toArray(new Predicate[] {}));
		
		if(isAgeBased && mode==2)
		{
				//Predicate add1 = builder.lessThan(clinicalCondJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage),ageinDay);
				//Predicate add2 = builder.greaterThanOrEqualTo(clinicalCondJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage), ageinDay);
				if(!(searchStr.trim().equals(""))) {
					Predicate p4 = builder.like(builder.lower(condJoin.get(HpiSymptom_.hpiSymptomName)), getLikePattern(searchStr.replaceAll("'", "''")));
					String startsWithArr[] = searchStr.split(" "); 
					if(startsWithArr.length > 1){
						for(int i=0;i<startsWithArr.length;i++){
							Predicate p5 = builder.like(builder.lower(condJoin.get(HpiSymptom_.hpiSymptomName)), getLikePattern(startsWithArr[i].replaceAll("'", "''")));
							predList.add(p5);
						}
						Predicate predListCond = builder.or(predList.toArray(new Predicate[] {}));
						Predicate combPred = builder.or(p4,predListCond);
						cq.where(cond1,cond2,combPred,add1,add2);
					}
					else
					{
						cq.where(cond1,cond2,p4,add1,add2);
					}
				}
				else
				{
					cq.where(cond1,cond2,add1,add2);
				}
		}
		else
		{
			if(!(searchStr.trim().equals(""))) {
				Predicate p4 = builder.like(builder.lower(condJoin.get(HpiSymptom_.hpiSymptomName)), getLikePattern(searchStr.replaceAll("'", "''")));
				String startsWithArr[] = searchStr.split(" "); 
				if(startsWithArr.length > 1){
					for(int i=0;i<startsWithArr.length;i++){
						Predicate p5 = builder.like(builder.lower(condJoin.get(HpiSymptom_.hpiSymptomName)), getLikePattern(startsWithArr[i].replaceAll("'", "''")));
						predList.add(p5);
					}
					Predicate predListCond = builder.or(predList.toArray(new Predicate[] {}));
					Predicate combPred = builder.or(p4,predListCond);
					cq.where(cond1,cond2,combPred);
				}
				else
				{
					cq.where(cond1,cond2,p4);
				}
			}
			else
			{
				cq.where(cond1,cond2);
			}
		}
		List<Object[]> obj = em.createQuery(cq).getResultList();
		return obj;
	}*/


	public HashMap<Integer, SymptomBean> getNewReadInstanceForSymptomGWT(int symptomId, int isFollowUp,
			int patientGender, int ageinDay, Integer patientId,
			Integer encounterId, int orderBy, boolean isAgeBased) {
		List<HpiSymptom> symptom = getSymptomQuery(symptomId,encounterId);
		HashMap<Integer, SymptomBean> HPISymptoms=new HashMap<Integer, SymptomBean>();
		SymptomBean sBean= new SymptomBean();
		for(int j=0;j<symptom.size();j++){
			HpiSymptom symptomRecord = symptom.get(j);
			String symptomGWId= symptomRecord.getHpiSymptomGwid() == null?"-1":symptomRecord.getHpiSymptomGwid();
			sBean.setSymptomGWId(symptomGWId);
			sBean.setSymptomId(Integer.parseInt(symptomRecord.getHpiSymptomId() == null?"-1":symptomRecord.getHpiSymptomId().toString()));
			sBean.setSymptomName(symptomRecord.getHpiSymptomName() == null?"":symptomRecord.getHpiSymptomName().toString());
			sBean.setSymptomComments(symptomRecord.getHpiSymptomComments() == null?"":symptomRecord.getHpiSymptomComments());
			sBean.setSymptomPrintText(symptomRecord.getHpiSymptomPrinttext() == null?"":symptomRecord.getHpiSymptomPrinttext().toString());
			sBean.setSymptomType(Integer.parseInt(symptomRecord.getHpiSymptomType() == null?"-1":symptomRecord.getHpiSymptomType().toString()));
			sBean.setRetainCase(symptomRecord.getHpiSymptomRetaincase() == null?"t":symptomRecord.getHpiSymptomRetaincase().toString());
			List<HpiSymptomQualifier> hpiSymptomQualifier=hpiSymptomQualifierRepository.findAll(HpiSpecification.getQualifierQuery(Integer.toString(symptomId),orderBy));
			LinkedHashMap<String, QualifierBean> hMapQualifier= new LinkedHashMap<String, QualifierBean>();
			for(int k=0;k<hpiSymptomQualifier.size();k++){
				HpiSymptomQualifier hpiSymptomQualifierlist =  hpiSymptomQualifier.get(k);
				QualifierBean qBean=new QualifierBean();
				qBean.setQualifierName(hpiSymptomQualifierlist.getHpiSymptomQualifierName() == null?"":hpiSymptomQualifierlist.getHpiSymptomQualifierName());
				qBean.setQualifierEandM(hpiSymptomQualifierlist.getHpiSymptomQualifierEandm() == null?"-":hpiSymptomQualifierlist.getHpiSymptomQualifierEandm());
				qBean.setQualifierId(Integer.parseInt(hpiSymptomQualifierlist.getHpiSymptomQualifierId() == null?"-1":hpiSymptomQualifierlist.getHpiSymptomQualifierId().toString()));
				qBean.setQualifierGWId(hpiSymptomQualifierlist.getHpiSymptomQualifierGwid() == null?"":hpiSymptomQualifierlist.getHpiSymptomQualifierGwid());
				qBean.setqualifierPrintText(hpiSymptomQualifierlist.getHpiSymptomQualifierPrinttext() == null?"":hpiSymptomQualifierlist.getHpiSymptomQualifierPrinttext());
				int qualifierId=Integer.parseInt(hpiSymptomQualifierlist.getHpiSymptomQualifierId() == null?"-1":hpiSymptomQualifierlist.getHpiSymptomQualifierId().toString());
				List<Object[]> hpiElement=getElementQuery(qualifierId,orderBy,patientGender,ageinDay,patientId,encounterId,isAgeBased);
				
				LinkedHashMap<String, HPIElementBean> hMapElement = new LinkedHashMap<String, HPIElementBean>();
				
				if(isAgeBased)
				{
					List<String> detOpGwids = new ArrayList<String>();
					List<Object[]> afterUnionList1=getElementQueryAfterUnion(qualifierId,orderBy,patientGender,ageinDay,patientId,encounterId,isAgeBased);
					for (Object[] value : hpiElement) {
						detOpGwids.add(value[0].toString());
					}
					int i = 0;
					for(Object[] value : afterUnionList1){
						if(detOpGwids.contains(value[0])){
							afterUnionList1.remove(i);
							i--;
						}
						i++;
					}	

					hpiElement.addAll(afterUnionList1);
				}

				for(Object[] value : hpiElement){
					HPIElementBean eBean=new HPIElementBean();
					String elementGWId=value[0] == null?"-1":value[0].toString();
					//int elementDatatype= (int) (value[6] == null?-1:value[6]);
					eBean.setElementGWId(elementGWId);
					eBean.setElementname(value[1] == null?"":value[1].toString());
					eBean.setElementPrintText(value[2] == null?"":value[2].toString());
					//eBean.setClinicalElementName(value[5] == null?"":value[5].toString());
					//eBean.setClinicalElementDataType(elementDatatype);
					//eBean.setClinicalElementIsActive(Boolean.parseBoolean((value[7] == null?false:value[7]).toString()));
					//eBean.setClinicalElementIsHistory(Boolean.parseBoolean((value[8] == null?false:value[8]).toString()));
					//eBean.setClinicalElementIsEpisode(Boolean.parseBoolean((value[9] == null?false:value[9]).toString()));
					//eBean.setClinicalElementIsSelect((Integer)(value[12] == null?-1:value[12]));
					//eBean.setClinicalElementGender((Integer)(value[11] == null?-1:value[11]));
					//eBean.setClinicalElementTextDimension((value[10] == null?-1:value[10]).toString());
					//eBean.setValue((value[13] == null?-1:value[13]).toString());					
					
					hMapElement.put(elementGWId,eBean);
				}
				qBean.setQualifierElements(hMapElement);
				hMapQualifier.put(qualifierId+"", qBean);
		}
		sBean.setSymptomQualifiers(hMapQualifier);	
		HPISymptoms.put(symptomId, sBean);
	}
	return HPISymptoms;

	}
 

	private List<Object[]> getElementQueryAfterUnion(int qualifierId,
			int orderBy, int patientGender, int ageinDay, Integer patientId,
			Integer encounterId, boolean isAgeBased) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
		Root<HpiElement> root = cq.from(HpiElement.class);
		/*Join<HpiElement,ClinicalElements> gwIdJoin = root.join(HpiElement_.clinicalElements,JoinType.INNER);
		Predicate symptomGwidPred=(cb.and(gwIdJoin.get(ClinicalElements_.clinicalElementsGender).in("0",patientGender)));
		gwIdJoin.on(symptomGwidPred);
		Join<HpiElement,PatientClinicalElements> gwIdPatientJoin = root.join(HpiElement_.patientClinicalElements,JoinType.LEFT);
		Predicate patientIdPred=(cb.equal(gwIdPatientJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId));
		Predicate patientEncIdPred=(cb.equal(gwIdPatientJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId));
		gwIdPatientJoin.on(patientIdPred,patientEncIdPred);*/
		//Join<ClinicalElements,ClinicalElementsCondition> clinicalElementsConditionJoin = gwIdJoin.join(ClinicalElements_.clinicalElementsConditions,JoinType.LEFT);	
		
		cq.multiselect(root.get(HpiElement_.hpiElementGwid),
				root.get(HpiElement_.hpiElementName),
				root.get(HpiElement_.hpiElementPrinttext),
				root.get(HpiElement_.hpiElementOrderby)
				/*gwIdJoin.get(ClinicalElements_.clinicalElementsGwid),
				gwIdJoin.get(ClinicalElements_.clinicalElementsName),
				gwIdJoin.get(ClinicalElements_.clinicalElementsDatatype),
				gwIdJoin.get(ClinicalElements_.clinicalElementsIsactive),
				gwIdJoin.get(ClinicalElements_.clinicalElementsIshistory),
				gwIdJoin.get(ClinicalElements_.clinicalElementsIsepisode),
				gwIdJoin.get(ClinicalElements_.clinicalElementsTextDimension),
				gwIdJoin.get(ClinicalElements_.clinicalElementsGender),
				gwIdJoin.get(ClinicalElements_.clinicalElementsIsselect),
				gwIdPatientJoin.get(PatientClinicalElements_.patientClinicalElementsValue)*/
				);
		
		Predicate qualifierIdPred=(cb.equal(root.get(HpiElement_.hpiElementQualifierid),qualifierId));
		
	//	Predicate andPred1= cb.and(gwIdPatientJoin.get(PatientClinicalElements_.patientClinicalElementsValue).isNull(),clinicalElementsConditionJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage).isNull(),clinicalElementsConditionJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage).isNull());
	//	Predicate andPred2= cb.and(cb.lessThan(clinicalElementsConditionJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage), ageinDay),cb.greaterThanOrEqualTo(clinicalElementsConditionJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage), ageinDay));
	//	Predicate orPred= cb.or(andPred1,andPred2);
		
		Predicate elemId=cb.and(cb.equal(root.get(HpiElement_.hpiElementIsactive),true));
		cq.where(qualifierIdPred/*,orPred*/,elemId);
		List<Object[]> obj=em.createQuery(cq).getResultList();
		return obj;
	}

	private List<Object[]> getElementQuery(int qualifierId, int orderBy,
			int patientGender, int ageinDay, Integer patientId,
			Integer encounterId, boolean isAgeBased) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
		Root<HpiElement> root = cq.from(HpiElement.class);
		/*Join<HpiElement,ClinicalElements> gwIdJoin = root.join(HpiElement_.clinicalElements,JoinType.INNER);
		Predicate symptomGwidPred=gwIdJoin.get(ClinicalElements_.clinicalElementsGender).in("0",patientGender);
		gwIdJoin.on(symptomGwidPred);*/
		
		/*Join<HpiElement,PatientClinicalElements> gwIdPatientJoin = root.join(HpiElement_.patientClinicalElements,JoinType.LEFT);
		Predicate patientIdPred=(cb.equal(gwIdPatientJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId));
		Predicate patientEncIdPred=(cb.equal(gwIdPatientJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId));
		gwIdPatientJoin.on(patientIdPred,patientEncIdPred);*/
				
		cq.multiselect(root.get(HpiElement_.hpiElementGwid),
				root.get(HpiElement_.hpiElementName),
				root.get(HpiElement_.hpiElementPrinttext),
				root.get(HpiElement_.hpiElementOrderby)
				/*gwIdJoin.get(ClinicalElements_.clinicalElementsGwid),
				gwIdJoin.get(ClinicalElements_.clinicalElementsName),
				gwIdJoin.get(ClinicalElements_.clinicalElementsDatatype),
				gwIdJoin.get(ClinicalElements_.clinicalElementsIsactive),
				gwIdJoin.get(ClinicalElements_.clinicalElementsIshistory),
				gwIdJoin.get(ClinicalElements_.clinicalElementsIsepisode),
				gwIdJoin.get(ClinicalElements_.clinicalElementsTextDimension),
				gwIdJoin.get(ClinicalElements_.clinicalElementsGender),
				gwIdJoin.get(ClinicalElements_.clinicalElementsIsselect),
				gwIdPatientJoin.get(PatientClinicalElements_.patientClinicalElementsValue)*/
				);
		
		Predicate hpiPred=cb.and(cb.equal(root.get(HpiElement_.hpiElementQualifierid),qualifierId),cb.equal(root.get(HpiElement_.hpiElementIsactive), true));
		
		if(isAgeBased)
		{
			//Predicate andPred=(cb.and(hpiPred,gwIdPatientJoin.get(PatientClinicalElements_.patientClinicalElementsValue).isNotNull()));
			cq.where(hpiPred/*,andPred*/);
		}
		else
		{
			cq.where(hpiPred);
		}
		
		if(orderBy == 2 )
			cq.orderBy(cb.asc(root.get(HpiElement_.hpiElementName)));
		if(orderBy == 3 )
			cq.orderBy(cb.asc(root.get(HpiElement_.hpiElementOrderby)));
		
		List<Object[]> obj = em.createQuery(cq).getResultList();
		return obj;
	}

	public List<HpiSymptom> getSymptomQuery(int savedSymptomId,
			Integer encounterId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<HpiSymptom> root = cq.from(HpiSymptom.class);
		//Join<HpiSymptom, ClinicalSystem> condJoin = root.join(HpiSymptom_.clinicalSystem,JoinType.INNER);
		//Join<HpiSymptom, PatientClinicalElements> condJoin2 = root.join(HpiSymptom_.patientClinicalElements,JoinType.INNER);
		cq.multiselect(root.get(HpiSymptom_.hpiSymptomId),
				root.get(HpiSymptom_.hpiSymptomSystemId),
				root.get(HpiSymptom_.hpiSymptomGwid),
				root.get(HpiSymptom_.hpiSymptomName),
				root.get(HpiSymptom_.hpiSymptomHitcount),
				root.get(HpiSymptom_.hpiSymptomComments),
				root.get(HpiSymptom_.hpiSymptomType),
				root.get(HpiSymptom_.hpiSymptomPrinttext),
				root.get(HpiSymptom_.hpiSymptomRetaincase)
				//condJoin.get(ClinicalSystem_.clinicalSystemName)
				);
		cq.where(builder.and(builder.equal(root.get(HpiSymptom_.hpiSymptomId),savedSymptomId)/*,builder.equal(condJoin2.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId)*/));
		List<Object[]> obj = em.createQuery(cq).getResultList();
		List<HpiSymptom> hpiSymptom = new ArrayList<HpiSymptom>();
		for(Object[] values : obj) {
			HpiSymptom hpiSym = new HpiSymptom();
			//ClinicalSystem clinicalSystem = new ClinicalSystem();
			Integer symptomId = (Integer) (values[0] == null?-1:values[0]);
			Integer systemId = (Integer) (values[1] == null?-1:values[1]);
			String symptomGwid = (String) (values[2] == null?"":values[2]);
			String symptomName = (String) (values[3] == null?"":values[3]);
			Integer hitCount = (Integer) (values[4] == null?-1:values[4]);
			String symptomComments = (String) (values[5] == null?"":values[5]);
			Integer symptomType = (Integer) (values[6] == null?-1:values[6]);
			String symptomPrintText = (String) (values[7] == null?"":values[7]);
			Boolean retainCase = (Boolean) (values[8] == null?false:values[8]);
			//String clinicalSystemName = (String) (values[9] == null?"":values[9]);
			hpiSym.setHpiSymptomId(symptomId);
			hpiSym.setHpiSymptomSystemId(systemId);
			hpiSym.setHpiSymptomGwid(symptomGwid);
			hpiSym.setHpiSymptomName(symptomName);
			hpiSym.setHpiSymptomHitcount(hitCount);
			hpiSym.setHpiSymptomComments(symptomComments);
			hpiSym.setHpiSymptomType(symptomType);
			hpiSym.setHpiSymptomPrinttext(symptomPrintText);
			hpiSym.setHpiSymptomRetaincase(retainCase);
			//clinicalSystem.setClinicalSystemName(clinicalSystemName);
			//hpiSym.setClinicalSystem(clinicalSystem);
			hpiSymptom.add(hpiSym);
		}
		return hpiSymptom;
	}

	/**
	 * Method to get all chronic symptom problems
	 * @return
	 */
	private JSONArray getAllSymptomsDataProblems(String symptomTypeList,
			String startsWith, Integer patientId, Integer encounterId,
			boolean isAgeBased, Integer templateId, int orderBy) {
		JSONArray chronicSymptomsFollowup1= new JSONArray();
		String[] symptomsTypeArray = symptomTypeList.split("~!");
		if(symptomsTypeArray[2].equals("1")){                                                       //patient's chronic conditions/symptoms
			chronicSymptomsFollowup1 = getDxBasedSymptoms(patientId, encounterId,orderBy, startsWith);
		}
		return chronicSymptomsFollowup1;
	}
	
	/**
	 * Method to get all Dx Based Symptom Query
	 * @return
	 */
	private JSONArray getDxBasedSymptoms(Integer patientId, Integer encounterId, int orderBy, String startsWith) {
		List<Integer> hpiSymptomIdList = getHPIDxMap(patientId,encounterId);
		JSONArray chronicSymptomsFollowup1= new JSONArray();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<HpiSymptom> root = cq.from(HpiSymptom.class);
		//Join<HpiSymptom, ClinicalSystem> condJoin = root.join(HpiSymptom_.clinicalSystem,JoinType.INNER);
		cq.multiselect(root.get(HpiSymptom_.hpiSymptomId),
				root.get(HpiSymptom_.hpiSymptomGwid),
				root.get(HpiSymptom_.hpiSymptomName),
				root.get(HpiSymptom_.hpiSymptomPrinttext),
				root.get(HpiSymptom_.hpiSymptomHitcount),
				root.get(HpiSymptom_.hpiSymptomType),
				builder.literal("1").alias("Status"),
				root.get(HpiSymptom_.hpiSymptomOrderby),
				root.get(HpiSymptom_.hpiSymptomRetaincase));
		if(!(startsWith.trim().equals(""))) {
			if(hpiSymptomIdList.isEmpty()){
				cq.where(builder.and(/*builder.equal(condJoin.get(ClinicalSystem_.clinicalSystemIsactive),true),*/root.get(HpiSymptom_.hpiSymptomId).in(-1)),builder.like(builder.lower(root.get(HpiSymptom_.hpiSymptomName)),getLikePattern(startsWith.replaceAll("'", "''"))));
			}
			else
			{
				cq.where(builder.and(/*builder.equal(condJoin.get(ClinicalSystem_.clinicalSystemIsactive),true),*/root.get(HpiSymptom_.hpiSymptomId).in(hpiSymptomIdList)),builder.like(builder.lower(root.get(HpiSymptom_.hpiSymptomName)),getLikePattern(startsWith.replaceAll("'", "''"))));
			}
		}
		else
		{
			//Predicate p1= builder.equal(condJoin.get(ClinicalSystem_.clinicalSystemIsactive),true);
			Predicate p2;
			if(hpiSymptomIdList.isEmpty())
			{
				p2= root.get(HpiSymptom_.hpiSymptomId).in(-1);
			}
			else
			{
				p2= root.get(HpiSymptom_.hpiSymptomId).in(hpiSymptomIdList);
			}
			cq.where(builder.and(/*p1,*/p2));
		}
		List<Object[]> hpiList = em.createQuery(cq).getResultList();
		int size = 0;
		if(hpiList.size()>0)
		{
			for(Object[] values : hpiList) {
				JSONObject hpiListObj= new JSONObject();
				try {
					hpiListObj.put("id", values[0] == null?"":values[0]);		//hpiSymptomId
					hpiListObj.put("gwid", values[1]== null?"":values[1]);		//hpiSymptomGwid
					hpiListObj.put("name", values[2]== null?"":values[2]);		//hpiSymptomName
					hpiListObj.put("printtext", values[3]== null?"":values[3]);	//hpiSymptomPrinttext
					hpiListObj.put("hitcount", values[4]== null?"":values[4]);	//hpiSymptomHitcount
					hpiListObj.put("type", values[5]== null?"":values[5]);		//hpiSymptomType
					hpiListObj.put("status", values[6]== null?"":values[6]);	//Status
					hpiListObj.put("orderby", values[7]== null?"":values[7]);	//hpiSymptomOrderby
					hpiListObj.put("retain", values[8]== null?"":values[8]);	//hpiSymptomRetaincase
					chronicSymptomsFollowup1.put(size, hpiListObj);
				}
				catch (JSONException e) {
					e.printStackTrace();
				}
				size++;
			}
		}
		List<Object> partStr = getHpiSymptomGwid(patientId);
		/*if(partStr.size()>0)
		{
			List<Object[]> patElem = getpatElemData(patientId,startsWith,partStr,encounterId,hpiSymptomIdList,orderBy);
			for(Object[] values : patElem) {
				JSONObject patElemObj= new JSONObject();
				try {
					patElemObj.put("id", values[0]== null?"":values[0]);			//hpiSymptomId
					patElemObj.put("gwid", values[1]== null?"":values[1]);			//hpiSymptomGwid
					patElemObj.put("name", values[2]== null?"":values[2]);			//hpiSymptomName
					patElemObj.put("printtext", values[3]== null?"":values[3]);		//hpiSymptomPrinttext
					patElemObj.put("hitcount", values[4]== null?"":values[4]);		//hpiSymptomHitcount
					patElemObj.put("type", values[5]== null?"":values[5]);			//hpiSymptomType
					patElemObj.put("status", values[6]== null?"":values[6]);		//Status
					patElemObj.put("orderby", values[7]== null?"":values[7]);		//hpiSymptomOrderby
					patElemObj.put("retain", values[8]== null?"":values[8]);		//hpiSymptomRetaincase
					chronicSymptomsFollowup1.put(size, patElemObj);
				}
				catch (JSONException e) {
					e.printStackTrace();
				}
				size++;
			}
		}*/
		
		List<JSONObject> jsonValues = new ArrayList<JSONObject>();
		for (int i = 0; i < chronicSymptomsFollowup1.length(); i++)
		{
			try
			{
		   jsonValues.add(chronicSymptomsFollowup1.getJSONObject(i));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
			if(orderBy == 1)
			{
				jsonList = sortBy(jsonValues,"status","hitcount");
			}
			else if(orderBy == 2)
			{
				jsonList = sortBy(jsonValues,"status","name");
			}
			else if(orderBy == 3)
			{
				jsonList = sortBy(jsonValues,"status","orderby");
			}
		JSONArray returnJson = new JSONArray();
		try
		{
			if(jsonList.size()>=5)
			{
				for(int j=0;j<5;j++)
				{
					returnJson.put(j, jsonList.get(j));
				}
			}
			else
			{
				for(int j=0;j<jsonList.size();j++)
				{
					returnJson.put(j, jsonList.get(j));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnJson;
	}

	/**
	 * Method to get patient element data for getting dx based symptoms 
	 * basing on patient Id
	 * @return
	 */
	/*private List<Object[]> getpatElemData(Integer patientId, String startsWith,
			List<Object> partStr, Integer encounterId, List<Integer> hpiSymptomIdList, int orderBy) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<PatientClinicalElements> root = cq.from(PatientClinicalElements.class);
		Join<PatientClinicalElements, HpiSymptom> condJoin = root.join(PatientClinicalElements_.hpiSymptom,JoinType.INNER);
		cq.multiselect(condJoin.get(HpiSymptom_.hpiSymptomId),
				condJoin.get(HpiSymptom_.hpiSymptomGwid),
				condJoin.get(HpiSymptom_.hpiSymptomName),
				condJoin.get(HpiSymptom_.hpiSymptomPrinttext),
				condJoin.get(HpiSymptom_.hpiSymptomHitcount),
				condJoin.get(HpiSymptom_.hpiSymptomType),
				builder.literal("2").alias("Status"),
				condJoin.get(HpiSymptom_.hpiSymptomOrderby),
				condJoin.get(HpiSymptom_.hpiSymptomRetaincase));
		List<Predicate> predList = new LinkedList<Predicate>();
		for(int i=0;i<partStr.size();i++){
			Object symptomId = partStr.get(i);
			int prevEncId=Integer.parseInt(getPreviousEncounterId(patientId,symptomId,encounterId));
			if(prevEncId!=-1){
				Predicate p1 = builder.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid), symptomId.toString());
				Predicate p2 = builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), prevEncId);
				Predicate p3 = builder.not(root.get(PatientClinicalElements_.patientClinicalElementsValue).in("0","3"));
				predList.add(builder.and(p1,p2,p3));
			}
		}
		Predicate cond1 = builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
		Predicate cond2 = builder.or(predList.toArray(new Predicate[] {}));
		Predicate cond3;
		if(hpiSymptomIdList.isEmpty())
		{
			cond3 = builder.not(condJoin.get(HpiSymptom_.hpiSymptomId).in(-1));
		}
		else
		{
			cond3 = builder.not(condJoin.get(HpiSymptom_.hpiSymptomId).in(hpiSymptomIdList));
		}
		if(!(startsWith.trim().equals(""))) {
			Predicate cond4 = builder.like(builder.lower(condJoin.get(HpiSymptom_.hpiSymptomName)), getLikePattern(startsWith.replaceAll("'","''")));
			cq.where(cond1,cond2,cond3,cond4);
		}
		else
		{
			cq.where(cond1,cond2,cond3);
		}
		if(orderBy == 1)
			cq.orderBy(builder.asc(builder.literal("2")),builder.asc(condJoin.get(HpiSymptom_.hpiSymptomHitcount)));
		else if(orderBy == 2)
			cq.orderBy(builder.asc(builder.literal("2")),builder.asc(condJoin.get(HpiSymptom_.hpiSymptomName)));
		else if(orderBy == 3)
			cq.orderBy(builder.asc(builder.literal("2")),builder.asc(condJoin.get(HpiSymptom_.hpiSymptomOrderby)));
		
		List<Object[]> obj = em.createQuery(cq).getResultList();
		
		return obj;
	}*/

	/**
	 * Method to get previous encounter id
	 * @return
	 */
	/*private String getPreviousEncounterId(Integer patientId, Object symptomId,
			Integer encounterId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<PatientClinicalElements> root = cq.from(PatientClinicalElements.class);
		Predicate p1 = builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid), patientId);
		Predicate p2 = builder.like(builder.lower(root.get(PatientClinicalElements_.patientClinicalElementsGwid)), getLikePattern(symptomId.toString()));
		Predicate p3 = builder.lessThan(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId);
		cq.select(builder.greatest(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid)));
		cq.where(p1,p2,p3);
		Object prevEncId = em.createQuery(cq).getSingleResult();
		String prevEncounterId = (prevEncId == null?"-1":prevEncId.toString());
		return prevEncounterId;
	}*/

	/**
	 * Method to get symptom gwid basing on symptom type for getting all symptoms
	 * @return
	 */
	private List<Object> getHpiSymptomGwid(Integer patientId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<HpiSymptom> root = cq.from(HpiSymptom.class);
		//Join<HpiSymptom, PatientClinicalElements> patElemJoin = root.join(HpiSymptom_.patientClinicalElements);
		//patElemJoin.on(builder.equal(patElemJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId));
		cq.select(root.get(HpiSymptom_.hpiSymptomGwid));
		cq.where(builder.equal(root.get(HpiSymptom_.hpiSymptomType),2));
		List<Object> obj = em.createQuery(cq).getResultList();
		return obj;
	}

	/**
	 * Method to get dx map id basing on dxcodes for getting dx based symptoms
	 * @return
	 */
	private List<Integer> getHPIDxMap(Integer patientId, Integer encounterId) {
		List<String> dxCode = getDxCodes(patientId,encounterId);
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<HpiDxMap> root = cq.from(HpiDxMap.class);
		cq.select(root.get(HpiDxMap_.hpiDxMapId));
		cq.where(root.get(HpiDxMap_.hpiDxMapDx).in(dxCode.isEmpty()?new String(""):dxCode));
		List<Object> obj = em.createQuery(cq).getResultList();
		List<Integer> hpiDxMap = new ArrayList<Integer>();
		for(int i=0;i<obj.size();i++)
		{
			hpiDxMap.add(Integer.parseInt(obj.get(i).toString()));
		}
		return hpiDxMap;
	}

	/**
	 * Method to get dxcodes basing on patientid
	 * @return
	 */
	private List<String> getDxCodes(Integer patientId, Integer encounterId) {
		List<String> dxCodes = new ArrayList<String>();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<PatientAssessments> root = cq.from(PatientAssessments.class);
		cq.select(builder.trim(root.get(PatientAssessments_.patient_assessments_dxcode)));
		cq.where(builder.and(builder.equal(root.get(PatientAssessments_.patient_assessments_patientId), patientId)),builder.equal(root.get(PatientAssessments_.patient_assessments_id), encounterId));
		List<Object> obj = em.createQuery(cq).getResultList();
		for(int i=0;i<obj.size();i++)
		{
			dxCodes.add(obj.get(i).toString());
		}
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> query = cb.createQuery(Object.class);
		Root<ProblemList> rootProb = query.from(ProblemList.class);
		query.select(cb.trim(rootProb.get(ProblemList_.problemListDxCode)));
		query.where(cb.and(cb.equal(rootProb.get(ProblemList_.problemListIsactive), true)),cb.equal(rootProb.get(ProblemList_.problemListPatientId), patientId));
		List<Object> objProb = em.createQuery(query).getResultList();
		for(int j=0;j<objProb.size();j++)
		{
			dxCodes.add(objProb.get(j).toString());
		}
		return dxCodes;
	}

	private List<HPISymptomBean> getWhereHpiSympGwidNotInHpiSympMappingGwid(
			int ageinDay, Integer templateId, String searchStr,
			Integer patientId, boolean isAgeBased, int mode, Integer encounterId) {
		List<Object> hpiSymptomMappingGwid = getHpiSymptomMappingGwid(searchStr);
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<HpiSymptom> root = cq.from(HpiSymptom.class);
		//Join<HpiSymptom, ClinicalSystem> clinicalJoin = root.join(HpiSymptom_.clinicalSystem,JoinType.INNER);
		//Join<HpiSymptom, PatientClinicalElements> patElemJoin = root.join(HpiSymptom_.patientClinicalElements,JoinType.LEFT);
		Join<HpiSymptom, ClinicalElements> leftJoinClinicalElem;
		Join<ClinicalElements, ClinicalElementsCondition> condJoin = null;
		
		//Predicate joinp1 = builder.equal(patElemJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid), patientId);
		//Predicate joinp2 = builder.lessThan(patElemJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId);
		//patElemJoin.on(builder.and(joinp1,joinp2));
		
		if(mode == 2)
		{
			//Join<HpiSymptom, ClinicalElements> addOnJoin = root.join(HpiSymptom_.clinicalElements,JoinType.LEFT);
		} 
		if(isAgeBased && mode == 2){
				//leftJoinClinicalElem = root.join(HpiSymptom_.clinicalElements,JoinType.LEFT);
				//condJoin = leftJoinClinicalElem.join(ClinicalElements_.clinicalElementsConditions,JoinType.LEFT);
		}
		Expression<Integer> inte = builder.<Integer>selectCase().when(builder.equal(builder.locate(builder.upper(root.get(HpiSymptom_.hpiSymptomName)),builder.literal("")),
				builder.literal(0)),
				-99999).<Integer>otherwise(builder.locate(builder.upper(root.get(HpiSymptom_.hpiSymptomName)),builder.literal("")));
			cq.distinct(true);
			cq.multiselect(root.get(HpiSymptom_.hpiSymptomId),
					root.get(HpiSymptom_.hpiSymptomGwid),
					root.get(HpiSymptom_.hpiSymptomName),
					root.get(HpiSymptom_.hpiSymptomPrinttext),
					root.get(HpiSymptom_.hpiSymptomHitcount),
					root.get(HpiSymptom_.hpiSymptomRetaincase),
					root.get(HpiSymptom_.hpiSymptomOrderby), 
					inte.alias("limitOrder"));
		List<Predicate> predList = new ArrayList<Predicate>();
		Predicate p1 = builder.isTrue(root.get(HpiSymptom_.hpiSymptomIsactive));
		//Predicate p2 = builder.isTrue(clinicalJoin.get(ClinicalSystem_.clinicalSystemIsactive));
		Predicate p3;
		if(hpiSymptomMappingGwid.size() > 0)
		{
			p3 = builder.not(root.get(HpiSymptom_.hpiSymptomGwid).in(hpiSymptomMappingGwid));
		}
		else
		{
			p3 = builder.not(root.get(HpiSymptom_.hpiSymptomGwid).in(""));
		}
		Predicate p7 = /*builder.or(builder.isNull(patElemJoin.get(PatientClinicalElements_.patientClinicalElementsId)),*/builder.equal(root.get(HpiSymptom_.hpiSymptomType), 1);
		
		if(isAgeBased && mode==2)
		{
				Predicate add1 = builder.lessThan(condJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage),ageinDay);
				Predicate add2 = builder.greaterThanOrEqualTo(condJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage), ageinDay);
				if(!(searchStr.trim().equals(""))) {
					Predicate p4 = builder.like(builder.lower(root.get(HpiSymptom_.hpiSymptomName)), getLikePattern(searchStr.replaceAll("'", "''")));
					String startsWithArr[] = searchStr.split(" "); 
					if(startsWithArr.length > 1){
						for(int i=0;i<startsWithArr.length;i++){
							Predicate p5 = builder.like(builder.lower(root.get(HpiSymptom_.hpiSymptomName)), getLikePattern(startsWithArr[i].replaceAll("'", "''")));
							predList.add(p5);
						}
						Predicate predListCond = builder.or(predList.toArray(new Predicate[] {}));
						Predicate combPred = builder.or(p4,predListCond);
						cq.where(p1/*,p2*/,p3,combPred,p7,add1,add2);
					}
					else
					{
						cq.where(p1/*,p2*/,p3,p4,p7,add1,add2);
					}
				}
				else
				{
					cq.where(p1/*,p2*/,p3,p7,add1,add2);
				}
		}
		else
		{
			if(!(searchStr.trim().equals(""))) {
				Predicate p4 = builder.like(builder.lower(root.get(HpiSymptom_.hpiSymptomName)), getLikePattern(searchStr.replaceAll("'", "''")));
				String startsWithArr[] = searchStr.split(" "); 
				if(startsWithArr.length > 1){
					for(int i=0;i<startsWithArr.length;i++){
						Predicate p5 = builder.like(builder.lower(root.get(HpiSymptom_.hpiSymptomName)), getLikePattern(startsWithArr[i].replaceAll("'", "''")));
						predList.add(p5);
					}
					Predicate predListCond = builder.or(predList.toArray(new Predicate[] {}));
					Predicate combPred = builder.or(p4,predListCond);
					cq.where(p1/*,p2*/,p3,combPred,p7);
				}
				else
				{
					cq.where(p1/*,p2*/,p3,p4,p7);
				}
			}
			else
			{
				cq.where(p1/*,p2*/,p3,p7);
			}
		}
		
		List<Object[]> hpiSymptomObjectList = em.createQuery(cq).getResultList();
		List<HPISymptomBean> hpiSymptomBeanList = new ArrayList<HPISymptomBean>();
		if(hpiSymptomObjectList.size()>0)
		{
			for(Object[] values : hpiSymptomObjectList) {
				HPISymptomBean hpiSymptomBean = new HPISymptomBean();
				Integer hpiSymptomId = (Integer) (values[0] == null?"-1":values[0]);
				String hpiSymptomGwid = (values[1] == null?"":values[1]).toString();
				String hpiSymptomName = (values[2] == null?"":values[2]).toString();
				String hpiSymptomPrinttext = (values[3] == null?"":values[3]).toString();
				Integer hpiSymptomHitcount = (Integer.parseInt((values[4] == null?"-1":values[4]).toString()));
				Boolean hpiSymptomRetaincase = (Boolean) (values[5] == null?"false":values[5]);
				Integer hpiSymptomOrderBy = (Integer.parseInt((values[6] == null?"-1":values[6]).toString()));
				String limitOrder = (values[7] == null?"":values[7]).toString();
				hpiSymptomBean.setHpiSymptomId(hpiSymptomId);
				hpiSymptomBean.setHpiSymptomGwid(hpiSymptomGwid);
				hpiSymptomBean.setHpiSymptomPrinttext(hpiSymptomPrinttext);
				hpiSymptomBean.setHpiSymptomHitcount(hpiSymptomHitcount);
				hpiSymptomBean.setHpiSymptomRetaincase(hpiSymptomRetaincase);
				hpiSymptomBean.setHpiSymptomOrderby(hpiSymptomOrderBy);
				hpiSymptomBean.setHpiSymptomName(hpiSymptomName);
				hpiSymptomBean.setLimitOrder(limitOrder);
				hpiSymptomBeanList.add(hpiSymptomBean);
			}
		}
		return hpiSymptomBeanList;
	}

	/**
	 * Method to get symptom mapping gwid's basing on search string
	 * @return
	 */
	private List<Object> getHpiSymptomMappingGwid(String searchStr) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<HpiSymptomMapping> root = cq.from(HpiSymptomMapping.class);
		cq.select(root.get(HpiSymptomMapping_.hpiSymptomMappingGwid));
		Predicate p1 = builder.equal(root.get(HpiSymptomMapping_.hpiSymptomMappingIsactive),true);
		Predicate p2 = builder.equal(root.get(HpiSymptomMapping_.hpiSymptomMappingName), searchStr.split(" ")[0].trim().toUpperCase());
		cq.where(p1,p2);
		List<Object> obj = em.createQuery(cq).getResultList();
		return obj;
	}

	@Override
	public List<HPISymptomBean> getWhereHpiSympGwidNotInPatElemGwid(
		int ageinDay, Integer templateId, String searchStr,
		Integer patientId, boolean isAgeBased, int mode) {
		List<String> patientClinicalElementsGwid = getPatientClinicalElementsGwid(patientId,searchStr,templateId);
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<HpiSymptom> root = cq.from(HpiSymptom.class);
		/*Join<HpiSymptom, ClinicalElementTemplateMapping> clinicalElementTMJoin = root.join(HpiSymptom_.clinicalElementTemplateMapping,JoinType.INNER);
		Predicate p1 = builder.equal(clinicalElementTMJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid),templateId);
		Predicate p2 = builder.equal(clinicalElementTMJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingType), 2);
		Predicate p3 = builder.equal(clinicalElementTMJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingIsactive), true);
		Predicate p4 = builder.isNull(clinicalElementTMJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingDefaultValue));
		clinicalElementTMJoin.on(builder.and(p1,p2,p3,p4));*/
		//Join<HpiSymptom, ClinicalElements> leftJoinClinicalElem;
		//Join<ClinicalElements, ClinicalElementsCondition> condJoin = null;
		if(isAgeBased){
			if(mode==2)
			{
				//  leftJoinClinicalElem = root.join(HpiSymptom_.clinicalElements,JoinType.LEFT);
				//condJoin = leftJoinClinicalElem.join(ClinicalElements_.clinicalElementsConditions,JoinType.LEFT);
			}
		}
		
		if(searchStr.trim().equalsIgnoreCase(""))
		{
		cq.multiselect(root.get(HpiSymptom_.hpiSymptomId),
				root.get(HpiSymptom_.hpiSymptomGwid),
				root.get(HpiSymptom_.hpiSymptomName),
				root.get(HpiSymptom_.hpiSymptomPrinttext),
				root.get(HpiSymptom_.hpiSymptomHitcount),
				root.get(HpiSymptom_.hpiSymptomRetaincase),
				builder.literal("-99999").alias("orderBy")
				//clinicalElementTMJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingOrderby).alias("limitorder")
				);
		}
		else
		{
			cq.multiselect(root.get(HpiSymptom_.hpiSymptomId),
					root.get(HpiSymptom_.hpiSymptomGwid),
					root.get(HpiSymptom_.hpiSymptomName),
					root.get(HpiSymptom_.hpiSymptomPrinttext),
					root.get(HpiSymptom_.hpiSymptomHitcount),
					root.get(HpiSymptom_.hpiSymptomRetaincase),
					//clinicalElementTMJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingOrderby).alias("orderBy"),
					builder.literal("-99999").alias("limitorder"));
		}
		if(isAgeBased)
		{
			if(mode==2)
			{
				//Predicate p5 = builder.lessThan(condJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage),ageinDay);
				//Predicate p6 = builder.greaterThanOrEqualTo(condJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage), ageinDay);
				Predicate p7;
				if(patientClinicalElementsGwid.isEmpty()){
					p7 = builder.and(builder.like(builder.lower(root.get(HpiSymptom_.hpiSymptomName)),getLikePattern(searchStr.split(" ")[0])),
							builder.not(root.get(HpiSymptom_.hpiSymptomGwid).in("")));
				}
				else
				{
					p7 = builder.and(builder.like(builder.lower(root.get(HpiSymptom_.hpiSymptomName)),getLikePattern(searchStr.split(" ")[0])),
							builder.not(root.get(HpiSymptom_.hpiSymptomGwid).in(patientClinicalElementsGwid)));
				}
				cq.where(/*builder.and(p5,p6,*/p7);
			}
			else
			{
				Predicate p7;
				if(patientClinicalElementsGwid.isEmpty()){
					p7 = builder.and(builder.like(builder.lower(root.get(HpiSymptom_.hpiSymptomName)),getLikePattern(searchStr.split(" ")[0])),
							builder.not(root.get(HpiSymptom_.hpiSymptomGwid).in("")));
				}
				else
				{
					p7 = builder.and(builder.like(builder.lower(root.get(HpiSymptom_.hpiSymptomName)),getLikePattern(searchStr.split(" ")[0])),
							builder.not(root.get(HpiSymptom_.hpiSymptomGwid).in(patientClinicalElementsGwid)));
				}
				cq.where(p7);
			}
		}
		else
		{
			if(patientClinicalElementsGwid.isEmpty()){
				cq.where(builder.and(builder.like(builder.lower(root.get(HpiSymptom_.hpiSymptomName)),getLikePattern(searchStr.split(" ")[0])),
						builder.not(root.get(HpiSymptom_.hpiSymptomGwid).in(""))));
			}
			else
			{
				cq.where(builder.and(builder.like(builder.lower(root.get(HpiSymptom_.hpiSymptomName)),getLikePattern(searchStr.split(" ")[0])),
						builder.not(root.get(HpiSymptom_.hpiSymptomGwid).in(patientClinicalElementsGwid))));
			}
		}
		//cq.orderBy(builder.asc(clinicalElementTMJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingOrderby)));
		List<Object[]> hpiSymptomObjectList = em.createQuery(cq).setMaxResults(10).getResultList();
		List<HPISymptomBean> hpiSymptomBeanList = new ArrayList<HPISymptomBean>();
		if(hpiSymptomObjectList.size()>0)
		{
			for(Object[] values : hpiSymptomObjectList) {
				HPISymptomBean hpiSymptomBean = new HPISymptomBean();
				Integer hpiSymptomId = (Integer) (values[0] == null?"-1":values[0]);
				String hpiSymptomGwid = (values[1] == null?"":values[1]).toString();
				String hpiSymptomName = (values[2] == null?"":values[2]).toString();
				String hpiSymptomPrinttext = (values[3] == null?"":values[3]).toString();
				Integer hpiSymptomHitcount = (Integer) (values[4] == null?"-1":values[4]);
				Boolean hpiSymptomRetaincase = (Boolean) (values[5] == null?"false":values[5]);
				Integer hpiSymptomOrderBy = (Integer.parseInt((values[6] == null?"-1":values[6]).toString()));
			//	String limitOrder = (values[7] == null?"":values[7]).toString();
				hpiSymptomBean.setHpiSymptomId(hpiSymptomId);
				hpiSymptomBean.setHpiSymptomGwid(hpiSymptomGwid);
				hpiSymptomBean.setHpiSymptomPrinttext(hpiSymptomPrinttext);
				hpiSymptomBean.setHpiSymptomHitcount(hpiSymptomHitcount);
				hpiSymptomBean.setHpiSymptomRetaincase(hpiSymptomRetaincase);
				hpiSymptomBean.setHpiSymptomOrderby(hpiSymptomOrderBy);
				hpiSymptomBean.setHpiSymptomName(hpiSymptomName);
			//	hpiSymptomBean.setLimitOrder(limitOrder);
				hpiSymptomBeanList.add(hpiSymptomBean);
			}
		}
		return hpiSymptomBeanList;
	}
	

	public List<HPISymptomBean> getWhereHpiSymptomMapping(int ageinDay, String searchStr, boolean isAgeBased, int mode) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> query = cb.createQuery();
		Root<HpiSymptomMapping> rootWhere = query.from(HpiSymptomMapping.class);
		query.select(rootWhere.get(HpiSymptomMapping_.hpiSymptomMappingGwid)).
		where(cb.and(cb.equal(rootWhere.get(HpiSymptomMapping_.hpiSymptomMappingIsactive), true), cb.equal(cb.trim(cb.upper(rootWhere.get(HpiSymptomMapping_.hpiSymptomMappingName))), searchStr.split(" ")[0].toUpperCase().trim())),
				cb.and(cb.equal(rootWhere.get(HpiSymptomMapping_.hpiSymptomMappingIsactive),true)));
		List<Object> symptomGwid = em.createQuery(query).getResultList();
		List<Integer> map = new ArrayList<Integer>();
		for(int i=0;i<symptomGwid.size();i++)
		{
			map.add(Integer.parseInt(symptomGwid.get(i).toString()));
		}
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<HpiSymptom> root = cq.from(HpiSymptom.class);
		//Join<HpiSymptom, ClinicalElements> leftJoinClinicalElem;
		//Join<ClinicalElements, ClinicalElementsCondition> condJoin = null;
		if(isAgeBased){
			if(mode==2)
			{
				//leftJoinClinicalElem = root.join(HpiSymptom_.clinicalElements,JoinType.LEFT);
				//condJoin = leftJoinClinicalElem.join(ClinicalElements_.clinicalElementsConditions,JoinType.LEFT);
			}
		}
		cq.distinct(true);
		cq.multiselect(root.get(HpiSymptom_.hpiSymptomId),
						root.get(HpiSymptom_.hpiSymptomGwid),
						root.get(HpiSymptom_.hpiSymptomName),
						root.get(HpiSymptom_.hpiSymptomPrinttext),
						root.get(HpiSymptom_.hpiSymptomHitcount),
						root.get(HpiSymptom_.hpiSymptomRetaincase),
						root.get(HpiSymptom_.hpiSymptomOrderby),
						builder.literal("-99999").alias("limitOrder"));
		if(isAgeBased)
		{
			if(mode==2)
			{
				//Predicate p1 = builder.lessThan(condJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage),ageinDay);
				//Predicate p2 = builder.greaterThanOrEqualTo(condJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage), ageinDay);
				Predicate p3;
				if(map.isEmpty())
				{
					p3 = root.get(HpiSymptom_.hpiSymptomGwid).in("");
				}
				else
				{
					p3 = root.get(HpiSymptom_.hpiSymptomGwid).in(map);
				}
				cq.where(/*builder.and(p1,p2,*/p3);
			}
			else
			{
				Predicate p4;
				if(map.isEmpty())
				{
					p4 = root.get(HpiSymptom_.hpiSymptomGwid).in("");
				}
				else
				{
					p4 = root.get(HpiSymptom_.hpiSymptomGwid).in(map);
				}
				cq.where(p4);
			}
		}
		else
		{
			if(map.isEmpty())
			{
				cq.where(root.get(HpiSymptom_.hpiSymptomGwid).in(""));
			}
			else
			{
				cq.where(root.get(HpiSymptom_.hpiSymptomGwid).in(map));
			}
		}
		List<Object[]> hpiSymptomList = em.createQuery(cq).getResultList();
		List<HPISymptomBean> hpiSymptom = new ArrayList<HPISymptomBean>();
		if(hpiSymptomList.size()>0)
		{
			for(Object[] values : hpiSymptomList) {
				HPISymptomBean hpiSymptomBean = new HPISymptomBean();
				Integer hpiSymptomId = (Integer) (values[0] == null?"-1":values[0]);			//hpiSymptomId
				String hpiSymptomGwid = (values[1] == null?"":values[1]).toString();			//hpiSymptomGwid
				String hpiSymptomName = (values[2] == null?"":values[2]).toString();			//hpiSymptomName
				String hpiSymptomPrinttext = (values[3] == null?"":values[3]).toString();		//hpiSymptomPrinttext
				Integer hpiSymptomHitcount = (Integer) (values[4] == null?"-1":values[4]);		//hpiSymptomHitcount
				Boolean hpiSymptomRetaincase = (Boolean) (values[5] == null?"false":values[5]);	//hpiSymptomRetaincase
				Integer hpiSymptomOrderBy = (Integer) (values[6] == null?"-1":values[6]);		//hpiSymptomOrderby
				String limitOrder = (values[7] == null?"":values[7]).toString();				//limitOrder
				hpiSymptomBean.setHpiSymptomId(hpiSymptomId);
				hpiSymptomBean.setHpiSymptomGwid(hpiSymptomGwid);
				hpiSymptomBean.setHpiSymptomPrinttext(hpiSymptomPrinttext);
				hpiSymptomBean.setHpiSymptomHitcount(hpiSymptomHitcount);
				hpiSymptomBean.setHpiSymptomRetaincase(hpiSymptomRetaincase);
				hpiSymptomBean.setHpiSymptomOrderby(hpiSymptomOrderBy);
				hpiSymptomBean.setHpiSymptomName(hpiSymptomName);
				hpiSymptomBean.setLimitOrder(limitOrder);
				hpiSymptom.add(hpiSymptomBean);
			}
		}
		
		return hpiSymptom;
	}


	/**
	 * method to get patient clinical elements gwid's for getting all symptoms
	 * @return
	 */
	private List<String> getPatientClinicalElementsGwid(Integer patientId, String searchStr, Integer templateId) {
		List<String> patClinicalElemGwid = new ArrayList<String>();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(Object.class);
		Root<PatientClinicalElements> root = cq.from(PatientClinicalElements.class);
		/*Join<PatientClinicalElements, HpiSymptom> rootJoin = root.join(PatientClinicalElements_.hpiSymptom,JoinType.INNER);
		Predicate p1 = cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
		rootJoin.on(p1);*/
		cq.select(root.get(PatientClinicalElements_.patientClinicalElementsGwid));
		//cq.where(cb.equal(rootJoin.get(HpiSymptom_.hpiSymptomType),2));
		List<Object> obj = em.createQuery(cq).getResultList();
		for(int i=0;i<obj.size();i++)
		{
			patClinicalElemGwid.add(obj.get(i).toString());
		}
		
		if(!searchStr.trim().equalsIgnoreCase(""))
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> query = builder.createQuery(Object.class);
			Root<ClinicalElementTemplateMapping> rootClinicalTM = cq.from(ClinicalElementTemplateMapping.class);
			cq.select(rootClinicalTM.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingGwid));
			Predicate p2 = builder.equal(rootClinicalTM.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingGwid),templateId);
			Predicate p3 = builder.equal(rootClinicalTM.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingType),2);
			Predicate p4 = builder.equal(rootClinicalTM.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingIsactive),true);
			Predicate p5 = builder.isNull(rootClinicalTM.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingDefaultValue));
			query.where(p2,p3,p4,p5);
			List<Object> objClinicalTM = em.createQuery(query).getResultList();
			for(int i=0;i<obj.size();i++)
			{
				patClinicalElemGwid.add(objClinicalTM.get(i).toString());
			}
		}
		return patClinicalElemGwid;
	}
	
	/**
	 * used to get patients negative symptom list
	 * @return
	 */
	/*private List<PatientClinicalElements> loadPatientsHPInegativeSymptomList(
			String clientId, Integer patientId, Integer encounterId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<PatientClinicalElements> root = cq.from(PatientClinicalElements.class);
		Join<PatientClinicalElements,HpiSymptom> hpiSymptom = root.join(PatientClinicalElements_.hpiSymptom,JoinType.INNER);
		Predicate p1 = builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid), patientId);
		Predicate p2 = builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId);
		Predicate p3 = root.get(PatientClinicalElements_.patientClinicalElementsValue).in("0","3");
		hpiSymptom.on(builder.and(p1,p2,p3));
		cq.multiselect(hpiSymptom.get(HpiSymptom_.hpiSymptomId),hpiSymptom.get(HpiSymptom_.hpiSymptomName),hpiSymptom.get(HpiSymptom_.hpiSymptomPrinttext),hpiSymptom.get(HpiSymptom_.hpiSymptomGwid));
		List<Object[]> list= em.createQuery(cq).getResultList();
		List<PatientClinicalElements> patElem = new ArrayList<PatientClinicalElements>();
		if(list.size()>0)
		{
			for(Object[] values : list) {
				PatientClinicalElements patElemBean = new PatientClinicalElements();
				HpiSymptom hpiSymptomBean = new HpiSymptom();
				Integer hpiSymptomId = (Integer) (values[0] == null?"-1":values[0]);
				String hpiSymptomPrinttext = (values[1] == null?"":values[1]).toString();
				String patientClinicalElementsValue = (values[2] == null?"":values[2]).toString();
				String hpiSymptomGWId = (values[3] == null?"":values[3]).toString();
				hpiSymptomBean.setHpiSymptomId(hpiSymptomId);
				hpiSymptomBean.setHpiSymptomPrinttext(hpiSymptomPrinttext);
				patElemBean.setPatientClinicalElementsValue(patientClinicalElementsValue);
				hpiSymptomBean.setHpiSymptomGwid(hpiSymptomGWId);
				patElemBean.setHpiSymptom(hpiSymptomBean);
				patElem.add(patElemBean);
			}
		}
		return patElem;
	}*/

	/**
	 * used to get patient symptom list
	 * @return
	 */
	/*private List<PatientClinicalElements> loadPatientsHPISymptomList(String clientId, Integer patientId,
			Integer encounterId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<PatientClinicalElements> root = cq.from(PatientClinicalElements.class);
		Join<PatientClinicalElements,HpiSymptom> hpiSymptom = root.join(PatientClinicalElements_.hpiSymptom,JoinType.INNER);
		Predicate p1 = builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid), patientId);
		Predicate p2 = builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId);
		Predicate p3 = root.get(PatientClinicalElements_.patientClinicalElementsValue).in("1","2");
		hpiSymptom.on(builder.and(p1,p2,p3));
		cq.multiselect(hpiSymptom.get(HpiSymptom_.hpiSymptomId),hpiSymptom.get(HpiSymptom_.hpiSymptomPrinttext),root.get(PatientClinicalElements_.patientClinicalElementsValue),hpiSymptom.get(HpiSymptom_.hpiSymptomGwid));
		cq.orderBy(builder.asc(root.get(PatientClinicalElements_.patientClinicalElementsId)));
		List<Object[]> list= em.createQuery(cq).getResultList();
		List<PatientClinicalElements> patElem = new ArrayList<PatientClinicalElements>();
		if(list.size()>0)
		{
			for(Object[] values : list) {
				PatientClinicalElements patElemBean = new PatientClinicalElements();
				HpiSymptom hpiSymptomBean = new HpiSymptom();
				Integer hpiSymptomId = (Integer) (values[0] == null?"-1":values[0]);
				String hpiSymptomPrinttext =  (values[1] == null?"":values[1]).toString();
				String patientClinicalElementsValue = (values[2] == null?"":values[2]).toString();
				String hpiSymptomGWId = (values[3] == null?"":values[3]).toString();
				hpiSymptomBean.setHpiSymptomId(hpiSymptomId);
				hpiSymptomBean.setHpiSymptomPrinttext(hpiSymptomPrinttext);
				patElemBean.setPatientClinicalElementsValue(patientClinicalElementsValue);
				hpiSymptomBean.setHpiSymptomGwid(hpiSymptomGWId);
				patElemBean.setHpiSymptom(hpiSymptomBean);
				patElem.add(patElemBean);
			}
		}
		return patElem;
	}*/

	/**
	 * method to get hpisymptomorderby details basing on symptomorderby is active
	 * @return
	 */
	private HpiSymptomOrderby getHpiSymptomOrderBy() {
		HpiSymptomOrderby hpiSymptomOrderby=hpiSymptomOrderbyRepository.findOne(HpiSpecification.OrderBy());
		return hpiSymptomOrderby;
	}


	/**
	 * Method to get Patient Gender and DOB based on patientId
	 * @param patientId
	 * @return
	 */
	private List<Object[]> getPatientReg(Integer patientId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<PatientRegistration> root = cq.from(PatientRegistration.class);
		cq.multiselect(root.get(PatientRegistration_.patientRegistrationSex),root.get(PatientRegistration_.patientRegistrationDob));
		cq.where(builder.equal(root.get(PatientRegistration_.patientRegistrationId), patientId.toString()));
		List<Object[]> list= em.createQuery(cq).getResultList();
		return list;
	}

	/**
	 * Method to get all values from leafLibrary based on templateId
	 * @param templateId
	 * @return
	 */
	private List<LeafLibrary> getIsAgeBased(Integer templateId) {
		List<LeafLibrary> leafDetails=leafLibraryRepository.findAll(LeafLibrarySpecification.getLeafDetailsByTemplateId(templateId));
		return leafDetails;
	}

	/**
	 * Returns the formatted the pattern
	 * @param searchTerm
	 * @return
	 */
	private static String getLikePattern(final String searchTerm) {
		StringBuilder pattern = new StringBuilder();
		pattern.append(searchTerm.toLowerCase());
		pattern.append("%");
		return pattern.toString();
	}
	
	/**
	 * Method to sort by two columns instead of orderby for union
	 * @param jsonValues
	 * @param col1
	 * @param col2
	 * @return
	 */
	public List<JSONObject> sortBy(List<JSONObject> jsonValues, final String col1,
			final String col2) {
		Collections.sort(jsonValues, new Comparator<JSONObject>() {
			@Override
			public int compare(JSONObject arg0, JSONObject arg1) {
				try {
					if (arg0.get(col1).toString().equalsIgnoreCase("") && arg1.get(col1).toString().equalsIgnoreCase("")) {
	                    return 0;
	                }
	                if (arg0.get(col1).toString().equalsIgnoreCase("")) {
	                    return 1;
	                }
	                if (arg1.get(col1).toString().equalsIgnoreCase("")) {
	                    return -1;
	                }
	                if(!((arg0.get(col1).toString().equalsIgnoreCase("") && arg1.get(col1).toString().equalsIgnoreCase("")) || arg0.get(col1).toString().equalsIgnoreCase("") || arg1.get(col1).toString().equalsIgnoreCase("")))
	                {
	                	if(col1.equalsIgnoreCase("name"))
						{
							return arg0.get(col1).toString().compareTo(arg1.get(col1).toString());
						}
						else
						{
							Integer a = Integer.parseInt(arg0.get(col1).toString());
							Integer b = Integer.parseInt(arg1.get(col1).toString());
							return  a.compareTo(b);
						}
	                }
					return arg1.get(col1).toString().compareTo(arg0.get(col1).toString());
				} catch (JSONException e) {
					e.printStackTrace();
					return 0;
				}
			}
		});
		Collections.sort(jsonValues, new Comparator<JSONObject>() {
			@Override
			public int compare(JSONObject arg0, JSONObject arg1) {
				try {
					if(arg0.get(col1).toString().equalsIgnoreCase(arg1.get(col1).toString()))
					{
						if (arg0.get(col2).toString().equalsIgnoreCase("") && arg1.get(col2).toString().equalsIgnoreCase("")) {
		                    return 0;
		                }
		                if (arg0.get(col2).toString().equalsIgnoreCase("")) {
		                    return 1;
		                }
		                if (arg1.get(col2).toString().equalsIgnoreCase("")) {
		                    return -1;
		                }
		                if(!((arg0.get(col2).toString().equalsIgnoreCase("") && arg1.get(col2).toString().equalsIgnoreCase("")) || arg0.get(col2).toString().equalsIgnoreCase("") || arg1.get(col2).toString().equalsIgnoreCase("")))
		                {
							if(col2.equalsIgnoreCase("name"))
							{
								return arg0.get(col2).toString().compareTo(arg1.get(col2).toString());
							}
							else
							{
								Integer a = Integer.parseInt(arg0.get(col2).toString());
								Integer b = Integer.parseInt(arg1.get(col2).toString());
								return  a.compareTo(b);
							}
		                }
		                return arg0.get(col2).toString().compareTo(arg1.get(col2).toString());
					}
					else
					{
						return 0;
					}
				} catch (JSONException e) {
					e.printStackTrace();
					return 0;
				}
			}
		});
		return jsonValues;
	}

}
