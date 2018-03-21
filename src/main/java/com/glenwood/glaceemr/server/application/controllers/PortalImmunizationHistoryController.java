package com.glenwood.glaceemr.server.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.VaccineUpdateBean;
import com.glenwood.glaceemr.server.application.services.portal.portalImmunizationHistory.PortalImmunizationHistoryService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

@RestController
@Transactional
@RequestMapping("/user/PortalImmunizationHistory")
public class PortalImmunizationHistoryController {

	@Autowired
	PortalImmunizationHistoryService portalImmunizationHistoryService;


	/**
	 * Appointment list of a patient appointments.
	 * 
	 * @param patientId
	 *            : id of the required patient.
	 * @param appointmentsType
	 *            : indicates future or past or present day appointment .
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/ImmunizationHistory", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getImmunizationHistory(
			 @RequestParam(value = "patientId", required = false, defaultValue = "0") int patientId,
			@RequestParam(value = "chartId", required = false, defaultValue = "present") int chartId,
			 @RequestParam(value = "pageOffset", required = false, defaultValue = "5") int pageOffset,
			 @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex)
			throws Exception {

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalImmunizationHistoryService
					.getPatientImmunizationHistory(patientId, chartId,
							pageOffset, pageIndex));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean
					.setData("Error in retrieving patient's immunization history!");
			return responseBean;
		}

	}

	/**
	 * Appointment list of a vaccine vsi files
	 * 
	 * @param patientId
	 *            : lab description cvx
	 * @return List of Appointments of a patient.
	 */
	@RequestMapping(value = "/VISFilesList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getVaccineVISFilesList(
			 @RequestParam(value = "labDescCVX", required = false, defaultValue = "0") String labDescCVX)
			throws Exception {

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalImmunizationHistoryService
					.getVaccineVISFilesList(labDescCVX));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving vaccine VIS files list!");
			return responseBean;
		}

	}

	/**
	 * Vaccine List
	 * 
	 * @param searchKey
	 *            : key to search
	 * @param pageOffset
	 *            : offset of the page.
	 * @param pageIndex
	 *            : index of the page.
	 * @return Vaccines list
	 */
	@RequestMapping(value = "/VaccineList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getVaccineList(
			@RequestParam(value = "searchKey", required = false, defaultValue = "present") String searchKey)
			throws Exception {

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalImmunizationHistoryService
					.getVaccineList(searchKey));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving vaccines list!");
			return responseBean;
		}

	}

	/**
	 * Vaccine Update Reasons list of a patient appointments.
	 * 
	 * @return reasons list.
	 */
	@RequestMapping(value = "/VaccineUpdateReasonsList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getVaccUpdateReasonList() throws Exception {

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalImmunizationHistoryService
					.getVaccUpdateReasonList());
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean
					.setData("Error in retrieving vaccine update reasons list!");
			return responseBean;
		}

	}

	/**
	 * @return Update vaccine info
	 * @throws Exception
	 */
	@RequestMapping(value = "/VaccineUpdate", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean bookAppointment(
			@RequestBody VaccineUpdateBean vaccineUpdateBean) throws Exception {

		EMRResponseBean responseBean=new EMRResponseBean();
		
		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			responseBean.setSuccess(true);
			responseBean.setData(portalImmunizationHistoryService
					.requestVaccineUpdate(vaccineUpdateBean));
			return responseBean;
		} catch (Exception e) {
			e.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in updating vaccine!");
			return responseBean;
		}
	}

}
