package com.glenwood.glaceemr.server.application.services.chart.vitals;



import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.ClinicalElements;
import com.glenwood.glaceemr.server.application.models.ClinicalElementsOptions;
import com.glenwood.glaceemr.server.application.models.ClinicalTextMapping;
import com.glenwood.glaceemr.server.application.models.Encounter;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements;
import com.glenwood.glaceemr.server.application.models.PatientClinicalElements_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PatientVitals;
import com.glenwood.glaceemr.server.application.models.UnitsOfMeasure;
import com.glenwood.glaceemr.server.application.models.VitalGroup;
import com.glenwood.glaceemr.server.application.models.VitalsParameter;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientVitalsRepository;
//import com.glenwood.glaceemr.server.application.repositories.ServiceDetailRepository;
import com.glenwood.glaceemr.server.application.repositories.UnitsOfMeasureRepository;
import com.glenwood.glaceemr.server.application.repositories.VitalGroupRepository;
import com.glenwood.glaceemr.server.application.repositories.VitalsParameterRepository;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalConstants;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalDataBean;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalElementBean;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.ClinicalElementsService;
import com.glenwood.glaceemr.server.application.services.chart.clinicalElements.PatientElementBean;
import com.glenwood.glaceemr.server.application.services.chart.dischargeVitals.DischargeVitalBean;
import com.glenwood.glaceemr.server.application.services.chart.print.TextFormatter;
import com.glenwood.glaceemr.server.application.specifications.DischargeVitalSpecification;
import com.glenwood.glaceemr.server.application.specifications.EncounterEntitySpecification;
import com.glenwood.glaceemr.server.application.specifications.PatientClinicalElementsSpecification;
import com.glenwood.glaceemr.server.application.specifications.VitalsSpecification;
import com.glenwood.glaceemr.server.utils.DateUtil;
import com.glenwood.glaceemr.server.utils.HUtil;
import com.google.common.base.Optional;

@Service
public class VitalsServiceImpl implements VitalsService {


	@Autowired 
	VitalGroupRepository vitalGroupRepository;
	@Autowired
	VitalsParameterRepository vitalParameterRepository;
	@Autowired 
	PatientRegistrationRepository patientRegRepository;
	@Autowired
	ClinicalElementsService clinicalElementsService;
	@Autowired
	UnitsOfMeasureRepository unitsOfMeasureRepository;
	@Autowired
	VitalDataBean vitalDataBean;
	@Autowired
	ClinicalDataBean clinicalDataBean;
	@Autowired
	PatientVitalsRepository patientVitalsRepository;
	@Autowired
	TextFormatter textFormat;
	@Autowired
	EntityManager em;
	
	List<String> gwids=new ArrayList<String>();
	List<VitalGroup> vitalGroupList=null;
	String currentheightvalue="";
	String currentweightvalue="";
	Short patientSex=0;
	Date patDOB=null;
	Integer ageinDay=0;
	Integer ageinYears=0;
	Integer fromPrint=0;
	
	@Override
	public DischargeVitalBean setVitals(Integer patientId,Integer encounterId,Integer groupId,Boolean isDischargeVitals,Integer admssEpisode,String clientId,Integer fromPrint)throws Exception{
		try{
			this.fromPrint=fromPrint;
			getPatientDetails(patientId);
			clearClinicalData();
			clinicalDataBean.clientId=clientId;
			getActiveVitalsGroup(patientId,groupId);
			for (VitalGroup vitalGroup : vitalGroupList) {
				getGroupVitals(patientId,encounterId,vitalGroup.getVitalGroupId());
			}
			setClinicalDataBean(patientId,encounterId,isDischargeVitals,admssEpisode);
			return prepareJsonfromBeans(patientId,encounterId,admssEpisode,patientSex,ageinDay);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}


	}

