package com.jdc.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractTest {

	protected static EntityManagerFactory emf;
	protected EntityManager em;
	
	@BeforeAll
	public static void init() {
		emf = Persistence.createEntityManagerFactory("getting-start-query");
	}
	
	@AfterAll
	public static void close() {
		if (null != emf && emf.isOpen()) {
			emf.close();
		}
	}

	@BeforeEach
	public void before() {
		em = emf.createEntityManager();
	}

	@AfterEach
	public void after() {
		em.close();
	}

}
