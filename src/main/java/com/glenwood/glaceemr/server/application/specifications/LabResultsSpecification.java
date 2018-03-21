package com.glenwood.glaceemr.server.application.specifications;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.FileDetails;
import com.glenwood.glaceemr.server.application.models.FileDetails_;
import com.glenwood.glaceemr.server.application.models.FileName;
import com.glenwood.glaceemr.server.application.models.FileName_;
import com.glenwood.glaceemr.server.application.models.Hl7DocsInbox;
import com.glenwood.glaceemr.server.application.models.Hl7DocsInbox_;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTest;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTest_;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTestmapping;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTestmapping_;
import com.glenwood.glaceemr.server.application.models.Hl7ResultInbox;
import com.glenwood.glaceemr.server.application.models.Hl7ResultInbox_;
import com.glenwood.glaceemr.server.application.models.Hl7Unmappedresults;
import com.glenwood.glaceemr.server.application.models.Hl7Unmappedresults_;
import com.glenwood.glaceemr.server.application.models.Hl7importCompanies;
import com.glenwood.glaceemr.server.application.models.LabEntries;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter_;
import com.glenwood.glaceemr.server.application.models.LabEntries_;
import com.glenwood.glaceemr.server.application.models.LabParameterCode;
import com.glenwood.glaceemr.server.application.models.LabParameterCode_;
import com.glenwood.glaceemr.server.application.models.LabParameters;
import com.glenwood.glaceemr.server.application.models.LabParameters_;
import com.glenwood.glaceemr.server.application.models.LabcompanyDetails;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.Specimen;

/**
 * @author yasodha
 * 
 * LabResultsSpecification contains all the predicates (rules)
 * required to get the results data for lab results module.
 */

public class LabResultsSpecification {

