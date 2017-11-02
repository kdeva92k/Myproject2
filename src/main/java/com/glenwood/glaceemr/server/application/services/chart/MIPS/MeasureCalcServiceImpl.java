package com.glenwood.glaceemr.server.application.services.chart.MIPS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.Bean.ClinicalDataQDM;
import com.glenwood.glaceemr.server.application.Bean.EPMeasureBean;
import com.glenwood.glaceemr.server.application.Bean.GeneratePDFDetails;
import com.glenwood.glaceemr.server.application.Bean.InvestigationQDM;
import com.glenwood.glaceemr.server.application.Bean.MIPSPatientInformation;
import com.glenwood.glaceemr.server.application.Bean.MIPSPerformanceBean;
import com.glenwood.glaceemr.server.application.Bean.MUDashboardBean;
import com.glenwood.glaceemr.server.application.Bean.MUPerformanceBean;
import com.glenwood.glaceemr.server.application.Bean.MeasureStatus;
import com.glenwood.glaceemr.server.application.Bean.ReferralQDM;
import com.glenwood.glaceemr.server.application.Bean.SharedFolderBean;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.Assessment;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.DiagnosticStudy;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.FunctionalStatus;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.Intervention;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.LabTest;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.Patient;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.PhysicalExam;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.Procedure;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.QDM;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.Request;
import com.glenwood.glaceemr.server.application.Bean.macra.ecqm.Benchmark;
import com.glenwood.glaceemr.server.application.Bean.macra.ecqm.Decile;
import com.glenwood.glaceemr.server.application.Bean.macra.ecqm.EMeasure;
import com.glenwood.glaceemr.server.application.Bean.macra.ecqm.EMeasureUtils;
import com.glenwood.glaceemr.server.application.Bean.mailer.GlaceMailer;
import com.glenwood.glaceemr.server.application.Bean.pqrs.PqrsMeasureBean;
import com.glenwood.glaceemr.server.application.Bean.pqrs.QualityMeasureBean;
import com.glenwood.glaceemr.server.application.models.Billinglookup;
import com.glenwood.glaceemr.server.application.models.Billinglookup_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.GatewayController;
import com.glenwood.glaceemr.server.application.models.GatewayController_;
import com.glenwood.glaceemr.server.application.models.InsCompany;
import com.glenwood.glaceemr.server.application.models.InsCompany_;
import com.glenwood.glaceemr.server.application.models.MacraConfiguration;
import com.glenwood.glaceemr.server.application.models.MacraConfiguration_;
import com.glenwood.glaceemr.server.application.models.MacraMeasuresRate;
import com.glenwood.glaceemr.server.application.models.MacraMeasuresRate_;
import com.glenwood.glaceemr.server.application.models.MacraProviderConfiguration;
import com.glenwood.glaceemr.server.application.models.MacraProviderConfiguration_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PqrsPatientEntries;
import com.glenwood.glaceemr.server.application.models.PqrsPatientEntries_;
import com.glenwood.glaceemr.server.application.models.QualityMeasuresPatientEntries;
import com.glenwood.glaceemr.server.application.models.QualityMeasuresPatientEntriesHistory;
import com.glenwood.glaceemr.server.application.models.QualityMeasuresPatientEntries_;
import com.glenwood.glaceemr.server.application.models.QualityMeasuresProviderMapping;
import com.glenwood.glaceemr.server.application.models.QualityMeasuresProviderMapping_;
import com.glenwood.glaceemr.server.application.models.StaffPinNumberDetails;
import com.glenwood.glaceemr.server.application.models.StaffPinNumberDetails_;
import com.glenwood.glaceemr.server.application.repositories.MacraConfigurationRepository;
import com.glenwood.glaceemr.server.application.repositories.MuAttestationObjectivesRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientMeasureStatusLogRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientMeasureStatusRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.repositories.ProblemListRepository;
import com.glenwood.glaceemr.server.application.specifications.QPPPerformanceSpecification;
import com.glenwood.glaceemr.server.datasource.TennantContextHolder;

@Service
@Transactional
public class MeasureCalcServiceImpl implements MeasureCalculationService{

	@Autowired
	MacraConfigurationRepository macraConfRepo;

	@Autowired
	PatientMeasureStatusRepository patientDataRepo;

	@Autowired
	PatientMeasureStatusLogRepository patientLogRepo;
	
	@Autowired
	MuAttestationObjectivesRepository attestationRepo;

	@Autowired
	PatientRegistrationRepository patientInfoRepo;

	@Autowired
	ProblemListRepository diagnosisRepo;

	@PersistenceContext
	EntityManager em;

	@Autowired
	SharedFolderBean sharedFolderBean;

	RestTemplate restTemplate = new RestTemplate();


	@Override
	public void saveMeasureDetails(int providerId, int patientId, List<MeasureStatus> measureStatus, boolean flag) {

		MeasureStatus patientObj = new MeasureStatus();

		QualityMeasuresPatientEntriesHistory patientLogObj = new QualityMeasuresPatientEntriesHistory();

		Date d = new Date();
		Timestamp curr_time = new Timestamp(d.getTime());

		String npi = getNPIForProvider(providerId);

		String tin = getTINForProvider(providerId);

		for(int i=0;i<measureStatus.size();i++){

			patientObj = measureStatus.get(i);

			String measureId = "";

			if(flag){

				measureId = ""+patientObj.getMeasureId();
				measureId = measureId.split("-")[0];

			}else{

				measureId = patientObj.getCmsId();

			}

			patientLogObj = new QualityMeasuresPatientEntriesHistory();

			QualityMeasuresPatientEntries patientData = patientDataRepo.findOne(Specifications.where(QPPPerformanceSpecification.isPatientExisting(providerId, measureId, patientId, patientObj.getReportingYear(),patientObj.getCriteria())));

			if(patientData == null || patientData.equals(null)){

				patientData = new QualityMeasuresPatientEntries();

				patientData.setQualityMeasuresPatientEntriesPatientId(patientId);
				patientData.setQualityMeasuresPatientEntriesMeasureId(measureId);
				patientData.setQualityMeasuresPatientEntriesCriteria(patientObj.getCriteria());
				patientData.setQualityMeasuresPatientEntriesProviderId(providerId);
				patientData.setQualityMeasuresPatientEntriesReportingYear(Integer.parseInt(patientObj.getReportingYear()));
				patientData.setQualityMeasuresPatientEntriesUpdatedOn(curr_time);
				patientData.setQualityMeasuresPatientEntriesIpp(patientObj.getIpp());
				patientData.setQualityMeasuresPatientEntriesDenominator(patientObj.getDenominator());
				patientData.setQualityMeasuresPatientEntriesDenominatorExclusion(patientObj.getDenominatorExclusion());
				patientData.setQualityMeasuresPatientEntriesNumerator(patientObj.getNumerator());
				patientData.setQualityMeasuresPatientEntriesNumeratorExclusion(patientObj.getNumeratorExclusion());
				patientData.setQualityMeasuresPatientEntriesDenominatorException(patientObj.getDenominatorException());
				patientData.setQualityMeasuresPatientEntriesMeasurePopulation(patientObj.getMeasurePopulation());
				patientData.setQualityMeasuresPatientEntriesMeasurePopulationExclusion(patientObj.getMeasurePopulationExclusion());
				patientData.setQualityMeasuresPatientEntriesMeasureObservation(new Double(patientObj.getMeasureObservation()).intValue());
				patientData.setQualityMeasuresPatientEntriesNpi(npi);
				patientData.setQualityMeasuresPatientEntriesTin(tin);

			}else{

				patientData.setQualityMeasuresPatientEntriesUpdatedOn(curr_time);
				patientData.setQualityMeasuresPatientEntriesIpp(patientObj.getIpp());
				patientData.setQualityMeasuresPatientEntriesDenominator(patientObj.getDenominator());
				patientData.setQualityMeasuresPatientEntriesDenominatorExclusion(patientObj.getDenominatorExclusion());
				patientData.setQualityMeasuresPatientEntriesNumerator(patientObj.getNumerator());
				patientData.setQualityMeasuresPatientEntriesNumeratorExclusion(patientObj.getNumeratorExclusion());
				patientData.setQualityMeasuresPatientEntriesDenominatorException(patientObj.getDenominatorException());
				patientData.setQualityMeasuresPatientEntriesMeasurePopulation(patientObj.getMeasurePopulation());
				patientData.setQualityMeasuresPatientEntriesMeasurePopulationExclusion(patientObj.getMeasurePopulationExclusion());
				patientData.setQualityMeasuresPatientEntriesMeasureObservation(new Double(patientObj.getMeasureObservation()).intValue());
				patientData.setQualityMeasuresPatientEntriesNpi(npi);
				patientData.setQualityMeasuresPatientEntriesTin(tin);

			}

			patientDataRepo.saveAndFlush(patientData);

			patientLogObj.setQualityMeasuresPatientEntriesPatientId(patientId);
			patientLogObj.setQualityMeasuresPatientEntriesMeasureId(measureId);
			patientLogObj.setQualityMeasuresPatientEntriesHistoryCriteria(patientObj.getCriteria());
			patientLogObj.setQualityMeasuresPatientEntriesProviderId(providerId);
			patientLogObj.setQualityMeasuresPatientEntriesReportingYear(Integer.parseInt(patientObj.getReportingYear()));
			patientLogObj.setQualityMeasuresPatientEntriesUpdatedOn(curr_time);
			patientLogObj.setQualityMeasuresPatientEntriesIpp(patientObj.getIpp());
			patientLogObj.setQualityMeasuresPatientEntriesDenominator(patientObj.getDenominator());
			patientLogObj.setQualityMeasuresPatientEntriesDenominatorExclusion(patientObj.getDenominatorExclusion());
			patientLogObj.setQualityMeasuresPatientEntriesNumerator(patientObj.getNumerator());
			patientLogObj.setQualityMeasuresPatientEntriesNumeratorExclusion(patientObj.getNumeratorExclusion());
			patientLogObj.setQualityMeasuresPatientEntriesDenominatorException(patientObj.getDenominatorException());
			patientLogObj.setQualityMeasuresPatientEntriesMeasurePopulation(patientObj.getMeasurePopulation());
			patientLogObj.setQualityMeasuresPatientEntriesMeasurePopulationExclusion(patientObj.getMeasurePopulationExclusion());
			patientLogObj.setQualityMeasuresPatientEntriesMeasureObservation(new Double(patientObj.getMeasureObservation()).intValue());
			patientLogObj.setQualityMeasuresPatientEntriesHistoryNpi(npi);
			patientLogObj.setQualityMeasuresPatientEntriesHistoryTin(tin);

			patientLogRepo.saveAndFlush(patientLogObj);

		}

	}

