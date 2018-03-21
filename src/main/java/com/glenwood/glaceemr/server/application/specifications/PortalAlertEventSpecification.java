package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.AlertEvent_;

public class PortalAlertEventSpecification {

	
	/**
	 * @return Recent most alert event predicate based on alert category and patient
	 */	
	public static Specification<AlertEvent> getMostRecentAlertEventByCategoryAndPatient(final int alertCategory, final int patientId)
	   {
		   return new Specification<AlertEvent>() {

			@Override
			public Predicate toPredicate(Root<AlertEvent> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				root.fetch(AlertEvent_.empProfileTableTo,JoinType.LEFT);
				
				Predicate mostRecentAlertEventPredicate=cq.where(cb.equal(root.get(AlertEvent_.alertEventCategoryId), alertCategory),cb.equal(root.get(AlertEvent_.alertEventPatientId), patientId)).orderBy(cb.desc(root.get(AlertEvent_.alertEventId))).getRestriction();
				
				return mostRecentAlertEventPredicate;
			}
			   
		};
	   }
}
