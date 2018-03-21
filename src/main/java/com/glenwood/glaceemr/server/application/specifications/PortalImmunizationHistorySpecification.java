package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.ClinicalElementsQuestions;
import com.glenwood.glaceemr.server.application.models.CvxVaccineGroupMapping;
import com.glenwood.glaceemr.server.application.models.CvxVaccineGroupMapping_;
import com.glenwood.glaceemr.server.application.models.ChartStatus;
import com.glenwood.glaceemr.server.application.models.ChartStatus_;
import com.glenwood.glaceemr.server.application.models.LabDescription;
import com.glenwood.glaceemr.server.application.models.LabDescription_;
import com.glenwood.glaceemr.server.application.models.LabEntries;
import com.glenwood.glaceemr.server.application.models.LabEntries_;
import com.glenwood.glaceemr.server.application.models.VaccineGroupMapping;
import com.glenwood.glaceemr.server.application.models.VaccineOrderDetails_;
import com.glenwood.glaceemr.server.application.models.VaccineOrder_;
import com.glenwood.glaceemr.server.application.models.VaccineReport;
import com.glenwood.glaceemr.server.application.models.VaccineReport_;
import com.glenwood.glaceemr.server.application.models.Vis;
import com.glenwood.glaceemr.server.application.models.Vis_;

public class PortalImmunizationHistorySpecification {


	/**
	 * @return LabDescImmunizationHistory
	 */	
	public static Specification<LabEntries> getLabDescImmunizationHistory(final int patientId, final int chartId)
	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Join<LabEntries, LabDescription> labDescJoin=root.join(LabEntries_.labDescriptionTable, JoinType.INNER);
				
				if(Long.class!=cq.getResultType()){
					
					root.fetch(LabEntries_.labPerformedBy);
					root.fetch(LabEntries_.vaccineOrderDetails, JoinType.LEFT).fetch(VaccineOrderDetails_.vaccineOrder, JoinType.LEFT).fetch(VaccineOrder_.vaccineManDetails, JoinType.LEFT);
				}
				Predicate immuniztionHistoryPredicate=cq.where(cb.greaterThan(root.get(LabEntries_.labEntriesTestStatus), 2),
						cb.notEqual(root.get(LabEntries_.labEntriesTestStatus), 7),
						cb.equal(root.get(LabEntries_.labEntriesChartid), chartId),
						cb.equal(labDescJoin.get(LabDescription_.labDescriptionGroupid), 36)).orderBy(cb.desc(root.get(LabEntries_.labEntriesPerfOn))).getRestriction();

				return immuniztionHistoryPredicate;
			}

		};
	}

	/**
	 * @return VaccineReportImmunizationHistory  
	 */	
	public static Specification<VaccineReport> getVaccineReportImmunizationHistory(final int patientId, final int chartId)
	{
		return new Specification<VaccineReport>() {

			@Override
			public Predicate toPredicate(Root<VaccineReport> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				if(Long.class!=cq.getResultType()){
					//root.fetch(VaccineReport_.labDescriptionTable);
				}

				Join<VaccineReport, LabDescription> vaccineDescJoin=root.join(VaccineReport_.labDescriptionTable, JoinType.INNER);
				
				root.fetch(VaccineReport_.labDescriptionTable, JoinType.INNER);
				Predicate immuniztionHistoryPredicate=cq.where(cb.equal(root.get(VaccineReport_.vaccineReportChartId), chartId),
						cb.equal(root.get(VaccineReport_.vaccineReportIsemr), 2),
						cb.equal(vaccineDescJoin.get(LabDescription_.labDescriptionGroupid), 36)).getRestriction();

				return immuniztionHistoryPredicate;
			}

		};
	}
	
	/**
	 * @return Vaccine List Predicate  
	 */	
	public static Specification<LabDescription> getVaccineListBySearchKey(final String searchKey)
	{
		return new Specification<LabDescription>() {

			@Override
			public Predicate toPredicate(Root<LabDescription> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate vaccineListPredicate;
				
				if(searchKey==null||searchKey.trim().length()<=0)
					vaccineListPredicate=cq.where(cb.equal(root.get(LabDescription_.labDescriptionGroupid), 36)).orderBy(cb.asc(root.get(LabDescription_.labDescriptionTestDesc))).getRestriction();
				else
					vaccineListPredicate=cq.where(cb.equal(root.get(LabDescription_.labDescriptionGroupid), 36),
							cb.like(cb.upper(root.get(LabDescription_.labDescriptionTestDesc)), "%"+searchKey.toUpperCase()+"%")).orderBy(cb.asc(root.get(LabDescription_.labDescriptionTestDesc))).getRestriction();
				
				return vaccineListPredicate;
			}

		};
	}
	
	
	/**
	 * @return Vaccine List Predicate  
	 */	
	public static Specification<CvxVaccineGroupMapping> getVaccineGroupMappings(final String labDescCVX)
	{
		return new Specification<CvxVaccineGroupMapping>() {

			@Override
			public Predicate toPredicate(Root<CvxVaccineGroupMapping> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				//Join<CvxVaccineGroupMapping, VaccineGroupMapping> vaccineGroupJoin=root.join(CvxVaccineGroupMapping_.vaccineGroupMapping);
				Predicate labDescCvxPredicate=cb.equal(root.get(CvxVaccineGroupMapping_.cvxVaccineGroupMappingCvxCode), labDescCVX);
				root.fetch(CvxVaccineGroupMapping_.vaccineGroupMapping, JoinType.INNER);
				return cq.where(cb.and(labDescCvxPredicate)).getRestriction();
			}

		};
	}
	
	/**
	 * @return Vaccine List Predicate  
	 */	
	public static Specification<Vis> getVaccineVISFilesList(final String vaccineGroupCode)
	{
		return new Specification<Vis>() {

			@Override
			public Predicate toPredicate(Root<Vis> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				root.fetch(Vis_.visFileMapping);
				Predicate visFilesListPredicate=cq.where(cb.and(cb.equal(root.get(Vis_.visVaccineGroupCode), vaccineGroupCode))).getRestriction();
				return visFilesListPredicate;
			}

		};
	}
	
	/**
	 * @return Vaccine Update Reasons Predicate  
	 */	
	public static Specification<ChartStatus> getVaccineUpdateReasonList()
	{
		return new Specification<ChartStatus>() {

			@Override
			public Predicate toPredicate(Root<ChartStatus> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate vaccUpdateReasonPredicate=cq.where(cb.equal(root.get(ChartStatus_.chart_status_reference_id), 998)).orderBy(cb.asc(root.get(ChartStatus_.chart_status_status_name))).getRestriction();

				return vaccUpdateReasonPredicate;
			}

		};
	}
	
}
