package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.data.jpa.domain.Specification;


import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.Room;
import com.glenwood.glaceemr.server.application.models.Room_;
import com.glenwood.glaceemr.server.application.models.Workflow;
import com.glenwood.glaceemr.server.application.models.Workflow_;




public class WorkflowAlertSpecification {

	/**
	 * 
	 * @param patientId
	 * @return condition for getting alert by patientid
	 */
	public static Specification<Workflow> getAlertByPatientId(final int patientId)
	{
		return new Specification<Workflow>() {

			@Override
			public Predicate toPredicate(Root<Workflow> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate patientIdPrdcte=query.where(cb.equal(root.get(Workflow_.workflowPatientid), patientId)).getRestriction();
				Predicate isActivePrdcte=query.where(cb.equal(root.get(Workflow_.workflowIsactive), true)).getRestriction();
				Predicate predicate=cb.and(patientIdPrdcte,isActivePrdcte);
				query.orderBy(cb.asc(root.get(Workflow_.workflowId)));
				return predicate;
			}
		};
	}

	/**
	 * 
	 * @param patientId
	 * @return condition for getting alert by patientid
	 */
	public static Specification<Workflow> getAllAlertByPatientId(final int patientId,final int encounterId)
	{
		return new Specification<Workflow>() {

			@Override
			public Predicate toPredicate(Root<Workflow> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate patientIdPrdcte=query.where(cb.and(cb.equal(root.get(Workflow_.workflowEncounterid), encounterId),
						cb.equal(root.get(Workflow_.workflowPatientid), patientId))).getRestriction();
				Predicate predicate=cb.and(patientIdPrdcte);
				return predicate;
			}
		};
	}

	/**
	 * 
	 * @param userId
	 * @return condition for getting alert by userid
	 */
	public static Specification<Workflow> getAlertByUserId(final int userId)
	{
		return new Specification<Workflow>() {

			@Override
			public Predicate toPredicate(Root<Workflow> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate isActivePredicate=query.where(cb.equal(root.get(Workflow_.workflowIsactive), true)).getRestriction();
				Predicate predicate=cb.and(isActivePredicate,query.where(cb.equal(root.get(Workflow_.workflowToid), userId)).getRestriction());
				return predicate;
			}
		};
	}

	/**
	 * 
	 * @param alertId
	 * @return condition for getting alert by alertid
	 */
	public static Specification<Workflow> getAlertByAlertId(final int alertId) {
		return new Specification<Workflow>() {

			@Override
			public Predicate toPredicate(Root<Workflow> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=query.where(cb.equal(root.get(Workflow_.workflowId), alertId)).getRestriction();
				return predicate;
			}
		};
	}


	/**
	 *
	 * @return condition for getting active room details 
	 */
	public static Specification<Room> getRoomDetails() {
		return new Specification<Room>() {

			@Override
			public Predicate toPredicate(Root<Room> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=query.where(cb.equal(root.get(Room_.roomIsActive), true)).getRestriction();
				return predicate;
			}
		};
	}

		/**
	 * 
	 * @param encounterId
	 * @return condition for getting alert by alertid
	 */
	public static Specification<Workflow> getAllAlertsByEncounterId(final int encounterId) {
		return new Specification<Workflow>() {

			@Override
			public Predicate toPredicate(Root<Workflow> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate isActive=query.where(cb.equal(root.get(Workflow_.workflowIsactive), true)).getRestriction();
				Predicate predicate=cb.and(isActive,query.where(cb.equal(root.get(Workflow_.workflowEncounterid), encounterId)).getRestriction());
				return predicate;
			}
		};
	}
	
	/**
	 * 
	 * @param patientId
	 * @return condition for getting workflow alert based on encounter checked out
	 */
	public static Specification<Workflow> getClosedEncounterWFAlerts()
	{
		return new Specification<Workflow>() {

			@Override
			public Predicate toPredicate(Root<Workflow> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Join<Workflow,Encounter> serviceDrJoin=root.join(Workflow_.encounter,JoinType.INNER);
				Predicate predicate=query.where(cb.and(cb.equal(root.get(Workflow_.workflowIsactive), true),
						cb.notEqual(serviceDrJoin.get(Encounter_.encounterStatus),1))).getRestriction();
				return predicate;
			}
		};
	}

	/**
	 * 
	 * @param patientId
	 * @return condition for getting active workflow alert 
	 */
	public static Specification<Workflow> getActiveAlerts()
	{
		return new Specification<Workflow>() {

			@Override
			public Predicate toPredicate(Root<Workflow> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
			
				Predicate predicate=cb.and(cb.equal(root.get(Workflow_.workflowIsactive), true));
				return predicate;
			}
		};
	}
}
