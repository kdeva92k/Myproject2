package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.AccountType;
import com.glenwood.glaceemr.server.application.models.AuthorizationMaster;
import com.glenwood.glaceemr.server.application.models.BillingConfigTable;
import com.glenwood.glaceemr.server.application.models.BillingConfigTable_;
import com.glenwood.glaceemr.server.application.models.Brandname;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.DrugDosage;
import com.glenwood.glaceemr.server.application.models.DrugForm;
import com.glenwood.glaceemr.server.application.models.DrugRelationMap;
import com.glenwood.glaceemr.server.application.models.DrugRelationMap_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.ReferringDoctor;
import com.glenwood.glaceemr.server.application.models.AppReferenceValues;
import com.glenwood.glaceemr.server.application.models.AppReferenceValues_;
import com.glenwood.glaceemr.server.application.models.InitialSettings;
import com.glenwood.glaceemr.server.application.models.InitialSettings_;
import com.glenwood.glaceemr.server.application.models.InsCompAddr;
import com.glenwood.glaceemr.server.application.models.InsCompAddr_;
import com.glenwood.glaceemr.server.application.models.InsCompany;
import com.glenwood.glaceemr.server.application.models.LocationDetails;
import com.glenwood.glaceemr.server.application.models.LocationDetails_;
import com.glenwood.glaceemr.server.application.models.NdcDrugBrandMap;
import com.glenwood.glaceemr.server.application.models.NdcDrugBrandMap_;
import com.glenwood.glaceemr.server.application.models.NoMatchFoundPatient;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PharmDetails;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosTable_;
import com.glenwood.glaceemr.server.application.models.PrescriberDetails;
import com.glenwood.glaceemr.server.application.models.PrescriberDetails_;
import com.glenwood.glaceemr.server.application.models.Prescription;
import com.glenwood.glaceemr.server.application.models.PrescriptionUnits;
import com.glenwood.glaceemr.server.application.models.Prescription_;
import com.glenwood.glaceemr.server.application.models.RefillMessageType;
import com.glenwood.glaceemr.server.application.models.SSInbox_;
import com.glenwood.glaceemr.server.application.models.SSMessageInbox_;
import com.glenwood.glaceemr.server.application.models.SSOutbox_;
import com.glenwood.glaceemr.server.application.models.SSPatientMessageMap_;
import com.glenwood.glaceemr.server.application.models.SchedulerAppointment;
import com.glenwood.glaceemr.server.application.models.SchedulerAppointment_;
import com.glenwood.glaceemr.server.application.models.SchedulerResource;
import com.glenwood.glaceemr.server.application.models.SchedulerResource_;
import com.glenwood.glaceemr.server.application.models.SSCancelOutbox;
import com.glenwood.glaceemr.server.application.models.SSCancelOutbox_;
import com.glenwood.glaceemr.server.application.models.SSInbox;
import com.glenwood.glaceemr.server.application.models.SSInbox_;
import com.glenwood.glaceemr.server.application.models.SSMedDetails;
import com.glenwood.glaceemr.server.application.models.SSMedDetails_;
import com.glenwood.glaceemr.server.application.models.SSMessageInbox;
import com.glenwood.glaceemr.server.application.models.SSMessageInbox_;
import com.glenwood.glaceemr.server.application.models.SSOutbox;
import com.glenwood.glaceemr.server.application.models.SSOutbox_;
import com.glenwood.glaceemr.server.application.models.SSPatientMessageMap;
import com.glenwood.glaceemr.server.application.models.SSPatientMessageMap_;
import com.glenwood.glaceemr.server.application.models.SSStatusErrorCodes;



/**
 * @author selvakumar
 *
 */
/*Getting the denied drugs data
 * @param encounterid
 * @param patientid
 */
@Component
@Transactional 
public class RefillRequestSpecification {

	public static Specification<Prescription> getDeniedDrug(final Integer encounterid, final Integer patientid) {
		return new Specification<Prescription>() {
			@Override
			public Predicate toPredicate(Root<Prescription> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				root.fetch(Prescription_.empprofile);
				Predicate encounter=cb.equal(root.get(Prescription_.docPrescEncounterId), encounterid);
				Predicate patientId=cb.equal(root.get(Prescription_.docPrescPatientId), patientid);
				Predicate ndccode=cb.isNotNull(root.get(Prescription_.docPrescNdcCode));
				Predicate value = cb.and(encounter, patientId,ndccode);
			
				return value;
			}
		};
	}
	
