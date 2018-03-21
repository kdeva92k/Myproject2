package com.glenwood.glaceemr.server.application.services.chart.ros;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalSystem;
import com.glenwood.glaceemr.server.application.models.ClinicalSystemOrder;
import com.glenwood.glaceemr.server.application.models.ClinicalSystemOrder_;
import com.glenwood.glaceemr.server.application.models.ClinicalSystem_;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.RosElement;
import com.glenwood.glaceemr.server.application.models.RosElement_;
import com.glenwood.glaceemr.server.application.models.SoapElementDatalist;
import com.glenwood.glaceemr.server.application.repositories.ClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.ClinicalSystemRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterEntityRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.ROSElementRepository;
import com.glenwood.glaceemr.server.application.repositories.ShortcutsRepository;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalConstants;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalDataBean;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalElementBean;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalElementsService;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.PatientDetailsBean;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.PatientElementBean;
import com.glenwood.glaceemr.server.application.specifications.PatientClinicalElementsSpecification;
import com.glenwood.glaceemr.server.application.specifications.ShortcutsSpecification;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

@Service
public class ROSServiceImpl implements ROSService {

	@Autowired
	ClinicalSystemRepository clinicalSystemRepository;

	@Autowired
	ROSElementRepository rosElementRepository;

	@Autowired
	ClinicalElementsRepository clinicalElementsRepository;

	@Autowired
	ClinicalElementsService clinicalElementService;

	@Autowired
	ClinicalDataBean clinicalDataBean;

	@Autowired
	PatientClinicalElementsRepository patientClinicalElementsRepository;

	@Autowired
	EncounterEntityRepository encounterRepository;

	@Autowired
	ROSBean rosBean;

	@Autowired
	PatientDetailsBean patientDetailsBean;
	
	@Autowired
	ShortcutsRepository shortcutsRepository;
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<ROSSystemBean> getROSElements(String clientId,Integer patientId, Integer chartId,Integer encounterId, Integer templateId, Integer tabId) {
		clearClinicalData();
		getROSElements(templateId);
		setClinicalDataBean(patientId,encounterId,templateId,3,clientId);
		return frameResponseBean(tabId);
	}

	private List<ROSSystemBean> frameResponseBean(Integer tabId) {
		List<ROSSystemBean> responseROSBean=new ArrayList<ROSSystemBean>();
		LinkedHashMap<Integer,ROSSystemBean> rosHashMap = rosBean.getROS();
		Set<Entry<Integer,ROSSystemBean>> systemHashMap = rosHashMap.entrySet();
		int systemId = -1;

		for(Entry<Integer,ROSSystemBean> currentSystem:systemHashMap){
			systemId = currentSystem.getKey();
			
			ROSSystemBean  rosSystemBean = currentSystem.getValue();
			String elementGWId = "0000000000000000000";
			List<ROSElementBean> roselements=rosSystemBean.getROSElements();
			
			List<ROSElementBean> systemElementsList=new ArrayList<ROSElementBean>();
			for(int i=0;i<roselements.size();i++){
				ROSElementBean rosElementBean = roselements.get(i);
				String elementName      = rosElementBean.getElementName().replace("!@@!", "\\");
				
				String elementPrintText = rosElementBean.getElementPrintText().replace("!@@!", "\\");
				elementGWId=rosElementBean.getElementGWID();
				ClinicalElementBean ceBean = clinicalDataBean.getClinicalElements(elementGWId);
				PatientElementBean patientElementBean=clinicalDataBean.getPatientElements(elementGWId);

				if(ceBean!=null){
					
					if(patientElementBean!=null){
						if(ceBean.getClinicalElementDataType() == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN){
							String associatedGWId=rosElementBean.getAssociatedGWID();
							PatientElementBean patientElementAssociatedBean=clinicalDataBean.getPatientElements(associatedGWId);
							ROSElementAssociateBean associateBean=getROSElementAssociateBean("", associatedGWId, "", "2", patientElementAssociatedBean != null ? patientElementAssociatedBean.getPatientClinicalElementText() : "", getSoapElementDataList(tabId, "detailoption_"+associatedGWId+"_text"));
							systemElementsList.add(getROSElementBean(elementName,elementPrintText,elementGWId,ceBean.getClinicalElementDataType(),patientElementBean.getPatientClinicalElementBoolean().toString(),associateBean));
						}else{
							if(!elementName.equalsIgnoreCase("Element Notes")){
								ROSElementAssociateBean associateBean=getROSElementAssociateBean(elementName, elementGWId, elementPrintText, ceBean.getClinicalElementDataType()+"", patientElementBean != null ? patientElementBean.getPatientClinicalElementText() : "", getSoapElementDataList(tabId, "element_"+elementGWId));
								systemElementsList.add(getROSElementBean(elementName,elementPrintText,elementGWId,ceBean.getClinicalElementDataType(),patientElementBean.getPatientClinicalElementText().toString(),associateBean));
							}
						}
					}else{
						if(ceBean.getClinicalElementDataType() == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN){
							String associatedGWId=rosElementBean.getAssociatedGWID();
							PatientElementBean patientElementAssociatedBean=clinicalDataBean.getPatientElements(associatedGWId);
							ROSElementAssociateBean associateBean=getROSElementAssociateBean("", associatedGWId, "", "2", patientElementAssociatedBean != null ? patientElementAssociatedBean.getPatientClinicalElementText() : "", getSoapElementDataList(tabId, "detailoption_"+associatedGWId+"_text"));
							systemElementsList.add(getROSElementBean(elementName,elementPrintText,elementGWId,ceBean.getClinicalElementDataType(),"",associateBean));
						}else if(ceBean.getClinicalElementDataType() == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT){
							if(!elementName.equalsIgnoreCase("Element Notes")){
								ROSElementAssociateBean associateBean=getROSElementAssociateBean(elementName, elementGWId, elementPrintText, ceBean.getClinicalElementDataType()+"", patientElementBean != null ? patientElementBean.getPatientClinicalElementText() : "", getSoapElementDataList(tabId, "element_"+elementGWId));
								systemElementsList.add(getROSElementBean(elementName,elementPrintText,elementGWId,ceBean.getClinicalElementDataType(),"",associateBean));
							}
						}
						else{
							systemElementsList.add(getROSElementBean(elementName,elementPrintText,elementGWId,ceBean.getClinicalElementDataType(),"",null));
						}
					}
				}
			}
			
			ROSSystemBean rosSystem=new ROSSystemBean();
			if(systemElementsList.size()>0){
				rosSystem.setSytemId(systemId);
				rosSystem.setSystemName((rosSystemBean.getSystemName()).replace("!@@!", "\\"));
				rosSystem.setEandMType(rosSystemBean.getEandMType());
				rosSystem.setDeferredGWID(rosSystemBean.getDeferredGWID());
				rosSystem.setROSElements(systemElementsList);
				responseROSBean.add(rosSystem);
				
			}


		}
		return responseROSBean;
	}


