package com.glenwood.glaceemr.server.application.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.Bean.DocumentsCategoryBean;
import com.glenwood.glaceemr.server.application.Bean.FileNameDetailsBean;
import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.AlertPatientDocMapping;
import com.glenwood.glaceemr.server.application.models.FileDetails;
import com.glenwood.glaceemr.server.application.models.FileName;
import com.glenwood.glaceemr.server.application.models.FormsTemplate;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsNotes;
import com.glenwood.glaceemr.server.application.services.Documents.DocumentsService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

/**
 * Controller for PatientDocuments
 * @author Soundarya
 *
 */
@RestController
@Transactional
@RequestMapping(value="/user/Documents")
public class DocumentsController {

	@Autowired
	DocumentsService documentsService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;

	private Logger logger = Logger.getLogger(DocumentsController.class);

	/**
	 * TO get category list based on patientId, If the patientId is not provided then it displays all category list
	 * @param patientId
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value="/getCategoryList", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getCategoryList(@RequestParam(value="patientId",required=false, defaultValue="-1") Integer patientId) throws Exception
	{
		List<Object> catDetails= documentsService.getCategoryList(patientId);
		EMRResponseBean result=new EMRResponseBean();
		result.setData(catDetails);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.DOCUMENT, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting documents category list.", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		return result;
	}

	/**
	 * To get particular folders of patient based on patientId and CategoryId
	 * @param patientId
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getFolders", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getFileDetailsByPatientId(@RequestParam(value="patientId",required=true, defaultValue="-1") Integer patientId , @RequestParam(value="categoryId", required=true, defaultValue="-1") Integer categoryId) throws Exception
	{
		List<FileDetails> filecatDetails= documentsService.getFileDetailsByPatientId(patientId,categoryId);
		EMRResponseBean result= new EMRResponseBean();
		result.setData(filecatDetails);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.DOCUMENT, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting documents folder list.", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "", LogUserType.USER_LOGIN, "", "");
		return result;
	}

	/**
	 * To get the list of files of a particular patient based on categoryId and fileNameId
	 * @param patientId
	 * @param categoryId
	 * @param fileNameId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getFileList",method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getFileList(@RequestParam(value="fileDetailsId", required= true, defaultValue="-1")String fileDetailsId) throws Exception{
		List<FileDetails> fileDetails=documentsService.getFileList(fileDetailsId);
		EMRResponseBean result= new EMRResponseBean();
		result.setData(fileDetails);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.DOCUMENT, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting documents file list.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "fileDetailsId="+fileDetailsId, LogUserType.USER_LOGIN, "", "");
		return result;
	}

	/**
	 * To get Info about documents
	 * @param fileNameId
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value="/getInfo",method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getInfo(@RequestParam(value="fileNameId", required= true, defaultValue="-1") Integer fileNameId
			) throws Exception{
		List<FileName> fileName=documentsService.getInfo(fileNameId.toString());
		EMRResponseBean result= new EMRResponseBean();
		result.setData(fileName);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.DOCUMENT, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting documents file info.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "fileNameId="+fileNameId, LogUserType.USER_LOGIN, "", "");
		return result;
	}


	/**
	 * To get Document notes
	 * @param notesFilenameId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getNotesByFileId", method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getDocNotes(@RequestParam(value="notesFilenameId", required= true, defaultValue="-1")Integer notesFilenameId) throws Exception{
		List<PatientDocumentsNotes> patientDocNotes= documentsService.getDocNotesDetails(notesFilenameId);
		EMRResponseBean result= new EMRResponseBean();
		result.setData(patientDocNotes);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.DOCUMENT, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting documents file notes.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "notesFilenameId="+notesFilenameId, LogUserType.USER_LOGIN, "", "");
		return result;
	}

	/**
	 * To add comments in Notes option
	 * @param notesFilenameId
	 * @param notesPatientNotes
	 * @param userId
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value="/addNotesByFileId", method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean addDocNotes(@RequestParam(value="notesFilenameId", required= true, defaultValue="-1")Integer notesFilenameId,
			@RequestParam(value="notesPatientNotes", required=false, defaultValue="")String notesPatientNotes,
			@RequestParam(value="userId",required=true)Integer userId) throws Exception{
		List<PatientDocumentsNotes> patientDocNotes= documentsService.addDocNotesDetails(notesFilenameId,notesPatientNotes,userId);
		EMRResponseBean result= new EMRResponseBean();
		result.setData(patientDocNotes);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.DOCUMENT, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Adding documents file notes.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "notesFilenameId="+notesFilenameId, LogUserType.USER_LOGIN, "", "");
		return result;
	}

	/**
	 * To delete a folder by passing its folderId
	 * @param fileDetailsId
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteFolder",method= RequestMethod.GET)
	@ResponseBody
	public void deleteFolder(@RequestParam(value="fileDetailsId", required= true,defaultValue="-1")String fileDetailsId)throws Exception{
		documentsService.deleteFolder(fileDetailsId);	
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.DOCUMENT, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "delete documents folder file.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "fileDetailsId="+fileDetailsId, LogUserType.USER_LOGIN, "", "");
	}

	/**
	 * To delete a file by passing its fileId
	 * @param fileNameId
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteFile", method=RequestMethod.GET)
	@ResponseBody
	public void  deleteFile(@RequestParam(value="fileNameId", required=true, defaultValue="-1")Integer fileNameId,
			@RequestParam(value="patientId",required= false, defaultValue="-1") Integer patientId) throws Exception{
		documentsService.deleteFile(fileNameId,patientId);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.DOCUMENT, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "delete documents file.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "fileNameId="+fileNameId, LogUserType.USER_LOGIN, "", "");
		
	}

	/**
	 * To review group of documents
	 * @param fileDetailsId
	 * @param patientId
	 * @param categoryId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/reviewGroupOfDocs", method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean reviewGroupOfDocs(
			@RequestParam(value="fileDetailsId",required=true)String fileDetailsId,
			@RequestParam(value="patientId",required=true)Integer patientId,
			@RequestParam(value="categoryId",required=true)Integer categoryId,
			@RequestParam(value="userId",required=true)Integer userId){
		List<FileDetails> fileDetails=documentsService.reviewGroupOfDocs(fileDetailsId,categoryId,patientId,userId);
		EMRResponseBean result= new EMRResponseBean();
		result.setData(fileDetails);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.DOCUMENT, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Review documents folder.", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "fileDetailsId="+fileDetailsId, LogUserType.USER_LOGIN, "", "");
		return result;

	}

	/**
	 * To review a single file
	 * @param fileNameId
	 * @param userId
	 * @return
	 */

