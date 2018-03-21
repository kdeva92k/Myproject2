package com.glenwood.glaceemr.server.application.services.referral;

public class ReferralDiagnosisBean {
	
	String dxCode;
	
	String dxDescription;

	public ReferralDiagnosisBean(String dxCode, String dxDescription) {
		this.dxCode = dxCode;
		this.dxDescription = dxDescription;
	}

	public String getDxCode() {
		return dxCode;
	}

	public void setDxCode(String dxCode) {
		this.dxCode = dxCode;
	}

	public String getDxDescription() {
		return dxDescription;
	}

	public void setDxDescription(String dxDescription) {
		this.dxDescription = dxDescription;
	}
	
}
