package com.glenwood.glaceemr.server.application.Bean;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

public class MUAttestationBean {

	int reportingYear;
	String lastModifiedDate;
	HashMap<Integer, HashMap<String, List<MUPerformanceBean>>> reportingStatus;

	String objectiveMeasureId;
	boolean objectiveStauts;
	
	public MUAttestationBean(String objectiveMeasureId, boolean objectiveStauts) {
		super();
		this.objectiveMeasureId = objectiveMeasureId;
		this.objectiveStauts = objectiveStauts;
	}

	public MUAttestationBean(){
	}

	public String getObjectiveMeasureId() {
		return objectiveMeasureId;
	}
	
	public void setObjectiveMeasureId(String objectiveMeasureId) {
		this.objectiveMeasureId = objectiveMeasureId;
	}

	public boolean isObjectiveStauts() {
		return objectiveStauts;
	}

	public void setObjectiveStauts(boolean objectiveStauts) {
		this.objectiveStauts = objectiveStauts;
	}

	public int getReportingYear() {
		return reportingYear;
	}
	
	public void setReportingYear(int reportingYear) {
		this.reportingYear = reportingYear;
	}
	
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public HashMap<Integer, HashMap<String, List<MUPerformanceBean>>> getReportingStatus() {
		return reportingStatus;
	}
	
	public void setReportingStatus(
			HashMap<Integer, HashMap<String, List<MUPerformanceBean>>> reportingStatus) {
		this.reportingStatus = reportingStatus;
	}
	
}
