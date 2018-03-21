package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.AttestationStatus;
import com.glenwood.glaceemr.server.application.models.AttestationStatus_;

public class AttestationStatusSpecification {

	public static Specification<AttestationStatus> getReportingStatus(final Integer reportingYear){
		return new Specification<AttestationStatus>() {

			@Override
			public Predicate toPredicate(Root<AttestationStatus> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate predicateByProgramYear = cb.equal(root.get(AttestationStatus_.reportingYear),reportingYear);
				
				return predicateByProgramYear;
				
			}
		};
		
	}
	
}
