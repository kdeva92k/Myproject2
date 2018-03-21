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

import com.glenwood.glaceemr.server.application.models.CodingSystems;
import com.glenwood.glaceemr.server.application.models.ConcentrateGroup_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.PatientAssessments;
import com.glenwood.glaceemr.server.application.models.PatientAssessments_;
import com.glenwood.glaceemr.server.application.models.LabEntries;
import com.glenwood.glaceemr.server.application.models.LabEntries_;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosTable_;
import com.glenwood.glaceemr.server.application.models.PosType;
import com.glenwood.glaceemr.server.application.models.PosType_;
import com.glenwood.glaceemr.server.application.models.ProblemList;
import com.glenwood.glaceemr.server.application.models.ProblemList_;
import com.glenwood.glaceemr.server.application.models.SkinTestOrderAllergenCategory;
import com.glenwood.glaceemr.server.application.models.SkinTestOrderAllergenCategory_;
import com.glenwood.glaceemr.server.application.models.SkinTestConcentration;
import com.glenwood.glaceemr.server.application.models.SkinTestConcentration_;
import com.glenwood.glaceemr.server.application.models.SkinTestFormShortcut;
import com.glenwood.glaceemr.server.application.models.SkinTestFormShortcutAllergenDetails;
import com.glenwood.glaceemr.server.application.models.SkinTestFormShortcutAllergenDetails_;
import com.glenwood.glaceemr.server.application.models.SkinTestFormShortcutCategoryDetails;
import com.glenwood.glaceemr.server.application.models.SkinTestFormShortcutCategoryDetails_;
import com.glenwood.glaceemr.server.application.models.SkinTestFormShortcut_;
import com.glenwood.glaceemr.server.application.models.SkinTestOrder_;
import com.glenwood.glaceemr.server.application.models.ConcentrateGroup;
import com.glenwood.glaceemr.server.application.models.SkinTestOrder;

/**
 * This class represents specifications needed for skin testing form sheets
 * generation and ordering tests to patients.
 * 
 * @author Swaroopa V
 *
 */
@Component
@Transactional
public class SkinTestingFormSpecification {
	
	/**
	 * Specification for getting active allergens data only
	 * @return
	 */
	public static Specification<ConcentrateGroup> getAllergenCategoriesWithAllergens() {
		return new Specification<ConcentrateGroup>() {

			@Override
			public Predicate toPredicate(Root<ConcentrateGroup> root,CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate isactive = cb.equal(root.get(ConcentrateGroup_.concentrateGroupStatus),true);
				cq.where(cb.and(isactive));
				cq.orderBy(cb.asc(root.get(ConcentrateGroup_.concentrateGroupId)));
				return cq.getRestriction();
			}

		};
	}

