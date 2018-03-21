package com.glenwood.glaceemr.server.application.services.chart.MIPS;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.Bean.EncounterQDM;
import com.glenwood.glaceemr.server.application.Bean.MIPSPerformanceBean;
import com.glenwood.glaceemr.server.application.Bean.MacraProviderQDM;
import com.glenwood.glaceemr.server.application.Bean.MeasureDetails;
import com.glenwood.glaceemr.server.application.Bean.QPPDetails;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.ChartcenterEncounter;
import com.glenwood.glaceemr.server.application.models.ChartcenterEncounter_;
import com.glenwood.glaceemr.server.application.models.Cpt_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.IAMeasures;
import com.glenwood.glaceemr.server.application.models.IAMeasures_;
import com.glenwood.glaceemr.server.application.models.MacraConfiguration;
import com.glenwood.glaceemr.server.application.models.MacraConfiguration_;
import com.glenwood.glaceemr.server.application.models.MacraMeasuresRate;
import com.glenwood.glaceemr.server.application.models.MacraMeasuresRate_;
import com.glenwood.glaceemr.server.application.models.MacraProviderConfiguration_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.Prescription_;
import com.glenwood.glaceemr.server.application.models.QualityMeasuresProviderMapping;
import com.glenwood.glaceemr.server.application.models.QualityMeasuresProviderMapping_;
import com.glenwood.glaceemr.server.application.models.ServiceDetail;
import com.glenwood.glaceemr.server.application.models.ServiceDetail_;
import com.glenwood.glaceemr.server.application.models.StaffPinNumberDetails;
import com.glenwood.glaceemr.server.application.models.StaffPinNumberDetails_;
import com.glenwood.glaceemr.server.application.repositories.MacraMeasuresRateRepository;
import com.glenwood.glaceemr.server.application.specifications.QPPPerformanceSpecification;

@Service
@Transactional
public class MUPerformanceRateServiceImpl implements MUPerformanceRateService{

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	MacraMeasuresRateRepository measuresRepo;
	
	public List<Integer> getPatientsSeenByDos(int providerId, Date startDate, Date endDate){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object> cq = builder.createQuery(Object.class);
        Root<ServiceDetail> root = cq.from(ServiceDetail.class);

        List<Integer> patients=new ArrayList<Integer>();
        
        cq.distinct(true);
        cq.select(root.get(ServiceDetail_.serviceDetailPatientid));
        
        if(endDate == null){
        	
        	cq.where(builder.and(builder.equal(builder.function("DATE", Date.class, root.get(ServiceDetail_.serviceDetailDos)), startDate),
        		builder.equal(root.get(ServiceDetail_.serviceDetailSdoctorid), providerId)));
        	
        }else{
        	
        	cq.where(builder.and(builder.between(builder.function("DATE", Date.class, root.get(ServiceDetail_.serviceDetailDos)), startDate, endDate),
            		builder.equal(root.get(ServiceDetail_.serviceDetailSdoctorid), providerId)));
        	
        }
        
        List<Object> result = em.createQuery(cq).getResultList();
        
        for(int i=0;i<result.size();i++){
            patients.add(Integer.parseInt(result.get(i).toString()));
        }
        System.out.println("patient seen by dos size>>>>>>>>>>>>>>>>>>"+result.size());
        return patients;
		
	}
	
