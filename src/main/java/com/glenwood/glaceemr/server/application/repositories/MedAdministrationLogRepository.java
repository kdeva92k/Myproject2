package com.glenwood.glaceemr.server.application.repositories;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glenwood.glaceemr.server.application.models.MedsAdminLog;

@Repository
public interface MedAdministrationLogRepository extends JpaRepository<MedsAdminLog, Integer>,JpaSpecificationExecutor<MedsAdminLog> {
	@Query("select current_timestamp() from Users pb where 1=1")
	   Timestamp findCurrentTimeStamp();
}
