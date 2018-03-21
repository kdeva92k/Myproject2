package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "lab_entries")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabEntries {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="lab_entries_seq")
	@SequenceGenerator(name ="lab_entries_seq", sequenceName="lab_entries_seq", allocationSize=1)
	@Column(name="lab_entries_testdetail_id", nullable=false)
	private Integer labEntriesTestdetailId;

	@Column(name="lab_entries_encounter_id")
	private Integer labEntriesEncounterId;

	@Column(name="lab_entries_test_id")
	private Integer labEntriesTestId;

	@Column(name="lab_entries_test_type")
	private Integer labEntriesTestType;

	@Column(name="lab_entries_ord_by")
	private Integer labEntriesOrdBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="lab_entries_ord_on")
	private Timestamp labEntriesOrdOn;

	@Column(name="lab_entries_perf_by")
	private Integer labEntriesPerfBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="lab_entries_perf_on")
	private Timestamp labEntriesPerfOn;

	@Column(name="lab_entries_rev_by")
	private Integer labEntriesRevBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="lab_entries_rev_on")
	private Timestamp labEntriesRevOn;

	@Column(name="lab_entries_informed_by")
	private Integer labEntriesInformedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="lab_entries_informed_on")
	private Timestamp labEntriesInformedOn;

	@Column(name="lab_entries_prelim_test_status")
	private Integer labEntriesPrelimTestStatus;

	@Column(name="lab_entries_confirm_test_status")
	private Integer labEntriesConfirmTestStatus;

	@Column(name="lab_entries_dx1")
	private Integer labEntriesDx1;

	@Column(name="lab_entries_dx2")
	private Integer labEntriesDx2;

	@Column(name="lab_entries_test_status")
	private Integer labEntriesTestStatus;

	@Column(name="lab_entries_refdoc_id")
	private Integer labEntriesRefdocId;

	@Column(name="lab_entries_test_desc")
	private String labEntriesTestDesc;

	@Column(name="lab_entries_scangroup_id")
	private String labEntriesScangroupId;

	@Column(name="lab_entries_cpt")
	private String labEntriesCpt;

	@Column(name="lab_entries_dx1code", columnDefinition="String default -1")
	private String labEntriesDx1code;

	@Column(name="lab_entries_dx2code", columnDefinition="String default -1")
	private String labEntriesDx2code;

	@Column(name="lab_entries_dx3code", columnDefinition="String default -1")
	private String labEntriesDx3code;

	@Column(name="lab_entries_dx4code", columnDefinition="String default -1")
	private String labEntriesDx4code;
	
	@Column(name="lab_entries_short_notes")
	private String labEntriesShortNotes;

	@Column(name="lab_entries_result_notes")
	private String labEntriesResultNotes;

	@Column(name="lab_entries_result_param")
	private String labEntriesResultParam;

	@Column(name="lab_entries_lot_no")
	private Integer labEntriesLotNo;

	@Column(name="lab_entries_groupid")
	private Integer labEntriesGroupid;

	@Column(name="lab_entries_is_billable")
	private Boolean labEntriesIsBillable;

	@Column(name="lab_entries_units")
	private Integer labEntriesUnits;

	@Column(name="lab_entries_zunits")
	private Integer labEntriesZunits;

	@Column(name="lab_entries_lab_route")
	private String labEntriesLabRoute;

	@Column(name="lab_entries_lab_site")
	private String labEntriesLabSite;

	@Column(name="lab_entries_shortorder_notes")
	private String labEntriesShortorderNotes;

	@Column(name="lab_entries_obs_units")
	private String labEntriesObsUnits;

	@Column(name="lab_entries_obs_id")
	private Integer labEntriesObsId;

	@Column(name="lab_entries_obs_value")
	private String labEntriesObsValue;

	@Column(name="lab_entries_lab_remainder")
	private Integer labEntriesLabRemainder;

	@Column(name="lab_entries_printxsl")
	private String labEntriesPrintxsl;

	@Column(name="lab_entries_src_of_specimen")
	private String labEntriesSrcOfSpecimen;

	@Column(name="lab_entries_specimen_coll_vol")
	private String labEntriesSpecimenCollVol;

	@Column(name="lab_entries_coll_vol_unit")
	private String labEntriesCollVolUnit;

	@Column(name="lab_entries_is_fasting")
	private Boolean labEntriesIsFasting;

	@Column(name="lab_entries_proc_leafid")
	private Integer labEntriesProcLeafid;

	@Column(name="lab_entries_ischdplab")
	private Integer labEntriesIschdplab;

	@Column(name="lab_entries_cpt_units")
	private String labEntriesCptUnits;

	@Column(name="lab_entries_instruction")
	private String labEntriesInstruction;

	@Column(name="lab_entries_setid")
	private Integer labEntriesSetid;

	@Column(name="lab_entries_dosage")
	private String labEntriesDosage;

	@Column(name="lab_entries_modifiedby")
	private Integer labEntriesModifiedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="lab_entries_modifiedon")
	private Timestamp labEntriesModifiedon;

	@Column(name="lab_entries_transactionid")
	private String labEntriesTransactionid;

	@Column(name="lab_entries_drugxml")
	private String labEntriesDrugxml;

	@Column(name="lab_entries_ndc")
	private String labEntriesNdc;

	@Column(name="lab_entries_scheduleddate")
	private String labEntriesScheduleddate;

	@Column(name="lab_entries_vaccine_vis")
	private String labEntriesVaccineVis;

	@Column(name="lab_entries_cvx")
	private String labEntriesCvx;

	@Column(name="lab_entries_prelimresult")
	private String labEntriesPrelimresult;

	@Column(name="lab_entries_lab_accessionno")
	private String labEntriesLabAccessionno;

	@Column(name="lab_entries_loinc")
	private String labEntriesLoinc;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="lab_entries_specimendate")
	private Timestamp labEntriesSpecimendate;

	@Column(name="lab_entries_cond_of_specimen")
	private String labEntriesCondOfSpecimen;

	@Column(name="lab_entries_chartid")
	private Integer labEntriesChartid;

	@Column(name="lab_entries_sepcimen_id")
	private Integer labEntriesSepcimenId;

	@Column(name="lab_entries_dx5code")
	private String labEntriesDx5code;

	@Column(name="lab_entries_dx6code")
	private String labEntriesDx6code;

	@Column(name="lab_entries_dx7code")
	private String labEntriesDx7code;

	@Column(name="lab_entries_dx8code")
	private String labEntriesDx8code;

	@Column(name="lab_entries_immunizationstatus")
	private Integer labEntriesImmunizationstatus;

	@Column(name="lab_entries_dosage_level")
	private Integer labEntriesDosageLevel;

	@Column(name="lab_entries_status")
	private Integer labEntriesStatus;

	@Column(name="lab_entries_dx1code_codesystem")
	private String labEntriesDx1codeCodesystem;

	@Column(name="lab_entries_dx2code_codesystem")
	private String labEntriesDx2codeCodesystem;

	@Column(name="lab_entries_dx3code_codesystem")
	private String labEntriesDx3codeCodesystem;

	@Column(name="lab_entries_dx4code_codesystem")
	private String labEntriesDx4codeCodesystem;

	@Column(name="lab_entries_dx5code_codesystem")
	private String labEntriesDx5codeCodesystem;

	@Column(name="lab_entries_dx6code_codesystem")
	private String labEntriesDx6codeCodesystem;

	@Column(name="lab_entries_dx7code_codesystem")
	private String labEntriesDx7codeCodesystem;

	@Column(name="lab_entries_dx8code_codesystem")
	private String labEntriesDx8codeCodesystem;

	@Column(name="lab_entries_administration_notes")
	private Integer labEntriesAdministrationNotes;

	@Column(name="lab_entries_refusalreason")
	private Integer labEntriesRefusalreason;

	@Column(name="lab_entries_visinformation")
	private String labEntriesVisinformation;

	@Column(name="lab_entires_persumeimmunityifo")
	private String labEntiresPersumeimmunityifo;

	@Column(name="lab_entries_entered_by")
	private Integer labEntriesEnteredBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="lab_entries_entered_on")
	private Timestamp labEntriesEnteredOn;

	@Column(name="lab_entries_bodysite_code")
	private String labEntriesBodysiteCode;

	@Column(name="lab_entries_bodysite_desc")
	private String labEntriesBodysiteDesc;

	@Column(name="lab_entries_strength")
	private String labEntriesStrength;

	@Column(name="lab_entries_basedose")
	private String labEntriesBasedose;

	@Column(name="lab_entries_basedose_unit")
	private String labEntriesBasedoseUnit;

	@Column(name="lab_entries_strength_unit")
	private String labEntriesStrengthUnit;

	@Column(name="lab_entries_faxinfonotes")
	private String labEntriesFaxinfonotes;

	@Column(name="lab_entries_clinical_info")
	private String labEntriesClinicalInfo;

	@Column(name="lab_entries_dx1code_codedesc")
	private String labEntriesDx1codeCodedesc;

	@Column(name="lab_entries_dx2code_codedesc")
	private String labEntriesDx2codeCodedesc;

	@Column(name="lab_entries_dx3code_codedesc")
	private String labEntriesDx3codeCodedesc;

	@Column(name="lab_entries_dx4code_codedesc")
	private String labEntriesDx4codeCodedesc;

	@Column(name="lab_entries_dx5code_codedesc")
	private String labEntriesDx5codeCodedesc;

	@Column(name="lab_entries_dx6code_codedesc")
	private String labEntriesDx6codeCodedesc;

	@Column(name="lab_entries_dx7code_codedesc")
	private String labEntriesDx7codeCodedesc;

	@Column(name="lab_entries_dx8code_codedesc")
	private String labEntriesDx8codeCodedesc;

	@Column(name="lab_entries_delinquency_days")
	private Integer labEntriesDelinquencyDays;

	@Column(name="lab_entries_critical_status")
	private Integer labEntriesCriticalStatus;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="lab_entries_next_reminder_date")
	private Timestamp labEntriesNextReminderDate;

	@Column(name="lab_entries_reminder_comments")
	private String labEntriesReminderComments;
	
	@Column(name="lab_entries_snomed")
	private String labEntriesSnomed;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="lab_entries_ord_by", referencedColumnName="emp_profile_empid" , insertable=false, updatable=false)
	private EmployeeProfile empProfile;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="lab_entries_perf_by", referencedColumnName="emp_profile_empid" , insertable=false, updatable=false)
	private EmployeeProfile labPerformedBy;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="lab_entries_chartid", referencedColumnName="chart_id" , insertable=false, updatable=false)
	private Chart chart;
		
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="lab_entries_test_id", referencedColumnName="lab_description_testid" , insertable=false, updatable=false)
	private LabDescription labDescriptionTable;
		
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="lab_entries_encounter_id", referencedColumnName="encounter_id" , insertable=false, updatable=false)
	private Encounter encounter;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="lab_entries_groupid", referencedColumnName="lab_groups_id" , insertable=false, updatable=false)
	private LabGroups labGroups;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="lab_entries_sepcimen_id", referencedColumnName="specimen_id" , insertable=false, updatable=false)
	private Specimen specimen;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@OneToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="lab_entries_testdetail_id",referencedColumnName="vaccination_consent_form_testdetail_id", insertable=false, updatable=false)
	private VaccinationConsentForm vaccinationConsentForm;	
	
	@OneToMany(mappedBy="labEntriesTable")
	@JsonBackReference
	private List<LabEntriesParameter> parameters;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="lab_entries_lot_no", referencedColumnName="vaccine_order_details_id", insertable=false, updatable=false)
	private VaccineOrderDetails vaccineOrderDetails;
	
	@OneToMany(mappedBy="labEntries")
	private List<FileDetails> fileDetails;
	
	
	
	
	public List<LabEntriesParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<LabEntriesParameter> parameters) {
		this.parameters = parameters;
	}

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public LabDescription getLabDescriptionTable() {
		return labDescriptionTable;
	}

	public void setLabDescriptionTable(LabDescription labDescriptionTable) {
		this.labDescriptionTable = labDescriptionTable;
	}

	public VaccinationConsentForm getVaccinationConsentForm() {
		return vaccinationConsentForm;
	}

	public void setVaccinationConsentForm(VaccinationConsentForm vaccinationConsentForm) {
		this.vaccinationConsentForm = vaccinationConsentForm;
	}

	public Specimen getSpecimen() {
		return specimen;
	}

	public void setSpecimen(Specimen specimen) {
		this.specimen = specimen;
	}

	public LabGroups getLabGroups() {
		return labGroups;
	}

	public void setLabGroups(LabGroups labGroups) {
		this.labGroups = labGroups;
	}

	public EmployeeProfile getEmpProfile() {
		return empProfile;
	}

	public void setEmpProfile(EmployeeProfile empProfile) {
		this.empProfile = empProfile;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public Integer getLabEntriesTestdetailId() {
		return labEntriesTestdetailId;
	}

	public void setLabEntriesTestdetailId(Integer labEntriesTestdetailId) {
		this.labEntriesTestdetailId = labEntriesTestdetailId;
	}

	public Integer getLabEntriesEncounterId() {
		return labEntriesEncounterId;
	}

	public void setLabEntriesEncounterId(Integer labEntriesEncounterId) {
		this.labEntriesEncounterId = labEntriesEncounterId;
	}

	public Integer getLabEntriesTestId() {
		return labEntriesTestId;
	}

	public void setLabEntriesTestId(Integer labEntriesTestId) {
		this.labEntriesTestId = labEntriesTestId;
	}

	public Integer getLabEntriesTestType() {
		return labEntriesTestType;
	}

	public void setLabEntriesTestType(Integer labEntriesTestType) {
		this.labEntriesTestType = labEntriesTestType;
	}

	public Integer getLabEntriesOrdBy() {
		return labEntriesOrdBy;
	}

	public void setLabEntriesOrdBy(Integer labEntriesOrdBy) {
		this.labEntriesOrdBy = labEntriesOrdBy;
	}

	public Timestamp getLabEntriesOrdOn() {
		return labEntriesOrdOn;
	}

	public void setLabEntriesOrdOn(Timestamp labEntriesOrdOn) {
		this.labEntriesOrdOn = labEntriesOrdOn;
	}

	public Integer getLabEntriesPerfBy() {
		return labEntriesPerfBy;
	}

	public void setLabEntriesPerfBy(Integer labEntriesPerfBy) {
		this.labEntriesPerfBy = labEntriesPerfBy;
	}

	public Timestamp getLabEntriesPerfOn() {
		return labEntriesPerfOn;
	}

	public void setLabEntriesPerfOn(Timestamp labEntriesPerfOn) {
		this.labEntriesPerfOn = labEntriesPerfOn;
	}

	public Integer getLabEntriesRevBy() {
		return labEntriesRevBy;
	}

	public void setLabEntriesRevBy(Integer labEntriesRevBy) {
		this.labEntriesRevBy = labEntriesRevBy;
	}

	public Timestamp getLabEntriesRevOn() {
		return labEntriesRevOn;
	}

	public void setLabEntriesRevOn(Timestamp labEntriesRevOn) {
		this.labEntriesRevOn = labEntriesRevOn;
	}

	public Integer getLabEntriesInformedBy() {
		return labEntriesInformedBy;
	}

	public void setLabEntriesInformedBy(Integer labEntriesInformedBy) {
		this.labEntriesInformedBy = labEntriesInformedBy;
	}

	public Timestamp getLabEntriesInformedOn() {
		return labEntriesInformedOn;
	}

	public void setLabEntriesInformedOn(Timestamp labEntriesInformedOn) {
		this.labEntriesInformedOn = labEntriesInformedOn;
	}

	public Integer getLabEntriesPrelimTestStatus() {
		return labEntriesPrelimTestStatus;
	}

	public void setLabEntriesPrelimTestStatus(Integer labEntriesPrelimTestStatus) {
		this.labEntriesPrelimTestStatus = labEntriesPrelimTestStatus;
	}

	public Integer getLabEntriesConfirmTestStatus() {
		return labEntriesConfirmTestStatus;
	}

	public void setLabEntriesConfirmTestStatus(Integer labEntriesConfirmTestStatus) {
		this.labEntriesConfirmTestStatus = labEntriesConfirmTestStatus;
	}

	public Integer getLabEntriesDx1() {
		return labEntriesDx1;
	}

	public void setLabEntriesDx1(Integer labEntriesDx1) {
		this.labEntriesDx1 = labEntriesDx1;
	}

	public Integer getLabEntriesDx2() {
		return labEntriesDx2;
	}

	public void setLabEntriesDx2(Integer labEntriesDx2) {
		this.labEntriesDx2 = labEntriesDx2;
	}

	public Integer getLabEntriesTestStatus() {
		return labEntriesTestStatus;
	}

	public void setLabEntriesTestStatus(Integer labEntriesTestStatus) {
		this.labEntriesTestStatus = labEntriesTestStatus;
	}

	public Integer getLabEntriesRefdocId() {
		return labEntriesRefdocId;
	}

	public void setLabEntriesRefdocId(Integer labEntriesRefdocId) {
		this.labEntriesRefdocId = labEntriesRefdocId;
	}

	public String getLabEntriesTestDesc() {
		return labEntriesTestDesc;
	}

	public void setLabEntriesTestDesc(String labEntriesTestDesc) {
		this.labEntriesTestDesc = labEntriesTestDesc;
	}

	public String getLabEntriesScangroupId() {
		return labEntriesScangroupId;
	}

	public void setLabEntriesScangroupId(String labEntriesScangroupId) {
		this.labEntriesScangroupId = labEntriesScangroupId;
	}

	public String getLabEntriesCpt() {
		return labEntriesCpt;
	}

	public void setLabEntriesCpt(String labEntriesCpt) {
		this.labEntriesCpt = labEntriesCpt;
	}

	public String getLabEntriesShortNotes() {
		return labEntriesShortNotes;
	}

	public void setLabEntriesShortNotes(String labEntriesShortNotes) {
		this.labEntriesShortNotes = labEntriesShortNotes;
	}

	public String getLabEntriesResultNotes() {
		return labEntriesResultNotes;
	}

	public void setLabEntriesResultNotes(String labEntriesResultNotes) {
		this.labEntriesResultNotes = labEntriesResultNotes;
	}

	public String getLabEntriesResultParam() {
		return labEntriesResultParam;
	}

	public void setLabEntriesResultParam(String labEntriesResultParam) {
		this.labEntriesResultParam = labEntriesResultParam;
	}

	public Integer getLabEntriesLotNo() {
		return labEntriesLotNo;
	}

	public void setLabEntriesLotNo(Integer labEntriesLotNo) {
		this.labEntriesLotNo = labEntriesLotNo;
	}

	public Integer getLabEntriesGroupid() {
		return labEntriesGroupid;
	}

	public void setLabEntriesGroupid(Integer labEntriesGroupid) {
		this.labEntriesGroupid = labEntriesGroupid;
	}

	public Boolean getLabEntriesIsBillable() {
		return labEntriesIsBillable;
	}

	public void setLabEntriesIsBillable(Boolean labEntriesIsBillable) {
		this.labEntriesIsBillable = labEntriesIsBillable;
	}

	public Integer getLabEntriesUnits() {
		return labEntriesUnits;
	}

	public void setLabEntriesUnits(Integer labEntriesUnits) {
		this.labEntriesUnits = labEntriesUnits;
	}

	public Integer getLabEntriesZunits() {
		return labEntriesZunits;
	}

	public void setLabEntriesZunits(Integer labEntriesZunits) {
		this.labEntriesZunits = labEntriesZunits;
	}

	public String getLabEntriesLabRoute() {
		return labEntriesLabRoute;
	}

	public void setLabEntriesLabRoute(String labEntriesLabRoute) {
		this.labEntriesLabRoute = labEntriesLabRoute;
	}

	public String getLabEntriesLabSite() {
		return labEntriesLabSite;
	}

	public void setLabEntriesLabSite(String labEntriesLabSite) {
		this.labEntriesLabSite = labEntriesLabSite;
	}

	public String getLabEntriesShortorderNotes() {
		return labEntriesShortorderNotes;
	}

	public void setLabEntriesShortorderNotes(String labEntriesShortorderNotes) {
		this.labEntriesShortorderNotes = labEntriesShortorderNotes;
	}

	public String getLabEntriesObsUnits() {
		return labEntriesObsUnits;
	}

	public void setLabEntriesObsUnits(String labEntriesObsUnits) {
		this.labEntriesObsUnits = labEntriesObsUnits;
	}

	public Integer getLabEntriesObsId() {
		return labEntriesObsId;
	}

	public void setLabEntriesObsId(Integer labEntriesObsId) {
		this.labEntriesObsId = labEntriesObsId;
	}

	public String getLabEntriesObsValue() {
		return labEntriesObsValue;
	}

	public void setLabEntriesObsValue(String labEntriesObsValue) {
		this.labEntriesObsValue = labEntriesObsValue;
	}

	public Integer getLabEntriesLabRemainder() {
		return labEntriesLabRemainder;
	}

	public void setLabEntriesLabRemainder(Integer labEntriesLabRemainder) {
		this.labEntriesLabRemainder = labEntriesLabRemainder;
	}

	public String getLabEntriesPrintxsl() {
		return labEntriesPrintxsl;
	}

	public void setLabEntriesPrintxsl(String labEntriesPrintxsl) {
		this.labEntriesPrintxsl = labEntriesPrintxsl;
	}

	public String getLabEntriesSrcOfSpecimen() {
		return labEntriesSrcOfSpecimen;
	}

	public void setLabEntriesSrcOfSpecimen(String labEntriesSrcOfSpecimen) {
		this.labEntriesSrcOfSpecimen = labEntriesSrcOfSpecimen;
	}

	public String getLabEntriesSpecimenCollVol() {
		return labEntriesSpecimenCollVol;
	}

	public void setLabEntriesSpecimenCollVol(String labEntriesSpecimenCollVol) {
		this.labEntriesSpecimenCollVol = labEntriesSpecimenCollVol;
	}

	public String getLabEntriesCollVolUnit() {
		return labEntriesCollVolUnit;
	}

	public void setLabEntriesCollVolUnit(String labEntriesCollVolUnit) {
		this.labEntriesCollVolUnit = labEntriesCollVolUnit;
	}

	public Boolean getLabEntriesIsFasting() {
		return labEntriesIsFasting;
	}

	public void setLabEntriesIsFasting(Boolean labEntriesIsFasting) {
		this.labEntriesIsFasting = labEntriesIsFasting;
	}

	public Integer getLabEntriesProcLeafid() {
		return labEntriesProcLeafid;
	}

	public void setLabEntriesProcLeafid(Integer labEntriesProcLeafid) {
		this.labEntriesProcLeafid = labEntriesProcLeafid;
	}

	public Integer getLabEntriesIschdplab() {
		return labEntriesIschdplab;
	}

	public void setLabEntriesIschdplab(Integer labEntriesIschdplab) {
		this.labEntriesIschdplab = labEntriesIschdplab;
	}

	public String getLabEntriesCptUnits() {
		return labEntriesCptUnits;
	}

	public void setLabEntriesCptUnits(String labEntriesCptUnits) {
		this.labEntriesCptUnits = labEntriesCptUnits;
	}

	public String getLabEntriesInstruction() {
		return labEntriesInstruction;
	}

	public void setLabEntriesInstruction(String labEntriesInstruction) {
		this.labEntriesInstruction = labEntriesInstruction;
	}

	public Integer getLabEntriesSetid() {
		return labEntriesSetid;
	}

	public void setLabEntriesSetid(Integer labEntriesSetid) {
		this.labEntriesSetid = labEntriesSetid;
	}

	public String getLabEntriesDosage() {
		return labEntriesDosage;
	}

	public void setLabEntriesDosage(String labEntriesDosage) {
		this.labEntriesDosage = labEntriesDosage;
	}

	public Integer getLabEntriesModifiedby() {
		return labEntriesModifiedby;
	}

	public void setLabEntriesModifiedby(Integer labEntriesModifiedby) {
		this.labEntriesModifiedby = labEntriesModifiedby;
	}

	public Timestamp getLabEntriesModifiedon() {
		return labEntriesModifiedon;
	}

	public void setLabEntriesModifiedon(Timestamp labEntriesModifiedon) {
		this.labEntriesModifiedon = labEntriesModifiedon;
	}

	public String getLabEntriesTransactionid() {
		return labEntriesTransactionid;
	}

	public void setLabEntriesTransactionid(String labEntriesTransactionid) {
		this.labEntriesTransactionid = labEntriesTransactionid;
	}

	public String getLabEntriesDrugxml() {
		return labEntriesDrugxml;
	}

	public void setLabEntriesDrugxml(String labEntriesDrugxml) {
		this.labEntriesDrugxml = labEntriesDrugxml;
	}

	public String getLabEntriesNdc() {
		return labEntriesNdc;
	}

	public void setLabEntriesNdc(String labEntriesNdc) {
		this.labEntriesNdc = labEntriesNdc;
	}

	public String getLabEntriesScheduleddate() {
		return labEntriesScheduleddate;
	}

	public void setLabEntriesScheduleddate(String labEntriesScheduleddate) {
		this.labEntriesScheduleddate = labEntriesScheduleddate;
	}

	public String getLabEntriesVaccineVis() {
		return labEntriesVaccineVis;
	}

	public void setLabEntriesVaccineVis(String labEntriesVaccineVis) {
		this.labEntriesVaccineVis = labEntriesVaccineVis;
	}

	public String getLabEntriesCvx() {
		return labEntriesCvx;
	}

	public void setLabEntriesCvx(String labEntriesCvx) {
		this.labEntriesCvx = labEntriesCvx;
	}

	public String getLabEntriesDx1code() {
		return labEntriesDx1code;
	}

	public void setLabEntriesDx1code(String labEntriesDx1code) {
		this.labEntriesDx1code = labEntriesDx1code;
	}

	public String getLabEntriesDx2code() {
		return labEntriesDx2code;
	}

	public void setLabEntriesDx2code(String labEntriesDx2code) {
		this.labEntriesDx2code = labEntriesDx2code;
	}

	public String getLabEntriesPrelimresult() {
		return labEntriesPrelimresult;
	}

	public void setLabEntriesPrelimresult(String labEntriesPrelimresult) {
		this.labEntriesPrelimresult = labEntriesPrelimresult;
	}

	public String getLabEntriesLabAccessionno() {
		return labEntriesLabAccessionno;
	}

	public void setLabEntriesLabAccessionno(String labEntriesLabAccessionno) {
		this.labEntriesLabAccessionno = labEntriesLabAccessionno;
	}

	public String getLabEntriesDx3code() {
		return labEntriesDx3code;
	}

	public void setLabEntriesDx3code(String labEntriesDx3code) {
		this.labEntriesDx3code = labEntriesDx3code;
	}

	public String getLabEntriesDx4code() {
		return labEntriesDx4code;
	}

	public void setLabEntriesDx4code(String labEntriesDx4code) {
		this.labEntriesDx4code = labEntriesDx4code;
	}

	public String getLabEntriesLoinc() {
		return labEntriesLoinc;
	}

	public void setLabEntriesLoinc(String labEntriesLoinc) {
		this.labEntriesLoinc = labEntriesLoinc;
	}

	public Timestamp getLabEntriesSpecimendate() {
		return labEntriesSpecimendate;
	}

	public void setLabEntriesSpecimendate(Timestamp labEntriesSpecimendate) {
		this.labEntriesSpecimendate = labEntriesSpecimendate;
	}

	public String getLabEntriesCondOfSpecimen() {
		return labEntriesCondOfSpecimen;
	}

	public void setLabEntriesCondOfSpecimen(String labEntriesCondOfSpecimen) {
		this.labEntriesCondOfSpecimen = labEntriesCondOfSpecimen;
	}

	public Integer getLabEntriesChartid() {
		return labEntriesChartid;
	}

	public void setLabEntriesChartid(Integer labEntriesChartid) {
		this.labEntriesChartid = labEntriesChartid;
	}

	public Integer getLabEntriesSepcimenId() {
		return labEntriesSepcimenId;
	}

	public void setLabEntriesSepcimenId(Integer labEntriesSepcimenId) {
		this.labEntriesSepcimenId = labEntriesSepcimenId;
	}

	public String getLabEntriesDx5code() {
		return labEntriesDx5code;
	}

	public void setLabEntriesDx5code(String labEntriesDx5code) {
		this.labEntriesDx5code = labEntriesDx5code;
	}

	public String getLabEntriesDx6code() {
		return labEntriesDx6code;
	}

	public void setLabEntriesDx6code(String labEntriesDx6code) {
		this.labEntriesDx6code = labEntriesDx6code;
	}

	public String getLabEntriesDx7code() {
		return labEntriesDx7code;
	}

	public void setLabEntriesDx7code(String labEntriesDx7code) {
		this.labEntriesDx7code = labEntriesDx7code;
	}

	public String getLabEntriesDx8code() {
		return labEntriesDx8code;
	}

	public void setLabEntriesDx8code(String labEntriesDx8code) {
		this.labEntriesDx8code = labEntriesDx8code;
	}

	public Integer getLabEntriesImmunizationstatus() {
		return labEntriesImmunizationstatus;
	}

	public void setLabEntriesImmunizationstatus(Integer labEntriesImmunizationstatus) {
		this.labEntriesImmunizationstatus = labEntriesImmunizationstatus;
	}

	public Integer getLabEntriesDosageLevel() {
		return labEntriesDosageLevel;
	}

	public void setLabEntriesDosageLevel(Integer labEntriesDosageLevel) {
		this.labEntriesDosageLevel = labEntriesDosageLevel;
	}

	public Integer getLabEntriesStatus() {
		return labEntriesStatus;
	}

	public void setLabEntriesStatus(Integer labEntriesStatus) {
		this.labEntriesStatus = labEntriesStatus;
	}

	public String getLabEntriesDx1codeCodesystem() {
		return labEntriesDx1codeCodesystem;
	}

	public void setLabEntriesDx1codeCodesystem(String labEntriesDx1codeCodesystem) {
		this.labEntriesDx1codeCodesystem = labEntriesDx1codeCodesystem;
	}

	public String getLabEntriesDx2codeCodesystem() {
		return labEntriesDx2codeCodesystem;
	}

	public void setLabEntriesDx2codeCodesystem(String labEntriesDx2codeCodesystem) {
		this.labEntriesDx2codeCodesystem = labEntriesDx2codeCodesystem;
	}

	public String getLabEntriesDx3codeCodesystem() {
		return labEntriesDx3codeCodesystem;
	}

	public void setLabEntriesDx3codeCodesystem(String labEntriesDx3codeCodesystem) {
		this.labEntriesDx3codeCodesystem = labEntriesDx3codeCodesystem;
	}

	public String getLabEntriesDx4codeCodesystem() {
		return labEntriesDx4codeCodesystem;
	}

	public void setLabEntriesDx4codeCodesystem(String labEntriesDx4codeCodesystem) {
		this.labEntriesDx4codeCodesystem = labEntriesDx4codeCodesystem;
	}

	public String getLabEntriesDx5codeCodesystem() {
		return labEntriesDx5codeCodesystem;
	}

	public void setLabEntriesDx5codeCodesystem(String labEntriesDx5codeCodesystem) {
		this.labEntriesDx5codeCodesystem = labEntriesDx5codeCodesystem;
	}

	public String getLabEntriesDx6codeCodesystem() {
		return labEntriesDx6codeCodesystem;
	}

	public void setLabEntriesDx6codeCodesystem(String labEntriesDx6codeCodesystem) {
		this.labEntriesDx6codeCodesystem = labEntriesDx6codeCodesystem;
	}

	public String getLabEntriesDx7codeCodesystem() {
		return labEntriesDx7codeCodesystem;
	}

	public void setLabEntriesDx7codeCodesystem(String labEntriesDx7codeCodesystem) {
		this.labEntriesDx7codeCodesystem = labEntriesDx7codeCodesystem;
	}

	public String getLabEntriesDx8codeCodesystem() {
		return labEntriesDx8codeCodesystem;
	}

	public void setLabEntriesDx8codeCodesystem(String labEntriesDx8codeCodesystem) {
		this.labEntriesDx8codeCodesystem = labEntriesDx8codeCodesystem;
	}

	public Integer getLabEntriesAdministrationNotes() {
		return labEntriesAdministrationNotes;
	}

	public void setLabEntriesAdministrationNotes(
			Integer labEntriesAdministrationNotes) {
		this.labEntriesAdministrationNotes = labEntriesAdministrationNotes;
	}

	public Integer getLabEntriesRefusalreason() {
		return labEntriesRefusalreason;
	}

	public void setLabEntriesRefusalreason(Integer labEntriesRefusalreason) {
		this.labEntriesRefusalreason = labEntriesRefusalreason;
	}

	public String getLabEntriesVisinformation() {
		return labEntriesVisinformation;
	}

	public void setLabEntriesVisinformation(String labEntriesVisinformation) {
		this.labEntriesVisinformation = labEntriesVisinformation;
	}

	public String getLabEntiresPersumeimmunityifo() {
		return labEntiresPersumeimmunityifo;
	}

	public void setLabEntiresPersumeimmunityifo(String labEntiresPersumeimmunityifo) {
		this.labEntiresPersumeimmunityifo = labEntiresPersumeimmunityifo;
	}

	public Integer getLabEntriesEnteredBy() {
		return labEntriesEnteredBy;
	}

	public void setLabEntriesEnteredBy(Integer labEntriesEnteredBy) {
		this.labEntriesEnteredBy = labEntriesEnteredBy;
	}

	public Timestamp getLabEntriesEnteredOn() {
		return labEntriesEnteredOn;
	}

	public void setLabEntriesEnteredOn(Timestamp labEntriesEnteredOn) {
		this.labEntriesEnteredOn = labEntriesEnteredOn;
	}

	public String getLabEntriesBodysiteCode() {
		return labEntriesBodysiteCode;
	}

	public void setLabEntriesBodysiteCode(String labEntriesBodysiteCode) {
		this.labEntriesBodysiteCode = labEntriesBodysiteCode;
	}

	public String getLabEntriesBodysiteDesc() {
		return labEntriesBodysiteDesc;
	}

	public void setLabEntriesBodysiteDesc(String labEntriesBodysiteDesc) {
		this.labEntriesBodysiteDesc = labEntriesBodysiteDesc;
	}

	public String getLabEntriesStrength() {
		return labEntriesStrength;
	}

	public void setLabEntriesStrength(String labEntriesStrength) {
		this.labEntriesStrength = labEntriesStrength;
	}

	public String getLabEntriesBasedose() {
		return labEntriesBasedose;
	}

	public void setLabEntriesBasedose(String labEntriesBasedose) {
		this.labEntriesBasedose = labEntriesBasedose;
	}

	public String getLabEntriesBasedoseUnit() {
		return labEntriesBasedoseUnit;
	}

	public void setLabEntriesBasedoseUnit(String labEntriesBasedoseUnit) {
		this.labEntriesBasedoseUnit = labEntriesBasedoseUnit;
	}

	public String getLabEntriesStrengthUnit() {
		return labEntriesStrengthUnit;
	}

	public void setLabEntriesStrengthUnit(String labEntriesStrengthUnit) {
		this.labEntriesStrengthUnit = labEntriesStrengthUnit;
	}

	public String getLabEntriesFaxinfonotes() {
		return labEntriesFaxinfonotes;
	}

	public void setLabEntriesFaxinfonotes(String labEntriesFaxinfonotes) {
		this.labEntriesFaxinfonotes = labEntriesFaxinfonotes;
	}

	public String getLabEntriesClinicalInfo() {
		return labEntriesClinicalInfo;
	}

	public void setLabEntriesClinicalInfo(String labEntriesClinicalInfo) {
		this.labEntriesClinicalInfo = labEntriesClinicalInfo;
	}

	public String getLabEntriesDx1codeCodedesc() {
		return labEntriesDx1codeCodedesc;
	}

	public void setLabEntriesDx1codeCodedesc(String labEntriesDx1codeCodedesc) {
		this.labEntriesDx1codeCodedesc = labEntriesDx1codeCodedesc;
	}

	public String getLabEntriesDx2codeCodedesc() {
		return labEntriesDx2codeCodedesc;
	}

	public void setLabEntriesDx2codeCodedesc(String labEntriesDx2codeCodedesc) {
		this.labEntriesDx2codeCodedesc = labEntriesDx2codeCodedesc;
	}

	public String getLabEntriesDx3codeCodedesc() {
		return labEntriesDx3codeCodedesc;
	}

	public void setLabEntriesDx3codeCodedesc(String labEntriesDx3codeCodedesc) {
		this.labEntriesDx3codeCodedesc = labEntriesDx3codeCodedesc;
	}

	public String getLabEntriesDx4codeCodedesc() {
		return labEntriesDx4codeCodedesc;
	}

	public void setLabEntriesDx4codeCodedesc(String labEntriesDx4codeCodedesc) {
		this.labEntriesDx4codeCodedesc = labEntriesDx4codeCodedesc;
	}

	public String getLabEntriesDx5codeCodedesc() {
		return labEntriesDx5codeCodedesc;
	}

	public void setLabEntriesDx5codeCodedesc(String labEntriesDx5codeCodedesc) {
		this.labEntriesDx5codeCodedesc = labEntriesDx5codeCodedesc;
	}

	public String getLabEntriesDx6codeCodedesc() {
		return labEntriesDx6codeCodedesc;
	}

	public void setLabEntriesDx6codeCodedesc(String labEntriesDx6codeCodedesc) {
		this.labEntriesDx6codeCodedesc = labEntriesDx6codeCodedesc;
	}

	public String getLabEntriesDx7codeCodedesc() {
		return labEntriesDx7codeCodedesc;
	}

	public void setLabEntriesDx7codeCodedesc(String labEntriesDx7codeCodedesc) {
		this.labEntriesDx7codeCodedesc = labEntriesDx7codeCodedesc;
	}
	
	public String getLabEntriesDx8codeCodedesc() {
		return labEntriesDx8codeCodedesc;
	}

	public Integer getLabEntriesCriticalStatus() {
		return labEntriesCriticalStatus;
	}

	public void setLabEntriesCriticalStatus(Integer labEntriesCriticalStatus) {
		this.labEntriesCriticalStatus = labEntriesCriticalStatus;
	}

	public Timestamp getLabEntriesNextReminderDate() {
		return labEntriesNextReminderDate;
	}

	public void setLabEntriesNextReminderDate(Timestamp labEntriesNextReminderDate) {
		this.labEntriesNextReminderDate = labEntriesNextReminderDate;
	}

	public String getLabEntriesReminderComments() {
		return labEntriesReminderComments;
	}

	public void setLabEntriesReminderComments(String labEntriesReminderComments) {
		this.labEntriesReminderComments = labEntriesReminderComments;
	}

	public Integer getLabEntriesDelinquencyDays() {
		return labEntriesDelinquencyDays;
	}

	public void setLabEntriesDelinquencyDays(Integer labEntriesDelinquencyDays) {
		this.labEntriesDelinquencyDays = labEntriesDelinquencyDays;
	}
	
	public void setLabEntriesDx8codeCodedesc(String labEntriesDx8codeCodedesc) {
		this.labEntriesDx8codeCodedesc = labEntriesDx8codeCodedesc;
	}

	public EmployeeProfile getLabPerformedBy() {
		return labPerformedBy;
	}

	public void setLabPerformedBy(EmployeeProfile labPerformedBy) {
		this.labPerformedBy = labPerformedBy;
	}

	public VaccineOrderDetails getVaccineOrderDetails() {
		return vaccineOrderDetails;
	}

	public void setVaccineOrderDetails(VaccineOrderDetails vaccineOrderDetails) {
		this.vaccineOrderDetails = vaccineOrderDetails;
	}

	public List<FileDetails> getFileDetails() {
		return fileDetails;
	}

	public void setFileDetails(List<FileDetails> fileDetails) {
		this.fileDetails = fileDetails;
	}
	
	public void setLabEntriesSnomed(String labEntriesSnomed)
	{
		this.labEntriesSnomed = labEntriesSnomed;
	}
	public String getLabEntriesSnomed()
	{
		return labEntriesSnomed;
	}
	
	public LabEntries(){
		super();
	}
	
	
	public LabEntries(Integer labEntriesTestdetailId,Integer labEntriesTestStatus){
		this.labEntriesTestdetailId=labEntriesTestdetailId;
		this.labEntriesTestStatus=labEntriesTestStatus;
	}
	
	public LabEntries(Integer labEntriesTestdetailId){
		this.labEntriesTestdetailId=labEntriesTestdetailId;
	}
	public LabEntries(Integer labEntriesTestId,String labEntriesTestDesc){

		this.labEntriesTestId=labEntriesTestId;
		this.labEntriesTestDesc=labEntriesTestDesc;
		}
	
	public LabEntries(Integer labEntriesTestdetailId,String labEntriesTestDesc,
			Integer labEntriesEncounterId,Integer labEntriesTestId,
			Integer labEntriesTestStatus,Integer labEntriesGroupid,Boolean labEntriesIsBillable,String labEntriesCpt){
		this.labEntriesTestdetailId=labEntriesTestdetailId;
		this.labEntriesTestDesc=labEntriesTestDesc;
		this.labEntriesEncounterId=labEntriesEncounterId;
		this.labEntriesTestId=labEntriesTestId;
		this.labEntriesTestStatus=labEntriesTestStatus;
		this.labEntriesGroupid=labEntriesGroupid;
		this.labEntriesIsBillable=labEntriesIsBillable;
		this.labEntriesCpt=labEntriesCpt;
	}
	
	
	//logsheetparams
	//Timestamp labEntriesOrdOn,Timestamp labEntriesPerfOn,String labEntriesDrugxml,
	public LabEntries(Integer labEntriesTestId,String labEntriesTestDesc, Integer labEntriesConfirmTestStatus,
			Integer labEntriesPrelimTestStatus,Integer labEntriesStatus,String labEntriesDrugxml,
			Integer labEntriesTestStatus,String labEntriesOrdOn,String labEntriesPerfOn,
			Integer labEntriesEncounterId, Integer labEntriesTestdetailId, String labEntriesResultNotes){
		this.labEntriesTestId=labEntriesTestId;
		this.labEntriesTestDesc=labEntriesTestDesc;
		this.labEntriesConfirmTestStatus=labEntriesConfirmTestStatus;
		this.labEntriesPrelimTestStatus=labEntriesPrelimTestStatus;
		this.labEntriesStatus=labEntriesStatus;
		this.labEntriesDrugxml=labEntriesDrugxml;
		this.labEntriesTestStatus=labEntriesTestStatus;
		this.labEntriesReminderComments=labEntriesOrdOn;
		this.labEntriesScangroupId=labEntriesPerfOn;
		this.labEntriesEncounterId=labEntriesEncounterId;
		this.labEntriesTestdetailId=labEntriesTestdetailId;
		this.labEntriesResultNotes=labEntriesResultNotes;
	
	}
	
}