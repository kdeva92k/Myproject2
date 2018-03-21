package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

import java.util.List;

public class HmrRuleBean {
	List<HmrBean> hmrData;
	List<HmrBean> hmrReferences;
	List<HmrBean> hmrCpt;
	public List<HmrBean> getHmrData() {
		return hmrData;
	}
	public void setHmrData(List<HmrBean> hmrData) {
		this.hmrData = hmrData;
	}
	public List<HmrBean> getHmrReferences() {
		return hmrReferences;
	}
	public void setHmrReferences(List<HmrBean> hmrReferences) {
		this.hmrReferences = hmrReferences;
	}
	public List<HmrBean> getHmrCpt() {
		return hmrCpt;
	}
	public void setHmrCpt(List<HmrBean> hmrCpt) {
		this.hmrCpt = hmrCpt;
	}
}
