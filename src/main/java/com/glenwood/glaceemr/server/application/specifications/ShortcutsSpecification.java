package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.SoapElementDatalist;
import com.glenwood.glaceemr.server.application.models.SoapElementDatalist_;

/**
 * Specification file for ShortcutsController
 * @author Chandrahas
 *
 */
public class ShortcutsSpecification {

	/**
	 * Method to Fetch shortcuts
	 * @param tabId
	 * @param elementId
	 * @return
	 */
	public static Specification<SoapElementDatalist> fetchShortcuts(final Integer tabId, final String elementId) {
		return new Specification<SoapElementDatalist>() {
			
			@Override
			public Predicate toPredicate(Root<SoapElementDatalist> root,
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				Predicate tabPred = builder.equal(root.get(SoapElementDatalist_.soapElementDatalistTabid), tabId);
				Predicate elementPred = builder.equal(root.get(SoapElementDatalist_.soapElementDatalistElementid), elementId);
				
				query.where(tabPred, elementPred).orderBy(builder.asc(root.get(SoapElementDatalist_.soapElementDatalistData)));
				
				return query.getRestriction();
			}
		};
	}
	
	/**
	 * Method to Fetch shortcuts
	 * @param shortcutId
	 * @return
	 */
	public static Specification<SoapElementDatalist> fetchShortcuts(final Integer shortcutId) {
		return new Specification<SoapElementDatalist>() {
			
			@Override
			public Predicate toPredicate(Root<SoapElementDatalist> root,
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				Predicate tabPred = builder.equal(root.get(SoapElementDatalist_.soapElementDatalistId), shortcutId);
				query.where(tabPred).orderBy(builder.asc(root.get(SoapElementDatalist_.soapElementDatalistData)));
				
				return query.getRestriction();
			}
		};
	}
}
