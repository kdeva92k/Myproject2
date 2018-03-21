package com.glenwood.glaceemr.server.application.models;

public class PatientFeedbackAnswersSaveBean {
	
	
	private Integer feedbackAnswersId;

	private Integer feedbackAnswersQuestionId;

	private Integer feedbackAnswersChoiceId;

	private String feedbackAnswersTextAnswer;
	
	private String feedbackAnswersType;

	public Integer getFeedbackAnswersId() {
		return feedbackAnswersId;
	}

	public void setFeedbackAnswersId(Integer feedbackAnswersId) {
		this.feedbackAnswersId = feedbackAnswersId;
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

	public String getFeedbackAnswersType() {
		return feedbackAnswersType;
	}

	public void setFeedbackAnswersType(String feedbackAnswersType) {
		this.feedbackAnswersType = feedbackAnswersType;
	}
	

}
