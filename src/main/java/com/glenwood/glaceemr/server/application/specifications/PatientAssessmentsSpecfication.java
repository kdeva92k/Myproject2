package com.glenwood.glaceemr.server.application.specifications;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.CodingSystems;
import com.glenwood.glaceemr.server.application.models.PatientAssessments;
import com.glenwood.glaceemr.server.application.models.PatientAssessments_;


public class PatientAssessmentsSpecfication {
	
	/**
	 * Get assessments of patient in all his encounters 
	 * @param encounterList
	 * @return
	 */
	public static Specification<PatientAssessments> getByEncounterId(final List<Integer> encounterList)
	{
		return new Specification<PatientAssessments>() {
			
			@Override
			public Predicate toPredicate(Root<PatientAssessments> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<PatientAssessments,CodingSystems> dxCodingSystemJoin= root.join("codingsystemsTable",JoinType.INNER);
				Predicate pred=null;
				if(encounterList.size()>0)
					pred= root.get(PatientAssessments_.patient_assessments_id).in(encounterList);
				else
					pred= root.get(PatientAssessments_.patient_assessments_id).in(-999);
				
				query.where(pred).orderBy(cb.asc(root.get(PatientAssessments_.patient_assessments_dxcode)));
				return query.getRestriction();
			}
		};
	}

}
