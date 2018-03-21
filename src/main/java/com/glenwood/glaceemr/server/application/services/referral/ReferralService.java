package com.glenwood.glaceemr.server.application.services.referral;

import java.util.List;

import org.json.JSONException;

import com.glenwood.glaceemr.server.application.models.Referral;

/**
 * Service Interface for Referral module 
 * @author software
 */
public interface ReferralService {
	
	public void cancelReferral(Integer loginID, Integer refId);
	
	public List<Referral> getListOfReferralsPlan(Integer encounterId,Integer patientId, String dx);
	
	public Referral getReferralPlan(Integer referralId);
	
	public void saveReferralPlan(Integer patientId, String reason, String notes, String dx,Integer criticalstatus, String expdate);
	
	public ReferralListBean getListOfReferrals(Integer chartId);
	
	public ReferralBean getReferral(Integer referralId,Integer charId,Boolean fromEdit);
	
	public ReferralBean getReferral(Integer referralId,Integer charId,Integer encounterId);

	public String saveReferral(ReferralForm formBean) throws JSONException;

}
