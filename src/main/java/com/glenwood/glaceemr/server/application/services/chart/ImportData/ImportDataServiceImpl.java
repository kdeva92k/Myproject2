package com.glenwood.glaceemr.server.application.services.chart.ImportData;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.AftercareIns;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.PatientAftercareData;
import com.glenwood.glaceemr.server.application.models.PatientAftercareData_;
import com.glenwood.glaceemr.server.application.models.PatientAssessments;
import com.glenwood.glaceemr.server.application.models.PatientAssessments_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalDxElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalDxElements_;
import com.glenwood.glaceemr.server.application.models.PlanInstruction;
import com.glenwood.glaceemr.server.application.models.PlanInstruction_;
import com.glenwood.glaceemr.server.application.models.PrimarykeyGenerator;
import com.glenwood.glaceemr.server.application.models.PrimarykeyGenerator_;
import com.glenwood.glaceemr.server.application.repositories.EncounterEntityRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientAftercareDataRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientAssessmentsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalDxElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.PrimarykeyGeneratorRepository;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalElementsService;
import com.glenwood.glaceemr.server.application.specifications.EncounterEntitySpecification;
import com.glenwood.glaceemr.server.application.specifications.PlanSpecification;
import com.glenwood.glaceemr.server.utils.HUtil;

@Service
@Transactional
public class ImportDataServiceImpl implements ImportDataService{


	@Autowired
	EncounterEntityRepository encounterEntityRepository;

	@Autowired
	ClinicalElementsService clinicalElementsService;

	@Autowired
	PatientClinicalDxElementsRepository patientClinicalDxElementsRepository;
	
	@Autowired
	PatientAssessmentsRepository h611Repository;
	
	@Autowired
	PrimarykeyGeneratorRepository h213Repository;
	
	@Autowired
	PatientAftercareDataRepository patientAftercareDataRepository;
	
	@PersistenceContext
	EntityManager em;
	