	/**
	 * 
	 * @return Condition to get inbox alerts
	 */
	
	public static Specification<SSMessageInbox> getInboxCount() {
		return new Specification<SSMessageInbox>() {
			@Override
			public Predicate toPredicate(Root<SSMessageInbox> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<SSMessageInbox,PharmDetails> ssMsgPharmJoin=root.join(SSMessageInbox_.pharm,JoinType.INNER);
				Join<SSMessageInbox, SSInbox> ssMesgSsInboxJoin=root.join(SSMessageInbox_.ssinbox,JoinType.LEFT);
				Join<SSInbox, SSPatientMessageMap> ssInboxSsMapJoin=ssMesgSsInboxJoin.join(SSInbox_.sspatientmessagemap,JoinType.LEFT);
				Join<SSMessageInbox, RefillMessageType> ssMesgRefillTypeJoin=root.join(SSMessageInbox_.RefillMessageType,JoinType.LEFT);
				Join<SSMessageInbox, EmployeeProfile> ssMesgEmpJoin=root.join(SSMessageInbox_.emp,JoinType.INNER);
				Join<SSMessageInbox, PatientRegistration> ssMesgPatJoin=root.join(SSMessageInbox_.patientregistration,JoinType.LEFT);
				Join<PatientRegistration, NoMatchFoundPatient> PatNoMatchJoin=ssMesgPatJoin.join(PatientRegistration_.nomatchfoundpatient,JoinType.LEFT);
				Join<SSPatientMessageMap, SSOutbox> ssMapSsOutboxJoin=ssInboxSsMapJoin.join(SSPatientMessageMap_.ssoutbox,JoinType.LEFT);
				
				Predicate errorCode=cb.not(cb.coalesce(ssMapSsOutboxJoin.get(SSOutbox_.ssOutboxStatusOrErrorCode), "").in("000","010"));
				Predicate isClose=cb.equal(root.get(SSMessageInbox_.ssMessageInboxIsClose), false);
				query.where(cb.and(errorCode, isClose));
			
				return query.getRestriction();
			}
			
			
		};
			
	}
	
	/**
	 * 
	 * @return Condition to get outbox alerts
	 */
	
	public static Specification<SSCancelOutbox> getOutboxCount(){
		return new Specification<SSCancelOutbox>() {

			@Override
			public Predicate toPredicate(Root<SSCancelOutbox> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<SSCancelOutbox, Prescription> ssCancelPrescJoin=root.join(SSCancelOutbox_.docpresc,JoinType.LEFT);
				Join<SSCancelOutbox, PatientRegistration> ssCancelPatJoin=root.join(SSCancelOutbox_.patientregistration,JoinType.LEFT);
				Join<SSCancelOutbox, LocationDetails> ssCancelLocJoin=root.join(SSCancelOutbox_.locationdetails,JoinType.LEFT);
				Join<LocationDetails, PrescriberDetails> locPrescJoin=ssCancelLocJoin.join(LocationDetails_.prescriberdetails,JoinType.LEFT);
				Join<SSCancelOutbox, PharmDetails> ssCancelPharmJoin=root.join(SSCancelOutbox_.pharmacydetails,JoinType.LEFT);
				Join<Prescription, EmployeeProfile> prescEmpJoin=ssCancelPrescJoin.join(Prescription_.empprofile,JoinType.LEFT);
				Join<Prescription, Encounter> prescEncJoin=ssCancelPrescJoin.join(Prescription_.encounter,JoinType.LEFT);
				
				Predicate response=cb.equal(root.get(SSCancelOutbox_.ssCancelOutboxResponseReceived), false);
				Predicate rxName=cb.notEqual(ssCancelPrescJoin.get(Prescription_.rxname),"");
				Predicate timestamp=cb.lessThan(root.get(SSCancelOutbox_.ssCancelOutboxSentDateTime), ""+cb.currentTimestamp());
				query.where(cb.and(response,rxName,timestamp));
				query.groupBy(locPrescJoin.get(PrescriberDetails_.doctorid),root.get(SSCancelOutbox_.ssCancelOutboxId));
				
				return query.getRestriction();
			}
			
		};
	}
	
