package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.BillingConfigTable;
import com.glenwood.glaceemr.server.application.models.BillingConfigTable_;

public class BillingConfigTableSpecification {

	/**
	 * Get State
	 * @param state
	 * @return
	 */
	public static Specification<BillingConfigTable> getState(final String state) {
		return new Specification<BillingConfigTable>() {
			
			@Override
			public Predicate toPredicate(Root<BillingConfigTable> root,
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				Predicate statePedicate = builder.equal(root.get(BillingConfigTable_.billingConfigTableConfigId), state);
				Predicate lookupPredicate = builder.equal(root.get(BillingConfigTable_.billingConfigTableLookupId), 5001);
				
				return builder.and(statePedicate,lookupPredicate);
			}
		};
	}
	
	/**
	 * Get Gender
	 * @param gender
	 * @return
	 */
	public static Specification<BillingConfigTable> getGender(final String gender) {
		return new Specification<BillingConfigTable>() {
			
			@Override
			public Predicate toPredicate(Root<BillingConfigTable> root,
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				Predicate genderPedicate = builder.equal(root.get(BillingConfigTable_.billingConfigTableConfigId), gender);
				Predicate lookupPredicate = builder.equal(root.get(BillingConfigTable_.billingConfigTableLookupId), 51);
				
				return builder.and(genderPedicate,lookupPredicate);
			}
		};
	}

	/**
	 * Get State, Gender
	 * @param state
	 * @param gender
	 * @return
	 */
	public static Specification<BillingConfigTable> getStateGender(final String state, final String gender) {
		return new Specification<BillingConfigTable>() {
			
			@Override
			public Predicate toPredicate(Root<BillingConfigTable> root,
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				Predicate statePedicate = builder.equal(root.get(BillingConfigTable_.billingConfigTableConfigId), state);
				Predicate lookupPredicate1 = builder.equal(root.get(BillingConfigTable_.billingConfigTableLookupId), 5001);
				
				Predicate genderPedicate = builder.equal(root.get(BillingConfigTable_.billingConfigTableConfigId), gender);
				Predicate lookupPredicate2 = builder.equal(root.get(BillingConfigTable_.billingConfigTableLookupId), 51);
				
				Predicate sPred = builder.and(statePedicate,lookupPredicate1);
				Predicate gPred = builder.and(genderPedicate,lookupPredicate2);
				
				return builder.or(sPred, gPred);
			}
		};
	}

}
