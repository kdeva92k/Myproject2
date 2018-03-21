package com.glenwood.glaceemr.server.application.services.portal.portalCahpsSurvey;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.CahpsQuestionnaire;
import com.glenwood.glaceemr.server.application.models.PatientCahpsSurvey;
import com.glenwood.glaceemr.server.application.models.PatientSurveySaveBean;
import com.glenwood.glaceemr.server.application.models.PortalCahpsQuestionnaireBean;
import com.glenwood.glaceemr.server.application.models.PortalPatientCahpsSurveyBean;

public interface PortalCahpsSurveyService {

	
	List<CahpsQuestionnaire> getPatientCahpsSurveyQuestionnaire(int patientAge);

	PatientCahpsSurvey saveCahpsSurvey(PatientSurveySaveBean surveySaveBean);
}
