package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.glenwood.glaceemr.server.application.models.SkinTestOrderEntry;

public interface SkinTestOrderEntryRepository extends JpaRepository<SkinTestOrderEntry, Integer> ,JpaSpecificationExecutor<SkinTestOrderEntry> {

}
