package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "leaf_version")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeafVersion {

	@Id
	@Column(name="leaf_version_id")
	private Integer leafVersionId;

	@Column(name="leaf_version_tab_id")
	private Integer leafVersionTabId;

	@Column(name="leaf_version_xml_version_id")
	private Integer leafVersionXmlVersionId;

	@Column(name="leaf_version_unknown")
	private Boolean leafVersionUnknown;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="leaf_version_xml_version_id", referencedColumnName="leaf_xml_version_id", insertable=false, updatable=false)
	LeafXmlVersion leafXMLVersion;

	public Integer getLeafVersionId() {
		return leafVersionId;
	}

	public void setLeafVersionId(Integer leafVersionId) {
		this.leafVersionId = leafVersionId;
	}

	public Integer getLeafVersionTabId() {
		return leafVersionTabId;
	}

	public void setLeafVersionTabId(Integer leafVersionTabId) {
		this.leafVersionTabId = leafVersionTabId;
	}

	public Integer getLeafVersionXmlVersionId() {
		return leafVersionXmlVersionId;
	}

	public void setLeafVersionXmlVersionId(Integer leafVersionXmlVersionId) {
		this.leafVersionXmlVersionId = leafVersionXmlVersionId;
	}

	public Boolean getLeafVersionUnknown() {
		return leafVersionUnknown;
	}

	public void setLeafVersionUnknown(Boolean leafVersionUnknown) {
		this.leafVersionUnknown = leafVersionUnknown;
	}

	public LeafXmlVersion getLeafXMLVersion() {
		return leafXMLVersion;
	}

	public void setLeafXMLVersion(LeafXmlVersion leafXMLVersion) {
		this.leafXMLVersion = leafXMLVersion;
	}
	
}