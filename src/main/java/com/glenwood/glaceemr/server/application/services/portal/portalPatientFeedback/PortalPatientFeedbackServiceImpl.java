package com.glenwood.glaceemr.server.application.services.portal.portalPatientFeedback;

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

import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.PatientPortalAlertConfig;
import com.glenwood.glaceemr.server.application.models.PatientFeedback;
import com.glenwood.glaceemr.server.application.models.PatientFeedbackAnswers;
import com.glenwood.glaceemr.server.application.models.PatientFeedbackQuestionnaire;
import com.glenwood.glaceemr.server.application.models.PatientFeedbackSaveBean;
import com.glenwood.glaceemr.server.application.models.PatientFeedback_;
import com.glenwood.glaceemr.server.application.repositories.AlertEventRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientPortalAlertConfigRespository;
import com.glenwood.glaceemr.server.application.repositories.PatientFeedbackAnswersRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientFeedbackQuestionnaireRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientFeedbackRepository;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.specifications.PortalPatientFeedbackSpecification;
import com.glenwood.glaceemr.server.application.specifications.PortalSettingsSpecification;

@Service
public class PortalPatientFeedbackServiceImpl implements PortalPatientFeedbackService{

	@Autowired
	PatientFeedbackQuestionnaireRepository patientFeedbackQuestionnaireRepository;
	
	@Autowired
	PatientFeedbackRepository patientFeedbackRepository;
	
	@Autowired
	PatientFeedbackAnswersRepository patientFeedbackAnswersRepository;

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
	public List<PatientFeedbackQuestionnaire> getPatientFeedbackSurveyQuestionnaire() {
		
		List<PatientFeedbackQuestionnaire> questionnaireList=patientFeedbackQuestionnaireRepository.findAll();
		
		return questionnaireList;
	}


	@Override
	public PatientFeedback savePatientFeedback(PatientFeedbackSaveBean feedbackSaveBean) {
		
		
		if(feedbackSaveBean.getFeedbackAnswersList().size()<=0){
			
			return null;
		}
		
		PatientFeedback feedback=new PatientFeedback();
		
		feedback.setDateOfFeedback(new Timestamp(new Date().getTime()));
		feedback.setFillingPersonEmail(feedbackSaveBean.getFillingPersonEmail());
		feedback.setFillingPersonFname(feedbackSaveBean.getFillingPersonFname());
		feedback.setFillingPersonLname(feedbackSaveBean.getFillingPersonLname());
		feedback.setFillingPersonRelationship(feedbackSaveBean.getFillingPersonRelationship());
		feedback.setPatientId(feedbackSaveBean.getPatientId());
		feedback.setPatientName(feedbackSaveBean.getPatientName());
		feedback.setPatientProvider(feedbackSaveBean.getPatientProvider());
		
		PatientFeedback feedbackSummary=patientFeedbackRepository.saveAndFlush(feedback);
		
		int lastFeedbackId=getLastFeebackId(feedbackSaveBean.getPatientId());
		
		for(int i=0;i<feedbackSaveBean.getFeedbackAnswersList().size();i++){
			
			PatientFeedbackAnswers answer=new PatientFeedbackAnswers();
			answer.setFeedbackAnswersChoiceId(feedbackSaveBean.getFeedbackAnswersList().get(i).getFeedbackAnswersChoiceId());
			answer.setFeedbackAnswersQuestionId(feedbackSaveBean.getFeedbackAnswersList().get(i).getFeedbackAnswersQuestionId());
			answer.setFeedbackAnswersTextAnswer(feedbackSaveBean.getFeedbackAnswersList().get(i).getFeedbackAnswersTextAnswer());
			answer.setFeedbackId(lastFeedbackId);
			
			patientFeedbackAnswersRepository.saveAndFlush(answer);
		}
				
		/*H810 paymentAlertCategory=h810Respository.findOne(PortalSettingsSpecification.getPortalAlertCategory(10));
		boolean sendToAll =paymentAlertCategory.getH810005();  
        int provider = Integer.parseInt(paymentAlertCategory.getH810003());
        int forwardTo = Integer.parseInt(paymentAlertCategory.getH810004());
        
		AlertEvent alert=new AlertEvent();
		alert.setAlertEventCategoryId(76);
		alert.setAlertEventStatus(1);
		alert.setAlertEventPatientId(feedbackSaveBean.getPatientId());
		alert.setAlertEventPatientName(feedbackSaveBean.getPatientName());
		alert.setAlertEventEncounterId(-1);
		alert.setAlertEventRefId(feedbackSummary.getFeedbackId());
		alert.setAlertEventMessage("Patient Feedback from Patient Portal.");
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
				AuditTrailEnumConstants.LogActionType.CREATE,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Patient with id "+feedbackSaveBean.getPatientId()+" submitted a feedback.",-1,
				request.getRemoteAddr(),feedbackSaveBean.getPatientId(),"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with id "+feedbackSaveBean.getPatientId()+" submitted a feedback.","");
		
		return feedbackSummary;
	}
	
	public int getLastFeebackId(int patientId) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery();
		Root<PatientFeedback> root = cq.from(PatientFeedback.class);
		cq.select(cb.max(root.get(PatientFeedback_.feedbackId))).where(cb.equal(root.get(PatientFeedback_.patientId), patientId));
		int feedbackId=(int) em.createQuery(cq).getSingleResult();
		
		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Patient with id "+patientId+" requested last feedback.",-1,
				request.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with id "+patientId+" requested last feedback.","");
		
		
		return feedbackId;
	}

}
