package com.glenwood.glaceemr.server.application.services.chart.RefillRequest;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.Prescription;
import com.glenwood.glaceemr.server.application.models.SSCancelOutbox;
import com.glenwood.glaceemr.server.application.models.SSMessageInbox;

public  interface  RefillRequestService{

	/**
	 * @param encounterid
	 * @param patientid
	 * @return
	 */
	List<Prescription> getDeniedDrugs(Integer encounterid, Integer patientid);
	SSMessageBean getCount();
	MedicationListBean getAlertInbox(String erxUserAlertType,String userId,boolean isNewPage);
	String isCloseAlert(int userId,String alertId,String alertEventIds);
	List<SSMessageInbox> mergeRequest(int patientId,String alertId);
	List<SSMessageInbox> patRequestsData(int patientId);
	String BillingConfig(int patientId);
	String getPosDetails(int patientId);
	PatientRegistration getPatientData(int patientId);

}
