package com.glenwood.glaceemr.server.application.services.DirectMail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.sql.Date;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.Bean.SharedFolderBean;
import com.glenwood.glaceemr.server.application.models.Chart;
import com.glenwood.glaceemr.server.application.models.Chart_;
import com.glenwood.glaceemr.server.application.models.DirectMailPermit;
import com.glenwood.glaceemr.server.application.models.DirectMailPermit_;
import com.glenwood.glaceemr.server.application.models.DirectSentItems;
import com.glenwood.glaceemr.server.application.models.DirectSentItems_;
import com.glenwood.glaceemr.server.application.models.DocumentWorkflow;
import com.glenwood.glaceemr.server.application.models.DocumentWorkflowAttachment;
import com.glenwood.glaceemr.server.application.models.DocumentWorkflow_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.PatientRepresentativesProfile;
import com.glenwood.glaceemr.server.application.models.PatientRepresentativesProfile_;
import com.glenwood.glaceemr.server.application.repositories.ChartRepository;
import com.glenwood.glaceemr.server.application.repositories.DirectMailPermitRepository;
import com.glenwood.glaceemr.server.application.repositories.DirectSentItemsRepository;
import com.glenwood.glaceemr.server.application.repositories.DocumentWorkflowAttachmentRepository;
import com.glenwood.glaceemr.server.application.repositories.DocumentWorkflowRepository;
import com.glenwood.glaceemr.server.application.repositories.EmpProfileRepository;
import com.glenwood.glaceemr.server.application.repositories.InitialSettingsRepository;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditLogConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailService;
import com.glenwood.glaceemr.server.datasource.TennantContextHolder;
import com.glenwood.glaceemr.server.utils.HttpConnectionUtils;

@Service
@Transactional
public class DirectMailServiceImpl implements DirectMailService{

	@Autowired
	EntityManager em;

	@Autowired
	ChartRepository chartrep;

	@Autowired
	EmpProfileRepository Empprof;

	@Autowired
	DirectSentItemsRepository directSentItems;

	@Autowired
	InitialSettingsRepository initialsetting;
	
	
	@Autowired
	DocumentWorkflowRepository documentWorkflowRepository;

	@Autowired
	DirectMailPermitRepository directMailPermit;

	@Autowired
	DocumentWorkflowAttachmentRepository documentWorkflowAttachmentRepository;

	

	@Autowired
	AuditTrailService auditTrailService;

	DirectMessageStatus status=new DirectMessageStatus();

