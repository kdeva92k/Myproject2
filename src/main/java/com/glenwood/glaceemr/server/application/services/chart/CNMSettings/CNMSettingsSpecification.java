package com.glenwood.glaceemr.server.application.services.chart.CNMSettings;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.CnmSettings;
import com.glenwood.glaceemr.server.application.models.CnmSettings_;

public class CNMSettingsSpecification {

	
	/**
	 * Get CNM settings entry based on name and Id 
	 * 
	 * @param dataListId
	 * @return
	 */
	public static Specification<CnmSettings> getCNMSettingsByIdAndName(final Integer id,final String name){
		return new Specification<CnmSettings>(){
			@Override
			public Predicate toPredicate(Root<CnmSettings> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.get(CnmSettings_.cnmSettingsId),id),cb.equal(root.get(CnmSettings_.cnmSettingsName),name));
			}
		};
	}
	
	/**
	 * Get CNM settings entry based on Id 
	 * 
	 * @param dataListId
	 * @return
	 */
	public static Specification<CnmSettings> getCNMSettingsById(final Integer id){
		return new Specification<CnmSettings>(){
			@Override
			public Predicate toPredicate(Root<CnmSettings> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(CnmSettings_.cnmSettingsId),id);
			}
		};
	}
	
	
	/**
	 * Get CNM settings entry based on name  
	 * 
	 * @param dataListId
	 * @return
	 */
	public static Specification<CnmSettings> getCNMSettingsByName(final String name){
		return new Specification<CnmSettings>(){
			@Override
			public Predicate toPredicate(Root<CnmSettings> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(CnmSettings_.cnmSettingsName),name);
			}
		};
	}
	
	
}
