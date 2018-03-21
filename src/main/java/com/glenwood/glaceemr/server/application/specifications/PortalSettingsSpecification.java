package com.glenwood.glaceemr.server.application.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.BillingConfigTable;
import com.glenwood.glaceemr.server.application.models.BillingConfigTable_;
import com.glenwood.glaceemr.server.application.models.Billinglookup;
import com.glenwood.glaceemr.server.application.models.Billinglookup_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.PatientPortalAlertConfig;
import com.glenwood.glaceemr.server.application.models.PatientPortalAlertConfig_;
import com.glenwood.glaceemr.server.application.models.InitialSettings;
import com.glenwood.glaceemr.server.application.models.InitialSettings_;
import com.glenwood.glaceemr.server.application.models.PatientPortalFeatureConfig;
import com.glenwood.glaceemr.server.application.models.PatientPortalFeatureConfig_;
import com.glenwood.glaceemr.server.application.models.PatientPortalMenuConfig;
import com.glenwood.glaceemr.server.application.models.PatientPortalMenuConfig_;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosTable_;

public class PortalSettingsSpecification {
	
	/**
	 * @return list of available Language options  
	 */	
	public static Specification<PatientPortalMenuConfig> getPortalMenuConfig(final boolean isActiveMenuItemList)
	   {
		   return new Specification<PatientPortalMenuConfig>() {

			@Override
			public Predicate toPredicate(Root<PatientPortalMenuConfig> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate menuPredicate=cq.where(cb.equal(root.get(PatientPortalMenuConfig_.patientPortalMenuConfigIsactive), true)).getRestriction();
				return menuPredicate;
			}
			   
		};
	   }
	
	/**
	 * @return list of available Language options  
	 */	
	public static Specification<PatientPortalFeatureConfig> getPortalFeatureConfig(final boolean isActiveFeatureItemList)
	   {
		   return new Specification<PatientPortalFeatureConfig>() {

			@Override
			public Predicate toPredicate(Root<PatientPortalFeatureConfig> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate featurePredicate=cq.where(cb.equal(root.get(PatientPortalFeatureConfig_.patientPortalFeatureConfigIsactive), isActiveFeatureItemList)).getRestriction();
				return featurePredicate;
			}
			   
		};
	   }

	
	/**
	 * @return list of available Language options  
	 */	
	public static Specification<InitialSettings> getPracticeDetails()
	   {
		   return new Specification<InitialSettings>() {

			@Override
			public Predicate toPredicate(Root<InitialSettings> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate practicePredicate=cq.where(cb.equal(root.get(InitialSettings_.initialSettingsOptionType), 4)).getRestriction();
				return practicePredicate;
			}
			   
		};
	   }
	
	/**
	 * @return list of available Language options  
	 */	
	public static Specification<InitialSettings> getSharedFolderPath()
	   {
		   return new Specification<InitialSettings>() {

			@Override
			public Predicate toPredicate(Root<InitialSettings> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate practicePredicate=cq.where(cb.equal(root.get(InitialSettings_.initialSettingsOptionType), 4)).getRestriction();
				Predicate sharedFolderPredicate = cq.where(cb.equal(root.get(InitialSettings_.initialSettingsOptionName),"Shared Folder Path")).getRestriction();
				
				return cb.and(practicePredicate,sharedFolderPredicate);
			}
			   
		};
	   }
	
	/**
	 * @return list of available Language options  
	 */	
	public static Specification<InitialSettings> getPracticeDetails(final String optionName)
	   {
		   return new Specification<InitialSettings>() {

			@Override
			public Predicate toPredicate(Root<InitialSettings> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate practicePredicate=cq.where(cb.equal(root.get(InitialSettings_.initialSettingsOptionType), 4),cb.equal(cb.upper(root.get(InitialSettings_.initialSettingsOptionName)), optionName.toUpperCase())).getRestriction();
				return practicePredicate;
			}
			   
		};
	   }
	
