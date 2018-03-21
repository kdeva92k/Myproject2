package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.expression.spel.ast.OpAnd;

import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsQuestions;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsQuestionsOptions;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsQuestionsOptions_;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsQuestions_;
import com.glenwood.glaceemr.server.application.models.ClinicalQuestionsGroup;
import com.glenwood.glaceemr.server.application.models.ClinicalQuestionsGroup_;
import com.glenwood.glaceemr.server.application.models.GroupQuestionsMapping;
import com.glenwood.glaceemr.server.application.models.GroupQuestionsMapping_;
import com.glenwood.glaceemr.server.application.models.LeafXmlVersion;
import com.glenwood.glaceemr.server.application.models.LeafXmlVersion_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElementsQuestions;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElementsQuestions_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalHistory;
import com.glenwood.glaceemr.server.application.models.PatientClinicalHistory_;
import com.glenwood.glaceemr.server.application.services.portal.portalForms.TabConstants;

public class PortalFormsSpecification {

	
	
	
	
	/**
	 * @return Portal Intake Forms  
	 */	
	public static Specification<ClinicalQuestionsGroup> getPatyientClinicalIntakeFormsList(final int isXML)
	{
		return new Specification<ClinicalQuestionsGroup>() {

			@Override
			public Predicate toPredicate(Root<ClinicalQuestionsGroup> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {				
				
				Predicate intakeFormsListPredicate;
				
				if(isXML==0||isXML==1)
				    intakeFormsListPredicate=cq.where(cb.equal(root.get(ClinicalQuestionsGroup_.clinicalQuestionsGroupIsxml), isXML), cb.equal(root.get(ClinicalQuestionsGroup_.clinicalQuestionsGroupIsactive), true)).getRestriction();
				else
					intakeFormsListPredicate=cq.where(cb.equal(root.get(ClinicalQuestionsGroup_.clinicalQuestionsGroupIsactive), true)).getRestriction();

				return intakeFormsListPredicate;
				
			}

		};
	}
	
	/**
	 * @return Portal Intake Forms  
	 */	
	public static Specification<ClinicalElementsQuestions> getLibraryQustionnaireElements(final int patientId, final int groupId)
	{
		return new Specification<ClinicalElementsQuestions>() {

			@Override
			public Predicate toPredicate(Root<ClinicalElementsQuestions> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Join<ClinicalElementsQuestions, GroupQuestionsMapping> clinicalElementsQuestionGroupQstnMappingJoin=root.join(ClinicalElementsQuestions_.groupQuestionsMappingList,JoinType.INNER);
				//Join<ClinicalElementsQuestions, ClinicalElementsQuestionsOptions> clinicalElementsQuestionsOptionsJoin=root.join(ClinicalElementsQuestions_.clinicalElementsQuestionsOptions, JoinType.INNER);
				Join<GroupQuestionsMapping, ClinicalQuestionsGroup> questrionGroupJoin=clinicalElementsQuestionGroupQstnMappingJoin.join(GroupQuestionsMapping_.clinialQuestionGroupMapping,JoinType.INNER);
				//Join<ClinicalElementsQuestionsOptions, ClinicalElementsOptions> clinicalElementsOptionsJoin=clinicalElementsQuestionsOptionsJoin.join(ClinicalElementsQuestionsOptions_.clinicalElementsOptions, JoinType.LEFT);
				//Join<ClinicalElementsQuestionsOptions, ClinicalElements> clinicalElementsJoin=clinicalElementsQuestionsOptionsJoin.join(ClinicalElementsQuestionsOptions_.clinicalElements, JoinType.LEFT);
				
				root.fetch(ClinicalElementsQuestions_.clinicalElementsQuestionsOptions).fetch(ClinicalElementsQuestionsOptions_.clinicalElementsOptions,JoinType.LEFT);
				root.fetch(ClinicalElementsQuestions_.clinicalElementsQuestionsOptions).fetch(ClinicalElementsQuestionsOptions_.clinicalElements,JoinType.LEFT);
				root.fetch(ClinicalElementsQuestions_.groupQuestionsMappingList).fetch(GroupQuestionsMapping_.clinialQuestionGroupMapping);
				
				
				Predicate intakeFormsPredicate=cq.where(cb.equal(questrionGroupJoin.get(ClinicalQuestionsGroup_.clinicalQuestionsGroupId), groupId)).
						orderBy(cb.desc(clinicalElementsQuestionGroupQstnMappingJoin.get(GroupQuestionsMapping_.groupQuestionsMappingId)), cb.desc(root.get(ClinicalElementsQuestions_.clinicalElementsQuestionsId))).getRestriction();

				return intakeFormsPredicate;
				
			}

		};
	}
	
	
	/**
	 * @return Portal Intake Forms  
	 */	
	public static Specification<PatientClinicalElementsQuestions> getPatientIntakeFormDetails(final int patientId, final int formGroup)
	{
		return new Specification<PatientClinicalElementsQuestions>() {

			@Override
			public Predicate toPredicate(Root<PatientClinicalElementsQuestions> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {				
				
				Predicate intakeFormsListPredicate = cq.where(cb.equal(root.get(PatientClinicalElementsQuestions_.patientClinicalElementsQuestionsPatientid),patientId),
						cb.equal(root.get(PatientClinicalElementsQuestions_.patientClinicalElementsQuestionsGroupid), formGroup)).getRestriction();

				return intakeFormsListPredicate;
				
			}

		};
	}
	
	/**
	 * @return Portal Clinical Questions Data   
	 */	
	public static Specification<PatientClinicalElementsQuestions> getPatientClinicalELementsQuestionsData(final int patientId, final int requestId)
	{
		return new Specification<PatientClinicalElementsQuestions>() {

			@Override
			public Predicate toPredicate(Root<PatientClinicalElementsQuestions> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate clinicalElementsQuestionsPredicate=cq.where(cb.equal(root.get(PatientClinicalElementsQuestions_.patientClinicalElementsQuestionsPatientid), patientId),
						cb.equal(root.get(PatientClinicalElementsQuestions_.patientClinicalElementsQuestionsId), requestId)).getRestriction();
				
				return clinicalElementsQuestionsPredicate;
			}

		};
	}
	
	/**
	 * @return Portal Clinical Questions Data   
	 */	
	public static Specification<GroupQuestionsMapping> getPatientClinicalELementsQuestions(final int groupId)
	{
		return new Specification<GroupQuestionsMapping>() {

			@Override
			public Predicate toPredicate(Root<GroupQuestionsMapping> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Join<GroupQuestionsMapping, ClinicalQuestionsGroup> questionsGroupJoin=root.join("clinialQuestionGroupMapping", JoinType.INNER);
				
				Predicate clinicalQuestionsPredicate=cq.where(cb.equal(root.get(GroupQuestionsMapping_.groupQuestionsMappingGroupId), groupId),
						cb.equal(questionsGroupJoin.get(ClinicalQuestionsGroup_.clinicalQuestionsGroupId), groupId)).orderBy(cb.desc(cb.literal(1))).getRestriction();
				
				return clinicalQuestionsPredicate;
			}

		};
	}
	
	
	/**
	 * @return Portal Clinical Questions Data   
	 */	
	public static Specification<ClinicalElementsQuestionsOptions> getOptionElements(final String gwid)
	{
		return new Specification<ClinicalElementsQuestionsOptions>() {

			@Override
			public Predicate toPredicate(Root<ClinicalElementsQuestionsOptions> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				StringBuffer query = new StringBuffer();
				query.append("select distinct clinical_elements_questions_options_gwid as optiongwid,clinical_elements_questions_options_savegwid as savegwid, ");
				query.append("clinical_elements_questions_options_value as optionvalue, ");
				query.append("clinical_elements_questions_options_name as optionname, ");
				query.append("clinical_elements_questions_options_selectbox as selectbox,clinical_elements_questions_options_isactive as isactive, coalesce(clinical_elements_datatype,0) as clinicaldatatype,clinical_elements_questions_options_isboolean as isboolean ");
				query.append("from clinical_elements_questions_options " +
							"left join clinical_elements_options on clinical_elements_questions_options_gwid=clinical_elements_options_gwid " +
							"and clinical_elements_options_value=clinical_elements_questions_options_value "+
							"left join clinical_elements on clinical_elements_questions_options_savegwid = clinical_elements_gwid " +
							"where clinical_elements_questions_options_gwid ilike '"+gwid+"' order by selectbox desc ");
				
				//Join<ClinicalElementsQuestionsOptions, ClinicalElementsOptions> elementsQuestionsOptionsJoin=root.join(ClinicalElementsQuestionsOptions_.)
				
				Predicate PatientClinicalElementsQuestionsPredicate=cq.where(cb.like(root.get(ClinicalElementsQuestionsOptions_.clinicalElementsQuestionsOptionsGwid), gwid)).getRestriction();
				
				return PatientClinicalElementsQuestionsPredicate;
			}

		};
	}
	
	/**
	 * @return Portal Clinical Questions Data   
	 */	
	public static Specification<PatientClinicalHistory> getPatientClinicalHistory(final int patientId, final String isActive, final String optionValue, final String optsaveGwid)
	{
		return new Specification<PatientClinicalHistory>() {

			@Override
			public Predicate toPredicate(Root<PatientClinicalHistory> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				
				Predicate clinicalHistoryPredicate=cq.where(cb.equal(root.get(PatientClinicalHistory_.patientClinicalHistoryPatientid), patientId),
						cb.or(cb.equal(root.get(PatientClinicalHistory_.patientClinicalHistoryValue), isActive),cb.equal(root.get(PatientClinicalHistory_.patientClinicalHistoryValue), optionValue)),
						cb.equal(root.get(PatientClinicalHistory_.patientClinicalHistoryGwid), optsaveGwid)).getRestriction();
				
				return clinicalHistoryPredicate;
			}

		};
	}
	
	/**
	 * @return Portal Clinical Questions Data   
	 */	
	public static Specification<LeafXmlVersion> getLeafXMLDetails(final int tabId, final String gender, final int patientAge, final int category)
	{
		return new Specification<LeafXmlVersion>() {

			@Override
			public Predicate toPredicate(Root<LeafXmlVersion> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				
				root.fetch(LeafXmlVersion_.leafVersionsList);
				Predicate leafDetailsPredicate=null;
				
				Predicate tabPredicate=cb.equal(root.get(LeafXmlVersion_.leafXmlVersionTabid), tabId);
				Predicate genderPredicate=cb.like(root.get(LeafXmlVersion_.leafXmlVersionPrintxslurl), "%"+gender+"%");
				
				/*Predicate agePredicate1=cb.greaterThan(cb.length(root.get(LeafXmlVersion_.leafXmlVersionPrintxslurl)), 0);
				Predicate agePredicate2=cb.lessThanOrEqualTo(cb.substring(root.get(LeafXmlVersion_.leafXmlVersionPrintxslurl), 1, cb.literal(value)), patientAge);*/				
				
				
				if(category==TabConstants.NORMAL_GROUP)
				    leafDetailsPredicate=cq.where(tabPredicate).getRestriction();
				else if(category==TabConstants.NORMAL_GROUP)
					leafDetailsPredicate=cq.where(tabPredicate, genderPredicate).getRestriction();
				
				
				
				return leafDetailsPredicate;
			}

		};
	}
	
}
