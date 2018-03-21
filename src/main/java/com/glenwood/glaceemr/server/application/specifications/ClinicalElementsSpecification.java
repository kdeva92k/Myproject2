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

import com.glenwood.glaceemr.server.application.models.ClinicalElementTemplateMapping;
import com.glenwood.glaceemr.server.application.models.ClinicalElementTemplateMapping_;
import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsCondition;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsCondition_;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions_;
import com.glenwood.glaceemr.server.application.models.ClinicalElements_;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping_;
import com.glenwood.glaceemr.server.application.models.SoapElementDatalist;
import com.glenwood.glaceemr.server.application.models.SoapElementDatalist_;
import com.glenwood.glaceemr.server.application.models.TemplateMappingOptions;
import com.glenwood.glaceemr.server.application.models.TemplateMappingOptions_;

public class ClinicalElementsSpecification {
	
	/**
	 * Get the clinical element details of given GWID's
	 * 
	 * @param gwid
	 * 
	 */
	public static Specification<ClinicalElements> getClinicalElements(final List<String> gwid){

		return new Specification<ClinicalElements>(){
			@Override
			public Predicate toPredicate(Root<ClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				gwid.add("-1");
				return root.get(ClinicalElements_.clinicalElementsGwid).in(gwid);
			}

		};
	}

	/**
	 * 
	 *   Get Clinical element Details of given GWID
	 * 
	 * @param gwid
	 * 
	 */
	
	public static Specification<ClinicalElements> getClinicalElement(final String gwid){

		return new Specification<ClinicalElements>(){
			@Override
			public Predicate toPredicate(Root<ClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				return cb.equal(root.get(ClinicalElements_.clinicalElementsGwid),gwid);
			}

		};
	}
	
	/**
	 * 
	 *   Get Clinical element Details of given GWID
	 * 
	 * @param gwid
	 * 
	 */
	
	public static Specification<ClinicalElements> getActiveClinicalElement(final String gwid){

		return new Specification<ClinicalElements>(){
			@Override
			public Predicate toPredicate(Root<ClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate isActive=cb.equal(root.get(ClinicalElements_.clinicalElementsIsactive),true);
				Predicate isGwid=cb.equal(root.get(ClinicalElements_.clinicalElementsGwid),gwid);
				return cb.and(isActive,isGwid);
			}

		};
	}
	
	/**
	 * Get clinical Element Options for the given GWID
	 * 
	 *  @param gwid
	 *
	 */
	public static Specification<ClinicalElementsOptions> getclinicalElementOptions(final String gwid){

		return new Specification<ClinicalElementsOptions>(){
			@Override
			public Predicate toPredicate(Root<ClinicalElementsOptions> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate isActive=cb.equal(root.get(ClinicalElementsOptions_.clinicalElementsOptionsActive),true);
				Predicate isGwid=cb.equal(root.get(ClinicalElementsOptions_.clinicalElementsOptionsGwid),gwid);
				query.orderBy(cb.asc(root.get(ClinicalElementsOptions_.clinicalElementsOptionsOrderBy)),cb.asc(root.get(ClinicalElementsOptions_.clinicalElementsOptionsValue)));
				return cb.and(isActive,isGwid);
			}

		};
	}
	
	/**
	 * Get the details of clinical elements of gwid's of specified age and sex
	 * 
	 * @param gwids
	 * @param patientAge
	 * @param patientSex
	 * 
	 */
	public static Specification<ClinicalElements> getClinicalElements(final String gwidPattern,final Integer patientAge,final short patientSex){

		return new Specification<ClinicalElements>(){

			
			@Override
			public Predicate toPredicate(Root<ClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
			
				Predicate gwid=cb.like(root.get(ClinicalElements_.clinicalElementsGwid),gwidPattern);
				Predicate isTrue=cb.equal(root.get(ClinicalElements_.clinicalElementsIsactive),true);
				Predicate gender=root.get(ClinicalElements_.clinicalElementsGender).in(patientSex,0);
				return cb.and(gwid,isTrue,gender);
			}

		};
	}
	
	
	/**
	 * Get the details of clinical elements of GWID with client Id falling under specified age and sex
	 * 
	 * @param gwids
	 * @param patientAge
	 * @param patientSex
	 * 
	 */
	public static Specification<ClinicalElements> getClinicalElementsByGWIDPattern(final String clientId,final String gwidPattern,final Integer patientAge,final short patientSex){

		return new Specification<ClinicalElements>(){

			
			@Override
			public Predicate toPredicate(Root<ClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
			
				root.fetch(ClinicalElements_.clinicalTextMappings,JoinType.LEFT);
				Predicate defgwid=cb.like(root.get(ClinicalElements_.clinicalElementsGwid),"000"+gwidPattern);
				Predicate clientIdgwid=cb.like(root.get(ClinicalElements_.clinicalElementsGwid),clientId+gwidPattern);
				Predicate finalgwid=cb.or(defgwid,clientIdgwid);
				Predicate isTrue=cb.equal(root.get(ClinicalElements_.clinicalElementsIsactive),true);
				Predicate gender=root.get(ClinicalElements_.clinicalElementsGender).in(patientSex,0);
				return cb.and(finalgwid,isTrue,gender);
			}

		};
	}
	
