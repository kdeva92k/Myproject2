package com.glenwood.glaceemr.server.application.services.chart.careplan;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Subquery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.CNMCodeSystem;
import com.glenwood.glaceemr.server.application.models.CNMCodeSystem_;
import com.glenwood.glaceemr.server.application.models.CarePlanConcern;
import com.glenwood.glaceemr.server.application.models.CarePlanConcernShortcut;
import com.glenwood.glaceemr.server.application.models.CarePlanConcernShortcut_;
import com.glenwood.glaceemr.server.application.models.CarePlanConcern_;
import com.glenwood.glaceemr.server.application.models.CarePlanGoal;
import com.glenwood.glaceemr.server.application.models.CarePlanGoalShortcut;
import com.glenwood.glaceemr.server.application.models.CarePlanGoalShortcut_;
import com.glenwood.glaceemr.server.application.models.CarePlanGoal_;
import com.glenwood.glaceemr.server.application.models.CarePlanIntervention;
import com.glenwood.glaceemr.server.application.models.CarePlanIntervention_;
import com.glenwood.glaceemr.server.application.models.CarePlanLog;
import com.glenwood.glaceemr.server.application.models.CarePlanLog_;
import com.glenwood.glaceemr.server.application.models.CarePlanOutcome;
import com.glenwood.glaceemr.server.application.models.CarePlanOutcome_;
import com.glenwood.glaceemr.server.application.models.CarePlanRecommendedIntervention;
import com.glenwood.glaceemr.server.application.models.CarePlanRecommendedIntervention_;
import com.glenwood.glaceemr.server.application.models.CarePlanSummary;
import com.glenwood.glaceemr.server.application.models.CarePlanSummary_;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions_;
import com.glenwood.glaceemr.server.application.models.ClinicalElements_;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.EncounterPlan;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.FrequentInterventions;
import com.glenwood.glaceemr.server.application.models.FrequentInterventions_;
import com.glenwood.glaceemr.server.application.models.GeneralShortcut;
import com.glenwood.glaceemr.server.application.models.GeneralShortcut_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalFindings;
import com.glenwood.glaceemr.server.application.models.PatientClinicalFindings_;
import com.glenwood.glaceemr.server.application.models.UnitsOfMeasure;
import com.glenwood.glaceemr.server.application.models.UnitsOfMeasure_;
import com.glenwood.glaceemr.server.application.models.VitalsParameter;
import com.glenwood.glaceemr.server.application.models.VitalsParameter_;
import com.glenwood.glaceemr.server.application.repositories.CarePlanConcernRepository;
import com.glenwood.glaceemr.server.application.repositories.CarePlanGoalRepository;
import com.glenwood.glaceemr.server.application.repositories.CarePlanInterventionRepository;
import com.glenwood.glaceemr.server.application.repositories.CarePlanLogRepository;
import com.glenwood.glaceemr.server.application.repositories.CarePlanOutcomeRepository;
import com.glenwood.glaceemr.server.application.repositories.CarePlanRecommendedInterventionRepository;
import com.glenwood.glaceemr.server.application.repositories.CarePlanSummaryRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterPlanRepository;
import com.glenwood.glaceemr.server.application.repositories.FrequentInterventionsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalFindingsRepository;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalConstants;
import com.glenwood.glaceemr.server.application.specifications.EncounterSpecification;
import com.glenwood.glaceemr.server.application.specifications.PatientClinicalElementsSpecification;
import com.glenwood.glaceemr.server.utils.HUtil;

@Service
public class CarePlanServiceImpl implements  CarePlanService  {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	CarePlanConcernRepository carePlanConcernRepository;

	@Autowired
	CarePlanOutcomeRepository carePlanOutcomeRepository;

	@Autowired
	CarePlanGoalRepository carePlanGoalRepository;

	@Autowired
	CarePlanInterventionRepository carePlanInterventionRepository;

	@Autowired
	EncounterPlanRepository encounterPlanRepository;
	
	@Autowired
	PatientClinicalElementsRepository patientClinicalElementsRepository;
	
	@Autowired
	CarePlanSummaryRepository carePlanSummaryRepository;
	
	@Autowired
	CarePlanRecommendedInterventionRepository carePlanRecommendedInterventionRepository;
	
	@Autowired
	CarePlanLogRepository carePlanLogRepository;

	@Autowired
	FrequentInterventionsRepository FrequentInterventionsRepository;
	
	@Autowired
	PatientClinicalFindingsRepository patientClinicalFindingsRepository;
	
