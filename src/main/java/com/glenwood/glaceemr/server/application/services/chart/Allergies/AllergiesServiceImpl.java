package com.glenwood.glaceemr.server.application.services.chart.Allergies;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.AllergiesEncountermap;
import com.glenwood.glaceemr.server.application.models.AllergiesEncountermap_;
import com.glenwood.glaceemr.server.application.models.AllergiesType;
import com.glenwood.glaceemr.server.application.models.AllergiesType_;
import com.glenwood.glaceemr.server.application.models.AllergyClass;
import com.glenwood.glaceemr.server.application.models.AllergyClass_;
import com.glenwood.glaceemr.server.application.models.DrugDetails;
import com.glenwood.glaceemr.server.application.models.DrugDetailsRxnormMap;
import com.glenwood.glaceemr.server.application.models.DrugDetailsRxnormMap_;
import com.glenwood.glaceemr.server.application.models.DrugDetails_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.LabDescription;
import com.glenwood.glaceemr.server.application.models.LabDescription_;
import com.glenwood.glaceemr.server.application.models.PatientAllergies;
import com.glenwood.glaceemr.server.application.models.PatientAllergiesHistory;
import com.glenwood.glaceemr.server.application.models.PatientAllergiesHistory_;
import com.glenwood.glaceemr.server.application.models.PatientAllergies_;
import com.glenwood.glaceemr.server.application.models.Unii;
import com.glenwood.glaceemr.server.application.models.Unii_;
import com.glenwood.glaceemr.server.application.repositories.AllergiesEncountermapRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientAllergiesRepository;
import com.glenwood.glaceemr.server.application.repositories.UniiRepository;
import com.glenwood.glaceemr.server.application.services.chart.CurrentMedication.PatientAllergiesBean;
import com.glenwood.glaceemr.server.application.services.chart.print.TextFormatter;
import com.glenwood.glaceemr.server.application.specifications.AllergiesSpecification;
import com.glenwood.glaceemr.server.utils.HUtil;
/**
 * Service Implementation for Allergies Module
 * @author Bhagya Lakshmi
 *
 */

@Service
@Transactional
public class AllergiesServiceImpl implements AllergiesService{

	@Autowired
	UniiRepository uniiRepository;
	
	@Autowired
	PatientAllergiesRepository patientAllergiesRepository;
	
	@Autowired
	AllergiesEncountermapRepository allergiesEncountermapRepository;
	
	@Autowired
	TextFormatter textFormatter;
	
	@PersistenceContext
	EntityManager em;
	
	/**
	 *Getting patient allergies data
	 */
	
	@Override
	public List<PatientAllergiesBean> getAllergies(int chartId,int encounterId,String statusStr,int fromSoap) {
		List<Integer> statusList = new ArrayList<Integer>();
		String[] statusSplit = statusStr.split(",");
		System.out.println(">>>>>"+statusSplit.length);
		for(int i=0;i<statusSplit.length;i++)
		{
			statusList.add(Integer.parseInt(statusSplit[i]));
		}
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<PatientAllergies> root = cq.from(PatientAllergies.class);
		List<PatientAllergiesBean> beanList=new ArrayList<PatientAllergiesBean>();
		try{
		Join<PatientAllergies, AllergiesType> patAllerTypeJoin=root.join(PatientAllergies_.allergiesType,JoinType.INNER);
		Join<PatientAllergies,EmployeeProfile> AllerEmpModifyJoin=root.join(PatientAllergies_.empProfileAllgModifiedByTable,JoinType.LEFT);
		Join<PatientAllergies,EmployeeProfile> AllerEmpCreateJoin=root.join(PatientAllergies_.empProfileAllgCreatedByTable,JoinType.LEFT);
		Predicate status=root.get(PatientAllergies_.patAllergStatus).in(statusList);
		Predicate patChart=builder.equal(root.get(PatientAllergies_.patAllergChartId), chartId);
		Predicate typeId=builder.greaterThanOrEqualTo(root.get(PatientAllergies_.patAllergTypeId), 0);
		Predicate encounterIdPred=builder.lessThanOrEqualTo(root.get(PatientAllergies_.patAllergEncounterId), encounterId);
		cq.multiselect(root.get(PatientAllergies_.patAllergId),
				root.get(PatientAllergies_.patAllergSeverity),
				root.get(PatientAllergies_.patAllergTypeId),
				patAllerTypeJoin.get(AllergiesType_.allergtypeName),
				root.get(PatientAllergies_.patAllergAllergicTo),
				root.get(PatientAllergies_.patAllergAllergyCode),
				root.get(PatientAllergies_.patAllergCodeSystem),
				root.get(PatientAllergies_.patAllergDrugCategory),
				root.get(PatientAllergies_.patAllergReactionTo),
				root.get(PatientAllergies_.patAllergModifiedOn),
				root.get(PatientAllergies_.patAllergOnsetDate),
				root.get(PatientAllergies_.patAllergCreatedOn),
				root.get(PatientAllergies_.patAllergStatus),
				root.get(PatientAllergies_.patAllergResolvedDate));
				
		if(fromSoap == 0)
		{
			cq.where(typeId,status,patChart).orderBy(builder.asc(root.get(PatientAllergies_.patAllergId)));
		}
		else if(fromSoap == 1)
		{
			cq.where(typeId,status,patChart,encounterIdPred).orderBy(builder.asc(root.get(PatientAllergies_.patAllergId)));
		}
		List<Object[]> allergies=em.createQuery(cq).getResultList();
		
		for(Object[] details : allergies){
			PatientAllergiesBean eachObj=new PatientAllergiesBean();
			eachObj.setPatAllergId(details[0]==null?-1:(Integer)details[0]);
			eachObj.setPatAllergSeverity(details[1]==null?-1:(Integer)details[1]);
			eachObj.setPatAllergTypeId(details[2]==null?-1:(Integer)details[2]);
			eachObj.setPatAllergName(details[3]==null?"":details[3].toString());
			eachObj.setPatAllergAllergicTo(details[4]==null?"":details[4].toString());
			eachObj.setPatAllergAllergyCode(details[5]==null?"":details[5].toString());
			eachObj.setPatAllergCodeSystem(details[6]==null?"":details[6].toString());
			eachObj.setPatAllergDrugCategory(details[7]==null?"":details[7].toString());
			eachObj.setPatAllergReactionTo(details[8]==null?"":details[8].toString());
			eachObj.setPatAllergModifiedOn(details[9]==null?"":details[9].toString());
			eachObj.setPatAllergOnsetDate(details[10]==null?"":details[10].toString());
			eachObj.setPatAllergCreatedOn(details[11]==null?"":details[11].toString());
			eachObj.setPatAllergStatus(details[12]==null?"":details[12].toString());
			eachObj.setPatAllergResolvedDate(details[13]==null?"":details[13].toString());
			beanList.add(eachObj);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return beanList;
	}
	
	
	/**
	 * Method to retrieve allergy types
	 */
	@Override
	public List<AllergiesTypeBean> retrievingAllergyType(int gender) {
		List<AllergiesTypeBean> allergiesTypeList = new ArrayList<AllergiesTypeBean>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<AllergiesType> root = cq.from(AllergiesType.class);
		Predicate typeId=builder.greaterThanOrEqualTo(root.get(AllergiesType_.allergtypeId), 0);
		Predicate isActive=builder.equal(root.get(AllergiesType_.allergtypeIsactive), true);
		Predicate genderPred = root.get(AllergiesType_.allergtypeGender).in(0,gender);
		cq.multiselect(root.get(AllergiesType_.allergtypeId),
				root.get(AllergiesType_.allergtypeName));
		cq.where(typeId,isActive,genderPred);
		cq.orderBy(builder.asc(root.get(AllergiesType_.allergtypeName)));
		List<Object[]> allergiesType = em.createQuery(cq).getResultList();
		for(Object[] values : allergiesType)
		{
			AllergiesTypeBean allergiesTypeBean  =new AllergiesTypeBean();
			allergiesTypeBean.setAllergtypeId((Integer)values[0]);
			allergiesTypeBean.setAllergtypeName(values[1].toString());
			allergiesTypeList.add(allergiesTypeBean);
		}
		return allergiesTypeList;
	}
	
	/**
	 * Method to get data for allergen search
	 * mode basing on allergy type id different modes will allocate 
	 */
	@Override
	public AllergyBean searchAllergy(String value,int mode,int offset,int limit) {
		AllergyBean allergyBean = null;
		if(mode==8)
		{
			allergyBean = new AllergyBean();
			Integer countCode=getCountCode(value);
			List<AllergySearchBean> uniiBeanList = getDrugSearch(value,offset,limit);
			allergyBean.setCountValue(countCode);
			allergyBean.setAllergySearchBean(uniiBeanList);
		}
		if(mode==9)
		{
			allergyBean = new AllergyBean();
			Integer countCode=getLabCountCode(value);
			List<AllergySearchBean> uniiBeanList = getDrugSearchLab(value,offset,limit);
			allergyBean.setCountValue(countCode);
			allergyBean.setAllergySearchBean(uniiBeanList);
		}
		if(mode==0)	
		{
			allergyBean = new AllergyBean();
			Integer countCode=getDrugCountCode(value);
			List<AllergySearchBean> drugDetailsList = getDrugDetailsSearch(value);
			List<AllergySearchBean> allergyClassList = getAllergyClassDetailsSearch(value,offset,limit);
			List<AllergySearchBean> finalList = new ArrayList<AllergySearchBean>();
			finalList.addAll(drugDetailsList);
			finalList.addAll(allergyClassList);
			List<AllergySearchBean> finalListAfterSort=sortBy(finalList);
			List<AllergySearchBean> allergySearchBean = null;
			if(finalListAfterSort.size()>limit)
			{
				allergySearchBean = new ArrayList<AllergySearchBean>();
				if(offset == 0)
				{
					for(int i=offset;i<limit;i++)
					{
							AllergySearchBean AllergySearch = finalListAfterSort.get(i);
							allergySearchBean.add(AllergySearch);
					}
				}
				else
				{
					for(int i=offset;i<=limit;i++)
					{
							AllergySearchBean AllergySearch = finalListAfterSort.get(i-1);
							allergySearchBean.add(AllergySearch);
					}
				}
			}
			else
			{
				allergySearchBean = new ArrayList<AllergySearchBean>();
				if(offset == 0)
				{
					for(int i=offset;i<finalListAfterSort.size();i++)
					{
							AllergySearchBean AllergySearch = finalListAfterSort.get(i);
							allergySearchBean.add(AllergySearch);
					}
				}
				else
				{
					for(int i=offset;i<=finalListAfterSort.size();i++)
					{
							AllergySearchBean AllergySearch = finalListAfterSort.get(i-1);
							allergySearchBean.add(AllergySearch);
					}
				}
			}
			allergyBean.setCountValue(countCode);
			allergyBean.setAllergySearchBean(allergySearchBean);
		}
		return allergyBean;
	}

	/**
	 * Method to get count of drug codes for drug allergy search
	 * @param value
	 * @return
	 */
	public Integer getDrugCountCode(String value) {
		List<AllergySearchBean> drugDetailsList = getDrugDetailsSearch(value);
		List<AllergySearchBean> allergyClassList = getAllergyClassCountDetails(value);
		List<AllergySearchBean> finalList = new ArrayList<AllergySearchBean>();
		finalList.addAll(drugDetailsList);
		finalList.addAll(allergyClassList);
		return finalList.size();
	}

	/**
	 * Method to get count of Allergy Class
	 * @param value
	 * @return
	 */
	public List<AllergySearchBean> getAllergyClassCountDetails(String value) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<AllergyClass> root = cq.from(AllergyClass.class);
		cq.distinct(true);
		cq.multiselect(
				builder.literal(""),
				builder.concat(builder.trim(root.get(AllergyClass_.allergyClassSearchName)),
						root.get(AllergyClass_.allergyClassName)),
				builder.literal(CodeSystem.RXNORM));
		cq.where(builder.like(builder.lower(builder.trim(root.get(AllergyClass_.allergyClassSearchName))), getLikePattern(value)));
		List<Object[]> allergyClassDetails = em.createQuery(cq).getResultList();
		List<AllergySearchBean> allergyClassList = new ArrayList<AllergySearchBean>();
		for(Object[] values : allergyClassDetails)
		{
			AllergySearchBean allergyClass = new AllergySearchBean();
			allergyClass.setCode(values[0]==null?"":values[0].toString());
			allergyClass.setSubstance(values[1]==null?"":values[1].toString());
			allergyClass.setCodesystemid(values[2]==null?"":values[2].toString());
			allergyClassList.add(allergyClass);
		}
		return allergyClassList;
	}

	/**
	 * Method to get details for Allergy Class Search
	 * @param value
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<AllergySearchBean> getAllergyClassDetailsSearch(String value,
			int offset, int limit) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<AllergyClass> root = cq.from(AllergyClass.class);
		cq.distinct(true);
		cq.select(builder.construct(AllergySearchBean.class,
				builder.literal(""),
				builder.trim(root.get(AllergyClass_.allergyClassSearchName)),
				builder.literal(CodeSystem.RXNORM)));
		cq.where(builder.like(builder.lower(builder.trim(root.get(AllergyClass_.allergyClassSearchName))), getLikePattern(value)));
		List<Object> allergyClassDetails = em.createQuery(cq).getResultList();
		List<AllergySearchBean> allergyClassList = new ArrayList<AllergySearchBean>();
		for(int i=0;i<allergyClassDetails.size();i++)
		{
			AllergySearchBean allergyClass = (AllergySearchBean) allergyClassDetails.get(i);
			allergyClassList.add(allergyClass);
		}
		return allergyClassList;
	}

	/**
	 * Method to get details for drug search
	 * @param value
	 * @return
	 */
	public List<AllergySearchBean> getDrugDetailsSearch(String value) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<DrugDetails> root = cq.from(DrugDetails.class);
		Join<DrugDetails, DrugDetailsRxnormMap> detailsRxJoin= root.join(DrugDetails_.detailsRxnormMap,JoinType.LEFT);
		cq.distinct(true);
		cq.multiselect(
				builder.coalesce(detailsRxJoin.get(DrugDetailsRxnormMap_.drugDetailsRxnormMapRxnormCode),"-1"),
				builder.trim(root.get(DrugDetails_.drugDetailsName)),
				builder.literal(CodeSystem.RXNORM));
		cq.where(builder.and(builder.lessThanOrEqualTo(root.get(DrugDetails_.drugDetailsId),20000),
				builder.like(builder.lower(builder.trim(root.get(DrugDetails_.drugDetailsName))), getLikePattern(value))));
		List<Object[]> allergySearchDetails = em.createQuery(cq).getResultList();
		List<AllergySearchBean> allergySearchBeanList = new ArrayList<AllergySearchBean>();
		for(Object[] values : allergySearchDetails)
		{
			AllergySearchBean allergySearchBean = new AllergySearchBean();
			allergySearchBean.setCode(values[0]==null?"":values[0].toString());
			allergySearchBean.setSubstance(values[1]==null?"":values[1].toString());
			allergySearchBean.setCodesystemid(values[2]==null?"":values[2].toString());
			allergySearchBeanList.add(allergySearchBean);
		}
		return allergySearchBeanList;
	}

