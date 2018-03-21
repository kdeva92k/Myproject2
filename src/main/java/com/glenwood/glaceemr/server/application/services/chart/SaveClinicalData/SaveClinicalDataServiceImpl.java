package com.glenwood.glaceemr.server.application.services.chart.SaveClinicalData;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.EncounterPlan;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalHistory;
import com.glenwood.glaceemr.server.application.models.PatientEpisodeAttributeDetails;
import com.glenwood.glaceemr.server.application.repositories.ClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterEntityRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterPlanRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalHistoryRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientEpisodeAttributeDetailsRepository;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalConstants;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalDataBean;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalElementBean;
import com.glenwood.glaceemr.server.application.specifications.ClinicalElementsSpecification;
import com.glenwood.glaceemr.server.application.specifications.EncounterEntitySpecification;
import com.glenwood.glaceemr.server.application.specifications.EncounterSpecification;
import com.glenwood.glaceemr.server.application.specifications.PatientClinicalElementsSpecification;
import com.glenwood.glaceemr.server.utils.HUtil;



@Component
public class SaveClinicalDataServiceImpl implements SaveClinicalDataService{


	@Autowired
	ClinicalDataBean clinicalDataBean;

	@Autowired
	ClinicalElementsRepository clinicalElementsRepository;

	@Autowired
	PatientClinicalHistoryRepository patientClinicalHistoryRepository;

	@Autowired
	PatientEpisodeAttributeDetailsRepository patientEpisodeAttributeDetailsRepository;

	@Autowired
	PatientClinicalElementsRepository patientClinicalElementsRepository;

	@Autowired
	EncounterEntityRepository encounterEntityRepository;

	@Autowired
	EncounterPlanRepository encounterPlanRepository;

	Integer episodeId=-1;
	
	List<String> gwids=new ArrayList<String>();

	@Override
	public void saveData(Integer patientId, Integer encounterId,Integer chartId, Integer templateId, String patElementsJSON) throws Exception {
		loadClinicalDataBean(patientId,encounterId,chartId,templateId,patElementsJSON);
		// delete the data in ElementsToDeleteBean

		deletePatientClinicalData(patientId,chartId,encounterId);
		deletePatientHistoryData(patientId);
		deletePatientEpisodeData(patientId,episodeId);

		// save the data in ElementsToSaveBean
		SaveDataClincialData(encounterId,patientId,chartId);

	}