	@Autowired
	ApplicationContext applicationContext;
	/**
	 * To fetch care plan concerns
	 * @param concernId
	 * @param patientId
	 * @param categoryId
	 * @return List
	 */
	@Override
	public List<CarePlanConcernBean> fetchCarePlanConcerns(Integer concernId,Integer patientId,Integer categoryId,Integer episodeId,Integer encounterId,String frmDate,String toDate) {
		//CarePlanConcern carePlanConcern = applicationContext.getBean(CarePlanConcern.class);
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarePlanConcernBean> cq = builder.createQuery(CarePlanConcernBean.class);
		Root<CarePlanConcern> root = cq.from(CarePlanConcern.class);
		Join<CarePlanConcern,EmployeeProfile> empCreatedJoin= root.join(CarePlanConcern_.empProfileConcernCreatedBy,JoinType.INNER);
		Join<CarePlanConcern,EmployeeProfile> empModifiedJoin= root.join(CarePlanConcern_.empProfileConcernModifiedBy,JoinType.INNER);

		List<Predicate> predicates = new ArrayList<>();
		if(patientId!=-1)
			predicates.add(builder.equal(root.get(CarePlanConcern_.carePlanConcernPatientId), patientId));
		if(categoryId!=-1)
			predicates.add(builder.equal(root.get(CarePlanConcern_.carePlanConcernCategoryId), categoryId));
		if(concernId!=-1)
			predicates.add(builder.equal(root.get(CarePlanConcern_.carePlanConcernId), concernId));
		if(episodeId!=-1)
			predicates.add(builder.equal(root.get(CarePlanConcern_.careplanConcernEpisodeId), episodeId));
		if(frmDate!=null && !(frmDate.equalsIgnoreCase("-1"))) {
			Date fromDate = null;
			try {
				fromDate=new SimpleDateFormat("MM/dd/yyyy").parse(frmDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			predicates.add(builder.greaterThanOrEqualTo(builder.function("DATE", Date.class,root.get(CarePlanConcern_.carePlanConcernCreatedOn)),fromDate));
		}
		if(toDate!=null && !(toDate.equalsIgnoreCase("-1"))) {
			Date tDate = null;
			try {
				tDate=new SimpleDateFormat("MM/dd/yyyy").parse(toDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			predicates.add(builder.lessThanOrEqualTo(builder.function("DATE", Date.class,root.get(CarePlanConcern_.carePlanConcernCreatedBy)),tDate));
		}
		
		predicates.add(builder.equal(root.get(CarePlanConcern_.carePlanConcernStatus), 1));
		Selection[] selections=new Selection[]{
				root.get(CarePlanConcern_.carePlanConcernId),
				root.get(CarePlanConcern_.carePlanConcernPatientId),
				root.get(CarePlanConcern_.carePlanConcernCategoryId),
				root.get(CarePlanConcern_.carePlanConcernProviderId),
				root.get(CarePlanConcern_.carePlanConcernType),
				root.get(CarePlanConcern_.carePlanConcernCode),
				root.get(CarePlanConcern_.carePlanConcernCodeSystem),
				root.get(CarePlanConcern_.carePlanConcernCodeSystemName),
				root.get(CarePlanConcern_.carePlanConcernCodeDesc),
				root.get(CarePlanConcern_.carePlanConcernPriority),
				root.get(CarePlanConcern_.carePlanConcernValue),
				root.get(CarePlanConcern_.carePlanConcernUnit),
				root.get(CarePlanConcern_.carePlanConcernDesc),
				root.get(CarePlanConcern_.carePlanConcernNotes),
				root.get(CarePlanConcern_.carePlanConcernStatus),
				root.get(CarePlanConcern_.carePlanConcernStatusUpdatedDate),
				root.get(CarePlanConcern_.carePlanConcernCreatedBy),
				root.get(CarePlanConcern_.carePlanConcernCreatedOn),
				root.get(CarePlanConcern_.carePlanConcernModifiedBy),
				root.get(CarePlanConcern_.carePlanConcernModifiedOn),
				root.get(CarePlanConcern_.careplanConcernEpisodeId),
				root.get(CarePlanConcern_.carePlanConcernFrom),
				root.get(CarePlanConcern_.careplanConcernEncounterId),
				empCreatedJoin.get(EmployeeProfile_.empProfileFullname),
				empModifiedJoin.get(EmployeeProfile_.empProfileFullname)
		};
		/*if(episodeId==-1 && encounterId!=-1)
			predicates.add(builder.equal(root.get(CarePlanConcern_.careplanConcernEncounterId), encounterId));
		predicates.add(builder.equal(root.get(CarePlanConcern_.carePlanConcernStatus),1));
		*/
		cq.select(builder.construct(CarePlanConcernBean.class, selections));
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		List<CarePlanConcernBean> concerns=entityManager.createQuery(cq).getResultList();
		return concerns;
	}
	
	/**
	 * To fetch care plan goals
	 * @param goalId
	 * @param concernId
	 * @param patientId
	 * @param encounterId
	 * @return List
	 */
	@Override
	public List<CarePlanGoalBean> fetchCarePlanGoals(Integer goalId,Integer concernId,
			Integer patientId, Integer encounterId,Integer episodeId,String frmDate,String toDate) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarePlanGoalBean> cq = builder.createQuery(CarePlanGoalBean.class);
		Root<CarePlanGoal> root = cq.from(CarePlanGoal.class);
		Join<CarePlanGoal,CarePlanConcern> concernJoin=root.join(CarePlanGoal_.carePlanConcern,JoinType.LEFT);
		List<Predicate> predicates = new ArrayList<>();
		if(patientId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalPatientId), patientId));
		if(encounterId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalEncounterId), encounterId));
		if(concernId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalConcernId), concernId));
		if(goalId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalId), goalId));
		if(episodeId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.careplanGoalEpisodeId), episodeId));
		if(frmDate!=null && !(frmDate.equalsIgnoreCase("-1"))) {
			Date fromDate = null;
			try {
				fromDate=new SimpleDateFormat("MM/dd/yyyy").parse(frmDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			predicates.add(builder.greaterThanOrEqualTo(builder.function("DATE", Date.class,root.get(CarePlanGoal_.carePlanGoalCreatedOn)),fromDate));
		}
		if(toDate!=null && !(toDate.equalsIgnoreCase("-1"))) {
			Date tDate = null;
			try {
				tDate=new SimpleDateFormat("MM/dd/yyyy").parse(toDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			predicates.add(builder.lessThanOrEqualTo(builder.function("DATE", Date.class,root.get(CarePlanGoal_.carePlanGoalCreatedOn)),tDate));
		}
		
		Selection[] selections=new Selection[]{
				root.get(CarePlanGoal_.carePlanGoalId),
				root.get(CarePlanGoal_.carePlanGoalPatientId),
				root.get(CarePlanGoal_.carePlanGoalEncounterId),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalConcernId),-1),
				concernJoin.get(CarePlanConcern_.carePlanConcernDesc),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalPriority),0),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalType),-1),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalTerm),0),
				root.get(CarePlanGoal_.carePlanGoalProviderId),
				root.get(CarePlanGoal_.carePlanGoalDesc),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalCode),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalCodeDescription),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalCodeOperator),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalValue),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalUnit),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalStatus),0),
				root.get(CarePlanGoal_.carePlanGoalTargetDate),
				root.get(CarePlanGoal_.carePlanGoalNextReviewDate),
				root.get(CarePlanGoal_.carePlanGoalNotes),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalType),-1),
				root.get(CarePlanGoal_.carePlanGoalFrom),
				root.get(CarePlanGoal_.carePlanGoalResultStatus),
				root.get(CarePlanGoal_.careplanGoalEpisodeId),
				root.get(CarePlanGoal_.carePlanGoalOrder),
				root.get(CarePlanGoal_.carePlanGoalValueOne),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalAssistanceStatus),0),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalLevelStatus),0)
		};
		cq.select(builder.construct(CarePlanGoalBean.class, selections));
		predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalStatus),1));
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		List<CarePlanGoalBean> concerns=entityManager.createQuery(cq).getResultList();
		return concerns;
	}

	/**
	 * To fetch care plan goals (bean structure)
	 * @param goalId
	 * @param concernId
	 * @param patientId
	 * @param encounterId
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List<CarePlanGoalBean> fetchCarePlanGoalBean(Integer goalId,Integer concernId,
			Integer patientId, Integer encounterId,Integer episodeId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarePlanGoalBean> cq = builder.createQuery(CarePlanGoalBean.class);
		Root<CarePlanGoal> root = cq.from(CarePlanGoal.class);
		Join<CarePlanGoal,CarePlanConcern> concernJoin=root.join(CarePlanGoal_.carePlanConcern,JoinType.LEFT);
		Join<CarePlanGoal,CarePlanOutcome> outcomeJoin=root.join(CarePlanGoal_.carePlanOutcome,JoinType.LEFT);
		Join<CarePlanGoal,EmployeeProfile> empCreatedJoin=root.join(CarePlanGoal_.empProfileGoalCreatedBy,JoinType.LEFT);
		Join<CarePlanGoal,EmployeeProfile> empModifiedJoin=root.join(CarePlanGoal_.empProfileGoalModifiedBy,JoinType.LEFT);

		if(encounterId!=-1) {
			//outcomeJoin.on(builder.equal(builder.function("DATE", Date.class, outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeReviewDate)),getEncounterDate(encounterId)));
			outcomeJoin.on(builder.equal(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeEncounterId),encounterId));
		}
		else {
			outcomeJoin.on(builder.equal(builder.function("DATE", Date.class, outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeReviewDate)),new Date()));
		}
/*			outcomeJoin.on(builder.equal(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeReviewDate),new Date()));
*/		final Subquery<Integer> subquery = cq.subquery(Integer.class);
		final Root<CarePlanOutcome> carePlanOutcome = subquery.from(CarePlanOutcome.class);
		subquery.select(builder.max(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeId)));
		subquery.groupBy(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeGoalId));

	/*	final Subquery<Integer> subqueryEncounter = cq.subquery(Integer.class);
		final Root<CarePlanOutcome> carePlanOutcomeEncounter = subqueryEncounter.from(CarePlanOutcome.class);
		subqueryEncounter.select(builder.max(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeId)));
		subqueryEncounter.where(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeGoalId));

		subqueryEncounter.groupBy(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeGoalId));*/

		
		List<Predicate> predicates = new ArrayList<>();
		List<Predicate> predicatesForStatus = new ArrayList<>();

		if(patientId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalPatientId), patientId));
		//if(encounterId!=-1)
		//	predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalEncounterId), encounterId));
		if(concernId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalConcernId), concernId));
		if(goalId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalId), goalId));
		if(episodeId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.careplanGoalEpisodeId),episodeId));
	/*	if(episodeId==-1 && encounterId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalEncounterId), encounterId));
		*/predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalStatus),1));
		predicates.add(builder.or(root.get(CarePlanGoal_.carePlanGoalResultStatus).in(0,1),root.get(CarePlanGoal_.carePlanGoalResultStatus).isNull()));
		
		//		predicates.add(builder.or(builder.in(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeId)).value(subquery),builder.isNull(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeId))));
		
		Selection[] selections=new Selection[]{
				root.get(CarePlanGoal_.carePlanGoalId),
				root.get(CarePlanGoal_.carePlanGoalPatientId),
				root.get(CarePlanGoal_.carePlanGoalEncounterId),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalConcernId),-1),
				concernJoin.get(CarePlanConcern_.carePlanConcernDesc),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalPriority),0),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalType),-1),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalTerm),0),
				root.get(CarePlanGoal_.carePlanGoalProviderId),
				root.get(CarePlanGoal_.carePlanGoalDesc),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalCode),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalCodeDescription),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalCodeOperator),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalValue),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalUnit),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalStatus),0),
				root.get(CarePlanGoal_.carePlanGoalTargetDate),
				root.get(CarePlanGoal_.carePlanGoalNextReviewDate),
				root.get(CarePlanGoal_.carePlanGoalNotes),
				root.get(CarePlanGoal_.carePlanGoalFrom),
				builder.coalesce(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeProgress),0),
				root.get(CarePlanGoal_.carePlanGoalResultStatus),
				root.get(CarePlanGoal_.careplanGoalEpisodeId),
				root.get(CarePlanGoal_.carePlanGoalOrder),
				root.get(CarePlanGoal_.carePlanGoalValueOne),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalAssistanceStatus),0),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalLevelStatus),0),
				builder.coalesce(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeTargetedGoal),false),
				builder.coalesce(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeId),-1),
				empCreatedJoin.get(EmployeeProfile_.empProfileFullname),
				empModifiedJoin.get(EmployeeProfile_.empProfileFullname),
				root.get(CarePlanGoal_.carePlanGoalCreatedOn),
				root.get(CarePlanGoal_.carePlanGoalModifiedOn)
		};
		cq.select(builder.construct(CarePlanGoalBean.class, selections));
		cq.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		cq.orderBy(builder.asc(root.get(CarePlanGoal_.carePlanGoalId)));
		List<CarePlanGoalBean> concerns=entityManager.createQuery(cq).getResultList();
		return concerns;
	}
	
	/**
	 * To fetch care plan outcomes
	 * @param outcomeId
	 * @param goalId
	 * @param patientId
	 * @param encounterId
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<CarePlanOutcomeBean> fetchCarePlanOutcomes(Integer outcomeId,Integer goalId, Integer patientId, Integer encounterId,Integer episodeId,String frmDate,String toDate) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarePlanOutcomeBean> cq = builder.createQuery(CarePlanOutcomeBean.class);
		Root<CarePlanOutcome> root = cq.from(CarePlanOutcome.class);
		Join<CarePlanOutcome,EmployeeProfile> employeeJoin=root.join(CarePlanOutcome_.empProfile,JoinType.INNER);
		Join<CarePlanOutcome,CarePlanGoal> outcomeJoin=root.join(CarePlanOutcome_.carePlanGoal,JoinType.INNER);

		Selection[] selections=new Selection[]{
				root.get(CarePlanOutcome_.carePlanOutcomeId),
				root.get(CarePlanOutcome_.carePlanOutcomeGoalId),
				root.get(CarePlanOutcome_.carePlanOutcomeReviewDate),
				outcomeJoin.get(CarePlanGoal_.carePlanGoalTargetDate),
				root.get(CarePlanOutcome_.carePlanOutcomeNotes),
				root.get(CarePlanOutcome_.carePlanOutcomeProgress),
				employeeJoin.get(EmployeeProfile_.empProfileFullname),
				outcomeJoin.get(CarePlanGoal_.carePlanGoalStatus),
				outcomeJoin.get(CarePlanGoal_.carePlanGoalResultStatus)
		};
		
		List<Predicate> predicates = new ArrayList<>();
		if(patientId!=-1)
			predicates.add(builder.equal(root.get(CarePlanOutcome_.carePlanOutcomePatientId), patientId));
		if(encounterId!=-1)
			predicates.add(builder.equal(root.get(CarePlanOutcome_.carePlanOutcomeEncounterId), encounterId));
		if(goalId!=-1)
			predicates.add(builder.equal(root.get(CarePlanOutcome_.carePlanOutcomeGoalId), goalId));
		if(outcomeId!=-1)
			predicates.add(builder.equal(root.get(CarePlanOutcome_.carePlanOutcomeId), outcomeId));
		if(episodeId!=-1)
			predicates.add(builder.equal(outcomeJoin.get(CarePlanGoal_.careplanGoalEpisodeId), episodeId));
		if(frmDate!=null && !(frmDate.equalsIgnoreCase("-1"))) {
			Date fromDate = null;
			try {
				fromDate=new SimpleDateFormat("MM/dd/yyyy").parse(frmDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			predicates.add(builder.greaterThanOrEqualTo(builder.function("DATE", Date.class,root.get(CarePlanOutcome_.carePlanOutcomeCreatedOn)),fromDate));
		}
		if(toDate!=null && !(toDate.equalsIgnoreCase("-1"))) {
			Date tDate = null;
			try {
				tDate=new SimpleDateFormat("MM/dd/yyyy").parse(toDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			predicates.add(builder.lessThanOrEqualTo(builder.function("DATE", Date.class,root.get(CarePlanOutcome_.carePlanOutcomeCreatedOn)),tDate));
		}
		cq.select(builder.construct(CarePlanOutcomeBean.class, selections));
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(builder.desc(root.get(CarePlanOutcome_.carePlanOutcomeId)));
		List<CarePlanOutcomeBean> outcomes=entityManager.createQuery(cq).getResultList();
		return outcomes;
	}
	
	/**
	 * To fetch care plan outcomes
	 * @param outcomeId
	 * @param goalId
	 * @param patientId
	 * @param encounterId
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<CarePlanGoalBean> fetchCarePlanOutcomesForCDA(Integer outcomeId,Integer goalId, Integer patientId, Integer encounterId,Integer episodeId,String frmDate,String toDate) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarePlanGoalBean> cq = builder.createQuery(CarePlanGoalBean.class);
		Root<CarePlanGoal> root = cq.from(CarePlanGoal.class);
		Join<CarePlanGoal,CarePlanOutcome> outcomeJoin=root.join(CarePlanGoal_.carePlanOutcome,JoinType.LEFT);
		if(encounterId!=-1)
			outcomeJoin.on(builder.equal(builder.function("DATE", Date.class, outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeReviewDate)),getEncounterDate(encounterId)));
		else
			outcomeJoin.on(builder.equal(builder.function("DATE", Date.class, outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeReviewDate)),new Date()));
		final Subquery<Integer> subquery = cq.subquery(Integer.class);
		final Root<CarePlanOutcome> carePlanOutcome = subquery.from(CarePlanOutcome.class);
		subquery.select(builder.max(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeId)));
		subquery.groupBy(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeGoalId));
		
		List<Predicate> predicates = new ArrayList<>();
		List<Predicate> predicatesForStatus = new ArrayList<>();

		if(patientId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalPatientId), patientId));
		if(goalId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalId), goalId));
		if(episodeId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.careplanGoalEpisodeId),episodeId));
		predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalStatus),1));
		predicates.add(builder.or(root.get(CarePlanGoal_.carePlanGoalResultStatus).in(0,1),root.get(CarePlanGoal_.carePlanGoalResultStatus).isNull()));
		

		Selection[] selections=new Selection[]{
				root.get(CarePlanGoal_.carePlanGoalId),
				root.get(CarePlanGoal_.carePlanGoalPatientId),
				root.get(CarePlanGoal_.carePlanGoalEncounterId),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalConcernId),-1),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalPriority),0),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalType),-1),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalTerm),0),
				root.get(CarePlanGoal_.carePlanGoalProviderId),
				root.get(CarePlanGoal_.carePlanGoalDesc),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalCode),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalCodeDescription),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalCodeOperator),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalValue),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalUnit),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalStatus),0),
				root.get(CarePlanGoal_.carePlanGoalTargetDate),
				root.get(CarePlanGoal_.carePlanGoalNextReviewDate),
				root.get(CarePlanGoal_.carePlanGoalNotes),
				root.get(CarePlanGoal_.carePlanGoalFrom),
				builder.coalesce(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeProgress),0),
				root.get(CarePlanGoal_.carePlanGoalResultStatus),
				root.get(CarePlanGoal_.careplanGoalEpisodeId),
				root.get(CarePlanGoal_.carePlanGoalOrder),
				root.get(CarePlanGoal_.carePlanGoalValueOne),
				outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeCreatedBy),
				outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeCreatedOn)
		};
		cq.select(builder.construct(CarePlanGoalBean.class, selections));
		cq.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		cq.orderBy(builder.asc(root.get(CarePlanGoal_.carePlanGoalId)));
		List<CarePlanGoalBean> concerns=entityManager.createQuery(cq).getResultList();
		return concerns;
	}
	
	/**
	 * To save care plan concern from given Bean
	 * @param CarePlanConcernBean
	 * @return List
	 */
	@Override
	public List<CarePlanConcernBean> saveCarePlanConcern(CarePlanConcernBean carePlanConcernJSON) {
		CarePlanConcern carePlanConcern=new CarePlanConcern();
		if(carePlanConcernJSON.getConcernId()!=-1)
			carePlanConcern.setCarePlanConcernId(carePlanConcernJSON.getConcernId());
		carePlanConcern.setCarePlanConcernCategoryId(carePlanConcernJSON.getConcernCategoryId());
		carePlanConcern.setCarePlanConcernPatientId(carePlanConcernJSON.getConcernPatientId());
		carePlanConcern.setCarePlanConcernProviderId(carePlanConcernJSON.getConcernProviderId());
		carePlanConcern.setCarePlanConcernType(carePlanConcernJSON.getConcernType());
		carePlanConcern.setCarePlanConcernCode(carePlanConcernJSON.getConcernCode());
		carePlanConcern.setCarePlanConcernCodeSystem(carePlanConcernJSON.getConcernCodeSystem());
		carePlanConcern.setCarePlanConcernCodeSystemName(carePlanConcernJSON.getConcernCodeSystemName());
		carePlanConcern.setCarePlanConcernCodeDesc(carePlanConcernJSON.getConcernCodeDesc());
		carePlanConcern.setCarePlanConcernPriority(carePlanConcernJSON.getConcernPriority());
		carePlanConcern.setCarePlanConcernValue(carePlanConcernJSON.getConcernValue());
		carePlanConcern.setCarePlanConcernUnit(carePlanConcernJSON.getConcernUnit());
		carePlanConcern.setCarePlanConcernDesc(carePlanConcernJSON.getConcernDesc());
		carePlanConcern.setCarePlanConcernNotes(carePlanConcernJSON.getConcernNotes());
		carePlanConcern.setCarePlanConcernStatus(carePlanConcernJSON.getConcernStatus());
		carePlanConcern.setCarePlanConcernStatusUpdatedDate(carePlanConcernRepository.findCurrentTimeStamp());
		carePlanConcern.setCarePlanConcernCreatedBy(carePlanConcernJSON.getConcernCreatedBy());
		carePlanConcern.setCarePlanConcernModifiedBy(carePlanConcernJSON.getConcernModifiedBy());
		carePlanConcern.setCarePlanConcernCreatedOn(carePlanConcernRepository.findCurrentTimeStamp());
		carePlanConcern.setCareplanConcernEpisodeId(carePlanConcernJSON.getEpisodeId());
		carePlanConcern.setCarePlanConcernFrom(carePlanConcernJSON.getConcernFrom());
		carePlanConcern.setCareplanConcernEncounterId(carePlanConcernJSON.getEncounterId());
		carePlanConcern.setCarePlanConcernModifiedOn(carePlanConcernRepository.findCurrentTimeStamp());
		carePlanConcernRepository.saveAndFlush(carePlanConcern);
		List<CarePlanConcernBean> carePlanConcerns=fetchCarePlanConcerns(-1,carePlanConcernJSON.getConcernPatientId(),-1,carePlanConcernJSON.getEpisodeId(),carePlanConcernJSON.getEncounterId(),"-1","-1");
		return carePlanConcerns;
	}

	/**
	 * To save care plan goal from given Bean
	 * @param CarePlanGoalBean
	 * @return List
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<CarePlanGoalBean> saveCarePlanGoal(CarePlanGoalBean carePlanGoalData) {
		
		CarePlanGoal carePlanGoal=new CarePlanGoal();
		if(carePlanGoalData.getCarePlanGoalId()!=-1) {
			carePlanGoal.setCarePlanGoalId(carePlanGoalData.getCarePlanGoalId());
			carePlanGoal = carePlanGoalRepository.findOne(carePlanGoal.getCarePlanGoalId());
		}
		else {
			carePlanGoal.setCarePlanGoalCreatedBy(carePlanGoalData.getCarePlanGoalCreatedBy());
			carePlanGoal.setCarePlanGoalCreatedOn(carePlanGoalRepository.findCurrentTimeStamp());
		}
		carePlanGoal.setCarePlanGoalPatientId(carePlanGoalData.getCarePlanGoalPatientId());
		carePlanGoal.setCarePlanGoalEncounterId(carePlanGoalData.getCarePlanGoalEncounterId());
		carePlanGoal.setCarePlanGoalConcernId(carePlanGoalData.getCarePlanGoalConcernId());
		carePlanGoal.setCarePlanGoalParentId(-1);
		carePlanGoal.setCarePlanGoalFrom(carePlanGoalData.getCarePlanGoalFrom());
		carePlanGoal.setCarePlanGoalPriority(carePlanGoalData.getCarePlanGoalPriority());
		carePlanGoal.setCarePlanGoalType(carePlanGoalData.getCarePlanGoalType());
		carePlanGoal.setCarePlanGoalTerm(carePlanGoalData.getCarePlanGoalTerm());
		carePlanGoal.setCarePlanGoalProviderId(carePlanGoalData.getCarePlanGoalProviderId());
		carePlanGoal.setCarePlanGoalDesc(carePlanGoalData.getCarePlanGoalDesc());
		carePlanGoal.setCarePlanGoalCode(carePlanGoalData.getCarePlanGoalCode());
		carePlanGoal.setCarePlanGoalCodeDescription(carePlanGoalData.getCarePlanGoalCodeDescription());
		carePlanGoal.setCarePlanGoalCodeSystem("2.16.840.1.113883.6.1");
		carePlanGoal.setCarePlanGoalCodeSystemName("LOINC");
		carePlanGoal.setCarePlanGoalCodeOperator(carePlanGoalData.getCarePlanGoalCodeOperator());
		carePlanGoal.setCarePlanGoalValue(carePlanGoalData.getCarePlanGoalValue());
		carePlanGoal.setCarePlanGoalUnit(carePlanGoalData.getCarePlanGoalUnit());		
		carePlanGoal.setCarePlanGoalStatus(carePlanGoalData.getCarePlanGoalStatus());
		carePlanGoal.setCareplanGoalEpisodeId(carePlanGoalData.getEpisodeId());
		carePlanGoal.setCareplanGoalResultStatus(carePlanGoalData.getCarePlanGoalResultStatus());
		carePlanGoal.setCarePlanGoalOrder(carePlanGoalData.getCarePlanGoalOrder());
		carePlanGoal.setCarePlanGoalValueOne(carePlanGoalData.getCarePlanGoalValueOne());


		try{
			SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
			if(carePlanGoalData.getCarePlanGoalTargetDate()!=null &carePlanGoalData.getCarePlanGoalTargetDate()!="")
			{
				Date targetDateString=new Date(carePlanGoalData.getCarePlanGoalTargetDate());
				Date targetDate = new Date(ft.format(targetDateString));
				carePlanGoal.setCarePlanGoalTargetDate((new Timestamp(targetDate.getTime())));
			}
			else
				carePlanGoal.setCarePlanGoalTargetDate(null);
			if(carePlanGoalData.getCarePlanGoalNextReviewDate()!=null &carePlanGoalData.getCarePlanGoalNextReviewDate()!="")
			{
				Date reviewDateString=new Date(carePlanGoalData.getCarePlanGoalNextReviewDate());
				Date reviewDate = new Date(ft.format(reviewDateString));
				carePlanGoal.setCarePlanGoalNextReviewDate(new Timestamp(reviewDate.getTime()));
			}
			else
				carePlanGoal.setCarePlanGoalNextReviewDate(null);
		}
		catch(Exception e){
		}
		carePlanGoal.setCarePlanGoalNotes(carePlanGoalData.getCarePlanGoalNotes());

		carePlanGoal.setCarePlanGoalModifiedBy(carePlanGoalData.getCarePlanGoalModifiedBy());
		carePlanGoal.setCarePlanGoalModifiedOn(carePlanGoalRepository.findCurrentTimeStamp());
		carePlanGoalRepository.saveAndFlush(carePlanGoal);
	
		List<CarePlanGoalBean> carePlanGoals=fetchCarePlanGoalBean(-1,-1,carePlanGoalData.getCarePlanGoalPatientId(),carePlanGoalData.getCarePlanGoalEncounterId(),carePlanGoalData.getEpisodeId());
		return carePlanGoals;
	}

	/**
	 * To save outcome of particular care plan goal
	 * @param goalId
	 * @param providerId
	 * @param patientId
	 * @param encounterId
	 * @param progress
	 * @param reviewDate
	 * @param targetDate
	 * @param notes
	 * @param status
	 * @return List
	 */
	@SuppressWarnings("deprecation")
	public List<CarePlanGoalBean>  saveCarePlanOutcomes(Integer goalId,Integer providerId,Integer patientId,Integer encounterId,Integer progress,String reviewDate,String targetDate,String notes,Integer status,Integer episodeId,Integer goalAssisStatus,Integer goalLevelStatus,Boolean targetedGoal) {
		CarePlanOutcome carePlanOutcome=new CarePlanOutcome();
		carePlanOutcome.setCarePlanOutcomeGoalId(goalId);
		carePlanOutcome.setCarePlanOutcomePatientId(patientId);
		carePlanOutcome.setCarePlanOutcomeEncounterId(encounterId);
		carePlanOutcome.setCarePlanOutcomeProviderId(providerId);
		carePlanOutcome.setCarePlanOutcomeTargetedGoal(targetedGoal);

		if(progress!=-1)
			carePlanOutcome.setCarePlanOutcomeProgress(progress);
		
		SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
		if(!reviewDate.equalsIgnoreCase("-1")) {
			try{
				Date reviewDateString=new Date(reviewDate);
				Date reviewDateToSave = new Date(ft.format(reviewDateString));
				carePlanOutcome.setCarePlanOutcomeReviewDate((new Timestamp(reviewDateToSave.getTime())));
			}
			catch(Exception e){}
		}
		else {
			if(encounterId!=-1)
				carePlanOutcome.setCarePlanOutcomeReviewDate(new Timestamp(new java.sql.Date(getEncounterDate(encounterId).getTime()).getTime()));
			else
				carePlanOutcome.setCarePlanOutcomeReviewDate(carePlanOutcomeRepository.findCurrentTimeStamp());
		}
		carePlanOutcome.setCarePlanOutcomeCreatedBy(providerId);
		carePlanOutcome.setCarePlanOutcomeModifiedBy(providerId);
		carePlanOutcome.setCarePlanOutcomeCreatedOn(carePlanOutcomeRepository.findCurrentTimeStamp());
		carePlanOutcome.setCarePlanOutcomeModifiedOn(carePlanOutcomeRepository.findCurrentTimeStamp());
		carePlanOutcome.setCarePlanOutcomeNotes(notes);
		
		if(goalId!=-1){		
			try{
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaUpdate<CarePlanGoal> cu = cb.createCriteriaUpdate(CarePlanGoal.class);
				Root<CarePlanGoal> rootCriteria = cu.from(CarePlanGoal.class);
				if(!targetDate.equalsIgnoreCase("-1")) {
					Date targetDateString=new Date(targetDate);
					Date targetDateToSave = new Date(ft.format(targetDateString));
					cu.set(rootCriteria.get(CarePlanGoal_.carePlanGoalTargetDate),new Timestamp(targetDateToSave.getTime()) );
				}
				cu.set(rootCriteria.get(CarePlanGoal_.carePlanGoalResultStatus), status);
				if(goalAssisStatus!=-1)
					cu.set(rootCriteria.get(CarePlanGoal_.carePlanGoalAssistanceStatus), goalAssisStatus);
				if(goalLevelStatus!=-1)
					cu.set(rootCriteria.get(CarePlanGoal_.carePlanGoalLevelStatus), goalLevelStatus);
				cu.set(rootCriteria.get(CarePlanGoal_.carePlanGoalModifiedBy), providerId);
				cu.set(rootCriteria.get(CarePlanGoal_.careplanGoalMasteredDate), carePlanGoalRepository.findCurrentTimeStamp());
				cu.set(rootCriteria.get(CarePlanGoal_.carePlanGoalModifiedOn), carePlanGoalRepository.findCurrentTimeStamp());
				cu.where(cb.equal(rootCriteria.get(CarePlanGoal_.carePlanGoalId),goalId),
						cb.equal(rootCriteria.get(CarePlanGoal_.carePlanGoalPatientId),patientId));
				this.entityManager.createQuery(cu).executeUpdate();
			}
		catch(Exception e){}
		}	
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarePlanOutcome> carePlanQuery = builder.createQuery(CarePlanOutcome.class);
		Root<CarePlanOutcome> outcomeJoin = carePlanQuery.from(CarePlanOutcome.class);
		/*Join<CarePlanGoal,CarePlanOutcome> outcomeJoin=carePlanRoot.join(CarePlanGoal_.carePlanOutcome,JoinType.INNER);
		outcomeJoin.on(builder.equal(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeEncounterId),encounterId));
		*/List<Predicate> predicatesForGoal = new ArrayList<>();
		/*	
		predicatesForGoal.add(builder.equal(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeEncounterId),encounterId));*/
		predicatesForGoal.add(builder.equal(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomePatientId), patientId));
		predicatesForGoal.add(builder.equal(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeGoalId), goalId));
		if(encounterId!=-1) {
			predicatesForGoal.add(builder.equal(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeEncounterId),encounterId));
			//predicatesForGoal.add(builder.equal(builder.function("DATE", Date.class, outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeReviewDate)),getEncounterDate(encounterId)));
		}
		else  {
			predicatesForGoal.add(builder.equal(builder.function("DATE", Date.class, outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeReviewDate)),new Date()));
		}
		predicatesForGoal.add(builder.equal(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeProviderId),providerId));

		carePlanQuery.where(builder.and(predicatesForGoal.toArray(new Predicate[predicatesForGoal.size()])));
		List<CarePlanOutcome> goals=entityManager.createQuery(carePlanQuery).getResultList();

		if(goals.size()>0) {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaUpdate<CarePlanOutcome> cu = cb.createCriteriaUpdate(CarePlanOutcome.class);
			Root<CarePlanOutcome> rootCriteria = cu.from(CarePlanOutcome.class);
			if(progress!=-1)
				cu.set(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeProgress),progress);
			cu.set(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeEncounterId),encounterId);
			cu.set(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeModifiedBy),providerId);
			cu.set(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeModifiedOn),carePlanOutcomeRepository.findCurrentTimeStamp());
			cu.set(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeTargetedGoal),targetedGoal);
			if(!reviewDate.equalsIgnoreCase("-1")) {
				try{
					Date reviewDateString=new Date(reviewDate);
					Date reviewDateToSave = new Date(ft.format(reviewDateString));
					cu.set(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeReviewDate),new Timestamp(reviewDateToSave.getTime()));
				}
				catch(Exception e){}
			}
			else {
				cu.set(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeReviewDate),new Timestamp(getEncounterDate(encounterId).getTime()));
			}
			if(encounterId!=-1) {
				cu.where(cb.equal(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeGoalId),goalId),
						cb.equal(rootCriteria.get(CarePlanOutcome_.carePlanOutcomePatientId),patientId),
						cb.equal(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeEncounterId),encounterId));
						//cb.equal(builder.function("DATE", Date.class, rootCriteria.get(CarePlanOutcome_.carePlanOutcomeReviewDate)),getEncounterDate(encounterId)));
				
			}
			else {
				cu.where(cb.equal(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeGoalId),goalId),
						cb.equal(rootCriteria.get(CarePlanOutcome_.carePlanOutcomePatientId),patientId),
						cb.equal(builder.function("DATE", Date.class, rootCriteria.get(CarePlanOutcome_.carePlanOutcomeReviewDate)),new Date()));
			}
			this.entityManager.createQuery(cu).executeUpdate();
		}
		else {
			carePlanOutcomeRepository.saveAndFlush(carePlanOutcome);
		}
		List<CarePlanGoalBean> carePlanGoals=fetchCarePlanGoalBean(-1,-1,patientId,encounterId,episodeId);
		return carePlanGoals;
	}


	/**
	 * To frame initial data for Care plan tab with concerns,goals,outcomes of particular patient
	 * @param patientId
	 * @param encounterId
	 * @return List
	 */
	@Override
	public Map<String, Object> getCarePlanInitialData(Integer patientId,
			Integer encounterId,Integer episodeId, Integer episodeTypeId, Integer previousEpisodeId) {
		Map<String,Object> listsMap=new HashMap<String,Object>();
		listsMap.put("concernsList", fetchCarePlanConcerns(-1,patientId,-1,episodeId,encounterId,"-1","-1"));
		listsMap.put("goalsList", fetchCarePlanGoalBean(-1,-1,patientId,encounterId,episodeId));
		listsMap.put("interventionsList", fetchInterventionPlanData(-1,-1,-1,patientId,encounterId,-1));
		listsMap.put("unitsList",getUnitsOfMeasures() );
		listsMap.put("vitalsList", getVitalParameters());
		listsMap.put("shortcutsList", fetchCarePlanShortcuts(episodeTypeId,"-1","-1"));
		listsMap.put("previousVisitShortcutsList", fetchPreviousCarePlanGoalShortcuts(patientId,episodeTypeId,previousEpisodeId));
		listsMap.put("employeeList", fetchEmployeeList());
		listsMap.put("lastVisitProgress",getLastVisitProgressStatus(patientId,encounterId,episodeId));
		listsMap.put("recommIntervention", fetchRecommIntervention(patientId,encounterId,episodeId,"-1","-1"));
		listsMap.put("reviewStatus", fetchCarePlanLog(patientId,new ArrayList<Integer>()));
		listsMap.put("healthStatus", getCarePlanStatus(patientId, encounterId, episodeId));
		listsMap.put("interventionShortcuts", fetchCarePlanInterventionShortcuts());
		//listsMap.put("getCarePlanSummary",getCarePlanSummaryData(patientId,episodeId,encounterId));
		return listsMap;
	}
	
	/**
	 * To fetch Intervention data
	 * @param goalId
	 * @param concernId
	 * @param categoryId
	 * @param patientId
	 * @param encounterId
	 * @return List
	 *//*
	@SuppressWarnings("rawtypes")
	public List<CarePlanInterventionBean> fetchInterventionData(Integer goalId,Integer concernId,
			Integer categoryId, Integer patientId, Integer encounterId){
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarePlanInterventionBean> cq = builder.createQuery(CarePlanInterventionBean.class);
		Root<CarePlanIntervention> root = cq.from(CarePlanIntervention.class);

		List<Predicate> predicates = new ArrayList<>();
		if(patientId!=-1)
			predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionPatientId), patientId));
		if(encounterId!=-1)
			predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionEncounterId), encounterId));
		if(concernId!=-1)
			predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionConcernId), concernId));
		if(goalId!=-1)
			predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionGoalId), goalId));
		if(categoryId!=-1)
			predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionCategoryId), categoryId));

		Selection[] selectedColumns = new Selection[]{
				root.get(CarePlanIntervention_.carePlanInterventionId),
				root.get(CarePlanIntervention_.carePlanInterventionPatientId),
				root.get(CarePlanIntervention_.carePlanInterventionEncounterId),
				root.get(CarePlanIntervention_.carePlanInterventionConcernId),
				root.get(CarePlanIntervention_.carePlanInterventionGoalId),
				root.get(CarePlanIntervention_.carePlanInterventionCategoryId),
				root.get(CarePlanIntervention_.carePlanInterventionDescription),
				root.get(CarePlanIntervention_.carePlanInterventionCode),
				root.get(CarePlanIntervention_.carePlanInterventionCodeName),
				root.get(CarePlanIntervention_.carePlanInterventionProblemCode),
				root.get(CarePlanIntervention_.carePlanInterventionProblemCodeSystem),
				root.get(CarePlanIntervention_.carePlanInterventionProblemCodeSystemDescription),
				root.get(CarePlanIntervention_.carePlanInterventionStatus),
				root.get(CarePlanIntervention_.carePlanInterventionOrderedOn),
				root.get(CarePlanIntervention_.carePlanInterventionPerformedOn),
				root.get(CarePlanIntervention_.carePlanInterventionNotDoneType),
				root.get(CarePlanIntervention_.carePlanInterventionNotDoneDescription),
				root.get(CarePlanIntervention_.carePlanInterventionNotDoneCode),
				root.get(CarePlanIntervention_.carePlanInterventionNotDoneCodeSystem),
				root.get(CarePlanIntervention_.carePlanInterventionNotes)
		};

		cq.select(builder.construct(CarePlanInterventionBean.class, selectedColumns));
		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		List<CarePlanInterventionBean> interventions=entityManager.createQuery(cq).getResultList();
		return interventions;
	}*/

	/**
	 * To get vital elements of particular encounterId
	 * @param encounterId
	 * @return List
	 */
	private List<Object[]> getVitals(Integer patientID) {
		List<String> GwIDs= new ArrayList<String>();
		GwIDs.addAll(Arrays.asList("0000200200100026000", "0000200200100029000", "0000200200100028000", "0000200200100034000", "0000200200100030000", "0000200200100032000", "0000200200100031000", "0000200200100037000", "0000200200100036000", "0000200200100033000", "0000200200100076000", "0000200200100075000", "0000200200100035000","0000200200100027000","0000200200100023000","0000200200100024000","0000200200100025000","0000200200100020000","0000200200100038000","0000200200100278000","0000200200100022000"));
		CriteriaBuilder builder= entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<PatientClinicalElements> root= query.from(PatientClinicalElements.class);
		Join<PatientClinicalElements, Encounter> encJoin= root.join(PatientClinicalElements_.encounter, JoinType.INNER);
		Join<PatientClinicalElements, VitalsParameter> vitalJoin= root.join(PatientClinicalElements_.vitalsParameter, JoinType.INNER);
		Join<PatientClinicalElements, ClinicalElements> clinicalJoin= root.join(PatientClinicalElements_.clinicalElement, JoinType.INNER);
		Join<VitalsParameter, UnitsOfMeasure> unitsJoin= vitalJoin.join(VitalsParameter_.unitsOfMeasureTable, JoinType.LEFT);
/*		Join<VitalsParameter, VitalGroup> groupJoin= vitalJoin.join(VitalsParameter_.vitalGroup, JoinType.INNER);
*/		Join<ClinicalElements, ClinicalElementsOptions> optionsJoin= clinicalJoin.join(ClinicalElements_.clinicalElementsOptions, JoinType.LEFT);
		Join<ClinicalElements, ClinicalTextMapping> textJoin= clinicalJoin.join(ClinicalElements_.clinicalTextMappings, JoinType.LEFT);
		Join<ClinicalElements, CNMCodeSystem> cnmJoin= clinicalJoin.join(ClinicalElements_.cnmCodeSystems, JoinType.LEFT);
		
		query.multiselect(vitalJoin.get(VitalsParameter_.vitalsParameterName),
				root.get(PatientClinicalElements_.patientClinicalElementsGwid),
				root.get(PatientClinicalElements_.patientClinicalElementsValue),
				unitsJoin.get(UnitsOfMeasure_.unitsOfMeasureCode),
				optionsJoin.get(ClinicalElementsOptions_.clinicalElementsOptionsName),
				clinicalJoin.get(ClinicalElements_.clinicalElementsDatatype),
				textJoin.get(ClinicalTextMapping_.clinicalTextMappingAssociatedElement),
				builder.coalesce(cnmJoin.get(CNMCodeSystem_.cnmCodeSystemCode),""),
				builder.coalesce(cnmJoin.get(CNMCodeSystem_.cnmCodeSystemOid),""));

		query.where(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientID),
				builder.or(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsValue),optionsJoin.get(ClinicalElementsOptions_.clinicalElementsOptionsValue)),
						builder.isNull(optionsJoin.get(ClinicalElementsOptions_.clinicalElementsOptionsGwid))),
						root.get(PatientClinicalElements_.patientClinicalElementsGwid).in(GwIDs));

		/*query.orderBy(builder.asc(groupJoin.get(VitalGroup_.vitalGroupOrderby)),
				builder.asc(vitalJoin.get(VitalsParameter_.vitalsParameterId)));
*/

		query.orderBy(builder.asc(root.get(PatientClinicalElements_.patientClinicalElementsGwid)),
				builder.desc(encJoin.get(Encounter_.encounterDate)));

		List<Object[]> result= entityManager.createQuery(query).getResultList();

		return result;
	}

	/**
	 * To get units of vital elements
	 * @return List
	 */
	private List<UnitsOfMeasure> getUnitsOfMeasures(){
		CriteriaBuilder builder= entityManager.getCriteriaBuilder();
		CriteriaQuery<UnitsOfMeasure> query= builder.createQuery(UnitsOfMeasure.class);
		Root<UnitsOfMeasure> root = query.from(UnitsOfMeasure.class);
		query.orderBy(builder.asc(root.get(UnitsOfMeasure_.unitsOfMeasureCode)));
		List<UnitsOfMeasure> units=entityManager.createQuery(query).getResultList();
		return units;
	}

	/**
	 * To get vital parameters
	 * @param vitalsList
	 * @return List
	 */
	private List<Object> getVitalParameters(){
		List<String> GwIDs= new ArrayList<String>();
		GwIDs.addAll(Arrays.asList("0000200200100075000","0000200200100076000","0000200200100038000","0000200200100025000","0000200200100020000","0000200200100023000","0000200200100024000","0000200200100278000"));
		CriteriaBuilder builder= entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<VitalsParameter> root = query.from(VitalsParameter.class);
		Join<VitalsParameter, CNMCodeSystem> cnmJoin= root.join(VitalsParameter_.cnmCodeSystem, JoinType.LEFT);
/*		Join<VitalsParameter, UnitsOfMeasure> unitsJoin= root.join(VitalsParameter_.unitsOfMeasureTable, JoinType.LEFT);
*/		query.multiselect(root.get(VitalsParameter_.vitalsParameterName),
				builder.coalesce(cnmJoin.get(CNMCodeSystem_.cnmCodeSystemCode),""),
				builder.coalesce(cnmJoin.get(CNMCodeSystem_.cnmCodeSystemOid),""));
		query.where(builder.equal(root.get(VitalsParameter_.vitalsParameterIsactive),true),
		root.get(VitalsParameter_.vitalsParameterGwId).in(GwIDs),builder.equal(cnmJoin.get(CNMCodeSystem_.cnmCodeSystemOid),"2.16.840.1.113883.6.1"));
		query.orderBy(builder.asc(root.get(VitalsParameter_.vitalsParameterName)));
		List<Object[]> vitals=entityManager.createQuery(query).getResultList();
		List<Object>  parsedVitalParameters=new ArrayList<Object>();
	    for(Object[]  vitalValues:vitals){
	    	Map<String, String> parsedObject=new HashMap<String, String>();
	    	try {
				parsedObject.put("vitalsParameterName", vitalValues[0].toString());
				parsedObject.put("cnmCodeSystemCode", vitalValues[1].toString());	
				parsedObject.put("cnmCodeSystemOid", vitalValues[2].toString());	
				parsedVitalParameters.add(parsedObject);
			} catch (Exception e) {
			}
	    }
		return parsedVitalParameters;
	}
	
	/**
	 * To parse vitals to JSON
	 * @param vitalsList
	 * @return
	 * @throws JSONException
	 */
	private JSONArray parseVitals(List<Object[]> vitalsList) throws JSONException {
		JSONArray vitals= new JSONArray();
		String encBasedGwid="";
		long bpCount=0;
		for(int i=0; i<vitalsList.size(); i++){
			JSONObject vital= new JSONObject();
			Object[] vitalsObj= vitalsList.get(i);
			if(!encBasedGwid.equalsIgnoreCase(vitalsObj[1].toString())) {
				if((!(vitalsObj[0].toString().toLowerCase().contains("systolic")) && !(vitalsObj[0].toString().toLowerCase().contains("diastolic"))) || (bpCount==0)) {
					String name= vitalsObj[0].toString();
					String gwid= vitalsObj[1].toString();
					encBasedGwid=gwid;
					String value= vitalsObj[2].toString();
					String units= vitalsObj[3].toString();
					String optionname= vitalsObj[4]!= null? vitalsObj[4].toString(): "";
					int datatype= Integer.parseInt(vitalsObj[5].toString());
					String assgwid= vitalsObj[6]!= null? vitalsObj[6].toString(): "";
					String code= vitalsObj[7].toString();
					String codeSystem= vitalsObj[8].toString();
					if(datatype == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION){
						value= optionname;
					}else if(datatype == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION){
						if(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("t"))
							value= "Yes";
						else
							value= "No";
					}
					else if(name.toLowerCase().contains("systolic") && bpCount==0){
						for(int j=0; j<vitalsList.size(); j++){
							Object[] tmpvitalsObj= vitalsList.get(j);
							String tmpassgwid= tmpvitalsObj[1]!= null? tmpvitalsObj[1].toString(): "";
							if(assgwid.equals(tmpassgwid)){
								String tmpvalue= tmpvitalsObj[2].toString();
								value= value+"/"+tmpvalue;
								break;
							}
						}
					}else if(name.equalsIgnoreCase("height")){
						String dispUnit= getDisplayUnit("0000200200100023000");
						value= HUtil.heightConversion(value, dispUnit);
					}else if(name.equalsIgnoreCase("weight")){
						String dispUnit= getDisplayUnit("0000200200100024000");
						value= HUtil.weightConversion(value, dispUnit);
					}

					if(!name.toLowerCase().contains("diastolic")){
						if(!name.equalsIgnoreCase("Height") && !name.equalsIgnoreCase("Weight")){
							if(name.contains("systolic")  && bpCount==0){
								bpCount++;
								name = name.substring(0,name.indexOf("systolic"))+"BP (mmHg)";
							}else if(name.contains("Systolic")  && bpCount==0){
								bpCount++;
								name = name.substring(0,name.indexOf("Systolic"))+"BP (mmHg)";
							}
							if(value.indexOf(".")!=-1 && value.indexOf(",")==-1){
								double doubleValue = Double.parseDouble(value);
								DecimalFormat f = new DecimalFormat("##.00");
								value = f.format(doubleValue).toString();
							}
						}
						vital.put("Unit", units);
						vital.put("DisplayName", name);
						vital.put("Value", value);
						vital.put("Code", code);
						vital.put("CodeSystem", codeSystem);
						vitals.put(vital);
				}
				}
			}
		}
		return vitals;
	}
	
	/**
	 * To get vital display units
	 * @param gwid
	 * @return String
	 */
	private String getDisplayUnit(String gwid) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<VitalsParameter> root = cq.from(VitalsParameter.class);
		cq.select(builder.coalesce(root.get(VitalsParameter_.vitalsParameterDisplayUnit),1));
		cq.where(builder.equal(root.get(VitalsParameter_.vitalsParameterGwId), gwid));
		List<Object> resultList = entityManager.createQuery(cq).getResultList();
		String unit= "1";
		if(resultList.size()>0)
			unit= resultList.get(0).toString();
		return unit;
	}

	/**
	 * To frame vitals with given patientId, encounterId
	 * @param patientId
	 * @param encounterId
	 * @return String
	 */
	public String getVitals(Integer patientId,Integer encounterId){
		List<Object[]> vitals= getVitals(patientId);
		JSONArray vitalsArr=null;
		try {
			vitalsArr = parseVitals(vitals);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return vitalsArr.toString();
	}

	
	/**
	 * To fetch care plan shortcuts
	 * @return List
	 */
	@Override
	public List<Object> fetchCarePlanShortcuts(Integer categoryId,String searchType,String searchStr){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<CarePlanGoalShortcut> root=cq.from(CarePlanGoalShortcut.class);
	    Join<CarePlanGoalShortcut,CarePlanConcernShortcut> concernJoin=root.join(CarePlanGoalShortcut_.carePlanConcernShortcut,JoinType.INNER);
		cq.multiselect(root.get(CarePlanGoalShortcut_.carePlanGoalShortcutId).alias("carePlanGoalShortcutId"),
		root.get(CarePlanGoalShortcut_.carePlanGoalShortcutDesc).alias("carePlanGoalShortcutDesc"),
		root.get(CarePlanGoalShortcut_.carePlanGoalShortcutTerm).alias("carePlanGoalShortcutTerm"),
		root.get(CarePlanGoalShortcut_.carePlanGoalShortcutPriority).alias("carePlanGoalShortcutPriority"),
		concernJoin.get(CarePlanConcernShortcut_.carePlanConcernShortcutId).alias("carePlanConcernShortcutId"),
		concernJoin.get(CarePlanConcernShortcut_.carePlanConcernShortcutDesc).alias("carePlanConcernShortcutDesc")	
		);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(CarePlanGoalShortcut_.carePlanGoalShortcutStatus),1));
		predicates.add(builder.equal(concernJoin.get(CarePlanConcernShortcut_.carePlanConcernShortcutCategoryId),categoryId));
		if(!(searchStr.equalsIgnoreCase("-1"))) {
			searchStr = "%"+searchStr+"%";
			if(searchType.equalsIgnoreCase("concern")) {
				predicates.add(builder.like(builder.lower(concernJoin.get(CarePlanConcernShortcut_.carePlanConcernShortcutDesc)),searchStr.toLowerCase()));
			}else {
				predicates.add(builder.like(builder.lower(root.get(CarePlanGoalShortcut_.carePlanGoalShortcutDesc)),searchStr.toLowerCase()));
			}
		}
		cq.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		cq.orderBy(builder.asc(concernJoin. get(CarePlanConcernShortcut_.carePlanConcernShortcutDesc)),builder.asc(root.get(CarePlanGoalShortcut_.carePlanGoalShortcutDesc)));
	    List<Object[]> shortcuts=entityManager.createQuery(cq).getResultList();
	    List<Object>  parsedShrotcuts=new ArrayList<Object>();
	    for(Object[]  shortcut:shortcuts){
	    	Map<String, String> parsedObject=new HashMap<String, String>();
	    	try {
				parsedObject.put("carePlanGoalShortcutId", shortcut[0].toString());
				parsedObject.put("carePlanGoalShortcutDesc", shortcut[1].toString());	
				parsedObject.put("carePlanGoalShortcutTerm", shortcut[2].toString());	
				parsedObject.put("carePlanGoalShortcutPriority", shortcut[3].toString());
				parsedObject.put("carePlanConcernShortcutId", shortcut[4].toString());
				parsedObject.put("carePlanConcernShortcutDesc", shortcut[5].toString());
				parsedShrotcuts.add(parsedObject);
			} catch (Exception e) {
			}
	    }
	  
		
		return parsedShrotcuts;
	}

	/**
	 * To import care plan shortcuts for given patient details
	 * @param patientId
	 * @param encounterId
	 * @param shortcutIDs
	 * @param providerId
	 * @return Map
	 * @throws ParseException 
	 */
	
	@Override
	public Map<String, Object> importCarePlanShortcuts(Integer patientId,
			Integer encounterId, String shortcutIDs,Integer providerId,Integer episodeId,Integer shortcutTerm,Integer categoryId,Integer previousEpisodeId,Integer summaryMode) throws ParseException {
		List<String> shortcutIdList=Arrays.asList(shortcutIDs.split(","));
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		for(String shortcutId:shortcutIdList){
			CriteriaQuery<CarePlanGoalShortcut> cq = builder.createQuery(CarePlanGoalShortcut.class);
			Root<CarePlanGoalShortcut> root=cq.from(CarePlanGoalShortcut.class);
			cq.where(builder.equal(root.get(CarePlanGoalShortcut_.carePlanGoalShortcutId),shortcutId));
			CarePlanGoalShortcut shortcut=(CarePlanGoalShortcut) entityManager.createQuery(cq).getResultList().get(0);
			Integer concernShortcutId=-1;
			if(shortcut.getCarePlanGoalShortcutConcernShortcutId()!=-1){
				CriteriaQuery<CarePlanConcernShortcut> concernQuery = builder.createQuery(CarePlanConcernShortcut.class);
				Root<CarePlanConcernShortcut> rootConcern=concernQuery.from(CarePlanConcernShortcut.class);
				concernQuery.where(builder.equal(rootConcern.get(CarePlanConcernShortcut_.carePlanConcernShortcutId),shortcut.getCarePlanGoalShortcutConcernShortcutId()));
				CarePlanConcernShortcut concernShortcut=(CarePlanConcernShortcut) entityManager.createQuery(concernQuery).getResultList().get(0);

				List<Predicate> predicates = new ArrayList<>();
				CriteriaQuery<CarePlanConcern> concernPatientQuery = builder.createQuery(CarePlanConcern.class);
				Root<CarePlanConcern> rootConcernPatient=concernPatientQuery.from(CarePlanConcern.class);
				predicates.add(builder.equal(rootConcernPatient.get(CarePlanConcern_.carePlanConcernPatientId),patientId));
				predicates.add(builder.equal(rootConcernPatient.get(CarePlanConcern_.carePlanConcernCategoryId),categoryId));
				if(episodeId!=-1)
					predicates.add(builder.equal(rootConcernPatient.get(CarePlanConcern_.careplanConcernEpisodeId),episodeId));
				predicates.add(builder.like(rootConcernPatient.get(CarePlanConcern_.carePlanConcernDesc),concernShortcut.getCarePlanConcernShortcutDesc()));
				concernPatientQuery.where(predicates.toArray(new Predicate[predicates.size()]));
				List<CarePlanConcern> concernPatient=entityManager.createQuery(concernPatientQuery).getResultList();
				if(concernPatient.size()>0)
				{
					concernShortcutId=concernPatient.get(0).getCarePlanConcernId();
				}
				else{		
					CarePlanConcern carePlanConcern=new CarePlanConcern();
					carePlanConcern.setCarePlanConcernCategoryId(concernShortcut.getCarePlanConcernShortcutCategoryId());
					carePlanConcern.setCarePlanConcernPatientId(patientId);
					carePlanConcern.setCarePlanConcernProviderId(providerId);
					carePlanConcern.setCarePlanConcernType(concernShortcut.getCarePlanConcernShortcutType());
					carePlanConcern.setCarePlanConcernCode(concernShortcut.getCarePlanConcernShortcutCode());
					carePlanConcern.setCarePlanConcernCodeSystem(concernShortcut.getCarePlanConcernShortcutCodeSystem());
					carePlanConcern.setCarePlanConcernCodeSystemName(concernShortcut.getCarePlanConcernShortcutCodeSystemName());
					carePlanConcern.setCarePlanConcernCodeDesc(concernShortcut.getCarePlanConcernShortcutCodeDesc());
					carePlanConcern.setCarePlanConcernPriority(concernShortcut.getCarePlanConcernShortcutPriority());
					carePlanConcern.setCarePlanConcernValue(concernShortcut.getCarePlanConcernShortcutValue());
					carePlanConcern.setCarePlanConcernUnit(concernShortcut.getCarePlanConcernShortcutUnit());
					carePlanConcern.setCarePlanConcernDesc(concernShortcut.getCarePlanConcernShortcutDesc());
					carePlanConcern.setCarePlanConcernNotes(concernShortcut.getCarePlanConcernShortcutNotes());
					carePlanConcern.setCarePlanConcernStatus(concernShortcut.getCarePlanConcernShortcutStatus());
					carePlanConcern.setCarePlanConcernStatusUpdatedDate(carePlanConcernRepository.findCurrentTimeStamp());
					carePlanConcern.setCarePlanConcernCreatedBy(providerId);
					carePlanConcern.setCarePlanConcernCreatedOn(carePlanConcernRepository.findCurrentTimeStamp());
					carePlanConcern.setCarePlanConcernModifiedBy(providerId);
					carePlanConcern.setCarePlanConcernModifiedOn(carePlanConcernRepository.findCurrentTimeStamp());
					carePlanConcern.setCareplanConcernEpisodeId(episodeId);
					carePlanConcern.setCarePlanConcernFrom(categoryId);
					carePlanConcern.setCareplanConcernEncounterId(encounterId);
					carePlanConcernRepository.saveAndFlush(carePlanConcern);
					concernShortcutId=carePlanConcern.getCarePlanConcernId();
					
				
				}
				CriteriaQuery<CarePlanGoal> carePlanQuery = builder.createQuery(CarePlanGoal.class);
				Root<CarePlanGoal> carePlanRoot = carePlanQuery.from(CarePlanGoal.class);
				Join<CarePlanGoal,CarePlanConcern> concernJoin=carePlanRoot.join(CarePlanGoal_.carePlanConcern,JoinType.LEFT);
				Join<CarePlanGoal,CarePlanOutcome> outcomeJoin=carePlanRoot.join(CarePlanGoal_.carePlanOutcome,JoinType.LEFT);
				outcomeJoin.on(builder.equal(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeEncounterId),encounterId));
				List<Predicate> predicatesForGoal = new ArrayList<>();
				predicatesForGoal.add(builder.like(carePlanRoot.get(CarePlanGoal_.carePlanGoalDesc),shortcut.getCarePlanGoalShortcutDesc()));
				predicatesForGoal.add(builder.equal(carePlanRoot.get(CarePlanGoal_.carePlanGoalPatientId), patientId));
				predicatesForGoal.add(builder.equal(carePlanRoot.get(CarePlanGoal_.carePlanGoalConcernId), concernShortcutId));
				predicatesForGoal.add(builder.equal(carePlanRoot.get(CarePlanGoal_.careplanGoalEpisodeId),episodeId));

				carePlanQuery.where(builder.and(predicatesForGoal.toArray(new Predicate[predicatesForGoal.size()])));
				List<CarePlanGoal> goals=entityManager.createQuery(carePlanQuery).getResultList();

				if(goals.size()>0) {
					
				}
				else {
					CarePlanGoal carePlanGoal=new CarePlanGoal();
					carePlanGoal.setCarePlanGoalPatientId(patientId);
					carePlanGoal.setCarePlanGoalEncounterId(encounterId);
					carePlanGoal.setCarePlanGoalConcernId(concernShortcutId);
					carePlanGoal.setCarePlanGoalParentId(-1);
					carePlanGoal.setCarePlanGoalFrom(-1);
					carePlanGoal.setCarePlanGoalPriority(shortcut.getCarePlanGoalShortcutPriority());
					carePlanGoal.setCarePlanGoalType(shortcut.getCarePlanGoalShortcutGoalType());
					carePlanGoal.setCarePlanGoalTerm(shortcutTerm);
					carePlanGoal.setCarePlanGoalProviderId(providerId);
					carePlanGoal.setCarePlanGoalDesc(shortcut.getCarePlanGoalShortcutDesc());
					carePlanGoal.setCarePlanGoalCode(shortcut.getCarePlanGoalShortcutCode());
					carePlanGoal.setCarePlanGoalCodeDescription(shortcut.getCarePlanGoalShortcutCodeDescription());
					carePlanGoal.setCarePlanGoalCodeSystem("2.16.840.1.113883.6.96");
					carePlanGoal.setCarePlanGoalCodeSystemName("SNOMED");
					carePlanGoal.setCarePlanGoalCodeOperator(shortcut.getCarePlanGoalShortcutCodeOperator());
					carePlanGoal.setCarePlanGoalValue(shortcut.getCarePlanGoalShortcutValue());
					carePlanGoal.setCarePlanGoalUnit(shortcut.getCarePlanGoalShortcutUnit());		
					carePlanGoal.setCarePlanGoalStatus(shortcut.getCarePlanGoalShortcutStatus());
					carePlanGoal.setCarePlanGoalTargetDate(null);
					carePlanGoal.setCarePlanGoalNextReviewDate(null);
					carePlanGoal.setCarePlanGoalNotes(shortcut.getCarePlanGoalShortcutNotes());
					carePlanGoal.setCarePlanGoalCreatedBy(providerId);
					carePlanGoal.setCarePlanGoalModifiedBy(providerId);
					carePlanGoal.setCarePlanGoalCreatedOn(carePlanConcernRepository.findCurrentTimeStamp());
					carePlanGoal.setCarePlanGoalModifiedOn(carePlanGoalRepository.findCurrentTimeStamp());
					carePlanGoal.setCareplanGoalEpisodeId(episodeId);
					carePlanGoalRepository.saveAndFlush(carePlanGoal);
				}
			}
		
	}
	
		Map<String,Object> listsMap=new HashMap<String,Object>();
		if(summaryMode!=2){
		listsMap=getCarePlanInitialData(patientId,encounterId,episodeId,categoryId,previousEpisodeId);
		}else{
		listsMap=getCarePlanSummaryData(patientId, episodeId, encounterId, categoryId);	
		}
		return listsMap;
	}

	@Override
	public void saveProgressPlanNotes(Integer encounterId, String planText){
		List<EncounterPlan> encPlan=null;
		if((encPlan=encounterPlanRepository.findAll(EncounterSpecification.getEncounterPlanByEncId(encounterId))).size()>0){
			for (EncounterPlan encounterPlan : encPlan) {
				encounterPlan.setPlantext(planText.replaceAll("'", "''"));
				encounterPlanRepository.saveAndFlush(encounterPlan);
			}
		}
		else
		{
			EncounterPlan encounterPlan=new EncounterPlan();
			encounterPlan.setEncounterid(encounterId);
			encounterPlan.setPlantext(planText.replaceAll("'", "''"));
			encounterPlanRepository.save(encounterPlan);
		}
	}
	
	@Override
	public void clearProgressPlanNotes(Integer encounterId){
		List<EncounterPlan> encPlan=null;
		if((encPlan=encounterPlanRepository.findAll(EncounterSpecification.getEncounterPlanByEncId(encounterId))).size()>0){
		encounterPlanRepository.delete(encounterId);
	}
}
	
	
	@Override
	public void saveProgressSubjectiveNotes(String gwid,Integer chartId,Integer patientId,Integer encounterId,String subjectiveNotes){
		List<PatientClinicalElements> patientElem=null;
		if((patientElem=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getByPatEncGwId(patientId, encounterId, gwid))).size()>0){
			PatientClinicalElements	element=patientElem.get(0);
			element.setPatientClinicalElementsPatientid(patientId);
			element.setPatientClinicalElementsEncounterid(encounterId);
			element.setPatientClinicalElementsGwid(gwid);
			element.setPatientClinicalElementsValue(subjectiveNotes);
			patientClinicalElementsRepository.saveAndFlush(element);
		}
		else{
		PatientClinicalElements element=new PatientClinicalElements();
		element.setPatientClinicalElementsPatientid(patientId);
		element.setPatientClinicalElementsEncounterid(encounterId);
		element.setPatientClinicalElementsGwid(gwid);
		element.setPatientClinicalElementsValue(subjectiveNotes);
		patientClinicalElementsRepository.saveAndFlush(element);
		}
	}
	
	@Override
	public void clearProgressSubjectiveNotes(String gwid,Integer patientId,Integer encounterId){
		List<PatientClinicalElements> patientElem=null;
		if((patientElem=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getByPatEncGwId(patientId, encounterId, gwid))).size()>0){
			patientClinicalElementsRepository.delete(patientElem);
		}
	}

	@Override
	public Map<String, Object> getCarePlanProgressInitialData(Integer patientId,
			Integer encounterId,Integer episodeId, String gwid) throws ParseException {
		Map<String,Object> listsMap=new HashMap<String,Object>();
		listsMap.put("concernsList", fetchCarePlanConcerns(-1,patientId,-1,episodeId,encounterId,"-1","-1"));
		listsMap.put("goalsList", fetchCarePlanGoalBean(-1,-1,patientId,encounterId,episodeId));
		listsMap.put("unitsList",getUnitsOfMeasures() );
		listsMap.put("vitalsList", getVitalParameters());
		listsMap.put("subjectiveData", getSubjectiveData(patientId,encounterId,gwid));
		listsMap.put("planData", getPlanData(encounterId));
		listsMap.put("hpiShortcut", getShortcuts(1));
		listsMap.put("planShortcut", getShortcuts(5));
		listsMap.put("assesmentShortcut", getShortcuts(115));
		listsMap.put("lastVisitProgress",getLastVisitProgressStatus(patientId,encounterId,episodeId));
		listsMap.put("encounterList",getEncounterListForEpisode(episodeId,patientId,encounterId));

		
		return listsMap;
	}
	
	public  List<PatientClinicalElements> getSubjectiveData(Integer patientId,Integer encounterId,String gwid){
		List<PatientClinicalElements> patientElem= new ArrayList<PatientClinicalElements>();
		patientElem=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getByPatEncGwId(patientId, encounterId,gwid));
		/*if((patientElem=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getByPatEncGwId(patientId, encounterId,gwid))).size()>0){
			System.out.println(" entering if");
			subjectiveData=patientElem.get(0).getPatientClinicalElementsValcarue();
			if(patientElem.size()>1)
			subjectiveData+=","+patientElem.get(1).getPatientClinicalElementsValue();
		}*/
		return patientElem;
	}	
	public String getPlanData(Integer encounterId){
		String encounterPlanData="";
		List<EncounterPlan> encPlan=null;
		if((encPlan=encounterPlanRepository.findAll(EncounterSpecification.getEncounterPlanByEncId(encounterId))).size()>0){
			encounterPlanData=encPlan.get(0).getPlantext();
		}
		return encounterPlanData;
	}

	@SuppressWarnings("rawtypes")
	public List<CarePlanGoalBean> fetchPreviousCarePlanGoalShortcuts(Integer patientId,Integer categoryId,Integer previousEpisodeId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarePlanGoalBean> cq = builder.createQuery(CarePlanGoalBean.class);
		Root<CarePlanGoal> root = cq.from(CarePlanGoal.class);
		Join<CarePlanGoal,CarePlanConcern> concernJoin=root.join(CarePlanGoal_.carePlanConcern,JoinType.LEFT);
		Join<CarePlanGoal,CarePlanOutcome> outcomeJoin=root.join(CarePlanGoal_.carePlanOutcome,JoinType.LEFT);

		final Subquery<Integer> subquery = cq.subquery(Integer.class);
		final Root<CarePlanOutcome> carePlanOutcome = subquery.from(CarePlanOutcome.class);
		subquery.select(builder.max(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeId)));
		subquery.groupBy(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeGoalId));

		List<Predicate> predicates = new ArrayList<>();
		if(patientId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalPatientId), patientId));
		/*if(categoryId!=-1)
		      predicates.add(builder.equal(concernJoin.get(CarePlanConcern_.carePlanConcernCategoryId),categoryId ));
		*/
			  predicates.add(builder.equal(root.get(CarePlanGoal_.careplanGoalEpisodeId),previousEpisodeId ));
		predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalStatus),1));
		predicates.add(builder.or(builder.in(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeId)).value(subquery),builder.isNull(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeId))));
		
		Selection[] selections=new Selection[]{
				root.get(CarePlanGoal_.carePlanGoalId),
				root.get(CarePlanGoal_.carePlanGoalPatientId),
				root.get(CarePlanGoal_.carePlanGoalEncounterId),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalConcernId),-1),
				concernJoin.get(CarePlanConcern_.carePlanConcernDesc),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalPriority),0),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalType),-1),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalTerm),0),
				root.get(CarePlanGoal_.carePlanGoalProviderId),
				root.get(CarePlanGoal_.carePlanGoalDesc),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalCode),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalCodeDescription),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalCodeOperator),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalValue),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalUnit),""),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalStatus),0),
				root.get(CarePlanGoal_.carePlanGoalTargetDate),
				root.get(CarePlanGoal_.carePlanGoalNextReviewDate),
				root.get(CarePlanGoal_.carePlanGoalNotes),
				root.get(CarePlanGoal_.carePlanGoalFrom),
				builder.coalesce(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeProgress),0),
				root.get(CarePlanGoal_.carePlanGoalResultStatus),
				root.get(CarePlanGoal_.careplanGoalEpisodeId),
				root.get(CarePlanGoal_.carePlanGoalOrder),
				root.get(CarePlanGoal_.carePlanGoalValueOne),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalAssistanceStatus),0),
				builder.coalesce(root.get(CarePlanGoal_.carePlanGoalLevelStatus),0)
				
		};
		
		cq.select(builder.construct(CarePlanGoalBean.class, selections));
		cq.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		List<CarePlanGoalBean> concerns=entityManager.createQuery(cq).getResultList();
		return concerns;
	}

	/**
	 * To import care plan shortcuts for given patient details from privous episode
	 * @param patientId
	 * @param encounterId
	 * @param shortcutIDs
	 * @param providerId
	 * @return Map
	 */
	
	@Override
	public Map<String, Object> importCarePlanShortcutsFromPrevious(Integer patientId,
			Integer encounterId, String previousGoalShortcutIDs,Integer providerId,Integer episodeId,Integer shortcutTerm,Integer categoryId,Integer previousEpisodeId) {
		List<String> shortcutIdList=Arrays.asList(previousGoalShortcutIDs.split(","));
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		for(String shortcutId:shortcutIdList){
			CriteriaQuery<CarePlanGoal> cq = builder.createQuery(CarePlanGoal.class);
			Root<CarePlanGoal> root=cq.from(CarePlanGoal.class);
			cq.where(builder.equal(root.get(CarePlanGoal_.carePlanGoalId),shortcutId));
			CarePlanGoal shortcut=(CarePlanGoal) entityManager.createQuery(cq).getResultList().get(0);
			Integer concernShortcutId=-1;
			if(shortcut.getCarePlanGoalConcernId()!=-1){
				CriteriaQuery<CarePlanConcern> concernQuery = builder.createQuery(CarePlanConcern.class);
				Root<CarePlanConcern> rootConcern=concernQuery.from(CarePlanConcern.class);
				concernQuery.where(builder.equal(rootConcern.get(CarePlanConcern_.carePlanConcernId),shortcut.getCarePlanGoalConcernId()));
				CarePlanConcern concernShortcut=(CarePlanConcern) entityManager.createQuery(concernQuery).getResultList().get(0);

				List<Predicate> predicates = new ArrayList<>();

				CriteriaQuery<CarePlanConcern> concernPatientQuery = builder.createQuery(CarePlanConcern.class);
				Root<CarePlanConcern> rootConcernPatient=concernPatientQuery.from(CarePlanConcern.class);
				predicates.add(builder.equal(rootConcernPatient.get(CarePlanConcern_.carePlanConcernPatientId),patientId));
				predicates.add(builder.equal(rootConcernPatient.get(CarePlanConcern_.carePlanConcernCategoryId),categoryId));
				predicates.add(builder.equal(rootConcernPatient.get(CarePlanConcern_.careplanConcernEpisodeId),episodeId));
				predicates.add(builder.like(rootConcernPatient.get(CarePlanConcern_.carePlanConcernDesc),concernShortcut.getCarePlanConcernDesc()));
				concernPatientQuery.where(predicates.toArray(new Predicate[predicates.size()]));
				List<CarePlanConcern> concernPatient=entityManager.createQuery(concernPatientQuery).getResultList();
				if(concernPatient.size()>0)
				{
					concernShortcutId=concernPatient.get(0).getCarePlanConcernId();
				}
				else{				
					CarePlanConcern carePlanConcern=new CarePlanConcern();
					carePlanConcern.setCarePlanConcernCategoryId(concernShortcut.getCarePlanConcernCategoryId());
					carePlanConcern.setCarePlanConcernPatientId(patientId);
					carePlanConcern.setCarePlanConcernProviderId(providerId);
					carePlanConcern.setCarePlanConcernType(concernShortcut.getCarePlanConcernType());
					carePlanConcern.setCarePlanConcernCode(concernShortcut.getCarePlanConcernCode());
					carePlanConcern.setCarePlanConcernCodeSystem(concernShortcut.getCarePlanConcernCodeSystem());
					carePlanConcern.setCarePlanConcernCodeSystemName(concernShortcut.getCarePlanConcernCodeSystemName());
					carePlanConcern.setCarePlanConcernCodeDesc(concernShortcut.getCarePlanConcernCodeDesc());
					carePlanConcern.setCarePlanConcernPriority(concernShortcut.getCarePlanConcernPriority());
					carePlanConcern.setCarePlanConcernValue(concernShortcut.getCarePlanConcernValue());
					carePlanConcern.setCarePlanConcernUnit(concernShortcut.getCarePlanConcernUnit());
					carePlanConcern.setCarePlanConcernDesc(concernShortcut.getCarePlanConcernDesc());
					carePlanConcern.setCarePlanConcernNotes(concernShortcut.getCarePlanConcernNotes());
					carePlanConcern.setCarePlanConcernStatus(concernShortcut.getCarePlanConcernStatus());
					carePlanConcern.setCarePlanConcernStatusUpdatedDate(carePlanConcernRepository.findCurrentTimeStamp());
					carePlanConcern.setCarePlanConcernCreatedBy(providerId);
					carePlanConcern.setCarePlanConcernModifiedBy(providerId);
					carePlanConcern.setCarePlanConcernCreatedOn(carePlanConcernRepository.findCurrentTimeStamp());
					carePlanConcern.setCarePlanConcernModifiedOn(carePlanConcernRepository.findCurrentTimeStamp());
					carePlanConcern.setCareplanConcernEpisodeId(episodeId);
					carePlanConcern.setCarePlanConcernFrom(categoryId);

					carePlanConcernRepository.saveAndFlush(carePlanConcern);
					concernShortcutId=carePlanConcern.getCarePlanConcernId();
				}
				CriteriaQuery<CarePlanGoal> carePlanQuery = builder.createQuery(CarePlanGoal.class);
				Root<CarePlanGoal> carePlanRoot = carePlanQuery.from(CarePlanGoal.class);
				Join<CarePlanGoal,CarePlanConcern> concernJoin=carePlanRoot.join(CarePlanGoal_.carePlanConcern,JoinType.LEFT);
				Join<CarePlanGoal,CarePlanOutcome> outcomeJoin=carePlanRoot.join(CarePlanGoal_.carePlanOutcome,JoinType.LEFT);
				outcomeJoin.on(builder.equal(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeEncounterId),encounterId));
				List<Predicate> predicatesForGoal = new ArrayList<>();
				predicatesForGoal.add(builder.like(carePlanRoot.get(CarePlanGoal_.carePlanGoalDesc),shortcut.getCarePlanGoalDesc()));
				predicatesForGoal.add(builder.equal(carePlanRoot.get(CarePlanGoal_.carePlanGoalPatientId), patientId));
				predicatesForGoal.add(builder.equal(carePlanRoot.get(CarePlanGoal_.carePlanGoalConcernId), concernShortcutId));
				predicatesForGoal.add(builder.equal(carePlanRoot.get(CarePlanGoal_.careplanGoalEpisodeId),episodeId));

				carePlanQuery.where(builder.and(predicatesForGoal.toArray(new Predicate[predicatesForGoal.size()])));
				List<CarePlanGoal> goals=entityManager.createQuery(carePlanQuery).getResultList();

				if(goals.size()>0) {
					
				}
				else {
					CarePlanGoal carePlanGoal=new CarePlanGoal();
					carePlanGoal.setCarePlanGoalPatientId(patientId);
					carePlanGoal.setCarePlanGoalEncounterId(encounterId);
					carePlanGoal.setCarePlanGoalConcernId(concernShortcutId);
					carePlanGoal.setCarePlanGoalParentId(-1);
					carePlanGoal.setCarePlanGoalFrom(-1);
					carePlanGoal.setCarePlanGoalPriority(shortcut.getCarePlanGoalPriority());
					carePlanGoal.setCarePlanGoalType(shortcut.getCarePlanGoalType());
					carePlanGoal.setCarePlanGoalTerm(shortcutTerm);
					if(shortcutTerm==3)
						carePlanGoal.setCarePlanGoalTerm(shortcut.getCarePlanGoalTerm());
					else
						carePlanGoal.setCarePlanGoalTerm(shortcutTerm);
					carePlanGoal.setCarePlanGoalProviderId(providerId);
					carePlanGoal.setCarePlanGoalDesc(shortcut.getCarePlanGoalDesc());
					carePlanGoal.setCarePlanGoalCode(shortcut.getCarePlanGoalCode());
					carePlanGoal.setCarePlanGoalCodeDescription(shortcut.getCarePlanGoalCodeDescription());
					carePlanGoal.setCarePlanGoalCodeSystem("2.16.840.1.113883.6.96");
					carePlanGoal.setCarePlanGoalCodeSystemName("SNOMED");
					carePlanGoal.setCarePlanGoalCodeOperator(shortcut.getCarePlanGoalCodeOperator());
					carePlanGoal.setCarePlanGoalValue(shortcut.getCarePlanGoalValue());
					carePlanGoal.setCarePlanGoalUnit(shortcut.getCarePlanGoalUnit());		
					carePlanGoal.setCarePlanGoalStatus(shortcut.getCarePlanGoalStatus());
					carePlanGoal.setCarePlanGoalTargetDate(null);
					carePlanGoal.setCarePlanGoalNextReviewDate(null);
					carePlanGoal.setCarePlanGoalNotes(shortcut.getCarePlanGoalNotes());
					carePlanGoal.setCarePlanGoalCreatedBy(providerId);
					carePlanGoal.setCarePlanGoalModifiedBy(providerId);
					carePlanGoal.setCarePlanGoalCreatedOn(carePlanConcernRepository.findCurrentTimeStamp());
					carePlanGoal.setCarePlanGoalModifiedOn(carePlanConcernRepository.findCurrentTimeStamp());
					carePlanGoal.setCareplanGoalEpisodeId(episodeId);
					carePlanGoalRepository.saveAndFlush(carePlanGoal);
				}
			}


			
		}
		Map<String,Object> listsMap=new HashMap<String,Object>();
		listsMap=getCarePlanInitialData(patientId,encounterId,episodeId,categoryId,previousEpisodeId);
		return listsMap;
	}
	@Override
	public List<CarePlanInterventionBean> fetchCarePlanInterventions(Integer patientId, Integer encounterId, Integer interventionMode, String dxCode,Integer Status,String frmDate,String toDate) {
		/*CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarePlanIntervention> cq = builder.createQuery(CarePlanIntervention.class);
		Root<CarePlanIntervention> root = cq.from(CarePlanIntervention.class);
		List<Predicate> predicates = new ArrayList<>();
		if(patientId!=-1)
			predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionPatientId), patientId));
		if(encounterId!=-1)
			predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionEncounterId), encounterId));
		if(interventionMode!=0 && dxCode!=null)
			predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionProblemCode), dxCode));
		cq.where(predicates.toArray(new Predicate[predicates.size()]));	
		cq.orderBy(builder.asc(root.get(CarePlanIntervention_.carePlanInterventionId)));
		return entityManager.createQuery(cq).getResultList();*/
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarePlanInterventionBean> cq = builder.createQuery(CarePlanInterventionBean.class);
		Root<CarePlanIntervention> root = cq.from(CarePlanIntervention.class);
		List<Predicate> predicates = new ArrayList<>();
		if(patientId!=-1)
			predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionPatientId), patientId));
		if(encounterId!=-1)
			predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionEncounterId), encounterId));
		if(interventionMode!=0 && dxCode!=null)
			predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionProblemCode), dxCode));
		if(Status!=null && Status!=-1)
			predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionStatus), Status));
		if(frmDate!=null && !(frmDate.equalsIgnoreCase("-1"))) {
			Date fromDate = null;
			try {
				fromDate=new SimpleDateFormat("MM/dd/yyyy").parse(frmDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			predicates.add(builder.greaterThanOrEqualTo(builder.function("DATE", Date.class,root.get(CarePlanIntervention_.carePlanInterventionCreatedOn)),fromDate));
		}
		if(toDate!=null && !(toDate.equalsIgnoreCase("-1"))) {
			Date tDate = null;
			try {
				tDate=new SimpleDateFormat("MM/dd/yyyy").parse(toDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			predicates.add(builder.lessThanOrEqualTo(builder.function("DATE", Date.class,root.get(CarePlanIntervention_.carePlanInterventionCreatedOn)),tDate));
		}
		Selection[] selectedColumns = new Selection[]{
				root.get(CarePlanIntervention_.carePlanInterventionId),
				root.get(CarePlanIntervention_.carePlanInterventionPatientId),
				root.get(CarePlanIntervention_.carePlanInterventionEncounterId),
				root.get(CarePlanIntervention_.carePlanInterventionConcernId),
				root.get(CarePlanIntervention_.carePlanInterventionGoalId),
				root.get(CarePlanIntervention_.carePlanInterventionCategoryId),
				root.get(CarePlanIntervention_.carePlanInterventionDescription),
				root.get(CarePlanIntervention_.carePlanInterventionCode),
				root.get(CarePlanIntervention_.carePlanInterventionCodeName),
				root.get(CarePlanIntervention_.carePlanInterventionProblemCode),
				root.get(CarePlanIntervention_.carePlanInterventionProblemCodeSystem),
				root.get(CarePlanIntervention_.carePlanInterventionProblemCodeSystemDescription),
				root.get(CarePlanIntervention_.carePlanInterventionStatus),
				root.get(CarePlanIntervention_.carePlanInterventionOrderedBy),
				root.get(CarePlanIntervention_.carePlanInterventionOrderedOn),
				root.get(CarePlanIntervention_.carePlanInterventionPerformedBy),
				root.get(CarePlanIntervention_.carePlanInterventionPerformedOn),
				root.get(CarePlanIntervention_.carePlanInterventionNotDoneType),
				root.get(CarePlanIntervention_.carePlanInterventionNotDoneDescription),
				root.get(CarePlanIntervention_.carePlanInterventionNotDoneCode),
				root.get(CarePlanIntervention_.carePlanInterventionNotDoneCodeSystem),
				root.get(CarePlanIntervention_.carePlanInterventionNotes)
		};
		cq.select(builder.construct(CarePlanInterventionBean.class, selectedColumns));
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(builder.asc(root.get(CarePlanIntervention_.carePlanInterventionId)));
		List<CarePlanInterventionBean> interventions=entityManager.createQuery(cq).getResultList();
		return interventions;

	}
@Override
	public void deleteCarePlanIntervention(Integer patientId, Integer encounterId, Integer delVal) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaDelete<CarePlanIntervention> delete = builder.createCriteriaDelete(CarePlanIntervention.class);
		Root<CarePlanIntervention> root = delete.from(CarePlanIntervention.class);
		if(patientId!=-1 && encounterId!=-1) {
		delete.where(builder.and(
				builder.equal(root.get(CarePlanIntervention_.carePlanInterventionId), delVal),
				builder.equal(root.get(CarePlanIntervention_.carePlanInterventionPatientId), patientId),
				builder.equal(root.get(CarePlanIntervention_.carePlanInterventionEncounterId), encounterId)));
				this.entityManager.createQuery(delete).executeUpdate();
		}
	}


public List<CarePlanInterventionBean> fetchInterventionPlanData(Integer goalId,Integer concernId,
		Integer CategoryId, Integer patientId, Integer encounterId,Integer intervenId){
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<CarePlanInterventionBean> cq = builder.createQuery(CarePlanInterventionBean.class);
	Root<CarePlanIntervention> root = cq.from(CarePlanIntervention.class);

	List<Predicate> predicates = new ArrayList<>();
	if(patientId!=-1)
		predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionPatientId), patientId));
	if(encounterId!=-1)
		predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionEncounterId), encounterId));
	if(concernId!=-1)
		predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionConcernId), concernId));
	if(goalId!=-1)
		predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionGoalId), goalId));
	if(CategoryId!=-1)
		predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionCategoryId), CategoryId));
	if(intervenId!=-1)
		predicates.add(builder.equal(root.get(CarePlanIntervention_.carePlanInterventionId), intervenId));
	Selection[] selectedColumns = new Selection[]{
			root.get(CarePlanIntervention_.carePlanInterventionId),
			root.get(CarePlanIntervention_.carePlanInterventionPatientId),
			root.get(CarePlanIntervention_.carePlanInterventionEncounterId),
			root.get(CarePlanIntervention_.carePlanInterventionConcernId),
			root.get(CarePlanIntervention_.carePlanInterventionGoalId),
			root.get(CarePlanIntervention_.carePlanInterventionCategoryId),
			root.get(CarePlanIntervention_.carePlanInterventionDescription),
			root.get(CarePlanIntervention_.carePlanInterventionCode),
			root.get(CarePlanIntervention_.carePlanInterventionCodeName),
			root.get(CarePlanIntervention_.carePlanInterventionProblemCode),
			root.get(CarePlanIntervention_.carePlanInterventionProblemCodeSystem),
			root.get(CarePlanIntervention_.carePlanInterventionProblemCodeSystemDescription),
			root.get(CarePlanIntervention_.carePlanInterventionStatus),
			root.get(CarePlanIntervention_.carePlanInterventionOrderedBy),
			root.get(CarePlanIntervention_.carePlanInterventionOrderedOn),
			root.get(CarePlanIntervention_.carePlanInterventionPerformedBy),
			root.get(CarePlanIntervention_.carePlanInterventionPerformedOn),
			root.get(CarePlanIntervention_.carePlanInterventionNotDoneType),
			root.get(CarePlanIntervention_.carePlanInterventionNotDoneDescription),
			root.get(CarePlanIntervention_.carePlanInterventionNotDoneCode),
			root.get(CarePlanIntervention_.carePlanInterventionNotDoneCodeSystem),
			root.get(CarePlanIntervention_.carePlanInterventionNotes)
	};
	cq.select(builder.construct(CarePlanInterventionBean.class, selectedColumns));
	cq.where(predicates.toArray(new Predicate[predicates.size()]));
	List<CarePlanInterventionBean> interventions=entityManager.createQuery(cq).getResultList();
	return interventions;
}



	@Override
	public void updateCarePlanIntervention(Integer patientId, Integer encounterId, Integer id,  String editedNotes, Integer orderedBy,
			Integer performedBy,Integer notDoneBy, String notDoneReason,Integer userId, Integer status, String perfOn, String orderedOn) {
		java.util.Date today =new java.util.Date();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
		CriteriaUpdate<CarePlanIntervention> update = builder.createCriteriaUpdate(CarePlanIntervention.class);
		Root<CarePlanIntervention> root = update.from(CarePlanIntervention.class);
		if(!editedNotes.equals("-1"))
		update.set(root.get(CarePlanIntervention_.carePlanInterventionNotes), editedNotes);
		if(orderedBy!=-1){
			@SuppressWarnings("deprecation")
			Date orderedDateString = new Date(orderedOn);
			@SuppressWarnings("deprecation")
			Date orderedDateToSave = new Date(ft.format(orderedDateString));
		update.set(root.get(CarePlanIntervention_.carePlanInterventionOrderedBy), orderedBy);
		update.set(root.get(CarePlanIntervention_.carePlanInterventionOrderedOn), new Timestamp(orderedDateToSave.getTime()));
		}
		if(performedBy!=-1){
			@SuppressWarnings("deprecation")
			Date performedDateString=new Date(perfOn);
			@SuppressWarnings("deprecation")
			Date performedDateToSave = new Date(ft.format(performedDateString));
		update.set(root.get(CarePlanIntervention_.carePlanInterventionPerformedBy), performedBy);
		update.set(root.get(CarePlanIntervention_.carePlanInterventionPerformedOn), new Timestamp(performedDateToSave.getTime()));
		}
		if(notDoneBy>0){
		update.set(root.get(CarePlanIntervention_.carePlanInterventionNotDoneType), notDoneBy);
		update.set(root.get(CarePlanIntervention_.carePlanInterventionNotDoneDescription), notDoneReason);
		}
		update.set(root.get(CarePlanIntervention_.carePlanInterventionStatus), status);
		update.set(root.get(CarePlanIntervention_.carePlanInterventionModifiedBy), userId);
		update.set(root.get(CarePlanIntervention_.carePlanInterventionModifiedOn), new Timestamp(today.getTime()));
		if(patientId!=-1 && encounterId!=-1 && id!=-1) {
			update.where(builder.and(
					builder.equal(root.get(CarePlanIntervention_.carePlanInterventionPatientId), patientId),
					builder.equal(root.get(CarePlanIntervention_.carePlanInterventionEncounterId), encounterId),
					builder.equal(root.get(CarePlanIntervention_.carePlanInterventionId), id)));
			}
			this.entityManager.createQuery(update).executeUpdate();
	}


	
	@Override
	public Map<String, Object> getEditCarePlanIntervention(Integer patientId, Integer encounterId, Integer intervenId) {
		Map<String,Object> listsMap=new HashMap<String,Object>();
		listsMap.put("getInterventionData", fetchInterventionPlanData(-1,-1,-1,patientId,encounterId,intervenId));
		listsMap.put("getDoctors", fetchEmployeeList());
		return listsMap;
	}
	
	public Map<String, Object> fetchEmployeeList(){
		Map<String,Object> listsMap=new HashMap<String,Object>();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<EmployeeProfile> root = cq.from(EmployeeProfile.class);
		cq.multiselect(root.get(EmployeeProfile_.empProfileEmpid),root.get(EmployeeProfile_.empProfileFullname),root.get(EmployeeProfile_.empProfileGroupid));
		Predicate predicateByIsActive=builder.equal(root.get(EmployeeProfile_.empProfileIsActive), true);
		Predicate predicateByEmpGroupId=root.get(EmployeeProfile_.empProfileGroupid).in(-1);
		cq.where(predicateByIsActive,predicateByEmpGroupId).orderBy(builder.desc(root.get(EmployeeProfile_.empProfileGroupid)),builder.asc(root.get(EmployeeProfile_.empProfileFullname)));
		List<Object[]> resultset = entityManager.createQuery(cq).getResultList();
		List<EmployeeProfile> phisicianList = new ArrayList<EmployeeProfile>();
		for(int i=0;i<resultset.size();i++){ 
			Integer empId = Integer.parseInt(resultset.get(i)[0].toString());
			String empName = resultset.get(i)[1].toString();
			Integer empGroupId = Integer.parseInt(resultset.get(i)[2].toString());
			EmployeeProfile eachObject = new EmployeeProfile();
			eachObject.setEmpProfileEmpid(empId);
			eachObject.setEmpProfileFullname(empName);
			eachObject.setEmpProfileGroupid(empGroupId);
			phisicianList.add(eachObject);			
		}
		listsMap.put("doctors", phisicianList);
		return listsMap;
	}