	@Override
	public Request getQDMRequestObject(String accountId,Boolean considerProvider,int patientID, int providerId, HashMap<String, HashMap<String, String>> codeListForQDM, Date repStartDate, Date repEndDate) {

		Request finalReqObject = new Request();

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		try{

			ExportQDM qdmData = new ExportQDM();
			EMeasureUtils measureUtils = new EMeasureUtils();		
			Patient requestObj = new Patient();

			String startDate="12/31/2007";
			String endDate="12/31/2017";

			SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
			Date date1=format.parse(startDate);
			Date date2=format.parse(endDate);

			HashMap<String, String> codeListForCNM = measureUtils.getCodeListForCNM(codeListForQDM);
			
			ArrayList<Integer> officeVisitEncounters = new ArrayList<Integer>();

			String snomedCodesForCNM = codeListForCNM.get("SNOMED");

			String loincCodesForCNM = codeListForCNM.get("LOINC");

			requestObj = qdmData.getRequestQDM(em,patientInfoRepo, diagnosisRepo, patientID, providerId);

			requestObj.setEncounterList(qdmData.getEncounterQDM(repStartDate, repEndDate, em, considerProvider, patientID, providerId, codeListForQDM.get("Encounter"), officeVisitEncounters));
			
			List<Procedure> medicationsReviewed = qdmData.getMedicationsReviewed(em,considerProvider,providerId,patientID,repStartDate,repEndDate, officeVisitEncounters);

			requestObj.setDxList(qdmData.getPatientDiagnosisQDM(diagnosisRepo, patientID));

//			List<QDM> tobaccoDetails=qdmData.getTobaccoDetails(em, patientID);

//			requestObj.setTobaccoStatusList(tobaccoDetails);

			if(codeListForQDM.containsKey("Medication")){
				requestObj.setMedicationOrders(qdmData.getMedicationQDM(em,considerProvider,providerId,codeListForQDM.get("Medication").get("RXNORM"), patientID, 2));
				requestObj.setActiveMedicationsList(qdmData.getActiveMedications(em,considerProvider,providerId, codeListForQDM.get("Medication").get("RXNORM"), patientID, 2));
			}

			List<InvestigationQDM> investigationQDM = qdmData.getInvestigationQDM(em,considerProvider,patientID,providerId, date1, date2);

			List<ClinicalDataQDM> clinicalDataQDM =qdmData.getClinicalDataQDM(em,considerProvider,providerId,patientID,snomedCodesForCNM,loincCodesForCNM,true,date1,date2,requestObj);

			if(codeListForQDM.containsKey("Immunization")){
				requestObj.setImmunizationList(qdmData.getImmuDetails(em,considerProvider,providerId, patientID, date1, date2));
			}

			if(codeListForQDM.containsKey("Diagnostic Study")){
				String codeList = measureUtils.getCodeListByCategory(codeListForQDM, "Diagnostic Study");
				List<DiagnosticStudy> diagnosisFromCNM = measureUtils.getDiagnosisFromCNM(clinicalDataQDM,codeList);
				requestObj.setDiagnosticStudyList(measureUtils.getDiagnosticStudyQDM(investigationQDM,codeList,diagnosisFromCNM));
			}

			if(codeListForQDM.containsKey("Laboratory Test")){
				List<LabTest> labTestQDM = measureUtils.getLabTestQDMForInvestigation(investigationQDM, measureUtils.getCodeListByCategory(codeListForQDM, "Laboratory Test"));
				requestObj.setLabTestList(labTestQDM);
			}

			if(codeListForQDM.containsKey("Risk Category/Assessment")){
				List<Assessment> riskAssessment =measureUtils.getAssessmentFromCNM(clinicalDataQDM,measureUtils.getCodeListByCategory(codeListForQDM, "Risk Category/Assessment"));
				requestObj.setRiskAssessmentList(riskAssessment);
			}

			if(codeListForQDM.containsKey("Physical Exam")){
				String codeList = measureUtils.getCodeListByCategory(codeListForQDM, "Physical Exam");
				List<PhysicalExam> physicalExamFromCNM=measureUtils.getPhysicalexamFromCNM(clinicalDataQDM,codeList);
				requestObj.setPhysicalExamList(measureUtils.getPhysicalexam(investigationQDM,codeList,physicalExamFromCNM));
			}

			if(codeListForQDM.containsKey("Procedure")){
				String codeList=measureUtils.getCodeListByCategory(codeListForQDM, "Procedure");
				List<String> cptCodes=measureUtils.getCPTCodes(codeListForQDM, "Procedure");
				List<Procedure> procBasedOnCPT=qdmData.getProcBasedOnCPT(em,considerProvider, patientID, providerId, cptCodes);
				List<Procedure>  procFromCNM = measureUtils.getProcFromCNM(clinicalDataQDM,codeList);
				requestObj.setProcedureList(measureUtils.getProcedureQDM(investigationQDM,codeList,medicationsReviewed,procFromCNM,procBasedOnCPT));
			}

			if(codeListForQDM.containsKey("Functional Status")){
				String codeList=measureUtils.getCodeListByCategory(codeListForQDM, "Functional Status");
				List<FunctionalStatus> functionalStatusList=measureUtils.getFunctionalStatus(clinicalDataQDM, codeList);
				requestObj.setFunctionalStatus(functionalStatusList);
			}
			
			if(codeListForQDM.containsKey("Intervention")){

				String codeList = measureUtils.getCodeListByCategory(codeListForQDM, "Intervention");

				List<Intervention> interventionQDM = measureUtils.getInterventionQDM(investigationQDM,codeList);

				List<Intervention>  interventionFromCNM = measureUtils.getInterventionFromCNM(clinicalDataQDM,codeList);

				if(interventionFromCNM.size() > 0){
					interventionQDM.addAll(interventionFromCNM);
				}

				if(codeListForQDM.containsKey("Communication")){

					List<ReferralQDM> referralObj = qdmData.getReferrals(em,considerProvider,providerId,patientID, repStartDate, repEndDate);

					if(referralObj.size() > 0){

						interventionQDM.addAll(qdmData.setReferrals(referralObj));

						requestObj.setCommunications(qdmData.getCommunicationQDM(referralObj));

					}

				}

				requestObj.setInterventionList(interventionQDM);

			}

			finalReqObject.setPatient(requestObj);

		}catch(Exception e){

			try {

				e.printStackTrace(printWriter);

				String responseMsg = GlaceMailer.buildMailContentFormat(accountId, patientID,"Error occurred while generating QDM Object",writer.toString());

				GlaceMailer.sendFailureReport(responseMsg,accountId,GlaceMailer.Configure.MU);

			} catch (Exception e1) {

				e1.printStackTrace();

			}finally{

				printWriter.flush();
				printWriter.close();

				e.printStackTrace();

			}

		}

		return finalReqObject;
	}

	@Override
	public List<EPMeasureBean> getEPMeasuresResponseObject(String accountId,Boolean isGroup,int patientID, int providerId, Date startDate, Date endDate, int reportingYear, boolean isTrans) {

		List<EPMeasureBean> epMeasureInfo = new ArrayList<EPMeasureBean>();

		ExportQDM qdmData = new ExportQDM();

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		Map<String, MeasureStatus> measureStatusObj = new HashMap<String, MeasureStatus>();

		List<MeasureStatus> epMeasureBean = new ArrayList<MeasureStatus>();

		try{

			boolean flag = qdmData.checkIsVisited(isGroup, patientID, providerId, em, startDate, endDate);

			epMeasureInfo.add(setEPrescriptionDetails(accountId, isGroup, qdmData, patientID, providerId, startDate, endDate, measureStatusObj,isTrans));

			epMeasureInfo.add(setMedicationReconcilatonDetails(accountId, isGroup, qdmData, patientID, providerId, startDate, endDate, measureStatusObj,isTrans));

			epMeasureInfo.add(setSecureMessageInfoDetails(accountId, isGroup, qdmData, patientID, providerId, startDate, endDate, flag, measureStatusObj,isTrans));

			epMeasureInfo.add(setPatientAccessInfoForPortal(accountId, qdmData, patientID, providerId, startDate, endDate, flag, measureStatusObj,isTrans));

			epMeasureInfo.add(setPatientElectronicAccessInfo(accountId, isGroup, qdmData, patientID, providerId, startDate, endDate, flag, measureStatusObj,isTrans));

			epMeasureInfo.add(setReferralExchangeInfo(accountId, isGroup, qdmData, patientID, providerId, startDate, endDate, measureStatusObj,isTrans));

			epMeasureInfo.add(setPatientEducationMaterialDetails(accountId, isGroup, qdmData, patientID, providerId, startDate, endDate, flag, measureStatusObj,isTrans));

			for(int i=0;i<measureStatusObj.keySet().size();i++){

				String key = measureStatusObj.keySet().toArray()[i].toString();

				measureStatusObj.get(key).setReportingYear(""+reportingYear);

				epMeasureBean.add(measureStatusObj.get(key));

			}

			saveMeasureDetails(providerId, patientID, epMeasureBean, false);

		}catch(Exception e){

			try {

				e.printStackTrace(printWriter);

				String responseMsg = GlaceMailer.buildMailContentFormat(accountId, patientID,"Error occurred while generating EP Measures Object",writer.toString());

				GlaceMailer.sendFailureReport(responseMsg,accountId,GlaceMailer.Configure.MU);

			} catch (Exception e1) {

				e1.printStackTrace();

			}finally{

				printWriter.flush();
				printWriter.close();

				e.printStackTrace();

			}

		}

		return epMeasureInfo;


	}

	private EPMeasureBean setEPrescriptionDetails(String accountId,Boolean isGroup,ExportQDM qdmData, int patientID, int providerId, Date startDate, Date endDate, Map<String, MeasureStatus> measureStatus, boolean isTrans){

		EPMeasureBean epObject = new EPMeasureBean();

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		MeasureStatus measureObj = new MeasureStatus();

		try{

			String ePrescResult = qdmData.getEPrescribingDetails(isGroup, patientID, providerId, em, startDate, endDate, measureObj);

			if(!isTrans){
				epObject.setMeasureId("ACI_EP_1");
				measureObj.setCmsId("ACI_EP_1");
			}else{
				epObject.setMeasureId("ACI_TRANS_EP_1");
				measureObj.setCmsId("ACI_TRANS_EP_1");
			}

			epObject.setMeasureTitle("Electronic Prescribing");

			int doneCount  = Integer.parseInt(ePrescResult.split(" &&&& ")[0].split("/")[0].replaceAll(" ",""));
			int totalCount = Integer.parseInt(ePrescResult.split(" &&&& ")[0].split("/")[1].replaceAll(" ",""));

			if(Integer.parseInt(ePrescResult.split(" &&&& ")[2]) == -1){
				epObject.setStatus("Not Applicable");
			}else{

				if(doneCount!=totalCount && totalCount>1 && doneCount>1){
					epObject.setStatus("Show Description");
					epObject.setDescription(ePrescResult.split(" &&&& ")[0].concat(" ".concat(ePrescResult.split(" &&&& ")[1])));
					epObject.setShortDescription(ePrescResult.split(" &&&& ")[0]);
				}
				else if(doneCount!=totalCount && totalCount>0){
					epObject.setStatus("Not Completed");
					epObject.setDescription(ePrescResult.split(" &&&& ")[0].concat(" ".concat(ePrescResult.split(" &&&& ")[1])));
				
				}else if(doneCount==totalCount && totalCount>0){
					epObject.setStatus("Completed");
					epObject.setDescription(ePrescResult.split(" &&&& ")[0].concat(" ".concat(ePrescResult.split(" &&&& ")[1])));
				}

			}

		}catch(Exception e){

			try{

				e.printStackTrace(printWriter);

				String responseMsg = GlaceMailer.buildMailContentFormat(accountId, patientID,"Error occurred while getting e-prescribing details",writer.toString());

				GlaceMailer.sendFailureReport(responseMsg,accountId,GlaceMailer.Configure.MU);

			} catch (Exception e1) {

				e1.printStackTrace();

			}finally{

				printWriter.flush();
				printWriter.close();

				e.printStackTrace();

			}

		}

		if(isTrans){
			measureStatus.put("ACI_TRANS_EP_1", measureObj);
		}else{
			measureStatus.put("ACI_EP_1", measureObj);
		}

		return epObject;

	}

	private EPMeasureBean setSecureMessageInfoDetails(String accountId,Boolean isGroup,ExportQDM qdmData, int patientId, int providerId, Date startDate, Date endDate, boolean visitFlag, Map<String, MeasureStatus> measureStatus,boolean isTrans){

		EPMeasureBean epObject = new EPMeasureBean();

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		MeasureStatus measureObj = new MeasureStatus();

		try{

			if(isTrans){
				epObject.setMeasureId("ACI_TRANS_SM_1");
				measureObj.setCmsId("ACI_TRANS_SM_1");
			}else{
				epObject.setMeasureId("ACI_CCTPE_2");
				measureObj.setCmsId("ACI_CCTPE_2");
			}

			epObject.setMeasureTitle("Secure Messaging");

			if(visitFlag){

				String result = qdmData.getSecureMessagingInfo(isGroup, providerId, patientId, startDate, endDate, em, measureObj);

				if((Integer.parseInt(result.split(":")[1].replace(" ","")) >= 1)){
					epObject.setStatus("Completed");
					epObject.setDescription(result);
				}else{
					epObject.setStatus("Not Completed");
					epObject.setDescription("Total Messages sent: 0");
				}

			}else{

				epObject.setDescription("No office visits done for this patient");
				epObject.setStatus("Not Applicable");				

			}

		}catch(Exception e){

			try{

				e.printStackTrace(printWriter);

				String responseMsg = GlaceMailer.buildMailContentFormat(accountId, patientId,"Error occurred while getting messaging details",writer.toString());

				GlaceMailer.sendFailureReport(responseMsg,accountId,GlaceMailer.Configure.MU);

			} catch (Exception e1) {

				e1.printStackTrace();

			}finally{

				printWriter.flush();
				printWriter.close();

				e.printStackTrace();

			}

		}

		if(isTrans){
			measureStatus.put("ACI_TRANS_SM_1", measureObj);
		}else{
			measureStatus.put("ACI_CCTPE_2", measureObj);
		}

		return epObject;

	}

	private EPMeasureBean setPatientAccessInfoForPortal(String accountId,ExportQDM qdmData, int patientId, int providerId, Date startDate, Date endDate, boolean visitFlag, Map<String, MeasureStatus> measureStatus,boolean isTrans){

		EPMeasureBean epObject = new EPMeasureBean();

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		MeasureStatus measureObj = new MeasureStatus();

		try{

			if(isTrans){
				epObject.setMeasureId("ACI_TRANS_PEA_1");
				measureObj.setCmsId("ACI_TRANS_PEA_1");
			}else{
				epObject.setMeasureId("ACI_PEA_1");
				measureObj.setCmsId("ACI_PEA_1");
			}

			epObject.setMeasureTitle("Patient Electronic Access");

			if(!visitFlag){

				epObject.setDescription("No office visits done for this patient");
				epObject.setStatus("Not Applicable");

			}else{

				if(qdmData.getPatientPortalAccess(patientId, em)){

					String portalLastAccessed = qdmData.getPatientAccessInfoForPortal(patientId, startDate, endDate, em, measureObj);

					epObject.setStatus("Completed");

					if(portalLastAccessed.length() > 0){
						epObject.setDescription("Last Accessed Portal on: "+portalLastAccessed);
					}

				}else{

					epObject.setDescription("Patient Portal Access : Inactive");
					epObject.setStatus("Not Completed");

				}

			}

		}catch(Exception e){

			try{

				e.printStackTrace(printWriter);

				String responseMsg = GlaceMailer.buildMailContentFormat(accountId, patientId,"Error occurred while getting portal access info",writer.toString());

				GlaceMailer.sendFailureReport(responseMsg,accountId,GlaceMailer.Configure.MU);

			} catch (Exception e1) {

				e1.printStackTrace();

			}finally{

				printWriter.flush();
				printWriter.close();

				e.printStackTrace();

			}

		}

		if(isTrans){
			measureStatus.put("ACI_TRANS_PEA_1", measureObj);
		}else{
			measureStatus.put("ACI_PEA_1", measureObj);
		}

		return epObject;

	}

