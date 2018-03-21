package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sch_drnotes")
public class SchedulerDrnotes {

	@Id
	@Column(name="sch_drnotes_id")
	private Integer schDrnotesId;

	@Column(name="sch_drnotes_resource")
	private Integer schDrnotesResource;

	@Column(name="sch_drnotes_apptdate")
	private Date schDrnotesApptdate;
	
	@Column(name="sch_drnotes_notes")
	private String schDrnotesNotes;

	public Integer getSchDrnotesId() {
		return schDrnotesId;
	}

	public void setSchDrnotesId(Integer schDrnotesId) {
		this.schDrnotesId = schDrnotesId;
	}

	public Integer getSchDrnotesResource() {
		return schDrnotesResource;
	}

	public void setSchDrnotesResource(Integer schDrnotesResource) {
		this.schDrnotesResource = schDrnotesResource;
	}

	public Date getSchDrnotesApptdate() {
		return schDrnotesApptdate;
	}

	public void setSchDrnotesApptdate(Date schDrnotesApptdate) {
		this.schDrnotesApptdate = schDrnotesApptdate;
	}

	public String getSchDrnotesNotes() {
		return schDrnotesNotes;
	}

	public void setSchDrnotesNotes(String schDrnotesNotes) {
		this.schDrnotesNotes = schDrnotesNotes;
	}
	
}