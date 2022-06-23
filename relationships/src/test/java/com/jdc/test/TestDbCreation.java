package com.jdc.test;

import java.awt.Color;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.student.entity.Student;

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
	
	@ParameterizedTest
	@CsvSource({
		"Aung Aung,aungaung,aungaung"
	})
	void test(String name, String loginId, String password) {
		
		var em = emf.createEntityManager();
		em.getTransaction().begin();
		
		var account = new Student(name, loginId, password);
		account.setColor(Color.DARK_GRAY);
		
		em.persist(account);
		
		em.getTransaction().commit();
	}
}
