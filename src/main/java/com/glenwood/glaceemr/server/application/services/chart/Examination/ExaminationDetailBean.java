package com.glenwood.glaceemr.server.application.services.chart.Examination;

import java.util.LinkedHashMap;

public class ExaminationDetailBean {
	private int detailId;
	private String detailName;
	private String detailDisplayName;
	private String detailPrinttext;
	LinkedHashMap<Integer, ExaminationDetailOptionBean> detailOptions;

	public int getDetailId() {
		return detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	public String getDetailName() {
		return detailName;
	}
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}
	public String getDetailPrinttext() {
		return detailPrinttext;
	}
	public void setDetailPrinttext(String detailPrinttext) {
		this.detailPrinttext = detailPrinttext;
	}
	public LinkedHashMap<Integer, ExaminationDetailOptionBean> getDetailOptions() {
		return detailOptions;
	}
	public void setDetailOptions(
			LinkedHashMap<Integer, ExaminationDetailOptionBean> detailOptions) {
		this.detailOptions = detailOptions;
	}
	public String getDetailDisplayName() {
		return detailDisplayName;
	}
	public void setDetailDisplayName(String detailDisplayName) {
		this.detailDisplayName = detailDisplayName;
	}

	
}
