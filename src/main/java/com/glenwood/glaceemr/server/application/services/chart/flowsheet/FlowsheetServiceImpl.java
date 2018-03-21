package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.Subquery;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.ChartStatus;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions;
import com.glenwood.glaceemr.server.application.models.CoreClassHierarchy;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.Flowsheet;
import com.glenwood.glaceemr.server.application.models.FlowsheetClinicalParam;
import com.glenwood.glaceemr.server.application.models.FlowsheetClinicalParam_;
import com.glenwood.glaceemr.server.application.models.FlowsheetDrug;
import com.glenwood.glaceemr.server.application.models.FlowsheetDx;
import com.glenwood.glaceemr.server.application.models.FlowsheetLab;
import com.glenwood.glaceemr.server.application.models.FlowsheetParam;
import com.glenwood.glaceemr.server.application.models.FlowsheetParam_;
import com.glenwood.glaceemr.server.application.models.FlowsheetType;
import com.glenwood.glaceemr.server.application.models.FlowsheetType_;
import com.glenwood.glaceemr.server.application.models.Flowsheet_;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTest;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTest_;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTestmapping;
import com.glenwood.glaceemr.server.application.models.Hl7ExternalTestmapping_;
import com.glenwood.glaceemr.server.application.models.HmrCategories;
import com.glenwood.glaceemr.server.application.models.HmrCategories_;
import com.glenwood.glaceemr.server.application.models.HmrCategoryUrl;
import com.glenwood.glaceemr.server.application.models.HmrCategoryUrl_;
import com.glenwood.glaceemr.server.application.models.HmrGroups;
import com.glenwood.glaceemr.server.application.models.HmrGroups_;
import com.glenwood.glaceemr.server.application.models.HmrPatientinstance;
import com.glenwood.glaceemr.server.application.models.HmrPatientinstance_;
import com.glenwood.glaceemr.server.application.models.HmrPatientinstanceparameters;
import com.glenwood.glaceemr.server.application.models.HmrPatientinstanceparameters_;
import com.glenwood.glaceemr.server.application.models.HmrTestDetail;
import com.glenwood.glaceemr.server.application.models.HmrTestDetail_;
import com.glenwood.glaceemr.server.application.models.HmrTests;
import com.glenwood.glaceemr.server.application.models.HmrTests_;
import com.glenwood.glaceemr.server.application.models.LabDescpParameters;
import com.glenwood.glaceemr.server.application.models.LabDescription;
import com.glenwood.glaceemr.server.application.models.LabDescription_;
import com.glenwood.glaceemr.server.application.models.LabEntries;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter_;
import com.glenwood.glaceemr.server.application.models.LabEntries_;
import com.glenwood.glaceemr.server.application.models.LabParameterCode;
import com.glenwood.glaceemr.server.application.models.LabParameterCode_;
import com.glenwood.glaceemr.server.application.models.LabParameters;
import com.glenwood.glaceemr.server.application.models.LabParameters_;
import com.glenwood.glaceemr.server.application.models.LabStandardCode;
import com.glenwood.glaceemr.server.application.models.LabStandardCode_;
import com.glenwood.glaceemr.server.application.models.LabStandardGroup;
import com.glenwood.glaceemr.server.application.models.LabStandardGroup_;
import com.glenwood.glaceemr.server.application.models.MedStatus;
import com.glenwood.glaceemr.server.application.models.OverrideAlertsBean;
import com.glenwood.glaceemr.server.application.models.Overridealerts;
import com.glenwood.glaceemr.server.application.models.Overridealerts_;
import com.glenwood.glaceemr.server.application.models.ParamStandardCode;
import com.glenwood.glaceemr.server.application.models.ParamStandardCode_;
import com.glenwood.glaceemr.server.application.models.ParamStandardGroup;
import com.glenwood.glaceemr.server.application.models.ParamStandardGroup_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.Prescription;
import com.glenwood.glaceemr.server.application.models.ProblemList;
import com.glenwood.glaceemr.server.application.models.ProblemList_;
import com.glenwood.glaceemr.server.application.models.VaccineOrderDetails_;
import com.glenwood.glaceemr.server.application.models.VaccineReport;
import com.glenwood.glaceemr.server.application.models.VaccineReport_;
import com.glenwood.glaceemr.server.application.models.VitalsParameter;
import com.glenwood.glaceemr.server.application.repositories.ChartRepository;
import com.glenwood.glaceemr.server.application.repositories.ChartStatusRepository;
import com.glenwood.glaceemr.server.application.repositories.ClinicalElementsOptionsRepository;
import com.glenwood.glaceemr.server.application.repositories.ClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.CoreClassHierarchyRepository;
import com.glenwood.glaceemr.server.application.repositories.CurrentMedicationRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterEntityRepository;
import com.glenwood.glaceemr.server.application.repositories.FlowsheetClinicalParamRepository;
import com.glenwood.glaceemr.server.application.repositories.FlowsheetDrugRepository;
import com.glenwood.glaceemr.server.application.repositories.FlowsheetDxRepository;
import com.glenwood.glaceemr.server.application.repositories.FlowsheetLabRepository;
import com.glenwood.glaceemr.server.application.repositories.FlowsheetParamRepository;
import com.glenwood.glaceemr.server.application.repositories.FlowsheetRepository;
import com.glenwood.glaceemr.server.application.repositories.FlowsheetTypeRepository;
import com.glenwood.glaceemr.server.application.repositories.IcdmRepository;
import com.glenwood.glaceemr.server.application.repositories.LabDescpParametersRepository;
import com.glenwood.glaceemr.server.application.repositories.LabDescriptionRepository;
import com.glenwood.glaceemr.server.application.repositories.LabEntriesParameterRepository;
import com.glenwood.glaceemr.server.application.repositories.LabEntriesRepository;
import com.glenwood.glaceemr.server.application.repositories.LabGroupRepository;
import com.glenwood.glaceemr.server.application.repositories.LabParametersRepository;
import com.glenwood.glaceemr.server.application.repositories.LabStandardCodeRepository;
import com.glenwood.glaceemr.server.application.repositories.LabStandardGroupRepository;
import com.glenwood.glaceemr.server.application.repositories.MedStatusRepository;
import com.glenwood.glaceemr.server.application.repositories.OverridealertsRepository;
import com.glenwood.glaceemr.server.application.repositories.ParamStandardCodeRepository;
import com.glenwood.glaceemr.server.application.repositories.ParamStandardGroupRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalHistoryRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.repositories.PrescriptionRepository;
import com.glenwood.glaceemr.server.application.repositories.VaccineReportRepository;
import com.glenwood.glaceemr.server.application.repositories.VitalsParameterRepository;
import com.glenwood.glaceemr.server.application.services.chart.prescription.PrescriptionServiceBean;
import com.glenwood.glaceemr.server.application.services.chart.prescription.PrescriptionServiceImpl;
import com.glenwood.glaceemr.server.application.specifications.ChartSpecification;
import com.glenwood.glaceemr.server.application.specifications.ClinicalElementsSpecification;
import com.glenwood.glaceemr.server.application.specifications.EncounterEntitySpecification;
import com.glenwood.glaceemr.server.application.specifications.FlowsheetSpecification;
import com.glenwood.glaceemr.server.application.specifications.IcdmSpecification;
import com.glenwood.glaceemr.server.application.specifications.InitialSettingsSpecification;
import com.glenwood.glaceemr.server.application.specifications.InvestigationSpecification;
import com.glenwood.glaceemr.server.application.specifications.OverridealertsSpecification;
import com.glenwood.glaceemr.server.application.specifications.PatientClinicalElementsSpecification;
import com.glenwood.glaceemr.server.application.specifications.PrescripitonSpecification;
import com.glenwood.glaceemr.server.application.specifications.VaccineReportSpecification;
import com.glenwood.glaceemr.server.application.specifications.VitalsSpecification;
import com.glenwood.glaceemr.server.utils.DateUtil;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.google.common.base.Functions;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * This contains the logic to get the flowsheet data
 * @author smita
 *
 */
@Service
public class FlowsheetServiceImpl implements FlowsheetService{

	@Autowired
	FlowsheetTypeRepository flowsheetTypeRepository;

	@Autowired
	FlowsheetRepository flowsheetRepository;

	@Autowired
	FlowsheetDxRepository flowsheetDxRepository;

	@Autowired
	FlowsheetLabRepository flowsheetLabRepository;

	@Autowired
	FlowsheetParamRepository flowsheetParamRepository;

	@Autowired
	FlowsheetClinicalParamRepository flowsheetClinicalParamRepository;

	@Autowired
	FlowsheetDrugRepository flowsheetDrugRepository;

	@Autowired
	VitalsParameterRepository vitalsParameterRepository;

	@Autowired
	LabParametersRepository labParametersRepository;

	@Autowired
	LabGroupRepository labGroupRepository;

	@Autowired
	CoreClassHierarchyRepository coreClassHierarchyRepository;

	@Autowired
	IcdmRepository icdmRepository;

	@Autowired
	ChartRepository chartRepository;

	@Autowired
	LabStandardGroupRepository labStandardGroupRepository;

	@Autowired
	LabStandardCodeRepository labStandardCodeRepository;

	@Autowired
	ParamStandardGroupRepository paramStandardGroupRepository;

	@Autowired
	ParamStandardCodeRepository paramStandardCodeRepository;

	@Autowired
	LabDescriptionRepository labDescriptionRepository;

	@Autowired
	LabEntriesRepository labEntriesRepository;

	@Autowired
	LabEntriesParameterRepository labEntriesParameterRepository;

	@Autowired
	LabDescpParametersRepository labDescpParametersRepository;

	@Autowired
	VaccineReportRepository vaccineReportRepository;

	@Autowired
	OverridealertsRepository overridealertsRepository;

	@Autowired
	PatientRegistrationRepository patientRegistrationRepository;

	@Autowired
	CurrentMedicationRepository currentMedicationRepository;

	@Autowired
	PrescriptionRepository prescriptionRepository;

	@Autowired
	MedStatusRepository medStatusRepository;

	@Autowired
	PatientClinicalHistoryRepository patientClinicalHistoryRepository;

	@Autowired
	PatientClinicalElementsRepository patientClinicalElementsRepository;

	@Autowired
	ClinicalElementsRepository clinicalElementsRepository;

	@Autowired
	EncounterEntityRepository encounterEntityRepository;

	@Autowired
	ChartStatusRepository h068Repository;

	@Autowired
	ClinicalElementsOptionsRepository clinicalElementsOptionsRepository;

	@PersistenceContext
	EntityManager em;

	private Logger logger = Logger.getLogger(FlowsheetServiceImpl.class);

	public static final String GlenwoodSystemsOID	=	"2.16.840.1.113883.3.225";
	public final static String LoincOID				=	"2.16.840.1.113883.6.1";
	public final static String SnomedOID				=	"2.16.840.1.113883.6.96";
	public static int LOAD_VITALS_DATA = 1;
	public static int LOAD_CLINICAL_ELEMENTS_DATA = 2;
	ArrayList<String> paramPerformedDate = new ArrayList<String> ();
	ArrayList<String> clinicalVitalParamDate = new ArrayList<String> ();

	/**
	 * Specification to get the list of flowsheets with details based on flowsheet type
	 * @param flowsheetType
	 * @return List<Flowsheet>
	 */
	@Override
	public List<Flowsheet> getFlowsheetPatientData(Integer flowsheetType) {
		//logger.debug("Request to get the flowsheet list based on type:Inside service Impl");
		List<Flowsheet> listofFlowsheet=flowsheetRepository.findAll(FlowsheetSpecification.flowsheetList(flowsheetType));
		//logger.debug("Request to get the flowsheet list based on type:Obtained data from DB");
		return listofFlowsheet;
	}

	@Override
	public FS_Management getFlowsheetsManagementDetails(Integer loadFromFlowsheet,Integer flowsheetId,Integer flowsheetTypeId){
		//logger.debug("Request to get the flowsheet basic details for configuration:Inside service Impl");
		FS_Management flowsheetManagement=new FS_Management();
		flowsheetManagement.setFlowsheetType(flowsheetTypeRepository.findAll(FlowsheetSpecification.flowsheetTypeIsactive(true)));
		flowsheetManagement.setVitalsParameter(vitalsParameterRepository.findAll(VitalsSpecification.vitalParametersIsActiveDistinct(true)));
		flowsheetManagement.setLabParameters(labParametersRepository.findAll(Specifications.where(InvestigationSpecification.labParametersIsActive(true)).and(InvestigationSpecification.labParametersNotEmptyDistinct())));
		flowsheetManagement.setLabGroups(labGroupRepository.findAll(InvestigationSpecification.labGroupIsActiveDistinct(true)));
		flowsheetManagement.setCoreClassHierarchy(coreClassHierarchyRepository.findAll(PrescripitonSpecification.drugClasses()));
		if(loadFromFlowsheet==1){
			flowsheetManagement.setLoadFromFlowsheet(loadFromFlowsheet);
			flowsheetManagement.setFlowsheetId(flowsheetId);
			flowsheetManagement.setFlowsheetTypeId(flowsheetTypeId);
		}
		//logger.debug("Request to get the flowsheet basic details for configuration:Obtained data from DB");
		return flowsheetManagement;
	}

	@Override
	public FS_ConfiguredDetails getFlowsheetsConfiguredDetails(Integer flowsheetId) {
		FS_ConfiguredDetails flowsheetConfiguredDetails=new FS_ConfiguredDetails();
		List<FlowsheetClinicalParam> flowsheetVitals=flowsheetClinicalParamRepository.findAll(FlowsheetSpecification.flowsheetClinicalParam(FlowsheetServiceImpl.LOAD_VITALS_DATA, flowsheetId));
		List<VitalsParameter> flowsheetVitalParameter=new ArrayList<VitalsParameter>();
		List<String> gwidsVitals=new ArrayList<String>();
		for(int i=0;i<flowsheetVitals.size();i++){
			gwidsVitals.add(Optional.fromNullable(flowsheetVitals.get(i).getFlowsheetClinicalParamMapElementgwid()).or("-1"));
		}
		if(gwidsVitals.size()>0)
			flowsheetVitalParameter=vitalsParameterRepository.findAll(VitalsSpecification.vitalsActiveGwId(gwidsVitals));
		flowsheetConfiguredDetails.setVitalsParameter(flowsheetVitalParameter);

		List<FlowsheetClinicalParam> flowsheetClinical=flowsheetClinicalParamRepository.findAll(FlowsheetSpecification.flowsheetClinicalParam(FlowsheetServiceImpl.LOAD_CLINICAL_ELEMENTS_DATA, flowsheetId));
		List<ClinicalElements> flowsheetClinicalElements=new ArrayList<ClinicalElements>();
		List<String> gwidsClinicalElements=new ArrayList<String>();
		for(int i=0;i<flowsheetClinical.size();i++){
			gwidsClinicalElements.add(Optional.fromNullable(flowsheetClinical.get(i).getFlowsheetClinicalParamMapElementgwid()).or("-1"));
		}
		if(gwidsClinicalElements.size()>0)
			flowsheetClinicalElements=clinicalElementsRepository.findAll(ClinicalElementsSpecification.getClinicalElements(gwidsClinicalElements));
		for(int i=0;i<flowsheetClinicalElements.size();i++){
			String gwid=Optional.fromNullable(flowsheetClinicalElements.get(i).getClinicalElementsGwid()).or("-1");
			gwid=gwid.trim();
			String append="";
			if(gwid.length()>2){
				if(gwid.substring(3, 8).contentEquals("01001"))
					append="CC";
				else if(gwid.substring(3, 8).contentEquals("01002"))
					append="HPI";
				else if(gwid.substring(3, 8).contentEquals("01003"))
					append="Hx";
				else if(gwid.substring(3, 8).contentEquals("01004"))
					append="ROS";
				else if(gwid.substring(3, 8).contentEquals("02001"))
					append="PE";
				else if(gwid.substring(3, 5).contentEquals("04"))
					append="Plan";
				else
					append="Others";
			}
			String name=Optional.fromNullable(flowsheetClinicalElements.get(i).getClinicalElementsName()).or("");
			if(!append.contentEquals(""))
				name=append+" - "+name;
			flowsheetClinicalElements.get(i).setClinicalElementsName(name);
		}
		flowsheetConfiguredDetails.setClinicalElements(flowsheetClinicalElements);
		flowsheetConfiguredDetails.setFlowsheetDrug(flowsheetDrugRepository.findAll(FlowsheetSpecification.flowsheetDrugsMapId(flowsheetId)));
		List<FlowsheetLab> flowsheetLabList=flowsheetLabRepository.findAll(FlowsheetSpecification.flowsheetLabMapId(flowsheetId));
		List<FS_LabBean> labBeanList=new ArrayList<FS_LabBean>();
		for(int i=0;i<flowsheetLabList.size();i++){
			FS_LabBean labBeanTemp=new FS_LabBean();
			labBeanTemp.setGroupId(flowsheetLabList.get(i).getFlowsheetLabStandardGroupid());
			LabStandardGroup labStandardGroup=labStandardGroupRepository.findOne(FlowsheetSpecification.labStandardGroup(flowsheetLabList.get(i).getFlowsheetLabStandardGroupid()));
			labBeanTemp.setLabName(labStandardGroup.getLabStandardGroupName());
			labBeanList.add(labBeanTemp);
		}
		flowsheetConfiguredDetails.setLabGroups(labBeanList);
		List<FlowsheetParam> flowsheetParamList=flowsheetParamRepository.findAll(FlowsheetSpecification.flowsheetParamMapId(flowsheetId));
		List<FS_LabParameterBean> paramBeanList=new ArrayList<FS_LabParameterBean>();
		for(int i=0;i<flowsheetParamList.size();i++){
			FS_LabParameterBean paramBeanTemp=new FS_LabParameterBean();
			paramBeanTemp.setGroupId(flowsheetParamList.get(i).getFlowsheetParamStandardGroupid());
			ParamStandardGroup paramStandardGroup=paramStandardGroupRepository.findOne(FlowsheetSpecification.paramStandardGroup(flowsheetParamList.get(i).getFlowsheetParamStandardGroupid()));
			paramBeanTemp.setParamName(paramStandardGroup.getParamStandardGroupName());
			paramBeanList.add(paramBeanTemp);
		}
		flowsheetConfiguredDetails.setLabParameters(paramBeanList);
		flowsheetConfiguredDetails.setFlowsheetDx(flowsheetDxRepository.findAll(FlowsheetSpecification.flowsheetDxMapid(flowsheetId)));
		return flowsheetConfiguredDetails;
	}

	@Override
	public FlowsheetBean getFlowsheetData(Integer flowsheetTypeParam,Integer flowsheetId, Integer patientId,Integer encounterId, Integer chartId) throws Exception {
		FlowsheetBean fBean=new FlowsheetBean();
	//	Integer chartId=-1;
		Integer count=0;
		if(flowsheetTypeParam==-1)
			fBean.setFlowsheetList(getFlowsheetNames(patientId));
		else
			fBean.setFlowsheetList(getFlowsheetNamesList(flowsheetTypeParam));
		if(encounterId!=-1)
			fBean.setChartBased(false);
		else
		{
			//chartId=getChartId(patientId);
						if(chartId!=-1){
				count=getCount(chartId);
				if(count>0){
				encounterId=getlatestEncounterId(chartId);
				}else{
					encounterId=-1;
				}
			}
		
			fBean.setChartBased(true);
		}
		if(flowsheetId!=-1){
			fBean.setFlowsheetId(flowsheetId);
			Flowsheet flowsheet=flowsheetRepository.findOne(Specifications.where(FlowsheetSpecification.flowsheetId(flowsheetId)).and(FlowsheetSpecification.flowsheetIsactive(true)));
			//Flowsheet flowsheet=getFlowsheetType(flowsheetId);
			int flowsheetType=Optional.fromNullable(flowsheet.getFlowsheetType()).or(-1);
			fBean.setFlowsheetType(flowsheetType);
			fBean.setFlowsheetName(Optional.fromNullable(Strings.emptyToNull(flowsheet.getFlowsheetName())).or("").toString());
			List<FlowsheetDx> flowhseetDx=new ArrayList<FlowsheetDx>();
			flowhseetDx=flowsheetDxRepository.findAll(Specifications.where(FlowsheetSpecification.flowsheetDxMapid(flowsheetId)));
			String[] flowsheetIds=new String[flowhseetDx.size()];
			for(int i=0;i<flowhseetDx.size();i++){
				flowsheetIds[i]=flowhseetDx.get(i).getFlowsheetDxCode();
			}
			if(flowsheetIds.length==0){
				flowsheetIds=new String[1];
				flowsheetIds[0]="-2";
			}
			fBean.setDxData(icdmRepository.findAll(Specifications.where(IcdmSpecification.icdmCodesIn(flowsheetIds))));
			String startDate = "";
			int patgender=-1;
			patgender=MoreObjects.firstNonNull(getPatientGender(patientId),-1);
			if((flowsheetType==1)|| (flowsheetType==5)){
				//Preventive Management
				//PatientRegistration patientReg=patientRegistrationRepository.findOne(Specifications.where(PatientRegistrationSpecification.PatientId(patientId+"")));
				//startDate = MoreObjects.firstNonNull(patientReg.getPatientRegistrationDob(), "").toString();
				startDate=getpatientDOb(patientId).toString();
				
				
			} else if(flowsheetType==2){
				//Disease Management
				List<FlowsheetDx> flowsheetDxList=flowsheetDxRepository.findAll(Specifications.where(FlowsheetSpecification.flowsheetDxCodeBasedOnFlowsheetTypeId(2, flowsheetType)));
				ArrayList<String> dxCodes=new ArrayList<String>();
				for(int j=0;j<flowsheetDxList.size();j++){
					dxCodes.add(flowsheetDxList.get(j).getFlowsheetDxCode());
				}
				if(dxCodes.size()>0){
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<Object> cq = builder.createQuery();
				Root<ProblemList> root = cq.from(ProblemList.class);
				Predicate[] restrictions1 = new Predicate[] {
						root.get(ProblemList_.problemListDxCode).in(dxCodes),
						builder.equal(root.get(ProblemList_.problemListPatientId),patientId)
				};
				cq.select(builder.selectCase().when(builder.isNull(root.get(ProblemList_.problemListOnsetDate)), builder.function("to_char", String.class, root.get(ProblemList_.problemListCreatedon),builder.literal("yyyy-MM-dd"))).otherwise(builder.function("to_char", String.class, root.get(ProblemList_.problemListOnsetDate), builder.literal("yyyy-MM-dd"))));
				cq.where(restrictions1);
				try{
					List<Object> rstList=em.createQuery(cq).getResultList();
					if(rstList.size()>0)
						startDate = Optional.fromNullable(rstList.get(0)).or("").toString();
				}catch(Exception e){
					e.printStackTrace();
				}
				}else{
					startDate="";
					}
			}else if(flowsheetType==3){
				//Meaningful Use Measures
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<Object> cq = builder.createQuery();
				cq.select(builder.currentDate()).from(FlowsheetType.class);
				Object rstList=em.createQuery(cq).getSingleResult();
				if(rstList!=null)
					startDate = Optional.fromNullable(rstList).or("").toString();
			}
			fBean.setLabData(formLabData(flowsheetId, startDate, patientId,encounterId,patgender,chartId));
			fBean.setParamData(formLabParameters(flowsheetId, startDate, patientId,encounterId,patgender,chartId));
			fBean.setParamDateArr(paramPerformedDate);
			fBean.setDrugData(formDrugData(patientId,flowsheetId, startDate,encounterId,patgender));
			//			fBean.setNotesData(formNotesData(flowsheetId));
			fBean.setClinicalVitalsData(formClinicalElementsData(flowsheetId, startDate, patientId,encounterId,patgender));
			fBean.setVitalDateArr(clinicalVitalParamDate);
			fBean.setClinicalPlanData(formClinicalData(flowsheetId, startDate, patientId,encounterId,patgender));

			/*if(!startDate.equals("")){
				String[] splitDate = startDate.split("-");
				String onSetFormatDate  = splitDate[1] + "/" + splitDate[2] + "/" + splitDate[0];
				fBean.setOnSetDate(onSetFormatDate);
			}*/
		}
		//logger.debug("Request to get the complete flowsheet details based on flowsheet id and patient Id:Obtained data from DB");
		return fBean;
	}

	/**
	 * Need to get count of entries 
	 * @param chartId
	 * @return
	 */
	