	public ROSElementBean getROSElementBean(String elementName,String elementPrintText,String elementGwid,Integer dataType,String value, ROSElementAssociateBean associateBean){
		ROSElementBean elementBean=new ROSElementBean();
		elementBean.setElementName(elementName);
		elementBean.setElementGWID(elementGwid);
		elementBean.setElementPrintText(elementPrintText);
		elementBean.setDataType(dataType);
		elementBean.setValue(value);
		elementBean.setAssociateElement(associateBean);
		return elementBean;
	}




	public  void getROSElements(Integer templateId) {
//		List<ClinicalSystem> rosSystems=clinicalSystemRepository.findAll(ROSSpecification.getActiveROSSystems(templateId));
		List<ROSSystemBean> rosSystems= getActiveROSSystems(templateId);
		List<String> activeRosSystemIds = new ArrayList<String>();
		activeRosSystemIds.add("-1");
		for(int i=0; i<rosSystems.size(); i++){
			ROSSystemBean rosSystemBean=new ROSSystemBean();
			if(rosSystems.get(i)!=null){
			rosSystemBean.setSytemId(Integer.parseInt(HUtil.Nz(rosSystems.get(i).getSystemId(),"-1")));
			rosSystemBean.setSystemName(HUtil.Nz(rosSystems.get(i).getSystemName(),"-1"));
			rosSystemBean.setEandMType(HUtil.Nz(rosSystems.get(i).getEandMType(),"-1"));
			rosSystemBean.setDeferredGWID(HUtil.Nz(rosSystems.get(i).getDeferredGWID(),""));
			activeRosSystemIds.add(rosSystemBean.getSystemId()+"");
			rosBean.getROS().put(rosSystemBean.getSystemId(), rosSystemBean);
		}
		}
		getROSActiveElementBySystem(activeRosSystemIds);
	}


	private List<ROSSystemBean> getActiveROSSystems(Integer templateId) {

		CriteriaBuilder cb= em.getCriteriaBuilder();
		CriteriaQuery<ROSSystemBean> query= cb.createQuery(ROSSystemBean.class);
		Root<ClinicalSystem> root= query.from(ClinicalSystem.class);
		Join<ClinicalSystem,ClinicalSystemOrder> paramJoin=root.join(ClinicalSystem_.clinicalSystemOrders,JoinType.LEFT);	
		paramJoin.on(cb.equal(paramJoin.get(ClinicalSystemOrder_.clinicalSystemOrderTemplateid),templateId));
		
		query.select(cb.construct(ROSSystemBean.class, 
				root.get(ClinicalSystem_.clinicalSystemRosGwid),
				root.get(ClinicalSystem_.clinicalSystemName),
				root.get(ClinicalSystem_.clinicalSystemRosEandmtype),
				root.get(ClinicalSystem_.clinicalSystemRosDeferredGwid)));
		
		query.where(cb.equal(root.get(ClinicalSystem_.clinicalSystemIsactive),true),
				cb.isNotNull(root.get(ClinicalSystem_.clinicalSystemRosGwid)));
		
		query.orderBy(cb.asc(paramJoin.get(ClinicalSystemOrder_.clinicalSystemOrderRos)));
		
		return em.createQuery(query).getResultList();
	}

