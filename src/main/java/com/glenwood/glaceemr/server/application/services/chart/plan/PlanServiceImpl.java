package com.glenwood.glaceemr.server.application.services.chart.plan;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.AftercareInsMapping;
import com.glenwood.glaceemr.server.application.models.ClinicalElementTemplateMapping;
import com.glenwood.glaceemr.server.application.models.ClinicalElementTemplateMapping_;
import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions_;
import com.glenwood.glaceemr.server.application.models.ClinicalElements_;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping_;
import com.glenwood.glaceemr.server.application.models.CnmSettings;
import com.glenwood.glaceemr.server.application.models.CnmSettings_;
import com.glenwood.glaceemr.server.application.models.EncounterPlan;
import com.glenwood.glaceemr.server.application.models.EncounterPlan_;
import com.glenwood.glaceemr.server.application.models.GeneralShortcut;
import com.glenwood.glaceemr.server.application.models.GeneralShortcut_;
import com.glenwood.glaceemr.server.application.models.LeafLibrary;
import com.glenwood.glaceemr.server.application.models.LeafLibrary_;
import com.glenwood.glaceemr.server.application.models.LeafPatient;
import com.glenwood.glaceemr.server.application.models.LeafPatient_;
import com.glenwood.glaceemr.server.application.models.PatientAftercareData;
import com.glenwood.glaceemr.server.application.models.PatientAftercareData_;
import com.glenwood.glaceemr.server.application.models.PatientAssessments;
import com.glenwood.glaceemr.server.application.models.PatientAssessments_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PlanInstruction;
import com.glenwood.glaceemr.server.application.models.PlanInstruction_;
import com.glenwood.glaceemr.server.application.models.PlanMapping;
import com.glenwood.glaceemr.server.application.models.PlanMapping_;
import com.glenwood.glaceemr.server.application.models.PlanType;
import com.glenwood.glaceemr.server.application.models.PlanType_;
import com.glenwood.glaceemr.server.application.models.ReferringDoctor;
import com.glenwood.glaceemr.server.application.models.ReferringDoctor_;
import com.glenwood.glaceemr.server.application.models.SoapElementDatalist;
import com.glenwood.glaceemr.server.application.models.SoapElementDatalist_;
import com.glenwood.glaceemr.server.application.repositories.AftercareInsMappingRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientAftercareDataRepository;
import com.glenwood.glaceemr.server.application.repositories.PlanMappingRepository;
import com.glenwood.glaceemr.server.application.repositories.SoapElementDatalistRepository;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalConstants;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalDataBean;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalElementOptionBean;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalElementsService;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.PatientElementBean;
import com.glenwood.glaceemr.server.application.services.chart.print.TextFormatter;
import com.glenwood.glaceemr.server.application.specifications.PlanSpecification;
import com.glenwood.glaceemr.server.utils.DateUtil;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.google.common.base.Optional;

@Service
public class PlanServiceImpl implements PlanService{

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	ClinicalElementsService clinicalElementsService;
	
	@Autowired
	ClinicalDataBean clinicalDataBean;
	
	@Autowired
	TextFormatter textFormat;
	
	@Autowired
	PlanBean planBean;
	
	@Autowired
	SoapElementDatalistRepository soapElementDatalistRepository;
	
	@Autowired
	PlanMappingRepository planMappingRepository;
	
	@Autowired
	PatientAftercareDataRepository patientAftercareDataRepository;
	
	@Autowired
	AftercareInsMappingRepository aftercareInsMappingRepository; 
	
	Short patientSex=0;
	Date patDOB=null;
	Integer ageinDay=0;
	Integer ageinYears=0;
	
	@Override
	public String getSystems(Integer patientId, Integer chartId,
			Integer encounterId, Integer templateId, String clientId, Integer tabId) {
		
		JSONArray systems= new JSONArray();
		try{
		
		JSONObject system= new JSONObject();
		InstructionData ins= getInstructions(patientId, chartId, encounterId, templateId, clientId, tabId, "");				
		if(ins.getPlaninstructions().size()> 0){
		system.put("planname", "Care Plan");
		system.put("systemid","3");
		systems.put(system);
		system= new JSONObject();
		}
		if(ins.getAnticipatoryguidance().size()> 0){
		system.put("planname", "Anticipatory Guidance");
		system.put("systemid","9");
		systems.put(system);
		system= new JSONObject();
		}
		system.put("planname", "Care Plan Notes");
		system.put("systemid","4");
		systems.put(system);
		system= new JSONObject();
		system.put("planname", "Patient Instructions");
		system.put("systemid","10");
		systems.put(system);
		system= new JSONObject();
		system.put("planname", "Return Visit");
		system.put("systemid","8");
		systems.put(system);
		system= new JSONObject();
		system.put("planname", "Handouts");
		system.put("systemid","6");
		systems.put(system);
		system= new JSONObject();
		system.put("planname", "Referral");
		system.put("systemid","5");
		systems.put(system);
		}catch(Exception e){
			e.printStackTrace();
		}
		return systems.toString();
	}

	/**
	 * Get Plan instructions
	 */
	@Override
	public InstructionData getInstructions(Integer patientId, Integer chartId, Integer encounterId, Integer templateId, String clientId, Integer tabId, String dxcode){
		/*clinicalDataBean.setClientId(clientId);
		clinicalElementsService.setClinicalDataBean(patientId, encounterId, templateId, 7,"04%");*/
		getPatientInfo(patientId);
		Integer leafLibraryId= getLeafLibraryId(templateId);
		Object leafCreatedDate= getLeafCreatedDate(leafLibraryId, encounterId);
		String leafCreated= null;
		if(leafCreatedDate != null)
			leafCreated= leafCreatedDate.toString();
		
		Boolean isAgeBased= getIsAgeBeased(templateId);
		
		clinicalDataBean.getClinicalElements().clear();
		clinicalDataBean.getPatientElements().clear();
		clinicalDataBean.createNewPlanReadInstance(clientId, patientId, chartId, encounterId, 7,templateId, patientSex, patDOB, ageinDay, leafCreated, isAgeBased);
		loadPlanElement(encounterId,patientId,templateId,patientSex,dxcode);
		loadPlanType();
		return frameInstuctionJSON(tabId);
//		return clinicalDataBean;
	}

