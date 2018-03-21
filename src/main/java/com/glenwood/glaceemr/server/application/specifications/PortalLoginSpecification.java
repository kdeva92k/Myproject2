package com.glenwood.glaceemr.server.application.specifications;

import java.sql.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.PrimarykeyGenerator;
import com.glenwood.glaceemr.server.application.models.PrimarykeyGenerator_;
import com.glenwood.glaceemr.server.application.models.PatientPortalUser;
import com.glenwood.glaceemr.server.application.models.PatientPortalUser_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PortalUser;
import com.glenwood.glaceemr.server.application.models.PortalUser_;

public class PortalLoginSpecification {
	
	/**
	 * @param patientId	: used to get patient details of a patient of that particular id
	 * @return BooleanExpression is a  predicate  
	 */	
   public static Specification<PortalUser> patientByUsername(final String username)
   {
	   return new Specification<PortalUser>() {

		@Override
		public Predicate toPredicate(Root<PortalUser> root,
				CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
			Predicate usernamePredicate=cb.and(cb.equal(cb.upper(root.get(PortalUser_.username)), username.toUpperCase()));
			return usernamePredicate;		
		}
		   
	};
   }
   
   public static Specification<PatientPortalUser> patientById(final String patientId)
   {
	   return new Specification<PatientPortalUser>() {

		@Override
		public Predicate toPredicate(Root<PatientPortalUser> root,
				CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
			Predicate usernamePredicate=cb.and(cb.equal(root.get(PatientPortalUser_.patient_portal_user_patient_id), Integer.parseInt(patientId)));
			return usernamePredicate;		
		}
		   
	};
   }
   
   public static Specification<PatientPortalUser> activePatientById(final int patientId)
   {
	   return new Specification<PatientPortalUser>() {

		@Override
		public Predicate toPredicate(Root<PatientPortalUser> root,
				CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
			Predicate usernamePredicate=cb.and(cb.equal(root.get(PatientPortalUser_.patient_portal_user_patient_id),patientId),cb.equal(root.get(PatientPortalUser_.patient_portal_user_portal_account_verified), 1));
			return usernamePredicate;		
		}
		   
	};
   }
   
   public static Specification<PatientPortalUser> inactivePatientById(final int patientId)
   {
	   return new Specification<PatientPortalUser>() {

		@Override
		public Predicate toPredicate(Root<PatientPortalUser> root,
				CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
			Predicate usernamePredicate=cb.and(cb.equal(root.get(PatientPortalUser_.patient_portal_user_patient_id),patientId),cb.equal(root.get(PatientPortalUser_.patient_portal_user_portal_account_verified), 0));
			return usernamePredicate;		
		}
		   
	};
   }
   
   public static Specification<PatientPortalUser> getAllPatientsByUsername(final String username)
   {
	   return new Specification<PatientPortalUser>() {

		@Override
		public Predicate toPredicate(Root<PatientPortalUser> root,
				CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
			Predicate usernamePredicate=cb.and(cb.equal(cb.upper(root.get(PatientPortalUser_.patient_portal_user_name)), username.toUpperCase()));
			return usernamePredicate;		
		}
		   
	};
   }
   
   public static Specification<PatientRegistration> getPatientsList(final Date dob, final String firstName, final String lastName)
   {
	   return new Specification<PatientRegistration>() {

		@Override
		public Predicate toPredicate(Root<PatientRegistration> root,
				CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
			Predicate usernamePredicate=cb.and(cb.equal(cb.upper(root.get(PatientRegistration_.patientRegistrationFirstName)), firstName.toUpperCase()),
					cb.equal(cb.upper(root.get(PatientRegistration_.patientRegistrationLastName)), lastName.toUpperCase()),
					cb.equal(root.get(PatientRegistration_.patientRegistrationDob), dob));
			return usernamePredicate;		
		}
		   
	};
   }
   
   public static Specification<PatientRegistration> getPatientsList(final Date dob, final String firstName, final String lastName, final String email)
   {
	   return new Specification<PatientRegistration>() {

		@Override
		public Predicate toPredicate(Root<PatientRegistration> root,
				CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
			Predicate usernamePredicate=cb.and(cb.equal(cb.upper(root.get(PatientRegistration_.patientRegistrationFirstName)), firstName.toUpperCase()),
					cb.equal(cb.upper(root.get(PatientRegistration_.patientRegistrationLastName)), lastName.toUpperCase()),
					cb.equal(root.get(PatientRegistration_.patientRegistrationDob), dob),
					cb.equal(root.get(PatientRegistration_.patientRegistrationMailId), email));
			return usernamePredicate;		
		}
		   
	};
   }
   
   public static Specification<Chart> chartByPatientId(final int patientId)
   {
	   return new Specification<Chart>() {

		@Override
		public Predicate toPredicate(Root<Chart> root,
				CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
			Predicate chartPredicate=cb.and(cb.equal(root.get(Chart_.chartPatientid), patientId));
			return chartPredicate;		
		}
		   
	};
   }
   
   
   public static Specification<PrimarykeyGenerator> h213ByCategoryName(final String category)
   {
	   return new Specification<PrimarykeyGenerator>() {

		@Override
		public Predicate toPredicate(Root<PrimarykeyGenerator> root,
				CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
			Predicate chartPredicate=cb.and(cb.equal(root.get(PrimarykeyGenerator_.primarykey_generator_tablename), category));
			return chartPredicate;		
		}
		   
	};
   }


}