	@RequestMapping(value="/reviewDocuments", method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean reviewDocuments(
			@RequestParam(value="fileNameId",required=true)Integer fileNameId,
			@RequestParam(value="userId",required=true)Integer userId){
		List<FileName> fileName=documentsService.reviewDocuments(fileNameId, userId);
		EMRResponseBean result= new EMRResponseBean();
		result.setData(fileName);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.DOCUMENT, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Review documents file.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "fileNameId="+fileNameId, LogUserType.USER_LOGIN, "", "");
		return result;

	}

	/**
	 * To get details when a message is forwarded from patient documents
	 * @param alertId
	 * @param patientId
	 * @return
	 */
	@RequestMapping(value="/getDetailsByAlertId", method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean alertByCategory(
			@RequestParam(value="alertId",required=true)String alertId,
			@RequestParam(value="patientId",required= false, defaultValue="-1") Integer patientId){
		List<FileNameDetailsBean> alertPatientDocMapping=documentsService.alertByCategory(alertId,patientId);
		EMRResponseBean result= new EMRResponseBean();
		result.setData(alertPatientDocMapping);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.DOCUMENT, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting documents file by alert id.", sessionMap.getUserID(), request.getRemoteAddr(), patientId, "alertId="+alertId, LogUserType.USER_LOGIN, "", "");
		return result;
	}

	/**
	 * To forward the documents using alerts
	 * @param fromid
	 * @param toid
	 * @param status
	 * @param alertid
	 * @param docCategoryid
	 * @param refid
	 * @param patientid
	 * @param encounterid
	 * @param msg
	 * @param chartid
	 * @param roomid
	 * @param parentid
	 * @return
	 */

	@RequestMapping(value = "/forward", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean createAlerts(
			 @RequestParam(value="fromid",required=true) String fromid,
			 @RequestParam(value="toid",required=true) String toid,
			 @RequestParam(value="status",required=true, defaultValue="1") String status,
			@RequestParam(value="alertid",required=true) String alertid,
			@RequestParam(value="docCategoryid",required=true) String docCategoryid,
			@RequestParam(value="refid",required=true, defaultValue="-1") String refid,
			@RequestParam(value="patientid",required=true, defaultValue="-1") String patientid,
			@RequestParam(value="encounterid",required=true, defaultValue="-1") String encounterid,
			@RequestParam(value="msg",required=true, defaultValue="") String msg,
			@RequestParam(value="chartid",required=true, defaultValue="-1") String chartid,
			 @RequestParam(value="roomid",required=true, defaultValue="-1") String roomid,
			@RequestParam(value="parentid",required=true, defaultValue="-1") String parentid){

		List<Integer> toIdList=new ArrayList<Integer>();
		String[] toIdArray=toid.split(",");
		for (String s : toIdArray) {
			toIdList.add(Integer.parseInt(s));
		}
		logger.debug("Creating the alert with id "+fromid);	
		List<AlertEvent> alertEvent=documentsService.forwardAlert(Integer.parseInt(fromid),toIdList,Integer.parseInt(status),alertid,Integer.parseInt(docCategoryid),Integer.parseInt(refid),Integer.parseInt(patientid),Integer.parseInt(encounterid),msg,Integer.parseInt(chartid),Integer.parseInt(roomid),Integer.parseInt(parentid));
		EMRResponseBean result= new EMRResponseBean();
		result.setData(alertEvent);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.DOCUMENT, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Forward documents file by alert id.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "alertId="+alertid+"&fromid="+fromid+"&toid="+toid, LogUserType.USER_LOGIN, "", "");
		return result;
	}
	
	
	//CONSENT FORMS
	/**
	 * To get list of consent forms
	 * @return 
	 */
	@RequestMapping(value = "/getConsentForms", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getConsentForms(){
		List<FormsTemplate> formTemplate=documentsService.getConsentForms();
		EMRResponseBean result=new EMRResponseBean();
		result.setData(formTemplate);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CONSENTFORM, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting consent forms", sessionMap.getUserID(), request.getRemoteAddr(), -1,"", LogUserType.USER_LOGIN, "", "");
		return result;
		
	}
	
	/**
	 * To get list of saved consent forms
	 * @param patientId
	 * @return
	 */
	@RequestMapping(value = "/getSavedForms", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getSavedForms(
			@RequestParam(value="patientId",required=true,defaultValue="-1")String patientId){
		List<Object> getSavedForms=documentsService.getSavedForms(patientId);
		EMRResponseBean result=new EMRResponseBean();
		result.setData(getSavedForms);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.CONSENTFORM, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Saving consent forms", sessionMap.getUserID(), request.getRemoteAddr(), -1,"patientId="+patientId, LogUserType.USER_LOGIN, "", "");
		return result;
	}
	
}