	private InstructionData frameInstuctionJSON(Integer tabId) {
    	List<String> associateJSON= getAssociateElement();
    	HashMap<String, String> associateMap=new HashMap<String, String>();
    	    	
    	for(int i=0;i<associateJSON.size();i++){    	    
    	    associateMap.put(associateJSON.get(i),i+"");
    	}
    	int elementId=0;
    	LinkedHashMap<Integer, String> temp=planBean.PlanTypes;
    	InstructionHighLevelDetailsData planObject=null;
    	List<InstructionHighLevelDetailsData> finalElementListArray = new ArrayList<InstructionHighLevelDetailsData>();
    	InstructionDetailsData planElementListObject;
    	String[] types = {"19","24","25","26","22","20","23","21","28","57","60","46","48","64","53","56","45","66","58","59","62","29","30","32","67","68","61","63","69","65","31","54","55","36","41","33","35","40","33","35","40","43","47","49","34","37","50","51","38","39","52","42","44"};
    	List<InstructionDetailsData> planElementListArray;

    	for(Map.Entry<Integer, String> entrySet:temp.entrySet()){
    		int typeId=entrySet.getKey();
    		String typeName=temp.get(typeId);
    		planElementListArray = new ArrayList<InstructionDetailsData>();
    		planElementListObject = new InstructionDetailsData();
    		typeName=typeName.replaceAll("&", "&amp;").replaceAll("\"","&quot;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    		List<InstructionDetailsData> elementListAppendArray=new ArrayList<InstructionDetailsData>();
    		LinkedHashMap<String, PlanElementBean> element=planBean.PlanInstructions;
    		int iseven=1;
    		Iterator<Entry<String, PlanElementBean>> i =element.entrySet().iterator();
    		while(i.hasNext()){
    			
    			Entry<String, PlanElementBean>  pb=i.next();
    			String elementGWId=pb.getKey();    			
    			PlanElementBean eBean=element.get(elementGWId);
    			int elementtype=eBean.getElementType();
    			if(elementtype==typeId){
    				String associateKey=HUtil.Nz(associateMap.get(elementGWId),"-1");
    				if(associateKey.equalsIgnoreCase("-1")){
    					try {
							planElementListArray=elementListGWT(elementGWId,element, elementId, patientSex, ageinDay, 0, tabId);

						} catch (Exception e) {
							e.printStackTrace();
						}
    					if(planElementListArray.size()>0){
    						for(int j=0;j<planElementListArray.size();j++){
								planElementListObject = planElementListArray.get(j);
    						}
    						elementListAppendArray.add(planElementListObject);

    					}
    					i.remove();
    				}
    			}
    		}

    		if(elementListAppendArray.size()>0){
    			planObject = new InstructionHighLevelDetailsData();
    			planObject.setDisplayname(typeName);
    			planObject.setTypeid(Integer.toString(typeId));
    			planObject.setPlanelements(elementListAppendArray);
    			finalElementListArray.add(planObject);
    		}
    	}
    	List<InstructionHighLevelDetailsData> anticipatoryGuidanceArray = new ArrayList<InstructionHighLevelDetailsData>();
    	List<InstructionHighLevelDetailsData> planInstructionsArray = new ArrayList<InstructionHighLevelDetailsData>();

    	for(int i=0;i<finalElementListArray.size();i++){
    		InstructionHighLevelDetailsData subElementListforTypes = finalElementListArray.get(i);
    		boolean value = false;
    		for(int k=0;k<types.length;k++){
    			value = subElementListforTypes.getTypeid().equalsIgnoreCase(types[k]);
    			if(value){
    				break;
    			}
    		}
    		if(value){
    			anticipatoryGuidanceArray.add(subElementListforTypes);
    		}else{
    			planInstructionsArray.add(subElementListforTypes);

    		}

    	}
    	InstructionData anticipatoryGuidanceObject = new InstructionData();
    	anticipatoryGuidanceObject.setAnticipatoryguidance(anticipatoryGuidanceArray);
    	anticipatoryGuidanceObject.setPlaninstructions(planInstructionsArray);
    	return anticipatoryGuidanceObject;

	}

	public List<InstructionDetailsData> elementListGWT(String elementGWId,LinkedHashMap  <String,PlanElementBean> element, int elementId,int patientGender,int ageinDay,int fromImport,int tabId) throws Exception{

		int Checktoaddattribute=fromImport;
		
		List<InstructionElementDetailsData> planelement = new ArrayList<InstructionElementDetailsData>();
		List<InstructionAssociateDetailsData> planelementAssociate = new ArrayList<InstructionAssociateDetailsData>();
		List<InstructionDetailsData> elementFinalObjectList = new ArrayList<InstructionDetailsData>();
		InstructionDetailsData elementObject = new InstructionDetailsData();
		elementId++;
		if(element.get(elementGWId)!=null){
			//elementFinalObject = new JSONArray();
			String elementName=element.get(elementGWId).getElementname().replaceAll("&", "&amp;").replaceAll("\"","&quot;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			String elementPrintText=element.get(elementGWId).getElementDescription().replaceAll("&", "&amp;").replaceAll("\"","&quot;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			String elementCode=element.get(elementGWId).getElementCode().replaceAll("&", "&amp;").replaceAll("\"","&quot;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			int elementInstructionId=element.get(elementGWId).getElementId();
			/*  String code="";
    	    if(!elementCode.equalsIgnoreCase("")){
    		code="("+elementCode+")";
    	    }
    	    elementName=elementName+code;*/

			int elementtype=element.get(elementGWId).getElementDataType();
			switch(elementtype){
			
			case (ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT):{

				List<InstructionDetailsData> planElementSub1 = null;
				List<InstructionDetailsData> planElementSub2 = null;
				PatientElementBean patientElementBean=clinicalDataBean.getPatientElements(elementGWId);
				String associatedElement= getAssociatedClinicalText(elementGWId);
				String isdate= getAssociatedClinicalTextIsdate(elementGWId).toString();
				if(!associatedElement.equalsIgnoreCase("") && element.get(associatedElement.split("#")[0]) != null){
					if(element.get(associatedElement.split("#")[0])!=null){
						if(associatedElement.split("#")[1].equalsIgnoreCase("2") || associatedElement.split("#")[1].equalsIgnoreCase("3")|| associatedElement.split("#")[1].equalsIgnoreCase("5")){
							planElementSub1 = elementListGWT(associatedElement.split("#")[0] , element, elementId,patientGender,ageinDay, Checktoaddattribute,tabId);
							if(planElementSub1.size()>0){
								InstructionDetailsData planElementSub1Object = planElementSub1.get(0);
								elementFinalObjectList.add(planElementSub1Object);
							}
						}
					}
				}
				if(elementFinalObjectList.size()>0){
					for(int i=0;i<elementFinalObjectList.size();i++){
						InstructionDetailsData associateObject = elementFinalObjectList.get(i);
						if(patientElementBean!=null){
							if(!elementName.trim().equalsIgnoreCase("")){
								InstructionAssociateDetailsData associateInnerObject = new InstructionAssociateDetailsData();
								if(!associatedElement.equalsIgnoreCase("") && associatedElement.split("#")[1].equalsIgnoreCase("5")){
									associateInnerObject.setPopup(1);
								}else{
									associateInnerObject.setPopup(0);
								}

								associateInnerObject.setIsdate(isdate);
								associateInnerObject.setElementgwid(elementGWId);
								associateInnerObject.setPrinttext(elementPrintText);
								associateInnerObject.setAssociateelement(associatedElement);
								associateInnerObject.setElementname(elementName);
								associateInnerObject.setClinicalelementtype(Integer.toString(ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT));
								associateInnerObject.setValue(patientElementBean.getPatientClinicalElementText());
								associateInnerObject.setShortcuts(getElementDataList(tabId, "xml_hpi_element_1_" + elementGWId + "_text"));
								planelementAssociate.add(associateInnerObject);
								associateObject.setAssociate(planelementAssociate);
								elementObject.setElementdetail(planelement);
							}else{
								InstructionAssociateDetailsData associateInnerObject = new InstructionAssociateDetailsData();
								if(!associatedElement.equalsIgnoreCase("") && associatedElement.split("#")[1].equalsIgnoreCase("5")){
									associateInnerObject.setPopup(1);
								}else{
									associateInnerObject.setPopup(0);
								}
								associateInnerObject.setIsdate(isdate);
								associateInnerObject.setElementgwid(elementGWId);
								associateInnerObject.setPrinttext(elementPrintText);
								associateInnerObject.setAssociateelement(associatedElement);
								associateInnerObject.setAssociateelement(elementName);
								associateInnerObject.setClinicalelementtype(Integer.toString(ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT));
								associateInnerObject.setValue(patientElementBean.getPatientClinicalElementText());
								associateInnerObject.setShortcuts(getElementDataList(tabId, "xml_hpi_element_1_" + elementGWId + "_text"));
								planelementAssociate.add(associateInnerObject);
								associateObject.setAssociate(planelementAssociate);
								elementObject.setElementdetail(planelement);
							}
						}else if(!associatedElement.equalsIgnoreCase("") && element.get(associatedElement.split("#")[0]) != null){
							if(!elementName.trim().equalsIgnoreCase("")){
								InstructionAssociateDetailsData associateInnerObject = new InstructionAssociateDetailsData();
								if(!associatedElement.equalsIgnoreCase("") && associatedElement.split("#")[1].equalsIgnoreCase("5")){
									associateInnerObject.setPopup(1);
								}else{
									associateInnerObject.setPopup(0);
								}
								associateInnerObject.setIsdate(isdate);
								associateInnerObject.setElementgwid(elementGWId);
								associateInnerObject.setPrinttext(elementPrintText);
								associateInnerObject.setAssociateelement(associatedElement);
								associateInnerObject.setElementname(elementName);
								associateInnerObject.setClinicalelementtype(Integer.toString(ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT));
								associateInnerObject.setValue("");
								associateInnerObject.setShortcuts(getElementDataList(tabId, "xml_hpi_element_1_" + elementGWId + "_text"));
								planelementAssociate.add(associateInnerObject);
								associateObject.setAssociate(planelementAssociate);
								elementObject.setElementdetail(planelement);
							}else{
								InstructionAssociateDetailsData associateInnerObject = new InstructionAssociateDetailsData();
								if(!associatedElement.equalsIgnoreCase("") && associatedElement.split("#")[1].equalsIgnoreCase("5")){
									associateInnerObject.setPopup(1);
								}else{
									associateInnerObject.setPopup(0);
								}
								associateInnerObject.setIsdate(isdate);
								associateInnerObject.setElementgwid(elementGWId);
								associateInnerObject.setPrinttext(elementPrintText);
								associateInnerObject.setAssociateelement(associatedElement);
								associateInnerObject.setElementname(elementName);
								associateInnerObject.setClinicalelementtype(Integer.toString(ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT));
								associateInnerObject.setValue("");
								associateInnerObject.setShortcuts(getElementDataList(tabId, "xml_hpi_element_1_" + elementGWId + "_text"));
								planelementAssociate.add(associateInnerObject);
								associateObject.setAssociate(planelementAssociate);
								elementObject.setElementdetail(planelement);
							}
						}
						if(!associatedElement.equalsIgnoreCase("")){
							if(associatedElement.split("#")[1].equalsIgnoreCase("1")){
								planElementSub2 = elementListGWT(associatedElement.split("#")[0] , element, elementId,patientGender,ageinDay, Checktoaddattribute,tabId);
								InstructionDetailsData planElementSub2Object = planElementSub2.get(0);
								elementFinalObjectList.add(planElementSub2Object);
							}
						}
					}
				}
				elementId++;
				break;
			}
			
			case (ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION):{
				elementId++;
				try{
					PatientElementBean patientElementBean=clinicalDataBean.getPatientElements(elementGWId);
					List<ClinicalElementOptionBean> option= getElementOption(elementGWId,patientGender,ageinDay);
					int j=0;
					for(;j<option.size();j++){
						ClinicalElementOptionBean optionRecord = option.get(j);
						String optionName=HUtil.Nz(optionRecord.getClinicalelementoptionName(),"").replaceAll("&", "&amp;").replaceAll("\"","&quot;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
						String optionValue=HUtil.Nz(optionRecord.getClinicalelementoptionValue(),"");
						boolean retain= optionRecord.getClinicalelementoptionRetainCase();
						if(patientElementBean!=null){
							String elementValue=HUtil.Nz(patientElementBean.getPatientClinicalElementOption(),"");							
							if(!optionValue.trim().isEmpty() && !elementValue.trim().isEmpty() && optionValue.equalsIgnoreCase(elementValue)){
								InstructionElementDetailsData elementsJson = new InstructionElementDetailsData();
								elementsJson.setElementgwid(elementGWId);
								elementsJson.setElementprinttext(elementPrintText);
								elementsJson.setOptionname(optionName);
								elementsJson.setOptionvalue(optionValue);
								elementsJson.setClinicalelementtype(ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION);
								elementsJson.setRetaincase(Boolean.toString(retain));
								elementsJson.setValue("1");
								
								planelement.add(elementsJson);
								elementObject.setElementdetail(planelement);
							}else{
								InstructionElementDetailsData elementsJson = new InstructionElementDetailsData();
								elementsJson.setElementgwid(elementGWId);
								elementsJson.setElementprinttext(elementPrintText);
								elementsJson.setOptionname(optionName);
								elementsJson.setOptionvalue(optionValue);
								elementsJson.setClinicalelementtype(ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION);
								elementsJson.setRetaincase(retain+"");
								elementsJson.setValue("");
								planelement.add(elementsJson);
								elementObject.setElementdetail(planelement);
							}
							elementId++;
						}else{
							InstructionElementDetailsData elementsJson = new InstructionElementDetailsData();
							elementsJson.setElementgwid(elementGWId);
							elementsJson.setElementprinttext(elementPrintText);
							elementsJson.setOptionname(optionName);
							elementsJson.setOptionvalue(optionValue);
							elementsJson.setClinicalelementtype(ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION);
							elementsJson.setRetaincase(retain+"");
							elementsJson.setValue("");
							planelement.add(elementsJson);
							elementObject.setElementdetail(planelement);
							elementId++;
						}
						elementObject.setElementinstructionid(elementInstructionId);
						elementObject.setElementname(elementName);
						elementFinalObjectList.add(elementObject);
					}
					elementId++;
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			}
			}
		}
		return elementFinalObjectList;
	}


	private List<ClinicalElementOptionBean> getElementOption(String elementGWId, int patientGender,
			int ageinday) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<ClinicalElementOptionBean> query= builder.createQuery(ClinicalElementOptionBean.class);
		Root<ClinicalElementsOptions> root= query.from(ClinicalElementsOptions.class);

		Predicate agePred= null;
		if(ageinday != -1){
			agePred= builder.or(builder.and(builder.equal(root.get(ClinicalElementsOptions_.clinicalElementsOptionsStartage),-1), 
											   builder.equal(root.get(ClinicalElementsOptions_.clinicalElementsOptionsEndage),-1)),
								  builder.and(builder.lessThan(root.get(ClinicalElementsOptions_.clinicalElementsOptionsStartage), ageinday),
										  	   builder.greaterThanOrEqualTo(root.get(ClinicalElementsOptions_.clinicalElementsOptionsEndage), ageinday)));
		}
		
		query.select(builder.construct(ClinicalElementOptionBean.class,
					  root.get(ClinicalElementsOptions_.clinicalElementsOptionsValue),
					   root.get(ClinicalElementsOptions_.clinicalElementsOptionsName),
					    builder.coalesce(root.get(ClinicalElementsOptions_.clinicalElementsOptionsRetaincase),false)));
		if(ageinday != -1){
			query.where(builder.like(root.get(ClinicalElementsOptions_.clinicalElementsOptionsGwid), elementGWId),
						 builder.equal(root.get(ClinicalElementsOptions_.clinicalElementsOptionsActive), true),
						  root.get(ClinicalElementsOptions_.clinicalElementsOptionsGender).in(0, patientGender),
						   agePred);
		}else{
			query.where(builder.like(root.get(ClinicalElementsOptions_.clinicalElementsOptionsGwid), elementGWId),
						 builder.equal(root.get(ClinicalElementsOptions_.clinicalElementsOptionsActive), true),
						  root.get(ClinicalElementsOptions_.clinicalElementsOptionsGender).in(0, patientGender));
		}
		
		query.orderBy(builder.asc(root.get(ClinicalElementsOptions_.clinicalElementsOptionsOrderBy)),
				builder.asc(root.get(ClinicalElementsOptions_.clinicalElementsOptionsValue)));
		
		return em.createQuery(query).getResultList();
	}

	private List<QuickNotesData> getElementDataList(int tabId, String elementId) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<QuickNotesData> query= builder.createQuery(QuickNotesData.class);
		Root<SoapElementDatalist> root= query.from(SoapElementDatalist.class);
		
		query.select(builder.construct(QuickNotesData.class, 
						root.get(SoapElementDatalist_.soapElementDatalistId),
						 root.get(SoapElementDatalist_.soapElementDatalistData)));
		
		query.where(builder.equal(root.get(SoapElementDatalist_.soapElementDatalistTabid), tabId),
					 builder.like(root.get(SoapElementDatalist_.soapElementDatalistElementid), elementId));
		
		query.orderBy(builder.asc(root.get(SoapElementDatalist_.soapElementDatalistData)));
		
		return em.createQuery(query).getResultList();
	}

	private Boolean getAssociatedClinicalTextIsdate(String elementGWId) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Boolean> query= builder.createQuery(Boolean.class);
		Root<ClinicalTextMapping> root= query.from(ClinicalTextMapping.class);
		
		query.multiselect(builder.coalesce(root.get(ClinicalTextMapping_.clinicalTextMappingIsdate),false));
		query.where(builder.like(root.get(ClinicalTextMapping_.clinicalTextMappingTextboxGwid), elementGWId));
		
		return em.createQuery(query).getSingleResult();
	}

	private String getAssociatedClinicalText(String elementGWId) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<String> query= builder.createQuery(String.class);
		Root<ClinicalTextMapping> root= query.from(ClinicalTextMapping.class);
		
		query.select(builder.coalesce(root.get(ClinicalTextMapping_.clinicalTextMappingAssociatedElement),""));
		query.where(builder.like(root.get(ClinicalTextMapping_.clinicalTextMappingTextboxGwid), elementGWId));
		
		return em.createQuery(query).getSingleResult();
	}

