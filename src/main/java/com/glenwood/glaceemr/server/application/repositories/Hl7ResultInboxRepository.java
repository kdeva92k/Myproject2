package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.glenwood.glaceemr.server.application.models.Hl7ResultInbox;

public interface Hl7ResultInboxRepository extends JpaRepository<Hl7ResultInbox,Integer>,JpaSpecificationExecutor<Hl7ResultInbox> {

}
