package com.jdc.balance.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.balance.model.domain.entity.BalanceItem;

public interface BalanceItemRepo extends JpaRepository<BalanceItem, Integer>{

}
