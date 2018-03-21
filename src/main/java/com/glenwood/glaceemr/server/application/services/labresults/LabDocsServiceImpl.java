package com.glenwood.glaceemr.server.application.services.labresults;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Service;

/**
 * @author yasodha
 * 
 * LabResultsServiceImpl gives the data required for 
 * external lab results
 */

@Service
public class LabDocsServiceImpl implements LabDocsService {

	@Override
	public String getFileContent(String filePath) throws Exception {
		BufferedReader in  = null;
		String htmlData = "<table width='100%' cellspacing='0' cellpadding='2' style='border:0;'>",str = "";
		try{
			 in = new BufferedReader(new FileReader(filePath));
			 while((str = in.readLine()) != null) {
				 if(str.startsWith("PID")) {
					 if(str.split("[|]").length >= 8) {
						 htmlData += "<tr style='font-size:17px;font-family: HelveticaNeue-Medium,Helvetica Neue,sans-serif;color: #6E6E6E;height: 40px;' ><td>Patient Name:&#160;";
						 String patientName = str.split("[|]")[5].toString(),FullName = "";
						 char[] pName = patientName.toCharArray();
							int secIndex = 0;
							int i = 0;
							for(;i < pName.length ; i++) {
								if(pName[i] == '^') {
									if( secIndex == 2 )
										break;
									secIndex++;
								}
							}
							if(secIndex == 2) {
								FullName = patientName.substring(0,i-1).replaceAll("\\^",",");
							} else {
								FullName = patientName.substring(0,i).replaceAll("\\^",",");
							}						 
							htmlData += FullName+"&#160;&#160;DOB:&#160;";
						 String dob = "";
						 if((dob = str.split("[|]")[7].toString()).length() >= 8) {
							 htmlData += dob.substring(4,6) + "/" + dob.substring(6,8) + "/" + dob.substring(0,4);
						 }
						 htmlData += "&#160;&#160;Sex:&#160;" + str.split("[|]")[8].toString() + "</td></tr>";
					 }
				}				
				 if(str.startsWith("OBX")) {
					 if(str.split("[|]").length >= 6) {
						 htmlData += "<tr style='font-size:17px;font-family: HelveticaNeue-Medium,Helvetica Neue,sans-serif;color: #6E6E6E;height: 40px;' ><td>"+str.split("[|]")[5].replaceAll("\\\\.br\\\\","<br/>").replaceAll("\\\\T\\\\", "&")+"</td></tr>"; 
					 }
				}
				 if(str.startsWith("NTE")){
					 if(str.split("[|]").length>=4){
						 htmlData += "<tr style='font-size:14px;color: #6E6E6E;height: 40px;font-family: HelveticaNeue-Medium,Helvetica Neue,sans-serif;' ><td>"+str.split("[|]")[3].replaceAll("\\\\.br\\\\","<br/>").replaceAll("\\\\T\\\\", "&")+"</td></tr>";
					 }
				}
			 }
			 htmlData += "</table>";
			 in.close();
		} catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		}		 
		return htmlData;
	}
	
	@Override
	public String getLabName(String filePath) throws Exception {
		BufferedReader in  = null;
		String labName = "", str = "";
		try{
			 in	= new BufferedReader(new FileReader(filePath));
			 while ((str = in.readLine()) != null) {
				 if(str.startsWith("OBR")) {
					 if(str.split("[|]").length >= 5) {
						 labName = str.split("[|]")[4].replaceAll("\\\\.br\\\\","<br/>").replaceAll("\\\\T\\\\", "&"); 
						 if( labName.split("\\^").length >= 2 ) {
							 labName = labName.split("\\^")[1];
						 }
					 }
				 }
			 }			
			 in.close();
		} catch(Exception e) {
			 e.printStackTrace();
			 throw e;
		}		 
		return labName;
	}
}
