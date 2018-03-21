package com.glenwood.glaceemr.server.application.services.chart.admission;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.Admission;
import com.glenwood.glaceemr.server.application.models.AdmissionRoom;
import com.glenwood.glaceemr.server.application.models.Admission_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.FaxOutbox;
import com.glenwood.glaceemr.server.application.models.FrequentInterventions;
import com.glenwood.glaceemr.server.application.models.FrequentInterventions_;
import com.glenwood.glaceemr.server.application.models.LeafPatient;
import com.glenwood.glaceemr.server.application.models.PatientAdmission;
import com.glenwood.glaceemr.server.application.models.PatientAdmission_;
import com.glenwood.glaceemr.server.application.models.PatientAllergies;
import com.glenwood.glaceemr.server.application.models.PatientEpisode;
import com.glenwood.glaceemr.server.application.repositories.AdmissionRepository;
import com.glenwood.glaceemr.server.application.repositories.AdmissionRoomRepository;
import com.glenwood.glaceemr.server.application.repositories.EmpProfileRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterEntityRepository;
import com.glenwood.glaceemr.server.application.repositories.FaxOutboxRepository;
import com.glenwood.glaceemr.server.application.repositories.LeafGroupRepository;
import com.glenwood.glaceemr.server.application.repositories.LeafLibraryRepository;
import com.glenwood.glaceemr.server.application.repositories.LeafPatientRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientAdmissionRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientAllergyRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientEpisodeRepository;
import com.glenwood.glaceemr.server.application.services.chart.CNMSettings.CNMSettingsServiceImpl;
import com.glenwood.glaceemr.server.application.specifications.AdmissionSpecification;
import com.glenwood.glaceemr.server.application.specifications.LeafPatientSpecfication;


@Service
public class AdmissionServiceImpl implements AdmissionService {

	@Autowired
	AdmissionRepository admissionRepository;
	
	@Autowired
	LeafGroupRepository leafgroupRepository;

	@Autowired
	LeafLibraryRepository leafLibraryRepository;

	@Autowired
	PatientEpisodeRepository patientEpisodeRepository;

	@Autowired
	EmpProfileRepository empProfileRepository;
	
	@Autowired
	EncounterEntityRepository encounterEntityRepository;
	
	@Autowired
	CNMSettingsServiceImpl cnmSettingsServiceImpl;
	
	@Autowired
	LeafPatientRepository leafPatientRepository;
	
	@Autowired
	PatientAllergyRepository patientAllergyRepository;
	
	@Autowired
	FaxOutboxRepository fax_outboxRepository;
	
	@Autowired
	AdmissionRoomRepository admissionRoomRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	PatientAdmissionRepository patientAdmissionRepository;
	
