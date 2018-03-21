package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.PatientPortalAlertConfig;
import com.glenwood.glaceemr.server.application.models.PatientPortalAlertConfig_;

public class PortalAlertSpecification {

	/**
	 * @param patientId	: used to get total messages list of a patient of that particular id
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<PatientPortalAlertConfig> getPortalAlertCategoryByName(final String alertCategory)
	   {
		   return new Specification<PatientPortalAlertConfig>() {

			@Override
			public Predicate toPredicate(Root<PatientPortalAlertConfig> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
								
				Predicate alertPredicate=cq.where(cb.equal(root.get(PatientPortalAlertConfig_.patient_portal_alert_config_display_name), alertCategory)).getRestriction();
				
				return alertPredicate;
			}
			   
		};
	   }
	
	
}
