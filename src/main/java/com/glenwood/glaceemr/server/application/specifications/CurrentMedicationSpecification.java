package com.glenwood.glaceemr.server.application.specifications;

import java.sql.Timestamp;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.EpisodeTypeList;
import com.glenwood.glaceemr.server.application.models.PatientEncounterType;
import com.glenwood.glaceemr.server.application.models.PatientEncounterType_;
import com.glenwood.glaceemr.server.application.models.PatientEpisode;
import com.glenwood.glaceemr.server.application.models.PatientEpisode_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PharmacyMapping;
import com.glenwood.glaceemr.server.application.models.PharmacyMapping_;
import com.glenwood.glaceemr.server.application.models.RatePlan;
import com.glenwood.glaceemr.server.application.models.ReceiptDetail;
import com.glenwood.glaceemr.server.application.models.ReceiptDetail_;
import com.glenwood.glaceemr.server.application.models.ReferringDoctor;
import com.glenwood.glaceemr.server.application.models.ReferringDoctor_;
import com.glenwood.glaceemr.server.application.models.RoomDetails;


@Component
@Transactional 
public class CurrentMedicationSpecification {
	static boolean isFormularySearch=false;
	
	
	/**
	 *  
	 * @return Getting chart pharmacy details
	 * @param patientId
	 */
	public static Specification<Chart> getChartPharmacy(final int patientId) {
		return new Specification<Chart>() {
			@Override
			public Predicate toPredicate(Root<Chart> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.equal(root.get(Chart_.chartPatientid), patientId));				
				return query.getRestriction();
			}
		};
	}

	/**
	 *  
	 * @return Getting pharmacy mapping pharmacy details
	 * @param patientId
	 */
	public static Specification<PharmacyMapping> getMapPharmacy(final int patientId) {
		return new Specification<PharmacyMapping>() {

			@Override
			public Predicate toPredicate(Root<PharmacyMapping> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.equal(root.get(PharmacyMapping_.pharmacyMappingPatid), patientId));	
				return query.getRestriction();
			}
		};
	}

	/**
	 *  
	 * @return Getting encounter details
	 * @param encounterId
	 */
	public static Specification<Encounter> getEncData(final int encounterId) {
		return new Specification<Encounter>() {

			@Override
			public Predicate toPredicate(Root<Encounter> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<Encounter, Chart> encChartJoin=root.join(Encounter_.chart,JoinType.INNER);
				Join<Chart, PatientRegistration> chartPatJoin=encChartJoin.join(Chart_.patientRegistrationTable,JoinType.INNER);
				Join<Encounter, PatientEncounterType> encPatTypeJoin=root.join(Encounter_.patientEncounterType,JoinType.INNER);
				Join<PatientEncounterType, RatePlan> patRateJoin=encPatTypeJoin.join(PatientEncounterType_.ratePlan,JoinType.INNER);
				Join<Encounter, RoomDetails> encH479Join=root.join(Encounter_.room_details,JoinType.LEFT);
				Join<PatientRegistration, ReferringDoctor> patH076Join=chartPatJoin.join(PatientRegistration_.referringPhyTable,JoinType.LEFT);
				Join<Encounter, EmployeeProfile> encEmpJoin=root.join(Encounter_.empProfileEmpId,JoinType.LEFT);
				Join<Encounter, ReferringDoctor> encH076Join=root.join(Encounter_.referringTable,JoinType.LEFT);
				Join<Encounter, PatientEpisode> encPatEpiJoin=root.join(Encounter_.patientEpisode,JoinType.LEFT);
				Join<PatientEpisode, EpisodeTypeList> patEpisodeJoin=encPatEpiJoin.join(PatientEpisode_.episodeTypeList,JoinType.LEFT);
				patH076Join.on(cb.equal(patH076Join.get(ReferringDoctor_.referring_doctor_type), 1));
				encH076Join.on(cb.equal(encH076Join.get(ReferringDoctor_.referring_doctor_type), 1));
				
				Predicate type=cb.equal(root.get(Encounter_.encounterType), 1);
				Predicate status=root.get(Encounter_.encounterStatus).in(1,2);
				Predicate encounterid=cb.equal(root.get(Encounter_.encounterId), encounterId);
				
				query.where(cb.and(type,status,encounterid));
				
				return query.getRestriction();
			}
		};
	}

	/**
	 *  
	 * @return Getting copayment details
	 * @param encounterDate
	 * @param patientId
	 */
	public static Specification<ReceiptDetail> getReceiptDetails(final Timestamp encounterDate,final int patientId) {
		return new Specification<ReceiptDetail>() {

			@Override
			public Predicate toPredicate(Root<ReceiptDetail> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate payerId=cb.equal(root.get(ReceiptDetail_.receiptDetailPayerId), patientId);
				Predicate payerType=cb.equal(root.get(ReceiptDetail_.receiptDetailPayerType), 1);
				Predicate amount=cb.notEqual(root.get(ReceiptDetail_.receiptDetailReceiptAmt), 0.00);
				Predicate depositeDate=cb.equal(root.get(ReceiptDetail_.receiptDetailDepositDate), encounterDate);
				query.where(payerId,payerType,amount,depositeDate);
				return query.getRestriction();
			}
		};
	}

	/**
	 *  
	 * @return Getting patient chart details
	 * @param patientId
	 */
	public static Specification<Chart> getchartDetails(final int patientId) {
		return new Specification<Chart>() {

			@Override
			public Predicate toPredicate(Root<Chart> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.equal(root.get(Chart_.chartPatientid), patientId));
				return query.getRestriction();
			}
		};
	}

	/**
	 *  
	 * @return Getting maximum of opened encounters details
	 * @param chartId
	 */
	public static Specification<Encounter> getMaxencounter(final String chartId) {
		return new Specification<Encounter>() {

			@Override
			public Predicate toPredicate(Root<Encounter> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate status= cb.equal(root.get(Encounter_.encounterStatus), 1);
				Predicate encChart=cb.equal(root.get(Encounter_.encounterChartid), Integer.parseInt(chartId));
				Predicate encType=cb.equal(root.get(Encounter_.encounterType), 3);
				query.where(status,encChart,encType).orderBy(cb.desc(root.get(Encounter_.encounterId)));
				
				return query.getRestriction();
			}
		};
	}
}
