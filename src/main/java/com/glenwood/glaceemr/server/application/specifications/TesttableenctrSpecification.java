package com.glenwood.glaceemr.server.application.specifications;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.TesttablePtn;
import com.glenwood.glaceemr.server.application.models.TesttablePtn_;
import com.glenwood.glaceemr.server.application.models.Testtableenctr;
import com.glenwood.glaceemr.server.application.models.Testtableenctr_;


public class TesttableenctrSpecification {
	
	/**
	 * @param isActive	: used to search the Encounter Entity by given isActive state
	 * @return BooleanExpression is a predicate   
	 * 
	 * This predicate filter data with isActive state on Encounter Entity 
	 */
		public static Specification<Testtableenctr> IsactiveEncounter() {
			return new Specification<Testtableenctr>() {
				
				@Override
				public Predicate toPredicate(Root<Testtableenctr> root, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					Predicate IsactiveEncounter=cb.isTrue(root.get(Testtableenctr_.isActive));
					return IsactiveEncounter;
				}
			};
		}
	/**
	 * @param dob	: used to search the Encounter Entity by given encounterDate field
	 * @return BooleanExpression is a predicate   
	 * 
	 * This predicate filter data by encounterDate field on Encounter Entity 
	 */
	
	public static Specification<Testtableenctr> EncounterByPatientId(final Integer patientId)
	{
		return new Specification<Testtableenctr>() {
			
			@Override
			public Predicate toPredicate(Root<Testtableenctr> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<Testtableenctr, TesttablePtn> patient=root.join(Testtableenctr_.patientTable);
				root.fetch(Testtableenctr_.patientTable);
				Predicate EncounterByPatientid=cb.equal(patient.get(TesttablePtn_.patientId), patientId);
				return EncounterByPatientid;
			}
		};
	}
	
	
	public static Specification<Testtableenctr> EncounterByPatientIds(final List<Integer> myList)
	{
		return new Specification<Testtableenctr>() {
			
			@Override
			public Predicate toPredicate(Root<Testtableenctr> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<Testtableenctr, TesttablePtn> patient=root.join(Testtableenctr_.patientTable);
				root.fetch(Testtableenctr_.patientTable);
				Expression<Integer> integ=patient.get(TesttablePtn_.patientId);
				Predicate EncounterByPatientid=integ.in(myList);
				return EncounterByPatientid;
			}
		};
	}
	
	
	

}
