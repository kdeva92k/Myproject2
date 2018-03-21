package com.glenwood.glaceemr.server.application.models;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "group_questions_mapping")
public class GroupQuestionsMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="group_questions_mapping_group_questions_mapping_id_seq1")
	@SequenceGenerator(name ="group_questions_mapping_group_questions_mapping_id_seq1", sequenceName="group_questions_mapping_group_questions_mapping_id_seq1", allocationSize=1)
	@Column(name="group_questions_mapping_id")
	private Integer groupQuestionsMappingId;

	@Column(name="group_questions_mapping_group_id")
	private Integer groupQuestionsMappingGroupId;

	@Column(name="group_questions_mapping_questions_id")
	private String groupQuestionsMappingQuestionsId;

	@Column(name="group_questions_mapping_isactive")
	private Boolean groupQuestionsMappingIsactive;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="group_questions_mapping_group_id", referencedColumnName="clinical_questions_group_id", updatable=false, insertable=false)
	private ClinicalQuestionsGroup  clinialQuestionGroupMapping;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="group_questions_mapping_questions_id", referencedColumnName="clinical_elements_questions_gwid", updatable=false, insertable=false)
	private ClinicalElementsQuestions  clinialELementsQuestionMapping;

	public Integer getGroupQuestionsMappingId() {
		return groupQuestionsMappingId;
	}

	public void setGroupQuestionsMappingId(Integer groupQuestionsMappingId) {
		this.groupQuestionsMappingId = groupQuestionsMappingId;
	}

	public Integer getGroupQuestionsMappingGroupId() {
		return groupQuestionsMappingGroupId;
	}

	public void setGroupQuestionsMappingGroupId(Integer groupQuestionsMappingGroupId) {
		this.groupQuestionsMappingGroupId = groupQuestionsMappingGroupId;
	}

	public String getGroupQuestionsMappingQuestionsId() {
		return groupQuestionsMappingQuestionsId;
	}

	public void setGroupQuestionsMappingQuestionsId(
			String groupQuestionsMappingQuestionsId) {
		this.groupQuestionsMappingQuestionsId = groupQuestionsMappingQuestionsId;
	}

	public Boolean getGroupQuestionsMappingIsactive() {
		return groupQuestionsMappingIsactive;
	}

	public void setGroupQuestionsMappingIsactive(
			Boolean groupQuestionsMappingIsactive) {
		this.groupQuestionsMappingIsactive = groupQuestionsMappingIsactive;
	}

	public ClinicalQuestionsGroup getClinialQuestionGroupMapping() {
		return clinialQuestionGroupMapping;
	}

	public void setClinialQuestionGroupMapping(
			ClinicalQuestionsGroup clinialQuestionGroupMapping) {
		this.clinialQuestionGroupMapping = clinialQuestionGroupMapping;
	}

	public ClinicalElementsQuestions getClinialELementsQuestionMapping() {
		return clinialELementsQuestionMapping;
	}

	public void setClinialELementsQuestionMapping(
			ClinicalElementsQuestions clinialELementsQuestionMapping) {
		this.clinialELementsQuestionMapping = clinialELementsQuestionMapping;
	}
	
}