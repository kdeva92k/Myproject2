package com.glenwood.glaceemr.server.application.services.chart.print;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.BillingConfigTable;
import com.glenwood.glaceemr.server.application.models.BillingConfigTable_;
import com.glenwood.glaceemr.server.application.models.Billinglookup;
import com.glenwood.glaceemr.server.application.models.Billinglookup_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.Encounter_;
import com.glenwood.glaceemr.server.application.models.InitialSettings;
import com.glenwood.glaceemr.server.application.models.InsCompAddr;
import com.glenwood.glaceemr.server.application.models.InsCompAddr_;
import com.glenwood.glaceemr.server.application.models.InsCompany;
import com.glenwood.glaceemr.server.application.models.InsCompany_;
import com.glenwood.glaceemr.server.application.models.LeafLibrary;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail;
import com.glenwood.glaceemr.server.application.models.PatientInsDetail_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PlaceOfService;
import com.glenwood.glaceemr.server.application.models.PlaceOfService_;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosTable_;
import com.glenwood.glaceemr.server.application.models.PosType;
import com.glenwood.glaceemr.server.application.models.PosType_;
import com.glenwood.glaceemr.server.application.models.ReferringDoctor;
import com.glenwood.glaceemr.server.application.models.ReferringDoctor_;
import com.glenwood.glaceemr.server.application.models.print.GenericPrintStyle;
import com.glenwood.glaceemr.server.application.repositories.EncounterRepository;
import com.glenwood.glaceemr.server.application.repositories.InitialSettingsRepository;
import com.glenwood.glaceemr.server.application.repositories.LeafLibraryRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.repositories.PosTableRepository;
import com.glenwood.glaceemr.server.application.repositories.print.GenericPrintStyleRepository;
import com.glenwood.glaceemr.server.application.services.chart.insurance.InsuranceDataBean;
import com.glenwood.glaceemr.server.application.services.employee.EmployeeDataBean;
import com.glenwood.glaceemr.server.application.services.patient.PatientDataBean;
import com.glenwood.glaceemr.server.application.services.pos.DefaultPracticeBean;
import com.glenwood.glaceemr.server.application.services.pos.PosDataBean;
import com.glenwood.glaceemr.server.application.specifications.EncounterSpecification;
import com.glenwood.glaceemr.server.application.specifications.InitialSettingsSpecification;
import com.glenwood.glaceemr.server.application.specifications.LeafLibrarySpecification;
import com.glenwood.glaceemr.server.application.specifications.PatientRegistrationSpecification;
import com.glenwood.glaceemr.server.application.specifications.PosTableSpecification;
import com.glenwood.glaceemr.server.application.specifications.print.GenericPrintSpecification;
import com.glenwood.glaceemr.server.utils.SessionMap;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;

@Service
@Transactional
public class GenericPrintServiceImpl implements GenericPrintService{

	@Autowired
	GenericPrintStyleRepository genericPrintStyleRepository;

	@Autowired
	PatientRegistrationRepository patientRegistrationRepository;

	@Autowired
	EncounterRepository encounterRepository;

	@Autowired
	PosTableRepository posTableRepository;

	@Autowired
	GenerateHeaderBean generateHeaderBean;

	@Autowired
	GenerateFooterBean generateFooterBean;

	@Autowired
	GeneratePDFBean generatePDFBean;

	@Autowired
	TextFormatter textFormat;

	@Autowired
	InitialSettingsRepository initialSettingsRepository;

	@Autowired
	LeafLibraryRepository leafLibraryRepository;

	@Autowired
	EntityManager em;

	@Autowired
	SessionMap sessionMap;

	@Override
	public List<GenericPrintStyle> getGenericPrintStyleList() {
		List<GenericPrintStyle> genericPrintStyleList=genericPrintStyleRepository.findAll();
		return genericPrintStyleList;
	}

	@Override
	public GenericPrintStyle getGenericPrintStyle(Integer styleId) {
		GenericPrintStyle genericPrintStyle=genericPrintStyleRepository.findOne(styleId);
		return genericPrintStyle;
	}

	@Override
	public GenericPrintStyle saveGenericPrintStyle(GenericPrintStyle genericPrintStyle) {
		GenericPrintStyle savedGenericPrintStyle=genericPrintStyleRepository.save(genericPrintStyle);
		return savedGenericPrintStyle;
	}