@Override
	public CarePlanIntervention saveCarePlanIntervention(Integer patientId, Integer encounterId, String description, String snomedCode,Integer status,Integer userId,Integer mode,String dxCode,String intervenDxDesc, String intervenDxCodeSystem) {
		
		java.util.Date today =new java.util.Date();
		CarePlanIntervention carePlanInterven = new CarePlanIntervention();
		carePlanInterven.setCareplanInterventionPatientId(patientId);
		carePlanInterven.setCareplanInterventionEncounterId(encounterId);
		carePlanInterven.setCareplanInterventionDescription(description);
		carePlanInterven.setCareplanInterventionCode(snomedCode);
		carePlanInterven.setCareplanInterventionCodeSystem("2.16.840.1.113883.6.96");
		carePlanInterven.setCareplanInterventionCodeSystemName("SNOMED");
		carePlanInterven.setCareplanInterventionStatus(status);
		if(status == 1){
			carePlanInterven.setCareplanInterventionOrderedBy(userId);
			carePlanInterven.setCareplanInterventionOrderedOn(new Timestamp(today.getTime()));
		}else if(status == 2){
			carePlanInterven.setCareplanInterventionPerformedBy(userId);
			carePlanInterven.setCareplanInterventionPerformedOn(new Timestamp(today.getTime()));
		}
		if(mode==1 && dxCode!=null){
			carePlanInterven.setCareplanInterventionProblemCode(dxCode);
			carePlanInterven.setCareplanInterventionProblemCodeSystem(intervenDxCodeSystem);
			carePlanInterven.setCareplanInterventionProblemCodeSystemDescription(intervenDxDesc);
		}
		carePlanInterven.setCareplanInterventionCreatedBy(userId);
		carePlanInterven.setCareplanInterventionCreatedOn(new Timestamp(today.getTime()));
		carePlanInterventionRepository.save(carePlanInterven);
		return carePlanInterven;
	}

