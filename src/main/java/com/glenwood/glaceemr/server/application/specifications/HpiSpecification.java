package com.glenwood.glaceemr.server.application.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.HpiElement;
import com.glenwood.glaceemr.server.application.models.HpiElement_;
import com.glenwood.glaceemr.server.application.models.HpiSymptom;
import com.glenwood.glaceemr.server.application.models.HpiSymptomOrderby;
import com.glenwood.glaceemr.server.application.models.HpiSymptomOrderby_;
import com.glenwood.glaceemr.server.application.models.HpiSymptomQualifier;
import com.glenwood.glaceemr.server.application.models.HpiSymptomQualifier_;
import com.glenwood.glaceemr.server.application.models.HpiSymptom_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
/**
 * Specification for HPI
 * @author Bhagya Lakshmi
 *
 */

public class HpiSpecification {

	/**
	 * Used to get HpiSymptomOrderby details basing on hpiSymptomOrderbyIsactive is true
	 * @return
	 */
	public static Specification<HpiSymptomOrderby> OrderBy(){
		return new Specification<HpiSymptomOrderby>() {

			@Override
			public Predicate toPredicate(Root<HpiSymptomOrderby> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.where(cb.equal(root.get(HpiSymptomOrderby_.hpiSymptomOrderbyIsactive),true));
				return query.getRestriction();
			}
		};
	}

	/**
	 * Used to get HpiSymptom details basing on list of symptomgwid's present in hpiSymptomGwid
	 * @param symptomGWId
	 * @return
	 */
	public static Specification<HpiSymptom> getBySymptomGWId(final String symptomGWId) {
		return new Specification<HpiSymptom>() {

			@Override
			public Predicate toPredicate(Root<HpiSymptom> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String[] split = symptomGWId.split(",");
				List<String> data = new ArrayList<String>();
				for(int i=0;i<split.length;i++)
				{
					data.add(split[i]);
				}
				query.where(root.get(HpiSymptom_.hpiSymptomGwid).in(data));
				return query.getRestriction();
			}
			
		};
	}