	private EPMeasureBean setPatientElectronicAccessInfo(String accountId,Boolean isGroup,ExportQDM qdmData, int patientId, int providerId, Date startDate, Date endDate, boolean visitFlag, Map<String, MeasureStatus> measureStatus,boolean isTrans){

		EPMeasureBean epObject = new EPMeasureBean();

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		MeasureStatus measureObj = new MeasureStatus();

		try{

			if(isTrans){
				epObject.setMeasureId("ACI_TRANS_PEA_2");
				measureObj.setCmsId("ACI_TRANS_PEA_2");
			}else{
				epObject.setMeasureId("ACI_CCTPE_1");
				measureObj.setCmsId("ACI_CCTPE_1");
			}

			epObject.setMeasureTitle("View, Download and Transmit (VDT)");

			if(!visitFlag){

				epObject.setDescription("No office visits done for this patient");
				epObject.setStatus("Not Applicable");

			}else{

				if(qdmData.getPatientPortalAccess(patientId, em)){

					String portalLastAccessed = qdmData.getPatientElectronicAccessInfo(isGroup,patientId, providerId, startDate, endDate, em, measureObj);

					if(portalLastAccessed.length() > 0){
						epObject.setStatus("Completed");
						epObject.setDescription("Last Accessed Portal on: "+portalLastAccessed);
					}else{
						epObject.setDescription("No views/downloads recorded");
						epObject.setStatus("Not Completed");
					}

				}else{

					epObject.setDescription("Patient Portal Access : Inactive");
					epObject.setStatus("Not Completed");

				}

			}

		}catch(Exception e){

			try{

				e.printStackTrace(printWriter);

				String responseMsg = GlaceMailer.buildMailContentFormat(accountId, patientId,"Error occurred while getting patient VDT info",writer.toString());

				GlaceMailer.sendFailureReport(responseMsg,accountId,GlaceMailer.Configure.MU);

			} catch (Exception e1) {

				e1.printStackTrace();

			}finally{

				printWriter.flush();
				printWriter.close();

				e.printStackTrace();

			}

		}

		if(isTrans){
			measureStatus.put("ACI_TRANS_PEA_2", measureObj);
		}else{
			measureStatus.put("ACI_CCTPE_1", measureObj);
		}

		return epObject;

	}

	private EPMeasureBean setReferralExchangeInfo(String accountId,Boolean isGroup,ExportQDM qdmData, int patientId, int providerId, Date startDate, Date endDate, Map<String, MeasureStatus> measureStatus,boolean isTrans){

		EPMeasureBean epObject = new EPMeasureBean();

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		MeasureStatus measureObj = new MeasureStatus();

		try{

			String result = qdmData.getReferralInfoExchangeByProvider(isGroup, providerId, patientId, startDate, endDate, em, measureObj);

			if(isTrans){
				epObject.setMeasureId("ACI_TRANS_HIE_1");
				measureObj.setCmsId("ACI_TRANS_HIE_1");
			}else{
				epObject.setMeasureId("ACI_HIE_1");
				measureObj.setCmsId("ACI_HIE_1");
			}

			epObject.setMeasureTitle("Health Information Exchange");

			if(result.equals("No referrals have been sent by provider")){

				epObject.setDescription("No referrals have been sent by provider");
				epObject.setStatus("Not Applicable");

			}else{

				int doneCount  = Integer.parseInt(result.split(" &&&& ")[0].split("/")[0].replaceAll(" ",""));
				int totalCount = Integer.parseInt(result.split(" &&&& ")[0].split("/")[1].replaceAll(" ",""));

				if(totalCount!=0 && doneCount==0){

					epObject.setStatus("Not Completed");
					epObject.setDescription(result.split(" &&&& ")[0].concat(" ".concat(result.split(" &&&& ")[1])));

				}else if(totalCount!=0 && doneCount!=0 && doneCount!=totalCount){

					epObject.setStatus("Show Description");
					epObject.setDescription(result.split(" &&&& ")[0].concat(" ".concat(result.split(" &&&& ")[1])));

				}else{

					epObject.setStatus("Completed");
					epObject.setDescription(result.split(" &&&& ")[0].concat(" ".concat(result.split(" &&&& ")[1])));

				}

			}

		}catch(Exception e){

			try{

				e.printStackTrace(printWriter);

				String responseMsg = GlaceMailer.buildMailContentFormat(accountId, patientId,"Error occurred while getting referral details",writer.toString());

				GlaceMailer.sendFailureReport(responseMsg,accountId,GlaceMailer.Configure.MU);

			} catch (Exception e1) {

				e1.printStackTrace();

			}finally{

				printWriter.flush();
				printWriter.close();

				e.printStackTrace();

			}

		}

		if(isTrans){
			measureStatus.put("ACI_TRANS_HIE_1", measureObj);
		}else{
			measureStatus.put("ACI_HIE_1", measureObj);
		}

		return epObject;

	}

	private EPMeasureBean setPatientEducationMaterialDetails(String accountId, Boolean isGroup, ExportQDM qdmData, int patientId, int providerId, Date startDate, Date endDate, boolean visitFlag, Map<String, MeasureStatus> measureStatus,boolean isTrans){

		EPMeasureBean epObject = new EPMeasureBean();

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		MeasureStatus measureObj = new MeasureStatus();

		try{

			if(isTrans){	
				epObject.setMeasureId("ACI_TRANS_PSE_1");
				measureObj.setCmsId("ACI_TRANS_PSE_1");
			}else{
				epObject.setMeasureId("ACI_PEA_2");
				measureObj.setCmsId("ACI_PEA_2");
			}

			epObject.setMeasureTitle("Patient-Specific Education");

			if(!visitFlag){

				epObject.setDescription("No office visits done for this patient");
				epObject.setStatus("Not Applicable");

			}else{

				String result = qdmData.getPatientEducationDetails(isGroup, providerId, patientId, startDate, endDate, em, measureObj);

				epObject.setDescription(result);

				if(result.equals("No handouts have been given by provider")){
					epObject.setStatus("Not Completed");
				}else{
					epObject.setStatus("Completed");
				}

			}

		}catch(Exception e){

			try{

				e.printStackTrace(printWriter);

				String responseMsg = GlaceMailer.buildMailContentFormat(accountId, patientId,"Error occurred while getting medication reconcilation details",writer.toString());

				GlaceMailer.sendFailureReport(responseMsg,accountId,GlaceMailer.Configure.MU);

			} catch (Exception e1) {

				e1.printStackTrace();

			}finally{

				printWriter.flush();
				printWriter.close();

				e.printStackTrace();

			}

		}

		if(isTrans){
			measureStatus.put("ACI_TRANS_PSE_1", measureObj);
		}else{
			measureStatus.put("ACI_PEA_2", measureObj);
		}

		return epObject;

	}

	private EPMeasureBean setMedicationReconcilatonDetails(String accountId,Boolean isGroup,ExportQDM qdmData, int patientId, int providerId, Date startDate, Date endDate, Map<String, MeasureStatus> measureStatus,boolean isTrans){

		EPMeasureBean epObject = new EPMeasureBean();

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		MeasureStatus measureObj = new MeasureStatus();

		try{

			String result = qdmData.getMedicationReconcilcationStatus(isGroup, providerId, patientId, startDate, endDate, em, measureObj);

			if(isTrans){
				epObject.setMeasureId("ACI_TRANS_MR_1");
				measureObj.setCmsId("ACI_TRANS_MR_1");
			}else{
				epObject.setMeasureId("ACI_HIE_3");
				measureObj.setCmsId("ACI_HIE_3");
			}

			epObject.setMeasureTitle("Medication Reconcilation");

			if(result.equals("No Encounters with Transition of Care marked")){
				epObject.setDescription("No Encounters with Transition of Care marked");
				epObject.setStatus("Not Applicable");
			}else{

				int doneCount  = Integer.parseInt(result.split(" &&&& ")[0].split("/")[0].replaceAll(" ",""));
				int totalCount = Integer.parseInt(result.split(" &&&& ")[0].split("/")[1].replaceAll(" ",""));

				if(doneCount!=totalCount && totalCount>1 && doneCount>1){
					epObject.setStatus("Show Description");
					epObject.setDescription(result.split(" &&&& ")[0].concat(" ".concat(result.split(" &&&& ")[1])));
					epObject.setShortDescription(result.split(" &&&& ")[0]);
				}
				else if(doneCount!=totalCount && totalCount>0){
					epObject.setStatus("Not Completed");
					epObject.setDescription(result.split(" &&&& ")[0].concat(" ".concat(result.split(" &&&& ")[1])));
				
				}else if(doneCount==totalCount && totalCount>0) {
					epObject.setStatus("Completed");
					epObject.setDescription(result.split(" &&&& ")[0].concat(" ".concat(result.split(" &&&& ")[1])));
				}

			}

		}catch(Exception e){

			try{

				e.printStackTrace(printWriter);

				String responseMsg = GlaceMailer.buildMailContentFormat(accountId, patientId,"Error occurred while getting referral details",writer.toString());

				GlaceMailer.sendFailureReport(responseMsg,accountId,GlaceMailer.Configure.MU);

			} catch (Exception e1) {

				e1.printStackTrace();

			}finally{

				printWriter.flush();
				printWriter.close();

				e.printStackTrace();

			}

		}

		if(isTrans){
			measureStatus.put("ACI_TRANS_MR_1", measureObj);
		}else{
			measureStatus.put("ACI_HIE_3", measureObj);
		}

		return epObject;

	}

