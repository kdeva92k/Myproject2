package com.glenwood.glaceemr.server.application.services.roomStatus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.ActivityLog;
import com.glenwood.glaceemr.server.application.models.ActivityLog_;
import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.AlertEvent_;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.CurrentMedication;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.LabEntries;
import com.glenwood.glaceemr.server.application.models.LabEntries_;
import com.glenwood.glaceemr.server.application.models.LabGroups;
import com.glenwood.glaceemr.server.application.models.LabGroups_;
import com.glenwood.glaceemr.server.application.models.LeafLibrary;
import com.glenwood.glaceemr.server.application.models.LeafPatient;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosTable_;
import com.glenwood.glaceemr.server.application.models.Prescription;
import com.glenwood.glaceemr.server.application.models.ReferringDoctor;
import com.glenwood.glaceemr.server.application.models.ReferringDoctor_;
import com.glenwood.glaceemr.server.application.models.Room;
import com.glenwood.glaceemr.server.application.repositories.ActivityLogRepository;
import com.glenwood.glaceemr.server.application.repositories.AlertEventRepository;
import com.glenwood.glaceemr.server.application.repositories.ChartRepository;
import com.glenwood.glaceemr.server.application.repositories.CurrentMedicationRepository;
import com.glenwood.glaceemr.server.application.repositories.EmployeeProfileRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterRepository;
import com.glenwood.glaceemr.server.application.repositories.LabEntriesRepository;
import com.glenwood.glaceemr.server.application.repositories.LeafLibraryRepository;
import com.glenwood.glaceemr.server.application.repositories.LeafPatientRepository;
import com.glenwood.glaceemr.server.application.repositories.PosTableRepository;
import com.glenwood.glaceemr.server.application.repositories.PrescriptionRepository;
import com.glenwood.glaceemr.server.application.repositories.RoomRepository;
import com.glenwood.glaceemr.server.application.specifications.RoomStatusSpecification;
import com.google.common.base.Optional;

@Service
@Transactional
public class RoomStatusServiceImpl implements  RoomStatusService {
	@Autowired
	EntityManager em;

	@Autowired
	PosTableRepository posTableRepository;
	
	@Autowired
	EmployeeProfileRepository employeeProfileRepository;
	
	@Autowired
	ChartRepository chartRepository;
	
	@Autowired
	EncounterRepository encounterRepository;

	@Autowired
	AlertEventRepository alertEventRepository;
	
	@Autowired	
	RoomRepository roomRepository;
	
	@Autowired
	ActivityLogRepository activityLogRepository;
	
	@Autowired
	PrescriptionRepository prescriptionRepository;
	
	@Autowired
	CurrentMedicationRepository currentMedicationRepository;
	
	@Autowired
	LabEntriesRepository labEntriesRepository;
	
	@Autowired
	LeafPatientRepository leafPatientRepository;
	
	@Autowired
	LeafLibraryRepository leafLibraryRepository;
	
	/**
	 * to get locations
	 * @return locations
	 */
	@Override
	public List<PosTable> getPos(Integer pos) {
		List<PosTable> locations = posTableRepository.findAll(RoomStatusSpecification.getPos());
		return locations;
		}
	
	/**
	 * to get provider information
	 * @return List<EmployeeProfile>
	 */
	@Override
	public List<EmployeeProfile> getproviders() {
		List<EmployeeProfile> empprofileList = employeeProfileRepository.findAll(RoomStatusSpecification.getproviders());
		return empprofileList;
	}
 
	/**
	 * to get roomName
	 * @return List<Room>
	 */
	@Override
	public List<Room> getRoomName() {
		List<Room> roomList = roomRepository.findAll(RoomStatusSpecification.getRoomIsActive());
		return roomList;
	}
	