	/**
	 * 
	 * @param erxUserAlertType
	 * @param userId
	 * @param isNewPage
	 * @return Specification to get all inbox alerts
	 */
	public static Specification<SSMessageInbox> getInboxAlert(final String erxUserAlertType,final String userId,boolean isNewPage){
		return new Specification<SSMessageInbox>() {
			@Override
			public Predicate toPredicate(Root<SSMessageInbox> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Join<SSMessageInbox,PharmDetails> ssMsgPharmJoin=root.join(SSMessageInbox_.pharm,JoinType.INNER);
				Join<SSMessageInbox, SSInbox> ssMesgSsInboxJoin=root.join(SSMessageInbox_.ssinbox,JoinType.LEFT);
				Join<SSInbox, SSPatientMessageMap> ssInboxSsMapJoin=ssMesgSsInboxJoin.join(SSInbox_.sspatientmessagemap,JoinType.LEFT);
				Join<SSMessageInbox, RefillMessageType> ssMesgRefillTypeJoin=root.join(SSMessageInbox_.RefillMessageType,JoinType.LEFT);
				Join<SSMessageInbox, EmployeeProfile> ssMesgEmpJoin=root.join(SSMessageInbox_.emp,JoinType.INNER);
				Join<SSMessageInbox, PatientRegistration> ssMesgPatJoin=root.join(SSMessageInbox_.patientregistration,JoinType.LEFT);
				Join<PatientRegistration, NoMatchFoundPatient> PatNoMatchJoin=ssMesgPatJoin.join(PatientRegistration_.nomatchfoundpatient,JoinType.LEFT);
				Join<SSPatientMessageMap, SSOutbox> ssMapSsOutboxJoin=ssInboxSsMapJoin.join(SSPatientMessageMap_.ssoutbox,JoinType.LEFT);
				Join<SSPatientMessageMap, Prescription> ssMapPrescJoin=ssInboxSsMapJoin.join(SSPatientMessageMap_.docpresc,JoinType.LEFT);
				
				root.fetch(SSMessageInbox_.emp,JoinType.INNER);
				root.fetch(SSMessageInbox_.patientregistration,JoinType.LEFT);
				Predicate errorCode=cb.not(cb.coalesce(ssMapSsOutboxJoin.get(SSOutbox_.ssOutboxStatusOrErrorCode), "").in("000","010"));
				Predicate isClose=cb.equal(root.get(SSMessageInbox_.ssMessageInboxIsClose), false);
				Predicate empId=null;
				if(erxUserAlertType.equals("OTHERUSERALERT"))
				{
					empId=cb.notEqual(ssMesgEmpJoin.get(EmployeeProfile_.empProfileEmpid),userId);
				}
				else if(erxUserAlertType.equals("MYALERT"))
				{
					empId=cb.equal(ssMesgEmpJoin.get(EmployeeProfile_.empProfileEmpid),userId);
				}
				query.where(cb.and(errorCode, isClose,empId));
				return query.getRestriction();
			}
			
		};
		
	}
	
