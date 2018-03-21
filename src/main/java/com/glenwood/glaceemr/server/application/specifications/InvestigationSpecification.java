package com.glenwood.glaceemr.server.application.specifications;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.BodySite;
import com.glenwood.glaceemr.server.application.models.BodySite_;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.Cpt;
import com.glenwood.glaceemr.server.application.models.Cpt_;
import com.glenwood.glaceemr.server.application.models.CvxVaccineGroupMapping;
import com.glenwood.glaceemr.server.application.models.CvxVaccineGroupMapping_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.FileDetails;
import com.glenwood.glaceemr.server.application.models.FileDetails_;
import com.glenwood.glaceemr.server.application.models.ChartStatus;
import com.glenwood.glaceemr.server.application.models.ChartStatus_;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTest;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTest_;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTestmapping;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTestmapping_;
import com.glenwood.glaceemr.server.application.models.Hl7ResultInbox;
import com.glenwood.glaceemr.server.application.models.Hl7ResultInbox_;
import com.glenwood.glaceemr.server.application.models.ImmunisationSite;
import com.glenwood.glaceemr.server.application.models.ImmunisationSite_;
import com.glenwood.glaceemr.server.application.models.LabAlertforwardstatus;
import com.glenwood.glaceemr.server.application.models.LabAlertforwardstatus_;
import com.glenwood.glaceemr.server.application.models.LabDescpParameters;
import com.glenwood.glaceemr.server.application.models.LabDescpParameters_;
import com.glenwood.glaceemr.server.application.models.LabDescription;
import com.glenwood.glaceemr.server.application.models.LabDescription_;
import com.glenwood.glaceemr.server.application.models.LabEntries;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter_;
import com.glenwood.glaceemr.server.application.models.LabEntries_;
import com.glenwood.glaceemr.server.application.models.LabFreqorder;
import com.glenwood.glaceemr.server.application.models.LabFreqorder_;
import com.glenwood.glaceemr.server.application.models.LabGroups;
import com.glenwood.glaceemr.server.application.models.LabGroups_;
import com.glenwood.glaceemr.server.application.models.LabIncludePrevious;
import com.glenwood.glaceemr.server.application.models.LabIncludePrevious_;
import com.glenwood.glaceemr.server.application.models.LabParameterCode;
import com.glenwood.glaceemr.server.application.models.LabParameterCode_;
import com.glenwood.glaceemr.server.application.models.LabParameters;
import com.glenwood.glaceemr.server.application.models.LabParameters_;
import com.glenwood.glaceemr.server.application.models.LeafPatient;
import com.glenwood.glaceemr.server.application.models.LeafPatient_;
import com.glenwood.glaceemr.server.application.models.OrdersetCategorylist;
import com.glenwood.glaceemr.server.application.models.OrdersetCategorylist_;
import com.glenwood.glaceemr.server.application.models.OrdersetList;
import com.glenwood.glaceemr.server.application.models.OrdersetList_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PatientVisEntries;
import com.glenwood.glaceemr.server.application.models.PatientVisEntries_;
import com.glenwood.glaceemr.server.application.models.Specimen;
import com.glenwood.glaceemr.server.application.models.Specimen_;
import com.glenwood.glaceemr.server.application.models.VaccinationConsentForm;
import com.glenwood.glaceemr.server.application.models.VaccinationConsentForm_;
import com.glenwood.glaceemr.server.application.models.VaccineOrder;
import com.glenwood.glaceemr.server.application.models.VaccineOrderDetails;
import com.glenwood.glaceemr.server.application.models.VaccineOrderDetails_;
import com.glenwood.glaceemr.server.application.models.VaccineOrder_;
import com.glenwood.glaceemr.server.application.models.Vis;
import com.glenwood.glaceemr.server.application.models.VisFileMapping;
import com.glenwood.glaceemr.server.application.models.VisFileMapping_;
import com.glenwood.glaceemr.server.application.models.Vis_;

/**
 * @author yasodha
 * 
 * InvestigationSpecification contains all the predicates (rules)
 * required to get the lab data for investigation module.
 */
public class InvestigationSpecification {
	
	/**
	 * Specification to check order set id
	 * @return
	 */
	public static Specification<LabDescription> checkOrderSetId(final ArrayList<Integer> list) {
		return new Specification<LabDescription>() {

			@Override
			public Predicate toPredicate(Root<LabDescription> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate checkId = root.get(LabDescription_.labDescriptionTestid).in(list);
				return checkId;
			}
		};
	}
	
	/**
	 * Specification to check lab is active or not
	 * @return
	 */
	public static Specification<LabDescription> labIsActive() {
		return new Specification<LabDescription>() {

			@Override
			public Predicate toPredicate(Root<LabDescription> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				root.join(LabDescription_.labGroups, JoinType.INNER);
				Predicate checkId = cb.equal(root.get(LabDescription_.labDescriptionIsactive), true);
				return checkId;
			}
		};
	}
	
	/**
	 * Specification to get associated list
	 * @param setId
	 * @return
	 */
	public static Specification<OrdersetCategorylist> checkOrderSetId(final Integer setId) {
		return new Specification<OrdersetCategorylist>() {

			@Override
			public Predicate toPredicate(Root<OrdersetCategorylist> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate checkId = cb.equal(root.get(OrdersetCategorylist_.setid), setId);
				Predicate checkCategory = cb.equal(root.get(OrdersetCategorylist_.categoryId), 101);
				return cb.and(checkCategory, checkId);
			}
		};
	}
	
	/**
	 * Specification to get order set list which is active
	 * @param testId
	 * @return
	 */
	public static  Specification<OrdersetList> getOrderSet() {
		return new Specification<OrdersetList>() {

			@Override
			public Predicate toPredicate(Root<OrdersetList> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate checkIsActive = cb.equal(root.get(OrdersetList_.isactive), true);
				Predicate checkType = cb.equal(root.get(OrdersetList_.setType), 1);
				return cb.and(checkIsActive, checkType);
			}
		};
	}
	
	/**
	 * Specification to get new lab parameters
	 * @param testId
	 * @return
	 */
	public static Specification<LabDescription> getNewLabParams(final Integer testId) {
		return new Specification<LabDescription>() {

			@Override
			public Predicate toPredicate(Root<LabDescription> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<LabDescription, LabDescpParameters> descJoin = root.join(LabDescription_.labDescParams, JoinType.INNER);
				Join<LabDescpParameters, LabParameters> paramsJoin = descJoin.join(LabDescpParameters_.labParametersTable, JoinType.INNER);
				Predicate checkIsActive = cb.equal(paramsJoin.get(LabParameters_.labParametersIsactive), true);
				Predicate descIsActive = cb.equal(descJoin.get(LabDescpParameters_.labDescpParameterIsactive), true);
				Predicate checkId = cb.equal(root.get(LabDescription_.labDescriptionTestid), testId);
				query.orderBy(cb.asc(descJoin.get(LabDescpParameters_.labDescpParameterSortorder)));
				return cb.and(checkIsActive, descIsActive, checkId);
			}
		};
	}
	
	/**
	 * Specification to check encounterId
	 * @param testId
	 * @return
	 */
	public static Specification<Encounter> checkEncounterId(final Integer encounterId) {
		return new Specification<Encounter>() {

			@Override
			public Predicate toPredicate(Root<Encounter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				root.join(Encounter_.patientEncounterType,JoinType.INNER);
				Predicate checkId = cb.equal(root.get(Encounter_.encounterId), encounterId);
				return checkId;
			}
		};
	}
	