    /**
     * to get todays patients data
     * @return patients data based on pos
     */
	@Override
	public List<PosRooms> getTodaysPatientsData(Integer pos) {
		List<PosRooms> posRooms=null;
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Encounter> root = cq.from(Encounter.class);
			Join<Encounter,Chart> encjoin = root.join(Encounter_.chart,JoinType.INNER);
			DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
	        Calendar cal = Calendar.getInstance();
			String currentdate = date.format(cal.getTime());
			encjoin.on(builder.and(builder.equal(root.get(Encounter_.encounterType),1),
	        		builder.equal(root.get(Encounter_.encounterStatus),1), 
	        		builder.equal(root.get(Encounter_.encounterPos),pos),
	        		builder.equal(builder.function("to_char",String.class,root.get(Encounter_.encounterDate),builder.literal("MM/dd/yyyy")),currentdate)));
	        Join<Chart,PatientRegistration> chpatJoin = encjoin.join(Chart_.patientRegistrationTable,JoinType.INNER);
	        cq.select(builder.construct(PosRooms.class,chpatJoin.get(PatientRegistration_.patientRegistrationId),
	        		builder.concat(chpatJoin.get(PatientRegistration_.patientRegistrationLastName),
	        		chpatJoin.get(PatientRegistration_.patientRegistrationFirstName))));         
	      	List<Object> resultSet = em.createQuery(cq).getResultList();
	      	posRooms = new ArrayList<PosRooms>();
			for (int i = 0; i < resultSet.size(); i++) {
				PosRooms rsb = (PosRooms) resultSet.get(i);
				posRooms.add(rsb);
			}
		} 
		catch (Exception exception) {
			exception.printStackTrace();
		}
		return posRooms;
	}
    
	/**
	 * to update room number
	 * @return List<Encounter>
	 */
	@Override
	public JSONObject updateRoomNo(Integer pos, String addPatientId,Short roomtoAdd) {
		JSONObject updateRoom=new JSONObject();
		try {
		Chart chart=chartRepository.findOne(RoomStatusSpecification.getChartId(addPatientId));
		Integer chartId=null;
		if(chart!=null){
			chartId=Optional.fromNullable(chart.getChartId()).or(-1);
		}
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<Encounter> update = cb.createCriteriaUpdate(Encounter.class);
		Root<Encounter> rootCriteria = update.from(Encounter.class);
		update.set(rootCriteria.get(Encounter_.encounterRoom), roomtoAdd);
		update.set(rootCriteria.get(Encounter_.encounterRoomIsactive),true);
		update.where(cb.and(cb.equal(rootCriteria.get(Encounter_.encounterPos),pos),
				   cb.equal(rootCriteria.get(Encounter_.encounterStatus),1),
				   cb.equal(rootCriteria.get(Encounter_.encounterType),1),
				   rootCriteria.get(Encounter_.encounterChartid).in(chartId)));
		this.em.createQuery(update).executeUpdate();
		String status = "updated";
		updateRoom.put("status",status);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return updateRoom;
	}

	/**
	 * to get roomStatus
	 * @return List<RoomStatusBean>
	 */
	@Override
	public List<PosRooms> getRoomStatus() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PosTable> root = cq.from(PosTable.class);		
		Join<PosTable,Encounter> posJoin = root.join(PosTable_.encounter,JoinType.LEFT);
	    Join<Encounter,Room> encRoomJoin = posJoin.join(Encounter_.room,JoinType.LEFT);
		Join<Encounter,EmployeeProfile> empJoin = posJoin.join(Encounter_.empProfileEmpId,JoinType.LEFT);
	    Join<Encounter,Chart> chartJoin = posJoin.join(Encounter_.chart,JoinType.LEFT);
		posJoin.on(builder.and(builder.notEqual(posJoin.get(Encounter_.encounterRoom),1),
				builder.equal(posJoin.get(Encounter_.encounterType),1),
				builder.equal(posJoin.get(Encounter_.encounterStatus),1),
				builder.equal(posJoin.get(Encounter_.encounterRoomIsactive),true)));
	    Join<Chart,PatientRegistration> patJoin = chartJoin.join(Chart_.patientRegistrationTable,JoinType.INNER);
	    Join<PatientRegistration,ReferringDoctor> h076Join = patJoin.join(PatientRegistration_.referringPhyTable,JoinType.LEFT);
	    Join<PatientRegistration,AlertEvent> alertJoin = patJoin.join(PatientRegistration_.alertEvent,JoinType.LEFT);
		alertJoin.on(alertJoin.get(AlertEvent_.alertEventCategoryId).in(2,3,4));		
	    cq.select(builder.construct(PosRooms.class,
	    		posJoin.get(Encounter_.encounterRoom),
	    		root.get(PosTable_.posTableRelationId),
	    		builder.concat(builder.concat("(",builder.concat(root.get(PosTable_.posTablePlaceOfService),")")),root.get(PosTable_.posTableFacilityComments)),
	    		empJoin.get(EmployeeProfile_.empProfileFullname),
	    		builder.concat(patJoin.get(PatientRegistration_.patientRegistrationFirstName),
	    		patJoin.get(PatientRegistration_.patientRegistrationLastName)),
	    		patJoin.get(PatientRegistration_.patientRegistrationId),
	    		h076Join.get(ReferringDoctor_.referring_doctor_referringdoctor),
	    		alertJoin.get(AlertEvent_.alertEventCategoryId),
	    		builder.function("to_char",String.class,builder.function("age",String.class,builder.literal(encounterRepository.findCurrentTimeStamp()),posJoin.get(Encounter_.encounterCreatedDate)),builder.literal("DD:HH24:MI:SS")),
	    		builder.function("to_char",String.class,builder.function("age",String.class,builder.literal(alertEventRepository.findCurrentTimeStamp()),alertJoin.get(AlertEvent_.alertEventCreatedDate)),builder.literal("DD:HH24:MI:SS"))));
	    cq.where(builder.and(builder.equal(root.get(PosTable_.posTablePosCode),11),builder.equal(root.get(PosTable_.posTableIsActive),true)));
	    cq.orderBy(builder.asc(posJoin.get(Encounter_.encounterPos)));
        List<Object> resultSet = em.createQuery(cq).getResultList();
        List<PosRooms> posRoomsList=new ArrayList<PosRooms>();
        for(int i=0;i<resultSet.size();i++){
        	PosRooms rsb=  (PosRooms) resultSet.get(i);
        	posRoomsList.add(rsb);
        }
		return posRoomsList;
	} 	
	
	/**
	 * to transfer patients
	 * @return List<Encounter>
	 */
	@Override
	public JSONObject updateTransferRooms(Short toswap,Short withroom, Integer pos) {
		List<Encounter> enc = encounterRepository.findAll(RoomStatusSpecification.getEncChartId(toswap, pos));
		List<Integer> chartIdList = new ArrayList<Integer>();
		for(int i=0;i<enc.size();i++)
		{
			chartIdList.add(enc.get(i).getEncounterChartid());
		}
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<Encounter> update = cb.createCriteriaUpdate(Encounter.class);
		Root<Encounter> rootCriteria = update.from(Encounter.class);
		update.set(rootCriteria.get(Encounter_.encounterRoom), withroom);
		update.where(cb.and(cb.equal(rootCriteria.get(Encounter_.encounterPos),pos),
				   cb.equal(rootCriteria.get(Encounter_.encounterStatus),1),
				   cb.equal(rootCriteria.get(Encounter_.encounterType),1),
				   cb.equal(rootCriteria.get(Encounter_.encounterRoomIsactive),true),
				   rootCriteria.get(Encounter_.encounterChartid).in(chartIdList)));
		this.em.createQuery(update).executeUpdate();
		String status = "updated";
		JSONObject transferPat = new JSONObject();
		try {
			transferPat.put("status",status);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return transferPat;
	}
	
	

	/**
	 * to swap patients
	 * @return status
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	@Override
	public JSONObject updateSwapPatients(Short toswap,Short withroom,Integer pos) {
		List<Encounter> chartidsfortoswap = new ArrayList<Encounter>();
        chartidsfortoswap = encounterRepository.findAll(RoomStatusSpecification.getchartIds(toswap, pos));
        List<Encounter> chartidswithRoom = new ArrayList<Encounter>();
        chartidswithRoom = encounterRepository.findAll(RoomStatusSpecification.chartidswithroom(withroom,pos));        
        for(int i=0;i<chartidsfortoswap.size();i++)
        {    	
        	CriteriaBuilder cb = em.getCriteriaBuilder();
    		CriteriaUpdate<Encounter> update = cb.createCriteriaUpdate(Encounter.class);
    		Root<Encounter> rootCriteria = update.from(Encounter.class);
    		update.set(rootCriteria.get(Encounter_.encounterRoom), withroom);
    		update.where(rootCriteria.get(Encounter_.encounterChartid).in(chartidsfortoswap.get(i).getEncounterChartid()));
    		this.em.createQuery(update).executeUpdate();
        }
        for(int i=0;i<chartidswithRoom.size();i++)
        {
        	CriteriaBuilder cb = em.getCriteriaBuilder();
    		CriteriaUpdate<Encounter> update = cb.createCriteriaUpdate(Encounter.class);
    		Root<Encounter> rootCriteria = update.from(Encounter.class);
    		update.set(rootCriteria.get(Encounter_.encounterRoom), toswap);
    		update.where(rootCriteria.get(Encounter_.encounterChartid).in(chartidswithRoom.get(i).getEncounterChartid()));
    		this.em.createQuery(update).executeUpdate();
        }  
        String status = "updated";
        JSONObject switchRooms = new JSONObject();
        try {
        	switchRooms.put("status",status);
		} catch (JSONException e) {
			e.printStackTrace();
		}
        return switchRooms;
	}
		
	/**
	 * to get encounterids
	 * @param patientId
	 * @return encounterIdlist
	 */
	public List<Integer> getEncId(String patientId){
	    Chart chart = chartRepository.findOne(RoomStatusSpecification.getChartId(patientId));
		Integer chartId = null;
		if(chart!=null){
			chartId = Optional.fromNullable(chart.getChartId()).or(-1);
		}
		List<Encounter> encounter = encounterRepository.findAll(RoomStatusSpecification.getEncId(chartId));
        List<Integer> encIdList = new ArrayList<Integer>();
        for(int i=0;i<encounter.size();i++){
        	encIdList.add(encounter.get(i).getEncounterId());
        }
		return encIdList;
   }
	
	/**
	 * to get ordered information
	 * @return orderedLabVaccines,orderedLabDetails
	 */
	@Override
	public List<OrderedData> getOrdered(List<String> patientIdList) {
		List<OrderedData> orderedList = new ArrayList<OrderedData>();
		for(int j=0;j<patientIdList.size();j++){
			OrderedData ordered=new OrderedData();
			List<Integer> encIdList=new ArrayList<Integer>();
				encIdList=getEncId(patientIdList.get(j));
				ordered.setPatientId(patientIdList.get(j));
			/**
			 * to get ordered lab details
			 */
			List<OrderedBean> ordLab=new ArrayList<OrderedBean>();
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<LabEntries> root = cq.from(LabEntries.class);
			Join<LabEntries,EmployeeProfile> empJoin = root.join(LabEntries_.empProfile,JoinType.LEFT);
			Join<LabEntries,Encounter> enclabJoin = root.join(LabEntries_.encounter,JoinType.LEFT);
			Join<LabEntries,LabGroups> labJoin = root.join(LabEntries_.labGroups,JoinType.INNER);
			cq.select(builder.construct(OrderedBean.class,builder.function("to_char",String.class,root.get(LabEntries_.labEntriesOrdOn),builder.literal("mm/dd/yyyy")),
					root.get(LabEntries_.labEntriesTestDesc),
					root.get(LabEntries_.labEntriesTestStatus),
					builder.coalesce(empJoin.get(EmployeeProfile_.empProfileFullname),""),
					root.get(LabEntries_.labEntriesResultNotes),
					root.get(LabEntries_.labEntriesLoinc),
					root.get(LabEntries_.labEntriesCpt),
					root.get(LabEntries_.labEntriesTestdetailId),
					root.get(LabEntries_.labEntriesTestId),
					root.get(LabEntries_.labEntriesGroupid),
				    labJoin.get(LabGroups_.labGroupsDesc)));
			cq.where(builder.and(builder.not(root.get(LabEntries_.labEntriesTestStatus).in(7)),
					builder.not(labJoin.get(LabGroups_.labGroupsId).in(36,5)),
					enclabJoin.get(Encounter_.encounterId).in(encIdList)));
			cq.orderBy(builder.desc(root.get(LabEntries_.labEntriesOrdOn)));
			List<Object> LabList = em.createQuery(cq).getResultList();
			for(int i=0;i<LabList.size();i++){
				OrderedBean orderedLabDet=(OrderedBean) LabList.get(i);
				ordLab.add(orderedLabDet);
			}
            /**
             * to get orderedvaccine Details	
             */
			List<OrderedBean> ordVac=new ArrayList<OrderedBean>();
	       	CriteriaBuilder cb=em.getCriteriaBuilder();
			CriteriaQuery<Object> criteriaQuery=cb.createQuery();
			Root<LabEntries> CriteriaRoot = criteriaQuery.from(LabEntries.class);
			Join<LabEntries,EmployeeProfile> labEmpJoin = CriteriaRoot.join(LabEntries_.empProfile,JoinType.LEFT);
			Join<LabEntries,Encounter> labEncJoin = CriteriaRoot.join(LabEntries_.encounter,JoinType.LEFT);
			Join<LabEntries,LabGroups> LabGroupJoin = CriteriaRoot.join(LabEntries_.labGroups,JoinType.INNER);
			criteriaQuery.select(builder.construct(OrderedBean.class,cb.function("to_char",String.class,
					CriteriaRoot.get(LabEntries_.labEntriesOrdOn),cb.literal("mm/dd/yyyy")),
					CriteriaRoot.get(LabEntries_.labEntriesTestDesc),
					CriteriaRoot.get(LabEntries_.labEntriesTestStatus),
					cb.coalesce(labEmpJoin.get(EmployeeProfile_.empProfileFullname),""),
					CriteriaRoot.get(LabEntries_.labEntriesResultNotes),
					CriteriaRoot.get(LabEntries_.labEntriesLoinc),
					CriteriaRoot.get(LabEntries_.labEntriesCpt),
					CriteriaRoot.get(LabEntries_.labEntriesTestdetailId),
					CriteriaRoot.get(LabEntries_.labEntriesTestId),
					CriteriaRoot.get(LabEntries_.labEntriesGroupid),
					LabGroupJoin.get(LabGroups_.labGroupsDesc)));
			criteriaQuery.where(cb.and(cb.not(CriteriaRoot.get(LabEntries_.labEntriesTestStatus).in(7)),
					CriteriaRoot.get(LabEntries_.labEntriesGroupid).in(36,5)),
					labEncJoin.get(Encounter_.encounterId).in(encIdList));
			criteriaQuery.orderBy(cb.desc(CriteriaRoot.get(LabEntries_.labEntriesOrdOn)));
			List<Object> vacList = em.createQuery(criteriaQuery).getResultList();
			for(int i=0;i<vacList.size();i++){
				OrderedBean ordVacDet=(OrderedBean)vacList.get(i);
				ordVac.add(ordVacDet);
			}
			ordered.setOrderedLabs(ordLab);
			ordered.setOrderdVaccine(ordVac);
			orderedList.add(ordered);
		}
		return orderedList;
	}
	
	
	/**
	 * to get activity information
	 * @return JSONObject
	 */

	@Override
	public List<ActivitiesData> getActivities(List<String> patientIdList) {
		List<ActivitiesData> activitiesList = new ArrayList<ActivitiesData>();
		for(int j=0;j<patientIdList.size();j++){
			List<Integer> encIdList = new ArrayList<Integer>();
			ActivitiesData act = new ActivitiesData();
			encIdList = getEncId(patientIdList.get(j));
			act.setPatientId(patientIdList.get(j));
			ActivityBean userData = null;
			ActivityBean entityData = null;
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<ActivityLog> root = cq.from(ActivityLog.class);
			cq.select(builder.construct(ActivityBean.class,root.get(ActivityLog_.moduleid),
					root.get(ActivityLog_.entityid),
					builder.function("to_char",String.class,root.get(ActivityLog_.time),builder.literal("yyyy-MM-dd HH:mm:ss")),
					root.get(ActivityLog_.userid)));
			cq.where(builder.and(root.get(ActivityLog_.patientid).in(patientIdList),builder.equal(root.get(ActivityLog_.status),false),root.get(ActivityLog_.encounterid).in(encIdList)));
			 List<Object> resultSet = em.createQuery(cq).getResultList();
		  	 List<ActivityBean> activities = new ArrayList<ActivityBean>();
	          for(int i=0;i<resultSet.size();i++){
	              ActivityBean ac = (ActivityBean) resultSet.get(i);
	              activities.add(ac);
	          }
				List<Integer> entities = new ArrayList<Integer>();
				List<Integer> userids = new ArrayList<Integer>();
				for(int i=0;i<activities.size();i++){
					ActivityBean actJSON = (ActivityBean) resultSet.get(i);
					String module = actJSON.getModuleid().toString();
					int entityId = Integer.parseInt(actJSON.getEntityid().toString());
					int userid = Integer.parseInt(actJSON.getUserid().toString());
	        		String userName = "";
	        		String entityValue = "";
			        userData = new ActivityBean(userName,userid);
			        entityData = new ActivityBean(entityId, entityValue);
					if(!userids.contains(userid)){
						userids.add(userid);
						CriteriaBuilder builderemp = em.getCriteriaBuilder();
						CriteriaQuery<Object> query = builder.createQuery();
						Root<EmployeeProfile> empRoot = query.from(EmployeeProfile.class);
						query.select(empRoot.get(EmployeeProfile_.empProfileFullname));
						query.where(builderemp.equal(empRoot.get(EmployeeProfile_.empProfileEmpid),userid));
	                 List<Object> result = em.createQuery(query).getResultList();
	                 if(!result.isEmpty()){
	                	userName=result.get(i).toString();
	                	userData.setUserid(userid);
	                	userData.setUserName(userName);
					}
				}
	          if(!entities.contains(entityId)){
	              entities.add(entityId);
	              	/**
					 * To get drug name for prescription
					 **/
					if(Integer.parseInt(module)==3){
						Prescription prescription = new Prescription();
						prescription = prescriptionRepository.findOne(RoomStatusSpecification.getRxName(entityId));
						String rxname = "";
						if(prescription!=null){
							rxname = Optional.fromNullable(prescription.getRxname()).or(" ");
						}
						CurrentMedication currentMedication = new CurrentMedication();
						currentMedication = currentMedicationRepository.findOne(RoomStatusSpecification.getCurMedRxName(entityId));
						String curMedRxName="";
						if(currentMedication!=null){
							curMedRxName = Optional.fromNullable(currentMedication.getCurrentMedicationRxName()).or("");
						}
						List<String> medName = new ArrayList<String>();
						medName.add(rxname);
						medName.add(curMedRxName);
						if(entityId!=-1){
							entityValue = medName.toString();
							entityData.setEntityid(entityId);;
							entityData.setEntityValue(entityValue);;
						}
					}
	    
					/**
					 *To get test name for investigation
					 **/
					if(Integer.parseInt(module)==2){ 
						LabEntries labEntries = new LabEntries();
						labEntries = labEntriesRepository.findOne(RoomStatusSpecification.getLabEntriesTestDesc(entityId));
						String labEntriesTestDesc = "";
						if(labEntries!=null){
							labEntriesTestDesc = Optional.fromNullable(labEntries.getLabEntriesTestDesc()).or("");
						}
						if(entityId!=-1){
							entityValue = labEntriesTestDesc;
							entityData.setEntityid(entityId);;
							entityData.setEntityValue(entityValue);;
						}
					}
					/**
					 * To get the template Name
					 **/
					if(Integer.parseInt(module)==4){ 
						LeafPatient leafPatient = new LeafPatient();
						leafPatient = leafPatientRepository.findOne(RoomStatusSpecification.getLeafPatLibId(entityId));
						Integer leafPatId = null;
						if(leafPatient!=null){
							leafPatId = Optional.fromNullable(leafPatient.getLeafPatientLeafLibraryId()).or(-1);
						}
						LeafLibrary leafLibrary = new LeafLibrary();
						leafLibrary = leafLibraryRepository.findOne(RoomStatusSpecification.getLeafLibName(leafPatId));
						String leafLibraryName = "";
						if(leafLibrary!=null){
							leafLibraryName = Optional.fromNullable(leafLibrary.getLeafLibraryName()).or("");
						}
						if(entityId!=-1){
							entityValue = leafLibraryName;
							entityData.setEntityid(entityId);;
							entityData.setEntityValue(entityValue);;
						}
					}
				}  
			}
			     	act.setactivityJson(activities);
			        act.setUserData(userData);
			        act.setEntityData(entityData);
			        activitiesList.add(act);
		}
		return activitiesList;
	}
	
}
	
	

