package com.glenwood.glaceemr.server.application.services.chart.ErxSummary;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.Brandname;
import com.glenwood.glaceemr.server.application.models.Brandname_;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.DeaSchedule;
import com.glenwood.glaceemr.server.application.models.DeaSchedule_;
import com.glenwood.glaceemr.server.application.models.DrugDetails;
import com.glenwood.glaceemr.server.application.models.DrugForm;
import com.glenwood.glaceemr.server.application.models.DrugForm_;
import com.glenwood.glaceemr.server.application.models.DrugRelationMap;
import com.glenwood.glaceemr.server.application.models.DrugRelationMap_;
import com.glenwood.glaceemr.server.application.models.EmpProfileAgentConfig;
import com.glenwood.glaceemr.server.application.models.EmpProfileAgentConfig_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.InitialSettings;
import com.glenwood.glaceemr.server.application.models.InitialSettings_;
import com.glenwood.glaceemr.server.application.models.LeafPatient;
import com.glenwood.glaceemr.server.application.models.LocationDetails;
import com.glenwood.glaceemr.server.application.models.LocationDetails_;
import com.glenwood.glaceemr.server.application.models.ModifiedDose;
import com.glenwood.glaceemr.server.application.models.ModifiedDose_;
import com.glenwood.glaceemr.server.application.models.NdcDrugBrandMap;
import com.glenwood.glaceemr.server.application.models.NdcDrugBrandMap_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PharmDetails;
import com.glenwood.glaceemr.server.application.models.PharmDetails_;
import com.glenwood.glaceemr.server.application.models.PrescriberDetails;
import com.glenwood.glaceemr.server.application.models.PrescriberDetails_;
import com.glenwood.glaceemr.server.application.models.Prescription;
import com.glenwood.glaceemr.server.application.models.PrescriptionUnits;
import com.glenwood.glaceemr.server.application.models.PrescriptionUnits_;
import com.glenwood.glaceemr.server.application.models.Prescription_;
import com.glenwood.glaceemr.server.application.models.RefillMessageType;
import com.glenwood.glaceemr.server.application.models.RefillMessageType_;
import com.glenwood.glaceemr.server.application.models.SSInbox;
import com.glenwood.glaceemr.server.application.models.SSInbox_;
import com.glenwood.glaceemr.server.application.models.SSOutbox;
import com.glenwood.glaceemr.server.application.models.SSOutbox_;
import com.glenwood.glaceemr.server.application.models.SSPatientMessageMap;
import com.glenwood.glaceemr.server.application.models.SSPatientMessageMap_;
import com.glenwood.glaceemr.server.application.models.SSStatusErrorCodes;
import com.glenwood.glaceemr.server.application.models.SSStatusErrorCodes_;
import com.glenwood.glaceemr.server.application.models.SsPatEligibilityDesc;
import com.glenwood.glaceemr.server.application.models.SsPatEligibilityDesc_;
import com.glenwood.glaceemr.server.application.repositories.PharmDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.PrescriptionRepository;
import com.glenwood.glaceemr.server.application.specifications.ErxSummarySpecification;

@Service
@Transactional
public class ErxSummaryServiceImp implements ErxSummaryService {
	
	@Autowired
	PharmDetailsRepository pharmDetailsRepository;
	
	@Autowired
	PrescriptionRepository prescriptionRepository;
		
	@Autowired
	EntityManagerFactory emf ;

