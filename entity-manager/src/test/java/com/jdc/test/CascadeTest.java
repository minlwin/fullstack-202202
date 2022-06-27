package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.demo.entity.Contact;
import com.jdc.demo.entity.Member;

public class CascadeTest {

	EntityManagerFactory emf;
	
	@ParameterizedTest
	@CsvSource("Min Lwin,minlwin,minlwin,09782003098,lwin.zawmin@gmail.com")
	void test(String name, String loginId, String password, String phone, String email) {
		
		var member = new Member(name, loginId, password);
		var contact = new Contact(phone, email);
		member.addContact(contact);
		
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(member);
		
		em.getTransaction().commit();
		
	}
	
	@Test
	void check_persistence_context() {
		
		var em = emf.createEntityManager();
		var contact = em.find(Contact.class, 1);
		
		assertTrue(em.contains(contact));
		assertTrue(em.contains(contact.getMember()));
		assertEquals("Admin User", contact.getMember().getName());
		
		em.detach(contact);
		
		assertFalse(em.contains(contact));
		assertTrue(em.contains(contact.getMember()));
		
	}
	
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
}
