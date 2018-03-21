package com.glenwood.glaceemr.server.application.services.chart.skintest;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.SkinTestConcentration;
import com.glenwood.glaceemr.server.application.models.ConcentrateGroup;
import com.glenwood.glaceemr.server.application.models.SkinTestOrder;
import com.glenwood.glaceemr.server.application.models.SkinTestOrderEntry;

public interface SkinTestingFormService {

	public List<ConcentrateGroup> getAllergenCategoriesWithAllergens();
	
	public void saveSkinTestSheet(String sheetName,JSONArray data,JSONArray categoryOrder,JSONObject otherDetails,int loginId) throws JSONException;
	
	public void editSkinTestSheet(int sheetId,JSONArray data, JSONArray categoryOrder,JSONObject otherDetails,int loginId) throws JSONException;
	
	public List<SkinTestShortcutBean> getSkinTestSheets();
	public List<EmployeeProfile> getAllTechnicians();
	public List<PosTable> getAllPos();
	public SkinTestOrder saveSkinTestOrder(SkinTestOrderSaveJSON skinTestOrderSaveJSON) throws Exception;
	
	public int saveSkinTestOrderEntry(SkinTestOrderEntrySaveJSON skinTestOrderEntrySaveJSON) throws Exception;
	
	public SkinTestOrder saveSkinTestOrderDetails(SkinTestOrderDetailsSaveJSON skinTestOrderDetailsSaveJSON,boolean primarySave) throws Exception;
	
	public List<SkinTestOrder> getSkinTestOrders(Integer patientId);
	
	public SkinTestOrderEntry getSkinTestOrderEntry(int entryId);
	
	public SkinTestOrderBean getSkinTestOrderDetails(Integer orderId);
	
	public DxAndPendingOrdersBean getDxAndPendingOrders(Integer patientId, Integer encounterId,Integer chartId);
	
	public EmployeeProfile getPatientServiceDoctor(Integer patientId, Integer encounterId);
	
	public PosTable getPatientPOS(Integer patientId, Integer encounterId);
	
	public List<SkinTestConcentration> getTestConcentrations();
	
	public SkinTestOrder reviewSkinTestOrder(Integer orderId,Integer reviewedBy);
	public SkinTestOrder completeSkinTestOrder(Integer orderId,Integer completedBy);
	public SkinTestOrder updateSkinTestOrderEvaluationText(Integer orderId,JSONArray list,int modifiedBy);
	public SkinTestOrder updateSkinTestOrderResultComments(Integer orderId,String resultComments,int modifiedBy);	
	
	
	public List<SkinTestOrderEntry> updateSkinTestEntryBilledStatus(JSONArray orderEntryIds,int billedStatus,int loginId);
	
//	public void getReviewedUserSignDetails(int reviewedBy);
	
	public SkinTestOrder editSkinTestOrder(SkinTestOrderSaveJSON skinTestOrderSaveJSON) throws Exception;
	
	public SkinTestOrderEntry editSkinTestOrderEntryDate(int orderEntryId,String newDate,int modifiedBy);
}