	private List<String> getAssociateElement() {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<String> query= builder.createQuery(String.class);
		Root<ClinicalTextMapping> root= query.from(ClinicalTextMapping.class);
		Join<ClinicalTextMapping, PlanInstruction> insJoin= root.join(ClinicalTextMapping_.planInstructionAssociate, JoinType.INNER);
		
		query.select(builder.substring(root.get(ClinicalTextMapping_.clinicalTextMappingAssociatedElement), 1, 19));
		query.where(builder.equal(insJoin.get(PlanInstruction_.planInstructionIsactive),true));
		
		return em.createQuery(query).getResultList();
	}

	private void loadPlanType() {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<PlanType> root= query.from(PlanType.class); 

		query.multiselect(builder.coalesce(root.get(PlanType_.planTypeId),-1), 
						   builder.coalesce(root.get(PlanType_.planTypeName),""));						  
		query.where(builder.not(root.get(PlanType_.planTypeId).in(3,6)));
		query.orderBy(builder.asc(root.get(PlanType_.planTypeOrderby)));
		
		List<Object[]> list= em.createQuery(query).getResultList();

		if(list!= null){
			for(int i=0; i<list.size(); i++){
				Object[] typeRecord= list.get(i);
				planBean.PlanTypes.put(Integer.parseInt(typeRecord[0].toString()), typeRecord[1].toString());
			}
		}
	}

