package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.MacraConfiguration;
import com.glenwood.glaceemr.server.application.models.MacraConfiguration_;
import com.glenwood.glaceemr.server.application.models.MacraProviderConfiguration;
import com.glenwood.glaceemr.server.application.models.MacraProviderConfiguration_;
import com.glenwood.glaceemr.server.application.models.MuAttestationObjectives;
import com.glenwood.glaceemr.server.application.models.MuAttestationObjectives_;
import com.glenwood.glaceemr.server.application.models.QualityMeasuresProviderMapping;
import com.glenwood.glaceemr.server.application.models.QualityMeasuresProviderMapping_;


public class QPPConfigurationSpecification {
	
	public static Specification<MacraProviderConfiguration> getProviderData(final Integer providerId,final Integer year){
		return new Specification<MacraProviderConfiguration>() {

			@Override
			public Predicate toPredicate(Root<MacraProviderConfiguration> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByProviderId = cb.equal(
						root.get(MacraProviderConfiguration_.macraProviderConfigurationProviderId),providerId);
				Predicate predicateByyear = cb.equal(
						root.get(MacraProviderConfiguration_.macraProviderConfigurationReportingYear),year);
				Predicate providerDetails=cb.and(predicateByProviderId,predicateByyear);
				return providerDetails;
			}
		};
		
	}
	public static Specification<EmployeeProfile> getProviderId(final String provider){
		return new Specification<EmployeeProfile>() {

			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByProviderName = cb.equal(
						root.get(EmployeeProfile_.empProfileFullname),provider);
				Predicate providerId=cb.and(predicateByProviderName);

				return providerId;
			}
		};
		
	}
	
	public static Specification<QualityMeasuresProviderMapping> getMeasureIds(final Integer providerId){
		return new Specification<QualityMeasuresProviderMapping>() {

			@Override
			public Predicate toPredicate(
					Root<QualityMeasuresProviderMapping> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByproviderId=cb.equal(root.get(QualityMeasuresProviderMapping_.qualityMeasuresProviderMappingProviderId),providerId);
				Predicate indiMeasureID=cb.and(predicateByproviderId);
				return indiMeasureID;
			}
		};
		
	}
	public static Specification<MacraProviderConfiguration> getProviderObj(final Integer providerId,final Integer year){
		return new Specification<MacraProviderConfiguration>() {

			@Override
			public Predicate toPredicate(Root<MacraProviderConfiguration> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByproviderId=cb.equal(root.get(MacraProviderConfiguration_.macraProviderConfigurationProviderId),providerId);
				Predicate predicateByYear=cb.equal(root.get(MacraProviderConfiguration_.macraProviderConfigurationReportingYear),year);
				Predicate providerObj=cb.and(predicateByproviderId,predicateByYear);
				return providerObj;
			}
		};
		
	}
	
	public static Specification<MacraConfiguration> getConfObj(final Integer programYear){
		return new Specification<MacraConfiguration>() {

			@Override
			public Predicate toPredicate(Root<MacraConfiguration> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByProgramYear=cb.equal(root.get(MacraConfiguration_.macraConfigurationYear),programYear);
				Predicate confObj=cb.and(predicateByProgramYear);
				return confObj;
			}
		};
		
	}

	public static Specification<MuAttestationObjectives> getObjectiveDetails(final int reportingYear) {
		
		return new Specification<MuAttestationObjectives>() {

			@Override
			public Predicate toPredicate(Root<MuAttestationObjectives> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicateByProgramYear=cb.equal(root.get(MuAttestationObjectives_.objectiveReportingYear), reportingYear);
				Predicate confObj=cb.and(predicateByProgramYear);
				return confObj;
			}
		};
		
	}
	
}
