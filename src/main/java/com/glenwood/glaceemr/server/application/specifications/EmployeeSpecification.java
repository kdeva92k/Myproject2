package com.glenwood.glaceemr.server.application.specifications;

import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;

public class EmployeeSpecification {

	static ArrayList<Integer> groupIdList=new ArrayList<Integer>(Arrays.asList(-1,-2,-3,-5,-6,-7,-10,-25));
	/**
	 * @param sort Example: asc
	 * @return condition to get employee details based on sorting order
	 */
	public static Specification<EmployeeProfile> getUsersList(final String sort){
		return new Specification<EmployeeProfile>() {

			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				Expression<Integer> exprToId=root.get(EmployeeProfile_.empProfileGroupid);
				Predicate activeEmployee=cb.and(cb.equal(root.get(EmployeeProfile_.empProfileIsActive), true),cb.notLike(cb.lower(root.get(EmployeeProfile_.empProfileFullname)), "%demo%"),exprToId.in(groupIdList));
				Predicate predicate=query.where(cb.and(cb.isNotNull(root.get(EmployeeProfile_.empProfileEmpid))),activeEmployee).getRestriction();

				if(sort.equalsIgnoreCase("asc"))
					query.orderBy(cb.asc(root.get(EmployeeProfile_.empProfileGroupid)),cb.asc(root.get(EmployeeProfile_.empProfileFullname)));
				if(sort.equalsIgnoreCase("desc"))
					query.orderBy(cb.desc(root.get(EmployeeProfile_.empProfileGroupid)),cb.asc(root.get(EmployeeProfile_.empProfileFullname)));
				return predicate;
			}
		};
	}


	/**
	 * 
	 * @param groupId
	 * @param sort Example: asc
	 * @return condition to get employee details based on sorting order and groupId
	 */
	public static Specification<EmployeeProfile> getUsersList(final String groupId,final String sort){
		return new Specification<EmployeeProfile>() {

			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				Expression<Integer> exprToId=root.get(EmployeeProfile_.empProfileGroupid);
				Predicate activeEmployee=cb.and(cb.equal(root.get(EmployeeProfile_.empProfileIsActive), true),cb.notLike(cb.lower(root.get(EmployeeProfile_.empProfileFullname)), "%demo%"),exprToId.in(groupId));
				Predicate predicate=query.where(cb.and(cb.isNotNull(root.get(EmployeeProfile_.empProfileEmpid))),activeEmployee).getRestriction();
				
				if(sort.equalsIgnoreCase("asc"))
					query.orderBy(cb.asc(root.get(EmployeeProfile_.empProfileGroupid)));
				if(sort.equalsIgnoreCase("desc"))
					query.orderBy(cb.desc(root.get(EmployeeProfile_.empProfileGroupid)));	
				return predicate;
			}

		};
	}
	
	/**
	 * This specification used to return employee details based on user id
	 * @param userId
	 * @return
	 */
	public static Specification<EmployeeProfile> getUserDetailsByUserId(final int userId){
		return new Specification<EmployeeProfile>() {

			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate predicate=query.where(cb.equal(root.get(EmployeeProfile_.empProfileEmpid), userId)).getRestriction();	
				return predicate;
			}

		};
	}

	
	////////////////////
	
	
	/**
	 * Search employees using login id
	 * @param loginId 
	 * @return  
	 */
	public static Specification<EmployeeProfile> findByEmpLoginId(final Integer loginId)
	{
		return new Specification<EmployeeProfile>() {
			
			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate pred = cb.equal(root.get(EmployeeProfile_.empProfileLoginid), loginId);
				return pred;
			}
		};
	}

	/**
	 * Search using first name
	 * @param fname
	 * @return
	 */
	public static Specification<EmployeeProfile> findByEmpFNameNotLike(final String fname)
	{
		return new Specification<EmployeeProfile>() {
			
			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.where(cb.notLike(cb.lower(root.get(EmployeeProfile_.empProfileFname)), fname.toLowerCase())).orderBy(cb.asc(root.get(EmployeeProfile_.empProfileDoctorid)));
				return query.getRestriction();
			}
		};
	}
	
	/**
	 * Search using last name
	 * @param lname
	 * @return
	 */
	public static Specification<EmployeeProfile> findByEmpLNameNotLike(final String lname)
	{
		return new Specification<EmployeeProfile>() {
			
			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate pred = cb.notLike(cb.lower(root.get(EmployeeProfile_.empProfileLname)), lname.toLowerCase());
				return pred;
			}
		};
	}
	
	/**
	 * Check active status
	 * @param active
	 * @return
	 */
	public static Specification<EmployeeProfile> isActive(final Boolean active)
	{
		return new Specification<EmployeeProfile>() {
			
			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate pred = cb.equal(root.get(EmployeeProfile_.empProfileIsActive), active);
				return pred;
			}
		};
	}
	
	/**
	 * Search using group id, in -1 and-10
	 * @return
	 */
	public static Specification<EmployeeProfile> findByGroupId()
	{
		return new Specification<EmployeeProfile>() {
			
			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.where(root.get(EmployeeProfile_.empProfileGroupid).in(-1,-10));
				return query.getRestriction();
			}
		};
	}
	
	/**
	 * Ordering with group Id in ascending order
	 * @return
	 */
	public static Specification<EmployeeProfile> orderByGroupId()
	{
		return new Specification<EmployeeProfile>() {
			
			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				query.orderBy(cb.asc(root.get(EmployeeProfile_.empProfileGroupid)));
				return query.getRestriction();
			}
		};
	}
	
	/**
	 * Search using employee Id
	 * @param empId
	 * @return
	 */
	public static Specification<EmployeeProfile> findByEmpId(final Integer empId)
	{
		return new Specification<EmployeeProfile>() {
			
			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				Predicate pred = cb.equal(root.get(EmployeeProfile_.empProfileEmpid), empId);
				return pred;
			}
		};
	}
	
	/**
	 * Search using group Id
	 * @param groupId
	 * @return
	 */
	public static Specification<EmployeeProfile> findByGroupId(final Integer groupId)
	{
		return new Specification<EmployeeProfile>() {
			
			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				Predicate pred = cb.equal(root.get(EmployeeProfile_.empProfileGroupid), groupId);
				return pred;
			}
		};
	}
	
}