	private void loadPlanElement(Integer encounterId, Integer patientId,
			Integer templateId, Short patientSex, String dxcode) {
		
		Integer leafLibraryId= getLeafLibraryId(templateId);
		Object leafCreatedDate= getLeafCreatedDate(leafLibraryId, encounterId);
		String leafCreated= "";
		if(leafCreatedDate != null)
			leafCreated= leafCreatedDate.toString();
		Boolean isAgeBased= getIsAgeBeased(templateId);
		
		getGeneralElementQuery(encounterId,templateId,patientSex,ageinDay,isAgeBased,leafCreated,dxcode);
		
	}

	private void getGeneralElementQuery(Integer encounterId,
			Integer templateId, Short patientSex, Integer ageinDay2,
			Boolean isAgeBased, String leafCreated, String dxcode) {
		
		Set<PlanElementBean> dup= new HashSet<PlanElementBean>();
		dup.clear();
		List<PlanElementBean> result= null;
		
		if(dxcode.trim().isEmpty()){
			result= getFirstSubResult1(encounterId, leafCreated, templateId);
			dup.addAll(result);
			result.clear();
			result= getFirstSubResult2(encounterId, leafCreated, templateId, isAgeBased);
			dup.addAll(result);
			result.clear();
			result= getSecondResult(encounterId);
			dup.addAll(result);
			result.clear();
			result= getThirdResult(encounterId);
			dup.addAll(result);
			result.clear();
		}else{
			result= getDxInstructions(encounterId, leafCreated, templateId, dxcode);
			dup.addAll(result);
			
			List<String> gwids= new ArrayList<String>();
			for(PlanElementBean ins: result){
				gwids.add(ins.getElementGWId());
			}
			result.clear();
			
			result= getDxInstructions2(encounterId, leafCreated, templateId, dxcode, gwids);
			dup.addAll(result);
			result.clear();
		}
		result.addAll(dup);

		Collections.sort(result, new Comparator<PlanElementBean>() {
			@Override
			public int compare(PlanElementBean o1, PlanElementBean o2) {
				 return o1.getElementname().compareTo(o2.getElementname());
			}

	    });
		for(PlanElementBean bean: result){
			planBean.PlanInstructions.put(bean.getElementGWId(), bean);
		}
		
	}

	private  List<PlanElementBean> getDxInstructions2(Integer encounterId,
			String leafCreated, Integer templateId, String dxcode, List<String> gwids) {

		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<PlanElementBean> query= builder.createQuery(PlanElementBean.class);
		Root<ClinicalTextMapping> root= query.from(ClinicalTextMapping.class);
//		Join<ClinicalTextMapping, PatientClinicalElements> textJoin= root.join(ClinicalTextMapping_.patientClinicalElements, JoinType.LEFT);
		Join<ClinicalTextMapping, PlanInstruction> insJoin= root.join(ClinicalTextMapping_.planInstruction, JoinType.INNER);
		Join<PlanInstruction, PlanMapping> mapJoin= insJoin.join(PlanInstruction_.planMappings, JoinType.LEFT);
		Join<PlanInstruction, ClinicalElements> elementJoin= insJoin.join(PlanInstruction_.clinicalElements, JoinType.LEFT);		
//		textJoin.on(builder.equal(textJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId));
		mapJoin.on(builder.equal(mapJoin.get(PlanMapping_.planMappingCode), dxcode));
		query.select(builder.construct(PlanElementBean.class,
					  builder.coalesce(root.get(ClinicalTextMapping_.clinicalTextMappingTextboxGwid),"-1"),
					   builder.coalesce(insJoin.get(PlanInstruction_.planInstructionName),""),
						builder.coalesce(insJoin.get(PlanInstruction_.planInstructionDescription),""),
						 builder.coalesce(insJoin.get(PlanInstruction_.planInstructionPlantypeid),-1),
						  builder.coalesce(mapJoin.get(PlanMapping_.planMappingCode),""),
						   builder.coalesce(elementJoin.get(ClinicalElements_.clinicalElementsDatatype),-1),
						    builder.coalesce(insJoin.get(PlanInstruction_.planInstructionId),-1)));
		
		query.where(builder.substring(root.get(ClinicalTextMapping_.clinicalTextMappingAssociatedElement),0,20).in(gwids));
		
		return em.createQuery(query).getResultList();
	
	}
	private List<PlanElementBean> getDxInstructions(Integer encounterId,
			String leafCreated, Integer templateId, String dxcode) {
		
		dxcode= dxcode.trim().toUpperCase();
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<PlanElementBean> query= builder.createQuery(PlanElementBean.class);
		Root<PlanInstruction> root= query.from(PlanInstruction.class);
		Join<PlanInstruction, ClinicalElements> elementJoin= root.join(PlanInstruction_.clinicalElements, JoinType.INNER);
		
		query.distinct(true).select(builder.construct(PlanElementBean.class,
					  builder.coalesce(root.get(PlanInstruction_.planInstructionGwid),"-1"),
					   builder.coalesce(root.get(PlanInstruction_.planInstructionName),""),
					    builder.coalesce(root.get(PlanInstruction_.planInstructionDescription),""),
					     builder.coalesce(root.get(PlanInstruction_.planInstructionPlantypeid),-1),
					      builder.literal(dxcode),
					       builder.coalesce(elementJoin.get(ClinicalElements_.clinicalElementsDatatype),-1),
					        builder.coalesce(root.get(PlanInstruction_.planInstructionId),-1)));
		
		
		
		Subquery<Integer> subquery = query.subquery(Integer.class);
		Root<PlanInstruction> subqueryFrom = subquery.from(PlanInstruction.class);
		Join<PlanInstruction, PlanMapping> mapJoin= subqueryFrom.join(PlanInstruction_.planMappings, JoinType.INNER);
		mapJoin.on(builder.equal(builder.trim(builder.upper(mapJoin.get(PlanMapping_.planMappingCode))), dxcode));
		subquery.select(subqueryFrom.get(PlanInstruction_.planInstructionId));
		Predicate subqueryPred = builder.in(root.get(PlanInstruction_.planInstructionId)).value(subquery);
		
		Subquery<String> tempMapQry= query.subquery(String.class);
		Root<ClinicalElementTemplateMapping> subqryFrom = tempMapQry.from(ClinicalElementTemplateMapping.class);
		tempMapQry.select(subqryFrom.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingGwid));		
		Predicate timestampPred= builder.or(
									builder.lessThanOrEqualTo(subqryFrom.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTimestamp), Timestamp.valueOf(leafCreated)),
									builder.isNull(subqryFrom.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTimestamp)));
		if(leafCreated != null)
			tempMapQry.where(subqryFrom.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid).in(templateId)/*, timestampPred*/);
		else
			tempMapQry.where(subqryFrom.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid).in(templateId));
		Predicate subqryPred = builder.in(root.get(PlanInstruction_.planInstructionGwid)).value(tempMapQry);
		
		query.where(builder.equal(root.get(PlanInstruction_.planInstructionIsactive), true),
					 elementJoin.get(ClinicalElements_.clinicalElementsGender).in(0, patientSex),
					  subqueryPred, subqryPred);

		
		return em.createQuery(query).getResultList();
	}

	private List<PlanElementBean> getThirdResult(Integer encounterId) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<PlanElementBean> query= builder.createQuery(PlanElementBean.class);
		Root<ClinicalTextMapping> root= query.from(ClinicalTextMapping.class);
