package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Overridealerts;
import com.glenwood.glaceemr.server.application.models.Overridealerts_;

public class OverridealertsSpecification {
	/**
	 * Specification to get the list of override alerts which are with elements details
	 * @param flowsheet Element Id
	 * @param flowsheet Element Type
	 * @param patient Id
	 * @param flowsheet Map Id
	 * @return Specification<Overridealerts>
	 */
	public static Specification<Overridealerts> overrideAlerts(final String[] flowsheetElementId,final Integer flowsheetElementType,final Integer patientId, final Integer flowsheetMapId,final Integer encounterId,final Encounter encounterEntity)
	{
		return new Specification<Overridealerts>() {

			@Override
			public Predicate toPredicate(Root<Overridealerts> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate elementType=cb.equal(root.get(Overridealerts_.overridealertsFlowsheetElementType),flowsheetElementType);
				Predicate flowsheetId=cb.equal(root.get(Overridealerts_.overridealertsFlowsheetMapId),flowsheetMapId);
				Predicate elementId=root.get(Overridealerts_.overridealertsFlowsheetElementId).in((Object[])flowsheetElementId);
				Predicate patientIdPred = cb.equal(root.get(Overridealerts_.patientid),patientId);
				Predicate overrideAlerts=null;
				if((encounterId!=-1)&&(encounterEntity.getEncounterStatus()==3)){
					Predicate encounterDatePred=cb.lessThanOrEqualTo(root.get(Overridealerts_.overriddenOn), encounterEntity.getEncounterDate());
					overrideAlerts=cb.and(elementType,flowsheetId,elementId,patientIdPred,encounterDatePred);
				}else{
					overrideAlerts=cb.and(elementType,flowsheetId,elementId,patientIdPred);
				}
				return overrideAlerts;
			}
		};
	}
}