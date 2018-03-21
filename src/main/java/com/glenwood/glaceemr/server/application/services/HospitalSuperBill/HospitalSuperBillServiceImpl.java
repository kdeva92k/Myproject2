package com.glenwood.glaceemr.server.application.services.HospitalSuperBill;

import java.math.BigInteger;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.Bean.RecentServiceBean;
import com.glenwood.glaceemr.server.application.Bean.AdmissionInfoBean;
import com.glenwood.glaceemr.server.application.models.Admission;
import com.glenwood.glaceemr.server.application.models.Cpt;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.ServiceDetail;
import com.glenwood.glaceemr.server.application.models.ServiceDetail_;
import com.glenwood.glaceemr.server.application.repositories.AdmissionRepository;
import com.glenwood.glaceemr.server.application.repositories.CptRepository;
import com.glenwood.glaceemr.server.application.repositories.EmployeeProfileRepository;
import com.glenwood.glaceemr.server.application.repositories.NonServiceDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.ServiceDetailRepository;
import com.glenwood.glaceemr.server.application.specifications.HospitalSuperbillSpecification;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

@Service
public class HospitalSuperBillServiceImpl implements HospitalSuperbillService {

	@Autowired
	AdmissionRepository admissionRepository;

	@Autowired
	ServiceDetailRepository serviceDetailRepository;

	@Autowired
	NonServiceDetailsRepository nonserviceDetailsRepository;

	@Autowired
	CptRepository cptRepository;

	@Autowired
	EntityManager em;

