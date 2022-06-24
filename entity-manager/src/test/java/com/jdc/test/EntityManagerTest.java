package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.demo.entity.Member;
import com.jdc.demo.entity.Member.Role;

@TestMethodOrder(OrderAnnotation.class)
public class EntityManagerTest {

	static EntityManagerFactory emf;
	
	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("entity-manager");
	}
	
	@Order(1)
	@ParameterizedTest
	@CsvSource("Min Lwin,minlwin,minlwin")
	void test_creation(String name, String loginId, String password) {
		// Transient State
		var member = new Member(name, loginId, password);
		
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// To be Managed State
		em.persist(member);
		assertEquals(2, member.getId());
		
		assertTrue(em.contains(member));
		
		// To be Detached State
		em.detach(member);
		
		assertFalse(em.contains(member));
		
		member.setRole(Role.Admin);
		
		// To be Managed again
		member = em.merge(member);
		
		assertTrue(em.contains(member));
		
		// Synchronize to Database
		em.getTransaction().commit();
	}
	
	@Order(2)
	@ParameterizedTest
	@ValueSource(ints = 1)
	void test_find(int id) {
		
		var em = emf.createEntityManager();
		var member = em.find(Member.class, id);
		assertNotNull(member);
		
		assertTrue(em.contains(member));
		
		em.detach(member);
		
		assertAll(
				() -> assertEquals("Admin User", member.getName()),
				() -> assertEquals(Role.Admin, member.getRole()),
				() -> assertEquals("admin", member.getLoginId())
		);
	}
	
	@Order(3)
	@ParameterizedTest
	@ValueSource(ints = 1)
	void test_getReference(int id) {
		var em = emf.createEntityManager();
		var member = em.getReference(Member.class, id);
		assertNotNull(member);
		assertTrue(em.contains(member));

		em.detach(member);
		
		assertAll(
				() -> assertThrows(LazyInitializationException.class, () -> member.getName()),
				() -> assertThrows(LazyInitializationException.class, () -> member.getRole()),
				() -> assertThrows(LazyInitializationException.class, () -> member.getLoginId())
		);
	}
	
	
	@Order(4)
	@ParameterizedTest
	@ValueSource(ints = 3)
	void test_find_not_found(int id) {
		var em = emf.createEntityManager();
		var member = em.find(Member.class, id);
		assertNull(member);
	}
	
	@Order(5)
	@ParameterizedTest
	@ValueSource(ints = 3)
	void test_getReference_not_found(int id) {
		var em = emf.createEntityManager();
		var member = em.getReference(Member.class, id);
		assertNotNull(member);
		
		assertThrows(EntityNotFoundException.class, member::getName);
	}
	
	@Order(6)
	@ParameterizedTest
	@ValueSource(ints = 1)
	void test_lazy_fetch_mode(int id) {
		var em = emf.createEntityManager();
		var member = em.getReference(Member.class, id);
		
		em.detach(member);
		
		assertThrows(LazyInitializationException.class, () -> member.getTags().size());
	}
	
	@AfterAll
	static void shutDown() {
		if(null != emf && emf.isOpen()) {
			emf.close();
		}
	}
}