	@Override
	public Boolean checkGroupOrIndividual(int year) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<MacraConfiguration> root = cq.from(MacraConfiguration.class);
		Predicate yearPredicate=builder.equal(root.get(MacraConfiguration_.macraConfigurationYear),year);
		cq.select(root.get(MacraConfiguration_.macraConfigurationType));
		cq.where(yearPredicate);
		List<Object> result=em.createQuery(cq).getResultList();
		if(result.size()>0)
		{	
			if(Integer.parseInt(result.get(0).toString())==0){
				return true;}
			else{
				return false;
			}
		}
		else return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<MIPSPerformanceBean> getMeasureRateReport(int providerId, String accountId, String configuredMeasures,boolean isACIReport, boolean isOrderBy){

		Calendar now = Calendar.getInstance();
		int reportingYear = now.get(Calendar.YEAR);

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<MIPSPerformanceBean> cq = builder.createQuery(MIPSPerformanceBean.class);
		Root<MacraMeasuresRate> root = cq.from(MacraMeasuresRate.class);

		Predicate byProviderId = builder.equal(root.get(MacraMeasuresRate_.macraMeasuresRateProviderId), providerId);
		Predicate byReportingYear = builder.equal(root.get(MacraMeasuresRate_.macraMeasuresRateReportingYear), reportingYear);		
		Predicate byConfiguredMeasures = root.get(MacraMeasuresRate_.macraMeasuresRateMeasureId).in(Arrays.asList(configuredMeasures.split(",")));

		cq.where(builder.and(byProviderId, byReportingYear, byConfiguredMeasures));

		Selection[] cqm_selections= new Selection[] {
				root.get(MacraMeasuresRate_.macraMeasuresRateMeasureId),
				root.get(MacraMeasuresRate_.macraMeasuresRateCriteria),
				root.get(MacraMeasuresRate_.macraMeasuresRateReportingYear),
				builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateIpp),0),
				root.get(MacraMeasuresRate_.macraMeasuresRateIpplist),
				builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateDenominator),0),
				root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorlist),
				builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorExclusion),0),
				root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorExclusionlist),
				builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateNumerator),0),
				root.get(MacraMeasuresRate_.macraMeasuresRateNumeratorlist),
				builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateNumeratorExclusion),0),
				root.get(MacraMeasuresRate_.macraMeasuresRateNumeratorExclusionlist),
				builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorException),0),
				root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorExceptionlist),
				root.get(MacraMeasuresRate_.macraMeasuresRatePerformance),
				root.get(MacraMeasuresRate_.macraMeasuresRateReporting)
		};

		Selection[] aci_selections= new Selection[] {
				root.get(MacraMeasuresRate_.macraMeasuresRateMeasureId),
				root.get(MacraMeasuresRate_.macraMeasuresRateCriteria),
				root.get(MacraMeasuresRate_.macraMeasuresRateReportingYear),
				builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateIpp),0),
				root.get(MacraMeasuresRate_.macraMeasuresRateIpplist),
				builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateDenominator),0),
				root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorlist),
				builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorExclusion),0),
				root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorExclusionlist),
				builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateNumerator),0),
				root.get(MacraMeasuresRate_.macraMeasuresRateNumeratorlist),
				builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateNumeratorExclusion),0),
				root.get(MacraMeasuresRate_.macraMeasuresRateNumeratorExclusionlist),
				builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorException),0),
				root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorExceptionlist),
				root.get(MacraMeasuresRate_.macraMeasuresRatePerformance),
				root.get(MacraMeasuresRate_.macraMeasuresRateReporting),
				builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRatePoints),0)
		};

		if(isACIReport){
			cq.multiselect(aci_selections);
		}else{
			cq.multiselect(cqm_selections);
		}

		if(isOrderBy){
			cq.orderBy(builder.desc(root.get(MacraMeasuresRate_.macraMeasuresRatePerformance)));
		}
		List<MIPSPerformanceBean> finalResult=new ArrayList<MIPSPerformanceBean>();
		List<MIPSPerformanceBean> results = em.createQuery(cq).getResultList();

		int range = results.size();
		
		/*if(isOrderBy && range > 6){
			range = 6;
		}*/

		for(int i=0;i<range;i++){

			MIPSPerformanceBean resultObject = results.get(i);

			String measureId = resultObject.getMeasureId();

			String cmsIdNTitle = "";

			if(configuredMeasures.contains("ACI_TRANS_EP_1") || configuredMeasures.contains("ACI_EP_1")){
				
				if(measureId.equals("ACI_TRANS_EP_1") || measureId.equals("ACI_EP_1")){
					cmsIdNTitle = measureId.concat("&&&Electronic Prescribing");						
				}else if(measureId.equals("ACI_TRANS_SM_1") || measureId.equals("ACI_CCTPE_2")){
					cmsIdNTitle = measureId.concat("&&&Secure Messaging");						
				}else if(measureId.equals("ACI_TRANS_PEA_1") || measureId.equals("ACI_PEA_1")){
					cmsIdNTitle = measureId.concat("&&&Patient Electronic Access");						
				}else if(measureId.equals("ACI_TRANS_PEA_2") || measureId.equals("ACI_CCTPE_1")){
					cmsIdNTitle = measureId.concat("&&&View, Download and Transmit (VDT)");						
				}else if(measureId.equals("ACI_TRANS_HIE_1") || measureId.equals("ACI_HIE_1")){
					cmsIdNTitle = measureId.concat("&&&Health Information Exchange");						
				}else if(measureId.equals("ACI_TRANS_PSE_1") || measureId.equals("ACI_PEA_2")){
					cmsIdNTitle = measureId.concat("&&&Patient-Specific Education");						
				}else if(measureId.equals("ACI_TRANS_MR_1") || measureId.equals("ACI_HIE_3")){
					cmsIdNTitle = measureId.concat("&&&Medication Reconcilation");						
				}else{
					cmsIdNTitle = getCMSIdAndTitle(measureId, accountId);
				}
				
			}else{
				cmsIdNTitle = getCMSIdAndTitle(measureId, accountId);
				addPointsAndPriorityStatus(measureId, accountId, resultObject);
			}

			resultObject.setCmsId(cmsIdNTitle.split("&&&")[0]);
			resultObject.setTitle(cmsIdNTitle.split("&&&")[1]);

		}
		
		finalResult=arrangeItForCriteria(results);
		return finalResult;

	}

	/**
	 * Function to return NPI value for provider
	 * @param providerId
	 * @return
	 */

	public String getNPIForProvider(int providerId){

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<StaffPinNumberDetails> root = cq.from(StaffPinNumberDetails.class);

		cq.select(root.get(StaffPinNumberDetails_.staff_pin_number_details_cctpin_number));
		cq.where(builder.equal(root.get(StaffPinNumberDetails_.staff_pin_number_details_profileid), providerId));

		List<Object> results = em.createQuery(cq).getResultList();

		String npi = "";

		if(results.size() > 0){
			npi = results.get(0).toString();
		}

		return npi;

	}

	/**
	 * Function to get TIN number based on selected providerId
	 * 
	 * @param providerId
	 * @param accountId
	 * @param configuredMeasures
	 * @return
	 */

	public String getTINForProvider(int providerId){

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<EmployeeProfile> root = cq.from(EmployeeProfile.class);

		cq.select(root.get(EmployeeProfile_.empProfileSsn));
		cq.where(builder.equal(root.get(EmployeeProfile_.empProfileEmpid), providerId));

		List<Object> results = em.createQuery(cq).getResultList();

		String ssn = "";

		if(results.size() > 0){
			ssn = results.get(0).toString();
		}

		return ssn;

	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<MIPSPerformanceBean> getMeasureRateReportByNPI(int providerId, String accountId, String configuredMeasures,boolean isACIReport, boolean isOrderBy){

		Calendar now = Calendar.getInstance();
		int reportingYear = now.get(Calendar.YEAR);

		String npiId = getNPIForProvider(providerId);

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<MIPSPerformanceBean> cq = builder.createQuery(MIPSPerformanceBean.class);
		Root<MacraMeasuresRate> root = cq.from(MacraMeasuresRate.class);

		Predicate byNpiId = builder.equal(root.get(MacraMeasuresRate_.macraMeasuresRateNpi), npiId);
		Predicate byReportingYear = builder.equal(root.get(MacraMeasuresRate_.macraMeasuresRateReportingYear), reportingYear);		
		Predicate byConfiguredMeasures = root.get(MacraMeasuresRate_.macraMeasuresRateMeasureId).in(Arrays.asList(configuredMeasures.split(",")));

		cq.where(builder.and(byNpiId, byReportingYear, byConfiguredMeasures));

		cq.groupBy(root.get(MacraMeasuresRate_.macraMeasuresRateMeasureId),
				root.get(MacraMeasuresRate_.macraMeasuresRateCriteria),
				root.get(MacraMeasuresRate_.macraMeasuresRateReportingYear),
				root.get(MacraMeasuresRate_.macraMeasuresRatePerformance));

		Selection[] selections= new Selection[] {

				root.get(MacraMeasuresRate_.macraMeasuresRateMeasureId),
				root.get(MacraMeasuresRate_.macraMeasuresRateCriteria),
				root.get(MacraMeasuresRate_.macraMeasuresRateReportingYear),
				builder.sum(builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateIpp),0)),
				builder.function("string_agg",String.class,root.get(MacraMeasuresRate_.macraMeasuresRateIpplist),builder.literal(",")),
				builder.sum(builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateDenominator),0)),
				builder.function("string_agg",String.class,root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorlist),builder.literal(",")),
				builder.sum(builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorExclusion),0)),
				builder.function("string_agg",String.class,root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorExclusionlist),builder.literal(",")),
				builder.sum(builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateNumerator),0)),
				builder.function("string_agg",String.class,root.get(MacraMeasuresRate_.macraMeasuresRateNumeratorlist),builder.literal(",")),
				builder.sum(builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateNumeratorExclusion),0)),
				builder.function("string_agg",String.class,root.get(MacraMeasuresRate_.macraMeasuresRateNumeratorExclusionlist),builder.literal(",")),
				builder.sum(builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorException),0)),
				builder.function("string_agg",String.class,root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorExceptionlist),builder.literal(",")),

		};

		cq.multiselect(selections);

		if(isOrderBy){
			cq.orderBy(builder.desc(root.get(MacraMeasuresRate_.macraMeasuresRatePerformance)));
		}
		
		List<MIPSPerformanceBean> results = em.createQuery(cq).getResultList();

		int range = results.size();
		
		/*if(isOrderBy && range > 6){
			range = 6;
		}*/
		
		for(int i=0;i<range;i++){

			MIPSPerformanceBean resultObject = results.get(i);

			String measureId = resultObject.getMeasureId();

			String cmsIdNTitle = "";

			if(configuredMeasures.contains("ACI_TRANS_EP_1") || configuredMeasures.contains("ACI_EP_1")){
				
				if(measureId.equals("ACI_TRANS_EP_1") || measureId.equals("ACI_EP_1")){
					cmsIdNTitle = measureId.concat("&&&Electronic Prescribing");						
				}else if(measureId.equals("ACI_TRANS_SM_1") || measureId.equals("ACI_CCTPE_2")){
					cmsIdNTitle = measureId.concat("&&&Secure Messaging");						
				}else if(measureId.equals("ACI_TRANS_PEA_1") || measureId.equals("ACI_PEA_1")){
					cmsIdNTitle = measureId.concat("&&&Patient Electronic Access");						
				}else if(measureId.equals("ACI_TRANS_PEA_2") || measureId.equals("ACI_CCTPE_1")){
					cmsIdNTitle = measureId.concat("&&&View, Download and Transmit (VDT)");						
				}else if(measureId.equals("ACI_TRANS_HIE_1") || measureId.equals("ACI_HIE_1")){
					cmsIdNTitle = measureId.concat("&&&Health Information Exchange");						
				}else if(measureId.equals("ACI_TRANS_PSE_1") || measureId.equals("ACI_PEA_2")){
					cmsIdNTitle = measureId.concat("&&&Patient-Specific Education");						
				}else if(measureId.equals("ACI_TRANS_MR_1") || measureId.equals("ACI_HIE_3")){
					cmsIdNTitle = measureId.concat("&&&Medication Reconcilation");						
				}
				
			}else{
				cmsIdNTitle = getCMSIdAndTitle(measureId, accountId);
				addPointsAndPriorityStatus(measureId, accountId, resultObject);
			}

			DecimalFormat newFormat = new DecimalFormat("#0.00");

			double performanceRate = 0;
			double reportingRate = 0;

			int score = 0, points = 0;

			long numeratorCount = resultObject.getNumeratorCount();
			long denominatorCount = resultObject.getDenominatorCount();
			long denominatorExclusionCount = resultObject.getDenominatorExclusionCount();
			long denominatorExceptionCount = resultObject.getDenominatorExceptionCount();

			try{

				if(numeratorCount != 0 || denominatorExceptionCount != 0 || denominatorExclusionCount!=0){

					reportingRate =  Double.valueOf(""+numeratorCount+denominatorExceptionCount+denominatorExclusionCount) / denominatorCount;
					reportingRate =  Double.valueOf(newFormat.format(reportingRate));

				}

				if(numeratorCount > 0){

					denominatorCount = denominatorCount - denominatorExclusionCount - denominatorExceptionCount;

					performanceRate =  Double.valueOf(""+numeratorCount) / denominatorCount;
					performanceRate =  Double.valueOf(newFormat.format(performanceRate));

				}

				if(reportingRate > 0 && reportingRate%10 != 0){
					score = (int) ((reportingRate / 10)+1);
				}else if(reportingRate > 0 && reportingRate%10 == 0){
					score = (int) (reportingRate / 10);
				}else{
					score = 0;
				}

				if((measureId.equals("ACI_HIE_1")) || (measureId.equals("ACI_PEA_1"))){
					points = score *2;
				}else{
					points = score;
				}

			}catch(Exception e){
				performanceRate = 0;
				reportingRate = 0;
			}

			resultObject.setReportingRate(reportingRate);
			resultObject.setPerformanceRate(performanceRate);
			resultObject.setCmsId(cmsIdNTitle.split("&&&")[0]);
			resultObject.setTitle(cmsIdNTitle.split("&&&")[1]);
			resultObject.setPoints(points);

		}
		List<MIPSPerformanceBean> finalResult=arrangeItForCriteria(results);
		return finalResult;

	}

	private List<MIPSPerformanceBean> arrangeItForCriteria(List<MIPSPerformanceBean> results) 
	{
		
		ArrayList<MIPSPerformanceBean> finalResult=new ArrayList<MIPSPerformanceBean>();
		ArrayList<String> a=new ArrayList<String>();
		ArrayList<String> c=new ArrayList<String>();
		ArrayList<String> b=new ArrayList<String>();
		for(int i=0;i<results.size();i++)
		{
			if(!a.contains(results.get(i).getMeasureId()))
				a.add(results.get(i).getMeasureId());
			else if(!b.contains(results.get(i).getMeasureId()))
				b.add(results.get(i).getMeasureId());
		}
		for(int j=0;j<results.size();j++)
		{
			if(b.contains(results.get(j).getMeasureId()) && !c.contains(results.get(j).getMeasureId()))
			{
				String measureId=results.get(j).getMeasureId();
				ArrayList<MIPSPerformanceBean> temp=new ArrayList<MIPSPerformanceBean>();
				for(int k=0;k<results.size();k++)
				{
					if(measureId.equals(results.get(k).getMeasureId()))
					{
						temp.add(results.get(k));
					}
				}
				ArrayList<MIPSPerformanceBean> finalTemp=new ArrayList<MIPSPerformanceBean>();
				for(int k=1;k<=temp.size();k++)
				{
					for(int l=0;l<temp.size();l++)
					{
						if(temp.get(l).getCriteria()==k)
							finalTemp.add(temp.get(l));
					}
					
				}
				finalResult.addAll(finalTemp);
				c.add(measureId);
			}
			else if(a.contains(results.get(j).getMeasureId()) && !b.contains(results.get(j).getMeasureId()))
			{
				finalResult.add(results.get(j));
			}
		}
		
		return finalResult;
	}

	/**
	 * Function to calculate performance of group of providers based on TIN value
	 * @param configuredMeasures
	 * @param accountId
	 * @return
	 * MIPSPerformanceBean to save entries in macra_measures_rate table
	 */

	@SuppressWarnings("rawtypes")
	@Override
	public List<MIPSPerformanceBean> getGroupPerformanceCount(String tinValue, String configuredMeasures, String accountId,boolean isACIReport, boolean isOrderBy){
		List<MIPSPerformanceBean> finalResult=new ArrayList<MIPSPerformanceBean>();
		List<MIPSPerformanceBean> results = new ArrayList<MIPSPerformanceBean>();

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		try{

			Calendar now = Calendar.getInstance();
			int reportingYear = now.get(Calendar.YEAR);

			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<MIPSPerformanceBean> cq = builder.createQuery(MIPSPerformanceBean.class);
			Root<MacraMeasuresRate> root = cq.from(MacraMeasuresRate.class);

			Predicate byEmpTin = builder.equal(root.get(MacraMeasuresRate_.macraMeasuresRateTin), tinValue);
			Predicate byReportingYear = builder.equal(root.get(MacraMeasuresRate_.macraMeasuresRateReportingYear), reportingYear);		
			Predicate byConfiguredMeasures = root.get(MacraMeasuresRate_.macraMeasuresRateMeasureId).in(Arrays.asList(configuredMeasures.split(",")));

			cq.where(builder.and(byEmpTin,byReportingYear, byConfiguredMeasures));

			cq.groupBy(root.get(MacraMeasuresRate_.macraMeasuresRateTin),
					root.get(MacraMeasuresRate_.macraMeasuresRateMeasureId),
					root.get(MacraMeasuresRate_.macraMeasuresRateCriteria),
					root.get(MacraMeasuresRate_.macraMeasuresRateReportingYear),
					root.get(MacraMeasuresRate_.macraMeasuresRatePerformance));

			Selection[] selections= new Selection[] {

					root.get(MacraMeasuresRate_.macraMeasuresRateTin),
					root.get(MacraMeasuresRate_.macraMeasuresRateMeasureId),
					root.get(MacraMeasuresRate_.macraMeasuresRateCriteria),
					root.get(MacraMeasuresRate_.macraMeasuresRateReportingYear),
					builder.sum(builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateIpp),0)),
					builder.function("string_agg",String.class,root.get(MacraMeasuresRate_.macraMeasuresRateIpplist),builder.literal(",")),
					builder.sum(builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateDenominator),0)),
					builder.function("string_agg",String.class,root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorlist),builder.literal(",")),
					builder.sum(builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorExclusion),0)),
					builder.function("string_agg",String.class,root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorExclusionlist),builder.literal(",")),
					builder.sum(builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateNumerator),0)),
					builder.function("string_agg",String.class,root.get(MacraMeasuresRate_.macraMeasuresRateNumeratorlist),builder.literal(",")),
					builder.sum(builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateNumeratorExclusion),0)),
					builder.function("string_agg",String.class,root.get(MacraMeasuresRate_.macraMeasuresRateNumeratorExclusionlist),builder.literal(",")),
					builder.sum(builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorException),0)),
					builder.function("string_agg",String.class,root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorExceptionlist),builder.literal(",")),

			};

			cq.multiselect(selections);

			if(isOrderBy){
				cq.orderBy(builder.desc(root.get(MacraMeasuresRate_.macraMeasuresRatePerformance)));
			}
			
			results = em.createQuery(cq).getResultList();

			int range = results.size();
			
			for(int i=0;i<range;i++){

				MIPSPerformanceBean resultObject = results.get(i);

				String measureId = resultObject.getMeasureId();

				String cmsIdNTitle = "";

				if(configuredMeasures.contains("ACI_TRANS_EP_1") || configuredMeasures.contains("ACI_EP_1")){
					
					if(measureId.equals("ACI_TRANS_EP_1") || measureId.equals("ACI_EP_1")){
						cmsIdNTitle = measureId.concat("&&&Electronic Prescribing");						
					}else if(measureId.equals("ACI_TRANS_SM_1") || measureId.equals("ACI_CCTPE_2")){
						cmsIdNTitle = measureId.concat("&&&Secure Messaging");						
					}else if(measureId.equals("ACI_TRANS_PEA_1") || measureId.equals("ACI_PEA_1")){
						cmsIdNTitle = measureId.concat("&&&Patient Electronic Access");						
					}else if(measureId.equals("ACI_TRANS_PEA_2") || measureId.equals("ACI_CCTPE_1")){
						cmsIdNTitle = measureId.concat("&&&View, Download and Transmit (VDT)");						
					}else if(measureId.equals("ACI_TRANS_HIE_1") || measureId.equals("ACI_HIE_1")){
						cmsIdNTitle = measureId.concat("&&&Health Information Exchange");						
					}else if(measureId.equals("ACI_TRANS_PSE_1") || measureId.equals("ACI_PEA_2")){
						cmsIdNTitle = measureId.concat("&&&Patient-Specific Education");						
					}else if(measureId.equals("ACI_TRANS_MR_1") || measureId.equals("ACI_HIE_3")){
						cmsIdNTitle = measureId.concat("&&&Medication Reconcilation");						
					}
					
				}else{
					cmsIdNTitle = getCMSIdAndTitle(measureId, accountId);
					addPointsAndPriorityStatus(measureId, accountId, resultObject);
				}

				DecimalFormat newFormat = new DecimalFormat("#0.00");

				double performanceRate = 0;
				double reportingRate = 0;

				int score = 0;
				int points = 0;

				long numeratorCount = resultObject.getNumeratorCount();
				long denominatorCount = resultObject.getDenominatorCount();
				long denominatorExclusionCount = resultObject.getDenominatorExclusionCount();
				long denominatorExceptionCount = resultObject.getDenominatorExceptionCount();

				try{

					if(numeratorCount != 0 || denominatorExceptionCount != 0 || denominatorExclusionCount!=0){

						reportingRate =  Double.valueOf(""+numeratorCount+denominatorExceptionCount+denominatorExclusionCount) / denominatorCount;
						reportingRate =  Double.valueOf(newFormat.format(reportingRate));

					}

					if(numeratorCount > 0){

						denominatorCount = denominatorCount - denominatorExclusionCount - denominatorExceptionCount;

						performanceRate =  Double.valueOf(""+numeratorCount) / denominatorCount;
						performanceRate =  Double.valueOf(newFormat.format(performanceRate));

					}

					if(reportingRate > 0 && reportingRate%10 != 0){
						score = (int) ((reportingRate / 10)+1);
					}else if(reportingRate > 0 && reportingRate%10 == 0){
						score = (int) (reportingRate / 10);
					}else{
						score = 0;
					}

					if((measureId.equals("ACI_HIE_1")) || (measureId.equals("ACI_PEA_1"))){
						points = score *2;
					}else{
						points = score;
					}

				}catch(Exception e){
					performanceRate = 0;
					reportingRate = 0;
				}

				resultObject.setReportingRate(reportingRate);
				resultObject.setPerformanceRate(performanceRate);
				resultObject.setCmsId(cmsIdNTitle.split("&&&")[0]);
				resultObject.setTitle(cmsIdNTitle.split("&&&")[1]);
				resultObject.setPoints(points);
				
				finalResult=arrangeItForCriteria(results);
			}

		}catch(Exception e){

			try{

				e.printStackTrace(printWriter);

				String responseMsg = GlaceMailer.buildMailContentFormat(accountId, -1,"Error occurred while calculating MIPS performance",writer.toString());

				GlaceMailer.sendFailureReport(responseMsg,accountId,GlaceMailer.Configure.MU);

			} catch (Exception e1) {

				e1.printStackTrace();

			}finally{

				printWriter.flush();
				printWriter.close();

				e.printStackTrace();

			}

		}

		return finalResult;

	}

	/**
	 * Function to calculate measure performance  
	 * 
	 * @param providerId
	 * @param measureId
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	@Override
	public List<MIPSPerformanceBean> getPerformanceCount(int providerId, String measureId, String configuredMeasures, String accountId){

		List<MIPSPerformanceBean> results = new ArrayList<MIPSPerformanceBean>();

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);

		try{

			Calendar now = Calendar.getInstance();
			int reportingYear = now.get(Calendar.YEAR);

			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<MIPSPerformanceBean> cq = builder.createQuery(MIPSPerformanceBean.class);
			Root<QualityMeasuresPatientEntries> root = cq.from(QualityMeasuresPatientEntries.class);

			Predicate byProviderId = builder.equal(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesProviderId), providerId);
			Predicate byReportingYear = builder.equal(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesReportingYear), reportingYear);		
			Predicate byConfiguredMeasures = root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesMeasureId).in(Arrays.asList(configuredMeasures.split(",")));

			if(!measureId.equals("")){
				Predicate byMeasureId = builder.equal(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesMeasureId), measureId);
				cq.where(builder.and(byProviderId, byMeasureId, byReportingYear, byConfiguredMeasures));
			}else{
				cq.where(builder.and(byProviderId, byReportingYear, byConfiguredMeasures));
			}

			cq.groupBy(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesProviderId),
					root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesMeasureId),
					root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesCriteria),
					root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesReportingYear));

			Selection[] selections= new Selection[] {
					root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesMeasureId),
					root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesCriteria),
					root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesReportingYear),
					builder.coalesce(builder.sum(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesIpp)),0),
					builder.coalesce(builder.sum(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominator)),0),
					builder.coalesce(builder.sum(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominatorExclusion)),0),
					builder.coalesce(builder.sum(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNumerator)),0),
					builder.coalesce(builder.sum(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNumeratorExclusion)),0),
					builder.coalesce(builder.sum(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominatorException)),0)
			};

			cq.multiselect(selections);

			results = em.createQuery(cq).getResultList();

			for(int i=0;i<results.size();i++){

				MIPSPerformanceBean resultObject = results.get(i);

				measureId = resultObject.getMeasureId();

				String cmsIdNTitle = "";

				if(configuredMeasures.contains("ACI_TRANS_EP_1") || configuredMeasures.contains("ACI_EP_1")){
					
					if(measureId.equals("ACI_TRANS_EP_1") || measureId.equals("ACI_EP_1")){
						cmsIdNTitle = measureId.concat("&&&Electronic Prescribing");						
					}else if(measureId.equals("ACI_TRANS_SM_1") || measureId.equals("ACI_CCTPE_2")){
						cmsIdNTitle = measureId.concat("&&&Secure Messaging");						
					}else if(measureId.equals("ACI_TRANS_PEA_1") || measureId.equals("ACI_PEA_1")){
						cmsIdNTitle = measureId.concat("&&&Patient Electronic Access");						
					}else if(measureId.equals("ACI_TRANS_PEA_2") || measureId.equals("ACI_CCTPE_1")){
						cmsIdNTitle = measureId.concat("&&&View, Download and Transmit (VDT)");						
					}else if(measureId.equals("ACI_TRANS_HIE_1") || measureId.equals("ACI_HIE_1")){
						cmsIdNTitle = measureId.concat("&&&Health Information Exchange");						
					}else if(measureId.equals("ACI_TRANS_PSE_1") || measureId.equals("ACI_PEA_2")){
						cmsIdNTitle = measureId.concat("&&&Patient-Specific Education");						
					}else if(measureId.equals("ACI_TRANS_MR_1") || measureId.equals("ACI_HIE_3")){
						cmsIdNTitle = measureId.concat("&&&Medication Reconcilation");						
					}
					
				}else{
					cmsIdNTitle = getCMSIdAndTitle(measureId, accountId);
				}

				DecimalFormat newFormat = new DecimalFormat("#0.00");

				double performanceRate = 0;
				double reportingRate = 0;

				int score = 0;
				int points = 0;

				long numeratorCount = resultObject.getNumeratorCount();
				long denominatorCount = resultObject.getDenominatorCount();
				long denominatorExclusionCount = resultObject.getDenominatorExclusionCount();
				long denominatorExceptionCount = resultObject.getDenominatorExceptionCount();
				try{

					if(numeratorCount != 0 || denominatorExceptionCount != 0 || denominatorExclusionCount!=0){

						denominatorCount = denominatorCount - denominatorExclusionCount - denominatorExceptionCount;
	
						reportingRate =  Double.valueOf(""+numeratorCount) / denominatorCount;
						reportingRate =  Double.valueOf(newFormat.format(reportingRate));
					}

					if(numeratorCount > 0){

						denominatorCount = denominatorCount - denominatorExclusionCount - denominatorExceptionCount;

						performanceRate =  Double.valueOf(""+numeratorCount) / denominatorCount;
						performanceRate =  Double.valueOf(newFormat.format(performanceRate));
					}

					if(reportingRate > 0 && reportingRate%10 != 0){
						score = (int) ((reportingRate / 10)+1);
					}else if(reportingRate > 0 && reportingRate%10 == 0){
						score = (int) (reportingRate / 10);
					}else{
						score = 0;
					}

					if((measureId.equals("ACI_HIE_1")) || (measureId.equals("ACI_PEA_1"))){
						points = score *2;
					}else{
						points = score;
					}

				}catch(Exception e){
					performanceRate = 0;
					reportingRate = 0;
				}

				resultObject.setPoints(points);
				resultObject.setPerformanceRate(performanceRate);
				resultObject.setReportingRate(reportingRate);
				resultObject.setCmsId(cmsIdNTitle.split("&&&")[0]);
				resultObject.setTitle(cmsIdNTitle.split("&&&")[1]);
				resultObject.setIppPatientsList(getPatientListByCriteria(1, providerId, measureId,resultObject.getCriteria()));
				resultObject.setDenominatorPatientsList(getPatientListByCriteria(2, providerId, measureId,resultObject.getCriteria()));
				resultObject.setDenominatorExclusionPatientsList(getPatientListByCriteria(3, providerId, measureId,resultObject.getCriteria()));
				resultObject.setDenominatorExceptionPatientsList(getPatientListByCriteria(4, providerId, measureId,resultObject.getCriteria()));
				resultObject.setNumeratorPatientsList(getPatientListByCriteria(5, providerId, measureId,resultObject.getCriteria()));
				resultObject.setNumeratorExclusionPatientsList(getPatientListByCriteria(6, providerId, measureId,resultObject.getCriteria()));

			}

		}catch(Exception e){

			try{

				e.printStackTrace(printWriter);

				String responseMsg = GlaceMailer.buildMailContentFormat(accountId, -1,"Error occurred while calculating MIPS performance",writer.toString());

				GlaceMailer.sendFailureReport(responseMsg,accountId,GlaceMailer.Configure.MU);

			} catch (Exception e1) {

				e1.printStackTrace();

			}finally{

				printWriter.flush();
				printWriter.close();

				e.printStackTrace();

			}

		}

		return results;

	}

	public void addPointsAndPriorityStatus(String measureId, String accountId,MIPSPerformanceBean resultObject)
	{
		EMeasureUtils measureUtils = new EMeasureUtils();
		try 
		{
			String sharedFolderPath = sharedFolderBean.getSharedFolderPath().get(accountId).toString();
			List<EMeasure> emeasureObj=measureUtils.getMeasureBeanDetails(measureId,sharedFolderPath,accountId);
			EMeasure eMeasure=emeasureObj.get(0);
			resultObject.setHighPriority(eMeasure.isHighPriority());
			resultObject.setOutcome(eMeasure.getType());
			resultObject.setPoints(getPointsByBenchMark(resultObject.getPerformanceRate(), eMeasure));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		

	}
	
	private String getCMSIdAndTitle(String measureId, String accountId){

		String result = "";

		try{

			String sharedFolderPath = sharedFolderBean.getSharedFolderPath().get(accountId).toString();

			String jsonFolder = sharedFolderPath+File.separator+"ECQM"+File.separator;

			File file = new File(jsonFolder+measureId+".json");

			String apiUrl = "http://hub-icd10.glaceemr.com/DataGateway/eCQMServices/getECQMInfoById?ids="+measureId;

			if(file.exists()){

				String jsonContent = FileUtils.readFileToString(file);

				JSONArray jsonArray = new JSONArray(jsonContent);

				result = jsonArray.getJSONObject(0).getString("cmsId");
				result = result.concat("&&&".concat(jsonArray.getJSONObject(0).getString("title")));

			}else{

				JSONArray measureDetailsArray=new JSONArray(restTemplate.getForObject(apiUrl, String.class));

				for(int i=0;i<measureDetailsArray.length();i++){

					result = measureDetailsArray.getJSONObject(i).getString("cmsId");
					result = result.concat("&&&".concat(measureDetailsArray.getJSONObject(i).getString("title")));

				}

			}

		}catch(Exception e){
			e.printStackTrace();
		}

		return result;

	}

	private String getPatientListByCriteria(int criteriaId, int providerId, String measureId,int criteria){

		String patientsList = "";

		Calendar now = Calendar.getInstance();
		int reportingYear = now.get(Calendar.YEAR);

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = builder.createQuery(String.class);
		Root<QualityMeasuresPatientEntries> root = cq.from(QualityMeasuresPatientEntries.class);

		Predicate condition = null;

		if(criteriaId == 1){
			condition = builder.greaterThanOrEqualTo(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesIpp), 1);
		}else if(criteriaId == 2){
			condition = builder.greaterThanOrEqualTo(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominator), 1);
		}else if(criteriaId == 3){
			condition = builder.greaterThanOrEqualTo(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominatorExclusion), 1);
		}else if(criteriaId == 4){
			condition = builder.greaterThanOrEqualTo(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominatorException), 1);
		}else if(criteriaId == 5){
			condition = builder.greaterThanOrEqualTo(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNumerator), 1);
		}else if(criteriaId == 6){
			condition = builder.greaterThanOrEqualTo(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNumeratorExclusion), 1);
		}

		Predicate byProviderId = builder.equal(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesProviderId), providerId);
		Predicate byReportingYear = builder.equal(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesReportingYear), reportingYear);		
		Predicate byCriteria=builder.equal(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesCriteria), criteria);
		if(!measureId.equals("")){
			Predicate byMeasureId = builder.equal(root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesMeasureId), measureId);
			cq.where(builder.and(byProviderId, byMeasureId, byReportingYear, condition,byCriteria));
		}else{
			cq.where(builder.and(byProviderId, byReportingYear, condition,byCriteria));
		}

		cq.select(builder.coalesce(builder.function("string_agg", String.class, root.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesPatientId).as(String.class),builder.literal(",")),""));

		patientsList = em.createQuery(cq).getResultList().get(0); 

		return patientsList;

	}

	@SuppressWarnings("unused")
	@Override
	public HashMap<String,Object> generateFilterContents(){

		HashMap<String,Object> filterDetails=new HashMap<String,Object>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Billinglookup> rootBillinglookup = cq.from(Billinglookup.class);
		cq.select(rootBillinglookup.get(Billinglookup_.blookName));
		Predicate forRace=builder.equal(rootBillinglookup.get(Billinglookup_.blookGroup), 250);
		cq.where(forRace);
		List<Object> result=em.createQuery(cq).getResultList();
		filterDetails.put("race", result);

		CriteriaBuilder builder1 = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq1 = builder1.createQuery();
		Root<Billinglookup> rootBillinglookup1 = cq1.from(Billinglookup.class);
		cq1.select(rootBillinglookup1.get(Billinglookup_.blookName));
		Predicate forEthnicity=builder1.equal(rootBillinglookup1.get(Billinglookup_.blookGroup), 251);
		cq1.where(forEthnicity);
		List<Object> result1=em.createQuery(cq1).getResultList();
		filterDetails.put("ethnicity", result1);

		CriteriaBuilder builder2 = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq2 = builder2.createQuery();
		Root<StaffPinNumberDetails> rooth478 = cq2.from(StaffPinNumberDetails.class);
		Join<StaffPinNumberDetails,MacraProviderConfiguration> joinh478MacraProviderConfiguration=rooth478.join(StaffPinNumberDetails_.macraProviderConfiguration,JoinType.INNER);
		cq2.select(rooth478.get(StaffPinNumberDetails_.staff_pin_number_details_cctpin_number));
		List<Object> result2=em.createQuery(cq2).getResultList();
		filterDetails.put("npi", result2);

		CriteriaBuilder builder3 = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq3 = builder3.createQuery();
		Root<EmployeeProfile> rootemployeeProfile = cq3.from(EmployeeProfile.class);
		Join<EmployeeProfile,MacraProviderConfiguration> joinEmployeeProfileMacraProviderConfiguration=rootemployeeProfile.join(EmployeeProfile_.macraProviderConfiguration,JoinType.INNER);
		cq3.select(rootemployeeProfile.get(EmployeeProfile_.empProfileSsn));
		Predicate byIsActive=builder3.equal(rootemployeeProfile.get(EmployeeProfile_.empProfileIsActive),true);
		cq3.where(byIsActive);
		List<Object> result3=em.createQuery(cq3).getResultList();
		filterDetails.put("ssn", result3);

		CriteriaBuilder builder4 = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq4 = builder4.createQuery();
		Root<InsCompany> rootinsCompany = cq4.from(InsCompany.class);
		cq4.multiselect(rootinsCompany.get(InsCompany_.insCompanyName),rootinsCompany.get(InsCompany_.insCompanyId));
		cq4.distinct(true);
		List<Object> result4=em.createQuery(cq4).getResultList();
		filterDetails.put("insurance", result4);

		return filterDetails;

	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<MIPSPatientInformation> getPatient(String patientId, String measureId, int criteria,Integer provider, String empTin, int mode, boolean isNotMet) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<MIPSPatientInformation> cq = builder.createQuery(MIPSPatientInformation.class);
		Root<PatientRegistration> root = cq.from(PatientRegistration.class);
		Join<PatientRegistration,QualityMeasuresPatientEntries> joinQualityMeasuresPatientEntries=root.join(PatientRegistration_.qualityMeasuresPatientEntries,JoinType.INNER);

		String npiValue = getNPIForProvider(provider);

		Predicate byPatientId = root.get(PatientRegistration_.patientRegistrationId).in(Arrays.asList(patientId.split(",")));
		Predicate byMeasureId = builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesMeasureId), measureId);
		Predicate byCriteria = builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesCriteria), criteria);
		Predicate byProvider = builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesProviderId), provider);
		Predicate byNpi = builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNpi), npiValue);
		Predicate byEmpTin=builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesTin), empTin);

		Predicate isDenom = builder.greaterThanOrEqualTo(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominator), 1);
//		Predicate isNotNumer = builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNumerator), 0);
		Predicate isNotDenomExc = builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominatorExclusion), 0);
		Predicate isNotDenomExcep = builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominatorException), 0);
		Predicate isNotNumerExc = builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNumeratorExclusion), 0);
		Predicate isPartialMet = builder.notEqual(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominator),joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNumerator));
		
		if(mode == 0){
			joinQualityMeasuresPatientEntries.on(byMeasureId, byCriteria,byNpi);
		}else if(mode == 1){
			joinQualityMeasuresPatientEntries.on(byMeasureId, byCriteria,byProvider);
		}else{
			joinQualityMeasuresPatientEntries.on(byMeasureId, byCriteria, byEmpTin);
		}

		Selection[] selections= new Selection[] {
				root.get(PatientRegistration_.patientRegistrationId),
				root.get(PatientRegistration_.patientRegistrationAccountno),
				root.get(PatientRegistration_.patientRegistrationLastName),
				root.get(PatientRegistration_.patientRegistrationFirstName),
				builder.function("to_mmddyyyy",Date.class,root.get(PatientRegistration_.patientRegistrationDob)),
				builder.selectCase().when(builder.equal(root.get(PatientRegistration_.patientRegistrationSex),1),"Male").when(builder.equal(root.get(PatientRegistration_.patientRegistrationSex),2),"Female").otherwise("TG").as(String.class),
				root.get(PatientRegistration_.patientRegistrationRace),
				root.get(PatientRegistration_.patientRegistrationEthnicity),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationAddress1), ""),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationAddress2), ""),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationCity), "-"),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationStateName), "-"),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationZip), "-"),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationPhoneNo),"-"),
				joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesIpp),
				joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominator),
				joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominatorExclusion),
				joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNumerator),
				joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNumeratorExclusion),
				joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominatorException)

		};

		if(isNotMet){
			cq.where(isDenom,isNotDenomExc,isNotDenomExcep,isNotNumerExc,byPatientId,isPartialMet);
		}else{
			cq.where(byPatientId);
		}

		cq.distinct(true);
		cq.orderBy(builder.asc(root.get(PatientRegistration_.patientRegistrationLastName)),builder.asc(root.get(PatientRegistration_.patientRegistrationFirstName)));
		cq.select(builder.construct(MIPSPatientInformation.class,selections));
		List<MIPSPatientInformation> patientDetailsWithResults = em.createQuery(cq).getResultList();
		return patientDetailsWithResults;

	}

	@Override
	public List<QualityMeasureBean> getQualityMeasureResponseObject(int userId, HashMap<String, String> codeListForQDM){

		List<QualityMeasureBean> pqrsResponsearray = new ArrayList<QualityMeasureBean>();
		try {
			QualityMeasureBean responseBean = null;
			List<QualityMeasuresProviderMapping> qualitymeasurebean = new ArrayList<QualityMeasuresProviderMapping>();
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<QualityMeasuresProviderMapping> cquery = builder.createQuery(QualityMeasuresProviderMapping.class);
			Root<QualityMeasuresProviderMapping> root1 = cquery.from(QualityMeasuresProviderMapping.class);
			cquery.where(builder.equal(root1.get(QualityMeasuresProviderMapping_.qualityMeasuresProviderMappingProviderId),userId));
			cquery.orderBy(builder.asc(root1.get(QualityMeasuresProviderMapping_.qualityMeasuresProviderMappingMeasureId).as(Integer.class)));
			qualitymeasurebean = em.createQuery(cquery).getResultList();
			Integer userid = -1;
			String measureid = "";
			String cmsId = "";
			for(int i=0;i<qualitymeasurebean.size();i++){

				String json = new ObjectMapper().writeValueAsString(codeListForQDM);
				JSONObject jObject  = new JSONObject(json);
				Iterator iter = jObject.keys();
				String key = "";
				String value = "";
				while(iter.hasNext())
				{
					key = (String)iter.next();
					value = jObject.getString(key);
					String measureidkey = key;
					String title = value;

					userid = qualitymeasurebean.get(i).getQualityMeasuresProviderMappingProviderId();
					measureid = qualitymeasurebean.get(i).getQualityMeasuresProviderMappingMeasureId();
					if(measureid.equalsIgnoreCase(measureidkey)){
						cmsId = title;
						break;
					}
				}
				responseBean = new QualityMeasureBean(measureid, userid, cmsId);
				pqrsResponsearray.add(responseBean);
			}
		}
		catch(Exception e){
		}

		return pqrsResponsearray;
	}

	@Override
	public List<PqrsMeasureBean> getPqrsResponseObject(int patientID, int providerId, Date startDate, Date endDate, HashMap<String, String> codeListForQDM) {

		List<PqrsMeasureBean> pqrsResponsearray = new ArrayList<PqrsMeasureBean>();
		try {

			PqrsMeasureBean responseBean = null;
			CriteriaBuilder builder = em.getCriteriaBuilder();
			List<PqrsPatientEntries> pqrsResponseBean =  new ArrayList<PqrsPatientEntries>();
			CriteriaQuery<PqrsPatientEntries> cq = builder.createQuery(PqrsPatientEntries.class);
			Root<PqrsPatientEntries> root = cq.from(PqrsPatientEntries.class);

			cq.where(builder.equal(root.get(PqrsPatientEntries_.pqrsPatientEntriesPatientId), patientID),
					//builder.equal(root.get(PqrsPatientEntries_.pqrsPatientEntriesProviderId), providerId),
					builder.equal(root.get(PqrsPatientEntries_.pqrsPatientEntriesIsActive), true),
					builder.greaterThan(root.get(PqrsPatientEntries_.pqrsPatientEntriesDos), startDate),
					builder.lessThan(root.get(PqrsPatientEntries_.pqrsPatientEntriesDos), endDate));

			cq.orderBy(builder.asc(root.get(PqrsPatientEntries_.pqrsPatientEntriesMeasureId).as(Integer.class)));
			pqrsResponseBean = em.createQuery(cq).getResultList();
			String measureid = "";
			String shortdesc = "";
			Integer indicator = -1;
			String performanceind = "";
			String measureTitle = "";
			HashMap<String, Integer> measureMap = new HashMap<String, Integer>();

			for(int i=0;i<pqrsResponseBean.size();i++){

				String json = new ObjectMapper().writeValueAsString(codeListForQDM);
				JSONObject jObject  = new JSONObject(json);
				Iterator iter = jObject.keys();
				String key = "";
				String value = "";
				while(iter.hasNext())
				{
					key = (String)iter.next();
					value = jObject.getString(key);
					String measureidkey = key;
					String title = value;
					measureid = pqrsResponseBean.get(i).getPqrsPatientEntriesMeasureId();
					if(measureid.equalsIgnoreCase(measureidkey)){
						measureTitle = title;
						break;
					}
				}
				shortdesc = pqrsResponseBean.get(i).getPqrsPatientEntriesShortDescription();
				indicator = pqrsResponseBean.get(i).getPqrsPatientEntriesPerformanceIndicator();

				if(indicator == 1)
					performanceind = "Performance Met";
				else if(indicator == 2)
					performanceind = "Performance Exclusion";
				else if(indicator == 3)
					performanceind = "Denominator Exclusion";
				else if(indicator == 4)
					performanceind = "Denominator Exception";
				else 
					performanceind = "Performance not Met";
				if(!measureMap.containsKey(measureid)){
					measureMap.put(measureid, i);
					responseBean = new PqrsMeasureBean(measureid,measureTitle,performanceind,shortdesc);
					pqrsResponsearray.add(responseBean);
				}else{
					responseBean  = pqrsResponsearray.get(measureMap.get(measureid));
					responseBean.setDescription( responseBean.getDescription().concat(","+shortdesc) );
				}
			}
		} 
		catch(Exception e){

		}
		return pqrsResponsearray;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<MUPerformanceBean> getAnalyticsPerformanceReport(int providerId, String accountId, String configuredMeasures,int submissionMethod,String sharedPath) throws Exception{
		EMeasureUtils eMeasureUtils=new EMeasureUtils();
		
		Calendar now = Calendar.getInstance();
		int reportingYear = now.get(Calendar.YEAR);
System.out.println("configuredMeasures>>>>>>>>>>>>>"+configuredMeasures);
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<MUPerformanceBean> cq = builder.createQuery(MUPerformanceBean.class);
		
		Root<EmployeeProfile> root = cq.from(EmployeeProfile.class);
		Join<EmployeeProfile, MacraProviderConfiguration> macraConfig = root.join(EmployeeProfile_.macraProviderConfiguration, JoinType.INNER);
//		Join<MacraProviderConfiguration,QualityMeasuresProviderMapping> joinProviderConfigurationProviderMapping=macraConfig.join(MacraProviderConfiguration_.qualityMeasuresProviderMappingTable,JoinType.INNER);
		Join<EmployeeProfile,MacraMeasuresRate> joinMeasureRate=root.join(EmployeeProfile_.macraMeasuresRate,JoinType.LEFT);
		
		Predicate byProviderId = builder.equal(root.get(EmployeeProfile_.empProfileEmpid), providerId);
		Predicate byReportingYear = builder.equal(macraConfig.get(MacraProviderConfiguration_.macraProviderConfigurationReportingYear), reportingYear);		
		Predicate byConfiguredMeasures = joinMeasureRate.get(MacraMeasuresRate_.macraMeasuresRateMeasureId).in(Arrays.asList(configuredMeasures.split(",")));
		joinMeasureRate.on(byConfiguredMeasures);
		cq.where(builder.and(byProviderId, byReportingYear));
		
		Selection[] aci_selections= new Selection[] {

				root.get(EmployeeProfile_.empProfileFullname),
				builder.coalesce(joinMeasureRate.get(MacraMeasuresRate_.macraMeasuresRateMeasureId),'0'),
				builder.coalesce(joinMeasureRate.get(MacraMeasuresRate_.macraMeasuresRateCriteria),1),
				builder.coalesce(joinMeasureRate.get(MacraMeasuresRate_.macraMeasuresRateReportingYear),2017),
				builder.coalesce(joinMeasureRate.get(MacraMeasuresRate_.macraMeasuresRateIpp),0),
				builder.coalesce(joinMeasureRate.get(MacraMeasuresRate_.macraMeasuresRateDenominator),0),
				builder.coalesce(joinMeasureRate.get(MacraMeasuresRate_.macraMeasuresRateDenominatorExclusion),0),
				builder.coalesce(joinMeasureRate.get(MacraMeasuresRate_.macraMeasuresRateNumerator),0),
				builder.coalesce(joinMeasureRate.get(MacraMeasuresRate_.macraMeasuresRateNumeratorExclusion),0),
				builder.coalesce(joinMeasureRate.get(MacraMeasuresRate_.macraMeasuresRateDenominatorException),0),
				builder.coalesce(joinMeasureRate.get(MacraMeasuresRate_.macraMeasuresRateReporting),0.0),
				builder.coalesce(joinMeasureRate.get(MacraMeasuresRate_.macraMeasuresRateReporting),0.0),
				builder.coalesce(joinMeasureRate.get(MacraMeasuresRate_.macraMeasuresRatePoints),0),
				builder.coalesce(joinMeasureRate.get(MacraMeasuresRate_.macraMeasuresRateNpi),""),
				macraConfig.get(MacraProviderConfiguration_.macraProviderConfigurationReportingStart),
				macraConfig.get(MacraProviderConfiguration_.macraProviderConfigurationReportingEnd)
					
			};
		
		/*Root<MacraMeasuresRate> root = cq.from(MacraMeasuresRate.class);

		Join<MacraMeasuresRate, EmployeeProfile> joinEmp = root.join(MacraMeasuresRate_.empProfileTable, JoinType.INNER);
		Join<EmployeeProfile, MacraProviderConfiguration> macraConfig = joinEmp.join(EmployeeProfile_.macraProviderConfiguration, JoinType.INNER);
		
		Predicate byProviderId = builder.equal(root.get(MacraMeasuresRate_.macraMeasuresRateProviderId), providerId);
		Predicate byReportingYear = builder.equal(root.get(MacraMeasuresRate_.macraMeasuresRateReportingYear), reportingYear);		
		Predicate byConfiguredMeasures = root.get(MacraMeasuresRate_.macraMeasuresRateMeasureId).in(Arrays.asList(configuredMeasures.split(",")));

		cq.where(builder.and(byProviderId, byReportingYear, byConfiguredMeasures));

		Selection[] aci_selections= new Selection[] {

			joinEmp.get(EmployeeProfile_.empProfileFullname),
			root.get(MacraMeasuresRate_.macraMeasuresRateMeasureId),
			root.get(MacraMeasuresRate_.macraMeasuresRateCriteria),
			root.get(MacraMeasuresRate_.macraMeasuresRateReportingYear),
			builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateIpp),0),
			builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateDenominator),0),
			builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorExclusion),0),
			builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateNumerator),0),
			builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateNumeratorExclusion),0),
			builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorException),0),
			root.get(MacraMeasuresRate_.macraMeasuresRateReporting),
			root.get(MacraMeasuresRate_.macraMeasuresRateReporting),
			builder.coalesce(root.get(MacraMeasuresRate_.macraMeasuresRatePoints),0),
			root.get(MacraMeasuresRate_.macraMeasuresRateNpi),
			macraConfig.get(MacraProviderConfiguration_.macraProviderConfigurationReportingStart),
			macraConfig.get(MacraProviderConfiguration_.macraProviderConfigurationReportingEnd)
				
		};*/

		cq.multiselect(aci_selections);

		List<MUPerformanceBean> results = em.createQuery(cq).getResultList();

		for(int i=0;i<results.size();i++){

			MUPerformanceBean resultObject = results.get(i);

			String measureId = resultObject.getMeasureId();

			String cmsIdNTitle = "";

			if(configuredMeasures.contains("ACI_TRANS_EP_1") || configuredMeasures.contains("ACI_EP_1")){
				
				if(measureId.equals("ACI_TRANS_EP_1") || measureId.equals("ACI_EP_1")){
					cmsIdNTitle = measureId.concat("&&&Electronic Prescribing");						
				}else if(measureId.equals("ACI_TRANS_SM_1") || measureId.equals("ACI_CCTPE_2")){
					cmsIdNTitle = measureId.concat("&&&Secure Messaging");						
				}else if(measureId.equals("ACI_TRANS_PEA_1") || measureId.equals("ACI_PEA_1")){
					cmsIdNTitle = measureId.concat("&&&Patient Electronic Access");						
				}else if(measureId.equals("ACI_TRANS_PEA_2") || measureId.equals("ACI_CCTPE_1")){
					cmsIdNTitle = measureId.concat("&&&View, Download and Transmit (VDT)");						
				}else if(measureId.equals("ACI_TRANS_HIE_1") || measureId.equals("ACI_HIE_1")){
					cmsIdNTitle = measureId.concat("&&&Health Information Exchange");						
				}else if(measureId.equals("ACI_TRANS_PSE_1") || measureId.equals("ACI_PEA_2")){
					cmsIdNTitle = measureId.concat("&&&Patient-Specific Education");						
				}else if(measureId.equals("ACI_TRANS_MR_1") || measureId.equals("ACI_HIE_3")){
					cmsIdNTitle = measureId.concat("&&&Medication Reconcilation");						
				}else{
					cmsIdNTitle = getCMSIdAndTitle(measureId, accountId);
				}
				
			}else if(!measureId.equals("0")){
				cmsIdNTitle = getCMSIdAndTitle(measureId, accountId);
				resultObject.setCmsId(cmsIdNTitle.split("&&&")[0]);
				resultObject.setTitle(cmsIdNTitle.split("&&&")[1]);
			}
			if(cmsIdNTitle.contains("CMS"))
			{	
				EMeasure eMeasureObj=null;
				List<EMeasure> eMeasures=new ArrayList<EMeasure>();
				eMeasures= eMeasureUtils.getMeasureBeanDetails(measureId, sharedPath,accountId);
				eMeasureObj=eMeasures.get(0);
				
				resultObject.setPoints(getPointsByBenchMark(resultObject.getReportingRate()/100, eMeasureObj));
			}
			
			if(submissionMethod==2)
			resultObject.setSubmissionMethod("EHR");
			else if(submissionMethod==1)
			resultObject.setSubmissionMethod("Claims");
			else if(submissionMethod==3)
			resultObject.setSubmissionMethod("Registry");

		}

		return results;

	}
	
	@Override
	public String getMeasureValidationServer() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = builder.createQuery(String.class);
		Root<GatewayController> root = cq.from(GatewayController.class);
		cq.select(root.get(GatewayController_.gatewayControllerUrl));
		cq.where(builder.equal(root.get(GatewayController_.gatewayControllerModuleName), "MACRA measure validation"));
		List<String> results = em.createQuery(cq).getResultList();
		return results.get(0);
	
	}
	
	@Override
	public void getDashBoardDetails(int providerId, String accountId, String tinValue, String configuredMeasures, String aciMeasures, boolean byNpi, MUDashboardBean providerDashboard,String sharedPath){		
		EMeasureUtils measureUtils = new EMeasureUtils();
		List<MIPSPerformanceBean> ecqmPerformance, aciPerformance;
		int ecqmPoints = 0, aciPoints = 0,measurePoint=0;
		
		if(byNpi){
			
			ecqmPerformance = getMeasureRateReportByNPI(providerId, accountId, configuredMeasures, false, true);
			aciPerformance = getMeasureRateReportByNPI(providerId, accountId, aciMeasures, true, true);
			
		}else if(!tinValue.equals("")){

			ecqmPerformance = getGroupPerformanceCount(tinValue, configuredMeasures, accountId, false, true);
			aciPerformance = getGroupPerformanceCount(tinValue, aciMeasures, accountId, true, true);
			
		}else{
			
			ecqmPerformance = getMeasureRateReport(providerId, accountId, configuredMeasures, false, true);
			aciPerformance = getMeasureRateReport(providerId, accountId, aciMeasures, true, true);
			
		}
		
		List<MIPSPerformanceBean> temp=new ArrayList<MIPSPerformanceBean>();
		List<EMeasure> eMeasures=new ArrayList<EMeasure>();
		for(int i=0;i<ecqmPerformance.size();i++){
			EMeasure eMeasureObj=null;
			try {
				eMeasures= measureUtils.getMeasureBeanDetails(ecqmPerformance.get(i).getMeasureId(), sharedPath,accountId);
				eMeasureObj=eMeasures.get(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(i<5 && temp.size()<7)
			{
				temp.add(ecqmPerformance.get(i));
				measurePoint=getPointsByBenchMark(ecqmPerformance.get(i).getPerformanceRate(),eMeasureObj);
				ecqmPerformance.get(i).setPoints(measurePoint);
				ecqmPoints+=measurePoint;
			}
			else if(i>=5 && temp.size()<7)
			{
				if(eMeasureObj.getType().equalsIgnoreCase("Outcome") || eMeasureObj.isHighPriority())
				{
					temp.add(ecqmPerformance.get(i));
					ecqmPoints+=getPointsByBenchMark(ecqmPerformance.get(i).getPerformanceRate(),eMeasureObj);
				}
			}
			//aciPoints += aciPerformance.get(i).getPoints();
			
		}

		
		for(int i=0;i<aciPerformance.size();i++){
			
			aciPoints += aciPerformance.get(i).getPoints();
			
		}
		
		providerDashboard.setAciMeasures(aciPerformance);
		providerDashboard.setEcqmMeasures(ecqmPerformance);
		
		providerDashboard.setAciPoints(aciPoints);
		providerDashboard.setEcqmPoints(ecqmPoints);
		
	}
	
	public String getProviderName(Integer provider) throws Exception {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<EmployeeProfile> rootEmployeeProfile = cq.from(EmployeeProfile.class);
		cq.select(rootEmployeeProfile.get(EmployeeProfile_.empProfileFullname));
		cq.where(builder.equal(rootEmployeeProfile.get(EmployeeProfile_.empProfileEmpid),provider));
		Query query=em.createQuery(cq);
		String providerName=(String) query.getSingleResult();
		return providerName;

	}
	
	private List<MIPSPatientInformation> getPatienttoPrint(int criteriaId, int providerId, String measureId,String tinId,int measureCriteria){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<MIPSPatientInformation> cq = builder.createQuery(MIPSPatientInformation.class);
		Root<PatientRegistration> root = cq.from(PatientRegistration.class);
		Join<PatientRegistration,QualityMeasuresPatientEntries> joinQualityMeasuresPatientEntries=root.join(PatientRegistration_.qualityMeasuresPatientEntries,JoinType.INNER);

		String npiValue = getNPIForProvider(providerId);
		List<Predicate> predicates = new ArrayList<>();
//		Predicate byPatientId = root.get(PatientRegistration_.patientRegistrationId).in(Arrays.asList(patientId.split(",")));
		predicates.add(builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesMeasureId), measureId));
		predicates.add(builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesCriteria), measureCriteria));
