package com.glenwood.glaceemr.server.application.models;

import java.util.Date;
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

@Entity
@Table(name = "hl7_result_inbox")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hl7ResultInbox {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hl7_result_inbox_hl7_result_inbox_id_seq")
	@SequenceGenerator(name ="hl7_result_inbox_hl7_result_inbox_id_seq", sequenceName="hl7_result_inbox_hl7_result_inbox_id_seq", allocationSize=1)
	@Column(name="hl7_result_inbox_id")
	private Integer hl7ResultInboxId;

	@Column(name="hl7_result_inbox_accountno")
	private String hl7ResultInboxAccountno;

	@Column(name="hl7_result_inbox_firstname")
	private String hl7ResultInboxFirstname;

	@Column(name="hl7_result_inbox_lastname")
	private String hl7ResultInboxLastname;

	@Column(name="hl7_result_inbox_dob")
	private Date hl7ResultInboxDob;

	@Column(name="hl7_result_inbox_filename")
	private String hl7ResultInboxFilename;

	@Column(name="hl7_result_inbox_status")
	private Integer hl7ResultInboxStatus;

	@Column(name="hl7_result_inbox_placed_date")
	private Date hl7ResultInboxPlacedDate;

	@Column(name="hl7_result_inbox_reviewed")
	private Integer hl7ResultInboxReviewed;

	@Column(name="hl7_result_inbox_remind")
	private Integer hl7ResultInboxRemind;

	@Column(name="hl7_result_inbox_staff_id")
	private Integer hl7ResultInboxStaffId;

	@Column(name="hl7_result_inbox_result_type")
	private Integer hl7ResultInboxResultType;

	@Column(name="hl7_result_inbox_labcompanyid")
	private Integer hl7ResultInboxLabcompanyid;

	@Column(name="hl7_result_inbox_labaccessionno")
	private String hl7ResultInboxLabaccessionno;

	@Column(name="hl7_result_inbox_comments")
	private String hl7ResultInboxComments;

	@Column(name="hl7_result_inbox_modified_by")
	private Integer hl7ResultInboxModifiedBy;

	@Column(name="hl7_result_inbox_modified_on")
	private Date hl7ResultInboxModifiedOn;

	@Column(name="hl7_result_inbox_prelimresult_id")
	private Integer hl7ResultInboxPrelimresultId;

	@Column(name="hl7_result_inbox_isactive")
	private Boolean hl7ResultInboxIsactive;

	@Column(name="hl7_result_inbox_documentid")
	private Integer hl7ResultInboxDocumentid;

	@Column(name="hl7_result_inbox_isabnormal")
	private Boolean hl7ResultInboxIsabnormal;

	@Column(name="hl7_result_inbox_placerorderno")
	private String hl7ResultInboxPlacerorderno;

	@Column(name="hl7_result_inbox_placergroupno")
	private String hl7ResultInboxPlacergroupno;

	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name="hl7_result_inbox_accountno", referencedColumnName="patient_registration_accountno", insertable=false, updatable=false)
	private PatientRegistration patientRegistration;

	@ManyToOne
	@JoinColumn(name="hl7_result_inbox_labcompanyid", referencedColumnName="id", insertable=false, updatable=false)
	private Hl7importCompanies importCompanies;
	
	@OneToMany(mappedBy="hl7ResultInbox", fetch=FetchType.EAGER)
	@JsonManagedReference
	private List<Hl7Unmappedresults> hl7UnmappedResults;
	
	public Hl7ResultInbox() {
	}
	
	public Hl7ResultInbox(Integer hl7ResultInboxId,
			String hl7ResultInboxAccountno,	
			String hl7ResultInboxFirstname,	
			String hl7ResultInboxLastname, 
			Integer hl7ResultInboxStatus,
			String hl7ResultInboxFilename, 
			Integer hl7ResultInboxReviewed,
			Date hl7ResultInboxDob,
			Date hl7ResultInboxPlacedDate) {
		this.hl7ResultInboxId = hl7ResultInboxId;
		this.hl7ResultInboxAccountno = hl7ResultInboxAccountno;
		this.hl7ResultInboxFirstname = hl7ResultInboxFirstname;
		this.hl7ResultInboxLastname = hl7ResultInboxLastname;
		this.hl7ResultInboxStatus = hl7ResultInboxStatus;
		this.hl7ResultInboxFilename = hl7ResultInboxFilename;
		this.hl7ResultInboxReviewed = hl7ResultInboxReviewed;
		this.hl7ResultInboxDob = hl7ResultInboxDob;
		this.hl7ResultInboxPlacedDate = hl7ResultInboxPlacedDate;
		
	}
	
	public Hl7ResultInbox(String hl7ResultInboxAccountno,
			Integer hl7ResultInboxId,	
			String hl7ResultInboxFirstname,	
			String hl7ResultInboxLastname, 
			Integer hl7ResultInboxStatus,
			Date hl7ResultInboxPlacedDate,
			Integer hl7ResultInboxReviewed,
			Integer hl7ResultInboxPrelimresultId,
			String hl7ResultInboxLabaccessionno,
			String hl7ResultInboxPlacerorderno,
			String hl7ResultInboxPlacergroupno,
			Integer hl7ResultInboxDocumentid,
			Integer hl7ResultInboxLabcompanyid) {
		this.hl7ResultInboxAccountno = hl7ResultInboxAccountno;
		this.hl7ResultInboxId = hl7ResultInboxId;
		this.hl7ResultInboxFirstname = hl7ResultInboxFirstname;
		this.hl7ResultInboxLastname = hl7ResultInboxLastname;
		this.hl7ResultInboxStatus = hl7ResultInboxStatus;
		this.hl7ResultInboxPlacedDate = hl7ResultInboxPlacedDate;
		this.hl7ResultInboxReviewed = hl7ResultInboxReviewed;
		this.hl7ResultInboxPrelimresultId=hl7ResultInboxPrelimresultId;
		this.hl7ResultInboxLabaccessionno=hl7ResultInboxLabaccessionno;
		this.hl7ResultInboxPlacerorderno=hl7ResultInboxPlacerorderno;
		this.hl7ResultInboxPlacergroupno=hl7ResultInboxPlacergroupno;
		this.hl7ResultInboxDocumentid=hl7ResultInboxDocumentid;
		this.hl7ResultInboxLabcompanyid=hl7ResultInboxLabcompanyid;
	}


	public Integer getHl7ResultInboxId() {
		return hl7ResultInboxId;
	}

	public void setHl7ResultInboxId(Integer hl7ResultInboxId) {
		this.hl7ResultInboxId = hl7ResultInboxId;
	}

	public String getHl7ResultInboxAccountno() {
		return hl7ResultInboxAccountno;
	}

	public void setHl7ResultInboxAccountno(String hl7ResultInboxAccountno) {
		this.hl7ResultInboxAccountno = hl7ResultInboxAccountno;
	}

	public String getHl7ResultInboxFirstname() {
		return hl7ResultInboxFirstname;
	}

	public void setHl7ResultInboxFirstname(String hl7ResultInboxFirstname) {
		this.hl7ResultInboxFirstname = hl7ResultInboxFirstname;
	}

	public String getHl7ResultInboxLastname() {
		return hl7ResultInboxLastname;
	}

	public void setHl7ResultInboxLastname(String hl7ResultInboxLastname) {
		this.hl7ResultInboxLastname = hl7ResultInboxLastname;
	}

	public Date getHl7ResultInboxDob() {
		return hl7ResultInboxDob;
	}

	public void setHl7ResultInboxDob(Date hl7ResultInboxDob) {
		this.hl7ResultInboxDob = hl7ResultInboxDob;
	}

	public String getHl7ResultInboxFilename() {
		return hl7ResultInboxFilename;
	}

	public void setHl7ResultInboxFilename(String hl7ResultInboxFilename) {
		this.hl7ResultInboxFilename = hl7ResultInboxFilename;
	}

	public Integer getHl7ResultInboxStatus() {
		return hl7ResultInboxStatus;
	}

	public void setHl7ResultInboxStatus(Integer hl7ResultInboxStatus) {
		this.hl7ResultInboxStatus = hl7ResultInboxStatus;
	}

	public Date getHl7ResultInboxPlacedDate() {
		return hl7ResultInboxPlacedDate;
	}

	public void setHl7ResultInboxPlacedDate(Date hl7ResultInboxPlacedDate) {
		this.hl7ResultInboxPlacedDate = hl7ResultInboxPlacedDate;
	}

	public Integer getHl7ResultInboxReviewed() {
		return hl7ResultInboxReviewed;
	}

	public void setHl7ResultInboxReviewed(Integer hl7ResultInboxReviewed) {
		this.hl7ResultInboxReviewed = hl7ResultInboxReviewed;
	}

	public Integer getHl7ResultInboxRemind() {
		return hl7ResultInboxRemind;
	}

	public void setHl7ResultInboxRemind(Integer hl7ResultInboxRemind) {
		this.hl7ResultInboxRemind = hl7ResultInboxRemind;
	}

	public Integer getHl7ResultInboxStaffId() {
		return hl7ResultInboxStaffId;
	}

	public void setHl7ResultInboxStaffId(Integer hl7ResultInboxStaffId) {
		this.hl7ResultInboxStaffId = hl7ResultInboxStaffId;
	}

	public Integer getHl7ResultInboxResultType() {
		return hl7ResultInboxResultType;
	}

	public void setHl7ResultInboxResultType(Integer hl7ResultInboxResultType) {
		this.hl7ResultInboxResultType = hl7ResultInboxResultType;
	}

	public Integer getHl7ResultInboxLabcompanyid() {
		return hl7ResultInboxLabcompanyid;
	}

	public void setHl7ResultInboxLabcompanyid(Integer hl7ResultInboxLabcompanyid) {
		this.hl7ResultInboxLabcompanyid = hl7ResultInboxLabcompanyid;
	}

	public String getHl7ResultInboxLabaccessionno() {
		return hl7ResultInboxLabaccessionno;
	}

	public void setHl7ResultInboxLabaccessionno(String hl7ResultInboxLabaccessionno) {
		this.hl7ResultInboxLabaccessionno = hl7ResultInboxLabaccessionno;
	}

	public String getHl7ResultInboxComments() {
		return hl7ResultInboxComments;
	}

	public void setHl7ResultInboxComments(String hl7ResultInboxComments) {
		this.hl7ResultInboxComments = hl7ResultInboxComments;
	}

	public Integer getHl7ResultInboxModifiedBy() {
		return hl7ResultInboxModifiedBy;
	}

	public void setHl7ResultInboxModifiedBy(Integer hl7ResultInboxModifiedBy) {
		this.hl7ResultInboxModifiedBy = hl7ResultInboxModifiedBy;
	}

	public Date getHl7ResultInboxModifiedOn() {
		return hl7ResultInboxModifiedOn;
	}

	public void setHl7ResultInboxModifiedOn(Date hl7ResultInboxModifiedOn) {
		this.hl7ResultInboxModifiedOn = hl7ResultInboxModifiedOn;
	}

	public Integer getHl7ResultInboxPrelimresultId() {
		return hl7ResultInboxPrelimresultId;
	}

	public void setHl7ResultInboxPrelimresultId(Integer hl7ResultInboxPrelimresultId) {
		this.hl7ResultInboxPrelimresultId = hl7ResultInboxPrelimresultId;
	}

	public Boolean getHl7ResultInboxIsactive() {
		return hl7ResultInboxIsactive;
	}

	public void setHl7ResultInboxIsactive(Boolean hl7ResultInboxIsactive) {
		this.hl7ResultInboxIsactive = hl7ResultInboxIsactive;
	}

	public Integer getHl7ResultInboxDocumentid() {
		return hl7ResultInboxDocumentid;
	}

	public void setHl7ResultInboxDocumentid(Integer hl7ResultInboxDocumentid) {
		this.hl7ResultInboxDocumentid = hl7ResultInboxDocumentid;
	}

	public Boolean getHl7ResultInboxIsabnormal() {
		return hl7ResultInboxIsabnormal;
	}

	public void setHl7ResultInboxIsabnormal(Boolean hl7ResultInboxIsabnormal) {
		this.hl7ResultInboxIsabnormal = hl7ResultInboxIsabnormal;
	}

	public String getHl7ResultInboxPlacerorderno() {
		return hl7ResultInboxPlacerorderno;
	}

	public void setHl7ResultInboxPlacerorderno(String hl7ResultInboxPlacerorderno) {
		this.hl7ResultInboxPlacerorderno = hl7ResultInboxPlacerorderno;
	}

	public String getHl7ResultInboxPlacergroupno() {
		return hl7ResultInboxPlacergroupno;
	}

	public void setHl7ResultInboxPlacergroupno(String hl7ResultInboxPlacergroupno) {
		this.hl7ResultInboxPlacergroupno = hl7ResultInboxPlacergroupno;
	}

	public PatientRegistration getPatientRegistration() {
		return patientRegistration;
	}

	public void setPatientRegistration(PatientRegistration patientRegistration) {
		this.patientRegistration = patientRegistration;
	}

	public Hl7importCompanies getImportCompanies() {
		return importCompanies;
	}

	public void setImportCompanies(Hl7importCompanies importCompanies) {
		this.importCompanies = importCompanies;
	}

	public List<Hl7Unmappedresults> getHl7UnmappedResults() {
		return hl7UnmappedResults;
	}

	public void setHl7UnmappedResults(List<Hl7Unmappedresults> hl7UnmappedResults) {
		this.hl7UnmappedResults = hl7UnmappedResults;
	}
}