package com.glenwood.glaceemr.server.application.services.chart.Examination;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class ExaminationTypedQuery {

	
	@PersistenceContext
	EntityManager entityManager;
	
	
	public Query getExaminationClinicalElemOptions(String gwids,Integer encounterId,Integer patientId,Integer patientAge,Integer templateId,short patientSex,boolean isAgeBased){
		
	/*	StringBuffer queryString=new StringBuffer();
		queryString.append(" SELECT koptionid as optionid,koptionvalue as optionvalue, koptionname as optionname,koptiongwid as optiongwid,");
		queryString.append(" koptiongwid as optiongwid, koptionorderby as optionorderby from ( ");
		queryString.append(" SELECT clinical_elements_options_id as koptionid,");
		queryString.append(" clinical_elements_options_value as koptionvalue,");
		queryString.append(" clinical_elements_options_name as koptionname, ");
		queryString.append(" clinical_elements_options_gwid as koptiongwid, ");
		queryString.append(" clinical_elements_options_order_by as koptionorderby ");
		queryString.append(" from clinical_element_template_mapping ");
		queryString.append(" inner join template_mapping_options on clinical_element_template_mapping_id=template_mapping_options_mapid ");
		queryString.append(" inner join clinical_elements_options on clinical_elements_options_gwid = clinical_element_template_mapping_gwid and ");
		queryString.append(" template_mapping_options_option_value::varchar = clinical_elements_options_value ");
		queryString.append(" and clinical_element_template_mapping_templateid= '"+templateId+"'");
		queryString.append(" and clinical_elements_options_gwid in ");
		queryString.append(" ('"+gwids.replaceAll(",", "','")+"')");
		queryString.append(" and clinical_elements_options_gender in (0, "+patientSex+")");
		if (isAgeBased){
		if(patientAge!=-1){
			queryString.append(" left join clinical_elements_condition on clinical_elements_options_id  = clinical_elements_condition_option_id ");
			queryString.append(" where ((clinical_elements_condition_startage is null and clinical_elements_condition_endage is null) ");
			queryString.append(" or (clinical_elements_condition_startage < "+patientAge+" and clinical_elements_condition_endage >="+patientAge+"))");
		}}
		queryString.append(" union ");
		queryString.append(" SELECT distinct ");
		queryString.append(" clinical_elements_options_id as koptionid, ");
		queryString.append(" patient_clinical_elements_value as koptionvalue, ");
		queryString.append(" clinical_elements_options_name as koptionname, ");
		queryString.append(" patient_clinical_elements_gwid as koptiongwid, ");
		queryString.append(" clinical_elements_options_order_by as koptionorderby ");
		queryString.append(" from patient_clinical_elements ");
		queryString.append(" inner join clinical_elements_options on patient_clinical_elements_gwid=clinical_elements_options_gwid ");
		queryString.append(" and patient_clinical_elements_value = clinical_elements_options_value ");
		queryString.append(" where patient_clinical_elements_gwid in ");
		queryString.append(" ('"+gwids.replaceAll(",", "','")+"')");
		queryString.append(" and patient_clinical_elements_patientid="+patientId+"");
		queryString.append(" and patient_clinical_elements_encounterid="+encounterId+" ");
		queryString.append(" )k ");
		queryString.append(" order by optiongwid,optionorderby asc,");
		queryString.append(" optionvalue asc");*/
		
		String queryString="select * from clinical_element_template_mapping T JOIN t.templateMappingOptions P";
		
		return entityManager.createQuery(queryString.toString());
	}
}
