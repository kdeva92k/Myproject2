package com.glenwood.glaceemr.server.application.specifications;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.AssociatedPhoneLogs;
import com.glenwood.glaceemr.server.application.models.AssociatedPhoneLogs_;

import com.glenwood.glaceemr.server.application.models.LabEntriesParameter;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter_;
import com.glenwood.glaceemr.server.application.models.LabParameterCode;
import com.glenwood.glaceemr.server.application.models.LabParameterCode_;
import com.glenwood.glaceemr.server.application.models.LabParameters;
import com.glenwood.glaceemr.server.application.models.LabParameters_;
import com.glenwood.glaceemr.server.application.models.PatientEpisode;
import com.glenwood.glaceemr.server.application.models.PatientEpisodeAttributeDetails;
import com.glenwood.glaceemr.server.application.models.PatientEpisodeAttributeDetails_;
import com.glenwood.glaceemr.server.application.models.PatientEpisode_;
import com.glenwood.glaceemr.server.application.models.ReminderEvent;
import com.glenwood.glaceemr.server.application.models.ReminderEvent_;
import com.glenwood.glaceemr.server.application.models.WarfarinIndication;
import com.glenwood.glaceemr.server.application.models.WarfarinIndication_;
import com.glenwood.glaceemr.server.application.models.WarfarinLog;
import com.glenwood.glaceemr.server.application.models.WarfarinLog_;
import com.glenwood.glaceemr.server.application.services.chart.coumadinflowsheet.LabEntriesParam_LabParamCodeBean;

public class CoumadinFlowSheetSpecification {
	/**
	 * Specification to retrieve episode data
	 */
	public static Specification<PatientEpisode> getEpisodes(
			final Integer patientId, final Integer episodeId, final Integer type) {
		return new Specification<PatientEpisode>() {
			public Predicate toPredicate(Root<PatientEpisode> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByPatientid = cb.equal(
						root.get(PatientEpisode_.patientEpisodePatientid),
						patientId);
				Predicate predicateByEpisodeid = cb.equal(
						root.get(PatientEpisode_.patientEpisodeId), episodeId);
				Predicate predicateByType = cb.equal(
						root.get(PatientEpisode_.patientEpisodeType), type);
				Predicate episodeDetails;
				if (episodeId != -1 && type != -1) {
					episodeDetails = cb.and(predicateByPatientid,
							predicateByEpisodeid, predicateByType);
				} else if (episodeId != -1) {
					episodeDetails = cb.and(predicateByPatientid,
							predicateByEpisodeid);
				} else {
					episodeDetails = cb.and(predicateByPatientid);
				}
				return episodeDetails;
			}
		};

	}

	/**
	 * Specification to get patient episode details in ascending order
	 */
	public static Specification<PatientEpisode> getOrder() {
		return new Specification<PatientEpisode>() {

			@Override
			public Predicate toPredicate(Root<PatientEpisode> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				query.orderBy(cb.asc(root
						.get(PatientEpisode_.patientEpisodeStartDate)));
				return query.getRestriction();
			}
		};
	}

	/**
	 * Specification to retrieve episode attribute details GWID
	 */
	public static Specification<PatientEpisodeAttributeDetails> getGWID(
			final Integer episodeId) {
		return new Specification<PatientEpisodeAttributeDetails>() {

			@Override
			public Predicate toPredicate(
					Root<PatientEpisodeAttributeDetails> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByEpisodeId = cb
						.equal(root
								.get(PatientEpisodeAttributeDetails_.patientEpisodeAttributeDetailsEpisodeid),
								episodeId);
				Predicate gwid = cb.and(predicateByEpisodeId);
				return gwid;
			}

		};
	}

	/**
	 * Specification to retrieve single particular row
	 */
	public static Specification<PatientEpisodeAttributeDetails> gettId(
			final Integer episodeId) {
		return new Specification<PatientEpisodeAttributeDetails>() {

			@Override
			public Predicate toPredicate(
					Root<PatientEpisodeAttributeDetails> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByEpisodeId = cb
						.equal(root
								.get(PatientEpisodeAttributeDetails_.patientEpisodeAttributeDetailsEpisodeid),
								episodeId);
				Predicate attriDetails = cb.and(predicateByEpisodeId);
				return attriDetails;
			}

		};
	}

