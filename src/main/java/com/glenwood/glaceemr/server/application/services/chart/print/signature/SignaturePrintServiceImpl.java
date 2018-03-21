package com.glenwood.glaceemr.server.application.services.chart.print.signature;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.DoctorSign;
import com.glenwood.glaceemr.server.application.models.print.SignaturePrint;
import com.glenwood.glaceemr.server.application.repositories.DoctorsignRepository;
import com.glenwood.glaceemr.server.application.repositories.print.SignaturePrintRepository;

@Service
public class SignaturePrintServiceImpl implements SignaturePrintService{

	@Autowired
	SignaturePrintRepository signaturePrintRepository;
	
	@Autowired
	DoctorsignRepository doctorsignRepository;

	@Override
	public List<SignaturePrint> getSignaturePrintStyleList() {
		List<SignaturePrint> signList=signaturePrintRepository.findAll();
		return signList;
	}
	
	@Override
	public SignaturePrint saveSignaturePrint(SignaturePrint signaturePrint) {
		SignaturePrint savedSignaturePrint=signaturePrintRepository.save(signaturePrint);
		return savedSignaturePrint;
	}

	@Override
	public SignaturePrint getSignaturePrint(Integer signaturePrintId) {
		SignaturePrint signaturePrint=signaturePrintRepository.findOne(signaturePrintId);
		return signaturePrint;
	}

	@Override
	public DoctorSign getUserSignDetails(Integer loginId) {
		DoctorSign doctorSignDetails=doctorsignRepository.findOne(loginId);
		return doctorSignDetails;
	}

	@Override
	public DoctorSign saveUserSignDetails(DoctorSign doctorSign) {
		DoctorSign doctorSignDetails=doctorsignRepository.save(doctorSign);
		return doctorSignDetails;
	}
	

}
