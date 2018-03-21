package com.glenwood.glaceemr.server.application.services.chart.print.genericheader;


import java.util.List;
import com.glenwood.glaceemr.server.application.models.LetterHeaderPos;
import com.glenwood.glaceemr.server.application.services.pos.PosDataBean;

public class LetterHeaderPosBean {

	List<PosDataBean> posList;
	List<LetterHeaderPos> savedPosList;

	public List<PosDataBean> getPosList() {
		return posList;
	}

	public void setPosList(List<PosDataBean> posList) {
		this.posList = posList;
	}

	public List<LetterHeaderPos> getSavedPosList() {
		return savedPosList;
	}

	public void setSavedPosList(List<LetterHeaderPos> savedPosList) {
		this.savedPosList = savedPosList;
	}

}
