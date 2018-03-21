package com.glenwood.glaceemr.server.application.services.referral;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.Encounter;	
import com.glenwood.glaceemr.server.application.models.Guarantor;
import com.glenwood.glaceemr.server.application.models.PatientAssessments;
import com.glenwood.glaceemr.server.application.models.LeafPatient;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.ProblemList;
import com.glenwood.glaceemr.server.application.models.Referral;
import com.glenwood.glaceemr.server.application.models.Referral_;
import com.glenwood.glaceemr.server.application.repositories.AlertInboxRepository;
import com.glenwood.glaceemr.server.application.repositories.ChartRepository;
import com.glenwood.glaceemr.server.application.repositories.EmpProfileRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterRepository;
import com.glenwood.glaceemr.server.application.repositories.GuarantorRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientAssessmentsRepository;
import com.glenwood.glaceemr.server.application.repositories.LeafPatientRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.repositories.ProblemListRepository;
import com.glenwood.glaceemr.server.application.repositories.ReferralRepository;
import com.glenwood.glaceemr.server.application.repositories.ReferringPhysiciansRepository;
import com.glenwood.glaceemr.server.application.specifications.ChartSpecification;
import com.glenwood.glaceemr.server.application.specifications.EmployeeSpecification;
import com.glenwood.glaceemr.server.application.specifications.EncounterSpecification;
import com.glenwood.glaceemr.server.application.specifications.GuarantorSpecification;
import com.glenwood.glaceemr.server.application.specifications.PatientAssessmentsSpecfication;
import com.glenwood.glaceemr.server.application.specifications.LeafPatientSpecfication;
import com.glenwood.glaceemr.server.application.specifications.PatientRegistrationSpecification;
import com.glenwood.glaceemr.server.application.specifications.ReferralSpecification;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

/**
 * Service implementation file for ReferralController, PlanReferralController
 * @author software
 */

@Service
public class ReferralServiceImpl implements ReferralService{

	@PersistenceContext
	EntityManager em;

	@Autowired
	ReferralRepository referralRepository; 
	
	@Autowired
	EncounterRepository EncounterRepository;
	
	@Autowired
	EmpProfileRepository empProfileRepository;
	
	@Autowired
	ReferringPhysiciansRepository referringPhysiciansRepository;
	
	@Autowired
	LeafPatientRepository leafPatientRepository;
	
	@Autowired
	ChartRepository chartRepository; 
	
	@Autowired
	PatientAssessmentsRepository h611Repository;
	
	@Autowired
	ProblemListRepository problemListRepository; 
	
	@Autowired
	GuarantorRepository guarantorRepository;
	
	@Autowired
	PatientRegistrationRepository patientRegistrationRepository;
	
	@Autowired
	AlertInboxRepository alertInboxRepository;
	
	private Logger logger = Logger.getLogger(ReferralServiceImpl.class);
	
