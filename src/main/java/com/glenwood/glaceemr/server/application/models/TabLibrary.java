package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "tab_library")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TabLibrary {

	@Id
	@Column(name="tab_library_id")
	private Integer tabLibraryId;

	@Column(name="tab_library_displayname")
	private String tabLibraryDisplayname;

	@Column(name="tab_library_description")
	private String tabLibraryDescription;

	@Column(name="tab_library_actionurl")
	private String tabLibraryActionurl;

	@Column(name="tab_library_summaryurl")
	private String tabLibrarySummaryurl;

	@Column(name="tab_library_isactive")
	private Boolean tabLibraryIsactive;

	@Column(name="tab_library_xmlurl")
	private String tabLibraryXmlurl;

	@Column(name="tab_library_xslurl")
	private String tabLibraryXslurl;

	@Column(name="tab_library_isxml")
	private Integer tabLibraryIsxml;

	@Column(name="tab_library_xml_version_id")
	private Integer tabLibraryXmlVersionId;

	@Column(name="tab_library_xml_group")
	private Integer tabLibraryXmlGroup;

	@Column(name="tab_library_printurl")
	private String tabLibraryPrinturl;

	@Column(name="tab_library_print_incheckout")
	private Boolean tabLibraryPrintIncheckout;

	@Column(name="tab_library_iscodified")
	private Integer tabLibraryIscodified;

	public Integer getTabLibraryId() {
		return tabLibraryId;
	}

	public void setTabLibraryId(Integer tabLibraryId) {
		this.tabLibraryId = tabLibraryId;
	}

	public String getTabLibraryDisplayname() {
		return tabLibraryDisplayname;
	}

	public void setTabLibraryDisplayname(String tabLibraryDisplayname) {
		this.tabLibraryDisplayname = tabLibraryDisplayname;
	}

	public String getTabLibraryDescription() {
		return tabLibraryDescription;
	}

	public void setTabLibraryDescription(String tabLibraryDescription) {
		this.tabLibraryDescription = tabLibraryDescription;
	}

	public String getTabLibraryActionurl() {
		return tabLibraryActionurl;
	}

	public void setTabLibraryActionurl(String tabLibraryActionurl) {
		this.tabLibraryActionurl = tabLibraryActionurl;
	}

	public String getTabLibrarySummaryurl() {
		return tabLibrarySummaryurl;
	}

	public void setTabLibrarySummaryurl(String tabLibrarySummaryurl) {
		this.tabLibrarySummaryurl = tabLibrarySummaryurl;
	}

	public Boolean getTabLibraryIsactive() {
		return tabLibraryIsactive;
	}

	public void setTabLibraryIsactive(Boolean tabLibraryIsactive) {
		this.tabLibraryIsactive = tabLibraryIsactive;
	}

	public String getTabLibraryXmlurl() {
		return tabLibraryXmlurl;
	}

	public void setTabLibraryXmlurl(String tabLibraryXmlurl) {
		this.tabLibraryXmlurl = tabLibraryXmlurl;
	}

	public String getTabLibraryXslurl() {
		return tabLibraryXslurl;
	}

	public void setTabLibraryXslurl(String tabLibraryXslurl) {
		this.tabLibraryXslurl = tabLibraryXslurl;
	}

	public Integer getTabLibraryIsxml() {
		return tabLibraryIsxml;
	}

	public void setTabLibraryIsxml(Integer tabLibraryIsxml) {
		this.tabLibraryIsxml = tabLibraryIsxml;
	}

	public Integer getTabLibraryXmlVersionId() {
		return tabLibraryXmlVersionId;
	}

	public void setTabLibraryXmlVersionId(Integer tabLibraryXmlVersionId) {
		this.tabLibraryXmlVersionId = tabLibraryXmlVersionId;
	}

	public Integer getTabLibraryXmlGroup() {
		return tabLibraryXmlGroup;
	}

	public void setTabLibraryXmlGroup(Integer tabLibraryXmlGroup) {
		this.tabLibraryXmlGroup = tabLibraryXmlGroup;
	}

	public String getTabLibraryPrinturl() {
		return tabLibraryPrinturl;
	}

	public void setTabLibraryPrinturl(String tabLibraryPrinturl) {
		this.tabLibraryPrinturl = tabLibraryPrinturl;
	}

	public Boolean getTabLibraryPrintIncheckout() {
		return tabLibraryPrintIncheckout;
	}

	public void setTabLibraryPrintIncheckout(Boolean tabLibraryPrintIncheckout) {
		this.tabLibraryPrintIncheckout = tabLibraryPrintIncheckout;
	}

	public Integer getTabLibraryIscodified() {
		return tabLibraryIscodified;
	}

	public void setTabLibraryIscodified(Integer tabLibraryIscodified) {
		this.tabLibraryIscodified = tabLibraryIscodified;
	}
	
	
}