@Override
public void saveInterventionData(List<CarePlanInterventionBean> carePlanInterventionBeans) {
	List<CarePlanInterventionBean> carePlanInterventions=new ArrayList<CarePlanInterventionBean>();
	for(CarePlanInterventionBean carePlanInterventionBean : carePlanInterventionBeans){
		java.util.Date today =new java.util.Date();
		//Map<String,Object> listsMap=new HashMap<String,Object>();
		CarePlanIntervention carePlanIntervention = new CarePlanIntervention();
		if(carePlanInterventionBean.getInterventionId()!=-1)
			carePlanIntervention.setCareplanInterventionId(carePlanInterventionBean.getInterventionId());
			carePlanIntervention.setCareplanInterventionPatientId(carePlanInterventionBean.getInterventionPatientId());
			carePlanIntervention.setCareplanInterventionEncounterId(carePlanInterventionBean.getInterventionEncounterId());
			carePlanIntervention.setCareplanInterventionConcernId(carePlanInterventionBean.getInterventionConcernId());
			carePlanIntervention.setCareplanInterventionGoalId(carePlanInterventionBean.getInterventionGoalId());
			carePlanIntervention.setCareplanInterventionCategoryId(carePlanInterventionBean.getInterventionCategoryId());
			carePlanIntervention.setCareplanInterventionDescription(carePlanInterventionBean.getInterventionDescription());
			carePlanIntervention.setCareplanInterventionCode(carePlanInterventionBean.getInterventionCode());
			carePlanIntervention.setCareplanInterventionCodeSystemName(carePlanInterventionBean.getInterventionCodeSystemName());
			carePlanIntervention.setCareplanInterventionCodeSystem(carePlanInterventionBean.getInterventionCodeSystem());
			carePlanIntervention.setCareplanInterventionStatus(carePlanInterventionBean.getInterventionStatus());
			carePlanIntervention.setCareplanInterventionOrderedBy(carePlanInterventionBean.getInterventionOrderedBy());
			if(carePlanInterventionBean.getInterventionOrderedBy()!=null)
			carePlanIntervention.setCareplanInterventionOrderedOn(new Timestamp(today.getTime()));
			carePlanIntervention.setCareplanInterventionPerformedBy(carePlanInterventionBean.getInterventionPerformedBy());
			if(carePlanInterventionBean.getInterventionPerformedBy()!=null)
			carePlanIntervention.setCareplanInterventionPerformedOn(new Timestamp(today.getTime()));
			carePlanIntervention.setCareplanInterventionNotes(carePlanInterventionBean.getInterventionNotes());
			carePlanIntervention.setCareplanInterventionNotDoneType(carePlanInterventionBean.getInterventionNotDoneType());
			carePlanIntervention.setCareplanInterventionNotDoneDescription(carePlanInterventionBean.getInterventionNotDoneDescription());
			carePlanIntervention.setCareplanInterventionNotDoneCode(carePlanInterventionBean.getInterventionNotDoneCode());
			carePlanIntervention.setCareplanInterventionNotDoneCodeSystem(carePlanInterventionBean.getInterventionNotDoneCodeSystem());
			carePlanInterventionRepository.save(carePlanIntervention);
			// carePlanInterventions.addAll(fetchInterventionData(-1,-1,-1,carePlanInterventionBean.getInterventionPatientId(),carePlanInterventionBean.getInterventionEncounterId()));}
	}
}

