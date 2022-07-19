package com.jdc.test;

import org.junit.jupiter.api.Test;

import com.jdc.join.demo.dto.SupplierDto;

public class ProjectionTest extends AbstractTest{

	@Test
	void search_supplier_name_with_product() {
		
		var jpql = "select new com.jdc.join.demo.dto.SupplierDto(s.id, s.name, s.phone) from Supplier s join s.product p where p.name = :product";
		var query = em.createQuery(jpql, SupplierDto.class);
		query.setParameter("product", "Potato Chips");
		
		var list = query.getResultList();
		System.out.println(list);
	}
}
