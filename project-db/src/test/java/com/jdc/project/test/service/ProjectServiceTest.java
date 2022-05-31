package com.jdc.project.test.service;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.project.model.service.ProjectService;

@TestMethodOrder(OrderAnnotation.class)
@SpringJUnitConfig(locations = "classpath:application.xml")
@Sql({
	"classpath:/members.sql",
	"classpath:/projects.sql"
})
public class ProjectServiceTest {

	@Autowired
	private ProjectService service;
	
	@Order(1)
	@ParameterizedTest
	@ValueSource(strings = {
			"7,Test Project 1,Just Testing,3,20220602,6",
			"7,Test Project 2,Just Testing,4,20220615,12",
			"7,Test Project 3,Just Testing,5,20220625,18",
	})
	void should_create_project(String csv) {
		
	}
	
	@Order(2)
	@ParameterizedTest
	@ValueSource(strings = {
			"Test Project 1,Just Testing,,20220602,6",
			"Test Project 2,Just Testing,,20220615,12",
			"Test Project 3,Just Testing,,20220625,18",
	})
	void should_not_create_no_manager(String csv) {
		
	}
}
