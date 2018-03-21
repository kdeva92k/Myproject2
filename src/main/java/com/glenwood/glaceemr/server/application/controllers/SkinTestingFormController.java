package com.glenwood.glaceemr.server.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.ConcentrateGroup;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.SkinTestOrder;
import com.glenwood.glaceemr.server.application.models.SkinTestOrderEntry;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.chart.skintest.DxAndPendingOrdersBean;
import com.glenwood.glaceemr.server.application.services.chart.skintest.SkinTestOrderBean;
import com.glenwood.glaceemr.server.application.services.chart.skintest.SkinTestOrderDetailsSaveJSON;
import com.glenwood.glaceemr.server.application.services.chart.skintest.SkinTestOrderEntrySaveJSON;
import com.glenwood.glaceemr.server.application.services.chart.skintest.SkinTestOrderSaveJSON;
import com.glenwood.glaceemr.server.application.services.chart.skintest.SkinTestSheetsDTO;
import com.glenwood.glaceemr.server.application.services.chart.skintest.SkinTestShortcutBean;
import com.glenwood.glaceemr.server.application.services.chart.skintest.SkinTestingFormService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

/**
 * Controller for Skin Testing Forms in GlaceEMR, 
 * contains the methods to create/edit/get the skin testing sheets (Shortcuts) and to perform the skin tests on patients. 
 * 
 * @author software
 *
 */
@RestController
@RequestMapping(value = "/user/SkinTestingForms.Action")
public class SkinTestingFormController {
	
	@Autowired
	SkinTestingFormService skinTestingFormService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;
	