	/**
	 * Specification to retrieve particular row of warfarin indication
	 */
	public static Specification<WarfarinIndication> getObj(
			final Integer indicationId, final Integer episodeId) {
		return new Specification<WarfarinIndication>() {

			@Override
			public Predicate toPredicate(Root<WarfarinIndication> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateById = cb.equal(
						root.get(WarfarinIndication_.warfarinIndicationId),
						indicationId);
				Predicate predicateByEpisodeId = cb.equal(root
						.get(WarfarinIndication_.warfarinIndicationEpisodeId),
						episodeId);
				Predicate indicationDatails = cb.and(predicateById,
						predicateByEpisodeId);
				return indicationDatails;
			}

		};
	}

	/**
	 * Specification to WarfarinLog data
	 */
	public static Specification<WarfarinLog> getObject(final Integer logId,
			final Integer episodeId) {
		return new Specification<WarfarinLog>() {

			@Override
			public Predicate toPredicate(Root<WarfarinLog> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByLogId = cb.equal(
						root.get(WarfarinLog_.warfarinLogId), logId);
				Predicate predicateByEpisodeId = cb.equal(
						root.get(WarfarinLog_.warfarinLogEpisodeId), episodeId);
				Predicate logDetails = cb.and(predicateByLogId,
						predicateByEpisodeId);
				return logDetails;
			}
		};
	}

	/**
	 * Specification to get recently inserted row
	 */
	public static Specification<WarfarinLog> getMaxId() {
		return new Specification<WarfarinLog>() {

			@Override
			public Predicate toPredicate(Root<WarfarinLog> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				cb.desc(root.get(WarfarinLog_.warfarinLogId));
				return query.getRestriction();
			}

		};
	}

	/**
	 * Specification to get remainder details
	 */
	public static Specification<ReminderEvent> getReminder(final Integer eventid) {
		return new Specification<ReminderEvent>() {

			@Override
			public Predicate toPredicate(Root<ReminderEvent> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate IDbased = cb.equal(
						root.get(ReminderEvent_.reminderEventId), eventid);
				Predicate EventRow = cb.and(IDbased);
				return EventRow;
			}
		};
	}

	/**
	 * Specification to get log info
	 */
	public static Specification<WarfarinLog> getLogInfo(final Integer chartId,
			final Integer episodeId) {
		return new Specification<WarfarinLog>() {

			@Override
			public Predicate toPredicate(Root<WarfarinLog> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				root.fetch(WarfarinLog_.employeetableByCreatedName,
						JoinType.LEFT);

				root.fetch(WarfarinLog_.employeetableModifiesName,
						JoinType.LEFT);
				root.fetch(WarfarinLog_.employeetableReviewedName,
						JoinType.LEFT);
				root.fetch(WarfarinLog_.employeetableByEnteredName,
						JoinType.LEFT);

				Predicate predi5 = cb.equal(
						root.get(WarfarinLog_.warfarinLogEpisodeId), episodeId);
				Predicate predi6 = cb.notEqual(
						root.get(WarfarinLog_.warfarinLogStatus), 3);

				Predicate warfarinDetails = cb.and(predi5, predi6);

				return warfarinDetails;

			}

		};
	}

	/**
	 * Specification to get LabParameterCode values
	 */
	public static Specification<LabParameterCode> getPTINR1() {
		return new Specification<LabParameterCode>() {

			@Override
			public Predicate toPredicate(Root<LabParameterCode> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate predicate = cb.or(cb.equal(
						root.get(LabParameterCode_.labParameterCodeValue),
						"5902-2"), cb.equal(
						root.get(LabParameterCode_.labParameterCodeValue),
						"6301-6"));

				Predicate PTINR = cb.and(predicate);
				return PTINR;
			}
		};
	}

