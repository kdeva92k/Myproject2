package com.glenwood.glaceemr.server.application.services.chart.growthgraph;


import java.util.List;

import com.glenwood.glaceemr.server.application.models.GrowthGraph;
import com.glenwood.glaceemr.server.application.models.GrowthGraphVitalData;
import com.glenwood.glaceemr.server.application.models.GrowthGraphPatientData;

public interface GrowthGraphService {

	/**
	 * To get default graph id based on patient id
	 * @param patientId
	 * @return default graph id 
	 */
	
	String getDefaultGraphId(String patientId);

	/**
	 * To get patient details based on patient id
	 * @param patientId
	 * @return patient details
	 */
	GrowthGraphPatientData getpatientinfo(String patientId);
	
	/**
	 * To get list of vital data's based on patient id
	 * @param patientId
	 * @return list of vital data's
	 */
	List<GrowthGraphVitalData> getVitalValues(String patientId,boolean wellvist);
	
	/**
	 * To get list graph details based on patient id
	 * @param patientId
	 * @return list graph details
	 */
	List<GrowthGraph> getGraphList(String patientId);

}