	/**
	 * Specification for default Age condition 
	 * 
	 * @return
	 */
	public static Specification<ClinicalElements> defaultAgePed(final Integer defAge){
		return new Specification<ClinicalElements>(){
			@Override
			public Predicate toPredicate(Root<ClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate defaultstartAge=cb.equal(root.get(ClinicalElements_.clinicalElementsStartage),defAge);
				Predicate defaultendAge=cb.equal(root.get(ClinicalElements_.clinicalElementsEndage),defAge);
				return cb.and(defaultstartAge,defaultendAge); 
			}

		};
	}
	
	
	/**
	 * Specification for null Age condition 
	 * 
	 * @return
	 */
	public static Specification<ClinicalElements> nullAgePed(){
		return new Specification<ClinicalElements>(){
			@Override
			public Predicate toPredicate(Root<ClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate defaultstartAge=cb.isNull(root.get(ClinicalElements_.clinicalElementsStartage));
				Predicate defaultendAge=cb.isNull(root.get(ClinicalElements_.clinicalElementsEndage));
				return cb.and(defaultstartAge,defaultendAge); 
			}

		};
	}
	
	
	/**
	 * 
	 * Specification for patient age condition 
	 * 
	 * @param patientAge
	 * @return
	 */
	public static Specification<ClinicalElements> patientAgePed(final Integer patientAge){
		return new Specification<ClinicalElements>(){
			@Override
			public Predicate toPredicate(Root<ClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate startAge=cb.lessThan(root.get(ClinicalElements_.clinicalElementsStartage),patientAge);
				Predicate endAge=cb.greaterThanOrEqualTo(root.get(ClinicalElements_.clinicalElementsEndage),patientAge);
				return cb.and(startAge,endAge);
			}

		};
	}
	
	
	/**
	 * 
	 *  Get the clinical elements Details
	 * 
	 * @param templateID
	 * @param patientSex
	 * @param tabType
	 * @param leafCreatedDate
	 * @param ageinDay
	 * @return
	 * 
	 */
	
