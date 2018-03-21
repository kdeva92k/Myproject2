package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.util.List;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "patient_clinical_elements")
public class PatientClinicalElements implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_clinical_elements_patient_clinical_elements_id_seq")
	@SequenceGenerator(name ="patient_clinical_elements_patient_clinical_elements_id_seq", sequenceName="patient_clinical_elements_patient_clinical_elements_id_seq", allocationSize=1)
	@Column(name="patient_clinical_elements_id")
	private Long patientClinicalElementsId;

	@Column(name="patient_clinical_elements_patientid")
	private Integer patientClinicalElementsPatientid;

	@Column(name="patient_clinical_elements_chartid")
	private Integer patientClinicalElementsChartid;

	@Column(name="patient_clinical_elements_encounterid")
	private Integer patientClinicalElementsEncounterid;
	
	
	@Column(name="patient_clinical_elements_gwid", length=20)
	private String patientClinicalElementsGwid;

	@Column(name="patient_clinical_elements_value")
	private String patientClinicalElementsValue;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_clinical_elements_encounterid",referencedColumnName="encounter_id",insertable = false, updatable = false)
	private Encounter encounter;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_clinical_elements_gwid",referencedColumnName="clinical_elements_gwid",insertable = false, updatable = false)
	private ClinicalElements clinicalElement;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_clinical_elements_gwid",referencedColumnName="vitals_parameter_gw_id",insertable = false, updatable = false)
	private VitalsParameter vitalsParameter;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_clinical_elements_gwid",referencedColumnName="pe_element_detail_option_gwid",insertable = false, updatable = false)
	private PeElementDetailOption peElementDetailOption;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_clinical_elements_gwid",referencedColumnName="pe_element_gwid",insertable = false, updatable = false)
	private PeElement peElement;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JsonManagedReference
	@JoinColumn(name="patient_clinical_elements_gwid", referencedColumnName="plan_instruction_gwid", insertable=false, updatable=false)
	private PlanInstruction planInstruction;
	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="patient_clinical_elements_gwid",referencedColumnName="hpi_symptom_gwid",insertable = false, updatable = false)
	private HpiSymptom hpiSymptom;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="patient_clinical_elements_gwid",referencedColumnName="hpi_element_gwid",insertable = false, updatable = false)
	private HpiElement hpiElement;*/
	
/*	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="patient_clinical_elements_gwid",referencedColumnName="history_element_gwid",insertable = false, updatable = false)
	private HistoryElement historyElement;
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="patient_clinical_elements_gwid", referencedColumnName="family_history_element_general_gwid", insertable=false, updatable=false)
	@JsonManagedReference
	FamilyHistoryElement familyHistoryElement;
	
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="patient_clinical_elements_gwid", referencedColumnName="family_history_element_notes_gwid", insertable=false, updatable=false)
	@JsonManagedReference
	FamilyHistoryElement familyHistoryElement1;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="patient_clinical_elements_gwid", referencedColumnName="family_history_element_status_gwid", insertable=false, updatable=false)
	@JsonManagedReference
	FamilyHistoryElement familyHistoryElement2;
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="patient_clinical_elements_gwid", referencedColumnName="surgical_history_element_details_gwid", insertable=false, updatable=false)
	@JsonManagedReference
	SurgicalHistoryElementDetails surgicalHistoryElementDetails;
	
	
	public FamilyHistoryElement getFamilyHistoryElement() {
		return familyHistoryElement;
	}

	public FamilyHistoryElement getFamilyHistoryElement1() {
		return familyHistoryElement1;
	}

	public FamilyHistoryElement getFamilyHistoryElement2() {
		return familyHistoryElement2;
	}

	public SurgicalHistoryElementDetails getSurgicalHistoryElementDetails() {
		return surgicalHistoryElementDetails;
	}

	public void setHistoryElement(HistoryElement historyElement) {
		this.historyElement = historyElement;
	}

	public void setFamilyHistoryElement(FamilyHistoryElement familyHistoryElement) {
		this.familyHistoryElement = familyHistoryElement;
	}

	public void setFamilyHistoryElement1(FamilyHistoryElement familyHistoryElement1) {
		this.familyHistoryElement1 = familyHistoryElement1;
	}

	public void setFamilyHistoryElement2(FamilyHistoryElement familyHistoryElement2) {
		this.familyHistoryElement2 = familyHistoryElement2;
	}

	public void setSurgicalHistoryElementDetails(
			SurgicalHistoryElementDetails surgicalHistoryElementDetails) {
		this.surgicalHistoryElementDetails = surgicalHistoryElementDetails;
	}*/

	public Long getPatientClinicalElementsId() {
		return patientClinicalElementsId;
	}

	public void setPatientClinicalElementsId(Long patientClinicalElementsId) {
		this.patientClinicalElementsId = patientClinicalElementsId;
	}

	public Integer getPatientClinicalElementsPatientid() {
		return patientClinicalElementsPatientid;
	}

	public void setPatientClinicalElementsPatientid(
			Integer patientClinicalElementsPatientid) {
		this.patientClinicalElementsPatientid = patientClinicalElementsPatientid;
	}

	public Integer getPatientClinicalElementsChartid() {
		return patientClinicalElementsChartid;
	}

	public void setPatientClinicalElementsChartid(
			Integer patientClinicalElementsChartid) {
		this.patientClinicalElementsChartid = patientClinicalElementsChartid;
	}

	public Integer getPatientClinicalElementsEncounterid() {
		return patientClinicalElementsEncounterid;
	}

	public void setPatientClinicalElementsEncounterid(
			Integer patientClinicalElementsEncounterid) {
		this.patientClinicalElementsEncounterid = patientClinicalElementsEncounterid;
	}

	public String getPatientClinicalElementsGwid() {
		return patientClinicalElementsGwid;
	}

	public void setPatientClinicalElementsGwid(String patientClinicalElementsGwid) {
		this.patientClinicalElementsGwid = patientClinicalElementsGwid;
	}

	public String getPatientClinicalElementsValue() {
		return patientClinicalElementsValue;
	}

	public void setPatientClinicalElementsValue(String patientClinicalElementsValue) {
		this.patientClinicalElementsValue = patientClinicalElementsValue;
	}

	public ClinicalElements getClinicalElement() {
		return clinicalElement;
	}

	public void setClinicalElement(ClinicalElements clinicalElement) {
		this.clinicalElement = clinicalElement;
	}

	public VitalsParameter getVitalsParameter() {
		return vitalsParameter;
	}

	public void setVitalsParameter(VitalsParameter vitalsParameter) {
		this.vitalsParameter = vitalsParameter;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
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

	public PlanInstruction getPlanInstruction() {
		return planInstruction;
	}

	public void setPlanInstruction(PlanInstruction planInstruction) {
		this.planInstruction = planInstruction;
	}

	/*public HpiSymptom getHpiSymptom() {
		return hpiSymptom;
	}
	
	public void setHpiSymptom(HpiSymptom hpiSymptom) {
		this.hpiSymptom = hpiSymptom;
	}
	
	public HpiElement getHpiElement() {
		return hpiElement;
	}
	
	public void setHpiElement(HpiElement hpiElement) {
		this.hpiElement = hpiElement;
	}*/
	
}