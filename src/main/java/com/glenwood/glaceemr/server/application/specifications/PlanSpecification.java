package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.AftercareInsMapping;
import com.glenwood.glaceemr.server.application.models.AftercareInsMapping_;
import com.glenwood.glaceemr.server.application.models.PatientAftercareData;
import com.glenwood.glaceemr.server.application.models.PatientAftercareData_;
import com.glenwood.glaceemr.server.application.models.PatientAssessments;
import com.glenwood.glaceemr.server.application.models.PatientAssessments_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalDxElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalDxElements_;
import com.glenwood.glaceemr.server.application.models.PlanMapping;
import com.glenwood.glaceemr.server.application.models.PlanMapping_;
import com.glenwood.glaceemr.server.application.models.PrimarykeyGenerator;
import com.glenwood.glaceemr.server.application.models.PrimarykeyGenerator_;
import com.glenwood.glaceemr.server.application.models.SoapElementDatalist;
import com.glenwood.glaceemr.server.application.models.SoapElementDatalist_;

public class PlanSpecification {

	public static Specification<SoapElementDatalist> bytabElementId(final Integer tabId, final String elementId){
		return new Specification<SoapElementDatalist>() {

			@Override
			public Predicate toPredicate(Root<SoapElementDatalist> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				query.where(cb.equal(root.get(SoapElementDatalist_.soapElementDatalistTabid), tabId),
							 cb.like(root.get(SoapElementDatalist_.soapElementDatalistElementid), elementId));
				
				return query.getRestriction();
			}
		};
	}

	public static Specification<PlanMapping> getDxMapping(final Integer insId, final String dxcode,
			final Integer mappingType) {
		return new Specification<PlanMapping>() {

			@Override
			public Predicate toPredicate(Root<PlanMapping> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				query.where(cb.equal(root.get(PlanMapping_.planMappingInstructionid), insId),
							 cb.equal(root.get(PlanMapping_.planMappingType), mappingType),
							  cb.equal(cb.trim(root.get(PlanMapping_.planMappingCode)), dxcode.trim()));
				
				return query.getRestriction();
			}
		}; 
	}

	/**
	 * Get After care instructions
	 * @param patientId
	 * @param encounterId
	 * @param insId
	 * @return
	 */
	public static Specification<PatientAftercareData> getAftercareIns(final Integer patientId,
			final Integer encounterId, final Integer insId, final String dxCode, final String codingsystem) {

		return new Specification<PatientAftercareData>() {

			@Override
			public Predicate toPredicate(Root<PatientAftercareData> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate finalPred= cb.and(cb.equal(root.get(PatientAftercareData_.patientAftercareDataPatientId), patientId),
											   cb.equal(root.get(PatientAftercareData_.patientAftercareDataEncounterId), encounterId),
											   	cb.equal(root.get(PatientAftercareData_.patientAftercareDataAftercareId), insId));
				if(!dxCode.isEmpty())
					finalPred= cb.and(finalPred,cb.like(cb.lower(cb.trim(root.get(PatientAftercareData_.patientAftercareDataDxcode))), dxCode.toLowerCase()));
				else
					finalPred= cb.and(finalPred,cb.or(cb.isNull(root.get(PatientAftercareData_.patientAftercareDataDxcode)),
									  					cb.equal(cb.trim(root.get(PatientAftercareData_.patientAftercareDataDxcode)),"")));
				if(!codingsystem.isEmpty())
					finalPred= cb.and(finalPred,cb.like(root.get(PatientAftercareData_.patientAftercareDataDxcodesystem), codingsystem));
				else
					finalPred= cb.and(finalPred,cb.or(cb.isNull(root.get(PatientAftercareData_.patientAftercareDataDxcodesystem)),
							  							cb.equal(cb.trim(root.get(PatientAftercareData_.patientAftercareDataDxcodesystem)),"")));					
				
				query.where(finalPred);
				
				return query.getRestriction();
			}
		};
	}

	/**
	 * Get After care instruction mapping codes
	 * @param insId
	 * @param dxCode
	 * @param insType
	 * @return
	 */
	public static Specification<AftercareInsMapping> getAftercareInsMapping(final Integer insId, final String dxCode,
			final int insType) {
		return new Specification<AftercareInsMapping>() {

			@Override
			public Predicate toPredicate(Root<AftercareInsMapping> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				query.where(cb.equal(root.get(AftercareInsMapping_.aftercareInsMappingInsId), insId),
						 	 cb.equal(cb.upper(cb.trim(root.get(AftercareInsMapping_.aftercareInsMappingCode))), dxCode),
						 	  cb.equal(root.get(AftercareInsMapping_.aftercareInsMappingInsType), insType));
				
				return query.getRestriction();
			}
		};
	}

	/**
	 * Get Patient clinical dx elements
	 * @param patientId
	 * @param chartId
	 * @param encounterId
	 * @param dxCode
	 * @return
	 */
	public static Specification<PatientClinicalDxElements> getPatientClinicalDxElements(
			final Integer patientId, final Integer chartId, final Integer encounterId,
			final String dxCode) {
		
		return new Specification<PatientClinicalDxElements>() {

			@Override
			public Predicate toPredicate(Root<PatientClinicalDxElements> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate finalPred= cb.and(cb.equal(root.get(PatientClinicalDxElements_.patientClinicalDxElementsPatientid),patientId),
											 cb.equal(root.get(PatientClinicalDxElements_.patientClinicalDxElementsEncounterid),encounterId),
											  cb.equal(root.get(PatientClinicalDxElements_.patientClinicalDxElementsChartid),chartId));
				if(!dxCode.isEmpty())
					finalPred= cb.and(finalPred, cb.like(cb.lower(cb.trim(root.get(PatientClinicalDxElements_.patientClinicalDxElementsCode))), dxCode.toLowerCase()));
				
				query.where(finalPred);
				
				return query.getRestriction();
			}
			
		};
	}
	
	/**
	 * Get Assessments
	 * @param encounterId
	 * @param patientId
	 * @param dxCode
	 * @return
	 */
	public static Specification<PatientAssessments> getAssessments(final Integer encounterId, final Integer patientId, final String dxCode) {
		return new Specification<PatientAssessments>() {

			@Override
			public Predicate toPredicate(Root<PatientAssessments> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				query.where(cb.equal(root.get(PatientAssessments_.patient_assessments_id), encounterId),
						 	 cb.equal(root.get(PatientAssessments_.patient_assessments_patientId), patientId),
						 	  cb.like(cb.lower(cb.trim(root.get(PatientAssessments_.patient_assessments_dxcode))), dxCode.trim().toLowerCase()));
				
				return query.getRestriction();
			}
		};
	}

	public static Specification<PrimarykeyGenerator> getDxSequence() {
		return new Specification<PrimarykeyGenerator>() {

			@Override
			public Predicate toPredicate(Root<PrimarykeyGenerator> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				query.where(cb.equal(root.get(PrimarykeyGenerator_.primarykey_generator_tablename), "patient_assessments"));
				
				return query.getRestriction();
			}
		};
	}
	
	/**
	 * Get Handouts
	 */
	public static Specification<PatientAftercareData> getHandouts(final Integer patientId, final Integer encounterId) {
		return new Specification<PatientAftercareData>() {

			@Override
			public Predicate toPredicate(Root<PatientAftercareData> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				query.where(cb.equal(root.get(PatientAftercareData_.patientAftercareDataEncounterId), encounterId),
						 	 cb.equal(root.get(PatientAftercareData_.patientAftercareDataPatientId), patientId));
				
				return query.getRestriction();
			}
		};
	}
}
