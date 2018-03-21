package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Guarantor;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;

public class GuarantorSpecification {

	/**
	 * Get guarantor details based on guarantor_key
	 * @return
	 */
	public static Specification<Guarantor> getByPatientId(final Integer key){
		return new Specification<Guarantor>() {

			@Override
			public Predicate toPredicate(Root<Guarantor> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Join<Guarantor, PatientRegistration> joinPatient= root.join("patientRegTable",JoinType.INNER);
				Predicate predicate= cb.equal(joinPatient.get(PatientRegistration_.patientRegistrationId),key);
				
				return predicate;
			}
		};
		
	}
}