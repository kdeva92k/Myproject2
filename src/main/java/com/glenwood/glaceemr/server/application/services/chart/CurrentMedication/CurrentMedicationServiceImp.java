package com.glenwood.glaceemr.server.application.services.chart.CurrentMedication;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.AllergiesType;
import com.glenwood.glaceemr.server.application.models.AllergiesType_;
import com.glenwood.glaceemr.server.application.models.Brandname;
import com.glenwood.glaceemr.server.application.models.Brandname_;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.CurrentMedication;
import com.glenwood.glaceemr.server.application.models.CurrentMedication_;
import com.glenwood.glaceemr.server.application.models.DrugCombination;
import com.glenwood.glaceemr.server.application.models.DrugCombination_;
import com.glenwood.glaceemr.server.application.models.DrugDetails;
import com.glenwood.glaceemr.server.application.models.DrugDetails_;
import com.glenwood.glaceemr.server.application.models.DrugDosage;
import com.glenwood.glaceemr.server.application.models.DrugDosageUnit;
import com.glenwood.glaceemr.server.application.models.DrugDosageUnit_;
import com.glenwood.glaceemr.server.application.models.DrugDosage_;
import com.glenwood.glaceemr.server.application.models.DrugForm;
import com.glenwood.glaceemr.server.application.models.DrugForm_;
import com.glenwood.glaceemr.server.application.models.DrugRelationMap;
import com.glenwood.glaceemr.server.application.models.DrugRelationMap_;
import com.glenwood.glaceemr.server.application.models.DrugRoute;
import com.glenwood.glaceemr.server.application.models.DrugRoute_;
import com.glenwood.glaceemr.server.application.models.DrugSchedule;
import com.glenwood.glaceemr.server.application.models.DrugSchedule_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.LeafPatient;
import com.glenwood.glaceemr.server.application.models.LeafPatient_;
import com.glenwood.glaceemr.server.application.models.LocationDetails;
import com.glenwood.glaceemr.server.application.models.LocationDetails_;
import com.glenwood.glaceemr.server.application.models.MedStatus;
import com.glenwood.glaceemr.server.application.models.MedStatus_;
import com.glenwood.glaceemr.server.application.models.MedSupDetails;
import com.glenwood.glaceemr.server.application.models.MedSupDetails_;
import com.glenwood.glaceemr.server.application.models.NdcDrugBrandMap;
import com.glenwood.glaceemr.server.application.models.NdcDrugBrandMap_;
import com.glenwood.glaceemr.server.application.models.PatientAllergies;
import com.glenwood.glaceemr.server.application.models.PatientAllergies_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PharmDetails;
import com.glenwood.glaceemr.server.application.models.PharmacyMapping;
import com.glenwood.glaceemr.server.application.models.Prescriberspecificdrug;
import com.glenwood.glaceemr.server.application.models.Prescriberspecificdrug_;
import com.glenwood.glaceemr.server.application.models.Prescription;
import com.glenwood.glaceemr.server.application.models.Prescription_;
import com.glenwood.glaceemr.server.application.models.ReceiptDetail;
import com.glenwood.glaceemr.server.application.models.Rxnorm;
import com.glenwood.glaceemr.server.application.models.Rxnorm_;
import com.glenwood.glaceemr.server.application.models.SSOutbox;
import com.glenwood.glaceemr.server.application.models.SSOutbox_;
import com.glenwood.glaceemr.server.application.models.SSStatusErrorCodes;
import com.glenwood.glaceemr.server.application.models.SSStatusErrorCodes_;
import com.glenwood.glaceemr.server.application.repositories.ChartRepository;
import com.glenwood.glaceemr.server.application.repositories.CurrentMedicationRepository;
import com.glenwood.glaceemr.server.application.repositories.DrugCombinationRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterRepository;
import com.glenwood.glaceemr.server.application.repositories.NdcDrugBrandMapRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientAllergyRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.repositories.PharmDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.PharmacyMappingRepository;
import com.glenwood.glaceemr.server.application.repositories.PrescriptionRepository;
import com.glenwood.glaceemr.server.application.repositories.ReceiptDetailRepository;
import com.glenwood.glaceemr.server.application.repositories.SSOutboxRepository;
import com.glenwood.glaceemr.server.application.specifications.CurrentMedicationSpecification;

@Service
@Transactional
public class CurrentMedicationServiceImp implements CurrentMedicationService{

	@Autowired
	PatientAllergyRepository patientAllergyRepository;
	
	@Autowired
	ChartRepository chartRepository;
	
	@Autowired
	PatientRegistrationRepository patientRegistrationRepository;
	
	@Autowired
	PharmacyMappingRepository pharmacyMappingRepository;
	
	@Autowired
	PharmDetailsRepository pharmDetailsRepository;
	
	@Autowired
	EncounterRepository encounterRepository;
	
	@Autowired
	ReceiptDetailRepository receiptDetailRepository;
	
	@Autowired
	CurrentMedicationRepository currentMedicationRepository;
	
	@Autowired
	PrescriptionRepository prescriptionRepository;
	
	@Autowired
	SSOutboxRepository ssOutboxRepository;
	
	@Autowired
	NdcDrugBrandMapRepository ndcDrugBrandMapRepository;
	
	@Autowired
	DrugCombinationRepository drugCombinationRepository;

	@Autowired
	EntityManagerFactory emf ;

	@PersistenceContext
	EntityManager em;
	
	/**
	 *Getting patient allergies data
	 */
	
