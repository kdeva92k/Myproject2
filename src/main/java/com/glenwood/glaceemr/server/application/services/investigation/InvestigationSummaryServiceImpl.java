package com.glenwood.glaceemr.server.application.services.investigation;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.AlertCategory;
import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.AssociatedOrderPblm;
import com.glenwood.glaceemr.server.application.models.BodySite;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.ChartStatus;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.Cpt;
import com.glenwood.glaceemr.server.application.models.CvxVaccineGroupMapping;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.FileDetails;
import com.glenwood.glaceemr.server.application.models.FileDetails_;
import com.glenwood.glaceemr.server.application.models.FileName;
import com.glenwood.glaceemr.server.application.models.FileName_;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTestmapping;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTestmapping_;
import com.glenwood.glaceemr.server.application.models.Hl7ResultInbox;
import com.glenwood.glaceemr.server.application.models.Hl7ResultInbox_;
import com.glenwood.glaceemr.server.application.models.Hl7Unmappedresults;
import com.glenwood.glaceemr.server.application.models.Hl7Unmappedresults_;
import com.glenwood.glaceemr.server.application.models.ImmunisationSite;
import com.glenwood.glaceemr.server.application.models.InitialSettings;
import com.glenwood.glaceemr.server.application.models.LabAlertforwardstatus;
import com.glenwood.glaceemr.server.application.models.LabAlertforwardstatus_;
import com.glenwood.glaceemr.server.application.models.LabDescpParameters;
import com.glenwood.glaceemr.server.application.models.LabDescription;
import com.glenwood.glaceemr.server.application.models.LabDescription_;
import com.glenwood.glaceemr.server.application.models.LabEntries;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter_;
import com.glenwood.glaceemr.server.application.models.LabEntries_;
import com.glenwood.glaceemr.server.application.models.LabFreqorder;
import com.glenwood.glaceemr.server.application.models.LabFreqorder_;
import com.glenwood.glaceemr.server.application.models.LabGroups;
import com.glenwood.glaceemr.server.application.models.LabIncludePrevious;
import com.glenwood.glaceemr.server.application.models.LabParameterCode;
import com.glenwood.glaceemr.server.application.models.LabParameters;
import com.glenwood.glaceemr.server.application.models.LabcompanyDetails;
import com.glenwood.glaceemr.server.application.models.LabcompanyDetails_;
import com.glenwood.glaceemr.server.application.models.LoginUsers;
import com.glenwood.glaceemr.server.application.models.LoginUsers_;
import com.glenwood.glaceemr.server.application.models.OrdersetCategorylist;
import com.glenwood.glaceemr.server.application.models.OrdersetList;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientEncounterType;
import com.glenwood.glaceemr.server.application.models.PatientImmunityVaccineMapping;
import com.glenwood.glaceemr.server.application.models.PatientImmunityVaccineMapping_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PatientVisEntries;
import com.glenwood.glaceemr.server.application.models.PatientVisEntries_;
import com.glenwood.glaceemr.server.application.models.PrimarykeyGenerator;
import com.glenwood.glaceemr.server.application.models.PrimarykeyGenerator_;
import com.glenwood.glaceemr.server.application.models.Specimen;
import com.glenwood.glaceemr.server.application.models.VaccinationConsentForm;
import com.glenwood.glaceemr.server.application.models.VaccineOrderDetails;
import com.glenwood.glaceemr.server.application.models.VaccineOrderDetails_;
import com.glenwood.glaceemr.server.application.models.VaccineReport;
import com.glenwood.glaceemr.server.application.models.VaccineReport_;
import com.glenwood.glaceemr.server.application.models.Vis;
import com.glenwood.glaceemr.server.application.models.VisFileMapping;
import com.glenwood.glaceemr.server.application.repositories.AlertCategoryRepository;
import com.glenwood.glaceemr.server.application.repositories.AssociatedOrderPblmRepository;
import com.glenwood.glaceemr.server.application.repositories.BodySiteRepository;
import com.glenwood.glaceemr.server.application.repositories.ChartRepository;
import com.glenwood.glaceemr.server.application.repositories.ChartStatusRepository;
import com.glenwood.glaceemr.server.application.repositories.CptRepository;
import com.glenwood.glaceemr.server.application.repositories.CvxVaccineGroupRepository;
import com.glenwood.glaceemr.server.application.repositories.EmpProfileRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterEntityRepository;
import com.glenwood.glaceemr.server.application.repositories.FileDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.FileNameRepository;
import com.glenwood.glaceemr.server.application.repositories.Hl7ResultInboxRepository;
import com.glenwood.glaceemr.server.application.repositories.Hl7UnmappedResultsRepository;
import com.glenwood.glaceemr.server.application.repositories.IcdmRepository;
import com.glenwood.glaceemr.server.application.repositories.ImmunisationSiteRepository;
import com.glenwood.glaceemr.server.application.repositories.InitialSettingsRepository;
import com.glenwood.glaceemr.server.application.repositories.LabAlertforwardstatusRepository;
import com.glenwood.glaceemr.server.application.repositories.LabCompanyDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.LabDescriptionRepository;
import com.glenwood.glaceemr.server.application.repositories.LabEntriesParameterRepository;
import com.glenwood.glaceemr.server.application.repositories.LabEntriesRepository;
import com.glenwood.glaceemr.server.application.repositories.LabGroupRepository;
import com.glenwood.glaceemr.server.application.repositories.LabIncludePreviousRepository;
import com.glenwood.glaceemr.server.application.repositories.LabParameterCodeRepository;
import com.glenwood.glaceemr.server.application.repositories.LabParametersRepository;
import com.glenwood.glaceemr.server.application.repositories.LoginUsersRepository;
import com.glenwood.glaceemr.server.application.repositories.OrderSetCategoryListRepository;
import com.glenwood.glaceemr.server.application.repositories.OrderSetListRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientEncounterTypeRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientImmunityVaccineMappingRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientVisEntriesRepository;
import com.glenwood.glaceemr.server.application.repositories.SpecimenRepository;
import com.glenwood.glaceemr.server.application.repositories.VaccinationConsentFormRepository;
import com.glenwood.glaceemr.server.application.repositories.VaccineOrderDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.VaccineReportRepository;
import com.glenwood.glaceemr.server.application.repositories.VisRepository;
import com.glenwood.glaceemr.server.application.services.alertinbox.AlertInboxService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailService;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalElementsService;
import com.glenwood.glaceemr.server.application.specifications.AlertCategorySpecification;
import com.glenwood.glaceemr.server.application.specifications.InitialSettingsSpecification;
import com.glenwood.glaceemr.server.application.specifications.InvestigationSpecification;
import com.glenwood.glaceemr.server.application.specifications.PatientClinicalElementsSpecification;
import com.glenwood.glaceemr.server.application.specifications.VaccineReportSpecification;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.glenwood.glaceemr.server.utils.SessionMap;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

/**
 * @author yasodha
 * 
 * InvestigationSummaryServiceImpl gives the data required for 
 * investigation summary
 */

@Service
public class InvestigationSummaryServiceImpl implements	InvestigationSummaryService {

	@Autowired
	VaccinationConsentFormRepository vaccinationConsentFormRepository;

	@Autowired
	LabDescriptionRepository labDescriptionRepository;

	@Autowired
	LabParametersRepository labParametersRepository;

	@Autowired
	LabParameterCodeRepository labParameterCodeRepository;

	@Autowired
	LabCompanyDetailsRepository labCompanyRepository;

	@Autowired
	EncounterEntityRepository encounterEntityRepository;

	@Autowired
	LabEntriesRepository labEntriesRepository;

	@Autowired
	EntityManagerFactory emf ;

	@Autowired
	EmpProfileRepository empProfileRepository;

	@Autowired
	CvxVaccineGroupRepository cvxRepository;

	@Autowired
	FileDetailsRepository fileDetailsRepository;

	@Autowired
	PatientVisEntriesRepository patientVISRepository;

	@Autowired
	VisRepository visRepository;

	@Autowired
	ChartStatusRepository h068Repository;

	@Autowired
	BodySiteRepository bodySiteRepository;

	@Autowired
	LabGroupRepository labGroupsRepository;

	@Autowired
	VaccineOrderDetailsRepository vaccineRepository;

	@Autowired
	ImmunisationSiteRepository siteRepository;

	@Autowired
	VaccinationConsentFormRepository consentRepository;

	@Autowired
	PatientRegistrationRepository patientRegistrationRepository;

	@Autowired
	ChartRepository chartRepository;

	@Autowired
	CptRepository cptRepository;

	@Autowired
	Hl7UnmappedResultsRepository hl7UnmappedResultsRepository;

	@Autowired
	IcdmRepository icdmRepository;

	@Autowired
	InitialSettingsRepository initialSettingsRepository;

	@Autowired
	VaccineReportRepository vaccineReportRepository;

	@Autowired
	VaccineOrderDetailsRepository vaccineOrderDetailsRepository;

	@Autowired
	LabEntriesParameterRepository labEntriesParameterRepository;

	@Autowired
	PatientClinicalElementsRepository patientClinicalElementsRepository;

	@Autowired
	LabIncludePreviousRepository labIncludePreviousRepository;

	@Autowired
	FileNameRepository fileNameRepository;

	@Autowired
	PatientVisEntriesRepository patientVisEntriesRepository;

	@Autowired
	OrderSetListRepository orderSetRepository;

	@Autowired
	OrderSetCategoryListRepository orderSetCatRepository;

	@Autowired
	PatientImmunityVaccineMappingRepository patientImmunityVaccineMappingRepository;

	@Autowired
	Hl7ResultInboxRepository hl7ResultInboxRepository;

	@Autowired
	AlertCategoryRepository alertCategoryRepository;

	@Autowired
	LabAlertforwardstatusRepository labAlertforwardstatusRepository;

	@Autowired
	AssociatedOrderPblmRepository associatedOrderPblmRepository;

	@Autowired
	LoginUsersRepository loginUsersRepository;

	@Autowired
	SpecimenRepository specimenRepository;

	@Autowired
	AlertInboxService alertInboxService;

	@Autowired
	PatientEncounterTypeRepository petRepository;

	@Autowired
	ClinicalElementsService clinicalElementsService;

	@PersistenceContext
	EntityManager em;

	@Autowired
	AuditTrailService auditTrailService;

	@Autowired
	SessionMap sessionMap;

	@Autowired
	HttpServletRequest request;

	private Logger logger = Logger.getLogger(InvestigationSummaryServiceImpl.class);
	ResourceBundle resBun = null;
	String savedIds ="";
	int encounterId ;
	int testDetailLotId;
	String edate="";
	int patientId ;
	int userId;
	int chartId;
	int rDoctorId;
	int defaultDoctor;
	int pos;
	String toDay;
	String loginid; 
	String fullData;
	String isforward;
	int toId;
	String ishighpriority = "false";
	String testId = new String();
	Integer empId = -1;

