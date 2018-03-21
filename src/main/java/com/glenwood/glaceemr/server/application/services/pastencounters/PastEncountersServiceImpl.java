package com.glenwood.glaceemr.server.application.services.pastencounters;

import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions_;
import com.glenwood.glaceemr.server.application.models.ClinicalElements_;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping_;
import com.glenwood.glaceemr.server.application.models.CurrentMedication;
import com.glenwood.glaceemr.server.application.models.CurrentMedication_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.EncounterPlan;
import com.glenwood.glaceemr.server.application.models.EncounterPlan_;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.AppReferenceValues;
import com.glenwood.glaceemr.server.application.models.AppReferenceValues_;
import com.glenwood.glaceemr.server.application.models.ReferralDetails;
import com.glenwood.glaceemr.server.application.models.ReferralDetails_;
import com.glenwood.glaceemr.server.application.models.PatientAssessments;
import com.glenwood.glaceemr.server.application.models.PatientAssessments_;
import com.glenwood.glaceemr.server.application.models.LabEntries;
import com.glenwood.glaceemr.server.application.models.LabEntries_;
import com.glenwood.glaceemr.server.application.models.LabGroups;
import com.glenwood.glaceemr.server.application.models.LabGroups_;
import com.glenwood.glaceemr.server.application.models.LeafLibrary;
import com.glenwood.glaceemr.server.application.models.LeafLibrary_;
import com.glenwood.glaceemr.server.application.models.LeafPatient;
import com.glenwood.glaceemr.server.application.models.LeafPatient_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosTable_;
import com.glenwood.glaceemr.server.application.models.Prescription;
import com.glenwood.glaceemr.server.application.models.Prescription_;
import com.glenwood.glaceemr.server.application.models.ReturnVisit;
import com.glenwood.glaceemr.server.application.models.ReturnVisit_;
import com.glenwood.glaceemr.server.application.models.UnitsOfMeasure;
import com.glenwood.glaceemr.server.application.models.UnitsOfMeasure_;
import com.glenwood.glaceemr.server.application.models.VitalGroup;
import com.glenwood.glaceemr.server.application.models.VitalGroup_;
import com.glenwood.glaceemr.server.application.models.VitalsParameter;
import com.glenwood.glaceemr.server.application.models.VitalsParameter_;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalConstants;
import com.glenwood.glaceemr.server.utils.HUtil;

@Service
public class PastEncountersServiceImpl implements PastEncountersService{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public String getPastEncounters(Integer patientId, Integer chartId) throws JSONException {
		
