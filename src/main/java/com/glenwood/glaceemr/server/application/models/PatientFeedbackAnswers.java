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
@Table(name = "patient_feedback_answers")
public class PatientFeedbackAnswers {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_feedback_answers_idseq")
	@SequenceGenerator(name ="patient_feedback_answers_idseq", sequenceName="patient_feedback_answers_idseq", allocationSize=1)
	@Column(name="feedback_answers_id")
	private Integer feedbackAnswersId;

	@Column(name="feedback_id")
	private Integer feedbackId;

	@Column(name="feedback_answers_question_id")
	private Integer feedbackAnswersQuestionId;

	@Column(name="feedback_answers_choice_id")
	private Integer feedbackAnswersChoiceId;

	@Column(name="feedback_answers_text_answer")
	private String feedbackAnswersTextAnswer;

	public Integer getFeedbackAnswersId() {
		return feedbackAnswersId;
	}

	public void setFeedbackAnswersId(Integer feedbackAnswersId) {
		this.feedbackAnswersId = feedbackAnswersId;
	}

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public Integer getFeedbackAnswersQuestionId() {
		return feedbackAnswersQuestionId;
	}

	public void setFeedbackAnswersQuestionId(Integer feedbackAnswersQuestionId) {
		this.feedbackAnswersQuestionId = feedbackAnswersQuestionId;
	}

	public Integer getFeedbackAnswersChoiceId() {
		return feedbackAnswersChoiceId;
	}

	public void setFeedbackAnswersChoiceId(Integer feedbackAnswersChoiceId) {
		this.feedbackAnswersChoiceId = feedbackAnswersChoiceId;
	}

	public String getFeedbackAnswersTextAnswer() {
		return feedbackAnswersTextAnswer;
	}

	public void setFeedbackAnswersTextAnswer(String feedbackAnswersTextAnswer) {
		this.feedbackAnswersTextAnswer = feedbackAnswersTextAnswer;
	}
	
}