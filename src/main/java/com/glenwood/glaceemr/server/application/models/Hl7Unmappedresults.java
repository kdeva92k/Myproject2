package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "hl7_unmappedresults")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hl7Unmappedresults implements Serializable{

   private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hl7_unmappedresults_hl7_unmappedresults_id_seq")
	@SequenceGenerator(name ="hl7_unmappedresults_hl7_unmappedresults_id_seq", sequenceName="hl7_unmappedresults_hl7_unmappedresults_id_seq", allocationSize=1)
	@Column(name="hl7_unmappedresults_id")
	private Integer hl7UnmappedresultsId;
	
	@Column(name="hl7_unmappedresults_accountno")
	private String hl7UnmappedresultsAccountno;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="hl7_unmappedresults_ord_date")
	private Timestamp hl7UnmappedresultsOrdDate;

	@Column(name="hl7_unmappedresults_ordby_lastname")
	private String hl7UnmappedresultsOrdbyLastname;

	@Column(name="hl7_unmappedresults_ordby_firstname")
	private String hl7UnmappedresultsOrdbyFirstname;

	@Column(name="hl7_unmappedresults_ordby_upinid")
	private String hl7UnmappedresultsOrdbyUpinid;

	@Column(name="hl7_unmappedresults_labname")
	private String hl7UnmappedresultsLabname;

	@Column(name="hl7_unmappedresults_filename")
	private String hl7UnmappedresultsFilename;

	@Column(name="hl7_unmappedresults_result")
	private String hl7UnmappedresultsResult;

	@Column(name="hl7_unmappedresults_map_status")
	private String hl7UnmappedresultsMapStatus;

	@Column(name="hl7_unmappedresults_testdetail_id")
	private Integer hl7UnmappedresultsTestdetailId;

	@Column(name="hl7_unmappedresults_test_status")
	private Integer hl7UnmappedresultsTestStatus;

	@Column(name="hl7_unmappedresults_prelim_status")
	private Integer hl7UnmappedresultsPrelimStatus;

	@Column(name="hl7_unmappedresults_final_status")
	private Integer hl7UnmappedresultsFinalStatus;

	@Column(name="hl7_unmappedresults_testnotes")
	private String hl7UnmappedresultsTestnotes;

	@Column(name="hl7_unmappedresults_rev_on")
	private Date hl7UnmappedresultsRevOn;

	@Column(name="hl7_unmappedresults_filewise_id")
	private Integer hl7UnmappedresultsFilewiseId;

	@Column(name="hl7_unmappedresults_ordby_docid")
	private Integer hl7UnmappedresultsOrdbyDocid;

	@Column(name="hl7_unmappedresults_ext_testcode")
	private String hl7UnmappedresultsExtTestcode;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="hl7_unmappedresults_performeddate")
	private Timestamp hl7UnmappedresultsPerformeddate;

	@Column(name="hl7_unmappedresults_result_status")
	private String hl7UnmappedresultsResultStatus;

	@Column(name="hl7_unmappedresults_labcomp_detailid")
	private Integer hl7UnmappedresultsLabcompDetailid;

	@Column(name="hl7_unmappedresults_src_of_specimen")
	private String hl7UnmappedresultsSrcOfSpecimen;

	@Column(name="hl7_unmappedresults_cond_of_specimen")
	private String hl7UnmappedresultsCondOfSpecimen;

	@Column(name="hl7_unmappedresults_prelimtest_id")
	private Integer hl7UnmappedresultsPrelimtestId;

	@Column(name="hl7_unmappedresults_isactive")
	private Boolean hl7UnmappedresultsIsactive;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="hl7_unmappedresults_specimen_collected_date")
	private Timestamp hl7UnmappedresultsSpecimenCollectedDate;

	@Column(name="hl7_unmappedresults_specimen_rejectreason")
	private String hl7UnmappedresultsSpecimenRejectreason;

	@Column(name="hl7_unmappedresults_resultscopiesto")
	private String hl7UnmappedresultsResultscopiesto;

	@Column(name="hl7_unmappedresults_relevant_clinical_info")
	private String hl7UnmappedresultsRelevantClinicalInfo;

	@Column(name="hl7_unmappedresults_requisition_id")
	private String hl7UnmappedresultsRequisitionId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="hl7_unmappedresults_spec_rec_on")
	private Timestamp hl7UnmappedresultsSpecRecOn;

	@Column(name="hl7_unmappedresults_isfasting")
	private Boolean hl7UnmappedresultsIsfasting;

	@Column(name="hl7_unmappedresults_collection_volume")
	private String hl7UnmappedresultsCollectionVolume;

	@Column(name="hl7_unmappedresults_ispdf")
	private Integer hl7UnmappedresultsIspdf;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="hl7_unmappedresults_filewise_id", referencedColumnName="hl7_result_inbox_id", insertable=false, updatable=false)
	private Hl7ResultInbox hl7ResultInbox;
		
	@ManyToOne(fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="hl7_unmappedresults_testdetail_id", referencedColumnName="lab_entries_testdetail_id", insertable=false, updatable=false)
	private LabEntries labEntriesUnmapped;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="hl7_unmappedresults_labcomp_detailid", referencedColumnName="labcompany_details_id", insertable=false, updatable=false)
	private LabcompanyDetails labCompanyDetails;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="hl7_unmappedresults_ordby_docid", referencedColumnName="emp_profile_empid", insertable=false, updatable=false)
	private EmployeeProfile empProfile;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="hl7_unmappedresults_accountno", referencedColumnName="patient_registration_accountno" , insertable=false, updatable=false)
	private PatientRegistration hl7UnmappedresultsPatTable;
	
	public Integer getHl7UnmappedresultsId() {
		return hl7UnmappedresultsId;
	}

	public void setHl7UnmappedresultsId(Integer hl7UnmappedresultsId) {
		this.hl7UnmappedresultsId = hl7UnmappedresultsId;
	}

	public String getHl7UnmappedresultsAccountno() {
		return hl7UnmappedresultsAccountno;
	}

	public void setHl7UnmappedresultsAccountno(String hl7UnmappedresultsAccountno) {
		this.hl7UnmappedresultsAccountno = hl7UnmappedresultsAccountno;
	}

	public Timestamp getHl7UnmappedresultsOrdDate() {
		return hl7UnmappedresultsOrdDate;
	}

	public void setHl7UnmappedresultsOrdDate(Timestamp hl7UnmappedresultsOrdDate) {
		this.hl7UnmappedresultsOrdDate = hl7UnmappedresultsOrdDate;
	}

	public String getHl7UnmappedresultsOrdbyLastname() {
		return hl7UnmappedresultsOrdbyLastname;
	}

	public void setHl7UnmappedresultsOrdbyLastname(
			String hl7UnmappedresultsOrdbyLastname) {
		this.hl7UnmappedresultsOrdbyLastname = hl7UnmappedresultsOrdbyLastname;
	}

	public String getHl7UnmappedresultsOrdbyFirstname() {
		return hl7UnmappedresultsOrdbyFirstname;
	}

	public void setHl7UnmappedresultsOrdbyFirstname(
			String hl7UnmappedresultsOrdbyFirstname) {
		this.hl7UnmappedresultsOrdbyFirstname = hl7UnmappedresultsOrdbyFirstname;
	}

	public String getHl7UnmappedresultsOrdbyUpinid() {
		return hl7UnmappedresultsOrdbyUpinid;
	}

	public void setHl7UnmappedresultsOrdbyUpinid(
			String hl7UnmappedresultsOrdbyUpinid) {
		this.hl7UnmappedresultsOrdbyUpinid = hl7UnmappedresultsOrdbyUpinid;
	}

	public String getHl7UnmappedresultsLabname() {
		return hl7UnmappedresultsLabname;
	}

	public void setHl7UnmappedresultsLabname(String hl7UnmappedresultsLabname) {
		this.hl7UnmappedresultsLabname = hl7UnmappedresultsLabname;
	}

	public String getHl7UnmappedresultsFilename() {
		return hl7UnmappedresultsFilename;
	}

	public void setHl7UnmappedresultsFilename(String hl7UnmappedresultsFilename) {
		this.hl7UnmappedresultsFilename = hl7UnmappedresultsFilename;
	}

	public String getHl7UnmappedresultsResult() {
		return hl7UnmappedresultsResult;
	}

	public void setHl7UnmappedresultsResult(String hl7UnmappedresultsResult) {
		this.hl7UnmappedresultsResult = hl7UnmappedresultsResult;
	}

	public String getHl7UnmappedresultsMapStatus() {
		return hl7UnmappedresultsMapStatus;
	}

	public void setHl7UnmappedresultsMapStatus(String hl7UnmappedresultsMapStatus) {
		this.hl7UnmappedresultsMapStatus = hl7UnmappedresultsMapStatus;
	}

	public Integer getHl7UnmappedresultsTestdetailId() {
		return hl7UnmappedresultsTestdetailId;
	}

	public void setHl7UnmappedresultsTestdetailId(
			Integer hl7UnmappedresultsTestdetailId) {
		this.hl7UnmappedresultsTestdetailId = hl7UnmappedresultsTestdetailId;
	}

	public Integer getHl7UnmappedresultsTestStatus() {
		return hl7UnmappedresultsTestStatus;
	}

	public void setHl7UnmappedresultsTestStatus(Integer hl7UnmappedresultsTestStatus) {
		this.hl7UnmappedresultsTestStatus = hl7UnmappedresultsTestStatus;
	}

	public Integer getHl7UnmappedresultsPrelimStatus() {
		return hl7UnmappedresultsPrelimStatus;
	}

	public void setHl7UnmappedresultsPrelimStatus(
			Integer hl7UnmappedresultsPrelimStatus) {
		this.hl7UnmappedresultsPrelimStatus = hl7UnmappedresultsPrelimStatus;
	}

	public Integer getHl7UnmappedresultsFinalStatus() {
		return hl7UnmappedresultsFinalStatus;
	}

	public void setHl7UnmappedresultsFinalStatus(
			Integer hl7UnmappedresultsFinalStatus) {
		this.hl7UnmappedresultsFinalStatus = hl7UnmappedresultsFinalStatus;
	}

	public String getHl7UnmappedresultsTestnotes() {
		return hl7UnmappedresultsTestnotes;
	}

	public void setHl7UnmappedresultsTestnotes(String hl7UnmappedresultsTestnotes) {
		this.hl7UnmappedresultsTestnotes = hl7UnmappedresultsTestnotes;
	}

	public Date getHl7UnmappedresultsRevOn() {
		return hl7UnmappedresultsRevOn;
	}

	public void setHl7UnmappedresultsRevOn(Date hl7UnmappedresultsRevOn) {
		this.hl7UnmappedresultsRevOn = hl7UnmappedresultsRevOn;
	}

	public Integer getHl7UnmappedresultsFilewiseId() {
		return hl7UnmappedresultsFilewiseId;
	}

	public void setHl7UnmappedresultsFilewiseId(Integer hl7UnmappedresultsFilewiseId) {
		this.hl7UnmappedresultsFilewiseId = hl7UnmappedresultsFilewiseId;
	}

	public Integer getHl7UnmappedresultsOrdbyDocid() {
		return hl7UnmappedresultsOrdbyDocid;
	}

	public void setHl7UnmappedresultsOrdbyDocid(Integer hl7UnmappedresultsOrdbyDocid) {
		this.hl7UnmappedresultsOrdbyDocid = hl7UnmappedresultsOrdbyDocid;
	}

	public String getHl7UnmappedresultsExtTestcode() {
		return hl7UnmappedresultsExtTestcode;
	}

	public void setHl7UnmappedresultsExtTestcode(
			String hl7UnmappedresultsExtTestcode) {
		this.hl7UnmappedresultsExtTestcode = hl7UnmappedresultsExtTestcode;
	}

	public Timestamp getHl7UnmappedresultsPerformeddate() {
		return hl7UnmappedresultsPerformeddate;
	}

	public void setHl7UnmappedresultsPerformeddate(
			Timestamp hl7UnmappedresultsPerformeddate) {
		this.hl7UnmappedresultsPerformeddate = hl7UnmappedresultsPerformeddate;
	}

	public String getHl7UnmappedresultsResultStatus() {
		return hl7UnmappedresultsResultStatus;
	}

	public void setHl7UnmappedresultsResultStatus(
			String hl7UnmappedresultsResultStatus) {
		this.hl7UnmappedresultsResultStatus = hl7UnmappedresultsResultStatus;
	}

	public Integer getHl7UnmappedresultsLabcompDetailid() {
		return hl7UnmappedresultsLabcompDetailid;
	}

	public void setHl7UnmappedresultsLabcompDetailid(
			Integer hl7UnmappedresultsLabcompDetailid) {
		this.hl7UnmappedresultsLabcompDetailid = hl7UnmappedresultsLabcompDetailid;
	}

	public String getHl7UnmappedresultsSrcOfSpecimen() {
		return hl7UnmappedresultsSrcOfSpecimen;
	}

	public void setHl7UnmappedresultsSrcOfSpecimen(
			String hl7UnmappedresultsSrcOfSpecimen) {
		this.hl7UnmappedresultsSrcOfSpecimen = hl7UnmappedresultsSrcOfSpecimen;
	}

	public String getHl7UnmappedresultsCondOfSpecimen() {
		return hl7UnmappedresultsCondOfSpecimen;
	}

	public void setHl7UnmappedresultsCondOfSpecimen(
			String hl7UnmappedresultsCondOfSpecimen) {
		this.hl7UnmappedresultsCondOfSpecimen = hl7UnmappedresultsCondOfSpecimen;
	}

	public Integer getHl7UnmappedresultsPrelimtestId() {
		return hl7UnmappedresultsPrelimtestId;
	}

	public void setHl7UnmappedresultsPrelimtestId(
			Integer hl7UnmappedresultsPrelimtestId) {
		this.hl7UnmappedresultsPrelimtestId = hl7UnmappedresultsPrelimtestId;
	}

	public Boolean getHl7UnmappedresultsIsactive() {
		return hl7UnmappedresultsIsactive;
	}

	public void setHl7UnmappedresultsIsactive(Boolean hl7UnmappedresultsIsactive) {
		this.hl7UnmappedresultsIsactive = hl7UnmappedresultsIsactive;
	}

	public Timestamp getHl7UnmappedresultsSpecimenCollectedDate() {
		return hl7UnmappedresultsSpecimenCollectedDate;
	}

	public void setHl7UnmappedresultsSpecimenCollectedDate(
			Timestamp hl7UnmappedresultsSpecimenCollectedDate) {
		this.hl7UnmappedresultsSpecimenCollectedDate = hl7UnmappedresultsSpecimenCollectedDate;
	}

	public String getHl7UnmappedresultsSpecimenRejectreason() {
		return hl7UnmappedresultsSpecimenRejectreason;
	}

	public void setHl7UnmappedresultsSpecimenRejectreason(
			String hl7UnmappedresultsSpecimenRejectreason) {
		this.hl7UnmappedresultsSpecimenRejectreason = hl7UnmappedresultsSpecimenRejectreason;
	}

	public String getHl7UnmappedresultsResultscopiesto() {
		return hl7UnmappedresultsResultscopiesto;
	}

	public void setHl7UnmappedresultsResultscopiesto(
			String hl7UnmappedresultsResultscopiesto) {
		this.hl7UnmappedresultsResultscopiesto = hl7UnmappedresultsResultscopiesto;
	}

	public String getHl7UnmappedresultsRelevantClinicalInfo() {
		return hl7UnmappedresultsRelevantClinicalInfo;
	}

	public void setHl7UnmappedresultsRelevantClinicalInfo(
			String hl7UnmappedresultsRelevantClinicalInfo) {
		this.hl7UnmappedresultsRelevantClinicalInfo = hl7UnmappedresultsRelevantClinicalInfo;
	}

	public String getHl7UnmappedresultsRequisitionId() {
		return hl7UnmappedresultsRequisitionId;
	}

	public void setHl7UnmappedresultsRequisitionId(
			String hl7UnmappedresultsRequisitionId) {
		this.hl7UnmappedresultsRequisitionId = hl7UnmappedresultsRequisitionId;
	}

	public Timestamp getHl7UnmappedresultsSpecRecOn() {
		return hl7UnmappedresultsSpecRecOn;
	}

	public void setHl7UnmappedresultsSpecRecOn(Timestamp hl7UnmappedresultsSpecRecOn) {
		this.hl7UnmappedresultsSpecRecOn = hl7UnmappedresultsSpecRecOn;
	}

	public Boolean getHl7UnmappedresultsIsfasting() {
		return hl7UnmappedresultsIsfasting;
	}

	public void setHl7UnmappedresultsIsfasting(Boolean hl7UnmappedresultsIsfasting) {
		this.hl7UnmappedresultsIsfasting = hl7UnmappedresultsIsfasting;
	}

	public String getHl7UnmappedresultsCollectionVolume() {
		return hl7UnmappedresultsCollectionVolume;
	}

	public void setHl7UnmappedresultsCollectionVolume(
			String hl7UnmappedresultsCollectionVolume) {
		this.hl7UnmappedresultsCollectionVolume = hl7UnmappedresultsCollectionVolume;
	}

	public Integer getHl7UnmappedresultsIspdf() {
		return hl7UnmappedresultsIspdf;
	}

	public void setHl7UnmappedresultsIspdf(Integer hl7UnmappedresultsIspdf) {
		this.hl7UnmappedresultsIspdf = hl7UnmappedresultsIspdf;
	}
	
	public Hl7ResultInbox getHl7ResultInbox() {
		return hl7ResultInbox;
	}

	public void setHl7ResultInbox(Hl7ResultInbox hl7ResultInbox) {
		this.hl7ResultInbox = hl7ResultInbox;
	}

	public LabEntries getLabEntriesUnmapped() {
		return labEntriesUnmapped;
	}

	public void setLabEntriesUnmapped(LabEntries labEntriesUnmapped) {
		this.labEntriesUnmapped = labEntriesUnmapped;
	}

	public LabcompanyDetails getLabCompanyDetails() {
		return labCompanyDetails;
	}

	public void setLabCompanyDetails(LabcompanyDetails labCompanyDetails) {
		this.labCompanyDetails = labCompanyDetails;
	}

	public EmployeeProfile getEmpProfile() {
		return empProfile;
	}

	public void setEmpProfile(EmployeeProfile empProfile) {
		this.empProfile = empProfile;
	}

	public PatientRegistration getHl7UnmappedresultsPatTable() {
		return hl7UnmappedresultsPatTable;
	}

	public void setHl7UnmappedresultsPatTable(
			PatientRegistration hl7UnmappedresultsPatTable) {
		this.hl7UnmappedresultsPatTable = hl7UnmappedresultsPatTable;
	}
}