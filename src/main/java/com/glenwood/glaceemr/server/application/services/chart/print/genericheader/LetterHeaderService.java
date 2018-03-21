package com.glenwood.glaceemr.server.application.services.chart.print.genericheader;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.LetterHeaderEmp;
import com.glenwood.glaceemr.server.application.models.LetterHeaderPos;
import com.glenwood.glaceemr.server.application.models.print.GenericLetterHeader;
import com.glenwood.glaceemr.server.application.models.print.LetterHeaderContent;
import com.glenwood.glaceemr.server.application.services.employee.EmployeeDataBean;
import com.glenwood.glaceemr.server.application.services.pos.PosDataBean;

public interface LetterHeaderService {
	
	List<GenericLetterHeader> getLetterHeaderList();
	GenericLetterHeader getLetterHeaderDetails(int letterHeaderId);
	List<LetterHeaderContent> getLetterHeaderContentList(int letterHeaderId);
	GenericLetterHeader saveLetterHeader(GenericLetterHeader genericLetterHeader);
	void deleteLetterHeaderContent(List<LetterHeaderContent> letterHeaderContentList);
	void saveLetterHeaderContent(LetterHeaderContent letterHeaderContent);
	List<EmployeeDataBean> getEmployeeDetails() throws Exception;
	List<PosDataBean> getPOSDetails() throws Exception;
	List<LetterHeaderPos> getLetterHeaderPOSList(Integer headerId,Integer variantId);
	List<LetterHeaderEmp> getLetterHeaderEmpList(Integer headerId,Integer variantid);
	void saveLetterHeaderPOS(LetterHeaderPos letterHeaderPos);
	void saveLetterHeaderEmp(LetterHeaderEmp letterHeaderEmp);
	void deleteLetterHeaderPos(List<LetterHeaderPos> letterHeaderContent);
	void deleteLetterHeaderEmp(List<LetterHeaderEmp> letterHeaderContent);
	List<LetterHeaderPos> fetchLetterHeaderPOSList(Integer headerId,Integer variantId);
	List<EmployeeDataBean> fetchLetterHeaderEmpList(Integer headerId,Integer variantId);
	long getLetterHeaderContentCount(Integer letterHeaderId, List<Integer> variantId);
	List<LetterHeaderContent> getLetterHeaderContentList(Integer letterHeaderId,
			List<Integer> variantId);

}