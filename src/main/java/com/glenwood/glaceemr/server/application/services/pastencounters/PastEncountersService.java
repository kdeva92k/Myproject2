package com.glenwood.glaceemr.server.application.services.pastencounters;

import org.json.JSONException;

public interface PastEncountersService {
	
	public String getPastEncounters(Integer patientId, Integer chartId) throws JSONException;

	public String getVisitSummary(Integer patientId, Integer chartId,
			Integer encounterId) throws JSONException;
	
}
