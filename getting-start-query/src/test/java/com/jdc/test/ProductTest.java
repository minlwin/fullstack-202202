package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.query.entity.Product;

public class ProductTest extends AbstractTest{

	@ParameterizedTest
	@CsvSource({
		"f,2",
		"b,1",
		"e,0"
	})
	void find_by_category_name_like(String category, int count) {
		
		var query = em.createNamedQuery("Product.findByCategoryNameLike", Product.class);
		query.setParameter("name", category.toLowerCase().concat("%"));
		
		var result = query.getResultList();
		assertEquals(count, result.size());
	}
}
