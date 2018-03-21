package com.glenwood.glaceemr.server.application.specifications;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.CoreClassGendrug;
import com.glenwood.glaceemr.server.application.models.CoreClassGendrug_;
import com.glenwood.glaceemr.server.application.models.CoreClassHierarchy;
import com.glenwood.glaceemr.server.application.models.CoreClassHierarchy_;
import com.glenwood.glaceemr.server.application.models.CoreGenproduct;
import com.glenwood.glaceemr.server.application.models.CoreGenproduct_;
import com.glenwood.glaceemr.server.application.models.CurrentMedication;
import com.glenwood.glaceemr.server.application.models.CurrentMedication_;
import com.glenwood.glaceemr.server.application.models.DrugSchedule;
import com.glenwood.glaceemr.server.application.models.DrugSchedule_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.MedStatus;
import com.glenwood.glaceemr.server.application.models.MedStatus_;
import com.glenwood.glaceemr.server.application.models.MedsAdminLog;
import com.glenwood.glaceemr.server.application.models.MedsAdminLog_;
import com.glenwood.glaceemr.server.application.models.MedsAdminPlan;
import com.glenwood.glaceemr.server.application.models.MedsAdminPlanShortcut;
import com.glenwood.glaceemr.server.application.models.MedsAdminPlanShortcut_;
import com.glenwood.glaceemr.server.application.models.MedsAdminPlan_;
import com.glenwood.glaceemr.server.application.models.NdcPkgProduct;
import com.glenwood.glaceemr.server.application.models.NdcPkgProduct_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.Prescription;
import com.glenwood.glaceemr.server.application.models.Prescription_;

/**
 * This class represents specifications needed for prescribing/viewing medications for a patient.   
 * @author selvakumar
 *
 */
