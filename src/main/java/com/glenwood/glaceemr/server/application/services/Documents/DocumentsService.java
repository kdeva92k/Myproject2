package com.glenwood.glaceemr.server.application.services.Documents;

import java.util.List;

import com.glenwood.glaceemr.server.application.Bean.FileNameDetailsBean;
import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.FileDetails;
import com.glenwood.glaceemr.server.application.models.FileName;
import com.glenwood.glaceemr.server.application.models.FormsTemplate;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsNotes;

public interface DocumentsService {

	List<Object> getCategoryList(int patientId);
	List<FileDetails> getFileDetailsByPatientId(int patientId,int categoryId );
	List<FileDetails> getFileList(String fileIdList);
	List<PatientDocumentsNotes> getDocNotesDetails(int notesFilenameId);
	List<PatientDocumentsNotes> addDocNotesDetails(int notesFilenameId, String notesPatientNotes,int userId);
	List<FileName> getInfo(String fileNameId);
	void deleteFile(int fileNameId,int patientId);
	void deleteFolder(String fileDetailsId);
	List<AlertEvent> forwardAlert(int fromId, List<Integer> toIdList,int status, String alertid,int docCategoryid, int refId, int patientId,int encounterId, String msg, int chartId, int roomId,int parentId);
	List<FileDetails> reviewGroupOfDocs(String fileDetailsId,int categoryId,int patientId,int userId);
	List<FileName> reviewDocuments(int fileNameId,int userId);
	List<FileNameDetailsBean> alertByCategory(String alertId,int patientId);
	List<FormsTemplate> getConsentForms();
	List<Object> getSavedForms(String patientId);
	}