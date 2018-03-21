package com.glenwood.glaceemr.server.application.services.chart.vitals;


import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.glenwood.glaceemr.server.application.models.VitalGroup;
import com.glenwood.glaceemr.server.application.models.VitalsParameter;
import com.glenwood.glaceemr.server.utils.HUtil;



@Component
@Scope(value="request",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class VitalDataBean{
	
	private LinkedHashMap<Integer, VitalGroupBean> VitalBean;
	VitalDataBean(){
		VitalBean = new LinkedHashMap<Integer, VitalGroupBean>();
	}
	
	public LinkedHashMap<Integer, VitalGroupBean> getVitals(){
		return this.VitalBean;
	}

	public void setVitalGroupData(List<VitalGroup> vitalGroupList) {
		for (VitalGroup vitalGroup : vitalGroupList) {
			VitalGroupBean vitalBean = new VitalGroupBean();
			int groupId = Integer.parseInt(HUtil.Nz(vitalGroup.getVitalGroupId(),"-1"));
			vitalBean.setGroupId(groupId);
			vitalBean.setGroupName(HUtil.Nz(vitalGroup.getVitalGroupName(),""));
			this.setVitalGroup(groupId,vitalBean);
		}


	}

	private void setVitalGroup(int groupId,VitalGroupBean vitalBean) {
		VitalBean.put(groupId,vitalBean);
	}

	
	public void setVitalElementBean(List<VitalsParameter> vitalsList,int groupId) {
		LinkedHashMap<String, VitalElementBean> vitalElementHashMap = new LinkedHashMap<String, VitalElementBean>(); 
		for (VitalsParameter vitalsParameter : vitalsList) {			
			VitalElementBean vitalElementBean = new VitalElementBean();
			vitalElementBean.setVitalGWId(HUtil.Nz(vitalsParameter.getVitalsParameterGwId(),""));
			vitalElementBean.setVitalName(HUtil.Nz(vitalsParameter.getVitalsParameterName(),""));
			vitalElementBean.setVitalUnit(Integer.parseInt(HUtil.Nz(vitalsParameter.getVitalsParameterUnitOfMeasureId(),"-1")));
			vitalElementBean.setVitalGender(Integer.parseInt(HUtil.Nz(vitalsParameter.getVitalsParameterGender(),"")));
			vitalElementBean.setVitalIsvitalGraphNeeded(HUtil.Nz(vitalsParameter.getVitalsParameterGraphNeeded(),"false"));
			vitalElementBean.setVitalIsvitalLastVisit(Boolean.parseBoolean(HUtil.Nz(vitalsParameter.getVitalsParameterPreloadFromLastVisit(),"")));
			vitalElementBean.setVitalConditionType(Integer.parseInt(HUtil.Nz(vitalsParameter.getVitalsParameterCondition().getVitalsParameterConditionType(),"")));
			vitalElementBean.setVitalCondition(HUtil.Nz(vitalsParameter.getVitalsParameterCondition().getVitalsParameterConditionCondition(),""));
			if(vitalsParameter.getUnitsOfMeasureTable()!=null && vitalsParameter.getUnitsOfMeasureTable().getUnitsOfMeasureCode() != null)
				vitalElementBean.setUnitsOfMeasureCode(vitalsParameter.getUnitsOfMeasureTable().getUnitsOfMeasureCode());
			else
				vitalElementBean.setUnitsOfMeasureCode("N/A");
			vitalElementHashMap.put(HUtil.Nz(vitalsParameter.getVitalsParameterGwId(),"-1"),vitalElementBean);
		}
				
		VitalBean.get(groupId).setVitalElements(vitalElementHashMap);
	}

	public VitalGroupBean getVitalGroup(int groupId) {
		return VitalBean.get(groupId);
	}

}