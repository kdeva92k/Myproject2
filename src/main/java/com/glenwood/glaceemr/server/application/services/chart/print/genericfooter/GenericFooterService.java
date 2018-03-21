package com.glenwood.glaceemr.server.application.services.chart.print.genericfooter;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.print.GenericLetterFooter;

public interface GenericFooterService {

	List<GenericLetterFooter> getGenericFooterList();
	GenericLetterFooter saveGenericFooter(GenericLetterFooter genericLetterFooter);
	GenericLetterFooter getGenericFooter(Integer genericLetterFooterId);

}
