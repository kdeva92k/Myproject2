package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "meaningfuluse_settings")
public class MeaningfuluseSettings {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="meaningfuluse_settings_meaningfuluse_settings_id_seq")
	@SequenceGenerator(name = "meaningfuluse_settings_meaningfuluse_settings_id_seq", sequenceName="meaningfuluse_settings_meaningfuluse_settings_id_seq", allocationSize=1)
	@Column(name="meaningfuluse_settings_id")
	private Integer meaningfuluseSettingsId;

	@Column(name="meaningfuluse_settings_type")
	private String meaningfuluseSettingsType;

	@Column(name="meaningfuluse_settings_providerid")
	private Integer meaningfuluseSettingsProviderid;

	@Column(name="meaningfuluse_settings_startdate")
	private Date meaningfuluseSettingsStartdate;

	@Column(name="meaningfuluse_settings_enddate")
	private Date meaningfuluseSettingsEnddate;

	@Column(name="meaningfuluse_settings_stage")
	private Integer meaningfuluseSettingsStage;

	@Column(name="meaningfuluse_settings_option_id")
	private Integer meaningfuluseSettingsOptionId;

	@Column(name="meaningfuluse_settings_option_name")
	private String meaningfuluseSettingsOptionName;

	@Column(name="meaningfuluse_settings_option_value")
	private String meaningfuluseSettingsOptionValue;

	@Column(name="meaningfuluse_settings_option_query")
	private String meaningfuluseSettingsOptionQuery;

	public Integer getMeaningfuluseSettingsId() {
		return meaningfuluseSettingsId;
	}

	public void setMeaningfuluseSettingsId(Integer meaningfuluseSettingsId) {
		this.meaningfuluseSettingsId = meaningfuluseSettingsId;
	}

	public String getMeaningfuluseSettingsType() {
		return meaningfuluseSettingsType;
	}

	public void setMeaningfuluseSettingsType(String meaningfuluseSettingsType) {
		this.meaningfuluseSettingsType = meaningfuluseSettingsType;
	}

	public Integer getMeaningfuluseSettingsProviderid() {
		return meaningfuluseSettingsProviderid;
	}

	public void setMeaningfuluseSettingsProviderid(
			Integer meaningfuluseSettingsProviderid) {
		this.meaningfuluseSettingsProviderid = meaningfuluseSettingsProviderid;
	}

	public Date getMeaningfuluseSettingsStartdate() {
		return meaningfuluseSettingsStartdate;
	}

	public void setMeaningfuluseSettingsStartdate(
			Date meaningfuluseSettingsStartdate) {
		this.meaningfuluseSettingsStartdate = meaningfuluseSettingsStartdate;
	}

	public Date getMeaningfuluseSettingsEnddate() {
		return meaningfuluseSettingsEnddate;
	}

	public void setMeaningfuluseSettingsEnddate(Date meaningfuluseSettingsEnddate) {
		this.meaningfuluseSettingsEnddate = meaningfuluseSettingsEnddate;
	}

	public Integer getMeaningfuluseSettingsStage() {
		return meaningfuluseSettingsStage;
	}

	public void setMeaningfuluseSettingsStage(Integer meaningfuluseSettingsStage) {
		this.meaningfuluseSettingsStage = meaningfuluseSettingsStage;
	}

	public Integer getMeaningfuluseSettingsOptionId() {
		return meaningfuluseSettingsOptionId;
	}

	public void setMeaningfuluseSettingsOptionId(
			Integer meaningfuluseSettingsOptionId) {
		this.meaningfuluseSettingsOptionId = meaningfuluseSettingsOptionId;
	}

	public String getMeaningfuluseSettingsOptionName() {
		return meaningfuluseSettingsOptionName;
	}

	public void setMeaningfuluseSettingsOptionName(
			String meaningfuluseSettingsOptionName) {
		this.meaningfuluseSettingsOptionName = meaningfuluseSettingsOptionName;
	}

	public String getMeaningfuluseSettingsOptionValue() {
		return meaningfuluseSettingsOptionValue;
	}

	public void setMeaningfuluseSettingsOptionValue(
			String meaningfuluseSettingsOptionValue) {
		this.meaningfuluseSettingsOptionValue = meaningfuluseSettingsOptionValue;
	}

	public String getMeaningfuluseSettingsOptionQuery() {
		return meaningfuluseSettingsOptionQuery;
	}

	public void setMeaningfuluseSettingsOptionQuery(
			String meaningfuluseSettingsOptionQuery) {
		this.meaningfuluseSettingsOptionQuery = meaningfuluseSettingsOptionQuery;
	}
	
}