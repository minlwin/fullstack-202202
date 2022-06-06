package com.jdc.balance.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jdc.balance.model.domain.entity.UserAccessLog;

public interface UserAccessLogRepo extends JpaRepository<UserAccessLog, Integer>, JpaSpecificationExecutor<UserAccessLog> {

}
