package com.jdc.spring.jpa.local.repo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.jpa.local.entity.Category;

@Repository
public class CategoryRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Category save(Category c) {
		if(c.getId() == 0) {
			entityManager.persist(c);
			return c;
		}
		
		return entityManager.merge(c);
	}

}