	@Autowired
	EmployeeProfileRepository employeeProfileRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	@Override
	public List<AdmissionInfoBean> getAdmittedPatientsList(Integer posId,Integer doctorId, String date) {
		List<AdmissionInfoBean> admissionDetails=new ArrayList<AdmissionInfoBean>();
		List<Admission> admissionList = new ArrayList<Admission>();
		SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy"); 
		SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date admissiondate =originalFormat.parse(date.toString());
			Admission admission = new Admission();
			admission.setAdmissionDate(Date.valueOf(targetFormat.format(admissiondate)));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try{
			admissionList = admissionRepository.findAll(HospitalSuperbillSpecification.getAdmittedPatientsList(posId));
			List<Integer> patientIdList = new ArrayList<Integer>();
			for (int i = 0; i < admissionList.size(); i++) {
				patientIdList.add(admissionList.get(i).getAdmissionPatientId());
			}
			
			CriteriaBuilder builder = em.getCriteriaBuilder();
			CriteriaQuery<Object> criteriaQuery = builder.createQuery();
			Root<ServiceDetail> root = criteriaQuery.from(ServiceDetail.class);
			criteriaQuery.select(builder.construct(RecentServiceBean.class,root.get(ServiceDetail_.serviceDetailPatientid),
					builder.greatest(root.get(ServiceDetail_.serviceDetailId)),
					builder.greatest(root.get(ServiceDetail_.serviceDetailDos))));
			criteriaQuery.where(root.get(ServiceDetail_.serviceDetailPatientid).in(patientIdList));
			criteriaQuery.groupBy(root.get(ServiceDetail_.serviceDetailPatientid));
			
			List<Object> resultSet = em.createQuery(criteriaQuery).getResultList();
			
			List<RecentServiceBean> resultSetList = new ArrayList<RecentServiceBean>();
			List<Integer> serviceDetailIdList = new ArrayList<Integer>();
			List<java.util.Date> maxDos=new ArrayList<java.util.Date>();
			
			for (int i = 0; i < resultSet.size(); i++) {
				RecentServiceBean hospitalSuperBillBean = (RecentServiceBean) resultSet.get(i);
				resultSetList.add(hospitalSuperBillBean);
				serviceDetailIdList.add(resultSetList.get(i).getServiceDetailId());
				maxDos.add(resultSetList.get(i).getServiceDetailDos());
			}
			
			List<ServiceDetail> service = new ArrayList<ServiceDetail>();
			service = serviceDetailRepository.findAll(HospitalSuperbillSpecification.getserviceDetails(serviceDetailIdList,maxDos));
			
			for (int i = 0; i < admissionList.size(); i++) {
				AdmissionInfoBean AdmisssionInfo = new AdmissionInfoBean();
				AdmisssionInfo.setAdmissionDate(admissionList.get(i).getAdmissionDate());
				AdmisssionInfo.setAdmissionPatientId(admissionList.get(i).getAdmissionPatientId());
				AdmisssionInfo.setAdmissionPOS(admissionList.get(i).getPosTable().getPosTableFacilityComments());
				AdmisssionInfo.setAdmissionDoctorId(admissionList.get(i).getAdmissionDoctorId());
				AdmisssionInfo.setPatientRegistrationDOB(admissionList.get(i).getPatientRegistration().getPatientRegistrationDob());
				AdmisssionInfo.setPatientRegistrationFirstName(admissionList.get(i).getPatientRegistration().getPatientRegistrationFirstName());
				AdmisssionInfo.setPatientRegistrationMidInitial(admissionList.get(i).getPatientRegistration().getPatientRegistrationMidInitial());
				AdmisssionInfo.setPatientRegistrationLastName(admissionList.get(i).getPatientRegistration().getPatientRegistrationLastName());
				AdmisssionInfo.setPatientRegistrationAccNo(admissionList.get(i).getPatientRegistration().getPatientRegistrationAccountno());
				if(admissionList.get(i).getPatientRegistration().getEmpProfile()!=null)
					AdmisssionInfo.setEmpProfileFullname(admissionList.get(i).getPatientRegistration().getEmpProfile().getEmpProfileFullname());
				AdmisssionInfo.setAdmissionAdmittedTo(admissionList.get(i).getAdmissionAdmittedTo());
				AdmisssionInfo.setAdmissionId(admissionList.get(i).getAdmissionId());
				AdmisssionInfo.setPosTableFacilityComments(admissionList.get(i).getPosTable().getPosTableFacilityComments());
				AdmisssionInfo.setPosTablePlaceId(admissionList.get(i).getPosTable().getPosTablePlaceId());
				AdmisssionInfo.setPosId(admissionList.get(i).getPosTable().getPosTableRelationId());
				AdmisssionInfo.setAdmissionDischargeDate(admissionList.get(i).getAdmissionDischargeDate());
				if(admissionList.get(i).getPatientRegistration().getReferringPhyTable()!=null)
					AdmisssionInfo.setReferringDoctor(Optional.fromNullable(admissionList.get(i).getPatientRegistration().getReferringPhyTable().getreferring_doctor_referringdoctor()).or(""));
				int flag = 0;
				
				for (int j=0;j<service.size();j++) {
					if (service.get(j).getServiceDetailPatientid().equals(admissionList.get(i).getAdmissionPatientId())) {
						if(service.get(j).getServiceDetailDos().equals(admissionList.get(i).getAdmissionDate())||service.get(j).getServiceDetailDos().after(admissionList.get(i).getAdmissionDate())){
							flag = 1;
							AdmisssionInfo.setServiceDetailDos(service.get(j).getServiceDetailDos());
							AdmisssionInfo.setServiceDetailId((service.get(j).getServiceDetailId()));
							AdmisssionInfo.setServiceDetailPatientid(service.get(j).getServiceDetailPatientid());
							AdmisssionInfo.setServiceDetailBdoctorid(service.get(j).getServiceDetailBdoctorid());
							AdmisssionInfo.setServiceDetailCharges(service.get(j).getServiceDetailCharges());
							AdmisssionInfo.setServiceDetailDx1(service.get(j).getServiceDetailDx1());
							AdmisssionInfo.setServiceDetailDx2(service.get(j).getServiceDetailDx2());
							AdmisssionInfo.setServiceDetailDx3(service.get(j).getServiceDetailDx3());
							AdmisssionInfo.setServiceDetailDx4(service.get(j).getServiceDetailDx4());
							AdmisssionInfo.setServiceDetailModifier1(service.get(j).getServiceDetailModifier1());
							AdmisssionInfo.setServiceDetailModifier2(service.get(j).getServiceDetailModifier2());
							AdmisssionInfo.setServiceDetailRdoctorid(service.get(j).getServiceDetailRdoctorid());
							AdmisssionInfo.setServiceDetailSdoctorid(service.get(j).getServiceDetailSdoctorid());
							AdmisssionInfo.setServiceDetailUnit(service.get(j).getServiceDetailUnit());
							AdmisssionInfo.setServiceDetailDop(service.get(j).getServiceDetailDop());
							AdmisssionInfo.setCptCptcode(service.get(j).getCpt().getCptCptcode());
							if(service.get(j).getSdoctors()!=null)
								AdmisssionInfo.setServiceDoctor(Optional.fromNullable(service.get(j).getSdoctors().getEmpProfileFullname()).or(""));
							if(service.get(j).getBdoctors()!=null)
								AdmisssionInfo.setBillingDoctor(Optional.fromNullable(service.get(j).getBdoctors().getEmpProfileFullname()).or(""));
						}	
					}
				}		
				admissionDetails.add(AdmisssionInfo);
			}
			return admissionDetails;
		} catch (Exception e) {
			e.printStackTrace();
			return admissionDetails;
		}
	}

