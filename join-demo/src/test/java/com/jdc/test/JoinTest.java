package com.jdc.test;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.join.demo.entity.Category;
import com.jdc.join.demo.entity.Product;
import com.jdc.join.demo.entity.Supplier;

@TestMethodOrder(OrderAnnotation.class)
public class JoinTest extends AbstractTest{
	
	
	@Order(1)
	@Test
	void test_default_many_to_one() {
		
		var data = em.find(Product.class, 1);
		System.out.println(data.getCategory().getName());
	}
	
	@Order(2)
	@Test
	void test_default_one_to_many() {
		
		var category = em.find(Category.class, 1);
		System.out.println(category.getName());
		
		category.getProduct().stream()
			.map(Product::getName)
			.forEach(System.out::println);
	}
	
	@Order(3)
	@Test
	void test_default_many_to_many() {
		
		var data = em.find(Supplier.class, 1);
		System.out.println(data.getName());
	}
	
	@Order(4)
	@Test
	void test_to_one_jpql_join() {
		
		var jpql = "select p from Product p join p.category c where c.name = :name";

		var query = em.createQuery(jpql, Product.class);
		query.setParameter("name", "Foods");
		
		var list = query.getResultList();
		list.stream().map(Product::getName).forEach(System.out::println);
	}
	
	@Order(5)
	@Test
	void test_to_many_jpql_join() {
		
		var jpql = "select p from Product p join p.supplier s where s.name = :supplier";
		var query = em.createQuery(jpql, Product.class);
		query.setParameter("supplier", "196 Store");
		
		var list = query.getResultList();
		list.stream().map(Product::getName).forEach(System.out::println);
		
	}
		

}
