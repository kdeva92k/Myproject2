package com.glenwood.glaceemr.server.application.services.chart.vitals;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.VitalGroup;
import com.glenwood.glaceemr.server.application.models.VitalsParameter;
import com.glenwood.glaceemr.server.application.services.chart.dischargeVitals.DischargeVitalBean;

public interface VitalsService  {
	
	public List<VitalGroup> getActiveVitalsGroup(Integer patientId,Integer groupId) throws Exception;

	public List<VitalsParameter> getGroupVitals(Integer patientId,Integer encounterId, Integer groupId) throws Exception;
	
	public DischargeVitalBean setVitals(Integer patientId,Integer encounterId, Integer groupId,Boolean isDischargeVitals,Integer admssEpisode ,String clientId, Integer fromPrint) throws Exception;

	public CustomVitalGroup setVitals(Integer patientId,Integer encounterId, Integer groupId,Boolean isDischargeVitals,Integer admssEpisode ,String clientId) throws Exception;
	
	public String getNotes(Integer patientId, Integer encounterId, String gwId);

	public void insertvitals(Integer patientId,Integer encounterId,Boolean isDischargeVitals,Integer admssEpisode);
}