//		Join<ClinicalTextMapping, PatientClinicalElements> textJoin= root.join(ClinicalTextMapping_.patientClinicalElements, JoinType.INNER);
		Join<ClinicalTextMapping, PlanInstruction> insJoin= root.join(ClinicalTextMapping_.planInstruction, JoinType.LEFT);
		Join<PlanInstruction, PlanMapping> mapJoin= insJoin.join(PlanInstruction_.planMappings, JoinType.LEFT);
		Join<PlanInstruction, ClinicalElements> elementJoin= insJoin.join(PlanInstruction_.clinicalElements, JoinType.LEFT);		
//		textJoin.on(builder.equal(textJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId));
			
		query.select(builder.construct(PlanElementBean.class,
					  builder.coalesce(root.get(ClinicalTextMapping_.clinicalTextMappingTextboxGwid),"-1"),
					   builder.coalesce(insJoin.get(PlanInstruction_.planInstructionName),""),
						builder.coalesce(insJoin.get(PlanInstruction_.planInstructionDescription),""),
						 builder.coalesce(insJoin.get(PlanInstruction_.planInstructionPlantypeid),-1),
						  builder.coalesce(mapJoin.get(PlanMapping_.planMappingCode),""),
						   builder.coalesce(elementJoin.get(ClinicalElements_.clinicalElementsDatatype),-1),
						    builder.coalesce(insJoin.get(PlanInstruction_.planInstructionId),-1)));
		
//		query.where(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId));
		
		return em.createQuery(query).getResultList();
	}

	private List<PlanElementBean> getSecondResult(Integer encounterId) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<PlanElementBean> query= builder.createQuery(PlanElementBean.class);
		Root<PatientClinicalElements> root= query.from(PatientClinicalElements.class);
		Join<PatientClinicalElements, PlanInstruction> insJoin= root.join(PatientClinicalElements_.planInstruction, JoinType.INNER);
		Join<PlanInstruction, PlanMapping> mapJoin= insJoin.join(PlanInstruction_.planMappings, JoinType.LEFT);
		Join<PlanInstruction, ClinicalElements> elementJoin= insJoin.join(PlanInstruction_.clinicalElements, JoinType.INNER);
		
		Predicate[] pred= new Predicate[]{
				builder.not(insJoin.get(PlanInstruction_.planInstructionPlantypeid).in(2,3,6))
		};
		elementJoin.on(pred);
			
		query.select(builder.construct(PlanElementBean.class,
					  builder.coalesce(insJoin.get(PlanInstruction_.planInstructionGwid),"-1"),
					   builder.coalesce(insJoin.get(PlanInstruction_.planInstructionName),""),
						builder.coalesce(insJoin.get(PlanInstruction_.planInstructionDescription),""),
						 builder.coalesce(insJoin.get(PlanInstruction_.planInstructionPlantypeid),-1),
						  builder.coalesce(mapJoin.get(PlanMapping_.planMappingCode),""),
						   builder.coalesce(elementJoin.get(ClinicalElements_.clinicalElementsDatatype),-1),
						    builder.coalesce(insJoin.get(PlanInstruction_.planInstructionId),-1)));
		
		query.where(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId));
		
		return em.createQuery(query).getResultList();
	}

	private List<PlanElementBean> getFirstSubResult2(Integer encounterId, String leafCreated, Integer templateId, Boolean isAgeBased) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<PlanElementBean> query= builder.createQuery(PlanElementBean.class);
		Root<PlanInstruction> root= query.from(PlanInstruction.class);
		Join<PlanInstruction, ClinicalElements> elementJoin= root.join(PlanInstruction_.clinicalElements, JoinType.INNER);
		
		query.distinct(true).select(builder.construct(PlanElementBean.class,
					  builder.coalesce(root.get(PlanInstruction_.planInstructionGwid),"-1"),
					   builder.coalesce(root.get(PlanInstruction_.planInstructionName),""),
					    builder.coalesce(root.get(PlanInstruction_.planInstructionDescription),""),
					     builder.coalesce(root.get(PlanInstruction_.planInstructionPlantypeid),-1),
					      builder.literal(""),
					       builder.coalesce(elementJoin.get(ClinicalElements_.clinicalElementsDatatype),-1),
					        builder.coalesce(root.get(PlanInstruction_.planInstructionId),-1)));
		
		
		
		Subquery<Integer> subquery = query.subquery(Integer.class);
		Root<PlanInstruction> subqueryFrom = subquery.from(PlanInstruction.class);
		subqueryFrom.join(PlanInstruction_.planMappings, JoinType.INNER);
		subquery.select(subqueryFrom.get(PlanInstruction_.planInstructionId));
		Predicate subqueryPred = builder.not(builder.in(root.get(PlanInstruction_.planInstructionId)).value(subquery));
		
		Subquery<String> tempMapQry= query.subquery(String.class);
		Root<ClinicalElementTemplateMapping> subqryFrom = tempMapQry.from(ClinicalElementTemplateMapping.class);
		tempMapQry.select(subqryFrom.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingGwid));		
		Predicate timestampPred= builder.or(
									builder.lessThanOrEqualTo(subqryFrom.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTimestamp), Timestamp.valueOf(leafCreated)),
									builder.isNull(subqryFrom.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTimestamp)));
		if(leafCreated != null)
			tempMapQry.where(subqryFrom.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid).in(templateId)/*, timestampPred*/);
		else
			tempMapQry.where(subqryFrom.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid).in(templateId));
		Predicate subqryPred = builder.in(root.get(PlanInstruction_.planInstructionGwid)).value(tempMapQry);
		
		query.where(builder.equal(root.get(PlanInstruction_.planInstructionIsactive), true),
					 elementJoin.get(ClinicalElements_.clinicalElementsGender).in(0, patientSex),
					  subqueryPred, subqryPred);

		
		return em.createQuery(query).getResultList();
	}
	
	private List<PlanElementBean> getFirstSubResult1(Integer encounterId, String leafCreated, Integer templateId) {
		List<String> dxlist= getMappingDx(encounterId);
//		List<String> tempMapGwidlist= getTemplateMappingGwid(templateId);
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<PlanElementBean> query= builder.createQuery(PlanElementBean.class);
		Root<PlanInstruction> root= query.from(PlanInstruction.class);
		Join<PlanInstruction, PlanMapping> mapJoin= root.join(PlanInstruction_.planMappings, JoinType.INNER);
		Join<PlanInstruction, ClinicalElements> elementJoin= root.join(PlanInstruction_.clinicalElements, JoinType.INNER);
		Join<ClinicalElements, ClinicalElementTemplateMapping> templateJoin= elementJoin.join(ClinicalElements_.clinicalElementTemplateMapping, JoinType.INNER);
					
		query.select(builder.construct(PlanElementBean.class,
					  builder.coalesce(root.get(PlanInstruction_.planInstructionGwid),"-1"),
					   builder.coalesce(root.get(PlanInstruction_.planInstructionName),""),
					    builder.coalesce(root.get(PlanInstruction_.planInstructionDescription),""),
					     builder.coalesce(root.get(PlanInstruction_.planInstructionPlantypeid),-1),
					      builder.coalesce(mapJoin.get(PlanMapping_.planMappingCode),""),
					       builder.coalesce(elementJoin.get(ClinicalElements_.clinicalElementsDatatype),-1),
					        builder.coalesce(root.get(PlanInstruction_.planInstructionId),-1)));
			
		Subquery<String> subQuery= query.subquery(String.class);
		Root<PlanInstruction> subQueryRoot= subQuery.from(PlanInstruction.class);
		Join<PlanInstruction, ClinicalElementTemplateMapping> tempJoin= subQueryRoot.join(PlanInstruction_.clinicalElementTemplateMapping, JoinType.INNER);
		
		subQuery.distinct(true)
			  .select(builder.trim(tempJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingGwid)))
			   .where(builder.equal(tempJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid), templateId));
		
