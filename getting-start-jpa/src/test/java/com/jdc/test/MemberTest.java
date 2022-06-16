package com.jdc.test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jdc.entity.Company;
import com.jdc.entity.Member;

public class MemberTest {

	static EntityManagerFactory emf;
	
	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("helloJpa");
	}
	
	@AfterAll
	static void close() {
		if(null != emf && emf.isOpen()) {
			emf.close();
		}
	}
	
	@Test
	void test_create_member() {
		
		var member = new Member();
		member.setName("Thidar");
		
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(member);
		
		System.out.println("Member Id is %s.".formatted(member.getId()));
		
		var company = new Company();
		company.setName("JDC");
		em.persist(company);
		
		System.out.println("Company Id is %s.".formatted(company.getId()));
		
		em.getTransaction().commit();
	}
}
