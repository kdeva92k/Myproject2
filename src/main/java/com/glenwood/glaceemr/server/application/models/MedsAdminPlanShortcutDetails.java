package com.glenwood.glaceemr.server.application.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "meds_admin_plan_shortcut_details")
public class MedsAdminPlanShortcutDetails {

	@Id
	@Column(name="meds_admin_plan_shortcut_details_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="meds_admin_plan_shortcut_details_id")
	@SequenceGenerator(name="meds_admin_plan_shortcut_details_id",sequenceName="meds_admin_plan_shortcut_details_id",allocationSize=1)
	private Integer medsAdminPlanShortcutDetailsId;

	@Column(name="meds_admin_plan_shortcut_details_map_id")
	private Integer medsAdminPlanShortcutDetailsMapId;

	@Column(name="meds_admin_plan_shortcut_plan_time")
	private String medsAdminPlanShortcutPlanTime;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="meds_admin_plan_shortcut_details_map_id",referencedColumnName="meds_admin_plan_shortcut_id",insertable=false, updatable=false)
	@JsonBackReference
	MedsAdminPlanShortcut medsAdminPlanShortcut;

	public Integer getMedsAdminPlanShortcutDetailsId() {
		return medsAdminPlanShortcutDetailsId;
	}

	public void setMedsAdminPlanShortcutDetailsId(
			Integer medsAdminPlanShortcutDetailsId) {
		this.medsAdminPlanShortcutDetailsId = medsAdminPlanShortcutDetailsId;
	}

	public Integer getMedsAdminPlanShortcutDetailsMapId() {
		return medsAdminPlanShortcutDetailsMapId;
	}

	public void setMedsAdminPlanShortcutDetailsMapId(
			Integer medsAdminPlanShortcutDetailsMapId) {
		this.medsAdminPlanShortcutDetailsMapId = medsAdminPlanShortcutDetailsMapId;
	}

	public String getMedsAdminPlanShortcutPlanTime() {
		return medsAdminPlanShortcutPlanTime;
	}

	public void setMedsAdminPlanShortcutPlanTime(
			String medsAdminPlanShortcutPlanTime) {
		this.medsAdminPlanShortcutPlanTime = medsAdminPlanShortcutPlanTime;
	}

	public MedsAdminPlanShortcut getMedsAdminPlanShortcut() {
		return medsAdminPlanShortcut;
	}

	public void setMedsAdminPlanShortcut(MedsAdminPlanShortcut medsAdminPlanShortcut) {
		this.medsAdminPlanShortcut = medsAdminPlanShortcut;
	}
}