package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Icdm;
import com.glenwood.glaceemr.server.application.models.Icdm_;

public class IcdmSpecification {
	/**
	 * Specification to get the list based on code
	 * @param flowsheetDxMapid
	 * @return Specification<Icdm>
	 */
	public static Specification<Icdm> icdmCodesIn(final String[] codes)
	{
		return new Specification<Icdm>() {

			@Override
			public Predicate toPredicate(Root<Icdm> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate icdmCodesIn = root.get(Icdm_.icdmIcdcode).in((Object[])codes);
				return icdmCodesIn;
			}
		};
	}
}
