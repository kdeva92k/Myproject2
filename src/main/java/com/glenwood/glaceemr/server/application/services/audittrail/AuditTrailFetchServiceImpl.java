package com.glenwood.glaceemr.server.application.services.audittrail;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.AuditTrail;
import com.glenwood.glaceemr.server.application.models.AuditTrail_;
import com.glenwood.glaceemr.server.application.repositories.AuditLogSubModuleRepository;
import com.glenwood.glaceemr.server.application.repositories.AuditTrailRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.specifications.AuditTrailSpecifications;
import com.glenwood.glaceemr.server.utils.HUtil;

@Service
public class AuditTrailFetchServiceImpl implements AuditTrailFetchService {
	@Resource
	AuditTrailRepository auditTrailRepository;
	@Resource
	PatientRegistrationRepository patientRegistrationRepository;
	
	@Autowired
	AuditLogSubModuleRepository auditLogSubModuleRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	long timeDiff = 0;

	@Override
	public Iterable<AuditTrail> getSearchResult(int userId, String parentModule, String subModule, String outcome, String desc,
			String startDate, String endDate, String action, int parentEvent, int patientId, String sessionId, String clientIp, int logId, String sortProperty, String order, int currentPage, int pageSize) {
		Iterable<AuditTrail> searchedList = null;

		Timestamp findCurrentTimeStamp = findCurrentTimeStamp();
		Sort sort = null;
		
		if(sortProperty.equalsIgnoreCase("id"))
		{
			if(order.equalsIgnoreCase("asc"))
			{
				sort = new Sort(Direction.ASC,"logId");
			}
			else
			{
				sort = new Sort(Direction.DESC,"logId");
			}
		}
		else if(sortProperty.equalsIgnoreCase("module"))
		{
			if(order.equalsIgnoreCase("asc"))
			{
				sort = new Sort(Direction.ASC,"module");
			}
			else
			{
				sort = new Sort(Direction.DESC,"module");
			}
		}
		else if(sortProperty.equalsIgnoreCase("action"))
		{
			if(order.equalsIgnoreCase("asc"))
			{
				sort = new Sort(Direction.ASC,"action");
			}
			else
			{
				sort = new Sort(Direction.DESC,"action");
			}
		}
		else
		{
			if(order.equalsIgnoreCase("asc"))
			{
				sort = new Sort(Direction.ASC,"logOn");
			}
			else
			{
				sort = new Sort(Direction.DESC,"logOn");
			}
		}

		if (startDate.equals("-1") && !endDate.equals("-1")) {

			searchedList = auditTrailRepository.findAll(AuditTrailSpecifications.getSearchResult(userId, parentModule, subModule,
					outcome, desc, action, parentEvent, patientId, sessionId, clientIp, logId, sortProperty, order,  null, Timestamp.valueOf(endDate), auditLogSubModuleRepository), new PageRequest(currentPage, pageSize, sort));

		} else if (!startDate.equals("-1") && endDate.equals("-1")) {

			searchedList = auditTrailRepository.findAll(
					AuditTrailSpecifications.getSearchResult(userId, parentModule, subModule, outcome, desc, action, parentEvent, patientId, sessionId, clientIp, logId, sortProperty, order, 
							(Timestamp) Timestamp.valueOf(startDate), (Timestamp) findCurrentTimeStamp, auditLogSubModuleRepository), 
					new PageRequest(currentPage, pageSize, sort));
		} else if (!startDate.equals("-1") && !endDate.equals("-1")) {

			searchedList = auditTrailRepository.findAll(
					AuditTrailSpecifications.getSearchResult(userId, parentModule, subModule, outcome, desc, action, parentEvent, patientId, sessionId, clientIp, logId, sortProperty, order, 
							Timestamp.valueOf(startDate), Timestamp.valueOf(endDate), auditLogSubModuleRepository),
					new PageRequest(currentPage, pageSize, sort));
		} else if (startDate.equals("-1") && endDate.equals("-1")) {

			searchedList = auditTrailRepository.findAll(
					AuditTrailSpecifications.getSearchResult(userId, parentModule, subModule, outcome, desc, action, parentEvent, patientId, sessionId, clientIp, logId, sortProperty, order, null, findCurrentTimeStamp, auditLogSubModuleRepository),
					new PageRequest(currentPage, pageSize, sort) );
		} else {

			searchedList = auditTrailRepository.findAll(new PageRequest(currentPage, pageSize, sort));
		}

		Iterator<AuditTrail> list = searchedList.iterator();

		while (list.hasNext()) {
			
			AuditTrail eventLog = (AuditTrail) list.next();
			String message = eventLog.getUserId() + "," + eventLog.getParentID() + "," + eventLog.getLogOn() + ","
					+ eventLog.getModule() + "," + "" + eventLog.getAction() + "," + eventLog.getOutcome() + "," + ""
					+ eventLog.getClientIp() + "," + eventLog.getSessionId() + "," + eventLog.getRelevantIds() + ","
					+ eventLog.getDesc();

			eventLog.setVerifyCheckSum(verifyChecksum(SHA1ForString(message), eventLog.getCheckSum()));
			if(eventLog.getPatientId() != -1 && eventLog.getPatientId() != 0){
				try{
				eventLog.setAccountNo(Integer.parseInt(patientRegistrationRepository.findOne(eventLog.getPatientId()).getPatientRegistrationAccountno()));
				}catch(Exception e){
					System.out.println(e.getLocalizedMessage());
				}
				try{
				eventLog.setPatientName(patientRegistrationRepository.findOne(eventLog.getPatientId()).getPatientRegistrationFirstName()+" "+HUtil.Nz(patientRegistrationRepository.findOne(eventLog.getPatientId()).getPatientRegistrationMidInitial(),"")+" "+patientRegistrationRepository.findOne(eventLog.getPatientId()).getPatientRegistrationLastName());
				}catch(Exception e){
					System.out.println(e.getLocalizedMessage());
				}
				try{
				eventLog.setDob(patientRegistrationRepository.findOne(eventLog.getPatientId()).getPatientRegistrationDob());
				}catch(Exception e){
					System.out.println(e.getLocalizedMessage());
				}
			}
		}
		
		
		return searchedList;
	}

