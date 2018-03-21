package com.glenwood.glaceemr.server.application.services.fax;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.DoctorSign;
import com.glenwood.glaceemr.server.application.models.DoctorSign_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.FaxInbox;
import com.glenwood.glaceemr.server.application.models.FaxInbox_;
import com.glenwood.glaceemr.server.application.models.FaxOutbox;
import com.glenwood.glaceemr.server.application.models.FaxOutbox_;
import com.glenwood.glaceemr.server.application.models.LoginUsers;
import com.glenwood.glaceemr.server.application.models.LoginUsers_;
import com.glenwood.glaceemr.server.application.repositories.DoctorsignRepository;
import com.glenwood.glaceemr.server.application.repositories.EmployeeProfileRepository;
import com.glenwood.glaceemr.server.application.repositories.FaxInboxRepository;
import com.glenwood.glaceemr.server.application.repositories.FaxOutboxRepository;
import com.glenwood.glaceemr.server.application.specifications.FaxSpecification;
import com.glenwood.glaceemr.server.application.models.FaxBox;
import com.glenwood.glaceemr.server.application.repositories.FaxBoxRepository;



/**
 * Service implementation for Fax Controller
 * @author Anil Kumar
 *
 */
@Service
public  class FaxServiceImpl implements FaxService{

	@Autowired
	EntityManager entityManager;
	
	

	@Autowired
	FaxOutboxRepository fax_outboxRepository;

	@Autowired
	FaxInboxRepository fax_inboxRepository;

	@Autowired
	EmployeeProfileRepository employeeProfileRepository;

	@Autowired
	DoctorsignRepository doctorsignRepository;

	@Autowired
	FaxBoxRepository faxBoxRepository;
	
	@Autowired
	EntityManagerFactory emf ;