	/**
	 * For getting Drs and NP group
	 * @return
	 */
	public static Specification<EmployeeProfile> getEligibleUsers() {
		return new Specification<EmployeeProfile>() {

			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root,CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate p1 = cb.and(root.get(EmployeeProfile_.empProfileGroupid).in("-1","-2","-3","-5","-6","-7","-10","-25"));
				Predicate p2=cb.equal(root.get(EmployeeProfile_.empProfileIsActive), true);
				cq.where(p1,p2);
				return cq.getRestriction();
			}
		};
	}

	public static Specification<PosTable> getPos() {
		return new Specification<PosTable>() {
			@Override
			public Predicate toPredicate(Root<PosTable> root,CriteriaQuery<?> cq,CriteriaBuilder cb) {
				Join<PosTable,PosType> rootjoin=root.join(PosTable_.posType,JoinType.INNER);
				Predicate predicate1=cb.equal(root.get(PosTable_.posTableIsActive), true);
				Predicate predicate2=cb.isNotNull(root.get(PosTable_.posTablePractice));
				Predicate predicate3=cb.like(cb.lower(rootjoin.get(PosType_.posTypeTypeName)), getLikePattern("office"));
				Predicate result=cb.and(predicate1,predicate2,predicate3);
				cq.where(cb.and(predicate1,predicate2));
				return cq.getRestriction();
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
		pattern.append("%");
		pattern.append(searchTerm.toLowerCase());
		pattern.append("%");
		return pattern.toString();
	}
	
	public static Specification<SkinTestOrder> getSkinTestOrders(
			final Integer patientId) {
		return new Specification<SkinTestOrder>() {

			@Override
			public Predicate toPredicate(Root<SkinTestOrder> root,CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate patientId1 = cb.equal(root.get(SkinTestOrder_.skinTestOrderPatientId),patientId);
				cq.where(cb.and(patientId1));
				cq.orderBy(cb.desc(root.get(SkinTestOrder_.skinTestOrderStartDate)),cb.desc(root.get(SkinTestOrder_.skinTestOrderCreatedOn)));
				return cq.getRestriction();
			}
		};

	}
	
	/**
	 * To get present encounter diagnosis data
	 * @param encounterid
	 * @param patientid
	 * @param chartid
	 * @return
	 */
	public static Specification<PatientAssessments> getcodingsystems(final int encounterid,final int chartid ) {
		return new Specification<PatientAssessments>() {

			@Override
			public Predicate toPredicate(Root<PatientAssessments> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientAssessments,CodingSystems> rootjoin=root.join(PatientAssessments_.codingsystemsTable,JoinType.INNER);
				Join<PatientAssessments, Encounter> encounterjoin=root.join(PatientAssessments_.encounter,JoinType.INNER);
				Predicate predicate=cb.equal(encounterjoin.get(Encounter_.encounterChartid), chartid);
				Predicate predicate1=cb.equal(encounterjoin.get(Encounter_.encounterType), 1);
				query.distinct(true);
				Predicate result=cb.and(predicate,predicate1);
				return result;
			}
		};

	}

	/**
	 * To get patient problems list
	 * @param patientid
	 * @return
	 */
	public static Specification<ProblemList> getproblemlist(final int patientid) {
		return new Specification<ProblemList>() {

			@Override
			public Predicate toPredicate(Root<ProblemList> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<ProblemList, CodingSystems> rootjoin=root.join(ProblemList_.codingSystems,JoinType.INNER);
				Predicate predicate=cb.equal(root.get(ProblemList_.problemListPatientId), patientid);
				Predicate predicate1=cb.equal(root.get(ProblemList_.problemListIsactive), true);
				Predicate result=cb.and(predicate,predicate1);
				return result;


			}
		};

	}
	
	/**
	 * Specification to get pending labs
	 * @param encounterId
	 * @param chartId
	 * @return
	 */
	public static Specification<LabEntries> pendingLabs(final Integer encounterId,final Integer chartId) {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
				root.join(LabEntries_.empProfile,JoinType.LEFT);
//				Join<LabEntries, Encounter> encounterJoin = root.join(LabEntries_.encounter,JoinType.INNER);
//				encounterJoin.on(cb.notEqual(root.get(LabEntries_.labEntriesEncounterId), encounterId));
				root.join(LabEntries_.labGroups,JoinType.INNER);
//				root.fetch(LabEntries_.empProfile);
//				root.fetch(LabEntries_.encounter);
				root.fetch(LabEntries_.labGroups);
				Predicate checkstatus = cb.equal(root.get(LabEntries_.labEntriesTestStatus), 1);
				Predicate checkChartId = cb.equal(root.get(LabEntries_.labEntriesChartid), chartId);
				Predicate labGroups = root.get(LabEntries_.labEntriesGroupid).in(1,9);
				query.orderBy(cb.asc(root.get(LabEntries_.labEntriesGroupid)),cb.desc(root.get(LabEntries_.labEntriesOrdOn)));
				return cb.and(checkstatus, checkChartId,labGroups);
			}
		};
	}
	
	public static Specification<SkinTestConcentration> getTestConcentrations() {
		return new Specification<SkinTestConcentration>() {
			@Override
			public Predicate toPredicate(Root<SkinTestConcentration> root, CriteriaQuery<?> cq,CriteriaBuilder cb) {
				Predicate isactive = cb.equal(root.get(SkinTestConcentration_.skinTestConcentrationIsActive),true);
				cq.where(cb.and(isactive));
				return cq.getRestriction();	
			}
		};
	}
	
	public static Specification<SkinTestOrder> getSkinTestOrder(final int orderId) {
		return new Specification<SkinTestOrder>() {

			@Override
			public Predicate toPredicate(Root<SkinTestOrder> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate finalPredicate = null;
				Join<SkinTestOrder, SkinTestFormShortcut> orderJoin = root.join(SkinTestOrder_.skinTestFormShortcut,JoinType.INNER);
				Join<SkinTestFormShortcut,SkinTestFormShortcutCategoryDetails> catJoin = orderJoin.join(SkinTestFormShortcut_.skinTestFormShortcutCategoryDetails,JoinType.INNER);
				Join<SkinTestFormShortcutCategoryDetails,SkinTestFormShortcutAllergenDetails> alleJoin = catJoin.join(SkinTestFormShortcutCategoryDetails_.skinTestFormShortcutAllergenDetails,JoinType.INNER);
				Predicate pred1 = cb.equal(alleJoin.get(SkinTestFormShortcutAllergenDetails_.skinTestFormShortcutAllergenDetailsCategoryDetailsId),catJoin.get(SkinTestFormShortcutCategoryDetails_.skinTestFormShortcutCategoryDetailsId));
				Predicate pred2 = cb.equal(catJoin.get(SkinTestFormShortcutCategoryDetails_.skinTestFormShortcutCategoryDetailsShortcutId),orderJoin.get(SkinTestFormShortcut_.skinTestFormShortcutId));
				Predicate pred3 = cb.equal(orderJoin.get(SkinTestFormShortcut_.skinTestFormShortcutId),root.get(SkinTestOrder_.skinTestOrderSkinTestFormShortcutId));
				cq.where(pred1,pred2,pred3,cb.and(cb.equal(root.get(SkinTestOrder_.skinTestOrderId) ,orderId)));
				cq.orderBy(cb.asc(catJoin.get(SkinTestFormShortcutCategoryDetails_.skinTestFormShortcutCategoryDetailsCategoryOrderId)),cb.asc(alleJoin.get(SkinTestFormShortcutAllergenDetails_.skinTestFormShortcutAllergenDetailsAllergenOrderId)));
				return cq.getRestriction();
			}
		};
	}

	public static Specification<SkinTestOrderAllergenCategory> getSkinTestOrderAllergenCategories(final int orderId) {
		return new Specification<SkinTestOrderAllergenCategory>() {

			@Override
			public Predicate toPredicate(Root<SkinTestOrderAllergenCategory> root,CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate orderPredicate = cb.equal(root.get(SkinTestOrderAllergenCategory_.skinTestOrderAllergenCategoryTestOrderId), orderId);	
				return orderPredicate;
			}
			
		};
	}
	
	/*public static Specification<Doctorsign> getReviewedUserSignDetails(final int reviewedBy) {
		return new Specification<Doctorsign>() {
			
			@Override
			public Predicate toPredicate(Root<Doctorsign> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				Join<Doctorsign, EmployeeProfile> sign = root.join(Doctorsign_.employeeProfile,JoinType.INNER);
				root.fetch(Doctorsign_.loginUsers);
				root.fetch(Doctorsign_.employeeProfile);
				cq.where(cb.and(cb.equal(sign.get(EmployeeProfile_.empProfileEmpid) ,reviewedBy)));				
				return cq.getRestriction();
			}
		};
	}*/
	
}