public List<CarePlanSummaryBean> fetchCarePlanSummaryBean(Integer goalId,Integer concernId,
		Integer patientId, Integer encounterId,Integer episodeId) {

	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<CarePlanSummaryBean> cq = builder.createQuery(CarePlanSummaryBean.class);
	Root<CarePlanSummary> root = cq.from(CarePlanSummary.class);
	Join<CarePlanSummary,CarePlanConcern> concernJoin=root.join(CarePlanSummary_.carePlanConcern,JoinType.LEFT);
	Join<CarePlanSummary,CarePlanGoal> goalJoin=root.join(CarePlanSummary_.carePlanGoal,JoinType.LEFT);
	Join<CarePlanGoal,CarePlanOutcome> outcomeJoin=goalJoin.join(CarePlanGoal_.carePlanOutcome,JoinType.LEFT);

	List<Predicate> predicates = new ArrayList<>();
	if(patientId!=-1)
		predicates.add(builder.equal(root.get(CarePlanSummary_.carePlanSummaryPatientId), patientId));

	if(episodeId!=-1)
		predicates.add(builder.equal(root.get(CarePlanSummary_.carePlanSummaryEpisodeId),episodeId));
	Selection[] selections=new Selection[]{
			root.get(CarePlanSummary_.carePlanSummaryId),
			root.get(CarePlanSummary_.carePlanSummaryPatientId),
			root.get(CarePlanSummary_.carePlanSummaryEncounterId),
			builder.coalesce(root.get(CarePlanSummary_.carePlanSummaryConcernId),-1),
			concernJoin.get(CarePlanConcern_.carePlanConcernDesc),
			goalJoin.get(CarePlanGoal_.carePlanGoalDesc),
			goalJoin.get(CarePlanGoal_.carePlanGoalTerm),
			root.get(CarePlanSummary_.carePlanSummaryMasterDate),
			root.get(CarePlanSummary_.carePlanSummaryComments),
			root.get(CarePlanSummary_.carePlanSummaryAvgVal),
			root.get(CarePlanSummary_.carePlanSummaryEpisodeId),
			goalJoin.get(CarePlanGoal_.carePlanGoalId),
			root.get(CarePlanSummary_.carePlanSummaryGoalProgress),
			goalJoin.get(CarePlanGoal_.carePlanGoalResultStatus),
			goalJoin.get(CarePlanGoal_.carePlanGoalOrder),
			builder.max(builder.coalesce(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeId),-1))
	};
	cq.select(builder.construct(CarePlanSummaryBean.class, selections));
	cq.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
	cq.groupBy(root.get(CarePlanSummary_.carePlanSummaryId),
			root.get(CarePlanSummary_.carePlanSummaryPatientId),
			root.get(CarePlanSummary_.carePlanSummaryEncounterId),
			builder.coalesce(root.get(CarePlanSummary_.carePlanSummaryConcernId),-1),
			concernJoin.get(CarePlanConcern_.carePlanConcernDesc),
			goalJoin.get(CarePlanGoal_.carePlanGoalDesc),
			goalJoin.get(CarePlanGoal_.carePlanGoalTerm),
			root.get(CarePlanSummary_.carePlanSummaryMasterDate),
			root.get(CarePlanSummary_.carePlanSummaryComments),
			root.get(CarePlanSummary_.carePlanSummaryAvgVal),
			root.get(CarePlanSummary_.carePlanSummaryEpisodeId),
			goalJoin.get(CarePlanGoal_.carePlanGoalId),
			root.get(CarePlanSummary_.carePlanSummaryGoalProgress),
			goalJoin.get(CarePlanGoal_.carePlanGoalResultStatus),
			goalJoin.get(CarePlanGoal_.carePlanGoalOrder));
	cq.orderBy(builder.asc(goalJoin.get(CarePlanGoal_.carePlanGoalOrder)));
	List<CarePlanSummaryBean> concerns=entityManager.createQuery(cq).getResultList();
	return concerns;

}

