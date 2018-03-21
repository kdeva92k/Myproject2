package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "cda_inbox")
public class CdaInbox {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cda_inbox_id_seq")
	@SequenceGenerator(name = "cda_inbox_id_seq", sequenceName="cda_inbox_id_seq", allocationSize=1)
	@Column(name="id")
	private Integer id;
	
	@Column(name="filetype")
	private Integer filetype;
	
	@Column(name="filename")
	private String filename;
	
	@Column(name="isattached")
	private Boolean isattached;
	
	@Column(name="patientid")
	private Integer patientid;
	
	@Column(name="chartid")
	private Integer chartid;
	
	@Column(name="encounterid")
	private Integer encounterid;
	
	@Column(name="createdby")
	private Integer createdby;
	
	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="creationtime")
	private Timestamp creationtime;
	
	@Column(name="xmlfiletype")
	private Integer xmlfiletype;
	
	@Column(name="unmappedpatientname")
	private String unmappedpatientname;
	
	@Column(name="error")
	private Boolean error;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFiletype() {
		return filetype;
	}

	public void setFiletype(Integer filetype) {
		this.filetype = filetype;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Boolean getIsattached() {
		return isattached;
	}

	public void setIsattached(Boolean isattached) {
		this.isattached = isattached;
	}

	public Integer getPatientid() {
		return patientid;
	}

	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}

	public Integer getChartid() {
		return chartid;
	}

	public void setChartid(Integer chartid) {
		this.chartid = chartid;
	}

	public Integer getEncounterid() {
		return encounterid;
	}

	public void setEncounterid(Integer encounterid) {
		this.encounterid = encounterid;
	}

	public Integer getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}

	public Timestamp getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(Timestamp creationtime) {
		this.creationtime = creationtime;
	}

	public Integer getXmlfiletype() {
		return xmlfiletype;
	}

	public void setXmlfiletype(Integer xmlfiletype) {
		this.xmlfiletype = xmlfiletype;
	}

	public String getUnmappedpatientname() {
		return unmappedpatientname;
	}

	public void setUnmappedpatientname(String unmappedpatientname) {
		this.unmappedpatientname = unmappedpatientname;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}
	
	
	
}
