package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.glenwood.glaceemr.server.application.models.BodySite;
import com.glenwood.glaceemr.server.application.models.BookingConfig;


public interface BookingConfigRepository extends JpaRepository<BookingConfig, Integer>,JpaSpecificationExecutor<BookingConfig> {

}