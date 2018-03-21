package com.glenwood.glaceemr.server.application.services.chart.careplan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarePlanConcernBean {
	
	Integer concernId;
	Integer concernPatientId;
	Integer concernCategoryId;
	Integer concernProviderId;
	Integer concernType;
	String concernCode;
	String concernCodeSystem;
	String concernCodeSystemName;
	String concernCodeDesc;
	Integer concernPriority;
	String concernValue;
	String concernUnit;
	String concernDesc;
	String concernNotes;
	Integer concernStatus;
	String concernStatusUpdatedDate;
	Integer concernCreatedBy;
	String concernCreatedOn;
	Integer concernModifiedBy;
	String concernModifiedOn;
	Integer episodeId;
	Integer concernFrom;
	Integer encounterId;
	String concernCreatedName;
	String concernModifiedName;
	
	public CarePlanConcernBean(){	
	}

	
	public CarePlanConcernBean(Integer concernId, Integer concernPatientId,
			Integer concernCategoryId, Integer concernProviderId,
			Integer concernType, String concernCode, String concernCodeSystem,
			String concernCodeSystemName, String concernCodeDesc,
			Integer concernPriority, String concernValue, String concernUnit,
			String concernDesc, String concernNotes, Integer concernStatus,
			Date concernStatusUpdatedDate, Integer concernCreatedBy,
			Date concernCreatedOn, Integer concernModifiedBy,
			Date concernModifiedOn, Integer episodeId, Integer concernFrom,
			Integer encounterId, String concernCreatedName,
			String concernModifiedName) {
		super();
		DateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.concernId = concernId;
		this.concernPatientId = concernPatientId;
		this.concernCategoryId = concernCategoryId;
		this.concernProviderId = concernProviderId;
		this.concernType = concernType;
		this.concernCode = concernCode;
		this.concernCodeSystem = concernCodeSystem;
		this.concernCodeSystemName = concernCodeSystemName;
		this.concernCodeDesc = concernCodeDesc;
		this.concernPriority = concernPriority;
		this.concernValue = concernValue;
		this.concernUnit = concernUnit;
		this.concernDesc = concernDesc;
		this.concernNotes = concernNotes;
		this.concernStatus = concernStatus;
		if(concernStatusUpdatedDate==null)
			this.concernStatusUpdatedDate = "";
		else
			this.concernStatusUpdatedDate = timeFormat.format(concernStatusUpdatedDate);
		this.concernCreatedBy = concernCreatedBy;
		if(concernCreatedOn==null)
			this.concernCreatedOn = "";
		else
			this.concernCreatedOn = timeFormat.format(concernCreatedOn);
		this.concernModifiedBy = concernModifiedBy;
		if(concernModifiedOn==null)
			this.concernModifiedOn = "";
		else
			this.concernModifiedOn = timeFormat.format(concernModifiedOn);
		this.episodeId = episodeId;
		this.concernFrom = concernFrom;
		this.encounterId = encounterId;
		this.concernCreatedName = concernCreatedName;
		this.concernModifiedName = concernModifiedName;
	}
	public Integer getConcernId() {
		return concernId;
	}
	public void setConcernId(Integer concernId) {
		this.concernId = concernId;
	}
	public Integer getConcernPatientId() {
		return concernPatientId;
	}
	public void setConcernPatientId(Integer concernPatientId) {
		this.concernPatientId = concernPatientId;
	}
	public Integer getConcernCategoryId() {
		return concernCategoryId;
	}
	public void setConcernCategoryId(Integer concernCategoryId) {
		this.concernCategoryId = concernCategoryId;
	}
	public Integer getConcernProviderId() {
		return concernProviderId;
	}
	public void setConcernProviderId(Integer concernProviderId) {
		this.concernProviderId = concernProviderId;
	}
	public Integer getConcernType() {
		return concernType;
	}
	public void setConcernType(Integer concernType) {
		this.concernType = concernType;
	}
	public String getConcernCode() {
		return concernCode;
	}
	public void setConcernCode(String concernCode) {
		this.concernCode = concernCode;
	}
	public String getConcernCodeSystem() {
		return concernCodeSystem;
	}
	public void setConcernCodeSystem(String concernCodeSystem) {
		this.concernCodeSystem = concernCodeSystem;
	}
	public String getConcernCodeSystemName() {
		return concernCodeSystemName;
	}
	public void setConcernCodeSystemName(String concernCodeSystemName) {
		this.concernCodeSystemName = concernCodeSystemName;
	}
	public String getConcernCodeDesc() {
		return concernCodeDesc;
	}
	public void setConcernCodeDesc(String concernCodeDesc) {
		this.concernCodeDesc = concernCodeDesc;
	}
	public Integer getConcernPriority() {
		return concernPriority;
	}
	public void setConcernPriority(Integer concernPriority) {
		this.concernPriority = concernPriority;
	}
	public String getConcernValue() {
		return concernValue;
	}
	public void setConcernValue(String concernValue) {
		this.concernValue = concernValue;
	}
	public String getConcernUnit() {
		return concernUnit;
	}
	public void setConcernUnit(String concernUnit) {
		this.concernUnit = concernUnit;
	}
	public String getConcernDesc() {
		return concernDesc;
	}
	public void setConcernDesc(String concernDesc) {
		this.concernDesc = concernDesc;
	}
	public String getConcernNotes() {
		return concernNotes;
	}
	public void setConcernNotes(String concernNotes) {
		this.concernNotes = concernNotes;
	}
	public Integer getConcernStatus() {
		return concernStatus;
	}
	public void setConcernStatus(Integer concernStatus) {
		this.concernStatus = concernStatus;
	}
	public String getConcernStatusUpdatedDate() {
		return concernStatusUpdatedDate;
	}
	public void setConcernStatusUpdatedDate(String concernStatusUpdatedDate) {
		this.concernStatusUpdatedDate = concernStatusUpdatedDate;
	}
	public Integer getConcernCreatedBy() {
		return concernCreatedBy;
	}
	public void setConcernCreatedBy(Integer concernCreatedBy) {
		this.concernCreatedBy = concernCreatedBy;
	}
	public String getConcernCreatedOn() {
		return concernCreatedOn;
	}
	public void setConcernCreatedOn(String concernCreatedOn) {
		this.concernCreatedOn = concernCreatedOn;
	}
	public Integer getConcernModifiedBy() {
		return concernModifiedBy;
	}
	public void setConcernModifiedBy(Integer concernModifiedBy) {
		this.concernModifiedBy = concernModifiedBy;
	}
	public String getConcernModifiedOn() {
		return concernModifiedOn;
	}
	public void setConcernModifiedOn(String concernModifiedOn) {
		this.concernModifiedOn = concernModifiedOn;
	}
	public Integer getEpisodeId() {
		return episodeId;
	}
	public void setEpisodeId(Integer episodeId) {
		this.episodeId = episodeId;
	}
	public Integer getConcernFrom() {
		return concernFrom;
	}
	public void setConcernFrom(Integer concernFrom) {
		this.concernFrom = concernFrom;
	}
	public Integer getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	public String getConcernCreatedName() {
		return concernCreatedName;
	}
	public void setConcernCreatedName(String concernCreatedName) {
		this.concernCreatedName = concernCreatedName;
	}
	public String getConcernModifiedName() {
		return concernModifiedName;
	}
	public void setConcernModifiedName(String concernModifiedName) {
		this.concernModifiedName = concernModifiedName;
	}
}