@Override
public Map<String, Object> getCarePlanSummaryData(Integer patientId, Integer episodeId,Integer encounterId,Integer episodeTypeId) throws ParseException 
{
	getSummaryData(patientId, episodeId, encounterId);
	Map<String,Object> listsMap=new HashMap<String,Object>();
	List<CarePlanSummaryBean> res= fetchCarePlanSummaryBean(-1,-1,patientId,-1,episodeId);
	listsMap.put("shortcutsList", fetchCarePlanShortcuts(episodeTypeId,"-1","-1"));
	listsMap.put("goalsList", res);
	return listsMap;		
}

public void getSummaryData(Integer patientId,Integer episodeId,Integer encounterId) throws ParseException
{
/*	List<CarePlanGoalBean> res= fetchCarePlanGoalBean(-1,-1,patientId,encounterId,episodeId);
*/	String reviewDate = getLatestOutcomeReviewDate(patientId, encounterId, episodeId);
	List<CarePlanGoalBean> res= fetchLatestGoalBean(-1,-1,patientId,encounterId,episodeId,reviewDate);

	
	List<Object[]> aggregate = getCarePlanOutcomeAggre(patientId,episodeId);
	for(int i=0;i<aggregate.size();i++)
	{
		Object[] inner= aggregate.get(i);
		int id = Integer.parseInt(""+inner[0]);
		int agg = Integer.parseInt(""+inner[1]);
		for(int j=0;j<res.size();j++)
		{
			if(res.get(j).getCarePlanGoalId()==id)
			{
				res.get(j).setAggregateValue(agg);
			}
		}
	}

	CarePlanSummary carePlanSummary = null;
	for(int i=0;i<res.size();i++)
	{
		CarePlanGoalBean obj = res.get(i);
		carePlanSummary = carePlanSummaryRepository.findByCarePlanSummaryPatientIdAndCarePlanSummaryGoalIdAndCarePlanSummaryEpisodeId(obj.getCarePlanGoalPatientId(),obj.getCarePlanGoalId(),obj.getEpisodeId());
		if(carePlanSummary==null) {
			carePlanSummary = new CarePlanSummary();
			carePlanSummary.setCarePlanSummaryAvgVal(obj.getAggregateValue());
		}
		else
			carePlanSummary = carePlanSummaryRepository.findOne(carePlanSummary.getCarePlanSummaryId());
			carePlanSummary.setCarePlanSummaryPatientId(obj.getCarePlanGoalPatientId());
			carePlanSummary.setCarePlanSummaryEncounterId(obj.getCarePlanGoalEncounterId());
			carePlanSummary.setCarePlanSummaryGoalId(obj.getCarePlanGoalId());
			carePlanSummary.setCarePlanSummaryConcernId(obj.getCarePlanGoalConcernId());
			carePlanSummary.setCarePlanSummaryEpisodeId(obj.getEpisodeId());
			carePlanSummary.setCarePlanSummaryGoalProgress(obj.getCarePlanGoalProgress());
			carePlanSummary.setCarePlanSummaryOutcomeId(obj.getOutcomeId());
			carePlanSummaryRepository.saveAndFlush(carePlanSummary);
	}	
}

public List<Object[]> getCarePlanOutcomeAggre(Integer patientId,Integer episodeId){
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
	Root<CarePlanOutcome> root = cq.from(CarePlanOutcome.class);
	final Subquery<Integer> subquery = cq.subquery(Integer.class);
	final Root<CarePlanGoal> carePlanGoal = subquery.from(CarePlanGoal.class);
	subquery.select(carePlanGoal.get(CarePlanGoal_.carePlanGoalId));
	subquery.where(builder.equal(carePlanGoal.get(CarePlanGoal_.careplanGoalEpisodeId),episodeId));
	List<Predicate> predicates = new ArrayList<>();
	if(patientId!=-1)
		predicates.add(builder.equal(root.get(CarePlanOutcome_.carePlanOutcomePatientId), patientId));
	predicates.add(builder.in(root.get(CarePlanOutcome_.carePlanOutcomeGoalId)).value(subquery));
	cq.multiselect(root.get(CarePlanOutcome_.carePlanOutcomeGoalId),builder.avg(root.get(CarePlanOutcome_.carePlanOutcomeProgress)).as(Integer.class));
	cq.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
	cq.groupBy(root.get(CarePlanOutcome_.carePlanOutcomeGoalId));
	List<Object[]> listsMap = entityManager.createQuery(cq).getResultList();
	return listsMap;
}

public Date getEncounterDate(Integer encounterId) {
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<Object> cq = builder.createQuery();
	Root<Encounter> root = cq.from(Encounter.class);
	cq.select(root.get(Encounter_.encounterDate));
	cq.where(builder.equal(root.get(Encounter_.encounterId), encounterId));
	List<Object> resultList = entityManager.createQuery(cq).getResultList();
	Date encounterDate= new Date();
	Date encounterDateToSave = new Date();
	if(resultList.size()>0)
		encounterDate= (Date) resultList.get(0);
	try{
		SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy");
		encounterDate = new Date (ft.format(encounterDate));
	}
	catch(Exception e){}
	return  encounterDate;
}

public List<Object[]> getShortcuts(Integer type) {
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
	Root<GeneralShortcut> root = cq.from(GeneralShortcut.class);
	cq.multiselect(root.get(GeneralShortcut_.generalShortcutId),root.get(GeneralShortcut_.generalShortcutCode));
	cq.where(builder.equal(root.get(GeneralShortcut_.generalShortcutMapGroupId),type),builder.equal(root.get(GeneralShortcut_.generalShortcutIsactive),true));
	cq.orderBy(builder.asc(root.get(GeneralShortcut_.generalShortcutCode)));
	List<Object[]> result= entityManager.createQuery(cq).getResultList();
	return result;
}

@Override
public void saveCarePlanSummaryData(Integer patientId,Integer encounterId,Integer episodeId,String completeJSON,Integer userId) throws Exception {
	java.util.Date today =new java.util.Date();
	SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
	JSONObject st1 = new JSONObject(completeJSON);
	String st2 = st1.getString("summary");
	JSONArray st3 =new JSONArray(st2);
	for(int i=0; i<st3.length();i++){
	JSONObject object = st3.getJSONObject(i);
	int sumId = object.getInt("carePlanSummaryId");
	String sumCom = object.get("carePlanSummaryComments").toString();
	int sumAvg =  Integer.parseInt(HUtil.Nz(object.getString("carePlanSummaryAggregate"),"-1"));
	String sumDate = object.get("carePlanSummaryMasteredDate").toString();
	int sumProgress =  Integer.parseInt(HUtil.Nz(object.getString("carePlanSummaryProgress"),"-1"));
	int outcomeId = object.getInt("carePlanSummaryOutcomeId");
	
	int goalId = object.getInt("carePlanSummaryGoalId");

	
	
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaUpdate<CarePlanSummary> update = builder.createCriteriaUpdate(CarePlanSummary.class);
	Root<CarePlanSummary> root = update.from(CarePlanSummary.class);
	
	CriteriaUpdate<CarePlanOutcome> updateGoalProgress = builder.createCriteriaUpdate(CarePlanOutcome.class);
	Root<CarePlanOutcome> rootGoal = updateGoalProgress.from(CarePlanOutcome.class);
	
	if(sumAvg!=-1)
	update.set(root.get(CarePlanSummary_.carePlanSummaryAvgVal), sumAvg);
	if(sumProgress!=-1)
	update.set(root.get(CarePlanSummary_.carePlanSummaryGoalProgress), sumProgress);
	if(!sumCom.equals("-1"))
	update.set(root.get(CarePlanSummary_.carePlanSummaryComments),sumCom );
	if(!sumDate.equals("-1")){
		@SuppressWarnings("deprecation")
		Date summaryDateString = new Date(sumDate);
		@SuppressWarnings("deprecation")
		Date summaryDateToSave = new Date(ft.format(summaryDateString));
		update.set(root.get(CarePlanSummary_.carePlanSummaryMasterDate),new Timestamp(summaryDateToSave.getTime()) );
	}
	update.set(root.get(CarePlanSummary_.carePlanSummaryModifiedBy),userId );
	update.set(root.get(CarePlanSummary_.carePlanSummaryModifiedOn),new Timestamp(today.getTime()) );
	update.where(builder.equal(root.get(CarePlanSummary_.carePlanSummaryId), sumId));
	this.entityManager.createQuery(update).executeUpdate();
	
	if(outcomeId!=-1) {
		updateGoalProgress.set(rootGoal.get(CarePlanOutcome_.carePlanOutcomeProgress),sumProgress);
		updateGoalProgress.set(rootGoal.get(CarePlanOutcome_.carePlanOutcomeModifiedBy),userId);
		updateGoalProgress.set(rootGoal.get(CarePlanOutcome_.carePlanOutcomeModifiedOn),new Timestamp(today.getTime()));
		updateGoalProgress.where(builder.and(builder.equal(rootGoal.get(CarePlanOutcome_.carePlanOutcomeId), outcomeId)));
		this.entityManager.createQuery(updateGoalProgress).executeUpdate();
	}
	else {
		CarePlanOutcome carePlanOutcome = new CarePlanOutcome();
		carePlanOutcome.setCarePlanOutcomePatientId(patientId);
		carePlanOutcome.setCarePlanOutcomeGoalId(goalId);
		carePlanOutcome.setCarePlanOutcomeEncounterId(encounterId);
		carePlanOutcome.setCarePlanOutcomeProviderId(userId);
		carePlanOutcome.setCarePlanOutcomeProgress(sumProgress);
		String reviewDate = getLatestOutcomeReviewDate(patientId, encounterId, episodeId);
		Date reviewDt = null;
		if(!reviewDate.equalsIgnoreCase("-1")) {
			reviewDt = 	new SimpleDateFormat("MM/dd/yyyy").parse(reviewDate);
			carePlanOutcome.setCarePlanOutcomeReviewDate(new Timestamp(new java.sql.Date(reviewDt.getTime()).getTime()));
		}
		else 
			carePlanOutcome.setCarePlanOutcomeReviewDate(carePlanOutcomeRepository.findCurrentTimeStamp());
		carePlanOutcome.setCarePlanOutcomeCreatedBy(userId);
		carePlanOutcome.setCarePlanOutcomeModifiedBy(userId);
		carePlanOutcome.setCarePlanOutcomeCreatedOn(carePlanOutcomeRepository.findCurrentTimeStamp());
		carePlanOutcome.setCarePlanOutcomeModifiedOn(carePlanOutcomeRepository.findCurrentTimeStamp());
		carePlanOutcomeRepository.saveAndFlush(carePlanOutcome);
	}
	}
}

public List<Object[]> getLastVisitProgressStatus(Integer patientId,Integer encounterId, Integer episodeId) {
	List<Object[]> progress = new ArrayList<Object[]>();
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<Integer> cq = builder.createQuery(Integer.class);
	Root<Encounter> root = cq.from(Encounter.class);
	Join<Encounter, Chart> chartJoin=root.join(Encounter_.chart,JoinType.INNER);
	cq.select(builder.max(root.get(Encounter_.encounterId)));
	if(episodeId!=-1)
	cq.where(builder.and(builder.equal(root.get(Encounter_.encounterPatientEpisodeid),episodeId),
			builder.equal(chartJoin.get(Chart_.chartPatientid),patientId),
			builder.notEqual(root.get(Encounter_.encounterId),encounterId)));
	else
		cq.where(builder.and(builder.equal(chartJoin.get(Chart_.chartPatientid),patientId),
				builder.notEqual(root.get(Encounter_.encounterId),encounterId)));
	Integer previousEncId = 	entityManager.createQuery(cq).getResultList().get(0);
	if(previousEncId.SIZE>0) {
		CriteriaQuery<Object[]> outcomeQuery = builder.createQuery(Object[].class);
		Root<CarePlanOutcome> rootOutcome = outcomeQuery.from(CarePlanOutcome.class);
		outcomeQuery.multiselect(rootOutcome.get(CarePlanOutcome_.carePlanOutcomeGoalId),builder.coalesce(rootOutcome.get(CarePlanOutcome_.carePlanOutcomeProgress),0));
		outcomeQuery.where(builder.equal(rootOutcome.get(CarePlanOutcome_.carePlanOutcomeEncounterId),previousEncId));
		progress = 	entityManager.createQuery(outcomeQuery).getResultList();
	}
	return progress;
}

/**
 * To save care plan concern from given Bean
 * @param CarePlanConcernBean
 * @return List
 */
