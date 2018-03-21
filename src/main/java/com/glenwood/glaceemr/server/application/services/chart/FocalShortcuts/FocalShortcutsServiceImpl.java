package com.glenwood.glaceemr.server.application.services.chart.FocalShortcuts;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions_;
import com.glenwood.glaceemr.server.application.models.ClinicalElements_;
import com.glenwood.glaceemr.server.application.models.FocalShortcut;
import com.glenwood.glaceemr.server.application.models.FocalShortcutElements;
import com.glenwood.glaceemr.server.application.models.FocalShortcutElements_;
import com.glenwood.glaceemr.server.application.models.FocalShortcut_;
import com.glenwood.glaceemr.server.application.models.HpiElement;
import com.glenwood.glaceemr.server.application.models.HpiSymptom;
import com.glenwood.glaceemr.server.application.models.HpiSymptomQualifier;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.PlanType;
import com.glenwood.glaceemr.server.application.models.PlanType_;
import com.glenwood.glaceemr.server.application.repositories.FocalShortcutElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.FocalShortcutRepository;
import com.glenwood.glaceemr.server.application.repositories.HpiElementRepository;
import com.glenwood.glaceemr.server.application.repositories.HpiSymptomQualifierRepository;
import com.glenwood.glaceemr.server.application.repositories.HpiSymptomRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.services.chart.SaveClinicalData.SaveClinicalDataService;
import com.glenwood.glaceemr.server.application.specifications.FocalShortcutSpecification;
import com.glenwood.glaceemr.server.application.specifications.HpiSpecification;
import com.glenwood.glaceemr.server.application.specifications.PatientClinicalElementsSpecification;
/**
 * Implementation for FocalShortcuts Service
 * @author Bhagya Lakshmi
 *
 */

@Service
@Transactional
public class FocalShortcutsServiceImpl implements FocalShortcutsService{
	
	@Autowired
	FocalShortcutRepository focalShortcutRepository;
	
	@Autowired
	FocalShortcutElementsRepository focalShortcutElementsRepository;
	
	@Autowired
	SaveClinicalDataService saveClinicalDataService;
	
	@Autowired
	HpiSymptomRepository hpiSymptomRepository;
	
	@Autowired
	PatientClinicalElementsRepository patientClinicalElementsRepository;
	
	@Autowired
	HpiSymptomQualifierRepository hpiSymptomQualifierRepository;
	
	@Autowired
	HpiElementRepository hpiElementRepository;
	
	@PersistenceContext
	EntityManager em;
	
	
	
