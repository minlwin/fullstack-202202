package com.jdc.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class OperationTest {

	protected EntityManagerFactory emf;
	
	@BeforeEach
	void init() {
		emf = Persistence.createEntityManagerFactory("entity-manager");
	}
	
	@AfterEach
	void shutDown() {
		if(null != emf && emf.isOpen()) {
			emf.close();
		}
	}
	
	abstract void operate_to_trasient(int id, String name, String loginId, String password);
	abstract void operate_to_managed(int id);
	abstract void operate_to_detached(int id);
	abstract void operate_to_removed(int id);
	

}
