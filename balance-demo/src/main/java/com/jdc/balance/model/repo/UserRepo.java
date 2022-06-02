package com.jdc.balance.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.balance.model.domain.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	Optional<User> findOneByLoginId(String username);

}
