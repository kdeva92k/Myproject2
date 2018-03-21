package com.glenwood.glaceemr.server.application.specifications;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.AlertEvent_;
import com.glenwood.glaceemr.server.application.models.AlertPatientDocMapping;
import com.glenwood.glaceemr.server.application.models.AlertPatientDocMapping_;
import com.glenwood.glaceemr.server.application.models.DoctorSign;
import com.glenwood.glaceemr.server.application.models.DoctorSign_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.FileDetails;
import com.glenwood.glaceemr.server.application.models.FileDetails_;
import com.glenwood.glaceemr.server.application.models.FileName;
import com.glenwood.glaceemr.server.application.models.FileName_;
import com.glenwood.glaceemr.server.application.models.FormsTemplate;
import com.glenwood.glaceemr.server.application.models.FormsTemplate_;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsCategory;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsCategory_;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsNotes;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsNotes_;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PatientRegistration_;
import com.glenwood.glaceemr.server.application.models.PatientSignature;
import com.glenwood.glaceemr.server.application.models.PatientSignature_;
/**
 * Specification for Patient Documents
 * @author Soundarya
 *
 */
public class DocumentsSpecification {

	/**
	 * Specification to get all category List 
	 * @return
	 */
	public static Specification<PatientDocumentsCategory> getAllCategories(){
		return new Specification<PatientDocumentsCategory>() {

			@Override
			public Predicate toPredicate(Root<PatientDocumentsCategory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.orderBy(cb.asc(root.get(PatientDocumentsCategory_.patientDocCategoryName)),cb.asc(root.get(PatientDocumentsCategory_.patientDocCategoryOrder)));
				Predicate predicate=cb.equal(root.get(PatientDocumentsCategory_.patientDocCategoryIsactive),true);
				return predicate;
			}
		};

	}	

	/**
	 * Specification to get the folder details based on patientId and categoryId
	 * @param patientId
	 * @param categoryId
	 */
	static ArrayList<Integer> patientIdList=new ArrayList<Integer>();

	public static Specification<FileDetails> getFileCategoryList(final int patientId,final int categoryId){
		return new Specification<FileDetails>() {

			@Override
			public Predicate toPredicate(Root<FileDetails> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				root.fetch(FileDetails_.fileName,JoinType.INNER);
				root.fetch(FileDetails_.patientDocCategory,JoinType.INNER);
				Predicate catpredicate=cb.equal(root.get(FileDetails_.filedetailsCategoryid),categoryId);
				Predicate patpredicate=cb.equal(root.get(FileDetails_.filedetailsPatientid),patientId);
				return query.where(cb.and(catpredicate,patpredicate)).orderBy(cb.desc(root.get(FileDetails_.filedetailsCreationdate))).getRestriction();
			}
		};

	}	

	/**
	 * To get the list of files 
	 * @param scanid
	 * @return
	 */
	public static Specification<FileDetails> getFileList(final String fileDetailsId){
		return new Specification<FileDetails>() {
			
			public Predicate toPredicate(Root<FileDetails> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				root.fetch(FileDetails_.fileName,JoinType.INNER);
				String list[]=fileDetailsId.split(",");
				List<Integer> fileDetailsIdl=new ArrayList<Integer>();
				for(int i=0;i<list.length;i++){
					fileDetailsIdl.add(Integer.parseInt(list[i]));	
				}
				Predicate predicate= root.get(FileDetails_.filedetailsId).in(fileDetailsIdl);
				return predicate;
			}
		};
	}

	/**
	 * To get Info about documents
	 * @param fileIds
	 * @return
	 */
	public static Specification<FileName> getInfo(final String fileIds){
		return new Specification<FileName>() {

			@Override
			public Predicate toPredicate(Root<FileName> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				root.fetch(FileName_.createdByEmpProfileTable,JoinType.LEFT);
				String list[]=fileIds.split(",");
				List<Integer> fileDetailsIdl=new ArrayList<Integer>();
				for(int i=0;i<list.length;i++){
					fileDetailsIdl.add(Integer.parseInt(list[i].trim()));	
				}
				
				Predicate fileId=root.get(FileName_.filenameId).in(fileDetailsIdl);
				return query.where(fileId).getRestriction();
			}	
		};
	}
	/**
	 * To get Document notes
	 * @param notesFilenameId
	 * @return
	 */
	public static Specification<PatientDocumentsNotes> getDocNotes(final int notesFilenameId){
		return new Specification<PatientDocumentsNotes>() {

			@Override
			public Predicate toPredicate(Root<PatientDocumentsNotes> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<PatientDocumentsNotes,EmployeeProfile> emp=root.join(PatientDocumentsNotes_.createdByEmpProfileTable,JoinType.LEFT);
				root.fetch(PatientDocumentsNotes_.createdByEmpProfileTable,JoinType.LEFT);
				Predicate predicate=cb.equal(root.get(PatientDocumentsNotes_.notesFilenameid), notesFilenameId);
				return predicate;
			}
		};
	}


