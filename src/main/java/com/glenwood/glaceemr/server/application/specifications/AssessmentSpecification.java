package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;


import com.glenwood.glaceemr.server.application.models.PatientAssessments;
import com.glenwood.glaceemr.server.application.models.PatientAssessments_;
import com.glenwood.glaceemr.server.application.models.Icdm;
import com.glenwood.glaceemr.server.application.models.Icdm_;


public class AssessmentSpecification {

	/**
	 * Fetching current visit data by encounter id
	 * @param encounterId
	 * @return
	 */
	public static Specification<PatientAssessments> DxByEncounterId(final Integer encounterId)	{
		return new Specification<PatientAssessments>() {

			@Override
			public Predicate toPredicate(Root<PatientAssessments> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				
				Predicate DxByEncounterId = cb.equal(root.get(PatientAssessments_.patient_assessments_encounterid), encounterId);
				return DxByEncounterId;
			}
		};
	}
	
	/**
	 * Fetching current visit data by patientid
	 * @param patientId
	 * @return
	 */
	public static Specification<PatientAssessments> DxByPatientId(final Integer patientId)	{
		return new Specification<PatientAssessments>() {

			@Override
			public Predicate toPredicate(Root<PatientAssessments> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				
				Predicate DxByPatientId = cb.equal(root.get(PatientAssessments_.patient_assessments_patientId), patientId);
				return DxByPatientId;
			}
		};
	}
	
	/*
	 * Fetching information related to particular code
	 */
	public static Specification<PatientAssessments> DxByCode(final String dxCode)	{
		return new Specification<PatientAssessments>() {

			@Override
			public Predicate toPredicate(Root<PatientAssessments> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				
				Predicate DxByPatientId = cb.like(root.get(PatientAssessments_.patient_assessments_dxcode), dxCode);
				return DxByPatientId;
			}
		};
	}
	
	/**
	 * Dx search criteria1
	 * @param keyword
	 * @return
	 */
	public static Specification<Icdm> DxByCriteria1(final String keyword) {
		return new Specification<Icdm>() {

			@Override
			public Predicate toPredicate(Root<Icdm> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate DxByCriterea = cb.like(root.get(Icdm_.icdmDescription),keyword.toUpperCase()+"%");
					
				return DxByCriterea;
			}
		};
	}
	
	/**
	 * Dx search criteria2
	 * @param keyword
	 * @return
	 */
	public static Specification<Icdm> DxByCriteria2(final String keyword) {
		return new Specification<Icdm>() {

			@Override
			public Predicate toPredicate(Root<Icdm> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate search1 = cb.like(root.get(Icdm_.icdmDescription),"% "+keyword.toUpperCase()+"%");
				Predicate search2 = cb.notLike(root.get(Icdm_.icdmDescription),keyword.toUpperCase()+"%");
				Predicate DxByCriterea = cb.and(search1, search2);
		
				return DxByCriterea;
			}
		};
	}
	
	
	/**
	 * Dx search criteria3
	 * @param keyword
	 * @return
	 */
	public static Specification<Icdm> DxByCriteria3(final String keyword)	{
		return new Specification<Icdm>() {

			@Override
			public Predicate toPredicate(Root<Icdm> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate search1 = cb.like(root.get(Icdm_.icdmDescription),"%"+keyword.toUpperCase()+"%");
				Predicate search2 = cb.notLike(root.get(Icdm_.icdmDescription),keyword.toUpperCase()+"%");
				Predicate search3 = cb.notLike(root.get(Icdm_.icdmDescription),"% "+keyword.toUpperCase()+"%");
				Predicate DxByCriterea = cb.and(search1, search2, search3);
				return DxByCriterea;
			}
		};
	}
	
	/**
	 * Dx search criteria4
	 * @param keyword
	 * @return
	 */
	public static Specification<Icdm> DxByCriteria4(final String keyword)	{
		return new Specification<Icdm>() {

			@Override
			public Predicate toPredicate(Root<Icdm> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate search1 = cb.like(root.get(Icdm_.icdmShortCutDesc),"%"+keyword.toUpperCase()+"%");
				Predicate search2 = cb.notLike(root.get(Icdm_.icdmDescription),"%"+keyword.toUpperCase()+"%");
				Predicate DxByCriterea = cb.and(search1, search2);
				return DxByCriterea;
			}
		};
	}


	/**
	 * Criteria to fetch edit page data
	 * @param patientid
	 * @param encounterid
	 * @param dxCode
	 * @param problemid
	 * @return
	 */
	public static Specification<PatientAssessments> getDataToEdit(final int patientid,final int encounterid,final String dxCode,final int problemid) {
		return new Specification<PatientAssessments>() {

			@Override
			public Predicate toPredicate(Root<PatientAssessments> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {	
				
				Predicate predicate=cb.equal(root.get(PatientAssessments_.patient_assessments_patientId), patientid);
				Predicate predicate1=cb.equal(root.get(PatientAssessments_.patient_assessments_encounterid), encounterid);
				Predicate predicate2=cb.equal(root.get(PatientAssessments_.patient_assessments_id), problemid);
				Predicate predicate3=cb.like(root.get(PatientAssessments_.patient_assessments_dxcode), dxCode);
				Predicate result;
				if(problemid!=-1){
					 result=cb.and(predicate,predicate1,predicate2);
				}else{
					 result=cb.and(predicate,predicate1,predicate3);
				}
				
				return result;

			}
		};

	}
	
	/**
	 * Ordering dx
	 * @param keyword
	 * @return
	 */
	public static Specification<PatientAssessments> getOrder()	{
		return new Specification<PatientAssessments>() {

			@Override
			public Predicate toPredicate(Root<PatientAssessments> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				query.orderBy(cb.asc(root.get(PatientAssessments_.patient_assessments_dxorder)));
				return query.getRestriction();
			}
		};
	}
}
