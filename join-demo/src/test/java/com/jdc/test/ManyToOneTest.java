package com.jdc.test;

import org.junit.jupiter.api.Test;

import com.jdc.join.demo.entity.Category;

public class ManyToOneTest extends AbstractTest{

	@Test
	void default_fetch() {
		
		em.createQuery("""
				select c from Category c join fetch c.product p
				""", Category.class).getResultList();
	}
}
