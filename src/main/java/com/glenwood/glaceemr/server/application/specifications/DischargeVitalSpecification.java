package com.glenwood.glaceemr.server.application.specifications;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

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

import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.PatientVitals;
import com.glenwood.glaceemr.server.application.models.PatientVitals_;

public class DischargeVitalSpecification {

	
	
	public static Specification<PatientVitals> getDichargeVitalsByEncounterId(final Integer patientId,final Integer encounterId,final Integer admissionEpisode){

		return new Specification<PatientVitals>(){

			@Override
			public Predicate toPredicate(Root<PatientVitals> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientVitals, Encounter> encPatVitalJoin = root.join(PatientVitals_.encounter,JoinType.INNER);
				root.join(PatientVitals_.clinicalElement,JoinType.INNER);
				query.orderBy(cb.asc(root.get(PatientVitals_.patientVitalsGwid)),cb.asc(root.get(PatientVitals_.patientVitalsDateOfRecording)),cb.asc(root.get(PatientVitals_.patientVitalsTimeOfRecording)));
				return cb.and(cb.equal(encPatVitalJoin.get(Encounter_.encounterId), encounterId),
						cb.equal(root.get(PatientVitals_.patientVitalsPatientid), patientId));
				
			}

		};
	}
	
	public static Specification<PatientVitals> getDichargeVitalsByGwid(final Date date ,final Timestamp timeOfRecordng ,final String gwid ,final Integer patientId,final Integer encounterId){

		return new Specification<PatientVitals>(){

			@Override
			public Predicate toPredicate(Root<PatientVitals> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.get(PatientVitals_.patientVitalsPatientid),patientId),
						cb.equal(root.get(PatientVitals_.patientVitalsEncounterid), encounterId),
						cb.equal(root.get(PatientVitals_.patientVitalsGwid), gwid),
						cb.equal(root.get(PatientVitals_.patientVitalsTimeOfRecording),timeOfRecordng),
						cb.equal(root.get(PatientVitals_.patientVitalsDateOfRecording),date));
			}

		};
	}
	
	public static Specification<PatientVitals> getDichargeVitalsByPatientId(final Integer patientId, final Integer encounterId, final Integer admissionEpisode){

		return new Specification<PatientVitals>(){

			@Override
			public Predicate toPredicate(Root<PatientVitals> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientVitals, Encounter> encPatVitalJoin = root.join(PatientVitals_.encounter,JoinType.INNER);
				root.join(PatientVitals_.clinicalElement,JoinType.INNER);
				query.orderBy(cb.asc(root.get(PatientVitals_.patientVitalsGwid)),cb.asc(root.get(PatientVitals_.patientVitalsDateOfRecording)),cb.asc(root.get(PatientVitals_.patientVitalsTimeOfRecording)));
				return cb.and(cb.equal(root.get(PatientVitals_.patientVitalsPatientid), patientId),
							  cb.lessThanOrEqualTo(root.get(PatientVitals_.patientVitalsEncounterid), encounterId));
				
			}

		};
	}
	
	public static Pageable getLimitedDischargeVitals(int pageIndex, int pageOffset){

		return new PageRequest(pageIndex, pageOffset, Sort.Direction.DESC,"patientVitalsDateOfRecording,patientVitalsTimeOfRecording");
	}

}
