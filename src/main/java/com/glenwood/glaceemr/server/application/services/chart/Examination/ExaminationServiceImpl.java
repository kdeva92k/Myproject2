package com.glenwood.glaceemr.server.application.services.chart.Examination;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions;
import com.glenwood.glaceemr.server.application.models.ClinicalSystem;
import com.glenwood.glaceemr.server.application.models.LeafLibrary;
import com.glenwood.glaceemr.server.application.models.LeafPatient;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PeElement;
import com.glenwood.glaceemr.server.application.models.PeElementDetail;
import com.glenwood.glaceemr.server.application.models.PeElementDetailOption;
import com.glenwood.glaceemr.server.application.models.PeElementGroup;
import com.glenwood.glaceemr.server.application.models.PeSystem;
import com.glenwood.glaceemr.server.application.models.SoapElementDatalist;
import com.glenwood.glaceemr.server.application.repositories.ClinicalElementTemplateMappingRepository;
import com.glenwood.glaceemr.server.application.repositories.ClinicalElementsOptionsRepository;
import com.glenwood.glaceemr.server.application.repositories.ClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.ClinicalSystemRepository;
import com.glenwood.glaceemr.server.application.repositories.LeafLibraryRepository;
import com.glenwood.glaceemr.server.application.repositories.LeafPatientRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientClinicalElementsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.repositories.PeElementDetailOptionRepository;
import com.glenwood.glaceemr.server.application.repositories.PeElementDetailRepository;
import com.glenwood.glaceemr.server.application.repositories.PeElementGroupRepository;
import com.glenwood.glaceemr.server.application.repositories.PeElementRepository;
import com.glenwood.glaceemr.server.application.repositories.PeSystemRepository;
import com.glenwood.glaceemr.server.application.repositories.SoapElementDatalistRepository;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalConstants;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalDataBean;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalElementBean;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalElementOptionBean;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalElementsService;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.PatientElementBean;
import com.glenwood.glaceemr.server.application.services.chart.vitals.DetailOptions;
import com.glenwood.glaceemr.server.application.specifications.ClinicalElementsSpecification;
import com.glenwood.glaceemr.server.application.specifications.ExaminationSpecification;
import com.glenwood.glaceemr.server.application.specifications.LeafLibrarySpecification;
import com.glenwood.glaceemr.server.application.specifications.LeafPatientSpecfication;
import com.glenwood.glaceemr.server.application.specifications.PatientClinicalElementsSpecification;
import com.glenwood.glaceemr.server.application.specifications.PatientRegistrationSpecification;
import com.glenwood.glaceemr.server.utils.DateUtil;
import com.glenwood.glaceemr.server.utils.HUtil;


@Service
@Transactional
public class ExaminationServiceImpl implements ExaminationService{

	@Autowired
	ClinicalSystemRepository clinicalSystemRepository;

	@Autowired
	PeElementRepository peElementRepository;

	@Autowired
	LeafPatientRepository leafPatientRepository;

	@Autowired
	ClinicalElementTemplateMappingRepository clinicalElementTemplateMappingRepository;

	@Autowired
	PeElementDetailRepository peElementDetailRepository;

	@Autowired
	PeElementDetailOptionRepository peElementDetailOptionRepository;

	@Autowired
	PeElementGroupRepository peElementGroupRepository;

	@Autowired
	ClinicalElementsOptionsRepository clinicalElementsOptionsRepository;

	@Autowired
	PeSystemRepository peSystemRepository;

	@Autowired
	ClinicalElementsRepository clinicalElementsRepository;

	@Autowired
	SoapElementDatalistRepository soapElementDatalistRepository;

	@Autowired
	ClinicalElementsService clinicalElementsService;

	@Autowired 
	PatientRegistrationRepository patientRegRepository;

	@Autowired
	LeafLibraryRepository leafLibraryRepository;

	@Autowired
	PatientClinicalElementsRepository patientClinicalElementsRepository;

	@Autowired
	ClinicalDataBean clinicalDataBean;

	Short patientSex=0;
	Date patDOB=new Date();
	Integer ageinDay=0;

	

