package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@Table(name = "hl7_docs_inbox")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hl7DocsInbox implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hl7_docs_inbox_hl7_docs_inbox_id_seq")
	@SequenceGenerator(name ="hl7_docs_inbox_hl7_docs_inbox_id_seq", sequenceName="hl7_docs_inbox_hl7_docs_inbox_id_seq", allocationSize=1)
	@Column(name="hl7_docs_inbox_id")
	private Integer hl7DocsInboxId;

	@Column(name="hl7_docs_inbox_patientid")
	private Integer hl7DocsInboxPatientid;

	@Column(name="hl7_docs_inbox_accountno")
	private String hl7DocsInboxAccountno;

	@Column(name="hl7_docs_inbox_firstname")
	private String hl7DocsInboxFirstname;

	@Column(name="hl7_docs_inbox_lastname")
	private String hl7DocsInboxLastname;

	@Column(name="hl7_docs_inbox_dob")
	private Date hl7DocsInboxDob;

	@Column(name="hl7_docs_inbox_provider_name")
	private String hl7DocsInboxProviderName;

	@Column(name="hl7_docs_vt")
	private String hl7DocsVt;

	@Column(name="hl7_docs_doc_type")
	private String hl7DocsDocType;

	@Column(name="hl7_docs_doc_details")
	private String hl7DocsDocDetails;

	@Column(name="hl7_docs_inbox_filename")
	private String hl7DocsInboxFilename;

	@Column(name="hl7_docs_inbox_placed_date")
	private Date hl7DocsInboxPlacedDate;

	@Column(name="hl7_docs_inbox_ord_date")
	private Date hl7DocsInboxOrdDate;

	@Column(name="hl7_docs_inbox_provider_id")
	private Integer hl7DocsInboxProviderId;

	@Column(name="hl7_docs_inbox_labcompanyid")
	private Integer hl7DocsInboxLabcompanyid;

	@Column(name="hl7_docs_inbox_filedetailid")
	private Integer hl7DocsInboxFiledetailid;

	@Column(name="hl7_docs_inbox_filewise_id")
	private Integer hl7DocsInboxFilewiseId;

	@Column(name="hl7_docs_inbox_isactive")
	private Boolean hl7DocsInboxIsactive;

	@Column(name="hl7_docs_inbox_filetype")
	private Integer hl7DocsInboxFiletype;

	@Column(name="hl7_docs_inbox_unique_identifier")
	private String hl7DocsInboxUniqueIdentifier;
		
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="hl7_docs_inbox_filedetailid" ,referencedColumnName="filedetails_id" ,insertable = false, updatable = false)
	FileDetails fileDetails;
	
	public Integer getHl7DocsInboxId() {
		return hl7DocsInboxId;
	}

	public void setHl7DocsInboxId(Integer hl7DocsInboxId) {
		this.hl7DocsInboxId = hl7DocsInboxId;
	}

	public Integer getHl7DocsInboxPatientid() {
		return hl7DocsInboxPatientid;
	}

	public void setHl7DocsInboxPatientid(Integer hl7DocsInboxPatientid) {
		this.hl7DocsInboxPatientid = hl7DocsInboxPatientid;
	}

	public String getHl7DocsInboxAccountno() {
		return hl7DocsInboxAccountno;
	}

	public void setHl7DocsInboxAccountno(String hl7DocsInboxAccountno) {
		this.hl7DocsInboxAccountno = hl7DocsInboxAccountno;
	}

	public String getHl7DocsInboxFirstname() {
		return hl7DocsInboxFirstname;
	}

	public void setHl7DocsInboxFirstname(String hl7DocsInboxFirstname) {
		this.hl7DocsInboxFirstname = hl7DocsInboxFirstname;
	}

	public String getHl7DocsInboxLastname() {
		return hl7DocsInboxLastname;
	}

	public void setHl7DocsInboxLastname(String hl7DocsInboxLastname) {
		this.hl7DocsInboxLastname = hl7DocsInboxLastname;
	}

	public Date getHl7DocsInboxDob() {
		return hl7DocsInboxDob;
	}

	public void setHl7DocsInboxDob(Date hl7DocsInboxDob) {
		this.hl7DocsInboxDob = hl7DocsInboxDob;
	}

	public String getHl7DocsInboxProviderName() {
		return hl7DocsInboxProviderName;
	}

	public void setHl7DocsInboxProviderName(String hl7DocsInboxProviderName) {
		this.hl7DocsInboxProviderName = hl7DocsInboxProviderName;
	}

	public String getHl7DocsVt() {
		return hl7DocsVt;
	}

	public void setHl7DocsVt(String hl7DocsVt) {
		this.hl7DocsVt = hl7DocsVt;
	}

	public String getHl7DocsDocType() {
		return hl7DocsDocType;
	}

	public void setHl7DocsDocType(String hl7DocsDocType) {
		this.hl7DocsDocType = hl7DocsDocType;
	}

	public String getHl7DocsDocDetails() {
		return hl7DocsDocDetails;
	}

	public void setHl7DocsDocDetails(String hl7DocsDocDetails) {
		this.hl7DocsDocDetails = hl7DocsDocDetails;
	}

	public String getHl7DocsInboxFilename() {
		return hl7DocsInboxFilename;
	}

	public void setHl7DocsInboxFilename(String hl7DocsInboxFilename) {
		this.hl7DocsInboxFilename = hl7DocsInboxFilename;
	}

	public Date getHl7DocsInboxPlacedDate() {
		return hl7DocsInboxPlacedDate;
	}

	public void setHl7DocsInboxPlacedDate(Date hl7DocsInboxPlacedDate) {
		this.hl7DocsInboxPlacedDate = hl7DocsInboxPlacedDate;
	}

	public Date getHl7DocsInboxOrdDate() {
		return hl7DocsInboxOrdDate;
	}

	public void setHl7DocsInboxOrdDate(Date hl7DocsInboxOrdDate) {
		this.hl7DocsInboxOrdDate = hl7DocsInboxOrdDate;
	}

	public Integer getHl7DocsInboxProviderId() {
		return hl7DocsInboxProviderId;
	}

	public void setHl7DocsInboxProviderId(Integer hl7DocsInboxProviderId) {
		this.hl7DocsInboxProviderId = hl7DocsInboxProviderId;
	}

	public Integer getHl7DocsInboxLabcompanyid() {
		return hl7DocsInboxLabcompanyid;
	}

	public void setHl7DocsInboxLabcompanyid(Integer hl7DocsInboxLabcompanyid) {
		this.hl7DocsInboxLabcompanyid = hl7DocsInboxLabcompanyid;
	}

	public Integer getHl7DocsInboxFiledetailid() {
		return hl7DocsInboxFiledetailid;
	}

	public void setHl7DocsInboxFiledetailid(Integer hl7DocsInboxFiledetailid) {
		this.hl7DocsInboxFiledetailid = hl7DocsInboxFiledetailid;
	}

	public Integer getHl7DocsInboxFilewiseId() {
		return hl7DocsInboxFilewiseId;
	}

	public void setHl7DocsInboxFilewiseId(Integer hl7DocsInboxFilewiseId) {
		this.hl7DocsInboxFilewiseId = hl7DocsInboxFilewiseId;
	}

	public Boolean getHl7DocsInboxIsactive() {
		return hl7DocsInboxIsactive;
	}

	public void setHl7DocsInboxIsactive(Boolean hl7DocsInboxIsactive) {
		this.hl7DocsInboxIsactive = hl7DocsInboxIsactive;
	}

	public Integer getHl7DocsInboxFiletype() {
		return hl7DocsInboxFiletype;
	}

	public void setHl7DocsInboxFiletype(Integer hl7DocsInboxFiletype) {
		this.hl7DocsInboxFiletype = hl7DocsInboxFiletype;
	}

	public String getHl7DocsInboxUniqueIdentifier() {
		return hl7DocsInboxUniqueIdentifier;
	}

	public void setHl7DocsInboxUniqueIdentifier(String hl7DocsInboxUniqueIdentifier) {
		this.hl7DocsInboxUniqueIdentifier = hl7DocsInboxUniqueIdentifier;
	}

	public FileDetails getFileDetails() {
		return fileDetails;
	}

	public void setFileDetails(FileDetails fileDetails) {
		this.fileDetails = fileDetails;
	}	
}