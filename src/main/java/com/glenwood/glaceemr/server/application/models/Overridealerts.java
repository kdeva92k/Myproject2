package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "overridealerts")
public class Overridealerts {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="overridealerts_tableid_seq")
	@SequenceGenerator(name ="overridealerts_tableid_seq", sequenceName="overridealerts_tableid_seq", allocationSize=1)
	@Column(name="tableid", nullable=false)
	private Integer tableid;

	@Column(name="patientid")
	private Integer patientid;

	@Column(name="testid")
	private Integer testid;

	@Column(name="overridden")
	private Boolean overridden;

	@Column(name="reason", length=254)
	private String reason;

	@Column(name="instanceid")
	private Integer instanceid;

	@Column(name="data", length=100)
	private String data;

	@Column(name="overridden_by")
	private Integer overriddenBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="overridden_on")
	private Timestamp overriddenOn;

	@Column(name="overduedata", length=100)
	private String overduedata;

	@Column(name="overridealerts_flowsheet_element_id", length=100)
	private String overridealertsFlowsheetElementId;

	@Column(name="overridealerts_flowsheet_element_type")
	private Integer overridealertsFlowsheetElementType;

	@Column(name="overridealerts_alert_type")
	private Integer overridealertsAlertType;
	
	@Column(name="overridealerts_flowsheet_map_id")
	private Integer overridealertsFlowsheetMapId;

	public Integer getTableid() {
		return tableid;
	}

	public void setTableid(Integer tableid) {
		this.tableid = tableid;
	}

	public Integer getPatientid() {
		return patientid;
	}

	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}

	public Integer getTestid() {
		return testid;
	}

	public void setTestid(Integer testid) {
		this.testid = testid;
	}

	public Boolean getOverridden() {
		return overridden;
	}

	public void setOverridden(Boolean overridden) {
		this.overridden = overridden;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getInstanceid() {
		return instanceid;
	}

	public void setInstanceid(Integer instanceid) {
		this.instanceid = instanceid;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getOverriddenBy() {
		return overriddenBy;
	}

	public void setOverriddenBy(Integer overriddenBy) {
		this.overriddenBy = overriddenBy;
	}

	public Date getOverriddenOn() {
		return overriddenOn;
	}

	public void setOverriddenOn(Timestamp overriddenOn) {
		this.overriddenOn = overriddenOn;
	}

	public String getOverduedata() {
		return overduedata;
	}

	public void setOverduedata(String overduedata) {
		this.overduedata = overduedata;
	}

	public String getOverridealertsFlowsheetElementId() {
		return overridealertsFlowsheetElementId;
	}

	public void setOverridealertsFlowsheetElementId(
			String overridealertsFlowsheetElementId) {
		this.overridealertsFlowsheetElementId = overridealertsFlowsheetElementId;
	}

	public Integer getOverridealertsFlowsheetElementType() {
		return overridealertsFlowsheetElementType;
	}

	public void setOverridealertsFlowsheetElementType(
			Integer overridealertsFlowsheetElementType) {
		this.overridealertsFlowsheetElementType = overridealertsFlowsheetElementType;
	}

	public Integer getOverridealertsAlertType() {
		return overridealertsAlertType;
	}

	public void setOverridealertsAlertType(Integer overridealertsAlertType) {
		this.overridealertsAlertType = overridealertsAlertType;
	}

	public Integer getOverridealertsFlowsheetMapId() {
		return overridealertsFlowsheetMapId;
	}

	public void setOverridealertsFlowsheetMapId(Integer overridealertsFlowsheetMapId) {
		this.overridealertsFlowsheetMapId = overridealertsFlowsheetMapId;
	}
}