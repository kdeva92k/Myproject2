package com.glenwood.glaceemr.server.application.services.audittrail;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;


import com.glenwood.glaceemr.server.application.models.AuditTrail;

public interface AuditTrailFetchService {
	public Iterable<AuditTrail> getSearchResult(int userId, String parentModule, String subModule, String outcome, String desc, String startDate, String endDate, String action, int parentEvent, int patientId, String sessionId, String clientIp, int logId, String sortProperty, String order, int currentPage, int pageSize);
	
	public String generateCsvCount(int userId, String parentModule, String subModule, String outcome, String desc, String startDate, String endDate, String action, int parentEvent, int patientId, String sessionId, String clientIp, int logId, HttpServletResponse response) throws IOException;	
	
	public Iterable<AuditTrail> generateCsv(int userId, String parentModule, String subModule, String outcome, String desc, String startDate, String endDate, String action, int parentEvent, int patientId, String sessionId, String clientIp, int logId, String sortProperty, String order, HttpServletResponse response) throws IOException;
}
