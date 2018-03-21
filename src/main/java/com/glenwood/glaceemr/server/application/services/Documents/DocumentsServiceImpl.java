package com.glenwood.glaceemr.server.application.services.Documents;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.http.HttpServletRequest;


import com.glenwood.glaceemr.server.application.Bean.DocumentsCategoryBean;
import com.glenwood.glaceemr.server.application.Bean.FileNameDetailsBean;
import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.AlertEvent_;
import com.glenwood.glaceemr.server.application.models.AlertPatientDocMapping;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.FileDetails;
import com.glenwood.glaceemr.server.application.models.FileDetails_;
import com.glenwood.glaceemr.server.application.models.FileName;
import com.glenwood.glaceemr.server.application.models.FileName_;
import com.glenwood.glaceemr.server.application.models.FormsTemplate;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsCategory;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsCategory_;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsNotes;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.repositories.AlertInboxRepository;
import com.glenwood.glaceemr.server.application.repositories.AlertPatientDocMappingRepository;
import com.glenwood.glaceemr.server.application.repositories.DoctorsignRepository;
import com.glenwood.glaceemr.server.application.repositories.EmployeeProfileRepository;
import com.glenwood.glaceemr.server.application.repositories.FormsTemplateRepository;
import com.glenwood.glaceemr.server.application.repositories.InitialSettingsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientDocumentsNotesRepository;
import com.glenwood.glaceemr.server.application.repositories.FileDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.FileNameRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientDocumentsCategoryRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientRegistrationRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientSignatureRepository;
import com.glenwood.glaceemr.server.application.services.alertinbox.AlertInboxService;
import com.glenwood.glaceemr.server.application.specifications.DocumentsSpecification;
import com.glenwood.glaceemr.server.application.specifications.PatientRegistrationSpecification;
import com.glenwood.glaceemr.server.utils.SessionMap;
import com.google.common.base.Optional;
/**
 * Service Implementation for PatientDocuments
 * @author Soundarya
 *
 */

@Service
@Transactional

public  class DocumentsServiceImpl implements DocumentsService{

	@Autowired
	EntityManagerFactory emf ;

	@PersistenceContext
	EntityManager em;

	@Autowired
	FileDetailsRepository fileDetailsRepository;

	@Autowired
	FileNameRepository fileNameRepository;


	@Autowired
	PatientDocumentsCategoryRepository patientDocCategoryRepository;

	@Autowired
	PatientRegistrationRepository patientRegistrationRepository;

	@Autowired
	AlertInboxRepository alertInboxRepository;

	@Autowired
	PatientDocumentsNotesRepository docNotesRepository;

	@Autowired
	AlertPatientDocMappingRepository alertPatientDocMappingRepository;

	@Autowired
	FormsTemplateRepository formsTemplateRepository;

	@Autowired
	EmployeeProfileRepository employeeProfileRepository;

	@Autowired
	DoctorsignRepository doctorsignRepository;

	@Autowired
	PatientSignatureRepository patientSignatureRepository ;

	@Autowired
	InitialSettingsRepository initialSettingsRepository;

	@Autowired
	SessionMap sessionMap;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	AlertInboxService alertInboxService;

	JSONObject result=null;