	/**
	 * Method to get focal shortcuts available for particular tab 
	 */
	@Override
	public JSONArray getFocalshortcutsAvailable(String tabId) {
		List<FocalShortcut> focalShortcutsAvailable = focalShortcutRepository.findAll(FocalShortcutSpecification.getByTabId(tabId));
		JSONArray focal = new JSONArray();
		for(int i=0;i<focalShortcutsAvailable.size();i++)
		{
			JSONObject focalObject = new JSONObject();
			try
			{
				focalObject.put("code", focalShortcutsAvailable.get(i).getFocalShortcutName());
				focalObject.put("shrtid", focalShortcutsAvailable.get(i).getFocalShortcutId());
				focalObject.put("desc", focalShortcutsAvailable.get(i).getFocalShortcutDescription());
				focal.put(i, focalObject);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(focal.toString().equals("[]"))
		   {    focal.put("No Shortcuts");  
		   }
		return focal;
	}
	
	/**
	 * Method to Search the focal shortcut 
	 */
	@Override
	public JSONArray searchFocalShortcuts(Integer tabId,String focalsearch) {
		List<FocalShortcut> focalShortcuts = focalShortcutRepository.findAll(FocalShortcutSpecification.getByFocalShrtNameAndTabId(tabId,focalsearch));
		JSONArray focal = new JSONArray();
		for(int i=0;i<focalShortcuts.size();i++)
		{
			JSONObject focalObject = new JSONObject();
			try
			{
				focalObject.put("code", focalShortcuts.get(i).getFocalShortcutName());
				focalObject.put("shrtid", focalShortcuts.get(i).getFocalShortcutId());
				focal.put(i, focalObject);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if(focal.toString().equals("[]"))
		   {    
			focal.put("No Shortcuts");  
		   }
		return focal;
	}
	
	/**
	 * deleting particular elements in focal shortcut 
	 */
	@Override
	public void deleteElementsInFocalShortcut(String shortcutId) {
		List<FocalShortcutElements> focalShortcutElem = focalShortcutElementsRepository.findAll(FocalShortcutSpecification.getByFocalShortcutEelmentId(shortcutId));
		for(int i=0;i<focalShortcutElem.size();i++){
			FocalShortcutElements focalShortcut = focalShortcutElem.get(i);
			focalShortcut.setFocalShortcutElementsIsactive(false);
			focalShortcutElementsRepository.saveAndFlush(focalShortcut);
		}
	}
	
	/**
	 * Method to update focal shortcut
	 */
	@Override
	public void updateFocalShortcut(String descriptionShortcut, Boolean isActive, String focalId) {
		List<FocalShortcut> focalShortcut = focalShortcutRepository.findAll(FocalShortcutSpecification.getByFocalShortcutId(focalId));
		for(int i=0;i<focalShortcut.size();i++){
			FocalShortcut focalShortcutBean = focalShortcut.get(i);
			focalShortcutBean.setFocalShortcutIsactive(isActive);
			focalShortcutRepository.saveAndFlush(focalShortcutBean);
		}
		
	}

	/**
	 * Method to get focal shortcut data
	 * @return 
	 */
	@Override
	public JSONArray getFocalShortcutData(String focalIndex, Integer tabId,
			Integer patientId, Integer chartId,
			Integer encounterId, Integer templateId) {
		JSONObject saveDataJSON = new JSONObject();
		JSONArray focalReturnArr = new JSONArray();
		try
		{
			saveDataJSON.put("chartId",chartId);
			saveDataJSON.put("templateId",templateId);
			saveDataJSON.put("patientId",patientId);
			saveDataJSON.put("encounterId",encounterId);
			saveDataJSON.put("chartId",chartId);
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<ClinicalElements> root = cq.from(ClinicalElements.class);
		/*Join<ClinicalElements, FocalShortcutElements> join1 = root.join(ClinicalElements_.focalShortcutElements,JoinType.INNER);
		Predicate p1 = builder.equal(join1.get(FocalShortcutElements_.focalShortcutElementsMapid),focalIndex);
		Predicate p2 = builder.equal(join1.get(FocalShortcutElements_.focalShortcutElementsIsactive), true);
		join1.on(builder.and(p1,p2));*/
		
		cq.multiselect(
				root.get(ClinicalElements_.clinicalElementsDatatype),
				root.get(ClinicalElements_.clinicalElementsGwid),
				//join1.get(FocalShortcutElements_.focalShortcutElementsValue),
				root.get(ClinicalElements_.clinicalElementsDatatype));
			
		List<Object[]> obj = em.createQuery(cq).getResultList();
		JSONArray elementsToSaveArr = new JSONArray();
		for(Object[] values: obj)
		{
			//Integer dataType = (Integer) values[3];
			String focalConcat = values[2].toString(); // values[2]====>focalShortcutElementsValue
			try {
				/*if(dataType == 2)
				{
					focalConcat="<![CDATA["+values[2].toString()+"]]";
				}
				else
				{
					focalConcat = values[2].toString();
				}*/
				JSONObject elementSaveObj=new JSONObject();
				elementSaveObj.put("elemenetsToSaveType",values[0]); // values[0]====>clinicalElementsDatatype
				elementSaveObj.put("valueToSave",focalConcat);
				elementSaveObj.put("clinicalelementgwid",values[1]); // values[1]===>clinicalElementsGwid
				elementsToSaveArr.put(elementSaveObj);
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		JSONArray elementsToDeleteArr = new JSONArray();
		JSONObject elementDeleteObj=new JSONObject();
		try
		{
			elementDeleteObj.put("elemenetsToDeleteType",-1);
			elementDeleteObj.put("valueToDelete","");
			elementDeleteObj.put("clinicalelementgwid","");
		
			elementsToDeleteArr.put(elementDeleteObj);
		
			saveDataJSON.put("ElementsToSave", elementsToSaveArr);
			saveDataJSON.put("ElementsToDelete", elementsToDeleteArr);
			
			saveClinicalDataService.saveData(patientId, encounterId, chartId, templateId, saveDataJSON.toString());
		
			List<FocalShortcut> focalDesc = focalShortcutRepository.findAll(FocalShortcutSpecification.getByFocalShortcutId(focalIndex));
			for(int i=0;i<focalDesc.size();i++)
			{
				JSONObject focalJObj = new JSONObject();
				focalJObj.put("desc", focalDesc.get(i).getFocalShortcutDescription() == null?"":focalDesc.get(i).getFocalShortcutDescription());
				focalJObj.put("code", focalDesc.get(i).getFocalShortcutName() == null?"":focalDesc.get(i).getFocalShortcutName());
				focalReturnArr.put(i, focalJObj);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return focalReturnArr;
	}


	/**
	 * Method to add new focal shortcut
	 */
	@Override
	public JSONArray addNewFocalShorctut(Integer tabId, Integer patientId,
			Integer encounterId, String symptomIds) {
		List<PatientClinicalElements> patElements = null;
		JSONArray result = null;
		if(tabId==1){
			List<HpiSymptom> hpiSymptomId = hpiSymptomRepository.findAll(HpiSpecification.getBySymptomGWId(symptomIds));
			String gwids="";
			List<Integer> hpiSymptomIdList = new ArrayList<Integer>();
			List<Integer> hpiSymptomQualifierIdList = new ArrayList<Integer>();
			for(int i=0;i<hpiSymptomId.size();i++)
			{
				hpiSymptomIdList.add(hpiSymptomId.get(i).getHpiSymptomId());
			}
			List<HpiSymptomQualifier> hpiSymptomQualifierId = hpiSymptomQualifierRepository.findAll(HpiSpecification.getByQualifierSymptomIdList(hpiSymptomIdList));
			for(int j=0;j<hpiSymptomQualifierId.size();j++)
			{
				hpiSymptomQualifierIdList.add(hpiSymptomQualifierId.get(j).getHpiSymptomQualifierId());
			}
			List<HpiElement> hpiElementGwid = hpiElementRepository.findAll(HpiSpecification.getByHpiElementQualifierIdList(hpiSymptomQualifierIdList));
			for(int k=0;k<hpiElementGwid.size();k++)
			{
				gwids=gwids+hpiElementGwid.get(k).getHpiElementGwid()+",";
			}
			gwids=gwids+"0000100100000000000,0000100200000000000";
			patElements = patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getByPatEncGwId(patientId,encounterId,gwids));
			result = new JSONArray();
			for(int i=0;i<patElements.size();i++)
			{
				JSONObject patElementsObj = new JSONObject();
				try
				{
					patElementsObj.put("patient_clinical_elements_gwid", patElements.get(i).getPatientClinicalElementsGwid());
					patElementsObj.put("patient_clinical_elements_value", patElements.get(i).getPatientClinicalElementsValue());
					result.put(i,patElementsObj);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			List<String> gwidList = new ArrayList<String>();
			for(int k=0;k<result.length();k++){
				try {
				JSONObject data=(JSONObject) result.get(k);
				String gwidStr;
					gwidStr = data.get("patient_clinical_elements_gwid").toString().replace("\"","");
					if(!gwidList.contains(gwidStr))
						gwidList.add(gwidStr);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			List<String> symptomList=new ArrayList<String>();
			String gwid=null;
			String type=null;
			String value=null;
			for(int k=0;k<gwidList.size();k++){
				 List<HpiElement> hpiElement = hpiElementRepository.findAll(HpiSpecification.getByHpiElementGwid(gwidList.get(k)));
				 List<Integer> hpiElementQualifierid = new ArrayList<Integer>();
				 for(int i=0;i<hpiElement.size();i++)
				 {
					 hpiElementQualifierid.add(hpiElement.get(i).getHpiElementQualifierid());
				 }
				 if(hpiElementQualifierid.size()>0)
				 {
					 List<HpiSymptomQualifier> hpiSymptomQualifier = hpiSymptomQualifierRepository.findAll(HpiSpecification.getByQualifierSymptomId(hpiElementQualifierid));
					 List<Integer> hpiSymptomQualifierSymId = new ArrayList<Integer>();
					 for(int i=0;i<hpiSymptomQualifier.size();i++)
					 {
						 hpiSymptomQualifierSymId.add(hpiSymptomQualifier.get(i).getHpiSymptomQualifierSymptomId());
					 }
					 if(hpiSymptomQualifierSymId.size()>0)
					 {
						 List<HpiSymptom> hpiSymptom = hpiSymptomRepository.findAll(HpiSpecification.getByHpiSymptomId(hpiSymptomQualifierSymId));
						  for(int l=0;l<hpiSymptom.size();l++)
						  {
							  gwid= hpiSymptom.get(l).getHpiSymptomGwid();
							  	if(!symptomList.contains(gwid))
							  		symptomList.add(gwid);
						  }
					 }
				 }
			 }
	  
			 for(int i=0;i<symptomList.size();i++){
				  String symgwid=symptomList.get(i).trim();
				  List<HpiSymptom> hpiSymptomType = hpiSymptomRepository.findAll(HpiSpecification.getBySymptomGWId(symgwid));
					for(int s=0;s<hpiSymptomType.size();s++){
						type=hpiSymptomType.get(s).getHpiSymptomType().toString();
						 if(type.equals("2"))
							 value="2";
						 else
							 value="1";
					}
				JSONObject symObj=new JSONObject();
				try
				{
				 symObj.put("patient_clinical_elements_gwid", symgwid);
				 symObj.put("patient_clinical_elements_value", value);
				 result.put(symObj);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * Method to save focal Data
	 * @return 
	 */
	@Override
	public JSONArray saveFocalData(String tabid, String shortcutName,
			String focalDescription, String xmlData) {
		JSONArray saveJson= new JSONArray();
		int flag=0;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<FocalShortcut> root = cq.from(FocalShortcut.class);
		cq.select(root.get(FocalShortcut_.focalShortcutName));
		cq.where(builder.and(builder.equal(root.get(FocalShortcut_.focalShortcutName), shortcutName),builder.equal(root.get(FocalShortcut_.focalShortcutTabid), tabid)));
		List<Object> focalShortcut=em.createQuery(cq).getResultList();
		for(int i=0;i<focalShortcut.size();i++)
		{
			if(focalShortcut.get(i).toString().equalsIgnoreCase(shortcutName))
			{
				flag = 1;
			}
		}
		if(flag == 0)
		{
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				InputSource is = new InputSource();
				is.setByteStream(new ByteArrayInputStream(xmlData.getBytes()));
				Document doc = dBuilder.parse(is);
				doc.getDocumentElement().normalize();
				NodeList nList =doc.getElementsByTagName("data");
				FocalShortcut focalShortcutInsert = new FocalShortcut();
				focalShortcutInsert.setFocalShortcutName(shortcutName);
				focalShortcutInsert.setFocalShortcutTabid(Integer.parseInt(tabid));
				focalShortcutInsert.setFocalShortcutIsactive(true);
				focalShortcutInsert.setFocalShortcutDescription(focalDescription);
				focalShortcutRepository.saveAndFlush(focalShortcutInsert);
				long rs = Long.valueOf(focalShortcutInsert.getFocalShortcutId().toString());
				for (int temp = 0; temp < nList.getLength(); temp++) {
				       Node nNode = nList.item(temp);
				   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					   		FocalShortcutElements focalShortcutElements = new FocalShortcutElements();			
							Element eElement = (Element) nNode;
					        NodeList nlList = eElement.getElementsByTagName("gwid").item(0).getChildNodes();
					        NodeList nlList2 = eElement.getElementsByTagName("value").item(0).getChildNodes();
					        Node nValue = (Node) nlList.item(0);
					        Node nValue2 = (Node) nlList2.item(0);
					        String gwid=nValue.getNodeValue();
					        String value=nValue2.getNodeValue(); 
					        int id=(int) rs;
					        focalShortcutElements.setFocalShortcutElementsGwid(gwid);
					        focalShortcutElements.setFocalShortcutElementsValue(value);
					        focalShortcutElements.setFocalShortcutElementsIsactive(true);
					        focalShortcutElements.setFocalShortcutElementsMapid(id);
					        focalShortcutElementsRepository.save(focalShortcutElements);
					        if(temp % 20 == 0)
					        {
					        	focalShortcutElementsRepository.flush();
					        }
					}
				}
				saveJson.put("sucess");
			    }
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			else
			{     
				String failed="shortcut already present";
				saveJson.put(failed.toString());
			}
			return saveJson;	
		}

	/**
	 * Search focal shortcuts
	 * @param key
	 * @param tabId
	 * @return
	 * @throws JSONException 
	 */
	@Override
	public JSONArray searchFocalShortcut(String key, Integer tabId) throws JSONException {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<FocalShortcut> root= query.from(FocalShortcut.class);
		
		query.multiselect(root.get(FocalShortcut_.focalShortcutId),
						   root.get(FocalShortcut_.focalShortcutName));
		query.where(builder.equal(root.get(FocalShortcut_.focalShortcutIsactive), true),
					 builder.equal(root.get(FocalShortcut_.focalShortcutTabid), tabId),
					  builder.like(builder.upper(root.get(FocalShortcut_.focalShortcutName)), key.toUpperCase()+"%"));
		query.orderBy(builder.asc(root.get(FocalShortcut_.focalShortcutName)));
		
		List<Object[]> result= em.createQuery(query).getResultList();
		JSONArray returnList= new JSONArray();
		for(int i=0; i<result.size(); i++){
			Object[] obj= result.get(i);
			JSONObject returnObj= new JSONObject();			
			returnObj.put("shrtid", obj[0]);
			returnObj.put("code", obj[1]);
			returnList.put(returnObj);
		}
		if(returnList.length()==0)
			returnList.put("No Shortcuts");
		return returnList;
	}

	/**
	 * Get selected focal shortcut
	 * @param focalId
	 * @return
	 * @throws JSONException 
	 */
	@Override
	public JSONArray fetchFocalShortcut(Integer focalId) throws JSONException {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<FocalShortcutElements> root= query.from(FocalShortcutElements.class);
		Join<FocalShortcutElements, FocalShortcut> shortcutJoin= root.join(FocalShortcutElements_.focalShortcut, JoinType.INNER);
		Join<FocalShortcutElements, ClinicalElements> clinicalJoin= root.join(FocalShortcutElements_.clinicalElements, JoinType.INNER);
		Join<FocalShortcutElements, PlanType> planJoin= root.join(FocalShortcutElements_.planType, JoinType.LEFT);
		Join<ClinicalElements, ClinicalElementsOptions> optionsJoin= clinicalJoin.join(ClinicalElements_.clinicalElementsOptions, JoinType.LEFT);
//		optionsJoin.on(builder.equal(optionsJoin.get(ClinicalElementsOptions_.clinicalElementsOptionsValue), root.get(FocalShortcutElements_.focalShortcutElementsValue)));
		
		query.multiselect(root.get(FocalShortcutElements_.focalShortcutElementsId),
						   root.get(FocalShortcutElements_.focalShortcutElementsGwid),
						   	root.get(FocalShortcutElements_.focalShortcutElementsValue),
						   	 root.get(FocalShortcutElements_.focalShortcutElementsMapid),
						   	  builder.coalesce(clinicalJoin.get(ClinicalElements_.clinicalElementsName),""),
						   	   builder.coalesce(planJoin.get(PlanType_.planTypeName),"NOTES"),
					 			builder.coalesce(clinicalJoin.get(ClinicalElements_.clinicalElementsDatatype),-1),
					 			 builder.coalesce(shortcutJoin.get(FocalShortcut_.focalShortcutName),""),
					 			  builder.coalesce(optionsJoin.get(ClinicalElementsOptions_.clinicalElementsOptionsName),""));
		query.where(builder.equal(root.get(FocalShortcutElements_.focalShortcutElementsIsactive), true),
					 builder.equal(shortcutJoin.get(FocalShortcut_.focalShortcutId), focalId),
					  builder.equal(optionsJoin.get(ClinicalElementsOptions_.clinicalElementsOptionsValue), root.get(FocalShortcutElements_.focalShortcutElementsValue)),
					   builder.equal(clinicalJoin.get(ClinicalElements_.clinicalElementsDatatype), 4));
		
		List<Object[]> result= em.createQuery(query).getResultList();
		JSONArray returnList= new JSONArray();
		for(int i=0; i<result.size(); i++){
			Object[] obj= result.get(i);
			JSONObject returnObj= new JSONObject();
			returnObj.put("id", obj[0]);
			returnObj.put("gwid", obj[1]);
			returnObj.put("optionvalue", obj[2]);
			returnObj.put("mapid", obj[3]);
			returnObj.put("elementname", obj[4]);
			returnObj.put("systemname", obj[5]);
			returnObj.put("datatype", obj[6]);
			returnObj.put("shortcutname", obj[7]);
			if(Integer.parseInt(obj[6].toString())>=4)
				returnObj.put("optionname", obj[8].toString());
			else
				returnObj.put("optionname", "");
			returnList.put(returnObj);
		}
		
		JSONArray secondList= fetchFocalShortcut2(focalId);
		for(int i=0; i<secondList.length(); i++){
			JSONObject obj= secondList.getJSONObject(i);
			returnList.put(obj);
		}
		
		return returnList;
	}
	
	/**
	 * Get selected focal shortcut
	 * @param focalId
	 * @return
	 * @throws JSONException 
	 */
	public JSONArray fetchFocalShortcut2(Integer focalId) throws JSONException {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<FocalShortcutElements> root= query.from(FocalShortcutElements.class);
		Join<FocalShortcutElements, FocalShortcut> shortcutJoin= root.join(FocalShortcutElements_.focalShortcut, JoinType.INNER);
		Join<FocalShortcutElements, ClinicalElements> clinicalJoin= root.join(FocalShortcutElements_.clinicalElements, JoinType.INNER);
		Join<FocalShortcutElements, PlanType> planJoin= root.join(FocalShortcutElements_.planType, JoinType.LEFT);
		Join<ClinicalElements, ClinicalElementsOptions> optionsJoin= clinicalJoin.join(ClinicalElements_.clinicalElementsOptions, JoinType.LEFT);
//		optionsJoin.on(builder.equal(optionsJoin.get(ClinicalElementsOptions_.clinicalElementsOptionsValue), root.get(FocalShortcutElements_.focalShortcutElementsValue)));
		
		query.multiselect(root.get(FocalShortcutElements_.focalShortcutElementsId),
						   root.get(FocalShortcutElements_.focalShortcutElementsGwid),
						   	root.get(FocalShortcutElements_.focalShortcutElementsValue),
						   	 root.get(FocalShortcutElements_.focalShortcutElementsMapid),
						   	  builder.coalesce(clinicalJoin.get(ClinicalElements_.clinicalElementsName),""),
						   	   builder.coalesce(planJoin.get(PlanType_.planTypeName),"NOTES"),
					 			builder.coalesce(clinicalJoin.get(ClinicalElements_.clinicalElementsDatatype),-1),
					 			 builder.coalesce(shortcutJoin.get(FocalShortcut_.focalShortcutName),""),
					 			  builder.coalesce(optionsJoin.get(ClinicalElementsOptions_.clinicalElementsOptionsName),""));
		query.where(builder.equal(root.get(FocalShortcutElements_.focalShortcutElementsIsactive), true),
					 builder.equal(shortcutJoin.get(FocalShortcut_.focalShortcutId), focalId),
					   builder.notEqual(clinicalJoin.get(ClinicalElements_.clinicalElementsDatatype), 4));
		
		List<Object[]> result= em.createQuery(query).getResultList();
		JSONArray returnList= new JSONArray();
		for(int i=0; i<result.size(); i++){
			Object[] obj= result.get(i);
			JSONObject returnObj= new JSONObject();
			returnObj.put("id", obj[0]);
			returnObj.put("gwid", obj[1]);
			returnObj.put("optionvalue", obj[2]);
			returnObj.put("mapid", obj[3]);
			returnObj.put("elementname", obj[4]);
			returnObj.put("systemname", obj[5]);
			returnObj.put("datatype", obj[6]);
			returnObj.put("shortcutname", obj[7]);
			if(Integer.parseInt(obj[6].toString())>=4)
				returnObj.put("optionname", obj[8].toString());
			else
				returnObj.put("optionname", "");
			returnList.put(returnObj);
		}
		
		return returnList;
	}

	/**
	 * Method to get patient encounter data
	 * @param patientId
	 * @param encounterId
	 * @param gwPattern
	 * @return
	 * @throws JSONException 
	 */
	@Override
	public JSONObject fetchPatientData(Integer patientId, Integer encounterId,
			String gwPattern) throws JSONException {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<PatientClinicalElements> root= query.from(PatientClinicalElements.class);
		
		query.multiselect(root.get(PatientClinicalElements_.patientClinicalElementsGwid),
						   root.get(PatientClinicalElements_.patientClinicalElementsValue));
		
		query.where(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid), patientId),
					 builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId),
					  builder.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid), gwPattern+"%"));
					  
		List<Object[]> result= em.createQuery(query).getResultList();
		JSONArray returnJSON= new JSONArray();
		List<String> gwidList= new ArrayList<String>();
		gwidList.add("-1");
		for(int i=0; i<result.size(); i++){
			Object[] obj= result.get(i);
			JSONObject returnObj= new JSONObject();
			returnObj.put("patient_clinical_elements_gwid", obj[0].toString());
			returnObj.put("patient_clinical_elements_value", obj[1].toString());
			returnJSON.put(returnObj);
			gwidList.add(obj[0].toString());
		}
		
		return getFocalShortcut(gwidList, returnJSON);
	}
	
	public JSONObject getFocalShortcut(List<String> gwidList, JSONArray returnJSON) throws JSONException{
		
		StringBuffer querryBuffer=new StringBuffer();	
		querryBuffer.append("select clinical_elements_gwid,clinical_elements_name,COALESCE(plan_type_name,'Notes'),clinical_elements_datatype,case clinical_elements_datatype when 4 then (select count(*) from clinical_elements_options where clinical_elements_options_gwid=clinical_elements_gwid) end as value,(select array_to_string(array_agg(clinical_elements_options_name),'~~') from (select clinical_elements_options_name from clinical_elements_options where clinical_elements_options_gwid=clinical_elements_gwid) as clinical_elements_options_name1) as optionname   from clinical_elements left join plan_type on plan_type_gwid=substr(clinical_elements_gwid,6,3)  where clinical_elements_gwid in(");
		String gwid=gwidList.toString();
		gwid=gwid.replace("[","");
		gwid=gwid.replace("]","");
		String afterSplit[]=gwid.split(", ");
		gwid="";
		for(int i=0;i<afterSplit.length-1;i++){
			gwid=gwid+afterSplit[i]+",";
		}
		gwid=gwid+afterSplit[afterSplit.length-1];
		
		String collectBufferData ="'"+gwid.replaceAll(",", "','")+"'";
		querryBuffer.append(collectBufferData+")");
		querryBuffer.append("order by clinical_elements_gwid");
		
		List<Object> list=em.createNativeQuery(querryBuffer.toString()).getResultList();
		JSONArray returnList= new JSONArray();
		returnList.put(list);
		/*for(int i=0; i<list.size(); i++){
			Object[] obj= list.get(i);
			JSONObject returnObj= new JSONObject();
			returnObj.put("clinical_elements_gwid", obj[0].toString());
			returnObj.put("clinical_elements_name", obj[1].toString());
			returnObj.put("plan_type_name", obj[2].toString());
			returnObj.put("clinical_elements_datatype", obj[3].toString());
			returnObj.put("value", obj[4].toString());
			returnObj.put("optionname", obj[5].toString());
			returnList.put(returnObj);
		}*/
		JSONObject returnObj= new JSONObject();
		returnObj.put("valueArr", returnList);
		returnObj.put("elementArr", returnJSON);
		return returnObj;
	}
}