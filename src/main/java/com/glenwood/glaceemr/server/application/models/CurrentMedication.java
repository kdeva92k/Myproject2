package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonDateSerializer;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "current_medication")
public class CurrentMedication {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "current_medication_current_medication_id_seq")
	@SequenceGenerator(name = "current_medication_current_medication_id_seq", sequenceName = "current_medication_current_medication_id_seq", allocationSize = 1)
	@Column(name="current_medication_id")
	private Integer currentMedicationId;

	@Column(name="current_medication_encounter_id")
	private Integer currentMedicationEncounterId;

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="current_medication_encounter_id",referencedColumnName="encounter_id",insertable=false,updatable=false)
	@JsonManagedReference
	Encounter encounter;

	@OneToMany(mappedBy="currentMedication", fetch=FetchType.LAZY)
	@JsonManagedReference
	List<MedsAdminPlan> medsAdminPlan;
	
	public List<MedsAdminPlan> getMedsAdminPlan() {
		return medsAdminPlan;
	}

	public void setMedsAdminPlan(List<MedsAdminPlan> medsAdminPlan) {
		this.medsAdminPlan = medsAdminPlan;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public MedStatus getMedstatus() {
		return medstatus;
	}

	public void setMedstatus(MedStatus medstatus) {
		this.medstatus = medstatus;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="current_medication_status",referencedColumnName="med_status_id",insertable=false,updatable=false)
	@JsonManagedReference
	MedStatus medstatus;



	@Column(name="current_medication_is_chronic")
	private Boolean currentMedicationIsChronic;

	@Column(name="current_medication_order_by")
	private Integer currentMedicationOrderBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="current_medication_order_on")
	private Timestamp currentMedicationOrderOn;

	@Column(name="current_medication_modified_by")
	private Integer currentMedicationModifiedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="current_medication_modified_on")
	private Timestamp currentMedicationModifiedOn;

	@Column(name="current_medication_rxnorm_cd")
	private String currentMedicationRXNormCD;

	
	@Column(name="current_medication_intake")
	private String currentMedicationIntake;

	@Column(name="current_medication_quantity")
	private String currentMedicationQuantity;

	@Column(name="current_medication_rx_name")
	private String currentMedicationRxName;

	@Column(name="current_medication_dosage_with_unit")
	private String currentMedicationDosageWithUnit;

	@Column(name="current_medication_unit")
	private String currentMedicationUnit;

	@Column(name="current_medication_refills")
	private String currentMedicationRefills;

	@Column(name="current_medication_route")
	private String currentMedicationRoute;

	@Column(name="current_medication_duration")
	private String currentMedicationDuration;

	@Column(name="current_medication_frequency1")
	private String currentMedicationFrequency1;

	@Column(name="current_medication_frequency2")
	private String currentMedicationFrequency2;

	@Column(name="current_medication_form")
	private String currentMedicationForm;

	@Column(name="current_medication_comments")
	private String currentMedicationComments;

	@Column(name="current_medication_patient_id")
	private Long currentMedicationPatientId;

	@Column(name="current_medication_route_id")
	private Integer currentMedicationRouteId;

	@Column(name="current_medication_allow_substitution")
	private Boolean currentMedicationAllowSubstitution;

	@Column(name="current_medication_status")
	private Integer currentMedicationStatus;

	@Column(name="current_medication_is_active")
	private Boolean currentMedicationIsActive;

	@Column(name="current_medication_unknown1")
	private Integer currentMedicationUnknown1;

	@Column(name="current_medication_unknown2")
	private Integer currentMedicationUnknown2;

	@Column(name="current_medication_unknown3")
	private Integer currentMedicationUnknown3;

	@Column(name="current_medication_unknown4")
	private Integer currentMedicationUnknown4;

	@Column(name="current_medication_notes")
	private String currentMedicationNotes;

	@Column(name="current_medication_lot_number")
	private String currentMedicationLotNumber;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="current_medication_expiry_date")
	private Timestamp currentMedicationExpiryDate;

	@Column(name="current_medication_days")
	private String currentMedicationDays;
	
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name="current_medication_start_date")
	private Date currentMedicationStartDate;

	@Column(name="current_medication_external_source_info")
	private String currentMedicationExternalSourceInfo;

	@Column(name="current_medication_is_overridden")
	private Boolean currentMedicationIsOverridden;

	@Column(name="current_medication_med_internal_root_source")
	private String currentMedicationMedInternalRootSource;

	@Column(name="current_medication_prescribed_by")
	private  String currentMedicationPrescribedBy;
	
	@Column(name="current_medication_inactivated_by")
	private Integer currentMedicationInactivatedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="current_medication_inactivated_on")
	private Timestamp currentMedicationInactivatedOn;

	@Column(name="current_medication_leaf_id")
	private Integer currentMedicationLeafId;

	@Column(name="current_medication_ndc_code")
	private String currentMedicationNdcCode;

	@Column(name="current_medication_is_uncoded")
	private Boolean currentMedicationIsUncoded;

	@Column(name="current_medication_quantity_units")
	private String currentMedicationQuantityUnits;

	@Column(name="current_medication_is_med_sup")
	private Boolean currentMedicationIsMedSup;

	@Column(name="current_medictiion_rxnorm_code")
	private String currentMedictiionRxnormCode;

	@Column(name="current_medication_rxnorm_code")
	private String currentMedicationRxnormCode;

	public Integer getCurrentMedicationId() {
		return currentMedicationId;
	}

	public void setCurrentMedicationId(Integer currentMedicationId) {
		this.currentMedicationId = currentMedicationId;
	}

	public Integer getCurrentMedicationEncounterId() {
		return currentMedicationEncounterId;
	}

	public void setCurrentMedicationEncounterId(Integer currentMedicationEncounterId) {
		this.currentMedicationEncounterId = currentMedicationEncounterId;
	}

	public Boolean getCurrentMedicationIsChronic() {
		return currentMedicationIsChronic;
	}

	public void setCurrentMedicationIsChronic(Boolean currentMedicationIsChronic) {
		this.currentMedicationIsChronic = currentMedicationIsChronic;
	}

	public String getCurrentMedicationRXNormCD() {
		return currentMedicationRXNormCD;
	}

	public void setCurrentMedicationRXNormCD(String currentMedicationRXNormCD) {
		this.currentMedicationRXNormCD = currentMedicationRXNormCD;
	}

	public Integer getCurrentMedicationOrderBy() {
		return currentMedicationOrderBy;
	}

	public void setCurrentMedicationOrderBy(Integer currentMedicationOrderBy) {
		this.currentMedicationOrderBy = currentMedicationOrderBy;
	}

	public Timestamp getCurrentMedicationOrderOn() {
		return currentMedicationOrderOn;
	}

	public void setCurrentMedicationOrderOn(Timestamp currentMedicationOrderOn) {
		this.currentMedicationOrderOn = currentMedicationOrderOn;
	}

	public Integer getCurrentMedicationModifiedBy() {
		return currentMedicationModifiedBy;
	}

	public void setCurrentMedicationModifiedBy(Integer currentMedicationModifiedBy) {
		this.currentMedicationModifiedBy = currentMedicationModifiedBy;
	}

	public Timestamp getCurrentMedicationModifiedOn() {
		return currentMedicationModifiedOn;
	}

	public void setCurrentMedicationModifiedOn(Timestamp currentMedicationModifiedOn) {
		this.currentMedicationModifiedOn = currentMedicationModifiedOn;
	}

	public String getCurrentMedicationIntake() {
		return currentMedicationIntake;
	}

	public void setCurrentMedicationIntake(String currentMedicationIntake) {
		this.currentMedicationIntake = currentMedicationIntake;
	}

	public String getCurrentMedicationQuantity() {
		return currentMedicationQuantity;
	}

	public void setCurrentMedicationQuantity(String currentMedicationQuantity) {
		this.currentMedicationQuantity = currentMedicationQuantity;
	}

	public String getCurrentMedicationRxName() {
		return currentMedicationRxName;
	}

	public void setCurrentMedicationRxName(String currentMedicationRxName) {
		this.currentMedicationRxName = currentMedicationRxName;
	}

	public String getCurrentMedicationDosageWithUnit() {
		return currentMedicationDosageWithUnit;
	}

	public void setCurrentMedicationDosageWithUnit(
			String currentMedicationDosageWithUnit) {
		this.currentMedicationDosageWithUnit = currentMedicationDosageWithUnit;
	}

	public String getCurrentMedicationUnit() {
		return currentMedicationUnit;
	}

	public void setCurrentMedicationUnit(String currentMedicationUnit) {
		this.currentMedicationUnit = currentMedicationUnit;
	}

	public String getCurrentMedicationRefills() {
		return currentMedicationRefills;
	}

	public void setCurrentMedicationRefills(String currentMedicationRefills) {
		this.currentMedicationRefills = currentMedicationRefills;
	}

	public String getCurrentMedicationRoute() {
		return currentMedicationRoute;
	}

	public void setCurrentMedicationRoute(String currentMedicationRoute) {
		this.currentMedicationRoute = currentMedicationRoute;
	}

	public String getCurrentMedicationDuration() {
		return currentMedicationDuration;
	}

	public void setCurrentMedicationDuration(String currentMedicationDuration) {
		this.currentMedicationDuration = currentMedicationDuration;
	}

	public String getCurrentMedicationFrequency1() {
		return currentMedicationFrequency1;
	}

	public void setCurrentMedicationFrequency1(String currentMedicationFrequency1) {
		this.currentMedicationFrequency1 = currentMedicationFrequency1;
	}

	public String getCurrentMedicationFrequency2() {
		return currentMedicationFrequency2;
	}

	public void setCurrentMedicationFrequency2(String currentMedicationFrequency2) {
		this.currentMedicationFrequency2 = currentMedicationFrequency2;
	}

	public String getCurrentMedicationForm() {
		return currentMedicationForm;
	}

	public void setCurrentMedicationForm(String currentMedicationForm) {
		this.currentMedicationForm = currentMedicationForm;
	}

	public String getCurrentMedicationComments() {
		return currentMedicationComments;
	}

	public void setCurrentMedicationComments(String currentMedicationComments) {
		this.currentMedicationComments = currentMedicationComments;
	}

	public Long getCurrentMedicationPatientId() {
		return currentMedicationPatientId;
	}

	public void setCurrentMedicationPatientId(Long currentMedicationPatientId) {
		this.currentMedicationPatientId = currentMedicationPatientId;
	}

	public Integer getCurrentMedicationRouteId() {
		return currentMedicationRouteId;
	}

	public void setCurrentMedicationRouteId(Integer currentMedicationRouteId) {
		this.currentMedicationRouteId = currentMedicationRouteId;
	}

	public Boolean getCurrentMedicationAllowSubstitution() {
		return currentMedicationAllowSubstitution;
	}

	public void setCurrentMedicationAllowSubstitution(
			Boolean currentMedicationAllowSubstitution) {
		this.currentMedicationAllowSubstitution = currentMedicationAllowSubstitution;
	}

	public Integer getCurrentMedicationStatus() {
		return currentMedicationStatus;
	}

	public void setCurrentMedicationStatus(Integer currentMedicationStatus) {
		this.currentMedicationStatus = currentMedicationStatus;
	}

	public Boolean getCurrentMedicationIsActive() {
		return currentMedicationIsActive;
	}

	public void setCurrentMedicationIsActive(Boolean currentMedicationIsActive) {
		this.currentMedicationIsActive = currentMedicationIsActive;
	}

	public Integer getCurrentMedicationUnknown1() {
		return currentMedicationUnknown1;
	}

	public void setCurrentMedicationUnknown1(Integer currentMedicationUnknown1) {
		this.currentMedicationUnknown1 = currentMedicationUnknown1;
	}

	public Integer getCurrentMedicationUnknown2() {
		return currentMedicationUnknown2;
	}

	public void setCurrentMedicationUnknown2(Integer currentMedicationUnknown2) {
		this.currentMedicationUnknown2 = currentMedicationUnknown2;
	}

	public Integer getCurrentMedicationUnknown3() {
		return currentMedicationUnknown3;
	}

	public void setCurrentMedicationUnknown3(Integer currentMedicationUnknown3) {
		this.currentMedicationUnknown3 = currentMedicationUnknown3;
	}

	public Integer getCurrentMedicationUnknown4() {
		return currentMedicationUnknown4;
	}

	public void setCurrentMedicationUnknown4(Integer currentMedicationUnknown4) {
		this.currentMedicationUnknown4 = currentMedicationUnknown4;
	}

	public String getCurrentMedicationNotes() {
		return currentMedicationNotes;
	}

	public void setCurrentMedicationNotes(String currentMedicationNotes) {
		this.currentMedicationNotes = currentMedicationNotes;
	}

	public String getCurrentMedicationLotNumber() {
		return currentMedicationLotNumber;
	}

	public void setCurrentMedicationLotNumber(String currentMedicationLotNumber) {
		this.currentMedicationLotNumber = currentMedicationLotNumber;
	}

	public Timestamp getCurrentMedicationExpiryDate() {
		return currentMedicationExpiryDate;
	}

	public void setCurrentMedicationExpiryDate(Timestamp currentMedicationExpiryDate) {
		this.currentMedicationExpiryDate = currentMedicationExpiryDate;
	}

	public String getCurrentMedicationDays() {
		return currentMedicationDays;
	}

	public void setCurrentMedicationDays(String currentMedicationDays) {
		this.currentMedicationDays = currentMedicationDays;
	}

	public Date getCurrentMedicationStartDate() {
		return currentMedicationStartDate;
	}

	public void setCurrentMedicationStartDate(Date currentMedicationStartDate) {
		this.currentMedicationStartDate = currentMedicationStartDate;
	}

	public String getCurrentMedicationExternalSourceInfo() {
		return currentMedicationExternalSourceInfo;
	}

	public void setCurrentMedicationExternalSourceInfo(
			String currentMedicationExternalSourceInfo) {
		this.currentMedicationExternalSourceInfo = currentMedicationExternalSourceInfo;
	}

	public Boolean getCurrentMedicationIsOverridden() {
		return currentMedicationIsOverridden;
	}

	public void setCurrentMedicationIsOverridden(
			Boolean currentMedicationIsOverridden) {
		this.currentMedicationIsOverridden = currentMedicationIsOverridden;
	}

	public String getCurrentMedicationMedInternalRootSource() {
		return currentMedicationMedInternalRootSource;
	}

	public void setCurrentMedicationMedInternalRootSource(
			String currentMedicationMedInternalRootSource) {
		this.currentMedicationMedInternalRootSource = currentMedicationMedInternalRootSource;
	}

	public Integer getCurrentMedicationInactivatedBy() {
		return currentMedicationInactivatedBy;
	}

	public void setCurrentMedicationInactivatedBy(
			Integer currentMedicationInactivatedBy) {
		this.currentMedicationInactivatedBy = currentMedicationInactivatedBy;
	}

	public Timestamp getCurrentMedicationInactivatedOn() {
		return currentMedicationInactivatedOn;
	}

	public void setCurrentMedicationInactivatedOn(
			Timestamp currentMedicationInactivatedOn) {
		this.currentMedicationInactivatedOn = currentMedicationInactivatedOn;
	}

	public Integer getCurrentMedicationLeafId() {
		return currentMedicationLeafId;
	}

	public void setCurrentMedicationLeafId(Integer currentMedicationLeafId) {
		this.currentMedicationLeafId = currentMedicationLeafId;
	}

	public String getCurrentMedicationNdcCode() {
		return currentMedicationNdcCode;
	}

	public void setCurrentMedicationNdcCode(String currentMedicationNdcCode) {
		this.currentMedicationNdcCode = currentMedicationNdcCode;
	}

	public Boolean getCurrentMedicationIsUncoded() {
		return currentMedicationIsUncoded;
	}

	public void setCurrentMedicationIsUncoded(Boolean currentMedicationIsUncoded) {
		this.currentMedicationIsUncoded = currentMedicationIsUncoded;
	}

	public String getCurrentMedicationQuantityUnits() {
		return currentMedicationQuantityUnits;
	}

	public void setCurrentMedicationQuantityUnits(
			String currentMedicationQuantityUnits) {
		this.currentMedicationQuantityUnits = currentMedicationQuantityUnits;
	}

	public Boolean getCurrentMedicationIsMedSup() {
		return currentMedicationIsMedSup;
	}

	public void setCurrentMedicationIsMedSup(Boolean currentMedicationIsMedSup) {
		this.currentMedicationIsMedSup = currentMedicationIsMedSup;
	}

	public String getCurrentMedictiionRxnormCode() {
		return currentMedictiionRxnormCode;
	}

	public void setCurrentMedictiionRxnormCode(String currentMedictiionRxnormCode) {
		this.currentMedictiionRxnormCode = currentMedictiionRxnormCode;
	}

	public String getCurrentMedicationRxnormCode() {
		return currentMedicationRxnormCode;
	}

	public void setCurrentMedicationRxnormCode(String currentMedicationRxnormCode) {
		this.currentMedicationRxnormCode = currentMedicationRxnormCode;
	}

	public String getCurrentMedicationPrescribedBy() {
		return currentMedicationPrescribedBy;
	}

	public void setCurrentMedicationPrescribedBy(String currentMedicationPrescribedBy) {
		this.currentMedicationPrescribedBy = currentMedicationPrescribedBy;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="current_medication_leaf_id",referencedColumnName="leaf_patient_id",insertable=false,updatable=false)
	@JsonManagedReference
	LeafPatient leafPatient;
	
	@NotFound(action=NotFoundAction.IGNORE) 
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="current_medication_modified_by",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	@JsonManagedReference
	EmployeeProfile empProfileModifiedBy;
	
	@NotFound(action=NotFoundAction.IGNORE) 
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="current_medication_order_by",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	@JsonManagedReference
	EmployeeProfile empProfileOrderBy;
	
}