@Override
public Map<String, Object> saveConcernAndGoal(CarePlanConcernBean carePlanConcernJSON,int previousEpisodeId) {
	CarePlanConcern carePlanConcern=new CarePlanConcern();
	if(carePlanConcernJSON.getConcernId()!=-1)
		carePlanConcern.setCarePlanConcernId(carePlanConcernJSON.getConcernId());
	carePlanConcern.setCarePlanConcernCategoryId(carePlanConcernJSON.getConcernCategoryId());
	carePlanConcern.setCarePlanConcernPatientId(carePlanConcernJSON.getConcernPatientId());
	carePlanConcern.setCarePlanConcernProviderId(carePlanConcernJSON.getConcernProviderId());
	carePlanConcern.setCarePlanConcernType(carePlanConcernJSON.getConcernType());
	carePlanConcern.setCarePlanConcernCode(carePlanConcernJSON.getConcernCode());
	carePlanConcern.setCarePlanConcernCodeSystem(carePlanConcernJSON.getConcernCodeSystem());
	carePlanConcern.setCarePlanConcernCodeSystemName(carePlanConcernJSON.getConcernCodeSystemName());
	carePlanConcern.setCarePlanConcernCodeDesc(carePlanConcernJSON.getConcernCodeDesc());
	carePlanConcern.setCarePlanConcernPriority(carePlanConcernJSON.getConcernPriority());
	carePlanConcern.setCarePlanConcernValue(carePlanConcernJSON.getConcernValue());
	carePlanConcern.setCarePlanConcernUnit(carePlanConcernJSON.getConcernUnit());
	carePlanConcern.setCarePlanConcernDesc(carePlanConcernJSON.getConcernDesc());
	carePlanConcern.setCarePlanConcernNotes(carePlanConcernJSON.getConcernNotes());
	carePlanConcern.setCarePlanConcernStatus(carePlanConcernJSON.getConcernStatus());
	carePlanConcern.setCarePlanConcernStatusUpdatedDate(carePlanConcernRepository.findCurrentTimeStamp());
	carePlanConcern.setCarePlanConcernCreatedBy(carePlanConcernJSON.getConcernCreatedBy());
	carePlanConcern.setCarePlanConcernModifiedBy(carePlanConcernJSON.getConcernModifiedBy());
	carePlanConcern.setCarePlanConcernCreatedOn(carePlanConcernRepository.findCurrentTimeStamp());
	carePlanConcern.setCareplanConcernEpisodeId(carePlanConcernJSON.getEpisodeId());
	carePlanConcern.setCarePlanConcernFrom(carePlanConcernJSON.getConcernFrom());
	carePlanConcern.setCareplanConcernEncounterId(carePlanConcernJSON.getEncounterId());
	carePlanConcern.setCarePlanConcernModifiedOn(carePlanConcernRepository.findCurrentTimeStamp());
	carePlanConcernRepository.saveAndFlush(carePlanConcern);
	if(carePlanConcernJSON.getConcernId()!=-1) {
		try{
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaUpdate<CarePlanGoal> cu = cb.createCriteriaUpdate(CarePlanGoal.class);
			Root<CarePlanGoal> rootCriteria = cu.from(CarePlanGoal.class);
			cu.set(rootCriteria.get(CarePlanGoal_.carePlanGoalStatus), carePlanConcernJSON.getConcernStatus());
			cu.where(cb.equal(rootCriteria.get(CarePlanGoal_.carePlanGoalConcernId),carePlanConcernJSON.getConcernId()),
					cb.equal(rootCriteria.get(CarePlanGoal_.carePlanGoalPatientId),carePlanConcernJSON.getConcernPatientId()));
			this.entityManager.createQuery(cu).executeUpdate();
		}
	catch(Exception e){}
	}	
	Map<String, Object> listsMap = getCarePlanInitialData(carePlanConcernJSON.getConcernPatientId(),carePlanConcernJSON.getEncounterId(),carePlanConcernJSON.getEpisodeId(),carePlanConcernJSON.getConcernCategoryId(),previousEpisodeId);
	return listsMap;	
	}

@Override
public Map<String, Object> showInactiveConcerns(int patientId, int encounterId,
		int episodeId) {
	// TODO Auto-generated method stub
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<CarePlanConcern> cq = builder.createQuery(CarePlanConcern.class);
	Root<CarePlanConcern> root = cq.from(CarePlanConcern.class);
	Join<CarePlanConcern, CarePlanGoal> goalJoin = root.join(CarePlanConcern_.carePlanGoal,JoinType.INNER);
	
	return null;
}

@Override
public List<CarePlanRecommendedInterventionBean> saveCarePlanRecommendedIntervention(
		CarePlanRecommendedInterventionBean carePlanRecommendedInterventionJson) {
	// TODO Auto-generated method stub
	CarePlanRecommendedIntervention carePlanRecommendedIntervention = new CarePlanRecommendedIntervention();
	if(carePlanRecommendedInterventionJson.getRecommInterventionId()!=-1)
		carePlanRecommendedIntervention.setCarePlanRecommendedInterventionId(carePlanRecommendedInterventionJson.getRecommInterventionId());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionPatientId(carePlanRecommendedInterventionJson.getRecommInterventionPatientId());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionEncounterId(carePlanRecommendedInterventionJson.getRecommInterventionEncounterId());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionEpisodeId(carePlanRecommendedInterventionJson.getRecommInterventionEpisodeId());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionCategoryId(carePlanRecommendedInterventionJson.getRecommInterventionCategoryId());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionConcernId(carePlanRecommendedInterventionJson.getRecommInterventionConcernId());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionGoalId(carePlanRecommendedInterventionJson.getRecommInterventionGoalId());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionDescription(carePlanRecommendedInterventionJson.getRecommInterventionDescription());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionNotes(carePlanRecommendedInterventionJson.getRecommInterventionNotes());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionCode(carePlanRecommendedInterventionJson.getRecommInterventionCode());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionCodeSystem(carePlanRecommendedInterventionJson.getRecommInterventionCodeSystem());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionCodeSystemName(carePlanRecommendedInterventionJson.getRecommInterventionCodeSystemname());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionCreatedBy(carePlanRecommendedInterventionJson.getRecommInterventionCreatedBy());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionModifiedBy(carePlanRecommendedInterventionJson.getRecommInterventionModifiedBy());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionRecommendedBy(carePlanRecommendedInterventionJson.getRecommInterventionRecommendedBy());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionCreatedOn(carePlanRecommendedInterventionRepository.findCurrentTimeStamp());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionModifiedOn(carePlanRecommendedInterventionRepository.findCurrentTimeStamp());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionRecommendedOn(carePlanRecommendedInterventionRepository.findCurrentTimeStamp());
	carePlanRecommendedIntervention.setCarePlanRecommendedInterventionStatus(carePlanRecommendedInterventionJson.getRecommInterventionStatus());
	carePlanRecommendedIntervention.setCareplanRecommendedInterventionResponsibleParty(carePlanRecommendedInterventionJson.getRecommResponsibleParty());
	
	carePlanRecommendedInterventionRepository.saveAndFlush(carePlanRecommendedIntervention);
	List<CarePlanRecommendedInterventionBean> recommInterventions=fetchRecommIntervention(carePlanRecommendedInterventionJson.getRecommInterventionPatientId(),carePlanRecommendedInterventionJson.getRecommInterventionEncounterId(),carePlanRecommendedInterventionJson.getRecommInterventionEpisodeId(),"-1","-1");
	return recommInterventions;
}

@Override
public List<CarePlanRecommendedInterventionBean> fetchRecommIntervention(
		Integer patientId, Integer encounterId, Integer episodeId,String frmDate,String toDate) {
	// TODO Auto-generated method stub
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<CarePlanRecommendedInterventionBean> cq = builder.createQuery(CarePlanRecommendedInterventionBean.class);
	Root<CarePlanRecommendedIntervention> root = cq.from(CarePlanRecommendedIntervention.class);
	Join<CarePlanRecommendedIntervention,CarePlanConcern> concernJoin = root.join(CarePlanRecommendedIntervention_.carePlanRecommConcern,JoinType.LEFT);
	Join<CarePlanRecommendedIntervention, CarePlanGoal> goalJoin = root.join(CarePlanRecommendedIntervention_.carePlanRecommGoal,JoinType.LEFT);
	Join<CarePlanRecommendedIntervention,EmployeeProfile> empCreatedJoin= root.join(CarePlanRecommendedIntervention_.empProfileRecommInterCreatedBy,JoinType.INNER);
	Join<CarePlanRecommendedIntervention,EmployeeProfile> empModifiedJoin= root.join(CarePlanRecommendedIntervention_.empProfileRecommInterModifiedBy,JoinType.INNER);

	
	List<Predicate> predicates = new ArrayList<>();
	if(patientId!=-1)
		predicates.add(builder.equal(root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionPatientId),patientId));
	if(encounterId!=-1)
		predicates.add(builder.equal(root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionEncounterId),encounterId));
	if(episodeId!=-1)
		predicates.add(builder.equal(root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionEpisodeId),episodeId));
	if(frmDate!=null && !(frmDate.equalsIgnoreCase("-1"))) {
		Date fromDate = null;
		try {
			fromDate=new SimpleDateFormat("MM/dd/yyyy").parse(frmDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		predicates.add(builder.greaterThanOrEqualTo(builder.function("DATE", Date.class,root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionCreatedOn)),fromDate));
	}
	if(toDate!=null && !(toDate.equalsIgnoreCase("-1"))) {
		Date tDate = null;
		try {
			tDate=new SimpleDateFormat("MM/dd/yyyy").parse(toDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		predicates.add(builder.lessThanOrEqualTo(builder.function("DATE", Date.class,root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionCreatedOn)),tDate));
	}
	Selection[] selections=new Selection[]{
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionId),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionPatientId),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionEncounterId),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionEpisodeId),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionConcernId),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionGoalId),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionCategoryId),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionDescription),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionRecommendedBy),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionRecommendedOn),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionCode),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionCodeSystem),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionCodeSystemName),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionPerformedBy),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionPerformedOn),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionNotes),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionCreatedBy),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionCreatedOn),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionModifiedBy),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionModifiedOn),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionStatus),
			root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionOrderedOn),
			empCreatedJoin.get(EmployeeProfile_.empProfileFullname),
			empModifiedJoin.get(EmployeeProfile_.empProfileFullname),
			builder.coalesce(concernJoin.get(CarePlanConcern_.carePlanConcernDesc),""),
			builder.coalesce(goalJoin.get(CarePlanGoal_.carePlanGoalDesc),""),
	};
	cq.select(builder.construct(CarePlanRecommendedInterventionBean.class, selections));
	cq.where(predicates.toArray(new Predicate[predicates.size()]));
	List<CarePlanRecommendedInterventionBean> recommInterventions=entityManager.createQuery(cq).getResultList();
	return recommInterventions;
}

@Override
public void deleteCarePlanRecommIntervention(Integer patientId, Integer encounterId, Integer delVal) {
	// TODO Auto-generated method stub
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaDelete<CarePlanRecommendedIntervention> delete = builder.createCriteriaDelete(CarePlanRecommendedIntervention.class);
	Root<CarePlanRecommendedIntervention> root = delete.from(CarePlanRecommendedIntervention.class);
	if(patientId!=-1) {
	delete.where(builder.and(
			builder.equal(root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionId), delVal),
			builder.equal(root.get(CarePlanRecommendedIntervention_.carePlanRecommendedInterventionPatientId), patientId)));
			this.entityManager.createQuery(delete).executeUpdate();
	}
}

@Override
public List<Object[]> saveCarePlanLog(Integer patientId,Integer userId,Boolean reviewStatus) {
	// TODO Auto-generated method stub
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<CarePlanLog> cq = builder.createQuery(CarePlanLog.class);
	Root<CarePlanLog> root = cq.from(CarePlanLog.class);
	cq.where(builder.and(builder.equal(root.get(CarePlanLog_.carePlanlogPatientId),patientId),
			builder.equal(builder.function("DATE", Date.class, root.get(CarePlanLog_.carePlanlogReviewedOn)), new Date()),
			builder.equal(root.get(CarePlanLog_.carePlanlogReviewedBy), userId)));
	List<CarePlanLog> resultSet = entityManager.createQuery(cq).getResultList();
	List<Integer> affectedLogId=new ArrayList<>();
	if(resultSet.size()>0) {
		CriteriaUpdate<CarePlanLog> update = builder.createCriteriaUpdate(CarePlanLog.class);
		Root<CarePlanLog> rootUpdate = update.from(CarePlanLog.class);
		update.set(rootUpdate.get(CarePlanLog_.carePlanlogReviewedBy), userId);
		update.set(rootUpdate.get(CarePlanLog_.carePlanlogReviewedOn), carePlanLogRepository.findCurrentTimeStamp());
		update.where(builder.and(builder.equal(rootUpdate.get(CarePlanLog_.carePlanlogPatientId),patientId),
				builder.equal(builder.function("DATE", Date.class, rootUpdate.get(CarePlanLog_.carePlanlogReviewedOn)), new Date()),
				builder.equal(root.get(CarePlanLog_.carePlanlogReviewedBy), userId)));	
		this.entityManager.createQuery(update).executeUpdate();
	}
	
	else {
		CarePlanLog carePlanLog = new CarePlanLog();
		carePlanLog.setCarePlanlogPatientId(patientId);
		carePlanLog.setCarePlanlogReviewedBy(userId);
		carePlanLog.setCarePlanlogReviewedOn(carePlanLogRepository.findCurrentTimeStamp());
		carePlanLog.setCarePlanlogReviewed(reviewStatus);
		carePlanLogRepository.saveAndFlush(carePlanLog);
		affectedLogId.add(carePlanLog.getCarePlanLogId());
	}
	List<Object[]> carePlanLogList = fetchCarePlanLog(patientId,affectedLogId);
	return carePlanLogList;
}

public List<Object[]> fetchCarePlanLog(Integer patientId, List<Integer> alertId) {
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
	Root<CarePlanLog> root = cq.from(CarePlanLog.class);
	Join<CarePlanLog, EmployeeProfile> empJoin = root.join(CarePlanLog_.empProfile,JoinType.INNER);
	cq.multiselect(root.get(CarePlanLog_.carePlanlogPatientId),
			root.get(CarePlanLog_.carePlanlogReviewedBy),
			root.get(CarePlanLog_.carePlanlogReviewedOn),
			empJoin.get(EmployeeProfile_.empProfileFullname),
			root.get(CarePlanLog_.carePlanLogId));
	cq.where(builder.equal(root.get(CarePlanLog_.carePlanlogPatientId),patientId));
	cq.orderBy(builder.desc(root.get(CarePlanLog_.carePlanLogId)));
	List<Object[]> carePlanLogList = entityManager.createQuery(cq).getResultList();
	carePlanLogList.add(alertId.toArray());
	return carePlanLogList;
}

@Override
public Map<String, Object> getCarePlanPrint(Integer patientId,
		Integer encounterId, Integer episodeId) {
	// TODO Auto-generated method stub
	Map<String,Object> listsMap=new HashMap<String,Object>();
	listsMap.put("concernsList", fetchCarePlanConcerns(-1,patientId,-1,episodeId,encounterId,"-1","-1"));
	listsMap.put("logList", fetchCarePlanLog(patientId,new ArrayList<Integer>()));
	listsMap.put("goalList", fetchCarePlanGoals(-1, -1, patientId, encounterId,episodeId,"-1","-1"));
	listsMap.put("recommList",fetchRecommIntervention(patientId, encounterId, episodeId,"-1","-1"));
	listsMap.put("healthStatus", getCarePlanStatus(patientId, encounterId, episodeId));
	return listsMap;
}
@Override
public void addFrequentIntervention(String elementName, String snomed,
		Integer userId,Integer providerId, Integer isfrmconfig,String categoryType, String codeOid,String groupName) throws Exception {
	java.util.Date today =new java.util.Date();
	FrequentInterventions freqIntervention = new FrequentInterventions();
	freqIntervention.setFrequentinterventionsdescription(elementName);
	freqIntervention.setFrequentinterventionscode(snomed);
	freqIntervention.setFrequentinterventionscodesystemoid(codeOid);
	if(providerId!=0)
		freqIntervention.setFrequentinterventionsuserid(providerId);
	else
		freqIntervention.setFrequentinterventionsuserid(userId);
	freqIntervention.setFrequentinterventionsstatus(1);
	if(isfrmconfig==1)
		freqIntervention.setFrequentinterventionsgroup(groupName);
	else
		freqIntervention.setFrequentinterventionsgroup("Others");
	freqIntervention.setFrequentinterventionscreatedby(userId);
	freqIntervention.setFrequentinterventionscreatedon(new Timestamp(today.getTime()));
	freqIntervention.setFrequentinterventionscategory(categoryType);
	FrequentInterventionsRepository.save(freqIntervention);
}

@Override
public List<Object> fetchFrequentInterventions(Integer userId, String categoryType) {
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
	Root<FrequentInterventions> root = cq.from(FrequentInterventions.class);
	List<Predicate> predicates = new ArrayList<>();
	if(userId!=-1){
		predicates.add(builder.equal(root.get(FrequentInterventions_.frequentinterventionsuserid), userId));
	}
	predicates.add(builder.equal(root.get(FrequentInterventions_.frequentinterventionscategory), categoryType));
	cq.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
	cq.multiselect(root.get(FrequentInterventions_.frequentinterventionsid),root.get(FrequentInterventions_.frequentinterventionscode),root.get(FrequentInterventions_.frequentinterventionsdescription),root.get(FrequentInterventions_.frequentinterventionsgroup));
	cq.orderBy(builder.asc(root.get(FrequentInterventions_.frequentinterventionsgroup)),
			builder.asc(root.get(FrequentInterventions_.frequentinterventionsdescription)));
	List<Object[]> result= entityManager.createQuery(cq).getResultList();
	List<Object>  parsedShrotcuts=new ArrayList<Object>();
	for(Object[]  freqList:result){
		Map<String, String> parsedObject=new HashMap<String, String>();
		try {
			parsedObject.put("freqId", freqList[0].toString());
			parsedObject.put("id", freqList[1].toString());	
			parsedObject.put("name", freqList[2].toString());	
			parsedObject.put("freqInterventionGroup", freqList[3].toString());
			parsedShrotcuts.add(parsedObject);
		} catch (Exception e) {
		}
	}
	return parsedShrotcuts;
}

@Override
public void deleteFrequentIntervention(String delid) {
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaDelete<FrequentInterventions> delete = builder.createCriteriaDelete(FrequentInterventions.class);
	Root<FrequentInterventions> root = delete.from(FrequentInterventions.class);
	if(!delid.equals(-1)) {
	delete.where(builder.equal(root.get(FrequentInterventions_.frequentinterventionscode), delid));
			this.entityManager.createQuery(delete).executeUpdate();
	}
}

@Override
public void UpdateFrequentInterventionGroup(Integer groupVal, String groupName) {
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaUpdate<FrequentInterventions> update = builder.createCriteriaUpdate(FrequentInterventions.class);
	Root<FrequentInterventions> root = update.from(FrequentInterventions.class);
	update.set(root.get(FrequentInterventions_.frequentinterventionsgroup), groupName);
	update.where(builder.equal(root.get(FrequentInterventions_.frequentinterventionsid), groupVal));
	this.entityManager.createQuery(update).executeUpdate();
}

@Override
public Map<String, Object> FrequentInterventionGroups(String groupName) {
	Map<String,Object> listsMap=new HashMap<String,Object>();
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
	Root<FrequentInterventions> root = cq.from(FrequentInterventions.class);
	Predicate predicates ;
	cq.multiselect(root.get(FrequentInterventions_.frequentinterventionsgroup));
	if(!groupName.equals("-1") && !groupName.equals("null") ){
		predicates = builder.like(builder.lower(builder.trim(root.get(FrequentInterventions_.frequentinterventionsgroup))),groupName.toLowerCase().trim()+"%");
		cq.where(predicates);
	}
	cq.orderBy(builder.asc(root.get(FrequentInterventions_.frequentinterventionsgroup)));
	cq.distinct(true);
	List<Object[]> result= entityManager.createQuery(cq).getResultList();
	listsMap.put("groups", result);
	return listsMap;
}

