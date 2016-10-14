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

import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.AlertEvent;
import com.glenwood.glaceemr.server.application.models.AlertEvent_;
import com.glenwood.glaceemr.server.application.models.AlertPatientDocMapping;
import com.glenwood.glaceemr.server.application.models.AlertPatientDocMapping_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.FileDetails;
import com.glenwood.glaceemr.server.application.models.FileDetails_;
import com.glenwood.glaceemr.server.application.models.FileName;
import com.glenwood.glaceemr.server.application.models.FileName_;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsCategory;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsCategory_;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsNotes;
import com.glenwood.glaceemr.server.application.models.PatientDocumentsNotes_;
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
				return query.where(cb.and(catpredicate,patpredicate)).getRestriction();
			}
		};

	}	

	/**
	 * To get the list of files 
	 * @param fileDetailsId
	 * @return
	 */
	public static Specification<FileDetails> getFileList(final String fileDetailsId){
		return new Specification<FileDetails>() {
			
			public Predicate toPredicate(Root<FileDetails> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				//Join<FileDetails,FileName> file=root.join(FileDetails_.fileName,JoinType.INNER);
				root.fetch(FileDetails_.fileName,JoinType.INNER);
				String list[]=fileDetailsId.split(",");
				List<Integer> fileDetailsIdl=new ArrayList<Integer>();
				for(int i=0;i<list.length;i++){
					fileDetailsIdl.add(Integer.parseInt(list[i]));	
				}
				//Join<FileName,FileDetails> file=root.join(FileName_.fileNameDetails,JoinType.INNER);
				//root.fetch(FileName_.fileNameDetails,JoinType.INNER);
				//Predicate predicate=cb.equal(file.get(FileName_.filenameScanid),fileDetailsId);
				Predicate predicate= root.get(FileDetails_.filedetailsId).in(fileDetailsIdl);
				return predicate;
			}
		};
	}

	/**
	 * To get Info about documents
	 * @param fileNameId
	 * @return
	 */
	public static Specification<FileName> getInfo(final int fileNameId){
		return new Specification<FileName>() {

			@Override
			public Predicate toPredicate(Root<FileName> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				root.fetch(FileName_.createdByEmpProfileTable,JoinType.LEFT);
				Predicate fileId=root.get(FileName_.filenameId).in(fileNameId);
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

	/*public static Specification<FileDetails> getFileList(final int patientId,final int categoryId,final int fileNameId){
		return new Specification<FileDetails>() {

			@Override
			public Predicate toPredicate(Root<FileDetails> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				Join<FileDetails, FileName> fileid=root.join(FileDetails_.fileName,JoinType.INNER);
				root.fetch(FileDetails_.patientDocCategory,JoinType.INNER);
				Predicate catpredicate=cb.equal(root.get(FileDetails_.filedetailsCategoryid),categoryId);
				Predicate patpredicate=cb.equal(root.get(FileDetails_.filedetailsPatientid),patientId);
				Predicate filepredicate=cb.equal(fileid.get(FileName_.filenameId),fileNameId);
				return query.where(cb.and(catpredicate,patpredicate,filepredicate)).getRestriction();
			}
		};
	}
	 */

}


