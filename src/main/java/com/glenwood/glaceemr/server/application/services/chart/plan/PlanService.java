package com.glenwood.glaceemr.server.application.services.chart.plan;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.ReferringDoctor;
import com.glenwood.glaceemr.server.application.models.PatientAssessments;

public interface PlanService {
	
	InstructionData getInstructions(Integer patientId, Integer chartId, Integer encounterId, Integer templateId, String clientId, Integer tabId, String dxcode);

	String getNotes(Integer patientId, Integer encounterId, String dxcode);

	ShortcutsData getPlanShortcuts(Integer limit, Integer offset, String key);

	List<PatientAssessments> getCurrentDx(Integer patientId, Integer encounterId);

	String getSystems(Integer patientId, Integer chartId, Integer encounterId,
			Integer templateId, String clientId, Integer tabId);

	String getPatientIns(Integer encounterId, String gwId);

	List<ReturnVisitData> getReturnVisit(Integer templateId, Integer encounterId);

	void mapInstructiontoDx(Integer insId, String dxcode, Integer mappingType,
			String codingsystem);

	void updatePatientAftercareIns(Integer insId, String insName, Boolean insStatus,
			Integer encounterId, Integer patientId, Integer otherIns,
			Integer dxHandout, String dxCode, String codingsystem);

	void getLanguages(Integer insId, Integer patientId);

	List<ReferringDoctor> getReferringPhysicians();

}
