package com.glenwood.glaceemr.server.application.specifications;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;

import com.glenwood.glaceemr.server.application.models.AuditTrail;
import com.glenwood.glaceemr.server.application.models.AuditTrail_;
import com.glenwood.glaceemr.server.application.repositories.AuditLogSubModuleRepository;

@Component
public class AuditTrailSpecifications {
	
	
	public static Specification<AuditTrail> checkUserId(final Integer userId) {
		Specification<AuditTrail> checkUserIdSpec = new Specification<AuditTrail>() {
			@Override
			public Predicate toPredicate(Root<AuditTrail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkUserIdPredicate = cb.equal(root.get(AuditTrail_.userId), userId);
				return checkUserIdPredicate;
			}
		};
		return checkUserIdSpec;
	}
	
	public static Specification<AuditTrail> checkPatientId(final int patientId) {
		Specification<AuditTrail> checkPatientIdSpec = new Specification<AuditTrail>() {
			@Override
			public Predicate toPredicate(Root<AuditTrail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkPatientIdPredicate = cb.equal(root.get(AuditTrail_.patientId), patientId);
				return checkPatientIdPredicate;
			}
		};
		return checkPatientIdSpec;
	}

	public static Specification<AuditTrail> checkLogModule(final String module) {
		Specification<AuditTrail> checkLogModuleSpec = new Specification<AuditTrail>() {
			@Override
			public Predicate toPredicate(Root<AuditTrail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkLogModulePredicate = cb.equal(root.get(AuditTrail_.module), module);
				return checkLogModulePredicate;
			}
		};
		return checkLogModuleSpec;
	}
	
	public static Specification<AuditTrail> checkParentLogModule(final List<Integer> subModuleIds) {
		Specification<AuditTrail> checkParentLogModuleSpec = new Specification<AuditTrail>() {
			@Override
			public Predicate toPredicate(Root<AuditTrail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(subModuleIds.size()==0)
					subModuleIds.add(0);
				Predicate checkParentLogModulePredicate = root.get(AuditTrail_.module).in(subModuleIds.toArray());
				return checkParentLogModulePredicate;
			}
		};
		return checkParentLogModuleSpec;
	}
	
	public static Specification<AuditTrail> checkAction(final String action) {
		Specification<AuditTrail> checkActionSpec = new Specification<AuditTrail>() {
			@Override
			public Predicate toPredicate(Root<AuditTrail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkActionPredicate = cb.equal(root.get(AuditTrail_.action), action);
				return checkActionPredicate;
			}
		};
		return checkActionSpec;
	}

	public static Specification<AuditTrail> checkOutcome(final String outcome) {
		Specification<AuditTrail> checkOutcomeSpec = new Specification<AuditTrail>() {
			@Override
			public Predicate toPredicate(Root<AuditTrail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkOutcomePredicate = cb.equal(root.get(AuditTrail_.outcome), outcome);
				return checkOutcomePredicate;
			}
		};
		return checkOutcomeSpec;
	}

	public static Specification<AuditTrail> checkDescription(final String desc) {
		Specification<AuditTrail> checkDescriptionSpec = new Specification<AuditTrail>() {
			@Override
			public Predicate toPredicate(Root<AuditTrail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkDescriptionPredicate = cb.like(cb.lower(root.get(AuditTrail_.desc)), "%" + desc + "%");
				return checkDescriptionPredicate;
			}
		};
		return checkDescriptionSpec;
	}

	public static Specification<AuditTrail> checkStartTimeStamp(final Timestamp startTimeStamp) {
		Specification<AuditTrail> checkStartTimeSpec = new Specification<AuditTrail>() {
			@Override
			public Predicate toPredicate(Root<AuditTrail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkStartTimeStampPredicate = cb.greaterThanOrEqualTo(root.get(AuditTrail_.logOn), startTimeStamp);
				return checkStartTimeStampPredicate;
			}
		};
		return checkStartTimeSpec;
	}
	
