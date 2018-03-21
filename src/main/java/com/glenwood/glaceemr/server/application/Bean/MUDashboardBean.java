package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;
import java.util.List;

public class MUDashboardBean {

	private int reportingYear;
	private String message;
	private Date startDate;
	private Date endDate;
	
	private int ecqmPoints;
	private int aciPoints;
	
	private List<MIPSPerformanceBean> ecqmMeasures;
	private List<MIPSPerformanceBean> aciMeasures;

	public int getEcqmPoints() {
		return ecqmPoints;
	}
	
	public void setEcqmPoints(int ecqmPoints) {
		this.ecqmPoints = ecqmPoints;
	}
	
	public int getAciPoints() {
		return aciPoints;
	}
	
	public void setAciPoints(int aciPoints) {
		this.aciPoints = aciPoints;
	}
	
	public List<MIPSPerformanceBean> getEcqmMeasures() {
		return ecqmMeasures;
	}
	
	public void setEcqmMeasures(List<MIPSPerformanceBean> ecqmMeasures) {
		this.ecqmMeasures = ecqmMeasures;
	}
	
	public List<MIPSPerformanceBean> getAciMeasures() {
		return aciMeasures;
	}
	
	public void setAciMeasures(List<MIPSPerformanceBean> aciMeasures) {
		this.aciMeasures = aciMeasures;
	}
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getReportingYear() {
		return reportingYear;
	}

	public void setReportingYear(int reportingYear) {
		this.reportingYear = reportingYear;
	}
	
}