//		predicates.add(builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNpi), npiValue);
		if(!tinId.equals("-1"))
			predicates.add(builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesTin), tinId));
		else
			predicates.add(builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesProviderId), providerId));
		
		if(criteriaId == 1){
			predicates.add(builder.greaterThanOrEqualTo(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesIpp), 1));
		}else if(criteriaId == 2){
			predicates.add(builder.greaterThanOrEqualTo(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominator), 1));
		}else if(criteriaId == 3){
			predicates.add(builder.greaterThanOrEqualTo(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominatorExclusion), 1));
		}else if(criteriaId == 4){
			predicates.add(builder.greaterThanOrEqualTo(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominatorException), 1));
		}else if(criteriaId == 5){
			predicates.add(builder.greaterThanOrEqualTo(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNumerator), 1));
		}else if(criteriaId == 6){
			predicates.add(builder.greaterThanOrEqualTo(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNumeratorExclusion), 1));
		}else if(criteriaId == 7){
			predicates.add(builder.greaterThanOrEqualTo(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominator), 1));
			predicates.add(builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominatorExclusion), 0));
			predicates.add(builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominatorException), 0));
			predicates.add(builder.equal(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNumeratorExclusion), 0));
			predicates.add(builder.notEqual(joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominator),joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNumerator)));
			
		}
		
		Selection[] selections= new Selection[] {
				root.get(PatientRegistration_.patientRegistrationId),
				root.get(PatientRegistration_.patientRegistrationAccountno),
				root.get(PatientRegistration_.patientRegistrationLastName),
				root.get(PatientRegistration_.patientRegistrationFirstName),
				builder.function("to_mmddyyyy",Date.class,root.get(PatientRegistration_.patientRegistrationDob)),
				builder.selectCase().when(builder.equal(root.get(PatientRegistration_.patientRegistrationSex),1),"Male").when(builder.equal(root.get(PatientRegistration_.patientRegistrationSex),2),"Female").otherwise("TG").as(String.class),
				root.get(PatientRegistration_.patientRegistrationRace),
				root.get(PatientRegistration_.patientRegistrationEthnicity),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationAddress1), ""),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationAddress2), ""),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationCity), "-"),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationStateName), "-"),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationZip), "-"),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationPhoneNo),""),
				joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesIpp),
				joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominator),
				joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominatorExclusion),
				joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNumerator),
				joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesNumeratorExclusion),
				joinQualityMeasuresPatientEntries.get(QualityMeasuresPatientEntries_.qualityMeasuresPatientEntriesDenominatorException)

		};

		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.distinct(true);
		cq.orderBy(builder.asc(root.get(PatientRegistration_.patientRegistrationLastName)),builder.asc(root.get(PatientRegistration_.patientRegistrationFirstName)));
		cq.select(builder.construct(MIPSPatientInformation.class,selections));
		
		List<MIPSPatientInformation> patientDetailsWithResults = em.createQuery(cq).getResultList();
		return patientDetailsWithResults;

	}
	
	@Override
	
	public  String generatePDFFile(GeneratePDFDetails generatePDFDetails,int provId, String measureid,String accountId, int criteriaId,String tinId,int measureCriteria,boolean isNotMet) throws Exception{

		String CMSIdAndTitle=getCMSIdAndTitle(measureid, accountId);
		String cmsID=CMSIdAndTitle.split("&&&")[0];
		String measureName=CMSIdAndTitle.split("&&&")[1];
		String measureType=null,providerName,submissionMethod,reportingStart,reportingEnd;
		String PDFData="";
		try{
			if(criteriaId == 1){
				measureType="IPP";
			}else if(criteriaId == 2){
				measureType="Denominator";
			}else if(criteriaId == 3){
				measureType="Denominator Exclusion";
			}else if(criteriaId == 4){
				measureType="Denominator Exception";
			}else if(criteriaId == 5){
				measureType="Numerator";
			}else if(criteriaId == 6){
				measureType="Numerator Exclusion";
			}else if(criteriaId == 7){
				measureType="Not Met";
			}
	
			
			PDFData+="<html><style> table.beta,.beta td,.beta th{border:1px solid lightgrey; border-collapse:collapse;}</style><body><h3><center>"+measureName+" - "+measureType+" Patient List </center></h3>";
	
			if(provId!=-1){
				providerName = getProviderName(provId);
				submissionMethod = generatePDFDetails.getSubmissionMethod();
				reportingStart =generatePDFDetails.getReportStart();
				reportingEnd = generatePDFDetails.getReportEnd();
				PDFData+="<div><table><tr><td>Provider Name</td><td>:&nbsp</td><td>"+providerName+"</td></tr><tr><td>Submission Method</td><td>:&nbsp</td><td>"+submissionMethod+"</td></tr><tr><td>Reporting Period</td><td>:&nbsp</td><td>"+reportingStart+" - "+reportingEnd+"</td></tr></table></div>";
	
			}
			else
				PDFData+="<div><table><tr><th> Tin Number</th> <td>:&nbsp</td>  <td>"+tinId+"</tr></table></div>";
	
			List<MIPSPatientInformation> totalPatientList= getPatienttoPrint(criteriaId,provId,measureid,tinId,measureCriteria);
	
	
			PDFData+="<table class=beta width=100% cellpadding=5 cellspacing=5> <tr>  <th>Account No.</th>   <th>Last Name</th>   <th>First Name</th>  <th>DOB</th>   <th>Gender</th> <th>Phone Number</th>   </tr><br>";
			String accountNo = null, lastName = null, firstName = null,dob = null,gender = null,phoneNo = null;
	
			for(int i=0;i<totalPatientList.size();i++)
			{
				accountNo = totalPatientList.get(i).getAccountNo();
				lastName = totalPatientList.get(i).getLastName();
				firstName = totalPatientList.get(i).getFirstName();
				dob = totalPatientList.get(i).getDob();
				gender = totalPatientList.get(i).getGender();
				phoneNo = totalPatientList.get(i).getPhoneNo();
	
				PDFData+="<tr> <td align=left >"+accountNo+"</td>  <td align=left>"+lastName+"</td>  <td align=left>"+firstName+"</td>   <td align=left>"+dob+"</td>    <td align=left>"+gender+"</td> <td align=left>"+phoneNo+"</td> </tr>";
			}
			PDFData+="</table> </body></html>";
		}
		catch(Exception e){
			PDFData="";
		}
		String sharedPath = sharedFolderBean.getSharedFolderPath().get(TennantContextHolder.getTennantId()).toString();
		

		String FileName = cmsID+System.currentTimeMillis();
		String HTMLFilePath=generateHTMLFilePath(sharedPath,PDFData,FileName);
		String s=null;	
		File patientDir = new File(sharedPath+"/ECQMPDF/");
		patientDir.setWritable(true, false);
		patientDir.setExecutable(true, false);
		if (!patientDir.exists()){
			patientDir.mkdir();
		}
		Process p2 = Runtime.getRuntime().exec("/usr/local/bin/wkhtmltopdf "+HTMLFilePath+" "+sharedPath+"/ECQMPDF/"+FileName+".pdf");
		BufferedReader stdInput2 = new BufferedReader(new  InputStreamReader(p2.getInputStream()));
		BufferedReader stdError2 = new BufferedReader(new InputStreamReader(p2.getErrorStream()));
		while ((s = stdInput2.readLine()) != null) {
			//System.out.println(s);
		}
		while ((s = stdError2.readLine()) != null) {

		}
		return FileName;
	}
	
	
	public String generateHTMLFilePath(String sharePath,String DatatoSave,String fileName)
	{
	File saveDir = new File(sharePath+"/ECQMPDF/");
	saveDir.setWritable(true, false);
	saveDir.setExecutable(true, false);
	String filename="";
	try{
		if (!saveDir.exists()){
		        saveDir.mkdir();
		}
		filename =sharePath+"/ECQMPDF/"+fileName+".html";
		File obj=new File(sharePath+"/ECQMPDF/"+fileName+".html");
		if(!obj.canExecute())
		{
		obj.setExecutable(true);
		}
		else
		if(!obj.canWrite())
		{
			obj.setWritable(true);
		}
		else
		if(!obj.canRead())
		{
		obj.setReadable(true, false);
		}
		FileWriter fstream = new FileWriter(obj);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(DatatoSave);
		out.close();
	}catch (Exception e){ //Catch exception if any
		e.printStackTrace();
	}
	return filename;
	}
	
	private int getPointsByReporting(double reportingRate){
		
		double rate = reportingRate / 10;
		
		if(rate == 0){
			return 0;
		}else if(rate >= 1 && rate < 4){
			return 3;
		}else if(rate >= 4 && rate < 5){
			return 4;
		}else if(rate >= 5 && rate < 6){
			return 5;
		}else if(rate >= 6 && rate < 7){
			return 6;
		}else if(rate >= 7 && rate < 8){
			return 7;
		}else if(rate >= 8 && rate < 9){
			return 8;
		}else if(rate >= 9){
			return 9;
		}else{
			return 10;
		}
		
	}
	
	private int getPointsByBenchMark(double performancRate,EMeasure eMeasureObj){
		performancRate=performancRate*100;
		HashMap<String, List<Benchmark>> benchMark=eMeasureObj.getBenchmark();
		List<Benchmark> benchMarkObjs=benchMark.get("2017");
		int index=0;
		for(int i=0;i<benchMarkObjs.size();i++)
		{
			if(benchMarkObjs.get(i).getSubmissionMethod().equals("EHR"))
			{
				ArrayList<Decile> decileList=benchMarkObjs.get(i).getDecileList();
				for(int j=0;j<decileList.size();j++)
				{
					if(!(decileList.get(j).getStart()==null && decileList.get(j).getEnd()==null))
					{
						if(decileList.get(j).getStart()!=null && decileList.get(j).getEnd()!=null)
						{
							if(performancRate>=decileList.get(j).getStart() && performancRate<=decileList.get(j).getEnd())
							index= decileList.get(j).getIndex();
						}
						else if(decileList.get(j).getStart()!=null)
						{
							if(performancRate>=decileList.get(j).getStart())
								index= decileList.get(j).getIndex();
						}
						else if(decileList.get(j).getEnd()!=null)
						{
							if(performancRate<=decileList.get(j).getEnd())
								index= decileList.get(j).getIndex();
						}
					}
				}
			}
		}
		if(index==0)
			index=1;
		return index;
		
	}
	@Override
	public String getMIPSMeasureDetails(String measureId,String accountId) throws Exception {
		String getMIPSData = getCMSIdAndTitle(measureId,accountId); 
		return getMIPSData;
	}
	
}