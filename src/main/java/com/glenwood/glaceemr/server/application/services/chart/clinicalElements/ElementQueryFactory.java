package com.glenwood.glaceemr.server.application.services.chart.clinicalElements;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.glenwood.glaceemr.server.application.models.ClinicalElementTemplateMapping;
import com.glenwood.glaceemr.server.application.models.ClinicalElementTemplateMapping_;
import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalElements_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalHistory;
import com.glenwood.glaceemr.server.application.models.PatientClinicalHistory_;

@Component
public class ElementQueryFactory {

	@Autowired
	EntityManager em;
	
	
	public List<ClinicalElementBean> getClinicalElementsFirst(int patientGender, String gwPattern,int patientId,int encounterId, int templateId, int ageinDay, int tabType,Boolean isAgeBased,String leafCreatedDate){
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<ClinicalElementBean> query= builder.createQuery(ClinicalElementBean.class);
		Root<ClinicalElements> root= query.from(ClinicalElements.class);
		Join<ClinicalElements, ClinicalElementTemplateMapping> mapJoin= root.join(ClinicalElements_.clinicalElementTemplateMapping, JoinType.INNER);
		
		Predicate templatePred= mapJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTemplateid).in(templateId);
		Predicate typePred= mapJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingType).in(7);
		Predicate genderPred= root.get(ClinicalElements_.clinicalElementsGender).in(0, patientGender); 
		Predicate finalPred= null;
		finalPred= builder.and(templatePred, typePred, genderPred);
		
		if(leafCreatedDate != null){
			Predicate createdPred= builder.or(builder.lessThanOrEqualTo(mapJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTimestamp), Timestamp.valueOf(leafCreatedDate)),
					   					       builder.isNull(mapJoin.get(ClinicalElementTemplateMapping_.clinicalElementTemplateMappingTimestamp)));
			finalPred= builder.and(finalPred, createdPred);
		}
		if(isAgeBased == true){
			finalPred= builder.and(finalPred, builder.or(builder.and(
											   builder.equal(root.get(ClinicalElements_.clinicalElementsStartage), -1), 
											    builder.equal(root.get(ClinicalElements_.clinicalElementsEndage), -1)),
											  builder.and(
											    builder.lessThan(root.get(ClinicalElements_.clinicalElementsEndage), ageinDay),
											     builder.greaterThanOrEqualTo(root.get(ClinicalElements_.clinicalElementsEndage), ageinDay))));
		}
		
