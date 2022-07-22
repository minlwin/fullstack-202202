package com.jdc.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.demo.entity.Category;
import com.jdc.demo.repo.CategoryRepo;

@SpringJUnitConfig(locations = "classpath:config-wihtout-jpa-setting.xml")
public class CategoryRepoTest {
	
	@Autowired
	private CategoryRepo repo;
	
	@Test
	void test_save() {
		
		var data = new Category();
		data.setName("Drinks");
		
		var result = repo.save(data);
		assertEquals(1, result.getId());
	}

}
