package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "clinical_elements_questions")
public class ClinicalElementsQuestions implements Serializable{

	@Id
	@Column(name="clinical_elements_questions_id")
	private Integer clinicalElementsQuestionsId;

	@Column(name="clinical_elements_questions_gwid")
	private String clinicalElementsQuestionsGwid;

	@Column(name="clinical_elements_questions_caption")
	private String clinicalElementsQuestionsCaption;

	@Column(name="clinical_elements_questions_datatype")
	private Integer clinicalElementsQuestionsDatatype;

	@Column(name="clinical_elements_questions_printtext")
	private String clinicalElementsQuestionsPrinttext;

	@Column(name="clinical_elements_questions_isactive")
	private Boolean clinicalElementsQuestionsIsactive;

	@Column(name="clinical_elements_questions_savegwid")
	private String clinicalElementsQuestionsSavegwid;
	
	@OneToMany(mappedBy="clinialELementsQuestionMapping", fetch=FetchType.LAZY)
	@JsonManagedReference
	Set<GroupQuestionsMapping> groupQuestionsMappingList; 
	
	@OneToMany(mappedBy="clinicalElementsQuestions", fetch=FetchType.LAZY)
	@JsonManagedReference
	Set<ClinicalElementsQuestionsOptions> clinicalElementsQuestionsOptions; 

	public Integer getClinicalElementsQuestionsId() {
		return clinicalElementsQuestionsId;
	}

	public void setClinicalElementsQuestionsId(Integer clinicalElementsQuestionsId) {
		this.clinicalElementsQuestionsId = clinicalElementsQuestionsId;
	}

	public String getClinicalElementsQuestionsGwid() {
		return clinicalElementsQuestionsGwid;
	}

	public void setClinicalElementsQuestionsGwid(
			String clinicalElementsQuestionsGwid) {
		this.clinicalElementsQuestionsGwid = clinicalElementsQuestionsGwid;
	}

	public String getClinicalElementsQuestionsCaption() {
		return clinicalElementsQuestionsCaption;
	}

	public void setClinicalElementsQuestionsCaption(
			String clinicalElementsQuestionsCaption) {
		this.clinicalElementsQuestionsCaption = clinicalElementsQuestionsCaption;
	}

	public Integer getClinicalElementsQuestionsDatatype() {
		return clinicalElementsQuestionsDatatype;
	}

	public void setClinicalElementsQuestionsDatatype(
			Integer clinicalElementsQuestionsDatatype) {
		this.clinicalElementsQuestionsDatatype = clinicalElementsQuestionsDatatype;
	}

	public String getClinicalElementsQuestionsPrinttext() {
		return clinicalElementsQuestionsPrinttext;
	}

	public void setClinicalElementsQuestionsPrinttext(
			String clinicalElementsQuestionsPrinttext) {
		this.clinicalElementsQuestionsPrinttext = clinicalElementsQuestionsPrinttext;
	}

	public Boolean getClinicalElementsQuestionsIsactive() {
		return clinicalElementsQuestionsIsactive;
	}

	public void setClinicalElementsQuestionsIsactive(
			Boolean clinicalElementsQuestionsIsactive) {
		this.clinicalElementsQuestionsIsactive = clinicalElementsQuestionsIsactive;
	}

	public String getClinicalElementsQuestionsSavegwid() {
		return clinicalElementsQuestionsSavegwid;
	}

	public void setClinicalElementsQuestionsSavegwid(
			String clinicalElementsQuestionsSavegwid) {
		this.clinicalElementsQuestionsSavegwid = clinicalElementsQuestionsSavegwid;
	}

	public Set<GroupQuestionsMapping> getGroupQuestionsMappingList() {
		return groupQuestionsMappingList;
	}

	public void setGroupQuestionsMappingList(
			Set<GroupQuestionsMapping> groupQuestionsMappingList) {
		this.groupQuestionsMappingList = groupQuestionsMappingList;
	}

	public List<ClinicalElementsQuestionsOptions> getClinicalElementsQuestionsOptions() {
		List list=new ArrayList<ClinicalElementsQuestionsOptions>(clinicalElementsQuestionsOptions);
		return list;
	}

	public void setClinicalElementsQuestionsOptions(
			Set<ClinicalElementsQuestionsOptions> clinicalElementsQuestionsOptions) {
		this.clinicalElementsQuestionsOptions = clinicalElementsQuestionsOptions;
	}
	
	
}