		try{
			if(chartId==-1)
				chartId= getChartId(patientId);

			List<Object[]> result= getPastEncounters(chartId);
			return parsePastEncounters(result);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get list of past encounters
	 * @param chartId
	 * @return
	 */
	private List<Object[]> getPastEncounters(Integer chartId) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<Encounter> root= query.from(Encounter.class);
		Join<Encounter, EmployeeProfile> empJoin= root.join(Encounter_.empProfileEmpId, JoinType.LEFT);
		Join<Encounter, LeafPatient> leafJoin= root.join(Encounter_.leafPatient, JoinType.LEFT);
		Join<LeafPatient, LeafLibrary> libraryJoin= leafJoin.join(LeafPatient_.leafLibraryTable, JoinType.LEFT);
		
		query.multiselect(root.get(Encounter_.encounterId),
						   root.get(Encounter_.encounterChiefcomp),
						   	root.get(Encounter_.encounterDate),
						   	 empJoin.get(EmployeeProfile_.empProfileFullname),
						   	  libraryJoin.get(LeafLibrary_.leafLibraryName),
						   	   libraryJoin.get(LeafLibrary_.leafLibraryId),
						   	    libraryJoin.get(LeafLibrary_.leafLibrarySoaptemplateId),
						   	     leafJoin.get(LeafPatient_.leafPatientIscomplete),
						   	      leafJoin.get(LeafPatient_.leafPatientId));
		
		query.where(builder.equal(root.get(Encounter_.encounterChartid), chartId),
					 builder.equal(root.get(Encounter_.encounterType), 1),
					  builder.notEqual(root.get(Encounter_.encounterStatus), 1));
		
		query.orderBy(builder.desc(root.get(Encounter_.encounterId)),
					   builder.asc(leafJoin.get(LeafPatient_.leafPatientLastmoddate)));
		
		List<Object[]> result= null;
		try{
			result= em.createQuery(query).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Parsing results into JSON
	 * @param result
	 * @return
	 * @throws JSONException
	 */
	public String parsePastEncounters(List<Object[]> result) throws JSONException{
		JSONArray encounterList= new JSONArray();		
		for(int i=0; i<result.size();){			
			JSONObject encounter= new JSONObject();
			Object[] resultRow= result.get(i);
			int encounterId= Integer.parseInt(resultRow[0].toString());
			encounter.put("encounterId", encounterId);
			encounter.put("encounterCC", resultRow[1]!=null? resultRow[1].toString(): "");
			encounter.put("encounterDate", resultRow[2]);
			encounter.put("serviceDoctor", resultRow[3]!=null? resultRow[3].toString(): "");
			JSONArray templateList= new JSONArray();
			for(int j=i; j<result.size(); j++){				
				Object[] row= result.get(j);
				int nxtEncId= Integer.parseInt(row[0].toString());				
				if(encounterId== nxtEncId){
					if(row[8]!=null){
						JSONObject template= new JSONObject();
						template.put("templatename", row[4]!=null? row[4]: "");
						template.put("formId", row[5]);
						template.put("templateId", row[6]);
						template.put("isSigned", row[7]);
						template.put("leafId", row[8]);
						templateList.put(template);
					}
				}else
					break;
			}
			encounter.put("clinicalTemplates", templateList);
			if(templateList.length()> 0){
				encounter.put("isTemplates", "yes");
				i= i+templateList.length();
			}
			else{
				encounter.put("isTemplates", "no");
				i= i+1;
			}
			encounterList.put(encounter);
			
		}
		return encounterList.toString();
	}
	
	/**
	 * Get chartId from patientId
	 * @param patientId
	 * @return
	 */
	public Integer getChartId(int patientId){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Chart> root = cq.from(Chart.class);
		cq.select(root.get(Chart_.chartId));
		cq.where(builder.equal(root.get(Chart_.chartPatientid), patientId));
		List <Object> resultList = em.createQuery(cq).getResultList();
		Integer chartId= -1;
		if(resultList.size()>0)
			chartId= Integer.parseInt(resultList.get(0).toString());
		return chartId;
	}

	@Override
	public String getVisitSummary(Integer patientId, Integer chartId,
			Integer encounterId) throws JSONException {
		JSONObject summary= new JSONObject();
		
		List<Object[]> vitals= getVitals(encounterId);
		JSONArray vitalsArr= parseVitals(vitals);
		
		List<Object[]> diagnosis= getDiagnosis(encounterId);
		JSONArray dxArr= parseDiagnosis(diagnosis);
		
		List<Object[]> encounterDetails= getEncounterDetails(encounterId, chartId);		
		
		String encDate= "";
		if(encounterDetails != null && encounterDetails.size()>0){
			Object[] encounter= encounterDetails.get(0);
			summary.put("encounterId", encounter[0]);
			summary.put("encounterCC", encounter[1]!=null? encounter[1]: "");
			summary.put("encounterDate", encounter[2]);
			summary.put("serviceDoctor", encounter[3]!=null? encounter[3]: "");
			summary.put("posId", encounter[4]!=null? encounter[4].toString(): "");
			summary.put("pos", "("+(encounter[5]!=null? encounter[5].toString():"")+") "+ (encounter[6]!=null? encounter[6]: ""));
			encDate= encounter[2].toString();
			Integer encReason= encounter[4]!= null ? Integer.parseInt(encounter[4].toString()): -1;
			String reason= getEncounterReason(encounterId, encReason);
			summary.put("reason", reason);
		}
		encDate= encDate.split(" ")[0];
		
		List<Object[]> referrals= getReferrals(encounterId, chartId);
		JSONArray referralArr= parseReferral(referrals);
		
		List<Object[]> leafs= getEncounterLeafs(encounterId);
		JSONArray leafArr= parseLeafs(leafs);
		
		String plan= getPlan(encounterId);
		
		String returnvisit= getReturnVisit(encounterId);
		
		List<Object[]> orderedlabs= getOrderedLabs(encounterId, chartId, 1);
		JSONArray orderedLabsArr= parseOrderedLabs(orderedlabs);
		
		List<Object[]> vaccines= getOrderedLabs(encounterId, chartId, 2);
		JSONArray vaccinesArr= parseOrderedLabs(vaccines);
		
		List<Object[]> performedlabs= getOrderedLabs(encounterId, chartId, 3);
		JSONArray performedLabsArr= parseOrderedLabs(performedlabs);
		
		List<Object[]> prescriptions= getPrescriptions(encounterId);
		JSONArray prescriptionsArr= parsePrescriptions(prescriptions);

		List<Object[]> currentmeds= getCurrentMeds(encounterId);
		JSONArray currentmedArr= parseCurrentMeds(currentmeds);
		
		summary.put("currentMedications",currentmedArr);
		summary.put("prescription",prescriptionsArr);
		summary.put("orderedLabs",orderedLabsArr);
		summary.put("vaccines", vaccinesArr);
		summary.put("performedLabs", performedLabsArr);
		
		summary.put("ReturnVisit", returnvisit);
		summary.put("plan", plan);
		summary.put("clinicalTemplates", leafArr);
		summary.put("vitals", vitalsArr);
		summary.put("diagnosisAssessment", dxArr);
		summary.put("refferals", referralArr);
		summary.put("superBill", new JSONArray());
		return summary.toString();
	}

	/**
	 * Parsing current medications to JSON
	 * @param currentmeds
	 * @return
	 * @throws JSONException
	 */
	private JSONArray parseCurrentMeds(List<Object[]> currentmeds) throws JSONException {
		JSONArray curMedsArr= new JSONArray();
		if(currentmeds!=null)
			for(int i=0; i<currentmeds.size(); i++){
				JSONObject currentmed= new JSONObject();
				Object[] currentmedObj= currentmeds.get(i);
				currentmed.put("drugname", currentmedObj[0]!=null? currentmedObj[0].toString():"");
				currentmed.put("dosagename", currentmedObj[1]!=null? currentmedObj[1].toString():"");
				currentmed.put("form", currentmedObj[2]!=null? currentmedObj[2].toString():"");
				currentmed.put("qty", currentmedObj[3]!=null? currentmedObj[3].toString():"");
				currentmed.put("route", currentmedObj[4]!=null? currentmedObj[4].toString():"");
				curMedsArr.put(currentmed);
			}
		return curMedsArr;
	}

	/**
	 * Get current medications
	 * @param encounterId
	 * @return
	 */
	private List<Object[]> getCurrentMeds(Integer encounterId) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<CurrentMedication> root= query.from(CurrentMedication.class);		
		
		query.multiselect(root.get(CurrentMedication_.currentMedicationRxName), root.get(CurrentMedication_.currentMedicationDosageWithUnit), 
						   root.get(CurrentMedication_.currentMedicationForm), root.get(CurrentMedication_.currentMedicationQuantity),
							root.get(CurrentMedication_.currentMedicationRoute));
							
		
		query.where(builder.equal(root.get(CurrentMedication_.currentMedicationStatus), 14),
				builder.equal(root.get(CurrentMedication_.currentMedicationEncounterId), encounterId));
					 
		query.orderBy(builder.desc(root.get(CurrentMedication_.currentMedicationId)));
		
		List<Object[]> result= null;
		try{
			result= em.createQuery(query).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Parsing prescriptions to JSON
	 * @param prescriptions
	 * @return
	 * @throws JSONException
	 */
	private JSONArray parsePrescriptions(List<Object[]> prescriptions) throws JSONException {
		JSONArray prescArr= new JSONArray();
		if(prescriptions!= null)
			for(int i=0; i<prescriptions.size(); i++){
				Object[] presObj= prescriptions.get(i);
				JSONObject presc= new JSONObject();
				presc.put("status", presObj[0]!=null? presObj[0].toString():"");
				String drugname= (presObj[1]!=null? presObj[1].toString(): "") + " "
								  +(presObj[2]!=null? presObj[2].toString(): "") + " "
								   +(presObj[3]!=null? presObj[3].toString(): "") + " "
									+(presObj[4]!=null? presObj[4].toString(): "") + " "
									 +(presObj[5]!=null? presObj[5].toString(): "") + " "
									  +(presObj[6]!=null? presObj[6].toString(): "") + " "
									   +(presObj[7]!=null? presObj[7].toString(): "") + " "
										+(presObj[8]!=null? presObj[8].toString(): "0") + " dispense " 
										 +(presObj[9]!=null? presObj[9].toString(): "0") + " refills";
				presc.put("drugname", drugname);
				prescArr.put(presc);
			}
		return prescArr;
	}

	/**
	 * Get Prescriptions
	 * @param encounterId
	 * @return
	 */
	private List<Object[]> getPrescriptions(Integer encounterId) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<Prescription> root= query.from(Prescription.class);
		
		query.multiselect(root.get(Prescription_.docPrescStatus), root.get(Prescription_.rxname), 
						   root.get(Prescription_.rxstrength), root.get(Prescription_.docPrescIntake),
							root.get(Prescription_.rxform), root.get(Prescription_.docPrescRoute),
							 root.get(Prescription_.rxfreq), root.get(Prescription_.docPrescDuration),
							  root.get(Prescription_.rxquantity), root.get(Prescription_.noofrefills));
							
		
		query.where(builder.equal(root.get(Prescription_.docPrescEncounterId), encounterId),
					 root.get(Prescription_.docPrescStatus).in(1,2,3));
					 
		
		List<Object[]> result= null;
		try{
			result= em.createQuery(query).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Parsing labs to JSON
	 * @param orderedlabs
	 * @return
	 * @throws JSONException
	 */
	private JSONArray parseOrderedLabs(List<Object[]> orderedlabs) throws JSONException {
		JSONArray labArr= new JSONArray();
		if(orderedlabs!= null)
			for(int i=0; i<orderedlabs.size(); i++){
				Object[] labObj= orderedlabs.get(i);
				JSONObject lab= new JSONObject();
				lab.put("orderon", labObj[0]!= null? labObj[0].toString(): "");
				lab.put("labname", labObj[1]!= null? labObj[1].toString(): "");
				lab.put("status", labObj[2]!= null? labObj[2].toString(): "");
				lab.put("orderby", labObj[3]!= null? labObj[3].toString(): "");
				lab.put("notes", labObj[4]!= null? labObj[4].toString(): "");
				lab.put("loinc", labObj[5]!= null? labObj[5].toString(): "");
				lab.put("cpt", labObj[6]!= null? labObj[6].toString(): "");
				lab.put("labtestdetailid", labObj[7]!= null? labObj[7].toString(): "");
				lab.put("testid", labObj[8]!= null? labObj[8].toString(): "");
				lab.put("labgroupid", labObj[9]!= null? labObj[9].toString(): "");
				lab.put("labgroupname", labObj[10]!= null? labObj[10].toString(): "");
				labArr.put(lab);
			}
		return labArr;
	}

	/**
	 * Get Ordered labs
	 * @param encounterId
	 * @param chartId
	 * @param mode: 1 - Ordered labs
	 * 				2 - Vaccines
	 * 				3 - Performed labs
	 * @return
	 */
	private List<Object[]> getOrderedLabs(Integer encounterId, Integer chartId, int mode) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<LabEntries> root= query.from(LabEntries.class);
		
		Join<LabEntries, LabGroups> groupJoin= root.join(LabEntries_.labGroups, JoinType.INNER);
		Join<LabEntries, Encounter> encounterJoin= root.join(LabEntries_.encounter, JoinType.LEFT);
		Join<LabEntries, EmployeeProfile> empJoin= root.join(LabEntries_.empProfile, JoinType.LEFT);
		
		query.multiselect(builder.function("glace_timezone", String.class, root.get(LabEntries_.labEntriesOrdOn),builder.literal("EDT"),builder.literal("MM/dd/yyyy")),
						  root.get(LabEntries_.labEntriesTestDesc),
						   root.get(LabEntries_.labEntriesTestStatus),
						    builder.coalesce(empJoin.get(EmployeeProfile_.empProfileFullname),""),
						     root.get(LabEntries_.labEntriesResultNotes),
						      root.get(LabEntries_.labEntriesLoinc),
						       root.get(LabEntries_.labEntriesCpt),
						        root.get(LabEntries_.labEntriesTestdetailId),
						         root.get(LabEntries_.labEntriesTestId),
						          root.get(LabEntries_.labEntriesGroupid),
						           groupJoin.get(LabGroups_.labGroupsDesc));
			
		if(mode==1){
			query.where(builder.notEqual(root.get(LabEntries_.labEntriesTestStatus),7),
						 builder.not(groupJoin.get(LabGroups_.labGroupsId).in(36,5)),
						  builder.equal(encounterJoin.get(Encounter_.encounterId),encounterId));
		}
		else if(mode==2){
			query.where(builder.notEqual(root.get(LabEntries_.labEntriesTestStatus),7),
					 groupJoin.get(LabGroups_.labGroupsId).in(36,5),
					  builder.equal(encounterJoin.get(Encounter_.encounterId),encounterId));
		}
		else if(mode==3){
			query.where(builder.equal(root.get(LabEntries_.labEntriesTestStatus),3),					 
					  builder.equal(encounterJoin.get(Encounter_.encounterId),encounterId));
		}
					 
		
		query.orderBy(builder.desc(root.get(LabEntries_.labEntriesOrdOn)));
		
		List<Object[]> result= null;
		try{
			result= em.createQuery(query).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Get return visit notes
	 * @param encounterId
	 * @return
	 */
	private String getReturnVisit(Integer encounterId) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object> query= builder.createQuery(Object.class);
		Root<ReturnVisit> root= query.from(ReturnVisit.class);		
		
		query.select(root.get(ReturnVisit_.returnVisitValue));
		
		query.where(builder.equal(root.get(ReturnVisit_.returnVisitEncounterId), encounterId));
				
		String notes="";

		try{
			List<Object> result= em.createQuery(query).getResultList();
			if(result != null && result.size()>0){
				notes= result.get(0) != null? result.get(0).toString(): "";
			}
		}catch(Exception e){
			e.printStackTrace();
			return notes;
		}
		return notes;
	
	}

	/**
	 * Get Plan notes
	 * @param encounterId
	 * @return
	 */
	private String getPlan(Integer encounterId) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object> query= builder.createQuery(Object.class);
		Root<EncounterPlan> root= query.from(EncounterPlan.class);		
		
		query.select(root.get(EncounterPlan_.plantext));
		
		query.where(builder.equal(root.get(EncounterPlan_.encounterid), encounterId));
				
		String notes="";

		try{
			List<Object> result= em.createQuery(query).getResultList();
			if(result != null && result.size()>0){
				notes= result.get(0) != null? result.get(0).toString(): "";
			}
		}catch(Exception e){
			e.printStackTrace();
			return notes;
		}
		return notes;
	}

	/**
	 * Parsing leafs to JSON
	 * @param leafs
	 * @return
	 * @throws JSONException
	 */
	private JSONArray parseLeafs(List<Object[]> leafs) throws JSONException {
		
		JSONArray leafsArr= new JSONArray();
		if(leafs != null)
			for(int i=0; i<leafs.size(); i++){
				JSONObject leaf= new JSONObject();
				Object[] leafObj= leafs.get(i);
				leaf.put("templatename", leafObj[0]!= null? leafObj[0].toString(): "");
				leaf.put("formid", leafObj[1]);
				leaf.put("templateid", leafObj[2]);
				leaf.put("issigned", leafObj[3]);
				leaf.put("leafid", leafObj[4]);
				leafsArr.put(leaf);
			}
		return leafsArr;
	}

	/**
	 * Get encounter leafs
	 * @param chartId
	 * @return 
	 * @return
	 */
	private List<Object[]> getEncounterLeafs(Integer encounterId) {

		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<LeafPatient> root= query.from(LeafPatient.class);
		
		Join<LeafPatient, LeafLibrary> libraryJoin= root.join(LeafPatient_.leafLibraryTable, JoinType.INNER);
		
		query.multiselect(libraryJoin.get(LeafLibrary_.leafLibraryName),
						   libraryJoin.get(LeafLibrary_.leafLibraryId),
						    libraryJoin.get(LeafLibrary_.leafLibrarySoaptemplateId),
						     root.get(LeafPatient_.leafPatientIscomplete),
						      root.get(LeafPatient_.leafPatientId));
		
		query.where(builder.equal(root.get(LeafPatient_.encounterTable), encounterId));
					 
		
		query.orderBy(builder.asc(root.get(LeafPatient_.leafPatientLastmoddate)));
		
		List<Object[]> result= null;
		try{
			result= em.createQuery(query).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	 * Parsing to JSON
	 * @param referrals
	 * @return
	 * @throws JSONException
	 */
	private JSONArray parseReferral(List<Object[]> referrals) throws JSONException {
		
		JSONArray referralArr= new JSONArray();
		if(referrals != null)
			for(int i=0; i<referrals.size(); i++){
				JSONObject referral= new JSONObject();
				Object[] referralObj= referrals.get(i);
				referral.put("rdoctor_to", referralObj[0]!= null? referralObj[0].toString(): "");
				referral.put("referral_for", referralObj[1]!= null? referralObj[1].toString(): "");
				referralArr.put(referral);
			}
		return referralArr;
	}

	/**
	 * Get encounter referrals
	 * @param encounterId
	 * @param chartId
	 * @return
	 */
	private List<Object[]> getReferrals(Integer encounterId, Integer chartId) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<ReferralDetails> root= query.from(ReferralDetails.class);		
		
		query.multiselect(root.get(ReferralDetails_.referral_details_rdoctor_to),
				root.get(ReferralDetails_.referral_details_printleafdetail));
		
		query.where(builder.equal(root.get(ReferralDetails_.referral_details_chartid), chartId),
				builder.equal(root.get(ReferralDetails_.referral_details_encounterid), encounterId),
				builder.notEqual(root.get(ReferralDetails_.referral_details_patientid), 2));				
				
		query.orderBy(builder.desc(root.get(ReferralDetails_.referral_details_ord_on)));
		
		List<Object[]> result= em.createQuery(query).getResultList();
		
		return result;
	}

	/**
	 * Get encounter reason
	 * @param encounterId
	 * @param encReason
	 * @return
	 */
	private String getEncounterReason(Integer encounterId,Integer encReason) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object> query= builder.createQuery(Object.class);
		Root<AppReferenceValues> root= query.from(AppReferenceValues.class);		
		
		query.select(root.get(AppReferenceValues_.App_Reference_Values_statusName));
		
		query.where(builder.equal(root.get(AppReferenceValues_.App_Reference_Values_reason_type), 1),
				builder.equal(root.get(AppReferenceValues_.App_Reference_Values_tableId), 402),
				builder.equal(root.get(AppReferenceValues_.App_Reference_Values_statusId), encReason));
				
		String reason="";

		try{
			List<Object> result= em.createQuery(query).getResultList();
			if(result != null && result.size()>0){
				reason= result.get(0) != null? result.get(0).toString(): "";
			}
		}catch(Exception e){
			e.printStackTrace();
			return reason;
		}
		return reason;
	}

	/**
	 * Get Encounter details
	 * @param encounterId
	 * @param chartId
	 * @return
	 */
	private List<Object[]> getEncounterDetails(Integer encounterId, Integer chartId) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<Encounter> root= query.from(Encounter.class);
		Join<Encounter, EmployeeProfile> empJoin= root.join(Encounter_.empProfileEmpId,JoinType.LEFT);
		Join<Encounter, PosTable> posJoin= root.join(Encounter_.posTable,JoinType.LEFT);
		
		
		query.multiselect(root.get(Encounter_.encounterId), root.get(Encounter_.encounterChiefcomp),
						   root.get(Encounter_.encounterDate), empJoin.get(EmployeeProfile_.empProfileFullname),
						   	posJoin.get(PosTable_.posTableRelationId), posJoin.get(PosTable_.posTablePlaceOfService),
						   	 posJoin.get(PosTable_.posTableFacilityComments), root.get(Encounter_.encounterReason));
		
		query.where(builder.equal(root.get(Encounter_.encounterId), encounterId),
				builder.equal(root.get(Encounter_.encounterChartid), chartId),
				builder.equal(root.get(Encounter_.encounterType), 1),
				builder.notEqual(root.get(Encounter_.encounterStatus), 1));
				
		List<Object[]> result= em.createQuery(query).getResultList();
		return result;
	}

	/**
	 * Converting to JSON
	 * @param diagnosisList
	 * @return
	 * @throws JSONException
	 */
	private JSONArray parseDiagnosis(List<Object[]> diagnosisList) throws JSONException {

		JSONArray diagnosisArr= new JSONArray();
		if(diagnosisList != null)
			for(int i=0; i<diagnosisList.size(); i++){
				Object[] diagnosis= diagnosisList.get(i);
				JSONObject dx= new JSONObject();
				dx.put("diagnosis", diagnosis[0]);
				dx.put("description", diagnosis[1]);
				dx.put("H611010", diagnosis[2]!=null? diagnosis[2].toString():"");
				dx.put("onsetdate", (diagnosis[3]!=null && !diagnosis[3].toString().isEmpty())? diagnosis[3].toString().split(" ")[0]:"" );
				dx.put("comment", diagnosis[4]);
				diagnosisArr.put(dx);
			}
		return diagnosisArr;
	}

	/**
	 * Get encounter Assessments
	 * @param encounterId
	 * @return
	 */
	private List<Object[]> getDiagnosis(Integer encounterId) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<PatientAssessments> root= query.from(PatientAssessments.class);
		
		query.multiselect(builder.trim(root.get(PatientAssessments_.patient_assessments_dxcode)), builder.trim(root.get(PatientAssessments_.patient_assessments_dxdescription)),
				root.get(PatientAssessments_.patient_assessments_dxorder), root.get(PatientAssessments_.patient_assessments_encounterdate),root.get(PatientAssessments_.patient_assessments_assessmentcomment));
		
		query.where(builder.equal(root.get(PatientAssessments_.patient_assessments_id), encounterId));
		
		query.orderBy(builder.asc(root.get(PatientAssessments_.patient_assessments_dxorder)));
		List<Object[]> result= em.createQuery(query).getResultList();
		
		return result;
	}

	/**
	 * Parsing vitals to JSON
	 * @param vitalsList
	 * @return
	 * @throws JSONException
	 */
	private JSONArray parseVitals(List<Object[]> vitalsList) throws JSONException {
		
		JSONArray vitals= new JSONArray();
		
		for(int i=0; i<vitalsList.size(); i++){
			
			JSONObject vital= new JSONObject();
			Object[] vitalsObj= vitalsList.get(i);
			
			String name= vitalsObj[0].toString();
			String gwid= vitalsObj[1].toString();
			String value= vitalsObj[2].toString();
			String units= vitalsObj[3].toString();
			String optionname= vitalsObj[4]!= null? vitalsObj[4].toString(): "";
			int datatype= Integer.parseInt(vitalsObj[5].toString());
			String assgwid= vitalsObj[6]!= null? vitalsObj[6].toString(): "";
			if(datatype == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION){
				value= optionname;
			}else if(datatype == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION){
				if(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("t"))
					value= "Yes";
				else
					value= "No";
			}
			else if(name.toLowerCase().contains("systolic")){
				for(int j=0; j<vitalsList.size(); j++){
					Object[] tmpvitalsObj= vitalsList.get(j);
					String tmpassgwid= tmpvitalsObj[1]!= null? tmpvitalsObj[1].toString(): "";
					if(assgwid.equals(tmpassgwid)){
						String tmpvalue= tmpvitalsObj[2].toString();
						value= value+"/"+tmpvalue;
						break;
					}
				}
			}else if(name.equalsIgnoreCase("height")){
				String dispUnit= getDisplayUnit("0000200200100023000");
				value= HUtil.heightConversion(value, dispUnit);
			}else if(name.equalsIgnoreCase("weight")){
				String dispUnit= getDisplayUnit("0000200200100024000");
				value= HUtil.weightConversion(value, dispUnit);
			}
			
			if(!name.toLowerCase().contains("diastolic")){
				if(!name.equalsIgnoreCase("Height") && !name.equalsIgnoreCase("Weight")){
					if(name.contains("systolic")){
						name = name.substring(0,name.indexOf("systolic"))+"BP (mmHg)";
					}else if(name.contains("Systolic")){
						name = name.substring(0,name.indexOf("Systolic"))+"BP (mmHg)";
					}else if(units!= null && units.length()>0 && !units.trim().equalsIgnoreCase("N/A") && !name.toLowerCase().contains("systolic")){
						name += "("+units+")";
					}
					if(value.indexOf(".")!=-1 && value.indexOf(",")==-1){
						double doubleValue = Double.parseDouble(value);
						DecimalFormat f = new DecimalFormat("##.00");
						value = f.format(doubleValue).toString();
					}
				}
				vital.put("DisplayName", name);
				vital.put("Value", value);
				vitals.put(vital);
			}
			
		}
		
		return vitals;
	}

	/**
	 * Get vital display unit
	 * @param gwid
	 * @return
	 */
	private String getDisplayUnit(String gwid) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<VitalsParameter> root = cq.from(VitalsParameter.class);
		cq.select(builder.coalesce(root.get(VitalsParameter_.vitalsParameterDisplayUnit),1));
		cq.where(builder.equal(root.get(VitalsParameter_.vitalsParameterGwId), gwid));
		List<Object> resultList = em.createQuery(cq).getResultList();
		String unit= "1";
		if(resultList.size()>0)
			unit= resultList.get(0).toString();
		return unit;
	}

