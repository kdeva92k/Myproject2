package com.glenwood.glaceemr.server.application.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsCondition;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsCondition_;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions_;
import com.glenwood.glaceemr.server.application.models.ClinicalElements_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.HpiSymptom;
import com.glenwood.glaceemr.server.application.models.HpiSymptom_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalHistory;
import com.glenwood.glaceemr.server.application.models.PatientClinicalHistory_;
import com.glenwood.glaceemr.server.application.models.PatientEpisode;
import com.glenwood.glaceemr.server.application.models.PatientEpisodeAttributeDetails;
import com.glenwood.glaceemr.server.application.models.PatientEpisodeAttributeDetails_;
import com.glenwood.glaceemr.server.application.models.PatientEpisode_;
import com.glenwood.glaceemr.server.application.models.VitalsParameter;
import com.glenwood.glaceemr.server.application.models.VitalsParameter_;



public class PatientClinicalElementsSpecification {

	/**
	 *
	 *  Get PatientClinicalElements data for given GWID and it is not a history element
	 * @param patientId
	 * @param encounterId
	 * @param gwids
	 * 
	 */
	public static Specification<PatientClinicalElements> getNonHistoryElemPatientData(final Integer patientId,final Integer encounterId,final String gwidPattern){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalElements,ClinicalElements> paramJoin=root.join(PatientClinicalElements_.clinicalElement,JoinType.INNER);
				Predicate encounterPred=null;
				if(encounterId!=-1)
					encounterPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
				Predicate patientPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
				Predicate elementPred=cb.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid),gwidPattern);
				Predicate isHistoryPred=cb.equal(paramJoin.get(ClinicalElements_.clinicalElementsIshistory),false);
				Predicate finalPred=null;
				if(encounterId!=-1)
					finalPred= cb.and(encounterPred,patientPred,elementPred,isHistoryPred);
				else
					finalPred= cb.and(patientPred,elementPred,isHistoryPred);

				return finalPred;

			}

		};
	}




	/**
	 *
	 *  Get PatientClinicalElements data for given GWID and it is not a history element and its less than or equal to that encounter
	 * @param patientId
	 * @param encounterId
	 * @param gwids
	 * 
	 */

	public static Specification<PatientClinicalElements> getNonHistoryElemPatientDataLessThanEncounter(final Integer patientId,final Integer encounterId,final List<String> gwids){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalElements,ClinicalElements> paramJoin=root.join(PatientClinicalElements_.clinicalElement,JoinType.INNER);
				Predicate encounterPred=null;
				if(encounterId!=-1)
					encounterPred=cb.lessThanOrEqualTo(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
				Predicate patientPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
				Predicate elementPred=root.get(PatientClinicalElements_.patientClinicalElementsGwid).in(gwids);
				Predicate finalPred=null;
				if(encounterId!=-1)
					finalPred= cb.and(encounterPred,patientPred,elementPred);
				else
					finalPred= cb.and(patientPred,elementPred);
				return finalPred;

			}

		};
	}

	/**
	 * Get PatientClinicalData for patientId and encounterId and given GWID Pattern
	 * @param patientId
	 * @param encounterId
	 * @param gwids
	 * 
	 */
	public static Specification<PatientClinicalElements> getPatClinicalDataByGWID(final Integer patientId,final Integer encounterId,final String gwidPattern){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalElements,ClinicalElements> paramJoin=root.join(PatientClinicalElements_.clinicalElement,JoinType.INNER);
				Predicate encounterPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
				Predicate patientPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
				Predicate elementPred=cb.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid),gwidPattern);
				return cb.and(patientPred,elementPred,encounterPred);

			}

		};
	}
	
	/**
	 * Get PatientClinicalData for patientId for given GWIDS order by encounter date
	 * @param patientId
	 * @param encounterId
	 * @param gwids
	 * 
	 */
	public static Specification<PatientClinicalElements> getPatClinicalDataByGWID(final Integer patientId,final List<String> gwids){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalElements,Encounter> paramJoin=root.join(PatientClinicalElements_.encounter,JoinType.INNER);
				Predicate patientPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
				Predicate elementPred=root.get(PatientClinicalElements_.patientClinicalElementsGwid).in(gwids);
				query.orderBy(cb.desc(paramJoin.get(Encounter_.encounterDate)));
				return cb.and(patientPred,elementPred);

			}

		};
	}

	
	/**
	 * Get PatientClinicalData for patientId and encounterId and given GWIDS
	 * @param patientId
	 * @param encounterId
	 * @param gwids
	 * 
	 */
	public static Specification<PatientClinicalElements> getPatClinicalDataByGWID(final Integer patientId,final Integer encounterId,final List<String> gwids){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalElements,ClinicalElements> paramJoin=root.join(PatientClinicalElements_.clinicalElement,JoinType.INNER);
				Predicate encounterPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
				Predicate patientPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
				Predicate elementPred=root.get(PatientClinicalElements_.patientClinicalElementsGwid).in(gwids);
				return cb.and(patientPred,elementPred,encounterPred);

			}

		};
	}




	/**
	 * 
	 * GET patientClinicalHistory for GWIDS given
	 * 
	 * @param patientId
	 * @param gwids
	 * 
	 */
	public static Specification<PatientClinicalHistory> getHistoryElemPatientData(final Integer patientId,final String gwidPattern){

		return new Specification<PatientClinicalHistory>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalHistory> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalHistory,ClinicalElements> paramJoin=root.join(PatientClinicalHistory_.clinicalElement,JoinType.INNER);
				Predicate elementPred=cb.like(root.get(PatientClinicalHistory_.patientClinicalHistoryGwid),gwidPattern);
				Predicate patientPred=cb.equal(root.get(PatientClinicalHistory_.patientClinicalHistoryPatientid),patientId);
				Predicate isHistoryPred=cb.equal(paramJoin.get(ClinicalElements_.clinicalElementsIshistory),true);
				return cb.and(elementPred,patientPred,isHistoryPred);
			}

		};
	}


	/**
	 * 
	 * GET patientClinicalHistory for a patient
	 * 
	 * @param patientId
	 * @param gwids
	 * 
	 */
	public static Specification<PatientClinicalHistory> getPatClinicalHistoryData(final Integer patientId){

		return new Specification<PatientClinicalHistory>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalHistory> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalHistory,ClinicalElements> paramJoin=root.join(PatientClinicalHistory_.clinicalElement,JoinType.INNER);
				Predicate patientPred=cb.equal(root.get(PatientClinicalHistory_.patientClinicalHistoryPatientid),patientId);
				//Predicate isHistoryPred=cb.equal(paramJoin.get(ClinicalElements_.clinicalElementsIshistory),true);
				//return cb.and(patientPred,isHistoryPred);
				return cb.and(patientPred);
			}

		};
	}



	/**
	 * 
	 * Get patient clinical history for list of GWID's falling under respected patient age and sex 
	 * 
	 * @param isAgeBased
	 * @param patientId
	 * @param patientSex
	 * @param gwids
	 * @param ageInDay
	 * @return
	 */
	public static Specification<ClinicalElements> getPatClinicalHistoryData(final String clientId,final boolean isAgeBased,final Integer patientId,final Short patientSex,final String gwidPattern,final Integer ageInDay){

		return new Specification<ClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<ClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {

				Join<ClinicalElements,PatientClinicalHistory> paramJoin=root.join(ClinicalElements_.patientClinicalHistory,JoinType.INNER);
				Predicate patientPred=cb.equal(paramJoin.get(PatientClinicalHistory_.patientClinicalHistoryPatientid),patientId);
				Predicate defGWIDPred=cb.like(root.get(ClinicalElements_.clinicalElementsGwid),"000"+gwidPattern);
				Predicate clientGWIDPred=cb.like(root.get(ClinicalElements_.clinicalElementsGwid),clientId+gwidPattern);
				Predicate finalGWIDPred=cb.or(defGWIDPred,clientGWIDPred);
				Predicate genderPred=root.get(ClinicalElements_.clinicalElementsGender).in(patientSex,0);
				Predicate finalPred=cb.and(patientPred,finalGWIDPred,genderPred);

				if(isAgeBased==true){
					Join<ClinicalElements, ClinicalElementsCondition> condParamJoin=root.join(ClinicalElements_.clinicalElementsConditions,JoinType.LEFT);
					Predicate defaultstartAge=cb.isNull(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage));
					Predicate defaultendAge=cb.isNull(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage));
					Predicate startAge=cb.lessThan(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage),ageInDay);
					Predicate endAge=cb.greaterThanOrEqualTo(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage),ageInDay);
					Predicate defAgePred=cb.and(defaultstartAge,defaultendAge);
					Predicate agePred=cb.and(startAge,endAge);
					Predicate age =cb.or(defAgePred,agePred);
					finalPred=cb.and(finalPred,age);
				}


				return finalPred;
			}

		};
	}





	/**
	 * GET Patient clinical Data for vitals pre-load From LastVisit 
	 * @param patientId
	 * @param prevEncId
	 * @param gwids
	 * 
	 */
	public static Specification<PatientClinicalElements> getPrevEncounterPatVitalData(final Integer patientId,final Integer prevEncId,final String gwidPattern){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalElements,VitalsParameter> paramJoin=root.join(PatientClinicalElements_.vitalsParameter,JoinType.INNER);
				Join<PatientClinicalElements,ClinicalElements> clinicalJoin=root.join(PatientClinicalElements_.clinicalElement,JoinType.INNER);
				Predicate elementPred=cb.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid),gwidPattern);
				Predicate encounterPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),prevEncId);
				Predicate patientPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
				Predicate preLoadPred=cb.equal(paramJoin.get(VitalsParameter_.vitalsParameterPreloadFromLastVisit),true);
				Predicate isHistoryPred=cb.equal(clinicalJoin.get(ClinicalElements_.clinicalElementsIshistory),false);
				return cb.and(encounterPred,patientPred,elementPred,isHistoryPred,preLoadPred);
			}
		};
	}		





	/**
	 * Get patient clinical data for given encounter  
	 * 
	 * @param EncId
	 * 
	 */
	public static Specification<PatientClinicalElements> getEncPatientClinicalData(final Integer encounterId){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				root.fetch(PatientClinicalElements_.clinicalElement,JoinType.INNER);
				Predicate encounterPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
				return cb.and(encounterPred);
			}
		};
	}		

	
	/**
	 * 
	 * Get patient clinical data of given encounter for list of GWID's falling under respected patient age and sex 
	 * 
	 * @param isAgeBased
	 * @param encounterId
	 * @param patientSex
	 * @param gwidPattern
	 * @param ageInDay
	 * @return
	 */
	public static Specification<ClinicalElements> getEncPatientClinicalData(final String clientId,final boolean isAgeBased,final Integer encounterId,final Short patientSex,final String gwidPattern,final Integer ageInDay){
		return new Specification<ClinicalElements>(){
			@Override
			public Predicate toPredicate(Root<ClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<ClinicalElements,PatientClinicalElements> paramJoin=root.join(ClinicalElements_.patientClinicalElements,JoinType.INNER);
				Predicate encounterPred=cb.equal(paramJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
				
				Predicate gwidPred=cb.like(root.get(ClinicalElements_.clinicalElementsGwid),"000"+gwidPattern);
				Predicate clientIdPred=cb.like(root.get(ClinicalElements_.clinicalElementsGwid),clientId+gwidPattern);
				Predicate finalgwidPred=cb.or(gwidPred,clientIdPred);
				
				Predicate genderPred=root.get(ClinicalElements_.clinicalElementsGender).in(patientSex,0);
				Predicate finalPred=cb.and(encounterPred,finalgwidPred,genderPred);

				if(isAgeBased==true){
					Join<ClinicalElements,ClinicalElementsCondition> condParamJoin=root.join(ClinicalElements_.clinicalElementsConditions,JoinType.LEFT);
					Predicate defaultstartAge=cb.isNull(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage));
					Predicate defaultendAge=cb.isNull(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage));
					Predicate startAge=cb.lessThan(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage),ageInDay);
					Predicate endAge=cb.greaterThanOrEqualTo(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage),ageInDay);
					Predicate defAgePred=cb.and(defaultstartAge,defaultendAge);
					Predicate agePred=cb.and(startAge,endAge);
					Predicate age =cb.or(defAgePred,agePred);
					finalPred=cb.and(finalPred,age);
				}
				return finalPred;
			}
		};
	}			


	/**
	 * Get patient Episode data for given encounter  
	 * 
	 * @param EncId
	 * 
	 */
	public static Specification<PatientEpisodeAttributeDetails> getPatientEpisodeElements(final Integer patientId,final Integer episodeId){

		return new Specification<PatientEpisodeAttributeDetails>(){

			@Override
			public Predicate toPredicate(Root<PatientEpisodeAttributeDetails> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientEpisodeAttributeDetails,PatientEpisode> episodeJoin=root.join(PatientEpisodeAttributeDetails_.patientEpisode,JoinType.INNER);
				Join<PatientEpisodeAttributeDetails,ClinicalElements> clincialJoin=root.join(PatientEpisodeAttributeDetails_.clinicalElements);
				Predicate patientPred=cb.equal(episodeJoin.get(PatientEpisode_.patientEpisodePatientid),patientId);
				Predicate episodePred=cb.equal(episodeJoin.get(PatientEpisode_.patientEpisodeId),episodeId);
				return cb.and(patientPred,episodePred);
			}
		};
	}		


	/**
	 * 
	 * Get PatientClincialElements of an encounter by GWID and its value
	 * 
	 * @param encounterId
	 * @param gwid
	 * @param value
	 * 
	 */
	public static Specification<PatientClinicalElements> getPatClinEleByGWIDAndValue(final Integer encounterId,final String gwid,final String value){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate encPredicate=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
				Predicate gwidPredicate=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsGwid),gwid);
				Predicate valuePredicate=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsValue),value);
				return cb.and(encPredicate,gwidPredicate,valuePredicate);
			}
		};
	}		

	


	/**
	 * 
	 * Get PatientClincialElements of a patient for an encounter by GWID 
	 * 
	 * @param encounterId
	 * @param gwid
	 * 
	 * 
	 */
	public static Specification<PatientClinicalElements> getPatClinEleByGWID(final Integer encounterId,final Integer patientId,final String gwid){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate patPredicate=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
				Predicate encPredicate=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
				Predicate gwidPredicate=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsGwid),gwid);
				return cb.and(patPredicate,encPredicate,gwidPredicate);
			}
		};
	}	
	
	
	/**
	 * 
	 * Get PatientClincialElements of a patient for an encounter by GWID pattern
	 * 
	 * @param encounterId
	 * @param gwid pattern Eg : %0002
	 * 
	 * 
	 */
	public static Specification<PatientClinicalElements> getPatClinEleByGWIDPattern(final Integer encounterId,final Integer patientId,final String gwid){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate patPredicate=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
				Predicate encPredicate=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
				Predicate gwidPatternPred=cb.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid),gwid);
				return cb.and(patPredicate,encPredicate,gwidPatternPred);
			}
		};
	}		



	/**
	 * 
	 * Get Patient Clinical History Elements of a patient by GWID and its value
	 * 
	 * @param encounterId
	 * @param gwid
	 * @param value
	 * 
	 */
	public static Specification<PatientClinicalHistory> getPatClinHistoryEleByGWIDAndValue(final Integer patientId,final String gwid,final String value){

		return new Specification<PatientClinicalHistory>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalHistory> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate patPredicate=cb.equal(root.get(PatientClinicalHistory_.patientClinicalHistoryPatientid),patientId);
				Predicate gwidPredicate=cb.equal(root.get(PatientClinicalHistory_.patientClinicalHistoryGwid),gwid);
				Predicate valuePredicate=cb.equal(root.get(PatientClinicalHistory_.patientClinicalHistoryValue),value);
				return cb.and(patPredicate,gwidPredicate,valuePredicate);
			}
		};
	}		


	/**
	 * 
	 * Get Patient Clinical History Elements of a patient by GWID 
	 * 
	 * @param encounterId
	 * @param gwid
	 * 
	 * 
	 */
	public static Specification<PatientClinicalHistory> getPatClinHistoryEleByGWID(final Integer patientId,final String gwid){

		return new Specification<PatientClinicalHistory>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalHistory> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate patPredicate=cb.equal(root.get(PatientClinicalHistory_.patientClinicalHistoryPatientid),patientId);
				Predicate gwidPredicate=cb.equal(root.get(PatientClinicalHistory_.patientClinicalHistoryGwid),gwid);
				return cb.and(patPredicate,gwidPredicate);
			}
		};
	}		



	/**
	 * 
	 * Get Patient Episode Elements of a patient by episode and  GWID and its value
	 * 
	 * @param encounterId
	 * @param gwid
	 * @param value
	 * 
	 */
	public static Specification<PatientEpisodeAttributeDetails> getPatEpisodeEleByGWIDAndValue(final Integer episodeId,final String gwid,final String value){

		return new Specification<PatientEpisodeAttributeDetails>(){

			@Override
			public Predicate toPredicate(Root<PatientEpisodeAttributeDetails> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate epiPredicate=cb.equal(root.get(PatientEpisodeAttributeDetails_.patientEpisodeAttributeDetailsEpisodeid),episodeId);
				Predicate gwidPredicate=cb.equal(root.get(PatientEpisodeAttributeDetails_.patientEpisodeAttributeDetailsGwid),gwid);
				Predicate valuePredicate=cb.equal(root.get(PatientEpisodeAttributeDetails_.patientEpisodeAttributeDetailsValue),value);
				return cb.and(epiPredicate,gwidPredicate,valuePredicate);
			}
		};
	}		

	/**
	 * 
	 * Get Patient Episode Elements of a patient by episode and  GWID 
	 * 
	 * @param encounterId
	 * @param gwid
	 * 
	 */
	public static Specification<PatientEpisodeAttributeDetails> getPatEpisodeEleByGWID(final Integer episodeId,final String gwid){

		return new Specification<PatientEpisodeAttributeDetails>(){

			@Override
			public Predicate toPredicate(Root<PatientEpisodeAttributeDetails> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate epiPredicate=cb.equal(root.get(PatientEpisodeAttributeDetails_.patientEpisodeAttributeDetailsEpisodeid),episodeId);
				Predicate gwidPredicate=cb.equal(root.get(PatientEpisodeAttributeDetails_.patientEpisodeAttributeDetailsGwid),gwid);
				return cb.and(epiPredicate,gwidPredicate);
			}
		};
	}		

	
	/**
	 * 
	 * Get Patient Clinical options elements of a patient by GWID by patientId and encounterId
	 * 
	 * @param encounterId
	 * @param gwid
	 * @param value
	 * 
	 */
	public static Specification<ClinicalElementsOptions> getPatClinicalOptionsData(final Integer patientId,final Integer encounterId,final List<String> gwid){
		return new Specification<ClinicalElementsOptions>(){
			@Override
			public Predicate toPredicate(Root<ClinicalElementsOptions> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<ClinicalElementsOptions,ClinicalElements> optionJoin=root.join(ClinicalElementsOptions_.clinicalElement,JoinType.INNER);
				Join<ClinicalElements,PatientClinicalElements> paramJoin=optionJoin.join(ClinicalElements_.patientClinicalElements,JoinType.INNER);
				Predicate valuePred=(cb.equal(paramJoin.get(PatientClinicalElements_.patientClinicalElementsValue),root.get(ClinicalElementsOptions_.clinicalElementsOptionsValue)));
				Predicate gwidPred=paramJoin.get(PatientClinicalElements_.patientClinicalElementsGwid).in(gwid);
				Predicate patPred=cb.equal(paramJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
				Predicate encPred=cb.equal(paramJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
				return cb.and(valuePred,gwidPred,patPred,encPred);
			}
		};
	}		
	
	
	/**
	 * Get patient hx  data for given patient  
	 * 
	 * @param PatientId
	 * 
	 */
	public static Specification<PatientClinicalHistory> getPatientHxData(final Integer patientId){

		return new Specification<PatientClinicalHistory>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalHistory> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalHistory,ClinicalElements> paramJoin=root.join(PatientClinicalHistory_.clinicalElement,JoinType.INNER);
				Predicate encounterPred=cb.equal(root.get(PatientClinicalHistory_.patientClinicalHistoryPatientid),patientId);
				return cb.and(encounterPred);
			}
		};
	}

	public static Specification<ClinicalElements> getClinicalElement(final String gwid){
		return new Specification<ClinicalElements>(){
			@Override
			public Predicate toPredicate(Root<ClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate gwPred=cb.equal(root.get(ClinicalElements_.clinicalElementsGwid),gwid);
				return gwPred;
			}
		};
	}
	
	/**
	 * Get PatientClinicalData for patientId and encounterId and given GWID Pattern with clientId
	 * 
	 * @param patientId
	 * @param encounterId
	 * @param gwids
	 * 
	 */
	public static Specification<PatientClinicalElements> getPatClinicalDataByGWIDPattern(final String clientId,final Integer patientId,final Integer encounterId,final String gwidPattern){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalElements,ClinicalElements> paramJoin=root.join(PatientClinicalElements_.clinicalElement,JoinType.INNER);
				Predicate encounterPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
				Predicate patientPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
				
				Predicate gwidPred=cb.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid),"000"+gwidPattern);
				Predicate clientIdPred=cb.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid),clientId+gwidPattern);
				Predicate elementPred=cb.or(gwidPred,clientIdPred);
				
				return cb.and(patientPred,elementPred,encounterPred);

			}

		};
	}
	
	/**
	 *
	 *  Get PatientClinicalElements data for given GWID and it is not a history element
	 * @param patientId
	 * @param encounterId
	 * @param gwids
	 * 
	 */
	public static Specification<PatientClinicalElements> getNonHistoryElemPatientData(final String clientId,final Integer patientId,final Integer encounterId,final String gwidPattern){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalElements,ClinicalElements> paramJoin=root.join(PatientClinicalElements_.clinicalElement,JoinType.INNER);
				Predicate encounterPred=null;
				if(encounterId!=-1)
					encounterPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
				Predicate patientPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
				Predicate gwidPred=cb.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid),"000"+gwidPattern);
				Predicate clientIdPred=cb.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid),clientId+gwidPattern);
				Predicate elementPred=cb.or(gwidPred,clientIdPred);
				Predicate isHistoryPred=cb.equal(paramJoin.get(ClinicalElements_.clinicalElementsIshistory),false);
				Predicate finalPred=null;
				if(encounterId!=-1)
					finalPred= cb.and(encounterPred,patientPred,elementPred,isHistoryPred);
				else
					finalPred= cb.and(patientPred,elementPred,isHistoryPred);

				return finalPred;

			}

		};
	}
	
	/**
	 * GET Patient clinical Data for vitals pre-load From LastVisit 
	 * @param patientId
	 * @param prevEncId
	 * @param gwids
	 * 
	 */
	public static Specification<PatientClinicalElements> getPrevEncounterPatVitalData(final String clientId,final Integer patientId,final Integer prevEncId,final String gwidPattern){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalElements,VitalsParameter> paramJoin=root.join(PatientClinicalElements_.vitalsParameter,JoinType.INNER);
				Join<PatientClinicalElements,ClinicalElements> clinicalJoin=root.join(PatientClinicalElements_.clinicalElement,JoinType.INNER);
				
				Predicate gwidPred=cb.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid),"000"+gwidPattern);
				Predicate clientIdPred=cb.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid),clientId+gwidPattern);
				Predicate elementPred=cb.or(gwidPred,clientIdPred);
				
				Predicate encounterPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),prevEncId);
				Predicate patientPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
				Predicate preLoadPred=cb.equal(paramJoin.get(VitalsParameter_.vitalsParameterPreloadFromLastVisit),true);
				Predicate isHistoryPred=cb.equal(clinicalJoin.get(ClinicalElements_.clinicalElementsIshistory),false);
				return cb.and(encounterPred,patientPred,elementPred,isHistoryPred,preLoadPred);
			}
		};
	}	

	/**
	 * 
	 * GET patientClinicalHistory for GWID pattern with clientId
	 * 
	 * @param patientId
	 * @param gwids
	 * 
	 */
	public static Specification<PatientClinicalHistory> getHistoryElemPatientData(final String clientId,final Integer patientId,final String gwidPattern){

		return new Specification<PatientClinicalHistory>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalHistory> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalHistory,ClinicalElements> paramJoin=root.join(PatientClinicalHistory_.clinicalElement,JoinType.INNER);
				Predicate gwidPred=cb.like(root.get(PatientClinicalHistory_.patientClinicalHistoryGwid),"000"+gwidPattern);
				Predicate clientIdPred=cb.like(root.get(PatientClinicalHistory_.patientClinicalHistoryGwid),clientId+gwidPattern);
				Predicate elementPred=cb.or(gwidPred,clientIdPred);
				Predicate patientPred=cb.equal(root.get(PatientClinicalHistory_.patientClinicalHistoryPatientid),patientId);
				Predicate isHistoryPred=cb.equal(paramJoin.get(ClinicalElements_.clinicalElementsIshistory),true);
				return cb.and(elementPred,patientPred,isHistoryPred);
			}

		};
	}
	
	/**
	 * Get PatientClinicalData for patientId for given GWIDS with encounter data's order by encounter date
	 * @param patientId
	 * @param encounterId
	 * @param gwids
	 * 
	 */
	public static Specification<PatientClinicalElements> getPatClinicalDataByGWDID(final Integer patientId,final List<String> gwids){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				root.fetch(PatientClinicalElements_.encounter,JoinType.INNER);
				Join<PatientClinicalElements,Encounter> paramJoin=root.join(PatientClinicalElements_.encounter,JoinType.INNER);
				Predicate patientPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
				Predicate elementPred=root.get(PatientClinicalElements_.patientClinicalElementsGwid).in(gwids);
				query.orderBy(cb.desc(paramJoin.get(Encounter_.encounterDate)));
				return cb.and(patientPred,elementPred);
			}

		};
	}
	
	/**
	 *
	 *  Get PatientClinicalElements data for given GWID and it is not a history element and its less than or equal to that encounter
	 * @param patientId
	 * @param encounterId
	 * @param gwids
	 * 
	 */

	public static Specification<PatientClinicalElements> getElemPatientDataLessThanEncounter(final Integer patientId,final Integer encounterId,final List<String> gwids){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalElements,ClinicalElements> paramJoin=root.join(PatientClinicalElements_.clinicalElement,JoinType.INNER);
				Predicate encounterPred=null;
				if(encounterId!=-1)
					encounterPred=cb.lessThanOrEqualTo(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
				Predicate patientPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
				Predicate elementPred=root.get(PatientClinicalElements_.patientClinicalElementsGwid).in(gwids);
				Predicate finalPred=null;
				if(encounterId!=-1)
					finalPred= cb.and(encounterPred,patientPred,elementPred);
				else
					finalPred= cb.and(patientPred,elementPred);
				return finalPred;

			}

		};
	}

	/**
	 * Get PatientClinicalElements data for given HPI symptomId and encounterId
	 * @param symptomId
	 * @param encounterId
	 * @return
	 */
	public static Specification<PatientClinicalElements> getByHpiSymptomidEncId(final String symptomId, final Integer encounterId) {
		
		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				//Join<PatientClinicalElements, HpiSymptom> file=root.join(PatientClinicalElements_.hpiSymptom,JoinType.INNER);
				//Predicate symptomGwidPred=(cb.equal(file.get(HpiSymptom_.hpiSymptomId),symptomId));
				Predicate encPred=root.get(PatientClinicalElements_.patientClinicalElementsEncounterid).in(encounterId);
				query.where(/*cb.and(symptomGwidPred,*/encPred);
				return query.getRestriction();
			}
		};
	}

	/**
	 * Get PatientClinicalElements data for given patientId,encounterId and gwid's
	 * @return
	 */
	public static Specification<PatientClinicalElements> getByPatEncGwId(final Integer patientId, final Integer encounterId, final String collectBufferData2) {
		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String split[] = collectBufferData2.split(",");
				List<String> data = new ArrayList<String>();
				for(int i=0;i<split.length;i++)
				{
					data.add(split[i]);
				}
				query.where(cb.and(cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId),
									cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId),
									root.get(PatientClinicalElements_.patientClinicalElementsGwid).in(data)));
				return query.getRestriction();
			}
			
		};
	}
	
	/**
	 *
	 *  Get PatientClinicalElements data for given GWID and it is not a history element and its less than or equal to that encounter
	 * @param patientId
	 * @param encounterId
	 * @param gwids
	 * 
	 */

	public static Specification<PatientClinicalElements> getClinicalElementByEncId(final Integer patientId,final Integer encounterId,final List<String> gwids){

		return new Specification<PatientClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate ptClinicalElem=cb.and(
						cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId),
						cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId),
						root.get(PatientClinicalElements_.patientClinicalElementsGwid).in(gwids));
				return ptClinicalElem;

			}

		};
	}
}