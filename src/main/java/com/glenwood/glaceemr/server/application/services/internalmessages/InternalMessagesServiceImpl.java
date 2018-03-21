package com.glenwood.glaceemr.server.application.services.internalmessages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.PhonemessageCommentScribble;
import com.glenwood.glaceemr.server.application.repositories.AlertInboxRepository;
import com.glenwood.glaceemr.server.application.repositories.ChartRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterRepository;
import com.glenwood.glaceemr.server.application.repositories.PhonemessageCommentScribbleRepository;
import com.glenwood.glaceemr.server.application.services.alertinbox.AlertInboxService;
import com.glenwood.glaceemr.server.application.services.phonemessages.PhoneMessagesService;
import com.glenwood.glaceemr.server.application.services.phonemessages.PhoneMessagesSpecification;
import com.glenwood.glaceemr.server.application.specifications.ChartSpecification;
import com.glenwood.glaceemr.server.application.specifications.InternalMessagesSpecification;

@Service
@Transactional
public class InternalMessagesServiceImpl implements InternalMessagesService{

	@Autowired
	EncounterRepository encounterRepository;
	
	@Autowired
	PhonemessageCommentScribbleRepository phonemessageCommentScribbleRepository;
	
	@Autowired
	AlertInboxRepository alertInboxRepository;
	
	@Autowired
	ChartRepository chartRepository;
	
	@Autowired
	AlertInboxService alertInboxService;
	
	@Autowired
	PhoneMessagesService phoneMessagesService;
	
	@Override
	public List<Encounter> getIMEncounters(String patientId) {

		List<Encounter> encounterList=encounterRepository.findAll(InternalMessagesSpecification.getIMEncounters(patientId));
		return encounterList;
	}

	@Override
	public Encounter getEncounterDetails(String encounterId) {
		
		Encounter encounter=encounterRepository.findOne(PhoneMessagesSpecification.getEncounterDetails(encounterId));
		
		return encounter;
	}

	@Override
	public Encounter update(String encounterId, String patientId,
			String chartId, String userId,String serviceDoctor, String message, String severity,
			String status, String encounterType) {
		
		Encounter encounter=encounterRepository.findOne(PhoneMessagesSpecification.getEncounterDetails(encounterId));
		encounter.setEncounterChartid(Integer.parseInt(chartId));
		encounter.setEncounterServiceDoctor(Long.parseLong(userId));
		encounter.setEncounterCreatedBy(Long.parseLong(userId));
		encounter.setEncounterModifiedby(Integer.parseInt(userId));
		encounter.setEncounterDocResponse(message);
		encounter.setEncounterSeverity(Short.parseShort(severity));
		encounter.setEncounterStatus(Short.parseShort(status));
		encounter.setEncounterType(Short.parseShort(encounterType));
		encounterRepository.saveAndFlush(encounter);
		
		if(status.equalsIgnoreCase("3")){//close an alert (if 3 means status is closed)
			List<Integer> encounterList= new ArrayList<Integer>();
			encounterList.add(Integer.parseInt(encounterId));
			alertInboxService.deleteAlertByEncounter(encounterList, Integer.parseInt(userId));
			updateChart(chartId);
		}
		
		return encounter;
	}

	public void updateChart(String chartId) {

		List<Integer> statusList=new ArrayList<Integer>();
		statusList=Arrays.asList(3,100);
		List<Encounter> officeEncounter=encounterRepository.findAll(InternalMessagesSpecification.getEncounterByChartIdAndEncType(chartId,1,statusList));
		List<Encounter> externalCommEncounter=encounterRepository.findAll(InternalMessagesSpecification.getEncounterByChartIdAndEncType(chartId,3,statusList));
		List<Encounter> msgCommEncounter=encounterRepository.findAll(InternalMessagesSpecification.getEncounterByChartIdAndEncType(chartId,4,statusList));
		statusList=Arrays.asList(1,100);
		List<Encounter> phoneEncounter=encounterRepository.findAll(InternalMessagesSpecification.getEncounterByChartIdAndEncType(chartId,2,statusList));

		if(officeEncounter.size() == 0 && externalCommEncounter.size() == 0 && msgCommEncounter.size() == 0&& phoneEncounter.size() == 0){
			
			Chart chartData=chartRepository.findOne(ChartSpecification.findByChartId(Integer.parseInt(chartId)));
			chartData.setChartForwardedFrom(0);
			chartData.setChartForwardedTo(0);
			chartData.setChartStatusid(1);
			chartRepository.saveAndFlush(chartData);
		}
		
	}
	

	@Override
	public Encounter compose(String patientId, String chartId, String userId,String serviceDoctor,String toId,
			String message, String severity, String encounterType) {
		
		Encounter newEncounter=phoneMessagesService.createEncounter(chartId, severity, Short.parseShort(encounterType), serviceDoctor, message, userId);
		
		String encounterId=newEncounter.getEncounterId().toString();
		insertScribbleFilePhoneMsg(patientId, chartId, encounterId);
		
		createAlert(patientId,encounterId,chartId,toId,userId, message);//to create new alert
		
		return newEncounter;
	}
	
	public int createAlert(String patientId,String encounterId,String chartId,String toId,String userId,String message) {
		
		List<Integer> toIdList=new ArrayList<Integer>(Arrays.asList(Integer.parseInt(toId)));
		List<AlertEvent> newAlert=alertInboxService.composeAlert(Integer.parseInt(userId), toIdList, 1, 13, -1, Integer.parseInt(patientId), Integer.parseInt(encounterId), message, Integer.parseInt(chartId), -1, -1);
		
		AlertEvent alert=newAlert.get(0);
		
		int alertId=alert.getAlertEventId();
		alert.setAlertEventEncounterId(Integer.parseInt(encounterId));
		alertInboxRepository.saveAndFlush(alert);
		
		return alertId;
	}

	public void insertScribbleFilePhoneMsg(String patientId,String chartId,String encounterId){
		
		PhonemessageCommentScribble commentScribble=new PhonemessageCommentScribble();
		commentScribble.setChartId(Integer.parseInt(chartId));
		commentScribble.setEncounterId(Integer.parseInt(encounterId));
		commentScribble.setPatientId(Integer.parseInt(patientId));
		phonemessageCommentScribbleRepository.save(commentScribble);
	}
}
