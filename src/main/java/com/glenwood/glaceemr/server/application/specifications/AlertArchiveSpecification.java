package com.glenwood.glaceemr.server.application.specifications;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.AlertArchive;
import com.glenwood.glaceemr.server.application.models.AlertArchive_;
import com.glenwood.glaceemr.server.application.models.AlertCategory_;

public class AlertArchiveSpecification {

	/**
	 * Get alert details based on alert id
	 * @param encounterIdList
	 * @return
	 */
	public static Specification<AlertArchive> byEncounterId(final List<Integer> encounterIdList) {
		return new Specification<AlertArchive>() {
			
			@Override
			public Predicate toPredicate(Root<AlertArchive> root,
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				Expression<Integer> expr=root.get(AlertArchive_.alertEventEncounterId);
				Predicate predicate=query.where(expr.in(encounterIdList)).getRestriction();
				query.orderBy(builder.desc(root.get(AlertArchive_.alertEventCreatedDate)));
				
				root.join("empProfileTableFrom",JoinType.LEFT);
				root.join("empProfileTableTo",JoinType.LEFT);
				root.join("empProfileTableReadBy",JoinType.LEFT);
				root.join("empProfileTableModifiedBy",JoinType.LEFT);

				root.fetch(AlertArchive_.empProfileTableFrom,JoinType.LEFT);
				root.fetch(AlertArchive_.empProfileTableTo,JoinType.LEFT);
				root.fetch(AlertArchive_.empProfileTableReadBy,JoinType.LEFT);
				root.fetch(AlertArchive_.empProfileTableModifiedBy,JoinType.LEFT);
				
				return predicate;
			}
		};
	}
}
