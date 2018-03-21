package com.glenwood.glaceemr.server.application.services.workflowalerts;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.AlertCategory;
import com.glenwood.glaceemr.server.application.models.AlertCategory_;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.InitialSettings;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.Room;
import com.glenwood.glaceemr.server.application.models.Room_;
import com.glenwood.glaceemr.server.application.models.UserGroup;
import com.glenwood.glaceemr.server.application.models.Workflow;
import com.glenwood.glaceemr.server.application.models.WorkflowBean;
import com.glenwood.glaceemr.server.application.models.WorkflowReportsBean;
import com.glenwood.glaceemr.server.application.models.Workflow_;
import com.glenwood.glaceemr.server.application.repositories.AlertCategoryRepository;
import com.glenwood.glaceemr.server.application.repositories.EmployeeProfileRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterEntityRepository;
import com.glenwood.glaceemr.server.application.repositories.InitialSettingsRepository;
import com.glenwood.glaceemr.server.application.repositories.RoomRepository;
import com.glenwood.glaceemr.server.application.repositories.UserGroupRepository;
import com.glenwood.glaceemr.server.application.repositories.WorkflowAlertRepository;
import com.glenwood.glaceemr.server.application.specifications.AlertCategorySpecification;
import com.glenwood.glaceemr.server.application.specifications.EmployeeSpecification;
import com.glenwood.glaceemr.server.application.specifications.EncounterEntitySpecification;
import com.glenwood.glaceemr.server.application.specifications.InitialSettingsSpecification;
import com.glenwood.glaceemr.server.application.specifications.UserGroupSpecification;
import com.glenwood.glaceemr.server.application.specifications.WorkflowAlertSpecification;

@Service
@Transactional
public class WorkflowAlertServiceImpl implements WorkflowAlertService{

	@Autowired
	WorkflowAlertRepository workFlowAlertRepository;

	@Autowired
	InitialSettingsRepository initialSettingsRepository;

	@Autowired
	UserGroupRepository userGroupRepository;

	@Autowired
	EmployeeProfileRepository empProfileRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	AlertCategoryRepository alertCategoryRepository;

	@Autowired
	EncounterEntityRepository encounterRepository;

	@Autowired
	EntityManagerFactory emf ;

	@PersistenceContext
	EntityManager em;