	/**
	 * TO get category list based on patientId
	 * If the patientId is not provided then it displays all category list in Attach option
	 * @param patientId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Object> getCategoryList(int patientId) {

		if(patientId!= -1 ){
			List<Object> getCategoryDetails= new ArrayList<Object>();
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = builder.createQuery();
			Root<FileDetails> root = cq.from(FileDetails.class);
			Join<FileDetails, PatientDocumentsCategory> catid=root.join(FileDetails_.patientDocCategory,JoinType.INNER);
			cq.multiselect(builder.construct(DocumentsCategoryBean.class,
					catid.get(PatientDocumentsCategory_.patientDocCategoryId),
					catid.get(PatientDocumentsCategory_.patientDocCategoryName),
					builder.count(root.get(FileDetails_.filedetailsId))));
			Predicate activeDocCategory= builder.equal(catid.get(PatientDocumentsCategory_.patientDocCategoryIsactive),true);
			Predicate patpredicate= builder.equal(root.get(FileDetails_.filedetailsPatientid),patientId);
			cq.where(builder.and(activeDocCategory,patpredicate)).getRestriction();
			cq.groupBy(catid.get(PatientDocumentsCategory_.patientDocCategoryId),catid.get(PatientDocumentsCategory_.patientDocCategoryName));
			cq.orderBy(builder.asc(catid.get(PatientDocumentsCategory_.patientDocCategoryName)));
			getCategoryDetails=em.createQuery(cq).getResultList();
			return getCategoryDetails;

		}
		else{
			List<PatientDocumentsCategory> patientDocumentsCategories=new ArrayList<PatientDocumentsCategory>();
			patientDocumentsCategories=patientDocCategoryRepository.findAll(DocumentsSpecification.getAllCategories());
			List<Object> getAllCategories= new ArrayList<Object>();
			for (PatientDocumentsCategory patientDocumentsCategory : patientDocumentsCategories) {
				getAllCategories.add(patientDocumentsCategory);
			}
			return getAllCategories;
		}
	}



	/**
	 * To get particular folders of patient based on patientId and CategoryId
	 * @param patientId
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<FileDetails> getFileDetailsByPatientId(int patientId, int categoryId) {
		List<FileDetails> fileDetails = null;
		fileDetails = fileDetailsRepository.findAll(DocumentsSpecification.getFileCategoryList(patientId,categoryId));
		return fileDetails;
	}

	/**
	 * To get the list of files 
	 * @param scanid
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<FileDetails> getFileList(String scanid) {
		List<FileDetails> fileDetails=null;
		fileDetails=fileDetailsRepository.findAll(DocumentsSpecification.getFileList(scanid));
		/*FileDetails fileDetail=null;
		if(fileDetails.size()>0){
			fileDetail=fileDetails.get(0);
		}*/

