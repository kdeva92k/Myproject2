package com.glenwood.glaceemr.server.application.services.chart.skintest;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.ConcentrateGroup;

public class SkinTestSheetsDTO {
	
	List<SkinTestShortcutBean> shortcutBeans;
	List<ConcentrateGroup> totalAllergensData;
	public List<SkinTestShortcutBean> getShortcutBeans() {
		return shortcutBeans;
	}
	public void setShortcutBeans(List<SkinTestShortcutBean> shortcutBeans) {
		this.shortcutBeans = shortcutBeans;
	}
	public List<ConcentrateGroup> getTotalAllergensData() {
		return totalAllergensData;
	}
	public void setTotalAllergensData(List<ConcentrateGroup> totalAllergensData) {
		this.totalAllergensData = totalAllergensData;
	}
}
