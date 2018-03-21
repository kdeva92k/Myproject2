package com.glenwood.glaceemr.server.application.services.chart.reports;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.Overridealerts;
import com.glenwood.glaceemr.server.application.repositories.OverridealertsRepository;
import com.google.common.base.Optional;

@Service
@Transactional
public class OverrideAlertsServiceImpl implements OverrideAlertsService{

	@Autowired
	OverridealertsRepository overridealertsRepository;
	
	@Override
	public String insertAlert(String elementids, Integer patientid,Integer fsElementType,Integer flowsheetId,
			Integer overriddenBy,String reason,String data) throws Exception {
		String message="";
		reason=URLDecoder.decode(Optional.fromNullable(reason).or(""),"UTF-8");
		data=Optional.fromNullable(data).or("");
		fsElementType=Optional.fromNullable(fsElementType).or(-1);
		flowsheetId=Optional.fromNullable(flowsheetId).or(-1);
		elementids=URLDecoder.decode(Optional.fromNullable(elementids).or("-1"),"UTF-8");
		String[] flowsheetIdSplit = elementids.split("@@@");
		for(int i=0 ; i < flowsheetIdSplit.length ; i++ ){
			if(!flowsheetIdSplit[i].trim().equals("")){
				Overridealerts override=new Overridealerts();
				override.setPatientid(patientid);
				override.setOverridden(true);
				override.setReason(reason);
				override.setInstanceid(-100);
				override.setData(data);
				override.setOverriddenBy(overriddenBy);
				override.setOverriddenOn(new Timestamp(new Date().getTime()));
				override.setOverridealertsAlertType(1);
				override.setOverridealertsFlowsheetMapId(flowsheetId);
				override.setOverridealertsFlowsheetElementType(fsElementType);
				override.setOverridealertsFlowsheetElementId(flowsheetIdSplit[i]);
				overridealertsRepository.save(override);
			}
		}
		message="Success";
		return message;
	}

}
