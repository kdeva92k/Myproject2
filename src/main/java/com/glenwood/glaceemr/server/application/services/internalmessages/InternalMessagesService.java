package com.glenwood.glaceemr.server.application.services.internalmessages;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.Encounter;

public interface InternalMessagesService {

	List<Encounter> getIMEncounters(String patientId);

	Encounter getEncounterDetails(String encounterId);

	Encounter update(String encounterId, String patientId, String chartId,
			String userId,String serviceDoctor, String message, String severity, String status,
			String encounterType);

	Encounter compose(String patientId, String chartId, String userId,String serviceDoctor,String toId,
			String message, String severity, String encounterType);
}