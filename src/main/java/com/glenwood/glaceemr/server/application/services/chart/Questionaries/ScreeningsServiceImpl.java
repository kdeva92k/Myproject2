package com.glenwood.glaceemr.server.application.services.chart.Questionaries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.glenwood.glaceemr.server.application.models.CarePlanConcern_;
import com.glenwood.glaceemr.server.application.models.CarePlanGoal_;
import com.glenwood.glaceemr.server.application.models.CarePlanIntervention;
import com.glenwood.glaceemr.server.application.models.CarePlanIntervention_;
import com.glenwood.glaceemr.server.application.models.CarePlanSummary_;
import com.glenwood.glaceemr.server.application.models.FrequentInterventions;
import com.glenwood.glaceemr.server.application.models.FrequentInterventions_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalFindings;
import com.glenwood.glaceemr.server.application.models.PatientClinicalFindings_;
import com.glenwood.glaceemr.server.application.models.RiskAssessment;
import com.glenwood.glaceemr.server.application.models.RiskAssessment_;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalFindingsRepository;
import com.glenwood.glaceemr.server.application.repositories.RiskAssessmentRepository;

@Service
@Transactional
public class ScreeningsServiceImpl implements ScreeningsService{
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	RiskAssessmentRepository riskAssessmentRepository;
	
	@Autowired
	PatientClinicalFindingsRepository patientClinicalFindingsRepository;
	
	@Override
	public List<RiskAssessmentBean> getScreeningsList(Integer patientId,Integer encounterId,Integer riskAssessmentId, Integer riskscreenId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RiskAssessmentBean> cq = builder.createQuery(RiskAssessmentBean.class);
		Root<RiskAssessment> root = cq.from(RiskAssessment.class);
		List<Predicate> predicates = new ArrayList<>();
		if(patientId!=-1)
			predicates.add(builder.equal(root.get(RiskAssessment_.riskAssessmentPatientId), patientId));
		if(encounterId!=-1)
			predicates.add(builder.equal(root.get(RiskAssessment_.riskAssessmentEncounterId), encounterId));
		if(riskAssessmentId!=-1)
			predicates.add(builder.equal(root.get(RiskAssessment_.riskAssessmentId), riskAssessmentId));
		if(riskscreenId!=-1)
			predicates.add(builder.equal(root.get(RiskAssessment_.riskAssessmentScreeningId), riskscreenId));
		@SuppressWarnings("rawtypes")
		Selection[] selectedColumns = new Selection[]{
				root.get(RiskAssessment_.riskAssessmentId),
				root.get(RiskAssessment_.riskAssessmentPatientId),
				root.get(RiskAssessment_.riskAssessmentEncounterId),
				root.get(RiskAssessment_.riskAssessmentDescription),
				root.get(RiskAssessment_.riskAssessmentCode),
				root.get(RiskAssessment_.riskAssessmentCodeSystem),
				root.get(RiskAssessment_.riskAssessmentCodeSystemName),
				root.get(RiskAssessment_.riskAssessmentStatus),
				root.get(RiskAssessment_.riskAssessmentNotes),
				root.get(RiskAssessment_.riskAssessmentCreatedBy),
				root.get(RiskAssessment_.riskAssessmentCreatedOn),
				root.get(RiskAssessment_.riskAssessmentResultValue),
				root.get(RiskAssessment_.riskAssessmentResultDescription),
				root.get(RiskAssessment_.riskAssessmentResultCode),
				root.get(RiskAssessment_.riskAssessmentOrderedBy),
				root.get(RiskAssessment_.riskAssessmentOrderedOn),
				root.get(RiskAssessment_.riskAssessmentDate),
				root.get(RiskAssessment_.riskAssessmentScreeningName),
				root.get(RiskAssessment_.riskAssessmentScreeningCode),
				root.get(RiskAssessment_.riskAssessmentScreeningCodeSystem),
				root.get(RiskAssessment_.riskAssessmentScreeningCodeSystemOid),
				root.get(RiskAssessment_.riskAssessmentScreeningId),
		};
		cq.select(builder.construct(RiskAssessmentBean.class, selectedColumns));
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(builder.desc(root.get(RiskAssessment_.riskAssessmentDate)));
		List<RiskAssessmentBean> screenings=entityManager.createQuery(cq).getResultList();
		return screenings;
	}

