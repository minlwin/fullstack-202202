package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.query.entity.Category;

public class GettingStartTest extends AbstractTest{

	@Test
	void select_count() {

		var query = em.createNamedQuery("Category.allCount", Long.class);

		var count = query.getSingleResult();
		assertEquals(7, count);
	}

	@Test
	void select_all() {
		
		var query = em.createNamedQuery("Category.getAll", Category.class);
		
		var stream = query.getResultStream();
		
		stream.forEach(c -> System.out.println(c.getName()));
	}
	
	@Test
	void update() {
		
		em.getTransaction().begin();
		
		var query = em.createNamedQuery("Category.updateNameById");
		
		query.setParameter("name", "Mens");
		query.setParameter("id", 1);
		
		var count = query.executeUpdate();
		em.getTransaction().commit();
		
		assertEquals(1, count);
	}
	
	@ParameterizedTest
	@CsvSource({
		"f,2",
		"e,1"
	})
	void seach_category_by_name_like(String name, int count) {
		var query = em.createNamedQuery("Category.findByNameLike", Category.class);
		query.setParameter(1, name.toLowerCase().concat("%"));
		
		var list = query.getResultList();
		assertEquals(count, list.size());
	}
}
