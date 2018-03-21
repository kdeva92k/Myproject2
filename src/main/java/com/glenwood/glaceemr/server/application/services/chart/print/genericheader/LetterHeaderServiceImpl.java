package com.glenwood.glaceemr.server.application.services.chart.print.genericheader;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.BillingConfigTable;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.EmployeeProfile_;
import com.glenwood.glaceemr.server.application.models.LetterHeaderEmp;
import com.glenwood.glaceemr.server.application.models.LetterHeaderEmp_;
import com.glenwood.glaceemr.server.application.models.LetterHeaderPos;
import com.glenwood.glaceemr.server.application.models.PlaceOfService;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosType;
import com.glenwood.glaceemr.server.application.models.SpecialisationReferring;
import com.glenwood.glaceemr.server.application.models.SpecialisationReferring_;
import com.glenwood.glaceemr.server.application.models.print.GenericLetterHeader;
import com.glenwood.glaceemr.server.application.models.print.LetterHeaderContent;
import com.glenwood.glaceemr.server.application.repositories.BillingConfigTableRepository;
import com.glenwood.glaceemr.server.application.repositories.EmpProfileRepository;
import com.glenwood.glaceemr.server.application.repositories.LetterHeaderEmpRepository;
import com.glenwood.glaceemr.server.application.repositories.LetterHeaderPosRepository;
import com.glenwood.glaceemr.server.application.repositories.PosTableRepository;
import com.glenwood.glaceemr.server.application.repositories.print.GenericLetterHeaderRepository;
import com.glenwood.glaceemr.server.application.repositories.print.LetterHeaderContentRepository;
import com.glenwood.glaceemr.server.application.services.chart.print.TextFormatter;
import com.glenwood.glaceemr.server.application.services.employee.EmployeeDataBean;
import com.glenwood.glaceemr.server.application.services.pos.PosDataBean;
import com.glenwood.glaceemr.server.application.specifications.BillingConfigTableSpecification;
import com.glenwood.glaceemr.server.application.specifications.EmployeeSpecification;
import com.glenwood.glaceemr.server.application.specifications.PosTableSpecification;
import com.glenwood.glaceemr.server.application.specifications.print.LetterHeaderSpecification;

@Service
public class LetterHeaderServiceImpl implements LetterHeaderService{
	
	@Autowired
	GenericLetterHeaderRepository genericHeaderRepository;
	
	@Autowired
	LetterHeaderContentRepository letterContentRepository;
	
	@Autowired
	EmpProfileRepository employeeProfileRepository;
	
	@Autowired
	PosTableRepository posTableRepository;
	
	@Autowired
	BillingConfigTableRepository billingConfigTableRepository;
	
	@Autowired
	LetterHeaderEmpRepository letterHeaderEmpRepository;
	
	@Autowired
	LetterHeaderPosRepository letterHeaderPosRepository;
	
	@Autowired
	EntityManager em;
	
	@Override
	public List<GenericLetterHeader> getLetterHeaderList() {
		List<GenericLetterHeader> genericHeaderList=genericHeaderRepository.findAll();
		return genericHeaderList;
	}

	@Override
	public List<LetterHeaderContent> getLetterHeaderContentList(int letterHeaderId) {
		List<LetterHeaderContent> letterHeaderContentList=letterContentRepository.findAll(LetterHeaderSpecification.getLetterHeaderContent(letterHeaderId));
		return letterHeaderContentList;
	}

	
	@Override
	public List<LetterHeaderContent> getLetterHeaderContentList(Integer letterHeaderId, List<Integer> variantId) {
		return letterContentRepository.findAll(LetterHeaderSpecification.getLetterHeaderContent(letterHeaderId,variantId));
	}
	
	
	@Override
	public long getLetterHeaderContentCount(Integer letterHeaderId, List<Integer> variantId) {
		return letterContentRepository.count(LetterHeaderSpecification.getLetterHeaderContent(letterHeaderId,variantId));
	}
	
	
	
	@Override
	public GenericLetterHeader saveLetterHeader(GenericLetterHeader genericLetterHeader) {
		GenericLetterHeader newgenericLetterHeader=genericHeaderRepository.save(genericLetterHeader);
		return newgenericLetterHeader;
	}

	@Override
	public void deleteLetterHeaderContent(List<LetterHeaderContent> letterHeaderContentList) {
		letterContentRepository.delete(letterHeaderContentList);
	}

