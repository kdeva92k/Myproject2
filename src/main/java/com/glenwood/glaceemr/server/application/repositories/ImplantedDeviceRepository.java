package com.glenwood.glaceemr.server.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.implanteddevices.ImplantedDevice;


@Repository
public interface ImplantedDeviceRepository  extends JpaRepository<ImplantedDevice, Integer>,JpaSpecificationExecutor<ImplantedDevice>{
	 List<ImplantedDevice> findByImplantedDevicePatientId(Integer patientId);
	 List<ImplantedDevice> findByImplantedDevicePatientIdAndImplantedDeviceStatus(Integer patientId,Byte statusId);
}