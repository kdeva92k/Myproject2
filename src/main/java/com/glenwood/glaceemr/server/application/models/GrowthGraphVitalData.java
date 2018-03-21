package com.glenwood.glaceemr.server.application.models;

public class GrowthGraphVitalData {

	private String headCircumference;
	private String height;
	private String weight;
	private String encounterDate;
	private Integer ageInDay;
	private Integer ageInMonth;
	private Integer ageInYear;
	
	public GrowthGraphVitalData(String headCircumference,String height,String weight,String encounterDate,Integer ageInMonth,Integer ageInDay,Integer ageInYear){
		
		this.headCircumference=headCircumference;
		this.height=height;
		this.weight=weight;
		this.encounterDate=encounterDate;
		this.ageInDay=ageInDay;
		this.ageInMonth=ageInMonth;
		this.ageInYear=ageInYear;
	}

	public String getHeadCircumference() {
		return headCircumference;
	}

	public void setHeadCircumference(String headCircumference) {
		this.headCircumference = headCircumference;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getEncounterDate() {
		return encounterDate;
	}

	public void setEncounterDate(String encounterDate) {
		this.encounterDate = encounterDate;
	}

	public Integer getAgeInDay() {
		return ageInDay;
	}

	public void setAgeInDay(Integer ageInDay) {
		this.ageInDay = ageInDay;
	}

	public Integer getAgeInMonth() {
		return ageInMonth;
	}

	public void setAgeInMonth(Integer ageInMonth) {
		this.ageInMonth = ageInMonth;
	}

	public Integer getAgeInYear() {
		return ageInYear;
	}

	public void setAgeInYear(Integer ageInYear) {
		this.ageInYear = ageInYear;
	}

		
}
