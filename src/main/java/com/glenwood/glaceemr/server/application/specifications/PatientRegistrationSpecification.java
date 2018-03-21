package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.PatientPortalUser;
import com.glenwood.glaceemr.server.application.models.PatientPortalUser_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;

public class PatientRegistrationSpecification {
	
	
	
	/**
	 * @param lastName	: used to search the patients 
	 * @return BooleanExpression is a  predicate  
	 */
	public static Specification<PatientRegistration> PatientByLastName(final String lastName)
	{
		return new Specification<PatientRegistration>() {
			
			@Override
			public Predicate toPredicate(Root<PatientRegistration> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate PatientByLastName = cb.like(cb.upper(root.get(PatientRegistration_.patientRegistrationLastName)),lastName.toUpperCase()+"%");
				return PatientByLastName;
			}
		};
	}
	
	
	/**
	 * @param lastName	: used to search the patients 
	 * @return BooleanExpression is a  predicate  
	 */
	public static Specification<PatientRegistration> PatientIsactive(final boolean isActive)
	{
		return new Specification<PatientRegistration>() {
			
			@Override
			public Predicate toPredicate(Root<PatientRegistration> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate PatientIsActive = cb.equal(root.get(PatientRegistration_.patientRegistrationActive),isActive);
				return PatientIsActive;
			}
		};
	}
	
	
	/**
	 * @param firstName	: used to search the patients 
	 * @return BooleanExpression is a  predicate  
	 */
	public static Specification<PatientRegistration> PatientByFirstName(final String firstName)
	{
		return new Specification<PatientRegistration>() {
			
			@Override
			public Predicate toPredicate(Root<PatientRegistration> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate PatientByFirstName = cb.like(cb.upper(root.get(PatientRegistration_.patientRegistrationFirstName)),firstName.toUpperCase()+"%");
				return PatientByFirstName;
			}
		};
	}
	
	
	/**
	 * @param accountNumber	: used to search the patients 
	 * @return BooleanExpression is a  predicate  
	 */
	public static Specification<PatientRegistration> PatientByAccountNumber(final String accountNumber)
	{
		return new Specification<PatientRegistration>() {
			
			@Override
			public Predicate toPredicate(Root<PatientRegistration> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate PatientByAccountNumber = cb.like(cb.upper(root.get(PatientRegistration_.patientRegistrationAccountno)),accountNumber.toUpperCase()+"%");
				return PatientByAccountNumber;
			}
		};
	}
	
	/**
	 * Get patients by patient id
	 * @param patient id
	 * @return String is a predicate  
	 */
	public static Specification<PatientRegistration> PatientId(final String patientId)
	{
		return new Specification<PatientRegistration>() {
			
			@Override
			public Predicate toPredicate(Root<PatientRegistration> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate PatientId = cb.equal(root.get(PatientRegistration_.patientRegistrationId), patientId);
				return PatientId;
			}
		};
	}
	
	
	/**
	 * @param username	: used to get patient details of a patient of that username.
	 * @return list of patient details.
	 */	
	public static Specification<PatientPortalUser> getPatientDetailsByUsername(final String username) {

		   return new Specification<PatientPortalUser>() {

			@Override
			public Predicate toPredicate(Root<PatientPortalUser> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
								
			    Predicate personalDetailsPredicate=cb.equal(cb.upper(root.get(PatientPortalUser_.patient_portal_user_name)),username.toUpperCase());
				
			    root.fetch(PatientPortalUser_.chartpatient_portal_userTable,JoinType.INNER).fetch(Chart_.patientRegistrationTable);
			    
			    cq.where(cb.and(personalDetailsPredicate));
				return cq.getRestriction();
			}
			   
		};
	   
	}
	
	/**
	 * @param patientId	: used to get patient details of a patient of that particular id
	 * @return BooleanExpression is a  predicate  
	 */	
   public static Specification<PatientRegistration> getPatientPersonalDetails(final int patientId)
   {
	   return new Specification<PatientRegistration>() {

		@Override
		public Predicate toPredicate(Root<PatientRegistration> root,
				CriteriaQuery<?> cq, CriteriaBuilder cb) {
			
			root.fetch(PatientRegistration_.guaranatorDetails, JoinType.LEFT);
		    Predicate personalDetailsPredicate=cb.equal(root.get(PatientRegistration_.patientRegistrationId),patientId);
			
		    cq.where(cb.and(personalDetailsPredicate));
			return cq.getRestriction();
		}
		   
	};
   }
	
	


   public static Specification<PatientRegistration> byPatientId(final int patientId)
   {
	   return new Specification<PatientRegistration>() {

		   @Override
		   public Predicate toPredicate(Root<PatientRegistration> root,
				   CriteriaQuery<?> cq, CriteriaBuilder cb) {

			   root.fetch(PatientRegistration_.empProfile, JoinType.LEFT);
			   Predicate pred=cb.equal(root.get(PatientRegistration_.patientRegistrationId),patientId);
			   return pred;
		   }

	   };
   }

}
