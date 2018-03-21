package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.PatientAllergies;
import com.glenwood.glaceemr.server.application.models.PatientAllergies_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.PlanInstruction;
import com.glenwood.glaceemr.server.application.models.PlanInstruction_;
import com.glenwood.glaceemr.server.application.models.PlanType;
import com.glenwood.glaceemr.server.application.models.PlanType_;
import com.glenwood.glaceemr.server.application.models.ProblemList;
import com.glenwood.glaceemr.server.application.models.ProblemList_;

public class PortalMedicalSummarySpecification {

	
	
	/**
	 * @param username	: used to get patient details of a patient of that username.
	 * @return list of patient details.
	 */	
	public static Specification<Encounter> getPlanOfCareByPatientId(final int patientId, final int chartId) {

		   return new Specification<Encounter>() {

			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
								
			    Predicate planOfCarePredicate=cq.where(cb.equal(root.get(Encounter_.encounterChartid), chartId)).getRestriction();
							    
				return planOfCarePredicate;
			}
			   
		};
	   
	}
	
	/**
	 * @param username	: used to get patient details of a patient of that username.
	 * @return list of patient details.
	 */	
	public static Specification<PlanType> getPlanOfCareDetailsByPatientId(final int patientId, final int encounterId) {

		   return new Specification<PlanType>() {

			@Override
			public Predicate toPredicate(Root<PlanType> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Join<PlanType, PlanInstruction> planInstructionsJoin=(Join<PlanType, PlanInstruction>) root.fetch(PlanType_.planTypeInstructions, JoinType.INNER);
				Join<PlanInstruction, PatientClinicalElements> planInstructionClinicalElementsJoin=(Join<PlanInstruction, PatientClinicalElements>) planInstructionsJoin.fetch(PlanInstruction_.patientPlanClinicalElements,JoinType.INNER);
								
			    Predicate planOfCarePredicate=cq.where(cb.notEqual(root.get(PlanType_.planTypeId), 6),
			    		cb.equal(planInstructionClinicalElementsJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid), patientId),
			    		cb.equal(planInstructionClinicalElementsJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId)).getRestriction();
							    
				return planOfCarePredicate;
			}
			   
		};
	   
	}
	
	
	/**
	 * @param username	: used to get patient details of a patient of that username.
	 * @return list of patient details.
	 */	
	public static Specification<PatientClinicalElements> getPlanOfCareInstructionsByPatientId(final int patientId, final int encounterId) {

		   return new Specification<PatientClinicalElements>() {

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Join<PatientClinicalElements, PlanInstruction> planInstructionClinicalElementsJoin=(Join<PatientClinicalElements, PlanInstruction>) root.fetch(PatientClinicalElements_.planInstruction, JoinType.INNER);
				Join<PlanInstruction, PlanType> planInstructionsJoin= (Join<PlanInstruction, PlanType>) planInstructionClinicalElementsJoin.fetch(PlanInstruction_.planType, JoinType.INNER);
								
			    Predicate planOfCarePredicate=cq.where(cb.notEqual(planInstructionsJoin.get(PlanType_.planTypeId), 6),
			    		cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid), patientId),
			    		cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId)).getRestriction();
							    
				return planOfCarePredicate;
			}
			   
		};
	   
	}
	

	/**
	 * @param patientId	: used to get problems list of a patient of that particular id
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<ProblemList> getPatientProblemList(final int patientId, final String problemType)
	{
		return new Specification<ProblemList>() {

			@Override
			public Predicate toPredicate(Root<ProblemList> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate problemTypePredicate;
				Predicate problemsList;
				if(problemType.equalsIgnoreCase("Active")){
					problemTypePredicate=cb.equal(root.get(ProblemList_.problemListIsactive), true);
					problemsList=cq.where(cb.equal(root.get(ProblemList_.problemListPatientId),patientId), problemTypePredicate).getRestriction();
				}
				else if(problemType.equalsIgnoreCase("Resolved")){
					problemTypePredicate=cb.equal(root.get(ProblemList_.problemListIsactive), false);
					problemsList=cq.where(cb.equal(root.get(ProblemList_.problemListPatientId),patientId), problemTypePredicate).getRestriction();
				}
				else
					problemsList=cq.where(cb.equal(root.get(ProblemList_.problemListPatientId),patientId) ).getRestriction();


				return problemsList;
			}

		};
	}
	
	

	/**
	 * @param chartId	: used to get patient allergies of a patient of that particular chart id
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<PatientAllergies> getPatientAllergiesList(final int chartId)
	{
		return new Specification<PatientAllergies>() {

			@Override
			public Predicate toPredicate(Root<PatientAllergies> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate ptAllgStatusPredicate=cb.notEqual(root.get(PatientAllergies_.patAllergStatus), 3);
				Predicate ptAllgTypeIdPredicate=cb.notLike(root.get(PatientAllergies_.patAllergTypeId).as(String.class), "-");
				Predicate ptAllgStatusPredicate2=cb.lessThan(root.get(PatientAllergies_.patAllergStatus),4);
				Predicate ptAllgChartId=cb.equal(root.get(PatientAllergies_.patAllergChartId), chartId);


				//root.fetch(PatientAllergies_.empProfileAllgCreatedByTable,JoinType.LEFT);
				//root.fetch(PatientAllergies_.empProfileAllgResolvedByTable,JoinType.LEFT);
				//root.fetch(PatientAllergies_.empProfileAllgInactiveByTable,JoinType.LEFT);
				//root.fetch(PatientAllergies_.empProfileAllgModifiedByTable,JoinType.LEFT);

				cq.where(ptAllgStatusPredicate,ptAllgTypeIdPredicate,ptAllgStatusPredicate2,ptAllgChartId);

				return cq.getRestriction();
			}

		};
	}
	
	/**
	 * @return list of providers
	 */	
	public static Specification<EmployeeProfile> getPatientEncounterProvidersList(final int patientId, final int chartId)
	   {
		   return new Specification<EmployeeProfile>() {

			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				
				Join<EmployeeProfile, Encounter> encounerServiceDoctorJoin=root.join(EmployeeProfile_.encounterServiceDr, JoinType.INNER);
				root.fetch(EmployeeProfile_.encounterServiceDr);
				
				Expression<Integer> exprToId=root.get(EmployeeProfile_.empProfileGroupid);
				Predicate activeEmployee=cb.and(cb.equal(root.get(EmployeeProfile_.empProfileIsActive), true),cb.notLike(cb.lower(root.get(EmployeeProfile_.empProfileFullname)), "%demo%"),exprToId.in(-1));
				Predicate serviceDoctorPredicate=cb.equal(encounerServiceDoctorJoin.get(Encounter_.encounterChartid), chartId);
				Predicate predicate=cq.where(cb.and(cb.isNotNull(root.get(EmployeeProfile_.empProfileEmpid))),activeEmployee,serviceDoctorPredicate).orderBy(cb.desc(root.get(EmployeeProfile_.empProfileFullname))).distinct(true).getRestriction();
					
				return predicate;
			}
			   
		};
	   }
	
	
	
	public static Pageable createPortalProblemsListPageRequest(final int pageOffset, final int pageIndex) {

		return new PageRequest(pageIndex, pageOffset, Sort.Direction.DESC,"problemListId");
	}
	
	public static Pageable createPortalAllergiesPageRequest(final int pageOffset, final int pageIndex) {

		return new PageRequest(pageIndex, pageOffset, Sort.Direction.DESC,"patAllergId");
	}

	public static Pageable createPortalPlanOfCarePageRequest(final int pageOffset, final int pageIndex) {

		return new PageRequest(pageIndex, pageOffset, Sort.Direction.DESC,"encounterId");
	}
}
