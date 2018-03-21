package com.glenwood.glaceemr.server.application.specifications;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.SchedulerResource;
import com.glenwood.glaceemr.server.application.models.SchedulerResourceCategory;
import com.glenwood.glaceemr.server.application.models.SchedulerResourceCategory_;
import com.glenwood.glaceemr.server.application.models.SchedulerResource_;
import com.glenwood.glaceemr.server.application.models.SchedulerUserDefault;
import com.glenwood.glaceemr.server.application.models.SchedulerUserDefault_;
/**
 * 
 * @author Manikandan
 *
 */
public class SchedulerSpecification {

	public static Specification<SchedulerResource> getResources(final List<Integer> resourceList) {
		return new Specification<SchedulerResource>() {
			
			@Override
			public Predicate toPredicate(Root<SchedulerResource> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
						Predicate isDoctorPredicate=query.where(cb.and(root.get(SchedulerResource_.schResourceIsdoctor).in(resourceList)),
								cb.equal(root.get(SchedulerResource_.schResourceLocalserver),true)).getRestriction();
						query.orderBy(cb.asc(root.get(SchedulerResource_.schResourceOrder)),cb.asc(root.get(SchedulerResource_.schResourceName)),cb.asc(root.get(SchedulerResource_.schResourceId)));
				return isDoctorPredicate;
			}
		};
	}
	
	public static Specification<SchedulerResourceCategory> getResourcesByCategory() {
		return new Specification<SchedulerResourceCategory>() {
			
			@Override
			public Predicate toPredicate(Root<SchedulerResourceCategory> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
						Predicate isDoctorPredicate=query.where(cb.equal(root.get(SchedulerResourceCategory_.schResourceCategoryIsactive), true)).getRestriction();
				return isDoctorPredicate;
			}
		};
	}
	
	
	public static Specification<SchedulerUserDefault> getDefaultUserList(final String userId) {
		return new Specification<SchedulerUserDefault>() {
			
			@Override
			public Predicate toPredicate(Root<SchedulerUserDefault> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
						Predicate cond=query.where(cb.and(cb.equal(root.get(SchedulerUserDefault_.schUserDefaultUserId), userId),cb.equal(root.get(SchedulerUserDefault_.schUserDefaultTodisplay), true))).getRestriction();
				return cond;
			}
		};
	}
	
}