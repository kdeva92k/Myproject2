package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class PortalImmunizationHistoryBean {

	List<LabEntries> labDescImmunizationHistoryList;
	
	List<VaccineReport> vaccineReportImmunizationHistoryList;

	public List<LabEntries> getLabDescImmunizationHistoryList() {
		return labDescImmunizationHistoryList;
	}

	public void setLabDescImmunizationHistoryList(
			List<LabEntries> labDescImmunizationHistoryList) {
		this.labDescImmunizationHistoryList = labDescImmunizationHistoryList;
	}

	public List<VaccineReport> getVaccineReportImmunizationHistoryList() {
		return vaccineReportImmunizationHistoryList;
	}

	public void setVaccineReportImmunizationHistoryList(
			List<VaccineReport> vaccineReportImmunizationHistoryList) {
		this.vaccineReportImmunizationHistoryList = vaccineReportImmunizationHistoryList;
	}
	
}
