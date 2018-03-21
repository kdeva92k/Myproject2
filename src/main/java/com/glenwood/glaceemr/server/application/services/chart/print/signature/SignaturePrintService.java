package com.glenwood.glaceemr.server.application.services.chart.print.signature;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.DoctorSign;
import com.glenwood.glaceemr.server.application.models.print.SignaturePrint;

public interface SignaturePrintService {

	List<SignaturePrint> getSignaturePrintStyleList();

	SignaturePrint saveSignaturePrint(SignaturePrint signaturePrint);

	SignaturePrint getSignaturePrint(Integer signaturePrintId);
	
	DoctorSign getUserSignDetails(Integer loginId);
	
	DoctorSign saveUserSignDetails(DoctorSign doctorSign);

}
