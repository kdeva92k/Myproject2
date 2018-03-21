package com.glenwood.glaceemr.server.application.specifications;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
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
import com.glenwood.glaceemr.server.application.models.ClinicalSystem;
import com.glenwood.glaceemr.server.application.models.ClinicalSystemOrder;
import com.glenwood.glaceemr.server.application.models.ClinicalSystemOrder_;
import com.glenwood.glaceemr.server.application.models.ClinicalSystem_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.PeElement;
import com.glenwood.glaceemr.server.application.models.PeElementDetail;
import com.glenwood.glaceemr.server.application.models.PeElementDetailOption;
import com.glenwood.glaceemr.server.application.models.PeElementDetailOption_;
import com.glenwood.glaceemr.server.application.models.PeElementDetail_;
import com.glenwood.glaceemr.server.application.models.PeElementGroup;
import com.glenwood.glaceemr.server.application.models.PeElementGroup_;
import com.glenwood.glaceemr.server.application.models.PeElement_;
import com.glenwood.glaceemr.server.application.models.PeSystem;
import com.glenwood.glaceemr.server.application.models.PeSystem_;

public class ExaminationSpecification {


	/**
	 * Get active Examination Systems by templateId
	 * 
	 * @param templateId
	 * @return
	 */
	public static Specification<ClinicalSystem> getActiveExaminationSystems(final Integer templateId){
		return new Specification<ClinicalSystem>(){
			@Override
			public Predicate toPredicate(Root<ClinicalSystem> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<ClinicalSystem,PeElementGroup> grpJoin=root.join(ClinicalSystem_.peElementGroups,JoinType.INNER);
				Join<PeElementGroup,PeElement> elementJoin=grpJoin.join(PeElementGroup_.peElements,JoinType.INNER);
				Join<ClinicalSystem, ClinicalSystemOrder> paramJoin=(Join<ClinicalSystem, ClinicalSystemOrder>)root.fetch(ClinicalSystem_.clinicalSystemOrders,JoinType.LEFT);
				//Join<ClinicalSystem,ClinicalSystemOrder> paramJoin=root.join(ClinicalSystem_.clinicalSystemOrders,JoinType.LEFT);	
				//paramJoin.on(cb.equal(paramJoin.get(ClinicalSystemOrder_.clinicalSystemOrderTemplateid),templateId));
				Predicate tempPred=cb.equal(paramJoin.get(ClinicalSystemOrder_.clinicalSystemOrderTemplateid),templateId);
				Predicate tempNullPred=cb.isEmpty(root.get(ClinicalSystem_.clinicalSystemOrders));
				Predicate isActivePred=cb.equal(root.get(ClinicalSystem_.clinicalSystemIsactive),true);
				Predicate gwidNotNull=cb.isNotNull(root.get(ClinicalSystem_.clinicalSystemPeGwid));
				Predicate templatePred=cb.or(tempPred,tempNullPred);
				Expression<Object> queryCase=cb.selectCase()
						.when(cb.isNotNull(paramJoin.get(ClinicalSystemOrder_.clinicalSystemOrderPe)),paramJoin.get(ClinicalSystemOrder_.clinicalSystemOrderPe)).otherwise(root.get(ClinicalSystem_.clinicalSystemOrderby));
				query.multiselect(queryCase);
				//query.orderBy(cb.asc(queryCase));
				query.orderBy(cb.asc(paramJoin.get(ClinicalSystemOrder_.clinicalSystemOrderPe)),cb.asc(root.get(ClinicalSystem_.clinicalSystemName)),cb.asc(root.get(ClinicalSystem_.clinicalSystemOrderby)));
				query.distinct(true);
				return cb.and(templatePred,isActivePred,gwidNotNull);
			}
		};
	}