	/*@Override
	public List<Object> fetchFrequentInterventions(Integer userId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<FrequentInterventions> root = cq.from(FrequentInterventions.class);
		List<Predicate> predicates = new ArrayList<>();
		if(userId!=-1){
			predicates.add(builder.equal(root.get(FrequentInterventions_.frequentinterventionsuserid), userId));
			predicates.add(builder.equal(root.get(FrequentInterventions_.frequentinterventionscategory), "SCREENINGS"));
			cq.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		}
		cq.multiselect(root.get(FrequentInterventions_.frequentinterventionsid),root.get(FrequentInterventions_.frequentinterventionscode),root.get(FrequentInterventions_.frequentinterventionsdescription));
		cq.orderBy(builder.asc(root.get(FrequentInterventions_.frequentinterventionsdescription)));
		List<Object[]> result= entityManager.createQuery(cq).getResultList();
		List<Object>  parsedShrotcuts=new ArrayList<Object>();
		for(Object[]  freqList:result){
			Map<String, String> parsedObject=new HashMap<String, String>();
			try {
				parsedObject.put("id", freqList[0].toString());
				parsedObject.put("code", freqList[1].toString());	
				parsedObject.put("name", freqList[2].toString());	
				parsedShrotcuts.add(parsedObject);
			} catch (Exception e) {
			}
		}
		return parsedShrotcuts;
	}*/

