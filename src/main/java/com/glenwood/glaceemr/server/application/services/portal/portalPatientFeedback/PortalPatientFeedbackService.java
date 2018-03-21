package com.glenwood.glaceemr.server.application.services.portal.portalPatientFeedback;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.PatientFeedback;
import com.glenwood.glaceemr.server.application.models.PatientFeedbackQuestionnaire;
import com.glenwood.glaceemr.server.application.models.PatientFeedbackSaveBean;

public interface PortalPatientFeedbackService {

	List<PatientFeedbackQuestionnaire> getPatientFeedbackSurveyQuestionnaire();
	
	PatientFeedback savePatientFeedback(PatientFeedbackSaveBean feedbackSaveBean);
}