	/**
	 * 
	 * @param erxUserAlertType
	 * @param userId
	 * @param isNewPage
	 * @return Specification to get all outbox alerts
	 */
	public static Specification<SSCancelOutbox> getOutboxAlert(String erxUserAlertType,String userId,boolean isNewPage){
		return new Specification<SSCancelOutbox>() {

			@Override
			public Predicate toPredicate(Root<SSCancelOutbox> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<SSCancelOutbox, Prescription> ssCancelPrescJoin=root.join(SSCancelOutbox_.docpresc,JoinType.LEFT);
				Join<SSCancelOutbox, PatientRegistration> ssCancelPatJoin=root.join(SSCancelOutbox_.patientregistration,JoinType.LEFT);
				Join<SSCancelOutbox, LocationDetails> ssCancelLocJoin=root.join(SSCancelOutbox_.locationdetails,JoinType.LEFT);
				Join<LocationDetails, PrescriberDetails> locPrescJoin=ssCancelLocJoin.join(LocationDetails_.prescriberdetails,JoinType.LEFT);
				Join<SSCancelOutbox, PharmDetails> ssCancelPharmJoin=root.join(SSCancelOutbox_.pharmacydetails,JoinType.LEFT);
				Join<Prescription, EmployeeProfile> prescEmpJoin=ssCancelPrescJoin.join(Prescription_.empprofile,JoinType.LEFT);
				Join<Prescription, Encounter> prescEncJoin=ssCancelPrescJoin.join(Prescription_.encounter,JoinType.LEFT);
				
				Predicate response=cb.equal(root.get(SSCancelOutbox_.ssCancelOutboxResponseReceived), false);
				Predicate rxName=cb.notEqual(ssCancelPrescJoin.get(Prescription_.rxname),"");
				Predicate timeStamp=cb.lessThan(root.get(SSCancelOutbox_.ssCancelOutboxSentDateTime), ""+cb.currentTimestamp());
				query.where(cb.and(response,rxName,timeStamp));
				
				return query.getRestriction();
			}
			
		};
		
	}
	
	/**
	 * 
	 * @param patientId
	 * @return Specification to get pending refill request data
	 */
	public static Specification<SSMessageInbox> getPendingRequests(final int patientId) {
		return new Specification<SSMessageInbox>() {
			@Override
			public Predicate toPredicate(Root<SSMessageInbox> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<SSMessageInbox,PharmDetails> ssMsgPharmJoin=root.join(SSMessageInbox_.pharm,JoinType.INNER);
				Join<SSMessageInbox, SSInbox> ssMesgSsInboxJoin=root.join(SSMessageInbox_.ssinbox,JoinType.LEFT);
				Join<SSInbox, SSPatientMessageMap> ssInboxSsMapJoin=ssMesgSsInboxJoin.join(SSInbox_.sspatientmessagemap,JoinType.LEFT);
				Join<SSInbox,SSMedDetails> ssInboxSsMedJoin=ssMesgSsInboxJoin.join(SSInbox_.ssmeddetails,JoinType.INNER);
				Join<SSMedDetails,PrescriptionUnits> ssMedPrescUnitsJoin=ssInboxSsMedJoin.join(SSMedDetails_.prescriptionunits,JoinType.LEFT);
				Join<SSMessageInbox, RefillMessageType> ssMesgRefillTypeJoin=root.join(SSMessageInbox_.RefillMessageType,JoinType.LEFT);
				Join<SSMessageInbox, EmployeeProfile> ssMesgEmpJoin=root.join(SSMessageInbox_.emp,JoinType.INNER);
				Join<EmployeeProfile,PrescriberDetails> EmpPrescJoin=ssMesgEmpJoin.join(EmployeeProfile_.prescriberDetails,JoinType.INNER);
				Join<PrescriberDetails,LocationDetails> precLocJoin=EmpPrescJoin.join(PrescriberDetails_.locationDetails,JoinType.LEFT);
				Join<SSMessageInbox, PatientRegistration> ssMesgPatJoin=root.join(SSMessageInbox_.patientregistration,JoinType.INNER);
				Join<SSMessageInbox, Chart> ssMesgChartJoin=root.join(SSMessageInbox_.chart,JoinType.INNER);
				Join<PatientRegistration, NoMatchFoundPatient> patNoMathJoin=ssMesgPatJoin.join(PatientRegistration_.nomatchfoundpatient,JoinType.LEFT);
				Join<SSPatientMessageMap, SSOutbox> ssMapSsoutboxJoin=ssInboxSsMapJoin.join(SSPatientMessageMap_.ssoutbox,JoinType.LEFT);
				Join<SSPatientMessageMap,Prescription> ssMapPrescJoin=ssInboxSsMapJoin.join(SSPatientMessageMap_.docpresc,JoinType.LEFT);
				Join<SSMedDetails,NdcDrugBrandMap> ssMedNdcJoin=ssInboxSsMedJoin.join(SSMedDetails_.ndcdrugbrandmap,JoinType.LEFT);
				Join<NdcDrugBrandMap,Brandname> ndcBrandJoin=ssMedNdcJoin.join(NdcDrugBrandMap_.brandName,JoinType.LEFT);
				Join<NdcDrugBrandMap,DrugRelationMap> ndcDrugRelJoin=ssMedNdcJoin.join(NdcDrugBrandMap_.drugRelationMap,JoinType.LEFT);
				Join<DrugRelationMap,DrugForm> drugRelFormJoin=ndcDrugRelJoin.join(DrugRelationMap_.drugForm,JoinType.LEFT);
				Join<DrugRelationMap,DrugDosage> drugRelDoseJoin=ndcDrugRelJoin.join(DrugRelationMap_.drugDosage,JoinType.LEFT);
				Join<SSOutbox,SSStatusErrorCodes> ssOutboxStatusJoin=ssMapSsoutboxJoin.join(SSOutbox_.ssstatuserrorcodes,JoinType.LEFT);
				
				root.fetch(SSMessageInbox_.emp,JoinType.INNER);
				ssMedNdcJoin.on(cb.lessThan(ssMedNdcJoin.get(NdcDrugBrandMap_.maindrugcode), 1000000));
				Predicate statusCode=cb.not(cb.coalesce(ssMapSsoutboxJoin.get(SSOutbox_.ssOutboxStatusOrErrorCode), "").in("000","010"));
				Predicate close=cb.equal(root.get(SSMessageInbox_.ssMessageInboxIsClose), false);
				Predicate patient=cb.equal(ssMesgPatJoin.get(PatientRegistration_.patientRegistrationId), patientId);
				
				query.where(cb.and(statusCode,close,patient));
				return query.getRestriction();
			}
			
			
		};
			
	}
	
