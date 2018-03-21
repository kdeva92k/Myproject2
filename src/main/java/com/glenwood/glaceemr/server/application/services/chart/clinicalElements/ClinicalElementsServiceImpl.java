package com.glenwood.glaceemr.server.application.services.chart.clinicalElements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.ClinicalElementTemplateMapping;
import com.glenwood.glaceemr.server.application.models.ClinicalElementTemplateMapping_;
import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsCondition;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsCondition_;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions_;
import com.glenwood.glaceemr.server.application.models.ClinicalElements_;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.EncounterPlan;
import com.glenwood.glaceemr.server.application.models.LeafLibrary;
import com.glenwood.glaceemr.server.application.models.LeafLibrary_;
import com.glenwood.glaceemr.server.application.models.LeafPatient;
import com.glenwood.glaceemr.server.application.models.LeafPatient_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalHistory;
import com.glenwood.glaceemr.server.application.models.PatientClinicalHistory_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.cnm.history.PatientClinicalElementsBean;
import com.glenwood.glaceemr.server.application.repositories.ClinicalElementTemplateMappingRepository;
import com.glenwood.glaceemr.server.application.repositories.ClinicalElementsOptionsRepository;
import com.glenwood.glaceemr.server.application.repositories.ClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.ClinicalTextMappingRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterEntityRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterPlanRepository;
import com.glenwood.glaceemr.server.application.repositories.LeafLibraryRepository;
import com.glenwood.glaceemr.server.application.repositories.LeafPatientRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalHistoryRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientVitalsRepository;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.chart.HPI.ClinicalElementsOptionBean;
import com.glenwood.glaceemr.server.application.specifications.ClinicalElementsSpecification;
import com.glenwood.glaceemr.server.application.specifications.EncounterEntitySpecification;
import com.glenwood.glaceemr.server.application.specifications.EncounterSpecification;
import com.glenwood.glaceemr.server.application.specifications.LeafLibrarySpecification;
import com.glenwood.glaceemr.server.application.specifications.LeafPatientSpecfication;
import com.glenwood.glaceemr.server.application.specifications.PatientClinicalElementsSpecification;
import com.glenwood.glaceemr.server.utils.DateUtil;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.glenwood.glaceemr.server.utils.SessionMap;


@Service
public class ClinicalElementsServiceImpl implements ClinicalElementsService{

	
	@Autowired
	ClinicalElementsRepository clinicalElementsRepository;

	@Autowired
	PatientClinicalElementsRepository patientClinicalElementsRepository;

	@Autowired
	PatientClinicalHistoryRepository patientClinicalHistoryRepository;

	@Autowired
	EncounterEntityRepository encounterEntityRepository;

	@Autowired
	ClinicalTextMappingRepository clinicalTextMappingRepository;

	@Autowired
	ClinicalElementsOptionsRepository clinicalElementsOptionsRepository;

	@Autowired 
	PatientRegistrationRepository patientRegRepository;

	@Autowired
	ClinicalDataBean clinicalDataBean;

	@Autowired
	LeafLibraryRepository leafLibraryRepository;

	@Autowired
	LeafPatientRepository leafPatientRepository;

	@Autowired
	ClinicalElementTemplateMappingRepository clinicalElementTemplateMappingRepository;

	@Autowired
	EncounterPlanRepository encounterPlanRepository;
	
	@Autowired
	PatientVitalsRepository patientVitalsRepository;

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;
		
	Short patientSex=0;
	Date patDOB=new Date();
	Integer ageinDay=0;
	
	
	
