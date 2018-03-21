package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "clinical_elements_questions_options")
public class ClinicalElementsQuestionsOptions {

	@Id
	@Column(name="clinical_elements_questions_options_id")
	private Integer clinicalElementsQuestionsOptionsId;

	@Column(name="clinical_elements_questions_options_gwid")
	private String clinicalElementsQuestionsOptionsGwid;

	@Column(name="clinical_elements_questions_options_value")
	private String clinicalElementsQuestionsOptionsValue;

	@Column(name="clinical_elements_questions_options_name")
	private String clinicalElementsQuestionsOptionsName;

	@Column(name="clinical_elements_questions_options_isactive")
	private Boolean clinicalElementsQuestionsOptionsIsactive;

	@Column(name="clinical_elements_questions_options_selectbox")
	private Integer clinicalElementsQuestionsOptionsSelectbox;

	@Column(name="clinical_elements_questions_options_datatype")
	private Integer clinicalElementsQuestionsOptionsDatatype;

	@Column(name="clinical_elements_questions_options_savegwid")
	private String clinicalElementsQuestionsOptionsSavegwid;

	@Column(name="clinical_elements_questions_options_isboolean")
	private Boolean clinicalElementsQuestionsOptionsIsboolean;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="clinical_elements_questions_options_gwid", referencedColumnName="clinical_elements_options_gwid", insertable=false, updatable=false)
	ClinicalElementsOptions clinicalElementsOptions;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="clinical_elements_questions_options_savegwid", referencedColumnName="clinical_elements_gwid", insertable=false, updatable=false)
	ClinicalElements clinicalElements; 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="clinical_elements_questions_options_gwid", referencedColumnName="clinical_elements_questions_gwid", updatable=false, insertable=false)
	ClinicalElementsQuestions clinicalElementsQuestions; 	

	public Integer getClinicalElementsQuestionsOptionsId() {
		return clinicalElementsQuestionsOptionsId;
	}

	public void setClinicalElementsQuestionsOptionsId(
			Integer clinicalElementsQuestionsOptionsId) {
		this.clinicalElementsQuestionsOptionsId = clinicalElementsQuestionsOptionsId;
	}

	public String getClinicalElementsQuestionsOptionsGwid() {
		return clinicalElementsQuestionsOptionsGwid;
	}

	public void setClinicalElementsQuestionsOptionsGwid(
			String clinicalElementsQuestionsOptionsGwid) {
		this.clinicalElementsQuestionsOptionsGwid = clinicalElementsQuestionsOptionsGwid;
	}

	public String getClinicalElementsQuestionsOptionsValue() {
		return clinicalElementsQuestionsOptionsValue;
	}

	public void setClinicalElementsQuestionsOptionsValue(
			String clinicalElementsQuestionsOptionsValue) {
		this.clinicalElementsQuestionsOptionsValue = clinicalElementsQuestionsOptionsValue;
	}

	public String getClinicalElementsQuestionsOptionsName() {
		return clinicalElementsQuestionsOptionsName;
	}

	public void setClinicalElementsQuestionsOptionsName(
			String clinicalElementsQuestionsOptionsName) {
		this.clinicalElementsQuestionsOptionsName = clinicalElementsQuestionsOptionsName;
	}

	public Boolean getClinicalElementsQuestionsOptionsIsactive() {
		return clinicalElementsQuestionsOptionsIsactive;
	}

	public void setClinicalElementsQuestionsOptionsIsactive(
			Boolean clinicalElementsQuestionsOptionsIsactive) {
		this.clinicalElementsQuestionsOptionsIsactive = clinicalElementsQuestionsOptionsIsactive;
	}

	public Integer getClinicalElementsQuestionsOptionsSelectbox() {
		return clinicalElementsQuestionsOptionsSelectbox;
	}

	public void setClinicalElementsQuestionsOptionsSelectbox(
			Integer clinicalElementsQuestionsOptionsSelectbox) {
		this.clinicalElementsQuestionsOptionsSelectbox = clinicalElementsQuestionsOptionsSelectbox;
	}

	public Integer getClinicalElementsQuestionsOptionsDatatype() {
		return clinicalElementsQuestionsOptionsDatatype;
	}

	public void setClinicalElementsQuestionsOptionsDatatype(
			Integer clinicalElementsQuestionsOptionsDatatype) {
		this.clinicalElementsQuestionsOptionsDatatype = clinicalElementsQuestionsOptionsDatatype;
	}

	public String getClinicalElementsQuestionsOptionsSavegwid() {
		return clinicalElementsQuestionsOptionsSavegwid;
	}

	public void setClinicalElementsQuestionsOptionsSavegwid(
			String clinicalElementsQuestionsOptionsSavegwid) {
		this.clinicalElementsQuestionsOptionsSavegwid = clinicalElementsQuestionsOptionsSavegwid;
	}

	public Boolean getClinicalElementsQuestionsOptionsIsboolean() {
		return clinicalElementsQuestionsOptionsIsboolean;
	}

	public void setClinicalElementsQuestionsOptionsIsboolean(
			Boolean clinicalElementsQuestionsOptionsIsboolean) {
		this.clinicalElementsQuestionsOptionsIsboolean = clinicalElementsQuestionsOptionsIsboolean;
	}

	public ClinicalElementsOptions getClinicalElementsOptions() {
		return clinicalElementsOptions;
	}

	public void setClinicalElementsOptions(
			ClinicalElementsOptions clinicalElementsOptions) {
		this.clinicalElementsOptions = clinicalElementsOptions;
	}

	public ClinicalElements getClinicalElements() {
		return clinicalElements;
	}

	public void setClinicalElements(ClinicalElements clinicalElements) {
		this.clinicalElements = clinicalElements;
	}

	public ClinicalElementsQuestions getClinicalElementsQuestions() {
		return clinicalElementsQuestions;
	}

	public void setClinicalElementsQuestions(
			ClinicalElementsQuestions clinicalElementsQuestions) {
		this.clinicalElementsQuestions = clinicalElementsQuestions;
	}
	
}