	/**
	 * Specification to check cpt code
	 * @param testId
	 * @return
	 */
	public static Specification<Cpt> checkCPTId(final String cptCode) {
		return new Specification<Cpt>() {

			@Override
			public Predicate toPredicate(Root<Cpt> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate checkId = cb.like(cb.lower(root.get(Cpt_.cptCptcode)), cptCode.toLowerCase());
				return checkId;
			}
		};
	}
	
	/**
	 * Specification to check test id of new lab
	 * @param testId
	 * @return
	 */
	public static Specification<LabDescription> checkTestId(final Integer testId) {
		return new Specification<LabDescription>() {

			@Override
			public Predicate toPredicate(Root<LabDescription> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate checkId = root.get(LabDescription_.labDescriptionTestid).in(testId);
				return checkId;
			}
		};
	}	
	
	/**
	 * Specification to get test details based on type
	 * @param test category type
	 * @return
	 */
	public static Specification<LabDescription> labByCategoryId(final Integer categoryId) {
		return new Specification<LabDescription>() {

			@Override
			public Predicate toPredicate(Root<LabDescription> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate labByCategoryId = cb.equal(cb.coalesce(root.get(LabDescription_.labDescriptionTestcategoryType),4), categoryId);
				return labByCategoryId;
			}
		};
	}
	
