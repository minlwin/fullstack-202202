package com.jdc.project.test.service;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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
@Sql("classpath:/projects.sql")
public class ProjectServiceTest {

	@Autowired
	private ProjectService service;
	
	@Value("${project.empty.name}")
	private String noName;
	@Value("${project.empty.manager}")
	private String noManager;
	@Value("${project.empty.start}")
	private String noStartDate;	
	
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
	@Order(3)
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
	@Order(4)
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
	
	@Disabled
	@Order(5)
	@ParameterizedTest
	@ValueSource(strings = {
			"1,Book Store,Shopping System,2,2022-05-10,6,Aung Aung,aungaung",
			"2,Project DB,Project Management System,3,2022-04-01,12,Aung Naing,aungnaing",
			"3,Smart Kitchen,Restaurant Management System,4,2022-02-15,9,Thiha,thiha",
			"4,Doctor Help,Clinick Management System,2,2022-05-10,6,Aung Aung,aungaung",
			"5,Order Me,Order Management System,2,2022-05-10,18,Aung Aung,aungaung",
			"6,The Movies,Movies Informations Provider,3,2022-05-10,6,Aung Naing,aungnaing"
	})
	void should_found_with_id(String csv) {
		var id = id(csv);
		var dto = dto(csv);
		
		var result = service.findById(id);
		
		assertNotNull(result);
		
		assertEquals(dto.getId(), result.getId());
		assertEquals(dto.getName(), result.getName());
		assertEquals(dto.getDescription(), result.getDescription());
		assertEquals(dto.getManagerId(), result.getManagerId());
		assertEquals(dto.getManagerLogin(), result.getManagerLogin());
		assertEquals(dto.getManagerName(), result.getManagerName());
		assertEquals(dto.getStartDate(), result.getStartDate());
		assertEquals(dto.getMonths(), result.getMonths());
		
	}

	@Disabled
	@Order(6)
	@ParameterizedTest
	@CsvSource({
		",,,,6",
		"project,,,,1",
		",Aung,,,5",
		",,2022-05-01,,3",
		",,,2022-05-01,4",
	})
	void should_search_correctly(String project, String manager, LocalDate dateFrom, LocalDate dateTo, int size) {
		
		var list = service.search(project, manager, dateFrom, dateTo);
		
		assertNotNull(list);
		assertEquals(size, list.size());
	}
	
	@Disabled
	@Order(7)
	@ParameterizedTest
	@CsvSource({
		"1,Book Catalog,Book Info Microservice,2022-10-12,18,1",
		"2,Medicine Catalog,Medicine Info Microservice,2022-11-12,12,1",
		"3,Member Catalog,Security Microservice,2022-12-12,9,1",
	})
	void should_updated(int id, String name, String description, LocalDate startDate, int month, int expected) {
		
		var result = service.update(id, name, description, startDate, month);
		assertEquals(expected, result);
	}
	
	@Disabled
	@Order(8)
	@ParameterizedTest
	@CsvSource({
		"1,1",
		"2,1",
		"3,1"
	})
	void should_deleted(int id, int expected) {
		int result = service.deleteById(id);
		assertEquals(expected, result);
	}
	
}
