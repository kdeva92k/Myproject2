package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

public class FS_LabParameterBean {
	private String paramName;
	private String paramValue;
	private String units;
	private String perfOn;
	private String paramCode;
	private String paramCodeSystem;
	private String gender;
	private String paramId;
	private boolean recentlyPerf;
	private String due;
	private String dueCriteria;
	private Integer groupId;
	
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		if(units==null)
			units="";
		this.units = units;
	}

	public String getPerfOn() {
		return perfOn;
	}
	public void setPerfOn(String perfOn) {
		this.perfOn = perfOn;
	}
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public String getParamCodeSystem() {
		return paramCodeSystem;
	}
	public void setParamCodeSystem(String paramCodeSystem) {
		this.paramCodeSystem = paramCodeSystem;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isRecentlyPerf() {
		return recentlyPerf;
	}

	public void setRecentlyPerf(boolean recentlyPerf) {
		this.recentlyPerf = recentlyPerf;
	}

	public String getDue() {
		return due;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public String getDueCriteria() {
		return dueCriteria;
	}

	public void setDueCriteria(String dueCriteria) {
		this.dueCriteria = dueCriteria;
	}

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
}
