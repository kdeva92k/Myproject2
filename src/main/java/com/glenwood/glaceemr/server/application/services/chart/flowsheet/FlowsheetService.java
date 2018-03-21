package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.Flowsheet;

public interface FlowsheetService {
	
	public List<Flowsheet> getFlowsheetPatientData(Integer flowsheetType);
	public FS_Management getFlowsheetsManagementDetails(Integer loadFromFlowsheet,Integer flowsheetId,Integer flowsheetTypeId);
	public FS_ConfiguredDetails getFlowsheetsConfiguredDetails(Integer flowsheetId);
	public FS_ConfiguredDetails getFlowsheetsStandardGroupDetails(Integer isStandardLab);
	public FlowsheetBean getFlowsheetData(Integer flowsheetType,Integer flowsheetId, Integer patientId, Integer encounterId, Integer chartId) throws Exception;
	public List<FS_ClinicalElementOptionBean> getClinicalElements(Integer patientId,Integer encounterId,String gwIds, Integer isVital);
	public List<FlowsheetBean> getRecommendedLabs(Integer chartId,Integer patientId,Integer encounterId) throws Exception;
	public FS_ConfiguredDetails saveFlowsheetsStandardGroupDetails(Integer isStandardLab,String saveGroupData);
	public FS_Management saveFlowsheetsManagmentDetails(Integer flowsheetId,Integer flowsheetType,String flowsheetName,String dataToSave) throws Exception;
	public HmrRuleBean getFlowSheetRuleQuery(String groupId,Integer flowsheetElementType) throws Exception;
	public List<FlowsheetBean> getFlowsheetDataList(Integer flowsheetType,String dxCode,Integer patientId,Integer encounterId,Integer chartId) throws Exception;
	public List<FlowsheetBean> getFlowsheetDataListOverdue(Integer patientId,Integer encounterId, Integer chartId) throws Exception;
	public List<OverdueAlert> overdueLabs(Integer patientId, Integer chartId, Integer encounterId) throws Exception;
	public FlowsheetBean getFlowsheetDataSOAP(Integer flowsheetType,Integer flowsheetId, Integer patientId, Integer encounterId,Integer chartId) throws Exception;

}
