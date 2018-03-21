package com.glenwood.glaceemr.server.application.services.referral;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.Referral;

public class ReferralListBean {
	
	List<Referral> referralList;
	
	String guarantorName;

	public ReferralListBean(List<Referral> referralList, String guarantorName) {
		this.referralList=referralList;
		this.guarantorName=guarantorName;
	}
	
	public List<Referral> getReferralList() {
		return referralList;
	}

	public void setReferralList(List<Referral> referralList) {
		this.referralList = referralList;
	}

	public String getGuarantorName() {
		return guarantorName;
	}

	public void setGuarantorName(String guarantorName) {
		this.guarantorName = guarantorName;
	}
	
}