	/**
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List gettotaltimedetails(int toId,String startDateStr,String endDateStr) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		List<WorkflowReportsBean> wfCatTTimeList=new ArrayList<WorkflowReportsBean>();
		try
		{
			Date startDate=formatter.parse(startDateStr);
			Date endDate=formatter.parse(endDateStr);
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<Workflow> root = cq.from(Workflow.class);
			Join<Workflow, AlertCategory> categoryNameJoin=root.join("alertCategoryName",JoinType.LEFT);

			Expression<Date> startDateExpr=builder.function("date", Date.class, root.get(Workflow_.workflowStarttime));
			Expression<Date> endDateExpr=builder.function("date", Date.class, root.get(Workflow_.workflowEndtime));

			if(toId==-111)
			{
				cq.select(
						builder.construct(WorkflowReportsBean.class, root.get(Workflow_.workflowPatientid),
						root.get(Workflow_.workflowEncounterid),root.get(Workflow_.workflowStatus),
						categoryNameJoin.get(AlertCategory_.alertCategoryName),
						builder.least(root.get(Workflow_.workflowStarttime)),
						builder.greatest(root.get(Workflow_.workflowEndtime)),
						builder.function("to_char", String.class,builder.function("age", String.class, builder.greatest(root.get(Workflow_.workflowEndtime)),builder.least(root.get(Workflow_.workflowStarttime))),builder.literal("dd HH24:MI:ss"))
					/*	builder.sum(
							builder.prod(
									builder.diff(
											builder.function("day", Integer.class, builder.greatest(root.get(Workflow_.workflowEndtime))),
											builder.function("day", Integer.class, builder.least(root.get(Workflow_.workflowStarttime)))),
											builder.literal(3600)
								),
								builder.sum(
										builder.prod(
												builder.diff(
														builder.function("hour", Integer.class, builder.greatest(root.get(Workflow_.workflowEndtime))),
														builder.function("hour", Integer.class, builder.least(root.get(Workflow_.workflowStarttime)))),
														builder.literal(60)),

													builder.diff(
															builder.function("minute", Integer.class, builder.greatest(root.get(Workflow_.workflowEndtime))),
															builder.function("minute", Integer.class, builder.least(root.get(Workflow_.workflowStarttime))))
										)
								)*/
						))					
				.where(
						builder.and(builder.equal(root.get(Workflow_.workflowIsactive), false),
								builder.greaterThanOrEqualTo(startDateExpr, startDate),
								builder.lessThanOrEqualTo(endDateExpr, endDate)))
								.groupBy(root.get(Workflow_.workflowEncounterid),root.get(Workflow_.workflowStatus),root.get(Workflow_.workflowPatientid),categoryNameJoin.get(AlertCategory_.alertCategoryName))
								.orderBy(builder.asc(root.get(Workflow_.workflowStatus)));
			}
			else
			{
				cq.select(
						builder.construct(WorkflowReportsBean.class, 
								root.get(Workflow_.workflowPatientid),
								root.get(Workflow_.workflowEncounterid),
								root.get(Workflow_.workflowStatus),
								categoryNameJoin.get(AlertCategory_.alertCategoryName),
								builder.least(root.get(Workflow_.workflowStarttime)),
								builder.greatest(root.get(Workflow_.workflowEndtime)),
								builder.function("to_char", String.class,builder.function("age", String.class, builder.greatest(root.get(Workflow_.workflowEndtime)),builder.least(root.get(Workflow_.workflowStarttime))),builder.literal("dd HH24:MI:ss"))))
				.where(builder.and(builder.equal(root.get(Workflow_.workflowIsactive), false),builder.greaterThanOrEqualTo(root.get(Workflow_.workflowStarttime), startDate),builder.lessThanOrEqualTo(root.get(Workflow_.workflowEndtime), endDate)),builder.equal(root.get(Workflow_.workflowToid), toId))
				.groupBy(root.get(Workflow_.workflowEncounterid),root.get(Workflow_.workflowStatus),root.get(Workflow_.workflowPatientid),categoryNameJoin.get(AlertCategory_.alertCategoryName))
				.orderBy(builder.asc(root.get(Workflow_.workflowStatus)));
			}
			Query query=em.createQuery(cq);
			List<Object> workflowAlertList=query.getResultList();
			for(int j=0;j<workflowAlertList.size();j++){
				WorkflowReportsBean wfCategoryTTBean=(WorkflowReportsBean) workflowAlertList.get(j);
				String age=wfCategoryTTBean.getDifference();
				if(!(age.equals("")||age==null)){
					String[] splitAge=age.split(" ");
					Integer dayCount=new Integer(splitAge[0]);
					String[] splitTime=splitAge[1].split(":");
					Integer hourCount=new Integer(splitTime[0]);
					Integer minuteCount=new Integer(splitTime[1]);
					age=""+((dayCount*1440)+(hourCount*60)+minuteCount);
					wfCategoryTTBean.setDifference(age);
				}
				
				wfCatTTimeList.add(wfCategoryTTBean);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return wfCatTTimeList;
	}
	/**
	 * 
	 * @param userId
	 * @return used to get count of alert
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object> getAlertCount(int userId) {

		List alerts=new ArrayList<Integer>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		EmployeeProfile userInfo=empProfileRepository.findOne(EmployeeSpecification.getUserDetailsByUserId(userId));
		List<UserGroup> userGroupList=userGroupRepository.findAll(UserGroupSpecification.groupDetailsByUserId(userId));
		List<UserGroup> userGroup=userGroupRepository.findAll();
		int groupId=userInfo.getEmpProfileGroupid();
		List<Integer> toIds=new ArrayList<Integer>();
		toIds.add(userId);
		toIds.add(groupId);
		if(groupId==-1||groupId==-2||groupId==-6||groupId==-10)
		{
			toIds.add(-99);
		}
		for(int i=0;i<userGroupList.size();i++)
		{
			toIds.add(userGroupList.get(i).getGroupid());
		}
		for(int i=0;i<userGroup.size();i++)
		{
			if(userGroup.get(i).getUserid()==groupId)
			{
				toIds.add(userGroup.get(i).getGroupid());//in case of user id as group id
			}
		}
		Root<Workflow> root = cq.from(Workflow.class);
		Join<Workflow, EmployeeProfile> fromIdJoin=root.join("empProfileTableFromId",JoinType.LEFT);
		Join<Workflow, PatientRegistration> patientIdJoin=root.join("patRegPatientId",JoinType.LEFT);
		Join<Workflow, Room> roomIdJoin=root.join("roomTableRoomId",JoinType.LEFT);
		Join<Workflow, AlertCategory> statusNameJoin=root.join("alertCategoryName",JoinType.LEFT);
		Expression<Integer> exprToId=root.get(Workflow_.workflowToid);
		Predicate[] restrictions = new Predicate[] {
				exprToId.in(toIds)
		};


		cq.select(builder.construct(WorkflowBean.class
				,root.get(Workflow_.workflowId)
				,root.get(Workflow_.workflowPatientid)
				,root.get(Workflow_.workflowEncounterid)
				,root.get(Workflow_.workflowChartid)
				,root.get(Workflow_.workflowRoomid)
				,root.get(Workflow_.workflowFromid)
				,root.get(Workflow_.workflowToid)
				,root.get(Workflow_.workflowMessage)
				,root.get(Workflow_.workflowStatus)
				,root.get(Workflow_.workflowIsactive)
				,root.get(Workflow_.workflowIshighpriority)
				,root.get(Workflow_.workflowStarttime)
				,root.get(Workflow_.workflowEndtime)
				,builder.currentTimestamp()
				,root.get(Workflow_.workflowMessage)			//This a dummy value. Time difference should come here.
				,fromIdJoin.get(EmployeeProfile_.empProfileFullname)
				,fromIdJoin.get(EmployeeProfile_.empProfileFullname)
				,builder.coalesce(roomIdJoin.get(Room_.roomName),"-1")
				,builder.coalesce(statusNameJoin.get(AlertCategory_.alertCategoryName),"-")
				,builder.coalesce(statusNameJoin.get(AlertCategory_.alertCategoryUrl),"-")
				,builder.concat(builder.coalesce(patientIdJoin.get(PatientRegistration_.patientRegistrationLastName),builder.literal(" ")),
						builder.concat(builder.literal(", "), 
								builder.concat(builder.coalesce(patientIdJoin.get(PatientRegistration_.patientRegistrationFirstName),builder.literal(" ")), 
										builder.concat(builder.literal(" "), builder.coalesce(patientIdJoin.get(PatientRegistration_.patientRegistrationMidInitial),builder.literal(" "))))))			//Lastname, Firstname MidInitial
				))
				.where(builder.equal(root.get(Workflow_.workflowIsactive), true), builder.and(restrictions));

		Query query=em.createQuery(cq);

		List<Object> workflowAlertList=query.getResultList();
		alerts.add(workflowAlertList.size());

		return alerts;
	}

	/**
	 * 
	 * @param userId login user id
	 * @return list of alerts based on given user id
	 * @throws ParseException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List workflowAlertList(int userId)  throws ParseException {

		checkIsActiveEncounter();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		EmployeeProfile userInfo=empProfileRepository.findOne(EmployeeSpecification.getUserDetailsByUserId(userId));
		List<UserGroup> userGroupList=userGroupRepository.findAll(UserGroupSpecification.groupDetailsByUserId(userId));
		List<UserGroup> userGroup=userGroupRepository.findAll();
		int groupId=userInfo.getEmpProfileGroupid();
		List<Integer> toIds=new ArrayList<Integer>();
		toIds.add(userId);
		toIds.add(groupId);
		if(groupId==-1||groupId==-2||groupId==-6||groupId==-10)
		{
			toIds.add(-99);
		}
		for(int i=0;i<userGroupList.size();i++)
		{
			toIds.add(userGroupList.get(i).getGroupid());
		}
		for(int i=0;i<userGroup.size();i++)
		{
			if(userGroup.get(i).getUserid()==groupId)
			{
				toIds.add(userGroup.get(i).getGroupid());//in case of user id as group id
			}
		}
		Root<Workflow> root = cq.from(Workflow.class);
		Join<Workflow, EmployeeProfile> fromIdJoin=root.join("empProfileTableFromId",JoinType.LEFT);
		Join<Workflow, PatientRegistration> patientIdJoin=root.join("patRegPatientId",JoinType.LEFT);
		Join<Workflow, Room> roomIdJoin=root.join("roomTableRoomId",JoinType.LEFT);
		Join<Workflow, AlertCategory> statusNameJoin=root.join("alertCategoryName",JoinType.LEFT);
		Expression<Integer> exprToId=root.get(Workflow_.workflowToid);
		Predicate[] restrictions = new Predicate[] {
				exprToId.in(toIds)
		};


		List<WorkflowBean> workflowBeans=new ArrayList<WorkflowBean>();

		cq.select(builder.construct(WorkflowBean.class
				,root.get(Workflow_.workflowId)
				,root.get(Workflow_.workflowPatientid)
				,root.get(Workflow_.workflowEncounterid)
				,root.get(Workflow_.workflowChartid)
				,root.get(Workflow_.workflowRoomid)
				,root.get(Workflow_.workflowFromid)
				,root.get(Workflow_.workflowToid)
				,root.get(Workflow_.workflowMessage)
				,root.get(Workflow_.workflowStatus)
				,root.get(Workflow_.workflowIsactive)
				,root.get(Workflow_.workflowIshighpriority)
				,root.get(Workflow_.workflowStarttime)
				,root.get(Workflow_.workflowEndtime)
				,builder.currentTimestamp()
				,root.get(Workflow_.workflowMessage)			//This a dummy value. Time difference should come here.
				,fromIdJoin.get(EmployeeProfile_.empProfileFullname)
				,fromIdJoin.get(EmployeeProfile_.empProfileFullname)
				,builder.coalesce(roomIdJoin.get(Room_.roomName),"-1")
				,builder.coalesce(statusNameJoin.get(AlertCategory_.alertCategoryName),"-")
				,builder.coalesce(statusNameJoin.get(AlertCategory_.alertCategoryUrl),"-")
				,builder.concat(builder.coalesce(patientIdJoin.get(PatientRegistration_.patientRegistrationLastName),builder.literal(" ")),
						builder.concat(builder.literal(", "), 
								builder.concat(builder.coalesce(patientIdJoin.get(PatientRegistration_.patientRegistrationFirstName),builder.literal(" ")), 
										builder.concat(builder.literal(" "), builder.coalesce(patientIdJoin.get(PatientRegistration_.patientRegistrationMidInitial),builder.literal(" "))))))			//Lastname, Firstname MidInitial
				))
				.where(builder.equal(root.get(Workflow_.workflowIsactive), true), builder.and(restrictions))
				.orderBy(builder.asc(root.get(Workflow_.workflowStarttime)));
		Query query=em.createQuery(cq);

		List<Object> workflowAlertList=query.getResultList();


		for(int j=0;j<workflowAlertList.size();j++){
			String receiverName=" ";
			String createdDateTime="";

			WorkflowBean wfb=(WorkflowBean) workflowAlertList.get(j);

			//HH converts hour in 24 hours format (0-23), day calculation
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date dateFromDb = null;

			final String OLD_FORMAT3 =  "yyyy-MM-dd HH:mm:ss";

			SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT3);
			Date currentDate = sdf.parse(wfb.getCurrentmilliseconds()+"");
			dateFromDb = format.parse(wfb.getWorkflowStarttime()+"");
			//in milliseconds
			long diff = currentDate.getTime() - dateFromDb.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			String timeBuffer="";

			String totalWaitingTime= " ("+getTotalTime(wfb.getWorkflowPatientid(),wfb.getCurrentmilliseconds())+")";
			

			if(diffDays>0){
				timeBuffer=diffDays+(diffDays==1?" day":" days");
			}
			else if(diffHours>0){
				timeBuffer=diffHours+(diffHours==1?" hr":" hrs");
			}
			else if(diffMinutes>0){
				timeBuffer=diffMinutes+(diffMinutes==1?" min":" mins");
			}
			else if(diffSeconds>0){
				timeBuffer=diffSeconds+(diffSeconds==1?" sec":" secs");
			}

			createdDateTime=timeBuffer+totalWaitingTime;		
			wfb.setTimeDifference(createdDateTime);			
			int toId=wfb.getWorkflowToid();
			if((toId==-99)||(toId==-1)||(toId==-2)||(toId==-3)||(toId==-4)||(toId==-5)||(toId==-6)||(toId==-7)||(toId==-10)||(toId==-11)||(toId==-15)||(toId==-25))
			{
				if(toId==-99)
					receiverName="All Clinical User";
				else if(toId==-1)
					receiverName="Doctors";
				else if(toId==-2)
					receiverName="Nurse";
				else if(toId==-3)
					receiverName="Front Desk";
				else if(toId==-4)
					receiverName="Online User";
				else if(toId==-5)
					receiverName="Admin";
				else if(toId==-6)
					receiverName="MA";
				else if(toId==-7)
					receiverName="Office Manager";
				else if(toId==-10)
					receiverName="Nurse Practitioner";
				else if(toId==-11)
					receiverName="CCR";
				else if(toId==-15)
					receiverName="SSOCCR";
				else if(toId==-25)
					receiverName="Case Manager";
			}

			wfb.setReceiverName(receiverName);
			workflowBeans.add(wfb);
		}
		return workflowBeans;
	}
	//To set workflow isactive as false if encounter is not opened
	private void checkIsActiveEncounter(){
		List<Workflow> activeEncounter=workFlowAlertRepository.findAll(WorkflowAlertSpecification.getClosedEncounterWFAlerts());
		
		for(int i=0;i<activeEncounter.size();i++){
			Workflow alert=activeEncounter.get(i);
			alert.setWorkflowIsactive(false);
			workFlowAlertRepository.saveAndFlush(alert);
		}
		
		//Used to inactive more than one alerts for a patient
		Map<Integer,Integer> alertsMap=new HashMap<Integer,Integer>();
		List<Integer> patientIds=new ArrayList<Integer>();
		List<Workflow> activeAlerts=workFlowAlertRepository.findAll(WorkflowAlertSpecification.getActiveAlerts());
		
		for(int i=0;i<activeAlerts.size();i++){
			Workflow alert=activeAlerts.get(i);
			int patientId=alert.getWorkflowPatientid();
			int workflowId=alert.getWorkflowId();
			
			if(!patientIds.contains(patientId)){
				patientIds.add(patientId);
			}else{
				int alertId=alertsMap.get(patientId);
				Workflow activeAlert=workFlowAlertRepository.findOne(WorkflowAlertSpecification.getAlertByAlertId(alertId));
				activeAlert.setWorkflowIsactive(false);
				workFlowAlertRepository.saveAndFlush(activeAlert);
			}
			alertsMap.put(patientId, workflowId);
		}
	}
	
	private String getTotalTime(Integer patientId, Date currentTime) {
		String timeBuffer="";
		Timestamp minimum=null;
		Workflow activeAlert=workFlowAlertRepository.findOne(WorkflowAlertSpecification.getAlertByPatientId(patientId));
		int activeEncId=activeAlert.getWorkflowEncounterid();
		List<Workflow> alertList=workFlowAlertRepository.findAll(WorkflowAlertSpecification.getAllAlertByPatientId(patientId,activeEncId));
		for(int i=0;i<alertList.size();i++){
			Workflow wf=alertList.get(i);
			if(minimum==null)
				minimum=wf.getWorkflowStarttime();
			else if(minimum.after(wf.getWorkflowStarttime()))
				minimum=wf.getWorkflowStarttime();
		}

		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date dateFromDb = null;
		Date currentDate = null;

		final String OLD_FORMAT3 =  "yyyy-MM-dd HH:mm:ss";

		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT3);
		try {
			currentDate = sdf.parse(currentTime+"");
			dateFromDb = format.parse(minimum+"");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//in milliseconds
		long diff = currentDate.getTime() - dateFromDb.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		if(diffDays>0){
			timeBuffer=diffDays+(diffDays==1?" day":" days");
		}
		else if(diffHours>0){
			timeBuffer=diffHours+(diffHours==1?" hr":" hrs");
		}
		else if(diffMinutes>0){
			timeBuffer=diffMinutes+(diffMinutes==1?" min":" mins");
		}
		else if(diffSeconds>0){
			timeBuffer=diffSeconds+(diffSeconds==1?" sec":" secs");
		}
		
		return timeBuffer;
	}

	/**
	 * 
	 * @return list of status, which having isworkflow status is true 
	 */
	@Override
	public List<AlertCategory> getWorkflowCategory() {
		List<AlertCategory> alertCategory=alertCategoryRepository.findAll(AlertCategorySpecification.getWorkflowCategory());
		return alertCategory;
	}

	/**
	 * 
	 * @param name
	 * @param url
	 * @return newly inserted status
	 */
	@Override
	public AlertCategory insertStatus(String name, String status) {
		int flag=0;
		List<AlertCategory> categoryName=alertCategoryRepository.findAll(AlertCategorySpecification.getWorkflowCategory());
		for(int i=0;i<categoryName.size();i++)
		{
			if(name.equalsIgnoreCase(categoryName.get(i).getAlertCategoryName()))
				flag=1;
		}
		String url="";
		status=status.replaceAll(" ",	"");
		if(status.trim().equalsIgnoreCase("chart"))
			url="/jsp/chart/patientdetails/Patient_Chart.Action";
		else
			if(status.trim().equalsIgnoreCase("checkin"))
				url="/jsp/chart/patientdetails/Patient_Chart.Action";
			else
				if(status.trim().equalsIgnoreCase("checkout"))
					url="/jsp/chart/CompleteVisit.Action";
				else
					if(status.trim().equalsIgnoreCase("review"))
						url="/jsp/alerts/HL7LabResultAction.Action";
					else
						if(status.trim().equalsIgnoreCase("patientdocument"))
							url="/jsp/chart/patientdetails/Patient_Chart.Action";
						else
							if(status.trim().equalsIgnoreCase("investigation"))
								url="/jsp/chart/labsandtests/QuickReviewInvestigation.Action";
							else
								if(status.trim().equalsIgnoreCase("prescription"))
									url="/jsp/chart/prescription/ElectronicPrescription/ShowStatusMessage.Action";

		AlertCategory aug=null;
		if(flag==0)
		{
			aug=new AlertCategory();
			int maxId=alertCategoryRepository.findAll(new PageRequest(0, 1,Direction.DESC,"alertCategoryId")).getContent().get(0).getAlertCategoryId()+1;
			aug.setAlertCategoryId(maxId);
			aug.setAlertCategoryName(name);
			aug.setAlertCategoryUrl(url);
			aug.setAlertCategoryDisplayName(name);
			aug.setAlertCategoryDisplayOrder(maxId);
			aug.setAlertCategoryGroup(4);
			aug.setAlertCategoryType(11);
			aug.setAlertCategorySection(1);
			aug.setAlertCategoryActionMap(-1);
			aug.setAlertCategorySubpage(-1);
			aug.setAlertCategoryQrFlag(0);
			aug.setAlertCategoryDefaultUser(-1);
			aug.setAlertCategoryIsIndividualAlert(false);
			aug.setAlertCategoryIsworkflow(true);
			alertCategoryRepository.save(aug);
			aug.setAlertCategoryUrl(status);
		}
		return aug;
	}

	/**
	 * method is used to delete the status based on categoryid
	 */
	@Override
	public void deleteStatus(int categoryId) {
		List<AlertCategory> category=alertCategoryRepository.findAll();
		int flag=0;
		for(int i=0;i<category.size();i++)
		{
			if(categoryId==category.get(i).getAlertCategoryId())
			{
				flag=1;	
			}
		}
		if(flag==1)
		{
			AlertCategory activeCategory=alertCategoryRepository.findOne(AlertCategorySpecification.getWorkflowCategoryById(categoryId));	
			activeCategory.setAlertCategoryIsworkflow(false);//it will inactive the status
			alertCategoryRepository.saveAndFlush(activeCategory);
		}
	}

	/**
	 * 
	 * @param categoryId
	 * @param name
	 * @param url
	 * @return updated status based on category id
	 */
	@Override
	public AlertCategory updateStatus(int categoryId, String categoryName,String url) {
		List<AlertCategory> categoryList=alertCategoryRepository.findAll();
		AlertCategory category=null;
		int flagId=0;
		int flagName=0;

		url=url.replaceAll(" ",	"");
		if(url.trim().equalsIgnoreCase("-1"))
			url="/jsp/chart/patientdetails/Patient_Chart.Action";
		else
			if(url.trim().equalsIgnoreCase("chart"))
				url="/jsp/chart/patientdetails/Patient_Chart.Action";
			else
				if(url.trim().equalsIgnoreCase("checkin"))
					url="/jsp/chart/patientdetails/Patient_Chart.Action";
				else
					if(url.trim().equalsIgnoreCase("checkout"))
						url="/jsp/chart/CompleteVisit.Action";
					else
						if(url.trim().equalsIgnoreCase("review"))
							url="/jsp/alerts/HL7LabResultAction.Action";
						else
							if(url.trim().equalsIgnoreCase("patientdocument"))
								url="/jsp/chart/patientdetails/Patient_Chart.Action";
							else
								if(url.trim().equalsIgnoreCase("investigation"))
									url="/jsp/chart/labsandtests/QuickReviewInvestigation.Action";
								else
									if(url.trim().equalsIgnoreCase("prescription"))
										url="/jsp/chart/prescription/ElectronicPrescription/ShowStatusMessage.Action";

		for(int i=0;i<categoryList.size();i++)
		{
			if(categoryId==categoryList.get(i).getAlertCategoryId())
			{
				flagId=1;	
			}
		}
		if(flagId==1)
		{
			for(int i=0;i<categoryList.size();i++)
			{
				if(categoryName.equalsIgnoreCase(categoryList.get(i).getAlertCategoryName()))
				{
					flagName=1;
				}
			}
			category=alertCategoryRepository.findOne(AlertCategorySpecification.getWorkflowCategoryById(categoryId));

			if(category.getAlertCategoryName().equalsIgnoreCase(categoryName))
			{
				flagName=0;
			}
			if((flagName==0)&&(flagId==1))
			{
				category.setAlertCategoryName(categoryName);
				category.setAlertCategoryDisplayName(categoryName);
				category.setAlertCategoryUrl(url);
				alertCategoryRepository.save(category);
			}
		}
		return category;
	}

	/**
	 * method is used to create new alert based on given details
	 * @param patientId
	 * @param encounterId
	 * @param chartId
	 * @param roomId
	 * @param fromId
	 * @param toId
	 * @param msg
	 * @param status
	 * @param isHighPriority
	 * @return newly inserted alert
	 */
	@Override
	public Workflow insertAlert(int patientId, int encounterId, int chartIdFromreq,
			int roomIdFromreq, int fromId, int toId, String msg, int status,
			boolean isHighPriority) {
		//List<Workflow> alertList=workFlowAlertRepository.findAll();
		CriteriaBuilder alertListBuilder = em.getCriteriaBuilder();
		CriteriaQuery<WorkflowBean> alertListcq = alertListBuilder.createQuery(WorkflowBean.class);
		Root<Workflow> workflowRoot = alertListcq.from(Workflow.class);
		alertListcq.multiselect(workflowRoot.get(Workflow_.workflowPatientid),
				workflowRoot.get(Workflow_.workflowEncounterid));
		List<WorkflowBean> alertList = em.createQuery(alertListcq).getResultList();
		
		
		CriteriaBuilder chartIdBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Object> chartIdcq = chartIdBuilder.createQuery();
		Root<Chart> chartIdroot = chartIdcq.from(Chart.class);
		chartIdcq.select(chartIdroot.get(Chart_.chartId)).where(chartIdBuilder.equal(chartIdroot.get(Chart_.chartPatientid), patientId));
		List<Object> chartIdList = em.createQuery(chartIdcq).getResultList();
		
		Short roomId = null;
		Integer chartId = null;
		
		if(roomIdFromreq == -1){
			CriteriaBuilder roomIdBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Object> roomIdcq = roomIdBuilder.createQuery();
			Root<Encounter> roomIdroot = roomIdcq.from(Encounter.class);
			roomIdcq.select(roomIdroot.get(Encounter_.encounterRoom)).where(roomIdBuilder.equal(roomIdroot.get(Encounter_.encounterId), encounterId));
			List<Object> roomIdList = em.createQuery(roomIdcq).getResultList();
			
				if(roomIdList.size()>0){
					roomId = roomIdList.get(0) != null? (Short) roomIdList.get(0):-1;
				}
				else
				roomId  =-1;
		}
		else{
				roomId = (short) roomIdFromreq;
		}
		
		if(chartIdList.size()>0)
			chartId = (Integer) chartIdList.get(0);
		

		int patientIdFlag=0,encounterIdFlag=0;
		Workflow alert=null;
		for(int i=0;i<alertList.size();i++)
		{
			if(patientId==alertList.get(i).getWorkflowPatientid())
			{
				patientIdFlag=1;
			}
			if(encounterId==alertList.get(i).getWorkflowEncounterid())
			{
				encounterIdFlag=1;
			}
		}
		if((encounterIdFlag==1)||(patientIdFlag==1))
		{
			List<Workflow> activeAlertList=workFlowAlertRepository.findAll(WorkflowAlertSpecification.getAlertByPatientId(patientId));//it will get active alerts for given patient id
			int count=activeAlertList.size();
			if(count<1){ //if encounter is already checkout
				
				alert=createAlert(patientId, encounterId, chartId, roomId, fromId, toId, msg, status, isHighPriority);
			}else if(count==1){
				
				Workflow activeAlert=activeAlertList.get(0);
				int lastAlertStatus=activeAlert.getWorkflowStatus();
				if(lastAlertStatus==status)//if status is not changed
				{
					activeAlert.setWorkflowToid(toId);
					activeAlert.setWorkflowFromid(fromId);
					activeAlert.setWorkflowRoomid(Integer.parseInt(roomId.toString()));
					activeAlert.setWorkflowIshighpriority(isHighPriority);
					activeAlert.setWorkflowMessage(msg);
					workFlowAlertRepository.saveAndFlush(activeAlert);
					alert=activeAlert;

				}
				else
				{
					activeAlert.setWorkflowIsactive(false);
					activeAlert.setWorkflowEndtime(new Timestamp(new Date().getTime()));
					workFlowAlertRepository.saveAndFlush(activeAlert);
					alert=createAlert(patientId, encounterId, chartId, roomId, fromId, toId, msg, status, isHighPriority);
				}
			}else if(count>1){
				
				for(int i=0;i<count-1;i++){//Inactive wrong workflow active alerts
					Workflow activeAlert=activeAlertList.get(i);
					activeAlert.setWorkflowIsactive(false);
					activeAlert.setWorkflowEndtime(new Timestamp(new Date().getTime()));
					workFlowAlertRepository.saveAndFlush(activeAlert);
				}
				
				Workflow activeAlert=activeAlertList.get(count-1);
				int lastAlertStatus=activeAlert.getWorkflowStatus();
				if(lastAlertStatus==status)//if status is not changed
				{
					activeAlert.setWorkflowToid(toId);
					activeAlert.setWorkflowFromid(fromId);
					activeAlert.setWorkflowRoomid(Integer.parseInt(roomId.toString()));
					activeAlert.setWorkflowIshighpriority(isHighPriority);
					activeAlert.setWorkflowMessage(msg);
					workFlowAlertRepository.saveAndFlush(activeAlert);
					alert=activeAlert;

				}
				else
				{
					activeAlert.setWorkflowIsactive(false);
					activeAlert.setWorkflowEndtime(new Timestamp(new Date().getTime()));
					workFlowAlertRepository.saveAndFlush(activeAlert);
					alert=createAlert(patientId, encounterId, chartId, roomId, fromId, toId, msg, status, isHighPriority);
				}
			}
		}
		else
		{
			alert=createAlert(patientId, encounterId, chartId, roomId, fromId, toId, msg, status, isHighPriority);
		}
		return alert;
	}

	public Workflow createAlert(int patientId, int encounterId, int chartId,
			int roomId, int fromId, int toId, String msg, int status,
			boolean isHighPriority) {
		Workflow alert=new Workflow();
		alert.setWorkflowChartid(chartId);
		alert.setWorkflowEncounterid(encounterId);
		alert.setWorkflowFromid(fromId);
		alert.setWorkflowIsactive(true);
		alert.setWorkflowIshighpriority(isHighPriority);
		alert.setWorkflowMessage(msg);
		alert.setWorkflowStatus(status);
		alert.setWorkflowPatientid(patientId);
		alert.setWorkflowRoomid(roomId);
		//		updateEncounterRoom(encounterId,roomId);//To update encounter room id in encounter_entity table
		alert.setWorkflowToid(toId);
		alert.setWorkflowStarttime(currentTimeStamp());
		workFlowAlertRepository.save(alert);
		return alert;
	}

	Timestamp currentTimeStamp(){

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();

		Root<Workflow> root = cq.from(Workflow.class);
		cq.select(builder.currentTimestamp());

		Query query=em.createQuery(cq);

		List currentTimeStamp=query.getResultList();


		final String DATE_FORMAT =  "yyyy-MM-dd HH:mm:ss";
		Date currentDate=null;

		try{
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			currentDate = sdf.parse(currentTimeStamp.get(0).toString());
		}
		catch(Exception e){
			currentDate=new Date();
		}



		Timestamp timeStamp=new Timestamp(currentDate.getTime());

		return timeStamp;
	}

	/**
	 * 
	 * @param alertId
	 * @param isHighPriority
	 * @return alert with updated status of ishighlight 
	 */
	@Override
	public Workflow highlightAlert(int alertId, boolean isHighPriority) {
		Workflow alert=workFlowAlertRepository.findOne(WorkflowAlertSpecification.getAlertByAlertId(alertId));
		alert.setWorkflowIshighpriority(isHighPriority);
		workFlowAlertRepository.save(alert);
		return alert;
	}

	/**
	 * this method is used to update the old alert, if status is -1 means it will update only old alert else it'll update old alert and create new alert also.
	 * @param alertId
	 * @param forwardToId
	 * @param statusId
	 * @return newly created alert from old alert
	 */
	@Override
	public Workflow fowardAlert(int alertId, int fromId,int forwardToId, int statusId,int roomId,String isHighpriority,String msg) {
		Workflow lastAlert=workFlowAlertRepository.findOne(WorkflowAlertSpecification.getAlertByAlertId(alertId));
		int lastStatusId=lastAlert.getWorkflowStatus();
		int lastAlertToid=lastAlert.getWorkflowToid();
		int lastAlertFromId=lastAlert.getWorkflowFromid();
		int encounterId=lastAlert.getWorkflowEncounterid();
		List<EmployeeProfile> empList=empProfileRepository.findAll();
		Set<Integer> empGroupIdSet = new HashSet<Integer>();
		for(int i=0;i<empList.size();i++)
		{
			empGroupIdSet.add(empList.get(i).getEmpProfileGroupid());
		}
		if(empGroupIdSet.contains(lastAlert.getWorkflowToid())) //if toid is groupid
		{
			if(fromId==forwardToId) // if fromid and toid both are same
			{		
				if(lastAlertToid==forwardToId) //if toid not changed
				{
					if(statusId==-1||statusId==lastStatusId)//if status not changed
					{
						lastAlert.setWorkflowToid(forwardToId);
						if(isHighpriority.equalsIgnoreCase("true")||isHighpriority.equalsIgnoreCase("false"))
							lastAlert.setWorkflowIshighpriority(Boolean.parseBoolean(isHighpriority));
						if(roomId!=-1)
						{
							lastAlert.setWorkflowRoomid(roomId);
							//						updateEncounterRoom(encounterId,roomId);
						}
						if(!msg.equalsIgnoreCase("-1"))
							lastAlert.setWorkflowMessage(msg);
						workFlowAlertRepository.save(lastAlert);
						return lastAlert;
					}
					else
					{
						return updateAndCreateWorkflowAlert(lastAlert, alertId, fromId, forwardToId, statusId, roomId, isHighpriority, msg, encounterId);	
					}
				}
				else
				{
					if(statusId==-1||statusId==lastStatusId)//if status not changed
					{
						return updateAndCreateWorkflowAlert(lastAlert, alertId, lastAlertFromId, forwardToId, lastStatusId, roomId, isHighpriority, msg, encounterId);
					}
					else
					{
						return updateAndCreateWorkflowAlert(lastAlert, alertId, lastAlertFromId, forwardToId, statusId, roomId, isHighpriority, msg, encounterId);
					}
				}
			}
			else
			{

				if(lastAlertToid==forwardToId) //if toid not changed
				{
					if(statusId==-1||statusId==lastStatusId)//if status not changed
					{
						lastAlert.setWorkflowFromid(fromId);
						lastAlert.setWorkflowToid(forwardToId);
						if(isHighpriority.equalsIgnoreCase("true")||isHighpriority.equalsIgnoreCase("false"))
							lastAlert.setWorkflowIshighpriority(Boolean.parseBoolean(isHighpriority));
						if(roomId!=-1)
						{
							lastAlert.setWorkflowRoomid(roomId);
							//						updateEncounterRoom(encounterId,roomId);
						}
						if(!msg.equalsIgnoreCase("-1"))
							lastAlert.setWorkflowMessage(msg);
						workFlowAlertRepository.save(lastAlert);
						return lastAlert;
					}
					else
					{
						if(statusId==-1||statusId==lastStatusId)//if status not changed
						{
							return updateAndCreateWorkflowAlert(lastAlert, alertId, fromId, forwardToId, lastStatusId, roomId, isHighpriority, msg, encounterId);
						}
						else
						{
							return updateAndCreateWorkflowAlert(lastAlert, alertId, fromId, forwardToId, statusId, roomId, isHighpriority, msg, encounterId);
						}	
					}
				}
				else
				{
					if(statusId==-1||statusId==lastStatusId)//if status not changed
					{
						return updateAndCreateWorkflowAlert(lastAlert, alertId, fromId, forwardToId, lastStatusId, roomId, isHighpriority, msg, encounterId);
					}
					else
					{
						return updateAndCreateWorkflowAlert(lastAlert, alertId, fromId, forwardToId, statusId, roomId, isHighpriority, msg, encounterId);
					}	
				}
			}
		}
		else
		{
			if(fromId!=forwardToId)
			{
				if(lastAlertToid==forwardToId) //if toid not changed
				{
					if(statusId==-1||statusId==lastStatusId)//if status not changed
					{
						lastAlert.setWorkflowFromid(fromId);
						lastAlert.setWorkflowToid(forwardToId);
						if(isHighpriority.equalsIgnoreCase("true")||isHighpriority.equalsIgnoreCase("false"))
							lastAlert.setWorkflowIshighpriority(Boolean.parseBoolean(isHighpriority));
						if(roomId!=-1)
						{
							lastAlert.setWorkflowRoomid(roomId);
							//						updateEncounterRoom(encounterId,roomId);
						}
						if(!msg.equalsIgnoreCase("-1"))
							lastAlert.setWorkflowMessage(msg);
						workFlowAlertRepository.save(lastAlert);
						return lastAlert;
					}
					else
					{
						return updateAndCreateWorkflowAlert(lastAlert, alertId, fromId, forwardToId, statusId, roomId, isHighpriority, msg, encounterId);	
					}
				}
				else
				{
					if(statusId==-1||statusId==lastStatusId)//if status not changed
					{
						return updateAndCreateWorkflowAlert(lastAlert, alertId, fromId, forwardToId, lastStatusId, roomId, isHighpriority, msg, encounterId);
					}
					else
					{
						return updateAndCreateWorkflowAlert(lastAlert, alertId, fromId, forwardToId, statusId, roomId, isHighpriority, msg, encounterId);
					}
				}
			}	
			else
			{
				if(lastAlertToid==forwardToId) //if toid not changed
				{
					if(statusId==-1||statusId==lastStatusId)//if status not changed
					{
						if(isHighpriority.equalsIgnoreCase("true")||isHighpriority.equalsIgnoreCase("false"))
							lastAlert.setWorkflowIshighpriority(Boolean.parseBoolean(isHighpriority));
						if(roomId!=-1)
						{
							lastAlert.setWorkflowRoomid(roomId);
							//						updateEncounterRoom(encounterId,roomId);
						}
						if(!msg.equalsIgnoreCase("-1"))
							lastAlert.setWorkflowMessage(msg);
						workFlowAlertRepository.save(lastAlert);
						return lastAlert;
					}
					else
					{
						return updateAndCreateWorkflowAlert(lastAlert, alertId, fromId, forwardToId, statusId, roomId, isHighpriority, msg, encounterId);	
					}
				}
				else
				{
					if(statusId==-1||statusId==lastStatusId)//if status not changed
					{
						return updateAndCreateWorkflowAlert(lastAlert, alertId, fromId, forwardToId, lastStatusId, roomId, isHighpriority, msg, encounterId);
					}
					else
					{
						return updateAndCreateWorkflowAlert(lastAlert, alertId, fromId, forwardToId, statusId, roomId, isHighpriority, msg, encounterId);
					}
				}
			}
		}
	}

	/**
	 * @return active room details
	 */
	@Override
	public List<Room> getRoomDetails() {
		List<Room> roomList=roomRepository.findAll(WorkflowAlertSpecification.getRoomDetails());
		return roomList;
	}
	/**
	 * this is used to inactive an alert
	 * @param alertId
	 */
	@Override
	public Workflow closeAlert(int patientId) {
		Workflow alert=workFlowAlertRepository.findOne(WorkflowAlertSpecification.getAlertByPatientId(patientId));
		if(alert!=null)
		{
			alert.setWorkflowIsactive(false);
			workFlowAlertRepository.save(alert);
			return alert;
		}else{
			return null;
		}
		
	}

	/**
	 * To create new alert in workflow table
	 * @param lastAlert old alert
	 * @param fromId
	 * @param forwardToId
	 * @param statusId
	 * @param isHighpriority
	 * @param msg
	 * @param roomId
	 * @param encounterId
	 */
	public Workflow newAlert(Workflow lastAlert,int fromId,int forwardToId,int statusId,String isHighpriority,String msg,int roomId,int encounterId)
	{
		Workflow newAlert=new Workflow();
		newAlert.setWorkflowChartid(lastAlert.getWorkflowChartid());
		newAlert.setWorkflowEncounterid(lastAlert.getWorkflowEncounterid());
		newAlert.setWorkflowFromid(fromId);
		newAlert.setWorkflowIsactive(true);
		if(isHighpriority.equalsIgnoreCase("true")||isHighpriority.equalsIgnoreCase("false"))
			newAlert.setWorkflowIshighpriority(Boolean.parseBoolean(isHighpriority));
		else
			newAlert.setWorkflowIshighpriority(lastAlert.getWorkflowIshighpriority());
		if(!msg.equalsIgnoreCase("-1"))
			newAlert.setWorkflowMessage(msg);
		else
			newAlert.setWorkflowMessage(lastAlert.getWorkflowMessage());
		newAlert.setWorkflowPatientid(lastAlert.getWorkflowPatientid());
		if(roomId!=-1)
		{
			newAlert.setWorkflowRoomid(roomId);
			//			updateEncounterRoom(encounterId,roomId);
		}
		else
		{
			newAlert.setWorkflowRoomid(lastAlert.getWorkflowRoomid());
			//			updateEncounterRoom(encounterId,lastAlert.getWorkflowRoomid());
		}
		newAlert.setWorkflowStarttime(currentTimeStamp());
		newAlert.setWorkflowStatus(statusId);
		newAlert.setWorkflowToid(forwardToId);
		workFlowAlertRepository.save(newAlert);
		return newAlert;
	}

	/**
	 * To update encounter room id in encounter_entity table when ever updating room id
	 * @param encounterId
	 * @param roomId
	 */
	public void updateEncounterRoom(int encounterId,int roomId)
	{
		Encounter encounterEntity=encounterRepository.findOne(EncounterEntitySpecification.EncounterById(new Integer(encounterId)));
		encounterEntity.setEncounterRoom((short) roomId);
		encounterEntity.setEncounterRoomIsactive(true);
		encounterRepository.saveAndFlush(encounterEntity);
	}

	@Override
	public boolean getWorkflowConfigStatus(String optionName) {
		InitialSettings data=initialSettingsRepository.findOne(InitialSettingsSpecification.optionName(optionName));
		boolean status=false;
		if(data!=null)
		{
			if(data.getInitialSettingsOptionValue().equalsIgnoreCase("1"))
				status=true;

		}
		return status;
	}

	public Workflow updateAndCreateWorkflowAlert(Workflow lastAlert,int alertId, int fromId,int forwardToId, int statusId,int roomId,String isHighpriority,String msg,int encounterId)
	{
		lastAlert.setWorkflowEndtime(currentTimeStamp());
		lastAlert.setWorkflowIsactive(false);//change the alert status so this alert won't come in home page
		workFlowAlertRepository.saveAndFlush(lastAlert);
		return newAlert(lastAlert,fromId,forwardToId,statusId,isHighpriority, msg,roomId,encounterId);


	}

	@Override
	public Workflow workflowAlertListByencounterId(int encounterId) {
		Workflow alerts=workFlowAlertRepository.findOne(WorkflowAlertSpecification.getAllAlertsByEncounterId(encounterId));
		if(alerts==null)
			return null;
		else
			return alerts;
	}

	@Override
	public Workflow workflowAlertListById(int workflowId) {
		Workflow alert=workFlowAlertRepository.findOne(WorkflowAlertSpecification.getAlertByAlertId(workflowId));
		return alert;
	}

}