	@Override
	public void saveLetterHeaderContent(LetterHeaderContent letterHeaderContent) {
		letterContentRepository.save(letterHeaderContent);
		
	}

	@Override
	public GenericLetterHeader getLetterHeaderDetails(int letterHeaderId) {
		GenericLetterHeader genericHeader=genericHeaderRepository.findOne(letterHeaderId);
		return genericHeader;
	}

	@Override
	public List<EmployeeDataBean> getEmployeeDetails() throws Exception {
		
		TextFormatter textFormat = new TextFormatter();

		List<EmployeeProfile> empList =  employeeProfileRepository.findAll(EmployeeSpecification.getUsersList("asc"));
	
		List<EmployeeDataBean> empolyeeBean = parseEmployeeDetails(empList, textFormat);

		return empolyeeBean;
				
	}
	@Override
	public List<PosDataBean> getPOSDetails() throws Exception {
		
		TextFormatter textFormat = new TextFormatter();
		List<PosTable> posList = posTableRepository.findAll(PosTableSpecification.getPOSDetails());
		
		List<PosDataBean> posBean = parsePOSDetails(posList, textFormat);
		
		return posBean;
				
	}
	/**
	 * Parsing Employee Details
	 * @param empList
	 * @param textFormat
	 * @return
	 * @throws Exception
	 */
	private List<EmployeeDataBean> parseEmployeeDetails(List<EmployeeProfile> empList, TextFormatter textFormat) throws Exception {
		
		List<EmployeeDataBean> empBean = new ArrayList<EmployeeDataBean>();
		
		for(int i=0; i<empList.size(); i++) {
			EmployeeProfile emp = empList.get(i);
			
			if(emp != null) {
				int empId = emp.getEmpProfileEmpid();
				int loginId = emp.getEmpProfileLoginid();
				String state = emp.getEmpProfileState();
				BillingConfigTable billing = billingConfigTableRepository.findOne(BillingConfigTableSpecification.getState(state));
				state = "";
				if(billing != null)
					state = billing.getBillingConfigTableLookupDesc();
				String fullName = textFormat.getFormattedName(emp.getEmpProfileFname(), emp.getEmpProfileMi(), emp.getEmpProfileLname(), emp.getEmpProfileCredentials());
//				String fullAddress = textFormat.getAddress(emp.getEmpProfileAddress(), "", emp.getEmpProfileCity(), state, emp.getEmpProfileZip());

				empBean.add(new EmployeeDataBean(empId, loginId, fullName, emp.getEmpProfileAddress(), state, emp.getEmpProfileCity(), emp.getEmpProfileZip(), emp.getEmpProfilePhoneno(), emp.getEmpProfileMailid()));
			}
		}
		
		return empBean;
	}

	/**
     * Parsing POS details
     * @param posList
     * @param textFormat
     * @return
     */
    private List<PosDataBean> parsePOSDetails(List<PosTable> posList, TextFormatter textFormat) {
       
        List<PosDataBean> posBean = new ArrayList<PosDataBean>();
        for(int i = 0; i < posList.size(); i++) {
            PosTable pos = posList.get(i);
            int relationId = pos.getPosTableRelationId();
            int placeId = pos.getPosTablePlaceId();
            String practice = pos.getPosTablePlaceOfService();
            String comments = pos.getPosTableFacilityComments();
            String name = "";
            if(practice != null && !practice.isEmpty())
                name = "(" + practice + ") ";
            if(comments != null && !comments.isEmpty())
                name = name + comments;
           
            String address = null, state = null, city = null, zip = null, phNum = null, faxNum = null;           
            PlaceOfService placeofService = pos.getPlaceOfService();
            if(placeofService != null) {
                state = placeofService.getPlaceOfServiceState();
                BillingConfigTable billing = billingConfigTableRepository.findOne(BillingConfigTableSpecification.getState(state));
                state = "";
                if(billing != null)
                    state = billing.getBillingConfigTableLookupDesc();
               
                address = placeofService.getPlaceOfServiceAddress();
                city = placeofService.getPlaceOfServiceCity();
                zip = placeofService.getPlaceOfServiceZip();
                phNum = placeofService.getPlaceOfServicePhone();
                faxNum = placeofService.getPlaceOfServiceFax();
            }
           
            String type = "";
            PosType posType = pos.getPosType();
            if(posType != null) {
                type = posType.getPosTypeTypeName();
            }
           
            posBean.add(new PosDataBean(relationId, placeId, name, address, state, city, zip, phNum, faxNum, type));
        }
        return posBean;
    }

