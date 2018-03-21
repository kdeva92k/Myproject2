package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "cahps_questionnaire")
public class CahpsQuestionnaire {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cahps_questionnaire_idseq")
	@SequenceGenerator(name ="cahps_questionnaire_idseq", sequenceName="cahps_questionnaire_idseq", allocationSize=1)
	@Column(name="cahps_questionnaire_id")
	private Integer cahpsQuestionnaireId;

	@Id
	@Column(name="question_id")
	private Integer questionId;

	@Column(name="question_caption")
	private String questionCaption;

	@Column(name="version_no")
	private Double versionNo;

	@Column(name="default_variable")
	private String defaultVariable;

	@Column(name="cahps_questionnaire_group_id")
	private Integer cahpsQuestionnaireGroupId;

	@Column(name="cahps_questionnaire_group_description")
	private String cahpsQuestionnaireGroupDescription;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cahpsQuestionnaire")
	@JsonManagedReference
	List<CahpsQuestionnaireChoice> cahpsQuestionnaireChoiceList;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="question_id",referencedColumnName="questionnaire_mapping_question_id",insertable=false,updatable=false)
	QuestionnaireMapping questionnaireMapping;
	
	
	public Integer getCahpsQuestionnaireId() {
		return cahpsQuestionnaireId;
	}

	public void setCahpsQuestionnaireId(Integer cahpsQuestionnaireId) {
		this.cahpsQuestionnaireId = cahpsQuestionnaireId;
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

	public Integer getCahpsQuestionnaireGroupId() {
		return cahpsQuestionnaireGroupId;
	}

	public void setCahpsQuestionnaireGroupId(Integer cahpsQuestionnaireGroupId) {
		this.cahpsQuestionnaireGroupId = cahpsQuestionnaireGroupId;
	}

	public String getCahpsQuestionnaireGroupDescription() {
		return cahpsQuestionnaireGroupDescription;
	}

	public void setCahpsQuestionnaireGroupDescription(
			String cahpsQuestionnaireGroupDescription) {
		this.cahpsQuestionnaireGroupDescription = cahpsQuestionnaireGroupDescription;
	}

	public List<CahpsQuestionnaireChoice> getCahpsQuestionnaireChoiceList() {
		return cahpsQuestionnaireChoiceList;
	}

	public void setCahpsQuestionnaireChoiceList(
			List<CahpsQuestionnaireChoice> cahpsQuestionnaireChoiceList) {
		this.cahpsQuestionnaireChoiceList = cahpsQuestionnaireChoiceList;
	}

	public QuestionnaireMapping getQuestionnaireMapping() {
		return questionnaireMapping;
	}

	public void setQuestionnaireMapping(QuestionnaireMapping questionnaireMapping) {
		this.questionnaireMapping = questionnaireMapping;
	}
	
	
}