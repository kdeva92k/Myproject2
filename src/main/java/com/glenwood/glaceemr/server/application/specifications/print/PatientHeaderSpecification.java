package com.glenwood.glaceemr.server.application.specifications.print;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.print.PatientHeaderDetails;
import com.glenwood.glaceemr.server.application.models.print.PatientHeaderDetails_;


public class PatientHeaderSpecification {
	
	public static Specification<PatientHeaderDetails> getPatientHeaderDetails(final Integer headerId,final Integer pageId){

		return new Specification<PatientHeaderDetails>(){

			@Override
			public Predicate toPredicate(Root<PatientHeaderDetails> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate headerCondition=cb.equal(root.get(PatientHeaderDetails_.patientHeaderId),headerId);
				Predicate pageCondition=cb.equal(root.get(PatientHeaderDetails_.pageId),pageId);
				query.orderBy(cb.asc(root.get(PatientHeaderDetails_.componentOrder)));
				return cb.and(headerCondition,pageCondition);
			}
		};
	}

}
