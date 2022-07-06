package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jdc.query.entity.Category;

public class GettingStartTest {

	EntityManagerFactory emf;
	EntityManager em;

	@BeforeEach
	void init() {
		emf = Persistence.createEntityManagerFactory("getting-start-query");
		em = emf.createEntityManager();
	}

	@AfterEach
	void close() {
		if (null != emf && emf.isOpen()) {
			emf.close();
		}
	}

	@Test
	void select_count() {

		var query = em.createNamedQuery("Category.allCount", Long.class);

		var count = query.getSingleResult();
		assertEquals(7, count);
	}

	@Test
	void select_all() {
		
		var query = em.createNamedQuery("Category.getAll", Category.class);
		
		var stream = query.getResultStream();
		
		stream.forEach(c -> System.out.println(c.getName()));
	}
	
	@Test
	void update() {
		
		em.getTransaction().begin();
		
		var query = em.createNamedQuery("Category.updateNameById");
		
		query.setParameter("name", "Mens");
		query.setParameter("id", 1);
		
		var count = query.executeUpdate();
		em.getTransaction().commit();
		
		assertEquals(1, count);
	}
}
