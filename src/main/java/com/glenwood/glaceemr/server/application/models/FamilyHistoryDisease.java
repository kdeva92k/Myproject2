package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "family_history_disease")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FamilyHistoryDisease {

	@Id
	@Column(name="family_history_disease_id")
	private Integer familyHistoryDiseaseId;

	@Column(name="family_history_disease_name")
	private String familyHistoryDiseaseName;

	@Column(name="family_history_disease_orderby")
	private Integer familyHistoryDiseaseOrderby;

	@Column(name="family_history_disease_isactive")
	private Boolean familyHistoryDiseaseIsactive;

	@Column(name="family_history_disease_system_id")
	private Integer familyHistoryDiseaseSystemId;

	@Column(name="family_history_disease_printtext")
	private String familyHistoryDiseasePrinttext;

	@Column(name="family_history_disease_snomed")
	private String familyHistoryDiseaseSnomed;
	
	@OneToMany(mappedBy="familyHistoryDisease")
	List<FamilyHistoryElement> familyHistoryElement;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="family_history_disease_system_id", referencedColumnName="clinical_system_hx_gwid", insertable=false, updatable=false)
	@JsonManagedReference
	ClinicalSystem clinicalSystem;
	

	public Integer getFamilyHistoryDiseaseId() {
		return familyHistoryDiseaseId;
	}

	public String getFamilyHistoryDiseaseName() {
		return familyHistoryDiseaseName;
	}

	public Integer getFamilyHistoryDiseaseOrderby() {
		return familyHistoryDiseaseOrderby;
	}

	public Boolean getFamilyHistoryDiseaseIsactive() {
		return familyHistoryDiseaseIsactive;
	}

	public Integer getFamilyHistoryDiseaseSystemId() {
		return familyHistoryDiseaseSystemId;
	}

	public String getFamilyHistoryDiseasePrinttext() {
		return familyHistoryDiseasePrinttext;
	}

	public String getFamilyHistoryDiseaseSnomed() {
		return familyHistoryDiseaseSnomed;
	}

	public List<FamilyHistoryElement> getFamilyHistoryElement() {
		return familyHistoryElement;
	}

	public void setFamilyHistoryDiseaseId(Integer familyHistoryDiseaseId) {
		this.familyHistoryDiseaseId = familyHistoryDiseaseId;
	}

	public void setFamilyHistoryDiseaseName(String familyHistoryDiseaseName) {
		this.familyHistoryDiseaseName = familyHistoryDiseaseName;
	}

	public void setFamilyHistoryDiseaseOrderby(Integer familyHistoryDiseaseOrderby) {
		this.familyHistoryDiseaseOrderby = familyHistoryDiseaseOrderby;
	}

	public void setFamilyHistoryDiseaseIsactive(Boolean familyHistoryDiseaseIsactive) {
		this.familyHistoryDiseaseIsactive = familyHistoryDiseaseIsactive;
	}

	public void setFamilyHistoryDiseaseSystemId(Integer familyHistoryDiseaseSystemId) {
		this.familyHistoryDiseaseSystemId = familyHistoryDiseaseSystemId;
	}

	public void setFamilyHistoryDiseasePrinttext(
			String familyHistoryDiseasePrinttext) {
		this.familyHistoryDiseasePrinttext = familyHistoryDiseasePrinttext;
	}

	public void setFamilyHistoryDiseaseSnomed(String familyHistoryDiseaseSnomed) {
		this.familyHistoryDiseaseSnomed = familyHistoryDiseaseSnomed;
	}

	public void setFamilyHistoryElement(
			List<FamilyHistoryElement> familyHistoryElement) {
		this.familyHistoryElement = familyHistoryElement;
	}
	
	
}