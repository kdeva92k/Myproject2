package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.TesttableAdrs;
import com.glenwood.glaceemr.server.application.models.TesttableAdrs_;;





public class TesttableAdrsSpecfication {
	
	/**
	 * @param lastName	: used to search the patients 
	 * @return BooleanExpression is a  predicate  
	 */
	public static Specification<TesttableAdrs> byAddress(final String address)
	{
		return new Specification<TesttableAdrs>() {
			
			@Override
			public Predicate toPredicate(Root<TesttableAdrs> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate byAddress = cb.like(cb.upper(root.get(TesttableAdrs_.address)), address.toUpperCase());
				return byAddress;
			}
		};
	}

}
