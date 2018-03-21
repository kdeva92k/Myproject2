package com.glenwood.glaceemr.server.application.services.portal.portalMedicalSummary;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.HttpConnectionUtils;
import com.glenwood.glaceemr.server.application.Bean.mailer.GlaceMailer;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.CurrentMedication;
import com.glenwood.glaceemr.server.application.models.CurrentMedication_;
import com.glenwood.glaceemr.server.application.models.DirectEmailLog;
import com.glenwood.glaceemr.server.application.models.DirectEmailLog_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.PatientAllergies_;
import com.glenwood.glaceemr.server.application.models.PatientPortalUser;
import com.glenwood.glaceemr.server.application.models.InitialSettings;
import com.glenwood.glaceemr.server.application.models.PatientPortalMenuConfig;
import com.glenwood.glaceemr.server.application.models.PatientRegistrationBean;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PortalConfigurationBean;
import com.glenwood.glaceemr.server.application.models.PatientAllergies;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.PatientEncounterType;
import com.glenwood.glaceemr.server.application.models.PatientEncounterType_;
import com.glenwood.glaceemr.server.application.models.PatientPortalFeatureConfig;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PlanInstruction;
import com.glenwood.glaceemr.server.application.models.PortalMedicalSummaryBean;
import com.glenwood.glaceemr.server.application.models.PortalMedicationBean;
import com.glenwood.glaceemr.server.application.models.PortalPlanOfCareBean;
import com.glenwood.glaceemr.server.application.models.PortalVitalsBean;
import com.glenwood.glaceemr.server.application.models.Prescription;
import com.glenwood.glaceemr.server.application.models.Prescription_;
import com.glenwood.glaceemr.server.application.models.ProblemList;
import com.glenwood.glaceemr.server.application.models.UnitsOfMeasure;
import com.glenwood.glaceemr.server.application.models.UnitsOfMeasure_;
import com.glenwood.glaceemr.server.application.models.VitalsParameter;
import com.glenwood.glaceemr.server.application.models.VitalsParameter_;
import com.glenwood.glaceemr.server.application.repositories.BillinglookupRepository;
import com.glenwood.glaceemr.server.application.repositories.ChartRepository;
import com.glenwood.glaceemr.server.application.repositories.DirectEmailLogRepository;
import com.glenwood.glaceemr.server.application.repositories.EmpProfileRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterEntityRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientPortalUserRepository;
import com.glenwood.glaceemr.server.application.repositories.InitialSettingsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientAllergiesRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientPortalMenuConfigRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.repositories.PlanTypeRepository;
import com.glenwood.glaceemr.server.application.repositories.ProblemListRepository;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.portal.portalAppointments.PortalAppointmentsService;
import com.glenwood.glaceemr.server.application.services.portal.portalDocuments.SharedFolderUtil;
import com.glenwood.glaceemr.server.application.services.portal.portalSettings.AjaxConnect;
import com.glenwood.glaceemr.server.application.services.portal.portalSettings.PortalBillingConfigFields;
import com.glenwood.glaceemr.server.application.services.portal.portalSettings.PortalSettingsService;
import com.glenwood.glaceemr.server.application.specifications.ChartSpecification;
import com.glenwood.glaceemr.server.application.specifications.PatientRegistrationSpecification;
import com.glenwood.glaceemr.server.application.specifications.PortalMedicalSummarySpecification;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.glenwood.glaceemr.server.utils.SessionMap;



@Service
public class PortalMedicalSummaryServiceImpl implements PortalMedicalSummaryService{

	@Autowired
	ProblemListRepository problemListRepository;

	@Autowired
	PatientAllergiesRepository patientAllergiesRepository;

	@Autowired
	EmpProfileRepository empProfileRepository;

	@Autowired
	ChartRepository chartRepository;

	@Autowired
	PatientRegistrationRepository patientRegistrationRepository;
	
	@Autowired
	PatientPortalUserRepository h809Repository;
	
	@Autowired
	BillinglookupRepository billinglookupRepository;
	
	@Autowired
	PatientClinicalElementsRepository patientClinicalElementsRepository;
	
	@Autowired
	EncounterEntityRepository encounterEntityRepository;
	