	/**
	 * Get active Examination Systems
	 * @return
	 */
	public static Specification<PeElement> getExaminationSystems(){
		return new Specification<PeElement>(){
			@Override
			public Predicate toPredicate(Root<PeElement> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(PeElement_.peElementIsactive),true);
			}
		};
	}




	/**
	 * Get active Examination elements
	 * 
	 * @return
	 */
	public static Specification<PeElement> getActiveExaminationElements(){
		return new Specification<PeElement>(){
			@Override
			public Predicate toPredicate(Root<PeElement> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(PeElement_.peElementIsactive),true);
			}
		};
	}




	/**
	 * Get clinical system details by system Id's
	 * 
	 * @param systemID
	 * @return
	 */
	public static Specification<ClinicalSystem> getClincialSysDetailsById(final List<Integer> systemIds){
		return new Specification<ClinicalSystem>(){
			@Override
			public Predicate toPredicate(Root<ClinicalSystem> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate isActive=cb.equal(root.get(ClinicalSystem_.clinicalSystemIsactive),true);
				Predicate gwidPred=root.get(ClinicalSystem_.clinicalSystemPeGwid).in(systemIds);
				return cb.and(isActive,gwidPred);
			}
		};
	}

	/**
	 * Get Examination element group details by system Id's
	 * 
	 * @param systemIds
	 * @return
	 */
	public static Specification<PeElementGroup> getElementGrpDetailsBySysId(final List<Integer> systemIds){
		return new Specification<PeElementGroup>(){
			@Override
			public Predicate toPredicate(Root<PeElementGroup> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate isActive=cb.equal(root.get(PeElementGroup_.peElementGroupIsactive),true);
				Predicate gwidPred=root.get(PeElementGroup_.peElementGroupSystemId).in(systemIds);
				query.orderBy(cb.asc(root.get(PeElementGroup_.peElementGroupSystemId)),cb.asc(root.get(PeElementGroup_.peElementGroupOrderby)));
				query.distinct(true);
				return cb.and(isActive,gwidPred);
			}
		};
	}



	/**
	 * Get active Examination element detail by element Id's
	 * 
	 * @param elementIds
	 * @return
	 */
	public static Specification<PeElementDetail> getElementDetailByElemId(final List<Integer> elementIds){
		return new Specification<PeElementDetail>(){
			@Override
			public Predicate toPredicate(Root<PeElementDetail> root,CriteriaQuery<?> query, CriteriaBuilder cb) {

				Predicate elemPred=root.get(PeElementDetail_.peElementDetailElementId).in(elementIds);
				Predicate isActive=cb.equal(root.get(PeElementDetail_.peElementDetailIsactive),true);
				query.orderBy(cb.asc(root.get(PeElementDetail_.peElementDetailElementId)),cb.asc(root.get(PeElementDetail_.peElementDetailOrderby)));
				query.distinct(true);
				return cb.and(elemPred,isActive);
			}
		};
	}

	/**
	 * Get active Examination detail options by element detail Id's
	 * 
	 * @param elementIds
	 * @return
	 */
	public static Specification<PeElementDetailOption> getDetailOptByElementId(final List<Integer> detailIds,final boolean isPatientReq,final Integer templateId,final Integer patientId,final Integer encounterId){
		return new Specification<PeElementDetailOption>(){
			@Override
			public Predicate toPredicate(Root<PeElementDetailOption> root,CriteriaQuery<?> query, CriteriaBuilder cb) {

				if(isPatientReq){
					Join<PeElementDetailOption,PatientClinicalElements> paramJoin=root.join(PeElementDetailOption_.patientClinicalElements,JoinType.INNER);
					Predicate patPred=cb.equal(paramJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
					Predicate encPred=cb.equal(paramJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
					paramJoin.on(cb.and(patPred,encPred));
				}else{
					Join<PeElementDetailOption,ClinicalElementTemplateMapping> tempJoin=root.join(PeElementDetailOption_.clinicalElementTemplateMappings,JoinType.INNER);
					tempJoin.on(cb.equal(tempJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid),templateId));
				}

				root.fetch(PeElementDetailOption_.clinicalElement);
				Predicate detIdPred=root.get(PeElementDetailOption_.peElementDetailOptionDetailId).in(detailIds);
				query.orderBy(cb.asc(root.get(PeElementDetailOption_.peElementDetailOptionDetailId)),cb.asc(root.get(PeElementDetailOption_.peElementDetailOptionOrderby)));
				Predicate isActive=cb.equal(root.get(PeElementDetailOption_.peElementDetailOptionIsactive), true);
				query.distinct(true);
				return cb.and(detIdPred,isActive);
			}
		};
	}




	/**
	 * Get Examination elements by group Id's
	 * 
	 * @param groupIds
	 * @return
	 */

	public static Specification<PeElement> getElementsByGrpId(final List<Integer> groupIds,final boolean isPatientReq,final Integer templateId,final Integer patientId,final Integer encounterId){
		return new Specification<PeElement>(){
			@Override
			public Predicate toPredicate(Root<PeElement> root,CriteriaQuery<?> query, CriteriaBuilder cb) {


				root.fetch(PeElement_.clinicalTextMappings,JoinType.INNER);
				Join<PeElement, ClinicalElementTemplateMapping> tempMapJoin=root.join(PeElement_.clinicalElementTemplateMappings,JoinType.INNER);
				if(isPatientReq){
					Join<PeElement,PatientClinicalElements> paramJoin=root.join(PeElement_.patientClinicalElements,JoinType.INNER);
					Predicate patPred=cb.equal(paramJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
					Predicate encPred=cb.equal(paramJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
					paramJoin.on(cb.and(patPred,encPred));
				}
				Predicate tempCond=cb.equal(tempMapJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid),templateId);
				Predicate grpidPred=root.get(PeElement_.peElementGroupId).in(groupIds);
				Predicate isActive=cb.equal(root.get(PeElement_.peElementIsactive), true);

				Predicate finalPred=null;
				if(!isPatientReq){
					finalPred=cb.and(tempCond,grpidPred,isActive);
				}else{
					finalPred=grpidPred;
				}
				query.distinct(true);
				query.orderBy(cb.asc(root.get(PeElement_.peElementGroupId)),cb.asc(root.get(PeElement_.peElementOrderby)));
				return finalPred;
			}
		};
	}
	
	
	/**
	 * 
	 * Get Patient Clinical elements of a patient by tabType other than PE System notes data (00002001%000000000)
	 * 
	 * @param encounterId
	 * @param gwid
	 * @param value
	 * 
	 */
	public static Specification<PatientClinicalElements> getTemplatePEElements(final Integer encounterId,final Integer templateId){
		return new Specification<PatientClinicalElements>(){
			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalElements, ClinicalElements> clinicalJoin=root.join(PatientClinicalElements_.clinicalElement,JoinType.INNER);
				Join<ClinicalElements,ClinicalElementTemplateMapping> tempJoin=clinicalJoin.join(ClinicalElements_.clinicalElementTemplateMapping,JoinType.INNER);
				Predicate gwidPred=cb.notLike(root.get(PatientClinicalElements_.patientClinicalElementsGwid),"00002001%000000000");
				Predicate encPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId);
				Predicate tempIdPred=cb.equal(tempJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid), templateId);
				Predicate typePred=cb.equal(tempJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingType),5);
				return cb.and(gwidPred,encPred,tempIdPred,typePred);
			}
		};
	}		
	
	
	/**
	 * 
	 *  Get the PE clinical elements Details
	 * 
	 * @param templateID
	 * @param patientSex
	 * @param tabType
	 * @param leafCreatedDate
	 * @param ageinDay
	 * @param systemIds if any
	 * @return
	 * 
	 */
	
	public static Specification<ClinicalElements> getPEClinicalElements(final Integer templateID,final Short patientSex,final Integer tabType,final String leafCreatedDate,final Integer ageinDay,final boolean isAgeBased,final List<Integer> grps){

		return new Specification<ClinicalElements>(){

			@SuppressWarnings("deprecation")
			@Override
			public Predicate toPredicate(Root<ClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<ClinicalElements,ClinicalElementTemplateMapping> paramJoin=root.join(ClinicalElements_.clinicalElementTemplateMapping,JoinType.INNER);		
				Join<ClinicalElementTemplateMapping,PeElement> elemJoin =paramJoin.join(ClinicalElementTemplateMapping_.peElement,JoinType.INNER);
				Predicate grpPred=null;
				if(grps.size()>0){
					grpPred=elemJoin.get(PeElement_.peElementGroupId).in(grps);
				}
				Predicate tempPredicate = cb.equal(paramJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid),templateID);
				Predicate tabTypePred = cb.equal(paramJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingType),tabType);
				Predicate genderPred =root.get(ClinicalElements_.clinicalElementsGender).in(0,patientSex);
				Predicate dataTypePred=cb.equal(root.get(ClinicalElements_.clinicalElementsDatatype),3);
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
				if(grps.size()>0){
					finalPred=cb.and(finalPred,grpPred);
				}
				
				return cb.and(finalPred,dataTypePred);
			}

		};
	}
	
	
	
	
	/**
	 * Get systems which have deferred element active 
	 * 
	 * @param systemIds
	 * @return
	 */
	public static Specification<PeSystem> getActiveDeferredSys(final List<Integer> systemIds){
		return new Specification<PeSystem>(){
			@Override
			public Predicate toPredicate(Root<PeSystem> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PeSystem, ClinicalElementsOptions> clinicalJoin=root.join(PeSystem_.clinicalElementsOptions,JoinType.INNER);
				Predicate optPred=cb.equal(clinicalJoin.get(ClinicalElementsOptions_.clinicalElementsOptionsValue),"1");
				Predicate notNullPred=cb.isNotNull(root.get(PeSystem_.peSystemDeferredGwid));
				Predicate sysPred=root.get(PeSystem_.peSystemId).in(systemIds);
				return cb.and(optPred,notNullPred,sysPred);
			}
		};
	}		
	
	
	/**
	 * Get PE elements which are normal for given patient and encounter 
	 * 
	 * @param systemIds
	 * @return
	 */
	public static Specification<PatientClinicalElements> getNormalPEelemByEnc(final Integer encounterId,final Integer templateId){
		return new Specification<PatientClinicalElements>(){
			@Override
			public Predicate toPredicate(Root<PatientClinicalElements> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientClinicalElements, ClinicalElements> clinicalJoin=root.join(PatientClinicalElements_.clinicalElement,JoinType.INNER);
				Join<ClinicalElements,ClinicalElementTemplateMapping> tempJoin=clinicalJoin.join(ClinicalElements_.clinicalElementTemplateMapping,JoinType.INNER);
				Join<ClinicalElementTemplateMapping,PeElement> tempMapJoin=tempJoin.join(ClinicalElementTemplateMapping_.peElement,JoinType.INNER);
				Predicate gwidPred=cb.equal(clinicalJoin.get(ClinicalElements_.clinicalElementsDatatype),3);
				Predicate encPred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId);
				Predicate tempIdPred=cb.equal(tempJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid), templateId);
				Predicate typePred=cb.equal(tempJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingType),5);
				Predicate valuePred=cb.equal(root.get(PatientClinicalElements_.patientClinicalElementsValue),"false");
				return cb.and(gwidPred,encPred,tempIdPred,valuePred,typePred);
			}
		};
	}		
	
	
	
	
	
	
}