	@Override
	public DirectMessageStatus sendMail(DirectBasicDetailsSent bean,HttpServletRequest request) throws Exception {

		try {
		
			CriteriaBuilder builder = em.getCriteriaBuilder();
			String SharedFolderPath=bean.getSharedFolderPath();
			String filenamexml="";
			String filenamehtml="";
			List<Attachment> attachments=new ArrayList<Attachment>();
			if(bean.getHreadable()||bean.getElectronic()){
				if(bean.getFileName().size()>0){
					if(bean.getElectronic()){

						if(getExtension(bean.getFileName().get(0)).contains("xml"))
							filenamexml=bean.getFileName().get(0);
						else
							filenamexml=bean.getFileName().get(1);


						String line=encodeFileToBase64Binary(SharedFolderPath+"/documents/sent_items/ccdxml/"+filenamexml);
						Attachment attachment=new Attachment();
						attachment.setContent(line);
						attachment.setContentType("application/xml");
						attachment.setFilename(filenamexml);

						attachments.add(attachment);

					}
					if(bean.getHreadable()){

						if(getExtension(bean.getFileName().get(0)).contains("html"))
							filenamehtml=bean.getFileName().get(0);
						else
							filenamehtml=bean.getFileName().get(1);

						String line=encodeFileToBase64Binary(SharedFolderPath+"/documents/sent_items/ccdhtml/"+filenamehtml);
						Attachment attachment=new Attachment();
						attachment.setContent(line);
						attachment.setContentType("text/html");
						attachment.setFilename(filenamehtml);

						attachments.add(attachment);				

					}
				}
			}    
			if(bean.filenameArray.size()>0){
				for(int i=0;i<bean.filenameArray.size();i++){
					String filename=bean.filenameArray.get(i);
					String ext= getExtension(filename).trim();
					if(ext.equalsIgnoreCase("pdf")){
						
						String savingpath = SharedFolderPath+"/daily_other_Attachments"+"/"+"ConvertedPdf"+"/"+getBillingDateWisePathFormat();
						String line=encodeFileToBase64Binary(savingpath+"/"+filename);
						Attachment attachment=new Attachment();
						attachment.setContent(line);
						attachment.setContentType("application/pdf");
						attachment.setFilename(filename);
						attachments.add(attachment);
					}

					else if(ext.equalsIgnoreCase("doc") || ext.equalsIgnoreCase("docx") || ext.equalsIgnoreCase("docs") || ext.equalsIgnoreCase("odt")){
						String savingpath = SharedFolderPath+"/Attachments";
						String line=encodeFileToBase64Binary(savingpath+"/"+filename);
						Attachment attachment=new Attachment();
						attachment.setContent(line);
						attachment.setContentType("application/msword");
						attachment.setFilename(filename);
						attachments.add(attachment);
					}
					else if(ext.equalsIgnoreCase("xls") || ext.equalsIgnoreCase("xlsx") || ext.equalsIgnoreCase("csv")){
						String savingpath = SharedFolderPath+"/Attachments";
						String line=encodeFileToBase64Binary(savingpath+"/"+filename);
						Attachment attachment=new Attachment();
						attachment.setContent(line);
						attachment.setContentType("application/ms-excel");
						attachment.setFilename(filename);
						attachments.add(attachment);
					}else if(ext.equalsIgnoreCase("zip")){
						String savingpath = SharedFolderPath+"/Attachments";
						String line=encodeFileToBase64Binary(savingpath+"/"+filename);
						Attachment attachment=new Attachment();
						attachment.setContent(line);
						attachment.setContentType("application/zip");
						attachment.setFilename(filename);
						attachments.add(attachment);
					}
					else if(ext.equalsIgnoreCase("xml")){
						String savingpath = SharedFolderPath+"/Attachments";
						String line=encodeFileToBase64Binary(savingpath+"/"+filename);
						Attachment attachment=new Attachment();
						attachment.setContent(line);
						attachment.setContentType("application/xml");
						attachment.setFilename(filename);
						attachments.add(attachment);
					}
					else if(ext.equalsIgnoreCase("html")){
						String savingpath = SharedFolderPath+"/Attachments";
						String line=encodeFileToBase64Binary(savingpath+"/"+filename);
						Attachment attachment=new Attachment();
						attachment.setContent(line);
						attachment.setContentType("application/html");
						attachment.setFilename(filename);
						attachments.add(attachment);
					}
					else{

						String savingpath =SharedFolderPath+"/Attachments";
						String line=encodeFileToBase64Binary(savingpath+"/"+filename);
						Attachment attachment=new Attachment();
						attachment.setContent(line);
						attachment.setContentType("text/plain");
						attachment.setFilename(filename);
						attachments.add(attachment);

					}
				}
			}
			if(bean.filenameArrayDoc.size()>0){
				for(int i=0;i<bean.filenameArrayDoc.size();i++){
					String filename=bean.filenameArrayDoc.get(i);
					String ext= getExtension(filename).trim();
					if(ext.equalsIgnoreCase("pdf")){
						String savingpath = SharedFolderPath+"/daily_other_Attachments"+"/"+"ConvertedPdf"+"/"+getBillingDateWisePathFormat();
						String line=encodeFileToBase64Binary(savingpath+"/"+filename);
						Attachment attachment=new Attachment();
						attachment.setContent(line);
						attachment.setContentType("application/pdf");
						attachment.setFilename(filename);
						attachments.add(attachment);
					}

					else if(ext.equalsIgnoreCase("doc") || ext.equalsIgnoreCase("docx") || ext.equalsIgnoreCase("docs") || ext.equalsIgnoreCase("odt")){
						String savingpath = SharedFolderPath+"/Attachments/"+bean.getPatientId();
						String line=encodeFileToBase64Binary(savingpath+"/"+filename);
						Attachment attachment=new Attachment();
						attachment.setContent(line);
						attachment.setContentType("application/msword");
						attachment.setFilename(filename);
						attachments.add(attachment);
					}
					else if(ext.equalsIgnoreCase("xml")){
						String savingpath = SharedFolderPath+"/Attachments/"+bean.getPatientId();
						String line=encodeFileToBase64Binary(savingpath+"/"+filename);
						Attachment attachment=new Attachment();
						attachment.setContent(line);
						attachment.setContentType("application/xml");
						attachment.setFilename(filename);
						attachments.add(attachment);
					}
					else if(ext.equalsIgnoreCase("html")){
						String savingpath = SharedFolderPath+"/Attachments/"+bean.getPatientId();
						String line=encodeFileToBase64Binary(savingpath+"/"+filename);
						Attachment attachment=new Attachment();
						attachment.setContent(line);
						attachment.setContentType("application/html");
						attachment.setFilename(filename);
						attachments.add(attachment);
					}
					else{
						String savingpath =SharedFolderPath+"/Attachments/"+bean.getPatientId();
						String line=encodeFileToBase64Binary(savingpath+"/"+filename);
						Attachment attachment=new Attachment();
						attachment.setContent(line);
						attachment.setContentType("text/plain");
						attachment.setFilename(filename);
						attachments.add(attachment);

					}
				}
			}
			Body body=new Body();
			String body_content=new String(Base64.encodeBase64(bean.getBody().getBytes()));
			body.setContent(body_content);
			body.setContentType("text/plain");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			Date date = new Date();
			DirectMessage directmessage=null;
			try {
				directmessage = new DirectMessage(bean.getFromAddress(), bean.getToAddress(), body,attachments,bean.getSubject(),bean.getAccountID(), "", dateFormat.format(date));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			CriteriaQuery<Integer> criteriaQuery = builder.createQuery(Integer.class);
			Root<Chart> root = criteriaQuery.from(Chart.class);
			criteriaQuery.select(root.get(Chart_.chartId)).where((builder.and(
					builder.equal(root.get(Chart_.chartPatientid),bean.getPatientId())))
					);
			Integer chartid=em.createQuery(criteriaQuery).getSingleResult();
			Integer userType = bean.getLoginType();
			Integer userId = null;
			String userName = null;
			CriteriaQuery<String> criteriaQuery1 = builder.createQuery(String.class);
			if(userType==1){
				userId = bean.getUserId();
				Root<EmployeeProfile> root1 = criteriaQuery1.from(EmployeeProfile.class);
				criteriaQuery1.select(root1.get(EmployeeProfile_.empProfileFullname)).where((builder.and(
						builder.equal(root1.get(EmployeeProfile_.empProfileEmpid),userId)))
						);
				userName=em.createQuery(criteriaQuery1).getSingleResult();
			}
			else if(userType==2){
				userId = bean.getPatientId();
			}

			else if(userType==3){
				userId = bean.getRepId();
				Root<PatientRepresentativesProfile> root1 = criteriaQuery1.from(PatientRepresentativesProfile.class);
				criteriaQuery1.select(root1.get(PatientRepresentativesProfile_.fname)).where((builder.and(
						builder.equal(root1.get(PatientRepresentativesProfile_.id),userId)))
						);
				userName=em.createQuery(criteriaQuery1).getSingleResult();
				userName += " ";
				criteriaQuery1.select(root1.get(PatientRepresentativesProfile_.mname)).where((builder.and(
						builder.equal(root1.get(PatientRepresentativesProfile_.id),userId)))
						);
				userName +=em.createQuery(criteriaQuery1).getSingleResult();
				userName += " ";
				criteriaQuery1.select(root1.get(PatientRepresentativesProfile_.lname)).where((builder.and(
						builder.equal(root1.get(PatientRepresentativesProfile_.id),userId)))
						);
				userName += em.createQuery(criteriaQuery1).getSingleResult();
			}
			CriteriaQuery<String> criteriaQuery2 = builder.createQuery(String.class);
			Root<EmployeeProfile> root1 = criteriaQuery2.from(EmployeeProfile.class);
			criteriaQuery2.select(root1.get(EmployeeProfile_.empProfileDirectMailid)).where((builder.and(
					builder.equal(root1.get(EmployeeProfile_.empProfileEmpid),userId)))
					);
			List<String> mailId=em.createQuery(criteriaQuery2).getResultList();
			if(mailId.size()>0){
			if(bean.getFromAddress().contentEquals(mailId.get(0))){
				try{

					DirectMessageStatus statusTemp=	putEntry(directmessage, bean,userId);

					
					if(statusTemp.getStatusLabel().equalsIgnoreCase("success")){
						request.setAttribute("responseMessage",status.getStatusMessage());
						if(userType==1){
							auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.SENT,-1,AuditLogConstants.SUCCESS,"Doctor "+userName+" transmitted  clinical summary from EMR",userId,"127.0.01",request.getRemoteAddr(),bean.getPatientId(),chartid,-1,AuditLogConstants.USER_LOGIN,request,"Doctor "+userName+" transmitted  clinical summary from EMR");
						}
						if(userType==2){
							auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PatientPortal,AuditLogConstants.SENT,-1,AuditLogConstants.SUCCESS,"Patient transmitted clinical summary from patient portal",userId,"127.0.01",request.getRemoteAddr(),bean.getPatientId(),chartid,-1,AuditLogConstants.USER_LOGIN,request,"Patient transmitted clinical summary from patient portal");
						}
						if(userType==3){
							auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PatientPortal,AuditLogConstants.SENT,-1,AuditLogConstants.SUCCESS,"Representative "+userName+" transmitted clinical summary",userId,"127.0.01",request.getRemoteAddr(),bean.getPatientId(),chartid,-1,AuditLogConstants.USER_LOGIN,request,"Representative "+userName+" transmitted clinical summary");
						}
					}else{
						request.setAttribute("errorMessage",status.getStatusMessage());
						if(userType==1){
							auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.CANCELED,-1,AuditLogConstants.FAILURE,"Error in transmitted  clinical summary from EMR by Doctor: '"+userName+"'",userId,"127.0.01",request.getRemoteAddr(),bean.getPatientId(),chartid,-1,AuditLogConstants.USER_LOGIN,request,status.getStatusMessage());
						}
						if(userType==2){
							auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PatientPortal,AuditLogConstants.CANCELED,-1,AuditLogConstants.FAILURE,"Error in Patient transmitted clinical summary from patient portal",userId,"127.0.01",request.getRemoteAddr(),bean.getPatientId(),chartid,-1,AuditLogConstants.USER_LOGIN,request,status.getStatusMessage());
						}
						if(userType==3){
							auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PatientPortal,AuditLogConstants.CANCELED,-1,AuditLogConstants.FAILURE,"Error in Representative "+userName+" transmitted clinical summary",userId,"127.0.01",request.getRemoteAddr(),bean.getPatientId(),chartid,-1,AuditLogConstants.USER_LOGIN,request,status.getStatusMessage());
						}
					}
				}
				catch (Exception e){
					status.setStatusCode(0);
					status.setStatusMessage("Error :"+e.getMessage());
					e.printStackTrace();
					String error= e.getMessage();
					request.setAttribute("errorMessage",error);
					if(userType==1){
						auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.CANCELED,-1,AuditLogConstants.FAILURE,"Error in transmitted  clinical summary from EMR by Doctor: '"+userName+"'",userId,"127.0.01",request.getRemoteAddr(),bean.getPatientId(),chartid,-1,AuditLogConstants.USER_LOGIN,request,error);
					}
					if(userType==2){
						auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PatientPortal,AuditLogConstants.CANCELED,-1,AuditLogConstants.FAILURE,"Error in Patient transmitted clinical summary from patient portal",userId,"127.0.01",request.getRemoteAddr(),bean.getPatientId(),chartid,-1,AuditLogConstants.USER_LOGIN,request,error);
					}
					if(userType==3){
						auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PatientPortal,AuditLogConstants.CANCELED,-1,AuditLogConstants.FAILURE,"Error in Representative "+userName+" transmitted clinical summary",userId,"127.0.01",request.getRemoteAddr(),bean.getPatientId(),chartid,-1,AuditLogConstants.USER_LOGIN,request,error);
					}
				}     
			}}
			else{
				

				CriteriaQuery<String> criteriaQuery4 = builder.createQuery(String.class);
				Root<EmployeeProfile> root4 = criteriaQuery4.from(EmployeeProfile.class);
				criteriaQuery4.select(root4.get(EmployeeProfile_.empProfileDoctorid))
				.where(builder.equal(root4.get(EmployeeProfile_.empProfileLoginid),bean.getLoginId())
						);
				String  loginid=em.createQuery(criteriaQuery4).getSingleResult();
				CriteriaQuery<String> criteriaQuery3 = builder.createQuery(String.class);
				Root<DirectMailPermit> root3 = criteriaQuery3.from(DirectMailPermit.class);
				Join<DirectMailPermit,EmployeeProfile> permitjoin = root3.join(DirectMailPermit_.empProfile, JoinType.INNER);
				criteriaQuery3.select(permitjoin.get(EmployeeProfile_.empProfileDirectMailid))
				.where(builder.equal(root3.get(DirectMailPermit_.empId),loginid));
				DirectMessageStatus statusTemp1=null;		
				List<String> mailid=em.createQuery(criteriaQuery3).getResultList();
				int flag=0;
				for(int j=0;j<mailid.size();j++){
					if(bean.getFromAddress().contentEquals(mailid.get(j))){
						flag=1;
					}
				}
				if(flag==1){
					statusTemp1=	putEntry(directmessage, bean,userId);

				}




				if(statusTemp1.getStatusLabel().equalsIgnoreCase("success")){
					request.setAttribute("responseMessage",status.getStatusMessage());
					if(userType==1){
						auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.SENT,-1,AuditLogConstants.SUCCESS,"Doctor "+userName+" transmitted  clinical summary from EMR",userId,"127.0.01",request.getRemoteAddr(),bean.getPatientId(),chartid,-1,AuditLogConstants.USER_LOGIN,request,"Doctor "+userName+" transmitted  clinical summary from EMR");
					}
					if(userType==2){
						auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PatientPortal,AuditLogConstants.SENT,-1,AuditLogConstants.SUCCESS,"Patient transmitted clinical summary from patient portal",userId,"127.0.01",request.getRemoteAddr(),bean.getPatientId(),chartid,-1,AuditLogConstants.USER_LOGIN,request,"Patient transmitted clinical summary from patient portal");
					}
					if(userType==3){
						auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PatientPortal,AuditLogConstants.SENT,-1,AuditLogConstants.SUCCESS,"Representative "+userName+" transmitted clinical summary",userId,"127.0.01",request.getRemoteAddr(),bean.getPatientId(),chartid,-1,AuditLogConstants.USER_LOGIN,request,"Representative "+userName+" transmitted clinical summary");
					}
				}else{
					request.setAttribute("errorMessage",status.getStatusMessage());
					if(userType==1){
						auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.Chart,AuditLogConstants.CANCELED,-1,AuditLogConstants.FAILURE,"Error in transmitted  clinical summary from EMR by Doctor: '"+userName+"'",userId,"127.0.01",request.getRemoteAddr(),bean.getPatientId(),chartid,-1,AuditLogConstants.USER_LOGIN,request,status.getStatusMessage());
					}
					if(userType==2){
						auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PatientPortal,AuditLogConstants.CANCELED,-1,AuditLogConstants.FAILURE,"Error in Patient transmitted clinical summary from patient portal",userId,"127.0.01",request.getRemoteAddr(),bean.getPatientId(),chartid,-1,AuditLogConstants.USER_LOGIN,request,status.getStatusMessage());
					}
					if(userType==3){
						auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.PatientPortal,AuditLogConstants.CANCELED,-1,AuditLogConstants.FAILURE,"Error in Representative "+userName+" transmitted clinical summary",userId,"127.0.01",request.getRemoteAddr(),bean.getPatientId(),chartid,-1,AuditLogConstants.USER_LOGIN,request,status.getStatusMessage());
					}
				}
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status.setStatusCode(0);
			status.setStatusMessage("Error :"+e.getMessage());
		}
		return status;
	}

	@Override
	public String insertInWorkflow(DirectMessage bean,HttpServletRequest request)  {
		String responseMsg="";
		try {
			// TODO Auto-generated method stub

			DocumentWorkflow workflow = new DocumentWorkflow();

			workflow.setSourceInformation(bean.getFromAddress());
			workflow.setDestinationInformation(bean.getToAddress());
			workflow.setNotes(bean.getSubject());
			workflow.setIsactive(true);
			workflow.setSentOn(Timestamp.valueOf(bean.getSentOn()));
			String	temp_content= new String(Base64.decodeBase64(bean.getBody().getContent()));
			workflow.setBody(temp_content);
			documentWorkflowRepository.saveAndFlush(workflow);
	//		auditTrailService.LogEvent(Log_Type, Log_Component, Event_Type, parent_Event, Event_Outcome, Description, User_Id, ClientIP, patientId, chartId, encounterId, LoginType)
			responseMsg="Data successfully added into document_workflow";
		} catch (Exception e) {

			e.printStackTrace();
			responseMsg="Error :"+e.getMessage();
		}
		return responseMsg;

	}

	@Override
	public String insertInWorkflowAttachment(DirectMessage bean,String SharedFolderPath2,HttpServletRequest request) throws Exception {
		String responseMsg="";
		try {
			// TODO Auto-generated method stub
			DocumentWorkflowAttachment workflowAttachment = new DocumentWorkflowAttachment();
			CriteriaBuilder builder = em.getCriteriaBuilder();
			SharedFolderBean SharedFolderPath1=new SharedFolderBean();
			System.out.println(SharedFolderPath1.getSharedFolderPath());/*.get(TennantContextHolder.getTennantId());*/
			String temp_content=new String();
			String filename=new String() ;
			;
			System.out.println();
			CriteriaQuery<Integer> criteriaQuery = builder.createQuery(Integer.class);
			Root<DocumentWorkflow> root = criteriaQuery.from(DocumentWorkflow.class);
			TypedQuery<Integer> typedQuery = em.createQuery (criteriaQuery
					.select(builder.greatest(root.get(DocumentWorkflow_.id))));
			Integer maxId=typedQuery.getSingleResult();
			for(int i=0;i<bean.getAttachments().size();i++){
				SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
				SimpleDateFormat targetFormat = new SimpleDateFormat("MMddyyyyHHmmss"); 
				java.util.Date date1 = originalFormat.parse(bean.getSentOn().toString()); 
				String date = targetFormat.format(date1);
				String ext = getExtension(bean.getAttachments().get(i).getFilename()) ;
				filename=	bean.getAttachments().get(i).getFilename().substring(0,bean.getAttachments().get(i).getFilename().lastIndexOf('.'))+date+"."+ext;
				temp_content= new String(Base64.decodeBase64(bean.getAttachments().get(i).getContent().toString()));
				File file =new File(SharedFolderPath1.getSharedFolderPath().get(TennantContextHolder.getTennantId())+"/documents"+"/"+"unprocessed/"+filename);
				if(!file.getParentFile().exists()){
					file.getParentFile().mkdirs();
				}
				if(!ext.equals("zip")){
				 FileOutputStream fos = null;
				 fos = new FileOutputStream(SharedFolderPath1.getSharedFolderPath().get(TennantContextHolder.getTennantId())+"/documents"+"/"+"unprocessed/"+filename);
					fos.write(Base64.decodeBase64(bean.getAttachments().get(i).getContent().toString()));
					fos.close();
				}else{
					FileOutputStream fos = null;
					FileInputStream fisDecoded = null;
					ZipInputStream zis = null;
					ZipFile zipFileAbstract=null;
					File decodedFile = new File(SharedFolderPath1.getSharedFolderPath().get(TennantContextHolder.getTennantId())+"/documents"+"/"+"unprocessed/"+filename);

					 if(!decodedFile.getParentFile().exists()){
						 decodedFile.getParentFile().mkdirs();
						}
					fos = new FileOutputStream(decodedFile);
					fos.write(Base64.decodeBase64(bean.getAttachments().get(i).getContent().toString()));
					fisDecoded = new FileInputStream(decodedFile);
					zis = new ZipInputStream(fisDecoded);
					zipFileAbstract=new ZipFile(SharedFolderPath1.getSharedFolderPath().get(TennantContextHolder.getTennantId())+"/documents"+"/"+"unprocessed/"+filename);

				fos.close();
				fisDecoded.close();
				zis.close();
				zipFileAbstract.close();
				}
					workflowAttachment.setId(-1);
				workflowAttachment.setContentType(bean.getAttachments().get(i).getContentType());
				workflowAttachment.setDocumentWorkflowId(maxId);
				workflowAttachment.setFileName("/documents"+"/"+"unprocessed/"+filename);
				documentWorkflowAttachmentRepository.saveAndFlush(workflowAttachment);
			
			}
			responseMsg="Data successfully added into document_workflow_attachments";
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			responseMsg="Error ;"+e.getMessage();
		}
		return responseMsg;



	}


	public String getExtension(String f){
		String ext = "";
		try {
			int i = f.lastIndexOf('.');
			if (i > 0 && i < f.length() - 1) {
				ext = f.substring(i + 1).toLowerCase();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ext;
	}

	private String encodeFileToBase64Binary(String fileName) throws IOException {

		File file = new File(fileName);
		byte[] bytes = loadFile(file);
		byte[] encoded = Base64.encodeBase64(bytes);
		String encodedString = new String(encoded);

		return encodedString;
	}

	private static byte[] loadFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		long length = file.length();
		if (length > Integer.MAX_VALUE) {
			// File is too large
		}
		byte[] bytes = new byte[(int)length];

		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
			offset += numRead;
		}

		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "+file.getName());
		}

		is.close();
		return bytes;
	}
	public static String getBillingDateWisePathFormat(){
		return getTime("yyyy/MM/dd");
	}
	public static String getTime(String pattern){	
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		Date d = new Date();
		try {
			dateFormat.applyPattern(pattern);
			return dateFormat.format(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String mdnReceiver(DirectMessageStatus bean,HttpServletRequest request)  {
		// TODO Auto-generated method stub

		CriteriaBuilder builder = em.getCriteriaBuilder();
		String responseMsg="";

		try {
			CriteriaUpdate<DirectSentItems> update=builder.createCriteriaUpdate(DirectSentItems.class);
			Root<DirectSentItems> root = update.from(DirectSentItems.class);
			update.set("statusCode", bean.getStatusCode());
			update.set("statusMessage", bean.getStatusMessage());

			update.where(builder.equal(root.get(DirectSentItems_.messageId), bean.getMessageId()));
			int result=em.createQuery(update).executeUpdate();
			if(result!=-1){
				responseMsg="successfully added";
			}
			auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.DirectMessage,AuditLogConstants.CREATED,-1,AuditLogConstants.SUCCESS,"MDN received ",-1,"127.0.01",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.USER_LOGIN,request,"MDN received ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseMsg="Error :"+e.getMessage();
			auditTrailService.LogEvent(AuditLogConstants.GLACE_LOG,AuditLogConstants.DirectMessage,AuditLogConstants.CREATED,-1,AuditLogConstants.FAILURE,"Error while receiving MDN",-1,"127.0.01",request.getRemoteAddr(),-1,-1,-1,AuditLogConstants.USER_LOGIN,request,"Error while receiving MDN");
		}
		return responseMsg;


	}
	private DirectMessageStatus putEntry(DirectMessage directmessage,DirectBasicDetailsSent bean,int userId) throws JsonProcessingException, Exception{



		ObjectMapper mapper = new ObjectMapper();
		String response = HttpConnectionUtils.postData("https://test.glacecentral.com/Gateway/DirectServices/send", mapper.writeValueAsString(directmessage), HttpConnectionUtils.HTTP_CONNECTION_MODE);
	//	String response = HttpConnectionUtils.postData("http://192.168.2.53:7080/directgateway/DirectServices/send", mapper.writeValueAsString(directmessage), HttpConnectionUtils.HTTP_CONNECTION_MODE);
		ObjectMapper objMapper = new ObjectMapper(); 
		status=objMapper.readValue(response,DirectMessageStatus.class );
DocumentWorkflowAttachment workflowAttachment = new DocumentWorkflowAttachment();		

if(bean.getFileName().size()>0)
for(int i=0;i<bean.getFileName().size();i++){
	String filename=bean.getFileName().get(i);
	String ext= getExtension(filename).trim();
	if(ext.equalsIgnoreCase("xml")){
			workflowAttachment.setId(-1);
			workflowAttachment.setContentType("application/xml");
			workflowAttachment.setFileName("/documents/sent_items/ccdxml/"+filename);
			workflowAttachment.setMessageId(status.getMessageId());
			documentWorkflowAttachmentRepository.saveAndFlush(workflowAttachment);
		
	}
	else if(ext.equalsIgnoreCase("html")){

		workflowAttachment.setId(-1);
		workflowAttachment.setContentType("text/html");
		workflowAttachment.setFileName("/documents/sent_items/ccdhtml/"+filename);
		workflowAttachment.setMessageId(status.getMessageId());
		documentWorkflowAttachmentRepository.saveAndFlush(workflowAttachment);

	}
	
	

}


if(bean.getFilenameArrayDoc().size()>0)
for(int i=0;i<bean.getFilenameArrayDoc().size();i++){
			
			String filename=bean.getFilenameArrayDoc().get(i);
			String ext= getExtension(filename).trim();
			if(ext.equalsIgnoreCase("pdf")){
				workflowAttachment.setId(-1);
				workflowAttachment.setContentType("application/pdf");
				workflowAttachment.setFileName("/daily_other_Attachments"+"/"+"ConvertedPdf"+"/"+getBillingDateWisePathFormat()+"/"+filename);
				workflowAttachment.setMessageId(status.getMessageId());

			}else if(ext.equalsIgnoreCase("doc") || ext.equalsIgnoreCase("docx") || ext.equalsIgnoreCase("docs") || ext.equalsIgnoreCase("odt")){

				workflowAttachment.setId(-1);
				workflowAttachment.setContentType("application/msword");
				workflowAttachment.setFileName( "/Attachments/"+bean.getPatientId()+"/"+filename);
				workflowAttachment.setMessageId(status.getMessageId());


			}else if(ext.equalsIgnoreCase("xls") || ext.equalsIgnoreCase("xlsx") || ext.equalsIgnoreCase("csv")){

				workflowAttachment.setId(-1);
				workflowAttachment.setContentType("application/ms-excel");
				workflowAttachment.setFileName( "/Attachments/"+bean.getPatientId()+"/"+filename);
				workflowAttachment.setMessageId(status.getMessageId());


			}else if(ext.equalsIgnoreCase("xml")){
					workflowAttachment.setId(-1);
					workflowAttachment.setContentType("application/xml");
					workflowAttachment.setFileName( "/Attachments/"+bean.getPatientId()+"/"+filename);
					workflowAttachment.setMessageId(status.getMessageId());

				
			}
			else if(ext.equalsIgnoreCase("html")){
				
					workflowAttachment.setId(-1);
					workflowAttachment.setContentType("application/html");
					workflowAttachment.setFileName("/Attachments/"+bean.getPatientId()+"/"+filename);
					workflowAttachment.setMessageId(status.getMessageId());

				
			}
			else{
				workflowAttachment.setId(-1);
				workflowAttachment.setContentType("text/plain");
				workflowAttachment.setFileName( "/Attachments/"+bean.getPatientId()+"/"+filename);
				workflowAttachment.setMessageId(status.getMessageId());

			}
			documentWorkflowAttachmentRepository.saveAndFlush(workflowAttachment);
		}

if(bean.getFilenameArray().size()>0)
for(int i=0;i<bean.getFilenameArray().size();i++){
	
	String filename=bean.getFilenameArray().get(i);
	String ext= getExtension(filename).trim();
	if(ext.equalsIgnoreCase("pdf")){
		workflowAttachment.setId(-1);
		workflowAttachment.setContentType("application/pdf");
		workflowAttachment.setFileName("/daily_other_Attachments"+"/"+"ConvertedPdf"+"/"+getBillingDateWisePathFormat()+"/"+filename);
		workflowAttachment.setMessageId(status.getMessageId());

	}else if(ext.equalsIgnoreCase("doc") || ext.equalsIgnoreCase("docx") || ext.equalsIgnoreCase("docs") || ext.equalsIgnoreCase("odt")){

		workflowAttachment.setId(-1);
		workflowAttachment.setContentType("application/msword");
		workflowAttachment.setFileName( "/Attachments/"+filename);
		workflowAttachment.setMessageId(status.getMessageId());


	}else if(ext.equalsIgnoreCase("zip")){

		workflowAttachment.setId(-1);
		workflowAttachment.setContentType("application/zip");
		workflowAttachment.setFileName( "/Attachments/"+filename);
		workflowAttachment.setMessageId(status.getMessageId());


	}
	else if(ext.equalsIgnoreCase("xls") || ext.equalsIgnoreCase("xlsx") || ext.equalsIgnoreCase("csv")){

		workflowAttachment.setId(-1);
		workflowAttachment.setContentType("application/ms-excel");
		workflowAttachment.setFileName( "/Attachments/"+filename);
		workflowAttachment.setMessageId(status.getMessageId());


	}
	else if(ext.equalsIgnoreCase("xml")){
			workflowAttachment.setId(-1);
			workflowAttachment.setContentType("application/xml");
			workflowAttachment.setFileName( "/Attachments/"+filename);
			workflowAttachment.setMessageId(status.getMessageId());

		
	}
	else if(ext.equalsIgnoreCase("html")){
		
			workflowAttachment.setId(-1);
			workflowAttachment.setContentType("application/html");
			workflowAttachment.setFileName("/Attachments/"+filename);
			workflowAttachment.setMessageId(status.getMessageId());

		
	}
	else{
		workflowAttachment.setId(-1);
		workflowAttachment.setContentType("text/plain");
		workflowAttachment.setFileName( "/Attachments/"+filename);
		workflowAttachment.setMessageId(status.getMessageId());

	}
	documentWorkflowAttachmentRepository.saveAndFlush(workflowAttachment);
}

		DirectSentItems directsent= new DirectSentItems();
		directsent.setFromAddress(directmessage.getFromAddress());
		directsent.setMessageId(status.getMessageId());
		directsent.setToAddress(directmessage.getToAddress());
		directsent.setSubject(directmessage.getSubject());
		directsent.setSentOn(Timestamp.valueOf(directmessage.getSentOn()));
		directsent.setSentBy(userId);
		directsent.setSentByType(bean.getLoginType());
		directsent.setPatientId(bean.getPatientId());
		directsent.setBody(bean.getBody());
		directsent.setIsactive(true);
		directsent.setStatusCode(status.getStatusCode());
		directsent.setStatusMessage(status.getStatusMessage());
		directSentItems.saveAndFlush(directsent);
		//CriteriaBuilder builder1 = em.getCriteriaBuilder();
		//	int lginid=Integer.parseInt(bean.getLoginId());
		return status;


	}

}