	public static Specification<ClinicalElements> getClinicalElements(final Integer templateID,final Short patientSex,final Integer tabType,final String leafCreatedDate,final Integer ageinDay,final boolean isAgeBased){

		return new Specification<ClinicalElements>(){

			@Override
			public Predicate toPredicate(Root<ClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<ClinicalElements,ClinicalElementTemplateMapping> paramJoin=root.join(ClinicalElements_.clinicalElementTemplateMapping,JoinType.INNER);
				Predicate tempPredicate = cb.equal(paramJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid),templateID);
				Predicate tabTypePred = cb.equal(paramJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingType),tabType);
				Predicate genderPred =root.get(ClinicalElements_.clinicalElementsGender).in(0,patientSex);
				Predicate timePred=null;
				
				Predicate finalPred=cb.and(tempPredicate,tabTypePred,genderPred);
				if(!leafCreatedDate.toString().equalsIgnoreCase("-1")){
					Predicate timeStampPred=cb.lessThanOrEqualTo(paramJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTimestamp),Timestamp.valueOf(leafCreatedDate));
					Predicate nullPred=cb.isNull(paramJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTimestamp));
					timePred=cb.or(timeStampPred,nullPred);
				}
				Predicate age=null;
				if(isAgeBased==true){
					Join<ClinicalElements, ClinicalElementsCondition> condParamJoin=root.join(ClinicalElements_.clinicalElementsConditions,JoinType.LEFT);
					Predicate defaultstartAge=cb.isNull(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage));
					Predicate defaultendAge=cb.isNull(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage));
					Predicate startAge=cb.lessThan(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage),ageinDay);
					Predicate endAge=cb.greaterThanOrEqualTo(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage),ageinDay);
					Predicate defAgePred=cb.and(defaultstartAge,defaultendAge);
					Predicate agePred=cb.and(startAge,endAge);
					age=cb.or(defAgePred,agePred);
				}
			
				if(!leafCreatedDate.toString().equalsIgnoreCase("-1")){
					finalPred=cb.and(finalPred,timePred);
				}
				if(isAgeBased==true){
					finalPred=cb.and(finalPred,age);
				}
				
				return finalPred;
			}

		};
	}
	
	/**
	 * Get the clinical element falling under age Rule
	 * 
	 * @param gwid
	 * 
	 */
	public static Specification<ClinicalElements> getAgeBasedSpecification(final Integer ageinDay){

		return new Specification<ClinicalElements>(){
			@Override
			public Predicate toPredicate(Root<ClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				root.join(ClinicalElements_.clinicalElementsConditions,JoinType.LEFT);
				Predicate defaultstartAge=cb.isNull(root.get(ClinicalElements_.clinicalElementsStartage));
				Predicate defaultendAge=cb.isNull(root.get(ClinicalElements_.clinicalElementsEndage));
				Predicate startAge=cb.lessThan(root.get(ClinicalElements_.clinicalElementsStartage),ageinDay);
				Predicate endAge=cb.greaterThanOrEqualTo(root.get(ClinicalElements_.clinicalElementsEndage),ageinDay);
				Predicate defAgePred=cb.and(defaultstartAge,defaultendAge);
				Predicate agePred=cb.and(startAge,endAge);
				return cb.or(defAgePred,agePred);
			}

		};
	}
	
	
	
	
	
	
	

	

	
	/**
	 * Get the clinical Text Mapping details of given GWID
	 * 
	 * @param gwid
	 * 
	 */
	
	public static Specification<ClinicalTextMapping> getClinicalTextMapping(final String gwid){

		return new Specification<ClinicalTextMapping>(){
			@Override
			public Predicate toPredicate(Root<ClinicalTextMapping> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(ClinicalTextMapping_.clinicalTextMappingTextboxGwid),gwid);
			}

		};
	}
	
	
	
	
	
	/**
	 * Get clinical Element Options for the given List of GWID's order by GWID , order by column  and option value
	 * 
	 *  @param gwid
	 *
	 */
	public static Specification<ClinicalElementsOptions> getclinicalElementOptions(final List<String> gwid){

		return new Specification<ClinicalElementsOptions>(){
			@Override
			public Predicate toPredicate(Root<ClinicalElementsOptions> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate isGwid=root.get(ClinicalElementsOptions_.clinicalElementsOptionsGwid).in(gwid);
				query.orderBy(cb.asc(root.get(ClinicalElementsOptions_.clinicalElementsOptionsGwid)),cb.asc(root.get(ClinicalElementsOptions_.clinicalElementsOptionsOrderBy)),cb.asc(root.get(ClinicalElementsOptions_.clinicalElementsOptionsValue)));
				return isGwid;
			}

		};
	}
	
	/**
	 * Get clinical Elements for the given tabId and templateId
	 * 
	 *  @param gwid
	 *
	 */
	public static Specification<ClinicalElementTemplateMapping> getclinicalElementByTabAndTempId(final Integer tabId,final Integer templateId){

		return new Specification<ClinicalElementTemplateMapping>(){
			@Override
			public Predicate toPredicate(Root<ClinicalElementTemplateMapping> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate tabPred=cb.equal(root.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingType),tabId);
				Predicate tempPred=cb.equal(root.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid),templateId);
				return cb.and(tabPred,tempPred);
			}

		};
	}
	
	

	/**
	 * @param gwids :List of required GlenwoodIds
	 * @param templateid :required template
	 * @return 
	 */
	
	public static Specification<ClinicalElementTemplateMapping> getGWIDTemplateClinicalElements(final List<String> gwids,final Integer templateid)
	{
		return new Specification<ClinicalElementTemplateMapping>() {

			@Override
			public Predicate toPredicate(
					Root<ClinicalElementTemplateMapping> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate gwid=	cb.substring(root.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingGwid), 4, 7).in(gwids);
				Predicate template=cb.equal(root.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid),templateid);

				return cb.and(gwid,template);
			}
		};
	}
	
	
	/**
	 * Get Clinical Options
	 * 
	 * @param elementIds
	 * @return
	 */
	public static Specification<ClinicalElementsOptions> getTempClinicalOptions(final List<Integer> elementIds,final Integer templateId,final List<String> gwids,final boolean isAgeBased,final short patientSex,final Integer ageinDay){
		return new Specification<ClinicalElementsOptions>(){
			@Override
			public Predicate toPredicate(Root<ClinicalElementsOptions> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Join<ClinicalElementsOptions,ClinicalElements> optionJoin=root.join(ClinicalElementsOptions_.clinicalElement,JoinType.INNER);
				Join<ClinicalElements,ClinicalElementTemplateMapping> elemJoin=optionJoin.join(ClinicalElements_.clinicalElementTemplateMapping,JoinType.INNER);
				Join<ClinicalElementTemplateMapping, TemplateMappingOptions> mapJoin=elemJoin.join(ClinicalElementTemplateMapping_.templateMappingOptions,JoinType.INNER);
				Predicate valuePred=cb.equal(mapJoin.get(TemplateMappingOptions_.templateMappingOptionsOptionValue).as(String.class),root.get(ClinicalElementsOptions_.clinicalElementsOptionsValue));
				Predicate tempCond=cb.equal(elemJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid),templateId);
				Predicate gwidPred=root.get(ClinicalElementsOptions_.clinicalElementsOptionsGwid).in(gwids);
				Predicate genderPred=root.get(ClinicalElementsOptions_.clinicalElementsOptionsGender).in(0,patientSex);
				Predicate age=null;
				Predicate finalPed=null;
				if(isAgeBased && ageinDay!=-1){
					@SuppressWarnings("unchecked")
					Join<ClinicalElementsOptions,ClinicalElementsCondition> condParamJoin=root.join(ClinicalElementsOptions_.clinicalElementsConditions,JoinType.LEFT);
					Predicate defaultstartAge=cb.isNull(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage));
					Predicate defaultendAge=cb.isNull(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage));
					Predicate startAge=cb.lessThan(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionStartage),ageinDay);
					Predicate endAge=cb.greaterThanOrEqualTo(condParamJoin.get(ClinicalElementsCondition_.clinicalElementsConditionEndage),ageinDay);
					Predicate defAgePred=cb.and(defaultstartAge,defaultendAge);
					Predicate agePred=cb.and(startAge,endAge);
					age=cb.or(defAgePred,agePred);
				}
				
				if(isAgeBased && ageinDay!=-1){
					finalPed=cb.and(valuePred,tempCond,gwidPred,age);
				}else{
					finalPed=cb.and(valuePred,tempCond,gwidPred);
				}
				return finalPed;
			}
		};
	}
	
	/**
	 * Get Soap element list based on tabId and elementId
	 * 
	 * @param tabId
	 * @param gwid
	 * @return
	 */
	public static Specification<SoapElementDatalist> getSoapElementDataList(final Integer tabId,final String gwid){
		return new Specification<SoapElementDatalist>(){
			@Override
			public Predicate toPredicate(Root<SoapElementDatalist> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate idPred=cb.like(root.get(SoapElementDatalist_.soapElementDatalistElementid),gwid);
				Predicate tabIdPred=cb.equal(root.get(SoapElementDatalist_.soapElementDatalistTabid),tabId);
				return cb.and(idPred,tabIdPred);
			}
		};
	}	
	
	/**
	 * Get Soap element list based on dataList Id
	 * 
	 * @param dataListId
	 * @return
	 */
	public static Specification<SoapElementDatalist> getSoapElementDataListById(final Integer dataListId){
		return new Specification<SoapElementDatalist>(){
			@Override
			public Predicate toPredicate(Root<SoapElementDatalist> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(SoapElementDatalist_.soapElementDatalistId),dataListId);
			}
		};
	}	
}