package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "focal_shortcut")
public class FocalShortcut {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="focal_shortcut_focal_shortcut_id_seq")
	@SequenceGenerator(name ="focal_shortcut_focal_shortcut_id_seq", sequenceName="focal_shortcut_focal_shortcut_id_seq", allocationSize=1)
	@Column(name="focal_shortcut_id")
	private Integer focalShortcutId;

	@Column(name="focal_shortcut_name")
	private String focalShortcutName;

	@Column(name="focal_shortcut_tabid")
	private Integer focalShortcutTabid;

	@Column(name="focal_shortcut_isactive")
	private Boolean focalShortcutIsactive;

	@Column(name="focal_shortcut_description")
	private String focalShortcutDescription;

	public Integer getFocalShortcutId() {
		return focalShortcutId;
	}

	public void setFocalShortcutId(Integer focalShortcutId) {
		this.focalShortcutId = focalShortcutId;
	}

	public String getFocalShortcutName() {
		return focalShortcutName;
	}

	public void setFocalShortcutName(String focalShortcutName) {
		this.focalShortcutName = focalShortcutName;
	}

	public Integer getFocalShortcutTabid() {
		return focalShortcutTabid;
	}

	public void setFocalShortcutTabid(Integer focalShortcutTabid) {
		this.focalShortcutTabid = focalShortcutTabid;
	}

	public Boolean getFocalShortcutIsactive() {
		return focalShortcutIsactive;
	}

	public void setFocalShortcutIsactive(Boolean focalShortcutIsactive) {
		this.focalShortcutIsactive = focalShortcutIsactive;
	}

	public String getFocalShortcutDescription() {
		return focalShortcutDescription;
	}

	public void setFocalShortcutDescription(String focalShortcutDescription) {
		this.focalShortcutDescription = focalShortcutDescription;
	}
	
	
}