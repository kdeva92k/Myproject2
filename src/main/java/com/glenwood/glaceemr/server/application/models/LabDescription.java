package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@SuppressWarnings("serial")
@Entity
@Table(name = "lab_description")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabDescription implements Serializable {

	@Id
	@Column(name="lab_description_testid")
	private Integer labDescriptionTestid;

	@Column(name="lab_description_groupid", nullable=false)
	private Integer labDescriptionGroupid;

	@Column(name="lab_description_billable", columnDefinition="Boolean default true")
	private Boolean labDescriptionBillable;

	@Column(name="lab_description_default_type", columnDefinition="Integer default 1")
	private Integer labDescriptionDefaultType;

	@Column(name="lab_description_is_scan_req", columnDefinition="Boolean default true")
	private Boolean labDescriptionIsScanReq;

	@Column(name="lab_description_isalert_req", columnDefinition="Boolean default true")
	private Boolean labDescriptionIsalertReq;

	@Column(name="lab_description_isresult_req", columnDefinition="Boolean default true")
	private Boolean labDescriptionIsresultReq;

	@Column(name="lab_description_result_type")
	private Integer labDescriptionResultType;

	@Column(name="lab_description_isactive", columnDefinition="Boolean default true")
	private Boolean labDescriptionIsactive;

	@Column(name="lab_description_sortby")
	private Integer labDescriptionSortby;

	@Column(name="lab_description_test_desc", nullable=false, columnDefinition="String default ''", length=255)
	private String labDescriptionTestDesc;

	@Column(name="lab_description_def_cpt1", length=10)
	private String labDescriptionDefCpt1;

	@Column(name="lab_description_def_cpt2", length=10)
	private String labDescriptionDefCpt2;

	@Column(name="lab_description_def_cpt3", length=10)
	private String labDescriptionDefCpt3;

	@Column(name="lab_description_def_cpt4", length=10)
	private String labDescriptionDefCpt4;

	@Column(name="lab_description_def_dx1", length=10)
	private String labDescriptionDefDx1;

	@Column(name="lab_description_def_dx2", length=10)
	private String labDescriptionDefDx2;

	@Column(name="lab_description_def_notes", length=1000)
	private String labDescriptionDefNotes;

	@Column(name="lab_description_parameters", length=4000)
	private String labDescriptionParameters;

	@Column(name="lab_description_scan_groupid")
	private Integer labDescriptionScanGroupid;

	@Column(name="lab_description_leaftabid")
	private Integer labDescriptionLeaftabid;

	@Column(name="lab_description_showinlogsheet", columnDefinition="Boolean default false")
	private Boolean labDescriptionShowinlogsheet;

	@Column(name="lab_description_printxslurl", length=1000)
	private String labDescriptionPrintxslurl;

	@Column(name="lab_description_ordercode_mapping", length=600)
	private String labDescriptionOrdercodeMapping;

	@Column(name="lab_description_def_instruction")
	private String labDescriptionDefInstruction;

	@Column(name="lab_description_hitcount")
	private Integer labDescriptionHitcount;

	@Column(name="lab_description_def_mod1", length=10)
	private String labDescriptionDefMod1;

	@Column(name="lab_description_def_mod2", length=10)
	private String labDescriptionDefMod2;

	@Column(name="lab_description_def_mod3", length=10)
	private String labDescriptionDefMod3;

	@Column(name="lab_description_def_mod4", length=10)
	private String labDescriptionDefMod4;

	@Column(name="lab_description_drugs", length=2000)
	private String labDescriptionDrugs;

	@Column(name="lab_description_ndc", length=50)
	private String labDescriptionNdc;

	@Column(name="lab_description_cvx", length=50)
	private String labDescriptionCvx;

	@Column(name="lab_description_instruction")
	private String labDescriptionInstruction;

	@Column(name="lab_description_inst_filename", length=200)
	private String labDescriptionInstFilename;

	@Column(name="lab_description_loinc", length=50)
	private String labDescriptionLoinc;

	@Column(name="lab_description_vaccine_vis", length=100)
	private String labDescriptionVaccineVis;

	@Column(name="lab_description_isdefaultprint", columnDefinition="Boolean default false")
	private Boolean labDescriptionIsdefaultprint;

	@Column(name="lab_description_printsort")
	private Integer labDescriptionPrintsort;

	@Column(name="lab_description_def_mod12", length=10)
	private String labDescriptionDefMod12;

	@Column(name="lab_description_def_mod22", length=10)
	private String labDescriptionDefMod22;

	@Column(name="lab_description_def_mod32", length=10)
	private String labDescriptionDefMod32;

	@Column(name="lab_description_def_mod42", length=10)
	private String labDescriptionDefMod42;

	@Column(name="lab_description_testcategory_type")
	private Integer labDescriptionTestcategoryType;

	@Column(name="lab_description_isfasting", columnDefinition="Boolean default false")
	private Boolean labDescriptionIsfasting;

	@Column(name="lab_description_basedose_unit", length=50)
	private String labDescriptionBasedoseUnit;

	@Column(name="lab_description_basedose", length=50)
	private String labDescriptionBasedose;

	@Column(name="lab_description_strength", length=50)
	private String labDescriptionStrength;

	@Column(name="lab_description_strength_unit", length=50)
	private String labDescriptionStrengthUnit;
	
	@Column(name="lab_description_critical_status")
	private Integer labDescriptionCriticalStatus;
	
	@Column(name="lab_description_delinquency_days")
	private Integer  labDescriptionDelinquencyDays;
	
	@Column(name="lab_description_def_dx1code_codedesc")
	private String  labDescriptionDefDx1codeCodedesc;
	
	@Column(name="lab_description_def_dx2code_codedesc")
	private String  labDescriptionDefDx2codeCodedesc;
	
	@Column(name="lab_description_def_dx3code_codedesc")
	private String  labDescriptionDefDx3codeCodedesc;
	
	@Column(name="lab_description_def_dx4code_codedesc")
	private String  labDescriptionDefDx4codeCodedesc;
	
	@Column(name="lab_description_def_dx5code_codedesc")
	private String  labDescriptionDefDx5codeCodedesc;
	
	@Column(name="lab_description_def_dx6code_codedesc")
	private String  labDescriptionDefDx6codeCodedesc;
	
	@Column(name="lab_description_def_dx7code_codedesc")
	private String  labDescriptionDefDx7codeCodedesc;
	
	@Column(name="lab_description_def_dx8code_codedesc")
	private String  labDescriptionDefDx8codeCodedesc;
	
	@Column(name="lab_description_def_dx1code_codesystem")
	private String  labDescriptionDefDx1codeCodesystem;
	
	@Column(name="lab_description_def_dx2code_codesystem")
	private String  labDescriptionDefDx2codeCodesystem;
	
	@Column(name="lab_description_def_dx3code_codesystem")
	private String  labDescriptionDefDx3codeCodesystem;
	
	@Column(name="lab_description_def_dx4code_codesystem")
	private String  labDescriptionDefDx4codeCodesystem;
	
	@Column(name="lab_description_def_dx5code_codesystem")
	private String  labDescriptionDefDx5codeCodesystem;
	
	@Column(name="lab_description_def_dx6code_codesystem")
	private String  labDescriptionDefDx6codeCodesystem;
	
	@Column(name="lab_description_def_dx7code_codesystem")
	private String  labDescriptionDefDx7codeCodesystem;
	
	@Column(name="lab_description_def_dx8code_codesystem")
	private String  labDescriptionDefDx8codeCodesystem;
	
	@Column(name="lab_description_def_dx3")
	private String  labDescriptionDefDx3;
	
	@Column(name="lab_description_def_dx4")
	private String  labDescriptionDefDx4;
	
	@Column(name="lab_description_def_dx5")
	private String  labDescriptionDefDx5;
	
	@Column(name="lab_description_def_dx6")
	private String  labDescriptionDefDx6;
	
	@Column(name="lab_description_def_dx7")
	private String  labDescriptionDefDx7;
	
	@Column(name="lab_description_def_dx8")
	private String  labDescriptionDefDx8;
	
	@Column(name="lab_description_snomed")
	private String  labDescriptionSnomed;
	
	
	public LabDescription(){
		super();
	}
	
	public LabDescription(Integer labDescriptionTestid,
			Integer labDescriptionGroupid,String labDescriptionParameters,
			String labDescriptionDrugs,String labDescriptionTestDesc,Integer labDescriptionScanGroupid,String labDescriptionCvx,
			String labDescriptionLoinc){
		this.labDescriptionTestid=labDescriptionTestid;
		this.labDescriptionGroupid=labDescriptionGroupid;
		this.labDescriptionParameters=labDescriptionParameters;
		this.labDescriptionDrugs=labDescriptionDrugs;
		this.labDescriptionTestDesc=labDescriptionTestDesc;
		this.labDescriptionScanGroupid=labDescriptionScanGroupid;
		this.labDescriptionCvx=labDescriptionCvx;
		this.labDescriptionLoinc=labDescriptionLoinc;
	}
	 
	public LabDescription(Integer labDescriptionTestid,String labDescriptionTestDesc){
		this.labDescriptionTestid=labDescriptionTestid;
		this.labDescriptionTestDesc=labDescriptionTestDesc;
	}
	
	 
	
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="labDescription")
	@JsonBackReference
	List<Hl7ExternalTestmapping> hl7ExternalTestmappingTable;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="labDescriptionTable")
	@JsonManagedReference
	List<LabEntries> labEntriesTable;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="labDescriptionTable")
	@JsonManagedReference
	List<VaccineReport> vaccineReportTable;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="lab_description_testid", referencedColumnName="lab_freqorder_testid" , insertable=false, updatable=false)
	LabFreqorder labFreqOrder;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="lab_description_groupid", referencedColumnName="lab_groups_id" , insertable=false, updatable=false)
	LabGroups labGroups;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="labDescriptionTable")
	List<LabDescpParameters> labDescParams;
	
	public LabGroups getLabGroups() {
		return labGroups;
	}

	public void setLabGroups(LabGroups labGroups) {
		this.labGroups = labGroups;
	}

	public List<LabDescpParameters> getLabDescParams() {
		return labDescParams;
	}

	public void setLabDescParams(List<LabDescpParameters> labDescParams) {
		this.labDescParams = labDescParams;
	}

	public Integer getLabDescriptionTestid() {
		return labDescriptionTestid;
	}

	public void setLabDescriptionTestid(Integer labDescriptionTestid) {
		this.labDescriptionTestid = labDescriptionTestid;
	}

	public Integer getLabDescriptionGroupid() {
		return labDescriptionGroupid;
	}

	public void setLabDescriptionGroupid(Integer labDescriptionGroupid) {
		this.labDescriptionGroupid = labDescriptionGroupid;
	}

	public Boolean getLabDescriptionBillable() {
		return labDescriptionBillable;
	}

	public void setLabDescriptionBillable(Boolean labDescriptionBillable) {
		this.labDescriptionBillable = labDescriptionBillable;
	}

	public Integer getLabDescriptionDefaultType() {
		return labDescriptionDefaultType;
	}

	public void setLabDescriptionDefaultType(Integer labDescriptionDefaultType) {
		this.labDescriptionDefaultType = labDescriptionDefaultType;
	}

	public Boolean getLabDescriptionIsScanReq() {
		return labDescriptionIsScanReq;
	}

	public void setLabDescriptionIsScanReq(Boolean labDescriptionIsScanReq) {
		this.labDescriptionIsScanReq = labDescriptionIsScanReq;
	}

	public Boolean getLabDescriptionIsalertReq() {
		return labDescriptionIsalertReq;
	}

	public void setLabDescriptionIsalertReq(Boolean labDescriptionIsalertReq) {
		this.labDescriptionIsalertReq = labDescriptionIsalertReq;
	}

	public Boolean getLabDescriptionIsresultReq() {
		return labDescriptionIsresultReq;
	}

	public void setLabDescriptionIsresultReq(Boolean labDescriptionIsresultReq) {
		this.labDescriptionIsresultReq = labDescriptionIsresultReq;
	}

	public Integer getLabDescriptionResultType() {
		return labDescriptionResultType;
	}

	public void setLabDescriptionResultType(Integer labDescriptionResultType) {
		this.labDescriptionResultType = labDescriptionResultType;
	}

	public Boolean getLabDescriptionIsactive() {
		return labDescriptionIsactive;
	}

	public void setLabDescriptionIsactive(Boolean labDescriptionIsactive) {
		this.labDescriptionIsactive = labDescriptionIsactive;
	}

	public Integer getLabDescriptionSortby() {
		return labDescriptionSortby;
	}

	public void setLabDescriptionSortby(Integer labDescriptionSortby) {
		this.labDescriptionSortby = labDescriptionSortby;
	}

	public String getLabDescriptionTestDesc() {
		return labDescriptionTestDesc;
	}

	public void setLabDescriptionTestDesc(String labDescriptionTestDesc) {
		this.labDescriptionTestDesc = labDescriptionTestDesc;
	}

	public String getLabDescriptionDefCpt1() {
		return labDescriptionDefCpt1;
	}

	public void setLabDescriptionDefCpt1(String labDescriptionDefCpt1) {
		this.labDescriptionDefCpt1 = labDescriptionDefCpt1;
	}

	public String getLabDescriptionDefCpt2() {
		return labDescriptionDefCpt2;
	}

	public void setLabDescriptionDefCpt2(String labDescriptionDefCpt2) {
		this.labDescriptionDefCpt2 = labDescriptionDefCpt2;
	}

	public String getLabDescriptionDefCpt3() {
		return labDescriptionDefCpt3;
	}

	public void setLabDescriptionDefCpt3(String labDescriptionDefCpt3) {
		this.labDescriptionDefCpt3 = labDescriptionDefCpt3;
	}

	public String getLabDescriptionDefCpt4() {
		return labDescriptionDefCpt4;
	}

	public void setLabDescriptionDefCpt4(String labDescriptionDefCpt4) {
		this.labDescriptionDefCpt4 = labDescriptionDefCpt4;
	}

	public String getLabDescriptionDefDx1() {
		return labDescriptionDefDx1;
	}

	public void setLabDescriptionDefDx1(String labDescriptionDefDx1) {
		this.labDescriptionDefDx1 = labDescriptionDefDx1;
	}

	public String getLabDescriptionDefDx2() {
		return labDescriptionDefDx2;
	}

	public void setLabDescriptionDefDx2(String labDescriptionDefDx2) {
		this.labDescriptionDefDx2 = labDescriptionDefDx2;
	}

	public String getLabDescriptionDefNotes() {
		return labDescriptionDefNotes;
	}

	public void setLabDescriptionDefNotes(String labDescriptionDefNotes) {
		this.labDescriptionDefNotes = labDescriptionDefNotes;
	}

	public String getLabDescriptionParameters() {
		return labDescriptionParameters;
	}

	public void setLabDescriptionParameters(String labDescriptionParameters) {
		this.labDescriptionParameters = labDescriptionParameters;
	}

	public Integer getLabDescriptionScanGroupid() {
		return labDescriptionScanGroupid;
	}

	public void setLabDescriptionScanGroupid(Integer labDescriptionScanGroupid) {
		this.labDescriptionScanGroupid = labDescriptionScanGroupid;
	}

	public Integer getLabDescriptionLeaftabid() {
		return labDescriptionLeaftabid;
	}

	public void setLabDescriptionLeaftabid(Integer labDescriptionLeaftabid) {
		this.labDescriptionLeaftabid = labDescriptionLeaftabid;
	}

	public Boolean getLabDescriptionShowinlogsheet() {
		return labDescriptionShowinlogsheet;
	}

	public void setLabDescriptionShowinlogsheet(Boolean labDescriptionShowinlogsheet) {
		this.labDescriptionShowinlogsheet = labDescriptionShowinlogsheet;
	}

	public String getLabDescriptionPrintxslurl() {
		return labDescriptionPrintxslurl;
	}

	public void setLabDescriptionPrintxslurl(String labDescriptionPrintxslurl) {
		this.labDescriptionPrintxslurl = labDescriptionPrintxslurl;
	}

	public String getLabDescriptionOrdercodeMapping() {
		return labDescriptionOrdercodeMapping;
	}

	public void setLabDescriptionOrdercodeMapping(
			String labDescriptionOrdercodeMapping) {
		this.labDescriptionOrdercodeMapping = labDescriptionOrdercodeMapping;
	}

	public String getLabDescriptionDefInstruction() {
		return labDescriptionDefInstruction;
	}

	public void setLabDescriptionDefInstruction(String labDescriptionDefInstruction) {
		this.labDescriptionDefInstruction = labDescriptionDefInstruction;
	}

	public Integer getLabDescriptionHitcount() {
		return labDescriptionHitcount;
	}

	public void setLabDescriptionHitcount(Integer labDescriptionHitcount) {
		this.labDescriptionHitcount = labDescriptionHitcount;
	}

	public String getLabDescriptionDefMod1() {
		return labDescriptionDefMod1;
	}

	public void setLabDescriptionDefMod1(String labDescriptionDefMod1) {
		this.labDescriptionDefMod1 = labDescriptionDefMod1;
	}

	public String getLabDescriptionDefMod2() {
		return labDescriptionDefMod2;
	}

	public void setLabDescriptionDefMod2(String labDescriptionDefMod2) {
		this.labDescriptionDefMod2 = labDescriptionDefMod2;
	}

	public String getLabDescriptionDefMod3() {
		return labDescriptionDefMod3;
	}

	public void setLabDescriptionDefMod3(String labDescriptionDefMod3) {
		this.labDescriptionDefMod3 = labDescriptionDefMod3;
	}

	public String getLabDescriptionDefMod4() {
		return labDescriptionDefMod4;
	}

	public void setLabDescriptionDefMod4(String labDescriptionDefMod4) {
		this.labDescriptionDefMod4 = labDescriptionDefMod4;
	}

	public String getLabDescriptionDrugs() {
		return labDescriptionDrugs;
	}

	public void setLabDescriptionDrugs(String labDescriptionDrugs) {
		this.labDescriptionDrugs = labDescriptionDrugs;
	}

	public String getLabDescriptionNdc() {
		return labDescriptionNdc;
	}

	public void setLabDescriptionNdc(String labDescriptionNdc) {
		this.labDescriptionNdc = labDescriptionNdc;
	}

	public String getLabDescriptionCvx() {
		return labDescriptionCvx;
	}

	public void setLabDescriptionCvx(String labDescriptionCvx) {
		this.labDescriptionCvx = labDescriptionCvx;
	}

	public String getLabDescriptionInstruction() {
		return labDescriptionInstruction;
	}

	public void setLabDescriptionInstruction(String labDescriptionInstruction) {
		this.labDescriptionInstruction = labDescriptionInstruction;
	}

	public String getLabDescriptionInstFilename() {
		return labDescriptionInstFilename;
	}

	public void setLabDescriptionInstFilename(String labDescriptionInstFilename) {
		this.labDescriptionInstFilename = labDescriptionInstFilename;
	}

	public String getLabDescriptionLoinc() {
		return labDescriptionLoinc;
	}

	public void setLabDescriptionLoinc(String labDescriptionLoinc) {
		this.labDescriptionLoinc = labDescriptionLoinc;
	}

	public String getLabDescriptionVaccineVis() {
		return labDescriptionVaccineVis;
	}

	public void setLabDescriptionVaccineVis(String labDescriptionVaccineVis) {
		this.labDescriptionVaccineVis = labDescriptionVaccineVis;
	}

	public Boolean getLabDescriptionIsdefaultprint() {
		return labDescriptionIsdefaultprint;
	}

	public void setLabDescriptionIsdefaultprint(Boolean labDescriptionIsdefaultprint) {
		this.labDescriptionIsdefaultprint = labDescriptionIsdefaultprint;
	}

	public Integer getLabDescriptionPrintsort() {
		return labDescriptionPrintsort;
	}

	public void setLabDescriptionPrintsort(Integer labDescriptionPrintsort) {
		this.labDescriptionPrintsort = labDescriptionPrintsort;
	}

	public String getLabDescriptionDefMod12() {
		return labDescriptionDefMod12;
	}

	public void setLabDescriptionDefMod12(String labDescriptionDefMod12) {
		this.labDescriptionDefMod12 = labDescriptionDefMod12;
	}

	public String getLabDescriptionDefMod22() {
		return labDescriptionDefMod22;
	}

	public void setLabDescriptionDefMod22(String labDescriptionDefMod22) {
		this.labDescriptionDefMod22 = labDescriptionDefMod22;
	}

	public String getLabDescriptionDefMod32() {
		return labDescriptionDefMod32;
	}

	public void setLabDescriptionDefMod32(String labDescriptionDefMod32) {
		this.labDescriptionDefMod32 = labDescriptionDefMod32;
	}

	public String getLabDescriptionDefMod42() {
		return labDescriptionDefMod42;
	}

	public void setLabDescriptionDefMod42(String labDescriptionDefMod42) {
		this.labDescriptionDefMod42 = labDescriptionDefMod42;
	}

	public Integer getLabDescriptionTestcategoryType() {
		return labDescriptionTestcategoryType;
	}

	public void setLabDescriptionTestcategoryType(
			Integer labDescriptionTestcategoryType) {
		this.labDescriptionTestcategoryType = labDescriptionTestcategoryType;
	}

	public Boolean getLabDescriptionIsfasting() {
		return labDescriptionIsfasting;
	}

	public void setLabDescriptionIsfasting(Boolean labDescriptionIsfasting) {
		this.labDescriptionIsfasting = labDescriptionIsfasting;
	}

	public String getLabDescriptionBasedoseUnit() {
		return labDescriptionBasedoseUnit;
	}

	public void setLabDescriptionBasedoseUnit(String labDescriptionBasedoseUnit) {
		this.labDescriptionBasedoseUnit = labDescriptionBasedoseUnit;
	}

	public String getLabDescriptionBasedose() {
		return labDescriptionBasedose;
	}

	public void setLabDescriptionBasedose(String labDescriptionBasedose) {
		this.labDescriptionBasedose = labDescriptionBasedose;
	}

	public String getLabDescriptionStrength() {
		return labDescriptionStrength;
	}

	public void setLabDescriptionStrength(String labDescriptionStrength) {
		this.labDescriptionStrength = labDescriptionStrength;
	}

	public String getLabDescriptionStrengthUnit() {
		return labDescriptionStrengthUnit;
	}

	public void setLabDescriptionStrengthUnit(String labDescriptionStrengthUnit) {
		this.labDescriptionStrengthUnit = labDescriptionStrengthUnit;
	}

	public List<LabEntries> getLabEntriesTable() {
		return labEntriesTable;
	}

	public void setLabEntriesTable(List<LabEntries> labEntriesTable) {
		this.labEntriesTable = labEntriesTable;
	}

	public List<VaccineReport> getVaccineReportTable() {
		return vaccineReportTable;
	}

	public void setVaccineReportTable(List<VaccineReport> vaccineReportTable) {
		this.vaccineReportTable = vaccineReportTable;
	}

	public List<Hl7ExternalTestmapping> getHl7ExternalTestmappingTable() {
		return hl7ExternalTestmappingTable;
	}

	public void setHl7ExternalTestmappingTable(
			List<Hl7ExternalTestmapping> hl7ExternalTestmappingTable) {
		this.hl7ExternalTestmappingTable = hl7ExternalTestmappingTable;
	}

	public LabFreqorder getLabFreqOrder() {
		return labFreqOrder;
	}

	public void setLabFreqOrder(LabFreqorder labFreqOrder) {
		this.labFreqOrder = labFreqOrder;
	}

	public Integer getLabDescriptionCriticalStatus() {
		return labDescriptionCriticalStatus;
	}

	public void setLabDescriptionCriticalStatus(Integer labDescriptionCriticalStatus) {
		this.labDescriptionCriticalStatus = labDescriptionCriticalStatus;
	}

	public Integer getLabDescriptionDelinquencyDays() {
		return labDescriptionDelinquencyDays;
	}

	public void setLabDescriptionDelinquencyDays(
			Integer labDescriptionDelinquencyDays) {
		this.labDescriptionDelinquencyDays = labDescriptionDelinquencyDays;
	}

	public String getLabDescriptionDefDx1codeCodedesc() {
		return labDescriptionDefDx1codeCodedesc;
	}

	public void setLabDescriptionDefDx1codeCodedesc(
			String labDescriptionDefDx1codeCodedesc) {
		this.labDescriptionDefDx1codeCodedesc = labDescriptionDefDx1codeCodedesc;
	}

	public String getLabDescriptionDefDx2codeCodedesc() {
		return labDescriptionDefDx2codeCodedesc;
	}

	public void setLabDescriptionDefDx2codeCodedesc(
			String labDescriptionDefDx2codeCodedesc) {
		this.labDescriptionDefDx2codeCodedesc = labDescriptionDefDx2codeCodedesc;
	}

	public String getLabDescriptionDefDx3codeCodedesc() {
		return labDescriptionDefDx3codeCodedesc;
	}

	public void setLabDescriptionDefDx3codeCodedesc(
			String labDescriptionDefDx3codeCodedesc) {
		this.labDescriptionDefDx3codeCodedesc = labDescriptionDefDx3codeCodedesc;
	}

	public String getLabDescriptionDefDx4codeCodedesc() {
		return labDescriptionDefDx4codeCodedesc;
	}

	public void setLabDescriptionDefDx4codeCodedesc(
			String labDescriptionDefDx4codeCodedesc) {
		this.labDescriptionDefDx4codeCodedesc = labDescriptionDefDx4codeCodedesc;
	}

	public String getLabDescriptionDefDx5codeCodedesc() {
		return labDescriptionDefDx5codeCodedesc;
	}

	public void setLabDescriptionDefDx5codeCodedesc(
			String labDescriptionDefDx5codeCodedesc) {
		this.labDescriptionDefDx5codeCodedesc = labDescriptionDefDx5codeCodedesc;
	}

	public String getLabDescriptionDefDx6codeCodedesc() {
		return labDescriptionDefDx6codeCodedesc;
	}

	public void setLabDescriptionDefDx6codeCodedesc(
			String labDescriptionDefDx6codeCodedesc) {
		this.labDescriptionDefDx6codeCodedesc = labDescriptionDefDx6codeCodedesc;
	}

	public String getLabDescriptionDefDx7codeCodedesc() {
		return labDescriptionDefDx7codeCodedesc;
	}

	public void setLabDescriptionDefDx7codeCodedesc(
			String labDescriptionDefDx7codeCodedesc) {
		this.labDescriptionDefDx7codeCodedesc = labDescriptionDefDx7codeCodedesc;
	}

	public String getLabDescriptionDefDx8codeCodedesc() {
		return labDescriptionDefDx8codeCodedesc;
	}

	public void setLabDescriptionDefDx8codeCodedesc(
			String labDescriptionDefDx8codeCodedesc) {
		this.labDescriptionDefDx8codeCodedesc = labDescriptionDefDx8codeCodedesc;
	}

	public String getLabDescriptionDefDx1codeCodesystem() {
		return labDescriptionDefDx1codeCodesystem;
	}

	public void setLabDescriptionDefDx1codeCodesystem(
			String labDescriptionDefDx1codeCodesystem) {
		this.labDescriptionDefDx1codeCodesystem = labDescriptionDefDx1codeCodesystem;
	}

	public String getLabDescriptionDefDx2codeCodesystem() {
		return labDescriptionDefDx2codeCodesystem;
	}

	public void setLabDescriptionDefDx2codeCodesystem(
			String labDescriptionDefDx2codeCodesystem) {
		this.labDescriptionDefDx2codeCodesystem = labDescriptionDefDx2codeCodesystem;
	}

	public String getLabDescriptionDefDx3codeCodesystem() {
		return labDescriptionDefDx3codeCodesystem;
	}

	public void setLabDescriptionDefDx3codeCodesystem(
			String labDescriptionDefDx3codeCodesystem) {
		this.labDescriptionDefDx3codeCodesystem = labDescriptionDefDx3codeCodesystem;
	}

	public String getLabDescriptionDefDx4codeCodesystem() {
		return labDescriptionDefDx4codeCodesystem;
	}

	public void setLabDescriptionDefDx4codeCodesystem(
			String labDescriptionDefDx4codeCodesystem) {
		this.labDescriptionDefDx4codeCodesystem = labDescriptionDefDx4codeCodesystem;
	}

	public String getLabDescriptionDefDx5codeCodesystem() {
		return labDescriptionDefDx5codeCodesystem;
	}

	public void setLabDescriptionDefDx5codeCodesystem(
			String labDescriptionDefDx5codeCodesystem) {
		this.labDescriptionDefDx5codeCodesystem = labDescriptionDefDx5codeCodesystem;
	}

	public String getLabDescriptionDefDx6codeCodesystem() {
		return labDescriptionDefDx6codeCodesystem;
	}

	public void setLabDescriptionDefDx6codeCodesystem(
			String labDescriptionDefDx6codeCodesystem) {
		this.labDescriptionDefDx6codeCodesystem = labDescriptionDefDx6codeCodesystem;
	}

	public String getLabDescriptionDefDx7codeCodesystem() {
		return labDescriptionDefDx7codeCodesystem;
	}

	public void setLabDescriptionDefDx7codeCodesystem(
			String labDescriptionDefDx7codeCodesystem) {
		this.labDescriptionDefDx7codeCodesystem = labDescriptionDefDx7codeCodesystem;
	}

	public String getLabDescriptionDefDx8codeCodesystem() {
		return labDescriptionDefDx8codeCodesystem;
	}

	public void setLabDescriptionDefDx8codeCodesystem(
			String labDescriptionDefDx8codeCodesystem) {
		this.labDescriptionDefDx8codeCodesystem = labDescriptionDefDx8codeCodesystem;
	}

	public String getLabDescriptionDefDx3() {
		return labDescriptionDefDx3;
	}

	public void setLabDescriptionDefDx3(String labDescriptionDefDx3) {
		this.labDescriptionDefDx3 = labDescriptionDefDx3;
	}

	public String getLabDescriptionDefDx4() {
		return labDescriptionDefDx4;
	}

	public void setLabDescriptionDefDx4(String labDescriptionDefDx4) {
		this.labDescriptionDefDx4 = labDescriptionDefDx4;
	}

	public String getLabDescriptionDefDx5() {
		return labDescriptionDefDx5;
	}

	public void setLabDescriptionDefDx5(String labDescriptionDefDx5) {
		this.labDescriptionDefDx5 = labDescriptionDefDx5;
	}

	public String getLabDescriptionDefDx6() {
		return labDescriptionDefDx6;
	}

	public void setLabDescriptionDefDx6(String labDescriptionDefDx6) {
		this.labDescriptionDefDx6 = labDescriptionDefDx6;
	}

	public String getLabDescriptionDefDx7() {
		return labDescriptionDefDx7;
	}

	public void setLabDescriptionDefDx7(String labDescriptionDefDx7) {
		this.labDescriptionDefDx7 = labDescriptionDefDx7;
	}

	public String getLabDescriptionDefDx8() {
		return labDescriptionDefDx8;
	}

	public void setLabDescriptionDefDx8(String labDescriptionDefDx8) {
		this.labDescriptionDefDx8 = labDescriptionDefDx8;
	}	
	
	public String getLabDescriptionSnomed() {
		return labDescriptionSnomed;
	}
	public void setLabDescriptionSnomed(String labDescriptionSnomed) {
		this.labDescriptionSnomed = labDescriptionSnomed;
	}

}