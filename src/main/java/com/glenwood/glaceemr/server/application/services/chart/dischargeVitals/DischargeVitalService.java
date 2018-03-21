package com.glenwood.glaceemr.server.application.services.chart.dischargeVitals;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.PatientVitals;

public interface DischargeVitalService {

	Boolean saveDischargeVitals(DischargeSaveVitalBean vitalDataBean);
	List<PatientVitals> getDischartgeVitals(Integer patientId, Integer chartId,Integer admssEpisode);

}
