package com.glenwood.glaceemr.server.application.services.portal.fileUploadService;

import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.services.portal.portalDocuments.SharedFolderUtil;
import com.glenwood.glaceemr.server.utils.HUtil;


@Service
public class FileUploadServiceImpl implements FileUploadService{

	@Override
	public String getSharedFolderPath() {
		
		String pathSeperator	=	java.io.File.separator;
		String sharedFolderPath=SharedFolderUtil.sharedFolderPath;
		String absoluteFilePath=SharedFolderUtil.sharedFolderPath;
		return absoluteFilePath;
		
	}

	@Override
	public String getFileNameOnCurrentDateAndTime(String originalFileName) {
		
		long uniqueID = System.currentTimeMillis();
		
		String newFileName =originalFileName+uniqueID;
		
		return newFileName;
	}

	
	 @Override
	    public String parseFilePath(String file_path) {


	        String fileName ="";
	        if(file_path.length()>0){
	            fileName = file_path.substring(file_path.lastIndexOf("/")+1, file_path.length());
	        }

	        return fileName;

	    }

	    @Override
	    public String parseFileType(String file_path) {

	        if(!HUtil.checkNull(file_path))
	            return file_path.substring(file_path.lastIndexOf(".")+1, file_path.length());
	        else
	            return "";
	    }
	    
	    
	    @Override
	    public String getAbsoluteFilePath(int patientId, int fileId) {
	        String filePath="" ;
	        
	        return filePath;
	    }


	    
	    @Override
	    public String getResponseContentType(String fileType) {
	        if(fileType.toLowerCase().equals("tif"))
	            return "image/tiff";
	        else if(fileType.toLowerCase().equals("html"))
	            return "text/html";
	        else if(fileType.toLowerCase().equals("htm"))
	            return "text/html";
	        else if(fileType.toLowerCase().equals("pdf"))
	            return "application/pdf";
	        else if(fileType.toLowerCase().equals("xml") || fileType.toLowerCase().equals("xsl"))
	            return "text/xml";
	        else if(fileType.toLowerCase().equals("cda"))
	            return "text/xml";
	        else if(fileType.toLowerCase().equals("jpg"))
	            return "image/jpeg";
	        else if(fileType.toLowerCase().equals("wav"))
	            return "audio/wav";
	        else if(fileType.toLowerCase().equals("rtf"))
	            return "application/rtf";
	        else if(fileType.toLowerCase().equals("png"))
	            return "image/png";
	        else if(fileType.toLowerCase().equals("gif"))
	            return "image/gif";
	        else if(fileType.toLowerCase().equals("jnlp"))
	            return "application/x-java-jnlp-file";
	        else if(fileType.toLowerCase().equals("docx"))
	            return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	        else if(fileType.toLowerCase().equals("xlsx"))
	            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	        else if(fileType.toLowerCase().equals("pptx"))
	            return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
	        else if(fileType.toLowerCase().equals("dotx"))
	            return "application/vnd.openxmlformats-officedocument.wordprocessingml.template";
	        else if(fileType.toLowerCase().equals("xltx"))
	            return "application/vnd.openxmlformats-officedocument.spreadsheetml.template";
	        else
	            return "text/plain";        
	    }
	    
	    
}