	/**
	 * @return list of available Language options  
	 */	
	public static Specification<PatientPortalAlertConfig> getPortalAlertCategory(final int portalAlertCategory)
	   {
		   return new Specification<PatientPortalAlertConfig>() {

			@Override
			public Predicate toPredicate(Root<PatientPortalAlertConfig> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate alertPredicate=cq.where(cb.equal(root.get(PatientPortalAlertConfig_.patient_portal_alert_config_id), portalAlertCategory)).getRestriction();
				return alertPredicate;
			}
			   
		};
	   }
	
	
	/**
	 * @return list of available Language options  
	 */	
	public static Specification<Billinglookup> getLanguageList()
	   {
		   return new Specification<Billinglookup>() {

			@Override
			public Predicate toPredicate(Root<Billinglookup> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate availableLanguageOptionsList=cq.where(cb.and(cb.equal(root.get(Billinglookup_.blookGroup), 253))).orderBy(cb.asc(root.get(Billinglookup_.blookName))).getRestriction();
				return availableLanguageOptionsList;
			}
			   
		};
	   }
	
	/**
	 * @return list of available Race options  
	 */	
	public static Specification<Billinglookup> getRaceList()
	   {
		   return new Specification<Billinglookup>() {

			@Override
			public Predicate toPredicate(Root<Billinglookup> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate availableRaceOptionsList=cq.where(cb.and(cb.equal(root.get(Billinglookup_.blookGroup), 250))).orderBy(cb.asc(root.get(Billinglookup_.blookName))).getRestriction();
				return availableRaceOptionsList;
			}
			   
		};
	   }
	
	
	/**
	 * @return list of available Ethnicity options  
	 */	
	public static Specification<Billinglookup> getEthnicityList()
	   {
		   return new Specification<Billinglookup>() {

			@Override
			public Predicate toPredicate(Root<Billinglookup> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate availableEthnicityOptionsList=cq.where(cb.and(cb.equal(root.get(Billinglookup_.blookGroup), 251))).orderBy(cb.asc(root.get(Billinglookup_.blookName))).getRestriction();
				return availableEthnicityOptionsList;
			}
			   
		};
	   }
	
	/**
	 * @return list of available states list 
	 */	
	public static Specification<BillingConfigTable> getStateList()
	   {
		   return new Specification<BillingConfigTable>() {

			@Override
			public Predicate toPredicate(Root<BillingConfigTable> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate availableEthnicityOptionsList=cq.where(cb.and(cb.equal(root.get(BillingConfigTable_.billingConfigTableLookupId), 5001)),
						cb.and(cb.equal(root.get(BillingConfigTable_.billingConfigTableIsActive), true),
								cb.notEqual(root.get(BillingConfigTable_.billingConfigTableLookupDesc), ""),
								cb.notEqual(root.get(BillingConfigTable_.billingConfigTableLookupDesc), " "))).orderBy(cb.asc(root.get(BillingConfigTable_.billingConfigTableLookupDesc))).getRestriction();
				return availableEthnicityOptionsList;
			}
			   
		};
	   }
	
	
	/**
	 * @return list of available patient Reminder Type options  
	 */	
	public static Specification<Billinglookup> getPatientReminderTypeList()
	   {
		   return new Specification<Billinglookup>() {

			@Override
			public Predicate toPredicate(Root<Billinglookup> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate availableReminderTypeOptionsList=cq.where(cb.and(cb.equal(root.get(Billinglookup_.blookGroup), 252))).orderBy(cb.asc(root.get(Billinglookup_.blookName))).getRestriction();
				return availableReminderTypeOptionsList;
			}
			   
		};
	   }
	
	
	
	/**
	 * @return list of available Marital Status options  
	 */	
	public static Specification<Billinglookup> getMaritalStatusList()
	   {
		   return new Specification<Billinglookup>() {

			@Override
			public Predicate toPredicate(Root<Billinglookup> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate availableMaritalStatusOptionsList=cq.where(cb.and(cb.equal(root.get(Billinglookup_.blookGroup), 14))).orderBy(cb.asc(root.get(Billinglookup_.blookName))).getRestriction();
				return availableMaritalStatusOptionsList;
			}
			   
		};
	   }
	
	
	
