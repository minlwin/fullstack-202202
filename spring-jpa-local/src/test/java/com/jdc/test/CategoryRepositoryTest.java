package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.spring.jpa.local.config.ApplicationConfig;
import com.jdc.spring.jpa.local.entity.Category;
import com.jdc.spring.jpa.local.repo.CategoryRepository;

@SpringJUnitConfig(
		classes = ApplicationConfig.class
)
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository repository;
	
	@Test
	void save_test() {
		
		var data = new Category();
		data.setName("Foods");
		
		var result = repository.save(data);
		assertEquals(1, result.getId());
	}

}
