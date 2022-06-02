package com.jdc.project.test.service;

import static com.jdc.project.test.utils.MemberServiceTestUtils.assertMemberValues;
import static com.jdc.project.test.utils.MemberServiceTestUtils.dto;
import static com.jdc.project.test.utils.MemberServiceTestUtils.id;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.project.model.ProjectDbException;
import com.jdc.project.model.dto.Member.Role;
import com.jdc.project.model.service.MemberService;

@TestMethodOrder(OrderAnnotation.class)
@SpringJUnitConfig(locations = "classpath:application.xml")
@Sql("classpath:/members.sql")
public class MemberServiceTest {
	
	@Autowired
	MemberService service;
	
	@Value("${member.duplicate.login}")
	private String duplicatePassword;
	@Value("${member.empty.name}")
	private String noName;
	@Value("${member.empty.login}")
	private String noLoginId;
	@Value("${member.empty.password}")
	private String noPassword;


	@Order(1)
	@ParameterizedTest
	@ValueSource(strings = {
		"7,Min Lwin,minlwin,minlwin,Admin,true",
		"7,Kyaw Lwin,kyawlwin,kyawlwin,Member,true",
	})
	void should_create_member_success(String csv) {
		
		// Prepare Data
		var expectedId = id(csv);
		var dto = dto(csv);
		
		// Business Method Execution
		int resultId = service.create(dto);
		
		// Check Result
		assertEquals(expectedId, resultId);
	}
	
	@Order(2)
	@ParameterizedTest
	@ValueSource(strings = {
		",minlwin,minlwin,Admin,true",
		",kyawlwin,kyawlwin,Member,true",
	})
	void should_not_create_member_no_name(String csv) {
		// Prepare Data
		var dto = dto(csv);
		
		// Business Method Execution
		var exception = assertThrows(ProjectDbException.class, () -> service.create(dto));
		
		// Check Result
		assertEquals(noName, exception.getMessage());
	}
	
	@Order(3)
	@ParameterizedTest
	@ValueSource(strings = {
		"Min Lwin,,minlwin,Admin,true",
		"Min Lwin,,kyawlwin,Member,true",
	})
	void should_not_create_member_no_login_id(String csv) {
		// Prepare Data
		var dto = dto(csv);
		
		// Business Method Execution
		var exception = assertThrows(ProjectDbException.class, () -> service.create(dto));
		
		// Check Result
		assertEquals(noLoginId, exception.getMessage());
	}
	
	@Order(4)
	@ParameterizedTest
	@ValueSource(strings = {
		"Min Lwin,minlwin,,Admin,true",
		"Min Lwin,kyawlwin,,Member,true",
	})
	void should_not_create_member_no_password(String csv) {
		// Prepare Data
		var dto = dto(csv);
		
		// Business Method Execution
		var exception = assertThrows(ProjectDbException.class, () -> service.create(dto));
		
		// Check Result
		assertEquals(noPassword, exception.getMessage());
	}
	
	@Order(5)
	@ParameterizedTest
	@ValueSource(strings = {
		"Ko Ko Oo,kokooo,kokooo,Admin,true",
		"Aung Aung,aungaung,aungaung,Member,true",
	})
	void should_not_create_member_duplicate_login_id(String csv) {
		// Prepare Data
		var dto = dto(csv);
		
		// Business Method Execution
		var exception = assertThrows(ProjectDbException.class, () -> service.create(dto));
		
		// Check Result
		assertEquals(duplicatePassword, exception.getMessage());
	}
	
	@Order(6)
	@ParameterizedTest
	@ValueSource(strings = {
		"1,Ko Ko Oo,kokooo,kokooo,Admin,true",
		"2,Aung Aung,aungaung,aungaung,Member,true",
	})
	void should_found_member_by_id(String csv) {
		// Prepare Data
		var id = id(csv);
		var dto = dto(csv);
		
		// Business Method Execution
		var result = service.findById(id);
		
		// Check Result
		assertMemberValues(dto, result);
	}

	@Order(7)
	@ParameterizedTest
	@CsvSource({
		"1,Thidar,Member",
		"2,Nilar,Admin"
	})
	void should_update_member_name_and_role(int id, String name, Role role) {
		// Business Method Execution
		var result = service.updateNameAndRole(id, name, role);

		// Check Result
		assertEquals(1, result);
		
		var dto = service.findById(id);
		assertEquals(name, dto.getName());
		assertEquals(role, dto.getRole());
	}
	
	@Order(8)
	@ParameterizedTest
	@CsvSource({
		"kokooo,kokooo,KOKOOO",
		"aungaung,aungaung,AUNGAUNG"
	})
	void should_change_password(String loginId, String oldPass, String newPass) {
		// Business Method Execution
		var result = service.changePassword(loginId, oldPass, newPass);

		// Check Result
		assertEquals(1, result);
	}
	
	@Order(9)
	@ParameterizedTest
	@CsvSource({
		"kokooo,kokoo,KOKOOO",
		"aungaung,aungaun,AUNGAUNG"
	})
	void should_not_change_password_with_different_old_password(String loginId, String oldPass, String newPass) {
		// Business Method Execution
		var exception = assertThrows(ProjectDbException.class, () -> service.changePassword(loginId, oldPass, newPass));
		
		// Check Result
		assertEquals("Please check your last login id.", exception.getMessage());
	}
	
	@Order(10)
	@ParameterizedTest
	@CsvSource({
		"kokooo,kokooo,kokooo",
		"aungaung,aungaung,aungaung"
	})
	void should_not_change_with_same_passwords(String loginId, String oldPass, String newPass) {
		// Business Method Execution
		var exception = assertThrows(ProjectDbException.class, () -> service.changePassword(loginId, oldPass, newPass));
		
		// Check Result
		assertEquals("You can't set the same password. Please change your new password.", exception.getMessage());
	}
	
	@Order(11)
	@ParameterizedTest
	@CsvSource({
		"kokooo,true",
		"aungaung,false"
	})
	void should_change_state(String loginId, boolean active) {
		// Business Method Execution
		var result = service.changeState(loginId, active);

		// Check Result
		assertEquals(1, result);
	}
	
	@Order(11)
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5})
	void should_delete_with_id(int id) {
		// Business Method Execution
		var result = service.delete(id);

		// Check Result
		assertEquals(1, result);
	}
	
	
	@Order(12)
	@ParameterizedTest
	@CsvSource({
		"Admin,1",
		"Member,5"
	})
	void should_search_by_role_only(Role role, int count) {
		
		// Business Method Execution
		var list = service.search(role, null, null);
		
		// Check Result
		assertEquals(count, list.size());
	}
	
	@Order(13)
	@ParameterizedTest
	@CsvSource({
		"Admin,Aung Aung,0",
		"Member,Aung Aung, 1"
	})
	void should_search_by_role_and_name_like(Role role, String name, int count) {
		// Business Method Execution
		var list = service.search(role, name, null);
		
		// Check Result
		assertEquals(count, list.size());
	}

	@Order(14)
	@ParameterizedTest
	@CsvSource({
		"true,3",
		"false,3"
	})
	void should_search_by_status(boolean status, int count) {
		
		// Business Method Execution
		var list = service.search(null, null, status);
		
		// Check Result
		assertEquals(count, list.size());
	}
}
