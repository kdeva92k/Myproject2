package com.glenwood.glaceemr.server.application.specifications;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;


public class EncounterEntitySpecification {
	
	/**
	 * Search using encounter id
	 * @param encounterId
	 * @return
	 */
	public static Specification<Encounter> EncounterById(final Integer encounterId)
	{
		return new Specification<Encounter>() {
			
			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate pred = cb.equal(root.get(Encounter_.encounterId), encounterId);
				return pred;
			}
		};
	}
	
	/**
	 * Search using chart id
	 * @param chartId
	 * @return
	 */
	public static Specification<Encounter> EncounterByChartId(final Integer chartId)
	{
		return new Specification<Encounter>() {
			
			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate pred = cb.equal(root.get(Encounter_.encounterChartid), chartId);
				return pred;
			}
		};
	}
	
	/**
	 * Search using encounter type
	 * @param type
	 * @return
	 */
	public static Specification<Encounter> EncounterByType(final Integer type)
	{
		return new Specification<Encounter>() {
			
			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate pred = cb.equal(root.get(Encounter_.encounterType), type);
				return pred;
			}
		};
	}
	
	/**
	 * Ordering with encounter date in ascending order
	 * @return
	 */
	public static Specification<Encounter> orderByDate()
	{
		return new Specification<Encounter>() {
			
			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.orderBy(cb.asc(root.get(Encounter_.encounterDate)));
				return query.getRestriction();
			}
		};
	}
	
	/**
	 * Get encounters for list of encounter Ids
	 * @param encounterIds
	 * @return
	 */
	public static Specification<Encounter> encountersByIds(final Integer[] encounterIds)
	{
		return new Specification<Encounter>() {
			
			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate pred = root.get(Encounter_.encounterId).in((Object[])encounterIds);
				return pred;
			}
		};
	}
	
	/**
	 * Get Encounter Details based on Encounter Id
	 * 
	 * @param encounterId
	 * 
	 */
	public static Specification<Encounter> getEncounterByEncId(final Integer encounterId) {
		return new Specification<Encounter>() {
			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
				return cb.equal(root.get(Encounter_.encounterId),encounterId);
			}
		};
	}
	
	
	/**
	 * Get Encounter Details based on patient Id
	 * 
	 * @param encounterId
	 * 
	 */
	public static Specification<Encounter> getEncounterByPatId(final Integer patientId,final Integer encounterId) {
		return new Specification<Encounter>() {
			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
				Join<Encounter, Chart> paramJoin=root.join(Encounter_.chart,JoinType.INNER);
				Join<Chart, PatientRegistration> regJoin=paramJoin.join(Chart_.patientRegistrationTable);
				regJoin.on(cb.equal(regJoin.get(PatientRegistration_.patientRegistrationId),patientId));
				query.orderBy(cb.desc(root.get(Encounter_.encounterId)));
				return cb.notEqual(root.get(Encounter_.encounterId), encounterId);
			}
		};
	}
	
	/**
	 * 
	 * Gets the previous Encounter details (having patient Data) 
	 * 
	 * @param patientId
	 * @param encounterId
	 * 
	 */
	public static Specification<Encounter> getPrevEncHavingData(final Integer patientId,final Integer encounterId) {
		return new Specification<Encounter>() {

			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
				Join<Encounter, PatientClinicalElements> patClinicalElementsJoin=root.join(Encounter_.patientClinicalElements,JoinType.INNER);
				Predicate patPredicate=cb.equal(patClinicalElementsJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
				Predicate encPredicate=cb.notEqual(patClinicalElementsJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId);
				query.orderBy(cb.desc(patClinicalElementsJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid)));
				return cb.and(patPredicate,encPredicate);
			}
		};
	}
	
	
}