	@Override
	public List<CustomPESystem> getActiveSystems(String clientId,Integer patientId, Integer chartId,Integer encounterId,Integer templateId) {
		clearClinicalData();
		List<ClinicalSystem> activePESystems = clinicalSystemRepository.findAll(ExaminationSpecification.getActiveExaminationSystems(templateId));
		ArrayList<Integer> sytemIds = new ArrayList<Integer>();

		//remove duplicates if any 
		
		for (int i = 0;i<activePESystems.size();i++) {
			
			if(!sytemIds.contains(activePESystems.get(i).getClinicalSystemId()))
			{
				sytemIds.add(activePESystems.get(i).getClinicalSystemId());
			}else{
				activePESystems.remove(i);
				i--;
			}
		}

		clinicalDataBean.clientId=clientId;
		setClinicalDataBean(patientId,encounterId,templateId,5);

		//check whether all active systems having atleast one clinical element and whether the system is documented or not 

		List<CustomPESystem> examinationSystems=new ArrayList<CustomPESystem>();

		for (int i=0;i<activePESystems.size();i++) {
			String resp=isSystemhavingElements(activePESystems.get(i).getClinicalSystemPeGwid(),patientId,encounterId,templateId);
			
			if(resp.split("%%")[0].equals("false")){
				activePESystems.remove(i);
				i--;
			}else{
				if(resp.split("%%").length==1)
					examinationSystems.add(getExaminationSysBean(activePESystems.get(i),""));
				else
					examinationSystems.add(getExaminationSysBean(activePESystems.get(i),resp.split("%%")[1]));
			}
		}




		//PE Notes

		CustomPESystem system = new CustomPESystem();
		system.setSystemName("Notes");
		system.setSystemId(102);

		List<PatientClinicalElements> patientPeNotes=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinEleByGWID(encounterId, patientId, "0000200100000000000"));
		if(patientPeNotes.size()>0){
			if(patientPeNotes.get(0).getPatientClinicalElementsValue()==null){
				system.getPeElements().add(getElementObject("","","", 2,"",null,null));
			}else{
				system.getPeElements().add(getElementObject("","","", 2,patientPeNotes.get(0).getPatientClinicalElementsValue(),null,null));
				if(!patientPeNotes.get(0).getPatientClinicalElementsValue().equalsIgnoreCase("")){
					system.setDocStatus("Documented");
				}
			}
		}
		examinationSystems.add(system);
		return examinationSystems;
	}




	private String isSystemhavingElements(int selSystemId,Integer patientId,Integer encounterId,Integer templateId) {

		List<PeElementGroup> elementGrps = peElementGroupRepository.findAll(ExaminationSpecification.getElementGrpDetailsBySysId(Arrays.asList(selSystemId)));
		List<Integer> elementIds = new ArrayList<Integer> ();
		elementIds.add(-1);
		for (PeElementGroup peElementGroup : elementGrps) {
			elementIds.add(peElementGroup.getPeElementGroupId());
		}
		List<PeElement> activePeElements = peElementRepository.findAll(ExaminationSpecification.getElementsByGrpId(elementIds,false,templateId,patientId,encounterId));
		activePeElements.addAll(peElementRepository.findAll(ExaminationSpecification.getElementsByGrpId(elementIds,true,templateId,patientId,encounterId)));
		boolean ishavingElement=false;
		boolean isSystemDocumented=false;
		StringBuffer systemValue=new StringBuffer();
		String status="";
		String text="";

		for (PeElement peElement : activePeElements) {
			if(clinicalDataBean.getClinicalElements(peElement.getPeElementGwid())!=null){
				ishavingElement=true;
				if(ishavingElement== true && isSystemDocumented==true)
					break;
			}
			try{
				ClinicalElementBean ceBean = clinicalDataBean.getClinicalElements(peElement.getPeElementGwid());
				int dataType=-1;
				if(ceBean!=null ){
					dataType=ceBean.getClinicalElementDataType();
					PatientElementBean patElemBean=clinicalDataBean.getPatientElements(peElement.getPeElementGwid());
					String elementValue=null;
					if(patElemBean!=null){
						if(dataType==3)
							elementValue=patElemBean.getPatientClinicalElementBoolean()+"";
						else
							elementValue=patElemBean.getPatientClinicalElementText();
						if(dataType==3||dataType==4||dataType==5){
							systemValue.append(elementValue);
						}else if(dataType==2 && elementValue!=null && !elementValue.equalsIgnoreCase("")){
							text=elementValue;
							status="Documented";
							isSystemDocumented=true;
							if(ishavingElement== true && isSystemDocumented==true)
								break;
						}
					}
				}

				String associatedElementNotesGWID =peElement.getClinicalTextMappings().get(0).getClinicalTextMappingTextboxGwid();
				ClinicalElementBean associatedCeBean = clinicalDataBean.getClinicalElements(associatedElementNotesGWID);
				PatientElementBean associatedPatientBean = clinicalDataBean.getPatientElements(associatedElementNotesGWID);
				if(associatedCeBean!=null && associatedPatientBean!=null && associatedPatientBean.getPatientClinicalElementText()!=null && !(associatedPatientBean.getPatientClinicalElementText().equals(""))){
					text=associatedPatientBean.getPatientClinicalElementText();
					status="Documented";
					isSystemDocumented=true;
					if(ishavingElement== true && isSystemDocumented==true)
						break;
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}



		String systemNotesGWID = getExaminationSystemNotesGWID(selSystemId);
		ClinicalElementBean ceBean = clinicalDataBean.getClinicalElements(systemNotesGWID);
		if((ceBean!=null) && (ceBean.getClinicalElementDataType() == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT)) {
			ishavingElement=true;
		}
		PatientElementBean patientElementBean=clinicalDataBean.getPatientElements(systemNotesGWID);
		if(patientElementBean!=null){
			text=patientElementBean.getPatientClinicalElementText();
			status="Documented";
		}

		if(systemValue.equals("") && text.equals("")){
			status="";
		}else if(systemValue.indexOf("true")>-1){
			status="Documented";
		}else if(systemValue.indexOf("false")>-1 && text.equals("")){
			status="Normal";
		} 
		return ishavingElement+"%%"+status;
	}


	@Override
	public CustomPESystem getSystemActiveElements(String clientId,Integer patientId, Integer chartId,Integer encounterId,Integer templateId,Integer selectedSystemId) {
		clearClinicalData();
		clinicalDataBean.clientId=clientId;
		setClinicalDataBean(patientId,encounterId,templateId,5);
		ExaminationSystem systemBean=getExaminationBeanForSystem(selectedSystemId, patientId, encounterId, templateId);
		return frameExaminationJson(systemBean,patientId,encounterId);
	}

	private ExaminationSystem getExaminationBeanForSystem(Integer selSystemId,Integer patientId, Integer encounterId,Integer templateId) {

		List<ClinicalSystem> activePESystems=clinicalSystemRepository.findAll(ExaminationSpecification.getClincialSysDetailsById(Arrays.asList(selSystemId)));

		//*************Get PE element groups*********************//
		List<PeElementGroup> elementGrps = peElementGroupRepository.findAll(ExaminationSpecification.getElementGrpDetailsBySysId(Arrays.asList(selSystemId)));

		List<Integer> elementIds = new ArrayList<Integer> ();
		elementIds.add(-1);
		for (PeElementGroup peElementGroup : elementGrps) {
			elementIds.add(peElementGroup.getPeElementGroupId());
		}

		//**************Get PE elements****************************//
		List<PeElement> activePeElements = peElementRepository.findAll(ExaminationSpecification.getElementsByGrpId(elementIds,false,templateId,patientId,encounterId));
		List<PeElement> patactivePeElements =peElementRepository.findAll(ExaminationSpecification.getElementsByGrpId(elementIds,true,templateId,patientId,encounterId));


		//remove duplicates from patactivePeElements
		elementIds.clear();
		for (PeElement peElement : activePeElements) {
			elementIds.add(peElement.getPeElementId());
		}

		for(int i=0;i<patactivePeElements.size();i++){
			if(elementIds.contains(patactivePeElements.get(i).getPeElementId())){
				patactivePeElements.remove(i);
				i--;
			}
		}	

		activePeElements.addAll(patactivePeElements);

		elementIds.clear();
		elementIds.add(-1);
		for (PeElement peElement : activePeElements) {
			elementIds.add(peElement.getPeElementId());
		}

		//****************Get PE element details*****************//
		List<PeElementDetail> elementDet = peElementDetailRepository.findAll(ExaminationSpecification.getElementDetailByElemId(elementIds));
		elementIds.clear();
		elementIds.add(-1);
		for (PeElementDetail peElementDetail : elementDet) {
			elementIds.add(peElementDetail.getPeElementDetailId());
		}

		//*******************Get PE element Detail Options**************//

		List<PeElementDetailOption> elementDetOpt = peElementDetailOptionRepository.findAll(ExaminationSpecification.getDetailOptByElementId(elementIds, false, templateId, patientId, encounterId));
		List<PeElementDetailOption> patelementDetOpt=peElementDetailOptionRepository.findAll(ExaminationSpecification.getDetailOptByElementId(elementIds, true, templateId, patientId, encounterId));
		List<String> detOpGwids=new ArrayList<>();

		//remove duplicates from patelementDetOpt

		for (PeElementDetailOption detOption : elementDetOpt) {
			detOpGwids.add(detOption.getPeElementDetailOptionGwid());
		}


		for(int i=0;i<patelementDetOpt.size();i++){
			if(detOpGwids.contains(patelementDetOpt.get(i).getPeElementDetailOptionGwid())){
				patelementDetOpt.remove(i);
				i--;
			}
		}	


		elementDetOpt.addAll(patelementDetOpt);
		for (PeElementDetailOption peElementDetailOption : elementDetOpt) {
			detOpGwids.add(peElementDetailOption.getPeElementDetailOptionGwid());
		}
		detOpGwids.add("-1");



		//****************Get ClinicalOptions***************************//

		getPatientDetails(patientId);
		List<LeafLibrary> leafDetails=leafLibraryRepository.findAll(LeafLibrarySpecification.getLeafDetailsByTemplateId(templateId));

		boolean isAgeBased=leafDetails.get(0).getLeafLibraryIsagebased()==null?false:leafDetails.get(0).getLeafLibraryIsagebased();


		List<ClinicalElementsOptions> clinicaldetailOptions=clinicalElementsOptionsRepository.findAll(ClinicalElementsSpecification.getTempClinicalOptions(elementIds, templateId, detOpGwids,isAgeBased,patientSex,ageinDay));
		List<ClinicalElementsOptions> patclinicaldetailOptions=clinicalElementsOptionsRepository.findAll(PatientClinicalElementsSpecification.getPatClinicalOptionsData(patientId, encounterId, detOpGwids));


		//remove duplicates from patclinicaldetailOptions

		for (ClinicalElementsOptions detOption : clinicaldetailOptions) {
			detOpGwids.add(detOption.getClinicalElementsOptionsGwid());
		}


		for(int i=0;i<patclinicaldetailOptions.size();i++){
			if(detOpGwids.contains(patclinicaldetailOptions.get(i).getClinicalElementsOptionsGwid())){
				patclinicaldetailOptions.remove(i);
				i--;
			}
		}	

		clinicaldetailOptions.addAll(patclinicaldetailOptions);



		//construct beans
		ExaminationSystem sBean = new ExaminationSystem();
		int systemId	=	Integer.parseInt(HUtil.Nz(activePESystems.get(0).getClinicalSystemPeGwid(),"-1"));
		sBean.setSystemId(Integer.parseInt(HUtil.Nz(activePESystems.get(0).getClinicalSystemPeGwid(),"-1")));
		sBean.setSystemName(HUtil.Nz(activePESystems.get(0).getClinicalSystemName(),""));
		sBean.setSystemEandMType(HUtil.Nz(activePESystems.get(0).getClinicalSystemPeEandmtype(),""));
		sBean.setSystemDeferredGWID(HUtil.Nz(activePESystems.get(0).getClinicalSystemPeDeferredGwid(),""));
		sBean.setChaperoneGWID(HUtil.Nz(activePESystems.get(0).getClinicalSystemPeChaperoneGwid(),""));
		LinkedHashMap<Integer, ExaminationElementGroupBean> elementGroupHashMap = new LinkedHashMap<Integer, ExaminationElementGroupBean>();
		boolean groupFlag	=	false;
		for(int j=0; j<elementGrps.size();j++){
			int elementGroupId	=-1;
			ExaminationElementGroupBean egbean = new ExaminationElementGroupBean();
			if(systemId==Integer.parseInt(HUtil.Nz(elementGrps.get(j).getPeElementGroupSystemId(),"-1"))){
				groupFlag	=	true;
				elementGroupId	=	Integer.parseInt(HUtil.Nz(elementGrps.get(j).getPeElementGroupId(),"-1"));
				egbean.setElementGroupId(Integer.parseInt(HUtil.Nz(elementGrps.get(j).getPeElementGroupId(),"-1")));
				egbean.setElementGroupName(HUtil.Nz(elementGrps.get(j).getPeElementGroupName(),""));
				elementGrps.remove(j);
				j--;

			}else
				if(groupFlag==true)break;
			if(elementGroupId!=-1){
				LinkedHashMap<Integer, ExaminationElementBean> examinationElements = new LinkedHashMap<Integer, ExaminationElementBean>();
				boolean	elementFlag	=	false;
				for(int k=0; k<activePeElements.size();k++){
					ExaminationElementBean ebean = new ExaminationElementBean();
					int elementId	=	-1;
					if(elementGroupId==Integer.parseInt(HUtil.Nz(activePeElements.get(k).getPeElementGroupId(),"-1"))){
						elementFlag	=	true;
						elementId	=	Integer.parseInt(HUtil.Nz(activePeElements.get(k).getPeElementId(),"-1"));
						ebean.setElementId(Integer.parseInt(HUtil.Nz(activePeElements.get(k).getPeElementId(),"-1")));
						ebean.setElementGWID(HUtil.Nz(activePeElements.get(k).getPeElementGwid(),"0000000000000000000"));
						ebean.setElementName(HUtil.Nz(activePeElements.get(k).getPeElementName(),""));
						ebean.setElementPrintText(HUtil.Nz(activePeElements.get(k).getPeElementPrinttext(),""));
						ebean.setAssociatedGWID(HUtil.Nz(activePeElements.get(k).getClinicalTextMappings().get(activePeElements.get(k).getClinicalTextMappings().size()-1).getClinicalTextMappingTextboxGwid(),""));
						ebean.setNameNeededInPrint(HUtil.Nz(activePeElements.get(k).getPeElementIsnameneededinprint(),""));

						activePeElements.remove(k);
						k--;
					}else
						if(elementFlag==true)break;
					if(elementId!=-1){
						LinkedHashMap<Integer, ExaminationDetailBean> examinationDetails = new LinkedHashMap<Integer, ExaminationDetailBean>();
						boolean	detailFlag	=	false;	
						for(int l=0; l<elementDet.size();l++){
							ExaminationDetailBean edbean = new ExaminationDetailBean();
							int detailId	=	-1;
							if(elementId==Integer.parseInt(HUtil.Nz(elementDet.get(l).getPeElementDetailElementId(),"-1"))){
								detailFlag	=	true;
								detailId	=	Integer.parseInt(HUtil.Nz(elementDet.get(l).getPeElementDetailId(),"-1"));
								edbean.setDetailId(Integer.parseInt(HUtil.Nz(elementDet.get(l).getPeElementDetailId(),"-1")));
								edbean.setDetailName((HUtil.Nz(elementDet.get(l).getPeElementDetailName(),"")));
								edbean.setDetailPrinttext((HUtil.Nz(elementDet.get(l).getPeElementDetailPrinttext(),"")));
								edbean.setDetailDisplayName((HUtil.Nz(elementDet.get(l).getPeElementDetailDisplayname(),"")));

								elementDet.remove(l);
								l--;
							}
							else
								if(detailFlag==true)break;
							if(detailId!=-1){
								LinkedHashMap<Integer, ExaminationDetailOptionBean> detailOptions = new LinkedHashMap<Integer, ExaminationDetailOptionBean>();
								boolean optionFlag=false;
								for(int m=0; m<elementDetOpt.size();m++){
									ExaminationDetailOptionBean edobean = new ExaminationDetailOptionBean();
									String detailOptionGwid	=	"-1";
									int type	=	-1;
									if(detailId==Integer.parseInt(HUtil.Nz(elementDetOpt.get(m).getPeElementDetailOptionDetailId(),"-1"))){
										optionFlag	=	true;

										detailOptionGwid	=	HUtil.Nz(elementDetOpt.get(m).getPeElementDetailOptionGwid(),"-1");
										type				=	Integer.parseInt(HUtil.Nz(elementDetOpt.get(m).getClinicalElement().getClinicalElementsDatatype(),"-1"));
										edobean.setOptionId(Integer.parseInt(HUtil.Nz(elementDetOpt.get(m).getPeElementDetailOptionId(),"-1")));
										edobean.setOptionName((HUtil.Nz(elementDetOpt.get(m).getPeElementDetailOptionName(),"")));
										edobean.setOptionDisplayName((HUtil.Nz(elementDetOpt.get(m).getPeElementDetailOptionDisplayname(),"")));
										edobean.setOptionPrintText((HUtil.Nz(elementDetOpt.get(m).getPeElementDetailOptionPrinttext(),"")));
										edobean.setOptionGWId((HUtil.Nz(elementDetOpt.get(m).getPeElementDetailOptionGwid(),"")));

										detailOptions.put(edobean.getOptionId(), edobean);
										elementDetOpt.remove(m);
										m--;
									}
									else
										if(optionFlag==true)break;
									if((!detailOptionGwid.equalsIgnoreCase("-1")) && (type==4 || type==5 )){
										LinkedHashMap<Integer, ClinicalElementOptionBean> clinicalElementOptions = new LinkedHashMap<Integer, ClinicalElementOptionBean>();
										for(int n=0;n<clinicaldetailOptions.size();n++){
											ClinicalElementOptionBean optionbean = new ClinicalElementOptionBean();
											if(detailOptionGwid.equalsIgnoreCase(HUtil.Nz(clinicaldetailOptions.get(n).getClinicalElementsOptionsGwid(),"-1"))){
												detailOptionGwid	=	HUtil.Nz(clinicaldetailOptions.get(n).getClinicalElementsOptionsGwid(),"-1");
												optionbean.setClinicalelementoptionId(Integer.parseInt(HUtil.Nz(clinicaldetailOptions.get(n).getClinicalElementsOptionsId(),"-1")));
												optionbean.setClinicalelementoptionGwid(HUtil.Nz(clinicaldetailOptions.get(n).getClinicalElementsOptionsGwid(),"-1"));
												optionbean.setClinicalelementoptionName(HUtil.Nz(clinicaldetailOptions.get(n).getClinicalElementsOptionsName(),"-1"));
												optionbean.setClinicalelementoptionValue(HUtil.Nz(clinicaldetailOptions.get(n).getClinicalElementsOptionsValue(),"-1"));

												clinicalElementOptions.put(optionbean.getClinicalelementoptionId(),optionbean);
												clinicaldetailOptions.remove(n);
												n--;
											}

										}	
										edobean.setClinicalElementOption(clinicalElementOptions);
										detailOptions.put(edobean.getOptionId(), edobean);

									}
								}
								edbean.setDetailOptions(detailOptions);
								examinationDetails.put(edbean.getDetailId(), edbean);
							}
						}
						ebean.setExaminationDetails(examinationDetails);
						examinationElements.put(ebean.getElementId(), ebean);
					}
				}
				egbean.setExaminationElements(examinationElements);
				elementGroupHashMap.put(egbean.getElementGroupId(), egbean);
			}
		}
		sBean.setExaminationElementGroups(elementGroupHashMap);
		//	ExaminationSystems.put(sBean.getSystemId(),sBean);
		return sBean;

	}


	private CustomPESystem frameExaminationJson(ExaminationSystem systemBean,Integer patientId,Integer encounterId) {

		CustomPESystem system=new CustomPESystem();
		List<ExaminationElement> elements=new ArrayList<ExaminationElement>();
		if(systemBean.getExaminationElementGroups().size()>0) {
			LinkedHashMap<Integer, ExaminationElementGroupBean> elemGroupHashMap = systemBean.getExaminationElementGroups();
			int elementGroupId = -1;
			String elementGroupName = "";
			ExaminationElementGroupBean egBean = new ExaminationElementGroupBean();
			Set<Entry<Integer, ExaminationElementGroupBean>> elementGroupResultSet = elemGroupHashMap.entrySet();
			for(Entry<Integer,ExaminationElementGroupBean> elementGroupEntrySet:elementGroupResultSet){
				elementGroupId         = elementGroupEntrySet.getKey();
				egBean                 = elementGroupEntrySet.getValue();
				elementGroupName     = egBean.getElementGroupName().replace("!@@!", "\\");
				LinkedHashMap<Integer, ExaminationElementBean> elementHashMap = egBean.getExaminationElements();
				int elementId = -1;
				String elementName = "";
				String elementPrintText = "";
				String elementGWID = "";
				String associatedGWID = "";
				ExaminationElementBean eBean = new ExaminationElementBean();
				Set<Entry<Integer, ExaminationElementBean>> elementResultSet = elementHashMap.entrySet();
				for(Entry<Integer,ExaminationElementBean> elementEntrySet:elementResultSet){
					elementId             = elementEntrySet.getKey();
					eBean                 = elementEntrySet.getValue();
					elementName         = eBean.getElementName().replace("!@@!", "\\");
					elementPrintText     = eBean.getElementPrintText().replace("!@@!", "\\");
					elementGWID         = eBean.getElementGWID();
					associatedGWID         = eBean.getAssociatedGWID();

					PatientElementBean patientElementBean2=clinicalDataBean.getPatientElements(elementGWID);
					ClinicalElementBean ceBean1 = clinicalDataBean.getClinicalElements(elementGWID);
					if(ceBean1!= null){
						if(ceBean1.getClinicalElementDataType() == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN) {
							ExaminationElement elementObj=null;
							String associatedElementNotesGWID = associatedGWID;
							ClinicalElementBean associatedCeBean = clinicalDataBean.getClinicalElements(associatedElementNotesGWID);
							PatientElementBean associatedPatientBean = clinicalDataBean.getPatientElements(associatedElementNotesGWID);
							if(patientElementBean2!=null){
								boolean elementValue=patientElementBean2.getPatientClinicalElementBoolean();
								if(elementValue){
									elementObj=getElementObject(elementName,elementPrintText,elementGWID,ceBean1.getClinicalElementDataType(),"true",null,null);
								}else{
									elementObj=getElementObject(elementName,elementPrintText,elementGWID,ceBean1.getClinicalElementDataType(),"false",null,null);
								}
							}else{
								elementObj=getElementObject(elementName,elementPrintText,elementGWID,ceBean1.getClinicalElementDataType(),"",null,null);
							}
							if(associatedCeBean!=null){
								if(elementObj!=null){
									if(associatedPatientBean != null){
										elementObj.setAssociate(Arrays.asList(getAssociateObject(elementName,elementPrintText,1,associatedElementNotesGWID,associatedCeBean.getClinicalElementDataType(),associatedPatientBean.getPatientClinicalElementText())));
									}else{
										elementObj.setAssociate(Arrays.asList(getAssociateObject(elementName,elementPrintText,1,associatedElementNotesGWID,associatedCeBean.getClinicalElementDataType(),"")));
									}
								}
							}

							elements.add(elementObj);
						}

						LinkedHashMap<Integer, ExaminationDetailBean> detailHashMap = eBean.getExaminationDetails();
						int detailId = -1;
						String detailName = "";
						String detailPrintText = "";
						String detailDisplayName = "";
						ExaminationDetailBean dBean = new ExaminationDetailBean();
						Set<Entry<Integer, ExaminationDetailBean>> detailResultSet = detailHashMap.entrySet();
						for(Entry<Integer,ExaminationDetailBean> detailEntrySet:detailResultSet){
							detailId             = detailEntrySet.getKey();
							dBean                 = detailEntrySet.getValue();
							detailName             = dBean.getDetailName().replace("!@@!", "\\");
							detailPrintText     = dBean.getDetailPrinttext().replace("!@@!", "\\");
							detailDisplayName    = dBean.getDetailDisplayName().replace("!@@!", "\\");
							LinkedHashMap<Integer, ExaminationDetailOptionBean> detailOptionHashMap = dBean.getDetailOptions();
							int detailOptionId = -1;
							String detailOptionName = "";
							String detailOptionDisplayName = "";
							String detailOptionPrintText = "";
							String detailOptionGWID = "";
							ExaminationDetailOptionBean doBean = new ExaminationDetailOptionBean();
							Set<Entry<Integer, ExaminationDetailOptionBean>> detailOptionResultSet = detailOptionHashMap.entrySet();
							for(Entry<Integer,ExaminationDetailOptionBean> detailOptionEntrySet:detailOptionResultSet){
								detailOptionId             = detailOptionEntrySet.getKey();
								doBean                     = detailOptionEntrySet.getValue();
								detailOptionName         = doBean.getOptionName().replace("!@@!", "\\");
								detailOptionDisplayName    = doBean.getOptionDisplayName().replace("!@@!", "\\");
								detailOptionPrintText     = doBean.getOptionPrintText().replace("!@@!", "\\");
								detailOptionGWID        = doBean.getOptionGWId();

								ClinicalElementBean detailceBean = clinicalDataBean.getClinicalElements(detailOptionGWID);
								int isSelect    =    -1;
								if(detailceBean!= null) {
									isSelect    =    detailceBean.getClinicalElementIsSelect();
									PatientElementBean detOptpatientElementBean=clinicalDataBean.getPatientElements(detailOptionGWID);
									if(detailceBean.getClinicalElementDataType() == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN) {
										if(detOptpatientElementBean!=null){
											boolean elementValue=detOptpatientElementBean.getPatientClinicalElementBoolean();
											for(int i=0;i<elements.size();i++){
												if(elements.get(i).getElementGwid().equalsIgnoreCase(elementGWID)){
													if(elementValue){
														elements.get(i).getElementDetails().add(getElementDetailObject(detailOptionDisplayName,detailOptionGWID,detailOptionPrintText,detailceBean.getClinicalElementDataType(),"true"));
													}else{
														elements.get(i).getElementDetails().add(getElementDetailObject(detailOptionDisplayName,detailOptionGWID,detailOptionPrintText,detailceBean.getClinicalElementDataType(),"false"));
													}
												}
											}
										}else{
											for(int i=0;i<elements.size();i++){
												if(elements.get(i).getElementGwid().equalsIgnoreCase(elementGWID)){
													elements.get(i).getElementDetails().add(getElementDetailObject(detailOptionDisplayName,detailOptionGWID,detailOptionPrintText,detailceBean.getClinicalElementDataType(),""));
												}
											}
										}
									}else if(detailceBean.getClinicalElementDataType() == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT){
										for(int i=0;i<elements.size();i++){
											if(elements.get(i).getElementGwid().equalsIgnoreCase(elementGWID)){
												if(detOptpatientElementBean!=null){
													elements.get(i).getElementDetails().add(getElementDetailObject(detailOptionDisplayName,detailOptionGWID,detailOptionPrintText,detailceBean.getClinicalElementDataType(),detOptpatientElementBean.getPatientClinicalElementText()));
												}else{
													elements.get(i).getElementDetails().add(getElementDetailObject(detailOptionDisplayName,detailOptionGWID,detailOptionPrintText,detailceBean.getClinicalElementDataType(),""));
												}
											}
										}
									}else if(detailceBean.getClinicalElementDataType() == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_XHTML_PLUGIN){
										for(int i=0;i<elements.size();i++){
											if(elements.get(i).getElementGwid().equalsIgnoreCase(elementGWID)){
												elements.get(i).getElementDetails().add(getElementDetailObject(detailOptionDisplayName,detailOptionGWID,detailOptionPrintText,detailceBean.getClinicalElementDataType(),""));
											}
										}
									}

									if (detailceBean.getClinicalElementDataType() == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION){
										try{
											LinkedHashMap<Integer, ClinicalElementOptionBean> clinicalOptionMap = doBean.getClinicalElementOption();
											ClinicalElementOptionBean optionBean = new ClinicalElementOptionBean();
											Set<Entry<Integer, ClinicalElementOptionBean>> clinicalOptionResultSet = clinicalOptionMap.entrySet();
											String optionName=null;
											String optionValue=null;
											for(int i=0;i<elements.size();i++){
												if(elements.get(i).getElementGwid().equalsIgnoreCase(elementGWID)){
													ElementDetail elemDet=getElementDetailObject(detailOptionDisplayName,detailOptionGWID,detailOptionPrintText,detailceBean.getClinicalElementDataType(),"");
													elements.get(i).getElementDetails().add(elemDet);
													for(Entry<Integer,ClinicalElementOptionBean> clinicalOptionEntrySet:clinicalOptionResultSet){
														optionBean                     = clinicalOptionEntrySet.getValue();
														optionName                    = optionBean.getClinicalelementoptionName().replace("!@@!", "\\");
														optionValue                    = optionBean.getClinicalelementoptionValue();
														if(detOptpatientElementBean!=null){
															String elementValue=HUtil.Nz(detOptpatientElementBean.getPatientClinicalElementOption(),"");
															if(optionBean.getClinicalelementoptionValue().equalsIgnoreCase(elementValue)){
																elemDet.getDetailOptions().add(getDetailOptionObject(optionName,optionValue,1));
															}else{
																elemDet.getDetailOptions().add(getDetailOptionObject(optionName,optionValue,0));
															}
														}else{
															elemDet.getDetailOptions().add(getDetailOptionObject(optionName,optionValue,0));
														}
													}

												}
											}
										}catch(Exception e){
											e.printStackTrace();
										}
									}

									if (detailceBean.getClinicalElementDataType() == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION){

										LinkedHashMap<Integer, ClinicalElementOptionBean> clinicalOptionMap = doBean.getClinicalElementOption();
										ClinicalElementOptionBean optionBean = new ClinicalElementOptionBean();
										Set<Entry<Integer, ClinicalElementOptionBean>> clinicalOptionResultSet = clinicalOptionMap.entrySet();
										int clinicalOptionId    =    -1;
										String optionName    =    "";
										String optionValue    =    "";

										for(int i=0;i<elements.size();i++){
											if(elements.get(i).getElementGwid().equalsIgnoreCase(elementGWID)){
												ElementDetail elemDet=getElementDetailObject(detailOptionDisplayName, detailOptionGWID, detailOptionPrintText, detailceBean.getClinicalElementDataType(), "");

												elements.get(i).elementDetails.add(elemDet);

												for(Entry<Integer,ClinicalElementOptionBean> clinicalOptionEntrySet:clinicalOptionResultSet){
													clinicalOptionId             = clinicalOptionEntrySet.getKey();
													optionBean                     = clinicalOptionEntrySet.getValue();
													optionName                    = optionBean.getClinicalelementoptionName().replace("!@@!", "\\");
													optionValue                    = optionBean.getClinicalelementoptionValue();

													PatientElementBean patElementBean=clinicalDataBean.getPatientElements(detailOptionGWID+"_"+optionValue);
													if(patElementBean!=null){
														String elementValue=HUtil.Nz(patElementBean.getPatientClinicalElementOption(),"");
														if(optionValue.equalsIgnoreCase(elementValue)){
															elemDet.getDetailOptions().add(getDetailOptionObject(optionName, optionValue, 1));
														}else{
															elemDet.getDetailOptions().add(getDetailOptionObject(optionName, optionValue, 0));
														}
													}else{
														elemDet.getDetailOptions().add(getDetailOptionObject(optionName, optionValue, 0));
													}
												}

											}
										}
									}
									if (detailceBean.getClinicalElementDataType() == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_OCX){   
										String ocxSrc=clinicalElementsService.getClinicalElementOptions(detailOptionGWID).get(0).getClinicalElementsOptionsValue();
										String dimension=detailceBean.getClinicalElementTextDimension();
										String height="";
										String width="";
										if(!dimension.equalsIgnoreCase("")){
											if(dimension.indexOf('X')!=-1){
												height=dimension.split("X")[1];
												width=dimension.split("X")[0];
											}
										}else{
											height="228";
											width="620";
										}
										PatientElementBean patElementBean=clinicalDataBean.getPatientElements(detailOptionGWID);
										String elementValue=null;
										if(patElementBean!=null){
											elementValue=patElementBean.getPatientClinicalElementText();
										}
										for(int i=0;i<elements.size();i++){
											if(elements.get(i).getElementGwid().equalsIgnoreCase(elementGWID)){
												ElementDetail elemDet=getElementDetailObject("", detailOptionGWID, detailOptionPrintText, detailceBean.getClinicalElementDataType(), "");
												elements.get(i).getElementDetails().add(elemDet);
												elemDet.getDetailOptions().add(getDetailOptionObject(elementName, ocxSrc, elementValue,ocxSrc,7,width,height));
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		//************ System Notes *************//

		String systemNotesGWID = getExaminationSystemNotesGWID(systemBean.getSystemId());
		ClinicalElementBean ceBean = clinicalDataBean.getClinicalElements(systemNotesGWID);
		PatientElementBean patientElementBean=clinicalDataBean.getPatientElements(systemNotesGWID);
		if((ceBean!=null) && (ceBean.getClinicalElementDataType() == ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT)) {
			if(patientElementBean!=null){
				elements.add(getElementObject("systemNotes", "", systemNotesGWID, 2, patientElementBean.getPatientClinicalElementText(), null, null));
			}else{
				elements.add(getElementObject("systemNotes", "", systemNotesGWID, 2, "", null, null));
			}
		}


		//****************** Deferred **********************//

		List<PatientClinicalElements> deferredPatientData = patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinEleByGWIDPattern(encounterId, patientId, "%02001"+ prependZeros(systemBean.getSystemId(),2) +"%000000001"));
		String isDefchecked="-1";
		if(deferredPatientData.size()!=0){

			isDefchecked=deferredPatientData.get(0).getPatientClinicalElementsValue();
		}

		String deferredValue=null;
		List<ClinicalElementsOptions> defOption=clinicalElementsService.getClinicalElementOptions(systemBean.getSystemDeferredGWID());
		if(defOption.size()!=0){
			deferredValue=defOption.get(0).getClinicalElementsOptionsValue();
		}
		system.setSystemId(systemBean.getSystemId());
		system.setSystemName(systemBean.getSystemName());
		system.setSystemDeferredGWID(systemBean.getSystemDeferredGWID());
		if(deferredValue!=null){
			system.setDeferredValue(deferredValue);
		}else{
			system.setDeferredValue("2");
		}
		system.setIsDeferredChecked(isDefchecked);
		system.setSystemEandMType(systemBean.getSystemEandMType());
		system.setSystemChaperOne(systemBean.getChaperoneGWID());
		system.setPeElements(elements);

		return system;
	}


	private String prependZeros(int value, int totalDigits){

		String returnString = "" + value;
		if(value != -1) {
			for(int i=1; i<totalDigits; i++) {
				if(value < (10*i)) {
					returnString = "0" + returnString;
				}
			}
		}
		return returnString;
	}

	private String getExaminationSystemNotesGWID(int systemId){
		/* 000-02-001-##-000000000
		 * ## - systemId
		 * System Notes's GWID will bed in this format
		 */
		String examinationSystemNotesGWID = "00002001"+String.format("%02d",systemId)+"000000000";
		return examinationSystemNotesGWID;
	}

	private DetailOptions getDetailOptionObject(String optionName, String optionValue,int value) {
		DetailOptions option=new DetailOptions();
		option.setOptionName(optionName);
		option.setOptionValue(optionValue);
		option.setValue(value+"");
		return option;
	}

	private DetailOptions getDetailOptionObject(String optionName, String optionValue,String value,String bkgndimage,Integer dataType,String width,String height) {
		DetailOptions option=new DetailOptions();
		option.setOptionName(optionName);
		option.setOptionValue(optionValue);
		option.setValue(value);
		option.setBkgndimage(bkgndimage);
		option.setWidth(width);
		option.setHeight(height);
		option.setDataType(dataType);
		return option;
	}


	private ElementDetail getElementDetailObject(String detailOptionDisplayName,String detailOptionGWID, String detailOptionPrintText,Integer clinicalElementDataType, String value) {
		ElementDetail detailObj=new ElementDetail();
		detailObj.setElementDetailName(detailOptionDisplayName);
		detailObj.setElementGwid(detailOptionGWID);
		detailObj.setElementPrintText(detailOptionPrintText);
		detailObj.setDataType(clinicalElementDataType);
		detailObj.setValue(value);
		return detailObj;
	}


	private AssociateElement getAssociateObject(String elementName,String elementPrintText, int popup, String elementGwid,Integer clinicalElementDataType, String patientClinicalElementText) {
		AssociateElement associateObj=new AssociateElement();
		try{
			associateObj.setElementName(elementName);
			associateObj.setElementPrintText(elementPrintText);
			associateObj.setPopup(popup);
			associateObj.setElementGwid(elementGwid);
			associateObj.setDataType(clinicalElementDataType);
			associateObj.setValue(patientClinicalElementText);
		}catch(Exception e){
			e.printStackTrace();
		}
		return associateObj;
	}


	private ExaminationElement getElementObject(String elementName, String elementPrintText,String elementGWID, Integer clinicalElementDataType, String value,List<AssociateElement> associateArray,List<ElementDetail> elementDetails) {
		ExaminationElement elementObject=new ExaminationElement();
		try{
			elementObject.setElementName(elementName);
			elementObject.setElementPrintText(elementPrintText);
			elementObject.setElementGwid(elementGWID);
			elementObject.setDataType(clinicalElementDataType);
			elementObject.setValue(value);
			if(associateArray!=null)
				elementObject.setAssociate(associateArray);
			if(elementDetails!=null)
				elementObject.setElementDetails(elementDetails);
		}catch(Exception e){
			e.printStackTrace();
		}
		return elementObject;
	}


	public void getPatientDetails(Integer patientId){
		List<PatientRegistration> patRegistration=patientRegRepository.findAll(PatientRegistrationSpecification.getPatientPersonalDetails(patientId));
		for (PatientRegistration patientRegistration : patRegistration) {
			patientSex=patientRegistration.getPatientRegistrationSex().shortValue();
			patDOB=patientRegistration.getPatientRegistrationDob();
		}
		SimpleDateFormat mdyFormat = new SimpleDateFormat("MM/dd/yyyy");
		ageinDay = (int)((DateUtil.dateDiff( DateUtil.DATE , DateUtil.getDate(mdyFormat.format(patDOB)) , new java.util.Date() )));
	}



	public List<ClinicalElements> setClinicalDataBean(Integer patientId,Integer encounterId,Integer templateId,Integer tabType){
		return clinicalElementsService.setClinicalDataBean(patientId, encounterId, templateId, tabType,"02001%");

	}

	public CustomPESystem getExaminationSysBean(ClinicalSystem peSystem,String status){
		CustomPESystem system = new CustomPESystem();
		system.setSystemName(peSystem.getClinicalSystemName());
		system.setSystemId(peSystem.getClinicalSystemPeGwid());
		system.setSystemEandMType(peSystem.getClinicalSystemPeEandmtype());
		system.setSystemDeferredGWID(peSystem.getClinicalSystemPeDeferredGwid());
		system.setSystemChaperOne(peSystem.getClinicalSystemPeChaperoneGwid());
		system.setDocStatus(status);
		return system;
	}


	@Override
	public void markAllNormal(Integer patientId, Integer encounterId,Integer templateId,Integer chartId,String systemIds) {

		List<PatientClinicalElements> patElements=patientClinicalElementsRepository.findAll(ExaminationSpecification.getTemplatePEElements(encounterId, templateId));
		patientClinicalElementsRepository.deleteInBatch(patElements);

		getPatientDetails(patientId); 
		List<LeafLibrary> leafDetails=leafLibraryRepository.findAll(LeafLibrarySpecification.getLeafDetailsByTemplateId(templateId));
		List<LeafPatient> leafPatient=leafPatientRepository.findAll(LeafPatientSpecfication.getPatLeafByLeafIdAndEncId(leafDetails.get(0)==null?-1:leafDetails.get(0).getLeafLibraryId(),encounterId));

		boolean isAgeBased=leafDetails.get(0).getLeafLibraryIsagebased()==null?false:leafDetails.get(0).getLeafLibraryIsagebased();
		String leafCreatedDate=leafPatient.get(0).getLeafPatientCreatedDate()==null?"-1":leafPatient.get(0).getLeafPatientCreatedDate().toString();
		List<ClinicalElements> peElements=clinicalElementsRepository.findAll(ExaminationSpecification.getPEClinicalElements(templateId, patientSex, 5, leafCreatedDate, ageinDay, isAgeBased,new ArrayList<Integer>()));

		patElements.clear();
		for (ClinicalElements clinicalElement : peElements) {
			PatientClinicalElements element=new PatientClinicalElements();
			element.setPatientClinicalElementsChartid(chartId);
			element.setPatientClinicalElementsPatientid(patientId);
			element.setPatientClinicalElementsEncounterid(encounterId);
			element.setPatientClinicalElementsGwid(clinicalElement.getClinicalElementsGwid());
			element.setPatientClinicalElementsValue("false");
			patElements.add(element);
		}
		patientClinicalElementsRepository.save(patElements);


		List<Integer> ids=new ArrayList<Integer>();
		try {
			JSONArray sysIds=new JSONArray(systemIds);
			for(int i=0;i<sysIds.length();i++){
				ids.add(Integer.parseInt(sysIds.get(i).toString()));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		List<PeSystem> deferredActivePEsys=peSystemRepository.findAll(ExaminationSpecification.getActiveDeferredSys(ids));
		ids.clear();
		for (PeSystem peSystem : deferredActivePEsys) {
			ids.add(peSystem.getPeSystemId());
		}
		List<PeElementGroup> grps=peElementGroupRepository.findAll(ExaminationSpecification.getElementGrpDetailsBySysId(ids));
		ids.clear();
		for (PeElementGroup peElementGroup : grps) {
			ids.add(peElementGroup.getPeElementGroupId());
		}



		List<ClinicalElements> peDefElements=clinicalElementsRepository.findAll(ExaminationSpecification.getPEClinicalElements(templateId, patientSex, 5, leafCreatedDate, ageinDay, isAgeBased, ids));
		List<String> gwids=new ArrayList<String>();
		for (ClinicalElements clinicalElement : peDefElements) {
			gwids.add(clinicalElement.getClinicalElementsGwid());
		}

		//delete Deferred PE Elements
		patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinicalDataByGWID(patientId,encounterId,gwids)).size();
		patientClinicalElementsRepository.deleteInBatch(patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinicalDataByGWID(patientId, encounterId, gwids)));

		//insert Deferred system element for patient
		for (PeSystem sysIds : deferredActivePEsys) {
			PatientClinicalElements element=new PatientClinicalElements();
			element.setPatientClinicalElementsChartid(chartId);
			element.setPatientClinicalElementsPatientid(patientId);
			element.setPatientClinicalElementsEncounterid(encounterId);

			element.setPatientClinicalElementsGwid(sysIds.getPeSystemDeferredGwid());
			element.setPatientClinicalElementsValue("1");
			patientClinicalElementsRepository.save(element);
		}
	}


	@Override
	public void clearAllNormal(Integer patientId, Integer encounterId,Integer templateId, Integer chartId, String systemIds) {

		// delete all normal PE Elements of patient
		patientClinicalElementsRepository.deleteInBatch(patientClinicalElementsRepository.findAll(ExaminationSpecification.getNormalPEelemByEnc(encounterId, templateId)));

		List<Integer> ids=new ArrayList<Integer>();
		try {
			JSONArray sysIds=new JSONArray(systemIds);
			for(int i=0;i<sysIds.length();i++){
				ids.add(Integer.parseInt(sysIds.get(i).toString()));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}


		List<PeSystem> deferredActivePEsys=peSystemRepository.findAll(ExaminationSpecification.getActiveDeferredSys(ids));
		List<String> deferredSystemIds=new ArrayList<String>();
		for (PeSystem peSystem : deferredActivePEsys) {
			deferredSystemIds.add(peSystem.getPeSystemDeferredGwid());
		}
		// delete deferred elements for patient
		patientClinicalElementsRepository.deleteInBatch(patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinicalDataByGWID(patientId, encounterId, deferredSystemIds)));
	}


	@Override
	public String getQuickNotes(Integer tabId, String elementId) {
		List<SoapElementDatalist> dataList=soapElementDatalistRepository.findAll(ClinicalElementsSpecification.getSoapElementDataList(tabId, elementId));
		JSONArray dataListArray=new JSONArray();
		for (SoapElementDatalist soapElementDatalist : dataList) {
			JSONObject dataObj=new JSONObject();
			try {
				dataObj.put("datalistid",soapElementDatalist.getSoapElementDatalistId().toString());
				dataObj.put("data", soapElementDatalist.getSoapElementDatalistData());

				dataListArray.put(dataObj);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return dataListArray.toString();
	}




	@Override
	public String getPENotes(Integer patientId, Integer encounterId) {
		List<PatientClinicalElements> patientPeNotes=patientClinicalElementsRepository.findAll(PatientClinicalElementsSpecification.getPatClinEleByGWID(encounterId, patientId, "0000200100000000000"));
		JSONArray penotes = new JSONArray();
		try{
			JSONObject peNotesObject = new JSONObject();
			if(patientPeNotes.size()>0){
				if(patientPeNotes.get(0).getPatientClinicalElementsValue()==null){
					peNotesObject.put("value", "");
					peNotesObject.put("datatype", 2);
				}else{
					peNotesObject.put("value", patientPeNotes.get(0).getPatientClinicalElementsValue());
					peNotesObject.put("datatype", 2);
				}
			}else{
				peNotesObject.put("value", "");
				peNotesObject.put("datatype", 2);
			}
			penotes.put(peNotesObject);
		}catch(Exception e){
			e.printStackTrace();
		}
		return penotes.toString();
	}




	@Override
	public String addQuickNotes(Integer tabId, String elementId,String elementData) {
		SoapElementDatalist dataList=new SoapElementDatalist();
		dataList.setSoapElementDatalistTabid(tabId);
		dataList.setSoapElementDatalistElementid(elementId);
		dataList.setSoapElementDatalistData(elementData);
		dataList=soapElementDatalistRepository.save(dataList);
		JSONArray returnJson = new JSONArray();
		try{
			dataList.getSoapElementDatalistId();
			JSONObject jobj=new JSONObject();
			jobj.put("Key",0);
			jobj.put("message","Data added to list");
			returnJson.put(jobj);
			jobj=new JSONObject();
			jobj.put("Key",1);
			jobj.put("message",dataList.getSoapElementDatalistId());
			returnJson.put(jobj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnJson.toString();
	}

	@Override
	public String deleteQuickNotes(Integer dataListId) {
		List<SoapElementDatalist> datList=soapElementDatalistRepository.findAll(ClinicalElementsSpecification.getSoapElementDataListById(dataListId));
		soapElementDatalistRepository.delete(datList.get(0));
		return "REMOVED";
	}

	/**
	 * Cleaning clinical data bean
	 */
	private void clearClinicalData() {
		try{
			clinicalDataBean.getPatientElements().clear();
			clinicalDataBean.getClinicalElements().clear();
			clinicalDataBean.getPatientEpisodeElements().clear();
			clinicalDataBean.getPatientHistoryElements().clear();
			clinicalDataBean.getElementsToDelete().clear();
			clinicalDataBean.getElementsToSave().clear();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
