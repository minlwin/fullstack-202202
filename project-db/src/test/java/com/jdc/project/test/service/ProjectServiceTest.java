package com.jdc.project.test.service;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.project.model.ProjectDbException;
import com.jdc.project.model.service.ProjectService;
import static com.jdc.project.test.utils.ProjectServiceTestUtils.*;

@TestMethodOrder(OrderAnnotation.class)
@SpringJUnitConfig(locations = "classpath:application.xml")
@Sql({
	"classpath:/members.sql",
	"classpath:/projects.sql"
})
public class ProjectServiceTest {

	@Autowired
	private ProjectService service;
	
	@Value("${project.empty.name}")
	private String noName;
	@Value("${project.empty.manager}")
	private String noManager;
	@Value("${project.empty.start}")
	private String noStartDate;	
	
	@Disabled
	@Order(1)
	@ParameterizedTest
	@ValueSource(strings = {
			"7,Test Project 1,Just Testing,3,20220602,6",
			"7,Test Project 2,Just Testing,4,20220615,12",
			"7,Test Project 3,Just Testing,5,20220625,18",
	})
	void should_create_project(String csv) {
		
		var expectedId = id(csv);
		var project = dto(csv);
		
		var id = service.create(project);
		
		assertEquals(expectedId, id);
	}
	
	@Disabled
	@Order(2)
	@ParameterizedTest
	@ValueSource(strings = {
			",Just Testing,3,20220602,6",
			",Just Testing,4,20220615,12",
			",Just Testing,5,20220625,18",
	})
	void should_not_create_no_name(String csv) {
		var exception = assertThrows(ProjectDbException.class, () -> service.create(dto(csv)));
		assertEquals(noName, exception.getMessage());
	}
	
	@Disabled
	@Order(2)
	@ParameterizedTest
	@ValueSource(strings = {
			"Test Project 1,Just Testing,,20220602,6",
			"Test Project 2,Just Testing,,20220615,12",
			"Test Project 3,Just Testing,,20220625,18",
	})
	void should_not_create_no_manager(String csv) {
		var exception = assertThrows(ProjectDbException.class, () -> service.create(dto(csv)));
		assertEquals(noManager, exception.getMessage());
	}
	
	@Disabled
	@Order(2)
	@ParameterizedTest
	@ValueSource(strings = {
			"Test Project 1,Just Testing,3,,6",
			"Test Project 2,Just Testing,4,,12",
			"Test Project 3,Just Testing,5,,18",
	})
	void should_not_create_no_start(String csv) {
		var exception = assertThrows(ProjectDbException.class, () -> service.create(dto(csv)));
		assertEquals(noStartDate, exception.getMessage());
	}
	
}