	@Override
	public List<LetterHeaderPos> fetchLetterHeaderPOSList(Integer headerId, Integer variantId) {
		return letterHeaderPosRepository.findAll(LetterHeaderSpecification.fetchPOSDetails(headerId, variantId));
	}

	@Override
	public List<EmployeeDataBean> fetchLetterHeaderEmpList(Integer headerId, Integer variantId) {
//		return letterHeaderEmpRepository.findAll(LetterHeaderSpecification.fetchEmpDetails(headerId, variantId));
		return fetLetterHeaderEmpDetails(headerId, variantId);
	}

	private List<EmployeeDataBean> fetLetterHeaderEmpDetails(Integer headerId,
			Integer variantId) {
		
		CriteriaBuilder builder= em.getCriteriaBuilder();
		CriteriaQuery<EmployeeDataBean> query= builder.createQuery(EmployeeDataBean.class);
		Root<LetterHeaderEmp> root= query.from(LetterHeaderEmp.class);
		Join<LetterHeaderEmp, EmployeeProfile> empJoin= root.join(LetterHeaderEmp_.empProfile, JoinType.LEFT);
		Join<EmployeeProfile, SpecialisationReferring> specialtyJoin= empJoin.join(EmployeeProfile_.specialityTable, JoinType.LEFT);
		
		Predicate pred= builder.equal(root.get(LetterHeaderEmp_.letterHeaderEmpMapId),headerId);
		Predicate varPred= builder.equal(root.get(LetterHeaderEmp_.letterHeaderEmpVariant),variantId);
		
		query.select(builder.construct(EmployeeDataBean.class, 
										empJoin.get(EmployeeProfile_.empProfileEmpid),
										 empJoin.get(EmployeeProfile_.empProfileLoginid),
										  empJoin.get(EmployeeProfile_.empProfileLname),
										   empJoin.get(EmployeeProfile_.empProfileFname),
										    empJoin.get(EmployeeProfile_.empProfileMi),
										     empJoin.get(EmployeeProfile_.empProfileCredentials),
										      empJoin.get(EmployeeProfile_.empProfileAddress),
										       empJoin.get(EmployeeProfile_.empProfileState),
										        empJoin.get(EmployeeProfile_.empProfileCity),
										         empJoin.get(EmployeeProfile_.empProfileZip),										          
										          empJoin.get(EmployeeProfile_.empProfilePhoneno),
										           empJoin.get(EmployeeProfile_.empProfileMailid),
										            specialtyJoin.get(SpecialisationReferring_.specialisation_referring_name)));
		query.where(pred,varPred);
		query.orderBy(builder.asc(root.get(LetterHeaderEmp_.letterHeaderEmpOrder)));
		
		return em.createQuery(query).getResultList();
	}

	@Override
	public List<LetterHeaderPos> getLetterHeaderPOSList(Integer headerId, Integer variantId) {
		return letterHeaderPosRepository.findAll(LetterHeaderSpecification.getPOSDetails(headerId, variantId));
	}

	@Override
	public List<LetterHeaderEmp> getLetterHeaderEmpList(Integer headerId, Integer variantId) {
		return letterHeaderEmpRepository.findAll(LetterHeaderSpecification.getEmpDetails(headerId, variantId));
	}
	
	@Override
	public void saveLetterHeaderPOS(LetterHeaderPos letterHeaderPos) {
		letterHeaderPosRepository.save(letterHeaderPos);
	}
	
	@Override
	public void saveLetterHeaderEmp(LetterHeaderEmp letterHeaderEmp) {
		letterHeaderEmpRepository.save(letterHeaderEmp);
	}

	@Override
	public void deleteLetterHeaderPos(List<LetterHeaderPos> letterHeaderContent) {
		letterHeaderPosRepository.delete(letterHeaderContent);
	}

	@Override
	public void deleteLetterHeaderEmp(List<LetterHeaderEmp> letterHeaderContent) {
		letterHeaderEmpRepository.delete(letterHeaderContent); 
		
	}

}