	/**
	 * Specification to get the details of a folder and its corresponding entries in filename table
	 * @param fileDetailsId
	 * @return
	 */
	public static Specification<FileName> getFileNameDetails(final String fileDetailsId){
		return new Specification<FileName>() {

			@Override
			public Predicate toPredicate(Root<FileName> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String list[]=fileDetailsId.split(",");
				List<Integer> fileDetailsIdl=new ArrayList<Integer>();
				for(int i=0;i<list.length;i++){
					fileDetailsIdl.add(Integer.parseInt(list[i]));	
				}
				Predicate getFileData = root.get(FileName_.filenameScanid).in(fileDetailsIdl);
				return getFileData;
			}
		};
	}


	/**
	 * Specification to delete a folder 
	 * @param fileDetailsId
	 * @return
	 */
	public static Specification<FileDetails> deleteFolder(final String fileDetailsId){
		return new Specification<FileDetails>() {

			@Override
			public Predicate toPredicate(Root<FileDetails> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				String list[]=fileDetailsId.split(",");
				List<Integer> fileDetailsIdl=new ArrayList<Integer>();
				for(int i=0;i<list.length;i++){
					fileDetailsIdl.add(Integer.parseInt(list[i]));	
				}
				Predicate delFolders = root.get(FileDetails_.filedetailsId).in(fileDetailsIdl);

				return delFolders;
			}

		};
	}


	/**
	 * Specification to delete a file
	 * @param fileNameId
	 * @return
	 */
	public static Specification<FileName> deleteFiles(final int fileNameId){
		return new Specification<FileName>() {

			@Override
			public Predicate toPredicate(Root<FileName> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Expression<Integer> expr=root.get(FileName_.filenameScanid);
				Predicate delFiles=root.get(FileName_.filenameId).in(fileNameId);
				return delFiles;
			}

		};
	}

	/**
	 * Specification to delete entries in filedetails table which are related to the file deleted
	 * @param fileNameId
	 * @param getDetails
	 * @param patientId
	 * @return
	 */
	public static Specification<FileDetails> deleteAFile(final int fileNameId,final List<Integer> getDetails,final int patientId){
		return new Specification<FileDetails>() {

			@Override
			public Predicate toPredicate(Root<FileDetails> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate filedetails = cb.not((root.get(FileDetails_.filedetailsId).in(getDetails)));
				Predicate patId=root.get(FileDetails_.filedetailsPatientid).in(patientId);
				return query.where(filedetails,patId).getRestriction();
			}
		};
	}

	/**
	 * To review group of documents by FileDetailsId
	 * @param fileDetailsId
	 * @param categoryId
	 * @param patientId
	 * @param userId
	 * @return
	 */
	public static Specification<FileDetails> byFileDetailsId(final String fileDetailsId,final int categoryId,final int patientId,final int userId){
		return new Specification<FileDetails>() {

			@Override
			public Predicate toPredicate(Root<FileDetails> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Join<FileDetails, FileName> file=root.join(FileDetails_.fileName,JoinType.INNER);
				root.fetch(FileDetails_.fileName,JoinType.INNER);
				String list[]=fileDetailsId.split(",");
				List<Integer> fileDetailsIdl=new ArrayList<Integer>();
				for(int i=0;i<list.length;i++){
					fileDetailsIdl.add(Integer.parseInt(list[i]));	
				}
				
				Predicate fileIdPredicate=root.get(FileDetails_.filedetailsId).in(fileDetailsIdl);
				Predicate catpredicate=cb.equal(root.get(FileDetails_.filedetailsCategoryid),categoryId);
				Predicate patpredicate=cb.equal(root.get(FileDetails_.filedetailsPatientid),patientId);
				return query.where(cb.and(fileIdPredicate,catpredicate,patpredicate)).getRestriction();
				
			}


		};
	}

	/**
	 * To review a single file by fileNameId
	 * @param fileNameId
	 * @return
	 */
	public static Specification<FileName> byfileNameId(final int fileNameId){
		return new Specification<FileName>() {

			@Override
			public Predicate toPredicate(Root<FileName> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.equal(root.get(FileName_.filenameId),fileNameId);
				return predicate;
			}

		};

	}

