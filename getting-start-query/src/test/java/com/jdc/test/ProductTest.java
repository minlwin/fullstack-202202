package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.query.entity.Product;

public class ProductTest extends AbstractTest{

	@ParameterizedTest
	@CsvSource({
		"fo,3",
		"foo,3",
		"foods,3",
		"foodd,0"
	})
	void find_by_category_name_like(String category, int count) {
		
		var query = em.createNamedQuery("Product.findByCategoryNameLike", Product.class);
		query.setParameter("name", category.toLowerCase().concat("%"));
		
		var result = query.getResultList();
		assertEquals(count, result.size());
	}
}