@Override
public List<Object> getCarePlanStatus(Integer patientId, Integer encounterId,Integer episodeId) {
	CriteriaBuilder builder= entityManager.getCriteriaBuilder();
	CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
	Root<PatientClinicalFindings> root = query.from(PatientClinicalFindings.class);
	query.multiselect(root.get(PatientClinicalFindings_.patientClinicalFindingsStatus),
			root.get(PatientClinicalFindings_.patientClinicalFindingsDescription));
	List<Predicate> predicates = new ArrayList<Predicate>();
	if(encounterId!=-1) {
		predicates.add(builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsEncounterId),encounterId));
		predicates.add(builder.equal(builder.function("DATE",Date.class,root.get(PatientClinicalFindings_.patientClinicalFindingsDate)),getEncounterDate(encounterId)));
	}
	else
		predicates.add(builder.equal(builder.function("DATE",Date.class,root.get(PatientClinicalFindings_.patientClinicalFindingsDate)),new Date()));
	if(episodeId!=-1)
		predicates.add(builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsEpisodeId),episodeId));
	query.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
	query.orderBy(builder.desc(root.get(PatientClinicalFindings_.patientClinicalFindingsId)));
	List<Object[]> healthStatus=entityManager.createQuery(query).getResultList();
	List<Object>  healthStatusResult=new ArrayList<Object>();
    for(Object[]  healthStatusValues:healthStatus){
    	Map<String, String> parsedObject=new HashMap<String, String>();
    	try {
			parsedObject.put("healthStatus", healthStatusValues[0].toString());
			parsedObject.put("healthStatusDesc", healthStatusValues[1].toString());	
			healthStatusResult.add(parsedObject);
		} catch (Exception e) {
		}
    }
	return healthStatusResult;
	/*		List<String> GwIDs= new ArrayList<String>();
		GwIDs.addAll(Arrays.asList("0000200200100075000","0000200200100076000","0000200200100038000","0000200200100025000","0000200200100020000","0000200200100023000","0000200200100024000"));
		CriteriaBuilder builder= entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<VitalsParameter> root = query.from(VitalsParameter.class);
		Join<VitalsParameter, CNMCodeSystem> cnmJoin= root.join(VitalsParameter_.cnmCodeSystem, JoinType.LEFT);
		query.multiselect(root.get(VitalsParameter_.vitalsParameterName),
				builder.coalesce(cnmJoin.get(CNMCodeSystem_.cnmCodeSystemCode),""),
				builder.coalesce(cnmJoin.get(CNMCodeSystem_.cnmCodeSystemOid),""));
		query.where(builder.equal(root.get(VitalsParameter_.vitalsParameterIsactive),true),
				root.get(VitalsParameter_.vitalsParameterGwId).in(GwIDs));
		query.orderBy(builder.asc(root.get(VitalsParameter_.vitalsParameterName)));
		List<Object[]> vitals=entityManager.createQuery(query).getResultList();
		List<Object>  parsedVitalParameters=new ArrayList<Object>();
	    for(Object[]  vitalValues:vitals){
	    	Map<String, String> parsedObject=new HashMap<String, String>();
	    	try {
				parsedObject.put("vitalsParameterName", vitalValues[0].toString());
				parsedObject.put("cnmCodeSystemCode", vitalValues[1].toString());	
				parsedObject.put("cnmCodeSystemOid", vitalValues[2].toString());	
				parsedVitalParameters.add(parsedObject);
			} catch (Exception e) {
			}
	    }
		return parsedVitalParameters;*/
	
}
@Override
public void saveCarePlanStatus(Integer patientId, Integer encounterId,
		Integer episodeId, String Description,Integer userId,Integer Status,String code) {
	// TODO Auto-generated method stub
	
	PatientClinicalFindings patientClinicalFindings = new PatientClinicalFindings();
	patientClinicalFindings.setPatientClinicalFindingsPatientId(patientId);
	patientClinicalFindings.setPatientClinicalFindingsEncounterId(encounterId);
	patientClinicalFindings.setPatientClinicalFindingsEpisodeId(episodeId);
	patientClinicalFindings.setPatientClinicalFindingsCreatedBy(userId);
	patientClinicalFindings.setPatientClinicalFindingsModifiedBy(userId);
	if(encounterId!=-1) 
		patientClinicalFindings.setPatientClinicalFindingsDate(new Timestamp(getEncounterDate(encounterId).getTime()));
	else
		patientClinicalFindings.setPatientClinicalFindingsDate(patientClinicalFindingsRepository.findCurrentTimeStamp());
	patientClinicalFindings.setPatientClinicalFindingsCreatedOn(patientClinicalFindingsRepository.findCurrentTimeStamp());
	patientClinicalFindings.setPatientClinicalFindingsModifiedOn(patientClinicalFindingsRepository.findCurrentTimeStamp());
	patientClinicalFindings.setPatientClinicalFindingsDescription(Description);
	patientClinicalFindings.setPatientClinicalFindingsStatus(Status);
	patientClinicalFindings.setPatientClinicalFindingsIsactive(true);
	patientClinicalFindings.setPatientClinicalFindingsCodeSystem("2.16.840.1.113883.6.96");
	patientClinicalFindings.setPatientClinicalFindingsCode(code);
	
	
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<PatientClinicalFindings> findingsQuery = builder.createQuery(PatientClinicalFindings.class);
	Root<PatientClinicalFindings> root = findingsQuery.from(PatientClinicalFindings.class);
	List<Predicate> predicatesForStatus = new ArrayList<>();
	predicatesForStatus.add(builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsPatientId),patientId));
	if(encounterId!=-1) {
		predicatesForStatus.add(builder.equal(builder.function("DATE",Date.class,root.get(PatientClinicalFindings_.patientClinicalFindingsDate)),getEncounterDate(encounterId)));
	}
	else
		predicatesForStatus.add(builder.equal(builder.function("DATE",Date.class,root.get(PatientClinicalFindings_.patientClinicalFindingsDate)),new Date()));
	if(episodeId!=-1)
		predicatesForStatus.add(builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsEpisodeId),episodeId));
	if(encounterId!=-1)
		predicatesForStatus.add(builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsEncounterId),encounterId));
	findingsQuery.where(builder.and(predicatesForStatus.toArray(new Predicate[predicatesForStatus.size()])));
	List<PatientClinicalFindings> statusQuery=entityManager.createQuery(findingsQuery).getResultList();
	if(statusQuery.size()>0) {
		CriteriaUpdate<PatientClinicalFindings> cu = builder.createCriteriaUpdate(PatientClinicalFindings.class);
		Root<PatientClinicalFindings> rootCriteria = cu.from(PatientClinicalFindings.class);
		if(encounterId!=-1)
			cu.set(rootCriteria.get(PatientClinicalFindings_.patientClinicalFindingsDate),new Timestamp(getEncounterDate(encounterId).getTime()));
		else
			cu.set(rootCriteria.get(PatientClinicalFindings_.patientClinicalFindingsDate),patientClinicalFindingsRepository.findCurrentTimeStamp());
		cu.set(rootCriteria.get(PatientClinicalFindings_.patientClinicalFindingsDescription), Description);
		cu.set(rootCriteria.get(PatientClinicalFindings_.patientClinicalFindingsStatus),Status);
		cu.set(rootCriteria.get(PatientClinicalFindings_.patientClinicalFindingsModifiedBy),userId);
		cu.set(rootCriteria.get(PatientClinicalFindings_.patientClinicalFindingsModifiedOn),patientClinicalFindingsRepository.findCurrentTimeStamp());
		cu.set(rootCriteria.get(PatientClinicalFindings_.patientClinicalFindingsCodeSystem),"2.16.840.1.113883.6.96");
		cu.set(rootCriteria.get(PatientClinicalFindings_.patientClinicalFindingsCode),code);

		List<Predicate> predicatesForUpdate = new ArrayList<>();
		if(patientId!=-1)
			predicatesForUpdate.add(builder.equal(rootCriteria.get(PatientClinicalFindings_.patientClinicalFindingsPatientId),patientId));
		if(encounterId!=-1)
			predicatesForUpdate.add(builder.equal(rootCriteria.get(PatientClinicalFindings_.patientClinicalFindingsEncounterId),encounterId));
		if(episodeId!=-1)
			predicatesForUpdate.add(builder.equal(rootCriteria.get(PatientClinicalFindings_.patientClinicalFindingsEpisodeId),episodeId));
		cu.where(builder.and(predicatesForUpdate.toArray(new Predicate[predicatesForUpdate.size()])));
		this.entityManager.createQuery(cu).executeUpdate();
	}
	else {
		patientClinicalFindingsRepository.saveAndFlush(patientClinicalFindings);
	}
}

public List<Object> getEncounterListForEpisode(Integer epId,Integer patientId,Integer encounterId) throws ParseException {
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<Object[]> encListQuery = builder.createQuery(Object[].class);
	Root<Encounter> root = encListQuery.from(Encounter.class);
	Join<Encounter,Chart> encJoin = root.join(Encounter_.chart,JoinType.INNER);
	encListQuery.multiselect(root.get(Encounter_.encounterId),root.get(Encounter_.encounterDate));
	encListQuery.where(builder.equal(root.get(Encounter_.encounterPatientEpisodeid),epId),
			builder.equal(encJoin.get(Chart_.chartPatientid),patientId),builder.notEqual(root.get(Encounter_.encounterId), encounterId));
	List<Object[]> encListResult=entityManager.createQuery(encListQuery).getResultList();
	List<Object>  encListResultArray=new ArrayList<Object>();

	SimpleDateFormat encDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    SimpleDateFormat parsedEncDate = new SimpleDateFormat("MM/dd/yyyy");

	for(Object[] result:encListResult){
		Map<String, String> encListResultObject=new HashMap<String, String>();
		Date date = encDate.parse(result[1].toString());
		try {
			encListResultObject.put("encounterId", result[0].toString());
		encListResultObject.put("encounterDate", parsedEncDate.format(date).toString());	
			encListResultArray.add(encListResultObject);
		} catch (Exception e) {
		}
	}
	return encListResultArray;
}


public List<Object> getImportShortcuts(Integer patientId,Integer encounterId) {
	List<String> GwIDs= new ArrayList<String>();
	GwIDs.addAll(Arrays.asList("0000100200000000000","0000100463001002000"));
	
	CriteriaBuilder builder= entityManager.getCriteriaBuilder();
	CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
	Root<PatientClinicalElements> root= query.from(PatientClinicalElements.class);
	query.multiselect(root.get(PatientClinicalElements_.patientClinicalElementsGwid),
			builder.coalesce(root.get(PatientClinicalElements_.patientClinicalElementsValue),""));
	query.where(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId),
			builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId),
			root.get(PatientClinicalElements_.patientClinicalElementsGwid).in(GwIDs));
	List<Object[]> valueResult=entityManager.createQuery(query).getResultList();
	List<Object> valueArray = new ArrayList<Object>();
	
	for(Object[] result:valueResult) {
		Map<String, String> valueObject=new HashMap<String, String>();
		valueObject.put("elementGwid",result[0].toString());
		valueObject.put("elementValue",result[1].toString());
		valueArray.add(valueObject);
	}
	String planData = getPlanData(encounterId);
	Map<String, String> planObject=new HashMap<String, String>();
	planObject.put("elementGwid","planData");
	planObject.put("elementValue",planData);
	valueArray.add(planObject);
	return valueArray;
}
public List<CarePlanGoalBean>  saveAssistanceStatus(Integer patientId,Integer episodeId,Integer goalId,Integer providerId,Integer status,Integer LevelStatus,Integer encounterId) {
	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	CriteriaUpdate<CarePlanGoal> cu = cb.createCriteriaUpdate(CarePlanGoal.class);
	Root<CarePlanGoal> root = cu.from(CarePlanGoal.class);
	if(status!=-1)
		cu.set(root.get(CarePlanGoal_.carePlanGoalAssistanceStatus), status);
	if(LevelStatus!=-1)
		cu.set(root.get(CarePlanGoal_.carePlanGoalLevelStatus), LevelStatus);
	cu.set(root.get(CarePlanGoal_.carePlanGoalModifiedBy), providerId);
	cu.set(root.get(CarePlanGoal_.careplanGoalMasteredDate), carePlanGoalRepository.findCurrentTimeStamp());
	cu.set(root.get(CarePlanGoal_.carePlanGoalModifiedOn), carePlanGoalRepository.findCurrentTimeStamp());
	cu.where(cb.equal(root.get(CarePlanGoal_.carePlanGoalId),goalId),
			cb.equal(root.get(CarePlanGoal_.carePlanGoalPatientId),patientId));
	this.entityManager.createQuery(cu).executeUpdate();
	List<CarePlanGoalBean> goals = fetchCarePlanGoalBean(-1, -1, patientId,encounterId, episodeId);
	return goals;
}

/**
 * To fetch care plan goals (bean structure)
 * @param goalId
 * @param concernId
 * @param patientId
 * @param encounterId
 * @return List
 */
@SuppressWarnings("rawtypes")
public List<CarePlanGoalBean> fetchLatestGoalBean(Integer goalId,Integer concernId,
		Integer patientId, Integer encounterId,Integer episodeId,String reviewDate) {
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<CarePlanGoalBean> cq = builder.createQuery(CarePlanGoalBean.class);
	Root<CarePlanGoal> root = cq.from(CarePlanGoal.class);
	Join<CarePlanGoal,CarePlanConcern> concernJoin=root.join(CarePlanGoal_.carePlanConcern,JoinType.LEFT);
	Join<CarePlanGoal,CarePlanOutcome> outcomeJoin=root.join(CarePlanGoal_.carePlanOutcome,JoinType.LEFT);
	outcomeJoin.on(builder.equal(builder.function("DATE", Date.class, outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeReviewDate)),getEncounterDate(encounterId)));

	if(reviewDate!=null && !(reviewDate.equalsIgnoreCase("-1"))) {
		Date revDate = null;
		try {
			revDate=new SimpleDateFormat("MM/dd/yyyy").parse(reviewDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		outcomeJoin.on(builder.equal(builder.function("DATE", Date.class,outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeReviewDate)),revDate));
	}
	
	final Subquery<Integer> subquery = cq.subquery(Integer.class);
	final Root<CarePlanOutcome> carePlanOutcome = subquery.from(CarePlanOutcome.class);
	subquery.select(builder.max(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeId)));
	subquery.groupBy(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeGoalId));


	List<Predicate> predicates = new ArrayList<>();
	List<Predicate> predicatesForStatus = new ArrayList<>();

	if(patientId!=-1)
		predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalPatientId), patientId));
	if(concernId!=-1)
		predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalConcernId), concernId));
	if(goalId!=-1)
		predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalId), goalId));
	if(episodeId!=-1)
		predicates.add(builder.equal(root.get(CarePlanGoal_.careplanGoalEpisodeId),episodeId));
	predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalStatus),1));
	
	Selection[] selections=new Selection[]{
			root.get(CarePlanGoal_.carePlanGoalId),
			root.get(CarePlanGoal_.carePlanGoalPatientId),
			root.get(CarePlanGoal_.carePlanGoalEncounterId),
			builder.coalesce(root.get(CarePlanGoal_.carePlanGoalConcernId),-1),
			concernJoin.get(CarePlanConcern_.carePlanConcernDesc),
			builder.coalesce(root.get(CarePlanGoal_.carePlanGoalPriority),0),
			builder.coalesce(root.get(CarePlanGoal_.carePlanGoalType),-1),
			builder.coalesce(root.get(CarePlanGoal_.carePlanGoalTerm),0),
			root.get(CarePlanGoal_.carePlanGoalProviderId),
			root.get(CarePlanGoal_.carePlanGoalDesc),
			builder.coalesce(root.get(CarePlanGoal_.carePlanGoalCode),""),
			builder.coalesce(root.get(CarePlanGoal_.carePlanGoalCodeDescription),""),
			builder.coalesce(root.get(CarePlanGoal_.carePlanGoalCodeOperator),""),
			builder.coalesce(root.get(CarePlanGoal_.carePlanGoalValue),""),
			builder.coalesce(root.get(CarePlanGoal_.carePlanGoalUnit),""),
			builder.coalesce(root.get(CarePlanGoal_.carePlanGoalStatus),0),
			root.get(CarePlanGoal_.carePlanGoalTargetDate),
			root.get(CarePlanGoal_.carePlanGoalNextReviewDate),
			root.get(CarePlanGoal_.carePlanGoalNotes),
			root.get(CarePlanGoal_.carePlanGoalFrom),
			builder.coalesce(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeProgress),0),
			root.get(CarePlanGoal_.carePlanGoalResultStatus),
			root.get(CarePlanGoal_.careplanGoalEpisodeId),
			root.get(CarePlanGoal_.carePlanGoalOrder),
			root.get(CarePlanGoal_.carePlanGoalValueOne),
			builder.coalesce(root.get(CarePlanGoal_.carePlanGoalAssistanceStatus),0),
			builder.coalesce(root.get(CarePlanGoal_.carePlanGoalLevelStatus),0),
			builder.coalesce(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeId),-1)
	};
	cq.select(builder.construct(CarePlanGoalBean.class, selections));
	cq.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
	cq.orderBy(builder.asc(root.get(CarePlanGoal_.carePlanGoalId)));
	List<CarePlanGoalBean> concerns=entityManager.createQuery(cq).getResultList();
	return concerns;
}

public String getLatestOutcomeReviewDate(Integer patientId,Integer encounterId,Integer episodeId) throws ParseException {
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<Object> cq = builder.createQuery();
	Root<CarePlanGoal> root = cq.from(CarePlanGoal.class);
	Join<CarePlanGoal,CarePlanOutcome> outcomeJoin=root.join(CarePlanGoal_.carePlanOutcome,JoinType.INNER);
	cq.select(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeReviewDate));
	List<Predicate> predicates = new ArrayList<>();

	if(patientId!=-1)
		predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalPatientId), patientId));
	if(episodeId!=-1)
		predicates.add(builder.equal(root.get(CarePlanGoal_.careplanGoalEpisodeId),episodeId));	
	
	cq.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
	cq.orderBy(builder.desc(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeReviewDate)));
	cq.distinct(true);

	List<Object> resultList = entityManager.createQuery(cq).getResultList();
	String reviewDate= "-1";
	SimpleDateFormat reviewDt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    SimpleDateFormat parsedReviewDt = new SimpleDateFormat("MM/dd/yyyy");
	if(resultList.size()>0) {
		Date date = reviewDt.parse(resultList.get(0).toString());
		reviewDate= parsedReviewDt.format(date);
	}
	return reviewDate;
}

public List<GeneralShortcut> fetchCarePlanInterventionShortcuts() {
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<GeneralShortcut> cq = builder.createQuery(GeneralShortcut.class);
	Root<GeneralShortcut> root = cq.from(GeneralShortcut.class);
	cq.where(builder.equal(root.get(GeneralShortcut_.generalShortcutIsactive), true),
			builder.equal(root.get(GeneralShortcut_.generalShortcutMapGroupId), 150));
	cq.orderBy(builder.desc(root.get(GeneralShortcut_.generalShortcutDescription)));
	cq.distinct(true);
	List<GeneralShortcut> resultList=entityManager.createQuery(cq).getResultList();
	return resultList;
	
	/*CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<CarePlanInterventionShortcut> cq = builder.createQuery(CarePlanInterventionShortcut.class);
	Root<CarePlanInterventionShortcut> root = cq.from(CarePlanInterventionShortcut.class);
	cq.where(builder.equal(root.get(CarePlanInterventionShortcut_.carePlanInterventionShortcutStatus), 1));
	cq.orderBy(builder.desc(root.get(CarePlanInterventionShortcut_.carePlanInterventionShortcutDesc)));
	cq.distinct(true);
	List<CarePlanInterventionShortcut> resultList=entityManager.createQuery(cq).getResultList();
	return resultList;*/
} 
}

