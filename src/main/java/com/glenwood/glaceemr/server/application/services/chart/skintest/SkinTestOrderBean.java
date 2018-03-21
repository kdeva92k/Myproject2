package com.glenwood.glaceemr.server.application.services.chart.skintest;

import com.glenwood.glaceemr.server.application.models.SkinTestOrder;

public class SkinTestOrderBean {
	SkinTestOrder skinTestOrder;
	SkinTestOrderShortCutBean orderShortcutBean;
	
	public SkinTestOrder getSkinTestOrder() {
		return skinTestOrder;
	}
	public void setSkinTestOrder(SkinTestOrder skinTestOrder) {
		this.skinTestOrder = skinTestOrder;
	}
	public SkinTestOrderShortCutBean getShortcutBean() {
		return orderShortcutBean;
	}
	public void setShortcutBean(SkinTestOrderShortCutBean orderShortcutBean) {
		this.orderShortcutBean = orderShortcutBean;
	}
}