	@Autowired
	PlanTypeRepository planTypeRepository;
	
	@Autowired
	PortalSettingsService portalSettingsService;
	
	@Autowired
	PortalAppointmentsService portalAppointmentsService;
	
	@Autowired
	InitialSettingsRepository initialSettingsRepository;

	@Autowired
	EntityManagerFactory emf ;

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;

	@Autowired
	DirectEmailLogRepository directEmailLogRepository;
	
	final static Logger logger = Logger.getLogger(PortalMedicalSummaryServiceImpl.class);

	@Override
	public PortalConfigurationBean getSessionMap(String username) throws JsonProcessingException {
							
		PortalConfigurationBean configurationBean=new PortalConfigurationBean();
		
		List<InitialSettings> practiceDetails=portalSettingsService.getPracticeDetails();
		
		PortalBillingConfigFields configFields=portalSettingsService.getPortalBillingConfigFields();
		
		List<PatientPortalMenuConfig> portalMenuItemList=portalSettingsService.getPortalMenuConfig(true);
		
		List<PatientPortalFeatureConfig> portalFeatureConfigList=portalSettingsService.getPortalFeatureConfig(true);
				
		configurationBean.setSessionID(sessionMap.getPortalSessionID());
		configurationBean.setUsername(sessionMap.getPortalUser());
		configurationBean.setTennantDB(sessionMap.getPortalDBName());
		configurationBean.setUserID(sessionMap.getPortalUserID());
		configurationBean.setGlaceApacheUrl(sessionMap.getGlaceApacheUrl());
		configurationBean.setGlaceSpringContext(sessionMap.getGlaceSpringContext());
		configurationBean.setGlaceTomcatContext(sessionMap.getGlaceTomcatContext());
		configurationBean.setGlaceTomcatUrl(sessionMap.getGlaceTomcatUrl());
		configurationBean.setPortalLoginUrl(sessionMap.getPortalLoginUrl());
		configurationBean.setPortalLoginContext(sessionMap.getPortalLoginContext());
		configurationBean.setPortalMenuItemList(portalMenuItemList);
		configurationBean.setPortalFeatureItemList(portalFeatureConfigList);
		configurationBean.setLoggedIn(true);		
		
		for(int p=0;p<practiceDetails.size();p++){
			
			if(practiceDetails.get(p).getInitialSettingsOptionName().equalsIgnoreCase("Address")){
				sessionMap.setPracticeAddress(practiceDetails.get(p).getInitialSettingsOptionValue());
				configurationBean.setPracticeAddress(sessionMap.getPracticeAddress());
			}
				
			if(practiceDetails.get(p).getInitialSettingsOptionName().equalsIgnoreCase("Doctor Message")){
				sessionMap.setPracticeDoctorMessage(practiceDetails.get(p).getInitialSettingsOptionValue());
				configurationBean.setPracticeDoctorMessage(sessionMap.getPracticeDoctorMessage());
			}
				
			if(practiceDetails.get(p).getInitialSettingsOptionName().equalsIgnoreCase("city")){
				sessionMap.setPracticeCity(practiceDetails.get(p).getInitialSettingsOptionValue());
				configurationBean.setPracticeCity(sessionMap.getPracticeCity());
			}
					
			if(practiceDetails.get(p).getInitialSettingsOptionName().equalsIgnoreCase("Web Address")){
				sessionMap.setPracticeWebAddress(practiceDetails.get(p).getInitialSettingsOptionValue());
				configurationBean.setPracticeWebAddress(sessionMap.getPracticeWebAddress());
			}
						
			if(practiceDetails.get(p).getInitialSettingsOptionName().equalsIgnoreCase("Fax Number")){
				sessionMap.setPracticeFax(practiceDetails.get(p).getInitialSettingsOptionValue());
				configurationBean.setPracticeFax(sessionMap.getPracticeFax());
			}
							
			if(practiceDetails.get(p).getInitialSettingsOptionName().equalsIgnoreCase("state")){
				
				if(practiceDetails.get(p).getInitialSettingsOptionValue()!=null)
				for(int s=0;s<configFields.getStatesList().size();s++){
					if(configFields.getStatesList().get(s).getBillingConfigTableConfigId().equals(Integer.parseInt(practiceDetails.get(p).getInitialSettingsOptionValue())))
						sessionMap.setPracticeState(configFields.getStatesList().get(s).getBillingConfigTableLookupDesc());
				}
				configurationBean.setPracticeState(sessionMap.getPracticeState());
			}
				
			if(practiceDetails.get(p).getInitialSettingsOptionName().equalsIgnoreCase("zipCode")){
				sessionMap.setPracticeZipcode(practiceDetails.get(p).getInitialSettingsOptionValue());
				configurationBean.setPracticeZipcode(sessionMap.getPracticeZipcode());
			}
					
			if(practiceDetails.get(p).getInitialSettingsOptionName().equalsIgnoreCase("Practice Name")){
				sessionMap.setPracticeName(practiceDetails.get(p).getInitialSettingsOptionValue());
				configurationBean.setPracticeName(sessionMap.getPracticeName());
			}
							
			if(practiceDetails.get(p).getInitialSettingsOptionName().equalsIgnoreCase("email Address")){
				sessionMap.setPracticeEmail(practiceDetails.get(p).getInitialSettingsOptionValue());
				configurationBean.setPracticeEmail(sessionMap.getPracticeEmail());
			}
								
			if(practiceDetails.get(p).getInitialSettingsOptionName().equalsIgnoreCase("Phone No")){
				sessionMap.setPracticePhone(practiceDetails.get(p).getInitialSettingsOptionValue());
				configurationBean.setPracticePhone(sessionMap.getPracticePhone());
			}
									
			if(practiceDetails.get(p).getInitialSettingsOptionName().equalsIgnoreCase("Shared Folder Path")){
				sessionMap.setPracticeSharedFolderPath(practiceDetails.get(p).getInitialSettingsOptionValue());
				configurationBean.setPracticeSharedFolderPath(sessionMap.getPracticeSharedFolderPath());
			}
		}
		
		SharedFolderUtil.sharedFolderPath=sessionMap.getPracticeSharedFolderPath()+"/";

		return configurationBean;
	}



