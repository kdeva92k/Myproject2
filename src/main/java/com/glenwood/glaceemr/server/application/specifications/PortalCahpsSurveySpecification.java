package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.CahpsQuestionnaire;
import com.glenwood.glaceemr.server.application.models.CahpsQuestionnaire_;
import com.glenwood.glaceemr.server.application.models.PatientCahpsSurvey;
import com.glenwood.glaceemr.server.application.models.PatientCahpsSurvey_;
import com.glenwood.glaceemr.server.application.models.QuestionnaireMapping;
import com.glenwood.glaceemr.server.application.models.QuestionnaireMapping_;

public class PortalCahpsSurveySpecification {


	/**
	 * @param patientId	: used to get appointments list of a patient of that particular id
	 * @param appointmentsType : all appointment (Future,Past,Present)
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<CahpsQuestionnaire> getPatientTotalAppointments(final int patientAge)
	{
		return new Specification<CahpsQuestionnaire>() {

			@Override
			public Predicate toPredicate(Root<CahpsQuestionnaire> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {				

				Join<CahpsQuestionnaire, QuestionnaireMapping> cahpsQuestionnaireMapping=root.join(CahpsQuestionnaire_.questionnaireMapping);

				//root.fetch(CahpsQuestionnaire_.cahpsQuestionnaireChoiceList, JoinType.INNER);
				root.fetch(CahpsQuestionnaire_.questionnaireMapping, JoinType.INNER);

				Predicate patientTypePrediate;

				if(patientAge<=18)
					patientTypePrediate=cb.equal(cb.upper(cahpsQuestionnaireMapping.get(QuestionnaireMapping_.questionnaireGroup)), "CHILD");
				else
					patientTypePrediate=cb.equal(cb.upper(cahpsQuestionnaireMapping.get(QuestionnaireMapping_.questionnaireGroup)), "ADULT");

				Predicate resultPredicate=cq.where(patientTypePrediate).orderBy(cb.asc(cahpsQuestionnaireMapping.get(QuestionnaireMapping_.mappingSequence))).getRestriction();

				return resultPredicate;

			}

		};
	}
	
	/**
	 * @param patientId	: used to get appointments list of a patient of that particular id
	 * @param appointmentsType : all appointment (Future,Past,Present)
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<PatientCahpsSurvey> getLastPatientSurvey(final int patientId, final int surveyId)
	{
		return new Specification<PatientCahpsSurvey>() {

			@Override
			public Predicate toPredicate(Root<PatientCahpsSurvey> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {			

				Predicate resultPredicate=cq.where(cb.equal(root.get(PatientCahpsSurvey_.surveyId), surveyId),
						cb.equal(root.get(PatientCahpsSurvey_.patientId), patientId)).getRestriction();

				return resultPredicate;

			}

		};
	}

}
