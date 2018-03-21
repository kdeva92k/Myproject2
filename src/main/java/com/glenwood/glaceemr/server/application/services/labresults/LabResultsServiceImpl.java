package com.glenwood.glaceemr.server.application.services.labresults;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.Bean.LabEntriesDetailsBean;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.FileDetails;
import com.glenwood.glaceemr.server.application.models.FileDetails_;
import com.glenwood.glaceemr.server.application.models.FileName;
import com.glenwood.glaceemr.server.application.models.FileName_;
import com.glenwood.glaceemr.server.application.models.Hl7DocsInbox;
import com.glenwood.glaceemr.server.application.models.Hl7DocsInbox_;
import com.glenwood.glaceemr.server.application.models.Hl7ResultInbox;
import com.glenwood.glaceemr.server.application.models.Hl7ResultInbox_;
import com.glenwood.glaceemr.server.application.models.Hl7Unmappedresults;
import com.glenwood.glaceemr.server.application.models.Hl7Unmappedresults_;
import com.glenwood.glaceemr.server.application.models.Hl7importCompanies;
import com.glenwood.glaceemr.server.application.models.Hl7importCompanies_;
import com.glenwood.glaceemr.server.application.models.LabEntries;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter_;
import com.glenwood.glaceemr.server.application.models.LabEntries_;
import com.glenwood.glaceemr.server.application.models.LabcompanyDetails;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistrationBean;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PrimarykeyGenerator;
import com.glenwood.glaceemr.server.application.models.PrimarykeyGenerator_;
import com.glenwood.glaceemr.server.application.models.Specimen;
import com.glenwood.glaceemr.server.application.models.Specimen_;
import com.glenwood.glaceemr.server.application.repositories.ChartRepository;
import com.glenwood.glaceemr.server.application.repositories.EmpProfileRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterRepository;
import com.glenwood.glaceemr.server.application.repositories.FileDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.FileNameRepository;
import com.glenwood.glaceemr.server.application.repositories.Hl7DocsInboxRepository;
import com.glenwood.glaceemr.server.application.repositories.Hl7ResultInboxRepository;
import com.glenwood.glaceemr.server.application.repositories.Hl7UnmappedResultsRepository;
import com.glenwood.glaceemr.server.application.repositories.Hl7importCompaniesRepository;
import com.glenwood.glaceemr.server.application.repositories.LabCompanyDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.LabEntriesParameterRepository;
import com.glenwood.glaceemr.server.application.repositories.LabEntriesRepository;
import com.glenwood.glaceemr.server.application.repositories.LabParametersRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditLogConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailService;
import com.glenwood.glaceemr.server.application.services.investigation.InvestigationSummaryService;
import com.glenwood.glaceemr.server.application.services.investigation.InvestigationSummaryServiceImpl;
import com.glenwood.glaceemr.server.application.services.investigation.SaveAttachResultData;
import com.glenwood.glaceemr.server.application.specifications.InvestigationSpecification;
import com.glenwood.glaceemr.server.application.specifications.LabResultsSpecification;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.glenwood.glaceemr.server.utils.SessionMap;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

/**
 * @author yasodha
 * 
 * LabResultsServiceImpl gives the data required for 
 * external lab results
 */
@Service
public class LabResultsServiceImpl implements LabResultsService {

	@Autowired
	EmpProfileRepository empProfileRepository;

	@Autowired
	Hl7ResultInboxRepository resultsRepository;

	@Autowired
	Hl7UnmappedResultsRepository unmappedResultsRepository;

	@Autowired
	FileNameRepository fileNameRepository;

	@Autowired
	ChartRepository chartRepository;

	@Autowired
	ResultParametersService parametersService;

	@Autowired
	PatientRegistrationRepository registrationRepository;

	@Autowired
	Hl7DocsInboxRepository docsInboxRepository;

	@Autowired
	LabParametersRepository labParametersRepository;

	@Autowired
	LabEntriesRepository labEntriesRepository;

	@Autowired
	LabEntriesParameterRepository labEntriesParametersRepository;

	@Autowired
	InvestigationSummaryService investigationService;

	@Autowired
	LabDocsService labDocsService;

	@Autowired
	EntityManagerFactory emf ;

	@Autowired
	AuditTrailService auditTrailService;

	@Autowired
	SessionMap sessionMap;

	@Autowired
	HttpServletRequest request;
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	FileDetailsRepository fileDetailsRepository;
	
	@Autowired
	Hl7importCompaniesRepository hl7ImportCompaniesRepository;
	
	@Autowired
	EncounterRepository encounterRepository;
	
	@Autowired
	LabCompanyDetailsRepository labCompanyDetailsRepository;


