package com.glenwood.glaceemr.server.application.services.chart.RefillRequest;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.SSCancelOutbox;
import com.glenwood.glaceemr.server.application.models.SSMessageInbox;

public class SSMessageBean {
	
	
	public List<SSMessageInbox> getSsinboxlist() {
		return ssinboxlist;
	}
	public void setSsinboxlist(List<SSMessageInbox> ssinboxlist) {
		this.ssinboxlist = ssinboxlist;
	}
	public List<SSCancelOutbox> getSsoutboxlist() {
		return ssoutboxlist;
	}
	public void setSsoutboxlist(List<SSCancelOutbox> ssoutboxlist) {
		this.ssoutboxlist = ssoutboxlist;
	}
	List<SSMessageInbox> ssinboxlist;
	List<SSCancelOutbox> ssoutboxlist;
	
	public SSMessageBean(List<SSMessageInbox> ssinboxlist,List<SSCancelOutbox> ssoutboxlist){
		this.ssinboxlist=ssinboxlist;
		this.ssoutboxlist=ssoutboxlist;
		
	}
}
