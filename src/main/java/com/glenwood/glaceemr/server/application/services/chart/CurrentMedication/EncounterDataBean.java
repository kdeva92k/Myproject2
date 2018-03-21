package com.glenwood.glaceemr.server.application.services.chart.CurrentMedication;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.ReceiptDetail;

public class EncounterDataBean {

	List<Encounter> encData;
	PatientRegistration copay;
	List<ReceiptDetail> copayment;
	
	public EncounterDataBean(List<Encounter> encData,PatientRegistration copay, List<ReceiptDetail> copayment) {
	this.encData=encData;
	this.copay=copay;
	this.copayment=copayment;
	
	}

	public List<Encounter> getEncData() {
		return encData;
	}

	public void setEncData(List<Encounter> encData) {
		this.encData = encData;
	}

	public PatientRegistration getCopay() {
		return copay;
	}

	public void setCopay(PatientRegistration copay) {
		this.copay = copay;
	}

	public List<ReceiptDetail> getCopayment() {
		return copayment;
	}

	public void setCopayment(List<ReceiptDetail> copayment) {
		this.copayment = copayment;
	}

}
