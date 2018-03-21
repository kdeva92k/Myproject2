package com.glenwood.glaceemr.server.application.services.portal.portalCahpsSurvey;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.CahpsQuestionnaire;
import com.glenwood.glaceemr.server.application.models.PatientCahpsSurvey;
import com.glenwood.glaceemr.server.application.models.PatientCahpsSurvey_;
import com.glenwood.glaceemr.server.application.models.PatientSurveyAnswers;
import com.glenwood.glaceemr.server.application.models.PatientSurveySaveBean;
import com.glenwood.glaceemr.server.application.repositories.AlertEventRepository;
import com.glenwood.glaceemr.server.application.repositories.CahpsQuestionnaireRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientPortalAlertConfigRespository;
import com.glenwood.glaceemr.server.application.repositories.PatientCahpsSurveyRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientSurveyAnswersRepository;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.specifications.PortalCahpsSurveySpecification;


@Service
public class PortalCahpsSurveyServiceImpl implements PortalCahpsSurveyService{



	@Autowired
	CahpsQuestionnaireRepository cahpsQuestionnaireRepository;

	@Autowired
	PatientCahpsSurveyRepository patientCahpsSurveyRepository;

	@Autowired
	PatientSurveyAnswersRepository patientSurveyAnswersRepository;
	
	@Autowired
	PatientPortalAlertConfigRespository h810Respository;
	
	@Autowired
	AlertEventRepository alertEventRepository;
	
	@Autowired
	EntityManager em;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;

	@Override
	public List<CahpsQuestionnaire> getPatientCahpsSurveyQuestionnaire(int patientAge) {
		
		List<CahpsQuestionnaire> questionnaireList=cahpsQuestionnaireRepository.findAll(PortalCahpsSurveySpecification.getPatientTotalAppointments(patientAge));

		return questionnaireList;
	}


	@Override
	public PatientCahpsSurvey saveCahpsSurvey(PatientSurveySaveBean surveySaveBean) {
		
		if(surveySaveBean.getSurveyAnswers().size()<=0){
			
			return null;
		}

		PatientCahpsSurvey cahpsSurvey=new PatientCahpsSurvey();

		cahpsSurvey.setDateOfSurvey(new Timestamp(new Date().getTime()));
		cahpsSurvey.setFillingPersonFname(surveySaveBean.getFillingPersonFirstName());
		cahpsSurvey.setFillingPersonLname(surveySaveBean.getFillingPersonLastName());
		cahpsSurvey.setFillingPersonRelationship(surveySaveBean.getFillingPersonRelationship());
		cahpsSurvey.setPatientAge(surveySaveBean.getPatientAge());
		cahpsSurvey.setPatientGender(surveySaveBean.getPatientGender());
		cahpsSurvey.setPatientId(surveySaveBean.getPatientId());
		cahpsSurvey.setPatientName(surveySaveBean.getPatientName());
		cahpsSurvey.setPatientProvider(surveySaveBean.getProviderId());
		cahpsSurvey.setPatientProviderName(surveySaveBean.getProviderName());

		PatientCahpsSurvey lastSurvey=patientCahpsSurveyRepository.saveAndFlush(cahpsSurvey);
				
		for(int i=0;i<surveySaveBean.getSurveyAnswers().size();i++){
			
			PatientSurveyAnswers answer=new PatientSurveyAnswers();
			answer.setPatientSurveyAnswersChoiceId(surveySaveBean.getSurveyAnswers().get(i).getPatientSurveyAnswersChoiceId());
			answer.setPatientSurveyAnswersQuestionId(surveySaveBean.getSurveyAnswers().get(i).getPatientSurveyAnswersQuestionId());
			answer.setPatientSurveyAnswersTextAnswer(surveySaveBean.getSurveyAnswers().get(i).getPatientSurveyAnswersTextAnswer());
			answer.setSurveyId(Integer.parseInt(String.valueOf(lastSurvey.getSurveyId())));
			
			patientSurveyAnswersRepository.saveAndFlush(answer);
		}
		
		
		/*H810 paymentAlertCategory=h810Respository.findOne(PortalSettingsSpecification.getPortalAlertCategory(9));
		boolean sendToAll =paymentAlertCategory.getH810005();  
        int provider = Integer.parseInt(paymentAlertCategory.getH810003());
        int forwardTo = Integer.parseInt(paymentAlertCategory.getH810004());
        
		AlertEvent alert=new AlertEvent();
		alert.setAlertEventCategoryId(75);
		alert.setAlertEventStatus(1);
		alert.setAlertEventPatientId(surveySaveBean.getPatientId());
		alert.setAlertEventPatientName(surveySaveBean.getPatientName());
		alert.setAlertEventEncounterId(-1);
		alert.setAlertEventRefId(lastSurvey.getSurveyId());
		alert.setAlertEventMessage("CAHPS Survey from Patient Portal.");
		alert.setAlertEventRoomId(-1);
		alert.setAlertEventCreatedDate(new Timestamp(new Date().getTime()));
		alert.setAlertEventModifiedby(-100);
		alert.setAlertEventFrom(-100);
		
		
		if(provider==0 && forwardTo==0)
			alert.setAlertEventTo(-1);
        else {
          if(sendToAll){
         	 if(forwardTo!=provider){
         		alert.setAlertEventTo(forwardTo);
         		AlertEvent alert2=alert;
         		alert2.setAlertEventTo(provider);
         		alertEventRepository.saveAndFlush(alert2);
         	 } else {
         		alert.setAlertEventTo(forwardTo);
         	 }            	 
          }else{
         	 if(forwardTo!=0){
         		alert.setAlertEventTo(forwardTo);
         	 } else {
         		alert.setAlertEventTo(provider);
         	}
          }
        }
		
		alertEventRepository.saveAndFlush(alert);*/
		
		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.CREATE,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Patient with id "+surveySaveBean.getPatientId()+" performed a CAHPS Survey.",-1,
				request.getRemoteAddr(),surveySaveBean.getPatientId(),"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with id "+surveySaveBean.getPatientId()+" performed a CAHPS Survey.","");

		return lastSurvey;
	}

	public int getLastSurveyId(int patientId) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery();
		Root<PatientCahpsSurvey> root = cq.from(PatientCahpsSurvey.class);
		cq.select(cb.max(root.get(PatientCahpsSurvey_.surveyId))).where(cb.equal(root.get(PatientCahpsSurvey_.patientId), patientId));
		int surveyId=(int) em.createQuery(cq).getSingleResult();
		
		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Request last survey Id of patient with Id: "+patientId,-1,
				request.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Requested last survey Id of patient with Id: "+patientId,"");

		
		return surveyId;
	}

	/*public Long getPatientEncounterProvidersList(int patientId, int chartId) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery();
		Root<EmployeeProfile> root = cq.from(EmployeeProfile.class);
		cq.select().where(PortalMedicalSummarySpecification.getPatientEncounterProvidersList(patientId, chartId)).;
		Long surveyId=(Long) em.createQuery(cq).getSingleResult();
		return surveyId;
	}*/

}
