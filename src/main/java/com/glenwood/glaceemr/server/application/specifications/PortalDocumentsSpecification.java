
package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.FileDetails;
import com.glenwood.glaceemr.server.application.models.FileDetails_;
import com.glenwood.glaceemr.server.application.models.FileName;
import com.glenwood.glaceemr.server.application.models.FileName_;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsCategory;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsCategory_;
import com.glenwood.glaceemr.server.application.models.PatientPortalSharedDocs;
import com.glenwood.glaceemr.server.application.models.PatientPortalSharedDocs_;
import com.glenwood.glaceemr.server.application.models.PatientSharedDocumentLog;
import com.glenwood.glaceemr.server.application.models.PatientSharedDocumentLog_;
import com.glenwood.glaceemr.server.application.models.SchedulerAppointment_;

public class PortalDocumentsSpecification {

	/**
	 * @param patientId	: used to get patient details of a patient of that particular id
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<PatientPortalSharedDocs> SharedDocsList(final int patientId)
	{
		return new Specification<PatientPortalSharedDocs>() {

			@Override
			public Predicate toPredicate(Root<PatientPortalSharedDocs> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Join<PatientPortalSharedDocs, FileDetails> docsDetailsJoin=root.join(PatientPortalSharedDocs_.fileDetails,JoinType.INNER);
				//Join<FileDetails, FileName> detailsNameJoin=docsDetailsJoin.join(FileDetails_.fileName,JoinType.INNER);
				//Predicate isFileActive=cb.equal(detailsNameJoin.get(FileName_.filenameIsactive), true);
				//detailsNameJoin.on(isFileActive);
				//Join<FileName, EmpProfile> nameProfileReviewedByJoin=detailsNameJoin.join(FileName_.empProfileReviewedUser,JoinType.INNER);
				//Join<FileName, EmpProfile> nameProfileCreatedByJoin=detailsNameJoin.join(FileName_.empProfileCreatedUser,JoinType.INNER);
				//Join<PatientPortalSharedDocs, EmployeeProfile> docsProfileSharedByJoin=root.join(PatientPortalSharedDocs_.empProfileSharedUser,JoinType.INNER);
				//root.fetch(PatientPortalSharedDocs_.fileDetails,JoinType.INNER).fetch(FileDetails_.encounterTable,JoinType.INNER);
				//root.fetch(PatientPortalSharedDocs_.fileDetails,JoinType.INNER).fetch(FileDetails_.createdByEmpProfileTable,JoinType.INNER);
				//root.fetch(PatientPortalSharedDocs_.fileDetails,JoinType.INNER).fetch(FileDetails_.lastModifiedByEmpProfileTable,JoinType.INNER);
				
				if(Long.class!=cq.getResultType()){
					root.fetch(PatientPortalSharedDocs_.fileDetails,JoinType.INNER).fetch(FileDetails_.patientDocCategory);
					root.fetch(PatientPortalSharedDocs_.fileDetails,JoinType.INNER).fetch(FileDetails_.fileName);
					root.fetch(PatientPortalSharedDocs_.empProfileSharedUser,JoinType.INNER);
				}
				
				Join<FileDetails, PatientDocumentsCategory> detailsCategoryJoin=docsDetailsJoin.join(FileDetails_.patientDocCategory,JoinType.INNER);
				Predicate isCategoryActive=cb.equal(detailsCategoryJoin.get(PatientDocumentsCategory_.patientDocCategoryIsactive), true);
				detailsCategoryJoin.on(isCategoryActive);
				
				Predicate patIdPredicate=cq.where(cb.equal(docsDetailsJoin.get(FileDetails_.filedetailsPatientid), patientId)).getRestriction();

				return patIdPredicate;
			}

		};
	}

	public static Specification<PatientSharedDocumentLog> getPatientVisitSummary(final int patientId) {

		return new Specification<PatientSharedDocumentLog>() {

			@Override
			public Predicate toPredicate(Root<PatientSharedDocumentLog> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Join<PatientSharedDocumentLog, Encounter> docsLogEncounterJoin=root.join(PatientSharedDocumentLog_.docsLogEncounterTable,JoinType.INNER);
				//Join<Encounter, EmployeeProfile> encounterEmpProJoin=docsLogEncounterJoin.join(Encounter_.empProfileEmpId,JoinType.INNER);
				
				if(Long.class!=cq.getResultType()){
					root.fetch(PatientSharedDocumentLog_.docsLogEncounterTable);
					root.fetch(PatientSharedDocumentLog_.docsLogEncounterTable).fetch(Encounter_.empProfileEmpId,JoinType.INNER);
					root.fetch(PatientSharedDocumentLog_.docsLogEncounterTable).fetch(Encounter_.patientEncounterType,JoinType.INNER);
				}
				

				cq.where(cb.and(cb.equal(root.get(PatientSharedDocumentLog_.patientSharedDocumentLogStatus),true),
						cb.equal(root.get(PatientSharedDocumentLog_.patientSharedDocumentLogSharedMode), 1),
						cb.equal(root.get(PatientSharedDocumentLog_.patientSharedDocumentLogPatientId), patientId)));
				
				//cq.orderBy(cb.desc(docsLogEncounterJoin.get(Encounter_.encounterDate)),cb.desc(root.get(PatientSharedDocumentLog_.patientSharedDocumentLogSharedOn)));

				return cq.getRestriction();
			}
		}; 

	}

	public static Specification<FileDetails> getFileDetailsByFileId(final int patientId, final int fileId) {

		return new Specification<FileDetails>() {

			@Override
			public Predicate toPredicate(Root<FileDetails> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {

				root.fetch(FileDetails_.fileName,JoinType.INNER);
				root.fetch(FileDetails_.createdByEmpProfileTable,JoinType.INNER);
				root.fetch(FileDetails_.patientDocCategory,JoinType.INNER);
				root.fetch(FileDetails_.patientPortalSharedDocs,JoinType.INNER);

				Predicate fileDetailsPredicate=cq.where(cb.equal(root.get(FileDetails_.filedetailsId), fileId),cb.equal(root.get(FileDetails_.filedetailsPatientid), patientId)).getRestriction();

				return fileDetailsPredicate;
			}
		};
	}

	public static Specification<FileName> getFileNamesList(final int patientId, final int fileId) {

		return new Specification<FileName>() {

			@Override
			public Predicate toPredicate(Root<FileName> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				
				Predicate fileDetailsPredicate=cq.where(cb.equal(root.get(FileName_.filenameScanid), fileId)).getRestriction();

				return fileDetailsPredicate;
			}
		};
	}

	public static Pageable createPortalVisitSummaryPageRequestByDescDate(int pageIndex, int offset) {

		return new PageRequest(pageIndex, offset, Sort.Direction.DESC,"patientSharedDocumentLogSharedOn");
	}


	public static Pageable createPortalSharedDocsPageRequestByDescDate(int pageIndex, int offset) {

		return new PageRequest(pageIndex, offset, Sort.Direction.DESC,"patientPortalShareddocsSharedon");
	}


}
