package com.glenwood.glaceemr.server.application.services.portal.portalForms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsQuestions;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsQuestionsOptions;
import com.glenwood.glaceemr.server.application.models.ClinicalIntakeFormBean;
import com.glenwood.glaceemr.server.application.models.ClinicalQuestionsGroup;
import com.glenwood.glaceemr.server.application.models.FileDetails;
import com.glenwood.glaceemr.server.application.models.FileName;
import com.glenwood.glaceemr.server.application.models.GroupQuestionsMapping;
import com.glenwood.glaceemr.server.application.models.PatientPortalAlertConfig;
import com.glenwood.glaceemr.server.application.models.LeafXmlVersion;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElementsQuestions;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElementsQuestions_;
import com.glenwood.glaceemr.server.application.models.PatientClinicalHistory;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.TabLibrary;
import com.glenwood.glaceemr.server.application.repositories.AlertEventRepository;
import com.glenwood.glaceemr.server.application.repositories.ClinicalElementsQuestionsOptionsRepository;
import com.glenwood.glaceemr.server.application.repositories.ClinicalElementsQuestionsRepository;
import com.glenwood.glaceemr.server.application.repositories.ClinicalQuestionsGroupRepository;
import com.glenwood.glaceemr.server.application.repositories.FileDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.FileNameRepository;
import com.glenwood.glaceemr.server.application.repositories.FormsTemplateRepository;
import com.glenwood.glaceemr.server.application.repositories.GroupQuestionsMappingRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientPortalUserRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientPortalAlertConfigRespository;
import com.glenwood.glaceemr.server.application.repositories.LeafVersionRepository;
import com.glenwood.glaceemr.server.application.repositories.LeafXmlVersionRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalElementsQuestionsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalHistoryRepository;
import com.glenwood.glaceemr.server.application.repositories.TabLibraryRepository;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.portal.portalDocuments.ExportHTMLAsPdf;
import com.glenwood.glaceemr.server.application.services.portal.portalDocuments.SharedFolderUtil;
import com.glenwood.glaceemr.server.application.services.portal.portalMedicalSummary.PortalMedicalSummaryService;
import com.glenwood.glaceemr.server.application.services.portal.portalSettings.PatientProfileSettingsFields;
import com.glenwood.glaceemr.server.application.services.portal.portalSettings.PortalSettingsService;
import com.glenwood.glaceemr.server.application.specifications.ConsentFormsSpecification;
import com.glenwood.glaceemr.server.application.specifications.PortalFormsSpecification;
import com.glenwood.glaceemr.server.application.specifications.PortalSettingsSpecification;
import com.glenwood.glaceemr.server.utils.DateUtil;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.glenwood.glaceemr.server.utils.HtmlTransformer;
import com.glenwood.glaceemr.server.utils.SessionMap;

@Service
public class PortalFormsServiceImpl implements PortalFormsService{


	@Autowired
	ClinicalElementsQuestionsRepository clinicalElementsQuestionsRepository;

	@Autowired
	PatientClinicalHistoryRepository patientClinicalHistoryRepository;

	@Autowired
	PatientClinicalElementsQuestionsRepository patientClinicalElementsQuestionsRepository;

	@Autowired
	ClinicalElementsQuestionsOptionsRepository clinicalElementsQuestionsOptionsRepository;

	@Autowired
	ClinicalQuestionsGroupRepository clinicalQuestionsGroupRepository;

	@Autowired
	GroupQuestionsMappingRepository groupQuestionsMappingRepository;

	@Autowired
	PortalSettingsService portalSettingsService;

	@Autowired
	PortalMedicalSummaryService portalMedicalSummaryService;

	@Autowired
	TabLibraryRepository tabLibraryRepository;

	@Autowired
	LeafVersionRepository leafVersionRepository;

	@Autowired
	LeafXmlVersionRepository leafXmlVersionRepository;

	@Autowired
	FileNameRepository fileNameRepository;
	
	@Autowired
	AlertEventRepository alertEventRepository;
	
	@Autowired
	PatientPortalAlertConfigRespository h810Respository;


	/*Consent Forms Related*/
	@Autowired
	FileDetailsRepository fileDetailsRepository;

	@Autowired
	FormsTemplateRepository formsTemplateRepository;

	@Autowired
	EntityManagerFactory emf ;

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	SessionMap sessionMap;

	@Autowired
	AuditTrailSaveService auditTrailSaveService;

	LinkedHashMap <String, PatientClinicalIntakeBean> patientElementsBeanMap=null;

	LinkedHashMap <String, PatientClinicalIntakeBean> patientOptionElementsBeanMap=null;

	LinkedHashMap<String, PatientClinicalIntakeBean> clinicalIntakeElements=null;

	String ClinicalElementsQuestionGwid;
	String PatientElementsQuestionGwid;
	String PatientElementsOptionGwid;

	public String ENCODING = "UTF-8";

	@Autowired
	HttpServletRequest httpServletRequest;


	List<ClinicalElementsQuestions> questionnaireElements=null;



	@Override
	public PortalClinicalIntakeAndConsentFormsListBean getPatientClinicalIntakeAndConsentFormsList(int patientId, int chartId, int isXML) {

		List<ClinicalIntakeFormBean> intakeFormsList=new ArrayList<ClinicalIntakeFormBean>();

		List<ClinicalQuestionsGroup> clinialIntakeFormsList = getPatientClinicalIntakeFormsList(isXML);


		if(clinialIntakeFormsList!=null){

			for(int i=0;i<clinialIntakeFormsList.size();i++){

				ClinicalIntakeFormBean intakeBean=new ClinicalIntakeFormBean();
				PatientClinicalElementsQuestions patientIntakeDetails=patientClinicalElementsQuestionsRepository.findOne(PortalFormsSpecification.getPatientIntakeFormDetails(patientId, clinialIntakeFormsList.get(i).getClinicalQuestionsGroupType()));
				intakeBean.setFormGroupDetails(clinialIntakeFormsList.get(i));
				intakeBean.setPatientFormDetails(patientIntakeDetails);
				intakeFormsList.add(intakeBean);
			}
		}

		List<FileDetails> consentFormsList = getAllPatientConsentFormsList(patientId, chartId);

		PortalClinicalIntakeAndConsentFormsListBean portalClinicalIntakeAndConsentFormsListBean=new PortalClinicalIntakeAndConsentFormsListBean();
		portalClinicalIntakeAndConsentFormsListBean.setClinicalIntakeFormsList(intakeFormsList);
		portalClinicalIntakeAndConsentFormsListBean.setConsentFormsList(consentFormsList);

		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Requested patient's clinical intake and consent forms with id:"+patientId,-1,
				httpServletRequest.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Requested patient's clinical intake and consent forms with id:"+patientId,"");
		
		return portalClinicalIntakeAndConsentFormsListBean;
	}



	@Override
	public List<ClinicalQuestionsGroup> getPatientClinicalIntakeFormsList(int isXML) {

		List<ClinicalQuestionsGroup> clinialIntakeFormsList=clinicalQuestionsGroupRepository.findAll(PortalFormsSpecification.getPatyientClinicalIntakeFormsList(isXML));

		return clinialIntakeFormsList;
	}



	@Override
	public List<ClinicalElementsQuestions> getLibraryQustionnaireElements(int patientId, int groupId) {

		clinicalIntakeElements=new LinkedHashMap<String, PatientClinicalIntakeBean>();

		List<ClinicalElementsQuestions> clinicalElementsQuestionsList=clinicalElementsQuestionsRepository.findAll(PortalFormsSpecification.getLibraryQustionnaireElements(patientId, groupId));

		/*for(int i=0;i<clinicalElementsQuestionsList.size();i++){

			PatientClinicalIntakeBean clinicalBean=new PatientClinicalIntakeBean();
			ClinicalElementsQuestions question=clinicalElementsQuestionsList.get(i);

			clinicalBean.setClinicalIntakeQuestionGwid(HUtil.Nz(question.getClinicalElementsQuestionsGwid(),""));
			clinicalBean.setClinicalIntakeQuestionSaveGwid(HUtil.Nz(question.getClinicalElementsQuestionsSavegwid(),""));
			clinicalBean.setClinicalIntakeQuestionCaption(HUtil.Nz(question.getClinicalElementsQuestionsCaption(),""));
			clinicalBean.setClinicalIntakeQuestionDatatype(HUtil.Nz(question.getClinicalElementsQuestionsDatatype(),""));
			clinicalBean.setClinicalIntakeOptionGwid(HUtil.Nz(question.getClinicalElementsQuestionsOptions().g,""));
			clinicalBean.setClinicalIntakeOptionSaveGwid(HUtil.Nz(rst.getString("clinical_elements_questions_options_gwid"),""));
			clinicalBean.setClinicalIntakeOptionName(HUtil.Nz(rst.getString("clinical_elements_questions_options_name"),""));
			clinicalBean.setClinicalIntakeOptionValue(HUtil.Nz(rst.getString("clinical_elements_questions_options_value"),""));
			clinicalBean.setOptValue(HUtil.Nz(rst.getString("clinical_elements_options_active"),""));
			clinicalBean.setClinicalIntakeOptionSelectBox(HUtil.Nz(rst.getString("clinical_elements_questions_options_selectbox"),""));
			clinicalBean.setClinicalElementsType(HUtil.Nz(rst.getString("clinical_elements_datatype"),""));
			clinicalBean.setIsBoolean(Boolean.parseBoolean(HUtil.Nz(rst.getString("isboolean"),"false")));
			Gwid1.put("gwid",HUtil.Nz(rst.getString("clinical_elements_questions_gwid"),"") );
			this.setGwid(Gwid1);
		    ClinicalElementsQuestionGwid=HUtil.Nz(rst.getString("clinical_elements_questions_gwid"),"000");
		    this.setClinicalIntakeElements(ClinicalElementsQuestionGwid, clinicalBean);	
		}*/

		setClinicalIntakeElements(clinicalElementsQuestionsList);
		System.out.println("questionnaireElements::::::::"+questionnaireElements.size());

		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Requested clinical intake forms questions with id:"+patientId,-1,
				httpServletRequest.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Requested clinical intake forms questions with id:"+patientId,"");
		
		return clinicalElementsQuestionsList;
	}

