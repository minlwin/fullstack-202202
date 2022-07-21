package com.jdc.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class AbstractTest {

	protected EntityManager em;
	EntityManagerFactory emf;
	

	@BeforeEach
	void init() {
		emf = Persistence.createEntityManagerFactory("join-demo");
		em = emf.createEntityManager();
	}
	
	@AfterEach
	void close() {
		em.close();
		emf.close();
	}
}
