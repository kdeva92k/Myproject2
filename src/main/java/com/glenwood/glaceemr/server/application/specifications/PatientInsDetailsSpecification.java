package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.InsCompAddr;
import com.glenwood.glaceemr.server.application.models.InsCompAddr_;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail_;


public class PatientInsDetailsSpecification {

	
	
	public static Specification<PatientInsDetail> getByPatientId(final Integer patientId) {
		return new Specification<PatientInsDetail>() {
			
			@Override
			public Predicate toPredicate(Root<PatientInsDetail> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate patientIdPred= cb.equal(root.get(PatientInsDetail_.patientInsDetailPatientid),patientId);
				Predicate insTypePred = root.get(PatientInsDetail_.patientInsDetailInstype).in(1,2);
				Predicate insIsactivePred = cb.equal(root.get(PatientInsDetail_.patientInsDetailIsactive), true);
				Fetch<PatientInsDetail, InsCompAddr> compJoin = root.fetch(PatientInsDetail_.insCompAddr,JoinType.LEFT);
				compJoin.fetch(InsCompAddr_.insCompany, JoinType.LEFT);
				query.where(patientIdPred,insTypePred,insIsactivePred);
				
				return query.getRestriction();
			}
		};
	}
	
}
