package com.glenwood.glaceemr.server.application.services.chart.RefillRequest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.AlertArchive;
import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.BillingConfigTable;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.InitialSettings;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.Prescription;
import com.glenwood.glaceemr.server.application.models.SchedulerAppointment;
import com.glenwood.glaceemr.server.application.models.SSCancelOutbox;
import com.glenwood.glaceemr.server.application.models.SSMessageInbox;
import com.glenwood.glaceemr.server.application.repositories.AlertArchiveRepository;
import com.glenwood.glaceemr.server.application.repositories.AlertInboxRepository;
import com.glenwood.glaceemr.server.application.repositories.BillingConfigTableRepository;
import com.glenwood.glaceemr.server.application.repositories.EmpProfileRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterRepository;
import com.glenwood.glaceemr.server.application.repositories.InitialSettingsRepository;
import com.glenwood.glaceemr.server.application.repositories.NdcDrugBrandMapRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.repositories.PosTableRepository;
import com.glenwood.glaceemr.server.application.repositories.PrescriptionRepository;
import com.glenwood.glaceemr.server.application.repositories.SchedulerAppointmentRepository;
import com.glenwood.glaceemr.server.application.repositories.SSCancelOutboxRepository;
import com.glenwood.glaceemr.server.application.repositories.SSMessageInboxRepository;
import com.glenwood.glaceemr.server.application.repositories.SSMessageInboxRepository;
import com.glenwood.glaceemr.server.application.specifications.MergeRefillSpecification;
import com.glenwood.glaceemr.server.application.specifications.RefillRequestSpecification;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.google.common.base.Optional;
import com.google.common.base.Strings;


/**
 * @author selvakumar
 *
 */
@Service
@Transactional
public class RefillRequestServiceImpl implements RefillRequestService {
	@Autowired
	PrescriptionRepository prescriptionRepository; 

	@Autowired
	EmpProfileRepository employeeRepository;
	
	@Autowired
	SSMessageInboxRepository ssmessageinboxrepository;
	
	@Autowired
	SSCancelOutboxRepository sscanceloutboxrepository;
	
	@Autowired
	AlertInboxRepository alertInboxRepository;
	
	@Autowired
	AlertArchiveRepository alertArchiveRepository;
	
	@Autowired
	BillingConfigTableRepository billingConfigTableRepository;
	
	@Autowired
	InitialSettingsRepository initialSettingsRepository;

	@Autowired
	SchedulerAppointmentRepository appointmentRepository;
	
	@Autowired
	EncounterRepository encounterRepository;
	
	@Autowired
	PatientRegistrationRepository patientRegistrationRepository;
	
	@Autowired
	PosTableRepository posTableRepository;
	
	@Autowired
	NdcDrugBrandMapRepository ndcDrugBrandMapRepository;
	
	@Autowired
	EntityManagerFactory emf ;
	
	@PersistenceContext
	EntityManager em;
	
	/*
	 * (non-Javadoc)
	 * @see com.glenwood.glaceemr.server.application.services.RefillRequestService.RefillRequestService#getdenieddrugs(java.lang.Integer, java.lang.Integer)
	   @param encounterid
	   @param patientid
	 */

