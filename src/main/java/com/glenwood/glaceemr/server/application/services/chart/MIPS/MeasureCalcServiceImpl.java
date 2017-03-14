package com.glenwood.glaceemr.server.application.services.chart.MIPS;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.Bean.ClinicalDataQDM;
import com.glenwood.glaceemr.server.application.Bean.EPMeasureBean;
import com.glenwood.glaceemr.server.application.Bean.InvestigationQDM;
import com.glenwood.glaceemr.server.application.Bean.MeasureStatus;
import com.glenwood.glaceemr.server.application.Bean.MedicationQDM;
import com.glenwood.glaceemr.server.application.Bean.ReferralQDM;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.Assessment;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.DiagnosticStudy;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.Intervention;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.LabTest;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.Patient;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.PhysicalExam;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.Procedure;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.QDM;
import com.glenwood.glaceemr.server.application.Bean.macra.data.qdm.Request;
import com.glenwood.glaceemr.server.application.Bean.macra.ecqm.EMeasureUtils;
import com.glenwood.glaceemr.server.application.models.QualityMeasuresPatientEntries;
import com.glenwood.glaceemr.server.application.repositories.MacraConfigurationRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientMeasureStatusLogRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientMeasureStatusRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.repositories.ProblemListRepository;
import com.glenwood.glaceemr.server.application.specifications.QPPConfigurationSpecification;
import com.glenwood.glaceemr.server.application.specifications.QPPPerformanceSpecification;


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
	PatientRegistrationRepository patientInfoRepo;
	
	@Autowired
	ProblemListRepository diagnosisRepo;
	
	@PersistenceContext
	EntityManager em;
	
	
	
	@Override
	public void saveMeasureDetails(String measureId, int patientId, List<MeasureStatus> measureStatus) {
		
		MeasureStatus patientObj = new MeasureStatus();
		
		Date d = new Date();
		Timestamp curr_time = new Timestamp(d.getTime());
		
		for(int i=0;i<measureStatus.size();i++){
			
			patientObj = measureStatus.get(i);
			
			QualityMeasuresPatientEntries patientData = patientDataRepo.findOne(Specifications.where(QPPPerformanceSpecification.isPatientExisting(measureId, patientId, patientObj.getReportingYear())));
			
			if(patientData == null || patientData.equals(null)){
				
				patientData.setQualityMeasuresPatientEntriesPatientId(patientId);
				patientData.setQualityMeasuresPatientEntriesMeasureId(measureId);
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
				patientData.setQualityMeasuresPatientEntriesMeasureObservation(Integer.parseInt(""+patientObj.getMeasureObservation()));

				patientDataRepo.saveAndFlush(patientData);
				
				patientLogRepo.saveAndFlush(patientData);
				
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
				patientData.setQualityMeasuresPatientEntriesMeasureObservation(Integer.parseInt(""+patientObj.getMeasureObservation()));
				
				patientDataRepo.saveAndFlush(patientData);

				patientData.setQualityMeasuresPatientEntriesPatientId(patientId);
				patientData.setQualityMeasuresPatientEntriesMeasureId(measureId);
				patientData.setQualityMeasuresPatientEntriesReportingYear(Integer.parseInt(patientObj.getReportingYear()));
				
				patientLogRepo.saveAndFlush(patientData);
				
			}
			
		}
		
	}

	@Override
	public Request getQDMRequestObject(int patientID, int providerId, HashMap<String, HashMap<String, String>> codeListForQDM) {
		
		Request finalReqObject = new Request();
		try{

			ExportQDM qdmData = new ExportQDM();
			EMeasureUtils measureUtils = new EMeasureUtils();		
			Patient requestObj = new Patient();

			String startDate="12/31/2015";
			String endDate="12/31/2017";

			SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
			Date date1=format.parse(startDate);
			Date date2=format.parse(endDate);
			
			boolean considerProvider = false;

			HashMap<String, String> codeListForCNM = measureUtils.getCodeListForCNM(codeListForQDM);

			String snomedCodesForCNM = codeListForCNM.get("SNOMED");

			String loincCodesForCNM = codeListForCNM.get("LOINC");

			List<MedicationQDM> medicationsReviewed = qdmData.getMedicationsReviewed(em,patientID,date1,date2);

			Integer confType = macraConfRepo.findOne(Specifications.where(QPPConfigurationSpecification.getConfObj(2017))).getMacraConfigurationType();

			if(confType == 0){
				requestObj = qdmData.getRequestQDM(patientInfoRepo, diagnosisRepo, patientID, providerId);
				considerProvider = true;
			}else{
				requestObj = qdmData.getRequestQDM(patientInfoRepo, diagnosisRepo, patientID, -1);
			}

			requestObj.setEncounterList(qdmData.getEncounterQDM(em, considerProvider, patientID, providerId, codeListForQDM.get("Encounter")));

			requestObj.setDxList(qdmData.getPatientDiagnosisQDM(diagnosisRepo, patientID));

			List<QDM> tobaccoDetails=qdmData.getTobaccoDetails(em, patientID);
			
			requestObj.setTobaccoStatusList(tobaccoDetails);
			
			if(codeListForQDM.containsKey("Medication")){
				requestObj.setMedicationOrders(qdmData.getMedicationQDM(em, codeListForQDM.get("Medication").get("RXNORM"), patientID, 2));
				requestObj.setActiveMedicationsList(qdmData.getActiveMedications(em, codeListForQDM.get("Medication").get("RXNORM"), patientID, 2));
			}

			List<InvestigationQDM> investigationQDM = qdmData.getInvestigationQDM(em,patientID,providerId);

			List<ClinicalDataQDM> clinicalDataQDM =qdmData.getClinicalDataQDM(em,patientID,snomedCodesForCNM,loincCodesForCNM,true,date1,date2);

			if(codeListForQDM.containsKey("Immunization")){
				requestObj.setImmunizationList(qdmData.getImmuDetails(em, patientID));
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
				List<PhysicalExam> physicalExam=measureUtils.getPhysicalexamFromCNM(clinicalDataQDM,measureUtils.getCodeListByCategory(codeListForQDM, "Physical Exam"));
				requestObj.setPhysicalExamList(physicalExam);
			}

			if(codeListForQDM.containsKey("Procedure")){
				String codeList=measureUtils.getCodeListByCategory(codeListForQDM, "Procedure");
				List<String> cptCodes=measureUtils.getCPTCodes(codeListForQDM, "Procedure");
				List<Procedure> procBasedOnCPT=qdmData.getProcBasedOnCPT(em, patientID, providerId, cptCodes);
				List<Procedure>  procFromCNM = measureUtils.getProcFromCNM(clinicalDataQDM,codeList);
				requestObj.setProcedureList(measureUtils.getProcedureQDM(investigationQDM,codeList,medicationsReviewed,procFromCNM,procBasedOnCPT));
			}
			
			if(codeListForQDM.containsKey("Intervention")){

				String codeList = measureUtils.getCodeListByCategory(codeListForQDM, "Intervention");
				
				List<Intervention> interventionQDM = measureUtils.getInterventionQDM(investigationQDM,codeList);
	
				List<Intervention>  interventionFromCNM = measureUtils.getInterventionFromCNM(clinicalDataQDM,codeList);
				
				if(interventionFromCNM.size() > 0){
					interventionQDM.addAll(interventionFromCNM);
				}
				
				if(codeListForQDM.containsKey("Communication")){
					
					List<ReferralQDM> referralObj = qdmData.getReferrals(em,patientID);
					
					if(referralObj.size() > 0){
						
						interventionQDM.addAll(qdmData.setReferrals(referralObj));
						
						requestObj.setCommunications(qdmData.getCommunicationQDM(referralObj));
						
					}
					
				}
				
				requestObj.setInterventionList(interventionQDM);

			}

			finalReqObject.setPatient(requestObj);

		}
		catch(Exception e){
			e.printStackTrace();
		}
		return finalReqObject;
	}

	@Override
	public List<EPMeasureBean> getEPMeasuresResponseObject(int patientID, int providerId, Date startDate, Date endDate) {
		
		List<EPMeasureBean> epMeasureInfo = new ArrayList<EPMeasureBean>();
		
		ExportQDM qdmData = new ExportQDM(); 
		
		int encounterId = qdmData.getMaxEncounterIdByPatient(patientID, em);
		
		epMeasureInfo.add(setEPrescriptionDetails(qdmData, encounterId, providerId));
		
		epMeasureInfo.add(setMedicationReconcilatonDetails(qdmData, encounterId, providerId));
		
		epMeasureInfo.add(setReferralExchangeInfo(qdmData, patientID, providerId, startDate, endDate));
		
		epMeasureInfo.add(setSecureMessageInfoDetails(qdmData, patientID, providerId, startDate, endDate));
		
		epMeasureInfo.add(setPatientElectronicAccessInfo(qdmData, patientID, providerId, startDate, endDate));
		
		epMeasureInfo.add(setPatientAccessInfoForPortal(qdmData, patientID, providerId, startDate, endDate));
		
		return epMeasureInfo;
		
	}
	
	private EPMeasureBean setEPrescriptionDetails(ExportQDM qdmData, int encounterId, int providerId){
		
		String ePrescResult = qdmData.getEPrescribingDetails(encounterId,em);
		
		EPMeasureBean epObject = new EPMeasureBean();
		
		epObject.setMeasureId("C202");
		epObject.setMeasureTitle("Electronic Prescribing");
		epObject.setDescription(ePrescResult.split("&&&&")[0]);
		
		if(Integer.parseInt(ePrescResult.split("&&&&")[1]) == -1){
			epObject.setStatus("Not Completed");
		}else if(Integer.parseInt(ePrescResult.split("&&&&")[1]) == 0){
			epObject.setStatus("Not Completed");
		}else{
			epObject.setStatus("Completed");
		}
		
		return epObject;
		
	}
	
	private EPMeasureBean setMedicationReconcilatonDetails(ExportQDM qdmData, int encounterId, int providerId){
		
		boolean isTransitionOfCare = qdmData.checkTransitionOfCareByEncId(encounterId, em);
		
		EPMeasureBean epObject = new EPMeasureBean();
		
		epObject.setMeasureId("C214");
		epObject.setMeasureTitle("Medication Reconciliation");
		if(!isTransitionOfCare){
			epObject.setDescription("Transition of Care not checked for current encounter");
			epObject.setStatus("Not Completed");
		}else{
			
			boolean medReconcilationResult = qdmData.getReconcilationStatusByEncId(encounterId,em);
			
			if(medReconcilationResult){
				epObject.setStatus("Completed");
			}else{
				epObject.setDescription("Reconcile medications to complete this measure");
				epObject.setStatus("Not Completed");
			}
			
		} 
		
		return epObject;
		
	}
	
	private EPMeasureBean setReferralExchangeInfo(ExportQDM qdmData, int patientId, int providerId, Date startDate, Date endDate){
		
		EPMeasureBean epObject = new EPMeasureBean();
		
		String result = qdmData.getReferralInfoExchangeByProvider(providerId, patientId, startDate, endDate,em);
		
		epObject.setMeasureId("C217");
		epObject.setMeasureTitle("Health Information Exchange");
		epObject.setDescription(result);
		
		if(Integer.parseInt(result.split("\n")[1].split(":")[1].replace(" ","")) == 0){
			epObject.setStatus("Not Completed");
		}else{
			
			if(Integer.parseInt(result.split("\n")[0].split(":")[1].replace(" ","")) >= 1){
				epObject.setStatus("Completed");
			}else{
				epObject.setStatus("Not Completed");
			}
		}
		
		return epObject;
		
	}
	
	private EPMeasureBean setSecureMessageInfoDetails(ExportQDM qdmData, int patientId, int providerId, Date startDate, Date endDate){
		
		EPMeasureBean epObject = new EPMeasureBean();
		
		String result = qdmData.getSecureMessagingInfo(providerId, patientId, startDate, endDate,em);
		
		epObject.setMeasureId("C213");
		epObject.setMeasureTitle("Secure Messaging");
		epObject.setDescription(result);
		
		if( (Integer.parseInt(result.split("\n")[0].split(":")[1].replace(" ","")) == 0) && (Integer.parseInt(result.split("\n")[1].split(":")[1].replace(" ","")) == 0) ){
			epObject.setStatus("Not Completed");
		}else{
			
			if( (Integer.parseInt(result.split("\n")[0].split(":")[1].replace(" ","")) >= 1) || (Integer.parseInt(result.split("\n")[1].split(":")[1].replace(" ","")) >= 1)){
				epObject.setStatus("Completed");
			}else{
				epObject.setStatus("Not Completed");
			}
		}
		
		return epObject;
		
	}
	
	private EPMeasureBean setPatientElectronicAccessInfo(ExportQDM qdmData, int patientId, int providerId, Date startDate, Date endDate){
		
		EPMeasureBean epObject = new EPMeasureBean();
		
		String patientCount = qdmData.getPatientElectronicAccessInfo(providerId, patientId, startDate, endDate, em);
		
		epObject.setMeasureId("C207");
		epObject.setMeasureTitle("Patient Electronic Access (VDT)");
		
		if(Integer.parseInt(patientCount.split("&")[1]) > 0){
			epObject.setDescription("Last Accessed On: "+patientCount.split("&")[0]);
			epObject.setStatus("Completed");
		}else{
			epObject.setDescription("Not an active portal user");
			epObject.setStatus("Not Completed");
		}
		
		return epObject;
		
	}
	
	private EPMeasureBean setPatientAccessInfoForPortal(ExportQDM qdmData, int patientId, int providerId, Date startDate, Date endDate){
		
		EPMeasureBean epObject = new EPMeasureBean();
		
		String portalLastAccessed = qdmData.getPatientAccessInfoForPortal(providerId, patientId, startDate, endDate, em);
		
		epObject.setMeasureId("C207");
		epObject.setMeasureTitle("Patient Access");
		
		if(portalLastAccessed!=""){
			epObject.setStatus("Completed");
			epObject.setDescription("Last Accessed Portal on: "+portalLastAccessed);
		}else{
			epObject.setStatus("Not Completed");
			epObject.setDescription("Not an active portal user");
		}
		
		return epObject;
		
	}
	
}