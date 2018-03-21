package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "App_Reference_Values")
public class AppReferenceValues implements Serializable{

	@Id
	@Column(name="App_Reference_Values_Id")
	private Integer App_Reference_Values_Id;

	@Column(name="App_Reference_Values_tableId")
	private Integer App_Reference_Values_tableId;

	
	@Column(name="App_Reference_Values_statusId")
	private Integer App_Reference_Values_statusId;

	@Column(name="App_Reference_Values_statusName")
	private String App_Reference_Values_statusName;

	@Column(name="App_Reference_Values_option_Color")
	private String App_Reference_Values_option_Color;

	public Integer getApp_Reference_Values_Id() {
		return App_Reference_Values_Id;
	}

	public void setApp_Reference_Values_Id(Integer App_Reference_Values_Id) {
		this.App_Reference_Values_Id = App_Reference_Values_Id;
	}

	public Integer getApp_Reference_Values_tableId() {
		return App_Reference_Values_tableId;
	}

	public void setApp_Reference_Values_tableId(Integer App_Reference_Values_tableId) {
		this.App_Reference_Values_tableId = App_Reference_Values_tableId;
	}

	public Integer getApp_Reference_Values_statusId() {
		return App_Reference_Values_statusId;
	}

	public void setApp_Reference_Values_statusId(Integer App_Reference_Values_statusId) {
		this.App_Reference_Values_statusId = App_Reference_Values_statusId;
	}

	public String getApp_Reference_Values_statusName() {
		return App_Reference_Values_statusName;
	}

	public void setApp_Reference_Values_statusName(String App_Reference_Values_statusName) {
		this.App_Reference_Values_statusName = App_Reference_Values_statusName;
	}

	public String getApp_Reference_Values_option_Color() {
		return App_Reference_Values_option_Color;
	}

	public void setApp_Reference_Values_option_Color(String App_Reference_Values_option_Color) {
		this.App_Reference_Values_option_Color = App_Reference_Values_option_Color;
	}

	public String getApp_Reference_Values_description() {
		return App_Reference_Values_description;
	}

	public void setApp_Reference_Values_description(String App_Reference_Values_description) {
		this.App_Reference_Values_description = App_Reference_Values_description;
	}

	public Integer getApp_Reference_Values_no_of_slot() {
		return App_Reference_Values_no_of_slot;
	}

	public void setApp_Reference_Values_no_of_slot(Integer App_Reference_Values_no_of_slot) {
		this.App_Reference_Values_no_of_slot = App_Reference_Values_no_of_slot;
	}

	public Integer getApp_Reference_Values_reason_type() {
		return App_Reference_Values_reason_type;
	}

	public void setApp_Reference_Values_reason_type(Integer App_Reference_Values_reason_type) {
		this.App_Reference_Values_reason_type = App_Reference_Values_reason_type;
	}

	public String getApp_Reference_Values_duration() {
		return App_Reference_Values_duration;
	}

	public void setApp_Reference_Values_duration(String App_Reference_Values_duration) {
		this.App_Reference_Values_duration = App_Reference_Values_duration;
	}

	public Boolean getApp_Reference_Values_isActive() {
		return App_Reference_Values_isActive;
	}

	public void setApp_Reference_Values_isActive(Boolean App_Reference_Values_isActive) {
		this.App_Reference_Values_isActive = App_Reference_Values_isActive;
	}

	public Boolean getApp_Reference_Values_unknown1() {
		return App_Reference_Values_unknown1;
	}

	public void setApp_Reference_Values_unknown1(Boolean App_Reference_Values_unknown1) {
		this.App_Reference_Values_unknown1 = App_Reference_Values_unknown1;
	}

	public String getApp_Reference_Values_unknown2() {
		return App_Reference_Values_unknown2;
	}

	public void setApp_Reference_Values_unknown2(String App_Reference_Values_unknown2) {
		this.App_Reference_Values_unknown2 = App_Reference_Values_unknown2;
	}

	public String getApp_Reference_Values_unknown3() {
		return App_Reference_Values_unknown3;
	}

	public void setApp_Reference_Values_unknown3(String App_Reference_Values_unknown3) {
		this.App_Reference_Values_unknown3 = App_Reference_Values_unknown3;
	}

	@Column(name="App_Reference_Values_description")
	private String App_Reference_Values_description;

	@Column(name="App_Reference_Values_no_of_slot")
	private Integer App_Reference_Values_no_of_slot;

	@Column(name="App_Reference_Values_reason_type")
	private Integer App_Reference_Values_reason_type;

	@Column(name="App_Reference_Values_duration")
	private String App_Reference_Values_duration;

	@Column(name="App_Reference_Values_isActive")
	private Boolean App_Reference_Values_isActive;

	@Column(name="App_Reference_Values_unknown1")
	private Boolean App_Reference_Values_unknown1;

	@Column(name="App_Reference_Values_unknown2")
	private String App_Reference_Values_unknown2;

	@Column(name="App_Reference_Values_unknown3")
	private String App_Reference_Values_unknown3;
}