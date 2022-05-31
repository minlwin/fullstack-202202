package com.jdc.project.test.utils;

import static org.junit.jupiter.api.Assertions.*;

import com.jdc.project.model.dto.Member;
import com.jdc.project.model.dto.Member.Role;

public class MemberServiceTestUtils {

	public static int id(String csv) {
		var array = csv.split(",");
		return Integer.parseInt(array[0]);
	}
	
	public static Member dto(String csv) {
		var array = csv.split(",");
		if(array.length == 6) {
			return new Member(array[1], array[2], array[3], role(array[4]), Boolean.valueOf(array[5]));
		}
		return new Member(array[0], array[1], array[2], role(array[3]), Boolean.valueOf(array[4]));
	}
	
	public static void assertMemberValues(Member expected, Member actural) {
		assertNotNull(expected, "Expected Value is null");
		assertNotNull(actural, "Actual Value is null");
		
		assertEquals(expected.getName(), actural.getName(), "Member Name");
		assertEquals(expected.getLoginId(), actural.getLoginId(), "Member Login Id");
		assertEquals(expected.getPassword(), actural.getPassword(), "Member Passwor");
		assertEquals(expected.getRole(), actural.getRole(), "Member Role");
		assertEquals(expected.isActive(), actural.isActive(), "Member State");	
	}

	
	private static Role role(String str) {
		return null == str ? null : Role.valueOf(str);
	}
	
}