	/* 
	 * Get list of referrals
	 * @see com.glenwood.glaceemr.server.application.services.referral.ReferralService#getListOfReferralsPlan(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Referral> getListOfReferralsPlan(Integer encounterID, Integer chartID, String dx) {
		
		Integer encounterId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(encounterID.toString())).or("-1"));
		Integer chartId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(chartID.toString())).or("-1"));
		
		dx = dx.trim();
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Referral> query= builder.createQuery(Referral.class);
		Root<Referral> root= query.from(Referral.class);
		
		query.select(builder.construct(Referral.class, 
				root.get(Referral_.referral_details_refid),
				 root.get(Referral_.referral_details_rdoctor_to),
				  root.get(Referral_.referral_details_rdoctor_spec),
				   root.get(Referral_.referral_details_printleafdetail),
				    root.get(Referral_.criticalStatus)));

		
		if(!dx.trim().isEmpty()){
			query.where(builder.equal(root.get(Referral_.referral_details_encounterid), encounterId),
					 builder.equal(root.get(Referral_.referral_details_chartid), chartId),
					   builder.notEqual(root.get(Referral_.referral_details_patientid), 2),
						builder.like(builder.upper(root.get(Referral_.referral_details_dxcode)), "%" + dx.toUpperCase() + "%"));
	}
		else{
			query.where(builder.equal(root.get(Referral_.referral_details_encounterid), encounterId),
				 	 builder.equal(root.get(Referral_.referral_details_chartid), chartId),
				 	   builder.notEqual(root.get(Referral_.referral_details_patientid), 2));					 	    
}
		query.orderBy(builder.asc(root.get(Referral_.referral_details_refid)));
		try{
			logger.debug("Getting list of referrals");
			return em.createQuery(query).getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * Get referral based on referral id
	 * @see com.glenwood.glaceemr.server.application.services.referral.ReferralService#getReferralPlan(java.lang.Integer)
	 */
	@Override
	public Referral getReferralPlan(Integer referralID) {
		
		Integer referralId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(referralID.toString())).or("-1"));
		Referral result = referralRepository.findOne(Specifications.where(ReferralSpecification.findByRefId(referralId)).and(ReferralSpecification.orderById()));
		logger.debug("Getting referral");
		
		return result;
	}
	
	/*
	 * Save referral
	 * @see com.glenwood.glaceemr.server.application.services.referral.ReferralService#saveReferralPlan(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public void saveReferralPlan(Integer referralID, String reason, String notes, String dx,Integer criticalstatus,String expdate) {
		
		Integer referralId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(referralID.toString())).or("-1"));
		Iterable<Referral> referralList = referralRepository.findAll(ReferralSpecification.findByRefId(referralId));
		
		Referral referral =null;
		
		for(Referral ref: referralList){
//			Date date = new Date();
			Referral refUpdate = ref;
//			refUpdate.setH413004(new Timestamp(date.getTime()));
			refUpdate.setreferral_details_printleafdetail(reason);
			refUpdate.setreferral_details_comment(notes);
			refUpdate.setreferral_details_dxcode(dx);
			refUpdate.setCriticalStatus(criticalstatus);
			
			SimpleDateFormat sourceformatter = new SimpleDateFormat("MM/dd/yyyy");			
			try {
				refUpdate.setReferralExpectResultDate(new Timestamp(sourceformatter.parse(expdate).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			referral = refUpdate;
			referralRepository.saveAndFlush(referral);
		}
		
		logger.debug("Saving referral");
	}

	
	/* 
	 * Cancel referral - changing status to cancelled
	 * @see com.glenwood.glaceemr.server.application.services.referral.ReferralService#cancelReferral(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void cancelReferral(Integer loginID, Integer refID) {
		
		Integer loginId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(loginID.toString())).or("-1"));
		Integer refId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(refID.toString())).or("-1"));
		if(loginId==-1)
			loginId=null;
		Iterable<Referral> referralList = referralRepository.findAll(ReferralSpecification.findByRefId(refId));
		Referral referral = null;
		
		for(Referral ref: referralList){
			Date date = new Date();
			Referral refUpdate = ref;
			refUpdate.setreferral_details_patientid(2);
			refUpdate.setReferralCancelBy(loginId);
			refUpdate.setReferralCancelOn(new Timestamp(date.getTime()));
			referral = refUpdate;
			referralRepository.saveAndFlush(referral);
		}
		
		logger.debug("Cancelling referral");
		
	}
	
	/*
	 * Get referral
	 * (non-Javadoc)
	 * @see com.glenwood.glaceemr.server.application.services.referral.ReferralService#getReferralSummaryDetails(java.lang.Integer, java.lang.Integer)
	 */
	
	@Override
	public ReferralBean getReferral(Integer referralID, Integer chartID, Boolean fromEdit) {
		
		ReferralBean result=null;
		
		Integer referralId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(referralID.toString())).or("-1"));
		Integer chartId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(chartID.toString())).or("-1"));
		
		List<Chart> chart = chartRepository.findAll(ChartSpecification.findByChartId(chartId));
		Integer patientId = -1;
		for(Chart eachchart: chart)
			patientId = eachchart.getChartPatientid();
		
		List<Referral> referralList = referralRepository.findAll(ReferralSpecification.getByRefId(referralId));
		/*EmployeeProfile employee;
		for(Referral referral: referralList){
			if(referral.getH413032()!=null && referral.getH413032().trim().length()>0){
				employee = empProfileRepository.findOne(EmployeeSpecification.findByEmpId(Integer.parseInt(referral.getH413032())));
				if(employee!=null){
					if(employee.getEmpProfileFullname()!=null)
						referral.setH413032(employee.getEmpProfileFullname());
				}
			}
		}*/
		
		List<Encounter> encounterList = EncounterRepository.findAll(Specifications
				   .where(EncounterSpecification.EncounterByChartId(chartId))
				   .and(EncounterSpecification.EncounterByType(1))
				   .and(EncounterSpecification.orderByDate()));

		
		List<Integer> ids = new ArrayList<>();
		for(Encounter enc: encounterList){
			ids.add(enc.getEncounterId());
		}
		
		List<LeafPatient> savedLeafList=null;
		if(referralId!=-1){
			Referral referral = referralRepository.findOne(ReferralSpecification.findByRefId(referralId));
			List<Integer> leafLibraryIdList = new ArrayList<>();
			List<Integer> leafPatientIdList = new ArrayList<>();
			List<Integer> leafencounterIdList = new ArrayList<>();
			if(referral!=null) {
				String template = referral.getreferral_details_scribble();
				if(template==null)
					template="";
				String[] templateArr = template.split("SoapNotesPrint.Action");
				if(!template.isEmpty()){
					for(int i=0;i<templateArr.length;i++){
						templateArr[i]=templateArr[i].replace('$', ' ').trim();
						String[] collect = templateArr[i].split("\\^\\^");
						leafLibraryIdList.add(Integer.parseInt(collect[0]));
						leafPatientIdList.add(Integer.parseInt(collect[1]));
						leafencounterIdList.add(Integer.parseInt(collect[2]));

					}
				}
				savedLeafList = leafPatientRepository.findAll(LeafPatientSpecfication.getSavedLeafDetails(patientId,chartId,leafLibraryIdList,leafPatientIdList,leafencounterIdList));
			}
		}
		
		/*for(LeafPatient leaf: savedLeafList){
			if(leaf.getLeafPatientIsfollowup()!=null && leaf.getLeafPatientIsfollowup()==true) {
				LeafLibrary leafLib = leaf.getLeafLibraryTable();
				String name = leafLib.getLeafLibraryName();
				leafLib.setLeafLibraryName(name+" - Follow up");
			}
		}*/
		
		if(fromEdit==true){
			List<EmployeeProfile> empList = empProfileRepository.findAll(Specifications.where(EmployeeSpecification.findByGroupId())
					.and(EmployeeSpecification.findByEmpFNameNotLike("demo"))
					.and(EmployeeSpecification.findByEmpLNameNotLike("demo"))
					.and(EmployeeSpecification.isActive(true))
					.and(EmployeeSpecification.orderByGroupId()));


			List<LeafPatient> leafList = leafPatientRepository.findAll(LeafPatientSpecfication.getLeafDetails(patientId,chartId,ids));
			
			/*for(LeafPatient leaf: leafList){
				if(leaf.getLeafPatientIsfollowup()!=null && leaf.getLeafPatientIsfollowup()==true) {
					LeafLibrary leafLib = leaf.getLeafLibraryTable();
					String name = leafLib.getLeafLibraryName();
					leafLib.setLeafLibraryName(name+" - Follow up");
				}
			}*/
			List<PatientAssessments> dxList = h611Repository.findAll(PatientAssessmentsSpecfication.getByEncounterId(ids));
			List<ProblemList> problemList=problemListRepository.findAll(ReferralSpecification.getproblemlist(patientId+""));
			
			for(int z=0;z<dxList.size()-1;z++){
				String dxcode1=dxList.get(z).getpatient_assessments_dxcode();
				dxcode1= (dxcode1!=null ? dxcode1.trim(): dxcode1);
				String oid=dxList.get(z).getpatient_assessmentsCodingSystemid();
				for(int z1=z+1;z1<dxList.size();z1++){
					String dxcode2=dxList.get(z1).getpatient_assessments_dxcode();
					dxcode2= (dxcode2!=null ? dxcode2.trim(): dxcode2);
					String oid2=dxList.get(z1).getpatient_assessmentsCodingSystemid();
					if(((dxcode1.replaceAll("\\s+","")).equalsIgnoreCase((dxcode2).replaceAll("\\s+","")))&&(oid.replaceAll("\\s+","").equalsIgnoreCase(oid2.replaceAll("\\s+","")))){
						dxList.remove(dxList.get(z1));
						z1--;
					}

				}

			}

			for(int z=0;z<problemList.size()-1;z++){
				String dxcode1=problemList.get(z).getProblemListDxCode();
				dxcode1= (dxcode1!=null ? dxcode1.trim(): dxcode1);
				for(int z1=z+1;z1<problemList.size();z1++){
					String dxcode2=problemList.get(z1).getProblemListDxCode();
					dxcode2= (dxcode2!=null ? dxcode2.trim(): dxcode2);
					if((dxcode1.replaceAll("\\s+","")).equalsIgnoreCase((dxcode2).replaceAll("\\s+",""))){
						problemList.remove(problemList.get(z1));
						z1--;
					}


				}

			}

			for(int i=0;i<problemList.size();i++){
				String dxcode=problemList.get(i).getProblemListDxCode();
				dxcode= (dxcode!=null ? dxcode.trim(): dxcode);
				for(int j=0;j<dxList.size();j++){
					String dxcode1=dxList.get(j).getpatient_assessments_dxcode();
					dxcode1= (dxcode1!=null ? dxcode1.trim(): dxcode1);
					if(dxcode.replaceAll("\\s+","").equalsIgnoreCase(dxcode1.replaceAll("\\s+",""))){
						dxList.remove(dxList.get(j));
						j--;
					}
				}
			}

			
			result = new ReferralBean(empList, referralList, leafList, savedLeafList, dxList, problemList,null);
		}
		else
			result = new ReferralBean(null, referralList, null, savedLeafList, null, null,null);
		
		logger.debug("Getting referral details");
		
		return result;
	}
	
	/* 
	 * Get referral
	 * (non-Javadoc)
	 * @see com.glenwood.glaceemr.server.application.services.referral.ReferralService#getReferral(java.lang.Integer, java.lang.Integer)
	 
	@SuppressWarnings("null")
	@Override
	public ReferralBean getReferral(Integer referralID, Integer chartID ) {

		ReferralBean result=null;
		
		Integer referralId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(referralID.toString())).or("-1"));
		Integer chartId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(chartID.toString())).or("-1"));
		
		List<Referral> referralList = referralRepository.findAll(ReferralSpecification.getByRefId(referralId));
		
		List<Encounter> encounterList = EncounterRepository.findAll(Specifications
				   .where(EncounterSpecification.EncounterByChartId(chartId))
				   .and(EncounterSpecification.EncounterByType(1))
				   .and(EncounterSpecification.orderByDate()));

		
		List<Integer> ids = new ArrayList<>();
		for(Encounter enc: encounterList){
			ids.add(enc.getEncounterId());
		}

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<H611> root = cq.from(H611.class);
		
		Join<H611,CodingSystems> dxCodingSystemJoin= root.join("codingsystemsTable",JoinType.INNER);
		Predicate pred=null;
		if(ids.size()>0)
			pred= root.get(H611_.patient_assessments_encounterid).in(ids);
		else
			pred= root.get(H611_.patient_assessments_encounterid).in(-999);
		
		cq.distinct(true).select(builder.construct(ReferralDiagnosisBean.class,root.get(H611_.h611005),root.get(H611_.h611006))).where(pred).orderBy(builder.asc(root.get(H611_.h611005)));
		
		List<Object> getResult=em.createQuery(cq).getResultList();
		List<ReferralDiagnosisBean> diagnosisList = new ArrayList<ReferralDiagnosisBean>();
		
		for(int i=0;i<getResult.size();i++){
			ReferralDiagnosisBean beanObj = (ReferralDiagnosisBean) getResult.get(i);
			ReferralDiagnosisBean finalbeanObj = new ReferralDiagnosisBean(beanObj.getDxCode(), beanObj.getDxDescription());
			diagnosisList.add(finalbeanObj);
		}
		
//		List<H611> dxList = h611Repository.findAll(H611Specfication.getByEncounterId(ids));
		
		result = new ReferralBean(null, referralList, null, null, null, null, diagnosisList);
		
		logger.debug("Getting referral details");
		
		return result;
	}*/
	
	
	/* 
	 * Getting list of referrals
	 * (non-Javadoc)
	 * @see com.glenwood.glaceemr.server.application.services.referral.ReferralService#getListOfReferrals(java.lang.Integer)
	 */
	@Override
	public ReferralListBean getListOfReferrals(Integer chartID) {
		
		Integer chartId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(chartID.toString())).or("-1"));

		List<Chart> chart = chartRepository.findAll(ChartSpecification.findByChartId(chartId));
		
		Integer patientId = -1;
		String fname="", lname="", fullname="";
		for(Chart eachchart: chart)
			patientId = eachchart.getChartPatientid();
		
		List<Guarantor> guarantorList = guarantorRepository.findAll(GuarantorSpecification.getByPatientId(patientId));
		for(Guarantor eachGuarantor: guarantorList){
			lname = eachGuarantor.getGuarantorLastName();
			fname = eachGuarantor.getGuarantorFirstName();
		}
		if(lname!=null)
			fullname = lname;
		if(fname!=null)
			fullname = fullname +" "+fname;
		
		List<Referral> referralList = referralRepository.findAll(Specifications
							.where(ReferralSpecification.findByChartId(chartId))
							.and(ReferralSpecification.findByStatusNotEqual(2))
							.and(ReferralSpecification.orderByOrderedDate()));
		
		ReferralListBean referralListBean = new ReferralListBean(referralList, fullname.trim());
		
		logger.debug("Getting list of referrals");
		
		return referralListBean;
	}

	
	/*
	 * Saving referral details
	 * editReferral  -1 : Create new referral
	 * 				  1 : Save referral
	 * (non-Javadoc)
	 * @see com.glenwood.glaceemr.server.application.services.referral.ReferralService#saveReferral(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String saveReferral(ReferralForm referralForm) throws JSONException {

		Integer chartId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(referralForm.getChartId())).or("-1"));
		Integer patientId = Integer.parseInt(getpatientid(chartId));
		Integer encounterId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(referralForm.getEncounterId())).or("-1"));
		Integer referralId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(referralForm.getRefId())).or("-1"));
		Integer userId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(referralForm.getUserId())).or("-1"));
		Integer loginId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(referralForm.getLoginId())).or("-1"));
		Integer editReferral = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(referralForm.getEditReferral())).or("-1"));
		Integer reftoId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(referralForm.getReferredToId())).or("-1"));
		
		String result = "-1";
		if(editReferral==1){

			Iterable<Referral> referralList = referralRepository.findAll(ReferralSpecification.findByRefId(referralId));

			Referral referral = new Referral();

			for(Referral ref: referralList){
				SimpleDateFormat sourceformatter = new SimpleDateFormat("MM/dd/yyyy");

				Referral refUpdate = ref;
				Date orderdate=null, apptdate=null, authdate=null, authexpdate=null, consreceivedate=null, consreviewedate=null;

				try {

					if(referralForm.getStatus()!=-1) {
						Date date = new Date();
						Timestamp dataStamp = new Timestamp(date.getTime());
						refUpdate.setReferralOrderBy(userId);
						if(!referralForm.getOrderedDate().isEmpty()){
							orderdate = sourceformatter.parse(referralForm.getOrderedDate());
							Timestamp orderdateTimestamp = new Timestamp(orderdate.getTime());
							refUpdate.setReferralOrderOn(orderdateTimestamp);
						}
						else {
							refUpdate.setReferralOrderOn(dataStamp);
						}

						if(referralForm.getStatus()==2 && refUpdate.getreferral_details_patientid()!=2){
							refUpdate.setReferralCancelBy(loginId);
							refUpdate.setReferralCancelOn(dataStamp);
						}
						if(referralForm.getStatus()==3 && refUpdate.getreferral_details_patientid()!=3){
							refUpdate.setReferralReceiveBy(userId);
							refUpdate.setReferralReceiveOn(dataStamp);
						}
						if(referralForm.getStatus()==4 && refUpdate.getreferral_details_patientid()!=4){
							refUpdate.setReferralReceiveBy(userId);
							refUpdate.setReferralReceiveOn(dataStamp);
							refUpdate.setReferralReviewedBy(userId);
							refUpdate.setReferralReviewedOn(dataStamp);
						}
					}
					if(referralForm.getApptDate().isEmpty())
						referralForm.setApptDate(null);
					else {
						apptdate = sourceformatter.parse(referralForm.getApptDate());
						Timestamp apptdateTimestamp = new Timestamp(apptdate.getTime());
						refUpdate.setreferral_details_apptconfirmdate(apptdateTimestamp);
					}

					if(referralForm.getAuthDate().isEmpty())
						referralForm.setAuthDate(null);
					else {
						authdate = sourceformatter.parse(referralForm.getAuthDate());
						Timestamp authdateTimestamp = new Timestamp(authdate.getTime());
						refUpdate.setreferral_details_authdate(authdateTimestamp);
					}

					if(referralForm.getAuthExpDate().isEmpty())
						referralForm.setAuthExpDate(null);
					else {
						authexpdate = sourceformatter.parse(referralForm.getAuthExpDate());
						Timestamp authexpdateTimestamp = new Timestamp(authexpdate.getTime());
						refUpdate.setReferralExpirationDate(authexpdateTimestamp);
					}

					if(referralForm.getConsReportReceivedDate()== null || referralForm.getConsReportReceivedDate().isEmpty())
						referralForm.setConsReportReceivedDate(null);
					else {
						consreceivedate = sourceformatter.parse(referralForm.getConsReportReceivedDate());
						Timestamp consreceivedTimestamp = new Timestamp(consreceivedate.getTime());
						refUpdate.setreferral_details_concernreprtrecdate(consreceivedTimestamp);
					}

					if(referralForm.getConsReportReviewedDate()== null || referralForm.getConsReportReviewedDate().isEmpty())
						referralForm.setConsReportReviewedDate(null);
					else {
						consreviewedate = sourceformatter.parse(referralForm.getConsReportReviewedDate());
						Timestamp consreviewedTimestamp = new Timestamp(consreviewedate.getTime());
						refUpdate.setreferral_details_revdate(consreviewedTimestamp);
					}
					if(referralForm.getOrderedDate().isEmpty())
						referralForm.setOrderedDate(null);
					else {
						orderdate = sourceformatter.parse(referralForm.getOrderedDate());
						Timestamp orderdateTimestamp = new Timestamp(orderdate.getTime());
						refUpdate.setreferral_details_ord_on(orderdateTimestamp);
					}

				}catch(ParseException e) {
					e.printStackTrace();
				}

				List<EmployeeProfile> emplist = empProfileRepository.findAll(EmployeeSpecification.findByEmpLoginId(loginId));
				Integer groupId = 0;
				for(EmployeeProfile emp: emplist){
					if(emp.getEmpProfileGroupid()==null)
						emp.setEmpProfileGroupid(-2);
					else
						groupId= emp.getEmpProfileGroupid();	
				}
				if(groupId==-1)
					refUpdate.setReferreddoctorLoginid(loginId);
				refUpdate.setReferralReferredtoid(reftoId);
				refUpdate.setreferral_details_rdoctor_from(referralForm.getReferredBy());
				refUpdate.setreferral_details_rdoctor_to(referralForm.getReferredTo());
				refUpdate.setreferral_details_rdoctor_spec(referralForm.getSpecialization());
				refUpdate.setreferral_details_rdoctor_address(referralForm.getAddress());
				refUpdate.setreferral_details_rdoctor_phno(referralForm.getPhone());
				refUpdate.setreferral_details_rdoctor_faxno(referralForm.getFax());
				refUpdate.setreferral_details_dxcode(referralForm.getRdx());
				refUpdate.setreferral_details_comment(referralForm.getReferralNotes());
				refUpdate.setreferral_details_authneeded(referralForm.getAuthNeeded());
				refUpdate.setreferral_details_auth_id(referralForm.getAuthDone());
				refUpdate.setreferral_details_authnumber(referralForm.getAuthNumber());

				refUpdate.setreferral_details_authoriseddate(referralForm.getAuthBy());
				refUpdate.setreferral_details_authcontact_person(referralForm.getAuthNotes());
				refUpdate.setreferral_details_authcomment(referralForm.getApptConfirmed());

				refUpdate.setreferral_details_appttime(referralForm.getApptTime());
				refUpdate.setreferral_details_apptby(referralForm.getApptBy());
				refUpdate.setreferral_details_authcontactperson(referralForm.getApptContactPerson());
				refUpdate.setreferral_details_ptnotified(referralForm.getPatientNotified());
				refUpdate.setreferral_details_apptcomment(referralForm.getApptNotes());
				refUpdate.setreferral_details_concernreportreceived(referralForm.getIsConsReportReceived());
				refUpdate.setreferral_details_concernreportid(referralForm.getIsConsReportReviewed());

				refUpdate.setreferral_details_reportverify(referralForm.getConsComment());

				refUpdate.setreferral_details_printleafdetail(referralForm.getReason());
				refUpdate.setreferral_details_scribble(referralForm.getPrintLeaf());
				refUpdate.setreferral_details_patientid(referralForm.getStatus());
				refUpdate.setReferralGuarantorname(referralForm.getGuarantorName());
				refUpdate.setReferralNoVisits(referralForm.getNumofVisits());
				refUpdate.setReferralHospitalname(referralForm.getHospitalName());
				refUpdate.setSummaryCareRecordProvided(referralForm.getIsSummaryCare());
				referral = refUpdate;
				referralRepository.saveAndFlush(referral);
			}
			logger.debug("Saving referral");
			result="-1";
		} 
		else if(editReferral==-1){
			
			List<Referral> referralList = referralRepository.findAll(ReferralSpecification.orderByIdDesc());
			Referral ref = referralList.get(0);
			int maxid=ref.getreferral_details_refid();
			int alertId= createAlert(maxid+1, 17, patientId, encounterId, chartId, userId, -1, 1, -1, -1, "");
			
			Referral referral = new Referral();
			referral.setreferral_details_refid(maxid+1);

			SimpleDateFormat sourceformatter = new SimpleDateFormat("MM/dd/yyyy");
			Date orderdate=null, apptdate=null, authdate=null, authexpdate=null, consreceivedate=null, consreviewedate=null;

			try {

				if(referralForm.getStatus()!=-1) {
					Date date = new Date();
					Timestamp dataStamp = new Timestamp(date.getTime());
					referral.setReferralOrderBy(userId);
					if(!referralForm.getOrderedDate().isEmpty()){
						orderdate = sourceformatter.parse(referralForm.getOrderedDate());
						Timestamp orderdateTimestamp = new Timestamp(orderdate.getTime());
						referral.setReferralOrderOn(orderdateTimestamp);
					}
					else {
						referral.setReferralOrderOn(dataStamp);
					}

					if(referralForm.getStatus()==2){
						referral.setReferralCancelBy(loginId);
						referral.setReferralOrderOn(dataStamp);
					}
					if(referralForm.getStatus()==3){
						referral.setReferralReceiveBy(userId);
						referral.setReferralReceiveOn(dataStamp);
					}
					if(referralForm.getStatus()==4){
						referral.setReferralReceiveBy(userId);
						referral.setReferralReceiveOn(dataStamp);
						referral.setReferralReviewedBy(userId);
						referral.setReferralReviewedOn(dataStamp);
					}
				}
				if(referralForm.getApptDate().isEmpty())
					referralForm.setApptDate(null);
				else {
					apptdate = sourceformatter.parse(referralForm.getApptDate());
					Timestamp apptdateTimestamp = new Timestamp(apptdate.getTime());
					referral.setreferral_details_apptconfirmdate(apptdateTimestamp);
				}

				if(referralForm.getAuthDate().isEmpty())
					referralForm.setAuthDate(null);
				else {
					authdate = sourceformatter.parse(referralForm.getAuthDate());
					Timestamp authdateTimestamp = new Timestamp(authdate.getTime());
					referral.setreferral_details_authdate(authdateTimestamp);
				}

				if(referralForm.getAuthExpDate().isEmpty())
					referralForm.setAuthExpDate(null);
				else {
					authexpdate = sourceformatter.parse(referralForm.getAuthExpDate());
					Timestamp authexpdateTimestamp = new Timestamp(authexpdate.getTime());
					referral.setReferralExpirationDate(authexpdateTimestamp);
				}

				if(referralForm.getConsReportReceivedDate().isEmpty())
					referralForm.setConsReportReceivedDate(null);
				else {
					consreceivedate = sourceformatter.parse(referralForm.getConsReportReceivedDate());
					Timestamp consreceivedTimestamp = new Timestamp(consreceivedate.getTime());
					referral.setreferral_details_concernreprtrecdate(consreceivedTimestamp);
				}

				if(referralForm.getConsReportReviewedDate().isEmpty())
					referralForm.setConsReportReviewedDate(null);
				else {
					consreviewedate = sourceformatter.parse(referralForm.getConsReportReviewedDate());
					Timestamp consreviewedTimestamp = new Timestamp(consreviewedate.getTime());
					referral.setreferral_details_revdate(consreviewedTimestamp);
				}
				if(referralForm.getOrderedDate().isEmpty())
					referralForm.setOrderedDate(null);
				else {
					orderdate = sourceformatter.parse(referralForm.getOrderedDate());
					Timestamp orderdateTimestamp = new Timestamp(orderdate.getTime());
					referral.setreferral_details_ord_on(orderdateTimestamp);
				}

			}catch(ParseException e) {
				e.printStackTrace();
			}

			referral.setReferralReferredtoid(reftoId);
			referral.setreferral_details_chartid(chartId);
			referral.setreferral_details_encounterid(encounterId);
			referral.setreferral_details_rdoctor_from(referralForm.getReferredBy());
			referral.setreferral_details_rdoctor_to(referralForm.getReferredTo());
			referral.setreferral_details_rdoctor_spec(referralForm.getSpecialization());
			referral.setreferral_details_rdoctor_address(referralForm.getAddress());
			referral.setreferral_details_rdoctor_phno(referralForm.getPhone());
			referral.setreferral_details_rdoctor_faxno(referralForm.getFax());
			referral.setreferral_details_dxcode(referralForm.getRdx());
			referral.setreferral_details_comment(referralForm.getReferralNotes());
			referral.setreferral_details_authneeded(referralForm.getAuthNeeded());
			referral.setreferral_details_auth_id(referralForm.getAuthDone());
			referral.setreferral_details_authnumber(referralForm.getAuthNumber());

			referral.setreferral_details_authoriseddate(referralForm.getAuthBy());
			referral.setreferral_details_authcontact_person(referralForm.getAuthNotes());
			referral.setreferral_details_authcomment(referralForm.getApptConfirmed());

			referral.setreferral_details_appttime(referralForm.getApptTime());
			referral.setreferral_details_apptby(referralForm.getApptBy());
			referral.setreferral_details_authcontactperson(referralForm.getApptContactPerson());
			referral.setreferral_details_ptnotified(referralForm.getPatientNotified());
			referral.setreferral_details_apptcomment(referralForm.getApptNotes());
			referral.setreferral_details_concernreportreceived(referralForm.getIsConsReportReceived());
			referral.setreferral_details_concernreportid(referralForm.getIsConsReportReviewed());

			referral.setreferral_details_reportverify(referralForm.getConsComment());
			referral.setreferral_details_isactive(-1);
			referral.setreferral_details_myalert(patientId);
			referral.setreferral_details_referral_for(alertId);
			referral.setreferral_details_printleafdetail(referralForm.getReason());
			referral.setreferral_details_scribble(referralForm.getPrintLeaf());
			referral.setreferral_details_leafid(-1);
			referral.setreferral_details_patientid(referralForm.getStatus());
			referral.setReferreddoctorLoginid(loginId);
			referral.setReferralGuarantorname(referralForm.getGuarantorName());
			referral.setReferralNoVisits(referralForm.getNumofVisits());
			referral.setReferralHospitalname(referralForm.getHospitalName());
			referral.setSummaryCareRecordProvided(referralForm.getIsSummaryCare());
			
			referralRepository.save(referral);

			result=""+(maxid+1);
			logger.debug("Creating referral");
		}
		return result;
	}

	/* 
	 * Get referral
	 * (non-Javadoc)
	 * @see com.glenwood.glaceemr.server.application.services.referral.ReferralService#getReferral(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public ReferralBean getReferral(Integer referralID, Integer chartID, Integer encounterID ) {

		ReferralBean result=null;
		
		Integer referralId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(referralID.toString())).or("-1"));
		Integer chartId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(chartID.toString())).or("-1"));
		Integer encounterId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(chartID.toString())).or("-1"));
		
		String patientId = getpatientid(chartId);
		List<Referral> referralList = referralRepository.findAll(ReferralSpecification.getByRefId(referralId));
		
		List<PatientAssessments> dxList=h611Repository.findAll(ReferralSpecification.getcodingsystems(encounterId,chartId));
		List<ProblemList> problemList=problemListRepository.findAll(ReferralSpecification.getproblemlist(patientId));
		
		for(int z=0;z<dxList.size()-1;z++){
			String dxcode1=dxList.get(z).getpatient_assessments_dxcode();
			dxcode1= (dxcode1!=null ? dxcode1.trim(): dxcode1);
			String oid=dxList.get(z).getpatient_assessmentsCodingSystemid();
			for(int z1=z+1;z1<dxList.size();z1++){
				String dxcode2=dxList.get(z1).getpatient_assessments_dxcode();
				dxcode2= (dxcode2!=null ? dxcode2.trim(): dxcode2);
				String oid2=dxList.get(z1).getpatient_assessmentsCodingSystemid();
				if(((dxcode1.replaceAll("\\s+","")).equalsIgnoreCase((dxcode2).replaceAll("\\s+","")))&&(oid.replaceAll("\\s+","").equalsIgnoreCase(oid2.replaceAll("\\s+","")))){
					dxList.remove(dxList.get(z1));
					z1--;
				}

			}

		}

		for(int z=0;z<problemList.size()-1;z++){
			String dxcode1=problemList.get(z).getProblemListDxCode();
			dxcode1= (dxcode1!=null ? dxcode1.trim(): dxcode1);
			for(int z1=z+1;z1<problemList.size();z1++){
				String dxcode2=problemList.get(z1).getProblemListDxCode();
				dxcode2= (dxcode2!=null ? dxcode2.trim(): dxcode2);
				if((dxcode1.replaceAll("\\s+","")).equalsIgnoreCase((dxcode2).replaceAll("\\s+",""))){
					problemList.remove(problemList.get(z1));
					z1--;
				}


			}

		}

		for(int i=0;i<problemList.size();i++){
			String dxcode=problemList.get(i).getProblemListDxCode();
			dxcode= (dxcode!=null ? dxcode.trim(): dxcode);
			for(int j=0;j<dxList.size();j++){
				String dxcode1=dxList.get(j).getpatient_assessments_dxcode();
				dxcode1= (dxcode1!=null ? dxcode1.trim(): dxcode1);
				if(dxcode.replaceAll("\\s+","").equalsIgnoreCase(dxcode1.replaceAll("\\s+",""))){
					dxList.remove(dxList.get(j));
					j--;
				}
			}
		}

		result = new ReferralBean(null,referralList,null,null,dxList,problemList,null);
		
		logger.debug("Getting referral details");
		
		return result;
	}
	
	public String getpatientid(int chartId){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<Chart> root = cq.from(Chart.class);
		cq.select(root.get(Chart_.chartPatientid));
		cq.where(builder.equal(root.get(Chart_.chartId), chartId));
		List <Object> resultList = em.createQuery(cq).getResultList();
		String patientId="-1";
		if(resultList.size()>0)
			patientId=resultList.get(0).toString();
		return patientId;

	}
	
	/**
	 * Creating alert to Doctor
	 * @param refId
	 * @param categoryId
	 * @param patientId
	 * @param encounterId
	 * @param chartId
	 * @param fromId
	 * @param toId
	 * @param status
	 * @param roomId
	 * @param parentId
	 * @param message
	 * @return
	 */
	public int createAlert(int refId, int categoryId, int patientId, int encounterId, int chartId, int fromId, int toId, int status, int roomId, int parentId, String message){

		String lastName="", firstName="", midInitial="";
		int  refid=refId;

		PatientRegistration patient=patientRegistrationRepository.findOne(PatientRegistrationSpecification.PatientId(patientId+""));
		if(patient!=null){

			lastName=Optional.fromNullable(patient.getPatientRegistrationLastName()).or(" ");
			firstName=Optional.fromNullable(patient.getPatientRegistrationFirstName()).or(" ");
			midInitial=Optional.fromNullable(patient.getPatientRegistrationMidInitial()).or(" ");
		}

		AlertEvent aug=new AlertEvent();
		aug.setAlertEventFrom(fromId);
		aug.setAlertEventTo(toId);
		aug.setAlertEventStatus(status);
		aug.setAlertEventCategoryId(categoryId);
		aug.setAlertEventRefId(refid);
		aug.setAlertEventPatientId(patientId);
		if(patient!=null)
			aug.setAlertEventPatientName(lastName+", "+firstName+" "+midInitial);		//Lastname, Firstname MidInitial
		aug.setAlertEventEncounterId(encounterId);
		aug.setAlertEventCreatedDate(new Timestamp(new Date().getTime()));
		aug.setAlertEventMessage(message);
		aug.setAlertEventModifiedby(fromId);
		aug.setAlertEventChartId(chartId);
		aug.setAlertEventRoomId(roomId);
		aug.setAlertEventParentalertid(parentId);
		alertInboxRepository.save(aug);

		return aug.getAlertEventId()!=null? aug.getAlertEventId(): -1;

	}

}
