package com.glenwood.glaceemr.server.application.models;

public class PortalPatientPaymentsSummary {

	private int patientId;
	
	private Double patientBalance;
	
	private Double depositBalance;
	
	private Double insBalance;
	
	private Double visitCopay;
	
	
	public PortalPatientPaymentsSummary(int patientId, Double patientBalance, Double depositBalance, Double insBalance, Double visitCopay) {
		super();
		
		this.patientId = patientId;
		
		if(patientBalance!=null)
		this.patientBalance = patientBalance;
		if(depositBalance!=null)
		this.depositBalance = depositBalance;
		if(insBalance!=null)
		this.insBalance = insBalance;
		if(visitCopay!=null)
		this.visitCopay = visitCopay;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public Double getPatientBalance() {
		return patientBalance;
	}

	public void setPatientBalance(Double patientBalance) {
		this.patientBalance = patientBalance;
	}

	public Double getDepositBalance() {
		return depositBalance;
	}

	public void setDepositBalance(Double depositBalance) {
		this.depositBalance = depositBalance;
	}

	public Double getInsBalance() {
		return insBalance;
	}

	public void setInsBalance(Double insBalance) {
		this.insBalance = insBalance;
	}

	public Double getVisitCopay() {
		return visitCopay;
	}

	public void setVisitCopay(Double visitCopay) {
		this.visitCopay = visitCopay;
	}

	
	
}