	/**
	 * @return list of available language options  
	 */	
	public static Specification<Billinglookup> getGenderTitleList()
	   {
		   return new Specification<Billinglookup>() {

			@Override
			public Predicate toPredicate(Root<Billinglookup> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate availableGenderTitleOptionsList=cq.where(cb.and(cb.equal(root.get(Billinglookup_.blookGroup), 21))).orderBy(cb.asc(root.get(Billinglookup_.blookName))).getRestriction();
				return availableGenderTitleOptionsList;
			}
			   
		};
	   }
	
	/**
	 * @return list of available language options  
	 */	
	public static Specification<Billinglookup> getPaymentMethod()
	   {
		   return new Specification<Billinglookup>() {

			@Override
			public Predicate toPredicate(Root<Billinglookup> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate availableGenderTitleOptionsList=cq.where(cb.and(cb.equal(root.get(Billinglookup_.blookGroup), 5))).orderBy(cb.asc(root.get(Billinglookup_.blookName))).getRestriction();
				return availableGenderTitleOptionsList;
			}
			   
		};
	   }
	
	
	/**
	 * @return list of available language options  
	 */	
	public static Specification<Billinglookup> getPayerType()
	   {
		   return new Specification<Billinglookup>() {

			@Override
			public Predicate toPredicate(Root<Billinglookup> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate availableGenderTitleOptionsList=cq.where(cb.and(cb.equal(root.get(Billinglookup_.blookGroup), 9))).orderBy(cb.asc(root.get(Billinglookup_.blookName))).getRestriction();
				return availableGenderTitleOptionsList;
			}
			   
		};
	   }
	
	
	/**
	 * @return list of available language options  
	 */	
	public static Specification<PosTable> getActivePosList()
	   {
		   return new Specification<PosTable>() {

			@Override
			public Predicate toPredicate(Root<PosTable> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate availableGenderTitleOptionsList=cq.where(cb.and(cb.equal(root.get(PosTable_.posTableIsActive), true))).orderBy(cb.desc(root.get(PosTable_.posTableFacilityComments))).getRestriction();
				return availableGenderTitleOptionsList;
			}
			   
		};
	   }

	/**
	 * @return list of providers
	 */	
	public static Specification<EmployeeProfile> getProvidersList()
	   {
		   return new Specification<EmployeeProfile>() {

			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Expression<Integer> exprToId=root.get(EmployeeProfile_.empProfileGroupid);
				Expression<String> empName=root.get(EmployeeProfile_.empProfileFullname);
				Predicate predicate=cq.where(cb.and(cb.isNotNull(root.get(EmployeeProfile_.empProfileEmpid)),cb.equal(root.get(EmployeeProfile_.empProfileIsActive), true),cb.not(cb.like(cb.lower(empName), "%demo%")), cb.not(cb.like(cb.lower(empName), "%test%")),exprToId.in(-1))).orderBy(cb.asc(root.get(EmployeeProfile_.empProfileFullname))).getRestriction();
					
				return predicate;
			}
			   
		};
	   }
	
	/**
	 * @return portal lab results configuration
	 */	
	public static Specification<InitialSettings> getPortalLabResultsConfig()
	   {
		   return new Specification<InitialSettings>() {

			@Override
			public Predicate toPredicate(Root<InitialSettings> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate practicePredicate=cq.where(cb.equal(root.get(InitialSettings_.initialSettingsOptionType), 2)).getRestriction();
				Predicate sharedFolderPredicate = cq.where(cb.equal(root.get(InitialSettings_.initialSettingsOptionName),"Lab result status to show in Patient Portal")).getRestriction();
				
				return cb.and(practicePredicate,sharedFolderPredicate);
			}
			   
		};
	   }
}