	/**
	 * 
	 * @return 	Specification to get Scheduler type ID
	 */
	public static Specification<InitialSettings> getInitValue() {
		return new Specification<InitialSettings>() {

			@Override
			public Predicate toPredicate(Root<InitialSettings> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.equal(root.get(InitialSettings_.initialSettingsOptionId), 62));
				return query.getRestriction();
			}
		};
				
	} 
	
	/**
	 * 
	 * @param data
	 * @return Specification to get type of scheduler
	 */
	public static Specification<BillingConfigTable> getBillingConfig(final InitialSettings data) {
		return new Specification<BillingConfigTable>() {

			@Override
			public Predicate toPredicate(Root<BillingConfigTable> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.and(cb.equal(root.get(BillingConfigTable_.billingConfigTableLookupId), 101),cb.equal(root.get(BillingConfigTable_.billingConfigTableConfigId),Integer.parseInt(data.getInitialSettingsOptionValue()))));
				return query.getRestriction();
			}
		};
				
	} 
	
	/**
	 * 
	 * @param type
	 * @param patientId
	 * @return Specification to get next appointment details
	 */
	public static Specification<SchedulerAppointment> getNextAppt(final String type, final int patientId) {
		return new Specification<SchedulerAppointment>() {

			@Override
			public Predicate toPredicate(Root<SchedulerAppointment> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<SchedulerAppointment, SchedulerResource> aptResJoin=root.join(SchedulerAppointment_.schResLoc,JoinType.INNER);
				Join<SchedulerAppointment,AppReferenceValues> aptH113Join=root.join(SchedulerAppointment_.App_Reference_ValuesApptType,JoinType.LEFT);
				
				aptResJoin.on(cb.and(cb.notEqual(aptResJoin.get(SchedulerResource_.schResourceIsdoctor), 0),cb.lessThan(aptResJoin.get(SchedulerResource_.schResourceIsdoctor), 10)));
				aptH113Join.on(cb.and(cb.equal(aptH113Join.get(AppReferenceValues_.App_Reference_Values_tableId), 402),cb.equal(aptH113Join.get(AppReferenceValues_.App_Reference_Values_reason_type), 1),cb.equal(aptH113Join.get(AppReferenceValues_.App_Reference_Values_isActive), true)));	
				Predicate patient=cb.equal(root.get(SchedulerAppointment_.schApptPatientId),patientId);
				Predicate apptStatus=cb.not(root.get(SchedulerAppointment_.schApptStatus).in(6,12));
				Predicate apptTime; 
				if(type.equalsIgnoreCase("Modern Scheduler")){
					apptTime=cb.greaterThan(root.get(SchedulerAppointment_.schApptStarttime), cb.currentTimestamp());
				}
				else{
					apptTime=cb.greaterThan(root.get(SchedulerAppointment_.schApptDate), cb.currentTimestamp());
				}
				query.where(cb.and(patient,apptStatus,apptTime)).orderBy(cb.asc(root.get(SchedulerAppointment_.schApptDate)),cb.asc(root.get(SchedulerAppointment_.schApptStarttime)));
				return query.getRestriction();
				
			}
		};
				
	} 
	
	/**
	 * 
	 * @param type
	 * @param patientId
	 * @return Specification to get prev appointment details
	 */
	public static Specification<Encounter> getPrevApptDate(String type, final int patientId) {
		return new Specification<Encounter>() {

			@Override
			public Predicate toPredicate(Root<Encounter> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<Encounter,Chart> encChartJoin=root.join(Encounter_.chart,JoinType.INNER);
				Join<Chart,PatientRegistration> chartPatJoin=encChartJoin.join(Chart_.patientRegistrationTable,JoinType.INNER);
				
				chartPatJoin.on(cb.equal(chartPatJoin.get(PatientRegistration_.patientRegistrationId), patientId));
				query.where(cb.and(cb.equal(root.get(Encounter_.encounterDate), cb.currentTimestamp()),cb.equal(root.get(Encounter_.encounterType), 1))).orderBy(cb.desc(root.get(Encounter_.encounterDate)));
				return query.getRestriction();
			}
		};
				
	} 
	
	public static Specification<PatientRegistration> getPatientDetails(final int patientId) {
		return new Specification<PatientRegistration>() {

			@Override
			public Predicate toPredicate(Root<PatientRegistration> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientRegistration,PatientInsDetail> patInsJoin=root.join(PatientRegistration_.patientInsuranceTable,JoinType.LEFT);
				Join<PatientInsDetail,InsCompAddr> insCompJoin=patInsJoin.join(PatientInsDetail_.insCompAddr,JoinType.LEFT);
				Join<InsCompAddr,InsCompany> insAddCompjoin=insCompJoin.join(InsCompAddr_.insCompany,JoinType.LEFT);
				Join<PatientRegistration,ReferringDoctor> patH076Join=root.join(PatientRegistration_.referringPhyTable,JoinType.LEFT);
				Join<PatientRegistration,EmployeeProfile> patEmpJoin=root.join(PatientRegistration_.empProfile,JoinType.LEFT);
				Join<PatientRegistration,AccountType> patAccTypeJoin=root.join(PatientRegistration_.ptAccType,JoinType.LEFT);
				Join<PatientRegistration,AuthorizationMaster> patAuthJoin=root.join(PatientRegistration_.authMaster,JoinType.LEFT);
				Join<PatientRegistration,PosTable> patPosJoin=root.join(PatientRegistration_.posTable,JoinType.LEFT);
				
				root.fetch(PatientRegistration_.patientInsuranceTable);
				root.fetch(PatientRegistration_.empProfile);
				patInsJoin.on(cb.and(cb.equal(patInsJoin.get(PatientInsDetail_.patientInsDetailIsactive), true),cb.or(cb.equal(patInsJoin.get(PatientInsDetail_.patientInsDetailInstype), 1),cb.equal(patInsJoin.get(PatientInsDetail_.patientInsDetailInstype), 2))));
				query.where(cb.equal(root.get(PatientRegistration_.patientRegistrationId), patientId));
				return query.getRestriction();
			}
		};
				
	} 
	
	public static Specification<PosTable> getPosDetails(final int patientId) {
		return new Specification<PosTable>() {

			@Override
			public Predicate toPredicate(Root<PosTable> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PosTable,Encounter> posEncJoin=root.join(PosTable_.encounter,JoinType.INNER);
				Join<Encounter,Chart> encChartJoin=posEncJoin.join(Encounter_.chart,JoinType.INNER);
				
				query.where(cb.and(cb.equal(posEncJoin.get(Encounter_.encounterType), 1),cb.equal(encChartJoin.get(Chart_.chartPatientid), patientId))).orderBy(cb.desc(posEncJoin.get(Encounter_.encounterDate)));
				return query.getRestriction();
			}
		};
				
	} 
}
