package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MeasureDetails {

	private String category;
	private String submissionMethod;
	private Date performanceStart;
	private Date performanceEnd;
	private List<HashMap<String, Object>> measurements ;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubmissionMethod() {
		return submissionMethod;
	}
	public void setSubmissionMethod(String submissionMethod) {
		this.submissionMethod = submissionMethod;
	}
	public Date getPerformanceStart() {
		return performanceStart;
	}
	public void setPerformanceStart(Date performanceStart) {
		this.performanceStart = performanceStart;
	}
	public Date getPerformanceEnd() {
		return performanceEnd;
	}
	public void setPerformanceEnd(Date performanceEnd) {
		this.performanceEnd = performanceEnd;
	}
	public List<HashMap<String, Object>> getMeasurements() {
		return measurements;
	}
	public void setMeasurements(List<HashMap<String, Object>> measurements) {
		this.measurements = measurements;
	}
	
	
	
}
