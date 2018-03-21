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

import com.glenwood.glaceemr.server.application.models.FileDetails;
import com.glenwood.glaceemr.server.application.models.FileDetails_;
import com.glenwood.glaceemr.server.application.models.LabDescription;
import com.glenwood.glaceemr.server.application.models.LabDescription_;
import com.glenwood.glaceemr.server.application.models.ChartStatus;
import com.glenwood.glaceemr.server.application.models.ChartStatus_;
import com.glenwood.glaceemr.server.application.models.LabEntries;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter_;
import com.glenwood.glaceemr.server.application.models.LabEntries_;
import com.glenwood.glaceemr.server.application.models.LabParameterCode;
import com.glenwood.glaceemr.server.application.models.LabParameterCode_;
import com.glenwood.glaceemr.server.application.models.LabParameters;
import com.glenwood.glaceemr.server.application.models.LabParameters_;

public class PortalLabResultsSpecification {


	public static Specification<ChartStatus> getLabStatusConfig() {
		return new Specification<ChartStatus>() {

			@Override
			public Predicate toPredicate(Root<ChartStatus> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {

				Predicate labResultsPredicate = cq.where(cb.equal(root.get(ChartStatus_.chart_status_reference_id), 413)).getRestriction();
				return labResultsPredicate;
			}	
		};
	}


	/**
	 * Specification to get the lab results list
	 * @param isReviewed
	 * @return
	 */


	public static Specification<LabEntries> getPatientLabResults(final int patientId, final int chartId, final int statusToShow) {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {
				
				if(Long.class!=cq.getResultType()){
					
					root.fetch(LabEntries_.encounter, JoinType.LEFT);
				}
                Join<LabEntries, LabDescription> joinLab = root.join(LabEntries_.labDescriptionTable,JoinType.INNER);
               
                Predicate statusPredicate = null;
                if(statusToShow == 1)
                	statusPredicate=cb.not(root.get(LabEntries_.labEntriesTestStatus).in(1,2,7,8));
                else
                	statusPredicate=cb.not(root.get(LabEntries_.labEntriesTestStatus).in(1,2,3,7,8));
				
				Predicate typePredicate=cb.notEqual(joinLab.get(LabDescription_.labDescriptionTestcategoryType),3);
				Predicate chartPredicate=cb.equal(root.get(LabEntries_.labEntriesChartid), chartId);

				Predicate labResultsPredicate = cq.where(statusPredicate, typePredicate, chartPredicate).getRestriction();
				return labResultsPredicate;
			}	
		};
	}

	public static Specification<LabEntriesParameter> getLabResultParameters(final int testDetailId, final int chartId) {

		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {

				Join<LabEntriesParameter, LabParameters> labPrameterJoin=root.join(LabEntriesParameter_.labParametersTable, JoinType.INNER);

				Predicate labResultParametersPredicate = cq.where(cb.equal(root.get(LabEntriesParameter_.labEntriesParameterTestdetailid), testDetailId), cb.equal(labPrameterJoin.get(LabParameters_.labParametersIsactive), true)).getRestriction();
				return labResultParametersPredicate;
			}	
		};
	}

	public static Specification<LabParameterCode> getLabParamCodeSystem(final String codeSystem) {

		return new Specification<LabParameterCode>() {

			@Override
			public Predicate toPredicate(Root<LabParameterCode> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {

				Predicate labResultParametersPredicate = cq.where(cb.equal(root.get(LabParameterCode_.labParameterCodeSystem), "LOINC")).getRestriction();
				return labResultParametersPredicate;
			}	
		};
	}


	public static Specification<LabEntriesParameter> getLabParameterHistory(final int parameterId, final int chartId) {

		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {

								
				Predicate chartIdPredicate = cb.equal(root.get(LabEntriesParameter_.labEntriesParameterChartid), chartId);
				Predicate parameterIdPredicate=cb.equal(root.get(LabEntriesParameter_.labEntriesParameterId), parameterId);
				
				Predicate parameterHistoryPredicate=cq.where(chartIdPredicate, parameterIdPredicate).getRestriction();
				
				return parameterHistoryPredicate;
			}	
		};
	}
	
	public static Specification<FileDetails> getLabAttachmentsFileDetails(final int patientId, final int fileDetailEntityId) {

		return new Specification<FileDetails>() {

			@Override
			public Predicate toPredicate(Root<FileDetails> root, CriteriaQuery<?> cq,
					CriteriaBuilder cb) {

				root.fetch(FileDetails_.fileName,JoinType.INNER);
				
				Predicate fileDetailsPredicate=cq.where(cb.equal(root.get(FileDetails_.filedetailsEntityid), fileDetailEntityId),cb.equal(root.get(FileDetails_.filedetailsPatientid), patientId)).getRestriction();

				return fileDetailsPredicate;
			}
		};
	}



	public static Pageable createPortalLabResultListRequestByDescDate(int pageIndex, int offset) {

		return new PageRequest(pageIndex, offset, Sort.Direction.DESC,"labEntriesTestDesc","labEntriesOrdOn");
	}


}
