package com.glenwood.glaceemr.server.application.services.pqrsreport;

import java.util.Date;

public interface PqrsReportService {
	
	public void getPatientServices(int patientId,int providerId,  Date startDate, Date endDate, String accountID, int flag)throws Exception;


}
