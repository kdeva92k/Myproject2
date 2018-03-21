package com.glenwood.glaceemr.server.application.services.chart.RefillRequest;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.SSCancelOutbox;
import com.glenwood.glaceemr.server.application.models.SSMessageInbox;

public class MedicationListBean {
		
	List<SSMessageInbox> inboxbean;
	public List<SSMessageInbox> getInboxbean() {
		return inboxbean;
	}
	public void setInboxbean(List<SSMessageInbox> inboxbean) {
		this.inboxbean = inboxbean;
	}

	List<SSCancelOutbox> ssoutboxlist;
	
		
	public List<SSCancelOutbox> getSsoutboxlist() {
		return ssoutboxlist;
	}
	public void setSsoutboxlist(List<SSCancelOutbox> ssoutboxlist) {
		this.ssoutboxlist = ssoutboxlist;
	}
	
	public MedicationListBean(List<SSMessageInbox> inboxbean,List<SSCancelOutbox> ssoutboxlist){
		
		this.inboxbean=inboxbean;
		this.ssoutboxlist=ssoutboxlist;
	}
	
	
	
}
