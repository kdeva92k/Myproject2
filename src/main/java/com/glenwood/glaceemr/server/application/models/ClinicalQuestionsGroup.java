package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clinical_questions_group")
public class ClinicalQuestionsGroup {

	@Id
	@Column(name="clinical_questions_group_id")
	private Integer clinicalQuestionsGroupId;

	@Column(name="clinical_questions_group_name")
	private String clinicalQuestionsGroupName;

	@Column(name="clinical_questions_group_type")
	private Integer clinicalQuestionsGroupType;

	@Column(name="clinical_questions_group_page_size")
	private Integer clinicalQuestionsGroupPageSize;

	@Column(name="clinical_questions_group_isactive")
	private Boolean clinicalQuestionsGroupIsactive;

	@Column(name="clinical_questions_group_isxml")
	private Integer clinicalQuestionsGroupIsxml;

	@Column(name="clinical_questions_group_tabid")
	private Integer clinicalQuestionsGroupTabid;
	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="clinical_questions_group_id", referencedColumnName="group_questions_mapping_group_id", insertable=false, updatable=false)
	private GroupQuestionsMapping clinicalQuestionsGroupMapping;*/

	public Integer getClinicalQuestionsGroupId() {
		return clinicalQuestionsGroupId;
	}

	public void setClinicalQuestionsGroupId(Integer clinicalQuestionsGroupId) {
		this.clinicalQuestionsGroupId = clinicalQuestionsGroupId;
	}

	public String getClinicalQuestionsGroupName() {
		return clinicalQuestionsGroupName;
	}

	public void setClinicalQuestionsGroupName(String clinicalQuestionsGroupName) {
		this.clinicalQuestionsGroupName = clinicalQuestionsGroupName;
	}

	public Integer getClinicalQuestionsGroupType() {
		return clinicalQuestionsGroupType;
	}

	public void setClinicalQuestionsGroupType(Integer clinicalQuestionsGroupType) {
		this.clinicalQuestionsGroupType = clinicalQuestionsGroupType;
	}

	public Integer getClinicalQuestionsGroupPageSize() {
		return clinicalQuestionsGroupPageSize;
	}

	public void setClinicalQuestionsGroupPageSize(
			Integer clinicalQuestionsGroupPageSize) {
		this.clinicalQuestionsGroupPageSize = clinicalQuestionsGroupPageSize;
	}

	public Boolean getClinicalQuestionsGroupIsactive() {
		return clinicalQuestionsGroupIsactive;
	}

	public void setClinicalQuestionsGroupIsactive(
			Boolean clinicalQuestionsGroupIsactive) {
		this.clinicalQuestionsGroupIsactive = clinicalQuestionsGroupIsactive;
	}

	public Integer getClinicalQuestionsGroupIsxml() {
		return clinicalQuestionsGroupIsxml;
	}

	public void setClinicalQuestionsGroupIsxml(Integer clinicalQuestionsGroupIsxml) {
		this.clinicalQuestionsGroupIsxml = clinicalQuestionsGroupIsxml;
	}

	public Integer getClinicalQuestionsGroupTabid() {
		return clinicalQuestionsGroupTabid;
	}

	public void setClinicalQuestionsGroupTabid(Integer clinicalQuestionsGroupTabid) {
		this.clinicalQuestionsGroupTabid = clinicalQuestionsGroupTabid;
	}

	/*public GroupQuestionsMapping getClinicalQuestionsGroupMapping() {
		return clinicalQuestionsGroupMapping;
	}

	public void setClinicalQuestionsGroupMapping(
			GroupQuestionsMapping clinicalQuestionsGroupMapping) {
		this.clinicalQuestionsGroupMapping = clinicalQuestionsGroupMapping;
	}*/
	
}