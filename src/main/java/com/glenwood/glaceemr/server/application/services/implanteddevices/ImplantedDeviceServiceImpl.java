package com.glenwood.glaceemr.server.application.services.implanteddevices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import com.glenwood.glaceemr.server.application.models.implanteddevices.ImplantedDevice;
import com.glenwood.glaceemr.server.application.repositories.ImplantedDeviceRepository;

@Service
public class ImplantedDeviceServiceImpl implements ImplantedDeviceService {

	@Autowired
	ImplantedDeviceRepository implantedDeviceRepository;

	@Override
	public List<ImplantedDevice> getImplantedDeviceList(Integer patientId) {
		List<ImplantedDevice> implantedDevices=implantedDeviceRepository.findByImplantedDevicePatientId(patientId);
		return implantedDevices;
	}

	@Override
	public List<ImplantedDevice> getImplantedDeviceListWithStatusReq(Integer patientId, Byte statusId) {
		List<ImplantedDevice> implantedDevices=implantedDeviceRepository.findByImplantedDevicePatientIdAndImplantedDeviceStatus(patientId, statusId);
		return implantedDevices;
	}

	@Override
	public ImplantedDevice getImplantedDeviceDetails(Integer implantedDeviceId) {
		ImplantedDevice implantedDevice=implantedDeviceRepository.findOne(implantedDeviceId);
		return implantedDevice;
	}

	@Override
	public ImplantedDevice saveImplantedDeviceDetails(ImplantedDevice implantedDevice) {
		ImplantedDevice savedimplantedDevice=implantedDeviceRepository.save(implantedDevice);
		return savedimplantedDevice;
	}

	@Override
	public String getDeviceDetailsBasedOnUDI(String udi){
		String hubUrl ="http://hub-icd10.glaceemr.com/DataGateway/UDIServices/getDeviceInfoByUDI?udi="+URLEncoder.encode(udi);
		return getDataFromHub( hubUrl);
	}

	@Override
	public String getDeviceDetailsBasedOnDI(String di) {
		String hubUrl ="http://hub-icd10.glaceemr.com/DataGateway/UDIServices/getDeviceInfoByDI?di="+di;
		return getDataFromHub( hubUrl);
	}
	@Override
	public String getCompanyList() {
		String hubUrl ="http://hub-icd10.glaceemr.com/DataGateway/UDIServices/getCompanyList";
		return getDataFromHub( hubUrl);
	}

	@Override
	public String getBrandListBasedOnCompany(String companyName) {
		String hubUrl ="http://hub-icd10.glaceemr.com/DataGateway/UDIServices/getBrandListByCompany?companyName="+URLEncoder.encode(companyName);
		return getDataFromHub( hubUrl);
	}

	@Override
	public String getModelListBasedOnBrand(String brandName,String companyName) {
		String hubUrl ="http://hub-icd10.glaceemr.com/DataGateway/UDIServices/getModelListByBrand?companyName="+URLEncoder.encode(companyName)+"&brandName="+URLEncoder.encode(brandName);
		return getDataFromHub( hubUrl);
	}

	/* Function to establish data connection with hub server and retrieve data*/
	public String getDataFromHub(String hubUrl){
		try{
			URL url = new URL(hubUrl);
			URLConnection conn = url.openConnection();
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return (sb.toString());
		}catch(IOException e){
			e.printStackTrace();
			return "{Error:\""+e.getMessage()+"\"}";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";

	}
}
