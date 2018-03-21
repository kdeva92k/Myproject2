package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "filename")
public class FileName {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="filename_filename_id_seq")
	@SequenceGenerator(name ="filename_filename_id_seq", sequenceName="filename_filename_id_seq", allocationSize=1)
	@Column(name="filename_id")
	private Integer filenameId;


	@Column(name="filename_scanid",unique=true)
	private Integer filenameScanid;

	@Column(name="filename_name")
	private String filenameName;

	@Column(name="filename_fileextension")
	private String filenameFileextension;

	@Column(name="filename_filesize")
	private Integer filenameFilesize;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="filename_createdon")
	private Timestamp filenameCreatedon;

	@Column(name="filename_createdby")
	private Integer filenameCreatedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="filename_modifiedon")
	private Timestamp filenameModifiedon;

	@Column(name="filename_modifiedby")
	private Integer filenameModifiedby;

	@Column(name="filename_isactive")
	private Boolean filenameIsactive;

	@Column(name="filename_orderby")
	private Integer filenameOrderby;

	@Column(name="filename_isreviewed")
	private Boolean filenameIsreviewed;

	@Column(name="filename_reviewedby")
	private Integer filenameReviewedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="filename_reviewedon")
	private Timestamp filenameReviewedon;

	@Column(name="is_notes_present")
	private Boolean isNotesPresent;

	@Column(name="filename_isnotespresent")
	private Boolean filenameIsnotespresent;

	@ManyToOne(cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="filename_reviewedby", referencedColumnName="emp_profile_empid", insertable=false,updatable=false)
	@JsonManagedReference
	private EmployeeProfile empProfile;

	public EmployeeProfile getCreatedByEmpProfileTable() {
		return createdByEmpProfileTable;
	}

	public void setCreatedByEmpProfileTable(EmployeeProfile createdByEmpProfileTable) {
		this.createdByEmpProfileTable = createdByEmpProfileTable;
	}
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="filename_createdby", referencedColumnName="emp_profile_empid", insertable=false,updatable=false)
	@JsonManagedReference
	EmployeeProfile createdByEmpProfileTable;


	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="filename_scanid", referencedColumnName="filedetails_id",  insertable=false, updatable=false)
	@JsonBackReference
	FileDetails fileNameDetails;
	
	public EmployeeProfile getEmpProfile() {
		return empProfile;
	}

	public void setEmpProfile(EmployeeProfile empProfile) {
		this.empProfile = empProfile;
	}

	public FileDetails getFileNameDetails() {
		return fileNameDetails;
	}

	public void setFileNameDetails(FileDetails fileNameDetails) {
		this.fileNameDetails = fileNameDetails;
	}

	public Integer getFilenameId() {
		return filenameId;
	}

	public Integer getFilenameScanid() {
		return filenameScanid;
	}

	public String getFilenameName() {
		return filenameName;
	}

	public String getFilenameFileextension() {
		return filenameFileextension;
	}

	public Integer getFilenameFilesize() {
		return filenameFilesize;
	}

	public Timestamp getFilenameCreatedon() {
		return filenameCreatedon;
	}

	public Integer getFilenameCreatedby() {
		return filenameCreatedby;
	}

	public Timestamp getFilenameModifiedon() {
		return filenameModifiedon;
	}

	public Integer getFilenameModifiedby() {
		return filenameModifiedby;
	}

	public Boolean getFilenameIsactive() {
		return filenameIsactive;
	}

	public Integer getFilenameOrderby() {
		return filenameOrderby;
	}

	public Boolean getFilenameIsreviewed() {
		return filenameIsreviewed;
	}

	public Integer getFilenameReviewedby() {
		return filenameReviewedby;
	}

	public Timestamp getFilenameReviewedon() {
		return filenameReviewedon;
	}

	public Boolean getIsNotesPresent() {
		return isNotesPresent;
	}

	public Boolean getFilenameIsnotespresent() {
		return filenameIsnotespresent;
	}

	public void setFilenameId(Integer filenameId) {
		this.filenameId = filenameId;
	}

	public void setFilenameScanid(Integer filenameScanid) {
		this.filenameScanid = filenameScanid;
	}

	public void setFilenameName(String filenameName) {
		this.filenameName = filenameName;
	}

	public void setFilenameFileextension(String filenameFileextension) {
		this.filenameFileextension = filenameFileextension;
	}

	public void setFilenameFilesize(Integer filenameFilesize) {
		this.filenameFilesize = filenameFilesize;
	}

	public void setFilenameCreatedon(Timestamp filenameCreatedon) {
		this.filenameCreatedon = filenameCreatedon;
	}

	public void setFilenameCreatedby(Integer filenameCreatedby) {
		this.filenameCreatedby = filenameCreatedby;
	}

	public void setFilenameModifiedon(Timestamp filenameModifiedon) {
		this.filenameModifiedon = filenameModifiedon;
	}

	public void setFilenameModifiedby(Integer filenameModifiedby) {
		this.filenameModifiedby = filenameModifiedby;
	}

	public void setFilenameIsactive(Boolean filenameIsactive) {
		this.filenameIsactive = filenameIsactive;
	}

	public void setFilenameOrderby(Integer filenameOrderby) {
		this.filenameOrderby = filenameOrderby;
	}

	public void setFilenameIsreviewed(Boolean filenameIsreviewed) {
		this.filenameIsreviewed = filenameIsreviewed;
	}

	public void setFilenameReviewedby(Integer filenameReviewedby) {
		this.filenameReviewedby = filenameReviewedby;
	}

	public void setFilenameReviewedon(Timestamp filenameReviewedon) {
		this.filenameReviewedon = filenameReviewedon;
	}

	public void setIsNotesPresent(Boolean isNotesPresent) {
		this.isNotesPresent = isNotesPresent;
	}

	public void setFilenameIsnotespresent(Boolean filenameIsnotespresent) {
		this.filenameIsnotespresent = filenameIsnotespresent;
	}
}