	@Override
	public List<Prescription> getDeniedDrugs(Integer encounterid,Integer patientid) {

		Integer encounterId=Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(encounterid.toString())).or("-1"));
		Integer patientId=Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(patientid.toString())).or("-1"));
		List<Prescription> list1=prescriptionRepository.findAll(RefillRequestSpecification.getDeniedDrug(encounterId,patientId));
		return list1;
	}
	/**
	 * Method to return the bean for complete alerts
	 */
	@Override
	public SSMessageBean getCount() {
		
		List<SSMessageInbox> ssinboxlist=ssmessageinboxrepository.findAll(RefillRequestSpecification.getInboxCount());
					
		List<SSCancelOutbox> ssoutboxlist=sscanceloutboxrepository.findAll(RefillRequestSpecification.getOutboxCount());
		
		SSMessageBean ssBean= new SSMessageBean(ssinboxlist,ssoutboxlist);
		
		return ssBean;
		
	}
	@Override
	public MedicationListBean getAlertInbox(String erxUserAlertType,String userId, boolean isNewPage) {
	
		List<SSMessageInbox> inboxList=ssmessageinboxrepository.findAll(RefillRequestSpecification.getInboxAlert(erxUserAlertType, userId, isNewPage));
		List<SSCancelOutbox> ssOutboxList=sscanceloutboxrepository.findAll(RefillRequestSpecification.getOutboxAlert(erxUserAlertType,userId,isNewPage));
		MedicationListBean ssBean= new MedicationListBean(inboxList,ssOutboxList);

		return ssBean;
	}
	
	@Override
	public String isCloseAlert(int userId, String alertId,String alertEventIds) {
		List<SSMessageInbox> ssMegInboxList=ssmessageinboxrepository.findAll(MergeRefillSpecification.getCloseAlert(alertId));
		for(int i=0;i<ssMegInboxList.size();i++){
		ssMegInboxList.get(i).setSsMessageInboxIsClose(true);
		}
		if(!alertEventIds.trim().equals("")){
			List<AlertEvent> Aevent=alertInboxRepository.findAll(MergeRefillSpecification.getAlertEvent(userId,alertEventIds));
			List<AlertArchive> Aarchive= new ArrayList<AlertArchive>();
			if(Aevent.size()>0){
			for(int i=0;i<Aevent.size();i++){
				Aevent.get(i).setAlertEventStatus(2);
				Aevent.get(i).setAlertEventClosedDate(alertInboxRepository.findCurrentTimeStamp());
				Aevent.get(i).setAlertEventModifiedby(userId);
				Aevent.get(i).setAlertEventModifiedDate(alertInboxRepository.findCurrentTimeStamp());
				Aarchive.get(i).setAlertEventId(Aevent.get(i).getAlertEventId());
				Aarchive.get(i).setAlertCategoryTable(Aevent.get(i).getAlertCategoryTable());
				Aarchive.get(i).setAlertEventChartId(Aevent.get(i).getAlertEventChartId());
				Aarchive.get(i).setAlertEventClosedDate(Aevent.get(i).getAlertEventClosedDate());
				Aarchive.get(i).setAlertEventCreatedDate(Aevent.get(i).getAlertEventCreatedDate());
				Aarchive.get(i).setAlertEventEncounterId(Aevent.get(i).getAlertEventEncounterId());
				Aarchive.get(i).setAlertEventFrom(Aevent.get(i).getAlertEventFrom());
				Aarchive.get(i).setAlertEventFrompage(Aevent.get(i).getAlertEventFrompage());
				Aarchive.get(i).setAlertEventHighlight(Aevent.get(i).getAlertEventHighlight());
				Aarchive.get(i).setAlertEventIsgroupalert(Aevent.get(i).getAlertEventIsgroupalert());
				Aarchive.get(i).setAlertEventMessage(Aevent.get(i).getAlertEventMessage());
				Aarchive.get(i).setAlertEventModifiedby(Aevent.get(i).getAlertEventModifiedby());
				Aarchive.get(i).setAlertEventModifiedDate(Aevent.get(i).getAlertEventModifiedDate());
				Aarchive.get(i).setAlertEventParentalertid(Aevent.get(i).getAlertEventParentalertid());
				Aarchive.get(i).setAlertEventPatientId(Aevent.get(i).getAlertEventPatientId());
				Aarchive.get(i).setAlertEventPatientName(Aevent.get(i).getAlertEventPatientName());
				Aarchive.get(i).setAlertEventRead(Aevent.get(i).getAlertEventRead());
				Aarchive.get(i).setAlertEventReadby(Aevent.get(i).getAlertEventReadby());
				Aarchive.get(i).setAlertEventReadDate(Aevent.get(i).getAlertEventReadDate());
				Aarchive.get(i).setAlertEventRefId(Aevent.get(i).getAlertEventRefId());
				Aarchive.get(i).setAlertEventRoomId(Aevent.get(i).getAlertEventRoomId());
				Aarchive.get(i).setAlertEventStatus(Aevent.get(i).getAlertEventStatus());
				Aarchive.get(i).setAlertEventStatus1(Aevent.get(i).getAlertEventStatus1());
				Aarchive.get(i).setAlertEventTo(Aevent.get(i).getAlertEventTo());
				Aarchive.get(i).setAlertEventUnknown(Aevent.get(i).getAlertEventUnknown());
				}
			alertArchiveRepository.save(Aarchive);
			alertInboxRepository.deleteInBatch(Aevent);
		}
		}
		return "Alert Closed";
	}
	@Override
	public List<SSMessageInbox> mergeRequest(int patientId, String alertId) {
		SSMessageInbox msgobj=ssmessageinboxrepository.findOne(MergeRefillSpecification.getPatientName(alertId));
		String lname=HUtil.ValidateSingleQuote(msgobj.getSsMessageInboxReceivedPatientLastname());
		String fname=HUtil.ValidateSingleQuote(msgobj.getSsMessageInboxReceivedPatientFirstname());
		String gender=HUtil.ValidateSingleQuote(msgobj.getSsMessageInboxReceivedPatientGender());
		Timestamp dob=msgobj.getSsMessageInboxReceivedPatientDob();
		List<SSMessageInbox> patlist=ssmessageinboxrepository.findAll(MergeRefillSpecification.getPatientList(lname,fname,dob,gender));
		for(int i=0;i<patlist.size();i++){
			patlist.get(i).setSsMessageInboxMapPatientId(patientId);
		}
		return patlist;
	}
	
	@Override
	public List<SSMessageInbox> patRequestsData(int patientId) {
		List<SSMessageInbox> patlist=ssmessageinboxrepository.findAll(RefillRequestSpecification.getPendingRequests(patientId));
		return patlist;
	}
				
	@Override
	public String BillingConfig(int patientId) {
		InitialSettings data=initialSettingsRepository.findOne(RefillRequestSpecification.getInitValue());
		BillingConfigTable type=billingConfigTableRepository.findOne(RefillRequestSpecification.getBillingConfig(data));
		List<SchedulerAppointment> schedulerAppointmentList=appointmentRepository.findAll(RefillRequestSpecification.getNextAppt(type.getBillingConfigTableLookupDesc(),patientId));
		List<Encounter> EncounterList=encounterRepository.findAll(RefillRequestSpecification.getPrevApptDate(type.getBillingConfigTableLookupDesc(),patientId));
		String apptdata=" ";
		if(EncounterList!=null && EncounterList.size()>0)
			apptdata=apptdata+EncounterList.get(0).getEncounterDate();
		if(schedulerAppointmentList!=null && schedulerAppointmentList.size()>0)
			apptdata=apptdata+"##"+schedulerAppointmentList.get(0).getSchApptDate()+" "+schedulerAppointmentList.get(0).getSchApptStarttime();
		else
			apptdata=apptdata+"##"+" ";
		
		return apptdata;
	}
	@Override
	public PatientRegistration getPatientData(int patientId) {
		List<PatientRegistration> patdata= patientRegistrationRepository.findAll(RefillRequestSpecification.getPatientDetails(patientId));
		return patdata.get(0);
	}
	@Override
	public String getPosDetails(int patientId) {
		String posvalue="";
		List<PosTable> posdata=posTableRepository.findAll(RefillRequestSpecification.getPosDetails(patientId));	
		if(posdata.size()>0)
			posvalue=(posdata.get(0).getPosTablePlaceOfService() + posdata.get(0).getPosTableFacilityComments());
		
		return posvalue;
	}


}