	private void SaveDataClincialData(Integer encounterId,Integer patientId,Integer chartId) {
		Set<Entry<String,ElementsToSaveBean>> hashResultSet=clinicalDataBean.getElementsToSave().entrySet();
		boolean isPatientElementExist;
		boolean isPatientHistoryElementExist;
		boolean isPatientEpisodeElementExist;
		for(Entry<String,ElementsToSaveBean> entrySet:hashResultSet){
			String clinicalElementGWId=entrySet.getKey();
			String clinicalElementId=clinicalElementGWId;
			
			isPatientElementExist=false;
			if(clinicalDataBean.getPatientElements().get(clinicalElementId)!=null){
				isPatientElementExist=true;
			}
			if(clinicalElementId.indexOf("_")!=-1){
				clinicalElementId=clinicalElementId.split("_")[0];
			}
			ElementsToSaveBean elementsToSaveBean=null;
			ClinicalElementBean clinicalElementBean=clinicalDataBean.getClinicalElements().get(clinicalElementId);
			elementsToSaveBean=entrySet.getValue();
			if(elementsToSaveBean!=null){
				if(!(clinicalElementId.substring(3, 8).equalsIgnoreCase("01003")&& encounterId==-1)){
					if(!isPatientElementExist){
						patientClinicalElementsRepository.save(setInsertStmtValues(elementsToSaveBean,patientId,chartId,encounterId,clinicalElementId));
					}else{
						if(elementsToSaveBean.getElemenetsToSaveType()!=ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION){
							List<PatientClinicalElements> patClinicalElem=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinEleByGWID(encounterId,patientId, clinicalElementId));
							if(patClinicalElem.size()>0){
							for (PatientClinicalElements patientClinicalElements : patClinicalElem) {
								patientClinicalElements.setPatientClinicalElementsValue(elementsToSaveBean.getValueToSave());
								patientClinicalElementsRepository.save(patientClinicalElements);
							}	}
							else{
								patientClinicalElementsRepository.save(setInsertStmtValues(elementsToSaveBean,patientId,chartId,encounterId,clinicalElementId));
							}

						}
					}
				}
			}
			if(clinicalElementGWId.equals("0000400000000000001"))
			{
				List<EncounterPlan> encPlan=null;
				if((encPlan=encounterPlanRepository.findAll(EncounterSpecification.getEncounterPlanByEncId(encounterId))).size()>0){
					for (EncounterPlan encounterPlan : encPlan) {
						encounterPlan.setPlantext(elementsToSaveBean.getValueToSave().replaceAll("'", "''"));
						encounterPlanRepository.saveAndFlush(encounterPlan);
					}
				}
				else
				{
					EncounterPlan encounterPlan=new EncounterPlan();
					encounterPlan.setEncounterid(encounterId);
					encounterPlan.setPlantext(elementsToSaveBean.getValueToSave().replaceAll("'", "''"));
					encounterPlanRepository.save(encounterPlan);
				}
				}
			else if(clinicalElementGWId.equals("0000100100000000000"))
			{
				List<Encounter> encounters=encounterEntityRepository.findAll(EncounterEntitySpecification.getEncounterByEncId(encounterId));
				for (Encounter encounterEntity : encounters) {
					encounterEntity.setEncounterChiefcomp(elementsToSaveBean.getValueToSave().replaceAll("'", "''"));
					encounterEntityRepository.saveAndFlush(encounterEntity);
				}
			}
			if(clinicalElementBean!=null &&  clinicalElementBean.getClinicalElementIsHistory()){
				isPatientHistoryElementExist=false;
				clinicalElementId=clinicalElementGWId;
				if(clinicalDataBean.getPatientHistoryElements().get(clinicalElementId)!=null)
					isPatientHistoryElementExist=true;
				if(clinicalElementId.indexOf("_")!=-1){
					clinicalElementId=clinicalElementId.split("_")[0];
				}
				if(!isPatientHistoryElementExist){
					clinicalElementBean=clinicalDataBean.getClinicalElements().get(clinicalElementId);
					if(clinicalElementBean!=null){
						PatientClinicalHistory patientClinicalHis=new PatientClinicalHistory();
						patientClinicalHis.setPatientClinicalHistoryPatientid(patientId);
						patientClinicalHis.setPatientClinicalHistoryChartid(chartId);
						patientClinicalHis.setPatientClinicalHistoryGwid(clinicalElementId);
						patientClinicalHis.setPatientClinicalHistoryValue(elementsToSaveBean.getValueToSave());
						patientClinicalHistoryRepository.save(patientClinicalHis);
					}
				}else{
					clinicalElementBean=clinicalDataBean.getClinicalElements().get(clinicalElementId);
					if(clinicalElementBean!=null){
						List<PatientClinicalHistory> patClinicalHistory=patientClinicalHistoryRepository.findAll(PatientClinicalElementsSpecification.getPatClinHistoryEleByGWID(patientId, clinicalElementId));
						for (PatientClinicalHistory patientClinicalHistory : patClinicalHistory) {
							patientClinicalHistory.setPatientClinicalHistoryValue(elementsToSaveBean.getValueToSave());
							patientClinicalHistoryRepository.saveAndFlush(patientClinicalHistory);
						}
					}
				}
				
			}else if(clinicalElementBean!=null &&  clinicalElementBean.getClinicalElementIsEpisode()){
				isPatientEpisodeElementExist=false;
				clinicalElementId=clinicalElementGWId;
				if(clinicalDataBean.getPatientEpisodeElements().get(clinicalElementId)!=null)
					isPatientEpisodeElementExist=true;
				if(clinicalElementId.indexOf("_")!=-1){
					clinicalElementId=clinicalElementId.split("_")[0];
				}

				if(!isPatientEpisodeElementExist){
					clinicalElementBean=clinicalDataBean.getClinicalElements().get(clinicalElementId);
					if(clinicalElementBean!=null){
						PatientEpisodeAttributeDetails patientEpisodeAttrDetails=new PatientEpisodeAttributeDetails();
						patientEpisodeAttrDetails.setPatientEpisodeAttributeDetailsEpisodeid(episodeId);
						patientEpisodeAttrDetails.setPatientEpisodeAttributeDetailsGwid(clinicalElementId);
						patientEpisodeAttrDetails.setPatientEpisodeAttributeDetailsValue(elementsToSaveBean.getValueToSave());
						patientEpisodeAttributeDetailsRepository.save(patientEpisodeAttrDetails);
					}
				}else{
					clinicalElementBean=clinicalDataBean.getClinicalElements().get(clinicalElementId);
					if(clinicalElementBean!=null){
						List<PatientEpisodeAttributeDetails> patientEpiAttrDetails=patientEpisodeAttributeDetailsRepository.findAll(PatientClinicalElementsSpecification.getPatEpisodeEleByGWID(episodeId, clinicalElementId));
						for (PatientEpisodeAttributeDetails patientEpisodeAttributeDetails : patientEpiAttrDetails) {
							patientEpisodeAttributeDetails.setPatientEpisodeAttributeDetailsValue(elementsToSaveBean.getValueToSave());
							patientEpisodeAttributeDetailsRepository.save(patientEpisodeAttributeDetails);
						}
					}
				}
			}
		}
	}

	private PatientClinicalElements setInsertStmtValues(ElementsToSaveBean elementsToSaveBean,Integer patientId, Integer chartId, Integer encounterId,String clinicalElementId) {
		PatientClinicalElements patientClinicalElements=new PatientClinicalElements();
		patientClinicalElements.setPatientClinicalElementsPatientid(patientId);
		patientClinicalElements.setPatientClinicalElementsChartid(chartId);
		patientClinicalElements.setPatientClinicalElementsEncounterid(encounterId);
		patientClinicalElements.setPatientClinicalElementsGwid(clinicalElementId);
		patientClinicalElements.setPatientClinicalElementsValue(elementsToSaveBean.getValueToSave());
		return patientClinicalElements;

	}



	private void loadClinicalDataBean(Integer patientId, Integer encounterId,Integer chartId, Integer templateId, String patElementsJSON) throws Exception {
		JSONObject patientElementsJSON = new JSONObject(patElementsJSON);
		LinkedHashMap <String,ElementsToSaveBean> elementsToSave = new LinkedHashMap<String,ElementsToSaveBean>();
		LinkedHashMap <String,ElementsToDeleteBean> elementsToDelete = new LinkedHashMap<String,ElementsToDeleteBean>();
		JSONArray elementsToSaveJSONArray = patientElementsJSON.getJSONArray("ElementsToSave");
		JSONArray ElementsToDeleteJSONArray = patientElementsJSON.getJSONArray("ElementsToDelete");
		List<String> clinicalElementGroup=new ArrayList<String>();
		for (int i = 0; i < elementsToSaveJSONArray.length();i++) {
			JSONObject elementsToSaveJSONObject = elementsToSaveJSONArray.getJSONObject(i);
			ElementsToSaveBean obj=new ElementsToSaveBean();
			obj.setElemenetsToSaveType(elementsToSaveJSONObject.getInt("elemenetsToSaveType"));
			obj.setValueToSave(URLDecoder.decode(elementsToSaveJSONObject.getString("valueToSave"),"UTF-8"));
			clinicalElementGroup.add(elementsToSaveJSONObject.getString("clinicalelementgwid"));
			elementsToSave.put(elementsToSaveJSONObject.getString("clinicalelementgwid"), obj);
		}
		for (int i = 0; i < ElementsToDeleteJSONArray.length();i++) {
			JSONObject elementsToDeleteJSONObject = ElementsToDeleteJSONArray.getJSONObject(i);
			ElementsToDeleteBean obj=new ElementsToDeleteBean();
			obj.setElemenetsToDeleteType(elementsToDeleteJSONObject.getInt("elemenetsToDeleteType"));
			obj.setValueToDelete(URLDecoder.decode(elementsToDeleteJSONObject.getString("valueToDelete"),"UTF-8"));
			clinicalElementGroup.add(elementsToDeleteJSONObject.getString("clinicalelementgwid"));
			elementsToDelete.put(elementsToDeleteJSONObject.getString("clinicalelementgwid"), obj);				
		}
		clinicalElementGroup.add("-1");
		
		
		//remove "_optionvalue" from GWID 
		
		for (int i=0;i<clinicalElementGroup.size();i++) {
			String gwid=clinicalElementGroup.get(i);
			if(gwid.contains("_")){
				gwid=gwid.substring(0, gwid.indexOf("_"));
			}
			
			gwids.add(gwid);
		}
		clinicalDataBean.getElementsToSave().clear();
		clinicalDataBean.getElementsToSave().putAll(elementsToSave);
		clinicalDataBean.getElementsToDelete().putAll(elementsToDelete);
		loadClinicalElementsDetailsToSave(patientId,encounterId);
	}

	private void loadClinicalElementsDetailsToSave(Integer patientId,Integer encounterId) {
		clinicalDataBean.setClinicalData(clinicalElementsRepository.findAll(ClinicalElementsSpecification.getClinicalElements(gwids)));
		clinicalDataBean.setPatientBeanToSave(patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getEncPatientClinicalData(encounterId)));
		clinicalDataBean.setPatientHistoryData(patientClinicalHistoryRepository.findAll(PatientClinicalElementsSpecification.getPatClinicalHistoryData(patientId)));
		List<Encounter> encounter=null;
		if((encounter=encounterEntityRepository.findAll(EncounterEntitySpecification.getEncounterByEncId(encounterId))).size()>0){
			episodeId=encounter.get(0).getEncounterPatientEpisodeid();
			clinicalDataBean.setPatientEpisodeElementsToSave(patientEpisodeAttributeDetailsRepository.findAll(PatientClinicalElementsSpecification.getPatientEpisodeElements(encounterId,episodeId)));
		}
	}	


	/**
	 * Deletes the patient history data  in {@link ElementsToDeleteBean} 
	 * @param patientId
	 * @throws Exception
	 */
	private void deletePatientHistoryData(Integer patientId)throws Exception{
		List<PatientClinicalHistory> patientClincialHistoryElements=new ArrayList<PatientClinicalHistory>();
		Set<Entry<String, ElementsToDeleteBean>> hashResultSet=clinicalDataBean.getElementsToDelete().entrySet();
		for(Entry<String, ElementsToDeleteBean> entrySet:hashResultSet){
			String clinicalElementId=entrySet.getKey();

			if(clinicalDataBean.getElementsToSave().get(clinicalElementId)==null){
				if(clinicalDataBean.getPatientHistoryElements().get(clinicalElementId)!=null){
					patientClincialHistoryElements.addAll(setHistoryDeleteStmtValue(patientId,clinicalElementId));
				}
			}
		}
		patientClinicalHistoryRepository.deleteInBatch(patientClincialHistoryElements);
	}


	private  List<PatientClinicalHistory> setHistoryDeleteStmtValue(Integer patientId,String clinicalElementId) throws Exception{
		String patientClinicalElementId=clinicalElementId;
		if(clinicalElementId.indexOf("_")!=-1){
			clinicalElementId=clinicalElementId.split("_")[0];
		}
		int elementDataType=-1;
		if(clinicalDataBean.getClinicalElements().get(clinicalElementId)!=null)
			elementDataType=Integer.parseInt(HUtil.Nz(clinicalDataBean.getClinicalElements().get(clinicalElementId).getClinicalElementDataType(), "-1"));
		String valueToDelete="-1";
		if(elementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION || elementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION)
			valueToDelete=clinicalDataBean.getPatientHistoryElements().get(patientClinicalElementId).getpatientHistoryElementOption()+"";
		else if (elementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT)
			valueToDelete=clinicalDataBean.getPatientHistoryElements().get(patientClinicalElementId).getpatientHistoryElementText();
		else if (elementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN)
			valueToDelete=clinicalDataBean.getPatientHistoryElements().get(patientClinicalElementId).getpatientHistoryElementBoolean().toString();
		else if (elementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_NUMBER)
			valueToDelete=clinicalDataBean.getPatientHistoryElements().get(patientClinicalElementId).getpatientHistoryElementNumber()+"";

		return patientClinicalHistoryRepository.findAll(PatientClinicalElementsSpecification.getPatClinHistoryEleByGWIDAndValue(patientId, clinicalElementId, valueToDelete));

	}





	/**
	 * Deletes the patient Clinical elements in  {@link ElementsToDeleteBean}
	 * 
	 * @param patientId
	 * @param chartId
	 * @param encounterId
	 */
	private void deletePatientClinicalData(Integer patientId, Integer chartId,Integer encounterId) {
		Set<Entry<String, ElementsToDeleteBean>> hashResultSet=clinicalDataBean.getElementsToDelete().entrySet();
		List<PatientClinicalElements> patClinicalElemToDelete=new ArrayList<PatientClinicalElements>();
			for(Entry<String, ElementsToDeleteBean> entrySet:hashResultSet){
			String clinicalElementId=entrySet.getKey();
			if(clinicalDataBean.getElementsToSave().get(clinicalElementId)==null){
				if(clinicalDataBean.getPatientElements().get(clinicalElementId)!=null){
					if(!(clinicalElementId.substring(3, 8).equalsIgnoreCase("01003")&&encounterId==-1)){
						String patientClinicalElementId=clinicalElementId;
						if(clinicalElementId.indexOf("_")!=-1){
							clinicalElementId=clinicalElementId.split("_")[0];
						}
						String valueToDelete="-1";
						valueToDelete=clinicalDataBean.getPatientElements().get(patientClinicalElementId).getPatientClinicalElementText();
						patClinicalElemToDelete.addAll(patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinEleByGWIDAndValue(encounterId, clinicalElementId,valueToDelete)));
					}
					if(clinicalElementId.equals("0000400000000000001")){
						List<EncounterPlan> encounterPlan=null;
						if((encounterPlan=encounterPlanRepository.findAll(EncounterSpecification.getEncounterPlanByEncId(encounterId))).size()>0){
							encounterPlan.get(0).setPlantext("");
						}
						encounterPlanRepository.saveAndFlush(encounterPlan.get(0));
					}
					else if(clinicalElementId.equals("0000100100000000000")){
						List<Encounter> encounter=null;
						if((encounter=encounterEntityRepository.findAll(EncounterEntitySpecification.getEncounterByEncId(encounterId))).size()>0){
							encounter.get(0).setEncounterChiefcomp("");
						}
						encounterEntityRepository.saveAndFlush(encounter.get(0));
					}
				}
			}
		}
		patientClinicalElementsRepository.deleteInBatch(patClinicalElemToDelete);
		
	}




	/**
	 * 
	 * Deletes the Patient Episode Data in  {@link ElementsToDeleteBean} 
	 * 
	 * @param patientId
	 * @param episodeId
	 * @throws Exception
	 */
	public void deletePatientEpisodeData(Integer patientId,Integer episodeId)throws Exception{
		Set<Entry<String, ElementsToDeleteBean>> hashResultSet=clinicalDataBean.getElementsToDelete().entrySet();
		List<PatientEpisodeAttributeDetails> patientEpisodeAttributeDet=new ArrayList<PatientEpisodeAttributeDetails>();
		for(Entry<String, ElementsToDeleteBean> entrySet:hashResultSet){
			String clinicalElementId=entrySet.getKey();
			if(clinicalDataBean.getElementsToSave().get(clinicalElementId)==null){
				if(clinicalDataBean.getPatientEpisodeElements().get(clinicalElementId)!=null){
					patientEpisodeAttributeDet.addAll(setEpisodeDeleteValue(patientId,clinicalElementId,episodeId));
				}
			}
		}
		patientEpisodeAttributeDetailsRepository.deleteInBatch(patientEpisodeAttributeDet);
	}


	public List<PatientEpisodeAttributeDetails> setEpisodeDeleteValue(Integer patientId,String clinicalElementId,Integer episodeId) throws Exception{
		String patientClinicalElementId=clinicalElementId;
		if(clinicalElementId.indexOf("_")!=-1){
			clinicalElementId=clinicalElementId.split("_")[0];
		}
		int elementDataType=-1;
		if(clinicalDataBean.getClinicalElements().get(clinicalElementId)!=null)
			elementDataType=Integer.parseInt(HUtil.Nz(clinicalDataBean.getClinicalElements().get(clinicalElementId).getClinicalElementDataType(), "-1"));
		String valueToDelete="-1";
		if(elementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION || elementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION)
			valueToDelete=clinicalDataBean.getPatientEpisodeElements().get(patientClinicalElementId).getPatientEpisodeElementOption()+"";
		else if (elementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT)
			valueToDelete=clinicalDataBean.getPatientEpisodeElements().get(patientClinicalElementId).getPatientEpisodeElementText();
		else if (elementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN)
			valueToDelete=clinicalDataBean.getPatientEpisodeElements().get(patientClinicalElementId).getPatientEpisodeElementBoolean().toString();
		else if (elementDataType==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_NUMBER)
			valueToDelete=clinicalDataBean.getPatientEpisodeElements().get(patientClinicalElementId).getPatientEpisodeElementNumber()+"";

		return patientEpisodeAttributeDetailsRepository.findAll(PatientClinicalElementsSpecification.getPatEpisodeEleByGWIDAndValue(episodeId, clinicalElementId, valueToDelete));
	}
}
