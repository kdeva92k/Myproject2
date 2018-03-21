package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.PatientPortalUser;
import com.glenwood.glaceemr.server.application.models.PatientPortalUser_;

public class PortalRecoverUserPasswordSpecifiction {

	/**
	 * @return Appointment Status List  
	 */	
	public static Specification<PatientPortalUser> authenticatePortalUser(final String username)
	{
		return new Specification<PatientPortalUser>() {

			@Override
			public Predicate toPredicate(Root<PatientPortalUser> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				root.fetch(PatientPortalUser_.chartpatient_portal_userTable, JoinType.INNER);

				Predicate securityQuestionsPredicate=cq.where(cb.equal(cb.upper(root.get(PatientPortalUser_.patient_portal_user_name)), username.toUpperCase()),cb.equal(root.get(PatientPortalUser_.patient_portal_user_account_state), 1)).getRestriction();

				return securityQuestionsPredicate;
			}

		};
	}
	
}