	/**
	 * To get frequently used cpt codes
	 * @param String posTypeId 
	 * if posTypeId is equal to "zero" then get hospital visit cpt codes 
	 * if posTypeId is equal to "one" then get Nursing home cpt codes
	 */
	@Override
	public List<Cpt> getCptCodes(String posTypeId) {
		List<Cpt> frequentlyUsedCodes = cptRepository.findAll(HospitalSuperbillSpecification.getCptcodes(posTypeId));
		return frequentlyUsedCodes;
	}

	/**
	 * For to update discharge date in admission table for a particular patient.
	 * @param Integer patientId
	 * @param String dischargeDate
	 * @param Integer admissionId
	 */
	@Override
	public List<Admission> updateDischargeDate(Integer patientId,String dischargeDate, Integer admissionId) {
		patientId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(patientId.toString())).or("-1"));
		admissionId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(admissionId.toString())).or("-1"));
		List<Admission> updateDischargeDate=new ArrayList<Admission>();
		try{
			updateDischargeDate = admissionRepository.findAll(HospitalSuperbillSpecification.getUpdateRowData(patientId, admissionId));
			SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy"); 
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date =originalFormat.parse(dischargeDate.toString());
			for(Admission admission : updateDischargeDate) {
				admission.setAdmissionDischargedDate(Date.valueOf(targetFormat.format(date)));
				admission= admissionRepository.save(admission);
			}
			return updateDischargeDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateDischargeDate;
	}

	/**
	 * Implementation to get previousVisitDxCodes
	 * @param Integer patientId
	 * @return List<ServiceDetail> previousDxCodes
	 */
	@Override
	public List<ServiceDetail> getPreviousVisitDxCodes(Integer patientId) {
		patientId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(patientId.toString())).or("-1"));
		List<ServiceDetail> selectPreviousDx=new ArrayList<ServiceDetail>(); 
		try {
			selectPreviousDx = serviceDetailRepository.findAll(HospitalSuperbillSpecification.getpreviousVisitDxCodes(patientId),new PageRequest(0, 1, Direction.DESC,"serviceDetailDos")).getContent();
			return selectPreviousDx;
		} catch (Exception e) {
			e.printStackTrace();
			return selectPreviousDx;
		}
	}


	/**
	 * Implementation to get provider information
	 *  @return List<EmpProfile>:Employee details
	 */
	@Override
	public List<EmployeeProfile> getProviderList() {
		List<EmployeeProfile> providersList = employeeProfileRepository.findAll(HospitalSuperbillSpecification.getListOfProviders());
		return providersList;
	}

	/**
	 * To get service information
	 * @param Integer patientId
	 * @param String admissionDate
	 */
	@Override
	public List<ServiceDetail> getServicesList(Integer patientId,String admissionDate) {
		List<ServiceDetail> serviceInfo = new ArrayList<ServiceDetail>();
		int patientid = Optional.fromNullable(patientId).or(-1);
		SimpleDateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy"); 
		java.util.Date formattedAdmDate=null;
		try {
			formattedAdmDate =originalFormat.parse(admissionDate.toString());
			ServiceDetail serviceDetail = null;
			serviceDetail = new ServiceDetail();
			serviceDetail.setServiceDetailDos(new Date(formattedAdmDate.getTime()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		} 
		try{
			serviceInfo = serviceDetailRepository.findAll(HospitalSuperbillSpecification.getServicesList(patientid,new Date(formattedAdmDate.getTime())));
		} catch (Exception e) {
			e.printStackTrace();
			return serviceInfo;
		}
		return serviceInfo;
	}

}