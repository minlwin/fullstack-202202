package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.demo.entity.Contact;

public class FetchModeTest {

	static EntityManagerFactory emf;
	
	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("entity-manager");
	}
	
	@ParameterizedTest
	@ValueSource(ints = 1)
	void test_lazy_single_object(int id) {
		
		var em = emf.createEntityManager();
		var contact = em.find(Contact.class, id);
		em.close();
		
		assertThrows(LazyInitializationException.class, contact.getMember()::getName);
	}
	
	@AfterAll
	static void shutDown() {
		if(null != emf && emf.isOpen()) {
			emf.close();
		}
	}
}