	/**
	 * Method to get count for allergy type id 9 (Imm./Inj)
	 * @param Criteria
	 * @return
	 */
	public Integer getLabCountCode(String Criteria) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<LabDescription> root = cq.from(LabDescription.class);
		cq.multiselect(
				builder.count(root.get(LabDescription_.labDescriptionTestid)),
						root.get(LabDescription_.labDescriptionTestid),
						root.get(LabDescription_.labDescriptionTestDesc));
		cq.where(root.get(LabDescription_.labDescriptionGroupid).in("36","5"),builder.equal(root.get(LabDescription_.labDescriptionIsactive), true),builder.like(builder.lower(root.get(LabDescription_.labDescriptionTestDesc)), getLikePattern(HUtil.ValidateSingleQuote(Criteria.toString()))));
		cq.groupBy(root.get(LabDescription_.labDescriptionTestid),root.get(LabDescription_.labDescriptionTestDesc));
		cq.orderBy(builder.asc(root.get(LabDescription_.labDescriptionTestDesc)));
		List<Object[]> countCode=em.createQuery(cq).getResultList();
		Integer getCountCode = countCode.size();
		return getCountCode;
	}

	/**
	 * Method to get details for allergy type id 9 (Imm./Inj)
	 * @param Criteria
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<AllergySearchBean> getDrugSearchLab(String Criteria, int offset, int limit) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<LabDescription> root = cq.from(LabDescription.class);
		cq.multiselect(
				root.get(LabDescription_.labDescriptionTestid),
						root.get(LabDescription_.labDescriptionTestDesc),
						builder.literal("-1").alias("codesystemoid"));
		cq.where(root.get(LabDescription_.labDescriptionGroupid).in("36","5"),builder.equal(root.get(LabDescription_.labDescriptionIsactive), true),builder.like(builder.lower(root.get(LabDescription_.labDescriptionTestDesc)), getLikePattern(HUtil.ValidateSingleQuote(Criteria.toString()))));
		cq.orderBy(builder.asc(root.get(LabDescription_.labDescriptionTestDesc)));
		List<Object[]> labDetails = em.createQuery(cq).setFirstResult(offset).setMaxResults(limit).getResultList();
		List<AllergySearchBean> labDescBeanList = new ArrayList<AllergySearchBean>();
		for(Object[] labDetailsValues : labDetails)
		{
			
			AllergySearchBean labDescBean = new AllergySearchBean();
			labDescBean.setCode(labDetailsValues[0]==null?"":labDetailsValues[0].toString());
			labDescBean.setSubstance(labDetailsValues[1]==null?"":labDetailsValues[1].toString());
			labDescBean.setCodesystemid(labDetailsValues[2]==null?"":labDetailsValues[2].toString());
			labDescBeanList.add(labDescBean);
		}
		return labDescBeanList;
	}

	/**
	 * For allergy type id 2,4,5 (Food Allergy,Environment,Substance) get count 
	 * @param Criteria
	 * @return
	 */
	public Integer getCountCode(String Criteria) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<Unii> root = cq.from(Unii.class);
		cq.multiselect(
				builder.count(root.get(Unii_.uniiCode)),
						root.get(Unii_.uniiSubstance),
						builder.literal(CodeSystem.UNII).alias("codesystemoid"));
		cq.where(builder.like(builder.lower(root.get(Unii_.uniiSubstance)), getLikePattern(HUtil.ValidateSingleQuote(Criteria.toString()))));
		cq.groupBy(root.get(Unii_.uniiCode),root.get(Unii_.uniiSubstance));
		cq.orderBy(builder.asc(root.get(Unii_.uniiSubstance)));
		List<Object[]> countCode=em.createQuery(cq).getResultList();
		Integer getCountCode = countCode.size();
		return getCountCode;
	}

	/**
	 * Allergen search for allergen type id 2,4,5 (Food Allergy,Environment,Substance)
	 * @param Criteria
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<AllergySearchBean> getDrugSearch(String Criteria, int offset, int limit) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<Unii> root = cq.from(Unii.class);
		cq.multiselect(builder.construct(AllergySearchBean.class,
				root.get(Unii_.uniiCode),
						root.get(Unii_.uniiSubstance),
						builder.literal(CodeSystem.UNII).alias("codesystemoid")));
		cq.where(builder.like(builder.lower(root.get(Unii_.uniiSubstance)), getLikePattern(HUtil.ValidateSingleQuote(Criteria.toString()))));
		cq.orderBy(builder.asc(root.get(Unii_.uniiSubstance)));
		List<Object> unii = em.createQuery(cq).setFirstResult(offset).setMaxResults(limit).getResultList();
		List<AllergySearchBean> uniiBeanList = new ArrayList<AllergySearchBean>();
		for(int i=0;i<unii.size();i++)
		{
			AllergySearchBean uniiBean = (AllergySearchBean) unii.get(i);
			uniiBeanList.add(uniiBean);
		}
		return uniiBeanList;
		
	}
	
	/**
	 * Method to search data for allergy reaction
	 */
	@Override
	public AllergyBean getReactionsOfAllergies(String Criteria, int offset, int limit) {
		AllergyBean allergyBean = new AllergyBean();
		List<String> reactionsOfAllergies = new ArrayList<String>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<PatientAllergies> root = cq.from(PatientAllergies.class);
		cq.distinct(true);
		cq.select(root.get(PatientAllergies_.patAllergReactionTo));
		cq.where(builder.like(builder.lower(root.get(PatientAllergies_.patAllergReactionTo)), HUtil.ValidateSingleQuote(getLikePattern(Criteria))));
		cq.orderBy(builder.asc(root.get(PatientAllergies_.patAllergReactionTo)));
		List<Object> reactions = em.createQuery(cq).setFirstResult(offset).setMaxResults(limit).getResultList();
		for(int i=0;i<reactions.size();i++)
		{
			reactionsOfAllergies.add(reactions.get(i).toString());
		}
		allergyBean.setCountValue(reactionsOfAllergies.size());
		allergyBean.setReactionName(reactionsOfAllergies);
		return allergyBean;
		
	}
	
	/**
	 * Method to get NKDA and NKA Check details
	 */
	@Override
	public List<PatientAllergies> getNKDAandNKACheckDetails(Integer ChartId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<PatientAllergies> root = cq.from(PatientAllergies.class);
		cq.multiselect(root.get(PatientAllergies_.patAllergId),
				root.get(PatientAllergies_.patAllergTypeId),
				root.get(PatientAllergies_.patAllergAllergicTo),
				root.get(PatientAllergies_.patAllergStatus));
		cq.where(builder.equal(root.get(PatientAllergies_.patAllergChartId),ChartId));
		List<Object[]> NKAResult = em.createQuery(cq).getResultList();
		List<PatientAllergies> NKDAandNKACheckDetails = new ArrayList<PatientAllergies>();
		for(Object[] values : NKAResult)
		{
			PatientAllergies NKDAandNKACheck = new PatientAllergies();
			NKDAandNKACheck.setPatAllergId(Integer.parseInt(values[0]==null?"-1":values[0].toString()));
			NKDAandNKACheck.setPatAllergTypeId(Integer.parseInt(values[1]==null?"-1":values[1].toString()));
			NKDAandNKACheck.setPatAllergAllergicTo(values[2]==null?"":values[2].toString());
			NKDAandNKACheck.setPatAllergStatus(Integer.parseInt(values[3]==null?"-1":values[3].toString()));
			NKDAandNKACheckDetails.add(NKDAandNKACheck);
		}
		return NKDAandNKACheckDetails;
	}
	
	/**
	 * Method to save New Allergies
	 */
	@Override
	public void saveNewAllergies(Integer save,String insertParameters){
		//em.getTransaction().begin();
		Integer rowId = getPatAllergiesMaxId();
		JSONObject jsonParameters = null;
		String chartId = null,encounterId = null,allergyType = null,severText = null,onSetDate = null,
				reaction = null,alergyName = null,userId = null,code = null,codeSystem = null,resolveDate = null,inactiveReason = null;
		Integer status = null;
		Timestamp current_date = new Timestamp(System.currentTimeMillis());
		try {
			String insertParametersDecode=HUtil.Nz(URLDecoder.decode(insertParameters,"UTF-8"),"");
			jsonParameters = new JSONObject(insertParametersDecode);
			chartId = jsonParameters.getString("chartId").toString();
			encounterId = jsonParameters.getString("encounterId");
			allergyType = jsonParameters.getString("typeId");
			status = Integer.parseInt(jsonParameters.getString("stat"));
			severText = jsonParameters.getString("severelevel");
			onSetDate = jsonParameters.getString("onsetDate");
			reaction = jsonParameters.getString("reaction");
			alergyName = jsonParameters.getString("allergyName");
			userId = jsonParameters.getString("userId");
			code = jsonParameters.getString("code");
			codeSystem = jsonParameters.getString("codeSystem");
		} catch (Exception e) {
			e.printStackTrace();
		}
		PatientAllergies patientAllergies = new PatientAllergies();
		patientAllergies.setPatAllergId(rowId+1);
		patientAllergies.setPatAllergChartId(Integer.parseInt(chartId));
		patientAllergies.setPatAllergEncounterId(Integer.parseInt(encounterId));
		if(save == 1)
		{
			patientAllergies.setPatAllergTypeId(Integer.parseInt(allergyType));
			patientAllergies.setPatAllergAllergicTo(alergyName);
			patientAllergies.setPatAllergCreatedBy(userId);
			patientAllergies.setPatAllergCreatedOn(current_date);
			patientAllergies.setPatAllergModifiedBy(userId);
			patientAllergies.setPatAllergModifiedOn(current_date);
			patientAllergies.setPatAllergAllergyCode(code);
			patientAllergies.setPatAllergReactionTo(reaction);
			patientAllergies.setPatAllergOnsetDate(onSetDate);
			patientAllergies.setPatAllergStatus(status);
			patientAllergies.setPatAllergResolvedBy("");
			patientAllergies.setPatAllergInactiveBy("");
			patientAllergies.setPatAllergInactiveOn(current_date);
			patientAllergies.setPatAllergSeverity(Integer.parseInt(severText));
			patientAllergies.setPatAllergInteractionXml("");
			patientAllergies.setPatAllergCodeSystem(codeSystem);
			patientAllergies.setPatAllergDrugCategory(alergyName);
			patientAllergies.setPatAllergInactiveReason("");
			patientAllergies.setPatAllergResolvedDate("");
			
			patientAllergiesRepository.saveAndFlush(patientAllergies);
		}
		else if(save == 4)
		{
			try
			{
				resolveDate= HUtil.Nz(jsonParameters.getString("resolveDate"),"");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			patientAllergies.setPatAllergTypeId(Integer.parseInt(allergyType));
			patientAllergies.setPatAllergAllergicTo(alergyName);
			patientAllergies.setPatAllergCreatedBy(userId);
			patientAllergies.setPatAllergCreatedOn(current_date);
			patientAllergies.setPatAllergModifiedBy(userId);
			patientAllergies.setPatAllergModifiedOn(current_date);
			patientAllergies.setPatAllergAllergyCode(code);
			patientAllergies.setPatAllergReactionTo(reaction);
			patientAllergies.setPatAllergOnsetDate(onSetDate);
			patientAllergies.setPatAllergStatus(status);
			patientAllergies.setPatAllergResolvedBy(userId);
			patientAllergies.setPatAllergInactiveBy(null);
			patientAllergies.setPatAllergInactiveOn(current_date);
			patientAllergies.setPatAllergSeverity(Integer.parseInt(severText));
			patientAllergies.setPatAllergInteractionXml(null);
			patientAllergies.setPatAllergCodeSystem(codeSystem);
			patientAllergies.setPatAllergDrugCategory(alergyName);
			patientAllergies.setPatAllergInactiveReason(null);
			patientAllergies.setPatAllergResolvedDate(resolveDate);
			patientAllergiesRepository.saveAndFlush(patientAllergies);
		}
		else if(save == 5)
		{
			try
			{
				inactiveReason= HUtil.Nz(jsonParameters.getString("inactiveReason"),"");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			patientAllergies.setPatAllergTypeId(Integer.parseInt(allergyType));
			patientAllergies.setPatAllergAllergicTo(alergyName);
			patientAllergies.setPatAllergCreatedBy(userId);
			patientAllergies.setPatAllergCreatedOn(current_date);
			patientAllergies.setPatAllergModifiedBy(userId);
			patientAllergies.setPatAllergModifiedOn(current_date);
			patientAllergies.setPatAllergAllergyCode(code);
			patientAllergies.setPatAllergReactionTo(reaction);
			patientAllergies.setPatAllergOnsetDate(onSetDate);
			patientAllergies.setPatAllergStatus(status);
			patientAllergies.setPatAllergResolvedBy(null);
			patientAllergies.setPatAllergInactiveBy(userId);
			patientAllergies.setPatAllergInactiveOn(current_date);
			patientAllergies.setPatAllergSeverity(Integer.parseInt(severText));
			patientAllergies.setPatAllergInteractionXml(null);
			patientAllergies.setPatAllergCodeSystem(codeSystem);
			patientAllergies.setPatAllergDrugCategory(alergyName);
			patientAllergies.setPatAllergInactiveReason(inactiveReason);
			patientAllergies.setPatAllergResolvedDate(null);
			patientAllergiesRepository.saveAndFlush(patientAllergies);
		}
	}

	/**
	 * Method to get MaxId from Patient Allergies 
	 * @return
	 */
	private Integer getPatAllergiesMaxId() {
		Integer maxId = 0;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<PatientAllergies> root = cq.from(PatientAllergies.class);
		cq.select(builder.greatest(root.get(PatientAllergies_.patAllergId)));
		Object maxIdObj = em.createQuery(cq).getSingleResult();
		if(maxIdObj!=null)
		{
			maxId = (Integer) maxIdObj;
		}
		return maxId;
	}
	
	/**
	 * Method to update new data for edited Allergies
	 */
	@Override
	public void editAllergies(String editData){
		JSONObject jsonParameters = null;
		String chartId = null,encounterId = null,allergyType = null,severText = null,onSetDate = null,
				reaction = null,allergyName = null,userId = null,code = null,codeSystem = null,resolveDate = null,inactiveReason = null,id=null;
		Integer status = null;
		try {
			String editDataDecode=HUtil.Nz(URLDecoder.decode(editData,"UTF-8"),"");
			System.out.println("editDataDecode"+editDataDecode);
			jsonParameters = new JSONObject(editDataDecode);
			chartId = jsonParameters.getString("chartId").toString();
			encounterId = jsonParameters.getString("encounterId");
			allergyType = jsonParameters.getString("typeId");
			status = Integer.parseInt(jsonParameters.getString("stat"));
			id = HUtil.Nz(jsonParameters.getString("id"), "-1");
			severText = jsonParameters.getString("severelevel");
			onSetDate = jsonParameters.getString("onsetDate");
			reaction = jsonParameters.getString("reaction");
			allergyName = jsonParameters.getString("allergyName");
			userId = jsonParameters.getString("userId");
			code = jsonParameters.getString("code");
			codeSystem = jsonParameters.getString("codeSystem");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(status == 4)
		{
			deleteAllergies(id);
		}
		else
		{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaUpdate<PatientAllergies> update = cb.createCriteriaUpdate(PatientAllergies.class);
			Root<PatientAllergies> rootCriteria = update.from(PatientAllergies.class);
			update.set(rootCriteria.get(PatientAllergies_.patAllergChartId), Integer.parseInt(chartId));
			update.set(rootCriteria.get(PatientAllergies_.patAllergEncounterId), Integer.parseInt(encounterId));
			update.set(rootCriteria.get(PatientAllergies_.patAllergTypeId), Integer.parseInt(allergyType));
			update.set(rootCriteria.get(PatientAllergies_.patAllergAllergicTo), allergyName);
			update.set(rootCriteria.get(PatientAllergies_.patAllergModifiedBy), userId);
			update.set(rootCriteria.get(PatientAllergies_.patAllergModifiedOn), patientAllergiesRepository.findCurrentTimeStamp());
			update.set(rootCriteria.get(PatientAllergies_.patAllergAllergyCode), code);
			update.set(rootCriteria.get(PatientAllergies_.patAllergReactionTo), reaction);
			update.set(rootCriteria.get(PatientAllergies_.patAllergOnsetDate), onSetDate);
			update.set(rootCriteria.get(PatientAllergies_.patAllergStatus), status);
			update.set(rootCriteria.get(PatientAllergies_.patAllergSeverity), Integer.parseInt(severText));
			update.set(rootCriteria.get(PatientAllergies_.patAllergCodeSystem), codeSystem);
			if(status == 2)
			{
				try{
					resolveDate= HUtil.Nz(jsonParameters.getString("resolveDate"),"");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				update.set(rootCriteria.get(PatientAllergies_.patAllergResolvedBy), userId);
				update.set(rootCriteria.get(PatientAllergies_.patAllergResolvedDate), resolveDate);
			}
			else if(status == 3){
				try{
				  inactiveReason = HUtil.Nz(jsonParameters.getString("inactiveReason"), "");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				  update.set(rootCriteria.get(PatientAllergies_.patAllergInactiveBy), userId);
				  update.set(rootCriteria.get(PatientAllergies_.patAllergInactiveOn), patientAllergiesRepository.findCurrentTimeStamp());
				  update.set(rootCriteria.get(PatientAllergies_.patAllergInactiveReason), inactiveReason);
			}
			update.where(cb.equal(rootCriteria.get(PatientAllergies_.patAllergId),Integer.parseInt(id)));
			this.em.createQuery(update).executeUpdate();
		}
	}

	/**
	 * Method to delete Allergies
	 */
	@Override
	public String deleteAllergy(String allergyid) {
		try
		{
			JSONObject allergyDetails=new JSONObject(allergyid);
			JSONArray allergyIdsArray=new JSONArray(HUtil.Nz(allergyDetails.getString("allergiesIds"),""));
			for(int i=0;i<allergyIdsArray.length();i++){
			    String eachAllergyId=HUtil.Nz(allergyIdsArray.getString(i),"-1");
			    deleteAllergies(eachAllergyId);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		String status = "success";
		return status;
	}
	
	/**
	 * Method to delete Allergies Criteria Query
	 * @param allergyid
	 */
	public void deleteAllergies(String allergyid) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<PatientAllergies> update = cb.createCriteriaUpdate(PatientAllergies.class);
		Root<PatientAllergies> rootCriteria = update.from(PatientAllergies.class);
		update.set(rootCriteria.get(PatientAllergies_.patAllergStatus), 4);
		update.where(cb.equal(rootCriteria.get(PatientAllergies_.patAllergId),Integer.parseInt(allergyid)));
		this.em.createQuery(update).executeUpdate();
	}
	
	/**
	 * Method to update review allergies
	 */
	@Override
	public void reviewAllergies(String reviewData){
		JSONObject jsonReview;
		String chartId=null,encounterId=null,userId=null;
		try {
			jsonReview = new JSONObject(reviewData);
			chartId = HUtil.Nz(jsonReview.getString("chartId"),"-1");
			encounterId = HUtil.Nz(jsonReview.getString("encounterId"),"-1");
			userId = HUtil.Nz(jsonReview.getString("userId"),"-1");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		boolean isReviewed=true;
		List<AllergiesEncountermap> allergiesEncountermap= allergiesEncountermapRepository.findAll(AllergiesSpecification.getbyChartIdAndReview(chartId));
		String checkstatus;
		if(allergiesEncountermap.size()>0)
		{
			checkstatus = allergiesEncountermap.get(0).getAllergencmapId()==null?"-1":allergiesEncountermap.get(0).getAllergencmapId().toString();
		}
		else
		{
			checkstatus = "-1";
		}
		if(!(checkstatus.equalsIgnoreCase("-1")))
		{
			List<AllergiesEncountermap> allergiesEncountermapEntityList= allergiesEncountermapRepository.findAll(AllergiesSpecification.getbyChartId(chartId));
			for(int i=0;i<allergiesEncountermapEntityList.size();i++)
			{
				AllergiesEncountermap allergiesEncountermapEntity = allergiesEncountermapEntityList.get(i);
				allergiesEncountermapEntity.setAllergencmapReviewed(false);
				allergiesEncountermapRepository.saveAndFlush(allergiesEncountermapEntity);
			}
		}
		Integer greatestAllergenId = getGreatestAllergenId();
		AllergiesEncountermap allergiesEncountermapInsert = new AllergiesEncountermap();
		allergiesEncountermapInsert.setAllergencmapId(greatestAllergenId+1);
		allergiesEncountermapInsert.setAllergencmapChartid(Integer.parseInt(chartId));
		allergiesEncountermapInsert.setAllergencmapEncounterid(Integer.parseInt(encounterId));
		allergiesEncountermapInsert.setAllergencmapAllergid("-1");
		allergiesEncountermapInsert.setAllergencmapReviewed(isReviewed);
		allergiesEncountermapInsert.setAllergencmapReviewedby(userId);
		allergiesEncountermapInsert.setAllergencmapReviewedon(allergiesEncountermapRepository.findCurrentTimeStamp());
		allergiesEncountermapRepository.saveAndFlush(allergiesEncountermapInsert);
	}

	/**
	 * Method to get MaxId from AllergiesEncountermap
	 * @return
	 */
	public Integer getGreatestAllergenId() {
		Integer maxId = 0;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<AllergiesEncountermap> root = cq.from(AllergiesEncountermap.class);
		cq.select(builder.greatest(root.get(AllergiesEncountermap_.allergencmapId)));
		Object maxIdObj = em.createQuery(cq).getSingleResult();
		if(maxIdObj!=null)
		{
			maxId = (Integer) maxIdObj;
		}
		return maxId;
	}
	
	/**
	 * Method to update NKDA check or Uncheck
	 */
	@Override
	public void nkdaUpdate(String nkdaData)
	{
		int ROWID 	= 0;
		int TYPEID  = -1;
		String chartId = null,encounterId = null,userId = null;
		JSONObject jsonNKDA;
		try {
			jsonNKDA = new JSONObject(nkdaData);
			chartId = HUtil.Nz(jsonNKDA.getString("chartId"),"-1");
			encounterId = HUtil.Nz(jsonNKDA.getString("encounterId"),"-1");
			userId = HUtil.Nz(jsonNKDA.getString("userId"),"-1");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		List<PatientAllergies> patAllerg = patientAllergiesRepository.findAll(AllergiesSpecification.getbyChartIdAndTypeId(chartId,TYPEID));
		Integer existId = null;
		if(patAllerg.size()>0)
		{
			existId = patAllerg.get(0).getPatAllergTypeId()==null?null:patAllerg.get(0).getPatAllergTypeId();
		}

		if(existId == null)
		{
			ROWID = getPatAllergiesMaxId()+1;
			Object allergTypeName = getAllergyTypeName(TYPEID);
			PatientAllergies patientAllergies = new PatientAllergies();
			patientAllergies.setPatAllergId(ROWID);
			patientAllergies.setPatAllergChartId(Integer.parseInt(chartId));
			patientAllergies.setPatAllergEncounterId(Integer.parseInt(encounterId));
			patientAllergies.setPatAllergTypeId(TYPEID);
			patientAllergies.setPatAllergAllergicTo(allergTypeName.toString());
			patientAllergies.setPatAllergStatus(Integer.parseInt(userId));
			patientAllergies.setPatAllergCreatedOn(patientAllergiesRepository.findCurrentTimeStamp());
			patientAllergies.setPatAllergCreatedBy(userId);
			patientAllergies.setPatAllergModifiedOn(patientAllergiesRepository.findCurrentTimeStamp());
			patientAllergies.setPatAllergModifiedBy(userId);
			patientAllergies.setPatAllergCodeSystem(Integer.toString(TYPEID));
			patientAllergiesRepository.saveAndFlush(patientAllergies);
		}
		else
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery(Object.class);
			Root<PatientAllergies> root = cq.from(PatientAllergies.class);
			cq.select(root.get(PatientAllergies_.patAllergStatus));
			cq.where(builder.and(builder.equal(root.get(PatientAllergies_.patAllergModifiedBy), userId),
					builder.equal(root.get(PatientAllergies_.patAllergChartId), chartId),
					builder.equal(root.get(PatientAllergies_.patAllergTypeId), TYPEID)));
			Object patAllergStatusObj = em.createQuery(cq).getSingleResult();
			String patAllergStatus = patAllergStatusObj==null?"-1":patAllergStatusObj.toString();
			if(!patAllergStatus.equalsIgnoreCase("1"))
			{
				PatientAllergies patAllergUpdate = patientAllergiesRepository.findOne(AllergiesSpecification.getbyChartIdAndTypeId(chartId, TYPEID));
				patAllergUpdate.setPatAllergStatus(1);
				patAllergUpdate.setPatAllergModifiedOn(patientAllergiesRepository.findCurrentTimeStamp());
				patAllergUpdate.setPatAllergModifiedBy(userId);
				patientAllergiesRepository.saveAndFlush(patAllergUpdate);
			}
		}
	}

	/**
	 * Method to get allergy type name for NKA or NKDA update
	 * @param tYPEID
	 * @return
	 */
	private Object getAllergyTypeName(int tYPEID) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<AllergiesType> root = cq.from(AllergiesType.class);
		cq.select(root.get(AllergiesType_.allergtypeName));
		cq.where(builder.equal(root.get(AllergiesType_.allergtypeId), tYPEID));
		Object allergTypeName = em.createQuery(cq).getSingleResult();
		return allergTypeName;
	}
	
	/**
	 * Method to update NKA check or uncheck
	 */
	@Override
	public void nkaUpdate(String nkaData){
		int ROWID 	= 0;
		int TYPEID  = 0; 
		String chartId = null,encounterId = null,userId = null;
				TYPEID=-2;
				JSONObject jsonNKDA;
				try {
					jsonNKDA = new JSONObject(nkaData);
					chartId = HUtil.Nz(jsonNKDA.getString("chartId"),"-1");
					encounterId = HUtil.Nz(jsonNKDA.getString("encounterId"),"-1");
					userId = HUtil.Nz(jsonNKDA.getString("userId"),"-1");
				} catch (JSONException e) {
					e.printStackTrace();
				}
		List<PatientAllergies> patAllerg = patientAllergiesRepository.findAll(AllergiesSpecification.getbyChartIdAndTypeId(chartId,TYPEID));
		Integer existId = null;
		if(patAllerg.size()>0)
		{
			existId = patAllerg.get(0).getPatAllergTypeId()==null?null:patAllerg.get(0).getPatAllergTypeId();
		}
		
		
		if(existId == null)
		{
			ROWID = getPatAllergiesMaxId()+1;
			Object allergTypeName = getAllergyTypeName(TYPEID);
			PatientAllergies patientAllergies = new PatientAllergies();
			patientAllergies.setPatAllergId(ROWID);
			patientAllergies.setPatAllergChartId(Integer.parseInt(chartId));
			patientAllergies.setPatAllergEncounterId(Integer.parseInt(encounterId));
			patientAllergies.setPatAllergTypeId(TYPEID);
			patientAllergies.setPatAllergAllergicTo(allergTypeName.toString());
			patientAllergies.setPatAllergStatus(1);
			patientAllergies.setPatAllergCreatedOn(patientAllergiesRepository.findCurrentTimeStamp());
			patientAllergies.setPatAllergCreatedBy(userId);
			patientAllergies.setPatAllergModifiedOn(patientAllergiesRepository.findCurrentTimeStamp());
			patientAllergies.setPatAllergModifiedBy(userId);
			patientAllergies.setPatAllergCodeSystem(Integer.toString(TYPEID));
			patientAllergiesRepository.saveAndFlush(patientAllergies);
		}
		else
		{
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery(Object.class);
			Root<PatientAllergies> root = cq.from(PatientAllergies.class);
			cq.select(root.get(PatientAllergies_.patAllergStatus));
			cq.where(builder.and(builder.equal(root.get(PatientAllergies_.patAllergModifiedBy), userId),
					builder.equal(root.get(PatientAllergies_.patAllergChartId), chartId),
					builder.equal(root.get(PatientAllergies_.patAllergTypeId), TYPEID)));
			Object patAllergStatusObj = em.createQuery(cq).getSingleResult();
			String patAllergStatus = patAllergStatusObj==null?"-1":patAllergStatusObj.toString();
			if(!patAllergStatus.equalsIgnoreCase("1"))
			{
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaUpdate<PatientAllergies> update = cb.createCriteriaUpdate(PatientAllergies.class);
				Root<PatientAllergies> rootCriteria = update.from(PatientAllergies.class);
				update.set(rootCriteria.get(PatientAllergies_.patAllergStatus), 1);
				update.set(rootCriteria.get(PatientAllergies_.patAllergModifiedOn), patientAllergiesRepository.findCurrentTimeStamp());
				update.set(rootCriteria.get(PatientAllergies_.patAllergModifiedBy), userId);
				update.where(cb.and(cb.equal(rootCriteria.get(PatientAllergies_.patAllergChartId), chartId),cb.equal(rootCriteria.get(PatientAllergies_.patAllergTypeId), TYPEID)));
				this.em.createQuery(update).executeUpdate();
			}
		}
	}
	
	/**
	 * Method to update patient allergies for NKDA Uncheck
	 */
	@Override
	public void nkdauncheck(String nkdaData){
		JSONObject jsonNKDA;
		String chartId = null;
		try {
			jsonNKDA = new JSONObject(nkdaData);
			chartId = HUtil.Nz(jsonNKDA.getString("chartId"),"-1");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		PatientAllergies patAllerg = patientAllergiesRepository.findOne(AllergiesSpecification.getbyChartIdAndTypeId(chartId, -1));
		patAllerg.setPatAllergStatus(2);
		patientAllergiesRepository.saveAndFlush(patAllerg);
	}
	
	/**
	 * Method to update patient allergies for NKA Uncheck
	 */
	@Override
	public void nkauncheck(String nkaData){
		JSONObject jsonNKDA;
		String chartId = null;
		try {
			jsonNKDA = new JSONObject(nkaData);
			chartId = HUtil.Nz(jsonNKDA.getString("chartId"),"-1");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		PatientAllergies patAllerg = patientAllergiesRepository.findOne(AllergiesSpecification.getbyChartIdAndTypeId(chartId, -2));
		patAllerg.setPatAllergStatus(2);
		patientAllergiesRepository.saveAndFlush(patAllerg);
	}
	
	/**
	 * Method to retrieve details for edit Allergies
	 */
	@Override
	public List<PatientAllergiesBean> retrieveDataForEditAllerg(String chartId,String patId)
	{
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<PatientAllergies> root = cq.from(PatientAllergies.class);
		Join<PatientAllergies, AllergiesType> allergType = root.join(PatientAllergies_.allergiesType,JoinType.INNER);
		
		cq.multiselect(root.get(PatientAllergies_.patAllergTypeId),
				root.get(PatientAllergies_.patAllergAllergicTo),
				allergType.get(AllergiesType_.allergtypeName),
				root.get(PatientAllergies_.patAllergDrugCategory),
				root.get(PatientAllergies_.patAllergReactionTo),
				root.get(PatientAllergies_.patAllergOnsetDate),
				root.get(PatientAllergies_.patAllergStatus),
				root.get(PatientAllergies_.patAllergSeverity),
				root.get(PatientAllergies_.patAllergResolvedDate),
				root.get(PatientAllergies_.patAllergAllergyCode),
				root.get(PatientAllergies_.patAllergCodeSystem)
				);
		cq.where(builder.and(builder.notEqual(root.get(PatientAllergies_.patAllergStatus), 3),
				builder.equal(root.get(PatientAllergies_.patAllergChartId), Integer.parseInt(chartId)),
				builder.greaterThan(root.get(PatientAllergies_.patAllergTypeId), 0),
				builder.equal(root.get(PatientAllergies_.patAllergId), Integer.parseInt(patId))));
		
		List<Object[]> editData = em.createQuery(cq).getResultList();
		List<PatientAllergiesBean> patAllergBean = new ArrayList<PatientAllergiesBean>();
		for(Object[] values : editData)
		{
			PatientAllergiesBean patAllerg = new PatientAllergiesBean();
			AllergiesType allergTypeBean = new AllergiesType();
			patAllerg.setPatAllergTypeId(Integer.parseInt(values[0]==null?"-1":values[0].toString()));
			patAllerg.setPatAllergAllergicTo(values[1]==null?"":values[1].toString());
			allergTypeBean.setAllergtypeName(values[2]==null?"":values[2].toString());
			patAllerg.setPatAllergName(values[2]==null?"":values[2].toString());
			patAllerg.setPatAllergDrugCategory(values[3]==null?"":values[3].toString());
			patAllerg.setPatAllergReactionTo(values[4]==null?"":values[4].toString());
			patAllerg.setPatAllergOnsetDate(values[5]==null?"":values[5].toString());
			patAllerg.setPatAllergStatus(values[6]==null?"-1":values[6].toString());
			patAllerg.setPatAllergSeverity(Integer.parseInt(values[7]==null?"":values[7].toString()));
			patAllerg.setPatAllergResolvedDate(values[8]==null?"":values[8].toString());
			patAllerg.setPatAllergAllergyCode(values[9]==null?"":values[9].toString());
			patAllerg.setPatAllergCodeSystem(values[10]==null?"":values[10].toString());
			patAllergBean.add(patAllerg);
		}
		return patAllergBean;
	}
	
	/**
	 * Method to retrieve details for In active Allergies
	 */
	@Override
	public List<PatientAllergiesBean> retrieveInActiveAllerg(String chartId,String statusStr)
	{
		List<Integer> statusList = new ArrayList<Integer>();
		String[] statusSplit = statusStr.split(",");
		System.out.println(">>>>>"+statusSplit.length);
		for(int i=0;i<statusSplit.length;i++)
		{
			statusList.add(Integer.parseInt(statusSplit[i]));
		}
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<PatientAllergies> root = cq.from(PatientAllergies.class);
		Join<PatientAllergies, AllergiesType> allergType = root.join(PatientAllergies_.allergiesType,JoinType.INNER);
		Join<PatientAllergies, EmployeeProfile> empProfileCreatedJoin = root.join(PatientAllergies_.empProfileAllgCreatedByTable,JoinType.LEFT);
		Join<PatientAllergies, EmployeeProfile> empProfileInactiveCreatedJoin = root.join(PatientAllergies_.empProfileAllgInactiveByTable,JoinType.LEFT);
		
		cq.multiselect(root.get(PatientAllergies_.patAllergId),
				root.get(PatientAllergies_.patAllergTypeId),
				allergType.get(AllergiesType_.allergtypeName),
				root.get(PatientAllergies_.patAllergAllergicTo),
				root.get(PatientAllergies_.patAllergDrugCategory),
				root.get(PatientAllergies_.patAllergReactionTo),
				root.get(PatientAllergies_.patAllergOnsetDate),
				root.get(PatientAllergies_.patAllergStatus),
				root.get(PatientAllergies_.patAllergSeverity),
				root.get(PatientAllergies_.patAllergResolvedDate),
				root.get(PatientAllergies_.patAllergInactiveReason),
				builder.function("format_name", String.class, builder.coalesce(empProfileInactiveCreatedJoin.get(EmployeeProfile_.empProfileFname), ""),
						builder.coalesce(empProfileInactiveCreatedJoin.get(EmployeeProfile_.empProfileLname),""),
						builder.coalesce(empProfileInactiveCreatedJoin.get(EmployeeProfile_.empProfileMi), ""),
						builder.coalesce(empProfileInactiveCreatedJoin.get(EmployeeProfile_.empProfileCredentials), ""),builder.literal(1)),
				//root.get(PatientAllergies_.patAllergInactiveBy),
				builder.function("glace_timezone", String.class, 
						root.get(PatientAllergies_.patAllergInactiveOn),
						builder.literal("EDT"),builder.literal("MM/dd/yyyy HH:MI:ss am")),
				//root.get(PatientAllergies_.patAllergInactiveOn),
				builder.function("format_name", String.class, builder.coalesce(empProfileCreatedJoin.get(EmployeeProfile_.empProfileFname), ""),
						builder.coalesce(empProfileCreatedJoin.get(EmployeeProfile_.empProfileLname),""),
						builder.coalesce(empProfileCreatedJoin.get(EmployeeProfile_.empProfileMi), ""),
						builder.coalesce(empProfileCreatedJoin.get(EmployeeProfile_.empProfileCredentials), ""),builder.literal(1)),
				//root.get(PatientAllergies_.patAllergCreatedBy),
				builder.function("glace_timezone", String.class, 
								root.get(PatientAllergies_.patAllergCreatedOn),
								builder.literal("EDT"),builder.literal("MM/dd/yyyy HH:MI:ss am"))
				//root.get(PatientAllergies_.patAllergCreatedOn),
				);
		cq.where(builder.and((root.get(PatientAllergies_.patAllergStatus).in(statusList)),
				builder.equal(root.get(PatientAllergies_.patAllergChartId), Integer.parseInt(chartId)),
				builder.greaterThan(root.get(PatientAllergies_.patAllergTypeId), 0)
				));
		if(statusStr.equalsIgnoreCase("3"))
		{
			cq.orderBy(builder.desc(root.get(PatientAllergies_.patAllergInactiveOn)));
		}
		else
		{
			cq.orderBy(builder.asc(root.get(PatientAllergies_.patAllergId)));
		}
		List<Object[]> editData = em.createQuery(cq).getResultList();
		List<PatientAllergiesBean> patAllergBean = new ArrayList<PatientAllergiesBean>();
		for(Object[] values : editData)
		{
			PatientAllergiesBean patAllerg = new PatientAllergiesBean();
			patAllerg.setPatAllergId(Integer.parseInt(values[0]==null?"-1":values[0].toString()));
			patAllerg.setPatAllergTypeId(Integer.parseInt(values[1]==null?"-1":values[1].toString()));
			patAllerg.setPatAllergName(values[2]==null?"":values[2].toString());
			patAllerg.setPatAllergAllergicTo(values[3]==null?"":values[3].toString());
			patAllerg.setPatAllergDrugCategory(values[4]==null?"":values[4].toString());
			patAllerg.setPatAllergReactionTo(values[5]==null?"":values[5].toString());
			patAllerg.setPatAllergOnsetDate(values[6]==null?"":values[6].toString());
			patAllerg.setPatAllergStatus(values[7]==null?"-1":values[7].toString());
			patAllerg.setPatAllergSeverity(Integer.parseInt(values[8]==null?"":values[8].toString()));
			patAllerg.setPatAllergResolvedDate(values[9]==null?"":values[9].toString());
			patAllerg.setPatAllergInactiveReason(values[10]==null?"":values[10].toString());
			patAllerg.setPatAllergInactiveBy(values[11]==null?"":values[11].toString());
			patAllerg.setPatAllergInactiveOn(values[12]==null?"":values[12].toString());
			patAllerg.setPatAllergCreatedBy(values[13]==null?"":values[13].toString());
			patAllerg.setPatAllergCreatedOn(values[14]==null?"":values[14].toString());
			patAllergBean.add(patAllerg);
		}
		return patAllergBean;
	}
	
	/**
	 * Method to get last review details
	 */
	@Override
	public JSONArray lastReviewDetails(String chartId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<AllergiesEncountermap> root = cq.from(AllergiesEncountermap.class);
		Join<AllergiesEncountermap, EmployeeProfile> empJoin = root.join(AllergiesEncountermap_.empProfile,JoinType.INNER);
		cq.multiselect(builder.coalesce(empJoin.get(EmployeeProfile_.empProfileFname), ""),
				builder.coalesce(empJoin.get(EmployeeProfile_.empProfileLname),""),
				builder.coalesce(empJoin.get(EmployeeProfile_.empProfileMi), ""),
				builder.coalesce(empJoin.get(EmployeeProfile_.empProfileCredentials), ""),
				builder.function("glace_timezone", String.class, root.get(AllergiesEncountermap_.allergencmapReviewedon),builder.literal("EDT"),builder.literal("MM/dd/yyyy HH:MI:ss am")).alias("reviewOn"),
				root.get(AllergiesEncountermap_.allergencmapReviewedon)
				);
		cq.where(builder.and(builder.equal(root.get(AllergiesEncountermap_.allergencmapChartid), chartId),
				builder.equal(root.get(AllergiesEncountermap_.allergencmapReviewed), true)));
		cq.orderBy(builder.asc(builder.function("glace_timezone", String.class, root.get(AllergiesEncountermap_.allergencmapReviewedon),builder.literal("EDT"),builder.literal("MM/dd/yyyy HH:MI:ss am"))));
		List<Object[]> lastReviewDetailsObj = em.createQuery(cq).getResultList();
		JSONArray allergEncMapArray = new JSONArray();
		int i=0;
		for(Object[] values : lastReviewDetailsObj)
		{
			String fName=values[0]==null?"":values[0].toString();
			String lName=values[1]==null?"":values[1].toString();
			String mName=values[2]==null?"":values[2].toString();
			String credentials=values[3]==null?"":values[3].toString();
			JSONObject allergEncMapObj = new JSONObject();
			try {
				String empName = textFormatter.getFormattedName(fName, mName, lName, credentials);
				allergEncMapObj.put("reviewby", textFormatter.getCamelCaseText(empName));
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
				String a= dateFormat.format(values[5]);
				System.out.println("date >>>"+a);
				allergEncMapObj.put("reviewon", values[4]==null?"":values[4].toString());
				allergEncMapArray.put(i, allergEncMapObj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
		return allergEncMapArray;
	}
	
	/**
	 * Returns the formatted the pattern
	 * @param searchTerm
	 * @return
	 */
	private static String getLikePattern(final String searchTerm) {
		StringBuilder pattern = new StringBuilder();
		pattern.append(searchTerm.toLowerCase());
		pattern.append("%");
		return pattern.toString();
	}
	
	/**
	 * Method to sort values for drug allergy search
	 * @param jsonValues
	 * @return
	 */
	public List<AllergySearchBean> sortBy(List<AllergySearchBean> jsonValues) {
		Collections.sort(jsonValues, new Comparator<AllergySearchBean>() {
			@Override
			public int compare(AllergySearchBean arg0, AllergySearchBean arg1) {
				try {
					if (arg0.getSubstance().toString().equalsIgnoreCase("") && arg1.getSubstance().toString().equalsIgnoreCase("")) {
	                    return 0;
	                }
	                if (arg0.getSubstance().toString().equalsIgnoreCase("")) {
	                    return 1;
	                }
	                if (arg1.getSubstance().toString().equalsIgnoreCase("")) {
	                    return -1;
	                }
	                if(!((arg0.getSubstance().toString().equalsIgnoreCase("") && arg1.getSubstance().toString().equalsIgnoreCase("")) || arg0.getSubstance().toString().equalsIgnoreCase("") || arg1.getSubstance().toString().equalsIgnoreCase("")))
	                {
							return arg0.getSubstance().toString().compareTo(arg1.getSubstance().toString());
	                }
					return arg1.getSubstance().toString().compareTo(arg0.getSubstance().toString());
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				}
			}
		});
		return jsonValues;
	}
	
	@Override
	public List<PatientAllergiesHistoryBean> patientAllergiesHistory(String chartId) {
		List<PatientAllergiesHistoryBean> patAllergHistory = getPatAllergHistory(chartId);
		List<PatientAllergiesHistoryBean> patAllerg = getPatAllerg(chartId);
		patAllergHistory.addAll(patAllerg);
		List<PatientAllergiesHistoryBean> patAllergHistorySort = sortByHistoryBean(patAllergHistory);
		return patAllergHistorySort;
	}

	public List<PatientAllergiesHistoryBean> getPatAllerg(String chartId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<PatientAllergies> root = cq.from(PatientAllergies.class);
		Join<PatientAllergies, AllergiesType> allergType = root.join(PatientAllergies_.allergiesType,JoinType.INNER);
		Join<PatientAllergies, EmployeeProfile> empProf = root.join(PatientAllergies_.empProfileAllgModifiedByTable,JoinType.LEFT);
		cq.multiselect(root.get(PatientAllergies_.patAllergId).alias("Id"),
						builder.literal(1).alias("status"),
						builder.literal(0).alias("IsActive"),
						allergType.get(AllergiesType_.allergtypeName).alias("Type"),
						builder.coalesce(root.get(PatientAllergies_.patAllergDrugCategory),"-").alias("drugCategory"),
						builder.coalesce(root.get(PatientAllergies_.patAllergAllergicTo),"-").alias("allergen"),
						builder.coalesce(root.get(PatientAllergies_.patAllergReactionTo),"-").alias("Reaction"),
						builder.coalesce(root.get(PatientAllergies_.patAllergOnsetDate),"-").alias("OnsetDate"),
						root.get(PatientAllergies_.patAllergStatus),
						builder.coalesce(root.get(PatientAllergies_.patAllergResolvedDate),"-").alias("ResolvedDate"),
						builder.coalesce(empProf.get(EmployeeProfile_.empProfileFname), ""),
						builder.coalesce(empProf.get(EmployeeProfile_.empProfileLname),""),
						builder.coalesce(empProf.get(EmployeeProfile_.empProfileMi), ""),
						builder.coalesce(empProf.get(EmployeeProfile_.empProfileCredentials), ""),
						builder.function("glace_timezone", String.class, root.get(PatientAllergies_.patAllergModifiedOn),builder.literal("EDT"),builder.literal("MM/dd/yyyy HH:MI:ss am")).alias("modifiedOn")
						);
		cq.where(builder.equal(root.get(PatientAllergies_.patAllergChartId),chartId));
		List<Object[]> patAllerg = em.createQuery(cq).getResultList();
		List<PatientAllergiesHistoryBean> patAllergiesHistory = new ArrayList<PatientAllergiesHistoryBean>();
		for(Object[] patAllergDetails : patAllerg)
		{
			String fName=patAllergDetails[10]==null?"":patAllergDetails[10].toString();
			String lName=patAllergDetails[11]==null?"":patAllergDetails[11].toString();
			String mName=patAllergDetails[12]==null?"":patAllergDetails[12].toString();
			String credentials=patAllergDetails[13]==null?"":patAllergDetails[13].toString();
			String empName = "";
			try
			{
				empName = textFormatter.getFormattedName(fName, mName, lName, credentials);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			PatientAllergiesHistoryBean patAllergBean = new PatientAllergiesHistoryBean();
			patAllergBean.setId(patAllergDetails[0]==null?-1:(Integer)patAllergDetails[0]);
			patAllergBean.setStatus(patAllergDetails[1]==null?-1:(Integer)patAllergDetails[1]);
			patAllergBean.setIsActive(patAllergDetails[2]==null?-1:(Integer)patAllergDetails[2]);
			patAllergBean.setType(patAllergDetails[3]==null?"":patAllergDetails[3].toString());
			patAllergBean.setDrugCategory(patAllergDetails[4]==null?"":patAllergDetails[4].toString());
			patAllergBean.setAllergen(patAllergDetails[5]==null?"":patAllergDetails[5].toString());
			patAllergBean.setReaction(patAllergDetails[6]==null?"":patAllergDetails[6].toString());
			patAllergBean.setOnsetDate(patAllergDetails[7]==null?"":patAllergDetails[7].toString());
			if(patAllergDetails[8] != null)
			{
				patAllergBean.setAllergyStatus((Integer)patAllergDetails[8] == 1?"Active":((Integer)patAllergDetails[8] == 2?"Resolved":"Inactive"));
			}
			else
			{
				patAllergBean.setAllergyStatus("");
			}
			patAllergBean.setResolvedDate(patAllergDetails[9]==null?"":patAllergDetails[9].toString());
			patAllergBean.setModifiedBy(empName);
			patAllergBean.setModifiedOn(patAllergDetails[14]==null?"":patAllergDetails[14].toString());
			patAllergiesHistory.add(patAllergBean);
		}
		return patAllergiesHistory;
	}

	public List<PatientAllergiesHistoryBean> getPatAllergHistory(String chartId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<PatientAllergiesHistory> root = cq.from(PatientAllergiesHistory.class);
		Join<PatientAllergiesHistory, AllergiesType> allergType = root.join(PatientAllergiesHistory_.allergiesType,JoinType.INNER);
		Join<PatientAllergiesHistory, EmployeeProfile> empProf = root.join(PatientAllergiesHistory_.empProfileAllgModifiedByTable,JoinType.INNER);
		cq.multiselect(root.get(PatientAllergiesHistory_.patallergId).alias("Id"),
						builder.literal(0).alias("status"),
						builder.literal(0).alias("IsActive"),
						allergType.get(AllergiesType_.allergtypeName).alias("Type"),
						builder.coalesce(root.get(PatientAllergiesHistory_.patallergDrugcategory),"-").alias("drugCategory"),
						builder.coalesce(root.get(PatientAllergiesHistory_.patallergAllergicto),"-").alias("allergen"),
						builder.coalesce(root.get(PatientAllergiesHistory_.patallergReactionto),"-").alias("Reaction"),
						builder.coalesce(root.get(PatientAllergiesHistory_.patallergOnsetdate),"-").alias("OnsetDate"),
						root.get(PatientAllergiesHistory_.patallergStatus),
						builder.coalesce(root.get(PatientAllergiesHistory_.patallergResolveddate),"-").alias("ResolvedDate"),
						builder.coalesce(empProf.get(EmployeeProfile_.empProfileFname), ""),
						builder.coalesce(empProf.get(EmployeeProfile_.empProfileLname),""),
						builder.coalesce(empProf.get(EmployeeProfile_.empProfileMi), ""),
						builder.coalesce(empProf.get(EmployeeProfile_.empProfileCredentials), ""),
						builder.function("glace_timezone", String.class, root.get(PatientAllergiesHistory_.patallergModifiedon),builder.literal("EDT"),builder.literal("MM/dd/yyyy HH:MI:ss am")).alias("modifiedOn")
						);
		cq.where(builder.equal(root.get(PatientAllergiesHistory_.patallergChartid),chartId));
		List<Object[]> patAllergHis = em.createQuery(cq).getResultList();
		List<PatientAllergiesHistoryBean> patAllergiesHistory = new ArrayList<PatientAllergiesHistoryBean>();
		for(Object[] patAllergDetails : patAllergHis)
		{
			String fName=patAllergDetails[10]==null?"":patAllergDetails[10].toString();
			String lName=patAllergDetails[11]==null?"":patAllergDetails[11].toString();
			String mName=patAllergDetails[12]==null?"":patAllergDetails[12].toString();
			String credentials=patAllergDetails[13]==null?"":patAllergDetails[13].toString();
			String empName = "";
			try
			{
				empName = textFormatter.getFormattedName(fName, mName, lName, credentials);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			PatientAllergiesHistoryBean patAllergBean = new PatientAllergiesHistoryBean();
			patAllergBean.setId(patAllergDetails[0]==null?-1:(Integer)patAllergDetails[0]);
			patAllergBean.setStatus(patAllergDetails[1]==null?-1:(Integer)patAllergDetails[1]);
			patAllergBean.setIsActive(patAllergDetails[2]==null?-1:(Integer)patAllergDetails[2]);
			patAllergBean.setType(patAllergDetails[3]==null?"":patAllergDetails[3].toString());
			patAllergBean.setDrugCategory(patAllergDetails[4]==null?"":patAllergDetails[4].toString());
			patAllergBean.setAllergen(patAllergDetails[5]==null?"":patAllergDetails[5].toString());
			patAllergBean.setReaction(patAllergDetails[6]==null?"":patAllergDetails[6].toString());
			patAllergBean.setOnsetDate(patAllergDetails[7]==null?"":patAllergDetails[7].toString());
			if(patAllergDetails[8] != null)
			{
				patAllergBean.setAllergyStatus((Integer)patAllergDetails[8] == 1?"Active":((Integer)patAllergDetails[8] == 2?"Resolved":"Inactive"));
			}
			else
			{
				patAllergBean.setAllergyStatus("");
			}
			patAllergBean.setResolvedDate(patAllergDetails[9]==null?"":patAllergDetails[9].toString());
			patAllergBean.setModifiedBy(empName);
			patAllergBean.setModifiedOn(patAllergDetails[14]==null?"":patAllergDetails[14].toString());
			patAllergiesHistory.add(patAllergBean);
		}
		return patAllergiesHistory;
	}
	
	public List<PatientAllergiesHistoryBean> sortByHistoryBean(List<PatientAllergiesHistoryBean> jsonValues) {
		Collections.sort(jsonValues, new Comparator<PatientAllergiesHistoryBean>() {
			@Override
			public int compare(PatientAllergiesHistoryBean arg0, PatientAllergiesHistoryBean arg1) {
				try {
					if (arg0.getId().equals(null) && arg1.getId().equals(null)) {
	                    return 0;
	                }
	                if (arg0.getId().equals(null)) {
	                    return 1;
	                }
	                if (arg1.getId().equals(null)) {
	                    return -1;
	                }
	                if(!((arg0.getId().equals(null) && arg1.getId().equals(null)) || arg0.getId().equals(null) || arg1.getId().equals(null)))
	                {
							return arg0.getId().compareTo(arg1.getId());
	                }
					return arg1.getId().compareTo(arg0.getId());
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				}
			}
		});
		
		System.out.println("after 1st sorting>>>>>>"+jsonValues);
		Collections.sort(jsonValues, new Comparator<PatientAllergiesHistoryBean>() {
			@Override
			public int compare(PatientAllergiesHistoryBean arg0, PatientAllergiesHistoryBean arg1) {
				if(arg0.getId().equals(arg1.getId()))
				{
				try {
					if (arg0.getModifiedOn().equalsIgnoreCase(null) && arg1.getModifiedOn().equalsIgnoreCase(null)) {
	                    return 0;
	                }
	                if (arg0.getModifiedOn().equalsIgnoreCase(null)) {
	                    return 1;
	                }
	                if (arg1.getModifiedOn().equalsIgnoreCase(null)) {
	                    return -1;
	                }
	                if(!((arg0.getModifiedOn().equalsIgnoreCase(null) && arg1.getModifiedOn().equalsIgnoreCase(null)) || arg0.getModifiedOn().equalsIgnoreCase(null) || arg1.getModifiedOn().equalsIgnoreCase(null)))
	                {
	                	/*SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:MM:ss");
	                	System.out.println("arg0.getId()"+arg0.getId());
	                	Date m1 = formatter.parse(arg0.getModifiedOn());
	                	System.out.println("m1---"+m1);
	                	Date m2 = formatter.parse(arg1.getModifiedOn());
	                	System.out.println("m2---"+m2);*/
							return arg1.getModifiedOn().compareTo(arg0.getModifiedOn());
	                }
					return arg1.getId().compareTo(arg0.getId());
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				}
			}
				return 0;
			}
		});
		
		System.out.println("after second sorting"+jsonValues);
		
		Collections.sort(jsonValues, new Comparator<PatientAllergiesHistoryBean>() {
			@Override
			public int compare(PatientAllergiesHistoryBean arg0, PatientAllergiesHistoryBean arg1) {
				if(arg0.getId().equals(arg1.getId()))
				{
					if(arg0.getModifiedOn().equals(arg1.getModifiedOn()))
					{
						try {
							if (arg0.getStatus().equals(null) && arg1.getStatus().equals(null)) {
			                    return 0;
			                }
			                if (arg0.getStatus().equals(null)) {
			                    return 1;
			                }
			                if (arg1.getStatus().equals(null)) {
			                    return -1;
			                }
			                if(!((arg0.getStatus().equals(null) && arg1.getStatus().equals(null)) || arg0.getStatus().equals(null) || arg1.getStatus().equals(null)))
			                {
									return arg1.getStatus().compareTo(arg0.getStatus());
			                }
							return arg1.getStatus().compareTo(arg0.getStatus());
						} catch (Exception e) {
							e.printStackTrace();
							return 0;
						}
					}
				}
			return 0;
			}
		});
		return jsonValues;
	}

	@Override
	public void saveAllergies(String allergData) {
		// TODO Auto-generated method stub
		JSONArray jsonParametersArray = null;
		try
		{
			String editDataDecode=HUtil.Nz(URLDecoder.decode(allergData,"UTF-8"),"");
			jsonParametersArray = new JSONArray(editDataDecode);
			for(int i=0;i<jsonParametersArray.length();i++)
			{
				JSONObject jsonParameters = jsonParametersArray.getJSONObject(i);
				if(jsonParameters.has("id"))
				{
					editAllergies(URLEncoder.encode(jsonParameters.toString(), "UTF-8").toString());
				}
				else
				{
					String status = jsonParameters.getString("stat");
					String charset = URLEncoder.encode(jsonParameters.toString(), "UTF-8").toString();
					System.out.println("charset"+charset);
					if(status.trim().equalsIgnoreCase("3")) {    //Inactive

						saveNewAllergies(5, charset);

					}else if(status.trim().equalsIgnoreCase("2")) {	//Resolved
						
						saveNewAllergies(4, charset);

					} else if (status.trim().equalsIgnoreCase("1")) {	//Active
						
						saveNewAllergies(1, charset);

					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}