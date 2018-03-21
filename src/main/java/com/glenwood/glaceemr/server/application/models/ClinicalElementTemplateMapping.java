package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "clinical_element_template_mapping")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClinicalElementTemplateMapping implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="clinical_element_template_mapping_id")
	private Integer clinicalElementTemplateMappingId;

	
	@Column(name="clinical_element_template_mapping_gwid")
	private String clinicalElementTemplateMappingGwid;

	@Column(name="clinical_element_template_mapping_templateid")
	private Integer clinicalElementTemplateMappingTemplateid;

	@Column(name="clinical_element_template_mapping_type")
	private Integer clinicalElementTemplateMappingType;

	@Column(name="clinical_element_template_mapping_default_value")
	private String clinicalElementTemplateMappingDefaultValue;

	@Column(name="clinical_element_template_mapping_property")
	private Integer clinicalElementTemplateMappingProperty;

	@Column(name="clinical_element_template_mapping_isactive")
	private Boolean clinicalElementTemplateMappingIsactive;

	@Column(name="clinical_element_template_mapping_orderby")
	private Integer clinicalElementTemplateMappingOrderby;

	@Column(name="clinical_element_template_mapping_isfocal")
	private Boolean clinicalElementTemplateMappingIsfocal;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="clinical_element_template_mapping_timestamp")
	private Timestamp clinicalElementTemplateMappingTimestamp;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "clinical_element_template_mapping_gwid", referencedColumnName = "clinical_elements_gwid", insertable = false, updatable = false)
	private ClinicalElements clinicalElement;
	
	@OneToMany(mappedBy="clinicalElementTemplateMapping")
	List<TemplateMappingOptions> templateMappingOptions;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "clinical_element_template_mapping_gwid", referencedColumnName = "pe_element_detail_option_gwid", insertable = false, updatable = false)
	private PeElementDetailOption peElementDetailOption; 
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "clinical_element_template_mapping_gwid", referencedColumnName = "pe_element_gwid", insertable = false, updatable = false)
	private PeElement peElement; 
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name = "clinical_element_template_mapping_gwid", referencedColumnName = "history_element_gwid", insertable = false, updatable = false)
	private HistoryElement historyElement2;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name = "clinical_element_template_mapping_gwid", referencedColumnName = "family_history_element_general_gwid", insertable = false, updatable = false)
	private FamilyHistoryElement familyHistoryElement;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name = "clinical_element_template_mapping_gwid", referencedColumnName = "surgical_history_element_details_gwid", insertable = false, updatable = false)
	private SurgicalHistoryElementDetails surgicalHistoryElementDetails;
	
	/*@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name = "clinical_element_template_mapping_gwid", referencedColumnName = "hpi_symptom_gwid", insertable = false, updatable = false)
	private HpiSymptom hpiSymptom;*/
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clinical_element_template_mapping_gwid", referencedColumnName="plan_instruction_gwid", insertable=false, updatable=false)
	private PlanInstruction planInstruction;
	
	public PlanInstruction getPlanInstruction() {
		return planInstruction;
	}

	public void setPlanInstruction(PlanInstruction planInstruction) {
		this.planInstruction = planInstruction;
	}

	public FamilyHistoryElement getFamilyHistoryElement() {
		return familyHistoryElement;
	}

	public SurgicalHistoryElementDetails getSurgicalHistoryElementDetails() {
		return surgicalHistoryElementDetails;
	}

	public void setFamilyHistoryElement(FamilyHistoryElement familyHistoryElement) {
		this.familyHistoryElement = familyHistoryElement;
	}

	public void setSurgicalHistoryElementDetails(
			SurgicalHistoryElementDetails surgicalHistoryElementDetails) {
		this.surgicalHistoryElementDetails = surgicalHistoryElementDetails;
	}

	

	public HistoryElement getHistoryElement2() {
		return historyElement2;
	}

	public void setHistoryElement2(HistoryElement historyElement2) {
		this.historyElement2 = historyElement2;
	}
	
	
	public List<TemplateMappingOptions> getTemplateMappingOptions() {
		return templateMappingOptions;
	}

	public void setTemplateMappingOptions(List<TemplateMappingOptions> templateMappingOptions) {
		this.templateMappingOptions = templateMappingOptions;
	}

	public Integer getClinicalElementTemplateMappingId() {
		return clinicalElementTemplateMappingId;
	}

	public void setClinicalElementTemplateMappingId(
			Integer clinicalElementTemplateMappingId) {
		this.clinicalElementTemplateMappingId = clinicalElementTemplateMappingId;
	}

	public String getClinicalElementTemplateMappingGwid() {
		return clinicalElementTemplateMappingGwid;
	}

	public void setClinicalElementTemplateMappingGwid(
			String clinicalElementTemplateMappingGwid) {
		this.clinicalElementTemplateMappingGwid = clinicalElementTemplateMappingGwid;
	}

	public Integer getClinicalElementTemplateMappingTemplateid() {
		return clinicalElementTemplateMappingTemplateid;
	}

	public void setClinicalElementTemplateMappingTemplateid(
			Integer clinicalElementTemplateMappingTemplateid) {
		this.clinicalElementTemplateMappingTemplateid = clinicalElementTemplateMappingTemplateid;
	}

	public Integer getClinicalElementTemplateMappingType() {
		return clinicalElementTemplateMappingType;
	}

	public void setClinicalElementTemplateMappingType(
			Integer clinicalElementTemplateMappingType) {
		this.clinicalElementTemplateMappingType = clinicalElementTemplateMappingType;
	}

	public String getClinicalElementTemplateMappingDefaultValue() {
		return clinicalElementTemplateMappingDefaultValue;
	}

	public void setClinicalElementTemplateMappingDefaultValue(
			String clinicalElementTemplateMappingDefaultValue) {
		this.clinicalElementTemplateMappingDefaultValue = clinicalElementTemplateMappingDefaultValue;
	}

	public Integer getClinicalElementTemplateMappingProperty() {
		return clinicalElementTemplateMappingProperty;
	}

	public void setClinicalElementTemplateMappingProperty(
			Integer clinicalElementTemplateMappingProperty) {
		this.clinicalElementTemplateMappingProperty = clinicalElementTemplateMappingProperty;
	}

	public Boolean getClinicalElementTemplateMappingIsactive() {
		return clinicalElementTemplateMappingIsactive;
	}

	public void setClinicalElementTemplateMappingIsactive(
			Boolean clinicalElementTemplateMappingIsactive) {
		this.clinicalElementTemplateMappingIsactive = clinicalElementTemplateMappingIsactive;
	}

	public Integer getClinicalElementTemplateMappingOrderby() {
		return clinicalElementTemplateMappingOrderby;
	}

	public void setClinicalElementTemplateMappingOrderby(
			Integer clinicalElementTemplateMappingOrderby) {
		this.clinicalElementTemplateMappingOrderby = clinicalElementTemplateMappingOrderby;
	}

	public Boolean getClinicalElementTemplateMappingIsfocal() {
		return clinicalElementTemplateMappingIsfocal;
	}

	public void setClinicalElementTemplateMappingIsfocal(
			Boolean clinicalElementTemplateMappingIsfocal) {
		this.clinicalElementTemplateMappingIsfocal = clinicalElementTemplateMappingIsfocal;
	}

	public Timestamp getClinicalElementTemplateMappingTimestamp() {
		return clinicalElementTemplateMappingTimestamp;
	}

	public void setClinicalElementTemplateMappingTimestamp(
			Timestamp clinicalElementTemplateMappingTimestamp) {
		this.clinicalElementTemplateMappingTimestamp = clinicalElementTemplateMappingTimestamp;
	}

	public ClinicalElements getClinicalElement() {
		return clinicalElement;
	}

	public void setClinicalElement(ClinicalElements clinicalElement) {
		this.clinicalElement = clinicalElement;
	}

	public PeElementDetailOption getPeElementDetailOption() {
		return peElementDetailOption;
	}

	public void setPeElementDetailOption(PeElementDetailOption peElementDetailOption) {
		this.peElementDetailOption = peElementDetailOption;
	}

	public PeElement getPeElement() {
		return peElement;
	}

	public void setPeElement(PeElement peElement) {
		this.peElement = peElement;
	}

	/*public HpiSymptom getHpiSymptom() {
		return hpiSymptom;
	}

	public void setHpiSymptom(HpiSymptom hpiSymptom) {
		this.hpiSymptom = hpiSymptom;
	}*/
	
	
}