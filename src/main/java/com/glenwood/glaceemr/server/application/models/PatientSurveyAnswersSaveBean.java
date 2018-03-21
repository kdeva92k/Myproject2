package com.glenwood.glaceemr.server.application.models;

public class PatientSurveyAnswersSaveBean {

	private Integer patientSurveyAnswersId;

	private Integer surveyId;

	private Integer patientSurveyAnswersQuestionId;

	private Integer patientSurveyAnswersChoiceId;

	private String patientSurveyAnswersTextAnswer;
	
	private String patientySurveyAnswerType;

	public Integer getPatientSurveyAnswersId() {
		return patientSurveyAnswersId;
	}

	public void setPatientSurveyAnswersId(Integer patientSurveyAnswersId) {
		this.patientSurveyAnswersId = patientSurveyAnswersId;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public Integer getPatientSurveyAnswersQuestionId() {
		return patientSurveyAnswersQuestionId;
	}

	public void setPatientSurveyAnswersQuestionId(
			Integer patientSurveyAnswersQuestionId) {
		this.patientSurveyAnswersQuestionId = patientSurveyAnswersQuestionId;
	}

	public Integer getPatientSurveyAnswersChoiceId() {
		return patientSurveyAnswersChoiceId;
	}

	public void setPatientSurveyAnswersChoiceId(Integer patientSurveyAnswersChoiceId) {
		this.patientSurveyAnswersChoiceId = patientSurveyAnswersChoiceId;
	}

	public String getPatientSurveyAnswersTextAnswer() {
		return patientSurveyAnswersTextAnswer;
	}

	public void setPatientSurveyAnswersTextAnswer(
			String patientSurveyAnswersTextAnswer) {
		this.patientSurveyAnswersTextAnswer = patientSurveyAnswersTextAnswer;
	}

	public String getPatientySurveyAnswerType() {
		return patientySurveyAnswerType;
	}

	public void setPatientySurveyAnswerType(String patientySurveyAnswerType) {
		this.patientySurveyAnswerType = patientySurveyAnswerType;
	}
	
}
