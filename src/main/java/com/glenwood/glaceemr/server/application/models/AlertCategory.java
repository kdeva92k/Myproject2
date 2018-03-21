package com.glenwood.glaceemr.server.application.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "alert_category")
public class AlertCategory {

	@Id
	@Column(name="alert_category_id")
	private Integer alertCategoryId;

	@Column(name="alert_category_name")
	private String alertCategoryName;

	@Column(name="alert_category_url")
	private String alertCategoryUrl;

	@Column(name="alert_category_display_name")
	private String alertCategoryDisplayName;

	@Column(name="alert_category_display_order")
	private Integer alertCategoryDisplayOrder;

	@Column(name="alert_category_group")
	private Integer alertCategoryGroup;

	@Column(name="alert_category_type")
	private Integer alertCategoryType;

	@Column(name="alert_category_section")
	private Integer alertCategorySection;

	@Column(name="alert_category_action_map")
	private Integer alertCategoryActionMap;

	@Column(name="alert_category_subpage")
	private Integer alertCategorySubpage;

	@Column(name="alert_category_qr_flag")
	private Integer alertCategoryQrFlag;

	@Column(name="alert_category_qr_url")
	private String alertCategoryQrUrl;

	@Column(name="alert_category_default_user")
	private Integer alertCategoryDefaultUser;

	@Column(name="alert_category_url_caption")
	private String alertCategoryUrlCaption;

	@Column(name="alert_category_is_individual_alert")
	private Boolean alertCategoryIsIndividualAlert;

	@Column(name="alert_category_isworkflow")
	private Boolean alertCategoryIsworkflow;
	

	@OneToMany(cascade=CascadeType.ALL,mappedBy="alertCategoryTable")
	@JsonManagedReference
	List<AlertEvent> alertEventCategoryId;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="alertCategoryTable")
	@JsonManagedReference
	List<AlertArchive> alertArchiveCategoryId;
	
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="alertCategoryTable")
	@JsonManagedReference
	List<AlertPrivilege> alertPrivilegeTable;


	public Integer getAlertCategoryId() {
		return alertCategoryId;
	}


	public void setAlertCategoryId(Integer alertCategoryId) {
		this.alertCategoryId = alertCategoryId;
	}


	public String getAlertCategoryName() {
		return alertCategoryName;
	}


	public void setAlertCategoryName(String alertCategoryName) {
		this.alertCategoryName = alertCategoryName;
	}


	public String getAlertCategoryUrl() {
		return alertCategoryUrl;
	}


	public void setAlertCategoryUrl(String alertCategoryUrl) {
		this.alertCategoryUrl = alertCategoryUrl;
	}


	public String getAlertCategoryDisplayName() {
		return alertCategoryDisplayName;
	}


	public void setAlertCategoryDisplayName(String alertCategoryDisplayName) {
		this.alertCategoryDisplayName = alertCategoryDisplayName;
	}


	public Integer getAlertCategoryDisplayOrder() {
		return alertCategoryDisplayOrder;
	}


	public void setAlertCategoryDisplayOrder(Integer alertCategoryDisplayOrder) {
		this.alertCategoryDisplayOrder = alertCategoryDisplayOrder;
	}


	public Integer getAlertCategoryGroup() {
		return alertCategoryGroup;
	}


	public void setAlertCategoryGroup(Integer alertCategoryGroup) {
		this.alertCategoryGroup = alertCategoryGroup;
	}


	public Integer getAlertCategoryType() {
		return alertCategoryType;
	}


	public void setAlertCategoryType(Integer alertCategoryType) {
		this.alertCategoryType = alertCategoryType;
	}


	public Integer getAlertCategorySection() {
		return alertCategorySection;
	}


	public void setAlertCategorySection(Integer alertCategorySection) {
		this.alertCategorySection = alertCategorySection;
	}


	public Integer getAlertCategoryActionMap() {
		return alertCategoryActionMap;
	}


	public void setAlertCategoryActionMap(Integer alertCategoryActionMap) {
		this.alertCategoryActionMap = alertCategoryActionMap;
	}


	public Integer getAlertCategorySubpage() {
		return alertCategorySubpage;
	}


	public void setAlertCategorySubpage(Integer alertCategorySubpage) {
		this.alertCategorySubpage = alertCategorySubpage;
	}


	public Integer getAlertCategoryQrFlag() {
		return alertCategoryQrFlag;
	}


	public void setAlertCategoryQrFlag(Integer alertCategoryQrFlag) {
		this.alertCategoryQrFlag = alertCategoryQrFlag;
	}


	public String getAlertCategoryQrUrl() {
		return alertCategoryQrUrl;
	}


	public void setAlertCategoryQrUrl(String alertCategoryQrUrl) {
		this.alertCategoryQrUrl = alertCategoryQrUrl;
	}


	public Integer getAlertCategoryDefaultUser() {
		return alertCategoryDefaultUser;
	}


	public void setAlertCategoryDefaultUser(Integer alertCategoryDefaultUser) {
		this.alertCategoryDefaultUser = alertCategoryDefaultUser;
	}


	public String getAlertCategoryUrlCaption() {
		return alertCategoryUrlCaption;
	}


	public void setAlertCategoryUrlCaption(String alertCategoryUrlCaption) {
		this.alertCategoryUrlCaption = alertCategoryUrlCaption;
	}


	public Boolean getAlertCategoryIsIndividualAlert() {
		return alertCategoryIsIndividualAlert;
	}


	public void setAlertCategoryIsIndividualAlert(
			Boolean alertCategoryIsIndividualAlert) {
		this.alertCategoryIsIndividualAlert = alertCategoryIsIndividualAlert;
	}


	public Boolean getAlertCategoryIsworkflow() {
		return alertCategoryIsworkflow;
	}


	public void setAlertCategoryIsworkflow(Boolean alertCategoryIsworkflow) {
		this.alertCategoryIsworkflow = alertCategoryIsworkflow;
	}


	public List<AlertEvent> getAlertEventCategoryId() {
		return alertEventCategoryId;
	}


	public void setAlertEventCategoryId(List<AlertEvent> alertEventCategoryId) {
		this.alertEventCategoryId = alertEventCategoryId;
	}


	public List<AlertPrivilege> getAlertPrivilegeTable() {
		return alertPrivilegeTable;
	}


	public void setAlertPrivilegeTable(List<AlertPrivilege> alertPrivilegeTable) {
		this.alertPrivilegeTable = alertPrivilegeTable;
	}
}