	@Override
	public List<PatientAllergiesBean> getAllergies(int chartId) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<PatientAllergies> root = cq.from(PatientAllergies.class);
		List<PatientAllergiesBean> beanList=new ArrayList<PatientAllergiesBean>();
		try{
		Join<PatientAllergies, AllergiesType> patAllerTypeJoin=root.join(PatientAllergies_.allergiesType,JoinType.INNER);
		Join<PatientAllergies,EmployeeProfile> AllerEmpModifyJoin=root.join(PatientAllergies_.empProfileAllgModifiedByTable,JoinType.INNER);
		Join<PatientAllergies,EmployeeProfile> AllerEmpCreateJoin=root.join(PatientAllergies_.empProfileAllgCreatedByTable,JoinType.INNER);
		Predicate status=builder.equal(root.get(PatientAllergies_.patAllergStatus), 1);
		Predicate patChart=builder.equal(root.get(PatientAllergies_.patAllergChartId), chartId);
		Predicate typeId=builder.greaterThanOrEqualTo(root.get(PatientAllergies_.patAllergTypeId), 0);
		cq.multiselect(root.get(PatientAllergies_.patAllergId),
				root.get(PatientAllergies_.patAllergSeverity),
				root.get(PatientAllergies_.patAllergTypeId),
				patAllerTypeJoin.get(AllergiesType_.allergtypeName),
				root.get(PatientAllergies_.patAllergAllergicTo),
				root.get(PatientAllergies_.patAllergAllergyCode),
				root.get(PatientAllergies_.patAllergCodeSystem),
				root.get(PatientAllergies_.patAllergDrugCategory),
				root.get(PatientAllergies_.patAllergReactionTo),
				root.get(PatientAllergies_.patAllergModifiedOn),
				root.get(PatientAllergies_.patAllergOnsetDate),
				root.get(PatientAllergies_.patAllergCreatedOn)).where(typeId,status,patChart).orderBy(builder.asc(root.get(PatientAllergies_.patAllergId)));
		
		List<Object[]> allergies=em.createQuery(cq).getResultList();
		
		for(Object[] details : allergies){
			PatientAllergiesBean eachObj=new PatientAllergiesBean();
			eachObj.setPatAllergId(details[0]==null?-1:(Integer)details[0]);
			eachObj.setPatAllergSeverity(details[1]==null?-1:(Integer)details[1]);
			eachObj.setPatAllergTypeId(details[2]==null?-1:(Integer)details[2]);
			eachObj.setPatAllergName(details[3]==null?"":details[3].toString());
			eachObj.setPatAllergAllergicTo(details[4]==null?"":details[4].toString());
			eachObj.setPatAllergAllergyCode(details[5]==null?"":details[5].toString());
			eachObj.setPatAllergCodeSystem(details[6]==null?"":details[6].toString());
			eachObj.setPatAllergDrugCategory(details[7]==null?"":details[7].toString());
			eachObj.setPatAllergReactionTo(details[8]==null?"":details[8].toString());
			eachObj.setPatAllergModifiedOn(details[9]==null?"":details[9].toString());
			eachObj.setPatAllergOnsetDate(details[10]==null?"":details[10].toString());
			eachObj.setPatAllergCreatedOn(details[11]==null?"":details[11].toString());
			beanList.add(eachObj);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return beanList;
	}
	
	/**
	 * Getting Patient pharmacy details
	 */

	@Override
	public PharmDetails getPharmacy(int patientId) {
		Chart chartPharmacy=chartRepository.findOne(CurrentMedicationSpecification.getChartPharmacy(patientId));
		PatientRegistration patPharmacy=patientRegistrationRepository.findOne(patientId);
		List<PharmacyMapping> mapPharmacy=pharmacyMappingRepository.findAll(CurrentMedicationSpecification.getMapPharmacy(patientId));
		int pharmacyId=-1;
		
		try{
		String pharm=chartPharmacy.getChartPharmacy();
		
		if(chartPharmacy.getChartPharmacy()!=null){
			int idx=pharm.indexOf("^");
			pharmacyId=Integer.parseInt(pharm.substring(0, idx));
		}
		else if(patPharmacy.getPatientRegistrationPharmacyId()!=null)
			pharmacyId=patPharmacy.getPatientRegistrationPharmacyId();
		else if(mapPharmacy.get(0).getPharmacyMappingPharmacyid()!=null)
			pharmacyId=mapPharmacy.get(0).getPharmacyMappingPharmacyid();
		else
			pharmacyId=-1;
		}catch(Exception e){
			e.printStackTrace();
		}
		PharmDetails pharmacyData=pharmDetailsRepository.findOne(pharmacyId);
		
		return pharmacyData;
	}

	/**
	 * Getting encounter details
	 */
	@Override
	public List<Encounter> getEncounterData(int encounterId) {
		List<Encounter> encData=encounterRepository.findAll(CurrentMedicationSpecification.getEncData(encounterId));
		return encData;
	}

	/**
	 * Getting Patient copay details
	 */
	@Override
	public PatientRegistration getCopay(int patientId) {
		PatientRegistration patData=patientRegistrationRepository.findOne(patientId);
		return patData;
	}

	/**
	 * Getting Patient copayment details
	 */
	@Override
	public List<ReceiptDetail> getCopayment(int encounterId,int patientId) {
		Encounter encDate=encounterRepository.findOne(encounterId);
		List<ReceiptDetail> receiptData=receiptDetailRepository.findAll(CurrentMedicationSpecification.getReceiptDetails(encDate.getEncounterDate(),patientId));
		return receiptData;
	}

	/**
	 * Getting Patient chart details
	 */
	@Override
	public Chart getchartDetails(int patientId) {
		Chart chartData=chartRepository.findOne(CurrentMedicationSpecification.getchartDetails(patientId));
		return chartData;
	}

	/**
	 * Getting max encounter datails for that patient if not creating new
	 */
	@Override
	public Encounter getMaxEnc(String chartId,int patientId,int userId) {
		List<Encounter> enc=encounterRepository.findAll(CurrentMedicationSpecification.getMaxencounter(chartId));
		Chart newChart=new Chart();
		if(chartId.equals("-1") || chartId.equals("")){
			newChart.setChartId(-1);
			newChart.setChartUnknown1("");
			newChart.setChartPatientid(patientId);
			newChart.setChartCreatedby(userId);
			newChart.setChartCreateddate(chartRepository.findCurrentTimeStamp());
			newChart.setChartAlreadyseen(false);
			chartRepository.saveAndFlush(newChart);
			if(newChart.getChartId()!=-1)
				chartId=newChart.getChartId().toString();
		}
		Encounter newEncounter=new Encounter();
		if(enc.size()<=0){
			newEncounter.setEncounterChartid(Integer.parseInt(chartId));
			newEncounter.setEncounterDate(encounterRepository.findCurrentTimeStamp());
			newEncounter.setEncounterType((short) 3);
			newEncounter.setEncounterReason(40);
			newEncounter.setEncounterServiceDoctor((long) -1);
			newEncounter.setEncounterChiefcomp("");
			newEncounter.setEncounterComments("");
			newEncounter.setEncounterRoom((short) 1);
			newEncounter.setEncounterStatus((short) 1);
			newEncounter.setEncounterIsportal(0);
			newEncounter.setEncounterSeverity((short) -1);
			newEncounter.setEncounterCreatedBy(null);
			newEncounter.setEncounterCreatedDate(encounterRepository.findCurrentTimeStamp());
			newEncounter.setEncounterDocResponse("");
			newEncounter.setEncounterChargeable(false);
			newEncounter.setEncounterCommentScribble("");
			newEncounter.setEncounterResponseScribble("");
			newEncounter.setEncounterInsReview(-1);
			newEncounter.setEncounterInsReviewComment("");
			newEncounter.setEncounterPos(3);
			newEncounter.setEncounterRefDoctor((long) -1);
			newEncounter.setEncounterMultireasonId("");
			newEncounter.setEncounterVisittype((short) -1);
			newEncounter.setEncounterBillingcomments("-1");
			newEncounter.setEncounterAssProvider("-1");
			newEncounter.setEncounterModifiedon(encounterRepository.findCurrentTimeStamp());
			newEncounter.setEncounterModifiedby(null);
			newEncounter.setEncounterRoomIsactive(true);
			newEncounter.setEncounterAlreadySeen(false);
			encounterRepository.saveAndFlush(newEncounter);
			return newEncounter;
		}
			
		
		return enc.get(0);
	}

	/**
	 * Getting Patient current medications for the current encounter
	 */
	@Override
	public List<MedicationDetailBean> getMedDetails(int encounterId, int patientId) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<CurrentMedication> root = cq.from(CurrentMedication.class);
		List<MedicationDetailBean> beanList=new ArrayList<MedicationDetailBean>();
		try{
		Join<CurrentMedication, LeafPatient> medLeafJoin=root.join(CurrentMedication_.leafPatient,JoinType.LEFT);
		Predicate status=builder.not(root.get(CurrentMedication_.currentMedicationStatus).in(16,18));
		Predicate isActive=builder.equal(root.get(CurrentMedication_.currentMedicationIsActive), true);
		Predicate encId=builder.equal(root.get(CurrentMedication_.currentMedicationEncounterId), encounterId);
		Predicate patId=builder.equal(root.get(CurrentMedication_.currentMedicationPatientId), patientId);
		
		cq.select(builder.construct(MedicationDetailBean.class, root.get(CurrentMedication_.currentMedicationId),
				root.get(CurrentMedication_.currentMedicationEncounterId),
				root.get(CurrentMedication_.currentMedicationStatus),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationIsChronic),false),
				root.get(CurrentMedication_.currentMedicationOrderBy),
				builder.upper(builder.function("to_char", String.class, root.get(CurrentMedication_.currentMedicationOrderOn),builder.literal("MM/dd/yyyy HH:MI:ss am"))),
				root.get(CurrentMedication_.currentMedicationModifiedBy),
				builder.upper(builder.function("to_char", String.class, root.get(CurrentMedication_.currentMedicationModifiedOn),builder.literal("MM/dd/yyyy HH:MI:ss am"))),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationIntake),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationQuantity),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationRxName),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationDosageWithUnit),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationUnit),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationRefills),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationRoute),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationDuration),""),
				root.get(CurrentMedication_.currentMedicationLotNumber),
				builder.upper(builder.function("to_char", String.class, root.get(CurrentMedication_.currentMedicationExpiryDate),builder.literal("MM/dd/yyyy HH:MI:ss am"))),
				root.get(CurrentMedication_.currentMedicationDays),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationFrequency1),""),
				root.get(CurrentMedication_.currentMedicationFrequency2),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationForm),""),
				root.get(CurrentMedication_.currentMedicationComments),
				root.get(CurrentMedication_.currentMedicationRouteId),
				root.get(CurrentMedication_.currentMedicationAllowSubstitution),
				root.get(CurrentMedication_.currentMedicationStartDate),
				root.get(CurrentMedication_.currentMedicationExternalSourceInfo),
				root.get(CurrentMedication_.currentMedicationIsOverridden),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationIsUncoded),false),
				root.get(CurrentMedication_.currentMedicationMedInternalRootSource),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationQuantityUnits),"-"),
				root.get(CurrentMedication_.currentMedicationIsMedSup),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationRxnormCode),""),
				medLeafJoin.get(LeafPatient_.leafPatientIscomplete),
				medLeafJoin.get(LeafPatient_.leafPatientCompletedOn))).where(status,isActive,encId,patId).orderBy(builder.asc(root.get(CurrentMedication_.currentMedicationId)));
		
		List<Object> medlist=em.createQuery(cq).getResultList();
		
		for(int i=0;i<medlist.size();i++){
			MedicationDetailBean eachObj=(MedicationDetailBean) medlist.get(i);
				beanList.add(eachObj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return beanList;
	}

	/**
	 * Getting summary of care and transition of care  details
	 */
	@Override
	public Encounter getTransitionSummaryCare(int encounterId) {
		Encounter transitionSummaryCare=encounterRepository.findOne(encounterId);
		return transitionSummaryCare;
	}

	/**
	 * Getting Patient's active current medication details
	 */
	@Override
	public List<ActiveMedicationsBean> getActiveCurrentMeds(int patientId) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<CurrentMedication> root = cq.from(CurrentMedication.class);
		List<ActiveMedicationsBean> beanList=new ArrayList<ActiveMedicationsBean>();
		
		try{
		Join<CurrentMedication,Encounter> currentEncJoin=root.join(CurrentMedication_.encounter,JoinType.INNER);
		Join<Encounter, Chart> encChartJoin=currentEncJoin.join(Encounter_.chart, JoinType.INNER);
		Join<Chart, PatientRegistration> chartPatJoin=encChartJoin.join(Chart_.patientRegistrationTable,JoinType.INNER);
		Join<CurrentMedication,EmployeeProfile> currentEmpModifyJoin=root.join(CurrentMedication_.empProfileModifiedBy,JoinType.LEFT);
		Join<CurrentMedication,EmployeeProfile> currentEmpOrderJoin=root.join(CurrentMedication_.empProfileOrderBy,JoinType.LEFT);
		Join<CurrentMedication,MedStatus> currentMedStatusJoin=root.join(CurrentMedication_.medstatus,JoinType.INNER);
		
		chartPatJoin.on(builder.equal(chartPatJoin.get(PatientRegistration_.patientRegistrationId), patientId));
		Predicate patientid=builder.equal(root.get(CurrentMedication_.currentMedicationPatientId), patientId);
		Predicate isActive=builder.equal(root.get(CurrentMedication_.currentMedicationIsActive),true);
		Predicate isMedSup=builder.equal(root.get(CurrentMedication_.currentMedicationIsMedSup),false);
		Predicate medStatus=builder.like(builder.lower(currentMedStatusJoin.get(MedStatus_.medStatusGroup)),"active");
		cq.select(builder.construct(ActiveMedicationsBean.class, root.get(CurrentMedication_.currentMedicationId),
				root.get(CurrentMedication_.currentMedicationEncounterId),
				root.get(CurrentMedication_.currentMedicationStatus),
				root.get(CurrentMedication_.currentMedicationIsChronic),
				builder.concat(builder.concat(builder.concat(builder.concat(currentEmpOrderJoin.get(EmployeeProfile_.empProfileFname), " "), builder.concat(currentEmpOrderJoin.get(EmployeeProfile_.empProfileLname), " ")), builder.concat(currentEmpOrderJoin.get(EmployeeProfile_.empProfileMi), " ")), currentEmpOrderJoin.get(EmployeeProfile_.empProfileCredentials)),
				builder.upper(builder.function("to_char", String.class, root.get(CurrentMedication_.currentMedicationOrderOn),builder.literal("MM/dd/yyyy HH:MI:ss am"))),
				builder.concat(builder.concat(builder.concat(builder.concat(currentEmpModifyJoin.get(EmployeeProfile_.empProfileFname), " "), builder.concat(currentEmpModifyJoin.get(EmployeeProfile_.empProfileLname), " ")), builder.concat(currentEmpModifyJoin.get(EmployeeProfile_.empProfileMi), " ")), currentEmpModifyJoin.get(EmployeeProfile_.empProfileCredentials)),
				builder.upper(builder.function("to_char", String.class, root.get(CurrentMedication_.currentMedicationModifiedOn),builder.literal("MM/dd/yyyy HH:MI:ss am"))),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationIntake),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationQuantity),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationRxName),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationDosageWithUnit),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationUnit),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationRefills),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationRoute),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationDuration),""),
				root.get(CurrentMedication_.currentMedicationLotNumber),
				builder.upper(builder.function("to_char", String.class, root.get(CurrentMedication_.currentMedicationExpiryDate),builder.literal("MM/dd/yyyy HH:MI:ss am"))),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationDays),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationFrequency1),""),
				root.get(CurrentMedication_.currentMedicationFrequency2),
				root.get(CurrentMedication_.currentMedicationForm),
				root.get(CurrentMedication_.currentMedicationNotes),
				root.get(CurrentMedication_.currentMedicationRouteId),
				root.get(CurrentMedication_.currentMedicationAllowSubstitution),
				root.get(CurrentMedication_.currentMedicationStartDate),
				root.get(CurrentMedication_.currentMedicationIsOverridden),
				root.get(CurrentMedication_.currentMedicationMedInternalRootSource),
				root.get(CurrentMedication_.currentMedicationPrescribedBy),
				root.get(CurrentMedication_.currentMedicationNdcCode),
				currentMedStatusJoin.get(MedStatus_.medStatusName),
				encChartJoin.get(Chart_.chartId),
				currentEncJoin.get(Encounter_.encounterType),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationIsUncoded),false),
				root.get(CurrentMedication_.currentMedicationExternalSourceInfo),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationRxnormCode),""))).where(builder.and(isActive,isMedSup,medStatus,patientid)).orderBy(builder.desc(root.get(CurrentMedication_.currentMedicationModifiedOn)));
		
		List<Object> medlist=em.createQuery(cq).getResultList();
		
		for(int i=0;i<medlist.size();i++){
			ActiveMedicationsBean eachObj=(ActiveMedicationsBean) medlist.get(i);
			eachObj.setUnits("");
			eachObj.setToprint(false);
			eachObj.setMedfrom(1);
			eachObj.setDxforrx("");
			eachObj.setAddi("");
			eachObj.setTrans_mode(0);
			eachObj.setSentdoctor("");
			eachObj.setSenttime("");
			eachObj.setFailurereason("");
			eachObj.setPharmacyname("");
			eachObj.setCurroot("nurse");
			eachObj.setTofax(false);
			eachObj.setPresctype("drug");
			eachObj.setFormstatus("");
			eachObj.setMed_quit_reason("");
			eachObj.setMonograph("");
			eachObj.setLeaflet("");
			eachObj.setEliid(-1);
			eachObj.setCopaydetail("");
			eachObj.setCovstatus("");
			eachObj.setDxqualifiercode("");
			eachObj.setIseprescription(1);
				beanList.add(eachObj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return beanList;
	}

	/**
	 * Getting Patient's active prescribed medication details
	 */
	@Override
	public List<ActiveMedicationsBean> getActivePrescMeds(int patientId) {
		List<Object> maxList=getMaxIdList(patientId);
		//List<Prescription> prescMeds=prescriptionRepository.findAll(CurrentMedicationSpecification.getActivePrescMeds(patientId,maxList));

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Prescription> root = cq.from(Prescription.class);
		List<ActiveMedicationsBean> beanList=new ArrayList<ActiveMedicationsBean>();
		try{
		Join<Prescription,Encounter> prescEncJoin=root.join(Prescription_.encounter,JoinType.INNER);
		Join<Encounter, Chart> encChartJoin=prescEncJoin.join(Encounter_.chart, JoinType.INNER);
		Join<Chart, PatientRegistration> chartPatJoin=encChartJoin.join(Chart_.patientRegistrationTable,JoinType.INNER);
		Join<Prescription, EmployeeProfile> prescEmpJoin=root.join(Prescription_.empprofile,JoinType.LEFT);
		Join<Prescription, EmployeeProfile> prescEmpModifyJoin=root.join(Prescription_.empProfileModify,JoinType.LEFT);
		Join<Prescription, MedStatus> prescStatusJoin=root.join(Prescription_.medstatus,JoinType.LEFT);
		Join<Prescription, SSOutbox> prescOutboxJoin=root.join(Prescription_.ssOutbox,JoinType.LEFT);
		Join<SSOutbox, LocationDetails> outboxLocDetailJoin=prescOutboxJoin.join(SSOutbox_.locationDetails,JoinType.LEFT);
		Join<LocationDetails, EmployeeProfile> locEmpJoin=outboxLocDetailJoin.join(LocationDetails_.empProfileLoc,JoinType.LEFT);
		Join<SSOutbox, SSStatusErrorCodes> outboxErrorCodesJoin=prescOutboxJoin.join(SSOutbox_.ssstatuserrorcodes,JoinType.LEFT);
		
		chartPatJoin.on(builder.equal(chartPatJoin.get(PatientRegistration_.patientRegistrationId),patientId));
		prescOutboxJoin.on(builder.notEqual(prescOutboxJoin.get(SSOutbox_.ssOutboxActualSentTime), "null"));
		if(!maxList.isEmpty())
			prescOutboxJoin.on(prescOutboxJoin.get(SSOutbox_.ssOutboxId).in(maxList));
		else
			prescOutboxJoin.on(prescOutboxJoin.get(SSOutbox_.ssOutboxId).in(new Integer(0)));
		Predicate isActive=builder.equal(root.get(Prescription_.docPrescIsActive),true);
		Predicate isMedSup=builder.equal(root.get(Prescription_.docPrescIsMedSup),false);
		Predicate medStatus=builder.like(builder.lower(prescStatusJoin.get(MedStatus_.medStatusGroup)),"active");
		
		cq.select(builder.construct(ActiveMedicationsBean.class, root.get(Prescription_.docPrescId),
				root.get(Prescription_.docPrescEncounterId),
				root.get(Prescription_.docPrescStatus),
				root.get(Prescription_.docPrescIsChronic),
				builder.concat(builder.concat(builder.concat(builder.concat(prescEmpModifyJoin.get(EmployeeProfile_.empProfileFname), " "), builder.concat(prescEmpModifyJoin.get(EmployeeProfile_.empProfileLname), " ")), builder.concat(prescEmpModifyJoin.get(EmployeeProfile_.empProfileMi), " ")), prescEmpModifyJoin.get(EmployeeProfile_.empProfileCredentials)),
				builder.upper(builder.function("to_char", String.class, root.get(Prescription_.docPrescOrderedDate),builder.literal("MM/dd/yyyy HH:MI:ss am"))),
				builder.concat(builder.concat(builder.concat(builder.concat(prescEmpModifyJoin.get(EmployeeProfile_.empProfileFname), " "), builder.concat(prescEmpModifyJoin.get(EmployeeProfile_.empProfileLname), " ")), builder.concat(prescEmpModifyJoin.get(EmployeeProfile_.empProfileMi), " ")), prescEmpModifyJoin.get(EmployeeProfile_.empProfileCredentials)),
				builder.upper(builder.function("to_char", String.class, root.get(Prescription_.docPrescLastModifiedDate),builder.literal("MM/dd/yyyy HH:MI:ss am"))),
				builder.coalesce(root.get(Prescription_.docPrescIntake),""),
				builder.coalesce(root.get(Prescription_.rxquantity),""),
				builder.coalesce(root.get(Prescription_.rxname),""),
				builder.coalesce(root.get(Prescription_.rxstrength),""),
				builder.coalesce(root.get(Prescription_.docPrescUnit),""),
				builder.coalesce(root.get(Prescription_.rxquantityunits),""),
				builder.coalesce(root.get(Prescription_.noofrefills),""),
				builder.coalesce(root.get(Prescription_.docPrescRoute),""),
				builder.coalesce(root.get(Prescription_.docPrescDuration),""),
				root.get(Prescription_.docPrescLotNumber),
				builder.upper(builder.function("to_char", String.class, root.get(Prescription_.docPrescExpireDate),builder.literal("MM/dd/yyyy HH:MI:ss am"))),
				builder.coalesce(root.get(Prescription_.noofdays),""),
				builder.coalesce(root.get(Prescription_.rxfreq),""),
				root.get(Prescription_.docPrescSchedule2),
				builder.coalesce(root.get(Prescription_.rxform),""),
				root.get(Prescription_.docPrescComments),
				root.get(Prescription_.rxroute),
				root.get(Prescription_.docPrescAllowSubstitution),
				root.get(Prescription_.docPrescStartDate),
				root.get(Prescription_.docPrescStopDate),
				root.get(Prescription_.docPrescToPrintFax),
				root.get(Prescription_.docPrescIsOverridden),
				root.get(Prescription_.docPrescMedInternalRootSource),
				builder.concat(builder.concat(builder.concat(builder.concat(prescEmpJoin.get(EmployeeProfile_.empProfileFname), " "), builder.concat(prescEmpJoin.get(EmployeeProfile_.empProfileLname), " ")), builder.concat(prescEmpJoin.get(EmployeeProfile_.empProfileMi), " ")), prescEmpJoin.get(EmployeeProfile_.empProfileCredentials)),
				root.get(Prescription_.docPrescDxForRx),
				root.get(Prescription_.docPrescAddsig),
				root.get(Prescription_.docPrescNdcCode),
				prescStatusJoin.get(MedStatus_.medStatusName),
				builder.selectCase().when(builder.equal(root.get(Prescription_.docPrescIsPrinted),true),3).when(root.get(Prescription_.docPrescFaxSentId).isNotNull(),2).when(builder.equal(root.get(Prescription_.docPrescIsEPrescSent), 1), 1).otherwise(0),
				builder.coalesce(locEmpJoin.get(EmployeeProfile_.empProfileFullname),""),
				builder.coalesce(prescOutboxJoin.get(SSOutbox_.ssOutboxActualSentTime),""),
				builder.coalesce(outboxErrorCodesJoin.get(SSStatusErrorCodes_.ssStatusErrorCodesDescription),""),
				builder.coalesce(prescOutboxJoin.get(SSOutbox_.ssOutboxPharmacyName),""),
				encChartJoin.get(Chart_.chartId),
				prescEncJoin.get(Encounter_.encounterType),
				root.get(Prescription_.docPrescToFax),
				builder.coalesce(root.get(Prescription_.docPrescIsUncoded),false),
				root.get(Prescription_.docPrescFormularyStatus),
				root.get(Prescription_.docPrescMedQuitReason),
				builder.coalesce(root.get(Prescription_.docPrescMonograph),""),
				builder.coalesce(root.get(Prescription_.docPrescLeaflet),""),
				root.get(Prescription_.docPrescExternalSourceInfo),
				root.get(Prescription_.docPrescMapEligibilityDescId),
				root.get(Prescription_.docPrescCopayDetail),
				root.get(Prescription_.docPrescCoverageStatus),
				builder.coalesce(root.get(Prescription_.docPrescRxnormCode),""),
				root.get(Prescription_.docPrescDxQualifierCode),
				root.get(Prescription_.docPrescIsEPresc))).where(builder.and(isActive,isMedSup,medStatus)).orderBy(builder.desc(root.get(Prescription_.docPrescLastModifiedDate)));
		List<Object> medlist=em.createQuery(cq).getResultList();
		
		
		for(int i=0;i<medlist.size();i++){
			ActiveMedicationsBean eachObj=(ActiveMedicationsBean) medlist.get(i);
			eachObj.setMedfrom(2);
			eachObj.setCurroot("doctor");
			eachObj.setPresctype("drug");
				beanList.add(eachObj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return beanList;
	}
	
	/**
	 * Getting max of surescripts inbox ids list
	 */
	public List<Object> getMaxIdList(int patientId){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<SSOutbox> root = cq.from(SSOutbox.class);
		
		cq.select(builder.greatest(root.get(SSOutbox_.ssOutboxId))).where(builder.equal(root.get(SSOutbox_.ssOutboxPatientId), patientId)).groupBy(root.get(SSOutbox_.ssOutboxPrescriptionId));
		
		List<Object> maxList=em.createQuery(cq).getResultList();
		return maxList;
	}

	/**
	 * Getting Patient inactive medication details
	 */
	@Override
	public List<InactiveMedBean> getInActiveCurrentMeds(int patientId) {
		//List<CurrentMedication> currentInactiveMeds=currentMedicationRepository.findAll(CurrentMedicationSpecification.getInActiveCurrentMeds(patientId));
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<CurrentMedication> root = cq.from(CurrentMedication.class);
		List<InactiveMedBean> beanList=new ArrayList<InactiveMedBean>();
		
		try{
		Join<CurrentMedication,Encounter> currentEncJoin=root.join(CurrentMedication_.encounter,JoinType.INNER);
		Join<Encounter, Chart> encChartJoin=currentEncJoin.join(Encounter_.chart, JoinType.INNER);
		Join<Chart, PatientRegistration> chartPatJoin=encChartJoin.join(Chart_.patientRegistrationTable,JoinType.INNER);
		Join<CurrentMedication,EmployeeProfile> currentEmpModifyJoin=root.join(CurrentMedication_.empProfileModifiedBy,JoinType.LEFT);
		Join<CurrentMedication,EmployeeProfile> currentEmpOrderJoin=root.join(CurrentMedication_.empProfileOrderBy,JoinType.LEFT);
		Join<CurrentMedication,MedStatus> currentMedStatusJoin=root.join(CurrentMedication_.medstatus,JoinType.INNER);
		
		chartPatJoin.on(builder.equal(chartPatJoin.get(PatientRegistration_.patientRegistrationId), patientId));
		Predicate medStatus=root.get(CurrentMedication_.currentMedicationStatus).in(15,18,20);
		
		cq.select(builder.construct(InactiveMedBean.class, root.get(CurrentMedication_.currentMedicationId),
				root.get(CurrentMedication_.currentMedicationEncounterId),
				root.get(CurrentMedication_.currentMedicationStatus),
				root.get(CurrentMedication_.currentMedicationIsChronic),
				builder.concat(builder.concat(builder.concat(builder.concat(currentEmpOrderJoin.get(EmployeeProfile_.empProfileFname), " "), builder.concat(currentEmpOrderJoin.get(EmployeeProfile_.empProfileLname), " ")), builder.concat(currentEmpOrderJoin.get(EmployeeProfile_.empProfileMi), " ")), currentEmpOrderJoin.get(EmployeeProfile_.empProfileCredentials)),
				builder.upper(builder.function("to_char", String.class, root.get(CurrentMedication_.currentMedicationOrderOn),builder.literal("MM/dd/yyyy HH:MI:ss am"))),
				builder.concat(builder.concat(builder.concat(builder.concat(currentEmpModifyJoin.get(EmployeeProfile_.empProfileFname), " "), builder.concat(currentEmpModifyJoin.get(EmployeeProfile_.empProfileLname), " ")), builder.concat(currentEmpModifyJoin.get(EmployeeProfile_.empProfileMi), " ")), currentEmpModifyJoin.get(EmployeeProfile_.empProfileCredentials)),
				builder.upper(builder.function("to_char", String.class, root.get(CurrentMedication_.currentMedicationModifiedOn),builder.literal("MM/dd/yyyy HH:MI:ss am"))),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationIntake),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationQuantity),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationRxName),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationDosageWithUnit),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationUnit),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationRefills),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationRoute),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationDuration),""),
				root.get(CurrentMedication_.currentMedicationLotNumber),
				builder.upper(builder.function("to_char", String.class, root.get(CurrentMedication_.currentMedicationExpiryDate),builder.literal("MM/dd/yyyy HH:MI:ss am"))),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationDays),""),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationFrequency1),""),
				root.get(CurrentMedication_.currentMedicationFrequency2),
				builder.coalesce(root.get(CurrentMedication_.currentMedicationForm),""),
				root.get(CurrentMedication_.currentMedicationNotes),
				root.get(CurrentMedication_.currentMedicationRouteId),
				root.get(CurrentMedication_.currentMedicationAllowSubstitution),
				root.get(CurrentMedication_.currentMedicationStartDate),
				root.get(CurrentMedication_.currentMedicationNdcCode),
				currentMedStatusJoin.get(MedStatus_.medStatusName))).where(medStatus).orderBy(builder.desc(root.get(CurrentMedication_.currentMedicationModifiedOn)));
		
		List<Object> sizelist=em.createQuery(cq).getResultList();
		
		for(int i=0;i<sizelist.size();i++){
			InactiveMedBean eachObj=(InactiveMedBean) sizelist.get(i);
				eachObj.setUnits("-");
				eachObj.setToprint(false);
				eachObj.setMedfrom(1);
				eachObj.setProvider_name("");
				eachObj.setDxforrx("");
				eachObj.setMed_quit_reason("");
				eachObj.setInactivated_by("");
				eachObj.setInactivated_on("");
				eachObj.setCurroot("nurse");
				String currObj=getCurrMedDate(patientId,eachObj.getRootid());
				if(currObj!=null)
					eachObj.setOrderdate(currObj);
				else{
					String presc=getDocPrescDate(patientId,eachObj.getRootid());
					eachObj.setOrderdate(presc);
				}
				
				beanList.add(eachObj);
			}
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> query = cb.createQuery();
		Root<Prescription> docroot = query.from(Prescription.class);
		
		Join<Prescription,Encounter> prescEncJoin=docroot.join(Prescription_.encounter,JoinType.INNER);
		Join<Prescription,EmployeeProfile> prescEmpJoin=docroot.join(Prescription_.empprofile,JoinType.LEFT);
		Join<Prescription,EmployeeProfile> prescEmpModifyJoin=docroot.join(Prescription_.empProfileModify,JoinType.LEFT);
		Join<Prescription,EmployeeProfile> PrescEmpInactiveJoin=docroot.join(Prescription_.empProfileInActive,JoinType.LEFT);
		Join<Prescription, MedStatus> prescStatusJoin=docroot.join(Prescription_.medstatus,JoinType.INNER);
		
		Predicate status=docroot.get(Prescription_.docPrescStatus).in(11,12,18,19);
		Predicate prescPatId=cb.equal(docroot.get(Prescription_.docPrescPatientId), patientId);	
		
		query.select(cb.construct(InactiveMedBean.class, docroot.get(Prescription_.docPrescId),
				docroot.get(Prescription_.docPrescEncounterId),
				docroot.get(Prescription_.docPrescStatus),
				docroot.get(Prescription_.docPrescIsChronic),
				cb.concat(cb.concat(cb.concat(cb.concat(prescEmpModifyJoin.get(EmployeeProfile_.empProfileFname), " "), cb.concat(prescEmpModifyJoin.get(EmployeeProfile_.empProfileLname), " ")), cb.concat(prescEmpModifyJoin.get(EmployeeProfile_.empProfileMi), " ")), prescEmpModifyJoin.get(EmployeeProfile_.empProfileCredentials)),
				cb.upper(cb.function("to_char", String.class, docroot.get(Prescription_.docPrescOrderedDate),cb.literal("MM/dd/yyyy HH:MI:ss am"))),
				cb.concat(cb.concat(cb.concat(cb.concat(prescEmpModifyJoin.get(EmployeeProfile_.empProfileFname), " "), cb.concat(prescEmpModifyJoin.get(EmployeeProfile_.empProfileLname), " ")), cb.concat(prescEmpModifyJoin.get(EmployeeProfile_.empProfileMi), " ")), prescEmpModifyJoin.get(EmployeeProfile_.empProfileCredentials)),
				cb.upper(cb.function("to_char", String.class, docroot.get(Prescription_.docPrescLastModifiedDate),cb.literal("MM/dd/yyyy HH:MI:ss am"))),
				cb.coalesce(docroot.get(Prescription_.docPrescIntake),""),
				cb.coalesce(docroot.get(Prescription_.rxquantity),""),
				cb.coalesce(docroot.get(Prescription_.rxname),""),
				cb.coalesce(docroot.get(Prescription_.rxstrength),""),
				cb.coalesce(docroot.get(Prescription_.docPrescUnit),""),
				cb.coalesce(docroot.get(Prescription_.rxquantityunits),""),
				cb.coalesce(docroot.get(Prescription_.noofrefills),""),
				cb.coalesce(docroot.get(Prescription_.docPrescRoute),""),
				cb.coalesce(docroot.get(Prescription_.docPrescDuration),""),
				docroot.get(Prescription_.docPrescLotNumber),
				cb.upper(cb.function("to_char", String.class, docroot.get(Prescription_.docPrescExpireDate),cb.literal("MM/dd/yyyy HH:MI:ss am"))),
				cb.coalesce(docroot.get(Prescription_.noofdays),""),
				cb.coalesce(docroot.get(Prescription_.rxfreq),""),
				docroot.get(Prescription_.docPrescSchedule2),
				cb.coalesce(docroot.get(Prescription_.rxform),""),
				cb.coalesce(docroot.get(Prescription_.docPrescComments),""),
				docroot.get(Prescription_.rxroute),
				docroot.get(Prescription_.docPrescAllowSubstitution),
				docroot.get(Prescription_.docPrescStartDate),
				docroot.get(Prescription_.docPrescStopDate),
				docroot.get(Prescription_.docPrescToPrintFax),
				cb.concat(cb.concat(cb.concat(cb.concat(prescEmpJoin.get(EmployeeProfile_.empProfileFname), " "), cb.concat(prescEmpJoin.get(EmployeeProfile_.empProfileLname), " ")), cb.concat(prescEmpJoin.get(EmployeeProfile_.empProfileMi), " ")), prescEmpJoin.get(EmployeeProfile_.empProfileCredentials)),
				docroot.get(Prescription_.docPrescNdcCode),
				docroot.get(Prescription_.docPrescDxForRx),
				docroot.get(Prescription_.docPrescMedQuitReason),
				cb.concat(cb.concat(cb.concat(cb.concat(PrescEmpInactiveJoin.get(EmployeeProfile_.empProfileFname), " "), cb.concat(PrescEmpInactiveJoin.get(EmployeeProfile_.empProfileLname), " ")), cb.concat(PrescEmpInactiveJoin.get(EmployeeProfile_.empProfileMi), " ")), PrescEmpInactiveJoin.get(EmployeeProfile_.empProfileCredentials)),
				cb.upper(cb.function("to_char", String.class, docroot.get(Prescription_.docPrescInactivatedOn),cb.literal("MM/dd/yyyy HH:MI:ss am"))),
				prescStatusJoin.get(MedStatus_.medStatusName))).where(cb.and(prescPatId,status)).orderBy(cb.desc(docroot.get(Prescription_.docPrescLastModifiedDate)));
		
		List<Object> docsize=em.createQuery(query).getResultList();
		
		for(int i=0;i<docsize.size();i++){
			InactiveMedBean eachObj=(InactiveMedBean) docsize.get(i);
			eachObj.setMedfrom(2);
			eachObj.setCurroot("doctor");
			String currObj=getCurrMedDate(patientId,eachObj.getRootid());
			if(currObj!=null)
				eachObj.setOrderdate(currObj);
			else{
				String presc=getDocPrescDate(patientId,eachObj.getRootid());
				eachObj.setOrderdate(presc);
			}
			beanList.add(eachObj);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return beanList;
	}
	
	/**
	 * Getting Patient medication search details
	 */
	@Override
	public List<SearchBean> getsearchData(String keyword,String prescriberspecific, String mapid, int userId,int offset,int limit) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<DrugCombination> root = cq.from(DrugCombination.class);
		List<SearchBean> beanList=new ArrayList<SearchBean>();
		
		try{
		Join<DrugCombination, NdcDrugBrandMap> combNdcMapJoin=root.join(DrugCombination_.ndcDrugBrandMap,JoinType.LEFT);
		Join<DrugCombination, DrugRelationMap> ndcDrugRelJoin=root.join(DrugCombination_.drugRelationMap,JoinType.LEFT);
		Join<DrugCombination, Brandname> ndcBrandJoin=root.join(DrugCombination_.brandname,JoinType.LEFT);
		Join<DrugRelationMap, DrugDetails> drugDetailJoin=ndcDrugRelJoin.join(DrugRelationMap_.drugDetails,JoinType.LEFT);
		Join<DrugRelationMap, DrugForm> drugFormJoin=ndcDrugRelJoin.join(DrugRelationMap_.drugForm,JoinType.LEFT);
		Join<DrugRelationMap, DrugDosage> drugDosageJoin=ndcDrugRelJoin.join(DrugRelationMap_.drugDosage,JoinType.LEFT);
		Join<DrugRelationMap, DrugDosageUnit> drugDosageUnitJoin=ndcDrugRelJoin.join(DrugRelationMap_.drugDosageUnit,JoinType.LEFT);
		Join<DrugRelationMap, DrugRoute> drugRouteJoin=ndcDrugRelJoin.join(DrugRelationMap_.drugRoute,JoinType.LEFT);
		Join<DrugCombination, Prescriberspecificdrug> ndcPrescSpecificJoin;
		if (prescriberspecific.equalsIgnoreCase("true"))
			ndcPrescSpecificJoin=root.join(DrugCombination_.prescriberspecificdrug,JoinType.INNER);
		else
			ndcPrescSpecificJoin=root.join(DrugCombination_.prescriberspecificdrug,JoinType.LEFT);
		ndcPrescSpecificJoin.on(builder.equal(ndcPrescSpecificJoin.get(Prescriberspecificdrug_.loginid), userId));
		Join<NdcDrugBrandMap, MedSupDetails> ndcMedSupJoin=combNdcMapJoin.join(NdcDrugBrandMap_.medSupDetails,JoinType.LEFT);
		Join<NdcDrugBrandMap, Rxnorm> ndcRxnormJoin=combNdcMapJoin.join(NdcDrugBrandMap_.rxnorms,JoinType.LEFT);
		Join<DrugRelationMap, DrugSchedule> drugRelSchJoin=ndcDrugRelJoin.join(DrugRelationMap_.drugSchedule,JoinType.LEFT);
		Predicate criteria = null;
		
		Predicate ndcCode=builder.isNotNull(root.get(DrugCombination_.drugCombinationNdcCode));
	
		if (!keyword.equals("")){
			criteria=builder.like(builder.lower(builder.concat(builder.concat(builder.coalesce(ndcBrandJoin.get(Brandname_.brandDescription),""), " "),builder.concat(builder.concat(builder.coalesce(drugDosageJoin.get(DrugDosage_.DrugDosageDesc),""), " "),builder.coalesce(drugFormJoin.get(DrugForm_.drugFormName),"")))),getLikePattern(keyword));
		}
	
		if(!mapid.equals("-1")){
			criteria =	 builder.and(builder.like(builder.lower(builder.concat(builder.concat(builder.coalesce(ndcBrandJoin.get(Brandname_.brandDescription),""), " "),builder.concat(builder.concat(builder.coalesce(drugDosageJoin.get(DrugDosage_.DrugDosageDesc),""), " "),builder.coalesce(drugFormJoin.get(DrugForm_.drugFormName),"")))),getLikePattern(keyword)),builder.equal(root.get(DrugCombination_.drugCombinationSpecificId),mapid));
		}
		
		cq.select(builder.construct(SearchBean.class,ndcBrandJoin.get(Brandname_.brandDescription),
				ndcDrugRelJoin.get(DrugRelationMap_.drugRelationMapDeaSchedule),
				drugDetailJoin.get(DrugDetails_.drugDetailsName),
				builder.least(combNdcMapJoin.get(NdcDrugBrandMap_.ndcCode)),
				builder.coalesce(drugDosageJoin.get(DrugDosage_.DrugDosageDesc),""),
				builder.coalesce(drugDosageUnitJoin.get(DrugDosageUnit_.drugDosageUnitName),""),
				builder.coalesce(drugRouteJoin.get(DrugRoute_.drugRouteName),""),
				builder.coalesce(drugRelSchJoin.get(DrugSchedule_.drugScheduleName),""),
				builder.coalesce(drugFormJoin.get(DrugForm_.drugFormName),""),
				builder.greatest(ndcDrugRelJoin.get(DrugRelationMap_.drugRelationMapCode)),
				builder.greatest(root.get(DrugCombination_.drugCombinationSpecificId)),
				ndcDrugRelJoin.get(DrugRelationMap_.drugRelationMapQty),
				builder.coalesce(ndcDrugRelJoin.get(DrugRelationMap_.drugRelationMapRefillsId),0),
				builder.coalesce(ndcDrugRelJoin.get(DrugRelationMap_.drugRelationMapTake),""),
				builder.coalesce(ndcDrugRelJoin.get(DrugRelationMap_.drugRelationMapDays),""),
				combNdcMapJoin.get(NdcDrugBrandMap_.drugType),
				combNdcMapJoin.get(NdcDrugBrandMap_.drugStatus),
				builder.coalesce(ndcDrugRelJoin.get(DrugRelationMap_.drugRelationMapQuantityUnit),"-"),
				builder.coalesce(ndcMedSupJoin.get(MedSupDetails_.sourceDesc),""),
				builder.coalesce(ndcMedSupJoin.get(MedSupDetails_.categoryName),""),
				combNdcMapJoin.get(NdcDrugBrandMap_.isMedSup),
				builder.greatest(builder.coalesce(combNdcMapJoin.get(NdcDrugBrandMap_.imageFilename), "")),
				builder.greatest(builder.coalesce(ndcRxnormJoin.get(Rxnorm_.rxnormCode),"")))).where(builder.and(ndcCode,criteria));
		cq.groupBy(ndcBrandJoin.get(Brandname_.brandDescription),ndcDrugRelJoin.get(DrugRelationMap_.drugRelationMapDeaSchedule),drugDetailJoin.get(DrugDetails_.drugDetailsName),drugDosageJoin.get(DrugDosage_.DrugDosageDesc),
				drugDosageUnitJoin.get(DrugDosageUnit_.drugDosageUnitName),drugRouteJoin.get(DrugRoute_.drugRouteName),drugRelSchJoin.get(DrugSchedule_.drugScheduleName),drugFormJoin.get(DrugForm_.drugFormName),
				ndcDrugRelJoin.get(DrugRelationMap_.drugRelationMapQty),ndcDrugRelJoin.get(DrugRelationMap_.drugRelationMapRefillsId),ndcDrugRelJoin.get(DrugRelationMap_.drugRelationMapTake),ndcDrugRelJoin.get(DrugRelationMap_.drugRelationMapDays),
				combNdcMapJoin.get(NdcDrugBrandMap_.drugType),combNdcMapJoin.get(NdcDrugBrandMap_.drugStatus),ndcDrugRelJoin.get(DrugRelationMap_.drugRelationMapQuantityUnit),ndcMedSupJoin.get(MedSupDetails_.sourceDesc),
				ndcMedSupJoin.get(MedSupDetails_.categoryName),combNdcMapJoin.get(NdcDrugBrandMap_.isMedSup));
		List<Object> sizelist=em.createQuery(cq).getResultList();
		long count=sizelist.size();
		
		List<Object> medlist=em.createQuery(cq).setFirstResult(offset).setMaxResults(limit).getResultList();
		
		for(int i=0;i<medlist.size();i++){
				SearchBean eachObj=(SearchBean) medlist.get(i);
				eachObj.setTotrec(count);
				beanList.add(eachObj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return beanList;
	}

	/**
	 * Getting pattern for like operation
	 */
	private static String getLikePattern(final String searchTerm) {
		StringBuilder pattern = new StringBuilder();
		pattern.append(searchTerm.toLowerCase());
		pattern.append("%");
		return pattern.toString();
	}
	
	/**
	 * Getting order date of the root medication for current medication
	 */
	public String getCurrMedDate(int patientId,int rootId){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<CurrentMedication> root = cq.from(CurrentMedication.class);
		String orderDate=null;
		try{
		Predicate currentMedId=builder.equal(root.get(CurrentMedication_.currentMedicationId), rootId);
		Predicate currentMedPatId=builder.equal(root.get(CurrentMedication_.currentMedicationPatientId),patientId);
		cq.select(builder.upper(builder.function("to_char", String.class, root.get(CurrentMedication_.currentMedicationOrderOn),builder.literal("MM/dd/yyyy HH:MI:ss am")))).where(builder.and(currentMedId,currentMedPatId));
		List<Object> obj=em.createQuery(cq).getResultList();
	
		if(!obj.isEmpty())
			orderDate= obj.get(0).toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return orderDate;
	}
	
	/**
	 * Getting order date of the root medication for prescribed medication
	 */
	public String getDocPrescDate(int patientId,int rootId){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Prescription> root = cq.from(Prescription.class);
		String orderDate=null;
		try{
		Predicate prescMedId=builder.equal(root.get(Prescription_.docPrescId), rootId);
		Predicate prescMedPatId=builder.equal(root.get(Prescription_.docPrescPatientId),patientId);
		cq.select(builder.upper(builder.function("to_char", String.class, root.get(Prescription_.docPrescOrderedDate),builder.literal("MM/dd/yyyy HH:MI:ss am")))).where(builder.and(prescMedId,prescMedPatId));
		List<Object> obj=em.createQuery(cq).getResultList();
		
		if(!obj.isEmpty())
			orderDate= obj.get(0).toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return orderDate;
	}
	
}
