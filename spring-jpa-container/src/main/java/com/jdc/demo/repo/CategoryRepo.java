package com.jdc.demo.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.demo.entity.Category;

@Repository
public class CategoryRepo {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Category save(Category c) {
		return entityManager.merge(c);
	}
}
