package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.query.entity.Customer;

public class FromClauseTest extends AbstractTest{

	@ParameterizedTest
	@CsvSource(value = {
			"Aun,094,1",
			"Aun,099,0"
	})
	void test_name_and_phone_like(String name, String phone, int count) {
		var jpql = "select c from Customer c where name like :name and phone like :phone";
		
		var query = em.createQuery(jpql, Customer.class);
		query.setParameter("name", name.concat("%"));
		query.setParameter("phone", phone.concat("%"));
		
		var result = query.getResultList();
		assertEquals(count, result.size());
	}
	
	@ParameterizedTest
	@CsvSource({
		"Red,3",
		"Blue,1",
		"Green,1",
		"Purple,0"
	})
	void test_member_of(String color, long count) {
		
		var jpql = "select count(p) from Product p where :color member of p.colors";
		
		var query = em.createQuery(jpql, Long.class);
		query.setParameter("color", color);
		
		var reuslt = query.getSingleResult();
		assertEquals(count, reuslt);
	}

	@ParameterizedTest
	@CsvSource({
		"2022-05-15,2022-05-30,5",
		"2022-05-16,2022-05-30,3",
		"2022-06-06,2022-06-07,0",
	})
	void test_between(LocalDate from, LocalDate to, long count) {
		var jpql = "select count(s) from Sale s where s.saleDate between :from and :to";
		
		var query = em.createQuery(jpql, Long.class);
		query.setParameter("from", from);
		query.setParameter("to", to);
		
		var result = query.getSingleResult();
		assertEquals(count, result);
	}
}
