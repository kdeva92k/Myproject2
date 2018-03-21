package com.glenwood.glaceemr.server.application.services.chart.MIPS;

import java.util.Date;

public class VaccReportBean {

	int vaccineReportVaccineId;
	int vaccineReportChartId;
	Date vaccineReportGivenDate;
	String labDescriptionTestDesc;
	String labDescriptionCvx;

	public VaccReportBean(int vaccineReportVaccineId,int vaccineReportChartId,Date vaccineReportGivenDate,String labDescriptionTestDesc,String labDescriptionCvx){
		super();
		this.vaccineReportVaccineId=vaccineReportVaccineId;
		this.vaccineReportChartId=vaccineReportChartId;
		this.vaccineReportGivenDate=vaccineReportGivenDate;
		this.labDescriptionTestDesc=labDescriptionTestDesc;
		this.labDescriptionCvx=labDescriptionCvx;
	}

	public String getLabDescriptionCvx() {
		return labDescriptionCvx;
	}

	public void setLabDescriptionCvx(String labDescriptionCvx) {
		this.labDescriptionCvx = labDescriptionCvx;
	}

	public int getVaccineReportVaccineId() {
		return vaccineReportVaccineId;
	}

	public void setVaccineReportVaccineId(int vaccineReportVaccineId) {
		this.vaccineReportVaccineId = vaccineReportVaccineId;
	}

	public int getVaccineReportChartId() {
		return vaccineReportChartId;
	}

	public void setVaccineReportChartId(int vaccineReportChartId) {
		this.vaccineReportChartId = vaccineReportChartId;
	}

	public Date getVaccineReportGivenDate() {
		return vaccineReportGivenDate;
	}

	public void setVaccineReportGivenDate(Date vaccineReportGivenDate) {
		this.vaccineReportGivenDate = vaccineReportGivenDate;
	}

	public String getLabDescriptionTestDesc() {
		return labDescriptionTestDesc;
	}

	public void setLabDescriptionTestDesc(String labDescriptionTestDesc) {
		this.labDescriptionTestDesc = labDescriptionTestDesc;
	}

}