	private Integer getCount(Integer chartId) {
		int count=0;
		CriteriaBuilder cb= em.getCriteriaBuilder();
		CriteriaQuery<Object> cq= cb.createQuery();
		Root<Encounter>root=cq.from(Encounter.class);
		cq.select(cb.count(root.get(Encounter_.encounterId)));
		cq.where(cb.equal(root.get(Encounter_.encounterChartid), chartId));
		try{
			count=  Integer.parseInt("" + em.createQuery(cq).getSingleResult());
		}catch(Exception e){
			e.printStackTrace();
			count=0;
		}
		
	
		return count;
	}
	/**
	 * Method to get the PatientDoB
	 * @param patientId
	 * @return
	 */
	private Date getpatientDOb(Integer patientId) {

		Date dobDt = new Date();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Date> cq = builder.createQuery(Date.class);
		Root<PatientRegistration> root = cq.from(PatientRegistration.class);
		cq.select(root.get(PatientRegistration_.patientRegistrationDob));
		cq.where(builder.equal(root.get(PatientRegistration_.patientRegistrationId), patientId));
		try{
			dobDt= em.createQuery(cq).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return dobDt;
	}


	/**
	 * Method to get the list of flowhseet based on patient Id
	 * @return List<FlowsheetNameBean>
	 */
	@SuppressWarnings("rawtypes")
	public List<FlowsheetNameBean> getFlowsheetNames(Integer patientId){
		//Query 1
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<FlowsheetNameBean> cq = builder.createQuery(FlowsheetNameBean.class);
		Root<FlowsheetDx> root = cq.from(FlowsheetDx.class);
		Join<FlowsheetDx, Flowsheet> join1=root.join("flowsheetTable",JoinType.INNER);
		join1.on(builder.equal(join1.get(Flowsheet_.flowsheetType), 2),builder.equal(join1.get(Flowsheet_.flowsheetIsactive), true));
		Join<Join<FlowsheetDx, Flowsheet>, FlowsheetType> join2=join1.join("flowsheetTypeTable",JoinType.INNER);
		Selection[] selections= new Selection[] { 
				join1.get( Flowsheet_.flowsheetId ),
				join1.get( Flowsheet_.flowsheetType),
				join1.get( Flowsheet_.flowsheetName),
				join2.get( FlowsheetType_.flowsheetTypeName),
				builder.literal("")
		};
		cq.select(builder.construct(FlowsheetNameBean.class,selections));
		cq.distinct(true);
		Predicate[] restrictions = new Predicate[] {
				builder.equal(join1.get( Flowsheet_.flowsheetType ),2),
				builder.equal(join1.get( Flowsheet_.flowsheetIsactive ),true),
		};
		cq.where(restrictions);
		List<FlowsheetNameBean> namesData=new ArrayList<FlowsheetNameBean>();
		try{
			namesData=em.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}

		//Query 2
		CriteriaBuilder builder1 = em.getCriteriaBuilder();
		CriteriaQuery<FlowsheetNameBean> cq1 = builder1.createQuery(FlowsheetNameBean.class);
		Root<Flowsheet> rootA = cq1.from(Flowsheet.class);
		Join<Flowsheet, FlowsheetType> joinA=rootA.join("flowsheetTypeTable",JoinType.INNER);
		Selection[] selections1= new Selection[] { 
				rootA.get( Flowsheet_.flowsheetId ),
				rootA.get( Flowsheet_.flowsheetType),
				rootA.get( Flowsheet_.flowsheetName),
				joinA.get( FlowsheetType_.flowsheetTypeName),
				builder1.literal("")
		};
		cq1.select(builder1.construct(FlowsheetNameBean.class,selections1));
		cq1.distinct(true);
		Predicate[] restrictions1 = new Predicate[] {
				builder1.equal(rootA.get( Flowsheet_.flowsheetType ),1),
				builder1.equal(rootA.get( Flowsheet_.flowsheetIsactive ),true),
		};
		cq1.where(restrictions1);
		List<FlowsheetNameBean> namesData1=new ArrayList<FlowsheetNameBean>();
		try{
			namesData1=em.createQuery(cq1).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		namesData.addAll(namesData1);
		namesData=orderByType(namesData);
		return namesData;
	}

	/**
	 * Method to get the list of flowhseet
	 * @return List<FlowsheetNameBean>
	 */
	@SuppressWarnings("rawtypes")
	public List<FlowsheetNameBean> getFlowsheetNamesList(Integer flowsheetTypeParam){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<FlowsheetNameBean> cq = builder.createQuery(FlowsheetNameBean.class);
		Root<Flowsheet> root = cq.from(Flowsheet.class);
		Join<Flowsheet, FlowsheetType> join=root.join("flowsheetTypeTable",JoinType.INNER);
		Selection[] selections= new Selection[] { 
				root.get( Flowsheet_.flowsheetId ),
				root.get( Flowsheet_.flowsheetType),
				root.get( Flowsheet_.flowsheetName),
				join.get( FlowsheetType_.flowsheetTypeName),
		};
		cq.select(builder.construct(FlowsheetNameBean.class,selections));
		cq.distinct(true);
		Predicate[] restrictions = new Predicate[] {
				builder.equal(root.get( Flowsheet_.flowsheetType ),flowsheetTypeParam),
				builder.equal(root.get( Flowsheet_.flowsheetIsactive ),true),
		};
		cq.where(restrictions);
		List<FlowsheetNameBean> namesData=new ArrayList<FlowsheetNameBean>();
		try{
			namesData=em.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return namesData;
	}

	/**
	 * Method to get the list of lab group ids's configured for the flowsheet
	 * @return List<Integer>
	 */
	/*	public List<Integer> getFlowsheetLabGroupId(Integer flowsheetId, Integer patientId){
		List<Integer> groupIds=new ArrayList<Integer>();
		groupIds.add(-2);
		List<Integer> gender=new ArrayList<Integer>();
		gender.add(5);
		List<FlowsheetLab> flowsheetLabGroupId=new ArrayList<FlowsheetLab>();
		flowsheetLabGroupId=flowsheetLabRepository.findAll(Specifications.where(FlowsheetSpecification.flowsheetLabGenderAndIsActive(gender,patientId)).and(FlowsheetSpecification.flowsheetLabMapId(flowsheetId)));
		for(int i=0;i<flowsheetLabGroupId.size();i++){
			if(i==0)
				groupIds.clear();
			groupIds.add(flowsheetLabGroupId.get(i).getFlowsheetLabStandardGroupid());
		}
		return groupIds;
	}*/
	public List<Integer> getFlowsheetLabGroupId(Integer flowsheetId, Integer patientId){
		CriteriaBuilder builder = em.getCriteriaBuilder();

		List<Integer> groupIds=new ArrayList<Integer>();
		groupIds.add(-2);
		try{
			String query= "select lab_standard_group_id from lab_standard_group inner join flowsheet_lab on flowsheet_lab_standard_groupid=lab_standard_group_id  where flowsheet_lab_mapid ="+flowsheetId+" and lab_standard_group_gender=5 or lab_standard_group_gender in(select patient_registration_sex from patient_registration where patient_registration_id="+patientId+")";
			groupIds.addAll( em.createNativeQuery(query).getResultList() );
		}catch(Exception e){
			e.printStackTrace();
		}
		return groupIds;
	}

	/**
	 * Method to get the list of param group ids's configured for the flowsheet
	 * @return List<Integer>
	 */
	public List<Integer> getFlowsheetParamGroupId(Integer flowsheetId, Integer patientId){
		List<Integer> groupIds=new ArrayList<Integer>();
		groupIds.add(-2);
		List<Integer> gender=new ArrayList<Integer>();
		gender.add(5);
//		List<FlowsheetParam> flowsheetParamGroupId=new ArrayList<FlowsheetParam>();
		//flowsheetParamGroupId=flowsheetParamRepository.findAll(Specifications.where(FlowsheetSpecification.flowsheetParamGenderAndIsActive(gender,patientId)).and(FlowsheetSpecification.flowsheetParamMapId(flowsheetId)));
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object>  cq = builder.createQuery();
		Root<FlowsheetParam> rootFlowsheetParam = cq.from(FlowsheetParam.class);
				
		
		Subquery<Integer> subquery = cq.subquery(Integer.class);
		Root<PatientRegistration> subqueryFrom = subquery.from(PatientRegistration.class);
		Expression<Integer> subQExp=subqueryFrom.get(PatientRegistration_.patientRegistrationSex);
		subquery.select(subQExp);
		subquery.where(builder.equal(subqueryFrom.get(PatientRegistration_.patientRegistrationId), patientId));
		
		Join<FlowsheetParam,ParamStandardGroup> join=rootFlowsheetParam.join("paramStandardGroupTable", JoinType.INNER);
		Predicate FlowsheetParamGenderIsActive = builder.and(builder.or(join.get(ParamStandardGroup_.paramStandardGroupGender).in(gender),builder.in(join.get(ParamStandardGroup_.paramStandardGroupGender)).value(subquery)),builder.equal(join.get(ParamStandardGroup_.paramStandardGroupIsactive),true));
		Predicate flowsheetParamMapId = builder.equal(rootFlowsheetParam.get(FlowsheetParam_.flowsheetParamMapid),flowsheetId);
		cq.select(rootFlowsheetParam.get(FlowsheetParam_.flowsheetParamStandardGroupid)).where(FlowsheetParamGenderIsActive,flowsheetParamMapId);
		cq.distinct(true);
		List<Object> obj=em.createQuery(cq).getResultList();
		
		for(int i=0;i<obj.size();i++){
			int groupid=(int) obj.get(i);
			groupIds.add(groupid);
		}
		
		/*for(int i=0;i<flowsheetParamGroupId.size();i++){
			if(i==0)
				groupIds.clear();
			groupIds.add(flowsheetParamGroupId.get(i).getFlowsheetParamStandardGroupid());
		}*/
		
		return groupIds;
	}

	/**
	 * Method to get the list of standard test codes of the labs which have been configured
	 * @return String[][]. In the array first column is code and second is codesystem 
	 * @throws Exception 
	 */
	public String[][] getFlowsheetLabCode(List<Integer> groupIds,JSONObject groupIdTestIdMap) throws Exception{
		List<LabStandardCode> labCodes=new ArrayList<LabStandardCode>();
		labCodes=  getlabStandardGroups(groupIds);
		//labStandardCodeRepository.findAll(FlowsheetSpecification.labStandardCodeGroupIds(groupIds));
		String[][] standardCode=new String[labCodes.size()][2];
		for(int i=0;i<labCodes.size();i++){
			if(labCodes.get(i).getLabStandardCodeGroupCodesystem().contentEquals(FlowsheetServiceImpl.GlenwoodSystemsOID)){
				standardCode[i][0]=Integer.toString(labCodes.get(i).getLabStandardCodeGroupGwid());
				standardCode[i][1]=labCodes.get(i).getLabStandardCodeGroupCodesystem();
			}else{
				standardCode[i][0]=labCodes.get(i).getLabStandardCodeGroupCode();
				standardCode[i][1]=labCodes.get(i).getLabStandardCodeGroupCodesystem();
			}
			if(groupIdTestIdMap.has(labCodes.get(i).getLabStandardCodeGroupId().toString())){
				groupIdTestIdMap.put(labCodes.get(i).getLabStandardCodeGroupId().toString(), 
						groupIdTestIdMap.get(labCodes.get(i).getLabStandardCodeGroupId().toString())
						+standardCode[i][0]+"###"+standardCode[i][1]+"@@@@");
			}else{
				groupIdTestIdMap.put(labCodes.get(i).getLabStandardCodeGroupId().toString(), 
						"@@@@"+standardCode[i][0]+"###"+standardCode[i][1]+"@@@@");
			}
		}
		if(standardCode.length==0){
			standardCode=new String[1][2];
			standardCode[0][0]="-2";
			standardCode[0][1]="-2";
		}
		return standardCode;
	}

	private List<LabStandardCode> getlabStandardGroups(List<Integer> groupIds) {
		CriteriaBuilder cb1=em.getCriteriaBuilder();
		CriteriaQuery<LabStandardCode> cq1 = cb1.createQuery(LabStandardCode.class);
		List<LabStandardCode> stndcode= new ArrayList<LabStandardCode>();
		Root<LabStandardCode> root1 = cq1.from(LabStandardCode.class);
		@SuppressWarnings("rawtypes")
		Selection[] selectionsforVR=new Selection[]{
			root1.get(LabStandardCode_.labStandardCodeGroupId),
			root1.get(LabStandardCode_.labStandardCodeGroupCode),
			root1.get(LabStandardCode_.labStandardCodeGroupGwid),
			root1.get(LabStandardCode_.labStandardCodeGroupCodesystem),

		};
		cq1.select(cb1.construct(LabStandardCode.class,selectionsforVR));
		Predicate[] restrictionsforVR=new Predicate[]{
				cb1.and(root1.get(LabStandardCode_.labStandardCodeGroupId).in(groupIds)),
		};
		cq1.where (restrictionsforVR);
		try{
			stndcode= em.createQuery(cq1).getResultList();	

		}catch(Exception e){
			e.printStackTrace();
		}
		return stndcode;

	}

	/**
	 * Method to get the list of standard test codes of the params which have been configured
	 * @return String[][]. In the array first column is code and second is codesystem 
	 * @throws Exception 
	 */
	public String[][] getFlowsheetParamCode(List<Integer> groupIds,JSONObject groupIdParamIdMap) throws Exception{
		
//		labCodes=paramStandardCodeRepository.findAll(FlowsheetSpecification.paramStandardCodeGroupIds(groupIds));
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object>  cq = builder.createQuery();
		Root<ParamStandardCode> rootParamStandardCode = cq.from(ParamStandardCode.class);
		
		Predicate paramStandardCodeGroupId = rootParamStandardCode.get(ParamStandardCode_.paramStandardCodeGroupId).in(groupIds);
		
		cq.multiselect(builder.construct(ParamStandardCode.class, rootParamStandardCode.get(ParamStandardCode_.paramStandardCodeId),
				rootParamStandardCode.get(ParamStandardCode_.paramStandardCodeGroupId),
				rootParamStandardCode.get(ParamStandardCode_.paramStandardCodeGroupCode),
				rootParamStandardCode.get(ParamStandardCode_.paramStandardCodeGroupGwid),
				rootParamStandardCode.get(ParamStandardCode_.paramStandardCodeGroupCodesystem))).where(paramStandardCodeGroupId);
		
		List<Object> obj=em.createQuery(cq).getResultList();
		List<ParamStandardCode> labCodes=new ArrayList<ParamStandardCode>();
		for(int i=0;i<obj.size();i++){
			ParamStandardCode paramCodeData = (ParamStandardCode) obj.get(i);
			ParamStandardCode ParamStandardCodeBean = new ParamStandardCode(paramCodeData.getParamStandardCodeId(),
					paramCodeData.getParamStandardCodeGroupId(),
					paramCodeData.getParamStandardCodeGroupCode(),
					paramCodeData.getParamStandardCodeGroupGwid(),
					paramCodeData.getParamStandardCodeGroupCodesystem());
			labCodes.add(ParamStandardCodeBean);
		}
		
		String[][] standardCode=new String[labCodes.size()][2];
		for(int i=0;i<labCodes.size();i++){
			if(labCodes.get(i).getParamStandardCodeGroupCodesystem().contentEquals(FlowsheetServiceImpl.GlenwoodSystemsOID)){
				standardCode[i][0]=Integer.toString(labCodes.get(i).getParamStandardCodeGroupGwid());
				standardCode[i][1]=labCodes.get(i).getParamStandardCodeGroupCodesystem();
			}else{
				standardCode[i][0]=labCodes.get(i).getParamStandardCodeGroupCode();
				standardCode[i][1]=labCodes.get(i).getParamStandardCodeGroupCodesystem();
			}
			if(groupIdParamIdMap.has(labCodes.get(i).getParamStandardCodeGroupId().toString())){
				groupIdParamIdMap.put(labCodes.get(i).getParamStandardCodeGroupId().toString(), 
						groupIdParamIdMap.get(labCodes.get(i).getParamStandardCodeGroupId().toString())
						+standardCode[i][0]+"###"+standardCode[i][1]+"@@@@");
			}else{
				groupIdParamIdMap.put(labCodes.get(i).getParamStandardCodeGroupId().toString(), 
						"@@@@"+standardCode[i][0]+"###"+standardCode[i][1]+"@@@@");
			}
		}
		if(standardCode.length==0){
			standardCode=new String[1][2];
			standardCode[0][0]="-2";
			standardCode[0][1]="-2";
		}
		return standardCode;
	}

	public ArrayList<FS_LabBean> formLabData(int flowsheetId,String startDate, Integer patientId,Integer encounterId,Integer patgender,Integer chartId) throws Exception{
	
		
		List<Integer> groupIds=getFlowsheetLabGroupId(flowsheetId,patientId);
		JSONObject groupIdTestIdMap=new JSONObject();
		String[][] codes= getFlowsheetLabCode(groupIds,groupIdTestIdMap);
		List<FS_GroupIdTest> testIdandGroupIds=new ArrayList<>();
		ArrayList<Integer>testIds=new ArrayList<Integer>();
		
		String testIdString = "";
		ArrayList<String> testDetailIdsTemp;
		try{
			testIdandGroupIds=getTestIdsandGroupIds(groupIds);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(testIdandGroupIds.size()>0)
			for (int i=0;i<testIdandGroupIds.size();i++){
				testIds.add(testIdandGroupIds.get(i).getTestId());
				testIdString += testIdandGroupIds.get(i).getTestId()+",";
			}
		if(testIds.size()==0){
			testIds.add(-2);
		}
		if(!testIdString.equals(""))
			testIdString = testIdString.substring(0, testIdString.length()-1);
		//if(testIds.size()>0){
		ArrayList<FS_LabBean>labdata = getLabsData(patientId, flowsheetId, encounterId,testIds,groupIds,chartId);
		//ArrayList<FS_LabBean>labEntries = new ArrayList<FS_LabBean>();
		for(int i=0;i<labdata.size();i++){
			int grupId = -1;
			
			try {
				for( int j = 0 ; j < testIdandGroupIds.size() ; j++ ) {
					if(testIdandGroupIds.get(j).getTestId() == Integer.parseInt(labdata.get(i).getLabTestId())) {
						grupId = testIdandGroupIds.get(j).getGroupId();
					}
				}
			} catch(Exception e){ 
				e.printStackTrace();
			}
			
			labdata.get(i).setGroupId( grupId );
			
		}

		Map<Integer, FS_LabBean> latestGrupLabData = new HashMap<>();
		Collections.sort(labdata);
		for(FS_GroupIdTest testIdandGroupId : testIdandGroupIds) {
			if(!latestGrupLabData.containsKey(testIdandGroupId.getGroupId()) || 
					latestGrupLabData.get(testIdandGroupId.getGroupId())== null ){
				latestGrupLabData.put(testIdandGroupId.getGroupId(), getLatestLabBean(testIdandGroupId, labdata));
			} else if( latestGrupLabData.get(testIdandGroupId.getGroupId())!= null ) {
				FS_LabBean bean = getLatestLabBean(testIdandGroupId, labdata);
				if( bean!= null && bean.compareTo(latestGrupLabData.get(testIdandGroupId.getGroupId())) > 1 ) {
					latestGrupLabData.put(testIdandGroupId.getGroupId(), bean);
				}
			}
		}
		ArrayList<FS_LabBean> retData  = new ArrayList<>();
		ArrayList<Integer> performedOrdersGrupId = new ArrayList<>();
		latestGrupLabData.values();

		for(Integer key : latestGrupLabData.keySet()) {
			if(latestGrupLabData.get(key) == null) {
				performedOrdersGrupId.add(key);
			}
		}
		//Integer chartId= getChartId(patientId);
		//List<Chart> charts=chartRepository.findAll(Specifications.where(ChartSpecification.patientId(patientId)));
		//chartId=charts.get(0).getChartId();


		//PatientRegistration patients=patientRegistrationRepository.findOne(Specifications.where(PatientRegistrationSpecification.PatientId(patientId+"")));
		//Integer gender=getPatientGender(patientId);
		List<LabStandardGroup> perfOrders=labStandardGroupRepository.findAll(Specifications.where(FlowsheetSpecification.labStandardGroupIdIsActive(groupIds)));
		Integer[] testIdArr = new Integer[testIds.size()];

		//testIdArr = null;

		for ( int i = 0 ; i < perfOrders.size() ; i++ ) {
			if(performedOrdersGrupId.contains(perfOrders.get(i).getLabStandardGroupId())) {
				FS_LabBean labBean= new FS_LabBean();
				LabStandardGroup hsh_lab = new LabStandardGroup();
				//set values
				String labName="";
				labName = Optional.fromNullable(perfOrders.get(i).getLabStandardGroupName()).or("");
				labBean.setLabName(labName);
				labBean.setGroupId(perfOrders.get(i).getLabStandardGroupId());
				String ordOn="";
				String prfOnSort="1900-05-13 16:40:35";
				String perOn="Never Performed";
				String status="";
				String notes=" ";
				labBean.setOrderedOn(ordOn);
				labBean.setPerformdatesort(prfOnSort);
				labBean.setPerformedOn(perOn);
				labBean.setStatus(status);
				labBean.setResultNotes(notes);
				//labBean.setCompletedDetaildId(testIds);
				labBean.setGender(patgender+"");
				labBean.setIslab("1");
				Integer[] getgrouptests=getGroupTestIds(testIdandGroupIds,perfOrders.get(i).getLabStandardGroupId());
				labBean.setLabTestId(convertListtoString(getgrouptests));
				labBean.settestDetailId(getTestDetailIds(chartId,getgrouptests));

				testDetailIdsTemp = setCompletedDetaildId(chartId,getgrouptests);
				labBean.setCompletedDetaildId(testDetailIdsTemp);
				retData.add(labBean);

			} else {
				FS_LabBean labBean = latestGrupLabData.get(perfOrders.get(i).getLabStandardGroupId());
				if(labBean == null) {
					continue;
				}
				LabStandardGroup hsh_lab = new LabStandardGroup();
				//set values
				String labName="";
				labName = Optional.fromNullable(perfOrders.get(i).getLabStandardGroupName()).or("");
				labBean.setLabName(labName);
				labBean.setGender(patgender+"");
				Integer[] getgrouptests=getGroupTestIds(testIdandGroupIds,perfOrders.get(i).getLabStandardGroupId());
				labBean.setLabTestId(convertListtoString(getgrouptests));
				labBean.settestDetailId(getTestDetailIds(chartId,getgrouptests));
				testDetailIdsTemp = setCompletedDetaildId(chartId,getgrouptests);
				labBean.setCompletedDetaildId(testDetailIdsTemp);
				retData.add(labBean);

			}
		}

		if(groupIds.size()>0)
			retData = checkFlowSheetRulesForLabData(patientId,chartId, groupIds, retData, startDate,groupIdTestIdMap,patgender);
		/*if(groupIds.size()>0)
			//retData = checkFlowSheetRulesForLabData(patientId,chartId, groupIds, arr_lab, startDate,groupsIdTestIdMap);
		 */
		return retData;
	}

	private String  convertListtoString(Integer[] testIds){
		String testIdString="";
		for (int i=0;i<testIds.length;i++){
			testIdString += testIds[i]+",";
		}
		if(testIdString.length()>0){
		testIdString = testIdString.substring(0, testIdString.length()-1);
		}
		return testIdString;
	}

	private Integer[] getGroupTestIds(List<FS_GroupIdTest> testIdandGroupIds, Integer groupIds){
		ArrayList<Integer> testIds= new ArrayList<Integer>();
		for(FS_GroupIdTest fsgroup:testIdandGroupIds){
			if((int)groupIds==fsgroup.getGroupId()){
				testIds.add(fsgroup.getTestId());
			}
			
		}
		Integer[] testIdArray = new Integer[testIds.size()];
		testIdArray=testIds.toArray(testIdArray);
		return testIdArray;
	}


	/*private Integer getChartId(Integer patientId) {

		Integer chartId = -1;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Chart> root = cq.from(Chart.class);
		cq.select(root.get(Chart_.chartId));
		cq.where(builder.equal(root.get(Chart_.chartPatientid), patientId));
		try{
		chartId = (Integer)em.createQuery(cq).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			chartId=-1;
		}
		return chartId;

	}*/

	private Integer getPatientGender(Integer patientId) {
		Integer gender = -1;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PatientRegistration> root = cq.from(PatientRegistration.class);
		cq.select(root.get(PatientRegistration_.patientRegistrationSex));
		cq.where(builder.equal(root.get(PatientRegistration_.patientRegistrationId), patientId));
		try{
		gender = (Integer)em.createQuery(cq).getSingleResult();
		}catch(Exception e){
          gender=-1;
		}
		return gender;
	}



	private FS_LabBean getLatestLabBean(FS_GroupIdTest testIdandGroupId,
			ArrayList<FS_LabBean> labData) {

		for(int i = 0 ; i < labData.size(); i++) {
			FS_LabBean data = labData.get(i);
			if( Integer.parseInt( data.getLabTestId() ) == testIdandGroupId.getTestId()){
				data.setGroupId(testIdandGroupId.getGroupId());
				labData.remove(i--);
				return data;
			} 
		}
		return null;
	}

	//Toget the testIds and Groupids 
	private List<FS_GroupIdTest> getTestIdsandGroupIds(List<Integer> groupIds) {
		//Query1
		List<FS_GroupIdTest> getTestIdsTemp1=new ArrayList<FS_GroupIdTest>() ;
		List<FS_GroupIdTest> getTestIdsTemp2=new ArrayList<FS_GroupIdTest>() ;
		List<FS_GroupIdTest> getTestIdsTemp3=new ArrayList<FS_GroupIdTest>() ;
		List<FS_GroupIdTest> getTestIdsTemp4=new ArrayList<FS_GroupIdTest>() ;


		CriteriaBuilder builder=em.getCriteriaBuilder() ;
		CriteriaQuery<FS_GroupIdTest> cq=builder.createQuery(FS_GroupIdTest.class);
		Root <LabStandardCode> root =cq.from(LabStandardCode.class);

		Join<LabStandardCode, LabDescription> codedesc=root.join(LabStandardCode_.labDescriptionTable,JoinType.INNER);
		/*codedesc.on(builder.equal(codedesc.get(LabDescription_.labDescriptionTestid),
				root.get(LabStandardCode_.labStandardCodeGroupGwid)));*/

		Selection[] selections=new Selection[]{
				root.get(LabStandardCode_.labStandardCodeGroupId).alias("groupId"),
				codedesc.get(LabDescription_.labDescriptionTestid).alias("testId"),
		};
		cq.distinct(true);
		//cq.select(builder.construct(FS_GroupIdTest.class,selections));
		cq.multiselect(selections);
		Predicate[] restrictions = new Predicate[] {
				builder.equal(root.get(LabStandardCode_.labStandardCodeGroupCodesystem),GlenwoodSystemsOID),
				builder.and(root.get(LabStandardCode_.labStandardCodeGroupId).in(groupIds)),

		};
		cq.where(restrictions);
		try{
			//List<Object> lists = em.createQuery(cq).getResultList();

			getTestIdsTemp1= em.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		//Query2 getting Loinc 
		CriteriaBuilder loinc=em.getCriteriaBuilder() ;
		CriteriaQuery<FS_GroupIdTest> cq1=loinc.createQuery(FS_GroupIdTest.class);
		Root <LabStandardCode> rootloinc =cq1.from(LabStandardCode.class);
		Join<LabStandardCode, LabDescription> loinccodedesc=rootloinc.join(LabStandardCode_.labDescriptionTable,JoinType.INNER);
		//loinccodedesc.on(loinc.equal(loinccodedesc.get(LabDescription_.labDescriptionLoinc),rootloinc.get(LabStandardCode_.labStandardCodeGroupCodesystem)));
		@SuppressWarnings("rawtypes")
		Selection[] selectionsloinc=new Selection[]{
			rootloinc.get(LabStandardCode_.labStandardCodeGroupId),
			codedesc.get(LabDescription_.labDescriptionTestid),
		};
		cq1.distinct(true);
		cq1.select(builder.construct(FS_GroupIdTest.class,selectionsloinc));
		Predicate[] restrictionsloinc = new Predicate[] {
				loinc.equal(rootloinc.get(LabStandardCode_.labStandardCodeGroupCodesystem),LoincOID),
				loinc.and(rootloinc.get(LabStandardCode_.labStandardCodeGroupId).in(groupIds)),

		};
		cq1.where(restrictionsloinc);
		getTestIdsTemp2=(em.createQuery(cq1).getResultList());
	
		//Query to get loinc  from externally 

		CriteriaBuilder loinchl7=em.getCriteriaBuilder() ;
		CriteriaQuery<FS_GroupIdTest>cq2=loinchl7.createQuery(FS_GroupIdTest.class);
		Root<LabStandardCode> rootloinchl7=cq2.from(LabStandardCode.class);

		Join<LabStandardCode, Hl7ExternalTest> hl7testroot =
				rootloinchl7.join(LabStandardCode_.hl7ExternalTestTable,JoinType.INNER);
		//hl7testroot.on(loinchl7.equal(hl7testroot.get(Hl7ExternalTest_.hl7ExternalTestCode),rootloinchl7.get(LabStandardCode_.labStandardCodeGroupCode)));
		Join<Hl7ExternalTest, Hl7ExternalTestmapping>hl7testdesc  =
				hl7testroot.join(Hl7ExternalTest_.hl7ExternalTestmappingTable,JoinType.INNER);
		//hl7testdesc.on(loinchl7.equal(hl7testdesc.get(Hl7ExternalTestmapping_.hl7ExternalTestmappingExternaltestid),hl7testroot.get(Hl7ExternalTest_.hl7ExternalTestId)));	
		Join<Hl7ExternalTestmapping,  LabDescription> hl7desc = 
				hl7testdesc.join(Hl7ExternalTestmapping_.labDescription,JoinType.INNER);
		//hl7desc.on(loinchl7.equal(hl7desc.get(LabDescription_.labDescriptionTestid),hl7testdesc.get(Hl7ExternalTestmapping_.hl7ExternalTestmappingLocaltestid)));
		Selection[] selectionshl7=new Selection[]{
				rootloinchl7.get(LabStandardCode_.labStandardCodeGroupId),
				hl7desc.get(LabDescription_.labDescriptionTestid),
		};
		cq2.distinct(true);
		cq2.select(builder.construct(FS_GroupIdTest.class,selectionshl7));
		Predicate[] restrictionshl7 = new Predicate[] {
				loinchl7.equal(rootloinc.get(LabStandardCode_.labStandardCodeGroupCodesystem),LoincOID),
				loinchl7.and(rootloinc.get(LabStandardCode_.labStandardCodeGroupId).in(groupIds)),
				loinchl7.equal(hl7testroot.get(Hl7ExternalTest_.hl7ExternalTestLabcompanyid),51),

		};
		cq2.where(restrictionshl7);

		getTestIdsTemp3=(em.createQuery(cq2).getResultList());
		
		//Query for snowmed codes 

		CriteriaBuilder snowmedhl7=em.getCriteriaBuilder() ;
		CriteriaQuery<FS_GroupIdTest>cq3=snowmedhl7.createQuery(FS_GroupIdTest.class);
		Root<LabStandardCode> rootsnowmedhl7=cq3.from(LabStandardCode.class);

		Join<LabStandardCode, Hl7ExternalTest> hl7testsmd =
				rootsnowmedhl7.join(LabStandardCode_.hl7ExternalTestTable,JoinType.INNER);
		//hl7testsmd.on(snowmedhl7.equal(hl7testsmd.get(Hl7ExternalTest_.hl7ExternalTestCode),rootsnowmedhl7.get(LabStandardCode_.labStandardCodeGroupCode)));
		Join<Hl7ExternalTest, Hl7ExternalTestmapping>hl7testdesmd  =
				hl7testsmd.join(Hl7ExternalTest_.hl7ExternalTestmappingTable,JoinType.INNER);
		//hl7testdesmd.on(snowmedhl7.equal(hl7testdesmd.get(Hl7ExternalTestmapping_.hl7ExternalTestmappingExternaltestid),hl7testsmd.get(Hl7ExternalTest_.hl7ExternalTestId)));	
		Join<Hl7ExternalTestmapping,  LabDescription> hl7descmd = 
				hl7testdesmd.join(Hl7ExternalTestmapping_.labDescription,JoinType.INNER);
		//hl7descmd.on(snowmedhl7.equal(hl7descmd.get(LabDescription_.labDescriptionTestid),hl7testdesmd.get(Hl7ExternalTestmapping_.hl7ExternalTestmappingLocaltestid)));
		Selection[] selectionsmd=new Selection[]{
				rootsnowmedhl7.get(LabStandardCode_.labStandardCodeGroupId),
				hl7descmd.get(LabDescription_.labDescriptionTestid),
		};
		cq3.distinct(true);
		cq3.select(builder.construct(FS_GroupIdTest.class,selectionsmd));
		Predicate[] restrictionsmd = new Predicate[] {
				snowmedhl7.equal(rootloinc.get(LabStandardCode_.labStandardCodeGroupCodesystem),SnomedOID),
				snowmedhl7.and(rootloinc.get(LabStandardCode_.labStandardCodeGroupId).in(groupIds)),
				snowmedhl7.equal(hl7testsmd.get(Hl7ExternalTest_.hl7ExternalTestLabcompanyid),54),

		};
		cq3.where(restrictionsmd);

		getTestIdsTemp4.addAll(em.createQuery(cq3).getResultList());
		

		getTestIdsTemp4.addAll(getTestIdsTemp1);
		getTestIdsTemp4.addAll(getTestIdsTemp2);
		getTestIdsTemp4.addAll(getTestIdsTemp3);

		return getTestIdsTemp4;


	}



	private  Integer getLabChartId(Integer patientId) {
		Integer chartId = -1;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Chart> root = cq.from(Chart.class);
		cq.select(root.get(Chart_.chartId));
		cq.where(builder.equal(root.get(Chart_.chartPatientid),patientId ));
		chartId = (Integer)em.createQuery(cq).getSingleResult();
		return chartId;
	}
	private  Short getEncounterStatusforLab(Integer encounterId) {
		Short encounterStatus = -1;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Encounter> root = cq.from(Encounter.class);
		cq.select(root.get(Encounter_.encounterStatus));
		cq.where(builder.equal(root.get(Encounter_.encounterId), encounterId));
		encounterStatus = (Short) em.createQuery(cq).getSingleResult();
		return encounterStatus;
	}

	private Date getencounterDateforLab(Integer encounterId) {
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		//orderedOn = timeStamp.toStringString();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Encounter> root = cq.from(Encounter.class);
		cq.select(root.get(Encounter_.encounterDate));
		cq.where(builder.equal(root.get(Encounter_.encounterId),encounterId));
		Timestamp encounterDate = (Timestamp) em.createQuery(cq).getSingleResult();
		return encounterDate;
	}


	private ArrayList<FS_LabBean> getLabsData (int patientId, int flowSheetId, int encounterId,List<Integer>testIds,List<Integer>groupIds,Integer chartId) throws Exception{

		//Integer chartId = -1;
		Date encounterDate=null;
		Short encounterStatus=0;
		//chartId=getLabChartId(patientId);
		if(encounterId!=-1){
			encounterStatus=getEncounterStatusforLab(encounterId);
			encounterDate=getencounterDateforLab(encounterId);
		}

		List<FS_LabBean> fsLabBeanList1 = new ArrayList<>();
		List<FS_LabBean> fsLabBeanList2 = new ArrayList<>();
		List<FS_LabBean> fsLabBeanList3= new ArrayList<>();

		//LabEntries table
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<FS_LabBean> cq = cb.createQuery(FS_LabBean.class);
		Root<LabEntries> root = cq.from(LabEntries.class);

		Selection[] selections=new Selection[]{
				root.get(LabEntries_.labEntriesPerfOn),
				root.get(LabEntries_.labEntriesTestdetailId),
				root.get(LabEntries_.labEntriesTestId),
				root.get(LabEntries_.labEntriesTestDesc),// taking this as name change here
				root.get(LabEntries_.labEntriesOrdOn),
				root.get(LabEntries_.labEntriesPerfOn),
				root.get(LabEntries_.labEntriesResultNotes),
				root.get(LabEntries_.labEntriesTestStatus)
		};
		//cq.distinct(true);
		cq.select(cb.construct(FS_LabBean.class,selections));
		if(encounterId!=-1 && encounterStatus==3){

			Predicate[] restrictions = new Predicate[] {
					cb.and(root.get(LabEntries_.labEntriesTestId).in(testIds),

							cb.equal(root.get(LabEntries_.labEntriesChartid),chartId),

							cb.greaterThan(root.get(LabEntries_.labEntriesTestStatus), 2),		
							cb.notEqual(root.get(LabEntries_.labEntriesTestStatus),7)),
							//cb.lessThanOrEqualTo(root.get(LabEntries_.labEntriesOrdOn), encounterDate)

			};
			cq.where(restrictions);
		} else {
			Predicate[] restrictions = new Predicate[] {
					cb.and(root.get(LabEntries_.labEntriesTestId).in(testIds),

							cb.equal(root.get(LabEntries_.labEntriesChartid),chartId),

							cb.greaterThan(root.get(LabEntries_.labEntriesTestStatus), 2),		
							cb.notEqual(root.get(LabEntries_.labEntriesTestStatus),7))

			};
			cq.where(restrictions);
		}

		try{
			fsLabBeanList1= em.createQuery(cq).getResultList();

		}catch(Exception e){
			e.printStackTrace();
		}
		//vaccinereport table 
		CriteriaBuilder cb1=em.getCriteriaBuilder();
		CriteriaQuery<FS_LabBean> cq1 = cb.createQuery(FS_LabBean.class);
		Root<VaccineReport> root1 = cq1.from(VaccineReport.class);
		Join<VaccineReport, LabDescription> vaccineJoin =root1.join(VaccineReport_.labDescriptionTable,JoinType.INNER);
		Selection[] selectionsforVR=new Selection[]{
				root1.get(VaccineReport_.vaccineReportGivenDate),
				root1.get(VaccineReport_.vaccineReportVaccineId),
				root1.get(VaccineReport_.vaccineReportComments),
				vaccineJoin.get(LabDescription_.labDescriptionTestDesc),
		};
		cq1.select(cb.construct(FS_LabBean.class,selectionsforVR));
		Predicate[] restrictionsforVR=new Predicate[]{
				cb1.and(root1.get(VaccineReport_.vaccineReportVaccineId).in(testIds)),
				cb1.equal(root1.get(VaccineReport_.vaccineReportChartId), chartId),
				//cb1.equal(root1.get(VaccineReport_.vaccineReportIsemr), 2),


		};
		cq1.where (restrictionsforVR);
		try{
			fsLabBeanList2= em.createQuery(cq1).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}

		CriteriaBuilder cb2=em.getCriteriaBuilder();
		CriteriaQuery<FS_LabBean> cq2 = cb.createQuery(FS_LabBean.class);
		Root<Overridealerts> root2 = cq2.from(Overridealerts.class);
		Selection[] selectoverridealerts=new Selection[]{
				root2.get(Overridealerts_.overriddenOn),
				root2.get(Overridealerts_.reason),
				root2.get(Overridealerts_.overridealertsFlowsheetElementId),

		};
		cq2.select(cb.construct(FS_LabBean.class,selectoverridealerts));
		//below predicates are not used why
		List<String> groupds= new ArrayList<>();
		for(Integer id : groupIds) {
			groupds.add(id+"");
		}
		Predicate[] restrictionsforoveralerts=new Predicate[]{
				cb2.equal(root2.get(Overridealerts_.overridealertsFlowsheetElementType),3),
				cb2.equal(root2.get(Overridealerts_.overridealertsFlowsheetMapId),flowSheetId),
				cb2.and(root2.get(Overridealerts_.overridealertsFlowsheetElementId).in(groupds)),
				cb2.equal(root2.get(Overridealerts_.patientid),patientId),
		};
		cq2.where(restrictionsforoveralerts);

		try{
			fsLabBeanList3 = em.createQuery(cq2).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}

		ArrayList<FS_LabBean> result = new ArrayList<>();
		result.addAll(fsLabBeanList1);
		result.addAll(fsLabBeanList2);
		result.addAll(fsLabBeanList3);

		
		return result;
	}




	private List<Integer> getPatientGendersOnChartId(Integer chartId) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = em.getCriteriaBuilder();

		List<Integer> genders=new ArrayList<Integer>();
		try{
			String query= "SELECT patient_registration. patient_registration_sex "+
					"FROM   patient_registration "
					+ "inner join chart "
					+ "ON patient_registration.patient_registration_id = "
					+ " chart.chart_patientid "
					+ "WHERE  chart.chart_id = "+chartId;
			genders.addAll( em.createNativeQuery(query).getResultList() );
		}catch(Exception e){
			e.printStackTrace();
		}
		return genders;

	}

	private ArrayList<Integer> getTestDetailIds(int chartId,Integer[] testId){
		ArrayList<Integer> testdetailsIdArr=new ArrayList<Integer>();
		if(testId.length>0){
			//List<LabEntries> orderLabs=labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.testIdsWithOrder(testId)).and(InvestigationSpecification.chartId(chartId)).and(InvestigationSpecification.statusEqual(1)));
			List<LabEntries>orderLabs=getLabEntriesorderStatus(chartId,testId);
			for(int k=0;k<orderLabs.size();k++)
				testdetailsIdArr.add(orderLabs.get(k).getLabEntriesTestdetailId());
		}
		return testdetailsIdArr;
	}

	private List<LabEntries> getLabEntriesorderStatus(int chartId,Integer[] testId){
			List<LabEntries>values=new ArrayList<LabEntries>();
	CriteriaBuilder cb=em.getCriteriaBuilder();
	CriteriaQuery<LabEntries> cq=cb.createQuery(LabEntries.class);
	Root<LabEntries>root=cq.from(LabEntries.class);
	
	Selection[] selections=new Selection[]{
			root.get(LabEntries_.labEntriesTestdetailId),
			
	};
	cq.select(cb.construct(LabEntries.class,selections));
	Predicate[] predications=new Predicate[]{
	root.get(LabEntries_.labEntriesTestId).in((Object[])testId),
	cb.equal(root.get(LabEntries_.labEntriesChartid),chartId),
	cb.equal(root.get(LabEntries_.labEntriesTestStatus),1),
	};
	cq.where (predications);
	cq.orderBy(cb.asc(root.get(LabEntries_.labEntriesTestDesc)),cb.desc(cb.coalesce(root.get(LabEntries_.labEntriesPerfOn),cb.function("to_timestamp",Timestamp.class,cb.literal("1900-05-13 16:40:35"),cb.literal("YYYY-MM-DD HH24:MI:SS")))));


	try{
		values= em.createQuery(cq).getResultList();	

	}catch(Exception e){
		e.printStackTrace();
	}	
		
		
		
		return values;
	}

	private ArrayList<String> setCompletedDetaildId(int chartId,Integer[] testId){
		ArrayList<String> testdetailsIdArr=new ArrayList<String>();
		if(testId.length>0){
			//List<LabEntries> orderLabs=labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.testIdsWithOrder(testId)).and(InvestigationSpecification.chartId(chartId)).and(InvestigationSpecification.statusNotEqual(2)).and(InvestigationSpecification.statusNotEqual(7)).and(InvestigationSpecification.statusGreaterThan(2)));
			List<LabEntries>orderLabs=getLabEntriesdetailId(chartId,testId);
			
			for(int k=0;k<orderLabs.size();k++){
				testdetailsIdArr.add(orderLabs.get(k).getLabEntriesTestdetailId()+"@#@"+orderLabs.get(k).getLabEntriesTestStatus());
			}
		}
		return testdetailsIdArr;
	}

	private List<LabEntries> getLabEntriesdetailId(int chartId, Integer[] testId) {
		List<LabEntries>values=new ArrayList<LabEntries>();
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<LabEntries> cq=cb.createQuery(LabEntries.class);
		Root<LabEntries>root=cq.from(LabEntries.class);
		
		Selection[] selections=new Selection[]{
				root.get(LabEntries_.labEntriesTestdetailId),
				root.get(LabEntries_.labEntriesTestStatus),
		};
		cq.select(cb.construct(LabEntries.class,selections));
		Predicate[] predications=new Predicate[]{
		root.get(LabEntries_.labEntriesTestId).in((Object[])testId),
		cb.equal(root.get(LabEntries_.labEntriesChartid),chartId),
		cb.notEqual(root.get(LabEntries_.labEntriesTestStatus),2),
		cb.notEqual(root.get(LabEntries_.labEntriesTestStatus),7),
		 cb.greaterThan(root.get(LabEntries_.labEntriesTestStatus),2),
		};
		cq.where (predications);
		cq.orderBy(cb.asc(root.get(LabEntries_.labEntriesTestDesc)),cb.desc(cb.coalesce(root.get(LabEntries_.labEntriesPerfOn),cb.function("to_timestamp",Timestamp.class,cb.literal("1900-05-13 16:40:35"),cb.literal("YYYY-MM-DD HH24:MI:SS")))));


		try{
			values= em.createQuery(cq).getResultList();	

		}catch(Exception e){
			e.printStackTrace();
		}
		return values;
	}

	public List<LabEntries> orderByDate(List<LabEntries> confData,final boolean descending){
		if (confData.size() > 0) {
			Collections.sort(confData, new Comparator<LabEntries>() {
				@Override
				public int compare(final LabEntries object1, final LabEntries object2) {
					String empty="1900-05-13 16:40:35";
					Timestamp object1TimeStamp=Optional.fromNullable(object1.getLabEntriesPerfOn()).or(Timestamp.valueOf(empty));
					Timestamp object2TimeStamp=Optional.fromNullable(object2.getLabEntriesPerfOn()).or(Timestamp.valueOf(empty));
					return (descending ? -1 : 1) * object1TimeStamp.compareTo(object2TimeStamp);
				}
			} );
		}
		return confData;
	}

	public List<VaccineReport> orderByDateVaccines(List<VaccineReport> confData,final boolean descending){
		if (confData.size() > 0) {
			Collections.sort(confData, new Comparator<VaccineReport>() {
				@Override
				public int compare(final VaccineReport object1, final VaccineReport object2) {
					String empty="1900-05-13 16:40:35";
					Timestamp object1TimeStamp=Optional.fromNullable(object1.getVaccineReportGivenDate()).or(Timestamp.valueOf(empty));
					Timestamp object2TimeStamp=Optional.fromNullable(object2.getVaccineReportGivenDate()).or(Timestamp.valueOf(empty));
					return (descending ? -1 : 1) * object1TimeStamp.compareTo(object2TimeStamp);
				}
			} );
		}
		return confData;
	}

	public List<VaccineReport> orderByStatusVaccines(List<VaccineReport> confData,final boolean descending){
		if (confData.size() > 0) {
			Collections.sort(confData, new Comparator<VaccineReport>() {
				@Override
				public int compare(final VaccineReport object1, final VaccineReport object2) {
					Integer object1String=object1.getVaccineReportIsemr();
					Integer object2String=object2.getVaccineReportIsemr();
					return (descending ? -1 : 1) * object1String.compareTo(object2String);
				}
			} );
		}
		return confData;
	}

	public List<LabEntriesParameter> orderByDateParam(List<LabEntriesParameter> confData,final boolean descending){
		if (confData.size() > 0) {
			Collections.sort(confData, new Comparator<LabEntriesParameter>() {
				@Override
				public int compare(final LabEntriesParameter object1, final LabEntriesParameter object2) {
					String empty="1900-05-13 16:40:35";
					Timestamp object1TimeStamp=Optional.fromNullable(object1.getLabEntriesParameterDate()).or(Timestamp.valueOf(empty));
					Timestamp object2TimeStamp=Optional.fromNullable(object2.getLabEntriesParameterDate()).or(Timestamp.valueOf(empty));
					return (descending ? -1 : 1) * object1TimeStamp.compareTo(object2TimeStamp);
				}
			} );
		}
		return confData;
	}

	public List<PatientClinicalElements> orderByDateClinical(List<PatientClinicalElements> confData,final boolean descending){
		if (confData.size() > 0) {
			Collections.sort(confData, new Comparator<PatientClinicalElements>() {
				@Override
				public int compare(final PatientClinicalElements object1, final PatientClinicalElements object2) {
					String empty="1900-05-13 16:40:35";
					Timestamp object1TimeStamp=Optional.fromNullable(object1.getEncounter().getEncounterDate()).or(Timestamp.valueOf(empty));
					Timestamp object2TimeStamp=Optional.fromNullable(object2.getEncounter().getEncounterDate()).or(Timestamp.valueOf(empty));
					return (descending ? -1 : 1) * object1TimeStamp.compareTo(object2TimeStamp);
				}
			} );
		}
		return confData;
	}

	public List<Prescription> orderByDateDrug(List<Prescription> confData,final boolean descending){
		if (confData.size() > 0) {
			Collections.sort(confData, new Comparator<Prescription>() {
				@Override
				public int compare(final Prescription object1, final Prescription object2) {
					String empty="1900-05-13 16:40:35";
					Timestamp object1TimeStamp=Optional.fromNullable(object1.getDocPrescOrderedDate()).or(Timestamp.valueOf(empty));
					Timestamp object2TimeStamp=Optional.fromNullable(object2.getDocPrescOrderedDate()).or(Timestamp.valueOf(empty));
					return (descending ? -1 : 1) * object1TimeStamp.compareTo(object2TimeStamp);
				}
			} );
		}
		return confData;
	}

	public ArrayList<FS_LabBean> checkFlowSheetRulesForLabData(int patientId,int chartId, List<Integer> groupIds,ArrayList<FS_LabBean> flowSheetData, String startDate,JSONObject groupIdTestIdMap,Integer patgender) throws Exception{
		String currentDate = getCurrentDate();
		ArrayList<FS_LabBean> tempFlowSheetData = new ArrayList<FS_LabBean>();
		List<String> newGroupIds=Lists.transform(groupIds, Functions.toStringFunction());
		if(startDate!=null& !startDate.equalsIgnoreCase("")){
		List<HmrBean> flowSheetRule = getFlowSheetRuleQueryPatientBased(patientId,newGroupIds, startDate,3);
		String today = getTodayDate();
		//PatientRegistration genderPat =  patientRegistrationRepository.findOne(Specifications.where(PatientRegistrationSpecification.PatientId(patientId+"")));
		//Integer  gender1 = gender
		String gender=patgender.toString();
		for(int flowSheetDataCounter = 0 ; flowSheetDataCounter < flowSheetData.size() ; flowSheetDataCounter++){
			FS_LabBean flowSheetDataObj = flowSheetData.get(flowSheetDataCounter);
			boolean isElementRuleExists = false;
			boolean isElementGenderRuleSatisfied = false;
			flowSheetDataObj.setDueCriteria("");
			//Checking Rule for Lab Parameters
			for(int dueRulesCounter = 0 ; dueRulesCounter < flowSheetRule.size() ; dueRulesCounter++ ){
				HmrBean flowSheetRuleObj = flowSheetRule.get(dueRulesCounter);
				String testIds="";
				try{
					testIds=MoreObjects.firstNonNull(groupIdTestIdMap.get(Optional.fromNullable(flowSheetRuleObj.getElement_id()).or((long) -1).toString()),"-1").toString();
				}catch(Exception e){
					testIds="-1";
				}
				if(Optional.fromNullable(flowSheetRuleObj.getElement_type()).or(-1).toString().equals("3") && testIds.contains("@@@@"+Optional.fromNullable(flowSheetDataObj.getLabTestId()).or("-2").toString()+"###")){
					isElementRuleExists = true;
					if(Optional.fromNullable(flowSheetRuleObj.getGender()).or(-1).toString().equals("0") || gender.equals(Optional.fromNullable(flowSheetRuleObj.getGender()).or(-1).toString()))
					{
						isElementGenderRuleSatisfied = true;
						if(( DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(today, flowSheetRuleObj.getEnddate().toString()) == -1 ) || DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 0){
							if((MoreObjects.firstNonNull(flowSheetRuleObj.getSchType(),"").toString().equals("") || MoreObjects.firstNonNull(flowSheetRuleObj.getSchedule(),"").toString().equals(""))){
								if(!flowSheetDataObj.getPerformedOn().equalsIgnoreCase(null) && !flowSheetDataObj.getPerformedOn().equals("Never Performed")){
									if(DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString().replace("-", "/"), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString().replace("-", "/"), flowSheetRuleObj.getEnddate().toString()) == -1){
										flowSheetDataObj.setDue("Completed");
										flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or("").toString());
										break;
									}
								}									
								flowSheetDataObj.setDue("Due Now");
								flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or(""));
								break;
							}
							if(!flowSheetRuleObj.getSchType().toString().equals("") && !flowSheetRuleObj.getSchedule().toString().equals("")){
								String freqStartDate = flowSheetRuleObj.getStartdate().toString();
								String freqEndDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(freqStartDate)), 3);
								while(DateUtil.dateDiffVal( flowSheetRuleObj.getEnddate().toString() , freqEndDate) == 1){
									freqEndDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(freqStartDate)), 3);
									if(DateUtil.dateDiffVal(freqEndDate, flowSheetRuleObj.getEnddate().toString()) == 1){
									} else {
										//Due Checking for schedule Range
										if(( DateUtil.dateDiffVal(today, freqStartDate ) == 1 && DateUtil.dateDiffVal(today, freqEndDate) == -1 ) || DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 0){
											if(!flowSheetDataObj.getPerformedOn().equalsIgnoreCase(null) && !flowSheetDataObj.getPerformedOn().equals("Never Performed")){
												//here change the code for last performed append last performed + due period 

												if(flowSheetRuleObj.getRuledate().equals("3")){
													String rulePerformedDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(flowSheetDataObj.getPerformedOn().toString().replace("-", "/"))), 3);
													if(DateUtil.dateDiffVal(currentDate,rulePerformedDate) == -1){
														flowSheetDataObj.setDue("Completed");
														flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or(""));
														break;
													}
												}else{
													if(DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString().replace("-", "/"), freqStartDate) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString().replace("-", "/"), freqEndDate) == -1){
														flowSheetDataObj.setDue("Completed");
														flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or(""));
														break;
													}
												}
											}					
											flowSheetDataObj.setDue("Due Now");
											flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or(""));
											break;
										} else if(DateUtil.dateDiffVal(today, freqStartDate ) == 1 && DateUtil.dateDiffVal(today, freqEndDate ) == 1){
											flowSheetDataObj.setDue("Over Due");
											if(flowSheetDataObj.getPerformedOn()!=null && !flowSheetDataObj.getPerformedOn().equalsIgnoreCase("") && !flowSheetDataObj.getPerformedOn().equalsIgnoreCase(" ") &&  flowSheetRuleObj.getStartdate()!=null && flowSheetRuleObj.getEnddate()!=null && !flowSheetDataObj.getPerformedOn().equals("Never Performed")){
												if(DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString().replace("-", "/"), freqStartDate ) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString().replace("-", "/"), freqEndDate) == -1){
													flowSheetDataObj.setDue("Completed");
													flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or(""));
												}
											}		

											if(flowSheetDataObj.getPerformedOn()!=null && !flowSheetDataObj.getPerformedOn().equalsIgnoreCase("") && !flowSheetDataObj.getPerformedOn().equalsIgnoreCase(" ") &&  flowSheetRuleObj.getStartdate()!=null && flowSheetRuleObj.getEnddate()!=null && !flowSheetDataObj.getPerformedOn().equals("Never Performed")){
												if(DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString().replace("-", "/"), freqStartDate) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString().replace("-", "/"), freqEndDate ) == 1){
													flowSheetDataObj.setDue("");
													flowSheetDataObj.setDueCriteria("");
												}
											}	
										} 
										freqStartDate = freqEndDate;
									}
								}
							}
						} else if(DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(today, flowSheetRuleObj.getEnddate().toString()) == 1){
							flowSheetDataObj.setDue("Over Due");
							flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or(""));
							if(flowSheetDataObj.getPerformedOn()!=null && !flowSheetDataObj.getPerformedOn().equalsIgnoreCase("") && flowSheetRuleObj.getStartdate()!=null && flowSheetRuleObj.getEnddate()!=null && !flowSheetDataObj.getPerformedOn().equals("Never Performed")){
								if(DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString().replace("-", "/"), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString().replace("-", "/"), flowSheetRuleObj.getEnddate().toString()) == -1){
									flowSheetDataObj.setDue("Completed");
									flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or(""));
								}
							}		
							if(flowSheetDataObj.getPerformedOn()!=null && !flowSheetDataObj.getPerformedOn().equalsIgnoreCase("") && flowSheetRuleObj.getStartdate()!=null && flowSheetRuleObj.getEnddate()!=null && !flowSheetDataObj.getPerformedOn().equals("Never Performed")){
								if(  ( DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString().replace("-", "/"), flowSheetRuleObj.getStartdate().toString()) == -1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString().replace("-", "/"), flowSheetRuleObj.getEnddate().toString()) == -1) ||  ( DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString().replace("-", "/"), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString().replace("-", "/"), flowSheetRuleObj.getEnddate().toString()) == 1)){
									flowSheetDataObj.setDue("");
									flowSheetDataObj.setDueCriteria("");
								}
							}	
						} 
					} 
				}
			}
			if(isElementRuleExists == false){
				tempFlowSheetData.add(flowSheetDataObj);
			} else if(isElementRuleExists == true && isElementGenderRuleSatisfied == true){
				tempFlowSheetData.add(flowSheetDataObj);
			}
		}
		}
		return tempFlowSheetData;
	}

	public ArrayList<FS_LabParameterBean> formLabParameters (int flowsheetId, String startDate,Integer patientId,Integer encounterId,Integer patgender,Integer chartId) throws Exception {
		paramPerformedDate = new ArrayList<String> ();
		// getting list of codes and there code system
		List<Integer> groupIds=getFlowsheetParamGroupId(flowsheetId,patientId);
		JSONObject groupIdParamIdMap=new JSONObject();
		String[][] codes= getFlowsheetParamCode(groupIds,groupIdParamIdMap);

		//getting the list of paramIds for the above codes
		List<Integer> paramsList=getParamIds(codes,groupIdParamIdMap);
		Integer[] paramIds=new Integer[paramsList.size()];
		for(int k=0;k<paramsList.size();k++){
			paramIds[k]=Optional.fromNullable(paramsList.get(k)).or(-1);
		}
		if(paramIds.length==0){
			paramIds=new Integer[1];
			paramIds[0]=-2;
		}
		//getting the chart id
	   // Integer chartId= getChartId(patientId);

		/*Encounter encounterEntity=null;
		//getting the encounter details
		if(encounterId!=-1){
			encounterEntity=encounterEntityRepository.findOne(EncounterEntitySpecification.EncounterById(encounterId));
		}*/

		//getting list of performed parameters having param id and chart id
		List<ParamStandardGroup> perfOrders=paramStandardGroupRepository.findAll(Specifications.where(FlowsheetSpecification.paramStandardGroupIdIsActive(groupIds)));
		
		ArrayList<FS_LabParameterBean> arr_param = new ArrayList<FS_LabParameterBean> ();
		Iterator<ParamStandardGroup> itr_param = perfOrders.iterator();
		ParamStandardGroup hsh_param = new ParamStandardGroup();
		FS_LabParameterBean paramBean = null;
		String paramName = "";
		String prevParamName = "";
		while(itr_param.hasNext()){
			hsh_param = itr_param.next();
			ArrayList<FS_LabParameterBean> arr_paramTemp=new ArrayList<FS_LabParameterBean>();
			String paramIdsTemp="";
			if(!prevParamName.equalsIgnoreCase(hsh_param.getParamStandardGroupName())){
				arr_paramTemp=new ArrayList<FS_LabParameterBean>();
				String[] paramIdTemp=groupIdParamIdMap.get(hsh_param.getParamStandardGroupId()+"").toString().split("@@@@");
				Integer[] paramIdsGroup=new Integer[paramIdTemp.length-1];
				for(int l=1;l<paramIdTemp.length;l++){
					Integer testId = Integer.parseInt(paramIdTemp[l].split("###")[0]);
					paramIdsGroup[l-1]=testId;
					if(paramIdsTemp.trim().length()==0){
						paramIdsTemp=testId+"";
					}else{
						paramIdsTemp+=","+testId;
					}
				}
				if(paramIdsGroup.length==0){
					paramIdsGroup=new Integer[1];
					paramIdsGroup[0]=-2;
				}
				//getting list of params having param id and chart id
//				List<LabEntriesParameter> perfParamsInt=labEntriesParameterRepository.findAll(Specifications.where(InvestigationSpecification.getParamDetails(paramIdsGroup, chartId,encounterId,encounterEntity)));
				
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<Object> cq1 = cb.createQuery();
				Root<LabEntriesParameter> rootLabEntriesParameter = cq1.from(LabEntriesParameter.class);
				
				Join<LabEntriesParameter,LabParameters> join= rootLabEntriesParameter.join("labParametersTable", JoinType.LEFT);
				Expression<Date> endDateExpr=cb.function("date", Date.class, rootLabEntriesParameter.get(LabEntriesParameter_.labEntriesParameterDate));
				Predicate paramMapId=rootLabEntriesParameter.get(LabEntriesParameter_.labEntriesParameterMapid).in((Object[])paramIdsGroup);
				Predicate isActivePred=cb.equal(join.get(LabParameters_.labParametersIsactive),true);
				Predicate entriesIsActivePred=cb.equal(cb.coalesce(rootLabEntriesParameter.get(LabEntriesParameter_.labEntriesParameterIsactive), true),true);
				Predicate chartIdPred=cb.equal(rootLabEntriesParameter.get(LabEntriesParameter_.labEntriesParameterChartid), chartId);
				Predicate paramDetails = null;
				/*if((encounterId!=-1)&&(getEncounterStatusforLab(encounterId)==3)){
					Predicate encounterDatePred=cb.lessThanOrEqualTo(endDateExpr, getencounterDateforLab(encounterId));
					//Predicate encounterDatePred=cb.lessThanOrEqualTo(rootLabEntriesParameter.get(LabEntriesParameter_.labEntriesParameterDate), getencounterDateforLab(encounterId));
					paramDetails = cb.and(paramMapId,isActivePred,entriesIsActivePred,chartIdPred,encounterDatePred);
				}else{*/
				
					paramDetails = cb.and(paramMapId,isActivePred,entriesIsActivePred,chartIdPred);
				//}
				
				cq1.multiselect(cb.construct(LabParametersData.class, rootLabEntriesParameter.get(LabEntriesParameter_.labEntriesParameterMapid),
						 rootLabEntriesParameter.get(LabEntriesParameter_.labEntriesParameterValue),
						 rootLabEntriesParameter.get(LabEntriesParameter_.labEntriesParameterDate),
						 join.get(LabParameters_.labParametersUnits))).where(paramDetails);
				
				cq1.orderBy(cb.desc(cb.coalesce(rootLabEntriesParameter.get(LabEntriesParameter_.labEntriesParameterDate),cb.function("to_timestamp",Timestamp.class,cb.literal("1900-05-13 16:40:35"),cb.literal("YYYY-MM-DD HH24:MI:SS")))),
						cb.desc(rootLabEntriesParameter.get(LabEntriesParameter_.labEntriesParameterId)));
				
				List<Object> obj=  new ArrayList<Object>();
				
				try{
					obj=em.createQuery(cq1).getResultList();
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
				List<LabEntriesParameter> labParameters=new ArrayList<LabEntriesParameter>();
				for(int i=0;i<obj.size();i++){
					LabEntriesParameter templabEntriesParameter = new LabEntriesParameter();
					LabParametersData labParametersData = (LabParametersData) obj.get(i);
					templabEntriesParameter.setLabEntriesParameterMapid(labParametersData.getLabEntriesParameterMapid());
					templabEntriesParameter.setLabEntriesParameterValue(labParametersData.getLabEntriesParameterValue());
					templabEntriesParameter.setLabEntriesParameterDate((Timestamp) labParametersData.getLabEntriesParameterDate());
					LabParameters tempLabParam=new LabParameters();
					tempLabParam.setLabParametersUnits(labParametersData.getLabParametersUnits());
					templabEntriesParameter.setLabParametersTable(tempLabParam);
					labParameters.add(templabEntriesParameter);
				}
				
				
//				List<LabParameters> paramIdsArray=labParametersRepository.findAll(Specifications.where(InvestigationSpecification.paramIdsLabParameter(paramIdsGroup)));
				CriteriaBuilder cb1 = em.getCriteriaBuilder();
				CriteriaQuery<Object> cq = cb1.createQuery();
				Root<LabParameters> rootLabParameters = cq.from(LabParameters.class);
				Predicate testIds = rootLabParameters.get(LabParameters_.labParametersId).in((Object[])paramIdsGroup);
				cq.select(rootLabParameters.get(LabParameters_.labParametersId)).where(testIds);
				List<Object> paramIdList=  em.createQuery(cq).getResultList();
				List<Integer> paramIdsArray = new ArrayList<Integer>();
				for(int i=0;i<paramIdList.size();i++){
						int paramId=(int) paramIdList.get(i);
						paramIdsArray.add(paramId);
				}
				
				if(paramIdsArray.size()>0){
					String[] groupId=new String[1];
					groupId[0]=hsh_param.getParamStandardGroupId()+"";

					//getting list of override alerts having group id
					
//					overridealertsRepository.findAll(OverridealertsSpecification.overrideAlerts(groupId, 2, patientId, flowsheetId,encounterId,encounterEntity));
					CriteriaBuilder cb2 = em.getCriteriaBuilder();
					CriteriaQuery<Object> cq2 = cb1.createQuery();
					Root<Overridealerts> rootOverridealerts = cq2.from(Overridealerts.class);
					Predicate elementType=cb2.equal(rootOverridealerts.get(Overridealerts_.overridealertsFlowsheetElementType),2);
					Predicate fsId=cb2.equal(rootOverridealerts.get(Overridealerts_.overridealertsFlowsheetMapId),flowsheetId);
					Predicate elementId=rootOverridealerts.get(Overridealerts_.overridealertsFlowsheetElementId).in((Object[])groupId);
					Predicate patientIdPred = cb2.equal(rootOverridealerts.get(Overridealerts_.patientid),patientId);
					Predicate overrideAlerts=null;
					if((encounterId!=-1)&&getEncounterStatusforLab(encounterId)==3){
							Predicate encounterDatePred=cb2.lessThanOrEqualTo(rootOverridealerts.get(Overridealerts_.overriddenOn), getencounterDateforLab(encounterId));
							overrideAlerts=cb2.and(elementType,fsId,elementId,patientIdPred,encounterDatePred);
					}else{
							overrideAlerts=cb2.and(elementType,fsId,elementId,patientIdPred);
					}
									
					cq2.multiselect(cb.construct(OverrideAlertsBean.class,
							rootOverridealerts.get(Overridealerts_.overridealertsFlowsheetElementId),
							rootOverridealerts.get(Overridealerts_.reason),
							rootOverridealerts.get(Overridealerts_.overriddenOn))).where(overrideAlerts);
							
					List<Object> alertsObj=  em.createQuery(cq2).getResultList();
					
					
					List<OverrideAlertsBean> overridealertsInt=new ArrayList<OverrideAlertsBean>();
					for(int i=0;i<alertsObj.size();i++){
						OverrideAlertsBean overridealertsData = (OverrideAlertsBean) alertsObj.get(i);
						OverrideAlertsBean overridealertsDataBean = new OverrideAlertsBean(overridealertsData.getOverridealertsFlowsheetElementId(),
								overridealertsData.getReason(),
								overridealertsData.getOverriddenOn());
						overridealertsInt.add(overridealertsDataBean);
					}
					
					for(int k=0;k<overridealertsInt.size();k++){
						LabEntriesParameter tempLabEntriesParam=new LabEntriesParameter();
						tempLabEntriesParam.setLabEntriesParameterMapid(Integer.parseInt(Optional.fromNullable(overridealertsInt.get(k).getOverridealertsFlowsheetElementId()).or("-1").toString()));
						String notes="Reason to Override: "+Optional.fromNullable(overridealertsInt.get(k).getReason()).or("-").toString();
						tempLabEntriesParam.setLabEntriesParameterValue(notes);
						tempLabEntriesParam.setLabEntriesParameterDate((Timestamp)overridealertsInt.get(k).getOverriddenOn());
						LabParameters param=new LabParameters();
						param.setLabParametersUnits("");
						tempLabEntriesParam.setLabParametersTable(param);
						labParameters.add(tempLabEntriesParam);
					}
				}
				//Order Labs by Date
				labParameters=orderByDateParam(labParameters, true);
				if(labParameters.size()>0){
					for(int k=0;k<labParameters.size();k++){
						paramBean = new FS_LabParameterBean();
						paramName = Optional.fromNullable(hsh_param.getParamStandardGroupName()).or("");
						paramBean.setParamName(paramName);
						paramBean.setGroupId(hsh_param.getParamStandardGroupId());
						String paramId=MoreObjects.firstNonNull(labParameters.get(k).getLabEntriesParameterMapid(),"").toString();
						paramBean.setParamId(paramId);
						paramBean.setRecentlyPerf(true);
						String perOn="Never Performed";
						paramBean.setParamValue(labParameters.get(k).getLabEntriesParameterValue());
						perOn=MoreObjects.firstNonNull(labParameters.get(k).getLabEntriesParameterDate(),"Never Performed").toString();
						if(!perOn.trim().contentEquals("Never Performed")){
							DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
							Date date = (Date) parser.parse(perOn);
							DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
							perOn=formatter.format(date);
						}
						paramBean.setPerfOn(perOn);
						if(!paramPerformedDate.contains(perOn))
							paramPerformedDate.add(perOn);
						String units="";
						units=Optional.fromNullable(labParameters.get(k).getLabEntriesParameterValue()).or("");
						if(!units.contentEquals("")){
							units=labParameters.get(k).getLabParametersTable().getLabParametersUnits();
						}
						paramBean.setUnits(units);
						arr_paramTemp.add(paramBean);
					} 
				}else{
					paramBean = new FS_LabParameterBean();
					paramName = Optional.fromNullable(hsh_param.getParamStandardGroupName()).or("");
					paramBean.setParamName(paramName);
					paramBean.setGroupId(hsh_param.getParamStandardGroupId());
					paramBean.setParamId("");
					paramBean.setRecentlyPerf(true);
					String perOn="Never Performed";
					if(!paramPerformedDate.contains(perOn))
						paramPerformedDate.add(perOn);
					paramBean.setPerfOn(perOn);
					paramBean.setParamValue("");
					paramBean.setUnits("");
					arr_paramTemp.add(paramBean);
				}
			}
			for(int h=0;h<arr_paramTemp.size();h++){
				arr_paramTemp.get(h).setParamId(paramIdsTemp);
			}
			arr_param.addAll(arr_paramTemp);
			prevParamName = hsh_param.getParamStandardGroupName(); 
		}
		paramPerformedDate= sorttoascending(paramPerformedDate,"Never Performed");
		Collections.reverse(paramPerformedDate);
		paramPerformedDate = limitthecount(paramPerformedDate,11);
		if(groupIds.size()>0)
			arr_param = checkFlowSheetRulesLabParametersData(patientId,chartId, groupIds, arr_param, startDate,groupIdParamIdMap,patgender);
		return arr_param;
	}

	public ArrayList<FS_LabParameterBean> checkFlowSheetRulesLabParametersData(int patientId, int chartId, List<Integer> groupIds,ArrayList<FS_LabParameterBean> flowSheetData, String startDate,JSONObject groupIdParamIdMap,Integer patgender) throws Exception{
		String currentDate = getCurrentDate();
		ArrayList<FS_LabParameterBean> tempFlowSheetData = new ArrayList<FS_LabParameterBean>();
		List<String> newGroupIds=Lists.transform(groupIds, Functions.toStringFunction());
		if(startDate!=null && !startDate.equalsIgnoreCase("")){
		List<HmrBean> flowSheetRule = getFlowSheetRuleQueryPatientBased(patientId,newGroupIds, startDate,2);
		String today = getTodayDate();
		//PatientRegistration genderPat =  patientRegistrationRepository.findOne(Specifications.where(PatientRegistrationSpecification.PatientId(patientId+"")));
		String gender = patgender.toString();
		//ArrayList<FS_LabParameterBean> tempFlowSheetData = new ArrayList<FS_LabParameterBean>();
		for(int flowSheetDataCounter = 0 ; flowSheetDataCounter < flowSheetData.size() ; flowSheetDataCounter++){
			FS_LabParameterBean flowSheetDataObj = flowSheetData.get(flowSheetDataCounter);
			boolean isElementRuleExists = false;
			boolean isElementGenderRuleSatisfied = false;
			flowSheetDataObj.setDueCriteria("");
			//Checking Rule for Lab Parameters
			for(int dueRulesCounter = 0 ; dueRulesCounter < flowSheetRule.size() ; dueRulesCounter++ ){
				HmrBean flowSheetRuleObj = flowSheetRule.get(dueRulesCounter);
				String paramIds="";
				try{
					paramIds=MoreObjects.firstNonNull(groupIdParamIdMap.get(Optional.fromNullable(flowSheetRuleObj.getElement_id()).or((long) -1).toString()),"-1").toString();
				}catch(Exception e){
					paramIds="-1";
				}
				if(Optional.fromNullable(flowSheetRuleObj.getElement_type()).or(-1)==2 && (paramIds.contains("@@@@"+Optional.fromNullable(flowSheetDataObj.getParamId()).or("-2").toString()+"###"))){
					isElementRuleExists = true;
					if(Optional.fromNullable(flowSheetRuleObj.getGender()).or(-1)==0 || gender.equals(Optional.fromNullable(flowSheetRuleObj.getGender()).or(-1).toString()))
					{
						isElementGenderRuleSatisfied = true;
						if(( DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(today, flowSheetRuleObj.getEnddate().toString()) == -1 ) || DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 0){
							if(flowSheetRuleObj.getSchType()!=null && flowSheetRuleObj.getSchedule()!=null ){
								if((flowSheetRuleObj.getSchType().toString().equals("") || flowSheetRuleObj.getSchedule().toString().equals(""))){
									if(!flowSheetDataObj.getPerfOn().equals("Never Performed")){
										if(DateUtil.dateDiffVal(flowSheetDataObj.getPerfOn().toString().replace("-", "/"), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerfOn().toString().replace("-", "/"), flowSheetRuleObj.getEnddate().toString()) == -1){
											flowSheetDataObj.setDue("Completed");
											flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or(""));
											break;
										}
									}									
									flowSheetDataObj.setDue("Due Now");
									flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or(""));
									break;
								}
								if(!flowSheetRuleObj.getSchType().toString().equals("") && !flowSheetRuleObj.getSchedule().toString().equals("")){
									String freqStartDate = flowSheetRuleObj.getStartdate().toString();
									String freqEndDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(freqStartDate)), 3);
									while(DateUtil.dateDiffVal( flowSheetRuleObj.getEnddate().toString() , freqEndDate) == 1){
										freqEndDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(freqStartDate)), 3);
										if(DateUtil.dateDiffVal(freqEndDate, flowSheetRuleObj.getEnddate().toString()) == 1){
										} else {
											//Due Checking for schedule Range
											if(( DateUtil.dateDiffVal(today, freqStartDate ) == 1 && DateUtil.dateDiffVal(today, freqEndDate) == -1 ) || DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 0){
												if(!flowSheetDataObj.getPerfOn().equals("Never Performed")){
													//here change the code for last performed append last performed + due period 

													if(flowSheetRuleObj.getRuledate().equals("3")){
														String rulePerformedDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(flowSheetDataObj.getPerfOn().toString().replace("-", "/"))), 3);
														if(DateUtil.dateDiffVal(currentDate,rulePerformedDate) == -1){
															flowSheetDataObj.setDue("Completed");
															flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or(""));
															break;
														}
													}else{
														if(DateUtil.dateDiffVal(flowSheetDataObj.getPerfOn().toString().replace("-", "/"), freqStartDate) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerfOn().toString().replace("-", "/"), freqEndDate) == -1){
															flowSheetDataObj.setDue("Completed");
															flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or(""));
															break;
														}
													}
												}					
												flowSheetDataObj.setDue("Due Now");
												flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or(""));
												break;
											} else if(DateUtil.dateDiffVal(today, freqStartDate ) == 1 && DateUtil.dateDiffVal(today, freqEndDate ) == 1){

												flowSheetDataObj.setDue("Over Due");
												if(!flowSheetDataObj.getPerfOn().equals("Never Performed")){
													if(DateUtil.dateDiffVal(flowSheetDataObj.getPerfOn().toString().replace("-", "/"), freqStartDate ) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerfOn().toString().replace("-", "/"), freqEndDate) == -1){
														flowSheetDataObj.setDue("Completed");
														flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or(""));
													}
												}		

												if(!flowSheetDataObj.getPerfOn().equals("Never Performed")){
													if(DateUtil.dateDiffVal(flowSheetDataObj.getPerfOn().toString().replace("-", "/"), freqStartDate) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerfOn().toString().replace("-", "/"), freqEndDate ) == 1){
														flowSheetDataObj.setDue("");
														flowSheetDataObj.setDueCriteria("");
													}
												}	
											} 
											freqStartDate = freqEndDate;
										}
									}
								}
							}
						} else if(DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(today, flowSheetRuleObj.getEnddate().toString()) == 1){
							flowSheetDataObj.setDue("Over Due");
							flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or(""));
							if(!flowSheetDataObj.getPerfOn().equals("Never Performed")){
								if(DateUtil.dateDiffVal(flowSheetDataObj.getPerfOn().toString().replace("-", "/"), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerfOn().toString().replace("-", "/"), flowSheetRuleObj.getEnddate().toString()) == -1){
									flowSheetDataObj.setDue("Completed");
									flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or(""));
								}
							}		
							if(!flowSheetDataObj.getPerfOn().equals("Never Performed")){
								if(  ( DateUtil.dateDiffVal(flowSheetDataObj.getPerfOn().toString().replace("-", "/"), flowSheetRuleObj.getStartdate().toString()) == -1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerfOn().toString().replace("-", "/"), flowSheetRuleObj.getEnddate().toString()) == -1) ||  ( DateUtil.dateDiffVal(flowSheetDataObj.getPerfOn().toString().replace("-", "/"), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerfOn().toString().replace("-", "/"), flowSheetRuleObj.getEnddate().toString()) == 1)){
									flowSheetDataObj.setDue("");
									flowSheetDataObj.setDueCriteria("");
								}
							}	
						} 
					} 
				}
			}
			if(isElementRuleExists == false){
				tempFlowSheetData.add(flowSheetDataObj);
			} else if(isElementRuleExists == true && isElementGenderRuleSatisfied == true){
				tempFlowSheetData.add(flowSheetDataObj);
			}
		}
		}
		return tempFlowSheetData;
	}

	public String getCurrentDate(){
		Query query = em.createNativeQuery("SELECT to_char(now(),'MM/dd/yyyy HH:MI:ss am')");
		return query.getSingleResult().toString(); 
	}

	public String getTodayDate(){
		Query query = em.createNativeQuery("SELECT to_char(now(),'MM/dd/yyyy HH:MI:ss am')");
		return query.getSingleResult().toString(); 
	}

	@SuppressWarnings("rawtypes")
	public List<HmrBean> getFlowSheetRuleQueryPatientBased(Integer patientId,List<String> groupIds, String startDate,Integer flowsheetElementType) throws java.text.ParseException{
		//Query 1
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<HmrBean> cq = builder.createQuery(HmrBean.class);
		Root<HmrTests> root = cq.from(HmrTests.class);
		Join<HmrTests, HmrCategories> hmr=root.join("hmrTestsTable",JoinType.LEFT);
		Join<HmrTests, HmrTestDetail> hmr1=root.join("hmrTestDetailTable",JoinType.LEFT);
		Join<HmrTests, EmployeeProfile> hmr2=root.join("empProfileHmrTestsModifiedByTable",JoinType.LEFT);
		Join<HmrTests, EmployeeProfile> hmr3=root.join("empProfileHmrTestsCreatedByTable",JoinType.LEFT);
		Join<Join<HmrTests, HmrCategories>, HmrGroups> hmr4=hmr.join("hmrGroupsTable",JoinType.LEFT);
		Selection[] selections= new Selection[] { 
				hmr.get( HmrCategories_.hmrCategoriesId ),
				hmr.get( HmrCategories_.hmrCategoriesDescrip ),
				root.get( HmrTests_.hmrTestsId ),
				root.get( HmrTests_.hmrTestsDescription ),
				root.get( HmrTests_.hmrTestsGender ),
				root.get( HmrTests_.hmrTestsCpt ),
				root.get( HmrTests_.hmrTestsDxs ),
				builder.coalesce(hmr1.get(HmrTestDetail_.hmrTestDetailFrom),0),
				builder.coalesce(hmr1.get(HmrTestDetail_.hmrTestDetailTo),0),
				root.get( HmrTests_.hmrTestsFlowsheetElementId ),
				root.get( HmrTests_.hmrTestsFlowsheetElementType ),
				builder.coalesce(hmr1.get(HmrTestDetail_.hmrTestDetailFrom),0),
				builder.coalesce(hmr1.get(HmrTestDetail_.hmrTestDetailTo),0),
				hmr1.get(HmrTestDetail_.hmrTestDetailDurationType),
				hmr1.get(HmrTestDetail_.hmrTestDetailSchedule),
				hmr1.get(HmrTestDetail_.hmrTestDetailScheduleType),
				root.get( HmrTests_.hmrTestsDxDesc ),
				root.get( HmrTests_.hmrTestsIsActive ),
				root.get( HmrTests_.hmrTestsIsDxBased ),
				root.get( HmrTests_.hmrTestsComments ),
				root.get( HmrTests_.hmrTestsMaxAge ),
				hmr2.get(EmployeeProfile_.empProfileFullname),
				builder.function("to_char", String.class, root.get( HmrTests_.hmrTestsModifiedOn),builder.literal("MM/DD/YYYY")),
				hmr4.get(HmrGroups_.hmrGroupName),
				hmr1.get(HmrTestDetail_.hmrTestDetailSortBy),
				hmr3.get(EmployeeProfile_.empProfileFullname),
				builder.function("to_char", String.class, root.get( HmrTests_.hmrTestsCreatedOn ),builder.literal("MM/DD/YYYY")),
				hmr1.get(HmrTestDetail_.hmrTestDetailIsActive),
				hmr1.get(HmrTestDetail_.hmrTestDetailDescription),
				builder.coalesce(root.get( HmrTests_.hmrTestsRuledate ),-1)
		};
		cq.select(builder.construct(HmrBean.class,selections));
		cq.distinct(true);
		//Start Tag: Framing Sub Query
		Subquery<String> subquery = cq.subquery(String.class);
		Root<HmrPatientinstanceparameters> subqueryFrom = subquery.from(HmrPatientinstanceparameters.class);
		Expression<String> expSelectSub = builder.concat(builder.concat(subqueryFrom.<String>get("hmrPatientinstanceparametersFlowsheetElementType"), ","),subqueryFrom.<String>get("hmrPatientinstanceparametersFlowsheetElementId"));
		subquery.select(expSelectSub);
		Predicate[] restrictionsSub = new Predicate[] {
				builder.equal(subqueryFrom.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersPatientid ),patientId),
				builder.equal(subqueryFrom.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersFlowsheetElementType),flowsheetElementType),
		};
		subquery.where(restrictionsSub);
		Expression<String> exp1 = builder.concat(builder.concat(root.<String>get("hmrTestsFlowsheetElementType"), ","),root.<String>get("hmrTestsFlowsheetElementId"));
		//End Tag: Framing Sub Query
		Predicate[] restrictions = new Predicate[] {
				root.get( HmrTests_.hmrTestsFlowsheetElementId ).in(groupIds),
				builder.equal(root.get( HmrTests_.hmrTestsFlowsheetElementType),flowsheetElementType),
				builder.or(builder.notEqual(hmr1.get(HmrTestDetail_.hmrTestDetailIsPreferredAge),1), builder.isNull(hmr1.get(HmrTestDetail_.hmrTestDetailIsPreferredAge))),
				builder.in(exp1).value(subquery).not()
		};
		cq.where(restrictions);
		List<HmrBean> confData=new ArrayList<HmrBean>();
		try{
			confData=em.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfFinal = new SimpleDateFormat("MM/dd/yyyy 01:mm:ss");
		for(int i=0;i<confData.size();i++){
			HmrBean bean=confData.get(i);
			// For Start Date
			String startDateContent="";
			CriteriaBuilder builderStart = em.getCriteriaBuilder();
			CriteriaQuery<Object> cqStart = builderStart.createQuery();
			cqStart.from(HmrTests.class);
			if((bean.getDurType()!=null)&&bean.getDurType().toString().contentEquals("1")){
				String dt = startDate;  
				try {
					if(!startDate.contentEquals("")){
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(dt));
						c.add(Calendar.MONTH,  Integer.parseInt(Optional.fromNullable(bean.getStartdate()).or("0")));  // number of days to add
						startDateContent = sdfFinal.format(c.getTime())+" am";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if((bean.getDurType()!=null)&&bean.getDurType().toString().contentEquals("2")){
				String dt = startDate;  
				try {
					if(!startDate.contentEquals("")){
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(dt));
						c.add(Calendar.YEAR,  Integer.parseInt(Optional.fromNullable(bean.getStartdate()).or("0")));  // number of days to add
						startDateContent = sdfFinal.format(c.getTime())+" am";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if((bean.getDurType()!=null)&&bean.getDurType().toString().contentEquals("0")){
				String dt = startDate; 
				try {
					if(!startDate.contentEquals("")){
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(dt));
						c.add(Calendar.DATE,  Integer.parseInt(Optional.fromNullable(bean.getStartdate()).or("0")));  // number of days to add
						startDateContent = sdfFinal.format(c.getTime())+" am";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			confData.get(i).setStartdate(startDateContent);

			//For End date
			String endDateContent="";
			CriteriaBuilder builderEnd = em.getCriteriaBuilder();
			CriteriaQuery<Object> cqEnd = builderEnd.createQuery();
			cqEnd.from(HmrTests.class);
			if((bean.getDurType()!=null)&&bean.getDurType().toString().contentEquals("1")){
				String dt = startDate; 
				try {
					if(!startDate.contentEquals("")){
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(dt));
						if((bean.getToAge()!=null)&&(!bean.getToAge().toString().contentEquals("0")))
							c.add(Calendar.MONTH, Integer.parseInt(Optional.fromNullable(bean.getEnddate()).or("0")));  
						else
							c.add(Calendar.YEAR, Optional.fromNullable(bean.getMaxyrs()).or(0));  
						endDateContent = sdfFinal.format(c.getTime())+" am";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if((bean.getDurType()!=null)&&bean.getDurType().toString().contentEquals("2")){
				String dt = startDate;  
				try {
					if(!startDate.contentEquals("")){
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(dt));
						if((bean.getToAge()!=null)&&(!bean.getToAge().toString().contentEquals("0")))
							c.add(Calendar.YEAR, Integer.parseInt(Optional.fromNullable(bean.getEnddate()).or("0")));  
						else
							c.add(Calendar.YEAR,Optional.fromNullable(bean.getMaxyrs()).or(0));  
						endDateContent = sdfFinal.format(c.getTime())+" am";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if((bean.getDurType()!=null)&&bean.getDurType().toString().contentEquals("0")){
				String dt = startDate;  
				try {
					if(!startDate.contentEquals("")){
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(dt));
						if((bean.getToAge()!=null)&&(!bean.getToAge().toString().contentEquals("0")))
							c.add(Calendar.DATE, Integer.parseInt(Optional.fromNullable(bean.getEnddate()).or("0")));  
						else
							c.add(Calendar.YEAR, Optional.fromNullable(bean.getMaxyrs()).or(0));  
						endDateContent = sdfFinal.format(c.getTime())+" am";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			confData.get(i).setEnddate(endDateContent);
		}

		//Query 2
		CriteriaBuilder builder1 = em.getCriteriaBuilder();
		CriteriaQuery<HmrBean> cq1 = builder1.createQuery(HmrBean.class);
		Root<HmrPatientinstanceparameters> root1 = cq1.from(HmrPatientinstanceparameters.class);
		Join<HmrPatientinstanceparameters, HmrCategories> hmrQ=root1.join("hmrCategoriesTable",JoinType.LEFT);
		Join<HmrPatientinstanceparameters, HmrPatientinstance> hmrQ1=root1.join("hmrPatientinstanceTable",JoinType.LEFT);
		Join<HmrPatientinstanceparameters, EmployeeProfile> hmrQ2=root1.join("empProfileHmrTestsModifiedByTable",JoinType.LEFT);
		Join<HmrPatientinstanceparameters, EmployeeProfile> hmrQ3=root1.join("empProfileHmrTestsCreatedByTable",JoinType.LEFT);
		Join<Join<HmrPatientinstanceparameters, HmrCategories>, HmrGroups> hmrQ4=hmrQ.join("hmrGroupsTable",JoinType.LEFT);
		Selection[] selections1= new Selection[] { 
				hmrQ.get( HmrCategories_.hmrCategoriesId ),
				hmrQ.get( HmrCategories_.hmrCategoriesDescrip ),
				root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersTestid ),
				root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersTestDesc ),
				root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersGender ),
				root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersBillingcode ),
				root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersDxs ),
				builder1.coalesce(hmrQ1.get(HmrPatientinstance_.hmrPatientinstanceFromDuration),0),
				builder1.coalesce(hmrQ1.get(HmrPatientinstance_.hmrPatientinstanceToDuration),0),
				root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersFlowsheetElementId ),
				root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersFlowsheetElementType ),
				builder1.coalesce(hmrQ1.get(HmrPatientinstance_.hmrPatientinstanceFromDuration),0),
				builder1.coalesce(hmrQ1.get(HmrPatientinstance_.hmrPatientinstanceToDuration),0),
				hmrQ1.get(HmrPatientinstance_.hmrPatientinstanceDurationType),
				hmrQ1.get(HmrPatientinstance_.hmrPatientinstanceSchedule),
				hmrQ1.get(HmrPatientinstance_.hmrPatientinstanceScheduleType),
				root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersDxdesc ),
				root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersIsActive ),
				root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersIsdxbased ),
				root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersComments ),
				root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersMaxagetoshowalert ),
				hmrQ2.get(EmployeeProfile_.empProfileFullname),
				builder1.function("to_char", String.class, root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersModifiedOn),builder.literal("MM/DD/YYYY")),
				hmrQ4.get(HmrGroups_.hmrGroupName),
				hmrQ1.get(HmrPatientinstance_.hmrPatientinstanceSortby),
				hmrQ3.get(EmployeeProfile_.empProfileFullname),
				builder1.function("to_char", String.class, root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersInitiatedOn ),builder.literal("MM/DD/YYYY")),
				hmrQ1.get(HmrPatientinstance_.hmrPatientinstanceIsactive),
				hmrQ1.get(HmrPatientinstance_.hmrPatientinstanceDescription),
				builder1.coalesce(root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersRuledate ),-1)
		};
		cq1.select(builder1.construct(HmrBean.class,selections1));
		cq1.distinct(true);
		Predicate[] restrictions1 = new Predicate[] {
				builder1.equal(root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersFlowsheetElementType),flowsheetElementType),
				builder1.or(builder1.notEqual(hmrQ1.get(HmrPatientinstance_.hmrPatientinstancePreferredAge),1), builder1.isNull(hmrQ1.get(HmrPatientinstance_.hmrPatientinstancePreferredAge))),
				builder1.equal(root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersPatientid ),patientId)
		};
		cq1.where(restrictions1);
		List<HmrBean> confData1=new ArrayList<HmrBean>();
		try{
			confData1=em.createQuery(cq1).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int i=0;i<confData1.size();i++){
			HmrBean bean=confData1.get(i);
			// For Start Date
			String startDateContent="";
			CriteriaBuilder builderStart = em.getCriteriaBuilder();
			CriteriaQuery<Object> cqStart = builderStart.createQuery();
			cqStart.from(HmrTests.class);
			if((confData1.get(i).getDurType()!=null)&&confData1.get(i).getDurType().toString().contentEquals("1")){
				String dt = startDate;  
				try {
					if(!startDate.contentEquals("")){
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(dt));
						c.add(Calendar.MONTH,Integer.parseInt(Optional.fromNullable(bean.getStartdate()).or("0")));  // number of days to add
						startDateContent = sdfFinal.format(c.getTime())+" am";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}else if((confData1.get(i).getDurType()!=null)&&confData1.get(i).getDurType().toString().contentEquals("2")){
				String dt = startDate;  
				try {
					if(!startDate.contentEquals("")){
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(dt));
						c.add(Calendar.YEAR, Integer.parseInt(Optional.fromNullable(bean.getStartdate()).or("0")));  // number of days to add
						startDateContent = sdfFinal.format(c.getTime())+" am";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if((confData1.get(i).getDurType()!=null)&&confData1.get(i).getDurType().toString().contentEquals("0")){
				String dt = startDate; 
				try {
					if(!startDate.contentEquals("")){
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(dt));
						c.add(Calendar.DATE,Integer.parseInt(Optional.fromNullable(bean.getStartdate()).or("0")));  // number of days to add
						startDateContent = sdfFinal.format(c.getTime())+" am";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			confData1.get(i).setStartdate(startDateContent);

			//For End date
			String endDateContent="";
			CriteriaBuilder builderEnd = em.getCriteriaBuilder();
			CriteriaQuery<Object> cqEnd = builderEnd.createQuery();
			cqEnd.from(HmrTests.class);
			if((confData1.get(i).getDurType()!=null)&&confData1.get(i).getDurType().toString().contentEquals("1")){
				String dt = startDate; 
				try {
					if(!startDate.contentEquals("")){
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(dt));
						if((confData1.get(i).getToAge()!=null)&&(!confData1.get(i).getToAge().toString().contentEquals("0")))
							c.add(Calendar.MONTH,  Integer.parseInt(Optional.fromNullable(bean.getEnddate()).or("0")));  
						else
							c.add(Calendar.YEAR, Optional.fromNullable(bean.getMaxyrs()).or(0));  
						endDateContent = sdfFinal.format(c.getTime())+" am";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if((confData1.get(i).getDurType()!=null)&&confData1.get(i).getDurType().toString().contentEquals("2")){
				String dt = startDate;  
				try {
					if(!startDate.contentEquals("")){
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(dt));
						if((confData1.get(i).getToAge()!=null)&&(!confData1.get(i).getToAge().toString().contentEquals("0")))
							c.add(Calendar.YEAR, Integer.parseInt(Optional.fromNullable(bean.getEnddate()).or("0")));  
						else
							c.add(Calendar.YEAR, Optional.fromNullable(bean.getMaxyrs()).or(0));  
						endDateContent = sdfFinal.format(c.getTime())+" am";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if((confData1.get(i).getDurType()!=null)&&confData1.get(i).getDurType().toString().contentEquals("0")){
				String dt = startDate;  
				try {
					if(!startDate.contentEquals("")){
						Calendar c = Calendar.getInstance();
						c.setTime(sdf.parse(dt));
						if((confData1.get(i).getToAge()!=null)&&(!confData1.get(i).getToAge().toString().contentEquals("0")))
							c.add(Calendar.DATE, Integer.parseInt(Optional.fromNullable(bean.getEnddate()).or("0")));  
						else
							c.add(Calendar.YEAR, Optional.fromNullable(bean.getMaxyrs()).or(0));  
						endDateContent = sdfFinal.format(c.getTime())+" am";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			confData1.get(i).setEnddate(endDateContent);
		}

		//Union
		confData.addAll(confData1);
		//Order by AlertName
		confData=orderByInitial(confData);
		//Order by AlertName,sortby 
		confData=orderByFurtherInt(confData,"ASC");
		return confData;
	}

	public List<HmrBean> orderByInitial(List<HmrBean> confData){
		if (confData.size() > 0) {
			Collections.sort(confData, new Comparator<HmrBean>() {
				@Override
				public int compare(final HmrBean object1, final HmrBean object2) {
					return object1.getAlertName().toString().compareToIgnoreCase(object2.getAlertName().toString());
				}
			} );
		}
		return confData;
	}

	public List<HmrBean> orderBySortBy(List<HmrBean> confData){
		if (confData.size() > 0) {
			Collections.sort(confData, new Comparator<HmrBean>() {
				@Override
				public int compare(final HmrBean object1, final HmrBean object2) {
					return object1.getSortby().toString().compareToIgnoreCase(object2.getSortby().toString());
				}
			} );
		}
		return confData;
	}

	public List<HmrBean> orderByReferences(List<HmrBean> confData){
		if (confData.size() > 0) {
			Collections.sort(confData, new Comparator<HmrBean>() {
				@Override
				public int compare(final HmrBean object1, final HmrBean object2) {
					return object1.getReferences().toString().compareToIgnoreCase(object2.getReferences().toString());
				}
			} );
		}
		return confData;
	}

	public List<HmrBean> orderByFurtherInt(List<HmrBean> confData,final String acsDesc){
		String prevValue="",currentValue="";
		List<HmrBean> confDataTemp = new ArrayList<HmrBean>();
		List<HmrBean> confDataFinal = new ArrayList<HmrBean>();
		for(int j=0;j<confData.size();j++){
			if(j==0)
				prevValue=confData.get(j).getAlertName().toString();
			currentValue=confData.get(j).getAlertName().toString();
			if(!prevValue.equalsIgnoreCase(currentValue)){
				if(confDataTemp.size() > 0){
					Collections.sort(confDataTemp, new Comparator<HmrBean>() {
						@Override
						public int compare(final HmrBean object1, final HmrBean object2) {
							if(acsDesc.equalsIgnoreCase("DESC")){
								if(Integer.parseInt(object1.getSortby().toString())>Integer.parseInt(object2.getSortby().toString()))
									return -1;
								else if(Integer.parseInt(object1.getSortby().toString())==Integer.parseInt(object2.getSortby().toString()))
									return 0;
								else
									return 1;
							}else{
								if(Integer.parseInt(object1.getSortby().toString())<Integer.parseInt(object2.getSortby().toString()))
									return -1;
								else if(Integer.parseInt(object1.getSortby().toString())==Integer.parseInt(object2.getSortby().toString()))
									return 0;
								else
									return 1;
							}
						}
					} );
				}
				if(confDataFinal.size()==0){
					confDataFinal=confDataTemp;
				}else{
					for(int k=0;k<confDataTemp.size();k++){
						confDataFinal.add(confDataTemp.get(k));
					}
				}
				confDataTemp= new ArrayList<HmrBean>();
				confDataTemp.add(confData.get(j));
				if((confDataTemp.size()==1)&&(j==(confData.size()-1)))
					confDataFinal.add(confDataTemp.get(0));
			}else{
				confDataTemp.add(confData.get(j));
				if(j==0&&j==(confData.size()-1))
					confDataFinal=confDataTemp;
			}
			prevValue=confData.get(j).getAlertName().toString();
		}
		return confDataFinal;
	}

	public List<FlowsheetNameBean> orderByType(List<FlowsheetNameBean> confData){
		if (confData.size() > 0) {
			Collections.sort(confData, new Comparator<FlowsheetNameBean>() {
				@Override
				public int compare(final FlowsheetNameBean object1, final FlowsheetNameBean object2) {
					return Integer.toString(object1.getFlowsheetType()).compareToIgnoreCase(Integer.toString(object2.getFlowsheetType()));
				}
			} );
		}
		return confData;
	}

	public ArrayList<FS_LabBean> orderByName(ArrayList<FS_LabBean> confData){
		if (confData.size() > 0) {
			Collections.sort(confData, new Comparator<FS_LabBean>() {
				@Override
				public int compare(final FS_LabBean object1, final FS_LabBean object2) {
					return object1.getLabName().toString().compareToIgnoreCase(object2.getLabName().toString());
				}
			} );
		}
		return confData;
	}

	public ArrayList<String> sorttoascending(ArrayList<String> vitalparamdate,String exceptioncase) throws Exception{
		ArrayList<String> returnvitalparamdate = new ArrayList<String>(); 
		ArrayList<FlowsheetDateBean> FlowsheetDateBeans = new ArrayList<FlowsheetDateBean>();
		Boolean neverdocument = false;
		for(String vitaldate : vitalparamdate){
			if(!vitaldate.equalsIgnoreCase(exceptioncase)){
				String vitaldatepairs[] = vitaldate.replace("-", "/").split("/");
				FlowsheetDateBeans.add(new FlowsheetDateBean(Integer.parseInt(vitaldatepairs[2]),Integer.parseInt(vitaldatepairs[0]),Integer.parseInt(vitaldatepairs[1])));
			}else{
				neverdocument = true;
			}
		}
		DateComparator comparator = new DateComparator(); 
		Collections.sort(FlowsheetDateBeans,comparator);

		for(FlowsheetDateBean FlowsheetDateBeaninst : FlowsheetDateBeans){
			String temp="";
			if(FlowsheetDateBeaninst.getMonth()<10)
				temp= "0"+FlowsheetDateBeaninst.getMonth()+"/";
			else
				temp= FlowsheetDateBeaninst.getMonth()+"/";
			if(FlowsheetDateBeaninst.getDay()<10)
				temp= temp + "0" +FlowsheetDateBeaninst.getDay()+"/";
			else
				temp= temp + FlowsheetDateBeaninst.getDay()+"/";
			temp= temp + FlowsheetDateBeaninst.getYear();
			returnvitalparamdate.add(temp);
		}
		if(neverdocument == true){
			returnvitalparamdate.add(exceptioncase);
		}
		return returnvitalparamdate;
	}

	private ArrayList<String> limitthecount(ArrayList<String> Date_grp_arr, int limit) {
		ArrayList<String> temp_Date_grp_arr = new ArrayList<String>();
		if(Date_grp_arr.size()<=limit)
			return Date_grp_arr;
		else{
			for(int count=0;count<limit;count++){
				temp_Date_grp_arr.add(Date_grp_arr.get(count));
			}
		}
		return temp_Date_grp_arr;
	}

	@SuppressWarnings("rawtypes")
	public List<LabDescription> getLabTestIds(String[][] codes,JSONObject groupIdTestIdMap)
			throws Exception {
		List<Integer> glenwoodCodes=new ArrayList<Integer>();
		List<String> loincCodes=new ArrayList<String>();
		List<String> snomedCodes=new ArrayList<String>();
		List<LabDescription> tempLabs=new ArrayList<LabDescription>();
		List<LabDescription> labs=new ArrayList<LabDescription>();
		for(int i=0;i<codes.length;i++){
			if(codes[i][1].contentEquals(FlowsheetServiceImpl.GlenwoodSystemsOID))
				glenwoodCodes.add(Integer.parseInt(codes[i][0]));
			else if(codes[i][1].contentEquals(FlowsheetServiceImpl.LoincOID))
				loincCodes.add(codes[i][0]);
			else if(codes[i][1].contentEquals(FlowsheetServiceImpl.SnomedOID))
				snomedCodes.add(codes[i][0]);
		}
		if(glenwoodCodes.size()!=0){
			labs.addAll(labDescriptionRepository.findAll(Specifications.where(InvestigationSpecification.testIdsLabDescription(glenwoodCodes.toArray(new Integer[0])))));
		}
		if(loincCodes.size()!=0){
			for(int y=0;y<loincCodes.size();y++){
				List<String> tempLoinc=new ArrayList<String>();
				tempLoinc.add(loincCodes.get(y));
				tempLabs=labDescriptionRepository.findAll(Specifications.where(InvestigationSpecification.loincCodeLabs(tempLoinc)));
				labs.addAll(tempLabs);
				String tempValue=loincCodes.get(y)+"###"+FlowsheetServiceImpl.LoincOID;
				for(int k=0;k<groupIdTestIdMap.length();k++){
					Iterator itr = groupIdTestIdMap.keys();
					while( itr.hasNext() ) {
						String key = (String)itr.next();
						if (groupIdTestIdMap.get(key).toString().contains("@@@@"+tempValue+"@@@@")){
							if(tempLabs.size()>0)
								groupIdTestIdMap.put(key, groupIdTestIdMap.get(key).toString().replace("@@@@"+tempValue+"@@@@","@@@@"+tempLabs.get(0).getLabDescriptionTestid()+"###"+FlowsheetServiceImpl.GlenwoodSystemsOID+"@@@@"));
							else
								groupIdTestIdMap.put(key, groupIdTestIdMap.get(key).toString().replace("@@@@"+tempValue+"@@@@","@@@@"));
							for(int p=1;p<tempLabs.size();p++){
								groupIdTestIdMap.put(key, groupIdTestIdMap.get(key).toString()+(tempLabs.get(p).getLabDescriptionTestid()+"###"+FlowsheetServiceImpl.GlenwoodSystemsOID+"@@@@"));
							}
						}
					}
				}
			}
			for(int y=0;y<loincCodes.size();y++){
				List<String> tempLoinc=new ArrayList<String>();
				tempLoinc.add(loincCodes.get(y));
				tempLabs=labDescriptionRepository.findAll(Specifications.where(InvestigationSpecification.hl7codes(tempLoinc, 51)));
				labs.addAll(tempLabs);
				String tempValue=loincCodes.get(y)+"###"+FlowsheetServiceImpl.LoincOID;
				for(int k=0;k<groupIdTestIdMap.length();k++){
					Iterator itr = groupIdTestIdMap.keys();
					while( itr.hasNext() ) {
						String key = (String)itr.next();
						if (groupIdTestIdMap.get(key).toString().contains("@@@@"+tempValue+"@@@@")){
							if(tempLabs.size()>0)
								groupIdTestIdMap.put(key, groupIdTestIdMap.get(key).toString().replace("@@@@"+tempValue+"@@@@","@@@@"+tempLabs.get(0).getLabDescriptionTestid()+"###"+FlowsheetServiceImpl.GlenwoodSystemsOID+"@@@@"));
							else
								groupIdTestIdMap.put(key, groupIdTestIdMap.get(key).toString().replace("@@@@"+tempValue+"@@@@","@@@@"));
							for(int p=1;p<tempLabs.size();p++){
								groupIdTestIdMap.put(key, groupIdTestIdMap.get(key).toString()+(tempLabs.get(p).getLabDescriptionTestid()+"###"+FlowsheetServiceImpl.GlenwoodSystemsOID+"@@@@"));
							}
						}
					}
				}
			}
		}
		if(snomedCodes.size()!=0){
			for(int y=0;y<snomedCodes.size();y++){
				List<String> tempSnomed=new ArrayList<String>();
				tempSnomed.add(snomedCodes.get(y));
				tempLabs=labDescriptionRepository.findAll(Specifications.where(InvestigationSpecification.hl7codes(tempSnomed, 54)));
				labs.addAll(tempLabs);
				String tempValue=snomedCodes.get(y)+"###"+FlowsheetServiceImpl.SnomedOID;
				for(int k=0;k<groupIdTestIdMap.length();k++){
					Iterator itr = groupIdTestIdMap.keys();
					while( itr.hasNext() ) {
						String key = (String)itr.next();
						if (groupIdTestIdMap.get(key).toString().contains("@@@@"+tempValue+"@@@@")){
							if(tempLabs.size()>0)
								groupIdTestIdMap.put(key, groupIdTestIdMap.get(key).toString().replace("@@@@"+tempValue+"@@@@","@@@@"+tempLabs.get(0).getLabDescriptionTestid()+"###"+FlowsheetServiceImpl.GlenwoodSystemsOID+"@@@@"));
							else
								groupIdTestIdMap.put(key, groupIdTestIdMap.get(key).toString().replace("@@@@"+tempValue+"@@@@","@@@@"));
							for(int p=1;p<tempLabs.size();p++){
								groupIdTestIdMap.put(key, groupIdTestIdMap.get(key).toString()+(tempLabs.get(p).getLabDescriptionTestid()+"###"+FlowsheetServiceImpl.GlenwoodSystemsOID+"@@@@"));
							}
						}
					}
				}
			}
		}
		return labs;
	}	

	@SuppressWarnings("rawtypes")
	public List<Integer> getParamIds(String[][] codes,JSONObject groupIdParamIdMap)
			throws Exception {
		List<Integer> glenwoodCodes=new ArrayList<Integer>();
		List<String> loincCodes=new ArrayList<String>();
		List<Integer> tempParams=new ArrayList<Integer>();
		List<Integer> params=new ArrayList<Integer>();
		for(int i=0;i<codes.length;i++){
			if(codes[i][1].contentEquals(FlowsheetServiceImpl.GlenwoodSystemsOID))
				glenwoodCodes.add(Integer.parseInt(codes[i][0]));
			else if(codes[i][1].contentEquals(FlowsheetServiceImpl.LoincOID))
				loincCodes.add(codes[i][0]);
		}
		if(glenwoodCodes.size()!=0){
//			params=labParametersRepository.findAll(Specifications.where(InvestigationSpecification.paramIdsLabParameter(glenwoodCodes.toArray(new Integer[0]))));
			
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<LabParameters> rootLabParameters = cq.from(LabParameters.class);
			
			Predicate testIds = rootLabParameters.get(LabParameters_.labParametersId).in((Object[])glenwoodCodes.toArray(new Integer[0]));
			cq.select(rootLabParameters.get(LabParameters_.labParametersId)).where(testIds);
			
			List<Object> obj=em.createQuery(cq).getResultList();
			for(int i=0;i<obj.size();i++){
				int paramid=(int) obj.get(i);
				params.add(paramid);
			}
		}
		if(loincCodes.size()!=0){
			
			
//				List<String> tempLoinc=new ArrayList<String>();
//				tempLoinc.add(loincCodes.get(y));
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<Object> cq = builder.createQuery();
				Root<LabParameters> rootLabParameters = cq.from(LabParameters.class);
				
				Join<LabParameters,LabParameterCode> join=rootLabParameters.join("labParameterCodeTable",JoinType.INNER);
				cq.distinct(true);
				Predicate loincCode = builder.and(join.get(LabParameterCode_.labParameterCodeValue).in(loincCodes),builder.equal(join.get(LabParameterCode_.labParameterCodeSystem),"LOINC"));
				cq.select(rootLabParameters.get(LabParameters_.labParametersId)).where(loincCode);
				
				List<Object> obj=em.createQuery(cq).getResultList();
				for(int i=0;i<obj.size();i++){
					int paramid=(int) obj.get(i);
					tempParams.add(paramid);
				}
				
//				tempParams=labParametersRepository.findAll(Specifications.where(InvestigationSpecification.loincCodeParams(tempLoinc)));
				params.addAll(tempParams);
				for(int y=0;y<loincCodes.size();y++){
					String tempValue=loincCodes.get(y)+"###"+FlowsheetServiceImpl.LoincOID;
					for(int k=0;k<groupIdParamIdMap.length();k++){
						Iterator itr = groupIdParamIdMap.keys();
						while( itr.hasNext() ) {
							String key = (String)itr.next();
							if (groupIdParamIdMap.get(key).toString().contains("@@@@"+tempValue+"@@@@")){
								if(tempParams.size()>0)
									groupIdParamIdMap.put(key, groupIdParamIdMap.get(key).toString().replace("@@@@"+tempValue+"@@@@","@@@@"+tempParams.get(0)+"###"+FlowsheetServiceImpl.GlenwoodSystemsOID+"@@@@"));
								else
									groupIdParamIdMap.put(key, groupIdParamIdMap.get(key).toString().replace("@@@@"+tempValue+"@@@@","@@@@"));
								for(int p=1;p<tempParams.size();p++){
									groupIdParamIdMap.put(key, groupIdParamIdMap.get(key).toString()+(tempParams.get(p)+"###"+FlowsheetServiceImpl.GlenwoodSystemsOID+"@@@@"));
								}
							}
						}
					}
				}
		}
		return params;
	}

	public ArrayList<FS_ClinicalElementBean> formClinicalElementsData(Integer flowsheetId,String startDate,Integer patientId,Integer encounterId,Integer patgender) throws Exception{
		clinicalVitalParamDate = new ArrayList<String> ();
		List<FlowsheetClinicalParam> flowsheetClinicalParam= new ArrayList<FlowsheetClinicalParam>();
		flowsheetClinicalParam=getDetails(FlowsheetServiceImpl.LOAD_VITALS_DATA, flowsheetId);
		ArrayList<FS_ClinicalElementBean> arr_clinical = new ArrayList<FS_ClinicalElementBean> ();
		Iterator<FlowsheetClinicalParam> itr_clinical_elements = flowsheetClinicalParam.iterator();
		FlowsheetClinicalParam hsh_notes = new FlowsheetClinicalParam();
		FS_ClinicalElementBean clinicalElementBean = null;
		String performedDate = "";
		String prevGwId = "";
		Encounter encounterEntity=null;
		//getting the encounter details
		Date encounterDate=null;
		if(encounterId!=-1){
			encounterEntity=encounterEntityRepository.findOne(EncounterEntitySpecification.EncounterById(encounterId));
			//encounterDate=getencounterDateforLab(encounterId);
		}
		List<String> gwidsComplete=new ArrayList<String>();
		while(itr_clinical_elements.hasNext()){
			hsh_notes = itr_clinical_elements.next();
			if(!prevGwId.equals(hsh_notes.getFlowsheetClinicalParamMapElementgwid())) {
				List<String> gwids=new ArrayList<String>();
				gwids.add(hsh_notes.getFlowsheetClinicalParamMapElementgwid());
				gwidsComplete.add(hsh_notes.getFlowsheetClinicalParamMapElementgwid());
				//getting list of vital parameters having glenwood id and patient id
				List<PatientClinicalElements> patientClinicalElements=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getElemPatientDataLessThanEncounter(patientId,encounterId, gwids));
				for(int g=0;g<patientClinicalElements.size();g++){
					ClinicalElements clinicalElementsInner=Optional.fromNullable(clinicalElementsRepository.findOne(PatientClinicalElementsSpecification.getClinicalElement(patientClinicalElements.get(g).getPatientClinicalElementsGwid()))).or(new ClinicalElements());
					patientClinicalElements.get(g).setClinicalElement(clinicalElementsInner);
					Encounter encounterEntityInner=Optional.fromNullable(encounterEntityRepository.findOne(EncounterEntitySpecification.EncounterById(Optional.fromNullable(patientClinicalElements.get(g).getPatientClinicalElementsEncounterid()).or(-1)))).or(new Encounter());
					patientClinicalElements.get(g).setEncounter(encounterEntityInner);
				}
				List<VitalsParameter> vitalsParameter=vitalsParameterRepository.findAll(VitalsSpecification.getUnitsForVitals(gwids));

				ClinicalElements clinicalElements=clinicalElementsRepository.findOne(Specifications.where(ClinicalElementsSpecification.getActiveClinicalElement(hsh_notes.getFlowsheetClinicalParamMapElementgwid())));
				String[] gwIdOverride=new String[1];
				if(clinicalElements==null)
					continue;
				String gwId = clinicalElements.getClinicalElementsGwid()+"";
				gwIdOverride[0]=gwId;

				//getting list of override alerts having gw id
				List<Overridealerts> overridealertsInt=overridealertsRepository.findAll(OverridealertsSpecification.overrideAlerts(gwIdOverride, 1, patientId, flowsheetId,encounterId,encounterEntity));
				for(int k=0;k<overridealertsInt.size();k++){
					PatientClinicalElements tempPatientClinicalElements=new PatientClinicalElements();
					tempPatientClinicalElements.setClinicalElement(clinicalElements);
					Encounter encounter=new Encounter();
					encounter.setEncounterDate((Timestamp)overridealertsInt.get(k).getOverriddenOn());
					tempPatientClinicalElements.setEncounter(encounter);
					String notes="Reason to Override: "+Optional.fromNullable(overridealertsInt.get(k).getReason()).or("-");
					tempPatientClinicalElements.setPatientClinicalElementsValue(notes);
					patientClinicalElements.add(tempPatientClinicalElements);
				}
				//Order Labs by Date
				patientClinicalElements=orderByDateClinical(patientClinicalElements, true);
				if(patientClinicalElements.size()>0){
					for(int k=0;k<patientClinicalElements.size();k++){
						clinicalElementBean = new FS_ClinicalElementBean();
						Integer dataType= Optional.fromNullable(patientClinicalElements.get(k).getClinicalElement().getClinicalElementsDatatype()).or(-1);
						String patValue= Optional.fromNullable(patientClinicalElements.get(k).getPatientClinicalElementsValue()).or("");
						String value="";
						if(dataType==5){
							List<ClinicalElementsOptions> options=clinicalElementsOptionsRepository.findAll(ClinicalElementsSpecification.getclinicalElementOptions(hsh_notes.getFlowsheetClinicalParamMapElementgwid()));
							for(int l=0;l<options.size();l++){
								performedDate=MoreObjects.firstNonNull(patientClinicalElements.get(k).getEncounter().getEncounterDate(),"Never Documented").toString();
								if(!performedDate.trim().contentEquals("Never Documented")){
									DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
									Date date = (Date) parser.parse(performedDate);
									DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
									performedDate=formatter.format(date);
								}
								if(!clinicalVitalParamDate.contains(performedDate))
									clinicalVitalParamDate.add(performedDate);
								String vitalName = Optional.fromNullable(patientClinicalElements.get(k).getClinicalElement().getClinicalElementsName()).or("");
								clinicalElementBean.setClinicalElementId(patientClinicalElements.get(k).getClinicalElement().getClinicalElementsGwid());
								clinicalElementBean.setClinicalElementName(vitalName);
								String optionName=Optional.fromNullable(options.get(l).getClinicalElementsOptionsName()).or("");
								if(patValue.contentEquals(options.get(l).getClinicalElementsOptionsValue())){
									value=optionName;
								}
								if(patientClinicalElements.get(k).getPatientClinicalElementsValue().startsWith("Reason to Override: ")){
									clinicalElementBean.setClinicalElementValue(patientClinicalElements.get(k).getPatientClinicalElementsValue());
									clinicalElementBean.setClinicalElementUnits("");
								}else{
									clinicalElementBean.setClinicalElementValue(value);
									String units="";
									if(vitalsParameter.size()>0){
										String unitsOfMeasure=Optional.fromNullable(vitalsParameter.get(0).getUnitsOfMeasureTable().getUnitsOfMeasureCode()).or("").toString();
										if(!unitsOfMeasure.contentEquals("N/A"))
											units=unitsOfMeasure;
										else
											units="";

									}
									clinicalElementBean.setClinicalElementUnits(units);
								}
								clinicalElementBean.setClinicalElementOn(performedDate);
								arr_clinical.add(clinicalElementBean);
								if(patValue.contentEquals(options.get(l).getClinicalElementsOptionsValue())){
									break;
								}
							}
						}else if(dataType==4){
							List<ClinicalElementsOptions> options=clinicalElementsOptionsRepository.findAll(ClinicalElementsSpecification.getclinicalElementOptions(hsh_notes.getFlowsheetClinicalParamMapElementgwid()));
							for(int l=0;l<options.size();l++){
								performedDate=MoreObjects.firstNonNull(patientClinicalElements.get(k).getEncounter().getEncounterDate(),"Never Documented").toString();
								if(!performedDate.trim().contentEquals("Never Documented")){
									DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
									Date date = (Date) parser.parse(performedDate);
									DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
									performedDate=formatter.format(date);
								}
								if(!clinicalVitalParamDate.contains(performedDate))
									clinicalVitalParamDate.add(performedDate);
								String vitalName = Optional.fromNullable(patientClinicalElements.get(k).getClinicalElement().getClinicalElementsName()).or("");
								clinicalElementBean.setClinicalElementId(patientClinicalElements.get(k).getClinicalElement().getClinicalElementsGwid());
								clinicalElementBean.setClinicalElementName(vitalName);
								String optionName=Optional.fromNullable(options.get(l).getClinicalElementsOptionsName()).or("");
								if(patValue.contentEquals(options.get(l).getClinicalElementsOptionsValue())){
									value=optionName;
								}
								if(patientClinicalElements.get(k).getPatientClinicalElementsValue().startsWith("Reason to Override: ")){
									clinicalElementBean.setClinicalElementValue(patientClinicalElements.get(k).getPatientClinicalElementsValue());
									clinicalElementBean.setClinicalElementUnits("");
								}else{
									clinicalElementBean.setClinicalElementValue(value);
									String units="";
									if(vitalsParameter.size()>0){
										String unitsOfMeasure=Optional.fromNullable(vitalsParameter.get(0).getUnitsOfMeasureTable().getUnitsOfMeasureCode()).or("").toString();
										if(!unitsOfMeasure.contentEquals("N/A"))
											units=unitsOfMeasure;
										else
											units="";

									}
									clinicalElementBean.setClinicalElementUnits(units);
								}
								clinicalElementBean.setClinicalElementOn(performedDate);
								arr_clinical.add(clinicalElementBean);
								if(patValue.contentEquals(options.get(l).getClinicalElementsOptionsValue())){
									break;
								}
							}
						}else{
							if(dataType==3 &&patValue.contentEquals("true")){
								value="Yes";
							}else if(dataType==3 &&patValue.contentEquals("false")){
								value="No";
							}else{
								value=patValue;
							}
							performedDate=MoreObjects.firstNonNull(patientClinicalElements.get(k).getEncounter().getEncounterDate(),"Never Documented").toString();
							if(!performedDate.trim().contentEquals("Never Documented")){
								DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
								Date date = (Date) parser.parse(performedDate);
								DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
								performedDate=formatter.format(date);
							}
							if(!clinicalVitalParamDate.contains(performedDate))
								clinicalVitalParamDate.add(performedDate);
							String vitalName = Optional.fromNullable(patientClinicalElements.get(k).getClinicalElement().getClinicalElementsName()).or("");
							clinicalElementBean.setClinicalElementId(patientClinicalElements.get(k).getClinicalElement().getClinicalElementsGwid());
							clinicalElementBean.setClinicalElementName(vitalName);
							if(patientClinicalElements.get(k).getPatientClinicalElementsValue().startsWith("Reason to Override: ")){
								clinicalElementBean.setClinicalElementValue(patientClinicalElements.get(k).getPatientClinicalElementsValue());
								clinicalElementBean.setClinicalElementUnits("");
							}else{
								clinicalElementBean.setClinicalElementValue(value);
								String units="";
								if(vitalsParameter.size()>0){
									String unitsOfMeasure=Optional.fromNullable(vitalsParameter.get(0).getUnitsOfMeasureTable().getUnitsOfMeasureCode()).or("").toString();
									if(!unitsOfMeasure.contentEquals("N/A"))
										units=unitsOfMeasure;
									else
										units="";

								}
								clinicalElementBean.setClinicalElementUnits(units);
							}
							clinicalElementBean.setClinicalElementOn(performedDate);
							arr_clinical.add(clinicalElementBean);
						}
					} 
				}else{
					List<ClinicalElements> clinicalElements1=clinicalElementsRepository.findAll(Specifications.where(ClinicalElementsSpecification.getActiveClinicalElement(hsh_notes.getFlowsheetClinicalParamMapElementgwid())));
					if(clinicalElements1!=null){
						if(clinicalElements1.size()>0){
							clinicalElementBean = new FS_ClinicalElementBean();
							performedDate="Never Documented";
							if(!clinicalVitalParamDate.contains(performedDate))
								clinicalVitalParamDate.add(performedDate);
							String vitalName = Optional.fromNullable(clinicalElements1.get(0).getClinicalElementsName()).or("");
							clinicalElementBean.setClinicalElementId(clinicalElements1.get(0).getClinicalElementsGwid());
							clinicalElementBean.setClinicalElementName(vitalName);
							clinicalElementBean.setClinicalElementValue("");
							clinicalElementBean.setClinicalElementUnits("");
							clinicalElementBean.setClinicalElementOn(performedDate);
							arr_clinical.add(clinicalElementBean);
						}
					}
				}
			} 
			prevGwId = hsh_notes.getFlowsheetClinicalParamMapElementgwid();
		}
		arr_clinical=orderByNameClinicalElements(arr_clinical);
		arr_clinical=conversion(arr_clinical);
		clinicalVitalParamDate= sorttoascending(clinicalVitalParamDate,"Never Documented");
		Collections.reverse(clinicalVitalParamDate);
		clinicalVitalParamDate=limitthecount(clinicalVitalParamDate,11);
		if(gwidsComplete.size()>0)
			arr_clinical = checkFlowSheetRulesClinicalElementsData(patientId,flowsheetId,gwidsComplete, arr_clinical, startDate,patgender);
		

		return arr_clinical;
	}
	private ArrayList<FS_ClinicalElementBean> conversion(ArrayList<FS_ClinicalElementBean> arr_clinical){
		String weightInGram,heightInCm,feetValue,lbs,degreeValue;
		for(int i=0;i<arr_clinical.size();i++){
			if(arr_clinical.get(i).getClinicalElementName().equalsIgnoreCase("Height")){
				heightInCm= Optional.fromNullable(arr_clinical.get(i).getClinicalElementValue()).or("");

				feetValue =HUtil.heightConversion(heightInCm,"flowsheet");
				arr_clinical.get(i).setClinicalElementValue(feetValue);
			}
			else if(arr_clinical.get(i).getClinicalElementName().equalsIgnoreCase("Weight")){
				weightInGram= arr_clinical.get(i).getClinicalElementValue();

				lbs =HUtil.weightConversion(weightInGram,"flowsheet");
				arr_clinical.get(i).setClinicalElementValue(lbs);
			}
			degreeValue = arr_clinical.get(i).getClinicalElementUnits();
			degreeValue=degreeValue.replace("&#176;F","\u00b0F");
			arr_clinical.get(i).setClinicalElementUnits(degreeValue);
		}
		return arr_clinical;
	}

	public ArrayList<FS_ClinicalElementBean> orderByNameClinicalElements(ArrayList<FS_ClinicalElementBean> confData){
		if (confData.size() > 0) {
			Collections.sort(confData, new Comparator<FS_ClinicalElementBean>() {
				@Override
				public int compare(final FS_ClinicalElementBean object1, final FS_ClinicalElementBean object2) {
					return object1.getClinicalElementName().toString().compareToIgnoreCase(object2.getClinicalElementName().toString());
				}
			} );
		}
		return confData;
	}

	public ArrayList<FS_ClinicalElementBean> checkFlowSheetRulesClinicalElementsData(Integer patientId, Integer flowSheetId,List<String> gwidsComplete,ArrayList<FS_ClinicalElementBean> flowSheetData, String startDate,Integer patgender) throws Exception{
		String currentDate = getCurrentDate();
		ArrayList<FS_ClinicalElementBean> tempFlowSheetData = new ArrayList<FS_ClinicalElementBean>();
		if(startDate!=null && !startDate.equalsIgnoreCase("")){
		List<HmrBean> flowSheetRule = getFlowSheetRuleQueryPatientBased(patientId,gwidsComplete, startDate,1);
		String today = getTodayDate();
		//PatientRegistration genderPat =  patientRegistrationRepository.findOne(Specifications.where(PatientRegistrationSpecification.PatientId(patientId+"")));
		String gender = patgender.toString();
		//ArrayList<FS_ClinicalElementBean> tempFlowSheetData = new ArrayList<FS_ClinicalElementBean>();
		for(int flowSheetDataCounter = 0 ; flowSheetDataCounter < flowSheetData.size() ; flowSheetDataCounter++){
			FS_ClinicalElementBean flowSheetDataObj = flowSheetData.get(flowSheetDataCounter);
			boolean isElementRuleExists = false;
			boolean isElementGenderRuleSatisfied = false;
			flowSheetDataObj.setDueCriteria("");
			for(int dueRulesCounter = 0 ; dueRulesCounter < flowSheetRule.size() ; dueRulesCounter++ ){
				HmrBean flowSheetRuleObj = flowSheetRule.get(dueRulesCounter);
				if(Optional.fromNullable(flowSheetRuleObj.getElement_type()).or(-1)==1 && Optional.fromNullable(flowSheetRuleObj.getElement_type()).or(-1).toString().equals(flowSheetDataObj.getClinicalElementId())){
					isElementRuleExists = true;
					if(Optional.fromNullable(flowSheetRuleObj.getGender()).or(-1)==0 || gender.equals(Optional.fromNullable(flowSheetRuleObj.getGender()).or(-1).toString()))
					{
						isElementGenderRuleSatisfied = true;
						if(( DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(today, flowSheetRuleObj.getEnddate().toString()) == -1 ) || DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 0){
							if((MoreObjects.firstNonNull(flowSheetRuleObj.getSchType(),"").toString().equals("") || MoreObjects.firstNonNull(flowSheetRuleObj.getSchedule(),"").toString().equals(""))){
								if(!flowSheetDataObj.getClinicalElementOn().equals("Never Documented")){
									if(DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString().replace("-", "/"), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString().replace("-", "/"), flowSheetRuleObj.getEnddate().toString()) == -1){
										flowSheetDataObj.setDue("Completed");
										flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or("").toString());
										break;
									}
								}									
								flowSheetDataObj.setDue("Due Now");
								flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or("").toString());
								break;
							}
							if(!flowSheetRuleObj.getSchType().toString().equals("") && !flowSheetRuleObj.getSchedule().toString().equals("")){
								String freqStartDate = flowSheetRuleObj.getStartdate().toString();
								String freqEndDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(freqStartDate)), 3);
								while(DateUtil.dateDiffVal( flowSheetRuleObj.getEnddate().toString() , freqEndDate) == 1){
									freqEndDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(freqStartDate)), 3);
									if(DateUtil.dateDiffVal(freqEndDate, flowSheetRuleObj.getEnddate().toString()) == 1){
									} else {
										//Due Checking for schedule Range
										if(( DateUtil.dateDiffVal(today, freqStartDate ) == 1 && DateUtil.dateDiffVal(today, freqEndDate) == -1 ) || DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 0){
											if(!flowSheetDataObj.getClinicalElementOn().equals("Never Documented")){
												//here change the code for last performed append last performed + due period 

												if(flowSheetRuleObj.getRuledate().equals("3")){
													String rulePerformedDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(flowSheetDataObj.getClinicalElementOn().toString().replace("-", "/"))), 3);
													if(DateUtil.dateDiffVal(currentDate,rulePerformedDate) == -1){
														flowSheetDataObj.setDue("Completed");
														flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or("").toString());
														break;
													}
												}else{
													if(DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString().replace("-", "/"), freqStartDate) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString().replace("-", "/"), freqEndDate) == -1){
														flowSheetDataObj.setDue("Completed");
														flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or("").toString());
														break;
													}
												}
											}					
											flowSheetDataObj.setDue("Due Now");
											flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or("").toString());
											break;
										} else if(DateUtil.dateDiffVal(today, freqStartDate ) == 1 && DateUtil.dateDiffVal(today, freqEndDate ) == 1){
											flowSheetDataObj.setDue("Over Due");
											if(!flowSheetDataObj.getClinicalElementOn().equals("Never Documented")){
												if(DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString().replace("-", "/"), freqStartDate ) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString().replace("-", "/"), freqEndDate) == -1){
													flowSheetDataObj.setDue("Completed");
													flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or("").toString());
												}
											}		

											if(!flowSheetDataObj.getClinicalElementOn().equals("Never Documented")){
												if(DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString().replace("-", "/"), freqStartDate) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString().replace("-", "/"), freqEndDate ) == 1){
													flowSheetDataObj.setDue("");
													flowSheetDataObj.setDueCriteria("");
												}
											}	
										} 
										freqStartDate = freqEndDate;
									}
								}
							}
						} else if(DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(today, flowSheetRuleObj.getEnddate().toString()) == 1){
							flowSheetDataObj.setDue("Over Due");
							flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or("").toString());
							if(!flowSheetDataObj.getClinicalElementOn().equals("Never Documented")){
								if(DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString().replace("-", "/"), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString().replace("-", "/"), flowSheetRuleObj.getEnddate().toString()) == -1){
									flowSheetDataObj.setDue("Completed");
									flowSheetDataObj.setDueCriteria(Optional.fromNullable(flowSheetRuleObj.getDesc()).or("").toString());
								}
							}		
							if(!flowSheetDataObj.getClinicalElementOn().equals("Never Documented")){
								if(  ( DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString().replace("-", "/"), flowSheetRuleObj.getStartdate().toString()) == -1 && DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString().replace("-", "/"), flowSheetRuleObj.getEnddate().toString()) == -1) ||  ( DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString().replace("-", "/"), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString().replace("-", "/"), flowSheetRuleObj.getEnddate().toString()) == 1)){
									flowSheetDataObj.setDue("");
									flowSheetDataObj.setDueCriteria("");
								}
							}	
						} 
					} 
				}
			}
			if(isElementRuleExists == false){
				tempFlowSheetData.add(flowSheetDataObj);
			} else if(isElementRuleExists == true && isElementGenderRuleSatisfied == true){
				tempFlowSheetData.add(flowSheetDataObj);
			}
		}
		}
		return tempFlowSheetData;
	}

	/**
	 * used for getting clinicalelements data 
	 * @param flowsheetId
	 * @param startDate
	 * @param patientId
	 * @param encounterId
	 * @return
	 * @throws Exception
	 */


	public ArrayList<FS_ClinicalElementBean> formClinicalData (Integer flowsheetId,String startDate,Integer patientId,Integer encounterId,Integer patgender) throws Exception{
		
		List<FlowsheetClinicalParam>flowsheetClinicalParam= new ArrayList<FlowsheetClinicalParam>();
		flowsheetClinicalParam=getDetails(FlowsheetServiceImpl.LOAD_CLINICAL_ELEMENTS_DATA, flowsheetId);
		//List<FlowsheetClinicalParam> flowsheetClinicalParam=flowsheetClinicalParamRepository.findAll(FlowsheetSpecification.flowsheetClinicalParam(FlowsheetServiceImpl.LOAD_CLINICAL_ELEMENTS_DATA, flowsheetId));
		ArrayList<FS_ClinicalElementBean> arr_clinical = new ArrayList<FS_ClinicalElementBean> ();
		Iterator<FlowsheetClinicalParam> itr_clinical = flowsheetClinicalParam.iterator();
		FlowsheetClinicalParam hsh_notes = new FlowsheetClinicalParam();
		FS_ClinicalElementBean clinicalElementBean = null;
		String performedDate = "";
		String prevGwId = "";
		Encounter encounterEntity=null;
		//getting the encounter details
		if(encounterId!=-1){
			encounterEntity=encounterEntityRepository.findOne(EncounterEntitySpecification.EncounterById(encounterId));
			//encounterEntity= getRequiredEncounterDetails(encounterId);
		}
		List<String> gwidsComplete=new ArrayList<String>();
		while(itr_clinical.hasNext()){
			hsh_notes = itr_clinical.next();
			if(!prevGwId.equalsIgnoreCase(hsh_notes.getFlowsheetClinicalParamMapElementgwid())){
				List<String> gwids=new ArrayList<String>();
				gwids.add(hsh_notes.getFlowsheetClinicalParamMapElementgwid());
				gwidsComplete.add(hsh_notes.getFlowsheetClinicalParamMapElementgwid());
				List<PatientClinicalElements> patientClinicalElements=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getElemPatientDataLessThanEncounter(patientId, encounterId, gwids));
				//List<PatientClinicalElements> patientClinicalElements=getElemPatientDataLessThanEncounter(patientId,encounterId,gwids);
				for(int g=0;g<patientClinicalElements.size();g++){
					ClinicalElements clinicalElementsInner=Optional.fromNullable(clinicalElementsRepository.findOne(PatientClinicalElementsSpecification.getClinicalElement(patientClinicalElements.get(g).getPatientClinicalElementsGwid()))).or(new ClinicalElements());
					patientClinicalElements.get(g).setClinicalElement(clinicalElementsInner);
					Encounter encounterEntityInner=Optional.fromNullable(encounterEntityRepository.findOne(EncounterEntitySpecification.EncounterById(Optional.fromNullable(patientClinicalElements.get(g).getPatientClinicalElementsEncounterid()).or(-1)))).or(new Encounter());
					patientClinicalElements.get(g).setEncounter(encounterEntityInner);
				}
				ClinicalElements clinicalElements=clinicalElementsRepository.findOne(Specifications.where(ClinicalElementsSpecification.getActiveClinicalElement(hsh_notes.getFlowsheetClinicalParamMapElementgwid())));
				String[] gwIdOverride=new String[1];
				if(clinicalElements==null)
					continue;
				String gwId = clinicalElements.getClinicalElementsGwid()+"";
				gwIdOverride[0]=gwId;

				//getting list of override alerts having gw id
				List<Overridealerts> overridealertsInt=overridealertsRepository.findAll(OverridealertsSpecification.overrideAlerts(gwIdOverride, 4, patientId, flowsheetId,encounterId,encounterEntity));
				for(int k=0;k<overridealertsInt.size();k++){
					PatientClinicalElements tempPatientClinicalElements=new PatientClinicalElements();
					tempPatientClinicalElements.setClinicalElement(clinicalElements);
					Encounter encounter=new Encounter();
					encounter.setEncounterDate((Timestamp)overridealertsInt.get(k).getOverriddenOn());
					tempPatientClinicalElements.setEncounter(encounter);
					String notes="Reason to Override: "+Optional.fromNullable(overridealertsInt.get(k).getReason()).or("-").toString();
					tempPatientClinicalElements.setPatientClinicalElementsValue(notes);
					patientClinicalElements.add(tempPatientClinicalElements);
				}
				//Order Labs by Date
				patientClinicalElements=orderByDateClinical(patientClinicalElements, true);
				if(patientClinicalElements.size()>0){
					for(int k=0;k<patientClinicalElements.size();k++){
						clinicalElementBean = new FS_ClinicalElementBean();
						Integer dataType= Optional.fromNullable(patientClinicalElements.get(k).getClinicalElement().getClinicalElementsDatatype()).or(-1);
						String patValue= Optional.fromNullable(patientClinicalElements.get(k).getPatientClinicalElementsValue()).or("").toString();
						String value="";
						if(dataType==5){
							List<ClinicalElementsOptions> options=clinicalElementsOptionsRepository.findAll(ClinicalElementsSpecification.getclinicalElementOptions(hsh_notes.getFlowsheetClinicalParamMapElementgwid()));
							performedDate=MoreObjects.firstNonNull(patientClinicalElements.get(k).getEncounter().getEncounterDate(),"Never Documented").toString();
							if(!performedDate.trim().contentEquals("Never Documented")){
								DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
								Date date = (Date) parser.parse(performedDate);
								DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
								performedDate=formatter.format(date);
							}
							String vitalName = Optional.fromNullable(patientClinicalElements.get(k).getClinicalElement().getClinicalElementsName()).or("");
							clinicalElementBean.setClinicalElementId(patientClinicalElements.get(k).getClinicalElement().getClinicalElementsGwid());
							clinicalElementBean.setClinicalElementName(vitalName);
							for(int l=0;l<options.size();l++){
								if(patientClinicalElements.get(k).getPatientClinicalElementsValue().startsWith("Reason to Override: ")){
									value=patientClinicalElements.get(k).getPatientClinicalElementsValue();
									break;
								}else{
									String optionName=Optional.fromNullable(options.get(l).getClinicalElementsOptionsName()).or("");
									if(patValue.contentEquals(options.get(l).getClinicalElementsOptionsValue())){
										value=optionName;
										break;
									}
								}
							}
							clinicalElementBean.setClinicalElementValue(value);
							clinicalElementBean.setClinicalElementUnits("");
							clinicalElementBean.setClinicalElementOn(performedDate);
							arr_clinical.add(clinicalElementBean);
						}else if(dataType==4){
							List<ClinicalElementsOptions> options=clinicalElementsOptionsRepository.findAll(ClinicalElementsSpecification.getclinicalElementOptions(hsh_notes.getFlowsheetClinicalParamMapElementgwid()));
							for(int l=0;l<options.size();l++){
								performedDate=MoreObjects.firstNonNull(patientClinicalElements.get(k).getEncounter().getEncounterDate(),"Never Documented").toString();
								if(!performedDate.trim().contentEquals("Never Documented")){
									DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
									Date date = (Date) parser.parse(performedDate);
									DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
									performedDate=formatter.format(date);
								}
								String vitalName = Optional.fromNullable(patientClinicalElements.get(k).getClinicalElement().getClinicalElementsName()).or("");
								clinicalElementBean.setClinicalElementId(patientClinicalElements.get(k).getClinicalElement().getClinicalElementsGwid());
								clinicalElementBean.setClinicalElementName(vitalName);
								String optionName=Optional.fromNullable(options.get(l).getClinicalElementsOptionsName()).or("");
								if(patValue.contentEquals(options.get(l).getClinicalElementsOptionsValue())){
									value=optionName;
								}
								if(patientClinicalElements.get(k).getPatientClinicalElementsValue().startsWith("Reason to Override: "))
									clinicalElementBean.setClinicalElementValue(patientClinicalElements.get(k).getPatientClinicalElementsValue());
								else
									clinicalElementBean.setClinicalElementValue(value);
								clinicalElementBean.setClinicalElementUnits("");
								clinicalElementBean.setClinicalElementOn(performedDate);
								arr_clinical.add(clinicalElementBean);
								if(patValue.contentEquals(options.get(l).getClinicalElementsOptionsValue())){
									break;
								}
							}
						}else{
							if(dataType==3 &&patValue.contentEquals("true")){
								value="Yes";
							}else if(dataType==3 &&patValue.contentEquals("false")){
								value="No";
							}else{
								value=patValue;
							}
							performedDate=MoreObjects.firstNonNull(patientClinicalElements.get(k).getEncounter().getEncounterDate(),"Never Documented").toString();
							if(!performedDate.trim().contentEquals("Never Documented")){
								DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
								Date date = (Date) parser.parse(performedDate);
								DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
								performedDate=formatter.format(date);
							}
							String vitalName = Optional.fromNullable(patientClinicalElements.get(k).getClinicalElement().getClinicalElementsName()).or("");
							clinicalElementBean.setClinicalElementId(patientClinicalElements.get(k).getClinicalElement().getClinicalElementsGwid());
							clinicalElementBean.setClinicalElementName(vitalName);
							if(patientClinicalElements.get(k).getPatientClinicalElementsValue().startsWith("Reason to Override: "))
								clinicalElementBean.setClinicalElementValue(patientClinicalElements.get(k).getPatientClinicalElementsValue());
							else
								clinicalElementBean.setClinicalElementValue(value);
							clinicalElementBean.setClinicalElementUnits("");
							clinicalElementBean.setClinicalElementOn(performedDate);
							arr_clinical.add(clinicalElementBean);
						}
					} 
				}else{
					List<ClinicalElements> clinicalElements1=clinicalElementsRepository.findAll(Specifications.where(ClinicalElementsSpecification.getActiveClinicalElement(hsh_notes.getFlowsheetClinicalParamMapElementgwid())));
					if(clinicalElements1!=null){
						if(clinicalElements1.size()>0){
							clinicalElementBean = new FS_ClinicalElementBean();
							performedDate="Never Documented";
							String vitalName = Optional.fromNullable(clinicalElements1.get(0).getClinicalElementsName()).or("");
							clinicalElementBean.setClinicalElementId(clinicalElements1.get(0).getClinicalElementsGwid());
							clinicalElementBean.setClinicalElementName(vitalName);
							clinicalElementBean.setClinicalElementValue("");
							clinicalElementBean.setClinicalElementUnits("");
							clinicalElementBean.setClinicalElementOn(performedDate);
							arr_clinical.add(clinicalElementBean);
						}
					}
				}
			}
		}
		arr_clinical=conversion(arr_clinical);
		if(gwidsComplete.size()>0)
			arr_clinical = checkFlowSheetRulesForClinicalData(patientId, flowsheetId,gwidsComplete, arr_clinical, startDate,patgender);

		
		return arr_clinical;
	}
	/*private List<Encounter> getRequiredEncounterDetails(Integer encounterId) {
		List<Encounter> stndcode= new ArrayList<Encounter>();
		CriteriaBuilder cb= em.getCriteriaBuilder();
		CriteriaQuery<Encounter> cq = cb.createQuery(Encounter.class);
		Root<FlowsheetClinicalParam> root = cq.from(FlowsheetClinicalParam.class);
		Selection[] selectionsforVR=new Selection[]{
				root.get(Encounter_.encounterId),
				root.get(Encounter_.encounterChartid),
				root.get(Encounter_.encounterStatus),
				root.get(Encounter_.encounterDate),
		};
		cq1.select(cb1.construct(FlowsheetClinicalParam.class,selectionsforVR));



		// TODO Auto-generated method stub
		return null;
	}*/

	private List<PatientClinicalElements> getElemPatientDataLessThanEncounter(
			Integer patientId, Integer encounterId, List<String> gwids) {
		CriteriaBuilder cb1=em.getCriteriaBuilder();
		CriteriaQuery<PatientClinicalElements> cq1 = cb1.createQuery(PatientClinicalElements.class);
		List<PatientClinicalElements> stndcode= new ArrayList<PatientClinicalElements>();
		Root<PatientClinicalElements> root1 = cq1.from(PatientClinicalElements.class);
		Join<PatientClinicalElements,ClinicalElements> paramJoin=root1.join(PatientClinicalElements_.clinicalElement,JoinType.INNER);
		
		Selection[] selectionsforVR=new Selection[]{
				root1.get(PatientClinicalElements_.patientClinicalElementsId),
				root1.get(PatientClinicalElements_.patientClinicalElementsPatientid),
				root1.get(PatientClinicalElements_.patientClinicalElementsChartid),
				root1.get(PatientClinicalElements_.patientClinicalElementsEncounterid),
				root1.get(PatientClinicalElements_.patientClinicalElementsGwid),
				root1.get(PatientClinicalElements_.patientClinicalElementsValue),
		};
		cq1.select(cb1.construct(PatientClinicalElements.class,selectionsforVR));
		if(encounterId!=-1){
		Predicate[] restrictionsforVR=new Predicate[]{
				cb1.lessThanOrEqualTo(root1.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId),
	        cb1.equal(root1.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId),
		root1.get(PatientClinicalElements_.patientClinicalElementsGwid).in(gwids),
		};
		cq1.where(restrictionsforVR);
		}else{
			Predicate[] restrictionsforVR=new Predicate[]{
		    cb1.equal(root1.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId),
			root1.get(PatientClinicalElements_.patientClinicalElementsGwid).in(gwids),
			};
			cq1.where(restrictionsforVR);
		}
		try{
			stndcode= em.createQuery(cq1).getResultList();	
		}catch(Exception e){
			e.printStackTrace();
		}
		return stndcode;
	}


	private List<PatientClinicalElements> getListOfPatientClinicalElements(
			Integer patientId, Integer encounterId, List<String> gwids) {
		CriteriaBuilder cb1=em.getCriteriaBuilder();
		CriteriaQuery<PatientClinicalElements> cq1 = cb1.createQuery(PatientClinicalElements.class);
		List<PatientClinicalElements> stndcode= new ArrayList<PatientClinicalElements>();
		Root<PatientClinicalElements> root1 = cq1.from(PatientClinicalElements.class);
		Join<PatientClinicalElements,ClinicalElements> paramJoin=root1.join(PatientClinicalElements_.clinicalElement,JoinType.INNER);

		@SuppressWarnings("rawtypes")
		Selection[] selectionsforVR=new Selection[]{
			root1.get(PatientClinicalElements_.patientClinicalElementsId),
			root1.get(PatientClinicalElements_.patientClinicalElementsPatientid),
			root1.get(PatientClinicalElements_.patientClinicalElementsChartid),
			root1.get(PatientClinicalElements_.patientClinicalElementsEncounterid),
			root1.get(PatientClinicalElements_.patientClinicalElementsGwid),
			root1.get(PatientClinicalElements_.patientClinicalElementsValue),
		};
		cq1.select(cb1.construct(PatientClinicalElements.class,selectionsforVR));
		Predicate encounterPred=null;
		if(encounterId!=-1)
			encounterPred=cb1.lessThanOrEqualTo(root1.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId);
		Predicate patientPred=cb1.equal(root1.get(PatientClinicalElements_.patientClinicalElementsPatientid),patientId);
		Predicate elementPred=root1.get(PatientClinicalElements_.patientClinicalElementsGwid).in(gwids);
		Predicate finalPred=null;
		if(encounterId!=-1)
			finalPred= cb1.and(encounterPred,patientPred,elementPred);
		else
			finalPred= cb1.and(patientPred,elementPred);
		cq1.where(finalPred);
		// TODO Auto-generated method stub
		try{
			stndcode= em.createQuery(cq1).getResultList();	

		}catch(Exception e){
			e.printStackTrace();
		}
		return stndcode;


	}

	/**
	 * Method to get the clinical flowsheetvalues
	 * @param lOAD_CLINICAL_ELEMENTS_DATA2
	 * @param flowsheetId
	 * @return
	 */
	private List<FlowsheetClinicalParam> getDetails(int lOAD_CLINICAL_ELEMENTS_DATA2, Integer flowsheetId) {
		CriteriaBuilder cb1=em.getCriteriaBuilder();
		CriteriaQuery<FlowsheetClinicalParam> cq1 = cb1.createQuery(FlowsheetClinicalParam.class);
		List<FlowsheetClinicalParam> stndcode= new ArrayList<FlowsheetClinicalParam>();
		Root<FlowsheetClinicalParam> root1 = cq1.from(FlowsheetClinicalParam.class);


		@SuppressWarnings("rawtypes")
		Selection[] selectionsforVR=new Selection[]{
			root1.get(FlowsheetClinicalParam_.flowsheetClinicalParamId),
			root1.get(FlowsheetClinicalParam_.flowsheetClinicalParamMapid),
			root1.get(FlowsheetClinicalParam_.flowsheetClinicalParamMapElementgwid),
			root1.get(FlowsheetClinicalParam_.flowsheetClinicalParamMapElementtype),
			root1.get(FlowsheetClinicalParam_.flowsheetClinicalParamSortorder),


		};
		cq1.select(cb1.construct(FlowsheetClinicalParam.class,selectionsforVR));
		Predicate[] restrictionsforVR=new Predicate[]{
				cb1.equal(root1.get(FlowsheetClinicalParam_.flowsheetClinicalParamMapElementtype) ,lOAD_CLINICAL_ELEMENTS_DATA2),
				cb1.equal(root1.get(FlowsheetClinicalParam_.flowsheetClinicalParamMapid),flowsheetId),
		};
		cq1.where (restrictionsforVR);
		try{
			stndcode= em.createQuery(cq1).getResultList();	

		}catch(Exception e){
			e.printStackTrace();
		}
		return stndcode;

	}


	public ArrayList<FS_ClinicalElementBean> checkFlowSheetRulesForClinicalData(Integer patientId, Integer flowSheetId,List<String> gwidsComplete,ArrayList<FS_ClinicalElementBean> flowSheetData, String startDate,Integer patgender) throws Exception{
		String currentDate = getCurrentDate();
		ArrayList<FS_ClinicalElementBean> tempFlowSheetData = new ArrayList<FS_ClinicalElementBean>();
		if(startDate!=null && !startDate.equalsIgnoreCase("")){
		List<HmrBean> flowSheetRule = getFlowSheetRuleQueryPatientBased(patientId,gwidsComplete, startDate,4);
		String today = getTodayDate();
		//ArrayList<FS_ClinicalElementBean> tempFlowSheetData = new ArrayList<FS_ClinicalElementBean>();
		for(int flowSheetDataCounter = 0 ; flowSheetDataCounter < flowSheetData.size() ; flowSheetDataCounter++){
			FS_ClinicalElementBean flowSheetDataObj = flowSheetData.get(flowSheetDataCounter);
			boolean isElementRuleExists = false;
			boolean isElementGenderRuleSatisfied = false;
			flowSheetDataObj.setDueCriteria("");
			for(int dueRulesCounter = 0 ; dueRulesCounter < flowSheetRule.size() ; dueRulesCounter++ ){
				HmrBean flowSheetRuleObj = flowSheetRule.get(dueRulesCounter);
				if( flowSheetRuleObj.getElement_type().equals("4") && flowSheetRuleObj.getElement_id().equals(flowSheetDataObj.getClinicalElementId() ) ){
					isElementRuleExists = true;
					if(flowSheetRuleObj.getGender().toString().equals("0"))
					{
						isElementGenderRuleSatisfied = true;
						if(( DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(today, flowSheetRuleObj.getEnddate().toString()) == -1 ) || DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 0){
							if(flowSheetRuleObj.getSchType()!=null && flowSheetRuleObj.getSchedule()!=null ){
								if((flowSheetRuleObj.getSchType().toString().equals("") || flowSheetRuleObj.getSchedule().toString().equals(""))){
									if(!flowSheetDataObj.getClinicalElementOn().equals("Never Documented")){

										if(DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString(), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString(), flowSheetRuleObj.getEnddate().toString()) == -1){
											flowSheetDataObj.setDue("Completed");
											flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
											break;
										}
									}									
									flowSheetDataObj.setDue("Due Now");
									flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
									break;
								}
								if(!flowSheetRuleObj.getSchType().toString().equals("") && !flowSheetRuleObj.getSchedule().toString().equals("")){
									String freqStartDate = flowSheetRuleObj.getStartdate().toString();
									String freqEndDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(freqStartDate)), 3);
									while(DateUtil.dateDiffVal( flowSheetRuleObj.getEnddate().toString() , freqEndDate) == 1){
										freqEndDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(freqStartDate)), 3);
										if(DateUtil.dateDiffVal(freqEndDate, flowSheetRuleObj.getEnddate().toString()) == 1){
										} else {
											//Due Checking for schedule Range
											if(( DateUtil.dateDiffVal(today, freqStartDate ) == 1 && DateUtil.dateDiffVal(today, freqEndDate) == -1 ) || DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 0){
												if(!flowSheetDataObj.getClinicalElementOn().equals("Never Documented")){
													if(flowSheetRuleObj.getRuledate().equals("3")){
														String rulePerformedDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(flowSheetDataObj.getClinicalElementOn().toString())), 3);
														if(DateUtil.dateDiffVal(currentDate,rulePerformedDate) == -1){
															flowSheetDataObj.setDue("Completed");
															flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
															break;
														}

													}else{
														if(DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString(), freqStartDate) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString(), freqEndDate) == -1){
															flowSheetDataObj.setDue("Completed");
															flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
															break;
														}
													}
												}					
												flowSheetDataObj.setDue("Due Now");
												flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
												break;
											} else if(DateUtil.dateDiffVal(today, freqStartDate ) == 1 && DateUtil.dateDiffVal(today, freqEndDate ) == 1){

												flowSheetDataObj.setDue("Over Due");
												if(!flowSheetDataObj.getClinicalElementOn().equals("Never Documented")){
													if(DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString(), freqStartDate ) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString(), freqEndDate) == -1){
														flowSheetDataObj.setDue("Completed");
														flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
													}
												}		

												if(!flowSheetDataObj.getClinicalElementOn().equals("Never Documented")){
													if(DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString(), freqStartDate) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString(), freqEndDate ) == 1){
														flowSheetDataObj.setDue("");
														flowSheetDataObj.setDueCriteria("");
													}
												}	
											} 
											freqStartDate = freqEndDate;
										}
									}
								}
							}
						} else if(DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(today, flowSheetRuleObj.getEnddate().toString()) == 1){
							flowSheetDataObj.setDue("Over Due");
							flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
							if(!flowSheetDataObj.getClinicalElementOn().equals("Never Documented")){
								if(DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString(), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString(), flowSheetRuleObj.getEnddate().toString()) == -1){
									flowSheetDataObj.setDue("Completed");
									flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
								}
							}		
							if(!flowSheetDataObj.getClinicalElementOn().equals("Never Documented")){
								if(  ( DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString(), flowSheetRuleObj.getStartdate().toString()) == -1 && DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString(), flowSheetRuleObj.getEnddate().toString()) == -1) ||  ( DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString(), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getClinicalElementOn().toString(), flowSheetRuleObj.getEnddate().toString()) == 1)){
									flowSheetDataObj.setDue("");
									flowSheetDataObj.setDueCriteria("");
								}
							}	
						} 
					} 
				}

			}
			if(isElementRuleExists == false){
				tempFlowSheetData.add(flowSheetDataObj);
			} else if(isElementRuleExists == true && isElementGenderRuleSatisfied == true){
				tempFlowSheetData.add(flowSheetDataObj);
			}

		}
		}
		return tempFlowSheetData;
	}

	public ArrayList<FS_DrugBean> formDrugData (Integer patientId,Integer flowsheetId, String startDate,Integer encounterId,Integer patgender) throws Exception{
		 List<FlowsheetDrug> flowsheetDrugList=flowsheetDrugRepository.findAll(FlowsheetSpecification.flowsheetDrugsMapId(flowsheetId));
	         ArrayList<FS_DrugBean> arr_drugs = new ArrayList<FS_DrugBean> ();
	         Iterator<FlowsheetDrug> itr_drugs = flowsheetDrugList.iterator();
	         FlowsheetDrug hsh_notes = new FlowsheetDrug();
	         FS_DrugBean drugBean = null;
	         String performedDate = "";
	         String classId = "";
	         Encounter encounterEntity=null;
	         //getting the encounter details
	         if(encounterId!=-1){
	             encounterEntity=encounterEntityRepository.findOne(EncounterEntitySpecification.EncounterById(encounterId));
	         }
	         List<String> classIdsComplete=new ArrayList<String>();
	         PrescriptionServiceImpl serviceimpl=new PrescriptionServiceImpl();
	         while(itr_drugs.hasNext()){
	             hsh_notes = itr_drugs.next();
	             if(!classId.equalsIgnoreCase(hsh_notes.getFlowsheetDrugClassId())){
	                 List<String> classIds=new ArrayList<String>();
	                 classIds.add(hsh_notes.getFlowsheetDrugClassId());
	                 classIdsComplete.add(hsh_notes.getFlowsheetDrugClassId());
	                 FlowsheetDrug flowsheetDrug=flowsheetDrugRepository.findOne(Specifications.where(FlowsheetSpecification.flowsheetDrugsClass(hsh_notes.getFlowsheetDrugClassId())).and(FlowsheetSpecification.flowsheetDrugsMapId(flowsheetId)));
	                 //List<Prescription> prescriptionElements=prescriptionRepository.findAll(PrescripitonSpecification.getactivemedwithclassIdpresc(patientId,hsh_notes.getFlowsheetDrugClassId(),encounterId,encounterEntity));

	                 List<PrescriptionServiceBean> prescriptionObject=new ArrayList<PrescriptionServiceBean>();
	                 prescriptionObject=serviceimpl.getMedDetailsWithClass(em,patientId,hsh_notes.getFlowsheetDrugClassId(),encounterId,encounterEntity);
	                 String[] classIdOverride=new String[1];
	                 classIdOverride[0]=hsh_notes.getFlowsheetDrugClassId();

	                 //getting list of override alerts having class id
	                 List<Overridealerts> overridealertsInt=overridealertsRepository.findAll(OverridealertsSpecification.overrideAlerts(classIdOverride, 5, patientId, flowsheetId,encounterId,encounterEntity));
	                 for(int k=0;k<overridealertsInt.size();k++){
	                     PrescriptionServiceBean tempPrescription=new PrescriptionServiceBean();
	                     CoreClassHierarchy core=new CoreClassHierarchy();
	                     core.setDisplayName(flowsheetDrug.getFlowsheetDrugClassName());
	                     tempPrescription.setBrandName("");
	                     String notes="Reason to Override: "+Optional.fromNullable(overridealertsInt.get(k).getReason()).or("-");
	                     tempPrescription.setComments(notes);
	                     tempPrescription.setOrderedDate((Timestamp)overridealertsInt.get(k).getOverriddenOn());
	                     prescriptionObject.add(tempPrescription);
	                 }
	                 //Order Labs by Date
	                 prescriptionObject=orderByDateDrug1(prescriptionObject, true);

	                 if(prescriptionObject.size()>0){
	                     for(int k=0;k<prescriptionObject.size();k++){
	                         drugBean = new FS_DrugBean();
	                         performedDate=MoreObjects.firstNonNull(prescriptionObject.get(k).getOrderedDate(),"Never Prescribed").toString();
	                         if(!performedDate.trim().contentEquals("Never Prescribed")){
	                             DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
	                             Date date = (Date) parser.parse(performedDate);
	                             DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	                             performedDate=formatter.format(date);
	                         }
	                         drugBean.setStatusOn(performedDate);
	                         String className = Optional.fromNullable(flowsheetDrug.getFlowsheetDrugClassName()).or("");
	                         drugBean.setGenericDrugName(className);
	                         drugBean.setGenericDrugId(hsh_notes.getFlowsheetDrugClassId());
	                         if(Optional.fromNullable(prescriptionObject.get(k).getComments()).or("").startsWith("Reason to Override: ")){
	                             drugBean.setStatus(Optional.fromNullable(prescriptionObject.get(k).getComments()).or(""));
	                             drugBean.setDrugName(prescriptionObject.get(k).getBrandName());
	                         }else{
	                             String name="";
	                             if(prescriptionObject.get(k).getBrandName()!=null)
	                                 name=prescriptionObject.get(k).getBrandName();
	                             else if(prescriptionObject.get(k).getUnit()!=null)
	                                 name+=prescriptionObject.get(k).getDose();
	                             else if(prescriptionObject.get(k).getForm()!=null)
	                                 name+=prescriptionObject.get(k).getForm();
	                             drugBean.setDrugName(name);
	                             MedStatus medStatus=medStatusRepository.findOne(PrescripitonSpecification.medStatus(prescriptionObject.get(k).getStatus()));
	                             drugBean.setStatus(Optional.fromNullable(medStatus.getMedStatusName()).or(""));
	                         }
	                         arr_drugs.add(drugBean);
	                     }
	                 }else{
	                     drugBean = new FS_DrugBean();
	                     performedDate="Never Prescribed";
	                     drugBean.setStatusOn(performedDate);
	                     if( flowsheetDrug != null ) {
	                         String className = Optional.fromNullable(flowsheetDrug.getFlowsheetDrugClassName()).or("");
	                         drugBean.setGenericDrugName(className);
	                     } else {
	                         drugBean.setGenericDrugName("");
	                     }
	                     drugBean.setGenericDrugId(hsh_notes.getFlowsheetDrugClassId());
	                     drugBean.setStatus("");
	                     drugBean.setDrugName("");
	                     arr_drugs.add(drugBean);
	                 }
	                 classId = hsh_notes.getFlowsheetDrugClassId();
	             }
	         }
	         if(classIdsComplete.size()>0)
	             arr_drugs = checkFlowSheetRulesForDrugData(patientId,flowsheetId,classIdsComplete, arr_drugs, startDate,patgender);
	        
	         return arr_drugs;
	}	
	public List<PrescriptionServiceBean> orderByDateDrug1(List<PrescriptionServiceBean> confData,final boolean descending){
        if (confData.size() > 0) {
            Collections.sort(confData, new Comparator<PrescriptionServiceBean>() {
                @Override
                public int compare(final PrescriptionServiceBean object1, final PrescriptionServiceBean object2) {
                    String empty="1900-05-13 16:40:35";
                    Timestamp object1TimeStamp=(Timestamp) Optional.fromNullable(object1.getOrderedDate()).or(Timestamp.valueOf(empty));
                    Timestamp object2TimeStamp=(Timestamp) Optional.fromNullable(object2.getOrderedDate()).or(Timestamp.valueOf(empty));
                    return (descending ? -1 : 1) * object1TimeStamp.compareTo(object2TimeStamp);
                }
            } );
        }
        return confData;
    }
	

	public ArrayList<FS_DrugBean> checkFlowSheetRulesForDrugData(Integer patientId, Integer flowSheetId,List<String> classIdsComplete,ArrayList<FS_DrugBean> flowSheetData, String startDate,Integer patgender) throws Exception{ 
		String currentDate = getCurrentDate();
		ArrayList<FS_DrugBean> tempFlowSheetData = new ArrayList<FS_DrugBean>();
		if(startDate!=null&&!startDate.equalsIgnoreCase("")){
		List<HmrBean> flowSheetRule = getFlowSheetRuleQueryPatientBased(patientId,classIdsComplete, startDate,5);
		String today = getTodayDate();
		//PatientRegistration genderPat =  patientRegistrationRepository.findOne(Specifications.where(PatientRegistrationSpecification.PatientId(patientId+"")));
		String gender = patgender.toString();
		//ArrayList<FS_DrugBean> tempFlowSheetData = new ArrayList<FS_DrugBean>();
		for(int flowSheetDataCounter = 0 ; flowSheetDataCounter < flowSheetData.size() ; flowSheetDataCounter++){
			FS_DrugBean flowSheetDataObj = flowSheetData.get(flowSheetDataCounter);
			boolean isElementRuleExists = false;
			boolean isElementGenderRuleSatisfied = false;
			flowSheetDataObj.setDueCriteria("");
			for(int dueRulesCounter = 0 ; dueRulesCounter < flowSheetRule.size() ; dueRulesCounter++ ){
				HmrBean flowSheetRuleObj = flowSheetRule.get(dueRulesCounter);
				if( flowSheetRuleObj.getElement_type().equals("5") && flowSheetRuleObj.getElement_id().equals(flowSheetDataObj.getGenericDrugId() ) ){
					isElementRuleExists = true;
					if(flowSheetRuleObj.getGender().toString().equals("0") || gender.equals(flowSheetRuleObj.getGender().toString()))
					{
						isElementGenderRuleSatisfied = true;
						if(( DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(today, flowSheetRuleObj.getEnddate().toString()) == -1 ) || DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 0){
							if(flowSheetRuleObj.getSchType()!=null && flowSheetRuleObj.getSchedule()!=null ){
								if((flowSheetRuleObj.getSchType().toString().equals("") || flowSheetRuleObj.getSchedule().toString().equals(""))){
									if(!flowSheetDataObj.getStatusOn().equals("Never Prescribed")){

										if(DateUtil.dateDiffVal(flowSheetDataObj.getStatusOn().toString(), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getStatusOn().toString(), flowSheetRuleObj.getEnddate().toString()) == -1){
											flowSheetDataObj.setDue("Completed");
											flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
											break;
										}
									}									
									flowSheetDataObj.setDue("Due Now");
									flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
									break;
								}
								if(!flowSheetRuleObj.getSchType().toString().equals("") && !flowSheetRuleObj.getSchedule().toString().equals("")){
									String freqStartDate = flowSheetRuleObj.getStartdate().toString();
									String freqEndDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(freqStartDate)), 3);
									while(DateUtil.dateDiffVal( flowSheetRuleObj.getEnddate().toString() , freqEndDate) == 1){
										freqEndDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(freqStartDate)), 3);
										if(DateUtil.dateDiffVal(freqEndDate, flowSheetRuleObj.getEnddate().toString()) == 1){
										} else {
											if(( DateUtil.dateDiffVal(today, freqStartDate ) == 1 && DateUtil.dateDiffVal(today, freqEndDate) == -1 ) || DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 0){
												if(!flowSheetDataObj.getStatusOn().equals("Never Prescribed")){
													if(flowSheetRuleObj.getRuledate().equals("3")){
														String rulePerformedDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(flowSheetDataObj.getStatusOn().toString())), 3);
														if(DateUtil.dateDiffVal(currentDate,rulePerformedDate) == -1){
															flowSheetDataObj.setDue("Completed");
															flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
															break;
														}
													}else{
														if(DateUtil.dateDiffVal(flowSheetDataObj.getStatusOn().toString(), freqStartDate) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getStatusOn().toString(), freqEndDate) == -1){
															flowSheetDataObj.setDue("Completed");
															flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
															break;
														}
													}
												}					
												flowSheetDataObj.setDue("Due Now");
												flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
												break;
											} else if(DateUtil.dateDiffVal(today, freqStartDate ) == 1 && DateUtil.dateDiffVal(today, freqEndDate ) == 1){

												flowSheetDataObj.setDue("Over Due");
												if(!flowSheetDataObj.getStatusOn().equals("Never Prescribed")){
													if(DateUtil.dateDiffVal(flowSheetDataObj.getStatusOn().toString(), freqStartDate ) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getStatusOn().toString(), freqEndDate) == -1){
														flowSheetDataObj.setDue("Completed");
														flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
													}
												}		

												if(!flowSheetDataObj.getStatusOn().equals("Never Prescribed")){
													if(DateUtil.dateDiffVal(flowSheetDataObj.getStatusOn().toString(), freqStartDate) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getStatusOn().toString(), freqEndDate ) == 1){
														flowSheetDataObj.setDue("");
														flowSheetDataObj.setDueCriteria("");
													}
												}	
											} 
											freqStartDate = freqEndDate;
										}
									}
								}
							}
						} else if(DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(today, flowSheetRuleObj.getEnddate().toString()) == 1){
							flowSheetDataObj.setDue("Over Due");
							flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
							if(!flowSheetDataObj.getStatusOn().equals("Never Prescribed")){
								if(DateUtil.dateDiffVal(flowSheetDataObj.getStatusOn().toString(), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getStatusOn().toString(), flowSheetRuleObj.getEnddate().toString()) == -1){
									flowSheetDataObj.setDue("Completed");
									flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
								}
							}		
							if(!flowSheetDataObj.getStatusOn().equals("Never Prescribed")){
								if(  ( DateUtil.dateDiffVal(flowSheetDataObj.getStatusOn().toString(), flowSheetRuleObj.getStartdate().toString()) == -1 && DateUtil.dateDiffVal(flowSheetDataObj.getStatusOn().toString(), flowSheetRuleObj.getEnddate().toString()) == -1) ||  ( DateUtil.dateDiffVal(flowSheetDataObj.getStatusOn().toString(), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getStatusOn().toString(), flowSheetRuleObj.getEnddate().toString()) == 1)){
									flowSheetDataObj.setDue("");
									flowSheetDataObj.setDueCriteria("");
								}
							}	
						} 
					} 
				}

			}
			if(isElementRuleExists == false){
				tempFlowSheetData.add(flowSheetDataObj);
			} else if(isElementRuleExists == true && isElementGenderRuleSatisfied == true){
				tempFlowSheetData.add(flowSheetDataObj);
			}

		}
		}
		return tempFlowSheetData;
	}

	@Override
	public List<FS_ClinicalElementOptionBean> getClinicalElements(Integer chartId,Integer encounterId,String gwIds, Integer isVital){
		Chart charts=chartRepository.findOne(Specifications.where(ChartSpecification.findByChartId(chartId)));
		Integer patientId=charts.getChartPatientid();
		List<FS_ClinicalElementOptionBean> cinicalElementOptionBeans=new ArrayList<FS_ClinicalElementOptionBean>();
		String[] elementIdArr=gwIds.split("~");
		for(int i=0;i<elementIdArr.length;i++){
			ClinicalElements clinicalElements=clinicalElementsRepository.findOne(ClinicalElementsSpecification.getActiveClinicalElement(elementIdArr[i]));
			List<ClinicalElementsOptions> clinicalElementOptions=clinicalElementsOptionsRepository.findAll(ClinicalElementsSpecification.getclinicalElementOptions(elementIdArr[i]));
			if(clinicalElementOptions.size()>0){
				for(int j=0;j<clinicalElementOptions.size();j++){
					FS_ClinicalElementOptionBean beanTemp=new FS_ClinicalElementOptionBean();
					beanTemp.setClinicalElementGwId(clinicalElements.getClinicalElementsGwid());
					beanTemp.setClinicalElementName(clinicalElements.getClinicalElementsName());
					beanTemp.setClinicalElementType(clinicalElements.getClinicalElementsDatatype());
					beanTemp.setClinicalElementOptionName(clinicalElementOptions.get(j).getClinicalElementsOptionsName());
					beanTemp.setClinicalElementOptionValue(clinicalElementOptions.get(j).getClinicalElementsOptionsValue());
					String units="";
					if(isVital==1){
						List<VitalsParameter> vitalsParameter=vitalsParameterRepository.findAll(VitalsSpecification.getUnitsForVitals(Arrays.asList(elementIdArr[i])));
						if(vitalsParameter.size()>0){
							String unitsOfMeasure=Optional.fromNullable(vitalsParameter.get(0).getUnitsOfMeasureTable().getUnitsOfMeasureCode()).or("").toString();
							if(!unitsOfMeasure.contentEquals("N/A"))
								units=unitsOfMeasure;
							else
								units="";

						}
					}
					beanTemp.setClinicalElementUnits(units);
					String value="";
					List<PatientClinicalElements> patientClinicalElements=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinicalDataByGWID(patientId, encounterId, Arrays.asList(elementIdArr[i])));
					for(int k=0;k<patientClinicalElements.size();k++){
						if(clinicalElements.getClinicalElementsDatatype()==5 
								&& clinicalElementOptions.get(j).getClinicalElementsOptionsValue().contentEquals(patientClinicalElements.get(k).getPatientClinicalElementsValue())){
							value=patientClinicalElements.get(k).getPatientClinicalElementsValue();
							break;
						}else if(clinicalElements.getClinicalElementsDatatype()!=5){
							value=patientClinicalElements.get(k).getPatientClinicalElementsValue();
							break;
						}else{
							value="";
						}
					}
					String gramValue,Feet,cmValue;
					DecimalFormat f = new DecimalFormat("#0.##");
					if (clinicalElements.getClinicalElementsName().equalsIgnoreCase("Weight")){
						gramValue = value;
						String lbs =HUtil.weightConversion(gramValue,"flowsheet");
						try{
							value=f.format(Double.parseDouble(lbs));
						}catch(Exception e){
							value=lbs;
						}
					}
					if (clinicalElements.getClinicalElementsName().equalsIgnoreCase("Height")){
						cmValue = value;
						Feet = HUtil.heightConversion(cmValue,"flowsheet");
						try{
							value=f.format(Double.parseDouble(Feet));
						}catch(Exception e){
							value=Feet;
						}
					}
					beanTemp.setPatientValue(value);
					cinicalElementOptionBeans.add(beanTemp);
				}
			}else{
				FS_ClinicalElementOptionBean beanTemp=new FS_ClinicalElementOptionBean();
				beanTemp.setClinicalElementGwId(clinicalElements.getClinicalElementsGwid());
				beanTemp.setClinicalElementName(clinicalElements.getClinicalElementsName());
				beanTemp.setClinicalElementType(clinicalElements.getClinicalElementsDatatype());
				beanTemp.setClinicalElementOptionName("");
				beanTemp.setClinicalElementOptionValue("");
				String units="";
				if(isVital==1){
					List<VitalsParameter> vitalsParameter=vitalsParameterRepository.findAll(VitalsSpecification.getUnitsForVitals(Arrays.asList(elementIdArr[i])));
					if(vitalsParameter.size()>0){
						String unitsOfMeasure=Optional.fromNullable(vitalsParameter.get(0).getUnitsOfMeasureTable().getUnitsOfMeasureCode()).or("").toString();
						if(!unitsOfMeasure.contentEquals("N/A"))
							units=unitsOfMeasure;
						else
							units="";

					}
				}
				beanTemp.setClinicalElementUnits(units);
				String value="";
				List<PatientClinicalElements> patientClinicalElements=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinicalDataByGWID(patientId, encounterId, Arrays.asList(elementIdArr[i])));
				for(int k=0;k<patientClinicalElements.size();k++){
					if(clinicalElements.getClinicalElementsDatatype()!=5){
						value=patientClinicalElements.get(k).getPatientClinicalElementsValue();
					}else{
						value="";
					}
				}
				String gramValue,Feet,cmValue;
				DecimalFormat f = new DecimalFormat("#0.##");
				if (clinicalElements.getClinicalElementsName().equalsIgnoreCase("Weight")){
					gramValue = value;
					String lbs =HUtil.weightConversion(gramValue,"flowsheet");
					try{
						value=f.format(Double.parseDouble(lbs));
					}catch(Exception e){
						value=lbs;
					}
				}
				if (clinicalElements.getClinicalElementsName().equalsIgnoreCase("Height")){
					cmValue = value;
					Feet = HUtil.heightConversion(cmValue,"flowsheet");
					try{
						value=f.format(Double.parseDouble(Feet));
					}catch(Exception e){
						value=Feet;
					}
				}
				beanTemp.setPatientValue(value);
				cinicalElementOptionBeans.add(beanTemp);
			}
		}
		return cinicalElementOptionBeans;

	}

	@Override
	public List<FlowsheetBean> getRecommendedLabs(Integer chartId, Integer patientId,Integer encounterId) throws Exception {
		List<FlowsheetBean> fBeanList=new ArrayList<FlowsheetBean>();
		List<FlowsheetNameBean> flowSheetIds =getFlowsheetNames(patientId);
		FlowsheetNameBean hsh_flowSheetIds = null; 
		for(int b=0;b<flowSheetIds.size();b++){
			hsh_flowSheetIds = flowSheetIds.get(b);
			Integer flowsheetId = Optional.fromNullable(hsh_flowSheetIds.getFlowsheetId()).or(-1);
			FlowsheetBean fBean=new FlowsheetBean();
			if(flowsheetId != -1){
				fBean.setFlowsheetId(flowsheetId);
				Flowsheet flowsheet=flowsheetRepository.findOne(Specifications.where(FlowsheetSpecification.flowsheetId(flowsheetId)).and(FlowsheetSpecification.flowsheetIsactive(true)));
				int flowsheetType=Optional.fromNullable(flowsheet.getFlowsheetType()).or(-1);
				fBean.setFlowsheetType(flowsheetType);
				fBean.setFlowsheetName(Optional.fromNullable(Strings.emptyToNull(flowsheet.getFlowsheetName())).or("").toString());
				String startDate = "";
				if(flowsheetType==1){
					//Preventive Management
					//PatientRegistration patientReg=patientRegistrationRepository.findOne(Specifications.where(PatientRegistrationSpecification.PatientId(patientId+"")));
					startDate = getpatientDOb(patientId).toString();
				} else if(flowsheetType==2){
					//Disease Management
					List<FlowsheetDx> flowsheetDxList=flowsheetDxRepository.findAll(Specifications.where(FlowsheetSpecification.flowsheetDxCodeBasedOnFlowsheetTypeId(2, flowsheetType)));
					ArrayList<String> dxCodes=new ArrayList<String>();
					for(int j=0;j<flowsheetDxList.size();j++){
						dxCodes.add(flowsheetDxList.get(j).getFlowsheetDxCode());
					}
					CriteriaBuilder builder = em.getCriteriaBuilder();
					CriteriaQuery<Object> cq = builder.createQuery();
					Root<ProblemList> root = cq.from(ProblemList.class);
					Predicate[] restrictions1 = new Predicate[] {
							root.get(ProblemList_.problemListDxCode).in(dxCodes),
							builder.equal(root.get(ProblemList_.problemListPatientId),patientId)
					};
					cq.select(builder.selectCase().when(builder.isNull(root.get(ProblemList_.problemListOnsetDate)), builder.function("to_char", String.class, root.get(ProblemList_.problemListCreatedon),builder.literal("yyyy-MM-dd"))).otherwise(builder.function("to_char", String.class, root.get(ProblemList_.problemListOnsetDate), builder.literal("yyyy-MM-dd"))));
					cq.where(restrictions1);
					try{
						List<Object> rstList=em.createQuery(cq).getResultList();
						if(rstList.size()>0)
							startDate = Optional.fromNullable(rstList.get(0)).or("").toString();
					}catch(Exception e){

					}
				}else if(flowsheetType==3){
					//Meaningful Use Measures
					CriteriaBuilder builder = em.getCriteriaBuilder();
					CriteriaQuery<Object> cq = builder.createQuery();
					cq.select(builder.currentDate()).from(FlowsheetType.class);
					Object rstList=em.createQuery(cq).getSingleResult();
					if(rstList!=null)
						startDate = Optional.fromNullable(rstList).or("").toString();
				}
				if((flowsheetType==1)||(flowsheetType==2)||(flowsheetType==3)){
					ArrayList<FS_LabBean> listLabBean=formDataRecommendedLabs(flowsheetId, startDate, patientId);
					listLabBean.addAll(formDataRecommendedLabsParams(flowsheetId, startDate, patientId));
					fBean.setLabData(listLabBean);
				}
			}
			fBeanList.add(fBean);
		}
		return fBeanList;
	}
	

	public ArrayList<FS_LabBean> formDataRecommendedLabsParams(int flowsheetId,String startDate, Integer patientId) throws Exception {
		// getting list of codes and there code system
		List<Integer> groupIds=getFlowsheetParamGroupId(flowsheetId,patientId);
		JSONObject groupIdParamIdMap=new JSONObject();
		String[][] codes= getFlowsheetParamCode(groupIds,groupIdParamIdMap);

		//getting the list of paramIds for the above codes
		List<Integer> paramsList=getParamIds(codes,groupIdParamIdMap);
		Integer[] paramIds=new Integer[paramsList.size()];
		for(int k=0;k<paramsList.size();k++){
			//paramIds[k]=Optional.fromNullable(paramsList.get(k).getLabParametersId()).or(-1);
			paramIds[k]=Optional.fromNullable(paramsList.get(k)).or(-1);
		}
		if(paramIds.length==0){
			paramIds=new Integer[1];
			paramIds[0]=-2;
		}
		//getting the chart id
		Integer chartId=-1;
		List<Chart> charts=chartRepository.findAll(Specifications.where(ChartSpecification.patientId(patientId)));
		chartId=charts.get(0).getChartId();

		//getting the patient gender
		//PatientRegistration patients=patientRegistrationRepository.findOne(Specifications.where(PatientRegistrationSpecification.PatientId(patientId+"")));
		Integer gender=getPatientGender(patientId);

		//getting list of performed parameters having param id and chart id
		List<ParamStandardGroup> perfOrders=paramStandardGroupRepository.findAll(Specifications.where(FlowsheetSpecification.paramStandardGroupIdIsActive(groupIds)));
		ArrayList<FS_LabBean> arr_param = new ArrayList<FS_LabBean>();
		Iterator<ParamStandardGroup> itr_param = perfOrders.iterator();
		ParamStandardGroup hsh_param = new ParamStandardGroup();
		FS_LabBean paramBean = null;
		String paramName = "";
		String prevParamName = "";
		while(itr_param.hasNext()){
			hsh_param = itr_param.next();
			ArrayList<FS_LabBean> arr_labTemp=new ArrayList<FS_LabBean>();
			String testIdsTemp="";
			ArrayList<Integer> testDetailIdsTemp=new ArrayList<Integer>();
			if(!prevParamName.equalsIgnoreCase(hsh_param.getParamStandardGroupName())){
				arr_labTemp=new ArrayList<FS_LabBean>();
				testDetailIdsTemp=new ArrayList<Integer>();
				String[] paramIdTemp=groupIdParamIdMap.get(hsh_param.getParamStandardGroupId()+"").toString().split("@@@@");
				Integer[] paramIdsGroup=new Integer[paramIdTemp.length-1];
				for(int l=1;l<paramIdTemp.length;l++){
					Integer paramId = Integer.parseInt(paramIdTemp[l].split("###")[0]);
					paramIdsGroup[l-1]=paramId;
				}
				if(paramIdsGroup.length==0){
					paramIdsGroup=new Integer[1];
					paramIdsGroup[0]=-2;
				}
				List<LabDescpParameters> testIdTemp=labDescpParametersRepository.findAll(InvestigationSpecification.getParamIdBased(paramIdsGroup));
				List<Integer> testIdArrayTemp=new ArrayList<Integer>();
				for(int l=0;l<testIdTemp.size();l++){
					if(testIdsTemp.trim().length()==0){
						testIdsTemp=testIdTemp.get(l).getLabDescpParameterTestid()+"";
					}else{
						testIdsTemp+=","+testIdTemp.get(l).getLabDescpParameterTestid();
					}
					if(!testIdArrayTemp.contains(testIdTemp.get(l).getLabDescpParameterTestid()))
						testIdArrayTemp.add(testIdTemp.get(l).getLabDescpParameterTestid());
				}
				Integer[] testIdArray=testIdArrayTemp.toArray(new Integer[testIdArrayTemp.size()]);
				testDetailIdsTemp.addAll(getTestDetailIds(chartId,testIdArray));

				//getting list of params having param id and chart id
				List<LabEntriesParameter> perfParamsInt=labEntriesParameterRepository.findAll(Specifications.where(InvestigationSpecification.getParamDetailsStatus(paramIdsGroup, chartId)));

				List<ChartStatus> statusList=h068Repository.findAll(InitialSettingsSpecification.getIdType(413));
				if(perfParamsInt.size()>0){
					for(int k=0;k<perfParamsInt.size();k++){
						paramBean = new FS_LabBean();
						paramName = Optional.fromNullable(hsh_param.getParamStandardGroupName()).or("");
						paramBean.setLabName(paramName);
						paramBean.setGroupId(hsh_param.getParamStandardGroupId());
						String perOn="Never Performed";
						String status="";
						String notes=" ";
						perOn=MoreObjects.firstNonNull(perfParamsInt.get(k).getLabEntriesParameterDate(),"Never Performed").toString();
						if(!perOn.trim().contentEquals("Never Performed")){
							DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
							Date date = (Date) parser.parse(perOn);
							DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
							perOn=formatter.format(date);
						}
						paramBean.setPerformedOn(perOn);
						paramBean.setStatus(status);
						paramBean.setGender(gender+"");
						for(int n=0;n<statusList.size();n++){
							if(statusList.get(n).getchart_status_status_id()==perfParamsInt.get(k).getLabEntriesTable().getLabEntriesTestStatus()){
								status=statusList.get(n).getchart_status_status_name();
								break;
							}
						}
						notes=Optional.fromNullable(perfParamsInt.get(k).getLabEntriesParameterValue()).or("")+" "
								+Optional.fromNullable(perfParamsInt.get(k).getLabParametersTable().getLabParametersUnits()).or("");
						paramBean.setResultNotes(notes);
						arr_labTemp.add(paramBean);
					} 
				}else{

					paramBean = new FS_LabBean();
					paramName = Optional.fromNullable(hsh_param.getParamStandardGroupName()).or("");
					paramBean.setLabName(paramName);
					paramBean.setGroupId(hsh_param.getParamStandardGroupId());
					String perOn="Never Performed";
					paramBean.setPerformedOn(perOn);
					paramBean.setResultNotes("");
					paramBean.setGender(gender+"");
					paramBean.setStatus("");
					arr_labTemp.add(paramBean);
				}
			}
			for(int h=0;h<arr_labTemp.size();h++){
				arr_labTemp.get(h).setIslab("0");
				arr_labTemp.get(h).setLabTestId(testIdsTemp);
				arr_labTemp.get(h).settestDetailId(testDetailIdsTemp);
			}
			arr_param.addAll(arr_labTemp);
			prevParamName = hsh_param.getParamStandardGroupName(); 
		}
		arr_param=orderByName(arr_param);
		if(groupIds.size()>0)
			arr_param = checkFlowSheetRulesRecommendedLabs(patientId,chartId, groupIds, arr_param, startDate);
		return arr_param;
	}

	public ArrayList<FS_LabBean> formDataRecommendedLabs(int flowsheetId,String startDate, Integer patientId) throws Exception{
		// getting list of codes and there code system
		List<Integer> groupIds=getFlowsheetLabGroupId(flowsheetId,patientId);
		JSONObject groupIdTestIdMap=new JSONObject();
		String[][] codes= getFlowsheetLabCode(groupIds,groupIdTestIdMap);

		//getting the list of testIds for the above codes
		List<LabDescription> labsList=getLabTestIds(codes,groupIdTestIdMap);
		Integer[] testIds=new Integer[labsList.size()];
		for(int k=0;k<labsList.size();k++){
			testIds[k]=Optional.fromNullable(labsList.get(k).getLabDescriptionTestid()).or(-1);
		}
		if(testIds.length==0){
			testIds=new Integer[1];
			testIds[0]=-2;
		}
		//getting the chart id
		Integer chartId=-1;
		List<Chart> charts=chartRepository.findAll(Specifications.where(ChartSpecification.patientId(patientId)));
		chartId=charts.get(0).getChartId();

		//getting the patient gender
		//PatientRegistration patients=patientRegistrationRepository.findOne(Specifications.where(PatientRegistrationSpecification.PatientId(patientId+"")));
		Integer gender=getPatientGender(patientId);

		List<LabStandardGroup> perfOrders=labStandardGroupRepository.findAll(Specifications.where(FlowsheetSpecification.labStandardGroupIdIsActive(groupIds)));
		ArrayList<FS_LabBean> arr_lab = new ArrayList<FS_LabBean> ();
		Iterator<LabStandardGroup> itr_lab = perfOrders.iterator();
		LabStandardGroup hsh_lab = new LabStandardGroup();
		FS_LabBean labBean = null;
		String labName = "";
		String prevLabName = "";
		while(itr_lab.hasNext()){
			hsh_lab = itr_lab.next();
			ArrayList<FS_LabBean> arr_labTemp=new ArrayList<FS_LabBean>();
			String testIdsTemp="";
			ArrayList<Integer> testDetailIdsTemp=new ArrayList<Integer>();
			if(!prevLabName.equalsIgnoreCase(hsh_lab.getLabStandardGroupName())){
				arr_labTemp=new ArrayList<FS_LabBean>();
				testDetailIdsTemp=new ArrayList<Integer>();
				String[] testIdTemp=groupIdTestIdMap.get(hsh_lab.getLabStandardGroupId()+"").toString().split("@@@@");
				Integer[] testIdsGroup=new Integer[testIdTemp.length-1];
				Integer[] testIdArray=new Integer[testIdTemp.length];
				for(int l=1;l<testIdTemp.length;l++){
					Integer testId = Integer.parseInt(testIdTemp[l].split("###")[0]);
					testIdsGroup[l-1]=testId;
					if(testIdsTemp.trim().length()==0){
						testIdsTemp=testId+"";
					}else{
						testIdsTemp+=","+testId;
					}
					testIdArray[l-1]=testId;
				}
				if(testIdsGroup.length==0){
					testIdsGroup=new Integer[1];
					testIdsGroup[0]=-2;
				}
				testDetailIdsTemp.addAll(getTestDetailIds(chartId,testIdArray));
				//getting list of performed labs having test id and chart id and not vaccines
				List<LabEntries> perfOrdersInt=labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.getDetailsExceptVaccinesNotPatDecli(testIdsGroup, chartId, 2, 7)));

				//getting list of vaccines which have been entered both manually
				List<VaccineReport> perfVaccinesInt=vaccineReportRepository.findAll(Specifications.where(VaccineReportSpecification.getDetailsOnlyVaccinesNotPatDecli(testIdsGroup,chartId)));
				for(int k=0;k<perfVaccinesInt.size();k++){
					perfVaccinesInt.get(k).setVaccineReportIsemr(3);
				}
				//getting list of vaccines which have been entered via investigation
				List<LabEntries> perfVaccinesIntInv=labEntriesRepository.findAll(Specifications.where(InvestigationSpecification.getDetailsVaccinesNotPatDecli(testIdsGroup, chartId, 2, 7)));
				if(perfVaccinesIntInv.size()>0){
					String[] groupId=new String[1];
					groupId[0]=hsh_lab.getLabStandardGroupId()+"";
					for(int k=0;k<perfVaccinesIntInv.size();k++){
						VaccineReport tempLabEntries=new VaccineReport();
						tempLabEntries.setVaccineReportVaccineId(Optional.fromNullable(perfVaccinesIntInv.get(k).getLabEntriesTestId()).or(-1));
						tempLabEntries.setVaccineReportGivenDate((Timestamp)perfVaccinesIntInv.get(k).getLabEntriesPerfOn());
						tempLabEntries.setVaccineReportComments(Optional.fromNullable(perfVaccinesIntInv.get(k).getLabEntriesResultNotes()).or("-"));
						tempLabEntries.setVaccineReportIsemr(perfVaccinesIntInv.get(k).getLabEntriesTestStatus());
						perfVaccinesInt.add(tempLabEntries);
					}
				}
				//Order Vaccines by Status
				perfVaccinesInt=orderByStatusVaccines(perfVaccinesInt, false);
				//Order Vaccines by Date
				perfVaccinesInt=orderByDateVaccines(perfVaccinesInt, true);

				List<ChartStatus> statusList=h068Repository.findAll(InitialSettingsSpecification.getIdType(413));
				if(perfOrdersInt.size()>0){
					for(int k=0;k<perfOrdersInt.size();k++){
						labBean = new FS_LabBean();
						labName = Optional.fromNullable(hsh_lab.getLabStandardGroupName()).or("");
						labBean.setLabName(labName);
						labBean.setGroupId(hsh_lab.getLabStandardGroupId());
						String ordOn="";
						String prfOnSort="1900-05-13 16:40:35";
						String perOn="Never Performed";
						String status="";
						String notes=" ";
						if(perfOrdersInt.size()>0){
							ordOn=MoreObjects.firstNonNull(perfOrdersInt.get(k).getLabEntriesOrdOn(),"").toString();
							prfOnSort=MoreObjects.firstNonNull(perfOrdersInt.get(k).getLabEntriesOrdOn(),"1900-05-13 16:40:35").toString();
							perOn=MoreObjects.firstNonNull(perfOrdersInt.get(k).getLabEntriesPerfOn(),"Never Performed").toString();
							for(int n=0;n<statusList.size();n++){
								if(statusList.get(n).getchart_status_status_id()==perfOrdersInt.get(k).getLabEntriesTestStatus()){
									status=statusList.get(n).getchart_status_status_name();
									break;
								}
							}
							notes=Optional.fromNullable(perfOrdersInt.get(k).getLabEntriesResultNotes()).or("").replace("!@!", "");
						}
						if(!ordOn.trim().contentEquals("")){
							DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
							Date date = (Date) parser.parse(ordOn);
							DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); 
							ordOn=formatter.format(date);
						}
						labBean.setOrderedOn(ordOn);
						labBean.setPerformdatesort(prfOnSort);
						if(!perOn.trim().contentEquals("Never Performed")){
							DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
							Date date = (Date) parser.parse(perOn);
							DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
							perOn=formatter.format(date);
						}
						labBean.setPerformedOn(perOn);
						labBean.setStatus(status);
						labBean.setResultNotes(notes);
						labBean.setGender(gender+"");
						arr_labTemp.add(labBean);
					} 
				}else if(perfVaccinesInt.size()>0){
					for(int k=0;k<perfVaccinesInt.size();k++){
						labBean = new FS_LabBean();
						labName = Optional.fromNullable(hsh_lab.getLabStandardGroupName()).or("");
						labBean.setLabName(labName);
						labBean.setGroupId(hsh_lab.getLabStandardGroupId());
						String ordOn="";
						String prfOnSort="1900-05-13 16:40:35";
						String perOn="Never Performed";
						if(perfVaccinesInt.size()>0){
							ordOn=MoreObjects.firstNonNull(perfVaccinesInt.get(k).getVaccineReportGivenDate(),"").toString();
							prfOnSort=MoreObjects.firstNonNull(perfVaccinesInt.get(k).getVaccineReportGivenDate(),"1900-05-13 16:40:35").toString();
							perOn=MoreObjects.firstNonNull(perfVaccinesInt.get(k).getVaccineReportGivenDate(),"Never Performed").toString();
						}
						if(!ordOn.trim().contentEquals("")){
							DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
							Date date = (Date) parser.parse(ordOn);
							DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); 
							ordOn=formatter.format(date);
						}
						labBean.setOrderedOn(ordOn);
						labBean.setPerformdatesort(prfOnSort);
						if(!perOn.trim().contentEquals("Never Performed")){
							DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
							Date date = (Date) parser.parse(perOn);
							DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
							perOn=formatter.format(date);
						}
						labBean.setPerformedOn(perOn);
						String status="";
						for(int n=0;n<statusList.size();n++){
							if(statusList.get(n).getchart_status_status_id()==perfVaccinesInt.get(k).getVaccineReportIsemr()){
								status=statusList.get(n).getchart_status_status_name();
								break;
							}
						}
						labBean.setStatus(status);
						labBean.setResultNotes(Optional.fromNullable(perfVaccinesInt.get(k).getVaccineReportComments()).or("").replace("!@!", ""));
						labBean.setGender(gender+"");
						arr_labTemp.add(labBean);
					}
				}else{
					labBean = new FS_LabBean();
					labName = Optional.fromNullable(hsh_lab.getLabStandardGroupName()).or("");
					labBean.setLabName(labName);
					labBean.setGroupId(hsh_lab.getLabStandardGroupId());
					String ordOn="";
					String prfOnSort="1900-05-13 16:40:35";
					String perOn="Never Performed";
					String status="";
					String notes=" ";
					labBean.setOrderedOn(ordOn);
					labBean.setPerformdatesort(prfOnSort);
					labBean.setPerformedOn(perOn);
					labBean.setStatus(status);
					labBean.setResultNotes(notes);
					labBean.setGender(gender+"");
					arr_labTemp.add(labBean);
				}
			}
			for(int h=0;h<arr_labTemp.size();h++){
				arr_labTemp.get(h).setIslab("1");
				arr_labTemp.get(h).setLabTestId(testIdsTemp);
				arr_labTemp.get(h).settestDetailId(testDetailIdsTemp);
			}
			arr_lab.addAll(arr_labTemp);
			prevLabName = hsh_lab.getLabStandardGroupName(); 
		} 
		arr_lab=orderByName(arr_lab);
		if(groupIds.size()>0)
			arr_lab = checkFlowSheetRulesRecommendedLabs(patientId,chartId, groupIds, arr_lab, startDate);
		return arr_lab;
	}

	public ArrayList<FS_LabBean> checkFlowSheetRulesRecommendedLabs(Integer patientId, Integer chartId, List<Integer> groupIds,ArrayList<FS_LabBean> flowSheetData, String startDate) throws Exception{
		List<String> newGroupIds=Lists.transform(groupIds, Functions.toStringFunction());
		ArrayList<FS_LabBean> tempFlowSheetData = new ArrayList<FS_LabBean>();
		if(startDate!=null && !startDate.equalsIgnoreCase("")){
		List<HmrBean> flowSheetRule = getFlowSheetRuleQueryPatientBased(patientId,newGroupIds, startDate,3);
		String today = getTodayDate();
		//ArrayList<FS_LabBean> tempFlowSheetData = new ArrayList<FS_LabBean>();
		for(int flowSheetDataCounter = 0 ; flowSheetDataCounter < flowSheetData.size() ; flowSheetDataCounter++){
			FS_LabBean flowSheetDataObj = flowSheetData.get(flowSheetDataCounter);
			boolean isElementRuleExists = false;
			boolean isElementGenderRuleSatisfied = false;
			flowSheetDataObj.setDueCriteria("");
			if(flowSheetDataObj.getIslab().equals("0") || flowSheetDataObj.getIslab().equals("1")){
				//Checking Rule for Lab Parameters
				for(int dueRulesCounter = 0 ; dueRulesCounter < flowSheetRule.size() ; dueRulesCounter++ ){
					HmrBean flowSheetRuleObj = flowSheetRule.get(dueRulesCounter);
					if( (flowSheetRuleObj.getElement_type().equals("3") && flowSheetRuleObj.getElement_id().equals(flowSheetDataObj.getLabTestId() )) || (flowSheetRuleObj.getElement_type().equals("2") && flowSheetRuleObj.getElement_id().equals(flowSheetDataObj.getLabTestId() ))){
						isElementRuleExists = true;
						if(flowSheetRuleObj.getGender().toString().equals("0") || flowSheetDataObj.getGender().equals(flowSheetRuleObj.getGender().toString()))
						{
							isElementGenderRuleSatisfied = true;
							if(( DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(today, flowSheetRuleObj.getEnddate().toString()) == -1 ) || DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 0){
								if(flowSheetRuleObj.getSchType()!=null && flowSheetRuleObj.getSchedule()!=null ){
									if((flowSheetRuleObj.getSchType().toString().equals("") || flowSheetRuleObj.getSchedule().toString().equals(""))){
										if(!flowSheetDataObj.getPerformedOn().equals("Never Performed")){
											if(DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString(), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString(), flowSheetRuleObj.getEnddate().toString()) == -1){
												flowSheetDataObj.setDue("Completed");
												flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
												break;
											}
										}									
										flowSheetDataObj.setDue("Due Now");
										flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
										break;
									}
									if(!flowSheetRuleObj.getSchType().toString().equals("") && !flowSheetRuleObj.getSchedule().toString().equals("")){
										String freqStartDate = flowSheetRuleObj.getStartdate().toString();
										String freqEndDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(freqStartDate)), 3);
										while(DateUtil.dateDiffVal( flowSheetRuleObj.getEnddate().toString() , freqEndDate) == 1){
											freqEndDate = DateUtil.formatDateTime(DateUtil.dateAdd(Integer.parseInt(flowSheetRuleObj.getSchType().toString()) + 4 ,Integer.parseInt(flowSheetRuleObj.getSchedule().toString()), DateUtil.getDate(freqStartDate)), 3);
											if(DateUtil.dateDiffVal(freqEndDate, flowSheetRuleObj.getEnddate().toString()) == 1){
												//break;
											} else {
												//Due Checking for schedule Range
												if(( DateUtil.dateDiffVal(today, freqStartDate ) == 1 && DateUtil.dateDiffVal(today, freqEndDate) == -1 ) || DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 0){
													if(!flowSheetDataObj.getPerformedOn().equals("Never Performed")){
														if(DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString(), freqStartDate) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString(), freqEndDate) == -1){
															flowSheetDataObj.setDue("Completed");
															flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
															break;
														}
													}									
													flowSheetDataObj.setDue("Due Now");
													flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
													break;
												} else if(DateUtil.dateDiffVal(today, freqStartDate ) == 1 && DateUtil.dateDiffVal(today, freqEndDate ) == 1){
													flowSheetDataObj.setDue("Over Due");
													if(!flowSheetDataObj.getPerformedOn().equals("Never Performed")){
														if(DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString(), freqStartDate ) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString(), freqEndDate) == -1){
															flowSheetDataObj.setDue("Completed");
															flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
														}
													}		

													if(!flowSheetDataObj.getPerformedOn().equals("Never Performed")){
														if(DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString(), freqStartDate) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString(), freqEndDate ) == 1){
															flowSheetDataObj.setDue("");
															flowSheetDataObj.setDueCriteria("");
														}
													}	
												} 
												freqStartDate = freqEndDate;
											}
										}
									}
								}
							} else if(DateUtil.dateDiffVal(today, flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(today, flowSheetRuleObj.getEnddate().toString()) == 1){
								flowSheetDataObj.setDue("Over Due");
								flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
								if(!flowSheetDataObj.getPerformedOn().equals("Never Performed")){
									if(DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString(), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString(), flowSheetRuleObj.getEnddate().toString()) == -1){
										flowSheetDataObj.setDue("Completed");
										flowSheetDataObj.setDueCriteria(flowSheetRuleObj.getDesc().toString());
									}
								}		
								if(!flowSheetDataObj.getPerformedOn().equals("Never Performed")){
									if(  ( DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString(), flowSheetRuleObj.getStartdate().toString()) == -1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString(), flowSheetRuleObj.getEnddate().toString()) == -1) ||  ( DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString(), flowSheetRuleObj.getStartdate().toString()) == 1 && DateUtil.dateDiffVal(flowSheetDataObj.getPerformedOn().toString(), flowSheetRuleObj.getEnddate().toString()) == 1)){
										flowSheetDataObj.setDue("");
										flowSheetDataObj.setDueCriteria("");
									}
								}	
							} 
						} 
					}

				}
			} 
			if(isElementRuleExists == false){
				tempFlowSheetData.add(flowSheetDataObj);
			} else if(isElementRuleExists == true && isElementGenderRuleSatisfied == true){
				tempFlowSheetData.add(flowSheetDataObj);
			}				
		}
		}
		return tempFlowSheetData;
	}

	@Override
	public FS_ConfiguredDetails getFlowsheetsStandardGroupDetails(Integer isStandardLab) {
		FS_ConfiguredDetails flowsheetConfiguredDetails=new FS_ConfiguredDetails();
		if(isStandardLab==1){
			List<LabStandardGroup> labGroupList=labStandardGroupRepository.findAll(FlowsheetSpecification.labStandardGroupIsActive(true));
			for(int i=0;i<labGroupList.size();i++){
				Integer groupId=Optional.fromNullable(labGroupList.get(i).getLabStandardGroupId()).or(-1);
				if(groupId!=-1){
					//labStandardCodeRepository.findAll(FlowsheetSpecification.labStandardCodeGroupId(groupId));
					//labGroupList.get(i).setLabStandardCodeTable(labStandardCodeRepository.findAll(FlowsheetSpecification.labStandardCodeGroupId(groupId)));
					labGroupList.get(i).setLabStandardCodeTable(getlabStandardGroupcodes(groupId));
				}
				if(labGroupList.get(i).getLabStandardCodeTable().size()>0){
					for(int j=0;j<labGroupList.get(i).getLabStandardCodeTable().size();j++){
						LabStandardCode temp=labGroupList.get(i).getLabStandardCodeTable().get(j);
						if(temp.getLabStandardCodeGroupCodesystem().contentEquals(FlowsheetServiceImpl.GlenwoodSystemsOID)&&
								temp.getLabStandardCodeGroupGwid()!=null){
							temp.setLabDescriptionTable(labDescriptionRepository.findOne(InvestigationSpecification.labByTestId(temp.getLabStandardCodeGroupGwid())));
						}
					}
				}
			}
			labGroupList=orderByLabGroupName(labGroupList);
			flowsheetConfiguredDetails.setLabStandardGroup(labGroupList);
		}else{
			List<ParamStandardGroup> paramGroupList=paramStandardGroupRepository.findAll(FlowsheetSpecification.paramStandardGroupIsActive(true));
			for(int i=0;i<paramGroupList.size();i++){
				Integer groupId=Optional.fromNullable(paramGroupList.get(i).getParamStandardGroupId()).or(-1);
				if(groupId!=-1){
					paramGroupList.get(i).setParamStandardCodeTable(paramStandardCodeRepository.findAll(FlowsheetSpecification.paramStandardCodeGroupId(groupId)));
				}
				if(paramGroupList.get(i).getParamStandardCodeTable().size()>0){
					for(int j=0;j<paramGroupList.get(i).getParamStandardCodeTable().size();j++){
						ParamStandardCode temp=paramGroupList.get(i).getParamStandardCodeTable().get(j);
						if(temp.getParamStandardCodeGroupCodesystem().contentEquals(FlowsheetServiceImpl.GlenwoodSystemsOID)&&
								temp.getParamStandardCodeGroupGwid()!=null){
							temp.setLabParametersTable(labParametersRepository.findOne(InvestigationSpecification.getParamDetails(temp.getParamStandardCodeGroupGwid())));
						}
					}
				}
			}
			paramGroupList=orderByParamGroupName(paramGroupList);
			flowsheetConfiguredDetails.setParamStandardGroup(paramGroupList);
		}
		return flowsheetConfiguredDetails;
	}

	private List<LabStandardCode> getlabStandardGroupcodes(Integer groupId) {
		CriteriaBuilder cb1=em.getCriteriaBuilder();
		CriteriaQuery<LabStandardCode> cq1 = cb1.createQuery(LabStandardCode.class);
		List<LabStandardCode> stndcode= new ArrayList<LabStandardCode>();
		Root<LabStandardCode> root1 = cq1.from(LabStandardCode.class);
		@SuppressWarnings("rawtypes")
		Selection[] selectionsforVR=new Selection[]{
			root1.get(LabStandardCode_.labStandardCodeGroupId),
			root1.get(LabStandardCode_.labStandardCodeGroupCode),
			root1.get(LabStandardCode_.labStandardCodeGroupGwid),
			root1.get(LabStandardCode_.labStandardCodeGroupCodesystem),

		};
		cq1.select(cb1.construct(LabStandardCode.class,selectionsforVR));
		Predicate[] restrictionsforVR=new Predicate[]{
				cb1.and(root1.get(LabStandardCode_.labStandardCodeGroupId).in(groupId)),
		};
		cq1.where (restrictionsforVR);
		try{
			stndcode= em.createQuery(cq1).getResultList();	

		}catch(Exception e){
			e.printStackTrace();
		}
		return stndcode;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public FS_ConfiguredDetails saveFlowsheetsStandardGroupDetails(Integer isStandardLab,String saveGroupData){
		FS_ConfiguredDetails flowsheetConfiguredDetails=new FS_ConfiguredDetails();
		String message="";
		Integer groupId=-1;
		String groupName="";
		try{
			if(saveGroupData!=""){
				String[] saveGroupDataArr=saveGroupData.split("####");
				if(saveGroupDataArr.length>0){
					String[] groupArr=saveGroupDataArr[0].split("@@@@");
					if(groupArr.length>0){
						if(isStandardLab==1){
							LabStandardGroup standardGroup=new LabStandardGroup();
							if(groupArr[0].contentEquals("-1")){
								CriteriaBuilder builder = em.getCriteriaBuilder();
								CriteriaQuery<Integer> cq = builder.createQuery(Integer.class);
								Root<LabStandardGroup> root=cq.from(LabStandardGroup.class);
								Selection[] multi= new Selection[] { 
										builder.max(root.get(LabStandardGroup_.labStandardGroupId))
								};
								cq.multiselect(multi);
								Integer standardGroupMax= em.createQuery(cq).getSingleResult();
								standardGroup.setLabStandardGroupId(standardGroupMax+1);
								standardGroup.setLabStandardGroupName(groupArr[1]);
								standardGroup.setLabStandardGroupGender(5);
								standardGroup.setLabStandardGroupIsactive(true);
								LabStandardGroup standardGroupNew=labStandardGroupRepository.save(standardGroup);
								groupId=standardGroupNew.getLabStandardGroupId();
								groupName=standardGroupNew.getLabStandardGroupName();
							}else{
								standardGroup=labStandardGroupRepository.findOne(FlowsheetSpecification.labStandardGroup(Integer.parseInt(groupArr[0])));
								standardGroup.setLabStandardGroupName(groupArr[1]);
								LabStandardGroup standardGroupNew=labStandardGroupRepository.saveAndFlush(standardGroup);
								groupId=standardGroupNew.getLabStandardGroupId();
								groupName=standardGroupNew.getLabStandardGroupName();
							}
							if(groupId!=-1){
								String[] codeArr=saveGroupDataArr[1].split("@@@@");
								List<LabStandardCode> newStandardCodes=new ArrayList<LabStandardCode>();
								for(int i=0;i<codeArr.length;i++){
									String[] codeSystemArr=codeArr[i].split("~~~~");
									if(codeSystemArr[0].contentEquals(FlowsheetServiceImpl.GlenwoodSystemsOID)){
										LabStandardCode newStandardCodesTemp=new LabStandardCode();
										newStandardCodesTemp.setLabStandardCodeId(-1);
										newStandardCodesTemp.setLabStandardCodeGroupId(groupId);
										newStandardCodesTemp.setLabStandardCodeGroupGwid(Integer.parseInt(Optional.fromNullable(codeSystemArr[1]).or("-1")));
										newStandardCodesTemp.setLabStandardCodeGroupCodesystem(FlowsheetServiceImpl.GlenwoodSystemsOID);
										newStandardCodes.add(newStandardCodesTemp);
									}else{
										LabStandardCode newStandardCodesTemp=new LabStandardCode();
										newStandardCodesTemp.setLabStandardCodeId(-1);
										newStandardCodesTemp.setLabStandardCodeGroupId(groupId);
										newStandardCodesTemp.setLabStandardCodeGroupCode(Optional.fromNullable(codeSystemArr[1]).or(""));
										newStandardCodesTemp.setLabStandardCodeGroupCodesystem(codeSystemArr[0]);
										newStandardCodes.add(newStandardCodesTemp);
									}
								}
								
								labStandardCodeRepository.delete(DeletelabStandardCodeGroupId(groupId));
								labStandardCodeRepository.save(newStandardCodes);
							}
							message="Saved successfully.";
						}else if(isStandardLab==2){
							ParamStandardGroup standardGroup=new ParamStandardGroup();
							if(groupArr[0].contentEquals("-1")){
								CriteriaBuilder builder = em.getCriteriaBuilder();
								CriteriaQuery<Integer> cq = builder.createQuery(Integer.class);
								Root<ParamStandardGroup> root=cq.from(ParamStandardGroup.class);
								Selection[] multi= new Selection[] { 
										builder.max(root.get(ParamStandardGroup_.paramStandardGroupId))
								};
								cq.multiselect(multi);
								Integer standardGroupMax= em.createQuery(cq).getSingleResult();
								standardGroup.setParamStandardGroupId(standardGroupMax+1);
								standardGroup.setParamStandardGroupName(groupArr[1]);
								standardGroup.setParamStandardGroupGender(5);
								standardGroup.setParamStandardGroupIsactive(true);
								ParamStandardGroup standardGroupNew=paramStandardGroupRepository.save(standardGroup);
								groupId=standardGroupNew.getParamStandardGroupId();
								groupName=standardGroupNew.getParamStandardGroupName();
							}else{
								standardGroup=paramStandardGroupRepository.findOne(FlowsheetSpecification.paramStandardGroup(Integer.parseInt(groupArr[0])));
								standardGroup.setParamStandardGroupName(groupArr[1]);
								ParamStandardGroup standardGroupNew=paramStandardGroupRepository.saveAndFlush(standardGroup);
								groupId=standardGroupNew.getParamStandardGroupId();
								groupName=standardGroupNew.getParamStandardGroupName();
							}
							if(groupId!=-1){
								String[] codeArr=saveGroupDataArr[1].split("@@@@");
								List<ParamStandardCode> newStandardCodes=new ArrayList<ParamStandardCode>();
								for(int i=0;i<codeArr.length;i++){
									String[] codeSystemArr=codeArr[i].split("~~~~");
									if(codeSystemArr[0].contentEquals(FlowsheetServiceImpl.GlenwoodSystemsOID)){
										ParamStandardCode newStandardCodesTemp=new ParamStandardCode();
										newStandardCodesTemp.setParamStandardCodeId(-1);
										newStandardCodesTemp.setParamStandardCodeGroupId(groupId);
										newStandardCodesTemp.setParamStandardCodeGroupGwid(Integer.parseInt(Optional.fromNullable(codeSystemArr[1]).or("-1")));
										newStandardCodesTemp.setParamStandardCodeGroupCodesystem(FlowsheetServiceImpl.GlenwoodSystemsOID);
										newStandardCodes.add(newStandardCodesTemp);
									}else{
										ParamStandardCode newStandardCodesTemp=new ParamStandardCode();
										newStandardCodesTemp.setParamStandardCodeId(-1);
										newStandardCodesTemp.setParamStandardCodeGroupId(groupId);
										newStandardCodesTemp.setParamStandardCodeGroupCode(Optional.fromNullable(codeSystemArr[1]).or(""));
										newStandardCodesTemp.setParamStandardCodeGroupCodesystem(codeSystemArr[0]);
										newStandardCodes.add(newStandardCodesTemp);
									}
								}
								paramStandardCodeRepository.delete(paramStandardCodeRepository.findAll(FlowsheetSpecification.paramStandardCodeGroupId(groupId)));
								paramStandardCodeRepository.save(newStandardCodes);
							}
							message="Saved successfully.";
						}
					}else{
						message="No data to save.";
					}
				}else{
					message="No data to save.";
				}
			}else{
				message="No data to save.";
			}
		}catch(Exception e){
			message="Saving failed.";
		}
		flowsheetConfiguredDetails=getFlowsheetsStandardGroupDetails(isStandardLab);
		flowsheetConfiguredDetails.setMessageAfterSave(message);
		flowsheetConfiguredDetails.setCurrentGroupIdAfterSave(groupId);
		flowsheetConfiguredDetails.setCurrentGroupNameAfterSave(groupName);
		return flowsheetConfiguredDetails;
	}
	private List<LabStandardCode> DeletelabStandardCodeGroupId(Integer groupId) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb1=em.getCriteriaBuilder();
		CriteriaQuery<LabStandardCode> cq1 = cb1.createQuery(LabStandardCode.class);
		List<LabStandardCode> stndcode= new ArrayList<LabStandardCode>();
		Root<LabStandardCode> root1 = cq1.from(LabStandardCode.class);
		@SuppressWarnings("rawtypes")
		Selection[] selectionsforVR=new Selection[]{
			root1.get(LabStandardCode_.labStandardCodeGroupId),
			root1.get(LabStandardCode_.labStandardCodeGroupCode),
			root1.get(LabStandardCode_.labStandardCodeGroupGwid),
			root1.get(LabStandardCode_.labStandardCodeGroupCodesystem),

		};
		cq1.select(cb1.construct(LabStandardCode.class,selectionsforVR));
		Predicate[] restrictionsforVR=new Predicate[]{
				cb1.and(root1.get(LabStandardCode_.labStandardCodeGroupId).in(groupId)),
		};
		cq1.where (restrictionsforVR);
		try{
			stndcode= em.createQuery(cq1).getResultList();	

		}catch(Exception e){
			e.printStackTrace();
		}
		return stndcode;
	}

	public List<LabStandardGroup> orderByLabGroupName(List<LabStandardGroup> confData){
		if (confData.size() > 0) {
			Collections.sort(confData, new Comparator<LabStandardGroup>() {
				@Override
				public int compare(final LabStandardGroup object1, final LabStandardGroup object2) {
					return object1.getLabStandardGroupName().toString().compareToIgnoreCase(object2.getLabStandardGroupName().toString());
				}
			} );
		}
		return confData;
	}
	public List<ParamStandardGroup> orderByParamGroupName(List<ParamStandardGroup> confData){
		if (confData.size() > 0) {
			Collections.sort(confData, new Comparator<ParamStandardGroup>() {
				@Override
				public int compare(final ParamStandardGroup object1, final ParamStandardGroup object2) {
					return object1.getParamStandardGroupName().toString().compareToIgnoreCase(object2.getParamStandardGroupName().toString());
				}
			} );
		}
		return confData;
	}

	@Override
	public FS_Management saveFlowsheetsManagmentDetails(Integer flowsheetId,Integer flowsheetType,String flowsheetName,String dataToSave) throws Exception {
		if(flowsheetId == 0){
			Flowsheet flowsheet=new Flowsheet();
			flowsheet.setFlowsheetName(flowsheetName);
			flowsheet.setFlowsheetType(flowsheetType);
			flowsheet.setFlowsheetIsactive(true);
			Flowsheet flowsheetNew=flowsheetRepository.save(flowsheet);
			flowsheetId = flowsheetNew.getFlowsheetId();
			flowsheetType=flowsheetNew.getFlowsheetType();
		}else{
			Flowsheet flowsheet=flowsheetRepository.findOne(FlowsheetSpecification.flowsheetId(flowsheetId));
			flowsheetType=flowsheet.getFlowsheetType();
		}
		if(!dataToSave.equals("-1")){
			String Records[] = dataToSave.split("@##@");
			if(!Records[0].trim().equalsIgnoreCase("-1"))
				saveinvestigation(Records[0],flowsheetId);
			else
				makeEmptyFlowsheet(1,flowsheetId);
			if(!Records[1].trim().equalsIgnoreCase("-1"))
				savedrugs(Records[1],flowsheetId);
			else
				makeEmptyFlowsheet(2,flowsheetId);
			if(!Records[2].trim().equalsIgnoreCase("-1"))
				saveparams(Records[2],flowsheetId);
			else
				makeEmptyFlowsheet(3,flowsheetId);
			if(!Records[3].trim().equalsIgnoreCase("-1"))
				savedx(Records[3],flowsheetId);
			else
				makeEmptyFlowsheet(4,flowsheetId);
			if(!Records[4].trim().equalsIgnoreCase("-1"))
				saveClinicalData(Records[4],flowsheetId,FlowsheetServiceImpl.LOAD_VITALS_DATA);
			else
				makeEmptyFlowsheet(5,flowsheetId);
			if(!Records[5].trim().equalsIgnoreCase("-1"))
				saveClinicalData(Records[5],flowsheetId,FlowsheetServiceImpl.LOAD_CLINICAL_ELEMENTS_DATA);
			else
				makeEmptyFlowsheet(6,flowsheetId);
		}
		FS_Management managementDetailsAfterSave=getFlowsheetsManagementDetails(-1, flowsheetId, flowsheetType);
		return managementDetailsAfterSave;
	}

	public void saveinvestigation(String labRecords,Integer flowsheetId) throws Exception{
		String[] testids = labRecords.split("!@@!");
		flowsheetLabRepository.delete(flowsheetLabRepository.findAll(FlowsheetSpecification.flowsheetLabMapId(flowsheetId)));
		for(int i=0;i<testids.length;i++){
			String[] emptyGroupIds=testids[i].split("~kk~");
			Integer groupId=-1;
			if(emptyGroupIds[0].contentEquals("-1")){
				LabDescription labDescription=labDescriptionRepository.findOne(InvestigationSpecification.labByTestId(Integer.parseInt(Optional.fromNullable(emptyGroupIds[1]).or("-1"))));
				LabStandardGroup labStandardGroup=new LabStandardGroup();
				labStandardGroup.setLabStandardGroupName(labDescription.getLabDescriptionTestDesc());
				labStandardGroup.setLabStandardGroupIsactive(true);
				labStandardGroup.setLabStandardGroupGender(5);
				LabStandardGroup labStandardGroupNew=labStandardGroupRepository.save(labStandardGroup);
				groupId=labStandardGroupNew.getLabStandardGroupId();
				if(groupId!=-1){
					LabStandardCode labStandardCode=new LabStandardCode();
					labStandardCode.setLabStandardCodeGroupId(groupId);
					labStandardCode.setLabStandardCodeGroupGwid(Integer.parseInt(Optional.fromNullable(emptyGroupIds[1]).or("-1")));
					labStandardCode.setLabStandardCodeGroupCodesystem(FlowsheetServiceImpl.GlenwoodSystemsOID);
					labStandardCodeRepository.save(labStandardCode);
				}
			}else{
				groupId=Integer.parseInt(Optional.fromNullable(testids[i]).or("-1"));
			}
			if(groupId!=-1){
				FlowsheetLab flowsheetlab=new FlowsheetLab();
				flowsheetlab.setFlowsheetLabMapid(flowsheetId);
				flowsheetlab.setFlowsheetLabStandardGroupid(groupId);
				flowsheetLabRepository.save(flowsheetlab);
			}
		}	
	}

	public void savedrugs(String drugRecords,Integer flowsheetId)  throws Exception{
		String[] drugs = drugRecords.split("!@@!");
		flowsheetDrugRepository.delete(flowsheetDrugRepository.findAll(FlowsheetSpecification.flowsheetDrugsMapId(flowsheetId)));
		for(int i=0;i<drugs.length;i++){
			String[] drugpairs = drugs[i].split("~kk~");
			FlowsheetDrug flowsheetDrug=new FlowsheetDrug();
			flowsheetDrug.setFlowsheetDrugClassId(drugpairs[0]);
			flowsheetDrug.setFlowsheetDrugClassName(drugpairs[1]);
			flowsheetDrug.setFlowsheetDrugMapid(flowsheetId);
			flowsheetDrugRepository.save(flowsheetDrug);
		}
	}

	public void saveparams(String paramRecords,Integer flowsheetId) throws Exception{
		String[] paramids = paramRecords.split("!@@!");
		flowsheetParamRepository.delete(flowsheetParamRepository.findAll(FlowsheetSpecification.flowsheetParamMapId(flowsheetId)));
		for(int i=0;i<paramids.length;i++){
			String[] emptyGroupIds=paramids[i].split("~kk~");
			Integer groupId=-1;
			if(emptyGroupIds[0].contentEquals("-1")){
				LabParameters labParameters=labParametersRepository.findOne(InvestigationSpecification.getParamDetails(Integer.parseInt(Optional.fromNullable(emptyGroupIds[1]).or("-1"))));
				ParamStandardGroup paramStandardGroup=new ParamStandardGroup();
				paramStandardGroup.setParamStandardGroupName(labParameters.getLabParametersDisplayname());
				paramStandardGroup.setParamStandardGroupIsactive(true);
				paramStandardGroup.setParamStandardGroupGender(5);
				ParamStandardGroup paramStandardGroupNew=paramStandardGroupRepository.save(paramStandardGroup);
				groupId=paramStandardGroupNew.getParamStandardGroupId();
				if(groupId!=-1){
					ParamStandardCode paramStandardCode=new ParamStandardCode();
					paramStandardCode.setParamStandardCodeGroupId(groupId);
					paramStandardCode.setParamStandardCodeGroupGwid(Integer.parseInt(Optional.fromNullable(emptyGroupIds[1]).or("-1")));
					paramStandardCode.setParamStandardCodeGroupCodesystem(FlowsheetServiceImpl.GlenwoodSystemsOID);
					paramStandardCodeRepository.save(paramStandardCode);
				}
			}else{
				groupId=Integer.parseInt(Optional.fromNullable(paramids[i]).or("-1"));
			}
			if(groupId!=-1){
				FlowsheetParam flowsheetParam=new FlowsheetParam();
				flowsheetParam.setFlowsheetParamMapid(flowsheetId);
				flowsheetParam.setFlowsheetParamStandardGroupid(groupId);
				flowsheetParamRepository.save(flowsheetParam);
			}
		}			
	}

	public void savedx(String paramRecords,Integer flowsheetId) throws Exception{
		String[] tempdx = paramRecords.split(",");
		flowsheetDxRepository.delete(flowsheetDxRepository.findAll(FlowsheetSpecification.flowsheetDxMapid(flowsheetId)));
		for(int i=0;i<tempdx.length;i++){
			FlowsheetDx flowsheetDx=new FlowsheetDx();
			flowsheetDx.setFlowsheetDxMapid(flowsheetId);
			flowsheetDx.setFlowsheetDxCode(tempdx[i]);
			flowsheetDxRepository.save(flowsheetDx);
		}
	}
	public void saveClinicalData(String paramRecords,Integer flowsheetId,Integer type) throws Exception{	
		String[] tempVitals = paramRecords.split("!@@!");
		flowsheetClinicalParamRepository.delete(flowsheetClinicalParamRepository.findAll(FlowsheetSpecification.flowsheetClinicalParam(type, flowsheetId)));
		for(int i=0;i<tempVitals.length;i++){
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Integer> cq = builder.createQuery(Integer.class);
			Root<FlowsheetClinicalParam> root=cq.from(FlowsheetClinicalParam.class);
			@SuppressWarnings("rawtypes")
			Selection[] multi= new Selection[] { 
				builder.coalesce(builder.max(root.get(FlowsheetClinicalParam_.flowsheetClinicalParamId)),0)
			};
			cq.multiselect(multi);
			Integer flowsheetClinicalParamMax= em.createQuery(cq).getSingleResult();
			FlowsheetClinicalParam flowsheetClinicalParam=new FlowsheetClinicalParam();
			flowsheetClinicalParam.setFlowsheetClinicalParamId(flowsheetClinicalParamMax+1);
			flowsheetClinicalParam.setFlowsheetClinicalParamMapid(flowsheetId);
			flowsheetClinicalParam.setFlowsheetClinicalParamMapElementgwid(tempVitals[i]);
			flowsheetClinicalParam.setFlowsheetClinicalParamMapElementtype(type);
			flowsheetClinicalParamRepository.save(flowsheetClinicalParam);
		}
	}

	public void makeEmptyFlowsheet(Integer val,Integer flowsheetId) throws Exception{
		switch (val) {
		case 1:
			flowsheetLabRepository.delete(flowsheetLabRepository.findAll(FlowsheetSpecification.flowsheetLabMapId(flowsheetId)));
			break;
		case 2:
			flowsheetDrugRepository.delete(flowsheetDrugRepository.findAll(FlowsheetSpecification.flowsheetDrugsMapId(flowsheetId)));
			break;
		case 3:
			flowsheetParamRepository.delete(flowsheetParamRepository.findAll(FlowsheetSpecification.flowsheetParamMapId(flowsheetId)));
			break;
		case 4:
			flowsheetDxRepository.delete(flowsheetDxRepository.findAll(FlowsheetSpecification.flowsheetDxMapid(flowsheetId)));
			break;
		case 5:
			flowsheetClinicalParamRepository.delete(flowsheetClinicalParamRepository.findAll(FlowsheetSpecification.flowsheetClinicalParam(FlowsheetServiceImpl.LOAD_VITALS_DATA, flowsheetId)));
			break;
		case 6:
			flowsheetClinicalParamRepository.delete(flowsheetClinicalParamRepository.findAll(FlowsheetSpecification.flowsheetClinicalParam(FlowsheetServiceImpl.LOAD_CLINICAL_ELEMENTS_DATA, flowsheetId)));
			break;
		default:
			break;
		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	public HmrRuleBean getFlowSheetRuleQuery(String groupId,Integer flowsheetElementType) throws Exception{
		HmrRuleBean hmrRuleBeanTemp=new HmrRuleBean();
		List<String> groupIds=new ArrayList<String>();
		groupIds.add(groupId);
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<HmrBean> cq = builder.createQuery(HmrBean.class);
		Root<HmrTests> root = cq.from(HmrTests.class);
		Join<HmrTests, HmrCategories> hmr=root.join("hmrTestsTable",JoinType.LEFT);
		Join<HmrTests, HmrTestDetail> hmr1=root.join("hmrTestDetailTable",JoinType.LEFT);
		Join<HmrTests, EmployeeProfile> hmr2=root.join("empProfileHmrTestsModifiedByTable",JoinType.LEFT);
		Join<HmrTests, EmployeeProfile> hmr3=root.join("empProfileHmrTestsCreatedByTable",JoinType.LEFT);
		Join<Join<HmrTests, HmrCategories>, HmrGroups> hmr4=hmr.join("hmrGroupsTable",JoinType.LEFT);
		Selection[] selections= new Selection[] { 
				hmr.get( HmrCategories_.hmrCategoriesId ),
				hmr.get( HmrCategories_.hmrCategoriesDescrip ),
				root.get( HmrTests_.hmrTestsId ),
				root.get( HmrTests_.hmrTestsDescription ),
				root.get( HmrTests_.hmrTestsGender ),
				root.get( HmrTests_.hmrTestsCpt ),
				root.get( HmrTests_.hmrTestsDxs ),
				hmr1.get(HmrTestDetail_.hmrTestDetailId),
				builder.coalesce(hmr1.get(HmrTestDetail_.hmrTestDetailFrom),0),
				builder.coalesce(hmr1.get(HmrTestDetail_.hmrTestDetailTo),0),
				hmr1.get(HmrTestDetail_.hmrTestDetailDurationType),
				hmr1.get(HmrTestDetail_.hmrTestDetailSchedule),
				hmr1.get(HmrTestDetail_.hmrTestDetailScheduleType),
				root.get( HmrTests_.hmrTestsDxDesc ),
				root.get( HmrTests_.hmrTestsIsActive ),
				root.get( HmrTests_.hmrTestsIsDxBased ),
				root.get( HmrTests_.hmrTestsComments ),
				root.get( HmrTests_.hmrTestsMaxAge ),
				hmr2.get(EmployeeProfile_.empProfileFullname),
				builder.function("to_char", String.class, root.get( HmrTests_.hmrTestsModifiedOn),builder.literal("MM/DD/YYYY")),
				hmr4.get(HmrGroups_.hmrGroupName),
				hmr4.get(HmrGroups_.hmrGroupId),
				hmr1.get(HmrTestDetail_.hmrTestDetailSortBy),
				hmr3.get(EmployeeProfile_.empProfileFullname),
				builder.function("to_char", String.class, root.get( HmrTests_.hmrTestsCreatedOn ),builder.literal("MM/DD/YYYY")),
				hmr1.get(HmrTestDetail_.hmrTestDetailIsActive),
				hmr1.get(HmrTestDetail_.hmrTestDetailDescription),
		};
		cq.select(builder.construct(HmrBean.class,selections));
		cq.distinct(true);
		Predicate[] restrictions = new Predicate[] {
				root.get( HmrTests_.hmrTestsFlowsheetElementId ).in(groupIds),
				builder.equal(root.get( HmrTests_.hmrTestsFlowsheetElementType),flowsheetElementType),
				builder.or(builder.notEqual(hmr1.get(HmrTestDetail_.hmrTestDetailIsPreferredAge),1), builder.isNull(hmr1.get(HmrTestDetail_.hmrTestDetailIsPreferredAge))),
		};
		cq.where(restrictions);
		List<HmrBean> confData=new ArrayList<HmrBean>();
		try{
			confData=em.createQuery(cq).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		//Order by sortby
		confData=orderBySortBy(confData);
		hmrRuleBeanTemp.setHmrData(confData);

		//Query to get the reference url's
		CriteriaBuilder builderReferences = em.getCriteriaBuilder();
		CriteriaQuery<HmrBean> cqReferences = builderReferences.createQuery(HmrBean.class);
		Root<HmrTests> rootReferences = cqReferences.from(HmrTests.class);
		Join<HmrTests, HmrCategories> hmrReferences=rootReferences.join("hmrTestsTable",JoinType.LEFT);
		Join<HmrTests, HmrCategoryUrl> hmrReferences1=rootReferences.join("hmrCategoryUrl",JoinType.LEFT);
		Join<HmrTests, HmrTestDetail> hmrReferences2=rootReferences.join("hmrTestDetailTable",JoinType.LEFT);
		Selection[] selectionsReferences= new Selection[] { 
				hmrReferences1.get( HmrCategoryUrl_.hmrCategoryUrlName ),
		};
		cqReferences.select(builderReferences.construct(HmrBean.class,selectionsReferences));
		cqReferences.distinct(true);
		Predicate[] restrictionsReferences = new Predicate[] {
				rootReferences.get( HmrTests_.hmrTestsFlowsheetElementId ).in(groupIds),
				builderReferences.equal(rootReferences.get( HmrTests_.hmrTestsFlowsheetElementType),flowsheetElementType),
				builderReferences.or(builderReferences.notEqual(hmrReferences2.get(HmrTestDetail_.hmrTestDetailIsPreferredAge),1), builderReferences.isNull(hmrReferences2.get(HmrTestDetail_.hmrTestDetailIsPreferredAge))),
		};
		cqReferences.where(restrictionsReferences);
		List<HmrBean> referencesData=new ArrayList<HmrBean>();
		try{
			referencesData=em.createQuery(cqReferences).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		//Order by references
		referencesData=orderByReferences(referencesData);
		hmrRuleBeanTemp.setHmrReferences(referencesData);

		//Query to get the cpt's
		CriteriaBuilder builderCpt = em.getCriteriaBuilder();
		CriteriaQuery<HmrBean> cqCpt = builderCpt.createQuery(HmrBean.class);
		Root<HmrTests> rootCpt = cqCpt.from(HmrTests.class);
		Join<HmrTests, HmrCategories> hmrCpt=rootCpt.join("hmrTestsTable",JoinType.LEFT);
		Join<HmrTests, HmrCategoryUrl> hmrCpt1=rootCpt.join("hmrCategoryUrl",JoinType.LEFT);
		Join<HmrTests, HmrTestDetail> hmrCpt2=rootCpt.join("hmrTestDetailTable",JoinType.LEFT);
		Selection[] selectionsCpt= new Selection[] { 
				rootCpt.get( HmrTests_.hmrTestsCpt ),
		};
		cqCpt.select(builderCpt.construct(HmrBean.class,selectionsCpt));
		cqCpt.distinct(true);
		Predicate[] restrictionsCpt = new Predicate[] {
				rootCpt.get( HmrTests_.hmrTestsFlowsheetElementId ).in(groupIds),
				builderCpt.equal(rootCpt.get( HmrTests_.hmrTestsFlowsheetElementType),flowsheetElementType),
				builderCpt.or(builderCpt.notEqual(hmrCpt2.get(HmrTestDetail_.hmrTestDetailIsPreferredAge),1), builderCpt.isNull(hmrCpt2.get(HmrTestDetail_.hmrTestDetailIsPreferredAge))),
		};
		cqCpt.where(restrictionsCpt);
		List<HmrBean> cptData=new ArrayList<HmrBean>();
		try{
			cptData=em.createQuery(cqCpt).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		//Order by Cpt
		cptData=orderByReferences(cptData);
		//Assigning from references to cpt becuase the HmrBean assigns to references variable
		for(int j=0;j<cptData.size();j++){
			cptData.get(j).setCpt(cptData.get(j).getReferences());
		}
		hmrRuleBeanTemp.setHmrCpt(cptData);
		return hmrRuleBeanTemp;
	}

	@Override
	public List<FlowsheetBean> getFlowsheetDataList(Integer flowsheetType,String dxCode,Integer patientId,Integer encounterId,Integer chartId) throws Exception {
				ArrayList<FlowsheetBean> flowsheets= new ArrayList<FlowsheetBean> (); 
		if(flowsheetType!=-1){
			List<Flowsheet> flowsheetIds = flowsheetRepository.findAll(Specifications.where(FlowsheetSpecification.flowsheetType(flowsheetType)).and(FlowsheetSpecification.flowsheetIsactive(true)));
			int flowsheetId;
			for (int i=0; i < flowsheetIds.size(); i++){
				flowsheetId = Optional.fromNullable(flowsheetIds.get(i).getFlowsheetId()).or(-1);
				flowsheets.add(getFlowsheetData(flowsheetType,flowsheetId, patientId, encounterId,chartId));
			}
		}else{
			List<FlowsheetDx> flowsheetIds = flowsheetDxRepository.findAll(Specifications.where(FlowsheetSpecification.flowsheetDxCode(dxCode)));
			int flowsheetId;
			for (int i=0; i < flowsheetIds.size(); i++){
				flowsheetId = Optional.fromNullable(flowsheetIds.get(i).getFlowsheetDxMapid()).or(-1);
				flowsheets.add(getFlowsheetData(flowsheetType,flowsheetId, patientId, encounterId,chartId));
			}
		}
		return flowsheets;
	}

	@Override
	public List<FlowsheetBean> getFlowsheetDataListOverdue(Integer patientId,Integer encounterId,Integer chartId) throws Exception {
		ArrayList<FlowsheetBean> flowsheets= new ArrayList<FlowsheetBean> (); 
		List<FlowsheetNameBean> flowsheetIds = getFlowsheetNames(patientId);;
		int flowsheetId;
		for (int i=0; i < flowsheetIds.size(); i++){
			flowsheetId = Optional.fromNullable(flowsheetIds.get(i).getFlowsheetId()).or(-1);
			flowsheets.add(getFlowsheetData(-1,flowsheetId, patientId, encounterId,chartId));
		}
		return flowsheets;
	}

	@Override
	public List<OverdueAlert> overdueLabs(Integer patientId, Integer chartId, Integer encounterId) throws Exception{
		List<FlowsheetBean> overdueData= new ArrayList<FlowsheetBean>();
		overdueData = 	getFlowsheetDataListOverdue(patientId, encounterId,chartId);
		List<OverdueAlert>  overduecollection = new ArrayList<OverdueAlert>();
		for(FlowsheetBean groupone : overdueData){

			for(FS_LabBean grouponelabdata : groupone.getLabData()){
				collectOverDueData(grouponelabdata.getLabName(),grouponelabdata.getLabTestId(),grouponelabdata.getDue(),"3",groupone.getFlowsheetType(),groupone.getFlowsheetId(),patientId,overduecollection);
			}
			for(FS_ClinicalElementBean grouponelabdata : groupone.getClinicalPlanData()){
				collectOverDueData(grouponelabdata.getClinicalElementName(),grouponelabdata.getClinicalElementId(),grouponelabdata.getDue(),"4",groupone.getFlowsheetType(),groupone.getFlowsheetId(),patientId,overduecollection);
			}

			for(FS_DrugBean grouponelabdata : groupone.getDrugData()){
				collectOverDueData(grouponelabdata.getGenericDrugName(),grouponelabdata.getGenericDrugId(),grouponelabdata.getDue(),"5",groupone.getFlowsheetType(),groupone.getFlowsheetId(),patientId,overduecollection);
			}
			/*ArrayList<FS_LabParameterBean> paramGroupMap = groupone.getParamData();
			if(!paramGroupMap.containsKey("")){
				ArrayList parameterdata = new ArrayList();
				Iterator<String> groupName = paramGroupMap.keySet().iterator();
				while(groupName.hasNext()){
					String paramNameKey = groupName.next();
					HashMap<String, HashMap<String, FS_LabParameterBean>> paramObject = paramGroupMap.get(paramNameKey);
					Iterator<String> obj = paramObject.keySet().iterator();
					while(obj.hasNext()){
						String objKey = obj.next();
						HashMap<String, FS_LabParameterBean> paramDateObject = paramObject.get(objKey);
						String originalDateKey = HUtil.Nz(sortparameter(groupone.getParamDate(),paramDateObject),"");
						if(!originalDateKey.equalsIgnoreCase("") && !originalDateKey.equalsIgnoreCase("null") && !originalDateKey.equalsIgnoreCase(null) ){
							FS_LabParameterBean flowSheetDataObj = paramDateObject.get(originalDateKey);
							parameterdata.add(flowSheetDataObj);
						}
					}
				}
				for(int i=0;i<parameterdata.size();i++){
					FS_LabParameterBean paramvalue =(FS_LabParameterBean) parameterdata.get(i);
					collectOverDueData(paramvalue.getParamName(),paramvalue.getParamId(),paramvalue.getDue(),"2",groupone.getFlowsheetType(),groupone.getFlowsheetId(),patientId,overduecollection);
				}
			}*/

			ArrayList<FS_LabParameterBean> parameterdata = groupone.getParamData();
			for(int i=0;i<parameterdata.size();i++){
				FS_LabParameterBean paramvalue =(FS_LabParameterBean) parameterdata.get(i);
				collectOverDueData(paramvalue.getParamName(),paramvalue.getParamId(),paramvalue.getDue(),"2",groupone.getFlowsheetType(),groupone.getFlowsheetId(),patientId,overduecollection);
			}
			/*ArrayList<FS_ClinicalElementBean> vitalData = groupone.getClinicalVitalsData();
			if(!vitalData.containsKey("")){
				ArrayList vitalcollectdata = new ArrayList();
				Iterator<String> vitalDate = vitalData.keySet().iterator();
				while(vitalDate.hasNext()){
					String vitalDateKey = vitalDate.next();
					HashMap<String, FS_ClinicalElementBean> vitalDateObject = vitalData.get(vitalDateKey);
					FS_ClinicalElementBean flowSheetDataObj = vitalDateObject.get(sortVital(groupone.getVitalDateArr(),vitalDateObject) );
					vitalcollectdata.add(flowSheetDataObj);
				}
				for(int i=0;i<vitalcollectdata.size();i++){
					FS_ClinicalElementBean vitalvalue =(FS_ClinicalElementBean) vitalcollectdata.get(i);
					collectOverDueData(vitalvalue.getClinicalElementName(),vitalvalue.getClinicalElementId(),vitalvalue.getDue(),"1",groupone.getFlowsheetType(),groupone.getFlowsheetId(),patientId,overduecollection);
				}
			}*/
			ArrayList<FS_ClinicalElementBean> vitalcollectdata = groupone.getClinicalVitalsData();
			for(int i=0;i<vitalcollectdata.size();i++){
				FS_ClinicalElementBean vitalvalue =(FS_ClinicalElementBean) vitalcollectdata.get(i);
				collectOverDueData(vitalvalue.getClinicalElementName(),vitalvalue.getClinicalElementId(),vitalvalue.getDue(),"1",groupone.getFlowsheetType(),groupone.getFlowsheetId(),patientId,overduecollection);
			}
		}
		return overduecollection;
	}

	public List<OverdueAlert> collectOverDueData(String name,String id,String due,String type,int flowsheetType,int flowsheetId, int patientId,List<OverdueAlert> overduecollection) throws Exception{
		OverdueAlert collectdata = new OverdueAlert();
		if( HUtil.Nz(due,"").equalsIgnoreCase("Due now") || HUtil.Nz(due,"").equalsIgnoreCase("over due") ){
			collectdata.setName(name);
			collectdata.setId(id);
			collectdata.setDue(due);
			collectdata.setType(type);
			collectdata.setFlowsheetId(flowsheetId);
			collectdata.setFlowsheetType(flowsheetType);
			collectdata.setAgePeriod(ageFormation(patientId,id,type,flowsheetId));
			overduecollection.add(collectdata);
		}
		return overduecollection ;
	}

	public List<OverdueAlert> collectOverDueData(String name,String id,String due,String testDetailId,String type,Integer flowsheetType,Integer flowsheetId, Integer patientId,List<OverdueAlert> overduecollection) throws Exception{
		OverdueAlert collectdata = new OverdueAlert();
		if( HUtil.Nz(due,"").equalsIgnoreCase("Due now") || HUtil.Nz(due,"").equalsIgnoreCase("over due") ){
			collectdata.setName(name);
			collectdata.setId(id);
			collectdata.setDue(due);
			collectdata.setType(type);
			collectdata.setTestDetailId(testDetailId);
			collectdata.setFlowsheetId(flowsheetId);
			collectdata.setFlowsheetType(flowsheetType);
			collectdata.setAgePeriod(ageFormation(patientId,id,type,flowsheetId));
			overduecollection.add(collectdata);
		}
		return overduecollection ;
	}

	public String ageFormation(Integer patientId,String id, String flowsheetElementType,Integer flowsheetId) throws Exception{
		String agePeriod = "";
		List<HmrBean> beanList=getFlowSheetPatientAlert_Details(patientId,id,flowsheetElementType,flowsheetId);
		String ageformingValues="";
		if(beanList.size()>0)
			ageformingValues = beanList.get(0).toString();
		if(!ageformingValues.equals("")){
			String removeBrackets = ageformingValues.replace("[", "").replace("]", "");
			String[] splitteddate ; 
			splitteddate = removeBrackets.split(",");
			if(splitteddate[2].trim().equals("0"))
				agePeriod = "("+splitteddate[0]+" - "+splitteddate[1]+")"+" days";
			else if(splitteddate[2].trim().equals("1"))
				agePeriod = "("+splitteddate[0]+" - "+splitteddate[1]+")"+" mnths";
			else
				agePeriod = "("+splitteddate[0]+" - "+splitteddate[1]+")"+" yrs";
		}
		return agePeriod ;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public List<HmrBean> getFlowSheetPatientAlert_Details(Integer patientId,String id, String flowsheetElementType,int flowsheetId) throws Exception  {
		//Query 1
		CriteriaBuilder builder1 = em.getCriteriaBuilder();
		CriteriaQuery<HmrBean> cq1 = builder1.createQuery(HmrBean.class);
		Root<HmrPatientinstanceparameters> root1 = cq1.from(HmrPatientinstanceparameters.class);
		Join<HmrPatientinstanceparameters, HmrCategories> hmrQ=root1.join("hmrCategoriesTable",JoinType.LEFT);
		Join<HmrPatientinstanceparameters, HmrPatientinstance> hmrQ1=root1.join("hmrPatientinstanceTable",JoinType.LEFT);
		Join<HmrPatientinstanceparameters, EmployeeProfile> hmrQ2=root1.join("empProfileHmrTestsModifiedByTable",JoinType.LEFT);
		Join<HmrPatientinstanceparameters, EmployeeProfile> hmrQ3=root1.join("empProfileHmrTestsCreatedByTable",JoinType.LEFT);
		Join<Join<HmrPatientinstanceparameters, HmrCategories>, HmrGroups> hmrQ4=hmrQ.join("hmrGroupsTable",JoinType.LEFT);
		Selection[] selections1= new Selection[] {
				builder1.coalesce(hmrQ1.get(HmrPatientinstance_.hmrPatientinstanceFromDuration),0),
				builder1.coalesce(hmrQ1.get(HmrPatientinstance_.hmrPatientinstanceToDuration),0),
				hmrQ1.get(HmrPatientinstance_.hmrPatientinstanceDurationType)
		};
		cq1.select(builder1.construct(HmrBean.class,selections1));
		cq1.distinct(true);
		Predicate[] restrictions1 = new Predicate[] {
				builder1.equal(root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersFlowsheetElementId),id),
				builder1.equal(root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersFlowsheetElementType),flowsheetElementType),
				builder1.or(builder1.notEqual(hmrQ1.get(HmrPatientinstance_.hmrPatientinstancePreferredAge),1), builder1.isNull(hmrQ1.get(HmrPatientinstance_.hmrPatientinstancePreferredAge))),
				builder1.equal(root1.get( HmrPatientinstanceparameters_.hmrPatientinstanceparametersPatientid ),patientId)
		};
		cq1.where(restrictions1);
		List<HmrBean> confData1=new ArrayList<HmrBean>();
		try{
			confData1=em.createQuery(cq1).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(confData1!=null){
			if(confData1.size()>0){
				//Query 2
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<HmrBean> cq = builder.createQuery(HmrBean.class);
				Root<HmrTests> root = cq.from(HmrTests.class);
				Join<HmrTests, HmrCategories> hmr=root.join("hmrTestsTable",JoinType.LEFT);
				Join<HmrTests, HmrTestDetail> hmr1=root.join("hmrTestDetailTable",JoinType.LEFT);
				Join<HmrTests, EmployeeProfile> hmr2=root.join("empProfileHmrTestsModifiedByTable",JoinType.LEFT);
				Join<HmrTests, EmployeeProfile> hmr3=root.join("empProfileHmrTestsCreatedByTable",JoinType.LEFT);
				Join<Join<HmrTests, HmrCategories>, HmrGroups> hmr4=hmr.join("hmrGroupsTable",JoinType.LEFT);
				Selection[] selections= new Selection[] { 
						builder.coalesce(hmr1.get(HmrTestDetail_.hmrTestDetailFrom),0),
						builder.coalesce(hmr1.get(HmrTestDetail_.hmrTestDetailTo),0),
						hmr1.get(HmrTestDetail_.hmrTestDetailDurationType)
				};
				cq.select(builder.construct(HmrBean.class,selections));
				cq.distinct(true);
				Predicate[] restrictions = new Predicate[] {
						builder.equal(root.get(HmrTests_.hmrTestsFlowsheetElementId ),id),
						builder.equal(root.get( HmrTests_.hmrTestsFlowsheetElementType),flowsheetElementType),
						builder.or(builder.notEqual(hmr1.get(HmrTestDetail_.hmrTestDetailIsPreferredAge),1), builder.isNull(hmr1.get(HmrTestDetail_.hmrTestDetailIsPreferredAge))),
				};
				cq.where(restrictions);
				List<HmrBean> confData=new ArrayList<HmrBean>();
				try{
					confData=em.createQuery(cq).getResultList();
				}catch(Exception e){
					e.printStackTrace();
				}
				return confData;
			}else{
				return confData1;
			}
		}else{
			return confData1;
		}
	}

	public FlowsheetBean getFlowsheetDataSOAP(Integer flowsheetTypeParam,Integer flowsheetId, Integer patientId,Integer encounterId,Integer chartId) throws Exception{
		//logger.debug("Request to get the flowsheet details for labs and params section for a particular flowsheet for a patient.:: inside service Impl");
		FlowsheetBean fBean=new FlowsheetBean();
	//	Integer chartId=-1;
		Integer count=0;
		if(encounterId!=-1)
			fBean.setChartBased(false);
		else		{
		//	chartId=getChartId(patientId);
			
			if(chartId!=-1){
				count=getCount(chartId);
				if(count>0){
				encounterId=getlatestEncounterId(chartId);
				}else{
					encounterId=-1;
				}
			}
		
			fBean.setChartBased(true);
		}
		if(flowsheetId!=-1){
			fBean.setFlowsheetId(flowsheetId);
			Flowsheet flowsheet=flowsheetRepository.findOne(Specifications.where(FlowsheetSpecification.flowsheetId(flowsheetId)).and(FlowsheetSpecification.flowsheetIsactive(true)));
			int flowsheetType=Optional.fromNullable(flowsheet.getFlowsheetType()).or(-1);
			fBean.setFlowsheetType(flowsheetType);
			fBean.setFlowsheetName(Optional.fromNullable(Strings.emptyToNull(flowsheet.getFlowsheetName())).or("").toString());
			List<FlowsheetDx> flowhseetDx=new ArrayList<FlowsheetDx>();
			flowhseetDx=flowsheetDxRepository.findAll(Specifications.where(FlowsheetSpecification.flowsheetDxMapid(flowsheetId)));
			String[] flowsheetIds=new String[flowhseetDx.size()];
			for(int i=0;i<flowhseetDx.size();i++){
				flowsheetIds[i]=flowhseetDx.get(i).getFlowsheetDxCode();
			}
			if(flowsheetIds.length==0){
				flowsheetIds=new String[1];
				flowsheetIds[0]="-2";
			}
			String startDate = "";
			int patgender=getPatientGender(patientId);
			if((flowsheetType==1)|| (flowsheetType==5)){
				//Preventive Management
				//PatientRegistration patientReg=patientRegistrationRepository.findOne(Specifications.where(PatientRegistrationSpecification.PatientId(patientId+"")));
				startDate = getpatientDOb(patientId).toString();
			} else if(flowsheetType==2){
				//Disease Management
				List<FlowsheetDx> flowsheetDxList=flowsheetDxRepository.findAll(Specifications.where(FlowsheetSpecification.flowsheetDxCodeBasedOnFlowsheetTypeId(2, flowsheetType)));
				ArrayList<String> dxCodes=new ArrayList<String>();
				for(int j=0;j<flowsheetDxList.size();j++){
					dxCodes.add(flowsheetDxList.get(j).getFlowsheetDxCode());
				}
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<Object> cq = builder.createQuery();
				Root<ProblemList> root = cq.from(ProblemList.class);
				Predicate[] restrictions1 = new Predicate[] {
						root.get(ProblemList_.problemListDxCode).in(dxCodes),
						builder.equal(root.get(ProblemList_.problemListPatientId),patientId)
				};
				cq.select(builder.selectCase().when(builder.isNull(root.get(ProblemList_.problemListOnsetDate)), builder.function("to_char", String.class, root.get(ProblemList_.problemListCreatedon),builder.literal("yyyy-MM-dd"))).otherwise(builder.function("to_char", String.class, root.get(ProblemList_.problemListOnsetDate), builder.literal("yyyy-MM-dd"))));
				cq.where(restrictions1);
				try{
					List<Object> rstList=em.createQuery(cq).getResultList();
					if(rstList.size()>0)
						startDate = Optional.fromNullable(rstList.get(0)).or("").toString();
				}catch(Exception e){
                    e.printStackTrace();
				}
			}else if(flowsheetType==3){
				//Meaningful Use Measures
				CriteriaBuilder builder = em.getCriteriaBuilder();
				CriteriaQuery<Object> cq = builder.createQuery();
				cq.select(builder.currentDate()).from(FlowsheetType.class);
				Object rstList=em.createQuery(cq).getSingleResult();
				if(rstList!=null)
					startDate = Optional.fromNullable(rstList).or("").toString();
			}
			fBean.setLabData(formLabData(flowsheetId, startDate, patientId,encounterId,patgender,chartId));
			fBean.setParamData(formLabParameters(flowsheetId, startDate, patientId,encounterId,patgender,chartId));
			fBean.setParamDateArr(paramPerformedDate);
			fBean.setClinicalVitalsData(formClinicalElementsData(flowsheetId, startDate, patientId,encounterId,patgender));
			fBean.setVitalDateArr(clinicalVitalParamDate);
			fBean.setClinicalPlanData(formClinicalData(flowsheetId, startDate, patientId,encounterId,patgender));
		}
		//logger.debug("Request to get the flowsheet details for labs and params section for a particular flowsheet for a patient.:: outside service Impl");
		return fBean;
	}

	private Integer getlatestEncounterId(Integer chartId) {
	Integer encId=-1;
	CriteriaBuilder cb=em.getCriteriaBuilder();
	CriteriaQuery<Object> cq=cb.createQuery();
	Root<Encounter> root=cq.from(Encounter.class);
	cq.select(root.get(Encounter_.encounterId));
	cq.where(cb.equal(root.get(Encounter_.encounterChartid),chartId));
	cq.orderBy(cb.desc((root.get(Encounter_.encounterId))));
	try{
		encId=(Integer) em.createQuery(cq).setMaxResults(1).getSingleResult();
	}catch(Exception e){
		e.printStackTrace();
		encId=-1;
	}
      return encId;
	}
}
