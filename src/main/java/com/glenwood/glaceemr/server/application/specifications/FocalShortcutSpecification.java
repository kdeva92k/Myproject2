package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.FocalShortcut;
import com.glenwood.glaceemr.server.application.models.FocalShortcutElements;
import com.glenwood.glaceemr.server.application.models.FocalShortcutElements_;
import com.glenwood.glaceemr.server.application.models.FocalShortcut_;
/**
 * Specification for Focal Shortcuts
 * @author Bhagya Lakshmi
 *
 */

public class FocalShortcutSpecification {

	/**
	 * Search using tabId
	 * @param tabId
	 * @return
	 */
	public static Specification<FocalShortcut> getByTabId(final String tabId) {
		return new Specification<FocalShortcut>() {

			@Override
			public Predicate toPredicate(Root<FocalShortcut> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.and(cb.equal(root.get(FocalShortcut_.focalShortcutIsactive), true),cb.equal(root.get(FocalShortcut_.focalShortcutTabid), tabId)));
				return query.getRestriction();
			}

		};
	}
	
	/**
	 * Search using tabId
	 * @param tabId
	 * @return
	 */
	public static Specification<FocalShortcut> getByFocalShrtNameAndTabId(final Integer tabId,final String focalsearch) {
		return new Specification<FocalShortcut>() {

			@Override
			public Predicate toPredicate(Root<FocalShortcut> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p1 = cb.equal(root.get(FocalShortcut_.focalShortcutIsactive), true);
				Predicate p2 = cb.equal(root.get(FocalShortcut_.focalShortcutTabid), tabId);
				Predicate p3 = cb.like(cb.lower(root.get(FocalShortcut_.focalShortcutName)), getLikePattern(focalsearch));
				query.where(cb.and(p1,p2,p3));
				return query.getRestriction();
			}

		};
	}

	/**
	 * Search using focal shortcutId
	 * @param shortcutId
	 * @return
	 */
	public static Specification<FocalShortcutElements> getByFocalShortcutEelmentId(final String shortcutId) {
		return new Specification<FocalShortcutElements>() {

			@Override
			public Predicate toPredicate(Root<FocalShortcutElements> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.equal(root.get(FocalShortcutElements_.focalShortcutElementsId), shortcutId));
				return query.getRestriction();

			}

		};
	}

	/**
	 * Search using focalId
	 * @param focalId
	 * @return
	 */
	public static Specification<FocalShortcut> getByFocalShortcutId(final String focalId) {
		return new Specification<FocalShortcut>() {

			@Override
			public Predicate toPredicate(Root<FocalShortcut> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.equal(root.get(FocalShortcut_.focalShortcutId), Integer.parseInt(focalId)));
				return query.getRestriction();
			}

		};
	}
	
	/**
	 * Returns the formatted the pattern
	 * @param searchTerm
	 * @return
	 */
	private static String getLikePattern(final String searchTerm) {
		StringBuilder pattern = new StringBuilder();
		pattern.append(searchTerm.toLowerCase());
		pattern.append("%");
		return pattern.toString();
	}
	
}