	/**
	 * Specification to get LabEntriesParameter values
	 */
	public static Specification<LabEntriesParameter> getPTINR2(
			final Integer chartId, final java.util.Date date1) {
		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByChartId = cb.equal(root
						.get(LabEntriesParameter_.labEntriesParameterChartid),
						chartId);
				Predicate predicateByDate = cb
						.equal(cb
								.function(
										"date",
										Date.class,
										root.get(LabEntriesParameter_.labEntriesParameterDate)),
								date1);
				Predicate PTINR = cb.and(predicateByChartId, predicateByDate);
				return PTINR;
			}
		};
	}

	/**
	 * Specification to get warfarin log
	 */
	public static Specification<WarfarinLog> getRow(final Integer patientId,
			final Integer episodeId) {
		return new Specification<WarfarinLog>() {

			@Override
			public Predicate toPredicate(Root<WarfarinLog> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate[] conditions = new Predicate[] {
						cb.equal(root.get(WarfarinLog_.warfarinLogPatientId),
								patientId),
						cb.equal(root.get(WarfarinLog_.warfarinLogStatus), 1),
						cb.equal(root.get(WarfarinLog_.warfarinLogEpisodeId),
								episodeId) };
				Predicate warfarinRow = cb.and(conditions);
				query.orderBy(
						cb.desc(root.get(WarfarinLog_.warfarinLogPerformedDate)))
						.getRestriction();

				return warfarinRow;
			}

		};
	}

	/**
	 * Specification to get recent INR
	 */
	public static Specification<LabEntriesParam_LabParamCodeBean> getRecentINR(
			final Integer episodeId, final Integer patientId,
			final Integer chartId, final Date performedDate) {
		return new Specification<LabEntriesParam_LabParamCodeBean>() {

			@Override
			public Predicate toPredicate(
					Root<LabEntriesParam_LabParamCodeBean> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Root<LabParameterCode> labParameterCode = query
						.from(LabParameterCode.class);

				Join<LabParameterCode, LabEntriesParameter> joinForRecentINR = labParameterCode
						.join("labentriesparam", JoinType.LEFT);

				Predicate predicateByChartId = cb.equal(joinForRecentINR
						.get(LabEntriesParameter_.labEntriesParameterChartid),
						chartId);
				joinForRecentINR.on(predicateByChartId);
				Predicate predicateByCodeValue = cb
						.equal(labParameterCode
								.get(LabParameterCode_.labParameterCodeValue),
								"5902-2");
				Predicate predicateByParamValue = cb
						.notEqual(
								joinForRecentINR
										.get(LabEntriesParameter_.labEntriesParameterValue),
								"");

				Predicate predicateBydate = cb.notEqual(joinForRecentINR
						.get(LabEntriesParameter_.labEntriesParameterDate),
						performedDate);
				Predicate predicateByParamdate = cb
						.greaterThan(
								joinForRecentINR
										.get(LabEntriesParameter_.labEntriesParameterDate),
								performedDate);
				Predicate recentINR = cb.and(predicateByCodeValue,
						predicateByParamValue, predicateBydate,
						predicateByParamdate);
				query.orderBy(cb.desc(joinForRecentINR
						.get(LabEntriesParameter_.labEntriesParameterDate)));

				return recentINR;

			}

		};
	}

	/**
	 * Specification to get LabEntriesparam values
	 */
	public static Specification<LabEntriesParameter> getLabEntriesParam(
			final Integer Id) {
		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate idBased = cb.equal(
						root.get(LabEntriesParameter_.labEntriesParameterId),
						Id);
				Predicate labEntriesParam = cb.and(idBased);

				return labEntriesParam;
			}
		};
	}

	/**
	 * Specification to get LabEntriesParameter values
	 */
	public static Specification<LabEntriesParameter> getRecentINR2(
			final Integer chartId, final Date performedDate) {
		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate predicateByChartId = cb.equal(root
						.get(LabEntriesParameter_.labEntriesParameterChartid),
						chartId);
				Predicate predicateByParamValue = cb
						.notEqual(
								root.get(LabEntriesParameter_.labEntriesParameterValue),
								"");
				Predicate predicateBydate = cb.notEqual(
						root.get(LabEntriesParameter_.labEntriesParameterDate),
						performedDate);
				Predicate predicateByParamdate = cb.greaterThan(
						root.get(LabEntriesParameter_.labEntriesParameterDate),
						performedDate);
				Predicate INRvalues = cb.and(predicateByChartId,
						predicateByParamValue, predicateBydate,
						predicateByParamdate);
				return INRvalues;
			}

		};

	}

	/**
	 * Specification to get PatientEpisode values
	 */
	public static Specification<PatientEpisode> getEpisodes(
			final Integer episodeId) {
		return new Specification<PatientEpisode>() {

			@Override
			public Predicate toPredicate(Root<PatientEpisode> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByEpisodeId = cb.equal(
						root.get(PatientEpisode_.patientEpisodeId), episodeId);
				Predicate episodes = cb.and(predicateByEpisodeId);
				return episodes;
			}

		};
	}

	/**
	 * Specification to get WarfarinLog count
	 */
	public static Specification<WarfarinLog> getCount() {
		return new Specification<WarfarinLog>() {

			@Override
			public Predicate toPredicate(Root<WarfarinLog> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<WarfarinLog, AssociatedPhoneLogs> join = root.join(
						WarfarinLog_.phonelogs, JoinType.INNER);
				Predicate predicateByEntityType = cb
						.equal(join
								.get(AssociatedPhoneLogs_.associatedPhoneLogsEntityType),
								2);
				join.on(predicateByEntityType);

				return query.getRestriction();
			}

		};
	}

	/**
	 * Specification to get lab entries values
	 */
	public static Specification<LabEntriesParameter> getLabEntries(
			final Integer chartId) {
		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByChartId = cb.equal(root
						.get(LabEntriesParameter_.labEntriesParameterChartid),
						chartId);
				Predicate labEntries = cb.and(predicateByChartId);
				return labEntries;
				
			}
		};
	}
	/**
	 * Specification to get PT LOINC values
	 */
	public static Specification<LabParameterCode> getLabParams() {
		return new Specification<LabParameterCode>() {

			@Override
			public Predicate toPredicate(Root<LabParameterCode> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByCodeValue = cb.equal(
						root.get(LabParameterCode_.labParameterCodeValue),
						"6301-6");
				Predicate labParams = cb.and(predicateByCodeValue);
				return labParams;
			}
		};
	}

	public static Specification<LabParameterCode> getLabParam() {
		return new Specification<LabParameterCode>() {

			@Override
			public Predicate toPredicate(Root<LabParameterCode> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByCodeValue = cb.equal(
						root.get(LabParameterCode_.labParameterCodeValue),
						"5902-2");
				Predicate labParams = cb.and(predicateByCodeValue);
				return labParams;
			}

		};
	}

	/**
	 * Specification to get indication
	 */
	public static Specification<WarfarinIndication> getIndication(
			final Integer episodeId) {
		return new Specification<WarfarinIndication>() {

			@Override
			public Predicate toPredicate(Root<WarfarinIndication> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByEpisodeId = cb.equal(root
						.get(WarfarinIndication_.warfarinIndicationEpisodeId),
						episodeId);
				Predicate predicateByStatus = cb.equal(
						root.get(WarfarinIndication_.warfarinIndicationStatus),
						1);
				Predicate indication = cb.and(predicateByEpisodeId,
						predicateByStatus);
				return indication;
			}

		};
	}

	/**
	 * Specification to get PT count
	 */
	public static Specification<LabParameters> getPTCount() {
		return new Specification<LabParameters>() {

			@Override
			public Predicate toPredicate(Root<LabParameters> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<LabParameters, LabParameterCode> LabParamCodeJoin = root
						.join(LabParameters_.labParamCode);
				Predicate codeValue = cb
						.equal(LabParamCodeJoin
								.get(LabParameterCode_.labParameterCodeValue),
								"5902-2");
				Predicate codeSystem = cb
						.like(LabParamCodeJoin
								.get(LabParameterCode_.labParameterCodeSystem),
								"LOINC");
				LabParamCodeJoin.on(codeValue, codeSystem);

				return query.getRestriction();
			}
		};
	}

	/**
	 * Specification to get INR count
	 */
	public static Specification<LabParameters> getINRCount() {
		return new Specification<LabParameters>() {

			@Override
			public Predicate toPredicate(Root<LabParameters> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<LabParameters, LabParameterCode> LabParamCodeJoin = root
						.join(LabParameters_.labParamCode);
				Predicate codeValue = cb
						.equal(LabParamCodeJoin
								.get(LabParameterCode_.labParameterCodeValue),
								"6301-6");
				Predicate codeSystem = cb
						.like(LabParamCodeJoin
								.get(LabParameterCode_.labParameterCodeSystem),
								"LOINC");
				LabParamCodeJoin.on(codeValue, codeSystem);

				return query.getRestriction();
			}
		};
	}

	/**
	 * Specification to get warfarin log
	 */
	public static Specification<WarfarinLog> getWarfarinlog(
			final Integer patientId) {
		return new Specification<WarfarinLog>() {

			@Override
			public Predicate toPredicate(Root<WarfarinLog> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate byPatientId = cb.equal(
						root.get(WarfarinLog_.warfarinLogPatientId), patientId);
				Predicate warfarinLog = cb.and(byPatientId);
				return warfarinLog;
			}

		};
	}

}
