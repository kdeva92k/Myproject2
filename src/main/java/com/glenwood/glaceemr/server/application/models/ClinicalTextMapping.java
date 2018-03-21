package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clinical_text_mapping")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClinicalTextMapping {

	
	@Column(name="clinical_text_mapping_id")
	private Integer clinicalTextMappingId;
	
	@Id
	@Column(name="clinical_text_mapping_textbox_gwid")
	private String clinicalTextMappingTextboxGwid;

	@Column(name="clinical_text_mapping_associated_element")
	private String clinicalTextMappingAssociatedElement;
	
	@Column(name="clinical_text_mapping_isdate")
	private Boolean clinicalTextMappingIsdate;

	@Column(name="clinical_text_mapping_popup_type")
	private Integer clinicalTextMappingPopupType;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "clinical_text_mapping_associated_element", referencedColumnName = "pe_element_gwid", insertable = false, updatable = false)
	private PeElement peElement;

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(formula = @JoinFormula(value = "split_part(clinical_text_mapping_associated_element,'#',1)", referencedColumnName = "history_element_gwid")) })
	private HistoryElement historyElement1;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "clinical_text_mapping_associated_element", referencedColumnName = "ros_element_gwid", insertable = false, updatable = false)
	private RosElement rosElement;
	
	/*@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(formula = @JoinFormula(value = "split_part(clinical_text_mapping_associated_element,'#',1)", referencedColumnName = "patient_clinical_elements_gwid")) })
	private PatientClinicalElements patientClinicalElements;*/
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clinical_text_mapping_textbox_gwid", referencedColumnName = "plan_instruction_gwid", insertable=false, updatable=false)	
	private PlanInstruction planInstruction;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(formula = @JoinFormula(value = "split_part(clinical_text_mapping_associated_element,'#',1)", referencedColumnName = "plan_instruction_gwid")) })
	private PlanInstruction planInstructionAssociate;
	
	public RosElement getRosElement() {
		return rosElement;
	}

	public void setRosElement(RosElement rosElement) {
		this.rosElement = rosElement;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "clinical_text_mapping_textbox_gwid", referencedColumnName = "clinical_elements_gwid", insertable = false, updatable = false)
	private ClinicalElements clinicalElements;

	
	
	
	public ClinicalElements getClinicalElements() {
		return clinicalElements;
	}

	public void setClinicalElements(ClinicalElements clinicalElements) {
		this.clinicalElements = clinicalElements;
	}

	public HistoryElement getHistoryElement1() {
		return historyElement1;
	}

	public void setHistoryElement1(HistoryElement historyElement1) {
		this.historyElement1 = historyElement1;
	}
	
	
	public Integer getClinicalTextMappingId() {
		return clinicalTextMappingId;
	}

	public void setClinicalTextMappingId(Integer clinicalTextMappingId) {
		this.clinicalTextMappingId = clinicalTextMappingId;
	}

	public String getClinicalTextMappingTextboxGwid() {
		return clinicalTextMappingTextboxGwid;
	}

	public void setClinicalTextMappingTextboxGwid(
			String clinicalTextMappingTextboxGwid) {
		this.clinicalTextMappingTextboxGwid = clinicalTextMappingTextboxGwid;
	}

	public String getClinicalTextMappingAssociatedElement() {
		return clinicalTextMappingAssociatedElement;
	}

	public void setClinicalTextMappingAssociatedElement(
			String clinicalTextMappingAssociatedElement) {
		this.clinicalTextMappingAssociatedElement = clinicalTextMappingAssociatedElement;
	}

	public Boolean getClinicalTextMappingIsdate() {
		return clinicalTextMappingIsdate;
	}

	public void setClinicalTextMappingIsdate(Boolean clinicalTextMappingIsdate) {
		this.clinicalTextMappingIsdate = clinicalTextMappingIsdate;
	}

	public Integer getClinicalTextMappingPopupType() {
		return clinicalTextMappingPopupType;
	}

	public void setClinicalTextMappingPopupType(Integer clinicalTextMappingPopupType) {
		this.clinicalTextMappingPopupType = clinicalTextMappingPopupType;
	}

	/*public PatientClinicalElements getPatientClinicalElements() {
		return patientClinicalElements;
	}

	public void setPatientClinicalElements(
			PatientClinicalElements patientClinicalElements) {
		this.patientClinicalElements = patientClinicalElements;
	}*/
	public PlanInstruction getPlanInstruction() {
		return planInstruction;
	}

	public void setPlanInstruction(PlanInstruction planInstruction) {
		this.planInstruction = planInstruction;
	}

	public PlanInstruction getPlanInstructionAssociate() {
		return planInstructionAssociate;
	}

	public void setPlanInstructionAssociate(PlanInstruction planInstructionAssociate) {
		this.planInstructionAssociate = planInstructionAssociate;
	}
}