	@Override
	public List<VitalGroup> getActiveVitalsGroup(Integer patientId,Integer groupId)throws Exception {
		try{
			patientId = Integer.parseInt(Optional.fromNullable(patientId+"").or("-1"));
			getPatientDetails(patientId);
			vitalGroupList =vitalGroupRepository.findAll(Specifications.where(VitalsSpecification.getVitalActiveGrps(patientId,groupId,patientSex)));
			VitalGroup notesGroup= new VitalGroup();
			notesGroup.setVitalGroupId(-999);
			notesGroup.setVitalGroupName("Comments");
			vitalGroupList.add(notesGroup);
			vitalDataBean.setVitalGroupData(vitalGroupList);
			return vitalGroupList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}



	public void getPatientDetails(Integer patientId){
		try{
			/*List<PatientRegistration> patRegistration=patientRegRepository.findAll(PatientRegistrationSpecification.getPatientPersonalDetails(patientId));
			for (PatientRegistration patientRegistration : patRegistration) {
				patientSex=patientRegistration.getPatientRegistrationSex().shortValue();
				patDOB=patientRegistration.getPatientRegistrationDob();
			}
			
					SimpleDateFormat mdyFormat = new SimpleDateFormat("MM/dd/yyyy");
			ageinDay = (int)((DateUtil.dateDiff( DateUtil.DATE , DateUtil.getDate(mdyFormat.format(patDOB)) , new java.util.Date() )));
			*/

			CriteriaBuilder builder= em.getCriteriaBuilder();
			CriteriaQuery<Object[]> query= builder.createQuery(Object[].class);
			Root<PatientRegistration> root= query.from(PatientRegistration.class); 

			query.multiselect(root.get(PatientRegistration_.patientRegistrationDob), 
					root.get(PatientRegistration_.patientRegistrationSex)/*,
					builder.function("age", String.class, root.get(PatientRegistration_.patientRegistrationDob))*/);
			query.where(builder.equal(root.get(PatientRegistration_.patientRegistrationId), patientId));
			List<Object[]> list= em.createQuery(query).getResultList();

			if(list!= null && list.size()>0){
				patDOB= (Date)list.get(0)[0];
				try{
					patientSex= Short.parseShort(list.get(0)[1]+"");
				}catch(Exception e){
					patientSex= -1;
				}
				ageinYears= textFormat.getAgeInYear(textFormat.convertDate(patDOB, "yyyy-MM-dd", "MM/dd/yyyy"));
				
				SimpleDateFormat mdyFormat = new SimpleDateFormat("MM/dd/yyyy");
				ageinDay = (int)((DateUtil.dateDiff( DateUtil.DATE , DateUtil.getDate(mdyFormat.format(patDOB)) , new java.util.Date())));
			}

		}catch(Exception e){
			System.out.println("Exception while getting patient details for vitals");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<VitalsParameter> getGroupVitals(Integer patientId,Integer encounterId, Integer groupId) throws Exception {
		try{
		List<VitalsParameter> vitalsList=vitalParameterRepository.findAll(VitalsSpecification.getVitalElements(patientSex,groupId));
		for (VitalsParameter vitalsParameter : vitalsList) {
			gwids.add(vitalsParameter.getVitalsParameterGwId());
		}
		gwids.add("-1");
		vitalDataBean.setVitalElementBean(vitalsList,groupId);
		return vitalsList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


	private void setClinicalDataBean(Integer patientId,Integer encounterId,Boolean isDischargeVitals,Integer admssEpisode) {
		try{
			clinicalElementsService.setVitalsClinicalData("02002%",patientId,encounterId,isDischargeVitals,admssEpisode,patientSex, ageinDay);
		}catch(Exception e){
			e.printStackTrace();
		}
	}





	private DischargeVitalBean prepareJsonfromBeans(Integer patientId, Integer encounterId,Integer admssEpisode,short patientSex,int patientAge) throws Exception {
		try{
			List<CustomVitalGroup> vitalData=new ArrayList<CustomVitalGroup>();
			int groupId = -1;
			LinkedHashMap<Integer,VitalGroupBean> vitalGroupHashMap = vitalDataBean.getVitals();
			Set<Entry<Integer,VitalGroupBean>> GroupHashMap = vitalGroupHashMap.entrySet();
			String isOrthoNotesActive = "";
			String isRepeatpulNotesActive = "";
			for(Entry<Integer,VitalGroupBean> currentGroup:GroupHashMap){
				CustomVitalGroup vitalGrpInfo=new CustomVitalGroup();
				groupId   = currentGroup.getKey();
				VitalGroupBean vitalGroupBean = currentGroup.getValue();
				String elementGWId = "0000000000000000000";
				LinkedHashMap<String,VitalElementBean>  vitalHashMap = vitalGroupBean.getVitalElements();
				Set<Entry<String, VitalElementBean>> ElementHashMap =  vitalHashMap.entrySet();
				if(!vitalGroupBean.getGroupName().equals("Others")){
					if(ElementHashMap.size()!=0){
						vitalGrpInfo.setVitalGroupName(vitalGroupBean.getGroupName());
						vitalGrpInfo.setAge(patientAge/365);
						vitalGrpInfo.setSex(patientSex);
						vitalGrpInfo.setGroupId(groupId);
						for(Entry<String,VitalElementBean> currentElement:ElementHashMap){
							VitalElementBean  elementBean = currentElement.getValue();
							String vitalName = HUtil.ValidateSpecialQuote(elementBean.getVitalName());
							String elementId = currentElement.getKey();
							elementGWId = elementBean.getVitalGWId();



							ClinicalElementBean ceBean = clinicalDataBean.getClinicalElements(elementGWId);
							PatientElementBean patientElementBean=clinicalDataBean.getPatientElements(elementGWId);
							String vitaltype = "";
							if(vitalName.contains("BP")){
								vitaltype = "BP";
							}else if(vitalName.equals("Height")){
								vitaltype = "height";
							}else if(vitalName.equals("Weight")){
								vitaltype = "weight";
							}else if(vitalName.equals("BMI")){
								vitaltype = "BMI";
							}else if(vitalName.equals("BSA")){
								vitaltype = "BSA";
							}else if(vitalName.contains("Pulse")){
								vitaltype = "pulse";
							}else if(vitalName.contains("Temperature")){
								vitaltype = "temperature";
							}else if(vitalName.equals("Head circumference")){
								vitaltype = "Headcircumference";
							}else if(vitalName.equals("Last Phy-V")){
								vitaltype = "Last Phy-V";
							}else if(vitalName.equals("Neck circumference")){
								vitaltype = "Neckcircumference";
							}else if(elementBean.getVitalName().equals("Weight change")){
								vitaltype = "WeightChanges";
							}else if(vitalName.contains("Others")){
								vitaltype = "others";
							}
							vitalGrpInfo.setVitalType(vitaltype);
							if(vitaltype.equalsIgnoreCase("BP")){
								if(vitalName.contains("Systolic") || vitalName.contains("systolic")){
									String elementAssociatGWId = ceBean.getClinicalTextMappings().size()>0 ?ceBean.getClinicalTextMappings().get(0).getClinicalTextMappingAssociatedElement()+"":"";
									VitalElementBean vitalAssociatedBean = null;
									for(Entry<String,VitalElementBean> currentAssociateElement:ElementHashMap){
										if(currentAssociateElement.getKey().equals(elementAssociatGWId)){
											vitalAssociatedBean = currentAssociateElement.getValue();
										}
									}
									ClinicalElementBean ceAssociateBean            = clinicalDataBean.getClinicalElements(elementAssociatGWId);
									PatientElementBean patientAssociateElementBean = clinicalDataBean.getPatientElements(elementAssociatGWId);
									String groupName ="";
									if(vitalAssociatedBean!=null && (elementBean.getVitalGender() == patientSex || elementBean.getVitalGender() == 0)){
										if(vitalName.contains("Systolic")){
											groupName = vitalName.substring(0, vitalName.indexOf("Systolic"))+"BP";
										}else if(vitalName.contains("systolic")){
											groupName = vitalName.substring(0, vitalName.indexOf("systolic"))+"BP";
										}
										if(ceBean!=null && ceAssociateBean!=null ){
											ElementDetails elementDet = prepareElement(clinicalDataBean,elementBean,patientElementBean,elementId,elementGWId,vitaltype,ceBean.getClinicalElementDataType(),patientId,encounterId);	

											for(int i=0;i<elementDet.associatedElements.size();i++){
												elementDet.associatedElements.get(i).elementName=groupName;
											}

											vitalGrpInfo.unitgroupdetails.add(elementDet);

											ElementDetails associate = prepareElement(clinicalDataBean, vitalAssociatedBean,patientAssociateElementBean,elementAssociatGWId,elementAssociatGWId,vitaltype,ceBean.getClinicalElementDataType(),patientId,encounterId);


											for (AssociatedElementDetails assoElement : associate.getAssociatedElements()) {
												elementDet.getAssociatedElements().add(assoElement);
											}

											//vitalGrpInfo.unitgroupdetails.add(associate);
										}
									}
								}
							}
							else{
								if(ceBean!=null){
									if(elementBean.getVitalGender() == patientSex || elementBean.getVitalGender() == 0){
										ElementDetails elementDet = prepareElement(clinicalDataBean,elementBean,patientElementBean,elementId,elementGWId,vitaltype,ceBean.getClinicalElementDataType(),patientId,encounterId);
										vitalGrpInfo.unitgroupdetails.add(elementDet);

									}
								}
							}
						}
						if(isOrthoNotesActive.equalsIgnoreCase(""))
							isOrthoNotesActive = clinicalElementsService.isClinicalElemActive("0000200200100201000")+"";
						String elementValue="";
						if(vitalGroupBean.getGroupName().contains("BP / Respiratory") && isOrthoNotesActive.equalsIgnoreCase("true")){
							ElementDetails elemDetails=new ElementDetails();
							PatientElementBean patientElementBeanBPNotes=clinicalDataBean.getPatientElements("0000200200100201000");
							if(patientElementBeanBPNotes!=null)
								elementValue=HUtil.Nz(patientElementBeanBPNotes.getPatientClinicalElementText(),"");
							elemDetails.associatedElements.add(getAssoElement("","","Repeat BP","","",0,"","",2,"0000200200100201000",elementValue,"",0,""));
							vitalGrpInfo.unitgroupdetails.add(elemDetails);
						}
						if(isRepeatpulNotesActive.equalsIgnoreCase(""))
							isRepeatpulNotesActive = clinicalElementsService.isClinicalElemActive("0000200200100236000")+"";
						elementValue="";
						if(vitalGroupBean.getGroupName().contains("Pulse / Temperature") && isRepeatpulNotesActive.equalsIgnoreCase("true")){
							PatientElementBean patientElementBeanPulseNotes=clinicalDataBean.getPatientElements("0000200200100236000");
							if(patientElementBeanPulseNotes!=null)
								elementValue=HUtil.Nz(patientElementBeanPulseNotes.getPatientClinicalElementText(),"");

						}

					}
				}else{
					if(ElementHashMap.size()!=0){

						vitalGrpInfo.setVitalGroupName(vitalGroupBean.getGroupName());
						vitalGrpInfo.setAge(patientAge);
						vitalGrpInfo.setSex(patientSex);
						vitalGrpInfo.setGroupId(groupId);
						for(Entry<String,VitalElementBean> currentElement:ElementHashMap){
							VitalElementBean  elementBean = currentElement.getValue();
							String elementId = currentElement.getKey();
							elementGWId = elementBean.getVitalGWId();
							ClinicalElementBean ceBean = clinicalDataBean.getClinicalElements(elementGWId);
							PatientElementBean patientElementBean=clinicalDataBean.getPatientElements(elementGWId);
							String vitaltype = "others";
							if(ceBean!=null){
								if(elementBean.getVitalGender() == patientSex || elementBean.getVitalGender() == 0){
									ElementDetails elementDet = prepareElement(clinicalDataBean,elementBean,patientElementBean,elementId,elementGWId,vitaltype,ceBean.getClinicalElementDataType(),patientId,encounterId);
									vitalGrpInfo.unitgroupdetails.add(elementDet);
								}
							}

						}

					}
				}

				vitalData.add(vitalGrpInfo);
			}

			/*boolean notes = clinicalElementsService.isClinicalElemActive("0000200200100198000");
			String elementValue="";
			if(notes){
				PatientElementBean patientElementBeanVitalNotes=clinicalDataBean.getPatientElements("0000200200100198000");
				if(patientElementBeanVitalNotes!=null)
					elementValue=HUtil.Nz(patientElementBeanVitalNotes.getPatientClinicalElementText(),"");
			}*/

			DischargeVitalBean dischargeVitalBean = new DischargeVitalBean();
			dischargeVitalBean.setVitalData(framePatientData(patientId,encounterId,admssEpisode));
			dischargeVitalBean.setVitals(vitalData);
			return dischargeVitalBean;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


	private Map<String, HashMap<String, String>> framePatientData(Integer patientId,Integer encounterId,Integer admssEpisode) {
		List<PatientVitals> patientVitals = null;
		if(fromPrint==0)
			patientVitals=patientVitalsRepository.findAll(DischargeVitalSpecification.getDichargeVitalsByEncounterId(patientId, encounterId, admssEpisode));
		else
			patientVitals=patientVitalsRepository.findAll(DischargeVitalSpecification.getDichargeVitalsByPatientId(patientId, encounterId, admssEpisode));
		Map<String, HashMap<String, String>> vitalData = new HashMap<String, HashMap<String,String>>();
		
		boolean weightVisited = false;
		boolean heightVisited = false;
		boolean tempVisited = false;
		boolean headCirVisited = false;
		
		for (PatientVitals patientVital : patientVitals) {
			
			// Height 
			if(patientVital.getPatientVitalsGwid().equals("0000200200100023000")){
				String tempValue = HUtil.heightConversion(patientVital.getPatientVitalsValue(),"display");
				String[] heightValue = new String[4];
				heightValue[0]=heightValue[1]=heightValue[2]=heightValue[3]=tempValue;
				try{
					if(tempValue.contains("-"))
						heightValue = tempValue.split("-");
				}catch(Exception e){}
				
				HashMap<String, String> dataMap = null;
				
				
				if(!heightVisited){
					dataMap = new HashMap<String, String>();
					dataMap.put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), heightValue[0]);
					vitalData.put(patientVital.getPatientVitalsGwid()+"_inFt", dataMap);
					
					
					
					dataMap = new HashMap<String, String>();
					dataMap.put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), heightValue[1]);
					vitalData.put(patientVital.getPatientVitalsGwid()+"_inFtin", dataMap);
					
					dataMap = new HashMap<String, String>();
					dataMap.put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), heightValue[2]);
					vitalData.put(patientVital.getPatientVitalsGwid()+"_ininches", dataMap);
					
					dataMap = new HashMap<String, String>();
					dataMap.put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), heightValue[3]);
					vitalData.put(patientVital.getPatientVitalsGwid()+"_incms", dataMap);
				
					dataMap = new HashMap<String, String>();
					dataMap.put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), heightValue[3]);
					vitalData.put(patientVital.getPatientVitalsGwid(), dataMap);
					heightVisited = true;
				
				}
				else{
					vitalData.get(patientVital.getPatientVitalsGwid()+"_inFt").put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), heightValue[0]);
					
					vitalData.get(patientVital.getPatientVitalsGwid()+"_inFtin").put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), heightValue[1]);
					vitalData.get(patientVital.getPatientVitalsGwid()+"_ininches").put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), heightValue[2]);
					vitalData.get(patientVital.getPatientVitalsGwid()+"_incms").put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), heightValue[3]);
					vitalData.get(patientVital.getPatientVitalsGwid()).put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), heightValue[3]);
				}
				
			}
			// weight
			else if (patientVital.getPatientVitalsGwid().equals("0000200200100024000")){
				String tempValue = HUtil.weightConversion(patientVital.getPatientVitalsValue(),"display");
				String[] weightValue = new String[4];
				weightValue[0]=weightValue[1]=weightValue[2]=weightValue[3]=tempValue;
				try{
					if(tempValue.contains("-"))
					weightValue = tempValue.split("-");
				}catch(Exception e){}
				
				HashMap<String, String> dataMap = null;
				
				
				if(!weightVisited){
					dataMap = new HashMap<String, String>();
					dataMap.put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), weightValue[0]);
					vitalData.put(patientVital.getPatientVitalsGwid()+"_inlbs", dataMap);
					
					
					
					dataMap = new HashMap<String, String>();
					dataMap.put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), weightValue[1]);
					vitalData.put(patientVital.getPatientVitalsGwid()+"_inLbsOz", dataMap);
					
					dataMap = new HashMap<String, String>();
					dataMap.put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), weightValue[2]);
					vitalData.put(patientVital.getPatientVitalsGwid()+"_inounce", dataMap);
					
					dataMap = new HashMap<String, String>();
					dataMap.put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), weightValue[3]);
					vitalData.put(patientVital.getPatientVitalsGwid()+"_inkg", dataMap);
				
					dataMap = new HashMap<String, String>();
					dataMap.put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), weightValue[3]);
					vitalData.put(patientVital.getPatientVitalsGwid(), dataMap);
					
					weightVisited = true;
				}
				else{
					vitalData.get(patientVital.getPatientVitalsGwid()+"_inlbs").put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), weightValue[0]);
					
					vitalData.get(patientVital.getPatientVitalsGwid()+"_inLbsOz").put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), weightValue[1]);
					vitalData.get(patientVital.getPatientVitalsGwid()+"_inounce").put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), weightValue[2]);
					vitalData.get(patientVital.getPatientVitalsGwid()+"_inkg").put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), weightValue[3]);
					vitalData.get(patientVital.getPatientVitalsGwid()).put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), weightValue[3]);
				}
				
			}
			// temperature
			else if ( patientVital.getPatientVitalsGwid().equals("0000200200100022000")){
				DecimalFormat f = new DecimalFormat("#0.#");
				String elementCelciusValue="";
				try{
					double elementFloatValue = Double.parseDouble(patientVital.getPatientVitalsValue());
					elementCelciusValue= HUtil.Nz(f.format(((float)((int)((elementFloatValue-32)*((float)5/9)*100))/100)),"");
				}catch(Exception e){
					elementCelciusValue=patientVital.getPatientVitalsValue();
				}
				
				HashMap<String, String> dataMap = null;
				if(!tempVisited){
					dataMap = new HashMap<String, String>();
					dataMap.put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), patientVital.getPatientVitalsValue());
					vitalData.put(patientVital.getPatientVitalsGwid(), dataMap);
				
					dataMap = new HashMap<String, String>();
					dataMap.put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), elementCelciusValue);
					vitalData.put(patientVital.getPatientVitalsGwid()+"_celcius", dataMap);
					
					tempVisited= true;
				}else{
					vitalData.get(patientVital.getPatientVitalsGwid()).put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), patientVital.getPatientVitalsValue());
					vitalData.get(patientVital.getPatientVitalsGwid()+"_celcius").put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), elementCelciusValue);
				}
			}
			//Head Circumference
			else if (patientVital.getPatientVitalsGwid().equals("0000200200100039000")){
				DecimalFormat f = new DecimalFormat("#0.##");
				String elementInchesValue="";
				try{
					double elementFloatValue = Double.parseDouble(patientVital.getPatientVitalsValue());
					elementInchesValue= HUtil.Nz(f.format(elementFloatValue*0.393700787),"");
				}catch(Exception e){
					elementInchesValue=patientVital.getPatientVitalsValue();
				}
				
				HashMap<String, String> dataMap = null;
				
				if(!headCirVisited){
					dataMap = new HashMap<String, String>();
					dataMap.put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), patientVital.getPatientVitalsValue());
					vitalData.put(patientVital.getPatientVitalsGwid(), dataMap);
				
					dataMap = new HashMap<String, String>();
					dataMap.put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), elementInchesValue);
					vitalData.put(patientVital.getPatientVitalsGwid()+"_ininches", dataMap);
					
					headCirVisited = true;
				}else{
					vitalData.get(patientVital.getPatientVitalsGwid()).put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), patientVital.getPatientVitalsValue());
					vitalData.get(patientVital.getPatientVitalsGwid()+"_ininches").put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), elementInchesValue);
				}
			}
			else {
				if(vitalData.containsKey(patientVital.getPatientVitalsGwid())){
					vitalData.get(patientVital.getPatientVitalsGwid()).put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), patientVital.getPatientVitalsValue());
				}else{
					HashMap<String, String> dataMap = new HashMap<String, String>();
					dataMap.put(patientVital.getPatientVitalsDateOfRecording().toString()+"_"+patientVital.getPatientVitalsTimeOfRecording().toString().split(" ")[1]+"_"+patientVital.getPatientVitalsEncounterid(), patientVital.getPatientVitalsValue());
					vitalData.put(patientVital.getPatientVitalsGwid(), dataMap);
				}
			}
			
			
		}
		
		return vitalData;
	}

	public ElementDetails prepareElement(ClinicalDataBean clinicalDataBean, VitalElementBean elementBean,PatientElementBean patientElementBean, String elementId, String elementGWId, String vitaltype, int elementtype,int patientId,int encounterId)throws Exception{

		//String unit=getUnitName(elementBean.getVitalUnit())!=null?getUnitName((elementBean.getVitalUnit())).getUnitsOfMeasureCode():"";
		String unit=elementBean.getUnitsOfMeasureCode();
		unit=(unit == null || unit.equalsIgnoreCase("N/A"))?"":unit;
		String vitalName = HUtil.ValidateSpecialQuote(elementBean.getVitalName());
//		int elementIdInteger;

		ElementDetails elemDetails=new ElementDetails();

		if(vitaltype.equalsIgnoreCase("weight") && elementtype==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT){
			if(patientElementBean!=null){

				String elementValue=patientElementBean.getPatientClinicalElementText();
				currentweightvalue=elementValue;
				String tempValue = HUtil.weightConversion(elementValue,"display");
				String[] weightValue = new String[4];
				weightValue[0]=weightValue[1]=weightValue[2]=weightValue[3]=tempValue;
				try{
					if(tempValue.contains("-"))
						weightValue = tempValue.split("-");
				}catch(Exception e){

				}
				if(elementValue!=""){

					elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightlbshidden","Weight","kg",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"1","kg",2,elementGWId,elementValue,"1",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightlbs","Weight","lbs","",-1,"01","lbs",2,elementGWId,weightValue[0],"",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightlbsoz","Weight","oz","",-1,"01","lbsoz",2,elementGWId,weightValue[1],"",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightounce","Weight","oz","",-1,"0","ounce",2,elementGWId,weightValue[2],"",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightkg","Weight","Kg","",-1,"0","kg",2,elementGWId,weightValue[3],"",-1,""));
					
					if(! isVitalElemActive("0000200200100206000") && clinicalElementsService.isClinicalElemActive("0000200200100206000")){
						ClinicalElements clinElement=null;
						String isSelect=(clinElement=clinicalElementsService.getClinicalElement("0000200200100206000"))!=null?clinElement.getClinicalElementsIsselect()+"":"-1";
						AssociatedElementDetails asoElemDet=getAssoElement("","","","","",-1,"","",4,"0000200200100206000","","",-1,isSelect);
						asoElemDet.detailOptions=constructJSON("0000200200100206000",clinicalDataBean,elementBean);
						elemDetails.associatedElements.add(asoElemDet);
					}
					//vitalGrpInfo.unitgroupdetails.add(elemDetails);

				}else{
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightlbshidden","Weight","kg",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"1","kg",2,elementGWId,"","1",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightlbs","Weight","lbs",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"01","lbs",2,elementGWId,weightValue[0],"",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightlbsoz","Weight","oz",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"01","lbsoz",2,elementGWId,weightValue[1],"",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightounce","Weight","oz","",-1,"0","ounce",2,elementGWId,weightValue[2],"",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightkg","Weight","Kg","",-1,"0","kg",2,elementGWId,weightValue[3],"",-1,""));
					
					if(! isVitalElemActive("0000200200100206000") && clinicalElementsService.isClinicalElemActive("0000200200100206000")){
						ClinicalElements clinElement=null;
						String isSelect=(clinElement=clinicalElementsService.getClinicalElement("0000200200100206000"))!=null?clinElement.getClinicalElementsIsselect()+"":"-1";
						AssociatedElementDetails asoElemDet=getAssoElement("","","","","",-1,"","",4,"0000200200100206000","","",-1,isSelect);
						asoElemDet.detailOptions=constructJSON("0000200200100206000",clinicalDataBean,elementBean);
						elemDetails.associatedElements.add(asoElemDet);
					}
				}
			}else{
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightlbshidden","Weight","kg",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"1","kg",2,elementGWId,"","1",-1,""));
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightlbs","Weight","lbs",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"01","lbs",2,elementGWId,"","",-1,""));
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightlbsoz","Weight","oz",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"01","lbsoz",2,elementGWId,"","",-1,""));
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightounce","Weight","oz","",-1,"0","ounce",2,elementGWId,"","",-1,""));
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightkg","Weight","kg","",-1,"0","kg",2,elementGWId,"","",-1,""));
				
				if(! isVitalElemActive("0000200200100206000") && clinicalElementsService.isClinicalElemActive("0000200200100206000")){
					ClinicalElements clinElement=null;
					String isSelect=(clinElement=clinicalElementsService.getClinicalElement("0000200200100206000"))!=null?clinElement.getClinicalElementsIsselect()+"":"-1";
					AssociatedElementDetails asoElemDet=getAssoElement("","","","","",-1,"","",4,"0000200200100206000","","",-1,isSelect);
					asoElemDet.detailOptions=constructJSON("0000200200100206000",clinicalDataBean,elementBean);
					elemDetails.associatedElements.add(asoElemDet);
				}
			}
		}else if(vitaltype.equalsIgnoreCase("height") && elementtype==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT){

			if(patientElementBean!=null){
				String elementValue=patientElementBean.getPatientClinicalElementText();
				String tempValue = HUtil.heightConversion(elementValue,"display");
				String[] heightValue = new String[4];
				heightValue[0]=heightValue[1]=heightValue[2]=heightValue[3]=tempValue;
				try{
					if(tempValue.contains("-"))
						heightValue = tempValue.split("-");
					currentheightvalue=heightValue[2];
				}catch(Exception e){
				}
				if(elementValue!=""){
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"heightfthidden","Height","cm",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"1","",2,elementGWId,elementValue,"1",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"heightft","Height","'","",-1,"01","ft",2,elementGWId,heightValue[0],"0",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"heightftin","Height","''","",-1,"01","ftin",2,elementGWId,heightValue[1],"0",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"heightinches","Height","in","",-1,"0","inches",2,elementGWId,heightValue[2],"0",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"heightcm","Height","cm","",-1,"0","cms",2,elementGWId,heightValue[3],"0",-1,""));
					
					if(! isVitalElemActive("0000200200100205000") && clinicalElementsService.isClinicalElemActive("0000200200100205000")){
						ClinicalElements clinElement=null;
						String isSelect=(clinElement=clinicalElementsService.getClinicalElement("0000200200100205000"))!=null?clinElement.getClinicalElementsIsselect()+"":"-1";
						AssociatedElementDetails asoElemDet=getAssoElement("","","","","",-1,"","",4,"0000200200100205000","","",-1,isSelect);
						asoElemDet.detailOptions=constructJSON("0000200200100205000",clinicalDataBean,elementBean);
						elemDetails.associatedElements.add(asoElemDet);
					}
				}else{

					elemDetails.associatedElements.add(getAssoElement(vitaltype,"heightfthidden","Height","cm",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"1","",2,elementGWId,elementValue,"1",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"heightft","Height","'",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"01","ft",2,elementGWId,heightValue[0],"0",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"heightft","Height","''",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"01","ftin",2,elementGWId,heightValue[1],"0",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"heightinches","Height","in","",-1,"0","inches",2,elementGWId,heightValue[2],"0",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"heightcm","Height","cm","",-1,"0","cms",2,elementGWId,heightValue[3],"0",-1,""));
					
					if(!isVitalElemActive("0000200200100205000") && clinicalElementsService.isClinicalElemActive("0000200200100205000")){
						ClinicalElements clinElement=null;
						String isSelect=(clinElement=clinicalElementsService.getClinicalElement("0000200200100205000"))!=null?clinElement.getClinicalElementsIsselect()+"":"-1";
						AssociatedElementDetails asoElemDet=getAssoElement("","","","","",-1,"","",4,"0000200200100205000","","",-1,isSelect);
						asoElemDet.detailOptions=constructJSON("0000200200100205000",clinicalDataBean,elementBean);
						elemDetails.associatedElements.add(asoElemDet);
					}
				}
			}else{
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"heightfthidden","Height","cm",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"1","",2,elementGWId,"","1",-1,""));
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"heightft","Height","'",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"01","ft",2,elementGWId,"","0",-1,""));
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"heightftin","Height","''",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"01","ftin",2,elementGWId,"","0",-1,""));
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"heightinches","Height","in","",-1,"0","inches",2,elementGWId,"","0",-1,""));
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"heightcm","Height","cm","",-1,"0","cms",2,elementGWId,"","0",-1,""));
				
				if(!isVitalElemActive("0000200200100205000") && clinicalElementsService.isClinicalElemActive("0000200200100205000")){
					ClinicalElements clinElement=null;
					String isSelect=(clinElement=clinicalElementsService.getClinicalElement("0000200200100205000"))!=null?clinElement.getClinicalElementsIsselect()+"":"-1";
					AssociatedElementDetails asoElemDet=getAssoElement("","","","","",-1,"","",4,"0000200200100205000","","",-1,isSelect);
					asoElemDet.detailOptions=constructJSON("0000200200100205000",clinicalDataBean,elementBean);
					elemDetails.associatedElements.add(asoElemDet);

				}
			}


		}else if(vitaltype.equalsIgnoreCase("temperature") && elementtype==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT){

			DecimalFormat f = new DecimalFormat("#0.#");
			if(patientElementBean!=null){
				String elementCelciusValue="";
				String elementValue=patientElementBean.getPatientClinicalElementText();
				try{
					double elementFloatValue = Double.parseDouble(elementValue);
					elementCelciusValue= HUtil.Nz(f.format(((float)((int)((elementFloatValue-32)*((float)5/9)*100))/100)),"");
				}catch(Exception e){
					elementCelciusValue=elementValue;
				}
				if(elementValue!=""){
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"tempFH",vitalName,"°F",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"1","",2,elementGWId,elementValue,"",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"tempcels",vitalName,"°C",elementBean.getVitalCondition(),0,"0","",2,elementGWId,elementCelciusValue,"",-1,""));
				}else{
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"tempFH",vitalName,"°F",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"1","",2,elementGWId,"","",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"tempcels",vitalName,"°C",elementBean.getVitalCondition(),0,"0","celcius",2,elementGWId,"","",-1,""));
				}
			}else{
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"tempFH",vitalName,"°F",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"1","",2,elementGWId,"","",-1,""));
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"tempcels",vitalName,"°C",elementBean.getVitalCondition(),0,"0","celcius",2,elementGWId,"","",-1,""));

			}
		}else if((vitaltype.equalsIgnoreCase("Headcircumference") || vitaltype.equalsIgnoreCase("Neckcircumference") )&& elementtype==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT){
			DecimalFormat f = new DecimalFormat("#0.##");
			if(patientElementBean!=null){
				String elementInchesValue="";
				String elementValue=patientElementBean.getPatientClinicalElementText();
				try{
					double elementFloatValue = Double.parseDouble(elementValue);
					elementInchesValue= HUtil.Nz(f.format(elementFloatValue*0.393700787),"");
				}catch(Exception e){
					elementInchesValue=elementValue;
				}
				if(elementValue!=""){
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"headcm",vitalName,"cm",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"0","",2,elementGWId,elementValue,"",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"headinch",vitalName,"inches",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"0","inches",2,elementGWId,elementInchesValue,"",-1,""));
				}else{
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"headcm",vitalName,"cm",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"0","",2,elementGWId,"","",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"headinch",vitalName,"inches",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"0","inches",2,elementGWId,"","",-1,""));
				}
			}else{
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"headcm",vitalName,"cm",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"0","",2,elementGWId,"","",-1,""));
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"headinch",vitalName,"inches",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"0","inches",2,elementGWId,"","",-1,""));
			}
		}
		else if((vitaltype.equalsIgnoreCase("WeightChanges")) && elementtype==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT){
			DecimalFormat f = new DecimalFormat("#0.##");
			if(patientElementBean!=null){
				String elementLbsValue="";
				String elementOzValue="";
				String elementValue=patientElementBean.getPatientClinicalElementText();
				try{
					double elementFloatValue = Double.parseDouble(elementValue);//complete lbs value
					double ounceValue = (elementFloatValue * 16);
					elementLbsValue= HUtil.Nz((int)(ounceValue / 16),"");
					elementOzValue = HUtil.Nz((f.format(ounceValue % 16)),"");

				}catch(Exception e){
					elementLbsValue=elementValue;
				}
				if(elementValue!=""){
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightchangeslbs",vitalName,"lbs",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"0","lbs",2,elementGWId,elementLbsValue,"",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightchangesoz",vitalName,"oz",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"0","oz",2,elementGWId,elementOzValue,"",-1,""));
				}else{
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightchangeslbs",vitalName,"lbs",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"0","lbs",2,elementGWId,"","",-1,""));
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightchangesoz",vitalName,"oz",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"0","oz",2,elementGWId,"","",-1,""));
				}
			}else{
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightchangeslbs",vitalName,"lbs",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"0","lbs",2,elementGWId,"","",-1,""));
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"weightchangesoz",vitalName,"oz",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"0","oz",2,elementGWId,"","",-1,""));
			}
		}
		else if(vitaltype.equalsIgnoreCase("Last Phy-V") && elementtype==ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT){
			String date = getLastPhysical(patientId);
			if(patientElementBean!=null){
				String elementValue=patientElementBean.getPatientClinicalElementText();
				if(elementValue.equals("")){
					elementValue = date;
				}
				if(elementValue!=""){
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,unit,elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",2,elementGWId,elementValue,"",-1,""));
				}else{
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,unit,elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",2,elementGWId,"","",-1,""));

				}
			}else if (date !=""){
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,unit,elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",2,elementGWId,date,"",-1,""));
			}
			else{
				elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,unit,elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",2,elementGWId,"","",-1,""));

			}
		}
		else{

			switch(elementtype){

			case ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_TEXT:
				ClinicalElementBean ceBean = clinicalDataBean.getClinicalElements(elementGWId);
				String isdate = ceBean.getClinicalTextMappings().size()>0?ceBean.getClinicalTextMappings().get(0).getClinicalTextMappingIsdate()+"":"";
				
						if(isdate.equals("t")){
					if(patientElementBean!=null){
						String elementValue=patientElementBean.getPatientClinicalElementText();

						if(elementValue!=""){
							elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,unit,elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",2,elementGWId,elementValue,"",1,""));
						}else{
							elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,unit,elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",2,elementGWId,"","",1,""));
						}
					}else{
						elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,unit,elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",2,elementGWId,"","",1,""));

					}
				}else{
					if(patientElementBean!=null){
						String elementValue=patientElementBean.getPatientClinicalElementText();
						if(elementValue!=""){
							elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,unit.replace("&#176;","°"),elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",2,elementGWId,elementValue,"",0,""));

						}else{
							elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,unit.replace("&#176;","°"),elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",2,elementGWId,"","",0,""));

						}
					}else{
						elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,unit.replace("&#176;","°"),elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",2,elementGWId,"","",0,""));

					}
				}

				break;

			case ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION:

				 ceBean = clinicalDataBean.getClinicalElements(elementGWId);

				int isSelect = ceBean.getClinicalElementIsSelect();

				try{

					List<ClinicalElementsOptions> option=clinicalElementsService.getClinicalElementOptions(elementGWId);

					int j=0;
					AssociatedElementDetails ascElem=null;
					for(;j<option.size();j++){

						ClinicalElementsOptions optionRecord=option.get(j);
						String optionName=HUtil.Nz(optionRecord.getClinicalElementsOptionsName(),"");

						String optionValue=HUtil.Nz(optionRecord.getClinicalElementsOptionsValue(),"");
						PatientElementBean patientOptionBean=clinicalDataBean.getPatientElements(elementGWId);

						if(elemDetails.associatedElements.size()==0){
							ascElem=getAssoElement(vitaltype,"",vitalName,"",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_SINGLEOPTION,elementGWId,"","",-1,Integer.toString(isSelect));
							elemDetails.associatedElements.add(ascElem);
							if(patientOptionBean!=null){
								String elementValue=HUtil.Nz(patientOptionBean.getPatientClinicalElementOption(),"");

								if(optionValue.equalsIgnoreCase(elementValue)){
									ascElem.detailOptions.add(getDetailOption(optionName,optionValue,"1"));
								}else{
									ascElem.detailOptions.add(getDetailOption(optionName,optionValue,""));

								}
							}else{
								ascElem.detailOptions.add(getDetailOption(optionName,optionValue,""));

							}
						}else{


							if(patientOptionBean!=null){
								String elementValue=HUtil.Nz(patientOptionBean.getPatientClinicalElementOption(),"");

								if(optionValue.equalsIgnoreCase(elementValue)){
									ascElem.detailOptions.add(getDetailOption(optionName,optionValue,"1"));

								}else{
									ascElem.detailOptions.add(getDetailOption(optionName,optionValue,""));
								}
							}else{
								ascElem.detailOptions.add(getDetailOption(optionName,optionValue,""));

							}
						}

					}

				}catch(Exception e){
					e.printStackTrace();
				}

				break;
			case ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN:

				if(patientElementBean!=null){
					if(patientElementBean.getPatientClinicalElementBoolean()==true){
						elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,"",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN,elementGWId,"1","",-1,""));
					}else{
						elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,"",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN,elementGWId,"0","",-1,""));
					}
				}else{
					elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,"",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_BOOLEAN,elementGWId,"","",-1,""));
				}

				break;
			case ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION:

