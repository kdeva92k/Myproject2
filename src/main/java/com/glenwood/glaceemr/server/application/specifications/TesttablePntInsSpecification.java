package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.TesttablePntIns;
import com.glenwood.glaceemr.server.application.models.TesttablePntIns_;
import com.glenwood.glaceemr.server.application.models.TesttablePtn;
import com.glenwood.glaceemr.server.application.models.TesttablePtn_;




@Component
@Transactional
public class TesttablePntInsSpecification {
	
	 public static Specification<TesttablePntIns> InsuranceByPatientId(final Integer patientId)
		{
			return new Specification<TesttablePntIns>() {
				
				@Override
				public Predicate toPredicate(Root<TesttablePntIns> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					Join<TesttablePntIns,TesttablePtn> patientinsurance=root.join(TesttablePntIns_.patientTable,JoinType.INNER);
					root.fetch(TesttablePntIns_.patientTable);
					root.fetch(TesttablePntIns_.insuranceMasterTable);
					query.multiselect(root);
					Predicate ispatientId=cb.equal(patientinsurance.get(TesttablePtn_.patientId),patientId);
					query.where(ispatientId);
					return null;
					
				}
			};
		}
	

}
