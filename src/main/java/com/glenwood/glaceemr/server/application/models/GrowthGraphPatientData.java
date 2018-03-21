package com.glenwood.glaceemr.server.application.models;

public class GrowthGraphPatientData {
	private String accountno;
	private String patientname;
	private Integer patientid;
	private Integer gender;
	private Integer ageInYear;
	private Integer isNewGraph;
	
	public GrowthGraphPatientData(String accountno,String patientname,Integer patientid,Integer gender,Integer ageInYear,Integer isNewGraph){
		this.accountno=accountno;
		this.patientname=patientname;
		this.patientid=patientid;
		this.gender=gender;
		this.ageInYear=ageInYear;
		this.isNewGraph=isNewGraph;
	}

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public Integer getPatientid() {
		return patientid;
	}

	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getAgeInYear() {
		return ageInYear;
	}

	public void setAgeInYear(Integer ageInYear) {
		this.ageInYear = ageInYear;
	}

	public Integer getIsNewGraph() {
		return isNewGraph;
	}

	public void setIsNewGraph(Integer isNewGraph) {
		this.isNewGraph = isNewGraph;
	}
	

}
