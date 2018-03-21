package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "vaccination_consent_form")
public class VaccinationConsentForm {

	@SequenceGenerator(name = "sequence", sequenceName = "vaccination_consent_form_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
	@Column(name="vaccination_consent_form_id")
	private Integer vaccinationConsentFormId;

	@Id
	@Column(name="vaccination_consent_form_testdetail_id")
	private Integer vaccinationConsentFormTestdetailId;

	@Column(name="vaccination_consent_form_image_path")
	private String vaccinationConsentFormImagePath;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="vaccination_consent_form_signed_date")
	private Timestamp vaccinationConsentFormSignedDate;

	@Column(name="vaccination_consent_form_user_id")
	private Integer vaccinationConsentFormUserId;

	@Column(name="vaccination_consent_form_created_by")
	private Integer vaccinationConsentFormCreatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="vaccination_consent_form_created_date")
	private Timestamp vaccinationConsentFormCreatedDate;

	@Column(name="vaccination_consent_form_isactive")
	private Boolean vaccinationConsentFormIsactive;

	@Column(name="vaccination_consent_form_signed_user")
	private String vaccinationConsentFormSignedUser;

	@Column(name="vaccination_consent_form_isgiven")
	private Boolean vaccinationConsentFormIsgiven;

	@Column(name="vaccination_consent_form_imgcontent")
	private String vaccinationConsentFormImgcontent;

	@OneToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="vaccination_consent_form_testdetail_id", referencedColumnName="lab_entries_testdetail_id" , insertable=false, updatable=false)
	private LabEntries labEntries;
	
	public Integer getVaccinationConsentFormId() {
		return vaccinationConsentFormId;
	}

	public void setVaccinationConsentFormId(Integer vaccinationConsentFormId) {
		this.vaccinationConsentFormId = vaccinationConsentFormId;
	}

	public Integer getVaccinationConsentFormTestdetailId() {
		return vaccinationConsentFormTestdetailId;
	}

	public void setVaccinationConsentFormTestdetailId(
			Integer vaccinationConsentFormTestdetailId) {
		this.vaccinationConsentFormTestdetailId = vaccinationConsentFormTestdetailId;
	}

	public String getVaccinationConsentFormImagePath() {
		return vaccinationConsentFormImagePath;
	}

	public void setVaccinationConsentFormImagePath(
			String vaccinationConsentFormImagePath) {
		this.vaccinationConsentFormImagePath = vaccinationConsentFormImagePath;
	}

	public Timestamp getVaccinationConsentFormSignedDate() {
		return vaccinationConsentFormSignedDate;
	}

	public void setVaccinationConsentFormSignedDate(
			Timestamp vaccinationConsentFormSignedDate) {
		this.vaccinationConsentFormSignedDate = vaccinationConsentFormSignedDate;
	}

	public Integer getVaccinationConsentFormUserId() {
		return vaccinationConsentFormUserId;
	}

	public void setVaccinationConsentFormUserId(Integer vaccinationConsentFormUserId) {
		this.vaccinationConsentFormUserId = vaccinationConsentFormUserId;
	}

	public Integer getVaccinationConsentFormCreatedBy() {
		return vaccinationConsentFormCreatedBy;
	}

	public void setVaccinationConsentFormCreatedBy(
			Integer vaccinationConsentFormCreatedBy) {
		this.vaccinationConsentFormCreatedBy = vaccinationConsentFormCreatedBy;
	}

	public Timestamp getVaccinationConsentFormCreatedDate() {
		return vaccinationConsentFormCreatedDate;
	}

	public void setVaccinationConsentFormCreatedDate(
			Timestamp vaccinationConsentFormCreatedDate) {
		this.vaccinationConsentFormCreatedDate = vaccinationConsentFormCreatedDate;
	}

	public Boolean getVaccinationConsentFormIsactive() {
		return vaccinationConsentFormIsactive;
	}

	public void setVaccinationConsentFormIsactive(
			Boolean vaccinationConsentFormIsactive) {
		this.vaccinationConsentFormIsactive = vaccinationConsentFormIsactive;
	}

	public String getVaccinationConsentFormSignedUser() {
		return vaccinationConsentFormSignedUser;
	}

	public void setVaccinationConsentFormSignedUser(
			String vaccinationConsentFormSignedUser) {
		this.vaccinationConsentFormSignedUser = vaccinationConsentFormSignedUser;
	}

	public Boolean getVaccinationConsentFormIsgiven() {
		return vaccinationConsentFormIsgiven;
	}

	public void setVaccinationConsentFormIsgiven(
			Boolean vaccinationConsentFormIsgiven) {
		this.vaccinationConsentFormIsgiven = vaccinationConsentFormIsgiven;
	}

	public String getVaccinationConsentFormImgcontent() {
		return vaccinationConsentFormImgcontent;
	}

	public void setVaccinationConsentFormImgcontent(
			String vaccinationConsentFormImgcontent) {
		this.vaccinationConsentFormImgcontent = vaccinationConsentFormImgcontent;
	}
}