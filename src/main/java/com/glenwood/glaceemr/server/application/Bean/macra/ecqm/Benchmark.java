package com.glenwood.glaceemr.server.application.Bean.macra.ecqm;

import java.util.ArrayList;

public class Benchmark {
	
	private String submissionMethod;
	private boolean isBenchmarkAvailable;
	private ArrayList<Decile> decileList;
	private boolean isToppedOut;
	
	public String getSubmissionMethod() {
		return submissionMethod;
	}
	
	public void setSubmissionMethod(String submissionMethod) {
		this.submissionMethod = submissionMethod;
	}
	
	public boolean isBenchmarkAvailable() {
		return isBenchmarkAvailable;
	}
	
	public void setBenchmarkAvailable(boolean isBenchmarkAvailable) {
		this.isBenchmarkAvailable = isBenchmarkAvailable;
	}
	
	public ArrayList<Decile> getDecileList() {
		return decileList;
	}
	
	public void setDecileList(ArrayList<Decile> decileList) {
		this.decileList = decileList;
	}
	
	public boolean isToppedOut() {
		return isToppedOut;
	}
	
	public void setToppedOut(boolean isToppedOut) {
		this.isToppedOut = isToppedOut;
	}
	
}