	public static Specification<AuditTrail> checkLogId(final int logId) {
		Specification<AuditTrail> checkLogIdSpec = new Specification<AuditTrail>() {
			@Override
			public Predicate toPredicate(Root<AuditTrail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkLogIdPredicate = cb.equal(root.get(AuditTrail_.logId), logId);
				return checkLogIdPredicate;
			}
		};
		return checkLogIdSpec;
	}
	
	public static Specification<AuditTrail> checkParentEvent(final int parentEvent) {
		Specification<AuditTrail> checkParentEventSpec = new Specification<AuditTrail>() {
			@Override
			public Predicate toPredicate(Root<AuditTrail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkParentEventPredicate = cb.equal(root.get(AuditTrail_.parentID), parentEvent);
				return checkParentEventPredicate;
			}
		};
		return checkParentEventSpec;
	}
	
	public static Specification<AuditTrail> checkSessionId(final String sessionId) {
		Specification<AuditTrail> checkSessionIdSpec = new Specification<AuditTrail>() {
			@Override
			public Predicate toPredicate(Root<AuditTrail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkSessionIdPredicate = cb.equal(root.get(AuditTrail_.sessionId),sessionId );
				return checkSessionIdPredicate;
			}
		};
		return checkSessionIdSpec;
	}
	
	public static Specification<AuditTrail> checkClientIp(final String clientIp) {
		Specification<AuditTrail> checkClientIpSpec = new Specification<AuditTrail>() {
			@Override
			public Predicate toPredicate(Root<AuditTrail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkClientIpPredicate = cb.equal(root.get(AuditTrail_.clientIp),clientIp );
				return checkClientIpPredicate;
			}
		};
		return checkClientIpSpec;
	}
	
	public static Specification<AuditTrail> getSearchResult(final int userId, final String parentModule, final String subModule, final String outcome, final String desc, final String action, final int parentEvent, final int patientId, final String sessionId, final String clientIp, final int logId, final String sortProperty, final String order, final Timestamp startTimeStamp, final Timestamp endTimeStamp, final AuditLogSubModuleRepository repository) {
		Specification<AuditTrail> specification = new Specification<AuditTrail>() {
			@Override
			public Predicate toPredicate(Root<AuditTrail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate checklogOnPredicate = cb.lessThanOrEqualTo(root.get(AuditTrail_.logOn), endTimeStamp);
					return checklogOnPredicate;
			}
		};

		if (userId != -1)
			specification = Specifications.where(specification).and(checkUserId(userId));
		if (patientId != -1)
			specification = Specifications.where(specification).and(checkPatientId(patientId));
		if (!parentModule.equals("-1")){
			if(subModule.equals("-1")){
				specification = Specifications.where(specification).and(checkParentLogModule(repository.getSubModuleIds(Integer.parseInt(parentModule))));
			}
			else{
				specification = Specifications.where(specification).and(checkLogModule(subModule));
			}
		}else if (!subModule.equals("-1"))
			specification = Specifications.where(specification).and(checkLogModule(subModule));
		if (!outcome.equals("-1"))
			specification = Specifications.where(specification).and(checkOutcome(outcome));
		if (!desc.trim().equals("-1"))
			specification = Specifications.where(specification).and(checkDescription(desc));
		if (!action.trim().equals("-1"))
			specification = Specifications.where(specification).and(checkAction(action));
		if (startTimeStamp != null)
			specification = Specifications.where(specification).and(checkStartTimeStamp(startTimeStamp));
		if (logId != -1){
			specification = Specifications.where(specification).and(checkLogId(logId));
			specification = Specifications.where(specification).or(checkParentEvent(logId));
		}
		if (parentEvent != -1){
			specification = Specifications.where(specification).and(checkLogId(parentEvent));
			specification = Specifications.where(specification).or(checkParentEvent(parentEvent));
		}
		if (!sessionId.equals("-1"))
			specification = Specifications.where(specification).and(checkSessionId(sessionId));
		if (!clientIp.equals("-1"))
			specification = Specifications.where(specification).and(checkClientIp(clientIp));
		return specification;
	}

}
