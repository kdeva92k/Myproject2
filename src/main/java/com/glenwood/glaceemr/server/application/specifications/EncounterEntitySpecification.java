package com.glenwood.glaceemr.server.application.specifications;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;


public class EncounterEntitySpecification {
	
	/**
	 * Search using encounter id
	 * @param encounterId
	 * @return
	 */
	public static Specification<Encounter> EncounterById(final Integer encounterId)
	{
		return new Specification<Encounter>() {
			
			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate pred = cb.equal(root.get(Encounter_.encounterId), encounterId);
				return pred;
			}
		};
	}
	
	/**
	 * Search using chart id
	 * @param chartId
	 * @return
	 */
	public static Specification<Encounter> EncounterByChartId(final Integer chartId)
	{
		return new Specification<Encounter>() {
			
			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate pred = cb.equal(root.get(Encounter_.encounterChartid), chartId);
				return pred;
			}
		};
	}
	
	/**
	 * Search using encounter type
	 * @param type
	 * @return
	 */
	public static Specification<Encounter> EncounterByType(final Integer type)
	{
		return new Specification<Encounter>() {
			
			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate pred = cb.equal(root.get(Encounter_.encounterType), type);
				return pred;
			}
		};
	}
	
	/**
	 * Ordering with encounter date in ascending order
	 * @return
	 */
	public static Specification<Encounter> orderByDate()
	{
		return new Specification<Encounter>() {
			
			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.orderBy(cb.asc(root.get(Encounter_.encounterDate)));
				return query.getRestriction();
			}
		};
	}
	
	/**
	 * Get encounters for list of encounter Ids
	 * @param encounterIds
	 * @return
	 */
	public static Specification<Encounter> encountersByIds(final Integer[] encounterIds)
	{
		return new Specification<Encounter>() {
			
			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate pred = root.get(Encounter_.encounterId).in((Object[])encounterIds);
				return pred;
			}
		};
	}
}
