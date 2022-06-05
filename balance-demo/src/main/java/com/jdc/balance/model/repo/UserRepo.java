package com.jdc.balance.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jdc.balance.model.domain.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{

	Optional<User> findOneByLoginId(String username);

}
