package com.glenwood.glaceemr.server.application.models;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "filedetails")
public class FileDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="filedetails_filedetails_id_seq")
	@SequenceGenerator(name ="filedetails_filedetails_id_seq", sequenceName="filedetails_filedetails_id_seq", allocationSize=1)
	@Column(name="filedetails_id")
	private Integer filedetailsId;

	@Column(name="filedetails_flag")
	private Integer filedetailsFlag;

	@Column(name="filedetails_description")
	private String filedetailsDescription;

	@Column(name="filedetails_comments")
	private String filedetailsComments;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="filedetails_creationdate")
	private Timestamp filedetailsCreationdate;

	@Column(name="filedetails_createdby")
	private Integer filedetailsCreatedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="filedetails_lastmodifiedon")
	private Timestamp filedetailsLastmodifiedon;

	@Column(name="filedetails_lastmodifiedby")
	private Integer filedetailsLastmodifiedby;

	@Column(name="filedetails_encounterid")
	private Integer filedetailsEncounterid;

	@Column(name="filedetails_patientid")
	private Integer filedetailsPatientid;

	@Column(name="filedetails_chartid")
	private Integer filedetailsChartid;

	@Column(name="filedetails_entityid")
	private Integer filedetailsEntityid;

	@Column(name="filedetails_type")
	private String filedetailsType;

	@Column(name="filedetails_buffer3")
	private Boolean filedetailsBuffer3;

	@Column(name="filedetails_categoryid")
	private Integer filedetailsCategoryid;

	@Column(name="filedetails_userdate")
	private String filedetailsUserdate;

	@Column(name="filedetails_faxreferenceid")
	private Integer filedetailsFaxreferenceid;

	@Column(name="filedetails_scantype")
	private Integer filedetailsScantype;

	@Column(name="filedetails_issigned")
	private Boolean filedetailsIssigned;


	@Column(name="filedetails_templateid")
	private BigInteger filedetailsTemplateid;

	@OneToOne(cascade=CascadeType.ALL,mappedBy="fileDetails")
	@JsonBackReference
	PatientPortalSharedDocs patientPortalSharedDocs;


	@OneToMany(mappedBy="fileNameDetails")
	@JsonManagedReference
	private List<FileName> fileName;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="filedetails_categoryid", referencedColumnName="patient_doc_category_id", insertable=false,updatable=false)
	@JsonManagedReference
	PatientDocumentsCategory patientDocCategory;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="filedetails_createdby", referencedColumnName="emp_profile_empid", insertable=false,updatable=false)
	@JsonManagedReference
	EmployeeProfile createdByEmpProfileTable;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="filedetails_lastmodifiedby", referencedColumnName="emp_profile_empid", insertable=false,updatable=false)
	@JsonManagedReference
	EmployeeProfile lastModifiedByEmpProfileTable;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="filedetails_encounterid", referencedColumnName="encounter_id", insertable=false,updatable=false)
	@JsonManagedReference
	Encounter encounterTable;

    @NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="filedetails_templateid", referencedColumnName="forms_template_id", insertable=false,updatable=false)
	@JsonManagedReference
	FormsTemplate formsTemplate;
	
	public FormsTemplate getFormsTemplate() {
		return formsTemplate;
	}

	public void setFormsTemplate(FormsTemplate formsTemplate) {
		this.formsTemplate = formsTemplate;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="filedetails_entityid", referencedColumnName="lab_entries_testdetail_id", insertable=false,updatable=false)
	private LabEntries labEntries;

	public LabEntries getLabEntries() {
		return labEntries;
	}

	public void setLabEntries(LabEntries labEntries) {
		this.labEntries = labEntries;
	}

	public EmployeeProfile getCreatedByEmpProfileTable() {
		return createdByEmpProfileTable;
	}

	public void setCreatedByEmpProfileTable(EmployeeProfile createdByEmpProfileTable) {
		this.createdByEmpProfileTable = createdByEmpProfileTable;
	}

	public EmployeeProfile getLastModifiedByEmpProfileTable() {
		return lastModifiedByEmpProfileTable;
	}

	public void setLastModifiedByEmpProfileTable(
			EmployeeProfile lastModifiedByEmpProfileTable) {
		this.lastModifiedByEmpProfileTable = lastModifiedByEmpProfileTable;
	}

	public Encounter getEncounterTable() {
		return encounterTable;
	}

	public void setEncounterTable(Encounter encounterTable) {
		this.encounterTable = encounterTable;
	}

	public PatientPortalSharedDocs getPatientPortalSharedDocs() {
		return patientPortalSharedDocs;
	}



	public List<FileName> getFileName() {
		return fileName;
	}

	public void setFileName(List<FileName> fileName) {
		this.fileName = fileName;
	}

	public PatientDocumentsCategory getPatientDocCategory() {
		return patientDocCategory;
	}

	public void setPatientPortalSharedDocs(
			PatientPortalSharedDocs patientPortalSharedDocs) {
		this.patientPortalSharedDocs = patientPortalSharedDocs;
	}



	public void setPatientDocCategory(PatientDocumentsCategory patientDocCategory) {
		this.patientDocCategory = patientDocCategory;
	}

	public Integer getFiledetailsId() {
		return filedetailsId;
	}

	public Integer getFiledetailsFlag() {
		return filedetailsFlag;
	}

	public String getFiledetailsDescription() {
		return filedetailsDescription;
	}

	public String getFiledetailsComments() {
		return filedetailsComments;
	}

	public Timestamp getFiledetailsCreationdate() {
		return filedetailsCreationdate;
	}

	public Integer getFiledetailsCreatedby() {
		return filedetailsCreatedby;
	}

	public Timestamp getFiledetailsLastmodifiedon() {
		return filedetailsLastmodifiedon;
	}

	public Integer getFiledetailsLastmodifiedby() {
		return filedetailsLastmodifiedby;
	}

	public Integer getFiledetailsEncounterid() {
		return filedetailsEncounterid;
	}

	public Integer getFiledetailsPatientid() {
		return filedetailsPatientid;
	}

	public Integer getFiledetailsChartid() {
		return filedetailsChartid;
	}

	public Integer getFiledetailsEntityid() {
		return filedetailsEntityid;
	}

	public String getFiledetailsType() {
		return filedetailsType;
	}

	public Boolean getFiledetailsBuffer3() {
		return filedetailsBuffer3;
	}

	public Integer getFiledetailsCategoryid() {
		return filedetailsCategoryid;
	}

	public String getFiledetailsUserdate() {
		return filedetailsUserdate;
	}

	public Integer getFiledetailsFaxreferenceid() {
		return filedetailsFaxreferenceid;
	}

	public Integer getFiledetailsScantype() {
		return filedetailsScantype;
	}

	public Boolean getFiledetailsIssigned() {
		return filedetailsIssigned;
	}

	public BigInteger getFiledetailsTemplateid() {
		return filedetailsTemplateid;
	}

	public void setFiledetailsId(Integer filedetailsId) {
		this.filedetailsId = filedetailsId;
	}

	public void setFiledetailsFlag(Integer filedetailsFlag) {
		this.filedetailsFlag = filedetailsFlag;
	}

	public void setFiledetailsDescription(String filedetailsDescription) {
		this.filedetailsDescription = filedetailsDescription;
	}

	public void setFiledetailsComments(String filedetailsComments) {
		this.filedetailsComments = filedetailsComments;
	}

	public void setFiledetailsCreationdate(Timestamp filedetailsCreationdate) {
		this.filedetailsCreationdate = filedetailsCreationdate;
	}

	public void setFiledetailsCreatedby(Integer filedetailsCreatedby) {
		this.filedetailsCreatedby = filedetailsCreatedby;
	}

	public void setFiledetailsLastmodifiedon(Timestamp filedetailsLastmodifiedon) {
		this.filedetailsLastmodifiedon = filedetailsLastmodifiedon;
	}

	public void setFiledetailsLastmodifiedby(Integer filedetailsLastmodifiedby) {
		this.filedetailsLastmodifiedby = filedetailsLastmodifiedby;
	}

	public void setFiledetailsEncounterid(Integer filedetailsEncounterid) {
		this.filedetailsEncounterid = filedetailsEncounterid;
	}

	public void setFiledetailsPatientid(Integer filedetailsPatientid) {
		this.filedetailsPatientid = filedetailsPatientid;
	}

	public void setFiledetailsChartid(Integer filedetailsChartid) {
		this.filedetailsChartid = filedetailsChartid;
	}

	public void setFiledetailsEntityid(Integer filedetailsEntityid) {
		this.filedetailsEntityid = filedetailsEntityid;
	}

	public void setFiledetailsType(String filedetailsType) {
		this.filedetailsType = filedetailsType;
	}

	public void setFiledetailsBuffer3(Boolean filedetailsBuffer3) {
		this.filedetailsBuffer3 = filedetailsBuffer3;
	}

	public void setFiledetailsCategoryid(Integer filedetailsCategoryid) {
		this.filedetailsCategoryid = filedetailsCategoryid;
	}

	public void setFiledetailsUserdate(String filedetailsUserdate) {
		this.filedetailsUserdate = filedetailsUserdate;
	}

	public void setFiledetailsFaxreferenceid(Integer filedetailsFaxreferenceid) {
		this.filedetailsFaxreferenceid = filedetailsFaxreferenceid;
	}

	public void setFiledetailsScantype(Integer filedetailsScantype) {
		this.filedetailsScantype = filedetailsScantype;
	}

	public void setFiledetailsIssigned(Boolean filedetailsIssigned) {
		this.filedetailsIssigned = filedetailsIssigned;
	}

	public void setFiledetailsTemplateid(BigInteger filedetailsTemplateid) {
		this.filedetailsTemplateid = filedetailsTemplateid;
	}



}