//		mapJoin.on(finalPred);
		query.distinct(true).select(builder.construct(ClinicalElementBean.class,
					  builder.coalesce(root.get(ClinicalElements_.clinicalElementsName),""),
					   builder.coalesce(root.get(ClinicalElements_.clinicalElementsGwid),"-1"),
						builder.coalesce(root.get(ClinicalElements_.clinicalElementsNotes),""),
						 builder.coalesce(root.get(ClinicalElements_.clinicalElementsDatatype),-1),
						  builder.coalesce(root.get(ClinicalElements_.clinicalElementsCptcode),""),
						   builder.coalesce(root.get(ClinicalElements_.clinicalElementsIcd9code),""),
						    builder.coalesce(root.get(ClinicalElements_.clinicalElementsSnomed),""),
						     builder.coalesce(root.get(ClinicalElements_.clinicalElementsIsactive),false),
						      builder.coalesce(root.get(ClinicalElements_.clinicalElementsIsglobal),false),
						       builder.coalesce(root.get(ClinicalElements_.clinicalElementsIshistory),false),
						        builder.coalesce(root.get(ClinicalElements_.clinicalElementsIsepisode),false),
						         builder.coalesce(root.get(ClinicalElements_.clinicalElementsTextDimension),""),
						          builder.coalesce(root.get(ClinicalElements_.clinicalElementsGender),0),
						           builder.coalesce(root.get(ClinicalElements_.clinicalElementsIsselect),-1)));
		
		query.where(finalPred);
		return em.createQuery(query).getResultList();		
	}
	
	public List<ClinicalElementBean> getClinicalElementsSecond(int patientGender, String gwPattern,int patientId,int encounterId, int templateId, int ageinDay, int tabType,Boolean isAgeBased,String leafCreatedDate, String clientId){
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<ClinicalElementBean> query= builder.createQuery(ClinicalElementBean.class);
		Root<ClinicalElements> root= query.from(ClinicalElements.class);
		
		Predicate agePred= builder.or(builder.and(
										builder.equal(root.get(ClinicalElements_.clinicalElementsStartage), -1), 
										builder.equal(root.get(ClinicalElements_.clinicalElementsEndage), -1)),
									  builder.and(
										builder.lessThan(root.get(ClinicalElements_.clinicalElementsEndage), ageinDay),
										builder.greaterThanOrEqualTo(root.get(ClinicalElements_.clinicalElementsEndage), ageinDay)));
		
		Predicate genderPred= root.get(ClinicalElements_.clinicalElementsGender).in(0, patientGender);
		Predicate gwIdPred= builder.or(builder.like(root.get(ClinicalElements_.clinicalElementsGwid), clientId+gwPattern+"%"),
				   builder.like(root.get(ClinicalElements_.clinicalElementsGwid), "000"+gwPattern+"%"));
		
				
		if("-1".equals(encounterId)){
			Join<ClinicalElements, PatientClinicalHistory> clinicalJoin= root.join(ClinicalElements_.patientClinicalHistory, JoinType.INNER);
			Predicate patientPred= builder.equal(clinicalJoin.get(PatientClinicalHistory_.patientClinicalHistoryPatientid), patientId);			
			clinicalJoin.on(patientPred);
		}else{
			Join<ClinicalElements, PatientClinicalElements> clinicalJoin= root.join(ClinicalElements_.patientClinicalElements, JoinType.INNER);
			Predicate[] pred= new Predicate[]{
				builder.equal(clinicalJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid), patientId),
				builder.equal(clinicalJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId)
			};			
			clinicalJoin.on(pred);
		}
		Predicate finalPred= null;
		if(isAgeBased== true)
			finalPred= builder.and(genderPred, gwIdPred, agePred);
		else
			finalPred= builder.and(genderPred, gwIdPred);		
		
		query.distinct(true).select(builder.construct(ClinicalElementBean.class,
					  builder.coalesce(root.get(ClinicalElements_.clinicalElementsName),""),
					   builder.coalesce(root.get(ClinicalElements_.clinicalElementsGwid),"-1"),
						builder.coalesce(root.get(ClinicalElements_.clinicalElementsNotes),""),
						 builder.coalesce(root.get(ClinicalElements_.clinicalElementsDatatype),-1),
						  builder.coalesce(root.get(ClinicalElements_.clinicalElementsCptcode),""),
						   builder.coalesce(root.get(ClinicalElements_.clinicalElementsIcd9code),""),
						    builder.coalesce(root.get(ClinicalElements_.clinicalElementsSnomed),""),
						     builder.coalesce(root.get(ClinicalElements_.clinicalElementsIsactive),false),
						      builder.coalesce(root.get(ClinicalElements_.clinicalElementsIsglobal),false),
						       builder.coalesce(root.get(ClinicalElements_.clinicalElementsIshistory),false),
						        builder.coalesce(root.get(ClinicalElements_.clinicalElementsIsepisode),false),
						         builder.coalesce(root.get(ClinicalElements_.clinicalElementsTextDimension),""),
						          builder.coalesce(root.get(ClinicalElements_.clinicalElementsGender),0),
						           builder.coalesce(root.get(ClinicalElements_.clinicalElementsIsselect),-1)));
		
		query.where(finalPred);
		return em.createQuery(query).getResultList();
	}

	public List<Object[]> getPatientClinicalElementandHistory(
			Integer patientId, Integer encounterId, Integer tabType, String clientId, String gwPattern) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<ClinicalElements> root= query.from(ClinicalElements.class);
		Join<ClinicalElements, PatientClinicalElements> clinicalJoin= root.join(ClinicalElements_.patientClinicalElements, JoinType.INNER);
		clinicalJoin.on(builder.or(builder.like(root.get(ClinicalElements_.clinicalElementsGwid), clientId+gwPattern+"%"),
								    builder.like(root.get(ClinicalElements_.clinicalElementsGwid), "000"+gwPattern+"%")));
		
		query.multiselect(clinicalJoin.get(PatientClinicalElements_.patientClinicalElementsId).alias("patientclinicalelementid"),
						   clinicalJoin.get(PatientClinicalElements_.patientClinicalElementsGwid).alias("patientclinicalelementgwid"),
							clinicalJoin.get(PatientClinicalElements_.patientClinicalElementsValue).alias("patientclinicalelementvalue"),
							 root.get(ClinicalElements_.clinicalElementsDatatype).alias("clinicalelementdatatype"));
		query.where(builder.equal(clinicalJoin.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId),
					 builder.equal(clinicalJoin.get(PatientClinicalElements_.patientClinicalElementsPatientid), patientId),
					  builder.equal(root.get(ClinicalElements_.clinicalElementsIshistory), false));
		
		return em.createQuery(query).getResultList();
	} 
	
}