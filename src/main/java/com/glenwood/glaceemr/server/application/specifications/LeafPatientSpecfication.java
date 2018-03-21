package com.glenwood.glaceemr.server.application.specifications;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.LeafLibrary;
import com.glenwood.glaceemr.server.application.models.LeafLibrary_;
import com.glenwood.glaceemr.server.application.models.LeafPatient;
import com.glenwood.glaceemr.server.application.models.LeafPatient_;

public class LeafPatientSpecfication {


	/**
	 * Get template details
	 * @param patientId         patient id
	 * @param chartId           chart id
	 * @param ids				encounter list for the patient
	 * @return
	 */
	public static Specification<LeafPatient> getLeafDetails(final Integer patientId, final Integer chartId,final List<Integer> ids)
	{
		return new Specification<LeafPatient>() {

			@Override
			public Predicate toPredicate(Root<LeafPatient> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {

				Join<LeafPatient,LeafLibrary> patRegJoin= root.join("leafLibraryTable",JoinType.INNER);
				Join<LeafPatient,Encounter> encounterJoin= root.join("encounterTable",JoinType.INNER);

				Predicate isactive = cb.equal(root.get(LeafPatient_.leafPatientIsactive),true);
				Predicate isreportNeededPred = cb.equal(patRegJoin.get(LeafLibrary_.leafLibraryIsreportNeeded),true);
				Predicate typePred = patRegJoin.get(LeafLibrary_.leafLibraryType).in(1,2);
				Predicate patentIdPred = cb.equal(root.get(LeafPatient_.leafPatientPatientId), patientId);
				Predicate encIdsPred;
				if(ids.size()>0)
					encIdsPred= encounterJoin.get(Encounter_.encounterId).in(ids);
				else
					encIdsPred= encounterJoin.get(Encounter_.encounterId).in(-999);
				query.where(isactive,typePred,isreportNeededPred,patentIdPred,encIdsPred)
				.orderBy(cb.desc(encounterJoin.get(Encounter_.encounterDate)));
				return query.getRestriction();
			}
		};
	}

	/**
	 * Get saved template details
	 * @param patientId					patient id
	 * @param chartId				    chart id
	 * @param leafLibraryIdList			list of leaf library id's
	 * @param leafPatientIdList			list of leaf patient id's
	 * @param leafencounterIdList		list of encounter id's
	 * @return
	 */
	public static Specification<LeafPatient> getSavedLeafDetails(final Integer patientId, final Integer chartId,final List<Integer> leafLibraryIdList, final List<Integer> leafPatientIdList, final List<Integer> leafencounterIdList)
	{
		return new Specification<LeafPatient>() {

			@Override
			public Predicate toPredicate(Root<LeafPatient> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {

				Join<LeafPatient,LeafLibrary> patRegJoin= root.join("leafLibraryTable",JoinType.INNER);
				Join<LeafPatient,Encounter> encounterJoin= root.join("encounterTable",JoinType.INNER);

				Predicate isactive = cb.equal(root.get(LeafPatient_.leafPatientIsactive),true);
				Predicate leafLibPred;
				if(leafLibraryIdList.size()>0)
					leafLibPred = root.get(LeafPatient_.leafPatientLeafLibraryId).in(leafLibraryIdList);
				else
					leafLibPred = root.get(LeafPatient_.leafPatientLeafLibraryId).in(-999);
				Predicate leafencPred;
				if(leafencounterIdList.size()>0)
					leafencPred = encounterJoin.get(Encounter_.encounterId).in(leafencounterIdList);
				else
					leafencPred = encounterJoin.get(Encounter_.encounterId).in(-999);
				Predicate leafpatPred;
				if(leafPatientIdList.size()>0)
					leafpatPred = root.get(LeafPatient_.leafPatientId).in(leafPatientIdList);
				else
					leafpatPred = root.get(LeafPatient_.leafPatientId).in(-999);
				Predicate leafpatidPred = cb.equal(root.get(LeafPatient_.leafPatientPatientId),patientId);

				query.where(isactive,leafLibPred,leafencPred,leafpatPred,leafpatidPred)
				.orderBy(cb.desc(encounterJoin.get(Encounter_.encounterDate)));
				return query.getRestriction();
			}
		};
	}


	/**
	 * Get patient leaf details for given encounter and leafId
	 * 
	 * @param leafId
	 * @param encounterId
	 * @return
	 */
	public static Specification<LeafPatient> getPatLeafByLeafIdAndEncId(final Integer leafId,final Integer encounterId){

		return new Specification<LeafPatient>(){

			@Override
			public Predicate toPredicate(Root<LeafPatient> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate leafIdPred=cb.equal(root.get(LeafPatient_.leafPatientLeafLibraryId),leafId);
				Predicate encounterPred=cb.equal(root.get(LeafPatient_.leafPatientEncounterId),encounterId);
				return cb.and(leafIdPred,encounterPred);
			}

		};
	}

	
	
	/**
	 * Get max leaf_patient_id
	 * 
	 * @param leafId
	 * @param encounterId
	 * @return
	 */
	public static Specification<LeafPatient> getLeafPatientOrderById(){

		return new Specification<LeafPatient>(){

			@Override
			public Predicate toPredicate(Root<LeafPatient> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.orderBy(cb.desc(root.get(LeafPatient_.leafPatientId)));
				return query.getRestriction();
			}

		};
	}




}
