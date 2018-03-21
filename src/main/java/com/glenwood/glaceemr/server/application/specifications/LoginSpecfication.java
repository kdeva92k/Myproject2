package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.LoginUsers;
import com.glenwood.glaceemr.server.application.models.LoginUsers_;

public class LoginSpecfication {
	
	/**
	 * This method is get the details for a user id
	 * @param search user by id
	 */
	public static Specification<LoginUsers> byUserId(final Integer userId)
	{
		return new Specification<LoginUsers>() {
			
			@Override
			public Predicate toPredicate(Root<LoginUsers> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate byUserId = cb.equal(root.get(LoginUsers_.loginUsersId), userId);
				return byUserId;
			}
		};
	}

}