	/**
	 * Get vitals
	 * @param encounterId
	 * @return
	 */
	private List<Object[]> getVitals(Integer encounterId) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<PatientClinicalElements> root= query.from(PatientClinicalElements.class);
		Join<PatientClinicalElements, VitalsParameter> vitalJoin= root.join(PatientClinicalElements_.vitalsParameter, JoinType.INNER);
		Join<PatientClinicalElements, ClinicalElements> clinicalJoin= root.join(PatientClinicalElements_.clinicalElement, JoinType.INNER);
		Join<VitalsParameter, UnitsOfMeasure> unitsJoin= vitalJoin.join(VitalsParameter_.unitsOfMeasureTable, JoinType.LEFT);
		Join<VitalsParameter, VitalGroup> groupJoin= vitalJoin.join(VitalsParameter_.vitalGroup, JoinType.INNER);
		Join<ClinicalElements, ClinicalElementsOptions> optionsJoin= clinicalJoin.join(ClinicalElements_.clinicalElementsOptions, JoinType.LEFT);
		Join<ClinicalElements, ClinicalTextMapping> textJoin= clinicalJoin.join(ClinicalElements_.clinicalTextMappings, JoinType.LEFT);
		
		query.multiselect(vitalJoin.get(VitalsParameter_.vitalsParameterName),
						   root.get(PatientClinicalElements_.patientClinicalElementsGwid),
						    root.get(PatientClinicalElements_.patientClinicalElementsValue),
						     unitsJoin.get(UnitsOfMeasure_.unitsOfMeasureCode),
						      optionsJoin.get(ClinicalElementsOptions_.clinicalElementsOptionsName),
						       clinicalJoin.get(ClinicalElements_.clinicalElementsDatatype),
						        textJoin.get(ClinicalTextMapping_.clinicalTextMappingAssociatedElement));
		
		query.where(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid),encounterId),
					 builder.or(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsValue),optionsJoin.get(ClinicalElementsOptions_.clinicalElementsOptionsValue)),
							 	 builder.isNull(optionsJoin.get(ClinicalElementsOptions_.clinicalElementsOptionsGwid))));
		
		query.orderBy(builder.asc(groupJoin.get(VitalGroup_.vitalGroupOrderby)),
				builder.asc(vitalJoin.get(VitalsParameter_.vitalsParameterId)));
		
		List<Object[]> result= em.createQuery(query).getResultList();
		
		return result;
	}
	
}
