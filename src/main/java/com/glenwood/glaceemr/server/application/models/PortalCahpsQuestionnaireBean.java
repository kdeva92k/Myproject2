package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class PortalCahpsQuestionnaireBean {

	List<CahpsQuestionnaire> questionnaireList;
	
	Long surveyId;
	
	int patientId;

	public List<CahpsQuestionnaire> getQuestionnaireList() {
		return questionnaireList;
	}

	public void setQuestionnaireList(List<CahpsQuestionnaire> questionnaireList) {
		this.questionnaireList = questionnaireList;
	}

	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
}
