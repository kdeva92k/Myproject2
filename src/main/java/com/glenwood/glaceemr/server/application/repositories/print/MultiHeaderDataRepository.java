package com.glenwood.glaceemr.server.application.repositories.print;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.glenwood.glaceemr.server.application.models.print.MultiHeaderData;

public interface MultiHeaderDataRepository  extends JpaRepository<MultiHeaderData, Integer>,JpaSpecificationExecutor<MultiHeaderData>{

}
