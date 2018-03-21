package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.Prescription;
import com.glenwood.glaceemr.server.application.models.Prescription_;


@Component
@Transactional
public class ErxSummarySpecification {

	/**
	 *  
	 * @return Getting prescription list of particular encounter
	 * @param encounterId
	 */
	public static Specification<Prescription> getUpdateList(final int encounterId) {
		return new Specification<Prescription>() {

			@Override
			public Predicate toPredicate(Root<Prescription> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.equal(root.get(Prescription_.docPrescEncounterId),encounterId));
				return query.getRestriction();
			}
		};
	}

}
