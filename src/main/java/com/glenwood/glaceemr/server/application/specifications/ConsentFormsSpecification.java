package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.FileDetails;
import com.glenwood.glaceemr.server.application.models.FileDetails_;
import com.glenwood.glaceemr.server.application.models.FileName;
import com.glenwood.glaceemr.server.application.models.FileName_;
import com.glenwood.glaceemr.server.application.models.FormsTemplate;
import com.glenwood.glaceemr.server.application.models.FormsTemplate_;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsCategory;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsCategory_;

public class ConsentFormsSpecification {

	
	/**
	 * @return Portal Consent Forms List 
	 * @formType 0-unsigned forms
	 *           1-signed forms
	 *           others-all forms
	 */	
	public static Specification<FileDetails> getPatientConsentFormsList(final int patientId, final int chartId, final int formType)
	{
		return new Specification<FileDetails>() {

			@Override
			public Predicate toPredicate(Root<FileDetails> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {				
				
				Predicate consentFormsListPredicate;
				
				Join<FileDetails, PatientDocumentsCategory> fileDetailsCategoryJoin=root.join(FileDetails_.patientDocCategory);
				
				root.fetch(FileDetails_.patientDocCategory);
				root.fetch(FileDetails_.formsTemplate);
				
				Predicate signPredicate;
				if(formType==0)
					signPredicate=cb.equal(root.get(FileDetails_.filedetailsIssigned), false);
				else if(formType==1)
					signPredicate=cb.equal(root.get(FileDetails_.filedetailsIssigned), true);
				else {
					
					consentFormsListPredicate=cq.where(cb.equal(root.get(FileDetails_.filedetailsPatientid), patientId),
							cb.equal(fileDetailsCategoryJoin.get(PatientDocumentsCategory_.patientDocCategoryId), cb.literal(3))).getGroupRestriction();

					return consentFormsListPredicate;
				}
				
				consentFormsListPredicate=cq.where(signPredicate,
						cb.equal(root.get(FileDetails_.filedetailsPatientid), patientId),
						cb.equal(fileDetailsCategoryJoin.get(PatientDocumentsCategory_.patientDocCategoryId), cb.literal(3))).getGroupRestriction();

				return consentFormsListPredicate;
				
			}

		};
	}
	
	/**
	 * @return Portal Consent Forms Details 
	 */	
	public static Specification<FormsTemplate> getPatientConsentFormDetails(final int patientId, final int chartId, final int fileTemplateId)
	{
		return new Specification<FormsTemplate>() {

			@Override
			public Predicate toPredicate(Root<FormsTemplate> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {				
				
				Predicate consentFormsDetailsPredicate;
				
				consentFormsDetailsPredicate=cq.where(cb.equal(root.get(FormsTemplate_.formsTemplateId), fileTemplateId)).getGroupRestriction();

				return consentFormsDetailsPredicate;
				
			}

		};
	}
	
	/**
	 * @return Portal Consent Forms File Details 
	 */	
	public static Specification<FileDetails> getPatientConsentFormFileDetails(final int patientId, final int chartId, final int fileDetailId)
	{
		return new Specification<FileDetails>() {

			@Override
			public Predicate toPredicate(Root<FileDetails> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {				
				
				Predicate consentFormsDetailsPredicate;
				
				root.fetch(FileDetails_.fileName);
				root.fetch(FileDetails_.formsTemplate);
				
				consentFormsDetailsPredicate=cq.where(cb.equal(root.get(FileDetails_.filedetailsId), fileDetailId), cb.equal(root.get(FileDetails_.filedetailsPatientid), patientId)).getGroupRestriction();

				return consentFormsDetailsPredicate;
				
			}

		};
	}
	
	/**
	 * @return Portal Consent Forms File Details 
	 */	
	public static Specification<FileName> getPatientConsentFormFileNameDetails(final int fileDetailId)
	{
		return new Specification<FileName>() {

			@Override
			public Predicate toPredicate(Root<FileName> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {				
				
				Predicate consentFormsDetailsPredicate;
				
				consentFormsDetailsPredicate=cq.where(cb.equal(root.get(FileName_.filenameScanid), fileDetailId)).getGroupRestriction();

				return consentFormsDetailsPredicate;
				
			}

		};
	}
	
}
