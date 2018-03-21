package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.AllergiesEncountermap;
import com.glenwood.glaceemr.server.application.models.AllergiesEncountermap_;
import com.glenwood.glaceemr.server.application.models.PatientAllergies;
import com.glenwood.glaceemr.server.application.models.PatientAllergies_;
import com.glenwood.glaceemr.server.application.models.Unii;
import com.glenwood.glaceemr.server.application.models.Unii_;
import com.glenwood.glaceemr.server.utils.HUtil;
/**
 * @author Bhagya Lakshmi
 * Allergies Specification
 */

public class AllergiesSpecification {

	
	/**
	 * Used to get details from Unii basing on uniiSubstance 
	 * @return
	 */
	public static Specification<Unii> getByLikeUniiSubstance(final String Criteria){
		return new Specification<Unii>() {

			@Override
			public Predicate toPredicate(Root<Unii> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.where(cb.like(root.get(Unii_.uniiSubstance), HUtil.ValidateSingleQuote(Criteria.toString())));
				
				return query.getRestriction();
			}
		};
	}

	/**
	 * Used to get Allergies encounter map details basing on chartId and reviewed true
	 * @param chartId
	 * @return
	 */
	public static Specification<AllergiesEncountermap> getbyChartIdAndReview(final String chartId) {
		return new Specification<AllergiesEncountermap>() {

			@Override
			public Predicate toPredicate(Root<AllergiesEncountermap> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.and(cb.equal(root.get(AllergiesEncountermap_.allergencmapChartid), chartId)),
							cb.equal(root.get(AllergiesEncountermap_.allergencmapReviewed), true));
				return query.getRestriction();
			}
		};
	}
	
	/**
	 * Used to get Allergies encounter map details basing on chartId
	 * @param chartId
	 * @return
	 */
	public static Specification<AllergiesEncountermap> getbyChartId(final String chartId) {
		return new Specification<AllergiesEncountermap>() {

			@Override
			public Predicate toPredicate(Root<AllergiesEncountermap> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.equal(root.get(AllergiesEncountermap_.allergencmapChartid), chartId));
				return query.getRestriction();
			}
		};
	}

	/**
	 * Used to get patient allergies details basing on chartId and typeId
	 * @param chartId
	 * @param tYPEID
	 * @return
	 */
	public static Specification<PatientAllergies> getbyChartIdAndTypeId(final String chartId, final int tYPEID) {
		return new Specification<PatientAllergies>() {

			@Override
			public Predicate toPredicate(Root<PatientAllergies> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.and(cb.equal(root.get(PatientAllergies_.patAllergTypeId), tYPEID),cb.equal(root.get(PatientAllergies_.patAllergChartId), chartId)));
				return query.getRestriction();
			}
			
		};
	}
}