	@Override
	public String getSearchScreenings(String searchString) {
		String hubURL = null;
		try {
			hubURL = "http://hub-icd10.glaceemr.com/DataGateway/ScreeningServices/searchScreening?keyword="+URLEncoder.encode(searchString, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getDataFromHub( hubURL);
	}

	@Override
	public String getScreeningInfo(Integer screenId) {
		String hubURL = null;
		try {
			hubURL = "http://hub-icd10.glaceemr.com/DataGateway/ScreeningServices/getScreeningInfo?id="+screenId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getDataFromHub( hubURL);
	}
	
	public String getDataFromHub(String hubUrl){
		try{
			URL url = new URL(hubUrl);
			URLConnection conn = url.openConnection();
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return (sb.toString());
		}catch(IOException e){
			e.printStackTrace();
			return "{Error:\""+e.getMessage()+"\"}";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";

	}

	@Override
	public void saveScreeningsInfo(RiskAssessmentBean riskAssessmentBean) {
		RiskAssessment riskAssessment=new RiskAssessment();
		if(riskAssessmentBean.getRiskAssessmentId()!=-1)
			riskAssessment.setRiskAssessmentId(riskAssessmentBean.getRiskAssessmentId());
			riskAssessment.setRiskAssessmentPatientId(riskAssessmentBean.getRiskAssessmentPatientId());
			riskAssessment.setRiskAssessmentEncounterId(riskAssessmentBean.getRiskAssessmentEncounterId());
			riskAssessment.setRiskAssessmentDescription(riskAssessmentBean.getRiskAssessmentDescription());
			riskAssessment.setRiskAssessmentCode(riskAssessmentBean.getRiskAssessmentCode());
			riskAssessment.setRiskAssessmentCodeSystem(riskAssessmentBean.getRiskAssessmentCodeSystem());
			riskAssessment.setRiskAssessmentCodeSystemName(riskAssessmentBean.getRiskAssessmentCodeSystemName());
			riskAssessment.setRiskAssessmentStatus(riskAssessmentBean.getRiskAssessmentStatus());
			riskAssessment.setRiskAssessmentNotes(riskAssessmentBean.getRiskAssessmentNotes());
			riskAssessment.setRiskAssessmentCreatedBy(riskAssessmentBean.getRiskAssessmentCreatedBy());
			riskAssessment.setRiskAssessmentCreatedOn(riskAssessmentRepository.findCurrentTimeStamp());
			riskAssessment.setRiskAssessmentResultValue(riskAssessmentBean.getRiskAssessmentResultValue());
			riskAssessment.setRiskAssessmentResultDescription(riskAssessmentBean.getRiskAssessmentResultDescription());
			riskAssessment.setRiskAssessmentResultCode(riskAssessmentBean.getRiskAssessmentResultCode());
			riskAssessment.setRiskAssessmentOrderedBy(riskAssessmentBean.getRiskAssessmentOrderedBy());
			riskAssessment.setRiskAssessmentOrderedOn(riskAssessmentRepository.findCurrentTimeStamp());
			riskAssessment.setRiskAssessmentDate(riskAssessmentRepository.findCurrentTimeStamp());
			riskAssessment.setRiskAssessmentScreeningName(riskAssessmentBean.getRiskAssessmentScreeningName());
			riskAssessment.setRiskAssessmentScreeningCode(riskAssessmentBean.getRiskAssessmentScreeningCode());
			riskAssessment.setRiskAssessmentScreeningCodeSystem(riskAssessmentBean.getRiskAssessmentScreeningCodeSystem());
			riskAssessment.setRiskAssessmentScreeningCodeSystemOid(riskAssessmentBean.getRiskAssessmentScreeningCodeSystemOid());
			riskAssessment.setRiskAssessmentScreeningId(riskAssessmentBean.getRiskAssessmentScreeningId());
			riskAssessmentRepository.saveAndFlush(riskAssessment);
	}

	@Override
	public void SaveScreeningsquestions(List<PatientClinicalFindingsBean> patientClinicalFindingsBeans,Integer patientId, Integer encounterId, Integer riskAssessmentId,Integer userId,Integer Mode) {
		// TODO Auto-generated method stub
		java.util.Date today =new java.util.Date();
		SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
		List<PatientClinicalFindingsBean> patientClinicalFindings=new ArrayList<PatientClinicalFindingsBean>();
		for(PatientClinicalFindingsBean patientClinicalFindingsBean :patientClinicalFindingsBeans){
			PatientClinicalFindings patientClinicalFindingsdata = null;
			if(patientClinicalFindingsBean.getPatientClinicalFindingsId()!=-1 && Mode!=1){
				patientClinicalFindingsdata=patientClinicalFindingsRepository.findOne(patientClinicalFindingsBean.getPatientClinicalFindingsId());
				patientClinicalFindingsdata.setPatientClinicalFindingsId(patientClinicalFindingsBean.getPatientClinicalFindingsId());
			}
			else
			{
				//patientClinicalFindingsdata=applicationContext.getBean(PatientClinicalFindings.class);
				patientClinicalFindingsdata = new PatientClinicalFindings();
			}
			if(Mode!=1){
				patientClinicalFindingsdata.setPatientClinicalFindingsPatientId(patientClinicalFindingsBean.getPatientClinicalFindingsPatientId());
				patientClinicalFindingsdata.setPatientClinicalFindingsEncounterId(patientClinicalFindingsBean.getPatientClinicalFindingsEncounterId());
			}else{
				patientClinicalFindingsdata.setPatientClinicalFindingsPatientId(patientId);
				patientClinicalFindingsdata.setPatientClinicalFindingsEncounterId(encounterId);
			}
			if(patientClinicalFindingsBean.getPatientClinicalFindingsDate()!=""){
				@SuppressWarnings("deprecation")
				Date orderedDateString = new Date(patientClinicalFindingsBean.getPatientClinicalFindingsDate());
				@SuppressWarnings("deprecation")
				Date orderedDateToSave = new Date(ft.format(orderedDateString));
				patientClinicalFindingsdata.setPatientClinicalFindingsDate(new Timestamp(orderedDateToSave.getTime()));
			}else
				patientClinicalFindingsdata.setPatientClinicalFindingsDate(patientClinicalFindingsRepository.findCurrentTimeStamp());
			patientClinicalFindingsdata.setPatientClinicalFindingsDescription(patientClinicalFindingsBean.getPatientClinicalFindingsDescription());
			patientClinicalFindingsdata.setPatientClinicalFindingsCode(patientClinicalFindingsBean.getPatientClinicalFindingsCode());
			patientClinicalFindingsdata.setPatientClinicalFindingsCodeSystem(patientClinicalFindingsBean.getPatientClinicalFindingsCodeSystem());
			patientClinicalFindingsdata.setPatientClinicalFindingsStatus(patientClinicalFindingsBean.getPatientClinicalFindingsStatus());
			patientClinicalFindingsdata.setPatientClinicalFindingsResultValue(patientClinicalFindingsBean.getPatientClinicalFindingsResultValue());
			patientClinicalFindingsdata.setPatientClinicalFindingsResultUnits(patientClinicalFindingsBean.getPatientClinicalFindingsResultUnits());
			patientClinicalFindingsdata.setPatientClinicalFindingsResultDescription(patientClinicalFindingsBean.getPatientClinicalFindingsResultDescription());
			patientClinicalFindingsdata.setPatientClinicalFindingsResultCode(patientClinicalFindingsBean.getPatientClinicalFindingsResultCode());
			patientClinicalFindingsdata.setPatientClinicalFindingsResultCodeSystem(patientClinicalFindingsBean.getPatientClinicalFindingsResultCodeSystem());
			patientClinicalFindingsdata.setPatientClinicalFindingsIsactive(patientClinicalFindingsBean.getPatientClinicalFindingsIsactive());
			if(patientClinicalFindingsBean.getPatientClinicalFindingsId()!=-1 && Mode!=1){
				patientClinicalFindingsdata.setPatientClinicalFindingsModifiedBy(userId);
				patientClinicalFindingsdata.setPatientClinicalFindingsModifiedOn(patientClinicalFindingsRepository.findCurrentTimeStamp());
			}else{
				patientClinicalFindingsdata.setPatientClinicalFindingsCreatedBy(userId);
				patientClinicalFindingsdata.setPatientClinicalFindingsCreatedOn(patientClinicalFindingsRepository.findCurrentTimeStamp());
			}
			patientClinicalFindingsdata.setPatientClinicalFindingsAnswerListId(patientClinicalFindingsBean.getPatientClinicalFindingsAnswerListId());
			patientClinicalFindingsdata.setPatientClinicalFindingsAnswerListName(patientClinicalFindingsBean.getPatientClinicalFindingsAnswerListName());
			patientClinicalFindingsdata.setPatientClinicalFindingsAnswerListOid(patientClinicalFindingsBean.getPatientClinicalFindingsAnswerListOid());
			patientClinicalFindingsdata.setPatientClinicalFindingsAnswerId(patientClinicalFindingsBean.getPatientClinicalFindingsAnswerId());
			patientClinicalFindingsdata.setpatientClinicalFindingsRiskAssessmentId(riskAssessmentId);
			patientClinicalFindingsdata.setPatientClinicalFindingsNotes(patientClinicalFindingsBean.getPatientClinicalFindingsNotes());
			patientClinicalFindingsRepository.save(patientClinicalFindingsdata);
		}
	}

	
	@Override
	public Map<String, Object> editScreeningQuestions(Integer riskAssessmentId,Integer patientId, Integer encounterId) 
	{
		//getSummaryData(patientId, episodeId, encounterId);
		Map<String,Object> listsMap=new HashMap<String,Object>();
		List<PatientClinicalFindingsBean> res = fetcheditScreeningQuestions(riskAssessmentId,patientId,encounterId);
		listsMap.put("screeningStatus", getScreeningsList(patientId,encounterId,riskAssessmentId,-1));
		listsMap.put("patientQandAData", res);
		return listsMap;		
	}
	

	/*private String fetchScreeningStatus(Integer screeningId, Integer patientId,	Integer encounterId) {
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
			Root<RiskAssessment> root = cq.from(RiskAssessment.class);
			String result;
			List<Predicate> predicates = new ArrayList<>();
			if(screeningId!=-1){
				predicates.add(builder.equal(root.get(RiskAssessment_.riskAssessmentPatientId), patientId));
				predicates.add(builder.equal(root.get(RiskAssessment_.riskAssessmentEncounterId), encounterId));
				predicates.add(builder.equal(root.get(RiskAssessment_.riskAssessmentId), screeningId));
				cq.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
			}
			cq.multiselect(root.get(RiskAssessment_.riskAssessmentStatus));
			result ="" +entityManager.createQuery(cq).getSingleResult();
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/

	public List<PatientClinicalFindingsBean> fetcheditScreeningQuestions(Integer riskAssessmentId,Integer patientId, Integer encounterId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PatientClinicalFindingsBean> cq = builder.createQuery(PatientClinicalFindingsBean.class);
		Root<PatientClinicalFindings> root = cq.from(PatientClinicalFindings.class);
		List<Predicate> predicates = new ArrayList<>();
		if(riskAssessmentId!=-1)
			predicates.add(builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsRiskAssessmentId), riskAssessmentId));
		if(patientId!=-1)
			predicates.add(builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsPatientId), patientId));
		if(encounterId!=-1)
			predicates.add(builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsEncounterId), encounterId));
		@SuppressWarnings("rawtypes")
		Selection[] selectedColumns = new Selection[]{
				root.get(PatientClinicalFindings_.patientClinicalFindingsId),
				root.get(PatientClinicalFindings_.patientClinicalFindingsPatientId),
				root.get(PatientClinicalFindings_.patientClinicalFindingsEncounterId),
				root.get(PatientClinicalFindings_.patientClinicalFindingsDate),
				root.get(PatientClinicalFindings_.patientClinicalFindingsDescription),
				root.get(PatientClinicalFindings_.patientClinicalFindingsCode),
				root.get(PatientClinicalFindings_.patientClinicalFindingsCodeSystem),
				root.get(PatientClinicalFindings_.patientClinicalFindingsStatus),
				root.get(PatientClinicalFindings_.patientClinicalFindingsResultValue),
				root.get(PatientClinicalFindings_.patientClinicalFindingsResultUnits),
				root.get(PatientClinicalFindings_.patientClinicalFindingsResultDescription),
				root.get(PatientClinicalFindings_.patientClinicalFindingsResultCode),
				root.get(PatientClinicalFindings_.patientClinicalFindingsResultCodeSystem),
				root.get(PatientClinicalFindings_.patientClinicalFindingsIsactive),
				root.get(PatientClinicalFindings_.patientClinicalFindingsCreatedBy),
				root.get(PatientClinicalFindings_.patientClinicalFindingsCreatedOn),
				root.get(PatientClinicalFindings_.patientClinicalFindingsAnswerListId),
				root.get(PatientClinicalFindings_.patientClinicalFindingsAnswerListName),
				root.get(PatientClinicalFindings_.patientClinicalFindingsAnswerListOid),
				root.get(PatientClinicalFindings_.patientClinicalFindingsAnswerId),
				root.get(PatientClinicalFindings_.patientClinicalFindingsRiskAssessmentId),
				root.get(PatientClinicalFindings_.patientClinicalFindingsNotes),
						
			};
		cq.select(builder.construct(PatientClinicalFindingsBean.class, selectedColumns));
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(builder.asc(root.get(PatientClinicalFindings_.patientClinicalFindingsId)));
		List<PatientClinicalFindingsBean> screeningquest=entityManager.createQuery(cq).getResultList();
		return screeningquest;
	}

	@Override
	public void saveScreeningStatus(Integer riskAssessmentId, Integer patientId,Integer encounterId, Integer status) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<RiskAssessment> update = builder.createCriteriaUpdate(RiskAssessment.class);
		Root<RiskAssessment> root = update.from(RiskAssessment.class);
		update.set(root.get(RiskAssessment_.riskAssessmentStatus), status);
		/*update.where(builder.equal(root.get(RiskAssessment_.riskAssessmentId), riskAssessmentId),
				builder.equal(root.get(RiskAssessment_.riskAssessmentEncounterId), encounterId));
		 */		List<Predicate> predicates = new ArrayList<>();
		 predicates.add(builder.equal(root.get(RiskAssessment_.riskAssessmentId), riskAssessmentId));
		 if(encounterId!=-1)
			 predicates.add(builder.equal(root.get(RiskAssessment_.riskAssessmentEncounterId), encounterId));
		 update.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		 this.entityManager.createQuery(update).executeUpdate();
	}

	@Override
	public void updateriskAssessmentScore(Integer patientId, Integer encounterId, Integer riskAssessmentId,Integer answerScore,String notes,Integer status,Integer userId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<RiskAssessment> update = builder.createCriteriaUpdate(RiskAssessment.class);
		Root<RiskAssessment> root = update.from(RiskAssessment.class);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(RiskAssessment_.riskAssessmentId), riskAssessmentId));
		if(encounterId!=-1)
		predicates.add(builder.equal(root.get(RiskAssessment_.riskAssessmentEncounterId), encounterId));
		update.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		update.set(root.get(RiskAssessment_.riskAssessmentResultValue), answerScore);
		update.set(root.get(RiskAssessment_.riskAssessmentNotes), notes);
		update.set(root.get(RiskAssessment_.riskAssessmentStatus), status);
		update.set(root.get(RiskAssessment_.riskAssessmentModifiedBy), userId);
		update.set(root.get(RiskAssessment_.riskAssessmentModifiedOn), patientClinicalFindingsRepository.findCurrentTimeStamp());
		/*update.where(builder.equal(root.get(RiskAssessment_.riskAssessmentId), riskAssessmentId),
				builder.equal(root.get(RiskAssessment_.riskAssessmentEncounterId), encounterId));
*/		this.entityManager.createQuery(update).executeUpdate();
	}

	@Override
	public void saveScreeningResult(Integer riskAssessmentId,Integer patientId, Integer encounterId, Integer status,
			String description, String code, String codeSys,Integer userId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<RiskAssessment> update = builder.createCriteriaUpdate(RiskAssessment.class);
		Root<RiskAssessment> root = update.from(RiskAssessment.class);
		update.set(root.get(RiskAssessment_.riskAssessmentResultDescription), description);
		update.set(root.get(RiskAssessment_.riskAssessmentResultCode), code);
		update.set(root.get(RiskAssessment_.riskAssessmentResultCodeSystem), codeSys);
		update.set(root.get(RiskAssessment_.riskAssessmentModifiedBy), userId);
		update.set(root.get(RiskAssessment_.riskAssessmentModifiedOn), patientClinicalFindingsRepository.findCurrentTimeStamp());

		/*update.where(builder.equal(root.get(RiskAssessment_.riskAssessmentId), riskAssessmentId),
				builder.equal(root.get(RiskAssessment_.riskAssessmentEncounterId), encounterId));

		 */	
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(RiskAssessment_.riskAssessmentId), riskAssessmentId));
		if(encounterId!=-1)
			predicates.add(builder.equal(root.get(RiskAssessment_.riskAssessmentEncounterId), encounterId));
		update.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		this.entityManager.createQuery(update).executeUpdate();

	}

	@Override
	public String getSearchInterventions(String searchString) {
		String hubURL = null;
		try {
			hubURL = "http://hub-icd10.glaceemr.com/DataGateway/SNOMEDServices/search?keyword="+URLEncoder.encode(searchString, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getDataFromHub( hubURL);
	}

	@Override
	public void deleteSnomedIntervention(Integer delId, Integer encounterId,Integer patientId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaDelete<PatientClinicalFindings> delete = builder.createCriteriaDelete(PatientClinicalFindings.class);
		Root<PatientClinicalFindings> root = delete.from(PatientClinicalFindings.class);
		if(delId!=-1) {
		delete.where(builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsId), delId),
				builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsPatientId), patientId));
				this.entityManager.createQuery(delete).executeUpdate();
		}
		
	}
	@Override
	public Map<String, Object> getPreviousData(String prevDate,Integer patientId, Integer encounterId, Integer riskAssId, Integer userId,Integer screenId) 
	{
		Map<String,Object> listsMap=new HashMap<String,Object>();
		List<PatientClinicalFindingsBean> res = getPreviousDatafn(prevDate,patientId,encounterId,screenId);
		SaveScreeningsquestions(res,patientId, encounterId, riskAssId,userId,1);
		listsMap.put("screeningStatus", getScreeningsList(patientId,encounterId,riskAssId,-1));
		listsMap.put("patientQandAData", fetcheditScreeningQuestions(riskAssId,patientId,encounterId) );
		return listsMap;		
	}
	
	public List<PatientClinicalFindingsBean> getPreviousDatafn(String prevDate,Integer patientId, Integer encounterId,Integer screenId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PatientClinicalFindingsBean> cq = builder.createQuery(PatientClinicalFindingsBean.class);
		Root<PatientClinicalFindings> root = cq.from(PatientClinicalFindings.class);
		Join<PatientClinicalFindings,RiskAssessment> pcfJoin=root.join(PatientClinicalFindings_.riskAssessment,JoinType.LEFT);
		List<Predicate> predicates = new ArrayList<>();
		if(patientId!=-1)
			predicates.add(builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsPatientId), patientId));
		predicates.add(builder.equal(pcfJoin.get(RiskAssessment_.riskAssessmentScreeningId), screenId));
		Date fromDate = null;
		try {
			fromDate=new SimpleDateFormat("yyyy-MM-dd").parse(prevDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		predicates.add(builder.equal(builder.function("DATE", Date.class,root.get(PatientClinicalFindings_.patientClinicalFindingsCreatedOn)),fromDate));
		@SuppressWarnings("rawtypes")
		Selection[] selectedColumns = new Selection[]{
				root.get(PatientClinicalFindings_.patientClinicalFindingsId),
				root.get(PatientClinicalFindings_.patientClinicalFindingsPatientId),
				root.get(PatientClinicalFindings_.patientClinicalFindingsEncounterId),
				root.get(PatientClinicalFindings_.patientClinicalFindingsDate),
				root.get(PatientClinicalFindings_.patientClinicalFindingsDescription),
				root.get(PatientClinicalFindings_.patientClinicalFindingsCode),
				root.get(PatientClinicalFindings_.patientClinicalFindingsCodeSystem),
				root.get(PatientClinicalFindings_.patientClinicalFindingsStatus),
				root.get(PatientClinicalFindings_.patientClinicalFindingsResultValue),
				root.get(PatientClinicalFindings_.patientClinicalFindingsResultUnits),
				root.get(PatientClinicalFindings_.patientClinicalFindingsResultDescription),
				root.get(PatientClinicalFindings_.patientClinicalFindingsResultCode),
				root.get(PatientClinicalFindings_.patientClinicalFindingsResultCodeSystem),
				root.get(PatientClinicalFindings_.patientClinicalFindingsIsactive),
				root.get(PatientClinicalFindings_.patientClinicalFindingsCreatedBy),
				root.get(PatientClinicalFindings_.patientClinicalFindingsCreatedOn),
				root.get(PatientClinicalFindings_.patientClinicalFindingsAnswerListId),
				root.get(PatientClinicalFindings_.patientClinicalFindingsAnswerListName),
				root.get(PatientClinicalFindings_.patientClinicalFindingsAnswerListOid),
				root.get(PatientClinicalFindings_.patientClinicalFindingsAnswerId),
				root.get(PatientClinicalFindings_.patientClinicalFindingsRiskAssessmentId),
				root.get(PatientClinicalFindings_.patientClinicalFindingsNotes),
						
			};
		cq.select(builder.construct(PatientClinicalFindingsBean.class, selectedColumns));
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(builder.asc(root.get(PatientClinicalFindings_.patientClinicalFindingsId)));
		List<PatientClinicalFindingsBean> screeningquest=entityManager.createQuery(cq).getResultList();
		return screeningquest;
	}

	@Override
	public List<PatientClinicalFindingsBean> getScreeningsStatus(Integer patientId,Integer encounterId, String lOINC, String startDate, String endDate) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PatientClinicalFindingsBean> cq = builder.createQuery(PatientClinicalFindingsBean.class);
		Root<PatientClinicalFindings> root = cq.from(PatientClinicalFindings.class);
		Join<PatientClinicalFindings,RiskAssessment> pcfJoin=root.join(PatientClinicalFindings_.riskAssessment,JoinType.INNER);
		List<Predicate> predicates = new ArrayList<>();
		if(patientId!=-1){
			predicates.add(builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsPatientId), patientId));
			predicates.add(builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsIsactive), true));
		}
		if(encounterId!=-1){
			predicates.add(builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsEncounterId), encounterId));
		}
		if(!lOINC.equals("-1")){
			predicates.add(builder.equal(pcfJoin.get(RiskAssessment_.riskAssessmentCode), lOINC));
		}
		if(!startDate.equals("-1") && endDate.equals("-1")){
			Date fromDate = null;
			try {
				fromDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			predicates.add(builder.equal(builder.function("DATE", Date.class,root.get(PatientClinicalFindings_.patientClinicalFindingsCreatedOn)),fromDate));
		}
		if(!endDate.equals("-1") && !startDate.equals("-1") ){
			Date fromDate = null;
			Date toDate=null;
			try {
				fromDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			predicates.add(builder.greaterThanOrEqualTo(builder.function("DATE", Date.class,root.get(PatientClinicalFindings_.patientClinicalFindingsCreatedOn)),fromDate));
			try {
				toDate=new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			predicates.add(builder.lessThanOrEqualTo(builder.function("DATE", Date.class,root.get(PatientClinicalFindings_.patientClinicalFindingsCreatedOn)),toDate));
		}
		Selection[] selections=new Selection[]{
				root.get(PatientClinicalFindings_.patientClinicalFindingsId),
				root.get(PatientClinicalFindings_.patientClinicalFindingsDate),
				root.get(PatientClinicalFindings_.patientClinicalFindingsDescription),
				root.get(PatientClinicalFindings_.patientClinicalFindingsCode),
				root.get(PatientClinicalFindings_.patientClinicalFindingsIsactive),
				root.get(PatientClinicalFindings_.patientClinicalFindingsCreatedBy),
				root.get(PatientClinicalFindings_.patientClinicalFindingsCreatedOn),
				root.get(PatientClinicalFindings_.patientClinicalFindingsNotes),
				pcfJoin.get(RiskAssessment_.riskAssessmentId),
				pcfJoin.get(RiskAssessment_.riskAssessmentPatientId),
				pcfJoin.get(RiskAssessment_.riskAssessmentEncounterId),
				pcfJoin.get(RiskAssessment_.riskAssessmentDescription),
				pcfJoin.get(RiskAssessment_.riskAssessmentCode),
				pcfJoin.get(RiskAssessment_.riskAssessmentStatus),
				pcfJoin.get(RiskAssessment_.riskAssessmentScreeningId),
		};
		cq.select(builder.construct(PatientClinicalFindingsBean.class, selections));
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(builder.asc(root.get(PatientClinicalFindings_.patientClinicalFindingsId)));
		List<PatientClinicalFindingsBean> screeningquest=entityManager.createQuery(cq).getResultList();
		return screeningquest;
	}

	@Override
	public String getScreenInterventions(Integer screenId) {
		String hubURL = null;
		try {
			hubURL = "http://hub-icd10.glaceemr.com/DataGateway/ScreeningServices/getRecommendedInterventions?id="+screenId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getDataFromHub( hubURL);
	}

	@Override
	public void deleteScreeningInfo(Integer patientId, Integer encounterId,	Integer riskId, String screenId) {
		if(patientId!=-1 && encounterId!=-1) {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaDelete<RiskAssessment> delete = builder.createCriteriaDelete(RiskAssessment.class);
			Root<RiskAssessment> root = delete.from(RiskAssessment.class);
			delete.where(builder.and(
					builder.equal(root.get(RiskAssessment_.riskAssessmentId), riskId),
					builder.equal(root.get(RiskAssessment_.riskAssessmentPatientId), patientId),
					builder.equal(root.get(RiskAssessment_.riskAssessmentEncounterId), encounterId)));
					this.entityManager.createQuery(delete).executeUpdate();
		}
		if(patientId!=-1 && encounterId!=-1) {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaDelete<PatientClinicalFindings> delete = builder.createCriteriaDelete(PatientClinicalFindings.class);
			Root<PatientClinicalFindings> root = delete.from(PatientClinicalFindings.class);
			delete.where(builder.and(
					builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsRiskAssessmentId), riskId),
					builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsPatientId), patientId),
					builder.equal(root.get(PatientClinicalFindings_.patientClinicalFindingsEncounterId), encounterId)));
					this.entityManager.createQuery(delete).executeUpdate();
		}
		if(patientId!=-1 && encounterId!=-1){
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaDelete<CarePlanIntervention> delete = builder.createCriteriaDelete(CarePlanIntervention.class);
			Root<CarePlanIntervention> root = delete.from(CarePlanIntervention.class);
			delete.where(builder.and(
					builder.equal(root.get(CarePlanIntervention_.carePlanInterventionProblemCode), screenId),
					builder.equal(root.get(CarePlanIntervention_.carePlanInterventionPatientId), patientId),
					builder.equal(root.get(CarePlanIntervention_.carePlanInterventionEncounterId), encounterId)));
					this.entityManager.createQuery(delete).executeUpdate();
		}
	}
}