package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;

public class MUPerformanceBean {

	String providerName;
	String npi;
	Date startDate;
	Date endDate;
	String submissionMethod;
	String cmsId;
	String title;
	String measureId;
	int criteria;
	int reportingYear;
	long ippCount;
	long denominatorCount;
	long denominatorExclusionCount;
	long numeratorCount;
	long numeratorExclusionCount;
	long denominatorExceptionCount;
	double performanceRate;
	double reportingRate;
	int points;
	
	public MUPerformanceBean(){
		
	}
	
	public MUPerformanceBean(String providerName, String measureId, int criteria,int reportingYear,
			int ippCount, int denominatorCount,
			int denominatorExclusionCount, int numeratorCount,
			int numeratorExclusionCount, int denominatorExceptionCount, double performanceRate,
			double reportingRate, double points, String npi, Date startDate, Date endDate) {
		
		this.measureId = measureId;
		this.criteria = criteria;
		this.reportingYear = reportingYear;
		this.ippCount = ippCount;
		this.denominatorCount = denominatorCount;
		this.denominatorExclusionCount = denominatorExclusionCount;
		this.numeratorCount = numeratorCount;
		this.numeratorExclusionCount = numeratorExclusionCount;
		this.denominatorExceptionCount = denominatorExceptionCount;
		this.performanceRate = performanceRate;
		this.reportingRate = reportingRate;
		this.points = (int)points;
		this.npi = npi;
		this.providerName = providerName;
		this.startDate = startDate;
		this.endDate = endDate;
		
	}

	public String getCmsId() {
		return cmsId;
	}

	public void setCmsId(String cmsId) {
		this.cmsId = cmsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMeasureId() {
		return measureId;
	}

	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}

	public String getSubmissionMethod() {
		return submissionMethod;
	}

	public void setSubmissionMethod(String submissionMethod) {
		this.submissionMethod = submissionMethod;
	}

	public int getCriteria() {
		return criteria;
	}

	public void setCriteria(int criteria) {
		this.criteria = criteria;
	}

	public int getReportingYear() {
		return reportingYear;
	}

	public void setReportingYear(int reportingYear) {
		this.reportingYear = reportingYear;
	}

	public long getIppCount() {
		return ippCount;
	}

	public void setIppCount(long ippCount) {
		this.ippCount = ippCount;
	}

	public long getDenominatorCount() {
		return denominatorCount;
	}

	public void setDenominatorCount(long denominatorCount) {
		this.denominatorCount = denominatorCount;
	}

	public long getDenominatorExclusionCount() {
		return denominatorExclusionCount;
	}

	public void setDenominatorExclusionCount(long denominatorExclusionCount) {
		this.denominatorExclusionCount = denominatorExclusionCount;
	}

	public long getNumeratorCount() {
		return numeratorCount;
	}

	public void setNumeratorCount(long numeratorCount) {
		this.numeratorCount = numeratorCount;
	}

	public long getNumeratorExclusionCount() {
		return numeratorExclusionCount;
	}

	public void setNumeratorExclusionCount(long numeratorExclusionCount) {
		this.numeratorExclusionCount = numeratorExclusionCount;
	}

	public long getDenominatorExceptionCount() {
		return denominatorExceptionCount;
	}

	public void setDenominatorExceptionCount(long denominatorExceptionCount) {
		this.denominatorExceptionCount = denominatorExceptionCount;
	}

	public double getPerformanceRate() {
		return performanceRate;
	}

	public void setPerformanceRate(double performanceRate) {
		this.performanceRate = performanceRate;
	}

	public double getReportingRate() {
		return reportingRate;
	}

	public void setReportingRate(double reportingRate) {
		this.reportingRate = reportingRate;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getNpi() {
		return npi;
	}

	public void setNpi(String npi) {
		this.npi = npi;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
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

}
