package com.glenwood.glaceemr.server.application.specifications;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;

public class InternalMessagesSpecification {

	/**
	 * To fetch all the phone message encounter based on patient id
	 * @param patientId
	 * @return
	 */
	public static Specification<Encounter> getIMEncounters(final String patientId){
		return new Specification<Encounter>() {
			
			@Override
			public Predicate toPredicate(Root<Encounter> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Join<Encounter,Chart> patientIdJoin=root.join(Encounter_.chart,JoinType.INNER);
				Join<Encounter,EmployeeProfile> serviceDrJoin=root.join(Encounter_.encounterCreatedByEmpProf,JoinType.INNER);

				Predicate[] predicateApUserId;

				predicateApUserId= new Predicate[] {
						cb.equal(root.get(Encounter_.encounterType), "4"),
						cb.equal(patientIdJoin.get(Chart_.chartPatientid), patientId)
				};


				Predicate predicate=cb.and(predicateApUserId);

				root.join("chart",JoinType.INNER);
				root.join("encounterCreatedByEmpProf",JoinType.INNER);

				root.fetch(Encounter_.chart,JoinType.INNER);
				root.fetch(Encounter_.encounterCreatedByEmpProf,JoinType.INNER);
				root.fetch(Encounter_.empProfileEmpId,JoinType.LEFT);

				return predicate;
			}
		};
	}

	public static Specification<Encounter> getEncounterByChartIdAndEncType(final String chartId, final int encounterType,
			final List<Integer> statusList) {
		return new Specification<Encounter>() {

			@Override
			public Predicate toPredicate(Root<Encounter> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate chartIdCond=cb.equal(root.get(Encounter_.encounterChartid), chartId);
				Predicate encounterTypeCond=cb.equal(root.get(Encounter_.encounterType), encounterType);
				Predicate statusCond=root.get(Encounter_.encounterStatus).in(statusList);
				
				return cb.and(chartIdCond,encounterTypeCond,statusCond);
			}
			
		};
	}
}
