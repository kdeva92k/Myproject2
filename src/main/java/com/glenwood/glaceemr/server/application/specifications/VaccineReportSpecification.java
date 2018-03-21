package com.glenwood.glaceemr.server.application.specifications;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.LabDescription;
import com.glenwood.glaceemr.server.application.models.PatientVisEntries;
import com.glenwood.glaceemr.server.application.models.PatientVisEntries_;
import com.glenwood.glaceemr.server.application.models.VaccineOrderDetails;
import com.glenwood.glaceemr.server.application.models.VaccineOrderDetails_;
import com.glenwood.glaceemr.server.application.models.VaccineReport;
import com.glenwood.glaceemr.server.application.models.VaccineReport_;

public class VaccineReportSpecification {
	/**
	 * Specification to get the list of orders having testId
	 * @param testId
	 * @param chartId
	 * @return Specification<VaccineReport>
	 */
	public static Specification<VaccineReport> getOrderDetailsOnlyVaccines(final Integer[] testId,final Integer chartId,final Integer encounterId,final Encounter encounterEntity)
	{
		return new Specification<VaccineReport>() {

			@Override
			public Predicate toPredicate(Root<VaccineReport> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<VaccineReport,LabDescription> join=root.join("labDescriptionTable",JoinType.LEFT);
				query.orderBy(cb.desc(cb.coalesce(root.get(VaccineReport_.vaccineReportGivenDate),cb.function("to_timestamp",Timestamp.class,cb.literal("1900-05-13 16:40:35"),cb.literal("YYYY-MM-DD HH24:MI:SS")))),
						cb.desc(root.get(VaccineReport_.vaccineReportVaccineId)));
				Predicate vaccIdPred=root.get(VaccineReport_.vaccineReportVaccineId).in((Object[])testId);
				Predicate chartIdPred=cb.equal(root.get(VaccineReport_.vaccineReportChartId),chartId);
				Predicate groupIdsPred =null;
				if((encounterId!=-1)&&(encounterEntity.getEncounterStatus()==3)){
					Predicate encounterDatePred=cb.lessThanOrEqualTo(root.get(VaccineReport_.vaccineReportGivenDate), encounterEntity.getEncounterDate());
					groupIdsPred =cb.and(vaccIdPred,chartIdPred,encounterDatePred);
				}else{
					groupIdsPred =cb.and(vaccIdPred,chartIdPred);
				}
				return groupIdsPred;
			}
		};
	}
	
	/**
	 * Specification to get the list of orders having testId
	 * @param testId
	 * @param chartId
	 * @return Specification<VaccineReport>
	 */
	public static Specification<VaccineReport> getDetailsOnlyVaccinesNotPatDecli(final Integer[] testId,final Integer chartId)
	{
		return new Specification<VaccineReport>() {

			@Override
			public Predicate toPredicate(Root<VaccineReport> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<VaccineReport,LabDescription> join=root.join("labDescriptionTable",JoinType.LEFT);
				query.orderBy(cb.desc(cb.coalesce(root.get(VaccineReport_.vaccineReportGivenDate),cb.function("to_timestamp",Timestamp.class,cb.literal("1900-05-13 16:40:35"),cb.literal("YYYY-MM-DD HH24:MI:SS")))));
				Predicate groupIdsPred =cb.and(root.get(VaccineReport_.vaccineReportVaccineId).in((Object[])testId),
						cb.equal(root.get(VaccineReport_.vaccineReportChartId),chartId));
				return groupIdsPred;
			}
		};
	}
	
	/**
	 * Specification to get the list of orders having encounterId
	 * @param encounterId
	 * @return Specification<VaccineReport>
	 */
	public static Specification<VaccineReport> getEncounterId(final Integer encounterId)
	{
		return new Specification<VaccineReport>() {

			@Override
			public Predicate toPredicate(Root<VaccineReport> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getEncounterId =cb.equal(root.get(VaccineReport_.vaccineReportEncounterId),encounterId);
				return getEncounterId;
			}
		};
	}
	
	/**
	 * Specification to get the list of orders having chartId
	 * @param chartId
	 * @return Specification<VaccineReport>
	 */
	public static Specification<VaccineReport> getChartId(final Integer chartId)
	{
		return new Specification<VaccineReport>() {

			@Override
			public Predicate toPredicate(Root<VaccineReport> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getEncounterId =cb.equal(root.get(VaccineReport_.vaccineReportChartId),chartId);
				return getEncounterId;
			}
		};
	}
	
	/**
	 * Specification to get the list of orders having chartId
	 * @param chartId
	 * @return Specification<VaccineReport>
	 */
	public static Specification<VaccineReport> getVaccineId(final Integer vaccineId)
	{
		return new Specification<VaccineReport>() {

			@Override
			public Predicate toPredicate(Root<VaccineReport> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getEncounterId =cb.equal(root.get(VaccineReport_.vaccineReportVaccineId),vaccineId);
				return getEncounterId;
			}
		};
	}
	
	/**
	 * Specification to get the list of orders having dosage level
	 * @param dosageLevel
	 * @return Specification<VaccineReport>
	 */
	public static Specification<VaccineReport> getDosageLevel(final Integer dosageLevel)
	{
		return new Specification<VaccineReport>() {

			@Override
			public Predicate toPredicate(Root<VaccineReport> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getDosageLevel =cb.equal(root.get(VaccineReport_.vaccineReportDosageLevel),dosageLevel);
				return getDosageLevel;
			}
		};
	}
	
	/**
	 * Specification to get the list of vaccines having lotNo
	 * @param lotNo
	 * @return Specification<VaccineOrderDetails>
	 */
	public static Specification<VaccineOrderDetails> getLotNo(final Integer lotNo)
	{
		return new Specification<VaccineOrderDetails>() {

			@Override
			public Predicate toPredicate(Root<VaccineOrderDetails> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getLotNo =cb.equal(root.get(VaccineOrderDetails_.vaccineOrderDetailsId),lotNo);
				return getLotNo;
			}
		};
	}
	
	/**
	 * Specification to get the list of vaccines having qty
	 * @param lotNo
	 * @return Specification<VaccineOrderDetails>
	 */
	public static Specification<VaccineOrderDetails> getQtyGreaterThan(final Double qty)
	{
		return new Specification<VaccineOrderDetails>() {

			@Override
			public Predicate toPredicate(Root<VaccineOrderDetails> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getQty =cb.greaterThan(root.get(VaccineOrderDetails_.vaccineOrderDetailsQtyUsed),qty);
				return getQty;
			}
		};
	}
	
	/**
	 * Specification to get the list of patient vis having Id
	 * @param adminId
	 * @return Specification<LabGroups>
	 */
	public static Specification<PatientVisEntries> patientVisAdminisId(final Integer adminId)
	{
		return new Specification<PatientVisEntries>() {

			@Override
			public Predicate toPredicate(Root<PatientVisEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate patientVisAdminisId = cb.equal(root.get(PatientVisEntries_.patientVisEntriesAdministrationId),adminId);
				return patientVisAdminisId;
			}
		};
	}
}
