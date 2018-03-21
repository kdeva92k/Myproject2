package com.glenwood.glaceemr.server.application.services.chart.SaveClinicalData;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Transactional
@RequestMapping(value="/user/SavePatientClinicalData.Action")
public class SavePatientClinicalDataController {
	
	
	@Autowired 
	SaveClinicalDataService saveClinicalDataService;
	
	@RequestMapping(value="/SaveData",method=RequestMethod.POST)
	@ResponseBody
	public void  saveData(@RequestBody SaveDataJSON dataJson) throws Exception{
		Integer templateId=dataJson.getTemplateId();
		Integer chartId=dataJson.getChartId();
		Integer patientId=dataJson.getPatientId();
		Integer encounterId=dataJson.getEncounterId();
		String patientElementsJSON=dataJson.getSaveObj();
		patientElementsJSON=URLDecoder.decode(patientElementsJSON, "UTF-8");
		saveClinicalDataService.saveData(patientId,encounterId,chartId,templateId,patientElementsJSON);
	}
	

	

}
