package com.glenwood.glaceemr.server.application.models;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;
@SuppressWarnings("serial")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name = "lab_entries_parameter")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabEntriesParameter implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="lab_entries_parameter_id_seq")
	@SequenceGenerator(name ="lab_entries_parameter_id_seq", sequenceName="lab_entries_parameter_id_seq", allocationSize=1)
	@Column(name="lab_entries_parameter_id", nullable=false)
	private Integer labEntriesParameterId;

	@Column(name="lab_entries_parameter_testdetailid", nullable=false)
	private Integer labEntriesParameterTestdetailid;

	@Column(name="lab_entries_parameter_mapid", nullable=false)
	private Integer labEntriesParameterMapid;

	@Column(name="lab_entries_parameter_value")
	private String labEntriesParameterValue;

	@Column(name="lab_entries_parameter_status", length=20)
	private String labEntriesParameterStatus;

	@Column(name="lab_entries_parameter_notes")
	private String labEntriesParameterNotes;

	@Column(name="lab_entries_parameter_chartid")
	private Integer labEntriesParameterChartid;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="lab_entries_parameter_date")
	private Timestamp labEntriesParameterDate;

	@Column(name="lab_entries_parameter_resultstatus", length=20)
	private String labEntriesParameterResultstatus;

	@Column(name="lab_entries_parameter_sortorder", columnDefinition="Integer default 1")
	private Integer labEntriesParameterSortorder;

	@Column(name="lab_entries_parameter_isactive", columnDefinition="Boolean default true")
	private Boolean labEntriesParameterIsactive;

	@Column(name="lab_entries_parameter_normalrange", length=100)
	private String labEntriesParameterNormalrange;

	@Column(name="lab_entries_parameter_labcomp_detailid")
	private Integer labEntriesParameterLabcompDetailid;

	@Column(name="lab_entries_parameter_ispdf", columnDefinition="Integer default 0")
	private Integer labEntriesParameterIspdf;

	@Column(name="lab_entries_parameter_filename_id")
	private Integer labEntriesParameterFilenameId;

	@Column(name="lab_entries_parameter_filename_scanid")
	private Integer labEntriesParameterFilenameScanid;
	
	@Column(name="lab_entries_parameter_name")
	private String labEntriesParameterName;
	
	@Column(name="lab_entries_parameter_code")
	private String labEntriesParameterCode;
	
	@Column(name="lab_entries_parameter_code_system")
	private String labEntriesParameterCodeSystem;

	@ManyToOne(cascade=CascadeType.ALL ,fetch=FetchType.LAZY)
	@JoinColumn(name="lab_entries_parameter_testdetailid", referencedColumnName="lab_entries_testdetail_id", insertable=false, updatable=false)
	@JsonManagedReference
	private LabEntries labEntriesTable;

	@NotFound(action=NotFoundAction.IGNORE) 
	@ManyToOne(cascade=CascadeType.ALL ,fetch=FetchType.LAZY)
	@JoinColumn(name="lab_entries_parameter_mapid", referencedColumnName="lab_parameters_id", insertable=false, updatable=false)
	@JsonManagedReference
	LabParameters labParametersTable;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="lab_entries_parameter_chartid",referencedColumnName="chart_id",insertable=false,updatable=false)
	Chart chart1; 

	public Integer getLabEntriesParameterId() {
		return labEntriesParameterId;
	}

	public void setLabEntriesParameterId(Integer labEntriesParameterId) {
		this.labEntriesParameterId = labEntriesParameterId;
	}

	public Integer getLabEntriesParameterTestdetailid() {
		return labEntriesParameterTestdetailid;
	}

	public void setLabEntriesParameterTestdetailid(
			Integer labEntriesParameterTestdetailid) {
		this.labEntriesParameterTestdetailid = labEntriesParameterTestdetailid;
	}

	public Integer getLabEntriesParameterMapid() {
		return labEntriesParameterMapid;
	}

	public void setLabEntriesParameterMapid(Integer labEntriesParameterMapid) {
		this.labEntriesParameterMapid = labEntriesParameterMapid;
	}

	public String getLabEntriesParameterValue() {
		return labEntriesParameterValue;
	}

	public void setLabEntriesParameterValue(String labEntriesParameterValue) {
		this.labEntriesParameterValue = labEntriesParameterValue;
	}

	public String getLabEntriesParameterStatus() {
		return labEntriesParameterStatus;
	}

	public void setLabEntriesParameterStatus(String labEntriesParameterStatus) {
		this.labEntriesParameterStatus = labEntriesParameterStatus;
	}

	public String getLabEntriesParameterNotes() {
		return labEntriesParameterNotes;
	}

	public void setLabEntriesParameterNotes(String labEntriesParameterNotes) {
		this.labEntriesParameterNotes = labEntriesParameterNotes;
	}

	public Integer getLabEntriesParameterChartid() {
		return labEntriesParameterChartid;
	}

	public void setLabEntriesParameterChartid(Integer labEntriesParameterChartid) {
		this.labEntriesParameterChartid = labEntriesParameterChartid;
	}

	public Timestamp getLabEntriesParameterDate() {
		return labEntriesParameterDate;
	}

	public void setLabEntriesParameterDate(Timestamp labEntriesParameterDate) {
		this.labEntriesParameterDate = labEntriesParameterDate;
	}

	public String getLabEntriesParameterResultstatus() {
		return labEntriesParameterResultstatus;
	}

	public void setLabEntriesParameterResultstatus(
			String labEntriesParameterResultstatus) {
		this.labEntriesParameterResultstatus = labEntriesParameterResultstatus;
	}

	public Integer getLabEntriesParameterSortorder() {
		return labEntriesParameterSortorder;
	}

	public void setLabEntriesParameterSortorder(Integer labEntriesParameterSortorder) {
		this.labEntriesParameterSortorder = labEntriesParameterSortorder;
	}

	public Boolean getLabEntriesParameterIsactive() {
		return labEntriesParameterIsactive;
	}

	public void setLabEntriesParameterIsactive(Boolean labEntriesParameterIsactive) {
		this.labEntriesParameterIsactive = labEntriesParameterIsactive;
	}

	public String getLabEntriesParameterNormalrange() {
		return labEntriesParameterNormalrange;
	}

	public void setLabEntriesParameterNormalrange(
			String labEntriesParameterNormalrange) {
		this.labEntriesParameterNormalrange = labEntriesParameterNormalrange;
	}

	public Integer getLabEntriesParameterLabcompDetailid() {
		return labEntriesParameterLabcompDetailid;
	}

	public void setLabEntriesParameterLabcompDetailid(
			Integer labEntriesParameterLabcompDetailid) {
		this.labEntriesParameterLabcompDetailid = labEntriesParameterLabcompDetailid;
	}

	public Integer getLabEntriesParameterIspdf() {
		return labEntriesParameterIspdf;
	}

	public void setLabEntriesParameterIspdf(Integer labEntriesParameterIspdf) {
		this.labEntriesParameterIspdf = labEntriesParameterIspdf;
	}

	public Integer getLabEntriesParameterFilenameId() {
		return labEntriesParameterFilenameId;
	}

	public void setLabEntriesParameterFilenameId(
			Integer labEntriesParameterFilenameId) {
		this.labEntriesParameterFilenameId = labEntriesParameterFilenameId;
	}

	public Integer getLabEntriesParameterFilenameScanid() {
		return labEntriesParameterFilenameScanid;
	}

	public void setLabEntriesParameterFilenameScanid(
			Integer labEntriesParameterFilenameScanid) {
		this.labEntriesParameterFilenameScanid = labEntriesParameterFilenameScanid;
	}

	public String getLabEntriesParameterName() {
		return labEntriesParameterName;
	}

	public void setLabEntriesParameterName(String labEntriesParameterName) {
		this.labEntriesParameterName = labEntriesParameterName;
	}
	
	public String getLabEntriesParameterCode() {
		return labEntriesParameterCode;
	}

	public void setLabEntriesParameterCode(String labEntriesParameterCode) {
		this.labEntriesParameterCode = labEntriesParameterCode;
	}
	
	public String getLabEntriesParameterCodeSystem() {
		return labEntriesParameterCodeSystem;
	}

	public void setLabEntriesParameterCodeSystem(String labEntriesParameterCodeSystem) {
		this.labEntriesParameterCodeSystem = labEntriesParameterCodeSystem;
	}
	
	public LabParameters getLabParametersTable() {
		return labParametersTable;
	}

	public void setLabParametersTable(LabParameters labParametersTable) {
		this.labParametersTable = labParametersTable;
	}

	public LabEntries getLabEntriesTable() {
		return labEntriesTable;
	}

	public void setLabEntriesTable(LabEntries labEntriesTable) {
		this.labEntriesTable = labEntriesTable;
	}
	/*
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL,optional = false)
	@JoinColumn(name="lab_entries_parameter_mapid",referencedColumnName="lab_parameter_code_paramid", nullable=false, insertable=false, updatable=false)
	@JsonManagedReference
	LabParameterCode labparametercode;  
	/*
	@OneToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="lab_entries_parameter_mapid",referencedColumnName="patient_episode_id", nullable=false, insertable=false, updatable=false)
	PatientEpisode patientepisodeTable;
	 */
}