	/**
	 * To load the allergen categories with its allergens
	 * @return the list of skin test sheets in the practice
	 * @throws Exception
	 */
	@RequestMapping(value ="/loadAllergens", method = RequestMethod.GET) 
	@ResponseBody
	public EMRResponseBean loadAllergens() throws Exception {
		List<ConcentrateGroup> data = skinTestingFormService.getAllergenCategoriesWithAllergens();
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(data);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CONFIGURATION, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successful retrieval of allergen categories with its allergens in Skintest Configuration", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To get the skin test sheets
	 * @param patientid
	 * @return the list of skin test sheets in the practice
	 * @throws Exception
	 */
	@RequestMapping(value ="/getSkinTestSheets", method = RequestMethod.GET) 
	@ResponseBody
	public EMRResponseBean getSkinTestSheets() throws Exception {
		List<SkinTestShortcutBean> shortcuts = skinTestingFormService.getSkinTestSheets();
		List<ConcentrateGroup> totalAllergensData = skinTestingFormService.getAllergenCategoriesWithAllergens();
		SkinTestSheetsDTO sheetsDTO = new SkinTestSheetsDTO();
		sheetsDTO.setShortcutBeans(shortcuts);
		sheetsDTO.setTotalAllergensData(totalAllergensData);
		/*HashMap<String, Object> resultData = new HashMap<String, Object>();
		resultData.put("shortcutsData", shortcuts);
		resultData.put("totalAllergensData", totalAllergensData);*/
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(sheetsDTO);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successful retrieval of skin test sheets", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To create the skin test sheets
	 * @throws Exception
	 */
	@RequestMapping(value ="/createSkinTestSheet", method = RequestMethod.GET) 
	@ResponseBody
	public void createSkinTestSheet(@RequestParam(value="sheetName",required=false,defaultValue="")String sheetName,
			@RequestParam(value="data",required=false,defaultValue="")String data,
			@RequestParam(value="categoryOrder",required=false,defaultValue="")String categoryOrder,
			@RequestParam(value="otherDetails",required=false,defaultValue="")String otherDetails,
			@RequestParam(value="loginId",required=false,defaultValue="-1")Integer loginId) throws Exception {
		JSONObject otherDetails1 = new JSONObject(otherDetails);
		JSONArray data1 = new JSONArray(data);
		JSONArray categoryOrder1 = new JSONArray(categoryOrder);
		skinTestingFormService.saveSkinTestSheet(sheetName,data1, categoryOrder1,otherDetails1,loginId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CONFIGURATION, LogActionType.CREATE, 0, Log_Outcome.SUCCESS, "successful creation of skin test sheets", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	/**
	 * To edit the skin test sheets
	 * @throws Exception
	 */
	@RequestMapping(value ="/editSkinTestSheet", method = RequestMethod.GET) 
	@ResponseBody
	public void editSkinTestSheet(@RequestParam(value="sheetId",required=false,defaultValue="")Integer sheetId,
			@RequestParam(value="data",required=false,defaultValue="")JSONArray data,
			@RequestParam(value="categoryOrder",required=false,defaultValue="")JSONArray categoryOrder,
			@RequestParam(value="otherDetails",required=false,defaultValue="")JSONObject otherDetails,
			@RequestParam(value="loginId",required=false,defaultValue="-1")Integer loginId) throws Exception {
		skinTestingFormService.editSkinTestSheet(sheetId,data, categoryOrder,otherDetails,loginId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CONFIGURATION, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successful updation of skin test sheet", -1, request.getRemoteAddr(), -1, "loginId="+loginId, LogUserType.USER_LOGIN, "", "");
		return;
	}
	
	/**
	 * To get the list of technicians 
	 * @throws Exception
	 */
	@RequestMapping(value ="/getAllUsers", method = RequestMethod.GET) 
	@ResponseBody
	public EMRResponseBean getAllTechnicians() throws Exception {
		List<EmployeeProfile> usersList = skinTestingFormService.getAllTechnicians();
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(usersList);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of technicians", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To get the list of pos 
	 * @throws Exception
	 */
	@RequestMapping(value ="/getAllPos", method = RequestMethod.GET) 
	@ResponseBody
	public EMRResponseBean getAllPos() throws Exception {
		List<PosTable> posList = skinTestingFormService.getAllPos();
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(posList);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of pos details", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To save a skin test order
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveSkinTestOrder", method = RequestMethod.POST) 
	@ResponseBody
	public EMRResponseBean saveSkinTestOrder(@RequestBody SkinTestOrderSaveJSON skinTestOrderSaveJSON) throws Exception {
		SkinTestOrder skinTestOrder = skinTestingFormService.saveSkinTestOrder(skinTestOrderSaveJSON);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(skinTestOrder.getSkinTestOrderId());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.CREATE, 0, Log_Outcome.SUCCESS, "successful insertion of skin test order", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To save a skin test order entry
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveSkinTestOrderEntry", method = RequestMethod.POST) 
	@ResponseBody
	public EMRResponseBean saveSkinTestOrderEntry(@RequestBody SkinTestOrderEntrySaveJSON skinTestOrderEntrySaveJSON) throws Exception {
		int entryId = skinTestingFormService.saveSkinTestOrderEntry(skinTestOrderEntrySaveJSON);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(skinTestingFormService.getSkinTestOrderEntry(entryId));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.CREATE, 0, Log_Outcome.SUCCESS, "successful insertion of skin test order entry", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To save a skin test order details
	 * @throws Exception
	 */
	@RequestMapping(value ="/saveSkinTestOrderDetails", method = RequestMethod.POST) 
	@ResponseBody
	public EMRResponseBean saveSkinTestOrderDetails(@RequestBody SkinTestOrderDetailsSaveJSON skinTestOrderDetailsSaveJSON) throws Exception {
		SkinTestOrder skinTestOrder = skinTestingFormService.saveSkinTestOrderDetails(skinTestOrderDetailsSaveJSON,false);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(skinTestingFormService.getSkinTestOrderDetails(skinTestOrder.getSkinTestOrderId()));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.CREATE, 0, Log_Outcome.SUCCESS, "successful insertion of skin test order details", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To get the skin test orders
	 * @param patientid
	 * @return the list of skin test orders of the patient
	 * @throws Exception
	 */
	@RequestMapping(value ="/getSkinTestOrders", method = RequestMethod.GET) 
	@ResponseBody
	public EMRResponseBean getSkinTestOrders(@RequestParam(value="patientId",required=false,defaultValue="")Integer patientId) throws Exception {
		List<SkinTestOrder> skinTestOrders = skinTestingFormService.getSkinTestOrders(patientId);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(skinTestOrders);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of skin test orders of the patient", -1, request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To get the skin test order
	 * @param orderid
	 * @return the skin test order
	 * @throws Exception
	 */
	@RequestMapping(value ="/getSkinTestOrderDetails", method = RequestMethod.GET) 
	@ResponseBody
	public EMRResponseBean getSkinTestOrder(@RequestParam(value="orderId",required=false,defaultValue="")Integer orderId) throws Exception {
		SkinTestOrderBean skinTestOrder = skinTestingFormService.getSkinTestOrderDetails(orderId);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(skinTestOrder);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved skin test order details", -1, request.getRemoteAddr(), -1, "orderId="+orderId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To get the patient's Dx list and pending lab orders
	 */
	@RequestMapping(value="/getDxAndPendingLabOrders", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getDxAndPendingLabOrders(@RequestParam(value="patientId",required=false,defaultValue= "-1")Integer patientId,
			@RequestParam(value="chartId",required=false,defaultValue="-1") Integer chartId,
			@RequestParam(value="encounterId",required=false,defaultValue="-1") Integer encounterId) {
		DxAndPendingOrdersBean dxAndPendingOrdersBean = skinTestingFormService.getDxAndPendingOrders(patientId, encounterId, chartId);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(dxAndPendingOrdersBean);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved patient's Dx list and pending lab orders", -1, request.getRemoteAddr(), patientId, "chartId="+chartId+"|encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To get the patient's encounter service doctor or principal doctor.
	 */
	@RequestMapping(value="/getPatientServiceDoctor", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientServiceDoctor(@RequestParam(value="patientId",required=false,defaultValue= "-1")Integer patientId,
			@RequestParam(value="encounterId",required=false,defaultValue="-1") Integer encounterId) {
		EmployeeProfile serivceDoctor = skinTestingFormService.getPatientServiceDoctor(patientId, encounterId);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(serivceDoctor);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved patient's encounter service doctor or principal doctor", -1, request.getRemoteAddr(), patientId, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To get the patient's encounter place of service.
	 */
	@RequestMapping(value="/getPatientPOS", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getPatientPOS(@RequestParam(value="patientId",required=false,defaultValue= "-1")Integer patientId,
			@RequestParam(value="encounterId",required=false,defaultValue="-1") Integer encounterId) {
		PosTable pos = skinTestingFormService.getPatientPOS(patientId, encounterId);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(pos);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved patient's encounter place of service", -1, request.getRemoteAddr(), patientId, "encounterId="+encounterId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	

	/**
	 * To get the list of intradermal test concentrations
	 */
	@RequestMapping(value="/getIntradermalTestConcentrations", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getIntradermalTestConcentrations() {
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(skinTestingFormService.getTestConcentrations());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.VIEW, 0, Log_Outcome.SUCCESS, "successfully retrieved list of intradermal test concentrations", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To update the reviewed details.
	 */
	@RequestMapping(value="/reviewSkinTestOrder", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean reviewSkinTestOrder(@RequestParam(value="orderId",required=false,defaultValue= "-1")Integer orderId,
			@RequestParam(value="reviewedBy",required=false) Integer reviewedBy) {
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(skinTestingFormService.reviewSkinTestOrder(orderId, reviewedBy));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successful updation of skintest order reviewed details.", -1, request.getRemoteAddr(), -1, "reviewedBy="+reviewedBy, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To update the completed details.
	 */
	@RequestMapping(value="/completeSkinTestOrder", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean completeSkinTestOrder(@RequestParam(value="orderId",required=false,defaultValue= "-1")Integer orderId,
			@RequestParam(value="completedBy",required=false) Integer completedBy) {
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(skinTestingFormService.completeSkinTestOrder(orderId, completedBy));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successful updation of skintest order completed details", -1, request.getRemoteAddr(), -1, "completedBy="+completedBy, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To save/update the evaluation text of an skin test order
	 */
	@RequestMapping(value="/updateOrderEvaluationText", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean updateOrderEvaluationText(@RequestParam(value="allergensList",required=false,defaultValue= "-1")JSONArray allergensList,
			@RequestParam(value="orderId",required=false,defaultValue= "-1")Integer orderId,
			@RequestParam(value="modifiedBy",required=false) Integer modifiedBy) {
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(skinTestingFormService.updateSkinTestOrderEvaluationText(orderId, allergensList,modifiedBy));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.CREATE, 0, Log_Outcome.SUCCESS, "successful insertion of skintest order evaluation text", -1, request.getRemoteAddr(), -1, "modifiedBy="+modifiedBy, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To save/update the result comments of an skin test order
	 */
	@RequestMapping(value="/updateOrderResultComments", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean updateOrderResultComments(@RequestParam(value="resultComments",required=false,defaultValue= "-1")String resultComments,
			@RequestParam(value="orderId",required=false,defaultValue= "-1")Integer orderId,
			@RequestParam(value="modifiedBy",required=false) Integer modifiedBy) {
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(skinTestingFormService.updateSkinTestOrderResultComments(orderId, resultComments,modifiedBy));
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.CREATE, 0, Log_Outcome.SUCCESS, "successful insertion of result comments of an skin test order", -1, request.getRemoteAddr(), -1, "modifiedBy="+modifiedBy, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To update the billed status of an test entry
	 */
	@RequestMapping(value="/updateSkinTestEntryBilledStatus", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean updateSkinTestEntryBilledStatus(@RequestParam(value="orderEntryIds",required=false,defaultValue= "-1")JSONArray orderEntryIds,
			@RequestParam(value="billedStatus",required=false) Integer billedStatus,
			@RequestParam(value="modifiedBy",required=false) Integer modifiedBy) {
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		List<SkinTestOrderEntry> entries = skinTestingFormService.updateSkinTestEntryBilledStatus(orderEntryIds, billedStatus,modifiedBy);
//		emrResponseBean.setData(entries.);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successfully updated the billed status of an skin test order test entry", -1, request.getRemoteAddr(), -1, "modifiedBy="+modifiedBy, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To get the reviewed user sign details 
	 */
	/*@RequestMapping(value="/getReviewedUserSignDetails",method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getReviewedUserSignDetails(@RequestParam(value="reviewedBy",required=false,defaultValue="-1") Integer reviewedBy) {
		skinTestingFormService.getReviewedUserSignDetails(reviewedBy);
		return null;
	}*/
	
	/**
	 * To edit the skin test order primary details
	 */
	@RequestMapping(value="/editSkinTestOrder", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean editSkinTestOrder(@RequestBody SkinTestOrderSaveJSON skinTestOrderSaveJSON) throws Exception {
		SkinTestOrder skinTestOrder = skinTestingFormService.editSkinTestOrder(skinTestOrderSaveJSON);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(skinTestOrder.getSkinTestOrderId());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successfully updated skin test order details", -1, request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	/**
	 * To edit the skin test order entry date
	 */
	@RequestMapping(value="/editSkinTestOrderEntryDate", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean editSkinTestOrderEntryDate(@RequestParam(value="newDate",required=false) String newDate,
			@RequestParam(value="orderEntryId",required=false)Integer orderEntryId,
			@RequestParam(value="modifiedBy",required=false)Integer modifiedBy) throws Exception {
		SkinTestOrderEntry orderEntry = skinTestingFormService.editSkinTestOrderEntryDate(orderEntryId,newDate,modifiedBy);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(orderEntry);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SKINTESTINGFORMS, LogActionType.UPDATE, 0, Log_Outcome.SUCCESS, "successfully updated the skin test order entry date", -1, request.getRemoteAddr(), -1, "modifiedBy="+modifiedBy, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}
	
	
}