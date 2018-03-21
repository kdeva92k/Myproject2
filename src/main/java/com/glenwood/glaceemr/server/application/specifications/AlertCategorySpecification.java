package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.AlertCategory;
import com.glenwood.glaceemr.server.application.models.AlertCategory_;

public class AlertCategorySpecification {

	/**
	 * Specification to fetch all category status if alertCategoryIsworkflow is true
	 * @return
	 */
	public static Specification<AlertCategory> getWorkflowCategory(){
		return new Specification<AlertCategory>() {

			@Override
			public Predicate toPredicate(Root<AlertCategory> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate predicate=cq.where(cb.equal(root.get(AlertCategory_.alertCategoryIsworkflow),new Boolean(true))).getRestriction();
				cq.orderBy(cb.asc(root.get(AlertCategory_.alertCategoryId)));
				return predicate;
			}
		};
		
	}

	/**
	 * Specification to fetch category status by category id 
	 * @return
	 */
	public static Specification<AlertCategory> getWorkflowCategoryById(final int categoryId){
		return new Specification<AlertCategory>() {

			@Override
			public Predicate toPredicate(Root<AlertCategory> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate predicate=cq.where(cb.equal(root.get(AlertCategory_.alertCategoryId),categoryId)).getRestriction();
				return predicate;
			}
		};
		
	}
	
	/**
	 * Method to check category type and section
	 * @param catType
	 * @return
	 */
	public static Specification<AlertCategory> getAlertCatByType(final int catType) {
		return new Specification<AlertCategory>() {

			@Override
			public Predicate toPredicate(Root<AlertCategory> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate checkType = cb.equal(root.get(AlertCategory_.alertCategoryType),catType);
				Predicate checkSection = cb.equal(root.get(AlertCategory_.alertCategorySection),4);
				return cb.and(checkType, checkSection);
			}
		};
	}

	public static Specification<AlertCategory> checkSectionAndActionmap(final Integer section, final Integer actionMap) {
		return new Specification<AlertCategory>() {

			@Override
			public Predicate toPredicate(Root<AlertCategory> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate checkSection = cb.equal(root.get(AlertCategory_.alertCategorySection),section);
				Predicate checkActionMap = cb.equal(root.get(AlertCategory_.alertCategoryActionMap),actionMap);
				return cb.and(checkActionMap, checkSection);
			}
		};
	}

	public static Specification<AlertCategory> checkTypeAndActionmap(final Integer actionMap, final Integer catType) {
		return new Specification<AlertCategory>() {

			@Override
			public Predicate toPredicate(Root<AlertCategory> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate checkSection = cb.equal(root.get(AlertCategory_.alertCategorySection),4);
				Predicate checkCatType = cb.equal(root.get(AlertCategory_.alertCategoryType),catType);
				Predicate checkActionMap = cb.equal(root.get(AlertCategory_.alertCategoryActionMap),actionMap);
				return cb.and(checkActionMap, checkSection, checkCatType);
			}
		};
	}
	
	/**
	 * Method to get alert category by alert name
	 * @param categoryName
	 * @return
	 */
	public static Specification<AlertCategory> getAlertCategoryByName(final String alertCategory) {
		return new Specification<AlertCategory>() {

			@Override
			public Predicate toPredicate(Root<AlertCategory> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate categoryPredicate = cb.equal(cb.upper(root.get(AlertCategory_.alertCategoryDisplayName)),alertCategory.toUpperCase());
				
				return categoryPredicate;
			}
		};
	}
}
