package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "patient_feedback_questionnaire")
public class PatientFeedbackQuestionnaire {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_feedback_questionnaire_idseq")
	@SequenceGenerator(name ="patient_feedback_questionnaire_idseq", sequenceName="patient_feedback_questionnaire_idseq", allocationSize=1)
	@Column(name="feedback_questionnaire_id")
	private Integer feedbackQuestionnaireId;

	@Id
	@Column(name="question_id")
	private Integer questionId;

	@Column(name="question_caption")
	private String questionCaption;

	@Column(name="version_no")
	private Double versionNo;

	@Column(name="default_variable")
	private String defaultVariable;

	@Column(name="feedback_questionnaire_group_id")
	private Integer feedbackQuestionnaireGroupId;

	@Column(name="feedback_questionnaire_group_description")
	private String feedbackQuestionnaireGroupDescription;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="patientFeedbackQuestionnaire")
	@JsonManagedReference
	List<PatientFeedbackQuestionnaireChoice> patientFeedbackQuestionnaireChoiceList;
	

	public Integer getFeedbackQuestionnaireId() {
		return feedbackQuestionnaireId;
	}

	public void setFeedbackQuestionnaireId(Integer feedbackQuestionnaireId) {
		this.feedbackQuestionnaireId = feedbackQuestionnaireId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestionCaption() {
		return questionCaption;
	}

	public void setQuestionCaption(String questionCaption) {
		this.questionCaption = questionCaption;
	}

	public Double getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Double versionNo) {
		this.versionNo = versionNo;
	}

	public String getDefaultVariable() {
		return defaultVariable;
	}

	public void setDefaultVariable(String defaultVariable) {
		this.defaultVariable = defaultVariable;
	}

	public Integer getFeedbackQuestionnaireGroupId() {
		return feedbackQuestionnaireGroupId;
	}

	public void setFeedbackQuestionnaireGroupId(Integer feedbackQuestionnaireGroupId) {
		this.feedbackQuestionnaireGroupId = feedbackQuestionnaireGroupId;
	}

	public String getFeedbackQuestionnaireGroupDescription() {
		return feedbackQuestionnaireGroupDescription;
	}

	public void setFeedbackQuestionnaireGroupDescription(
			String feedbackQuestionnaireGroupDescription) {
		this.feedbackQuestionnaireGroupDescription = feedbackQuestionnaireGroupDescription;
	}

	public List<PatientFeedbackQuestionnaireChoice> getPatientFeedbackQuestionnaireChoiceList() {
		return patientFeedbackQuestionnaireChoiceList;
	}

	public void setPatientFeedbackQuestionnaireChoiceList(
			List<PatientFeedbackQuestionnaireChoice> patientFeedbackQuestionnaireChoiceList) {
		this.patientFeedbackQuestionnaireChoiceList = patientFeedbackQuestionnaireChoiceList;
	}
	
	
}