//				elementIdInteger = -1;
//				elementIdInteger++;
				try{
					List<ClinicalElementsOptions> option=clinicalElementsService.getClinicalElementOptions(elementGWId);
					int j=0;
					for(;j<option.size();j++){
						ClinicalElementsOptions optionRecord=option.get(j);
						String optionValue=HUtil.Nz(optionRecord.getClinicalElementsOptionsValue(),"");
						PatientElementBean patientOptionBean=clinicalDataBean.getPatientElements(elementGWId+"_"+optionValue);
						if(patientOptionBean!=null){
							String elementValue=HUtil.Nz(patientOptionBean.getPatientClinicalElementOption(),"");

							if(optionValue.equalsIgnoreCase(elementValue)){
								elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,"",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION,elementGWId,"1","",-1,""));	
							}else{
								elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,"",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION,elementGWId,"","",-1,""));
							}
						}else{
							elemDetails.associatedElements.add(getAssoElement(vitaltype,"",vitalName,"",elementBean.getVitalCondition(),elementBean.getVitalConditionType(),"","",ClinicalConstants.CLINICAL_ELEMENT_DATATYPE_MULTIPLEOPTION,elementGWId,"","",-1,""));	
						}
//						elementIdInteger++;
					}

				}catch(Exception e){
					e.printStackTrace();
				}

				break;

			}
		}
		return elemDetails;


	}





	private DetailOptions getDetailOption(String optionName,String optionValue, String value) {
		DetailOptions detOption=new DetailOptions();
		detOption.setOptionName(optionName);
		detOption.setOptionValue(optionValue);
		detOption.setValue(value);
		return detOption;
	}

	private AssociatedElementDetails getAssoElement(String vitaltype, String id,String elementname, String unit, String condition, int  conditionType,String main, String mainType, int clinicalElementType, String elementGWID,String elementValue,String hidden,int isDate,String isSelect ) throws Exception {

		AssociatedElementDetails ascElement=new AssociatedElementDetails();
		ascElement.setVitalType(vitaltype);
		if(id!="")
			ascElement.setId(id);
		ascElement.setElementName(elementname);
		ascElement.setUnit(unit);
		if(condition!="")
			ascElement.setCondition(condition);
		if(conditionType!=-1)
			ascElement.setConditionType(conditionType);
		ascElement.setMain(main);
		if(mainType!="")
			ascElement.setMainType(mainType);
		ascElement.setClinicalElementType(clinicalElementType);
		if(elementGWID!="")
			ascElement.setElementGWID(elementGWID);
		ascElement.setValue(elementValue);
		if(hidden!="")
			ascElement.setHidden(hidden);
		if(isDate!=-1)
			ascElement.setIsDate(isDate);
		if(isSelect!="")
			ascElement.setIsSelect(isSelect);

		return ascElement;
	}






	private String getLastPhysical(int patientId) throws Exception{
		String lastPhy="";
		/*StringBuffer qry = new StringBuffer();
		Date serviceDos=null;
		List<ServiceDetail> serviceDetail=null;
		if((serviceDetail=serviceDetailRepository.findAll(VitalsSpecification.getServiceDetDOS(patientId))).size()>0){
			serviceDos=serviceDetail.get(0).getServiceDetailDos();
			SimpleDateFormat mdyFormat = new SimpleDateFormat("MM/dd/yyyy");
			lastPhy= mdyFormat.format(serviceDos);
		}*/
		return lastPhy;
	}

	public List<DetailOptions> constructJSON(String elementGWId,ClinicalDataBean dataBean,VitalElementBean elementBean)throws Exception{
//		int elementIdInteger=0;
		List<DetailOptions> detOptions=new ArrayList<DetailOptions>();
		if(clinicalElementsService.isClinicalElemActive(elementGWId)){
			String elementValue = null;
			PatientElementBean patientElement=dataBean.getPatientElements(elementGWId);
			if(patientElement!=null){
				elementValue=HUtil.Nz(patientElement.getPatientClinicalElementOption(),"");
			}
			List<ClinicalElementsOptions> option=clinicalElementsService.getClinicalElementOptions(elementGWId);
			for(int j=0;j<option.size();j++){

				ClinicalElementsOptions optionRecord=option.get(j);
				String optionName=HUtil.Nz(optionRecord.getClinicalElementsOptionsName(),"");
				String optionValue=HUtil.Nz(optionRecord.getClinicalElementsOptionsValue(),"");
				if(optionValue.equalsIgnoreCase(elementValue)){
					detOptions.add(getDetailOption(optionName, optionValue, "1"));
				}else{
					detOptions.add(getDetailOption(optionName, optionValue, ""));
//					elementIdInteger++;
				}
			}
		}
		return detOptions;
	}

	public UnitsOfMeasure getUnitName(Integer unitId){
		List<UnitsOfMeasure> unit=null;
		if((unit=unitsOfMeasureRepository.findAll(VitalsSpecification.getUnit(unitId))).size()>0){
			return unit.get(0);
		}else{
			return null;
		}
	}

	public boolean isVitalElemActive(String gwid){
		List<VitalsParameter> vital=null;
		boolean isActive=false;
		if((vital=vitalParameterRepository.findAll(VitalsSpecification.isVitalActive(gwid))).size()>0){
			isActive=Boolean.parseBoolean(HUtil.nz(vital.get(0).getVitalsParameterIsactive(),false)+"");
		}
		return isActive;
	}

	@Override
	public String getNotes(Integer patientId, Integer encounterId, String gwId) {
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<Object> cq= builder.createQuery();
		Root<PatientClinicalElements> root= cq.from(PatientClinicalElements.class);
		cq.select(root.get(PatientClinicalElements_.patientClinicalElementsValue)).
			where(builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsPatientid), patientId),
				  builder.equal(root.get(PatientClinicalElements_.patientClinicalElementsEncounterid), encounterId),
				  builder.like(root.get(PatientClinicalElements_.patientClinicalElementsGwid), gwId));
		
		List <Object> resultList= em.createQuery(cq).getResultList();
		String notes= "";
		if(resultList.size()>0)
			notes= resultList.get(0).toString();
		return notes;
	}

	@Override
	public CustomVitalGroup setVitals(Integer patientId,Integer encounterId,Integer groupId,Boolean isDischargeVitals,Integer admssEpisode,String clientId)throws Exception{
		CustomVitalGroup vitalGrpInfo =new CustomVitalGroup();
		getPatientDetails(patientId);
		clearClinicalData();
		clinicalDataBean.clientId=clientId;
		getActiveVitalsGroup(patientId,groupId);
		for (VitalGroup vitalGroup : vitalGroupList) {
			getGroupVitals(patientId,encounterId,vitalGroup.getVitalGroupId());
		}
		setClinicalDataBean(patientId,encounterId,isDischargeVitals,admssEpisode);
		return prepareJsonfromBeans(vitalGrpInfo,patientId,encounterId,patientSex,ageinYears);
	}

	public void insertvitals(Integer patientId,Integer encounterId,Boolean isDischargeVitals,Integer admssEpisode){
		try{
			clinicalElementsService.setVitalsClinicalDataLoadWithLastVisit("02002%",patientId,encounterId,isDischargeVitals,admssEpisode,patientSex, ageinDay);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private CustomVitalGroup prepareJsonfromBeans(CustomVitalGroup vitalGrpInfo,Integer patientId, Integer encounterId,short patientSex,int patientAge) throws Exception {
		int groupId = -1;
		LinkedHashMap<Integer,VitalGroupBean> vitalGroupHashMap = vitalDataBean.getVitals();
		Set<Entry<Integer,VitalGroupBean>> GroupHashMap = vitalGroupHashMap.entrySet();
		for(Entry<Integer,VitalGroupBean> currentGroup:GroupHashMap){

			groupId   = currentGroup.getKey();
			VitalGroupBean vitalGroupBean = currentGroup.getValue();
			String elementGWId = "0000000000000000000";
			LinkedHashMap<String,VitalElementBean>  vitalHashMap = vitalGroupBean.getVitalElements();
			Set<Entry<String, VitalElementBean>> ElementHashMap =  vitalHashMap.entrySet();
			if(!vitalGroupBean.getGroupName().equals("Others")){
				if(ElementHashMap.size()!=0){
					vitalGrpInfo.setVitalGroupName(vitalGroupBean.getGroupName());
					vitalGrpInfo.setAge(patientAge);
					vitalGrpInfo.setSex(patientSex);
					vitalGrpInfo.setGroupId(groupId);
					for(Entry<String,VitalElementBean> currentElement:ElementHashMap){
						VitalElementBean  elementBean = currentElement.getValue();
						String vitalName = HUtil.ValidateSpecialQuote(elementBean.getVitalName());
						String elementId = currentElement.getKey();
						elementGWId = elementBean.getVitalGWId();



						ClinicalElementBean ceBean = clinicalDataBean.getClinicalElements(elementGWId);
						PatientElementBean patientElementBean=clinicalDataBean.getPatientElements(elementGWId);
						String vitaltype = "";
						if(vitalName.contains("BP")){
							vitaltype = "BP";
						}else if(vitalName.equals("Height")){
							vitaltype = "height";
						}else if(vitalName.equals("Weight")){
							vitaltype = "weight";
						}else if(vitalName.equals("BMI")){
							vitaltype = "BMI";
						}else if(vitalName.equals("BSA")){
							vitaltype = "BSA";
						}else if(vitalName.contains("Pulse")){
							vitaltype = "pulse";
						}else if(vitalName.contains("Temperature")){
							vitaltype = "temperature";
						}else if(vitalName.equals("Head circumference")){
							vitaltype = "Headcircumference";
						}else if(vitalName.equals("Last Phy-V")){
							vitaltype = "Last Phy-V";
						}else if(vitalName.equals("Neck circumference")){
							vitaltype = "Neckcircumference";
						}else if(elementBean.getVitalName().equals("Weight change")){
							vitaltype = "WeightChanges";
						}else if(vitalName.contains("Others")){
							vitaltype = "others";
						}
						vitalGrpInfo.setVitalType(vitaltype);
						if(vitaltype.equalsIgnoreCase("BP")){
							if(vitalName.contains("Systolic") || vitalName.contains("systolic")){
								ClinicalTextMapping clinElement=null;
								String elementAssociatGWId=(clinElement=clinicalElementsService.getClinicalTextMapping(elementGWId))!=null?clinElement.getClinicalTextMappingAssociatedElement()+"":"";
								VitalElementBean vitalAssociatedBean = null;
								for(Entry<String,VitalElementBean> currentAssociateElement:ElementHashMap){
									if(currentAssociateElement.getKey().equals(elementAssociatGWId)){
										vitalAssociatedBean = currentAssociateElement.getValue();
									}
								}
								ClinicalElementBean ceAssociateBean            = clinicalDataBean.getClinicalElements(elementAssociatGWId);
								PatientElementBean patientAssociateElementBean = clinicalDataBean.getPatientElements(elementAssociatGWId);
								String groupName ="";
								if(vitalAssociatedBean!=null && (elementBean.getVitalGender() == patientSex || elementBean.getVitalGender() == 0)){
									if(vitalName.contains("Systolic")){
										groupName = vitalName.substring(0, vitalName.indexOf("Systolic"))+"BP";
									}else if(vitalName.contains("systolic")){
										groupName = vitalName.substring(0, vitalName.indexOf("systolic"))+"BP";
									}
									if(ceBean!=null && ceAssociateBean!=null ){
										ElementDetails elementDet = prepareElement(clinicalDataBean,elementBean,patientElementBean,elementId,elementGWId,vitaltype,ceBean.getClinicalElementDataType(),patientId,encounterId);	

										for(int i=0;i<elementDet.associatedElements.size();i++){
											elementDet.associatedElements.get(i).elementName=groupName;
										}

										vitalGrpInfo.unitgroupdetails.add(elementDet);

										ElementDetails associate = prepareElement(clinicalDataBean, vitalAssociatedBean,patientAssociateElementBean,elementAssociatGWId,elementAssociatGWId,vitaltype,ceBean.getClinicalElementDataType(),patientId,encounterId);


										for (AssociatedElementDetails assoElement : associate.getAssociatedElements()) {
											elementDet.getAssociatedElements().add(assoElement);
										}

										//vitalGrpInfo.unitgroupdetails.add(associate);
									}
								}
							}
						}
						else{
							if(ceBean!=null){
								if(elementBean.getVitalGender() == patientSex || elementBean.getVitalGender() == 0){
									ElementDetails elementDet = prepareElement(clinicalDataBean,elementBean,patientElementBean,elementId,elementGWId,vitaltype,ceBean.getClinicalElementDataType(),patientId,encounterId);
									vitalGrpInfo.unitgroupdetails.add(elementDet);

								}
							}
						}
					}
					boolean isOrthoNotesActive = clinicalElementsService.isClinicalElemActive("0000200200100201000");
					String elementValue="";
					if(isOrthoNotesActive && vitalGroupBean.getGroupName().contains("BP / Respiratory")){
						ElementDetails elemDetails=new ElementDetails();
						PatientElementBean patientElementBeanBPNotes=clinicalDataBean.getPatientElements("0000200200100201000");
						if(patientElementBeanBPNotes!=null)
							elementValue=HUtil.Nz(patientElementBeanBPNotes.getPatientClinicalElementText(),"");
						elemDetails.associatedElements.add(getAssoElement("","","Repeat BP","","",0,"","",2,"0000200200100201000",elementValue,"",0,""));
						vitalGrpInfo.unitgroupdetails.add(elemDetails);
					}
					boolean isRepeatpulNotesActive = clinicalElementsService.isClinicalElemActive("0000200200100236000");
					elementValue="";
					if(isRepeatpulNotesActive && vitalGroupBean.getGroupName().contains("Pulse / Temperature")){
						PatientElementBean patientElementBeanPulseNotes=clinicalDataBean.getPatientElements("0000200200100236000");
						if(patientElementBeanPulseNotes!=null)
							elementValue=HUtil.Nz(patientElementBeanPulseNotes.getPatientClinicalElementText(),"");

					}

				}
			}else{
				if(ElementHashMap.size()!=0){

					vitalGrpInfo.setVitalGroupName(vitalGroupBean.getGroupName());
					vitalGrpInfo.setAge(patientAge);
					vitalGrpInfo.setSex(patientSex);
					vitalGrpInfo.setGroupId(groupId);
					for(Entry<String,VitalElementBean> currentElement:ElementHashMap){
						VitalElementBean  elementBean = currentElement.getValue();
						String elementId = currentElement.getKey();
						elementGWId = elementBean.getVitalGWId();
						ClinicalElementBean ceBean = clinicalDataBean.getClinicalElements(elementGWId);
						PatientElementBean patientElementBean=clinicalDataBean.getPatientElements(elementGWId);
						String vitaltype = "others";
						if(ceBean!=null){
							if(elementBean.getVitalGender() == patientSex || elementBean.getVitalGender() == 0){
								ElementDetails elementDet = prepareElement(clinicalDataBean,elementBean,patientElementBean,elementId,elementGWId,vitaltype,ceBean.getClinicalElementDataType(),patientId,encounterId);
								vitalGrpInfo.unitgroupdetails.add(elementDet);
							}
						}

					}

				}
			}
		}

		/*boolean notes = clinicalElementsService.isClinicalElemActive("0000200200100198000");
		String elementValue="";
		if(notes){
			PatientElementBean patientElementBeanVitalNotes=clinicalDataBean.getPatientElements("0000200200100198000");
			if(patientElementBeanVitalNotes!=null)
				elementValue=HUtil.Nz(patientElementBeanVitalNotes.getPatientClinicalElementText(),"");
		}*/

		return vitalGrpInfo;
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