	public List<Integer> getPatientsSeenByEncounter(int providerId, Date startDate, Date endDate){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object> cq = builder.createQuery(Object.class);
        Root<Chart> rootChart = cq.from(Chart.class);

        List<Integer> patients = new ArrayList<Integer>();
        
        Join<Chart,Encounter> joinchartEncounter=rootChart.join(Chart_.encounterTable,JoinType.INNER);
        
        Join<Chart,ChartcenterEncounter> joinChartCenter=rootChart.join(Chart_.chartCenterEncounter,JoinType.INNER);
        cq.distinct(true);
        cq.select(rootChart.get(Chart_.chartPatientid));
        
        Predicate byDate = null, byDate1 = null;
        
        if(endDate == null){
        	
        	byDate = builder.equal(builder.function("DATE", Date.class, joinchartEncounter.get(Encounter_.encounterDate)), startDate);
            joinchartEncounter.on(byDate);
            
            byDate1 = builder.equal(builder.function("DATE", Date.class, joinChartCenter.get(ChartcenterEncounter_.encounterDate)), startDate);
            joinChartCenter.on(byDate1);
        	
        }else{
        	
        	byDate = builder.between(builder.function("DATE", Date.class, joinchartEncounter.get(Encounter_.encounterDate)), startDate, endDate);
            joinchartEncounter.on(byDate);
            
            byDate1 = builder.between(builder.function("DATE", Date.class, joinChartCenter.get(ChartcenterEncounter_.encounterDate)), startDate, endDate);
            joinChartCenter.on(byDate1);
        	
        }
        
        cq.where(builder.equal(joinchartEncounter.get(Encounter_.encounterType),1),
        		builder.equal(joinchartEncounter.get(Encounter_.encounter_service_doctor),providerId));
        
        List<Object> result = em.createQuery(cq).getResultList();
        System.out.println("patient seen by encounter size>>>>>>>>>>>>>>>>>>>>>>>."+result.size());
        for(int i=0;i<result.size();i++){
            patients.add(Integer.parseInt(result.get(i).toString()));
        }
        
        return patients;
		
	}
	
	@Override
	public List<Integer> getPatientsSeen(int providerId, Date startDate, Date endDate) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object> cq = builder.createQuery(Object.class);
        Root<PatientRegistration> root = cq.from(PatientRegistration.class);
        
        List<Integer> patientsList = new ArrayList<Integer>();

        List<Integer> patientsSeenByDos = new ArrayList<Integer>();
        List<Integer> patientsSeenByEncounter = new ArrayList<Integer>();
        
        if(endDate == null){
        	patientsSeenByDos = getPatientsSeenByDos(providerId, startDate, null);
            patientsSeenByEncounter = getPatientsSeenByEncounter(providerId, startDate, null);
        }else{
        	patientsSeenByDos = getPatientsSeenByDos(providerId, startDate, endDate);
            patientsSeenByEncounter = getPatientsSeenByEncounter(providerId, startDate, endDate);
        }
        
        if(patientsSeenByDos.size() > 0 && patientsSeenByEncounter.size() > 0){
        	cq.where(builder.or(root.get(PatientRegistration_.patientRegistrationId).in(patientsSeenByDos),
            		root.get(PatientRegistration_.patientRegistrationId).in(patientsSeenByEncounter)));
        }else if(patientsSeenByDos.size() > 0 && patientsSeenByEncounter.size() <= 0){
        	cq.where(root.get(PatientRegistration_.patientRegistrationId).in(patientsSeenByDos));
        }else if(patientsSeenByDos.size() <= 0 && patientsSeenByEncounter.size() > 0){
        	cq.where(root.get(PatientRegistration_.patientRegistrationId).in(patientsSeenByEncounter));
        }
        
        if(patientsSeenByDos.size() > 0 || patientsSeenByEncounter.size() > 0){
        	
        	cq.distinct(true);
            cq.select(root.get(PatientRegistration_.patientRegistrationId));
            
            List<Object> result = em.createQuery(cq).getResultList();
            System.out.println("final patient seen size is>>>>>>>>>>>>>>>>>"+result.size());
            for(int i=0;i<result.size();i++){
            	patientsList.add(Integer.parseInt(result.get(i).toString()));
            }
            
        }
        
