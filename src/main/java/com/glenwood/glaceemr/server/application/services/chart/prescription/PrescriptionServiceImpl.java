package com.glenwood.glaceemr.server.application.services.chart.prescription;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.AlertCategory;
import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.AppReferenceValues;
import com.glenwood.glaceemr.server.application.models.CoreClassGendrug;
import com.glenwood.glaceemr.server.application.models.CoreClassGendrug_;
import com.glenwood.glaceemr.server.application.models.CoreClassHierarchy;
import com.glenwood.glaceemr.server.application.models.CoreClassHierarchy_;
import com.glenwood.glaceemr.server.application.models.CoreGenproduct;
import com.glenwood.glaceemr.server.application.models.CoreGenproduct_;
import com.glenwood.glaceemr.server.application.models.CurrentMedication;
import com.glenwood.glaceemr.server.application.models.DrugSchedule;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.MedStatus;
import com.glenwood.glaceemr.server.application.models.MedStatus_;
import com.glenwood.glaceemr.server.application.models.MedsAdminLog;
import com.glenwood.glaceemr.server.application.models.MedsAdminPlan;
import com.glenwood.glaceemr.server.application.models.MedsAdminPlanShortcut;
import com.glenwood.glaceemr.server.application.models.NdcPkgProduct;
import com.glenwood.glaceemr.server.application.models.NdcPkgProduct_;
import com.glenwood.glaceemr.server.application.models.PatientPortalAlertConfig;
import com.glenwood.glaceemr.server.application.models.PatientPortalRefillRequest;
import com.glenwood.glaceemr.server.application.models.PatientPortalRefillRequest_;
import com.glenwood.glaceemr.server.application.models.PharmacyFilterBean;
import com.glenwood.glaceemr.server.application.models.PharmacyMapping;
import com.glenwood.glaceemr.server.application.models.PharmacyMapping_;
import com.glenwood.glaceemr.server.application.models.PortalRefillRequestBean;
import com.glenwood.glaceemr.server.application.models.Prescription;
import com.glenwood.glaceemr.server.application.models.Prescription_;
import com.glenwood.glaceemr.server.application.repositories.AlertCategoryRepository;
import com.glenwood.glaceemr.server.application.repositories.AlertEventRepository;
import com.glenwood.glaceemr.server.application.repositories.AppReferenceValuesRepository;
import com.glenwood.glaceemr.server.application.repositories.CurrentMedicationRepository;
import com.glenwood.glaceemr.server.application.repositories.DrugScheduleRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterRepository;
import com.glenwood.glaceemr.server.application.repositories.MedAdministrationLogRepository;
import com.glenwood.glaceemr.server.application.repositories.MedAdministrationPlanRepository;
import com.glenwood.glaceemr.server.application.repositories.MedAdministrationPlanShortcutRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientPortalAlertConfigRespository;
import com.glenwood.glaceemr.server.application.repositories.PatientPortalRefillRequestRepository;
import com.glenwood.glaceemr.server.application.repositories.PharmDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.PharmacyMappingRepository;
import com.glenwood.glaceemr.server.application.repositories.PrescriptionRepository;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.specifications.AlertCategorySpecification;
import com.glenwood.glaceemr.server.application.specifications.EncounterSpecification;
import com.glenwood.glaceemr.server.application.specifications.PharmacyFilterSpecification;
import com.glenwood.glaceemr.server.application.specifications.PortalAlertSpecification;
import com.glenwood.glaceemr.server.application.specifications.PrescripitonSpecification;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

  
@Service
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService{
	@Autowired
	PrescriptionRepository prescriptionRepository;
	
	@Autowired
	CurrentMedicationRepository currentMedicationRepository;
	
	@Autowired
	DrugScheduleRepository drugScheduleRepository;
	
	
	@Autowired
	MedAdministrationPlanRepository medAdminPlanRepository;
	
	@Autowired
	MedAdministrationLogRepository medAdminLogRepository;
	
	@Autowired
	MedAdministrationPlanShortcutRepository medAdministrationPlanShortcutRepository;
	
	@Autowired
	AppReferenceValuesRepository App_Reference_ValuesRepository;
	
	@Autowired
	EncounterRepository encounterRepository;
	
	@Autowired
	AlertEventRepository alertEventRepository;
	
	@Autowired
	PharmDetailsRepository pharmDetailsRepository;
	
	@Autowired
	PatientPortalRefillRequestRepository patient_portal_refill_requestRepository;
	
	@Autowired
	PharmacyMappingRepository pharmacyMappingRepository;
	
	@Autowired
	AlertCategoryRepository alertCategoryRepository;
	
	@Autowired
	PatientPortalAlertConfigRespository patient_portal_alert_configRespository;
	
	@PersistenceContext
	EntityManager em;

	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;
	/**
	 * To get the prescribed medications by the doctor
	 */
	@Override
	public Map<String, Object> getactivemedwithclass(Integer patientid) {
		List<CurrentMedication> currentmedlist=currentMedicationRepository.findAll(PrescripitonSpecification.getactivemedwithclass(patientid,null,null));
		List<Prescription> prescmedlist=prescriptionRepository.findAll(PrescripitonSpecification.getactivemedwithclasspresc(patientid,null,null));
		Map<String, Object> mapobject=new HashMap<String,Object>();
		mapobject.put("currentmed", currentmedlist);
		mapobject.put("prescmed", prescmedlist);
		return mapobject;
	}

	/**
	 * To save the medications plan (Currently this is for medication log used by Behavioural health practices)
	 */
	@Override
	public void saveMedicationAdminPlan(String dataToSave) throws JSONException {
	       JSONObject medPlanData = new JSONObject(dataToSave);
	       int patientId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(medPlanData.get("patientId").toString())).or("-1"));
	       int prescId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(medPlanData.get("prescId").toString())).or("-1"));
	       int userId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(medPlanData.get("loginId").toString())).or("-1"));
	       String planTime =  Optional.fromNullable(Strings.emptyToNull(medPlanData.get("planTimeList").toString())).or("-1");
	       String shortcut =  Optional.fromNullable(Strings.emptyToNull(medPlanData.get("shortcut").toString())).or("-1");
	       String planTimeList[] = null;
			try {
				if(planTime!=null&&planTime!=null) {
					planTimeList = planTime.split(",");
				}
			} catch (Exception e) {
				System.out.println("Exception in plan time set up \n"+e);
			}
			for(int i=0;i<planTimeList.length;i++) {
				String planTimeTemp = planTimeList[i].replace("[", "").replace("]", "").trim();
				MedsAdminPlan newPlan = new MedsAdminPlan();
			    newPlan.setMedsAdminPlanMedicationId(prescId);
			    newPlan.setMedsAdminPlanPatientId(patientId);
			    newPlan.setMedsAdminPlanTime(planTimeTemp);
			    newPlan.setMedsAdminPlanSavedTime(medAdminLogRepository.findCurrentTimeStamp());
			    newPlan.setMedsAdminPlanOrderedBy(userId);
			    newPlan.setMedsAdminPlanModifiedOn(medAdminLogRepository.findCurrentTimeStamp());
			    newPlan.setMedsAdminPlanModifiedBy(userId);
			    newPlan.setMedsAdminPlanIsActive(true);
			    newPlan.setShortcut(shortcut);
			    medAdminPlanRepository.saveAndFlush(newPlan);
			}
	}

	/**
	 * To save the administration details of a patient. (Currently this is for medication log used by Behavioural health practices)
	 */
	@Override
	public void saveMedicationAdminLog(String dataToSave) throws Exception {
		JSONObject medAdminLogData = new JSONObject(dataToSave);
	    int patientId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(medAdminLogData.get("patientId").toString())).or("-1"));
	    int planId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(medAdminLogData.get("planId").toString())).or("-1"));
	    int administeredBy = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(medAdminLogData.get("administeredBy").toString())).or("-1"));
	    String actualplanDate = Optional.fromNullable(Strings.emptyToNull(medAdminLogData.get("actualplanDate").toString())).or("");
	    String adminDate = Optional.fromNullable(Strings.emptyToNull(medAdminLogData.get("adminDate").toString().replace("text", " "))).or("");
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    Date parsedDate = dateFormat.parse(adminDate);
	    
	   
	    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	    int logId = -1;
	    boolean isDuplicate = Boolean.parseBoolean(Optional.fromNullable(Strings.emptyToNull(medAdminLogData.get("isDuplicate").toString())).or("false"));
	    if(isDuplicate) 
	    	logId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(medAdminLogData.get("logId").toString())).or("-1"));
	    int medAdminCategory = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(medAdminLogData.get("selectedAdminCategory").toString())).or("-1"));
	    String adminNotes = Optional.fromNullable(Strings.emptyToNull(medAdminLogData.get("adminNotes").toString())).or("");
		MedsAdminLog newAdminLog = new MedsAdminLog();
		newAdminLog.setMedsAdminLogPatientId(patientId);
		if(logId!= -1)
			newAdminLog.setMedsAdminLogRouteId(logId);
		else
			newAdminLog.setMedsAdminLogRouteId(-1);
		newAdminLog.setMedsAdminLogPlanId(planId);
		newAdminLog.setMedsAdminLogActualPlanDate(actualplanDate);
		newAdminLog.setMedsAdminLogAdministeredBy(administeredBy);
		newAdminLog.setMedsAdminLogAdministrationDate(adminDate);
		newAdminLog.setMedsAdminLogModifiedBy(administeredBy);
		newAdminLog.setMedsAdminLogModifiedDate(medAdminLogRepository.findCurrentTimeStamp());
		newAdminLog.setMedsAdminLogIsDeleted(false);
		newAdminLog.setMedsAdminLogAdminCategory(medAdminCategory);
		newAdminLog.setMedsAdminLogAdminNotes(adminNotes);