	public String getClinicalElementsQuestionGwid() {
		return ClinicalElementsQuestionGwid;
	}

	public void setClinicalElementsQuestionGwid(String clinicalElementsQuestionGwid) {
		ClinicalElementsQuestionGwid = clinicalElementsQuestionGwid;
	}

	public void setClinicalIntakeElements(List<ClinicalElementsQuestions> questionsList) {
		this.questionnaireElements=questionsList;
	}

	public ClinicalElementsQuestions getClinicalIntakeElements(String ClinicalIntakeQuestionGwid) {

		ClinicalElementsQuestions questionBean=null;

		for(int i=0;i<questionnaireElements.size();i++){

			if(questionnaireElements.get(i).getClinicalElementsQuestionsGwid().equalsIgnoreCase(ClinicalIntakeQuestionGwid))
				questionBean= questionnaireElements.get(i);

		}

		return questionBean;
	}


	@Override
	public ClinicalIntakeXMLBean getClinicalIntakePatientXml(int patientId, int requestId, int mode, int groupId, String groupName, int isXML, int retXML,int tabId, int limit, int pageNo) throws Exception {

		getLibraryQustionnaireElements(patientId, groupId);
		getPatientDetails(patientId);
		StringBuffer clinicalIntakeXML = new StringBuffer("");
		System.out.println("patientId:::::"+patientId+":::::::requestId:::::::"+requestId);
		if(isXML==1){
			requestId=getLeafRequestId(patientId, tabId);
		}else{

			tabId=0;
			requestId=getLeafRequestId(patientId, tabId);
		}

		String getPatientData="";

		if(getPatientClinicalElementsQuestionsData(patientId,requestId)==null||getPatientClinicalElementsQuestionsData(patientId,requestId).getPatientClinicalElementsQuestionsData()==null)
			getPatientData="";
		else
			getPatientData=getPatientClinicalElementsQuestionsData(patientId,requestId).getPatientClinicalElementsQuestionsData();

		System.out.println("getPatientData:::::::::"+getPatientData);
		if(!getPatientData.equalsIgnoreCase("")){
			Hashtable<Integer,String> patElemGwid=setPatientElementsBean(getPatientData.split("@#####@")[0]);//patElemGwid is hashtable of question ids
			System.out.println("patElemGwid::::::;"+patElemGwid.size());
			try{
				int totalNoOfQuestions=(int) getTotalClinicalQuestions(limit, pageNo, groupId);
				List<GroupQuestionsMapping> clinicalQuestions=getClinicalQuestions(limit, pageNo, groupId);
				int add=pageNo*limit;

				int totalNoofPages = (totalNoOfQuestions % limit == 0) ?  (totalNoOfQuestions / limit) : ((totalNoOfQuestions / limit) +1);
				clinicalIntakeXML.append("\n\t<questionnaire>");
				clinicalIntakeXML.append("\n\t<question>");
				clinicalIntakeXML.append("\n\t<header><patientid value=\""+patientId+"\">"+patientId+"</patientid>\n<requestid value=\""+requestId+"\">"+requestId+"</requestid>\n<mode value=\""+mode+"\">"+mode+"</mode><groupId value=\""+groupId+"\">"+groupId+"</groupId><groupName value=\""+groupName+"\">"+groupName+"</groupName></header>");
				clinicalIntakeXML.append("\n\t\t<tnoofquestions value=\""+totalNoOfQuestions+"\">"+totalNoOfQuestions+"</tnoofquestions>");
				clinicalIntakeXML.append("\n\t\t<noofpages value=\""+totalNoofPages+"\">"+totalNoofPages+"</noofpages>");
				clinicalIntakeXML.append("\n\t\t<pageno value=\""+pageNo+"\">"+pageNo+"</pageno>");

				System.out.println("patientElementsBeanMap.isEmpty():::::::"+patientElementsBeanMap.isEmpty()+":::::patientElementsBeanMap.size:::::"+patientElementsBeanMap.size()+":::::::::clinicalQuestions.size::::::::"+clinicalQuestions.size());
				if(patientElementsBeanMap.isEmpty()==true){
					for(int i=0; i<clinicalQuestions.size(); i++){
						int quesNo=i+add+1; 
						GroupQuestionsMapping question = clinicalQuestions.get(i);
						int count=1;
						String gwid = HUtil.Nz(question.getClinialELementsQuestionMapping().getClinicalElementsQuestionsGwid(),"");
						System.out.println("gwid::::::11111111:::::::::"+gwid);
						clinicalIntakeXML.append("\n\t\t<caption displayname=\""+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsCaption()+"\" clinicalintakeid=\""+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsGwid()+"\" noofquestions=\""+totalNoOfQuestions+"\" noofpages=\""+totalNoofPages+"\" pageno=\""+pageNo+"\" selectbox=\""+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsOptions().get(0).getClinicalElementsQuestionsOptionsSelectbox()+"\"  type=\"\" questionNo=\""+quesNo+".\" groupid=\""+groupId+"\" gwid=\""+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsSavegwid()+"\" >");
						clinicalIntakeXML.append("\n\t\t\t<choices>");
						List<ClinicalElementsQuestionsOptions> option=getOptionElements(gwid);
						for(int j=0; j<option.size(); j++){
							ClinicalElementsQuestionsOptions  optionElements= option.get(j);
							String optionName=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsName(), "");
							String optionValue=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsValue(), "");
							String optiongwid=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsGwid(), "");
							String saveGwid=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsSavegwid(), "");
							String selectbox =HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsSelectbox(), "");
							String isActive=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsIsactive(), "");
							String clinicaldatatype=HUtil.Nz(optionElements.getClinicalElements().getClinicalElementsDatatype(), "0");
							String isBoolean=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsIsboolean(), "");
							String elemValue = HUtil.Nz(getPatientClinicalHistory(patientId, isActive, optionValue, saveGwid).getPatientClinicalHistoryValue(),"");
							if(!elemValue.equalsIgnoreCase("")){
								if(!(elemValue.equalsIgnoreCase("true") || elemValue.equalsIgnoreCase("false"))){
									optionValue=elemValue;
									isActive="true";
								}
								else{
									isActive=elemValue;
								}
							}
							clinicalIntakeXML.append("\n\t\t\t\t<choice displayname=\""+optionName+"\" clinicaloptionid=\""+optiongwid+"\" clinicalelementtype=\""+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsDatatype()+"\" selectbox=\""+selectbox+"\"  clinicaloptionvalue=\""+optionValue+"\" isactive=\""+isActive+"\" optionid=\"\" count=\""+count+"\" optionsize=\""+option.size()+"\" gwid=\""+saveGwid+"\" clinicaldatatype=\""+clinicaldatatype+"\" isboolean=\""+isBoolean+"\"></choice>");
							count++;
						}
						clinicalIntakeXML.append("\n\t\t\t</choices>");
						clinicalIntakeXML.append("\n\t\t</caption>");
						clinicalIntakeXML.append("\n\t\t\t<clinicalelementtype>"+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsDatatype()+"</clinicalelementtype>");
					}
					clinicalIntakeXML.append("\n\t</question>");
					clinicalIntakeXML.append("\n</questionnaire>");
				}else{
					for(int i=0; i<clinicalQuestions.size(); i++){
						int quesNo=i+add+1; 
						GroupQuestionsMapping question = clinicalQuestions.get(i);
						int count=1;

						if(question.getClinialELementsQuestionMapping()==null||question.getClinialELementsQuestionMapping().getClinicalElementsQuestionsGwid()==null)
							break;

						String gwid = HUtil.Nz(question.getClinialELementsQuestionMapping().getClinicalElementsQuestionsGwid(),"");

						System.out.println("gwid::::::2222222222222:::::::::"+gwid);
						boolean isDrawn=false; 
						for(int k=0;k<patElemGwid.size();k++){
							String patGwid=patElemGwid.get(k);	
							System.out.println(gwid+":::::::::"+patGwid);
							if(patGwid!=null){
								if(gwid.equalsIgnoreCase(patGwid)){
									clinicalIntakeXML.append("\n\t\t<caption displayname=\""+patientElementsBeanMap.get(patGwid).getPatientQuestionCaption()+"\" clinicalintakeid=\""+patientElementsBeanMap.get(patGwid).getPatientQuestionGwid()+"\" noofquestions=\""+totalNoOfQuestions+"\" noofpages=\""+totalNoofPages+"\" pageno=\""+pageNo+"\" selectbox=\""+this.getClinicalIntakeElements(patGwid).getClinicalElementsQuestionsOptions().get(0).getClinicalElementsQuestionsOptionsSelectbox()+"\"  type=\"\" questionNo=\""+quesNo+".\" groupid=\""+groupId+"\" gwid=\""+patientElementsBeanMap.get(patGwid).getPatientQuestionSaveGwid()+"\">");
									clinicalIntakeXML.append("\n\t\t\t<choices>");
									int option=Integer.parseInt(HUtil.Nz(getPatientElementsBeanMap(patGwid).getPatientOptionLength(),"0"));
									System.out.println("getPatientElementsBeanMap(patGwid).getPatientOptionLength():::::::::::"+getPatientElementsBeanMap(patGwid).getPatientOptionLength());
									for(int j=0; j<option; j++){
										String optiongwidKey=getPatientElementsBeanMap(patGwid).getPatientQuestionGwid()+"_"+j;
										String optGwid=HUtil.Nz(getPatientOptionElementsBeanMap(optiongwidKey).getPatientOptionGwid(),"");
										String optsaveGwid=HUtil.Nz(getPatientOptionElementsBeanMap(optiongwidKey).getPatientOptionSaveGwid(),"");
										String optionName=HUtil.Nz(getPatientOptionElementsBeanMap(optiongwidKey).getPatientOptionName(), "");
										String optionValue=HUtil.Nz(getPatientOptionElementsBeanMap(optiongwidKey).getPatientOptionValue(), "");
										String isActive=HUtil.Nz(getPatientOptionElementsBeanMap(optiongwidKey).getPatientOptValue(), "");
										String clinicaldatatype=HUtil.Nz(getPatientOptionElementsBeanMap(optiongwidKey).getPatientClinicalElementsType(), "");
										/*String elemValue = HUtil.Nz(getPatientClinicalHistory(patientId, isActive, optionValue, optsaveGwid).getPatientClinicalHistoryValue(),"");
										if(!elemValue.equalsIgnoreCase("")){
											if(!(elemValue.equalsIgnoreCase("true") || elemValue.equalsIgnoreCase("false"))){
												optionValue=elemValue;
												isActive="true";
											}else{
												isActive=elemValue;
											}
										}*/
										String optionType=HUtil.Nz(this.getPatientOptionElementsBeanMap(optiongwidKey).getPatientOptionType(), "");
										String selectbox =HUtil.Nz(this.getPatientOptionElementsBeanMap(optiongwidKey).getSelectBox(), "");
										Boolean isBoolean = this.getPatientOptionElementsBeanMap(optiongwidKey).getPatientElemIsBoolean();
										clinicalIntakeXML.append("\n\t\t\t\t<choice displayname=\""+optionName+"\" clinicaloptionid=\""+optGwid+"\" clinicalelementtype=\""+optionType+"\" selectbox=\""+selectbox+"\"  clinicaloptionvalue=\""+optionValue+"\" isactive=\""+isActive+"\" optionid=\"\" count=\""+count+"\" optionsize=\""+option+"\" gwid=\""+optsaveGwid+"\" clinicaldatatype=\""+clinicaldatatype+"\" isboolean=\""+isBoolean+"\"></choice>");
										count++;
									}
									clinicalIntakeXML.append("\n\t\t\t</choices>");
									clinicalIntakeXML.append("\n\t\t</caption>");
									clinicalIntakeXML.append("\n\t\t\t<clinicalelementtype>"+getClinicalIntakeElements(patGwid).getClinicalElementsQuestionsDatatype()+"</clinicalelementtype>");
									isDrawn=true;
									break;
								}
							}
						}if(!isDrawn){
							clinicalIntakeXML.append("\n\t\t<caption displayname=\""+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsDatatype()+"\" clinicalintakeid=\""+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsGwid()+"\" noofquestions=\""+totalNoOfQuestions+"\" noofpages=\""+totalNoofPages+"\" pageno=\""+pageNo+"\" selectbox=\""+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsOptions().get(0).getClinicalElementsQuestionsOptionsSelectbox()+"\"  type=\"\" questionNo=\""+quesNo+".\" groupid=\""+groupId+"\" gwid=\""+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsSavegwid()+"\" >");
							clinicalIntakeXML.append("\n\t\t\t<choices>");
							List<ClinicalElementsQuestionsOptions> option=getOptionElements(gwid);
							for(int j=0; j<option.size(); j++){
								ClinicalElementsQuestionsOptions  optionElements= option.get(j);
								String optionName=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsName(), "");
								String optionValue=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsValue(), "");
								String optiongwid=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsGwid(), "");
								String saveGwid=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsSavegwid(), "");
								String selectbox =HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsSelectbox(), "");
								String isActive=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsIsactive(), "");
								String clinicaldatatype=HUtil.Nz(optionElements.getClinicalElements().getClinicalElementsDatatype(), "0");
								String isBoolean=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsIsboolean(), "");
								String elemValue = HUtil.Nz(getPatientClinicalHistory(patientId, isActive, optionValue, saveGwid).getPatientClinicalHistoryValue(),"");
								if(!elemValue.equalsIgnoreCase("")){
									if(!(elemValue.equalsIgnoreCase("true") || elemValue.equalsIgnoreCase("false"))){
										optionValue=elemValue;
										isActive="true";
									}
									else{
										isActive=elemValue;
									}}
								clinicalIntakeXML.append("\n\t\t\t\t<choice displayname=\""+optionName+"\" clinicaloptionid=\""+optiongwid+"\" clinicalelementtype=\""+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsDatatype()+"\" selectbox=\""+selectbox+"\"  clinicaloptionvalue=\""+optionValue+"\" isactive=\""+isActive+"\" optionid=\"\" count=\""+count+"\" optionsize=\""+option.size()+"\" gwid=\""+saveGwid+"\" clinicaldatatype=\""+clinicaldatatype+"\" isboolean=\""+isBoolean+"\" ></choice>");
								count++;
							}
							clinicalIntakeXML.append("\n\t\t\t</choices>");
							clinicalIntakeXML.append("\n\t\t</caption>");
							clinicalIntakeXML.append("\n\t\t\t<clinicalelementtype>"+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsDatatype()+"</clinicalelementtype>");
						}else{
							isDrawn=false;
						}
					}
					clinicalIntakeXML.append("\n\t</question>");
					clinicalIntakeXML.append("\n</questionnaire>");
				}
			}catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}else{
			clinicalIntakeXML=getClinicalIntakeXml(patientId,requestId,mode,groupId,groupName,limit,pageNo);
		}

		ClinicalIntakeXMLBean xmlBean=new ClinicalIntakeXMLBean();

		xmlBean.setPatientId(patientId);
		xmlBean.setIntakeFormGroupId(groupId);
		xmlBean.setXmlData(XMLTransformationFactory(clinicalIntakeXML,"portal/ClinicalIntake.xsl", httpServletRequest).toString());
		xmlBean.setIntakeFormGroupName(groupName);
		
		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Requested clinical intake forms",-1,
				httpServletRequest.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with Id"+patientId+"requested clinical intake forms","");
		
		return xmlBean;

	}

	public PatientClinicalElementsQuestions getPatientClinicalElementsQuestionsData(int patientId,int requestId){

		PatientClinicalElementsQuestions clinicalQuestionSData=patientClinicalElementsQuestionsRepository.findOne(PortalFormsSpecification.getPatientClinicalELementsQuestionsData(patientId, requestId));

		return clinicalQuestionSData;
	}

	public Hashtable<Integer,String> setPatientElementsBean(String childXml)throws Exception{/*
		patientElementsBeanMap=new LinkedHashMap<String, PatientClinicalIntakeBean>();
		patientOptionElementsBeanMap=new LinkedHashMap<String, PatientClinicalIntakeBean>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document currentDoc = dBuilder.parse(new InputSource(new java.io.StringReader(childXml)));
		Hashtable<Integer,String> patientElemGwid=new Hashtable<Integer,String>();
		try{
			NodeList c=(NodeList)currentDoc.getElementsByTagName("caption");
			for(int i=0; i<c.getLength(); i++){
				PatientClinicalIntakeBean patientElementBean=new PatientClinicalIntakeBean();
				Node currentElem = c.item(i);
				String PatientElementsQuestionGwid=HUtil.Nz(currentElem.getAttributes().getNamedItem("clinicalquestionid").getNodeValue(),"");
				String cGwid=currentElem.getAttributes().getNamedItem("clinicalquestionid").getNodeValue();
				String savegwid=currentElem.getAttributes().getNamedItem("gwid").getNodeValue();
				String qCaption=currentElem.getAttributes().getNamedItem("displayname").getNodeValue();
				int cOptionLen=currentElem.getChildNodes().item(0).getChildNodes().getLength();
				patientElementBean.setPatientQuestionGwid(HUtil.Nz(cGwid,""));
				patientElementBean.setPatientQuestionSaveGwid(HUtil.Nz(savegwid,""));
				System.out.println("PatientElementsQuestionGwid::::::::::"+PatientElementsQuestionGwid+":::cGwid:::"+cGwid+"::::savegwid:::::"+savegwid+"::::qCaption:::"+qCaption+"::::cOptionLen:::"+cOptionLen);
				patientElemGwid.put(i, PatientElementsQuestionGwid);
				patientElementBean.setPatientQuestionCaption(HUtil.Nz(qCaption,""));
				patientElementBean.setPatientOptionLength(HUtil.Nz(cOptionLen,""));
				this.setPatientElementsBeanMap(PatientElementsQuestionGwid, patientElementBean);
				for(int n=0;n<cOptionLen;n++){
					PatientClinicalIntakeBean patientOptionBean=new PatientClinicalIntakeBean();
					String coptionName=currentElem.getChildNodes().item(0).getChildNodes().item(n).getAttributes().getNamedItem("displayname").getNodeValue();
					String coptionValue=currentElem.getChildNodes().item(0).getChildNodes().item(n).getAttributes().getNamedItem("clinicaloptionvalue").getNodeValue();
					String coptiongwid=currentElem.getChildNodes().item(0).getChildNodes().item(n).getAttributes().getNamedItem("clinicaloptionid").getNodeValue();
					String coptionsavegwid=currentElem.getChildNodes().item(0).getChildNodes().item(n).getAttributes().getNamedItem("gwid").getNodeValue();
					String selectbox =currentElem.getChildNodes().item(0).getChildNodes().item(n).getAttributes().getNamedItem("selectbox").getNodeValue();
					String optiontype =currentElem.getChildNodes().item(0).getChildNodes().item(n).getAttributes().getNamedItem("clinicalelementtype").getNodeValue();
					String isActive=currentElem.getChildNodes().item(0).getChildNodes().item(n).getAttributes().getNamedItem("isactive").getNodeValue();
					String datatype=currentElem.getChildNodes().item(0).getChildNodes().item(n).getAttributes().getNamedItem("clinicaldatatype").getNodeValue();
					String isboolean=currentElem.getChildNodes().item(0).getChildNodes().item(n).getAttributes().getNamedItem("isboolean").getNodeValue();
					String PatientElementsOptionGwid=currentElem.getChildNodes().item(0).getChildNodes().item(n).getAttributes().getNamedItem("clinicaloptionid").getNodeValue()+"_"+n;
					patientOptionBean.setPatientOptionGwid(HUtil.Nz(coptiongwid,""));
					patientOptionBean.setPatientOptionSaveGwid(HUtil.Nz(coptionsavegwid,""));
					patientOptionBean.setPatientOptionName(HUtil.Nz(coptionName,""));
					patientOptionBean.setPatientOptionValue(HUtil.Nz(coptionValue,""));
					patientOptionBean.setPatientOptValue(HUtil.Nz(isActive,""));
					patientOptionBean.setPatientOptionType(HUtil.Nz(optiontype,""));
					patientOptionBean.setSelectBox(HUtil.Nz(selectbox,""));
					patientOptionBean.setPatientElemIsBoolean(Boolean.parseBoolean(HUtil.Nz(isboolean,"false")));
					patientOptionBean.setPatientClinicalElementsType(HUtil.Nz(datatype,"0"));
					System.out.println("coptionName::::::::::"+coptionName+":::coptionValue:::"+coptionValue+":::coptiongwid:::"+coptiongwid+":::coptionsavegwid:::"+coptionsavegwid+":::selectbox:::"+selectbox+":::optiontype:::"+optiontype);
					this.setPatientOptionElementsBeanMap(PatientElementsOptionGwid, patientOptionBean);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();	
		}
		return patientElemGwid;
	 */
		return null;}

	public void setPatientElementsBeanMap(String PatientElementsQuestionGwid, PatientClinicalIntakeBean patientElementBean) {
		this.patientElementsBeanMap.put(PatientElementsQuestionGwid, patientElementBean);
	}

	public void setPatientOptionElementsBeanMap(String PatientElementsOptionGwid, PatientClinicalIntakeBean patientOptionBean) {
		this.patientOptionElementsBeanMap.put(PatientElementsOptionGwid, patientOptionBean);
	}

	public String getGwidQury(int pageOffset,int pageIndex,int groupId,boolean isCount)throws Exception{

		if(!isCount){
			List<GroupQuestionsMapping> clinicalElementsQuestionsList=groupQuestionsMappingRepository.findAll(PortalFormsSpecification.getPatientClinicalELementsQuestions(groupId));
		}else if(isCount){
			Long count=groupQuestionsMappingRepository.count(PortalFormsSpecification.getPatientClinicalELementsQuestions(groupId));	
		}

		StringBuilder query = new StringBuilder();
		query.append("select distinct clinical_elements_questions_gwid,clinical_elements_questions_caption from clinical_elements_questions ");
		query.append("left join clinical_questions_group on clinical_questions_group_id="+groupId);
		query.append("left join group_questions_mapping on clinical_elements_questions_gwid=group_questions_mapping_questions_id where group_questions_mapping_group_id=clinical_questions_group_id order by 1");
		if(!isCount){
			query.append(" offset "+ (pageOffset * pageIndex))
			.append(" limit "+pageOffset);
		}else{
			String countQury = " select count(*) as totalcount from ("+query.toString()+")t";
			query=new StringBuilder();
			query.append(countQury);
		}
		return query.toString(); 
	}


	public List<GroupQuestionsMapping> getClinicalQuestions(int pageOffset,int pageIndex,int groupId){

		List<GroupQuestionsMapping> clinicalElementsQuestionsList=groupQuestionsMappingRepository.findAll(PortalFormsSpecification.getPatientClinicalELementsQuestions(groupId));

		return clinicalElementsQuestionsList;
	}

	public long getTotalClinicalQuestions(int pageOffset,int pageIndex,int groupId){

		Long count=groupQuestionsMappingRepository.count(PortalFormsSpecification.getPatientClinicalELementsQuestions(groupId));

		return count;
	}

	public List<ClinicalElementsQuestionsOptions> getOptionElements(String gwid)throws Exception{


		List<ClinicalElementsQuestionsOptions> clinicalElementsQuestionsOptionsList=clinicalElementsQuestionsOptionsRepository.findAll(PortalFormsSpecification.getOptionElements(gwid));

		return clinicalElementsQuestionsOptionsList;

		/*StringBuffer query = new StringBuffer();
		query.append("select distinct clinical_elements_questions_options_gwid as optiongwid,clinical_elements_questions_options_savegwid as savegwid, ");
		query.append("clinical_elements_questions_options_value as optionvalue, ");
		query.append("clinical_elements_questions_options_name as optionname, ");
		query.append("clinical_elements_questions_options_selectbox as selectbox,clinical_elements_questions_options_isactive as isactive, coalesce(clinical_elements_datatype,0) as clinicaldatatype,clinical_elements_questions_options_isboolean as isboolean ");
		query.append("from clinical_elements_questions_options " +
					"left join clinical_elements_options on clinical_elements_questions_options_gwid=clinical_elements_options_gwid " +
					"and clinical_elements_options_value=clinical_elements_questions_options_value "+
					"left join clinical_elements on clinical_elements_questions_options_savegwid = clinical_elements_gwid " +
					"where clinical_elements_questions_options_gwid ilike '"+gwid+"' order by selectbox desc ");
		return query.toString();*/
	}

	public PatientClinicalIntakeBean getPatientElementsBeanMap(String PatientElementsQuestionGwid) {
		return patientElementsBeanMap.get(PatientElementsQuestionGwid);
	}
	public PatientClinicalIntakeBean getPatientOptionElementsBeanMap(String PatientElementsOptionGwid) {
		return patientOptionElementsBeanMap.get(PatientElementsOptionGwid);
	}

	public PatientClinicalHistory getPatientClinicalHistory(int patientId, String isActive, String optionValue, String optsaveGwid){

		PatientClinicalHistory patientClinicalHistory=patientClinicalHistoryRepository.findOne(PortalFormsSpecification.getPatientClinicalHistory(patientId,isActive,optionValue,optsaveGwid));

		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Requested patient's clinical history with patientId:"+patientId,-1,
				httpServletRequest.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Requested patient's clinical history with patientId:"+patientId,"");
		
		return patientClinicalHistory;
	}

	public StringBuffer getClinicalIntakeXml(int patientId,int requestId,int mode,int groupId,String groupName,int limit,int pageNo) throws Exception{
		StringBuffer clinicalIntakeXML = new StringBuffer("");
		try{
			int totalNoOfQuestions=(int) getTotalClinicalQuestions(limit, pageNo, groupId);
			List<GroupQuestionsMapping> clinicalQuestions=getClinicalQuestions(limit, pageNo, groupId);
			int add=pageNo*limit;

			int totalNoofPages = (totalNoOfQuestions % limit == 0) ?  (totalNoOfQuestions / limit) : ((totalNoOfQuestions / limit) +1);
			clinicalIntakeXML.append("\n\t<questionnaire>");
			clinicalIntakeXML.append("\n\t<question>");
			clinicalIntakeXML.append("\n\t<header><patientid value=\""+patientId+"\">"+patientId+"</patientid>\n<requestid value=\""+requestId+"\">"+requestId+"</requestid>\n<mode value=\""+mode+"\">"+mode+"</mode><groupId value=\""+groupId+"\">"+groupId+"</groupId><groupName value=\""+groupName+"\">"+groupName+"</groupName></header>");
			clinicalIntakeXML.append("\n\t\t<tnoofquestions value=\""+totalNoOfQuestions+"\">"+totalNoOfQuestions+"</tnoofquestions>");
			clinicalIntakeXML.append("\n\t\t<noofpages value=\""+totalNoofPages+"\">"+totalNoofPages+"</noofpages>");
			clinicalIntakeXML.append("\n\t\t<pageno value=\""+pageNo+"\">"+pageNo+"</pageno>");
			for(int i=0; i<clinicalQuestions.size(); i++){
				int quesNo=i+add+1; 
				GroupQuestionsMapping question = clinicalQuestions.get(i);
				int count=1;
				String gwid = HUtil.Nz(question.getClinialELementsQuestionMapping().getClinicalElementsQuestionsGwid(),"");
				//String isActive = HUtil.Nz(gwidHash.get("clinical_elements_options_active"),"");
				clinicalIntakeXML.append("\n\t\t<caption displayname=\""+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsCaption()+"\" clinicalintakeid=\""+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsGwid()+"\" noofquestions=\""+totalNoOfQuestions+"\" noofpages=\""+totalNoofPages+"\" pageno=\""+pageNo+"\" selectbox=\""+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsOptions().get(0).getClinicalElementsQuestionsOptionsSelectbox()+"\"  type=\"\" questionNo=\""+quesNo+".\" groupid=\""+groupId+"\" gwid=\""+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsSavegwid()+"\">");
				clinicalIntakeXML.append("\n\t\t\t<choices>");
				List<ClinicalElementsQuestionsOptions> option=getOptionElements(gwid);
				for(int j=0; j<option.size(); j++){
					ClinicalElementsQuestionsOptions  optionElements= option.get(j);
					String optionName=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsName(), "");
					String optionValue=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsValue(), "");
					String optiongwid=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsGwid(), "");
					String saveGwid=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsSavegwid(), "");
					String selectbox =HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsSelectbox(), "");
					String isActive=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsIsactive(), "");
					String clinicaldatatype=HUtil.Nz(optionElements.getClinicalElements().getClinicalElementsDatatype(), "0");
					String isBoolean=HUtil.Nz(optionElements.getClinicalElementsQuestionsOptionsIsboolean(), "");
					String elemValue = HUtil.Nz(getPatientClinicalHistory(patientId, isActive, optionValue, saveGwid).getPatientClinicalHistoryValue(),"");
					if(!elemValue.equalsIgnoreCase("")){
						if(!(elemValue.equalsIgnoreCase("true") || elemValue.equalsIgnoreCase("false"))){
							optionValue=elemValue;
							isActive="true";
						}
						else{
							isActive=elemValue;
						}}
					clinicalIntakeXML.append("\n\t\t\t\t<choice displayname=\""+optionName+"\" clinicaloptionid=\""+optiongwid+"\" clinicalelementtype=\""+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsDatatype()+"\" selectbox=\""+selectbox+"\"  clinicaloptionvalue=\""+optionValue+"\" isactive=\""+isActive+"\" optionid=\"\" count=\""+count+"\" optionsize=\""+option.size()+"\" gwid=\""+saveGwid+"\" clinicaldatatype=\""+clinicaldatatype+"\" isboolean=\""+isBoolean+"\"></choice>");
					count++;
				}
				clinicalIntakeXML.append("\n\t\t\t</choices>");
				clinicalIntakeXML.append("\n\t\t</caption>");
				clinicalIntakeXML.append("\n\t\t\t<clinicalelementtype>"+getClinicalIntakeElements(gwid).getClinicalElementsQuestionsDatatype()+"</clinicalelementtype>");
			}

			clinicalIntakeXML.append("\n\t</question>");
			clinicalIntakeXML.append("\n</questionnaire>");
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
		}
		return clinicalIntakeXML;
	}

	public String XMLTransformationFactory(StringBuffer XMLData,String XSLFile,HttpServletRequest request)throws Exception {
		String xmlText=XMLData.toString();
		StringBuffer HTMLData = new StringBuffer();
		if(!xmlText.equalsIgnoreCase("")){
			try{
				HtmlTransformer transformer = new HtmlTransformer();
				Reader xmlreader = new StringReader(xmlText);
				Source xml = new StreamSource(xmlreader);
				transformer.setOutputMethod("html");
				transformer.setRequest(request);
				transformer.setXmlIn(xml);
				transformer.setXslData(XSLFile);
				//System.out.println("xmlIn:::::::"+transformer.getXmlIn());
				//System.out.println("xslData:::::::"+transformer.getXslData());
				HTMLData = new StringBuffer(transformer.transform());
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			HTMLData.append("");
		}
		/*
		File file1=new File("f:\\myHTML.htm");
		BufferedWriter writer=new BufferedWriter(new FileWriter(file1));
		writer.write(HTMLData.toString());
		writer.close();
		 */
		return HTMLData.toString();
	}

	public int getLeafRequestId(int patientId, int tabId){

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<PatientClinicalElementsQuestions> root = cq.from(PatientClinicalElementsQuestions.class);

		cq.select(builder.max(root.get(PatientClinicalElementsQuestions_.patientClinicalElementsQuestionsId)));
		cq.where(builder.equal(root.get(PatientClinicalElementsQuestions_.patientClinicalElementsQuestionsPatientid), patientId),
				builder.equal(root.get(PatientClinicalElementsQuestions_.patientClinicalElementsQuestionsTabid), tabId));


		List<Object> planOfCareResultList=em.createQuery(cq).getResultList();
		int requestId=0;
		for(int i=0;i<planOfCareResultList.size();i++){
			requestId=(int) planOfCareResultList.get(i);
		}

		return requestId;

	}

	public int getRequestId(){




		return 0;
	}





	/*===========================================================================================================================================================================================================*/




	@Override
	public ClinicalIntakeXMLBean getXMLIntakeForm(int isXML, int retXML, int patientId, int requestId, int tabId, int chartID, int groupId, String groupName) {/*

		 CompletePatientDetailsBean PatientModel = getPatientDetails(patientId);

		 TabLibrary tabLibrary=getXMLTabData(tabId);

		 int age=-1;

		 if(tabLibrary.getTabLibraryXmlGroup()==TabConstants.AGE_BASED_ON_MONTH)
			 age=getAgeinMonth(PatientModel.getPatientRegistrationDob());
		 else if(tabLibrary.getTabLibraryXmlGroup()==TabConstants.AGE_BASED_ON_MONTH)
		     age=getAgeinYear(PatientModel.getPatientRegistrationDob());

		 LeafXmlVersion leafXmlVersion=getLeafXMLDetails(tabId, PatientModel.getPatientRegistrationSex(), age, tabLibrary.getTabLibraryXmlGroup());

		 TabDetailBean tabBean = TabDetailBean.getNewInstance(tabId ,PatientModel, tabLibrary, leafXmlVersion);


		 TabDetailModel tabDetailModel= new TabDetailModel();
		 tabDetailModel.setChartId(chartID);
		 tabDetailModel.setPatientId(patientId);
		 tabDetailModel.setCallType(TabConstants.SCREEN);
		 tabDetailModel.setPcareImageOCXVersion(httpServletRequest.getServletContext().getInitParameter("PcareImageOCXVersion"));
		 tabDetailModel.setRealPath(httpServletRequest.getServletContext().getRealPath(""));
		 Vector tabDetail = tabDetailModel.getPortalTabDetails(tabBean,tabId,PatientModel,requestId);

		    String xmlFileName = new String();
			String xslFileName = new String();
			String xmlData = new String();
			String xmlDataPortal = new String();
			String originalXMLData = new String();
			Vector tabData = new Vector();
			XMLGenerator generator;
			int tabIndex = -1;
			String gwidData="";
			try{			

				PatientClinicalElementsQuestions clinicalQuestions=	getPatientClinicalElementsQuestionsData(patientId, requestId);
				tabIndex=clinicalQuestions.getPatientClinicalElementsQuestionsId();
				xmlDataPortal=clinicalQuestions.getPatientClinicalElementsQuestionsData();

				if(xmlDataPortal.indexOf("@#####@")!=-1){
				gwidData=xmlDataPortal.substring(xmlDataPortal.indexOf("@#####@"),xmlDataPortal.length());
				xmlData=xmlDataPortal.substring(0,xmlDataPortal.indexOf("@#####@"));
				}

				originalXMLData = xmlData;
				generator = new XMLGenerator(request,request.getSession(false).getServletContext().getInitParameter("PcareImageOCXVersion"),patientModel);

				if(System.getProperty("os.name").toLowerCase().indexOf("windows")>=0)
					ENCODING="iso-8859-1";
				else if(System.getProperty("os.name").toLowerCase().indexOf("linux")>=0)
					ENCODING="UTF-8";			

				generator.setEncoding(this.ENCODING);

				xmlFileName	= tabBean.getXmlURL();


				//if(this.getCallType()==TabConstants.SCREEN)
					xslFileName	=	tabBean.getXslURL();
				else if(this.getCallType()==TabConstants.PRINTING)
					xslFileName	=	tabBean.getPrintingURL();			

				if (xmlData.equalsIgnoreCase("")) {	
					FileReader freader = new FileReader(xmlFileName);
					BufferedReader breader	=	new BufferedReader(freader);
					String temp = "";
					StringBuffer tempXMLData = new StringBuffer();
					while(temp!=null){
						tempXMLData.append(temp.trim());
						temp=breader.readLine();
					}		
					xmlData = tempXMLData.toString();
					StringBuffer tempxml = new StringBuffer();
					for(int i=0;i<xmlData.length();i++){
						if(Character.getType(xmlData.charAt(i))!=15){
							tempxml.append(xmlData.charAt(i));
						}
					}
					xmlData=tempxml.toString();
					xmlData="<?xml version=\"1.0\"?><!DOCTYPE leaf SYSTEM \"file:///"	+ this.getRealPath()  + "/jsp/chart/LeafTool/LeafDTD.dtd\">"+xmlData.substring(xmlData.indexOf("<leaf"),xmlData.length());
					Pattern p	=	Pattern.compile("[\n\t\r]");
					Matcher m	=	p.matcher("");
					m.reset(xmlData);
					xmlData	=	m.replaceAll("");
					xmlData = generator.dbParsing(xmlData,dbUtils);
					Hashtable tab = new Hashtable();
					tab.put("tabdesc",tabBean.getDescription());
					tab.put("xmldata",xmlData);
					tab.put("tabdataindex",tabIndex);
					tab.put("xslurl",xslFileName);
					tabData.add(tab);
				}
				else{		
					StringBuffer tempxml = new StringBuffer();
					for(int i=0;i<xmlData.length();i++){
						if(Character.getType(xmlData.charAt(i))!=15){
							tempxml.append(xmlData.charAt(i));
						}
					}
					xmlData=tempxml.toString();	
					String dataversion=generator.getVersion("<?xml version=\"1.0\"?>"+xmlData);
					if(dataversion.equalsIgnoreCase(tabBean.getVersionName())){
						xmlFileName = tabBean.getXmlURL();
					}
					else{
						xmlFileName = this.getRealPath() + dbUtils.tableLookUp("leaf_xml_version_xmlurl","leaf_xml_version","leaf_xml_version_version ilike '"	+	dataversion	+	"'");
					}
					xmlData = generator.generateXML("<?xml version=\"1.0\"?>"+xmlData,xmlFileName,dbUtils);
					Hashtable tab = new Hashtable();
					tab.put("tabdesc",tabBean.getDescription());
					tab.put("xmldata",xmlData);
					tab.put("tabdataindex",tabIndex);
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					InputSource source = new InputSource(new ByteArrayInputStream(originalXMLData.getBytes()));
					Document doc = builder.parse(source);
					NodeList nlist = doc.getDocumentElement().getChildNodes();
					for(int i=0;i<nlist.getLength();i++){
						if(nlist.item(i).getFirstChild()!=null){
							GlaceOutLoggingStream.console(nlist.item(i).getNodeName() + "=" + nlist.item(i).getFirstChild().getNodeValue());
						}
					}




					tab.put("originalxmldata",originalXMLData);
					tab.put("xslurl",xslFileName);
					tabData.add(tab);
					GlaceOutLoggingStream.console("\n\n--->Coming1"); 
				}
			}catch(Exception e){
				e.printStackTrace();
				//throw new Exception("Exception thrown in the function getTabDetails -->  "	+	e.getMessage());
			}
			if(xmlDataPortal.indexOf("@#####@")!=-1){
			tabData.add(gwidData);
			}


		return null;
	 */ return null;}

	public String dbParsing(String xmldata)throws Exception{/*

		try{
			xmldata="<?xml version=\"1.0\"?><!DOCTYPE leaf SYSTEM \"file:///"	+ request.getSession().getServletContext().getRealPath("")  + "/jsp/chart/LeafTool/LeafDTD.dtd\">"+xmldata.substring(xmldata.indexOf("<leaf"),xmldata.length());
			InputSource is = new InputSource();
            is.setByteStream(new ByteArrayInputStream(xmldata.getBytes()));
            is.setEncoding(ENCODING);

			DocumentBuilderFactory docBuilderFactory	=	DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder	=	docBuilderFactory.newDocumentBuilder();
            Document dbdata =       docBuilder.parse(is);

			NodeList ndlist	=	dbdata.getElementsByTagName("leaf");
			Node nd	= ndlist.item(0);
			int isdbneeded=0;
			if(nd.getAttributes().getNamedItem("isdbparsingneeded")!=null)
				isdbneeded	=	Integer.parseInt(HUtil.Nz(nd.getAttributes().getNamedItem("isdbparsingneeded").getNodeValue(),"0"));
			if(isdbneeded==1){
				dbdata	=	formOCXDATA(dbdata,PcareImageOCXVersion);
				xmldata="<?xml version=\"1.0\"?>" + formFullXML(dbdata,dbUtils);
			}
			else{
				dbdata	=	formOCXDATA(dbdata,PcareImageOCXVersion);
				xmldata="<?xml version=\"1.0\"?>" + dbdata.getElementsByTagName("leaf").item(0).toString();
			}
		}catch(Exception e){
			//GlaceOutLoggingStream.console( "--->exception---<>"+e+"--"+e.getStackTrace());
			e.printStackTrace();
			throw e;
		}
		return xmldata;
	 */ return null;}

	private Document formOCXDATA(Document dbData,String PcareImageOCXVersion){/*
		NodeList datalist	=	dbData.getElementsByTagName("element");
		for(int i=0;i<datalist.getLength();i++){
			Node nd	=	datalist.item(i);
			if(nd.getAttributes().getNamedItem("type").getNodeValue().equals("ocx") || nd.getAttributes().getNamedItem("type").getNodeValue().equals("flash")){
				if(nd.getAttributes().getNamedItem("bkgndimage")!=null){
					if(!nd.getAttributes().getNamedItem("bkgndimage").getNodeValue().trim().equals(""))
						nd.getAttributes().getNamedItem("bkgndimage").setNodeValue(request.getScheme()+"://"	+	request.getServerName()	+	":"	+	request.getServerPort()	+	request.getContextPath()	+	nd.getAttributes().getNamedItem("bkgndimage").getNodeValue());
				}
				if(nd.getAttributes().getNamedItem("version")!=null){
					nd.getAttributes().getNamedItem("version").setNodeValue("../../../includes/activex/PCareImage.cab#Version="+PcareImageOCXVersion);
				}
				if(nd.getAttributes().getNamedItem("serverpath")!=null){
					nd.getAttributes().getNamedItem("serverpath").setNodeValue(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath());
				}
			}
		}
		return dbData;
	 */return null;}

	private String formFullXML(Document dbdata)throws Exception{/*
		NodeList datalist	=	dbdata.getElementsByTagName("element");
		NodeList nodeListTitle=dbdata.getElementsByTagName("title");
		String isexclude="";
		for(int j=0;j<nodeListTitle.getLength();j++){
			Node node	=	nodeListTitle.item(j);
			isexclude=HUtil.Nz(httpServletRequest.getAttribute("isexculedneeded"),"");
			if(!isexclude.equals("1") && node.getAttributes().getNamedItem("heading")!=null){
				node.getFirstChild().setNodeValue("");
			}
		}
		int dbparsing=0;
		int fromrequest=0;
		for(int i=0;i<datalist.getLength();i++){
			Node node	=	datalist.item(i);
			if(!node.getNodeName().equals("#text")){
				dbparsing=0;
				fromrequest=0;
				if(node.getAttributes().getNamedItem("dbparsing")!=null)
					dbparsing	=	Integer.parseInt(HUtil.Nz(node.getAttributes().getNamedItem("dbparsing").getNodeValue(),"-1"));
				if(node.getAttributes().getNamedItem("fromrequest")!=null)
					fromrequest	=	Integer.parseInt(HUtil.Nz(node.getAttributes().getNamedItem("fromrequest").getNodeValue(),"-1"));
				if(fromrequest==1){
					if(datalist.item(i).getAttributes()!=null){
						if(datalist.item(i).getAttributes().getNamedItem("id")!=null){
							String id	=	HUtil.Nz(datalist.item(i).getAttributes().getNamedItem("id").getNodeValue(),"-1");
							if(dbdata.getElementById(id).getFirstChild()!=null){
								dbdata.getElementById(id).getFirstChild().setNodeValue(cb (dbdata,id,dbUtils));
							}
							else
								dbdata.getElementById(id).appendChild(dbdata.createTextNode(processNode(dbdata,id,dbUtils)));
						}
					}
				}
				if(dbparsing==1){
					if(datalist.item(i).getAttributes()!=null){
						if(datalist.item(i).getAttributes().getNamedItem("id")!=null){
							String id	=	HUtil.Nz(datalist.item(i).getAttributes().getNamedItem("id").getNodeValue(),"-1");
							if(dbdata.getElementById(id)!=null){
								if(datalist.item(i).getAttributes().getNamedItem("isexculedneeded")!=null && isexclude.equals("1"))    {
									dbdata.getElementById(id).getFirstChild().setNodeValue(""); 
		                         }	
								else if(dbdata.getElementById(id).getFirstChild() != null){
									dbdata.getElementById(id).getFirstChild().setNodeValue(processNode(dbdata,id,dbUtils));
								}
							}
						}
					}
				}

			}
		}
		datalist	=	dbdata.getElementsByTagName("select");
		for(int i=0;i<datalist.getLength();i++){
			Node node	=	datalist.item(i);
			if(!node.getNodeName().equals("#text")){
				dbparsing=0;
				if(node.getAttributes().getNamedItem("dbparsing")!=null)
					dbparsing	=	Integer.parseInt(HUtil.Nz(node.getAttributes().getNamedItem("dbparsing").getNodeValue(),"-1"));
				if(datalist.item(i).getAttributes()!=null && datalist.item(i).getAttributes().getNamedItem("listquery")!=null){
					String id	=	HUtil.Nz(datalist.item(i).getAttributes().getNamedItem("id").getNodeValue(),"-1");
					if(!datalist.item(i).getAttributes().getNamedItem("listquery").getNodeValue().trim().equals(""))
						dbdata.getElementById(id).setAttribute("option",getOptionValue(datalist.item(i).getAttributes().getNamedItem("listquery").getNodeValue(),dbUtils));
				}
				if(dbparsing==1){
					if(datalist.item(i).getAttributes()!=null && datalist.item(i).getAttributes().getNamedItem("id")!=null){
						String id	=	HUtil.Nz(datalist.item(i).getAttributes().getNamedItem("id").getNodeValue(),"-1");
						if(dbdata.getElementById(id)!=null && dbdata.getElementById(id).getFirstChild() != null){
							Hashtable temp	=	processSelectNode(dbdata,id,dbUtils);
							dbdata.getElementById(id).getFirstChild().setNodeValue(temp.get("value").toString());
						}
					}
				}
			}
		}
		return dbdata.getElementsByTagName("leaf").item(0).toString();
	 */ return null;}


	public CompletePatientDetailsBean getPatientDetails(int patientId){

		PatientProfileSettingsFields settingFields= portalSettingsService.getPatientProfileSettingsFieldsList();

		PatientRegistration patientReg=portalMedicalSummaryService.getPatientPersonalDetails(patientId);

		CompletePatientDetailsBean patientDetailsBean=new CompletePatientDetailsBean();

		/*Extracting DOB*/

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		String patientDob=dateFormat.format(patientReg.getPatientRegistrationDob());


		/*Extracting Patient Gender*/
		int patientGenderValue=patientReg.getPatientRegistrationSex();
		String patientGender;
		if(patientGenderValue==1)
			patientGender="Male";
		else if(patientGenderValue==2)
			patientGender="Female";
		else
			patientGender="--";


		/*Extracting Marital Status*/
		String patientMaritialStatus="--";

		for(int i=0;i<settingFields.getAvailabeMaritalStatusOptions().size();i++){
			if(patientReg.getPatientRegistrationMaritalstatus().equals((int)settingFields.getAvailabeMaritalStatusOptions().get(i).getBlookIntid()))
				patientMaritialStatus=settingFields.getAvailabeMaritalStatusOptions().get(i).getBlookName();
		}


		/*Extracting Race*/
		String race="--";

		for(int i=0;i<settingFields.getAvailabeRaceOptions().size();i++){
			if(settingFields.getAvailabeRaceOptions().get(i).getBlookIntid().equals(Integer.parseInt(patientReg.getPatientRegistrationRace())))
				race=settingFields.getAvailabeRaceOptions().get(i).getBlookName();
		}

		/*Extracting Ethnicity*/
		String patientEthnicity="--";

		for(int i=0;i<settingFields.getAvailabeEthnicityOptions().size();i++){
			if(patientReg.getPatientRegistrationEthnicity().equals((short)settingFields.getAvailabeEthnicityOptions().get(i).getBlookIntid()))
				patientEthnicity=settingFields.getAvailabeRaceOptions().get(i).getBlookName();
		}

		/*Extracting Language*/
		String patientLanguage="--";

		for(int i=0;i<settingFields.getAvailabelanguageOptions().size();i++){
			if(settingFields.getAvailabelanguageOptions().get(i).getBlookIntid().equals(Integer.parseInt(patientReg.getPatientRegistrationPreferredLan())))
				patientLanguage=settingFields.getAvailabelanguageOptions().get(i).getBlookName();
		}

		patientDetailsBean.setPatientRegistrationId(patientId);
		patientDetailsBean.setPatientRegistrationFirstName(patientReg.getPatientRegistrationFirstName());
		patientDetailsBean.setPatientRegistrationLastName(patientReg.getPatientRegistrationLastName());
		patientDetailsBean.setPatientRegistrationMidInitial(patientReg.getPatientRegistrationMidInitial());
		patientDetailsBean.setPatientRegistrationDob("");
		patientDetailsBean.setPatientRegistrationSex(patientGender);
		patientDetailsBean.setPatientRegistrationMaritalstatus(patientMaritialStatus);
		patientDetailsBean.setPatientRegistrationRace(race);
		patientDetailsBean.setPatientRegistrationEthnicity(patientEthnicity);
		patientDetailsBean.setPatientRegistrationPreferredLan(patientLanguage);

		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Requested complete patient details with patientId:"+patientId,-1,
				httpServletRequest.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Requested complete patient details with patientId:"+patientId,"");
		
		return patientDetailsBean;

	}



	@Override
	public LeafXmlVersion getLeafXMLDetails(int tabId, String gender, int patientAge, int category) {

		LeafXmlVersion leafXMLVersion=leafXmlVersionRepository.findOne(PortalFormsSpecification.getLeafXMLDetails(tabId, gender, patientAge, category));

		return leafXMLVersion;
	}

	@Override
	public TabLibrary getXMLTabData(int tabId) {

		TabLibrary tabLibrary=tabLibraryRepository.findOne(tabId);

		return tabLibrary;
	}

	public int getAgeinYear(String dob){
		int ageinyear = 0,ageinmonth=0,ageinday=0;
		String age="";

		if(HUtil.Nz(dob,"").equals(""))return -1;
		ageinyear = (int)(DateUtil.dateDiff( DateUtil.DATE , DateUtil.getDate(dob) , new java.util.Date() )/365);
		return ageinyear;
	}


	public int getAgeinMonth(String dob){
		int ageinyear = 0,ageinmonth=0,ageinday=0;
		String age="";
		if(HUtil.Nz(dob,"").equals(""))return -1;
		ageinyear = (int)(DateUtil.dateDiff( DateUtil.DATE , DateUtil.getDate(dob) , new java.util.Date() )/365);
		ageinmonth = (int)((DateUtil.dateDiff( DateUtil.DATE , DateUtil.getDate(dob) , new java.util.Date() )%365)/30);
		ageinyear = 12 * ageinyear;
		ageinmonth = ageinyear + ageinmonth; 
		return ageinmonth;
	}



	@Override
	public List<FileDetails> getIncompletePatientConsentFormsList(int patientId, int chartId) {

		List<FileDetails> filesList=fileDetailsRepository.findAll(ConsentFormsSpecification.getPatientConsentFormsList(patientId, chartId, 0));

		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Requested incomplete consent forms list with patientId:"+patientId,-1,
				httpServletRequest.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Requested incomplete consent forms list with patientId:"+patientId,"");
		
		return filesList;
	}

	@Override
	public List<FileDetails> getSignedPatientConsentFormsList(int patientId, int chartId) {

		List<FileDetails> filesList=fileDetailsRepository.findAll(ConsentFormsSpecification.getPatientConsentFormsList(patientId, chartId, 0));

		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Requested signed consent forms list with patientId:"+patientId,-1,
				httpServletRequest.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Requested signed consent forms list with patientId:"+patientId,"");
		
		return filesList;
	}

	@Override
	public List<FileDetails> getAllPatientConsentFormsList(int patientId, int chartId) {

		List<FileDetails> filesList=fileDetailsRepository.findAll(ConsentFormsSpecification.getPatientConsentFormsList(patientId, chartId, -1));

		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Requested all consent forms list with patientId:"+patientId,-1,
				httpServletRequest.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Requested all consent forms list with patientId:"+patientId,"");
		
		return filesList;
	}



	@Override
	public PortalConsentFormDetailsBean getIncompletePatientConsentFormsDetails(int patientId, int chartId, int fileDetailId) throws IOException {

		PortalConsentFormDetailsBean consentDetailsBean=new PortalConsentFormDetailsBean();

		String pathSeperator	=	java.io.File.separator;

		FileDetails consentFormFileDetails=fileDetailsRepository.findOne(ConsentFormsSpecification.getPatientConsentFormFileDetails(patientId, chartId, fileDetailId));

		if(consentFormFileDetails==null)
			return null;


		if(consentFormFileDetails.getFiledetailsIssigned()){
			consentDetailsBean.setSigned(true);
			consentDetailsBean.setFormSaveName(consentFormFileDetails.getFileName().get(0).getFilenameName());
		}else {

			consentDetailsBean.setSigned(false);

			String formsRootPathStr=sessionMap.getPracticeSharedFolderPath()+pathSeperator+"PatientConsentForms";

			File formsRootPath = new File(formsRootPathStr);
			if (!formsRootPath.exists()) {
				if (formsRootPath.mkdir()) {

				} else {

				}
			}
			Random random=new Random();
			int randomNumber=random.nextInt(100000);

			String formsPatientFolderPathStr=formsRootPath+pathSeperator+patientId;

			File formsPatientFolderPath = new File(formsPatientFolderPathStr);
			if (!formsPatientFolderPath.exists()) {
				if (formsPatientFolderPath.mkdir()) {

				} else {

				}
			}
			String templateFilePathStr;
			File templateFilePath;
			if(consentFormFileDetails.getFileName()==null||consentFormFileDetails.getFileName().get(0)==null||!consentFormFileDetails.getFileName().get(0).getFilenameName().contains(".htm")){
				return null;
			}
			else{
				templateFilePathStr=formsPatientFolderPathStr+pathSeperator+consentFormFileDetails.getFileName().get(0).getFilenameName();
				templateFilePath=new File(templateFilePathStr);
				if(templateFilePath.exists() && !templateFilePath.isDirectory()){

				}
				else{
					System.out.println("templateFilePathStr:::::::::"+templateFilePathStr);
					return null;
				}
			}

			Document consentForm=Jsoup.parse(new File(templateFilePathStr), "UTF-8");

			consentDetailsBean.setConsentHtml(consentForm.outerHtml());
			consentDetailsBean.setFormFilePath(templateFilePathStr);
		}

		String patientAttachmentsFolderPathStr=sessionMap.getPracticeSharedFolderPath()+pathSeperator+"Attachments";

		File patientAttachmentsFolderPath = new File(patientAttachmentsFolderPathStr);
		if (!patientAttachmentsFolderPath.exists()) {
			if (patientAttachmentsFolderPath.mkdir()) {

			} else {

			}
		}
		String patientDocumentsSavePathStr=sessionMap.getPracticeSharedFolderPath()+pathSeperator+"Attachments"+pathSeperator+patientId;

		File patientDocumentsSavePath = new File(patientAttachmentsFolderPathStr);
		if (!patientDocumentsSavePath.exists()) {
			if (patientDocumentsSavePath.mkdir()) {

			} else {

			}
		}

		consentDetailsBean.setPdfSavePath(patientDocumentsSavePathStr);
		consentDetailsBean.setFormName(consentFormFileDetails.getFormsTemplate().getFormsTemplateName());
		consentDetailsBean.setFormFileType(consentFormFileDetails.getFormsTemplate().getFormsTemplateFilename());
		consentDetailsBean.setFormTemplateId(consentFormFileDetails.getFormsTemplate().getFormsTemplateId());
		consentDetailsBean.setPatientId(patientId);
		consentDetailsBean.setFileDetailsId(consentFormFileDetails.getFiledetailsId());
		
		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.READ,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Requested incomplete consent forms details with patientId:"+patientId,-1,
				httpServletRequest.getRemoteAddr(),patientId,"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Requested incomplete consent forms details with patientId:"+patientId,"");
		
		return consentDetailsBean;
	}



	@Override
	public PortalConsentFormDetailsBean saveConsentAsPDF(PortalConsentFormDetailsBean formDetailsBean) {

		HTMLtoPdfConverter pdfConverter=new HTMLtoPdfConverter();

		SimpleDateFormat sdf=new SimpleDateFormat("MMddyyyyhhmmss");

		String formSavingName=formDetailsBean.getFormName()+"_"+formDetailsBean.getFormTemplateId()+"_"+formDetailsBean.getPatientId()+"_"+sdf.format(new Date())+".pdf";
		pdfConverter.pdfConverter(formDetailsBean.getPdfSavePath(), formDetailsBean.getConsentHtml(), formDetailsBean.getPatientId(), formSavingName);
		
		FileName fileName=fileNameRepository.findAll(ConsentFormsSpecification.getPatientConsentFormFileNameDetails(formDetailsBean.getFileDetailsId())).get(0);
		fileName.setFilenameName(formSavingName);
		fileName.setFilenameModifiedon(new Timestamp(new Date().getTime()));
		fileName.setFilenameModifiedby(formDetailsBean.getPatientId());

		fileNameRepository.saveAndFlush(fileName);

		formDetailsBean.setFormSaveName(formSavingName);

		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.EXPORT,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Saved consent form details with patientId:"+formDetailsBean.getPatientId()+"as PDF",-1,
				httpServletRequest.getRemoteAddr(),formDetailsBean.getPatientId(),"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Saved consent form details with patientId:"+formDetailsBean.getPatientId(),"");
		
		return formDetailsBean;
	}



	@Override
	public AlertEvent savePatientConsentForm(PortalConsentFormDetailsBean consentSaveBean) throws IOException {

		ExportHTMLAsPdf exportHTMLAsPdf=new ExportHTMLAsPdf();
		
		String pathSeperator	=	java.io.File.separator;

		SimpleDateFormat sdf=new SimpleDateFormat("MMddyyyyhhmmss");		

		String formSavingName=consentSaveBean.getFormTemplateId()+"_"+consentSaveBean.getPatientId()+"_"+sdf.format(new Date())+".pdf";
		
		String consenttHtmlPath=consentSaveBean.getPdfSavePath()+pathSeperator+consentSaveBean.getFormTemplateId()+"_"+consentSaveBean.getPatientId()+"_"+sdf.format(new Date())+".html";
	    
	    File template = new File(consenttHtmlPath);
	 	if (!template.exists()) {
	 		template.createNewFile();
	 	}
	    
	    Document html=Jsoup.parse(new File(consenttHtmlPath), "UTF-8");
	        		    
	        		        		  
	    String consenttHtml=consentSaveBean.getConsentHtml();
	    
	    BufferedWriter htmlWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(consenttHtmlPath), "UTF-8"));
	    html.getElementsByTag("html").remove();
        html.append(consenttHtml);
        htmlWriter.write(html.toString());
        htmlWriter.flush();
        htmlWriter.close();
		
		
		exportHTMLAsPdf.pdfConverter(consentSaveBean.getPdfSavePath(), consenttHtmlPath, formSavingName);
		
		FileName fileName=fileNameRepository.findAll(ConsentFormsSpecification.getPatientConsentFormFileNameDetails(consentSaveBean.getFileDetailsId())).get(0);
		fileName.setFilenameName(formSavingName);
		fileName.setFilenameModifiedon(new Timestamp(new Date().getTime()));
		fileName.setFilenameModifiedby(consentSaveBean.getPatientId());

		fileNameRepository.saveAndFlush(fileName);
		
		FileDetails fileDetails=fileDetailsRepository.findOne(ConsentFormsSpecification.getPatientConsentFormFileDetails(consentSaveBean.getPatientId(), -1, consentSaveBean.getFileDetailsId()));
		fileDetails.setFiledetailsIssigned(true);
		fileDetails.setFiledetailsLastmodifiedon(new Timestamp(new Date().getTime()));
		fileDetails.setFiledetailsLastmodifiedby(consentSaveBean.getPatientId());
		
		fileDetailsRepository.saveAndFlush(fileDetails);
		

		/*H810 demographicAlertCategory=h810Respository.findOne(PortalSettingsSpecification.getPortalAlertCategory(11));
		boolean sendToAll =demographicAlertCategory.getH810005();  
		int provider = Integer.parseInt(demographicAlertCategory.getH810003());
		int forwardTo = Integer.parseInt(demographicAlertCategory.getH810004());*/

		/*AlertEvent alert=new AlertEvent();
		alert.setAlertEventCategoryId(22);
		alert.setAlertEventStatus(1);
		alert.setAlertEventPatientId(regSaveDetailsBean.getPatientRegistrationId());
		alert.setAlertEventPatientName(registrationDetails.getPatientRegistrationLastName()+", "+registrationDetails.getPatientRegistrationFirstName());
		alert.setAlertEventEncounterId(-1);
		alert.setAlertEventRefId(demographicEntry.getH807001());
		alert.setAlertEventMessage("Request to change the Demographics Details.");
		alert.setAlertEventRoomId(-1);
		alert.setAlertEventCreatedDate(new Timestamp(new Date().getTime()));
		alert.setAlertEventModifiedby(-100);
		alert.setAlertEventFrom(-100);

		if(provider==0 && forwardTo==0)
			alert.setAlertEventTo(-1);
		else {
			if(sendToAll){
				if(forwardTo!=provider){
					alert.setAlertEventTo(forwardTo);
					AlertEvent alert2=alert;
					alert2.setAlertEventTo(provider);
					alertEventRepository.saveAndFlush(alert2);
				} else {
					alert.setAlertEventTo(forwardTo);
				}            	 
			}else{
				if(forwardTo!=0){
					alert.setAlertEventTo(forwardTo);
				} else {
					alert.setAlertEventTo(provider);
				}
			}
		}

		alertEventRepository.saveAndFlush(alert);*/

		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.EXPORT,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Saved consent form with patientId:"+consentSaveBean.getPatientId()+"as PDF",-1,
				httpServletRequest.getRemoteAddr(),consentSaveBean.getPatientId(),"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Saved consent form with patientId:"+consentSaveBean.getPatientId(),"");
		
		return null;
	}

}