	@Override
	public List<ClinicalElementsOptions> getClinicalElementOptions(String gwid) {
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "Clinical element options viewed", sessionMap.getUserID(), request.getRemoteAddr(), -1, "gwid="+gwid, LogUserType.USER_LOGIN, "", "");
		return clinicalElementsOptionsRepository.findAll(ClinicalElementsSpecification.getclinicalElementOptions(gwid));

	}

	@Override
	public ClinicalDataBean setVitalsClinicalData(String gwidPattern,Integer patientId, Integer encounterId, Boolean isDischargeVitals,Integer admssEpisode, short patientSex, Integer ageinDay) {
		clinicalDataBean.setClinicalData(clinicalElementsRepository.findAll(Specifications.where(ClinicalElementsSpecification.getClinicalElementsByGWIDPattern(clinicalDataBean.clientId,gwidPattern,ageinDay, patientSex)).and(ClinicalElementsSpecification.defaultAgePed(-1)).or(ClinicalElementsSpecification.patientAgePed(ageinDay))));
		List<PatientClinicalElements> patientData=Collections.emptyList();
		List<PatientClinicalHistory> patientHistoryData=null;
		if(!isDischargeVitals.booleanValue()){
			List<Encounter> enc= encounterEntityRepository.findAll(EncounterEntitySpecification.getPrevEncHavingData(patientId,encounterId));
			if(enc.size()>0 && enc.get(0)!=null){
				if(patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinicalDataByGWIDPattern(clinicalDataBean.clientId,patientId,encounterId,gwidPattern)).size()>0){
					patientData=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getNonHistoryElemPatientData(clinicalDataBean.clientId,patientId,encounterId,gwidPattern));
				}
				else{
					patientData=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPrevEncounterPatVitalData(clinicalDataBean.clientId,patientId, enc.get(0).getEncounterId(), gwidPattern));
				}
			}else{
				patientData=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getNonHistoryElemPatientData(clinicalDataBean.clientId,patientId,encounterId,gwidPattern));
			}
			clinicalDataBean.setPatientClinicalData(patientData);
			patientHistoryData=patientClinicalHistoryRepository.findAll(PatientClinicalElementsSpecification.getHistoryElemPatientData(clinicalDataBean.clientId,patientId, gwidPattern));
			clinicalDataBean.setHistoryDatatoPatElementBean(patientHistoryData);
		}else{
			/*System.out.println("YES DISCHARGE VITALS");
			List<PatientVitals> patientVitals = patientVitalsRepository.findAll(DischargeVitalSpecification.getDichargeVitalsByEncounterId(patientId, encounterId, admssEpisode));
			clinicalDataBean.setPatientVitalsClinicalData(patientVitals);*/
		}
		
		return clinicalDataBean;
		
	}
	
	
	public void setVitalsClinicalDataLoadWithLastVisit(String gwidPattern,Integer patientId, Integer encounterId, Boolean isDischargeVitals,Integer admssEpisode, short patientSex, Integer ageinDay) {
		if(!isDischargeVitals.booleanValue()){
			List<Encounter> enc= encounterEntityRepository.findAll(EncounterEntitySpecification.getPrevEncHavingData(patientId,encounterId));
			List<PatientClinicalElements> patientData=Collections.emptyList();
			if(enc.size()>0 && enc.get(0)!=null){
				if(patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinicalDataByGWIDPattern(clinicalDataBean.clientId,patientId,encounterId,gwidPattern)).size()>0){
				}else{
					patientData=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPrevEncounterPatVitalData(clinicalDataBean.clientId,patientId, enc.get(0).getEncounterId(), gwidPattern));
				}
			}
			if(patientData.size()>0 && patientData.get(0)!=null){
				for(Integer i=0;i<patientData.size();i++){
					PatientClinicalElements patientClinicalElements=new PatientClinicalElements();
					patientClinicalElements.setPatientClinicalElementsPatientid(patientData.get(i).getPatientClinicalElementsPatientid());
					patientClinicalElements.setPatientClinicalElementsChartid(patientData.get(i).getPatientClinicalElementsChartid());
					patientClinicalElements.setPatientClinicalElementsEncounterid(encounterId);
					patientClinicalElements.setPatientClinicalElementsGwid(patientData.get(i).getPatientClinicalElementsGwid());
					patientClinicalElements.setPatientClinicalElementsValue(patientData.get(i).getPatientClinicalElementsValue());
					patientClinicalElementsRepository.save(patientClinicalElements);
				}
			}
		}
	}

	@Override
	public boolean isClinicalElemActive(String gwid){
		List<ClinicalElements> element=null;
		boolean isActive=false;

		if((element=clinicalElementsRepository.findAll(ClinicalElementsSpecification.getClinicalElement(gwid))).size()>0){
			isActive=Boolean.parseBoolean(HUtil.nz(element.get(0).getClinicalElementsIsactive(),false)+"");
		}
		return isActive;
	}

	@Override
	public ClinicalElements getClinicalElement(String gwid){
		List<ClinicalElements> element=null;
		if((element=clinicalElementsRepository.findAll(ClinicalElementsSpecification.getClinicalElement(gwid))).size()>0){
			return element.get(0);
		}else
			return null;
	}


	public void setClinicalDataBean(ClinicalDataBean clinicalDataBean) {
		this.clinicalDataBean = clinicalDataBean;
	}

	@Override
	public ClinicalTextMapping getClinicalTextMapping(String gwid) {
		List<ClinicalTextMapping> element=null;
		if((element=clinicalTextMappingRepository.findAll(ClinicalElementsSpecification.getClinicalTextMapping(gwid))).size()>0){
			return element.get(0);
		}else
			return null;
	}


	public void getPatientDetails(Integer patientId){
		/*List<PatientRegistration> patRegistration=patientRegRepository.findAll(PatientRegistrationSpecification.getPatientPersonalDetails(patientId));
		for (PatientRegistration patientRegistration : patRegistration) {
			patientSex=patientRegistration.getPatientRegistrationSex().shortValue();
			patDOB=patientRegistration.getPatientRegistrationDob();
		}
		SimpleDateFormat mdyFormat = new SimpleDateFormat("MM/dd/yyyy");
		ageinDay = (int)((DateUtil.dateDiff( DateUtil.DATE , DateUtil.getDate(mdyFormat.format(patDOB)) , new java.util.Date() )));*/
		
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
				SimpleDateFormat mdyFormat = new SimpleDateFormat("MM/dd/yyyy");
				ageinDay = (int)((DateUtil.dateDiff( DateUtil.DATE , DateUtil.getDate(mdyFormat.format(patDOB)) , new java.util.Date())));
			}
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "clinical element patient details viewed", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");

		}catch(Exception e){
			System.out.println("Exception while getting patient details for clinical elements");
			e.printStackTrace();
		}
	}
	
	public List<ClinicalElements> setClinicalDataBean(Integer patientId,Integer encounterId,Integer templateId,Integer tabType,String gwidPattern){

		getPatientDetails(patientId);
		
		List<LeafLibrary> leafDetails=leafLibraryRepository.findAll(LeafLibrarySpecification.getLeafDetailsByTemplateId(templateId));
		List<LeafPatient> leafPatient=leafPatientRepository.findAll(LeafPatientSpecfication.getPatLeafByLeafIdAndEncId(leafDetails.get(0)==null?-1:leafDetails.get(0).getLeafLibraryId(),encounterId));
		boolean isAgeBased=leafDetails.get(0).getLeafLibraryIsagebased()==null?false:leafDetails.get(0).getLeafLibraryIsagebased();

		/*
		 * Set clinical elements to ClinicalDataBean
		 * 
		 * */

		List<ClinicalElements> clinicalelements=clinicalElementsRepository.findAll(ClinicalElementsSpecification.getClinicalElements(templateId, patientSex, tabType,leafPatient.get(0).getLeafPatientCreatedDate()==null?"-1":leafPatient.get(0).getLeafPatientCreatedDate().toString(), ageinDay,isAgeBased));
		clinicalDataBean.setClinicalData(clinicalelements);
		if(encounterId==-1){
			clinicalelements=clinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinicalHistoryData(clinicalDataBean.clientId,isAgeBased, patientId, patientSex, gwidPattern, ageinDay));
		}else{
			clinicalelements=clinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getEncPatientClinicalData(clinicalDataBean.clientId,isAgeBased, encounterId, patientSex, gwidPattern, ageinDay));
		}
		clinicalDataBean.setClinicalData(clinicalelements);

		/*
		 * set Patient clinical elements and patient History elements to PatientElementBean of ClinicalDataBean 
		 * 
		 * */

		List<PatientClinicalElements> patientData=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getNonHistoryElemPatientData(clinicalDataBean.clientId,patientId,encounterId,gwidPattern));
		List<PatientClinicalHistory> patientHistoryData=patientClinicalHistoryRepository.findAll(PatientClinicalElementsSpecification.getHistoryElemPatientData(clinicalDataBean.clientId,patientId, gwidPattern));
		clinicalDataBean.setPatientClinicalData(patientData);
		clinicalDataBean.setHistoryDatatoPatElementBean(patientHistoryData);

		return clinicalelements;
	}

	public List<ClinicalElementBean> setClinicalDataBeans(Integer patientId,Integer encounterId,Integer templateId,Integer tabType,String gwidPattern){

		getPatientDetails(patientId);
		
		List<LeafLibrary> leafDetails=getLeafDetailsByTemplateId(templateId);
		String leafPatientCreatedDate=getPatLeafByLeafIdAndEncId(leafDetails.get(0)==null?-1:leafDetails.get(0).getLeafLibraryId(),encounterId);
		boolean isAgeBased=leafDetails.get(0).getLeafLibraryIsagebased()==null?false:leafDetails.get(0).getLeafLibraryIsagebased();

		List<ClinicalElementBean> clinicalelements=getClinicalElements(templateId, patientSex, tabType,leafPatientCreatedDate==null?"-1":leafPatientCreatedDate, ageinDay,isAgeBased);
		clinicalDataBean.setClinicalDataBean(clinicalelements);
		if(encounterId==-1){
			clinicalelements=getPatClinicalHistoryData(clinicalDataBean.clientId,isAgeBased, patientId, patientSex, gwidPattern, ageinDay);
		}else{
			clinicalelements=getEncPatientClinicalData(clinicalDataBean.clientId,isAgeBased, encounterId, patientSex, gwidPattern, ageinDay);
		}
		clinicalDataBean.setClinicalDataBean(clinicalelements);

		List<PatientClinicalElementsBean> patientData=getNonHistoryElemPatientData(clinicalDataBean.clientId,patientId,encounterId,gwidPattern);
		List<PatientClinicalElementsBean> patientHistoryData=getHistoryElemPatientData(clinicalDataBean.clientId,patientId, gwidPattern);
		clinicalDataBean.setPatientClinicalDataBean(patientData);
		clinicalDataBean.setHistoryDatatoPatElement(patientHistoryData);

		return clinicalelements;
	}




	private List<ClinicalElementBean> getEncPatientClinicalData(
			final String clientId, final boolean isAgeBased,
			final Integer encounterId, final Short patientSex,
			final String gwidPattern, final Integer ageInDay) {

		CriteriaBuilder cb= em.getCriteriaBuilder();
		CriteriaQuery<ClinicalElementBean> query= cb.createQuery(ClinicalElementBean.class);
		Root<ClinicalElements> root= query.from(ClinicalElements.class);
		

		Join<ClinicalElements,PatientClinicalElements> paramJoin=root.join(ClinicalElements_.patientClinicalElements,JoinType.INNER);
		Predicate encounterPred=cb.equal(paramJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
		
		Predicate gwidPred=cb.like(root.get(ClinicalElements_.clinicalElementsGwid),"000"+gwidPattern);
		Predicate clientIdPred=cb.like(root.get(ClinicalElements_.clinicalElementsGwid),clientId+gwidPattern);
		Predicate finalgwidPred=cb.or(gwidPred,clientIdPred);
		
		Predicate genderPred=root.get(ClinicalElements_.clinicalElementsGender).in(patientSex,0);
		Predicate finalPred=cb.and(encounterPred,finalgwidPred,genderPred);

		if(isAgeBased==true){
			Join<ClinicalElements,ClinicalElementsCondition> condParamJoin=root.join(ClinicalElements_.clinicalElementsConditions,JoinType.LEFT);
			Predicate defaultstartAge=cb.isNull(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage));
			Predicate defaultendAge=cb.isNull(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage));
			Predicate startAge=cb.lessThan(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage),ageInDay);
			Predicate endAge=cb.greaterThanOrEqualTo(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage),ageInDay);
			Predicate defAgePred=cb.and(defaultstartAge,defaultendAge);
			Predicate agePred=cb.and(startAge,endAge);
			Predicate age =cb.or(defAgePred,agePred);
			finalPred=cb.and(finalPred,age);
		}
		
		query.select(cb.construct(ClinicalElementBean.class,
				root.get(ClinicalElements_.clinicalElementsName),
				root.get(ClinicalElements_.clinicalElementsGwid),
				root.get(ClinicalElements_.clinicalElementsNotes),
				root.get(ClinicalElements_.clinicalElementsDatatype),
				root.get(ClinicalElements_.clinicalElementsCptcode),
				root.get(ClinicalElements_.clinicalElementsIcd9code),
				root.get(ClinicalElements_.clinicalElementsSnomed),
				root.get(ClinicalElements_.clinicalElementsIsactive),
				root.get(ClinicalElements_.clinicalElementsIsglobal),
				root.get(ClinicalElements_.clinicalElementsIshistory),
				root.get(ClinicalElements_.clinicalElementsIsepisode),
				root.get(ClinicalElements_.clinicalElementsTextDimension),
				root.get(ClinicalElements_.clinicalElementsGender),
				root.get(ClinicalElements_.clinicalElementsIsselect)));
		
		query.where(finalPred);
	
		try{
			return em.createQuery(query).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private List<ClinicalElementBean> getPatClinicalHistoryData(
			final String clientId, final boolean isAgeBased,
			final Integer patientId, final Short patientSex,
			final String gwidPattern, final Integer ageInDay) {

		CriteriaBuilder cb= em.getCriteriaBuilder();
		CriteriaQuery<ClinicalElementBean> query= cb.createQuery(ClinicalElementBean.class);
		Root<ClinicalElements> root= query.from(ClinicalElements.class);


		Join<ClinicalElements,PatientClinicalHistory> paramJoin=root.join(ClinicalElements_.patientClinicalHistory,JoinType.INNER);
		Predicate patientPred=cb.equal(paramJoin.get(PatientClinicalHistory_.patientClinicalHistoryPatientid),patientId);
		Predicate defGWIDPred=cb.like(root.get(ClinicalElements_.clinicalElementsGwid),"000"+gwidPattern);
		Predicate clientGWIDPred=cb.like(root.get(ClinicalElements_.clinicalElementsGwid),clientId+gwidPattern);
		Predicate finalGWIDPred=cb.or(defGWIDPred,clientGWIDPred);
		Predicate genderPred=root.get(ClinicalElements_.clinicalElementsGender).in(patientSex,0);
		Predicate finalPred=cb.and(patientPred,finalGWIDPred,genderPred);

		if(isAgeBased==true){
			Join<ClinicalElements, ClinicalElementsCondition> condParamJoin=root.join(ClinicalElements_.clinicalElementsConditions,JoinType.LEFT);
			Predicate defaultstartAge=cb.isNull(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage));
			Predicate defaultendAge=cb.isNull(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage));
			Predicate startAge=cb.lessThan(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage),ageInDay);
			Predicate endAge=cb.greaterThanOrEqualTo(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage),ageInDay);
			Predicate defAgePred=cb.and(defaultstartAge,defaultendAge);
			Predicate agePred=cb.and(startAge,endAge);
			Predicate age =cb.or(defAgePred,agePred);
			finalPred=cb.and(finalPred,age);
		}

		query.select(cb.construct(ClinicalElementBean.class,
				root.get(ClinicalElements_.clinicalElementsName),
				root.get(ClinicalElements_.clinicalElementsGwid),
				root.get(ClinicalElements_.clinicalElementsNotes),
				root.get(ClinicalElements_.clinicalElementsDatatype),
				root.get(ClinicalElements_.clinicalElementsCptcode),
				root.get(ClinicalElements_.clinicalElementsIcd9code),
				root.get(ClinicalElements_.clinicalElementsSnomed),
				root.get(ClinicalElements_.clinicalElementsIsactive),
				root.get(ClinicalElements_.clinicalElementsIsglobal),
				root.get(ClinicalElements_.clinicalElementsIshistory),
				root.get(ClinicalElements_.clinicalElementsIsepisode),
				root.get(ClinicalElements_.clinicalElementsTextDimension),
				root.get(ClinicalElements_.clinicalElementsGender),
				root.get(ClinicalElements_.clinicalElementsIsselect)));
		
		query.where(finalPred);
		
		try{
			return em.createQuery(query).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	
	}

	private List<PatientClinicalElementsBean> getHistoryElemPatientData(
			String clientId, Integer patientId, String gwidPattern) {

		CriteriaBuilder cb= em.getCriteriaBuilder();
		CriteriaQuery<PatientClinicalElementsBean> query= cb.createQuery(PatientClinicalElementsBean.class);
		Root<PatientClinicalHistory> root= query.from(PatientClinicalHistory.class);

		Join<PatientClinicalHistory,ClinicalElements> paramJoin=root.join(PatientClinicalHistory_.clinicalElement,JoinType.INNER);
		Predicate gwidPred=cb.like(root.get(PatientClinicalHistory_.patientClinicalHistoryGwid),"000"+gwidPattern);
		Predicate clientIdPred=cb.like(root.get(PatientClinicalHistory_.patientClinicalHistoryGwid),clientId+gwidPattern);
		Predicate elementPred=cb.or(gwidPred,clientIdPred);
		Predicate patientPred=cb.equal(root.get(PatientClinicalHistory_.patientClinicalHistoryPatientid),patientId);
		Predicate isHistoryPred=cb.equal(paramJoin.get(ClinicalElements_.clinicalElementsIshistory),true);
		
		query.select(cb.construct(PatientClinicalElementsBean.class,
				root.get(PatientClinicalHistory_.patientClinicalHistoryGwid),
				root.get(PatientClinicalHistory_.patientClinicalHistoryValue),
				paramJoin.get(ClinicalElements_.clinicalElementsDatatype),
				root.get(PatientClinicalHistory_.patientClinicalHistoryId)));
		
		query.where(cb.and(elementPred,patientPred,isHistoryPred));
	
		try{
			return em.createQuery(query).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private List<PatientClinicalElementsBean> getNonHistoryElemPatientData(
			String clientId, Integer patientId, Integer encounterId,
			String gwidPattern) {
		
		CriteriaBuilder cb= em.getCriteriaBuilder();
		CriteriaQuery<PatientClinicalElementsBean> query= cb.createQuery(PatientClinicalElementsBean.class);
		Root<PatientClinicalElements> root= query.from(PatientClinicalElements.class);
		

		Join<PatientClinicalElements,ClinicalElements> paramJoin=root.join(PatientClinicalElements_.clinicalElement,JoinType.INNER);
		Predicate encounterPred=null;
		if(encounterId!=-1)
			encounterPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
		Predicate patientPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
		Predicate gwidPred=cb.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid),"000"+gwidPattern);
		Predicate clientIdPred=cb.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid),clientId+gwidPattern);
		Predicate elementPred=cb.or(gwidPred,clientIdPred);
		Predicate isHistoryPred=cb.equal(paramJoin.get(ClinicalElements_.clinicalElementsIshistory),false);
		Predicate finalPred=null;
		if(encounterId!=-1)
			finalPred= cb.and(encounterPred,patientPred,elementPred,isHistoryPred);
		else
			finalPred= cb.and(patientPred,elementPred,isHistoryPred);

		query.select(cb.construct(PatientClinicalElementsBean.class,
				root.get(PatientClinicalElements_.patientClinicalElementsGwid),
				root.get(PatientClinicalElements_.patientClinicalElementsValue),
				paramJoin.get(ClinicalElements_.clinicalElementsDatatype),
				root.get(PatientClinicalElements_.patientClinicalElementsId)));
		
		query.where(finalPred);

		try{
			return em.createQuery(query).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private List<ClinicalElementBean> getClinicalElements(
			final Integer templateID, final Short patientSex,
			final Integer tabType, final String leafCreatedDate,
			final Integer ageinDay, final boolean isAgeBased) {

		CriteriaBuilder cb= em.getCriteriaBuilder();
		CriteriaQuery<ClinicalElementBean> query= cb.createQuery(ClinicalElementBean.class);
		Root<ClinicalElements> root= query.from(ClinicalElements.class);
		
		Join<ClinicalElements,ClinicalElementTemplateMapping> paramJoin=root.join(ClinicalElements_.clinicalElementTemplateMapping,JoinType.INNER);
		Predicate tempPredicate = cb.equal(paramJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid),templateID);
		Predicate tabTypePred = cb.equal(paramJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingType),tabType);
		Predicate genderPred =root.get(ClinicalElements_.clinicalElementsGender).in(0,patientSex);
		Predicate timePred=null;
		
		Predicate finalPred=cb.and(tempPredicate,tabTypePred,genderPred);
		/*if(!leafCreatedDate.toString().equalsIgnoreCase("-1")){
			Predicate timeStampPred=cb.lessThanOrEqualTo(paramJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTimestamp),Timestamp.valueOf(leafCreatedDate));
			Predicate nullPred=cb.isNull(paramJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTimestamp));
			timePred=cb.or(timeStampPred,nullPred);
		}*/
		Predicate age=null;
		if(isAgeBased==true){
			Join<ClinicalElements, ClinicalElementsCondition> condParamJoin=root.join(ClinicalElements_.clinicalElementsConditions,JoinType.LEFT);
			Predicate defaultstartAge=cb.isNull(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage));
			Predicate defaultendAge=cb.isNull(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage));
			Predicate startAge=cb.lessThan(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage),ageinDay);
			Predicate endAge=cb.greaterThanOrEqualTo(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage),ageinDay);
			Predicate defAgePred=cb.and(defaultstartAge,defaultendAge);
			Predicate agePred=cb.and(startAge,endAge);
			age=cb.or(defAgePred,agePred);
		}
	
		/*if(!leafCreatedDate.toString().equalsIgnoreCase("-1")){
			finalPred=cb.and(finalPred,timePred);
		}*/
		if(isAgeBased==true){
			finalPred=cb.and(finalPred,age);
		}
		
		query.select(cb.construct(ClinicalElementBean.class,
				root.get(ClinicalElements_.clinicalElementsName),
				root.get(ClinicalElements_.clinicalElementsGwid),
				root.get(ClinicalElements_.clinicalElementsNotes),
				root.get(ClinicalElements_.clinicalElementsDatatype),
				root.get(ClinicalElements_.clinicalElementsCptcode),
				root.get(ClinicalElements_.clinicalElementsIcd9code),
				root.get(ClinicalElements_.clinicalElementsSnomed),
				root.get(ClinicalElements_.clinicalElementsIsactive),
				root.get(ClinicalElements_.clinicalElementsIsglobal),
				root.get(ClinicalElements_.clinicalElementsIshistory),
				root.get(ClinicalElements_.clinicalElementsIsepisode),
				root.get(ClinicalElements_.clinicalElementsTextDimension),
				root.get(ClinicalElements_.clinicalElementsGender),
				root.get(ClinicalElements_.clinicalElementsIsselect)));
		
		query.where(finalPred);
		
		try{
			return em.createQuery(query).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	
	}

	private String getPatLeafByLeafIdAndEncId(int leafId,
			Integer encounterId) {
		CriteriaBuilder cb= em.getCriteriaBuilder();
		CriteriaQuery<Object> query= cb.createQuery(Object.class);
		Root<LeafPatient> root= query.from(LeafPatient.class);
		
		query.select(root.get(LeafPatient_.leafPatientCreatedDate));
		
		query.where(cb.equal(root.get(LeafPatient_.leafPatientLeafLibraryId),leafId),
				cb.equal(root.get(LeafPatient_.leafPatientEncounterId),encounterId));
		
		List<Object> result= em.createQuery(query).getResultList();
		if(result != null && result.size()>0)
			return result.get(0).toString();
		else
			return "-1";		
	}

	private List<LeafLibrary> getLeafDetailsByTemplateId(Integer templateId) {
		CriteriaBuilder cb= em.getCriteriaBuilder();
		CriteriaQuery<LeafLibrary> query= cb.createQuery(LeafLibrary.class);
		Root<LeafLibrary> root= query.from(LeafLibrary.class);
		
		query.where(cb.equal(root.get(LeafLibrary_.leafLibrarySoaptemplateId),templateId));
		return em.createQuery(query).getResultList();
	}

	public List<ClinicalElements> setHistoryClinicalDataBean(Integer patientId,Integer encounterId,Integer templateId,Integer tabType,String gwidPattern){

		getPatientDetails(patientId);
		
		List<LeafLibrary> leafDetails=leafLibraryRepository.findAll(LeafLibrarySpecification.getLeafDetailsByTemplateId(templateId));
		List<LeafPatient> leafPatient=leafPatientRepository.findAll(LeafPatientSpecfication.getPatLeafByLeafIdAndEncId(leafDetails.get(0)==null?-1:leafDetails.get(0).getLeafLibraryId(),encounterId));
		boolean isAgeBased=leafDetails.get(0).getLeafLibraryIsagebased()==null?false:leafDetails.get(0).getLeafLibraryIsagebased();

		/*
		 * Set clinical elements to ClinicalDataBean
		 * 
		 * */

		List<ClinicalElements> clinicalelements=clinicalElementsRepository.findAll(ClinicalElementsSpecification.getClinicalElements(templateId, patientSex, tabType,leafPatient.get(0).getLeafPatientCreatedDate()==null?"-1":leafPatient.get(0).getLeafPatientCreatedDate().toString(), ageinDay,isAgeBased));
		clinicalDataBean.setClinicalData(clinicalelements);
		if(encounterId==-1){
			clinicalelements=clinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinicalHistoryData(clinicalDataBean.clientId,isAgeBased, patientId, patientSex, gwidPattern, ageinDay));
		}else{
			clinicalelements=clinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getEncPatientClinicalData(clinicalDataBean.clientId,isAgeBased, encounterId, patientSex, gwidPattern, ageinDay));
		}
		clinicalDataBean.setClinicalData(clinicalelements);

		/*
		 * set Patient clinical elements or  patient History elements to PatientHistoryElementBean of ClinicalDataBean based on encounterId
		 * 
		 * */


		if(encounterId==-1){
			List<PatientClinicalHistory> patientHistoryData=patientClinicalHistoryRepository.findAll(PatientClinicalElementsSpecification.getHistoryElemPatientData(clinicalDataBean.clientId,patientId, gwidPattern));
			clinicalDataBean.setPatientHistoryData(patientHistoryData);
		}	else{
			List<PatientClinicalElements> patientData=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getNonHistoryElemPatientData(clinicalDataBean.clientId,patientId,encounterId,gwidPattern));
			clinicalDataBean.setPatientClinData(patientData);
		}
		return clinicalelements;
	}



	@Override
	public List<String> delPatientElementByEncID(Integer patientId,Integer encounterId, Integer tabId,Integer templateId,Integer prevEncounterId) {
		List<ClinicalElementTemplateMapping> mappedClinicalElementsList=clinicalElementTemplateMappingRepository.findAll(ClinicalElementsSpecification.getclinicalElementByTabAndTempId(tabId, templateId));
		ArrayList<String> mappedGwids=new ArrayList<String>();
		mappedGwids.add("-1");
		for (ClinicalElementTemplateMapping mapping : mappedClinicalElementsList) {
			mappedGwids.add(mapping.getClinicalElementTemplateMappingGwid());
		}
		/*List<String> associateGwids= getAssociateGwids(tabId, templateId, prevEncounterId);
		Set<String> gwids= new HashSet<String>();
		gwids.addAll(mappedGwids);
		gwids.addAll(associateGwids);
		mappedGwids.clear();
		mappedGwids.addAll(gwids);*/
		
		patientClinicalElementsRepository.deleteInBatch(patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinicalDataByGWID(patientId, encounterId, mappedGwids)));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "clinical data deleted @encounterId="+encounterId+" @templateId="+templateId+" @tabId="+tabId, sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		return mappedGwids;
	}



	private List<String> getAssociateGwids(Integer tabId, Integer templateId, Integer encounterId) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = builder.createQuery(String.class);
		Root<ClinicalElementTemplateMapping> root = cq.from(ClinicalElementTemplateMapping.class);
		Join<ClinicalElementTemplateMapping, ClinicalElements> clinicalJoin= root.join(ClinicalElementTemplateMapping_.clinicalElement, JoinType.INNER);		
		Join<ClinicalElements, PatientClinicalElements> patientJoin= clinicalJoin.join(ClinicalElements_.patientClinicalElements, JoinType.LEFT);
		Join<ClinicalElements, ClinicalTextMapping> textJoin= clinicalJoin.join(ClinicalElements_.clinicalTextMappings, JoinType.INNER);
		patientJoin.on(builder.equal(patientJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId));
		
		cq.select(textJoin.get(ClinicalTextMapping_.clinicalTextMappingTextboxGwid));
		
		cq.where(builder.equal(root.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingType),tabId),
				  builder.equal(root.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid),templateId));
		
		List<String> result= new ArrayList<String>();
		try{
			result= em.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void deleteNotesData(Integer patientId, Integer encounterId,Integer tabId, Integer templateId) {
		String notesGWID=null;
		if(tabId==3){
			notesGWID="0000100400000000001";
		}if(tabId==5){
			notesGWID="0000200100000000000";
		}if(tabId==7){
			notesGWID="0000400000000000001";
		}
		if(notesGWID!=null)
			patientClinicalElementsRepository.deleteInBatch(patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinEleByGWID(encounterId, patientId, notesGWID)));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.TEMPLATE, LogActionType.VIEW, 1, Log_Outcome.SUCCESS, "clinical notes data deleted @encounterId="+encounterId+" @templateId="+templateId+" @tabId="+tabId, sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
	}



	@Override
	public void insertDataForImport(Integer patientId,Integer encounterId,Integer prevEncounterId, Integer tabId, Integer templateId,List<String> mappedGwids) {
		List<PatientClinicalElements> patclinicalElems=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinicalDataByGWID(patientId, prevEncounterId, mappedGwids));

		for (PatientClinicalElements patclincialElem : patclinicalElems) {
			PatientClinicalElements patElem=new PatientClinicalElements();
			patElem.setPatientClinicalElementsPatientid(patclincialElem.getPatientClinicalElementsPatientid());
			patElem.setPatientClinicalElementsChartid(patclincialElem.getPatientClinicalElementsChartid());
			patElem.setPatientClinicalElementsEncounterid(encounterId);
			patElem.setPatientClinicalElementsGwid(patclincialElem.getPatientClinicalElementsGwid());
			patElem.setPatientClinicalElementsValue(patclincialElem.getPatientClinicalElementsValue());
			patientClinicalElementsRepository.save(patElem);
		}

		patientClinicalElementsRepository.save(patclinicalElems);
		String notesGWID=null;
		if(tabId==3){
			if(isClinicalElemActive("0000100400000000001")){
				notesGWID="0000100400000000001";
			}
		}if(tabId==5){
			if(isClinicalElemActive("0000200100000000000")){
				notesGWID="0000200100000000000";
			}
		}if(tabId==7){
			if(isClinicalElemActive("0000400000000000001")){
				notesGWID="0000400000000000001";
			}
			List<EncounterPlan> encounterPlan=encounterPlanRepository.findAll(EncounterSpecification.getEncounterPlanByEncId(prevEncounterId));
			
			for(int i=0; i<encounterPlan.size(); i++){
				EncounterPlan entity= new EncounterPlan();
				entity.setEncounterid(encounterId);
				entity.setPlantext(encounterPlan.get(i).getPlantext());
				encounterPlanRepository.saveAndFlush(entity);
			}

		}
		if(notesGWID!=null){
			patclinicalElems = patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinEleByGWID(prevEncounterId, patientId,notesGWID));
			for (PatientClinicalElements patclincialElem : patclinicalElems) {
				PatientClinicalElements patElem=new PatientClinicalElements();
				patElem.setPatientClinicalElementsPatientid(patclincialElem.getPatientClinicalElementsPatientid());
				patElem.setPatientClinicalElementsChartid(patclincialElem.getPatientClinicalElementsChartid());
				patElem.setPatientClinicalElementsEncounterid(encounterId);
				patElem.setPatientClinicalElementsGwid(patclincialElem.getPatientClinicalElementsGwid());
				patElem.setPatientClinicalElementsValue(patclincialElem.getPatientClinicalElementsValue());
				patientClinicalElementsRepository.save(patElem);
			}
		}
	}

	@Override
	public List<ClinicalElementsOptionBean> getSymptomClinicalElementOptions(
			String elementGWId, Integer patientId, Integer encounterId,
			int patientGender, int ageinDay, boolean isAgeBased, int flag) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<ClinicalElementsOptions> root = cq.from(ClinicalElementsOptions.class);
		/*Join<ClinicalElementsOptions,PatientClinicalElements> gwIdJoin = root.join(ClinicalElementsOptions_.patientClinicalElements,JoinType.LEFT);
		Predicate patientClinicalGWId = builder.equal(gwIdJoin.get(PatientClinicalElements_.patientClinicalElementsGwid),elementGWId);
		Predicate patientIdPred = builder.equal(gwIdJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
		Predicate encId = builder.equal(gwIdJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
		gwIdJoin.on(patientClinicalGWId,patientIdPred,encId);*/
		Join<ClinicalElementsOptions,ClinicalElementsCondition> rootJoin = root.join(ClinicalElementsOptions_.clinicalElementsConditions,JoinType.LEFT);
		Predicate clinicalElemOptionGwid = builder.like(root.get(ClinicalElementsOptions_.clinicalElementsOptionsGwid), elementGWId);
		Predicate clinicalElemOptionActive= builder.equal(root.get(ClinicalElementsOptions_.clinicalElementsOptionsActive), true);
		Predicate clinicalElemOptionGender= root.get(ClinicalElementsOptions_.clinicalElementsOptionsGender).in("0",patientGender);
		Predicate optionalcondition;
		cq.select(builder.construct(ClinicalElementsOptionBean.class,
				root.get(ClinicalElementsOptions_.clinicalElementsOptionsId),
				root.get(ClinicalElementsOptions_.clinicalElementsOptionsOrderBy),
				root.get(ClinicalElementsOptions_.clinicalElementsOptionsGwid),
				root.get(ClinicalElementsOptions_.clinicalElementsOptionsName),
				root.get(ClinicalElementsOptions_.clinicalElementsOptionsValue),
				root.get(ClinicalElementsOptions_.clinicalElementsOptionsRetaincase)
				//gwIdJoin.get(PatientClinicalElements_.patientClinicalElementsValue)
				));
		
		
		/*if(flag == 1)
		{
			optionalcondition = builder.and(gwIdJoin.get(PatientClinicalElements_.patientClinicalElementsValue).isNotNull(),clinicalElemOptionGwid,clinicalElemOptionActive,clinicalElemOptionGender);
		}*/
			optionalcondition = builder.and(clinicalElemOptionGwid,clinicalElemOptionActive,clinicalElemOptionGender);
		
		
		if(isAgeBased && ageinDay != -1){
			Predicate ageInDayCondition;
			Predicate p1 = builder.and(rootJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage).isNull(),rootJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage).isNull());
			Predicate p2 = builder.and(builder.lessThan(rootJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage),ageinDay),builder.greaterThanOrEqualTo(rootJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage), ageinDay));
			ageInDayCondition = builder.or(p1,p2);
			cq.where(optionalcondition,ageInDayCondition);
		}
		else
		{
			cq.where(optionalcondition);
		}
		System.out.println("where problem????");
		List<Object> hpinotes=em.createQuery(cq).getResultList();
		List<ClinicalElementsOptionBean> clinicalElemOptBean = new ArrayList<ClinicalElementsOptionBean>();
			for(int i=0; i<hpinotes.size();i++)
			{
				ClinicalElementsOptionBean clinicalElemOpt = (ClinicalElementsOptionBean) hpinotes.get(i);              
				clinicalElemOptBean.add(clinicalElemOpt);
			}
			
		return clinicalElemOptBean;
	}

	@Override
	public List<ClinicalElementsOptionBean> getSymptomElementOptionAfterUnion(
			String elementGWId, Integer patientId, Integer encounterId,
			int patientGender, int ageinDay, boolean isAgeBased, int flag) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery();
		Root<ClinicalElementsOptions> root = cq.from(ClinicalElementsOptions.class);
		/*Join<ClinicalElementsOptions,PatientClinicalElements> gwIdJoin = root.join(ClinicalElementsOptions_.patientClinicalElements,JoinType.INNER);
		Predicate patientClinicalGWId = cb.equal(gwIdJoin.get(PatientClinicalElements_.patientClinicalElementsGwid),elementGWId);
		Predicate patientIdPred = cb.equal(gwIdJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
		Predicate encId = cb.equal(gwIdJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
		gwIdJoin.on(patientClinicalGWId,patientIdPred,encId);*/
		Predicate clinicalElemOptionGwid = cb.like(root.get(ClinicalElementsOptions_.clinicalElementsOptionsGwid), elementGWId);
		Predicate clinicalElemOptionActive= cb.equal(root.get(ClinicalElementsOptions_.clinicalElementsOptionsActive), true);
		Predicate clinicalElemOptionGender= root.get(ClinicalElementsOptions_.clinicalElementsOptionsGender).in("0",patientGender);
		cq.where(clinicalElemOptionGwid,clinicalElemOptionActive,clinicalElemOptionGender);
		cq.orderBy(cb.asc(root.get(ClinicalElementsOptions_.clinicalElementsOptionsOrderBy)),cb.asc(root.get(ClinicalElementsOptions_.clinicalElementsOptionsValue)));
		cq.select(cb.construct(ClinicalElementsOptionBean.class,
				root.get(ClinicalElementsOptions_.clinicalElementsOptionsId),
				root.get(ClinicalElementsOptions_.clinicalElementsOptionsOrderBy),
				root.get(ClinicalElementsOptions_.clinicalElementsOptionsGwid),
				root.get(ClinicalElementsOptions_.clinicalElementsOptionsName),
				root.get(ClinicalElementsOptions_.clinicalElementsOptionsValue),
				root.get(ClinicalElementsOptions_.clinicalElementsOptionsRetaincase)
				//gwIdJoin.get(PatientClinicalElements_.patientClinicalElementsValue)
				));
		List<Object> hpinotes=em.createQuery(cq).getResultList();
		List<ClinicalElementsOptionBean> gsBean = new ArrayList<ClinicalElementsOptionBean>();
			for(int i=0; i<hpinotes.size();i++)
			{
				ClinicalElementsOptionBean gs = (ClinicalElementsOptionBean) hpinotes.get(i);              
				gsBean.add(gs);
			}
			
		return gsBean;
	}
}