@Component
@Transactional 
public class PrescripitonSpecification {
	/**
	 * To get the patient's active medications which are reported by patient at the visit.
	 * @param patientid
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Specification<CurrentMedication> getactivemedwithclass( final Integer patientid,final Date startDate,final Date endDate) {
		return new Specification<CurrentMedication>() {
			@Override
			public Predicate toPredicate(Root<CurrentMedication> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {


				Join<CurrentMedication,MedStatus> currjoin=root.join(CurrentMedication_.medstatus,JoinType.INNER);
				Join<CurrentMedication,Encounter> currjoin1=root.join(CurrentMedication_.encounter,JoinType.INNER);
				Join<Encounter, Chart> cc=currjoin1.join(Encounter_.chart, JoinType.INNER);
				Join<Chart, PatientRegistration> patientjoin=cc.join(Chart_.patientRegistrationTable,JoinType.INNER);



				String likePattern = getLikePattern("active");
				Predicate patientId=cb.equal(root.get(CurrentMedication_.currentMedicationPatientId), patientid);
				Predicate isactive=cb.equal(root.get(CurrentMedication_.currentMedicationIsActive),true);
				Predicate medstatus=cb.like(cb.lower(currjoin.get(MedStatus_.medStatusGroup)),likePattern);
				Predicate orderDate = null;
				if(startDate!=null&&endDate!=null) {
					orderDate = cb.lessThanOrEqualTo(root.get(CurrentMedication_.currentMedicationOrderOn), endDate);
					//					orderDate = cb.between(root.get(CurrentMedication_.currentMedicationOrderOn), startDate, endDate);
				}
				root.fetch(CurrentMedication_.medstatus,JoinType.INNER);
				root.fetch(CurrentMedication_.encounter,JoinType.INNER);
				if(startDate!=null&&endDate!=null) {
					cq.where(cb.and(patientId, isactive,medstatus,orderDate));
				}
				else 
					cq.where(cb.and(patientId, isactive,medstatus));
				return cq.getRestriction();




			}
		};
	}

	/**
	 * To get the patient's active medication which are being prescribed by the provider.
	 * @param patientid
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Specification<Prescription> getactivemedwithclasspresc( final Integer patientid,final Date startDate,final Date endDate) {
		return new Specification<Prescription>() {
			@Override
			public Predicate toPredicate(Root<Prescription> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {


				Join<Prescription,Encounter> currjoin1=root.join(Prescription_.encounter,JoinType.INNER);
				//					Join<Prescription,MedsAdminPlan> medPlanJoin = root.join(Prescription_.medsAdminPlan,JoinType.INNER);
				Join<Prescription,MedStatus> currjoin=root.join(Prescription_.medstatus,JoinType.INNER);
				Join<Encounter, Chart> cc=currjoin1.join(Encounter_.chart, JoinType.INNER);
				//					Join<Chart, PatientRegistration> patientjoin=cc.join(Chart_.patientRegistrationTable,JoinType.INNER);

				String likePattern = getLikePattern("active");
				Predicate patientId=cb.equal(root.get(Prescription_.docPrescPatientId), patientid);
				Predicate isactive=cb.equal(root.get(Prescription_.docPrescIsActive),true);
				Predicate status=root.get(Prescription_.docPrescStatus).in(1,2,3);
				Predicate ismedsup=cb.equal(root.get(Prescription_.docPrescIsMedSup),false);
				Predicate medstatus=cb.like(cb.lower(currjoin.get(MedStatus_.medStatusGroup)),likePattern);
				Predicate orderDate = null;
				if(startDate!=null&&endDate!=null) {
					orderDate = cb.lessThanOrEqualTo((root.get(Prescription_.docPrescOrderedDate)).as(Date.class), endDate);
					//						orderDate = cb.between(root.get(Prescription_.docPrescOrderedDate), startDate, endDate);
				}
				root.fetch(Prescription_.medstatus,JoinType.INNER);
				root.fetch(Prescription_.encounter,JoinType.INNER);

				//					root.fetch(Prescription_.medsAdminPlan,JoinType.LEFT);
				if(startDate!=null&&endDate!=null) {
					cq.where(cb.and(patientId, isactive,medstatus,ismedsup,orderDate,status));
				}
				else
					cq.where(cb.and(patientId, isactive,medstatus,ismedsup));
				return cq.getRestriction();
			}
		};
	}

	/**
	 * To get the plan Ids for a single medication of a patient.
	 * @param prescId
	 * @return
	 */
	public static Specification<MedsAdminPlan> getMedsPlanIds(final Integer prescId) {
		return new Specification<MedsAdminPlan>() {

			@Override
			public Predicate toPredicate(Root<MedsAdminPlan> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate planId = cb.equal(root.get(MedsAdminPlan_.medsAdminPlanMedicationId),prescId);
				return planId;
			}
		};
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
	 * To get the specification for getting the med administered history based on the plan Id
	 * @param planid
	 * @return
	 */
	public static Specification<MedsAdminLog> getMedAdministrationLogHistoryData(final Integer planid) {
		return new Specification<MedsAdminLog>() {

			@Override
			public Predicate toPredicate(Root<MedsAdminLog> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate planIdExists = cb.equal(root.get(MedsAdminLog_.medsAdminLogPlanId),planid);
				root.fetch(MedsAdminLog_.empprofile);
				return planIdExists;
			}
		};

	}

	/**
	 * To get the single log of a administered medications based on its Id
	 * @param logid
	 * @return
	 */
	public static Specification<MedsAdminLog> getMedAdministrationLogData(final Integer logid) {
		return new Specification<MedsAdminLog>() {

			@Override
			public Predicate toPredicate(Root<MedsAdminLog> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate planIdExists = cb.equal(root.get(MedsAdminLog_.medsAdminLogId),logid);
				root.fetch(MedsAdminLog_.empprofile);
				return planIdExists;
			}
		};

	}

	/*To get the Current Medical supplies for the particular patient
	 * @param patientid
	 * @return 
	 * 
	 */
	public static Specification<CurrentMedication> getactivemedicalsupplieswithclass( final Integer patientid,final Date startDate,final Date endDate) {
		return new Specification<CurrentMedication>() {
			@Override 
			public Predicate toPredicate(Root<CurrentMedication> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {


				Join<CurrentMedication,MedStatus> currjoin=root.join(CurrentMedication_.medstatus,JoinType.INNER);
				Join<CurrentMedication,Encounter> currjoin1=root.join(CurrentMedication_.encounter,JoinType.INNER);
				Join<Encounter, Chart> cc=currjoin1.join(Encounter_.chart, JoinType.INNER);
				Join<Chart, PatientRegistration> patientjoin=cc.join(Chart_.patientRegistrationTable,JoinType.INNER);



				String likePattern = getLikePattern("active");
				Predicate patientId=cb.equal(root.get(CurrentMedication_.currentMedicationPatientId), patientid);
				Predicate isactive=cb.equal(root.get(CurrentMedication_.currentMedicationIsActive),true);
				Predicate medstatus=cb.like(cb.lower(currjoin.get(MedStatus_.medStatusGroup)),likePattern);
				Predicate ismedsup=cb.equal(root.get(CurrentMedication_.currentMedicationIsMedSup),true);

				Predicate orderDate = null;
				if(startDate!=null&&endDate!=null) {
					orderDate = cb.lessThanOrEqualTo(root.get(CurrentMedication_.currentMedicationOrderOn), endDate);
					//					orderDate = cb.between(root.get(CurrentMedication_.currentMedicationOrderOn), startDate, endDate);
				}
				root.fetch(CurrentMedication_.medstatus,JoinType.INNER);
				root.fetch(CurrentMedication_.encounter,JoinType.INNER);
				if(startDate!=null&&endDate!=null) {
					cq.where(cb.and(patientId, isactive,medstatus,orderDate,ismedsup));
				}
				else 
					cq.where(cb.and(patientId, isactive,medstatus,ismedsup));
				return cq.getRestriction();


			}
		};
	}

	
	
	/*To get the prescribed Medical supplies for the particular patient
	 * @param patientid
	 * @return 
	 * 
	 */
	public static Specification<Prescription> getactivemedicalsupplieswithclasspresc( final Integer patientid,final Date startDate,final Date endDate) {
		return new Specification<Prescription>() {
			@Override
			public Predicate toPredicate(Root<Prescription> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {


				Join<Prescription,Encounter> currjoin1=root.join(Prescription_.encounter,JoinType.INNER);
				Join<Prescription,MedStatus> currjoin=root.join(Prescription_.medstatus,JoinType.INNER);
				Join<Encounter, Chart> cc=currjoin1.join(Encounter_.chart, JoinType.INNER);

				String likePattern = getLikePattern("active");
				Predicate patientId=cb.equal(root.get(Prescription_.docPrescPatientId), patientid);
				Predicate isactive=cb.equal(root.get(Prescription_.docPrescIsActive),true);
				Predicate ismedsup=cb.equal(root.get(Prescription_.docPrescIsMedSup),true);
				Predicate medstatus=cb.like(cb.lower(currjoin.get(MedStatus_.medStatusGroup)),likePattern);
				Predicate orderDate = null;
				if(startDate!=null&&endDate!=null) {
					orderDate = cb.lessThanOrEqualTo(root.get(Prescription_.docPrescOrderedDate), endDate);
					//					orderDate = cb.between(root.get(Prescription_.docPrescOrderedDate), startDate, endDate);
				}
				root.fetch(Prescription_.medstatus,JoinType.INNER);
				root.fetch(Prescription_.encounter,JoinType.INNER);

				if(startDate!=null&&endDate!=null) {
					cq.where(cb.and(patientId, isactive,medstatus,ismedsup,orderDate));
				}
				else
					cq.where(cb.and(patientId, isactive,medstatus,ismedsup));
				return cq.getRestriction();
			}
		};
	}
	
	/*
	 * To get the frequecny list based on selected medication
	 */
	public static Specification<DrugSchedule> getfrequencylist(final String brandname, final String mode) {
		return new Specification<DrugSchedule>() {

			@Override
			public Predicate toPredicate(Root<DrugSchedule> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Join<DrugSchedule, Prescription> join1=root.join(DrugSchedule_.presc,JoinType.INNER);
				Predicate Predicate2;		
				query.distinct(true);
				Predicate predicate1=cb.equal(root.get(DrugSchedule_.drugScheduleType),0);
				query.orderBy(cb.asc(root.get(DrugSchedule_.drugScheduleName)));
				if(mode.equalsIgnoreCase("getallfrequencylist")){
					query.where(predicate1);
					
				}else{
					 Predicate2=cb.like((cb.lower(join1.get(Prescription_.rxname))), getLikePattern(brandname));
					 query.where(predicate1,Predicate2);
				}
					 
				return query.getRestriction();
				
			}
		};
		
	}
	public static Specification<Prescription> getactivemedwithclassIdpresc( final Integer patientid,final String classId,final Integer encounterId,final Encounter encounterEntity) {
		return new Specification<Prescription>() {
			@Override
			public Predicate toPredicate(Root<Prescription> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				Join<Prescription,Encounter> currjoin1=root.join(Prescription_.encounter,JoinType.INNER);
				Join<Prescription,MedStatus> currjoin=root.join(Prescription_.medstatus,JoinType.INNER);
				currjoin1.join(Encounter_.chart, JoinType.INNER);
				Join<Prescription, NdcPkgProduct> pkgjoin=root.join(Prescription_.ndcPkgProduct,JoinType.INNER);
				Join<NdcPkgProduct, CoreGenproduct> corejoin=pkgjoin.join(NdcPkgProduct_.coregenproduct,JoinType.INNER);
				Join<CoreGenproduct, CoreClassGendrug> classjoin=corejoin.join(CoreGenproduct_.coreclassgendrug,JoinType.INNER);
				Join<CoreClassGendrug, CoreClassHierarchy> hierarchyjon=classjoin.join(CoreClassGendrug_.coreClassHierarchyTable,JoinType.INNER);
				String likePattern = getLikePattern("active");
				Predicate patientId=cb.equal(root.get(Prescription_.docPrescPatientId), patientid);
				Predicate isactive=cb.equal(root.get(Prescription_.docPrescIsActive),true);
				Predicate ismedsup=cb.equal(root.get(Prescription_.docPrescIsMedSup),false);
				Predicate medstatus=cb.like(cb.lower(currjoin.get(MedStatus_.medStatusGroup)),likePattern);
				Predicate classIdPres=cb.equal(hierarchyjon.get(CoreClassHierarchy_.parentClassId), classId);
				root.fetch(Prescription_.medstatus,JoinType.INNER);
				root.fetch(Prescription_.encounter,JoinType.INNER);
				if((encounterId!=-1)&&(encounterEntity.getEncounterStatus()==3)){
					Predicate encounterDatePred=cb.lessThanOrEqualTo(root.get(Prescription_.docPrescOrderedDate), encounterEntity.getEncounterDate());
					cq.where(cb.and(patientId, isactive,medstatus,ismedsup,classIdPres,encounterDatePred));
				}else{
					cq.where(cb.and(patientId, isactive,medstatus,ismedsup,classIdPres));
				}
				return cq.getRestriction();
			}
		};
	}
	/**
	 * Specification to get the list of distinct drug classes
	 * @param isActive
	 * @return Specification<CoreClassHierarchy>
	 */
	public static Specification<CoreClassHierarchy> drugClasses()
	{
		return new Specification<CoreClassHierarchy>() {

			@Override
			public Predicate toPredicate(Root<CoreClassHierarchy> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Subquery<String> subquery = query.subquery(String.class);
				Root<CoreClassHierarchy> subqueryFrom = subquery.from(CoreClassHierarchy.class);
				Expression<String> subQExp=subqueryFrom.get(CoreClassHierarchy_.parentClassId);
				subquery.select(subQExp);
				Join<CoreClassHierarchy,CoreClassGendrug> classJoin=root.join("coreClassGendrugTable",JoinType.LEFT);
				classJoin.join("coreGendrugTable",JoinType.LEFT);
				query.distinct(true);
				query.orderBy(cb.asc(root.get(CoreClassHierarchy_.displayName)));
				Predicate drugClasses = cb.in(root.get(CoreClassHierarchy_.classId)).value(subquery);
				return drugClasses;
			}
		};
	}
	
	/**
	 * Specification to get the med status
	 * @param isActive
	 * @return Specification<MedStatus>
	 */
	public static Specification<MedStatus> medStatus(final Integer medStatusId)
	{
		return new Specification<MedStatus>() {

			@Override
			public Predicate toPredicate(Root<MedStatus> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate isActive=cb.equal(root.get(MedStatus_.medStatusIsActive), true); 
				Predicate medStatusPred = cb.equal(root.get(MedStatus_.medStatusId),medStatusId);
				Predicate medStatus=cb.and(isActive,medStatusPred);
				return medStatus;
			}
		};
	}
	
	/**
	 * Specification to get the patients completed medications
	 * @param patientId
	 * @param chartId
	 * @return Specification<Prescription>
	 */
	public static Specification<Prescription> getPatientCompletedMedications(final Integer patientId, final Integer chartId)
	{
		return new Specification<Prescription>() {

			@Override
			public Predicate toPredicate(Root<Prescription> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				
				Expression<Integer> prescStatus=root.get(Prescription_.docPrescStatus);
				Predicate prescStatusPredicate=cb.and(cb.equal(root.get(Prescription_.docPrescIsActive), true), prescStatus.in(1,2,3)); 
				Predicate patientIdPredicate = cb.equal(root.get(Prescription_.docPrescPatientId), patientId);
				return cq.where(prescStatusPredicate, patientIdPredicate).getRestriction();
			}
		};
	}
	
	/**
	 * Specification to get the patient's refill request history
	 * @param patientId
	 * @param chartId
	 * @return Specification<Encounter>
	 */
	public static Specification<Encounter> getRefillRequestHistory(final int encounterType, final Integer encounterReason, final int chartId)
	{
		return new Specification<Encounter>() {

			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				
				return cq.where(cb.equal(root.get(Encounter_.encounterChartid), chartId), cb.equal(root.get(Encounter_.encounterType), encounterType), cb.equal(root.get(Encounter_.encounterReason), encounterReason)).orderBy(cb.desc(root.get(Encounter_.encounterDate))).getRestriction();
			}
		};
	}
	
	public static Specification<MedsAdminPlanShortcut> getshortcuts() {
		return new Specification<MedsAdminPlanShortcut>() {

			@Override
			public Predicate toPredicate(Root<MedsAdminPlanShortcut> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.orderBy(cb.asc(root.get(MedsAdminPlanShortcut_.medsAdminPlanShortcutId)));
				return query.getRestriction();
			}
		};
	}
}


