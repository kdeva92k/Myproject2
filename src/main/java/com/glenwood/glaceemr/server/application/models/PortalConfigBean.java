package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class PortalConfigBean {

	int patientId;

	int chartId;

	boolean havingNewForms;

	boolean havingNewIntakeForms;

	boolean havingNewConsentForms;

	boolean havingAppointmentToday;

	List<PortalSchedulerAppointmentBean> todaysApptList;

	boolean havingPaymentPending;

	PortalPatientPaymentsSummary portalPatientPaymentsSummary;
	
	String totalPatientDue;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getChartId() {
		return chartId;
	}

	public void setChartId(int chartId) {
		this.chartId = chartId;
	}

	public boolean isHavingNewForms() {
		return havingNewForms;
	}

	public void setHavingNewForms(boolean havingNewForms) {
		this.havingNewForms = havingNewForms;
	}

	public boolean isHavingNewIntakeForms() {
		return havingNewIntakeForms;
	}

	public void setHavingNewIntakeForms(boolean havingNewIntakeForms) {
		this.havingNewIntakeForms = havingNewIntakeForms;
	}

	public boolean isHavingNewConsentForms() {
		return havingNewConsentForms;
	}

	public void setHavingNewConsentForms(boolean havingNewConsentForms) {
		this.havingNewConsentForms = havingNewConsentForms;
	}

	public boolean isHavingAppointmentToday() {
		return havingAppointmentToday;
	}

	public void setHavingAppointmentToday(boolean havingAppointmentToday) {
		this.havingAppointmentToday = havingAppointmentToday;
	}

	public List<PortalSchedulerAppointmentBean> getTodaysApptList() {
		return todaysApptList;
	}

	public void setTodaysApptList(List<PortalSchedulerAppointmentBean> todaysApptList) {
		this.todaysApptList = todaysApptList;
	}

	public boolean isHavingPaymentPending() {
		return havingPaymentPending;
	}

	public void setHavingPaymentPending(boolean havingPaymentPending) {
		this.havingPaymentPending = havingPaymentPending;
	}

	public PortalPatientPaymentsSummary getPortalPatientPaymentsSummary() {
		return portalPatientPaymentsSummary;
	}

	public void setPortalPatientPaymentsSummary(
			PortalPatientPaymentsSummary portalPatientPaymentsSummary) {
		this.portalPatientPaymentsSummary = portalPatientPaymentsSummary;
	}

	public String getTotalPatientDue() {
		return totalPatientDue;
	}

	public void setTotalPatientDue(String totalPatientDue) {
		this.totalPatientDue = totalPatientDue;
	}
	
	
	
}
