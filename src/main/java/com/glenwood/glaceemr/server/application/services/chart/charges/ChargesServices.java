package com.glenwood.glaceemr.server.application.services.chart.charges;
/**
 * Charges service interface 
 * @author Tarun
 *
 */
import java.util.List;

import com.glenwood.glaceemr.server.application.models.Cpt;
import com.glenwood.glaceemr.server.application.models.PatientAssessments;
import com.glenwood.glaceemr.server.application.models.InitialSettings;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.Quickcpt;
import com.glenwood.glaceemr.server.application.models.SaveServicesBean;
import com.glenwood.glaceemr.server.application.models.ServiceDetail;
import com.glenwood.glaceemr.server.application.models.SubmitStatus;
import com.glenwood.glaceemr.server.application.models.UpdateServiceBean;



public interface ChargesServices {
	/**
	 * Basic information of a patient 
	 * @param patientId
	 * @return
	 */
	public ChargesPageBasicDetailsBean findAllBasicDetails(Integer patientId);

	/**
	 * Quick CPT codes
	 * @return
	 */
	public List<Quickcpt> findAllQuickCptCodes();

	/**
	 * All services available for a patient
	 * @param patientId
	 * @return
	 */
	public List<ServiceDetail> getAllServices(Integer patientId);
	
	/**
	 * Single service all information 
	 * @param patientId
	 * @param serviceId
	 * @return
	 */
	public ServiceDetail getServiceDetails(Integer patientId, Integer serviceId);

	/**
	 * Service Deletion
	 * @param serviceIDs
	 * @param patientId
	 * @param modifiedDate
	 * @param loginId
	 * @param loginName
	 * @return
	 */
	public Boolean deleteMentoinedServices(String serviceIDs, Integer patientId,String modifiedDate, String loginId, String loginName);

	/**
	 * Previous visit Dx codes
	 * @param patientId
	 * @return
	 */
	public ServiceDetail getPreviousVisitDxCodes(Integer patientId);

	/**
	 * Cpt code total information based on requested CPT codes list
	 * @param cptCodes
	 * @return
	 */
	public List<Cpt> getAllCptCodesDetails(List<String> cptCodes);

	/**
	 * Patient guaranteer information
	 * @param patientid
	 * @return
	 */
	public PatientRegistration getGuarantorDetail(Integer patientid);

	/**
	 * Save new service to the patient 
	 * @param createdEntity
	 */
	public void requestToSaveService(ServiceDetail createdEntity);

	/**
	 * 
	 * @param saveServicesBean
	 * @param cpt
	 * @param forGuarantor
	 * @param i
	 * @param defaultBillingReason
	 * @return
	 */
	public Boolean insertEnityCreation(SaveServicesBean saveServicesBean,Cpt cpt, PatientRegistration forGuarantor, int i, String defaultBillingReason);
	/**
	 * Get default billing reason
	 * @return
	 */
	public InitialSettings getDefaultBillingReason();

	/**
	 * Get all submit status type with color information
	 * @return
	 */
	public List<SubmitStatus> getSubmitStausInfo();
	/**
	 * Update existed record
	 * @param updateService
	 * @return
	 */
	public Boolean updateServiceDetails(UpdateServiceBean updateService);
	/**
	 * EMR diagnosis information 
	 * @param patientId
	 * @param dos
	 * @return
	 */
	public List<PatientAssessments> getEMRDxCodes(Integer patientId,String dos);
}
