package com.jdc.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EntityManagerTest {

	static EntityManagerFactory emf;
	
	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("entity-manager");
	}
	
	@Test
	void test_creation() {
		
	}
	
	@AfterAll
	static void shutDown() {
		if(null != emf && emf.isOpen()) {
			emf.close();
		}
	}
}
