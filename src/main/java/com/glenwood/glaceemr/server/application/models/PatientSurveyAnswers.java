package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "patient_survey_answers")
public class PatientSurveyAnswers {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_survey_answers_idseq")
	@SequenceGenerator(name ="patient_survey_answers_idseq", sequenceName="patient_survey_answers_idseq", allocationSize=1)
	@Column(name="patient_survey_answers_id")
	private Integer patientSurveyAnswersId;

	@Column(name="survey_id")
	private Integer surveyId;

	@Column(name="patient_survey_answers_question_id")
	private Integer patientSurveyAnswersQuestionId;

	@Column(name="patient_survey_answers_choice_id")
	private Integer patientSurveyAnswersChoiceId;

	@Column(name="patient_survey_answers_text_answer")
	private String patientSurveyAnswersTextAnswer;

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
	
}