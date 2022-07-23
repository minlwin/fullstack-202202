package com.jdc.demo.model.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.jdc.demo.model.entity.Division;

@Repository
public class DivisionRepo {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Division> findAll() {
		return entityManager.createNamedQuery("Division.findAll", Division.class)
				.getResultList();
	}
}
