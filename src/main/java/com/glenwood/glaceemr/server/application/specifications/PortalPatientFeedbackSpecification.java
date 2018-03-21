package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.PatientFeedback;
import com.glenwood.glaceemr.server.application.models.PatientFeedbackQuestionnaire;
import com.glenwood.glaceemr.server.application.models.PatientFeedbackQuestionnaire_;
import com.glenwood.glaceemr.server.application.models.PatientFeedback_;

public class PortalPatientFeedbackSpecification {

	
	/**
	 * @param patientId	: used to get appointments list of a patient of that particular id
	 * @param appointmentsType : all appointment (Future,Past,Present)
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<PatientFeedbackQuestionnaire> getPatientFeedbackQuestionnaire()
	{
		return new Specification<PatientFeedbackQuestionnaire>() {

			@Override
			public Predicate toPredicate(Root<PatientFeedbackQuestionnaire> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {				

				root.fetch(PatientFeedbackQuestionnaire_.patientFeedbackQuestionnaireChoiceList);

				Predicate resultPredicate=cq.where().getRestriction();

				return resultPredicate;

			}

		};
	}
	
	/**
	 * @param patientId	: used to get appointments list of a patient of that particular id
	 * @param appointmentsType : all appointment (Future,Past,Present)
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<PatientFeedback> getPatientFeedback(final int patientId, final int feedbackId)
	{
		return new Specification<PatientFeedback>() {

			@Override
			public Predicate toPredicate(Root<PatientFeedback> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {				
				
				Predicate resultPredicate=cq.where(cb.equal(root.get(PatientFeedback_.patientId), patientId), cb.equal(root.get(PatientFeedback_.feedbackId), feedbackId)).getRestriction();

				return resultPredicate;

			}

		};
	}
	
}