	/**
	 * Specification to get results to be reviewed list
	 * @param encounterId
	 * @param chartId
	 * @return
	 */
	public static Specification<LabEntries> labResults(final Integer chartId) {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				root.join(LabEntries_.empProfile,JoinType.LEFT);
				Join<LabEntries, Encounter> encounterJoin = root.join(LabEntries_.encounter,JoinType.LEFT);
				root.join(LabEntries_.labGroups,JoinType.INNER);
				root.fetch(LabEntries_.empProfile);
				root.fetch(LabEntries_.encounter);
				root.fetch(LabEntries_.labGroups);
				Predicate checkstatus = root.get(LabEntries_.labEntriesTestStatus).in(3);
				Predicate checkGroupId = cb.not(root.get(LabEntries_.labEntriesGroupid).in(36,5));
				Predicate checkChartId = cb.equal(encounterJoin.get(Encounter_.encounterChartid), chartId);				
				query.orderBy(cb.asc(root.get(LabEntries_.labEntriesGroupid)),cb.desc(root.get(LabEntries_.labEntriesOrdOn)));
				return cb.and(checkstatus, checkGroupId, checkChartId);
			}
		};
	}

	/**
	 * Specification to get pending labs
	 * @param encounterId
	 * @param chartId
	 * @return
	 */
	public static Specification<LabEntries> pendingLabs(final Integer encounterId,final Integer chartId) {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				root.join(LabEntries_.empProfile,JoinType.LEFT);
				Join<LabEntries, Encounter> encounterJoin = root.join(LabEntries_.encounter,JoinType.INNER);
				encounterJoin.on(cb.notEqual(root.get(LabEntries_.labEntriesEncounterId), encounterId));
				root.join(LabEntries_.labGroups,JoinType.INNER);
				root.fetch(LabEntries_.empProfile);
				root.fetch(LabEntries_.encounter);
				root.fetch(LabEntries_.labGroups);
				Predicate checkstatus = cb.equal(root.get(LabEntries_.labEntriesTestStatus), 1);
				Predicate checkChartId = cb.equal(encounterJoin.get(Encounter_.encounterChartid), chartId);				
				query.orderBy(cb.asc(root.get(LabEntries_.labEntriesGroupid)),cb.desc(root.get(LabEntries_.labEntriesOrdOn)));
				return cb.and(checkstatus, checkChartId);
			}
		};
	}
	
	/**
	 * Specification to get test details
	 * @param testId
	 * @return
	 */
	public static Specification<LabDescription> labByTestId(final Integer testId) {
		return new Specification<LabDescription>() {

			@Override
			public Predicate toPredicate(Root<LabDescription> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate vis = cb.equal(root.get(LabDescription_.labDescriptionTestid), testId);
				return vis;
			}
		};
	}
	
	/**
	 * Specification to get the list of labs having testId
	 * @param testId
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> testIds(final ArrayList<Integer> testIds)
	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate testIdsPred = root.get(LabEntries_.labEntriesTestId).in(testIds);
				return testIdsPred;
			}
		};
	}
	
	/**
	 * Specification to check parameter is active and compare testdetailId
	 * @return
	 */
	public static Specification<LabEntriesParameter> checkParameterAndTestDetailId(final Integer testDetailId) {
		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<LabEntriesParameter, LabEntries> paramJoin = root.join(LabEntriesParameter_.labEntriesTable,JoinType.INNER);
				Predicate checkId = cb.equal(paramJoin.get(LabEntries_.labEntriesTestdetailId),testDetailId);
				Predicate checkParam = cb.equal(root.get(LabEntriesParameter_.labEntriesParameterIsactive), true);
				Predicate finalCheck = cb.and(checkId,checkParam);
				return finalCheck;
			}
		};
	}	

	/**
	 * Specification to check vaccines consent
	 * @return
	 */
	public static Specification<VaccinationConsentForm> consentByChartId(final Integer chartId) {
		return new Specification<VaccinationConsentForm>() {

			@Override
			public Predicate toPredicate(Root<VaccinationConsentForm> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<VaccinationConsentForm, LabEntries> consentChart = root.join(VaccinationConsentForm_.labEntries,JoinType.INNER);
				Predicate consent = cb.equal(consentChart.get(LabEntries_.chart).get(Chart_.chartId),chartId);
				return consent;
			}	
		};
	}

	/**
	 * Specification to check vaccines expiry date
	 * @return
	 */
	public static Specification<VaccineOrderDetails> checkExpiryDate() {
		return new Specification<VaccineOrderDetails>() {

			@Override
			public Predicate toPredicate(Root<VaccineOrderDetails> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate expiryDate = cb.greaterThan(root.get(VaccineOrderDetails_.vaccineOrderDetailsExpiry), new Date());
				return expiryDate;
			}	
		};
	}

	/**
	 * Specification to check whether a vaccine is active or not
	 * @return
	 */
	public static Specification<VaccineOrderDetails> checkIsActive() {
		return new Specification<VaccineOrderDetails>() {

			@Override
			public Predicate toPredicate(Root<VaccineOrderDetails> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate isActive = cb.equal(root.get(VaccineOrderDetails_.vaccineOrderDetailsIsactive), true);
				return isActive;
			}	
		};
	}

	/**
	 * Method to check CHDP state for a vaccine
	 * @param isCHDP
	 * @return
	 */
	public static Specification<VaccineOrderDetails> chdpCriteria(final String isCHDP) {
		return new Specification<VaccineOrderDetails>() {

			@Override
			public Predicate toPredicate(Root<VaccineOrderDetails> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate chdp = cb.equal(root.get(VaccineOrderDetails_.vaccineOrderDetailsIschdp), isCHDP);
				System.out.println("" + query.toString());
				return chdp;
			}	
		};
	}

	/**
	 * Specification to compare vaccine test id with
	 * @param testId
	 * @return
	 */
	public static Specification<VaccineOrderDetails> checkVaccineId(final Integer testId) {
		return new Specification<VaccineOrderDetails>() {

			@Override
			public Predicate toPredicate(Root<VaccineOrderDetails> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate vaccineId = cb.equal(root.get(VaccineOrderDetails_.vaccineOrderDetailsVaccineId), testId);
				return vaccineId;
			}	
		};
	}

	/**
	 * Method to get frequently ordered labs based on 
	 * @param groupId
	 * @param loginId
	 * @return
	 */
	public static Specification<LabDescription> getFrequentLabs(final Integer groupId, final Integer loginId) {
		return new Specification<LabDescription>() {

			@Override
			public Predicate toPredicate(Root<LabDescription> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {	
				Predicate checkGroupId = cb.equal(root.get(LabDescription_.labDescriptionGroupid), groupId);
				Predicate checkisActive = cb.equal(root.get(LabDescription_.labDescriptionIsactive), true);
				Join<LabDescription, LabFreqorder> join = root.join(LabDescription_.labFreqOrder, JoinType.INNER);
				Predicate userIdPredicate = join.get(LabFreqorder_.labFreqorderUserid).in(-1, loginId);
				Join<LabDescription, Hl7ExternalTestmapping> rootjoin = root.join(LabDescription_.hl7ExternalTestmappingTable, JoinType.LEFT);
				rootjoin.join(Hl7ExternalTestmapping_.hl7ExternalTestTable, JoinType.LEFT);
				query.distinct(true);
				query.orderBy(cb.asc(root.get(LabDescription_.labDescriptionTestDesc)));
				return cb.and(checkGroupId,checkisActive,userIdPredicate);
			}	
		};
	}

	/**
	 * Specification to get the site information
	 * @return Predicate
	 */
	public static Specification<ImmunisationSite> getSiteDetails() {
		return new Specification<ImmunisationSite>() {

			@Override
			public Predicate toPredicate(Root<ImmunisationSite> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate isActive = cb.equal(root.get(ImmunisationSite_.immunisationSiteIsactive),true);
				query.orderBy(cb.asc(root.get(ImmunisationSite_.immunisationSiteSortorder)));
				return isActive;
			}	
		};
	}

	/**
	 * Specification to check whether a vaccine got consent or not based on its
	 * @param testDetailId
	 * @return consent Predicate
	 */
	public static Specification<VaccinationConsentForm> checkForConsent(final Integer testDetailId) {
		return new Specification<VaccinationConsentForm>() {

			@Override
			public Predicate toPredicate(Root<VaccinationConsentForm> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate consent = cb.equal(root.get(VaccinationConsentForm_.vaccinationConsentFormTestdetailId),testDetailId);
				return consent;
			}	
		};
	}

	/**
	 * Specification to get list of vaccines based on
	 * @param encounterId which are ordered but consent is not availed
	 * @return vaccineRule
	 */
	public static Specification<LabEntries> vaccineByEncounter(final Integer encounterId) {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<LabEntries, LabDescription> descriptionJoin = root.join(LabEntries_.labDescriptionTable,JoinType.INNER);
				Join<LabEntries, Encounter> encounterFetch = root.join(LabEntries_.encounter,JoinType.INNER);
				Join<Encounter, Chart> chartFetch = encounterFetch.join(Encounter_.chart,JoinType.INNER);
				Join<LabEntries, VaccinationConsentForm> consentJoin = root.join(LabEntries_.vaccinationConsentForm,JoinType.LEFT);
				chartFetch.join(Chart_.patientRegistrationTable,JoinType.INNER);
				Predicate checkGroup = cb.equal(descriptionJoin.get(LabDescription_.labDescriptionGroupid), 36);
				Predicate checkStatus = root.get(LabEntries_.labEntriesTestStatus).in(1,6);
				Predicate checkEncounterId = cb.equal(root.get(LabEntries_.labEntriesEncounterId), encounterId);
				Predicate checkConsent = cb.isNull(consentJoin.get(VaccinationConsentForm_.vaccinationConsentFormTestdetailId));
				Predicate vaccinesRule = cb.and(checkGroup,checkStatus,checkEncounterId,checkConsent);
				return vaccinesRule;
			}	
		};
	}
	/**
	 * Method to get the rule for checking test status
	 * @return predicate value LabsByTestStatus
	 */
	public static Specification<LabEntries> testDetailsByTestId(final Integer testDetailId) {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<LabEntries, Encounter> encounterJoin = root.join(LabEntries_.encounter,JoinType.LEFT);
				Join<Encounter, LeafPatient> leafJoin = encounterJoin.join(Encounter_.leafPatient, JoinType.LEFT) ;
				leafJoin.on(cb.equal(leafJoin.get(LeafPatient_.leafPatientId),-1));
				root.join(LabEntries_.labDescriptionTable,JoinType.LEFT);
				root.join(LabEntries_.specimen,JoinType.LEFT);
				root.join(LabEntries_.empProfile,JoinType.LEFT);
				if ( Long.class != query.getResultType() ) {
					root.fetch(LabEntries_.labDescriptionTable,JoinType.LEFT);
					root.fetch(LabEntries_.specimen,JoinType.LEFT);
					root.fetch(LabEntries_.empProfile,JoinType.LEFT);
				}				
				Predicate testDetails = cb.equal(root.get(LabEntries_.labEntriesTestdetailId), testDetailId);
				return testDetails;
			}
		};
	}

	/**
	 * Method to get the rule for checking test status
	 * @return predicate value LabsByTestStatus
	 */
	public static Specification<LabEntries> checkTestStatus() {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate labsByTestStatus = cb.not((root.get(LabEntries_.labEntriesTestStatus).in(2,7)));
				Predicate checkStatus = cb.lessThan(root.get(LabEntries_.labEntriesTestStatus), 9);
				Predicate status = cb.and(checkStatus, labsByTestStatus);
				return status;
			}
		};
	}

	public static Specification<PatientVisEntries> visByTestDetailId(final Integer testDetailId) {
		return new Specification<PatientVisEntries>() {

			@Override
			public Predicate toPredicate(Root<PatientVisEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate checkId = cb.equal(root.get(PatientVisEntries_.patientVisEntriesAdministrationId), testDetailId);
				Predicate isActive = cb.equal(root.get(PatientVisEntries_.patientVisEntriesIsActive), true);
				return cb.and(checkId, isActive);
			}
		};
	}	
	
	/**
	 * Method to get VIS by testId
	 * @return predicate value vis
	 */
	public static Specification<CvxVaccineGroupMapping> cvxByLabs(final String cvx) {
		return new Specification<CvxVaccineGroupMapping>() {

			@Override
			public Predicate toPredicate(Root<CvxVaccineGroupMapping> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate vis = cb.equal(root.get(CvxVaccineGroupMapping_.cvxVaccineGroupMappingCvxCode), cvx);
				return vis;
			}
		};
	}

	/**
	 * Method to check vis file mapping language code
	 * @return predicate value lanCode
	 */
	public static Specification<Vis> checkVISLangCode(final String groupCode,final String langCode) {
		return new Specification<Vis>() {

			@Override
			public Predicate toPredicate(Root<Vis> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<Vis, VisFileMapping> subJoin = root.join(Vis_.visFileMapping, JoinType.INNER);
				Predicate langPredicate = cb.equal(subJoin.get(VisFileMapping_.visFileMappingLanguageCode), langCode);
				Predicate groupCodePredicate = cb.equal(root.get(Vis_.visVaccineGroupCode), groupCode);
				Predicate resultPredicate = cb.and(langPredicate, groupCodePredicate);
				return resultPredicate;
			}
		};
	}

	/**
	 * Method to get the status list based on column chart_status_reference_idchart_status_reference_id
	 * @return predicate value statusValue
	 */
	public static Specification<ChartStatus> getStatusList() {
		return new Specification<ChartStatus>() {

			@Override
			public Predicate toPredicate(Root<ChartStatus> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate statusValue = cb.equal(root.get(ChartStatus_.chart_status_reference_id), 413);
				query.orderBy(cb.asc(root.get(ChartStatus_.chart_status_status_id)));
				return statusValue;
			}
		};
	}

	/**
	 * Method to get the refusal reason list based on column chart_status_reference_id
	 * @return predicate value refusalReasonValue
	 */
	public static Specification<ChartStatus> getRefusalReasonList() {
		return new Specification<ChartStatus>() {

			@Override
			public Predicate toPredicate(Root<ChartStatus> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate refusalReasonValue = cb.equal(root.get(ChartStatus_.chart_status_reference_id), 997);
				query.orderBy(cb.asc(root.get(ChartStatus_.chart_status_status_id)));
				return refusalReasonValue;
			}
		};
	}

	/**
	 * Method to get the source list based on column chart_status_reference_id
	 * @return predicate value sourceValue
	 */
	public static Specification<ChartStatus> getSourceList() {
		return new Specification<ChartStatus>() {

			@Override
			public Predicate toPredicate(Root<ChartStatus> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate sourceValue = cb.equal(root.get(ChartStatus_.chart_status_reference_id), 998);
				query.orderBy(cb.asc(root.get(ChartStatus_.chart_status_status_id)));
				return sourceValue;
			}
		};
	}

	/**
	 * Predicate to get data based on 
	 * @param searchStr
	 * @return
	 */
	public static Specification<BodySite> searchBasedOnCode(final String searchStr) {
		return new Specification<BodySite>() {

			@Override
			public Predicate toPredicate(Root<BodySite> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				if (Long.class != query.getResultType()) {

				}
				Predicate basedOnCode = cb.like(cb.lower(root.get(BodySite_.bodySiteCode)), searchStr.toLowerCase());
				return basedOnCode;
			}
		};
	}

	/**
	 * Predicate to get data based on 
	 * @param searchStr
	 * @return
	 */
	public static Specification<BodySite> searchBasedOnDesc(final String searchStr) {
		return new Specification<BodySite>() {

			@Override
			public Predicate toPredicate(Root<BodySite> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				if (Long.class != query.getResultType()) {

				}
				Predicate basedOnDesc = cb.like(cb.lower(root.get(BodySite_.bodySiteDescription)), searchStr.toLowerCase());
				return basedOnDesc;
			}	
		};
	}

	/**
	 * Predicate to get lot number details 
	 * @param vaccineId
	 * @return
	 */
	public static Specification<VaccineOrderDetails> getLotNoDetails(final Integer isCHDP,final Integer testId) {
		return new Specification<VaccineOrderDetails>() {

			@Override
			public Predicate toPredicate(Root<VaccineOrderDetails> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<VaccineOrderDetails, VaccineOrder> rootJoin = root.join(VaccineOrderDetails_.vaccineOrder,JoinType.INNER);
				Expression<Double> quantity = cb.diff(cb.sum(root.get(VaccineOrderDetails_.vaccineOrderDetailsQty), 
						root.get(VaccineOrderDetails_.vaccineOrderDetailsQtyReconcile)), 
						root.get(VaccineOrderDetails_.vaccineOrderDetailsQtyUsed));
				rootJoin.on(cb.greaterThan(quantity, 0.0));
				rootJoin.join(VaccineOrder_.vaccineManDetails, JoinType.LEFT);
				Predicate quantityPredicate = cb.greaterThan(quantity, 0.0);
				Predicate expiryDate = cb.greaterThan(root.get(VaccineOrderDetails_.vaccineOrderDetailsExpiry), new Date());
				Predicate isActive = cb.equal(root.get(VaccineOrderDetails_.vaccineOrderDetailsIsactive), true);
				Predicate chdp = cb.equal(root.get(VaccineOrderDetails_.vaccineOrderDetailsIschdp), isCHDP);
				Predicate vaccineId = cb.equal(root.get(VaccineOrderDetails_.vaccineOrderDetailsVaccineId), testId);
				return cb.and(quantityPredicate, expiryDate, isActive, chdp, vaccineId);
			}	
		};
	}

	/**
	 * Method to get the rule for checking 
	 * @param encounterId
	 * with the id in entity and
	 * @return predicate value LabByEncounterId
	 */
	public static Specification<LabEntries> labByEncounterId(final Integer encounterId)	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				root.join(LabEntries_.empProfile,JoinType.LEFT);
				root.join(LabEntries_.encounter,JoinType.LEFT);
				root.join(LabEntries_.labGroups,JoinType.INNER);
				root.fetch(LabEntries_.empProfile);
				root.fetch(LabEntries_.encounter);
				root.fetch(LabEntries_.labGroups);
				Predicate LabByEncounterId = cb.equal(root.get(LabEntries_.labEntriesEncounterId), encounterId);
				query.orderBy(cb.asc(root.get(LabEntries_.labEntriesGroupid)),cb.desc(root.get(LabEntries_.labEntriesOrdOn)));
				return LabByEncounterId;
			}
		};
	}

	/**
	 * Method to get the rule for checking 
	 * @param testStatus
	 * not in given list
	 * @return predicate value LabsByTestStatus
	 */
	public static Specification<LabEntries> LabsByTestStatus()	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate LabsByTestStatus = cb.not((root.get(LabEntries_.labEntriesTestStatus).in(2,7)));
				return LabsByTestStatus;
			}
		};
	}

	/**
	 * Specification to get the list of labs having testId
	 * @param testId
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> testIdsWithOrder(final Integer[] testId)
	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.orderBy(cb.asc(root.get(LabEntries_.labEntriesTestDesc)),cb.desc(cb.coalesce(root.get(LabEntries_.labEntriesPerfOn),cb.function("to_timestamp",Timestamp.class,cb.literal("1900-05-13 16:40:35"),cb.literal("YYYY-MM-DD HH24:MI:SS")))));
				Predicate testIds = root.get(LabEntries_.labEntriesTestId).in((Object[])testId);
				return testIds;
			}
		};
	}

	/**
	 * Specification to get the list of labs having chart Id
	 * @param chart Id
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> chartId(final Integer chartId) {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate chartIdPred = cb.equal(root.get(LabEntries_.labEntriesChartid),chartId);
				return chartIdPred;
			}
		};
	}
	
	/**
	 * Specification to get the list of labs having chart Id
	 * @param chart Id
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> chartIdLog(final Integer chartId) {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate chartIdPred = cb.equal(root.get(LabEntries_.labEntriesChartid),chartId);
				query.distinct(true);
				return chartIdPred;
			}
		};
	}
	
	/**
	 * Specification to get the list of labs having chart Id with Orderby OrderDate
	 * @param chart Id
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> chartIdLogOrderBy(final Integer chartId) {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate chartIdPred = cb.equal(root.get(LabEntries_.labEntriesChartid),chartId);
				query.orderBy(cb.desc(root.get(LabEntries_.labEntriesOrdOn)), cb.desc(root.get(LabEntries_.labEntriesPerfOn)));
				query.distinct(true);
				return chartIdPred;
			}
		};
	}


	/**
	 * Specification to get the list of labs having encounter Id
	 * @param chart Id
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> labEntryencounterId(final Integer encounterId)
	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate encounterIdPred = cb.equal(root.get(LabEntries_.labEntriesEncounterId),encounterId);
				return encounterIdPred;
			}
		};
	}

	/**
	 * Specification to get the list of labs having status greater than
	 * @param status
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> statusGreaterThan(final Integer status)
	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate statusGreaterThan = cb.greaterThan(root.get(LabEntries_.labEntriesTestStatus),status);
				return statusGreaterThan;
			}
		};
	}

	/**
	 * Specification to get the list of labs having status less than
	 * @param status
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> statusLessThan(final Integer status)
	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate statusLessThan = cb.lessThan(root.get(LabEntries_.labEntriesTestStatus),status);
				return statusLessThan;
			}
		};
	}
	
	/**
	 * Specification to get the list of labs having status not equal to
	 * @param status
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> statusNotEqual(final Integer status)
	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate statusNotEqual = cb.notEqual(root.get(LabEntries_.labEntriesTestStatus),status);
				return statusNotEqual;
			}
		};
	}

	/**
	 * Specification to get the list of labs having status equal to
	 * @param status
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> statusEqual(final Integer status)
	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate statusEqual = cb.equal(root.get(LabEntries_.labEntriesTestStatus),status);
				return statusEqual;
			}
		};
	}

	/**
	 * Specification to get the list of orders having testId
	 * @param testId
	 * @param chartId
	 * @param status greater than
	 * @param status not equal
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> getOrderDetailsExceptVaccines(final Integer[] testId,final Integer chartId,final Integer statusGreaterThan,final Integer statusNotEqual,final Integer encounterId,final Encounter encounterEntity)
	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.orderBy(cb.desc(cb.coalesce(root.get(LabEntries_.labEntriesPerfOn),cb.function("to_timestamp",Timestamp.class,cb.literal("1900-05-13 16:40:35"),cb.literal("YYYY-MM-DD HH24:MI:SS")))));
				Predicate testIdPred=root.get(LabEntries_.labEntriesTestId).in((Object[])testId);
				Predicate testStatusGreaterPred=cb.greaterThan(root.get(LabEntries_.labEntriesTestStatus),statusGreaterThan);
				Predicate testStatusNotEqualPred=cb.notEqual(root.get(LabEntries_.labEntriesTestStatus),statusNotEqual);
				Predicate chartIdPred=cb.equal(root.get(LabEntries_.labEntriesChartid),chartId);
				Predicate groupIdVaccPred=cb.notEqual(root.get(LabEntries_.labEntriesGroupid),36);
				Predicate groupIdsPred = null;
				if((encounterId!=-1)&&(encounterEntity.getEncounterStatus()==3)){
					Predicate encounterDatePred=cb.lessThanOrEqualTo(root.get(LabEntries_.labEntriesOrdOn), encounterEntity.getEncounterDate());
					groupIdsPred=cb.and(testIdPred,testStatusGreaterPred,testStatusNotEqualPred,chartIdPred,groupIdVaccPred,encounterDatePred);
				}else{
					groupIdsPred=cb.and(testIdPred,testStatusGreaterPred,testStatusNotEqualPred,chartIdPred,groupIdVaccPred);
				}
				return groupIdsPred;
			}
		};
	}

	/**
	 * Specification to get the list of orders having testId
	 * @param testId
	 * @param chartId
	 * @param status greater than
	 * @param status not equal
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> getOrderDetailsVaccines(final Integer[] testId,final Integer chartId,final Integer statusGreaterThan,final Integer statusNotEqual,final Integer encounterId,final Encounter encounterEntity)
	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.orderBy(cb.desc(cb.coalesce(root.get(LabEntries_.labEntriesPerfOn),cb.function("to_timestamp",Timestamp.class,cb.literal("1900-05-13 16:40:35"),cb.literal("YYYY-MM-DD HH24:MI:SS")))));
				Predicate testIdPred=root.get(LabEntries_.labEntriesTestId).in((Object[])testId);
				Predicate testStatusGreaterPred=cb.greaterThan(root.get(LabEntries_.labEntriesTestStatus),statusGreaterThan);
				Predicate testStatusNotEqualPred=cb.notEqual(root.get(LabEntries_.labEntriesTestStatus),statusNotEqual);
				Predicate chartIdPred=cb.equal(root.get(LabEntries_.labEntriesChartid),chartId);
				Predicate groupIdPred=cb.equal(root.get(LabEntries_.labEntriesGroupid),36);
				Predicate groupIdsPred =cb.and(testIdPred,testStatusGreaterPred,testStatusNotEqualPred,chartIdPred,groupIdPred);
				if((encounterId!=-1)&&(encounterEntity.getEncounterStatus()==3)){
					Predicate encounterDatePred=cb.lessThanOrEqualTo(root.get(LabEntries_.labEntriesOrdOn), encounterEntity.getEncounterDate());
					groupIdsPred =cb.and(testIdPred,testStatusGreaterPred,testStatusNotEqualPred,chartIdPred,groupIdPred,encounterDatePred);
				}else{
					 groupIdsPred =cb.and(testIdPred,testStatusGreaterPred,testStatusNotEqualPred,chartIdPred,groupIdPred);
				}
				return groupIdsPred;
			}
		};
	}

	/**
	 * Specification to get the list of orders having testId
	 * @param testId
	 * @param chartId
	 * @param status greater than
	 * @param status not equal
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> getDetailsVaccinesNotPatDecli(final Integer[] testId,final Integer chartId,final Integer statusGreaterThan,final Integer statusNotEqual)
	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.orderBy(cb.desc(cb.coalesce(root.get(LabEntries_.labEntriesPerfOn),cb.function("to_timestamp",Timestamp.class,cb.literal("1900-05-13 16:40:35"),cb.literal("YYYY-MM-DD HH24:MI:SS")))));
				Predicate groupIdsPred =cb.and(root.get(LabEntries_.labEntriesTestId).in((Object[])testId),
						cb.greaterThan(root.get(LabEntries_.labEntriesTestStatus),statusGreaterThan),
						cb.notEqual(root.get(LabEntries_.labEntriesTestStatus),statusNotEqual),
						cb.equal(root.get(LabEntries_.labEntriesChartid),chartId),
						cb.equal(root.get(LabEntries_.labEntriesGroupid),36));
				return groupIdsPred;
			}
		};
	}

	/**
	 * Specification to get the list of orders having testId
	 * @param testId
	 * @param chartId
	 * @param status greater than
	 * @param status less than
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> getDetailsExceptVaccinesNotPatDecli(final Integer[] testId,final Integer chartId,final Integer statusGreaterThan,final Integer statusLessThan)
	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.orderBy(cb.desc(cb.coalesce(root.get(LabEntries_.labEntriesPerfOn),cb.function("to_timestamp",Timestamp.class,cb.literal("1900-05-13 16:40:35"),cb.literal("YYYY-MM-DD HH24:MI:SS")))),
						cb.asc(root.get(LabEntries_.labEntriesTestStatus)));
				Predicate groupIdsPred =cb.and(root.get(LabEntries_.labEntriesTestId).in((Object[])testId),
						cb.greaterThan(root.get(LabEntries_.labEntriesTestStatus),statusGreaterThan),
						cb.lessThan(root.get(LabEntries_.labEntriesTestStatus),statusLessThan),
						cb.equal(root.get(LabEntries_.labEntriesChartid),chartId),
						cb.notEqual(root.get(LabEntries_.labEntriesGroupid),36));
				return groupIdsPred;
			}
		};
	}

	/**
	 * Specification to get the list of labs having testdetailId
	 * @param testdetailId
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> testdetailIds(final Integer testdetailId)
	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate testdetailIds = cb.equal(root.get(LabEntries_.labEntriesTestdetailId),testdetailId);
				return testdetailIds;
			}
		};
	}

	/**
	 * Specification to get the list of labs not having group id
	 * @param groupId
	 * @return Specification<LabEntries>
	 */
	public static Specification<LabEntries> groupIdNotLabEntries(final Integer groupId)
	{
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate groupIdPred = cb.notEqual(cb.coalesce(root.get(LabEntries_.labEntriesGroupid),-1),groupId);
				return groupIdPred;
			}
		};
	}
	
	/**
	 * Specification to get the list of labs having testId
	 * @param testId
	 * @return Specification<LabDescription>
	 */
	public static Specification<LabDescription> testIdsLabDescription(final Integer[] testId)
	{
		return new Specification<LabDescription>() {

			@Override
			public Predicate toPredicate(Root<LabDescription> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate testIds = root.get(LabDescription_.labDescriptionTestid).in((Object[])testId);
				return testIds;
			}
		};
	}

	/**
	 * Specification to get the list of labs not having group id
	 * @param groupId
	 * @return Specification<LabDescription>
	 */
	public static Specification<LabDescription> groupIdNot(final Integer groupId)
	{
		return new Specification<LabDescription>() {

			@Override
			public Predicate toPredicate(Root<LabDescription> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate groupIdPred = cb.notEqual(cb.coalesce(root.get(LabDescription_.labDescriptionGroupid),-1),groupId);
				return groupIdPred;
			}
		};
	}

	/**
	 * Specification to get the list of labs having group id
	 * @param groupId
	 * @return Specification<LabDescription>
	 */
	public static Specification<LabDescription> groupId(final Integer groupId)
	{
		return new Specification<LabDescription>() {

			@Override
			public Predicate toPredicate(Root<LabDescription> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate groupIdPred = cb.equal(root.get(LabDescription_.labDescriptionGroupid),groupId);
				return groupIdPred;
			}
		};
	}

	/**
	 * Specification to get the list of labs having LOINC code
	 * @param LOINC
	 * @return Specification<LabDescription>
	 */
	public static Specification<LabDescription> loincCodeLabs(final List<String> loinc)
	{
		return new Specification<LabDescription>() {

			@Override
			public Predicate toPredicate(Root<LabDescription> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate loincCode = root.get(LabDescription_.labDescriptionLoinc).in(loinc);
				return loincCode;
			}
		};
	}

	/**
	 * Specification to get the list of labs after mapping with HL7 table
	 * @param codes
	 * @param codessystem
	 * @return Specification<LabDescription>
	 */
	public static Specification<LabDescription> hl7codes(final List<String> codes,final Integer codeSystemNumber)
	{
		return new Specification<LabDescription>() {

			@Override
			public Predicate toPredicate(Root<LabDescription> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<Join<LabDescription, Hl7ExternalTestmapping>,Hl7ExternalTest> join=root.join("hl7ExternalTestmappingTable",JoinType.INNER).join("hl7ExternalTestTable", JoinType.INNER);
				Predicate hl7codes = cb.and(cb.equal(join.get(Hl7ExternalTest_.hl7ExternalTestLabcompanyid),codeSystemNumber),join.get(Hl7ExternalTest_.hl7ExternalTestCode).in(codes));
				return hl7codes;
			}
		};
	}

	/**
	 * Specification to get the list of parameter entries having paramId and chart id
	 * @param paramId
	 * @param chartId
	 * @return Specification<LabEntriesParameter>
	 */
	public static Specification<LabEntriesParameter> getParamDetails(final Integer[] paramId,final Integer chartId,final Integer encounterId,final Encounter encounterEntity)
	{
		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<LabEntriesParameter,LabParameters> join= root.join("labParametersTable", JoinType.LEFT);
				query.orderBy(cb.desc(cb.coalesce(root.get(LabEntriesParameter_.labEntriesParameterDate),cb.function("to_timestamp",Timestamp.class,cb.literal("1900-05-13 16:40:35"),cb.literal("YYYY-MM-DD HH24:MI:SS")))),
						cb.desc(root.get(LabEntriesParameter_.labEntriesParameterId)));
				Predicate paramMapId=root.get(LabEntriesParameter_.labEntriesParameterMapid).in((Object[])paramId);
				Predicate isActivePred=cb.equal(join.get(LabParameters_.labParametersIsactive),true);
				Predicate entriesIsActivePred=cb.equal(cb.coalesce(root.get(LabEntriesParameter_.labEntriesParameterIsactive), true),true);
				Predicate chartIdPred=cb.equal(root.get(LabEntriesParameter_.labEntriesParameterChartid), chartId);
				Predicate paramDetails = null;
				if((encounterId!=-1)&&(encounterEntity.getEncounterStatus()==3)){
					Predicate encounterDatePred=cb.lessThanOrEqualTo(root.get(LabEntriesParameter_.labEntriesParameterDate), encounterEntity.getEncounterDate());
					paramDetails = cb.and(paramMapId,isActivePred,entriesIsActivePred,chartIdPred,encounterDatePred);
				}else{
					paramDetails = cb.and(paramMapId,isActivePred,entriesIsActivePred,chartIdPred);
				}
				return paramDetails;
			}
		};
	}

	/**
	 * Specification to get the list of parameter entries having paramId and chart id
	 * @param paramId
	 * @param chartId
	 * @return Specification<LabEntriesParameter>
	 */
	public static Specification<LabEntriesParameter> getParamDetailsStatus(final Integer[] paramId,final Integer chartId)
	{
		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<LabEntriesParameter,LabParameters> join= root.join("labParametersTable", JoinType.LEFT);
				Join<LabEntriesParameter,LabEntries> join1= root.join("labEntriesTable", JoinType.LEFT);
				query.orderBy(cb.desc(cb.coalesce(root.get(LabEntriesParameter_.labEntriesParameterDate),cb.function("to_timestamp",Timestamp.class,cb.literal("1900-05-13 16:40:35"),cb.literal("YYYY-MM-DD HH24:MI:SS")))),
						cb.asc(join1.get(LabEntries_.labEntriesTestStatus)));
				Predicate paramDetails = cb.and(root.get(LabEntriesParameter_.labEntriesParameterMapid).in((Object[])paramId),
						cb.equal(join.get(LabParameters_.labParametersIsactive),true),
						cb.equal(cb.coalesce(root.get(LabEntriesParameter_.labEntriesParameterIsactive), true),true),
						cb.equal(root.get(LabEntriesParameter_.labEntriesParameterChartid), chartId));
				root.fetch(LabEntriesParameter_.labEntriesTable);
				return paramDetails;
			}
		};
	}

	/**
	 * Specification to get the list of parameter entries having testdetailid
	 * @param testdetailid
	 * @return Specification<LabEntriesParameter>
	 */
	public static Specification<LabEntriesParameter> getParamEntriesTestDetailId(final Integer testdetailid) {
		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getParams = cb.equal(root.get(LabEntriesParameter_.labEntriesParameterTestdetailid),testdetailid);
				query.distinct(true);
				return getParams;
			}
		};
	}

	/**
	 * Specification to get the list of parameter entries not having testdetailid
	 * @param testdetailid
	 * @return Specification<LabEntriesParameter>
	 */
	public static Specification<LabEntriesParameter> getParamEntriesTestDetailIdNot(final Integer testdetailid) {
		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getParams = cb.notEqual(root.get(LabEntriesParameter_.labEntriesParameterTestdetailid),testdetailid);
				return getParams;
			}
		};
	}


	/**
	 * Specification to get the list of parameter entries having map id
	 * @param testdetailid
	 * @return Specification<LabEntriesParameter>
	 */
	public static Specification<LabEntriesParameter> getParamEntriesMapId(final Integer mapId)
	{
		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getParams = cb.equal(root.get(LabEntriesParameter_.labEntriesParameterMapid),mapId);
				return getParams;
			}
		};
	}

	/**
	 * Specification to get the list of parameter entries having parameter id
	 * @param parameter id
	 * @return Specification<LabEntriesParameter>
	 */
	public static Specification<LabEntriesParameter> getParamEntriesId(final Integer parameterId)
	{
		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getParamEntriesId = cb.equal(root.get(LabEntriesParameter_.labEntriesParameterId),parameterId);
				return getParamEntriesId;
			}
		};
	}

	/**
	 * Specification to get the list of parameter entries which are active
	 * @param isActive
	 * @return Specification<LabEntriesParameter>
	 */
	public static Specification<LabEntriesParameter> getParamEntriesIsActive(final Boolean isActive)
	{
		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getParamEntriesId = cb.equal(cb.coalesce(root.get(LabEntriesParameter_.labEntriesParameterIsactive),true),isActive);
				return getParamEntriesId;
			}
		};
	}

	/**
	 * Specification to get the list of Lab parameters which are active
	 * @param isActive
	 * @return Specification<LabParameters>
	 */
	public static Specification<LabParameters> labParametersIsActive(final Boolean isActive)
	{
		return new Specification<LabParameters>() {

			@Override
			public Predicate toPredicate(Root<LabParameters> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate labParametersIsactive = cb.equal(root.get(LabParameters_.labParametersIsactive),isActive);
				return labParametersIsactive;
			}
		};
	}

	/**
	 * Specification to get the list of distinct Lab parameters which are not empty
	 * @return Specification<LabParameters>
	 */
	public static Specification<LabParameters> labParametersNotEmptyDistinct()
	{
		return new Specification<LabParameters>() {

			@Override
			public Predicate toPredicate(Root<LabParameters> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.distinct(true);
				query.orderBy(cb.asc(root.get(LabParameters_.labParametersDisplayname)));
				Predicate labParametersNotEmpty = cb.notEqual(cb.coalesce(cb.trim(root.get(LabParameters_.labParametersDisplayname)), cb.literal("")), cb.literal(""));
				return labParametersNotEmpty;
			}
		};
	}

	/**
	 * Specification to get the list of params having paramId
	 * @param testId
	 * @return Specification<LabParameters>
	 */
	public static Specification<LabParameters> paramIdsLabParameter(final Integer[] paramId)
	{
		return new Specification<LabParameters>() {

			@Override
			public Predicate toPredicate(Root<LabParameters> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate testIds = root.get(LabParameters_.labParametersId).in((Object[])paramId);
				return testIds;
			}
		};
	}


	/**
	 * Specification to get the list of labs having LOINC code
	 * @param LOINC
	 * @return Specification<LabParameters>
	 */
	public static Specification<LabParameters> loincCodeParams(final List<String> loinc)
	{
		return new Specification<LabParameters>() {

			@Override
			public Predicate toPredicate(Root<LabParameters> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<LabParameters,LabParameterCode> join=root.join("labParameterCodeTable",JoinType.INNER);
				query.distinct(true);
				Predicate loincCode = cb.and(join.get(LabParameterCode_.labParameterCodeValue).in(loinc),cb.equal(join.get(LabParameterCode_.labParameterCodeSystem),"LOINC"));
				return loincCode;
			}
		};
	}

	/**
	 * Specification to get the list of parameter entries having paramId
	 * @param paramId
	 * @param chartId
	 * @return Specification<LabParameters>
	 */
	public static Specification<LabParameters> getParamDetails(final Integer paramId)
	{
		return new Specification<LabParameters>() {

			@Override
			public Predicate toPredicate(Root<LabParameters> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getParamDetails = cb.equal(root.get(LabParameters_.labParametersId),paramId);
				return getParamDetails;
			}
		};
	}

	/**
	 * Specification to get the list of parameter entries having displayname
	 * @param paramId
	 * @param chartId
	 * @return Specification<LabParameters>
	 */
	public static Specification<LabParameters> getParamDisplayname(final String displayname,final Boolean exactMatch)
	{
		return new Specification<LabParameters>() {

			@Override
			public Predicate toPredicate(Root<LabParameters> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getParamDetails=null;
				if(!exactMatch)
					getParamDetails=cb.like(cb.upper(root.get(LabParameters_.labParametersDisplayname)), "%"+displayname.toUpperCase()+"%");
				else
					getParamDetails= cb.like(cb.upper(root.get(LabParameters_.labParametersDisplayname)),displayname.toUpperCase());
				return getParamDetails;
			}
		};
	}

	/**
	 * Specification to get the list of parameter entries having units
	 * @param paramId
	 * @param chartId
	 * @return Specification<LabParameters>
	 */
	public static Specification<LabParameters> getParamUnits(final String units,final Boolean exactMatch)
	{
		return new Specification<LabParameters>() {

			@Override
			public Predicate toPredicate(Root<LabParameters> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getParamUnits=null;
				if(!exactMatch)
					getParamUnits=cb.like(cb.upper(root.get(LabParameters_.labParametersUnits)), "%"+units.toUpperCase()+"%");
				else
					getParamUnits= cb.like(cb.upper(root.get(LabParameters_.labParametersUnits)),units.toUpperCase());
				return getParamUnits;
			}
		};
	}


	/**
	 * Specification to get the list of parameter code entries having code
	 * @param code
	 * @return Specification<LabParameterCode>
	 */
	public static Specification<LabParameterCode> getCode(final String code)
	{
		return new Specification<LabParameterCode>() {

			@Override
			public Predicate toPredicate(Root<LabParameterCode> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getParamDetails = cb.equal(root.get(LabParameterCode_.labParameterCodeValue),code);
				return getParamDetails;
			}
		};
	}
	/**
	 * Specification to get the list of parameter code entries having paramId
	 * @param code
	 * @return Specification<LabParameterCode>
	 */
	public static Specification<LabParameterCode> getParamId(final int paramId)
	{
		return new Specification<LabParameterCode>() {

			@Override
			public Predicate toPredicate(Root<LabParameterCode> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getParamDetails = cb.equal(root.get(LabParameterCode_.labParameterCodeParamid ),paramId);
				return getParamDetails;
			}
		};
	}

	/**
	 * Specification to get the list of parameter code entries having code
	 * @param codeSystem
	 * @return Specification<LabParameterCode>
	 */
	public static Specification<LabParameterCode> getCodeSystem(final String codeSystem)
	{
		return new Specification<LabParameterCode>() {

			@Override
			public Predicate toPredicate(Root<LabParameterCode> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate getParamDetails = cb.equal(root.get(LabParameterCode_.labParameterCodeSystem),codeSystem);
				return getParamDetails;
			}
		};
	}


	/**
	 * Specification to get the list of distinct lab groups which are active
	 * @param isActive
	 * @return Specification<LabGroups>
	 */
	public static Specification<LabGroups> labGroupIsActiveDistinct(final Boolean isActive)
	{
		return new Specification<LabGroups>() {

			@Override
			public Predicate toPredicate(Root<LabGroups> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.distinct(true);
				query.orderBy(cb.asc(root.get(LabGroups_.labGroupsDesc)));
				Predicate labGroupsIsActiveDistinct = cb.equal(root.get(LabGroups_.labGroupsIsactive),isActive);
				return labGroupsIsActiveDistinct;
			}
		};
	}

	/**
	 * Specification to get the list of distinct hl7 results having id
	 * @param isActive
	 * @return Specification<Hl7ResultInbox>
	 */
	public static Specification<Hl7ResultInbox> hl7ResultsId(final Integer id)
	{
		return new Specification<Hl7ResultInbox>() {

			@Override
			public Predicate toPredicate(Root<Hl7ResultInbox> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate hl7ResultsId = cb.equal(root.get(Hl7ResultInbox_.hl7ResultInboxId),id);
				return hl7ResultsId;
			}
		};
	}

	/**
	 * Specification to get the list of distinct labs having lab id
	 * @param labId
	 * @return Specification<LabIncludePrevious>
	 */
	public static Specification<LabIncludePrevious> labIncludeLabId(final Integer labId)
	{
		return new Specification<LabIncludePrevious>() {

			@Override
			public Predicate toPredicate(Root<LabIncludePrevious> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate labIncludeLabId = cb.equal(root.get(LabIncludePrevious_.labIncludePreviousLabid),labId);
				return labIncludeLabId;
			}
		};
	}

	/**
	 * Specification to get the list of distinct labs having encounter Id
	 * @param encounterId
	 * @return Specification<LabIncludePrevious>
	 */
	public static Specification<LabIncludePrevious> labIncludeEncounterId(final Integer encounterId)
	{
		return new Specification<LabIncludePrevious>() {

			@Override
			public Predicate toPredicate(Root<LabIncludePrevious> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate labIncludeLabId = cb.equal(root.get(LabIncludePrevious_.labIncludePreviousEncounterid),encounterId);
				return labIncludeLabId;
			}
		};
	}

	/**
	 * Specification to get the list of distinct labs having encounter Id
	 * @param encounterId
	 * @return Specification<LabAlertforwardstatus>
	 */
	public static Specification<LabAlertforwardstatus> labAlertForwardLabStatus(final Integer labStatus)
	{
		return new Specification<LabAlertforwardstatus>() {

			@Override
			public Predicate toPredicate(Root<LabAlertforwardstatus> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate labIncludeLabId = cb.equal(root.get(LabAlertforwardstatus_.labAlertforwardstatusLabstatus),labStatus);
				return labIncludeLabId;
			}
		};
	}

	/**
	 * Specification to get the list of specimens having source
	 * @param source
	 * @return Specification<Specimen>
	 */
	public static Specification<Specimen> specimenSource(final String source)
	{
		return new Specification<Specimen>() {

			@Override
			public Predicate toPredicate(Root<Specimen> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate specimenSource = cb.equal(root.get(Specimen_.specimenSource),source);
				return specimenSource;
			}
		};
	}

	/**
	 * Specification to get the list of specimens having col volume
	 * @param source
	 * @return Specification<Specimen>
	 */
	public static Specification<Specimen> specimenColVol(final String colVol) {
		return new Specification<Specimen>() {

			@Override
			public Predicate toPredicate(Root<Specimen> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate specimenSource = cb.equal(root.get(Specimen_.specimenCollVol),colVol);
				return specimenSource;
			}
		};
	}

	/**
	 * Specification to get the list of specimens having col volume unit
	 * @param source
	 * @return Specification<Specimen>
	 */
	public static Specification<Specimen> specimenColVolUnit(final String unit)	{
		return new Specification<Specimen>() {

			@Override
			public Predicate toPredicate(Root<Specimen> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate specimenSource = cb.equal(root.get(Specimen_.specimenCollVolUnit),unit);
				return specimenSource;
			}
		};
	}

	/**
	 * Specification to get the list of specimens having date
	 * @param source
	 * @return Specification<Specimen>
	 */
	public static Specification<Specimen> specimenDate(final Timestamp date) {
		return new Specification<Specimen>() {

			@Override
			public Predicate toPredicate(Root<Specimen> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate specimenSource = cb.equal(root.get(Specimen_.specimenDate),date);
				return specimenSource;
			}
		};
	}

	/**
	 * Specification to get the list of testIds having param
	 * @param params
	 * @return Specification<LabDescpParameters>
	 */
	public static Specification<LabDescpParameters> getParamIdBased(final Integer[] params)
	{
		return new Specification<LabDescpParameters>() {

			@Override
			public Predicate toPredicate(Root<LabDescpParameters> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<LabDescpParameters,LabParameters> join=root.join("labParametersTable",JoinType.INNER);
				Predicate getParamIdBased = join.get(LabParameters_.labParametersId).in((Object[])params);
				return getParamIdBased;
			}
		};
	}

	/**
	 * Specification to find test
	 * @param encounterId
	 * @param testDetailId
	 * @return
	 */
	public static Specification<LabEntries> findTest(final String encounterId, final String testDetailId) {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate checkEncounterId = cb.equal(root.get(LabEntries_.labEntriesEncounterId), Integer.parseInt(encounterId));
				Predicate checkTestDetailId = cb.equal(root.get(LabEntries_.labEntriesTestdetailId), Integer.parseInt(testDetailId));
				return cb.and(checkEncounterId, checkTestDetailId);
			}
		};
	}

	/**
	 * Specification to check patient id and get encounter id
	 * @param patientId
	 * @return
	 */
	public static Specification<PatientRegistration> checkPatientId(final Integer patientId) {
		return new Specification<PatientRegistration>() {

			@Override
			public Predicate toPredicate(Root<PatientRegistration> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate checkEncounterId = cb.equal(root.get(PatientRegistration_.patientRegistrationId), patientId);
				return checkEncounterId;
			}
		};
	}

	/**
	 * Specification to check specimen condition
	 * @param condition
	 * @return
	 */
	public static Specification<Specimen> specimenCondition(final String condition) {
		return new Specification<Specimen>() {

			@Override
			public Predicate toPredicate(Root<Specimen> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate specimenSource = cb.equal(root.get(Specimen_.specimenCondition), condition);
				return specimenSource;
			}
		};
	}

	/**
	 * Specification to verify file Scan Id
	 * @param fileScanId
	 * @return
	 */
	public static Specification<FileDetails> checkFileScanId(final Integer fileScanId) {
		return new Specification<FileDetails>() {

			@Override
			public Predicate toPredicate(Root<FileDetails> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate specimenSource = cb.equal(root.get(FileDetails_.filedetailsId), fileScanId);
				return specimenSource;
			}
		};
	}
	
	public static Specification<LabEntries> getTestLog(final Integer testId) {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate test = root.get(LabEntries_.labEntriesTestId).in(testId);
				return test;
			}
		};
	}

	public static Specification<LabEntriesParameter> getParamLog(final Integer paramId, final Integer chartId) {
		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				@SuppressWarnings("unused")
				Join<LabEntriesParameter,LabParameters> params1 = root.join("labParametersTable",JoinType.INNER);
				Join<LabEntriesParameter, LabEntries> labJoin = root.join(LabEntriesParameter_.labEntriesTable, JoinType.INNER);
				Predicate param = root.get(LabEntriesParameter_.labEntriesParameterMapid).in(paramId);
				Predicate chart = cb.equal(root.get(LabEntriesParameter_.labEntriesParameterChartid),chartId);
				Predicate active = cb.equal(root.get(LabEntriesParameter_.labEntriesParameterIsactive), true);
				Predicate status = cb.and(cb.greaterThan(labJoin.get(LabEntries_.labEntriesTestStatus), 2),
						cb.lessThan(labJoin.get(LabEntries_.labEntriesTestStatus), 7));
				query.distinct(true);
				return cb.and(param,chart,active, status);
			}
		};
	}

	public static Specification<PatientRegistration> getPatientData(final Integer labEntriesChartid) {
		return new Specification<PatientRegistration>() {

			@Override
			public Predicate toPredicate(Root<PatientRegistration> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientRegistration,Chart> chartJoin = root.join(PatientRegistration_.alertTable,JoinType.INNER);
				Predicate chart = cb.equal(chartJoin.get(Chart_.chartId),labEntriesChartid);
				return chart;
			}
		};
	}

	public static Specification<LabEntries> checkDate(final Timestamp fromDate, final Timestamp toDate) {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkDate = cb.and(cb.greaterThan(root.get(LabEntries_.labEntriesPerfOn), fromDate),
						cb.lessThan(root.get(LabEntries_.labEntriesPerfOn), toDate));
				return checkDate;
			}
		};
	}

	public static Specification<LabEntries> checkDeleted() {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate status = cb.and(cb.notEqual(root.get(LabEntries_.labEntriesTestStatus), 2),
						cb.notEqual(root.get(LabEntries_.labEntriesTestStatus), 7));
				query.orderBy(cb.desc(root.get(LabEntries_.labEntriesOrdOn)), cb.desc(root.get(LabEntries_.labEntriesPerfOn)));
				return status;
			}
		};
	}

	public static Specification<LabEntries> checkOrderedStatus() {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate status = cb.equal(root.get(LabEntries_.labEntriesTestStatus), 1);
				return status;
			}
		};
	}
	
	public static Specification<LabEntries> checkReviewedStatus() {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate status = cb.equal(root.get(LabEntries_.labEntriesTestStatus), 3);
				return status;
			}
		};
	}
}