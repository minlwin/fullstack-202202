package com.jdc.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractTest {

	protected EntityManagerFactory emf;
	protected EntityManager em;

	@BeforeEach
	public void init() {
		emf = Persistence.createEntityManagerFactory("getting-start-query");
		em = emf.createEntityManager();
	}

	@AfterEach
	public void close() {
		em.close();
		if (null != emf && emf.isOpen()) {
			emf.close();
		}
	}

}
