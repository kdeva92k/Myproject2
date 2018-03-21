package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "cahps_questionnaire_choice")
public class CahpsQuestionnaireChoice implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cahps_questionnaire_choice_idseq")
	@SequenceGenerator(name ="cahps_questionnaire_choice_idseq", sequenceName="cahps_questionnaire_choice_idseq", allocationSize=1)
	@Column(name="cahps_questionnaire_choice_id")
	private Integer cahpsQuestionnaireChoiceId;

	@Column(name="cahps_questionnaire_choice_question_id")
	private Integer cahpsQuestionnaireChoiceQuestionId;

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
	@JoinColumn(name="cahps_questionnaire_choice_question_id",referencedColumnName="question_id",insertable=false,updatable=false)
	CahpsQuestionnaire cahpsQuestionnaire;

	public Integer getCahpsQuestionnaireChoiceId() {
		return cahpsQuestionnaireChoiceId;
	}

	public void setCahpsQuestionnaireChoiceId(Integer cahpsQuestionnaireChoiceId) {
		this.cahpsQuestionnaireChoiceId = cahpsQuestionnaireChoiceId;
	}

	public Integer getCahpsQuestionnaireChoiceQuestionId() {
		return cahpsQuestionnaireChoiceQuestionId;
	}

	public void setCahpsQuestionnaireChoiceQuestionId(
			Integer cahpsQuestionnaireChoiceQuestionId) {
		this.cahpsQuestionnaireChoiceQuestionId = cahpsQuestionnaireChoiceQuestionId;
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

	public CahpsQuestionnaire getCahpsQuestionnaire() {
		return cahpsQuestionnaire;
	}

	public void setCahpsQuestionnaire(CahpsQuestionnaire cahpsQuestionnaire) {
		this.cahpsQuestionnaire = cahpsQuestionnaire;
	}
	
}