package com.glenwood.glaceemr.server.application.services.chart.dischargeVitals;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.PatientVitals;
import com.glenwood.glaceemr.server.application.repositories.PatientVitalsRepository;
import com.glenwood.glaceemr.server.application.specifications.DischargeVitalSpecification;

@Service
public class DischargeVitalServiceImpl implements DischargeVitalService{


	@Autowired
	PatientVitalsRepository patientVitalsRepository;

	
	
	
	@Override
	public Boolean saveDischargeVitals(DischargeSaveVitalBean vitalDataBean) {

		try{
			
			
			Set<Entry<String, String>> saveDataset = vitalDataBean.getSavedataMap().entrySet();
			Set<Entry<String, String>> delDataset = vitalDataBean.getDeletedataMap().entrySet();
			 
			performOperation(delDataset,"delete",vitalDataBean.getSavedataMap(),vitalDataBean.patientId,vitalDataBean.encounterId,vitalDataBean.chartId);
			performOperation(saveDataset,"save",vitalDataBean.getDeletedataMap(),vitalDataBean.patientId,vitalDataBean.encounterId,vitalDataBean.chartId);
			
			
			
			
			
		/*	List<PatientVitals> patientVitalsToDelete = new ArrayList<PatientVitals>();
			for (Entry<String, String> dataEntry: delDataset ) {
				try{
					String[] idString = dataEntry.getKey().split("_");
					String clinicalElementGwid = idString[0].toString();
					String dateString = idString[1].split("_")[0].toString();
					String timeString = idString[1].split("_")[1].toString();
					Date date = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(dateString).getTime()));

					if(!vitalDataBean.getSavedataMap().containsKey(dataEntry.getKey())){
						patientVitalsToDelete.add(patientVitalsRepository.findOne(DischargeVitalSpecification.getDichargeVitalsByGwid(date, new Timestamp(date.getTime()), clinicalElementGwid, vitalDataBean.getPatientId(), vitalDataBean.getEncounterId())));
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}*/
			
			
			
			
		/*	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			Date parsed=null;

			try {
				parsed = format.parse(vitalDataBean.getDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			java.sql.Date vitalDate = new java.sql.Date(parsed.getTime());


			List<PatientVitals> vitalsToSave=new ArrayList<PatientVitals>();

			Map<String,String> dataMap=vitalDataBean.getDataMap();
			Set<Entry<String, String>> dataset = dataMap.entrySet();

			for (Entry<String, String> dataEntry: dataset) {
				PatientVitals patVitals = new PatientVitals();
				patVitals.setPatientVitalsPatientid(vitalDataBean.getPatientId());
				patVitals.setPatientVitalsChartid(vitalDataBean.getChartId());
				patVitals.setPatientVitalsEncounterid(vitalDataBean.getEncounterId());
				patVitals.setPatientVitalsTimeOfRecording(new Timestamp(parsed.getTime()));
				patVitals.setPatientVitalsDateOfRecording(vitalDate);
				patVitals.setPatientVitalsGwid(dataEntry.getKey());
				patVitals.setPatientVitalsValue(dataEntry.getValue());
				vitalsToSave.add(patVitals);
			}

			patientVitalsRepository.save(vitalsToSave);*/
			return true;
		}catch(Exception e){
			return false;
		}
	}

	private void performOperation(Set<Entry<String, String>> dataSet,String operation,Map<String,String> dataMap,Integer patientId,Integer encounterId,Integer chartId) {
		
		List<PatientVitals> patientVitalsToDelete = new ArrayList<PatientVitals>();
		List<PatientVitals> patientVitalsToSave = new ArrayList<PatientVitals>();
		
		for (Entry<String, String> dataEntry: dataSet ) {
			try{
				String[] idString = dataEntry.getKey().split("_");
				String clinicalElementGwid = idString[0].toString();
				String dateString = idString[1].toString();
				String timeString = idString[2].toString();
				String elementEncounterId = "";
				if(idString.length>3 && idString[3] != null)
					elementEncounterId = idString[3].toString();
				else
					elementEncounterId = encounterId.toString();
				Date date = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(dateString).getTime()));
				Timestamp time = new Timestamp((new SimpleDateFormat("hh:mm:ss").parse(timeString).getTime()));
				if(operation.equalsIgnoreCase("delete") &&  !dataMap.containsKey(dataEntry.getKey())){
					patientVitalsToDelete.add(patientVitalsRepository.findOne(DischargeVitalSpecification.getDichargeVitalsByGwid(date, time, clinicalElementGwid, patientId, Integer.parseInt(elementEncounterId))));
				}else if (operation.equalsIgnoreCase("save")){
					//System.out.println("date"+date.toString()+"time"+time.toString()+"gwid"+clinicalElementGwid+"patientId"+patientId+"encounterId"+encounterId);
					
					PatientVitals patientVital = patientVitalsRepository.findOne(DischargeVitalSpecification.getDichargeVitalsByGwid(date, time, clinicalElementGwid, patientId, Integer.parseInt(elementEncounterId)));
					if(patientVital != null){
						patientVital.setPatientVitalsValue(dataEntry.getValue());
						patientVitalsRepository.saveAndFlush(patientVital);
					}else{
						PatientVitals addPatientVital = new PatientVitals();
						addPatientVital.setPatientVitalsGwid(clinicalElementGwid);
						addPatientVital.setPatientVitalsChartid(chartId);
						addPatientVital.setPatientVitalsPatientid(patientId);
						addPatientVital.setPatientVitalsEncounterid(Integer.parseInt(elementEncounterId));
						addPatientVital.setPatientVitalsDateOfRecording(date);
						addPatientVital.setPatientVitalsTimeOfRecording(time);
						addPatientVital.setPatientVitalsValue(dataEntry.getValue());
						patientVitalsToSave.add(addPatientVital);
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(!patientVitalsToDelete.isEmpty())
			patientVitalsRepository.deleteInBatch(patientVitalsToDelete);
		if(!patientVitalsToSave.isEmpty())
			patientVitalsRepository.save(patientVitalsToSave);
	}

	@Override
	public List<PatientVitals> getDischartgeVitals(Integer patientId,Integer encounterId, Integer admssEpisode) {
		return patientVitalsRepository.findAll(DischargeVitalSpecification.getDichargeVitalsByEncounterId(patientId, encounterId, admssEpisode));
	}

}