	@PersistenceContext
	EntityManager em;
	
	
	/**
	 * Getting Patient pharmacy details
	 */
	@Override
	public PharmacyBean getPatientPharmacy(int patientId){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PatientRegistration> root = cq.from(PatientRegistration.class);
		Integer pharmId=null;
		PharmacyBean result=null;
		try{
		cq.select(root.get(PatientRegistration_.patientRegistrationPharmacyId)).where(builder.equal(root.get(PatientRegistration_.patientRegistrationId),patientId));
		List<Object> obj=em.createQuery(cq).getResultList();
		if(!obj.isEmpty())
			pharmId= Integer.parseInt(obj.get(0).toString());
	
		if(pharmId!=null){
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Object> query = cb.createQuery();
			Root<PharmDetails> pharmRoot = query.from(PharmDetails.class);
			query.select(cb.construct(PharmacyBean.class, pharmRoot.get(PharmDetails_.pharmDetailsStoreName),
				pharmRoot.get(PharmDetails_.pharmDetailsAddressLine1),
				pharmRoot.get(PharmDetails_.pharmDetailsCity),
				pharmRoot.get(PharmDetails_.pharmDetailsState),
				pharmRoot.get(PharmDetails_.pharmDetailsZip),
				pharmRoot.get(PharmDetails_.pharmDetailsFax),
				pharmRoot.get(PharmDetails_.pharmDetailsPrimaryPhone),
				cb.lower(cb.coalesce(pharmRoot.get(PharmDetails_.pharmDetailsPartnerAccount),"")),
				cb.selectCase().when(cb.and(cb.lessThanOrEqualTo(pharmRoot.get(PharmDetails_.pharmDetailsActiveStartTime), pharmDetailsRepository.findCurrentTimeStamp()),cb.greaterThan(pharmRoot.get(PharmDetails_.pharmDetailsActiveEndTime), pharmDetailsRepository.findCurrentTimeStamp())), pharmRoot.get(PharmDetails_.pharmDetailsIsSsPharm)).otherwise(0),
				pharmRoot.get(PharmDetails_.pharmDetailsVersion),
				pharmRoot.get(PharmDetails_.pharmDetailsServiceLevel),
				cb.trim(cb.concat(cb.concat(cb.concat(cb.concat(cb.coalesce(pharmRoot.get(PharmDetails_.pharmDetailsSpecialtytype1),""), " "), cb.concat(cb.coalesce(pharmRoot.get(PharmDetails_.pharmDetailsSpecialtytype2),""), " ")), cb.concat(cb.coalesce(pharmRoot.get(PharmDetails_.pharmDetailsSpecialtytype3),""), " ")), cb.coalesce(pharmRoot.get(PharmDetails_.pharmDetailsSpecialtytype4),""))),
				pharmRoot.get(PharmDetails_.pharmDetailsId))).where(cb.equal(pharmRoot.get(PharmDetails_.pharmDetailsId),pharmId));
		
			List<Object> object=em.createQuery(query).getResultList();
			if(!object.isEmpty())
				result=(PharmacyBean) object.get(0);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	 * Getting provider details
	 */
	@Override
	public String getProvider(int userId,int chartUserGroupId,int encounterId) {
		int eRxEnable=0;
		String erxEnabledUser="false";
		String agent="false";
		String serviceDr="false";
		Integer prescriberId=userId;
		boolean userFlag=false;
		userFlag=getUserRegisteredStatus(userId);
		if(userFlag){
			eRxEnable=1;
			erxEnabledUser="true";
		}
		List<Integer> agentUserFor=getAgentIds(userId);
		if(chartUserGroupId==(-1) || chartUserGroupId==(-10)){
			if(eRxEnable==1)
				prescriberId=userId;
			else if(agentUserFor.size()>0){
				prescriberId=agentUserFor.get(0);
				agent="true";
			}
		}
		else{
			prescriberId= (int) getServiceDoctor(encounterId);
			serviceDr="true";
		}
		
		List<Prescription> updateList=prescriptionRepository.findAll(ErxSummarySpecification.getUpdateList(encounterId));
		List<Prescription> objlist=new ArrayList<Prescription>();
		for(int i=0;i<updateList.size();i++){
			
			Prescription obj= updateList.get(i);
			obj.setDocPrescProviderId(prescriberId);
			prescriptionRepository.saveAndFlush(obj);
			objlist.add(obj);
		}
		String csallowprovider=getInitialValues("Allow controlled substance for provider");
		String csallowerx=getInitialValues("Allow controlled Substance Drugs through eRx?");
		JSONObject providerObj=new JSONObject();
		try {
			providerObj.put("csallowerx",csallowerx);
			providerObj.put("prescriberId", prescriberId) ;
			providerObj.put("eRxEnable",eRxEnable);
			providerObj.put("erxEnabledUser",erxEnabledUser);
			providerObj.put("agent",agent);
			providerObj.put("serviceDr",serviceDr);
			providerObj.put("csallowprovider",csallowprovider);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return providerObj.toString();
	}
	
	@Override
	public int getMaxEncounterId(int patientId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Chart> root = cq.from(Chart.class);
		cq.select(root.get(Chart_.chartId)).where(builder.equal(root.get(Chart_.chartPatientid),patientId));
		List<Object> chartData=em.createQuery(cq).getResultList();
		int chartId=-1;
		if(!chartData.isEmpty())
			chartId=(int) chartData.get(0);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> query = cb.createQuery();
		Root<Encounter> rootEnc = query.from(Encounter.class);
		query.select(cb.greatest(rootEnc.get(Encounter_.encounterId))).where(cb.equal(rootEnc.get(Encounter_.encounterChartid), chartId));
		List<Object> EncData=em.createQuery(query).getResultList();
		int encId=-1;
		if(!EncData.isEmpty())
			encId=(int) EncData.get(0);
		
		return encId;
	}


	/**
	 * Getting data from initial settings
	 */
	private String getInitialValues(String name) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<InitialSettings> root = cq.from(InitialSettings.class);
		cq.select(root.get(InitialSettings_.initialSettingsOptionValue)).where(builder.like(root.get(InitialSettings_.initialSettingsOptionName), name));
		List<Object> obj=em.createQuery(cq).getResultList();
		String result=null;
		if(!obj.isEmpty())
			result=obj.get(0).toString();
		return result;
	}
	

	/**
	 * Getting Patient's service doctor for that particular encounter
	 */
	private long getServiceDoctor(int encounterId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Encounter> root = cq.from(Encounter.class);
		cq.select(root.get(Encounter_.encounter_service_doctor)).where(builder.equal(root.get(Encounter_.encounterId),encounterId));
		long serviceDoctor=-1;
		Object obj=em.createQuery(cq).getSingleResult();
		if(obj!=null) 
			serviceDoctor=(long)obj ;
		return serviceDoctor;
	}

	
	/**
	 * Getting user's agent Ids 
	 */
	private List<Integer> getAgentIds(int userId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<EmpProfileAgentConfig> root = cq.from(EmpProfileAgentConfig.class);
		Join<EmpProfileAgentConfig, EmployeeProfile> agentEmpJoin=root.join(EmpProfileAgentConfig_.employeeProfile,JoinType.INNER);
		agentEmpJoin.on(builder.equal(agentEmpJoin.get(EmployeeProfile_.empProfileIsActive),true));
		cq.select(root.get(EmpProfileAgentConfig_.empProfileAgentConfigEmpid)).where(builder.equal(root.get(EmpProfileAgentConfig_.empProfileAgentConfigAgentId),userId));
		List<Object> resultList= em.createQuery(cq).getResultList();
		List<Integer> agentIdList= new ArrayList<Integer>();
		for(int i=0;i<resultList.size();i++){
			int obj=(int) resultList.get(i);
			agentIdList.add(obj);
		}
		
		return agentIdList;
	}

	/**
	 * Getting Patient details for erx summary data
	 */
	@Override
	public List<ErxPatientDataBean> getPatientData(final int patientId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PatientRegistration> root = cq.from(PatientRegistration.class);
//		Join<PatientRegistration, StateConfigTable> patBillingJoin=root.join(PatientRegistration_.stateConfigTable,JoinType.LEFT);
//		patBillingJoin.on(builder.equal(patBillingJoin.get(StateConfigTable_.stateConfigTableLookupId),5001));
		
		cq.select(builder.construct(ErxPatientDataBean.class, root.get(PatientRegistration_.patientRegistrationId),
				builder.trim(root.get(PatientRegistration_.patientRegistrationAccountno)),
				root.get(PatientRegistration_.patientRegistrationSsn),
				root.get(PatientRegistration_.patientRegistrationLastName),
				root.get(PatientRegistration_.patientRegistrationMidInitial),
				root.get(PatientRegistration_.patientRegistrationFirstName),
				builder.function("to_char", String.class, root.get(PatientRegistration_.patientRegistrationDob),builder.literal("YYYY-MM-DD")),
				root.get(PatientRegistration_.patientRegistrationSex),
				builder.selectCase().when(builder.gt(builder.function("char_length", Integer.class, builder.trim(builder.coalesce(root.get(PatientRegistration_.patientRegistrationAddress1), ""))),35),builder.substring(builder.trim(builder.coalesce(root.get(PatientRegistration_.patientRegistrationAddress1), "")), 1, 35)).otherwise(builder.trim(builder.coalesce(root.get(PatientRegistration_.patientRegistrationAddress1), ""))),
				builder.selectCase().when(builder.gt(builder.function("char_length", Integer.class, builder.trim(builder.coalesce(root.get(PatientRegistration_.patientRegistrationAddress2), ""))),35),builder.substring(builder.trim(builder.coalesce(root.get(PatientRegistration_.patientRegistrationAddress2), "")), 1, 35)).otherwise(builder.trim(builder.coalesce(root.get(PatientRegistration_.patientRegistrationAddress2), ""))),
				builder.selectCase().when(builder.le(builder.function("char_length", Integer.class, builder.trim(builder.coalesce(root.get(PatientRegistration_.patientRegistrationAddress2), ""))),0),builder.trim(builder.coalesce(root.get(PatientRegistration_.patientRegistrationAddress2), ""))).otherwise("AD2"),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationCity),""),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationStateName),""),
				builder.coalesce(builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationZip),builder.literal("-"),builder.literal(""))),""),
				root.get(PatientRegistration_.patientRegistrationMailId),
				builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationPhoneNo),builder.literal("-"),builder.literal(""))),
				builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationCellno),builder.literal("-"),builder.literal(""))),
				builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationFaxno),builder.literal("-"),builder.literal(""))),
				builder.selectCase().when(builder.gt(builder.function("char_length", Integer.class, builder.trim(builder.coalesce(root.get(PatientRegistration_.patientRegistrationWorkNo), ""))),13),
						builder.selectCase().when(builder.or(builder.and(builder.notEqual(builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationPhoneNo),builder.literal("-"),builder.literal(""))), ""), root.get(PatientRegistration_.patientRegistrationPhoneNo).isNotNull()), builder.and(builder.notEqual(builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationCellno),builder.literal("-"),builder.literal(""))), ""),root.get(PatientRegistration_.patientRegistrationCellno).isNotNull())),
								builder.coalesce(builder.concat(builder.concat(builder.substring(builder.function("replace", String.class, root.get(PatientRegistration_.patientRegistrationWorkNo),builder.literal("-"),builder.literal("")), 1, 10), builder.literal("X")),builder.concat(builder.literal(" "), builder.function("split_part", String.class, builder.trim(builder.coalesce(root.get(PatientRegistration_.patientRegistrationWorkNo), "")),builder.literal("-"),builder.literal(4)))),"")).otherwise("")).otherwise(
										builder.selectCase().when(builder.or(builder.and(builder.notEqual(builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationPhoneNo),builder.literal("-"),builder.literal(""))), ""), root.get(PatientRegistration_.patientRegistrationPhoneNo).isNotNull()), builder.and(builder.notEqual(builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationCellno),builder.literal("-"),builder.literal(""))), ""),root.get(PatientRegistration_.patientRegistrationCellno).isNotNull())), 
												builder.selectCase().when(builder.and(builder.notEqual(builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationWorkNo),builder.literal("-"),builder.literal(""))), ""), root.get(PatientRegistration_.patientRegistrationWorkNo).isNotNull()),builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationWorkNo),builder.literal("-"),builder.literal(""))).otherwise("")).otherwise("")),
				builder.selectCase().when(builder.or(builder.equal(builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationPhoneNo),builder.literal("-"),builder.literal(""))),""),root.get(PatientRegistration_.patientRegistrationPhoneNo).isNull()), builder.coalesce(root.get(PatientRegistration_.patientRegistrationPhoneNo), "")).otherwise("TE"),
				builder.selectCase().when(builder.or(builder.and(builder.notEqual(builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationPhoneNo),builder.literal("-"),builder.literal(""))), ""),root.get(PatientRegistration_.patientRegistrationPhoneNo).isNotNull()),builder.equal(builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationCellno),builder.literal("-"),builder.literal(""))), ""),root.get(PatientRegistration_.patientRegistrationCellno).isNull()), 
						builder.selectCase().when(builder.and(builder.equal(builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationCellno),builder.literal("-"),builder.literal(""))), ""),root.get(PatientRegistration_.patientRegistrationCellno).isNull()), builder.coalesce(root.get(PatientRegistration_.patientRegistrationCellno), "")).otherwise("CP")).otherwise("TE"),
				builder.selectCase().when(builder.or(builder.equal(builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationFaxno),builder.literal("-"),builder.literal(""))), ""),root.get(PatientRegistration_.patientRegistrationFaxno).isNull()), builder.coalesce(root.get(PatientRegistration_.patientRegistrationFaxno), "")).otherwise("FX"),
				builder.selectCase().when(builder.or(builder.and(builder.notEqual(builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationPhoneNo),builder.literal("-"),builder.literal(""))), ""), root.get(PatientRegistration_.patientRegistrationPhoneNo).isNotNull()), builder.and(builder.notEqual(builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationCellno),builder.literal("-"),builder.literal(""))), ""),root.get(PatientRegistration_.patientRegistrationCellno).isNotNull())), 
						builder.selectCase().when(builder.and(builder.equal(builder.trim(builder.function("replace", String.class,root.get(PatientRegistration_.patientRegistrationPhoneNo),builder.literal("-"),builder.literal(""))), ""), root.get(PatientRegistration_.patientRegistrationPhoneNo).isNull()), builder.coalesce(root.get(PatientRegistration_.patientRegistrationPhoneNo), "")).otherwise("WP")).otherwise(""),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationSiblingsAges),""),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationSuffix),""),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationExtrafldXml),""),
				builder.function("to_char", String.class, root.get(PatientRegistration_.patientRegistrationDob),builder.literal("MM/DD/YYYY")),
				builder.selectCase().when(builder.equal(root.get(PatientRegistration_.patientRegistrationGuarantorrel), 0), 1).when(builder.equal(root.get(PatientRegistration_.patientRegistrationGuarantorrel), 1), 2).when(root.get(PatientRegistration_.patientRegistrationGuarantorrel).in(7,8,9,10),3).otherwise(4)));
				cq.where(builder.equal(root.get(PatientRegistration_.patientRegistrationId), patientId));
		List<Object> resultList= em.createQuery(cq).getResultList();
		List<ErxPatientDataBean> patientList=new ArrayList<ErxPatientDataBean>();
		ErxPatientDataBean patientData=(ErxPatientDataBean) resultList.get(0);
		patientList.add(patientData);
		return patientList;
	}

	
	/**
	 * Getting doctor details for erx summary data
	 */
	@Override
	public List<DoctorDetailBean> getDoctorDetails(int encounterId,int userId,int chartUserGroupId,int pos) {
		String doctorSPIStr="";
    	String DoctorSPI="";
    	boolean isUserRegistered=false;
    	int doctorId = -1;
    	short encounterType=0;
    	String encounterReason=""; 
		List<Object[]> obj=getEncounterObject(encounterId);
		if(!obj.isEmpty()){
			encounterType=(short) obj.get(0)[0];
			encounterReason= obj.get(0)[1].toString();
		}
		int InboxId = getInboxId(encounterId);
		if(encounterType == 3 && encounterReason.equals("44")){
			long size=0;
			doctorSPIStr=getSPI(InboxId);
			doctorId=getPrescriberId(doctorSPIStr);
			size=getPrescriberList(doctorId);
			if(size<=0){
				doctorId=getProviderDoctorId(encounterId);
			}
			size=getPrescriberList(doctorId);
			if(size<=0){
				doctorId=getPrincipleDoctorId(encounterId);
			}
		}
		else
		{
			isUserRegistered=getUserRegisteredStatus(userId);
			if((chartUserGroupId==(-1) || chartUserGroupId==(-10)) && isUserRegistered)
				doctorId=userId;
			else
				doctorId=getProviderDoctorId(encounterId);
		}
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PrescriberDetails> root = cq.from(PrescriberDetails.class);
		Join<PrescriberDetails, LocationDetails> prescLocJoin=root.join(PrescriberDetails_.locationDetails,JoinType.INNER);
		Join<LocationDetails, EmployeeProfile>  locEmpJoin=prescLocJoin.join(LocationDetails_.empProfileLoc,JoinType.INNER);
		if(!isUserRegistered){
			 locEmpJoin.on(builder.equal(locEmpJoin.get(EmployeeProfile_.empProfileEmpid),userId));
		}
		
		Integer posId;
		if(getUserPosId(pos,userId)==null)
			posId=minLocationId(userId);
		else
			posId=pos;
		
		prescLocJoin.on(builder.equal(prescLocJoin.get(LocationDetails_.posId), posId));
		cq.select(builder.construct(DoctorDetailBean.class, prescLocJoin.get(LocationDetails_.prescriberId),
				prescLocJoin.get(LocationDetails_.locationId),
				prescLocJoin.get(LocationDetails_.spiLocationid),
				locEmpJoin.get(EmployeeProfile_.empProfileEmpid),
				root.get(PrescriberDetails_.lastName),
				builder.coalesce(root.get(PrescriberDetails_.middleName),""),
				builder.coalesce(root.get(PrescriberDetails_.salutation),""),
				root.get(PrescriberDetails_.firstName),
				builder.coalesce(root.get(PrescriberDetails_.suffix),""),
				prescLocJoin.get(LocationDetails_.addressline1),
				builder.selectCase().when(builder.equal(builder.trim(prescLocJoin.get(LocationDetails_.addressline2)),""),prescLocJoin.get(LocationDetails_.addressline2)).otherwise("AD2"),
				builder.selectCase().when(builder.notEqual(builder.trim(prescLocJoin.get(LocationDetails_.addressline2)),""),prescLocJoin.get(LocationDetails_.addressline2)).otherwise(""),
				builder.coalesce(prescLocJoin.get(LocationDetails_.city),""),
				builder.coalesce(prescLocJoin.get(LocationDetails_.state),""),
				builder.coalesce(prescLocJoin.get(LocationDetails_.zip),""),
				builder.function("replace", String.class, prescLocJoin.get(LocationDetails_.primaryphone) , builder.literal("-"), builder.literal("")),
				builder.function("formatphoneno", String.class, builder.function("replace", String.class, prescLocJoin.get(LocationDetails_.primaryphone) , builder.literal("-"), builder.literal(""))),
				builder.selectCase().when(builder.equal(builder.trim(builder.function("replace", String.class, prescLocJoin.get(LocationDetails_.primaryphone) , builder.literal("-"), builder.literal(""))),""), builder.coalesce(prescLocJoin.get(LocationDetails_.primaryphone), "")).otherwise("TE"),
				builder.function("replace", String.class, prescLocJoin.get(LocationDetails_.fax) , builder.literal("-"), builder.literal("")),
				builder.function("formatphoneno", String.class, builder.function("replace", String.class, prescLocJoin.get(LocationDetails_.fax) , builder.literal("-"), builder.literal(""))),
				builder.selectCase().when(builder.equal(builder.trim(builder.function("replace", String.class, prescLocJoin.get(LocationDetails_.beeperphone) , builder.literal("-"), builder.literal(""))),""), builder.coalesce(prescLocJoin.get(LocationDetails_.primaryphone), "")).otherwise("BN"),
				builder.function("replace", String.class, prescLocJoin.get(LocationDetails_.beeperphone) , builder.literal("-"), builder.literal("")),
				builder.selectCase().when(builder.equal(builder.trim(builder.function("replace", String.class, prescLocJoin.get(LocationDetails_.cellularphone) , builder.literal("-"), builder.literal(""))),""), builder.coalesce(prescLocJoin.get(LocationDetails_.primaryphone), "")).otherwise("CP"),
				builder.function("replace", String.class, prescLocJoin.get(LocationDetails_.cellularphone) , builder.literal("-"), builder.literal("")),
				builder.selectCase().when(builder.equal(builder.trim(builder.function("replace", String.class, prescLocJoin.get(LocationDetails_.homephone) , builder.literal("-"), builder.literal(""))),""), builder.coalesce(prescLocJoin.get(LocationDetails_.primaryphone), "")).otherwise("HP"),
				builder.function("replace", String.class, prescLocJoin.get(LocationDetails_.homephone) , builder.literal("-"), builder.literal("")),
				builder.selectCase().when(builder.equal(builder.trim(builder.function("replace", String.class, prescLocJoin.get(LocationDetails_.nightphone) , builder.literal("-"), builder.literal(""))),""), builder.coalesce(prescLocJoin.get(LocationDetails_.primaryphone), "")).otherwise("NP"),
				builder.function("replace", String.class, prescLocJoin.get(LocationDetails_.nightphone) , builder.literal("-"), builder.literal("")),
				builder.selectCase().when(builder.equal(builder.trim(builder.function("replace", String.class, prescLocJoin.get(LocationDetails_.workphone) , builder.literal("-"), builder.literal(""))),""), builder.coalesce(prescLocJoin.get(LocationDetails_.primaryphone), "")).otherwise("WP"),
				builder.function("replace", String.class, prescLocJoin.get(LocationDetails_.workphone) , builder.literal("-"), builder.literal("")),
				builder.selectCase().when(builder.notEqual(builder.trim(root.get(PrescriberDetails_.stateLicenseNumber)),""), builder.trim(builder.coalesce(root.get(PrescriberDetails_.stateLicenseNumber),""))).otherwise(""),
				builder.selectCase().when(builder.equal(prescLocJoin.get(LocationDetails_.fax), ""), prescLocJoin.get(LocationDetails_.fax)).otherwise("FX"),
				prescLocJoin.get(LocationDetails_.email),
				builder.selectCase().when(builder.equal(builder.trim(prescLocJoin.get(LocationDetails_.email)), ""), builder.coalesce(prescLocJoin.get(LocationDetails_.email),"")).otherwise("EM"),
				prescLocJoin.get(LocationDetails_.clinicname),
				builder.coalesce(prescLocJoin.get(LocationDetails_.npi),""),
				builder.coalesce(prescLocJoin.get(LocationDetails_.dea),""),
				builder.selectCase().when(builder.notEqual(builder.trim(locEmpJoin.get(EmployeeProfile_.empProfileSsn)),""), locEmpJoin.get(EmployeeProfile_.empProfileSsn)).otherwise(""),
				root.get(PrescriberDetails_.specialityQualifier),
				root.get(PrescriberDetails_.specialityCode),
				root.get(PrescriberDetails_.spiroot),
				locEmpJoin.get(EmployeeProfile_.empProfileFname),
				locEmpJoin.get(EmployeeProfile_.empProfileLname),
				prescLocJoin.get(LocationDetails_.servicelevel)));
		cq.where(builder.equal(root.get(PrescriberDetails_.doctorid),userId));
		List<Object> resultList= em.createQuery(cq).getResultList();
		List<DoctorDetailBean> doctorList=new ArrayList<DoctorDetailBean>();
		for(int i=0;i<resultList.size();i++){
			DoctorDetailBean eachobj=(DoctorDetailBean) resultList.get(i);
			if(isUserRegistered){
				eachobj.setAgentfname("");
				eachobj.setAgentlname("");
			}
			doctorList.add(eachobj);
		}
		return doctorList;
	}

	
	/**
	 * Getting location id for that user.
	 */
	private Integer minLocationId(int userId){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LocationDetails> root = cq.from(LocationDetails.class);
		cq.select(builder.least(root.get(LocationDetails_.locationId))).where(builder.equal(root.get(LocationDetails_.prescriberId), userId));
		List<Object> obj=em.createQuery(cq).getResultList();
		Integer locationId = null;
		Integer posId = null;
		if(!obj.isEmpty())
			locationId=(Integer) obj.get(0);
		if(locationId!=null)
			posId=getPosId(locationId);
		
		return posId;
	}
	
	/**
	 * Getting POS Id for particular location
	 */
	private Integer getPosId(int locationId){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LocationDetails> root = cq.from(LocationDetails.class);
		
		cq.select(builder.least(root.get(LocationDetails_.posId))).where(builder.equal(root.get(LocationDetails_.locationId), locationId));
		List<Object> obj=em.createQuery(cq).getResultList();
		Integer posId = null;
		if(!obj.isEmpty())
			posId=(Integer) obj.get(0);
		
		return posId;
	}
	
	
	/**
	 * Getting POS Id for particular user
	 */
	private Integer getUserPosId(int pos ,int userId){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LocationDetails> root = cq.from(LocationDetails.class);
		
		cq.select(builder.least(root.get(LocationDetails_.posId))).where(builder.and(builder.equal(root.get(LocationDetails_.posId), pos),builder.equal(root.get(LocationDetails_.prescriberId), userId)));
		List<Object> obj=em.createQuery(cq).getResultList();
		Integer posId = null;
		if(!obj.isEmpty())
			posId=(Integer) obj.get(0);
		
		return posId;
	}
	
	
	/**
	 * Checking Erx eligibility status of user 
	 */
	private boolean getUserRegisteredStatus(int userId) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PrescriberDetails> root = cq.from(PrescriberDetails.class);
		Join<PrescriberDetails, LocationDetails> prescLocJoin=root.join(PrescriberDetails_.locationDetails,JoinType.INNER);
		Long rowCount;
		boolean userFlag=false;
		try{
		cq.select(builder.count(root.get(PrescriberDetails_.doctorid))).where(builder.and(builder.equal(prescLocJoin.get(LocationDetails_.prescriberId),userId),builder.ge(prescLocJoin.get(LocationDetails_.servicelevel), 0)));
		rowCount=(Long) em.createQuery(cq).getSingleResult();
		
		if(rowCount>0){
			userFlag=true;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return userFlag;
	}

	
	/**
	 * Getting patient's principle doctor Id
	 */
	private int getPrincipleDoctorId(int encounterId) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Encounter> root = cq.from(Encounter.class);
		Join<Encounter, Chart> chartEncJoin=root.join(Encounter_.chart,JoinType.INNER);
		Join<Chart, PatientRegistration> chartPatJoin=chartEncJoin.join(Chart_.patientRegistrationTable,JoinType.INNER);
		int doctorId=-1;
		try{
		cq.select(chartPatJoin.get(PatientRegistration_.patientRegistrationPrincipalDoctor)).where(builder.equal(root.get(Encounter_.encounterId), encounterId));
		Object obj=em.createQuery(cq).getSingleResult();
		
		if(obj!=null)
			doctorId= (int) obj;
		}catch(Exception e){
			e.printStackTrace();
		}
		return doctorId;
	}

	
	/**
	 * Getting provider Id for that encounter
	 */
	private int getProviderDoctorId(int encounterId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Prescription> root = cq.from(Prescription.class);
		int doctorId=-1;
		try{
		cq.select(root.get(Prescription_.docPrescProviderId)).where(builder.equal(root.get(Prescription_.docPrescEncounterId), encounterId));
		Object obj=em.createQuery(cq).getSingleResult();
		
		if(obj!=null)
			doctorId= (int) obj;
		}catch(Exception e){
			e.printStackTrace();
		}
		return doctorId;
	}

	
	/**
	 * Checking whether user is in prescriber list or not
	 */
	private Long getPrescriberList(int doctorId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PrescriberDetails> root = cq.from(PrescriberDetails.class);
		Join<PrescriberDetails, LocationDetails> prescLocJoin=root.join(PrescriberDetails_.locationDetails,JoinType.INNER);
		Long rowCount=(long) 0;
		try{
		cq.select(builder.count(root.get(PrescriberDetails_.doctorid))).where(builder.equal(prescLocJoin.get(LocationDetails_.prescriberId),doctorId));
		rowCount=(Long) em.createQuery(cq).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return rowCount;
	}

	/**
	 * Getting prescriber Id based on SPI location Id
	 */
	private int getPrescriberId(String doctorSPIStr) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LocationDetails> root = cq.from(LocationDetails.class);
		int doctorId=-1;
		try{
		cq.select(root.get(LocationDetails_.prescriberId)).where(builder.equal(root.get(LocationDetails_.spiLocationid), doctorSPIStr));
		Object obj=em.createQuery(cq).getSingleResult();
		
		if(obj!=null)
			doctorId= (int) obj;
		}catch(Exception e){
			e.printStackTrace();
		}
		return doctorId;
	}

	
	/**
	 * Getting prescriber's SPI location Id based on inbox Id
	 */
	private String getSPI(int inboxId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<SSInbox> root = cq.from(SSInbox.class);
		String spi=null;
		
		try{
		cq.select(root.get(SSInbox_.ssInboxToId)).where(builder.equal(root.get(SSInbox_.ssInboxId), inboxId));
		Object obj=em.createQuery(cq).getSingleResult();
		
		if(obj!=null)
			spi= (String) obj;
		}catch(Exception e){
			e.printStackTrace();
		}
		return spi;
	}

	
	/**
	 * Getting inbox Id based encounter Id
	 */
	private int getInboxId(int encounterId) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<SSPatientMessageMap> root = cq.from(SSPatientMessageMap.class);
		int inboxId=0;
		try{
		cq.select(root.get(SSPatientMessageMap_.ssPatientMessageMapInboxId)).where(builder.equal(root.get(SSPatientMessageMap_.ssPatientMessageMapEncounterId), encounterId));
		List<Object> obj=em.createQuery(cq).getResultList();
		
		if(!obj.isEmpty())
			inboxId=(int) obj.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return inboxId;
	}

	
	/**
	 * Getting encounter details
	 */
	private List<Object[]> getEncounterObject(int encounterId) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<Encounter> root = cq.from(Encounter.class);
		
		cq.multiselect(root.get(Encounter_.encounterType),root.get(Encounter_.encounterReason)).where(builder.equal(root.get(Encounter_.encounterId), encounterId));
		List<Object[]> obj= em.createQuery(cq).getResultList();
		
		return obj;		
	}

	
	/**
	 * Getting pharmacy details for Erx summary data
	 */
	@Override
	public List<PharmacyBean> getPharmDetails(int pharmId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PharmDetails> root = cq.from(PharmDetails.class);
		cq.select(builder.construct(PharmacyBean.class, root.get(PharmDetails_.pharmDetailsId),
				root.get(PharmDetails_.pharmDetailsNcpdpid),
				root.get(PharmDetails_.pharmDetailsSsEmail),
				root.get(PharmDetails_.pharmDetailsStoreName),
				root.get(PharmDetails_.pharmDetailsAddressLine1),
				root.get(PharmDetails_.pharmDetailsCity),
				root.get(PharmDetails_.pharmDetailsState),
				builder.selectCase().when(builder.notEqual(builder.trim(root.get(PharmDetails_.pharmDetailsAddressLine2)), ""), root.get(PharmDetails_.pharmDetailsAddressLine2)).otherwise(""),
				root.get(PharmDetails_.pharmDetailsZip),
				root.get(PharmDetails_.pharmDetailsPrimaryPhone),
				root.get(PharmDetails_.pharmDetailsFax),
				root.get(PharmDetails_.pharmDetailsPhBeeper),
				root.get(PharmDetails_.pharmDetailsPhCell),
				root.get(PharmDetails_.pharmDetailsPhNight),
				builder.selectCase().when(builder.or(builder.equal(builder.trim(root.get(PharmDetails_.pharmDetailsPrimaryPhone)), ""),root.get(PharmDetails_.pharmDetailsPrimaryPhone).isNull()), builder.coalesce(root.get(PharmDetails_.pharmDetailsPrimaryPhone), "")).otherwise("TE"),
				builder.function("formatphoneno", String.class, root.get(PharmDetails_.pharmDetailsPrimaryPhone)),
				builder.selectCase().when(builder.or(builder.equal(builder.trim(root.get(PharmDetails_.pharmDetailsPhBeeper)), ""),root.get(PharmDetails_.pharmDetailsPhBeeper).isNull()), builder.coalesce(root.get(PharmDetails_.pharmDetailsPhBeeper), "")).otherwise("BN"),
				builder.selectCase().when(builder.or(builder.equal(builder.trim(root.get(PharmDetails_.pharmDetailsPhNight)), ""),root.get(PharmDetails_.pharmDetailsPhNight).isNull()), builder.coalesce(root.get(PharmDetails_.pharmDetailsPhNight), "")).otherwise("NP"),
				builder.selectCase().when(builder.or(builder.equal(builder.trim(root.get(PharmDetails_.pharmDetailsPhCell)), ""),root.get(PharmDetails_.pharmDetailsPhCell).isNull()), builder.coalesce(root.get(PharmDetails_.pharmDetailsPhCell), "")).otherwise("CP"),
				builder.selectCase().when(builder.or(builder.equal(builder.trim(root.get(PharmDetails_.pharmDetailsFax)), ""),root.get(PharmDetails_.pharmDetailsFax).isNull()), root.get(PharmDetails_.pharmDetailsFax)).otherwise("FX"),
				builder.function("formatphoneno", String.class, root.get(PharmDetails_.pharmDetailsFax)),
				builder.selectCase().when(builder.and(builder.equal(root.get(PharmDetails_.pharmDetailsServiceLevel), 2048),builder.lessThanOrEqualTo(root.get(PharmDetails_.pharmDetailsActiveStartTime), pharmDetailsRepository.findCurrentTimeStamp()),builder.greaterThan(root.get(PharmDetails_.pharmDetailsActiveEndTime), pharmDetailsRepository.findCurrentTimeStamp()),builder.equal(root.get(PharmDetails_.pharmDetailsIsSsPharm),1)), 1).otherwise(0),
				root.get(PharmDetails_.pharmDetailsEmail),
				builder.selectCase().when(builder.equal(builder.trim(root.get(PharmDetails_.pharmDetailsEmail)), ""), builder.coalesce(root.get(PharmDetails_.pharmDetailsEmail), "")).otherwise("EM"),
				root.get(PharmDetails_.pharmDetailsPharmacistLname),
				root.get(PharmDetails_.pharmDetailsPharmacistFname),
				root.get(PharmDetails_.pharmDetailsPharmacistMidname),
				root.get(PharmDetails_.pharmDetailsPharmacistPrefix),
				root.get(PharmDetails_.pharmDetailsPharmacistSuffix),
				root.get(PharmDetails_.pharmDetailsnpi),
				root.get(PharmDetails_.pharmDetailsIsSsPharm)));
		cq.where(builder.equal(root.get(PharmDetails_.pharmDetailsId),pharmId));
		List<PharmacyBean> pharmList=new ArrayList<PharmacyBean>();
		List<Object> resultList= em.createQuery(cq).getResultList();
		PharmacyBean pharmData=null;
		if(!resultList.isEmpty()){
			pharmData=(PharmacyBean) resultList.get(0);
			if(pharmData.getAddress2().equals(""))
				pharmData.setPhrmplacelocationqualifier("");
			else
				pharmData.setPhrmplacelocationqualifier("AD2");
	}
			pharmList.add(pharmData);
		
		return pharmList;
	}

	
	/**
	 * Getting medication details for Erx summary popup
	 */
	@Override
	public List<NewRxBean> getNewRxDetails(int encounterId, String prescId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Prescription> root = cq.from(Prescription.class);
		List<NewRxBean> rxList=new ArrayList<NewRxBean>();
		try{
		Join<Prescription, NdcDrugBrandMap> prescNDCJoin=root.join(Prescription_.ndcdrugbrandmap,JoinType.LEFT);
		Join<NdcDrugBrandMap, DrugRelationMap> ndcRelMapJoin=prescNDCJoin.join(NdcDrugBrandMap_.drugRelationMap,JoinType.LEFT);
		Join<DrugRelationMap, DeaSchedule> relSchJoin=ndcRelMapJoin.join(DrugRelationMap_.deaSchedule,JoinType.LEFT);
		Join<Prescription, EmployeeProfile> prescModifyJoin=root.join(Prescription_.empProfileModify,JoinType.INNER);
		Join<Prescription, DrugForm> prescFormJoin=root.join(Prescription_.drugForm,JoinType.LEFT);
		Join<Prescription, SSInbox> prescInboxJoin=root.join(Prescription_.ssInbox,JoinType.LEFT);
		Join<Prescription, PrescriptionUnits> prescUnitsJoin=root.join(Prescription_.prescriptionUnits,JoinType.LEFT);
		Join<Prescription, SsPatEligibilityDesc> prescEligibleJoin=root.join(Prescription_.ssPatEligibilityDesc,JoinType.LEFT);
		Predicate encId=builder.equal(root.get(Prescription_.docPrescEncounterId), encounterId);
		String [] data=prescId.split(",");
		List<Integer> ids=new ArrayList<>();
		for(int i=0;i<data.length;i++)
			ids.add(Integer.parseInt(data[i]));
		
		Predicate presc=root.get(Prescription_.docPrescId).in(ids);
		Predicate status=builder.or(builder.equal(root.get(Prescription_.docPrescStatus), 1),builder.equal(root.get(Prescription_.docPrescStatus), 2));
		Predicate ePresc=builder.equal(root.get(Prescription_.docPrescIsEPresc), 1);
		Predicate ndcCode=root.get(Prescription_.docPrescNdcCode).isNotNull();
		Predicate ssForm=builder.coalesce(prescFormJoin.get(DrugForm_.drugFormSsFormCode),"").isNotNull();
		
		cq.select(builder.construct(NewRxBean.class, root.get(Prescription_.docPrescId),
				builder.coalesce(root.get(Prescription_.rxfreq),""),
				root.get(Prescription_.docPrescIsUncoded),
//				builder.concat(builder.concat(builder.concat(root.get(Prescription_.docPrescId).toString(), builder.literal("~")), builder.concat(root.get(Prescription_.rxstrength), builder.literal("~"))), root.get(Prescription_.rxform)),
				builder.function("to_char", String.class, root.get(Prescription_.docPrescOrderedDate),builder.literal("YYYY-MM-DD")),
				builder.selectCase().when(root.get(Prescription_.rxname).isNotNull(), root.get(Prescription_.rxname)).otherwise(""),
				builder.substring(builder.concat(builder.concat(builder.concat(builder.coalesce(root.get(Prescription_.rxname),""), builder.literal(" ")), builder.concat(builder.coalesce(root.get(Prescription_.rxstrength),""), builder.literal(" "))), builder.coalesce(root.get(Prescription_.rxform),"")), 1, 105),
				builder.coalesce(root.get(Prescription_.rxstrength),""),
				root.get(Prescription_.docPrescUnit),
				builder.coalesce(root.get(Prescription_.rxform),""),
//				builder.substring(builder.concat(builder.concat((builder.selectCase().when(builder.and(root.get(Prescription_.docPrescIntake).isNotNull(),builder.gt(builder.function("char_length", Integer.class,root.get(Prescription_.docPrescIntake)),0)), builder.concat(" ",root.get(Prescription_.docPrescIntake))).otherwise("")).toString(), builder.literal((builder.selectCase().when(builder.and(root.get(Prescription_.docPrescRoute).isNotNull(),builder.gt(builder.function("char_length", Integer.class,root.get(Prescription_.docPrescRoute)),0)), builder.concat(" ",root.get(Prescription_.docPrescRoute))).otherwise("")).toString())), (builder.selectCase().when(builder.and(root.get(Prescription_.rxfreq).isNotNull(),builder.gt(builder.function("char_length", Integer.class,root.get(Prescription_.rxfreq)),0)), builder.concat(" ",root.get(Prescription_.rxfreq))).otherwise("")).toString()),1,140),
				builder.selectCase().when(builder.or(root.get(Prescription_.docPrescComments).isNull(),builder.lt(builder.function("char_length", Integer.class, root.get(Prescription_.docPrescComments)), 1)), builder.coalesce(root.get(Prescription_.docPrescComments),"")).when(builder.gt(builder.function("char_length", Integer.class, root.get(Prescription_.docPrescComments)), 210), builder.substring(root.get(Prescription_.docPrescComments), 1, 210)).otherwise(builder.trim(root.get(Prescription_.docPrescComments))),
				builder.coalesce(root.get(Prescription_.noofdays),""),
				builder.coalesce(root.get(Prescription_.docPrescIntake),""),
				builder.coalesce(root.get(Prescription_.docPrescRoute),""),
				builder.coalesce(root.get(Prescription_.rxquantity),""),
				builder.selectCase().when(builder.notEqual(root.get(Prescription_.noofrefills), ""), root.get(Prescription_.noofrefills)).otherwise("0"),
				root.get(Prescription_.docPrescIsPrinted),
				builder.selectCase().when(builder.equal(root.get(Prescription_.docPrescAllowSubstitution),true), 0).otherwise(1),
				builder.selectCase().when(builder.or(prescUnitsJoin.get(PrescriptionUnits_.code).isNull(),builder.equal(prescUnitsJoin.get(PrescriptionUnits_.code), "")), 
						builder.selectCase().when(builder.and(prescFormJoin.get(DrugForm_.drugFormSsFormQualifier).isNotNull(),builder.notEqual(prescFormJoin.get(DrugForm_.drugFormSsFormQualifier), "")), prescFormJoin.get(DrugForm_.drugFormSsFormQualifier)).otherwise("ZZ")).otherwise(prescUnitsJoin.get(PrescriptionUnits_.code)),
				root.get(Prescription_.docPrescUniqueId),
				builder.trim(prescInboxJoin.get(SSInbox_.ssInboxRxReferenceNo)),
				prescInboxJoin.get(SSInbox_.ssInboxPharmacyMessageId),
				prescFormJoin.get(DrugForm_.drugFormSsFormCode),
				builder.trim(builder.function("split_part", String.class, root.get(Prescription_.docPrescDxForRx),builder.literal("-"),builder.literal(1))),
				builder.selectCase().when(builder.equal(root.get(Prescription_.docPrescDxForRx),""), builder.coalesce(root.get(Prescription_.docPrescDxForRx), "")).otherwise("ICD9"),
				builder.selectCase().when(builder.equal(root.get(Prescription_.docPrescDxForRx),""), builder.coalesce(root.get(Prescription_.docPrescDxForRx), "")).otherwise("1"),
				builder.selectCase().when(builder.notEqual(builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescPbmParticipantId), "")),""), prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescPbmParticipantId)).otherwise(""),
				prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescId),
				root.get(Prescription_.docPrescAddsig),
				builder.coalesce(builder.function("split_part", String.class, builder.trim(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescCardHolderName)),builder.literal(","),builder.literal(1)), ""),
				builder.coalesce(builder.function("split_part", String.class, builder.trim(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescCardHolderName)),builder.literal(","),builder.literal(2)), ""),
				builder.selectCase().when(builder.gt(builder.function("char_length", Integer.class, builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescCardHolderId), ""))),35), builder.substring(builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescCardHolderId), "")), 1, 35)).otherwise(builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescCardHolderId), ""))),
				builder.selectCase().when(builder.gt(builder.function("char_length", Integer.class, builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescInsuranceName), ""))),35), builder.substring(builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescInsuranceName), "")), 1, 35)).otherwise(builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescInsuranceName), ""))),
				builder.selectCase().when(builder.gt(builder.function("char_length", Integer.class, builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescGroupId), ""))),35), builder.substring(builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescGroupId), "")), 1, 35)).otherwise(builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescGroupId), ""))),
				builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescCardHolderName), "")),
				builder.function("initcap", String.class, builder.trim(builder.function("split_part", String.class,prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescCardHolderName),builder.literal(","),builder.literal(1)))),
				builder.function("initcap", String.class, builder.trim(builder.function("split_part", String.class,prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescCardHolderName),builder.literal(","),builder.literal(2)))),
				builder.selectCase().when(builder.gt(builder.function("char_length", Integer.class, builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescInsuranceId), ""))),35), builder.substring(builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescInsuranceId), "")), 1, 35)).otherwise(builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescInsuranceId), ""))),
				builder.selectCase().when(builder.gt(builder.function("char_length", Integer.class, builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescInsuranceId), ""))),35), builder.substring(builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescInsuranceId), "")), 36)).otherwise(builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescInsuranceId), ""))),
				builder.trim(builder.coalesce(prescEligibleJoin.get(SsPatEligibilityDesc_.ssPatEligibilityDescBinId), "")),
				builder.trim(builder.coalesce(root.get(Prescription_.docPrescNdcCode), "")),
				builder.coalesce(root.get(Prescription_.rxquantityunits),""),
				builder.function("to_char", String.class, root.get(Prescription_.docPrescOrderedDate),builder.literal("MM/DD/YYYY")),
				builder.coalesce(builder.function("to_char", String.class, root.get(Prescription_.docPrescStartDate),builder.literal("MM/DD/YYYY")), ""),
				builder.coalesce(builder.function("to_char", String.class, root.get(Prescription_.docPrescStartDate),builder.literal("YYYY-MM-DD")), ""),
				builder.selectCase().when(ndcRelMapJoin.get(DrugRelationMap_.drugRelationMapDeaSchedule).isNotNull(), ndcRelMapJoin.get(DrugRelationMap_.drugRelationMapDeaSchedule)).otherwise(-1),
				builder.selectCase().when(relSchJoin.get(DeaSchedule_.deaScheduleValue).in(1,2,3,4,5), relSchJoin.get(DeaSchedule_.deaScheduleQualifier)).otherwise(""),
				root.get(Prescription_.docPrescProviderId),
				root.get(Prescription_.docPrescIsOverridden),
				root.get(Prescription_.docPrescStatus),
				prescNDCJoin.get(NdcDrugBrandMap_.isMedSup)));
		cq.where(builder.and(encId,presc,status,ePresc,ndcCode,ssForm));
		
		List<Object> resultList= em.createQuery(cq).getResultList();
		for(int i=0;i<resultList.size();i++){
			NewRxBean eachobj=(NewRxBean) resultList.get(i);
			String prescOrderNo=eachobj.getPrescriptionid()+"~"+eachobj.getDosage()+"~"+eachobj.getForm();
			eachobj.setPrescriberordernumber(prescOrderNo);
			if(eachobj.getIsmedsup()==true)
				eachobj.setDrugcoveragestatuscode("SP");
			if(eachobj.getUncoded()==true)
				eachobj.setDrugcoveragestatuscode("CP");
			if(!eachobj.getDeaschedule().equals(""))
				eachobj.setDrugcoveragestatuscode("SI");
			rxList.add(eachobj);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return rxList;
	}

	
	/**
	 * Getting prescribed medications of current encounter
	 */
	@Override
	public List<PrescribedMedBean> getPrescData(int encounterId) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Prescription> root = cq.from(Prescription.class);
		List<Object> maxList=getMaxIdList(encounterId);
		List<PrescribedMedBean> medList=new ArrayList<PrescribedMedBean>();
		try{
		Join<Prescription, EmployeeProfile> prescEmpJoin=root.join(Prescription_.empProfileOrderBy,JoinType.LEFT);
		Join<Prescription, SSInbox> prescInboxJoin=root.join(Prescription_.ssInbox,JoinType.LEFT);
		Join<SSInbox, RefillMessageType> inboxMsgTypeJoin=prescInboxJoin.join(SSInbox_.refillMessageType,JoinType.LEFT);
		Join<Prescription, LeafPatient> prescLeafJoin=root.join(Prescription_.leafPatient,JoinType.LEFT);
		Join<Prescription, DrugForm> prescFormJoin=root.join(Prescription_.drugForm,JoinType.LEFT);
		Join<Prescription, ModifiedDose> prescModifyJoin=root.join(Prescription_.modifiedDose,JoinType.LEFT);
		Join<Prescription, SSOutbox> prescOutboxJoin=root.join(Prescription_.ssOutbox,JoinType.LEFT);
		Join<SSOutbox, LocationDetails> outboxLocDetailJoin=prescOutboxJoin.join(SSOutbox_.locationDetails,JoinType.LEFT);
		Join<LocationDetails, EmployeeProfile> locEmpJoin=outboxLocDetailJoin.join(LocationDetails_.empProfileLoc,JoinType.LEFT);
		Join<SSOutbox, SSStatusErrorCodes> outboxErrorCodesJoin=prescOutboxJoin.join(SSOutbox_.ssstatuserrorcodes,JoinType.LEFT);
		
		prescFormJoin.on(builder.notEqual(builder.coalesce(prescFormJoin.get(DrugForm_.drugFormSsFormCode), ""), ""));
		Predicate sentTime=builder.notEqual(prescOutboxJoin.get(SSOutbox_.ssOutboxActualSentTime), "null");
		outboxErrorCodesJoin.on(builder.notEqual(outboxErrorCodesJoin.get(SSStatusErrorCodes_.ssStatusErrorCodesTypeId), 9));
		if(!maxList.isEmpty())
			prescOutboxJoin.on(builder.and(prescOutboxJoin.get(SSOutbox_.ssOutboxId).in(maxList),sentTime));
		else
			prescOutboxJoin.on(builder.and(prescOutboxJoin.get(SSOutbox_.ssOutboxId).in(new Integer(0)),sentTime));
		
		
		cq.select(builder.construct(PrescribedMedBean.class, root.get(Prescription_.docPrescId),
				root.get(Prescription_.docPrescEncounterId),
				root.get(Prescription_.docPrescStatus),
				builder.function("to_char", String.class, root.get(Prescription_.docPrescStartDate),builder.literal("MM/DD/YYYY")),
				root.get(Prescription_.docPrescStopDate),
				root.get(Prescription_.docPrescIsChronic),
				root.get(Prescription_.docPrescToPrintFax),
				root.get(Prescription_.docPrescToFax),
				root.get(Prescription_.docPrescOrderedBy),
				root.get(Prescription_.docPrescOrderedDate),
				root.get(Prescription_.docPrescLastModifiedBy),
				builder.function("to_char", String.class, root.get(Prescription_.docPrescLastModifiedDate),builder.literal("MM/DD/YYYY")),
				root.get(Prescription_.docPrescIntake),
				root.get(Prescription_.rxquantity),
				root.get(Prescription_.rxname),
				root.get(Prescription_.rxstrength),
				root.get(Prescription_.docPrescUnit),
				root.get(Prescription_.noofrefills),
				root.get(Prescription_.docPrescRoute),
				root.get(Prescription_.docPrescDuration),
				root.get(Prescription_.docPrescLotNumber),
				builder.function("to_char", String.class, root.get(Prescription_.docPrescExpireDate),builder.literal("MM/DD/YYYY")),
				root.get(Prescription_.noofdays),
				root.get(Prescription_.rxfreq),
				root.get(Prescription_.docPrescSchedule2),
				root.get(Prescription_.rxform),
				builder.function("split_part", String.class, root.get(Prescription_.docPrescComments),builder.literal("~"),builder.literal(1)),
				root.get(Prescription_.rxroute),
				root.get(Prescription_.docPrescAllowSubstitution),
				root.get(Prescription_.docPrescDxForRx),
				root.get(Prescription_.docPrescDxQualifierCode	),
				root.get(Prescription_.docPrescIsEPresc),
				root.get(Prescription_.docPrescIsEPrescSent),
				root.get(Prescription_.docPrescUniqueId),
				prescEmpJoin.get(EmployeeProfile_.empProfileFullname),
				prescInboxJoin.get(SSInbox_.ssInboxId),
				inboxMsgTypeJoin.get(RefillMessageType_.refillMessageTypeName),
				inboxMsgTypeJoin.get(RefillMessageType_.refillMessageTypeDesc),
				prescInboxJoin.get(SSInbox_.ssInboxMessageType),
				prescFormJoin.get(DrugForm_.drugFormSsFormCode),
				root.get(Prescription_.docPrescInstruction),
				root.get(Prescription_.docPrescAddsig),
				builder.coalesce(root.get(Prescription_.rxquantityunits), "-"),
				root.get(Prescription_.docPrescMapEligibilityDescId),
				root.get(Prescription_.docPrescFormularyStatus),
				root.get(Prescription_.docPrescCopayDetail),
				root.get(Prescription_.docPrescCoverageStatus),
				root.get(Prescription_.docPrescNdcCode),
				root.get(Prescription_.docPrescExternalSourceInfo),
				root.get(Prescription_.docPrescIsOverridden),
				builder.coalesce(root.get(Prescription_.docPrescMonograph), ""),
				builder.coalesce(root.get(Prescription_.docPrescLeaflet), ""),
				root.get(Prescription_.docPrescIsPrinted),
				root.get(Prescription_.docPrescFaxSentId),
				root.get(Prescription_.docPrescMedQuitReason),
				builder.coalesce(root.get(Prescription_.docPrescIsUncoded), false),
				//builder.selectCase().when(builder.and(builder.equal(prescLeafJoin.get(LeafPatient_.leafPatientIscomplete), true),builder.lessThan(prescLeafJoin.get(LeafPatient_.leafPatientCompletedOn),root.get(Prescription_.docPrescLastModifiedDate) )), true).otherwise(false),
				root.get(Prescription_.docPrescMedInternalRootSource),
				prescModifyJoin.get(ModifiedDose_.modifiedDoseParentDose),
				builder.coalesce(root.get(Prescription_.docPrescRxnormCode),""),
				root.get(Prescription_.docPrescIsMedSup),
				prescOutboxJoin.get(SSOutbox_.ssOutboxStatusOrErrorCode),
				prescOutboxJoin.get(SSOutbox_.ssOutboxActualSentTime),
				locEmpJoin.get(EmployeeProfile_.empProfileFullname),
				builder.function("to_char", String.class, builder.function("to_timestamp", Timestamp.class,prescOutboxJoin.get(SSOutbox_.ssOutboxSentDateTime),builder.literal("YYYY-MM-DD HH24:MI:SS") ),builder.literal("MM/dd/yyyy HH:MI:ss am")),
				prescOutboxJoin.get(SSOutbox_.ssOutboxPharmacyName),
				outboxErrorCodesJoin.get(SSStatusErrorCodes_.ssStatusErrorCodesDescription),
				builder.function("date_diff", Double.class, builder.literal(4),root.get(Prescription_.docPrescOrderedDate),builder.literal(pharmDetailsRepository.findCurrentTimeStamp()))));
		cq.where(builder.and(builder.equal(root.get(Prescription_.docPrescEncounterId), encounterId),builder.not(root.get(Prescription_.docPrescStatus).in(13,18,19))));
		List<Object> resultList= em.createQuery(cq).getResultList();
		for(int i=0;i<resultList.size();i++){
			PrescribedMedBean eachobj=(PrescribedMedBean) resultList.get(i);
			medList.add(eachobj);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return medList;
	}

	
	/**
	 * Getting maximum id list of sent medication.
	 */
	private List<Object> getMaxIdList(int encounterId) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<SSOutbox> root = cq.from(SSOutbox.class);
		
		cq.select(builder.greatest(root.get(SSOutbox_.ssOutboxId))).where(builder.equal(root.get(SSOutbox_.ssOutboxEncounterId), encounterId)).groupBy(root.get(SSOutbox_.ssOutboxPrescriptionId));
		
		List<Object> maxList=em.createQuery(cq).getResultList();
		return maxList;
		
	}

	
	/**
	 * Checking for controlled substances
	 */
	@Override
	public String checkCS(String medicationNames,int userId,int pharmId) {
		boolean canSendControlledSubstanceForGWT = false;
		String MedicationNameArr[]=medicationNames.split("~");	
		int controlledSubstanceCount=0;
		String Message="";
		String controlledSubstanceNDCList = "";
		String scheduleValue ="";
		String checkScheduleVal = "false";
		String confirmDialog="0";
		int identifier = 1;
		String allowControlledSubstance="1";
		allowControlledSubstance=getInitialValues("Allow controlled Substance Drugs through eRx?");
		if(allowControlledSubstance.equals("0"))
		{
			identifier = 0;
		}
		try{
		for(int i=0;i<MedicationNameArr.length;i++)
		{
			String temp[]=MedicationNameArr[i].split("@@@");
			int prescId = 0;
			if(temp.length>2)
				prescId=Integer.parseInt(MedicationNameArr[i].split("@@@")[2]);
			String isPrinted="false";
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Prescription> root = cq.from(Prescription.class);
			cq.select(root.get(Prescription_.docPrescIsPrinted)).where(builder.equal(root.get(Prescription_.docPrescId), prescId));
			List<Object> result= em.createQuery(cq).getResultList();
			if(!result.isEmpty())
				isPrinted=result.get(0).toString();
			
			if(checkControlledSubstance(MedicationNameArr[i]) && (isPrinted.equalsIgnoreCase("false")))
			{
				controlledSubstanceCount++;
				Message+=MedicationNameArr[i].split("@@@")[0]+",";
				if((MedicationNameArr[i].split("@@@")).length >1 ){
					controlledSubstanceNDCList += MedicationNameArr[i].split("@@@")[1]+",";						
					scheduleValue += checkScheduleValue(MedicationNameArr[i])+",";						
				}
				if(checkScheduleVal.equals("true")){
					scheduleValue = checkScheduleValue(MedicationNameArr[i]);												
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(controlledSubstanceNDCList.length() > 1){
			controlledSubstanceNDCList = controlledSubstanceNDCList.substring(0, (controlledSubstanceNDCList.length())-1);
			if(!checkScheduleVal.equals("true"))
				scheduleValue  = scheduleValue.substring(0, (scheduleValue.length())-1);
			
		}
		int printcount=0;
		for(int i=0;i<MedicationNameArr.length;i++)
		{
			String temp[]=MedicationNameArr[i].split("@@@");
			int prescId = 0;
			if(temp.length>2)
			prescId=Integer.parseInt(MedicationNameArr[i].split("@@@")[2]);
			String isPrinted="false";
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Prescription> root = cq.from(Prescription.class);
			cq.select(root.get(Prescription_.docPrescIsPrinted)).where(builder.equal(root.get(Prescription_.docPrescId), prescId));
			List<Object> result= em.createQuery(cq).getResultList();
			if(!result.isEmpty())
				isPrinted=result.get(0).toString();
			if(isPrinted.equalsIgnoreCase("true") )
				printcount++;
		}
		String canSendControlledSubstance="0";
		canSendControlledSubstance = getInitialValues("Allow controlled Substance Drugs through eRx?");
		int serviceLevel=3;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LocationDetails> root = cq.from(LocationDetails.class);
		cq.select(root.get(LocationDetails_.servicelevel)).where(builder.equal(root.get(LocationDetails_.prescriberId),userId));
		List<Object> result= em.createQuery(cq).getResultList();
		if(!result.isEmpty())
			serviceLevel=(int) result.get(0);
		
		Integer pharmServiceLevel=1;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> query = cb.createQuery();
		Root<PharmDetails> pharmRoot = query.from(PharmDetails.class);
		query.select(pharmRoot.get(PharmDetails_.pharmDetailsServiceLevel)).where(cb.equal(pharmRoot.get(PharmDetails_.pharmDetailsId), pharmId));
		List<Object> pharmList= em.createQuery(query).getResultList();
		if(!pharmList.isEmpty())
			if(pharmList.get(0)!=null)
				pharmServiceLevel= (Integer) pharmList.get(0);
		
		if(controlledSubstanceCount>0)
		{
			Message="The Medication(s) "+Message;
			Message=Message.substring(0,Message.length()-1);
			Message+=" are controlled substance(s)";
			if((MedicationNameArr.length - printcount)>controlledSubstanceCount)
			{				
				if(canSendControlledSubstance.equals("1") && serviceLevel==2051)
				{
					if(pharmServiceLevel>=2048)
					{
						Message+=". Do you want to send this prescription?";
						canSendControlledSubstanceForGWT = true;
					}
					else
					{
						Message+=" and the selected pharmacy is not EPCS capable.Do you want to send the other medications?";
					}
					
				}
				else
					Message+=" and cannot be sent electronically.Do you want to send the other medications?";
				
				confirmDialog="1";
			}
			else if((MedicationNameArr.length - printcount == controlledSubstanceCount))
			{
				if(canSendControlledSubstance.equals("1") && serviceLevel==2051)
				{
					if(pharmServiceLevel>=2048)
					{
						Message+=". Do you want to send this prescription?";
						confirmDialog="1";
						canSendControlledSubstanceForGWT = true;
					}
					else
					{
						Message+=" and the selected pharmacy is not EPCS capable. So cannot be sent electronically.";						
						confirmDialog="2";
					}
				}
				else
				{
					Message+=" and cannot be sent electronically.";						
					confirmDialog="2";
				}
				
			}
		}
		JSONObject messageForGWT = new JSONObject();
		try {
			messageForGWT.put("ndcList", controlledSubstanceNDCList);
			messageForGWT.put("message", Message);
	    	messageForGWT.put("confirmDialog", confirmDialog);
	    	messageForGWT.put("canSendControlledSubstanceForGWT", canSendControlledSubstanceForGWT);
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	
		return messageForGWT.toString();
	}

	
	/**
	 * Getting DEA schedule values for controlled substances
	 */
	private String checkScheduleValue(String drugInformations) {
		
		String[] MedicationDetails = drugInformations.split("@@@");
		String DEASchedule = "";	
		String NDC=MedicationDetails[1].replaceAll("'", "''");
		String name=MedicationDetails[0].replaceAll("'", "''");
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> query = cb.createQuery();
		Root<DrugRelationMap> drugRoot = query.from(DrugRelationMap.class);
		try{
		Join<DrugRelationMap, DrugDetails> drugRelDetailsJoin=drugRoot.join(DrugRelationMap_.drugDetails,JoinType.INNER);
		Join<DrugRelationMap, NdcDrugBrandMap> drugNdcJoin=drugRoot.join(DrugRelationMap_.ndcList,JoinType.INNER);
		Join<NdcDrugBrandMap, Brandname> ndcBrandJoin=drugNdcJoin.join(NdcDrugBrandMap_.brandName,JoinType.INNER);
		query.select(drugRoot.get(DrugRelationMap_.drugRelationMapDeaSchedule));
		
		if(MedicationDetails.length > 1){
			query.where(cb.and(cb.or(cb.like(cb.lower(ndcBrandJoin.get(Brandname_.brandDescription)), cb.lower(cb.literal(name))),cb.like(drugNdcJoin.get(NdcDrugBrandMap_.ndcCode),NDC)),drugRoot.get(DrugRelationMap_.drugRelationMapDeaSchedule).in(2,3,4,5)));
		} else {
			query.where(cb.and(cb.like(cb.lower(ndcBrandJoin.get(Brandname_.brandDescription)), cb.lower(cb.literal(name))),drugRoot.get(DrugRelationMap_.drugRelationMapDeaSchedule).in(2,3,4,5)));
		}
		query.groupBy(drugRoot.get(DrugRelationMap_.drugRelationMapDeaSchedule));
		List<Object> data= em.createQuery(query).getResultList();
		if(!data.isEmpty()){
			DEASchedule=data.get(0).toString();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return DEASchedule;
	}

	
	/**
	 * Checking where the given medication is a controlled substance or not.
	 */
	private boolean checkControlledSubstance(String drugInformations) {

		String[] MedicationDetails = drugInformations.split("@@@");
		String checkUncodebyName="";
		int count=0;
		boolean isControlledSubstance=false;
		String name=MedicationDetails[0].replaceAll("'", "''");
		String NDC="";
		if(MedicationDetails.length > 1)
			NDC=MedicationDetails[1].replaceAll("'", "''");
			
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Brandname> root = cq.from(Brandname.class);
		Join<Brandname, NdcDrugBrandMap> brandNdcJoin=root.join(Brandname_.ndcDrugBrandMap,JoinType.INNER);
		
		cq.select(root.get(Brandname_.brandDescription)).where(builder.like(builder.lower(root.get(Brandname_.brandDescription)), builder.lower(builder.literal(name))));
		List<Object> result= em.createQuery(cq).getResultList();
		if(!result.isEmpty())
			checkUncodebyName=result.get(0).toString();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> query = cb.createQuery();
		Root<DrugRelationMap> drugRoot = query.from(DrugRelationMap.class);
		try{
		Join<DrugRelationMap, DrugDetails> drugRelDetailsJoin=drugRoot.join(DrugRelationMap_.drugDetails,JoinType.INNER);
		Join<DrugRelationMap, NdcDrugBrandMap> drugNdcJoin=drugRoot.join(DrugRelationMap_.ndcList,JoinType.INNER);
		Join<NdcDrugBrandMap, Brandname> ndcBrandJoin=drugNdcJoin.join(NdcDrugBrandMap_.brandName,JoinType.INNER);
		query.select(drugRoot.get(DrugRelationMap_.drugRelationMapCode));
		if(MedicationDetails.length > 1){
		query.where(cb.and(cb.or(cb.like(cb.lower(ndcBrandJoin.get(Brandname_.brandDescription)), cb.lower(cb.literal(name))),cb.like(drugNdcJoin.get(NdcDrugBrandMap_.ndcCode),NDC)),drugRoot.get(DrugRelationMap_.drugRelationMapDeaSchedule).in(2,3,4,5)));	
		}
		else {
			if(checkUncodebyName.equals(""))
				query.where(cb.and(cb.like(cb.<String>selectCase().when(cb.like(ndcBrandJoin.get(Brandname_.brandDescription),""),cb.function("split_part", String.class, ndcBrandJoin.get(Brandname_.brandDescription),cb.literal(" "),cb.literal(1))).otherwise(ndcBrandJoin.get(Brandname_.brandDescription)),cb.<String>selectCase().when(cb.like(cb.literal(name), ""), cb.function("split_part", String.class, cb.literal(name),cb.literal(" "),cb.literal(1))).otherwise(name)),drugRoot.get(DrugRelationMap_.drugRelationMapDeaSchedule).in(2,3,4,5)));
			else
				query.where(cb.and(cb.like(cb.lower(ndcBrandJoin.get(Brandname_.brandDescription)), cb.lower(cb.literal(name))),drugRoot.get(DrugRelationMap_.drugRelationMapDeaSchedule).in(2,3,4,5)));
		}
		List<Object> data= em.createQuery(query).getResultList();
		if(!data.isEmpty()){
			count=data.size();
		}
		if(count>0)
			isControlledSubstance=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return isControlledSubstance;
	}
	
	
	
}