package com.glenwood.glaceemr.server.application.services.chart.print.patientheader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.print.PatientHeader;
import com.glenwood.glaceemr.server.application.models.print.PatientHeaderDetails;
import com.glenwood.glaceemr.server.application.repositories.print.PatientHeaderDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.print.PatientHeaderRepository;
import com.glenwood.glaceemr.server.application.specifications.print.PatientHeaderSpecification;

@Service
public class PatientHeaderServiceImpl implements PatientHeaderService{
	
	@Autowired
	PatientHeaderRepository patientHeaderRepository;
	
	@Autowired
	PatientHeaderDetailsRepository patientHeaderDetailRepository;

	@Override
	public List<PatientHeader> getPatientHeaderList() {
		List<PatientHeader> patientHeaderList=patientHeaderRepository.findAll();
		return patientHeaderList;
	}

	@Override
	public List<PatientHeaderDetails> getPatientHeaderDetailList(int patientHeaderId,int pageId){
			List<PatientHeaderDetails> patientHeaderDetailList=patientHeaderDetailRepository.findAll(PatientHeaderSpecification.getPatientHeaderDetails(patientHeaderId,pageId));
			return patientHeaderDetailList;
	}

	@Override
	public PatientHeader savePatientHeader(PatientHeader patientHeader) {
		PatientHeader savedPatientHeader=patientHeaderRepository.save(patientHeader);
		return savedPatientHeader;
	}

	@Override
	public void deletePatientHeaderDetails(List<PatientHeaderDetails> PatientHeaderDetailsList) {
		patientHeaderDetailRepository.delete(PatientHeaderDetailsList);
		
	}

	@Override
	public void savePatientHeaderDetails(PatientHeaderDetails patientHeaderDetails) {
		patientHeaderDetailRepository.save(patientHeaderDetails);
		
	}

	@Override
	public PatientHeader getPatientHeader(int patientHeaderId) {
		PatientHeader patientHeader=patientHeaderRepository.findOne(patientHeaderId);
		return patientHeader;
	}
	
	//To get patient header attribute count
	@Override
	public Integer getPatientHeaderAttributeCount(int patientHeaderId, int pageId) {
		return Integer.parseInt(patientHeaderDetailRepository.countByPatientHeaderIdAndPageId(patientHeaderId, pageId)+"");
	}
}
