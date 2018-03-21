package com.glenwood.glaceemr.server.application.services.portal.portalForms;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class HTMLtoPdfConverter {
	
	String paramname = "successfully pdf generated";  	
	public String  pdfConverter(String destinationpath,String htmlfiles, int patientId, String fileIdName){
		String s = null;
		File Outfile=null;
		String fileName=fileIdName.trim().replace(" ", "");
	    String pathSeperator	=	java.io.File.separator;
		try{		
			
			String destnormal=destinationpath+pathSeperator+fileName;
			Process p2 = Runtime.getRuntime().exec(pathSeperator+"usr"+pathSeperator+"bin"+pathSeperator+"wkhtmltopdf   "+ htmlfiles.trim()+" "+destnormal);
			Outfile = new File(destnormal);
			
			BufferedReader stdInput2 = new BufferedReader(new  InputStreamReader(p2.getInputStream()));
			BufferedReader stdError2 = new BufferedReader(new InputStreamReader(p2.getErrorStream()));
	        while ((s = stdInput2.readLine()) != null) {
	         
	          //return "Error";
	        }
	        while ((s = stdError2.readLine()) != null) {
	          
	          // return "Error";
	        }
	        return fileName;
	        
		}catch(Exception e){
	    	paramname="Please install Wkhtmltopdf";
	    	return "Error";
	    }
		
	}
}