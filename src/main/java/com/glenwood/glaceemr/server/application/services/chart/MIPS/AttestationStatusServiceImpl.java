package com.glenwood.glaceemr.server.application.services.chart.MIPS;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.Bean.AttestationBean;
import com.glenwood.glaceemr.server.application.Bean.AttestationInfo;
import com.glenwood.glaceemr.server.application.Bean.MUAnalyticsBean;
import com.glenwood.glaceemr.server.application.Bean.ReportingInfo;
import com.glenwood.glaceemr.server.application.models.AttestationStatus;
import com.glenwood.glaceemr.server.application.models.AttestationStatus_;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.repositories.AttestationStatusRepository;
import com.glenwood.glaceemr.server.application.repositories.EmployeeProfileRepository;
import com.glenwood.glaceemr.server.application.services.employee.EmployeeDataBean;
import com.glenwood.glaceemr.server.application.specifications.AttestationStatusSpecification;


@Service
@Transactional
public class AttestationStatusServiceImpl implements AttestationStatusService{

	@Autowired
	AttestationStatusRepository attestationRepo;
	
	@Autowired
	EmployeeProfileRepository employeeRepo;
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<AttestationStatus> getProviderReportingDetails(Integer reportingYear) {
		
		List<AttestationStatus> response = attestationRepo.findAll(Specifications.where(AttestationStatusSpecification.getReportingStatus(reportingYear)));
        
		return response;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<EmployeeDataBean> getActiveProviderList(){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<EmployeeDataBean> cq = builder.createQuery(EmployeeDataBean.class);
		Root<EmployeeProfile> root = cq.from(EmployeeProfile.class);
		
		Predicate[] restrictions = new Predicate[] {
				builder.equal(root.get(EmployeeProfile_.empProfileIsActive),true),
				root.get(EmployeeProfile_.empProfileGroupid).in(-1,-10),
		};
		
		cq.multiselect(new Selection[]{root.get(EmployeeProfile_.empProfileEmpid), root.get(EmployeeProfile_.empProfileFullname)});
		
		cq.where(restrictions);
		
		cq.orderBy(builder.asc(root.get(EmployeeProfile_.empProfileFullname)));
		
		Query query=em.createQuery(cq);
		
		List<EmployeeDataBean> employeeList = query.getResultList();
		
		return employeeList;
		
	}

	@Override
	public List<AttestationStatus> saveReportingDetails(Integer reportingYear, Integer reportingMethod, Integer reportingType, String comments, Integer reportingStatus, Integer providerId) {
		
		if(providerId == -1){
			
			List<EmployeeDataBean> employeeList = getActiveProviderList();
			
			for(int i=0;i<employeeList.size();i++){
				
				insertAttestationRecord(reportingYear,reportingMethod,reportingType,comments,getReportingStatus(reportingStatus),employeeList.get(i).getEmpFullname());
				
			}
			
		}else{
			
			insertAttestationRecord(reportingYear,reportingMethod,reportingType,comments,getReportingStatus(reportingStatus),providerId);
			
		}
		
		List<AttestationStatus> response = attestationRepo.findAll(Specifications.where(AttestationStatusSpecification.getReportingStatus(reportingYear)));
		
		return response;
		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<MUAnalyticsBean> getAllActiveProviderStatus() {
		
		List<MUAnalyticsBean> resultBean = new ArrayList<MUAnalyticsBean>();
		
		List<ReportingInfo> finalReportingDataDetails = new ArrayList<ReportingInfo>();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Integer> cq1 = builder.createQuery(Integer.class);
		CriteriaQuery<AttestationBean> cq = builder.createQuery(AttestationBean.class);
		Root<AttestationStatus> root1 = cq1.from(AttestationStatus.class);
		Root<EmployeeProfile> root = cq.from(EmployeeProfile.class);
		
		Join<EmployeeProfile, AttestationStatus> attestationTable = root.join(EmployeeProfile_.reportingProvider, JoinType.LEFT);
		
		cq1.distinct(true);

		cq1.multiselect(root1.get(AttestationStatus_.reportingYear));
		
		cq1.groupBy(root1.get(AttestationStatus_.reportingYear));
		
		cq1.orderBy(builder.asc(root1.get(AttestationStatus_.reportingYear)));
		
		List<Integer> reportingYrs = em.createQuery(cq1).getResultList();
		
		Predicate[] restrictions = new Predicate[] {
				builder.equal(root.get(EmployeeProfile_.empProfileIsActive),true),
				root.get(EmployeeProfile_.empProfileGroupid).in(-1,-10),
		};
		
		Selection[] selections= new Selection[] { 
			
			attestationTable.get(AttestationStatus_.reportingYear),
			root.get(EmployeeProfile_.empProfileEmpid),
			root.get(EmployeeProfile_.empProfileFullname),
			attestationTable.get(AttestationStatus_.reportingType),
			attestationTable.get(AttestationStatus_.reportingMethod),
			attestationTable.get(AttestationStatus_.reportingStatus),
			attestationTable.get(AttestationStatus_.reportedDate),
			attestationTable.get(AttestationStatus_.reportingComments),
										
		};
		
		cq.where(restrictions);
		
		cq.multiselect(selections);
		
		cq.orderBy(builder.asc(root.get(EmployeeProfile_.empProfileEmpid)));
		
		List<AttestationBean> reportingInfo = em.createQuery(cq).getResultList();
		
		HashMap<String, HashMap<String, AttestationInfo>> completeReportingInfo = new HashMap<String, HashMap<String,AttestationInfo>>();

		for(int j=0;j<reportingYrs.size();j++){
			
			Integer reportingYear = reportingYrs.get(j);
			
			finalReportingDataDetails = new ArrayList<ReportingInfo>();
			
			completeReportingInfo = new HashMap<String, HashMap<String,AttestationInfo>>();
			
			AttestationBean prevRepObject = new AttestationBean();
			
			boolean isProviderExisting = false;
			
			for(int i=0;i<reportingInfo.size();i++){
			
				ReportingInfo reportingInfoDetails = new ReportingInfo();

				AttestationBean object = reportingInfo.get(i);
				
				AttestationInfo obj = new AttestationInfo(object.getReportingType(), object.getReportingMethod(), object.getReportingStatus(), object.getReportedDate(), object.getReportingComments());

				HashMap<String, AttestationInfo> providerInfo = new HashMap<String, AttestationInfo>();
				
				if(i>0 && !prevRepObject.getReportingProvider().equals(object.getReportingProvider()) && completeReportingInfo.size() >= 1){
					
					reportingInfoDetails.setEmployeeId(prevRepObject.getEmployeeID());
					
					reportingInfoDetails.setProvider(prevRepObject.getReportingProvider());
					
					providerInfo = completeReportingInfo.get(prevRepObject.getReportingProvider());
					
					if(prevRepObject.getReportingYear() == null){

						providerInfo.put("MU", new AttestationInfo());

						providerInfo.put("PQRS", new AttestationInfo());

					}else{
						
						obj = new AttestationInfo(prevRepObject.getReportingType(), prevRepObject.getReportingMethod(), prevRepObject.getReportingStatus(), prevRepObject.getReportedDate(), prevRepObject.getReportingComments());
						
						addToProviderInfo(prevRepObject, obj, reportingYear, providerInfo, completeReportingInfo, true);
						
					}
					
					reportingInfoDetails.setReportingInfo(providerInfo);
					
					finalReportingDataDetails.add(reportingInfoDetails);
					
					completeReportingInfo.remove(prevRepObject.getReportingProvider());
					
				}
				
				prevRepObject = object;
				
				reportingInfoDetails = new ReportingInfo();
				
				providerInfo = new HashMap<String, AttestationInfo>();
				
				obj = new AttestationInfo(object.getReportingType(), object.getReportingMethod(), object.getReportingStatus(), object.getReportedDate(), object.getReportingComments());
				
				if(completeReportingInfo.containsKey(object.getReportingProvider())){
					
					isProviderExisting = true;
					
					providerInfo = completeReportingInfo.get(object.getReportingProvider());
					
				}
				
				addToProviderInfo(object, obj, reportingYear, providerInfo, completeReportingInfo, false);
				
				completeReportingInfo.put(object.getReportingProvider(), providerInfo);
				
				if(providerInfo.keySet().size() == 2){
				
					reportingInfoDetails.setEmployeeId(object.getEmployeeID());
				
					reportingInfoDetails.setProvider(object.getReportingProvider());
					
					reportingInfoDetails.setReportingInfo(providerInfo);
					
					if(isProviderExisting){
						
						isProviderExisting = false;
						finalReportingDataDetails.add(reportingInfoDetails);
						
					}else{
						
						finalReportingDataDetails.add(reportingInfoDetails);
						
					}
					
					completeReportingInfo.remove(object.getReportingProvider());
					
				}
				
			}
			
			resultBean.add(j, new MUAnalyticsBean(reportingYear, finalReportingDataDetails));
			
		}
		
		return resultBean;
		
	}
	
	private void addToProviderInfo(AttestationBean object, AttestationInfo obj, Integer reportingYear,
			HashMap<String, AttestationInfo> providerInfo, HashMap<String, HashMap<String, AttestationInfo>> completeReportingInfo, boolean isFromCondition){
		
		
		if(isFromCondition){
			
			if(object.getReportingType().trim().equals("MU")){

				providerInfo.put("PQRS", new AttestationInfo());

			}

			if(object.getReportingType().trim().equals("PQRS")){

				providerInfo.put("MU", new AttestationInfo());

			}
			
		}else{
			
			if(object.getReportingYear() == null){

				providerInfo.put("MU", obj);

				providerInfo.put("PQRS", obj);

			}else{

				if(completeReportingInfo.containsKey(object.getReportingProvider())){

					providerInfo = completeReportingInfo.get(object.getReportingProvider());

				}

				if(object.getReportingYear().equals(reportingYear)){

					if(object.getReportingType().trim().equals("MU")){

						providerInfo.put("MU", obj);

					}

					if(object.getReportingType().trim().equals("PQRS")){

						providerInfo.put("PQRS", obj);

					}

				}else{
				
					if(providerInfo.keySet().size() == 0){
					
						providerInfo.put(object.getReportingType(), new AttestationInfo());
					
					}else{
					
						if(providerInfo.containsKey("MU")){
						
							providerInfo.put("PQRS", new AttestationInfo());
						
						}else{
						
							providerInfo.put("MU", new AttestationInfo());
						
						}
						
					}
					
				}

			}
			
		}
		
	}
	
	private String getReportingStatus(Integer reportingStatus){
		
		String status = "";
		
		if(reportingStatus == 1){
			status = "Yes";
		}else if(reportingStatus == 2){
			status = "No";
		}else if(reportingStatus == 3){
			status = "Not Required";
		}else if(reportingStatus == 4){
			status = "Not Participated";
		}
		
		return status;
		
	}
	
	private void insertAttestationRecord(Integer reportingYear, Integer reportingMethod, Integer reportingType, String comments, String reportingStatus,Integer providerId){
		
		EmployeeProfile employee = employeeRepo.findOne(providerId);
		
		AttestationStatus providerInfo = new AttestationStatus();
		
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		
		providerInfo.setReportedDate(timeStamp);
		providerInfo.setReportingComments(comments);
		providerInfo.setReportingYear(reportingYear);
		providerInfo.setReportingProvider(employee.getEmpProfileFullname());
		providerInfo.setReportingStatus(reportingStatus);
		
		if(reportingType == 0 || reportingType == -1){
			
			providerInfo.setReportingType("MU");
			
			providerInfo.setReportingMethod("None");
			
			attestationRepo.saveAndFlush(providerInfo);
			
		}
		
		providerInfo = new AttestationStatus();
		
		providerInfo.setReportedDate(timeStamp);
		providerInfo.setReportingComments(comments);
		providerInfo.setReportingYear(reportingYear);
		providerInfo.setReportingProvider(employee.getEmpProfileFullname());
		providerInfo.setReportingStatus(reportingStatus);
		
		if(reportingType == 1 || reportingType == -1){
			
			providerInfo.setReportingType("PQRS");
			
			if(reportingMethod == 1){
				
				providerInfo.setReportingMethod("EHR");
				
			}else if(reportingMethod == 2){
				
				providerInfo.setReportingMethod("Registry");
				
			}else if(reportingMethod == 3){
				
				providerInfo.setReportingMethod("Claims");
				
			}
			
			attestationRepo.saveAndFlush(providerInfo);
			
		}
		
	}
	
	private void insertAttestationRecord(Integer reportingYear, Integer reportingMethod, Integer reportingType, String comments, String reportingStatus, String providerName){
		
		AttestationStatus providerInfo = new AttestationStatus();
		
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		
		providerInfo.setReportedDate(timeStamp);
		providerInfo.setReportingComments(comments);
		providerInfo.setReportingYear(reportingYear);
		providerInfo.setReportingProvider(providerName);
		providerInfo.setReportingStatus(reportingStatus);
		
		if(reportingType == 0 || reportingType == -1){
			
			providerInfo.setReportingType("MU");
			
			providerInfo.setReportingMethod("None");
			
			attestationRepo.saveAndFlush(providerInfo);
			
		}
		
		providerInfo = new AttestationStatus();
		
		providerInfo.setReportedDate(timeStamp);
		providerInfo.setReportingComments(comments);
		providerInfo.setReportingYear(reportingYear);
		providerInfo.setReportingProvider(providerName);
		providerInfo.setReportingStatus(reportingStatus);
		
		if(reportingType == 1 || reportingType == -1){
			
			providerInfo.setReportingType("PQRS");
			
			if(reportingMethod == 1){
				
				providerInfo.setReportingMethod("EHR");
				
			}else if(reportingMethod == 2){
				
				providerInfo.setReportingMethod("Registry");
				
			}else if(reportingMethod == 3){
				
				providerInfo.setReportingMethod("Claims");
				
			}
			
			attestationRepo.saveAndFlush(providerInfo);
			
		}
		
	}

}