	@Override
	public void generatePDFPreview(Integer styleId) {
		try{
			GenericPrintStyle genericPrintStyle=genericPrintStyleRepository.findOne(styleId);
			int letterHeaderId=genericPrintStyle.getGenericPrintStyleHeaderId();
			int patientHeaderId=genericPrintStyle.getGenericPrintStylePatientHeaderId();
			String patientHeaderPage1;
			String patientHeaderHTML=generateHeaderBean.generatePatientHeader(patientHeaderId, 1);
			if(generateHeaderBean.getPatientHeaderType(patientHeaderId)==2){
				patientHeaderPage1=generateHeaderBean.generatePatientHeader(patientHeaderId, 2);
			}else{
				patientHeaderPage1=patientHeaderHTML;
			}

			String headerHTML="";
			if(letterHeaderId>0){
				headerHTML=generateHeaderBean.generateHeader(letterHeaderId);
			}
			int footerId=genericPrintStyle.getGenericPrintStyleFooterId();
			String footerHTML="";
			int pageVariant=0;
			if(footerId>0){
				footerHTML=generateFooterBean.generateFooter(footerId);
				pageVariant=generateFooterBean.getPageFormatForFooter(footerId);
			}

			//generatePDFBean.generatePDF(headerHTML,patientHeaderPage1,patientHeaderHTML,footerHTML,pageVariant);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public String[] generatePatentDetailsArr(PatientDataBean patientBean){
		String[] patientDetailsArr=new String[20];
		//"Patient Name", "Age", "DOS", "Gender","Account #","Phone #","DOB","Mobile #","Address","Referring Doctor","Insurance Details","Supervising doctor",
		//"Service/Performing Doctor","Ethinicity","Race","Preffered/Primary Language","Care Group"
		patientDetailsArr[0]= isNull(patientBean.getPatientName());
		patientDetailsArr[1]= isNull(patientBean.getAge());
		patientDetailsArr[2]= isNull(patientBean.getDos());
		patientDetailsArr[3]= isNull(patientBean.getGender());
		patientDetailsArr[4]= isNull(patientBean.getAccountId());
		patientDetailsArr[5]= isNull(patientBean.getPhNum());
		patientDetailsArr[6]= isNull(patientBean.getDob());
		patientDetailsArr[7]= isNull(patientBean.getMobileNum());
		patientDetailsArr[8]= isNull(patientBean.getAddress());
		patientDetailsArr[9]= isNull(patientBean.getServiceReferral());
		patientDetailsArr[10]= getInsuranceName(patientBean);
		patientDetailsArr[11]=patientBean.getPrincipalDr()!= null? isNull(patientBean.getPrincipalDr().getEmpFullname()) : "";
		patientDetailsArr[12]=patientBean.getServiceDr()!=null ? isNull(patientBean.getServiceDr().getEmpFullname()) : "";
		patientDetailsArr[13]=isNull(patientBean.getEthinicity());
		patientDetailsArr[14]=isNull(patientBean.getRace());
		patientDetailsArr[15]=isNull(patientBean.getPrefLang());

		return patientDetailsArr;

	}

	@Override
	public void generatePDFPreview(Integer styleId,Integer patientId/*,	PrintDetailsDataBean printDetails*/) {
		try{
			GenericPrintStyle genericPrintStyle=genericPrintStyleRepository.findOne(styleId);
			int letterHeaderId=genericPrintStyle.getGenericPrintStyleHeaderId();
			int patientHeaderId=genericPrintStyle.getGenericPrintStylePatientHeaderId();
			String patientHeaderPage1;

			PatientRegistration patientDetails= patientRegistrationRepository.findOne(GenericPrintSpecification.getPatientDetails(patientId));
			List<InsuranceDataBean> insuranceList= getPatientInsuranceDetails(patientId); //patientInsDetailsRepository.findAll(PatientInsDetailsSpecification.getByPatientId(patientId));
			int encounterId=7328;//printDetails.getEncounterId();
			Encounter encounter = encounterRepository.findOne(EncounterSpecification.EncounterById(encounterId, true));
			PatientDataBean patientBean = parsePatientDetails(patientDetails, encounter, insuranceList);
			String[] patientDetailsArr=generatePatentDetailsArr(patientBean);
			String patientHeaderHTML=generateHeaderBean.generatePatientHeader(patientHeaderId, 1,patientDetailsArr);
			if(generateHeaderBean.getPatientHeaderType(patientHeaderId)==2){
				patientHeaderPage1=generateHeaderBean.generatePatientHeader(patientHeaderId, 2,patientDetailsArr);
			}else{
				patientHeaderPage1=patientHeaderHTML;
			}

			String headerHTML="";
			if(letterHeaderId>0){
				headerHTML=generateHeaderBean.generateHeader(letterHeaderId);
			}
			int footerId=genericPrintStyle.getGenericPrintStyleFooterId();
			String footerHTML="";
			int pageVariant=0;
			if(footerId>0){
				footerHTML=generateFooterBean.generateFooter(footerId);
				pageVariant=generateFooterBean.getPageFormatForFooter(footerId);
			}
			int pageOrientation=1;//landscape
			Rectangle pageSize=PageSize.A4;
			//generatePDFBean.generatePDF(headerHTML,patientHeaderPage1,patientHeaderHTML,footerHTML,pageVariant,pageSize,pageOrientation);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public GenericPrintBean getCompleteDetails(Integer patientId, Integer encounterId) throws Exception {

		List<Integer> insTypeList = new ArrayList<Integer>();
		insTypeList.add(1);
		insTypeList.add(2);

		PatientRegistration patientDetails= getPatientDetails(patientId);
		List<InsuranceDataBean> insuranceList= getPatientInsuranceDetails(patientId);//patientInsDetailsRepository.findAll(PatientInsDetailsSpecification.getByPatientId(patientId));
		Encounter encounter = getEncounterDetails(encounterId);
		List<EmployeeDataBean> empList =  getEmployeeDetails(); //employeeProfileRepository.findAll(EmployeeSpecification.getUsersList("asc"));
		List<PosTable> posList = posTableRepository.findAll(PosTableSpecification.getPOSDetails());
		PatientDataBean patientBean = parsePatientDetails(patientDetails, encounter, insuranceList);
		List<EmployeeDataBean> empolyeeBean = parseEmpState(empList);
		List<PosDataBean> posBean = parsePOSDetails(posList);
		List<InitialSettings> initialList= initialSettingsRepository.findAll(Specifications.where(InitialSettingsSpecification.optionType(4)).and(InitialSettingsSpecification.optionVisible(true)));

		DefaultPracticeBean practiceBean = parsePracticeDetails(initialList);
		GenericPrintBean genericBean = new GenericPrintBean(patientBean, empolyeeBean, posBean, practiceBean);
		return genericBean;

	}

	private List<EmployeeDataBean> getEmployeeDetails() {
		final ArrayList<Integer> groupIdList=new ArrayList<Integer>(Arrays.asList(-1,-2,-3,-5,-6,-7,-10,-25));

		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<EmployeeDataBean> query= builder.createQuery(EmployeeDataBean.class);
		Root<EmployeeProfile> root= query.from(EmployeeProfile.class);

		Expression<Integer> exprToId= root.get(EmployeeProfile_.empProfileGroupid);
		Predicate activeEmployee= builder.and(
				builder.equal(root.get(EmployeeProfile_.empProfileIsActive), true), 
				builder.notLike(builder.lower(root.get(EmployeeProfile_.empProfileFullname)), "%demo%"),
				exprToId.in(groupIdList));
		Predicate predicate= builder.and(builder.isNotNull(root.get(EmployeeProfile_.empProfileEmpid)));

		query.select(builder.construct(EmployeeDataBean.class, 
				root.get(EmployeeProfile_.empProfileEmpid),
				root.get(EmployeeProfile_.empProfileLoginid),
				root.get(EmployeeProfile_.empProfileLname),
				root.get(EmployeeProfile_.empProfileFname),
				root.get(EmployeeProfile_.empProfileMi),
				root.get(EmployeeProfile_.empProfileCredentials),
				root.get(EmployeeProfile_.empProfileAddress),
				root.get(EmployeeProfile_.empProfileState),
				root.get(EmployeeProfile_.empProfileCity),
				root.get(EmployeeProfile_.empProfileZip),
				root.get(EmployeeProfile_.empProfilePhoneno),
				root.get(EmployeeProfile_.empProfileMailid))
				);
		query.where(predicate, activeEmployee);
		query.orderBy(builder.asc(root.get(EmployeeProfile_.empProfileGroupid)),builder.asc(root.get(EmployeeProfile_.empProfileFullname)));

		return em.createQuery(query).getResultList();

	}

	private List<InsuranceDataBean> getPatientInsuranceDetails(Integer patientId) {

		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<InsuranceDataBean> query= builder.createQuery(InsuranceDataBean.class);
		Root<PatientInsDetail> root= query.from(PatientInsDetail.class);
		Join<PatientInsDetail, InsCompAddr> addrJoin= root.join(PatientInsDetail_.insCompAddr,JoinType.LEFT);
		Join<InsCompAddr, InsCompany> compJoin= addrJoin.join(InsCompAddr_.insCompany, JoinType.LEFT);

		Predicate patientIdPred= builder.equal(root.get(PatientInsDetail_.patientInsDetailPatientid),patientId);
		Predicate insTypePred = root.get(PatientInsDetail_.patientInsDetailInstype).in(1,2);
		Predicate insIsactivePred = builder.equal(root.get(PatientInsDetail_.patientInsDetailIsactive), true);		

		query.select(builder.construct(InsuranceDataBean.class, 
				root.get(PatientInsDetail_.patientInsDetailPatientinsuranceid),
				builder.coalesce(root.get(PatientInsDetail_.patientInsDetailInstype),-1),
				builder.coalesce(compJoin.get(InsCompany_.insCompanyId), -1),
				builder.coalesce(compJoin.get(InsCompany_.insCompanyName), ""),
				builder.coalesce(addrJoin.get(InsCompAddr_.insCompAddrAddress), ""),
				builder.coalesce(addrJoin.get(InsCompAddr_.insCompAddrCity), ""),
				builder.coalesce(addrJoin.get(InsCompAddr_.insCompAddrState), ""),
				builder.coalesce(addrJoin.get(InsCompAddr_.insCompAddrZip), "")));

		query.where(patientIdPred,insTypePred,insIsactivePred);
		return em.createQuery(query).getResultList();
	}

	private Encounter getEncounterDetails(Integer encounterId) {

		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Encounter> query= builder.createQuery(Encounter.class);
		Root<Encounter> root= query.from(Encounter.class);

		query.select(builder.construct(Encounter.class, root.get(Encounter_.encounterId),
				builder.function("to_char", String.class, root.get(Encounter_.encounterDate),builder.literal("MM/dd/yyyy")),
				root.get(Encounter_.encounterPos),
				root.get(Encounter_.encounterRefDoctor),
				root.get(Encounter_.encounter_service_doctor)));

		query.where(builder.equal(root.get(Encounter_.encounterId), encounterId));

		try{
			return em.createQuery(query).getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	private PatientRegistration getPatientDetails(Integer patientId) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<PatientRegistration> query= builder.createQuery(PatientRegistration.class);
		Root<PatientRegistration> root= query.from(PatientRegistration.class);

		/*PatientRegistration patient= new PatientRegistration();
		query.multiselect(
				root.get(PatientRegistration_.patientRegistrationLastName),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationMidInitial), ""),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationFirstName), ""),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationAccountno), ""),						
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationSex), 0),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationAddress1), ""),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationAddress2), ""),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationCity), ""),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationState), ""),
				builder.coalesce(root.get(PatientRegistration_.patientRegistrationZip), ""));*/
		query.select(builder.construct(PatientRegistration.class,
				root.get(PatientRegistration_.patientRegistrationLastName),
				root.get(PatientRegistration_.patientRegistrationMidInitial),
				root.get(PatientRegistration_.patientRegistrationFirstName),
				root.get(PatientRegistration_.patientRegistrationAccountno),
				builder.function("to_char", String.class, root.get(PatientRegistration_.patientRegistrationDob),builder.literal("MM/dd/yyyy")),
				root.get(PatientRegistration_.patientRegistrationSex),
				root.get(PatientRegistration_.patientRegistrationAddress1),
				root.get(PatientRegistration_.patientRegistrationAddress2),
				root.get(PatientRegistration_.patientRegistrationCity),
				root.get(PatientRegistration_.patientRegistrationState),
				root.get(PatientRegistration_.patientRegistrationZip),
				root.get(PatientRegistration_.patientRegistrationPhoneNo),
				root.get(PatientRegistration_.patientRegistrationWorkNo),
				root.get(PatientRegistration_.patientRegistrationPosId),
				root.get(PatientRegistration_.patientRegistrationReferingPhysician),
				root.get(PatientRegistration_.patientRegistrationPrincipalDoctor),										
				root.get(PatientRegistration_.patientRegistrationCellno),
				root.get(PatientRegistration_.patientRegistrationEthnicity),
				root.get(PatientRegistration_.patientRegistrationRace),
				root.get(PatientRegistration_.patientRegistrationPreferredLan)));
				query.where(builder.equal(root.get(PatientRegistration_.patientRegistrationId), patientId));
		/*
		try{
			Object[] result= em.createQuery(query).getSingleResult();
			if(result != null){
				patient.setPatientRegistrationLastName(result[0].toString());
				patient.setPatientRegistrationMidInitial(result[1].toString());
				patient.setPatientRegistrationFirstName(result[2].toString());
				patient.setPatientRegistrationAccountno(result[3].toString());
				patient.setPatientRegistrationSex(Integer.parseInt(result[4].toString()));
				patient.setPatientRegistrationAddress1(result[5].toString());
				patient.setPatientRegistrationAddress2(result[6].toString());
				patient.setPatientRegistrationCity(result[7].toString());
				patient.setPatientRegistrationState(result[8].toString());
				patient.setPatientRegistrationZip(result[9].toString());
			}
			return patient;
		}catch(NoResultException e){
			return null;
		}*/
		try{
			return em.createQuery(query).getSingleResult();
		}catch(NoResultException e){			
			return null;
		}
	}

	/**
	 * Parsing Practice Details
	 * @param initialList
	 * @return
	 */
	private DefaultPracticeBean parsePracticeDetails(List<InitialSettings> initialList) {

		DefaultPracticeBean practiceBean = new DefaultPracticeBean();
		for(int i=0; i<initialList.size(); i++){
			if(initialList.get(i).getInitialSettingsOptionName() != null && !initialList.get(i).getInitialSettingsOptionName().trim().isEmpty()){
				if(initialList.get(i).getInitialSettingsOptionName().trim().equalsIgnoreCase("Doctor Message"))
					practiceBean.setPracticeDrMsg(initialList.get(i).getInitialSettingsOptionValue());
				else if(initialList.get(i).getInitialSettingsOptionName().trim().equalsIgnoreCase("Practice Name"))
					practiceBean.setPracticeName(initialList.get(i).getInitialSettingsOptionValue());
				else if(initialList.get(i).getInitialSettingsOptionName().trim().equalsIgnoreCase("Address"))
					practiceBean.setPracticeStreet(initialList.get(i).getInitialSettingsOptionValue());
				else if(initialList.get(i).getInitialSettingsOptionName().trim().equalsIgnoreCase("city"))
					practiceBean.setPracticeCity(initialList.get(i).getInitialSettingsOptionValue());
				else if(initialList.get(i).getInitialSettingsOptionName().trim().equalsIgnoreCase("state")){
					practiceBean.setPracticeState(getState(initialList.get(i).getInitialSettingsOptionValue())); //billingConfigTableRepository.findAll(BillingConfigTableSpecification.getState(initialList.get(i).getInitialSettingsOptionValue()));
				}
				else if(initialList.get(i).getInitialSettingsOptionName().trim().equalsIgnoreCase("zipCode"))
					practiceBean.setPracticeZip(initialList.get(i).getInitialSettingsOptionValue());
				else if(initialList.get(i).getInitialSettingsOptionName().trim().equalsIgnoreCase("Web Address"))
					practiceBean.setPracticeWebAddress(initialList.get(i).getInitialSettingsOptionValue());
				else if(initialList.get(i).getInitialSettingsOptionName().trim().equalsIgnoreCase("email Address"))
					practiceBean.setPracticeEmail(initialList.get(i).getInitialSettingsOptionValue());
				else if(initialList.get(i).getInitialSettingsOptionName().trim().equalsIgnoreCase("Phone No"))
					practiceBean.setPracticePhoneNum(initialList.get(i).getInitialSettingsOptionValue());
				else if(initialList.get(i).getInitialSettingsOptionName().trim().equalsIgnoreCase("Fax Number"))
					practiceBean.setPracticeFaxNum(initialList.get(i).getInitialSettingsOptionValue());
			}
		}
		return practiceBean;
	}

	/**
	 * Parsing Patient Details
	 * @param patientDetails
	 * @param encounter
	 * @return
	 * @throws Exception
	 */
	private PatientDataBean parsePatientDetails(PatientRegistration patientDetails, Encounter encounter, List<InsuranceDataBean> insuranceBean) throws Exception {
		String patientName = textFormat.getFormattedName(patientDetails.getPatientRegistrationFirstName(), patientDetails.getPatientRegistrationMidInitial(), patientDetails.getPatientRegistrationLastName(), "");
		String temp= patientDetails.getPatientRegistrationSpoketo();
		String age = getAge(temp);
		String dos = null;
		String gender = patientDetails.getPatientRegistrationSex().toString();
		String accountId = patientDetails.getPatientRegistrationAccountno();
		String phNum = textFormat.getFormattedPhoneNum(patientDetails.getPatientRegistrationPhoneNo());
		String dob = temp;
		String mobileNum = textFormat.getFormattedPhoneNum(patientDetails.getPatientRegistrationCellno());
		String state = patientDetails.getPatientRegistrationState();
		String address = null;
		String refPhyName = null;
		String serviceRefName = null;
		String ethinicity = isNull(patientDetails.getPatientRegistrationEthnicity());
		String race = isNull(patientDetails.getPatientRegistrationRace());
		String prefLang = patientDetails.getPatientRegistrationPreferredLan();

		if(encounter != null) {
			//			dos = textFormat.getFormattedDate(encounter.getEncounterDate());
			dos = encounter.getMedicationAttestationStatus();
		}

		List<Map<String, String>> billing = getStateGender(state, gender);//billingConfigTableRepository.findAll(BillingConfigTableSpecification.getStateGender(state, gender));		
		state = "";
		gender = "";
		if(billing != null) {
			for(int i=0; i<billing.size(); i++) {
				Map<String, String> bill = billing.get(i);
				if(bill.get("id") != null && Integer.parseInt(bill.get("id").toString()) == 5001) {
					state = bill.get("desc");
				}
				if(bill.get("id") != null && Integer.parseInt(bill.get("id").toString()) == 51) {
					gender = bill.get("desc");
				}
			}
		}

		address = textFormat.getAddress(patientDetails.getPatientRegistrationAddress1(),patientDetails.getPatientRegistrationAddress2(),patientDetails.getPatientRegistrationCity(),state,patientDetails.getPatientRegistrationZip());
		ReferringDoctor refPhyEntity = null;
		if(patientDetails.getPatientRegistrationReferingPhysician() != null)
			refPhyEntity = getReferringPhyDetails((long)patientDetails.getPatientRegistrationReferingPhysician());	
		refPhyName = "";
		if(refPhyEntity != null)
			refPhyName = textFormat.getFormattedName(refPhyEntity.getreferring_doctor_firstname(), refPhyEntity.getreferring_doctor_midinitial(), refPhyEntity.getreferring_doctor_lastname(), refPhyEntity.getreferring_doctor_credential());
		ReferringDoctor serviceRefEntity=null;
		if(encounter != null)
			serviceRefEntity = getReferringPhyDetails(encounter.getEncounterRefDoctor());
		serviceRefName = "";
		if(serviceRefEntity != null)
			serviceRefName = textFormat.getFormattedName(serviceRefEntity.getreferring_doctor_firstname(), serviceRefEntity.getreferring_doctor_midinitial(), serviceRefEntity.getreferring_doctor_lastname(), serviceRefEntity.getreferring_doctor_credential());

		//		List<InsuranceDataBean> insuranceBean = parseInsuranceDetails(practiceList);

		EmployeeDataBean principalDrData = null;
		EmployeeProfile employee = getEmpDetails((long)patientDetails.getPatientRegistrationPrincipalDoctor());
		if(employee != null){
			principalDrData = parseDoctorDetails(employee);
		}

		//		List<Billinglookup> billingEthinicity = billinglookupRepository.findAll(BillingLookupSpecification.getDetails(ethinicity, race, prefLang));
		List<String> ethniArr = getList(ethinicity);
		List<String> raceArr = getList(race);		
		JSONArray billingEthinicity = getLookupDetails(ethniArr, raceArr, prefLang);

		ethinicity = "";
		race = "";
		prefLang = "";
		if(billingEthinicity != null) {
			for(int i=0; i<billingEthinicity.length(); i++) {
				JSONObject bill = billingEthinicity.getJSONObject(i);
				if(bill.get("group") != null && bill.getInt("group") == 251) {
					ethinicity = ethinicity + bill.getString("name") + ", ";
				}
				if(bill.get("group") != null && bill.getInt("group") == 250) {
					race = race + bill.getString("name") + ", ";
				}
				if(bill.get("group") != null && bill.getInt("group") == 253) {
					prefLang = bill.getString("name");
				}
			}
		}
		if(ethinicity.lastIndexOf(",")!=-1)
			ethinicity= ethinicity.substring(0, ethinicity.lastIndexOf(","));
		if(race.lastIndexOf(",")!=-1)
			race= race.substring(0, race.lastIndexOf(","));

		
		EmployeeDataBean serviceDrData = null;
		if(encounter != null) {
			EmployeeProfile emp = getEmpDetails(encounter.getEncounterServiceDoctor());
			if(emp!=null)
				serviceDrData = parseDoctorDetails(emp);

		}


		Integer encounterId = null, patientId = null;
		if(encounter != null)
			encounterId = encounter.getEncounterId();
		if(patientDetails != null) {
			patientId = patientDetails.getPatientRegistrationId();
		}
		List<PosDataBean> posList = null;		
		if(encounter != null)
			posList = getPOSDetails(encounter.getEncounterPos()); //posTableRepository.findAll(PosTableSpecification.getPOSDetailsById(encounter.getEncounterPos()));
		List<PosDataBean> posBean = parsePOSDetails1(posList);
		PatientDataBean bean = new PatientDataBean(patientName, age, dos, gender, accountId, phNum, dob,
				mobileNum, address, refPhyName, serviceRefName, insuranceBean, posBean, principalDrData,
				serviceDrData, ethinicity, race, prefLang, patientId, encounterId);

		return bean;
	}

	private List<String> getList(String value) {
		
		List<String> list= new ArrayList<String>();
		if(value== null || value.trim().isEmpty())
			list.add("-1");
		else{
			String arr[]= value.split(",");		
			for(int i=0; i<arr.length; i++){
				list.add(arr[i]);
			}
		}
		return list;
	}

	private JSONArray getLookupDetails(List<String> ethniArr,
			List<String> raceArr, String prefLang) throws Exception {

		JSONArray returnList= new JSONArray();

		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<Billinglookup> root= query.from(Billinglookup.class);

		Predicate ethinPred= root.get(Billinglookup_.blookIntid).in(ethniArr);
		Predicate groupPred1 = builder.equal(root.get(Billinglookup_.blookGroup), 251);

		Predicate racePred= root.get(Billinglookup_.blookIntid).in(raceArr);
		Predicate groupPred2 = builder.equal(root.get(Billinglookup_.blookGroup), 250);

		Predicate prefLangPred= builder.equal(root.get(Billinglookup_.blookIntid), prefLang);
		Predicate groupPred3 = builder.equal(root.get(Billinglookup_.blookGroup), 253);

		Predicate ethPred = builder.and(ethinPred, groupPred1);
		Predicate racPred = builder.and(racePred, groupPred2);
		Predicate prePred = builder.and(prefLangPred, groupPred3);

		query.multiselect(builder.coalesce(root.get(Billinglookup_.blookGroup), -1),
				builder.coalesce(root.get(Billinglookup_.blookName),""));	
		query.where(builder.or(ethPred, racPred, prePred));

		List<Object[]> result= em.createQuery(query).getResultList();

		for(int i=0; i<result.size(); i++){
			Object[] resultObj= result.get(i);
			JSONObject obj= new JSONObject();
			obj.put("group", Short.parseShort(resultObj[0].toString()));
			obj.put("name", resultObj[1].toString());
			returnList.put(obj);
		}
		return returnList;
	}

	private List<PosDataBean> getPOSDetails(Integer posId) {

		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<PosDataBean> query= builder.createQuery(PosDataBean.class);
		Root<PosTable> root= query.from(PosTable.class);

		Join<PosTable, PlaceOfService> posJoin = root.join(PosTable_.placeOfService,JoinType.LEFT);
		Join<PosTable, PosType> typeJoin= root.join(PosTable_.posType,JoinType.LEFT);


		Predicate isactivePred = builder.equal(root.get(PosTable_.posTableIsActive), true);
		Predicate poscodePred = builder.not((root.get(PosTable_.posTablePosCode).in(40)));
		Predicate placeIsActivePred = builder.equal(posJoin.get(PlaceOfService_.placeOfServiceIsActive), true);
		Predicate posIdpred = builder.equal(root.get(PosTable_.posTableRelationId), posId);

		query.select(builder.construct(PosDataBean.class, 
				root.get(PosTable_.posTableRelationId),
				root.get(PosTable_.posTablePlaceId),
				root.get(PosTable_.posTablePlaceOfService),
				root.get(PosTable_.posTableFacilityComments),
				posJoin.get(PlaceOfService_.placeOfServiceAddress),
				posJoin.get(PlaceOfService_.placeOfServiceState),
				posJoin.get(PlaceOfService_.placeOfServiceCity),
				posJoin.get(PlaceOfService_.placeOfServiceZip),
				posJoin.get(PlaceOfService_.placeOfServicePhone),
				posJoin.get(PlaceOfService_.placeOfServiceFax),
				typeJoin.get(PosType_.posTypeTypeName)));

		query.where(posIdpred, isactivePred, poscodePred, placeIsActivePred);
		query.orderBy(builder.asc(root.get(PosTable_.posTableFacilityComments)));

		return em.createQuery(query).getResultList();
	}

	/**
	 * Get State and Gender
	 * @param state
	 * @param gender
	 * @return
	 */
	private List<Map<String, String>> getStateGender(String state, String gender) {

		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
		Root<BillingConfigTable> root= query.from(BillingConfigTable.class);

		Predicate statePedicate = builder.equal(root.get(BillingConfigTable_.billingConfigTableConfigId), state);
		Predicate lookupPredicate1 = builder.equal(root.get(BillingConfigTable_.billingConfigTableLookupId), 5001);

		Predicate genderPedicate = builder.equal(root.get(BillingConfigTable_.billingConfigTableConfigId), gender);
		Predicate lookupPredicate2 = builder.equal(root.get(BillingConfigTable_.billingConfigTableLookupId), 51);

		Predicate sPred = builder.and(statePedicate,lookupPredicate1);
		Predicate gPred = builder.and(genderPedicate,lookupPredicate2);

		query.multiselect(root.get(BillingConfigTable_.billingConfigTableLookupId),
				root.get(BillingConfigTable_.billingConfigTableLookupDesc));
		query.where(builder.or(sPred, gPred));

		List<Object[]> result= em.createQuery(query).getResultList();
		List<Map<String, String>> returnList= new ArrayList<Map<String,String>>();
		for(int i=0; i<result.size(); i++){
			Object[] obj= result.get(i);
			Map<String, String> returnObj= new HashMap<String, String>();
			returnObj.put("id", obj[0].toString());
			returnObj.put("desc", obj[1].toString());
			returnList.add(returnObj);
		}
		return returnList;
	}

	private EmployeeProfile getEmpDetails(
			Long empId) {

		try{
			CriteriaBuilder builder= em.getCriteriaBuilder();
			CriteriaQuery<EmployeeProfile> query= builder.createQuery(EmployeeProfile.class);
			Root<EmployeeProfile> root= query.from(EmployeeProfile.class);

			query.select(builder.construct(EmployeeProfile.class, 
					root.get(EmployeeProfile_.empProfileEmpid),
					root.get(EmployeeProfile_.empProfileLoginid),
					root.get(EmployeeProfile_.empProfileFname),
					root.get(EmployeeProfile_.empProfileLname),
					root.get(EmployeeProfile_.empProfileMi),
					root.get(EmployeeProfile_.empProfileCredentials),
					root.get(EmployeeProfile_.empProfileAddress),
					root.get(EmployeeProfile_.empProfileCity),
					root.get(EmployeeProfile_.empProfileState),				
					root.get(EmployeeProfile_.empProfilePhoneno),
					root.get(EmployeeProfile_.empProfileMailid)));

			query.where(builder.equal(root.get(EmployeeProfile_.empProfileEmpid), empId));

			return em.createQuery(query).getSingleResult();
		}catch(NoResultException e){			
			return null;
		}
	}

	private ReferringDoctor getReferringPhyDetails(
			Long refId) {

		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<ReferringDoctor> query= builder.createQuery(ReferringDoctor.class);
		Root<ReferringDoctor> root= query.from(ReferringDoctor.class);

		query.select(builder.construct(ReferringDoctor.class, root.get(ReferringDoctor_.referring_doctor_uniqueid),
				root.get(ReferringDoctor_.referring_doctor_lastname),
				root.get(ReferringDoctor_.referring_doctor_midinitial),
				root.get(ReferringDoctor_.referring_doctor_firstname),
				root.get(ReferringDoctor_.referring_doctor_credential)));
		query.where(builder.equal(root.get(ReferringDoctor_.referring_doctor_uniqueid), refId));

		try{
			ReferringDoctor result= em.createQuery(query).getSingleResult();
			return result;
		}catch(NoResultException e){
			return null;
		}		
	}

	/**
	 * Parsing service doctor details
	 * @param encounter
	 * @return
	 * @throws Exception
	 */
	private EmployeeDataBean parseDoctorDetails(EmployeeProfile emp) throws Exception {
		EmployeeDataBean doctorData = null;		
		String name = "", state = "";
		if(emp != null) {
			name = textFormat.getFormattedName(emp.getEmpProfileFname(), emp.getEmpProfileMi(), emp.getEmpProfileLname(), emp.getEmpProfileCredentials());
		}
		state = getState(emp.getEmpProfileState()); //billingConfigTableRepository.findAll(BillingConfigTableSpecification.getState(emp.getEmpProfileState()));
		doctorData = new EmployeeDataBean(emp.getEmpProfileEmpid(), -1, name, emp.getEmpProfileAddress(), state, emp.getEmpProfileCity(), emp.getEmpProfileZip(), emp.getEmpProfilePhoneno(), emp.getEmpProfileMailid());
		return doctorData;
	}

	/**
	 * Parsing Employee Details
	 * @param empList
	 * @param textFormat
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<EmployeeDataBean> parseEmployeeDetails(List<EmployeeProfile> empList) throws Exception {

		List<EmployeeDataBean> empBean = new ArrayList<EmployeeDataBean>();

		for(int i=0; i<empList.size(); i++) {
			EmployeeProfile emp = empList.get(i);

			if(emp != null) {
				int empId = emp.getEmpProfileEmpid();
				int loginId = emp.getEmpProfileLoginid();
				String state = emp.getEmpProfileState();
				state= getState(state); //billingConfigTableRepository.findOne(BillingConfigTableSpecification.getState(state));
				String fullName = textFormat.getFormattedName(emp.getEmpProfileFname(), emp.getEmpProfileMi(), emp.getEmpProfileLname(), emp.getEmpProfileCredentials());
				//				String fullAddress = textFormat.getAddress(emp.getEmpProfileAddress(), "", emp.getEmpProfileCity(), state, emp.getEmpProfileZip());

				empBean.add(new EmployeeDataBean(empId, loginId, fullName, emp.getEmpProfileAddress(), state, emp.getEmpProfileCity(), emp.getEmpProfileZip(), emp.getEmpProfilePhoneno(), emp.getEmpProfileMailid()));
			}
		}

		return empBean;
	}

	public List<EmployeeDataBean> parseEmpState(List<EmployeeDataBean> empList) throws Exception {

		for(int i=0; i<empList.size(); i++) {
			EmployeeDataBean emp = empList.get(i);

			if(emp != null) {
				String state = emp.getEmpState();
				state = getState(state); //billingConfigTableRepository.findOne(BillingConfigTableSpecification.getState(state));
				String fullName = textFormat.getFormattedName(emp.getEmpFirstName(), emp.getEmpMiddleName(), emp.getEmpLastName(), emp.getEmpCredentials());
				//				String fullAddress = textFormat.getAddress(emp.getEmpProfileAddress(), "", emp.getEmpProfileCity(), state, emp.getEmpProfileZip());
				emp.setEmpFullname(fullName);
				emp.setEmpState(state);				
			}
		}

		return empList;
	}

	@Override
	public EmployeeDataBean parseEmployeeDetail(EmployeeProfile emp) throws Exception {

		EmployeeDataBean empBean = null;

		if(emp != null) {
			int empId = emp.getEmpProfileEmpid();
			int loginId = emp.getEmpProfileLoginid();
			String state = emp.getEmpProfileState();
			state = getState(state); //billingConfigTableRepository.findOne(BillingConfigTableSpecification.getState(state));
			String fullName = textFormat.getFormattedName(emp.getEmpProfileFname(), emp.getEmpProfileMi(), emp.getEmpProfileLname(), emp.getEmpProfileCredentials());
			//			String fullAddress = textFormat.getAddress(emp.getEmpProfileAddress(), "", emp.getEmpProfileCity(), state, emp.getEmpProfileZip());

			empBean = new EmployeeDataBean(empId, loginId, fullName, emp.getEmpProfileAddress(), state, emp.getEmpProfileCity(), emp.getEmpProfileZip(), emp.getEmpProfilePhoneno(), emp.getEmpProfileMailid());
		}

		return empBean;
	}

	@Override
	public EmployeeDataBean parseEmployeeDetail1(EmployeeDataBean emp) throws Exception {

		if(emp != null) {
			String state = emp.getEmpState();
			state = getState(state); //billingConfigTableRepository.findOne(BillingConfigTableSpecification.getState(state));
			String fullName = textFormat.getFormattedName(emp.getEmpFirstName(), emp.getEmpMiddleName(), emp.getEmpLastName(), emp.getEmpCredentials());
			//			String fullAddress = textFormat.getAddress(emp.getEmpProfileAddress(), "", emp.getEmpProfileCity(), state, emp.getEmpProfileZip());

			emp.setEmpFullname(fullName);
			emp.setEmpState(state);
		}

		return emp;
	}

	/**
	 * Parsing Insurance Details
	 * @param patientDetails
	 * @param textFormat
	 * @return
	 */
	private List<InsuranceDataBean> parseInsuranceDetails(List<PatientInsDetail> patientIns) {
		List<InsuranceDataBean> insuranceBean = new ArrayList<InsuranceDataBean>();
		if (patientIns != null)
			for (int i = 0; i < patientIns.size(); i++) {
				PatientInsDetail insCompany = patientIns.get(i);
				String insId = insCompany.getPatientInsDetailPatientinsuranceid();
				Integer insType = insCompany.getPatientInsDetailInstype();
				if (insCompany != null) {
					InsCompAddr insCompAddr = insCompany.getInsCompAddr();
					if (insCompAddr != null) {
						String insAddress = isNull(insCompAddr.getInsCompAddrAddress());
						String insCity = isNull(insCompAddr.getInsCompAddrCity());
						String insState = isNull(insCompAddr.getInsCompAddrState());
						String insZip = isNull(insCompAddr.getInsCompAddrZip());
						Integer insCompId = null;
						String insCompName = null;						
						InsCompany insComp = insCompAddr.getInsCompany();
						if (insComp != null) {
							insCompId = insComp.getInsCompanyId();
							insCompName = insComp.getInsCompanyName();
						}

						insuranceBean.add(new InsuranceDataBean(insId, insType, insCompId, insCompName, insAddress, insCity, insState, insZip));
					}
				}
			}
		return insuranceBean;
	}

	private String isNull(Object value) {
		if(value == null)
			return "";
		else
			return value.toString().trim();
	}

	/**
	 * Parsing POS details
	 * @param posList
	 * @param textFormat
	 * @return
	 */
	public List<PosDataBean> parsePOSDetails1(List<PosDataBean> posList) {

		if(posList != null){
			for(int i = 0; i < posList.size(); i++) {
				PosDataBean pos = posList.get(i);
				String practice = pos.getPosName();
				String comments = pos.getPosComments();
				String name = "";
				if(practice != null && !practice.isEmpty())
					name = "(" + practice + ") ";
				if(comments != null && !comments.isEmpty())
					name = name + comments;

				pos.setPosName(name);
				pos.setPosState(getState(pos.getPosState()));
			}
		}
		return posList;
	}

	@Override
	public List<PosDataBean> parsePOSDetails(List<PosTable> posList) {

		List<PosDataBean> posBean = new ArrayList<PosDataBean>();
		if(posList != null){
			for(int i = 0; i < posList.size(); i++) {
				PosTable pos = posList.get(i);
				int relationId = pos.getPosTableRelationId();
				int placeId = pos.getPosTablePlaceId();
				String practice = pos.getPosTablePlaceOfService();
				String comments = pos.getPosTableFacilityComments();
				String name = "";
				if(practice != null && !practice.isEmpty())
					name = "(" + practice + ") ";
				if(comments != null && !comments.isEmpty())
					name = name + comments;

				String address = null, state = null, city = null, zip = null, phNum = null, faxNum = null;           
				PlaceOfService placeofService = pos.getPlaceOfService();
				if(placeofService != null) {
					state = placeofService.getPlaceOfServiceState();
					state = getState(state); //billingConfigTableRepository.findOne(BillingConfigTableSpecification.getState(state));					
					address = placeofService.getPlaceOfServiceAddress();
					city = placeofService.getPlaceOfServiceCity();
					zip = placeofService.getPlaceOfServiceZip();
					phNum = placeofService.getPlaceOfServicePhone();
					faxNum = placeofService.getPlaceOfServiceFax();
				}

				String type = "";
				PosType posType = pos.getPosType();
				if(posType != null) {
					type = posType.getPosTypeTypeName();
				}

				posBean.add(new PosDataBean(relationId, placeId, name, address, state, city, zip, phNum, faxNum, type));
			}
		}
		return posBean;
	}

	/**
	 * Get State
	 * @param state
	 * @return
	 */
	private String getState(String state) {

		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<String> query= builder.createQuery(String.class);
		Root<BillingConfigTable> root= query.from(BillingConfigTable.class);
		Predicate statePedicate = builder.equal(root.get(BillingConfigTable_.billingConfigTableConfigId), state);
		Predicate lookupPredicate = builder.equal(root.get(BillingConfigTable_.billingConfigTableLookupId), 5001);

		query.select(builder.coalesce(root.get(BillingConfigTable_.billingConfigTableLookupDesc), ""));
		query.where(builder.and(statePedicate,lookupPredicate));

		state="";
		try{
			state= em.createQuery(query).getSingleResult();
		}catch(NoResultException e){
			return "";
		}
		return state;
	}

	@Override
	public PosDataBean parsePOSDetail(PosTable pos) {

		PosDataBean posBean = null;
		if(pos != null) {
			int relationId = pos.getPosTableRelationId();
			int placeId = pos.getPosTablePlaceId();
			String practice = pos.getPosTablePlaceOfService();
			String comments = pos.getPosTableFacilityComments();
			String name = "";
			if(practice != null && !practice.isEmpty())
				name = "(" + practice + ") ";
			if(comments != null && !comments.isEmpty())
				name = name + comments;

			String address = null, state = null, city = null, zip = null, phNum = null, faxNum = null;           
			PlaceOfService placeofService = pos.getPlaceOfService();
			if(placeofService != null) {
				state = placeofService.getPlaceOfServiceState();
				state = getState(state); //billingConfigTableRepository.findOne(BillingConfigTableSpecification.getState(state));
				address = placeofService.getPlaceOfServiceAddress();
				city = placeofService.getPlaceOfServiceCity();
				zip = placeofService.getPlaceOfServiceZip();
				phNum = placeofService.getPlaceOfServicePhone();
				faxNum = placeofService.getPlaceOfServiceFax();
			}

			String type = "";
			PosType posType = pos.getPosType();
			if(posType != null) {
				type = posType.getPosTypeTypeName();
			}

			posBean= new PosDataBean(relationId, placeId, name, address, state, city, zip, phNum, faxNum, type);
		}
		return posBean;
	}

	/**
	 * Method to get Header HTML as String
	 * @throws Exception 
	 */
	@Override
	public String getHeaderHTML(Integer styleId, Integer patientId, Integer encounterId,  String sharedPath) throws Exception{
		String headerHTML = "";
		try{

			GenericPrintBean genericPrintBean = getCompleteDetails(patientId, encounterId);
			GenericPrintStyle genericPrintStyle=genericPrintStyleRepository.findOne(styleId);
			int letterHeaderId=genericPrintStyle.getGenericPrintStyleHeaderId();
			if(letterHeaderId>0){
				headerHTML=generateHeaderBean.generateHeader(letterHeaderId, sharedPath, genericPrintBean);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return "failure";
		}
		return headerHTML;
	}

	@Override
	public String getPatientHeaderHTML(Integer styleId, Integer patientId, Integer encounterId) throws Exception {

		List<Integer> insTypeList = new ArrayList<Integer>();
		insTypeList.add(1);
		insTypeList.add(2);

		GenericPrintStyle genericPrintStyle=genericPrintStyleRepository.findOne(styleId);
		int patientHeaderId=genericPrintStyle.getGenericPrintStylePatientHeaderId();

		String patientHeaderHTML = "";
		PatientRegistration patientDetails= patientRegistrationRepository.findOne(GenericPrintSpecification.getPatientDetails(patientId, insTypeList));
		List<InsuranceDataBean> insuranceList= getPatientInsuranceDetails(patientId);//patientInsDetailsRepository.findAll(PatientInsDetailsSpecification.getByPatientId(patientId));
		//		int encounterId=7328;//printDetails.getEncounterId();
		Encounter encounter = encounterRepository.findOne(EncounterSpecification.EncounterById(encounterId, true));
		PatientDataBean patientBean = parsePatientDetails(patientDetails, encounter, insuranceList);
		String[] patientDetailsArr=generatePatentDetailsArr(patientBean);

		if(generateHeaderBean.getPatientHeaderType(patientHeaderId)==2){
			patientHeaderHTML = generateHeaderBean.generatePatientHeader(patientHeaderId, 2,patientDetailsArr);
		}else{
			patientHeaderHTML = generateHeaderBean.generatePatientHeader(patientHeaderId, 1,patientDetailsArr);
		}

		return patientHeaderHTML;
	}

	@Override
	public String getFooterHTML(Integer styleId){

		String footerHTML = "";
		GenericPrintStyle genericPrintStyle=genericPrintStyleRepository.findOne(styleId);
		int footerId=genericPrintStyle.getGenericPrintStyleFooterId();
		//		int pageVariant=0;
		if(footerId>0){
			footerHTML=generateFooterBean.generateFooter(footerId);
			//			pageVariant=generateFooterBean.getPageFormatForFooter(footerId);
		}    	
		return footerHTML;
	}

	@Override
	public PatientRegistration getPatientData(int patientId) {
		return patientRegistrationRepository.findOne(PatientRegistrationSpecification.byPatientId(patientId));
	}

	@Override
	public PatientRegistration getTesData(int patientId){
		return patientRegistrationRepository.findOne(GenericPrintSpecification.getPatientDetails(patientId));
	}

	@Override
	public Encounter getEncounterData(int encounterId) {
		return encounterRepository.findOne(EncounterSpecification.EncounterById(encounterId, true));
	}

	@Override
	public String getLeftHeaderHTML(Integer styleId) {
		String leftHeaderHTML="";
		GenericPrintStyle genericPrintStyle=genericPrintStyleRepository.findOne(styleId);
		int headerId=genericPrintStyle.getGenericPrintStyleHeaderId();
		/*if(headerId>0)
    		leftHeaderHTML=generateHeaderBean.generateLeftHeader(headerId);*/
		return leftHeaderHTML;
	}

	@Override
	public CustomGenericBean getCustomeGenericData(Integer styleId, Integer patientId, Integer encounterId, String sharedFolderPath) throws Exception{

		String headerHTML="",patientHeaderHTML="",leftHeaderHTML="",footerHTML="";
		CustomGenericBean customGenericBean = new CustomGenericBean();

		GenericPrintBean genericPrintBean = getCompleteDetails(patientId, encounterId);
		GenericPrintStyle genericPrintStyle=genericPrintStyleRepository.findOne(styleId);

		if(genericPrintStyle!=null){
			int letterHeaderId=genericPrintStyle.getGenericPrintStyleHeaderId();
			int patientHeaderId=genericPrintStyle.getGenericPrintStylePatientHeaderId();
			int footerId=genericPrintStyle.getGenericPrintStyleFooterId();
	
			PatientDataBean patientDataBean=genericPrintBean.getPatientBean(); 
			String[] patientDetailsArr=generatePatentDetailsArr(patientDataBean);
	
			if(letterHeaderId>0){
				headerHTML=generateHeaderBean.generateHeader(letterHeaderId, sharedFolderPath, genericPrintBean);
			}
	
			if(generateHeaderBean.getPatientHeaderType(patientHeaderId)==2){
				patientHeaderHTML = generateHeaderBean.generatePatientHeader(patientHeaderId, 2,patientDetailsArr);
			}else{
				patientHeaderHTML = generateHeaderBean.generatePatientHeader(patientHeaderId, 1,patientDetailsArr);
			}
	
			if(footerId>0){
				footerHTML=generateFooterBean.generateFooter(footerId);
			}
	
			if(letterHeaderId>0){
				leftHeaderHTML=generateHeaderBean.generateLeftHeader(letterHeaderId,genericPrintBean);
			}
		}
		customGenericBean.setHeaderHTML(headerHTML);
		customGenericBean.setPatientHeaderHTML(patientHeaderHTML);
		customGenericBean.setLeftHeaderHTML(leftHeaderHTML);
		customGenericBean.setFooterHTML(footerHTML);

		return customGenericBean;
	}

	@Override
	public void saveLeafLibrary(LeafLibrary leafLibrary) {
		leafLibraryRepository.save(leafLibrary);
	}

	@Override
	public List<LeafLibrary> getTemplatesList() {		
		return leafLibraryRepository.findAll(LeafLibrarySpecification.getAllTemplates());
	}

	@Override
	public List<LeafLibrary> getStyleTemplatesList(Integer styleId) {
		return leafLibraryRepository.findAll(LeafLibrarySpecification.getByPrintStyleId(styleId));
	}

	@Override
	public LeafLibrary getLeafLibrary(int templateId) {
		return leafLibraryRepository.findOne(LeafLibrarySpecification.getLeafDetailsById(templateId)) ;
	}

	public String getInsuranceName(PatientDataBean patientBean){
		String primInsId="";
		try{
			List<InsuranceDataBean> insuranceList= patientBean.getInsuranceDetails();
			if(insuranceList!= null){
				for(int i=0;i<insuranceList.size();i++){
					if(insuranceList.get(i).getInsType()== 1)
						primInsId= insuranceList.get(i).getInsCompName();
				}
			}
			return primInsId;
		}catch(Exception e){
			return "";
		}
	}

	/**
	 * Get template based on generic print style id 
	 */
	@Override
	public List<LeafLibrary> getLeafLibraryStyle(Integer styleId) {
		return leafLibraryRepository.findAll(LeafLibrarySpecification.getByPrintStyleId(styleId));
	}

	/**
	 * Get Age string
	 * @param dob
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getAge(String dob) {

		try{
			List<Object[]> result= em.createNativeQuery("select date_part('years',age( (select current_date),'"+dob+"')) as years,"
					+ "date_part('months',age( (select current_date),'"+dob+"')) as months,"
					+ "date_part('days',age( (select current_date),'"+dob+"')) as days").getResultList();

			String age="";
			if(result != null && result.size()>0){
				Object[] obj= result.get(0);
				int y= (int)Double.parseDouble(obj[0].toString());
				int m= (int)Double.parseDouble(obj[1].toString());
				int d= (int)Double.parseDouble(obj[2].toString());			
				if (y > 0 && m > 0) {
					age= y + " y " + m+ " m";
				} else if (y > 0) {
					age= y + " y";
				} else if (m > 0 && d > 0) {
					age= m + " m " + d +" d";
				} else if (m > 0) {
					age= m + " m";
				} else {
					age= d+" d";
				}
			}
			return age;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}

	/** 
	 * Set shared folder path from initial settings table
	 * */
	@Override
	public List<InitialSettings> getPracticeDetails() {

		List<InitialSettings> practiceDetails=initialSettingsRepository.findAll(GenericPrintSpecification.getPracticeDetails());

		for(int i=0;i<practiceDetails.size();i++){
			if(practiceDetails.get(i).getInitialSettingsOptionName()!=null && practiceDetails.get(i).getInitialSettingsOptionName().equalsIgnoreCase("Shared Folder Path")){
				sessionMap.setPracticeSharedFolderPath(practiceDetails.get(i).getInitialSettingsOptionValue());
			}
		}

		return practiceDetails;
	}

	/**
	 * Generate PDF file using HTML data input in shared folder
	 * @param styleId - Generic print style id
	 * @param patientId
	 * @param dataBean - Print details data bean
	 */
	@Override
	public void generatePDFPrint(Integer styleId, Integer patientId,PrintDetailsDataBean databean) {

		//Need to replace with common function
		getPracticeDetails();

		String sharedFolderPath=sessionMap.getPracticeSharedFolderPath()+"/CNM/PRINT";

		String headerHTML="",patientHeaderHTML="",leftHeaderHTML="",footerHTML="";
		String patientHeaderPage1="";
		int pageVariant=0;
		int headerRowCount=0;
		try{

			int encounterId=databean.getEncounterId();
			GenericPrintBean genericPrintBean = getCompleteDetails(patientId, encounterId);

			GenericPrintStyle genericPrintStyle=genericPrintStyleRepository.findOne(styleId);
			
			if(genericPrintStyle!=null){
			int letterHeaderId=genericPrintStyle.getGenericPrintStyleHeaderId();
			int patientHeaderId=genericPrintStyle.getGenericPrintStylePatientHeaderId();
			int footerId=genericPrintStyle.getGenericPrintStyleFooterId();
			PatientDataBean patientDataBean=genericPrintBean.getPatientBean(); 
			String[] patientDetailsArr=generatePatentDetailsArr(patientDataBean);

			//Header row count is used to get the height of top margin for PDF
				headerRowCount=generateHeaderBean.getPatientHeaderAttributeCount(patientHeaderId, 1);

			//Generate Letter header HTML
			if(letterHeaderId>0){
				headerHTML=generateHeaderBean.generateHeader(letterHeaderId, sharedFolderPath, genericPrintBean);
				if(!headerHTML.equalsIgnoreCase("")){
					headerHTML=headerHTML.replaceAll("cellpadding='0'", "");
					headerHTML="<head><style type='text/css' media='all'>td{padding-top:5px;}</style></head><body>"+headerHTML+"</body>";
				}
			}

			//Generate Patient header HTML
			patientHeaderHTML = generateHeaderBean.generatePatientHeader(patientHeaderId, 1,patientDetailsArr);
			if(generateHeaderBean.getPatientHeaderType(patientHeaderId)==2){
				patientHeaderPage1 = generateHeaderBean.generatePatientHeader(patientHeaderId, 2,patientDetailsArr);
			}else{
				patientHeaderPage1 = patientHeaderHTML;
			}

			//Generate footer HTML along with page number style
			if(footerId>0){
				footerHTML=generateFooterBean.generateFooter(footerId);
				pageVariant=generateFooterBean.getPageFormatForFooter(footerId);
			}

			//Generate Left side header HTML			
			if(letterHeaderId>0){
				leftHeaderHTML=generateHeaderBean.generateLeftHeader(letterHeaderId,genericPrintBean);
				if(!leftHeaderHTML.equalsIgnoreCase("")){
					leftHeaderHTML=leftHeaderHTML.replaceAll("cellpadding='0'", "");
					leftHeaderHTML="<head><style type='text/css' media='all'>td{padding-top:7px;}</style></head><body>"+leftHeaderHTML+"</body>";
				}
			}
			}
			headerRowCount=headerRowCount+1;
			// Content of PDF
			String contentHTML=URLDecoder.decode(databean.getHtmlData(),"UTF-8");
			contentHTML = contentHTML.replaceAll("[^\\x00-\\x7F^\\xB0]", "");
			contentHTML = contentHTML.replaceAll("&amp;apos;", "'");
			//Filepath of PDF file
			String fileName=sharedFolderPath+"/"+databean.getFileName();

			//Call to generate PDF with provided details
			generatePDFBean.generatePDF(fileName,headerHTML,patientHeaderPage1,patientHeaderHTML,footerHTML,leftHeaderHTML,contentHTML,pageVariant,getPageSize(1),0,headerRowCount);

		}catch(Exception e){
			e.printStackTrace();
		}


	}

	//To identify page size based on integer input given
	public Rectangle getPageSize(int pagesize){
		Rectangle pageRect;
		switch(pagesize){
		case 1:
			pageRect=PageSize.LETTER;
			break;
		case 2:
			pageRect=PageSize.A4;
			break;
		default:
			pageRect=PageSize.A4;
		}

		return pageRect;
	}

}