package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.jdc.student.entity.Account;
import com.jdc.student.entity.Account.Role;
import com.jdc.student.entity.Address;

public class TestDbCreation {

	static EntityManagerFactory emf;
	
	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("relationships");
	}
	
	@AfterAll
	static void close() {
		if(null != emf && emf.isOpen()) {
			emf.close();
		}
	}
	
	@Test
	void test() {
		
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// create account
		var account = new Account();
		account.setLoginId("thidar");
		account.setPassword("thidar");
		account.setRole(Role.Admin);
		account.setName("Thidar");
		
		em.persist(account);
		
		assertEquals(1, account.getId());
		
		// create address
		var address = new Address();
		address.setAccount(account);
		address.setAddress("Kamayut 1 Block");
		address.setPhone("097667888886");
		address.setEmail("thidar@gmail.com");
		
		em.persist(address);
		
		em.getTransaction().commit();
		
	}
}
