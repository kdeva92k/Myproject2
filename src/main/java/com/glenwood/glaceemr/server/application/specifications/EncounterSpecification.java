package com.glenwood.glaceemr.server.application.specifications;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.EncounterPlan;
import com.glenwood.glaceemr.server.application.models.EncounterPlan_;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.AppReferenceValues;
import com.glenwood.glaceemr.server.application.models.AppReferenceValues_;


public class EncounterSpecification {
	
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
	
	/**
	 * 
	 * get Encounter Plan by encounter Id 
	 * 
	 * @param encounterId
	 * @return
	 */
	public static Specification<EncounterPlan> getEncounterPlanByEncId(final Integer encounterId) {
		return new Specification<EncounterPlan>() {
			@Override
			public Predicate toPredicate(Root<EncounterPlan> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
				return cb.equal(root.get(EncounterPlan_.encounterid),encounterId);
			}
		};
	}
	
	 /**
     * search using encounter id
     * @param encounterId
     * @return
     */
    public static Specification<Encounter> EncounterById(final Integer encounterId, boolean join)
    {
        return new Specification<Encounter>() {
           
            @Override
            public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                Predicate pred = cb.equal(root.get(Encounter_.encounterId), encounterId);
                root.fetch(Encounter_.empProfileEmpId,JoinType.LEFT);
                root.fetch(Encounter_.referringTable,JoinType.LEFT);
                
                return pred;
            }
        };
    }
    

	/**
	 * 
	 * get Encounter reason id
	 * 
	 * @param reason id
	 * @return
	 */
	public static Specification<AppReferenceValues> getEncounterReasonId(final String reasonType, final Integer reasonGroup) {
		return new Specification<AppReferenceValues>() {
			@Override
			public Predicate toPredicate(Root<AppReferenceValues> root, CriteriaQuery<?> cq,CriteriaBuilder cb) {
				
				return cq.where(cb.equal(cb.upper(root.get(AppReferenceValues_.App_Reference_Values_statusName)), "REFILLS"), cb.equal(root.get(AppReferenceValues_.App_Reference_Values_reason_type), reasonGroup)).getRestriction();
			}
		};
	}

}