	/**
	 * To create/update Admission
	 */
	@Override
	public String saveAdmission( AdmissionBean admission) {


		Admission admssObj = null;
		Admission newadmssObj = null;

		try{
			Integer admissionId;
			String returnEpisode="-1";
			admssObj = getAdmission(admission.getPatientId());
			if(admssObj == null){
				newadmssObj = new Admission();
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				Date parsed=null;

				try {
					parsed = format.parse(admission.getAdmissionDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}

				java.sql.Date admssDate = new java.sql.Date(parsed.getTime());
				newadmssObj.setAdmissionAdmittedDate(admssDate);				
				newadmssObj.setAdmissionTime(admission.getAdmissionTime());
				newadmssObj.setAdmissionDoctorId(admission.getAdmssProvider());
				newadmssObj.setAdmissionPosId(admission.getPos());
				newadmssObj.setAdmissionPatientId(admission.getPatientId());
				newadmssObj.setAdmissionStatus(1);
				newadmssObj.setAdmissionRoom(admission.getRoomNo());
				newadmssObj.setAdmissionBlock(admission.getBlockNo());
				newadmssObj.setAdmissionNotes(admission.getNotes());


				//open a admission type episode for the patient
				PatientEpisode patAdmsEpisode=new PatientEpisode();
				patAdmsEpisode.setPatientEpisodePatientid(admission.getPatientId());
				patAdmsEpisode.setPatientEpisodeName("Admission");
				patAdmsEpisode.setPatientEpisodeType(140); // 140 is the admission episode_type list id 
				patAdmsEpisode.setPatientEpisodeStartDate(admssDate);
				Timestamp patEpisodeCreatedtmp=new Timestamp(new Date().getTime());
				patAdmsEpisode.setPatientEpisodeCreatedDate(patEpisodeCreatedtmp);
				patAdmsEpisode.setPatientEpisodeCreatedBy(admission.getLoginId());
				patAdmsEpisode.setPatientEpisodeStatus(0);
				patAdmsEpisode.setPatientEpisodeIsactive(true);
				PatientEpisode patientEpisode=patientEpisodeRepository.save(patAdmsEpisode);
				newadmssObj.setAdmissionEpisode(patientEpisode.getPatientEpisodeId());
				
				admissionRepository.save(newadmssObj);
				admissionId= newadmssObj.getAdmissionId();
				try{
					returnEpisode= patientEpisode.getPatientEpisodeId().toString();
				}catch(Exception e){
					
				}
			}
			else{				
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				Date parsed=null;

				try {
					parsed = format.parse(admission.getAdmissionDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}

				java.sql.Date admssDate = new java.sql.Date(parsed.getTime());
				admssObj.setAdmissionId(admission.getAdmissionId());
				admssObj.setAdmissionAdmittedDate(admssDate);				
				admssObj.setAdmissionTime(admission.getAdmissionTime());
				admssObj.setAdmissionDoctorId(admission.getAdmssProvider());
				admssObj.setAdmissionPosId(admission.getPos());
				admssObj.setAdmissionPatientId(admission.getPatientId());
				admssObj.setAdmissionStatus(1);
				admssObj.setAdmissionRoom(admission.getRoomNo());
				admssObj.setAdmissionBlock(admission.getBlockNo());
				admssObj.setAdmissionNotes(admission.getNotes());				
				admissionRepository.save(admssObj);
				
				admissionId= admission.getAdmissionId();
				try{
					returnEpisode= admission.getAdmissionEpisode().toString();
				}catch(Exception e){
					
				}
			}
			
			List<PatientAdmission> existList= getPatientAdmission(admissionId,1);
			if(existList != null && existList.size()>0)
				deletePatientAdmission(existList);
			
			JSONArray admissionArr= null;
			try{
				admissionArr= new JSONArray(admission.getSelectedDx());
			}catch(Exception e){
				admissionArr= null;
			}
			if(admissionArr != null){
				for(int i=0; i<admissionArr.length(); i++){

					try{
						JSONObject dxJson= admissionArr.getJSONObject(i);
						if(dxJson != null){
							String dx= dxJson.getString("dx");
							String desc= dxJson.getString("value");
							String codesystem= dxJson.getString("codesystem");

							PatientAdmission patAdmission= new PatientAdmission();
							patAdmission.setPatientAdmissionAdmissionId(admissionId);
							patAdmission.setPatientAdmissionDxCode(dx);
							patAdmission.setPatientAdmissionDxDescription(desc);
							patAdmission.setPatientAdmissionDxCodingSystem(codesystem);
							patAdmission.setPatientAdmissionDxType(1);
							patientAdmissionRepository.save(patAdmission);
						}
					}catch(Exception e){

					}
				}
			}
			
			return new JSONObject().put("patientEpisode", returnEpisode+"#~#"+admissionId).toString();

		}catch(Exception e){
			e.printStackTrace();
			return "failure";
		}

	}

	/**
	 * Get recently closed Admission details 
	 */
	@Override
	public Admission getAdmissionPast(Integer patientId) {
		List<Admission> admission=admissionRepository.findAll(AdmissionSpecification.getPastAdmissionByPatientId(patientId));
		if(admission.size() > 0){
			return admission.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * Get open Admission details
	 */
	@Override
	public Admission getAdmission(Integer patientId) {
		List<Admission> admission=admissionRepository.findAll(AdmissionSpecification.getAdmissionByPatientId(patientId));
		if(admission.size() > 0){
			return admission.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public List<Object[]> getdischargeValues(Integer admissionId,Integer patientId) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<Admission> root = cq.from(Admission.class);
		Join<Admission, PatientAdmission> patjoin = root.join(Admission_.patientAdmission,JoinType.INNER);
		cq.multiselect(root.get(Admission_.admissionDischargeDate),
				root.get(Admission_.admissionDischargeDisposition),
				root.get(Admission_.admissionDischargeDispositionOther),
				builder.function("string_agg",String.class,patjoin.get(PatientAdmission_.patientAdmissionDxCode),builder.literal(",")),
				builder.function("string_agg",String.class,patjoin.get(PatientAdmission_.patientAdmissionDxDescription),builder.literal("@")));
		cq.where(builder.and(
				builder.equal(root.get(Admission_.admissionId), admissionId),
				builder.equal(root.get(Admission_.admissionPatientId), patientId),
				builder.equal(patjoin.get(PatientAdmission_.patientAdmissionDxType), 2)));
		cq.groupBy(root.get(Admission_.admissionDischargeDate),root.get(Admission_.admissionDischargeDisposition),root.get(Admission_.admissionDischargeDispositionOther));
		List<Object[]> dischargeValuesList = entityManager.createQuery(cq).getResultList();
		return dischargeValuesList;
}
	
/*	@Override
	public List<Admission> getPastAdmission(Integer patientId) {
		List<Admission> admission=admissionRepository.findAll(AdmissionSpecification.getPastAdmissionByPatientId(patientId));
		if(admission.size() > 0){
			return admission;
		}else{
			return Collections.<Admission>emptyList();
		}
	}*/

	/**
	 * Get patient episode details
	 * @param patEpisodeId
	 * @return
	 */
	public PatientEpisode getPatientEpisodebyEpisodeId(Integer patEpisodeId){
		
		List<PatientEpisode> patEpisode=null;
		if((patEpisode=patientEpisodeRepository.findAll(AdmissionSpecification.getPatientEpisodebyEpisodeId(patEpisodeId))).size()>0){
			return patEpisode.get(0);
		}else
			return null;
	}
	
	/**
	 * Discharge patient
	 */
	@Override
	public String dischargePatient(Integer patientId,Integer loginId,Integer userId) {

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			format.parse(format.format(date));
			java.sql.Date admssdisDate = new java.sql.Date(format.parse(format.format(date)).getTime());

			Admission admiss=getAdmission(patientId);
			if(admiss != null){
				admiss.setAdmissionDischargedDate(admssdisDate);
				admiss.setAdmissionDischargeDocId(userId);
				admiss.setAdmissionStatus(2);
				admissionRepository.saveAndFlush(admiss);
				
				
				//update admission episode status to 1 on discharge
				PatientEpisode patEpisode=null;
				if((patEpisode = getPatientEpisodebyEpisodeId(admiss.getAdmissionEpisode()))!=null){
					patEpisode.setPatientEpisodeStatus(1);
					patEpisode.setPatientEpisodeModifiedBy(loginId);
					patEpisode.setPatientEpisodeModifiedDate(new Timestamp(date.getTime()));
					patientEpisodeRepository.saveAndFlush(patEpisode);
				}
			}

			return "success";
		
		} catch (ParseException e) {
			e.printStackTrace();
			return "failure";
		}
	
	
	}

	/**
	 * Open template
	 * @param encounterId
	 * @param userId
	 * @return
	 */
	
	public List<Admission> openAdmissionTemplate(Integer encounterId, Integer userId) {
		
		return null;
	}

	/**
	 * Creating clinical note
	 */
	@Override
	public Encounter openAdmissionLeaf(AdmissionBean admission) {
		
		Encounter encEnt=new Encounter();
		encEnt.setEncounterChartid(admission.getChartId());
		encEnt.setEncounterDate(new Timestamp(new Date().getTime()));
		encEnt.setEncounterCreatedDate(new Timestamp(new Date().getTime()));
		encEnt.setEncounterType(new Short("5"));
		encEnt.setEncounterServiceDoctor(new Long(admission.getAdmssProvider()));
		encEnt.setEncounterPos(admission.getPos());
		encEnt.setEncounterReason(-1);
		encEnt.setEncounterStatus(new Short("3"));
		encEnt.setEncounterCreatedBy(new Long(admission.getLoginId()));
		encEnt.setEncounterClosedBy(new Long(admission.getLoginId()));
		encEnt.setEncounterClosedDate(encEnt.getEncounterCreatedDate());
		encEnt.setEncounterChargeable(false);
		encEnt.setEncounterAlreadySeen(false);
		encEnt.setEncounterInsReview(0);
		encEnt.setEncounterSeverity(new Short("1"));
		encEnt.setEncounterPatientEpisodeid(admission.getAdmissionEpisode());
		return encounterEntityRepository.save(encEnt);
		
		//openLeafForPatient(newenc.getEncounterId(),1695,admission);
	}

	private void openLeafForPatient(Integer encounterId,Integer leafLibrarId,AdmissionBean admission) {
		
		LeafPatient admissionLeaf = new LeafPatient();
		admissionLeaf.setLeafPatientId(leafPatientRepository.findAll(LeafPatientSpecfication.getLeafPatientOrderById()).get(0).getLeafPatientId()+1);
		admissionLeaf.setLeafPatientPatientId(admission.getPatientId());
		admissionLeaf.setLeafPatientEncounterId(encounterId);
		admissionLeaf.setLeafPatientLeafLibraryId(leafLibrarId);
		admissionLeaf.setLeafPatientCreatedBy(admission.getUserId());;
		admissionLeaf.setLeafPatientCreatedDate(new Timestamp(new Date().getTime()));
		admissionLeaf.setLeafPatientLastModyby(admission.getUserId());
		admissionLeaf.setLeafPatientLastmoddate(admissionLeaf.getLeafPatientCreatedDate());
		admissionLeaf.setLeafPatientIscomplete(false);
		admissionLeaf.setLeafPatientIsactive(true);
		admissionLeaf.setLeafPatientIsprinted(false);		
		admissionLeaf.setLeafPatientIsfaxed(false);
		admissionLeaf.setLeafPatientSignOne("");
		admissionLeaf.setLeafPatientProblemid(-1);
		admissionLeaf.setLeafPatientMaSignedUserid(-1);
		admissionLeaf.setLeafPatientPlanMode(cnmSettingsServiceImpl.getCNMSettingsIsActiveById(401));
		
		leafPatientRepository.save(admissionLeaf);
		
	}

	/**
	 * Get encounter leaf details
	 */
	@Override
	public List<Admission> getLeafDetails(Integer encounterId, Integer userId) {
		leafLibraryRepository.findAll(Specifications.where(AdmissionSpecification.A()).and(AdmissionSpecification.B(encounterId, userId)));
		//leafLibraryRepository.findAll(AdmissionSpecification.getFequentList(userId));
		return null;
	}

	/**
	 * Get Admission encounterIds
	 */
	@Override
	public String getAdmissionEncDetails(Integer admssEpisode) {
		List<Encounter> admssEnc=encounterEntityRepository.findAll(AdmissionSpecification.getAdmissionEncByEpisodeId(admssEpisode));
		JSONArray encDataJson= new JSONArray();
		for (int i=0;i<admssEnc.size();i++) {
			try {
				encDataJson.put(i, new JSONObject().put("encounterId",((Encounter)admssEnc.get(i)).getEncounterId()));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return encDataJson.toString();
	}

	/**
	 * Get Admission clinical notes
	 */
	@Override
	public AdmissionLeafBean getAdmissionLeafs(Integer admssEpisode) {
		List<LeafPatient> admissionLeafs = null;
		List<FaxOutbox> admssLeafFaxDet = null;
		List<PatientAllergies> admissionAllergiesDet = null;
		try {
			List<Encounter> admssEnc=encounterEntityRepository.findAll(AdmissionSpecification.getAdmissionEncByEpisodeId(admssEpisode));
			List<Integer> encounterIds = new ArrayList<Integer>();
			for (Encounter encounter : admssEnc) {
				encounterIds.add(encounter.getEncounterId());
			}
			if(encounterIds.size() >= 1){
				admissionLeafs =  leafPatientRepository.findAll(Specifications.where(AdmissionSpecification.getPatientLeafsByEncId(encounterIds)).and(AdmissionSpecification.getAdmissionPatientLeafsByEncId(encounterIds)));
				admssLeafFaxDet =  fax_outboxRepository.findAll(AdmissionSpecification.getLeafFaxDetails(encounterIds));
				admissionAllergiesDet = patientAllergyRepository.findAll(AdmissionSpecification.getPatientAllergiesList(encounterIds));
			}else{
				admissionLeafs = Collections.emptyList();
				admssLeafFaxDet = Collections.emptyList();
				admissionAllergiesDet = Collections.emptyList();
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		AdmissionLeafBean admissionLeafBean = new AdmissionLeafBean();
		admissionLeafBean.setAdmissionLeafBean(admissionLeafs);
		admissionLeafBean.setAdmissionLeafFaxBean(admssLeafFaxDet);
		admissionLeafBean.setAllergyBean(admissionAllergiesDet);
		return admissionLeafBean;
	}

	/**
	 * Get Rooms of selected block
	 */
	@Override
	public List<AdmissionRoom> getRooms(Integer blockId) {
		
		return admissionRoomRepository.findAll(AdmissionSpecification.getRooms(blockId));
	}
	
	/**
	 * Get Past admission dates
	 */
	@Override
	public List<String> getPastAdmissionDates(Integer patientId) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);
		Root<Admission> root= cq.from(Admission.class);
		cq.multiselect(root.get(Admission_.admissionId), root.get(Admission_.admissionDate), root.get(Admission_.admissionTime)).
			where(builder.equal(root.get(Admission_.admissionPatientId), patientId),builder.equal(root.get(Admission_.admissionStatus), 2)).
			orderBy(builder.desc(root.get(Admission_.admissionId)));
		
		List<Object[]> list=entityManager.createQuery(cq).getResultList();
		List<String> resultList= new ArrayList<String>();
		for(Object[] values : list) {
			try{
				Integer admissionId= Integer.parseInt(values[0].toString());
				SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
				Date dateStr= null;
				try {
					if(values[1] != null)
						dateStr = format.parse(values[1].toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}		
				if(dateStr != null){
					String admissionDate= new SimpleDateFormat("MM/dd/yyyy").format(dateStr);
					String admissionTime= values[2]!= null ? values[2].toString(): "";
					resultList.add(admissionId+"###"+admissionDate+" "+admissionTime.trim());
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return resultList;
	}

	/**
	 * Get selected past admission details
	 */
	@Override
	public Admission getPastAdmission(Integer admissionId) {
		return admissionRepository.findOne(AdmissionSpecification.byPastAdmissionId(admissionId));
	}

	/**
	 * Save discharge details- Diagnosis at admission and Diagnosis at discharge
	 */
	@Override
	public void saveDishcargeDetails(AdmissionBean dataJson) throws JSONException {
		
		List<PatientAdmission> existList= getPatientAdmission(dataJson.getAdmissionId(),2);
		if(existList != null && existList.size()>0)
			deletePatientAdmission(existList);
		
		/*JSONArray admissionArr= null;
		try{
			admissionArr= new JSONArray(dataJson.getSelectedDx());
		}catch(Exception e){
			admissionArr= null;
		}
		if(admissionArr != null){
			for(int i=0; i<admissionArr.length(); i++){

				try{
					JSONObject dxJson= admissionArr.getJSONObject(i);
					if(dxJson != null){
						String dx= dxJson.getString("dx");
						String desc= dxJson.getString("value");
						String codesystem= dxJson.getString("codesystem");

						PatientAdmission patAdmission= new PatientAdmission();
						patAdmission.setPatientAdmissionAdmissionId(dataJson.getAdmissionId());
						patAdmission.setPatientAdmissionDxCode(dx);
						patAdmission.setPatientAdmissionDxDescription(desc);
						patAdmission.setPatientAdmissionDxCodingSystem(codesystem);
						patAdmission.setPatientAdmissionDxType(1);
						patientAdmissionRepository.save(patAdmission);
					}
				}catch(Exception e){

				}
			}
		}*/
		
		JSONArray dischargeArr= null;
		try{
			dischargeArr= new JSONArray(dataJson.getDischargeDx());
		}catch(Exception e){
			dischargeArr= null;
		}
		if(dischargeArr != null){
			for(int i=0; i<dischargeArr.length(); i++){

				try{
					JSONObject dxJson= dischargeArr.getJSONObject(i);
					if(dxJson != null){
						String dx= dxJson.getString("dx");
						String desc= dxJson.getString("value");
						String codesystem= dxJson.getString("codesystem");

						PatientAdmission patAdmission= new PatientAdmission();
						patAdmission.setPatientAdmissionAdmissionId(dataJson.getAdmissionId());
						patAdmission.setPatientAdmissionDxCode(dx);
						patAdmission.setPatientAdmissionDxDescription(desc);
						patAdmission.setPatientAdmissionDxCodingSystem(codesystem);
						patAdmission.setPatientAdmissionDxType(2);
						patientAdmissionRepository.save(patAdmission);
					}
				}catch(Exception e){

				}
			}
		}
		dischargePatientDetails(dataJson.getAdmissionId(),dataJson.getPatientId(),dataJson.getLoginId(),dataJson.getUserId(), dataJson.getDischargeDate(),dataJson.getDispositionvalue(),dataJson.getDispositionText());
	}
	
	@Override
	public String dischargePatientDetails(Integer admissionId,Integer patientId, Integer loginId,Integer userId, String date, String dispositionvalue, String dispositionText) {		
		try {
			
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date disdate=null;

			try {
				disdate = format.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			java.sql.Date dischargeDate = new java.sql.Date(disdate.getTime());
			Admission admiss=getAdmission(patientId);
			
			/*admiss.setAdmissionDischargedDate(dischargeDate);
			admiss.setAdmissionDischargeDocId(userId);
			admiss.setAdmissionStatus(2);
			admiss.setadmissionDischargeDisposition(dispositionvalue);
			admiss.setadmissionDischargeDispositionOther(dispositionText);
			admissionRepository.saveAndFlush(admiss);*/
			
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaUpdate<Admission> update = builder.createCriteriaUpdate(Admission.class);
			Root<Admission> root = update.from(Admission.class);
			update.set(root.get(Admission_.admissionDischargeDate), dischargeDate);
			update.set(root.get(Admission_.admissionDischargeDocId), userId);
			update.set(root.get(Admission_.admissionStatus), 2);
			update.set(root.get(Admission_.admissionDischargeDisposition), dispositionvalue);
			update.set(root.get(Admission_.admissionDischargeDispositionOther), dispositionText);
			update.where(builder.equal(root.get(Admission_.admissionId), admissionId));
			this.entityManager.createQuery(update).executeUpdate();
			
			if(admiss != null){
				//update admission episode status to 1 on discharge
				PatientEpisode patEpisode=null;
				if((patEpisode = getPatientEpisodebyEpisodeId(admiss.getAdmissionEpisode()))!=null){
					patEpisode.setPatientEpisodeStatus(1);
					patEpisode.setPatientEpisodeModifiedBy(loginId);
					patEpisode.setPatientEpisodeModifiedDate(new Timestamp(disdate.getTime()));
					patientEpisodeRepository.saveAndFlush(patEpisode);
				}
			}

			return "success";
		
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
	
	private List<PatientAdmission> getPatientAdmission(Integer admissionId){
		return patientAdmissionRepository.findAll(AdmissionSpecification.byPatientAdmissionId(admissionId));
	}
	
	private List<PatientAdmission> getPatientAdmission(Integer admissionId, Integer type){
		return patientAdmissionRepository.findAll(AdmissionSpecification.byPatientAdmissionId(admissionId, type));
	}
	
	private void deletePatientAdmission(List<PatientAdmission> admission){
		patientAdmissionRepository.delete(admission);
	}
}
