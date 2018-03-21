package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.PatientReviewedDetails;
import com.glenwood.glaceemr.server.application.models.PatientReviewedDetails_;


public class PatientReviewedSpecification {
	
	public static Specification<PatientReviewedDetails> getReviewDetails(final Integer chartId){

		return new Specification<PatientReviewedDetails>(){

			@Override
			public Predicate toPredicate(Root<PatientReviewedDetails> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				root.fetch(PatientReviewedDetails_.empProfile,JoinType.INNER);
				Predicate chartPred=cb.equal(root.get(PatientReviewedDetails_.patientReviewedDetailsChartid),chartId);
				query.orderBy(cb.desc(root.get(PatientReviewedDetails_.patientReviewedDetailsOn)));
				return chartPred;
			}
		};
	}

}
