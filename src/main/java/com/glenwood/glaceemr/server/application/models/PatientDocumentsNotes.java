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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "patient_doc_notes")
public class PatientDocumentsNotes {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_doc_notes_notes_id_seq")
	@SequenceGenerator(name ="patient_doc_notes_notes_id_seq", sequenceName="patient_doc_notes_notes_id_seq", allocationSize=1)
	@Column(name="notes_id")
	private Integer notesId;

	@Column(name="notes_filenameid")
	private Integer notesFilenameid;

	@Column(name="notes_patientnotes")
	private String notesPatientnotes;

	public EmployeeProfile getCreatedByEmpProfileTable() {
		return createdByEmpProfileTable;
	}

	public void setCreatedByEmpProfileTable(EmployeeProfile createdByEmpProfileTable) {
		this.createdByEmpProfileTable = createdByEmpProfileTable;
	}

	@Column(name="notes_createdby")
	private Integer notesCreatedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="notes_createdon")
	private Timestamp notesCreatedon;

	@Column(name="notes_modifiedby")
	private Integer notesModifiedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="notes_modifiedon")
	private Timestamp notesModifiedon;

	@Column(name="notes_status")
	private Integer notesStatus;
	
	

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="notes_createdby", referencedColumnName="emp_profile_empid", insertable=false,updatable=false)
	@JsonManagedReference
	EmployeeProfile createdByEmpProfileTable;

	public Integer getNotesId() {
		return notesId;
	}

	public void setNotesId(Integer notesId) {
		this.notesId = notesId;
	}

	public Integer getNotesFilenameid() {
		return notesFilenameid;
	}

	public void setNotesFilenameid(Integer notesFilenameid) {
		this.notesFilenameid = notesFilenameid;
	}

	public String getNotesPatientnotes() {
		return notesPatientnotes;
	}

	public void setNotesPatientnotes(String notesPatientnotes) {
		this.notesPatientnotes = notesPatientnotes;
	}

	public Integer getNotesCreatedby() {
		return notesCreatedby;
	}

	public void setNotesCreatedby(Integer notesCreatedby) {
		this.notesCreatedby = notesCreatedby;
	}

	public Timestamp getNotesCreatedon() {
		return notesCreatedon;
	}

	public void setNotesCreatedon(Timestamp notesCreatedon) {
		this.notesCreatedon = notesCreatedon;
	}

	public Integer getNotesModifiedby() {
		return notesModifiedby;
	}

	public void setNotesModifiedby(Integer notesModifiedby) {
		this.notesModifiedby = notesModifiedby;
	}

	public Timestamp getNotesModifiedon() {
		return notesModifiedon;
	}

	public void setNotesModifiedon(Timestamp notesModifiedon) {
		this.notesModifiedon = notesModifiedon;
	}

	public Integer getNotesStatus() {
		return notesStatus;
	}

	public void setNotesStatus(Integer notesStatus) {
		this.notesStatus = notesStatus;
	}
}