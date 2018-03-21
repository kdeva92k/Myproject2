package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "vis")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vis {

	@Id
	@Column(name="vis_id")
	private Integer visId;

	@Column(name="vis_vaccine_group_code")
	private String visVaccineGroupCode;

	@Column(name="vis_name")
	private String visName;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="vis_publication_date")
	private Timestamp visPublicationDate;

	@OneToOne(fetch=FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name="vis_id", referencedColumnName="vis_file_mapping_vis_id", insertable=false, updatable=false)
	private VisFileMapping visFileMapping;
	
	public VisFileMapping getVisFileMapping() {
		return visFileMapping;
	}

	public void setVisFileMapping(VisFileMapping visFileMapping) {
		this.visFileMapping = visFileMapping;
	}

	public Integer getVisId() {
		return visId;
	}

	public void setVisId(Integer visId) {
		this.visId = visId;
	}

	public String getVisVaccineGroupCode() {
		return visVaccineGroupCode;
	}

	public void setVisVaccineGroupCode(String visVaccineGroupCode) {
		this.visVaccineGroupCode = visVaccineGroupCode;
	}

	public String getVisName() {
		return visName;
	}

	public void setVisName(String visName) {
		this.visName = visName;
	}

	public Timestamp getVisPublicationDate() {
		return visPublicationDate;
	}

	public void setVisPublicationDate(Timestamp visPublicationDate) {
		this.visPublicationDate = visPublicationDate;
	}
	
}