	public void getROSActiveElementBySystem(List<String> activeRosSystemIds){
//		List<RosElement> activeROSElements=rosElementRepository.findAll(ROSSpecification.getActiveROSElementBySystem(activeRosSystemIds));
		
		List<ROSElementBean> activeROSElements= getSystemElements(activeRosSystemIds);
		for(int j=0; j<activeROSElements.size(); j++){
			ROSElementBean rosElementBean = new ROSElementBean();
			ROSElementBean rosList = activeROSElements.get(j);
			rosElementBean.setElementGWID(HUtil.Nz(rosList.getElementGWID(),"0000000000000000000"));
			rosElementBean.setElementName(HUtil.Nz(rosList.getElementName(),"-1"));
			rosElementBean.setElementPrintText(HUtil.Nz(rosList.getElementPrintText(),"-1"));
			String associateGwid = "0000000000000000000";
			if(rosList.getAssociatedGWID() != null && !rosList.getAssociatedGWID().trim().isEmpty()){
					associateGwid = rosList.getAssociatedGWID(); 
			}
			rosElementBean.setAssociatedGWID(associateGwid);
			rosBean.getROS().get(rosList.getDataType()).getROSElements().add(rosElementBean);
		}
	}

	private List<ROSElementBean> getSystemElements(List<String> activeRosSystemIds) {
		CriteriaBuilder cb= em.getCriteriaBuilder();
		CriteriaQuery<ROSElementBean> query= cb.createQuery(ROSElementBean.class);
		Root<RosElement> root= query.from(RosElement.class);
		
		Join<RosElement, ClinicalTextMapping> textJoin= root.join(RosElement_.clinicalTextMapping, JoinType.LEFT);
		
		query.multiselect(root.get(RosElement_.rosElementGwid), 
				root.get(RosElement_.rosElementName),
				root.get(RosElement_.rosElementPrinttext),
				root.get(RosElement_.rosElementSystemId),
				textJoin.get(ClinicalTextMapping_.clinicalTextMappingTextboxGwid));
		
		query.where(root.get(RosElement_.rosElementSystemId).in(activeRosSystemIds),
					cb.equal(root.get(RosElement_.rosElementIsactive),true));
		
		query.orderBy(cb.asc(root.get(RosElement_.rosElementOrderby)));
		
		return em.createQuery(query).getResultList();
	}

	public List<ClinicalElementBean> setClinicalDataBean(Integer patientId,Integer encounterId,Integer templateId,Integer tabType,String clientId){
		clinicalDataBean.setClientId(clientId);
		return clinicalElementService.setClinicalDataBeans(patientId, encounterId, templateId, tabType,"01004%");
	}

	@Override
	public String getROSNotes(Integer patientId, Integer encounterId) {
		return getPatClinEleByGWID(encounterId,patientId,"0000100400000000001");
	}

	private String getPatClinEleByGWID(
			Integer encounterId, Integer patientId, String gwid) {
		
		CriteriaBuilder cb= em.getCriteriaBuilder();
		CriteriaQuery<String> query= cb.createQuery(String.class);
		Root<PatientClinicalElements> root= query.from(PatientClinicalElements.class);

		query.select(cb.coalesce(root.get(PatientClinicalElements_.patientClinicalElementsValue),""));
		
		query.where(cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId),
				cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId),
				cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsGwid),gwid));
	
		try{
			List<String> result= em.createQuery(query).getResultList();
			if(result != null && result.size()>0)
				return result.get(0);
			else
				return "";
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}

	public List<SoapElementDatalist> getSoapElementDataList(Integer tabId, String elementId) {
		tabId = Integer.parseInt(Optional.fromNullable(tabId+"").or("-1"));
		elementId = Optional.fromNullable(Strings.emptyToNull(elementId.toString())).or("");
		return shortcutsRepository.findAll(ShortcutsSpecification.fetchShortcuts(tabId, elementId.trim()));
	}
	
	private ROSElementAssociateBean getROSElementAssociateBean(String elementName, String elementGWID, String elementPrintText, String elementType, String elementValue,
			List<SoapElementDatalist> elementShortcuts) {
		
		ROSElementAssociateBean bean=new ROSElementAssociateBean();
		bean.setElementName(elementName);
		bean.setElementGWID(elementGWID);
		bean.setElementPrintText(elementPrintText);
		bean.setElementType(elementType);
		bean.setElementValue(elementValue);
		bean.setElementShortcuts(elementShortcuts);
		
		return bean;
	}
	
	/**
	 * Cleaning clinical data bean
	 */
	private void clearClinicalData() {
		try{
			clinicalDataBean.getPatientElements().clear();
			clinicalDataBean.getClinicalElements().clear();
			clinicalDataBean.getPatientEpisodeElements().clear();
			clinicalDataBean.getPatientHistoryElements().clear();
			clinicalDataBean.getElementsToDelete().clear();
			clinicalDataBean.getElementsToSave().clear();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
