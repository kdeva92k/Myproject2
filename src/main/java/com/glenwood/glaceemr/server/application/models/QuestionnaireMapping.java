package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "questionnaire_mapping")
public class QuestionnaireMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="questionnaire_mapping_idseq")
	@SequenceGenerator(name ="questionnaire_mapping_idseq", sequenceName="questionnaire_mapping_idseq", allocationSize=1)
	@Column(name="questionnaire_mapping_id")
	private Integer questionnaireMappingId;

	@Column(name="questionnaire_mapping_question_id")
	private Integer questionnaireMappingQuestionId;

	@Column(name="mapping_sequence")
	private Integer mappingSequence;

	@Column(name="questionnaire_group")
	private String questionnaireGroup;

	public Integer getQuestionnaireMappingId() {
		return questionnaireMappingId;
	}

	public void setQuestionnaireMappingId(Integer questionnaireMappingId) {
		this.questionnaireMappingId = questionnaireMappingId;
	}

	public Integer getQuestionnaireMappingQuestionId() {
		return questionnaireMappingQuestionId;
	}

	public void setQuestionnaireMappingQuestionId(
			Integer questionnaireMappingQuestionId) {
		this.questionnaireMappingQuestionId = questionnaireMappingQuestionId;
	}

	public Integer getMappingSequence() {
		return mappingSequence;
	}

	public void setMappingSequence(Integer mappingSequence) {
		this.mappingSequence = mappingSequence;
	}

	public String getQuestionnaireGroup() {
		return questionnaireGroup;
	}

	public void setQuestionnaireGroup(String questionnaireGroup) {
		this.questionnaireGroup = questionnaireGroup;
	}
	
	
}