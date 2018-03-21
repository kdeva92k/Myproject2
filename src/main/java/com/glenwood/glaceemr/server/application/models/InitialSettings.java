package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "initial_settings")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InitialSettings  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="initial_settings_option_id")
	private Integer initialSettingsOptionId;

	@Column(name="initial_settings_option_type")
	private Short initialSettingsOptionType;

	@Column(name="initial_settings_option_name")
	private String initialSettingsOptionName;

	@Column(name="initial_settings_option_value")
	private String initialSettingsOptionValue;

	@Column(name="initial_settings_table_row_no")
	private Integer initialSettingsTableRowNo;

	@Column(name="initial_settings_table_col_no")
	private Integer initialSettingsTableColNo;

	@Column(name="initial_settings_table_colspan")
	private Integer initialSettingsTableColspan;

	@Column(name="initial_settings_textbox_size")
	private Integer initialSettingsTextboxSize;

	@Column(name="initial_settings_view_type")
	private String initialSettingsViewType;

	@Column(name="initial_settings_option_source")
	private String initialSettingsOptionSource;

	@Column(name="initial_settings_visible")
	private Boolean initialSettingsVisible;

	@Column(name="initial_settings_option_name_desc")
	private String initialSettingsOptionNameDesc;
	
	public String getInitialSettingsOptionNameDesc() {
		return initialSettingsOptionNameDesc;
	}

	public void setInitialSettingsOptionNameDesc(
			String initialSettingsOptionNameDesc) {
		this.initialSettingsOptionNameDesc = initialSettingsOptionNameDesc;
	}

	public Integer getInitialSettingsOptionId() {
		return initialSettingsOptionId;
	}

	public void setInitialSettingsOptionId(Integer initialSettingsOptionId) {
		this.initialSettingsOptionId = initialSettingsOptionId;
	}

	public Short getInitialSettingsOptionType() {
		return initialSettingsOptionType;
	}

	public void setInitialSettingsOptionType(Short initialSettingsOptionType) {
		this.initialSettingsOptionType = initialSettingsOptionType;
	}

	public String getInitialSettingsOptionName() {
		return initialSettingsOptionName;
	}

	public void setInitialSettingsOptionName(String initialSettingsOptionName) {
		this.initialSettingsOptionName = initialSettingsOptionName;
	}

	public String getInitialSettingsOptionValue() {
		return initialSettingsOptionValue;
	}

	public void setInitialSettingsOptionValue(String initialSettingsOptionValue) {
		this.initialSettingsOptionValue = initialSettingsOptionValue;
	}

	public Integer getInitialSettingsTableRowNo() {
		return initialSettingsTableRowNo;
	}

	public void setInitialSettingsTableRowNo(Integer initialSettingsTableRowNo) {
		this.initialSettingsTableRowNo = initialSettingsTableRowNo;
	}

	public Integer getInitialSettingsTableColNo() {
		return initialSettingsTableColNo;
	}

	public void setInitialSettingsTableColNo(Integer initialSettingsTableColNo) {
		this.initialSettingsTableColNo = initialSettingsTableColNo;
	}

	public Integer getInitialSettingsTableColspan() {
		return initialSettingsTableColspan;
	}

	public void setInitialSettingsTableColspan(Integer initialSettingsTableColspan) {
		this.initialSettingsTableColspan = initialSettingsTableColspan;
	}

	public Integer getInitialSettingsTextboxSize() {
		return initialSettingsTextboxSize;
	}

	public void setInitialSettingsTextboxSize(Integer initialSettingsTextboxSize) {
		this.initialSettingsTextboxSize = initialSettingsTextboxSize;
	}

	public String getInitialSettingsViewType() {
		return initialSettingsViewType;
	}

	public void setInitialSettingsViewType(String initialSettingsViewType) {
		this.initialSettingsViewType = initialSettingsViewType;
	}

	public String getInitialSettingsOptionSource() {
		return initialSettingsOptionSource;
	}

	public void setInitialSettingsOptionSource(String initialSettingsOptionSource) {
		this.initialSettingsOptionSource = initialSettingsOptionSource;
	}

	public Boolean getInitialSettingsVisible() {
		return initialSettingsVisible;
	}

	public void setInitialSettingsVisible(Boolean initialSettingsVisible) {
		this.initialSettingsVisible = initialSettingsVisible;
	}
	
}