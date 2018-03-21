package com.glenwood.glaceemr.server.application.services.chart.skintest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.PatientAssessments;
import com.glenwood.glaceemr.server.application.models.LabEntries;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.ProblemList;
import com.glenwood.glaceemr.server.application.models.SkinTestOrderAllergen;
import com.glenwood.glaceemr.server.application.models.SkinTestOrderAllergenCategory;
import com.glenwood.glaceemr.server.application.models.Concentrate;
import com.glenwood.glaceemr.server.application.models.ConcentrateGroup;
import com.glenwood.glaceemr.server.application.models.SkinTestFormShortcut;
import com.glenwood.glaceemr.server.application.models.SkinTestFormShortcutAllergenDetails;
import com.glenwood.glaceemr.server.application.models.SkinTestFormShortcutCategoryDetails;
import com.glenwood.glaceemr.server.application.models.SkinTestOrder;
import com.glenwood.glaceemr.server.application.models.SkinTestOrderDetails;
import com.glenwood.glaceemr.server.application.models.SkinTestOrderEntry;
import com.glenwood.glaceemr.server.application.models.SkinTestConcentration;
import com.glenwood.glaceemr.server.application.repositories.EmployeeProfileRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientAssessmentsRepository;
import com.glenwood.glaceemr.server.application.repositories.LabEntriesRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.repositories.PosTableRepository;
import com.glenwood.glaceemr.server.application.repositories.ProblemListRepository;
import com.glenwood.glaceemr.server.application.repositories.ConcentrateGroupRepository;
import com.glenwood.glaceemr.server.application.repositories.ConcentrateRepository;
import com.glenwood.glaceemr.server.application.repositories.SkinTestConcentrationRepository;
import com.glenwood.glaceemr.server.application.repositories.SkinTestFormShortcutAllergenDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.SkinTestFormShortcutCategoryDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.SkinTestFormShortcutRepository;
import com.glenwood.glaceemr.server.application.repositories.SkinTestOrderAllergenCategoryRepository;
import com.glenwood.glaceemr.server.application.repositories.SkinTestOrderAllergenRepository;
import com.glenwood.glaceemr.server.application.repositories.SkinTestOrderDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.SkinTestOrderEntryRepository;
import com.glenwood.glaceemr.server.application.repositories.SkinTestOrderRepository;
import com.glenwood.glaceemr.server.application.specifications.SkinTestingFormSpecification;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

@Service
@Transactional
public class SkinTestingFormServiceImpl implements SkinTestingFormService {

	@Autowired
	ConcentrateGroupRepository concentrateGroupRepository;
	
	@Autowired
	ConcentrateRepository concentrateRepository;
	
	@Autowired 
	SkinTestFormShortcutRepository skinTestFormShortcutRepository;
	
	@Autowired 
	SkinTestFormShortcutCategoryDetailsRepository skinTestFormShortcutCategoryDetailsRepository;
	
	@Autowired 
	SkinTestFormShortcutAllergenDetailsRepository skinTestFormShortcutAllergenDetailsRepository;
	
	@Autowired 
	EmployeeProfileRepository employeeProfileRepository;
	
	@Autowired
	SkinTestOrderRepository skinTestOrderRepository;
	
	@Autowired
	SkinTestOrderEntryRepository skinTestOrderEntryRepository;
	
	@Autowired
	SkinTestOrderDetailsRepository skinTestOrderDetailsRepository;
	
	@Autowired PatientAssessmentsRepository h611Repository;
	
	@Autowired ProblemListRepository problemListRepository;
	
	@Autowired LabEntriesRepository labEntriesRepository;
	
	@Autowired EncounterRepository encounterRepository;
	
	@Autowired PatientRegistrationRepository patientRegistrationRepository;
	
	@Autowired PosTableRepository posRepository;
	
	@Autowired SkinTestConcentrationRepository skinTestConcentrationRepository;
	
	@Autowired SkinTestOrderAllergenCategoryRepository orderAllergenCategoryRepository;
	
	@Autowired SkinTestOrderAllergenRepository orderAllergenRepository;
	
	/**
	 * To get all the allergens data
	 */
	@Override
	public List<ConcentrateGroup> getAllergenCategoriesWithAllergens() {
		List<ConcentrateGroup> allergenCategoryList = concentrateGroupRepository.findAll(SkinTestingFormSpecification.getAllergenCategoriesWithAllergens());
		for(ConcentrateGroup concentrateGroup:allergenCategoryList) {
			for(Concentrate concentrate:concentrateGroup.getAllergens()) {
				concentrate.getConcentrateName();
			}
		}
		return allergenCategoryList;
	}

