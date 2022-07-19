package com.jdc.test;

import org.junit.jupiter.api.Test;

import com.jdc.query.entity.Category;
import com.jdc.query.entity.Product;

public class InnerJoinTest extends AbstractTest{

	void implicit_inner_join() {
		
		var jpql = "select distinct(p.category) from Product p";
		var query = em.createQuery(jpql, Category.class);
		
		query.getResultStream().map(Category::getName).forEach(System.out::println);
		
	}
	
	@Test
	void explicit_inner_join() {
		
		var jpql = "select distinct(p.product) from Sale s join s.products p";
		var query = em.createQuery(jpql, Product.class);
		
		var list = query.getResultStream().map(Product::getName).toList();
		System.out.println(list);
	}
}
