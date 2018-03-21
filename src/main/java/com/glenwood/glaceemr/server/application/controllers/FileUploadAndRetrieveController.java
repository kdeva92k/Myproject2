package com.glenwood.glaceemr.server.application.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.glenwood.glaceemr.server.application.models.FileDataBean;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.portal.fileUploadService.FileUploadService;
import com.glenwood.glaceemr.server.application.services.portal.portalDocuments.PortalDocumentsService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.HUtil;

@RestController
@Transactional
@RequestMapping(value = "/user/FileUploadAndRetrieve")
public class FileUploadAndRetrieveController {

	@Autowired
	FileUploadService fileUploadAndRetrieveService;

	@Autowired
	PortalDocumentsService portalDocumentsService;

	@Autowired
	PortalDocumentsController portalDocumentsController;

	@Autowired
	EMRResponseBean responseBean;

	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;

	String pathSeperator = java.io.File.separator;

	@RequestMapping(value = "/upload/image", method = RequestMethod.POST, produces = "text/html")
	@ResponseBody
	public EMRResponseBean uploadPatientProfilePicture(@RequestParam(value = "file", required = false, defaultValue = "") MultipartFile file, @RequestParam(value = "patientId", required = false, defaultValue = "0") int patientId) {

		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			Assert.notNull(file, "uploaded file is empty");
			Assert.notNull(patientId, "entity Id is not passed");
			String absolutePath = fileUploadAndRetrieveService
					.getSharedFolderPath() + "/photo/";
			Assert.notNull(absolutePath, "absolute path is empty");
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File(absolutePath + patientId
							+ ".jpg")));
			stream.write(bytes);
			stream.close();
			System.out.println("File uploaded successfully!");
			responseBean.setSuccess(true);
			responseBean.setData("Profile Picture uploaded successfully!");
			auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
					AuditTrailEnumConstants.LogActionType.CREATE,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Patient with id "+patientId+" uploaded a new profile picture.",-1,
					request.getRemoteAddr(),patientId,"",
					AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with id "+patientId+" uploaded a new profile picture.","");
			return responseBean;

		} catch (Exception ex) {
			ex.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Profile picture upload failed!");
			auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
					AuditTrailEnumConstants.LogActionType.CREATE,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Patient with id "+patientId+" failed to upload a new profile picture.",-1,
					request.getRemoteAddr(),patientId,"",
					AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with id "+patientId+" failed to upload a new profile picture.","");
			return responseBean;
		}
	}

	@RequestMapping(value = "/upload/profilePicture", method = RequestMethod.POST)
	@ResponseBody
	public EMRResponseBean uploadPatientProfilePicture(
			@RequestBody FileDataBean dataBean) {

		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			int patientId = dataBean.getPatientId();
			Assert.notNull(patientId, "entity Id is not passed");
			String absolutePath = fileUploadAndRetrieveService
					.getSharedFolderPath() + "/photo/";
			Assert.notNull(absolutePath, "absolute path is empty");
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File(absolutePath + patientId
							+ ".jpg")));
			stream.write(dataBean.getByteArrayContent());
			stream.close();
			System.out.println("File uploaded successfully!");
			responseBean.setSuccess(true);
			responseBean.setData("Profile Picture uploaded successfully!");
			return responseBean;

		} catch (Exception ex) {
			ex.printStackTrace();
			responseBean.setSuccess(true);
			responseBean.setData("Profile picture upload failed!");
			return responseBean;
		}
	}

	@RequestMapping(value = "/retrieve/profilePicture", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean retrievePatientProfilePicture(@RequestParam(value = "patientId", required = false, defaultValue = "0") int patientId) {

		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			Assert.notNull(patientId, "patientId Id is not passed");
			String absolutePath = fileUploadAndRetrieveService
					.getSharedFolderPath() + "/photo/";
			Assert.notNull(absolutePath, "absolute path is empty");
			File imageFile = new File(absolutePath + patientId + ".jpg");
			byte[] byteFileStore = new byte[(int) imageFile.length()];
			@SuppressWarnings("resource")
			FileInputStream fileInputStream = new FileInputStream(imageFile);
			fileInputStream.read(byteFileStore);
			String encodedFile = Base64.encodeBase64String(byteFileStore);
			FileDataBean imageBean = new FileDataBean();
			imageBean.setStringContent(encodedFile);
			System.out.println("Image retrieved successfully!");
			responseBean.setSuccess(true);
			responseBean.setData(imageBean);
			return responseBean;
		} catch (Exception ex) {
			ex.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving file!");
			return responseBean;
		}
	}
	
	@RequestMapping(value = "/retrieve/file", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean retrievePatientFile(@RequestParam(value = "patientId", required = false, defaultValue = "0") int patientId,
			@RequestParam(value = "fileName", required = false, defaultValue = "0") String fileName,
			@RequestParam(value = "filedetailsType", required=false, defaultValue="0") int filedetailsType,
			@RequestParam(value = "fileCategory", required = false, defaultValue = "0") String fileCategory,
			HttpServletRequest request, HttpServletResponse response) {

		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			Assert.notNull(patientId, "patient Id is not passed");
			Assert.notNull(fileName, "fileName is not passed");
			Assert.notNull(fileCategory, "fileCategory is not passed");
			Assert.notNull(patientId, "patientId Id is not passed");
			String absolutePath = null;
			
			if (fileCategory.equalsIgnoreCase("ProfilePicture"))
				absolutePath = fileUploadAndRetrieveService
						.getSharedFolderPath()
						+ pathSeperator
						+ "photo"
						+ pathSeperator + patientId + pathSeperator + fileName;
			else{
				if(filedetailsType==1)
					absolutePath = fileUploadAndRetrieveService
					.getSharedFolderPath()
					+ pathSeperator
					+ "patientinfo"
					+ pathSeperator + patientId + pathSeperator + fileName;
				else if(filedetailsType==2)
					absolutePath = fileUploadAndRetrieveService
					.getSharedFolderPath()
					+ pathSeperator
					+ "Attachments"
					+ pathSeperator + patientId + pathSeperator + fileName;
				else if(filedetailsType==3)
					absolutePath = fileUploadAndRetrieveService
					.getSharedFolderPath()
					+ pathSeperator
					+ "dicomAttachment"
					+ pathSeperator + patientId + pathSeperator + fileName;
			}
			Assert.notNull(absolutePath, "absolute path is empty");
			String fileType = fileUploadAndRetrieveService .parseFileType(absolutePath);
			response.setContentType(fileUploadAndRetrieveService .getResponseContentType(fileType));
			FileDataBean dataBean = new FileDataBean();
			dataBean.setByteArrayContent(readAndReturnFileStream(absolutePath, response));
			responseBean.setSuccess(true);
			responseBean.setData(dataBean);
			return responseBean;
		} catch (Exception ex) {
			ex.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving file!");
			return responseBean;
		}
	}

	@RequestMapping(value = "/retrieve/image", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean retrievePatientImageDocuments(
			@RequestParam(value = "patientId", required = false, defaultValue = "0") int patientId,
			@RequestParam(value = "fileName", required = false, defaultValue = "0") String fileName,
			@RequestParam(value = "filedetailsType", required=false, defaultValue="0") int filedetailsType,
			@RequestParam(value = "fileCategory", required = false, defaultValue = "0") String fileCategory) {

		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			Assert.notNull(patientId, "patient Id is not passed");
			Assert.notNull(fileName, "fileName is not passed");
			Assert.notNull(fileCategory, "fileCategory is not passed");
			Assert.notNull(patientId, "patientId Id is not passed");
			String absolutePath = null;
			
			if (fileCategory.equalsIgnoreCase("ProfilePicture"))
				absolutePath = fileUploadAndRetrieveService
						.getSharedFolderPath()
						+ pathSeperator
						+ "photo"
						+ pathSeperator + patientId + pathSeperator + fileName;
			else{
				if(filedetailsType==1)
					absolutePath = fileUploadAndRetrieveService
					.getSharedFolderPath()
					+ pathSeperator
					+ "patientinfo"
					+ pathSeperator + patientId + pathSeperator + fileName;
				else if(filedetailsType==2)
					absolutePath = fileUploadAndRetrieveService
					.getSharedFolderPath()
					+ pathSeperator
					+ "Attachments"
					+ pathSeperator + patientId + pathSeperator + fileName;
				else if(filedetailsType==3)
					absolutePath = fileUploadAndRetrieveService
					.getSharedFolderPath()
					+ pathSeperator
					+ "dicomAttachment"
					+ pathSeperator + patientId + pathSeperator + fileName;
			}
			Assert.notNull(absolutePath, "absolute path is empty");
			File imageFile = new File(absolutePath);
			byte[] byteFileStore = new byte[(int) imageFile.length()];
			@SuppressWarnings("resource")
			FileInputStream fileInputStream = new FileInputStream(imageFile);
			fileInputStream.read(byteFileStore);
			String encodedFile = Base64.encodeBase64String(byteFileStore);
			FileDataBean imageBean = new FileDataBean();
			imageBean.setStringContent(encodedFile);
			System.out.println("Image retrieved successfully!");
			responseBean.setSuccess(true);
			responseBean.setData(imageBean);
			return responseBean;
		} catch (Exception ex) {
			ex.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving file!");
			return responseBean;
		}
	}

	@RequestMapping(value = "/retrieve/html", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean retrievePatientHTMLDocuments(
			@RequestParam(value = "patientId", required = false, defaultValue = "0") int patientId,
			@RequestParam(value = "fileName", required = false, defaultValue = "0") String fileName,
			@RequestParam(value = "fileCategory", required = false, defaultValue = "0") String fileCategory) {

		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			Assert.notNull(patientId, "patient Id is not passed");
			Assert.notNull(fileName, "fileName is not passed");
			Assert.notNull(fileCategory, "fileCategory is not passed");
			String absolutePath = null;
			if (fileCategory.equalsIgnoreCase("LABHTML"))
				absolutePath = fileUploadAndRetrieveService
						.getSharedFolderPath()
						+ pathSeperator
						+ "Attachments"
						+ pathSeperator + patientId + pathSeperator + fileName;
			Assert.notNull(absolutePath, "absolute path is empty");
			Document htmlDocument = Jsoup
					.parse(new File(absolutePath), "UTF-8");
			FileDataBean htmlBean = new FileDataBean();
			htmlBean.setStringContent(htmlDocument.outerHtml());
			responseBean.setSuccess(true);
			responseBean.setData(htmlBean);
			return responseBean;

		} catch (Exception ex) {
			ex.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving file!");
			return responseBean;
		}
	}

	@RequestMapping(value = "/preview/file/{patientId}/{fileName}", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean filePreviewByFileId(
			@PathVariable("patientId") int patientId,
			@PathVariable("fileName") String fileDetailsName,
			@RequestParam(value = "isToDownload", required = false) String isToDownLoad,
			HttpServletRequest request, HttpServletResponse response) {

		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			isToDownLoad = HUtil.Nz(isToDownLoad, "false");
			String filePath = fileUploadAndRetrieveService
					.getSharedFolderPath()
					+ pathSeperator
					+ "Attachments"
					+ pathSeperator
					+ patientId
					+ pathSeperator
					+ fileDetailsName + ".pdf";
			String fileName = fileUploadAndRetrieveService
					.parseFilePath(filePath);
			String fileType = fileUploadAndRetrieveService
					.parseFileType(filePath);
			if (isToDownLoad.equals("true")) {
				response.setContentType("application/pdf");
				response.setHeader("Content-Transfer-Encoding", "binary");
				response.setHeader("Content-Disposition", "inline; filename=\""
						+ fileName + "\"");
			}
			response.setContentType(fileUploadAndRetrieveService
					.getResponseContentType(fileType));
			FileDataBean dataBean = new FileDataBean();
			dataBean.setByteArrayContent(readAndReturnFileStream(filePath,
					response));
			responseBean.setSuccess(true);
			responseBean.setData(dataBean);
			return responseBean;
		} catch (Exception ex) {
			ex.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving file!");
			return responseBean;
		}
	}

	@RequestMapping(value = "/FilePreview/{patientId}/{fileCategory}/{fileName}", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean filePreviewByFileName(
			@PathVariable("patientId") int patientId,
			@PathVariable("fileCategory") String fileCategory,
			@PathVariable("fileName") String fileDetailsName,
			@RequestParam(value = "isToDownload", required = false) String isToDownLoad,
			HttpServletRequest request, HttpServletResponse response) {

		responseBean.setCanUserAccess(true);
		responseBean.setIsAuthorizationPresent(true);
		responseBean.setLogin(true);

		try {
			isToDownLoad = HUtil.Nz(isToDownLoad, "false");
			String categoryPath = "";
			if (fileCategory.equalsIgnoreCase("CDA"))
				categoryPath = pathSeperator + "GlacePortal" + pathSeperator
						+ "CDA" + pathSeperator + patientId + pathSeperator;
			else if (fileCategory.equalsIgnoreCase("ENSSTMNTS"))
				categoryPath = pathSeperator + "ENSSTMNTS" + pathSeperator;
			else if (fileCategory.equalsIgnoreCase("LABPDF"))
				categoryPath = pathSeperator + "HL7" + pathSeperator
						+ "AttachedDocs" + pathSeperator;
			else if (fileCategory.equalsIgnoreCase("documents"))
				categoryPath = pathSeperator + "patientinfo" + pathSeperator
						+ patientId + pathSeperator;
			else if (fileCategory.equalsIgnoreCase("CONSENTFORM"))
				categoryPath = pathSeperator + "Attachments" + pathSeperator
						+ patientId + pathSeperator;
			else if (fileCategory.equalsIgnoreCase("VIS"))
				categoryPath = pathSeperator + "vismaterial" + pathSeperator;
			String filePath = "";
			filePath = fileUploadAndRetrieveService.getSharedFolderPath()
					+ categoryPath + fileDetailsName + ".pdf";
			String fileName = fileUploadAndRetrieveService
					.parseFilePath(filePath);
			String fileType = fileUploadAndRetrieveService
					.parseFileType(filePath);
			if (isToDownLoad.equals("true")) {
				response.setContentType("application/pdf");
				response.setHeader("Content-Transfer-Encoding", "binary");
				response.setHeader("Content-Disposition", "inline; filename=\""
						+ fileName + "\"");
			}
			response.setContentType(fileUploadAndRetrieveService
					.getResponseContentType(fileType));
			FileDataBean dataBean = new FileDataBean();
			dataBean.setByteArrayContent(readAndReturnFileStream(filePath,
					response));
			responseBean.setSuccess(true);
			responseBean.setData(dataBean);
			return responseBean;
		} catch (Exception ex) {
			ex.printStackTrace();
			responseBean.setSuccess(false);
			responseBean.setData("Error in retrieving file!");
			return responseBean;
		}
	}

	public void writeToResponse(String filePath, HttpServletResponse response)
			throws Exception {

		FileInputStream fin = null;
		BufferedInputStream bin = null;
		ServletOutputStream sos = null;
		try {
			fin = new FileInputStream(filePath);
			bin = new BufferedInputStream(fin);
			sos = response.getOutputStream();
			byte b[] = new byte[1024];
			int length;
			while ((length = bin.read(b)) != -1) {
				sos.write(b, 0, length);
			}
			sos.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			if (sos != null)
				sos.close();
			if (bin != null)
				bin.close();
			if (fin != null)
				fin.close();
		}

	}

	public byte[] readAndReturnFileStream(String filePath,
			HttpServletResponse response) throws Exception {

		FileInputStream fin = null;
		BufferedInputStream bin = null;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			fin = new FileInputStream(filePath);
			bin = new BufferedInputStream(fin);
			byte b[] = new byte[1024];
			int length;
			while ((length = bin.read(b)) != -1) {
				outputStream.write(b, 0, length);
			}
			outputStream.flush();
			return outputStream.toByteArray();
		} catch (Exception e) {
			throw e;
		} finally {
			if (outputStream != null)
				outputStream.close();
			if (bin != null)
				bin.close();
			if (fin != null)
				fin.close();
		}

	}

}