	/**
	 * Method to save investigation data received from HL7 results (Parsing and attaching unknown patient results)
	 * @param attachData
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String saveInvestigation(SaveAttachResultData attachData) throws Exception {
		setResourceBundle();
		String testDetailId = attachData.getTestDetailId(); 
		Integer patientId = -1;
		boolean isUpdate = false;
		this.chartId = attachData.getChartId();
		patientId = getPatientId();
		if ( !testDetailId.equals("-1") ) {
			isUpdate = true;
			List<LabEntries> labEntries = labEntriesRepository.findAll(InvestigationSpecification.testdetailIds(Integer.parseInt(testDetailId)));
			for( LabEntries labs : labEntries ) {
				labs.setLabEntriesTestType(1);
				labs.setLabEntriesPerfBy(attachData.getPerformedBy());
				if(attachData.getPerformedDate().equals(""))
					attachData.setPerformedDate(attachData.getOrderedDate());
				if( !attachData.getPerformedDate().equals("") )
					labs.setLabEntriesPerfOn(Timestamp.valueOf(attachData.getPerformedDate()));
				labs.setLabEntriesTestStatus(attachData.getStatus());
				if( isUpdate ) {
					if( labs.getLabEntriesResultNotes().trim().equals("") )
						labs.setLabEntriesResultNotes(attachData.getResultNotes());
				} else {
					labs.setLabEntriesResultNotes(attachData.getResultNotes());
				}
				labs.setLabEntriesClinicalInfo(attachData.getClinicalInfo());
				if( attachData.getIsFasting().equalsIgnoreCase("Y") ) {
					labs.setLabEntriesIsFasting(true);
				}
				if( !attachData.getSpecimenSource().equals("") ) {
					long specimenId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + getSpecimenData(attachData))).or("-1"));
					if( specimenId == -1 ) {
						Specimen insertSpecimen = new Specimen();
						insertSpecimen.setSpecimenSource(attachData.getSpecimenSource());
						if( !attachData.getSpecimenCondition().equals("") )
							insertSpecimen.setSpecimenCondition(attachData.getSpecimenCondition());
						if( !attachData.getSpecimencollecteddate().equals("") )
							insertSpecimen.setSpecimenDate(Timestamp.valueOf(attachData.getSpecimencollecteddate()));
						if( !attachData.getCollectionVolume().equals("") )
							insertSpecimen.setSpecimenCollVol(attachData.getCollectionVolume());
						specimenRepository.save(insertSpecimen);
						specimenId = getSpecimenData(attachData);
					}
					labs.setLabEntriesSepcimenId(Integer.parseInt("" + specimenId));
				}
				labEntriesRepository.saveAndFlush(labs);
				if( testDetailId.equals("-1") ) {
					testDetailId = getLabTestDetailId();
				}
			}
			Vector paramEntryIdVect = SaveParameters(this.chartId, Integer.parseInt(testDetailId), attachData.getResultParamStr(),attachData.getLabLocCodeDetails(), attachData.getRootPath(), attachData.getResultFileName());
			addLabAlert(patientId,encounterId, Integer.parseInt(testDetailId), attachData.getStatus(), 1, attachData.getOrderedBy());
			for( int j = 0;j < paramEntryIdVect.size() ;j++ ) {
				int fileScanId = Integer.parseInt(HUtil.Nz(getFileScanId(Integer.parseInt("" + paramEntryIdVect.get(j))), "-1"));
				if( fileScanId != -1 ) {
					List<FileDetails> fileList = fileDetailsRepository.findAll(InvestigationSpecification.checkFileScanId(fileScanId));
					for( FileDetails fileData : fileList ) {
						fileData.setFiledetailsEntityid(Integer.parseInt("" + paramEntryIdVect.get(j)));
						fileDetailsRepository.saveAndFlush(fileData);
					}
				}
			}
		}
		return testDetailId;
	}

	@Override
	public String getFileScanId(int parameterId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LabEntriesParameter> root = cq.from(LabEntriesParameter.class);
		cq.select(root.get(LabEntriesParameter_.labEntriesParameterFilenameScanid));
		cq.where(builder.equal(root.get(LabEntriesParameter_.labEntriesParameterId), parameterId));
		return "" + em.createQuery(cq).getFirstResult();
	}

	/**
	 * Method to save parameters from lab results
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Vector SaveParameters (int chartId, int testDetailId, String paramString,Hashtable labLocDetails,String rootPath,String resultFileName) throws Exception {
		String[] paramSet = paramString.split("@#@");
		String[] param;
		int paramId = -1;
		int paramLen = 0;
		int sortOrder = 1;
		Vector paramIdVector = new Vector();
		for(int i=0; i<paramSet.length; i++) {
			param = paramSet[i].split("\\|~\\|");
			paramLen = param.length;
			if(paramLen > paramValueMap("PARAM_ID")) {
				paramId = Integer.parseInt(HUtil.Nz(param[paramValueMap("PARAM_ID")],"-1").trim());
			}
			if(paramId == -1) {
				String paramName	= "";
				String paramUnits	= "";
				String NormalRange	= "";
				String paramCode	= "";
				String paramCodeSys	= "";
				if(paramLen > paramValueMap("PARAM_NAME"))
					paramName = HUtil.ValidateSingleQuote(HUtil.Nz(param[paramValueMap("PARAM_NAME")],"")).trim();
				if(paramLen > paramValueMap("PARAM_UNITS"))
					paramUnits = HUtil.ValidateSingleQuote(HUtil.Nz(param[paramValueMap("PARAM_UNITS")],"")).trim();
				if(paramLen > paramValueMap("PARAM_NORMALRANGE"))
					NormalRange = HUtil.ValidateSingleQuote(HUtil.Nz(param[paramValueMap("PARAM_NORMALRANGE")],"")).trim();
				if(paramLen > paramValueMap("PARAM_CODE"))
					paramCode = HUtil.ValidateSingleQuote(HUtil.Nz(param[paramValueMap("PARAM_CODE")],"")).trim();
				if(paramLen > paramValueMap("PARAM_CODESYSTEM"))
					paramCodeSys = HUtil.ValidateSingleQuote(HUtil.Nz(param[paramValueMap("PARAM_CODESYSTEM")],"")).trim();
				paramId = insertParamDesc(paramName, paramUnits, NormalRange, paramCode, paramCodeSys);
			}
			if(paramId != -1) {
				int labEntryParamId = -1;
				String paramValue = "";
				String paramDate  = "";
				String paramStatus = "";
				String paramNotes = "";
				String paramResultStatus = "";
				String normalRange = "";
				String labLocCode = "-1";
				String paramValuetype = "";
				String paramEncryptionType = "";
				String encryptedFileContent = "";
				String paramName="";
				if(paramLen > paramValueMap("PARAM_NAME"))
					paramName = HUtil.ValidateSingleQuote(Optional.fromNullable(param[paramValueMap("PARAM_NAME")]).or("")).trim();
				if(paramLen > paramValueMap("PARAM_LABENTRYID"))
					labEntryParamId = Integer.parseInt(HUtil.Nz(param[paramValueMap("PARAM_LABENTRYID")],"-1").trim());
				if(paramLen > paramValueMap("PARAM_VALUE"))
					paramValue = HUtil.ValidateSingleQuote(HUtil.Nz(param[paramValueMap("PARAM_VALUE")],"")).trim();
				if(paramLen > paramValueMap("PARAM_DATE"))
					paramDate = HUtil.ValidateSingleQuote(HUtil.Nz(param[paramValueMap("PARAM_DATE")],"")).trim();
				if(paramLen > paramValueMap("PARAM_STATUS"))
					paramStatus = HUtil.ValidateSingleQuote(HUtil.Nz(param[paramValueMap("PARAM_STATUS")],"")).trim();
				if(paramLen > paramValueMap("PARAM_NOTES"))
					paramNotes = HUtil.ValidateSingleQuote(HUtil.Nz(param[paramValueMap("PARAM_NOTES")],""));
				if(paramLen > paramValueMap("PARAM_RESULTSTATUS"))
					paramResultStatus = HUtil.ValidateSingleQuote(HUtil.Nz(param[paramValueMap("PARAM_RESULTSTATUS")],"")).trim();
				if(labEntryParamId==-1)
					DeleteParameters(testDetailId,paramId);
				if(paramLen > paramValueMap("PARAM_NORMALRANGE"))
					normalRange = HUtil.ValidateSingleQuote(HUtil.Nz(param[paramValueMap("PARAM_NORMALRANGE")],"")).trim();
				if(paramLen > paramValueMap("PARAM_LAB_LOC_CODE"))
					labLocCode = HUtil.ValidateSingleQuote(HUtil.Nz(param[paramValueMap("PARAM_LAB_LOC_CODE")],"")).trim();
				if(paramLen > paramValueMap("PARAM_VALUE_TYPE"))
					paramValuetype = HUtil.ValidateSingleQuote(HUtil.Nz(param[paramValueMap("PARAM_VALUE_TYPE")],"")).trim();
				if(paramLen > paramValueMap("PARAM_ENCRYPTION_TYPE"))
					paramEncryptionType = HUtil.ValidateSingleQuote(HUtil.Nz(param[paramValueMap("PARAM_ENCRYPTION_TYPE")],"")).trim();
				if(paramLen > paramValueMap("ENCRYPTED_FILE_CONTENT"))
					encryptedFileContent = HUtil.ValidateSingleQuote(HUtil.Nz(param[paramValueMap("ENCRYPTED_FILE_CONTENT")],"")).trim();
				int fileNameId=-1,fileScanId=-1,isPdf=0;
				if(paramValuetype.equalsIgnoreCase("ED") && paramEncryptionType.equalsIgnoreCase("Base64")) {
					int encounterID = Integer.parseInt(HUtil.Nz(getEncounterId(testDetailId), "-1"));
					this.chartId = chartId;
					int patientID = Integer.parseInt(HUtil.Nz(getPatientId(), "-1"));
					Hashtable fileDetailsHash = putPDFAttacmentEntry(encounterID,patientID,chartId,testDetailId,resultFileName,rootPath,encryptedFileContent);
					if(fileDetailsHash.containsKey("fileNameId")){
						fileNameId = (int) fileDetailsHash.get("fileNameId");
					}
					if(fileDetailsHash.containsKey("fileScanId")){
						fileScanId = (int) fileDetailsHash.get("fileScanId");
					}
					isPdf=1;
				}
				int labLocDetId=-1;
				Hashtable labLocDetail = new Hashtable();
				if(labLocDetails.containsKey(labLocCode)){
					labLocDetail = (Hashtable)labLocDetails.get(labLocCode);
				}
				labLocDetId = insertLabAddressDetails(labLocDetail);
				String paramCode="";
				String paramCodeSystem="";
				List<LabParameterCode> labParameterCodeList=labParameterCodeRepository.findAll(Specifications.where(InvestigationSpecification.getParamId(paramId)).and(InvestigationSpecification.getCodeSystem("LOINC")));
				if(labParameterCodeList.size()>0)
				{
					paramCode=labParameterCodeList.get(0).getLabParameterCodeValue();
					paramCodeSystem="2.16.840.1.113883.6.1";
				}
				if(paramCode.equals(""))
				{
					List<LabParameterCode> labParameterCodeListSnomed=labParameterCodeRepository.findAll(Specifications.where(InvestigationSpecification.getParamId(paramId)).and(InvestigationSpecification.getCodeSystem("SNOMED")));
					if(labParameterCodeList.size()>0)
					{
						paramCode=labParameterCodeList.get(0).getLabParameterCodeValue();
						paramCodeSystem="2.16.840.1.113883.6.96";
					}
				}
				int paramEntryId = insertParamValue(chartId, labEntryParamId, testDetailId, paramId, paramValue, paramDate, paramStatus, paramNotes, paramResultStatus, sortOrder , normalRange,labLocDetId,fileNameId,fileScanId,isPdf,paramName,paramCode,paramCodeSystem);
				paramIdVector.add(paramEntryId);
				sortOrder++;
			}
		}
		return paramIdVector;
	}

	/**
	 * Method to handle multiple ZEF segments for each OBX
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Hashtable putPDFAttacmentEntry(int encounterId,int patientID,int chartId,int testDetailId,String resultFileName,String rootPath,String encryptedData)throws Exception{
		resultFileName = resultFileName.substring(0,resultFileName.lastIndexOf("_"));
		Hashtable fileDetailsHash = new Hashtable();
		try {			
			int categoryId = Integer.parseInt(HUtil.Nz(getScanGroupId(testDetailId),"1"));
			FileDetails fileDetails = new FileDetails();
			fileDetails.setFiledetailsFlag(2);
			fileDetails.setFiledetailsDescription("PDF Report");
			Date date = new Date();
			Timestamp currentTime = new Timestamp(date.getTime());
			fileDetails.setFiledetailsCreationdate(currentTime);
			fileDetails.setFiledetailsCreatedby(-100);
			fileDetails.setFiledetailsLastmodifiedon(currentTime);
			fileDetails.setFiledetailsLastmodifiedby(-100);
			fileDetails.setFiledetailsEncounterid(encounterId);
			fileDetails.setFiledetailsPatientid(patientID);
			fileDetails.setFiledetailsChartid(chartId);
			fileDetails.setFiledetailsEntityid(-1);
			fileDetails.setFiledetailsCategoryid(categoryId);
			fileDetailsRepository.save(fileDetails);
			this.chartId = chartId;
			this.patientId = patientID;
			this.encounterId = encounterId;
			int filedetailsId = Integer.parseInt(getFileDetailId(categoryId, currentTime));
			decodeToPDF(encryptedData, rootPath + "/Attachments/" + patientID + "/", resultFileName + "_" + filedetailsId);
			FileName fileName = new FileName();
			fileName.setFilenameScanid(filedetailsId);
			fileName.setFilenameName(resultFileName+"_" + filedetailsId + ".pdf");
			fileName.setFilenameFileextension(".pdf");
			fileName.setFilenameIsactive(true);
			fileNameRepository.save(fileName);
			int fileNameId = Integer.parseInt(getFileNameId(filedetailsId, resultFileName+"_" + filedetailsId + ".pdf"));
			fileDetailsHash.put("fileNameId",fileNameId);
			fileDetailsHash.put("fileScanId",filedetailsId);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fileDetailsHash;
	}

	private String getFileNameId(Integer filedetailsId, String fileName) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<FileName> root = cq.from(FileName.class);
		cq.select(root.get(FileName_.filenameId));
		cq.where(builder.and(builder.equal(root.get(FileName_.filenameScanid), filedetailsId),
				builder.equal(root.get(FileName_.filenameName), fileName),
				builder.equal(root.get(FileName_.filenameIsactive), true),
				builder.equal(root.get(FileName_.filenameFileextension), ".pdf")));
		return "" + em.createQuery(cq).getFirstResult();
	}

	/**
	 * Method to get file detail id
	 * @param categoryId
	 * @return
	 */
	private String getFileDetailId(Integer categoryId, Timestamp currentTime) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<FileDetails> root = cq.from(FileDetails.class);
		cq.select(root.get(FileDetails_.filedetailsId));
		cq.where(builder.and(builder.equal(root.get(FileDetails_.filedetailsCategoryid), categoryId),
				builder.equal(root.get(FileDetails_.filedetailsChartid), chartId),
				builder.equal(root.get(FileDetails_.filedetailsPatientid), patientId),
				builder.equal(root.get(FileDetails_.filedetailsEncounterid), encounterId),
				builder.equal(root.get(FileDetails_.filedetailsFlag), 2),
				builder.equal(root.get(FileDetails_.filedetailsDescription), "PDF Report"),
				builder.equal(root.get(FileDetails_.filedetailsCreationdate), currentTime),
				builder.equal(root.get(FileDetails_.filedetailsLastmodifiedon), currentTime)));
		return "" + em.createQuery(cq).getFirstResult();
	}

	/**
	 * Method to decode to pdf
	 * @param encypedData
	 * @param destPath
	 * @param resFilename
	 * @return
	 * @throws Exception
	 */
	@Override
	public String decodeToPDF(String encypedData,String destPath,String resFilename)throws Exception {
		FileOutputStream fout = null;
		BufferedOutputStream buffout = null;
		String pdfFileName=resFilename+".pdf";
		try {
			Base64 decoder = new Base64();
			byte[] decodedBytes = decoder.decode(encypedData.toString());
			File destDir = new File (destPath);
			if(!destDir.exists()){
				destDir.mkdir();
			}
			fout = new FileOutputStream(new File(destPath+"/"+pdfFileName));
			buffout = new BufferedOutputStream(fout);
			buffout.write(decodedBytes);
			buffout.flush();
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if(buffout != null)
					buffout.close();
			} catch(Exception ie){}
			try {
				if(fout != null)
					fout.close();
			} catch(Exception ie){}
		}
		return pdfFileName;
	}
	/**
	 * Method to get scan group id
	 * @param testDetailId
	 * @return
	 */
	private String getScanGroupId(int testDetailId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LabEntries> root = cq.from(LabEntries.class);
		cq.select(root.get(LabEntries_.labEntriesScangroupId));
		cq.where(builder.equal(root.get(LabEntries_.labEntriesTestdetailId), testDetailId));
		return "" + em.createQuery(cq).getFirstResult();
	}

	/**
	 * Method to get encounter id
	 * @param testDetailId
	 * @return
	 */
	private String getEncounterId(Integer testDetailId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LabEntries> root = cq.from(LabEntries.class);
		cq.select(root.get(LabEntries_.labEntriesEncounterId));
		cq.where(builder.equal(root.get(LabEntries_.labEntriesTestdetailId), testDetailId));
		return "" + em.createQuery(cq).getFirstResult();
	}

	/**
	 * Method to handle multiple zps segments for external results
	 */
	@SuppressWarnings("rawtypes")
	public Integer insertLabAddressDetails(Hashtable locationDetail) {
		int labCompAddrId = -1;
		try {
			labCompAddrId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + getLabCompanyAddrId(locationDetail))).or("-1"));
			if( labCompAddrId == -1 ) {
				LabcompanyDetails labCompanyDetails = new LabcompanyDetails();
				labCompanyDetails.setLabcompanyDetailsLabname(HUtil.Nz(locationDetail.get("labLocName"),"").toString().trim());
				labCompanyDetails.setLabcompanyDetailsLabaddress(HUtil.Nz(locationDetail.get("labLocAddr"),"").toString().trim());
				labCompanyDetails.setLabcompanyDetailsLabcity(HUtil.Nz(locationDetail.get("labLocCity"),"").toString().trim());
				labCompanyDetails.setLabcompanyDetailsLabstate(HUtil.Nz(locationDetail.get("labLocState"),"").toString().trim());
				labCompanyDetails.setLabcompanyDetailsLabzip(HUtil.Nz(locationDetail.get("labLocZip"),"").toString().trim());
				labCompanyDetails.setLabcompanyDetailsDirectorName(HUtil.Nz(locationDetail.get("labDirector"),"").toString().trim());
				labCompanyDetails.setLabcompanyDetailsClianumber(HUtil.Nz(locationDetail.get("cliaNumber"),"").toString().trim());
				labCompanyDetails.setLabcompanyDetailsDirectorPhonenumber(HUtil.Nz(locationDetail.get("labDirectorPhoneNumber"),"").toString().trim());
				labCompanyDetails.setLabcompanyDetailsLabloccode(HUtil.Nz(locationDetail.get("labLocCode"),"").toString().trim());
				labCompanyRepository.save(labCompanyDetails);
				labCompAddrId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + getLabCompanyAddrId(locationDetail))).or("-1"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return labCompAddrId;
	}

	/**
	 * Method to get lab company details id
	 * @param locationDetail
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String getLabCompanyAddrId(Hashtable locationDetail) {
		String addrId = "-1";
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LabcompanyDetails> root = cq.from(LabcompanyDetails.class);
		cq.select(root.get(LabcompanyDetails_.labcompanyDetailsId));
		cq.where(builder.and(builder.equal(root.get(LabcompanyDetails_.labcompanyDetailsLabname), Optional.fromNullable(Strings.emptyToNull("" + locationDetail.get("labLocName").toString().trim())).or(""))),
				builder.equal(root.get(LabcompanyDetails_.labcompanyDetailsLabaddress), Optional.fromNullable(Strings.emptyToNull("" + locationDetail.get("labLocAddr").toString().trim())).or("")),
				builder.equal(root.get(LabcompanyDetails_.labcompanyDetailsLabcity), Optional.fromNullable(Strings.emptyToNull("" + locationDetail.get("labLocCity").toString().trim())).or("")),
				builder.equal(root.get(LabcompanyDetails_.labcompanyDetailsLabstate), Optional.fromNullable(Strings.emptyToNull("" + locationDetail.get("labLocState").toString().trim())).or("")),
				builder.equal(root.get(LabcompanyDetails_.labcompanyDetailsLabzip), Optional.fromNullable(Strings.emptyToNull("" + locationDetail.get("labLocZip").toString().trim())).or("")),
				builder.equal(root.get(LabcompanyDetails_.labcompanyDetailsDirectorName), Optional.fromNullable(Strings.emptyToNull("" + locationDetail.get("labDirector").toString().trim())).or("")),
				builder.equal(root.get(LabcompanyDetails_.labcompanyDetailsClianumber), Optional.fromNullable(Strings.emptyToNull("" + locationDetail.get("cliaNumber").toString().trim())).or("")),
				builder.equal(root.get(LabcompanyDetails_.labcompanyDetailsDirectorPhonenumber), Optional.fromNullable(Strings.emptyToNull("" + locationDetail.get("labDirectorPhoneNumber").toString().trim())).or("")),
				builder.equal(root.get(LabcompanyDetails_.labcompanyDetailsLabloccode), Optional.fromNullable(Strings.emptyToNull("" + locationDetail.get("labLocCode").toString().trim())).or("")));
		addrId = "" + em.createQuery(cq).getFirstResult(); 
		return addrId;
	}

	/**
	 * Insert parameters from lab entries
	 * @return
	 * @throws Exception
	 */
	private int insertParamValue(int chartId, int labEntryParamId,
			int testDetailId, int paramId, String paramValue, String performedDate,
			String paramStatus, String paramNotes, String paramResultStatus,
			int sortOrder, String normalRange, int labLocDetId, int fileNameId,
			int fileScanId, int isPdf,String paramName,String paramCode,String paramCodeSystem) throws Exception {
		if(labEntryParamId == -1) {
			List<LabEntriesParameter> labEntriesParameterList=labEntriesParameterRepository.findAll(Specifications.where(InvestigationSpecification.getParamEntriesIsActive(true)).and(InvestigationSpecification.getParamEntriesTestDetailIdNot(-1)).and(InvestigationSpecification.getParamEntriesTestDetailId(testDetailId)).and(InvestigationSpecification.getParamEntriesMapId(paramId)));
			if(labEntriesParameterList.size()>0)
				labEntryParamId = Optional.fromNullable(labEntriesParameterList.get(0).getLabEntriesParameterId()).or(-1);
		}
		if(labEntryParamId != -1) {
			List<LabEntriesParameter> labEntriesParameterList=labEntriesParameterRepository.findAll(InvestigationSpecification.getParamEntriesId(labEntryParamId));
			for(int h=0;h<labEntriesParameterList.size();h++){
				labEntriesParameterList.get(h).setLabEntriesParameterValue(paramValue);
				labEntriesParameterList.get(h).setLabEntriesParameterStatus(paramStatus);
				labEntriesParameterList.get(h).setLabEntriesParameterNotes(paramNotes);
				labEntriesParameterList.get(h).setLabEntriesParameterChartid(chartId);
				if(!performedDate.equals(""))
					labEntriesParameterList.get(h).setLabEntriesParameterDate(Timestamp.valueOf(performedDate));
				labEntriesParameterList.get(h).setLabEntriesParameterResultstatus(paramResultStatus);
				labEntriesParameterList.get(h).setLabEntriesParameterSortorder(sortOrder);
				if(!normalRange.equals(""))
					labEntriesParameterList.get(h).setLabEntriesParameterNormalrange(normalRange);
				labEntriesParameterList.get(h).setLabEntriesParameterLabcompDetailid(labLocDetId);
				labEntriesParameterList.get(h).setLabEntriesParameterIspdf(isPdf);
				labEntriesParameterList.get(h).setLabEntriesParameterFilenameId(fileNameId);
				labEntriesParameterList.get(h).setLabEntriesParameterFilenameScanid(fileScanId);
				labEntriesParameterList.get(h).setLabEntriesParameterName(paramName);
				labEntriesParameterList.get(h).setLabEntriesParameterCode(paramCode);
				labEntriesParameterList.get(h).setLabEntriesParameterCodeSystem(paramCodeSystem);
				labEntriesParameterRepository.saveAndFlush(labEntriesParameterList.get(h));
			}
			return labEntryParamId;
		} else {
			LabEntriesParameter labEntriesParameterTemp = new LabEntriesParameter();
			labEntriesParameterTemp.setLabEntriesParameterTestdetailid(testDetailId);
			labEntriesParameterTemp.setLabEntriesParameterMapid(paramId);
			labEntriesParameterTemp.setLabEntriesParameterValue(paramValue);
			labEntriesParameterTemp.setLabEntriesParameterStatus(paramStatus);
			labEntriesParameterTemp.setLabEntriesParameterNotes(paramNotes);
			labEntriesParameterTemp.setLabEntriesParameterChartid(chartId);
			if(!performedDate.equals("")) {
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				DateFormat parser = new SimpleDateFormat("MM/dd/yyyy"); 
				Date date = null;
				try {
					date = (Date) parser.parse(performedDate);

				} catch (ParseException e) {
					e.printStackTrace();
				}
				performedDate = formatter.format(date);
				labEntriesParameterTemp.setLabEntriesParameterDate(Timestamp.valueOf(performedDate));
			}
			labEntriesParameterTemp.setLabEntriesParameterResultstatus(paramResultStatus);
			labEntriesParameterTemp.setLabEntriesParameterSortorder(sortOrder);
			if(!normalRange.equals(""))
				labEntriesParameterTemp.setLabEntriesParameterNormalrange(normalRange);
			labEntriesParameterTemp.setLabEntriesParameterLabcompDetailid(labLocDetId);
			labEntriesParameterTemp.setLabEntriesParameterIspdf(isPdf);
			labEntriesParameterTemp.setLabEntriesParameterFilenameId(fileNameId);
			labEntriesParameterTemp.setLabEntriesParameterFilenameScanid(fileScanId);
			labEntriesParameterTemp.setLabEntriesParameterName(paramName);
			labEntriesParameterTemp.setLabEntriesParameterCode(paramCode);
			labEntriesParameterTemp.setLabEntriesParameterCodeSystem(paramCodeSystem);
			labEntriesParameterRepository.saveAndFlush(labEntriesParameterTemp);
			List<LabEntriesParameter> labEntriesParameterList=labEntriesParameterRepository.findAll(Specifications.where(InvestigationSpecification.getParamEntriesIsActive(true)).and(InvestigationSpecification.getParamEntriesTestDetailIdNot(-1)).and(InvestigationSpecification.getParamEntriesTestDetailId(testDetailId)).and(InvestigationSpecification.getParamEntriesMapId(paramId)));
			if(labEntriesParameterList.size()>0)
				labEntryParamId = Optional.fromNullable(labEntriesParameterList.get(0).getLabEntriesParameterId()).or(-1);
			return labEntryParamId;
		}		
	}

	/**
	 * Method to add new lab alert in inbox page
	 * @param patientId
	 * @param encounterId
	 * @param testDetailId
	 * @param status
	 * @param isExternal
	 * @param userId
	 * @throws Exception
	 */
	@Override
	public void addLabAlert(int patientId,int encounterId, int testDetailId, int status ,int isExternal, int userId) throws Exception {	
		String Message = Optional.fromNullable(Strings.emptyToNull("" + getTestName(testDetailId))).or("");				
		AlertEvent alertEvent = alertInboxService.getAlertId(patientId, encounterId, testDetailId);
		try {
			switch( status ) {
			case 1: String isDefault    = Optional.fromNullable(Strings.emptyToNull("" + getAlertForwardStatus(status))).or("t");
			String scheduledDate = Optional.fromNullable(Strings.emptyToNull("" + getLabScheduledDate(testDetailId))).or("-1");
			if( isDefault.equalsIgnoreCase("t") ) {
				Message = Message + " has to be done."; 
				if( !scheduledDate.equals("") )
					Message = Message + ".\nScheduled Date: "+scheduledDate;
				putLabAlert(encounterId, testDetailId, status, -1, isExternal, Message, alertEvent);
			}
			break;
			case 2: deleteLabAlert(alertEvent);
			break;
			case 3: Message = Message + " has been done,result need to be reviewed"; 
			putLabAlert(encounterId, testDetailId, status, -1, isExternal, Message, alertEvent);
			break;
			case 4: Message = Message + " result has been reviewed,Patient has to be informed."; 
			putLabAlert(encounterId, testDetailId, status, -1, isExternal, Message, alertEvent);
			break;
			case 5: deleteLabAlert(alertEvent);
			break;
			case 6: deleteLabAlert(alertEvent);
			break;
			case 7: deleteLabAlert(alertEvent);
			break;
			}
		} catch( Exception e ) {
			e.printStackTrace();
			throw e;
		}		
	}

	/**
	 * Method to get lab entries scheduled date
	 * @param testDetailId
	 * @return
	 */
	private String getLabScheduledDate(Integer testDetailId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LabEntries> root = cq.from(LabEntries.class);
		cq.select(root.get(LabEntries_.labEntriesScheduleddate));
		cq.where(builder.equal(root.get(LabEntries_.labEntriesTestdetailId), testDetailId));
		return "" + em.createQuery(cq).getFirstResult();
	}

	/**
	 * Method to get lab alert forward status
	 * @param status
	 * @return
	 */
	private String getAlertForwardStatus(Integer status) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LabAlertforwardstatus> root = cq.from(LabAlertforwardstatus.class);
		cq.select(root.get(LabAlertforwardstatus_.labAlertforwardstatusTodefault));
		cq.where(builder.equal(root.get(LabAlertforwardstatus_.labAlertforwardstatusLabstatus), status));
		return "" + em.createQuery(cq).getFirstResult();
	}

	/**
	 * Method to get test name
	 * @param testDetailId
	 * @return
	 */
	private String getTestName(Integer testDetailId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LabEntries> root = cq.from(LabEntries.class);
		cq.select(root.get(LabEntries_.labEntriesTestDesc));
		cq.where(builder.equal(root.get(LabEntries_.labEntriesTestdetailId), testDetailId));
		return "" + em.createQuery(cq).getFirstResult();
	}

	/**
	 * Method to get lab test detail Id after inserting in to lab entries from hl7
	 * @return
	 */
	private String getLabTestDetailId() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PrimarykeyGenerator> root = cq.from(PrimarykeyGenerator.class);
		cq.select(root.get(PrimarykeyGenerator_.primarykey_generator_rowcount));
		cq.where(builder.equal(root.get(PrimarykeyGenerator_.primarykey_generator_tablename),"lab_entries"));
		return "" + em.createQuery(cq).getFirstResult();
	}

	/**
	 * Method to get specimen id
	 * @param attachData
	 * @return
	 */
	private Integer getSpecimenData(SaveAttachResultData attachData) {
		Specimen specimen = specimenRepository.findOne(Specifications.where(InvestigationSpecification.specimenSource(attachData.getSpecimenSource()))
				.and(InvestigationSpecification.specimenColVol(attachData.getCollectionVolume()))
				.and(InvestigationSpecification.specimenColVolUnit(""))
				.and(InvestigationSpecification.specimenCondition(attachData.getSpecimenCondition())));
		return specimen.getSpecimenId();
	}

	/**
	 * Method to get Patient id based on chart
	 * @return
	 */
	private Integer getPatientId() {
		Integer patientId = -1;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Chart> root = cq.from(Chart.class);
		cq.select(root.get(Chart_.chartPatientid));
		cq.where(builder.equal(root.get(Chart_.chartId), this.chartId));
		patientId = (Integer)em.createQuery(cq).getSingleResult();
		return patientId;
	}

	/**
	 * Method to get order set data
	 * @throws Exception
	 */
	public List<OrderSetData> findOrderSetData() throws Exception {
		List<OrderSetData> orderSet = new ArrayList<OrderSetData>();
		List<OrdersetList> orderSetList = orderSetRepository.findAll(InvestigationSpecification.getOrderSet());
		for (int i = 0; i < orderSetList.size(); i++) {
			OrdersetList orderSetData = orderSetList.get(i);
			OrderSetData orderData = new OrderSetData();
			List<OrdersetCategorylist> categoryList = orderSetCatRepository.findAll(InvestigationSpecification.checkOrderSetId(orderSetData.getSetId()));
			if( categoryList.size() > 0 ) {
				OrdersetCategorylist categoryData = categoryList.get(0);
				String[] associatedList = categoryData.getAssociatedList().trim().split(",");
				ArrayList<Integer> list = new ArrayList<Integer>();
				for (int j = 0; j < associatedList.length; j++) {
					list.add(Integer.parseInt(associatedList[j]));
				}
				List<LabDescription> labData = labDescriptionRepository.findAll(Specifications.where(InvestigationSpecification.checkOrderSetId(list)).and(InvestigationSpecification.labIsActive()));
				orderData.setOrderSetId("" + orderSetData.getSetId());
				orderData.setOrderSetName(orderSetData.getSetName());
				orderData.setLabDetailsList(labData);
				orderSet.add(orderData);
			}
		}
		return orderSet;
	}

	/**
	 * Method to save newly added lab
	 */
	@Override
	public String saveNewLab(LabDescription testDetails, Integer encounterId, Integer testId,Integer userId,Integer chartId, Integer patientId) throws Exception {		
		String saveData = formSaveObject(testDetails, encounterId, testId, chartId);
		this.chartId = chartId;
		savelab("1", encounterId, patientId, chartId, userId, saveData, "-1", "-1", "false", "" + testId);
		return "success";
	}

	/**
	 * Method to form save object for saving new lab
	 * @param testDetails
	 */
	private String formSaveObject(LabDescription testDetails, Integer encounterId, Integer testId, Integer chartId) {
		String saveObject = "";
		saveObject = saveObject + "lab_isnew_col_0~@@~" + "0" + "#@@#";
		saveObject = saveObject + "lab_ismodified_col_0~@@~" + "1" + "#@@#";
		saveObject = saveObject + "lab_isbilled_col_0~@@~" + "1" + "#@@#";
		saveObject = saveObject + "lab_testdetailid_col_0~@@~" + "-1" + "#@@#";
		saveObject = saveObject + "lab_encounterid_col_0~@@~"+ encounterId +"#@@#";
		saveObject = saveObject + "lab_chartid_col_0~@@~" + chartId + "#@@#";
		saveObject = saveObject + "lab_testid_col_0~@@~" + testId +"#@@#";
		saveObject = saveObject + "lab_groupid_col_0~@@~" + testDetails.getLabDescriptionGroupid() +"#@@#";
		saveObject = saveObject + "lab_dx1id_col_0~@@~" + "-1" +"#@@#";
		saveObject = saveObject + "lab_dx2id_col_0~@@~" + "-1" +"#@@#";
		saveObject = saveObject + "lab_dx1code_col_0~@@~" + Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionDefDx1())).or("") +"#@@#";
		saveObject = saveObject + "lab_dx2code_col_0~@@~" + Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionDefDx2())).or("") +"#@@#";
		saveObject = saveObject + "lab_dx3code_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_dx4code_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_dx5code_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_dx6code_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_dx7code_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_dx8code_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_dx1code_codesystem_col_0~@@~" + Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionDefDx1codeCodesystem())).or("") +"#@@#";
		saveObject = saveObject + "lab_dx2code_codesystem_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_dx3code_codesystem_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_dx4code_codesystem_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_dx5code_codesystem_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_dx6code_codesystem_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_dx7code_codesystem_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_dx8code_codesystem_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_teststatus_col_0~@@~" + "1" +"#@@#";
		String[] cptDetails = getCPTDataAndQuantity(testDetails);
		String cptQuantity = "";
		@SuppressWarnings("unused")
		String cptData = "";
		if( cptDetails.length > 0)  {
			cptData = cptDetails[0];
		}
		if( cptDetails.length > 1) {
			cptQuantity = cptDetails[1];
		}
		saveObject = saveObject + "lab_qnty_col_0~@@~" + cptQuantity +"#@@#";
		String defaultCptData = Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionDefCpt1())).or("");
		if(!Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionDefCpt2())).or("").equalsIgnoreCase("") ) {
			if( !defaultCptData.equals("") ) {
				defaultCptData += "," + Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionDefCpt2())).or("");
			} else {
				defaultCptData += Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionDefCpt2())).or("");
			}
		}
		if(!Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionDefCpt3())).or("").equalsIgnoreCase("") ) {
			if( !defaultCptData.equals("") ) {
				defaultCptData += "," + Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionDefCpt3())).or("");
			} else {
				defaultCptData += Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionDefCpt3())).or("");
			}
		}
		if(!Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionDefCpt4())).or("").equalsIgnoreCase("") ) {
			if( !defaultCptData.equals("") ) {
				defaultCptData += "," + Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionDefCpt4())).or("");
			} else {
				defaultCptData += Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionDefCpt4())).or("");
			}
		}
		saveObject = saveObject + "lab_cpt_col_0~@@~" + defaultCptData +"#@@#";
		saveObject = saveObject + "lab_labname_col_0~@@~" + Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionTestDesc())).or("") +"#@@#";
		saveObject = saveObject + "lab_printxslurl_col_0~@@~" + Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionPrintxslurl())).or("") +"#@@#";
		String visitType = getVisitType(encounterId);
		if( visitType.equalsIgnoreCase("f") ) {
			visitType = "1";
		} else {
			visitType = "3";
		}
		saveObject = saveObject + "lab_ischdp_col_0~@@~" + visitType +"#@@#";
		saveObject = saveObject + "lab_instruction_col_0~@@~" + Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionInstruction())).or(" ") +"#@@#";
		saveObject = saveObject + "lab_isbillable_col_0~@@~" + testDetails.getLabDescriptionBillable() +"#@@#";
		saveObject = saveObject + "lab_confirmresult_col_0~@@~" + "0" +"#@@#";
		saveObject = saveObject + "lab_drug_col_0~@@~" + testDetails.getLabDescriptionDrugs() +"#@@#";
		saveObject = saveObject + "lab_orderby_col_0~@@~" + this.empId +"#@@#";
		Date date = new Date();
		saveObject = saveObject + "lab_enteredon_col_0~@@~" + new Timestamp(date.getTime()) +"#@@#";
		saveObject = saveObject + "lab_enteredby_col_0~@@~" + this.empId +"#@@#";
		saveObject = saveObject + "lab_bodysitecode_col_0~@@~" + " " +"#@@#";
		saveObject = saveObject + "lab_bodysitedesc_col_0~@@~" + " " +"#@@#";
		saveObject = saveObject + "lab_performon_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_performby_col_0~@@~" + "-1" +"#@@#";
		saveObject = saveObject + "lab_reviewon_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_reviewby_col_0~@@~" + "-1" +"#@@#";
		saveObject = saveObject + "lab_informon_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_informby_col_0~@@~" + "-1" +"#@@#";
		saveObject = saveObject + "lab_patientvisinfoentries_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_persumeimmunityifo_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_loinc_col_0~@@~" + Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionLoinc())).or(" ") +"#@@#";
		saveObject = saveObject + "lab_snomed_col_0~@@~" + Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionSnomed())).or(" ") +"#@@#";
		saveObject = saveObject + "lab_status_col_0~@@~" + "0" +"#@@#";
		String orderedDate = getOrderedDate(encounterId);
		saveObject = saveObject + "lab_orderon_col_0~@@~" + orderedDate +"#@@#";
		saveObject = saveObject + "lab_shortnotes_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_intext_col_0~@@~" + testDetails.getLabDescriptionDefaultType() +"#@@#";
		saveObject = saveObject + "lab_scheduled_date_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_sourceofspecimen_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_collectionvolume_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_specimendate_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_collectionunits_col_0~@@~" + "" +"#@@#";
		saveObject = saveObject + "lab_short_order_notes_col_0~@@~" + "Order Notes" +"#@@#";
		saveObject = saveObject + "lab_isfasting_col_0~@@~" + Optional.fromNullable(Strings.emptyToNull("" + testDetails.getLabDescriptionIsfasting())).or("f") +"#@@#";
		List<LabDescription> ResultdataAry = getNewLabParameters(testId);
		String appendResult = "";
		if( ResultdataAry.size() > 0 ) {
			for(int rstCount = 0 ; rstCount < ResultdataAry.size();rstCount++ ) {
				LabDescription Resultdata = ResultdataAry.get(rstCount);
				List<LabDescpParameters> descparams = Resultdata.getLabDescParams();
				if( descparams.size() > 0 ) {
					//for (int i = 0; i < descparams.size(); i++) {
						LabParameters labParams = descparams.get(rstCount).getLabParametersTable();
						Resultdata = ResultdataAry.get(rstCount);
						appendResult += "-1" + " |~|";
						appendResult += labParams.getLabParametersId() + " |~|";
						appendResult += labParams.getLabParametersDisplayname() + " |~|";
						appendResult += " " + " |~|";
						appendResult += "" + " |~|";  //Date 
						appendResult += "N" + " |~|";  //Normal Range
						appendResult += "" + " |~|"; //Notes;
						appendResult += "" + " @#@"; //Status	
					//}
				}
			}
		}
		saveObject = saveObject + "lab_resultdata_col_0~@@~" + appendResult +"#@@#";
		saveObject = saveObject + "lab_lotno_col_0~@@~" + "-1" +"#@@#";
		saveObject = saveObject + "lab_oldlotno_col_0~@@~" + "-1" +"#@@#";
		saveObject = saveObject + "lab_ndc_col_0~@@~" + Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionNdc())).or("") +"#@@#";
		saveObject = saveObject + "lab_cvx_col_0~@@~" + Optional.fromNullable(Strings.emptyToNull(testDetails.getLabDescriptionCvx())).or("-1") +"#@@#";
		saveObject = saveObject + "lab_refusalreason_col_0~@@~" + "0" +"#@@#";
		saveObject = saveObject + "lab_administration_notes_col_0~@@~" + "0" +"#@@#";
		return saveObject;
	}

	private String[] getCPTDataAndQuantity(LabDescription testDetails) {
		String cptData = "";
		String cptQuantity = "";
		String cptCode = "";
		String mod = "";
		String mod2 = "";
		JSONObject mod12Json = new JSONObject();
		JSONObject modJson = new JSONObject();
		JSONObject cptJson = new JSONObject();
		try {
			mod12Json.put("mod12", testDetails.getLabDescriptionDefMod12());
			mod12Json.put("mod22", testDetails.getLabDescriptionDefMod22());
			mod12Json.put("mod32", testDetails.getLabDescriptionDefMod32());
			mod12Json.put("mod42", testDetails.getLabDescriptionDefMod42());
			modJson.put("mod1", testDetails.getLabDescriptionDefMod1());
			modJson.put("mod2", testDetails.getLabDescriptionDefMod2());
			modJson.put("mod3", testDetails.getLabDescriptionDefMod3());
			modJson.put("mod4", testDetails.getLabDescriptionDefMod4());
			if ( !Optional.fromNullable(Strings.emptyToNull("" + testDetails.getLabDescriptionDefCpt1())).or("").equals("") ) {
//				if( getCPTs(Optional.fromNullable(Strings.emptyToNull("" + testDetails.getLabDescriptionDefCpt1().split("~")[0])).or("")).size() < 1 )
					cptJson.put("cpt1" , testDetails.getLabDescriptionDefCpt1().split("~")[0]);
			}
			if ( !Optional.fromNullable(Strings.emptyToNull("" + testDetails.getLabDescriptionDefCpt2())).or("").equals("") ) {
//				if ( getCPTs(Optional.fromNullable(Strings.emptyToNull("" + testDetails.getLabDescriptionDefCpt2().split("~")[0])).or("")).size() < 1 )
					cptJson.put("cpt2" , testDetails.getLabDescriptionDefCpt2().split("~")[0]);
			}
			if ( !Optional.fromNullable(Strings.emptyToNull("" + testDetails.getLabDescriptionDefCpt3())).or("").equals("") ) {
//				if ( getCPTs(Optional.fromNullable(Strings.emptyToNull("" + testDetails.getLabDescriptionDefCpt3().split("~")[0])).or("")).size() < 1 )
					cptJson.put("cpt3" , testDetails.getLabDescriptionDefCpt3().split("~")[0]);
			}
			if ( !Optional.fromNullable(Strings.emptyToNull("" + testDetails.getLabDescriptionDefCpt4())).or("").equals("") ) {
//				if ( getCPTs(Optional.fromNullable(Strings.emptyToNull("" + testDetails.getLabDescriptionDefCpt4().split("~")[0])).or("")).size() < 1 )
					cptJson.put("cpt4" , testDetails.getLabDescriptionDefCpt4().split("~")[0]);
			}
			for (int i = 1; i <= 4 ; i++) {
				cptCode = Optional.fromNullable(Strings.emptyToNull((cptJson.getString("cpt" + i)).trim())).or("");
				mod = Optional.fromNullable(Strings.emptyToNull((modJson.getString("mod" + i)).trim())).or("");
				mod2 = Optional.fromNullable(Strings.emptyToNull((mod12Json.getString("mod" + i + "2")).trim())).or("");
				if( (!cptCode.equals("")) && ( getCPTs(Optional.fromNullable(Strings.emptyToNull("" + cptJson.getString("cpt" + i).split("~")[0])).or("")).size() > 0 )){
					if( (!mod.equals("")) && (!mod2.equals("")) )
						cptData += cptCode.split("~")[0] + "-" + mod + "-" + mod2 + ",";
					else if( (!mod.equals("")) && (mod2.equals("")) )
						cptData += cptCode.split("~")[0] + "-" + mod + ",";
					else if((!mod2.equals("")) && (mod.equals("")))
						cptData += cptCode.split("~")[0] + "-" + mod2 + ",";
					else
						cptData += cptCode.split("~")[0] + ",";
					if ( cptCode.split("~").length > 1 ) {
						cptQuantity += cptCode + "^";
					} else {
						cptQuantity += cptCode + "~1^";
					}
				}			
			}
			if( cptData.length() > 0 ) {
				cptData = cptData.substring(0,cptData.length()-1);
			}
		} catch(Exception e) {

		}
		String[] cptDetails = new String[2];
		cptDetails[0] = cptData;
		cptDetails[1] = cptQuantity;
		return cptDetails;
	}

	private List<LabDescription> getNewLabParameters(Integer testid) {
		List<LabDescription> labDesc = labDescriptionRepository.findAll(Specifications.where(InvestigationSpecification.getNewLabParams(testid)));
		return labDesc;
	}

	private String getOrderedDate(Integer encounterId) {
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		String orderedOn = timeStamp.toString();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Encounter> root = cq.from(Encounter.class);
		cq.select(root.get(Encounter_.encounterDate));
		cq.where(builder.equal(root.get(Encounter_.encounterId),encounterId));
		Timestamp encounterDate = (Timestamp) em.createQuery(cq).getSingleResult();
		if( encounterDate == timeStamp ) {
			return orderedOn;
		} else {
			return encounterDate.toString();
		}
	}

	private String getVisitType(Integer encounterId) {
		String isCHDPVisit = "f";
		if ( encounterId != -1 ) {
			List<Encounter> encounterTypeList = encounterEntityRepository.findAll(Specifications.where(InvestigationSpecification.checkEncounterId(encounterId)));
			Encounter encounterData = encounterTypeList.get(0);
			PatientEncounterType petType = encounterData.getPatientEncounterType();
			if( petType.getGroupid() == 2 || petType.getGroupid() == 4 ) {
				isCHDPVisit = "f";
			} else {
				isCHDPVisit = "t";
			}
		}
		return isCHDPVisit;
	}

	private Vector<CPTDTO> getCPTs(String cptCode) {
		Vector<CPTDTO> cptVector = new Vector<CPTDTO>();
		CPTDTO cpt = null;
		List<Cpt> cptList = cptRepository.findAll(Specifications.where(InvestigationSpecification.checkCPTId(cptCode)));
		if( cptList.size() > 0) {
			Cpt cptData = cptList.get(0);
			cpt = CPTDTO.getInstance(Long.parseLong(Optional.fromNullable(Strings.emptyToNull("" + cptData.getCptId())).or("-1")),
					Optional.fromNullable(Strings.emptyToNull("" + cptData.getCptCptcode())).or(""),
					Optional.fromNullable(Strings.emptyToNull("" + cptData.getCptDescription())).or(""),
					Optional.fromNullable(Strings.emptyToNull("" + cptData.getCptShortCutDesc())).or(""),
					Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + cptData.getCptCpttype())).or("0")),
					Float.parseFloat(Optional.fromNullable(Strings.emptyToNull("" + cptData.getCptCost())).or("0")),
					Float.parseFloat(Optional.fromNullable(Strings.emptyToNull("" + cptData.getCptMcallowed())).or("0")),
					Float.parseFloat(Optional.fromNullable(Strings.emptyToNull("" + cptData.getCptRvu())).or("0")),
					Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + cptData.getCptGroupid())).or("0")),
					Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + cptData.getCptOrderby())).or("0")),
					Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + cptData.getCptHitcount())).or("0")),
					Optional.fromNullable(Strings.emptyToNull("" + cptData.getCptShortCutDesc())).or("false").equals("t")?true:false);
			cptVector.add(cpt);
		}		
		return cptVector;
	}

	/**
	 * Method to get test details of a newly ordered lab
	 * @param testId
	 * @return
	 */
	@Override
	public LabDescription getNewTestDetails(Integer testId) {
		List<LabDescription> labDataList = labDescriptionRepository.findAll(Specifications.where(InvestigationSpecification.checkTestId(testId)));
		LabDescription labData = labDataList.get(0);
		return labData;
	}	

	/**
	 * Method to get list of labs to be reviewed
	 * @param chartId
	 * @return
	 */
	public List<LabEntries> findResultsLabs(Integer chartId) {
		logger.debug("in finding results to be reviewed");
		chartId =  Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + chartId)).or("-1"));
		List<LabEntries> labs = labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.labResults(chartId)));
		return labs;
	}

	/**
	 * Method to get pending orders list
	 * @param encounterId
	 * @param chartId
	 * @return
	 */	
	@Override
	public List<LabEntries> findPendingOrders(Integer encounterId, Integer chartId) {
		logger.debug("in pending orders");
		this.encounterId = encounterId;
		encounterId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + encounterId)).or("-1"));
		chartId =  Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + chartId)).or("-1"));
		List<LabEntries> labs = labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.pendingLabs(encounterId,chartId)));
		return labs;
	}

	/**
	 * Method to update status for a lab
	 * @param testDetailIds
	 * @param encounterId
	 * @param testStatus
	 */
	@Override
	public void performOrDeleteLab(String testDetailId, String encounterId, String testStatus) {
		if( testDetailId.contains(",") ) {
			String[] testId = testDetailId.split(",");
			String[] encId = encounterId.split(",");
			for (int i = 0; i < testId.length; i++) {
				if( !testId[i].equals("") ) {
					updateTestData(testId[i], encId[i], testStatus);
				}
			}
		} else {
			if( !testDetailId.equals("") ) {
				updateTestData(testDetailId , encounterId, testStatus);
			}
		}		
	}

	/**
	 * Method to update test data
	 * @param testDetailId
	 * @param encounterId
	 * @param testStatus
	 */
	private void updateTestData(String testDetailId, String encounterId, String testStatus) {
		List<LabEntries> labs = labEntriesRepository.findAll(InvestigationSpecification.findTest(encounterId,testDetailId));
		if( labs.size() > 0 ) {
			for (LabEntries lab : labs) {
				if ( testStatus.equalsIgnoreCase("6") ) {
					EmployeeProfile empProfile = labs.get(0).getEmpProfile();
					Integer loginId = getLoginUserId(empProfile.getEmpProfileEmpid());
					Date date = new Date();
					Timestamp perfOn = new Timestamp(date.getTime());
					lab.setLabEntriesPerfOn(perfOn);
					lab.setLabEntriesPerfBy(loginId);					
					lab.setLabEntriesRevOn(perfOn);
					lab.setLabEntriesRevBy(loginId);
				}
				lab.setLabEntriesTestStatus(Integer.parseInt(testStatus));
				labEntriesRepository.saveAndFlush(lab);
			}
		}
	}	

	private Integer getLoginUserId(Integer empid) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<EmployeeProfile> root = cq.from(EmployeeProfile.class);
		cq.select(root.get(EmployeeProfile_.empProfileLoginid));
		cq.where(builder.equal(root.get(EmployeeProfile_.empProfileEmpid),empid));
		Integer loginId =-1;
		try {
			loginId=(Integer) em.createQuery(cq).getSingleResult();
		} catch(NoResultException e) {
			loginId = -1;
		}
		return loginId;
	}

	/**
	 * Method to find VIS details based on testId and testDetailId
	 */
	public List<VisData> findVISData(Integer testId, Integer testDetailId) {
		logger.debug("in getting VIS details");
		String langCode = getLanguageCode(this.chartId);
		if( !langCode.equalsIgnoreCase("17")) {
			langCode = "6";
		}
		List<LabDescription> labDetails = labDescriptionRepository.findAll(InvestigationSpecification.labByTestId(testId));
		List<CvxVaccineGroupMapping> cvxVaccine = null;
		for (int i = 0; i < labDetails.size(); i++) {
			cvxVaccine = cvxRepository.findAll(InvestigationSpecification.cvxByLabs(labDetails.get(i).getLabDescriptionCvx()));
		}	
		List<VisInfo> visInfoList = new ArrayList<VisInfo>();
		for (int i = 0; i < cvxVaccine.size(); i++) {
			CvxVaccineGroupMapping vaccineCVX = cvxVaccine.get(i);
			List<Vis> visList = visRepository.findAll(InvestigationSpecification.checkVISLangCode(vaccineCVX.getCvxVaccineGroupMappingVaccineGroupCode(), langCode));
			Date date = new Date();			
			for (int j = 0; j < visList.size(); j++) {
				Vis vis = visList.get(j);
				VisFileMapping visFileMapping = vis.getVisFileMapping();
				for (int k = 0; k < cvxVaccine.size(); k++) {
					CvxVaccineGroupMapping cvx = cvxVaccine.get(k);
					if (cvx.getCvxVaccineGroupMappingVaccineGroupCode().equals(vis.getVisVaccineGroupCode())) {
						VisInfo visInfo = new VisInfo();
						visInfo.setCvxVaccineGroupMappingCvxCode(cvx.getCvxVaccineGroupMappingCvxCode());
						visInfo.setCvxVaccineGroupMappingUncertainFormulationCvx(cvx.getCvxVaccineGroupMappingUncertainFormulationCvx());
						visInfo.setCvxVaccineGroupMappingVaccineGroupCode(cvx.getCvxVaccineGroupMappingVaccineGroupCode());
						visInfo.setVisEntriesId(-1);
						visInfo.setVisId(vis.getVisId());
						visInfo.setVisName(vis.getVisName());
						visInfo.setVisPresentDate(new Timestamp(date.getTime()));
						visInfo.setVisPublicationDate(vis.getVisPublicationDate());
						visInfo.setVisFileMappingFileName(visFileMapping.getVisFileMappingFileName());
						visInfoList.add(visInfo);
						break;
					} 
				}
			}		
		}

		List<PatientVisEntries> patientVisList = patientVISRepository.findAll(InvestigationSpecification.visByTestDetailId(testDetailId));
		List<VisEntries> visEntriesList = new ArrayList<VisEntries>();

		for (int i = 0; i < patientVisList.size(); i++) {
			VisEntries visEntries = new VisEntries();
			PatientVisEntries patientVis = patientVisList.get(i);
			visEntries.setPatientVisEntriesCvx(patientVis.getPatientVisEntriesCvx());
			visEntries.setPatientVisEntriesId(patientVis.getPatientVisEntriesId());
			visEntries.setPatientVisEntriesPresentationDate(patientVis.getPatientVisEntriesPresentationDate());
			visEntries.setPatientVisEntriesVisId(patientVis.getPatientVisEntriesVisId());
			visEntriesList.add(visEntries);
		}

		List<VisData> visDataList = new ArrayList<VisData>();
		for (int i = 0; i < visInfoList.size(); i++) {
			VisInfo cvx = visInfoList.get(i);
			if( visEntriesList.size() > 0 ) {
				for (int j = 0; j < visEntriesList.size(); j++) {
					VisEntries visEntries = visEntriesList.get(j);
					if( visEntries.getPatientVisEntriesCvx().equalsIgnoreCase(cvx.getCvxVaccineGroupMappingUncertainFormulationCvx())
							&& visEntries.getPatientVisEntriesId().equals(cvx.getVisId())) {
						VisData visData = new VisData();
						visData.setCvxMapCode(cvx.getCvxVaccineGroupMappingUncertainFormulationCvx());
						visData.setVisName(cvx.getVisName());
						visData.setGroupName(cvx.getCvxVaccineGroupMappingVaccineGroupCode());
						visData.setGroupCode(cvx.getCvxVaccineGroupMappingCvxCode());
						visData.setVisPubDate(cvx.getVisPublicationDate());
						visData.setVisFileName(cvx.getVisFileMappingFileName());
						if( cvx.getVisEntriesId() != null ) {
							visData.setVisEntriesId(cvx.getVisEntriesId());
						} else {
							visData.setVisEntriesId(-1);
						}
						if( cvx.getVisPresentDate() != null ) {
							visData.setVisPrsntDate(cvx.getVisPresentDate());
						} else {
							visData.setVisPrsntDate(visEntries.getPatientVisEntriesPresentationDate());
						}
						if( cvx.getVisId() != null ) {
							visData.setVisPatientEntiresId(cvx.getVisId());
						} else {
							visData.setVisPatientEntiresId(visEntries.getPatientVisEntriesId());
						}
						visDataList.add(visData);
					}
				}
			} else {
				VisData visData = new VisData();
				visData.setCvxMapCode(cvx.getCvxVaccineGroupMappingUncertainFormulationCvx());
				visData.setVisName(cvx.getVisName());
				visData.setGroupName(cvx.getCvxVaccineGroupMappingVaccineGroupCode());
				visData.setGroupCode(cvx.getCvxVaccineGroupMappingCvxCode());
				visData.setVisPubDate(cvx.getVisPublicationDate());
				visData.setVisFileName(cvx.getVisFileMappingFileName());
				visData.setVisEntriesId(cvx.getVisEntriesId());
				visData.setVisPrsntDate(cvx.getVisPresentDate());
				visData.setVisPatientEntiresId(cvx.getVisId());
				visDataList.add(visData);
			}
		}

		return visDataList;
	}

	/**
	 * Method to get language code of patient based on
	 * @param chartId2
	 * @return
	 */
	private String getLanguageCode(int chartId2) {
		String langCode;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PatientRegistration> root = cq.from(PatientRegistration.class);
		Join<PatientRegistration, Chart> joinChart = root.join(PatientRegistration_.alertTable,JoinType.INNER);
		cq.select(root.get(PatientRegistration_.patientRegistrationPreferredLan));
		cq.where(builder.equal(joinChart.get(Chart_.chartPatientid), chartId2));
		langCode = "" + em.createQuery(cq).getFirstResult();
		return langCode;
	}

	/**
	 * Method to get lot number details
	 * @param vaccineId
	 * @param onLoad
	 */
	@Override
	public List<VaccineOrderDetails> findLotDetails(Integer vaccineId, String onLoad) {
		String isCHDP = "";
		List<VaccineOrderDetails> lotDetails;
		if( onLoad == "-2" ) {
			isCHDP = getCHDPFromLab(vaccineId, this.encounterId, this.testDetailLotId);
			lotDetails = vaccineRepository.findAll(InvestigationSpecification.getLotNoDetails(Integer.parseInt(isCHDP), vaccineId));
		} else {
			isCHDP = getCHDPFromVaccines(vaccineId);
			lotDetails = vaccineRepository.findAll(InvestigationSpecification.getLotNoDetails(Integer.parseInt(isCHDP), vaccineId));
		}
		logger.debug("in getting lot number details");
		return lotDetails;
	}

	/**
	 * Method to save vaccine consent
	 * @param consentData
	 */
	@Override
	public List<VaccinationConsentForm> saveVaccineConsent(ConsentDetails consentData) {
		logger.debug("in saveVaccineConsent");
		String[] testDetailsArray= consentData.getVaccineDetailId().toString().split(",");
		String filepath = saveDecodeImage(consentData.getImageContent(), consentData.getSharedFolderPath(), consentData.getEncounterId());
		Date date = new Date();
		for (int i = 0; i < testDetailsArray.length; i++) {			
			VaccinationConsentForm consentForm = new VaccinationConsentForm();
			consentForm.setVaccinationConsentFormImagePath(filepath);
			consentForm.setVaccinationConsentFormImgcontent(consentData.getImageContent());
			consentForm.setVaccinationConsentFormIsgiven(consentData.isConsentGiven());
			consentForm.setVaccinationConsentFormSignedUser("$$" + consentData.getSignedUser() + "$$");
			consentForm.setVaccinationConsentFormTestdetailId(Integer.parseInt(testDetailsArray[i]));
			consentForm.setVaccinationConsentFormSignedDate(new Timestamp(date.getTime()));
			consentForm.setVaccinationConsentFormCreatedBy(sessionMap.getUserID());
			consentForm.setVaccinationConsentFormCreatedDate(new Timestamp(date.getTime()));
			consentForm.setVaccinationConsentFormUserId(sessionMap.getUserID());
			vaccinationConsentFormRepository.saveAndFlush(consentForm);
		}
		List<VaccinationConsentForm> consentList = vaccinationConsentFormRepository.findAll(InvestigationSpecification.consentByChartId(consentData.getChartId()));
		return consentList;
	}

	/**
	 * Method to save the decoded image in shared folder
	 * @param imgBase64Data
	 * @param encounterId
	 * @return
	 */
	public static String saveDecodeImage(String imgBase64Data, String path, int encounterId) {
		try {
			String sharedFolderPath = path;
			File dirStructure = new File(sharedFolderPath+"/vaccineconsentform");
			java.util.Date date = new java.util.Date();
			SimpleDateFormat ft = new SimpleDateFormat ("yyMMddhhmm");
			if ( !dirStructure.exists() ) {
				dirStructure.setReadable(true,false);
				dirStructure.setWritable(true,false);
				dirStructure.setExecutable(true,false);
				dirStructure.mkdir();
			}
			Base64 decoder = new Base64();
			imgBase64Data =  imgBase64Data.replaceAll("@","/");
			String str = "";
			for(int i=0;i<imgBase64Data.length();i++) {
				if(imgBase64Data.charAt(i)==' ') {
					str = str + "+";
				} else {
					str = str + imgBase64Data.charAt(i);
				}
			}
			byte[] imgBytes = decoder.decode(str);
			FileOutputStream osf;
			osf = new FileOutputStream(new File(sharedFolderPath+"/vaccineconsentform/"+encounterId+"_"+ft.format(date)+"_consentform.png"));
			osf.write(imgBytes);
			osf.flush();
			osf.close();
			return sharedFolderPath+"/vaccineconsentform/"+encounterId+"_"+ft.format(date)+"_consentform.png";
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "";
	}

	/**
	 * Method to get the lab details based on
	 * @param encounterId
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<LabEntries> findTodaysOrders(Integer encounterId) throws Exception {
		logger.debug("in findTodaysOrders");
		this.encounterId = encounterId;
		encounterId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + encounterId)).or("-1"));
		//List<LabEntries> labs = labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.labByEncounterId(encounterId)).and(InvestigationSpecification.LabsByTestStatus()));
		List<LabEntries> labs = getTodayOrdersbasedonEncounterId(encounterId);
		return labs;
	}

	
	private List<LabEntries> getTodayOrdersbasedonEncounterId(
			Integer encounterId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		List<LabEntries>values=new ArrayList<LabEntries>();
		CriteriaQuery<LabEntries> cq=cb.createQuery(LabEntries.class);
		Root<LabEntries>root=cq.from(LabEntries.class);
		root.join(LabEntries_.empProfile,JoinType.LEFT);
		root.join(LabEntries_.encounter,JoinType.LEFT);
		root.join(LabEntries_.labGroups,JoinType.INNER);
		Selection[] selections=new Selection[]{
				root.get(LabEntries_.labEntriesTestdetailId),
				root.get(LabEntries_.labEntriesTestDesc),
				root.get(LabEntries_.labEntriesEncounterId),
				root.get(LabEntries_.labEntriesTestId),
				root.get(LabEntries_.labEntriesTestStatus),
				root.get(LabEntries_.labEntriesGroupid),
				root.get(LabEntries_.labEntriesIsBillable),
				root.get(LabEntries_.labEntriesCpt),
				
		};
		cq.select(cb.construct(LabEntries.class,selections));
		Predicate[] predications=new Predicate[]{
				cb.equal(root.get(LabEntries_.labEntriesEncounterId), encounterId),
				cb.notEqual(root.get(LabEntries_.labEntriesTestStatus),2),
				cb.notEqual(root.get(LabEntries_.labEntriesTestStatus),7),
				};
				cq.where (predications);
				try{
					values= em.createQuery(cq).getResultList();	

				}catch(Exception e){
					e.printStackTrace();
				}
				return values;
	}
	
	private Integer getEmpId(int encounterId2) {
		Integer employeeId;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<EmployeeProfile> root = cq.from(EmployeeProfile.class);
		Join<EmployeeProfile, Encounter> join = root.join(EmployeeProfile_.encounterServiceDr);
		cq.select(root.get(EmployeeProfile_.empProfileEmpid));
		cq.where(builder.equal(join.get(Encounter_.encounterId), encounterId2));
		try {
			employeeId = Integer.parseInt(MoreObjects.firstNonNull(em.createQuery(cq).getSingleResult(),-1).toString());
		} catch(NoResultException e) {
			employeeId=-1;
		}
		return employeeId;
	}

	/**
	 * Method to get the lab details based on
	 * @param testDetailId
	 * @param testId
	 * @return Orders
	 * @throws Exception 
	 */
	@Override
	public Orders findCompleteTestDetails(Integer testDetailId, Integer testId, String groupId)
			throws Exception {
		this.testDetailLotId = testDetailId;
		Orders orderDetails = new Orders();
		logger.debug("in getting testDetails");
		List<LabEntries> labs = labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.testDetailsByTestId(testDetailId)).and(InvestigationSpecification.checkTestStatus()));
		LabData labData = new LabData();
		for (int i = 0; i < labs.size(); i++) {
			LabEntries labEntry = labs.get(i);
			labData.setChartId("" + labEntry.getLabEntriesChartid());
			Encounter encounter = labEntry.getEncounter();
			if( labEntry.getEmpProfile() != null ) {
				EmployeeProfile empData = labEntry.getEmpProfile();
				labData.setEmpFullName(empData.getEmpProfileFullname());
				Chart chart = encounter.getChart();
				PatientRegistration patReg = chart.getPatientRegistrationTable();
				if( empData.getEmpProfileGroupid() == -10 ) {
					if( encounter.getEncounterId() == -1 ) {
						//int patReg=getPatientIdinChart(labEntry.getLabEntriesChartid());
					//	String patempId=getPatientRegistrationPrincipalDoctor(patReg);
						labData.setEmpId("" + patReg.getPatientRegistrationPrincipalDoctor());
					} else {
						Long esd=encounter.getEncounterServiceDoctor();
						if(esd==null){
							labData.setEmpId("" + patReg.getPatientRegistrationPrincipalDoctor());
						}else{
						labData.setEmpId("" + encounter.getEncounterServiceDoctor());
						}
					}
				} else {
					labData.setEmpId("" + empData.getEmpProfileEmpid());
				}
			} else {
				Chart chart = encounter.getChart();
				PatientRegistration patReg = chart.getPatientRegistrationTable();
				//int patReg=getPatientIdinChart(labEntry.getLabEntriesChartid());
				//String patempId=getPatientRegistrationPrincipalDoctor(patReg);
				if( encounter.getEncounterId() == -1 ) {
					/*Chart chart = encounter.getChart();
					PatientRegistration patReg = chart.getPatientRegistrationTable();*/
				/*	int patReg=getPatientIdinChart(labEntry.getLabEntriesChartid());
					String patempId=getPatientRegistrationPrincipalDoctor(patReg);*/
					labData.setEmpId("" + patReg.getPatientRegistrationPrincipalDoctor());
					labData.setEmpFullName(getEmployeeName(labData.getEmpId()));
				} else {
				Long esd=encounter.getEncounterServiceDoctor();
					if(esd==null){
						labData.setEmpId("" + patReg.getPatientRegistrationPrincipalDoctor());
					
					
					}else{
						labData.setEmpId("" + encounter.getEncounterServiceDoctor());
					}
					labData.setEmpFullName(getEmployeeName(labData.getEmpId()));
				}
			}
			
			labData.setEncounterId("" + labEntry.getLabEntriesEncounterId());
			this.chartId = labEntry.getLabEntriesChartid();
			labData.setPatientId("" + getPatientId());
			if( labEntry.getLabEntriesSepcimenId() != null && labEntry.getLabEntriesSepcimenId() != -1 ) {
				Specimen specimen = labEntry.getSpecimen();
				labData.setSpecCollVol(specimen.getSpecimenCollVol());
				labData.setSpecCollVolUnit(specimen.getSpecimenCollVolUnit());
				labData.setSpecimenSource(specimen.getSpecimenSource());
			} else {
				labData.setSpecCollVol("");
				labData.setSpecCollVolUnit("");
				labData.setSpecimenSource("");
			}
			labData.setTestAdminNotes("" + labEntry.getLabEntriesAdministrationNotes());
			labData.setTestBaseDose(labEntry.getLabEntriesBasedose());
			labData.setTestBodySiteCode(labEntry.getLabEntriesBodysiteCode());
			labData.setTestBodySiteDesc(labEntry.getLabEntriesBodysiteDesc());
			labData.setTestPrelimStatus("" + labEntry.getLabEntriesPrelimTestStatus());
			labData.setTestResultStatus("" + labEntry.getLabEntriesStatus());
			labData.setTestConfirmStatus("" + labEntry.getLabEntriesConfirmTestStatus());
			labData.setTestDetailId("" + labEntry.getLabEntriesTestdetailId());
			labData.setTestDosage(labEntry.getLabEntriesDosage());
			labData.setTestDoseLevel("" + labEntry.getLabEntriesDosageLevel());
			labData.setTestGroupId("" + labEntry.getLabEntriesGroupid());
			labData.setTestId("" + labEntry.getLabEntriesTestId());
			if( labEntry.getLabEntriesIsBillable() != null )
				labData.setTestIsBillable(labEntry.getLabEntriesIsBillable());
			else
				labData.setTestIsBillable(true);
			labData.setTestIsChdp("" + labEntry.getLabEntriesIschdplab());
			if( labEntry.getLabEntriesIsFasting() != null )
				labData.setTestIsFasting(labEntry.getLabEntriesIsFasting());
			else
				labData.setTestIsFasting(false);
			labData.setTestLoinc(labEntry.getLabEntriesLoinc());
			labData.setTestName(labEntry.getLabEntriesTestDesc());
			labData.setTestOrdBy("" + labEntry.getLabEntriesOrdBy());
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			labData.setTestOrdOn(formatter.format(labEntry.getLabEntriesOrdOn()));
			labData.setTestPerfBy("" + labEntry.getLabEntriesPerfBy());
			if( labEntry.getLabEntriesPerfOn() != null ) 
				labData.setTestPerfOn(formatter.format(labEntry.getLabEntriesPerfOn()));
			else {
				labData.setTestPerfOn("");
			}
			labData.setTestRefusalId("" + labEntry.getLabEntriesRefusalreason());
			labData.setTestRevBy("" + labEntry.getLabEntriesRevBy());
			if( labEntry.getLabEntriesRevOn() != null )
				labData.setTestRevOn(formatter.format(labEntry.getLabEntriesRevOn()));
			else 
				labData.setTestRevOn("");
			labData.setTestRoute(labEntry.getLabEntriesLabRoute());
			if( labEntry.getLabEntriesShortorderNotes() != null )
				labData.setTestShortOrderNotes(labEntry.getLabEntriesShortorderNotes());
			else
				labData.setTestShortOrderNotes("");
			labData.setTestSite(labEntry.getLabEntriesLabSite());
			labData.setTestSpecimenId("" + labEntry.getLabEntriesSepcimenId());
			labData.setTeststatus("" + labEntry.getLabEntriesTestStatus());
			labData.setTestStrength(labEntry.getLabEntriesStrength());
			labData.setTestStrengthUnit(labEntry.getLabEntriesStrengthUnit());
			labData.setTestModifiedBy("" + labEntry.getLabEntriesModifiedby());
			labData.setTestShortNotes(labEntry.getLabEntriesShortNotes());
			labData.setTestCpt(labEntry.getLabEntriesCpt());
			labData.setTestCptUnits(labEntry.getLabEntriesCptUnits());
			labData.setTestResultNotes(labEntry.getLabEntriesResultNotes());
			labData.setDx1Code(labEntry.getLabEntriesDx1code());
			labData.setDx1CodeSystem(labEntry.getLabEntriesDx1codeCodesystem());
			labData.setDx2Code(labEntry.getLabEntriesDx2code());
			labData.setDx2CodeSystem(labEntry.getLabEntriesDx2codeCodesystem());
			labData.setDx3Code(labEntry.getLabEntriesDx3code());
			labData.setDx3CodeSystem(labEntry.getLabEntriesDx3codeCodesystem());
			labData.setDx4Code(labEntry.getLabEntriesDx4code());
			labData.setDx4CodeSystem(labEntry.getLabEntriesDx4codeCodesystem());
			labData.setDx1CodeDesc(labEntry.getLabEntriesDx1codeCodedesc());
			labData.setDx2CodeDesc(labEntry.getLabEntriesDx2codeCodedesc());
			labData.setDx3CodeDesc(labEntry.getLabEntriesDx3codeCodedesc());
			labData.setDx4CodeDesc(labEntry.getLabEntriesDx4codeCodedesc());
			labData.setTestLotNo("" + labEntry.getLabEntriesLotNo());
		}		
		orderDetails.setLabEntries(labData);

		logger.debug("in getting status list");
		List<ChartStatus> statusList = h068Repository.findAll(InvestigationSpecification.getStatusList());
		orderDetails.setStatusList(statusList);

		logger.debug("in getting refusal reason list");
		List<ChartStatus> refusalReason = h068Repository.findAll(InvestigationSpecification.getRefusalReasonList());
		orderDetails.setRefusalReasonList(refusalReason);

		logger.debug("in getting source list");
		List<ChartStatus> sourceList = h068Repository.findAll(InvestigationSpecification.getSourceList());
		orderDetails.setSourceList(sourceList);

		logger.debug("in getting immunisation site info");
		List<ImmunisationSite> siteInfo = siteRepository.findAll(InvestigationSpecification.getSiteDetails());
		orderDetails.setSiteInfo(siteInfo);

		List<VaccinationConsentForm> consentDetails = consentRepository.findAll(InvestigationSpecification.checkForConsent(testDetailId));
		if ( consentDetails.size() > 0 ) {
			orderDetails.setIsConsentObtained(true);
		} else {
			orderDetails.setIsConsentObtained(false);
		}

		if( !orderDetails.getIsConsentObtained() ) {
			List<LabEntries> vaccineInfoList = findVaccineInfo(labs.get(0).getLabEntriesEncounterId());
			List<Vaccines> vaccineDataList = new ArrayList<Vaccines>();
			for (int i = 0; i < vaccineInfoList.size(); i++) {
				LabEntries vaccineInfo = vaccineInfoList.get(i);
				Vaccines vaccineData = new Vaccines();
				PatientRegistration patData = patientRegistrationRepository.findOne(InvestigationSpecification.getPatientData(vaccineInfo.getLabEntriesChartid()));
				//PatientRegistration patData = getPatientData(vaccineInfo.getLabEntriesChartid());
				//if(patData!=null){
				vaccineData.setAccountNo(patData.getPatientRegistrationAccountno());
				vaccineData.setChartId("" + vaccineInfo.getLabEntriesChartid());
				DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				vaccineData.setDob(formatter.format(patData.getPatientRegistrationDob()));
				vaccineData.setFirstName(patData.getPatientRegistrationFirstName());
				vaccineData.setLastName(patData.getPatientRegistrationLastName());
				vaccineData.setMidInitial(patData.getPatientRegistrationMidInitial());
				vaccineData.setPatientId("" + patData.getPatientRegistrationId());
				vaccineData.setPhoneNo(patData.getPatientRegistrationPhoneNo());
				vaccineData.setTestDetailId("" + vaccineInfo.getLabEntriesTestdetailId());
				vaccineData.setTestName(vaccineInfo.getLabEntriesTestDesc());
				vaccineDataList.add(vaccineData);
			}
			
			orderDetails.setVaccineConsentInfo(vaccineDataList);
		}

		if( groupId.equalsIgnoreCase("36")) {
			List<VisData> visData = findVISData(testId, testDetailId);
			orderDetails.setVisData(visData);
		}

		if( groupId.equalsIgnoreCase("36") || groupId.equalsIgnoreCase("5")) {
			this.encounterId = labs.get(0).getLabEntriesEncounterId();
			this.testDetailLotId = labs.get(0).getLabEntriesTestdetailId();
			List<VaccineOrderDetails> vaccineOrderDetails = findLotDetails(testId, "-2");
			orderDetails.setLotDetails(vaccineOrderDetails);
		}
		if( labData.getTestCpt() != null ) {
			if( labData.getTestCpt().equalsIgnoreCase("92551")) { /** Audiometry Screening*/
				List<PatientClinicalElements> leftValueList = patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinEleByGWID(Integer.parseInt(labData.getEncounterId()), Integer.parseInt(labData.getPatientId()), "0000200200100145000"));
				if( leftValueList.size() > 0 ) {
					orderDetails.setLeftEarValue(leftValueList.get(0).getPatientClinicalElementsValue());
					orderDetails.setAudiometryLeftList(clinicalElementsService.getClinicalElementOptions("0000200200100145000")); /** audio status left*/
				}
				List<PatientClinicalElements> rightValueList = patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinEleByGWID(Integer.parseInt(labData.getEncounterId()), Integer.parseInt(labData.getPatientId()), "0000200200100146000"));
				if( rightValueList.size() > 0 ) {
					orderDetails.setRightEarValue(rightValueList.get(0).getPatientClinicalElementsValue());
					orderDetails.setAudiometryRightList(clinicalElementsService.getClinicalElementOptions("0000200200100146000")); /** audio status right*/
				}
			} else if( labData.getTestCpt().equalsIgnoreCase("99173")) { /** Vision Screening*/
				List<PatientClinicalElements> leftEyeList = patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinEleByGWID(Integer.parseInt(labData.getEncounterId()), Integer.parseInt(labData.getPatientId()), "0000200200100147000"));
				if( leftEyeList.size() > 0 ) {
					orderDetails.setLeftEyeValue(leftEyeList.get(0).getPatientClinicalElementsValue());
					orderDetails.setVisionLeftList(clinicalElementsService.getClinicalElementOptions("0000200200100147000")); /** vital vision left*/
				}
				List<PatientClinicalElements> rightEyeList = patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinEleByGWID(Integer.parseInt(labData.getEncounterId()), Integer.parseInt(labData.getPatientId()), "0000200200100148000"));
				if( rightEyeList.size() > 0 ) {
					orderDetails.setRightEyeValue(rightEyeList.get(0).getPatientClinicalElementsValue());
					orderDetails.setVisionRightList(clinicalElementsService.getClinicalElementOptions("0000200200100148000")); /** vital vision right*/
				}
				List<PatientClinicalElements> glassList = patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinEleByGWID(Integer.parseInt(labData.getEncounterId()), Integer.parseInt(labData.getPatientId()), "0000200200100179000"));
				if( glassList.size() > 0 ) {
					orderDetails.setGlassValue(glassList.get(0).getPatientClinicalElementsValue());
					orderDetails.setGlassValueList(clinicalElementsService.getClinicalElementOptions("0000200200100179000")); /** vital glass value*/
				}
			}
			List<PatientClinicalElements> weightList = patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinEleByGWID(Integer.parseInt(labData.getEncounterId()), Integer.parseInt(labData.getPatientId()), "0000200200100024000"));
			if( weightList.size() > 0 ) {
				orderDetails.setPatientWeight(weightList.get(0).getPatientClinicalElementsValue());
			}
		}
		this.chartId = Integer.parseInt(labData.getChartId());

		List<LabEntriesParameter> labParameters = labEntriesParameterRepository.findAll(InvestigationSpecification.checkParameterAndTestDetailId(testDetailId));
		orderDetails.setLabParameters(labParameters);

		this.patientId = getPatientId();
		List<Object> docId = getFileScanIds(testDetailId, this.patientId, 2);
		ArrayList<String> docDetails = new ArrayList<String>();
		for (int l = 0; l < docId.size(); l++) {
			List<Object> docIdList = getFileName((Integer)docId.get(l)); 
			for (int k = 0; k < docIdList.size(); k++) {
				docDetails.add("" + docIdList.get(k));
			}
		}				
		ArrayList<String> imageDetails = new ArrayList<String>();
		List<Object> scanId = getFileScanIds(testDetailId, this.patientId, 1);
		for (int l = 0; l < scanId.size(); l++) {
			List<Object> scanIdList = getFileName((Integer)scanId.get(l)); 
			for (int k = 0; k < scanIdList.size(); k++) {
				imageDetails.add("" + scanIdList.get(k));
			}
		}
		orderDetails.setDocDetails(docDetails);
		orderDetails.setImageDetails(imageDetails);
		return orderDetails;
	}

	private String getPatientRegistrationPrincipalDoctor(int patReg) {
	
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PatientRegistration> root = cq.from(PatientRegistration.class);
		cq.select(root.get(PatientRegistration_.patientRegistrationPrincipalDoctor));
		cq.where(builder.equal(root.get(PatientRegistration_.patientRegistrationId), patReg));		
		return "" + em.createQuery(cq).getSingleResult();
	}

	private PatientRegistration getPatientData(Integer labEntriesChartid) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		PatientRegistration values=null;
		CriteriaQuery<PatientRegistration> cq=cb.createQuery(PatientRegistration.class);
		Root<PatientRegistration>root=cq.from(PatientRegistration.class);
		Join<PatientRegistration, Chart>chartJoin= root.join(PatientRegistration_.alertTable,JoinType.INNER);
		Selection[] selections=new Selection[]{
				root.get(PatientRegistration_.patientRegistrationAccountno),
				root.get(PatientRegistration_.patientRegistrationFirstName),
				root.get(PatientRegistration_.patientRegistrationLastName),
				root.get(PatientRegistration_.patientRegistrationMidInitial),
				root.get(PatientRegistration_.patientRegistrationId),
				//root.get(PatientRegistration_.patientRegistrationDob),
				root.get(PatientRegistration_.patientRegistrationPhoneNo),
		};
		cq.multiselect(selections);
		Predicate[] predications=new Predicate[]{
				cb.equal(chartJoin.get(Chart_.chartId),labEntriesChartid),
				};
				cq.where (predications);
				try{
					values= em.createQuery(cq).getSingleResult();	

				}catch(Exception e){
					e.printStackTrace();
				}
				return values;
	}

	private String getEmployeeName(String employeeId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<EmployeeProfile> root = cq.from(EmployeeProfile.class);
		cq.select(root.get(EmployeeProfile_.empProfileFullname));
		cq.where(builder.equal(root.get(EmployeeProfile_.empProfileEmpid), employeeId));		
		return "" + em.createQuery(cq).getSingleResult();
	}

	/**
	 * Method to get CHDP criteria state
	 * @param labId
	 * @param labEncounterId
	 * @return
	 */
	private String getCHDPFromLab(Integer labId, int labEncounterId, int testDetailId) {
		String isCHDP;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LabEntries> root = cq.from(LabEntries.class);
		cq.select(root.get(LabEntries_.labEntriesIschdplab));
		cq.where(builder.and(builder.equal(root.get(LabEntries_.labEntriesTestId), labId), 
				builder.equal(root.get(LabEntries_.labEntriesTestdetailId), testDetailId),
				builder.equal(root.get(LabEntries_.labEntriesEncounterId), encounterId)));
		cq.orderBy(builder.desc((root.get(LabEntries_.labEntriesIschdplab))));
		isCHDP = "" + em.createQuery(cq).getSingleResult();
		return isCHDP;
	}

	/**
	 * Method to get CHDP criteria state from vaccineOrderDetails
	 * @param labId
	 * @return
	 */
	private String getCHDPFromVaccines(Integer labId) {
		String isCHDP;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<VaccineOrderDetails> root = cq.from(VaccineOrderDetails.class);
		cq.select(root.get(VaccineOrderDetails_.vaccineOrderDetailsIschdp));
		cq.where(builder.equal(root.get(VaccineOrderDetails_.vaccineOrderDetailsVaccineId), labId));
		cq.orderBy(builder.desc((root.get(VaccineOrderDetails_.vaccineOrderDetailsIschdp))));
		isCHDP = "" + em.createQuery(cq).getFirstResult();
		return isCHDP;
	}

	/**
	 * Method to get the body site data
	 * @param searchStr
	 * @param limit
	 * @param offset
	 * @return
	 */
	@Override
	public Page<BodySite> findBodySiteData(String searchStr, Integer limit, Integer offset) throws Exception {
		logger.debug("in getting body site data");
		Page<BodySite> siteDetails = bodySiteRepository.findAll(Specifications.where(InvestigationSpecification.searchBasedOnCode(searchStr + "%")),new PageRequest(offset, limit));
		if ( siteDetails.getContent().size() < 1 ) {
			siteDetails = bodySiteRepository.findAll(Specifications.where(InvestigationSpecification.searchBasedOnDesc(searchStr + "%")),new PageRequest(offset, limit));
		}
		return siteDetails;
	}

	/**
	 * Method to find frequent orders list
	 */
	@Override
	public List<FrequentOrders> findFrequentOrders(Integer encounterId) throws Exception {
		logger.debug("in getting frequent orders list");
		List<FrequentOrders> freqList = new ArrayList<FrequentOrders>();
		List<LabGroups> labGroups = labGroupsRepository.findAll();
		for (int i = 0; i < labGroups.size(); i++) {
			FrequentOrders freqOrders = new FrequentOrders();
			LabGroups groups = labGroups.get(i);
			this.empId = getEmpId(encounterId);
			Integer loginId = getLoginUserId(this.empId);
			//List<LabDescription> frequentOrder = labDescriptionRepository.findAll(Specifications.where(InvestigationSpecification.getFrequentLabs(groups.getLabGroupsId(),loginId)));
			List<LabDescription> frequentOrder = getFrequentLabs(groups.getLabGroupsId(),loginId);
			if( frequentOrder.size() > 0 ) {
				freqOrders.setLabs(frequentOrder);
				freqOrders.setGroupId(groups.getLabGroupsId());
				freqOrders.setGroupName(groups.getLabGroupsDesc());
				freqList.add(freqOrders);
			}
		}
		return freqList;
	}

	private List<LabDescription> getFrequentLabs(Integer labGroupsId,
			Integer loginId2) {
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<LabDescription> cq = cb.createQuery(LabDescription.class);
		Root<LabDescription> root = cq.from(LabDescription.class);
		Join<LabDescription, LabFreqorder> join = root.join(LabDescription_.labFreqOrder, JoinType.INNER);
		Join<LabDescription, Hl7ExternalTestmapping> rootjoin = root.join(LabDescription_.hl7ExternalTestmappingTable, JoinType.LEFT);
		rootjoin.join(Hl7ExternalTestmapping_.hl7ExternalTestTable, JoinType.LEFT);
		@SuppressWarnings("rawtypes")
		Selection[] selections=new Selection[]{
		/*	root.get(LabDescription_.labDescriptionTestid),
			root.get(LabDescription_.labDescriptionGroupid),
			root.get(LabDescription_.labDescriptionParameters),
			root.get(LabDescription_.labDescriptionDrugs),
			root.get(LabDescription_.labDescriptionTestDesc),
			root.get(LabDescription_.labDescriptionScanGroupid),
			root.get(LabDescription_.labDescriptionCvx),
			root.get(LabDescription_.labDescriptionLoinc),
			*/
			root.get(LabDescription_.labDescriptionTestid),
			root.get(LabDescription_.labDescriptionTestDesc),
		};
		cq.select(cb.construct(LabDescription.class,selections));
		Predicate[] restrictions = new Predicate[] {
				cb.equal(root.get(LabDescription_.labDescriptionGroupid), labGroupsId),
				cb.equal(root.get(LabDescription_.labDescriptionIsactive), true),
				join.get(LabFreqorder_.labFreqorderUserid).in(-1, loginId2),
		};
		cq.distinct(true);
		cq.where(restrictions);
		cq.orderBy(cb.asc(root.get(LabDescription_.labDescriptionTestDesc)));
		List<LabDescription> rstList=new ArrayList<LabDescription>();
		try{
			rstList=em.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return rstList;
	}

	/**
	 * Method to get vaccine details which has to take consent
	 * @return the entity PatientRegistration
	 * @throws Exception
	 */
	public List<LabEntries> findVaccineInfo(Integer encounterId) throws Exception {
		logger.debug("in getting vaccine details");
		encounterId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + encounterId)).or("-1"));
		List<LabEntries> vaccines = labEntriesRepository.findAll(InvestigationSpecification.vaccineByEncounter(encounterId));
		return vaccines;
	}

	@Override
	public void  savelab(String requesttosave,Integer encounterIdParam,Integer patientIdParam,Integer chartIdParam,
			Integer userIdParam,String fullDataParam,String isforwardParam,String forwardto,
			String ishighpriorityParam,String testidParam) throws Exception{
		Integer emploginId=-1;
		Integer logindefId=-1;
		emploginId=getEmpLoginId(userIdParam);
		if(emploginId!=-1)
		//EmployeeProfile empProfile=empProfileRepository.findOne(EmployeeSpecification.getUserDetailsByUserId(userIdParam));
		//LoginUsers login=loginUsersRepository.findOne(LoginSpecfication.byUserId(emploginId));
		logindefId=getUserIdfordefDoctor(emploginId);
		encounterId = encounterIdParam;
		patientId   = patientIdParam;
		chartId     = chartIdParam;
		userId      = userIdParam;
		loginid		=""+userIdParam;
		toDay = new String();
		Calendar cal = new GregorianCalendar();
		toDay = (cal.get(Calendar.MONTH)+ 1) + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR);
		fullData = URLDecoder.decode(fullDataParam,"UTF-8");
	/*	try {
            fullDataParam = fullDataParam.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            fullDataParam = fullDataParam.replaceAll("\\+", "%2B");
            fullData = URLDecoder.decode(fullDataParam,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
	}*/
		defaultDoctor = Optional.fromNullable(logindefId).or(-1);
		isforward=isforwardParam;
		toId=Integer.parseInt(Optional.fromNullable(forwardto).or("-1"));
		ishighpriority = Optional.fromNullable(ishighpriorityParam).or("false");
		testId = Optional.fromNullable(testidParam).or("");
		if(requesttosave.equals("2")) {
			logger.debug("Enters into save parameter section");
			SaveParameters (chartId, -1,fullData);
		} else {
			logger.debug("Enters into save order section");
			saveInvestigation();
		}
	}

	private Integer getUserIdfordefDoctor(Integer emploginId) {
		Integer logindefdoctor=-1;
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Object> cq=cb.createQuery();
		Root<LoginUsers> root=cq.from(LoginUsers.class);
		cq.select(root.get(LoginUsers_.loginUsersDefaultDoctor));
		cq.where(cb.equal(root.get(LoginUsers_.loginUsersId), emploginId));
	
		try{
			logindefdoctor=(Integer) em.createQuery(cq).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			logindefdoctor=-1;
		}
	      return logindefdoctor;
		}

	private Integer getEmpLoginId(Integer userIdParam) {
		Integer loginId=-1;
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Object> cq=cb.createQuery();
		Root<EmployeeProfile> root=cq.from(EmployeeProfile.class);
		cq.select(root.get(EmployeeProfile_.empProfileLoginid));
		cq.where(cb.equal(root.get(EmployeeProfile_.empProfileEmpid), userIdParam)).getRestriction();
	
		try{
			loginId=(Integer) em.createQuery(cq).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			loginId=-1;
		}
	      return loginId;
		}
	

	public void saveInvestigation() throws Exception{ 
		setResourceBundle();
		String[] labRecord = fullData.split("!@@!"); 
		String[] testdetidarray=new String[labRecord.length];
		int len=0;
		List<Encounter> encounterList=new ArrayList<Encounter>();
		encounterList=getEncounterList(encounterId);
				
		//List<Encounter> encounterList=encounterEntityRepository.findAll(EncounterEntitySpecification.EncounterById(encounterId));
		String dateString=HUtil.currentDate();
		Integer posInteger=1;
		if(encounterList.size()>0){
			dateString=MoreObjects.firstNonNull(encounterList.get(0).getEncounterDate(), HUtil.currentDate()).toString();
			DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
			Date date = (Date) parser.parse(dateString);
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			dateString=formatter.format(date);
			posInteger=Optional.fromNullable(encounterList.get(0).getEncounterPos()).or(1);
		}
		edate  = dateString; 
		pos    = posInteger;
		for (int i=0; i < labRecord.length && !fullData.equals(""); i++){
			String tempRecord = labRecord[i];
			String[] labFieldValuePair = tempRecord.split("#@@#");
			String[] dataArray = new String[dataValueMap("MAXVARIABLECOUNT")];
			for (int j=0 ; j < (labFieldValuePair.length); j++ )
			{
				int k = 0;		    	
				String[] data = labFieldValuePair[j].split("~@@~");
				for(int m=0;m<data.length;m++)
					k = data.length;
				String fieldName = data[0];			
				String fieldValue ="";
				if ( k > 1)
					fieldValue = Optional.fromNullable(data[1]).or("");
				if (fieldName.substring(0,4).equals("lab_"))
					dataArray[dataValueMap("int_" + fieldName)] = fieldValue;
			}	

			String ids = moveDataToDataBase(dataArray);
			if ( ids != null){
				savedIds = savedIds + dataArray[dataValueMap("int_lab_uniquekey")] + "~^" + ids  + "!!!";
				testdetidarray[len] = ids.split("##")[0];
				len++;
			}
		}   
		for(int k=0;k<testdetidarray.length;k++){
			int hl7fileid=getUnmappedResult(Integer.parseInt(Optional.fromNullable(testdetidarray[k]).or("-1")));
			if(hl7fileid!=-1)
			{
				List<Hl7Unmappedresults> detail=getUnmappedResultTestDetailId(hl7fileid);
				String[] temptestidarray=new String[detail.size()];
				for(int l=0;l<detail.size();l++)
				{
					Hl7Unmappedresults temp=detail.get(l);
					temptestidarray[l]=temp.getHl7UnmappedresultsTestdetailId().toString();
				}	
				boolean isreviewed=true;
				for(int m=0;m<temptestidarray.length;m++)
				{
					Integer labEntriesList=getLabEntriesTestStatusfortemp(Integer.parseInt(Optional.fromNullable(temptestidarray[m]).or("-1").toString()));
					//LabEntries labEntriesList=labEntriesRepository.findOne(InvestigationSpecification.testdetailIds(Integer.parseInt(Optional.fromNullable(temptestidarray[m]).or("-1").toString())));
					if(labEntriesList!=null){
						int teststatus=Optional.fromNullable(labEntriesList).or(-1);
						if(teststatus<=3)
							isreviewed=false;
					}
				}
				if(isreviewed)
				{
					List<Hl7ResultInbox> hl7ResultInboxList=hl7ResultInboxRepository.findAll(InvestigationSpecification.hl7ResultsId(hl7fileid));
					for(int g=0;g<hl7ResultInboxList.size();g++){
						hl7ResultInboxList.get(g).setHl7ResultInboxReviewed(1);
						hl7ResultInboxRepository.saveAndFlush(hl7ResultInboxList.get(g));
					}
				}
			}
		}
	}    

	private Integer getLabEntriesTestStatusfortemp(int parseInt) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LabEntries> root=cq.from(LabEntries.class);
		cq.select(root.get(LabEntries_.labEntriesTestStatus));
		cq.where(builder.equal(root.get(LabEntries_.labEntriesTestdetailId), parseInt));
		Integer unmappedResult=-1;
		List<Object> list=em.createQuery(cq).getResultList();
		if(list.size()>0)
			unmappedResult= Integer.parseInt(MoreObjects.firstNonNull(list.get(0),"-1").toString());
		return unmappedResult;
	}

	private List<Encounter> getEncounterList(int encounterId2) {
		List<Encounter> encounterResults=new ArrayList<Encounter>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Encounter> cq = builder.createQuery(Encounter.class);
		Root<Encounter> root=cq.from(Encounter.class);
		Selection[] selections=new Selection[]{
				root.get(Encounter_.encounter_service_doctor),
				root.get(Encounter_.encounterDate),
				root.get(Encounter_.encounterPos),
				
			};
			cq.select(builder.construct(Encounter.class,selections));
			Predicate[] restrictions = new Predicate[] {
					builder.equal(root.get(Encounter_.encounterId), encounterId2),
					
			};
			cq.where(restrictions);
		
		 encounterResults= em.createQuery(cq).getResultList();
		
		return encounterResults;
	}

	@SuppressWarnings("unused")
	public String moveDataToDataBase(String[] dataStr) throws Exception{
		logger.debug("Enters into move to database function.");
		int labEncounterId ; 
		String dxStr="";
		String SaveDxIds = "";
		String Units= "1";
		String saveLabIsNewLotUnit = "";
		int testEncounterId;
		rDoctorId = Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_referredto")]).or("-1").trim());
		String labencounterdate  = "";
		String labPerformedDate = "";
		String dx1code = "";
		String dx2code = "";
		String dx3code = "";
		String dx4code = "";
		String dx5code = "";
		String dx6code = "";
		String dx7code = "";
		String dx8code = "";
		String  saveid ="";
		int servicedoctor;
		int lab_status = -1;
		String toDay = new String();
		Calendar cal = new GregorianCalendar();
		toDay = (cal.get(Calendar.MONTH)+ 1) + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR);
		lab_status = Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_teststatus")]).or("-1"));//HTil************
		String[] CPTArray = Optional.fromNullable(dataStr[dataValueMap("int_lab_cpt")]).or("").split(",");
		String[] CPTUnits = Optional.fromNullable(dataStr[dataValueMap("int_lab_qnty")]).or("").split("\\^");
		labPerformedDate = Optional.fromNullable(dataStr[dataValueMap("int_lab_performon")]).or(toDay).split(" ")[0];
		if(dataStr[dataValueMap("int_lab_testdetailid")].equals("-1")){
			logger.debug("Save a new order.");
			labEncounterId=encounterId;
			labencounterdate=edate;
		} else {
			logger.debug("Save an existing order.");
			Integer labentriesEncounterIdforLab=getLabEntriesEncounterforExist(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_testdetailid")]).or("-1").toString()));
			//List<LabEntries> labEntriesList=labEntriesRepository.findAll(InvestigationSpecification.testdetailIds(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_testdetailid")]).or("-1").toString())));
			int encount=Integer.parseInt(MoreObjects.firstNonNull(labentriesEncounterIdforLab,-1).toString());
			labEncounterId=encount;
			
			List<Encounter> encounterList=new ArrayList<Encounter>();
			encounterList=getEncounterList(encounterId);
			//List<Encounter> encounterList=encounterEntityRepository.findAll(EncounterEntitySpecification.EncounterById(labEncounterId));
			String dateString=HUtil.currentDate();
			if(encounterList.size()>0){
				dateString=MoreObjects.firstNonNull(encounterList.get(0).getEncounterDate(), HUtil.currentDate()).toString();
				DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
				Date date = (Date) parser.parse(dateString);
				DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				dateString=formatter.format(date);
			}
			labencounterdate=dateString;
		}
		
		List<Encounter> encounterListOuter=new ArrayList<Encounter>();
		encounterListOuter=getEncounterList(encounterId);
		//List<Encounter> encounterListOuter=encounterEntityRepository.findAll(EncounterEntitySpecification.EncounterById(labEncounterId));
		servicedoctor=defaultDoctor;
		if(encounterListOuter.size()>0)
			servicedoctor = Integer.parseInt(MoreObjects.firstNonNull(encounterListOuter.get(0).getEncounterServiceDoctor(),defaultDoctor).toString());
		dx1code = MoreObjects.firstNonNull(dataStr[dataValueMap("int_lab_dx1code")],"").trim();
		if(!dx1code.equals("") && !dx1code.equals(null)) {
			dx1code = dx1code.trim();
		} else {
			dx1code = "NODX";
			dataStr[dataValueMap("int_lab_dx1code_codesystem")] = "2.16.840.1.113883.6.90";//ICD 10
		}
		dx2code = MoreObjects.firstNonNull(dataStr[dataValueMap("int_lab_dx2code")],"").trim();
		if(!dx2code.equals("") && !dx2code.equals(null)){
			dx2code =  dx2code.trim();
		} else {
			dataStr[dataValueMap("int_lab_dx2code_codesystem")]="";
		}
		dx3code = MoreObjects.firstNonNull(dataStr[dataValueMap("int_lab_dx3code")],"").trim();
		if(!dx3code.equals("") && !dx3code.equals(null)){
			dx3code = dx3code.trim();
		}else{
			dataStr[dataValueMap("int_lab_dx3code_codesystem")]="";
		}
		dx4code = MoreObjects.firstNonNull(dataStr[dataValueMap("int_lab_dx4code")],"").trim();
		if(!dx4code.equals("") && !dx4code.equals(null)){
			dx4code = dx4code.trim();
		}else{
			dataStr[dataValueMap("int_lab_dx4code_codesystem")]="";
		}
		dx5code = MoreObjects.firstNonNull(dataStr[dataValueMap("int_lab_dx5code")],"").trim();
		if(!dx5code.equals("") && !dx5code.equals(null)){
			dx5code = dx5code.trim();
		}else{
			dataStr[dataValueMap("int_lab_dx5code_codesystem")]="";
		}
		dx6code = MoreObjects.firstNonNull(dataStr[dataValueMap("int_lab_dx6code")],"").trim();
		if(!dx6code.equals("") && !dx6code.equals(null)){
			dx6code = dx6code.trim();
		}else{
			dataStr[dataValueMap("int_lab_dx6code_codesystem")]="";
		}
		dx7code =MoreObjects.firstNonNull(dataStr[dataValueMap("int_lab_dx7code")],"").trim();
		if(!dx7code.equals("") && !dx7code.equals(null)){
			dx7code = dx7code.trim();
		}else{
			dataStr[dataValueMap("int_lab_dx7code_codesystem")]="";
		}
		dx8code = MoreObjects.firstNonNull(dataStr[dataValueMap("int_lab_dx8code")],"").trim();
		if(!dx8code.equals("") && !dx8code.equals(null)){
			dx8code = dx8code.trim();
		}else{
			dataStr[dataValueMap("int_lab_dx8code_codesystem")]="";
		}
		//Service entry
		if(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_teststatus")]).or("-1").trim())>=3 
				&& Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_teststatus")]).or("-1").trim()) <7 
				&& !Optional.fromNullable(dataStr[dataValueMap("int_lab_cpt")]).or("").equals("") 
				&& (Optional.fromNullable(dataStr[dataValueMap("int_lab_isbillable")]).or("true").equals("true")
						|| Optional.fromNullable(dataStr[dataValueMap("int_lab_isbillable")]).or("t").equals("t"))
						&& !Optional.fromNullable(dataStr[dataValueMap("int_lab_qnty")]).or("").equals("") 
						&& Optional.fromNullable(dataStr[dataValueMap("int_lab_isbilled")]).or("0").equals("0") ){/*
			CPTDAO cptDAO = new CPTDAO();
			CPTDTO cptDTO;
			ServiceDetailDAO serviceDAO = new ServiceDetailDAO(dbUtils);
			boolean insert = true;
			for(int i = 0; i< CPTArray.length ; i++){
				insert = true;
				//Calculate units 
				Units="1";
				for(int j=0;j<CPTUnits.length;j++){
					if(CPTArray[i].split("-").length > 1){
						if(CPTArray[i].split("-")[1].equalsIgnoreCase("SL")){
							if(CPTUnits[j].split("~")[0].equals(CPTArray[i].split("-")[0]+"-SL")){
								if(CPTUnits[j].split("~").length >1){
									if(!CPTUnits[j].split("~")[1].trim().equals(""))
										Units=CPTUnits[j].split("~")[1].trim();
								}
							}
						}else{
							if(CPTUnits[j].split("~")[0].equals(CPTArray[i].split("-")[0])){
								if(CPTUnits[j].split("~").length >1){
									if(!CPTUnits[j].split("~")[1].trim().equals(""))
										Units=CPTUnits[j].split("~")[1].trim();
								}
							}
						}
					}else{
						if(CPTUnits[j].split("~")[0].equals(CPTArray[i].split("-")[0])){
							if(CPTUnits[j].split("~").length >1){
								if(!CPTUnits[j].split("~")[1].trim().equals(""))
									Units=CPTUnits[j].split("~")[1].trim();
							}
						}
					}
				}
				if((dataStr[dataValueMap("int_lab_dosage")] != null) && (Integer.parseInt(Units) <= 1)){	//For calculating units from dosage for injections and vaccinations
					Units= ""+calculateUnit(HUtil.Nz(dataStr[dataValueMap("int_lab_dosage")],""), CPTArray[i],dbUtils);
				}
				if(Integer.parseInt(Units) <= 0){			//Finally when unit value is lessthan or equal to 0, then unit is set to default value 1. 
					Units="1";
				}
				String ndccode=HUtil.Nz(dataStr[dataValueMap("int_lab_ndc")],"");
				String loinccode=HUtil.Nz(dataStr[dataValueMap("int_lab_loinc")],"");
				//Visit type and Plan type in superbill
				int visittype = 2;
				int plantype  = -1;
				String cptQuery = "select groupid,plantype from patient_encounter_type inner join encounter on encounter_visittype=categoryid and encounter_id ="+encounterId;
				ResultSet rst = dbUtils.executeQuery(cptQuery);
				if (rst.next()){
					visittype = Integer.parseInt(HUtil.Nz(rst.getString("groupid"),"2"));
					plantype  = Integer.parseInt(HUtil.Nz(rst.getString("plantype"),"-1"));
				}

				Vector cptVect = new Vector();
				if(CPTArray[i].split("-").length > 1){
					if(CPTArray[i].split("-")[1].equalsIgnoreCase("SL")){
						cptVect = cptDAO.getCpts(CPTArray[i].split("-")[0]+"-"+CPTArray[i].split("-")[1],true,plantype);
					}else{
						cptVect = cptDAO.getCpts(CPTArray[i].split("-")[0],true,plantype);
					}
				}else{
					cptVect = cptDAO.getCpts(CPTArray[i],true,plantype);
				}
				if(cptVect.size() > 0 ){ 
					cptDTO =((CPTDTO)(cptVect.get(0)));
					cptDTO.getCost();
					//createEMRServiceDetail(long patientId,String dos_from,String date_of_posting,String scptcode,String modifier1,String comments,String modifier2,int servicedoctor,int billeddoctor,int refdoctor,int pos,int units,double charges,int howpaid,double copay,double payments,int pri_insdetailId,int sec_insdetailId,String dx1,String dx2,String dx3,String dx4,int placedby,String placedon,int modifiedby,String modifiedon,int encounterId,int serviceid,boolean isupdate)

					CompletePatientDetailModel patientModel = CompletePatientDetailModel.getNewInstance(patientId,session.getAttribute("connectString").toString());
					BillingServiceDetailModel billingServiceModel = new BillingServiceDetailModel();
					billingServiceModel.setPatientId(patientId);
					billingServiceModel.setDosFrom(labPerformedDate);
					billingServiceModel.setDop(labPerformedDate);	                
					billingServiceModel.setCptCode(cptDTO.getCPTCode());
					if(CPTArray[i].split("-").length>1){
						if(CPTArray[i].split("-").length>3){
							billingServiceModel.setMod1(CPTArray[i].split("-")[2]);
							billingServiceModel.setMod2(CPTArray[i].split("-")[3]);
						}
						else if(CPTArray[i].split("-").length>2){
							if(!(CPTArray[i].split("-")[1].equalsIgnoreCase("SL"))){
								billingServiceModel.setMod1(CPTArray[i].split("-")[1]);
								billingServiceModel.setMod2(CPTArray[i].split("-")[2]);
							}else{
								billingServiceModel.setMod1(CPTArray[i].split("-")[2]);
							}
						}
						else if(!CPTArray[i].split("-")[1].equalsIgnoreCase("SL"))
							billingServiceModel.setMod1(CPTArray[i].split("-")[1]);		
					}
					billingServiceModel.setSdoctorId(servicedoctor);
					billingServiceModel.setBdoctorId(servicedoctor);
					billingServiceModel.setReferralId(rDoctorId);
					billingServiceModel.setPosId(pos);
					billingServiceModel.setDx1(dx1code);
					billingServiceModel.setDx2(dx2code);
					billingServiceModel.setDx3(dx3code);
					billingServiceModel.setDx4(dx4code);
					billingServiceModel.setDx5(dx5code);
					billingServiceModel.setDx6(dx6code);
					billingServiceModel.setDx7(dx7code);
					billingServiceModel.setDx8(dx8code);
					String dxSystem="";
					billingServiceModel.setDxCodeSystemId(dxSystem);
					billingServiceModel.setServiceId(-1);
					billingServiceModel.setVisitType(visittype);
					billingServiceModel.setCostPlanId(plantype);
					if(!ndccode.trim().equalsIgnoreCase(""))
						billingServiceModel.setRlu_claim(ndccode);
					else
						billingServiceModel.setRlu_claim(HUtil.Nz(dbUtils.newTableLookUp("coalesce(cpt_ndc,'')","cpt","cpt_cptcode ilike '"+cptDTO.getCPTCode()+"'"),""));
					String insuranceName=HUtil.Nz(patientModel.getPrimaryInsuranceName(),"");
					billingServiceModel.setPriInsId(patientModel.getPatientPrimaryInsId());
					int billingReason = Integer.parseInt(HUtil.Nz(dbUtils.newTableLookUp("initial_settings_option_value", "initial_settings", "initial_settings_option_name = 'Default Billing Reason'"),"-1"));
					if(insuranceName.equalsIgnoreCase("Self Pay") || insuranceName.equalsIgnoreCase("SelfPay") || insuranceName.equalsIgnoreCase("Self-Pay")  ){
						serviceDAO.updatePrimaryInsToSelfPay(billingServiceModel,dbUtils);	
						if(billingServiceModel.getPriInsId() < 0)
							throw new Exception("Unable to generate Self Pay Insurance ID .....");
						billingServiceModel.setBillingReason(billingReason);
					}
					else{
						billingServiceModel.setSubmitStatus("0");
						if(visittype == 9){
							billingServiceModel.setBillingReason(billingReason);
						}
					}
					billingServiceModel.setSecInsId(Integer.parseInt(HUtil.nz(patientModel.getPatientSecondaryInsId(),"-1").toString()));
					billingServiceModel.setCharges(cptDTO.getCost()*Integer.parseInt(Units));
					billingServiceModel.setScomments(cptDTO.getDescription());
					billingServiceModel.setUnits((Float.parseFloat(Units)));
					billingServiceModel.setScreenName("From Investigation");
					serviceDAO.createOrUpdateServiceDetail(billingServiceModel,session.getAttribute("loginName").toString());
					String intMessage = "Service added from investigation for lab "+HUtil.Nz(dataStr[dataValueMap("int_lab_labname")],"")+" with cpt "+CPTArray[i];
					EventLog.LogEvent(AuditTrail.GLACE_LOG,AuditTrail.Investigations,AuditTrail.UPDATE,Integer.parseInt(session.getAttribute("parent_event").toString()),AuditTrail.SUCCESS,intMessage,Integer.parseInt(session.getAttribute("loginId").toString()),"127.0.01",request_Audit.getRemoteAddr(),patientId,chartId,encounterId,AuditTrail.USER_LOGIN,request_Audit,dbUtils,"Service added from investigation.");
				}
			}
						 */}

		LabEntries labEntriesSave=new LabEntries();
		Specimen specimenSave=new Specimen();
		if(dataStr[dataValueMap("int_lab_testdetailid")].equals("-1")){
			//labEntriesSave.setLabEntriesTestdetailId(-1);
		}else{
			labEntriesSave=labEntriesRepository.findOne(InvestigationSpecification.testdetailIds(Integer.parseInt(dataStr[dataValueMap("int_lab_testdetailid")])));
			testEncounterId = Optional.fromNullable(labEntriesSave.getLabEntriesEncounterId()).or(-1);
			if(testEncounterId != encounterId){
				if(Integer.parseInt(dataStr[dataValueMap("int_lab_teststatus")]) >= 3) {
					int labid = Integer.parseInt(dataStr[dataValueMap("int_lab_testdetailid")]);
					PreviousVisitLabLog(labid);
				}
			}
		}
		labEntriesSave.setLabEntriesEncounterId(labEncounterId);
		if(dataStr[dataValueMap("int_lab_testid")] != null)
			labEntriesSave.setLabEntriesTestId(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_testid")]).or("-1").toString()));
		if(dataStr[dataValueMap("int_lab_intext")] != null)
			labEntriesSave.setLabEntriesTestType(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_intext")]).or("0").toString()));
		if(dataStr[dataValueMap("int_lab_printxslurl")] != null)    	
			labEntriesSave.setLabEntriesPrintxsl(Optional.fromNullable(dataStr[dataValueMap("int_lab_printxslurl")]).or(""));
		// 	0 - Internal 1 - External  Results.
		if(dataStr[dataValueMap("int_lab_orderby")] != null)
			labEntriesSave.setLabEntriesOrdBy(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_orderby")]).or("-1")));
		if(dataStr[dataValueMap("int_lab_orderon")] != null){
			labEntriesSave.setLabEntriesOrdOn(Timestamp.valueOf(dataStr[dataValueMap("int_lab_orderon")]));
		}
		//[DHC for notes col
		if(dataStr[dataValueMap("int_lab_short_order_notes")] != null){
			if(dataStr[dataValueMap("int_lab_short_order_notes")].equals("Order Notes")){
				dataStr[dataValueMap("int_lab_short_order_notes")]="";
			}
			labEntriesSave.setLabEntriesShortorderNotes(HUtil.ValidateSingleQuote(dataStr[dataValueMap("int_lab_short_order_notes")]));
		}
		if(dataStr[dataValueMap("int_lab_scheduled_date")] != null)
			labEntriesSave.setLabEntriesScheduleddate(HUtil.ValidateSingleQuote(dataStr[dataValueMap("int_lab_scheduled_date")]));

		//DHC]
		if(dataStr[dataValueMap("int_lab_site")] != null){
			if (!dataStr[dataValueMap("int_lab_site")].equals("") )
				labEntriesSave.setLabEntriesLabSite(dataStr[dataValueMap("int_lab_site")]);
		}
		if(dataStr[dataValueMap("int_lab_route")] != null){
			if (!dataStr[dataValueMap("int_lab_route")].equals("") )
				labEntriesSave.setLabEntriesLabRoute(dataStr[dataValueMap("int_lab_route")]);
		}
		if(dataStr[dataValueMap("int_lab_vaccinevis")] != null){
			if (!dataStr[dataValueMap("int_lab_vaccinevis")].equals("") )
				labEntriesSave.setLabEntriesVaccineVis(dataStr[dataValueMap("int_lab_vaccinevis")]);
		}
		if(dataStr[dataValueMap("int_lab_performby")] != null){
			if (!dataStr[dataValueMap("int_lab_performby")].equals("") && !dataStr[dataValueMap("int_lab_performby")].equalsIgnoreCase("null") )
				labEntriesSave.setLabEntriesPerfBy(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_performby")]).or("-1").toString()));
		}
		if(dataStr[dataValueMap("int_lab_performon")] != null){
			if (!dataStr[dataValueMap("int_lab_performon")].equals(""))
				labEntriesSave.setLabEntriesPerfOn(Timestamp.valueOf(dataStr[dataValueMap("int_lab_performon")]));
		}
		if(dataStr[dataValueMap("int_lab_reviewby")] != null){
			if (!dataStr[dataValueMap("int_lab_reviewby")].equals(""))
				labEntriesSave.setLabEntriesRevBy(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_reviewby")]).or("-1").toString()));
		}
		if(dataStr[dataValueMap("int_lab_reviewon")] != null){
			if (!dataStr[dataValueMap("int_lab_reviewon")].equals(""))
				labEntriesSave.setLabEntriesRevOn(Timestamp.valueOf(dataStr[dataValueMap("int_lab_reviewon")]));
		}
		if(dataStr[dataValueMap("int_lab_informby")] != null){
			if (!dataStr[dataValueMap("int_lab_informby")].equals(""))
				labEntriesSave.setLabEntriesInformedBy(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_informby")]).or("-1").toString()));
		}
		if(dataStr[dataValueMap("int_lab_informon")] != null){
			if (!dataStr[dataValueMap("int_lab_informon")].equals("")){
				labEntriesSave.setLabEntriesInformedOn(Timestamp.valueOf(dataStr[dataValueMap("int_lab_informon")]));
			}
		}
		if(dataStr[dataValueMap("int_lab_prelimresult")] != null){
			if (!dataStr[dataValueMap("int_lab_prelimresult")].equals(""))
				labEntriesSave.setLabEntriesPrelimresult(dataStr[dataValueMap("int_lab_prelimresult")]);
		}
		if(dataStr[dataValueMap("int_lab_confirmresult")] != null){
			if (!dataStr[dataValueMap("int_lab_confirmresult")].equals(""))
				labEntriesSave.setLabEntriesConfirmTestStatus(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_confirmresult")]).or("-1").toString()));
		}
		labEntriesSave.setLabEntriesModifiedby(userId);
		Date dateMod= new Date();
		Timestamp timeStampMod=new Timestamp(dateMod.getTime());
		labEntriesSave.setLabEntriesModifiedon(timeStampMod);
		labEntriesSave.setLabEntriesChartid(chartId);
		if(dataStr[dataValueMap("int_lab_dx1id")] != null || dataStr[dataValueMap("int_lab_dx2id")] != null){ 
			int dxid1 = Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx1id")]).or("-1"));
			int dxid2 = Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx2id")]).or("-1"));
			if(dxid1 != -1)
				labEntriesSave.setLabEntriesDx1(dxid1);
			else
				labEntriesSave.setLabEntriesDx1(null);
			if(dxid2 != -1)
				labEntriesSave.setLabEntriesDx2(dxid2);
			else
				labEntriesSave.setLabEntriesDx2(null);
		}
		if(dataStr[dataValueMap("int_lab_dx1code")] != null)
			labEntriesSave.setLabEntriesDx1code(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx1code")]).or("NODX").trim());
		if(dataStr[dataValueMap("int_lab_dx2code")] != null)
			labEntriesSave.setLabEntriesDx2code(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx2code")]).or("").trim());
		if(dataStr[dataValueMap("int_lab_dx3code")] != null)
			labEntriesSave.setLabEntriesDx3code(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx3code")]).or("").trim());
		if(dataStr[dataValueMap("int_lab_dx4code")] != null)
			labEntriesSave.setLabEntriesDx4code(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx4code")]).or("").trim());
		if(dataStr[dataValueMap("int_lab_dx5code")] != null)
			labEntriesSave.setLabEntriesDx5code(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx5code")]).or("").trim());
		if(dataStr[dataValueMap("int_lab_dx6code")] != null)
			labEntriesSave.setLabEntriesDx6code(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx6code")]).or("").trim());
		if(dataStr[dataValueMap("int_lab_dx7code")] != null)
			labEntriesSave.setLabEntriesDx7code(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx7code")]).or("").trim());
		if(dataStr[dataValueMap("int_lab_dx8code")] != null)
			labEntriesSave.setLabEntriesDx8code(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx8code")]).or("").trim());

		if(dataStr[dataValueMap("int_lab_dx1code_codedesc")] != null)
			labEntriesSave.setLabEntriesDx1codeCodedesc(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx1code_codedesc")]).or("").trim());
		if(dataStr[dataValueMap("int_lab_dx2code_codedesc")] != null)
			labEntriesSave.setLabEntriesDx2codeCodedesc(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx2code_codedesc")]).or("").trim());
		if(dataStr[dataValueMap("int_lab_dx3code_codedesc")] != null)
			labEntriesSave.setLabEntriesDx3codeCodedesc(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx3code_codedesc")]).or("").trim());
		if(dataStr[dataValueMap("int_lab_dx4code_codedesc")] != null)
			labEntriesSave.setLabEntriesDx4codeCodedesc(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx4code_codedesc")]).or("").trim());
		if(dataStr[dataValueMap("int_lab_dx5code_codedesc")] != null)
			labEntriesSave.setLabEntriesDx5codeCodedesc(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx5code_codedesc")]).or("").trim());
		if(dataStr[dataValueMap("int_lab_dx6code_codedesc")] != null)
			labEntriesSave.setLabEntriesDx6codeCodedesc(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx6code_codedesc")]).or("").trim());
		if(dataStr[dataValueMap("int_lab_dx7code_codedesc")] != null)
			labEntriesSave.setLabEntriesDx7codeCodedesc(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx7code_codedesc")]).or("").trim());
		if(dataStr[dataValueMap("int_lab_dx8code_codedesc")] != null)
			labEntriesSave.setLabEntriesDx8codeCodedesc(Optional.fromNullable(dataStr[dataValueMap("int_lab_dx8code_codedesc")]).or("").trim());

		if ((dataStr[dataValueMap("int_lab_teststatus")]!=null) && (!dataStr[dataValueMap("int_lab_teststatus")].equals("")))
			labEntriesSave.setLabEntriesTestStatus(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_teststatus")]).or("-1").toString()));
		if(dataStr[dataValueMap("int_lab_labname")] != null){
			if (!dataStr[dataValueMap("int_lab_labname")].equals(""))
				labEntriesSave.setLabEntriesTestDesc(dataStr[dataValueMap("int_lab_labname")]);
		}
		if(dataStr[dataValueMap("int_lab_referredto")] != null){
			if (! dataStr[dataValueMap("int_lab_referredto")].equals(""))
				labEntriesSave.setLabEntriesRefdocId(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_referredto")]).or("-1").toString()));
		}
		if(dataStr[dataValueMap("int_lab_cpt")] != null)
			labEntriesSave.setLabEntriesCpt(dataStr[dataValueMap("int_lab_cpt")]);
		if(dataStr[dataValueMap("int_lab_shortnotes")] != null)
			labEntriesSave.setLabEntriesResultNotes(URLDecoder.decode(Optional.fromNullable(dataStr[dataValueMap("int_lab_shortnotes")]).or(""),"UTF-8"));
		if(dataStr[dataValueMap("int_lab_status")] != null)
		{
			if (! dataStr[dataValueMap("int_lab_status")].equals(""))
				labEntriesSave.setLabEntriesStatus(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_status")]).or("-1").toString()));
		}

		if(dataStr[dataValueMap("int_lab_dx1code_codesystem")] != null)
		{
			if (! dataStr[dataValueMap("int_lab_dx1code_codesystem")].equals(""))
				labEntriesSave.setLabEntriesDx1codeCodesystem(dataStr[dataValueMap("int_lab_dx1code_codesystem")]);
			else
				labEntriesSave.setLabEntriesDx1codeCodesystem("2.16.840.1.113883.6.90");
		}
		if(dataStr[dataValueMap("int_lab_dx2code_codesystem")] != null)
		{
			if (! dataStr[dataValueMap("int_lab_dx2code_codesystem")].equals(""))
				labEntriesSave.setLabEntriesDx2codeCodesystem(dataStr[dataValueMap("int_lab_dx2code_codesystem")]);
		}
		if(dataStr[dataValueMap("int_lab_dx3code_codesystem")] != null)
		{
			if (! dataStr[dataValueMap("int_lab_dx3code_codesystem")].equals(""))
				labEntriesSave.setLabEntriesDx3codeCodesystem(dataStr[dataValueMap("int_lab_dx3code_codesystem")]);
		}
		if(dataStr[dataValueMap("int_lab_dx4code_codesystem")] != null)
		{
			if (! dataStr[dataValueMap("int_lab_dx4code_codesystem")].equals(""))
				labEntriesSave.setLabEntriesDx4codeCodesystem(dataStr[dataValueMap("int_lab_dx4code_codesystem")]);
		}
		if(dataStr[dataValueMap("int_lab_dx5code_codesystem")] != null)
		{
			if (! dataStr[dataValueMap("int_lab_dx5code_codesystem")].equals(""))
				labEntriesSave.setLabEntriesDx5codeCodesystem(dataStr[dataValueMap("int_lab_dx5code_codesystem")]);
		}
		if(dataStr[dataValueMap("int_lab_dx6code_codesystem")] != null)
		{
			if (! dataStr[dataValueMap("int_lab_dx6code_codesystem")].equals(""))
				labEntriesSave.setLabEntriesDx6codeCodesystem(dataStr[dataValueMap("int_lab_dx6code_codesystem")]);
		}
		if(dataStr[dataValueMap("int_lab_dx7code_codesystem")] != null)
		{
			if (! dataStr[dataValueMap("int_lab_dx7code_codesystem")].equals(""))
				labEntriesSave.setLabEntriesDx7codeCodesystem(dataStr[dataValueMap("int_lab_dx7code_codesystem")]);
		}
		if(dataStr[dataValueMap("int_lab_dx8code_codesystem")] != null)
		{
			if (! dataStr[dataValueMap("int_lab_dx8code_codesystem")].equals(""))
				labEntriesSave.setLabEntriesDx8codeCodesystem(dataStr[dataValueMap("int_lab_dx8code_codesystem")]);
		}
		if(dataStr[dataValueMap("int_lab_administration_notes")] != null)
		{
			if (! dataStr[dataValueMap("int_lab_administration_notes")].equals("")){
				String administrationvalue = dataStr[dataValueMap("int_lab_administration_notes")]; 
				if(!administrationvalue.equals("-1"))
					labEntriesSave.setLabEntriesAdministrationNotes(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_administration_notes")]).or("-1").toString()));
				else
					labEntriesSave.setLabEntriesAdministrationNotes(0);
			}
		} else {
			labEntriesSave.setLabEntriesAdministrationNotes(0);
		}
		if(dataStr[dataValueMap("int_lab_refusalreason")] != null)
		{
			if (! dataStr[dataValueMap("int_lab_refusalreason")].equals(""))
				labEntriesSave.setLabEntriesRefusalreason(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_refusalreason")]).or("-1").toString()));
		} else {
			labEntriesSave.setLabEntriesRefusalreason(0);
		}
		if((dataStr[dataValueMap("int_lab_enteredby")] != null)){
			if(!dataStr[dataValueMap("int_lab_enteredby")].equals(""))
				labEntriesSave.setLabEntriesEnteredBy(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_enteredby")]).or("-1").toString()));
			else
				labEntriesSave.setLabEntriesEnteredBy(-1);
		}
		if(dataStr[dataValueMap("int_lab_enteredon")] != null){
			if(!dataStr[dataValueMap("int_lab_enteredon")].equals(""))
				labEntriesSave.setLabEntriesEnteredOn(Timestamp.valueOf(dataStr[dataValueMap("int_lab_enteredon")]));
			else{
				Date dateEntered= new Date();
				Timestamp timeStampEntered=new Timestamp(dateEntered.getTime());
				labEntriesSave.setLabEntriesEnteredOn(timeStampEntered);
			}
		}

		if((dataStr[dataValueMap("int_lab_bodysitecode")] != null)){
			if(!dataStr[dataValueMap("int_lab_bodysitecode")].equals(""))
				labEntriesSave.setLabEntriesBodysiteCode(dataStr[dataValueMap("int_lab_bodysitecode")]);
			else
				labEntriesSave.setLabEntriesBodysiteCode("");
		}
		if(dataStr[dataValueMap("int_lab_bodysitedesc")] != null){
			if(!dataStr[dataValueMap("int_lab_bodysitedesc")].equals(""))
				labEntriesSave.setLabEntriesBodysiteDesc(dataStr[dataValueMap("int_lab_bodysitedesc")]);
			else	
				labEntriesSave.setLabEntriesBodysiteDesc("");
		}
		logger.debug("Save logic for vaccines and injections starts here.");
		/*
		 *	For Vaccines and Injestions alone 
		 *     
		 */
		List<InitialSettings> initialSettingVacc=initialSettingsRepository.findAll(InitialSettingsSpecification.optionId(22));
		String vacgroupid = "";
		if(initialSettingVacc.size()>0)
			vacgroupid=Optional.fromNullable(initialSettingVacc.get(0).getInitialSettingsOptionValue()).or("");
		List<InitialSettings> initialSettingChemo=initialSettingsRepository.findAll(InitialSettingsSpecification.optionId(6335));
		String chemogroupid="";
		if(initialSettingChemo.size()>0)
			chemogroupid=Optional.fromNullable(initialSettingChemo.get(0).getInitialSettingsOptionValue()).or("");
		List<InitialSettings> initialSettingSub=initialSettingsRepository.findAll(InitialSettingsSpecification.optionId(1020));
		String groupid=Optional.fromNullable(dataStr[dataValueMap("int_lab_groupid")]).or("-1");
		String supGroupId ="";
		if(initialSettingSub.size()>0)
			supGroupId=Optional.fromNullable(initialSettingSub.get(0).getInitialSettingsOptionValue()).or("");
		LabEntries labStatus=labEntriesRepository.findOne(InvestigationSpecification.testdetailIds(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_testdetailid")]).or("-1").toString())));
		//LabEntries labStatus=getLabStatus(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_testdetailid")]).or("-1").toString()));
		int tmp_lab_or_vac_status= -1;
		if(labStatus!=null){
			tmp_lab_or_vac_status=Optional.fromNullable(labStatus.getLabEntriesTestStatus()).or(-1);
		}
		//Putting entry in vaccination records for performed vaccinations
		if(groupid.equals(vacgroupid) && !Optional.fromNullable(dataStr[dataValueMap("int_lab_testid")]).or("-1").equals("-1")){
			if(lab_status==7 ||lab_status==8 ){
				if((tmp_lab_or_vac_status >=3) && (tmp_lab_or_vac_status <=6)){					//Deleted lab is in performed status, Entry in vaccination report have to be removed. 
					deleteVaccineDosage(labEncounterId, chartId, Optional.fromNullable(dataStr[dataValueMap("int_lab_testid")]).or("-1"));
				}
			}else if(lab_status>=3){
				if ((dataStr[dataValueMap("int_lab_Dosagelevel")]!=null) && (!dataStr[dataValueMap("int_lab_Dosagelevel")].equals(""))){
					saveVaccineDosage(dataStr[dataValueMap("int_lab_Dosagelevel")],dataStr[dataValueMap("int_lab_testid")],Integer.parseInt(dataStr[dataValueMap("int_lab_performby")]), labEncounterId,  dataStr[dataValueMap("int_lab_performon")].split(" ")[0],Integer.parseInt(dataStr[dataValueMap("int_lab_testdetailid")]),dataStr[dataValueMap("int_lab_shortnotes")]);
				}
			}
		}

		if((dataStr[dataValueMap("int_lab_lotno")]!=null) && (!dataStr[dataValueMap("int_lab_lotno")].equals(""))){
			saveLabIsNewLotUnit = "0"+"~"+Optional.fromNullable(dataStr[dataValueMap("int_lab_unitno")]).or("1")+"~"+Optional.fromNullable(dataStr[dataValueMap("int_lab_oldunitno")]).or("1")+"~"+dataStr[dataValueMap("int_lab_lotno")]+"~"+Optional.fromNullable(dataStr[dataValueMap("int_lab_oldlotno")]).or("-1");
			labEntriesSave.setLabEntriesLotNo(Integer.parseInt(dataStr[dataValueMap("int_lab_lotno")].toString()));

			if(groupid.equals(supGroupId) && dataStr[dataValueMap("int_lab_unitno")]!=null){
				labEntriesSave.setLabEntriesUnits(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_unitno")]).or("-1").toString()));
			}else{
				labEntriesSave.setLabEntriesUnits(1);
			}
			if(lab_status==7)
			{
				if(!dataStr[dataValueMap("int_lab_lotno")].equals("-1")){
					if(groupid.equals(vacgroupid)||groupid.equals("5") ){
						decreaseVaccineCount(dataStr[dataValueMap("int_lab_lotno")],dataStr[dataValueMap("int_lab_dosage")]);	    		
					}
				}
			}
			if(lab_status >= 3){
				int curunit = Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_unitno")]).or("1"));
				int prevunit = Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_oldunitno")]).or("1"));
				if(tmp_lab_or_vac_status >=3){							//Already Performed Labs
					if(lab_status < 7){
						if(!dataStr[dataValueMap("int_lab_lotno")].equals(dataStr[dataValueMap("int_lab_oldlotno")])){				//Lot number is changed
							if(groupid.equals(vacgroupid)){
					    		if(!dataStr[dataValueMap("int_lab_lotno")].equals("-1") && !dataStr[dataValueMap("int_lab_dosage")].equals("-1") )
					    			increaseVaccineCount(dataStr[dataValueMap("int_lab_lotno")],dataStr[dataValueMap("int_lab_dosage")]);
								if(dataStr[dataValueMap("int_lab_oldlotno")] != null && !dataStr[dataValueMap("int_lab_oldlotno")].equals("-1") )
									decreaseVaccineCount(dataStr[dataValueMap("int_lab_oldlotno")]);
							}else{
					    		if(!dataStr[dataValueMap("int_lab_lotno")].equals("-1") && !dataStr[dataValueMap("int_lab_dosage")].equals("-1"))
					    			increaseVaccineCount(dataStr[dataValueMap("int_lab_lotno")],dataStr[dataValueMap("int_lab_dosage")]);
								if( dataStr[dataValueMap("int_lab_oldlotno")] != null && !dataStr[dataValueMap("int_lab_oldlotno")].equals("-1") )
									decreaseVaccineCount(dataStr[dataValueMap("int_lab_oldlotno")],prevunit);
							}
						}else{																										//Only doses changed
							if(!groupid.equals(vacgroupid) && !dataStr[dataValueMap("int_lab_lotno")].equals("-1"))																			//For injections alone
								increaseVaccineCount(dataStr[dataValueMap("int_lab_lotno")],(curunit - prevunit));
						}
					}else if(tmp_lab_or_vac_status < 7 && dataStr[dataValueMap("int_lab_oldlotno")] != null && !dataStr[dataValueMap("int_lab_oldlotno")].equals("-1") ){					//Delete already performed labs
						if(groupid.equals(vacgroupid))
						decreaseVaccineCount(dataStr[dataValueMap("int_lab_oldlotno")]);
					}
				}else if(lab_status < 7 && !dataStr[dataValueMap("int_lab_lotno")].equals("-1")){													//Labs that are not already performed
					if(groupid.equals(vacgroupid)){
						increaseVaccineCount(dataStr[dataValueMap("int_lab_lotno")]);
					}else{
						increaseVaccineCount(dataStr[dataValueMap("int_lab_lotno")],dataStr[dataValueMap("int_lab_dosage")]);	    		
					}
				}

				if(groupid.equals(vacgroupid)){
					if ((dataStr[dataValueMap("int_lab_oldunitno")]!=null) && (!dataStr[dataValueMap("int_lab_oldunitno")].equals(""))){
						labEntriesSave.setLabEntriesZunits(1);
					} else {
						labEntriesSave.setLabEntriesZunits(0);
					}
				}else{
					if ((dataStr[dataValueMap("int_lab_oldunitno")]!=null) && (!dataStr[dataValueMap("int_lab_oldunitno")].equals(""))){
						labEntriesSave.setLabEntriesZunits(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_unitno")]).or("-1").toString()));
					} else {
						labEntriesSave.setLabEntriesZunits(0);
					}
				}
			}
		}

		if ((dataStr[dataValueMap("int_lab_groupid")]!=null) && (!dataStr[dataValueMap("int_lab_groupid")].equals("")))
			labEntriesSave.setLabEntriesGroupid(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_groupid")]).or("-1").toString()));
		if ((dataStr[dataValueMap("int_lab_isbillable")]!=null) && (!dataStr[dataValueMap("int_lab_isbillable")].equals("")))
			labEntriesSave.setLabEntriesIsBillable(Boolean.parseBoolean(dataStr[dataValueMap("int_lab_isbillable")]));

		if ((dataStr[dataValueMap("int_lab_iscritical")]!=null))
			labEntriesSave.setLabEntriesCriticalStatus(Integer.parseInt(MoreObjects.firstNonNull(dataStr[dataValueMap("int_lab_iscritical")],0).toString()));

		if ((dataStr[dataValueMap("int_lab_reminder_date")]!=null) && (dataStr[dataValueMap("int_lab_teststatus")].equalsIgnoreCase("1")) && (dataStr[dataValueMap("int_lab_groupid")].equalsIgnoreCase("1")))
			labEntriesSave.setLabEntriesNextReminderDate(Timestamp.valueOf(dataStr[dataValueMap("int_lab_reminder_date")]));

		if ((dataStr[dataValueMap("int_lab_delinquency_days")]!=null) && (!dataStr[dataValueMap("int_lab_delinquency_days")].equals("")) && (dataStr[dataValueMap("int_lab_teststatus")].equalsIgnoreCase("1")) && (dataStr[dataValueMap("int_lab_groupid")].equalsIgnoreCase("1")))
			labEntriesSave.setLabEntriesDelinquencyDays(Integer.parseInt(MoreObjects.firstNonNull(dataStr[dataValueMap("int_lab_delinquency_days")],0).toString()));


		//[dhc added for observed units
		if(dataStr[dataValueMap("int_lab_observedunits")] != null){
			if (!dataStr[dataValueMap("int_lab_observedunits")].equals(""))
				labEntriesSave.setLabEntriesObsUnits(dataStr[dataValueMap("int_lab_observedunits")]);
		}
		if(dataStr[dataValueMap("int_lab_observationidentifier")] != null){
			if (!dataStr[dataValueMap("int_lab_observationidentifier")].equals(""))
				labEntriesSave.setLabEntriesObsId(Integer.parseInt(Optional.fromNullable(dataStr[dataValueMap("int_lab_observationidentifier")]).or("-1").toString()));
		}
		if(dataStr[dataValueMap("int_lab_observationvalue")] != null){
			if (!dataStr[dataValueMap("int_lab_observationvalue")].equals(""))
				labEntriesSave.setLabEntriesObsValue(dataStr[dataValueMap("int_lab_observationvalue")]);
		}
		//dhc]
		//[dhc- HL7 lab_sourceofspecimen     int_lab_sourceofspecimen
		if(dataStr[dataValueMap("int_lab_ndc")] != null){
			if (!dataStr[dataValueMap("int_lab_ndc")].equals(""))
				labEntriesSave.setLabEntriesNdc(dataStr[dataValueMap("int_lab_ndc")]);
		}
		if(dataStr[dataValueMap("int_lab_cvx")] != null){	       
			if (!dataStr[dataValueMap("int_lab_cvx")].equals(""))
				labEntriesSave.setLabEntriesCvx(dataStr[dataValueMap("int_lab_cvx")]);
		}
		if ((dataStr[dataValueMap("int_lab_isfasting")]!=null) && (!dataStr[dataValueMap("int_lab_isfasting")].equals("")))
			labEntriesSave.setLabEntriesIsFasting(Boolean.parseBoolean(dataStr[dataValueMap("int_lab_isfasting")]));
		else
			labEntriesSave.setLabEntriesIsFasting(false);
		if(dataStr[dataValueMap("int_lab_qnty")] != null){ 
			if (!dataStr[dataValueMap("int_lab_qnty")].equals(""))
				labEntriesSave.setLabEntriesCptUnits(dataStr[dataValueMap("int_lab_qnty")]);
		}
		if(dataStr[dataValueMap("int_lab_ischdp")] != null)
			labEntriesSave.setLabEntriesIschdplab(Integer.parseInt(dataStr[dataValueMap("int_lab_ischdp")]));
		else
			labEntriesSave.setLabEntriesIschdplab(1);
		if(dataStr[dataValueMap("int_lab_instruction")] != null)
			labEntriesSave.setLabEntriesInstruction(dataStr[dataValueMap("int_lab_instruction")]);
		if(dataStr[dataValueMap("int_lab_strength")] != null){
			labEntriesSave.setLabEntriesStrength(dataStr[dataValueMap("int_lab_strength")]);
		}
		if(dataStr[dataValueMap("int_lab_strengthunit")] != null){
			labEntriesSave.setLabEntriesStrengthUnit(dataStr[dataValueMap("int_lab_strengthunit")]);
		}
		if(dataStr[dataValueMap("int_lab_basedose")] != null && !dataStr[dataValueMap("int_lab_basedose")].equals("")){
			labEntriesSave.setLabEntriesBasedose(dataStr[dataValueMap("int_lab_basedose")]);
		}
		if(dataStr[dataValueMap("int_lab_basedoseunit")] != null && !dataStr[dataValueMap("int_lab_basedoseunit")].equals("")){
			labEntriesSave.setLabEntriesBasedoseUnit(dataStr[dataValueMap("int_lab_basedoseunit")]);
		}
		if(dataStr[dataValueMap("int_lab_dosage")] != null && !dataStr[dataValueMap("int_lab_dosage")].equals("")){
			labEntriesSave.setLabEntriesDosage(dataStr[dataValueMap("int_lab_dosage")]);
		}
		if(dataStr[dataValueMap("int_lab_drug")] != null){
			if (!dataStr[dataValueMap("int_lab_drug")].equals(""))
				labEntriesSave.setLabEntriesDrugxml(dataStr[dataValueMap("int_lab_drug")]);
		}
		if(dataStr[dataValueMap("int_lab_loinc")] != null){
			if (!dataStr[dataValueMap("int_lab_loinc")].equals(""))
				labEntriesSave.setLabEntriesLoinc(dataStr[dataValueMap("int_lab_loinc")]);
		}
		if(dataStr[dataValueMap("int_lab_snomed")] != null){
			if (!dataStr[dataValueMap("int_lab_snomed")].equals(""))
				labEntriesSave.setLabEntriesSnomed(dataStr[dataValueMap("int_lab_snomed")]);
		}
		if(dataStr[dataValueMap("int_lab_collectionvolume")]!=null){
			if(!dataStr[dataValueMap("int_lab_collectionvolume")].equals(""))
				labEntriesSave.setLabEntriesSpecimenCollVol(dataStr[dataValueMap("int_lab_collectionvolume")]);
		}
		//MPK
		if(dataStr[dataValueMap("int_lab_sourceofspecimen")] != null && !dataStr[dataValueMap("int_lab_sourceofspecimen")].equals("")){
			Specimen specimen = null;
			if(dataStr[dataValueMap("int_lab_specimendate")] != null && !(dataStr[dataValueMap("int_lab_specimendate")].equals(""))){
				specimen = specimenRepository.findOne(Specifications.where(InvestigationSpecification.specimenSource(dataStr[dataValueMap("int_lab_sourceofspecimen")]))
						.and(InvestigationSpecification.specimenColVol(dataStr[dataValueMap("int_lab_collectionvolume")]))
						.and(InvestigationSpecification.specimenColVolUnit(dataStr[dataValueMap("int_lab_collectionunits")]))
						.and(InvestigationSpecification.specimenDate(Timestamp.valueOf(dataStr[dataValueMap("int_lab_specimendate")]))));
			} else {
				specimen = specimenRepository.findOne(Specifications.where(InvestigationSpecification.specimenSource(dataStr[dataValueMap("int_lab_sourceofspecimen")]))
						.and(InvestigationSpecification.specimenColVol(dataStr[dataValueMap("int_lab_collectionvolume")]))
						.and(InvestigationSpecification.specimenColVolUnit(dataStr[dataValueMap("int_lab_collectionunits")])));
			}
			long specimenId = -1;
			if( specimen != null ) {
				specimenId = Optional.fromNullable(specimen.getSpecimenId()).or(-1);
			}
			if(specimenId == -1) {
				if(dataStr[dataValueMap("int_lab_collectionunits")] != null){
					if (!dataStr[dataValueMap("int_lab_collectionunits")].equals(""))
						specimenSave.setSpecimenCollVolUnit(dataStr[dataValueMap("int_lab_collectionunits")]);
				}
				if(dataStr[dataValueMap("int_lab_specimendate")] != null){
					if (!dataStr[dataValueMap("int_lab_specimendate")].equals(""))
						specimenSave.setSpecimenDate(Timestamp.valueOf(dataStr[dataValueMap("int_lab_specimendate")]));
				}
				if(dataStr[dataValueMap("int_lab_sourceofspecimen")] != null){
					if (!dataStr[dataValueMap("int_lab_sourceofspecimen")].equals(""))
						specimenSave.setSpecimenSource(dataStr[dataValueMap("int_lab_sourceofspecimen")]);
				}
				if(dataStr[dataValueMap("int_lab_collectionvolume")] != null){
					if (!dataStr[dataValueMap("int_lab_collectionvolume")].equals(""))
						specimenSave.setSpecimenCollVol(dataStr[dataValueMap("int_lab_collectionvolume")]);
				}
				specimenId = specimenRepository.saveAndFlush(specimenSave).getSpecimenId();
			}
			labEntriesSave.setLabEntriesSepcimenId(Integer.parseInt(specimenId+""));
		} else {
			labEntriesSave.setLabEntriesSepcimenId(-1);
		}
		labEntriesSave.setLabEntriesImmunizationstatus(0);
		if(dataStr[dataValueMap("int_lab_testdetailid")].equals("-1")){
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<LabEntries> root = cq.from(LabEntries.class);
			cq.multiselect(builder.coalesce(builder.max(root.get(LabEntries_.labEntriesTestdetailId)),-1));
			int testDetTemp=Integer.parseInt(MoreObjects.firstNonNull(Integer.parseInt(em.createQuery(cq).getSingleResult().toString()),-1).toString());
			labEntriesRepository.saveAndFlush(labEntriesSave);
			CriteriaBuilder builder1 = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq1 = builder1.createQuery();
			Root<LabEntries> root1 = cq1.from(LabEntries.class);
			cq1.multiselect(builder1.coalesce(builder1.max(root1.get(LabEntries_.labEntriesTestdetailId)),-1));
			int testDetAfterSave=Integer.parseInt(MoreObjects.firstNonNull(em.createQuery(cq1).getSingleResult().toString(),-1).toString());
			if(testDetAfterSave==(testDetTemp+1))
				saveid=testDetAfterSave+"";
			else
				saveid="-1";
		}else{
			LabEntries saveidLab=labEntriesRepository.saveAndFlush(labEntriesSave);
			saveid=saveidLab.getLabEntriesTestdetailId()+"";
		}
		if(dataStr[dataValueMap("int_lab_testdetailid")].equals("-1")){
			updateLabHitCount(Integer.parseInt(dataStr[dataValueMap("int_lab_testid")]));
		}

		String paramString = dataStr[dataValueMap("int_lab_resultdata")];
		String visPatientInfo = "";
		if(dataStr[dataValueMap("int_lab_patientvisinfoentries")]!=null)
			visPatientInfo = (dataStr[dataValueMap("int_lab_patientvisinfoentries")].toString()).replace("@cell@", "#~@~").replace("@row@", "~#~");
		String persumeimmunityinfo="";
		if(dataStr[dataValueMap("int_lab_persumeimmunityifo")]!=null)
			persumeimmunityinfo = dataStr[dataValueMap("int_lab_persumeimmunityifo")];
		SavePatientVISinformation(Integer.parseInt(saveid),visPatientInfo);
		//List<Chart> chart=chartRepository.findAll(ChartSpecification.findByChartId(chartId));
		int tmpPatientId = -1;
		
		/*if( chart.size() > 0 ) {
			tmpPatientId = chart.get(0).getChartPatientid();
		}*/
		tmpPatientId=getPatientIdinChart(chartId);
		SavePatientImmunityinformation(labEncounterId,tmpPatientId,chartId,persumeimmunityinfo);
		SaveParameters(chartId, Integer.parseInt(saveid), paramString);
		if(dataStr[dataValueMap("int_lab_testdetailid")].equals("-1")){
			if(!dx1code.equals("")){
				insert_assc_prblm(dx1code, Integer.parseInt(saveid), 1, userId);
				if(!dx2code.equals("")){
					insert_assc_prblm(dx2code, Integer.parseInt(saveid), 1, userId);
				}
			}
		}
		int refId = Integer.parseInt(saveid);
		int isExternal = Integer.parseInt(HUtil.Nz(dataStr[dataValueMap("int_lab_intext")],"0"));
		String Message =  dataStr[dataValueMap("int_lab_labname")];
		String isDefault = "t";	//If lab_alertforwardstatus_todefault field in lab_alertforwardstatus is false, then alert shouldnot be forwarded.
		logger.debug("Adding alert for the lab.");
		LabAlertforwardstatus labAlertforwardstatus = labAlertforwardstatusRepository.findOne(InvestigationSpecification.labAlertForwardLabStatus(lab_status));
		if( labAlertforwardstatus != null ) {
			isDefault = "" + labAlertforwardstatus.getLabAlertforwardstatusTodefault();
		} else {
			isDefault = "t";
		}
		List<Integer> isSpecificList = new ArrayList<Integer>();
		int isSpecific = 0;
		int speGrpId = 0;
		if ( !groupid.equals("") ) {
			List<AlertCategory> alertCategory = alertCategoryRepository.findAll(AlertCategorySpecification.getAlertCatByType(Integer.parseInt(groupid)));
			for (int i = 0; i < alertCategory.size(); i++) {
				AlertCategory alerts = alertCategory.get(i);
				isSpecific = Integer.parseInt(HUtil.Nz(alerts.getAlertCategoryId(),"-1"));
				isSpecificList.add(isSpecific);
			}
			speGrpId = Integer.parseInt(groupid);
		}
		this.empId = getEmpId(labEncounterId);
		this.userId = getLoginUserId(this.empId);
		AlertEvent alertEvent = alertInboxService.getAlertId(patientId, labEncounterId, refId);
		if ( isforward.equals("-1") ) {
			if ( tmp_lab_or_vac_status != lab_status ) {
				for (int i = 0; i < isSpecificList.size(); i++) {
					if ( isSpecificList.get(i) != -1 ) {
						try {
							switch ( lab_status ) {
							case 1:
								if ( isDefault.equalsIgnoreCase("t") ) {
									Message = Message + " has to be done"; 
									if(!dataStr[dataValueMap("int_lab_scheduled_date")].equals(""))
										Message = Message + ".\nScheduled Date: " + dataStr[dataValueMap("int_lab_scheduled_date")];
									putLabAlert(labEncounterId, refId, lab_status, speGrpId, isExternal, Message, alertEvent);
								}
								break;
							case 2:
								deleteLabAlert(alertEvent);
								break;
							case 3:Message = Message + " has been done,result need to be reviewed"; 
							putLabAlert(labEncounterId, refId, lab_status, speGrpId, isExternal, Message, alertEvent);
							break;
							case 4:Message = Message + " result has been reviewed,Patient has to be informed."; 
							putLabAlert(labEncounterId, refId, lab_status, speGrpId, isExternal, Message, alertEvent);
							break;
							case 5:
								deleteLabAlert(alertEvent);
								break;
							case 6:
								deleteLabAlert(alertEvent);
								break;
							case 7:
								deleteLabAlert(alertEvent);
								break;
							case 8:
								deleteLabAlert(alertEvent);
							}	
						} catch( Exception e ) {
							e.printStackTrace();
							throw e;
						}
					} else {
						try {
							switch( lab_status ) {
							case 1:
								if(isDefault.equalsIgnoreCase("t")){
									Message = Message + " has to be done"; 
									if(!dataStr[dataValueMap("int_lab_scheduled_date")].equals(""))
										Message = Message + ".\nScheduled Date: "+dataStr[dataValueMap("int_lab_scheduled_date")];
									putLabAlert(labEncounterId, refId, lab_status, -1, isExternal, Message, alertEvent);
								}
								break;
							case 2:
								deleteLabAlert(alertEvent);
								break;
							case 3:Message = Message + " has been done,result need to be reviewed"; 
							putLabAlert(labEncounterId, refId, lab_status, -1, isExternal, Message, alertEvent);
							break;
							case 4:Message = Message + " result has been reviewed,Patient has to be informed."; 
							putLabAlert(labEncounterId, refId, lab_status, -1, isExternal, Message, alertEvent);
							break;
							case 5:
								deleteLabAlert(alertEvent);
								break;
							case 6:
								deleteLabAlert(alertEvent);
								break;
							case 7:
								deleteLabAlert(alertEvent);
								break;
							case 8:
								deleteLabAlert(alertEvent);
							}
						} catch( Exception e ) {
							e.printStackTrace();
							throw e;
						}
					}				
				}				
			}
		} else {
			int CategoryId = -2;
			int section = -1;
			if ( isExternal == 1 ) {
				section = 3;
			} else {
				section = 2;
			}
			CategoryId = alertInboxService.getCategoryId(section,lab_status);
			boolean isHighPriority = false;  
			String[] labdet = testId.split("['@']");
			for(int i = 0 ; i < labdet.length ; i++) { 
				try{
					if(!labdet[i].equals("")){
						int test_id = Integer.parseInt(labdet[i]);
						int curTestId = Integer.parseInt(HUtil.Nz(dataStr[dataValueMap("int_lab_testid")],"-1"));
						if(test_id == curTestId){
							if(ishighpriority.toString().equals("true"))
								isHighPriority = true;
							try{
								deleteLabAlert(alertEvent);
								switch(lab_status){
								case 1:Message = Message + " has to be done"; 
								if(!dataStr[dataValueMap("int_lab_scheduled_date")].equals(""))
									Message = Message + "\nScheduled Date: "+dataStr[dataValueMap("int_lab_scheduled_date")];
								List<Integer> alertIdList = new ArrayList<Integer>();
								alertIdList.add(alertEvent.getAlertEventId());
								alertInboxService.forwardAlerts(getAlertIdList(alertEvent), "" + CategoryId, Message, "" + userId, "" + toId, "" + isHighPriority);
								break;
								case 2:
									deleteLabAlert(alertEvent);
									break;
								case 3:Message = Message + " has been done,result need to be reviewed"; 
								alertInboxService.forwardAlerts(getAlertIdList(alertEvent), "" + CategoryId, Message, "" + userId, "" + toId, "" + isHighPriority);
								break;
								case 4:Message = Message + " result has been reviewed,Patient has to be informed."; 
								alertInboxService.forwardAlerts(getAlertIdList(alertEvent), "" + CategoryId, Message, "" + userId, "" + toId, "" + isHighPriority);
								break;
								case 5:
								case 6:
								case 7:
								case 8:deleteLabAlert(alertEvent);

								}
							} catch ( Exception e ) {
								e.printStackTrace();
								throw e;
							}
						}	
					}
				} catch( Exception e ) {
					e.printStackTrace();	    
				}
			}
		}
		logger.debug("End of adding alert for the lab.");
		// For changing the LabIsNew to 0 and LotUnit data to new Unit
		saveid = saveid + "##" + saveLabIsNewLotUnit; 

		if(!SaveDxIds.equals("")){
			saveid = saveid + "~^" + SaveDxIds; 
		}
		String message = "";
		String intMessage = "";
		if(dataStr[dataValueMap("int_lab_testdetailid")].equals("-1")){
			message = "Test details saved";
			intMessage = "New test "+Optional.fromNullable(dataStr[dataValueMap("int_lab_labname")]).or("")+ " added with status "+lab_status;
		}else{
			if(lab_status == 7){
				DeleteParameters(Integer.parseInt(dataStr[dataValueMap("int_lab_testdetailid")]));
				message = "Test deleted";
				intMessage = Optional.fromNullable(dataStr[dataValueMap("int_lab_labname")]).or("")+ " deleted";
			}else if(lab_status == 2){
				message = "Test cancelled";
				intMessage = Optional.fromNullable(dataStr[dataValueMap("int_lab_labname")]).or("")+ " cancelled";
			}else if(lab_status == 8){
				message = "Patient Declined the Test";
				intMessage = Optional.fromNullable(dataStr[dataValueMap("int_lab_labname")]).or("")+ " Patient Declined";
			}else{
				message = "Test details updated";
				intMessage = Optional.fromNullable(dataStr[dataValueMap("int_lab_labname")]).or("")+ " updated";
			}
		}
		return saveid;

	}
private int getPatientIdinChart(int chartId2) {

		Integer chartId = -1;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Chart> root = cq.from(Chart.class);
		cq.select(root.get(Chart_.chartPatientid));
		cq.where(builder.equal(root.get(Chart_.chartId), chartId2));
		try{
		chartId = (Integer)em.createQuery(cq).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			chartId=-1;
		}
		return chartId;

	}


/**
 * 
 * @param parseInt
 * @return
 */
	private Integer getLabEntriesEncounterforExist(int parseInt) {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<LabEntries> root=cq.from(LabEntries.class);
			cq.select(root.get(LabEntries_.labEntriesEncounterId));
			cq.where(builder.equal(root.get(LabEntries_.labEntriesTestdetailId), parseInt));
			Integer unmappedResult=-1;
			List<Object> list=em.createQuery(cq).getResultList();
			if(list.size()>0)
				unmappedResult= Integer.parseInt(MoreObjects.firstNonNull(list.get(0),"-1").toString());
			return unmappedResult;
		}


	/**
	 * Method to put lab alert in inbox page
	 * @param labEncounterId
	 * @param refId
	 * @param status
	 * @param labType
	 * @param isExternal
	 * @param message
	 * @param alertEvent
	 */
	private void putLabAlert(int labEncounterId, int refId, int status, int labType, int isExternal, String message, AlertEvent alertEvent) {
		int forwardTo = 0;
		int CategoryId = -2;
		switch( status ) {
		case 1: forwardTo = -2;
		break;
		case 3: forwardTo = -1;
		break;
		case 4: forwardTo = -2;
		break;
		}
		int section = -1;
		if ( isExternal == 1 ) {
			section = 3;
		} else {
			section = 2;
		}
		try {
			//Alert should go to nurse even if ordered by a nurse
			if ( status == 3 ) {	//Test waiting for review alert should go to ordered provider alone.
				LabEntries labData = labEntriesRepository.findOne(InvestigationSpecification.findTest("" + labEncounterId,"" + refId)); 
				if( labData.getLabEntriesOrdBy() == null ) {
					forwardTo = -1;
				} else {
					forwardTo = labData.getLabEntriesOrdBy();
				}
				if(forwardTo == -1 && isExternal == 1) {
					PatientRegistration patRegistration = patientRegistrationRepository.findOne(InvestigationSpecification.checkPatientId(patientId));
					if( patRegistration.getPatientRegistrationPrincipalDoctor() == null ) {
						forwardTo = -1;
					} else {
						forwardTo  = patRegistration.getPatientRegistrationPrincipalDoctor();
					}
				}
			}
			if( labType != -1) {
				CategoryId = alertInboxService.getAlertCategoryId(status, labType);
			}
			if( CategoryId == -1 ) {
				CategoryId = alertInboxService.getCategoryId(section,status);
			}
			deleteLabAlert(alertEvent);
			alertInboxService.forwardAlerts(getAlertIdList(alertEvent), "" + CategoryId, message, "" + userId, "" + forwardTo, ishighpriority);
		} catch(Exception e) {
			logger.debug("error in put lab alert");
		}
	}

	private List<Integer> getAlertIdList(AlertEvent alertEvent) {
		List<Integer> alertIdList = new ArrayList<Integer>();
		alertIdList.add(alertEvent.getAlertEventId());
		return alertIdList;
	}

	private void deleteLabAlert(AlertEvent alertEvent) {	
		if(alertEvent!=null){
			List<Integer> alertIdList = new ArrayList<Integer>();
			alertIdList.add(alertEvent.getAlertEventId());
			alertInboxService.deleteAlert(alertIdList, userId);
		}
	}
	public List<Hl7Unmappedresults> getUnmappedResultTestDetailId(Integer hl7fileid){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Hl7Unmappedresults> cq = builder.createQuery(Hl7Unmappedresults.class);
		Root<Hl7Unmappedresults> root=cq.from(Hl7Unmappedresults.class);
		cq.where(builder.equal(root.get("hl7UnmappedresultsFilewiseId"), hl7fileid));
		List<Hl7Unmappedresults> unmappedResults= em.createQuery(cq).getResultList();

		return unmappedResults;
	}

	public void SavePatientVISinformation(int testDetailId,String visInfo) throws Exception{
		List<PatientVisEntries> pat=new ArrayList<PatientVisEntries>();
		pat=patientVisEntriesRepository.findAll(VaccineReportSpecification.patientVisAdminisId(testDetailId));
		if(pat.size()>0){
			patientVisEntriesRepository.delete(pat);
		}
		if(!visInfo.equals("")){
			String[] visDataPair = visInfo.split("~#~");
			for(int i=0;i<visDataPair.length;i++){
				if( !visDataPair[i].equals("") ) {
					insertVISEntriesDetails(testDetailId,visDataPair[i]);
				}
			}
		}
	}

	public void insertVISEntriesDetails(int testDetailId,String visDataPair) throws Exception{
		String[] visvalues = visDataPair.split("#~@~");
		String visCVXCode =visvalues[1].toString();
		String visGroupCode =visvalues[2].toString();
		String visPublicationDate =visvalues[3].toString();
		String vispatiententiresid =visvalues[4].toString();
		String visPresentationDate =visvalues[5].toString();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PatientVisEntries> root = cq.from(PatientVisEntries.class);
		cq.multiselect(builder.coalesce(builder.max(root.get(PatientVisEntries_.patientVisEntriesId)),0));
		int uniqueid =Integer.parseInt(MoreObjects.firstNonNull(em.createQuery(cq).getSingleResult(),0).toString())+1;
		int loginId = sessionMap.getUserID();
		PatientVisEntries patientVisEntries=new PatientVisEntries();
		patientVisEntries.setPatientVisEntriesId(uniqueid);
		patientVisEntries.setPatientVisEntriesAdministrationId(testDetailId);
		patientVisEntries.setPatientVisEntriesCvx(visCVXCode);
		patientVisEntries.setPatientVisEntriesVaccineGroupCode(visGroupCode);
		patientVisEntries.setPatientVisEntriesPublicationDate(Timestamp.valueOf(visPublicationDate));
		patientVisEntries.setPatientVisEntriesPresentationDate(Timestamp.valueOf(visPresentationDate));
		patientVisEntries.setPatientVisEntriesIsActive(true);
		patientVisEntries.setPatientVisEntriesId(Integer.parseInt(vispatiententiresid));
		patientVisEntries.setPatientVisEntriesCreatedBy(loginId);
		Date dateCreated=new Date();
		Timestamp timeStampCreated=new Timestamp(dateCreated.getTime());
		patientVisEntries.setPatientVisEntriesCreatedOn(timeStampCreated);
		patientVisEntries.setPatientVisEntriesModifiedBy(loginId);
		Date dateModified=new Date();
		Timestamp timeStampModified=new Timestamp(dateModified.getTime());
		patientVisEntries.setPatientVisEntriesModifiedOn(timeStampModified);
		patientVisEntriesRepository.saveAndFlush(patientVisEntries);
	}

	public void SavePatientImmunityinformation(int encounterId,int patientId,int chartid,String persumeimmunityinfo) throws Exception{
		if(!persumeimmunityinfo.equals("")){
			String[] immunityDataPair = persumeimmunityinfo.split("~#~");
			for(int i=0;i<immunityDataPair.length;i++){
				insertImmunityDetails(encounterId,patientId,chartid,immunityDataPair[i]);
			}
		}
	}

	public void insertImmunityDetails(int encounterId,int patientId,int chartid,String immunityDataPair) throws Exception{
		String[] immunityValues = immunityDataPair.split("#~@~");
		int groupcode =Integer.parseInt(immunityValues[1].toString());
		int diseasesCode =Integer.parseInt(immunityValues[2].toString());
		String systemOID =immunityValues[3].toString();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PatientImmunityVaccineMapping> root = cq.from(PatientImmunityVaccineMapping.class);
		cq.multiselect(builder.coalesce(builder.max(root.get(PatientImmunityVaccineMapping_.patientImmunityVaccineMappingId)),0));
		int uniqueid =Integer.parseInt(MoreObjects.firstNonNull(em.createQuery(cq).getSingleResult(),0).toString())+1;
		int loginId = sessionMap.getUserID();
		PatientImmunityVaccineMapping patientImmunityVaccineMappingtemp=new PatientImmunityVaccineMapping();
		patientImmunityVaccineMappingtemp.setPatientImmunityVaccineMappingId(uniqueid);
		patientImmunityVaccineMappingtemp.setPatientImmunityVaccineMappingPatientId(patientId);
		patientImmunityVaccineMappingtemp.setPatientImmunityVaccineMappingEncounterId(encounterId);
		patientImmunityVaccineMappingtemp.setPatientImmunityVaccineGroupCode(groupcode);
		patientImmunityVaccineMappingtemp.setPatientImmunityVaccineMappingDiseaseCode(diseasesCode+"");
		patientImmunityVaccineMappingtemp.setPatientImmunityVaccineMappingDiseaseCodeSystemOid(systemOID);
		patientImmunityVaccineMappingtemp.setPatientImmunityVaccineMappingCreatedBy(loginId);
		Date dateCre=new Date();
		Timestamp timeStampCre=new Timestamp(dateCre.getTime());
		patientImmunityVaccineMappingtemp.setPatientImmunityVaccineMappingCreatedOn(timeStampCre);
		patientImmunityVaccineMappingtemp.setPatientImmunityVaccineMappingChartid(chartid);
		patientImmunityVaccineMappingRepository.saveAndFlush(patientImmunityVaccineMappingtemp);
	}

	public Integer getUnmappedResult(Integer testdetid){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Hl7Unmappedresults> root=cq.from(Hl7Unmappedresults.class);
		cq.distinct(true);
		cq.multiselect(root.get("hl7UnmappedresultsFilewiseId"));
		cq.where(builder.equal(root.get("hl7UnmappedresultsTestdetailId"), testdetid));
		cq.orderBy(builder.desc(root.get("hl7UnmappedresultsFilewiseId")));
		Integer unmappedResult=-1;
		List<Object> list=em.createQuery(cq).getResultList();
		if(list.size()>0)
			unmappedResult= Integer.parseInt(MoreObjects.firstNonNull(list.get(0),"-1").toString());
		return unmappedResult;
	}

	public void deleteVaccineDosage(int encounterid, int chartid, String testid){
		List<VaccineReport> vaccineReportList=vaccineReportRepository.findAll(Specifications.where(VaccineReportSpecification.getChartId(chartid)).and(VaccineReportSpecification.getEncounterId(encounterid)).and(VaccineReportSpecification.getVaccineId(Integer.parseInt(testid))));
		if(vaccineReportList.size()>0){
			vaccineReportRepository.delete(vaccineReportRepository.findAll(Specifications.where(VaccineReportSpecification.getEncounterId(encounterid)).and(VaccineReportSpecification.getVaccineId(Integer.parseInt(testid))).and(VaccineReportSpecification.getChartId(chartid))));
		}
	}

	public void saveVaccineDosage(String dosage,String testid, int performedby, int labencId, String givenDate,int testdetailid, String resultNotes){
		LabEntries labEntriesList=labEntriesRepository.findOne(InvestigationSpecification.testdetailIds(testdetailid));
		int status=1;
		if(labEntriesList!=null)
			status= Optional.fromNullable(labEntriesList.getLabEntriesTestStatus()).or(1);
		List<VaccineReport> vaccineReportList=vaccineReportRepository.findAll(Specifications.where(VaccineReportSpecification.getEncounterId(labencId)).and(VaccineReportSpecification.getVaccineId(Integer.parseInt(testid))));
		int dosagelevel = -1;
		if(vaccineReportList.size()>0)
			dosagelevel=Optional.fromNullable(vaccineReportList.get(0).getVaccineReportDosageLevel()).or(-1);
		if((testdetailid != -1)&& status>2 &&status<7 ){
			if(!dosage.equals("-1")){
				List<VaccineReport> vaccineReportListUpdate=vaccineReportRepository.findAll(Specifications.where(VaccineReportSpecification.getEncounterId(labencId)).and(VaccineReportSpecification.getVaccineId(Integer.parseInt(testid)))
						.and(VaccineReportSpecification.getDosageLevel(dosagelevel)));
				for(int j=0;j<vaccineReportListUpdate.size();j++){
					vaccineReportListUpdate.get(j).setVaccineReportDosageLevel(Integer.parseInt(dosage));
					if(!givenDate.equals(""))
						vaccineReportListUpdate.get(j).setVaccineReportGivenDate(Timestamp.valueOf(givenDate+" 00:00:00"));
					Date dateMod=new Date();
					Timestamp timeStampModified=new Timestamp(dateMod.getTime());
					vaccineReportListUpdate.get(j).setVaccineReportLastModified(timeStampModified);
					vaccineReportListUpdate.get(j).setVaccineReportComments(resultNotes);
					vaccineReportRepository.saveAndFlush(vaccineReportListUpdate.get(j));
				}

			}
		}else{
			if(!dosage.equals("-1")){
				List<VaccineReport> vaccineReportCount=vaccineReportRepository.findAll(Specifications.where(VaccineReportSpecification.getChartId(chartId)).and(VaccineReportSpecification.getDosageLevel(Integer.parseInt(dosage)))
						.and(VaccineReportSpecification.getVaccineId(Integer.parseInt(testid))));
				if(vaccineReportCount.size()>0){
					vaccineReportRepository.delete(vaccineReportRepository.findAll(Specifications.where(VaccineReportSpecification.getChartId(chartId)).and(VaccineReportSpecification.getDosageLevel(Integer.parseInt(dosage)))
							.and(VaccineReportSpecification.getVaccineId(Integer.parseInt(testid)))));
				}
				if(givenDate.equals("")){
					VaccineReport vaccineReportTemp=new VaccineReport();
					CriteriaBuilder builder = em.getCriteriaBuilder();
					CriteriaQuery<Object> cq = builder.createQuery();
					Root<VaccineReport> root = cq.from(VaccineReport.class);
					cq.multiselect(builder.coalesce(builder.max(root.get(VaccineReport_.vaccineReportId)),0));
					vaccineReportTemp.setVaccineReportId(Integer.parseInt(MoreObjects.firstNonNull(em.createQuery(cq).getSingleResult(),0).toString())+1);
					vaccineReportTemp.setVaccineReportChartId(chartId);
					vaccineReportTemp.setVaccineReportEncounterId(labencId);
					vaccineReportTemp.setVaccineReportVaccineId(Integer.parseInt(testid));
					Date dateGiven=new Date();
					Timestamp timeStampGiven=new Timestamp(dateGiven.getTime());
					vaccineReportTemp.setVaccineReportGivenDate(timeStampGiven);
					vaccineReportTemp.setVaccineReportDosageLevel(Integer.parseInt(dosage));
					vaccineReportTemp.setVaccineReportComments(resultNotes);
					vaccineReportTemp.setVaccineReportIsemr(1);
					vaccineReportTemp.setVaccineReportUserid(performedby);
					Date dateMod=new Date();
					Timestamp timeStampMod=new Timestamp(dateMod.getTime());
					vaccineReportTemp.setVaccineReportLastModified(timeStampMod);
					vaccineReportRepository.saveAndFlush(vaccineReportTemp);
				}
				else{
					VaccineReport vaccineReportTemp=new VaccineReport();
					CriteriaBuilder builder = em.getCriteriaBuilder();
					CriteriaQuery<Object> cq = builder.createQuery();
					Root<VaccineReport> root = cq.from(VaccineReport.class);
					cq.multiselect(builder.coalesce(builder.max(root.get(VaccineReport_.vaccineReportId)),0));
					vaccineReportTemp.setVaccineReportId(Integer.parseInt(MoreObjects.firstNonNull(em.createQuery(cq).getSingleResult(),0).toString())+1);
					vaccineReportTemp.setVaccineReportChartId(chartId);
					vaccineReportTemp.setVaccineReportEncounterId(labencId);
					vaccineReportTemp.setVaccineReportVaccineId(Integer.parseInt(testid));
					vaccineReportTemp.setVaccineReportGivenDate(Timestamp.valueOf(givenDate+" 00:00:00"));
					vaccineReportTemp.setVaccineReportDosageLevel(Integer.parseInt(dosage));
					vaccineReportTemp.setVaccineReportComments(resultNotes);
					vaccineReportTemp.setVaccineReportIsemr(1);
					vaccineReportTemp.setVaccineReportUserid(performedby);
					Date dateMod=new Date();
					Timestamp timeStampMod=new Timestamp(dateMod.getTime());
					vaccineReportTemp.setVaccineReportLastModified(timeStampMod);
					vaccineReportRepository.saveAndFlush(vaccineReportTemp);
				}
			}
			else{
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<Object> cq = builder.createQuery();
				Root<VaccineReport> root = cq.from(VaccineReport.class);
				cq.multiselect(builder.coalesce(builder.max(root.get(VaccineReport_.vaccineReportDosageLevel)),0));
				Predicate[] rest=new Predicate[]{
						builder.equal(root.get(VaccineReport_.vaccineReportChartId),chartId)
						,builder.equal(root.get(VaccineReport_.vaccineReportVaccineId),Integer.parseInt(testid))
				};
				cq.where(rest);
				dosage =  Optional.fromNullable(MoreObjects.firstNonNull(em.createQuery(cq).getSingleResult(),0).toString()).or("-1");
				int currdos =Integer.parseInt(dosage)+1;
				if(givenDate.equals("")){
					VaccineReport vaccineReportTemp=new VaccineReport();
					CriteriaBuilder builder1 = em.getCriteriaBuilder();
					CriteriaQuery<Object> cq1 = builder1.createQuery();
					Root<VaccineReport> root1 = cq1.from(VaccineReport.class);
					cq1.multiselect(builder1.coalesce(builder1.max(root1.get(VaccineReport_.vaccineReportId)),0));
					vaccineReportTemp.setVaccineReportId(Integer.parseInt(MoreObjects.firstNonNull(em.createQuery(cq1).getSingleResult(),0).toString())+1);
					vaccineReportTemp.setVaccineReportChartId(chartId);
					vaccineReportTemp.setVaccineReportEncounterId(labencId);
					vaccineReportTemp.setVaccineReportVaccineId(Integer.parseInt(testid));
					Date dateGiven=new Date();
					Timestamp timeStampGiven=new Timestamp(dateGiven.getTime());
					vaccineReportTemp.setVaccineReportGivenDate(timeStampGiven);
					vaccineReportTemp.setVaccineReportDosageLevel(currdos);
					vaccineReportTemp.setVaccineReportComments(resultNotes);
					vaccineReportTemp.setVaccineReportIsemr(1);
					vaccineReportTemp.setVaccineReportUserid(performedby);
					Date dateMod=new Date();
					Timestamp timeStampMod=new Timestamp(dateMod.getTime());
					vaccineReportTemp.setVaccineReportLastModified(timeStampMod);
					vaccineReportRepository.saveAndFlush(vaccineReportTemp);
				}
				else{

					VaccineReport vaccineReportTemp=new VaccineReport();
					CriteriaBuilder builder1 = em.getCriteriaBuilder();
					CriteriaQuery<Object> cq1 = builder1.createQuery();
					Root<VaccineReport> root1 = cq1.from(VaccineReport.class);
					cq1.multiselect(builder1.coalesce(builder1.max(root1.get(VaccineReport_.vaccineReportId)),0));
					vaccineReportTemp.setVaccineReportId(Integer.parseInt(MoreObjects.firstNonNull(em.createQuery(cq1).getSingleResult(),0).toString())+1);
					vaccineReportTemp.setVaccineReportChartId(chartId);
					vaccineReportTemp.setVaccineReportEncounterId(labencId);
					vaccineReportTemp.setVaccineReportVaccineId(Integer.parseInt(testid));
					vaccineReportTemp.setVaccineReportGivenDate(Timestamp.valueOf(givenDate+" 00:00:00"));
					vaccineReportTemp.setVaccineReportDosageLevel(Integer.parseInt(dosage));
					vaccineReportTemp.setVaccineReportComments(resultNotes);
					vaccineReportTemp.setVaccineReportIsemr(1);
					vaccineReportTemp.setVaccineReportUserid(performedby);
					Date dateMod=new Date();
					Timestamp timeStampMod=new Timestamp(dateMod.getTime());
					vaccineReportTemp.setVaccineReportLastModified(timeStampMod);
					vaccineReportRepository.saveAndFlush(vaccineReportTemp);
				}
			}
		}
	}

	public void increaseVaccineCount(String lotno)
	{
		Integer lotNoInt=-1;
		if(!lotno.trim().contentEquals(""))
			lotNoInt=Integer.parseInt(lotno);
		List<VaccineOrderDetails> vaccineReportList=vaccineOrderDetailsRepository.findAll(Specifications.where(VaccineReportSpecification.getLotNo(lotNoInt)));
		for(int j=0;j<vaccineReportList.size();j++){
			VaccineOrderDetails vaccineOrderTemp=vaccineReportList.get(j);
			vaccineOrderTemp.setVaccineOrderDetailsQtyUsed(vaccineReportList.get(j).getVaccineOrderDetailsQtyUsed()+1);
			vaccineOrderDetailsRepository.saveAndFlush(vaccineOrderTemp);
		}
	}
	public void increaseVaccineCount(String lotno,int incr)
	{
		Integer lotNoInt=-1;
		if(!lotno.trim().contentEquals(""))
			lotNoInt=Integer.parseInt(lotno);
		List<VaccineOrderDetails> vaccineReportList=vaccineOrderDetailsRepository.findAll(Specifications.where(VaccineReportSpecification.getLotNo(lotNoInt)));
		for(int j=0;j<vaccineReportList.size();j++){
			VaccineOrderDetails vaccineOrderTemp=vaccineReportList.get(j);
			vaccineOrderTemp.setVaccineOrderDetailsQtyUsed(vaccineReportList.get(j).getVaccineOrderDetailsQtyUsed()+incr);
			vaccineOrderDetailsRepository.saveAndFlush(vaccineOrderTemp);
		}
	}
	@SuppressWarnings("unused")
	public void increaseVaccineCount(String lotno,String dose) {
		String[] m1= dose.split("~");
		String unit = new String();
		int incr;
		try{
			unit = Optional.fromNullable(m1[1]).or("");
			incr = Integer.parseInt(m1[0]);
		}catch(Exception e)
		{
			incr = 1;
		}
		Integer lotNoInt=-1;
		if(!lotno.trim().contentEquals(""))
			lotNoInt=Integer.parseInt(lotno);
		List<VaccineOrderDetails> vaccineReportList=vaccineOrderDetailsRepository.findAll(Specifications.where(VaccineReportSpecification.getLotNo(lotNoInt)));
		for(int j=0;j<vaccineReportList.size();j++){
			VaccineOrderDetails vaccineOrderTemp=vaccineReportList.get(j);
			vaccineOrderTemp.setVaccineOrderDetailsQtyUsed(vaccineReportList.get(j).getVaccineOrderDetailsQtyUsed()+incr);
			vaccineOrderDetailsRepository.saveAndFlush(vaccineOrderTemp);
		}
	}

	public void decreaseVaccineCount(String lotno,String  dose)
	{
		String[] m1= dose.split("~");
		double incr;
		try{
			incr = Double.parseDouble(m1[0]);
		}catch(Exception e){
			incr = 0.0;
		}
		decreaseVaccineCount(lotno,incr);
	}

	public void decreaseVaccineCount(String lotno,double incr)
	{
	
		Integer lotNoInt=-1;
		if(!lotno.trim().contentEquals(""))
			lotNoInt=Integer.parseInt(lotno);
		List<VaccineOrderDetails> vaccineReportList=vaccineOrderDetailsRepository.findAll(Specifications.where(VaccineReportSpecification.getLotNo(lotNoInt)).and(VaccineReportSpecification.getQtyGreaterThan(0.0)));
		for(int j=0;j<vaccineReportList.size();j++){
			VaccineOrderDetails vaccineOrderTemp=vaccineReportList.get(j);
			vaccineOrderTemp.setVaccineOrderDetailsQtyUsed(vaccineReportList.get(j).getVaccineOrderDetailsQtyUsed()-incr);
			vaccineOrderDetailsRepository.saveAndFlush(vaccineOrderTemp);
		}
	}

	public void decreaseVaccineCount(String lotno)
	{
		Integer lotNoInt=-1;
		if(!lotno.trim().contentEquals(""))
			lotNoInt=Integer.parseInt(lotno);
		List<VaccineOrderDetails> vaccineReportList=vaccineOrderDetailsRepository.findAll(Specifications.where(VaccineReportSpecification.getLotNo(lotNoInt)).and(VaccineReportSpecification.getQtyGreaterThan(0.0)));
		for(VaccineOrderDetails vaccineOrder:vaccineReportList){
			VaccineOrderDetails vaccineOrderTemp=vaccineOrder;
			vaccineOrderTemp.setVaccineOrderDetailsQtyUsed(vaccineOrder.getVaccineOrderDetailsQtyUsed()-1);
			vaccineOrderDetailsRepository.saveAndFlush(vaccineOrderTemp);
		}
	}

	public void SaveParameters (int chartId, int testDetailId, String paramString) throws Exception {
		setResourceBundle();
		logger.debug("Begin of save parameter logic.");
		String[] paramSet = paramString.split("@#@");
		String[] param;
		int paramId = -1;
		int paramLen = 0;
		int sortOrder = 1;
		for(int i=0; i<paramSet.length; i++) {
			param = paramSet[i].split("\\|~\\|");// |~|
			paramLen = param.length;
			if(paramLen > paramValueMap("PARAM_ID")){
				paramId = Integer.parseInt(Optional.fromNullable(param[paramValueMap("PARAM_ID")]).or("-1").trim());
			}
			if(paramId == -1) {
				String paramName	= "";
				String paramUnits	= "";
				String NormalRange	= "";
				String paramCode	= "";
				String paramCodeSys	= "";
				if(paramLen > paramValueMap("PARAM_NAME"))
					paramName = HUtil.ValidateSingleQuote(Optional.fromNullable(param[paramValueMap("PARAM_NAME")]).or("")).trim();
				if(paramLen > paramValueMap("PARAM_UNITS"))
					paramUnits = HUtil.ValidateSingleQuote(Optional.fromNullable(param[paramValueMap("PARAM_UNITS")]).or("")).trim();
				if(paramLen > paramValueMap("PARAM_NORMALRANGE"))
					NormalRange = HUtil.ValidateSingleQuote(Optional.fromNullable(param[paramValueMap("PARAM_NORMALRANGE")]).or("")).trim();
				if(paramLen > paramValueMap("PARAM_CODE"))
					paramCode = HUtil.ValidateSingleQuote(Optional.fromNullable(param[paramValueMap("PARAM_CODE")]).or("")).trim();
				if(paramLen > paramValueMap("PARAM_CODESYSTEM"))
					paramCodeSys = HUtil.ValidateSingleQuote(Optional.fromNullable(param[paramValueMap("PARAM_CODESYSTEM")]).or("")).trim();
				paramId = insertParamDesc(paramName, paramUnits, NormalRange, paramCode, paramCodeSys);
			}
			if(paramId != -1) {
				int labEntryParamId = -1;					//lab_entries_parameter_id - to identify insert or update in lab_entries_parameter table
				String paramValue = "";
				String paramDate  = "";
				String paramStatus = "";
				String paramNotes = "";
				String paramResultStatus = "";
				String normalRange	= "";
				String paramName="";
				if(paramLen > paramValueMap("PARAM_NAME"))
					paramName = HUtil.ValidateSingleQuote(Optional.fromNullable(param[paramValueMap("PARAM_NAME")]).or("")).trim();
				if(paramLen > paramValueMap("PARAM_LABENTRYID"))
					labEntryParamId = Integer.parseInt(Optional.fromNullable(param[paramValueMap("PARAM_LABENTRYID")]).or("-1").trim());
				if(paramLen > paramValueMap("PARAM_VALUE"))
					paramValue = HUtil.ValidateSingleQuote(URLDecoder.decode(Optional.fromNullable(param[paramValueMap("PARAM_VALUE")]).or(""),"UTF-8")).trim();
				if(paramLen > paramValueMap("PARAM_DATE")){
					String dateString=HUtil.ValidateSingleQuote(Optional.fromNullable(param[paramValueMap("PARAM_DATE")]).or("")).trim();
					if(!dateString.equalsIgnoreCase("")) {
						DateFormat parser = new SimpleDateFormat("MM/dd/yyyy"); 
						Date date = (Date) parser.parse(dateString);
						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						dateString=formatter.format(date);
					}
					paramDate=dateString;

				}
				if(paramLen > paramValueMap("PARAM_STATUS"))
					paramStatus = HUtil.ValidateSingleQuote(Optional.fromNullable(param[paramValueMap("PARAM_STATUS")]).or("")).trim();
				if(paramLen > paramValueMap("PARAM_NOTES"))
					paramNotes = HUtil.ValidateSingleQuote(Optional.fromNullable(param[paramValueMap("PARAM_NOTES")]).or(""));
				if(paramLen > paramValueMap("PARAM_RESULTSTATUS"))
					paramResultStatus = HUtil.ValidateSingleQuote(Optional.fromNullable(param[paramValueMap("PARAM_RESULTSTATUS")]).or("")).trim();
				if(labEntryParamId==-1)
					DeleteParameters(testDetailId,paramId);
				if(paramLen > paramValueMap("PARAM_NORMALRANGE"))
					normalRange = HUtil.ValidateSingleQuote(Optional.fromNullable(param[paramValueMap("PARAM_NORMALRANGE")]).or("")).trim();
				String paramCode="";
				String paramCodeSystem="";
				List<LabParameterCode> labParameterCodeList=labParameterCodeRepository.findAll(Specifications.where(InvestigationSpecification.getParamId(paramId)).and(InvestigationSpecification.getCodeSystem("LOINC")));
				if(labParameterCodeList.size()>0)
				{
					paramCode=labParameterCodeList.get(0).getLabParameterCodeValue();
					paramCodeSystem="2.16.840.1.113883.6.1";
				}
				if(paramCode.equals(""))
				{
					List<LabParameterCode> labParameterCodeListSnomed=labParameterCodeRepository.findAll(Specifications.where(InvestigationSpecification.getParamId(paramId)).and(InvestigationSpecification.getCodeSystem("SNOMED")));
					if(labParameterCodeList.size()>0)
					{
						paramCode=labParameterCodeList.get(0).getLabParameterCodeValue();
						paramCodeSystem="2.16.840.1.113883.6.96";
					}
				}
				insertParamValue(chartId, labEntryParamId, testDetailId, paramId, paramValue, paramDate, paramStatus, paramNotes, paramResultStatus, sortOrder , normalRange,paramName,paramCode,paramCodeSystem);
				sortOrder++;
			}
		}
		logger.debug("End of save parameter logic.");
	}

	public void insertParamValue(int chartId, int labEntryParamId, int testDetailId, int paramId, String paramValue, String performedDate, String paramStatus, String paramNotes, String paramResultStatus, int sortOrder , String normalRange,String paramName,String paramCode,String paramCodeSystem) throws Exception {
		if(labEntryParamId == -1) {
			List<LabEntriesParameter> labEntriesParameterList=labEntriesParameterRepository.findAll(Specifications.where(InvestigationSpecification.getParamEntriesIsActive(true)).and(InvestigationSpecification.getParamEntriesTestDetailIdNot(-1)).and(InvestigationSpecification.getParamEntriesTestDetailId(testDetailId)).and(InvestigationSpecification.getParamEntriesMapId(paramId)));
			if(labEntriesParameterList.size()>0)
				labEntryParamId = Optional.fromNullable(labEntriesParameterList.get(0).getLabEntriesParameterId()).or(-1);
		}
		if(labEntryParamId != -1) {
			List<LabEntriesParameter> labEntriesParameterList=labEntriesParameterRepository.findAll(InvestigationSpecification.getParamEntriesId(labEntryParamId));
			for(int h=0;h<labEntriesParameterList.size();h++){
				labEntriesParameterList.get(h).setLabEntriesParameterValue(paramValue);
				labEntriesParameterList.get(h).setLabEntriesParameterStatus(paramStatus);
				labEntriesParameterList.get(h).setLabEntriesParameterNotes(paramNotes);
				labEntriesParameterList.get(h).setLabEntriesParameterChartid(chartId);
				if(!performedDate.equals(""))
					labEntriesParameterList.get(h).setLabEntriesParameterDate(Timestamp.valueOf(performedDate));
				labEntriesParameterList.get(h).setLabEntriesParameterResultstatus(paramResultStatus);
				labEntriesParameterList.get(h).setLabEntriesParameterSortorder(sortOrder);
				if(!normalRange.equals(""))
					labEntriesParameterList.get(h).setLabEntriesParameterNormalrange(normalRange);
				labEntriesParameterList.get(h).setLabEntriesParameterName(paramName);
				labEntriesParameterList.get(h).setLabEntriesParameterCode(paramCode);
				labEntriesParameterList.get(h).setLabEntriesParameterCodeSystem(paramCodeSystem);
				labEntriesParameterRepository.saveAndFlush(labEntriesParameterList.get(h));
			}
		} else {
			LabEntriesParameter labEntriesParameterTemp = new LabEntriesParameter();
			labEntriesParameterTemp.setLabEntriesParameterTestdetailid(testDetailId);
			labEntriesParameterTemp.setLabEntriesParameterMapid(paramId);
			labEntriesParameterTemp.setLabEntriesParameterValue(paramValue);
			labEntriesParameterTemp.setLabEntriesParameterStatus(paramStatus);
			labEntriesParameterTemp.setLabEntriesParameterNotes(paramNotes);
			labEntriesParameterTemp.setLabEntriesParameterChartid(chartId);
			if(!performedDate.equals(""))
				labEntriesParameterTemp.setLabEntriesParameterDate(Timestamp.valueOf(performedDate));
			labEntriesParameterTemp.setLabEntriesParameterResultstatus(paramResultStatus);
			labEntriesParameterTemp.setLabEntriesParameterSortorder(sortOrder);
			labEntriesParameterTemp.setLabEntriesParameterIsactive(true);
			if(!normalRange.equals(""))
				labEntriesParameterTemp.setLabEntriesParameterNormalrange(normalRange);
			labEntriesParameterTemp.setLabEntriesParameterLabcompDetailid(-2);
			labEntriesParameterTemp.setLabEntriesParameterIspdf(0);
			labEntriesParameterTemp.setLabEntriesParameterFilenameId(-2);
			labEntriesParameterTemp.setLabEntriesParameterFilenameScanid(-2);
			labEntriesParameterTemp.setLabEntriesParameterName(paramName);
			labEntriesParameterTemp.setLabEntriesParameterCode(paramCode);
			labEntriesParameterTemp.setLabEntriesParameterCodeSystem(paramCodeSystem);
			labEntriesParameterRepository.saveAndFlush(labEntriesParameterTemp);
		}
	}

	public int insertParamDesc(String paramName, String paramUnits, String NormalRange, String paramCode, String paramCodeSys) throws Exception {
		if(paramCodeSys.equals("LN")){
			paramCodeSys = "LOINC"; 
		}
		int paramId = -1;
		if( (!paramCode.equals("")) && (!paramCode.equalsIgnoreCase("null")) && (!paramCode.equalsIgnoreCase("undefined")) && (!paramCodeSys.equals("")) ){
			List<LabParameterCode> labParameterCodeList=labParameterCodeRepository.findAll(Specifications.where(InvestigationSpecification.getCode(paramCode)).and(InvestigationSpecification.getCodeSystem(paramCodeSys)));
			if( labParameterCodeList.size() > 0 ) {
				paramId = Optional.fromNullable(labParameterCodeList.get(0).getLabParameterCodeParamid()).or(-1);
			}
		}
		List<LabParameters> labParametersList=labParametersRepository.findAll(InvestigationSpecification.getParamDetails(paramId));
		int count=Optional.fromNullable(labParametersList.size()).or(0);
		if(paramId == -1 || count==0) {
			if(!paramName.equals("")) {
				if(!paramUnits.equals("")){
					List<LabParameters> labParameterList=labParametersRepository.findAll(Specifications.where(InvestigationSpecification.getParamDisplayname(paramName, true)).and(InvestigationSpecification.getParamUnits(paramUnits, true)));
					paramId = Optional.fromNullable(labParameterList.get(0).getLabParametersId()).or(-1);	
				}
				if(paramId == -1 || count==0) {					//If both paramName and LOINC Code doesn't matches, then new entry is inserted in lab_parameters table.
					LabParameters labParametersTemp=new LabParameters();
					labParametersTemp.setLabParametersName(paramName);
					labParametersTemp.setLabParametersDisplayname(paramName);
					labParametersTemp.setLabParametersUnits(paramUnits);
					labParametersTemp.setLabParametersNormalrange(NormalRange);
					labParametersTemp.setLabParametersIsactive(true);
					LabParameters labParam=labParametersRepository.saveAndFlush(labParametersTemp);
					paramId = labParam.getLabParametersId();
					insertParamCodeSystem(paramId, paramCode, paramCodeSys);
				}else {
					insertParamCodeSystem(paramId, paramCode, paramCodeSys);
					updateParamRange(paramId, NormalRange);
				}
			}
		}else {
			updateParamRange(paramId, NormalRange);
			updateParamUnits(paramId, paramUnits);
		}
		return paramId;
	}

	public void updateParamRange(int paramId, String NormalRange) throws Exception {
		if(paramId != -1 && !NormalRange.equals("")) {				//For updating normal range values
			List<LabParameters> labParametersList=labParametersRepository.findAll(InvestigationSpecification.getParamDetails(paramId));
			for(int j=0;j<labParametersList.size();j++){
				if(Optional.fromNullable(labParametersList.get(j).getLabParametersNormalrange()).or("").equals("")) {
					LabParameters labParametersTemp=labParametersList.get(j);
					labParametersTemp.setLabParametersNormalrange(NormalRange);
					labParametersRepository.saveAndFlush(labParametersTemp);
				}
			}
		}
	}

	public void updateParamUnits(int paramId, String paramUnits) throws Exception {
		if(paramId != -1 && !paramUnits.equals("")) {				//For updating normal range values
			List<LabParameters> labParametersList=labParametersRepository.findAll(InvestigationSpecification.getParamDetails(paramId));
			for(int j=0;j<labParametersList.size();j++){
				if(Optional.fromNullable(labParametersList.get(j).getLabParametersUnits()).or("").equals("")) {
					LabParameters labParametersTemp=labParametersList.get(j);
					labParametersTemp.setLabParametersUnits(paramUnits);
					labParametersRepository.saveAndFlush(labParametersTemp);
				}
			}
		}
	}

	public void insertParamCodeSystem(int paramId, String paramCode, String paramCodeSys) throws Exception {
		if(paramId != -1 && !paramCode.equals("") && !paramCodeSys.equals("")) {				//For inserting code and codesystem values
			LabParameterCode labParameterCode=new LabParameterCode();
			labParameterCode.setLabParameterCodeParamid(paramId);
			labParameterCode.setLabParameterCodeValue(paramCode);
			labParameterCode.setLabParameterCodeSystem(paramCodeSys);
			labParameterCodeRepository.saveAndFlush(labParameterCode);
		}
	}

	@Override
	public void DeleteParameters (int testDetailId) throws Exception {
		if(testDetailId != -1) {
			List<LabEntriesParameter> labEntriesParameterList=labEntriesParameterRepository.findAll(Specifications.where(InvestigationSpecification.getParamEntriesTestDetailId(testDetailId)));
			for(int j=0;j<labEntriesParameterList.size();j++){
				LabEntriesParameter labEntriesParameterTemp=labEntriesParameterList.get(j);
				labEntriesParameterTemp.setLabEntriesParameterIsactive(false);
				labEntriesParameterRepository.saveAndFlush(labEntriesParameterTemp);
			}
		}
	}

	public void DeleteParameters (int testDetailId,int paramId) throws Exception {
		if(testDetailId != -1) {
			List<LabEntriesParameter> labEntriesParameterList=labEntriesParameterRepository.findAll(Specifications.where(InvestigationSpecification.getParamEntriesTestDetailId(testDetailId)).and(InvestigationSpecification.getParamEntriesMapId(paramId)));
			for(int j=0;j<labEntriesParameterList.size();j++){
				LabEntriesParameter labEntriesParameterTemp=labEntriesParameterList.get(j);
				labEntriesParameterTemp.setLabEntriesParameterIsactive(false);
				labEntriesParameterRepository.saveAndFlush(labEntriesParameterTemp);
			}
		}
	}

	public void PreviousVisitLabLog(int labid)throws Exception{		//Labs that are ordered in previous visit and performed in this visit 
		List<LabIncludePrevious> labIncludePreviousList=labIncludePreviousRepository.findAll(Specifications.where(InvestigationSpecification.labIncludeLabId(labid)).and(InvestigationSpecification.labIncludeEncounterId(encounterId)));
		long count = labIncludePreviousList.size();
		if(count <= 0){
			LabIncludePrevious labIncludePreviousTemp=new LabIncludePrevious();
			labIncludePreviousTemp.setLabIncludePreviousLabid(labid);
			labIncludePreviousTemp.setLabIncludePreviousEncounterid(encounterId);
			labIncludePreviousRepository.saveAndFlush(labIncludePreviousTemp);
		}
	}

	public void updateLabHitCount(int labTestId)throws Exception{
		List<LabDescription> labDescriptionList=labDescriptionRepository.findAll(InvestigationSpecification.testIdsLabDescription(new Integer[]{labTestId}));
		for(int i=0;i<labDescriptionList.size();i++){
			labDescriptionList.get(i).setLabDescriptionHitcount(Optional.fromNullable(labDescriptionList.get(i).getLabDescriptionHitcount()).or(0)+1);
			labDescriptionRepository.saveAndFlush(labDescriptionList.get(i));
		}
	}

	public int paramValueMap(String xStr) throws Exception {
		return Integer.parseInt(resBun.getString(xStr));
	}

	public int dataValueMap(String xStr) throws Exception{
		String str = xStr.toLowerCase();
		String[] qstr = str.split("_col_");
		String tmpstr = qstr[0];
		return Integer.parseInt(resBun.getString(tmpstr));
	}

	public void setResourceBundle() throws Exception{
		logger.debug("Begin of setting resource bundle.");
		Locale locale   =  LocaleContextHolder.getLocale();
		locale = (locale == null)?Locale.getDefault():locale;
		resBun = ResourceBundle.getBundle("com.glenwood.glaceemr.server.application.services.investigation.Investigation",locale);
		if ( resBun == null )
			throw new Exception("Unable to load the resource");
		logger.debug("End of setting resource bundle.");
	}

	public void insert_assc_prblm (String dxcode,int entityid,int entitytype,int userid)
	{
		AssociatedOrderPblm associatedOrderPblm=new AssociatedOrderPblm();
		associatedOrderPblm.setAssociatedOrderPblmDxcode(dxcode);
		associatedOrderPblm.setAssociatedOrderPblmEntityId(entityid);
		associatedOrderPblm.setAssociatedOrderPblmEntityType(entitytype);
		associatedOrderPblm.setAssociatedOrderPblmAssocBy(userid);
		Date date=new Date();
		Timestamp timeStamp=new Timestamp(date.getTime());
		associatedOrderPblm.setAssociatedOrderPblmAssocOn(timeStamp);
		associatedOrderPblm.setAssociatedOrderPblmFlag(true);
		associatedOrderPblmRepository.saveAndFlush(associatedOrderPblm);
	}

	@Override
	public LS_Bean findPatientLabDataByChart(Integer chartId) throws Exception {
		return getCompleteLabData(chartId, null, null);
	}
	
	@Override
	public LS_Bean findPatientLabDataByCategory(Integer chartId,Integer category) throws Exception {
		return getLabDataCategory(chartId, category);
	}
	
	@Override
	public LS_Bean findPatientLabDataByTest(Integer chartId,Integer testId) throws Exception {
		return getLabDataTestId(chartId, testId);
	}

	public LS_Bean getCompleteLabData(Integer chartId, Timestamp fromDate, Timestamp toDate) {
		LS_Bean lsBean = new LS_Bean();
		List<LabEntries> labEntriesList = null;
		if( fromDate != null ) {
			  labEntriesList=getLabEntriesDataForLogsheetFromDate(chartId,fromDate,toDate);
		//	labEntriesList = labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.chartIdLog(chartId)).and(InvestigationSpecification.statusGreaterThan(2)).and(InvestigationSpecification.statusLessThan(7)).and(InvestigationSpecification.checkDate(fromDate,toDate)));
		} else {
            labEntriesList=getLabEntriesDataForLogsheet(chartId);
			//labEntriesList = labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.chartIdLog(chartId)).and(InvestigationSpecification.statusGreaterThan(2)).and(InvestigationSpecification.statusLessThan(7)));
		}

		ArrayList<Integer> testIds = new ArrayList<Integer>();

		for(int i=0;i < labEntriesList.size();i++) {

			if( !testIds.contains(labEntriesList.get(i).getLabEntriesTestId()) ) {
				testIds.add(labEntriesList.get(i).getLabEntriesTestId());
			}

			HashMap<String, String> temp = new HashMap<String, String>();
			LabDescription labDescription = labDescriptionRepository.findOne(InvestigationSpecification.labByTestId(labEntriesList.get(i).getLabEntriesTestId()));

			if(labDescription!=null)
				temp.put("testCategory", Optional.fromNullable(labDescription.getLabDescriptionTestcategoryType()).or(4)+"");
			else
				temp.put("testCategory","4");
			if(labDescription!=null){
				if(Optional.fromNullable(labDescription.getLabDescriptionTestcategoryType()).or(4)==3)
					continue;
			}			
		}
		if(labEntriesList.size() > 0) {
			lsBean.setParamData(labAndParamData(chartId, testIds));
			lsBean.setExternalLabsResult(new ArrayList<LS_External>());
//			lsBean.setExternalLabsResult(externalData(chartId));
		} else {
			GroupName groupName = new GroupName();
			groupName.setLaboratories(new ArrayList<LS_Lab>());
			groupName.setRadiology(new ArrayList<LS_Lab>());
			groupName.setMiscellaneous(new ArrayList<LS_Lab>());
			groupName.setProcedures(new ArrayList<LS_Lab>());
			lsBean.setParamData(groupName);
			lsBean.setExternalLabsResult(new ArrayList<LS_External>());
		}
		lsBean.setChartId(chartId);
		//lsBean.setPatientId(chartRepository.findOne(ChartSpecification.findByChartId(chartId)).getChartPatientid());
		lsBean.setPatientId(getChartpatientId(chartId));

		return lsBean;
	}

	private List<LabEntries> getLabEntriesDataForLogsheetFromDate(
			Integer chartId, Timestamp fromDate, Timestamp toDate) {
		List<LabEntries>values=new ArrayList<LabEntries>();

	      CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<LabEntries> cq=cb.createQuery(LabEntries.class);
		Root<LabEntries>root=cq.from(LabEntries.class);
		
		Selection[] selections=new Selection[]{
				root.get(LabEntries_.labEntriesTestId),
				root.get(LabEntries_.labEntriesTestDesc),
				
		};
		cq.select(cb.construct(LabEntries.class,selections));
		Predicate[] predications=new Predicate[]{
	        cb.equal(root.get(LabEntries_.labEntriesChartid),chartId),
		cb.greaterThan(root.get(LabEntries_.labEntriesTestStatus),2),
		cb.lessThan(root.get(LabEntries_.labEntriesTestStatus),7),
		cb.greaterThan(root.get(LabEntries_.labEntriesPerfOn), fromDate),
		cb.lessThan(root.get(LabEntries_.labEntriesPerfOn), toDate),
		};
		cq.distinct(true);
		cq.where (predications);
		


		try{
			values= em.createQuery(cq).getResultList();	

		}catch(Exception e){
			e.printStackTrace();
		}	
	      return values;
		}


	private Integer getChartpatientId(Integer chartId) {
		// TODO Auto-generated method stub
		//return null;
		Integer patId = -1;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Chart> root = cq.from(Chart.class);
		cq.select(root.get(Chart_.chartPatientid));
		cq.where(builder.equal(root.get(Chart_.chartId), chartId));
		try{
			patId = (Integer)em.createQuery(cq).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			patId=-1;
		}
		return patId;

	}

	private List<LabEntries> getLabEntriesDataForLogsheet(Integer chartId) {
		// TODO Auto-generated method stub
		//return null;
		List<LabEntries>values=new ArrayList<LabEntries>();

      CriteriaBuilder cb=em.getCriteriaBuilder();
	CriteriaQuery<LabEntries> cq=cb.createQuery(LabEntries.class);
	Root<LabEntries>root=cq.from(LabEntries.class);
	
	Selection[] selections=new Selection[]{
			root.get(LabEntries_.labEntriesTestId),
			root.get(LabEntries_.labEntriesTestDesc),
			
	};
	cq.select(cb.construct(LabEntries.class,selections));
	Predicate[] predications=new Predicate[]{
        cb.equal(root.get(LabEntries_.labEntriesChartid),chartId),
	cb.greaterThan(root.get(LabEntries_.labEntriesTestStatus),2),
	cb.lessThan(root.get(LabEntries_.labEntriesTestStatus),7),
	};
	cq.distinct(true);
	cq.where (predications);
	


	try{
		values= em.createQuery(cq).getResultList();	

	}catch(Exception e){
		e.printStackTrace();
	}	
      return values;
	}

	public LS_Bean getLabDataTestId(Integer chartId,Integer testId){
		LS_Bean lsBean=new LS_Bean();
		lsBean.setParamData(labAndParamData(chartId,new ArrayList<Integer>(Arrays.asList(testId))));
		lsBean.setChartId(chartId);
		//lsBean.setPatientId(chartRepository.findOne(ChartSpecification.findByChartId(chartId)).getChartPatientid());
		lsBean.setPatientId(getChartpatientId(chartId));
		return lsBean;
	}

	public LS_Bean getLabDataCategory(Integer chartId,Integer category){
		LS_Bean lsBean=new LS_Bean();
		List<LabDescription> labDescription=labDescriptionRepository.findAll(InvestigationSpecification.labByCategoryId(category));
		ArrayList<Integer> testIdsDescription=new ArrayList<Integer>();
		for(int i=0;i<labDescription.size();i++){
			testIdsDescription.add(labDescription.get(i).getLabDescriptionTestid());
		}
		List<LabEntries> labEntriesList=labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.chartIdLog(chartId)).and(InvestigationSpecification.statusGreaterThan(2)).and(InvestigationSpecification.statusLessThan(7)).and(InvestigationSpecification.testIds(testIdsDescription)));
		ArrayList<Integer> testIdsEntries=new ArrayList<Integer>();
		for(int i=0;i<labEntriesList.size();i++){
			testIdsEntries.add(labEntriesList.get(i).getLabEntriesTestId());
		}
		if(testIdsEntries.size() > 0 ) {
			lsBean.setParamData(labAndParamData(chartId, testIdsEntries));
		} else {
			GroupName groupName = new GroupName();
			groupName.setLaboratories(new ArrayList<LS_Lab>());
			groupName.setRadiology(new ArrayList<LS_Lab>());
			groupName.setMiscellaneous(new ArrayList<LS_Lab>());
			groupName.setProcedures(new ArrayList<LS_Lab>());
			lsBean.setParamData(groupName);
		}
		lsBean.setChartId(chartId);
		//lsBean.setPatientId(chartRepository.findOne(ChartSpecification.findByChartId(chartId)).getChartPatientid());
		lsBean.setPatientId(getChartpatientId(chartId));
		return lsBean;
	}
	
	public GroupName labAndParamData(Integer chartId,ArrayList<Integer> testIds){

		GroupName labs = new GroupName();
		List<LS_Lab> radiology = new ArrayList<LS_Lab>();
		List<LS_Lab> laboratories = new ArrayList<LS_Lab>();
		List<LS_Lab> procedures = new ArrayList<LS_Lab>();
		List<LS_Lab> miscellaneous = new ArrayList<LS_Lab>();		
		for (int i = 0; i < testIds.size(); i++) {
			LS_Lab labData = new LS_Lab();
			Integer testId = -1;
			String testCategory = "4", labName = "";
                List<LabEntries>paramData=getParamlogsheet(chartId,testIds.get(i));
		//	List<LabEntries> paramData = labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.chartIdLog(chartId)).and(InvestigationSpecification.statusGreaterThan(2)).and(InvestigationSpecification.statusLessThan(7)).and(InvestigationSpecification.getTestLog(testIds.get(i))));

			List<ParamData> labParamDetails = new ArrayList<ParamData>();
			for (int j = 0; j < paramData.size(); j++) {				
				LabEntries paramDetails = paramData.get(j);
				ParamData labParam = new ParamData();
				testId = paramDetails.getLabEntriesTestId();
				LabDescription labDescription = labDescriptionRepository.findOne(InvestigationSpecification.labByTestId(paramDetails.getLabEntriesTestId()));
				if(labDescription != null) {
					testCategory = Optional.fromNullable(labDescription.getLabDescriptionTestcategoryType()).or(4) + "";
				} else {
					testCategory = "4";
				}
				labName = paramDetails.getLabEntriesTestDesc();
				labParam.setConfirmTestStatus(paramDetails.getLabEntriesConfirmTestStatus());
				labParam.setPrelimStatus(paramDetails.getLabEntriesPrelimTestStatus());
				labParam.setResultStatus(paramDetails.getLabEntriesStatus());
				labParam.setDrugxml(paramDetails.getLabEntriesDrugxml());
				labParam.setLabStatus(paramDetails.getLabEntriesTestStatus());
				DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
				labParam.setOrderedDate(paramDetails.getLabEntriesReminderComments());
				if( paramDetails.getLabEntriesScangroupId() != null )
					labParam.setPerformedDate(paramDetails.getLabEntriesScangroupId());
				else
					labParam.setPerformedDate("");
				/*try
				{
					System.out.println("drugxml in if "+paramDetails.getLabEntriesReminderComments());
				labParam.setOrderedDate(formatter.format(formatter.parse(paramDetails.getLabEntriesReminderComments())));
				System.out.println("drugxml in if "+formatter.format(formatter.parse(paramDetails.getLabEntriesReminderComments())));
				if( paramDetails.getLabEntriesScangroupId() != null )
					labParam.setPerformedDate(formatter.format(formatter.parse(paramDetails.getLabEntriesScangroupId())));
				else
					labParam.setPerformedDate("");
				}
				catch(Exception e){
					
				}*/
				labParam.setEncounterId(paramDetails.getLabEntriesEncounterId());
				labParam.setTestDetailId(paramDetails.getLabEntriesTestdetailId());
				labParam.setResultNotes(paramDetails.getLabEntriesResultNotes());
				int testdetailId = Integer.parseInt(HUtil.Nz(labParam.getTestDetailId(),"-1001"));
				List<Object> paramList = getParamMapIds(testdetailId);
				if( paramList.size() > 0 ) {
					List<LS_Parameter> rstList = new ArrayList<LS_Parameter>();
					for(int k = 0 ; k < paramList.size() ; k++) {
						LS_Parameter lsParameter = paramDataComplete((Integer)paramList.get(k), chartId);
						if( lsParameter != null ) {
							rstList.add(lsParameter);
						}
					}

					if( testCategory.equalsIgnoreCase("2")) {
						labData.setParameters(rstList);
					} else {
						labData.setParameters(new ArrayList<LS_Parameter>());
					}
				} else {
					labData.setParameters(new ArrayList<LS_Parameter>());
				}
				this.chartId = chartId;
				this.patientId = getPatientId();

				List<Object> docId = getFileScanIds(testdetailId, this.patientId, 2);
				ArrayList<String> fileNameList = new ArrayList<String>();
				for (int l = 0; l < docId.size(); l++) {
					List<Object> docIdList = getFileName((Integer)docId.get(l)); 
					for (int k = 0; k < docIdList.size(); k++) {
						fileNameList.add("" + docIdList.get(k));
					}
				}				
				List<Object> scanId = getFileScanIds(testdetailId, this.patientId, 1);
				for (int l = 0; l < scanId.size(); l++) {
					List<Object> scanIdList = getFileName((Integer)scanId.get(l)); 
					for (int k = 0; k < scanIdList.size(); k++) {
						fileNameList.add("" + scanIdList.get(k));
					}
				}
				labParam.setFileNameList(fileNameList);
				labParam.setFileCount(fileNameList.size());
				labParamDetails.add(labParam);
			}
			labData.setLabName(labName);
			labData.setTestId(testId);
			labData.setTestCategory(testCategory);
			labData.setLabParamDetails(labParamDetails);

			switch( labData.getTestCategory() ) {
			case "1": radiology.add(labData);
			break;
			case "2": laboratories.add(labData);
			break;
			case "4": miscellaneous.add(labData);
			break;
			case "5": procedures.add(labData);
			break;
			}
		}
		labs.setRadiology(radiology);
		labs.setLaboratories(laboratories);
		labs.setMiscellaneous(miscellaneous);
		labs.setProcedures(procedures);
		return labs;
	}
	
	private List<LabEntries> getParamlogsheet(Integer chartId, Integer testId) {
		// TODO Auto-generated method stub
		//return null;
		
		List<LabEntries>values=new ArrayList<LabEntries>();

	      CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<LabEntries> cq=cb.createQuery(LabEntries.class);
		Root<LabEntries>root=cq.from(LabEntries.class);
		
		Selection[] selections=new Selection[]{
				root.get(LabEntries_.labEntriesTestId),
				root.get(LabEntries_.labEntriesTestDesc),
				root.get(LabEntries_.labEntriesConfirmTestStatus),
				root.get(LabEntries_.labEntriesPrelimTestStatus),
				root.get(LabEntries_.labEntriesStatus),
				root.get(LabEntries_.labEntriesDrugxml),
				root.get(LabEntries_.labEntriesTestStatus),
				//root.get(LabEntries_.labEntriesOrdOn),
			    cb.function("to_char", String.class, root.get(LabEntries_.labEntriesOrdOn),cb.literal("MM/dd/yyyy hh:mm:ss")),
			    cb.function("to_char", String.class, root.get(LabEntries_.labEntriesPerfOn),cb.literal("MM/dd/yyyy hh:mm:ss")),

				//root.get(LabEntries_.labEntriesPerfOn),
				root.get(LabEntries_.labEntriesEncounterId),
				root.get(LabEntries_.labEntriesTestdetailId),
				root.get(LabEntries_.labEntriesResultNotes),
				
		};
		cq.select(cb.construct(LabEntries.class,selections));
		Predicate[] predications=new Predicate[]{
	        cb.equal(root.get(LabEntries_.labEntriesChartid),chartId),
		cb.greaterThan(root.get(LabEntries_.labEntriesTestStatus),2),
		cb.lessThan(root.get(LabEntries_.labEntriesTestStatus),7),
		cb.equal(root.get(LabEntries_.labEntriesTestId),testId),
		};
		cq.where (predications);
		


		try{
			values= em.createQuery(cq).getResultList();	

		}catch(Exception e){
			e.printStackTrace();
		}	
	      return values;
		}

	

	private List<Object> getFileName(Integer scanId) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<FileName> root = cq.from(FileName.class);
		cq.select(root.get(FileName_.filenameName));
		cq.where(builder.and(builder.equal(root.get(FileName_.filenameScanid), scanId)));
		return em.createQuery(cq).getResultList();
	}

	private List<Object> getFileScanIds(int testdetailId, int patientId2, int scanType) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<FileDetails> root = cq.from(FileDetails.class);
		cq.select(root.get(FileDetails_.filedetailsId));
		cq.where(builder.and(builder.equal(root.get(FileDetails_.filedetailsEntityid), testdetailId)),
				builder.equal(root.get(FileDetails_.filedetailsScantype), scanType),
				builder.equal(root.get(FileDetails_.filedetailsPatientid), patientId2));
		return em.createQuery(cq).getResultList();
	}

	private List<Object> getParamMapIds(int testdetilid) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LabEntriesParameter> root = cq.from(LabEntriesParameter.class);
		cq.select(root.get(LabEntriesParameter_.labEntriesParameterMapid));
		cq.where(builder.equal(root.get(LabEntriesParameter_.labEntriesParameterTestdetailid), testdetilid));
		cq.distinct(true);
		return em.createQuery(cq).getResultList();
	}

	@SuppressWarnings("unused")
	public List<LS_External> externalData(Integer chartId){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<LS_External> cq = builder.createQuery(LS_External.class);
		Root<LabEntries> root = cq.from(LabEntries.class);
		Join<LabEntries,Hl7Unmappedresults> params=root.join("labData",JoinType.INNER);
		Join<Join<LabEntries,Hl7Unmappedresults>,PatientRegistration> params1=params.join("hl7UnmappedresultsPatTable",JoinType.INNER);
		Join<Join<LabEntries,Hl7Unmappedresults>,Hl7ResultInbox> params2=params.join("hl7ResultInbox",JoinType.INNER);
		Join<LabEntries,Chart> params3 = root.join("chart",JoinType.INNER);
		@SuppressWarnings("rawtypes")
		Selection[] selections=new Selection[]{
			root.get(LabEntries_.labEntriesTestdetailId),
			root.get(LabEntries_.labEntriesTestDesc),
			root.get(LabEntries_.labEntriesTestId),
			params.get(Hl7Unmappedresults_.hl7UnmappedresultsFilewiseId),
			params2.get(Hl7ResultInbox_.hl7ResultInboxReviewed),
		};
		cq.select(builder.construct(LS_External.class,selections));
		Predicate[] restrictions = new Predicate[] {
				builder.equal(root.get(LabEntries_.labEntriesChartid),chartId),
				builder.equal(params.get(Hl7Unmappedresults_.hl7UnmappedresultsIsactive),true),
				builder.equal(params2.get(Hl7ResultInbox_.hl7ResultInboxIsactive),true),
		};
		cq.where(restrictions);
		List<LS_External> rstList=new ArrayList<LS_External>();
		try{
			rstList=em.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return rstList;
	}
	
	public LS_Parameter paramDataComplete(Integer paramMapId, Integer chartId) {
		LS_Parameter parameterData = new LS_Parameter();
		Integer paramId = -1;
		String displayName = "";
		
		List<LabEntriesParameter> labDetailsList = labEntriesParameterRepository.findAll(InvestigationSpecification.getParamLog(paramMapId, chartId));
		List<ParamValues> paramValuesList = new ArrayList<ParamValues>();
		for (int k = 0; k < labDetailsList.size(); k++) {
			ParamValues paramValues = new ParamValues();
			LabEntriesParameter labEntriesParameter = labDetailsList.get(k);
			LabParameters labParameters = labEntriesParameter.getLabParametersTable();
			if( labParameters != null ) {
				paramValues.setUnits(labParameters.getLabParametersUnits());
				paramValues.setNormalRange(labParameters.getLabParametersNormalrange());
				paramValues.setParamType(labParameters.getLabParametersType());
				paramValues.setParamName(labParameters.getLabParametersName());
				paramId = labParameters.getLabParametersId();
				displayName = labParameters.getLabParametersDisplayname();
				paramValues.setFlowsheetUrl(labParameters.getLabParametersFlowsheeturl());
				paramValues.setIsflowsheetNeeded(labParameters.getLabParametersIsflowsheetneeded());
			}
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
			if( labEntriesParameter.getLabEntriesParameterDate() != null ) {
				paramValues.setParamDate(formatter.format(labEntriesParameter.getLabEntriesParameterDate()));
			} else {
				Timestamp date = getLabPerformedDate(labEntriesParameter.getLabEntriesParameterTestdetailid());
				if(date==null)
					date = getLabOrderedDate(labEntriesParameter.getLabEntriesParameterTestdetailid());
				paramValues.setParamDate(formatter.format(date));
			}
			paramValues.setLabEntryParamId(labEntriesParameter.getLabEntriesParameterId());
			paramValues.setLabEntryParamValue(labEntriesParameter.getLabEntriesParameterValue());
			paramValues.setLabEntryParamNotes(labEntriesParameter.getLabEntriesParameterNotes());
			paramValues.setLabEntryParamStatus(labEntriesParameter.getLabEntriesParameterStatus());
			paramValues.setLabEntryLabComp(labEntriesParameter.getLabEntriesParameterLabcompDetailid());
			paramValues.setResultStatus(labEntriesParameter.getLabEntriesParameterStatus());
			paramValuesList.add(paramValues);
		}
		if( paramValuesList.size() > 0 && paramId != -1) {

			parameterData.setDisplayName(displayName);
			parameterData.setParamId(paramId);
			parameterData.setParamValuesList(paramValuesList);
		}
		if( parameterData.getParamId() != null || parameterData.getDisplayName() != null && parameterData.getParamValuesList() != null )	{
			return parameterData;
		} else {
			return null;
		}
	}

	private Timestamp getLabOrderedDate(Integer testdetailid) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LabEntries> root = cq.from(LabEntries.class);
		cq.select(root.get(LabEntries_.labEntriesOrdOn));
		cq.where(builder.and(builder.equal(root.get(LabEntries_.labEntriesTestdetailId), testdetailid)),
				builder.greaterThan(root.get(LabEntries_.labEntriesTestStatus), 2),
				builder.lessThan(root.get(LabEntries_.labEntriesTestStatus), 7));
		return (Timestamp) em.createQuery(cq).getSingleResult();
	}

	private Timestamp getLabPerformedDate(Integer testdetailid) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<LabEntries> root = cq.from(LabEntries.class);
		cq.select(root.get(LabEntries_.labEntriesPerfOn));
		cq.where(builder.and(builder.equal(root.get(LabEntries_.labEntriesTestdetailId), testdetailid)),
				builder.greaterThan(root.get(LabEntries_.labEntriesTestStatus), 2),
				builder.lessThan(root.get(LabEntries_.labEntriesTestStatus), 7));
		return (Timestamp) em.createQuery(cq).getSingleResult(); 
	}

	public ArrayList<LabEntries> orderByName(ArrayList<LabEntries> labData){
		if (labData.size() > 0) {
			Collections.sort(labData, new Comparator<LabEntries>() {
				@Override
				public int compare(final LabEntries object1, final LabEntries object2) {
					return object1.getLabEntriesTestDesc().toString().compareToIgnoreCase(object2.getLabEntriesTestDesc().toString());
				}
			} );
		}
		return labData;
	}

	public List<LS_Parameter> orderByDisplayName(List<LS_Parameter> labData){
		if (labData.size() > 0) {
			Collections.sort(labData, new Comparator<LS_Parameter>() {
				@Override
				public int compare(final LS_Parameter object1, final LS_Parameter object2) {
					return object1.getDisplayName().toString().compareToIgnoreCase(object2.getDisplayName().toString());
				}
			} );
		}
		return labData;
	}

	public ArrayList<String> sorttoascending(ArrayList<String> vitalparamdate){
		ArrayList<String> returnvitalparamdate = new ArrayList<String>(); 
		ArrayList<LogsheetDateBean> logsheetDateBean = new ArrayList<LogsheetDateBean>();
		for(String vitaldate : vitalparamdate){
			String vitaldatepairs[] = vitaldate.replace("-", "/").split("/");
			logsheetDateBean.add(new LogsheetDateBean(Integer.parseInt(vitaldatepairs[2]),Integer.parseInt(vitaldatepairs[0]),Integer.parseInt(vitaldatepairs[1])));
		}
		DateComparator comparator = new DateComparator(); 
		Collections.sort(logsheetDateBean,comparator);

		for(LogsheetDateBean logsheetDateBeaninst : logsheetDateBean){
			String temp="";
			if(logsheetDateBeaninst.getMonth()<10)
				temp= "0"+logsheetDateBeaninst.getMonth()+"/";
			else
				temp= logsheetDateBeaninst.getMonth()+"/";
			if(logsheetDateBeaninst.getDay()<10)
				temp= temp + "0" +logsheetDateBeaninst.getDay()+"/";
			else
				temp= temp + logsheetDateBeaninst.getDay()+"/";
			temp= temp + logsheetDateBeaninst.getYear();
			returnvitalparamdate.add(temp);
		}
		return returnvitalparamdate;
	}

	@Override
	public LS_Bean getResultsByDate(Integer chartId, String fromDate, String toDate) throws Exception {
		DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if( fromDate != null && !fromDate.equals("")) {
			Date date1 = (Date) parser.parse(fromDate);
			fromDate = formatter.format(date1);
			fromDate = fromDate + ".000000000";
		}
		if( toDate != null && ! toDate.equals("")) {
			Date date2 = (Date) parser.parse(toDate);
			toDate = formatter.format(date2);
		}
		if( toDate != null && !toDate.equals("")) {
			return getCompleteLabData(chartId, Timestamp.valueOf(fromDate), Timestamp.valueOf(toDate));
		} else {
			Date date = new Date();
			return getCompleteLabData(chartId, Timestamp.valueOf(fromDate), new Timestamp(date.getTime()));
		}
	}

	/**
	 * Method to find summary of all orders
	 */
	@Override
	public OrderLogGroups findOrdersSummary(Integer chartId) {
		List<LabEntries> labsList = labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.chartIdLog(chartId)).and(InvestigationSpecification.checkDeleted()));
		return setOrdersData(labsList);
	}
	
	/**
	 * Method to find summary of all orders
	 */
	@Override
	public OrderLogGroups findPendingSummary(Integer chartId) {
		List<LabEntries> labsList = labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.chartIdLog(chartId)).and(InvestigationSpecification.checkDeleted()).and(InvestigationSpecification.checkOrderedStatus()));
		return setOrdersData(labsList);
	}
	
	/**
	 * Method to find summary of all orders
	 */
	@Override
	public OrderLogGroups findReviewedSummary(Integer chartId) {		
		List<LabEntries> labsList = labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.chartIdLog(chartId)).and(InvestigationSpecification.checkDeleted()).and(InvestigationSpecification.checkReviewedStatus()));			
		return setOrdersData(labsList);
	}
	
	/**
	 * Method to find summary of all orders by OrderDate
	 */
	@Override
	public List<OrderLog> findOrderByDateSummary(Integer chartId) {		
		List<LabEntries> labsList = labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.chartIdLogOrderBy(chartId)).and(InvestigationSpecification.checkDeleted()));
		return setOrdersDataByOrderDate(labsList);
	}
	private List<OrderLog> setOrdersDataByOrderDate(List<LabEntries> labsList) {
		//List<LabEntries> SortedLabData = new ArrayList<LabEntries>();
		List<OrderLog> logDatasByOrderDate = new ArrayList<OrderLog>();
		String testCategory = "4";
		Integer testId = -1;
		for (int i = 0; i < labsList.size(); i++) {
			OrderLog orderLog = new OrderLog();
			LabEntries labs = labsList.get(i);
			testId = labs.getLabEntriesTestId();
			LabDescription labDescription = labDescriptionRepository.findOne(InvestigationSpecification.labByTestId(labs.getLabEntriesTestId()));
			if(labDescription != null) {
				testCategory = Optional.fromNullable(labDescription.getLabDescriptionTestcategoryType()).or(4) + "";
			} else {
				testCategory = "4";
			}
			orderLog.setConfirmStatus("" + labs.getLabEntriesConfirmTestStatus());
			orderLog.setEncounterId("" + labs.getLabEntriesEncounterId());
			orderLog.setLabName(labs.getLabEntriesTestDesc());
			orderLog.setTestGroupId("" + labs.getLabEntriesGroupid());
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			orderLog.setOrderedDate(formatter.format(labs.getLabEntriesOrdOn()));
			if( labs.getLabEntriesPerfOn() != null ) {
				orderLog.setPerformedDate(formatter.format(labs.getLabEntriesPerfOn()));
			} else {
				orderLog.setPerformedDate("-");
			}
			orderLog.setPrelimStatus("" + labs.getLabEntriesPrelimTestStatus());
			orderLog.setResultStatus("" + labs.getLabEntriesStatus());
			orderLog.setTestCategory(testCategory);
			orderLog.setTestDetailId("" + labs.getLabEntriesTestdetailId());
			orderLog.setTestId("" + testId);
			orderLog.setTestStatus("" + labs.getLabEntriesTestStatus());
			logDatasByOrderDate.add(orderLog);
		}						
		return  logDatasByOrderDate;
	}
	
	private OrderLogGroups setOrdersData(List<LabEntries> labsList) {
		OrderLogGroups logGroups = new OrderLogGroups();
		List<OrderLog> radiology = new ArrayList<OrderLog>();
		List<OrderLog> laboratories = new ArrayList<OrderLog>();
		List<OrderLog> vaccines = new ArrayList<OrderLog>();
		List<OrderLog> procedures = new ArrayList<OrderLog>();
		List<OrderLog> miscellaneous = new ArrayList<OrderLog>();
		String testCategory = "4";
		Integer testId = -1;
		logGroups.setCount(labsList.size());
		for (int i = 0; i < labsList.size(); i++) {
			OrderLog orderLog = new OrderLog();
			LabEntries labs = labsList.get(i);
			testId = labs.getLabEntriesTestId();
			LabDescription labDescription = labDescriptionRepository.findOne(InvestigationSpecification.labByTestId(labs.getLabEntriesTestId()));
			if(labDescription != null) {
				testCategory = Optional.fromNullable(labDescription.getLabDescriptionTestcategoryType()).or(4) + "";
			} else {
				testCategory = "4";
			}
			orderLog.setConfirmStatus("" + labs.getLabEntriesConfirmTestStatus());
			orderLog.setEncounterId("" + labs.getLabEntriesEncounterId());
			orderLog.setLabName(labs.getLabEntriesTestDesc());
			orderLog.setTestGroupId("" + labs.getLabEntriesGroupid());
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			orderLog.setOrderedDate(formatter.format(labs.getLabEntriesOrdOn()));
			if( labs.getLabEntriesPerfOn() != null ) {
				orderLog.setPerformedDate(formatter.format(labs.getLabEntriesPerfOn()));
			} else {
				orderLog.setPerformedDate("-");
			}
			orderLog.setPrelimStatus("" + labs.getLabEntriesPrelimTestStatus());
			orderLog.setResultStatus("" + labs.getLabEntriesStatus());
			orderLog.setTestCategory(testCategory);
			orderLog.setTestDetailId("" + labs.getLabEntriesTestdetailId());
			orderLog.setTestId("" + testId);
			orderLog.setTestStatus("" + labs.getLabEntriesTestStatus());
			switch( orderLog.getTestCategory() ) {
			case "1": radiology.add(orderLog);
			break;
			case "2": laboratories.add(orderLog);
			break;
			case "3": vaccines.add(orderLog);
			break;
			case "4": miscellaneous.add(orderLog);
			break;
			case "5": procedures.add(orderLog);
			break;
			}
		}
		logGroups.setLaboratories(laboratories);
		logGroups.setMiscellaneous(miscellaneous);
		logGroups.setVaccinations(vaccines);
		logGroups.setProcedures(procedures);
		logGroups.setRadiology(radiology);
		return logGroups;
	}
}