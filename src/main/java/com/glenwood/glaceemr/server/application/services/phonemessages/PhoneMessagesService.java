package com.glenwood.glaceemr.server.application.services.phonemessages;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.Encounter;

public interface PhoneMessagesService {
	
	/**
	 * To get the list of phone messages based on the patient id
	 * @param patientId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Encounter> getPhoneEncounters(String patientId, String startDate, String endDate);

	/**
	 * To get the particular encounter details based on the encounter id
	 * @param encounterId
	 * @return
	 */
	Encounter getEncounterDetails(String encounterId);

	/**
	 * To update the particular encounter details
	 * @param encounterId
	 * @param response
	 * @param status
	 * @param modifiedBy
	 * @param serviceDr
	 * @return
	 */
	Encounter update(String encounterId, String response, String status, String modifiedBy, String serviceDr);

	/**
	 *  To send reply to a phone message encounter.
	 * @param encounterId
	 * @param replyMessage
	 * @param status
	 * @param modifiedBy
	 * @param severity
	 * @return
	 */
	Encounter sendReply(String encounterId, String replyMessage, String status, String modifiedBy, String severity);

	/**
	 * To create phone message encounter
	 * @param chartId
	 * @param severity
	 * @param encounterType
	 * @param serviceDr
	 * @param comments
	 * @param createdBy
	 * @return
	 */
	Encounter createEncounter(String chartId, String severity, Short encounterType, String serviceDr, String comments, String createdBy);

}
