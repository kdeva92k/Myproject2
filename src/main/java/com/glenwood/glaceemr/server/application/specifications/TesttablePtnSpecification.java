package com.glenwood.glaceemr.server.application.specifications;


import java.sql.Timestamp;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.TesttableIns;
import com.glenwood.glaceemr.server.application.models.TesttableIns_;
import com.glenwood.glaceemr.server.application.models.TesttablePntIns;
import com.glenwood.glaceemr.server.application.models.TesttablePntIns_;
import com.glenwood.glaceemr.server.application.models.TesttablePtn;
import com.glenwood.glaceemr.server.application.models.TesttablePtn_;


@Component
@Transactional 
public class TesttablePtnSpecification {

	/**
	 * @param lastName	: used to search the patients 
	 * @return BooleanExpression is a  predicate  
	 */
	public static Specification<TesttablePtn> PatientByLastName(final String lastName)
	{
		return new Specification<TesttablePtn>() {
			
			@Override
			public Predicate toPredicate(Root<TesttablePtn> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate PatientByLastName = cb.like(cb.upper(root.get(TesttablePtn_.patientLName)), lastName.toUpperCase());
				return PatientByLastName;
			}
		};
	}
	
	
	
	/**
	 * @param lastName	: used to search the patients 
	 * @return BooleanExpression is a  predicate  
	 */
	public static Specification<TesttablePtn> PatientByFirstName(final String firstName)
	{
		return new Specification<TesttablePtn>() {
			
			@Override
			public Predicate toPredicate(Root<TesttablePtn> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate PatientByFirstName = cb.like(cb.upper(root.get(TesttablePtn_.patientFName)), firstName.toUpperCase());
				return PatientByFirstName;
			}
		};
	}
	

	/**
	 * @param dob	: used to search the patients Entity by given date of birth
	 * @return BooleanExpression is a predicate   
	 * 
	 * This predicate filter Patients data by dateofbirth  on Patient Entity 
	 */
	
	public static Specification<TesttablePtn> PatientByDob(final String dob)
	{
		return new Specification<TesttablePtn>() {
			
			@Override
			public Predicate toPredicate(Root<TesttablePtn> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Timestamp toTimeStamp = Timestamp.valueOf(dob+" 00:00:00");
				Predicate PatientByDob=cb.equal(root.get(TesttablePtn_.patientDob),toTimeStamp);
				return PatientByDob;
			}
		};
	}
	
	
	public static Specification<TesttablePtn> PatientByPatientId(final Integer PatietnId)
	{
		return new Specification<TesttablePtn>() {
			
			@Override
			public Predicate toPredicate(Root<TesttablePtn> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				
				Predicate PatientById=cb.equal(root.get(TesttablePtn_.patientId),PatietnId);
				return PatientById;
			}
		};
	}

	/**
	 * @param lastName		: used to search the patients Entity by given lastName
	 * @param insuranceName	: used to search the insurance Entity by given insurance
	 * @return BooleanBuilder is a predicate   
	 * 
	 * This predicate filter Patients data with lastName on patients Entity  and insuranceName on Insurance Entity
	 */
	
	public static Specification<TesttablePtn> PatientByInusranceName(final String insuranceName)
	{
		return new Specification<TesttablePtn>() {
			
			@Override
			public Predicate toPredicate(Root<TesttablePtn> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
//				Join<Patient,Encounter> encounter=root.join(Patient_.encounterTable);
				Join<TesttablePtn,TesttablePntIns> patientinsurance= root.join(TesttablePtn_.patientInsuranceTable);
				Join<TesttablePntIns,TesttableIns> insurance= patientinsurance.join(TesttablePntIns_.insuranceMasterTable);
				if (Long.class != query.getResultType()) {
//				root.fetch(Patient_.patientInsuranceTable).fetch(PatientInsurance_.insuranceMasterTable);
//				root.fetch(Patient_.encounterTable);
				}
				Predicate PatientByInsuranceName=cb.like(cb.upper(insurance.get(TesttableIns_.inuranceName)),insuranceName.toUpperCase());
				return PatientByInsuranceName;
			}
		};
	}
	
	
	
}