	public boolean verifyChecksum(String message, String existingCheckSum) {
		String out = "";
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("SHA-1"); // SHA-512

			md.update(message.getBytes());
			byte[] mb = md.digest();
			for (int i = 0; i < mb.length; i++) {
				byte temp = mb[i];
				String s = Integer.toHexString(new Byte(temp));
				while (s.length() < 2) {
					s = "0" + s;
				}
				s = s.substring(s.length() - 2);
				out += s;
			}
			
			if (out.equals(existingCheckSum)) {
				return true;
			} else {
				return false;
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("ERROR: " + e.getMessage());
			return false;
		}
	}
	
	public String SHA1ForString(String message) {
		String out = "";
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("SHA-1"); // SHA-512

			md.update(message.getBytes());
			byte[] mb = md.digest();
			for (int i = 0; i < mb.length; i++) {
				byte temp = mb[i];
				String s = Integer.toHexString(new Byte(temp));
				while (s.length() < 2) {
					s = "0" + s;
				}
				s = s.substring(s.length() - 2);
				out += s;
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return out;
	}

	public Timestamp findCurrentTimeStamp() {

		Query query = entityManager.createNativeQuery(" select current_timestamp from audittrail_log limit 1");
		return (Timestamp) query.getSingleResult();
	}


	@Override
	public Iterable<AuditTrail> generateCsv(int userId, String parentModule, String subModule, String outcome, String desc, String startDate,
			String endDate, String action, int parentEvent, int patientId, String sessionId, String clientIp, int logId, String sortProperty, String order, HttpServletResponse response)
			throws IOException {

		Iterable<AuditTrail> searchedList = null;
		Timestamp findCurrentTimeStamp = findCurrentTimeStamp();
		if (startDate.equals("-1") && !endDate.equals("-1")) {
			searchedList = auditTrailRepository.findAll(AuditTrailSpecifications.getSearchResult(userId, parentModule, subModule,
					outcome, desc, action, parentEvent, patientId, sessionId, clientIp, logId, sortProperty, order,  null, Timestamp.valueOf(endDate), auditLogSubModuleRepository));
		} else if (!startDate.equals("-1") && endDate.equals("-1")) {
			searchedList = auditTrailRepository.findAll(AuditTrailSpecifications.getSearchResult(userId, parentModule, subModule,
					outcome, desc, action, parentEvent, patientId, sessionId, clientIp, logId, sortProperty, order,  Timestamp.valueOf(startDate), findCurrentTimeStamp, auditLogSubModuleRepository));
		} else if (!startDate.equals("-1") && !endDate.equals("-1")) {
			searchedList = auditTrailRepository.findAll(AuditTrailSpecifications.getSearchResult(userId, parentModule, subModule,
					outcome, desc, action, parentEvent, patientId, sessionId, clientIp, logId, sortProperty, order,  Timestamp.valueOf(startDate), Timestamp.valueOf(endDate), auditLogSubModuleRepository));
		} else if (startDate.equals("-1") && endDate.equals("-1")) {
			searchedList = auditTrailRepository.findAll(AuditTrailSpecifications.getSearchResult(userId, parentModule, subModule,
					outcome, desc, action, parentEvent, patientId, sessionId, clientIp, logId, sortProperty, order,  null, findCurrentTimeStamp, auditLogSubModuleRepository));
		} else {
			searchedList = auditTrailRepository.findAll();
		}
		
		Iterator<AuditTrail> list = searchedList.iterator();

		while (list.hasNext()) {
			
			AuditTrail eventLog = (AuditTrail) list.next();
			String message = eventLog.getUserId() + "," + eventLog.getParentID() + "," + eventLog.getLogOn() + ","
					+ eventLog.getModule() + "," + "" + eventLog.getAction() + "," + eventLog.getOutcome() + "," + ""
					+ eventLog.getClientIp() + "," + eventLog.getSessionId() + "," + eventLog.getRelevantIds() + ","
					+ eventLog.getDesc();

			eventLog.setVerifyCheckSum(verifyChecksum(SHA1ForString(message), eventLog.getCheckSum()));
			if(eventLog.getPatientId() != -1 && eventLog.getPatientId() != 0){
				try{
				eventLog.setAccountNo(Integer.parseInt(patientRegistrationRepository.findOne(eventLog.getPatientId()).getPatientRegistrationAccountno()));
				}catch(Exception e){
					System.out.println(e.getLocalizedMessage());
				}
				try{
					System.out.println("firstname >> "+patientRegistrationRepository.findOne(eventLog.getPatientId()).getPatientRegistrationFirstName());
					System.out.println("lastname >> "+patientRegistrationRepository.findOne(eventLog.getPatientId()).getPatientRegistrationLastName());
				eventLog.setPatientName(patientRegistrationRepository.findOne(eventLog.getPatientId()).getPatientRegistrationFirstName()+patientRegistrationRepository.findOne(eventLog.getPatientId()).getPatientRegistrationMidInitial()+patientRegistrationRepository.findOne(eventLog.getPatientId()).getPatientRegistrationLastName());
				}catch(Exception e){
					System.out.println(e.getLocalizedMessage());
				}
				try{
				eventLog.setDob(patientRegistrationRepository.findOne(eventLog.getPatientId()).getPatientRegistrationDob());
				}catch(Exception e){
					System.out.println(e.getLocalizedMessage());
				}
			}
		}

		return searchedList;

	}

	@Override
	public String generateCsvCount(int userId, String parentModule, String subModule, String outcome, String desc,
			String startDate, String endDate, String action, int parentEvent, int patientId, String sessionId,
			String clientIp, int logId, HttpServletResponse response) throws IOException {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> query = cb.createQuery();
		Root<AuditTrail> rootCount = query.from(AuditTrail.class);
		Timestamp findCurrentTimeStamp = findCurrentTimeStamp();
		query.select(cb.count(rootCount.get(AuditTrail_.logId)));
		List<Predicate> predList = new ArrayList<>();
		if (userId != -1){
			Predicate userIdPredict = cb.equal( rootCount.get(AuditTrail_.userId),userId);
			predList.add(userIdPredict);
		}
		if (patientId != -1){
			Predicate patientIdPredict = cb.equal( rootCount.get(AuditTrail_.patientId),patientId);
			predList.add(patientIdPredict);
		}
		if (!parentModule.equals("-1")){
			if(subModule.equals("-1")){
				Predicate parentModulePredicate = cb.equal(rootCount.get(AuditTrail_.module), auditLogSubModuleRepository.getSubModuleIds(Integer.parseInt(parentModule)));
				predList.add(parentModulePredicate);
			}
			else{
				Predicate modulePredict = cb.equal( rootCount.get(AuditTrail_.module),subModule);
				predList.add(modulePredict);
			}
		}else if (!subModule.equals("-1")){
			Predicate modulePredict = cb.equal( rootCount.get(AuditTrail_.module),subModule);
			predList.add(modulePredict);
		}
		if (!outcome.equals("-1")){
			Predicate outcomePredict = cb.equal( rootCount.get(AuditTrail_.outcome),outcome);
			predList.add(outcomePredict);
		}
		if (!desc.trim().equals("-1")){
			Predicate descPredict = cb.equal( rootCount.get(AuditTrail_.desc),desc);
			predList.add(descPredict);
		}
		if (!action.trim().equals("-1")){
			Predicate actionPredict = cb.equal( rootCount.get(AuditTrail_.action),action);
			predList.add(actionPredict);
		}
		if (logId != -1){
			Predicate logIdPredict = cb.equal( rootCount.get(AuditTrail_.logId),logId);
			Predicate parentPredict = cb.equal( rootCount.get(AuditTrail_.parentID),logId);
			Predicate or = cb.or(logIdPredict,parentPredict);
			predList.add(or);
		}
		if (parentEvent != -1){
			Predicate parentEventPredict = cb.equal( rootCount.get(AuditTrail_.logId),parentEvent);
			Predicate parentPredict = cb.equal( rootCount.get(AuditTrail_.parentID),parentEvent);
			Predicate or = cb.or(parentEventPredict,parentPredict);
			predList.add(or);
		}
		if (!sessionId.equals("-1")){
			Predicate sessionIdPredict = cb.equal( rootCount.get(AuditTrail_.sessionId),sessionId);
			predList.add(sessionIdPredict);
		}
		if (!clientIp.equals("-1")){
			Predicate clientIpPredict = cb.equal( rootCount.get(AuditTrail_.clientIp),clientIp);
			predList.add(clientIpPredict);
		}
		if (!endDate.equals("-1")) {
			Predicate endDatePredict = cb.lessThanOrEqualTo(rootCount.get(AuditTrail_.logOn),Timestamp.valueOf(endDate));
			predList.add(endDatePredict);
		} 
		if (!startDate.equals("-1")) {
			Predicate startDatePredict = cb.greaterThanOrEqualTo(rootCount.get(AuditTrail_.logOn), Timestamp.valueOf(startDate));
			predList.add(startDatePredict);
		} 
		if(startDate.equals("-1") && endDate.equals("-1")){
			Predicate datePredict = cb.lessThanOrEqualTo(rootCount.get(AuditTrail_.logOn),findCurrentTimeStamp);
			predList.add(datePredict);
		}
		query.where(cb.and(predList.toArray(new Predicate[] {})));
		Object rst = entityManager.createQuery(query).getSingleResult();
		return rst.toString();
		}
}