	private Logger logger = Logger.getLogger(InvestigationSummaryServiceImpl.class);
	String rootPath;
	/**
	 * Method to get list of users
	 * @throws Exception
	 */
	@Override
	public List<UsersList> getListOfUsers() throws Exception {
		logger.debug("in finding users list");
		List<UsersList> listOfUsers = new ArrayList<UsersList>();
		List<EmployeeProfile> users = empProfileRepository.findAll(Specifications.where(LabResultsSpecification.getActiveUser()).and(LabResultsSpecification.verifyFullName("Demo")).and(LabResultsSpecification.verifyGroupId()));
		auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.LoginAndLogOut,
				AuditLogConstants.LOGIN,1,AuditLogConstants.SUCCESS,"Sucessfull login User Name(" + 1 +")",-1,
				"127.0.0.1",request.getRemoteAddr(),-1,-1,-1,
				AuditLogConstants.USER_LOGIN,request,"User (" + sessionMap.getUserID() + ") logged in through SSO");
		for (int i = 0; i < users.size(); i++) {
			UsersList usersList = new UsersList();
			EmployeeProfile empData = users.get(i);
			usersList.setUserId("" + empData.getEmpProfileEmpid());
			usersList.setUserName(empData.getEmpProfileFullname());
			usersList.setGroupId("" + empData.getEmpProfileGroupid());
			if( empData.getEmpProfileGroupid() == -1 ) {
				usersList.setGroupName("Doctors");
			} else {
				usersList.setGroupName("Nurse Practitioners");
			}
			usersList.setPriority("1");
			listOfUsers.add(usersList);
		}
		UsersList unknownUser = new UsersList();
		unknownUser.setUserId("-1");
		unknownUser.setUserName("Unknown Provider(s)");
		unknownUser.setGroupId("-1");
		unknownUser.setGroupName("Doctors");
		unknownUser.setPriority("2");
		listOfUsers.add(unknownUser);
		return listOfUsers;
	}

	/**
	 * Method to get list of results
	 * @param doctorId
	 * @param isReviewed
	 * @param orderedDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ResultList> getListOfResults(String doctorId, String isReviewed, String orderedDate, Integer pageNo,Integer pageSize) {
		ArrayList<Integer> list = new ArrayList<Integer>();

		try{
		for (int j = 0; j < doctorId.split(",").length; j++) {
			list.add(Integer.parseInt( doctorId.split(",")[j]));
		}		
		CriteriaBuilder cbuilder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cqry = cbuilder.createQuery();
		Root<Hl7ResultInbox> rootHl7ResultInbox = cqry.from(Hl7ResultInbox.class);
		
		Predicate activeResult = cbuilder.equal(rootHl7ResultInbox.get(Hl7ResultInbox_.hl7ResultInboxIsactive), true);
		
		Predicate reviewedResult = cbuilder.equal(rootHl7ResultInbox.get(Hl7ResultInbox_.hl7ResultInboxReviewed), Integer.parseInt(isReviewed));
		
		Join<Hl7ResultInbox, Hl7Unmappedresults> resultsJoin = rootHl7ResultInbox.join(Hl7ResultInbox_.hl7UnmappedResults,JoinType.INNER);
		@SuppressWarnings("unused")
		Join<Hl7ResultInbox, PatientRegistration> patJoin = rootHl7ResultInbox.join(Hl7ResultInbox_.patientRegistration,JoinType.LEFT);
		
		Predicate hl7unmapResActive = cbuilder.equal(resultsJoin.get(Hl7Unmappedresults_.hl7UnmappedresultsIsactive), true);
		resultsJoin.on(hl7unmapResActive);
		
		Predicate checkOrdBy = resultsJoin.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdbyDocid).in(list);
		cqry.where(activeResult,reviewedResult,checkOrdBy,hl7unmapResActive);
		cqry.multiselect(cbuilder.construct(Hl7ResultInbox.class,
				rootHl7ResultInbox.get(Hl7ResultInbox_.hl7ResultInboxId),
				cbuilder.coalesce(rootHl7ResultInbox.get(Hl7ResultInbox_.hl7ResultInboxAccountno),cbuilder.literal("")),
				cbuilder.coalesce(rootHl7ResultInbox.get(Hl7ResultInbox_.hl7ResultInboxFirstname),cbuilder.literal("")),
				cbuilder.coalesce(rootHl7ResultInbox.get(Hl7ResultInbox_.hl7ResultInboxLastname),cbuilder.literal("")),
				rootHl7ResultInbox.get(Hl7ResultInbox_.hl7ResultInboxStatus),
				cbuilder.coalesce(rootHl7ResultInbox.get(Hl7ResultInbox_.hl7ResultInboxFilename),cbuilder.literal("")),
				rootHl7ResultInbox.get(Hl7ResultInbox_.hl7ResultInboxReviewed),
				rootHl7ResultInbox.get(Hl7ResultInbox_.hl7ResultInboxDob),
				rootHl7ResultInbox.get(Hl7ResultInbox_.hl7ResultInboxPlacedDate)));
		cqry.distinct(true);
		cqry.orderBy(cbuilder.desc(rootHl7ResultInbox.get(Hl7ResultInbox_.hl7ResultInboxPlacedDate)));
		Query query=em.createQuery(cqry);
		if(pageSize != -1){
			query.setFirstResult((pageNo-1)*pageSize);	// Page no (offset)
			query.setMaxResults(pageSize);		// Page size (No of results)
		}
		@SuppressWarnings("unchecked")
		List<Object> obj=query.getResultList();
		
		List<Hl7ResultInbox> resultInboxList = new ArrayList<Hl7ResultInbox>();
		for(int i=0;i<obj.size();i++){
			Hl7ResultInbox hl7Results = (Hl7ResultInbox) obj.get(i);
			Hl7ResultInbox hl7ResultsData = new Hl7ResultInbox(hl7Results.getHl7ResultInboxId(),
					hl7Results.getHl7ResultInboxAccountno(),
					hl7Results.getHl7ResultInboxFirstname(),
					hl7Results.getHl7ResultInboxLastname(),
					hl7Results.getHl7ResultInboxStatus(),
					hl7Results.getHl7ResultInboxFilename(),
					hl7Results.getHl7ResultInboxReviewed(),
					hl7Results.getHl7ResultInboxDob(),
					hl7Results.getHl7ResultInboxPlacedDate());
			PatientRegistration patReg = new PatientRegistration();
			hl7ResultsData.setPatientRegistration(patReg);
			resultInboxList.add(hl7ResultsData);
		}
		
		List<ResultList> resultData = new ArrayList<ResultList>();
		for (int i = 0; i < resultInboxList.size(); i++) {
			ResultList results = new ResultList();
			Hl7ResultInbox inboxData = resultInboxList.get(i);
			if( inboxData.getHl7ResultInboxStatus() == 3 ) {
				results.setPatientId("-1");
			} else {
				PatientRegistration patReg = inboxData.getPatientRegistration();
				if( patReg != null ) {
					results.setPatientId("" + patReg.getPatientRegistrationId());
				} else {
					results.setPatientId("-1");	
				}
			}
			results.setAccountNo(inboxData.getHl7ResultInboxAccountno());
			results.setPatientName(inboxData.getHl7ResultInboxLastname() + ", " + inboxData.getHl7ResultInboxFirstname());
			results.setResultInboxId("" + inboxData.getHl7ResultInboxId());
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			results.setReceivedDate(formatter.format(inboxData.getHl7ResultInboxPlacedDate()));
			results.setDob(formatter.format(inboxData.getHl7ResultInboxDob()));
			results.setInboxStatus("" + inboxData.getHl7ResultInboxStatus());
			if( inboxData.getHl7ResultInboxStatus() == 1 ) {
				results.setCategoryName("Attached");
			} else if( inboxData.getHl7ResultInboxStatus() == 2 ) {
				results.setCategoryName("Unattached");
			} else {
				results.setCategoryName("Unknown");
			}
			List<Hl7UnmappedresultsBean> unmappedResultsList = getHl7UnmappedResults(inboxData.getHl7ResultInboxId());
			for (int j = 0; j < unmappedResultsList.size(); j++) {
				Hl7UnmappedresultsBean unmappedResults = (Hl7UnmappedresultsBean) unmappedResultsList.get(j);
				results.setOrderedBy(unmappedResults.getHl7UnmappedresultsOrdbyLastname() + ", " + unmappedResults.getHl7UnmappedresultsOrdbyFirstname());
				results.setOrderedDate(formatter.format(unmappedResults.getHl7UnmappedresultsOrdDate()));
			}
			results.setFileName(inboxData.getHl7ResultInboxFilename());
			results.setDocInboxId("-1");
			results.setIsReviewed("" + inboxData.getHl7ResultInboxReviewed());
			results.setIsDocument("0");
			results.setFileNameId("-1");
			resultData.add(results);
		}
		boolean reviewed = false;
		if( isReviewed.equals("1")) {
			reviewed = true;
		} else {
			reviewed = false;
		}
		List<Hl7DocsInbox> docsInboxList = docsInboxRepository.findAll(Specifications.where(Specifications.where(LabResultsSpecification.checkFileReviewed(reviewed)).and(LabResultsSpecification.checkPatientId())).and(LabResultsSpecification.verifyDocProvider(list)));
		for (int i = 0; i < docsInboxList.size(); i++) {
			Hl7DocsInbox docsData = docsInboxList.get(i);
			ResultList results = new ResultList();
			results.setDocInboxId("" + docsData.getHl7DocsInboxId());
			results.setFileName(docsData.getHl7DocsInboxFilename());
			if( docsData.getHl7DocsInboxPatientid() != null ) {
				results.setPatientId("" + docsData.getHl7DocsInboxPatientid());
			} else {
				results.setPatientId("-1");
			}
			results.setAccountNo(docsData.getHl7DocsInboxAccountno());
			results.setPatientName(docsData.getHl7DocsInboxLastname() + ", " + docsData.getHl7DocsInboxFirstname());
			results.setResultInboxId("-1");
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			results.setReceivedDate(formatter.format(docsData.getHl7DocsInboxPlacedDate()));
			results.setDob(formatter.format(docsData.getHl7DocsInboxDob()));
			if( docsData.getFileDetails() != null ) {
				FileDetails fileDetails = docsData.getFileDetails();
				if( !Optional.fromNullable(Strings.emptyToNull("" + fileDetails.getFiledetailsId())).or("-1").equals("-1") ) {
					results.setInboxStatus("4");
					results.setCategoryName("Attached Docs");
					results.setFileDetailId("" + fileDetails.getFiledetailsId());
				} else {
					results.setInboxStatus("5");
					results.setCategoryName("Unknown Docs");
					results.setFileDetailId("-1");
				}
				List<FileName> fileName = fileDetails.getFileName();
				for(i=0;i<fileName.size();i++){
					results.setFileNameId("" + fileName.get(i).getFilenameId());
					if( fileName.get(i).getFilenameIsreviewed().equals(true) ) {
						results.setIsReviewed("1");
					} else {
						results.setIsReviewed("0");
					}	
				}
			} else {
				results.setInboxStatus("5");
				results.setCategoryName("Unknown Docs");
				results.setIsReviewed("0");
				results.setFileDetailId("-1");
				results.setFileNameId("-1");
			}
			results.setOrderedBy(docsData.getHl7DocsInboxProviderName());
			if(docsData.getHl7DocsInboxOrdDate()!=null && !docsData.getHl7DocsInboxOrdDate().toString().equalsIgnoreCase(""))
				results.setOrderedDate(formatter.format(docsData.getHl7DocsInboxOrdDate()));
			results.setIsDocument("1");
			resultData.add(results);
		}
		for (int i = 0; i < resultData.size(); i++) {
			ResultList results = resultData.get(i);
			if( !results.getResultInboxId().equals("-1") ) {
				List<Object> testIds = getTestIds(results.getResultInboxId());
				results.setIsAbnormal(getAbnormalStatus(testIds));
			} else {
				results.setIsAbnormal("0");
			}
		}
		return resultData;
	}catch(Exception e) {
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.VIEW, -1,Log_Outcome.FAILURE,"Failure in getting the list of results received for the user" , -1,
					request.getRemoteAddr() , -1 , "doctorId="+doctorId,LogUserType.USER_LOGIN ,"","");
			return null;
		} finally {
			em.close();
		}
	}

	private List<Hl7UnmappedresultsBean> getHl7UnmappedResults(Integer hl7ResultInboxId) {
		try{

			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7Unmappedresults> root = cq.from(Hl7Unmappedresults.class);
			cq.select(builder.construct(Hl7UnmappedresultsBean.class, root.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdbyFirstname),
					root.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdbyLastname),
					root.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdDate),
					root.get(Hl7Unmappedresults_.hl7UnmappedresultsTestdetailId)))
					.where(builder.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsFilewiseId),hl7ResultInboxId));

			List<Hl7UnmappedresultsBean> resData= new ArrayList<Hl7UnmappedresultsBean>(); 
			List<Object> resultList = em.createQuery(cq).getResultList();
			for(int i=0;i<resultList.size();i++){
				Hl7UnmappedresultsBean hl7unmappedresults = (Hl7UnmappedresultsBean) resultList.get(i);
				Hl7UnmappedresultsBean resultsData = new Hl7UnmappedresultsBean(hl7unmappedresults.getHl7UnmappedresultsOrdbyFirstname(),
						hl7unmappedresults.getHl7UnmappedresultsOrdbyLastname(),
						hl7unmappedresults.getHl7UnmappedresultsOrdDate(),
						hl7unmappedresults.getHl7UnmappedresultsTestDetailId());
				resData.add(resultsData);
			}
			return resData;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}

	}

	/**
	 * Method to get abnormal status
	 * @param testIds
	 * @return
	 */
	private String getAbnormalStatus(List<Object> testIds) {
		String isAbnormal = "0";
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<LabEntriesParameter> root = cq.from(LabEntriesParameter.class);
			cq.select(root.get(LabEntriesParameter_.labEntriesParameterStatus));
			cq.where(root.get(LabEntriesParameter_.labEntriesParameterTestdetailid).in(testIds));
			List<Object> statusList = em.createQuery(cq).getResultList();
			for (int i = 0; i < statusList.size(); i++) {
				if( statusList.get(i) != null ) {
					String paramStatus = statusList.get(i).toString();
					if(paramStatus.equalsIgnoreCase("H")|| paramStatus.equalsIgnoreCase("HH") || paramStatus.equalsIgnoreCase("L")|| paramStatus.equalsIgnoreCase("A") || paramStatus.equalsIgnoreCase("LL") || paramStatus.equalsIgnoreCase("<")|| paramStatus.equalsIgnoreCase(">") || paramStatus.equalsIgnoreCase("S")|| paramStatus.equalsIgnoreCase("R") || paramStatus.equalsIgnoreCase("I") || paramStatus.equalsIgnoreCase("HI") || paramStatus.equalsIgnoreCase("LOW")) {
						isAbnormal = "1";
						return isAbnormal;
					}
				}
			}
			return isAbnormal;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Method to get testId
	 * @param hl7Id
	 * @return
	 */
	private List<Object> getTestIds(String hl7Id) {
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7Unmappedresults> root = cq.from(Hl7Unmappedresults.class);
			cq.select(root.get(Hl7Unmappedresults_.hl7UnmappedresultsTestdetailId));
			cq.where(builder.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsFilewiseId),Integer.parseInt(hl7Id)));
			List<Object> resultList = em.createQuery(cq).getResultList();
			return resultList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Service method to get patient result details
	 * @param hl7FileId
	 * @return
	 */
	@Override
	public ResultDetails getPatientResultData(String hl7FileId) {
		List<Hl7ResultInbox> resultInboxList=checkHl7Id(hl7FileId);
		ResultDetails patientResults = new ResultDetails();
		setPatientResults(patientResults, resultInboxList,hl7FileId);		
		return patientResults;
	}

	public List<Hl7ResultInbox> checkHl7Id(String hl7FileId) {

		List<Hl7ResultInbox> beanList=new ArrayList<Hl7ResultInbox>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Hl7ResultInbox> root = cq.from(Hl7ResultInbox.class);
		Predicate verifyId = root.get(Hl7ResultInbox_.hl7ResultInboxId).in(Integer.parseInt(hl7FileId));
		Predicate verifyIsActive = builder.equal(root.get(Hl7ResultInbox_.hl7ResultInboxIsactive), true);	
		cq.select(builder.construct(Hl7ResultInbox.class,root.get(Hl7ResultInbox_.hl7ResultInboxAccountno),
				root.get(Hl7ResultInbox_.hl7ResultInboxId),
				root.get(Hl7ResultInbox_.hl7ResultInboxFirstname),
				root.get(Hl7ResultInbox_.hl7ResultInboxLastname),
				root.get(Hl7ResultInbox_.hl7ResultInboxStatus),
				root.get(Hl7ResultInbox_.hl7ResultInboxPlacedDate),
				root.get(Hl7ResultInbox_.hl7ResultInboxReviewed),
				root.get(Hl7ResultInbox_.hl7ResultInboxPrelimresultId),
				root.get(Hl7ResultInbox_.hl7ResultInboxLabaccessionno),
				root.get(Hl7ResultInbox_.hl7ResultInboxPlacerorderno),
				root.get(Hl7ResultInbox_.hl7ResultInboxPlacergroupno),
				root.get(Hl7ResultInbox_.hl7ResultInboxDocumentid),
				root.get(Hl7ResultInbox_.hl7ResultInboxLabcompanyid)));
		cq.where(verifyId,verifyIsActive);
		cq.orderBy(builder.asc(root.get(Hl7ResultInbox_.hl7ResultInboxId)));

		List<Object> inboxList=em.createQuery(cq).getResultList();
		for(int i=0;i<inboxList.size();i++){
			Hl7ResultInbox eachObj=(Hl7ResultInbox) inboxList.get(i);
			beanList.add(eachObj);
		}
		return beanList;
	}


	private void setPatientResults(ResultDetails patientResults, List<Hl7ResultInbox> resultInboxList,String hl7FileId) {
		
		for (int i = 0; i < resultInboxList.size(); i++) {
			Hl7ResultInbox resultInbox = resultInboxList.get(i);
			patientResults.setPatientId(getPatientId(resultInbox.getHl7ResultInboxAccountno()));
			patientResults.setHl7Id("" + resultInbox.getHl7ResultInboxId());
			patientResults.setAccountNo(resultInbox.getHl7ResultInboxAccountno());
			patientResults.setPatientName(resultInbox.getHl7ResultInboxLastname() + ", " + resultInbox.getHl7ResultInboxFirstname());
			patientResults.setStatus("" + resultInbox.getHl7ResultInboxStatus());
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			patientResults.setPlacedDate(formatter.format(resultInbox.getHl7ResultInboxPlacedDate()));
			patientResults.setIsReviewed("" + resultInbox.getHl7ResultInboxReviewed());
			patientResults.setPrelimResultId("" + resultInbox.getHl7ResultInboxPrelimresultId());
			patientResults.setLabAccessionNo(Optional.fromNullable(Strings.emptyToNull("" + resultInbox.getHl7ResultInboxLabaccessionno())).or(""));
			patientResults.setPlacerOrderNo(Optional.fromNullable(Strings.emptyToNull("" + resultInbox.getHl7ResultInboxPlacerorderno())).or(""));
			patientResults.setPlacerGroupNo(Optional.fromNullable(Strings.emptyToNull("" + resultInbox.getHl7ResultInboxPlacergroupno())).or(""));
			Hl7importCompanies importCompanies =hl7ImportCompaniesRepository.findOne(resultInbox.getHl7ResultInboxLabcompanyid());
			patientResults.setLabCompany(Optional.fromNullable(Strings.emptyToNull("" + importCompanies.getLabcompanyname())).or(""));
			patientResults.setLabCompanyId(Optional.fromNullable(Strings.emptyToNull("" + importCompanies.getId())).or("-1"));

			List<Hl7UnmappedresultsBean> unmappedResultsList = gethl7UnmappedresultsDataList(hl7FileId,"",0);
			List<UnmappedResults> unmappedList = new ArrayList<UnmappedResults>();
			for (int j = 0; j < unmappedResultsList.size(); j++) {
				UnmappedResults unmapped = new UnmappedResults();
				Hl7UnmappedresultsBean unmappedResults = unmappedResultsList.get(j);
				unmapped.setUnMappedId("" + unmappedResults.getHl7UnmappedresultsId());
				unmapped.setDelOrdDate("" + unmappedResults.getHl7UnmappedresultsOrdDate());
				unmapped.setOrderedDate(formatter.format(unmappedResults.getHl7UnmappedresultsOrdDate()));
				if(unmappedResults.getHl7UnmappedresultsPerformeddate() !=null){
					unmapped.setPerformedDate(formatter.format(unmappedResults.getHl7UnmappedresultsPerformeddate()));
				}else{
					unmapped.setPerformedDate(formatter.format(unmappedResults.getHl7UnmappedresultsOrdDate()));
				}
				String empProfileName=getEmpProfileFullName(String.valueOf(unmappedResults.getHl7UnmappedresultsOrdbyDocid()));

				if( unmappedResults.getHl7UnmappedresultsOrdbyDocid() == -1) {
					unmapped.setOrderBy(unmappedResults.getHl7UnmappedresultsOrdbyFirstname() + " " + unmappedResults.getHl7UnmappedresultsOrdbyLastname());	
				} else {
					unmapped.setOrderBy(empProfileName);
				}
				unmapped.setTestName(unmappedResults.getHl7UnmappedresultsLabname());
				unmapped.setResultXML(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsResult())).or(""));
				unmapped.setTestDetailId(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsTestDetailId())).or("-1"));
				LabEntriesDetailsBean labEntries=getLabEntriesData(unmappedResults.getHl7UnmappedresultsTestDetailId());
				if( labEntries!= null ) {
					unmapped.setTestStatus(Optional.fromNullable(Strings.emptyToNull("" + labEntries.getLabEntriesTestStatus())).or("" + unmappedResults.getHl7UnmappedresultsTestStatus()));
					unmapped.setPrelimStatus(Optional.fromNullable(Strings.emptyToNull("" + labEntries.getLabEntriesPrelimTestStatus())).or("0"));
					unmapped.setFinalStatus(Optional.fromNullable(Strings.emptyToNull("" + labEntries.getLabEntriesConfirmTestStatus())).or("0"));
					unmapped.setTestNotes(Optional.fromNullable(Strings.emptyToNull("" + labEntries.getLabEntriesResultNotes())).or("" + unmappedResults.getHl7UnmappedresultsTestnotes()));
					if( labEntries.getLabEntriesRevOn() != null ) {
						unmapped.setReviewedOn(Optional.fromNullable(Strings.emptyToNull("" + formatter.format(labEntries.getLabEntriesRevOn()))).or(""));
						String reviewedBy=labEntries.getLabEntriesRevBy().toString();
						String empProfileFullName=getEmpProfileFullName(reviewedBy);
						unmapped.setReviewedBy(empProfileFullName);
					}
					unmapped.setMapStatus(unmappedResults.getHl7UnmappedresultsMapStatus());
					unmapped.setResultStatus(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsResultStatus())).or(""));
					String collectionDate = "";
					String specimenDate = "";
					if( labEntries.getLabEntriesSepcimenId()!=null &&  labEntries.getLabEntriesSepcimenId() != -1 ) {
						Specimen specimen = getSpecimenInfo(labEntries.getLabEntriesSepcimenId());
						if( specimen != null ) {
							unmapped.setSpecimenSource(Optional.fromNullable(Strings.emptyToNull("" + specimen.getSpecimenSource())).or(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsSrcOfSpecimen())).or("")));
							unmapped.setSpecimenCondition(Optional.fromNullable(Strings.emptyToNull("" + specimen.getSpecimenCondition())).or(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsCondOfSpecimen())).or("")));
							if( unmappedResults.getHl7UnmappedresultsSpecimenCollectedDate() != null ) {
								collectionDate = formatter.format(unmappedResults.getHl7UnmappedresultsSpecimenCollectedDate());
							}
							if( specimen.getSpecimenDate() != null ) {
								specimenDate = formatter.format(specimen.getSpecimenDate());
							}
							unmapped.setSpecimenCollectedDate(Optional.fromNullable(Strings.emptyToNull(specimenDate)).or(Optional.fromNullable(Strings.emptyToNull(collectionDate)).or("")));
						} 
					} else {
						unmapped.setSpecimenSource(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsSrcOfSpecimen())).or(""));
						unmapped.setSpecimenCondition(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsCondOfSpecimen())).or(""));
						if( unmappedResults.getHl7UnmappedresultsSpecimenCollectedDate() != null ) {
							unmapped.setSpecimenCollectedDate(Optional.fromNullable(Strings.emptyToNull("" + formatter.format(unmappedResults.getHl7UnmappedresultsSpecimenCollectedDate()))).or(""));
						} else {
							unmapped.setSpecimenCollectedDate("");
						}
					}
					String fileDetailCategoryId=getFileDetailsList(labEntries.getLabEntriesTestdetailId());
					if( fileDetailCategoryId != null ) {
						unmapped.setCategoryId("" + Optional.fromNullable(Strings.emptyToNull(fileDetailCategoryId)).or("-1"));
					} else {
						unmapped.setCategoryId("-1");
					}
				} else {
					unmapped.setTestStatus(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsTestStatus())).or("" + unmappedResults.getHl7UnmappedresultsTestStatus()));
					unmapped.setPrelimStatus(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsPrelimStatus())).or("0"));
					unmapped.setFinalStatus(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsFinalStatus())).or("0"));
					unmapped.setTestNotes(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsTestnotes())).or("" + unmappedResults.getHl7UnmappedresultsTestnotes()));
					unmapped.setReviewedOn("");	
					unmapped.setMapStatus(unmappedResults.getHl7UnmappedresultsMapStatus());
					unmapped.setResultStatus(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsResultStatus())).or(""));
					unmapped.setSpecimenSource(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsSrcOfSpecimen())).or(""));
					unmapped.setSpecimenCondition(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsCondOfSpecimen())).or(""));
					if( unmappedResults.getHl7UnmappedresultsSpecimenCollectedDate() != null ) {
						unmapped.setSpecimenCollectedDate(Optional.fromNullable(Strings.emptyToNull("" + formatter.format(unmappedResults.getHl7UnmappedresultsSpecimenCollectedDate()))).or(""));
					} else {
						unmapped.setSpecimenCollectedDate("");
					}
					unmapped.setCategoryId("-1");
				}
				
				unmapped.setSpecimenRejectReason(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsSpecimenRejectreason())).or(""));
				unmapped.setResultsCopiesTo(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsResultscopiesto())).or(""));
				unmapped.setRelevantClinicalInfo(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsRelevantClinicalInfo())).or(""));
				unmapped.setPrelimTestId("" + unmappedResults.getHl7UnmappedresultsPrelimtestId());
				unmapped.setLocCompDetId("" + unmappedResults.getHl7UnmappedresultsLabcompDetailid());
				unmapped.setIsPDF(Optional.fromNullable(Strings.emptyToNull("" + unmappedResults.getHl7UnmappedresultsIspdf())).or("0"));
				
				LabcompanyDetails compDetails =labCompanyDetailsRepository.findOne(unmappedResults.getHl7UnmappedresultsLabcompDetailid());							
				if( compDetails != null ) {
					unmapped.setLabCompName(Optional.fromNullable(Strings.emptyToNull("" + compDetails.getLabcompanyDetailsLabname())).or(""));
					unmapped.setLabCompDirector(Optional.fromNullable(Strings.emptyToNull("" + compDetails.getLabcompanyDetailsDirectorName())).or(""));
					unmapped.setLabCompAddr(Optional.fromNullable(Strings.emptyToNull("" + compDetails.getLabcompanyDetailsLabaddress())).or(""));
					unmapped.setLabCompCity(Optional.fromNullable(Strings.emptyToNull("" + compDetails.getLabcompanyDetailsLabcity())).or(""));
					unmapped.setLabCompState(Optional.fromNullable(Strings.emptyToNull("" + compDetails.getLabcompanyDetailsLabstate())).or(""));
					unmapped.setLabCompZip(Optional.fromNullable(Strings.emptyToNull("" + compDetails.getLabcompanyDetailsLabzip())).or(""));
				}
				List<Parameters> map = new ArrayList<Parameters>();
				try {
					parametersService.getParameterValues(unmapped.getResultXML(), map, unmapped.getTestDetailId());
					unmapped.setParameters(map);
					unmappedList.add(unmapped);
				} catch (Exception e) {
					e.printStackTrace();
					auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.VIEW, -1,Log_Outcome.FAILURE,"Failure in getting the complete details of the received result" , -1,
							request.getRemoteAddr() , -1 , "hl7FileId="+hl7FileId,LogUserType.USER_LOGIN ,"","");
				}
			}
			patientResults.setDocumentId(Optional.fromNullable(Strings.emptyToNull("" + resultInbox.getHl7ResultInboxDocumentid())).or("-1"));
			patientResults.setUnmappedResults(unmappedList);
		}
		
	}

	private String getFileDetailsList(Integer labEntriesTestdetailId) {

		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();	
			Root<FileDetails> root = cq.from(FileDetails.class);
			cq.select(root.get(FileDetails_.filedetailsCategoryid));
			Predicate fileEntityId =builder.equal(root.get(FileDetails_.filedetailsEntityid),labEntriesTestdetailId);
			cq.where(fileEntityId);
			String fileDetailsCategoryId = "" + em.createQuery(cq).getFirstResult();
			return fileDetailsCategoryId;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	private Specimen getSpecimenInfo(Integer labEntriesSepcimenId) {

		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();	
			Root<Specimen> root = cq.from(Specimen.class);
			cq.multiselect(builder.construct(Specimen.class, root.get(Specimen_.specimenSource)),
					root.get(Specimen_.specimenCondition),
					root.get(Specimen_.specimenDate));
			Predicate specimenId =builder.equal(root.get(Specimen_.specimenId),labEntriesSepcimenId);
			cq.where(specimenId);
	
			List<Object> specimenList=em.createQuery(cq).getResultList();
			Specimen eachObj=new Specimen();
			if(!specimenList.isEmpty())
				eachObj=(Specimen) specimenList.get(0);
			return eachObj;			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}
	
	private LabEntriesDetailsBean getLabEntriesData(Integer hl7UnmappedresultsTestDetailId) {

		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();	
			Root<LabEntries> root = cq.from(LabEntries.class);
			cq.multiselect(builder.construct(LabEntriesDetailsBean.class,root.get(LabEntries_.labEntriesTestStatus),
					root.get(LabEntries_.labEntriesPrelimTestStatus),
					root.get(LabEntries_.labEntriesConfirmTestStatus),
					root.get(LabEntries_.labEntriesResultNotes),
					root.get(LabEntries_.labEntriesRevOn),
					root.get(LabEntries_.labEntriesRevBy),
					root.get(LabEntries_.labEntriesSepcimenId),
					root.get(LabEntries_.labEntriesTestdetailId)));
			Predicate testDetailId =builder.equal(root.get(LabEntries_.labEntriesTestdetailId),hl7UnmappedresultsTestDetailId);
			cq.where(testDetailId);
			List<Object> resultList=em.createQuery(cq).getResultList();
			LabEntriesDetailsBean eachObj=new LabEntriesDetailsBean();
			if(!resultList.isEmpty())
				eachObj=(LabEntriesDetailsBean) resultList.get(0);
			return eachObj;
		}  catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}


	private String getEmpProfileFullName(String reviewedBy) {

		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();		
			Root<EmployeeProfile> root = cq.from(EmployeeProfile.class);
			Predicate isActive = builder.equal(root.get(EmployeeProfile_.empProfileIsActive), true);	
			Predicate reviewed = root.get(EmployeeProfile_.empProfileEmpid).in(reviewedBy);
			cq.select(root.get(EmployeeProfile_.empProfileFullname));
			cq.where(isActive,reviewed);
			String empFullName = "" ;
			List<Object> resultList = em.createQuery(cq).getResultList();
			for (int i = 0; i < resultList.size(); i++) {
				empFullName = resultList.get(i).toString();
			}
			return empFullName;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	public List<Hl7UnmappedresultsBean> gethl7UnmappedresultsDataList(
			String hl7FileId,String testName,int value) {

		List<Hl7UnmappedresultsBean> beanList=new ArrayList<Hl7UnmappedresultsBean>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Hl7Unmappedresults> root = cq.from(Hl7Unmappedresults.class);
		Join<Hl7Unmappedresults, Hl7ResultInbox> unmappedJoin = root.join(Hl7Unmappedresults_.hl7ResultInbox, JoinType.INNER);
		unmappedJoin.on(builder.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsIsactive), true));	
		Predicate verifyId = unmappedJoin.get(Hl7ResultInbox_.hl7ResultInboxId).in(Integer.parseInt(hl7FileId));
		Predicate verifyIsActive = builder.equal(unmappedJoin.get(Hl7ResultInbox_.hl7ResultInboxIsactive), true);
		Predicate nameCheck = builder.like(root.get(Hl7Unmappedresults_.hl7UnmappedresultsLabname), testName);
		cq.select(builder.construct(Hl7UnmappedresultsBean.class, root.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdbyFirstname),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdbyLastname),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdDate),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsTestdetailId),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsId),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsPerformeddate),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdbyDocid),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsLabname),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsResult),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsMapStatus),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsResultStatus),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsSpecimenCollectedDate),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsSrcOfSpecimen),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsCondOfSpecimen),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsTestStatus),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsPrelimStatus),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsFinalStatus),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsTestnotes),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsSpecimenRejectreason),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsResultscopiesto),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsRelevantClinicalInfo),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsPrelimtestId),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsLabcompDetailid),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsIspdf),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsExtTestcode),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsFilename),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsRequisitionId),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsIsfasting),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsCollectionVolume),
				root.get(Hl7Unmappedresults_.hl7UnmappedresultsFilewiseId)
				));
        if(value==1){
        	cq.where(verifyId,verifyIsActive,nameCheck);
        }else{
        	cq.where(verifyId,verifyIsActive);
        }
		cq.orderBy(builder.asc(root.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdDate)),builder.asc(unmappedJoin.get(Hl7ResultInbox_.hl7ResultInboxId)),builder.asc(root.get(Hl7Unmappedresults_.hl7UnmappedresultsId)));

		List<Object> inboxList=em.createQuery(cq).getResultList();
		for(int i=0;i<inboxList.size();i++){
			Hl7UnmappedresultsBean eachObj=(Hl7UnmappedresultsBean) inboxList.get(i);
			beanList.add(eachObj);
		}

		return beanList;
	}


	/**
	 * Method to get patient id
	 * @param accountNo
	 * @return
	 */
	private String getPatientId(String accountNo) {
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<PatientRegistration> root = cq.from(PatientRegistration.class);
			Join<PatientRegistration, Hl7ResultInbox> resultJoin = root.join(PatientRegistration_.hl7ResultInbox,JoinType.INNER);
			cq.select(root.get(PatientRegistration_.patientRegistrationId));
			cq.where(builder.equal(resultJoin.get(Hl7ResultInbox_.hl7ResultInboxAccountno),accountNo));
			cq.distinct(true);
			String patientId = "";
			List<Object> resultList = em.createQuery(cq).getResultList();
			for (int i = 0; i < resultList.size(); i++) {
				patientId = resultList.get(i).toString();
			}
			return patientId;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Method to attach result to patient
	 * @param hl7FileId
	 * @param patientId
	 * @param userId
	 */
	@SuppressWarnings("unused")
	@Override
	public void attachToPatient(String hl7FileId, String patientId,	String userId, String sharedFolderPath) {
		this.rootPath = sharedFolderPath;
		String chartId = getChartId(patientId);
		String patientAccNo = "";
		if( chartId.equals("-1") ) {
			Chart chart = new Chart();
			chart.setChartId(-1);
			chart.setChartUnknown1("");
			chart.setChartPatientid(Integer.parseInt(patientId));
			chart.setChartCreatedby(-100);
			Date date = new Date();
			chart.setChartCreateddate(new Timestamp(date.getTime()));
			chart.setChartAlreadyseen(false);
			chartRepository.save(chart);
			chartId = getPatientChart();
		}
		int documentId = getDocumentId(hl7FileId);
		if( documentId != -1 ) {
			List<PatientRegistration> patientDataList = registrationRepository.findAll(LabResultsSpecification.getPatientData(patientId));
			if( patientDataList.size() > 0 ) {
				PatientRegistration patientData = patientDataList.get(0);
				String patientFName = patientData.getPatientRegistrationFirstName();
				String patientLName = patientData.getPatientRegistrationLastName();
				patientAccNo = patientData.getPatientRegistrationAccountno();
				List<Hl7DocsInbox> docsList = docsInboxRepository.findAll(LabResultsSpecification.getDocsData(documentId));
				for(Hl7DocsInbox docsData : docsList) {
					docsData.setHl7DocsInboxAccountno(patientAccNo);
					docsData.setHl7DocsInboxLastname(patientLName);
					docsData.setHl7DocsInboxFirstname(patientFName);
					docsData.setHl7DocsInboxDob(patientData.getPatientRegistrationDob());
					docsData.setHl7DocsInboxPatientid(Integer.parseInt(patientId));
					docsInboxRepository.saveAndFlush(docsData);
				}
				List<Hl7Unmappedresults> results = unmappedResultsRepository.findAll(LabResultsSpecification.verifyFileIdAndStatus(hl7FileId, "UnMapped"));
				for( Hl7Unmappedresults unmapped : results ) {
					unmapped.setHl7UnmappedresultsAccountno(patientAccNo);
					unmappedResultsRepository.saveAndFlush(unmapped);
				}
			}
		}
		int labCompId = getLabCompanyId(hl7FileId);
		String labCompName = getLabCompanyName(labCompId);
		List<Hl7Unmappedresults> unmappedDetailsList = unmappedResultsRepository.findAll(LabResultsSpecification.getResultsDetails(hl7FileId));
		if( unmappedDetailsList != null && !unmappedDetailsList.isEmpty() ) {
			for (int i = 0; i < unmappedDetailsList.size(); i++) {
				try {
					Hl7Unmappedresults unmappedData = unmappedDetailsList.get(i);
					int hl7ResId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsId())).or("-1"));
					DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					String orderDate = Optional.fromNullable(Strings.emptyToNull("" + formatter.format(unmappedData.getHl7UnmappedresultsOrdDate()))).or("");
					int orderBy = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsOrdbyDocid())).or("-1"));
					String performDate = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsPerformeddate())).or("");
					String testName	 = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsLabname())).or("");
					String testCode  = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsExtTestcode())).or("");
					String resultXML = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsResult())).or("");
					String resultNotes = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsTestnotes())).or("");
					int testStatus = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsTestStatus())).or("3"));
					String resultStatus = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsFinalStatus())).or("");
					String specimenSrc = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsSrcOfSpecimen())).or("");
					String specimenCond = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsCondOfSpecimen())).or("");
					Hl7ResultInbox result = unmappedData.getHl7ResultInbox();
					String labAccessionNo = Optional.fromNullable(Strings.emptyToNull("" + result.getHl7ResultInboxLabaccessionno())).or("");
					String fileName = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsFilename())).or("").replace(".xml",".pdf");
					String specimencollecteddate = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsSpecimenCollectedDate())).or("");
					String requisitionid = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsRequisitionId())).or("");
					String isFasting = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsIsfasting())).or("");
					String collectionVolume = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsCollectionVolume())).or("");
					String clinicalInfo = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsRelevantClinicalInfo())).or("");
					if ( isFasting.equalsIgnoreCase("t") ) {
						isFasting = "Y";
					} else {
						isFasting = "N";
					}
					int encounterId = -1;
					String testDetailId = "-1";

					String paramString = parametersService.getResultParam(resultXML,labCompName);

					@SuppressWarnings("rawtypes")
					Hashtable labLocCodeDetails = new Hashtable();
					labLocCodeDetails = parametersService.getLabLocationDetails(resultXML, labCompName);
					int locTestId = parametersService.getLocalTestId(labCompId, testCode, testName);		//To find glenwood testId from external testid
					int matchedTestDetailId = parametersService.getTestDetailId(patientAccNo, Integer.parseInt(chartId), labAccessionNo, testCode, locTestId, testName, orderDate, requisitionid);
					List<LabEntries> orders = labEntriesRepository.findAll(LabResultsSpecification.getOrderedLabs(chartId, testName));
					long orderedLabsCount = orders.size();
					List<LabEntries> performedLabs = labEntriesRepository.findAll(LabResultsSpecification.getPerformedLabs(chartId, testName, orderDate));
					long isPerformed = performedLabs.size();
					String performed_testDetailId = "";
					if( matchedTestDetailId != -1 ) {			//Received test matched with ordered test
						if( isPerformed == 0 ) {
							encounterId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + getLabEncounterId(matchedTestDetailId))).or("-1"));
							testDetailId = investigationService.saveInvestigation(setAttachSaveData(Integer.toString(matchedTestDetailId), encounterId, Integer.parseInt(chartId), locTestId, orderBy, orderDate, -100, performDate, testName, resultNotes, paramString, testStatus, specimenSrc, specimenCond, specimencollecteddate, isFasting, collectionVolume, clinicalInfo, labLocCodeDetails, rootPath,fileName));						
							List<Hl7Unmappedresults> testList = unmappedResultsRepository.findAll(LabResultsSpecification.verifyFileIdAndStatus("" + hl7ResId, "Mapped"));
							for( Hl7Unmappedresults testResults : testList) {
								testResults.setHl7UnmappedresultsTestdetailId(Integer.parseInt(testDetailId));
								unmappedResultsRepository.saveAndFlush(testResults);
							}
							//Making entry in configuration table for unmapped code
							if(!checkForMapping(Integer.parseInt(chartId), Integer.parseInt(hl7FileId),testName))
								parametersService.insertConfigTbl(chartId,testName,testDetailId,testCode,labCompId);
							updatePatientInfo(patientId, Integer.parseInt(hl7FileId),0);			//Updating Patient Informations in hl7_result_inbox table
						} else {
							performed_testDetailId = Optional.fromNullable(Strings.emptyToNull("" + performedLabs.get(0).getLabEntriesTestdetailId())).or("");
							parametersService.importParameters(chartId, performed_testDetailId, paramString,labLocCodeDetails,rootPath,fileName,orderBy,testStatus);
							List<Hl7Unmappedresults> testList = unmappedResultsRepository.findAll(LabResultsSpecification.verifyFileIdAndStatus("" + hl7ResId, "Mapped"));
							for( Hl7Unmappedresults testResults : testList) {
								testResults.setHl7UnmappedresultsTestdetailId(Integer.parseInt(performed_testDetailId));
								unmappedResultsRepository.saveAndFlush(testResults);
							}
						}
					} else if( orderedLabsCount == 0 && locTestId != -1 ) {
						orderDate = parseDate(orderDate);
						if( isPerformed == 0 ) {
							encounterId = parametersService.findEncounters(Integer.parseInt(chartId), orderDate, 1);
							if(encounterId == -1) {				//No office visit for the patient on the test ordered date
								encounterId = parametersService.findEncounters(Integer.parseInt(chartId), orderDate, 3);			//Check for external encounters
								if(encounterId == -1) {					//New external encounter is created for the result
									encounterId  = parametersService.addNewExternalEncounter(orderDate, Integer.parseInt(chartId), userId);
								}
							}
							testDetailId = investigationService.saveInvestigation(setAttachSaveData("-1", encounterId, Integer.parseInt(chartId), locTestId, orderBy, orderDate, -100, performDate, testName, resultNotes, paramString, testStatus, specimenSrc, specimenCond, specimencollecteddate, isFasting, collectionVolume, clinicalInfo, labLocCodeDetails, rootPath,fileName));
							List<Hl7Unmappedresults> testList = unmappedResultsRepository.findAll(LabResultsSpecification.verifyFileIdAndStatus("" + hl7ResId, "Mapped"));
							for( Hl7Unmappedresults testResults : testList) {
								testResults.setHl7UnmappedresultsTestdetailId(Integer.parseInt(testDetailId));
								unmappedResultsRepository.saveAndFlush(testResults);
							}
							//Making entry in configuration table for unmapped code
							if(!checkForMapping(Integer.parseInt(chartId), Integer.parseInt(hl7FileId),testName))
								parametersService.insertConfigTbl(chartId,testName,testDetailId,testCode,labCompId);
							updatePatientInfo(patientId, Integer.parseInt(hl7FileId),0);			//Updating Patient Informations in hl7_result_inbox table
						} else {
							performed_testDetailId = Optional.fromNullable(Strings.emptyToNull("" + performedLabs.get(0).getLabEntriesTestdetailId())).or("");
							parametersService.importParameters(chartId, performed_testDetailId, paramString,labLocCodeDetails,rootPath,fileName,orderBy,testStatus);
							List<Hl7Unmappedresults> testList = unmappedResultsRepository.findAll(LabResultsSpecification.verifyFileIdAndStatus("" + hl7ResId, "Mapped"));
							for( Hl7Unmappedresults testResults : testList) {
								testResults.setHl7UnmappedresultsTestdetailId(Integer.parseInt(performed_testDetailId));
								unmappedResultsRepository.saveAndFlush(testResults);
							}
						}
					} else {	//Received tests are not ordered for the patient
						updatePatientInfo(patientId, Integer.parseInt(hl7FileId), 1);			//Updating Patient Informations in hl7_result_inbox table
					}					
					int isPdf = Integer.parseInt(HUtil.Nz(getIsPdf(hl7FileId), "0"));
					if( isPdf == 1 ) {
						String encryptedData = parametersService.getDataFromFile(sharedFolderPath, fileName);
						parametersService.putPDFAttacmentEntry(encounterId, Integer.parseInt(patientId), chartId, Integer.parseInt(testDetailId), fileName, sharedFolderPath, encryptedData);
					}
					rootPath = sharedFolderPath;
					if( !fileName.equals("") ) {
						String reportPath = rootPath + "/HL7/pdfReportProcessed";
						File ff = new File( reportPath + "/" + fileName );
						if( ff.exists() ) {
							parametersService.createFolder(rootPath + "/Attachments/" + patientId);
							parametersService.moveFile(reportPath,rootPath + "/Attachments/" + patientId, fileName, false);
							ArrayList<String> filelist = new ArrayList<String>();
							FileBean bean;
							FileDetailBean filedetail = null;
							filelist.add(fileName);
							for(int fil = filelist.size()-1;fil >= 0;fil--) {
								ArrayList<FileBean> savefile = new ArrayList<FileBean>();
								String description = filelist.get(i);
								bean = FileBean.createInstance(-1,-1,filelist.get(fil),"", 12,true,"-1",orderBy,"-1",orderBy);
								savefile.add(bean);
								filedetail = FileDetailBean.createInstance(-1,1,2,description,"received from imaging healthcare","",orderBy,"",orderBy,encounterId, Integer.parseInt(patientId),Integer.parseInt(chartId),savefile,true,orderDate.substring(0, 10).trim(),Integer.parseInt(testDetailId),-1);
								parametersService.saveFileDetail(filedetail);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.ATTACH, -1,Log_Outcome.FAILURE,"Failure in attaching the result to a Patient" , -1,
							request.getRemoteAddr() , Integer.parseInt(patientId) , "hl7FileId="+hl7FileId+"|userId="+userId,LogUserType.USER_LOGIN ,"","");
				}
			}
		}
	}

	/**
	 * Method to check mapping in configuration table for unmapped code
	 * @param chartId
	 * @param hl7FileId
	 * @param curTestName
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean checkForMapping(Integer chartId, Integer hl7FileId, String curTestName) throws Exception {
		int labCompId = Integer.parseInt(HUtil.Nz(getLabCompanyId("" + hl7FileId), "1"));
		boolean isMapped = false;
		List<Hl7Unmappedresults> unmappedData = unmappedResultsRepository.findAll(Specifications.where(LabResultsSpecification.getResultsDetails("" + hl7FileId)).and(LabResultsSpecification.checkTestName(curTestName)));
		if(!(unmappedData == null || unmappedData.size() > 0)) {
			for (int i = 0; i < unmappedData.size(); i++) {
				Hl7Unmappedresults resultDetails = unmappedData.get(i);
				String testName	 = HUtil.Nz(resultDetails.getHl7UnmappedresultsLabname(),"");
				String testCode  = HUtil.Nz(resultDetails.getHl7UnmappedresultsExtTestcode(),"");
				int locTestId	= parametersService.getLocalTestId(labCompId, testCode, testName);		//To find glenwood testId from external testid	
				if(locTestId != -1) {			//Received test matched with ordered test
					isMapped = true;
				}
			}
		}
		return isMapped;
	}

	/**
	 * Method to set attach bean
	 */
	@SuppressWarnings("rawtypes")
	private SaveAttachResultData setAttachSaveData(String testDetailId, int encounterId, int chartId, int testId, int orderedBy, String orderedDate, 
			int performedBy, String performedDate, String testName, String resultNotes, String paramString, int testStatus, 
			String specimenSrc, String specimenCond, String specimencollecteddate, 
			String isFasting , String collectionVolume, String clinicalInfo,Hashtable labLocCodeDetails,String rootPath,String fileName) {
		SaveAttachResultData attachData = new SaveAttachResultData();
		attachData.setTestDetailId(testDetailId);
		attachData.setEncounterId(encounterId);
		attachData.setChartId(chartId);
		attachData.setTestId(testId);
		attachData.setOrderedBy(orderedBy);
		attachData.setOrderedDate(orderedDate);
		attachData.setPerformedBy(-100);
		attachData.setPerformedDate(performedDate);
		attachData.setTestName(testName);
		attachData.setResultNotes(resultNotes);
		attachData.setResultParamStr(paramString);
		attachData.setStatus(testStatus);
		attachData.setSpecimenSource(specimenSrc);
		attachData.setSpecimenCondition(specimenCond);
		attachData.setSpecimencollecteddate(specimencollecteddate);
		attachData.setIsFasting(isFasting);
		attachData.setCollectionVolume(collectionVolume);
		attachData.setClinicalInfo(clinicalInfo);
		attachData.setLabLocCodeDetails(labLocCodeDetails);
		attachData.setRootPath(rootPath);
		attachData.setResultFileName(fileName);
		return attachData;
	}

	/**
	 * Method to get lab entries encounter id
	 * @param matchedTestDetailId
	 * @return
	 */
	private String getLabEncounterId(Integer matchedTestDetailId) {
		String encounterId="";
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<LabEntries> root = cq.from(LabEntries.class);
			cq.select(root.get(LabEntries_.labEntriesEncounterId));
			cq.where(builder.equal(root.get(LabEntries_.labEntriesTestdetailId),matchedTestDetailId));
			List<Object> objList = em.createQuery(cq).getResultList();
			if(!objList.isEmpty()){
				encounterId = (String) objList.get(0);
			}
			return encounterId;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Method to update patient information in hl7_result_inbox table
	 * @param flag
	 * @param patientId
	 * @param hl7FileId
	 */
	private void updatePatientInfo(String patientId, Integer hl7FileId, Integer flag) {
		
		PatientRegistrationBean patientDataList=getPatientDetails(patientId);
		if(patientDataList!=null ) {
			String patientFName = patientDataList.getPatientRegistrationFirstName();
			String patientLName = patientDataList.getPatientRegistrationLastName();
			String patientAccNo = patientDataList.getPatientRegistrationAccountno();
			List<Hl7ResultInbox> resultsList = resultsRepository.findAll(LabResultsSpecification.getResultsData(hl7FileId));
			for( Hl7ResultInbox patientResultData : resultsList ) {
				patientResultData.setHl7ResultInboxAccountno(patientAccNo);
				patientResultData.setHl7ResultInboxFirstname(patientFName);
				patientResultData.setHl7ResultInboxLastname(patientLName);
				patientResultData.setHl7ResultInboxDob(patientDataList.getPatientRegisDob());
				if( flag == 0 ) {
					patientResultData.setHl7ResultInboxStatus(1);
				} else if( flag == 1 ) {
					patientResultData.setHl7ResultInboxStatus(2);
				}
				resultsRepository.saveAndFlush(patientResultData);
			}
		}
	}
	
	public PatientRegistrationBean getPatientDetails(String patientId) {
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<PatientRegistration> root = cq.from(PatientRegistration.class);
			Predicate patientIdVal = builder.equal(root.get(PatientRegistration_.patientRegistrationId), patientId);

			cq.multiselect(builder.construct(PatientRegistrationBean.class, root.get(PatientRegistration_.patientRegistrationFirstName),
					root.get(PatientRegistration_.patientRegistrationLastName),
					root.get(PatientRegistration_.patientRegistrationAccountno),
					root.get(PatientRegistration_.patientRegistrationDob)));		
			cq.where(patientIdVal);

			List<Object> patientRegistraionList=em.createQuery(cq).getResultList();
			PatientRegistrationBean eachObj=new PatientRegistrationBean();
			if(!patientRegistraionList.isEmpty())
				eachObj=(PatientRegistrationBean) patientRegistraionList.get(0);
			return eachObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			em.close();
		}
	}

	/**
	 * Method to get documentId
	 * @param hl7FileId
	 * @return
	 */
	private int getDocumentId(String hl7FileId) {
		int documentId = -1;
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7ResultInbox> root = cq.from(Hl7ResultInbox.class);
			cq.select(root.get(Hl7ResultInbox_.hl7ResultInboxDocumentid));
			cq.where(builder.equal(root.get(Hl7ResultInbox_.hl7ResultInboxId),hl7FileId));
			List<Object> objList = em.createQuery(cq).getResultList();
			if(!objList.isEmpty()){
				documentId = (int) objList.get(0);
			}
			return documentId;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			em.close();
		}
	}

	/**
	 * Method to get lab company name
	 * @param labCompId
	 * @return
	 */
	private String getLabCompanyName(int labCompId) {
		String labCompanyName = "";
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7importCompanies> root = cq.from(Hl7importCompanies.class);
			cq.select(root.get(Hl7importCompanies_.labcompanyname));
			cq.where(builder.equal(root.get(Hl7importCompanies_.id),labCompId));
			List<Object> objList = em.createQuery(cq).getResultList();
			if(!objList.isEmpty()){
				labCompanyName = (String) objList.get(0);
			}
			if( labCompanyName.equals("") || labCompanyName.equals(null) ) {
				labCompanyName = "Labcorp";
			}
			return labCompanyName;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Method to get lab company id
	 * @param hl7FileId
	 * @return
	 */
	private int getLabCompanyId(String hl7FileId) {
		int labCompanyId = -1;
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7ResultInbox> root = cq.from(Hl7ResultInbox.class);
			cq.select(root.get(Hl7ResultInbox_.hl7ResultInboxLabcompanyid));
			cq.where(builder.equal(root.get(Hl7ResultInbox_.hl7ResultInboxId),hl7FileId));
			List<Object> objList = em.createQuery(cq).getResultList();
			if(!objList.isEmpty()){
				labCompanyId = (int)objList.get(0);
			}
			return labCompanyId;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			em.close();
		}
	}

	/**
	 * Method to get patient chart Id after inserting in to chart
	 * @return
	 */
	private String getPatientChart() {
		String chartId="";
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<PrimarykeyGenerator> root = cq.from(PrimarykeyGenerator.class);
			cq.select(root.get(PrimarykeyGenerator_.primarykey_generator_rowcount));
			cq.where(builder.equal(root.get(PrimarykeyGenerator_.primarykey_generator_tablename),"chart"));
			List<Object> objList = em.createQuery(cq).getResultList();
			if(!objList.isEmpty()){
				chartId = String.valueOf(objList.get(0));
			}
			return chartId;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}


	/**
	 * Method to get chart id based on patient id
	 * @param patientId
	 * @return
	 */
	@Override
	public String getChartId(String patientId) {
		String chartId = "-1";
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Chart> root = cq.from(Chart.class);
			cq.select(root.get(Chart_.chartId));
			cq.where(builder.equal(root.get(Chart_.chartPatientid),patientId));
			List<Object> objList = em.createQuery(cq).getResultList();
			if(!objList.isEmpty()){
				chartId = String.valueOf(objList.get(0));
			}
			return chartId;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Method to save lab details
	 * @param labDetails
	 * @return 
	 */
	@Override
	public void saveLabDetails(SaveData labDetails) throws Exception {
		List<TestDetails> testDetailsList = labDetails.getTestDetails();		
		try{
		for (int i = 0; i < testDetailsList.size(); i++) {
			TestDetails testDetails = testDetailsList.get(i);
			if( labDetails.getIsReviewAll() ) {
				if( Integer.parseInt(testDetails.getTestStatus()) <= 3 ) {
					testDetails.setTestStatus("4");
				}
			}
			List<LabEntries> labEntriesList = labEntriesRepository.findAll(InvestigationSpecification.testdetailIds(Integer.parseInt(testDetails.getTestDetailId())));
			for( LabEntries labEntries : labEntriesList ) {
				labEntries.setLabEntriesTestStatus(Integer.parseInt(testDetails.getTestStatus()));
				labEntries.setLabEntriesRevBy(Integer.parseInt(labDetails.getDoctorId()));
				if( testDetails.getReviewedOn() != null && !testDetails.getReviewedOn().equalsIgnoreCase("")) {
					labEntries.setLabEntriesRevOn(Timestamp.valueOf(testDetails.getReviewedOn()));
				} else {
					if(testDetails.getTestStatus().equalsIgnoreCase("4"))
						labEntries.setLabEntriesRevOn(new Timestamp(new Date().getTime()));
				}
				labEntries.setLabEntriesPrelimTestStatus(Integer.parseInt(testDetails.getPrelimStatus()));
				labEntries.setLabEntriesConfirmTestStatus(Integer.parseInt(testDetails.getFinalStatus()));
				labEntries.setLabEntriesConfirmTestStatus(Integer.parseInt(testDetails.getFinalStatus()));
				String testNotes = HUtil.Nz(URLDecoder.decode(testDetails.getTestNotes(), "UTF-8"),"");
				testNotes = testNotes.replaceAll("\n", "!@!");
				labEntries.setLabEntriesResultNotes(testNotes.replaceAll("'", "''"));
				labEntries.setLabEntriesZunits(0);
				labEntries.setLabEntriesIsFasting(false);
				labEntries.setLabEntriesProcLeafid(-1);
				labEntries.setLabEntriesIschdplab(1);
				labEntries.setLabEntriesSetid(-1);
				labEntries.setLabEntriesSepcimenId(-1);
				labEntries.setLabEntriesImmunizationstatus(0);
				labEntries.setLabEntriesRefusalreason(0);
				labEntries.setLabEntriesAdministrationNotes(0);				
				labEntriesRepository.saveAndFlush(labEntries);
			}
			
			List<Hl7Unmappedresults> unmapList = unmappedResultsRepository.findAll(LabResultsSpecification.getUnreviewedList(labDetails.getHl7FileId()));
			if( unmapList.size() <= 0 ) {
				for(Hl7Unmappedresults unmap:unmapList){
					unmap.setHl7UnmappedresultsTestStatus(Integer.parseInt(testDetails.getTestStatus()));
				}
			}
		}
		int reviewStatus = getResultReviewStatus(labDetails.getHl7FileId());
		if( reviewStatus == 0 ) {
			List<Hl7Unmappedresults> unmappedList = unmappedResultsRepository.findAll(LabResultsSpecification.getUnreviewedList(labDetails.getHl7FileId()));
			if( unmappedList.size() <= 0 ) {
				List<Hl7ResultInbox> inboxList = resultsRepository.findAll(LabResultsSpecification.getResults(labDetails.getHl7FileId()));
				for( Hl7ResultInbox inbox : inboxList ) {
					inbox.setHl7ResultInboxReviewed(1);
				}
			}
		}
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.UPDATE, -1,Log_Outcome.SUCCESS,"Success in Saving the details of a lab from Hl7" , -1,
				request.getRemoteAddr() , -1 , "" ,LogUserType.USER_LOGIN ,"","");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.UPDATE, -1,Log_Outcome.FAILURE,"Failure in Saving the details of a lab from Hl7" , -1,
					request.getRemoteAddr() , -1 , "" ,LogUserType.USER_LOGIN ,"","");
		}
	}

	private int getResultReviewStatus(String hl7FileId) {
		int status = 0;
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Hl7ResultInbox> root = cq.from(Hl7ResultInbox.class);
		cq.select(root.get(Hl7ResultInbox_.hl7ResultInboxReviewed));
		cq.where(builder.equal(root.get(Hl7ResultInbox_.hl7ResultInboxId), hl7FileId));
		status = em.createQuery(cq).getFirstResult();
		return status;
	}

	@Override
	public ResultDetails findPreviousOrders(String hl7FileId) {
		String HL7IdList = "";
		List<Integer> statusList = new ArrayList<>();
		statusList.add(1);
		statusList.add(2);
		String accountNo = parametersService.getAccountNo(hl7FileId, statusList);
		Integer status = Optional.fromNullable(parametersService.getResultStatus(hl7FileId)).or(1);
		String isReviewed = HUtil.Nz(parametersService.getIsReviewed(hl7FileId), "0");
		String fromReviewAll = "-1";
		if( status != 3 ) {
			if( isReviewed.equals("0")) {
				if( !accountNo.equals("") ) {
					HL7IdList = getUnreviewedHL7Ids(accountNo);
				}
			}
			if( HL7IdList.equals("") || (fromReviewAll.equals("1")))
				HL7IdList = getReviewedHL7Ids(hl7FileId);
		} else {
			HL7IdList = getUnknownCatHL7Ids(hl7FileId);
		}
		if(HL7IdList.equals(""))
			HL7IdList = "-1";
		if( HL7IdList.startsWith(",")) {
			HL7IdList = HL7IdList.substring(1);
		}
		List<Hl7ResultInbox> resultInboxList=checkHl7Id(hl7FileId);
		ResultDetails patientResults = new ResultDetails();
		setPatientResults(patientResults, resultInboxList,hl7FileId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.VIEW, -1,Log_Outcome.SUCCESS,"Success in getting the previous orders for a result" , -1,
				request.getRemoteAddr() , -1 , "hl7FileId="+hl7FileId,LogUserType.USER_LOGIN ,"","");
		return patientResults;
	}

	private String getUnknownCatHL7Ids(String hl7FileId) {
		List<Hl7ResultInbox> hl7List = resultsRepository.findAll(LabResultsSpecification.getResultsData(Integer.parseInt(hl7FileId)));
		String firstName = "", lastName = "";
		java.sql.Date dob = null;
		for( Hl7ResultInbox hl7Data : hl7List ) {
			firstName = hl7Data.getHl7ResultInboxFirstname();
			lastName = hl7Data.getHl7ResultInboxLastname();
			dob = (java.sql.Date) hl7Data.getHl7ResultInboxDob();
		}
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7ResultInbox> root = cq.from(Hl7ResultInbox.class);
			cq.select(root.get(Hl7ResultInbox_.hl7ResultInboxId));
			cq.where(builder.and(builder.equal(root.get(Hl7ResultInbox_.hl7ResultInboxFirstname), firstName.replaceAll("'","''"))),
					builder.equal(root.get(Hl7ResultInbox_.hl7ResultInboxLastname), lastName.replaceAll("'","''")),
					builder.equal(root.get(Hl7ResultInbox_.hl7ResultInboxDob), dob));
			return processResult(em, cq);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	private String getReviewedHL7Ids(String hl7FileId) {
		List<Integer> statusList = new ArrayList<>();
		statusList.add(1);
		statusList.add(2);
		statusList.add(3);
		String orderedDate  =  HUtil.Nz(parametersService.getUnmappedOrderedDate(hl7FileId),"01/01/1900");
		if( !orderedDate.contains("-") )
			orderedDate = parseDate(orderedDate);
		String patientAccNo	=  HUtil.Nz(parametersService.getAccountNo(hl7FileId, statusList),"-1");
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7ResultInbox> root = cq.from(Hl7ResultInbox.class);
			Join<Hl7ResultInbox, Hl7Unmappedresults> join = root.join(Hl7ResultInbox_.hl7UnmappedResults);
			join.on(builder.and(builder.equal(root.get(Hl7ResultInbox_.hl7ResultInboxReviewed), 1), 
					builder.equal(root.get(Hl7ResultInbox_.hl7ResultInboxIsactive), true)));
			cq.select(root.get(Hl7ResultInbox_.hl7ResultInboxId));
			cq.where(builder.and(builder.equal(join.get(Hl7Unmappedresults_.hl7UnmappedresultsOrdDate), Timestamp.valueOf(orderedDate))),
					builder.like(root.get(Hl7ResultInbox_.hl7ResultInboxAccountno), patientAccNo));
			cq.orderBy(builder.asc(root.get(Hl7ResultInbox_.hl7ResultInboxId)));		
			return processResult(em, cq);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	private String processResult(EntityManager em2, CriteriaQuery<Object> cq) {
		EntityManager em = emf.createEntityManager();
		try {	
			List<Object> result = em.createQuery(cq).getResultList();
			String hl7Ids = "";
			for( Object resultData : result ) {
				if( hl7Ids.equals("") ) {
					hl7Ids = String.valueOf(resultData);
				} else {
					hl7Ids = "," + String.valueOf(resultData);
				}
			}
			return hl7Ids;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	private String getUnreviewedHL7Ids(String accountNo) {
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7ResultInbox> root = cq.from(Hl7ResultInbox.class);
			cq.select(root.get(Hl7ResultInbox_.hl7ResultInboxId));
			cq.where(builder.and(builder.equal(root.get(Hl7ResultInbox_.hl7ResultInboxAccountno), accountNo)),
					builder.equal(root.get(Hl7ResultInbox_.hl7ResultInboxReviewed), 0));
			cq.orderBy(builder.asc(root.get(Hl7ResultInbox_.hl7ResultInboxAccountno)));
			return processResult(em, cq);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	/**
	 * Method to attach results to already ordered labs
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@Override
	public void attachToOrderedLabs(AttachLabData attachLabData) throws Exception {
		this.rootPath = attachLabData.getSharedFolderPath();
		String chartId = getChartId(attachLabData.getPatientId());
		int labCompId = getLabCompanyId(attachLabData.getHl7FileId());
		String labCompName = getLabCompanyName(labCompId);
		List<String> resultList = attachLabData.getResultName();
		List<String> testDetailIdList = attachLabData.getTestDetailId();
		List<String> testIdList = attachLabData.getTestId();
		List<String> testNameList = attachLabData.getTestName();
		try{
		for(int j = 0; j < resultList.size(); j++) {
			String resultName = resultList.get(j);
			String testDetailId = testDetailIdList.get(j);
			String testId = testIdList.get(j);
			String selectedTest = testNameList.get(j);
			Integer docId = -1, encId = -1, isExternal = -1;
			List<Hl7Unmappedresults> unmappedDetailsList = unmappedResultsRepository.findAll(Specifications.where(LabResultsSpecification.getResultsDetails(attachLabData.getHl7FileId())).and(LabResultsSpecification.checkTestName(resultName)));
			if( unmappedDetailsList != null && !unmappedDetailsList.isEmpty() ) {
				for (int i = 0; i < unmappedDetailsList.size(); i++) {
					try {
						Hl7Unmappedresults unmappedData = unmappedDetailsList.get(i);
						int hl7ResId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsId())).or("-1"));
						DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
						String orderDate = Optional.fromNullable(Strings.emptyToNull("" + formatter.format(unmappedData.getHl7UnmappedresultsOrdDate()))).or("");
						int orderBy = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsOrdbyDocid())).or("-1"));
						String performDate = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsPerformeddate())).or("");
						String testName	 = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsLabname())).or("");
						String testCode  = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsExtTestcode())).or("");
						String resultXML = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsResult())).or("");
						String resultNotes = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsTestnotes())).or("");
						int testStatus = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsTestStatus())).or("3"));
						String specimenSrc = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsSrcOfSpecimen())).or("");
						String specimenCond = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsCondOfSpecimen())).or("");
						String fileName = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsFilename())).or("").replace(".xml",".pdf");
						String specimencollecteddate = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsSpecimenCollectedDate())).or("");
						String isFasting = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsIsfasting())).or("");
						String collectionVolume = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsCollectionVolume())).or("");
						String clinicalInfo = Optional.fromNullable(Strings.emptyToNull("" + unmappedData.getHl7UnmappedresultsRelevantClinicalInfo())).or("");
						int encounterId = -1;
						String paramString = parametersService.getResultParam(resultXML,labCompName);
						@SuppressWarnings("rawtypes")
						Hashtable labLocCodeDetails = new Hashtable();
						labLocCodeDetails = parametersService.getLabLocationDetails(resultXML, labCompName);
						int locTestId = parametersService.getLocalTestId(labCompId, testCode, testName);		//To find glenwood testId from external testid
						List<LabEntries> performedLabs = labEntriesRepository.findAll(LabResultsSpecification.getPerformedLabs(chartId, testName, orderDate));
						long isPerformed = performedLabs.size();
						String performed_testDetailId = Optional.fromNullable(Strings.emptyToNull("" + performedLabs.get(0).getLabEntriesTestdetailId())).or("");
						if( !testDetailId.equals("-1") ) {			//Received test matched with ordered test
							if( isPerformed == 0 ) {
								encounterId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + getLabEncounterId(Integer.parseInt(testDetailId)))).or("-1"));
								testDetailId = investigationService.saveInvestigation(setAttachSaveData(Integer.toString(Integer.parseInt(testDetailId)), encounterId, Integer.parseInt(chartId), locTestId, orderBy, orderDate, -100, performDate, testName, resultNotes, paramString, testStatus, specimenSrc, specimenCond, specimencollecteddate, isFasting, collectionVolume, clinicalInfo, labLocCodeDetails, rootPath,fileName));
								if( !chartId.equals("-1") ) {
									List<LabEntries> labsList = labEntriesRepository.findAll(InvestigationSpecification.testdetailIds(Integer.parseInt(testDetailId))) ;
									for( LabEntries labs : labsList ) {
										labs.setLabEntriesChartid(Integer.parseInt(chartId));
										labEntriesRepository.saveAndFlush(labs);
									}
								}
								//Making entry in configuration table for unmapped code
								if(!checkForMapping(Integer.parseInt(chartId), Integer.parseInt(attachLabData.getHl7FileId()),testName))
									parametersService.insertConfigTbl(chartId,testName,testDetailId,testCode,labCompId);
							} else {
								performed_testDetailId = Optional.fromNullable(Strings.emptyToNull("" + performedLabs.get(0).getLabEntriesTestdetailId())).or("");
								parametersService.importParameters(chartId, performed_testDetailId, paramString,labLocCodeDetails,rootPath,fileName,orderBy,testStatus);
							}
							List<Hl7Unmappedresults> testList = unmappedResultsRepository.findAll(LabResultsSpecification.verifyFileIdAndStatus("" + hl7ResId, "Mapped"));
							for( Hl7Unmappedresults testResults : testList) {
								testResults.setHl7UnmappedresultsTestdetailId(Integer.parseInt(testDetailId));
								unmappedResultsRepository.saveAndFlush(testResults);
							}
						} else {
							orderDate = parseDate(orderDate);
							if( isPerformed == 0 ) {
								encounterId = parametersService.findEncounters(Integer.parseInt(chartId), orderDate, 1);
								if(encounterId == -1) {				//No office visit for the patient on the test ordered date
									encounterId = parametersService.findEncounters(Integer.parseInt(chartId), orderDate, 3);			//Check for external encounters
									if(encounterId == -1) {					//New external encounter is created for the result
										encounterId  = parametersService.addNewExternalEncounter(orderDate, Integer.parseInt(chartId), attachLabData.getUserId());
									}
								}
								if(!testId.equalsIgnoreCase("-1") && !selectedTest.equalsIgnoreCase("")) {
									testDetailId = investigationService.saveInvestigation(setAttachSaveData("-1", encounterId, Integer.parseInt(chartId), Integer.parseInt(testId), orderBy, orderDate, -100, performDate, selectedTest, resultNotes, paramString, testStatus, specimenSrc, specimenCond, specimencollecteddate, isFasting, collectionVolume, clinicalInfo, labLocCodeDetails, rootPath,fileName));
								} else {
									testDetailId = investigationService.saveInvestigation(setAttachSaveData("-1", encounterId, Integer.parseInt(chartId), locTestId, orderBy, orderDate, -100, performDate, testName, resultNotes, paramString, testStatus, specimenSrc, specimenCond, specimencollecteddate, isFasting, collectionVolume, clinicalInfo, labLocCodeDetails, rootPath,fileName));									
								}
								if( !chartId.equals("-1") ) {
									List<LabEntries> labsList = labEntriesRepository.findAll(InvestigationSpecification.testdetailIds(Integer.parseInt(testDetailId))) ;
									for( LabEntries labs : labsList ) {
										labs.setLabEntriesChartid(Integer.parseInt(chartId));
										labEntriesRepository.saveAndFlush(labs);
									}
								}
								//Making entry in configuration table for unmapped code
								if(!checkForMapping(Integer.parseInt(chartId), Integer.parseInt(attachLabData.getHl7FileId()),testName))
									parametersService.insertConfigTbl(chartId,testName,testDetailId,testCode,labCompId);
							} else {
								performed_testDetailId = Optional.fromNullable(Strings.emptyToNull("" + performedLabs.get(0).getLabEntriesTestdetailId())).or("");
								parametersService.importParameters(chartId, performed_testDetailId, paramString,labLocCodeDetails,rootPath,fileName,orderBy,testStatus);
							}
							List<Hl7Unmappedresults> testList = unmappedResultsRepository.findAll(LabResultsSpecification.verifyFileIdAndStatus("" + hl7ResId, "Mapped"));
							for( Hl7Unmappedresults testResults : testList) {
								testResults.setHl7UnmappedresultsTestdetailId(Integer.parseInt(testDetailId));
								unmappedResultsRepository.saveAndFlush(testResults);
							}
						}
						int isPdf = Integer.parseInt(HUtil.Nz(getIsPdf(attachLabData.getHl7FileId()), "0"));
						if( isPdf == 1 ) {
							String encryptedData = parametersService.getDataFromFile(attachLabData.getSharedFolderPath(), fileName);
							parametersService.putPDFAttacmentEntry(encounterId, Integer.parseInt(attachLabData.getPatientId()), chartId, Integer.parseInt(testDetailId), fileName, attachLabData.getSharedFolderPath(), encryptedData);
						}
						rootPath = attachLabData.getSharedFolderPath();
						if( !fileName.equals("") ) {
							String reportPath = rootPath + "/HL7/pdfReportProcessed";
							File ff = new File( reportPath + "/" + fileName );
							if( ff.exists() ) {
								parametersService.createFolder(rootPath + "/Attachments/" + attachLabData.getPatientId());
								parametersService.moveFile(reportPath,rootPath + "/Attachments/" + attachLabData.getPatientId(), fileName, false);
								ArrayList<String> filelist = new ArrayList<String>();
								FileBean bean;
								FileDetailBean filedetail = null;
								filelist.add(fileName);
								for(int fil = filelist.size()-1;fil >= 0;fil--) {
									ArrayList<FileBean> savefile = new ArrayList<FileBean>();
									String description = filelist.get(i);
									bean = FileBean.createInstance(-1,-1,filelist.get(fil),"", 12,true,"-1",orderBy,"-1",orderBy);
									savefile.add(bean);
									filedetail = FileDetailBean.createInstance(-1,1,2,description,"received from imaging healthcare","",orderBy,"",orderBy,encounterId, Integer.parseInt(attachLabData.getPatientId()),Integer.parseInt(chartId),savefile,true,orderDate.substring(0, 10).trim(),Integer.parseInt(testDetailId),-1);
									parametersService.saveFileDetail(filedetail);
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			List<LabEntries> labsList = labEntriesRepository.findAll(InvestigationSpecification.testdetailIds(Integer.parseInt(testDetailId))) ;
			for( LabEntries labData : labsList ) {
				encId = labData.getLabEntriesEncounterId();
				isExternal = labData.getLabEntriesTestType();
				docId = labData.getLabEntriesOrdBy();
			}
			investigationService.addLabAlert(Integer.parseInt(attachLabData.getPatientId()), encId, Integer.parseInt(testDetailId), 3, isExternal, docId);
			if( checkAllMapped(Integer.parseInt(attachLabData.getHl7FileId())) ) {
				//				EventLog.LogEvent(AuditTrail.GLACE_LOG,AuditTrail.HL7_Lab_Results,AuditTrail.UPDATE,Integer.parseInt(session.getAttribute("parent_event").toString()),AuditTrail.SUCCESS,"Lab Result(testname="+names[i]+") Attached to an ordered Lab(testdetailid="+testIdAttach+") by the user with userid="+userId,Integer.parseInt(session.getAttribute("loginId").toString()),"127.0.01",request.getRemoteAddr(),-1,-1,-1,AuditTrail.USER_LOGIN,request,dbUtils,"Lab result file marked as Attached");
			}
			//			EventLog.LogEvent(AuditTrail.GLACE_LOG,AuditTrail.HL7_Lab_Results,AuditTrail.UPDATE,Integer.parseInt(session.getAttribute("parent_event").toString()),AuditTrail.SUCCESS,"Lab Result(testname="+names[i]+") Attached to an ordered Lab(testdetailid="+testIdAttach+") by the user with userid="+userId,Integer.parseInt(session.getAttribute("loginId").toString()),"127.0.01",request.getRemoteAddr(),-1,-1,-1,AuditTrail.USER_LOGIN,request,dbUtils,"Lab result attached to an Ordered Lab");
		}
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.ATTACH, -1,Log_Outcome.SUCCESS,"Success in attaching unknown result to a lab" , -1,
				request.getRemoteAddr() , -1 , "" ,LogUserType.USER_LOGIN ,"","");
		}catch(Exception e){
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.ATTACH, -1,Log_Outcome.FAILURE,"Failure in attaching unknown result to a lab" , -1,
					request.getRemoteAddr() , -1 , "" ,LogUserType.USER_LOGIN ,"","");
		}
	}

	private String getIsPdf(String hl7FileId) {
		String isPdf="";
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7Unmappedresults> root = cq.from(Hl7Unmappedresults.class);
			cq.select(root.get(Hl7Unmappedresults_.hl7UnmappedresultsIspdf));
			cq.where(builder.equal(root.get(Hl7Unmappedresults_.hl7UnmappedresultsId), hl7FileId));
			List<Object> objList = em.createQuery(cq).getResultList();
			if(!objList.isEmpty()){
				isPdf = String.valueOf(objList.get(0));
			}	
			return isPdf;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}
	
	@Override
	public List<LabEntries> getOrderedList(String orderedOn, String patientId) {
		orderedOn = parseDate(orderedOn);
		List<LabEntries> orderedLabs = labEntriesRepository.findAll(LabResultsSpecification.checkOrders(patientId, orderedOn, 1));
		return orderedLabs;
	}

	@Override
	public List<LabEntries> getPendingList(String orderedOn, String patientId) {
		orderedOn = parseDate(orderedOn);
		List<LabEntries> orderedLabs = labEntriesRepository.findAll(LabResultsSpecification.checkOrders(patientId, orderedOn, 2));
		return orderedLabs;
	}

	@Override
	public String deleteUnmappedLab(String hl7Id, String testName, String orderedOn) {
		List<Hl7Unmappedresults> deleteList = unmappedResultsRepository.findAll(LabResultsSpecification.deleteUnmapped(hl7Id, testName, Timestamp.valueOf(orderedOn)));
		try {
			for( Hl7Unmappedresults deleteLab : deleteList ) {
				unmappedResultsRepository.delete(deleteLab);
			}
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.DELETE, -1,Log_Outcome.SUCCESS,"Success in deleting the lab, if no mapping" , -1,
					request.getRemoteAddr() , -1 , "hl7FileId="+hl7Id+"|testName="+testName,LogUserType.USER_LOGIN ,"","");
			return "Successfully deleted";
		} catch (Exception e) {
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.DELETE, -1,Log_Outcome.FAILURE,"Failure in deleting the lab, if no mapping" , -1,
					request.getRemoteAddr() , -1 , "hl7FileId="+hl7Id+"|testName="+testName,LogUserType.USER_LOGIN ,"","");
			return "Failed";
		}		
	}

	private String parseDate(String currentDate) {
		DateFormat parser = new SimpleDateFormat("MM/dd/yyyy"); 
		Date date = null;
		try {
			date = (Date) parser.parse(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		currentDate = formatter.format(date);
		return currentDate;
	}

	private int getInboxStatus(int hl7FileId) {
		int isPdf=-1;
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7ResultInbox> root = cq.from(Hl7ResultInbox.class);
			cq.select(root.get(Hl7ResultInbox_.hl7ResultInboxStatus));
			cq.where(builder.equal(root.get(Hl7ResultInbox_.hl7ResultInboxId), hl7FileId));				
			List<Object> objList = em.createQuery(cq).getResultList();
			if(!objList.isEmpty()){
				isPdf = (int)objList.get(0);
			}	
			return isPdf;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			em.close();
		}
	}

	public boolean checkAllMapped(int hl7FileId) {		//Check map status of all results and mark file as mapped
		boolean allMapped = false;
		try {
			//Check reviewed status of hl7_result_inbox table
			int mapStatus = Integer.parseInt(HUtil.Nz(getInboxStatus(hl7FileId), "0"));
			if( mapStatus == 2) {
				List<Hl7Unmappedresults> unmappedList = unmappedResultsRepository.findAll(LabResultsSpecification.getUnmappedCount(hl7FileId));
				int unmappedCount = Integer.parseInt(HUtil.Nz(unmappedList.size(), "0"));
				if(unmappedCount <= 0) {
					List<Hl7ResultInbox> resultsList = resultsRepository.findAll(LabResultsSpecification.getResultsData(hl7FileId)) ;
					for( Hl7ResultInbox results : resultsList ) {
						results.setHl7ResultInboxStatus(1);
						resultsRepository.saveAndFlush(results);
					}
					allMapped = true;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return allMapped;
	}

	@Override
	public DocumentData getDocumentData(String fileType, String patientId, String documentId, String sharedFolderPath) {
		if( documentId == null || documentId.equals("null") ) {
			documentId = "-1";
		}
		DocumentData result = new DocumentData();
		try {
			if( patientId.equals("-1") ) {
				String htmlContent = "";
				if( fileType.equalsIgnoreCase("html")) {
					String fileName = getFileName(documentId);
					try {
						htmlContent	= "<div style=\"min-height:400px;height:100%;\" >" + labDocsService.getFileContent(sharedFolderPath + "/HL7/UnAttachedDocs/"+fileName) + "</div>";
					} catch( FileNotFoundException e ) {
						htmlContent = "<div style=\"height:100px;\" ><p style=\"color:red;\"><b>File Not Found.</b></p></div>";
					}
					result.setFileName(fileName);
				}
				result.setHtmlContent(htmlContent);
				auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.VIEW, -1,Log_Outcome.SUCCESS,"Success in getting the document data like file name shared folder path, etc" , -1,
						request.getRemoteAddr() , Integer.parseInt(patientId) , "documentId="+documentId ,LogUserType.USER_LOGIN ,"","");
				return result;
			} else {
				String htmlContent = "";
				String labName = "";
				if( fileType.equalsIgnoreCase("html")) {
					String fileName = getFileName(documentId);
					result.setFileName(fileName);
					try {
						labName = labDocsService.getLabName(sharedFolderPath + "/HL7/AttachedDocs/" + fileName);
						htmlContent	= "<div style=\"min-height:400px;height:100%;\" >" + labDocsService.getFileContent(sharedFolderPath + "/HL7/AttachedDocs/" + fileName)+"</div>";
					} catch(FileNotFoundException e) {
						labName = "";
						htmlContent = "<div style=\"height:100px;\" ><p style=\"color:red;\"><b>File Not Found.</b></p></div>";
					}
					result.setLabName(labName);
					result.setHtmlContent(htmlContent);
				} else if( fileType.equals("pdf") ) {								
					String fileName = getFileName(documentId);
					result.setFileName(fileName);
				}	
				auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.VIEW, -1,Log_Outcome.SUCCESS,"Success in getting the document data like file name shared folder path, etc" , -1,
						request.getRemoteAddr() , Integer.parseInt(patientId) , "documentId="+documentId ,LogUserType.USER_LOGIN ,"","");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.VIEW, -1,Log_Outcome.FAILURE,"Failure in getting the document data like file name shared folder path, etc" , -1,
					request.getRemoteAddr() , Integer.parseInt(patientId) , "documentId="+documentId ,LogUserType.USER_LOGIN ,"","");
			return result;
		}
	}

	private String getFileName(String documentId) {
		String fileName="";
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Hl7DocsInbox> root = cq.from(Hl7DocsInbox.class);
			cq.select(root.get(Hl7DocsInbox_.hl7DocsInboxFilename));
			cq.where(builder.equal(root.get(Hl7DocsInbox_.hl7DocsInboxId), Integer.parseInt(documentId)));
			List<Object> objList = em.createQuery(cq).getResultList();
			if(!objList.isEmpty()){
				fileName = (String)objList.get(0);
			}	
			return fileName;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public String reviewFile(String fileId, String userId) {
		List<FileName> fileList = fileNameRepository.findAll(LabResultsSpecification.checkFileId(fileId));
		for( FileName file : fileList ) {
			file.setFilenameIsreviewed(true);
			file.setFilenameReviewedby(Integer.parseInt(userId));
			file.setFilenameReviewedon(new Timestamp(new Date().getTime()));
			fileNameRepository.saveAndFlush(file);
		}
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.UPDATE, -1,Log_Outcome.SUCCESS,"Success in Reviewing the received documents" , -1,
				request.getRemoteAddr() , -1 , "fileId="+fileId+"userId="+userId ,LogUserType.USER_LOGIN ,"","");
		return "Reviewed the document";
	}

	@Override
	public String getPDFName(String testDetailId) {
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<FileName> root = cq.from(FileName.class);
			Join<FileName, FileDetails> join = root.join(FileName_.fileNameDetails,JoinType.INNER);
			cq.select(root.get(FileName_.filenameName));
			cq.where(builder.equal(join.get(FileDetails_.filedetailsEntityid), Integer.parseInt(testDetailId)));
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.VIEW, -1,Log_Outcome.SUCCESS,"Success in getting the PDF details." , -1,
					request.getRemoteAddr() , -1 , "testDetailId="+testDetailId,LogUserType.USER_LOGIN ,"","");
			return "" + em.createQuery(cq).getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
			auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.LABINTERFACE,LogActionType.VIEW, -1,Log_Outcome.FAILURE,"Failure in getting the PDF details." , -1,
					request.getRemoteAddr() , -1 , "testDetailId="+testDetailId,LogUserType.USER_LOGIN ,"","");
			return null;
		} finally {
			em.close();
		}
	}
}