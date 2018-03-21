package com.glenwood.glaceemr.server.application.services.chart.growthgraph;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.GrowthGraph;
import com.glenwood.glaceemr.server.application.models.GrowthGraphPatientData;
import com.glenwood.glaceemr.server.application.models.GrowthGraphVitalData;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.repositories.EncounterEntityRepository;
import com.glenwood.glaceemr.server.application.repositories.GrowthGraphRepository;
import com.glenwood.glaceemr.server.application.repositories.InitialSettingsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.specifications.GrowthGraphSpecification;
import com.glenwood.glaceemr.server.application.specifications.InitialSettingsSpecification;
import com.glenwood.glaceemr.server.application.specifications.PatientClinicalElementsSpecification;
import com.glenwood.glaceemr.server.application.specifications.PatientRegistrationSpecification;
import com.glenwood.glaceemr.server.utils.DateUtil;

@Service
@Transactional
public class GrowthGraphServiceImpl implements GrowthGraphService{

	@Autowired
	PatientRegistrationRepository patientRegistrationRepository;

	@Autowired
	PatientClinicalElementsRepository patientClinicalElementsRepository;

	@Autowired
	InitialSettingsRepository initialSettingsRepository;

	@Autowired
	GrowthGraphRepository growthgraphRepository;

	@Autowired
	EncounterEntityRepository encounterEntityRepository;

	@Autowired
	EntityManagerFactory emf ;

	@PersistenceContext
	EntityManager em;


	/**
	 * To get default graph id based on patient id
	 * @param patientId
	 * @return default graph id 
	 */
	@Override
	public String getDefaultGraphId(String patientId) {

		Integer AgeInDays;
		PatientRegistration patientRegistration=patientRegistrationRepository.findOne(PatientRegistrationSpecification.getPatientPersonalDetails(Integer.parseInt(patientId)));
		AgeInDays		 = DateUtil.dateDiff( DateUtil.DATE , patientRegistration.getPatientRegistrationDob() , new Date()) ;
		String defaultGraphId="";
		if (AgeInDays <= 1095){
			if (patientRegistration.getPatientRegistrationSex()==1)
				defaultGraphId = "1"; // Wt-Age & Ht-Age for Boys 0-36 month
			else
				defaultGraphId = "2";	// Wt-Age & Ht-Age for Girls 0-36 month 
		}
		else{
			if (patientRegistration.getPatientRegistrationSex()==1)
				defaultGraphId = "5"; // Wt-Age & Ht-Age for Boys 2-20 Years
			else
				defaultGraphId = "6";	// Wt-Age & Ht-Age for Girls 2-20 Years
		}
		return defaultGraphId;
	}

	/**
	 * To get patient details based on patient id
	 * @param patientId
	 * @return patient details
	 */
	@Override
	public GrowthGraphPatientData getpatientinfo(String patientId) {

		PatientRegistration patientData=patientRegistrationRepository.findOne(
				PatientRegistrationSpecification.getPatientPersonalDetails(Integer.parseInt(patientId)));

		int AgeInYear = (int)(DateUtil.dateDiff( DateUtil.DATE , patientData.getPatientRegistrationDob() , new java.util.Date() )/365);

		int isNewGrowthGraph=Integer.parseInt(
				initialSettingsRepository.findOne(
						InitialSettingsSpecification.optionName("New Growth Graph")).
						getInitialSettingsOptionValue());

		GrowthGraphPatientData patientInfoBean=new GrowthGraphPatientData(
				patientData.getPatientRegistrationAccountno(),
				patientData.getPatientRegistrationFirstName(),
				patientData.getPatientRegistrationId(),
				patientData.getPatientRegistrationSex(),
				AgeInYear,isNewGrowthGraph);

		return patientInfoBean;

	}

