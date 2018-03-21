package com.glenwood.glaceemr.server.application.services.chart.Examination;


import java.util.LinkedHashMap;

import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalElementOptionBean;


public class ExaminationDetailOptionBean {
	private int optionId;
	private String optionName;
	private String optionDisplayName;
	private String optionGWId;
	private String optionPrintText;
	LinkedHashMap<Integer, ClinicalElementOptionBean> clinicalElementOption;

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getOptionGWId() {
		return optionGWId;
	}

	public void setOptionGWId(String optionGWId) {
		this.optionGWId = optionGWId;
	}

	public String getOptionPrintText() {
		return optionPrintText;
	}

	public void setOptionPrintText(String optionPrintText) {
		this.optionPrintText = optionPrintText;
	}

	public String getOptionDisplayName() {
		return optionDisplayName;
	}

	public void setOptionDisplayName(String optionDisplayName) {
		this.optionDisplayName = optionDisplayName;
	}

	public LinkedHashMap<Integer, ClinicalElementOptionBean> getClinicalElementOption() {
		return clinicalElementOption;
	}

	public void setClinicalElementOption(
			LinkedHashMap<Integer, ClinicalElementOptionBean> clinicalElementOption) {
		this.clinicalElementOption = clinicalElementOption;
	}


}
