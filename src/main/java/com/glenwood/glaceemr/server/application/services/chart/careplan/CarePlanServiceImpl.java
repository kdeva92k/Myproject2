package com.glenwood.glaceemr.server.application.services.chart.careplan;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
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
import org.springframework.stereotype.Service;

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
import com.glenwood.glaceemr.server.application.models.CarePlanOutcome;
import com.glenwood.glaceemr.server.application.models.CarePlanOutcome_;
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
import com.glenwood.glaceemr.server.application.models.GeneralShortcut;
import com.glenwood.glaceemr.server.application.models.GeneralShortcut_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.UnitsOfMeasure;
import com.glenwood.glaceemr.server.application.models.UnitsOfMeasure_;
import com.glenwood.glaceemr.server.application.models.VitalGroup;
import com.glenwood.glaceemr.server.application.models.VitalGroup_;
import com.glenwood.glaceemr.server.application.models.VitalsParameter;
import com.glenwood.glaceemr.server.application.models.VitalsParameter_;
import com.glenwood.glaceemr.server.application.repositories.CarePlanConcernRepository;
import com.glenwood.glaceemr.server.application.repositories.CarePlanGoalRepository;
import com.glenwood.glaceemr.server.application.repositories.CarePlanOutcomeRepository;
import com.glenwood.glaceemr.server.application.repositories.CarePlanInterventionRepository;
import com.glenwood.glaceemr.server.application.repositories.CarePlanSummaryRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterPlanRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalElementsRepository;
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

	/**
	 * To fetch care plan concerns
	 * @param concernId
	 * @param patientId
	 * @param categoryId
	 * @return List
	 */
	@Override
	public List<CarePlanConcern> fetchCarePlanConcerns(Integer concernId,Integer patientId,Integer categoryId,Integer episodeId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarePlanConcern> cq = builder.createQuery(CarePlanConcern.class);
		Root<CarePlanConcern> root = cq.from(CarePlanConcern.class);
		List<Predicate> predicates = new ArrayList<>();
		if(patientId!=-1)
			predicates.add(builder.equal(root.get(CarePlanConcern_.carePlanConcernPatientId), patientId));
		if(categoryId!=-1)
			predicates.add(builder.equal(root.get(CarePlanConcern_.carePlanConcernCategoryId), categoryId));
		if(concernId!=-1)
			predicates.add(builder.equal(root.get(CarePlanConcern_.carePlanConcernId), concernId));
		if(episodeId!=-1)
			predicates.add(builder.equal(root.get(CarePlanConcern_.careplanConcernEpisodeId), episodeId));
		predicates.add(builder.equal(root.get(CarePlanConcern_.carePlanConcernStatus),1));
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		List<CarePlanConcern> concerns=entityManager.createQuery(cq).getResultList();
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
	public List<CarePlanGoal> fetchCarePlanGoals(Integer goalId,Integer concernId,
			Integer patientId, Integer encounterId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarePlanGoal> cq = builder.createQuery(CarePlanGoal.class);
		Root<CarePlanGoal> root = cq.from(CarePlanGoal.class);
		List<Predicate> predicates = new ArrayList<>();
		if(patientId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalPatientId), patientId));
		if(encounterId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalEncounterId), encounterId));
		if(concernId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalConcernId), concernId));
		if(goalId!=-1)
			predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalId), goalId));
		predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalStatus),1));
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		List<CarePlanGoal> concerns=entityManager.createQuery(cq).getResultList();
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
		if(encounterId!=-1)
		outcomeJoin.on(builder.equal(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeEncounterId),encounterId));

		final Subquery<Integer> subquery = cq.subquery(Integer.class);
		final Root<CarePlanOutcome> carePlanOutcome = subquery.from(CarePlanOutcome.class);
		subquery.select(builder.max(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeId)));
		subquery.groupBy(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeGoalId));

	/*	final Subquery<Integer> subqueryEncounter = cq.subquery(Integer.class);
		final Root<CarePlanOutcome> carePlanOutcomeEncounter = subqueryEncounter.from(CarePlanOutcome.class);
		subqueryEncounter.select(builder.max(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeId)));
		subqueryEncounter.where(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeGoalId));

		subqueryEncounter.groupBy(carePlanOutcome.get(CarePlanOutcome_.carePlanOutcomeGoalId));*/

		
		List<Predicate> predicates = new ArrayList<>();
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
		predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalStatus),1));
        
		
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
	public List<CarePlanOutcomeBean> fetchCarePlanOutcomes(Integer outcomeId,Integer goalId, Integer patientId, Integer encounterId,Integer episodeId) {
		
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

		cq.select(builder.construct(CarePlanOutcomeBean.class, selections));
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(builder.desc(root.get(CarePlanOutcome_.carePlanOutcomeId)));
		List<CarePlanOutcomeBean> outcomes=entityManager.createQuery(cq).getResultList();
		return outcomes;
	}
	
	/**
	 * To save care plan concern from given Bean
	 * @param CarePlanConcernBean
	 * @return List
	 */
	@Override
	public List<CarePlanConcern> saveCarePlanConcern(CarePlanConcernBean carePlanConcernJSON) {
		
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
		carePlanConcernRepository.saveAndFlush(carePlanConcern);
		List<CarePlanConcern> carePlanConcerns=fetchCarePlanConcerns(-1,carePlanConcernJSON.getConcernPatientId(),-1,carePlanConcernJSON.getEpisodeId());
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
		if(carePlanGoalData.getCarePlanGoalId()!=-1)
			carePlanGoal.setCarePlanGoalId(carePlanGoalData.getCarePlanGoalId());
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
		carePlanGoal.setCarePlanGoalCodeSystem("2.16.840.1.113883.6.96");
		carePlanGoal.setCarePlanGoalCodeSystemName("SNOMED");
		carePlanGoal.setCarePlanGoalCodeOperator(carePlanGoalData.getCarePlanGoalCodeOperator());
		carePlanGoal.setCarePlanGoalValue(carePlanGoalData.getCarePlanGoalValue());
		carePlanGoal.setCarePlanGoalUnit(carePlanGoalData.getCarePlanGoalUnit());		
		carePlanGoal.setCarePlanGoalStatus(carePlanGoalData.getCarePlanGoalStatus());
		carePlanGoal.setCareplanGoalEpisodeId(carePlanGoalData.getEpisodeId());
		carePlanGoal.setCareplanGoalResultStatus(carePlanGoalData.getCarePlanGoalResultStatus());


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
		carePlanGoal.setCarePlanGoalCreatedBy(carePlanGoalData.getCarePlanGoalCreatedBy());
		carePlanGoal.setCarePlanGoalModifiedBy(carePlanGoalData.getCarePlanGoalModifiedBy());
		carePlanGoal.setCarePlanGoalCreatedOn(carePlanConcernRepository.findCurrentTimeStamp());
		carePlanGoal.setCarePlanGoalCreatedOn(carePlanConcernRepository.findCurrentTimeStamp());
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
	public List<CarePlanGoalBean>  saveCarePlanOutcomes(Integer goalId,Integer providerId,Integer patientId,Integer encounterId,Integer progress,String reviewDate,String targetDate,String notes,Integer status,Integer episodeId) {
		CarePlanOutcome carePlanOutcome=new CarePlanOutcome();
		carePlanOutcome.setCarePlanOutcomeGoalId(goalId);
		carePlanOutcome.setCarePlanOutcomePatientId(patientId);
		carePlanOutcome.setCarePlanOutcomeEncounterId(encounterId);
		carePlanOutcome.setCarePlanOutcomeProviderId(providerId);
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
			carePlanOutcome.setCarePlanOutcomeReviewDate(new Timestamp(new java.sql.Date(getEncounterDate(encounterId).getTime()).getTime()));
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
				cu.where(cb.equal(rootCriteria.get(CarePlanGoal_.carePlanGoalId),goalId),
						cb.equal(rootCriteria.get(CarePlanGoal_.carePlanGoalPatientId),patientId));
				this.entityManager.createQuery(cu).executeUpdate();
			}
		catch(Exception e){}
		}	
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CarePlanGoal> carePlanQuery = builder.createQuery(CarePlanGoal.class);
		Root<CarePlanGoal> carePlanRoot = carePlanQuery.from(CarePlanGoal.class);
		Join<CarePlanGoal,CarePlanOutcome> outcomeJoin=carePlanRoot.join(CarePlanGoal_.carePlanOutcome,JoinType.INNER);
		outcomeJoin.on(builder.equal(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeEncounterId),encounterId));
		List<Predicate> predicatesForGoal = new ArrayList<>();
		predicatesForGoal.add(builder.equal(carePlanRoot.get(CarePlanGoal_.carePlanGoalPatientId), patientId));
		predicatesForGoal.add(builder.equal(carePlanRoot.get(CarePlanGoal_.carePlanGoalId), goalId));
		predicatesForGoal.add(builder.equal(outcomeJoin.get(CarePlanOutcome_.carePlanOutcomeEncounterId),encounterId));

		carePlanQuery.where(builder.and(predicatesForGoal.toArray(new Predicate[predicatesForGoal.size()])));
		List<CarePlanGoal> goals=entityManager.createQuery(carePlanQuery).getResultList();

		if(goals.size()>0) {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaUpdate<CarePlanOutcome> cu = cb.createCriteriaUpdate(CarePlanOutcome.class);
			Root<CarePlanOutcome> rootCriteria = cu.from(CarePlanOutcome.class);
			if(progress!=-1)
				cu.set(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeProgress),progress);
			cu.set(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeProviderId),providerId);
			cu.set(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeModifiedBy),providerId);
			cu.set(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeModifiedOn),carePlanOutcomeRepository.findCurrentTimeStamp());
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
			cu.where(cb.equal(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeGoalId),goalId),
					cb.equal(rootCriteria.get(CarePlanOutcome_.carePlanOutcomePatientId),patientId),
					cb.equal(rootCriteria.get(CarePlanOutcome_.carePlanOutcomeEncounterId),encounterId));
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
		listsMap.put("concernsList", fetchCarePlanConcerns(-1,patientId,-1,episodeId));
		listsMap.put("goalsList", fetchCarePlanGoalBean(-1,-1,patientId,encounterId,episodeId));
		listsMap.put("interventionsList", fetchInterventionData(-1,-1,-1,patientId,encounterId));
		listsMap.put("unitsList",getUnitsOfMeasures() );
		listsMap.put("vitalsList", getVitalParameters());
		listsMap.put("shortcutsList", fetchCarePlanShortcuts(episodeTypeId));
		listsMap.put("previousVisitShortcutsList", fetchPreviousCarePlanGoalShortcuts(patientId,episodeTypeId,previousEpisodeId));
		listsMap.put("employeeList", fetchEmployeeList());
		listsMap.put("lastVisitProgress",getLastVisitProgressStatus(patientId,encounterId,episodeId));
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
	 */
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
	}

	/**
	 * To get vital elements of particular encounterId
	 * @param encounterId
	 * @return List
	 */
	private List<Object[]> getVitals(Integer encounterId) {
		CriteriaBuilder builder= entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<PatientClinicalElements> root= query.from(PatientClinicalElements.class);
		Join<PatientClinicalElements, VitalsParameter> vitalJoin= root.join(PatientClinicalElements_.vitalsParameter, JoinType.INNER);
		Join<PatientClinicalElements, ClinicalElements> clinicalJoin= root.join(PatientClinicalElements_.clinicalElement, JoinType.INNER);
		Join<VitalsParameter, UnitsOfMeasure> unitsJoin= vitalJoin.join(VitalsParameter_.unitsOfMeasureTable, JoinType.LEFT);
		Join<VitalsParameter, VitalGroup> groupJoin= vitalJoin.join(VitalsParameter_.vitalGroup, JoinType.INNER);
		Join<ClinicalElements, ClinicalElementsOptions> optionsJoin= clinicalJoin.join(ClinicalElements_.clinicalElementsOptions, JoinType.LEFT);
		Join<ClinicalElements, ClinicalTextMapping> textJoin= clinicalJoin.join(ClinicalElements_.clinicalTextMappings, JoinType.LEFT);

		query.multiselect(vitalJoin.get(VitalsParameter_.vitalsParameterName),
				root.get(PatientClinicalElements_.patientClinicalElementsGwid),
				root.get(PatientClinicalElements_.patientClinicalElementsValue),
				unitsJoin.get(UnitsOfMeasure_.unitsOfMeasureCode),
				optionsJoin.get(ClinicalElementsOptions_.clinicalElementsOptionsName),
				clinicalJoin.get(ClinicalElements_.clinicalElementsDatatype),
				textJoin.get(ClinicalTextMapping_.clinicalTextMappingAssociatedElement));

		query.where(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId),
				builder.or(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsValue),optionsJoin.get(ClinicalElementsOptions_.clinicalElementsOptionsValue)),
						builder.isNull(optionsJoin.get(ClinicalElementsOptions_.clinicalElementsOptionsGwid))));

		query.orderBy(builder.asc(groupJoin.get(VitalGroup_.vitalGroupOrderby)),
				builder.asc(vitalJoin.get(VitalsParameter_.vitalsParameterId)));

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
	private List<VitalsParameter> getVitalParameters(){
		CriteriaBuilder builder= entityManager.getCriteriaBuilder();
		CriteriaQuery<VitalsParameter> query= builder.createQuery(VitalsParameter.class);
		Root<VitalsParameter> root = query.from(VitalsParameter.class);
		query.where(builder.equal(root.get(VitalsParameter_.vitalsParameterIsactive),true));
		query.orderBy(builder.asc(root.get(VitalsParameter_.vitalsParameterName)));
		List<VitalsParameter> vitals=entityManager.createQuery(query).getResultList();
		return vitals;
	}
	
	/**
	 * To parse vitals to JSON
	 * @param vitalsList
	 * @return
	 * @throws JSONException
	 */
	private JSONArray parseVitals(List<Object[]> vitalsList) throws JSONException {
		JSONArray vitals= new JSONArray();
		for(int i=0; i<vitalsList.size(); i++){
			JSONObject vital= new JSONObject();
			Object[] vitalsObj= vitalsList.get(i);
			String name= vitalsObj[0].toString();
			String gwid= vitalsObj[1].toString();
			String value= vitalsObj[2].toString();
			String units= vitalsObj[3].toString();
			String optionname= vitalsObj[4]!= null? vitalsObj[4].toString(): "";
			int datatype= Integer.parseInt(vitalsObj[5].toString());
			String assgwid= vitalsObj[6]!= null? vitalsObj[6].toString(): "";
			if(datatype == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION){
				value= optionname;
			}else if(datatype == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION){
				if(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("t"))
					value= "Yes";
				else
					value= "No";
			}
			else if(name.toLowerCase().contains("systolic")){
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
					if(name.contains("systolic")){
						name = name.substring(0,name.indexOf("systolic"))+"BP (mmHg)";
					}else if(name.contains("Systolic")){
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
				vitals.put(vital);
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
		List<Object[]> vitals= getVitals(encounterId);
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
	public List<Object> fetchCarePlanShortcuts(Integer categoryId){
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
		cq.where(builder.equal(concernJoin.get(CarePlanConcernShortcut_.carePlanConcernShortcutCategoryId),categoryId));
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
	 */
	
	@Override
	public Map<String, Object> importCarePlanShortcuts(Integer patientId,
			Integer encounterId, String shortcutIDs,Integer providerId,Integer episodeId,Integer shortcutTerm,Integer categoryId,Integer previousEpisodeId,Integer summaryMode) {
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
					carePlanConcern.setCarePlanConcernModifiedBy(providerId);
					carePlanConcern.setCarePlanConcernCreatedOn(carePlanConcernRepository.findCurrentTimeStamp());
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
					carePlanGoal.setCarePlanGoalCreatedOn(carePlanConcernRepository.findCurrentTimeStamp());
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
			Integer encounterId,Integer episodeId, String gwid) {
		Map<String,Object> listsMap=new HashMap<String,Object>();
		listsMap.put("concernsList", fetchCarePlanConcerns(-1,patientId,-1,episodeId));
		listsMap.put("goalsList", fetchCarePlanGoalBean(-1,-1,patientId,encounterId,episodeId));
		listsMap.put("unitsList",getUnitsOfMeasures() );
		listsMap.put("vitalsList", getVitalParameters());
		listsMap.put("subjectiveData", getSubjectiveData(patientId,encounterId,gwid));
		listsMap.put("planData", getPlanData(encounterId));
		listsMap.put("hpiShortcut", getShortcuts(1));
		listsMap.put("planShortcut", getShortcuts(5));
		listsMap.put("lastVisitProgress",getLastVisitProgressStatus(patientId,encounterId,episodeId));
		return listsMap;
	}
	
	public  List<PatientClinicalElements> getSubjectiveData(Integer patientId,Integer encounterId,String gwid){
		List<PatientClinicalElements> patientElem= new ArrayList<PatientClinicalElements>();
		patientElem=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getByPatEncGwId(patientId, encounterId,gwid));
		/*if((patientElem=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getByPatEncGwId(patientId, encounterId,gwid))).size()>0){
			System.out.println(" entering if");
			subjectiveData=patientElem.get(0).getPatientClinicalElementsValue();
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
		if(categoryId!=-1)
		      predicates.add(builder.equal(concernJoin.get(CarePlanConcern_.carePlanConcernCategoryId),categoryId ));predicates.add(builder.equal(root.get(CarePlanGoal_.carePlanGoalStatus),1));
		if(previousEpisodeId!=-1)
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
					carePlanGoal.setCarePlanGoalCreatedOn(carePlanConcernRepository.findCurrentTimeStamp());
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
	public List<CarePlanInterventionBean> fetchCarePlanInterventions(Integer patientId, Integer encounterId, Integer interventionMode, String dxCode) {
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
public List<CarePlanInterventionBean> saveInterventionData(CarePlanInterventionBean carePlanInterventionBean) {
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
		carePlanIntervention.setCareplanInterventionCodeSystemName("SNOMED");
		carePlanIntervention.setCareplanInterventionCodeSystem("2.16.840.1.113883.6.96");
		carePlanIntervention.setCareplanInterventionStatus(1);
		carePlanIntervention.setCareplanInterventionOrderedBy(carePlanInterventionBean.getInterventionOrderedBy());
		carePlanIntervention.setCareplanInterventionOrderedOn(new Timestamp(today.getTime()));
		carePlanIntervention.setCareplanInterventionNotes(carePlanInterventionBean.getInterventionNotes());
		carePlanInterventionRepository.save(carePlanIntervention);
		List<CarePlanInterventionBean> carePlanInterventions=fetchInterventionData(-1,-1,-1,carePlanInterventionBean.getInterventionPatientId(),carePlanInterventionBean.getInterventionEncounterId());
		return carePlanInterventions;

}

public List<CarePlanSummaryBean> fetchCarePlanSummaryBean(Integer goalId,Integer concernId,
		Integer patientId, Integer encounterId,Integer episodeId) {

	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<CarePlanSummaryBean> cq = builder.createQuery(CarePlanSummaryBean.class);
	Root<CarePlanSummary> root = cq.from(CarePlanSummary.class);
	Join<CarePlanSummary,CarePlanConcern> concernJoin=root.join(CarePlanSummary_.carePlanConcern,JoinType.LEFT);
	Join<CarePlanSummary,CarePlanGoal> goalJoin=root.join(CarePlanSummary_.carePlanGoal,JoinType.LEFT);

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
	};
	cq.select(builder.construct(CarePlanSummaryBean.class, selections));
	cq.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
	cq.orderBy(builder.asc(root.get(CarePlanSummary_.carePlanSummaryId)));
	List<CarePlanSummaryBean> concerns=entityManager.createQuery(cq).getResultList();
	return concerns;

}

@Override
public Map<String, Object> getCarePlanSummaryData(Integer patientId, Integer episodeId,Integer encounterId,Integer episodeTypeId) 
{
	getSummaryData(patientId, episodeId, encounterId);
	Map<String,Object> listsMap=new HashMap<String,Object>();
	List<CarePlanSummaryBean> res= fetchCarePlanSummaryBean(-1,-1,patientId,-1,episodeId);
	listsMap.put("shortcutsList", fetchCarePlanShortcuts(episodeTypeId));
	listsMap.put("goalsList", res);
	return listsMap;		
}

public void getSummaryData(Integer patientId,Integer episodeId,Integer encounterId)
{
	List<CarePlanGoalBean> res= fetchCarePlanGoalBean(-1,-1,patientId,encounterId,episodeId);
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
		
		if(carePlanSummary==null)
		{
			carePlanSummary = new CarePlanSummary();
			carePlanSummary.setCarePlanSummaryPatientId(obj.getCarePlanGoalPatientId());
			carePlanSummary.setCarePlanSummaryEncounterId(obj.getCarePlanGoalEncounterId());
			carePlanSummary.setCarePlanSummaryGoalId(obj.getCarePlanGoalId());
			carePlanSummary.setCarePlanSummaryAvgVal(obj.getAggregateValue());
			carePlanSummary.setCarePlanSummaryConcernId(obj.getCarePlanGoalConcernId());
			carePlanSummary.setCarePlanSummaryEpisodeId(obj.getEpisodeId());
			carePlanSummary.setCarePlanSummaryGoalProgress(obj.getCarePlanGoalProgress());
			carePlanSummaryRepository.saveAndFlush(carePlanSummary);
		}

		
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
		SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
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
public void saveCarePlanSummaryData(String completeJSON,Integer userId) throws Exception {
	java.util.Date today =new java.util.Date();
	SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
	JSONObject st1 = new JSONObject(completeJSON);
	String st2 = st1.getString("summary");
	JSONArray st3 =new JSONArray(st2);
	for(int i=0; i<st3.length();i++){
	JSONObject object = st3.getJSONObject(i);
	int sumId = object.getInt("carePlanSummaryId");
	String sumCom = object.get("carePlanSummaryComments").toString();
	int sumAvg =  object.getInt("carePlanSummaryAggregate");
	String sumDate = object.get("carePlanSummaryMasteredDate").toString();
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaUpdate<CarePlanSummary> update = builder.createCriteriaUpdate(CarePlanSummary.class);
	Root<CarePlanSummary> root = update.from(CarePlanSummary.class);
	update.set(root.get(CarePlanSummary_.carePlanSummaryAvgVal), sumAvg);
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

	}
	
	}

public List<Object[]> getLastVisitProgressStatus(Integer patientId,Integer encounterId, Integer episodeId) {
	List<Object[]> progress = new ArrayList<Object[]>();
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<Integer> cq = builder.createQuery(Integer.class);
	Root<Encounter> root = cq.from(Encounter.class);
	Join<Encounter, Chart> chartJoin=root.join(Encounter_.chart,JoinType.INNER);
	cq.select(builder.max(root.get(Encounter_.encounterId)));
	cq.where(builder.and(builder.equal(root.get(Encounter_.encounterPatientEpisodeid),episodeId),
			builder.equal(chartJoin.get(Chart_.chartPatientid),patientId),
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

}