	/**
	 * Get previous encounter list 
	 * @param patientId
	 * @param encounterId
	 * @return
	 */
	@Override
	public String getImportEncList(Integer patientId, Integer encounterId) {
		List<Encounter> encounters=encounterEntityRepository.findAll(EncounterEntitySpecification.getPrevEncHavingData(patientId,encounterId));
		List<Integer> encList=new ArrayList<Integer>();
		JSONArray encounterList=new JSONArray();
		for(int k=0;k<encounters.size();k++){
			try {
				if(!encList.contains(encounters.get(k).getEncounterId())){
					encList.add(encounters.get(k).getEncounterId());
					JSONObject enc=new JSONObject();
					enc.put("encounterid",encounters.get(k).getEncounterId());
					String encdate = new SimpleDateFormat("MM/dd/yyyy").format(encounters.get(k).getEncounterDate());
					enc.put("encounterdate",encdate+" "+encounters.get(k).getEncounterChiefcomp());
					encounterList.put(enc);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}


		return encounterList.toString();
	}

	/**
	 * Import data from previous encounter 
	 * @param patientId
	 * @param encounterId
	 * @param templateId
	 * @param chartId
	 * @param tabId
	 * @param prevEncounterId
	 * @param dxSpecific
	 * @param dxCode
	 */
	@Override
	public void importData(Integer patientId, Integer encounterId,
			Integer templateId, Integer chartId, Integer tabId,
			Integer prevEncounterId, String dxSpecific, String dxCode,
			Integer userId) {
		
		
		List<String> mappedGwids=clinicalElementsService.delPatientElementByEncID(patientId,encounterId,tabId,templateId,prevEncounterId);
		clinicalElementsService.deleteNotesData(patientId,encounterId,tabId,templateId);
		clinicalElementsService.insertDataForImport(patientId,encounterId,prevEncounterId,tabId,templateId,mappedGwids);
		if(tabId==7 && dxSpecific.equalsIgnoreCase("t")){
			importAssessments(patientId, chartId, encounterId, prevEncounterId, userId);
			importHandouts(patientId, encounterId, prevEncounterId);
		}
	}

	/**
	 * Importing handouts
	 * @param patientId
	 * @param encounterId
	 * @param prevEncounterId
	 */
	private void importHandouts(Integer patientId, Integer encounterId,
			Integer prevEncounterId) {
		deleteDxHandouts(patientId, encounterId);
		List<PatientAftercareData> previousHandouts= getPreviousHandouts(patientId, prevEncounterId);
		for(int i=0; i<previousHandouts.size(); i++){
			PatientAftercareData aftercareBean= previousHandouts.get(i);
			int max= getPatientAftercareMaxid();
			PatientAftercareData newRow= new PatientAftercareData(max, patientId, encounterId, aftercareBean.getPatientAftercareDataAftercareId(), aftercareBean.getPatientAftercareDataUnknown1(), aftercareBean.getPatientAftercareDataName(), aftercareBean.getPatientAftercareDataDirty(), aftercareBean.getPatientAftercareDataStatus(), aftercareBean.getPatientAftercareDataUrl(), aftercareBean.getPatientAftercareDataCategory(), aftercareBean.getPatientAftercareDataDxcode(), aftercareBean.getPatientAftercareDataDxcodesystem());
			patientAftercareDataRepository.saveAndFlush(newRow);
		}
	}

	/**
	 * Get max id for patient aftercare data
	 * @return
	 */
	private int getPatientAftercareMaxid() {

		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Integer> query= builder.createQuery(Integer.class);
		Root<PatientAftercareData> root= query.from(PatientAftercareData.class);
		
		query.select(builder.coalesce(builder.max(root.get(PatientAftercareData_.patientAftercareDataId)),1));
		
		int returnVal= 1;
		try{
			returnVal= em.createQuery(query).getSingleResult();
		}catch(NoResultException e){			
			return 1;
		}
		return returnVal+1;
	
	}

	/**
	 * Get encounter handouts
	 * @param patientId
	 * @param encounterId
	 * @return
	 */
	private List<PatientAftercareData> getPreviousHandouts(Integer patientId,
			Integer encounterId) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<PatientAftercareData> query= builder.createQuery(PatientAftercareData.class);
		Root<PatientAftercareData> root= query.from(PatientAftercareData.class);
		Join<PatientAftercareData, AftercareIns> insJoin= root.join(PatientAftercareData_.aftercareIns, JoinType.INNER);
		
		query.distinct(true);
		
		query.select(builder.construct(PatientAftercareData.class,
			root.get(PatientAftercareData_.patientAftercareDataId),
			 root.get(PatientAftercareData_.patientAftercareDataPatientId),
			  root.get(PatientAftercareData_.patientAftercareDataEncounterId),
			   root.get(PatientAftercareData_.patientAftercareDataAftercareId),
			    root.get(PatientAftercareData_.patientAftercareDataUnknown1),
			     root.get(PatientAftercareData_.patientAftercareDataName),
			      root.get(PatientAftercareData_.patientAftercareDataDirty),
			       root.get(PatientAftercareData_.patientAftercareDataStatus),
					root.get(PatientAftercareData_.patientAftercareDataUrl),
					 root.get(PatientAftercareData_.patientAftercareDataCategory),
					  root.get(PatientAftercareData_.patientAftercareDataDxcode),
					   root.get(PatientAftercareData_.patientAftercareDataDxcodesystem)));
		
		query.where(builder.and(builder.equal(root.get(PatientAftercareData_.patientAftercareDataEncounterId), encounterId),
								 builder.equal(root.get(PatientAftercareData_.patientAftercareDataPatientId), patientId),
								  builder.equal(root.get(PatientAftercareData_.patientAftercareDataStatus), true)));
		
		
		List<PatientAftercareData> result= null;
		try{
			result= em.createQuery(query).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return result;
	}

	private void deleteDxHandouts(Integer patientId, Integer encounterId) {		
		List<PatientAftercareData> handouts= getHandouts(patientId, encounterId);
		if(handouts.size()>0)
			patientAftercareDataRepository.delete(handouts);
	}

	private List<PatientAftercareData> getHandouts(Integer patientId, Integer encounterId) {
		return patientAftercareDataRepository.findAll(PlanSpecification.getHandouts(patientId, encounterId));
	}

	private void importDxPlan(Integer patientId, Integer encounterId,
			Integer templateId, Integer chartId, Integer prevEncounterId,
			String dxCode, Integer userId) {
		
		deleteDxIns(patientId, chartId, encounterId, dxCode);
		String notes= getDxNotes(patientId, prevEncounterId, dxCode);
		updateDxNotes(encounterId, patientId, dxCode, notes);
		int max= getAssessmentId(encounterId, patientId, dxCode.trim());
		List<PatientClinicalDxElements> result = getPreviousDxInstructions(patientId, prevEncounterId, dxCode.trim());
		for(int j=0; j<result.size(); j++){
			PatientClinicalDxElements resultRow= result.get(j);
			PatientClinicalDxElements patdxObj= new PatientClinicalDxElements(max, patientId, chartId, encounterId, resultRow.getPatientClinicalDxElementsGwid(), resultRow.getPatientClinicalDxElementsCode(), resultRow.getPatientClinicalDxElementsCodesystem(), resultRow.getPatientClinicalDxElementsValue());
			patdxObj.setPatientClinicalDxElementsLastModifiedBy(userId);
			patdxObj.setPatientClinicalDxElementsLastModifiedOn(new Timestamp(new Date().getTime()));
			patientClinicalDxElementsRepository.saveAndFlush(patdxObj);
		}
	}

	/**
	 * Get Dx notes
	 * @param patientId
	 * @param encounterId
	 * @param dxCode
	 * @return
	 */
	private String getDxNotes(Integer patientId, Integer encounterId,
			String dxCode) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<String> query= builder.createQuery(String.class);
		Root<PatientAssessments> root= query.from(PatientAssessments.class);
		
		query.select(builder.coalesce(root.get(PatientAssessments_.planNotes),""));
		query.where(builder.and(builder.equal(root.get(PatientAssessments_.patient_assessments_id), encounterId),
								 builder.equal(root.get(PatientAssessments_.patient_assessments_patientId), patientId),
								  builder.like(builder.lower(builder.trim(root.get(PatientAssessments_.patient_assessments_dxcode))), dxCode.trim().toLowerCase())));
		
		String returnVal= "";
		try{
			returnVal= em.createQuery(query).getSingleResult();
		}catch(NoResultException e){			
			return "";
		}
		return returnVal;
	}

	/**
	 * Importing previous Assessments and Dx instrutions
	 * @param patientId
	 * @param chartId
	 * @param encounterId
	 * @param prevEncounterId
	 * @param userId 
	 */
	private void importAssessments(Integer patientId, Integer chartId,
			Integer encounterId, Integer prevEncounterId, Integer userId) {
		Timestamp encDate= getEncounterDate(encounterId);
		int order= getMaxOrder(encounterId);
		List<PatientAssessments> prevDxlist= getEncounterAssessments(prevEncounterId);
		List<PatientAssessments> curDxlist= getEncounterAssessments(encounterId);
		deleteDxIns(patientId, chartId, encounterId, "");
		if(prevDxlist != null){
			for(int i=0; i< prevDxlist.size(); i++) {
				PatientAssessments res = prevDxlist.get(i);
				String oldCode = res.getpatient_assessments_dxcode() != null? res.getpatient_assessments_dxcode().toString() : null;
				int flag = 0;
				for(int j=0; j< curDxlist.size(); j++) {

					PatientAssessments curres = curDxlist.get(j);
					String newCode = curres.getpatient_assessments_dxcode() != null? curres.getpatient_assessments_dxcode().toString() : null;

					if(oldCode != null && newCode != null) {
						if(oldCode.trim().equalsIgnoreCase(newCode.trim())) {
							flag = 1;
							break;
						}
					}
				}
				if(flag == 0) {
					int orderRow;
					try {
						orderRow = Integer.parseInt(res.getpatient_assessments_dxorder().toString());
					} catch(Exception e) {
						orderRow = 1;
					}
					int orderVal = order + orderRow;

					Timestamp now= new Timestamp(new Date().getTime());
					
					int maxId= getMaxDxId();
					PatientAssessments newObj= new PatientAssessments(maxId, encounterId, res.getpatient_assessments_patientId(), encDate,
							res.getpatient_assessments_dxcode(), res.getpatient_assessments_dxdescription(), res.getpatient_assessments_scribble(), res.getpatient_assessments_isactive(),
							res.getH555555(), res.getpatient_assessments_dxtype(), orderVal, now,
							res.getpatient_assessments_userid(), now, res.getpatient_assessments_lastmodifiedby(), res.getpatient_assessments_assessmentcomment(),
							res.getpatient_assessments_status(), res.getpatient_assessmentsCodingSystemid(), res.getAssessmentDxcodesystem(), res.getPlanNotes());

					h611Repository.save(newObj);
					updateDxSequence(maxId);

					List<PatientClinicalDxElements> result = getPreviousDxInstructions(patientId, prevEncounterId, HUtil.Nz(res.getpatient_assessments_dxcode(),""));
					for(int j=0; j<result.size(); j++){
						PatientClinicalDxElements resultRow= result.get(j);
						PatientClinicalDxElements patdxObj= new PatientClinicalDxElements(maxId, patientId, chartId, encounterId, resultRow.getPatientClinicalDxElementsGwid(), resultRow.getPatientClinicalDxElementsCode(), resultRow.getPatientClinicalDxElementsCodesystem(), resultRow.getPatientClinicalDxElementsValue());
						patdxObj.setPatientClinicalDxElementsLastModifiedBy(userId);
						patdxObj.setPatientClinicalDxElementsLastModifiedOn(new Timestamp(new Date().getTime()));
						patientClinicalDxElementsRepository.saveAndFlush(patdxObj);
					}


				}
				else {
					
					updateDxNotes(encounterId, patientId, res.getpatient_assessments_dxcode(), res.getPlanNotes());
					int max= getAssessmentId(encounterId, patientId, res.getpatient_assessments_dxcode());
					List<PatientClinicalDxElements> result = getPreviousDxInstructions(patientId, prevEncounterId, HUtil.Nz(res.getpatient_assessments_dxcode(),""));
					for(int j=0; j<result.size(); j++){
						PatientClinicalDxElements resultRow= result.get(j);
						PatientClinicalDxElements patdxObj= new PatientClinicalDxElements(max, patientId, chartId, encounterId, resultRow.getPatientClinicalDxElementsGwid(), resultRow.getPatientClinicalDxElementsCode(), resultRow.getPatientClinicalDxElementsCodesystem(), resultRow.getPatientClinicalDxElementsValue());
						patdxObj.setPatientClinicalDxElementsLastModifiedBy(userId);
						patdxObj.setPatientClinicalDxElementsLastModifiedOn(new Timestamp(new Date().getTime()));
						patientClinicalDxElementsRepository.saveAndFlush(patdxObj);
					}
				}
			}
		}
	}

	/**
	 * Updating sequence for H611 table
	 * @param max
	 */
	private void updateDxSequence(int max) {
		
		PrimarykeyGenerator seqObj=  h213Repository.findOne(PlanSpecification.getDxSequence());
		if(seqObj!= null){
			seqObj.setprimarykey_generator_rowcount(max);
			h213Repository.save(seqObj);
		}
	}

	/**
	 * Get sequence for H611 table
	 * @return
	 */
	private int getMaxDxId() {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Integer> query= builder.createQuery(Integer.class);
		Root<PrimarykeyGenerator> root= query.from(PrimarykeyGenerator.class);
		
		query.select(builder.coalesce(root.get(PrimarykeyGenerator_.primarykey_generator_rowcount),1));
		query.where(builder.equal(root.get(PrimarykeyGenerator_.primarykey_generator_tablename), "h611"));
		
		int returnVal= 1;
		try{
			returnVal= em.createQuery(query).getSingleResult();
		}catch(NoResultException e){			
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 1;
		}
		return returnVal+1;
	}

	/**
	 * Get Assessment id 
	 * @param encounterId
	 * @param patientId
	 * @param dxCode
	 * @return
	 */
	private Integer getAssessmentId(Integer encounterId, Integer patientId,
			String dxCode) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Integer> query= builder.createQuery(Integer.class);
		Root<PatientAssessments> root= query.from(PatientAssessments.class);
		
		query.select(builder.coalesce(root.get(PatientAssessments_.patient_assessments_id),-1));
		
		query.where(builder.and(builder.equal(root.get(PatientAssessments_.patient_assessments_id), encounterId),
				builder.and(builder.equal(root.get(PatientAssessments_.patient_assessments_patientId), patientId),
				 builder.like(builder.lower(builder.trim(root.get(PatientAssessments_.patient_assessments_dxcode))), dxCode.trim().toLowerCase()))));
		
		int returnVal= -1;
		try{
			returnVal= em.createQuery(query).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return returnVal;
		
	}

	/**
	 * Get Dx Notes based on Encounter
	 * @param encounterId
	 * @param patientId
	 * @param patient_assessments_dxcode
	 */
	private void updateDxNotes(Integer encounterId, Integer patientId, String dxCode, String notes) {
		
		if(dxCode== null)
			dxCode= "";		
		updateNotes(getAssessments(encounterId, patientId, dxCode, notes), notes);
	}


	private List<PatientAssessments> getAssessments(Integer encounterId, Integer patientId, String dxCode, String notes) {
		return h611Repository.findAll(PlanSpecification.getAssessments(encounterId, patientId, dxCode));
	}

	private void updateNotes(List<PatientAssessments> dxlist, String notes) {
		
		if(dxlist != null){
			for(int i=0; i<dxlist.size(); i++){
				PatientAssessments dxRow= dxlist.get(i);
				dxRow.setPlanNotes(notes);
				h611Repository.save(dxRow);
			}
		}
	}

	/**
	 * Get dx instructions
	 * @param patientId
	 * @param encounterId
	 * @param dxCode
	 * @return
	 */
	private List<PatientClinicalDxElements> getPreviousDxInstructions(Integer patientId,
			Integer encounterId, String dxCode) {
		
		if(dxCode== null)
			dxCode= "";
		else
			dxCode= dxCode.toLowerCase().trim();
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<PatientClinicalDxElements> query= builder.createQuery(PatientClinicalDxElements.class);
		Root<PatientClinicalDxElements> root= query.from(PatientClinicalDxElements.class);
		Join<PatientClinicalDxElements, PlanInstruction> insJoin= root.join(PatientClinicalDxElements_.planInstruction, JoinType.INNER);
		insJoin.join(PlanInstruction_.planMappings, JoinType.LEFT);
		
		query.distinct(true);
		
		query.select(builder.construct(PatientClinicalDxElements.class,
			root.get(PatientClinicalDxElements_.patientClinicalDxElementsId),
			 root.get(PatientClinicalDxElements_.patientClinicalDxElementsPatientid),
			  root.get(PatientClinicalDxElements_.patientClinicalDxElementsChartid),
			   root.get(PatientClinicalDxElements_.patientClinicalDxElementsEncounterid),
			    root.get(PatientClinicalDxElements_.patientClinicalDxElementsGwid),
			     root.get(PatientClinicalDxElements_.patientClinicalDxElementsCode),
			      root.get(PatientClinicalDxElements_.patientClinicalDxElementsCodesystem),
			       root.get(PatientClinicalDxElements_.patientClinicalDxElementsValue)));
		
		query.where(builder.and(builder.equal(root.get(PatientClinicalDxElements_.patientClinicalDxElementsEncounterid), encounterId),
								 builder.equal(root.get(PatientClinicalDxElements_.patientClinicalDxElementsPatientid), patientId),
								  builder.like(builder.lower(builder.trim(root.get(PatientClinicalDxElements_.patientClinicalDxElementsCode))), dxCode)));
		
		
		List<PatientClinicalDxElements> result= null;
		try{
			result= em.createQuery(query).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Delete dx plan instructions
	 * @param patientId
	 * @param chartId
	 * @param encounterId
	 * @param string
	 */
	private void deleteDxIns(Integer patientId, Integer chartId,
			Integer encounterId, String dxCode) {
		
		patientClinicalDxElementsRepository.deleteInBatch(getPatientClinicalDxElements(patientId, chartId, encounterId, dxCode));
	}

	/**
	 * Get Patient clinical Dx elements
	 * @param patientId
	 * @param chartId
	 * @param encounterId
	 * @param dxCode
	 * @return
	 */
	private List<PatientClinicalDxElements> getPatientClinicalDxElements(
			Integer patientId, Integer chartId, Integer encounterId,
			String dxCode) {

		return patientClinicalDxElementsRepository.findAll(PlanSpecification.getPatientClinicalDxElements(patientId, chartId, encounterId, dxCode.trim()));
	}

	/**
	 * Get Assessments based on Encounter 
	 * @param encounterId
	 */
	private List<PatientAssessments> getEncounterAssessments(Integer encounterId) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<PatientAssessments> query= builder.createQuery(PatientAssessments.class);
		Root<PatientAssessments> root= query.from(PatientAssessments.class);
		
		query.select(builder.construct(PatientAssessments.class,
			root.get(PatientAssessments_.patient_assessments_id),root.get(PatientAssessments_.patient_assessments_id),root.get(PatientAssessments_.patient_assessments_patientId),
			 root.get(PatientAssessments_.patient_assessments_encounterdate),root.get(PatientAssessments_.patient_assessments_dxcode),root.get(PatientAssessments_.patient_assessments_dxdescription),
			  root.get(PatientAssessments_.patient_assessments_scribble),root.get(PatientAssessments_.patient_assessments_isactive),root.get(PatientAssessments_.h555555),
			   root.get(PatientAssessments_.patient_assessments_dxtype),root.get(PatientAssessments_.patient_assessments_dxorder),root.get(PatientAssessments_.patient_assessments_createdon),
			    root.get(PatientAssessments_.patient_assessments_userid),root.get(PatientAssessments_.patient_assessments_lastmodifiedon),root.get(PatientAssessments_.patient_assessments_lastmodifiedby),
			     root.get(PatientAssessments_.patient_assessments_assessmentcomment),root.get(PatientAssessments_.patient_assessments_status),root.get(PatientAssessments_.patient_assessmentsCodingSystemid),
			      root.get(PatientAssessments_.assessmentDxcodesystem),root.get(PatientAssessments_.planNotes)));
		
		query.where(builder.equal(root.get(PatientAssessments_.patient_assessments_id), encounterId));
		
		query.orderBy(builder.asc(root.get(PatientAssessments_.patient_assessments_dxorder)));
		
		List<PatientAssessments> result= null;
		try{
			result= em.createQuery(query).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Get Max order from Assessments
	 * @param encounterId
	 * @return
	 */
	private int getMaxOrder(Integer encounterId) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Integer> query= builder.createQuery(Integer.class);
		Root<PatientAssessments> root= query.from(PatientAssessments.class);
		
		query.select(builder.coalesce(builder.max(root.get(PatientAssessments_.patient_assessments_dxorder)),1));
		query.where(builder.equal(root.get(PatientAssessments_.patient_assessments_id), encounterId));
		
		int returnVal= 1;
		try{
			returnVal= em.createQuery(query).getSingleResult();
		}catch(NoResultException e){			
			return 1;
		}
		return returnVal+1;
	}

	/**
	 * Get Encounter date
	 * @param encounterId
	 * @return
	 */
	private Timestamp getEncounterDate(Integer encounterId) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Timestamp> query= builder.createQuery(Timestamp.class);
		Root<Encounter> root= query.from(Encounter.class);
		
		query.select(root.get(Encounter_.encounterDate));
		query.where(builder.equal(root.get(Encounter_.encounterId), encounterId));
		
		Timestamp returnVal= null;
		try{
			returnVal= em.createQuery(query).getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return returnVal;
	}
}
