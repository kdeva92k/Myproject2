package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.glenwood.glaceemr.server.application.models.SchedulerUserDefault;

public interface SchedulerUserDefaultRepository extends JpaRepository<SchedulerUserDefault, Integer>,JpaSpecificationExecutor<SchedulerUserDefault>{

}