	@Override
	public List<PatientPortalUser> getPatientDetailsByUsername(String username) throws JsonProcessingException {
		
		System.out.println("username:::***************************************:::"+username);
		
		List<PatientPortalUser> patientPersonalDetails= h809Repository.findAll(PatientRegistrationSpecification.getPatientDetailsByUsername(username));
		
		System.out.println("patientPersonalDetailsSize::::*********************************************:::"+patientPersonalDetails.size());
		
		return patientPersonalDetails;

	}


	@Override
	public PatientRegistration getPatientPersonalDetails(int patientId) {

		PatientRegistration patientPersonalDetails= patientRegistrationRepository.findOne(PatientRegistrationSpecification.getPatientPersonalDetails(patientId));

		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Retrieving patient's personal details with patientId:"+patientId,-1,
				request.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Retrieving patient's personal details with patientId:"+patientId,"");
		
		return patientPersonalDetails;

	}

	@Override
	public List<Chart> getPatientChartId(int patientId) {

		List<Chart> chartId=chartRepository.findAll(ChartSpecification.getChartId(patientId));

		return chartId;

	}
	
	
	@Override
	public List<PortalPlanOfCareBean> getPlanOfCare(int patientId, int chartId, int pageOffset, int pageIndex) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PatientClinicalElements> root = cq.from(PatientClinicalElements.class);
		Join<PatientClinicalElements, Encounter> clinicalElementsEncounterJoin=root.join(PatientClinicalElements_.encounter,JoinType.INNER);
		Join<Encounter, PatientEncounterType> encounterTypeJoin=clinicalElementsEncounterJoin.join(Encounter_.patientEncounterType);
		Join<PatientClinicalElements, PlanInstruction> planInstructionClinicalElementsJoin=root.join(PatientClinicalElements_.planInstruction, JoinType.INNER);
		Join<Encounter, EmployeeProfile> encounterServiceDoctorJoin=clinicalElementsEncounterJoin.join(Encounter_.empProfileEmpId, JoinType.LEFT);
		