	/**
	 * To get list of vital data's based on patient id
	 * @param patientId
	 * @return list of vital data's
	 */
	@Override
	public List<GrowthGraphVitalData> getVitalValues(String patientId, boolean wellvisit) {

		List<GrowthGraphVitalData> clinicalDataBeans=new ArrayList<GrowthGraphVitalData>();

		if(wellvisit){
			List<Encounter> encounterList = null;

			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> query = builder.createQuery();

			Root<Chart> root = query.from(Chart.class);
			query.select(root.get(Chart_.chartId));
			query.where(builder.equal(root.get(Chart_.chartPatientid),patientId));

			Query resultQry=em.createQuery(query);
			Object charIdObj=resultQry.getSingleResult();

			if(charIdObj != null){
				encounterList = encounterEntityRepository.findAll(GrowthGraphSpecification.getWellVisitDetails((int) charIdObj));
			}

			if(encounterList.size()>0 && encounterList != null){
				PatientRegistration patientRegistration=patientRegistrationRepository.findOne(PatientRegistrationSpecification.getPatientPersonalDetails(Integer.parseInt(patientId)));
				List<String> gwids=new ArrayList<String>(Arrays.asList("0000200200100023000","0000200200100024000","0000200200100039000"));//Gwdid of height,weight and head circumference
				List<PatientClinicalElements> patientDetails = null;
				List<Integer> encList = new ArrayList<Integer>();

				for(int i=0;i<encounterList.size();i++){
					int encouterId = encounterList.get(i).getEncounterId();

					if(!encList.contains(encouterId)){
						encList.add(encouterId);
						patientDetails = patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getClinicalElementByEncId(Integer.parseInt(patientId),encouterId,gwids));

						GrowthGraphVitalData dataBean=new GrowthGraphVitalData("", "", "", "-", 0, 0, 0);//Initializing the default values
						for(int j=0;j<patientDetails.size();j++){

							String gwdId=patientDetails.get(j).getPatientClinicalElementsGwid();
							String value=patientDetails.get(j).getPatientClinicalElementsValue();

							if(j==0){
								SimpleDateFormat form = new SimpleDateFormat("MM/dd/yyyy");

								Date encounterDate=patientDetails.get(j).getEncounter().getEncounterDate();
								String encounterDateStr=form.format(encounterDate);

								int ageInDays = ((DateUtil.dateDiff( DateUtil.DATE , patientRegistration.getPatientRegistrationDob() ,encounterDate)%366)%30) ;
								int ageInYear = (int)(DateUtil.dateDiff( DateUtil.DATE ,patientRegistration.getPatientRegistrationDob() ,encounterDate )/366);
								int ageInMonth = (int)((DateUtil.dateDiff( DateUtil.DATE ,patientRegistration.getPatientRegistrationDob() ,encounterDate)%366)/30);

								dataBean.setAgeInDay(ageInDays);
								dataBean.setAgeInMonth(ageInMonth);
								dataBean.setAgeInYear(ageInYear);
								dataBean.setEncounterDate(encounterDateStr);
							}

							if(gwdId.equalsIgnoreCase("0000200200100039000"))//0000200200100039000 is gwdId of Head Circumference
								dataBean.setHeadCircumference(value);
							if(gwdId.equalsIgnoreCase("0000200200100024000"))//0000200200100024000 is gwdId of weight
								dataBean.setWeight(value);
							if(gwdId.equalsIgnoreCase("0000200200100023000"))//0000200200100023000 is gwdId Height 
								dataBean.setHeight(""+Double.parseDouble(value)*0.393700787);

						}
						clinicalDataBeans.add(dataBean);
					}
				}
			}

		}else{

			PatientRegistration patientRegistration=patientRegistrationRepository.findOne(PatientRegistrationSpecification.getPatientPersonalDetails(Integer.parseInt(patientId)));

			List<String> gwids=new ArrayList<String>(Arrays.asList("0000200200100023000","0000200200100024000","0000200200100039000"));//Gwdid of height,weight and head circumference
			List<PatientClinicalElements> patientDetails=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinicalDataByGWDID(Integer.parseInt(patientId), gwids));

			String currentEncounterDateStr=null;
			Date currentEncounterDate=null;
			Date encounterDate=null;	
			int currentEncId;
			int previousEncId=-1;