//		newAdminLog.setShortcut(shortcut);
		MedsAdminLog savedLog = medAdminLogRepository.saveAndFlush(newAdminLog);
		if(logId==-1) {
			int logRouteId = savedLog.getMedsAdminLogId();
			savedLog.setMedsAdminLogRouteId(logRouteId);
			medAdminLogRepository.saveAndFlush(savedLog);
		}	
	}


	/**
	 * To get the log history of a medication plan (Currently this is for medication log used by Behavioural health practices).
	 */
	@Override
	public List<MedsAdminLog> getMedicationAdminLogHistory(int planId) {
		List<MedsAdminLog> administeredlist=medAdminLogRepository.findAll(PrescripitonSpecification.getMedAdministrationLogHistoryData(planId));
		return administeredlist;
	}


	/**
	 * To delete the administered medication log (Currently this is for medication log used by Behavioural health practices).
	 */
	@Override
	public void deleteMedicationAdminLog(int deletedBy,int logId) {
		MedsAdminLog adminLog = medAdminLogRepository.findOne(logId);
		adminLog.setMedsAdminLogIsDeleted(true);
		adminLog.setMedsAdminLogDeletedOn(medAdminLogRepository.findCurrentTimeStamp());
		adminLog.setMedsAdminLogDeletedBy(deletedBy);
		adminLog.setMedsAdminLogModifiedBy(deletedBy);
		adminLog.setMedsAdminLogModifiedDate(medAdminLogRepository.findCurrentTimeStamp());
		medAdminLogRepository.saveAndFlush(adminLog);
	}

	/**
	 * To get the particular log history (Currently this is for medication log used by Behavioural health practices)
	 */
	@Override
	public List getMedicationAdminLog(int logId) {
		List<MedsAdminLog> administeredlist=medAdminLogRepository.findAll(PrescripitonSpecification.getMedAdministrationLogData(logId));
		return administeredlist;
	}



	/**
	 * To get the patient's active medications between two dates
	 */
	@Override
	public Map<String, Object> getOneMonthActiveMeds(Integer patientid,
			Integer whichMonth,Integer year) {
		Calendar startDate = Calendar.getInstance();
		startDate.set(year, whichMonth, 1);
		int endDay = startDate.getActualMaximum(Calendar.DAY_OF_MONTH); 
		if(Calendar.getInstance().get(Calendar.MONTH)==whichMonth)
			endDay = Calendar.getInstance().get(Calendar.DATE);
		Calendar endDate = Calendar.getInstance();
		endDate.set(year, whichMonth, endDay);
//		List<CurrentMedication> currentmedlist=currentMedicationRepository.findAll(PrescripitonSpecification.getactivemedwithclass(patientid,startDate.getTime(),endDate.getTime()));
		List<CurrentMedication> currentmedlist= new ArrayList<CurrentMedication>();
		List<Prescription> prescmedlist=prescriptionRepository.findAll(PrescripitonSpecification.getactivemedwithclasspresc(patientid,startDate.getTime(),endDate.getTime()));
		for(CurrentMedication obj:currentmedlist) {
			obj.setMedsAdminPlan(medAdminPlanRepository.findAll(PrescripitonSpecification.getMedsPlanIds(obj.getCurrentMedicationId())));
		}
		for(Prescription obj:prescmedlist) {
			obj.setMedsAdminPlan(medAdminPlanRepository.findAll(PrescripitonSpecification.getMedsPlanIds(obj.getDocPrescId())));
		}
		Map<String, Object> mapobject=new HashMap<String,Object>();
		mapobject.put("currentmed", currentmedlist);
		mapobject.put("prescmed", prescmedlist);
		return mapobject;
	}

	/**
	 * To edit the medication plan (Currently this is for medication log used by Behavioural health practices)
	 */
	@Override
	public void editMedicationAdminPlan(String dataToSave) throws JSONException {
	       JSONObject medPlanData = new JSONObject(dataToSave);
	       int patientId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(medPlanData.get("patientId").toString())).or("-1"));
	       int prescId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(medPlanData.get("prescId").toString())).or("-1"));
	       int planId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(medPlanData.get("planId").toString())).or("-1"));
	       boolean isPlanDeleted = Boolean.parseBoolean(Optional.fromNullable(Strings.emptyToNull(medPlanData.get("isPlanDeleted").toString())).or("false"));
	       int userId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(medPlanData.get("loginId").toString())).or("-1"));
	       String planTime =  Optional.fromNullable(Strings.emptyToNull(medPlanData.get("planTimeList").toString())).or("");
	       String planTimeList[] = null;
	       if(isPlanDeleted) {
	    	   MedsAdminPlan newPlan = medAdminPlanRepository.findOne(planId);
	    	   newPlan.setMedsAdminPlanModifiedOn(medAdminLogRepository.findCurrentTimeStamp());
	    	   newPlan.setMedsAdminPlanModifiedBy(userId);
	    	   newPlan.setMedsAdminPlanIsActive(false);
	    	   newPlan.setMedsAdminPlanDeletedBy(userId);
	    	   newPlan.setMedsAdminPlanDeletedOn(medAdminLogRepository.findCurrentTimeStamp());
	    	   medAdminPlanRepository.saveAndFlush(newPlan);
		   }
			try {
				if(planTime!=null&&planTime!="") {
					planTimeList = planTime.split(",");
				}
			} catch (Exception e) {
				System.out.println("Exception in plan time set up \n"+e);
			}
			if(planTimeList.length>0) {
				for(int i=0;i<planTimeList.length;i++) {
					String planTimeTemp = planTimeList[i].replace("[", "").replace("]", "").trim();
				if (!planTimeTemp.equalsIgnoreCase("")) {
					MedsAdminPlan newPlan = new MedsAdminPlan();
					newPlan.setMedsAdminPlanMedicationId(prescId);
					newPlan.setMedsAdminPlanPatientId(patientId);
					newPlan.setMedsAdminPlanTime(planTimeTemp);
					newPlan.setMedsAdminPlanIsActive(true);
					newPlan.setMedsAdminPlanSavedTime(medAdminLogRepository.findCurrentTimeStamp());
					newPlan.setMedsAdminPlanModifiedOn(medAdminLogRepository.findCurrentTimeStamp());
					newPlan.setMedsAdminPlanModifiedBy(userId);
					medAdminPlanRepository.saveAndFlush(newPlan);
				}
			}
		}
	}
	
	/**
	 * To get the medical supplies for the particular patient
	 * @param patientid
	 * throws {@link Exception}
	 */
	@Override
	public PrescriptionBean getactivemedicalsupplies(Integer patientid) {
		List<CurrentMedication> currentmedlist=currentMedicationRepository.findAll(PrescripitonSpecification.getactivemedicalsupplieswithclass(patientid,null,null));
		List<Prescription> prescmedlist=prescriptionRepository.findAll(PrescripitonSpecification.getactivemedicalsupplieswithclasspresc(patientid,null,null));
		PrescriptionBean beanobj=new PrescriptionBean(prescmedlist, currentmedlist);
		/*	Map<String, Object> mapobject=new HashMap<String,Object>();
		mapobject.put("currentmed", currentmedlist);
		mapobject.put("prescmed", prescmedlist);*/
		return beanobj;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.glenwood.glaceemr.server.application.services.chart.prescription.PrescriptionService#getfrequencylist(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DrugSchedule> getfrequencylist(String brandname,String mode) {
		// TODO Auto-generated method stub
		List<DrugSchedule> schedulelist=drugScheduleRepository.findAll(PrescripitonSpecification.getfrequencylist(brandname,mode));
		return schedulelist;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.glenwood.glaceemr.server.application.services.chart.prescription.PrescriptionService#getfrequencylistall(java.lang.String, java.lang.String)
	 */
	@Override
	public List<DrugSchedule> getfrequencylistall(String brandname,String mode) {
		// TODO Auto-generated method stub
		List<DrugSchedule> schedulelist=drugScheduleRepository.findAll(PrescripitonSpecification.getfrequencylist(brandname,mode));
		return schedulelist;
	}

	/*
	 * (non-Javadoc)
	 * @see com.glenwood.glaceemr.server.application.services.chart.prescription.PrescriptionService#gettake(java.lang.String)
	 */
	@Override
	public List<IntakeBean> gettake(String brandname) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Prescription> root = cq.from(Prescription.class);
		cq.multiselect(builder.construct(IntakeBean.class, root.get(Prescription_.docPrescIntake),
				root.get(Prescription_.docPrescIntake)));
		cq.where(builder.like((builder.lower(root.get(Prescription_.rxname))),getLikePattern(brandname)));
		cq.orderBy(builder.asc(root.get(Prescription_.docPrescIntake)));
		cq.distinct(true);
		List<Object> dd=em.createQuery(cq).getResultList();
		List<IntakeBean> bean1 = new ArrayList<IntakeBean>();
		for(int i=0;i<dd.size();i++){
		IntakeBean takebean=(IntakeBean)dd.get(i);
		String name=takebean.getName();
		String value=takebean.getValue();
		takebean.setName(name);
		takebean.setValue(value);
		bean1.add(takebean);
		}
		
		return bean1;
	}
	
	
	/**
	 * Retunrs the formatted the pattern
	 * @param searchTerm
	 * @return
	 */
	private static String getLikePattern(final String searchTerm) {
		StringBuilder pattern = new StringBuilder();
		pattern.append(searchTerm.toLowerCase());
		pattern.append("%");
		return pattern.toString();
	}
	
	/**
	 * To modify the administered medication log notes
	 */
	@Override
	public void updateMedicationAdminLogNotes(int modifiedBy, int logId,String notes) {
		MedsAdminLog adminLog = medAdminLogRepository.findOne(logId);
		adminLog.setMedsAdminLogModifiedDate(medAdminLogRepository.findCurrentTimeStamp());
		adminLog.setMedsAdminLogModifiedBy(modifiedBy);
		adminLog.setMedsAdminLogAdminNotes(notes);
		medAdminLogRepository.saveAndFlush(adminLog);
	}

	/**
	 * To get the medication administration plan shortcuts
	 */
	@Override
	public List<MedsAdminPlanShortcut> getMedAdminPlanShortcuts() {
		List<MedsAdminPlanShortcut> shortcutsList = medAdministrationPlanShortcutRepository.findAll(PrescripitonSpecification.getshortcuts());
		return shortcutsList;
	}
	
	@Override
	public PharmacyFilterBean getPharmacyList(PharmacyFilterBean pharmacyFilterBean) {
		
		pharmacyFilterBean.setPharmacyList(pharmDetailsRepository.findAll(PharmacyFilterSpecification.getPharmaciesBy(pharmacyFilterBean),
				PharmacyFilterSpecification.createPharmacyPaginationRequest(pharmacyFilterBean.getPageIndex(), pharmacyFilterBean.getPageOffset())).getContent());
		
		pharmacyFilterBean.setTotalPharamaciesCount(pharmDetailsRepository.count(PharmacyFilterSpecification.getPharmaciesBy(pharmacyFilterBean)));
		
		return pharmacyFilterBean;
	}

	@Override
	public EMRResponseBean requestRefill(PortalRefillRequestBean requestBean) {
		
		String requestedMedicine="";
		
		Timestamp requestedDate=new Timestamp(new Date().getTime());
		
		int requestId=getNewPortalRefillRequestId();		
		
		for(int i=0;i<requestBean.getPrecriptionList().size();i++){
		
		PatientPortalRefillRequest prescriptionMed=new PatientPortalRefillRequest();
		prescriptionMed.setpatient_portal_refill_request_refill_id(requestId);
		prescriptionMed.setpatient_portal_refill_request_patient_id(requestBean.getPatientId());
		prescriptionMed.setpatient_portal_refill_request_zhtml(1);
		prescriptionMed.setpatient_portal_refill_request_requested_date(requestedDate.toString());
		prescriptionMed.setpatient_portal_refill_request_prescription_id(requestBean.getPrecriptionList().get(i).getPrescriptionId());
		prescriptionMed.setpatient_portal_refill_request_status(1);
		prescriptionMed.setpatient_portal_refill_request_flag(0);
		
		requestedMedicine = requestedMedicine.equalsIgnoreCase("") ? "" : requestedMedicine + ",";
		requestedMedicine = requestedMedicine+requestBean.getPrecriptionList().get(i).getPrescriptionName()+" ["+requestBean.getPrecriptionList().get(i).getPrescriptionDosage()+"]";
		
		patient_portal_refill_requestRepository.saveAndFlush(prescriptionMed);
		}
		
		String requestComments = "Request Type : Refill requested from patient portal \r\n Medication : "+requestedMedicine+"\r\n Comments : "+requestBean.getComments()+" \r\n Suggested Pharmacy : "+requestBean.getPharmacyName();
		
		PharmacyMapping pharmacy=new PharmacyMapping();
		pharmacy.setPharmacyMappingMapid(getNewPharmacyMappingId());
		pharmacy.setPharmacyMappingPatid(requestBean.getPatientId());
		pharmacy.setPharmacyMappingPharmacyid(requestBean.getPharmacyId());
		pharmacy.setPharmacyMappingUsrGrpid(2);
		
		pharmacyMappingRepository.saveAndFlush(pharmacy);
		
		Encounter requestEncounter=new Encounter();
		requestEncounter.setEncounterChartid(requestBean.getChartId());
		requestEncounter.setEncounterDate(requestedDate);
		requestEncounter.setEncounterComments(requestComments);
		requestEncounter.setEncounterChargeable(true);
		requestEncounter.setEncounterAlreadySeen(false);
		requestEncounter.setEncounterType(Short.valueOf("3"));
		requestEncounter.setEncounterReason(getEncounterReasonId("Refills", 2));
		requestEncounter.setEncounterStatus(Short.valueOf("1"));
		
		requestEncounter=encounterRepository.saveAndFlush(requestEncounter);
		
		String requestInfo      = "Request " + requestedMedicine + " for Refillment as on " + requestedDate;
		
		PatientPortalAlertConfig paymentAlertCategory=patient_portal_alert_configRespository.findOne(PortalAlertSpecification.getPortalAlertCategoryByName("Refill Request"));
		boolean sendToAll =paymentAlertCategory.getpatient_portal_alert_config_send_to_all();  
		int provider = Integer.parseInt(paymentAlertCategory.getpatient_portal_alert_config_provider());
		int forwardTo = Integer.parseInt(paymentAlertCategory.getpatient_portal_alert_config_forward_to());

		AlertCategory alertCategory=alertCategoryRepository.findOne(AlertCategorySpecification.getAlertCategoryByName("Refill Request"));
		
		AlertEvent alert=new AlertEvent();
		alert.setAlertEventCategoryId(alertCategory.getAlertCategoryId());
		alert.setAlertEventStatus(1);
		alert.setAlertEventPatientId(requestBean.getPatientId());
		alert.setAlertEventPatientName(requestBean.getUsername());
		alert.setAlertEventEncounterId(requestEncounter.getEncounterId());
		alert.setAlertEventRefId(Integer.parseInt(String.valueOf(requestId)));
		alert.setAlertEventMessage(requestInfo);
		alert.setAlertEventRoomId(-1);
		alert.setAlertEventCreatedDate(new Timestamp(new Date().getTime()));
		alert.setAlertEventModifiedby(-100);
		alert.setAlertEventFrom(-100);


		if(provider==0 && forwardTo==0)
			alert.setAlertEventTo(-1);
		else {
			if(sendToAll){
				if(forwardTo!=provider){
					alert.setAlertEventTo(forwardTo);
					AlertEvent alert2=alert;
					alert2.setAlertEventTo(provider);
					alertEventRepository.saveAndFlush(alert2);
				} else {
					alert.setAlertEventTo(forwardTo);
				}            	 
			}else{
				if(forwardTo!=0){
					alert.setAlertEventTo(forwardTo);
				} else {
					alert.setAlertEventTo(provider);
				}
			}
		}

		alertEventRepository.saveAndFlush(alert);
		
		

		EMRResponseBean reposnseBean=new EMRResponseBean();
		reposnseBean.setCanUserAccess(true);
		reposnseBean.setLogin(true);
		reposnseBean.setIsAuthorizationPresent(true);
		reposnseBean.setData("Refill request sent successfully");
		
		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.CREATE,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Patient with id "+requestBean.getPatientId()+" made a refill request.",-1,
				request.getRemoteAddr(),requestBean.getPatientId(),"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with id "+requestBean.getPatientId()+" made a refill request.","");
		
		return reposnseBean;
	}
	
	public Integer getNewPortalRefillRequestId() {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PatientPortalRefillRequest> root = cq.from(PatientPortalRefillRequest.class);
		cq.select(builder.max(root.get(PatientPortalRefillRequest_.patient_portal_refill_request_refill_id)));
		Integer requestId=(Integer) em.createQuery(cq).getSingleResult();

		return requestId+1;
	}
	
	public Integer getNewPharmacyMappingId() {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PharmacyMapping> root = cq.from(PharmacyMapping.class);
		cq.select(builder.max(root.get(PharmacyMapping_.pharmacyMappingMapid)));
		Integer mapId=(Integer) em.createQuery(cq).getSingleResult();

		return mapId+1;
	}
	
	public Integer getEncounterReasonId(String reasonType, int reasonGroup) {

		AppReferenceValues encounterReason=App_Reference_ValuesRepository.findOne(EncounterSpecification.getEncounterReasonId(reasonType, reasonGroup));
		return encounterReason.getApp_Reference_Values_statusId();
	}

	@Override
	public List<Prescription> getPatientRefillRequestMedications(int patientId, int chartId) {
		
		List<Prescription> medList=prescriptionRepository.findAll(PrescripitonSpecification.getPatientCompletedMedications(patientId, chartId));
		
		return medList;
	}

	@Override
	public List<Encounter> getPatientRefillRequestHistory(int patientId, int chartId) {
		
		return encounterRepository.findAll(PrescripitonSpecification.getRefillRequestHistory(3, getEncounterReasonId("Refills", 2), chartId));		
	}
	
	
	public List<PrescriptionServiceBean> getMedDetailsWithClass(EntityManager em1,Integer patientId,
            String flowsheetDrugClassId, Integer encounterId,
            Encounter encounterEntity) {
        CriteriaBuilder builder1 = em1.getCriteriaBuilder();
        CriteriaQuery<Object> cq1 = builder1.createQuery();
        Root<Prescription> root1 = cq1.from(Prescription.class);
        // TODO Auto-generated method stub
           Join<Prescription,Encounter> currjoin1=root1.join(Prescription_.encounter,JoinType.INNER);
           Join<Prescription,MedStatus> currjoin=root1.join(Prescription_.medstatus,JoinType.INNER);
           currjoin1.join(Encounter_.chart, JoinType.INNER);
           Join<Prescription, NdcPkgProduct> pkgjoin=root1.join(Prescription_.ndcPkgProduct,JoinType.INNER);
           Join<NdcPkgProduct, CoreGenproduct> corejoin=pkgjoin.join(NdcPkgProduct_.coregenproduct,JoinType.INNER);
           Join<CoreGenproduct, CoreClassGendrug> classjoin=corejoin.join(CoreGenproduct_.coreclassgendrug,JoinType.INNER);
           Join<CoreClassGendrug, CoreClassHierarchy> hierarchyjon=classjoin.join(CoreClassGendrug_.coreClassHierarchyTable,JoinType.INNER);
           String likePattern = getLikePattern("active");
           Predicate patientId1=builder1.equal(root1.get(Prescription_.docPrescPatientId), patientId);
           Predicate isactive=builder1.equal(root1.get(Prescription_.docPrescIsActive),true);
           Predicate ismedsup=builder1.equal(root1.get(Prescription_.docPrescIsMedSup),false);
           Predicate medstatus=builder1.like(builder1.lower(currjoin.get(MedStatus_.medStatusGroup)),likePattern);
           Predicate classIdPres=hierarchyjon.get(CoreClassHierarchy_.parentClassId).in(flowsheetDrugClassId);
           /*root1.fetch(Prescription_.medstatus,JoinType.INNER);
           root1.fetch(Prescription_.encounter,JoinType.INNER);*/
           if((encounterId!=-1)&&(encounterEntity.getEncounterStatus()==3)){
               Predicate encounterDatePred=builder1.lessThanOrEqualTo(root1.get(Prescription_.docPrescOrderedDate), encounterEntity.getEncounterDate());
               cq1.where(builder1.and(patientId1, isactive,medstatus,ismedsup,classIdPres,encounterDatePred));
           }else{
               cq1.where(builder1.and(patientId1, isactive,medstatus,ismedsup,classIdPres));
           }
           //System.out.println("medstatus"+medstatus+"patientId1"+patientId+"isactive"+isactive+"classIdPres"+flowsheetDrugClassId+"medstatus"+likePattern);
           cq1.multiselect(builder1.construct(PrescriptionServiceBean.class,
                   root1.get(Prescription_.rxname),
                   root1.get(Prescription_.docPrescOrderedDate),
                   root1.get(Prescription_.docPrescStatus),
                   root1.get(Prescription_.docPrescEncounterId),
                   root1.get(Prescription_.rxform),
                   root1.get(Prescription_.rxstrength),
                   root1.get(Prescription_.docPrescComments) ));
          // System.out.println("cq.................."+cq1);
       List<Object> pre=em1.createQuery(cq1).getResultList();
       List<PrescriptionServiceBean> pre1=new ArrayList<PrescriptionServiceBean>();
       for(int i1=0;i1<pre.size();i1++){
           PrescriptionServiceBean inner=(PrescriptionServiceBean)pre.get(i1);
           String brandname=inner.getBrandName();
          // System.out.println("brandname???????????????????????????"+brandname);
           Date ordereddate = inner.getOrderedDate();
           Integer status=inner.getStatus();
           Integer encounterid=inner.getEncounterId();
           String form=inner.getForm();
           String strength=inner.getDose();
           String comments=inner.getComments();
           PrescriptionServiceBean inner1=new PrescriptionServiceBean(brandname, ordereddate, status, encounterid,form,strength,comments);
           inner1.setBrandName(brandname);
           inner1.setEncounterId(encounterid);
           inner1.setStatus(status);
           inner1.setOrderedDate(ordereddate);
           inner1.setComments(comments);
           inner1.setDose(strength);
           inner1.setForm(form);
           pre1.add(inner1);

       }

       return pre1;
    }

}