//		Predicate leafPred= builder.or(builder.lessThanOrEqualTo(templateJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTimestamp), Timestamp.valueOf(leafCreated)));
		Predicate typePred= builder.equal(mapJoin.get(PlanMapping_.planMappingType),1);
		Predicate activePred= builder.equal(root.get(PlanInstruction_.planInstructionIsactive), true);
		Predicate dxPred= mapJoin.get(PlanMapping_.planMappingCode).in(dxlist);
		Predicate gwidPred= builder.in(root.get(PlanInstruction_.planInstructionGwid)).value(subQuery);
		Predicate templatePred= builder.equal(templateJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid), templateId);
		Predicate genderPred= elementJoin.get(ClinicalElements_.clinicalElementsGender).in(0, patientSex);
		Predicate finalPred= builder.and(typePred, activePred, templatePred, genderPred, gwidPred);
		if(dxlist.size()>0)
			finalPred= builder.and(finalPred, dxPred);
		/*if(tempMapGwidlist.size()>0)
			finalPred= builder.and(finalPred, gwidPred);*/
	/*	if(leafCreated != null);
		finalPred= builder.and(finalPred, leafPred);*/

		query.where(finalPred);
		
		return em.createQuery(query).getResultList();
	}

	private List<String> getTemplateMappingGwid(Integer templateId) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<String> query= builder.createQuery(String.class);
		Root<PlanInstruction> root= query.from(PlanInstruction.class);
		Join<PlanInstruction, ClinicalElementTemplateMapping> templateJoin= root.join(PlanInstruction_.clinicalElementTemplateMapping, JoinType.INNER);
		
		query.distinct(true)
			  .select(builder.trim(templateJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingGwid)))
			   .where(builder.equal(templateJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid), templateId));
		
		List<String> result= em.createQuery(query).getResultList();
		
		return result;
	}

	private List<String> getMappingDx(Integer encounterId) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<String> query= builder.createQuery(String.class);
		Root<PatientAssessments> root= query.from(PatientAssessments.class);
		
		query.select(builder.trim(root.get(PatientAssessments_.patient_assessments_dxcode)))
			 .where(builder.equal(root.get(PatientAssessments_.patient_assessments_id), encounterId));
		
		List<String> result= em.createQuery(query).getResultList();
		Set<String> set = new HashSet<String>();
		set.addAll(result);
		
		result.clear();
		for(String value: set){
			value= value.split("\\.")[0]+"*";
			try{
				result.add(value);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		set.addAll(result);
		result.clear();
		result.addAll(set);
		return result;
	}

	/**
	 * Get whether template is age based	
	 * @param templateId
	 * @return
	 */
	private Boolean getIsAgeBeased(Integer templateId) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Boolean> query= builder.createQuery(Boolean.class);
		Root<LeafLibrary> root= query.from(LeafLibrary.class); 

		query.multiselect(builder.coalesce(root.get(LeafLibrary_.leafLibraryIsagebased), false))	 
			 .where(builder.equal(root.get(LeafLibrary_.leafLibrarySoaptemplateId), templateId));
		
		Boolean list= em.createQuery(query).getSingleResult();

		return list;
	
	}

	/**
	 * Get leaf created date for patient template
	 * @param leafLibraryId
	 * @param encounterId
	 * @return
	 */
	private Object getLeafCreatedDate(Integer leafLibraryId, Integer encounterId) {

		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object> query= builder.createQuery();
		Root<LeafPatient> root= query.from(LeafPatient.class); 

		query.select(builder.function("to_char", String.class, root.get(LeafPatient_.leafPatientCreatedDate), builder.literal("yyyy-MM-dd HH:mm:ss")))	 
			 .where(builder.equal(root.get(LeafPatient_.leafPatientLeafLibraryId), leafLibraryId),
					 builder.equal(root.get(LeafPatient_.leafPatientEncounterId), encounterId) );
		
		Object result=  em.createQuery(query).getSingleResult();
		
		return result;
	}

	/**
	 * Get leaf library id from leaf_library table
	 * @param templateId
	 * @return
	 */
	private Integer getLeafLibraryId(Integer templateId) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Integer> query= builder.createQuery(Integer.class);
		Root<LeafLibrary> root= query.from(LeafLibrary.class); 

		query.select(builder.coalesce(root.get(LeafLibrary_.leafLibraryId),-1))	 
			 .where(builder.equal(root.get(LeafLibrary_.leafLibrarySoaptemplateId), templateId));
		
		Integer list= em.createQuery(query).getSingleResult();

		return list;
	}

	/**
	 * Get patient basic info
	 * @param patientId
	 */
	public void getPatientInfo(Integer patientId){
		try{
			
			CriteriaBuilder builder= em.getCriteriaBuilder();
			CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
			Root<PatientRegistration> root= query.from(PatientRegistration.class); 

			query.multiselect(root.get(PatientRegistration_.patientRegistrationDob), 
								root.get(PatientRegistration_.patientRegistrationSex));
			query.where(builder.equal(root.get(PatientRegistration_.patientRegistrationId), patientId));
			List<Object[]> list= em.createQuery(query).getResultList();

			if(list!= null && list.size()>0){
				patDOB= (Date)list.get(0)[0];
				try{
					patientSex= Short.parseShort(list.get(0)[1]+"");
				}catch(Exception e){
					patientSex= -1;
				}
				ageinYears= textFormat.getAgeInYear(textFormat.convertDate(patDOB, "yyyy-MM-dd", "MM/dd/yyyy"));
				
				SimpleDateFormat mdyFormat = new SimpleDateFormat("MM/dd/yyyy");
				ageinDay = (int)((DateUtil.dateDiff( DateUtil.DATE , DateUtil.getDate(mdyFormat.format(patDOB)) , new java.util.Date())));
			}

		}catch(Exception e){
			System.out.println("Exception while getting patient details for vitals");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Get Plan notes/Dx notes
	 * @param patientId
	 * @param encounterId
	 * @param dxcode
	 * @return
	 */
	@Override
	public String getNotes(Integer patientId, Integer encounterId, String dxcode) {
		
		patientId= Optional.fromNullable(patientId).or(-1);
		encounterId= Optional.fromNullable(encounterId).or(-1);
		dxcode= Optional.fromNullable(dxcode).or("").trim();
		
		String notes= "";
		
		if(dxcode.length()>0)
			notes= getDxNotes(patientId, encounterId, dxcode);
		else{
			notes= getEncounterNotes(patientId, encounterId, dxcode);
			if(notes.trim().length()==0)
				notes= getPatientClinicalElementNotes(patientId, encounterId, dxcode);
		}
		return notes;
	}

	/**
	 * Get dx related notes
	 * @param patientId
	 * @param encounterId
	 * @param dxcode
	 * @return
	 */
	private String getDxNotes(Integer patientId, Integer encounterId,
			String dxcode) {
		
		try{
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object> query= builder.createQuery();
		Root<PatientAssessments> root= query.from(PatientAssessments.class);

		query.select(root.get(PatientAssessments_.planNotes));
		
		query.where(builder.equal(root.get(PatientAssessments_.patient_assessments_id), encounterId),
					 builder.equal(root.get(PatientAssessments_.patient_assessments_patientId), patientId),
					  builder.like(builder.trim(root.get(PatientAssessments_.patient_assessments_dxcode)), dxcode));
		List<Object> result= em.createQuery(query).getResultList();
		if(result !=null && result.size()>0)
			return result.get(0).toString();
		return "";
	
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}		
	}

	/**
	 * Get notes from patient clinical elements
	 * @param patientId
	 * @param encounterId
	 * @param dxcode
	 */
	private String getPatientClinicalElementNotes(Integer patientId,
			Integer encounterId, String dxcode) {
		
		try{
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object> query= builder.createQuery();
		Root<PatientClinicalElements> root= query.from(PatientClinicalElements.class);

		query.select(root.get(PatientClinicalElements_.patientClinicalElementsValue))
			 .where(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId),
					 builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid), patientId),
					  builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsGwid), "0000400000000000001"));
		List<Object> result= em.createQuery(query).getResultList();
		if(result !=null && result.size()>0)
			return result.get(0).toString();
		return "";
		
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Get encounter notes
	 * @param patientId
	 * @param encounterId
	 * @param dxcode
	 * @return
	 */
	private String getEncounterNotes(Integer patientId, Integer encounterId,
			String dxcode) {
		try{
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object> query= builder.createQuery();
		Root<EncounterPlan> root= query.from(EncounterPlan.class);
		
		query.select(root.get(EncounterPlan_.plantext))
			 .where(builder.equal(root.get(EncounterPlan_.encounterid), encounterId));
		
		List<Object> result= em.createQuery(query).getResultList();
		if(result !=null && result.size()>0)
			return result.get(0).toString();
		return "";
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Get Plan shortcuts
	 * @param limit
	 * @param offset
	 * @param key
	 * @return
	 */
	@Override
	public ShortcutsData getPlanShortcuts(Integer limit, Integer offset, String key) {

		try{
		
		key= Optional.fromNullable(key).or("");
		if(key.length()>0)
			return getShortcuts(limit, offset, key);				
		else
			return getShortcuts(limit, offset);

		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * Get Plan shortcuts on search
	 * @param limit
	 * @param offset
	 * @param key
	 */
	private ShortcutsData getShortcuts(Integer limit, Integer offset, String key) {
		try{
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Data> query= builder.createQuery(Data.class);
		Root<GeneralShortcut> root= query.from(GeneralShortcut.class);
		query.select(builder.construct(Data.class, root.get(GeneralShortcut_.generalShortcutId),
				   		   root.get(GeneralShortcut_.generalShortcutCode),
				   		    root.get(GeneralShortcut_.generalShortcutDescription)))
			 .where(builder.equal(root.get(GeneralShortcut_.generalShortcutMapGroupId), 5),
					 builder.equal(root.get(GeneralShortcut_.generalShortcutIsactive), true),
					  builder.like(builder.lower(root.get(GeneralShortcut_.generalShortcutCode)), key.toLowerCase()+"%"))
			 .orderBy(builder.asc(root.get(GeneralShortcut_.generalShortcutCode)));
		
		List<Data> result= em.createQuery(query).setMaxResults(limit).setFirstResult(offset).getResultList();
		long count= em.createQuery(query).getResultList().size();
		/*List<Data> shortcutsArr= new ArrayList<Data>();
		for(int i=0; i<result.size(); i++){
			 JSONObject shortcutObj= new JSONObject();
			 Object[] obj= result.get(i);
			 shortcutObj.put("id", obj[0]);
			 shortcutObj.put("name", obj[1]);
			 shortcutObj.put("value", obj[2]);
			 shortcutsArr.put(shortcutObj);
		}*/
		
		ShortcutsData returnJSON= new ShortcutsData();
		returnJSON.setData(result);
		returnJSON.setPages(getNormalPageDetails(limit, offset, count));
		returnJSON.setOffset(offset);
		
		return returnJSON;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Get Plan shortcuts on scroll
	 * @param limit
	 * @param offset
	 */
	private ShortcutsData getShortcuts(Integer limit, Integer offset) {
		try{
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Data> query= builder.createQuery(Data.class);
		Root<GeneralShortcut> root= query.from(GeneralShortcut.class);
		query.select(builder.construct(Data.class, root.get(GeneralShortcut_.generalShortcutId),
				   		   root.get(GeneralShortcut_.generalShortcutCode),
				   		    root.get(GeneralShortcut_.generalShortcutDescription)))
			 .where(builder.equal(root.get(GeneralShortcut_.generalShortcutMapGroupId), 5),
					 builder.equal(root.get(GeneralShortcut_.generalShortcutIsactive), true))
			 .orderBy(builder.asc(root.get(GeneralShortcut_.generalShortcutCode)));
		
		List<Data> result= em.createQuery(query).setMaxResults(limit).setFirstResult(offset).getResultList();
		long count= em.createQuery(query).getResultList().size();
		
		ShortcutsData returnJSON= new ShortcutsData();
		returnJSON.setData(result);
		returnJSON.setPages(getNormalPageDetails(limit, offset, count));
		returnJSON.setOffset(offset);
		
		return returnJSON;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}

	public static Pages getNormalPageDetails(int limit , int offset, long totalCount) throws Exception{
		
		Pages pageInfoData=new Pages();
		int total=(int)Math.floor( ( ((double)totalCount) /limit) ) ;

		pageInfoData.setTotalPages(Integer.toString((int) Math.ceil( ( ((double)totalCount) /limit) ) ) );
/*		pageInfoData.put("lastOffset", ((total*limit)) );
		if(offset==0)
			pageInfoData.s("prevOffset", 0);
		else
			pageInfoData.put("prevOffset", offset-limit);*/
		pageInfoData.setCurrentPage(Integer.toString((offset/limit)+1));
		if((offset/limit) == total)
			pageInfoData.setNextOffset(Integer.toString(offset));
		else
			pageInfoData.setNextOffset(Integer.toString(offset+limit));
		return pageInfoData;
	}

	/**
	 * Get current encounter diagnosis codes
	 * @param patientId
	 * @param encounterId
	 * @return
	 */
	@Override
	public List<PatientAssessments> getCurrentDx(Integer patientId, Integer encounterId) {
		
		try{
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<PatientAssessments> query= builder.createQuery(PatientAssessments.class);
		Root<PatientAssessments> root= query.from(PatientAssessments.class);
		
		query.select(builder.construct(PatientAssessments.class, 
					  root.get(PatientAssessments_.patient_assessments_id), 
					   root.get(PatientAssessments_.patient_assessments_dxcode), 
					    root.get(PatientAssessments_.patient_assessments_dxdescription),
					     root.get(PatientAssessments_.patient_assessments_assessmentcomment),
					      root.get(PatientAssessments_.patient_assessments_status),
				    	   root.get(PatientAssessments_.patient_assessmentsCodingSystemid)))
			  .where(builder.equal(root.get(PatientAssessments_.patient_assessments_id), encounterId),
					  builder.equal(root.get(PatientAssessments_.patient_assessments_patientId), patientId),
					   builder.equal(root.get(PatientAssessments_.patient_assessments_isactive), false))
			  .orderBy(builder.asc(root.get(PatientAssessments_.patient_assessments_dxorder)));
		
		List<PatientAssessments> result= em.createQuery(query).getResultList();
		
		return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Get Encounter Patient instructions
	 * @param encounterId
	 * @param gwId
	 * @return
	 */
	@Override
	public String getPatientIns(Integer encounterId, String gwId) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<String> query= builder.createQuery(String.class);
		Root<PatientClinicalElements> root= query.from(PatientClinicalElements.class);
		
		query.select(root.get(PatientClinicalElements_.patientClinicalElementsValue));
		query.where(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId),
				     builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsGwid), gwId));
		
		String result= null;
		try{
			result= em.createQuery(query).getSingleResult();
		}catch(NoResultException e){
			result= "";
		}
		return result;
	}

	/**
	 * Get Return visit elements
	 * @param templateId
	 * @param encounterId
	 * @return
	 */
	@Override
	public List<ReturnVisitData> getReturnVisit(Integer templateId,
			Integer encounterId) {
		
		boolean format= getDateFormat();
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<ReturnVisitData> query= builder.createQuery(ReturnVisitData.class);
		Root<PlanInstruction> root= query.from(PlanInstruction.class);
		Join<PlanInstruction, ClinicalElements> clinicalJoin= root.join(PlanInstruction_.clinicalElements, JoinType.INNER);
		Join<ClinicalElements, ClinicalElementTemplateMapping> templateJoin= clinicalJoin.join(ClinicalElements_.clinicalElementTemplateMapping, JoinType.INNER);
		Join<ClinicalElements, PatientClinicalElements> patientJoin= clinicalJoin.join(ClinicalElements_.patientClinicalElements, JoinType.LEFT);
		
		Predicate gwidPred= builder.or(builder.and(builder.like(templateJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingGwid), "00004006%"),
													builder.equal(templateJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid), templateId)),
									   builder.and(builder.equal(templateJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingGwid), "0000400000000000002"),
											   		builder.equal(templateJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid), templateId)));
		templateJoin.on(gwidPred);
		patientJoin.on(builder.equal(patientJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId));
		
		query.select(builder.construct(ReturnVisitData.class, 
										clinicalJoin.get(ClinicalElements_.clinicalElementsGwid),
										 templateJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingGwid),
										  clinicalJoin.get(ClinicalElements_.clinicalElementsDatatype),
										   root.get(PlanInstruction_.planInstructionIsactive),																   	
										     clinicalJoin.get(ClinicalElements_.clinicalElementsName),
										      builder.coalesce(patientJoin.get(PatientClinicalElements_.patientClinicalElementsValue),""),
										       root.get(PlanInstruction_.planInstructionOrderby)
										));
		query.orderBy(builder.asc(root.get(PlanInstruction_.planInstructionOrderby)));
		List<ReturnVisitData> result= em.createQuery(query).getResultList();
		for(ReturnVisitData resultObj: result){
			resultObj.setFormat(format);
		}
		return result;
	}

	/**
	 * Get Return visit date format
	 * @return
	 */
	private Boolean getDateFormat() {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Boolean> query= builder.createQuery(Boolean.class);
		Root<CnmSettings> root= query.from(CnmSettings.class);
		
		query.select(builder.coalesce(root.get(CnmSettings_.cnmSettingsIsactive), false));
		query.where(builder.equal(root.get(CnmSettings_.cnmSettingsId), 9));
		
		try{
			Boolean result= em.createQuery(query).getSingleResult();
			return result;
		}catch(NoResultException e){
			return false;
		}
		
	}

	/**
	 * Mapping instruction to dxcode
	 * @param insId
	 * @param dxcode
	 * @param mappingType
	 * @param codingsystem
	 * @return
	 */
	@Override
	public void mapInstructiontoDx(Integer insId, String dxcode,
			Integer mappingType, String codingsystem) {
		
		deleteDuplicateMapping(insId, dxcode, mappingType);
		PlanMapping entity= new PlanMapping();
		entity.setPlanMappingInstructionid(insId);
		entity.setPlanMappingCode(dxcode);
		entity.setPlanMappingType(mappingType);
		entity.setPlanMappingCodingsystem(codingsystem);
		planMappingRepository.save(entity);
		
	}

	/**
	 * Deleting duplicate dx mappings
	 * @param insId
	 * @param dxcode
	 * @param mappingType
	 */
	private void deleteDuplicateMapping(Integer insId, String dxcode,
			Integer mappingType) {
		
		List<PlanMapping> list= planMappingRepository.findAll(PlanSpecification.getDxMapping(insId, dxcode, mappingType));
		if(list.size()>0)
			planMappingRepository.deleteInBatch(list);
	}

	/**
	 * Update after care instruction status
	 * @param insId
	 * @param insName
	 * @param insStatus
	 * @param encounterId
	 * @param patientId
	 * @param otherIns
	 * @param dxHandout
	 * @param dxCode
	 * @param codingsystem
	 */
	@Override
	public void updatePatientAftercareIns(Integer insId, String insName,
			Boolean insStatus, Integer encounterId, Integer patientId,
			Integer otherIns, Integer dxHandout, String dxCode, String codingsystem) {
		
		if(insStatus== true){
			List<PatientAftercareData> list= getPatientAftercareData(patientId, encounterId, insId, dxCode, codingsystem);
			deleteAftercareIns(list);
			insertAftercareIns(insId, insName, insStatus, encounterId, patientId, otherIns, dxHandout, dxCode, codingsystem);
		}else{
			updateAftercareIns(insId, insName, insStatus, encounterId, patientId, otherIns, dxHandout, dxCode, codingsystem);
		}
		
	}

	/**
	 * Get Patient After care instructions
	 * @param patientId
	 * @param encounterId
	 * @param insId
	 * @return
	 */
	private List<PatientAftercareData> getPatientAftercareData(
			Integer patientId, Integer encounterId, Integer insId, String dxCode, String codingsystem) {
		return patientAftercareDataRepository.findAll(PlanSpecification.getAftercareIns(patientId, encounterId, insId, dxCode.trim(), codingsystem));
	}

	/**
	 * Delete Patient After care instructions
	 * @param list
	 */
	private void deleteAftercareIns(List<PatientAftercareData> list) {
		if(list != null && list.size()> 0)
			patientAftercareDataRepository.deleteInBatch(list);
	}

	/**
	 * Update Patient After care instructions
	 * @param insId
	 * @param insName
	 * @param insStatus
	 * @param encounterId
	 * @param patientId
	 * @param otherIns
	 * @param dxHandout
	 * @param dxCode
	 */
	private void updateAftercareIns(Integer insId, String insName, Boolean insStatus, Integer encounterId,
			Integer patientId, Integer otherIns, Integer dxHandout, String dxCode, String codingSystem) {
		
		List<PatientAftercareData> list= getPatientAftercareData(patientId, encounterId, insId, dxCode, codingSystem);
		for(int i=0; i<list.size(); i++){
			PatientAftercareData entity= list.get(i);
			entity.setPatientAftercareDataStatus(insStatus);
			patientAftercareDataRepository.save(entity);
		}
	}

	/**
	 * Save Patient After care instruction status
	 * @param insId
	 * @param insName
	 * @param insStatus
	 * @param encounterId
	 * @param patientId
	 * @param otherIns
	 * @param dxHandout
	 * @param dxCode
	 * @param codingsystem
	 */
	private void insertAftercareIns(Integer insId, String insName, Boolean insStatus,
			Integer encounterId, Integer patientId, Integer otherIns,
			Integer dxHandout, String dxCode, String codingsystem) {
		
		Integer maxId= getMaxPatientAftercareInsId();
		
		if(dxHandout== 1 && dxCode.trim().length()> 0){
			deleteAftercareInsMapping(insId, dxCode, 3);
			AftercareInsMapping entity= new AftercareInsMapping();
			entity.setAftercareInsMappingInsId(insId);
			entity.setAftercareInsMappingCode(dxCode.trim());
			entity.setAftercareInsMappingCodingSystem(codingsystem);
			entity.setAftercareInsMappingInsType(3);			
			aftercareInsMappingRepository.save(entity);
//			otherIns= 0;
		}
		
//		deletePatientAftercareData(patientId, encounterId, insId, dxCode, codingsystem);
		PatientAftercareData entity= new PatientAftercareData();
		entity.setPatientAftercareDataId(maxId);
		entity.setPatientAftercareDataPatientId(patientId);
		entity.setPatientAftercareDataEncounterId(encounterId);
		entity.setPatientAftercareDataAftercareId(insId);
		entity.setPatientAftercareDataUnknown1(otherIns);
		entity.setPatientAftercareDataName(insName);
		entity.setPatientAftercareDataStatus(insStatus);
		entity.setPatientAftercareDataCategory("FRMMED");
		if(!dxCode.isEmpty())
			entity.setPatientAftercareDataDxcode(dxCode.trim());
		if(!codingsystem.isEmpty())
			entity.setPatientAftercareDataDxcodesystem(codingsystem.trim());
		patientAftercareDataRepository.saveAndFlush(entity);
	}

	/**
	 * Deleting duplicate patient after care data
	 * @param patientId
	 * @param encounterId
	 * @param insId
	 * @param dxCode
	 * @param codingsystem
	 */
	private void deletePatientAftercareData(Integer patientId,
			Integer encounterId, Integer insId, String dxCode,
			String codingsystem) {
		patientAftercareDataRepository.deleteInBatch(getPatientAftercareData(patientId, encounterId, insId, dxCode, codingsystem));
	}

	/**
	 * Deleting duplicate After care instruction dx mapping entries
	 * @param insId
	 * @param dxCode
	 * @param insType
	 */
	private void deleteAftercareInsMapping(Integer insId, String dxCode, int insType) {		
		aftercareInsMappingRepository.deleteInBatch(getAftercareInsMapping(insId, dxCode, insType));
	}

	/**
	 * Get After care instruction dx mapping entries
	 * @param insId
	 * @param dxCode
	 * @param insType
	 * @return
	 */
	private List<AftercareInsMapping> getAftercareInsMapping(Integer insId, String dxCode, int insType) {
		return aftercareInsMappingRepository.findAll(PlanSpecification.getAftercareInsMapping(insId, dxCode, insType));
	}

	/**
	 * Get max id for PatientAftercareData
	 * @return
	 */
	private Integer getMaxPatientAftercareInsId() {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Integer> query= builder.createQuery(Integer.class);
		Root<PatientAftercareData> root= query.from(PatientAftercareData.class);
		
		query.select(builder.coalesce(builder.max(root.get(PatientAftercareData_.patientAftercareDataId)),0));
		return em.createQuery(query).getSingleResult()+1;
	}

	@Override
	public void getLanguages(Integer insId, Integer patientId) {
		
	}

	@Override
	public List<ReferringDoctor> getReferringPhysicians() {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<ReferringDoctor> query= builder.createQuery(ReferringDoctor.class);
		Root<ReferringDoctor> root= query.from(ReferringDoctor.class);
		
		query.select(builder.construct(ReferringDoctor.class,
				root.get(ReferringDoctor_.referring_doctor_uniqueid),
				root.get(ReferringDoctor_.referring_doctor_lastname),
				root.get(ReferringDoctor_.referring_doctor_firstname)));
		
		query.where(builder.equal(root.get(ReferringDoctor_.referring_doctor_isactive), true));		
		
		return em.createQuery(query).getResultList();
	}
}