			GrowthGraphVitalData dataBean = null;
			for(int i=0;i<patientDetails.size();i++){

				currentEncId = patientDetails.get(i).getEncounter().getEncounterId();
				SimpleDateFormat form = new SimpleDateFormat("MM/dd/yyyy");

				currentEncounterDate=patientDetails.get(i).getEncounter().getEncounterDate();
				currentEncounterDateStr=form.format(currentEncounterDate);

				String gwdId=patientDetails.get(i).getPatientClinicalElementsGwid();
				String value=patientDetails.get(i).getPatientClinicalElementsValue();

				if(currentEncId != previousEncId){
					dataBean=new GrowthGraphVitalData("", "", "", "-", 0, 0, 0);//Initializing the default values

					encounterDate=patientDetails.get(i).getEncounter().getEncounterDate();

					int ageInDays = ((DateUtil.dateDiff( DateUtil.DATE , patientRegistration.getPatientRegistrationDob() ,encounterDate)%366)%30) ;
					int ageInYear = (int)(DateUtil.dateDiff( DateUtil.DATE ,patientRegistration.getPatientRegistrationDob() ,encounterDate )/366);
					int ageInMonth = (int)((DateUtil.dateDiff( DateUtil.DATE ,patientRegistration.getPatientRegistrationDob() ,encounterDate)%366)/30);

					dataBean.setAgeInDay(ageInDays);
					dataBean.setAgeInMonth(ageInMonth);
					dataBean.setAgeInYear(ageInYear);
					dataBean.setEncounterDate(currentEncounterDateStr);

					if(gwdId.equalsIgnoreCase("0000200200100039000"))//0000200200100039000 is gwdId of Head Circumference
						dataBean.setHeadCircumference(value);
					if(gwdId.equalsIgnoreCase("0000200200100024000"))//0000200200100024000 is gwdId of weight
						dataBean.setWeight(value);
					if(gwdId.equalsIgnoreCase("0000200200100023000"))//0000200200100023000 is gwdId Height 
						dataBean.setHeight(""+Double.parseDouble(value)*0.393700787);

					if(i == (patientDetails.size()-1))
						clinicalDataBeans.add(dataBean);
					else{
						int nextEncId = patientDetails.get(i+1).getEncounter().getEncounterId();
						if(currentEncId != nextEncId)
							clinicalDataBeans.add(dataBean);
					}
				}else{
					if(gwdId.equalsIgnoreCase("0000200200100039000"))//0000200200100039000 is gwdId of Head Circumference
						dataBean.setHeadCircumference(value);
					if(gwdId.equalsIgnoreCase("0000200200100024000"))//0000200200100024000 is gwdId of weight
						dataBean.setWeight(value);
					if(gwdId.equalsIgnoreCase("0000200200100023000"))//0000200200100023000 is gwdId Height 
						dataBean.setHeight(""+Double.parseDouble(value)*0.393700787);

					if(i == (patientDetails.size()-1))
						clinicalDataBeans.add(dataBean);
					else{
						int nextEncId = patientDetails.get(i+1).getEncounter().getEncounterId();
						if(currentEncId != nextEncId)
							clinicalDataBeans.add(dataBean);
					}
				}

				previousEncId = currentEncId;
			}
		}

		return clinicalDataBeans;
	}

	/**
	 * To get list graph details based on patient id
	 * @param patientId
	 * @return list graph details
	 */
	@Override
	public List<GrowthGraph> getGraphList(String patientId) {


		PatientRegistration patientRegistration=patientRegistrationRepository.findOne(PatientRegistrationSpecification.getPatientPersonalDetails(Integer.parseInt(patientId)));
		int ageInMonth = (int)((DateUtil.dateDiff( DateUtil.DATE ,patientRegistration.getPatientRegistrationDob() ,new Date() )%365)/30);
		Integer sex=patientRegistration.getPatientRegistrationSex();

		List<GrowthGraph> graphData=growthgraphRepository.findAll(GrowthGraphSpecification.byAgeandSex(ageInMonth, sex));

		return graphData;
	}

}