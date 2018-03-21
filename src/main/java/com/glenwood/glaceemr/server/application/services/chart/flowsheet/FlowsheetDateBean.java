package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

public class FlowsheetDateBean {
	private int year;
	private int month;
	private int day;
	
    public FlowsheetDateBean(int year,int month,int day){
    	this.year = year;
    	this.month= month;
    	this.day  = day;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

}