	/**
	 * Specification to check whether an user is active or not
	 * @return
	 */
	public static Specification<EmployeeProfile> getActiveUser() {
		return new Specification<EmployeeProfile>() {

			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate isActive = cb.equal(root.get(EmployeeProfile_.empProfileIsActive), true);
				return isActive;
			}	
		};
	}

	/**
	 * Specification to check whether employee full name match with 
	 * @param namePattern
	 * @return
	 */
	public static Specification<EmployeeProfile> verifyFullName(final String namePattern) {
		return new Specification<EmployeeProfile>() {

			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate fullName = cb.notLike(root.get(EmployeeProfile_.empProfileFullname), "%" + namePattern + "%");
				return fullName;
			}	
		};
	}

	/**
	 * Specification to check whether employee group match with 
	 * @return
	 */
	public static Specification<EmployeeProfile> verifyGroupId() {
		return new Specification<EmployeeProfile>() {

			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate fullName = root.get(EmployeeProfile_.empProfileGroupid).in(-1,-10);
				query.orderBy(cb.desc(root.get(EmployeeProfile_.empProfileGroupid)),cb.asc(root.get(EmployeeProfile_.empProfileFullname)));
				return fullName;
			}	
		};
	}
	public static Specification<EmployeeProfile> verifyDocId(final String reviewedBy) {
		return new Specification<EmployeeProfile>() {

			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate fullName = root.get(EmployeeProfile_.empProfileEmpid).in(reviewedBy);
				return fullName;
			}	
		};
	}
	/**
	 * Specification to get the active result list
	 * @return
	 */
	public static Specification<Hl7ResultInbox> getActiveResult() {
		return new Specification<Hl7ResultInbox>() {

			@Override
			public Predicate toPredicate(Root<Hl7ResultInbox> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate isActive = cb.equal(root.get(Hl7ResultInbox_.hl7ResultInboxIsactive), true);
				return isActive;
			}	
		};
	}

	/**
	 * Specification to get the reviewed or waiting for review results
	 * @param isReviewed
	 * @return
	 */
	public static Specification<Hl7ResultInbox> verifyReviewedStatus(final String isReviewed) {
		return new Specification<Hl7ResultInbox>() {

			@Override
			public Predicate toPredicate(Root<Hl7ResultInbox> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate isActive = cb.equal(root.get(Hl7ResultInbox_.hl7ResultInboxReviewed), Integer.parseInt(isReviewed));
				return isActive;
			}	
		};
	}

	/**
	 * Specification to verify order by based on
	 * @param doctorId
	 * @return
	 */
	public static Specification<Hl7ResultInbox> verifyOrdBy(final ArrayList<Integer> doctorId) {
		return new Specification<Hl7ResultInbox>() {

			@Override
			public Predicate toPredicate(Root<Hl7ResultInbox> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<Hl7ResultInbox, Hl7Unmappedresults> resultsJoin = root.join(Hl7ResultInbox_.hl7UnmappedResults,JoinType.INNER);
				@SuppressWarnings("unused")
				Join<Hl7ResultInbox, PatientRegistration> patJoin = root.join(Hl7ResultInbox_.patientRegistration,JoinType.LEFT);
				Predicate isActive = cb.equal(resultsJoin.get(Hl7Unmappedresults_.hl7UnmappedresultsIsactive), true);
				resultsJoin.on(isActive);
				Predicate checkOrdBy = resultsJoin.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdbyDocid).in(doctorId);
				query.distinct(true);
				query.orderBy(cb.desc(root.get(Hl7ResultInbox_.hl7ResultInboxPlacedDate)));

				return checkOrdBy;
			}	
		};
	}

	/**
	 * Specification to check whether document is reviewed or not
	 * @param reviewed 
	 * @return
	 */
	public static Specification<Hl7DocsInbox> checkFileReviewed(final Boolean reviewed) {
		return new Specification<Hl7DocsInbox>() {

			@Override
			public Predicate toPredicate(Root<Hl7DocsInbox> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<Hl7DocsInbox, FileDetails> docsJoin = root.join(Hl7DocsInbox_.fileDetails,JoinType.LEFT);
				Join<FileDetails, FileName> fileJoin = docsJoin.join(FileDetails_.fileName, JoinType.LEFT);
				Predicate isActive = cb.equal(fileJoin.get(FileName_.filenameIsreviewed), reviewed);
				Predicate activeDoc = cb.equal(root.get(Hl7DocsInbox_.hl7DocsInboxIsactive), true);				
				return cb.or((cb.and(isActive, activeDoc)),(cb.and(activeDoc)));
			}	
		};
	}

	/**
	 * Specification to check the patient id is -1
	 * @return
	 */
	public static Specification<Hl7DocsInbox> checkPatientId() {
		return new Specification<Hl7DocsInbox>() {

			@Override
			public Predicate toPredicate(Root<Hl7DocsInbox> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate patientId = cb.equal(root.get(Hl7DocsInbox_.hl7DocsInboxPatientid), -1);				
				return patientId;
			}	
		};
	}

	/**
	 * Specification to verify the prover details
	 * @return
	 */
	public static Specification<Hl7DocsInbox> verifyDocProvider(final ArrayList<Integer> doctorId) {
		return new Specification<Hl7DocsInbox>() {

			@Override
			public Predicate toPredicate(Root<Hl7DocsInbox> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate provider = root.get(Hl7DocsInbox_.hl7DocsInboxProviderId).in(doctorId);
				query.distinct(true);
				return provider;
			}	
		};
	}

	/**
	 * Specification to check the hl7 Id and return the patient result details
	 * @param hl7FileId
	 * @return
	 */
	public static Specification<Hl7ResultInbox> checkHl7Id(final String hl7FileId) {
		return new Specification<Hl7ResultInbox>() {

			@SuppressWarnings("unused")
			@Override
			public Predicate toPredicate(Root<Hl7ResultInbox> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<Hl7ResultInbox, Hl7Unmappedresults> unmappedJoin = root.join(Hl7ResultInbox_.hl7UnmappedResults, JoinType.INNER);
				unmappedJoin.on(cb.equal(unmappedJoin.get(Hl7Unmappedresults_.hl7UnmappedresultsIsactive), true));
				Join<Hl7Unmappedresults, LabEntries> labJoin = unmappedJoin.join(Hl7Unmappedresults_.labEntriesUnmapped, JoinType.LEFT);
				Join<Hl7Unmappedresults, LabcompanyDetails> labCompJoin = unmappedJoin.join(Hl7Unmappedresults_.labCompanyDetails, JoinType.LEFT);
				Join<Hl7Unmappedresults, EmployeeProfile> empJoin = unmappedJoin.join(Hl7Unmappedresults_.empProfile, JoinType.LEFT);
				Join<Hl7ResultInbox, Hl7importCompanies> importJoin = root.join(Hl7ResultInbox_.importCompanies, JoinType.LEFT);
				Join<LabEntries, FileDetails> fileJoin = labJoin.join(LabEntries_.fileDetails, JoinType.LEFT);
				Join<LabEntries, Specimen> specimenJoin = labJoin.join(LabEntries_.specimen, JoinType.LEFT);
				Predicate verifyId = root.get(Hl7ResultInbox_.hl7ResultInboxId).in(Integer.parseInt(hl7FileId));
				Predicate verifyIsActive = cb.equal(root.get(Hl7ResultInbox_.hl7ResultInboxIsactive), true);
				query.orderBy(cb.asc(unmappedJoin.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdDate)),cb.asc(root.get(Hl7ResultInbox_.hl7ResultInboxId)), cb.asc(unmappedJoin.get(Hl7Unmappedresults_.hl7UnmappedresultsId)));
				return cb.and(verifyId, verifyIsActive);
			}	
		};
	}

	/**
	 * Specification to verify parameter code and coding system
	 * @param paramCode
	 * @param codingSystem
	 * @return
	 */
	public static Specification<LabParameters> verifyParamCodeAndSystem(final String paramCode, final String codingSystem) {
		return new Specification<LabParameters>() {

			@Override
			public Predicate toPredicate(Root<LabParameters> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<LabParameters, LabParameterCode> join = root.join(LabParameters_.labParameterCodeTable,JoinType.INNER);
				Predicate checkCode = cb.equal(join.get(LabParameterCode_.labParameterCodeValue), paramCode);
				Predicate checkSystem = cb.equal(join.get(LabParameterCode_.labParameterCodeSystem), codingSystem);
				return cb.and(checkCode, checkSystem);
			}	
		};
	}

	/**
	 * Specification to get lab parameter data
	 * @param testDetailId
	 * @param displayName
	 * @return
	 */
	public static Specification<LabEntriesParameter> getParamData(final String testDetailId, final String displayName) {
		return new Specification<LabEntriesParameter>() {

			@Override
			public Predicate toPredicate(Root<LabEntriesParameter> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<LabEntriesParameter, LabParameters> join = root.join(LabEntriesParameter_.labParametersTable,JoinType.INNER);
				Predicate checkTestId = cb.equal(root.get(LabEntriesParameter_.labEntriesParameterTestdetailid), testDetailId);
				Predicate checkIsActive = cb.equal(root.get(LabEntriesParameter_.labEntriesParameterIsactive), true);
				Predicate checkDisplayName = cb.like(join.get(LabParameters_.labParametersDisplayname), displayName);
				return cb.and(checkTestId, checkIsActive, checkDisplayName);
			}	
		};
	}

	/**
	 * Specification to verify file id and map status
	 * @param hl7FileId
	 * @return
	 */
	public static Specification<Hl7Unmappedresults> verifyFileIdAndStatus(final String hl7FileId, final String mapStatus) {
		return new Specification<Hl7Unmappedresults>() {

			@Override
			public Predicate toPredicate(Root<Hl7Unmappedresults> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate checkFileId = cb.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsFilewiseId), hl7FileId);
				Predicate checkMapStatus = cb.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsMapStatus), mapStatus);
				return cb.and(checkFileId, checkMapStatus);
			}	
		};
	}

	/**
	 * Specification to get patient data
	 * @param patientId
	 * @return
	 */
	public static Specification<PatientRegistration> getPatientData(final String patientId) {
		return new Specification<PatientRegistration>() {
			@Override
			public Predicate toPredicate(Root<PatientRegistration> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate checkPatientId = cb.equal(root.get(PatientRegistration_.patientRegistrationId), patientId);
				return cb.and(checkPatientId);
			}
		};
	}

	/**
	 * Specification to get document data
	 * @param documentId
	 * @return
	 */
	public static Specification<Hl7DocsInbox> getDocsData(final Integer documentId) {
		return new Specification<Hl7DocsInbox>() {

			@Override
			public Predicate toPredicate(Root<Hl7DocsInbox> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate docId = root.get(Hl7DocsInbox_.hl7DocsInboxId).in(documentId);
				return docId;
			}	
		};
	}

	/**
	 * Specification to get results data
	 * @param documentId
	 * @return
	 */
	public static Specification<Hl7ResultInbox> getResultsData(final Integer  documentId) {
		return new Specification<Hl7ResultInbox>() {

			@Override
			public Predicate toPredicate(Root<Hl7ResultInbox> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Predicate docId = root.get(Hl7ResultInbox_.hl7ResultInboxId).in(documentId);
				return docId;
			}	
		};
	}

	/**
	 * Specification to get results details
	 * @param hl7FileId
	 * @return
	 */
	public static Specification<Hl7Unmappedresults> getResultsDetails(final String hl7FileId) {
		return new Specification<Hl7Unmappedresults>() {

			@Override
			public Predicate toPredicate(Root<Hl7Unmappedresults> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<Hl7Unmappedresults, Hl7ResultInbox> join = root.join(Hl7Unmappedresults_.hl7ResultInbox,JoinType.INNER);
				Predicate docId = join.get(Hl7ResultInbox_.hl7ResultInboxId).in(hl7FileId);
				query.orderBy(cb.asc(root.get(Hl7Unmappedresults_.hl7UnmappedresultsId)));
				return docId;
			}	
		};
	}

	/**
	 * Specification to get results which do not match 
	 * @param labAccessionNo and equal to
	 * @param testDetId
	 * @return
	 */
	public static Specification<Hl7Unmappedresults> checkAccessionAndTestId(final String labAccessionNo, final Integer testDetId) {
		return new Specification<Hl7Unmappedresults>() {

			@Override
			public Predicate toPredicate(Root<Hl7Unmappedresults> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<Hl7Unmappedresults, Hl7ResultInbox> join = root.join(Hl7Unmappedresults_.hl7ResultInbox,JoinType.INNER);
				Predicate accession = cb.notLike(join.get(Hl7ResultInbox_.hl7ResultInboxLabaccessionno), labAccessionNo);
				Predicate detId = cb.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsTestdetailId), testDetId);
				return cb.and(accession, detId);
			}	
		};
	}

	/**
	 * Specification to get list of all ordered labs in investigation
	 * @param chartId
	 * @param testName
	 * @return
	 */
	public static Specification<LabEntries> getOrderedLabs(final String chartId, final String testName) {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate chartCheck = cb.equal(root.get(LabEntries_.labEntriesChartid), Integer.parseInt(chartId));
				Predicate statusCheck = cb.equal(root.get(LabEntries_.labEntriesTestStatus), 1);
				Predicate nameCheck = cb.like(root.get(LabEntries_.labEntriesTestDesc), testName);
				return cb.and(chartCheck, statusCheck, nameCheck);
			}
		};
	}

	/**
	 * Specification to get list of all performed labs in investigation
	 * @param chartId
	 * @param testName
	 * @return
	 */
	public static Specification<LabEntries> getPerformedLabs(final String chartId, final String testName, final String orderDate) {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate chartCheck = cb.equal(root.get(LabEntries_.labEntriesChartid), Integer.parseInt(chartId));
				Predicate statusCheck = cb.equal(root.get(LabEntries_.labEntriesTestStatus), 3);
				Predicate nameCheck = cb.like(root.get(LabEntries_.labEntriesTestDesc), testName);
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				DateFormat parser = new SimpleDateFormat("MM/dd/yyyy"); 
				Date date = null;
				try {
					date = (Date) parser.parse(orderDate);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				String orderedDate = formatter.format(date);
				Predicate dateCheck = cb.equal(root.get(LabEntries_.labEntriesOrdOn), Timestamp.valueOf(orderedDate)); 
				return cb.and(chartCheck, statusCheck, nameCheck, dateCheck);
			}
		};
	}

	/**
	 * Specification to check lab name
	 * @param curTestName
	 * @return
	 */
	public static Specification<Hl7Unmappedresults> checkTestName(final String curTestName) {
		return new Specification<Hl7Unmappedresults>() {

			@Override
			public Predicate toPredicate(Root<Hl7Unmappedresults> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate nameCheck = cb.like(root.get(Hl7Unmappedresults_.hl7UnmappedresultsLabname), curTestName);
				return nameCheck;
			}
		};
	}

	/**
	 * Specification to test lab name
	 * @param testName
	 * @param labcompId
	 * @return
	 */
	public static Specification<Hl7ExternalTest> checkExternalTestName(final String testName, final Integer labcompId) {
		return new Specification<Hl7ExternalTest>() {

			@Override
			public Predicate toPredicate(Root<Hl7ExternalTest> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate nameCheck = cb.equal(root.get(Hl7ExternalTest_.hl7ExternalTestName), testName);
				Predicate labCompIdCheck = cb.equal(root.get(Hl7ExternalTest_.hl7ExternalTestLabcompanyid), labcompId);
				return cb.and(nameCheck, labCompIdCheck);
			}
		};
	}

	/**
	 * Specification to check
	 * @param localTestId
	 * @param labcompId
	 * @return
	 */
	public static Specification<Hl7ExternalTestmapping> verifyTestIdAndCompId(final Integer localTestId, final Integer labcompId) {
		return new Specification<Hl7ExternalTestmapping>() {

			@Override
			public Predicate toPredicate(Root<Hl7ExternalTestmapping> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate nameCheck = cb.equal(root.get(Hl7ExternalTestmapping_.hl7ExternalTestmappingLocaltestid), localTestId);
				Predicate labCompIdCheck = cb.equal(root.get(Hl7ExternalTestmapping_.hl7ExternalTestmappingLabcompanyid), labcompId);
				return cb.and(nameCheck, labCompIdCheck);
			}
		};
	}
	
	/**
	 * Specification to check the hl7 Id and return the patient result details
	 * @param hl7FileId
	 * @return
	 */
	public static Specification<Hl7ResultInbox> getUnmappedResults(final String hl7FileId) {
		return new Specification<Hl7ResultInbox>() {

			@SuppressWarnings("unused")
			@Override
			public Predicate toPredicate(Root<Hl7ResultInbox> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Join<Hl7ResultInbox, Hl7Unmappedresults> unmappedJoin = root.join(Hl7ResultInbox_.hl7UnmappedResults, JoinType.INNER);
				unmappedJoin.on(cb.equal(unmappedJoin.get(Hl7Unmappedresults_.hl7UnmappedresultsIsactive), true));
				Join<Hl7Unmappedresults, LabEntries> labJoin = unmappedJoin.join(Hl7Unmappedresults_.labEntriesUnmapped, JoinType.LEFT);
				Join<Hl7Unmappedresults, LabcompanyDetails> labCompJoin = unmappedJoin.join(Hl7Unmappedresults_.labCompanyDetails, JoinType.LEFT);
				Join<Hl7Unmappedresults, EmployeeProfile> empJoin = unmappedJoin.join(Hl7Unmappedresults_.empProfile, JoinType.LEFT);
				Join<Hl7ResultInbox, Hl7importCompanies> importJoin = root.join(Hl7ResultInbox_.importCompanies, JoinType.LEFT);
				Join<LabEntries, FileDetails> fileJoin = labJoin.join(LabEntries_.fileDetails, JoinType.LEFT);
				Join<LabEntries, Specimen> specimenJoin = labJoin.join(LabEntries_.specimen, JoinType.LEFT);
				Predicate verifyId = root.get(Hl7ResultInbox_.hl7ResultInboxId).in(Integer.parseInt(hl7FileId));
				Predicate verifyIsActive = cb.equal(root.get(Hl7ResultInbox_.hl7ResultInboxIsactive), true);
				Predicate verifyMap = cb.and(cb.notEqual(unmappedJoin.get(Hl7Unmappedresults_.hl7UnmappedresultsMapStatus), "Mapped"),
						cb.notEqual(unmappedJoin.get(Hl7Unmappedresults_.hl7UnmappedresultsMapStatus), "1"));
				query.orderBy(cb.asc(unmappedJoin.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdDate)),cb.asc(root.get(Hl7ResultInbox_.hl7ResultInboxId)), cb.asc(unmappedJoin.get(Hl7Unmappedresults_.hl7UnmappedresultsId)));
				return cb.and(verifyId, verifyIsActive);
			}	
		};
	}

	public static Specification<LabEntries> checkOrders(final String patientId, final String orderedOn, final int value) {
		return new Specification<LabEntries>() {

			@Override
			public Predicate toPredicate(Root<LabEntries> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<LabEntries, Encounter> encounterJoin = root.join(LabEntries_.encounter, JoinType.INNER);
				Join<Encounter, Chart> chartJoin = encounterJoin.join(Encounter_.chart, JoinType.INNER);
				chartJoin.on(cb.equal(root.get(LabEntries_.labEntriesTestStatus), 1));
				Predicate checkPatientId = cb.equal(chartJoin.get(Chart_.chartPatientid), Integer.parseInt(patientId));
				Predicate orderedDate = null;
				if( value == 1 ) {
					orderedDate = cb.equal(root.get(LabEntries_.labEntriesOrdOn), Timestamp.valueOf(orderedOn));
				} else {
					orderedDate = cb.notEqual(root.get(LabEntries_.labEntriesOrdOn), Timestamp.valueOf(orderedOn));
				}
				Predicate checkStatus = cb.equal(root.get(LabEntries_.labEntriesTestStatus), 1);
				query.orderBy(cb.asc(encounterJoin.get(Encounter_.encounterDate)));
				return cb.and(checkPatientId, orderedDate, checkStatus);
			}
		};
	}

	public static Specification<Hl7Unmappedresults> deleteUnmapped(final String hl7Id, final String testName, final Timestamp orderedOn) {
		return new Specification<Hl7Unmappedresults>() {

			@Override
			public Predicate toPredicate(Root<Hl7Unmappedresults> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate nameCheck = cb.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsLabname), testName);
				Predicate hl7IdCheck = cb.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsFilewiseId), hl7Id);
				Predicate orderCheck = cb.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdDate), orderedOn);
				return cb.and(nameCheck, hl7IdCheck, orderCheck);
			}
		};
	}

	public static Specification<FileDetails> verifyFileDetailId(final Integer id) {
		return new Specification<FileDetails>() {

			@Override
			public Predicate toPredicate(Root<FileDetails> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkId = cb.equal(root.get(FileDetails_.filedetailsId), id);
				return checkId;
			}
		};
	}

	public static Specification<FileName> checkScanId(final Integer id) {
		return new Specification<FileName>() {

			@Override
			public Predicate toPredicate(Root<FileName> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkId = cb.equal(root.get(FileName_.filenameScanid), id);
				return checkId;
			}
		};
	}

	public static Specification<FileName> checkFileIsActive() {
		return new Specification<FileName>() {

			@Override
			public Predicate toPredicate(Root<FileName> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate isActive = cb.equal(root.get(FileName_.filenameIsactive), false);
				return isActive;
			}
		};
	}

	public static Specification<FileName> checkFileName(final String fileName) {
		return new Specification<FileName>() {

			@Override
			public Predicate toPredicate(Root<FileName> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate isActive = cb.equal(root.get(FileName_.filenameName), fileName);
				return isActive;
			}
		};
	}

	public static Specification<Hl7Unmappedresults> getUnmappedCount(final int hl7FileId) {
		return new Specification<Hl7Unmappedresults>() {

			@Override
			public Predicate toPredicate(Root<Hl7Unmappedresults> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate fileId = cb.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsFilewiseId), hl7FileId);
				Predicate isActive = cb.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsIsactive), true);
				Predicate mapStatus = cb.or(cb.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsMapStatus), "2"), cb.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsMapStatus), "UnMapped"));
				return cb.and(fileId,isActive,mapStatus);
			}
		};
	}

	public static Specification<FileName> checkFileId(final String fileId) {
		return new Specification<FileName>() {

			@Override
			public Predicate toPredicate(Root<FileName> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate checkFileId = root.get(FileName_.filenameId).in(Integer.parseInt(fileId));
				Predicate isReviewed = cb.notEqual(root.get(FileName_.filenameIsreviewed), true);
				return cb.and(checkFileId, isReviewed);
			}
		};
	}

	public static Specification<Hl7Unmappedresults> getUnreviewedList(final String hl7FileId) {
		return new Specification<Hl7Unmappedresults>() {

			@Override
			public Predicate toPredicate(Root<Hl7Unmappedresults> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<Hl7Unmappedresults, LabEntries> join = root.join(Hl7Unmappedresults_.labEntriesUnmapped, JoinType.INNER);
				join.on(cb.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsIsactive),true));
				Predicate checkFileId = cb.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsFilewiseId),(Integer.parseInt(hl7FileId)));
				Predicate status = cb.lessThanOrEqualTo(join.get(LabEntries_.labEntriesTestStatus), 3);
				return cb.and(checkFileId, status);
			}
		};
	}

	public static Specification<Hl7ResultInbox> getResults(final String hl7FileId) {
		return new Specification<Hl7ResultInbox>() {

			@Override
			public Predicate toPredicate(Root<Hl7ResultInbox> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate fileId = cb.equal(root.get(Hl7ResultInbox_.hl7ResultInboxId), Integer.parseInt(hl7FileId));
				return fileId;
			}
		};
	}
}