		cq.select(builder.construct(PortalPlanOfCareBean.class,root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),clinicalElementsEncounterJoin.get(Encounter_.encounterDate), encounterTypeJoin.get(PatientEncounterType_.encounterTypename), encounterServiceDoctorJoin.get(EmployeeProfile_.empProfileEmpid),encounterServiceDoctorJoin.get(EmployeeProfile_.empProfileFullname))).distinct(true);
		cq.where(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid), patientId)).orderBy(builder.desc(clinicalElementsEncounterJoin.get(Encounter_.encounterDate)));
		
		
		Query query=em.createQuery(cq);
		query.setFirstResult(pageIndex*pageOffset);	// Page no (offset)
		query.setMaxResults(pageOffset);		// Page size (No of results)
		
		List<Object> planOfCareResultList=query.getResultList();
		List<PortalPlanOfCareBean> detailsBeanList = new ArrayList<PortalPlanOfCareBean>();
		for(int i=0;i<planOfCareResultList.size();i++){
			PortalPlanOfCareBean detailsBean=(PortalPlanOfCareBean)planOfCareResultList.get(i);
		detailsBeanList.add(detailsBean);
		}
		
		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Retrieving patient's plan of care with patientId:"+patientId,-1,
				request.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Retrieving patient's plan of care with patientId:"+patientId,"");
	
		return detailsBeanList;
	}
	
	/*@Override
	public List<PlanType> getPlanOfCareDetails(int patientId, int encounterId, int pageOffset, int pageIndex) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery();
		Root<PlanType> root = cq.from(PlanType.class);
		Join<PlanType, PlanInstruction> planInstructionTypeJoin=root.join(PlanType_.planTypeInstructions,JoinType.INNER);
		Join<PlanInstruction, PatientClinicalElements> planInstructionClinicalElementsJoin=planInstructionTypeJoin.join(PlanInstruction_.patientPlanClinicalElements, JoinType.INNER);
		
		cq.multiselect(cb.construct(PlanType.class), root.get(PlanType_.planTypeId), root.get(PlanType_.planTypeName), root.get(PlanType_.planTypeGwid), root.get(PlanType_.planTypeInstructions));
		cq.where(cb.notEqual(root.get(PlanType_.planTypeId), 6),cb.equal(planInstructionClinicalElementsJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid), patientId), cb.equal(planInstructionClinicalElementsJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId));
		List<Object> planOfCareResultList=em.createQuery(cq).getResultList();
		List<PlanType> detailsBeanList = new ArrayList<PlanType>();
		for(int i=0;i<planOfCareResultList.size();i++){
			PlanType detailsBean=(PlanType)planOfCareResultList.get(i);
		detailsBeanList.add(detailsBean);
		}
		
		return detailsBeanList;
	}*/

	

	@Override
	public List<PatientClinicalElements> getPlanOfCareDetails(int patientId, int encounterId, int pageOffset, int pageIndex) {
		
		List<PatientClinicalElements> planTyleList=patientClinicalElementsRepository.findAll(PortalMedicalSummarySpecification.getPlanOfCareInstructionsByPatientId(patientId, encounterId));
		
		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Retrieving patient's plan of care details with patientId:"+patientId,-1,
				request.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Retrieving patient's plan of care details with patientId:"+patientId,"");
	
		return planTyleList;
	}
	
	@Override
	public List<ProblemList> getPatientProblemList(int patientId, String problemType, int pageOffset, int pageIndex) {

		List<ProblemList> problemsList=problemListRepository.findAll(PortalMedicalSummarySpecification.getPatientProblemList(patientId, problemType)/*, PortalMedicalSummarySpecification.createPortalProblemsListPageRequest(pageOffset, pageOffset)*/)/*.getContent()*/;

		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Retrieving patient's problem list with patientId:"+patientId,-1,
				request.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Retrieving patient's problem list with patientId:"+patientId,"");
	
		return problemsList;

	}
	
	@Override
	public List<PatientAllergies> getPatientAllergies(int chartId, int pageOffset, int pageIndex) {

		List<PatientAllergies> allergiesList=patientAllergiesRepository.findAll(PortalMedicalSummarySpecification.getPatientAllergiesList(chartId), PortalMedicalSummarySpecification.createPortalAllergiesPageRequest(pageOffset, pageIndex)).getContent();

		return allergiesList;

	}
	
	
	

	 /*Method to execute the custom queries*/ 
	public List<Map<String, Object>> CustomQueryResult(List<String> listinorder,CriteriaQuery<Object> cq) 
	{

		try{

			Iterator iter= em.createQuery(cq).getResultList().iterator();
			List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();

			while ( iter.hasNext() ) {
				Object[] row = (Object[]) iter.next();
				Map<String,Object> newMap=  new HashMap<String,Object>();
				for(int i=0;i<row.length;i++)
				{
					newMap.put(listinorder.get(i),(Object)row[i]);
				}
				resultList.add(newMap);

			}
			return resultList;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}
	
	
	@Override
	public PortalMedicalSummaryBean getPortalMedicalSummaryDetails(int patientId, int chartId) {
		
		int totalActiveProblemsCount=(int) problemListRepository.count(PortalMedicalSummarySpecification.getPatientProblemList(patientId, "Active"));
		int totalResolvedProblemsCount=(int) problemListRepository.count(PortalMedicalSummarySpecification.getPatientProblemList(patientId, "Resolved"));
		int totalProblemsCount=(int) problemListRepository.count(PortalMedicalSummarySpecification.getPatientProblemList(patientId, "All"));
		int totalAllergiesCount=(int) patientAllergiesRepository.count(PortalMedicalSummarySpecification.getPatientAllergiesList(chartId));
		int totalPlanOfCareCount=10;
		
		
		PortalMedicalSummaryBean summaryBean=new PortalMedicalSummaryBean(totalActiveProblemsCount,totalResolvedProblemsCount,totalProblemsCount,totalAllergiesCount,totalPlanOfCareCount);
		
		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Retrieving patient's medical summary details with patientId:"+patientId,-1,
				request.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Retrieving patient's medical summary details with patientId:"+patientId,"");
	
		return summaryBean;
	}
	
	public static Pageable createPortalPlanOfCareByDescDate(int pageIndex, int offset) {

		return new PageRequest(pageIndex, offset, Sort.Direction.DESC,"encounterDate");
	}
	
	
	@Override
	public List<PortalMedicationBean> getPatientMedication(int patientId, int chartId) 
	{
		try
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<PortalMedicationBean> criteriaQuery = builder.createQuery(PortalMedicationBean.class);
			Root<CurrentMedication> root = criteriaQuery.from(CurrentMedication.class);
			criteriaQuery.select(builder.construct(PortalMedicationBean.class, root.get(CurrentMedication_.currentMedicationRxName),
					root.get(CurrentMedication_.currentMedicationOrderOn),
					root.get(CurrentMedication_.currentMedicationIsActive)));
			criteriaQuery.where(builder.and(builder.equal(root.get(CurrentMedication_.currentMedicationPatientId), patientId),builder.equal(root.get(CurrentMedication_.currentMedicationIsActive), true))).distinct(true);
			List<PortalMedicationBean> medicationSummary=em.createQuery(criteriaQuery).getResultList();
			CriteriaQuery<PortalMedicationBean> criteriaQuery1 = builder.createQuery(PortalMedicationBean.class);
			Root<Prescription> root1 = criteriaQuery1.from(Prescription.class);
			criteriaQuery1.select(builder.construct(PortalMedicationBean.class, root1.get(Prescription_.rxname),
					root1.get(Prescription_.docPrescOrderedDate),
					root1.get(Prescription_.docPrescIsActive)));
			criteriaQuery1.where(builder.and(builder.equal(root1.get(Prescription_.docPrescPatientId), patientId),builder.equal(root1.get(Prescription_.docPrescIsActive), true))).distinct(true);
			List<PortalMedicationBean> medicationSummary1=em.createQuery(criteriaQuery1).getResultList();
			for(int i=0;i<medicationSummary1.size();i++){
				medicationSummary.add(medicationSummary1.get(i));
			}
			logger.info("Medication Summary result size: "+medicationSummary.size());
			return medicationSummary;
		}
		catch(Exception exception)
		{
			logger.error("Medication Summary API failed");
			exception.printStackTrace();
			return null;
		}
		finally
		{
			em.close();
		}
	}



	


	@Override
	public List<PortalVitalsBean> getPatientVital(int patientId, int chartId) {try
	{
		
		logger.error("Vitals API started");
		CriteriaBuilder builder = em.getCriteriaBuilder();
		List<String> gwIdList = new ArrayList<String>();
		gwIdList.add("0000200200100038000");gwIdList.add("0000200200100107000");gwIdList.add("0000200200100227000");gwIdList.add("0000200200100075000");gwIdList.add("0000200200100076000");gwIdList.add("0000200200100022000");gwIdList.add("0000200200100023000");gwIdList.add("0000200200100023000");gwIdList.add("0000200200100039000");gwIdList.add("0000200200100024000");gwIdList.add("0000200200100025000");gwIdList.add("0000200200100044000");
		CriteriaQuery<Integer> criteriaQuery1 = builder.createQuery(Integer.class);
		Root<PatientClinicalElements> root1 = criteriaQuery1.from(PatientClinicalElements.class);
		criteriaQuery1.select(builder.greatest(root1.get(PatientClinicalElements_.patientClinicalElementsEncounterid))).where(builder.and(builder.equal(root1.get(PatientClinicalElements_.patientClinicalElementsChartid),chartId)));
		List<Integer> EncounterIdList=em.createQuery(criteriaQuery1).setMaxResults(2).getResultList();
		int maxEncounterId = 0;
		if(EncounterIdList.size()>0){
			maxEncounterId=Integer.parseInt(HUtil.Nz(EncounterIdList.get(0),"0"));
		}
		CriteriaQuery<PortalVitalsBean> criteriaQuery = builder.createQuery(PortalVitalsBean.class);
		Root<PatientClinicalElements> root = criteriaQuery.from(PatientClinicalElements.class);
		Join<PatientClinicalElements,Encounter> PatientClinicalElementEncounterJoin=root.join(PatientClinicalElements_.encounter,JoinType.INNER);
		Join<PatientClinicalElements,VitalsParameter> patientClinicalElementVitalParameter=root.join(PatientClinicalElements_.vitalsParameter,JoinType.INNER);
		Join<VitalsParameter, UnitsOfMeasure> measureJoin=patientClinicalElementVitalParameter.join(VitalsParameter_.unitsOfMeasureTable,JoinType.INNER);
		criteriaQuery.select(builder.construct(PortalVitalsBean.class, 
				root.get(PatientClinicalElements_.patientClinicalElementsGwid),
				root.get(PatientClinicalElements_.patientClinicalElementsValue),
				PatientClinicalElementEncounterJoin.get(Encounter_.encounterDate),
				measureJoin.get(UnitsOfMeasure_.unitsOfMeasureCode)
				));
		criteriaQuery.where(builder.and(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsChartid), chartId),builder.equal(PatientClinicalElementEncounterJoin.get(Encounter_.encounterId), maxEncounterId),root.get(PatientClinicalElements_.patientClinicalElementsGwid).in(gwIdList))).distinct(true);
		List<PortalVitalsBean> vitalList=em.createQuery(criteriaQuery).getResultList();
		logger.info("Vital Summary result size: "+vitalList.size());
		return vitalList;
		
		
	}
	catch(Exception exception)
	{
		logger.error("Vital  API failed");
		exception.printStackTrace();
		return null;
	}
	finally
	{
		em.close();
	}}
	
	
	@Override
	public void putLog(int patientId, int chartId) {
		try {
			logger.error("Putting log");
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Integer> criteriaQuery1 = builder.createQuery(Integer.class);
			Root<DirectEmailLog> root1 = criteriaQuery1.from(DirectEmailLog.class);
			criteriaQuery1.select(root1.get(DirectEmailLog_.directEmailLogId));
			criteriaQuery1.where(builder.and(builder.equal(root1.get(DirectEmailLog_.directEmailLogPatientid), patientId),builder.equal(root1.get(DirectEmailLog_.directEmailLogActionType), 1),builder.equal(root1.get(DirectEmailLog_.directEmailLogUserType), 2))).distinct(true);
			List<Integer> logId=em.createQuery(criteriaQuery1).getResultList();
			if(logId.size()<1){
				CriteriaQuery<Integer> criteriaQuery = builder.createQuery(Integer.class);
				Root<DirectEmailLog> root = criteriaQuery.from(DirectEmailLog.class);
				criteriaQuery.select(builder.greatest(root.get(DirectEmailLog_.directEmailLogId)));
				int id=em.createQuery(criteriaQuery).getSingleResult();
			DirectEmailLog directEmailLog = new DirectEmailLog();
			directEmailLog.setDirectEmailLogId(id+1);
			directEmailLog.setDirectEmailLogActionType(1);
			directEmailLog.setDirectEmailLogSentBy(patientId);
			directEmailLog.setDirectEmailLogSentOn(directEmailLogRepository.findCurrentTimeStamp());
			directEmailLog.setDirectEmailLogUserType(2);
			directEmailLogRepository.saveAndFlush(directEmailLog);
			
			}
			logger.error("entered data in log");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error While entered data in log");
		}
	}



	@Override
	public List<PatientAllergies> getPatientAllergiesByPatientIdNew(
			int patientId, int pageOffset, int pageIndex)
			throws JsonProcessingException {
		try
		{
			Chart chart=chartRepository.findOne(ChartSpecification.getChartId(patientId));	
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<PatientAllergies> criteriaQuery = builder.createQuery(PatientAllergies.class);
			Root<PatientAllergies> root = criteriaQuery.from(PatientAllergies.class);
			criteriaQuery.select(builder.construct(PatientAllergies.class, root.get(PatientAllergies_. patAllergAllergicTo),
					root.get(PatientAllergies_. patAllergReactionTo),
					root.get(PatientAllergies_. patAllergOnsetDate),
					root.get(PatientAllergies_. patAllergCreatedOn),
					root.get(PatientAllergies_. patAllergModifiedOn),
					root.get(PatientAllergies_. patAllergDrugCategory),
					root.get(PatientAllergies_. patAllergSeverity)));
			criteriaQuery.where(builder.and(builder.equal(root.get(PatientAllergies_.patAllergStatus), 1),builder.notEqual(root.get(PatientAllergies_.patAllergTypeId),"-1"),builder.notEqual(root.get(PatientAllergies_.patAllergTypeId),"-2"),builder.equal(root.get(PatientAllergies_.patAllergChartId), chart.getChartId()))).distinct(true);
			List<PatientAllergies> patientAllergiesList=em.createQuery(criteriaQuery).getResultList();
			logger.info("Allergies  result size: "+patientAllergiesList.size());
			return patientAllergiesList;
		}
		catch(Exception exception)
		{
			logger.error("Allergies Summary API failed");
			exception.printStackTrace();
			return null;
		}
		finally
		{
			em.close();
		}
	}
	
	@Override
	public String getemailresponse(int patientId, int chartId, String fromDate, String toDate, String email,
			String comments,String encounterids,String accountId,int Transmitcheckboxflag) throws Exception {
		// TODO Auto-generated method stub
		String subject="";
		String tomcatURL="";
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<PatientRegistrationBean> criteriaQuery = builder.createQuery(PatientRegistrationBean.class);
		Root<PatientRegistration> root = criteriaQuery.from(PatientRegistration.class);
		criteriaQuery.select(builder.construct(PatientRegistrationBean.class, 
				root.get(PatientRegistration_.patientRegistrationFirstName),
				root.get(PatientRegistration_.patientRegistrationMidInitial),
				root.get(PatientRegistration_.patientRegistrationLastName)
				));
		criteriaQuery.where(builder.equal(root.get(PatientRegistration_.patientRegistrationId), patientId)).distinct(true);
		List<PatientRegistrationBean> PatientRegistrationList = em.createQuery(criteriaQuery).getResultList();

		if(Transmitcheckboxflag==1)
		{
			subject= "::"+HUtil.Nz(PatientRegistrationList.get(0).getPatientRegistrationFirstName(),"")+" "+
					HUtil.Nz(PatientRegistrationList.get(0).getPatientRegistrationMidInitial(),"")+" "+
					HUtil.Nz(PatientRegistrationList.get(0).getPatientRegistrationLastName(),"")+"  Sent Clinical Summary ";
		}
		else if(Transmitcheckboxflag==2)
		{

			subject= HUtil.Nz(PatientRegistrationList.get(0).getPatientRegistrationFirstName(),"")+" "+
					HUtil.Nz(PatientRegistrationList.get(0).getPatientRegistrationMidInitial(),"")+" "+
					HUtil.Nz(PatientRegistrationList.get(0).getPatientRegistrationLastName(),"")+"  Sent Clinical Summary Via Secure Messaging";

		}
		String fileName="";
		if(accountId.trim().equalsIgnoreCase("glace"))
		{
			String accountDetails = HttpConnectionUtils.postData("https://sso.glaceemr.com/TestSSOAccess?accountId="+accountId, "", HttpConnectionUtils.HTTP_CONNECTION_MODE,"text/plain");
			String url="http://192.168.2.53:8000/GlaceDeveloper/GenerateCDAServlet";
			String querystring="patientId="+patientId+"&fromDate="+fromDate.trim()+"&toDate="+toDate.trim()+"&mode=3&encounterids="+encounterids.trim()+"&email=1";
			AjaxConnect ajax=new AjaxConnect();
			fileName=ajax.sendPost(url,querystring);
		}else{

			String accountDetails = HttpConnectionUtils.postData("https://sso.glaceemr.com/TestSSOAccess?accountId="+accountId, "", HttpConnectionUtils.HTTP_CONNECTION_MODE,"");
			JSONObject json= new JSONObject(accountDetails);
			tomcatURL=json.getString("Glace_tomcat_URL")+"/GenerateCDAServlet";
			String querystring="patientId="+patientId+"&fromDate="+fromDate.trim()+"&toDate="+toDate.trim()+"&mode=3&encounterids="+encounterids.trim()+"&email=1";
			AjaxConnect ajax=new AjaxConnect();
			fileName=ajax.sendPost(tomcatURL,querystring);
		}

		String responsemessage=GlaceMailer.sendMail(email, comments, accountId,subject,fileName);
		return responsemessage;
	}


	public List<Encounter> getEncounterList1(int patientId, int chartId,Date fromDate,Date toDate,int offset,int pageindex) {try
	{
		logger.error("encounter API started");
		  TimeZone.setDefault(TimeZone.getTimeZone("EDT"));
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Encounter> criteriaQuery = builder.createQuery(Encounter.class);
		Root<Encounter> root = criteriaQuery.from(Encounter.class);
		Join<Encounter,EmployeeProfile> EncounterEmployeeProfilejoin=root.join(Encounter_.empProfileEmpId,JoinType.INNER);
		List<Predicate> predicates = new ArrayList<>();
		criteriaQuery.select(builder.construct(Encounter.class, 
				root.get((Encounter_.encounterDate)),
				root.get((Encounter_.encounterId)),
				EncounterEmployeeProfilejoin.get((EmployeeProfile_.empProfileFullname))
				));
		predicates.add(builder.and(builder.equal(root.get(Encounter_.encounterChartid),chartId),builder.equal(root.get(Encounter_.encounterType), 1)));
		 if( !HUtil.Nz(toDate,"").equals(""))
			predicates.add(builder.lessThan(builder.function("DATE", Date.class,root.get(Encounter_.encounterDate)),toDate));
		 if(!HUtil.Nz(fromDate,"").equals("") && !HUtil.Nz(toDate,"").equals(""))
			predicates.add(builder.and(builder.greaterThanOrEqualTo(builder.function("DATE", Date.class,root.get(Encounter_.encounterDate)),fromDate)));
		 criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		 criteriaQuery.orderBy(builder.desc(root.get(Encounter_.encounterDate))).distinct(true);
		List<Encounter> EncounterList = em.createQuery(criteriaQuery).setFirstResult(offset).setMaxResults(pageindex).getResultList();
		logger.info("Encounter result size: "+EncounterList.size());

		return EncounterList;
	}
	catch(Exception exception)
	{
		logger.error("Vital  API failed");
		exception.printStackTrace();
		return null;
	}
	finally
	{
		em.close();
	}}

}
