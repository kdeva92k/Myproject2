package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "patient_feedback_questionnaire_choice")
public class PatientFeedbackQuestionnaireChoice {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_feedback_questionnaire_choice_idseq")
	@SequenceGenerator(name ="patient_feedback_questionnaire_choice_idseq", sequenceName="patient_feedback_questionnaire_choice_idseq", allocationSize=1)
	@Column(name="feedback_questionnaire_choice_id")
	private Integer feedbackQuestionnaireChoiceId;

	@Column(name="feedback_questionnaire_choice_question_id")
	private Integer feedbackQuestionnaireChoiceQuestionId;

	@Column(name="choice_id")
	private Integer choiceId;

	@Column(name="choice_caption")
	private String choiceCaption;

	@Column(name="question_skip_to")
	private Integer questionSkipTo;

	@Column(name="choice_type")
	private String choiceType;

	@Column(name="choice_group_id")
	private Integer choiceGroupId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="feedback_questionnaire_choice_question_id",referencedColumnName="question_id",insertable=false,updatable=false)
	PatientFeedbackQuestionnaire patientFeedbackQuestionnaire;
	

	public Integer getFeedbackQuestionnaireChoiceId() {
		return feedbackQuestionnaireChoiceId;
	}

	public void setFeedbackQuestionnaireChoiceId(
			Integer feedbackQuestionnaireChoiceId) {
		this.feedbackQuestionnaireChoiceId = feedbackQuestionnaireChoiceId;
	}

	public Integer getFeedbackQuestionnaireChoiceQuestionId() {
		return feedbackQuestionnaireChoiceQuestionId;
	}

	public void setFeedbackQuestionnaireChoiceQuestionId(
			Integer feedbackQuestionnaireChoiceQuestionId) {
		this.feedbackQuestionnaireChoiceQuestionId = feedbackQuestionnaireChoiceQuestionId;
	}

	public Integer getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(Integer choiceId) {
		this.choiceId = choiceId;
	}

	public String getChoiceCaption() {
		return choiceCaption;
	}

	public void setChoiceCaption(String choiceCaption) {
		this.choiceCaption = choiceCaption;
	}

	public Integer getQuestionSkipTo() {
		return questionSkipTo;
	}

	public void setQuestionSkipTo(Integer questionSkipTo) {
		this.questionSkipTo = questionSkipTo;
	}

	public String getChoiceType() {
		return choiceType;
	}

	public void setChoiceType(String choiceType) {
		this.choiceType = choiceType;
	}

	public Integer getChoiceGroupId() {
		return choiceGroupId;
	}

	public void setChoiceGroupId(Integer choiceGroupId) {
		this.choiceGroupId = choiceGroupId;
	}

	public PatientFeedbackQuestionnaire getPatientFeedbackQuestionnaire() {
		return patientFeedbackQuestionnaire;
	}

	public void setPatientFeedbackQuestionnaire(
			PatientFeedbackQuestionnaire patientFeedbackQuestionnaire) {
		this.patientFeedbackQuestionnaire = patientFeedbackQuestionnaire;
	}
	
}