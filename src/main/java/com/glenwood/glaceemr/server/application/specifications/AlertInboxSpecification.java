package com.glenwood.glaceemr.server.application.specifications;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.AlertArchive;
import com.glenwood.glaceemr.server.application.models.AlertArchive_;
import com.glenwood.glaceemr.server.application.models.AlertCategory;
import com.glenwood.glaceemr.server.application.models.AlertCategory_;
import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.AlertEvent_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;

/**
 * Specifications for alerts module
 * @author Manikandan
 */
public class AlertInboxSpecification {

	/**
	 * Specification to fetch all the users list based on the group id
	 * @return
	 */
	public static Specification<EmployeeProfile> getUsersList(){
		return new Specification<EmployeeProfile>() {

			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate predicate=cq.where(cb.isNotNull(root.get(EmployeeProfile_.empProfileEmpid))).getRestriction();
				cq.orderBy(cb.desc(root.get(EmployeeProfile_.empProfileGroupid)));
				return predicate;
			}
		};
		
	}

	/**
	 * Get the user details based on the user id
	 * @param userId
	 * @return
	 */
	public static Specification<EmployeeProfile> getUserById(final String userId){
		return new Specification<EmployeeProfile>() {

			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate predicate=cq.where(cb.equal(root.get(EmployeeProfile_.empProfileEmpid),Integer.parseInt(userId))).getRestriction();
				return predicate;
			}
		};
		
	}

	/**
	 * Get alert details based on alert id
	 * @param alertIdList
	 * @return
	 */
	public static Specification<AlertEvent> byAlertId(final List<Integer> alertIdList) {
		return new Specification<AlertEvent>() {
			
			@Override
			public Predicate toPredicate(Root<AlertEvent> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				Expression<Integer> expr=root.get(AlertEvent_.alertEventId);
				Predicate predicate=query.where(expr.in(alertIdList)).getRestriction();
				root.fetch(AlertEvent_.empProfileTableFrom,JoinType.LEFT);
				return predicate;
			}
		};
	}

	/**
	 * Get alert details based on alert id
	 * @param encounterIdList
	 * @return
	 */
	public static Specification<AlertEvent> byEncounterId(final List<Integer> encounterIdList) {
		return new Specification<AlertEvent>() {
			
			@Override
			public Predicate toPredicate(Root<AlertEvent> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				Expression<Integer> expr=root.get(AlertEvent_.alertEventEncounterId);
				Predicate predicate=query.where(expr.in(encounterIdList)).getRestriction();
				query.orderBy(cb.desc(root.get(AlertEvent_.alertEventId)));

				root.join("empProfileTableFrom",JoinType.LEFT);
				root.join("empProfileTableTo",JoinType.LEFT);
				root.join("empProfileTableReadBy",JoinType.LEFT);
				root.join("empProfileTableModifiedBy",JoinType.LEFT);

				root.fetch(AlertEvent_.empProfileTableFrom,JoinType.LEFT);
				root.fetch(AlertEvent_.empProfileTableTo,JoinType.LEFT);
				root.fetch(AlertEvent_.empProfileTableReadBy,JoinType.LEFT);
				root.fetch(AlertEvent_.empProfileTableModifiedBy,JoinType.LEFT);
				
				return predicate;
			}
		};
	}

	/**
	 * Get category details based on the category id
	 * @param categoryIdList
	 * @return
	 */
	public static Specification<AlertCategory> byCategoryId(final List<Integer> categoryIdList) {
		return new Specification<AlertCategory>() {
			
			@Override
			public Predicate toPredicate(Root<AlertCategory> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				Expression<Integer> expr=root.get(AlertCategory_.alertCategoryId);
				Predicate predicate=query.where(expr.in(categoryIdList)).getRestriction();
				query.orderBy(cb.asc(root.get(AlertCategory_.alertCategoryId)));
				
				return predicate;
			}
		};
	}
	
	public static Specification<AlertEvent> getAlertData(final Integer patientId, final Integer encounterId, final Integer refId) {
		return new Specification<AlertEvent>() {

			@Override
			public Predicate toPredicate(Root<AlertEvent> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate checkPatientId = cb.equal(root.get(AlertEvent_.alertEventPatientId), patientId);
				Predicate checkEncounterId = cb.equal(root.get(AlertEvent_.alertEventEncounterId), encounterId);
				Predicate checkRefId = cb.equal(root.get(AlertEvent_.alertEventRefId), refId);
				Predicate checkStatus = cb.equal(root.get(AlertEvent_.alertEventStatus), 1);
				return cb.and(checkPatientId, checkEncounterId, checkRefId, checkStatus);
			}
		};
	}
	
	public static Specification<AlertEvent> byEncIdAndCatId(final Integer encounterId, final Integer categoryId) {
		return new Specification<AlertEvent>() {

			@Override
			public Predicate toPredicate(Root<AlertEvent> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkCatId=cb.equal(root.get(AlertEvent_.alertEventCategoryId), categoryId);
				Predicate checkEncId=cb.equal(root.get(AlertEvent_.alertEventEncounterId),encounterId );
				Predicate checkStatus = cb.equal(root.get(AlertEvent_.alertEventStatus), 1);
				Predicate mainPredicate=cb.and(checkStatus,checkEncId,checkCatId);
				return mainPredicate;
			}


		};
	}

	/**
	 * used to get alerts from alert_archive based on parentid and categoryid
	 * @param parentId
	 * @param categoryId
	 * @return
	 */
	 
	public static Specification<AlertArchive> inactiveAlertsByParentid(final Integer parentId,final Integer categoryId) {
		return new Specification<AlertArchive>() {
			
			@Override
			public Predicate toPredicate(Root<AlertArchive> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				root.fetch(AlertArchive_.empProfileTableFrom,JoinType.LEFT);
				Predicate predicate=query.where(cb.and(
						cb.or(cb.equal(root.get(AlertArchive_.alertEventParentalertid), parentId ),cb.equal(root.get(AlertArchive_.alertEventId), parentId ))
						,cb.equal(root.get(AlertArchive_.alertEventCategoryId),categoryId ))).getRestriction();
				query.orderBy(cb.asc(root.get(AlertArchive_.alertEventCreatedDate)));
				return predicate;
			}
		};
	}
	
	/**
	 * Used to get max id based on category
	 * @param categoryId
	 * @return
	 */
	public static Specification<AlertEvent> getMaxId(final Integer categoryId) {
		return new Specification<AlertEvent>() {
			
			@Override
			public Predicate toPredicate(Root<AlertEvent> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate predicate=query.where(cb.equal(root.get(AlertEvent_.alertEventCategoryId), categoryId)).getRestriction();
				cb.max(root.get(AlertEvent_.alertEventId));
				return predicate;
			}
		};
	}
	
}