package com.glenwood.glaceemr.server.application.services.chart.skintest;

import java.util.Map;

public class SkinTestShortcutBean {

	private int skinTestFormShortcutId;
	private String skinTestFormShortcutName;
	private Boolean skinTestFormShortcutPrickWhealNeeded;
	private Boolean skinTestFormShortcutPrickFlareNeeded;
	private Boolean skinTestFormShortcutPrickErythemaNeeded;
	private Boolean skinTestFormShortcutPrickPseudopodiaNeeded;
	private Boolean skinTestFormShortcutIntradermalWhealNeeded;
	private Boolean skinTestFormShortcutIntradermalFlareNeeded;
	private Boolean skinTestFormShortcutIntradermalErythemaNeeded;
	private Boolean skinTestFormShortcutIntradermalPseudopodiaNeeded;
	private String skinTestFormShortcutScoringNotes;
	private String skinTestFormShortcutNotes;
	private String skinTestFormShortcutdefaultreadvalue;
	/**
	 * 
	 */
	

	
	
	Map<Integer, ConcentrateGroupBean> concentrateGroupBean;

	public int getSkinTestFormShortcutId() {
		return skinTestFormShortcutId;
	}

	public void setSkinTestFormShortcutId(int skinTestFormShortcutId) {
		this.skinTestFormShortcutId = skinTestFormShortcutId;
	}

	public String getSkinTestFormShortcutName() {
		return skinTestFormShortcutName;
	}

	public void setSkinTestFormShortcutName(String skinTestFormShortcutName) {
		this.skinTestFormShortcutName = skinTestFormShortcutName;
	}

	public Boolean getSkinTestFormShortcutPrickWhealNeeded() {
		return skinTestFormShortcutPrickWhealNeeded;
	}

	public void setSkinTestFormShortcutPrickWhealNeeded(
			Boolean skinTestFormShortcutPrickWhealNeeded) {
		this.skinTestFormShortcutPrickWhealNeeded = skinTestFormShortcutPrickWhealNeeded;
	}

	public Boolean getSkinTestFormShortcutPrickFlareNeeded() {
		return skinTestFormShortcutPrickFlareNeeded;
	}

	public void setSkinTestFormShortcutPrickFlareNeeded(
			Boolean skinTestFormShortcutPrickFlareNeeded) {
		this.skinTestFormShortcutPrickFlareNeeded = skinTestFormShortcutPrickFlareNeeded;
	}

	public Boolean getSkinTestFormShortcutPrickErythemaNeeded() {
		return skinTestFormShortcutPrickErythemaNeeded;
	}

	public void setSkinTestFormShortcutPrickErythemaNeeded(
			Boolean skinTestFormShortcutPrickErythemaNeeded) {
		this.skinTestFormShortcutPrickErythemaNeeded = skinTestFormShortcutPrickErythemaNeeded;
	}

	public Boolean getSkinTestFormShortcutPrickPseudopodiaNeeded() {
		return skinTestFormShortcutPrickPseudopodiaNeeded;
	}

	public void setSkinTestFormShortcutPrickPseudopodiaNeeded(
			Boolean skinTestFormShortcutPrickPseudopodiaNeeded) {
		this.skinTestFormShortcutPrickPseudopodiaNeeded = skinTestFormShortcutPrickPseudopodiaNeeded;
	}

	public Boolean getSkinTestFormShortcutIntradermalWhealNeeded() {
		return skinTestFormShortcutIntradermalWhealNeeded;
	}

	public void setSkinTestFormShortcutIntradermalWhealNeeded(
			Boolean skinTestFormShortcutIntradermalWhealNeeded) {
		this.skinTestFormShortcutIntradermalWhealNeeded = skinTestFormShortcutIntradermalWhealNeeded;
	}

	public Boolean getSkinTestFormShortcutIntradermalFlareNeeded() {
		return skinTestFormShortcutIntradermalFlareNeeded;
	}

	public void setSkinTestFormShortcutIntradermalFlareNeeded(
			Boolean skinTestFormShortcutIntradermalFlareNeeded) {
		this.skinTestFormShortcutIntradermalFlareNeeded = skinTestFormShortcutIntradermalFlareNeeded;
	}

	public Boolean getSkinTestFormShortcutIntradermalErythemaNeeded() {
		return skinTestFormShortcutIntradermalErythemaNeeded;
	}

	public void setSkinTestFormShortcutIntradermalErythemaNeeded(
			Boolean skinTestFormShortcutIntradermalErythemaNeeded) {
		this.skinTestFormShortcutIntradermalErythemaNeeded = skinTestFormShortcutIntradermalErythemaNeeded;
	}

	public Boolean getSkinTestFormShortcutIntradermalPseudopodiaNeeded() {
		return skinTestFormShortcutIntradermalPseudopodiaNeeded;
	}

	public void setSkinTestFormShortcutIntradermalPseudopodiaNeeded(
			Boolean skinTestFormShortcutIntradermalPseudopodiaNeeded) {
		this.skinTestFormShortcutIntradermalPseudopodiaNeeded = skinTestFormShortcutIntradermalPseudopodiaNeeded;
	}

	public String getSkinTestFormShortcutScoringNotes() {
		return skinTestFormShortcutScoringNotes;
	}

	public void setSkinTestFormShortcutScoringNotes(
			String skinTestFormShortcutScoringNotes) {
		this.skinTestFormShortcutScoringNotes = skinTestFormShortcutScoringNotes;
	}

	public String getSkinTestFormShortcutNotes() {
		return skinTestFormShortcutNotes;
	}

	public void setSkinTestFormShortcutNotes(String skinTestFormShortcutNotes) {
		this.skinTestFormShortcutNotes = skinTestFormShortcutNotes;
	}

	public Map<Integer, ConcentrateGroupBean> getConcentrateGroupBean() {
		return concentrateGroupBean;
	}

	public void setConcentrateGroupBean(Map<Integer, ConcentrateGroupBean> concentrateGroupBean) {
		this.concentrateGroupBean = concentrateGroupBean;
	}
	@Override
	public String toString() {
		return "sheet name: "+skinTestFormShortcutName+"Categories: "+concentrateGroupBean.toString();
	}

	public String getSkinTestFormShortcutdefaultreadvalue() {
		return skinTestFormShortcutdefaultreadvalue;
	}

	public void setSkinTestFormShortcutdefaultreadvalue(
			String skinTestFormShortcutdefaultreadvalue) {
		this.skinTestFormShortcutdefaultreadvalue = skinTestFormShortcutdefaultreadvalue;
	}
	
}