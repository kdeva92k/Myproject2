package com.glenwood.glaceemr.server.application.services.chart.ErxSummary;

import java.util.List;

public interface ErxSummaryService {

	/**
	 *  
	 * @return getting Patient pharmacy details
	 * @param patientId
	 * @throws Exception
	 */
	PharmacyBean getPatientPharmacy(int patientId);

	
	/**
	 *  
	 * @return getting Provider details
	 * @param chartUserGroupId
	 * @param encounterId
	 * @param userId
	 * @throws Exception
	 */
	String getProvider(int userId,int chartUserGroupId,int encounterId);

	
	/**
	 *  
	 * @return getting patient data for Erx summary popup
	 * @param patientId
	 * @throws Exception
	 */
	List<ErxPatientDataBean> getPatientData(int patientId);

	
	/**
	 *  
	 * @return getting doctor details for Erx summary popup
	 * @param chartUserGroupId
	 * @param encounterId
	 * @param userId
	 * @param pos
	 * @throws Exception
	 */
	List<DoctorDetailBean> getDoctorDetails(int encounterId,int userId,int chartUserGroupId, int pos);

	
	/**
	 *  
	 * @return getting pharmacy details for Erx summary popup
	 * @param pharmId
	 * @throws Exception
	 */
	List<PharmacyBean> getPharmDetails(int pharmId);

	
	/**
	 *  
	 * @return getting medication details for Erx summary popup
	 * @param encounterId
	 * @param prescId
	 * @throws Exception
	 */
	List<NewRxBean> getNewRxDetails(int encounterId, String prescId);

	
	/**
	 *  
	 * @return getting Prescribed medication details of currenrt encounter
	 * @param patientId
	 * @param encounterId
	 * @param userId
	 * @throws Exception
	 */
	List<PrescribedMedBean> getPrescData(int encounterId);

	
	/**
	 *  
	 * @return controlled substances check
	 * @param pharmacyId
	 * @param MedicationNames
	 * @param userId
	 * @throws Exception
	 */
	String checkCS(String medicationNames, int userId, int pharmacyId);


	int getMaxEncounterId(int patientId);

}