	@SuppressWarnings("unchecked")
	@Override
	public List<FaxOutbox> getOutboxDetails(Integer fax_outbox_folderid, Integer userId,Integer pageNo,Integer pageSize){

		entityManager = emf.createEntityManager();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<FaxOutbox> root = cq.from(FaxOutbox.class);

		List<FaxOutbox> getOutboxDetails=new ArrayList<FaxOutbox>();
		try {
			cq.select(builder.construct(FaxOutboxBean.class, 
					root.get(FaxOutbox_.fax_outbox_id).alias("faxId"),
					builder.coalesce(root.get(FaxOutbox_.fax_outbox_subject), ""),
					root.get(FaxOutbox_.fax_outbox_htmlfile).alias("faxFileName"),
					root.get(FaxOutbox_.fax_outbox_folderid).alias("folderId"),
					root.get(FaxOutbox_.fax_outbox_scheduledtime).alias("scheduleTime"),
					root.get(FaxOutbox_.fax_outbox_dispatchtime).alias("actualDespatchtime"),
					root.get(FaxOutbox_.fax_outbox_statusid).alias("statusId"),
					root.get(FaxOutbox_.fax_outbox_recipientnumber).alias("recipientNumber"),
					root.get(FaxOutbox_.fax_outbox_recipientname).alias("recipientName"),
					root.get(FaxOutbox_.fax_outbox_forwardedto).alias("forwardedToUserId"),
					root.get(FaxOutbox_.fax_outbox_createdby).alias("createdBy"),
					root.get(FaxOutbox_.fax_outbox_createddate).alias("createdDate"),
					builder.coalesce(root.get(FaxOutbox_.fax_outbox_billingcode), ""),
					root.get(FaxOutbox_.fax_outbox_modifiedby).alias("modifiedBy"),
					root.get(FaxOutbox_.fax_outbox_sendername).alias("senderName"),
					root.get(FaxOutbox_.fax_outboxFaxpngfiles).alias("outboxThumbnail"),
					root.get(FaxOutbox_.fax_outboxAttachment).alias("faxAttachments"),
					builder.coalesce(root.get(FaxOutbox_.fax_outboxRetriesNote),"")
					)).where(FaxSpecification.getFaxOutboxDetails(fax_outbox_folderid, userId, root, cq, builder));
			cq.orderBy(builder.desc(root.get(FaxOutbox_.fax_outbox_scheduledtime)));
			Query query = entityManager.createQuery(cq);
			if(pageNo == 1){
				getOutboxDetails = query.setFirstResult(0).setMaxResults(12).getResultList();
			}else{
				if(pageSize==1){
					getOutboxDetails = query.setFirstResult( (pageNo-1)*12 ).setMaxResults(12).getResultList();
				}
				else{
					getOutboxDetails = query.setFirstResult( (pageNo-1)*pageSize ).setMaxResults(pageSize).getResultList();
				}
			}
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		finally {
		}
		return getOutboxDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaxInboxBean> getInboxDetails(Integer fax_inbox_folderid, Integer fax_inbox_statusid,Integer fax_inbox_forwardeduserid, Integer fax_inbox_location,int pageNo,int pageSize) {
		entityManager = emf.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<FaxInbox> root = cq.from(FaxInbox.class);

		List<FaxInboxBean> getInboxDetails=new ArrayList<FaxInboxBean>();
		try{
			cq.select(builder.construct(FaxInboxBean.class, 
					root.get(FaxInbox_.fax_inbox_id).alias("faxId"),
					root.get(FaxInbox_.fax_inbox_receiveddate).alias("receiveDate"),
					root.get(FaxInbox_.fax_inbox_tsid).alias("senderName"),
					root.get(FaxInbox_.fax_inbox_csid).alias("recepientName"),
					root.get(FaxInbox_.fax_inbox_filenames).alias("faxFileName"),
					root.get(FaxInbox_.fax_inbox_folderid).alias("folderId"), 
					builder.coalesce(root.get(FaxInbox_.fax_inbox_subject), ""),
					root.get(FaxInbox_.fax_inbox_type).alias("faxTypeId"),
					root.get(FaxInbox_.fax_inbox_statusid).alias("statusId"),
					root.get(FaxInbox_.fax_inbox_forwardeduserid).alias("forwardTo"),
					root.get(FaxInbox_.fax_inbox_modifieduserid).alias("modifiedBy"),
					root.get(FaxInbox_.fax_inbox_modifiedtime).alias("modifiedDate"),
					builder.coalesce(root.get(FaxInbox_.fax_inbox020Faxnotes), "")
					)).where(FaxSpecification.getFaxInboxDetails(fax_inbox_folderid, fax_inbox_statusid, fax_inbox_forwardeduserid, fax_inbox_location, root, cq, builder));
			cq.orderBy(builder.desc(root.get(FaxInbox_.fax_inbox_id)),(builder.desc(root.get(FaxInbox_.fax_inbox_modifieduserid))),(builder.desc(root.get(FaxInbox_.fax_inbox_modifiedtime))));

			Query query = entityManager.createQuery(cq);

			if(pageNo == 1){
				getInboxDetails = query.setFirstResult(0).setMaxResults(12).getResultList();
			}else{
				if(pageSize==1){
					getInboxDetails = query.setFirstResult( (pageNo-1)*12 ).setMaxResults(12).getResultList();
				}
				else{
					getInboxDetails=query.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).getResultList();	
				}	
			}
		}

		catch(Exception exception) {
			exception.printStackTrace();
		}
		finally {
		}
		return getInboxDetails;
	}

	@Override
	public List<FaxFolderBean> getFaxFolderCount(Integer fax_location,Integer userId){
		List<FaxFolderBean> tabBasedCount = new ArrayList<FaxFolderBean>();
		FaxFolderBean faxTabCount = new FaxFolderBean();
		try{
			faxTabCount.setTotalInbox(fax_inboxRepository.count(FaxSpecification.getInboxDetails(1,-1,0,fax_location)));
			faxTabCount.setUnreadInbox(fax_inboxRepository.count(FaxSpecification.getInboxDetails(1,0,0,fax_location)));

			faxTabCount.setTotalSaved(fax_inboxRepository.count(FaxSpecification.getInboxDetails(5,-1,0,fax_location)));
			faxTabCount.setUnreadSaved(fax_inboxRepository.count(FaxSpecification.getInboxDetails(5,0,0,fax_location)));

			faxTabCount.setTotalTrash(fax_inboxRepository.count(FaxSpecification.getInboxDetails(3,-1,0,fax_location)));
			faxTabCount.setUnreadTrash(fax_inboxRepository.count(FaxSpecification.getInboxDetails(3,0,0,fax_location)));

			faxTabCount.setOutboxFax(fax_outboxRepository.count(FaxSpecification.getOutboxDetails(2,-1)));
			faxTabCount.setSentFax(fax_outboxRepository.count(FaxSpecification.getOutboxDetails(4,-1)));

			faxTabCount.setMytotalInbox(fax_inboxRepository.count(FaxSpecification.getInboxDetails(1,-1,userId,fax_location)));
			faxTabCount.setMyunreadInbox(fax_inboxRepository.count(FaxSpecification.getInboxDetails(1,0,userId,fax_location)));

			faxTabCount.setMytotalSaved(fax_inboxRepository.count(FaxSpecification.getInboxDetails(5,-1,userId,fax_location)));
			faxTabCount.setMyunreadSaved(fax_inboxRepository.count(FaxSpecification.getInboxDetails(5,0,userId,fax_location)));

			faxTabCount.setMytotalTrash(fax_inboxRepository.count(FaxSpecification.getInboxDetails(3,-1,userId,fax_location)));
			faxTabCount.setMyunreadTrash(fax_inboxRepository.count(FaxSpecification.getInboxDetails(3,0,userId,fax_location)));

			faxTabCount.setMyoutboxFax(fax_outboxRepository.count(FaxSpecification.getOutboxDetails(2,userId)));
			faxTabCount.setMysentFax(fax_outboxRepository.count(FaxSpecification.getOutboxDetails(4,userId)));

			tabBasedCount.add(faxTabCount);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
		return tabBasedCount;
	}


	@Override
	public void deleteSelectFax (String faxId,String fileNames,Integer TRASH, Integer userId) {

		List<FaxInbox> faxToDelete=new ArrayList<FaxInbox>();
		List<Integer> selectedId = new ArrayList<Integer>();

		for(int index=0;index<faxId.split(",").length;index++){
			selectedId.add(index, Integer.parseInt(faxId.split(",")[index]));
		}
		
		
		faxToDelete = fax_inboxRepository.findAll(FaxSpecification.getSelectedFaxDetails(selectedId));
		
		FaxInbox faxInboxBean = faxToDelete.get(0);

		String updatedFileNames = getStringDiff(faxInboxBean.getfax_inbox_filenames(), fileNames, ",");

		if(fileNames.trim().length()<=0 || updatedFileNames.indexOf('.')<0){

			for(FaxInbox faxDetails : faxToDelete) {

				if(TRASH == 3){
					faxDetails.setfax_inbox_modifieduserid(userId);
					faxDetails.setfax_inbox018Isenabled(false);
				}else{
					faxDetails.setfax_inbox_modifieduserid(userId);
					faxDetails.setfax_inbox_folderid(3);
				}
				
				faxDetails = fax_inboxRepository.saveAndFlush(faxDetails);

			}
		}

		else{
			
			Date date=new Date();
			faxInboxBean.setfax_inbox_modifieduserid(userId);
			faxInboxBean.setfax_inbox_filenames(updatedFileNames);
			faxInboxBean.setfax_inbox_modifiedtime(new Timestamp(date.getTime()));
			fax_inboxRepository.save(faxInboxBean);		

			FaxInbox newFaxInboxBean = new FaxInbox();
			int currFaxId = getCurrMaxId();

			newFaxInboxBean.setfax_inbox_id(currFaxId);
			newFaxInboxBean.setfax_inbox_sendername(faxInboxBean.getfax_inbox_sendername());
			newFaxInboxBean.setfax_inbox_recipientname(faxInboxBean.getfax_inbox_recipientname());
			newFaxInboxBean.setfax_inbox_recipientnumber(faxInboxBean.getfax_inbox_recipientnumber());
			newFaxInboxBean.setfax_inbox_receiveddate(faxInboxBean.getfax_inbox_receiveddate());
			newFaxInboxBean.setfax_inbox_tsid(faxInboxBean.getfax_inbox_tsid());
			newFaxInboxBean.setfax_inbox_csid(faxInboxBean.getfax_inbox_csid());
			newFaxInboxBean.setfax_inbox_routinginfo(faxInboxBean.getfax_inbox_routinginfo());
			newFaxInboxBean.setfax_inbox_filenames(fileNames);
			newFaxInboxBean.setfax_inbox_subject(faxInboxBean.getfax_inbox_subject());
			newFaxInboxBean.setfax_inbox_type(faxInboxBean.getfax_inbox_type());
			newFaxInboxBean.setfax_inbox_statusid(faxInboxBean.getfax_inbox_statusid());
			newFaxInboxBean.setfax_inbox_modifieduserid(userId);
			newFaxInboxBean.setfax_inbox_modifiedtime(faxInboxBean.getfax_inbox_modifiedtime());
			newFaxInboxBean.setfax_inbox017Faxbox(faxInboxBean.getfax_inbox017Faxbox());
			if(TRASH == 3){
				newFaxInboxBean.setfax_inbox018Isenabled (false);
				newFaxInboxBean.setfax_inbox_modifieduserid(userId);
				if(faxInboxBean.getfax_inbox_forwardeduserid() == 0){
					newFaxInboxBean.setfax_inbox_forwardeduserid(userId);
				}else{
					newFaxInboxBean.setfax_inbox_forwardeduserid(faxInboxBean.getfax_inbox_forwardeduserid());
				}
			}else{
				newFaxInboxBean.setfax_inbox_modifieduserid(userId);
				newFaxInboxBean.setfax_inbox_folderid(3);
				if(faxInboxBean.getfax_inbox_forwardeduserid() == 0){
					newFaxInboxBean.setfax_inbox_forwardeduserid(userId);
				}else{
					newFaxInboxBean.setfax_inbox_forwardeduserid(faxInboxBean.getfax_inbox_forwardeduserid());
				}
			}

			fax_inboxRepository.saveAndFlush(newFaxInboxBean);
		}
	}

	public String getStringDiff1(String Stringx,String Stringy,String Seperator){
		String xDiffString = "";
		try{
			boolean isExists    = true;
			String xString[]     = Stringx.split(Seperator);
			String yString[]     = Stringy.split(Seperator);
			for(int i=0;i<xString.length;i++){
				for(int j=0;j<yString.length;j++){
					if( xString[i].equals(yString[j] )){
						isExists = true;
						break;
					}else
						isExists = false;
				}
				if (!isExists){
					if(xDiffString=="")
						xDiffString = xString[i];
					else
						xDiffString = xDiffString + Seperator + xString[i];
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		return xDiffString;
	}

	public Integer getCurrMaxId1() {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<FaxInbox> root = cq.from(FaxInbox.class);
		cq.select(builder.max(root.get(FaxInbox_.fax_inbox_id)));
		Integer faxId=(Integer) entityManager.createQuery(cq).getSingleResult();

		return faxId+1;
	}

	@Override
	public void deleteSelectOutboxFax (String faxId,Integer TRASH, Integer userId) {

		List<FaxOutbox> faxToDelete=new ArrayList<FaxOutbox>();
		List<Integer> selectedId = new ArrayList<Integer>();
		for(int index=0;index<faxId.split(",").length;index++){
			selectedId.add(index, Integer.parseInt(faxId.split(",")[index]));
		}
		faxToDelete = fax_outboxRepository.findAll(FaxSpecification.getSelectedFaxDetails1(selectedId));

		for(FaxOutbox faxDetails : faxToDelete) {

			faxDetails.setfax_outbox_statusid(9);
			faxDetails.setfax_outbox_modifiedby(userId);

			faxDetails = fax_outboxRepository.save(faxDetails);
		}
	}

	@Override
	public List<FaxUsersBean> getFaxUserList(){

		List<FaxUsersBean> faxUsers = new ArrayList<FaxUsersBean>();
		int[] groupId = new int[]{-1,-2,-3,-6,-7,-10};

		for(int i=0;i<groupId.length;i++){

			FaxUsersBean usersList = new FaxUsersBean();

			if(groupId[i] == -1){
				usersList.setName("doctors");
			}else if(groupId[i] == -2){
				usersList.setName("nurse");
			}else if(groupId[i] == -3){
				usersList.setName("frontdesk");
			}else if(groupId[i] == -6){
				usersList.setName("ma");
			}else if(groupId[i] == -7){
				usersList.setName("officeManager");
			}else if(groupId[i] == -10){
				usersList.setName("nursePractitioner");
			}

			usersList.setUsersList(getForwardUserList(groupId[i]));

			faxUsers.add(usersList);
		}

		return faxUsers;
	}

	public List<FaxForwardListBean> getForwardUserList(Integer empProfileGroupid) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<EmployeeProfile> root = cq.from(EmployeeProfile.class);
		cq.distinct(true);
		cq.select(

				builder.construct(FaxForwardListBean.class,
						root.get(EmployeeProfile_.empProfileEmpid).alias("hsp001"),
						root.get(EmployeeProfile_.empProfileFullname).alias("hsp002")));
		Predicate condition = null;
		condition = builder.and(builder.equal(root.get(EmployeeProfile_.empProfileIsActive),true),builder.equal(root.get(EmployeeProfile_.empProfileGroupid),empProfileGroupid),builder.notLike(root.get(EmployeeProfile_.empProfileFullname),"test"),builder.notLike(root.get(EmployeeProfile_.empProfileFullname),"demo"));
		cq.where(condition);
		List<Object> empnames=entityManager.createQuery(cq).getResultList();
		List<FaxForwardListBean> detailsBeanList = new ArrayList<FaxForwardListBean>();
		for(int i=0;i<empnames.size();i++)
		{
			FaxForwardListBean detailsBean=(FaxForwardListBean)empnames.get(i);
			detailsBeanList.add(detailsBean);
		}
		return detailsBeanList;
	}

	@Override
	public List<FaxInbox> forwardFax(String faxId,String fileNames, Integer forwardTo,Integer folderId,Integer userId){

		List<FaxInbox> faxList = new ArrayList<FaxInbox>();

		String[] faxIds = faxId.split(",");

		faxList=fax_inboxRepository.findAll(FaxSpecification.faxForward(faxIds));
		
		FaxInbox faxInboxBean = faxList.get(0);

		String updatedFileNames = getStringDiff(faxInboxBean.getfax_inbox_filenames(), fileNames, ",");

		if(fileNames.trim().length()<=0 || updatedFileNames.indexOf('.')<0){

			FaxInbox aeupdate=null;
			for(FaxInbox fax:faxList){
				Date date=new Date();
				FaxInbox ae =fax;
				ae.setfax_inbox_forwardeduserid(forwardTo);
				ae.setfax_inbox_folderid(folderId);
				ae.setfax_inbox_modifieduserid(userId);
				ae.setfax_inbox_modifiedtime(new Timestamp(date.getTime()));
				aeupdate=ae;
				fax_inboxRepository.save(aeupdate);
			}

		}else{

			Date date=new Date();
			faxInboxBean.setfax_inbox_modifieduserid(userId);
			faxInboxBean.setfax_inbox_filenames(updatedFileNames);
			faxInboxBean.setfax_inbox_modifiedtime(new Timestamp(date.getTime()));
			fax_inboxRepository.save(faxInboxBean);		

			FaxInbox newFaxInboxBean = new FaxInbox();
			int currFaxId = getCurrMaxId();

			newFaxInboxBean.setfax_inbox_id(currFaxId);
			newFaxInboxBean.setfax_inbox_sendername(faxInboxBean.getfax_inbox_sendername());
			newFaxInboxBean.setfax_inbox_recipientname(faxInboxBean.getfax_inbox_recipientname());
			newFaxInboxBean.setfax_inbox_recipientnumber(faxInboxBean.getfax_inbox_recipientnumber());
			newFaxInboxBean.setfax_inbox_receiveddate(faxInboxBean.getfax_inbox_receiveddate());
			newFaxInboxBean.setfax_inbox_tsid(faxInboxBean.getfax_inbox_tsid());
			newFaxInboxBean.setfax_inbox_csid(faxInboxBean.getfax_inbox_csid());
			newFaxInboxBean.setfax_inbox_routinginfo(faxInboxBean.getfax_inbox_routinginfo());
			newFaxInboxBean.setfax_inbox_filenames(fileNames);
			newFaxInboxBean.setfax_inbox_folderid(faxInboxBean.getfax_inbox_folderid());
			newFaxInboxBean.setfax_inbox_subject(faxInboxBean.getfax_inbox_subject());
			newFaxInboxBean.setfax_inbox_type(faxInboxBean.getfax_inbox_type());
			newFaxInboxBean.setfax_inbox_statusid(faxInboxBean.getfax_inbox_statusid());
			newFaxInboxBean.setfax_inbox_forwardeduserid(forwardTo);
			newFaxInboxBean.setfax_inbox_modifieduserid(userId);
			newFaxInboxBean.setfax_inbox_modifiedtime(faxInboxBean.getfax_inbox_modifiedtime());
			newFaxInboxBean.setfax_inbox017Faxbox(faxInboxBean.getfax_inbox017Faxbox());

			fax_inboxRepository.saveAndFlush(newFaxInboxBean);

		}


		return faxList;
	}

	public String getStringDiff(String Stringx,String Stringy,String Seperator){
		String xDiffString = "";
		try{
			boolean isExists    = true;
			String xString[]     = Stringx.split(Seperator);
			String yString[]     = Stringy.split(Seperator);
			for(int i=0;i<xString.length;i++){
				for(int j=0;j<yString.length;j++){
					if( xString[i].equals(yString[j] )){
						isExists = true;
						break;
					}else
						isExists = false;
				}
				if (!isExists){
					if(xDiffString=="")
						xDiffString = xString[i];
					else
						xDiffString = xDiffString + Seperator + xString[i];
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		return xDiffString;
	}

	public Integer getCurrMaxId() {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<FaxInbox> root = cq.from(FaxInbox.class);
		cq.select(builder.max(root.get(FaxInbox_.fax_inbox_id)));
		Integer faxId=(Integer) entityManager.createQuery(cq).getSingleResult();

		return faxId+1;
	}


	@Override
	public List<FaxInbox> getInFaxDetails(final Integer faxId, Integer userId, Integer faxTab, Integer faxFolder) {


		List<FaxInbox> inFaxDetails=new ArrayList<FaxInbox>();
		inFaxDetails = fax_inboxRepository.findAll(FaxSpecification.getInFaxDetails(faxId, faxFolder, faxFolder, faxFolder));

		if(faxId!=-1){
			inFaxDetails = fax_inboxRepository.findAll(FaxSpecification.getInFaxDetails(faxId, userId, faxTab, faxFolder));
		}else{
			inFaxDetails = fax_inboxRepository.findAll(FaxSpecification.getInFaxDetails(getMaxFaxId(faxFolder), userId, faxTab, faxFolder));
		}
		
		
		inFaxDetails.get(0).setfax_inbox_statusid(1);
		
		inFaxDetails.get(0).setfax_inbox_modifieduserid(userId);

		fax_inboxRepository.save(inFaxDetails.get(0));

		return inFaxDetails;

	}

	@Override
	public List<FaxOutbox> getOutFaxDetails(Integer userId,Integer faxId, Integer faxTab, Integer faxFolder) {
		List<FaxOutbox> getOutboxDetails=new ArrayList<FaxOutbox>();
		try{
			if(faxId!=-1){
				getOutboxDetails = fax_outboxRepository.findAll(FaxSpecification.getOutFaxDetails(userId,faxId, faxTab, faxFolder));
			}else{
				getOutboxDetails = fax_outboxRepository.findAll(FaxSpecification.getOutFaxDetails(getMaxFaxId(faxFolder),userId,faxTab, faxFolder));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
		return getOutboxDetails;
	}

	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<FaxSignListBean> getSignatureDetails(Integer empId){

		entityManager = emf.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<DoctorSign> root = cq.from(DoctorSign.class);
		Join<DoctorSign, EmployeeProfile> join1 = root.join(DoctorSign_.empLoginId,JoinType.INNER);
		Join<DoctorSign, LoginUsers> join2 = root.join(DoctorSign_.empLoginName,JoinType.INNER);

		List<Integer> allowGroupId = new ArrayList<Integer>();

		allowGroupId.add(-1);
		allowGroupId.add(-7);
		allowGroupId.add(-10);

		List<FaxSignListBean> getSignatureDetails = new ArrayList<FaxSignListBean>();
		int loginId = -1;
		boolean accessType = true;

		try {

			loginId = employeeProfileRepository.findOne(FaxSpecification.getEmployeeLoginId(empId)).getEmpProfileLoginid();
			accessType = doctorsignRepository.findOne(FaxSpecification.getSignAccessType(loginId)).getSignaccess();

			if(accessType){

				cq.select(
						builder.construct(FaxSignListBean.class,
								join1.get(EmployeeProfile_.empProfileFullname),
								join1.get(EmployeeProfile_.empProfileLoginid),
								join2.get(LoginUsers_.loginUsersUsername),
								root.get(DoctorSign_.filename)
								)
						);
				cq.where(builder.and(builder.equal(join1.get(EmployeeProfile_.empProfileIsActive), true) ,builder.notLike(root.get(DoctorSign_.filename), ""), join1.get(EmployeeProfile_.empProfileGroupid).in(allowGroupId), builder.equal(root.get(DoctorSign_.signaccess), true)));
    
			}else{

				cq.select(
						builder.construct(FaxSignListBean.class,
								join1.get(EmployeeProfile_.empProfileFullname),
								join1.get(EmployeeProfile_.empProfileLoginid),
								join2.get(LoginUsers_.loginUsersUsername),
								root.get(DoctorSign_.filename)
								)
						);
				cq.where(builder.and(builder.equal(root.get(DoctorSign_.empLoginId),loginId),builder.equal(join1.get(EmployeeProfile_.empProfileIsActive), true)));

			}

			Query query = entityManager.createQuery(cq);

			getSignatureDetails = query.getResultList();

		} catch (Exception e) {
		}
		finally{

		}

		return getSignatureDetails;

	}

	@SuppressWarnings("unused")
	private boolean getAccessType(int loginId) {
		EntityManager em = emf.createEntityManager();
		try {
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<DoctorSign> root = cq.from(DoctorSign.class);
			Join<DoctorSign, EmployeeProfile> join1 = root.join(DoctorSign_.empLoginId,JoinType.INNER);
			cq.select(
					builder.construct(FaxSignListBean.class,
							join1.get(EmployeeProfile_.empProfileFullname),
							join1.get(EmployeeProfile_.empProfileLoginid),
							root.get(DoctorSign_.filename)

							));
			cq.where(builder.equal(root.get(DoctorSign_.empLoginId), loginId));
			Object obj=em.createQuery(cq).getSingleResult();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return true;
		} finally {
			em.close();
		}
	}

	@Override
	public String lastFaxReceivedTime(Integer userId) {

		String timeDetails = "";

		FaxInbox faxReceivedTime = new FaxInbox();

		faxReceivedTime = fax_inboxRepository.findOne(FaxSpecification.lastFaxReceivedTime(getMaxFaxId1(0, userId)));

		timeDetails = faxReceivedTime.getfax_inbox_receiveddate().toString();

		faxReceivedTime = fax_inboxRepository.findOne(FaxSpecification.lastFaxReceivedTime(getMaxFaxId1(1, userId)));
		if(faxReceivedTime!=null)
			timeDetails = timeDetails.concat("~".concat(faxReceivedTime.getfax_inbox_receiveddate().toString()));
			else
			timeDetails = timeDetails.concat("~".concat("-1"));
			return timeDetails;
	}


	private int getMaxFaxId1(int faxTab, int userId) {
		int maxId=0;
		EntityManager em = emf.createEntityManager();
		try {

			CriteriaBuilder builder = em.getCriteriaBuilder();

			CriteriaQuery<Object> cq = builder.createQuery();

			Root<FaxInbox> root = cq.from(FaxInbox.class);

			cq.select(builder.max(root.get(FaxInbox_.fax_inbox_id)));

			if(faxTab == 0){
				cq.where(builder.and(builder.equal(root.get(FaxInbox_.fax_inbox_folderid), 1), builder.equal(root.get(FaxInbox_.fax_inbox_forwardeduserid), userId), builder.equal(root.get(FaxInbox_.fax_inbox018Isenabled), true)));
			}else{
				cq.where(builder.and(builder.equal(root.get(FaxInbox_.fax_inbox_folderid), 1), builder.equal(root.get(FaxInbox_.fax_inbox_forwardeduserid), 0),builder.equal(root.get(FaxInbox_.fax_inbox018Isenabled), true)));
			}

			//return Integer.parseInt("" + em.createQuery(cq).getSingleResult());
			Query query=em.createQuery(cq);
			Object maxIdObj=query.getSingleResult();         
           
			if(maxIdObj!=null)
				maxId = (int) maxIdObj;			
			if(maxId < 1){
			 return maxId;
			}
			else{
			return maxId ;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
		}
	}

	
	public Integer getMaxFaxId(Integer faxFolder) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		if(faxFolder == 2 || faxFolder == 4){
			Root<FaxOutbox> root = cq.from(FaxOutbox.class);
			cq.select(builder.max(root.get(FaxOutbox_.fax_outbox_id)));
		}else{
			Root<FaxInbox> root = cq.from(FaxInbox.class);
			cq.select(builder.max(root.get(FaxInbox_.fax_inbox_id))); 
		}
		List<Object> result = entityManager.createQuery(cq).getResultList();
		Integer maxId =-1;
		for (int i = 0; i < result.size(); i++) {
			maxId = Integer.valueOf(result.get(i)==null?"0":result.get(i).toString());
		}
		if (result.size()>0) {
			return maxId;
		}
		
		return 0;
		
		
	     
}
	@Override
	public List<FaxBox> getFaxLocation() {	
		List<FaxBox> getFaxLocation=new ArrayList<FaxBox>();	
		getFaxLocation = faxBoxRepository.findAll();
		return getFaxLocation;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaxInboxBean> markReadAlert(Integer faxId,Integer userId) {
		List<FaxInbox> faxReadList=fax_inboxRepository.findAll(FaxSpecification.readFax(faxId));
		FaxInbox faxUpdate = null;
		for (FaxInbox faxInbox : faxReadList) {
			Date date= new Date();
			FaxInbox ae=faxInbox;
             ae.setfax_inbox_statusid(1);
             ae.setfax_inbox_modifieduserid(userId);
             ae.setfax_inbox_modifiedtime(new Timestamp(date.getTime()));	
             faxUpdate=ae;
             fax_inboxRepository.saveAndFlush(faxUpdate);
		}
		entityManager = emf.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<FaxInbox> root = cq.from(FaxInbox.class);

		List<FaxInboxBean> getInboxDetails=new ArrayList<FaxInboxBean>();
		try{
			cq.select(builder.construct(FaxInboxBean.class, 			
					root.get(FaxInbox_.fax_inbox_id).alias("faxId"),
					root.get(FaxInbox_.fax_inbox_receiveddate).alias("receiveDate"),
					root.get(FaxInbox_.fax_inbox_tsid).alias("senderName"),
					root.get(FaxInbox_.fax_inbox_csid).alias("recepientName"),
					root.get(FaxInbox_.fax_inbox_filenames).alias("faxFileName"),
					root.get(FaxInbox_.fax_inbox_folderid).alias("folderId"), 
					builder.coalesce(root.get(FaxInbox_.fax_inbox_subject), ""),
					root.get(FaxInbox_.fax_inbox_type).alias("faxTypeId"),
					root.get(FaxInbox_.fax_inbox_statusid).alias("statusId"),
					root.get(FaxInbox_.fax_inbox_forwardeduserid).alias("forwardTo"),
					root.get(FaxInbox_.fax_inbox_modifieduserid).alias("modifiedBy"),
					root.get(FaxInbox_.fax_inbox_modifiedtime).alias("modifiedDate"),
					builder.coalesce(root.get(FaxInbox_.fax_inbox020Faxnotes), "")					
					)).where(FaxSpecification.getReadFax(faxId,root, cq, builder));
			cq.orderBy(builder.desc(root.get(FaxInbox_.fax_inbox_id)),(builder.desc(root.get(FaxInbox_.fax_inbox_modifieduserid))),(builder.desc(root.get(FaxInbox_.fax_inbox_modifiedtime))));
			Query query = entityManager.createQuery(cq);
			getInboxDetails = query.setMaxResults(10).getResultList();
		}

		catch(Exception exception) {
			exception.printStackTrace();
		}
		System.out.println("mark unread list "+faxReadList);
		return getInboxDetails;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FaxInboxBean> markUnReadAlert(Integer faxId,Integer userId) {
		List<FaxInbox> faxUnReadList=fax_inboxRepository.findAll(FaxSpecification.readFax(faxId));
		FaxInbox faxUpdate = null;
		for (FaxInbox faxInbox : faxUnReadList) {
			Date date= new Date();
			FaxInbox ae=faxInbox;
             ae.setfax_inbox_statusid(0);
             ae.setfax_inbox_modifieduserid(userId);
             ae.setfax_inbox_modifiedtime(new Timestamp(date.getTime()));	
             faxUpdate=ae;
             fax_inboxRepository.saveAndFlush(faxUpdate);
		}
			entityManager = emf.createEntityManager();
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<FaxInbox> root = cq.from(FaxInbox.class);

			List<FaxInboxBean> getInboxDetails=new ArrayList<FaxInboxBean>();
			try{
				cq.select(builder.construct(FaxInboxBean.class, 
						root.get(FaxInbox_.fax_inbox_id).alias("faxId"),
						root.get(FaxInbox_.fax_inbox_receiveddate).alias("receiveDate"),
						root.get(FaxInbox_.fax_inbox_tsid).alias("senderName"),
						root.get(FaxInbox_.fax_inbox_csid).alias("recepientName"),
						root.get(FaxInbox_.fax_inbox_filenames).alias("faxFileName"),
						root.get(FaxInbox_.fax_inbox_folderid).alias("folderId"), 
						builder.coalesce(root.get(FaxInbox_.fax_inbox_subject), ""),
						root.get(FaxInbox_.fax_inbox_type).alias("faxTypeId"),
						root.get(FaxInbox_.fax_inbox_statusid).alias("statusId"),
						root.get(FaxInbox_.fax_inbox_forwardeduserid).alias("forwardTo"),
						root.get(FaxInbox_.fax_inbox_modifieduserid).alias("modifiedBy"),
						root.get(FaxInbox_.fax_inbox_modifiedtime).alias("modifiedDate"),
						builder.coalesce(root.get(FaxInbox_.fax_inbox020Faxnotes), "")			
						)).where(FaxSpecification.getReadFax(faxId,root, cq, builder));
				cq.orderBy(builder.desc(root.get(FaxInbox_.fax_inbox_id)),(builder.desc(root.get(FaxInbox_.fax_inbox_modifieduserid))),(builder.desc(root.get(FaxInbox_.fax_inbox_modifiedtime))));
				Query query = entityManager.createQuery(cq);
				getInboxDetails = query.setMaxResults(10).getResultList();
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
			return getInboxDetails;
		}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaxInboxBean> searchFax(String nameString, String faxFolder,
			String faxTab, String faxLocation,String forwardUserId) {
		// TODO Auto-generated method stub
		entityManager = emf.createEntityManager();
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<FaxInbox> root = cq.from(FaxInbox.class);
		List<FaxInboxBean> getInboxDetails=new ArrayList<FaxInboxBean>();
		try{
			cq.select(builder.construct(FaxInboxBean.class, 
					root.get(FaxInbox_.fax_inbox_id).alias("faxId"),
					root.get(FaxInbox_.fax_inbox_receiveddate).alias("receiveDate"),
					root.get(FaxInbox_.fax_inbox_tsid).alias("senderName"),
					root.get(FaxInbox_.fax_inbox_csid).alias("recepientName"),
					root.get(FaxInbox_.fax_inbox_filenames).alias("faxFileName"),
					root.get(FaxInbox_.fax_inbox_folderid).alias("folderId"), 
					builder.coalesce(root.get(FaxInbox_.fax_inbox_subject), ""),
					root.get(FaxInbox_.fax_inbox_type).alias("faxTypeId"),
					root.get(FaxInbox_.fax_inbox_statusid).alias("statusId"),
					root.get(FaxInbox_.fax_inbox_forwardeduserid).alias("forwardTo"),
					root.get(FaxInbox_.fax_inbox_modifieduserid).alias("modifiedBy"),
					root.get(FaxInbox_.fax_inbox_modifiedtime).alias("modifiedDate"),
					builder.coalesce(root.get(FaxInbox_.fax_inbox020Faxnotes), "")
					)).where(FaxSpecification.getSearchFax(nameString,Integer.parseInt(faxFolder),Integer.parseInt(faxTab),Integer.parseInt(faxLocation),Integer.parseInt(forwardUserId),root, cq, builder));
			cq.orderBy(builder.desc(root.get(FaxInbox_.fax_inbox_id)),(builder.desc(root.get(FaxInbox_.fax_inbox_modifieduserid))),(builder.desc(root.get(FaxInbox_.fax_inbox_modifiedtime))));

			Query query = entityManager.createQuery(cq);
			getInboxDetails = query.setMaxResults(10).getResultList();
		}

		catch(Exception exception) {
			exception.printStackTrace();
		}
		return getInboxDetails;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaxOutbox> outBoxSearch(String nameString, String faxFolder,
			String faxTab, String faxLocation,String ForwardUserId){
			
		entityManager = emf.createEntityManager();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<FaxOutbox> root = cq.from(FaxOutbox.class);

		List<FaxOutbox> getOutboxDetails=new ArrayList<FaxOutbox>();
		try {
			cq.select(builder.construct(FaxOutboxBean.class, 
					root.get(FaxOutbox_.fax_outbox_id).alias("faxId"),
					builder.coalesce(root.get(FaxOutbox_.fax_outbox_subject), ""),
					root.get(FaxOutbox_.fax_outbox_htmlfile).alias("faxFileName"),
					root.get(FaxOutbox_.fax_outbox_folderid).alias("folderId"),
					root.get(FaxOutbox_.fax_outbox_scheduledtime).alias("scheduleTime"),
					root.get(FaxOutbox_.fax_outbox_dispatchtime).alias("actualDespatchtime"),
					root.get(FaxOutbox_.fax_outbox_statusid).alias("statusId"),
					root.get(FaxOutbox_.fax_outbox_recipientnumber).alias("recipientNumber"),
					root.get(FaxOutbox_.fax_outbox_recipientname).alias("recipientName"),
					root.get(FaxOutbox_.fax_outbox_forwardedto).alias("forwardedToUserId"),
					root.get(FaxOutbox_.fax_outbox_createdby).alias("createdBy"),
					root.get(FaxOutbox_.fax_outbox_createddate).alias("createdDate"),
					builder.coalesce(root.get(FaxOutbox_.fax_outbox_billingcode), ""),
					root.get(FaxOutbox_.fax_outbox_modifiedby).alias("modifiedBy"),
					root.get(FaxOutbox_.fax_outbox_sendername).alias("senderName"),
					root.get(FaxOutbox_.fax_outboxFaxpngfiles).alias("outboxThumbnail"),
					root.get(FaxOutbox_.fax_outboxAttachment).alias("faxAttachments"),
					builder.coalesce(root.get(FaxOutbox_.fax_outboxRetriesNote),"")
					)).where(FaxSpecification.getOutBoxSearchFax(nameString,Integer.parseInt(faxFolder),Integer.parseInt(faxTab),Integer.parseInt(faxLocation),Integer.parseInt(ForwardUserId),root, cq, builder));
			cq.orderBy(builder.desc(root.get(FaxOutbox_.fax_outbox_scheduledtime)));
			Query query = entityManager.createQuery(cq);
			getOutboxDetails = query.setMaxResults(10).getResultList();		
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return getOutboxDetails;
		
	}

}