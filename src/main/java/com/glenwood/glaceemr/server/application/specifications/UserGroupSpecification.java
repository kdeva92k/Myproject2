package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.glenwood.glaceemr.server.application.models.UserGroup;
import com.glenwood.glaceemr.server.application.models.UserGroup_;

/**
 * Specifications for UserGroup module
 * @author Jeyanthkumar S
 */
public class UserGroupSpecification {

	/**
	 * 
	 * @param groupId 
	 * @return  condition to fetch the user group details based on the group id
	 */
	public static Specification<UserGroup> groupDetailsByGroupId(final Integer groupId){
		return new Specification<UserGroup>() {

			@Override
			public Predicate toPredicate(Root<UserGroup> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate predicate=cq.where(cb.equal(root.get(UserGroup_.groupId),groupId)).getRestriction();
				return predicate;
			}
		};

	}
	
	/**
	 * 
	 * @param userId 
	 * @return  condition to fetch the user group details based on the user id
	 */
	public static Specification<UserGroup> groupDetailsByUserId(final Integer userId){
		return new Specification<UserGroup>() {

			@Override
			public Predicate toPredicate(Root<UserGroup> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate predicate=cq.where(cb.equal(root.get(UserGroup_.userId),userId)).getRestriction();
				return predicate;
			}
		};

	}

}
