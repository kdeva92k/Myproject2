package com.glenwood.glaceemr.server.application.services.implanteddevices;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.implanteddevices.ImplantedDevice;

public interface ImplantedDeviceService {

	List<ImplantedDevice> getImplantedDeviceList(Integer patientId);

	List<ImplantedDevice> getImplantedDeviceListWithStatusReq(Integer patientId, Byte statusId);

	ImplantedDevice getImplantedDeviceDetails(Integer implantedDeviceId);
	
	ImplantedDevice saveImplantedDeviceDetails(ImplantedDevice implantedDevice);

	String getDeviceDetailsBasedOnUDI(String udi);

	String getDeviceDetailsBasedOnDI(String di);

	String getCompanyList();

	String getBrandListBasedOnCompany(String companyName);

	String getModelListBasedOnBrand(String brandName,String companyName);
}
