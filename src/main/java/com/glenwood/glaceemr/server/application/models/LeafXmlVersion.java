package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "leaf_xml_version")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeafXmlVersion {

	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leaf_xml_version_leaf_xml_version_id_seq")
	@SequenceGenerator(name = "leaf_xml_version_leaf_xml_version_id_seq", sequenceName = "leaf_xml_version_leaf_xml_version_id_seq", allocationSize = 1)
	@Column(name="leaf_xml_version_id")
	private Integer leafXmlVersionId;

	@Column(name="leaf_xml_version_tabid")
	private Integer leafXmlVersionTabid;

	@Column(name="leaf_xml_version_xmlurl")
	private String leafXmlVersionXmlurl;

	@Column(name="leaf_xml_version_xslurl")
	private String leafXmlVersionXslurl;

	@Column(name="leaf_xml_version_version")
	private String leafXmlVersionVersion;

	@Column(name="leaf_xml_version_printxslurl")
	private String leafXmlVersionPrintxslurl;

	@Column(name="leaf_xml_version_condition")
	private String leafXmlVersionCondition;

	@Column(name="leaf_xml_version_unknown")
	private String leafXmlVersionUnknown;

	@Column(name="leaf_xml_version_tab_type")
	private Integer leafXmlVersionTabType;

	@Column(name="leaf_xml_version_ipad_xslurl")
	private String leafXmlVersionIpadXslurl;
	
	@OneToMany(mappedBy="leafXMLVersion", fetch=FetchType.LAZY)
	@JsonManagedReference 
	List<LeafVersion> leafVersionsList;

	public Integer getLeafXmlVersionId() {
		return leafXmlVersionId;
	}

	public void setLeafXmlVersionId(Integer leafXmlVersionId) {
		this.leafXmlVersionId = leafXmlVersionId;
	}

	public Integer getLeafXmlVersionTabid() {
		return leafXmlVersionTabid;
	}

	public void setLeafXmlVersionTabid(Integer leafXmlVersionTabid) {
		this.leafXmlVersionTabid = leafXmlVersionTabid;
	}

	public String getLeafXmlVersionXmlurl() {
		return leafXmlVersionXmlurl;
	}

	public void setLeafXmlVersionXmlurl(String leafXmlVersionXmlurl) {
		this.leafXmlVersionXmlurl = leafXmlVersionXmlurl;
	}

	public String getLeafXmlVersionXslurl() {
		return leafXmlVersionXslurl;
	}

	public void setLeafXmlVersionXslurl(String leafXmlVersionXslurl) {
		this.leafXmlVersionXslurl = leafXmlVersionXslurl;
	}

	public String getLeafXmlVersionVersion() {
		return leafXmlVersionVersion;
	}

	public void setLeafXmlVersionVersion(String leafXmlVersionVersion) {
		this.leafXmlVersionVersion = leafXmlVersionVersion;
	}

	public String getLeafXmlVersionPrintxslurl() {
		return leafXmlVersionPrintxslurl;
	}

	public void setLeafXmlVersionPrintxslurl(String leafXmlVersionPrintxslurl) {
		this.leafXmlVersionPrintxslurl = leafXmlVersionPrintxslurl;
	}

	public String getLeafXmlVersionCondition() {
		return leafXmlVersionCondition;
	}

	public void setLeafXmlVersionCondition(String leafXmlVersionCondition) {
		this.leafXmlVersionCondition = leafXmlVersionCondition;
	}

	public String getLeafXmlVersionUnknown() {
		return leafXmlVersionUnknown;
	}

	public void setLeafXmlVersionUnknown(String leafXmlVersionUnknown) {
		this.leafXmlVersionUnknown = leafXmlVersionUnknown;
	}

	public Integer getLeafXmlVersionTabType() {
		return leafXmlVersionTabType;
	}

	public void setLeafXmlVersionTabType(Integer leafXmlVersionTabType) {
		this.leafXmlVersionTabType = leafXmlVersionTabType;
	}

	public String getLeafXmlVersionIpadXslurl() {
		return leafXmlVersionIpadXslurl;
	}

	public void setLeafXmlVersionIpadXslurl(String leafXmlVersionIpadXslurl) {
		this.leafXmlVersionIpadXslurl = leafXmlVersionIpadXslurl;
	}

	public List<LeafVersion> getLeafVersionsList() {
		return leafVersionsList;
	}

	public void setLeafVersionsList(List<LeafVersion> leafVersionsList) {
		this.leafVersionsList = leafVersionsList;
	}
	
	
}