package com.glenwood.glaceemr.server.application.services.chart.CurrentMedication;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PharmDetails;
import com.glenwood.glaceemr.server.application.models.ReceiptDetail;

public interface CurrentMedicationService {

	/**
	 *  
	 * @return Getting patient allergies data
	 * @param chartId
	 */
	List<PatientAllergiesBean> getAllergies(int chartId);
	
	/**
	 *  
	 * @return Getting Patient pharmacy details
	 * @param patientId
	 */
	PharmDetails getPharmacy(int patientId);
	
	/**
	 *  
	 * @return Getting Patient Encounter data
	 * @param patientId
	 * @param encounterId
	 */
	List<Encounter> getEncounterData(int encounterId);
	
	/**
	 *  
	 * @return Getting Patient copay details
	 * @param patientId
	 * @param encounterId
	 */
	PatientRegistration getCopay(int patientId);
	
	/**
	 *  
	 * @return Getting Patient copayment details
	 * @param patientId
	 * @param encounterId
	 */
	List<ReceiptDetail> getCopayment(int encounterId,int patientId);
	
	/**
	 *  
	 * @return Getting Patient chart details
	 * @param patientId
	 */
	Chart getchartDetails(int patientId);
	
	/**
	 *  
	 * @return Getting max of encounters 
	 * @param patientId
	 * @param chartId
	 * @param userId
	 */
	Encounter getMaxEnc(String chartId,int patientId,int userId);
	
	/**
	 *  
	 * @return Getting Patient current medications details 
	 * @param patientId
	 * @param encounterId
	 */
	List<MedicationDetailBean> getMedDetails(int encounterId,int patientId);
	
	/**
	 *  
	 * @return Getting Patient transition of care and summary care details 
	 * @param patientId
	 * @param encounterId
	 * @param userId
	 */
	Encounter getTransitionSummaryCare(int encounterId);
	
	/**
	 *  
	 * @return Getting Active current medications of the patient
	 * @param patientId
	 */
	List<ActiveMedicationsBean> getActiveCurrentMeds(int patientId);
	
	/**
	 *  
	 * @return Getting Active prescribed medications of the patient
	 * @param patientId
	 */
	List<ActiveMedicationsBean> getActivePrescMeds(int patientId);
	
	/**
	 *  
	 * @return Getting Inactive medications of the patient
	 * @param patientId
	 */
	List<InactiveMedBean> getInActiveCurrentMeds(int patientId);
	
	/**
	 *  
	 * @return Getting medication list based on search keyword
	 * @param keyword
	 * @param prescriberspecific
	 * @param mapid
	 * @param userId
	 * @param offset
	 * @param limit
	 */
	List<SearchBean> getsearchData(String keyword,	String prescriberspecific, String mapid, int userId, int offset, int limit);
}
