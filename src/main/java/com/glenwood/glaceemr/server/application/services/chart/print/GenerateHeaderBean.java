package com.glenwood.glaceemr.server.application.services.chart.print;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glenwood.glaceemr.server.application.models.LetterHeaderEmp;
import com.glenwood.glaceemr.server.application.models.LetterHeaderPos;
import com.glenwood.glaceemr.server.application.models.print.GenericLetterHeader;
import com.glenwood.glaceemr.server.application.models.print.LetterHeaderContent;
import com.glenwood.glaceemr.server.application.models.print.PatientHeader;
import com.glenwood.glaceemr.server.application.models.print.PatientHeaderDetails;
import com.glenwood.glaceemr.server.application.services.chart.print.genericheader.LetterHeaderService;
import com.glenwood.glaceemr.server.application.services.chart.print.patientheader.PatientHeaderService;
import com.glenwood.glaceemr.server.application.services.employee.EmployeeDataBean;
import com.glenwood.glaceemr.server.application.services.patient.PatientDataBean;
import com.glenwood.glaceemr.server.application.services.pos.DefaultPracticeBean;
import com.glenwood.glaceemr.server.application.services.pos.PosDataBean;

@Component
public class GenerateHeaderBean {

	@Autowired
	LetterHeaderService letterHeaderService;

	@Autowired
	PatientHeaderService patientHeaderService;

	@Autowired
	GenericPrintService genericPrintService;
	
	@Autowired
	TextFormatter textFormatter;
	
	int attributeCount=3;