	/**
	 * To get details when a message is forwarded from patient documents
	 * @param alertId
	 * @return
	 */
	public static Specification<AlertPatientDocMapping> getalertByCategory(final String alertId){
		return new Specification<AlertPatientDocMapping>() {

			@Override
			public Predicate toPredicate(Root<AlertPatientDocMapping> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.equal(root.get(AlertPatientDocMapping_.alertId), alertId));
				return query.getRestriction();
			}
		};
	}

	/**
	 * Specification to forward the documents using alerts
	 * @param alertIdList
	 * @return
	 */
	public static Specification<AlertEvent> byAlertId(final List<Integer> alertIdList) {
		return new Specification<AlertEvent>() {

			@Override
			public Predicate toPredicate(Root<AlertEvent> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				Expression<Integer> expr=root.get(AlertEvent_.alertEventId);
				Predicate predicate=query.where(expr.in(alertIdList)).getRestriction();
				return predicate;
			}
		};
	}

	/**
	 * Specification to forward the documents using alerts
	 * @param docCategoryid
	 * @return
	 */
	public static Specification<PatientDocumentsCategory> CatId1(final int docCategoryid){
		return new Specification<PatientDocumentsCategory>() {

			@Override
			public Predicate toPredicate(Root<PatientDocumentsCategory> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				query.where(cb.equal(root.get(PatientDocumentsCategory_.patientDocCategoryId), docCategoryid));
				return query.getRestriction();
			}
		};
	}

	public static Specification<FormsTemplate> getConsentFormDetails() {
		return new Specification<FormsTemplate>() {

			@Override
			public Predicate toPredicate(Root<FormsTemplate> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate type=cb.equal(root.get(FormsTemplate_.formsTemplateType), 2);
				Predicate isActive=cb.equal(root.get(FormsTemplate_.formsTemplateIsactive), true);
				query.where(cb.and(type,isActive));
				return query.getRestriction();
			}
			
		};
	}

	public static Specification<FormsTemplate> templateDetails(final String templateId) {
	return new Specification<FormsTemplate>()	{

		@Override
		public Predicate toPredicate(Root<FormsTemplate> root,
				CriteriaQuery<?> query, CriteriaBuilder cb) {
			Predicate predicate=cb.equal(root.get(FormsTemplate_.formsTemplateId), templateId);
			query.where(predicate);
			return query.getRestriction();
		}
		
	};
		
	}

	public static Specification<PatientRegistration> getprincipalDrId(
			final String patientId) {
		return new Specification<PatientRegistration>() {

			@Override
			public Predicate toPredicate(Root<PatientRegistration> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.equal(root.get(PatientRegistration_.patientRegistrationId), patientId);
				query.where(predicate);
				return query.getRestriction();
			}
		};
	}

	public static Specification<EmployeeProfile> getloginUserId(
			final int principalDrId) {
		return new Specification<EmployeeProfile>() {

			@Override
			public Predicate toPredicate(Root<EmployeeProfile> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.equal(root.get(EmployeeProfile_.empProfileEmpid),principalDrId);
				query.where(predicate);
				return query.getRestriction();
			}
			
		};
	}

	public static Specification<DoctorSign> getphysicianSignImg(final int loginUserId) {
		return new Specification<DoctorSign>() {

			@Override
			public Predicate toPredicate(Root<DoctorSign> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.equal(root.get(DoctorSign_.loginid), loginUserId);
				query.where(predicate);
				return query.getRestriction();
			}
			
		};
	}

	public static Specification<PatientSignature> getpatientSignId(
			final String physicianSignImg) {
		return new Specification<PatientSignature>() {

			@Override
			public Predicate toPredicate(Root<PatientSignature> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.equal(root.get(PatientSignature_.signaturefilename), physicianSignImg);
				query.where(predicate);
				return query.getRestriction();
			}
			
		};
	}
	
	/**
	 * To review a single file by fileNameId
	 * @param fileNameId
	 * @return
	 */
	public static Specification<FileName> byfileNameIds(final List<String> fileNameId){
		return new Specification<FileName>() {

			@Override
			public Predicate toPredicate(Root<FileName> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=root.get(FileName_.filenameId).in(fileNameId);
				Join<FileName, FileDetails> fileDetailsJoin = root.join(FileName_.fileNameDetails,JoinType.INNER);
				Join<FileDetails, PatientDocumentsCategory> patDocCatJoin = fileDetailsJoin.join(FileDetails_.patientDocCategory,JoinType.INNER);
				
				root.fetch(FileName_.fileNameDetails,JoinType.INNER);
				fileDetailsJoin.fetch(FileDetails_.patientDocCategory,JoinType.INNER);
				return predicate;
			}

		};

	}

	public static Specification<AlertPatientDocMapping> getalertsByFileId(final Integer filenameId) {
		return new Specification<AlertPatientDocMapping>() {

			@Override
			public Predicate toPredicate(Root<AlertPatientDocMapping> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p1 = root.get(AlertPatientDocMapping_.forwardedFiledetailsId).in(filenameId.toString());
				Predicate p2 = cb.like(root.get(AlertPatientDocMapping_.forwardedFiledetailsId), filenameId+",%");
				Predicate p3 = cb.like(root.get(AlertPatientDocMapping_.forwardedFiledetailsId), "%,"+filenameId+",%");
				Predicate p4 = cb.like(root.get(AlertPatientDocMapping_.forwardedFiledetailsId), "%,"+filenameId);
				/*cb.or(root.get(AlertPatientDocMapping_.forwardedFiledetailsId).in(filenameId),
						cb.or(cb.like(root.get(AlertPatientDocMapping_.forwardedFiledetailsId), filenameId+",%"), 
						cb.like(root.get(AlertPatientDocMapping_.forwardedFiledetailsId), "%,"+filenameId+",%")));
				*/
				query.where(cb.or(p1,p2,p3,p4));
				return query.getRestriction();
			}

		};
	}

}