		return fileDetails;
	}

	/**
	 * To get Info about documents
	 * @param fileIds
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<FileName> getInfo(String fileIds) {
		List<FileName> fileName=null;
		fileName = fileNameRepository.findAll(DocumentsSpecification.getInfo(fileIds));
		return fileName;
	}



	/**
	 * To get Document notes
	 * @param notesFilenameId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PatientDocumentsNotes> getDocNotesDetails(int notesFilenameId) {
		List<PatientDocumentsNotes> PatientDocumentsNotes=null;
		PatientDocumentsNotes=docNotesRepository.findAll(DocumentsSpecification.getDocNotes(notesFilenameId));
		return PatientDocumentsNotes;
	}

	/**
	 * To add comments in Notes option
	 * @param notesFilenameId
	 * @param notesPatientNotes
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PatientDocumentsNotes> addDocNotesDetails(int notesFilenameId, String notesPatientNotes,int userId) {
		List<PatientDocumentsNotes> PatientDocumentsNotes=null;

		Date date= new Date();
		PatientDocumentsNotes pd=new PatientDocumentsNotes();
		pd.setNotesFilenameid(notesFilenameId);
		pd.setNotesPatientnotes(notesPatientNotes);
		pd.setNotesCreatedby(userId);
		pd.setNotesCreatedon(new Timestamp(date.getTime()));
		pd.setNotesModifiedby(userId);
		pd.setNotesModifiedon(new Timestamp(date.getTime()));
		docNotesRepository.saveAndFlush(pd);
		PatientDocumentsNotes=docNotesRepository.findAll(DocumentsSpecification.getDocNotes(notesFilenameId));

		return PatientDocumentsNotes;
	}

	/**
	 * To delete a folder by passing its folderId
	 * @param fileDetailsId
	 * @throws Exception
	 */
	@Override
	public void deleteFolder(String fileDetailsId) {

		List<FileName> fileNameList= fileNameRepository.findAll(DocumentsSpecification.getFileNameDetails(fileDetailsId));
		fileNameRepository.deleteInBatch(fileNameList);

		List<FileDetails> fileDetails = fileDetailsRepository.findAll(DocumentsSpecification.deleteFolder( fileDetailsId));
		fileDetailsRepository.deleteInBatch(fileDetails);

	}

	/**
	 * To delete a file by passing its fileId
	 * @param fileNameId
	 * @throws Exception
	 */
	@Override
	public void deleteFile(int fileNameId,int patientId) {
		List<FileName> fileNameList = fileNameRepository.findAll(DocumentsSpecification.deleteFiles(fileNameId));
		fileNameRepository.deleteInBatch(fileNameList);

		List<Integer> getDetails= new ArrayList<Integer>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<FileName> root = cq.from(FileName.class);
		cq.distinct(true);
		cq.select(builder.construct(Integer.class,root.get(FileName_.filenameScanid)));

		List<Object> getDetailsResultSet=em.createQuery(cq).getResultList();
		for(Object result:getDetailsResultSet){
			getDetails.add((Integer) result);
		}
		if(getDetails.size()>0){
			List<FileDetails> fileName = fileDetailsRepository.findAll(DocumentsSpecification.deleteAFile(fileNameId,getDetails,patientId));
			fileDetailsRepository.deleteInBatch(fileName);
		}
	}

	/**
	 * To review group of documents
	 * @param fileDetailsId
	 * @param patientId
	 * @param categoryId
	 * @param userId
	 * @return
	 */
	@Override
	public List<FileDetails> reviewGroupOfDocs(String fileDetailsId,
			int categoryId, int patientId, int userId) {
		List<Integer> fileNameId = new ArrayList<Integer>();
		List<FileDetails> fileDetails=fileDetailsRepository.findAll(DocumentsSpecification.byFileDetailsId(fileDetailsId,categoryId,patientId,userId));
		Date date= new Date();
		for(int i=0;i<fileDetails.size();i++){
			int fd=fileDetails.get(i).getFileName().size();
			for(int j=0;j<fd;j++){
				FileName fileName= fileDetails.get(i).getFileName().get(j);
				fileName.setFilenameIsreviewed(true);
				fileName.setFilenameReviewedby(userId);
				fileName.setFilenameReviewedon(new Timestamp(date.getTime()));
				fileNameRepository.saveAndFlush(fileName);
				fileNameId.add(fileName.getFilenameId());
			}
		}
		
		Set<Integer> stringsSet = new LinkedHashSet<Integer>();//A Linked hash set 
		//prevents the adding order of the elements
		for (Integer value: fileNameId) {
		    stringsSet.add(value);
		}
		List<Integer> dupList = new ArrayList<Integer>(stringsSet);
		
		checkReviewed(dupList,userId);
		return fileDetails;
	}

	public void checkReviewed(List<Integer> filenameId,int userId) {
		for(int i=0;i<filenameId.size();i++)
		{
			List<AlertPatientDocMapping> alertPatientDocMapping= alertPatientDocMappingRepository.findAll(DocumentsSpecification.getalertsByFileId(filenameId.get(i)));
			if(alertPatientDocMapping.size()>0)
			{
				for(int j=0;j<alertPatientDocMapping.size();j++)
				{
					List<Boolean> isReviewDetails = new ArrayList<Boolean>();
					AlertPatientDocMapping alertpatDocMap = alertPatientDocMapping.get(j);
					int alertId = alertpatDocMap.getAlertId();
					String fileDetailsIds = alertpatDocMap.getForwardedFiledetailsId();
					if(fileDetailsIds.contains(","))
					{
						String fileNameId[] = fileDetailsIds.split(",");
						for(int k=0;k<fileNameId.length;k++)
						{
							String fileId = fileNameId[k];
							if(!filenameId.contains(fileId))
							{
								List<FileName> fileNameList=fileNameRepository.findAll(DocumentsSpecification.byfileNameId(Integer.parseInt(fileId.trim())));
								isReviewDetails.add(fileNameList.get(0).getFilenameIsreviewed());
							}
						}
					}
					else
					{
						List<FileName> fileNameList=fileNameRepository.findAll(DocumentsSpecification.byfileNameId(Integer.parseInt(fileDetailsIds.trim())));
						isReviewDetails.add(fileNameList.get(0).getFilenameIsreviewed());
					}
					
					if(!isReviewDetails.contains(false))
					{
						deleteAlertInPatDocMap(alertId);
						List<Integer> alertIdList = new ArrayList<Integer>();
						alertIdList.add(alertId);
						alertInboxService.deleteAlert(alertIdList, userId);
					}
				}
			}
		}
	}



	public void deleteAlertInPatDocMap(int alertId) {
		List<AlertPatientDocMapping> alertPatientDocMapping= alertPatientDocMappingRepository.findAll(DocumentsSpecification.getalertByCategory(Integer.toString(alertId)));
		alertPatientDocMappingRepository.deleteInBatch(alertPatientDocMapping);
	}



	/**
	 * To review a single file
	 * @param fileNameId
	 * @param userId
	 * @return
	 */
	@Override
	public List<FileName> reviewDocuments(int fileNameId, int userId) {
		List<FileName> fileNameList=fileNameRepository.findAll(DocumentsSpecification.byfileNameId(fileNameId));
		Date date=new Date();
		for(int i=0;i<fileNameList.size();i++){
			FileName fileName=fileNameList.get(i);
			fileName.setFilenameIsreviewed(true);
			fileName.setFilenameReviewedby(userId);
			fileName.setFilenameReviewedon(new Timestamp(date.getTime()));
			fileNameRepository.saveAndFlush(fileName);
		}
		List<Integer> fileList = new ArrayList<Integer>();
		fileList.add(fileNameList.get(0).getFilenameId());
		checkReviewed(fileList,userId);
		return fileNameList;
	}

	/**
	 * To get details when a message is forwarded from patient documents
	 * @param alertId
	 * @param patientId
	 * @return
	 */
	@Override
	public List<FileNameDetailsBean> alertByCategory(String alertId,int patientId) {
		List<AlertPatientDocMapping> alertPatientDocMapping= alertPatientDocMappingRepository.findAll(DocumentsSpecification.getalertByCategory(alertId));
		List<FileNameDetailsBean> fileName=null;
		if(alertPatientDocMapping.size()>0){
			fileName=getFileInfo(alertPatientDocMapping.get(0).getForwardedFiledetailsId());
		}
		return fileName;
	}

	public List<FileNameDetailsBean> getFileInfo(String forwardedFiledetailsId) {
		String list[]=forwardedFiledetailsId.split(",");
		List<Integer> fileDetailsIdl=new ArrayList<Integer>();
		for(int i=0;i<list.length;i++){
			fileDetailsIdl.add(Integer.parseInt(list[i].trim()));	
		}
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery(Object.class);
		Root<FileName> root = cq.from(FileName.class);
		Join<FileName, FileDetails> fdjoin = root.join(FileName_.fileNameDetails,JoinType.LEFT);
		Predicate fileId=root.get(FileName_.filenameId).in(fileDetailsIdl);
		
		cq.select(builder.construct(FileNameDetailsBean.class, 
				root.get(FileName_.filenameId),
				root.get(FileName_.filenameScanid),
				root.get(FileName_.filenameName),
				fdjoin.get(FileDetails_.filedetailsId),
				fdjoin.get(FileDetails_.filedetailsFlag),
				fdjoin.get(FileDetails_.filedetailsDescription)));
		
		cq.where(fileId);
		List<Object> fileName = em.createQuery(cq).getResultList();
		List<FileNameDetailsBean> fileNameBean = new ArrayList<FileNameDetailsBean>();
		for(int i=0;i<fileName.size();i++)
		{
			FileNameDetailsBean fileNamedetails = (FileNameDetailsBean) fileName.get(i);
			fileNameBean.add(fileNamedetails);
		}
		return fileNameBean;
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
	@Override
	public List<AlertEvent> forwardAlert(int fromId, List<Integer> toIdList,
			int status, String alertid,int docCategoryid,int refId, int patientId,
			int encounterId, String msg, int chartId, int roomId, int parentId) {

		String lastName="", firstName="", midInitial="";
		int  refid=refId;
		if(alertid.equals("37")){
			CriteriaBuilder qb = em.getCriteriaBuilder();
			CriteriaQuery<Object> cq = qb.createQuery();

			Root<AlertEvent> root = cq.from(AlertEvent.class);
			cq.select(qb.max(root.get(AlertEvent_.alertEventRefId)));
			cq.where(qb.equal(root.get(AlertEvent_.alertEventCategoryId),37));

			Query query=em.createQuery(cq);
			Object maxIdObj=query.getSingleResult();

			if(maxIdObj!=null)
				refid = (int) maxIdObj;

			if(refid < 1)
				refid = 1;
		}
		PatientDocumentsCategory category=patientDocCategoryRepository.findOne(DocumentsSpecification.CatId1(docCategoryid));
		PatientRegistration patient=patientRegistrationRepository.findOne(PatientRegistrationSpecification.PatientId(patientId+""));
		if(patient!=null){

			lastName=Optional.fromNullable(patient.getPatientRegistrationLastName()).or(" ");
			firstName=Optional.fromNullable(patient.getPatientRegistrationFirstName()).or(" ");
			midInitial=Optional.fromNullable(patient.getPatientRegistrationMidInitial()).or(" ");
		}
		List<AlertEvent> alertEventList=null;
		List<Integer> alertId=new ArrayList<>();
		for(int i=0;i<toIdList.size();i++)
		{
			String categoryName=category.getPatientDocCategoryName();
			PatientDocumentsCategory docCat=new PatientDocumentsCategory();
			AlertEvent aug=new AlertEvent();
			aug.setAlertEventFrom(fromId);
			aug.setAlertEventTo(toIdList.get(i));
			aug.setAlertEventStatus(status);
			aug.setAlertEventCategoryId(Integer.parseInt(alertid));
			aug.setAlertEventRefId(refid);
			aug.setAlertEventPatientId(patientId);
			if(patient!=null)
				aug.setAlertEventPatientName(lastName+", "+firstName+" "+midInitial);		//Lastname, Firstname MidInitial
			aug.setAlertEventEncounterId(encounterId);
			aug.setAlertEventCreatedDate(new Timestamp(new Date().getTime()));
			docCat.setPatientDocCategoryId(docCategoryid);
			msg+="--"+categoryName+" category.";
			aug.setAlertEventMessage(msg);
			aug.setAlertEventModifiedby(fromId);
			aug.setAlertEventChartId(chartId);
			aug.setAlertEventRoomId(roomId);
			aug.setAlertEventParentalertid(parentId);
			alertInboxRepository.save(aug);
			alertId.add(aug.getAlertEventId());

		}
		alertEventList=alertInboxRepository.findAll(DocumentsSpecification.byAlertId(alertId));
		return alertEventList;
	}


	/**
	 * To get list of consent forms
	 */
	@Override
	public List<FormsTemplate> getConsentForms() {
		List<FormsTemplate> formsTemplate=formsTemplateRepository.findAll(DocumentsSpecification.getConsentFormDetails());
		return formsTemplate;
	}

	/**
	 * To get previously saved consent forms
	 */
	@Override
	public List<Object> getSavedForms(String patientId) {
		List<Object> getForms= new ArrayList<Object>();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = builder.createQuery();
		Root<FileDetails> root = cq.from(FileDetails.class);
		Join<FileDetails, FileName> filename=root.join(FileDetails_.fileName,JoinType.INNER);
		Join<FileDetails, PatientDocumentsCategory> patDocCategory=root.join(FileDetails_.patientDocCategory,JoinType.INNER);
		Join<FileName, EmployeeProfile> empprofile=filename.join(FileName_.createdByEmpProfileTable);
		cq.multiselect(builder.construct(ConsentFormsBean.class, 
				patDocCategory.get(PatientDocumentsCategory_.patientDocCategoryName),
				root.get(FileDetails_.filedetailsId),
				root.get(FileDetails_.filedetailsDescription),
				root.get(FileDetails_.filedetailsComments),
				builder.function("to_char", String.class, root.get(FileDetails_.filedetailsCreationdate),builder.literal("MM/dd/yyyy HH:MI:ss am")),
				builder.coalesce(filename.get(FileName_.filenameIsreviewed), false),
				builder.count(root.get(FileDetails_.filedetailsId)),
				filename.get(FileName_.filenameName),
				empprofile.get(EmployeeProfile_.empProfileFullname)
				));
		Predicate patId=builder.equal(root.get(FileDetails_.filedetailsPatientid),patientId);
		Predicate patIsActive=builder.equal(patDocCategory.get(PatientDocumentsCategory_.patientDocCategoryIsactive), true);
		Predicate fileNameIsActive=builder.equal(filename.get(FileName_.filenameIsactive), true);
		Predicate catId=builder.equal(root.get(FileDetails_.filedetailsCategoryid), 3);
		cq.where(builder.and(patId,patIsActive,fileNameIsActive,catId));
		cq.groupBy(patDocCategory.get(PatientDocumentsCategory_.patientDocCategoryName),
				root.get(FileDetails_.filedetailsId),
				root.get(FileDetails_.filedetailsDescription),
				root.get(FileDetails_.filedetailsComments),
				root.get(FileDetails_.filedetailsCreationdate),
				filename.get(FileName_.filenameIsreviewed),
				patDocCategory.get(PatientDocumentsCategory_.patientDocCategoryOrder),
				patDocCategory.get(PatientDocumentsCategory_.patientDocCategoryId),
				root.get(FileDetails_.filedetailsBuffer3),
				filename.get(FileName_.filenameName),
				empprofile.get(EmployeeProfile_.empProfileFullname));
		cq.orderBy(builder.asc(patDocCategory.get(PatientDocumentsCategory_.patientDocCategoryName)),
				builder.asc(patDocCategory.get(PatientDocumentsCategory_.patientDocCategoryOrder)),
				builder.asc(patDocCategory.get(PatientDocumentsCategory_.patientDocCategoryId)),
				builder.desc(root.get(FileDetails_.filedetailsBuffer3)),
				builder.desc(root.get(FileDetails_.filedetailsCreationdate)),
				builder.asc(root.get(FileDetails_.filedetailsId)));
		getForms=em.createQuery(cq).getResultList();
		return getForms;
	}
}



