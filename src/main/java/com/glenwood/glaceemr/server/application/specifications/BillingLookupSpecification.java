package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Billinglookup;
import com.glenwood.glaceemr.server.application.models.Billinglookup_;

public class BillingLookupSpecification {

	
	/**
	 * Get Ethinicity
	 * @param ethinicity
	 * @return
	 */
	public static Specification<Billinglookup> getEthinicity(final String ethinicity) {
		return new Specification<Billinglookup>() {
			
			@Override
			public Predicate toPredicate(Root<Billinglookup> root,
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				Predicate ethinPred= builder.equal(root.get(Billinglookup_.blookIntid), ethinicity);
				Predicate groupPred = builder.equal(root.get(Billinglookup_.blookGroup), 251);
				
				return builder.and(ethinPred, groupPred);
			}
		};
	}
	
	/**
	 * Get Race
	 * @param race
	 * @return
	 */
	public static Specification<Billinglookup> getRace(final String race) {
		return new Specification<Billinglookup>() {
			
			@Override
			public Predicate toPredicate(Root<Billinglookup> root,
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				Predicate racePred= builder.equal(root.get(Billinglookup_.blookIntid), race);
				Predicate groupPred = builder.equal(root.get(Billinglookup_.blookGroup), 250);
				
				return builder.and(racePred, groupPred);
			}
		};
	}
	
	/**
	 * Get Preferred / Primary Language
	 * @param prefLang
	 * @return
	 */
	public static Specification<Billinglookup> getPrefLanguage(final String prefLang) {
		return new Specification<Billinglookup>() {
			
			@Override
			public Predicate toPredicate(Root<Billinglookup> root,
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				Predicate prefLangPred= builder.equal(root.get(Billinglookup_.blookIntid), prefLang);
				Predicate groupPred = builder.equal(root.get(Billinglookup_.blookGroup), 253);
				
				return builder.and(prefLangPred, groupPred);
			}
		};
	}
	
	public static Specification<Billinglookup> getDetails(final String ethinicity, final String race, final String prefLang) {
		return new Specification<Billinglookup>() {
			
			@Override
			public Predicate toPredicate(Root<Billinglookup> root,
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				Predicate ethinPred= builder.equal(root.get(Billinglookup_.blookIntid), ethinicity);
				Predicate groupPred1 = builder.equal(root.get(Billinglookup_.blookGroup), 251);
				
				Predicate racePred= builder.equal(root.get(Billinglookup_.blookIntid), race);
				Predicate groupPred2 = builder.equal(root.get(Billinglookup_.blookGroup), 250);
				
				Predicate prefLangPred= builder.equal(root.get(Billinglookup_.blookIntid), prefLang);
				Predicate groupPred3 = builder.equal(root.get(Billinglookup_.blookGroup), 253);
				
				Predicate ethPred = builder.and(ethinPred, groupPred1);
				Predicate racPred = builder.and(racePred, groupPred2);
				Predicate prePred = builder.and(prefLangPred, groupPred3);
				
				return builder.or(ethPred, racPred, prePred);
			}
		};
	}
}