	/**
	 * Used to get hpiSymptom data basing on symptomId 
	 * @param symptomId
	 * @return
	 */
	public static Specification<HpiSymptom> getBYSymptomId(final String symptomId) {
		return new Specification<HpiSymptom>() {

			@Override
			public Predicate toPredicate(Root<HpiSymptom> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.equal(root.get(HpiSymptom_.hpiSymptomId),Integer.parseInt(symptomId)));
				return query.getRestriction();
			}
		};
	}

	/**
	 * Used to get HpiSymptom data basing on hpiSymptomId list
	 * @return
	 */
	public static Specification<HpiSymptom> getByHpiSymptomId(final List<Integer> hpiSymptomIdListInteger) {
		return new Specification<HpiSymptom>() {

			@Override
			public Predicate toPredicate(Root<HpiSymptom> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(root.get(HpiSymptom_.hpiSymptomId).in(hpiSymptomIdListInteger));
				return query.getRestriction();
			}
			
		};
	}
	
	/**
	 * get HpiSymptomQualifier details basing on symptomId 
	 * @param symptomId
	 * @param isFollowUp
	 * @param orderBy
	 * @return
	 */
	public static Specification<HpiSymptomQualifier> getQualifierQuery(final String symptomId,
			final int orderBy) {
		return new Specification<HpiSymptomQualifier>() {

			@Override
			public Predicate toPredicate(Root<HpiSymptomQualifier> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate symptomidPred=(cb.equal(root.get(HpiSymptomQualifier_.hpiSymptomQualifierSymptomId),symptomId));
				Predicate symptomIsActivePred=cb.equal(root.get(HpiSymptomQualifier_.hpiSymptomQualifierIsactive),true);
				query.where(cb.and(symptomidPred,symptomIsActivePred));
				
				if(orderBy == 2)
				{
					query.orderBy(cb.asc(root.get(HpiSymptomQualifier_.hpiSymptomQualifierName)));
				}
				else if(orderBy == 3)
				{
					query.orderBy(cb.asc(root.get(HpiSymptomQualifier_.hpiSymptomQualifierOrderby)));
				}
				return query.getRestriction();
			}
			
		};
	}
	
	/**
	 * used to get hpiSymptomQualifier details basing on symptomId list
	 * @param hpiSymptomIdList
	 * @return
	 */
	public static Specification<HpiSymptomQualifier> getByQualifierSymptomIdList(
			final List<Integer> hpiSymptomIdList) {
		return new Specification<HpiSymptomQualifier>() {

			@Override
			public Predicate toPredicate(Root<HpiSymptomQualifier> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(root.get(HpiSymptomQualifier_.hpiSymptomQualifierSymptomId).in(hpiSymptomIdList));
				return query.getRestriction();
			}
			
		};

	}

	/**
	 * used to get HpiSymptomQualifier details basing on  hpiElementQualifierid list
	 * @param hpiElementQualifierid
	 * @return
	 */
	public static Specification<HpiSymptomQualifier> getByQualifierSymptomId(final List<Integer> hpiElementQualifierid) {
		return new Specification<HpiSymptomQualifier>() {

			@Override
			public Predicate toPredicate(Root<HpiSymptomQualifier> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(root.get(HpiSymptomQualifier_.hpiSymptomQualifierId).in(hpiElementQualifierid));
				return query.getRestriction();
			}
			
		};
	}
	
	/**
	 * used to get hpiElement details basing on hpiElementQualifierIdList
	 * @param hpiSymptomQualifierIdList
	 * @return
	 */
	public static Specification<HpiElement> getByHpiElementQualifierIdList(final List<Integer> hpiSymptomQualifierIdList) {
		return new Specification<HpiElement>() {

			@Override
			public Predicate toPredicate(Root<HpiElement> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(root.get(HpiElement_.hpiElementQualifierid).in(hpiSymptomQualifierIdList));
				return query.getRestriction();
			}
			
		};
	}

	/**
	 * used to get hpiElement details basing on symptomgwid
	 * @param gwid
	 * @return
	 */
	public static Specification<HpiElement> getByHpiElementGwid(final String gwid) {
		return new Specification<HpiElement>() {

			@Override
			public Predicate toPredicate(Root<HpiElement> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(root.get(HpiElement_.hpiElementGwid).in(gwid));
				return query.getRestriction();
			}
			
		};
	}

	/**
	 * used to get details from hpiElement basing on the corresponding qualifierId
	 * @param qualifierId
	 * @param orderBy
	 * @param patientGender
	 * @param ageinDay
	 * @param patientId
	 * @param encounterId
	 * @param isAgeBased
	 * @return
	 */
	/*public static Specification<HpiElement> getElementQuery(final int qualifierId, final int orderBy,
			final int patientGender, final int ageinDay, final Integer patientId,
			final Integer encounterId, final boolean isAgeBased) {
		return new Specification<HpiElement>() {

			@Override
			public Predicate toPredicate(Root<HpiElement> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Join<HpiElement,ClinicalElements> gwIdJoin = root.join(HpiElement_.clinicalElements,JoinType.INNER);
				Predicate symptomGwidPred=gwIdJoin.get(ClinicalElements_.clinicalElementsGender).in("0",patientGender);
				gwIdJoin.on(symptomGwidPred);
				
				Join<HpiElement,PatientClinicalElements> gwIdPatientJoin = root.join(HpiElement_.patientClinicalElements,JoinType.LEFT);
				Predicate patientIdPred=(cb.equal(gwIdPatientJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId));
				Predicate patientEncIdPred=(cb.equal(gwIdPatientJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId));
				gwIdPatientJoin.on(patientIdPred,patientEncIdPred);
				
				Predicate hpiPred=cb.and(cb.equal(root.get(HpiElement_.hpiElementQualifierid),qualifierId),cb.equal(root.get(HpiElement_.hpiElementIsactive), true));
				
				if(isAgeBased)
				{
					Predicate andPred=(cb.and(hpiPred,gwIdPatientJoin.get(PatientClinicalElements_.patientClinicalElementsValue).isNotNull()));
					query.where(hpiPred,andPred);
				}
				else
				{
					query.where(hpiPred);
				}
				
				if(orderBy == 2 )
					query.orderBy(cb.asc(root.get(HpiElement_.hpiElementName)));
				if(orderBy == 3 )
					query.orderBy(cb.asc(root.get(HpiElement_.hpiElementOrderby)));
				
				return query.getRestriction();
			}
			
		};
	}

	
	public static Specification<HpiElement> getElementQueryAfterUnion(final int qualifierId, int orderBy,
			final int patientGender, final int ageinDay, final Integer patientId,
			final Integer encounterId, boolean isAgeBased) {
		return new Specification<HpiElement>() {

			@Override
			public Predicate toPredicate(Root<HpiElement> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<HpiElement,ClinicalElements> gwIdJoin = root.join(HpiElement_.clinicalElements,JoinType.INNER);
				Predicate symptomGwidPred=(cb.and(gwIdJoin.get(ClinicalElements_.clinicalElementsGender).in("0",patientGender)));
				gwIdJoin.on(symptomGwidPred);
				Join<HpiElement,PatientClinicalElements> gwIdPatientJoin = root.join(HpiElement_.patientClinicalElements,JoinType.LEFT);
				Predicate patientIdPred=(cb.equal(gwIdPatientJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId));
				Predicate patientEncIdPred=(cb.equal(gwIdPatientJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId));
				gwIdPatientJoin.on(patientIdPred,patientEncIdPred);
				Join<ClinicalElements,ClinicalElementsCondition> clinicalElementsConditionJoin = gwIdJoin.join(ClinicalElements_.clinicalElementsConditions,JoinType.LEFT);
				Predicate qualifierIdPred=(cb.equal(root.get(HpiElement_.hpiElementQualifierid),qualifierId));
				
				Predicate andPred1= cb.and(gwIdPatientJoin.get(PatientClinicalElements_.patientClinicalElementsValue).isNull(),clinicalElementsConditionJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage).isNull(),clinicalElementsConditionJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage).isNull());
				Predicate andPred2= cb.and(cb.lessThan(clinicalElementsConditionJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage), ageinDay),cb.greaterThanOrEqualTo(clinicalElementsConditionJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage), ageinDay));
				Predicate orPred= cb.or(andPred1,andPred2);
				
				Predicate elemId=cb.and(cb.equal(root.get(HpiElement_.hpiElementIsactive),true));
				query.where(qualifierIdPred,orPred,elemId);
				return query.getRestriction();
			}
			
		};
	}
*/

	/**
	 * used to delete symptom list basing on symtomGwid
	 * @param symptomGWId
	 * @param encounterId
	 * @return
	 */
	public static Specification<PatientClinicalElements> getDeleteSymptomList(final String symptomGWId, final Integer encounterId) {
		return new Specification<PatientClinicalElements>() {

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.and(cb.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid), symptomGWId), cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId)));
				return query.getRestriction();
			}
			
		};
	}
}
