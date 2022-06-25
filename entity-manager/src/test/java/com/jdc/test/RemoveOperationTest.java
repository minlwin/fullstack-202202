package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.demo.entity.Member;

public class RemoveOperationTest extends OperationTest{

	@ParameterizedTest
	@CsvFileSource(resources = "/members.csv")
	@Override
	void operate_to_trasient(int id, String name, String loginId, String password) {
		var em = emf.createEntityManager();
		var member = new Member(name, loginId, password);
		
		assertFalse(em.contains(member));
		
		em.getTransaction().begin();
		em.remove(member);
		em.getTransaction().commit();
	}

	@ParameterizedTest
	@ValueSource(ints = 1)
	@Override
	void operate_to_managed(int id) {
		
		var em = emf.createEntityManager();
		var member = em.find(Member.class, id);
		assertTrue(em.contains(member));
		
		em.getTransaction().begin();
		em.remove(member);
		
		assertFalse(em.contains(member));
		
		em.getTransaction().commit();		
	}

	@ParameterizedTest
	@ValueSource(ints = 1)
	@Override
	void operate_to_detached(int id) {

		var em = emf.createEntityManager();
		var member = em.find(Member.class, id);
		
		em.detach(member);
		assertFalse(em.contains(member));
		
		assertThrows(IllegalArgumentException.class, () -> {
			em.getTransaction().begin();
			em.remove(member);
			em.getTransaction().commit();		
		});
	}

	@ParameterizedTest
	@ValueSource(ints = 1)
	@Override
	void operate_to_removed(int id) {
		var em = emf.createEntityManager();
		var member = em.find(Member.class, id);
		em.getTransaction().begin();
		em.remove(member);
		assertFalse(em.contains(member));
		
		em.remove(member);
		em.getTransaction().commit();
	}
	
	
}