	public String generateHeader(int letterHeaderId){

		GenericLetterHeader genericLetterheader=letterHeaderService.getLetterHeaderDetails(letterHeaderId);
		List<LetterHeaderContent> letterHeaderContentList=letterHeaderService.getLetterHeaderContentList(letterHeaderId);		

		StringBuilder headerHTML=new StringBuilder();
		if(genericLetterheader.getGenericLetterHeaderType()==2){
			headerHTML.append("<table width='100%' cellpadding='1'>");
			for(int i=0;i<letterHeaderContentList.size();i++){
				headerHTML.append("<tr><td style='width:100%;"+generateStyleString(letterHeaderContentList.get(i).getLetterHeaderContentStyle())+"'>");
				if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==2){
					headerHTML.append(drawTextHeaderData(letterHeaderContentList.get(i),generateStyleString(letterHeaderContentList.get(i).getLetterHeaderContentStyle())));
				}else if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==3){
					headerHTML.append(letterHeaderContentList.get(i).getLetterHeaderContentCustom().replace("\n", "<br/>"));
				}else if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==4){
					if(letterHeaderContentList.get(i).getLetterHeaderContentFlag()!=4){
						headerHTML.append(drawAddressData(letterHeaderContentList.get(i),genericLetterheader.getGenericLetterHeaderAddress().toString()));
					}
				}
				headerHTML.append("</td></tr>");
			}
			headerHTML.append("</table>");
		}

		return headerHTML.toString();
	}
	
	/**
	 * Method to generate final header as HTML
	 * @param letterHeaderId
	 * @param shareFolderPath
	 * @param genericPrintBean
	 * @return
	 * @throws Exception
	 */
	public String generateHeader(int letterHeaderId, String shareFolderPath, GenericPrintBean genericPrintBean) throws Exception{
		StringBuilder headerHTML=new StringBuilder();
		try{
			GenericLetterHeader genericLetterheader=letterHeaderService.getLetterHeaderDetails(letterHeaderId);
			List<LetterHeaderContent> letterHeaderContentList=letterHeaderService.getLetterHeaderContentList(letterHeaderId);		

			if(genericLetterheader.getGenericLetterHeaderType()==2){					// Text only header
				headerHTML.append(getTextOnlyHeaderHTML(genericLetterheader, letterHeaderContentList, genericPrintBean));
			}

			else if(genericLetterheader.getGenericLetterHeaderType()==5) {				// Custom header
				for(int i=0;i<letterHeaderContentList.size();i++){
					if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==8){
						headerHTML.append(letterHeaderContentList.get(i).getLetterHeaderContentCustom());
					}
				}
			}

			else if(genericLetterheader.getGenericLetterHeaderType()==1) {				// Image only header
				for(int i=0;i<letterHeaderContentList.size();i++){
					if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==1){

						String styleStr= letterHeaderContentList.get(i).getLetterHeaderContentStyle();
						String[] styleList = styleStr.split("#~#");
						String height = "", width = "";
						if(styleList.length > 0){
							if(styleList[0] != null && !styleList[0].trim().isEmpty() && !styleList[0].trim().equalsIgnoreCase("-"))
								height = styleList[0]+"px";
							if(styleList.length > 1)
								width = styleList[1]+"px";
						}

						int alignFlag = letterHeaderContentList.get(i).getLetterHeaderContentFlag();
						String align= "center";
						switch(alignFlag) {
						case 0: align= "center"; break;
						case 1: align= "left";   break;
						case 2: align= "right";  break;
						case 3: height= "100%"; width="100%"; break;
						default: align="center";
						}

						if(styleList.length > 0){
							headerHTML.append("<div style='width:100%; text-align:"+align+"'><img id='headerImg0' name='headerImg0' src='"+shareFolderPath+"/"+letterHeaderContentList.get(i).getLetterHeaderContentCustom()+"' height='"+height+"' width='"+width+"' /></div>");
						}
						else {
							headerHTML.append("<div style='width:100%; text-align:"+align+"'><img id='headerImg0' name='headerImg0' src='"+shareFolderPath+"/"+letterHeaderContentList.get(i).getLetterHeaderContentCustom()+"'/></div>");
						}
					}
				}
			}

			else if(genericLetterheader.getGenericLetterHeaderType()==3){				// Text with Image on sides header
				
				int splitType= 1;
				String width= "33.3%";
				for(int i=0;i<letterHeaderContentList.size();i++){
					if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==10){
						splitType= letterHeaderContentList.get(i).getLetterHeaderContentFlag();
					}
				}
				if(splitType== 1){
					width="33.3%";
				}else if(splitType== 2){
					width="50%";
				}
					
				headerHTML.append("<table width='100%' cellpadding='1'>");
				headerHTML.append("<tr>");
				for(int i=0;i<letterHeaderContentList.size();i++){
					if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==1){
						if(letterHeaderContentList.get(i).getLetterHeaderContentFlag()==1){
							if(splitType== 1 || (letterHeaderContentList.get(i).getLetterHeaderContentCustom()!=null
									&& !letterHeaderContentList.get(i).getLetterHeaderContentCustom().trim().isEmpty())){
								headerHTML.append("<td width='"+width+"'>"+getGenericImage(letterHeaderContentList.get(i),shareFolderPath,"float:left;")+"</td>");
								break;
							}
						}
					}
				}

				headerHTML.append("<td width='"+width+"'>"+getTextOnlyHeaderHTML(genericLetterheader, letterHeaderContentList, genericPrintBean)+"</td>");

				for(int i=0;i<letterHeaderContentList.size();i++){
					if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==1){
						if(letterHeaderContentList.get(i).getLetterHeaderContentFlag()==2){
							if(splitType== 1 || (letterHeaderContentList.get(i).getLetterHeaderContentCustom()!=null
									&& !letterHeaderContentList.get(i).getLetterHeaderContentCustom().trim().isEmpty())){
								headerHTML.append("<td>"+getGenericImage(letterHeaderContentList.get(i),shareFolderPath,"float:right;")+"</td>");
								break;
							}
						}
					}
				}	
				headerHTML.append("</tr></table>");
			}
			else if(genericLetterheader.getGenericLetterHeaderType()==4) {				// Logo with two headers
				headerHTML.append("<table width='100%' cellpadding='1'>");
				headerHTML.append("<tr>");
				for(int i=0;i<letterHeaderContentList.size();i++){
					if(letterHeaderContentList.get(i).getLetterHeaderContentVariant() ==1 && letterHeaderContentList.get(i).getLetterHeaderContentFlag()==1 && letterHeaderContentList.get(i).getLetterHeaderContentCustom()!=""){
						headerHTML.append("<td width='33.3%'><div align='left' style='align-items: left;width:100%;'>"+getGenericImage(letterHeaderContentList.get(i), shareFolderPath, letterHeaderContentList.get(i).getLetterHeaderContentStyle())+"</div></td>");
					}
				}
				headerHTML.append("<td style='width:80%'>");
				headerHTML.append(getTextOnlyHeaderHTML(genericLetterheader, letterHeaderContentList, genericPrintBean));
				headerHTML.append("<hr/>");
				headerHTML.append(getTextOnlyHeaderHTML2(genericLetterheader, letterHeaderContentList, genericPrintBean));
				headerHTML.append("</td>");
				headerHTML.append("</tr>");
				headerHTML.append("</table>");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return "unable to fetch header details";
		}
		return headerHTML.toString();
	}

	private String getGenericImage(LetterHeaderContent letterHeaderContent, String shareFolderPath, String styleStr) {
		
		StringBuffer imageHTML = new StringBuffer("");
		int flag = letterHeaderContent.getLetterHeaderContentFlag();
		String style = getGenericImageStyle(letterHeaderContent);
		if(letterHeaderContent.getLetterHeaderContentCustom()!=null && !letterHeaderContent.getLetterHeaderContentCustom().trim().isEmpty())
		imageHTML.append("<img src='"+shareFolderPath+"/"+ letterHeaderContent.getLetterHeaderContentCustom()+"' id='headerImg"+flag+"' name='headerImg"+flag+"' style='"+styleStr+style+"'/>");
		return imageHTML.toString();
	}

	private String getGenericImageStyle(LetterHeaderContent letterHeaderContent) {

		StringBuffer imageStyleHTML = new StringBuffer();
		String styleStr = letterHeaderContent.getLetterHeaderContentStyle();
		String[] styleList = styleStr.split("#~#");
		if(styleList.length > 0){
			if(styleList[0] != null && !styleList[0].trim().isEmpty() && !styleList[0].trim().equalsIgnoreCase("-"))
				imageStyleHTML.append("height:"+styleList[0]+"px;");
			if(styleList.length > 1)
				imageStyleHTML.append("width:"+styleList[1]+"px;");
		}

		return imageStyleHTML.toString();
	}

	public String drawTextHeaderData(LetterHeaderContent letterHeaderContent,String style){
		StringBuilder header=new StringBuilder();
		header.append("<table width='100%'>");
		String customText=letterHeaderContent.getLetterHeaderContentCustom().replace("\n", "<br/>");
		if(letterHeaderContent.getLetterHeaderContentFlag()==1){

			header.append("<tr><td style='"+style+"'>Practice Name</td></tr><tr><td style='"+style+"'>"+customText+"</td></tr>");
		}else if(letterHeaderContent.getLetterHeaderContentFlag()==2){

			header.append("<tr><td style='"+style+"'>Principal Doctor</td></tr><tr><td style='"+style+"'>"+customText+"</td></tr>");
		}else if(letterHeaderContent.getLetterHeaderContentFlag()==3){

			header.append("<tr><td style='"+style+"'>Service Doctor</td></tr><tr><td style='"+style+"'>"+customText+"</td></tr>");
		}else if(letterHeaderContent.getLetterHeaderContentFlag()==4){
			header.append("<tr><td style='"+style+"'>Practice Name 1</td><td style='"+style+"'>Practice Name 2</td><td style='"+style+"'>Practice Name 3</td></tr><tr><td colspan='3' style='"+style+"'>"+customText+"</td></tr>");
		}else if(letterHeaderContent.getLetterHeaderContentFlag()==5){
			header.append("<tr><td style='"+style+"'>Doctor Name 1</td><td style='"+style+"'>Doctor Name 2</td><td style='"+style+"'>Doctor Name 3</td></tr><tr><td colspan='3' style='"+style+"'>"+customText+"</td></tr>");
		}else if(letterHeaderContent.getLetterHeaderContentFlag()==6){
			header.append("<tr><td style='"+style+"'>"+customText+"</td></tr>");
		}
		header.append("</table>");
		return header.toString();
	}
	
	/**
	 * Method to get text header as HTML
	 * @param letterHeaderContent
	 * @param style
	 * @param genericPrintBean
	 * @param posList
	 * @param empList
	 * @return
	 */
	public String drawTextHeaderData(LetterHeaderContent letterHeaderContent,String style, GenericPrintBean genericPrintBean, List<LetterHeaderPos> posList, List<EmployeeDataBean> empList){
		try{
			StringBuilder header=new StringBuilder();			
			String customText=letterHeaderContent.getLetterHeaderContentCustom().replace("\n", "<br/>");
			if(letterHeaderContent.getLetterHeaderContentFlag()==1){						// Default Practice
				DefaultPracticeBean practiceBean = genericPrintBean.getPracticeBean();
				
				if((practiceBean != null && practiceBean.getPracticeName() != null && !practiceBean.getPracticeName().trim().isEmpty())||(customText != null && !customText.trim().isEmpty()))				
					header.append("<table width='100%' style='text-align:center;' cellspacing='0' cellpadding='0'>");
				if(practiceBean != null && practiceBean.getPracticeName() != null && !practiceBean.getPracticeName().trim().isEmpty())					
					header.append("<tr><td style='"+style+"'>"+practiceBean.getPracticeName()+"</td></tr>");
				if(customText != null && !customText.trim().isEmpty())
					header.append("<tr><td style='"+style+"'>"+customText+"</td></tr>");
				if((practiceBean != null && practiceBean.getPracticeName() != null && !practiceBean.getPracticeName().trim().isEmpty())||(customText != null && !customText.trim().isEmpty()))				
					header.append("</table>");
			}else if(letterHeaderContent.getLetterHeaderContentFlag()==2){					// Place of Service
				PatientDataBean patientBean = genericPrintBean.getPatientBean();
				List<PosDataBean> posBean = patientBean.getPosDetails();
				if((posBean != null && posBean.size()>0 && posBean.get(0) != null) || (customText != null && !customText.trim().isEmpty()))
					header.append("<table width='100%' style='text-align:center;' cellspacing='0' cellpadding='0'>");
				if(posBean != null && posBean.size()>0 && posBean.get(0) != null){
					if(posBean.get(0).getPosName() != null && !posBean.get(0).getPosName().isEmpty())
						header.append("<tr><td style='"+style+"'>"+posBean.get(0).getPosName()+"</td></tr>");
				}
				if(customText != null && !customText.trim().isEmpty())
					header.append("<tr><td style='"+style+"'>"+customText+"</td></tr>");
				if((posBean != null && posBean.size()>0 && posBean.get(0) != null) || (customText != null && !customText.trim().isEmpty()))
					header.append("</table>");
			}else if(letterHeaderContent.getLetterHeaderContentFlag()==3){					// All Practices

				if(posList != null){
					if(posList.size()>0){
						header.append("<table width='100%' style='text-align:center;' cellspacing='0' cellpadding='0'>");
						header.append("<tr>");
					}
					float width= getWidth(posList.size());
					for(int i=0; i< posList.size(); i++){
						PosDataBean posBean = genericPrintService.parsePOSDetail(posList.get(i).getPosTable());
						if(posBean != null){
							header.append("<td style='width:"+width+"%;"+style+"'>"+posBean.getPosName()+"</td>");
						}
					}
					if(posList.size()>0){
						header.append("</tr>");
						header.append("</table>");
					}
				}
				//				header.append("<tr><td style='"+style+"'>Practice Name 1</td><td style='"+style+"'>Practice Name 2</td><td style='"+style+"'>Practice Name 3</td></tr><tr><td colspan='3' style='"+style+"'>"+customText+"</td></tr>");
			}else if(letterHeaderContent.getLetterHeaderContentFlag()==4){					// Principal Doctor
				PatientDataBean patientBean = genericPrintBean.getPatientBean();
				String principalDr = "";
				if(patientBean != null){
					principalDr = patientBean.getPrincipalDr().getEmpFullname();
					if((principalDr!=null && !principalDr.trim().isEmpty() || (customText != null && !customText.trim().isEmpty())))
						header.append("<table width='100%' style='text-align:center;' cellspacing='0' cellpadding='0'>");
					if(principalDr!=null && !principalDr.trim().isEmpty())						
						header.append("<tr><td style='"+style+"'>"+principalDr+"</td></tr>");
				}else{
					if(customText != null && !customText.trim().isEmpty())
						header.append("<table width='100%' style='text-align:center;' cellspacing='0' cellpadding='0'>");
				}
				if(customText != null && !customText.trim().isEmpty())
					header.append("<tr><td style='"+style+"'>"+customText+"</td></tr>");
				if((principalDr!=null && !principalDr.trim().isEmpty() || (customText != null && !customText.trim().isEmpty())))
					header.append("</table>");
			}else if(letterHeaderContent.getLetterHeaderContentFlag()==5){					// Service Doctor
				PatientDataBean patientBean = genericPrintBean.getPatientBean();
				String serviceDr = "";
				if(patientBean != null){
					serviceDr = patientBean.getServiceDr().getEmpFullname();
					if((serviceDr!=null && !serviceDr.trim().isEmpty() || (customText != null && !customText.trim().isEmpty())))
						header.append("<table width='100%' style='text-align:center;' cellspacing='0' cellpadding='0'>");
					if(serviceDr!=null && !serviceDr.trim().isEmpty())
						header.append("<tr><td style='"+style+"'>"+serviceDr+"</td></tr>");
				}else{
					if(customText != null && !customText.trim().isEmpty())
						header.append("<table width='100%' style='text-align:center;' cellspacing='0' cellpadding='0'>");
				}
				if(customText != null && !customText.trim().isEmpty())
					header.append("<tr><td style='"+style+"'>"+customText+"</td></tr>");
				if((serviceDr!=null && !serviceDr.trim().isEmpty() || (customText != null && !customText.trim().isEmpty())))
					header.append("</table>");
			}else if(letterHeaderContent.getLetterHeaderContentFlag()==6){					// All Doctors
				
				if(empList != null) {
					if(empList.size()>0){
						header.append("<table width='100%' style='text-align:center;' cellspacing='0' cellpadding='0'>");
						header.append("<tr>");
					}
					float width= getWidth(empList.size());
					for(int i=0; i< empList.size(); i++){
						EmployeeDataBean emp= empList.get(i);
						String empFullname = textFormatter.getFormattedName(emp.getEmpFirstName(), emp.getEmpMiddleName(), emp.getEmpLastName(), emp.getEmpCredentials());
						if(empFullname != null)
							header.append("<td style='width:"+width+"%;"+style+"'>"+empFullname+"</td>");
					}
					if(empList.size()>0){
						header.append("</tr>");
						header.append("</table>");
					}
				}
				
				//			header.append("<tr><td style='"+style+"'>Doctor Name 1</td><td style='"+style+"'>Doctor Name 2</td><td style='"+style+"'>Doctor Name 3</td></tr><tr><td colspan='3' style='"+style+"'>"+customText+"</td></tr>");
			}else if(letterHeaderContent.getLetterHeaderContentFlag()==7){					// Custom
				if(customText != null && !customText.trim().isEmpty()){
					header.append("<table width='100%' style='text-align:center;' cellspacing='0' cellpadding='0'>");
					header.append("<tr><td style='"+style+"'>"+customText+"</td></tr>");
					header.append("</table>");
				}
			}
			
			return header.toString();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * Method to draw complete address details in header
	 * @param letterHeaderContent
	 * @param addressFormat
	 * @param style
	 * @param genericPrintBean
	 * @param posList
	 * @param empList
	 * @param headerFlag
	 * @return
	 * @throws Exception
	 */
	public String drawAddressData(LetterHeaderContent letterHeaderContent,String addressFormat, String style, GenericPrintBean genericPrintBean, List<LetterHeaderPos> posList, List<EmployeeDataBean> empList, int headerFlag) throws Exception{		
		StringBuilder address=new StringBuilder();

		List<String> addressList = new ArrayList<String>();
		List<String> phoneList = new ArrayList<String>();
		List<String> faxList = new ArrayList<String>();
		List<String> mailList = new ArrayList<String>();
		
		addressList.clear();
		phoneList.clear();
		faxList.clear();
		mailList.clear();
		if(letterHeaderContent.getLetterHeaderContentFlag()!=7){	
			address.append("<table width='100%' style='text-align: center;' cellspacing='0' cellpadding='0'><tr>");
			if(letterHeaderContent.getLetterHeaderContentFlag() == 1){				     // Default Practice
				DefaultPracticeBean practiceBean =  genericPrintBean.getPracticeBean();
				address.append("<td>");
				if(practiceBean != null){				
					if(addressFormat.charAt(1)=='1'|| addressFormat.charAt(2)=='1'|| addressFormat.charAt(3)=='1'|| addressFormat.charAt(4)=='1'|| addressFormat.charAt(5)=='1')
						address.append("<table width='100%' style='text-align: center;' cellspacing='0' cellpadding='0'>");
					if(addressFormat.charAt(1)=='1'){
						address.append(generateField("",practiceBean.getPracticeStreet(),style));
						String secondAddress=parseAddress(practiceBean.getPracticeCity(), practiceBean.getPracticeState(), practiceBean.getPracticeZip());
						address.append(generateField("",secondAddress,style));
					}
					if(addressFormat.charAt(2)=='1'){					
						String finalPhNum = practiceBean.getPracticePhoneNum();
						try{
							if(finalPhNum.indexOf("-")!=-1){
								String[] arr= 	finalPhNum.split("-");
								if(arr.length == 3)
									finalPhNum = "("+ arr[0] + ") " + arr[1] + "-" + arr[2];
							}
						}catch(Exception e){
							finalPhNum = practiceBean.getPracticePhoneNum();
						}
						address.append(generateField("Phone: ",finalPhNum,style));
					}
					if(addressFormat.charAt(3)=='1'){
						String finalFaxNum = practiceBean.getPracticeFaxNum();
						try{
							if(finalFaxNum.indexOf("-")!=-1){
								String[] arr= 	finalFaxNum.split("-");
								if(arr.length == 3)
									finalFaxNum = "("+ arr[0] + ") " + arr[1] + "-" + arr[2];
							}
						}catch(Exception e){
							finalFaxNum = practiceBean.getPracticeFaxNum();
						}
						address.append(generateField("Fax: ",finalFaxNum,style));
					}
					if(addressFormat.charAt(4)=='1')
						address.append(generateField("Email: ",practiceBean.getPracticeEmail(),style));
					if(addressFormat.charAt(5)=='1')
						address.append(generateField("Web: ",practiceBean.getPracticeWebAddress(),style));
					if(addressFormat.charAt(1)=='1'|| addressFormat.charAt(2)=='1'|| addressFormat.charAt(3)=='1'|| addressFormat.charAt(4)=='1'|| addressFormat.charAt(5)=='1')
						address.append("</table>");
				}
				address.append("</td>");
			}
			if(letterHeaderContent.getLetterHeaderContentFlag() == 2){				     // Place of Service
				PatientDataBean patientBean=genericPrintBean.getPatientBean();
				List<PosDataBean> posBean=patientBean.getPosDetails();
				address.append("<td>");
				if(posBean != null && posBean.size()>0 && posBean.get(0) != null){
					if(addressFormat.charAt(1)=='1'|| addressFormat.charAt(2)=='1'|| addressFormat.charAt(3)=='1')
						address.append("<table width='100%' style='text-align: center;' cellspacing='0' cellpadding='0'>");
					if(addressFormat.charAt(1)=='1'){
						address.append(generateField("",posBean.get(0).getPosAddress(),style));
						address.append(generateField("",parseAddress(posBean.get(0).getPosCity(), posBean.get(0).getPosState(), posBean.get(0).getPosZip()),style));
					}
					if(addressFormat.charAt(2)=='1'){
						String finalPhNum= posBean.get(0).getPosPhNum();
						try{
							if(finalPhNum.indexOf("-")!=-1){
								String[] arr= 	finalPhNum.split("-");
								if(arr.length == 3)
									finalPhNum = "("+ arr[0] + ") " + arr[1] + "-" + arr[2];
							}
						}catch(Exception e){
							finalPhNum = posBean.get(0).getPosPhNum();
						}
						address.append(generateField("Phone: ",finalPhNum,style));
					}
					if(addressFormat.charAt(3)=='1'){
						String finalFaxNum= posBean.get(0).getPosFaxNum();
						try{
							if(finalFaxNum.indexOf("-")!=-1){
								String[] arr= 	finalFaxNum.split("-");
								if(arr.length == 3)
									finalFaxNum = "("+ arr[0] + ") " + arr[1] + "-" + arr[2];
							}
						}catch(Exception e){
							finalFaxNum = posBean.get(0).getPosFaxNum();
						}
						address.append(generateField("Fax: ",finalFaxNum,style));
					}
					if(addressFormat.charAt(1)=='1'|| addressFormat.charAt(2)=='1'|| addressFormat.charAt(3)=='1')
						address.append("</table>");
				}
				address.append("</td>");
			}
			if(letterHeaderContent.getLetterHeaderContentFlag() == 3){					// All Practices
				if(posList.size()<=0)
					address.append("<td>");
				for(int i=0; i< posList.size(); i++){
					float width= (float)100/posList.size();
					address.append("<td width='"+width+"%'>");
					if(addressFormat.charAt(1)=='1'|| addressFormat.charAt(2)=='1'|| addressFormat.charAt(3)=='1')					
						address.append("<table width='100%' style='text-align: center;' cellspacing='0' cellpadding='0'>");
					PosDataBean posBean = genericPrintService.parsePOSDetail(posList.get(i).getPosTable());
					if(addressFormat.charAt(1)=='1'){
						address.append(generateField("",posBean.getPosAddress(),style));
						address.append(generateField("",parseAddress(posBean.getPosCity(),posBean.getPosState(),posBean.getPosZip()),style));
					}
					if(addressFormat.charAt(2)=='1'){
						String finalPhNum= posBean.getPosPhNum();
						try{
							if(finalPhNum.indexOf("-")!=-1){
								String[] arr= 	finalPhNum.split("-");
								if(arr.length == 3)
									finalPhNum = "("+ arr[0] + ") " + arr[1] + "-" + arr[2];
							}
						}catch(Exception e){
							finalPhNum = posBean.getPosPhNum();
						}
						address.append(generateField("Phone: ",finalPhNum,style));
					}
					if(addressFormat.charAt(3)=='1'){
						String finalFaxNum= posBean.getPosFaxNum();
						try{
							if(finalFaxNum.indexOf("-")!=-1){
								String[] arr= 	finalFaxNum.split("-");
								if(arr.length == 3)
									finalFaxNum = "("+ arr[0] + ") " + arr[1] + "-" + arr[2];
							}
						}catch(Exception e){
							finalFaxNum = posBean.getPosFaxNum();
						}
						address.append(generateField("Fax: ",finalFaxNum,style));
					}
					if(addressFormat.charAt(1)=='1'|| addressFormat.charAt(2)=='1'|| addressFormat.charAt(3)=='1')
						address.append("</table>");
					address.append("</td>");
				}
				if(posList.size()<=0)
					address.append("</td>");

			}
			if(letterHeaderContent.getLetterHeaderContentFlag() == 4){					// Principal Doctor Address
				address.append("<td>");
				EmployeeDataBean empBean =  genericPrintBean.getPatientBean().getPrincipalDr();				
				if(empBean != null){
					if(addressFormat.charAt(1)=='1' || addressFormat.charAt(2)=='1' || addressFormat.charAt(4)=='1')
						address.append("<table width='100%' style='text-align: center;' cellspacing='0' cellpadding='0'>");
					if(addressFormat.charAt(1)=='1'){
						address.append(generateField("",empBean.getEmpAddress(),style));
						address.append(generateField("",parseAddress(empBean.getEmpCity(), empBean.getEmpState(), empBean.getEmpZip()),style));
					}
					if(addressFormat.charAt(2)=='1'){
						String finalPhNum= empBean.getEmpPhNum();
						try{
							if(finalPhNum.indexOf("-")!=-1){
								String[] arr= 	finalPhNum.split("-");
								if(arr.length == 3)
									finalPhNum = "("+ arr[0] + ") " + arr[1] + "-" + arr[2];
							}
						}catch(Exception e){
							finalPhNum = empBean.getEmpPhNum();
						}
						address.append(generateField("Phone: ",finalPhNum,style));
					}
					if(addressFormat.charAt(4)=='1')
						address.append(generateField("Email: ",empBean.getEmpMailId(),style));
					if(addressFormat.charAt(1)=='1' || addressFormat.charAt(2)=='1' || addressFormat.charAt(4)=='1')
						address.append("</table>");				
				}
				address.append("</td>");
			}
			if(letterHeaderContent.getLetterHeaderContentFlag() == 5){					// Service Doctor Address
				address.append("<td>");
				EmployeeDataBean empBean =  genericPrintBean.getPatientBean().getServiceDr();				
				if(empBean != null){
					if(addressFormat.charAt(1)=='1' || addressFormat.charAt(2)=='1' || addressFormat.charAt(4)=='1')
						address.append("<table width='100%' style='text-align: center;' cellspacing='0' cellpadding='0'>");
					if(addressFormat.charAt(1)=='1'){
						address.append(generateField("",empBean.getEmpAddress(),style));
						address.append(generateField("",parseAddress(empBean.getEmpCity(), empBean.getEmpState(), empBean.getEmpZip()),style));
					}
					if(addressFormat.charAt(2)=='1'){
						String finalPhNum= empBean.getEmpPhNum();
						try{
							if(finalPhNum.indexOf("-")!=-1){
								String[] arr= 	finalPhNum.split("-");
								if(arr.length == 3)
									finalPhNum = "("+ arr[0] + ") " + arr[1] + "-" + arr[2];
							}
						}catch(Exception e){
							finalPhNum = empBean.getEmpPhNum();
						}
						address.append(generateField("Phone: ",finalPhNum,style));
					}
					if(addressFormat.charAt(4)=='1')
						address.append(generateField("Email: ",empBean.getEmpMailId(),style));
					if(addressFormat.charAt(1)=='1' || addressFormat.charAt(2)=='1' || addressFormat.charAt(4)=='1')
						address.append("</table>");
				}
				address.append("</td>");
			}

			if(letterHeaderContent.getLetterHeaderContentFlag() == 6){					// All Doctors Address
				if(empList.size()<=0)
					address.append("<td>");
				for(int i=0; i< empList.size(); i++){				
					EmployeeDataBean empBean = genericPrintService.parseEmployeeDetail1(empList.get(i));
					if(empBean != null){
						float width= (float)100/empList.size();
						address.append("<td width='"+width+"%'>");
						if(addressFormat.charAt(1)=='1' || addressFormat.charAt(2)=='1' || addressFormat.charAt(4)=='1'){						
							address.append("<table width='100%' style='text-align: center;' cellspacing='0' cellpadding='0'>");
						}
						if(addressFormat.charAt(1)=='1'){
							address.append(generateField("",empBean.getEmpAddress(),style));
							address.append(generateField("",parseAddress(empBean.getEmpCity(),empBean.getEmpState(),empBean.getEmpZip()),style));
						}
						if(addressFormat.charAt(2)=='1'){
							String finalPhNum= empBean.getEmpPhNum();
							try{
								if(finalPhNum.indexOf("-")!=-1){
									String[] arr= 	finalPhNum.split("-");
									if(arr.length == 3)
										finalPhNum = "("+ arr[0] + ") " + arr[1] + "-" + arr[2];								
								}else if(finalPhNum.trim().length()==10){
									finalPhNum = "("+ finalPhNum.substring(0, 3) + ") " + finalPhNum.substring(3, 6) + "-" + finalPhNum.substring(6, 10);
								}
							}catch(Exception e){
								finalPhNum = empBean.getEmpPhNum();
							}
							address.append(generateField("Phone: ",finalPhNum,style));
						}
						if(addressFormat.charAt(4)=='1')
							address.append(generateField("Email: ",empBean.getEmpMailId(),style));
						if(addressFormat.charAt(1)=='1' || addressFormat.charAt(2)=='1' || addressFormat.charAt(4)=='1')
							address.append("</table>");
						address.append("</td>");
					}
				}
				if(empList.size()<=0)
					address.append("</td>");
			}

			address.append("</tr></table>");
		}
		return address.toString();
	}
	
	/**
	 * Method to generate field
	 * @param name
	 * @param value
	 * @return
	 */
	private String generateField(String name, String value, String style) {
		
		String field = "";
		if(value != null && !value.trim().isEmpty())
			field = "<tr><td style='"+style+"'>"+name+value+"</td></tr>";
		return field;
	}

	/**
	 * Method to parse Address second line
	 * @param city
	 * @param state
	 * @param zip
	 * @return
	 */
	private String parseAddress(String city,String state, String zip) {
		
		String addressSecondLine = "";
		if(city !=  null && !city.isEmpty())
			addressSecondLine = city.trim()+", "; 
		if(state != null && !state.isEmpty())
			addressSecondLine = addressSecondLine + state.trim() +" ";
		if(zip != null && !zip.isEmpty())
			addressSecondLine = addressSecondLine + zip.trim();
		
		return addressSecondLine;
	}

	/**
	 * Method to parse Address 
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @return
	 */
	private String parseAddress(String address, String city,String state, String zip) {
		
		String addressSecondLine = "";
		String finalAddress = "";
		if(city !=  null && !city.isEmpty())
			addressSecondLine = city.trim()+", "; 
		if(state != null && !state.isEmpty())
			addressSecondLine = addressSecondLine + state.trim() +" ";
		if(zip != null && !zip.isEmpty())
			addressSecondLine = addressSecondLine + zip.trim();
		if(address != null && !address.isEmpty())
			finalAddress = address.trim();
		if(!addressSecondLine.trim().isEmpty())
			if(!finalAddress.isEmpty())
				finalAddress = finalAddress + "</td></tr><tr><td>" + addressSecondLine.trim();
			else
				finalAddress = addressSecondLine.trim();
		return finalAddress;
	}

	public String drawAddressData(LetterHeaderContent letterHeaderContent,String addressFormat ){
		StringBuilder address=new StringBuilder();
		address.append("<table width='100%'>");

		if((addressFormat).charAt(1)=='1'){
			if(letterHeaderContent.getLetterHeaderContentFlag()==1){
				address.append("<tr><td >Default Address</td></tr>");
			}else if(letterHeaderContent.getLetterHeaderContentFlag()==2){
				address.append("<tr><td>POS Address</td></tr>");
			}else if(letterHeaderContent.getLetterHeaderContentFlag()==3){
				
				address.append("<tr><td>Address 1</td><td>Address 2</td><td>Address 3</td></tr>");
				
			}
		}
		if((addressFormat).charAt(2)=='1'){
			if(letterHeaderContent.getLetterHeaderContentFlag()==3){
				
				address.append("<tr><td>Phone 1</td><td>Phone 2</td><td>Phone 3</td></tr>");
				
			}else{
				address.append("<tr><td>Phone</td></tr>");
			}
		}
		if((addressFormat).charAt(3)=='1'){
			if(letterHeaderContent.getLetterHeaderContentFlag()==3){
				
				address.append("<tr><td>Fax 1</td><td>Fax 2</td><td>Fax 3</td></tr>");
				
			}else{
				address.append("<tr><td>Fax</td></tr>");
			}

		}
		if((addressFormat).charAt(4)=='1'){
			if(letterHeaderContent.getLetterHeaderContentFlag()==3){
				
				address.append("<tr><td>e-mail 1</td><td>e-mail 2</td><td>e-mail 3</td></tr>");
				
			}else{
				address.append("<tr><td>e-mail</td></tr>");
			}

		}
		if((addressFormat).charAt(5)=='1'){
			if(letterHeaderContent.getLetterHeaderContentFlag()==3){
				
				address.append("<tr><td>Web portal 1</td><td>Web portal 2</td><td>Web portal 3</td></tr>");
				
			}else{
				address.append("<tr><td>Web portal</td></tr>");
			}

		}
		address.append("</table>");
		return address.toString();
	}
	public String generateStyleString(String styleData){
		StringBuilder style=new StringBuilder();
		if(styleData!=null && !styleData.equals("")){

			String[] styleDataArr=styleData.split("#~#");
			if(styleDataArr.length>=6){

				if(styleDataArr[0].equals("1")){
					style.append("font-weight:bold;");
				}
				if(styleDataArr[1].equals("1")){
					style.append("font-style:italic;");
				}
				if(styleDataArr[2].equals("1")){
					style.append("text-decoration:underline;");

				}
				style.append("font-family:"+styleDataArr[3]+";");
				style.append("font-size:"+styleDataArr[4]+"px;");
				style.append("color:#"+styleDataArr[5]+";");
				if(styleDataArr.length>7)
					style.append("text-transform:"+styleDataArr[7]+";");
				else if(styleDataArr.length>6)
					style.append("text-align:"+styleDataArr[6]+";");
				else
					style.append("text-align: center;");
			}
		}else
			return "";
		return style.toString();
	}

	public int getPatientHeaderType(int patientHeaderId){
		PatientHeader patientHeader=patientHeaderService.getPatientHeader(patientHeaderId);
		if(patientHeader!=null){
			return patientHeader.getPatientHeaderType();
		}
		return -1;
	}
	public String generatePatientHeader(int patientHeaderId,int pageId){
		List<PatientHeaderDetails> patientHeaderDetails=patientHeaderService.getPatientHeaderDetailList(patientHeaderId, pageId);

		return generatePatientHeaderHTML(patientHeaderDetails);
	}
	public String generatePatientHeader(int patientHeaderId,int pageId,String[] patientDetailsArr){
		List<PatientHeaderDetails> patientHeaderDetails=patientHeaderService.getPatientHeaderDetailList(patientHeaderId, pageId);

		return generatePatientHeaderHTML(patientHeaderDetails,patientDetailsArr);
	}
	public int getPatientHeaderAttributeCount(int patientHeaderId,int pageId){
		return patientHeaderService.getPatientHeaderAttributeCount(patientHeaderId, pageId)/attributeCount;
	}
	/**
	 * Method to get patient header as HTML
	 * @param patientHeaderDetails
	 * @return
	 */
	public String generatePatientHeaderHTML(List<PatientHeaderDetails> patientHeaderDetails){
		String[] attributes = {"Patient Name", "Age", "DOS", "Gender","Account #","Phone #","DOB","Mobile #","Address","Referring Doctor","Pri. Ins.","Supervising doctor",
				"Service/Performing Doctor","Ethinicity","Race","Preffered/Primary Language","Care Group"};
		StringBuilder pHeaderHTML=new StringBuilder();


		int i=0;
		int count=0;
		if(patientHeaderDetails.size()>0){
			pHeaderHTML.append("<table style='border-collapse:collapse;' cellpadding='1' cellspacing='0' width='100%'><tr><td colspan='");
			pHeaderHTML.append(attributeCount);
			pHeaderHTML.append("' style='height:8px'><hr/></td></tr>");
			for(;i<patientHeaderDetails.size();i++){
				if(count%attributeCount==0){
					pHeaderHTML.append("<tr>");
				}
				pHeaderHTML.append("<td width='33%'><font style='font-weight:bold;font-size:12px;font-family:Arial;'>");
				pHeaderHTML.append(attributes[patientHeaderDetails.get(i).getComponentId()]);
				pHeaderHTML.append(":</font>&nbsp;<font style='color:#999;font-size:12px;font-family:Arial;'><i>&#60;");
				pHeaderHTML.append(attributes[patientHeaderDetails.get(i).getComponentId()]);
				pHeaderHTML.append("&#62;</i></font></td>");
				count++;
				if(count%attributeCount==0){
					pHeaderHTML.append("</tr>");
				}
			}
			if(count%attributeCount!=0){
				for(;count%attributeCount!=0;count++){
					pHeaderHTML.append("<td width='33%'></td>");
				}
				pHeaderHTML.append("</tr>");
			}
			pHeaderHTML.append("<tr><td colspan='");
			pHeaderHTML.append(attributeCount);
			pHeaderHTML.append("' style='height:8px'><hr/></td></tr>");
			pHeaderHTML.append("</table>");
		}

		return pHeaderHTML.toString();
	}
	
	/**
	 * Method to get patient header as HTML
	 * @param patientHeaderDetails
	 * @param patientDetailsArr
	 * @return
	 */
	public String generatePatientHeaderHTML(List<PatientHeaderDetails> patientHeaderDetails, String[] patientDetailsArr){
		String[] attributes = {"Patient Name", "Age", "DOS", "Gender","Account #","Phone #","DOB","Mobile #","Address","Referring Doctor","Pri. Ins.","Supervising doctor",
				"Service/Performing Doctor","Ethinicity","Race","Preffered/Primary Language","Care Group"};
		StringBuilder pHeaderHTML=new StringBuilder();


		int i=0;
		int count=0;
		if(patientHeaderDetails.size()>0){
			pHeaderHTML.append("<table style='border-collapse:collapse;' cellpadding='1' cellspacing='0' width='100%'><tr><td colspan='");
			pHeaderHTML.append(attributeCount);
			pHeaderHTML.append("' style='height:8px'><hr/></td></tr>");
			for(;i<patientHeaderDetails.size();i++){
				if(count%attributeCount==0){
					pHeaderHTML.append("<tr>");
				}
				String alignStyle="";
				if(patientHeaderDetails.size()==attributeCount){
					if(count%attributeCount==0)
						alignStyle="text-align: left;";
					else if(count%attributeCount==attributeCount-1)
						alignStyle="text-align: right;";
					else
						alignStyle="text-align: center;";
				}
				pHeaderHTML.append("<td width='33%' style='"+alignStyle+"'><font style='font-weight:bold;font-size:12px;font-family:Arial;'>");
				pHeaderHTML.append(attributes[patientHeaderDetails.get(i).getComponentId()]);
				if(!patientDetailsArr[patientHeaderDetails.get(i).getComponentId()].equalsIgnoreCase("")){
					pHeaderHTML.append(":</font>&nbsp;<font style='font-size:12px;font-family:Arial;'>");
					pHeaderHTML.append(patientDetailsArr[patientHeaderDetails.get(i).getComponentId()]);
				}else{
					pHeaderHTML.append(":</font>&nbsp;<font style='color:#999;font-size:12px;font-family:Arial;'><i>&#60;");
					pHeaderHTML.append(attributes[patientHeaderDetails.get(i).getComponentId()]+"&#62;</i>");
				}
				pHeaderHTML.append("</font></td>");
				count++;
				if(count%attributeCount==0){
					pHeaderHTML.append("</tr>");
				}
			}
			if(count%attributeCount!=0){
				for(;count%attributeCount!=0;count++){
					pHeaderHTML.append("<td width='33%'></td>");
				}
				pHeaderHTML.append("</tr>");
			}
			pHeaderHTML.append("<tr><td colspan='");
			pHeaderHTML.append(attributeCount);
			pHeaderHTML.append("' style='height:8px'><hr/></td></tr>");
			pHeaderHTML.append("</table>");
		}

		return pHeaderHTML.toString();
	}
	
	/**
	 * Method to get letter header content flag
	 * @param letterHeaderContentList
	 * @param variantId
	 * @return
	 */
	private Integer getHeaderFlag(List<LetterHeaderContent> letterHeaderContentList, int variantId) {
		
		for(int i=0; i<letterHeaderContentList.size(); i++){
			if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==variantId)
				return letterHeaderContentList.get(i).getLetterHeaderContentFlag();
		}
		return -1;
	}
	
	/**
	 * Dividing screen into number of blocks 
	 * @param size
	 * @return
	 */
	public float getWidth(int size){
		
		float width;
		try{
			width = 100/size;
		}catch(Exception e){
			width = 0;
			return width;
		}
		return width;
	}
	
	/**
	 * Method to get text header as HTML
	 * @param genericLetterheader
	 * @param letterHeaderContentList
	 * @param genericPrintBean
	 * @return
	 * @throws Exception
	 */
	public String getTextOnlyHeaderHTML(GenericLetterHeader genericLetterheader, List<LetterHeaderContent> letterHeaderContentList, GenericPrintBean genericPrintBean) throws Exception{		
		StringBuffer headerHTML = new StringBuffer();
				
//		List<InitialSettings> initialList= initialSettingsRepository.findAll(Specifications.where(InitialSettingsSpecification.optionType(4)).and(InitialSettingsSpecification.optionVisible(true)));
		if(letterHeaderContentList.size()>0)
			headerHTML.append("<table width='100%' cellpadding='1'>");
		for(int i=0;i<letterHeaderContentList.size();i++){			
			if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==2||
				letterHeaderContentList.get(i).getLetterHeaderContentVariant()==11){
				int mode=1; 
				if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==11)
					mode=3;
				List<LetterHeaderPos> posList = letterHeaderService.fetchLetterHeaderPOSList(genericLetterheader.getGenericLetterHeaderId(), mode);
				List<EmployeeDataBean> empList = letterHeaderService.fetchLetterHeaderEmpList(genericLetterheader.getGenericLetterHeaderId(), mode);
				String data=drawTextHeaderData(letterHeaderContentList.get(i),generateStyleString(letterHeaderContentList.get(i).getLetterHeaderContentStyle()),genericPrintBean,posList,empList);
				if(data!=null && !data.trim().isEmpty()){
					headerHTML.append("<tr><td style='width:100%;"+generateStyleString(letterHeaderContentList.get(i).getLetterHeaderContentStyle())+"'>");
					headerHTML.append(data);
					headerHTML.append("</td></tr>");
				}
			}else if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==3){
				if(letterHeaderContentList.get(i).getLetterHeaderContentCustom()!=null && !letterHeaderContentList.get(i).getLetterHeaderContentCustom().isEmpty()){
					headerHTML.append("<tr><td style='width:100%;"+generateStyleString(letterHeaderContentList.get(i).getLetterHeaderContentStyle())+"'>");
					headerHTML.append(letterHeaderContentList.get(i).getLetterHeaderContentCustom().replace("\n", "<br/>"));
					headerHTML.append("</td></tr>");
				}
			}else if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==4){
				if(letterHeaderContentList.get(i).getLetterHeaderContentFlag()!=4){
					List<LetterHeaderPos> posList = letterHeaderService.fetchLetterHeaderPOSList(genericLetterheader.getGenericLetterHeaderId(), 1);
					List<EmployeeDataBean> empList = letterHeaderService.fetchLetterHeaderEmpList(genericLetterheader.getGenericLetterHeaderId(), 1);
					headerHTML.append("<tr><td style='width:100%;"+generateStyleString(letterHeaderContentList.get(i).getLetterHeaderContentStyle())+"'>");
					headerHTML.append(drawAddressData(letterHeaderContentList.get(i),genericLetterheader.getGenericLetterHeaderAddress().toString(),generateStyleString(letterHeaderContentList.get(i).getLetterHeaderContentStyle()),genericPrintBean,posList,empList,getHeaderFlag(letterHeaderContentList,2)));
					headerHTML.append("</td></tr>");
				}
			}
		}
		if(letterHeaderContentList.size()>0)
			headerHTML.append("</table>");
		
		return headerHTML.toString();
	}
	
	/**
	 * Method to get second text header (Left Image with two headers style)
	 * @param genericLetterheader
	 * @param letterHeaderContentList
	 * @param genericPrintBean
	 * @return
	 * @throws Exception
	 */
	public String getTextOnlyHeaderHTML2(GenericLetterHeader genericLetterheader, List<LetterHeaderContent> letterHeaderContentList, GenericPrintBean genericPrintBean) throws Exception{
		
		StringBuffer headerHTML = new StringBuffer();				
		if(letterHeaderContentList.size()>0)
			headerHTML.append("<table width='100%' cellpadding='1'>");
		for(int i=0;i<letterHeaderContentList.size();i++){
			
			if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==5||
				letterHeaderContentList.get(i).getLetterHeaderContentVariant()==12){
				int mode=1;
				if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==12)
					mode=4;
				List<LetterHeaderPos> posList = letterHeaderService.fetchLetterHeaderPOSList(genericLetterheader.getGenericLetterHeaderId(), mode);
				List<EmployeeDataBean> empList = letterHeaderService.fetchLetterHeaderEmpList(genericLetterheader.getGenericLetterHeaderId(), mode);
				headerHTML.append("<tr><td style='width:100%;"+generateStyleString(letterHeaderContentList.get(i).getLetterHeaderContentStyle())+"'>");
				headerHTML.append(drawTextHeaderData(letterHeaderContentList.get(i),generateStyleString(letterHeaderContentList.get(i).getLetterHeaderContentStyle()),genericPrintBean,posList,empList));
				headerHTML.append("</td></tr>");
			}else if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==6){
				headerHTML.append("<tr><td style='width:100%;"+generateStyleString(letterHeaderContentList.get(i).getLetterHeaderContentStyle())+"'>");
				headerHTML.append(letterHeaderContentList.get(i).getLetterHeaderContentCustom().replace("\n", "<br/>"));
				headerHTML.append("</td></tr>");
			}else if(letterHeaderContentList.get(i).getLetterHeaderContentVariant()==7){
				if(letterHeaderContentList.get(i).getLetterHeaderContentFlag()!=4){
					List<LetterHeaderPos> posList = letterHeaderService.fetchLetterHeaderPOSList(genericLetterheader.getGenericLetterHeaderId(), 1);
					List<EmployeeDataBean> empList = letterHeaderService.fetchLetterHeaderEmpList(genericLetterheader.getGenericLetterHeaderId(), 1);
					headerHTML.append("<tr><td style='width:100%;"+generateStyleString(letterHeaderContentList.get(i).getLetterHeaderContentStyle())+"'>");
					headerHTML.append(drawAddressData(letterHeaderContentList.get(i),genericLetterheader.getGenericLetterHeaderAddress().toString(),generateStyleString(letterHeaderContentList.get(i).getLetterHeaderContentStyle()),genericPrintBean,posList,empList,getHeaderFlag(letterHeaderContentList,5)));
					headerHTML.append("</td></tr>");
				}
			}			
		}
		if(letterHeaderContentList.size()>0)
			headerHTML.append("</table>");
		
		return headerHTML.toString();
	}

	/**
	 * Method to get left side header as HTML
	 * @param headerId
	 * @param genericPrintBean
	 * @return
	 * @throws Exception
	 */
	public String generateLeftHeader(int headerId, GenericPrintBean genericPrintBean) throws Exception {
	
		GenericLetterHeader genericLetterheader=letterHeaderService.getLetterHeaderDetails(headerId);		
		List<LetterHeaderPos> posList = letterHeaderService.fetchLetterHeaderPOSList(genericLetterheader.getGenericLetterHeaderId(), 2);
		List<EmployeeDataBean> empList = letterHeaderService.fetchLetterHeaderEmpList(genericLetterheader.getGenericLetterHeaderId(), 2);
		List<Integer> variantIds= new ArrayList<Integer>();		
		variantIds.add(13);
		variantIds.add(14);
		
		String leftHeaderHTML="";
		Integer leftIndex=-1;
		Integer leftAddressIndex=-1;
		String addressFormat = genericLetterheader.getGenericLetterHeaderAddress().toString();
		String leftHeaderStyle="";
		String leftAddrStyle="";
		
		if(letterHeaderService.getLetterHeaderContentCount(genericLetterheader.getGenericLetterHeaderId(),variantIds)>0){
			List<LetterHeaderContent> list= letterHeaderService.getLetterHeaderContentList(genericLetterheader.getGenericLetterHeaderId(),variantIds);
			for(int i=0; i<list.size(); i++){
				if(list.get(i).getLetterHeaderContentVariant()==13){
					leftIndex= list.get(i).getLetterHeaderContentFlag();
					leftHeaderStyle= generateStyleString(list.get(i).getLetterHeaderContentStyle());
				}
				else if(list.get(i).getLetterHeaderContentVariant()==14){
					leftAddressIndex= list.get(i).getLetterHeaderContentFlag();
					leftAddrStyle= generateStyleString(list.get(i).getLetterHeaderContentStyle());
				}
			}
		}else{
			leftIndex= genericLetterheader.getGenericLetterHeaderLeft();
			leftAddressIndex= genericLetterheader.getGenericLetterHeaderLeftAddress();
		}
		
		if(leftHeaderStyle.isEmpty())
			leftHeaderStyle="font-size: 12px; font-weight: bold; font-family: Perpetua;";
		if(leftAddrStyle.isEmpty())
			leftAddrStyle="font-size: 12px;font-family:Times New Roman;";
		
		List<String> addressList = new ArrayList<String>();
		List<String> namesList = new ArrayList<String>();
		List<String> phoneList = new ArrayList<String>();
		List<String> emailList = new ArrayList<String>();
		List<String> faxList = new ArrayList<String>();
		
		int countLeft =0, countLeftAddress =0;
		
		if(leftIndex == 1 || leftAddressIndex == 1){
			addressList.clear();
			namesList.clear();
			phoneList.clear();
			emailList.clear();
			faxList.clear();
			for(int i=0; i< empList.size(); i++){
				EmployeeDataBean empBean = genericPrintService.parseEmployeeDetail1(empList.get(i));
				namesList.add(empBean.getEmpFullname());
				addressList.add(parseAddress(empBean.getEmpAddress(), empBean.getEmpCity(), empBean.getEmpState(), empBean.getEmpZip()));
				phoneList.add(empBean.getEmpPhNum() != null ? empBean.getEmpPhNum(): "");
				emailList.add(empBean.getEmpMailId() != null ? empBean.getEmpMailId(): "");
			}
			
			if(leftIndex == 1)
				countLeft= empList.size();
			if(leftAddressIndex == 1)
				countLeftAddress = empList.size();
		}
		if(leftIndex == 2 || leftAddressIndex == 2){
			if(leftAddressIndex == 2){
				addressList.clear();				
				phoneList.clear();
				emailList.clear();
				faxList.clear();
			}else if(leftIndex == 2){
				namesList.clear();
			}
			for(int i=0; i< posList.size(); i++){
				PosDataBean posBean = genericPrintService.parsePOSDetail(posList.get(i).getPosTable());
				namesList.add(posBean.getPosName());
				addressList.add(parseAddress(posBean.getPosAddress(), posBean.getPosCity(), posBean.getPosState(), posBean.getPosZip()));
				phoneList.add(posBean.getPosPhNum() != null ? posBean.getPosPhNum(): "");
				faxList.add(posBean.getPosFaxNum() != null ? posBean.getPosFaxNum(): "");
			}	
			if(leftIndex == 2)
				countLeft= posList.size();
			if(leftAddressIndex == 2)
				countLeftAddress = posList.size();
		}
		
		if(leftIndex ==0){												// Default Practice Name			
			DefaultPracticeBean defaultPracticeBean = genericPrintBean.getPracticeBean();
			leftHeaderHTML += "<table width='100%' style='"+leftHeaderStyle+"' cellspacing='0' cellpadding='0'>";
			leftHeaderHTML += "<tr><td>"+defaultPracticeBean.getPracticeName()+"</td></tr>";
			leftHeaderHTML += "</table>";
			leftHeaderHTML += "<br/><br/>";
		}
		else if(leftIndex ==1 || leftIndex ==2){						// 1)Doctors Name 2)Practices Name
			if(countLeft >0){
				leftHeaderHTML += "<table width='100%' style='"+leftHeaderStyle+"' cellspacing='0' cellpadding='0'>";
				for(int i=0; i<countLeft; i++){
					if(!namesList.get(i).trim().isEmpty())
						leftHeaderHTML += "<tr><td>"+namesList.get(i)+"<br/><br/><br/></td></tr>";
				}
				leftHeaderHTML += "</table>";
				leftHeaderHTML += "<br/><br/>";
			}
		}		
		else if(leftIndex ==3){											// Principal Doctor Name
			EmployeeDataBean employeeDataBean = genericPrintBean.getPatientBean().getPrincipalDr();
			if(employeeDataBean != null){
				leftHeaderHTML += "<table width='100%' style='"+leftHeaderStyle+"' cellspacing='0' cellpadding='0'>";
				leftHeaderHTML += "<tr><td>"+employeeDataBean.getEmpFullname()+"</td></tr>";
				leftHeaderHTML += "</table>";
				leftHeaderHTML += "<br/><br/>";
			}
		}
		else if(leftIndex ==4){											// Service Doctor Name
			EmployeeDataBean employeeDataBean = genericPrintBean.getPatientBean().getServiceDr();
			if(employeeDataBean != null){
				leftHeaderHTML += "<table width='100%' style='"+leftHeaderStyle+"' cellspacing='0' cellpadding='0'>";
				leftHeaderHTML += "<tr><td>"+employeeDataBean.getEmpFullname()+"</td></tr>";
				leftHeaderHTML += "</table>";
				leftHeaderHTML += "<br/><br/>";
			}
		}
		else if(leftIndex ==5){											// Place Of Service Name
			List<PosDataBean> posDataBean = genericPrintBean.getPatientBean().getPosDetails();
			if(posDataBean != null && posDataBean.size()>0 && posDataBean.get(0) != null && posDataBean.get(0).getPosName() != null && !posDataBean.get(0).getPosName().isEmpty()){
				leftHeaderHTML += "<table width='100%' style='"+leftHeaderStyle+"' cellspacing='0' cellpadding='0'>";
				leftHeaderHTML += "<tr><td>"+posDataBean.get(0).getPosName()+"</td></tr>";
				leftHeaderHTML += "</table>";
				leftHeaderHTML += "<br/><br/>";
			}
		}
		
		if(leftAddressIndex ==0){										// Default Practice Address
			DefaultPracticeBean defaultPracticeBean = genericPrintBean.getPracticeBean();
			if(defaultPracticeBean != null){
				leftHeaderHTML += "<table width='100%' style='"+leftAddrStyle+"' cellspacing='0' cellpadding='0'>";
				leftHeaderHTML += "<tr><td>Offices:<br/><br/></td></tr>";
				leftHeaderHTML += "<tr><td>"+parseAddress(defaultPracticeBean.getPracticeStreet(), defaultPracticeBean.getPracticeCity(), defaultPracticeBean.getPracticeState(), defaultPracticeBean.getPracticeZip())+"</td></tr>";
				if(addressFormat.charAt(1)=='1' && defaultPracticeBean.getPracticePhoneNum() != null && !defaultPracticeBean.getPracticePhoneNum().trim().isEmpty())
					leftHeaderHTML += "<tr><td>Ph: "+textFormatter.getFormattedPhoneNum(defaultPracticeBean.getPracticePhoneNum())+"</td></tr>";
				if(addressFormat.charAt(2)=='1' && defaultPracticeBean.getPracticeFaxNum() != null && !defaultPracticeBean.getPracticeFaxNum().trim().isEmpty())
					leftHeaderHTML += "<tr><td>Fax: "+textFormatter.getFormattedPhoneNum(defaultPracticeBean.getPracticeFaxNum())+"</td></tr>";
				if(addressFormat.charAt(3)=='1' && defaultPracticeBean.getPracticeEmail() != null && !defaultPracticeBean.getPracticeEmail().trim().isEmpty())
					leftHeaderHTML += "<tr><td>Email: "+defaultPracticeBean.getPracticeEmail()+"</td></tr>";
				if(addressFormat.charAt(4)=='1' && defaultPracticeBean.getPracticeWebAddress() != null && !defaultPracticeBean.getPracticeWebAddress().trim().isEmpty())
					leftHeaderHTML += "<tr><td>Web: "+defaultPracticeBean.getPracticeWebAddress()+"</td></tr>";
				leftHeaderHTML += "</table>";
			}
		}
		else if(leftAddressIndex ==1 || leftAddressIndex ==2){			// 1)Doctors Address 2) Practices Address
			if(countLeftAddress >0){
				leftHeaderHTML += "<table width='100%' style='"+leftAddrStyle+"' cellspacing='0' cellpadding='0'><tr><td>";
				if(leftAddressIndex == 1)
					leftHeaderHTML += "Address:<br/><br/></td></tr><tr><td>";
				else
					leftHeaderHTML += "Offices:<br/><br/></td></tr><tr><td>";
				for(int i=0; i<countLeftAddress; i++){
					leftHeaderHTML += "<table width='100%' style='"+leftAddrStyle+"' cellpadding='0' cellspacing='0'>";
					if(!addressList.get(i).isEmpty())
						leftHeaderHTML += "<tr><td>"+addressList.get(i)+"</td></tr>";
					if(!phoneList.get(i).trim().isEmpty())
						leftHeaderHTML += "<tr><td>Ph: "+textFormatter.getFormattedPhoneNum(phoneList.get(i))+"</td></tr>";
					if(leftAddressIndex ==2)
					if(!faxList.get(i).trim().isEmpty())
						leftHeaderHTML += "<tr><td>Fax: "+textFormatter.getFormattedPhoneNum(faxList.get(i))+"</td></tr>";
					if(leftAddressIndex ==1)
					if(!emailList.get(i).trim().isEmpty())
						leftHeaderHTML += "<tr><td>Email: "+emailList.get(i)+"</td></tr>";
					leftHeaderHTML += "</table>";
					leftHeaderHTML += "<br/><br/>";
				}
				leftHeaderHTML += "</td></tr></table>";
			}
		}
		else if(leftAddressIndex ==3){									// Principal Doctor Address
			
			EmployeeDataBean employeeDataBean = genericPrintBean.getPatientBean().getPrincipalDr();
			if(employeeDataBean != null){

				leftHeaderHTML += "<table width='100%' style='"+leftAddrStyle+"' cellspacing='0' cellpadding='0'><tr><td>";
				leftHeaderHTML += "Address:<br/><br/></td></tr><tr><td>";
				leftHeaderHTML += "<table width='100%' style='"+leftAddrStyle+"' cellspacing='0' cellpadding='0'>";
				if(employeeDataBean.getEmpAddress() != null && !employeeDataBean.getEmpAddress().trim().isEmpty())
					leftHeaderHTML += "<tr><td>"+parseAddress(employeeDataBean.getEmpAddress(), employeeDataBean.getEmpCity(), employeeDataBean.getEmpState(), employeeDataBean.getEmpZip())+"</td></tr>";
				if(!employeeDataBean.getEmpPhNum().trim().isEmpty())
					leftHeaderHTML += "<tr><td>Ph: "+textFormatter.getFormattedPhoneNum(employeeDataBean.getEmpPhNum())+"</td></tr>";					
				if(!employeeDataBean.getEmpMailId().trim().isEmpty())
					leftHeaderHTML += "<tr><td>Email: "+employeeDataBean.getEmpMailId()+"</td></tr>";
				leftHeaderHTML += "</table>";
				leftHeaderHTML += "<br/>";
				leftHeaderHTML += "</td></tr></table>";
			}
		}
		else if(leftAddressIndex ==4){									// Service Doctor Address

			EmployeeDataBean employeeDataBean = genericPrintBean.getPatientBean().getServiceDr();
			if(employeeDataBean != null){

				leftHeaderHTML += "<table width='100%' style='"+leftAddrStyle+"' cellspacing='0' cellpadding='0'><tr><td>";
				leftHeaderHTML += "Address:<br/><br/></td></tr><tr><td>";
				leftHeaderHTML += "<table width='100%' style='"+leftAddrStyle+"' cellspacing='0' cellpadding='0'>";
				if(employeeDataBean.getEmpAddress() != null && !employeeDataBean.getEmpAddress().trim().isEmpty())
					leftHeaderHTML += "<tr><td>"+parseAddress(employeeDataBean.getEmpAddress(), employeeDataBean.getEmpCity(), employeeDataBean.getEmpState(), employeeDataBean.getEmpZip())+"</td></tr>";
				if(!employeeDataBean.getEmpPhNum().trim().isEmpty())
					leftHeaderHTML += "<tr><td>Ph: "+textFormatter.getFormattedPhoneNum(employeeDataBean.getEmpPhNum())+"</td></tr>";					
				if(!employeeDataBean.getEmpMailId().trim().isEmpty())
					leftHeaderHTML += "<tr><td>Email: "+employeeDataBean.getEmpMailId()+"</td></tr>";
				leftHeaderHTML += "</table>";
				leftHeaderHTML += "<br/>";
				leftHeaderHTML += "</td></tr></table>";
			}
		}
		else if(leftAddressIndex ==5){									// Place of service Address

			List<PosDataBean> posDataBean = genericPrintBean.getPatientBean().getPosDetails();
			if(posDataBean != null && posDataBean.size()>0){

				leftHeaderHTML += "<table width='100%' style='"+leftAddrStyle+"' cellspacing='0' cellpadding='0'>";
				leftHeaderHTML += "<tr><td>Offices:<br/><br/></td></tr><tr><td>";
				leftHeaderHTML += "<table width='100%' style='"+leftAddrStyle+"' cellspacing='0' cellpadding='0'>";
				if(posDataBean.get(0).getPosAddress() != null && !posDataBean.get(0).getPosAddress().trim().isEmpty())
					leftHeaderHTML += "<tr><td>"+parseAddress(posDataBean.get(0).getPosAddress(), posDataBean.get(0).getPosCity(), posDataBean.get(0).getPosState(), posDataBean.get(0).getPosZip())+"</td></tr>";
				if(posDataBean.get(0).getPosPhNum() != null && !posDataBean.get(0).getPosPhNum().trim().isEmpty())
					leftHeaderHTML += "<tr><td>Ph: "+textFormatter.getFormattedPhoneNum(posDataBean.get(0).getPosPhNum())+"</td></tr>";					
				if(posDataBean.get(0).getPosFaxNum() != null && !posDataBean.get(0).getPosFaxNum().trim().isEmpty())
					leftHeaderHTML += "<tr><td>Fax: "+textFormatter.getFormattedPhoneNum(posDataBean.get(0).getPosFaxNum())+"</td></tr>";
				leftHeaderHTML += "</table>";
				leftHeaderHTML += "<br/><br/>";
				leftHeaderHTML += "</td></tr></table>";
			}
		}
		
		return leftHeaderHTML; 
		/*if(leftIndex ==1 || leftIndex ==2 || leftIndex ==4 || leftIndex ==5){
			leftHeaderHTML = "<table width='100%'>";
			for(int i=0; i<count; i++){
				if(!namesList.get(i).trim().isEmpty())
					leftHeaderHTML += "<tr><td>"+namesList.get(i)+"</td></tr>";
			}
			leftHeaderHTML += "</table>";
			leftHeaderHTML += "<br/>";
			leftHeaderHTML += "<br/>";
		}
		if(leftIndex ==1 || leftIndex ==3 || leftIndex ==4 || leftIndex ==6){
			
			leftHeaderHTML += "<table width='100%'>";
			for(int i=0; i<count; i++){
				leftHeaderHTML += "<table width='100%'>";
				if(!addressList.get(i).isEmpty())
					leftHeaderHTML += "<tr><td>"+addressList.get(i)+"</td></tr>";
				if(!phoneList.get(i).trim().isEmpty())
					leftHeaderHTML += "<tr><td>"+phoneList.get(i)+"</td></tr>";
				if(!faxList.get(i).trim().isEmpty())
					leftHeaderHTML += "<tr><td>"+faxList.get(i)+"</td></tr>";
				if(!emailList.get(i).trim().isEmpty())
					leftHeaderHTML += "<tr><td>"+emailList.get(i)+"</td></tr>";
				leftHeaderHTML += "</table>";
				leftHeaderHTML += "<br/>";
			}
		}*/
		
	}
}