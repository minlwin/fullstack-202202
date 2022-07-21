package com.jdc.test;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.query.dto.CustomerDates;
import com.jdc.query.dto.ProductCountByCategory;
import com.jdc.query.dto.SaleProductCountByTownship;

@TestMethodOrder(OrderAnnotation.class)
public class GroupByTest extends AbstractTest{

	@Test
	@Order(1)
	void test_group_by() {
		
		var jpql = """
				select new com.jdc.query.dto.ProductCountByCategory(
					c.name, count(p.id)) from Product p 
				join p.category c group by c.name order by c.name
				""";
		
		var query = em.createQuery(jpql, ProductCountByCategory.class);
		var list = query.getResultList();
		
		System.out.println(list);
	}
	
	@Test
	@Order(2)
	void test_having() {
		
		var jpql = """
				select new com.jdc.query.dto.ProductCountByCategory(
					c.name, count(p.id)) from Product p 
				join p.category c group by c.name 
				having count(p.id) > 2 
				order by c.name
				""";
		
		var query = em.createQuery(jpql, ProductCountByCategory.class);
		var list = query.getResultList();
		
		System.out.println(list);
	}
	
	@Test
	@Order(3)
	void test_min_max() {
		
		var jpql = """
				select new com.jdc.query.dto.CustomerDates(
					c.id, c.name, min(s.saleDate), max(s.saleDate)) 
				from Sale s join s.customer c 
				group by c.id, c.name order by c.name
				""";
		
		var query = em.createQuery(jpql, CustomerDates.class);
		var list = query.getResultList();
		
		System.out.println(list);
	}
	
	@Test
	@Order(4)
	void test_sum() {
		
		var list = em.createQuery("""
				select new com.jdc.query.dto.SaleProductCountByTownship(
					t.id, t.name, p.id, p.name, p.price, sum(ps.quantity)
				) from ProductSale ps 
				join ps.product p 
				join ps.sale.customer.address.township t 
				group by t.id, t.name, p.id, p.name, p.price 
				order by t.name, p.name
				""", SaleProductCountByTownship.class).getResultList();
		
		list.forEach(System.out::println);
		
	}
}
