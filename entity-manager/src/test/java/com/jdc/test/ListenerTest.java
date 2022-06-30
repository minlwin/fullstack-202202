package com.jdc.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jdc.demo.entity.Member;

public class ListenerTest {

	private EntityManagerFactory emf;
	
	@BeforeEach
	void init() {
		emf = Persistence.createEntityManagerFactory("entity-manager");
	}
	
	@AfterEach
	void close() {
		if(null != emf && emf.isOpen()) {
			emf.close();
		}
	}
	
	@Test
	void test_create() {
		
		var member = new Member("Thidar", "thidar", "thidar");
		
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(member);
		
		em.getTransaction().commit();
	}
	
	@Test
	void test_update() {
		var em = emf.createEntityManager();
		var member = em.find(Member.class, 1);
		
		em.getTransaction().begin();
		member.setName("Min Lwin");
		em.getTransaction().commit();
	}
}
