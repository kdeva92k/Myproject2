package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.CnmSettings;
import com.glenwood.glaceemr.server.application.models.CnmSettings_;


public class CNMSettingsSpecification {
	
	public static Specification<CnmSettings> getReviewDetails(final Integer id){

		return new Specification<CnmSettings>(){

			@Override
			public Predicate toPredicate(Root<CnmSettings> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate idPred=cb.equal(root.get(CnmSettings_.cnmSettingsId),id);
				Predicate activePred=cb.equal(root.get(CnmSettings_.cnmSettingsIsactive),true);
				return cb.and(idPred,activePred);
			}
		};
	}

}