        return patientsList;
        
	}
	
	@Override
	public void addToMacraMeasuresRate(Integer providerId, List<MIPSPerformanceBean> providerPerformance, int reportingYear,
			Date startDate, Date endDate, boolean isACI) {
		
		MacraMeasuresRate measureRate = new MacraMeasuresRate();
		Date d = new Date();
		Timestamp curr_time = new Timestamp(d.getTime());
		
		for(int i=0;i<providerPerformance.size();i++){
			
			MIPSPerformanceBean performanceByMeasure = providerPerformance.get(i);

			measureRate = measuresRepo.findOne(Specifications.where(QPPPerformanceSpecification.isRecordExisting(providerId, reportingYear, startDate, endDate, performanceByMeasure.getMeasureId(), performanceByMeasure.getCriteria())));
			
			if(measureRate == null){
			
				measureRate = new MacraMeasuresRate();
				
			}
			if(performanceByMeasure.getCmsId()!=null && !performanceByMeasure.getCmsId().equals("N/A"))
			measureRate.setMacraMeasuresRateCMSId(performanceByMeasure.getCmsId());
			measureRate.setMacraMeasuresRateMeasureId(performanceByMeasure.getMeasureId());
			measureRate.setMacraMeasuresRateCriteria(performanceByMeasure.getCriteria());
			measureRate.setMacraMeasuresRateMeasureType(1);
			measureRate.setMacraMeasuresRatePerformance(performanceByMeasure.getPerformanceRate());
			measureRate.setMacraMeasuresRatePeriodEnd(endDate);
			measureRate.setMacraMeasuresRatePeriodStart(startDate);
			measureRate.setMacraMeasuresRateProviderId(providerId);
			measureRate.setMacraMeasuresRateReporting(performanceByMeasure.getReportingRate());
			measureRate.setMacraMeasuresRateReportingYear(reportingYear);
			
			measureRate.setMacraMeasuresRateIpp(Integer.parseInt(performanceByMeasure.getIppCount()+""));
			measureRate.setMacraMeasuresRateIpplist(performanceByMeasure.getIppPatientsList());
			
			measureRate.setMacraMeasuresRateDenominator(Integer.parseInt(performanceByMeasure.getDenominatorCount()+""));
			measureRate.setMacraMeasuresRateDenominatorlist(performanceByMeasure.getDenominatorPatientsList());

			measureRate.setMacraMeasuresRateDenominatorExclusion(Integer.parseInt(performanceByMeasure.getDenominatorExclusionCount()+""));
			measureRate.setMacraMeasuresRateDenominatorExclusionlist(performanceByMeasure.getDenominatorExclusionPatientsList());
			
			measureRate.setMacraMeasuresRateNumerator(Integer.parseInt(performanceByMeasure.getNumeratorCount()+""));
			measureRate.setMacraMeasuresRateNumeratorlist(performanceByMeasure.getNumeratorPatientsList());
			
			measureRate.setMacraMeasuresRateDenominatorException(Integer.parseInt(performanceByMeasure.getDenominatorExceptionCount()+""));
			measureRate.setMacraMeasuresRateDenominatorExceptionlist(performanceByMeasure.getDenominatorExceptionPatientsList());
			
			measureRate.setMacraMeasuresRateNumeratorExclusion(Integer.parseInt(performanceByMeasure.getNumeratorExclusionCount()+""));
			measureRate.setMacraMeasuresRateNumeratorExclusionlist(performanceByMeasure.getNumeratorExclusionPatientsList());
			
			measureRate.setMacraMeasuresRateNpi(getNPIForProvider(providerId));
			measureRate.setMacraMeasuresRateTin(getTINForProvider(providerId));
			
			if(isACI){
				measureRate.setMacraMeasuresRatePoints(performanceByMeasure.getPoints());
			}
			
			d = new Date();
			curr_time = new Timestamp(d.getTime());
			
			measureRate.setMacraMeasuresRateUpdatedOn(curr_time);
			
			measuresRepo.saveAndFlush(measureRate);
			
		}
		
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

	@Override
	public String getLastUpdatedDate() {
		Query query = em.createNativeQuery(" select to_char(max(macra_measures_rate_updated_on),'mm/dd/yyyy hh12:mm:ss AM') from macra_measures_rate");
		return (String) query.getSingleResult();
	}

	@Override
	public String getCompleteQPPJSON(int reportingYear, int providerId, List<MacraProviderQDM> providerInfo,List<MIPSPerformanceBean> attestationMeasures,String sharedPath) {
		QPPDetails completeQPPDetails=new QPPDetails();
		MeasureDetails qualityMeasures,aciMeasures,iaMeasures=null;
		completeQPPDetails.setEntityType(getEntityType(reportingYear));
		completeQPPDetails.setTaxpayerIdentificationNumber(getTINForProvider(providerId));
		completeQPPDetails.setNationalProviderIdentifier(getNPIForProvider(providerId));
		completeQPPDetails.setPerformanceYear(reportingYear);
		
		qualityMeasures=getQualityMeasuresStatus(reportingYear,providerId,providerInfo);
		if(qualityMeasures!=null)
		completeQPPDetails.setMeasurementSets(qualityMeasures);
		
		aciMeasures=getACIMeasuresStatus(reportingYear,providerId,providerInfo,attestationMeasures);
		if(aciMeasures!=null)
		completeQPPDetails.getMeasurementSets().add(aciMeasures);
		
		iaMeasures=getIAMeasureStatus(providerId, reportingYear,providerInfo);
		if(iaMeasures!=null)
		completeQPPDetails.getMeasurementSets().add(iaMeasures);
		
		return generateJSONFile(completeQPPDetails,sharedPath,providerId); 
	}

	@Override
	public String getQualityJSON(int reportingYear, int providerId,List<MacraProviderQDM> providerInfo, String sharedPath) {
		QPPDetails completeQPPDetails=new QPPDetails();
		completeQPPDetails.setEntityType(getEntityType(reportingYear));
		completeQPPDetails.setTaxpayerIdentificationNumber(getTINForProvider(providerId));
		completeQPPDetails.setNationalProviderIdentifier(getNPIForProvider(providerId));
		completeQPPDetails.setPerformanceYear(reportingYear);
		completeQPPDetails.setMeasurementSets(getQualityMeasuresStatus(reportingYear,providerId,providerInfo));
		
		return generateJSONFile(completeQPPDetails,sharedPath,providerId); 
	}

	@Override
	public String getACIJSON(int reportingYear, int providerId,List<MacraProviderQDM> providerInfo,List<MIPSPerformanceBean> attestationMeasures, String sharedPath) {
		QPPDetails completeQPPDetails=new QPPDetails();
		completeQPPDetails.setEntityType(getEntityType(reportingYear));
		completeQPPDetails.setTaxpayerIdentificationNumber(getTINForProvider(providerId));
		completeQPPDetails.setNationalProviderIdentifier(getNPIForProvider(providerId));
		completeQPPDetails.setPerformanceYear(reportingYear);
		completeQPPDetails.getMeasurementSets().add(getACIMeasuresStatus(reportingYear,providerId,providerInfo,attestationMeasures));
		
		return generateJSONFile(completeQPPDetails,sharedPath,providerId); 
	}
	
	private String generateJSONFile(QPPDetails completeQPPDetails,String sharedPath, int providerId) {
		
		String filePath=sharedPath+File.separator+"QPPJSONs";
		File QPPJSONsFolder=new File(filePath);
		QPPJSONsFolder.mkdir();
		ObjectMapper mapper = new ObjectMapper();
		
		String fileName=getProviderName(providerId);
		File file=new File(filePath+File.separator+fileName+".json");
		try {
			file.createNewFile();
			mapper.writeValue(file, completeQPPDetails);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return fileName;
	}

	private MeasureDetails getACIMeasuresStatus(int reportingYear, int providerId,List<MacraProviderQDM> providerInfo,List<MIPSPerformanceBean> attestationMeasures) {
		MeasureDetails measureDetails = new MeasureDetails();
		measureDetails.setCategory("aci");
		if(providerInfo.get(0).getMacraProviderConfigurationReportingMethod()==2)
			measureDetails.setSubmissionMethod("electronicHealthRecord");
		else if(providerInfo.get(0).getMacraProviderConfigurationReportingMethod()==3)
			measureDetails.setSubmissionMethod("registry");
		else
			measureDetails.setSubmissionMethod("claims");
		measureDetails.setPerformanceStart(providerInfo.get(0).getMacraProviderConfigurationReportingStart());
		measureDetails.setPerformanceEnd(providerInfo.get(0).getMacraProviderConfigurationReportingEnd());
		
		List<HashMap<String, Object>> status=getACIPerformanceCount(attestationMeasures,reportingYear,providerId);
		measureDetails.setMeasurements(status);
		if(status.size()>0)
			return measureDetails;
			else
				return null;
	}

	private List<HashMap<String, Object>> getACIPerformanceCount(List<MIPSPerformanceBean> attestationMeasures,int reportingYear,int providerId) {
		
		String aciTransMeasures = "ACI_TRANS_EP_1,ACI_TRANS_SM_1,ACI_TRANS_PEA_1,ACI_TRANS_PEA_2,ACI_TRANS_HIE_1,ACI_TRANS_PSE_1,ACI_TRANS_MR_1";
		
		List<HashMap<String, Object>> measurementList=new ArrayList<HashMap<String, Object>>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<MIPSPerformanceBean> cq = builder.createQuery(MIPSPerformanceBean.class);
		Root<MacraMeasuresRate> root = cq.from(MacraMeasuresRate.class);
		Selection[] selections= new Selection[] {
				root.get(MacraMeasuresRate_.macraMeasuresRateMeasureId),
				root.get(MacraMeasuresRate_.macraMeasuresRateDenominator),
				root.get(MacraMeasuresRate_.macraMeasuresRateNumerator)
				
		};
		cq.select(builder.construct(MIPSPerformanceBean.class,selections));
		cq.where(builder.equal(root.get(MacraMeasuresRate_.macraMeasuresRateReportingYear), reportingYear),builder.equal(root.get(MacraMeasuresRate_.macraMeasuresRateProviderId), providerId),root.get(MacraMeasuresRate_.macraMeasuresRateMeasureId).in(Arrays.asList(aciTransMeasures.split(","))));
		List<MIPSPerformanceBean> result=em.createQuery(cq).getResultList();
		//Add few default JSON details
		if(result.size()>0)
		{
			measurementList.add(getDefaultACIParts("ACI_INFBLO_1"));
			measurementList.add(getDefaultACIParts("ACI_ONCDIR_1"));
			for(MIPSPerformanceBean eachAttestationMeasure:attestationMeasures){
				measurementList.add(getDefaultACIParts(eachAttestationMeasure.getMeasureId()));
			}
			
		}
		for(MIPSPerformanceBean eachObject:result)
		{
			
			HashMap<String, Object> measurement=new HashMap<String, Object>();
			
			if(eachObject.getMeasureId().trim().equals("ACI_TRANS_HIE_1") && eachObject.getDenominatorCount()<100)
			{
				measurement.put("measureId","ACI_TRANS_LVOTC_1");
				measurement.put("value", true);
			}
			else if(eachObject.getMeasureId().trim().equals("ACI_TRANS_EP_1") && eachObject.getDenominatorCount()<100)
			{
				measurement.put("measureId","ACI_TRANS_LVPP_1");
				measurement.put("value", true);
			}
			else
			{	
				measurement.put("measureId", eachObject.getMeasureId());
				HashMap<String, Object> value=new HashMap<String, Object>();
				value.put("denominator", eachObject.getDenominatorCount());
				value.put("numerator", eachObject.getNumeratorCount());
				measurement.put("value", value);
			}
			measurementList.add(measurement);
		}
		return measurementList;
		
	}
	private HashMap<String, Object> getDefaultACIParts(String measureId){
		HashMap<String, Object> measurement=new HashMap<String, Object>();
		measurement.put("measureId",measureId);
		measurement.put("value", true);
		return measurement;
	}
	private MeasureDetails getQualityMeasuresStatus(int reportingYear,int providerId,List<MacraProviderQDM> providerInfo) {
		MeasureDetails measureDetails = new MeasureDetails();
		measureDetails.setCategory("quality");
		if(providerInfo.get(0).getMacraProviderConfigurationReportingMethod()==2)
			measureDetails.setSubmissionMethod("electronicHealthRecord");
		else if(providerInfo.get(0).getMacraProviderConfigurationReportingMethod()==3)
			measureDetails.setSubmissionMethod("registry");
		else
			measureDetails.setSubmissionMethod("claims");
		measureDetails.setPerformanceStart(providerInfo.get(0).getMacraProviderConfigurationReportingStart());
		measureDetails.setPerformanceEnd(providerInfo.get(0).getMacraProviderConfigurationReportingEnd());
		List<HashMap<String, Object>> status=getQualityPerformanceCount(reportingYear,providerId,providerInfo.get(0).getMeasures());
		measureDetails.setMeasurements(status);
		if(status.size()>0)
			return measureDetails;
			else
				return null;
	}

	private MeasureDetails getIAMeasureStatus(int reportingYear,int providerId,List<MacraProviderQDM> providerInfo) {
		MeasureDetails measureDetails = new MeasureDetails();
		measureDetails.setCategory("ia");
		if(providerInfo.get(0).getMacraProviderConfigurationReportingMethod()==2)
			measureDetails.setSubmissionMethod("electronicHealthRecord");
		else if(providerInfo.get(0).getMacraProviderConfigurationReportingMethod()==3)
			measureDetails.setSubmissionMethod("registry");
		else
			measureDetails.setSubmissionMethod("claims");
		measureDetails.setPerformanceStart(providerInfo.get(0).getMacraProviderConfigurationReportingStart());
		measureDetails.setPerformanceEnd(providerInfo.get(0).getMacraProviderConfigurationReportingEnd());
		List<HashMap<String, Object>> status=getIAMeasureCount(reportingYear,providerId);
		measureDetails.setMeasurements(status);
		if(status.size()>0)
		return measureDetails;
		else
			return null;
	}
	
	private List<HashMap<String, Object>> getQualityPerformanceCount(int reportingYear,int providerId,String configuredMeasures) {
		List<HashMap<String, Object>> measurementList=new ArrayList<HashMap<String, Object>>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<MIPSPerformanceBean> cq = builder.createQuery(MIPSPerformanceBean.class);
		Root<MacraMeasuresRate> root = cq.from(MacraMeasuresRate.class);
		Selection[] selections= new Selection[] {
				root.get(MacraMeasuresRate_.macraMeasuresRateMeasureId),
				root.get(MacraMeasuresRate_.macraMeasuresRateDenominator),
				root.get(MacraMeasuresRate_.macraMeasuresRateNumerator),
				root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorException),
				root.get(MacraMeasuresRate_.macraMeasuresRateDenominatorExclusion),
				root.get(MacraMeasuresRate_.macraMeasuresRatePerformance)
				
		};
		cq.select(builder.construct(MIPSPerformanceBean.class,selections));
		cq.where(builder.equal(root.get(MacraMeasuresRate_.macraMeasuresRateReportingYear), reportingYear),builder.equal(root.get(MacraMeasuresRate_.macraMeasuresRateProviderId), providerId),root.get(MacraMeasuresRate_.macraMeasuresRateMeasureId).in(Arrays.asList(configuredMeasures.split(","))));
		List<MIPSPerformanceBean> result=em.createQuery(cq).getResultList();
		for(MIPSPerformanceBean eachObject:result)
		{
			HashMap<String, Object> measurement=new HashMap<String, Object>();
			int length = eachObject.getMeasureId().trim().length();
			if(length==1)
				eachObject.setMeasureId("00"+eachObject.getMeasureId());
			else if(length==2)
				eachObject.setMeasureId("0"+eachObject.getMeasureId());
				
			measurement.put("measureId", eachObject.getMeasureId());
			
			HashMap<String, Object> value=new HashMap<String, Object>();
			value.put("isEndToEndReported", true);
			value.put("performanceMet", eachObject.getNumeratorCount());
			value.put("eligiblePopulationExclusion", eachObject.getDenominatorExclusionCount());
			value.put("eligiblePopulationException", eachObject.getDenominatorExceptionCount());
			value.put("performanceNotMet", (eachObject.getDenominatorCount()-eachObject.getDenominatorExclusionCount()-eachObject.getDenominatorExceptionCount())-eachObject.getNumeratorCount());
			value.put("eligiblePopulation", eachObject.getDenominatorCount());
			value.put("performanceRate", eachObject.getPerformanceRate());
			value.put("reportingRate", new Integer(100));
			measurement.put("value", value);
			measurementList.add(measurement);
		}
		return measurementList;
		
	}

	public List<HashMap<String, Object>> getIAMeasureCount(Integer providerId, Integer year)
	{
		List<HashMap<String, Object>> measurementList=new ArrayList<HashMap<String, Object>>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
        Root<QualityMeasuresProviderMapping> root1 = cq.from(QualityMeasuresProviderMapping.class);
        Join< QualityMeasuresProviderMapping,IAMeasures> joinIAMeasures = root1.join(QualityMeasuresProviderMapping_.iaMeasures,JoinType.LEFT);
        Predicate byProvider=builder.equal(root1.get(QualityMeasuresProviderMapping_.qualityMeasuresProviderMappingProviderId),providerId);
        Predicate byYear = builder.equal(root1.get(QualityMeasuresProviderMapping_.qualityMeasuresProviderMappingReportingYear), year);
        Predicate byIaYear=builder.equal(joinIAMeasures.get(IAMeasures_.IaMeasuresReportingYear),year);
        Predicate byMeasureId=builder.like(root1.get(QualityMeasuresProviderMapping_.qualityMeasuresProviderMappingMeasureId),"IA_%");
       
        joinIAMeasures.on(byIaYear);
        
        cq.multiselect(root1.get(QualityMeasuresProviderMapping_.qualityMeasuresProviderMappingMeasureId),builder.coalesce(joinIAMeasures.get(IAMeasures_.IaMeasuresStatus),false));
        cq.where(byProvider,byYear,byMeasureId);
		List<Object[]> result=em.createQuery(cq).getResultList();
		for(int i=0;i<result.size();i++){
			HashMap<String, Object> measurement=new HashMap<String, Object>();
			measurement.put("measureId", result.get(i)[0]);
			measurement.put("value", result.get(i)[1]);
			measurementList.add(measurement);
		}
		return measurementList;
	}
	
	private String getEntityType(int reportingYear) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = builder.createQuery(String.class);
		Root<MacraConfiguration> root = cq.from(MacraConfiguration.class);
		cq.select(builder.selectCase().when(builder.equal(root.get(MacraConfiguration_.macraConfigurationType), 0), "individual").otherwise("group").as(String.class));

		cq.where(builder.equal(root.get(MacraConfiguration_.macraConfigurationYear), reportingYear));
		
		return em.createQuery(cq).getResultList().get(0);
	}
	public String getProviderName(Integer provider) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<EmployeeProfile> rootEmployeeProfile = cq.from(EmployeeProfile.class);
		cq.select(rootEmployeeProfile.get(EmployeeProfile_.empProfileFullname));
		cq.where(builder.equal(rootEmployeeProfile.get(EmployeeProfile_.empProfileEmpid),provider));
		Query query=em.createQuery(cq);
		String providerName=(String) query.getSingleResult();
		return providerName+"_"+System.currentTimeMillis();

	}

	
}
