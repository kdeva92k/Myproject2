package com.glenwood.glaceemr.server.application.services.HospitalSuperBill;

import java.util.List;

import com.glenwood.glaceemr.server.application.Bean.AdmissionInfoBean;
import com.glenwood.glaceemr.server.application.models.Admission;
import com.glenwood.glaceemr.server.application.models.Cpt;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.ServiceDetail;


public interface HospitalSuperbillService {

	public List<AdmissionInfoBean> getAdmittedPatientsList(Integer posId,Integer doctorId, String date);
	public List<Cpt> getCptCodes(String posTypeId);
	public List<Admission> updateDischargeDate(Integer patientId,String dischargeDate, Integer admissionId);
	public List<ServiceDetail> getPreviousVisitDxCodes(Integer patientId);
	public List<EmployeeProfile> getProviderList();
	public List<ServiceDetail> getServicesList(Integer patientId,String admissionDate);
}



