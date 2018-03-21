package com.glenwood.glaceemr.server.application.services.phonemessages;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.repositories.EncounterEntityRepository;
import com.glenwood.glaceemr.server.application.services.alertinbox.AlertInboxService;
@Service
@Transactional
public class PhoneMessagesServiceImpl implements PhoneMessagesService{

	@Autowired
	EncounterEntityRepository encounterEntityRepository; 

	@Autowired
	AlertInboxService alertInboxService;

	@Override
	public List<Encounter> getPhoneEncounters(String patientId, String startDate, String endDate) {

		List<Encounter> encounterList=encounterEntityRepository.findAll(PhoneMessagesSpecification.getEncounters(patientId, startDate, endDate));

		return encounterList;
	}

	@Override
	public Encounter getEncounterDetails(String encounterId) {

		Encounter encounter=encounterEntityRepository.findOne(PhoneMessagesSpecification.getEncounterDetails(encounterId));

		return encounter;
	}

	@Override
	public Encounter update(String encounterId, String response,
			String status, String modifiedBy, String serviceDr) {

		Encounter encounter=encounterEntityRepository.findOne(PhoneMessagesSpecification.getEncounter(encounterId));

		encounter.setEncounterServiceDoctor(Long.parseLong(serviceDr));
		
		if(status.equalsIgnoreCase("2")){//delete an alert when encounter status is answered
			
			deleteAlertsByEncounterId(encounterId,modifiedBy);
		}
		
		encounter.setEncounterStatus(Short.parseShort(status));
		encounter.setEncounterModifiedby((Integer.parseInt(modifiedBy)));
		encounter.setEncounterDocResponse(response);
		encounter.setEncounterModifiedon(new Timestamp(new Date().getTime()));

		encounterEntityRepository.saveAndFlush(encounter);

		return encounter;
	}

	private void deleteAlertsByEncounterId(String encounterId, String modifiedBy) {
		
		List<Integer> alertIdList=new ArrayList<Integer>();
		
		List<AlertEvent> alerts=alertInboxService.getAlertsByEncIdAndCatId(Integer.parseInt(encounterId),11);//11 is phone message waiting for reply
		for(int i=0;i<alerts.size();i++){
			
			int alertid=alerts.get(i).getAlertEventId();
			alertIdList.add(alertid);
		}
		
		alertInboxService.deleteAlert(alertIdList, Integer.parseInt(modifiedBy));
	}

	@Override
	public Encounter sendReply(String encounterId, String replyMessage,
			String status, String modifiedBy, String severity) {

		Encounter encounter=encounterEntityRepository.findOne(PhoneMessagesSpecification.getEncounter(encounterId));

		encounter.setEncounterComments(replyMessage);
		encounter.setEncounterStatus(Short.parseShort(status));
		encounter.setEncounterModifiedby((Integer.parseInt(modifiedBy)));
		encounter.setEncounterSeverity(Short.parseShort(severity));
		encounter.setEncounterModifiedon(new Timestamp(new Date().getTime()));

		encounterEntityRepository.saveAndFlush(encounter);
		
		List<Integer> encounterList= new ArrayList<Integer>();
		encounterList.add(Integer.parseInt(encounterId));
		alertInboxService.deleteAlertByEncounter(encounterList, Integer.parseInt(modifiedBy));

		return encounter;
	}

	@Override
	public Encounter createEncounter(String chartId, String severity,
			Short encounterType, String serviceDr, String comments,
			String createdBy) {

		Encounter encounter = new Encounter();

		encounter.setEncounterChartid(Integer.parseInt(chartId));
		encounter.setEncounterSeverity(Short.parseShort(severity));
		encounter.setEncounterType(encounterType);
		encounter.setEncounterStatus(Short.parseShort("1"));
		encounter.setEncounterServiceDoctor(Long.parseLong(serviceDr));
		encounter.setEncounterComments(comments);
		encounter.setEncounterCreatedBy(Long.parseLong(createdBy));
		encounter.setEncounterCreatedDate(new Timestamp(new Date().getTime()));
		encounter.setEncounterDate(new Timestamp(new Date().getTime()));
		encounter.setEncounterReason(-1);
		encounter.setEncounterChargeable(false);
		encounter.setEncounterAlreadySeen(false);
		encounter.setEncounterInsReview(-1);
		encounter.setEncounterMedReview(-1);
		encounter.setEncounterUserLoginId(-1);
		encounter.setEncounterAlertId(Long.parseLong("-1"));
		encounter.setEncounterPos(2);
		encounter.setEncounterRefDoctor(Long.parseLong("-1"));
		encounter.setEncounterAccessStatus(false);
		encounter.setEncounterIsinstruction(false);
		encounter.setEncounterVisittype(Short.parseShort("-1"));
		encounter.setEncounterAssProvider("-1,-1");
		encounter.setEncounterModifiedby(Integer.parseInt(createdBy));
		encounter.setEncounterModifiedon(new Timestamp(new Date().getTime()));
		encounter.setEncounterRoomIsactive(false);
		encounter.setTransitionOfCare(false);
		encounter.setSummaryOfCare(false);

		encounterEntityRepository.save(encounter);
		
		return encounter;
	}
}