	/**
	 * To save a skin test sheet
	 */
	@Override
	public void saveSkinTestSheet(String sheetName,JSONArray data, JSONArray categoryOrder,JSONObject otherDetails,int loginId) throws JSONException {
		SkinTestFormShortcut skinTestFormShortcut = new SkinTestFormShortcut();
		skinTestFormShortcut.setSkinTestFormShortcutName(sheetName);
		skinTestFormShortcut.setSkinTestFormShortcutCreatedOn(skinTestFormShortcutRepository.findCurrentTimeStamp());
		skinTestFormShortcut.setSkinTestFormShortcutCreatedBy(loginId);
		skinTestFormShortcut.setSkinTestFormShortcutLastModifiedBy(loginId);
		skinTestFormShortcut.setSkinTestFormShortcutLastModifiedOn(skinTestFormShortcutRepository.findCurrentTimeStamp());
		skinTestFormShortcut.setSkinTestFormShortcutPrickWhealNeeded(otherDetails.getBoolean("pwValue"));
		skinTestFormShortcut.setSkinTestFormShortcutPrickFlareNeeded(otherDetails.getBoolean("pfValue"));
		skinTestFormShortcut.setSkinTestFormShortcutPrickErythemaNeeded(otherDetails.getBoolean("peValue"));
		skinTestFormShortcut.setSkinTestFormShortcutPrickPseudopodiaNeeded(otherDetails.getBoolean("ppValue"));
		skinTestFormShortcut.setSkinTestFormShortcutIntradermalWhealNeeded(otherDetails.getBoolean("iwValue"));
		skinTestFormShortcut.setSkinTestFormShortcutIntradermalFlareNeeded(otherDetails.getBoolean("ifValue"));
		skinTestFormShortcut.setSkinTestFormShortcutIntradermalErythemaNeeded(otherDetails.getBoolean("ieValue"));
		skinTestFormShortcut.setSkinTestFormShortcutIntradermalPseudopodiaNeeded(otherDetails.getBoolean("ipValue"));
		skinTestFormShortcut.setSkinTestFormShortcutScoringNotes(otherDetails.getString("scoringNotes"));
		skinTestFormShortcut.setSkinTestFormShortcutNotes(otherDetails.getString("notes"));
		skinTestFormShortcut.setskinTestFormShortcutdefaultreadvalue(otherDetails.getString("defaulttextbox"));
		SkinTestFormShortcut savedShortcut = skinTestFormShortcutRepository.saveAndFlush(skinTestFormShortcut);
		for(int i=0; i<data.length();i++) {
			SkinTestFormShortcutCategoryDetails formShortcutDetails = new SkinTestFormShortcutCategoryDetails();
			JSONObject obj = new JSONObject();
			if(data.get(i)!=null&&data.get(i)!="null")
				obj = (JSONObject) data.get(i);
			Integer categoryId = -1;
			categoryId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(obj.keys().next().toString())).or("-1"));
			int categoryOrderId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(((JSONObject)categoryOrder.get(i)).keys().next().toString())).or("-1"));
			formShortcutDetails.setSkinTestFormShortcutCategoryDetailsShortcutId(savedShortcut.getSkinTestFormShortcutId());
			formShortcutDetails.setSkinTestFormShortcutCategoryDetailsAllergenCategoryId(categoryId);
			formShortcutDetails.setSkinTestFormShortcutCategoryDetailsCategoryOrderId(categoryOrderId);
			formShortcutDetails.setSkinTestFormShortcutCategoryDetailsCreatedOn(skinTestFormShortcutRepository.findCurrentTimeStamp());
			formShortcutDetails.setSkinTestFormShortcutCategoryDetailsCreatedBy(loginId);
			formShortcutDetails.setSkinTestFormShortcutCategoryDetailsModifiedOn(skinTestFormShortcutRepository.findCurrentTimeStamp());
			formShortcutDetails.setSkinTestFormShortcutCategoryDetailsModifiedBy(loginId);
			SkinTestFormShortcutCategoryDetails savedShortcutDetails = skinTestFormShortcutCategoryDetailsRepository.saveAndFlush(formShortcutDetails);
			JSONArray temp = (JSONArray)obj.get(categoryId.toString());
			
			for(int j=0;j<temp.length();j++) {
				SkinTestFormShortcutAllergenDetails shortcutAllergenDetails = new SkinTestFormShortcutAllergenDetails();
				JSONObject allergen = (JSONObject) temp.get(j);
				Integer allergenOrder = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(allergen.keys().next().toString())).or("-1"));
				Integer allergenId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(allergen.getString(allergen.keys().next().toString()))).or("-1"));
				shortcutAllergenDetails.setSkinTestFormShortcutAllergenDetailsShortcutId(savedShortcut.getSkinTestFormShortcutId());
				shortcutAllergenDetails.setSkinTestFormShortcutAllergenDetailsAllergenOrderId(allergenOrder);
				shortcutAllergenDetails.setSkinTestFormShortcutAllergenDetailsCategoryDetailsId(savedShortcutDetails.getSkinTestFormShortcutCategoryDetailsId());
				shortcutAllergenDetails.setSkinTestFormShortcutAllergenDetailsAllergenId(allergenId);
				shortcutAllergenDetails.setSkinTestFormShortcutAllergenDetailsCreatedOn(skinTestFormShortcutRepository.findCurrentTimeStamp());
				shortcutAllergenDetails.setSkinTestFormShortcutAllergenDetailsCreatedBy(loginId);
				shortcutAllergenDetails.setSkinTestFormShortcutAllergenDetailsModifiedOn(skinTestFormShortcutRepository.findCurrentTimeStamp());
				shortcutAllergenDetails.setSkinTestFormShortcutAllergenDetailsModifiedBy(loginId);
				skinTestFormShortcutAllergenDetailsRepository.saveAndFlush(shortcutAllergenDetails);
			}
			
		}
	}

	/**
	 * To edit the skin test sheet
	 */
	public void editSkinTestSheet(int sheetId,JSONArray data, JSONArray categoryOrder,JSONObject otherDetails,int loginId) throws JSONException {
		SkinTestFormShortcut skinTestFormShortcut = skinTestFormShortcutRepository.findOne(sheetId);
		skinTestFormShortcut.setSkinTestFormShortcutLastModifiedBy(loginId);
		skinTestFormShortcut.setSkinTestFormShortcutLastModifiedOn(skinTestFormShortcutRepository.findCurrentTimeStamp());
		skinTestFormShortcut.setSkinTestFormShortcutPrickWhealNeeded(otherDetails.getBoolean("pwValue"));
		skinTestFormShortcut.setSkinTestFormShortcutPrickFlareNeeded(otherDetails.getBoolean("pfValue"));
		skinTestFormShortcut.setSkinTestFormShortcutPrickErythemaNeeded(otherDetails.getBoolean("peValue"));
		skinTestFormShortcut.setSkinTestFormShortcutPrickPseudopodiaNeeded(otherDetails.getBoolean("ppValue"));
		skinTestFormShortcut.setSkinTestFormShortcutIntradermalWhealNeeded(otherDetails.getBoolean("iwValue"));
		skinTestFormShortcut.setSkinTestFormShortcutIntradermalFlareNeeded(otherDetails.getBoolean("ifValue"));
		skinTestFormShortcut.setSkinTestFormShortcutIntradermalErythemaNeeded(otherDetails.getBoolean("ieValue"));
		skinTestFormShortcut.setSkinTestFormShortcutIntradermalPseudopodiaNeeded(otherDetails.getBoolean("ipValue"));
		skinTestFormShortcut.setSkinTestFormShortcutScoringNotes(otherDetails.getString("scoringNotes"));
		skinTestFormShortcut.setSkinTestFormShortcutNotes(otherDetails.getString("notes"));
		SkinTestFormShortcut savedShortcut = skinTestFormShortcutRepository.saveAndFlush(skinTestFormShortcut);
		for(int i=0; i<data.length();i++) {
			SkinTestFormShortcutCategoryDetails formShortcutDetails = new SkinTestFormShortcutCategoryDetails();
			JSONObject obj = new JSONObject();
			if(data.get(i)!=null&&data.get(i)!="null")
				obj = (JSONObject) data.get(i);
			Integer categoryId = -1;
			categoryId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(obj.keys().next().toString())).or("-1"));
			int categoryOrderId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(((JSONObject)categoryOrder.get(i)).keys().next().toString())).or("-1"));
			formShortcutDetails.setSkinTestFormShortcutCategoryDetailsShortcutId(savedShortcut.getSkinTestFormShortcutId());
			formShortcutDetails.setSkinTestFormShortcutCategoryDetailsAllergenCategoryId(categoryId);
			formShortcutDetails.setSkinTestFormShortcutCategoryDetailsCategoryOrderId(categoryOrderId);
			SkinTestFormShortcutCategoryDetails savedShortcutDetails = skinTestFormShortcutCategoryDetailsRepository.saveAndFlush(formShortcutDetails);
			JSONArray temp = (JSONArray)obj.get(categoryId.toString());
			
			for(int j=0;j<temp.length();j++) {
				SkinTestFormShortcutAllergenDetails shortcutAllergenDetails = new SkinTestFormShortcutAllergenDetails();
				JSONObject allergen = (JSONObject) temp.get(j);
				Integer allergenOrder = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(allergen.keys().next().toString())).or("-1"));
				Integer allergenId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(allergen.getString(allergen.keys().next().toString()))).or("-1"));
				shortcutAllergenDetails.setSkinTestFormShortcutAllergenDetailsShortcutId(savedShortcut.getSkinTestFormShortcutId());
				shortcutAllergenDetails.setSkinTestFormShortcutAllergenDetailsAllergenOrderId(allergenOrder);
				shortcutAllergenDetails.setSkinTestFormShortcutAllergenDetailsCategoryDetailsId(savedShortcutDetails.getSkinTestFormShortcutCategoryDetailsId());
				shortcutAllergenDetails.setSkinTestFormShortcutAllergenDetailsAllergenId(allergenId);
				skinTestFormShortcutAllergenDetailsRepository.saveAndFlush(shortcutAllergenDetails);
			}
			
		}
	
	}
	
	/**
	 * To get all the existing skin test sheets
	 */
	public List<SkinTestShortcutBean> getSkinTestSheets() {
		List<SkinTestFormShortcut> shortcuts = skinTestFormShortcutRepository.findAll();
		
		List<SkinTestShortcutBean> skinTestShortcuts = new ArrayList<SkinTestShortcutBean>();
//		skinTestShortcuts.remove(arg0)
		for(SkinTestFormShortcut shortcut:shortcuts) {
			SkinTestShortcutBean skinTestShortcutBean = new SkinTestShortcutBean();
			skinTestShortcutBean.setSkinTestFormShortcutId(shortcut.getSkinTestFormShortcutId());
			skinTestShortcutBean.setSkinTestFormShortcutName(shortcut.getSkinTestFormShortcutName());
			skinTestShortcutBean.setSkinTestFormShortcutPrickWhealNeeded(shortcut.getSkinTestFormShortcutPrickWhealNeeded());
			skinTestShortcutBean.setSkinTestFormShortcutPrickFlareNeeded(shortcut.getSkinTestFormShortcutPrickFlareNeeded());
			skinTestShortcutBean.setSkinTestFormShortcutPrickErythemaNeeded(shortcut.getSkinTestFormShortcutPrickErythemaNeeded());
			skinTestShortcutBean.setSkinTestFormShortcutIntradermalWhealNeeded(shortcut.getSkinTestFormShortcutIntradermalWhealNeeded());
			skinTestShortcutBean.setSkinTestFormShortcutIntradermalFlareNeeded(shortcut.getSkinTestFormShortcutIntradermalFlareNeeded());
			skinTestShortcutBean.setSkinTestFormShortcutIntradermalErythemaNeeded(shortcut.getSkinTestFormShortcutIntradermalErythemaNeeded());
			skinTestShortcutBean.setSkinTestFormShortcutIntradermalPseudopodiaNeeded(shortcut.getSkinTestFormShortcutIntradermalPseudopodiaNeeded());
			skinTestShortcutBean.setSkinTestFormShortcutScoringNotes(shortcut.getSkinTestFormShortcutScoringNotes());
			skinTestShortcutBean.setSkinTestFormShortcutNotes(shortcut.getSkinTestFormShortcutNotes());
			skinTestShortcutBean.setSkinTestFormShortcutdefaultreadvalue(shortcut.getskinTestFormShortcutdefaultreadvalue());

			final HashMap<Integer,ConcentrateGroupBean> concentrateGroup = new HashMap<Integer,ConcentrateGroupBean>();
			for(SkinTestFormShortcutCategoryDetails categoryDetails:shortcut.getSkinTestFormShortcutCategoryDetails()) {
				ConcentrateGroupBean concentrateGroupBean = new ConcentrateGroupBean();
				concentrateGroupBean.setConcentrateGroupId(categoryDetails.getSkinTestFormShortcutCategoryDetailsAllergenCategoryId());
				ConcentrateGroup group = concentrateGroupRepository.findOne(categoryDetails.getSkinTestFormShortcutCategoryDetailsAllergenCategoryId());
				concentrateGroupBean.setConcentrateGroupName(group.getConcentrateGroupName());
				concentrateGroupBean.setConcentrateGroupOrder(categoryDetails.getSkinTestFormShortcutCategoryDetailsCategoryOrderId());
				concentrateGroup.put(categoryDetails.getSkinTestFormShortcutCategoryDetailsAllergenCategoryId(), concentrateGroupBean);
				final HashMap<Integer,ConcentrateBean> concentrateBeans = new HashMap<Integer,ConcentrateBean>();
				for(SkinTestFormShortcutAllergenDetails allergenDetails:categoryDetails.getSkinTestFormShortcutAllergenDetails()) {
					ConcentrateBean concentrateBean = new ConcentrateBean();
					concentrateBean.setConcentrateId(allergenDetails.getSkinTestFormShortcutAllergenDetailsAllergenId());
					concentrateBean.setConcentrateName(concentrateRepository.findOne(allergenDetails.getSkinTestFormShortcutAllergenDetailsAllergenId()).getConcentrateName());
					concentrateBean.setConcentrateOrder(allergenDetails.getSkinTestFormShortcutAllergenDetailsAllergenOrderId());
					concentrateBeans.put(allergenDetails.getSkinTestFormShortcutAllergenDetailsAllergenId(),concentrateBean);
				}
				List<Map.Entry<Integer, ConcentrateBean>> allergenList = new LinkedList<>(concentrateBeans.entrySet());
			       Collections.sort(allergenList, new Comparator<Map.Entry<Integer, ConcentrateBean>>() {
			            public int compare(Map.Entry<Integer, ConcentrateBean> o1, Map.Entry<Integer, ConcentrateBean> o2) {
			            	Integer value1 = o1.getValue().getConcentrateOrder();
							Integer value2 = o2.getValue().getConcentrateOrder();
							return value1.compareTo(value2);
			            }
			       });

			    Map<Integer, ConcentrateBean> sortedAllergens = new LinkedHashMap<Integer, ConcentrateBean>();
			    for (Map.Entry<Integer, ConcentrateBean> entry : allergenList) {
			    	sortedAllergens.put(entry.getKey(), entry.getValue());
			    } 
				concentrateGroupBean.setConcentrateBeans(sortedAllergens);
			}
			List<Map.Entry<Integer, ConcentrateGroupBean>> categoryList = new LinkedList<>(concentrateGroup.entrySet());
		       Collections.sort(categoryList, new Comparator<Map.Entry<Integer, ConcentrateGroupBean>>() {
		            public int compare(Map.Entry<Integer, ConcentrateGroupBean> o1, Map.Entry<Integer, ConcentrateGroupBean> o2) {
		            	Integer value1 = o1.getValue().getConcentrateGroupOrder();
						Integer value2 = o2.getValue().getConcentrateGroupOrder();
						return value1.compareTo(value2);
		            }
		       });

		    Map<Integer, ConcentrateGroupBean> sortedCategories = new LinkedHashMap<Integer, ConcentrateGroupBean>();
		    for (Map.Entry<Integer, ConcentrateGroupBean> entry : categoryList) {
		    	sortedCategories.put(entry.getKey(), entry.getValue());
		    }
			skinTestShortcutBean.setConcentrateGroupBean(sortedCategories);
			
			skinTestShortcuts.add(skinTestShortcutBean);
			
			
		}
		for(SkinTestShortcutBean bean:skinTestShortcuts){
//			System.out.println(bean.toString());
			
		}
		return skinTestShortcuts;
	}
	
	/**
	 * To get all the eligibile users to perform skin test
	 */
	@Override
	public List<EmployeeProfile> getAllTechnicians() {
		List<EmployeeProfile> techniciansList = employeeProfileRepository.findAll(SkinTestingFormSpecification.getEligibleUsers());
		return techniciansList;
	}
	
	/**
	 * To get all the pos information for billing purpose
	 */
	@Override
	public List<PosTable> getAllPos() {
		List<PosTable> posList = posRepository.findAll(SkinTestingFormSpecification.getPos());
		return posList;
	}
	
	public void saveShortcutForPatient(SkinTestOrderSaveJSON skinTestOrderSaveJSON,Integer orderId) {
		SkinTestFormShortcut skinTestFormShortcut = skinTestFormShortcutRepository.findOne(skinTestOrderSaveJSON.getSkinTestShortcutId());
		for(SkinTestFormShortcutCategoryDetails categoryDetails:skinTestFormShortcut.getSkinTestFormShortcutCategoryDetails()) {
			SkinTestOrderAllergenCategory allergenCategory = new SkinTestOrderAllergenCategory();
			allergenCategory.setSkinTestOrderAllergenCategoryConcentrateGroupId(categoryDetails.getSkinTestFormShortcutCategoryDetailsAllergenCategoryId());
			allergenCategory.setSkinTestOrderAllergenCategoryConcentrateGroupOrderId(categoryDetails.getSkinTestFormShortcutCategoryDetailsCategoryOrderId());
			allergenCategory.setSkinTestOrderAllergenCategoryConcentrateGroupName(concentrateGroupRepository.findOne(categoryDetails.getSkinTestFormShortcutCategoryDetailsAllergenCategoryId()).getConcentrateGroupName());
			allergenCategory.setSkinTestOrderAllergenCategoryCreatedBy(skinTestOrderSaveJSON.getLoginId());
			allergenCategory.setSkinTestOrderAllergenCategoryCreatedOn(skinTestFormShortcutRepository.findCurrentTimeStamp());
			allergenCategory.setSkinTestOrderAllergenCategoryTestOrderId(orderId);
			allergenCategory.setSkinTestOrderAllergenCategoryPatientId(Integer.parseInt(skinTestOrderSaveJSON.getPatientId()));
			SkinTestOrderAllergenCategory savedCategory = orderAllergenCategoryRepository.saveAndFlush(allergenCategory);
			for(SkinTestFormShortcutAllergenDetails allergenDetails :categoryDetails.getSkinTestFormShortcutAllergenDetails()) {
				SkinTestOrderAllergen allergen = new SkinTestOrderAllergen();
				allergen.setSkinTestOrderAllergenCategoryMapId(savedCategory.getSkinTestOrderAllergenCategoryId());
				allergen.setSkinTestOrderAllergenConcentrateId(allergenDetails.getSkinTestFormShortcutAllergenDetailsAllergenId());
				allergen.setSkinTestOrderAllergenConcentrateOrderId(allergenDetails.getSkinTestFormShortcutAllergenDetailsAllergenOrderId());
				allergen.setSkinTestOrderAllergenConcentrateName(concentrateRepository.findOne(allergenDetails.getSkinTestFormShortcutAllergenDetailsAllergenId()).getConcentrateName());
				allergen.setIsIntradermalNeeded(concentrateRepository.findOne(allergenDetails.getSkinTestFormShortcutAllergenDetailsAllergenId()).getIsIntradermalNeeded());
				allergen.setSkinTestOrderAllergenCreatedBy(skinTestOrderSaveJSON.getLoginId());
				allergen.setSkinTestOrderAllergenCreatedOn(skinTestFormShortcutRepository.findCurrentTimeStamp());
				allergen.setSkinTestOrderAllergenPatientId(Integer.parseInt(skinTestOrderSaveJSON.getPatientId()));
				orderAllergenRepository.saveAndFlush(allergen);
			}
		}
	}
	
	public void getShortcutForPatient() {
		
	}
	
	/**
	 * To save a skin test order for a patient
	 */
	@Override
	public SkinTestOrder saveSkinTestOrder(SkinTestOrderSaveJSON skinTestOrderSaveJSON) throws Exception {
		int orderId;
		try {
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date startDateTemp = null;
			java.sql.Date startDate = null;
			try {
				startDateTemp = (Date) format.parse(skinTestOrderSaveJSON.getStartDate().toString());
				startDate = new java.sql.Date(startDateTemp.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			SkinTestOrder skinTestOrder = new SkinTestOrder();
			skinTestOrder.setSkinTestOrderPatientId(Integer.parseInt(skinTestOrderSaveJSON.getPatientId().toString()));
			skinTestOrder.setSkinTestOrderEncounterId(Integer.parseInt(skinTestOrderSaveJSON.getEncounterId().toString()));
			skinTestOrder.setSkinTestOrderSkinTestFormShortcutId(Integer.parseInt(skinTestOrderSaveJSON.getSkinTestShortcutId().toString()));
			skinTestOrder.setSkinTestOrderCreatedOn(skinTestOrderRepository.findCurrentTimeStamp());
			skinTestOrder.setSkinTestOrderLastModifiedOn(skinTestOrderRepository.findCurrentTimeStamp());
			skinTestOrder.setSkinTestOrderStatus(skinTestOrderSaveJSON.getStatus());
			skinTestOrder.setSkinTestOrderCreatedBy(skinTestOrderSaveJSON.getLoginId());
			skinTestOrder.setSkinTestOrderLastModifiedBy(skinTestOrderSaveJSON.getLoginId());
			skinTestOrder.setSkinTestOrderStartDate(startDate);
			skinTestOrder.setSkinTestOrderTechnician(Integer.parseInt(skinTestOrderSaveJSON.getTechnician().toString()));
			skinTestOrder.setSkinTestOrderDxCode1(skinTestOrderSaveJSON.getDxCode1());
			skinTestOrder.setSkinTestOrderDxDesc1(skinTestOrderSaveJSON.getDxDesc1());
			skinTestOrder.setSkinTestOrderDxCode2(skinTestOrderSaveJSON.getDxCode2());
			skinTestOrder.setSkinTestOrderDxDesc2(skinTestOrderSaveJSON.getDxDesc2());
			skinTestOrder.setSkinTestOrderDxCode3(skinTestOrderSaveJSON.getDxCode3());
			skinTestOrder.setSkinTestOrderDxDesc3(skinTestOrderSaveJSON.getDxDesc3());
			skinTestOrder.setSkinTestOrderDxCode4(skinTestOrderSaveJSON.getDxCode4());
			skinTestOrder.setSkinTestOrderDxDesc4(skinTestOrderSaveJSON.getDxDesc4());
			skinTestOrder.setSkinTestOrderDxCode5(skinTestOrderSaveJSON.getDxCode5());
			skinTestOrder.setSkinTestOrderDxDesc5(skinTestOrderSaveJSON.getDxDesc5());
			skinTestOrder.setSkinTestOrderDxCode6(skinTestOrderSaveJSON.getDxCode6());
			skinTestOrder.setSkinTestOrderDxDesc6(skinTestOrderSaveJSON.getDxDesc6());
			skinTestOrder.setSkinTestOrderDxCode7(skinTestOrderSaveJSON.getDxCode7());
			skinTestOrder.setSkinTestOrderDxDesc7(skinTestOrderSaveJSON.getDxDesc7());
			skinTestOrder.setSkinTestOrderDxCode8(skinTestOrderSaveJSON.getDxCode8());
			skinTestOrder.setSkinTestOrderDxDesc8(skinTestOrderSaveJSON.getDxDesc8());
			skinTestOrder.setSkinTestOrderDxCode9(skinTestOrderSaveJSON.getDxCode9());
			skinTestOrder.setSkinTestOrderDxDesc9(skinTestOrderSaveJSON.getDxDesc9());
			skinTestOrder.setSkinTestOrderDxCode10(skinTestOrderSaveJSON.getDxCode10());
			skinTestOrder.setSkinTestOrderDxDesc10(skinTestOrderSaveJSON.getDxDesc10());
			skinTestOrder.setSkinTestOrderDxCode11(skinTestOrderSaveJSON.getDxCode11());
			skinTestOrder.setSkinTestOrderDxDesc11(skinTestOrderSaveJSON.getDxDesc11());
			skinTestOrder.setSkinTestOrderDxCode12(skinTestOrderSaveJSON.getDxCode12());
			skinTestOrder.setSkinTestOrderDxDesc12(skinTestOrderSaveJSON.getDxDesc12());
			skinTestOrder.setSkinTestOrderDxCodeSystemId(skinTestOrderSaveJSON.getDxCodeSystem());
			skinTestOrder.setSkinTestOrderMappingTestDetailId(skinTestOrderSaveJSON.getTestDetailId());
			skinTestOrder.setSkinTestOrderOrderingPhysician(skinTestOrderSaveJSON.getServiceDoctor());
			skinTestOrder.setSkinTestOrderOrderingLocation(skinTestOrderSaveJSON.getPos());
			skinTestOrder.setSkinTestOrderNotes(skinTestOrderSaveJSON.getOrderNotes());
			//skinTestOrder.setskinTestOrderDefaultReadValue(skinTestOrderSaveJSON.getDefaultReadValue());
			orderId = skinTestOrderRepository.saveAndFlush(skinTestOrder).getSkinTestOrderId();
			saveShortcutForPatient(skinTestOrderSaveJSON,orderId);
			List<String> concentrationsList = skinTestOrderSaveJSON.getConcentrationList();
			SkinTestOrderEntrySaveJSON entrySaveJSON = new SkinTestOrderEntrySaveJSON();
			entrySaveJSON.setOrderId(orderId);
			entrySaveJSON.setLoginId(skinTestOrderSaveJSON.getLoginId());
			entrySaveJSON.setPatientId(skinTestOrderSaveJSON.getPatientId());
			entrySaveJSON.setEncounterId(skinTestOrderSaveJSON.getEncounterId());
			entrySaveJSON.setTestDate(skinTestOrderSaveJSON.getStartDate());
			entrySaveJSON.setTechnician(skinTestOrderSaveJSON.getTechnician());
			entrySaveJSON.setConcentration(null);
			entrySaveJSON.setUnits(skinTestOrderSaveJSON.getUnits());
			entrySaveJSON.setServiceDoctor(skinTestOrderSaveJSON.getServiceDoctor());
			entrySaveJSON.setPos(skinTestOrderSaveJSON.getPos());
			entrySaveJSON.setNewEntry(true);
			entrySaveJSON.setBilledStatus(0);
			entrySaveJSON.setIsAllergensRecorded(false);
			if(skinTestOrderSaveJSON.getDefaultReadValue()!=""){
				entrySaveJSON.setPrickFlag(false);
				entrySaveJSON.setIntradermalFlag(false);
				Integer Value = Integer.parseInt(skinTestOrderSaveJSON.getDefaultReadValue());
				for(int r=1;r<=Value;r++){
					entrySaveJSON.setDefaultReadValue(r+"");
					saveSkinTestOrderEntry(entrySaveJSON);
				}
			}
			if(skinTestOrderSaveJSON.getPrickFlag()) {
				entrySaveJSON.setPrickFlag(true);
				entrySaveJSON.setIntradermalFlag(false);
				entrySaveJSON.setDefaultReadValue("");
				saveSkinTestOrderEntry(entrySaveJSON);
			}
			int intradermalCount = 0;
			if(skinTestOrderSaveJSON.getIntradermalFlag()) {
				entrySaveJSON.setDefaultReadValue("");
				if(concentrationsList!=null&&concentrationsList.size()>0)
					intradermalCount = concentrationsList.size();
				else
					intradermalCount = 1;
				for(int i=0;i<intradermalCount;i++) {
					entrySaveJSON.setIntradermalFlag(true);
					entrySaveJSON.setPrickFlag(false);
					if(concentrationsList!=null&&concentrationsList.size()>0)
						entrySaveJSON.setConcentration(concentrationsList.get(i));
					saveSkinTestOrderEntry(entrySaveJSON);
				}
			}
			return skinTestOrder;
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * For Saving entry (Prick/intradermal) for an order
	 */
	@Override
	public int saveSkinTestOrderEntry(SkinTestOrderEntrySaveJSON skinTestOrderEntrySaveJSON) throws Exception {
		SkinTestOrderEntry skinTestOrderEntry = new SkinTestOrderEntry();
		skinTestOrderEntry.setSkinTestOrderEntrySkinTestOrderId(skinTestOrderEntrySaveJSON.getOrderId());
		skinTestOrderEntry.setSkinTestOrderEntryEncounterId(Integer.parseInt(skinTestOrderEntrySaveJSON.getEncounterId()));
		skinTestOrderEntry.setSkinTestOrderEntryCreatedBy(skinTestOrderEntrySaveJSON.getLoginId());
		skinTestOrderEntry.setSkinTestOrderEntryConcentration(Optional.fromNullable(Strings.emptyToNull(skinTestOrderEntrySaveJSON.getConcentration())).or(""));
		skinTestOrderEntry.setSkinTestOrderEntryCreatedOn(skinTestOrderRepository.findCurrentTimeStamp());
		skinTestOrderEntry.setSkinTestOrderEntryModifiedBy(skinTestOrderEntrySaveJSON.getLoginId());
		skinTestOrderEntry.setSkinTestOrderEntryModifiedOn(skinTestOrderRepository.findCurrentTimeStamp());
		skinTestOrderEntry.setSkinTestOrderEntryNoOfUnits(skinTestOrderEntrySaveJSON.getUnits());
		skinTestOrderEntry.setSkinTestOrderEntryBilledStatus(skinTestOrderEntrySaveJSON.getBilledStatus());
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date testDateTemp = null;
		java.sql.Date testDate = null;
		try {
			testDateTemp = (Date) format.parse(skinTestOrderEntrySaveJSON.getTestDate());
			testDate = new java.sql.Date(testDateTemp.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		skinTestOrderEntry.setSkinTestOrderEntryTestDate(testDate);
		skinTestOrderEntry.setSkinTestOrderEntryTestPerformedTechnician(skinTestOrderEntrySaveJSON.getTechnician());
		skinTestOrderEntry.setSkinTestOrderEntryDiluentWhealvalue(skinTestOrderEntrySaveJSON.getDiluentWhealValue());
		skinTestOrderEntry.setSkinTestOrderEntryDiluentFlarevalue(skinTestOrderEntrySaveJSON.getDiluentFlareValue());
		skinTestOrderEntry.setSkinTestOrderEntryDiluentGrade(skinTestOrderEntrySaveJSON.getDiluentGrade());
		skinTestOrderEntry.setSkinTestOrderEntryDiluentErythema(skinTestOrderEntrySaveJSON.getDiluentErythema());
		skinTestOrderEntry.setSkinTestOrderEntryDiluentPseudopodia(skinTestOrderEntrySaveJSON.getDiluentPseudopodia());
		skinTestOrderEntry.setSkinTestOrderEntryHistamineWhealvalue(skinTestOrderEntrySaveJSON.getHistamineWhealValue());
		skinTestOrderEntry.setSkinTestOrderEntryHistamineFlarevalue(skinTestOrderEntrySaveJSON.getHistamineFlareValue());
		skinTestOrderEntry.setSkinTestOrderEntryHistamineGrade(skinTestOrderEntrySaveJSON.getHistamineGrade());
		skinTestOrderEntry.setSkinTestOrderEntryHistamineErythema(skinTestOrderEntrySaveJSON.getHistamineErythema());
		skinTestOrderEntry.setSkinTestOrderEntryHistaminePseudopodia(skinTestOrderEntrySaveJSON.getHistaminePseudopodia());
		skinTestOrderEntry.setSkinTestOrderEntryOrderingPhysician(skinTestOrderEntrySaveJSON.getServiceDoctor());
		skinTestOrderEntry.setSkinTestOrderEntryOrderingLocation(skinTestOrderEntrySaveJSON.getPos());
		//skinTestOrderEntry.setSkinTestOrderEntryDefaultRead(skinTestOrderEntrySaveJSON.getDefaultReadValue());
		if(skinTestOrderEntrySaveJSON.getDefaultReadValue()!="" && skinTestOrderEntrySaveJSON.getDefaultReadValue()!=null && skinTestOrderEntrySaveJSON.getDefaultReadValue()!="null"){
			skinTestOrderEntry.setSkinTestOrderEntryTypeOfTest("Reading"+skinTestOrderEntrySaveJSON.getDefaultReadValue());
		}else{
			if(skinTestOrderEntrySaveJSON.getPrickFlag())
				skinTestOrderEntry.setSkinTestOrderEntryTypeOfTest("Prick");
			else {
				skinTestOrderEntry.setSkinTestOrderEntryTypeOfTest("Intradermal");
				skinTestOrderEntry.setSkinTestOrderEntryConcentration(skinTestOrderEntrySaveJSON.getConcentration());
			}
		}
		SkinTestOrderEntry entry = skinTestOrderEntryRepository.saveAndFlush(skinTestOrderEntry);
		SkinTestOrderDetailsSaveJSON skinTestOrderDetailsSaveJSON = new SkinTestOrderDetailsSaveJSON();
		skinTestOrderDetailsSaveJSON.setOrderId(entry.getSkinTestOrderEntrySkinTestOrderId());
		skinTestOrderDetailsSaveJSON.setOrderEntryId(entry.getSkinTestOrderEntryId());
		skinTestOrderDetailsSaveJSON.setLoginId(skinTestOrderEntrySaveJSON.getLoginId());
		skinTestOrderDetailsSaveJSON.setEncounterId(skinTestOrderEntrySaveJSON.getEncounterId());
		List<SkinTestResultJSON> results = new ArrayList<SkinTestResultJSON>();
		SkinTestFormShortcut formShortcut = skinTestFormShortcutRepository.findOne(skinTestOrderRepository.findOne(skinTestOrderEntrySaveJSON.getOrderId()).getSkinTestOrderSkinTestFormShortcutId());
		for(SkinTestFormShortcutCategoryDetails categoryDetails:formShortcut.getSkinTestFormShortcutCategoryDetails()) {
			for(SkinTestFormShortcutAllergenDetails shortcutAllergenDetails:categoryDetails.getSkinTestFormShortcutAllergenDetails()) {
				SkinTestResultJSON skinTestResultJSON = new SkinTestResultJSON();
				skinTestResultJSON.setOrderDetailId(-1);
				skinTestResultJSON.setAllergenId(shortcutAllergenDetails.getSkinTestFormShortcutAllergenDetailsAllergenId());
				SkinTestResult skinTestResult = new SkinTestResult();
				skinTestResult.setconcentrateGroupId(categoryDetails.getSkinTestFormShortcutCategoryDetailsAllergenCategoryId());
				skinTestResult.setErythema(null);
				skinTestResult.setFlare(null);
				skinTestResult.setGrade(null);
				skinTestResult.setPseudopodia(null);
				skinTestResult.setWheal(null);
				skinTestResultJSON.setResult(skinTestResult);
				results.add(skinTestResultJSON);	
			}
		}
		skinTestOrderDetailsSaveJSON.setResults(results);
		SkinTestOrder order = saveSkinTestOrderDetails(skinTestOrderDetailsSaveJSON, true);
		return entry.getSkinTestOrderEntryId();
	}
	
	/**
	 * To save recorded allergen results in the detail level
	 */
	@Override
	public SkinTestOrder saveSkinTestOrderDetails(SkinTestOrderDetailsSaveJSON skinTestOrderDetailsSaveJSON,boolean detailPrimarySave) throws Exception {
		int orderId;
		try {
			orderId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(skinTestOrderDetailsSaveJSON.getOrderId().toString())).or("-1"));
			int orderEntryId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(skinTestOrderDetailsSaveJSON.getOrderEntryId().toString())).or("-1"));
			if(!detailPrimarySave) {
				SkinTestOrderEntry skinTestOrderEntry = skinTestOrderEntryRepository.findOne(orderEntryId);
				skinTestOrderEntry.setSkinTestOrderEntryModifiedBy(skinTestOrderDetailsSaveJSON.getLoginId());
				skinTestOrderEntry.setSkinTestOrderEntryModifiedOn(skinTestOrderRepository.findCurrentTimeStamp());
				skinTestOrderEntry.setSkinTestOrderEntryNoOfUnits(skinTestOrderDetailsSaveJSON.getUnits());
				skinTestOrderEntry.setSkinTestOrderEntryBilledStatus(skinTestOrderDetailsSaveJSON.getBilledStatus());
				skinTestOrderEntry.setSkinTestOrderEntryDiluentWhealvalue(skinTestOrderDetailsSaveJSON.getDiluentWhealValue());
				skinTestOrderEntry.setSkinTestOrderEntryDiluentFlarevalue(skinTestOrderDetailsSaveJSON.getDiluentFlareValue());
				skinTestOrderEntry.setSkinTestOrderEntryDiluentGrade(skinTestOrderDetailsSaveJSON.getDiluentGrade());
				skinTestOrderEntry.setSkinTestOrderEntryDiluentErythema(skinTestOrderDetailsSaveJSON.getDiluentErythema());
				skinTestOrderEntry.setSkinTestOrderEntryDiluentPseudopodia(skinTestOrderDetailsSaveJSON.getDiluentPseudopodia());
				skinTestOrderEntry.setSkinTestOrderEntryHistamineWhealvalue(skinTestOrderDetailsSaveJSON.getHistamineWhealValue());
				skinTestOrderEntry.setSkinTestOrderEntryHistamineFlarevalue(skinTestOrderDetailsSaveJSON.getHistamineFlareValue());
				skinTestOrderEntry.setSkinTestOrderEntryHistamineGrade(skinTestOrderDetailsSaveJSON.getHistamineGrade());
				skinTestOrderEntry.setSkinTestOrderEntryHistamineErythema(skinTestOrderDetailsSaveJSON.getHistamineErythema());
				skinTestOrderEntry.setSkinTestOrderEntryHistaminePseudopodia(skinTestOrderDetailsSaveJSON.getHistaminePseudopodia());
				orderEntryId = skinTestOrderEntryRepository.saveAndFlush(skinTestOrderEntry).getSkinTestOrderEntryId();
			}
			for(SkinTestResultJSON skinTestResultJSON: skinTestOrderDetailsSaveJSON.getResults()) {
				int skinTestOrderDetailsId = skinTestResultJSON.getOrderDetailId();
				
				int allergenId = skinTestResultJSON.getAllergenId();
				SkinTestResult skinTestResult = skinTestResultJSON.getResult();
				SkinTestOrderDetails skinTestOrderDetails;
				if(skinTestOrderDetailsId == -1) {
					skinTestOrderDetails = new SkinTestOrderDetails();
					skinTestOrderDetails.setSkinTestOrderDetailsCreatedBy(skinTestOrderDetailsSaveJSON.getLoginId());
					skinTestOrderDetails.setSkinTestOrderDetailsCreatedOn(skinTestOrderRepository.findCurrentTimeStamp());
					skinTestOrderDetails.setSkinTestOrderDetailsEncounterId(Integer.parseInt(skinTestOrderDetailsSaveJSON.getEncounterId()));
				}
				else
					skinTestOrderDetails = skinTestOrderDetailsRepository.findOne(skinTestOrderDetailsId); 
				skinTestOrderDetails.setSkinTestOrderDetailsAllergenId(allergenId);
				skinTestOrderDetails.setSkinTestOrderDetailsResultValue(skinTestResult.getGrade());
				skinTestOrderDetails.setSkinTestOrderDetailsAllergenCategoryId(skinTestResult.getconcentrateGroupId());
				skinTestOrderDetails.setSkinTestOrderDetailsSkinTestOrderEntryId(orderEntryId);
				skinTestOrderDetails.setSkinTestOrderDetailsWhealvalue(skinTestResult.getWheal());
				skinTestOrderDetails.setSkinTestOrderDetailsFlarevalue(skinTestResult.getFlare());
				skinTestOrderDetails.setSkinTestOrderDetailsErythema(skinTestResult.isErythema());
				skinTestOrderDetails.setSkinTestOrderDetailsPseudopodia(skinTestResult.isPseudopodia());
				skinTestOrderDetails.setSkinTestOrderDetailsModifiedBy(skinTestOrderDetailsSaveJSON.getLoginId());
				skinTestOrderDetails.setSkinTestOrderDetailsModifiedOn(skinTestOrderRepository.findCurrentTimeStamp());
				skinTestOrderDetails.setSkinTestOrderDetailsReadValue(skinTestResult.getReadValue());
				skinTestOrderDetailsRepository.saveAndFlush(skinTestOrderDetails);
			}
			SkinTestOrder skinTestOrder = skinTestOrderRepository.findOne(orderId);
			/*for(SkinTestOrderEntry orderEntry:skinTestOrder.getSkinTestOrderEntries()) {
				System.out.println(">>"+orderEntry.getSkinTestOrderEntryConcentration());
				for(SkinTestOrderDetails orderDetails: orderEntry.getSkinTestOrderDetails()) {
					
				}
			}*/
			return skinTestOrder;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
	}

	/**
	 * To save evaluation text based on the recorded results of all allergens in the form
	 */
	@Override
	public SkinTestOrder updateSkinTestOrderEvaluationText(Integer orderId,JSONArray list,int modifiedBy) {
		ArrayList<Integer> listdata = new ArrayList<Integer>();
		try {
			if (list != null) { 
				for (int i=0;i<list.length();i++) { 
					listdata.add(Integer.parseInt(list.get(i).toString()));
				} 
			} 
		} catch (NumberFormatException | JSONException e) {
			e.printStackTrace();
		}
		
		List<String> allergensWithPositiveResult = new ArrayList<String>();
		String evalutionText = "";
		if(listdata.size()>0){
			evalutionText="Skin testing was performed with normal positive and negative controls, showing significant reactivity to ";
			for(int i=0;i<listdata.size();i++) {
				allergensWithPositiveResult.add(concentrateRepository.findOne(listdata.get(i)).getConcentrateName());
			}
			for(int i=0;i<allergensWithPositiveResult.size();i++) {
				if(i==allergensWithPositiveResult.size()-1)
					evalutionText += allergensWithPositiveResult.get(i);
				else
					evalutionText += allergensWithPositiveResult.get(i)+", ";
			}
		} else {
			evalutionText="There was no significant reactivity to the allergens tested.";
		}
		SkinTestOrder skinTestOrder = skinTestOrderRepository.getOne(orderId);
		skinTestOrder.setSkinTestOrderEvaluationText(evalutionText);
		return skinTestOrderRepository.saveAndFlush(skinTestOrder);
	}

	/**
	 * To save the result comments of an order
	 */
	@Override
	public SkinTestOrder updateSkinTestOrderResultComments(Integer orderId,String resultComments,int modifiedBy) {
		SkinTestOrder skinTestOrder = skinTestOrderRepository.getOne(orderId);
		skinTestOrder.setSkinTestOrderResultComments(resultComments);
		skinTestOrder.setSkinTestOrderLastModifiedBy(modifiedBy);
		return skinTestOrderRepository.saveAndFlush(skinTestOrder);
	}
	
	/**
	 * To update the billed status at entry level
	 */
	public List<SkinTestOrderEntry> updateSkinTestEntryBilledStatus(JSONArray orderEntryIds,int billedStatus,int loginId) {
		ArrayList<Integer> listdata = new ArrayList<Integer>();
		try {
			if (orderEntryIds != null) { 
				for (int i=0;i<orderEntryIds.length();i++) { 
					listdata.add(Integer.parseInt(orderEntryIds.get(i).toString()));
				} 
			} 
		} catch (NumberFormatException | JSONException e) {
			e.printStackTrace();
		}
		List<SkinTestOrderEntry> skinTestOrderEntries = skinTestOrderEntryRepository.findAll(listdata);
		for(SkinTestOrderEntry skinTestOrderEntry:skinTestOrderEntries) {
			skinTestOrderEntry.setSkinTestOrderEntryBilledStatus(billedStatus);
			skinTestOrderEntry.setSkinTestOrderEntryModifiedBy(loginId);
			skinTestOrderEntry.setSkinTestOrderEntryModifiedOn(skinTestOrderRepository.findCurrentTimeStamp());
			skinTestOrderEntryRepository.saveAndFlush(skinTestOrderEntry);
		}
		return skinTestOrderEntries;
	}
	
	/**
	 * To get all the skin test orders of the patient
	 */
	@Override
	public List<SkinTestOrder> getSkinTestOrders(Integer patientId) {
		List<SkinTestOrder> skinTestOrders = skinTestOrderRepository.findAll(SkinTestingFormSpecification.getSkinTestOrders(patientId));
		for(SkinTestOrder order:skinTestOrders) {
			order.getOrderStatus();
			order.getSkinTestFormShortcut().getSkinTestFormShortcutName();
			order.getOrderingPhysician().getEmpProfileFullname();
		}
		return skinTestOrders;
	}
	
	/**
	 * 
	 */
	@Override
	public SkinTestOrderEntry getSkinTestOrderEntry(int entryId) {
		SkinTestOrderEntry entry = skinTestOrderEntryRepository.findOne(entryId);
		for(SkinTestOrderDetails details: entry.getSkinTestOrderDetails()) {
			details.getSkinTestOrderDetailsAllergenId();
		}
		return entry;
	}
	
	/**
	 * To get the all details of an order
	 */
	@Override
	public SkinTestOrderBean getSkinTestOrderDetails(Integer orderId) {
		SkinTestOrder skinTestOrder = skinTestOrderRepository.findOne(SkinTestingFormSpecification.getSkinTestOrder(orderId));
		skinTestOrder.getSkinTestFormShortcut().getSkinTestFormShortcutName();
		if(skinTestOrder.getCompletedBy()!=null)
			skinTestOrder.getCompletedBy().getEmpProfileFullname();
		if(skinTestOrder.getReviewedBy()!=null)
			skinTestOrder.getReviewedBy().getEmpProfileFullname();
		for(SkinTestOrderEntry orderEntry:skinTestOrder.getSkinTestOrderEntries()) {
			for(SkinTestOrderDetails orderDetails: orderEntry.getSkinTestOrderDetails()) {
			}
		}
		SkinTestOrderBean orderBean = new SkinTestOrderBean();
		orderBean.setSkinTestOrder(skinTestOrder);
		SkinTestOrderShortCutBean orderShortCutBean = new SkinTestOrderShortCutBean();
		orderShortCutBean.setOrderId(skinTestOrder.getSkinTestOrderId());
		List<SkinTestOrderAllergenCategory> orderAllergenCategories = orderAllergenCategoryRepository.findAll(SkinTestingFormSpecification.getSkinTestOrderAllergenCategories(orderId));
		for(SkinTestOrderAllergenCategory category:orderAllergenCategories) {
			category.getSkinTestOrderAllergenCategoryConcentrateGroupName();
			for(SkinTestOrderAllergen allergen: category.getSkinTestOrderAllergens()){
				allergen.getSkinTestOrderAllergenConcentrateName(); 
			}
		}
		
		orderShortCutBean.setAllergenCategories(orderAllergenCategories);
		orderBean.setShortcutBean(orderShortCutBean);
		return orderBean;
	}

	/**
	 *  To get Dx and pending labs of an patient
	 */
	@Override
	public DxAndPendingOrdersBean getDxAndPendingOrders(Integer patientId, Integer encounterId,Integer chartId) {
		List<PatientAssessments> list1=h611Repository.findAll(SkinTestingFormSpecification.getcodingsystems(encounterId,chartId));
		for(PatientAssessments h611: list1) { 
			h611.getpatient_assessmentsCodingSystemid();
		}
		List<ProblemList> problemList=problemListRepository.findAll(SkinTestingFormSpecification.getproblemlist(patientId));
		for(ProblemList problemList2: problemList) {
			problemList2.getProblemListCodingSystemid();
		}
		
		List<LabEntries> pendingOrders = labEntriesRepository.findAll(Specifications.where(SkinTestingFormSpecification.pendingLabs(encounterId,chartId)));

		try{
		for(int z=0;z<list1.size()-1;z++){
			String dxcode1=list1.get(z).getpatient_assessments_dxcode();
			String oid=list1.get(z).getpatient_assessmentsCodingSystemid();
			for(int z1=z+1;z1<list1.size();z1++){
				String dxcode2=list1.get(z1).getpatient_assessments_dxcode();
				String oid2=list1.get(z1).getpatient_assessmentsCodingSystemid();
				if(((dxcode1.replaceAll("\\s+","")).equalsIgnoreCase((dxcode2).replaceAll("\\s+","")))&&(oid.replaceAll("\\s+","").equalsIgnoreCase(oid2.replaceAll("\\s+","")))){
					if(list1.get(z).getpatient_assessments_id()>list1.get(z1).getpatient_assessments_id()){
						list1.remove(list1.get(z1));
					}else{
						list1.remove(list1.get(z));
						z--;
					}
				}

			}

		}

		for(int z=0;z<problemList.size()-1;z++){
			String dxcode1=problemList.get(z).getProblemListDxCode();
			for(int z1=z+1;z1<problemList.size();z1++){
				String dxcode2=problemList.get(z1).getProblemListDxCode();
				if((dxcode1.replaceAll("\\s+","")).equalsIgnoreCase((dxcode2).replaceAll("\\s+",""))){
					if(problemList.get(z).getProblemListId()>problemList.get(z1).getProblemListId()){
						problemList.remove(problemList.get(z1));
					}else{
						problemList.remove(problemList.get(z));
						z--;
					}
				}


			}

		}

		for(int i=0;i<problemList.size();i++){
			String dxcode=problemList.get(i).getProblemListDxCode();
			for(int j=0;j<list1.size();j++){
				String dxcode1=list1.get(j).getpatient_assessments_dxcode();
				if(dxcode.replaceAll("\\s+","").equalsIgnoreCase(dxcode1.replaceAll("\\s+",""))){
					if(problemList.get(i).getProblemListId()>list1.get(j).getpatient_assessments_id()){
						list1.remove(list1.get(j));
					}else{
						problemList.remove(problemList.get(i));
					}
				}
			}
		}}catch(Exception e) {
			e.printStackTrace();
		}
		DxAndPendingOrdersBean dxAndPendingOrdersBean = new DxAndPendingOrdersBean();
		dxAndPendingOrdersBean.setdxList(list1);
		dxAndPendingOrdersBean.setProblemList(problemList);
		dxAndPendingOrdersBean.setPendingOrders(pendingOrders);
		return dxAndPendingOrdersBean;
	}

	/**
	 * To get the patient's service or principle doctor
	 */
	@Override
	public EmployeeProfile getPatientServiceDoctor(Integer patientId,Integer encounterId) {
		EmployeeProfile employeeProfile = null;
		if(encounterId != -1) {
			Encounter encounter = encounterRepository.findOne(encounterId);
			employeeProfile = employeeProfileRepository.findOne(encounter.getEncounterServiceDoctor().intValue());
		} else {
			PatientRegistration patientRegistration =  patientRegistrationRepository.findOne(patientId);
			employeeProfile = employeeProfileRepository.findOne(patientRegistration.getPatientRegistrationPrincipalDoctor());
		}
		return employeeProfile;
	}
	
	/**
	 * To get the patient's POS
	 */
	@Override
	public PosTable getPatientPOS(Integer patientId, Integer encounterId) {
		PosTable pos;
		if(encounterId != -1) {
			Encounter encounter = encounterRepository.findOne(encounterId);
			pos = posRepository.findOne(encounter.getEncounterPos());
		} else {
			PatientRegistration patientRegistration =  patientRegistrationRepository.findOne(patientId);
			pos = posRepository.findOne(patientRegistration.getPatientRegistrationPosId());
		}
		return pos;
	}

	/**
	 * To get the default test concentrations
	 */
	@Override
	public List<SkinTestConcentration> getTestConcentrations() {
		List<SkinTestConcentration> skinTestConcentrations = skinTestConcentrationRepository.findAll(SkinTestingFormSpecification.getTestConcentrations());
		return skinTestConcentrations;
	}

	/**
	 * To save the review operation of an order by the physician
	 */
	@Override
	public SkinTestOrder reviewSkinTestOrder(Integer orderId, Integer reviewedBy) {
		SkinTestOrder skinTestOrder = skinTestOrderRepository.findOne(orderId);
		skinTestOrder.setSkinTestOrderStatus(2);
		if(skinTestOrder.getSkinTestOrderCompletedOn()==null) {
			skinTestOrder.setSkinTestOrderCompletedBy(reviewedBy);
			skinTestOrder.setSkinTestOrderCompletedOn(skinTestOrderRepository.findCurrentTimeStamp());
		}
		skinTestOrder.setSkinTestOrderReviewedBy(reviewedBy);
		skinTestOrder.setSkinTestOrderReviewedOn(skinTestOrderRepository.findCurrentTimeStamp());
		skinTestOrder.setSkinTestOrderLastModifiedBy(reviewedBy);
		skinTestOrder.setSkinTestOrderLastModifiedOn(skinTestOrderRepository.findCurrentTimeStamp());
		skinTestOrder.getReviewedBy();
		return skinTestOrder;
	}
	
	/**
	 * Completing the order by the technician
	 */
	@Override
	public SkinTestOrder completeSkinTestOrder(Integer orderId, Integer completedBy) {
		SkinTestOrder skinTestOrder = skinTestOrderRepository.findOne(orderId);
		skinTestOrder.setSkinTestOrderStatus(3);
		skinTestOrder.setSkinTestOrderCompletedBy(completedBy);
		skinTestOrder.setSkinTestOrderCompletedOn(skinTestOrderRepository.findCurrentTimeStamp());
		skinTestOrder.setSkinTestOrderLastModifiedBy(completedBy);
		skinTestOrder.setSkinTestOrderLastModifiedOn(skinTestOrderRepository.findCurrentTimeStamp());
		skinTestOrder.getCompletedBy();
		return skinTestOrder;
	}

	/**
	 * To edit the skin test order
	 */
	@Override
	public SkinTestOrder editSkinTestOrder(SkinTestOrderSaveJSON skinTestOrderSaveJSON) throws Exception {
		int orderId = skinTestOrderSaveJSON.getOrderId();
		try {
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date startDateTemp = null;
			java.sql.Date startDate = null;
			try {
				startDateTemp = (Date) format.parse(skinTestOrderSaveJSON.getStartDate().toString());
				startDate = new java.sql.Date(startDateTemp.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			SkinTestOrder skinTestOrder = skinTestOrderRepository.findOne(orderId);
			skinTestOrder.setSkinTestOrderLastModifiedOn(skinTestOrderRepository.findCurrentTimeStamp());
			skinTestOrder.setSkinTestOrderLastModifiedBy(skinTestOrderSaveJSON.getLoginId());
			skinTestOrder.setSkinTestOrderStartDate(startDate);
			skinTestOrder.setSkinTestOrderTechnician(Integer.parseInt(skinTestOrderSaveJSON.getTechnician().toString()));
			skinTestOrder.setSkinTestOrderDxCode1(skinTestOrderSaveJSON.getDxCode1());
			skinTestOrder.setSkinTestOrderDxDesc1(skinTestOrderSaveJSON.getDxDesc1());
			skinTestOrder.setSkinTestOrderDxCode2(skinTestOrderSaveJSON.getDxCode2());
			skinTestOrder.setSkinTestOrderDxDesc2(skinTestOrderSaveJSON.getDxDesc2());
			skinTestOrder.setSkinTestOrderDxCode3(skinTestOrderSaveJSON.getDxCode3());
			skinTestOrder.setSkinTestOrderDxDesc3(skinTestOrderSaveJSON.getDxDesc3());
			skinTestOrder.setSkinTestOrderDxCode4(skinTestOrderSaveJSON.getDxCode4());
			skinTestOrder.setSkinTestOrderDxDesc4(skinTestOrderSaveJSON.getDxDesc4());
			skinTestOrder.setSkinTestOrderDxCode5(skinTestOrderSaveJSON.getDxCode5());
			skinTestOrder.setSkinTestOrderDxDesc5(skinTestOrderSaveJSON.getDxDesc5());
			skinTestOrder.setSkinTestOrderDxCode6(skinTestOrderSaveJSON.getDxCode6());
			skinTestOrder.setSkinTestOrderDxDesc6(skinTestOrderSaveJSON.getDxDesc6());
			skinTestOrder.setSkinTestOrderDxCode7(skinTestOrderSaveJSON.getDxCode7());
			skinTestOrder.setSkinTestOrderDxDesc7(skinTestOrderSaveJSON.getDxDesc7());
			skinTestOrder.setSkinTestOrderDxCode8(skinTestOrderSaveJSON.getDxCode8());
			skinTestOrder.setSkinTestOrderDxDesc8(skinTestOrderSaveJSON.getDxDesc8());
			skinTestOrder.setSkinTestOrderDxCode9(skinTestOrderSaveJSON.getDxCode9());
			skinTestOrder.setSkinTestOrderDxDesc9(skinTestOrderSaveJSON.getDxDesc9());
			skinTestOrder.setSkinTestOrderDxCode10(skinTestOrderSaveJSON.getDxCode10());
			skinTestOrder.setSkinTestOrderDxDesc10(skinTestOrderSaveJSON.getDxDesc10());
			skinTestOrder.setSkinTestOrderDxCode11(skinTestOrderSaveJSON.getDxCode11());
			skinTestOrder.setSkinTestOrderDxDesc11(skinTestOrderSaveJSON.getDxDesc11());
			skinTestOrder.setSkinTestOrderDxCode12(skinTestOrderSaveJSON.getDxCode12());
			skinTestOrder.setSkinTestOrderDxDesc12(skinTestOrderSaveJSON.getDxDesc12());
			skinTestOrder.setSkinTestOrderMappingTestDetailId(skinTestOrderSaveJSON.getTestDetailId());
			skinTestOrder.setSkinTestOrderOrderingPhysician(skinTestOrderSaveJSON.getServiceDoctor());
			skinTestOrder.setSkinTestOrderOrderingLocation(skinTestOrderSaveJSON.getPos());
			skinTestOrder.setSkinTestOrderNotes(skinTestOrderSaveJSON.getOrderNotes());
			return skinTestOrder;
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * To edit the order entry date from results recording screen
	 */
	@Override
	public SkinTestOrderEntry editSkinTestOrderEntryDate(int orderEntryId,String newDate,int modifiedBy) {
		SkinTestOrderEntry orderEntry = skinTestOrderEntryRepository.findOne(orderEntryId);
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date dateTemp = null;
		java.sql.Date testDate = null;
		try {
			dateTemp = (Date) format.parse(newDate);
			testDate = new java.sql.Date(dateTemp.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		orderEntry.setSkinTestOrderEntryTestDate(testDate);
		orderEntry.setSkinTestOrderEntryModifiedBy(modifiedBy);
		orderEntry.setSkinTestOrderEntryModifiedOn(skinTestOrderRepository.findCurrentTimeStamp());
		return null;
	}

	/*@Override
	public void getReviewedUserSignDetails(int reviewedBy) {
		Doctorsign sign = doctorSignRepository.findOne(SkinTestingFormSpecification.getReviewedUserSignDetails(reviewedBy));
		System.out.println(">>>>>>>>>>>>>>>>"+sign.getFilename());
	}*/
	
}